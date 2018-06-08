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
import gov.ca.water.shapelite.Nullable;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CoordIntersector3D {

  /**
   * A static EPSILON difference for testing (default=
   * {@linkplain Double#MIN_NORMAL}).
   */
  private static final double EPSILON = Double.MIN_NORMAL;


  /**
   * This checks for equality in the X and Y dimensions within the tolerance of
   * EPSILON, which can be custom configured.
   *
   * @param a The first coordinate to compare.
   * @param b The second coordinate to compare.
   * @return Boolean, true if the coordinates intersect within EPSILON. If
   * either coordinate is null, this will return false.
   * @throws IllegalArgumentException if either coordinate return false for
   * hasZ.
   */
  public final boolean intersects(@Nullable Coord a, @Nullable Coord b) {
    return intersects(a, b, EPSILON);
  }

  /**
   * This checks for equality in the X and Y dimensions within the tolerance of
   * EPSILON, which can be custom configured.
   *
   * @param a The first coordinate to compare.
   * @param b The second coordinate to compare.
   * @param epsilon The tolerance for determining if two points are the same.
   * @return Boolean, true if the coordinates intersect within EPSILON. If
   * either coordinate is null, this will return false.
   * @throws IllegalArgumentException if either coordinate return false for
   * hasZ.
   */
  public final boolean intersects(@Nullable Coord a, @Nullable Coord b, double epsilon) {
    if (a == null || b == null) {
      return false;
    }
    if (!a.hasZ() || !b.hasZ()) {
      throw new IllegalArgumentException("Expected coordinates with Z values,"
          + "for 3D distance calculation, but instead at least one coordinate"
          + " returned false for hasZ().");
    }
    double dx = Math.abs(a.getX() - b.getX());
    if (dx > epsilon) {
      return false;
    }
    double dy = Math.abs(a.getY() - b.getY());
    if (dy > epsilon) {
      return false;
    }
    int z = Coord.Index.Z;
    double dz = Math.abs(a.get(z) - b.get(z));
    return dz <= epsilon;

  }

}
