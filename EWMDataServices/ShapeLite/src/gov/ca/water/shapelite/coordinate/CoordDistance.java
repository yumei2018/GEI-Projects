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
public interface CoordDistance {

  /**
   * Calculates the Euclidean distance. If either of the Z coordinates being
   * compared is NaN, then only the 2D coordinates are calculated in the
   * distance.
   *
   * @param a One of the Coordinates to calculate distance for.
   * @param b The other coordinate to compare to this coordinate.
   * @return The double distance in coordinate units.
   * @throws IllegalArgumentException if either coordinate is null.
   */
  double distance(@NonNull Coord a, @NonNull Coord b);

  /**
   * Calculates the distance in US Foot, making the assumption that the
   * coordinates are in latitude/longitude.
   *
   * @param a One of the Coordinates to calculate distance for.
   * @param b The other coordinate to compare to this coordinate.
   * @return double distance in feet.
   * @throws IllegalArgumentException if either coordinate is null.
   */
  double distanceInFeet(@NonNull Coord a, @NonNull Coord b);

  /**
   * Calculates the distance in km to other coordinate. Assumes that (X, Y)
   * represents Latitude and Longitude. Uses an Earth Radius of 6373.
   *
   * @param a One of the Coordinates to calculate distance for.
   * @param b The other coordinate to compare to this coordinate.
   * @return The double distance in km.
   * @throws IllegalArgumentException if either coordinate is null.
   */
  double distanceInKm(@NonNull Coord a, @NonNull Coord b);


}
