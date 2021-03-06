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

import gov.ca.water.shapelite.Segment;
import gov.ca.water.shapelite.segment.DefaultSegment;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class SegmentExtenderTest {

  public SegmentExtenderTest() {
  }

  /**
   * Test of extend method, of class SegmentExtender.
   */
  @Test
  public void testExtendNone() {
    System.out.println("extend");
    double distance = 0;
    SegmentExtender instance = new SegmentExtender();
    Segment seg = new DefaultSegment(1, 1, 1, 3);
    Segment expResult = new DefaultSegment(1, 1, 1, 3);
    Segment result = instance.extend2D(seg, distance);
    assertEquals(expResult, result);
  }

  /**
   * Test of extend method, of class SegmentExtender.
   */
  @Test
  public void testExtendVertical() {
    System.out.println("extend");
    double distance = 1;
    SegmentExtender instance = new SegmentExtender();
    Segment seg = new DefaultSegment(1, 1, 1, 3);
    Segment expResult = new DefaultSegment(1, 0, 1, 4);
    Segment result = instance.extend2D(seg, distance);
    assertEquals(expResult, result);
  }

  /**
   * Test of extend method, of class SegmentExtender.
   */
  @Test
  public void testExtendHorizontal() {
    System.out.println("extend");
    double distance = 1;
    SegmentExtender instance = new SegmentExtender();
    Segment seg = new DefaultSegment(1, 1, 3, 1);
    Segment expResult = new DefaultSegment(0, 1, 4, 1);
    Segment result = instance.extend2D(seg, distance);
    assertEquals(expResult, result);
  }

}
