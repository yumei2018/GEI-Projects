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
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.NonNull;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface ZoomableEnvelope {
/**
   * Adjusts the size of this envelope by a multiplicative factor scale, keeping
   * the anchor point in the same relative location in both views.
   *
   * @param scale The double multiplicative scale factor.
   * @return The resulting Envelope.
   */
  Envelope zoom(double scale);

  /**
   * Adjusts the size of this envelope by a multiplicative factor scale, keeping
   * the anchor point in the same relative location in both views.
   *
   * @param scale The multiplier scale for zooming.
   * @param anchor The position that should be in the same location after
   * zooming.
   * @return the Envelope that is the result of the zoom operation.
   */
  Envelope zoom(double scale, @NonNull Coord anchor);
}
