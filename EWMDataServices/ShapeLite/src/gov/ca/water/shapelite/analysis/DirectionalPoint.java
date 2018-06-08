/*
 * The MIT License
 *
 * Copyright 2014 hdunsford.
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
package gov.ca.water.shapelite.analysis;

import com.vividsolutions.jts.geom.Coordinate;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Nullable;
import gov.ca.water.shapelite.coordinate.CoordBase;
import gov.ca.water.shapelite.coordinate.CoordValues;

/**
 * Directional Point.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DirectionalPoint extends CoordBase {

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

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * Direction x component (delta x).
   */
  private double dx;
  /**
   * Direction y component (delta y).
   */
  private double dy;

  //</editor-fold>
  /**
   * Creates a new default instance of a DirectionalPoint.
   */
  public DirectionalPoint() {

  }

  /**
   * Creates a new DirectionalPoint with the specified coord.
   *
   * @param coord The coordinate to use X and Y components for. If this is null,
   * an empty directional point is created.
   */
  public DirectionalPoint(@Nullable Coord coord) {
    if (coord == null) {
      return;
    }
    this.setX(coord.getX());
    this.setY(coord.getY());
  }

  /**
   * Creates a new DirectionalPoint with the specified coord.
   *
   * @param other The directional point to copy properties from. If this is
   * null, an empty DirectionalPoint is created.
   */
  public DirectionalPoint(@Nullable DirectionalPoint other) {
    if (other == null) {
      return;
    }
    this.setX(other.getX());
    this.setY(other.getY());
    this.setDx(other.getDx());
    this.setDy(other.getDy());
  }

  /**
   * Gets the change in x.
   *
   * @return the change in x.
   */
  public final double getDx() {
    return dx;
  }

  /**
   * Sets the change in x.
   *
   * @param dx the change in x to set
   */
  public final void setDx(double dx) {
    this.dx = dx;
  }

  /**
   * Gets the change in y.
   *
   * @return the change in y.
   */
  public final double getDy() {
    return dy;
  }

  /**
   * Sets the change in y.
   *
   * @param dy the change in y to set.
   */
  public final void setDy(double dy) {
    this.dy = dy;
  }

  /**
   * Creates a new DirectionalPoint from this coordinate.
   *
   * @return A Copy of this DirectionPoint object.
   */
  @Override
  @NonNull
  public final DirectionalPoint copy() {
    DirectionalPoint result = new DirectionalPoint();
    result.copyProperties(this);
    return result;
  }

  /**
   * Copies the pertinent X, Y, DX, DY values to this object.
   *
   * @param other The other object to copy.
   */
  @Override
  public final void copyProperties(CoordValues other) {
    this.setX(other.get(X));
    this.setY(other.get(Y));
    if (other instanceof DirectionalPoint) {
      DirectionalPoint dp = (DirectionalPoint) other;
      this.setDx(dp.dx);
      this.setDy(dp.dy);
    }
  }

  /**
   * Gets M.
   *
   * @return
   */
  @Override
  public double getM() {
    return 0;
  }

  /**
   * Sets M.
   *
   * @param m
   */
  @Override
  public void setM(double m) {
    throw new IllegalStateException("DirectionalPoint does not support M");
  }

  /**
   * Gets Z.
   *
   * @return
   */
  @Override
  public double getZ() {
    return 0;
  }

  /**
   * Sets Z.
   *
   * @param z
   */
  @Override
  public void setZ(double z) {
    throw new IllegalStateException("DirectionalPoint does not support Z");
  }

  /* {@inheritdoc} */
  @Override
  public final boolean hasM() {
    return false;
  }

  /* {@inheritdoc} */
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
