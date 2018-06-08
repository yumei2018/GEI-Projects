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
import gov.ca.water.shapelite.coordinate.CoordXY;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ListComparerTest {

  public ListComparerTest() {
  }

  /**
   * Test of equivalent method, of class ListComparer.
   */
  @Test
  public final void testEquivalentPartNull() {
    System.out.println("equivalent");
    List<Coord> a = null;
    List<Coord> b = null;
    ListComparer instance = new ListComparer();
    boolean expResult = true;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class ListComparer.
   */
  @Test
  public final void testEquivalentPartEmpty() {
    System.out.println("equivalent");
    List<Coord> a = new ArrayList<>();
    List<Coord> b = new ArrayList<>();
    ListComparer instance = new ListComparer();
    boolean expResult = true;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class ListComparer.
   */
  @Test
  public final void testEquivalentListFirstNull() {
    System.out.println("equivalent");
    List<Coord> a = null;
    List<Coord> b = new ArrayList<>();
    ListComparer instance = new ListComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class ListComparer.
   */
  @Test
  public final void testEquivalentListSecondNull() {
    System.out.println("equivalent");
    List<Coord> a = new ArrayList<>();
    List<Coord> b = null;
    ListComparer instance = new ListComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class ListComparer.
   */
  @Test
  public final void testEquivalentListFirstEmpty() {
    System.out.println("equivalent");
    List<Coord> a = new ArrayList<>();
    Coord x = new CoordXY(10, 10);
    List<Coord> b = new ArrayList<>();
    b.add(x);
    ListComparer instance = new ListComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class ListComparer.
   */
  @Test
  public final void testEquivalentListSecondEmpty() {
    System.out.println("equivalent");
    List<Coord> a = new ArrayList<>();
    Coord x = new CoordXY(10, 10);
    List<Coord> b = new ArrayList<>();
    a.add(x);
    ListComparer instance = new ListComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class ListComparer.
   */
  @Test
  public final void testEquivalentPartSizeOneSame() {
    System.out.println("equivalent part size one same");
    Coord x = new CoordXY(10, 10);
     List<Coord> a = new ArrayList<>();
    a.add(x);
    List<Coord> b = new ArrayList<>();
    b.add(x.copy());
    ListComparer instance = new ListComparer();
    boolean expResult = true;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class ListComparer.
   */
  @Test
  public final void testEquivalentPartSizeOneDiff() {
    System.out.println("equivalent part size one different");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
     List<Coord> a = new ArrayList<>();
    a.add(x);
    List<Coord> b = new ArrayList<>();
    b.add(y);
    ListComparer instance = new ListComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class ListComparer.
   */
  @Test
  public final void testEquivalentListOpenSameOrder() {
    System.out.println("equivalent part oen same order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    List<Coord> a = new ArrayList<>();
    a.add(x);
    a.add(y);
    a.add(z);
    List<Coord> b = new ArrayList<>();
    b.add(x.copy());
    b.add(y.copy());
    b.add(z.copy());

    ListComparer instance = new ListComparer();
    boolean expResult = true;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class ListComparer.
   */
  @Test
  public final void testEquivalentPartOpenReverseOrder() {
    System.out.println("equivalent part open reverse order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    List<Coord> a = new ArrayList<>();
    a.add(x);
    a.add(y);
    a.add(z);
    List<Coord> b = new ArrayList<>();
    b.add(z.copy());
    b.add(y.copy());
    b.add(x.copy());

    ListComparer instance = new ListComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class ListComparer.
   */
  @Test
  public final void testEquivalentPartOpenCycledOrder() {
    System.out.println("equivalent closed cycled order");
    Coord w = new CoordXY(5, 10);
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    List<Coord> a = new ArrayList<>();
    a.add(w);
    a.add(x);
    a.add(y);
    a.add(z);
    List<Coord> b = new ArrayList<>();
    // ordered in to require cycling to be equal.
    b.add(z.copy());
    b.add(w.copy());
    b.add(x.copy());
    b.add(y.copy());

    ListComparer instance = new ListComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class ListComparer.
   */
  @Test
  public final void testEquivalentPartOpenCycledReversed() {
    System.out.println("equivalent closed cycled order");
    Coord w = new CoordXY(5, 10);
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    List<Coord> a = new ArrayList<>();
    a.add(w);
    a.add(x);
    a.add(y);
    a.add(z);
    List<Coord> b = new ArrayList<>();
    // ordered in to require cycling to be equal.
    b.add(w.copy());
    b.add(z.copy());
    b.add(y.copy());
    b.add(x.copy());

    ListComparer instance = new ListComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class ListComparer.
   */
  @Test
  public final void testEquivalentPartOpenWrongOrder() {
    System.out.println("equivalent open wrong order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    List<Coord> a = new ArrayList<>();
    a.add(x);
    a.add(y);
    a.add(z);
    List<Coord> b = new ArrayList<>();
    b.add(x.copy());
    b.add(z.copy());
    b.add(y.copy());

    ListComparer instance = new ListComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class ListComparer.
   */
  @Test
  public final void testEquivalentPartOpenWrongSize() {
    System.out.println("equivalent open wrong order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);

    List<Coord> a = new ArrayList<>();
    a.add(x);
    a.add(y);
    a.add(z);
    List<Coord> b = new ArrayList<>();
    b.add(x.copy());
    b.add(z.copy());

    ListComparer instance = new ListComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

  /**
   * Test of equivalent method, of class ListComparer.
   */
  @Test
  public final void testEquivalentPartOpenWrongCoord() {
    System.out.println("equivalent open wrong order");
    Coord x = new CoordXY(10, 10);
    Coord y = new CoordXY(20, 10);
    Coord z = new CoordXY(20, 20);
    Coord y2 = new CoordXY(20, 11);

    List<Coord> a = new ArrayList<>();
    a.add(x);
    a.add(y);
    a.add(z);
    List<Coord> b = new ArrayList<>();
    b.add(x.copy());
    b.add(y2.copy());
    b.add(z.copy());

    ListComparer instance = new ListComparer();
    boolean expResult = false;
    boolean result = instance.equivalent(a, b);
    assertEquals(expResult, result);
  }

}
