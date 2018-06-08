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
package gov.ca.water.shapelite.io.geojson;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DimensionTest {

  /**
   * All the dimensions.
   */
  private static final Dimension[] ALL_DIM = new Dimension[]{
    Dimension.None,
    Dimension.One,
    Dimension.Two,
    Dimension.Three,
    Dimension.Four
  };

  /**
   * Test of values method, of class Dimension.
   */
  @Test
  public void testValues() {
    System.out.println("values");
    Dimension[] expResult = ALL_DIM;
    Dimension[] result = Dimension.values();
    assertArrayEquals(expResult, result);
  }

  /**
   * Test of valueOf method, of class Dimension.
   */
  @Test
  public void testValueOf() {
    System.out.println("valueOf");
    String name = "None";
    Dimension expResult = Dimension.None;
    Dimension result = Dimension.valueOf(name);
    assertEquals(expResult, result);
  }

  /**
   * Test of fromInt method, of class Dimension.
   */
  @Test
  public void testFromIntNone() {
    System.out.println("fromInt None");
    int value = 0;
    Dimension expResult = Dimension.None;
    Dimension result = Dimension.fromInt(value);
    assertEquals(expResult, result);
  }

  /**
   * Test of fromInt method, of class Dimension.
   */
  @Test
  public void testFromInOne() {
    System.out.println("fromInt One");
    int value = 1;
    Dimension expResult = Dimension.One;
    Dimension result = Dimension.fromInt(value);
    assertEquals(expResult, result);
  }

  /**
   * Test of fromInt method, of class Dimension.
   */
  @Test
  public void testFromIntTwo() {
    System.out.println("fromInt Two");
    int value = 2;
    Dimension expResult = Dimension.Two;
    Dimension result = Dimension.fromInt(value);
    assertEquals(expResult, result);
  }

  /**
   * Test of fromInt method, of class Dimension.
   */
  @Test
  public void testFromIntThree() {
    System.out.println("fromInt None");
    int value = 3;
    Dimension expResult = Dimension.Three;
    Dimension result = Dimension.fromInt(value);
    assertEquals(expResult, result);
  }

  /**
   * Test of fromInt method, of class Dimension.
   */
  @Test
  public void testFromIntFour() {
    System.out.println("fromInt Four");
    int value = 4;
    Dimension expResult = Dimension.Four;
    Dimension result = Dimension.fromInt(value);
    assertEquals(expResult, result);
  }

  /**
   * Test of toInt method, of class Dimension.
   */
  @Test
  public void testToInt() {
    System.out.println("toInt");
    Dimension instance = Dimension.None;
    int expResult = 0;
    int result = instance.toInt();
    assertEquals(expResult, result);
  }

}
