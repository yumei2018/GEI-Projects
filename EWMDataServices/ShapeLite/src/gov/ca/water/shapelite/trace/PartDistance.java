/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.trace;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PartDistance {
  /**
   * Part.
   */
  private Part part;

  /**
   * Coord.
   */
  private Coord coord;

  /**
   * The index of the segment the closest match point was on.
   */
  private int segmentIndex;

  /**
   * The closest distance to the feature.
   */
  private double distance;

  /**
   * @return the distance
   */
  public final double getDistance() {
    return distance;
  }

  /**
   * @param distance the distance to set
   */
  public final void setDistance(double distance) {
    this.distance = distance;
  }

  /**
   * Gets the part.
   *
   * @return the part
   */
  public final Optional<Part> getPart() {
    return Optional.ofNullable(part);
  }

  /**
   * Sets the Part.
   *
   * @param part the part to set
   */
  public final void setPart(Part part) {
    this.part = part;
  }

  /**
   * Gets the closest point in the part to the coord.
   *
   * @return the coord
   */
  public final Coord getCoord() {
    return coord;
  }

  /**
   * Sets the Coord.
   *
   * @param coord the coord to set
   */
  public final void setCoord(Coord coord) {
    this.coord = coord;
  }

  /**
   * @return the segmentIndex
   */
  public final int getSegmentIndex() {
    return segmentIndex;
  }

  /**
   * @param index the segmentIndex to set
   */
  public final void setSegmentIndex(int index) {
    this.segmentIndex = index;
  }
}
