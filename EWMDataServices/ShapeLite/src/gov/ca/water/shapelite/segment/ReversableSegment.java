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
package gov.ca.water.shapelite.segment;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.NonNull;

/**
 * The equals method is deliberately very general here.  Equality will be true
 * as long as the other object is a SegmentValues that intersects with this
 * instance.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ReversableSegment extends SegmentBase {

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Initializes a new instance of the Segment class.
   *
   * @param x1 The double x of the start of the segment.
   * @param y1 The double y of the start of the segment.
   * @param x2 The double x of the end of the segment.
   * @param y2 The double y of the end of the segment.
   */
  public ReversableSegment(double x1, double y1, double x2, double y2) {
    super(x1, y1, x2, y2);
  }

  /**
   * Initializes a new instance of the Segment class.
   *
   * @param p1 The start coordinate for the segment.
   * @param p2 The end coordinate for the segment.
   * @throws IllegalArgumentException if p1 or p2 are null.
   */
  public ReversableSegment(@NonNull Coord p1, @NonNull Coord p2) {
    super(p1, p2);
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Tests for equality with another segment. To be equal, the P1 coordinates
   * and P2 coordinates can be the same or reversed.
   *
   * @param obj The other object to test. If the object does not implement
   * SegmentValues, this will return false.
   * @return True if the other segment has P1 and P2 coordinates with the same
   * values as this segment.
   */
  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof SegmentValues)) {
      return false;
    }
    if (obj instanceof SegmentValues) {
      SegmentValues other = (SegmentValues) obj;
      if (this.getP1().equals(other.getP1())
           && this.getP2().equals(other.getP2())) {
        return true;
      }
      return this.equalsReversed(other);
    }
    return false;
  }

  /**
   * Gets a hash code. The hash code is designed to be calculated in a way that
   * makes it reversible so that AB.hashCode() will equal BA.hashCode();
   *
   * @return A hash code that will treat reversed segments as the same thing.
   */
  @Override
  public final int hashCode() {
    return getP1().hashCode() + getP2().hashCode();
  }

  /**
   * Gets a deep copy of this ReversableSegment.
   * @return A new ReversableSegment with the same values, but different objects.
   */
  @Override
  public final ReversableSegment copy() {
    return new ReversableSegment(this.getP1().copy(), this.getP2().copy());
  }

}
