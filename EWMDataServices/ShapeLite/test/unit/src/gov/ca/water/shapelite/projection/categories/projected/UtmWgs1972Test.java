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
public class UtmWgs1972Test {

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
  private static final UtmWgs1972 PROJ
      = Projections.getProjected().getUtmWgs1972();

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
   * Test of getWGS1972UTMZone10N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone10N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone10N());
  }

  /**
   * Test of getWGS1972UTMZone10S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone10S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone10S());
  }

  /**
   * Test of getWGS1972UTMZone11N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone11N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone11N());
  }

  /**
   * Test of getWGS1972UTMZone11S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone11S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone11S());
  }

  /**
   * Test of getWGS1972UTMZone12N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone12N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone12N());
  }

  /**
   * Test of getWGS1972UTMZone12S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone12S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone12S());
  }

  /**
   * Test of getWGS1972UTMZone13N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone13N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone13N());
  }

  /**
   * Test of getWGS1972UTMZone13S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone13S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone13S());
  }

  /**
   * Test of getWGS1972UTMZone14N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone14N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone14N());
  }

  /**
   * Test of getWGS1972UTMZone14S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone14S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone14S());
  }

  /**
   * Test of getWGS1972UTMZone15N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone15N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone15N());
  }

  /**
   * Test of getWGS1972UTMZone15S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone15S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone15S());
  }

  /**
   * Test of getWGS1972UTMZone16N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone16N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone16N());
  }

  /**
   * Test of getWGS1972UTMZone16S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone16S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone16S());
  }

  /**
   * Test of getWGS1972UTMZone17N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone17N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone17N());
  }

  /**
   * Test of getWGS1972UTMZone17S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone17S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone17S());
  }

  /**
   * Test of getWGS1972UTMZone18N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone18N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone18N());
  }

  /**
   * Test of getWGS1972UTMZone18S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone18S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone18S());
  }

  /**
   * Test of getWGS1972UTMZone19N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone19N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone19N());
  }

  /**
   * Test of getWGS1972UTMZone19S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone19S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone19S());
  }

  /**
   * Test of getWGS1972UTMZone1N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone1N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone1N());
  }

  /**
   * Test of getWGS1972UTMZone1S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone1S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone1S());
  }

  /**
   * Test of getWGS1972UTMZone20N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone20N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone20N());
  }

  /**
   * Test of getWGS1972UTMZone20S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone20S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone20S());
  }

  /**
   * Test of getWGS1972UTMZone21N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone21N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone21N());
  }

  /**
   * Test of getWGS1972UTMZone21S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone21S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone21S());
  }

  /**
   * Test of getWGS1972UTMZone22N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone22N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone22N());
  }

  /**
   * Test of getWGS1972UTMZone22S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone22S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone22S());
  }

  /**
   * Test of getWGS1972UTMZone23N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone23N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone23N());
  }

  /**
   * Test of getWGS1972UTMZone23S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone23S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone23S());
  }

  /**
   * Test of getWGS1972UTMZone24N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone24N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone24N());
  }

  /**
   * Test of getWGS1972UTMZone24S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone24S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone24S());
  }

  /**
   * Test of getWGS1972UTMZone25N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone25N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone25N());
  }

  /**
   * Test of getWGS1972UTMZone25S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone25S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone25S());
  }

  /**
   * Test of getWGS1972UTMZone26N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone26N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone26N());
  }

  /**
   * Test of getWGS1972UTMZone26S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone26S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone26S());
  }

  /**
   * Test of getWGS1972UTMZone27N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone27N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone27N());
  }

  /**
   * Test of getWGS1972UTMZone27S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone27S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone27S());
  }

  /**
   * Test of getWGS1972UTMZone28N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone28N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone28N());
  }

  /**
   * Test of getWGS1972UTMZone28S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone28S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone28S());
  }

  /**
   * Test of getWGS1972UTMZone29N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone29N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone29N());
  }

  /**
   * Test of getWGS1972UTMZone29S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone29S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone29S());
  }

  /**
   * Test of getWGS1972UTMZone2N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone2N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone2N());
  }

  /**
   * Test of getWGS1972UTMZone2S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone2S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone2S());
  }

  /**
   * Test of getWGS1972UTMZone30N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone30N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone30N());
  }

  /**
   * Test of getWGS1972UTMZone30S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone30S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone30S());
  }

  /**
   * Test of getWGS1972UTMZone31N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone31N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone31N());
  }

  /**
   * Test of getWGS1972UTMZone31S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone31S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone31S());
  }

  /**
   * Test of getWGS1972UTMZone32N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone32N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone32N());
  }

  /**
   * Test of getWGS1972UTMZone32S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone32S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone32S());
  }

  /**
   * Test of getWGS1972UTMZone33N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone33N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone33N());
  }

  /**
   * Test of getWGS1972UTMZone33S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone33S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone33S());
  }

  /**
   * Test of getWGS1972UTMZone34N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone34N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone34N());
  }

  /**
   * Test of getWGS1972UTMZone34S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone34S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone34S());
  }

  /**
   * Test of getWGS1972UTMZone35N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone35N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone35N());
  }

  /**
   * Test of getWGS1972UTMZone35S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone35S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone35S());
  }

  /**
   * Test of getWGS1972UTMZone36N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone36N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone36N());
  }

  /**
   * Test of getWGS1972UTMZone36S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone36S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone36S());
  }

  /**
   * Test of getWGS1972UTMZone37N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone37N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone37N());
  }

  /**
   * Test of getWGS1972UTMZone37S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone37S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone37S());
  }

  /**
   * Test of getWGS1972UTMZone38N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone38N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone38N());
  }

  /**
   * Test of getWGS1972UTMZone38S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone38S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone38S());
  }

  /**
   * Test of getWGS1972UTMZone39N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone39N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone39N());
  }

  /**
   * Test of getWGS1972UTMZone39S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone39S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone39S());
  }

  /**
   * Test of getWGS1972UTMZone3N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone3N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone3N());
  }

  /**
   * Test of getWGS1972UTMZone3S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone3S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone3S());
  }

  /**
   * Test of getWGS1972UTMZone40N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone40N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone40N());
  }

  /**
   * Test of getWGS1972UTMZone40S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone40S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone40S());
  }

  /**
   * Test of getWGS1972UTMZone41N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone41N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone41N());
  }

  /**
   * Test of getWGS1972UTMZone41S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone41S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone41S());
  }

  /**
   * Test of getWGS1972UTMZone42N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone42N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone42N());
  }

  /**
   * Test of getWGS1972UTMZone42S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone42S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone42S());
  }

  /**
   * Test of getWGS1972UTMZone43N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone43N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone43N());
  }

  /**
   * Test of getWGS1972UTMZone43S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone43S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone43S());
  }

  /**
   * Test of getWGS1972UTMZone44N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone44N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone44N());
  }

  /**
   * Test of getWGS1972UTMZone44S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone44S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone44S());
  }

  /**
   * Test of getWGS1972UTMZone45N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone45N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone45N());
  }

  /**
   * Test of getWGS1972UTMZone45S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone45S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone45S());
  }

  /**
   * Test of getWGS1972UTMZone46N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone46N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone46N());
  }

  /**
   * Test of getWGS1972UTMZone46S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone46S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone46S());
  }

  /**
   * Test of getWGS1972UTMZone47N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone47N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone47N());
  }

  /**
   * Test of getWGS1972UTMZone47S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone47S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone47S());
  }

  /**
   * Test of getWGS1972UTMZone48N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone48N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone48N());
  }

  /**
   * Test of getWGS1972UTMZone48S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone48S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone48S());
  }

  /**
   * Test of getWGS1972UTMZone49N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone49N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone49N());
  }

  /**
   * Test of getWGS1972UTMZone49S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone49S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone49S());
  }

  /**
   * Test of getWGS1972UTMZone4N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone4N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone4N());
  }

  /**
   * Test of getWGS1972UTMZone4S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone4S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone4S());
  }

  /**
   * Test of getWGS1972UTMZone50N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone50N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone50N());
  }

  /**
   * Test of getWGS1972UTMZone50S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone50S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone50S());
  }

  /**
   * Test of getWGS1972UTMZone51N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone51N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone51N());
  }

  /**
   * Test of getWGS1972UTMZone51S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone51S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone51S());
  }

  /**
   * Test of getWGS1972UTMZone52N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone52N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone52N());
  }

  /**
   * Test of getWGS1972UTMZone52S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone52S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone52S());
  }

  /**
   * Test of getWGS1972UTMZone53N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone53N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone53N());
  }

  /**
   * Test of getWGS1972UTMZone53S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone53S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone53S());
  }

  /**
   * Test of getWGS1972UTMZone54N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone54N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone54N());
  }

  /**
   * Test of getWGS1972UTMZone54S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone54S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone54S());
  }

  /**
   * Test of getWGS1972UTMZone55N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone55N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone55N());
  }

  /**
   * Test of getWGS1972UTMZone55S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone55S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone55S());
  }

  /**
   * Test of getWGS1972UTMZone56N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone56N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone56N());
  }

  /**
   * Test of getWGS1972UTMZone56S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone56S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone56S());
  }

  /**
   * Test of getWGS1972UTMZone57N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone57N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone57N());
  }

  /**
   * Test of getWGS1972UTMZone57S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone57S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone57S());
  }

  /**
   * Test of getWGS1972UTMZone58N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone58N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone58N());
  }

  /**
   * Test of getWGS1972UTMZone58S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone58S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone58S());
  }

  /**
   * Test of getWGS1972UTMZone59N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone59N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone59N());
  }

  /**
   * Test of getWGS1972UTMZone59S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone59S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone59S());
  }

  /**
   * Test of getWGS1972UTMZone5N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone5N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone5N());
  }

  /**
   * Test of getWGS1972UTMZone5S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone5S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone5S());
  }

  /**
   * Test of getWGS1972UTMZone60N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone60N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone60N());
  }

  /**
   * Test of getWGS1972UTMZone60S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone60S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone60S());
  }

  /**
   * Test of getWGS1972UTMZone6N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone6N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone6N());
  }

  /**
   * Test of getWGS1972UTMZone6S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone6S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone6S());
  }

  /**
   * Test of getWGS1972UTMZone7N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone7N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone7N());
  }

  /**
   * Test of getWGS1972UTMZone7S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone7S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone7S());
  }

  /**
   * Test of getWGS1972UTMZone8N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone8N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone8N());
  }

  /**
   * Test of getWGS1972UTMZone8S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone8S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone8S());
  }

  /**
   * Test of getWGS1972UTMZone9N method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone9N() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone9N());
  }

  /**
   * Test of getWGS1972UTMZone9S method, of class UtmWgs1972.
   */
  @Test
  public void testWGS1972UTMZone9S() {
    testToWGS84AndBack(PROJ.getWGS1972UTMZone9S());
  }

}
