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

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.NonNan;
import gov.ca.water.shapelite.NonNull;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public abstract class CoordBase implements Coord {

  /**
   * Gets the intersection tool for 2D intersection calculations.
   */
  private static final CoordIntersector2D INTERSECT2D = new CoordIntersector2D();

  /**
   * Gets a standard distance tool for 2D distance calculations.
   */
  private static final CoordDistance2D DISTANCE2D = new CoordDistance2D();

  /**
   * A hash offset to use in the hash set calculation.
   */
  protected static final int HASH_OFFSET = 3;

  /**
   * A multiplier to use in a hash map calculation.
   */
  protected static final int HASH_MULTIPLIER = 41;

  /**
   * The bit shift to use for the HASH Calculation.
   */
  protected static final int HASH_SHIFT = 32;

  /**
   * A static EPSILON difference for testing (default=
   * {@linkplain Double#MIN_NORMAL}).
   */
  private static double epsilon = Double.MIN_NORMAL;

  /**
   * The x ordinate, generally Easting or longitude. Due to the ubiquity of
   * algorithms that assume that x and y are valid values, NAN values will not
   * be accepted. Use Optional&lt;Coord&gt; if a calculation returns an invalid
   * result, or throw an exception.
   */
  @NonNan
  private double x;

  /**
   * The y ordinate, generally northing, or latitude. Due to the ubiquity of
   * algorithms that assume that x and y are valid values, NAN values will not
   * be accepted. Use Optional&lt;Coord&gt; if a calculation returns an invalid
   * result, or throw an exception.
   */
  @NonNan
  private double y;

  /**
   * Creates a default instance of the coord base.  Because of serialization,
   * hasValue must default as being true and be explicitly set to false
   * by the empty case.
   *
   */
  public CoordBase() {
  }

  /**
   * Gets the smallest value that is considered different.
   *
   * @return the epsilon
   */
  public static double getEpsilon() {
    return epsilon;
  }

  /**
   * Sets the smallest value difference for which two numbers are considered
   * different.
   *
   * @param aEpsilon the epsilon to set
   */
  public static void setEpsilon(double aEpsilon) {
    epsilon = aEpsilon;
  }

  @Override
  public abstract void copyProperties(CoordValues other);

  /**
   * Gets the X value.
   *
   * @return the x
   */
  @Override
  public final double getX() {
    return x;
  }

  /**
   * @param x the x to set
   * @throws IllegalArgumentException if x is NaN;
   */
  @Override
  public final void setX(@NonNan double x) {
    if (Double.isNaN(x)) {
      throw new IllegalArgumentException("X Coordiante cannot be NaN");
    }
    boolean changed = this.x != x;
    this.x = x;
    if (changed) {
      onChanged();
    }
  }

  /**
   * @return the y
   */
  @Override
  public final double getY() {
    return y;
  }

  /**
   * @param y the y to set
   * @throws IllegalArgumentException if y is NaN;
   */
  @Override
  public final void setY(@NonNan double y) {
    if (Double.isNaN(y)) {
      throw new IllegalArgumentException("Y Coordinate cannot be NaN");
    }
    boolean changed = this.y != y;
    this.y = y;
    if (changed) {
      this.onChanged();
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
  public final double distance2D(@NonNull Coord other) {
    return DISTANCE2D.distance(this, other);
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
  public final boolean intersects2D(final Coord other) {
    return INTERSECT2D.intersects(this, other);
  }

  /**
   * This checks for equality in the X and Y dimensions within the tolerance of
   * EPSILON, which can be custom configured.
   *
   * @param other The other coordinate.
   * @param epsilon the tolerance to use for determining intersection.
   * @return Boolean, true if the coordinates intersect within EPSILON.
   * @throws IllegalArgumentException if Coord other is null.
   */
  @Override
  public final boolean intersects2D(Coord other, double epsilon) {
    return INTERSECT2D.intersects(this, other, epsilon);
  }

  /**
   * Gets the 2D distance in feet to the other coordinate.
   *
   * @param other The other coordinate to get a geographic distance to, assuming
   * that the coordinates are in decimal degrees.
   * @return the distance in feet.
   */
  @Override
  public final double distanceInFeet(Coord other) {
    return DISTANCE2D.distanceInFeet(this, other);
  }

  /**
   * The input coordinate is assumed to be in WGS84.
   *
   * @param distance The distance in Feet.
   * @return The Envelope.
   */
  @Override
  public final Envelope bufferInFeet(double distance) {
    return DISTANCE2D.bufferInFeet(this, distance);
  }

  /**
   * Gets the 2D distance in km to the other coordinate.
   *
   * @param other The other coordinate to get a geographic distance to, assuming
   * that the coordinates are in decimal degrees.
   * @return the distance in Km.
   */
  @Override
  public final double distanceInKm(Coord other) {
    return DISTANCE2D.distanceInKm(this, other);
  }

  /**
   * occurs any time a value is updated using the various setters. This will be
   * overridden in child classes optionally, if they need to be observable.
   */
  protected void onChanged() {

  }

  /**
   * Clears the values in the vector and marks the coordinate as being empty.
   */
  @Override
  public void clear() {
    for (int i = 0; i < getSize(); i++) {
      if (has(i)) {
        set(i, 0);
      }
    }
  }

}
