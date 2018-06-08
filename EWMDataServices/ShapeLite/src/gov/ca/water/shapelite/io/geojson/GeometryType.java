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

import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.ShapeCategory;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public enum GeometryType {
  /**
   * A single point associated with the feature and attributes.
   */
  Point,
  /**
   * Multiple points associated with the feature and attributes.
   */
  MultiPoint,
  /**
   * A single path of connected vertices associated with the feature and
   * attributes.
   */
  LineString,
  /**
   * Multiple, disconnected paths associated with the feature and attributes.
   */
  MultiLineString,
  /**
   * A single exterior ring with potentially multiple holes associated with the
   * feature and attributes.
   */
  Polygon,
  /**
   * Multiple polygons associated with the feature and attributes.
   */
  MultiPolygon,
  /**
   * An array of shapes in the geometries object.
   */
  GeometryCollection;

  /**
   * Defines a geometry type from a category and an array of coordinates.
   *
   * @param category The category, like point, multipoint, line, or polygon.
   * @param dimension The integer dimension of the coordinates.
   * @return An optional GeometryType. If the coord array matches the expected
   * dimensions, the proper type will be returned. this will return empty if the
   * coordinate array did not match one of the expected depths for the specified
   * category.
   */
  public static Optional<GeometryType> from(ShapeCategory category, Dimension dimension) {
    GeometryType result = null;
    switch (category) {
      case Point:
        result = GeometryType.Point;
        break;
      case MultiPoint:
        result = GeometryType.MultiPoint;
        break;
      case PolyLine:
        if (dimension == Dimension.Two) {
          result = GeometryType.LineString;
        } else if (dimension == Dimension.Three) {
          result = GeometryType.MultiLineString;
        }
        break;
      case Polygon:
        if (dimension == Dimension.Three) {
          result = GeometryType.Polygon;
        } else if (dimension == Dimension.Four) {
          result = GeometryType.MultiPolygon;
        }
        break;
      default:
      // do nothing
    }
    return Optional.ofNullable(result);
  }
}
