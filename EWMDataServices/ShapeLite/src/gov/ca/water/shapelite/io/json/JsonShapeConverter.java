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
package gov.ca.water.shapelite.io.json;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeCategory;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.coordinate.CoordFactory;
import gov.ca.water.shapelite.coordinate.CoordM;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class JsonShapeConverter {

  /**
   * The WKID for WGS1984.
   */
  private static final int WGS84 = 4326;

  /**
   * Private constructor.
   */
  private JsonShapeConverter() {

  }

  /**
   * Gets a shape from the raw geometry JSON.
   *
   * @param geometry The String geometry json starting with the open brace.
   * @param hasM Boolean, true if the vertices will have M values.
   * @param hasZ Boolean, true if the vertices will have Z values.
   * @return The Shape created.
   */
  public static Shape getShape(JsonGeometry<?> geometry, boolean hasM, boolean hasZ) {
    Shape result = Shape.getNullShape();
    if (geometry instanceof JsonPoint) {
      JsonPoint point = (JsonPoint) geometry;
      return getPoint(point, hasM, hasZ);
    }
    if (geometry instanceof JsonLine) {
      JsonLine line = (JsonLine) geometry;
      return getLine(line, hasM, hasZ);
    }
    if (geometry instanceof JsonPolygon) {
      JsonPolygon polygon = (JsonPolygon) geometry;
      return getPolygon(polygon, hasM, hasZ);
    }
    return result;
  }

  /**
   * Gets a Shape representation of the point.
   *
   * @param point The JsonPoint representation of the point object.
   * @param hasM Boolean, true if the point has M.
   * @param hasZ Boolean, true if the shape type has Z.
   * @return The Shape created from the JSON geometry content.
   */
  public static Shape getPoint(JsonPoint point, boolean hasM, boolean hasZ) {
    double x = 0;
    double y = 0;
    double m = 0;
    double z = 0;
    if (!Double.isNaN(point.getX())) {
      x = point.getX();
    }
    if (!Double.isNaN(point.getY())) {
      y = point.getY();
    }
    Coord coord;
    if (hasZ) {
      if (!Double.isNaN(point.getZ())) {
        z = point.getZ();
      }
      if (!Double.isNaN(point.getM())) {
        m = point.getM();
      }
      coord = new CoordZ(x, y, z, m);
    } else if (hasM) {
      if (!Double.isNaN(point.getM())) {
        m = point.getM();
      }
      coord = new CoordM(x, y, m);
    } else {
      coord = new CoordXY(x, y);
    }
    Shape result = new Shape(coord);
    return result;
  }

  /**
   * Gets a Shape representation.
   *
   * @param geometry The JsonLine geometry to convert.
   * @param hasM Boolean, true if the vertices have M values.
   * @param hasZ Boolean, true if the vertices have Z values.
   * @return The equivalent Shape.
   */
  public static Shape getLine(JsonLine geometry, boolean hasM, boolean hasZ) {
    Shape shape = new Shape(ShapeType.PolyLine);
    for (double[][] path : geometry.getPaths()) {
      Part part = new Part();
      for (double[] vertex : path) {
        part.getCoordinates().add(CoordFactory.getCoord(vertex, hasM, hasZ));
      }
      shape.getParts().add(part);
    }
    shape.calculateBounds();
    return shape;
  }

  /**
   * Gets a Shape representation.
   *
   * @param geometry The JsonPolygon geometry to translate.
   * @param hasM Boolean, true if the polygon has M values.
   * @param hasZ Boolean, true if the polygon has Z values.
   * @return the Shape
   */
  public static Shape getPolygon(JsonPolygon geometry, boolean hasM, boolean hasZ) {
    Shape shape = new Shape(ShapeType.Polygon);
    for (double[][] ring : geometry.getRings()) {
      Part part = new Part();
      for (double[] vertex : ring) {
        part.getCoordinates().add(CoordFactory.getCoord(vertex, hasM, hasZ));
      }
      part.setClosed(true);
      shape.getParts().add(part);

    }

    shape.calculateBounds();
    return shape;
  }

  /**
   * Get Geometry.
   *
   * @param shape The Shape to convert to a geometry. This defaults to the WGS84
   * spatial reference.
   * @return A matching JsonGeometry for the specified shape.
   */
  public static JsonGeometry<?> getGeometry(Shape shape) {
    return getGeometry(shape, WGS84);
  }

  /**
   * Get Geometry.
   *
   * @param shape The Shape to convert to a geometry.
   * @param wkid The well known id for the projection.
   * @return A JsonGeometry for the shape.
   */
  public static JsonGeometry<?> getGeometry(Shape shape, int wkid) {
    if (shape.getShapeType().getCategory() == ShapeCategory.Polygon) {
      JsonPolygon polygon = new JsonPolygon();
      polygon.setSpatialReference(new SpatialReference(wkid));
      polygon.setRings(new double[shape.getParts().size()][][]);
      for (int iPart = 0; iPart < shape.getParts().size(); iPart++) {
        Part part = shape.getParts().get(iPart);
        double[][] coords = new double[part.getCoordinates().size()][];
        for (int iCoord = 0; iCoord < part.getCoordinates().size(); iCoord++) {
          coords[iCoord] = new double[2];
          coords[iCoord][0] = part.getCoordinates().get(iCoord).getX();
          coords[iCoord][1] = part.getCoordinates().get(iCoord).getY();
        }
        polygon.getRings()[iPart] = coords;
      }
      return polygon;
    }
    if (shape.getShapeType().getCategory() == ShapeCategory.PolyLine) {
      JsonLine lineString = new JsonLine();
      lineString.setSpatialReference(new SpatialReference(wkid));
      lineString.setPaths(new double[shape.getParts().size()][][]);
      for (int iPart = 0; iPart < shape.getParts().size(); iPart++) {
        Part part = shape.getParts().get(iPart);
        double[][] coords = new double[part.getCoordinates().size()][];
        for (int iCoord = 0; iCoord < part.getCoordinates().size(); iCoord++) {
          coords[iCoord] = new double[2];
          coords[iCoord][0] = part.getCoordinates().get(iCoord).getX();
          coords[iCoord][1] = part.getCoordinates().get(iCoord).getY();
        }
        lineString.getPaths()[iPart] = coords;
      }
      return lineString;
    }
    if (shape.getShapeType().getCategory() == ShapeCategory.MultiPoint) {
      JsonMultiPoint multiPoint = new JsonMultiPoint();
      multiPoint.setSpatialReference(new SpatialReference(wkid));
      multiPoint.setPoints(new double[shape.getParts().size()][][]);
      for (int iPart = 0; iPart < shape.getParts().size(); iPart++) {
        Part part = shape.getParts().get(iPart);
        double[][] coords = new double[part.getCoordinates().size()][];
        for (int iCoord = 0; iCoord < part.getCoordinates().size(); iCoord++) {
          coords[iCoord] = new double[2];
          coords[iCoord][0] = part.getCoordinates().get(iCoord).getX();
          coords[iCoord][1] = part.getCoordinates().get(iCoord).getY();
        }
        multiPoint.getPoints()[iPart] = coords;
      }
      return multiPoint;
    }
    if (shape.getShapeType().getCategory() == ShapeCategory.Point) {
      JsonPoint point = new JsonPoint();
      point.setX(shape.getX());
      point.setY(shape.getY());
      if (shape.hasM()) {
        point.setM(shape.getM());
      }
      if (shape.hasZ()) {
        point.setZ(shape.getZ());
      }
      return point;
    }
    throw new UnsupportedOperationException("Not supported");
  }
}
