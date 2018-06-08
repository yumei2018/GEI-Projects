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
public class NationalGridsNorwayTest {

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
  private static final NationalGridsNorway PROJ = Projections.getProjected().getNationalGridsNorway();

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
   * Test of getNGO1948BaerumKommune method, of class NationalGridsNorway.
   */
  @Test
  public void testNGO1948BaerumKommune() {
    testToWGS84AndBack(PROJ.getNGO1948BaerumKommune());
  }

  /**
   * Test of getNGO1948Bergenhalvoen method, of class NationalGridsNorway.
   */
  @Test
  public void testNGO1948Bergenhalvoen() {
    testToWGS84AndBack(PROJ.getNGO1948Bergenhalvoen());
  }

  /**
   * Test of getNGO1948NorwayZone1 method, of class NationalGridsNorway.
   */
  @Test
  public void testNGO1948NorwayZone1() {
    testToWGS84AndBack(PROJ.getNGO1948NorwayZone1());
  }

  /**
   * Test of getNGO1948NorwayZone2 method, of class NationalGridsNorway.
   */
  @Test
  public void testNGO1948NorwayZone2() {
    testToWGS84AndBack(PROJ.getNGO1948NorwayZone2());
  }

  /**
   * Test of getNGO1948NorwayZone3 method, of class NationalGridsNorway.
   */
  @Test
  public void testNGO1948NorwayZone3() {
    testToWGS84AndBack(PROJ.getNGO1948NorwayZone3());
  }

  /**
   * Test of getNGO1948NorwayZone4 method, of class NationalGridsNorway.
   */
  @Test
  public void testNGO1948NorwayZone4() {
    testToWGS84AndBack(PROJ.getNGO1948NorwayZone4());
  }

  /**
   * Test of getNGO1948NorwayZone5 method, of class NationalGridsNorway.
   */
  @Test
  public void testNGO1948NorwayZone5() {
    testToWGS84AndBack(PROJ.getNGO1948NorwayZone5());
  }

  /**
   * Test of getNGO1948NorwayZone6 method, of class NationalGridsNorway.
   */
  @Test
  public void testNGO1948NorwayZone6() {
    testToWGS84AndBack(PROJ.getNGO1948NorwayZone6());
  }

  /**
   * Test of getNGO1948NorwayZone7 method, of class NationalGridsNorway.
   */
  @Test
  public void testNGO1948NorwayZone7() {
    testToWGS84AndBack(PROJ.getNGO1948NorwayZone7());
  }

  /**
   * Test of getNGO1948NorwayZone8 method, of class NationalGridsNorway.
   */
  @Test
  public void testNGO1948NorwayZone8() {
    testToWGS84AndBack(PROJ.getNGO1948NorwayZone8());
  }

  /**
   * Test of getNGO1948OsloKommune method, of class NationalGridsNorway.
   */
  @Test
  public void testNGO1948OsloKommune() {
    testToWGS84AndBack(PROJ.getNGO1948OsloKommune());
  }

  /**
   * Test of getNGO1948OsloNorwayZone1 method, of class NationalGridsNorway.
   */
  @Test
  public void testNGO1948OsloNorwayZone1() {
    testToWGS84AndBack(PROJ.getNGO1948OsloNorwayZone1());
  }

  /**
   * Test of getNGO1948OsloNorwayZone2 method, of class NationalGridsNorway.
   */
  @Test
  public void testNGO1948OsloNorwayZone2() {
    testToWGS84AndBack(PROJ.getNGO1948OsloNorwayZone2());
  }

  /**
   * Test of getNGO1948OsloNorwayZone3 method, of class NationalGridsNorway.
   */
  @Test
  public void testNGO1948OsloNorwayZone3() {
    testToWGS84AndBack(PROJ.getNGO1948OsloNorwayZone3());
  }

  /**
   * Test of getNGO1948OsloNorwayZone4 method, of class NationalGridsNorway.
   */
  @Test
  public void testNGO1948OsloNorwayZone4() {
    testToWGS84AndBack(PROJ.getNGO1948OsloNorwayZone4());
  }

  /**
   * Test of getNGO1948OsloNorwayZone5 method, of class NationalGridsNorway.
   */
  @Test
  public void testNGO1948OsloNorwayZone5() {
    testToWGS84AndBack(PROJ.getNGO1948OsloNorwayZone5());
  }

  /**
   * Test of getNGO1948OsloNorwayZone6 method, of class NationalGridsNorway.
   */
  @Test
  public void testNGO1948OsloNorwayZone6() {
    testToWGS84AndBack(PROJ.getNGO1948OsloNorwayZone6());
  }

  /**
   * Test of getNGO1948OsloNorwayZone7 method, of class NationalGridsNorway.
   */
  @Test
  public void testNGO1948OsloNorwayZone7() {
    testToWGS84AndBack(PROJ.getNGO1948OsloNorwayZone7());
  }

  /**
   * Test of getNGO1948OsloNorwayZone8 method, of class NationalGridsNorway.
   */
  @Test
  public void testNGO1948OsloNorwayZone8() {
    testToWGS84AndBack(PROJ.getNGO1948OsloNorwayZone8());
  }

}
