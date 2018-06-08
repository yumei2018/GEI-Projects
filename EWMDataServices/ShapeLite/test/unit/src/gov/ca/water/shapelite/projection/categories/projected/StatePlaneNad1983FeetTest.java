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
public class StatePlaneNad1983FeetTest {

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
  private static final StatePlaneNad1983Feet PROJ
      = Projections.getProjected().getStatePlaneNad1983Feet();

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
   * Test of getNAD1983StatePlaneAlabamaEastFIPS0101Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneAlabamaEastFIPS0101Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlabamaEastFIPS0101Feet());
  }

  /**
   * Test of getNAD1983StatePlaneAlabamaWestFIPS0102Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneAlabamaWestFIPS0102Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlabamaWestFIPS0102Feet());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska10FIPS5010Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneAlaska10FIPS5010Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska10FIPS5010Feet());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska1FIPS5001Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneAlaska1FIPS5001Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska1FIPS5001Feet());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska2FIPS5002Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneAlaska2FIPS5002Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska2FIPS5002Feet());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska3FIPS5003Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneAlaska3FIPS5003Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska3FIPS5003Feet());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska4FIPS5004Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneAlaska4FIPS5004Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska4FIPS5004Feet());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska5FIPS5005Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneAlaska5FIPS5005Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska5FIPS5005Feet());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska6FIPS5006Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneAlaska6FIPS5006Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska6FIPS5006Feet());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska7FIPS5007Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneAlaska7FIPS5007Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska7FIPS5007Feet());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska8FIPS5008Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneAlaska8FIPS5008Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska8FIPS5008Feet());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska9FIPS5009Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneAlaska9FIPS5009Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska9FIPS5009Feet());
  }

  /**
   * Test of getNAD1983StatePlaneArizonaCentralFIPS0202Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneArizonaCentralFIPS0202Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneArizonaCentralFIPS0202Feet());
  }

  /**
   * Test of getNAD1983StatePlaneArizonaEastFIPS0201Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneArizonaEastFIPS0201Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneArizonaEastFIPS0201Feet());
  }

  /**
   * Test of getNAD1983StatePlaneArizonaWestFIPS0203Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneArizonaWestFIPS0203Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneArizonaWestFIPS0203Feet());
  }

  /**
   * Test of getNAD1983StatePlaneArkansasNorthFIPS0301Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneArkansasNorthFIPS0301Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneArkansasNorthFIPS0301Feet());
  }

  /**
   * Test of getNAD1983StatePlaneArkansasSouthFIPS0302Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneArkansasSouthFIPS0302Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneArkansasSouthFIPS0302Feet());
  }

  /**
   * Test of getNAD1983StatePlaneCaliforniaIFIPS0401Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneCaliforniaIFIPS0401Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneCaliforniaIFIPS0401Feet());
  }

  /**
   * Test of getNAD1983StatePlaneCaliforniaIIFIPS0402Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneCaliforniaIIFIPS0402Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneCaliforniaIIFIPS0402Feet());
  }

  /**
   * Test of getNAD1983StatePlaneCaliforniaIIIFIPS0403Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneCaliforniaIIIFIPS0403Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneCaliforniaIIIFIPS0403Feet());
  }

  /**
   * Test of getNAD1983StatePlaneCaliforniaIVFIPS0404Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneCaliforniaIVFIPS0404Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneCaliforniaIVFIPS0404Feet());
  }

  /**
   * Test of getNAD1983StatePlaneCaliforniaVFIPS0405Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneCaliforniaVFIPS0405Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneCaliforniaVFIPS0405Feet());
  }

  /**
   * Test of getNAD1983StatePlaneCaliforniaVIFIPS0406Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneCaliforniaVIFIPS0406Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneCaliforniaVIFIPS0406Feet());
  }

  /**
   * Test of getNAD1983StatePlaneColoradoCentralFIPS0502Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneColoradoCentralFIPS0502Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneColoradoCentralFIPS0502Feet());
  }

  /**
   * Test of getNAD1983StatePlaneColoradoNorthFIPS0501Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneColoradoNorthFIPS0501Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneColoradoNorthFIPS0501Feet());
  }

  /**
   * Test of getNAD1983StatePlaneColoradoSouthFIPS0503Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneColoradoSouthFIPS0503Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneColoradoSouthFIPS0503Feet());
  }

  /**
   * Test of getNAD1983StatePlaneConnecticutFIPS0600Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneConnecticutFIPS0600Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneConnecticutFIPS0600Feet());
  }

  /**
   * Test of getNAD1983StatePlaneDelawareFIPS0700Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneDelawareFIPS0700Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneDelawareFIPS0700Feet());
  }

  /**
   * Test of getNAD1983StatePlaneFloridaEastFIPS0901Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneFloridaEastFIPS0901Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneFloridaEastFIPS0901Feet());
  }

  /**
   * Test of getNAD1983StatePlaneFloridaNorthFIPS0903Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneFloridaNorthFIPS0903Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneFloridaNorthFIPS0903Feet());
  }

  /**
   * Test of getNAD1983StatePlaneFloridaWestFIPS0902Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneFloridaWestFIPS0902Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneFloridaWestFIPS0902Feet());
  }

  /**
   * Test of getNAD1983StatePlaneGeorgiaEastFIPS1001Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneGeorgiaEastFIPS1001Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneGeorgiaEastFIPS1001Feet());
  }

  /**
   * Test of getNAD1983StatePlaneGeorgiaWestFIPS1002Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneGeorgiaWestFIPS1002Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneGeorgiaWestFIPS1002Feet());
  }

  /**
   * Test of getNAD1983StatePlaneGuamFIPS5400Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneGuamFIPS5400Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneGuamFIPS5400Feet());
  }

  /**
   * Test of getNAD1983StatePlaneHawaii1FIPS5101Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneHawaii1FIPS5101Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneHawaii1FIPS5101Feet());
  }

  /**
   * Test of getNAD1983StatePlaneHawaii2FIPS5102Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneHawaii2FIPS5102Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneHawaii2FIPS5102Feet());
  }

  /**
   * Test of getNAD1983StatePlaneHawaii3FIPS5103Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneHawaii3FIPS5103Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneHawaii3FIPS5103Feet());
  }

  /**
   * Test of getNAD1983StatePlaneHawaii4FIPS5104Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneHawaii4FIPS5104Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneHawaii4FIPS5104Feet());
  }

  /**
   * Test of getNAD1983StatePlaneHawaii5FIPS5105Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneHawaii5FIPS5105Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneHawaii5FIPS5105Feet());
  }

  /**
   * Test of getNAD1983StatePlaneIdahoCentralFIPS1102Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneIdahoCentralFIPS1102Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneIdahoCentralFIPS1102Feet());
  }

  /**
   * Test of getNAD1983StatePlaneIdahoEastFIPS1101Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneIdahoEastFIPS1101Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneIdahoEastFIPS1101Feet());
  }

  /**
   * Test of getNAD1983StatePlaneIdahoWestFIPS1103Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneIdahoWestFIPS1103Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneIdahoWestFIPS1103Feet());
  }

  /**
   * Test of getNAD1983StatePlaneIllinoisEastFIPS1201Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneIllinoisEastFIPS1201Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneIllinoisEastFIPS1201Feet());
  }

  /**
   * Test of getNAD1983StatePlaneIllinoisWestFIPS1202Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneIllinoisWestFIPS1202Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneIllinoisWestFIPS1202Feet());
  }

  /**
   * Test of getNAD1983StatePlaneIndianaEastFIPS1301Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneIndianaEastFIPS1301Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneIndianaEastFIPS1301Feet());
  }

  /**
   * Test of getNAD1983StatePlaneIndianaWestFIPS1302Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneIndianaWestFIPS1302Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneIndianaWestFIPS1302Feet());
  }

  /**
   * Test of getNAD1983StatePlaneIowaNorthFIPS1401Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneIowaNorthFIPS1401Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneIowaNorthFIPS1401Feet());
  }

  /**
   * Test of getNAD1983StatePlaneIowaSouthFIPS1402Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneIowaSouthFIPS1402Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneIowaSouthFIPS1402Feet());
  }

  /**
   * Test of getNAD1983StatePlaneKansasNorthFIPS1501Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneKansasNorthFIPS1501Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneKansasNorthFIPS1501Feet());
  }

  /**
   * Test of getNAD1983StatePlaneKansasSouthFIPS1502Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneKansasSouthFIPS1502Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneKansasSouthFIPS1502Feet());
  }

  /**
   * Test of getNAD1983StatePlaneKentuckyFIPS1600Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneKentuckyFIPS1600Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneKentuckyFIPS1600Feet());
  }

  /**
   * Test of getNAD1983StatePlaneKentuckyNorthFIPS1601Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneKentuckyNorthFIPS1601Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneKentuckyNorthFIPS1601Feet());
  }

  /**
   * Test of getNAD1983StatePlaneKentuckySouthFIPS1602Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneKentuckySouthFIPS1602Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneKentuckySouthFIPS1602Feet());
  }

  /**
   * Test of getNAD1983StatePlaneLouisianaNorthFIPS1701Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneLouisianaNorthFIPS1701Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneLouisianaNorthFIPS1701Feet());
  }

  /**
   * Test of getNAD1983StatePlaneLouisianaSouthFIPS1702Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneLouisianaSouthFIPS1702Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneLouisianaSouthFIPS1702Feet());
  }

  /**
   * Test of getNAD1983StatePlaneMaineEastFIPS1801Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneMaineEastFIPS1801Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMaineEastFIPS1801Feet());
  }

  /**
   * Test of getNAD1983StatePlaneMaineWestFIPS1802Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneMaineWestFIPS1802Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMaineWestFIPS1802Feet());
  }

  /**
   * Test of getNAD1983StatePlaneMarylandFIPS1900Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneMarylandFIPS1900Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMarylandFIPS1900Feet());
  }

  /**
   * Test of getNAD1983StatePlaneMassachusettsIslandFIPS2002Feet method, of
   * class StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneMassachusettsIslandFIPS2002Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMassachusettsIslandFIPS2002Feet());
  }

  /**
   * Test of getNAD1983StatePlaneMassachusettsMainlandFIPS2001Feet method, of
   * class StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneMassachusettsMainlandFIPS2001Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMassachusettsMainlandFIPS2001Feet());
  }

  /**
   * Test of getNAD1983StatePlaneMichiganCentralFIPS2112Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneMichiganCentralFIPS2112Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMichiganCentralFIPS2112Feet());
  }

  /**
   * Test of getNAD1983StatePlaneMichiganNorthFIPS2111Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneMichiganNorthFIPS2111Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMichiganNorthFIPS2111Feet());
  }

  /**
   * Test of getNAD1983StatePlaneMichiganSouthFIPS2113Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneMichiganSouthFIPS2113Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMichiganSouthFIPS2113Feet());
  }

  /**
   * Test of getNAD1983StatePlaneMinnesotaCentralFIPS2202Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneMinnesotaCentralFIPS2202Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMinnesotaCentralFIPS2202Feet());
  }

  /**
   * Test of getNAD1983StatePlaneMinnesotaNorthFIPS2201Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneMinnesotaNorthFIPS2201Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMinnesotaNorthFIPS2201Feet());
  }

  /**
   * Test of getNAD1983StatePlaneMinnesotaSouthFIPS2203Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneMinnesotaSouthFIPS2203Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMinnesotaSouthFIPS2203Feet());
  }

  /**
   * Test of getNAD1983StatePlaneMississippiEastFIPS2301Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneMississippiEastFIPS2301Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMississippiEastFIPS2301Feet());
  }

  /**
   * Test of getNAD1983StatePlaneMississippiWestFIPS2302Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneMississippiWestFIPS2302Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMississippiWestFIPS2302Feet());
  }

  /**
   * Test of getNAD1983StatePlaneMissouriCentralFIPS2402Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneMissouriCentralFIPS2402Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMissouriCentralFIPS2402Feet());
  }

  /**
   * Test of getNAD1983StatePlaneMissouriEastFIPS2401Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneMissouriEastFIPS2401Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMissouriEastFIPS2401Feet());
  }

  /**
   * Test of getNAD1983StatePlaneMissouriWestFIPS2403Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneMissouriWestFIPS2403Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMissouriWestFIPS2403Feet());
  }

  /**
   * Test of getNAD1983StatePlaneMontanaFIPS2500Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneMontanaFIPS2500Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMontanaFIPS2500Feet());
  }

  /**
   * Test of getNAD1983StatePlaneNebraskaFIPS2600Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneNebraskaFIPS2600Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNebraskaFIPS2600Feet());
  }

  /**
   * Test of getNAD1983StatePlaneNevadaCentralFIPS2702Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneNevadaCentralFIPS2702Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNevadaCentralFIPS2702Feet());
  }

  /**
   * Test of getNAD1983StatePlaneNevadaEastFIPS2701Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneNevadaEastFIPS2701Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNevadaEastFIPS2701Feet());
  }

  /**
   * Test of getNAD1983StatePlaneNevadaWestFIPS2703Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneNevadaWestFIPS2703Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNevadaWestFIPS2703Feet());
  }

  /**
   * Test of getNAD1983StatePlaneNewHampshireFIPS2800Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneNewHampshireFIPS2800Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNewHampshireFIPS2800Feet());
  }

  /**
   * Test of getNAD1983StatePlaneNewJerseyFIPS2900Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneNewJerseyFIPS2900Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNewJerseyFIPS2900Feet());
  }

  /**
   * Test of getNAD1983StatePlaneNewMexicoCentralFIPS3002Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneNewMexicoCentralFIPS3002Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNewMexicoCentralFIPS3002Feet());
  }

  /**
   * Test of getNAD1983StatePlaneNewMexicoEastFIPS3001Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneNewMexicoEastFIPS3001Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNewMexicoEastFIPS3001Feet());
  }

  /**
   * Test of getNAD1983StatePlaneNewMexicoWestFIPS3003Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneNewMexicoWestFIPS3003Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNewMexicoWestFIPS3003Feet());
  }

  /**
   * Test of getNAD1983StatePlaneNewYorkCentralFIPS3102Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneNewYorkCentralFIPS3102Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNewYorkCentralFIPS3102Feet());
  }

  /**
   * Test of getNAD1983StatePlaneNewYorkEastFIPS3101Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneNewYorkEastFIPS3101Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNewYorkEastFIPS3101Feet());
  }

  /**
   * Test of getNAD1983StatePlaneNewYorkLongIslandFIPS3104Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneNewYorkLongIslandFIPS3104Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNewYorkLongIslandFIPS3104Feet());
  }

  /**
   * Test of getNAD1983StatePlaneNewYorkWestFIPS3103Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneNewYorkWestFIPS3103Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNewYorkWestFIPS3103Feet());
  }

  /**
   * Test of getNAD1983StatePlaneNorthCarolinaFIPS3200Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneNorthCarolinaFIPS3200Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNorthCarolinaFIPS3200Feet());
  }

  /**
   * Test of getNAD1983StatePlaneNorthDakotaNorthFIPS3301Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneNorthDakotaNorthFIPS3301Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNorthDakotaNorthFIPS3301Feet());
  }

  /**
   * Test of getNAD1983StatePlaneNorthDakotaSouthFIPS3302Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneNorthDakotaSouthFIPS3302Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNorthDakotaSouthFIPS3302Feet());
  }

  /**
   * Test of getNAD1983StatePlaneOhioNorthFIPS3401Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneOhioNorthFIPS3401Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneOhioNorthFIPS3401Feet());
  }

  /**
   * Test of getNAD1983StatePlaneOhioSouthFIPS3402Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneOhioSouthFIPS3402Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneOhioSouthFIPS3402Feet());
  }

  /**
   * Test of getNAD1983StatePlaneOklahomaNorthFIPS3501Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneOklahomaNorthFIPS3501Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneOklahomaNorthFIPS3501Feet());
  }

  /**
   * Test of getNAD1983StatePlaneOklahomaSouthFIPS3502Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneOklahomaSouthFIPS3502Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneOklahomaSouthFIPS3502Feet());
  }

  /**
   * Test of getNAD1983StatePlaneOregonNorthFIPS3601Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneOregonNorthFIPS3601Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneOregonNorthFIPS3601Feet());
  }

  /**
   * Test of getNAD1983StatePlaneOregonSouthFIPS3602Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneOregonSouthFIPS3602Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneOregonSouthFIPS3602Feet());
  }

  /**
   * Test of getNAD1983StatePlanePRVirginIslandsFIPS5200Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlanePRVirginIslandsFIPS5200Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlanePRVirginIslandsFIPS5200Feet());
  }

  /**
   * Test of getNAD1983StatePlanePennsylvaniaNorthFIPS3701Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlanePennsylvaniaNorthFIPS3701Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlanePennsylvaniaNorthFIPS3701Feet());
  }

  /**
   * Test of getNAD1983StatePlanePennsylvaniaSouthFIPS3702Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlanePennsylvaniaSouthFIPS3702Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlanePennsylvaniaSouthFIPS3702Feet());
  }

  /**
   * Test of getNAD1983StatePlaneRhodeIslandFIPS3800Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneRhodeIslandFIPS3800Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneRhodeIslandFIPS3800Feet());
  }

  /**
   * Test of getNAD1983StatePlaneSouthCarolinaFIPS3900Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneSouthCarolinaFIPS3900Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneSouthCarolinaFIPS3900Feet());
  }

  /**
   * Test of getNAD1983StatePlaneSouthDakotaNorthFIPS4001Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneSouthDakotaNorthFIPS4001Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneSouthDakotaNorthFIPS4001Feet());
  }

  /**
   * Test of getNAD1983StatePlaneSouthDakotaSouthFIPS4002Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneSouthDakotaSouthFIPS4002Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneSouthDakotaSouthFIPS4002Feet());
  }

  /**
   * Test of getNAD1983StatePlaneTennesseeFIPS4100Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneTennesseeFIPS4100Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneTennesseeFIPS4100Feet());
  }

  /**
   * Test of getNAD1983StatePlaneTexasCentralFIPS4203Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneTexasCentralFIPS4203Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneTexasCentralFIPS4203Feet());
  }

  /**
   * Test of getNAD1983StatePlaneTexasNorthCentralFIPS4202Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneTexasNorthCentralFIPS4202Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneTexasNorthCentralFIPS4202Feet());
  }

  /**
   * Test of getNAD1983StatePlaneTexasNorthFIPS4201Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneTexasNorthFIPS4201Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneTexasNorthFIPS4201Feet());
  }

  /**
   * Test of getNAD1983StatePlaneTexasSouthCentralFIPS4204Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneTexasSouthCentralFIPS4204Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneTexasSouthCentralFIPS4204Feet());
  }

  /**
   * Test of getNAD1983StatePlaneTexasSouthFIPS4205Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneTexasSouthFIPS4205Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneTexasSouthFIPS4205Feet());
  }

  /**
   * Test of getNAD1983StatePlaneUtahCentralFIPS4302Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneUtahCentralFIPS4302Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneUtahCentralFIPS4302Feet());
  }

  /**
   * Test of getNAD1983StatePlaneUtahNorthFIPS4301Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneUtahNorthFIPS4301Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneUtahNorthFIPS4301Feet());
  }

  /**
   * Test of getNAD1983StatePlaneUtahSouthFIPS4303Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneUtahSouthFIPS4303Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneUtahSouthFIPS4303Feet());
  }

  /**
   * Test of getNAD1983StatePlaneVermontFIPS4400Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneVermontFIPS4400Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneVermontFIPS4400Feet());
  }

  /**
   * Test of getNAD1983StatePlaneVirginiaNorthFIPS4501Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneVirginiaNorthFIPS4501Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneVirginiaNorthFIPS4501Feet());
  }

  /**
   * Test of getNAD1983StatePlaneVirginiaSouthFIPS4502Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneVirginiaSouthFIPS4502Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneVirginiaSouthFIPS4502Feet());
  }

  /**
   * Test of getNAD1983StatePlaneWashingtonNorthFIPS4601Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneWashingtonNorthFIPS4601Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWashingtonNorthFIPS4601Feet());
  }

  /**
   * Test of getNAD1983StatePlaneWashingtonSouthFIPS4602Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneWashingtonSouthFIPS4602Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWashingtonSouthFIPS4602Feet());
  }

  /**
   * Test of getNAD1983StatePlaneWestVirginiaNorthFIPS4701Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneWestVirginiaNorthFIPS4701Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWestVirginiaNorthFIPS4701Feet());
  }

  /**
   * Test of getNAD1983StatePlaneWestVirginiaSouthFIPS4702Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneWestVirginiaSouthFIPS4702Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWestVirginiaSouthFIPS4702Feet());
  }

  /**
   * Test of getNAD1983StatePlaneWisconsinCentralFIPS4802Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneWisconsinCentralFIPS4802Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWisconsinCentralFIPS4802Feet());
  }

  /**
   * Test of getNAD1983StatePlaneWisconsinNorthFIPS4801Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneWisconsinNorthFIPS4801Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWisconsinNorthFIPS4801Feet());
  }

  /**
   * Test of getNAD1983StatePlaneWisconsinSouthFIPS4803Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneWisconsinSouthFIPS4803Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWisconsinSouthFIPS4803Feet());
  }

  /**
   * Test of getNAD1983StatePlaneWyomingEastCentralFIPS4902Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneWyomingEastCentralFIPS4902Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWyomingEastCentralFIPS4902Feet());
  }

  /**
   * Test of getNAD1983StatePlaneWyomingEastFIPS4901Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneWyomingEastFIPS4901Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWyomingEastFIPS4901Feet());
  }

  /**
   * Test of getNAD1983StatePlaneWyomingWestCentralFIPS4903Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneWyomingWestCentralFIPS4903Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWyomingWestCentralFIPS4903Feet());
  }

  /**
   * Test of getNAD1983StatePlaneWyomingWestFIPS4904Feet method, of class
   * StatePlaneNad1983Feet.
   */
  @Test
  public void testNAD1983StatePlaneWyomingWestFIPS4904Feet() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWyomingWestFIPS4904Feet());
  }

}
