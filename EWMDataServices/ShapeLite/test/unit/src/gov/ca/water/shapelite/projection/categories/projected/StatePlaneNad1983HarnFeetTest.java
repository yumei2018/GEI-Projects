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
public class StatePlaneNad1983HarnFeetTest {

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
  private static final StatePlaneNad1983HarnFeet PROJ
      = Projections.getProjected().getStatePlaneNad1983HarnFeet();

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
   * Test of getNAD1983HARNStatePlaneArizonaCentralFIPS0202FeetIntl method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneArizonaCentralFIPS0202FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneArizonaCentralFIPS0202FeetIntl());
  }

  /**
   * Test of getNAD1983HARNStatePlaneArizonaEastFIPS0201FeetIntl method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneArizonaEastFIPS0201FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneArizonaEastFIPS0201FeetIntl());
  }

  /**
   * Test of getNAD1983HARNStatePlaneArizonaWestFIPS0203FeetIntl method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneArizonaWestFIPS0203FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneArizonaWestFIPS0203FeetIntl());
  }

  /**
   * Test of getNAD1983HARNStatePlaneCaliforniaIFIPS0401Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneCaliforniaIFIPS0401Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneCaliforniaIFIPS0401Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneCaliforniaIIFIPS0402Feet method, of class
   * StatePlaneNad1983HarnFeet..
   */
  @Test
  public void testNAD1983HARNStatePlaneCaliforniaIIFIPS0402Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneCaliforniaIIFIPS0402Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneCaliforniaIIIFIPS0403Feet method, of class
   * StatePlaneNad1983HarnFeet..
   */
  @Test
  public void testNAD1983HARNStatePlaneCaliforniaIIIFIPS0403Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneCaliforniaIIIFIPS0403Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneCaliforniaIVFIPS0404Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneCaliforniaIVFIPS0404Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneCaliforniaIVFIPS0404Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneCaliforniaVFIPS0405Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneCaliforniaVFIPS0405Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneCaliforniaVFIPS0405Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneCaliforniaVIFIPS0406Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneCaliforniaVIFIPS0406Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneCaliforniaVIFIPS0406Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneColoradoCentralFIPS0502Feet method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneColoradoCentralFIPS0502Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneColoradoCentralFIPS0502Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneColoradoNorthFIPS0501Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneColoradoNorthFIPS0501Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneColoradoNorthFIPS0501Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneColoradoSouthFIPS0503Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneColoradoSouthFIPS0503Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneColoradoSouthFIPS0503Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneConnecticutFIPS0600Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneConnecticutFIPS0600Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneConnecticutFIPS0600Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneDelawareFIPS0700Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneDelawareFIPS0700Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneDelawareFIPS0700Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneFloridaEastFIPS0901Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneFloridaEastFIPS0901Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneFloridaEastFIPS0901Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneFloridaNorthFIPS0903Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneFloridaNorthFIPS0903Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneFloridaNorthFIPS0903Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneFloridaWestFIPS0902Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneFloridaWestFIPS0902Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneFloridaWestFIPS0902Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneGeorgiaEastFIPS1001Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneGeorgiaEastFIPS1001Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneGeorgiaEastFIPS1001Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneGeorgiaWestFIPS1002Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneGeorgiaWestFIPS1002Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneGeorgiaWestFIPS1002Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneHawaii1FIPS5101Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneHawaii1FIPS5101Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneHawaii1FIPS5101Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneHawaii2FIPS5102Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneHawaii2FIPS5102Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneHawaii2FIPS5102Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneHawaii3FIPS5103Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneHawaii3FIPS5103Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneHawaii3FIPS5103Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneHawaii4FIPS5104Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneHawaii4FIPS5104Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneHawaii4FIPS5104Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneHawaii5FIPS5105Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneHawaii5FIPS5105Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneHawaii5FIPS5105Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneIdahoCentralFIPS1102Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneIdahoCentralFIPS1102Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneIdahoCentralFIPS1102Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneIdahoEastFIPS1101Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneIdahoEastFIPS1101Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneIdahoEastFIPS1101Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneIdahoWestFIPS1103Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneIdahoWestFIPS1103Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneIdahoWestFIPS1103Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneIndianaEastFIPS1301Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneIndianaEastFIPS1301Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneIndianaEastFIPS1301Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneIndianaWestFIPS1302Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneIndianaWestFIPS1302Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneIndianaWestFIPS1302Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneKentuckyNorthFIPS1601Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneKentuckyNorthFIPS1601Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneKentuckyNorthFIPS1601Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneKentuckySouthFIPS1602Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneKentuckySouthFIPS1602Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneKentuckySouthFIPS1602Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMarylandFIPS1900Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneMarylandFIPS1900Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMarylandFIPS1900Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMassachusettsIslandFIPS2002Feet method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneMassachusettsIslandFIPS2002Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMassachusettsIslandFIPS2002Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMassachusettsMainlandFIPS2001Feet method,
   * of class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneMassachusettsMainlandFIPS2001Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMassachusettsMainlandFIPS2001Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMichiganCentralFIPS2112FeetIntl method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneMichiganCentralFIPS2112FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMichiganCentralFIPS2112FeetIntl());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMichiganNorthFIPS2111FeetIntl method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneMichiganNorthFIPS2111FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMichiganNorthFIPS2111FeetIntl());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMichiganSouthFIPS2113FeetIntl method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneMichiganSouthFIPS2113FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMichiganSouthFIPS2113FeetIntl());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMississippiEastFIPS2301Feet method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneMississippiEastFIPS2301Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMississippiEastFIPS2301Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMississippiWestFIPS2302Feet method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneMississippiWestFIPS2302Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMississippiWestFIPS2302Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMontanaFIPS2500FeetIntl method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneMontanaFIPS2500FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMontanaFIPS2500FeetIntl());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNewMexicoCentralFIPS3002Feet method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneNewMexicoCentralFIPS3002Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNewMexicoCentralFIPS3002Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNewMexicoEastFIPS3001Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneNewMexicoEastFIPS3001Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNewMexicoEastFIPS3001Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNewMexicoWestFIPS3003Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneNewMexicoWestFIPS3003Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNewMexicoWestFIPS3003Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNewYorkCentralFIPS3102Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneNewYorkCentralFIPS3102Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNewYorkCentralFIPS3102Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNewYorkEastFIPS3101Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneNewYorkEastFIPS3101Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNewYorkEastFIPS3101Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNewYorkLongIslandFIPS3104Feet method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneNewYorkLongIslandFIPS3104Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNewYorkLongIslandFIPS3104Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNewYorkWestFIPS3103Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneNewYorkWestFIPS3103Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNewYorkWestFIPS3103Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNorthDakotaNorthFIPS3301FeetIntl method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneNorthDakotaNorthFIPS3301FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNorthDakotaNorthFIPS3301FeetIntl());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNorthDakotaSouthFIPS3302FeetIntl method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneNorthDakotaSouthFIPS3302FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNorthDakotaSouthFIPS3302FeetIntl());
  }

  /**
   * Test of getNAD1983HARNStatePlaneOklahomaNorthFIPS3501Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneOklahomaNorthFIPS3501Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneOklahomaNorthFIPS3501Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneOklahomaSouthFIPS3502Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneOklahomaSouthFIPS3502Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneOklahomaSouthFIPS3502Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneOregonNorthFIPS3601FeetIntl method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneOregonNorthFIPS3601FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneOregonNorthFIPS3601FeetIntl());
  }

  /**
   * Test of getNAD1983HARNStatePlaneOregonSouthFIPS3602FeetIntl method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneOregonSouthFIPS3602FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneOregonSouthFIPS3602FeetIntl());
  }

  /**
   * Test of getNAD1983HARNStatePlaneTennesseeFIPS4100Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneTennesseeFIPS4100Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneTennesseeFIPS4100Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneTexasCentralFIPS4203Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneTexasCentralFIPS4203Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneTexasCentralFIPS4203Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneTexasNorthCentralFIPS4202Feet method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneTexasNorthCentralFIPS4202Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneTexasNorthCentralFIPS4202Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneTexasNorthFIPS4201Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneTexasNorthFIPS4201Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneTexasNorthFIPS4201Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneTexasSouthCentralFIPS4204Feet method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneTexasSouthCentralFIPS4204Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneTexasSouthCentralFIPS4204Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneTexasSouthFIPS4205Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneTexasSouthFIPS4205Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneTexasSouthFIPS4205Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneUtahCentralFIPS4302FeetIntl method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneUtahCentralFIPS4302FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneUtahCentralFIPS4302FeetIntl());
  }

  /**
   * Test of getNAD1983HARNStatePlaneUtahNorthFIPS4301FeetIntl method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneUtahNorthFIPS4301FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneUtahNorthFIPS4301FeetIntl());
  }

  /**
   * Test of getNAD1983HARNStatePlaneUtahSouthFIPS4303FeetIntl method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneUtahSouthFIPS4303FeetIntl() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneUtahSouthFIPS4303FeetIntl());
  }

  /**
   * Test of getNAD1983HARNStatePlaneVirginiaNorthFIPS4501Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneVirginiaNorthFIPS4501Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneVirginiaNorthFIPS4501Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneVirginiaSouthFIPS4502Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneVirginiaSouthFIPS4502Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneVirginiaSouthFIPS4502Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneWashingtonNorthFIPS4601Feet method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneWashingtonNorthFIPS4601Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneWashingtonNorthFIPS4601Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneWashingtonSouthFIPS4602Feet method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneWashingtonSouthFIPS4602Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneWashingtonSouthFIPS4602Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneWisconsinCentralFIPS4802Feet method, of
   * class StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneWisconsinCentralFIPS4802Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneWisconsinCentralFIPS4802Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneWisconsinNorthFIPS4801Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneWisconsinNorthFIPS4801Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneWisconsinNorthFIPS4801Feet());
  }

  /**
   * Test of getNAD1983HARNStatePlaneWisconsinSouthFIPS4803Feet method, of class
   * StatePlaneNad1983HarnFeet.
   */
  @Test
  public void testNAD1983HARNStatePlaneWisconsinSouthFIPS4803Feet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneWisconsinSouthFIPS4803Feet());
  }

}
