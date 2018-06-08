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

import gov.ca.water.shapelite.coordinate.CoordXY;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class BoxTest {

  public BoxTest() {
  }

  /**
   * Test of isEmpty method, of class Envelope.
   */
  @Test
  public void testIsEmpty() {
    System.out.println("isEmpty");
    Envelope instance = new DefaultEnvelope();
    boolean result = instance.isEmpty();

    Assert.assertTrue(result);
  }

  @Test
  public void testConstructor() {
    Envelope b = new DefaultEnvelope();
  }

  @Test
  public void testArrayConstructor() {
    double[] vals = {1.0, 2.0, 3.0, 4.0};
    Envelope b = new DefaultEnvelope(vals);
    double[] test = b.toArray2D();
    for (int i = 0; i < 4; i++) {
      Assert.assertTrue("Array constructor", vals[i] == test[i]);
    }
  }

  @Test
  public void expandFromEmpty() {
    Envelope b = new DefaultEnvelope();
    Envelope init = new DefaultEnvelope(1, 2, 10, 20);
    b.expandToInclude(init);
    Assert.assertTrue("expandFromEmpty", b.equals(init));
  }

  @Test
  public void intersectionBoxTest() {
    Envelope a = new DefaultEnvelope(1, 1, 5, 5);
    Envelope b = new DefaultEnvelope(2, 2, 3, 3);
    Assert.assertTrue(a.intersects(b));
  }

  @Test
  public void intersectionCoordTest() {
    Envelope a = new DefaultEnvelope(1, 1, 5, 5);
    Coord c = new CoordXY(3, 3);
    Coord d = new CoordXY(8, 8);
    Assert.assertTrue(a.intersects(c));
    Assert.assertTrue(!a.intersects(d));
  }

  @Test
  public void copyTest() {
    Envelope a = new DefaultEnvelope(1, 1, 5, 5);
    Envelope b = a.copy();
    Assert.assertTrue(a.equals(b));
  }

}
