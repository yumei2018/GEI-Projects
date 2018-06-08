/*
 * The MIT License
 *
 * Copyright 2012 hdunsford.
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

import gov.ca.water.shapelite.coordinate.CoordType;

/**
 * An Enumeration for the shape types from a shapefile.
 * <ul>
 * <li>NullShape(0)</li>
 * <li>Point(1)</li>
 * <li>PolyLine(3)</li>
 * <li>Polygon(5)</li>
 * <li>MultiPoint(8)</li>
 * <li>PointZ(11)</li>
 * <li>PolyLineZ(13)</li>
 * <li>PolygonZ(15)</li>
 * <li>MultiPointZ(18)</li>
 * <li>PointM(21)</li>
 * <li>PolyLineM(23)</li>
 * <li>PolygonM(25)</li>
 * <li>MultiPointM(28)</li>
 * <li>MultiPatch(31)</li>
 * </ul>
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public enum ShapeType {

  /**
   * Null shape.
   */
  NullShape(0),
  /**
   * Point X, Y.
   */
  Point(1),
  /**
   * Polyline X, Y.
   */
  PolyLine(3),
  /**
   * Polygon X, Y.
   */
  Polygon(5),
  /**
   * Multipoint X, Y.
   */
  MultiPoint(8),
  /**
   * Point X, Y, Z, M.
   */
  PointZ(11),
  /**
   * PolyLine X, Y, Z, M.
   */
  PolyLineZ(13),
  /**
   * Polygon X, Y, Z, M.
   */
  PolygonZ(15),
  /**
   * Multipoint X, Y, Z, M.
   */
  MultiPointZ(18),
  /**
   * Point X, Y, M.
   */
  PointM(21),
  /**
   * PolyLine X, Y, M.
   */
  PolyLineM(23),
  /**
   * PolyLine X, Y, M.
   */
  PolygonM(25),
  /**
   * Multipoint X, Y, M.
   */
  MultiPointM(28),
  /**
   * MultiPatch.
   */
  MultiPatch(31);

  //<editor-fold defaultstate="collapsed" desc="Enum Defintions">
  /**
   * Private value Fields.
   */
  private final int value;

  /**
   * Constructor with value.
   *
   * @param value the Associated Shape Value
   */
  ShapeType(int value) {
    this.value = value;
  }

  /**
   * Get the ShapeType's Value.
   *
   * @return the assigned value.
   */
  public int getValue() {
    return value;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Gets the general category, grouping the M, Z and other factors.
   *
   * @return The general ShapeCategory, regardless of the M, Z status.
   */
  public ShapeCategory getCategory() {
    if (isPoint()) {
      return ShapeCategory.Point;
    }
    if (isMultiPoint()) {
      return ShapeCategory.MultiPoint;
    }
    if (isPolyLine()) {
      return ShapeCategory.PolyLine;
    }
    if (isPolygon()) {
      return ShapeCategory.Polygon;
    }
    return ShapeCategory.Null;
  }

  /**
   * Gets a boolean that indicates whether this ShapeType has a Z value.
   *
   * @return Boolean, true if the shape type is one that has z.
   */
  public boolean hasZ() {
    if (this.equals(ShapeType.PointZ)) {
      return true;
    }
    if (this.equals(ShapeType.MultiPointZ)) {
      return true;
    }
    if (this.equals(ShapeType.PolyLineZ)) {
      return true;
    }
    return this.equals(ShapeType.PolygonZ);
  }

  /**
   * Gets a boolean that indicates whether this ShapeType has an M value.
   *
   * @return Boolean, true if the shapetype is one that has m.
   */
  public boolean hasM() {
    switch (this) {
      case MultiPointM:
      case MultiPointZ:
      case PointM:
      case PointZ:
      case PolyLineM:
      case PolyLineZ:
      case PolygonM:
      case PolygonZ:
        return true;
      default:
        return false;

    }
  }

  /**
   * Gets a boolean that is true if the shapetype is Point, PointM or PointZ.
   *
   * @return A boolean, true if the shapetype is point.
   */
  public boolean isPoint() {
    return isPoint(this);
  }

  /**
   * Gets a boolean that is true if the shapetype is MultiPoint, MultiPointM, or
   * MultiPointZ.
   *
   * @return A boolean that is true if the shapetype is MultiPoint.
   */
  public boolean isMultiPoint() {
    return isMultiPoint(this);
  }

  /**
   * Gets a boolean that is true if the shapetype is PolyLine, PolyLineM, or
   * PolyLineZ.
   *
   * @return a boolean that is true if the shapetype is PolyLine.
   */
  public boolean isPolyLine() {
    return isPolyLine(this);
  }

  /**
   * Gets a boolean that is true if the shapetype is Polygon, PolygonM, or
   * PolygonZ.
   *
   * @return A boolean that is true if the ShapeType is Polygon.
   */
  public boolean isPolygon() {
    return isPolygon(this);
  }

  /**
   * Gets the coordinate type for this shape. M also has X and Y values. Z has
   * X, Y, M values as well as Z.
   *
   * @return The CoordType enumeration for this shape type.
   */
  public CoordType getCoordType() {
    if (hasZ()) {
      return CoordType.Z;
    }
    if (hasM()) {
      return CoordType.M;
    }
    return CoordType.XY;
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Static ShapeType methods">
  /**
   * Given a specific numeric value, return the ShapeType enumeration that
   * matches that numeric value.
   *
   * @param value The integer representation of the shapetype.
   * @return A ShapeType enumeration matching the specified integer.
   */
  public static ShapeType find(int value) {
    for (ShapeType type : ShapeType.values()) {
      if (type.getValue() == value) {
        return type;
      }
    }
    return ShapeType.NullShape;
  }

  /**
   * Get whether represents a point shape.
   *
   * @param shpType the ShapeType to evaluate
   * @return true if shpType = [Point |PointZ | PointM]
   */
  public static boolean isPoint(ShapeType shpType) {
    return ((shpType != null)
        && ((shpType.equals(ShapeType.Point))
        || (shpType.equals(ShapeType.PointZ))
        || (shpType.equals(ShapeType.PointM))));
  }

  /**
   * Get whether represents a MultiPoint shape.
   *
   * @param shpType the ShapeType to evaluate
   * @return true if shpType = [MultiPoint |MultiPointZ | MultiPointM]
   */
  public static boolean isMultiPoint(ShapeType shpType) {
    return ((shpType != null)
        && ((shpType.equals(ShapeType.MultiPoint))
        || (shpType.equals(ShapeType.MultiPointZ))
        || (shpType.equals(ShapeType.MultiPointM))));
  }

  /**
   * Get whether represents a PolyLine shape.
   *
   * @param shpType the ShapeType to evaluate
   * @return true if shpType = [PolyLine |PolyLineZ | PolyLineM]
   */
  public static boolean isPolyLine(ShapeType shpType) {
    return ((shpType != null)
        && ((shpType.equals(ShapeType.PolyLine))
        || (shpType.equals(ShapeType.PolyLineZ))
        || (shpType.equals(ShapeType.PolyLineM))));
  }

  /**
   * Gets the Appropriate ShapeType modified by the specified M and Z
   * properties.
   *
   * @param hasM Boolean, true if the coordinates can support M.
   * @param hasZ Boolean, true if the coordinates can support Z.
   * @return The ShapeType.
   */
  public static ShapeType getPolyLine(boolean hasM, boolean hasZ) {
    if (hasZ) {
      return ShapeType.PolyLineZ;
    }
    if (hasM) {
      return ShapeType.PolyLineM;
    }
    return ShapeType.PolyLine;
  }

  /**
   * Gets the Appropriate ShapeType modified by the specified M and Z
   * properties.
   *
   * @param hasM Boolean, true if the coordinates can support M.
   * @param hasZ Boolean, true if the coordinates can support Z.
   * @return The ShapeType.
   */
  public static ShapeType getPolygon(boolean hasM, boolean hasZ) {
    if (hasZ) {
      return ShapeType.PolygonZ;
    }
    if (hasM) {
      return ShapeType.PolygonM;
    }
    return ShapeType.Polygon;
  }

  /**
   * Get whether represents a PolyLine shape.
   *
   * @param shpType the ShapeType to evaluate
   * @return true if shpType = [Polygon |PolygonZ | PolygonM]
   */
  public static boolean isPolygon(ShapeType shpType) {
    return ((shpType != null)
        && ((shpType.equals(ShapeType.Polygon))
        || (shpType.equals(ShapeType.PolygonZ))
        || (shpType.equals(ShapeType.PolygonM))));
  }

  /**
   * Gets the ShapeType from the category.
   *
   * @param category The category of shape to get.
   * @param hasZ Boolean, true if the shape type should have Z.
   * @param hasM Boolean, true if the shape type should have M.
   * @return The ShapeType which expands the category with Z and M information.
   */
  public static ShapeType fromCategory(ShapeCategory category, boolean hasZ,
      boolean hasM) {
    switch (category) {
      case Null:
        return NullShape;
      case Point:
        if (hasZ) {
          return PointZ;
        }
        if (hasM) {
          return PointM;
        }
        return Point;
      case PolyLine:
        if (hasZ) {
          return PolyLineZ;
        }
        if (hasM) {
          return PolyLineM;
        }
        return PolyLine;
      case MultiPoint:
        if (hasZ) {
          return MultiPointZ;
        }
        if (hasM) {
          return MultiPointM;
        }
        return MultiPoint;
      case Polygon:
        if (hasZ) {
          return PolygonZ;
        }
        if (hasM) {
          return PolygonM;
        }
        return Polygon;
      default:
        return NullShape;
    }

  }

  //</editor-fold>
}
