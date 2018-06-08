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
package gov.ca.water.shapelite.compare;

import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.coordinate.CoordXY;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class FeatureComparerTest {

  public FeatureComparerTest() {
  }

  /**
   * Test of equivalent method, of class FeatureComparer.
   */
  @Test
  public void testEquivalentPoint() {
    System.out.println("equivalent");
    Feature a = new Feature(new Shape(new CoordXY(1, 2)));
    Feature b = new Feature(new Shape(new CoordXY(1, 2)));
    FeatureComparer instance = new FeatureComparer();
    boolean expResult = true;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class FeatureComparer.
   */
  @Test
  public void testEquivalentAttributes() {
    System.out.println("equivalent");
    Feature a = new Feature(new Shape(new CoordXY(1, 2)));
    Feature b = new Feature(new Shape(new CoordXY(1, 2)));
    FeatureComparer instance = new FeatureComparer();
    a.getAttributes().put("Name", "Smith");
    a.getAttributes().put("Length", "1234");
    b.getAttributes().put("Name", "Smith");
    b.getAttributes().put("Length", "1234");
    boolean expResult = true;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  



}
