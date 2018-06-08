/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.trace;

import com.google.gson.Gson;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.io.json.JsonGeometry;
import gov.ca.water.shapelite.io.json.JsonPolygon;
import gov.ca.water.shapelite.io.json.JsonShapeConverter;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Reproject;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PolygonTracerJson {

  /**
   * The well known ID for wgs84.
   */
  private static final int WGS84 = 4326;

  /**
   * The reference polygons to trace to.
   */
  private final PolygonTracer tracer;

  /**
   * Polygon Tracer.
   *
   * @param reference The FeatureSet reference.
   */
  public PolygonTracerJson(FeatureSet reference) {
    if (!reference.getFeatureType().isPolygon()) {
      throw new IllegalArgumentException("Only Polygon shapefiles are supported.");
    }
    tracer = new PolygonTracer(reference);
  }

  /**
   * Creates a new instance of the PolygonTracerJson class using the specified
   * polygon shapefile as the source.
   *
   * @param filename The String filename of the shp file to open.
   * @throws java.io.IOException If there was an error reading the file.
   */
  public PolygonTracerJson(String filename) throws IOException {
    ShapefileReader reader = new ShapefileReader();
    reader.open(filename);
    FeatureSet reference = reader.getFeatures();
    if (!reference.getFeatureType().isPolygon()) {
      throw new IllegalArgumentException("Only Polygon shapefiles are supported.");
    }
    tracer = new PolygonTracer(reference);
  }

  /**
   * Creates a new instance of the PolygonTracerJson class using the specified
   * polygon shapefile as the source.
   *
   * @param shp The shp file stream.
   * @param shx The shx file stream.
   * @param dbf The dbf file stream.
   * @param prj The prj file stream.
   * @throws java.io.IOException If there was an error reading the file.
   */
  public PolygonTracerJson(InputStream shp, InputStream shx, InputStream dbf,
      InputStream prj) throws IOException {
    ShapefileReader reader = new ShapefileReader();
    reader.open(shp, shx, dbf, prj);
    FeatureSet reference = reader.getFeatures();
    if (!reference.getFeatureType().isPolygon()) {
      throw new IllegalArgumentException("Only Polygon shapefiles are supported.");
    }
    tracer = new PolygonTracer(reference);
  }

  /**
   * A JsonGeometry polygon string format.
   *
   * @param jsonPolygon The ESRI Json geometry format.
   * @param tolerance the tolerance in units that match the reference
   * projection.
   * @return The JsonGeometry of the modified geometry.
   * @throws ProjectionException if the spatial references didn't work.
   */
  public final String trace(String jsonPolygon, double tolerance)
      throws ProjectionException {
    Gson gson = new Gson();
    JsonPolygon polygon = gson.fromJson(jsonPolygon, JsonPolygon.class);

    Shape drawn = JsonShapeConverter.getShape(polygon, false, false);
    ProjectionInfo drawnProj = null;
    int wkid = WGS84;
    if (polygon.getSpatialReference() != null) {
      wkid = polygon.getSpatialReference().getWkid();
      if (wkid != tracer.getReference().getProjection().getEpsgCode()) {
        Optional<ProjectionInfo> maybeProj
            = ProjectionInfo.fromEpsgCode(polygon.getSpatialReference().getWkid());
        if (maybeProj.isPresent()) {
          drawnProj = maybeProj.get();
          ProjectionInfo refProj = tracer.getReference().getProjection();
          Coord left = drawn.first();
          Coord right = new CoordXY(left.getX() + tolerance, left.getY());

          drawn = Reproject.reprojectShape(drawn, drawnProj, refProj);
          Coord nLeft = drawn.first();
          Coord nRight = Reproject.reprojectCoordinate(right, drawnProj, refProj);
          tolerance = nRight.getX() - nLeft.getX();
        }
      }
    }
    Shape resultShape = tracer.trace(drawn, tolerance);
    if (drawnProj != null) {
      resultShape = Reproject.reprojectShape(resultShape,
          tracer.getReference().getProjection(), drawnProj);

    }
    JsonGeometry<?> geom = JsonShapeConverter.getGeometry(resultShape, wkid);
    String result = gson.toJson(geom);
    return result;
  }

}
