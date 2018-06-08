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
package gov.ca.water.shapelite.analysis;

import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Segment;

/**
 * A class to represent a specific segment in a part.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PartSegmentJoint {

  /**
   * The part.
   */
  private final Part part;
  /**
   * The segment.
   */
  private final Segment segment;
  /**
   * The index.
   */
  private final int segmentIndex;

  /**
   * Creates a new instance of a PartSegment Joint.
   *
   * @param part The part.
   * @param seg The Segment.
   * @param index The integer index of the first coordinate of the segment in
   * the part.
   */
  public PartSegmentJoint(Part part, Segment seg, int index) {
    this.part = part;
    this.segment = seg;
    this.segmentIndex = index;
  }

  /**
   * Gets the part.
   *
   * @return the part
   */
  public final Part getPart() {
    return part;
  }

  /**
   * Gets the segment.
   *
   * @return the segment
   */
  public final Segment getSegment() {
    return segment;
  }

  /**
   * Gets the zero based integer index.
   *
   * @return the segmentIndex
   */
  public final int getSegmentIndex() {
    return segmentIndex;
  }

}
