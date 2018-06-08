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
public interface MeasurableEnvelope {

  /**
   * Calculates the smallest distance from this box to the specified coordinate.
   *
   * @param position The coordinate to test.
   * @return The double distance to the position.
   */
  double distance(Coord position);

  /**
   * If the point is inside the extents, this returns the largest distance to
   * the bounds. if the point is outside, then rather than finding the smallest
   * distance in the box to the point, this finds the largest distance to the
   * point.
   *
   * @param position The coordinate to test. This cannot be null.
   * @return The double maximum distance.
   * @throws IllegalArgumentException if position is null or empty.
   */
  double maxDistance(@NonNull Coord position);


  /**
   * Gets the distance to the specified envelope.
   * @param envelope The envelope to test.
   * @return The double minimum distance between the specified envelope.  This
   * will be zero if the envelopes intersect.
   */
  double distance(Envelope envelope);

}
