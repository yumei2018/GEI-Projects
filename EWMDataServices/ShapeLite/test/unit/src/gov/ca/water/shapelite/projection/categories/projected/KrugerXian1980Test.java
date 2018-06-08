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
public class KrugerXian1980Test {

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
  private static final KrugerXian1980 PROJ
      = Projections.getProjected().getKrugerXian1980();

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
   * Test of getXian19803DegreeGKCM102E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM102E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM102E());
  }

  /**
   * Test of getXian19803DegreeGKCM105E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM105E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM105E());
  }

  /**
   * Test of getXian19803DegreeGKCM108E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM108E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM108E());
  }

  /**
   * Test of getXian19803DegreeGKCM111E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM111E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM111E());
  }

  /**
   * Test of getXian19803DegreeGKCM114E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM114E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM114E());
  }

  /**
   * Test of getXian19803DegreeGKCM117E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM117E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM117E());
  }

  /**
   * Test of getXian19803DegreeGKCM120E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM120E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM120E());
  }

  /**
   * Test of getXian19803DegreeGKCM123E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM123E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM123E());
  }

  /**
   * Test of getXian19803DegreeGKCM126E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM126E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM126E());
  }

  /**
   * Test of getXian19803DegreeGKCM129E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM129E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM129E());
  }

  /**
   * Test of getXian19803DegreeGKCM132E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM132E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM132E());
  }

  /**
   * Test of getXian19803DegreeGKCM135E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM135E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM135E());
  }

  /**
   * Test of getXian19803DegreeGKCM75E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM75E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM75E());
  }

  /**
   * Test of getXian19803DegreeGKCM78E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM78E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM78E());
  }

  /**
   * Test of getXian19803DegreeGKCM81E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM81E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM81E());
  }

  /**
   * Test of getXian19803DegreeGKCM84E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM84E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM84E());
  }

  /**
   * Test of getXian19803DegreeGKCM87E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM87E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM87E());
  }

  /**
   * Test of getXian19803DegreeGKCM90E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM90E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM90E());
  }

  /**
   * Test of getXian19803DegreeGKCM93E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM93E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM93E());
  }

  /**
   * Test of getXian19803DegreeGKCM96E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM96E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM96E());
  }

  /**
   * Test of getXian19803DegreeGKCM99E method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKCM99E() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKCM99E());
  }

  /**
   * Test of getXian19803DegreeGKZone25 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone25() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone25());
  }

  /**
   * Test of getXian19803DegreeGKZone26 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone26() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone26());
  }

  /**
   * Test of getXian19803DegreeGKZone27 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone27() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone27());
  }

  /**
   * Test of getXian19803DegreeGKZone28 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone28() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone28());
  }

  /**
   * Test of getXian19803DegreeGKZone29 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone29() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone29());
  }

  /**
   * Test of getXian19803DegreeGKZone30 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone30() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone30());
  }

  /**
   * Test of getXian19803DegreeGKZone31 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone31() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone31());
  }

  /**
   * Test of getXian19803DegreeGKZone32 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone32() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone32());
  }

  /**
   * Test of getXian19803DegreeGKZone33 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone33() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone33());
  }

  /**
   * Test of getXian19803DegreeGKZone34 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone34() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone34());
  }

  /**
   * Test of getXian19803DegreeGKZone35 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone35() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone35());
  }

  /**
   * Test of getXian19803DegreeGKZone36 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone36() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone36());
  }

  /**
   * Test of getXian19803DegreeGKZone37 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone37() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone37());
  }

  /**
   * Test of getXian19803DegreeGKZone38 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone38() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone38());
  }

  /**
   * Test of getXian19803DegreeGKZone39 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone39() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone39());
  }

  /**
   * Test of getXian19803DegreeGKZone40 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone40() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone40());
  }

  /**
   * Test of getXian19803DegreeGKZone41 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone41() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone41());
  }

  /**
   * Test of getXian19803DegreeGKZone42 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone42() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone42());
  }

  /**
   * Test of getXian19803DegreeGKZone43 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone43() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone43());
  }

  /**
   * Test of getXian19803DegreeGKZone44 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone44() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone44());
  }

  /**
   * Test of getXian19803DegreeGKZone45 method, of class KrugerXian1980.
   */
  @Test
  public void testXian19803DegreeGKZone45() {
    testToWGS84AndBack(PROJ.getXian19803DegreeGKZone45());
  }

  /**
   * Test of getXian1980GKCM105E method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKCM105E() {
    testToWGS84AndBack(PROJ.getXian1980GKCM105E());
  }

  /**
   * Test of getXian1980GKCM111E method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKCM111E() {
    testToWGS84AndBack(PROJ.getXian1980GKCM111E());
  }

  /**
   * Test of getXian1980GKCM117E method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKCM117E() {
    testToWGS84AndBack(PROJ.getXian1980GKCM117E());
  }

  /**
   * Test of getXian1980GKCM123E method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKCM123E() {
    testToWGS84AndBack(PROJ.getXian1980GKCM123E());
  }

  /**
   * Test of getXian1980GKCM129E method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKCM129E() {
    testToWGS84AndBack(PROJ.getXian1980GKCM129E());
  }

  /**
   * Test of getXian1980GKCM135E method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKCM135E() {
    testToWGS84AndBack(PROJ.getXian1980GKCM135E());
  }

  /**
   * Test of getXian1980GKCM75E method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKCM75E() {
    testToWGS84AndBack(PROJ.getXian1980GKCM75E());
  }

  /**
   * Test of getXian1980GKCM81E method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKCM81E() {
    testToWGS84AndBack(PROJ.getXian1980GKCM81E());
  }

  /**
   * Test of getXian1980GKCM87E method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKCM87E() {
    testToWGS84AndBack(PROJ.getXian1980GKCM87E());
  }

  /**
   * Test of getXian1980GKCM93E method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKCM93E() {
    testToWGS84AndBack(PROJ.getXian1980GKCM93E());
  }

  /**
   * Test of getXian1980GKCM99E method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKCM99E() {
    testToWGS84AndBack(PROJ.getXian1980GKCM99E());
  }

  /**
   * Test of getXian1980GKZone13 method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKZone13() {
    testToWGS84AndBack(PROJ.getXian1980GKZone13());
  }

  /**
   * Test of getXian1980GKZone14 method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKZone14() {
    testToWGS84AndBack(PROJ.getXian1980GKZone14());
  }

  /**
   * Test of getXian1980GKZone15 method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKZone15() {
    testToWGS84AndBack(PROJ.getXian1980GKZone15());
  }

  /**
   * Test of getXian1980GKZone16 method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKZone16() {
    testToWGS84AndBack(PROJ.getXian1980GKZone16());
  }

  /**
   * Test of getXian1980GKZone17 method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKZone17() {
    testToWGS84AndBack(PROJ.getXian1980GKZone17());
  }

  /**
   * Test of getXian1980GKZone18 method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKZone18() {
    testToWGS84AndBack(PROJ.getXian1980GKZone18());
  }

  /**
   * Test of getXian1980GKZone19 method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKZone19() {
    testToWGS84AndBack(PROJ.getXian1980GKZone19());
  }

  /**
   * Test of getXian1980GKZone20 method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKZone20() {
    testToWGS84AndBack(PROJ.getXian1980GKZone20());
  }

  /**
   * Test of getXian1980GKZone21 method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKZone21() {
    testToWGS84AndBack(PROJ.getXian1980GKZone21());
  }

  /**
   * Test of getXian1980GKZone22 method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKZone22() {
    testToWGS84AndBack(PROJ.getXian1980GKZone22());
  }

  /**
   * Test of getXian1980GKZone23 method, of class KrugerXian1980.
   */
  @Test
  public void testXian1980GKZone23() {
    testToWGS84AndBack(PROJ.getXian1980GKZone23());
  }

}
