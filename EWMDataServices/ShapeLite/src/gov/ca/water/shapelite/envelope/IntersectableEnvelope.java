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
import gov.ca.water.shapelite.Nullable;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Segment;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface IntersectableEnvelope {

  /**
   * Gets the envelope that is the overlapping region between the two envelopes.
   *
   * @param other the envelope to intersect with. If other is null or empty,
   * then this method will return a new empty envelope.
   * @return The Envelope of intersection, or an Empty envelope in the case of
   * no intersection.
   */
  Envelope intersect(@Nullable Envelope other);

  /**
   * This will test for intersection of the specified coordinate within this
   * box. If the Z is NaN in either of the bounds of this box or in the
   * coordinate, then the Z dimension will not be tested.
   *
   * @param coordinate The Coordinate to test for intersection with this
   * envelope. If coordinate is null, or either this or coordinate is empty then
   * this will return false.
   * @return A boolean that is true if the coordinate intersects with this
   * Envelope.
   */
  boolean intersects(@Nullable Coord coordinate);

  /**
   * This will test for intersections using the X and Y ordinate. If the Z
   * ordinate is not NaN in any of the values, it will test for intersection in
   * the Z dimension.
   *
   * @param other The Envelope to test for intersection.
   * @return True if the rectangular regions overlap or touch.
   */
  boolean intersects(@Nullable Envelope other);

  /**
   * This will test for intersection of the specified coordinate within this
   * box, but only in X and Y coordinates.
   *
   * @param coordinate The Coordinate to test for intersection with this
   * envelope. If coordinate is null, or either this or coordinate is empty then
   * this will return false.
   * @return A boolean that is true if the coordinate intersects with this
   * Envelope.
   */
  boolean intersects2D(@Nullable Coord coordinate);

  /**
   * This will test for intersections using the X and Y ordinates only.
   *
   * @param seg The Segment to test for intersection.
   * @return True if the rectangular regions overlap or touch the segment.
   */
  boolean intersects2D(@Nullable Segment seg);

  /**
   * This will test for intersections using the X and Y ordinate, and ignore Z
   * or M values, even if one or the other envelope has them.
   *
   * @param other The Envelope to test for intersection. Other can be null. If
   * other is null, or either envelope is empty, this returns false.
   * @return True if the rectangular regions overlap or touch.
   */
  boolean intersects2D(@Nullable Envelope other);

  /**
   * Gets an optional segment representing the intersection of that segment with
   * this envelope in 2D.
   *
   * @param coord The coordinate to intersect.
   * @return The Optional segment, which is empty if there is no intersection,
   * or a 2D version of the coordinate if there is an intersection.
   */
  Optional<Coord> intersection2D(Coord coord);

  /**
   * Gets an optional segment representing the intersection of that segment with
   * this envelope in 2D.
   *
   * @param seg The segment to intersect.
   * @return The Optional segment, which is empty if there is no intersection.
   */
  Optional<Segment> intersection2D(Segment seg);

  /**
   * Gets an optional envelope representing the intersection of the specified
   * envelope with this envelope in 2D.
   *
   * @param env The envelope to intersect.
   * @return The Optional segment, which is empty if there is no intersection.
   */
  Optional<Envelope> intersection2D(Envelope env);

}
