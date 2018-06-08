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
package gov.ca.water.shapelite.io.geojson;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeCategory;
import gov.ca.water.shapelite.ShapeFactory;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.coordinate.CoordFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GeoJsonShapeConverter {

  /**
   * Converts a given GeoGeometry into a list of Shapes. Usually, the list would
   * only have one member, unless the geometry is a MultiGeometry, in which case
   * there could be more shapes if different shape types are present. For
   * instance if the multigeometry has points, lines and polygons, there will be
   * three shapes. A MultiPoint, a PolyLine and Polygon shape with multiple
   * parts.
   *
   * @param geometry The geometry to convert.
   * @return The Shape or list of Shapes contained in the geometry.
   */
  public final List<Shape> getShapes(GeoGeometry geometry) {
    List<Shape> result = new ArrayList<>();
    switch (geometry.getType()) {
      case Point:
        result.add(getPoint(geometry));
        break;
      case MultiPoint:
        result.add(getMultiPoint(geometry));
        break;
      case LineString:
        result.add(getLineString(geometry));
        break;
      case MultiLineString:
        result.add(getMultiLineString(geometry));
        break;
      case Polygon:
        result.add(getPolygon(geometry));
        break;
      case MultiPolygon:
        result.add(getMultiPolygon(geometry));
        break;
      case GeometryCollection:
        result.addAll(getMultiGeometry(geometry));
        break;
      default:
      // do nothing;
    }
    return result;
  }

  /**
   * Gets the optional shape for the specified geometry.
   *
   * @param category the category of the shape. If the actual type for the
   * geometry does not correctly match the category, the result will be empty.
   * @param geometry The geometry to convert into a shape.
   * @return A shape based on the category and geometry. If the category and
   * geometry don't match correctly, this may be a shape with a NullShape type.
   */
  public final Shape getShape(ShapeCategory category, GeoGeometry geometry) {
    Shape result = null;
    switch (category) {
      case Point:
        if (geometry.getType() == GeometryType.Point) {
          result = getPoint(geometry);
        }
        break;
      case MultiPoint:
        result = getOnlyMultiPoint(geometry);
        break;
      case PolyLine:
        result = getOnlyPolylines(geometry);
        break;
      case Polygon:
        result = getOnlyPolygons(geometry);
        break;
      default:
      // do nothing;
    }
    return result;
  }

  /**
   * Given a geometry, this will extract the shape that would be appropriate for
   * a polygon shapefile.
   *
   * @param geometry The GeoGeometry.
   * @return A Shape that is either a polygon or a NullType shape.
   */
  private Shape getOnlyPolygons(GeoGeometry geometry) {
    Shape result = new Shape();
    if (null != geometry.getType()) {
      switch (geometry.getType()) {
        case Polygon:
          result = getPolygon(geometry);
          break;
        case MultiPolygon:
          result = getMultiPolygon(geometry);
          break;
        case GeometryCollection:
          List<Shape> shapes = getMultiGeometry(geometry);
          for (Shape shape : shapes) {
            if (shape.getShapeType().getCategory() == ShapeCategory.Polygon) {
              result = shape;
            }
          }
          break;
        default:
          break;
      }
    }
    return result;
  }

  /**
   * Gets the shape that would be appropriate for a line shapefile.
   *
   * @param geometry The geometry.
   * @return The line shape, or else a Shape with a ShapeType.NullShape
   * property.
   */
  private Shape getOnlyPolylines(GeoGeometry geometry) {
    Shape result = Shape.getNullShape();
    if (null != geometry.getType()) {
      switch (geometry.getType()) {
        case LineString:
          result = getLineString(geometry);
          break;
        case MultiLineString:
          result = getMultiLineString(geometry);
          break;
        case MultiPolygon:
          List<Shape> shapes = getMultiGeometry(geometry);
          for (Shape shape : shapes) {
            if (shape.getShapeType().getCategory() == ShapeCategory.PolyLine) {
              result = shape;
            }
          }
          break;
        default:
          break;
      }
    }
    return result;
  }

  /**
   * Gets a Shape that would be appropriate for a MultiPoint shape type.
   *
   * @param geometry The geometry.
   * @return Gets the multipoint shape, or shape with ShapeType.NullShape.
   */
  private Shape getOnlyMultiPoint(GeoGeometry geometry) {
    Shape result = new Shape();
    if (null != geometry.getType()) {
      switch (geometry.getType()) {
        case MultiPoint:
          result = getMultiPoint(geometry);
          break;
        case Point:
          result = getPoint(geometry);
          switch (result.getShapeType()) {
            case Point:
              result.setShapeType(ShapeType.MultiPoint);
              break;
            case PointM:
              result.setShapeType(ShapeType.MultiPointM);
              break;
            case PointZ:
              result.setShapeType(ShapeType.MultiPointZ);
              break;
            default:
            // do nothing
          }
          break;
        case GeometryCollection:
          List<Shape> shapes = getMultiGeometry(geometry);
          for (Shape shape : shapes) {
            if (shape.getShapeType().getCategory() == ShapeCategory.MultiPoint) {
              result = shape;
            }
          }
          break;
        default:
          break;
      }
    }
    return result;
  }

  /**
   * Gets a Point or PointZ depending on whether there is a Z value in the
   * array.
   *
   * @param geometry The Geometry to get.
   * @return The Point.
   */
  private Shape getPoint(GeoGeometry geometry) {
    if (geometry.getDimension() == Dimension.One) {
      ArrayList<?> point = geometry.getCoordinates();
      ArrayList<Double> ordinates = new ArrayList<>();
      Object objX = point.get(0);
      if (objX instanceof Double) {
        ordinates.add((Double) objX);
      } else {
        ordinates.add(0.0);
      }
      Object objY = point.get(1);
      if (objY instanceof Double) {
        ordinates.add((Double) objY);
      } else {
        ordinates.add(0.0);
      }
      if (point.size() > 2) {
        Object objZ = point.get(2);
        if (objZ instanceof Double) {
          ordinates.add((Double) objZ);
        }
      }
      Coord coord = CoordFactory.get(ordinates);
      Shape result = new Shape(coord);
      return result;
    }
    return Shape.getNullShape();
  }

  /**
   * Gets a multiple point object.
   *
   * @param geometry The geometry with coordinate array.
   * @return The newly created Shape.
   */
  private Shape getMultiPoint(GeoGeometry geometry) {
    if (geometry.getDimension() == Dimension.Two) {
      Shape result = ShapeFactory.fromMultiPoint(geometry.getMultiPointCoordinates());
      return result;
    }
    return Shape.getNullShape();
  }

  /**
   * Gets a single connected path as a shape from the geometry.
   *
   * @param geometry The geometry with coordinate array.
   * @return The newly created Shape.
   */
  private Shape getLineString(GeoGeometry geometry) {
    if (geometry.getDimension() == Dimension.Two) {
      Shape result = ShapeFactory.fromLineString(geometry.getLineStringCoordinates());
      return result;
    }
    return Shape.getNullShape();
  }

  /**
   * Gets a single connected path as a shape from the geometry.
   *
   * @param geometry The geometry with coordinate array.
   * @return The newly created Shape.
   */
  private Shape getMultiLineString(GeoGeometry geometry) {
    if (geometry.getDimension() == Dimension.Three) {
      Shape result = ShapeFactory.fromMultiLineString(
          geometry.getMultiLineStringCoordinates());
      return result;
    }
    return Shape.getNullShape();
  }

  /**
   * Gets a polygon.
   *
   * @param geometry The GeoGeometry object to convert.
   * @return A Shape that has only the specified polygon.
   */
  private Shape getPolygon(GeoGeometry geometry) {
    if (geometry.getDimension() == Dimension.Three) {
      Shape result = ShapeFactory.fromPolygon(geometry.getPolygonCoordinates());
      return result;
    }
    return Shape.getNullShape();
  }

  /**
   * Gets a shape with potentially multiple exterior or interior rings.
   *
   * @param geometry The GeoGeometry to convert.
   * @return The Shape to create.
   */
  private Shape getMultiPolygon(GeoGeometry geometry) {
    if (geometry.getDimension() == Dimension.Four) {
      Shape result = ShapeFactory.fromMultiPolygon(
          geometry.getMultiPolygonCoordinates());
      return result;
    }
    return Shape.getNullShape();
  }

  /**
   * In the case of a multi-geometry, this will add all the potential shapes
   * into a list of shapes and return it.
   *
   * @param geometry The GeoGeometry to convert.
   * @return A List of Shape objects. If all the parts are the same shape type,
   * they will be combined to a single, multi-part shape. Otherwise, they will
   * be grouped into a list of shapes, where each shape has a different type.
   */
  private List<Shape> getMultiGeometry(GeoGeometry geometry) {
    List<Shape> results = new ArrayList<>();
    Shape multiPoint = new Shape(ShapeType.MultiPoint);
    Shape multiLine = new Shape(ShapeType.PolyLine);
    Shape multiPolygon = new Shape(ShapeType.Polygon);
    for (GeoGeometry geom : geometry.getGeometries()) {
      List<Shape> shapes = getShapes(geom);
      for (Shape shape : shapes) {
        switch (shape.getShapeType()) {
          case Point:
          case PointM:
          case PointZ:
          case MultiPoint:
          case MultiPointM:
          case MultiPointZ:
            multiPoint.getParts().addAll(shape.getParts());
            break;
          case PolyLine:
          case PolyLineM:
          case PolyLineZ:
            multiLine.getParts().addAll(shape.getParts());
            break;
          case Polygon:
          case PolygonM:
          case PolygonZ:
            multiPolygon.getParts().addAll(shape.getParts());
            break;
          default:
          // do nothing.
        }
      }
    }
    if (!multiPoint.getParts().isEmpty()) {
      results.add(multiPoint);
    }
    if (!multiLine.getParts().isEmpty()) {
      results.add(multiLine);
    }
    if (!multiPolygon.getParts().isEmpty()) {
      results.add(multiPolygon);
    }
    return results;
  }

}
