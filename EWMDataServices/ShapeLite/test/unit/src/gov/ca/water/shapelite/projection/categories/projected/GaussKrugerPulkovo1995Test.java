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
public class GaussKrugerPulkovo1995Test {

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
  private static final GaussKrugerPulkovo1995 PROJ
      = Projections.getProjected().getGaussKrugerPulkovo1995();

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
   * Test of getPulkovo19953DegreeGKCM102E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM102E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM102E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM105E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM105E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM105E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM108E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM108E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM108E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM111E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM111E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM111E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM114E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM114E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM114E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM117E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM117E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM117E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM120E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM120E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM120E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM123E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM123E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM123E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM126E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM126E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM126E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM129E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM129E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM129E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM132E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM132E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM132E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM135E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM135E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM135E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM138E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM138E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM138E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM141E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM141E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM141E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM144E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM144E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM144E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM147E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM147E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM147E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM150E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM150E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM150E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM153E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM153E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM153E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM156E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM156E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM156E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM159E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM159E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM159E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM162E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM162E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM162E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM165E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM165E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM165E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM168E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM168E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM168E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM168W method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM168W() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM168W());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM171E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM171E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM171E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM171W method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM171W() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM171W());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM174E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM174E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM174E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM174W method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM174W() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM174W());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM177E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM177E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM177E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM177W method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM177W() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM177W());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM180E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM180E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM180E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM21E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM21E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM21E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM24E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM24E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM24E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM27E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM27E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM27E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM30E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM30E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM30E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM33E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM33E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM33E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM36E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM36E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM36E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM39E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM39E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM39E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM42E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM42E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM42E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM45E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM45E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM45E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM48E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM48E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM48E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM51E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM51E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM51E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM54E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM54E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM54E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM57E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM57E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM57E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM60E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM60E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM60E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM63E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM63E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM63E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM66E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM66E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM66E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM69E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM69E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM69E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM72E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM72E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM72E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM75E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM75E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM75E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM78E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM78E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM78E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM81E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM81E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM81E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM84E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM84E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM84E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM87E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM87E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM87E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM90E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM90E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM90E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM93E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM93E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM93E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM96E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM96E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM96E());
  }

  /**
   * Test of getPulkovo19953DegreeGKCM99E method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKCM99E() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKCM99E());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone10 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone10() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone10());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone11 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone11() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone11());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone12 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone12() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone12());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone13 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone13() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone13());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone14 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone14() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone14());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone15 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone15() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone15());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone16 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone16() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone16());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone17 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone17() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone17());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone18 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone18() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone18());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone19 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone19() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone19());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone20 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone20() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone20());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone21 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone21() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone21());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone22 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone22() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone22());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone23 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone23() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone23());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone24 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone24() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone24());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone25 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone25() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone25());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone26 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone26() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone26());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone27 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone27() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone27());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone28 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone28() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone28());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone29 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone29() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone29());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone30 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone30() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone30());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone31 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone31() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone31());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone32 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone32() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone32());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone33 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone33() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone33());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone34 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone34() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone34());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone35 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone35() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone35());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone36 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone36() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone36());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone37 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone37() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone37());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone38 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone38() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone38());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone39 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone39() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone39());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone40 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone40() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone40());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone41 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone41() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone41());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone42 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone42() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone42());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone43 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone43() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone43());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone44 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone44() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone44());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone45 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone45() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone45());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone46 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone46() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone46());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone47 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone47() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone47());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone48 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone48() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone48());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone49 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone49() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone49());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone50 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone50() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone50());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone51 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone51() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone51());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone52 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone52() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone52());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone53 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone53() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone53());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone54 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone54() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone54());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone55 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone55() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone55());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone56 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone56() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone56());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone57 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone57() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone57());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone58 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone58() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone58());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone59 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone59() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone59());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone60 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone60() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone60());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone61 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone61() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone61());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone62 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone62() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone62());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone63 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone63() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone63());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone64 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone64() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone64());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone7 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone7() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone7());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone8 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone8() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone8());
  }

  /**
   * Test of getPulkovo19953DegreeGKZone9 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo19953DegreeGKZone9() {
    testToWGS84AndBack(PROJ.getPulkovo19953DegreeGKZone9());
  }

  /**
   * Test of getPulkovo1995GKZone10 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone10() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone10());
  }

  /**
   * Test of getPulkovo1995GKZone10N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone10N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone10N());
  }

  /**
   * Test of getPulkovo1995GKZone11 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone11() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone11());
  }

  /**
   * Test of getPulkovo1995GKZone11N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone11N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone11N());
  }

  /**
   * Test of getPulkovo1995GKZone12 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone12() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone12());
  }

  /**
   * Test of getPulkovo1995GKZone12N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone12N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone12N());
  }

  /**
   * Test of getPulkovo1995GKZone13 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone13() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone13());
  }

  /**
   * Test of getPulkovo1995GKZone13N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone13N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone13N());
  }

  /**
   * Test of getPulkovo1995GKZone14 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone14() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone14());
  }

  /**
   * Test of getPulkovo1995GKZone14N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone14N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone14N());
  }

  /**
   * Test of getPulkovo1995GKZone15 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone15() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone15());
  }

  /**
   * Test of getPulkovo1995GKZone15N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone15N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone15N());
  }

  /**
   * Test of getPulkovo1995GKZone16 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone16() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone16());
  }

  /**
   * Test of getPulkovo1995GKZone16N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone16N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone16N());
  }

  /**
   * Test of getPulkovo1995GKZone17 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone17() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone17());
  }

  /**
   * Test of getPulkovo1995GKZone17N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone17N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone17N());
  }

  /**
   * Test of getPulkovo1995GKZone18 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone18() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone18());
  }

  /**
   * Test of getPulkovo1995GKZone18N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone18N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone18N());
  }

  /**
   * Test of getPulkovo1995GKZone19 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone19() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone19());
  }

  /**
   * Test of getPulkovo1995GKZone19N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone19N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone19N());
  }

  /**
   * Test of getPulkovo1995GKZone2 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone2() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone2());
  }

  /**
   * Test of getPulkovo1995GKZone20 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone20() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone20());
  }

  /**
   * Test of getPulkovo1995GKZone20N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone20N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone20N());
  }

  /**
   * Test of getPulkovo1995GKZone21 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone21() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone21());
  }

  /**
   * Test of getPulkovo1995GKZone21N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone21N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone21N());
  }

  /**
   * Test of getPulkovo1995GKZone22 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone22() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone22());
  }

  /**
   * Test of getPulkovo1995GKZone22N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone22N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone22N());
  }

  /**
   * Test of getPulkovo1995GKZone23 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone23() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone23());
  }

  /**
   * Test of getPulkovo1995GKZone23N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone23N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone23N());
  }

  /**
   * Test of getPulkovo1995GKZone24 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone24() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone24());
  }

  /**
   * Test of getPulkovo1995GKZone24N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone24N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone24N());
  }

  /**
   * Test of getPulkovo1995GKZone25 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone25() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone25());
  }

  /**
   * Test of getPulkovo1995GKZone25N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone25N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone25N());
  }

  /**
   * Test of getPulkovo1995GKZone26 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone26() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone26());
  }

  /**
   * Test of getPulkovo1995GKZone26N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone26N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone26N());
  }

  /**
   * Test of getPulkovo1995GKZone27 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone27() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone27());
  }

  /**
   * Test of getPulkovo1995GKZone27N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone27N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone27N());
  }

  /**
   * Test of getPulkovo1995GKZone28 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone28() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone28());
  }

  /**
   * Test of getPulkovo1995GKZone28N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone28N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone28N());
  }

  /**
   * Test of getPulkovo1995GKZone29 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone29() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone29());
  }

  /**
   * Test of getPulkovo1995GKZone29N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone29N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone29N());
  }

  /**
   * Test of getPulkovo1995GKZone2N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone2N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone2N());
  }

  /**
   * Test of getPulkovo1995GKZone3 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone3() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone3());
  }

  /**
   * Test of getPulkovo1995GKZone30 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone30() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone30());
  }

  /**
   * Test of getPulkovo1995GKZone30N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone30N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone30N());
  }

  /**
   * Test of getPulkovo1995GKZone31 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone31() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone31());
  }

  /**
   * Test of getPulkovo1995GKZone31N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone31N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone31N());
  }

  /**
   * Test of getPulkovo1995GKZone32 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone32() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone32());
  }

  /**
   * Test of getPulkovo1995GKZone32N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone32N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone32N());
  }

  /**
   * Test of getPulkovo1995GKZone3N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone3N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone3N());
  }

  /**
   * Test of getPulkovo1995GKZone4 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone4() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone4());
  }

  /**
   * Test of getPulkovo1995GKZone4N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone4N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone4N());
  }

  /**
   * Test of getPulkovo1995GKZone5 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone5() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone5());
  }

  /**
   * Test of getPulkovo1995GKZone5N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone5N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone5N());
  }

  /**
   * Test of getPulkovo1995GKZone6 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone6() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone6());
  }

  /**
   * Test of getPulkovo1995GKZone6N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone6N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone6N());
  }

  /**
   * Test of getPulkovo1995GKZone7 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone7() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone7());
  }

  /**
   * Test of getPulkovo1995GKZone7N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone7N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone7N());
  }

  /**
   * Test of getPulkovo1995GKZone8 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone8() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone8());
  }

  /**
   * Test of getPulkovo1995GKZone8N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone8N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone8N());
  }

  /**
   * Test of getPulkovo1995GKZone9 method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone9() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone9());
  }

  /**
   * Test of getPulkovo1995GKZone9N method, of class GaussKrugerPulkovo1995.
   */
  @Test
  public void testPulkovo1995GKZone9N() {
    testToWGS84AndBack(PROJ.getPulkovo1995GKZone9N());
  }

}
