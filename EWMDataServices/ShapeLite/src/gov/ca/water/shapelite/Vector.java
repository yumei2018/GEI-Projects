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

import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.coordinate.CoordXY;
import com.vividsolutions.jts.geom.Coordinate;
import gov.ca.water.shapelite.coordinate.CoordDistance3D;
import gov.ca.water.shapelite.coordinate.CoordIntersector3D;
import gov.ca.water.shapelite.coordinate.CoordBase;
import gov.ca.water.shapelite.coordinate.CoordDistance2D;
import gov.ca.water.shapelite.coordinate.CoordIntersector2D;
import gov.ca.water.shapelite.coordinate.CoordValues;
import gov.ca.water.shapelite.coordinate.HasZ;

/**
 * A vector is used for calculating dot products and cross products. The X, Y, Z
 * values inherited from the Coordinate are used to express the magnitude in
 * each direction. The M value is ignored.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class Vector extends CoordBase implements HasZ {

  /**
   * A variable multiplier.
   */
  private static final int HASH_STEP_3D = 1260;

  /**
   * A variable multiplier for when there are only two values.
   */
  private static final int HASH_STEP_2D = 44700;

  /**
   * Gets the intersection tool for 2D intersection calculations.
   */
  private static final CoordIntersector3D INTERSECT3D = new CoordIntersector3D();

  /**
   * Gets the intersection tool for 2D intersection calculations.
   */
  private static final CoordIntersector2D INTERSECT2D = new CoordIntersector2D();

  /**
   * Gets a standard distance tool for 2D distance calculations.
   */
  private static final CoordDistance3D DISTANCE3D = new CoordDistance3D();

  /**
   * Gets a standard distance tool for 2D distance calculations.
   */
  private static final CoordDistance2D DISTANCE2D = new CoordDistance2D();

  /**
   * Vectors support 3 values, X, Y and Z.
   */
  private static final int VECTOR_SIZE = 3;

  /**
   * The integer index to use for X values.
   */
  private static final int X = Coord.Index.X;

  /**
   * The integer index to use for Z values.
   */
  private static final int Y = Coord.Index.Y;

  /**
   * The integer index to use for Z values.
   */
  private static final int Z = Coord.Index.Z;

  /**
   * The Z value for this vector.
   */
  @NonNan
  private double z;

  /**
   * A boolean that indicates if the z value has meaning.
   */
  private boolean hasZ;

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Default constructor.
   */
  public Vector() {
    z = 0;
  }

  /**
   * Initializes a new instance of a vector as the difference between two
   * points.
   *
   * @param start The start coordinate for the tail of the vector.
   * @param end The end coordinate for the tip of the vector.
   */
  public Vector(final Coord start, final Coord end) {
    this.setX(end.getX() - start.getX());
    this.setY(end.getY() - start.getY());
    if (start.hasZ() && end.hasZ()) {
      this.setZ(end.get(Z) - start.get(Z));
      hasZ = true;
    }
  }

  /**
   * Initializes a new vector using an existing coordinate.
   *
   * @param coord The tip of a vector beginning at 0,0. This can be null, in
   * which case an empty 0, 0 vector is returned.
   */
  public Vector(@Nullable Coord coord) {
    if (coord == null) {
      return;
    }
    this.setX(coord.getX());
    this.setY(coord.getY());
    if (coord.hasZ()) {
      this.z = coord.get(Z);
      hasZ = true;
    }
  }

  /**
   * Initializes a new 2D vector.
   *
   * @param x The magnitude of the vx directional component.
   * @param y The magnitude of the vy directional component.
   */
  public Vector(@NonNan double x, @NonNan double y) {
    if (Double.isNaN(x) || Double.isNaN(y)) {
      throw new IllegalArgumentException("x and y cannot be NaN.");
    }
    this.setX(x);
    this.setY(y);
  }

  /**
   * Initializes a new instance of a vector.
   *
   * @param x The magnitude of the vx directional component.
   * @param y The magnitude of the vy directional component.
   * @param z The magnitude of the z directional component.
   */
  public Vector(@NonNan double x, @NonNan double y, @NonNan double z) {
    if (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z)) {
      throw new IllegalArgumentException("x and y cannot be NaN.");
    }
    this.setX(x);
    this.setY(y);
    this.z = z;
    hasZ = true;
  }

  /**
   * Initializes a new instance of a vector from an existing vector.
   *
   * @param original The vector to copy. This can be null, in which case this
   * becomes an empty vector.
   */
  public Vector(@Nullable Vector original) {
    if (original == null) {
      return;
    }
    this.setX(original.getX());
    this.setY(original.getY());
    this.z = original.getZ();
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Adds this vector with the specified vector.
   *
   * @param v The vector to add to this vector. This can be null or empty, in
   * which case a copy of this vector is returned. Z will be the sum of the
   * values if either vector has a z value.
   * @return The resulting vector (achieved by a tip to tail merger of the two.)
   * @throws IllegalArgumentException if v is null.
   */
  public Vector add(@Nullable Vector v) {
    if (v == null) {
      return this.copy();
    }
    double vx = this.getX() + v.getX();
    double vy = this.getY() + v.getY();

    if (this.hasZ && v.hasZ) {
      double localZ = this.z + v.getZ();
      return new Vector(vx, vy, localZ);
    } else {
      return new Vector(vx, vy);
    }

  }

  /**
   * Creates a deep copy of this vector.
   *
   * @return A Vector copy.
   */
  @Override
  public Vector copy() {
    Vector result = new Vector();
    result.copyProperties(this);
    return result;
  }

  /**
   * Copies the vx, vy coordinates of other to this vector. If the other has z,
   * then this vector will also be set to having z.
   *
   * @param other The other vector to copy values from. This can be null, in
   * which case nothing happens.
   */
  @Override
  public void copyProperties(@Nullable CoordValues other) {
    if (other == null) {
      this.setX(0);
      this.setY(0);
      this.setZ(0);
      return;
    }
    this.setX(other.get(X));
    this.setY(other.get(Y));
    if (other.has(Z)) {
      this.setZ(other.get(X));
    }

  }

  /**
   * Returns a 3D vector.
   *
   * @param v The Vector v.
   * @return A new Vector result of the cross product.
   * @throws IllegalArgumentException if v is null.
   */
  public Vector cross(@NonNull Vector v) {
    if (v == null) {
      throw new IllegalArgumentException("Vector v cannot be null.");
    }
    double mz = 0;
    if (hasZ) {
      mz = getZ();
    }
    double vz = 0;
    if (v.hasZ) {
      vz = v.getZ();
    }
    Vector result = new Vector();
    result.setX(getY() * vz - mz * v.getY());
    result.setY(mz * v.getX() - getX() * vz);
    result.setZ(getX() * v.getY() - getY() * v.getX());
    return result;
  }

  /**
   * Calculates the Euclidean distance. If either of the Z coordinates being
   * compared is missing, then only the 2D coordinates are calculated in the
   * distance.
   *
   * @param other The other coordinate to compare to this coordinate.
   * @return The double distance in coordinate units.
   * @throws IllegalArgumentException if other is not null.
   */
  @Override
  public double distance(@NonNull Coord other) {
    if (this.hasZ && other.hasZ()) {
      return DISTANCE3D.distance(this, other);
    } else {
      return DISTANCE2D.distance(this, other);
    }

  }

  /**
   * Gets the dot product of the two vectors.
   *
   * @param v the other vector to get the dot product with.
   * @return The double scalar dot product value.
   * @throws IllegalArgumentException if v is null.
   */
  public double dot(@NonNull Vector v) {
    if (v == null) {
      throw new IllegalArgumentException("Vector v cannot be null.");
    }
    double result = getX() * v.getX() + getY() * v.getY();
    if (hasZ && v.hasZ) {
      result += getZ() * v.getZ();
    }
    return result;
  }

  /**
   * Equals comparison for vectors.
   *
   * @param obj Object
   * @return a boolean that is true if the other object is a vector with the
   * same X, Y and Z configuration.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Vector) {
      Vector other = (Vector) obj;
      if (this.hasZ != other.hasZ) {
        return false;
      }
      if (this.getX() != other.getX()) {
        return false;
      }
      if (this.getY() != other.getY()) {
        return false;
      }
      if (hasZ) {
        if (this.getZ() != other.getZ()) {
          return false;
        }
      }
    }
    return true;
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
  public double get(int index) {
    switch (index) {
      case Coord.Index.X:
        return this.getX();
      case Coord.Index.Y:
        return this.getY();
      case Coord.Index.Z:
        return this.getZ();
      default:
        throw new IllegalArgumentException("Could not match index " + index
            + " with a valid ordinate in CoordM type.");
    }

  }

  /**
   * M is not supported.
   *
   * @return Always returns 0.
   */
  @Override
  public double getM() {
    return 0;
  }

  /**
   * M is not supported. This throws an IllegalStateException.
   *
   * @param m The double to set.
   */
  @Override
  public void setM(double m) {
    throw new IllegalStateException("Vector does not support M values.");
  }

  /**
   * This always returns 4, because an index value of 3 is the M index.
   *
   * @return The integer 4.
   */
  @Override
  public int getSize() {
    return VECTOR_SIZE;
  }

  /* @inheritdoc */
  @Override
  public double getZ() {
    return z;
  }

  //</editor-fold>
  /**
   * Gets a boolean that is true if this coordinate has a value corresponding to
   * the specified integer index.
   *
   * @param index The integer index, like 0 for X, 1 for Y, 2 for Z, 3 for M.
   * @return Boolean that is true for X, Y and M indices.
   */
  @Override
  public boolean has(int index) {
    switch (index) {
      case Coord.Index.X:
        return true;
      case Coord.Index.Y:
        return true;
      case Coord.Index.Z:
        return hasZ;
      default:
        return false;
    }
  }

  /**
   * Gets a numeric hash code representing this vector in a way to try to
   * minimize the collisions.
   *
   * @return An integer hash code.
   */
  @Override
  public int hashCode() {
    int hash = Double.valueOf(this.getX()).hashCode();
    if (hasZ) {
      hash = HASH_STEP_3D * hash + Double.valueOf(this.getY()).hashCode();
      hash = HASH_STEP_3D * hash + Double.valueOf(this.getZ()).hashCode();
    } else {
      hash = HASH_STEP_2D * hash + Double.valueOf(this.getY()).hashCode();
    }
    return hash;
  }

  /**
   * This is always false for Vector.
   *
   * @return false.
   */
  @Override
  public boolean hasM() {
    return false;
  }

  /* @inheritdoc */
  @Override
  public boolean hasZ() {
    return hasZ;
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
  public boolean intersects(Coord other) {
    if (this.hasZ && other.hasZ()) {
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
  public boolean intersects(Coord other, double epsilon) {
    if (this.hasZ && other.hasZ()) {
      return INTERSECT3D.intersects(this, other, epsilon);
    } else {
      return INTERSECT2D.intersects(this, other, epsilon);
    }
  }

  /**
   * Gets the scalar length of this vector. This is the square root of the sum
   * of all the vector components.
   *
   * @return The double length.
   */
  public double length() {
    double dx = getX() * getX();
    double dy = getY() * getY();
    double dz = 0;
    if (hasZ) {
      dz = getZ() * getZ();
    }
    return Math.sqrt(dx + dy + dz);
  }

  /**
   * Multiplies this vector with a scalar value, preserving the direction, but
   * not the magnitude.
   *
   * @param scalar The scalar value to multiply this vector by.
   * @return The resulting Vector.
   */
  public Vector multiply(double scalar) {
    double vx = this.getX() * scalar;
    double vy = this.getY() * scalar;
    if (this.hasZ) {
      double vz = this.getZ() * scalar;
      return new Vector(vx, vy, vz);
    } else {
      return new Vector(vx, vy);
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
   * @param value The value to set.
   * @throws IllegalArgumentException if the value is NaN.
   */
  @Override
  public void set(int index, @NonNan double value) {
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
      default:
        throw new IllegalArgumentException("Could not match index " + index
            + " with a valid ordinate in CoordM type.");
    }
  }

  /**
   * Changes the length to the specified value, preserving the
   * direction...(unless the specified value is negative). If the vector
   * currently has no direction because the current length is zero, then this
   * assigns the entire length to the X axis.
   *
   * @param length The length to set.
   */
  public void setLength(double length) {
    if (length() == 0) {
      setX(length);
      return;
    }
    double r = length / length();
    setX(r * getX());
    setY(r * getY());
    setZ(r * getZ());
  }

  /**
   * Sets a potentially null value as Z. If z is null, then hasZ will be false
   * for point point.
   *
   * @param z the potentially null value for z.
   */
  public void setZ(@Nullable Double z) {
    if (z == null) {
      hasZ = false;
    } else {
      hasZ = true;
      this.z = z;
    }
  }

  /* @inheritdoc */
  @Override
  public void setZ(double z) {
    this.z = z;
    hasZ = true;
  }

  /**
   * Subtract This simply negates the vx and vy dimensions of the specified
   * vector before adding them.
   *
   * @param v The vector to subtract from the current vector.
   * @return The resulting vector from the subtraction.
   * @throws IllegalArgumentException if v is null.
   */
  @NonNull
  public Vector subtract(@NonNull Vector v) {
    if (v == null) {
      throw new IllegalArgumentException("Vector v cannot be null.");
    }
    double vx = this.getX() - v.getX();
    double vy = this.getY() - v.getY();
    if (hasZ && v.hasZ) {
      double vz = this.getZ() - v.getZ();
      return new Vector(vx, vy, vz);
    } else {
      return new Vector(vx, vy);
    }
  }

  /**
   * Creates a new CoordXY or CoordZ with X, Y and Z values equivalent to the
   * components of this vector. If the tail were at the origin, the resulting
   * coordinate would be at the tip of this vector.
   *
   * @return A new Coordinate created from this vector.
   */
  @NonNull
  public Coord toCoord() {
    if (this.hasZ) {
      return new CoordZ(getX(), getY(), getZ());
    } else {
      return new CoordXY(getX(), getY());
    }
  }

  /**
   * Gets a JTS coordinate object representing the X, Y and Z values of this
   * vector.
   *
   * @return Coordinate.
   */
  @Override
  public Coordinate toCoordinate() {
    if (this.hasZ()) {
      return new Coordinate(this.getX(), this.getY(), this.getZ());
    } else {
      return new Coordinate(this.getX(), this.getY());
    }
  }

  /**
   * Normalizes the vector by dividing each X and Y term by the 2D length. This
   * does nothing to Z.
   *
   * @return The normalized vector (this object).
   */
  public Vector normalize() {
    double x = this.getX();
    double y = this.getY();
    double length = x * x + y * y;
    if (length != 0) {
      x = x / length;
      this.setX(x);
      y = y / length;
      this.setY(y);
    }
    return this;
  }

  /**
   * Gets a String representation of the vector.
   *
   * @return The String vector.
   */
  @Override
  public String toString() {
    return "Vector{" + this.getX() + ", " + this.getY() + ", " + this.getZ() + '}';
  }

}
