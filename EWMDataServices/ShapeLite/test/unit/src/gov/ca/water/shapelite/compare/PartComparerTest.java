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
package gov.ca.water.shapelite.compare;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.coordinate.CoordXY;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PartComparerTest {

  public PartComparerTest() {
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartNull() {
    System.out.println("equivalent Nulls");
    Part a = null;
    Part b = null;
    PartComparer instance = new PartComparer();
    boolean expResult = true;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartFirstNull() {
    System.out.println("equivalent a null");
    Part a = null;
    Part b = new Part();
    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartSecondNull() {
    System.out.println("equivalent b null");
    Part a = new Part();
    Part b = null;
    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartEmpty() {
    System.out.println("equivalent empty");
    Part a = new Part();
    Part b = new Part();
    PartComparer instance = new PartComparer();
    boolean expResult = true;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartFirstEmpty() {
    System.out.println("equivalent a empty");
    Coord x = new CoordXY(10, 10);
    Part a = new Part();
    Part b = new Part();
    b.getCoordinates().add(x);
    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartSecondEmpty() {
    System.out.println("equivalent b empty");
    Coord x = new CoordXY(10, 10);
    Part a = new Part();
    a.getCoordinates().add(x);
    Part b = new Part();
    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartSizeOneSame() {
    System.out.println("equivalent part size one same");
    Coord x = new CoordXY(10, 10);
    Part a = new Part();
    a.getCoordinates().add(x);
    Part b = new Part();
    b.getCoordinates().add(x.copy());
    PartComparer instance = new PartComparer();
    boolean expResult = true;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartSizeOneDiff() {
    System.out.println("equivalent part size one different");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Part a = new Part();
    a.getCoordinates().add(x);
    Part b = new Part();
    b.getCoordinates().add(y);
    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartSizeOneTwo() {
    System.out.println("equivalent part size one different");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Part a = new Part();
    a.getCoordinates().add(x);
    Part b = new Part();
    b.getCoordinates().add(x);
    b.getCoordinates().add(y);
    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartSizeTwoOne() {
    System.out.println("equivalent part size one different");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Part a = new Part();
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    Part b = new Part();
    b.getCoordinates().add(x);
    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartOpenSameOrder() {
    System.out.println("equivalent part oen same order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    Part b = new Part();
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(y.copy());
    b.getCoordinates().add(z.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = true;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartOpenReverseOrder() {
    System.out.println("equivalent part open reverse order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    Part b = new Part();
    b.getCoordinates().add(z.copy());
    b.getCoordinates().add(y.copy());
    b.getCoordinates().add(x.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartOpenCycledOrder() {
    System.out.println("equivalent closed cycled order");
    Coord w = new CoordXY(5, 10);
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.getCoordinates().add(w);
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    Part b = new Part(); // ordered in to require cycling to be equal.
    b.getCoordinates().add(z.copy());
    b.getCoordinates().add(w.copy());
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(y.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartOpenCycledReversed() {
    System.out.println("equivalent closed cycled order");
    Coord w = new CoordXY(5, 10);
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.getCoordinates().add(w);
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    Part b = new Part(); // ordered in to require cycling to be equal.
    b.getCoordinates().add(w.copy());
    b.getCoordinates().add(z.copy());
    b.getCoordinates().add(y.copy());
    b.getCoordinates().add(x.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartOpenWrongOrder() {
    System.out.println("equivalent open wrong order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    Part b = new Part();
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(z.copy());
    b.getCoordinates().add(y.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartOpenWrongSize() {
    System.out.println("equivalent open wrong order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    Part b = new Part();
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(z.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartOpenWrongCoord() {
    System.out.println("equivalent open wrong order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);
    Coord y2 = new CoordXY(20, 11);

    Part a = new Part();
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    Part b = new Part();
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(y2.copy());
    b.getCoordinates().add(z.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartClosedSameOrder() {
    System.out.println("equivalent part closed same order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.setClosed(true);
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    Part b = new Part();
    b.setClosed(true);
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(y.copy());
    b.getCoordinates().add(z.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = true;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartClosedReverseOrder() {
    System.out.println("equivalent part closed reverse order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.setClosed(true);
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    Part b = new Part();
    b.setClosed(true);
    b.getCoordinates().add(z.copy());
    b.getCoordinates().add(y.copy());
    b.getCoordinates().add(x.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartClosedCycledOrder() {
    System.out.println("equivalent closed cycled order");
    Coord w = new CoordXY(5, 10);
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.setClosed(true);
    a.getCoordinates().add(w);
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    Part b = new Part();
    b.setClosed(true); // ordered in to require cycling to be equal.
    b.getCoordinates().add(z.copy());
    b.getCoordinates().add(w.copy());
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(y.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartClosedCycledReversed() {
    System.out.println("equivalent closed cycled order");
    Coord w = new CoordXY(5, 10);
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.setClosed(true);
    a.getCoordinates().add(w);
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    Part b = new Part();
    b.setClosed(true); // ordered in to require cycling to be equal.
    b.getCoordinates().add(w.copy());
    b.getCoordinates().add(z.copy());
    b.getCoordinates().add(y.copy());
    b.getCoordinates().add(x.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartClosedWrongOrder() {
    System.out.println("equivalent closed wrong order");
    Coord w = new CoordXY(5, 10);
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.setClosed(true);
    a.getCoordinates().add(w);
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    Part b = new Part();
    b.setClosed(true); // ordered in a way cannot be cycled or reversed to equal.
    b.getCoordinates().add(w.copy());
    b.getCoordinates().add(z.copy());
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(y.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartClosedWrongCoord() {
    System.out.println("equivalent closed wrong order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);
    Coord y2 = new CoordXY(20, 11);

    Part a = new Part();
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    Part b = new Part();
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(y2.copy());
    b.getCoordinates().add(z.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartFirstOpenCloseableSameOrder() {
    System.out.println("equivalent part first closeable same order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    a.getCoordinates().add(x);
    Part b = new Part();
    b.setClosed(true);
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(y.copy());
    b.getCoordinates().add(z.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartFirstOpenCloseableReverseOrder() {
    System.out.println("equivalent part first closeable reverse order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    a.getCoordinates().add(x);
    Part b = new Part();
    b.setClosed(true);
    b.getCoordinates().add(z.copy());
    b.getCoordinates().add(y.copy());
    b.getCoordinates().add(x.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartFirstOpenCloseableCycledOrder() {
    System.out.println("equivalent first part closeable cycled order");
    Coord w = new CoordXY(5, 10);
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.getCoordinates().add(w);
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    a.getCoordinates().add(w);
    Part b = new Part(); // ordered in to require cycling to be equal.
    b.setClosed(true);
    b.getCoordinates().add(z.copy());
    b.getCoordinates().add(w.copy());
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(y.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartFirstOpenCloseableCycledReversed() {
    System.out.println("equivalent first part closeable cycled order");
    Coord w = new CoordXY(5, 10);
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.getCoordinates().add(w);
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    a.getCoordinates().add(w);
    Part b = new Part(); // ordered in to require cycling to be equal.
    b.setClosed(true);
    b.getCoordinates().add(w.copy());
    b.getCoordinates().add(z.copy());
    b.getCoordinates().add(y.copy());
    b.getCoordinates().add(x.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartFirstOpenCloseableWrongOrder() {
    System.out.println("equivalent part first closeable wrong order");
    Coord w = new CoordXY(5, 5);
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.getCoordinates().add(w);
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    a.getCoordinates().add(w);
    Part b = new Part();
    b.setClosed(true);
    b.getCoordinates().add(w.copy());
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(z.copy());
    b.getCoordinates().add(y.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartFirstOpenCloseableWrongSize() {
    System.out.println("equivalent part first closeable wrong order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    a.getCoordinates().add(x);
    Part b = new Part();
    b.setClosed(true);
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(z.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartFirstOpenCloseableWrongCoord() {
    System.out.println("equivalent first closeable wrong order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);
    Coord y2 = new CoordXY(20, 11);

    Part a = new Part();
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    a.getCoordinates().add(x);
    Part b = new Part();
    b.setClosed(true);
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(y2.copy());
    b.getCoordinates().add(z.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartFirstOpenSameOrder() {
    System.out.println("equivalent part first open same order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    Part b = new Part();
    b.setClosed(true);
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(y.copy());
    b.getCoordinates().add(z.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartSecondOpenSameOrder() {
    System.out.println("equivalent part second open same order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.setClosed(true);
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);

    Part b = new Part();
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(y.copy());
    b.getCoordinates().add(z.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartSecondCloseableSameOrder() {
    System.out.println("equivalent part second closeable same order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.setClosed(true);
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    Part b = new Part();
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(y.copy());
    b.getCoordinates().add(z.copy());
    b.getCoordinates().add(x.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartSecondCloseableReverseOrder() {
    System.out.println("equivalent part second closeable reverse order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.setClosed(true);
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    Part b = new Part();
    b.getCoordinates().add(z.copy());
    b.getCoordinates().add(y.copy());
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(z.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartSecondCloseableCycledOrder() {
    System.out.println("equivalent part second closeable cycled order");
    Coord w = new CoordXY(5, 10);
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.setClosed(true);
    a.getCoordinates().add(w);
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    a.setClosed(true);
    Part b = new Part(); // ordered in to require cycling to be equal.
    b.getCoordinates().add(z.copy());
    b.getCoordinates().add(w.copy());
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(y.copy());
    b.getCoordinates().add(z.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartSecondCloseableCycledReversed() {
    System.out.println("equivalent part second closeable cycled order");
    Coord w = new CoordXY(5, 10);
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.setClosed(true);
    a.getCoordinates().add(w);
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    Part b = new Part(); // ordered in to require cycling to be equal.
    b.getCoordinates().add(w.copy());
    b.getCoordinates().add(z.copy());
    b.getCoordinates().add(y.copy());
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(w.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartSecondCloseableWrongOrder() {
    System.out.println("equivalent part second closeable wrong order");
    Coord w = new CoordXY(5, 10);
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.setClosed(true);
    a.getCoordinates().add(w);
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    a.setClosed(true);
    Part b = new Part();
    b.getCoordinates().add(w.copy());
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(z.copy());
    b.getCoordinates().add(y.copy());
    b.getCoordinates().add(w.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartSecondCloseableWrongSize() {
    System.out.println("equivalent open wrong order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    Part a = new Part();
    a.setClosed(true);
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    a.setClosed(true);
    Part b = new Part();
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(z.copy());
    b.getCoordinates().add(x.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class PartComparer.
   */
  @Test
  public final void testEquivalentPartSecondCloseableWrongCoord() {
    System.out.println("equivalent open wrong order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);
    Coord y2 = new CoordXY(20, 11);

    Part a = new Part();
    a.setClosed(true);
    a.getCoordinates().add(x);
    a.getCoordinates().add(y);
    a.getCoordinates().add(z);
    a.setClosed(true);
    Part b = new Part();
    b.getCoordinates().add(x.copy());
    b.getCoordinates().add(y2.copy());
    b.getCoordinates().add(z.copy());
    b.getCoordinates().add(x.copy());

    PartComparer instance = new PartComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

}
