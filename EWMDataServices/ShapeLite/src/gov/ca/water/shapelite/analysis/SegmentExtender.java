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
package gov.ca.water.shapelite.analysis;

import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Segment;
import gov.ca.water.shapelite.segment.DefaultSegment;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class SegmentExtender {

  /**
   * Extends the segment equally in both directions by distance buffer. If the
   * segment is a point, this will default to expanding the segment
   * horizontally. The Segment should not be null.
   *
   * @param seg The segment to expand equally in both directions.
   * @param distance The buffer distance to expand the segment.
   * @return The expanded segment.
   */
  public final Segment extend2D(@NonNull Segment seg, double distance) {
    if (seg == null) {
      throw new IllegalArgumentException("Argument seg cannot be null.");
    }
    double dx = seg.getP2().getX() - seg.getP1().getX();
    double dy = seg.getP2().getY() - seg.getP1().getY();
    double length = seg.length();
    if (length == 0) {
      double x = distance;
      double x1 = seg.getP1().getX() - x;
      double y1 = seg.getP1().getY();
      double x2 = seg.getP2().getX() + x;
      double y2 = seg.getP2().getY();
      return new DefaultSegment(x1, y1, x2, y2);
    } else {
      double x = distance * dx / length;
      double y = distance * dy / length;
      double x1 = seg.getP1().getX() - x;
      double y1 = seg.getP1().getY() - y;
      double x2 = seg.getP2().getX() + x;
      double y2 = seg.getP2().getY() + y;
      return new DefaultSegment(x1, y1, x2, y2);
    }

  }
}
