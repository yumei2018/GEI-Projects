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

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CoordIndexTest {

  public CoordIndexTest() {
  }

  /**
   * Test of getCoord method, of class CoordIndex.
   */
  @Test
  public final void testConstructorNullCoord() {
    System.out.println("Constructor");
    boolean thrown = false;
    try {
      CoordIndex instance = new CoordIndex(null, 1);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    boolean expResult = true;
    assertEquals(expResult, thrown);
  }

  /**
   * Test of getCoord method, of class CoordIndex.
   */
  @Test
  public final void testGetCoord() {
    System.out.println("getCoord");
    Coord a = new CoordXY(5, 5);
    CoordIndex instance = new CoordIndex(a, 1);
    Coord expResult = a;
    Coord result = instance.getCoord();
    assertEquals(expResult, result);
  }

  /**
   * Test of setCoord method, of class CoordIndex.
   */
  @Test
  public final void testSetCoord() {
    System.out.println("setCoord");
    Coord coord = new CoordZ(5, 5, 5);
    CoordIndex instance = new CoordIndex(new CoordZ(), 3);
    instance.setCoord(coord);
    Coord expResult = coord;
    Coord result = instance.getCoord();
    assertEquals(expResult, result);
  }

  /**
   * Test of setCoord method, of class CoordIndex.
   */
  @Test
  public final void testSetCoordNull() {
    System.out.println("setCoord");
    Coord coord = null;
    CoordIndex instance = new CoordIndex(new CoordZ(), 3);
    boolean thrown = false;
    try {
      instance.setCoord(coord);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    boolean expResult = true;
    assertEquals(expResult, thrown);
  }

  /**
   * Test of getIndex method, of class CoordIndex.
   */
  @Test
  public final void testGetIndex() {
    System.out.println("getIndex");
    CoordIndex instance = new CoordIndex(new CoordZ(), 4);
    int expResult = 4;
    int result = instance.getIndex();
    assertEquals(expResult, result);
  }

  /**
   * Test of setIndex method, of class CoordIndex.
   */
  @Test
  public final void testSetIndex() {
    System.out.println("setIndex");
    int index = 5;
    CoordIndex instance = new CoordIndex(new CoordZ(), 3);
    instance.setIndex(index);
    int result = instance.getIndex();
    int expResult = 5;
    assertEquals(expResult, result);
  }

}
