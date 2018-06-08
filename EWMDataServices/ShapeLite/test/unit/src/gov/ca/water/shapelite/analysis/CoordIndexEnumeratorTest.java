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
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.coordinate.CoordZ;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CoordIndexEnumeratorTest {

  public CoordIndexEnumeratorTest() {
  }

  /**
   * Test of hasNext method, of class CoordIndexEnumerator.
   */
  @Test
  public final void testNullConstructor() {
    System.out.println("hasNext");
    boolean thrown = false;
    try {
      CoordIndexEnumerator instance = new CoordIndexEnumerator(null);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    boolean expResult = true;
    assertEquals(expResult, thrown);
  }

  /**
   * Test of hasNext method, of class CoordIndexEnumerator.
   */
  @Test
  public final void testOutOfRangeIndex() {
    System.out.println("OutOfRangeIndex");
    boolean thrown = false;
    Part testPart = new Part();
    CoordZ a = new CoordZ();
    CoordZ b = new CoordZ();
    CoordZ c = new CoordZ();
    CoordZ d = new CoordZ();
    testPart.getCoordinates().add(a);
    testPart.getCoordinates().add(b);
    testPart.getCoordinates().add(c);
    testPart.getCoordinates().add(d);
    try {
      CoordIndexEnumerator instance = new CoordIndexEnumerator(testPart, 5);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    boolean expResult = true;
    assertEquals(expResult, thrown);
  }

  /**
   * Test of hasNext method, of class CoordIndexEnumerator.
   */
  @Test
  public final void testOutOfRangeIndex2() {
    System.out.println("OutOfRangeIndex2");
    boolean thrown = false;
    Part testPart = new Part();
    CoordZ a = new CoordZ();
    CoordZ b = new CoordZ();
    CoordZ c = new CoordZ();
    CoordZ d = new CoordZ();
    testPart.getCoordinates().add(a);
    testPart.getCoordinates().add(b);
    testPart.getCoordinates().add(c);
    testPart.getCoordinates().add(d);
    try {
      CoordIndexEnumerator instance = new CoordIndexEnumerator(testPart, -1);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    boolean expResult = true;
    assertEquals(expResult, thrown);
  }

  /**
   * Test of hasNext method, of class CoordIndexEnumerator.
   */
  @Test
  public final void testOutOfRangeIndex3() {
    System.out.println("OutOfRangeIndex");
    boolean thrown = false;
    Part testPart = new Part();
    CoordZ a = new CoordZ();
    CoordZ b = new CoordZ();
    CoordZ c = new CoordZ();
    CoordZ d = new CoordZ();
    testPart.getCoordinates().add(a);
    testPart.getCoordinates().add(b);
    testPart.getCoordinates().add(c);
    testPart.getCoordinates().add(d);
    try {
      CoordIndexEnumerator instance = new CoordIndexEnumerator(testPart, 5,
          IndexDirection.Decreasing);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    boolean expResult = true;
    assertEquals(expResult, thrown);
  }

  /**
   * Test of hasNext method, of class CoordIndexEnumerator.
   */
  @Test
  public final void testOutOfRangeIndex4() {
    System.out.println("OutOfRangeIndex");
    boolean thrown = false;
    Part testPart = new Part();
    CoordZ a = new CoordZ();
    CoordZ b = new CoordZ();
    CoordZ c = new CoordZ();
    CoordZ d = new CoordZ();
    testPart.getCoordinates().add(a);
    testPart.getCoordinates().add(b);
    testPart.getCoordinates().add(c);
    testPart.getCoordinates().add(d);
    try {
      CoordIndexEnumerator instance = new CoordIndexEnumerator(testPart, -1,
          IndexDirection.Decreasing);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    boolean expResult = true;
    assertEquals(expResult, thrown);
  }

  /**
   * Test of hasNext method, of class CoordIndexEnumerator.
   */
  @Test
  public final void testHasNextLinearAscending() {
    System.out.println("hasNext");
    Part testPart = new Part();
    CoordZ a = new CoordZ();
    CoordZ b = new CoordZ();
    CoordZ c = new CoordZ();
    CoordZ d = new CoordZ();
    testPart.getCoordinates().add(a);
    testPart.getCoordinates().add(b);
    testPart.getCoordinates().add(c);
    testPart.getCoordinates().add(d);
    Coord[] expCoords = new Coord[]{a, b, c, d};

    CoordIndexEnumerator instance = new CoordIndexEnumerator(testPart);
    boolean expResult = true;
    for (int i = 0; i < expCoords.length; i++) {
      assertEquals(expResult, instance.hasNext());
      instance.next();
    }
    expResult = false;
    assertEquals(expResult, instance.hasNext());
    boolean thrown = false;
    expResult = true;
    try {
      instance.next();
    } catch (IllegalStateException ex) {
      thrown = true;
    }
    assertEquals(expResult, thrown);
  }

  /**
   * Test of hasNext method, of class CoordIndexEnumerator.
   */
  @Test
  public final void testHasNextLinearDescending() {
    System.out.println("hasNext");
    Part testPart = new Part();
    CoordZ a = new CoordZ();
    CoordZ b = new CoordZ();
    CoordZ c = new CoordZ();
    CoordZ d = new CoordZ();
    testPart.getCoordinates().add(a);
    testPart.getCoordinates().add(b);
    testPart.getCoordinates().add(c);
    testPart.getCoordinates().add(d);
    Coord[] expCoords = new Coord[]{c, b, a};

    CoordIndexEnumerator instance = new CoordIndexEnumerator(testPart, 2,
        IndexDirection.Decreasing);
    boolean expResult = true;
    for (int i = 0; i < expCoords.length; i++) {
      assertEquals(expResult, instance.hasNext());
      instance.next();
    }
    expResult = false;
    assertEquals(expResult, instance.hasNext());
    expResult = true;
    boolean thrown = false;
    try {
      instance.next();
    } catch (IllegalStateException ex) {
      thrown = true;
    }
    assertEquals(expResult, thrown);

  }

  /**
   * Test of hasNext method, of class CoordIndexEnumerator.
   */
  @Test
  public final void testHasNextClosedAscending() {
    System.out.println("hasNext");
    Part testPart = new Part();
    testPart.setClosed(true);
    CoordZ a = new CoordZ();
    CoordZ b = new CoordZ();
    CoordZ c = new CoordZ();
    CoordZ d = new CoordZ();
    testPart.getCoordinates().add(a);
    testPart.getCoordinates().add(b);
    testPart.getCoordinates().add(c);
    testPart.getCoordinates().add(d);
    Coord[] expCoords = new Coord[]{c, b, d, a};

    CoordIndexEnumerator instance = new CoordIndexEnumerator(testPart,
        2, IndexDirection.Increasing);
    boolean expResult = true;
    for (int i = 0; i < expCoords.length; i++) {
      assertEquals(expResult, instance.hasNext());
      instance.next();
    }
    expResult = false;
    assertEquals(expResult, instance.hasNext());
    expResult = true;
    boolean thrown = false;
    try {
      instance.next();
    } catch (IllegalStateException ex) {
      thrown = true;
    }
    assertEquals(expResult, thrown);
  }


  /**
   * Test of hasNext method, of class CoordIndexEnumerator.
   */
  @Test
  public final void testHasNextClosedAscending2() {
    System.out.println("hasNext");
    Part testPart = new Part();
    testPart.setClosed(true);
    CoordZ a = new CoordZ();
    CoordZ b = new CoordZ();
    CoordZ c = new CoordZ();
    CoordZ d = new CoordZ();
    testPart.getCoordinates().add(a);
    testPart.getCoordinates().add(b);
    testPart.getCoordinates().add(c);
    testPart.getCoordinates().add(d);
    Coord[] expCoords = new Coord[]{c, b, d, a};

    CoordIndexEnumerator instance = new CoordIndexEnumerator(testPart,
        2);
    boolean expResult = true;
    for (int i = 0; i < expCoords.length; i++) {
      assertEquals(expResult, instance.hasNext());
      instance.next();
    }
    expResult = false;
    assertEquals(expResult, instance.hasNext());
    expResult = true;
    boolean thrown = false;
    try {
      instance.next();
    } catch (IllegalStateException ex) {
      thrown = true;
    }
    assertEquals(expResult, thrown);
  }

  /**
   * Test of hasNext method, of class CoordIndexEnumerator.
   */
  @Test
  public final void testHasNextClosedDescending() {
    System.out.println("hasNext");
    Part testPart = new Part();
    testPart.setClosed(true);
    CoordZ a = new CoordZ();
    CoordZ b = new CoordZ();
    CoordZ c = new CoordZ();
    CoordZ d = new CoordZ();
    testPart.getCoordinates().add(a);
    testPart.getCoordinates().add(b);
    testPart.getCoordinates().add(c);
    testPart.getCoordinates().add(d);
    Coord[] expCoords = new Coord[]{c, b, a, d};

    CoordIndexEnumerator instance = new CoordIndexEnumerator(testPart,
        2, IndexDirection.Increasing);
    boolean expResult = true;
    for (int i = 0; i < expCoords.length; i++) {
      assertEquals(expResult, instance.hasNext());
      instance.next();
    }
    expResult = false;
    assertEquals(expResult, instance.hasNext());
    expResult = true;
    boolean thrown = true;
    try {
      instance.next();
    } catch (IllegalStateException ex) {
      thrown = true;
    }
    assertEquals(expResult, thrown);
  }

  /**
   * Test of hasNext method, of class CoordIndexEnumerator.
   */
  @Test
  public final void testNextLinearAscending() {
    System.out.println("hasNext");
    Part testPart = new Part();
    CoordZ a = new CoordZ();
    CoordZ b = new CoordZ();
    CoordZ c = new CoordZ();
    CoordZ d = new CoordZ();
    testPart.getCoordinates().add(a);
    testPart.getCoordinates().add(b);
    testPart.getCoordinates().add(c);
    testPart.getCoordinates().add(d);
    Coord[] expCoords = new Coord[]{a, b, c, d};
    int[] indices = new int[]{0, 1, 2, 3};

    CoordIndexEnumerator instance = new CoordIndexEnumerator(testPart);
    boolean expResult = true;
    for (int i = 0; i < expCoords.length; i++) {
      assertEquals(expResult, instance.hasNext());
      CoordIndex item = instance.next();
      assertEquals(item.getCoord(), expCoords[i]);
      assertEquals(item.getIndex(), indices[i]);
    }
    expResult = false;
    assertEquals(expResult, instance.hasNext());
  }

  /**
   * Test of hasNext method, of class CoordIndexEnumerator.
   */
  @Test
  public final void testNextLinearDescending() {
    System.out.println("hasNext");
    Part testPart = new Part();
    CoordZ a = new CoordZ();
    CoordZ b = new CoordZ();
    CoordZ c = new CoordZ();
    CoordZ d = new CoordZ();
    testPart.getCoordinates().add(a);
    testPart.getCoordinates().add(b);
    testPart.getCoordinates().add(c);
    testPart.getCoordinates().add(d);
    Coord[] expCoords = new Coord[]{c, b, a};
    int[] indices = new int[]{2, 1, 0};
    CoordIndexEnumerator instance = new CoordIndexEnumerator(testPart, 2,
        IndexDirection.Decreasing);
    boolean expResult = true;
    for (int i = 0; i < expCoords.length; i++) {
      assertEquals(expResult, instance.hasNext());
      CoordIndex item = instance.next();
      assertEquals(item.getCoord(), expCoords[i]);
      assertEquals(item.getIndex(), indices[i]);
    }
    expResult = false;
    assertEquals(expResult, instance.hasNext());
  }

  /**
   * Test of hasNext method, of class CoordIndexEnumerator.
   */
  @Test
  public final void testNextClosedAscending() {
    System.out.println("hasNext");

    Part testPart = new Part();
    testPart.setClosed(true);
    CoordZ a = new CoordZ();
    CoordZ b = new CoordZ();
    CoordZ c = new CoordZ();
    CoordZ d = new CoordZ();
    testPart.getCoordinates().add(a);
    testPart.getCoordinates().add(b);
    testPart.getCoordinates().add(c);
    testPart.getCoordinates().add(d);
    Coord[] expCoords = new Coord[]{c, b, d, a};
    int[] indices = new int[]{2, 3, 0, 1};
    CoordIndexEnumerator instance = new CoordIndexEnumerator(testPart,
        2, IndexDirection.Increasing);
    boolean expResult = true;
    for (int i = 0; i < expCoords.length; i++) {
      assertEquals(expResult, instance.hasNext());
      CoordIndex item = instance.next();
      assertEquals(item.getCoord(), expCoords[i]);
      assertEquals(item.getIndex(), indices[i]);
    }
    expResult = false;
    assertEquals(expResult, instance.hasNext());
  }

  /**
   * Test of hasNext method, of class CoordIndexEnumerator.
   */
  @Test
  public final void testNextClosedDescending() {
    System.out.println("NextClosedDescending");
    Part testPart = new Part();
    testPart.setClosed(true);
    CoordZ a = new CoordZ();
    CoordZ b = new CoordZ();
    CoordZ c = new CoordZ();
    CoordZ d = new CoordZ();
    testPart.getCoordinates().add(a);
    testPart.getCoordinates().add(b);
    testPart.getCoordinates().add(c);
    testPart.getCoordinates().add(d);
    Coord[] expCoords = new Coord[]{a, d, c, b};
    int[] indices = new int[]{0, 3, 2, 1};
    CoordIndexEnumerator instance = new CoordIndexEnumerator(testPart, 0,
        IndexDirection.Decreasing);
    boolean expResult = true;
    for (int i = 0; i < expCoords.length; i++) {
      assertEquals(expResult, instance.hasNext());
      CoordIndex item = instance.next();
      assertEquals(item.getCoord(), expCoords[i]);
      assertEquals(item.getIndex(), indices[i]);
    }
    expResult = false;
    assertEquals(expResult, instance.hasNext());
  }

  /**
   * Test of getPart method, of class CoordIndexEnumerator.
   */
  @Test
  public final void testGetPart() {
    Part testPart = new Part();
    CoordZ a = new CoordZ();
    CoordZ b = new CoordZ();
    CoordZ c = new CoordZ();
    CoordZ d = new CoordZ();
    testPart.getCoordinates().add(a);
    testPart.getCoordinates().add(b);
    testPart.getCoordinates().add(c);
    testPart.getCoordinates().add(d);
    CoordIndexEnumerator instance = new CoordIndexEnumerator(testPart);
    Part expPart = testPart;
    Part result = instance.getPart();
    assertEquals(expPart, result);
  }

  /**
   * Test of getCurrent method, of class CoordIndexEnumerator.
   */
  @Test
  public final void testGetCurrent() {
    System.out.println("hasNext");
    Part testPart = new Part();
    CoordZ a = new CoordZ();
    CoordZ b = new CoordZ();
    CoordZ c = new CoordZ();
    CoordZ d = new CoordZ();
    testPart.getCoordinates().add(a);
    testPart.getCoordinates().add(b);
    testPart.getCoordinates().add(c);
    testPart.getCoordinates().add(d);
    Coord[] expCoords = new Coord[]{a, b, c, d};
    int[] indices = new int[]{0, 1, 2, 3};

    CoordIndexEnumerator instance = new CoordIndexEnumerator(testPart);

    for (int i = 0; i < expCoords.length; i++) {
      CoordIndex expResult = instance.next();
      assertEquals(instance.getCurrent(), expResult);
      assertEquals(instance.getCurrent().getIndex(), indices[i]);
      assertEquals(instance.getCurrent().getCoord(), expCoords[i]);
    }
  }

  /**
   * Test of getStartIndex method, of class CoordIndexEnumerator.
   */
  @Test
  public final void testGetStartIndex() {
    System.out.println("getStartIndex");

    Part testPart = new Part();
    CoordZ a = new CoordZ();
    CoordZ b = new CoordZ();
    CoordZ c = new CoordZ();
    CoordZ d = new CoordZ();
    testPart.getCoordinates().add(a);
    testPart.getCoordinates().add(b);
    testPart.getCoordinates().add(c);
    testPart.getCoordinates().add(d);
    CoordIndexEnumerator instance = new CoordIndexEnumerator(testPart, 3,
        IndexDirection.Decreasing);

    int expResult = 3;
    assertEquals(expResult, instance.getStartIndex());
  }

  /**
   * Test of getDirection method, of class CoordIndexEnumerator.
   */
  @Test
  public final void testGetDirection() {
    System.out.println("getDirection");
    Part testPart = new Part();
    CoordZ a = new CoordZ();
    CoordZ b = new CoordZ();
    CoordZ c = new CoordZ();
    CoordZ d = new CoordZ();
    testPart.getCoordinates().add(a);
    testPart.getCoordinates().add(b);
    testPart.getCoordinates().add(c);
    testPart.getCoordinates().add(d);
    CoordIndexEnumerator instance = new CoordIndexEnumerator(testPart, 0,
        IndexDirection.Decreasing);
    IndexDirection expResult = IndexDirection.Decreasing;

    assertEquals(expResult, instance.getDirection());
  }

  /**
   * Test of getCount method, of class CoordIndexEnumerator.
   */
  @Test
  public final void testGetCount() {
    System.out.println("getCount");
    Part testPart = new Part();
    testPart.setClosed(true);
    CoordZ a = new CoordZ();
    CoordZ b = new CoordZ();
    CoordZ c = new CoordZ();
    CoordZ d = new CoordZ();
    testPart.getCoordinates().add(a);
    testPart.getCoordinates().add(b);
    testPart.getCoordinates().add(c);
    testPart.getCoordinates().add(d);
    Coord[] expCoords = new Coord[]{c, b, d, a};
    int[] indices = new int[]{2, 3, 0, 1};
    CoordIndexEnumerator instance = new CoordIndexEnumerator(testPart,
        2, IndexDirection.Increasing);

    for (int i = 0; i < expCoords.length; i++) {
      assertEquals(i, instance.getCount());
      CoordIndex item = instance.next();
      assertEquals(item.getCoord(), expCoords[i]);
      assertEquals(item.getIndex(), indices[i]);
    }
    assertEquals(indices.length, instance.getCount());
  }

}
