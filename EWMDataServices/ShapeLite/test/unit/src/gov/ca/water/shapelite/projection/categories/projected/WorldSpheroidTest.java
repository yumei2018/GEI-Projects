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
public class WorldSpheroidTest {

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
  private static final WorldSpheroid PROJ = Projections.getProjected().getWorldSpheroid();

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
   * Test of getAitoffsphere method, of class WorldSpheroid.
   */
  @Test
  public void testAitoffsphere() {
    testToWGS84AndBack(PROJ.getAitoffsphere());
  }

  /**
   * Test of getBehrmannsphere method, of class WorldSpheroid.
   */
  @Test
  public void testBehrmannsphere() {
    testToWGS84AndBack(PROJ.getBehrmannsphere());
  }

  /**
   * Test of getBonnesphere method, of class WorldSpheroid.
   */
  @Test
  public void testBonnesphere() {
    testToWGS84AndBack(PROJ.getBonnesphere());
  }

  /**
   * Test of getCrasterParabolicsphere method, of class WorldSpheroid.
   */
  @Test
  public void testCrasterParabolicsphere() {
    testToWGS84AndBack(PROJ.getCrasterParabolicsphere());
  }

  /**
   * Test of getCylindricalEqualAreasphere method, of class WorldSpheroid.
   */
  @Test
  public void testCylindricalEqualAreasphere() {
    testToWGS84AndBack(PROJ.getCylindricalEqualAreasphere());
  }

  /**
   * Test of getEckertIIIsphere method, of class WorldSpheroid.
   */
  @Test
  public void testEckertIIIsphere() {
    testToWGS84AndBack(PROJ.getEckertIIIsphere());
  }

  /**
   * Test of getEckertIIsphere method, of class WorldSpheroid.
   */
  @Test
  public void testEckertIIsphere() {
    testToWGS84AndBack(PROJ.getEckertIIsphere());
  }

  /**
   * Test of getEckertIVsphere method, of class WorldSpheroid.
   */
  @Test
  public void testEckertIVsphere() {
    testToWGS84AndBack(PROJ.getEckertIVsphere());
  }

  /**
   * Test of getEckertIsphere method, of class WorldSpheroid.
   */
  @Test
  public void testEckertIsphere() {
    testToWGS84AndBack(PROJ.getEckertIsphere());
  }

  /**
   * Test of getEckertVIsphere method, of class WorldSpheroid.
   */
  @Test
  public void testEckertVIsphere() {
    testToWGS84AndBack(PROJ.getEckertVIsphere());
  }

  /**
   * Test of getEckertVsphere method, of class WorldSpheroid.
   */
  @Test
  public void testEckertVsphere() {
    testToWGS84AndBack(PROJ.getEckertVsphere());
  }

  /**
   * Test of getEquidistantConicsphere method, of class WorldSpheroid.
   */
  @Test
  public void testEquidistantConicsphere() {
    testToWGS84AndBack(PROJ.getEquidistantConicsphere());
  }

  /**
   * Test of getEquidistantCylindricalsphere method, of class WorldSpheroid.
   */
  @Test
  public void testEquidistantCylindricalsphere() {
    testToWGS84AndBack(PROJ.getEquidistantCylindricalsphere());
  }

  /**
   * Test of getFlatPolarQuarticsphere method, of class WorldSpheroid.
   */
  @Test
  public void testFlatPolarQuarticsphere() {
    testToWGS84AndBack(PROJ.getFlatPolarQuarticsphere());
  }

  /**
   * Test of getGallStereographicsphere method, of class WorldSpheroid.
   */
  @Test
  public void testGallStereographicsphere() {
    testToWGS84AndBack(PROJ.getGallStereographicsphere());
  }

  /**
   * Test of getHammerAitoffsphere method, of class WorldSpheroid.
   */
  @Test
  public void testHammerAitoffsphere() {
    testToWGS84AndBack(PROJ.getHammerAitoffsphere());
  }

  /**
   * Test of getLoximuthalsphere method, of class WorldSpheroid.
   */
  @Test
  public void testLoximuthalsphere() {
    testToWGS84AndBack(PROJ.getLoximuthalsphere());
  }

  /**
   * Test of getMercatorsphere method, of class WorldSpheroid.
   */
  @Test
  public void testMercatorsphere() {
    testToWGS84AndBack(PROJ.getMercatorsphere());
  }

  /**
   * Test of getMillerCylindricalsphere method, of class WorldSpheroid.
   */
  @Test
  public void testMillerCylindricalsphere() {
    testToWGS84AndBack(PROJ.getMillerCylindricalsphere());
  }

  /**
   * Test of getMollweidesphere method, of class WorldSpheroid.
   */
  @Test
  public void testMollweidesphere() {
    testToWGS84AndBack(PROJ.getMollweidesphere());
  }

  /**
   * Test of getPlateCarreesphere method, of class WorldSpheroid.
   */
  @Test
  public void testPlateCarreesphere() {
    testToWGS84AndBack(PROJ.getPlateCarreesphere());
  }

  /**
   * Test of getPolyconicsphere method, of class WorldSpheroid.
   */
  @Test
  public void testPolyconicsphere() {
    testToWGS84AndBack(PROJ.getPolyconicsphere());
  }

  /**
   * Test of getQuarticAuthalicsphere method, of class WorldSpheroid.
   */
  @Test
  public void testQuarticAuthalicsphere() {
    testToWGS84AndBack(PROJ.getQuarticAuthalicsphere());
  }

  /**
   * Test of getRobinsonsphere method, of class WorldSpheroid.
   */
  @Test
  public void testRobinsonsphere() {
    testToWGS84AndBack(PROJ.getRobinsonsphere());
  }

  /**
   * Test of getSinusoidalsphere method, of class WorldSpheroid.
   */
  @Test
  public void testSinusoidalsphere() {
    testToWGS84AndBack(PROJ.getSinusoidalsphere());
  }

  /**
   * Test of getTimessphere method, of class WorldSpheroid.
   */
  @Test
  public void testTimessphere() {
    testToWGS84AndBack(PROJ.getTimessphere());
  }

  /**
   * Test of getVanderGrintenIsphere method, of class WorldSpheroid.
   */
  @Test
  public void testVanderGrintenIsphere() {
    testToWGS84AndBack(PROJ.getVanderGrintenIsphere());
  }

  /**
   * Test of getVerticalPerspectivesphere method, of class WorldSpheroid.
   */
  @Test
  public void testVerticalPerspectivesphere() {
    testToWGS84AndBack(PROJ.getVerticalPerspectivesphere());
  }

  /**
   * Test of getWinkelIIsphere method, of class WorldSpheroid.
   */
  @Test
  public void testWinkelIIsphere() {
    testToWGS84AndBack(PROJ.getWinkelIIsphere());
  }

  /**
   * Test of getWinkelIsphere method, of class WorldSpheroid.
   */
  @Test
  public void testWinkelIsphere() {
    testToWGS84AndBack(PROJ.getWinkelIsphere());
  }

  /**
   * Test of getWinkelTripelNGSsphere method, of class WorldSpheroid.
   */
  @Test
  public void testWinkelTripelNGSsphere() {
    testToWGS84AndBack(PROJ.getWinkelTripelNGSsphere());
  }

}
