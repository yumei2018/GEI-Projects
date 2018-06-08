/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gov.ca.water.shapelite.analysis;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineSegment;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.operation.linemerge.LineMerger;
import com.vividsolutions.jts.simplify.DouglasPeuckerSimplifier;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.data.dataset.RasterDataset;
import gov.ca.water.shapelite.data.dataset.RasterArrayDataset;
import gov.ca.water.shapelite.topology.Adapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * This uses http://paulbourke.net/papers/conrec/ to describe the algorithm. The
 * ricardo implementation handled the g case where all three vertices of the
 * triangle lie on the contour level by returning a square. This is being
 * updated with a variation that returns an outline of the flat region.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ContourCreator {

  /**
   * A default geometry factory.
   */
  private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory();

  /**
   * Top left index in a block of four vertices.
   */
  private static final int TOP_LEFT = 0;
  /**
   * Top right index in a block of four vertices.
   */
  private static final int TOP_RIGHT = 1;
  /**
   * Bottom left index in a block of four vertices.
   */
  private static final int BOTTOM_LEFT = 2;
  /**
   * Bottom right index in a block of four vertices.
   */
  private static final int BOTTOM_RIGHT = 3;
  /**
   * The top triangle index in a set of triangles.
   */
  private static final int TOP = 0;
  /**
   * The left triangle index in a set of triangles.
   */
  private static final int LEFT = 1;
  /**
   * The right triangle index in a set of triangles.
   */
  private static final int RIGHT = 2;
  /**
   * The bottom triangle index in a set of triangles.
   */
  private static final int BOTTOM = 3;

  /**
   * half of a cell size.
   */
  private static final double HALF = .5;

  /**
   * The number of vertices in a 4 x 4 block.
   */
  private static final int BLOCK_SIZE = 4;

  /**
   * The number of significant digits for double precision numbers.
   */
  private static final int DOUBLE_PRECISION = 16;

  /**
   * The default number of digits after the zero for the contour field.
   */
  private static final int DEFAULT_DECIMAL = 5;

  /**
   * The value to use to replace missing values by default.
   */
  public static final double DEFAULT_MISSING = -.11;

  /**
   * The multiplier to the cell size to obtain the buffer tolerance for curves
   * to keep when simplifying the resulting shapes.
   */
  public static final double TOLERANCE_RATIO = .1;

  /**
   * The default number of neighbor cells to consider as part of this raster for
   * the purpose of creating the shapes.
   */
  public static final int DEFAULT_CELL_BORDER_SIZE = 2;

  /**
   * The double valued step size to use. Contours will appear as multiples of
   * the step size from 0.
   */
  private Double stepSize;

  /**
   * The String name of the contour field. By default, this is ELEVATION.
   */
  private String contourFieldName;

  /**
   * The number of digits past the decimal for the numeric contour field.
   */
  private int decimals;

  /**
   * The double value that should be used if the value is no-data.
   */
  private double missingContourLevel;

  /**
   * The integer size in cells that is used from the neighbor tiles if a
 RasterArrayDataset is used.
   */
  private int cellBorderSize;

  /**
   * Creates a new instance of the Contour Creator.
   *
   * @param stepSize The z value separation between the contours.
   */
  public ContourCreator(double stepSize) {
    this.contourFieldName = "ELEVATION";
    this.decimals = DEFAULT_DECIMAL;
    this.missingContourLevel = DEFAULT_MISSING;
    this.stepSize = stepSize;
    cellBorderSize = DEFAULT_CELL_BORDER_SIZE;
  }

  /**
   *
   * @param raster A raster, like an elevation raster, to contour. of the
   * contour. For instance, ELEVATION.
   * @return A FeatureSet of the values.
   */
  public final FeatureSet getContours(RasterDataset raster) {
    double[][] data = raster.getData();
    double x0 = raster.getXllcorner();
    double yll = raster.getYllcorner();
    double size = raster.getCellsize();
    double halfCell = HALF * size;
    double y0 = yll + size * raster.getHeight();
    FeatureSet result = new FeatureSet();
    result.getFields().add(new Field(this.contourFieldName, DOUBLE_PRECISION,
        decimals));
    result.getProjection().copyProperties(raster.getProjection());

    /**
     * 0 - top left
     */
    Coordinate[] block = new Coordinate[BLOCK_SIZE];
    List<Double> levels = new ArrayList<>();
    for (double level = 0; level >= raster.getMin(); level -= stepSize) {
      levels.add(level);
    }
    for (double level = stepSize; level <= raster.getMax(); level += stepSize) {
      levels.add(level);
    }
    Collections.sort(levels);
    for (Double level : levels) {
      // merge on level triangles
      LineMerger merger = new LineMerger();
      List<ContourTriangle> onLevel = new ArrayList<>();
      for (int row = 0; row < raster.getHeight() - 1; row++) {
        for (int col = 0; col < raster.getWidth() - 1; col++) {

          double r = row + HALF;
          double c = col + HALF;
          block[TOP_LEFT] = new Coordinate(
              x0 + c * size,
              y0 - r * size,
              getZ(data[row][col],
                  raster.getNoDataValue()));

          block[TOP_RIGHT] = new Coordinate(
              x0 + (c + 1) * size,
              y0 - r * size,
              getZ(data[row][col + 1],
                  raster.getNoDataValue()));

          block[BOTTOM_LEFT] = new Coordinate(
              x0 + c * size,
              y0 - (r + 1) * size,
              getZ(data[row + 1][col],
                  raster.getNoDataValue()));
          block[BOTTOM_RIGHT] = new Coordinate(
              x0 + (c + 1) * size,
              y0 - (r + 1) * size,
              getZ(data[row + 1][col + 1],
                  raster.getNoDataValue()));
          // if any member of the block is a raster no-date value, lets
          // not calculate contours for this block.
          double total = 0;
          int count = 0;
          for (int i = 0; i < BLOCK_SIZE; i++) {
            total += block[i].z;
            count++;
          }

          // Two vertices is ok only if they are diagonally across
          double average = total / count;

          Coordinate m = new Coordinate(x0 + c * size + halfCell,
              y0 - r * size - halfCell, average);
          ContourTriangle[] box = new ContourTriangle[BLOCK_SIZE];
          box[TOP] = new ContourTriangle(block[TOP_LEFT], block[TOP_RIGHT], m,
              raster.getNoDataValue(), missingContourLevel);
          box[LEFT] = new ContourTriangle(block[TOP_LEFT], m,
              block[BOTTOM_LEFT], raster.getNoDataValue(),
              missingContourLevel);
          box[RIGHT] = new ContourTriangle(block[TOP_RIGHT],
              block[BOTTOM_RIGHT], m, raster.getNoDataValue(),
              missingContourLevel);
          box[BOTTOM] = new ContourTriangle(block[BOTTOM_LEFT],
              block[BOTTOM_RIGHT], m, raster.getNoDataValue(),
              missingContourLevel);

          for (int i = 0; i < BLOCK_SIZE; i++) {
            Optional<LineSegment> seg = box[i].getContour(level);
            if (seg.isPresent()) {
              Coordinate[] coords = new Coordinate[2];
              coords[0] = seg.get().p0;
              coords[1] = seg.get().p1;
              LineString ls = GEOMETRY_FACTORY.createLineString(coords);
              merger.add(ls);
            }
            if (box[i].isFlatOnLevel(level)) {
              onLevel.add(box[i]);
            }
          }

        }
      }
      if (!onLevel.isEmpty()) {
        Optional<Geometry> merged = mergeTriangles(onLevel);
        if (merged.isPresent()) {
          merger.add(merged.get());
        }
      }
      Collection<?> lines = merger.getMergedLineStrings();
      if (lines == null || lines.isEmpty()) {
        continue;
      }
      Shape shape = null;
      if (lines.size() == 1) {
        Object item = lines.iterator().next();
        if (item instanceof Geometry) {
          Geometry geom = (Geometry) item;
          DouglasPeuckerSimplifier simp = new DouglasPeuckerSimplifier(geom);
          simp.setDistanceTolerance(size * TOLERANCE_RATIO);
          Geometry simple = simp.getResultGeometry();
          shape = Adapter.getShape(simple, true, true);
        }

      } else {
        LineString[] array = new LineString[lines.size()];
        int i = 0;
        for (Object item : lines) {
          if (item instanceof Geometry) {
            Geometry geom = (Geometry) item;
            DouglasPeuckerSimplifier simp = new DouglasPeuckerSimplifier(geom);
            simp.setDistanceTolerance(size * TOLERANCE_RATIO);
            geom = simp.getResultGeometry();
            if (geom instanceof LineString) {

              array[i] = (LineString) geom;
            }
            i++;
          }

        }
        MultiLineString ls = GEOMETRY_FACTORY.createMultiLineString(array);
        shape = Adapter.getShape(ls, true, true);
      }
      if (shape != null) {
        shape.setShapeType(ShapeType.PolyLineZ);
        for (Coord c : shape.getCoordinates()) {
          c.set(Coord.Index.Z, level);
        }
        Feature f = new Feature(shape);
        f.getAttributes().put(contourFieldName, Double.toString(level));
        result.getFeatures().add(f);
      }

    }

    return result;
  }

  /**
   * Creates a FeatureSet of contours with the contour level as the attribute.
   * This will look for neighboring tiles in order to draw the outside edges.
   *
   * @param rasterArray A raster array, like an elevation raster, to contour.
   * @return A FeatureSet of the values.
   */
  public final FeatureSet getContours(RasterArrayDataset rasterArray) {
    RasterDataset raster = rasterArray.getCenter();
    raster.calculateMinMax();
    double size = raster.getCellsize();
    FeatureSet result = new FeatureSet();
    result.getFields().add(new Field(this.contourFieldName, DOUBLE_PRECISION,
        decimals));
    result.getProjection().copyProperties(raster.getProjection());
    /**
     * 0 - top left
     */

    List<Double> levels = getLevels(raster);
    Collections.sort(levels);
    for (Double level : levels) {
      // merge on level triangles
      LineMerger merger = new LineMerger();
      List<ContourTriangle> onLevel = getSegments(rasterArray, level, merger);
      if (!onLevel.isEmpty()) {
        Optional<Geometry> merged = mergeTriangles(onLevel);
        if (merged.isPresent()) {
          merger.add(merged.get());
        }

      }
      Collection<?> lines = merger.getMergedLineStrings();
      if (lines == null || lines.isEmpty()) {
        continue;
      }
      Shape shape = null;
      if (lines.size() == 1) {
        Object next = lines.iterator().next();
        if (next instanceof Geometry) {
          Geometry geom = (Geometry) next;
          Shape original = Adapter.getShape(geom, true, true);
          Optional<Shape> originalItem = original.intersection(raster.getEnvelope());
          if (originalItem.isPresent()) {
            DouglasPeuckerSimplifier simp = new DouglasPeuckerSimplifier(geom);
            simp.setDistanceTolerance(size * TOLERANCE_RATIO);
            Geometry simple = simp.getResultGeometry();
            shape = Adapter.getShape(simple, true, true);
            Optional<Shape> item = shape.intersection(raster.getEnvelope());
            if (item.isPresent()) {
              shape = item.get();
            }
          }
        }
      } else {
        LineString[] array = new LineString[lines.size()];
        int i = 0;
        for (Object item : lines) {
          if (item instanceof Geometry) {
            Geometry geom = (Geometry) item;
            DouglasPeuckerSimplifier simp = new DouglasPeuckerSimplifier(geom);
            simp.setDistanceTolerance(size * TOLERANCE_RATIO);
            geom = simp.getResultGeometry();
            if (geom instanceof LineString) {
              array[i] = (LineString) geom;
            }
            i++;
          }
        }
        MultiLineString ls = GEOMETRY_FACTORY.createMultiLineString(array);
        shape = Adapter.getShape(ls, true, true);
        Optional<Shape> item = shape.intersection(raster.getEnvelope());
        if (item.isPresent()) {
          shape = item.get();
        }

      }
      if (shape != null) {
        shape.setShapeType(ShapeType.PolyLineZ);
        for (Coord c : shape.getCoordinates()) {
          c.set(Coord.Index.Z, level);
        }
        Feature f = new Feature(shape);
        f.getAttributes().put(contourFieldName, Double.toString(level));
        result.getFeatures().add(f);
      }
    }

    return result;
  }

  /**
   * For a given level in the raster array, this will attempt to build segments,
   * either adding them directly to the LineMerger utility, or grouping them in
   * a separate list to be merged if adjacent cell values fall exactly on a
   * contour line.
   *
   * @param rasterArray The raster array with the center raster being the target
   * raster, and the eight neighbor rasters being helped.
   * @param level The double level.
   * @param merger A LineMerger that can combine the segments.
   * @return A List&lt;ContourTriangle&gt; that can store all the segments that
   * appear exactly on a line so that they can be merged.
   */
  final List<ContourTriangle> getSegments(RasterArrayDataset rasterArray,
      Double level, LineMerger merger) {
    RasterDataset raster = rasterArray.getCenter();
    double x0 = raster.getXllcorner();
    double yll = raster.getYllcorner();
    double size = raster.getCellsize();
    double halfCell = HALF * size;
    double y0 = yll + size * raster.getHeight();
    Coordinate[] block = new Coordinate[BLOCK_SIZE];
    List<ContourTriangle> onLevel = new ArrayList<>();
    for (int row = -cellBorderSize; row < raster.getHeight() + cellBorderSize; row++) {
      for (int col = -cellBorderSize; col < raster.getWidth() + cellBorderSize; col++) {
        double r = row + HALF;
        double c = col + HALF;
        block[TOP_LEFT] = new Coordinate(
            x0 + c * size,
            y0 - r * size,
            rasterArray.getValue(col, row).orElse(missingContourLevel));

        block[TOP_RIGHT] = new Coordinate(
            x0 + (c + 1) * size,
            y0 - r * size,
            rasterArray.getValue(col + 1, row).orElse(missingContourLevel));

        block[BOTTOM_LEFT] = new Coordinate(
            x0 + c * size,
            y0 - (r + 1) * size,
            rasterArray.getValue(col, row + 1).orElse(missingContourLevel));
        block[BOTTOM_RIGHT] = new Coordinate(
            x0 + (c + 1) * size,
            y0 - (r + 1) * size,
            rasterArray.getValue(col + 1, row + 1).orElse(missingContourLevel));
        double total = 0;
        int count = 0;
        boolean skipPlease = false;
        for (int i = 0; i < BLOCK_SIZE; i++) {
          if (block[i].z == missingContourLevel) {
            skipPlease = true;
            break;
          }
          total += block[i].z;
          count++;
        }
        if (skipPlease) {
          continue;
        }

        // Two vertices is ok only if they are diagonally across
        double average = total / count;

        Coordinate m = new Coordinate(x0 + c * size + halfCell,
            y0 - r * size - halfCell, average);
        ContourTriangle[] box = new ContourTriangle[BLOCK_SIZE];
        box[TOP] = new ContourTriangle(block[TOP_LEFT], block[TOP_RIGHT], m,
            raster.getNoDataValue(), missingContourLevel);
        box[LEFT] = new ContourTriangle(block[TOP_LEFT], m,
            block[BOTTOM_LEFT], raster.getNoDataValue(),
            missingContourLevel);
        box[RIGHT] = new ContourTriangle(block[TOP_RIGHT],
            block[BOTTOM_RIGHT], m, raster.getNoDataValue(),
            missingContourLevel);
        box[BOTTOM] = new ContourTriangle(block[BOTTOM_LEFT],
            block[BOTTOM_RIGHT], m, raster.getNoDataValue(),
            missingContourLevel);

        for (int i = 0; i < BLOCK_SIZE; i++) {
          Optional<LineSegment> seg = box[i].getContour(level);
          if (seg.isPresent()) {
            Coordinate[] coords = new Coordinate[2];
            coords[0] = seg.get().p0;
            coords[1] = seg.get().p1;
            LineString ls = GEOMETRY_FACTORY.createLineString(coords);
            merger.add(ls);
          }
          if (box[i].isFlatOnLevel(level)) {
            onLevel.add(box[i]);
          }
        }

      }
    }
    return onLevel;
  }

  /**
   * Gets the levels to use for a particular raster. The levels are based on the
   * stepSize, anchored at zero.
   *
   * @param raster The raster with minimum and maximum values to read.
   * @return A List of Double values showing the contour levels.
   */
  public final List<Double> getLevels(RasterDataset raster) {
    List<Double> levels = new ArrayList<>();
    if (raster.getMin() <= 0 && raster.getMax() >= 0) {
      for (double level = 0; level >= raster.getMin(); level -= stepSize) {
        levels.add(level);
      }
      for (double level = stepSize; level <= raster.getMax(); level += stepSize) {
        levels.add(level);
      }
    } else if (raster.getMin() >= 0 && raster.getMax() > 0) {
      double start = ((int) (raster.getMin() / stepSize)) * stepSize;
      for (double level = start; level <= raster.getMax(); level += stepSize) {
        if (level >= raster.getMin()) {
          levels.add(level);
        }
      }
    } else if (raster.getMin() < 0 && raster.getMax() <= 0) {
      double start = ((int) (raster.getMax() / stepSize)) * stepSize;
      for (double level = start; level >= raster.getMin(); level -= stepSize) {
        if (level <= raster.getMax()) {
          levels.add(level);
        }
      }
    }
    return levels;
  }

  /**
   * Gets a Z value, or a substitute elevation in the case of a no-data value.
   *
   * @param raw the raw value, which may be a no-data value.
   * @param noDataValue The no data value.
   * @return A double Z value or replacement no-data value.
   */
  final double getZ(double raw, double noDataValue) {
    if (raw == noDataValue) {
      return missingContourLevel;
    }
    return raw;
  }

  /**
   * Joins the triangles that are all flat and at the same level.
   *
   * @param onLevel The list of contour triangles to convert to polygons.
   * @return A Geometry of the merged polygons.
   */
  final Geometry mergeTrianglesJts(List<ContourTriangle> onLevel) {

    Polygon[] polygons = new Polygon[onLevel.size()];
    for (int i = 0; i < onLevel.size(); i++) {
      ContourTriangle triangle = onLevel.get(i);
      polygons[i] = triangle.toPolygon();
    }
    MultiPolygon pg = GEOMETRY_FACTORY.createMultiPolygon(polygons);
    try {
      // Buffer sometimes fails, something about comparison method
      // violating a general contract.  In those cases, just return the
      // original polygon.

      return pg.buffer(0);
    } catch (Exception ex) {
      System.out.println("Buffer polygon failed.");
      return pg;

    }
  }

  /**
   * Joins the triangles that are all flat and at the same level.
   *
   * @param onLevel The list of contour triangles to convert to polygons.
   * @return An optional Geometry of the merged polygons.
   */
  final Optional<Geometry> mergeTriangles(List<ContourTriangle> onLevel) {
    TriangleMerger merger = new TriangleMerger();
    Optional<Shape> shape = merger.merge(onLevel);
    if (shape.isPresent()) {
      return Adapter.getGeometry(shape.get());
    }
    return Optional.empty();
  }

  /**
   * Gets the String name of the contour field. By default, this is ELEVATION.
   *
   * @return the contourFieldName
   */
  public final String getContourFieldName() {
    return contourFieldName;
  }

  /**
   * Sets the String name of the contour field. By default, this is ELEVATION.
   *
   * @param contourFieldName the contourFieldName to set
   */
  public final void setContourFieldName(String contourFieldName) {
    this.contourFieldName = contourFieldName;
  }

  /**
   * Gets the number of digits past the decimal for the numeric contour field.
   *
   * @return the integer number of digits.
   */
  public final int getDecimals() {
    return decimals;
  }

  /**
   * Sets the number of digits past the decimal for the numeric contour field.
   *
   * @param decimals the integer number of digits.
   */
  public final void setDecimals(int decimals) {
    this.decimals = decimals;
  }

  /**
   * Gets the double value that should be used if the value is no-data.
   *
   * @return the missingContourLevel
   */
  public final double getMissingContourLevel() {
    return missingContourLevel;
  }

  /**
   * Sets the double value that should be used if the value is no-data.
   *
   * @param missingContourLevel the missingContourLevel to set
   */
  public final void setMissingContourLevel(double missingContourLevel) {
    this.missingContourLevel = missingContourLevel;
  }

  /**
   * Gets the double valued step size to use. Contours will appear as multiples
   * of the step size from 0.
   *
   * @return the stepSize
   */
  public final Double getStepSize() {
    return stepSize;
  }

  /**
   * Sets the double valued step size to use. Contours will appear as multiples
   * of the step size from 0.
   *
   * @param stepSize the stepSize to set
   */
  public final void setStepSize(Double stepSize) {
    this.stepSize = stepSize;
  }

  /**
   * Gets the number of cells of overlap into the neighbor cells that are used
   * during the contour calculation. This is followed by an intersection so that
   * this overlap is only used to try to make the lines more seamless.
   *
   * @return the cellBorderSize The overlap in cells.
   */
  public final int getCellBorderSize() {
    return cellBorderSize;
  }

  /**
   * Sets the number of cells of overlap into the neighbor cells that are used
   * during the calculation. This is followed by an intersection so that this
   * overlap is only used to try to make the lines more seamless.
   *
   * @param cellBorderSize the cellBorderSize to set
   */
  public final void setCellBorderSize(int cellBorderSize) {
    this.cellBorderSize = cellBorderSize;
  }
}
