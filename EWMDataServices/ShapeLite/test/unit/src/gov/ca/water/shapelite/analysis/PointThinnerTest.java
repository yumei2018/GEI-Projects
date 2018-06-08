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
package gov.ca.water.shapelite.analysis;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.compare.ListComparer;
import gov.ca.water.shapelite.coordinate.CoordFactory;
import gov.ca.water.shapelite.coordinate.CoordXY;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PointThinnerTest {

  public PointThinnerTest() {
  }

  /**
   * Test of thin method, of class PointThinner.
   */
  @Test
  public final void testThinUnder() {
    System.out.println("thin");
    PointThinner instance = new PointThinner();
    List<CoordXY> coords = CoordFactory.randomXY(50);
    Collections.sort(coords, Coord.By.YX);
    ListComparer lists = new ListComparer();
    List<CoordXY> resultList = instance.thin(coords, coords.get(0),
        coords.get(coords.size() - 1), 100);
    boolean expResult = true;
    boolean result = lists.equivalent(coords, resultList);
    assertEquals(expResult, result);
  }

  @Test
  public final void testThinnedPointCount() {
    System.out.println("thin");

    PointThinner instance = new PointThinner();
    // use a seed if this should be repeatable.
    List<CoordXY> coords = CoordFactory.randomXY(1000, 10);
    Collections.sort(coords, Coord.By.X);
    Coord start = coords.get(0);
    Coord end = coords.get(coords.size() - 1);
    List<CoordXY> resultList = instance.thin(coords, start, end, 100);
    // assert the result list is not greater than 100
    int expSize = 100;
    assertTrue("The resulting list should have been trimmed to 100.",
        resultList.size() <= expSize);
  }

  @Test
  public final void testThinnedPointsInOriginal() {
    System.out.println("thin");
    PointThinner instance = new PointThinner();
    // use a seed if this should be repeatable.
    List<CoordXY> coords = CoordFactory.randomXY(1000, 20);
    Collections.sort(coords, Coord.By.X);
    Coord start = coords.get(0);
    Coord end = coords.get(coords.size() - 1);
    List<CoordXY> resultList = instance.thin(coords, start, end, 100);
    for (CoordXY coord : resultList) {
      if (!coords.contains(coord)) {
        fail("Coord " + coord.toString() + " was not found in the original list.");
      }
    }
  }

}
