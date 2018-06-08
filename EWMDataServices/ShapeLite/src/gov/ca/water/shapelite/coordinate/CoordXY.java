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
package gov.ca.water.shapelite.coordinate;

import com.vividsolutions.jts.geom.Coordinate;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Nullable;

/**
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CoordXY extends CoordBase {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Initializes a new instance of the Coordinate class.
   */
  public CoordXY() {
    // all values are zero, and hasValue is false.  This defines
    // an empty coordinate, which will still function like a 0,0, but
    // can flag something as being uninnitialized.
  }

  /**
   * Initializes a new instance of the Coordinate class.
   *
   * @param x the horizontal x coordinate.
   * @param y the vertical y coordinate.
   */
  public CoordXY(double x, double y) {
    this.setX(x);
    this.setY(y);
  }

  /**
   * Initializes a new instance of the Coordinate class.
   *
   * @param other the other coordinate to copy values from. If this is null, an
   * Empty coordinate is created.
   */
  public CoordXY(@Nullable HasXY other) {
    if (other == null) {
      return; // no need to copy values, and hasValue should remain false.
    }
    this.setX(other.getX());
    this.setY(other.getY());
  }

  /**
   * Initializes a new instance of the Coordinate class.
   *
   * @param other the other coordinate to copy values from. If this is null, an
   * Empty coordinate is created.
   */
  public CoordXY(@Nullable CoordValues other) {
    if (other == null) {
      return; // no need to copy values, and hasValue should remain false.
    }
    this.setX(other.get(Coord.Index.X));
    this.setY(other.get(Coord.Index.Y));
  }

  /**
   * Initializes a new instance of the Coordinate class.
   *
   * @param other the other coordinate to copy values from. If this is null, an
   * Empty coordinate is created.
   */
  public CoordXY(@Nullable Coord other) {
    this((HasXY)other);
  }



  /**
   * Creates a new Coord object from a JTS Coordinate.
   *
   * @param c The JTS Coordinate, which cannot support M values. If this is
   * null, then an empty coordinate will be created.
   * @throws IllegalArgumentException if either c.x or c.y is NaN.
   */
  public CoordXY(Coordinate c) {
    if (c == null) {
      return; // no need to define coordinates.
    }
    this.setX(c.x);
    this.setY(c.y);
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Generates a copy of this coordinate.
   *
   * @return A duplicate coordinate that is a different object. This will never
   * be null, but may be flagged as Empty.
   */
  @Override
  public final CoordXY copy() {
    CoordXY result = new CoordXY();
    result.copyProperties(this);
    return result;
  }

  /**
   * Copies the X, Y, Z and M values from the other coord object while creating
   * a separate coordinate.
   *
   * @param other The other coordinate to copy from. This can be null, in which
   * case, this coordinate becomes empty.
   */
  @Override
  public final void copyProperties(@Nullable CoordValues other) {
    if (other == null) {
      this.setX(0);
      this.setY(0);
      return;
    }
    this.setX(other.get(Index.X));
    this.setY(other.get(Index.Y));
  }

  /**
   * Calculates the Euclidean distance. If either of the Z coordinates being
   * compared is NaN, then only the 2D coordinates are calculated in the
   * distance.
   *
   * @param other The other coordinate to compare to this coordinate.
   * @return The double distance in coordinate units.
   * @throws IllegalArgumentException if other is not null.
   */
  @Override
  public final double distance(@NonNull Coord other) {
    return super.distance2D(other);
  }

  /**
   * CoordXY does not support M.
   * @return Always returns 0.
   */
  @Override
  public double getM() {
    return 0;
  }

  /**
   * CoordXY does not support setting M values.  This will throw an exception.
   * @param m The double m value to set.
   */
  @Override
  public void setM(double m) {
    throw new IllegalStateException("CoordXY does not support M values.");
  }

  /**
   * CoordXY does not support Z.  This will always return 0.
   * @return Always returns 0.
   */
  @Override
  public double getZ() {
    return 0;
  }

  /**
   * CoordXY does not support Z.  This will throw an exception.
   * @param z The z parameter to set.
   */
  @Override
  public void setZ(double z) {
    throw new IllegalStateException("CoordXY does not support Z values.");
  }

  /**
   * This checks for equality in the X and Y dimensions within the tolerance of
   * EPSILON, which can be custom configured.
   *
   * @param other The other coordinate.
   * @return Boolean, true if the coordinates intersect within EPSILON.
   * @throws IllegalArgumentException if Coord other is null.
   */
  @Override
  public final boolean intersects(final Coord other) {
    return super.intersects2D(other);
  }

  /**
   * Gets a String with the X and Y values.
   *
   * @return A String representation of the coordinate.
   */
  @Override
  public final String toString() {
    return "(X:" + this.getX() + ", Y:" + this.getY() + ")";
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Object Overrides">
  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: return true if obj!=null, and is an instance if Coord, and
   * override {@linkplain #equals(gov.ca.water.shapelite.Coord)} returns
   * true.</p>
   *
   * @param obj The object to compare to.
   * @return Boolean, true if the other object is a coordinate with the same X,
   * Y, M and Z values.
   */
  @Override
  public final boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj instanceof Coord) {
      Coord other = (Coord) obj;
      if (other.hasM() || other.hasZ()) {
        return false;
      }
      double x1 = this.getX();
      double x2 = other.getX();
      double y1 = this.getY();
      double y2 = other.getY();
      if (Math.abs(x2 - x1) > Double.MIN_NORMAL) {
        return false;
      }
      return Math.abs(y2 - y1) <= Double.MIN_NORMAL;
    }
    if (obj instanceof Coordinate) {
      Coordinate other = (Coordinate) obj;
      double x1 = this.getX();
      double x2 = other.x;
      double y1 = this.getY();
      double y2 = other.y;
      if (Math.abs(x2 - x1) > Double.MIN_NORMAL) {
        return false;
      }
      return Math.abs(y2 - y1) <= Double.MIN_NORMAL;
    }

    return false;
  }

  /*{@inheritDoc}*/
  @Override
  public final int hashCode() {
    int hash = HASH_OFFSET;
    hash = HASH_MULTIPLIER * hash
        + (int) (Double.doubleToLongBits(this.getX())
        ^ (Double.doubleToLongBits(this.getX()) >>> HASH_SHIFT));
    hash = HASH_MULTIPLIER * hash
        + (int) (Double.doubleToLongBits(this.getY())
        ^ (Double.doubleToLongBits(this.getY()) >>> HASH_SHIFT));
    return hash;
  }

  //</editor-fold>
  /**
   * Gets a boolean that indicates if this Coord has M.
   *
   * @return hasM
   */
  @Override
  public final boolean hasM() {
    return false;
  }

  /**
   * Gets a boolean that indicates if this Coord has Z.
   *
   * @return hasZ
   */
  @Override
  public final boolean hasZ() {
    return false;
  }

  /**
   * Gets a Java topology suite coordinate from this 2D coordinate.
   *
   * @return a JTS Coordinate.
   */
  @Override
  public final Coordinate toCoordinate() {
    return new Coordinate(this.getX(), this.getY());
  }

  /**
   * Gets the double value associated with the specified size.
   *
   * @param index The integer index of the size.
   * @return The double valued index.
   */
  @Override
  public final double get(int index) {
    switch (index) {
      case 0:
        return getX();
      case 1:
        return getY();
      default:
        throw new IllegalArgumentException("Coord XY does not "
            + "support the get operation for index: " + index);
    }
  }

  /**
   * Gets 2 for CoordXY, for X and Y.
   *
   * @return the integer 2.
   */
  @Override
  public final int getSize() {
    return 2;
  }

  /**
   * Gets a boolean that is true if CoordXY supports the ordinate index. For
   * CoordXY, this will be true for 0 and 1.
   *
   * @param index the integer index to get.
   * @return Boolean, true if this coordinate type supports a value for the
   * specified index.
   */
  @Override
  public final boolean has(int index) {
    return (index == 0 || index == 1);
  }

  /**
   * Sets either the 0 or 1 ordinate for CoordXY.
   *
   * @param index The integer index, either 0 or 1.
   * @param value The value to set.
   * @throws IllegalArgumentException if the value is NaN.
   */
  @Override
  public final void set(int index, double value) {
    switch (index) {
      case 0:
        setX(value);
        break;
      case 1:
        setY(value);
        break;
      default:
        throw new IllegalArgumentException("Coord XY does not "
            + "support the set operation for index: " + index);
    }
  }

  /**
   * This checks for equality in the non M dimensions within the tolerance of
   * EPSILON, which can be custom configured.
   *
   * @param other The other coordinate.
   * @param epsilon the tolerance for intersection.
   * @return Boolean, true if the coordinates intersect within EPSILON, which is
   * defined as Double.MIN_VALUE.
   * @throws IllegalArgumentException if Coord other is null.
   */
  @Override
  public final boolean intersects(Coord other, double epsilon) {
    return intersects2D(other, epsilon);
  }






}
