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
public class StatePlaneNad1983HarnTest {

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
  private static final StatePlaneNad1983Harn PROJ
      = Projections.getProjected().getStatePlaneNad1983Harn();

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
   * Test of getNAD1983HARNMaine2000CentralZone method, of class NationalGrids.
   */
  @Test
  public void testNAD1983HARNMaine2000CentralZone() {
    testToWGS84AndBack(PROJ.getNAD1983HARNMaine2000CentralZone());
  }

  /**
   * Test of getNAD1983HARNMaine2000EastZone method, of class NationalGrids.
   */
  @Test
  public void testNAD1983HARNMaine2000EastZone() {
    testToWGS84AndBack(PROJ.getNAD1983HARNMaine2000EastZone());
  }

  /**
   * Test of getNAD1983HARNMaine2000WestZone method, of class NationalGrids.
   */
  @Test
  public void testNAD1983HARNMaine2000WestZone() {
    testToWGS84AndBack(PROJ.getNAD1983HARNMaine2000WestZone());
  }

  /**
   * Test of getNAD1983HARNStatePlaneAlabamaEastFIPS0101 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneAlabamaEastFIPS0101() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneAlabamaEastFIPS0101());
  }

  /**
   * Test of getNAD1983HARNStatePlaneAlabamaWestFIPS0102 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneAlabamaWestFIPS0102() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneAlabamaWestFIPS0102());
  }

  /**
   * Test of getNAD1983HARNStatePlaneArizonaCentralFIPS0202 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneArizonaCentralFIPS0202() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneArizonaCentralFIPS0202());
  }

  /**
   * Test of getNAD1983HARNStatePlaneArizonaEastFIPS0201 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneArizonaEastFIPS0201() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneArizonaEastFIPS0201());
  }

  /**
   * Test of getNAD1983HARNStatePlaneArizonaWestFIPS0203 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneArizonaWestFIPS0203() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneArizonaWestFIPS0203());
  }

  /**
   * Test of getNAD1983HARNStatePlaneArkansasNorthFIPS0301 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneArkansasNorthFIPS0301() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneArkansasNorthFIPS0301());
  }

  /**
   * Test of getNAD1983HARNStatePlaneArkansasSouthFIPS0302 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneArkansasSouthFIPS0302() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneArkansasSouthFIPS0302());
  }

  /**
   * Test of getNAD1983HARNStatePlaneCaliforniaIFIPS0401 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneCaliforniaIFIPS0401() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneCaliforniaIFIPS0401());
  }

  /**
   * Test of getNAD1983HARNStatePlaneCaliforniaIIFIPS0402 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneCaliforniaIIFIPS0402() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneCaliforniaIIFIPS0402());
  }

  /**
   * Test of getNAD1983HARNStatePlaneCaliforniaIIIFIPS0403 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneCaliforniaIIIFIPS0403() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneCaliforniaIIIFIPS0403());
  }

  /**
   * Test of getNAD1983HARNStatePlaneCaliforniaIVFIPS0404 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneCaliforniaIVFIPS0404() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneCaliforniaIVFIPS0404());
  }

  /**
   * Test of getNAD1983HARNStatePlaneCaliforniaVFIPS0405 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneCaliforniaVFIPS0405() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneCaliforniaVFIPS0405());
  }

  /**
   * Test of getNAD1983HARNStatePlaneCaliforniaVIFIPS0406 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneCaliforniaVIFIPS0406() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneCaliforniaVIFIPS0406());
  }

  /**
   * Test of getNAD1983HARNStatePlaneColoradoCentralFIPS0502 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneColoradoCentralFIPS0502() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneColoradoCentralFIPS0502());
  }

  /**
   * Test of getNAD1983HARNStatePlaneColoradoNorthFIPS0501 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneColoradoNorthFIPS0501() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneColoradoNorthFIPS0501());
  }

  /**
   * Test of getNAD1983HARNStatePlaneColoradoSouthFIPS0503 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneColoradoSouthFIPS0503() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneColoradoSouthFIPS0503());
  }

  /**
   * Test of getNAD1983HARNStatePlaneConnecticutFIPS0600 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneConnecticutFIPS0600() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneConnecticutFIPS0600());
  }

  /**
   * Test of getNAD1983HARNStatePlaneDelawareFIPS0700 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneDelawareFIPS0700() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneDelawareFIPS0700());
  }

  /**
   * Test of getNAD1983HARNStatePlaneFloridaEastFIPS0901 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneFloridaEastFIPS0901() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneFloridaEastFIPS0901());
  }

  /**
   * Test of getNAD1983HARNStatePlaneFloridaNorthFIPS0903 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneFloridaNorthFIPS0903() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneFloridaNorthFIPS0903());
  }

  /**
   * Test of getNAD1983HARNStatePlaneFloridaWestFIPS0902 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneFloridaWestFIPS0902() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneFloridaWestFIPS0902());
  }

  /**
   * Test of getNAD1983HARNStatePlaneGeorgiaEastFIPS1001 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneGeorgiaEastFIPS1001() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneGeorgiaEastFIPS1001());
  }

  /**
   * Test of getNAD1983HARNStatePlaneGeorgiaWestFIPS1002 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneGeorgiaWestFIPS1002() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneGeorgiaWestFIPS1002());
  }

  /**
   * Test of getNAD1983HARNStatePlaneHawaii1FIPS5101 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneHawaii1FIPS5101() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneHawaii1FIPS5101());
  }

  /**
   * Test of getNAD1983HARNStatePlaneHawaii2FIPS5102 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneHawaii2FIPS5102() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneHawaii2FIPS5102());
  }

  /**
   * Test of getNAD1983HARNStatePlaneHawaii3FIPS5103 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneHawaii3FIPS5103() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneHawaii3FIPS5103());
  }

  /**
   * Test of getNAD1983HARNStatePlaneHawaii4FIPS5104 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneHawaii4FIPS5104() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneHawaii4FIPS5104());
  }

  /**
   * Test of getNAD1983HARNStatePlaneHawaii5FIPS5105 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneHawaii5FIPS5105() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneHawaii5FIPS5105());
  }

  /**
   * Test of getNAD1983HARNStatePlaneIdahoCentralFIPS1102 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneIdahoCentralFIPS1102() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneIdahoCentralFIPS1102());
  }

  /**
   * Test of getNAD1983HARNStatePlaneIdahoEastFIPS1101 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneIdahoEastFIPS1101() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneIdahoEastFIPS1101());
  }

  /**
   * Test of getNAD1983HARNStatePlaneIdahoWestFIPS1103 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneIdahoWestFIPS1103() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneIdahoWestFIPS1103());
  }

  /**
   * Test of getNAD1983HARNStatePlaneIllinoisEastFIPS1201 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneIllinoisEastFIPS1201() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneIllinoisEastFIPS1201());
  }

  /**
   * Test of getNAD1983HARNStatePlaneIllinoisWestFIPS1202 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneIllinoisWestFIPS1202() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneIllinoisWestFIPS1202());
  }

  /**
   * Test of getNAD1983HARNStatePlaneIndianaEastFIPS1301 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneIndianaEastFIPS1301() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneIndianaEastFIPS1301());
  }

  /**
   * Test of getNAD1983HARNStatePlaneIndianaWestFIPS1302 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneIndianaWestFIPS1302() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneIndianaWestFIPS1302());
  }

  /**
   * Test of getNAD1983HARNStatePlaneIowaNorthFIPS1401 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneIowaNorthFIPS1401() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneIowaNorthFIPS1401());
  }

  /**
   * Test of getNAD1983HARNStatePlaneIowaSouthFIPS1402 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneIowaSouthFIPS1402() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneIowaSouthFIPS1402());
  }

  /**
   * Test of getNAD1983HARNStatePlaneKansasNorthFIPS1501 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneKansasNorthFIPS1501() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneKansasNorthFIPS1501());
  }

  /**
   * Test of getNAD1983HARNStatePlaneKansasSouthFIPS1502 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneKansasSouthFIPS1502() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneKansasSouthFIPS1502());
  }

  /**
   * Test of getNAD1983HARNStatePlaneKentuckyNorthFIPS1601 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneKentuckyNorthFIPS1601() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneKentuckyNorthFIPS1601());
  }

  /**
   * Test of getNAD1983HARNStatePlaneKentuckySouthFIPS1602 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneKentuckySouthFIPS1602() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneKentuckySouthFIPS1602());
  }

  /**
   * Test of getNAD1983HARNStatePlaneLouisianaNorthFIPS1701 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneLouisianaNorthFIPS1701() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneLouisianaNorthFIPS1701());
  }

  /**
   * Test of getNAD1983HARNStatePlaneLouisianaSouthFIPS1702 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneLouisianaSouthFIPS1702() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneLouisianaSouthFIPS1702());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMaineEastFIPS1801 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneMaineEastFIPS1801() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMaineEastFIPS1801());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMaineWestFIPS1802 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneMaineWestFIPS1802() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMaineWestFIPS1802());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMarylandFIPS1900 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneMarylandFIPS1900() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMarylandFIPS1900());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMassachusettsIslandFIPS2002 method, of
   * class NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneMassachusettsIslandFIPS2002() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMassachusettsIslandFIPS2002());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMassachusettsMainlandFIPS2001 method, of
   * class NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneMassachusettsMainlandFIPS2001() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMassachusettsMainlandFIPS2001());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMichiganCentralFIPS2112 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneMichiganCentralFIPS2112() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMichiganCentralFIPS2112());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMichiganNorthFIPS2111 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneMichiganNorthFIPS2111() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMichiganNorthFIPS2111());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMichiganSouthFIPS2113 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneMichiganSouthFIPS2113() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMichiganSouthFIPS2113());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMinnesotaCentralFIPS2202 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneMinnesotaCentralFIPS2202() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMinnesotaCentralFIPS2202());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMinnesotaNorthFIPS2201 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneMinnesotaNorthFIPS2201() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMinnesotaNorthFIPS2201());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMinnesotaSouthFIPS2203 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneMinnesotaSouthFIPS2203() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMinnesotaSouthFIPS2203());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMississippiEastFIPS2301 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneMississippiEastFIPS2301() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMississippiEastFIPS2301());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMississippiWestFIPS2302 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneMississippiWestFIPS2302() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMississippiWestFIPS2302());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMissouriCentralFIPS2402 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneMissouriCentralFIPS2402() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMissouriCentralFIPS2402());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMissouriEastFIPS2401 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneMissouriEastFIPS2401() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMissouriEastFIPS2401());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMissouriWestFIPS2403 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneMissouriWestFIPS2403() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMissouriWestFIPS2403());
  }

  /**
   * Test of getNAD1983HARNStatePlaneMontanaFIPS2500 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneMontanaFIPS2500() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneMontanaFIPS2500());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNebraskaFIPS2600 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneNebraskaFIPS2600() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNebraskaFIPS2600());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNevadaCentralFIPS2702 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneNevadaCentralFIPS2702() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNevadaCentralFIPS2702());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNevadaEastFIPS2701 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneNevadaEastFIPS2701() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNevadaEastFIPS2701());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNevadaWestFIPS2703 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneNevadaWestFIPS2703() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNevadaWestFIPS2703());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNewHampshireFIPS2800 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneNewHampshireFIPS2800() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNewHampshireFIPS2800());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNewJerseyFIPS2900 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneNewJerseyFIPS2900() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNewJerseyFIPS2900());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNewMexicoCentralFIPS3002 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneNewMexicoCentralFIPS3002() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNewMexicoCentralFIPS3002());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNewMexicoEastFIPS3001 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneNewMexicoEastFIPS3001() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNewMexicoEastFIPS3001());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNewMexicoWestFIPS3003 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneNewMexicoWestFIPS3003() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNewMexicoWestFIPS3003());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNewYorkCentralFIPS3102 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneNewYorkCentralFIPS3102() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNewYorkCentralFIPS3102());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNewYorkEastFIPS3101 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneNewYorkEastFIPS3101() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNewYorkEastFIPS3101());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNewYorkLongIslandFIPS3104 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneNewYorkLongIslandFIPS3104() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNewYorkLongIslandFIPS3104());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNewYorkWestFIPS3103 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneNewYorkWestFIPS3103() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNewYorkWestFIPS3103());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNorthDakotaNorthFIPS3301 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneNorthDakotaNorthFIPS3301() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNorthDakotaNorthFIPS3301());
  }

  /**
   * Test of getNAD1983HARNStatePlaneNorthDakotaSouthFIPS3302 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneNorthDakotaSouthFIPS3302() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneNorthDakotaSouthFIPS3302());
  }

  /**
   * Test of getNAD1983HARNStatePlaneOhioNorthFIPS3401 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneOhioNorthFIPS3401() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneOhioNorthFIPS3401());
  }

  /**
   * Test of getNAD1983HARNStatePlaneOhioSouthFIPS3402 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneOhioSouthFIPS3402() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneOhioSouthFIPS3402());
  }

  /**
   * Test of getNAD1983HARNStatePlaneOklahomaNorthFIPS3501 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneOklahomaNorthFIPS3501() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneOklahomaNorthFIPS3501());
  }

  /**
   * Test of getNAD1983HARNStatePlaneOklahomaSouthFIPS3502 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneOklahomaSouthFIPS3502() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneOklahomaSouthFIPS3502());
  }

  /**
   * Test of getNAD1983HARNStatePlaneOregonNorthFIPS3601 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneOregonNorthFIPS3601() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneOregonNorthFIPS3601());
  }

  /**
   * Test of getNAD1983HARNStatePlaneOregonSouthFIPS3602 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneOregonSouthFIPS3602() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneOregonSouthFIPS3602());
  }

  /**
   * Test of getNAD1983HARNStatePlanePRVirginIslandsFIPS5200 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlanePRVirginIslandsFIPS5200() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlanePRVirginIslandsFIPS5200());
  }

  /**
   * Test of getNAD1983HARNStatePlaneRhodeIslandFIPS3800 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneRhodeIslandFIPS3800() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneRhodeIslandFIPS3800());
  }

  /**
   * Test of getNAD1983HARNStatePlaneSouthDakotaNorthFIPS4001 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneSouthDakotaNorthFIPS4001() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneSouthDakotaNorthFIPS4001());
  }

  /**
   * Test of getNAD1983HARNStatePlaneSouthDakotaSouthFIPS4002 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneSouthDakotaSouthFIPS4002() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneSouthDakotaSouthFIPS4002());
  }

  /**
   * Test of getNAD1983HARNStatePlaneTennesseeFIPS4100 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneTennesseeFIPS4100() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneTennesseeFIPS4100());
  }

  /**
   * Test of getNAD1983HARNStatePlaneTexasCentralFIPS4203 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneTexasCentralFIPS4203() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneTexasCentralFIPS4203());
  }

  /**
   * Test of getNAD1983HARNStatePlaneTexasNorthCentralFIPS4202 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneTexasNorthCentralFIPS4202() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneTexasNorthCentralFIPS4202());
  }

  /**
   * Test of getNAD1983HARNStatePlaneTexasNorthFIPS4201 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneTexasNorthFIPS4201() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneTexasNorthFIPS4201());
  }

  /**
   * Test of getNAD1983HARNStatePlaneTexasSouthCentralFIPS4204 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneTexasSouthCentralFIPS4204() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneTexasSouthCentralFIPS4204());
  }

  /**
   * Test of getNAD1983HARNStatePlaneTexasSouthFIPS4205 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneTexasSouthFIPS4205() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneTexasSouthFIPS4205());
  }

  /**
   * Test of getNAD1983HARNStatePlaneUtahCentralFIPS4302 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneUtahCentralFIPS4302() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneUtahCentralFIPS4302());
  }

  /**
   * Test of getNAD1983HARNStatePlaneUtahNorthFIPS4301 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneUtahNorthFIPS4301() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneUtahNorthFIPS4301());
  }

  /**
   * Test of getNAD1983HARNStatePlaneUtahSouthFIPS4303 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneUtahSouthFIPS4303() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneUtahSouthFIPS4303());
  }

  /**
   * Test of getNAD1983HARNStatePlaneVermontFIPS4400 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneVermontFIPS4400() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneVermontFIPS4400());
  }

  /**
   * Test of getNAD1983HARNStatePlaneVirginiaNorthFIPS4501 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneVirginiaNorthFIPS4501() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneVirginiaNorthFIPS4501());
  }

  /**
   * Test of getNAD1983HARNStatePlaneVirginiaSouthFIPS4502 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneVirginiaSouthFIPS4502() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneVirginiaSouthFIPS4502());
  }

  /**
   * Test of getNAD1983HARNStatePlaneWashingtonNorthFIPS4601 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneWashingtonNorthFIPS4601() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneWashingtonNorthFIPS4601());
  }

  /**
   * Test of getNAD1983HARNStatePlaneWashingtonSouthFIPS4602 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneWashingtonSouthFIPS4602() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneWashingtonSouthFIPS4602());
  }

  /**
   * Test of getNAD1983HARNStatePlaneWestVirginiaNorthFIPS4701 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneWestVirginiaNorthFIPS4701() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneWestVirginiaNorthFIPS4701());
  }

  /**
   * Test of getNAD1983HARNStatePlaneWestVirginiaSouthFIPS4702 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneWestVirginiaSouthFIPS4702() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneWestVirginiaSouthFIPS4702());
  }

  /**
   * Test of getNAD1983HARNStatePlaneWisconsinCentralFIPS4802 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneWisconsinCentralFIPS4802() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneWisconsinCentralFIPS4802());
  }

  /**
   * Test of getNAD1983HARNStatePlaneWisconsinNorthFIPS4801 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneWisconsinNorthFIPS4801() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneWisconsinNorthFIPS4801());
  }

  /**
   * Test of getNAD1983HARNStatePlaneWisconsinSouthFIPS4803 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneWisconsinSouthFIPS4803() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneWisconsinSouthFIPS4803());
  }

  /**
   * Test of getNAD1983HARNStatePlaneWyomingECFIPS4902 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneWyomingECFIPS4902() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneWyomingECFIPS4902());
  }

  /**
   * Test of getNAD1983HARNStatePlaneWyomingEastFIPS4901 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneWyomingEastFIPS4901() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneWyomingEastFIPS4901());
  }

  /**
   * Test of getNAD1983HARNStatePlaneWyomingWCFIPS4903 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneWyomingWCFIPS4903() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneWyomingWCFIPS4903());
  }

  /**
   * Test of getNAD1983HARNStatePlaneWyomingWestFIPS4904 method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983HARNStatePlaneWyomingWestFIPS4904() {
    testToWGS84AndBack(PROJ.getNAD1983HARNStatePlaneWyomingWestFIPS4904());
  }

}
