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
package gov.ca.water.shapelite.utils;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ArrayUtilsTest {

  public ArrayUtilsTest() {
  }

  /**
   * Test of toArray4 method, of class ArrayUtils.
   */
  @Test
  public void testToArray4() {
    System.out.println("toArray4");
    ArrayList list = null;
    double[][][][] expResult = null;
    double[][][][] result = ArrayUtils.toArray4(list);
    assertArrayEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toArray3 method, of class ArrayUtils.
   */
  @Test
  public void testToArray3() {
    System.out.println("toArray3");
    ArrayList list = null;
    double[][][] expResult = null;
    double[][][] result = ArrayUtils.toArray3(list);
    assertArrayEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toArray2 method, of class ArrayUtils.
   */
  @Test
  public void testToArray2() {
    System.out.println("toArray2");
    ArrayList list = null;
    double[][] expResult = null;
    double[][] result = ArrayUtils.toArray2(list);
    assertArrayEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toArray method, of class ArrayUtils.
   */
  @Test
  public void testToArray() {
    System.out.println("toArray");
    ArrayList items = null;
    double[] expResult = null;
    double[] result = ArrayUtils.toArray(items);
    // assertArrayEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toList method, of class ArrayUtils.
   */
  @Test
  public void testToList_doubleArrArrArrArr() {
    System.out.println("toList");
    double[][][][] values = null;
    ArrayList<ArrayList<ArrayList<ArrayList<Double>>>> expResult = null;
    ArrayList<ArrayList<ArrayList<ArrayList<Double>>>> result = ArrayUtils.toList(values);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toList method, of class ArrayUtils.
   */
  @Test
  public void testToList_doubleArrArrArr() {
    System.out.println("toList");
    double[][][] values = null;
    ArrayList<ArrayList<ArrayList<Double>>> expResult = null;
    ArrayList<ArrayList<ArrayList<Double>>> result = ArrayUtils.toList(values);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toList method, of class ArrayUtils.
   */
  @Test
  public void testToList_doubleArrArr() {
    System.out.println("toList");
    double[][] values = null;
    ArrayList<ArrayList<Double>> expResult = null;
    ArrayList<ArrayList<Double>> result = ArrayUtils.toList(values);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toList method, of class ArrayUtils.
   */
  @Test
  public void testToList_doubleArr() {
    System.out.println("toList");
    double[] values = null;
    ArrayList<Double> expResult = null;
    ArrayList<Double> result = ArrayUtils.toList(values);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of deepCopy method, of class ArrayUtils.
   */
  @Test
  public void testDeepCopy() {
    System.out.println("deepCopy");
    ArrayList values = null;
    ArrayList expResult = null;
    ArrayList result = ArrayUtils.deepCopy(values);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of copy3 method, of class ArrayUtils.
   */
  @Test
  public void testCopy2() {
    System.out.println("copy2");
    double[][] expResult = new double[2][2];
    expResult[0][0] = 111;
    expResult[1][0] = 222;
    expResult[0][1] = 333;
    expResult[1][1] = 444;
    double[][] result = ArrayUtils.copy2(expResult);
    for (int i = 0; i < expResult.length; i++) {
      for (int j = 0; j < expResult[i].length; j++) {
        assertEquals(expResult[i][j], result[i][j], 0);
      }
    }
    boolean stop = true;
  }

  /**
   * Test of copy3 method, of class ArrayUtils.
   */
  @Test
  public void testCopy3() {
    System.out.println("copy3");
    double[][][] expResult = ArrayUtils.random3(10, 20, 30);
    double[][][] result = ArrayUtils.copy3(expResult);
    for (int i = 0; i < expResult.length; i++) {
      for (int j = 0; j < expResult[i].length; j++) {
        for (int k = 0; k < expResult[i][j].length; k++) {
          assertEquals(expResult[i][j][k], result[i][j][k], 0);
        }
      }
    }
  }

  /**
   * Test of copy4 method, of class ArrayUtils.
   */
  @Test
  public void testCopy4() {
    System.out.println("copy4");
    double[][][][] expResult = ArrayUtils.random4(10, 20, 30, 40);
    double[][][][] result = ArrayUtils.copy4(expResult);
    for (int i = 0; i < expResult.length; i++) {
      for (int j = 0; j < expResult[i].length; j++) {
        for (int k = 0; k < expResult[i][j].length; k++) {
          for (int l = 0; l < expResult[i][j][k].length; l++) {
            assertEquals(expResult[i][j][k][l], result[i][j][k][l], 0);
          }
        }
      }
    }
  }

}
