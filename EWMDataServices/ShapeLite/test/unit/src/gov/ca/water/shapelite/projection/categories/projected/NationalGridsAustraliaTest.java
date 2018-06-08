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
public class NationalGridsAustraliaTest {

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
  private static final NationalGridsAustralia PROJ
      = Projections.getProjected().getNationalGridsAustralia();

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
   * Test of getAGD1966ACTGridAGCZone method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966ACTGridAGCZone() {
    testToWGS84AndBack(PROJ.getAGD1966ACTGridAGCZone());
  }

  /**
   * Test of getAGD1966AMGZone48 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966AMGZone48() {
    testToWGS84AndBack(PROJ.getAGD1966AMGZone48());
  }

  /**
   * Test of getAGD1966AMGZone49 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966AMGZone49() {
    testToWGS84AndBack(PROJ.getAGD1966AMGZone49());
  }

  /**
   * Test of getAGD1966AMGZone50 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966AMGZone50() {
    testToWGS84AndBack(PROJ.getAGD1966AMGZone50());
  }

  /**
   * Test of getAGD1966AMGZone51 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966AMGZone51() {
    testToWGS84AndBack(PROJ.getAGD1966AMGZone51());
  }

  /**
   * Test of getAGD1966AMGZone52 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966AMGZone52() {
    testToWGS84AndBack(PROJ.getAGD1966AMGZone52());
  }

  /**
   * Test of getAGD1966AMGZone53 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966AMGZone53() {
    testToWGS84AndBack(PROJ.getAGD1966AMGZone53());
  }

  /**
   * Test of getAGD1966AMGZone54 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966AMGZone54() {
    testToWGS84AndBack(PROJ.getAGD1966AMGZone54());
  }

  /**
   * Test of getAGD1966AMGZone55 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966AMGZone55() {
    testToWGS84AndBack(PROJ.getAGD1966AMGZone55());
  }

  /**
   * Test of getAGD1966AMGZone56 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966AMGZone56() {
    testToWGS84AndBack(PROJ.getAGD1966AMGZone56());
  }

  /**
   * Test of getAGD1966AMGZone57 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966AMGZone57() {
    testToWGS84AndBack(PROJ.getAGD1966AMGZone57());
  }

  /**
   * Test of getAGD1966AMGZone58 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966AMGZone58() {
    testToWGS84AndBack(PROJ.getAGD1966AMGZone58());
  }

  /**
   * Test of getAGD1966ISG542 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966ISG542() {
    testToWGS84AndBack(PROJ.getAGD1966ISG542());
  }

  /**
   * Test of getAGD1966ISG543 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966ISG543() {
    testToWGS84AndBack(PROJ.getAGD1966ISG543());
  }

  /**
   * Test of getAGD1966ISG551 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966ISG551() {
    testToWGS84AndBack(PROJ.getAGD1966ISG551());
  }

  /**
   * Test of getAGD1966ISG552 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966ISG552() {
    testToWGS84AndBack(PROJ.getAGD1966ISG552());
  }

  /**
   * Test of getAGD1966ISG553 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966ISG553() {
    testToWGS84AndBack(PROJ.getAGD1966ISG553());
  }

  /**
   * Test of getAGD1966ISG561 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966ISG561() {
    testToWGS84AndBack(PROJ.getAGD1966ISG561());
  }

  /**
   * Test of getAGD1966ISG562 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966ISG562() {
    testToWGS84AndBack(PROJ.getAGD1966ISG562());
  }

  /**
   * Test of getAGD1966ISG563 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966ISG563() {
    testToWGS84AndBack(PROJ.getAGD1966ISG563());
  }

  /**
   * Test of getAGD1966VICGRID method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1966VICGRID() {
    testToWGS84AndBack(PROJ.getAGD1966VICGRID());
  }

  /**
   * Test of getAGD1984AMGZone48 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1984AMGZone48() {
    testToWGS84AndBack(PROJ.getAGD1984AMGZone48());
  }

  /**
   * Test of getAGD1984AMGZone49 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1984AMGZone49() {
    testToWGS84AndBack(PROJ.getAGD1984AMGZone49());
  }

  /**
   * Test of getAGD1984AMGZone50 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1984AMGZone50() {
    testToWGS84AndBack(PROJ.getAGD1984AMGZone50());
  }

  /**
   * Test of getAGD1984AMGZone51 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1984AMGZone51() {
    testToWGS84AndBack(PROJ.getAGD1984AMGZone51());
  }

  /**
   * Test of getAGD1984AMGZone52 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1984AMGZone52() {
    testToWGS84AndBack(PROJ.getAGD1984AMGZone52());
  }

  /**
   * Test of getAGD1984AMGZone53 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1984AMGZone53() {
    testToWGS84AndBack(PROJ.getAGD1984AMGZone53());
  }

  /**
   * Test of getAGD1984AMGZone54 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1984AMGZone54() {
    testToWGS84AndBack(PROJ.getAGD1984AMGZone54());
  }

  /**
   * Test of getAGD1984AMGZone55 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1984AMGZone55() {
    testToWGS84AndBack(PROJ.getAGD1984AMGZone55());
  }

  /**
   * Test of getAGD1984AMGZone56 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1984AMGZone56() {
    testToWGS84AndBack(PROJ.getAGD1984AMGZone56());
  }

  /**
   * Test of getAGD1984AMGZone57 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1984AMGZone57() {
    testToWGS84AndBack(PROJ.getAGD1984AMGZone57());
  }

  /**
   * Test of getAGD1984AMGZone58 method, of class NationalGridsAustralia.
   */
  @Test
  public void testAGD1984AMGZone58() {
    testToWGS84AndBack(PROJ.getAGD1984AMGZone58());
  }

  /**
   * Test of getGDA1994MGAZone48 method, of class NationalGridsAustralia.
   */
  @Test
  public void testGDA1994MGAZone48() {
    testToWGS84AndBack(PROJ.getGDA1994MGAZone48());
  }

  /**
   * Test of getGDA1994MGAZone49 method, of class NationalGridsAustralia.
   */
  @Test
  public void testGDA1994MGAZone49() {
    testToWGS84AndBack(PROJ.getGDA1994MGAZone49());
  }

  /**
   * Test of getGDA1994MGAZone50 method, of class NationalGridsAustralia.
   */
  @Test
  public void testGDA1994MGAZone50() {
    testToWGS84AndBack(PROJ.getGDA1994MGAZone50());
  }

  /**
   * Test of getGDA1994MGAZone51 method, of class NationalGridsAustralia.
   */
  @Test
  public void testGDA1994MGAZone51() {
    testToWGS84AndBack(PROJ.getGDA1994MGAZone51());
  }

  /**
   * Test of getGDA1994MGAZone52 method, of class NationalGridsAustralia.
   */
  @Test
  public void testGDA1994MGAZone52() {
    testToWGS84AndBack(PROJ.getGDA1994MGAZone52());
  }

  /**
   * Test of getGDA1994MGAZone53 method, of class NationalGridsAustralia.
   */
  @Test
  public void testGDA1994MGAZone53() {
    testToWGS84AndBack(PROJ.getGDA1994MGAZone53());
  }

  /**
   * Test of getGDA1994MGAZone54 method, of class NationalGridsAustralia.
   */
  @Test
  public void testGDA1994MGAZone54() {
    testToWGS84AndBack(PROJ.getGDA1994MGAZone54());
  }

  /**
   * Test of getGDA1994MGAZone55 method, of class NationalGridsAustralia.
   */
  @Test
  public void testGDA1994MGAZone55() {
    testToWGS84AndBack(PROJ.getGDA1994MGAZone55());
  }

  /**
   * Test of getGDA1994MGAZone56 method, of class NationalGridsAustralia.
   */
  @Test
  public void testGDA1994MGAZone56() {
    testToWGS84AndBack(PROJ.getGDA1994MGAZone56());
  }

  /**
   * Test of getGDA1994MGAZone57 method, of class NationalGridsAustralia.
   */
  @Test
  public void testGDA1994MGAZone57() {
    testToWGS84AndBack(PROJ.getGDA1994MGAZone57());
  }

  /**
   * Test of getGDA1994MGAZone58 method, of class NationalGridsAustralia.
   */
  @Test
  public void testGDA1994MGAZone58() {
    testToWGS84AndBack(PROJ.getGDA1994MGAZone58());
  }

  /**
   * Test of getGDA1994SouthAustraliaLambert method, of class NationalGridsAustralia.
   */
  @Test
  public void testGDA1994SouthAustraliaLambert() {
    testToWGS84AndBack(PROJ.getGDA1994SouthAustraliaLambert());
  }

  /**
   * Test of getGDA1994VICGRID94 method, of class NationalGridsAustralia.
   */
  @Test
  public void testGDA1994VICGRID94() {
    testToWGS84AndBack(PROJ.getGDA1994VICGRID94());
  }

}
