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
package gov.ca.water.shapelite.projection.categories.geographic;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import gov.ca.water.shapelite.projection.categories.projected.UtmOtherTest;
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
   * Since Geographic coordinates are in decimal degree, this should be a small
   * number. The value 0.00000001 represents about 1 mm.
   */
  private static final double TOLERANCE = 0.00000001;

  /**
   * The WGS84 projection, which is used to test going to geographic coordinates
   * and back.
   */
  private static final ProjectionInfo WGS84
      = Projections.getGeographic().getWorld().getWGS1984();

  /**
   * The UtmOther projection coordinate system list.
   */
  private static final World PROJ = Projections.getGeographic().getWorld();

  /**
   * Tests converting values to WGS84 and back.
   *
   * @param projection The ProjectionInfo to test.
   */
  public void testToWGS84AndBack(ProjectionInfo projection) {

    try {
      Coord original = new CoordXY(10, 10);
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
   * Test of getGRS1980 method, of class World.
   */
  @Test
  public void testGetGRS1980() {
    testToWGS84AndBack(PROJ.getGRS1980());
  }

  /**
   * Test of getITRF1989 method, of class World.
   */
  @Test
  public void testGetITRF1989() {
    testToWGS84AndBack(PROJ.getITRF1989());
  }

  /**
   * Test of getITRF1990 method, of class World.
   */
  @Test
  public void testGetITRF1990() {
    testToWGS84AndBack(PROJ.getITRF1990());
  }

  /**
   * Test of getITRF1991 method, of class World.
   */
  @Test
  public void testGetITRF1991() {
    testToWGS84AndBack(PROJ.getITRF1991());
  }

  /**
   * Test of getITRF1992 method, of class World.
   */
  @Test
  public void testGetITRF1992() {
    testToWGS84AndBack(PROJ.getITRF1992());
  }

  /**
   * Test of getITRF1993 method, of class World.
   */
  @Test
  public void testGetITRF1993() {
    testToWGS84AndBack(PROJ.getITRF1993());
  }

  /**
   * Test of getITRF1994 method, of class World.
   */
  @Test
  public void testGetITRF1994() {
    testToWGS84AndBack(PROJ.getITRF1994());
  }

  /**
   * Test of getITRF1996 method, of class World.
   */
  @Test
  public void testGetITRF1996() {
    testToWGS84AndBack(PROJ.getITRF1996());
  }

  /**
   * Test of getITRF1997 method, of class World.
   */
  @Test
  public void testGetITRF1997() {
    testToWGS84AndBack(PROJ.getITRF1997());
  }

  /**
   * Test of getITRF2000 method, of class World.
   */
  @Test
  public void testGetITRF2000() {
    testToWGS84AndBack(PROJ.getITRF2000());
  }

  /**
   * Test of getNSWC9Z2 method, of class World.
   */
  @Test
  public void testGetNSWC9Z2() {
    testToWGS84AndBack(PROJ.getNSWC9Z2());
  }

  /**
   * Test of getWGS1966 method, of class World.
   */
  @Test
  public void testGetWGS1966() {
    testToWGS84AndBack(PROJ.getWGS1966());
  }

  /**
   * Test of getWGS1972 method, of class World.
   */
  @Test
  public void testGetWGS1972() {
    testToWGS84AndBack(PROJ.getWGS1972());
  }

  /**
   * Test of getWGS1972TBE method, of class World.
   */
  @Test
  public void testGetWGS1972TBE() {
    testToWGS84AndBack(PROJ.getWGS1972TBE());
  }

  /**
   * Test of getWGS1984 method, of class World.
   */
  @Test
  public void testGetWGS1984() {
    testToWGS84AndBack(PROJ.getWGS1984());
  }

}
