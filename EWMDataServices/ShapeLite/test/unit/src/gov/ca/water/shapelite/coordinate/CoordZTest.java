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
package gov.ca.water.shapelite.coordinate;

import com.vividsolutions.jts.geom.Coordinate;
import gov.ca.water.shapelite.Coord;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CoordZTest {



  /**
   * Test of copy method, of class CoordZ.
   */
  @Test
  public void testCopy() {
    System.out.println("copy");
    CoordZ instance = new CoordZ(1, 2, 3);
    CoordZ expResultCoord = new CoordZ(1,2,3);
    CoordZ resultCoord = instance.copy();
    boolean expResult = true;
    boolean result = expResultCoord.equals(resultCoord);
    assertEquals(expResult, result);
  }

  /**
   * Test of copyProperties method, of class CoordZ.
   */
  @Test
  public void testCopyProperties() {
    System.out.println("copy");
    CoordZ resultCoord = new CoordZ(0, 0, 0);
    CoordZ expResultCoord = new CoordZ(1,2,3);
    resultCoord.copyProperties(expResultCoord);
    boolean expResult = true;
    boolean result = expResultCoord.equals(resultCoord);
    assertEquals(expResult, result);
  }

  /**
   * Test of distance method, of class CoordZ.
   */
  @Test
  public void testDistance() {
    System.out.println("copy");
    CoordZ instance = new CoordZ(0, 0, 0);
    CoordZ other = new CoordZ(2,2,2);
    double expResult = Math.sqrt(12);
    double result = instance.distance(other);
    assertEquals(expResult, result, 0.0);
  }

 
  /**
   * Test of equals method, of class CoordZ.
   */
  @Test
  public void testEquals() {
    System.out.println("equals");
    CoordZ other = new CoordZ(1,2,3);
    CoordZ instance = new CoordZ(1,2,3);
    boolean expResult = true;
    boolean result = instance.equals(other);
    assertEquals(expResult, result);
  }

  /**
   * Test of hashCode method, of class CoordZ.
   */
  @Test
  public void testHashCode() {
    System.out.println("hashCode");
    CoordZ other = new CoordZ(1,2,3);
    CoordZ instance = new CoordZ(1,2,3);
    int hashcode = instance.hashCode();
    int otherHash = other.hashCode();
    assertEquals(hashcode, otherHash);
  }

  /**
   * Test of intersects method, of class CoordZ.
   */
  @Test
  public void testIntersects_Coord_Y() {
    System.out.println("intersects");
    CoordZ instance = new CoordZ(1,2,3);
    CoordZ other = new CoordZ(1,2,3);
    boolean expResult = true;
    boolean result = instance.intersects(other);
    assertEquals(expResult, result);
  }
  
  /**
   * Test of intersects method, of class CoordZ.
   */
  @Test
  public void testIntersects_Coord_NX() {
    System.out.println("intersects");
    CoordZ instance = new CoordZ(1,2,3);
    CoordZ other = new CoordZ(4,2,3);
    boolean expResult = false;
    boolean result = instance.intersects(other);
    assertEquals(expResult, result);
  }
  
   /**
   * Test of intersects method, of class CoordZ.
   */
  @Test
  public void testIntersects_Coord_NY() {
    System.out.println("intersects");
    CoordZ instance = new CoordZ(1,2,3);
    CoordZ other = new CoordZ(1,3,3);
    boolean expResult = false;
    boolean result = instance.intersects(other);
    assertEquals(expResult, result);
  }
  
   /**
   * Test of intersects method, of class CoordZ.
   */
  @Test
  public void testIntersects_Coord_NZ() {
    System.out.println("intersects");
    CoordZ instance = new CoordZ(1,2,3);
    CoordZ other = new CoordZ(1,2,6);
    boolean expResult = false;
    boolean result = instance.intersects(other);
    assertEquals(expResult, result);
  }

  /**
   * Test of intersects method, of class CoordZ.
   */
  @Test
  public void testIntersects_Coord_double() {
    System.out.println("intersects");
    Coord other = new CoordZ(1,2,3);
    double epsilon = 2;
    CoordZ instance = new CoordZ(2,2,3);
    boolean expResult = true;
    boolean result = instance.intersects(other, epsilon);
    assertEquals(expResult, result);
  }

  /**
   * Test of toCoordinate method, of class CoordZ.
   */
  @Test
  public void testToCoordinate() {
    System.out.println("toCoordinate");
    CoordZ instance = new CoordZ(1,2,3);
    Coordinate expResultCoord = new Coordinate(1,2,3);
    Coordinate resultCoord = instance.toCoordinate();
    boolean expResult = true;
    boolean result = expResultCoord.equals(resultCoord);
    assertEquals(expResult, result);
  }

  /**
   * Test of getZ method, of class CoordZ.
   */
  @Test
  public void testGetZ() {
    System.out.println("getZ");
    CoordZ instance = new CoordZ(1,2,3);
    double expResult = 3.0;
    double result = instance.getZ();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of setZ method, of class CoordZ.
   */
  @Test
  public void testSetZ() {
    System.out.println("setZ");
    double z = 3.0;
    CoordZ instance = new CoordZ(1,2,3);
    instance.setZ(z);
    double expResult = z;
    double result = instance.getZ();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of toString method, of class CoordZ.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    CoordZ instance = new CoordZ(1,2,3);
    String expResult = "(X:" + 1.0 + ", Y:" + 2.0 + ", Z:" + 3.0
        + " M:" + 0.0 + ")";
    String result = instance.toString();
    assertEquals(expResult, result);
  }

  /**
   * Test of hasM method, of class CoordZ.
   */
  @Test
  public void testHasM() {
    System.out.println("hasM");
    CoordZ instance = new CoordZ();
    boolean expResult = true;
    boolean result = instance.hasM();
    assertEquals(expResult, result);
  }

  /**
   * Test of hasZ method, of class CoordZ.
   */
  @Test
  public void testHasZ() {
    System.out.println("hasZ");
    CoordZ instance = new CoordZ();
    boolean expResult = true;
    boolean result = instance.hasZ();
    assertEquals(expResult, result);
  }

  /**
   * Test of getM method, of class CoordZ.
   */
  @Test
  public void testGetM() {
    System.out.println("getM");
    CoordZ instance = new CoordZ(1,2,3,4);
    double expResult = 4.0;
    double result = instance.getM();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of setM method, of class CoordZ.
   */
  @Test
  public void testSetM() {
    System.out.println("setM");
    double m = 4.0;
    CoordZ instance = new CoordZ(1,2,3);
    instance.setM(m);
    double expResult = 4.0;
    double result = instance.getM();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of get method, of class CoordZ.
   */
  @Test
  public void testGet() {
    System.out.println("get");
    CoordZ instance = new CoordZ(1,2,3);
    double expResult = 3.0;
    double result = instance.get(Coord.Index.Z);
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of set method, of class CoordZ.
   */
  @Test
  public void testSet() {
    System.out.println("set");
    int index = Coord.Index.Z;
    double value = 3;
    CoordZ instance = new CoordZ(1,2,0);
    instance.set(index, value);
  }

  /**
   * Test of getSize method, of class CoordZ.
   */
  @Test
  public void testGetSize() {
    System.out.println("getSize");
    CoordZ instance = new CoordZ(1,2,3);
    int expResult = 4;
    int result = instance.getSize();
    assertEquals(expResult, result);
  }

  /**
   * Test of has method, of class CoordZ.
   */
  @Test
  public void testHas() {
    System.out.println("has");
    int index = Coord.Index.Z;
    CoordZ instance = new CoordZ(1,2,3);
    boolean expResult = true;
    boolean result = instance.has(index);
    assertEquals(expResult, result);
  }

}
