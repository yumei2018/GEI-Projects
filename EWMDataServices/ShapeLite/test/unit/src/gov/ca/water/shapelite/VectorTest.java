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
import gov.ca.water.shapelite.coordinate.CoordZ;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class VectorTest {

  public VectorTest() {
  }

  /**
   * Test of cross method, of class Vector.
   */
  @Test
  public void testCross() {
    System.out.println("cross");
    Vector v = new Vector(0,1);
    Vector instance = new Vector(1,0);
    Vector expResult = new Vector(0,0,1);
    Vector result = instance.cross(v);
    Assert.assertEquals(expResult, result);
  }

  /**
   * Test of dot method, of class Vector.
   */
  @Test
  public void testDot() {
    System.out.println("dot");
    Vector v = new Vector(2,2);
    Vector instance = new Vector(3,4);
    double expResult = 14;
    double result = instance.dot(v);
    Assert.assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of multiply method, of class Vector.
   */
  @Test
  public void testMultiply() {
    System.out.println("multiply");
    double scalar = 3.0;
    Vector instance = new Vector(1,2);
    Vector expResultVector = new Vector(3,6);
    Vector resultVector = instance.multiply(scalar);
    boolean expResult = true;
    boolean result = expResultVector.equals(resultVector);
    Assert.assertEquals(expResult, result);
  }

  /**
   * Test of add method, of class Vector.
   */
  @Test
  public void testAdd() {
    System.out.println("add");
    Vector v = new Vector(1, 2);
    Vector instance = new Vector(3, 4);
    Vector expResultVector = new Vector(4, 6);
    Vector resultVector = instance.add(v);
    boolean expResult = true;
    boolean result = resultVector.equals(expResultVector);
    assertEquals(expResult, result);
  }

  /**
   * Test of subtract method, of class Vector.
   */
  @Test
  public void testSubtract() {
    System.out.println("subtract");
    Vector v = new Vector(1, 2);
    Vector instance = new Vector(3, 4);
    Vector expResultVector = new Vector(2, 2);
    Vector resultVector = instance.subtract(v);
    boolean expResult = true;
    boolean result = resultVector.equals(expResultVector);
    assertEquals(expResult, result);
  }

  /**
   * Test of length method, of class Vector.
   */
  @Test
  public void testLength() {
    System.out.println("length");
    Vector instance = new Vector(3, 4);
    double expResult = 5.0;
    double result = instance.length();
    Assert.assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of setLength method, of class Vector.
   */
  @Test
  public void testSetLength() {
    System.out.println("setLength");
    Vector instance = new Vector(2.24452, -123.131123);
    double lengthgoal = 34.12312412;
    instance.setLength(lengthgoal);
    double l = instance.length();
    System.out.println("diff: " + Double.toString(l - lengthgoal));
    if ((l - lengthgoal) > .00000001) {
      Assert.fail("The length set did not match the target.");
    }
  }

  /**
   * Test of toCoord method, of class Vector.
   */
  @Test
  public void testToCoord2D() {
    System.out.println("toCoord - 2D");
    Vector instance = new Vector(1,2);
    Coord resultCoord = instance.toCoord();
    Coord expResultCoord = new CoordXY(1,2);
    boolean expResult = true;
    boolean result = resultCoord.equals(expResultCoord);
    assertEquals(expResult, result);
    
  }
  /**
   * Test of toCoord method, of class Vector.
   */
  @Test
  public void testToCoord3D() {
    System.out.println("toCoord - 3D");
    Vector instance = new Vector(1,2,3);
    Coord resultCoord = instance.toCoord();
    Coord expResultCoord = new CoordZ(1,2,3);
    boolean expResult = true;
    boolean result = resultCoord.equals(expResultCoord);
    assertEquals(expResult, result);
    
  }

  /**
   * Test of copy method, of class Vector.
   */
  @Test
  public void testCopy() {
    System.out.println("copy");
    Vector instance = new Vector(3, 4);
    Vector expResultVector = new Vector(3, 4);
    Vector resultVector = instance.copy();
    boolean expResult = true;
    boolean result = resultVector.equals(expResultVector);
    // Resulting vectors should be equivalent and pass vector equals.
    assertEquals(expResult, result);
    expResult = false;
    // Resulting vector object should not be the same object.
    result = resultVector == instance;
    assertEquals(expResult, result);
  }
}
