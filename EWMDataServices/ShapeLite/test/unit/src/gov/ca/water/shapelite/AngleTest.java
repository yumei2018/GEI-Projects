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
package gov.ca.water.shapelite;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class AngleTest {

  /**
   * Pi in degrees.
   */
  public static final double PI_DEGREEES = 180;

  /**
   * Test of getDegrees method, of class Angle.
   */
  @Test
  public final void testGetDegrees() {
    System.out.println("getDegrees");
    Angle instance = new Angle(Math.PI);
    double expResult = PI_DEGREEES;
    double result = instance.getDegrees();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of setDegrees method, of class Angle.
   */
  @Test
  public final void testSetDegrees() {
    double degreeAngle = PI_DEGREEES;
    Angle instance = new Angle();
    instance.setDegrees(degreeAngle);
    double expResult = PI_DEGREEES;
    double result = instance.getDegrees();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of getRadians method, of class Angle.
   */
  @Test
  public final void testGetRadians() {
    System.out.println("getRadians");
    Angle instance = new Angle(Math.PI);
    double expResult = Math.PI;
    double result = instance.getRadians();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of setRadians method, of class Angle.
   */
  @Test
  public final void testSetRadians() {
    System.out.println("setRadians");
    double radianAngle = Math.PI;
    Angle instance = new Angle();
    instance.setRadians(radianAngle);
    double expResult = Math.PI;
    double result = instance.getRadians();
    assertEquals(expResult, result, 0.0);
  }

}
