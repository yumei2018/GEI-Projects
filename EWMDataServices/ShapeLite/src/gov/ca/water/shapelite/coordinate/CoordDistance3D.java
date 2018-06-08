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
import gov.ca.water.shapelite.NonNull;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CoordDistance3D {

  /**
   * The conversion factor to convert miles to meters.
   */
  private static final double KM_TO_FEET = 3280;

  /**
   * The radius of the earth in Km.
   */
  private static final double EARTH_RADIUS = 6373.0;

  /**
   * Calculates the Euclidean distance. If either of the Z coordinates being
   * compared is NaN, then only the 2D coordinates are calculated in the
   * distance.
   *
   * @param a One of the Coordinates to calculate distance for.
   * @param b The other coordinate to compare to this coordinate.
   * @return The double distance in coordinate units.
   * @throws IllegalArgumentException if either coordinate is null, or if either
   * coordinate is hasZ is false.
   */
  public final double distance(@NonNull Coord a, @NonNull Coord b) {
    if (a == null || b == null) {
      throw new IllegalArgumentException("Neither a, nor b can be null.");
    }
    if (!a.hasZ() || !b.hasZ()) {
      throw new IllegalArgumentException("The Z parameter was missing "
          + "from one coordinate.");
    }
    double dx = a.getX() - b.getX();
    double dy = a.getY() - b.getY();
    int z = Coord.Index.Z;
    double dz = a.get(z) - b.get(z);
    return Math.sqrt(dx * dx + dy * dy + dz * dz);
  }

}
