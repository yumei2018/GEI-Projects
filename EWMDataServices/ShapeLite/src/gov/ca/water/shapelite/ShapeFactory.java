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
package gov.ca.water.shapelite;

import gov.ca.water.shapelite.coordinate.CoordFactory;
import gov.ca.water.shapelite.coordinate.CoordM;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class ShapeFactory {

  /**
   * Creates a new instance of the ShapeFactory class.
   */
  private ShapeFactory() {

  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Given an array of interleaved xy values, this will return a list of
   * coordiantes.
   *
   * @param xy An array of interleaved coordinates like [x1,y1,x2,y2...xn,yn].
   * @return A list of Coord objects.
   */
  public static List<Coord> createCoords(double[] xy) {
    return createCoords(xy, null, null);
  }

  /**
   * Given an array of interleaved xy values, this will return a list of
   * coordinates.
   *
   * @param xy An array of interleaved coordinates like [x1,y1,x2,y2...xn,yn].
   * This cannot be null.
   * @param m An array of m coordinates. If this is null, and z is also null,
   * then this will create use a coordinate type without m.
   * @param z An array of z coordinates. If this is null, coordinates will be of
   * a type without Z.
   * @return A list of 3D Coord objects.
   */
  public static List<Coord> createCoords(@NonNull double[] xy, @Nullable double[] m,
      @Nullable double[] z) {
    List<Coord> result = new ArrayList<>();

    for (int i = 0; i < xy.length / 2; i++) {
      Coord c;
      if (z != null) {
        c = new CoordZ();
      } else if (m != null) {
        c = new CoordM();
      } else {
        c = new CoordXY();
      }
      c.setX(xy[i * 2]);
      c.setY(xy[i * 2 + 1]);
      if (m != null && m.length > i) {
        c.set(Coord.Index.M, m[i]);
      }
      if (z != null && z.length > i) {
        c.set(Coord.Index.Z, z[i]);
      }
      result.add(c);
    }
    return result;
  }

  /**
   * Coordinates are assumed to be stored in an array of arrays, where the
   * interior array is the coordinate, containing [x,y] or [x,y,z] format.
   *
   * @param pointArray The coordinate array.
   * @return The List of coordinates.
   */
  public static List<Coord> createCoords(@NonNull double[][] pointArray) {
    if (pointArray == null) {
      throw new IllegalArgumentException("pointArray cannot be null.");
    }
    List<Coord> result = new ArrayList<>();
    for (double[] point : pointArray) {
      Coord c = CoordFactory.get(point);
      result.add(c);
    }
    return result;
  }

  /**
   * Creates a multi-point shape from the GeoJson array format.
   *
   * @param pointArray The array of position arrays.
   * @return A newly created multipoint.
   */
  public static Shape fromMultiPoint(@NonNull double[][] pointArray) {
    if (pointArray == null) {
      throw new IllegalArgumentException("pointArray cannot be null.");
    }
    List<Coord> coords = createCoords(pointArray);
    ShapeType type = ShapeType.MultiPointZ;
    if (coords.isEmpty() || !coords.get(0).hasZ()) {
      type = ShapeType.MultiPoint;
    }
    Shape result = new Shape(type);
    Part part = new Part();
    part.getCoordinates().addAll(coords);
    result.getParts().add(part);
    return result;
  }

  /**
   * Creates a multi-point shape from the GeoJson array format.
   *
   * @param pointArray The array of position arrays.
   * @return A newly created multipoint.
   */
  public static Shape fromLineString(@NonNull double[][] pointArray) {
    if (pointArray == null) {
      throw new IllegalArgumentException("pointArray cannot be null.");
    }
    List<Coord> coords = createCoords(pointArray);
    ShapeType type = ShapeType.PolyLineZ;
    if (coords.isEmpty() || !coords.get(0).hasZ()) {
      type = ShapeType.PolyLine;
    }
    Shape result = new Shape(type);
    Part part = new Part();
    part.getCoordinates().addAll(coords);
    result.getParts().add(part);
    result.calculateBounds();
    return result;
  }

  /**
   * Creates an appropriate mulit-part polyline shape from the specified array
   * of linestring arrays.
   *
   * @param pointArray The array of linestrings.
   * @return The newly created Shape object.
   */
  public static Shape fromMultiLineString(@NonNull double[][][] pointArray) {
    if (pointArray == null) {
      throw new IllegalArgumentException("pointArray cannot be null.");
    }
    Shape result = new Shape(ShapeType.PolyLine);
    for (double[][] lineString : pointArray) {
      Part part = new Part();
      List<Coord> coords = createCoords(lineString);
      part.getCoordinates().addAll(coords);
      result.getParts().add(part);
    }
    if (result.first().hasZ()) {
      result.setShapeType(ShapeType.PolyLineZ);
    }
    result.calculateBounds();
    return result;
  }

  /**
   * Creates an appropriate mulit-part polyline shape from the specified array
   * of linestring arrays.
   *
   * @param pointArray The array of linear rings. The first linear ring in the
   * array is the exterior polygon. The first and last points in the linear ring
   * should be the same.
   * @return The newly created Shape object.
   */
  public static Shape fromPolygon(@NonNull double[][][] pointArray) {
    if (pointArray == null) {
      throw new IllegalArgumentException("pointArray cannot be null.");
    }
    Shape result = new Shape(ShapeType.Polygon);
    boolean first = true;
    for (double[][] lineString : pointArray) {
      Part part = new Part();
      part.setClosed(true);
      List<Coord> coords = createCoords(lineString);
      part.getCoordinates().addAll(coords);

      // remove redundnat last point.
      Optional<Coord> maybeLast = part.getLast();
      Optional<Coord> maybeFirst = part.getFirst();
      if (maybeLast.isPresent()) {
        Coord last = maybeLast.get();
        Coord start = maybeFirst.get();
        if (start.equals(last)) {
          part.getCoordinates().remove(part.getCoordinates().size() - 1);
        }
      }

      // ensure that the winding order is correct for the shapefile format.
      if (first) {
        if (part.isHole()) {
          part.reverse();
        }
      } else if (!part.isHole()) {
        part.reverse();
      }

      result.getParts().add(part);
      first = false;
    }
    if (result.firstHasZ()) {
      result.setShapeType(ShapeType.PolygonZ);
    }
    result.calculateBounds();
    return result;
  }

  /**
   * Creates a single shape from a correctly formatted geoJson polygon array.
   *
   * @param pointArray The array of polygons.
   * @return The Shape created from multiple polygons.
   */
  public static Shape fromMultiPolygon(@NonNull double[][][][] pointArray) {
    if (pointArray == null) {
      throw new IllegalArgumentException("pointArray cannot be null.");
    }
    Shape result = new Shape(ShapeType.Polygon);
    for (double[][][] polygon : pointArray) {
      Shape polygonShape = fromPolygon(polygon);
      result.getParts().addAll(polygonShape.getParts());
    }
    if (result.firstHasZ()) {
      result.setShapeType(ShapeType.PolygonZ);
    }
    result.calculateBounds();
    return result;
  }

  /**
   * Creates a new polyline result from the specified double xy values.
   *
   * @param xy An array of interleaved coordinates like [x1,y1,x2,y2...xn,yn].
   * If this is null or empty, then a null result is created.
   * @return A PolyLine Shape object.
   */
  public static Shape createPolyline(@Nullable double[] xy) {
    if (xy == null || xy.length == 0) {
      return Shape.getNullShape();
    }
    List<Coord> coords = createCoords(xy);
    Shape result = new Shape(ShapeType.PolyLine);
    Part prt = new Part(coords);
    result.getParts().add(prt);
    result.calculateBounds();
    return result;
  }

  /**
   * Creates a new point result from the specified coordinate.
   *
   * @param coord A Coord object. If this is null, a NullShape is returned
   * instead, with ShapeType Null.
   * @return A Point result defined for the single point.
   */
  public static Shape createPoint(@Nullable Coord coord) {
    return new Shape(coord);
  }

  /**
   * Creates as set of points shapes.
   *
   * @param xy An array of interleaved coordinates like [x1,y1,x2,y2...xn,yn].
   * @param m An array of m coordinates.
   * @param z An array of z coordinates.
   * @return A List of point result objects, where each point is a separate
   * result.
   */
  public static List<Shape> createPointShapes(double[] xy,
      double[] m, double[] z) {
    List<Shape> results = new ArrayList<>();
    List<Coord> coords = createCoords(xy, m, z);
    for (Coord coord : coords) {
      results.add(new Shape(coord));
    }
    return results;
  }

  /**
   * Creates a new list of point shapes from the specified list of coordinates.
   *
   * @param coords The list of coordinates.
   * @return the list of shapes.
   */
  public static List<Shape> createPointShapes(List<Coord> coords) {
    List<Shape> result = new ArrayList<>();
    for (Coord coord : coords) {
      Shape shape = new Shape(coord);
      result.add(shape);
    }
    return result;
  }

  //</editor-fold>
}
