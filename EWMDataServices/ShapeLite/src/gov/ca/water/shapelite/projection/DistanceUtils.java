/*
 * The MIT License
 *
 * Copyright 2017 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.projection;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class DistanceUtils {

  /**
   * The number of US Foot survey feet in a meter.
   */
  private static final double US_FOOT_PER_METER = 3.280833333;

  /**
   * The number of Meters in a single Decimal degree at the equator or
   * north/south.
   */
  private static final double METER_PER_DD = 111320;

  /**
   * Private constructor for utils class.
   */
  private DistanceUtils() {
  }

  /**
   * Gets an approximate measure of distance in the projected units that is
   * similar to feet going in. This is the presumed to represent the north south
   * distance, which does not change with latitude.
   *
   * @param feetUS The measured distance in feet.
   * @param proj The projected coordinate system. This cannot be null.
   * @return The double distance in the projectected coordinate system.
   */
  public static double feetToProjectedDistanceY(double feetUS, ProjectionInfo proj) {
    if (proj == null) {
      throw new IllegalArgumentException("Parameter proj cannot be null.");
    }
    double meters = feetUS / US_FOOT_PER_METER;
    if (proj.isLatLon()) {
      return meters / METER_PER_DD;
    } else {
      return meters / proj.getUnit().getMeters();
    }
  }

  /**
   * Gets the projected distance in US_Foot equivalents. For geographic
   * coordinates, this will be more or less accurate for north/south
   * measurements and at the equator and does not attempt to account for the
   * distortion of horizontal distance as one approaches the poles.
   *
   * @param distance The double distance in projected units.
   * @param proj The information about the projected coordinate system.
   * @return The double distance.
   */
  public static double projectedDistanceToFeetY(double distance, ProjectionInfo proj) {
    if (proj == null) {
      throw new IllegalArgumentException("Parameter proj cannot be null.");
    }
    double meters;
    if (proj.isLatLon()) {
      meters = distance * METER_PER_DD;
    } else {
      meters = distance * proj.getUnit().getMeters();
    }
    return meters * US_FOOT_PER_METER;
  }

}
