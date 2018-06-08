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
public class AustraliaTest {

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
  private static final Australia PROJ = Projections.getGeographic().getAustralia();

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
   * Test of getAustralianGeodeticDatum1966 method, of class Australia.
   */
  @Test
  public void testGetAustralianGeodeticDatum1966() {
    testToWGS84AndBack(PROJ.getAustralianGeodeticDatum1966());
  }

  /**
   * Test of getAustralianGeodeticDatum1984 method, of class Australia.
   */
  @Test
  public void testGetAustralianGeodeticDatum1984() {
    testToWGS84AndBack(PROJ.getAustralianGeodeticDatum1984());
  }

  /**
   * Test of getChathamIslands1979 method, of class Australia.
   */
  @Test
  public void testGetChathamIslands1979() {
    testToWGS84AndBack(PROJ.getChathamIslands1979());
  }

  /**
   * Test of getGeocentricDatumofAustralia1994 method, of class Australia.
   */
  @Test
  public void testGetGeocentricDatumofAustralia1994() {
    testToWGS84AndBack(PROJ.getGeocentricDatumofAustralia1994());
  }

  /**
   * Test of getNZGD2000 method, of class Australia.
   */
  @Test
  public void testGetNZGD2000() {
    testToWGS84AndBack(PROJ.getNZGD2000());
  }

  /**
   * Test of getNewZealandGeodeticDatum1949 method, of class Australia.
   */
  @Test
  public void testGetNewZealandGeodeticDatum1949() {
    testToWGS84AndBack(PROJ.getNewZealandGeodeticDatum1949());
  }

}
