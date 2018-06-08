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

import gov.ca.water.shapelite.coordinate.CoordXY;
import com.vividsolutions.jts.geom.Coordinate;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CoordXYTest {

  /**
   * Test of copy method, of class CoordXY.
   */
  @Test
  public final void testCopy() {
    System.out.println("copy");
    CoordXY instance = new CoordXY(1, 2);
    CoordXY expResult = new CoordXY(1, 2);
    CoordXY result = instance.copy();
    assertEquals(expResult, result);
    if (result == instance) {
      fail("Should not be the same object.");
    }

  }

  /**
   * Test of copyProperties method, of class CoordXY.
   */
  @Test
  public final void testCopyProperties() {
    System.out.println("copyProperties");
    Coord other = new CoordXY(1, 2);
    CoordXY instance = new CoordXY();
    instance.copyProperties(other);
    assertEquals(instance.getX(), other.getX(), Double.MIN_VALUE);
    assertEquals(instance.getY(), other.getY(), Double.MIN_VALUE);
  }

  /**
   * Test of distance method, of class CoordXY.
   */
  @Test
  public final void testDistance() {
    System.out.println("distance");
    Coord other = null;
    CoordXY instance = new CoordXY(0, 0);
    double expResult = 0.0;
    Coord test = new CoordXY(3, 4);
    double result = instance.distance(test);
    assertEquals(expResult, result, 5);
  }

  /**
   * Test of intersects method, of class CoordXY.
   */
  @Test
  public final void testIntersectsSame() {
    System.out.println("intersects");
    Coord other = new CoordXY(1, 2);
    CoordXY instance = new CoordXY(1, 2);
    boolean expResult = true;
    boolean result = instance.intersects(other);
    assertEquals(expResult, result);
  }

  /**
   * Test of intersects method, of class CoordXY.
   */
  @Test
  public final void testIntersectsDifferentX() {
    System.out.println("intersects");
    Coord other = new CoordXY(1, 2);
    CoordXY instance = new CoordXY(2, 2);
    boolean expResult = false;
    boolean result = instance.intersects(other);
    assertEquals(expResult, result);
  }

  /**
   * Test of intersects method, of class CoordXY.
   */
  @Test
  public final void testIntersectsDifferentY() {
    System.out.println("intersects");
    Coord other = new CoordXY(1, 2);
    CoordXY instance = new CoordXY(1, 3);
    boolean expResult = false;
    boolean result = instance.intersects(other);
    assertEquals(expResult, result);
  }

  /**
   * Test of equals method, of class CoordXY.
   */
  @Test
  public final void testEquals() {
    System.out.println("equals");
    CoordXY instance = new CoordXY(1, 2);
    CoordXY diff = new CoordXY(3, 4);
    CoordXY same = new CoordXY(1, 2);
    if (instance.equals(diff)) {
      fail("The coordinates should have been different.");
    }
    if (!instance.equals(same)) {
      fail("The hash codes should have been the same.");
    }
  }

  /**
   * Test of hashCode method, of class CoordXY.
   */
  @Test
  public final void testHashCode() {
    System.out.println("hashCode");
    CoordXY instance = new CoordXY(1, 2);
    CoordXY diff = new CoordXY(3, 4);
    CoordXY same = new CoordXY(1, 2);
    if (instance.hashCode() == diff.hashCode()) {
      fail("The hash codes should have been different.");
    }
    if (instance.hashCode() != same.hashCode()) {
      fail("The hash codes should have been the same.");
    }
  }

  /**
   * Test of getEpsilon method, of class CoordXY.
   */
  @Test
  public final void testEpsilon() {
    System.out.println("getEpsilon");
    double expResult = 0.0123;
    CoordXY.setEpsilon(expResult);
    double result = CoordXY.getEpsilon();
    assertEquals(expResult, result, 0.0123);
  }

  /**
   * Test of hasM method, of class CoordXY.
   */
  @Test
  public final void testHasM() {
    System.out.println("hasM");
    CoordXY instance = new CoordXY();
    boolean expResult = false;
    boolean result = instance.hasM();
    assertEquals(expResult, result);
  }

  /**
   * Test of hasZ method, of class CoordXY.
   */
  @Test
  public final void testHasZ() {
    System.out.println("hasZ");
    CoordXY instance = new CoordXY();
    boolean expResult = false;
    boolean result = instance.hasZ();
    assertEquals(expResult, result);
  }

  /**
   * Test of toCoordinate method, of class CoordXY.
   */
  @Test
  public final void testToCoordinate() {
    System.out.println("toCoordinate");
    CoordXY instance = new CoordXY(-.7, 1.2);
    Coordinate expResult = new Coordinate(-.7, 1.2);
    Coordinate result = instance.toCoordinate();
    assertEquals(expResult, result);
  }

  




}
