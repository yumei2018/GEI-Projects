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
package gov.ca.water.shapelite.coordinate;

import com.vividsolutions.jts.geom.Coordinate;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Nullable;

/**
 * This coord represents a special shapefile coordinate type that has M values.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CoordM extends CoordBase implements HasM {

  /**
   * The size of the M ordinates represents one larger than the maximum index
   * value that needs to be checked if in a for loop.
   */
  public static final int M_SIZE = 4;

  /**
   * A measure value.
   */
  private double m;

  /**
   * Creates a new instance of a CoordM class where the hasValue member is set
   * to false.
   */
  public CoordM() {
  }

  /**
   * Creates a new instance of a CoordM given the x, y and m values.
   *
   * @param x The horizontal coordinate or longitude.
   * @param y The vertical coordinate or latitude.
   * @param m A measure value.
   */
  public CoordM(double x, double y, double m) {
    if (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(m)) {
      throw new IllegalArgumentException("x, y and m cannot be NaN.");
    }
    this.setX(x);
    this.setY(y);
    this.setM(m);
  }

  /**
   * Initializes a new instance of the Coordinate class.
   *
   * @param other the other coordinate to copy values from. If this is null, an
   * Empty coordinate is created.
   */
  public CoordM(@Nullable CoordValues other) {
    if (other == null) {
      return; // no need to copy values, and hasValue should remain false.
    }
    doCopyProperties(other);
  }

  /**
   * Generates a copy of this coordinate.
   *
   * @return A duplicate coordinate that is a different object. This will never
   * be null, but may be flagged as Empty.
   */
  @Override
  public final CoordM copy() {
    CoordM result = new CoordM();
    result.copyProperties(this);
    return result;
  }

  /**
   * Copies the X, Y, Z and M values from the other coord object while creating
   * a separate coordinate.
   *
   * @param other The other coordinate to copy from. If the other coordinate is
   * null or empty, then this will become an empty coordinate.
   */
  @Override
  public final void copyProperties(@Nullable CoordValues other) {
    doCopyProperties(other);
  }

  /**
   * Private, non-overridden implementation for use with constructor.
   *
   * @param other The other coordinate to copy properties from.
   */
  private void doCopyProperties(@Nullable CoordValues other) {
    if (other == null) {
      this.setX(0);
      this.setY(0);
      this.setM(0);
      return;
    }
    this.setX(other.get(Coord.Index.X));
    this.setY(other.get(Coord.Index.Y));
    if (other.has(Coord.Index.M)) {
      this.setM(other.get(Coord.Index.M));
    }
  }

  //<editor-fold defaultstate="collapsed" desc="Object Overrides">
  /**
   * Tests for approximate equality. For Coord classes, X,Y and M must all exist
   * and match this item. For Coordinate classes, M is ignored.
   *
   * @param obj The object to test.
   * @return Boolean that is true if the coordinate has the same values.
   */
  @Override
  public final boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj instanceof Coord) {
      Coord other = (Coord) obj;
      if (other.hasZ() || !other.hasM()) {
        return false;
      }
      double x1 = this.getX();
      double x2 = other.getX();
      double y1 = this.getY();
      double y2 = other.getY();
      double m1 = this.getM();
      double m2 = other.get(Coord.Index.M);
      if (Math.abs(m2 - m1) > Double.MIN_NORMAL) {
        return false;
      }
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

  /**
   * CoordM does not support Z. This will always return 0.
   *
   * @return Always returns 0.
   */
  @Override
  public final double getZ() {
    return 0;
  }

  /**
   * CoordM does nto support Z values. This will throw an exception.
   *
   * @param z The double to set.
   */
  @Override
  public final void setZ(double z) {
    throw new IllegalStateException("CoordM does not support Z values.");
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
    hash = HASH_MULTIPLIER * hash
        + (int) (Double.doubleToLongBits(this.getM())
        ^ (Double.doubleToLongBits(this.getM()) >>> HASH_SHIFT));
    return hash;
  }

  //</editor-fold>
  /* @inheritdoc */
  @Override
  public final double getM() {
    return m;
  }

  /* @inheritdoc */
  @Override
  public final void setM(final double m) {
    boolean changed = this.m != m;
    this.m = m;
    if (changed) {
      onChanged();
    }

  }

  /**
   * Gets an empty coordinate.
   *
   * @return An empty coordinate.
   */
  public static CoordM getEmpty() {
    return new CoordM();
  }

  /**
   * Gets a String with the X and Y values.
   *
   * @return A String representation of the coordinate.
   */
  @Override
  public final String toString() {
    return "(X:" + this.getX() + ", Y:" + this.getY() + ", M:" + this.getM() + ")";

  }

  /**
   * Gets a boolean that indicates if this Coord has M.
   *
   * @return hasM
   */
  @Override
  public final boolean hasM() {
    return true;
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
    return distance2D(other);
  }

  /**
   * This checks for equality in the X and Y dimensions within the tolerance of
   * EPSILON, which can be custom configured. This ignores M values.
   *
   * @param other The other coordinate.
   * @return Boolean, true if the coordinates intersect within EPSILON.
   * @throws IllegalArgumentException if Coord other is null.
   */
  @Override
  public final boolean intersects(final Coord other) {
    return intersects2D(other);
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
   * Gets the i'th ordinate from the coordinate. This supports the following:
   * <ul>
   * <li>X - 0</li>
   * <li>Y - 1</li>
   * <li>Z - 2> (This index is not supported for CoordM)</li>
   * <li>M - 3></li>
   * </ul>
   *
   * @param index The zero based integer index.
   * @return A double value represented by the specified index.
   */
  @Override
  public final double get(int index) {
    switch (index) {
      case Coord.Index.X:
        return this.getX();
      case Coord.Index.Y:
        return this.getY();
      case Coord.Index.Z:
        throw new IllegalArgumentException("CoordM does not have a Z "
            + "value, so should not be accessed using the index 2");
      case Coord.Index.M:
        return this.getM();
      default:
        throw new IllegalArgumentException("Could not match index " + index
            + " with a valid ordinate in CoordM type.");
    }

  }

  /**
   * Sets the i'th ordinate from the coordinate. This supports the following:
   * <ul>
   * <li>X - 0</li>
   * <li>Y - 1</li>
   * <li>Z - 2> (This index is not supported for CoordM)</li>
   * <li>M - 3></li>
   * </ul>
   *
   * @param index The zero based integer index.
   * @param value a double value represented by the specified index. This cannot
   * be a NaN value.
   * @throws IllegalArgumentException if the value is NaN.
   */
  @Override
  public final void set(int index, double value) {
    switch (index) {
      case Coord.Index.X:
        this.setX(value);
        break;
      case Coord.Index.Y:
        this.setY(value);
        break;
      case Coord.Index.Z:
        throw new IllegalArgumentException("CoordM does not have a Z "
            + "value, so should not be accessed using the index 2");
      case Coord.Index.M:
        this.setM(value);
        break;
      default:
        throw new IllegalArgumentException("Could not match index " + index
            + " with a valid ordinate in CoordM type.");
    }
  }

  /**
   * This always returns 4, because an index value of 3 is the M index.
   *
   * @return The integer 4.
   */
  @Override
  public final int getSize() {
    return M_SIZE;
  }

  /**
   * Gets a boolean that is true if this coordinate has a value corresponding to
   * the specified integer index.
   *
   * @param index The integer index, like 0 for X, 1 for Y, 2 for Z, 3 for M.
   * @return Boolean that is true for X, Y and M indices.
   */
  @Override
  public final boolean has(int index) {
    switch (index) {
      case Coord.Index.X:
        return true;
      case Coord.Index.Y:
        return true;
      case Coord.Index.M:
        return true;
      default:
        return false;
    }
  }

}
