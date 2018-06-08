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
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CoordIndexRemoverTest {

  /**
   * Coordinate A.
   */
  private final CoordZ a = new CoordZ(1, 1, 1);
  /**
   * Coordinate B.
   */
  private final CoordZ b = new CoordZ(2, 2, 2);
  /**
   * Coordinate C.
   */
  private final CoordZ c = new CoordZ(3, 3, 3);
  /**
   * Coordinate D.
   */
  private final CoordZ d = new CoordZ(4, 4, 4);

  /**
   * Gets a standard set of coordinates.
   *
   * @return
   */
  private Part getTestPart() {
    Part result = new Part();
    result.getCoordinates().add(a);
    result.getCoordinates().add(b);
    result.getCoordinates().add(c);
    result.getCoordinates().add(d);
    return result;
  }

  public CoordIndexRemoverTest() {
  }

  /**
   * Test of hasNext method, of class CoordIndexRemover.
   */
  @Test
  public final void testNullConstructor() {
    System.out.println("hasNext");
    boolean thrown = false;
    try {
      CoordIndexRemover instance = new CoordIndexRemover(null);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    boolean expResult = true;
    assertEquals(expResult, thrown);
  }

  /**
   * Test of hasNext method, of class CoordIndexRemover.
   */
  @Test
  public final void testOutOfRangeIndex() {
    System.out.println("OutOfRangeIndex");
    boolean thrown = false;
    Part testPart = getTestPart();
    try {
      CoordIndexRemover instance = new CoordIndexRemover(testPart, 5);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    boolean expResult = true;
    assertEquals(expResult, thrown);
  }

  /**
   * Test of hasNext method, of class CoordIndexRemover.
   */
  @Test
  public final void testOutOfRangeIndex2() {
    System.out.println("OutOfRangeIndex2");
    boolean thrown = false;
    Part testPart = getTestPart();
    try {
      CoordIndexRemover instance = new CoordIndexRemover(testPart, -1);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    boolean expResult = true;
    assertEquals(expResult, thrown);
  }

  /**
   * Test of hasNext method, of class CoordIndexRemover.
   */
  @Test
  public final void testOutOfRangeIndex3() {
    System.out.println("OutOfRangeIndex");
    boolean thrown = false;
    Part testPart = getTestPart();
    try {
      CoordIndexRemover instance = new CoordIndexRemover(testPart, 5,
          IndexDirection.Decreasing);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    boolean expResult = true;
    assertEquals(expResult, thrown);
  }

  /**
   * Test of hasNext method, of class CoordIndexRemover.
   */
  @Test
  public final void testOutOfRangeIndex4() {
    System.out.println("OutOfRangeIndex");
    boolean thrown = false;
    Part testPart = getTestPart();
    try {
      CoordIndexRemover instance = new CoordIndexRemover(testPart, -1,
          IndexDirection.Decreasing);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    boolean expResult = true;
    assertEquals(expResult, thrown);
  }

  /**
   * Test of hasNext method, of class CoordIndexRemover.
   */
  @Test
  public final void testHasNextLinearAscending() {
    System.out.println("HasNextLinearAscending");
    Part testPart = getTestPart();
    Coord[] expCoords = new Coord[]{a, b, c, d};

    CoordIndexRemover instance = new CoordIndexRemover(testPart);
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
   * Test of hasNext method, of class CoordIndexRemover.
   */
  @Test
  public final void testHasNextLinearDescending() {
    System.out.println("LinearDescending");
    Part testPart = getTestPart();
    Coord[] expCoords = new Coord[]{c, b, a};

    CoordIndexRemover instance = new CoordIndexRemover(testPart, 2,
        IndexDirection.Decreasing);
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
   * Test of hasNext method, of class CoordIndexRemover.
   */
  @Test
  public final void testHasNextClosedAscending() {
    System.out.println("HasNextClosedAscending");
    Part testPart = getTestPart();
    testPart.setClosed(true);
    Coord[] expCoords = new Coord[]{c, b, d, a};

    CoordIndexRemover instance = new CoordIndexRemover(testPart,
        2, IndexDirection.Increasing);
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
   * Test of hasNext method, of class CoordIndexRemover.
   */
  @Test
  public final void testHasNextClosedAscending2() {
    System.out.println("HasNextClosedAscending2");
    Part testPart = getTestPart();
    testPart.setClosed(true);
    Coord[] expCoords = new Coord[]{c, b, d, a};

    CoordIndexRemover instance = new CoordIndexRemover(testPart,
        2);
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
   * Test of hasNext method, of class CoordIndexRemover.
   */
  @Test
  public final void testHasNextClosedDescending() {
    System.out.println("HasNextClosedDescending");
    Part testPart = getTestPart();
    testPart.setClosed(true);
    Coord[] expCoords = new Coord[]{c, b, a, d};

    CoordIndexRemover instance = new CoordIndexRemover(testPart,
        2, IndexDirection.Increasing);
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
   * Test of hasNext method, of class CoordIndexRemover.
   */
  @Test
  public final void testNextLinearAscending() {
    System.out.println("NextLinearAscending");
    Part testPart = getTestPart();
    Coord[] expCoords = new Coord[]{a, b, c, d};
    int[] indices = new int[]{0, 1, 2, 3};

    CoordIndexRemover instance = new CoordIndexRemover(testPart);
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
   * Test of hasNext method, of class CoordIndexRemover.
   */
  @Test
  public final void testNextLinearDescending() {
    System.out.println("NextLinearDescending");
    Part testPart = getTestPart();
    Coord[] expCoords = new Coord[]{c, b, a};
    int[] indices = new int[]{2, 1, 0};
    CoordIndexRemover instance = new CoordIndexRemover(testPart, 2,
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
   * Test of hasNext method, of class CoordIndexRemover.
   */
  @Test
  public final void testNextClosedAscending() {
    System.out.println("NextClosedAscending");

    Part testPart = getTestPart();
    testPart.setClosed(true);
    Coord[] expCoords = new Coord[]{c, d, a, b};
    int[] indices = new int[]{2, 3, 0, 1};
    CoordIndexRemover instance = new CoordIndexRemover(testPart,
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
   * Test of hasNext method, of class CoordIndexRemover.
   */
  @Test
  public final void testNextClosedDescending() {
    System.out.println("NextClosedDescending");
    Part testPart = getTestPart();
    testPart.setClosed(true);
    Coord[] expCoords = new Coord[]{a, d, c, b};
    int[] indices = new int[]{0, 3, 2, 1};
    CoordIndexRemover instance = new CoordIndexRemover(testPart, 0,
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
   * Test of getPart method, of class CoordIndexRemover.
   */
  @Test
  public final void testGetPart() {
    Part testPart = getTestPart();
    CoordIndexRemover instance = new CoordIndexRemover(testPart);
    Part expPart = testPart;
    Part result = instance.getPart();
    assertEquals(expPart, result);
  }

  /**
   * Test of getCurrent method, of class CoordIndexRemover.
   */
  @Test
  public final void testGetCurrent() {
    System.out.println("hasNext");
    Part testPart = getTestPart();
    Coord[] expCoords = new Coord[]{a, b, c, d};
    int[] indices = new int[]{0, 1, 2, 3};

    CoordIndexRemover instance = new CoordIndexRemover(testPart);

    for (int i = 0; i < expCoords.length; i++) {
      CoordIndex expResult = instance.next();
      assertEquals(instance.getCurrent(), expResult);
      assertEquals(instance.getCurrent().getIndex(), indices[i]);
      assertEquals(instance.getCurrent().getCoord(), expCoords[i]);
    }
  }

  /**
   * Test of getStartIndex method, of class CoordIndexRemover.
   */
  @Test
  public final void testGetStartIndex() {
    System.out.println("getStartIndex");

    Part testPart = getTestPart();
    CoordIndexRemover instance = new CoordIndexRemover(testPart, 3,
        IndexDirection.Decreasing);

    int expResult = 3;
    assertEquals(expResult, instance.getStartIndex());
  }

  /**
   * Test of getDirection method, of class CoordIndexRemover.
   */
  @Test
  public final void testGetDirection() {
    System.out.println("getDirection");
    Part testPart = getTestPart();
    CoordIndexRemover instance = new CoordIndexRemover(testPart, 0,
        IndexDirection.Decreasing);
    IndexDirection expResult = IndexDirection.Decreasing;
    assertEquals(expResult, instance.getDirection());
  }

  /**
   * Test of getCount method, of class CoordIndexRemover.
   */
  @Test
  public final void testGetCount() {
    System.out.println("getCount");
    Part testPart = getTestPart();
    testPart.setClosed(true);
    Coord[] expCoords = new Coord[]{c, d, a, b};
    int[] indices = new int[]{2, 3, 0, 1};
    CoordIndexRemover instance = new CoordIndexRemover(testPart,
        2, IndexDirection.Increasing);
    for (int i = 0; i < expCoords.length; i++) {
      assertEquals(i, instance.getCount());
      CoordIndex item = instance.next();
      assertEquals(item.getCoord(), expCoords[i]);
      assertEquals(item.getIndex(), indices[i]);
    }
    assertEquals(indices.length, instance.getCount());
  }

  @Test
  public final void testSetIndex() {
    System.out.println("SetIndex");
    Part testPart = getTestPart();
    testPart.setClosed(true);
    Coord[] expCoords = new Coord[]{c, d, a, b};
    int[] indices = new int[]{2, 3, 0, 1};
    CoordIndexRemover instance = new CoordIndexRemover(testPart,
        0, IndexDirection.Increasing);
    instance.setNextIndex(2);
    for (int i = 0; i < expCoords.length; i++) {
      assertEquals(i, instance.getCount());
      CoordIndex item = instance.next();
      assertEquals(item.getCoord(), expCoords[i]);
      assertEquals(item.getIndex(), indices[i]);
    }
    assertEquals(indices.length, instance.getCount());
  }

  /**
   * This tests the case when the index has already been started, and we have to
   * remove the "current" index, but then only assign startIndex so that we have
   * to actually trigger "next" again to get the actual next coordinate.
   */
  @Test
  public final void testSetIndexStarted() {
    System.out.println("SetIndex - Started");
    Part testPart = getTestPart();
    testPart.setClosed(true);
    Coord[] expCoords = new Coord[]{a, c, d};
    int[] indices = new int[]{0, 2, 3};
    CoordIndexRemover instance = new CoordIndexRemover(testPart,
        0, IndexDirection.Increasing);

    for (int i = 0; i < expCoords.length; i++) {
      assertEquals(i, instance.getCount());
      CoordIndex item = instance.next();
      assertEquals(item.getCoord(), expCoords[i]);
      assertEquals(item.getIndex(), indices[i]);
      if (i == 0) {
        instance.setNextIndex(2); // skip b and go directly to c
      }
    }
    assertEquals(indices.length, instance.getCount());
  }

  @Test
  public final void testSetIndexRemoved() {
    System.out.println("SetIndex - Removed");
    Part testPart = getTestPart();
    testPart.setClosed(true);
    CoordIndexRemover instance = new CoordIndexRemover(testPart,
        0, IndexDirection.Increasing);
    instance.next();
    boolean expResult = true;
    boolean thrown = false;
    try {
      instance.setNextIndex(0);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    assertEquals(expResult, thrown);

  }

  @Test
  public final void testSetIndexBadRange() {
    System.out.println("SetIndex - BadRange");
    Part testPart = getTestPart();
    testPart.setClosed(true);
    CoordIndexRemover instance = new CoordIndexRemover(testPart,
        0, IndexDirection.Increasing);
    instance.next();
    boolean expResult = true;
    boolean thrown = false;
    try {
      instance.setNextIndex(-1);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    try {
      instance.setNextIndex(5);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    assertEquals(expResult, thrown);

  }

  /**
   * Tests setting the direction afterwards.
   */
  @Test
  public final void testSetDirection() {
    System.out.println("SetDirection");
    Part testPart = getTestPart();
    testPart.setClosed(true);
    Coord[] expCoords = new Coord[]{c, b, a, d};
    int[] indices = new int[]{2, 1, 0, 3};
    CoordIndexRemover instance = new CoordIndexRemover(testPart,
        0, IndexDirection.Increasing);
    instance.setNextIndex(2);
    instance.setDirection(IndexDirection.Decreasing);
    for (int i = 0; i < expCoords.length; i++) {
      assertEquals(i, instance.getCount());
      CoordIndex item = instance.next();
      assertEquals(item.getCoord(), expCoords[i]);
      assertEquals(item.getIndex(), indices[i]);
    }
    assertEquals(indices.length, instance.getCount());
    boolean expResult = true;
    boolean thrown = false;
    try {
      instance.next();
    } catch (IllegalStateException ex) {
      thrown = true;
    }
    assertEquals(expResult, thrown);
  }

  @Test
  public final void testGetRemainder() {
    System.out.println("GetRemainder");
    Part testPart = getTestPart();
    testPart.setClosed(true);
    Coord[] expCoords = new Coord[]{b, c, d};
    CoordIndexRemover instance = new CoordIndexRemover(testPart,
        0, IndexDirection.Increasing);
    instance.next();
    Part remainder = instance.getRemainder().get();
    boolean expResult = true;
    for (int i = 0; i < expCoords.length; i++) {
      boolean equal = expCoords[i].equals(remainder.getCoordinates().get(i));
      assertEquals(expResult, equal);
    }
  }

  /**
   * Tests setting the direction afterwards.
   */
  @Test
  public final void testEmptyPart() {
    System.out.println("Test Empty Part");
    Part testPart = new Part();
    testPart.setClosed(true);
    boolean expResult = false;
    CoordIndexRemover instance = new CoordIndexRemover(testPart,
        0, IndexDirection.Increasing);
    assertEquals(expResult, instance.hasNext());
    assertEquals(expResult, instance.hasNext(1));
    expResult = true;
    boolean thrown = false;
    try {
      CoordIndex item = instance.peekNext();
    } catch (IllegalStateException ex) {
      thrown = true;
    }
    assertEquals(expResult, thrown);
    try {
      CoordIndex item = instance.next();
    } catch (IllegalStateException ex) {
      thrown = true;
    }
    assertEquals(expResult, thrown);
  }

  @Test
  public final void testPeekNextNoneNext() {
    System.out.println("GetNextNoneNext");
    Part testPart = getTestPart();
    testPart.setClosed(true);
    CoordIndexRemover instance = new CoordIndexRemover(testPart,
        0, IndexDirection.Increasing);
    instance.next();
    instance.next();
    instance.next();
    instance.next();
    boolean expResult = true;
    boolean thrown = false;
    try {
      instance.peekNext();
    } catch (IllegalStateException ex) {
      thrown = true;
    }
    assertEquals(expResult, thrown);

  }

  @Test
  public final void testGetRemoved(){
    System.out.println("GetRemoved");
    Part testPart = getTestPart();
    testPart.setClosed(true);
    CoordIndexRemover instance = new CoordIndexRemover(testPart,
        0, IndexDirection.Increasing);
    instance.next();
    boolean expResult = true;
    boolean result = instance.getRemoved().contains(0);
    assertEquals(expResult, result);
    int expSize = 1;
    assertEquals(expSize, instance.getRemoved().size());
  }

}
