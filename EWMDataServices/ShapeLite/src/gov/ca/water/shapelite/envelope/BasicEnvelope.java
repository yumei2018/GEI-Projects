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
package gov.ca.water.shapelite.envelope;

import gov.ca.water.shapelite.Coord;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface BasicEnvelope extends EnvelopeValues {

  /**
   * Gets the coordinate at the geographic center of this envelope.
   *
   * @return A newly created Coordinate calculated to be the center position.
   */
  Coord getCenter();

  /**
   * Gets the double distance between minimum and maximum Y.
   *
   * @return the height.
   */
  double getHeight();

  /**
   * Gets the double distance between minimum and maximum X.
   *
   * @return the width.
   */
  double getWidth();

  /**
   * This tests both coordinates. If both coordinates, (which cannot be null)
   * are empty coordinates, then this envelope is also considered empty.
   *
   * @return A boolean that is true if this Envelope is empty.
   */
  boolean isEmpty();

  /**
   * This tests to see if the Z axis is either NAN or else equal, so that the
   * box is two dimensional.
   *
   * @return A boolean that is true if the Z dimension is NaN for either
   * coordinate, or else if the minimum and maximum Z are equal.
   */
  boolean isFlat();


  /**
   * Corrects the envelope if isEmpty, by interchanging the max and min
   * coordinates if they are inverted. (Only implemented for 2D Envelopes)
   *
   * @see isEmpty
   */
  void correct();

  //</editor-fold>


  /**
   * Gets a boolean that indicates if this Envelope has a Z value. Both the Min
   * and Max coordinates must have a z value for this to be true.
   *
   * @return boolean, true if the envelope has Z values.
   */
  boolean hasZ();

  /**
   * Gets a boolean that indicates if this Envelope has an M value. Both the Min
   * and Max coordinates must have an M value for this to be true.
   *
   * @return boolean, true if the envelope has M values.
   */
  boolean hasM();

}
