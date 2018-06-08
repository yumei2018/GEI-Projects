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
public class StatePlaneNad1983Test {

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
  private static final StatePlaneNad1983 PROJ
      = Projections.getProjected().getStatePlaneNad1983();

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
   * Test of getMichiganGeoRef2008 method, of class StatePlaneNad1983.
   */
  @Test
  public void testMichiganGeoRef2008() {
    testToWGS84AndBack(PROJ.getMichiganGeoRef2008());
  }

  /**
   * Test of getNAD1983Maine2000CentralZone method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983Maine2000CentralZone() {
    testToWGS84AndBack(PROJ.getNAD1983Maine2000CentralZone());
  }

  /**
   * Test of getNAD1983Maine2000EastZone method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983Maine2000EastZone() {
    testToWGS84AndBack(PROJ.getNAD1983Maine2000EastZone());
  }

  /**
   * Test of getNAD1983Maine2000WestZone method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983Maine2000WestZone() {
    testToWGS84AndBack(PROJ.getNAD1983Maine2000WestZone());
  }

  /**
   * Test of getNAD1983StatePlaneAlabamaEastFIPS0101 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneAlabamaEastFIPS0101() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlabamaEastFIPS0101());
  }

  /**
   * Test of getNAD1983StatePlaneAlabamaWestFIPS0102 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneAlabamaWestFIPS0102() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlabamaWestFIPS0102());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska10FIPS5010 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneAlaska10FIPS5010() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska10FIPS5010());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska1FIPS5001 method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneAlaska1FIPS5001() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska1FIPS5001());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska2FIPS5002 method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneAlaska2FIPS5002() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska2FIPS5002());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska3FIPS5003 method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneAlaska3FIPS5003() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska3FIPS5003());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska4FIPS5004 method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneAlaska4FIPS5004() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska4FIPS5004());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska5FIPS5005 method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneAlaska5FIPS5005() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska5FIPS5005());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska6FIPS5006 method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneAlaska6FIPS5006() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska6FIPS5006());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska7FIPS5007 method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneAlaska7FIPS5007() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska7FIPS5007());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska8FIPS5008 method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneAlaska8FIPS5008() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska8FIPS5008());
  }

  /**
   * Test of getNAD1983StatePlaneAlaska9FIPS5009 method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneAlaska9FIPS5009() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneAlaska9FIPS5009());
  }

  /**
   * Test of getNAD1983StatePlaneArizonaCentralFIPS0202 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneArizonaCentralFIPS0202() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneArizonaCentralFIPS0202());
  }

  /**
   * Test of getNAD1983StatePlaneArizonaEastFIPS0201 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneArizonaEastFIPS0201() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneArizonaEastFIPS0201());
  }

  /**
   * Test of getNAD1983StatePlaneArizonaWestFIPS0203 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneArizonaWestFIPS0203() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneArizonaWestFIPS0203());
  }

  /**
   * Test of getNAD1983StatePlaneArkansasNorthFIPS0301 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneArkansasNorthFIPS0301() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneArkansasNorthFIPS0301());
  }

  /**
   * Test of getNAD1983StatePlaneArkansasSouthFIPS0302 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneArkansasSouthFIPS0302() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneArkansasSouthFIPS0302());
  }

  /**
   * Test of getNAD1983StatePlaneCaliforniaIFIPS0401 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneCaliforniaIFIPS0401() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneCaliforniaIFIPS0401());
  }

  /**
   * Test of getNAD1983StatePlaneCaliforniaIIFIPS0402 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneCaliforniaIIFIPS0402() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneCaliforniaIIFIPS0402());
  }

  /**
   * Test of getNAD1983StatePlaneCaliforniaIIIFIPS0403 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneCaliforniaIIIFIPS0403() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneCaliforniaIIIFIPS0403());
  }

  /**
   * Test of getNAD1983StatePlaneCaliforniaIVFIPS0404 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneCaliforniaIVFIPS0404() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneCaliforniaIVFIPS0404());
  }

  /**
   * Test of getNAD1983StatePlaneCaliforniaVFIPS0405 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneCaliforniaVFIPS0405() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneCaliforniaVFIPS0405());
  }

  /**
   * Test of getNAD1983StatePlaneCaliforniaVIFIPS0406 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneCaliforniaVIFIPS0406() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneCaliforniaVIFIPS0406());
  }

  /**
   * Test of getNAD1983StatePlaneColoradoCentralFIPS0502 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneColoradoCentralFIPS0502() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneColoradoCentralFIPS0502());
  }

  /**
   * Test of getNAD1983StatePlaneColoradoNorthFIPS0501 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneColoradoNorthFIPS0501() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneColoradoNorthFIPS0501());
  }

  /**
   * Test of getNAD1983StatePlaneColoradoSouthFIPS0503 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneColoradoSouthFIPS0503() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneColoradoSouthFIPS0503());
  }

  /**
   * Test of getNAD1983StatePlaneConnecticutFIPS0600 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneConnecticutFIPS0600() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneConnecticutFIPS0600());
  }

  /**
   * Test of getNAD1983StatePlaneDelawareFIPS0700 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneDelawareFIPS0700() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneDelawareFIPS0700());
  }

  /**
   * Test of getNAD1983StatePlaneFloridaEastFIPS0901 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneFloridaEastFIPS0901() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneFloridaEastFIPS0901());
  }

  /**
   * Test of getNAD1983StatePlaneFloridaNorthFIPS0903 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneFloridaNorthFIPS0903() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneFloridaNorthFIPS0903());
  }

  /**
   * Test of getNAD1983StatePlaneFloridaWestFIPS0902 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneFloridaWestFIPS0902() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneFloridaWestFIPS0902());
  }

  /**
   * Test of getNAD1983StatePlaneGeorgiaEastFIPS1001 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneGeorgiaEastFIPS1001() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneGeorgiaEastFIPS1001());
  }

  /**
   * Test of getNAD1983StatePlaneGeorgiaWestFIPS1002 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneGeorgiaWestFIPS1002() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneGeorgiaWestFIPS1002());
  }

  /**
   * Test of getNAD1983StatePlaneGuamFIPS5400 method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneGuamFIPS5400() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneGuamFIPS5400());
  }

  /**
   * Test of getNAD1983StatePlaneHawaii1FIPS5101 method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneHawaii1FIPS5101() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneHawaii1FIPS5101());
  }

  /**
   * Test of getNAD1983StatePlaneHawaii2FIPS5102 method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneHawaii2FIPS5102() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneHawaii2FIPS5102());
  }

  /**
   * Test of getNAD1983StatePlaneHawaii3FIPS5103 method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneHawaii3FIPS5103() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneHawaii3FIPS5103());
  }

  /**
   * Test of getNAD1983StatePlaneHawaii4FIPS5104 method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneHawaii4FIPS5104() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneHawaii4FIPS5104());
  }

  /**
   * Test of getNAD1983StatePlaneHawaii5FIPS5105 method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneHawaii5FIPS5105() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneHawaii5FIPS5105());
  }

  /**
   * Test of getNAD1983StatePlaneIdahoCentralFIPS1102 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneIdahoCentralFIPS1102() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneIdahoCentralFIPS1102());
  }

  /**
   * Test of getNAD1983StatePlaneIdahoEastFIPS1101 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneIdahoEastFIPS1101() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneIdahoEastFIPS1101());
  }

  /**
   * Test of getNAD1983StatePlaneIdahoWestFIPS1103 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneIdahoWestFIPS1103() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneIdahoWestFIPS1103());
  }

  /**
   * Test of getNAD1983StatePlaneIllinoisEastFIPS1201 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneIllinoisEastFIPS1201() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneIllinoisEastFIPS1201());
  }

  /**
   * Test of getNAD1983StatePlaneIllinoisWestFIPS1202 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneIllinoisWestFIPS1202() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneIllinoisWestFIPS1202());
  }

  /**
   * Test of getNAD1983StatePlaneIndianaEastFIPS1301 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneIndianaEastFIPS1301() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneIndianaEastFIPS1301());
  }

  /**
   * Test of getNAD1983StatePlaneIndianaWestFIPS1302 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneIndianaWestFIPS1302() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneIndianaWestFIPS1302());
  }

  /**
   * Test of getNAD1983StatePlaneIowaNorthFIPS1401 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneIowaNorthFIPS1401() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneIowaNorthFIPS1401());
  }

  /**
   * Test of getNAD1983StatePlaneIowaSouthFIPS1402 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneIowaSouthFIPS1402() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneIowaSouthFIPS1402());
  }

  /**
   * Test of getNAD1983StatePlaneKansasNorthFIPS1501 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneKansasNorthFIPS1501() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneKansasNorthFIPS1501());
  }

  /**
   * Test of getNAD1983StatePlaneKansasSouthFIPS1502 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneKansasSouthFIPS1502() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneKansasSouthFIPS1502());
  }

  /**
   * Test of getNAD1983StatePlaneKentuckyFIPS1600 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneKentuckyFIPS1600() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneKentuckyFIPS1600());
  }

  /**
   * Test of getNAD1983StatePlaneKentuckyNorthFIPS1601 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneKentuckyNorthFIPS1601() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneKentuckyNorthFIPS1601());
  }

  /**
   * Test of getNAD1983StatePlaneKentuckySouthFIPS1602 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneKentuckySouthFIPS1602() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneKentuckySouthFIPS1602());
  }

  /**
   * Test of getNAD1983StatePlaneLouisianaNorthFIPS1701 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneLouisianaNorthFIPS1701() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneLouisianaNorthFIPS1701());
  }

  /**
   * Test of getNAD1983StatePlaneLouisianaSouthFIPS1702 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneLouisianaSouthFIPS1702() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneLouisianaSouthFIPS1702());
  }

  /**
   * Test of getNAD1983StatePlaneMaineEastFIPS1801 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneMaineEastFIPS1801() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMaineEastFIPS1801());
  }

  /**
   * Test of getNAD1983StatePlaneMaineWestFIPS1802 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneMaineWestFIPS1802() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMaineWestFIPS1802());
  }

  /**
   * Test of getNAD1983StatePlaneMarylandFIPS1900 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneMarylandFIPS1900() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMarylandFIPS1900());
  }

  /**
   * Test of getNAD1983StatePlaneMassachusettsIslandFIPS2002 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneMassachusettsIslandFIPS2002() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMassachusettsIslandFIPS2002());
  }

  /**
   * Test of getNAD1983StatePlaneMassachusettsMainlandFIPS2001 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneMassachusettsMainlandFIPS2001() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMassachusettsMainlandFIPS2001());
  }

  /**
   * Test of getNAD1983StatePlaneMichiganCentralFIPS2112 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneMichiganCentralFIPS2112() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMichiganCentralFIPS2112());
  }

  /**
   * Test of getNAD1983StatePlaneMichiganNorthFIPS2111 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneMichiganNorthFIPS2111() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMichiganNorthFIPS2111());
  }

  /**
   * Test of getNAD1983StatePlaneMichiganSouthFIPS2113 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneMichiganSouthFIPS2113() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMichiganSouthFIPS2113());
  }

  /**
   * Test of getNAD1983StatePlaneMinnesotaCentralFIPS2202 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneMinnesotaCentralFIPS2202() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMinnesotaCentralFIPS2202());
  }

  /**
   * Test of getNAD1983StatePlaneMinnesotaNorthFIPS2201 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneMinnesotaNorthFIPS2201() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMinnesotaNorthFIPS2201());
  }

  /**
   * Test of getNAD1983StatePlaneMinnesotaSouthFIPS2203 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneMinnesotaSouthFIPS2203() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMinnesotaSouthFIPS2203());
  }

  /**
   * Test of getNAD1983StatePlaneMississippiEastFIPS2301 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneMississippiEastFIPS2301() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMississippiEastFIPS2301());
  }

  /**
   * Test of getNAD1983StatePlaneMississippiWestFIPS2302 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneMississippiWestFIPS2302() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMississippiWestFIPS2302());
  }

  /**
   * Test of getNAD1983StatePlaneMissouriCentralFIPS2402 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneMissouriCentralFIPS2402() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMissouriCentralFIPS2402());
  }

  /**
   * Test of getNAD1983StatePlaneMissouriEastFIPS2401 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneMissouriEastFIPS2401() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMissouriEastFIPS2401());
  }

  /**
   * Test of getNAD1983StatePlaneMissouriWestFIPS2403 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneMissouriWestFIPS2403() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMissouriWestFIPS2403());
  }

  /**
   * Test of getNAD1983StatePlaneMontanaFIPS2500 method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneMontanaFIPS2500() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneMontanaFIPS2500());
  }

  /**
   * Test of getNAD1983StatePlaneNebraskaFIPS2600 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneNebraskaFIPS2600() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNebraskaFIPS2600());
  }

  /**
   * Test of getNAD1983StatePlaneNevadaCentralFIPS2702 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneNevadaCentralFIPS2702() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNevadaCentralFIPS2702());
  }

  /**
   * Test of getNAD1983StatePlaneNevadaEastFIPS2701 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneNevadaEastFIPS2701() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNevadaEastFIPS2701());
  }

  /**
   * Test of getNAD1983StatePlaneNevadaWestFIPS2703 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneNevadaWestFIPS2703() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNevadaWestFIPS2703());
  }

  /**
   * Test of getNAD1983StatePlaneNewHampshireFIPS2800 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneNewHampshireFIPS2800() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNewHampshireFIPS2800());
  }

  /**
   * Test of getNAD1983StatePlaneNewJerseyFIPS2900 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneNewJerseyFIPS2900() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNewJerseyFIPS2900());
  }

  /**
   * Test of getNAD1983StatePlaneNewMexicoCentralFIPS3002 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneNewMexicoCentralFIPS3002() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNewMexicoCentralFIPS3002());
  }

  /**
   * Test of getNAD1983StatePlaneNewMexicoEastFIPS3001 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneNewMexicoEastFIPS3001() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNewMexicoEastFIPS3001());
  }

  /**
   * Test of getNAD1983StatePlaneNewMexicoWestFIPS3003 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneNewMexicoWestFIPS3003() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNewMexicoWestFIPS3003());
  }

  /**
   * Test of getNAD1983StatePlaneNewYorkCentralFIPS3102 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneNewYorkCentralFIPS3102() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNewYorkCentralFIPS3102());
  }

  /**
   * Test of getNAD1983StatePlaneNewYorkEastFIPS3101 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneNewYorkEastFIPS3101() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNewYorkEastFIPS3101());
  }

  /**
   * Test of getNAD1983StatePlaneNewYorkLongIslandFIPS3104 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneNewYorkLongIslandFIPS3104() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNewYorkLongIslandFIPS3104());
  }

  /**
   * Test of getNAD1983StatePlaneNewYorkWestFIPS3103 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneNewYorkWestFIPS3103() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNewYorkWestFIPS3103());
  }

  /**
   * Test of getNAD1983StatePlaneNorthCarolinaFIPS3200 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneNorthCarolinaFIPS3200() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNorthCarolinaFIPS3200());
  }

  /**
   * Test of getNAD1983StatePlaneNorthDakotaNorthFIPS3301 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneNorthDakotaNorthFIPS3301() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNorthDakotaNorthFIPS3301());
  }

  /**
   * Test of getNAD1983StatePlaneNorthDakotaSouthFIPS3302 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneNorthDakotaSouthFIPS3302() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneNorthDakotaSouthFIPS3302());
  }

  /**
   * Test of getNAD1983StatePlaneOhioNorthFIPS3401 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneOhioNorthFIPS3401() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneOhioNorthFIPS3401());
  }

  /**
   * Test of getNAD1983StatePlaneOhioSouthFIPS3402 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneOhioSouthFIPS3402() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneOhioSouthFIPS3402());
  }

  /**
   * Test of getNAD1983StatePlaneOklahomaNorthFIPS3501 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneOklahomaNorthFIPS3501() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneOklahomaNorthFIPS3501());
  }

  /**
   * Test of getNAD1983StatePlaneOklahomaSouthFIPS3502 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneOklahomaSouthFIPS3502() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneOklahomaSouthFIPS3502());
  }

  /**
   * Test of getNAD1983StatePlaneOregonNorthFIPS3601 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneOregonNorthFIPS3601() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneOregonNorthFIPS3601());
  }

  /**
   * Test of getNAD1983StatePlaneOregonSouthFIPS3602 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneOregonSouthFIPS3602() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneOregonSouthFIPS3602());
  }

  /**
   * Test of getNAD1983StatePlanePennsylvaniaNorthFIPS3701 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlanePennsylvaniaNorthFIPS3701() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlanePennsylvaniaNorthFIPS3701());
  }

  /**
   * Test of getNAD1983StatePlanePennsylvaniaSouthFIPS3702 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlanePennsylvaniaSouthFIPS3702() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlanePennsylvaniaSouthFIPS3702());
  }

  /**
   * Test of getNAD1983StatePlanePuertoRicoVirginIslandsFIPS5200 method, of
   * class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlanePuertoRicoVirginIslandsFIPS5200() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlanePuertoRicoVirginIslandsFIPS5200());
  }

  /**
   * Test of getNAD1983StatePlaneRhodeIslandFIPS3800 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneRhodeIslandFIPS3800() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneRhodeIslandFIPS3800());
  }

  /**
   * Test of getNAD1983StatePlaneSouthCarolinaFIPS3900 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneSouthCarolinaFIPS3900() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneSouthCarolinaFIPS3900());
  }

  /**
   * Test of getNAD1983StatePlaneSouthDakotaNorthFIPS4001 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneSouthDakotaNorthFIPS4001() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneSouthDakotaNorthFIPS4001());
  }

  /**
   * Test of getNAD1983StatePlaneSouthDakotaSouthFIPS4002 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneSouthDakotaSouthFIPS4002() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneSouthDakotaSouthFIPS4002());
  }

  /**
   * Test of getNAD1983StatePlaneTennesseeFIPS4100 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneTennesseeFIPS4100() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneTennesseeFIPS4100());
  }

  /**
   * Test of getNAD1983StatePlaneTexasCentralFIPS4203 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneTexasCentralFIPS4203() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneTexasCentralFIPS4203());
  }

  /**
   * Test of getNAD1983StatePlaneTexasNorthCentralFIPS4202 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneTexasNorthCentralFIPS4202() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneTexasNorthCentralFIPS4202());
  }

  /**
   * Test of getNAD1983StatePlaneTexasNorthFIPS4201 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneTexasNorthFIPS4201() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneTexasNorthFIPS4201());
  }

  /**
   * Test of getNAD1983StatePlaneTexasSouthCentralFIPS4204 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneTexasSouthCentralFIPS4204() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneTexasSouthCentralFIPS4204());
  }

  /**
   * Test of getNAD1983StatePlaneTexasSouthFIPS4205 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneTexasSouthFIPS4205() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneTexasSouthFIPS4205());
  }

  /**
   * Test of getNAD1983StatePlaneUtahCentralFIPS4302 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneUtahCentralFIPS4302() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneUtahCentralFIPS4302());
  }

  /**
   * Test of getNAD1983StatePlaneUtahNorthFIPS4301 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneUtahNorthFIPS4301() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneUtahNorthFIPS4301());
  }

  /**
   * Test of getNAD1983StatePlaneUtahSouthFIPS4303 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneUtahSouthFIPS4303() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneUtahSouthFIPS4303());
  }

  /**
   * Test of getNAD1983StatePlaneVermontFIPS4400 method, of class StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneVermontFIPS4400() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneVermontFIPS4400());
  }

  /**
   * Test of getNAD1983StatePlaneVirginiaNorthFIPS4501 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneVirginiaNorthFIPS4501() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneVirginiaNorthFIPS4501());
  }

  /**
   * Test of getNAD1983StatePlaneVirginiaSouthFIPS4502 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneVirginiaSouthFIPS4502() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneVirginiaSouthFIPS4502());
  }

  /**
   * Test of getNAD1983StatePlaneWashingtonNorthFIPS4601 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneWashingtonNorthFIPS4601() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWashingtonNorthFIPS4601());
  }

  /**
   * Test of getNAD1983StatePlaneWashingtonSouthFIPS4602 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneWashingtonSouthFIPS4602() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWashingtonSouthFIPS4602());
  }

  /**
   * Test of getNAD1983StatePlaneWestVirginiaNorthFIPS4701 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneWestVirginiaNorthFIPS4701() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWestVirginiaNorthFIPS4701());
  }

  /**
   * Test of getNAD1983StatePlaneWestVirginiaSouthFIPS4702 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneWestVirginiaSouthFIPS4702() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWestVirginiaSouthFIPS4702());
  }

  /**
   * Test of getNAD1983StatePlaneWisconsinCentralFIPS4802 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneWisconsinCentralFIPS4802() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWisconsinCentralFIPS4802());
  }

  /**
   * Test of getNAD1983StatePlaneWisconsinNorthFIPS4801 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneWisconsinNorthFIPS4801() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWisconsinNorthFIPS4801());
  }

  /**
   * Test of getNAD1983StatePlaneWisconsinSouthFIPS4803 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneWisconsinSouthFIPS4803() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWisconsinSouthFIPS4803());
  }

  /**
   * Test of getNAD1983StatePlaneWyomingEastCentralFIPS4902 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneWyomingEastCentralFIPS4902() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWyomingEastCentralFIPS4902());
  }

  /**
   * Test of getNAD1983StatePlaneWyomingEastFIPS4901 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneWyomingEastFIPS4901() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWyomingEastFIPS4901());
  }

  /**
   * Test of getNAD1983StatePlaneWyomingWestCentralFIPS4903 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneWyomingWestCentralFIPS4903() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWyomingWestCentralFIPS4903());
  }

  /**
   * Test of getNAD1983StatePlaneWyomingWestFIPS4904 method, of class
   * StatePlaneNad1983.
   */
  @Test
  public void testNAD1983StatePlaneWyomingWestFIPS4904() {
    testToWGS84AndBack(PROJ.getNAD1983StatePlaneWyomingWestFIPS4904());
  }

}
