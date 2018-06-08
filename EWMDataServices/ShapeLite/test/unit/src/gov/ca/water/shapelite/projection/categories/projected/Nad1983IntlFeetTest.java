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
public class Nad1983IntlFeetTest {

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
  private static final Nad1983IntlFeet PROJ
      = Projections.getProjected().getNad1983IntlFeet();

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
   * Test of getNAD1983StatePlaneArizonaCentralFIPS0202FeetIntl method, of class
   * Nad1983IntlFeet.
   */
  @Test
  public void testNAD1983StatePlaneArizonaCentralFIPS0202FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneArizonaCentralFIPS0202FeetIntl());
  }

  /**
   * Test of getNAD1983StatePlaneArizonaEastFIPS0201FeetIntl method, of class
   * Nad1983IntlFeet.
   */
  @Test
  public void testNAD1983StatePlaneArizonaEastFIPS0201FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneArizonaEastFIPS0201FeetIntl());
  }

  /**
   * Test of getNAD1983StatePlaneArizonaWestFIPS0203FeetIntl method, of class
   * Nad1983IntlFeet.
   */
  @Test
  public void testNAD1983StatePlaneArizonaWestFIPS0203FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneArizonaWestFIPS0203FeetIntl());
  }

  /**
   * Test of getNAD1983StatePlaneMichiganCentralFIPS2112FeetIntl method, of
   * class Nad1983IntlFeet.
   */
  @Test
  public void testNAD1983StatePlaneMichiganCentralFIPS2112FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMichiganCentralFIPS2112FeetIntl());
  }

  /**
   * Test of getNAD1983StatePlaneMichiganNorthFIPS2111FeetIntl method, of class
   * Nad1983IntlFeet.
   */
  @Test
  public void testNAD1983StatePlaneMichiganNorthFIPS2111FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMichiganNorthFIPS2111FeetIntl());
  }

  /**
   * Test of getNAD1983StatePlaneMichiganSouthFIPS2113FeetIntl method, of class
   * Nad1983IntlFeet.
   */
  @Test
  public void testNAD1983StatePlaneMichiganSouthFIPS2113FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMichiganSouthFIPS2113FeetIntl());
  }

  /**
   * Test of getNAD1983StatePlaneMontanaFIPS2500FeetIntl method, of class
   * Nad1983IntlFeet.
   */
  @Test
  public void testNAD1983StatePlaneMontanaFIPS2500FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMontanaFIPS2500FeetIntl());
  }

  /**
   * Test of getNAD1983StatePlaneNorthDakotaNorthFIPS3301FeetIntl method, of
   * class Nad1983IntlFeet.
   */
  @Test
  public void testNAD1983StatePlaneNorthDakotaNorthFIPS3301FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNorthDakotaNorthFIPS3301FeetIntl());
  }

  /**
   * Test of getNAD1983StatePlaneNorthDakotaSouthFIPS3302FeetIntl method, of
   * class Nad1983IntlFeet.
   */
  @Test
  public void testNAD1983StatePlaneNorthDakotaSouthFIPS3302FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNorthDakotaSouthFIPS3302FeetIntl());
  }

  /**
   * Test of getNAD1983StatePlaneOregonNorthFIPS3601FeetIntl method, of class
   * Nad1983IntlFeet.
   */
  @Test
  public void testNAD1983StatePlaneOregonNorthFIPS3601FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneOregonNorthFIPS3601FeetIntl());
  }

  /**
   * Test of getNAD1983StatePlaneOregonSouthFIPS3602FeetIntl method, of class
   * Nad1983IntlFeet.
   */
  @Test
  public void testNAD1983StatePlaneOregonSouthFIPS3602FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneOregonSouthFIPS3602FeetIntl());
  }

  /**
   * Test of getNAD1983StatePlaneSouthCarolinaFIPS3900FeetIntl method, of class
   * Nad1983IntlFeet.
   */
  @Test
  public void testNAD1983StatePlaneSouthCarolinaFIPS3900FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneSouthCarolinaFIPS3900FeetIntl());
  }

  /**
   * Test of getNAD1983StatePlaneUtahCentralFIPS4302FeetIntl method, of class
   * Nad1983IntlFeet.
   */
  @Test
  public void testNAD1983StatePlaneUtahCentralFIPS4302FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneUtahCentralFIPS4302FeetIntl());
  }

  /**
   * Test of getNAD1983StatePlaneUtahNorthFIPS4301FeetIntl method, of class
   * Nad1983IntlFeet.
   */
  @Test
  public void testNAD1983StatePlaneUtahNorthFIPS4301FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneUtahNorthFIPS4301FeetIntl());
  }

  /**
   * Test of getNAD1983StatePlaneUtahSouthFIPS4303FeetIntl method, of class
   * Nad1983IntlFeet.
   */
  @Test
  public void testNAD1983StatePlaneUtahSouthFIPS4303FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneUtahSouthFIPS4303FeetIntl());
  }

}
