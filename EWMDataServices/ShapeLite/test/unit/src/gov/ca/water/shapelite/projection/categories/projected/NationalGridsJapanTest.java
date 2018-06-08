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
package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class NationalGridsJapanTest {

  /**
   * Since UTM is in meters, demand a TOLERANCE of 1mm.
   */
  private static final double TOLERANCE = .001;

  /**
   * The WGS84 projection, which is used to test going to geographic coordinates
   * and back.
   */
  private static final ProjectionInfo WGS84
      = Projections.getGeographic().getWorld().getWGS1984();

  /**
   * The UtmOther projection coordinate system list.
   */
  private static final NationalGridsJapan PROJ = Projections.getProjected().getNationalGridsJapan();

  /**
   * Tests converting values to WGS84 and back.
   *
   * @param projection The ProjectionInfo to test.
   */
  public void testToWGS84AndBack(ProjectionInfo projection) {

    try {
      Coord original = new CoordXY(100, 100);
      Coord world = Reproject.reprojectCoordinate(original, projection, WGS84);
      Coord result = Reproject.reprojectCoordinate(world, WGS84, projection);
      assertEquals(projection.getName() + " failed.", original.getX(),
          result.getX(), TOLERANCE);
      assertEquals(projection.getName() + " failed.", original.getY(),
          result.getY(), TOLERANCE);
    } catch (ProjectionException ex) {
      Logger.getLogger(UtmOtherTest.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Test of getJGD2000JapanZone1 method, of class NationalGridsJapan.
   */
  @Test
  public void testJGD2000JapanZone1() {
    testToWGS84AndBack(PROJ.getJGD2000JapanZone1());
  }

  /**
   * Test of getJGD2000JapanZone10 method, of class NationalGridsJapan.
   */
  @Test
  public void testJGD2000JapanZone10() {
    testToWGS84AndBack(PROJ.getJGD2000JapanZone10());
  }

  /**
   * Test of getJGD2000JapanZone11 method, of class NationalGridsJapan.
   */
  @Test
  public void testJGD2000JapanZone11() {
    testToWGS84AndBack(PROJ.getJGD2000JapanZone11());
  }

  /**
   * Test of getJGD2000JapanZone12 method, of class NationalGridsJapan.
   */
  @Test
  public void testJGD2000JapanZone12() {
    testToWGS84AndBack(PROJ.getJGD2000JapanZone12());
  }

  /**
   * Test of getJGD2000JapanZone13 method, of class NationalGridsJapan.
   */
  @Test
  public void testJGD2000JapanZone13() {
    testToWGS84AndBack(PROJ.getJGD2000JapanZone13());
  }

  /**
   * Test of getJGD2000JapanZone14 method, of class NationalGridsJapan.
   */
  @Test
  public void testJGD2000JapanZone14() {
    testToWGS84AndBack(PROJ.getJGD2000JapanZone14());
  }

  /**
   * Test of getJGD2000JapanZone15 method, of class NationalGridsJapan.
   */
  @Test
  public void testJGD2000JapanZone15() {
    testToWGS84AndBack(PROJ.getJGD2000JapanZone15());
  }

  /**
   * Test of getJGD2000JapanZone16 method, of class NationalGridsJapan.
   */
  @Test
  public void testJGD2000JapanZone16() {
    testToWGS84AndBack(PROJ.getJGD2000JapanZone16());
  }

  /**
   * Test of getJGD2000JapanZone17 method, of class NationalGridsJapan.
   */
  @Test
  public void testJGD2000JapanZone17() {
    testToWGS84AndBack(PROJ.getJGD2000JapanZone17());
  }

  /**
   * Test of getJGD2000JapanZone18 method, of class NationalGridsJapan.
   */
  @Test
  public void testJGD2000JapanZone18() {
    testToWGS84AndBack(PROJ.getJGD2000JapanZone18());
  }

  /**
   * Test of getJGD2000JapanZone19 method, of class NationalGridsJapan.
   */
  @Test
  public void testJGD2000JapanZone19() {
    testToWGS84AndBack(PROJ.getJGD2000JapanZone19());
  }

  /**
   * Test of getJGD2000JapanZone2 method, of class NationalGridsJapan.
   */
  @Test
  public void testJGD2000JapanZone2() {
    testToWGS84AndBack(PROJ.getJGD2000JapanZone2());
  }

  /**
   * Test of getJGD2000JapanZone3 method, of class NationalGridsJapan.
   */
  @Test
  public void testJGD2000JapanZone3() {
    testToWGS84AndBack(PROJ.getJGD2000JapanZone3());
  }

  /**
   * Test of getJGD2000JapanZone4 method, of class NationalGridsJapan.
   */
  @Test
  public void testJGD2000JapanZone4() {
    testToWGS84AndBack(PROJ.getJGD2000JapanZone4());
  }

  /**
   * Test of getJGD2000JapanZone5 method, of class NationalGridsJapan.
   */
  @Test
  public void testJGD2000JapanZone5() {
    testToWGS84AndBack(PROJ.getJGD2000JapanZone5());
  }

  /**
   * Test of getJGD2000JapanZone6 method, of class NationalGridsJapan.
   */
  @Test
  public void testJGD2000JapanZone6() {
    testToWGS84AndBack(PROJ.getJGD2000JapanZone6());
  }

  /**
   * Test of getJGD2000JapanZone7 method, of class NationalGridsJapan.
   */
  @Test
  public void testJGD2000JapanZone7() {
    testToWGS84AndBack(PROJ.getJGD2000JapanZone7());
  }

  /**
   * Test of getJGD2000JapanZone8 method, of class NationalGridsJapan.
   */
  @Test
  public void testJGD2000JapanZone8() {
    testToWGS84AndBack(PROJ.getJGD2000JapanZone8());
  }

  /**
   * Test of getJGD2000JapanZone9 method, of class NationalGridsJapan.
   */
  @Test
  public void testJGD2000JapanZone9() {
    testToWGS84AndBack(PROJ.getJGD2000JapanZone9());
  }

  /**
   * Test of getJapanZone1 method, of class NationalGridsJapan.
   */
  @Test
  public void testJapanZone1() {
    testToWGS84AndBack(PROJ.getJapanZone1());
  }

  /**
   * Test of getJapanZone10 method, of class NationalGridsJapan.
   */
  @Test
  public void testJapanZone10() {
    testToWGS84AndBack(PROJ.getJapanZone10());
  }

  /**
   * Test of getJapanZone11 method, of class NationalGridsJapan.
   */
  @Test
  public void testJapanZone11() {
    testToWGS84AndBack(PROJ.getJapanZone11());
  }

  /**
   * Test of getJapanZone12 method, of class NationalGridsJapan.
   */
  @Test
  public void testJapanZone12() {
    testToWGS84AndBack(PROJ.getJapanZone12());
  }

  /**
   * Test of getJapanZone13 method, of class NationalGridsJapan.
   */
  @Test
  public void testJapanZone13() {
    testToWGS84AndBack(PROJ.getJapanZone13());
  }

  /**
   * Test of getJapanZone14 method, of class NationalGridsJapan.
   */
  @Test
  public void testJapanZone14() {
    testToWGS84AndBack(PROJ.getJapanZone14());
  }

  /**
   * Test of getJapanZone15 method, of class NationalGridsJapan.
   */
  @Test
  public void testJapanZone15() {
    testToWGS84AndBack(PROJ.getJapanZone15());
  }

  /**
   * Test of getJapanZone16 method, of class NationalGridsJapan.
   */
  @Test
  public void testJapanZone16() {
    testToWGS84AndBack(PROJ.getJapanZone16());
  }

  /**
   * Test of getJapanZone17 method, of class NationalGridsJapan.
   */
  @Test
  public void testJapanZone17() {
    testToWGS84AndBack(PROJ.getJapanZone17());
  }

  /**
   * Test of getJapanZone18 method, of class NationalGridsJapan.
   */
  @Test
  public void testJapanZone18() {
    testToWGS84AndBack(PROJ.getJapanZone18());
  }

  /**
   * Test of getJapanZone19 method, of class NationalGridsJapan.
   */
  @Test
  public void testJapanZone19() {
    testToWGS84AndBack(PROJ.getJapanZone19());
  }

  /**
   * Test of getJapanZone2 method, of class NationalGridsJapan.
   */
  @Test
  public void testJapanZone2() {
    testToWGS84AndBack(PROJ.getJapanZone2());
  }

  /**
   * Test of getJapanZone3 method, of class NationalGridsJapan.
   */
  @Test
  public void testJapanZone3() {
    testToWGS84AndBack(PROJ.getJapanZone3());
  }

  /**
   * Test of getJapanZone4 method, of class NationalGridsJapan.
   */
  @Test
  public void testJapanZone4() {
    testToWGS84AndBack(PROJ.getJapanZone4());
  }

  /**
   * Test of getJapanZone5 method, of class NationalGridsJapan.
   */
  @Test
  public void testJapanZone5() {
    testToWGS84AndBack(PROJ.getJapanZone5());
  }

  /**
   * Test of getJapanZone6 method, of class NationalGridsJapan.
   */
  @Test
  public void testJapanZone6() {
    testToWGS84AndBack(PROJ.getJapanZone6());
  }

  /**
   * Test of getJapanZone7 method, of class NationalGridsJapan.
   */
  @Test
  public void testJapanZone7() {
    testToWGS84AndBack(PROJ.getJapanZone7());
  }

  /**
   * Test of getJapanZone8 method, of class NationalGridsJapan.
   */
  @Test
  public void testJapanZone8() {
    testToWGS84AndBack(PROJ.getJapanZone8());
  }

  /**
   * Test of getJapanZone9 method, of class NationalGridsJapan.
   */
  @Test
  public void testJapanZone9() {
    testToWGS84AndBack(PROJ.getJapanZone9());
  }

}
