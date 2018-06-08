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
public class GaussKrugerPulkovo1942Test {

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
  private static final GaussKrugerPulkovo1942 PROJ
      = Projections.getProjected().getGaussKrugerPulkovo1942();

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
   * Test of getPulkovo19423DegreeGKCM102E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM102E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM102E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM105E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM105E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM105E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM108E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM108E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM108E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM111E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM111E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM111E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM114E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM114E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM114E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM117E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM117E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM117E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM120E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM120E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM120E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM123E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM123E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM123E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM126E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM126E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM126E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM129E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM129E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM129E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM132E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM132E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM132E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM135E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM135E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM135E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM138E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM138E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM138E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM141E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM141E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM141E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM144E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM144E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM144E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM147E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM147E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM147E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM150E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM150E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM150E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM153E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM153E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM153E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM156E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM156E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM156E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM159E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM159E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM159E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM162E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM162E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM162E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM165E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM165E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM165E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM168E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM168E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM168E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM168W method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM168W() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM168W());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM171E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM171E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM171E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM171W method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM171W() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM171W());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM174E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM174E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM174E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM174W method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM174W() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM174W());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM177E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM177E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM177E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM177W method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM177W() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM177W());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM180E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM180E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM180E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM21E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM21E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM21E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM24E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM24E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM24E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM27E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM27E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM27E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM30E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM30E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM30E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM33E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM33E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM33E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM36E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM36E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM36E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM39E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM39E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM39E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM42E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM42E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM42E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM45E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM45E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM45E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM48E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM48E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM48E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM51E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM51E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM51E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM54E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM54E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM54E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM57E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM57E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM57E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM60E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM60E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM60E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM63E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM63E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM63E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM66E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM66E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM66E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM69E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM69E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM69E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM72E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM72E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM72E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM75E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM75E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM75E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM78E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM78E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM78E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM81E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM81E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM81E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM84E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM84E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM84E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM87E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM87E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM87E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM90E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM90E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM90E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM93E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM93E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM93E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM96E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM96E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM96E());
  }

  /**
   * Test of getPulkovo19423DegreeGKCM99E method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKCM99E() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKCM99E());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone10 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone10() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone10());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone11 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone11() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone11());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone12 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone12() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone12());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone13 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone13() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone13());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone14 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone14() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone14());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone15 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone15() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone15());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone16 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone16() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone16());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone17 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone17() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone17());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone18 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone18() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone18());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone19 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone19() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone19());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone20 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone20() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone20());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone21 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone21() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone21());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone22 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone22() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone22());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone23 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone23() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone23());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone24 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone24() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone24());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone25 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone25() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone25());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone26 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone26() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone26());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone27 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone27() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone27());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone28 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone28() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone28());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone29 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone29() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone29());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone30 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone30() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone30());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone31 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone31() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone31());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone32 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone32() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone32());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone33 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone33() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone33());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone34 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone34() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone34());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone35 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone35() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone35());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone36 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone36() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone36());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone37 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone37() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone37());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone38 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone38() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone38());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone39 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone39() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone39());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone40 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone40() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone40());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone41 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone41() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone41());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone42 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone42() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone42());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone43 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone43() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone43());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone44 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone44() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone44());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone45 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone45() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone45());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone46 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone46() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone46());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone47 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone47() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone47());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone48 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone48() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone48());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone49 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone49() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone49());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone50 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone50() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone50());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone51 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone51() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone51());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone52 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone52() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone52());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone53 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone53() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone53());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone54 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone54() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone54());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone55 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone55() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone55());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone56 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone56() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone56());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone57 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone57() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone57());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone58 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone58() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone58());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone59 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone59() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone59());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone60 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone60() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone60());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone61 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone61() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone61());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone62 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone62() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone62());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone63 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone63() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone63());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone64 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone64() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone64());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone7 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone7() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone7());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone8 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone8() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone8());
  }

  /**
   * Test of getPulkovo19423DegreeGKZone9 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo19423DegreeGKZone9() {
    testToWGS84AndBack(PROJ.getPulkovo19423DegreeGKZone9());
  }

  /**
   * Test of getPulkovo1942GKZone10 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone10() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone10());
  }

  /**
   * Test of getPulkovo1942GKZone10N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone10N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone10N());
  }

  /**
   * Test of getPulkovo1942GKZone11 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone11() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone11());
  }

  /**
   * Test of getPulkovo1942GKZone11N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone11N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone11N());
  }

  /**
   * Test of getPulkovo1942GKZone12 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone12() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone12());
  }

  /**
   * Test of getPulkovo1942GKZone12N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone12N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone12N());
  }

  /**
   * Test of getPulkovo1942GKZone13 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone13() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone13());
  }

  /**
   * Test of getPulkovo1942GKZone13N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone13N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone13N());
  }

  /**
   * Test of getPulkovo1942GKZone14 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone14() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone14());
  }

  /**
   * Test of getPulkovo1942GKZone14N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone14N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone14N());
  }

  /**
   * Test of getPulkovo1942GKZone15 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone15() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone15());
  }

  /**
   * Test of getPulkovo1942GKZone15N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone15N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone15N());
  }

  /**
   * Test of getPulkovo1942GKZone16 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone16() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone16());
  }

  /**
   * Test of getPulkovo1942GKZone16N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone16N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone16N());
  }

  /**
   * Test of getPulkovo1942GKZone17 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone17() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone17());
  }

  /**
   * Test of getPulkovo1942GKZone17N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone17N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone17N());
  }

  /**
   * Test of getPulkovo1942GKZone18 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone18() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone18());
  }

  /**
   * Test of getPulkovo1942GKZone18N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone18N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone18N());
  }

  /**
   * Test of getPulkovo1942GKZone19 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone19() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone19());
  }

  /**
   * Test of getPulkovo1942GKZone19N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone19N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone19N());
  }

  /**
   * Test of getPulkovo1942GKZone2 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone2() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone2());
  }

  /**
   * Test of getPulkovo1942GKZone20 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone20() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone20());
  }

  /**
   * Test of getPulkovo1942GKZone20N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone20N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone20N());
  }

  /**
   * Test of getPulkovo1942GKZone21 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone21() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone21());
  }

  /**
   * Test of getPulkovo1942GKZone21N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone21N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone21N());
  }

  /**
   * Test of getPulkovo1942GKZone22 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone22() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone22());
  }

  /**
   * Test of getPulkovo1942GKZone22N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone22N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone22N());
  }

  /**
   * Test of getPulkovo1942GKZone23 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone23() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone23());
  }

  /**
   * Test of getPulkovo1942GKZone23N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone23N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone23N());
  }

  /**
   * Test of getPulkovo1942GKZone24 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone24() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone24());
  }

  /**
   * Test of getPulkovo1942GKZone24N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone24N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone24N());
  }

  /**
   * Test of getPulkovo1942GKZone25 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone25() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone25());
  }

  /**
   * Test of getPulkovo1942GKZone25N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone25N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone25N());
  }

  /**
   * Test of getPulkovo1942GKZone26 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone26() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone26());
  }

  /**
   * Test of getPulkovo1942GKZone26N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone26N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone26N());
  }

  /**
   * Test of getPulkovo1942GKZone27 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone27() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone27());
  }

  /**
   * Test of getPulkovo1942GKZone27N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone27N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone27N());
  }

  /**
   * Test of getPulkovo1942GKZone28 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone28() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone28());
  }

  /**
   * Test of getPulkovo1942GKZone28N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone28N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone28N());
  }

  /**
   * Test of getPulkovo1942GKZone29 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone29() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone29());
  }

  /**
   * Test of getPulkovo1942GKZone29N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone29N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone29N());
  }

  /**
   * Test of getPulkovo1942GKZone2N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone2N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone2N());
  }

  /**
   * Test of getPulkovo1942GKZone3 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone3() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone3());
  }

  /**
   * Test of getPulkovo1942GKZone30 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone30() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone30());
  }

  /**
   * Test of getPulkovo1942GKZone30N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone30N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone30N());
  }

  /**
   * Test of getPulkovo1942GKZone31 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone31() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone31());
  }

  /**
   * Test of getPulkovo1942GKZone31N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone31N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone31N());
  }

  /**
   * Test of getPulkovo1942GKZone32 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone32() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone32());
  }

  /**
   * Test of getPulkovo1942GKZone32N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone32N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone32N());
  }

  /**
   * Test of getPulkovo1942GKZone3N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone3N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone3N());
  }

  /**
   * Test of getPulkovo1942GKZone4 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone4() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone4());
  }

  /**
   * Test of getPulkovo1942GKZone4N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone4N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone4N());
  }

  /**
   * Test of getPulkovo1942GKZone5 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone5() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone5());
  }

  /**
   * Test of getPulkovo1942GKZone5N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone5N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone5N());
  }

  /**
   * Test of getPulkovo1942GKZone6 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone6() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone6());
  }

  /**
   * Test of getPulkovo1942GKZone6N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone6N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone6N());
  }

  /**
   * Test of getPulkovo1942GKZone7 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone7() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone7());
  }

  /**
   * Test of getPulkovo1942GKZone7N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone7N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone7N());
  }

  /**
   * Test of getPulkovo1942GKZone8 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone8() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone8());
  }

  /**
   * Test of getPulkovo1942GKZone8N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone8N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone8N());
  }

  /**
   * Test of getPulkovo1942GKZone9 method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone9() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone9());
  }

  /**
   * Test of getPulkovo1942GKZone9N method, of class GaussKrugerPolkovo1942.
   */
  @Test
  public void testPulkovo1942GKZone9N() {
    testToWGS84AndBack(PROJ.getPulkovo1942GKZone9N());
  }

}
