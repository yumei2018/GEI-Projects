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
public class UtmNad1983Test {

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
  private static final UtmNad1983 PROJ = Projections.getProjected().getUtmNad1983();

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
   * Test of getNAD1983UTMZone10N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone10N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone10N());
  }

  /**
   * Test of getNAD1983UTMZone10S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone10S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone10S());
  }

  /**
   * Test of getNAD1983UTMZone11N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone11N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone11N());
  }

  /**
   * Test of getNAD1983UTMZone11S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone11S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone11S());
  }

  /**
   * Test of getNAD1983UTMZone12N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone12N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone12N());
  }

  /**
   * Test of getNAD1983UTMZone12S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone12S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone12S());
  }

  /**
   * Test of getNAD1983UTMZone13N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone13N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone13N());
  }

  /**
   * Test of getNAD1983UTMZone13S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone13S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone13S());
  }

  /**
   * Test of getNAD1983UTMZone14N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone14N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone14N());
  }

  /**
   * Test of getNAD1983UTMZone14S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone14S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone14S());
  }

  /**
   * Test of getNAD1983UTMZone15N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone15N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone15N());
  }

  /**
   * Test of getNAD1983UTMZone15S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone15S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone15S());
  }

  /**
   * Test of getNAD1983UTMZone16N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone16N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone16N());
  }

  /**
   * Test of getNAD1983UTMZone16S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone16S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone16S());
  }

  /**
   * Test of getNAD1983UTMZone17N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone17N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone17N());
  }

  /**
   * Test of getNAD1983UTMZone17S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone17S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone17S());
  }

  /**
   * Test of getNAD1983UTMZone18N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone18N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone18N());
  }

  /**
   * Test of getNAD1983UTMZone18S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone18S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone18S());
  }

  /**
   * Test of getNAD1983UTMZone19N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone19N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone19N());
  }

  /**
   * Test of getNAD1983UTMZone19S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone19S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone19S());
  }

  /**
   * Test of getNAD1983UTMZone1N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone1N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone1N());
  }

  /**
   * Test of getNAD1983UTMZone1S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone1S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone1S());
  }

  /**
   * Test of getNAD1983UTMZone20N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone20N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone20N());
  }

  /**
   * Test of getNAD1983UTMZone20S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone20S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone20S());
  }

  /**
   * Test of getNAD1983UTMZone21N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone21N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone21N());
  }

  /**
   * Test of getNAD1983UTMZone21S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone21S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone21S());
  }

  /**
   * Test of getNAD1983UTMZone22N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone22N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone22N());
  }

  /**
   * Test of getNAD1983UTMZone22S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone22S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone22S());
  }

  /**
   * Test of getNAD1983UTMZone23N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone23N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone23N());
  }

  /**
   * Test of getNAD1983UTMZone23S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone23S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone23S());
  }

  /**
   * Test of getNAD1983UTMZone24N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone24N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone24N());
  }

  /**
   * Test of getNAD1983UTMZone24S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone24S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone24S());
  }

  /**
   * Test of getNAD1983UTMZone25N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone25N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone25N());
  }

  /**
   * Test of getNAD1983UTMZone25S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone25S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone25S());
  }

  /**
   * Test of getNAD1983UTMZone26N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone26N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone26N());
  }

  /**
   * Test of getNAD1983UTMZone26S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone26S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone26S());
  }

  /**
   * Test of getNAD1983UTMZone27N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone27N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone27N());
  }

  /**
   * Test of getNAD1983UTMZone27S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone27S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone27S());
  }

  /**
   * Test of getNAD1983UTMZone28N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone28N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone28N());
  }

  /**
   * Test of getNAD1983UTMZone28S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone28S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone28S());
  }

  /**
   * Test of getNAD1983UTMZone29N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone29N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone29N());
  }

  /**
   * Test of getNAD1983UTMZone29S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone29S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone29S());
  }

  /**
   * Test of getNAD1983UTMZone2N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone2N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone2N());
  }

  /**
   * Test of getNAD1983UTMZone2S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone2S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone2S());
  }

  /**
   * Test of getNAD1983UTMZone30N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone30N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone30N());
  }

  /**
   * Test of getNAD1983UTMZone30S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone30S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone30S());
  }

  /**
   * Test of getNAD1983UTMZone31N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone31N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone31N());
  }

  /**
   * Test of getNAD1983UTMZone31S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone31S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone31S());
  }

  /**
   * Test of getNAD1983UTMZone32N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone32N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone32N());
  }

  /**
   * Test of getNAD1983UTMZone32S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone32S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone32S());
  }

  /**
   * Test of getNAD1983UTMZone33N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone33N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone33N());
  }

  /**
   * Test of getNAD1983UTMZone33S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone33S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone33S());
  }

  /**
   * Test of getNAD1983UTMZone34N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone34N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone34N());
  }

  /**
   * Test of getNAD1983UTMZone34S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone34S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone34S());
  }

  /**
   * Test of getNAD1983UTMZone35N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone35N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone35N());
  }

  /**
   * Test of getNAD1983UTMZone35S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone35S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone35S());
  }

  /**
   * Test of getNAD1983UTMZone36N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone36N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone36N());
  }

  /**
   * Test of getNAD1983UTMZone36S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone36S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone36S());
  }

  /**
   * Test of getNAD1983UTMZone37N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone37N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone37N());
  }

  /**
   * Test of getNAD1983UTMZone37S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone37S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone37S());
  }

  /**
   * Test of getNAD1983UTMZone38N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone38N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone38N());
  }

  /**
   * Test of getNAD1983UTMZone38S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone38S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone38S());
  }

  /**
   * Test of getNAD1983UTMZone39N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone39N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone39N());
  }

  /**
   * Test of getNAD1983UTMZone39S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone39S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone39S());
  }

  /**
   * Test of getNAD1983UTMZone3N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone3N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone3N());
  }

  /**
   * Test of getNAD1983UTMZone3S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone3S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone3S());
  }

  /**
   * Test of getNAD1983UTMZone40N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone40N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone40N());
  }

  /**
   * Test of getNAD1983UTMZone40S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone40S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone40S());
  }

  /**
   * Test of getNAD1983UTMZone41N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone41N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone41N());
  }

  /**
   * Test of getNAD1983UTMZone41S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone41S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone41S());
  }

  /**
   * Test of getNAD1983UTMZone42N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone42N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone42N());
  }

  /**
   * Test of getNAD1983UTMZone42S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone42S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone42S());
  }

  /**
   * Test of getNAD1983UTMZone43N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone43N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone43N());
  }

  /**
   * Test of getNAD1983UTMZone43S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone43S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone43S());
  }

  /**
   * Test of getNAD1983UTMZone44N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone44N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone44N());
  }

  /**
   * Test of getNAD1983UTMZone44S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone44S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone44S());
  }

  /**
   * Test of getNAD1983UTMZone45N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone45N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone45N());
  }

  /**
   * Test of getNAD1983UTMZone45S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone45S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone45S());
  }

  /**
   * Test of getNAD1983UTMZone46N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone46N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone46N());
  }

  /**
   * Test of getNAD1983UTMZone46S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone46S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone46S());
  }

  /**
   * Test of getNAD1983UTMZone47N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone47N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone47N());
  }

  /**
   * Test of getNAD1983UTMZone47S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone47S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone47S());
  }

  /**
   * Test of getNAD1983UTMZone48N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone48N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone48N());
  }

  /**
   * Test of getNAD1983UTMZone48S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone48S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone48S());
  }

  /**
   * Test of getNAD1983UTMZone49N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone49N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone49N());
  }

  /**
   * Test of getNAD1983UTMZone49S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone49S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone49S());
  }

  /**
   * Test of getNAD1983UTMZone4N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone4N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone4N());
  }

  /**
   * Test of getNAD1983UTMZone4S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone4S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone4S());
  }

  /**
   * Test of getNAD1983UTMZone50N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone50N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone50N());
  }

  /**
   * Test of getNAD1983UTMZone50S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone50S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone50S());
  }

  /**
   * Test of getNAD1983UTMZone51N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone51N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone51N());
  }

  /**
   * Test of getNAD1983UTMZone51S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone51S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone51S());
  }

  /**
   * Test of getNAD1983UTMZone52N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone52N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone52N());
  }

  /**
   * Test of getNAD1983UTMZone52S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone52S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone52S());
  }

  /**
   * Test of getNAD1983UTMZone53N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone53N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone53N());
  }

  /**
   * Test of getNAD1983UTMZone53S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone53S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone53S());
  }

  /**
   * Test of getNAD1983UTMZone54N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone54N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone54N());
  }

  /**
   * Test of getNAD1983UTMZone54S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone54S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone54S());
  }

  /**
   * Test of getNAD1983UTMZone55N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone55N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone55N());
  }

  /**
   * Test of getNAD1983UTMZone55S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone55S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone55S());
  }

  /**
   * Test of getNAD1983UTMZone56N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone56N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone56N());
  }

  /**
   * Test of getNAD1983UTMZone56S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone56S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone56S());
  }

  /**
   * Test of getNAD1983UTMZone57N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone57N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone57N());
  }

  /**
   * Test of getNAD1983UTMZone57S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone57S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone57S());
  }

  /**
   * Test of getNAD1983UTMZone58N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone58N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone58N());
  }

  /**
   * Test of getNAD1983UTMZone58S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone58S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone58S());
  }

  /**
   * Test of getNAD1983UTMZone59N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone59N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone59N());
  }

  /**
   * Test of getNAD1983UTMZone59S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone59S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone59S());
  }

  /**
   * Test of getNAD1983UTMZone5N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone5N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone5N());
  }

  /**
   * Test of getNAD1983UTMZone5S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone5S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone5S());
  }

  /**
   * Test of getNAD1983UTMZone60N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone60N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone60N());
  }

  /**
   * Test of getNAD1983UTMZone60S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone60S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone60S());
  }

  /**
   * Test of getNAD1983UTMZone6N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone6N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone6N());
  }

  /**
   * Test of getNAD1983UTMZone6S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone6S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone6S());
  }

  /**
   * Test of getNAD1983UTMZone7N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone7N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone7N());
  }

  /**
   * Test of getNAD1983UTMZone7S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone7S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone7S());
  }

  /**
   * Test of getNAD1983UTMZone8N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone8N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone8N());
  }

  /**
   * Test of getNAD1983UTMZone8S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone8S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone8S());
  }

  /**
   * Test of getNAD1983UTMZone9N method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone9N() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone9N());
  }

  /**
   * Test of getNAD1983UTMZone9S method, of class UtmNad1983.
   */
  @Test
  public void testNAD1983UTMZone9S() {
    testToWGS84AndBack(PROJ.getNAD1983UTMZone9S());
  }

}
