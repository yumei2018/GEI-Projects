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
public class UtmWgs1984Test {

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
  private static final UtmWgs1984 PROJ = Projections.getProjected().getUtmWgs1984();

  /**
   * Tests converting values to WGS84 and back.
   *
   * @param projection The ProjectionInfo to test.
   */
  public void testToWGS84AndBack(ProjectionInfo projection) {

    try {
      Coord original = new CoordXY(500000, 5000000);
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
   * Test of getWGS1984ComplexUTMZone20N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984ComplexUTMZone20N() {
    testToWGS84AndBack(PROJ.getWGS1984ComplexUTMZone20N());
  }

  /**
   * Test of getWGS1984ComplexUTMZone21N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984ComplexUTMZone21N() {
    testToWGS84AndBack(PROJ.getWGS1984ComplexUTMZone21N());
  }

  /**
   * Test of getWGS1984ComplexUTMZone22N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984ComplexUTMZone22N() {
    testToWGS84AndBack(PROJ.getWGS1984ComplexUTMZone22N());
  }

  /**
   * Test of getWGS1984ComplexUTMZone23N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984ComplexUTMZone23N() {
    testToWGS84AndBack(PROJ.getWGS1984ComplexUTMZone23N());
  }

  /**
   * Test of getWGS1984ComplexUTMZone24N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984ComplexUTMZone24N() {
    testToWGS84AndBack(PROJ.getWGS1984ComplexUTMZone24N());
  }

  /**
   * Test of getWGS1984ComplexUTMZone25N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984ComplexUTMZone25N() {
    testToWGS84AndBack(PROJ.getWGS1984ComplexUTMZone25N());
  }

  /**
   * Test of getWGS1984ComplexUTMZone26N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984ComplexUTMZone26N() {
    testToWGS84AndBack(PROJ.getWGS1984ComplexUTMZone26N());
  }

  /**
   * Test of getWGS1984ComplexUTMZone27N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984ComplexUTMZone27N() {
    testToWGS84AndBack(PROJ.getWGS1984ComplexUTMZone27N());
  }

  /**
   * Test of getWGS1984ComplexUTMZone28N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984ComplexUTMZone28N() {
    testToWGS84AndBack(PROJ.getWGS1984ComplexUTMZone28N());
  }

  /**
   * Test of getWGS1984ComplexUTMZone29N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984ComplexUTMZone29N() {
    testToWGS84AndBack(PROJ.getWGS1984ComplexUTMZone29N());
  }

  /**
   * Test of getWGS1984ComplexUTMZone30N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984ComplexUTMZone30N() {
    testToWGS84AndBack(PROJ.getWGS1984ComplexUTMZone30N());
  }

  /**
   * Test of getWGS1984UTMZone10N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone10N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone10N());
  }

  /**
   * Test of getWGS1984UTMZone10S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone10S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone10S());
  }

  /**
   * Test of getWGS1984UTMZone11N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone11N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone11N());
  }

  /**
   * Test of getWGS1984UTMZone11S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone11S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone11S());
  }

  /**
   * Test of getWGS1984UTMZone12N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone12N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone12N());
  }

  /**
   * Test of getWGS1984UTMZone12S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone12S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone12S());
  }

  /**
   * Test of getWGS1984UTMZone13N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone13N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone13N());
  }

  /**
   * Test of getWGS1984UTMZone13S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone13S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone13S());
  }

  /**
   * Test of getWGS1984UTMZone14N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone14N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone14N());
  }

  /**
   * Test of getWGS1984UTMZone14S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone14S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone14S());
  }

  /**
   * Test of getWGS1984UTMZone15N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone15N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone15N());
  }

  /**
   * Test of getWGS1984UTMZone15S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone15S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone15S());
  }

  /**
   * Test of getWGS1984UTMZone16N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone16N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone16N());
  }

  /**
   * Test of getWGS1984UTMZone16S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone16S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone16S());
  }

  /**
   * Test of getWGS1984UTMZone17N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone17N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone17N());
  }

  /**
   * Test of getWGS1984UTMZone17S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone17S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone17S());
  }

  /**
   * Test of getWGS1984UTMZone18N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone18N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone18N());
  }

  /**
   * Test of getWGS1984UTMZone18S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone18S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone18S());
  }

  /**
   * Test of getWGS1984UTMZone19N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone19N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone19N());
  }

  /**
   * Test of getWGS1984UTMZone19S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone19S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone19S());
  }

  /**
   * Test of getWGS1984UTMZone1N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone1N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone1N());
  }

  /**
   * Test of getWGS1984UTMZone1S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone1S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone1S());
  }

  /**
   * Test of getWGS1984UTMZone20N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone20N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone20N());
  }

  /**
   * Test of getWGS1984UTMZone20S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone20S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone20S());
  }

  /**
   * Test of getWGS1984UTMZone21N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone21N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone21N());
  }

  /**
   * Test of getWGS1984UTMZone21S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone21S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone21S());
  }

  /**
   * Test of getWGS1984UTMZone22N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone22N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone22N());
  }

  /**
   * Test of getWGS1984UTMZone22S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone22S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone22S());
  }

  /**
   * Test of getWGS1984UTMZone23N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone23N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone23N());
  }

  /**
   * Test of getWGS1984UTMZone23S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone23S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone23S());
  }

  /**
   * Test of getWGS1984UTMZone24N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone24N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone24N());
  }

  /**
   * Test of getWGS1984UTMZone24S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone24S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone24S());
  }

  /**
   * Test of getWGS1984UTMZone25N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone25N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone25N());
  }

  /**
   * Test of getWGS1984UTMZone25S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone25S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone25S());
  }

  /**
   * Test of getWGS1984UTMZone26N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone26N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone26N());
  }

  /**
   * Test of getWGS1984UTMZone26S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone26S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone26S());
  }

  /**
   * Test of getWGS1984UTMZone27N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone27N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone27N());
  }

  /**
   * Test of getWGS1984UTMZone27S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone27S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone27S());
  }

  /**
   * Test of getWGS1984UTMZone28N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone28N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone28N());
  }

  /**
   * Test of getWGS1984UTMZone28S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone28S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone28S());
  }

  /**
   * Test of getWGS1984UTMZone29N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone29N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone29N());
  }

  /**
   * Test of getWGS1984UTMZone29S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone29S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone29S());
  }

  /**
   * Test of getWGS1984UTMZone2N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone2N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone2N());
  }

  /**
   * Test of getWGS1984UTMZone2S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone2S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone2S());
  }

  /**
   * Test of getWGS1984UTMZone30N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone30N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone30N());
  }

  /**
   * Test of getWGS1984UTMZone30S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone30S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone30S());
  }

  /**
   * Test of getWGS1984UTMZone31N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone31N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone31N());
  }

  /**
   * Test of getWGS1984UTMZone31S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone31S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone31S());
  }

  /**
   * Test of getWGS1984UTMZone32N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone32N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone32N());
  }

  /**
   * Test of getWGS1984UTMZone32S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone32S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone32S());
  }

  /**
   * Test of getWGS1984UTMZone33N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone33N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone33N());
  }

  /**
   * Test of getWGS1984UTMZone33S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone33S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone33S());
  }

  /**
   * Test of getWGS1984UTMZone34N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone34N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone34N());
  }

  /**
   * Test of getWGS1984UTMZone34S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone34S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone34S());
  }

  /**
   * Test of getWGS1984UTMZone35N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone35N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone35N());
  }

  /**
   * Test of getWGS1984UTMZone35S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone35S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone35S());
  }

  /**
   * Test of getWGS1984UTMZone36N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone36N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone36N());
  }

  /**
   * Test of getWGS1984UTMZone36S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone36S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone36S());
  }

  /**
   * Test of getWGS1984UTMZone37N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone37N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone37N());
  }

  /**
   * Test of getWGS1984UTMZone37S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone37S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone37S());
  }

  /**
   * Test of getWGS1984UTMZone38N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone38N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone38N());
  }

  /**
   * Test of getWGS1984UTMZone38S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone38S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone38S());
  }

  /**
   * Test of getWGS1984UTMZone39N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone39N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone39N());
  }

  /**
   * Test of getWGS1984UTMZone39S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone39S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone39S());
  }

  /**
   * Test of getWGS1984UTMZone3N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone3N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone3N());
  }

  /**
   * Test of getWGS1984UTMZone3S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone3S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone3S());
  }

  /**
   * Test of getWGS1984UTMZone40N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone40N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone40N());
  }

  /**
   * Test of getWGS1984UTMZone40S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone40S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone40S());
  }

  /**
   * Test of getWGS1984UTMZone41N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone41N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone41N());
  }

  /**
   * Test of getWGS1984UTMZone41S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone41S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone41S());
  }

  /**
   * Test of getWGS1984UTMZone42N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone42N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone42N());
  }

  /**
   * Test of getWGS1984UTMZone42S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone42S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone42S());
  }

  /**
   * Test of getWGS1984UTMZone43N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone43N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone43N());
  }

  /**
   * Test of getWGS1984UTMZone43S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone43S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone43S());
  }

  /**
   * Test of getWGS1984UTMZone44N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone44N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone44N());
  }

  /**
   * Test of getWGS1984UTMZone44S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone44S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone44S());
  }

  /**
   * Test of getWGS1984UTMZone45N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone45N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone45N());
  }

  /**
   * Test of getWGS1984UTMZone45S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone45S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone45S());
  }

  /**
   * Test of getWGS1984UTMZone46N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone46N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone46N());
  }

  /**
   * Test of getWGS1984UTMZone46S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone46S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone46S());
  }

  /**
   * Test of getWGS1984UTMZone47N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone47N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone47N());
  }

  /**
   * Test of getWGS1984UTMZone47S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone47S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone47S());
  }

  /**
   * Test of getWGS1984UTMZone48N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone48N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone48N());
  }

  /**
   * Test of getWGS1984UTMZone48S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone48S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone48S());
  }

  /**
   * Test of getWGS1984UTMZone49N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone49N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone49N());
  }

  /**
   * Test of getWGS1984UTMZone49S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone49S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone49S());
  }

  /**
   * Test of getWGS1984UTMZone4N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone4N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone4N());
  }

  /**
   * Test of getWGS1984UTMZone4S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone4S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone4S());
  }

  /**
   * Test of getWGS1984UTMZone50N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone50N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone50N());
  }

  /**
   * Test of getWGS1984UTMZone50S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone50S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone50S());
  }

  /**
   * Test of getWGS1984UTMZone51N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone51N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone51N());
  }

  /**
   * Test of getWGS1984UTMZone51S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone51S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone51S());
  }

  /**
   * Test of getWGS1984UTMZone52N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone52N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone52N());
  }

  /**
   * Test of getWGS1984UTMZone52S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone52S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone52S());
  }

  /**
   * Test of getWGS1984UTMZone53N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone53N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone53N());
  }

  /**
   * Test of getWGS1984UTMZone53S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone53S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone53S());
  }

  /**
   * Test of getWGS1984UTMZone54N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone54N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone54N());
  }

  /**
   * Test of getWGS1984UTMZone54S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone54S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone54S());
  }

  /**
   * Test of getWGS1984UTMZone55N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone55N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone55N());
  }

  /**
   * Test of getWGS1984UTMZone55S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone55S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone55S());
  }

  /**
   * Test of getWGS1984UTMZone56N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone56N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone56N());
  }

  /**
   * Test of getWGS1984UTMZone56S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone56S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone56S());
  }

  /**
   * Test of getWGS1984UTMZone57N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone57N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone57N());
  }

  /**
   * Test of getWGS1984UTMZone57S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone57S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone57S());
  }

  /**
   * Test of getWGS1984UTMZone58N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone58N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone58N());
  }

  /**
   * Test of getWGS1984UTMZone58S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone58S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone58S());
  }

  /**
   * Test of getWGS1984UTMZone59N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone59N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone59N());
  }

  /**
   * Test of getWGS1984UTMZone59S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone59S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone59S());
  }

  /**
   * Test of getWGS1984UTMZone5N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone5N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone5N());
  }

  /**
   * Test of getWGS1984UTMZone5S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone5S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone5S());
  }

  /**
   * Test of getWGS1984UTMZone60N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone60N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone60N());
  }

  /**
   * Test of getWGS1984UTMZone60S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone60S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone60S());
  }

  /**
   * Test of getWGS1984UTMZone6N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone6N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone6N());
  }

  /**
   * Test of getWGS1984UTMZone6S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone6S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone6S());
  }

  /**
   * Test of getWGS1984UTMZone7N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone7N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone7N());
  }

  /**
   * Test of getWGS1984UTMZone7S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone7S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone7S());
  }

  /**
   * Test of getWGS1984UTMZone8N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone8N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone8N());
  }

  /**
   * Test of getWGS1984UTMZone8S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone8S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone8S());
  }

  /**
   * Test of getWGS1984UTMZone9N method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone9N() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone9N());
  }

  /**
   * Test of getWGS1984UTMZone9S method, of class UtmWgs1984Test.
   */
  @Test
  public void testWGS1984UTMZone9S() {
    testToWGS84AndBack(PROJ.getWGS1984UTMZone9S());
  }

}
