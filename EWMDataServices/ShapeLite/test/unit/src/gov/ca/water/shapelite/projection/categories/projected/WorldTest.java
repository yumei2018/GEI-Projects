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
public class WorldTest {

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
  private static final World PROJ = Projections.getProjected().getWorld();

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
   * Test of getFullerworld method, of class World.
   */
  @Test
  public void testFullerworld() {
    testToWGS84AndBack(PROJ.getFullerworld());
  }

  /**
   * Test of getGallStereographicworld method, of class World.
   */
  @Test
  public void testGallStereographicworld() {
    testToWGS84AndBack(PROJ.getGallStereographicworld());
  }

  /**
   * Test of getHammerAitoffworld method, of class World.
   */
  @Test
  public void testHammerAitoffworld() {
    testToWGS84AndBack(PROJ.getHammerAitoffworld());
  }

  /**
   * Test of getLoximuthalworld method, of class World.
   */
  @Test
  public void testLoximuthalworld() {
    testToWGS84AndBack(PROJ.getLoximuthalworld());
  }

  /**
   * Test of getMercatorworld method, of class World.
   */
  @Test
  public void testMercatorworld() {
    testToWGS84AndBack(PROJ.getMercatorworld());
  }

  /**
   * Test of getMillerCylindricalworld method, of class World.
   */
  @Test
  public void testMillerCylindricalworld() {
    testToWGS84AndBack(PROJ.getMillerCylindricalworld());
  }

  /**
   * Test of getMollweideworld method, of class World.
   */
  @Test
  public void testMollweideworld() {
    testToWGS84AndBack(PROJ.getMollweideworld());
  }

  /**
   * Test of getPlateCarreeworld method, of class World.
   */
  @Test
  public void testPlateCarreeworld() {
    testToWGS84AndBack(PROJ.getPlateCarreeworld());
  }

  /**
   * Test of getPolyconicworld method, of class World.
   */
  @Test
  public void testPolyconicworld() {
    testToWGS84AndBack(PROJ.getPolyconicworld());
  }

  /**
   * Test of getQuarticAuthalicworld method, of class World.
   */
  @Test
  public void testQuarticAuthalicworld() {
    testToWGS84AndBack(PROJ.getQuarticAuthalicworld());
  }

  /**
   * Test of getRobinsonworld method, of class World.
   */
  @Test
  public void testRobinsonworld() {
    testToWGS84AndBack(PROJ.getRobinsonworld());
  }

  /**
   * Test of getSinusoidalworld method, of class World.
   */
  @Test
  public void testSinusoidalworld() {
    testToWGS84AndBack(PROJ.getSinusoidalworld());
  }

  /**
   * Test of getTheWorldfromSpace method, of class World.
   */
  @Test
  public void testTheWorldfromSpace() {
    testToWGS84AndBack(PROJ.getTheWorldfromSpace());
  }

  /**
   * Test of getTimesworld method, of class World.
   */
  @Test
  public void testTimesworld() {
    testToWGS84AndBack(PROJ.getTimesworld());
  }

  /**
   * Test of getVanderGrintenIworld method, of class World.
   */
  @Test
  public void testVanderGrintenIworld() {
    testToWGS84AndBack(PROJ.getVanderGrintenIworld());
  }

  /**
   * Test of getVerticalPerspectiveworld method, of class World.
   */
  @Test
  public void testVerticalPerspectiveworld() {
    testToWGS84AndBack(PROJ.getVerticalPerspectiveworld());
  }

  /**
   * Test of getWebMercator method, of class World.
   */
  @Test
  public void testWebMercator() {
    testToWGS84AndBack(PROJ.getWebMercator());
  }

  /**
   * Test of getWinkelIIworld method, of class World.
   */
  @Test
  public void testWinkelIIworld() {
    testToWGS84AndBack(PROJ.getWinkelIIworld());
  }

  /**
   * Test of getWinkelIworld method, of class World.
   */
  @Test
  public void testWinkelIworld() {
    testToWGS84AndBack(PROJ.getWinkelIworld());
  }

  /**
   * Test of getWinkelTripelNGSworld method, of class World.
   */
  @Test
  public void testWinkelTripelNGSworld() {
    testToWGS84AndBack(PROJ.getWinkelTripelNGSworld());
  }

}
