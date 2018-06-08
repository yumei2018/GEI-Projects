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
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.NonNull;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CoordDistance2D {

  /**
   * The conversion factor to convert miles to meters.
   */
  private static final double KM_TO_FEET = 3280;

  /**
   * The radius of the earth in Km.
   */
  private static final double EARTH_RADIUS = 6373.0;

  /**
   * The number of kilometers per decimal degree at the equator.
   */
  private static final double KM_PER_DD = 111.32;

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
  public final double distance(@NonNull HasXY a, @NonNull HasXY b) {
    if (a == null || b == null) {
      throw new IllegalArgumentException("Neither a, nor b can be null.");
    }
    double dx = a.getX() - b.getX();
    double dy = a.getY() - b.getY();
    return Math.sqrt(dx * dx + dy * dy);
  }

 
  /**
   * Calculates the distance in US Foot, making the assumption that the
   * coordinates are in latitude/longitude.
   *
   * @param a One of the Coordinates to calculate distance for.
   * @param b The other coordinate to compare to this coordinate.
   * @return double distance in feet.
   * @throws IllegalArgumentException if either coordinate is null.
   */
  public final double distanceInFeet(@NonNull Coord a, @NonNull Coord b) {
    if (a == null || b == null) {
      throw new IllegalArgumentException("Neither a, nor b can be null.");
    }
    double dist = distanceInKm(a, b);
    return dist * KM_TO_FEET;
  }

  /**
   * Calculates the distance in km to other coordinate. Assumes that (X, Y)
   * represents Latitude and Longitude. Uses an Earth Radius of 6373.
   *
   * @param a One of the Coordinates to calculate distance for.
   * @param b The other coordinate to compare to this coordinate.
   * @return The double distance in km.
   * @throws IllegalArgumentException if either coordinate is null.
   */
  public final double distanceInKm(@NonNull Coord a, @NonNull Coord b) {
    if (a == null || b == null) {
      throw new IllegalArgumentException("Coord other cannot be null.");
    }
    double lat1 = a.getY();
    double lng1 = a.getX();
    double lat2 = b.getY();
    double lng2 = b.getX();

    double earthRadius = EARTH_RADIUS; //kilometers
    double dLat = Math.toRadians(lat2 - lat1);
    double dLng = Math.toRadians(lng2 - lng1);
    double t = Math.sin(dLat / 2) * Math.sin(dLat / 2)
        + Math.cos(Math.toRadians(lat1))
        * Math.cos(Math.toRadians(lat2))
        * Math.sin(dLng / 2) * Math.sin(dLng / 2);
    double c = 2.0 * Math.atan2(Math.sqrt(t), Math.sqrt(1.0 - t));
    double distInKM = (earthRadius * c);
    return distInKM;
  }

  /**
   * The input coordinate is assumed to be in WGS84.
   *
   * @param coord The coordinate to get a buffer envelope around.
   * @param distance The distance in Feet.
   * @return The Envelope.
   */
  public final Envelope bufferInFeet(@NonNull Coord coord, double distance) {
    if (coord == null) {
      throw new IllegalArgumentException("Argument coord cannot be null.");
    }
    // latitude is always the same factor.
    double dy = distance / (KM_PER_DD * KM_TO_FEET);
    // longitude changes approximately as a function of latitude.
    double dx = dy * Math.cos(Math.toRadians(coord.getY()));
    return new DefaultEnvelope(coord.getX() - dx, coord.getY() - dy,
        coord.getX() + dx, coord.getY() + dy);
  }

}
