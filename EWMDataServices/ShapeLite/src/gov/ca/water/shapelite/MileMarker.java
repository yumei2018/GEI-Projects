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

/**
 * This class stores information about a mile and a location.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MileMarker implements Comparable<MileMarker> {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The Double valued mileage at this location, used for levee or river
   * distances.
   */
  private Double mile;

  /**
   * The geographic coordinate of this mile marker.
   */
  private Coord location;

  //</editor-fold>
  /**
   * Compares the mile marker to another mile marker so that they can be sorted
   * according to the mileage.
   *
   * @param o The other MileMarker.
   * @return Integer that is 1 if this mile is greater than the other mile, 0 if
   * the miles are the same, and -1 if this mile is less than the other mile.
   */
  @Override
  public final int compareTo(MileMarker o) {
    return getMile().compareTo(o.getMile());
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the geographic coordinate of this mile marker.
   *
   * @return the location
   */
  public final Coord getLocation() {
    return location;
  }

  /**
   * Sets the geographic coordinate of this mile marker.
   *
   * @param location the location to set
   */
  public final void setLocation(Coord location) {
    this.location = location;
  }

  /**
   * Gets the Double valued mileage at this location, used for levee or river
   * distances.
   *
   * @return the mile
   */
  public final Double getMile() {
    return mile;
  }

  /**
   * Sets the Double valued mileage at this location, used for levee or river
   * distances.
   *
   * @param mile the mile to set
   */
  public final void setMile(Double mile) {
    this.mile = mile;
  }

  //</editor-fold>
}
