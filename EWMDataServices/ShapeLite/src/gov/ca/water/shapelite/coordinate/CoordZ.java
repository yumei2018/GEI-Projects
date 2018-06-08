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
 * CoordZ represents a Shapefile coordinate that has both M and Z.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CoordZ extends CoordBase implements HasM, HasZ {

  /**
   * Gets the intersection tool for 2D intersection calculations.
   */
  private static final CoordIntersector2D INTERSECT2D = new CoordIntersector2D();

  /**
   * Gets a standard distance tool for 2D distance calculations.
   */
  private static final CoordDistance2D DISTANCE2D = new CoordDistance2D();

  /**
   * Gets the intersection tool for 3D intersection calculations.
   */
  private static final CoordIntersector3D INTERSECT3D = new CoordIntersector3D();

  /**
   * Gets a standard distance tool for 3D distance calculations.
   */
  private static final CoordDistance3D DISTANCE3D = new CoordDistance3D();

  /**
   * The integer size of ordinates in a CoordZ object.
   */
  private static final int COORDZ_SIZE = 4;

  /**
   * The offset in the get/set methods for the Z parameter.
   */
  public static final int Z = 2;

  /**
   * The offset in the get/set methods for the M parameter.
   */
  public static final int M = 3;

  /**
   * The z ordinate, generally altitude.
   */
  private double z;

  /**
   * The m coordinate, generally a measure value.
   */
  private double m;

  /**
   * Creates a new default CoordZ instance where hasValue is false.
   */
  public CoordZ() {
  }

  /**
   * Initializes a new instance of the Coordinate class.
   *
   * @param other the other coordinate to copy values from. If this is null, an
   * Empty coordinate is created.
   */
  public CoordZ(@Nullable CoordValues other) {
    if (other == null) {
      return; // no need to copy values, and hasValue should remain false.
    }
    copyProperties(other);
  }

  /**
   * Initializes a new instance of the Coordinate class.
   *
   * @param x the longitudinal x coordinate.
   * @param y the latitudinal y coordinate.
   * @param z the altitude z coordinate.
   */
  public CoordZ(final double x, final double y, final double z) {
    this.setX(x);
    this.setY(y);
    // M will have the default 0 value.
    this.z = z;
  }

  /**
   * Initializes a new instance of the Coordinate class.
   *
   * @param x the longitudinal x coordinate.
   * @param y the latitudinal y coordinate.
   * @param z the altitude z coordinate.
   * @param m the extra m coordinate.
   */
  public CoordZ(double x, double y, double z, double m) {
    this.setX(x);
    this.setY(y);
    this.setM(m);
    this.z = z;
  }

  /**
   * Creates a new Coord object from a JTS Coordinate.
   *
   * @param c The JTS Coordinate, which cannot support M values. If this is
   * null, then an empty coordinate will be created.
   */
  public CoordZ(Coordinate c) {
    if (c == null) {
      return; // no need to define coordinates.
    }
    this.setX(c.x);
    this.setY(c.y);
    this.z = c.z;

  }

  /**
   * Generates a copy of this coordinate.
   *
   * @return A duplicate coordinate that is a different object. This will never
   * be null, but may be flagged as Empty.
   */
  @Override
  public final CoordZ copy() {
    CoordZ result = new CoordZ();
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
    if (other.hasZ()) {
      return DISTANCE3D.distance(this, other);
    } else {
      return DISTANCE2D.distance(this, other);
    }
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
      this.setZ(0);
      return;
    }
    this.setX(other.get(Coord.Index.X));
    this.setY(other.get(Coord.Index.Y));
    if (other.has(Coord.Index.M)) {
      this.setM(other.get(Coord.Index.M));
    }
    if (other.has(Coord.Index.Z)) {
      this.setZ(other.get(Coord.Index.Z));
    }
  }

  /**
   * Tests for approximate equality. For Coord classes, X,Y,Z and M must all
   * exist and match this item. For Coordinate classes, M is ignored.
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
      if (!other.hasM() || !other.hasZ()) {
        return false;
      }
      double x1 = this.getX();
      double x2 = other.getX();
      double y1 = this.getY();
      double y2 = other.getY();
      double z1 = this.getZ();
      double z2 = other.get(Coord.Index.Z);
      double m1 = this.getM();
      double m2 = other.get(Coord.Index.M);
      if (Math.abs(m2 - m1) > Double.MIN_NORMAL) {
        return false;
      }
      if (Math.abs(x2 - x1) > Double.MIN_NORMAL) {
        return false;
      }
      if (Math.abs(y2 - y1) > Double.MIN_NORMAL) {
        return false;
      }
      return Math.abs(z2 - z1) <= Double.MIN_NORMAL;
    }
    if (obj instanceof Coordinate) {
      Coordinate other = (Coordinate) obj;
      double x1 = this.getX();
      double x2 = other.x;
      double y1 = this.getY();
      double y2 = other.y;
      double z1 = this.getZ();
      double z2 = other.z;
      if (Math.abs(x2 - x1) > Double.MIN_NORMAL) {
        return false;
      }
      if (Math.abs(y2 - y1) > Double.MIN_NORMAL) {
        return false;
      }
      if (Math.abs(z2 - z1) > Double.MIN_NORMAL) {
        return false;
      }
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
    hash = HASH_MULTIPLIER * hash
        + (int) (Double.doubleToLongBits(this.getM())
        ^ (Double.doubleToLongBits(this.getM()) >>> HASH_SHIFT));
    hash = HASH_MULTIPLIER * hash
        + (int) (Double.doubleToLongBits(this.getZ())
        ^ (Double.doubleToLongBits(this.getZ()) >>> HASH_SHIFT));
    return hash;
  }

  /**
   * This checks for equality in the X and Y dimensions within the tolerance of
   * EPSILON, which can be custom configured. This will also check the Z values,
   * but only if the other value is a CoordZ. M is never considered for
   * intersects. Use equals to compare M values.
   *
   * @param other The other coordinate.
   * @return Boolean, true if the coordinates intersect within EPSILON.
   * @throws IllegalArgumentException if Coord other is null.
   */
  @Override
  public final boolean intersects(final Coord other) {
    if (other.hasZ()) {
      return INTERSECT3D.intersects(this, other);
    } else {
      return INTERSECT2D.intersects(this, other);
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
    if (other.hasZ()) {
      return INTERSECT3D.intersects(this, other, epsilon);
    } else {
      return INTERSECT2D.intersects(this, other, epsilon);
    }
  }

  /**
   * Gets the 2d coordinate.
   *
   * @return The coordinate.
   */
  @Override
  public final Coordinate toCoordinate() {
    return new Coordinate(this.getX(), this.getY(), z);
  }

  /**
   * Gets the Z value, which usually indicates elevation for a point.
   *
   * @return the z
   */
  @Override
  public final double getZ() {
    return z;
  }

  /**
   * @param z the z to set. A NaN value here treats the coordinate like a 2D
   * coordinate.
   * @throws IllegalArgumentException if Z is NaN.
   */
  @Override
  public final void setZ(final double z) {
    boolean changed = this.z != z;
    this.z = z;
    if (changed) {
      this.onChanged();
    }
  }

  /**
   * Gets a String with the X and Y values.
   *
   * @return A String representation of the coordinate.
   */
  @Override
  public final String toString() {
    return "(X:" + this.getX() + ", Y:" + this.getY() + ", Z:" + this.getZ()
        + " M:" + this.getM() + ")";

  }

  /**
   * Gets a boolean that indicates if this Coord has M.
   *
   * @return hasM
   */
  @Override
  public final boolean hasM() {
    return !Double.isNaN(m);
  }

  /**
   * Gets a boolean that indicates if this Coord has Z.
   *
   * @return hasZ
   */
  @Override
  public final boolean hasZ() {
    return !Double.isNaN(z);
  }

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
      this.onChanged();
    }

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
   *
   */
  @Override
  public final double get(int index) {
    switch (index) {
      case Coord.Index.X:
        return this.getX();
      case Coord.Index.Y:
        return this.getY();
      case Coord.Index.Z:
        return this.getZ();
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
   * @param value A double value represented by the specified index. This cannot
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
        this.setZ(value);
        break;
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
    return COORDZ_SIZE;
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
      case Coord.Index.Z:
        return true;
      case Coord.Index.M:
        return true;
      default:
        return false;
    }
  }
}
