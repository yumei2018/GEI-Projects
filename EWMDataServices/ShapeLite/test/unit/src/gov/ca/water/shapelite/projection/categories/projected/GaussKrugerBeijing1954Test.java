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
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GaussKrugerBeijing1954Test {

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
  private static final GaussKrugerBeijing1954 PROJ
      = Projections.getProjected().getGaussKrugerBeijing1954();

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
   * Test of getBeijing19543DegreeGKCM102E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM102E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM102E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM105E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM105E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM105E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM108E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM108E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM108E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM111E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM111E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM111E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM114E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM114E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM114E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM117E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM117E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM117E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM120E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM120E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM120E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM123E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM123E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM123E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM126E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM126E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM126E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM129E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM129E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM129E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM132E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM132E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM132E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM135E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM135E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM135E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM75E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM75E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM75E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM78E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM78E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM78E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM81E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM81E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM81E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM84E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM84E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM84E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM87E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM87E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM87E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM90E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM90E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM90E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM93E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM93E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM93E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM96E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM96E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM96E());
  }

  /**
   * Test of getBeijing19543DegreeGKCM99E method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKCM99E() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKCM99E());
  }

  /**
   * Test of getBeijing19543DegreeGKZone25 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone25() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone25());
  }

  /**
   * Test of getBeijing19543DegreeGKZone26 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone26() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone26());
  }

  /**
   * Test of getBeijing19543DegreeGKZone27 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone27() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone27());
  }

  /**
   * Test of getBeijing19543DegreeGKZone28 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone28() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone28());
  }

  /**
   * Test of getBeijing19543DegreeGKZone29 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone29() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone29());
  }

  /**
   * Test of getBeijing19543DegreeGKZone30 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone30() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone30());
  }

  /**
   * Test of getBeijing19543DegreeGKZone31 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone31() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone31());
  }

  /**
   * Test of getBeijing19543DegreeGKZone32 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone32() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone32());
  }

  /**
   * Test of getBeijing19543DegreeGKZone33 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone33() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone33());
  }

  /**
   * Test of getBeijing19543DegreeGKZone34 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone34() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone34());
  }

  /**
   * Test of getBeijing19543DegreeGKZone35 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone35() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone35());
  }

  /**
   * Test of getBeijing19543DegreeGKZone36 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone36() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone36());
  }

  /**
   * Test of getBeijing19543DegreeGKZone37 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone37() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone37());
  }

  /**
   * Test of getBeijing19543DegreeGKZone38 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone38() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone38());
  }

  /**
   * Test of getBeijing19543DegreeGKZone39 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone39() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone39());
  }

  /**
   * Test of getBeijing19543DegreeGKZone40 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone40() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone40());
  }

  /**
   * Test of getBeijing19543DegreeGKZone41 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone41() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone41());
  }

  /**
   * Test of getBeijing19543DegreeGKZone42 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone42() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone42());
  }

  /**
   * Test of getBeijing19543DegreeGKZone43 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone43() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone43());
  }

  /**
   * Test of getBeijing19543DegreeGKZone44 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone44() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone44());
  }

  /**
   * Test of getBeijing19543DegreeGKZone45 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing19543DegreeGKZone45() {
    testToWGS84AndBack(PROJ.getBeijing19543DegreeGKZone45());
  }

  /**
   * Test of getBeijing1954GKZone13 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone13() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone13());
  }

  /**
   * Test of getBeijing1954GKZone13N method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone13N() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone13N());
  }

  /**
   * Test of getBeijing1954GKZone14 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone14() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone14());
  }

  /**
   * Test of getBeijing1954GKZone14N method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone14N() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone14N());
  }

  /**
   * Test of getBeijing1954GKZone15 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone15() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone15());
  }

  /**
   * Test of getBeijing1954GKZone15N method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone15N() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone15N());
  }

  /**
   * Test of getBeijing1954GKZone16 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone16() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone16());
  }

  /**
   * Test of getBeijing1954GKZone16N method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone16N() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone16N());
  }

  /**
   * Test of getBeijing1954GKZone17 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone17() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone17());
  }

  /**
   * Test of getBeijing1954GKZone17N method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone17N() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone17N());
  }

  /**
   * Test of getBeijing1954GKZone18 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone18() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone18());
  }

  /**
   * Test of getBeijing1954GKZone18N method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone18N() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone18N());
  }

  /**
   * Test of getBeijing1954GKZone19 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone19() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone19());
  }

  /**
   * Test of getBeijing1954GKZone19N method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone19N() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone19N());
  }

  /**
   * Test of getBeijing1954GKZone20 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone20() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone20());
  }

  /**
   * Test of getBeijing1954GKZone20N method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone20N() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone20N());
  }

  /**
   * Test of getBeijing1954GKZone21 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone21() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone21());
  }

  /**
   * Test of getBeijing1954GKZone21N method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone21N() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone21N());
  }

  /**
   * Test of getBeijing1954GKZone22 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone22() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone22());
  }

  /**
   * Test of getBeijing1954GKZone22N method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone22N() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone22N());
  }

  /**
   * Test of getBeijing1954GKZone23 method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone23() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone23());
  }

  /**
   * Test of getBeijing1954GKZone23N method, of class GaussKrugerBeijing1954.
   */
  @Test
  public void testBeijing1954GKZone23N() {
    testToWGS84AndBack(PROJ.getBeijing1954GKZone23N());
  }

}
