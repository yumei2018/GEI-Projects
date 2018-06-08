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
public class StatePlaneNad1927Test {

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
  private static final StatePlaneNad1927 PROJ
      = Projections.getProjected().getStatePlaneNad1927();

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
   * Test of getNAD1927StatePlaneAlabamaEastFIPS0101 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneAlabamaEastFIPS0101() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneAlabamaEastFIPS0101());
  }

  /**
   * Test of getNAD1927StatePlaneAlabamaWestFIPS0102 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneAlabamaWestFIPS0102() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneAlabamaWestFIPS0102());
  }

  /**
   * Test of getNAD1927StatePlaneAlaska10FIPS5010 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneAlaska10FIPS5010() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneAlaska10FIPS5010());
  }

  /**
   * Test of getNAD1927StatePlaneAlaska1FIPS5001 method, of class StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneAlaska1FIPS5001() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneAlaska1FIPS5001());
  }

  /**
   * Test of getNAD1927StatePlaneAlaska2FIPS5002 method, of class StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneAlaska2FIPS5002() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneAlaska2FIPS5002());
  }

  /**
   * Test of getNAD1927StatePlaneAlaska3FIPS5003 method, of class StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneAlaska3FIPS5003() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneAlaska3FIPS5003());
  }

  /**
   * Test of getNAD1927StatePlaneAlaska4FIPS5004 method, of class StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneAlaska4FIPS5004() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneAlaska4FIPS5004());
  }

  /**
   * Test of getNAD1927StatePlaneAlaska5FIPS5005 method, of class StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneAlaska5FIPS5005() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneAlaska5FIPS5005());
  }

  /**
   * Test of getNAD1927StatePlaneAlaska6FIPS5006 method, of class StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneAlaska6FIPS5006() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneAlaska6FIPS5006());
  }

  /**
   * Test of getNAD1927StatePlaneAlaska7FIPS5007 method, of class StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneAlaska7FIPS5007() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneAlaska7FIPS5007());
  }

  /**
   * Test of getNAD1927StatePlaneAlaska8FIPS5008 method, of class StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneAlaska8FIPS5008() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneAlaska8FIPS5008());
  }

  /**
   * Test of getNAD1927StatePlaneAlaska9FIPS5009 method, of class StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneAlaska9FIPS5009() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneAlaska9FIPS5009());
  }

  /**
   * Test of getNAD1927StatePlaneArizonaCentralFIPS0202 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneArizonaCentralFIPS0202() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneArizonaCentralFIPS0202());
  }

  /**
   * Test of getNAD1927StatePlaneArizonaEastFIPS0201 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneArizonaEastFIPS0201() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneArizonaEastFIPS0201());
  }

  /**
   * Test of getNAD1927StatePlaneArizonaWestFIPS0203 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneArizonaWestFIPS0203() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneArizonaWestFIPS0203());
  }

  /**
   * Test of getNAD1927StatePlaneArkansasNorthFIPS0301 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneArkansasNorthFIPS0301() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneArkansasNorthFIPS0301());
  }

  /**
   * Test of getNAD1927StatePlaneArkansasSouthFIPS0302 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneArkansasSouthFIPS0302() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneArkansasSouthFIPS0302());
  }

  /**
   * Test of getNAD1927StatePlaneCaliforniaIFIPS0401 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneCaliforniaIFIPS0401() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneCaliforniaIFIPS0401());
  }

  /**
   * Test of getNAD1927StatePlaneCaliforniaIIFIPS0402 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneCaliforniaIIFIPS0402() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneCaliforniaIIFIPS0402());
  }

  /**
   * Test of getNAD1927StatePlaneCaliforniaIIIFIPS0403 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneCaliforniaIIIFIPS0403() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneCaliforniaIIIFIPS0403());
  }

  /**
   * Test of getNAD1927StatePlaneCaliforniaIVFIPS0404 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneCaliforniaIVFIPS0404() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneCaliforniaIVFIPS0404());
  }

  /**
   * Test of getNAD1927StatePlaneCaliforniaVFIPS0405 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneCaliforniaVFIPS0405() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneCaliforniaVFIPS0405());
  }

  /**
   * Test of getNAD1927StatePlaneCaliforniaVIFIPS0406 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneCaliforniaVIFIPS0406() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneCaliforniaVIFIPS0406());
  }

  /**
   * Test of getNAD1927StatePlaneCaliforniaVIIFIPS0407 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneCaliforniaVIIFIPS0407() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneCaliforniaVIIFIPS0407());
  }

  /**
   * Test of getNAD1927StatePlaneColoradoCentralFIPS0502 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneColoradoCentralFIPS0502() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneColoradoCentralFIPS0502());
  }

  /**
   * Test of getNAD1927StatePlaneColoradoNorthFIPS0501 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneColoradoNorthFIPS0501() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneColoradoNorthFIPS0501());
  }

  /**
   * Test of getNAD1927StatePlaneColoradoSouthFIPS0503 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneColoradoSouthFIPS0503() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneColoradoSouthFIPS0503());
  }

  /**
   * Test of getNAD1927StatePlaneConnecticutFIPS0600 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneConnecticutFIPS0600() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneConnecticutFIPS0600());
  }

  /**
   * Test of getNAD1927StatePlaneDelawareFIPS0700 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneDelawareFIPS0700() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneDelawareFIPS0700());
  }

  /**
   * Test of getNAD1927StatePlaneFloridaEastFIPS0901 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneFloridaEastFIPS0901() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneFloridaEastFIPS0901());
  }

  /**
   * Test of getNAD1927StatePlaneFloridaNorthFIPS0903 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneFloridaNorthFIPS0903() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneFloridaNorthFIPS0903());
  }

  /**
   * Test of getNAD1927StatePlaneFloridaWestFIPS0902 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneFloridaWestFIPS0902() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneFloridaWestFIPS0902());
  }

  /**
   * Test of getNAD1927StatePlaneGeorgiaEastFIPS1001 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneGeorgiaEastFIPS1001() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneGeorgiaEastFIPS1001());
  }

  /**
   * Test of getNAD1927StatePlaneGeorgiaWestFIPS1002 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneGeorgiaWestFIPS1002() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneGeorgiaWestFIPS1002());
  }

  /**
   * Test of getNAD1927StatePlaneGuamFIPS5400 method, of class StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneGuamFIPS5400() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneGuamFIPS5400());
  }

  /**
   * Test of getNAD1927StatePlaneIdahoCentralFIPS1102 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneIdahoCentralFIPS1102() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneIdahoCentralFIPS1102());
  }

  /**
   * Test of getNAD1927StatePlaneIdahoEastFIPS1101 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneIdahoEastFIPS1101() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneIdahoEastFIPS1101());
  }

  /**
   * Test of getNAD1927StatePlaneIdahoWestFIPS1103 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneIdahoWestFIPS1103() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneIdahoWestFIPS1103());
  }

  /**
   * Test of getNAD1927StatePlaneIllinoisEastFIPS1201 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneIllinoisEastFIPS1201() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneIllinoisEastFIPS1201());
  }

  /**
   * Test of getNAD1927StatePlaneIllinoisWestFIPS1202 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneIllinoisWestFIPS1202() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneIllinoisWestFIPS1202());
  }

  /**
   * Test of getNAD1927StatePlaneIndianaEastFIPS1301 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneIndianaEastFIPS1301() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneIndianaEastFIPS1301());
  }

  /**
   * Test of getNAD1927StatePlaneIndianaWestFIPS1302 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneIndianaWestFIPS1302() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneIndianaWestFIPS1302());
  }

  /**
   * Test of getNAD1927StatePlaneIowaNorthFIPS1401 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneIowaNorthFIPS1401() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneIowaNorthFIPS1401());
  }

  /**
   * Test of getNAD1927StatePlaneIowaSouthFIPS1402 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneIowaSouthFIPS1402() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneIowaSouthFIPS1402());
  }

  /**
   * Test of getNAD1927StatePlaneKansasNorthFIPS1501 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneKansasNorthFIPS1501() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneKansasNorthFIPS1501());
  }

  /**
   * Test of getNAD1927StatePlaneKansasSouthFIPS1502 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneKansasSouthFIPS1502() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneKansasSouthFIPS1502());
  }

  /**
   * Test of getNAD1927StatePlaneKentuckyNorthFIPS1601 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneKentuckyNorthFIPS1601() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneKentuckyNorthFIPS1601());
  }

  /**
   * Test of getNAD1927StatePlaneKentuckySouthFIPS1602 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneKentuckySouthFIPS1602() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneKentuckySouthFIPS1602());
  }

  /**
   * Test of getNAD1927StatePlaneLouisianaNorthFIPS1701 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneLouisianaNorthFIPS1701() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneLouisianaNorthFIPS1701());
  }

  /**
   * Test of getNAD1927StatePlaneLouisianaSouthFIPS1702 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneLouisianaSouthFIPS1702() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneLouisianaSouthFIPS1702());
  }

  /**
   * Test of getNAD1927StatePlaneMaineEastFIPS1801 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneMaineEastFIPS1801() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneMaineEastFIPS1801());
  }

  /**
   * Test of getNAD1927StatePlaneMaineWestFIPS1802 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneMaineWestFIPS1802() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneMaineWestFIPS1802());
  }

  /**
   * Test of getNAD1927StatePlaneMarylandFIPS1900 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneMarylandFIPS1900() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneMarylandFIPS1900());
  }

  /**
   * Test of getNAD1927StatePlaneMassachusettsIslandFIPS2002 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneMassachusettsIslandFIPS2002() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneMassachusettsIslandFIPS2002());
  }

  /**
   * Test of getNAD1927StatePlaneMassachusettsMainlandFIPS2001 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneMassachusettsMainlandFIPS2001() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneMassachusettsMainlandFIPS2001());
  }

  /**
   * Test of getNAD1927StatePlaneMichiganCentralFIPS2112 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneMichiganCentralFIPS2112() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneMichiganCentralFIPS2112());
  }

  /**
   * Test of getNAD1927StatePlaneMichiganNorthFIPS2111 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneMichiganNorthFIPS2111() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneMichiganNorthFIPS2111());
  }

  /**
   * Test of getNAD1927StatePlaneMichiganSouthFIPS2113 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneMichiganSouthFIPS2113() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneMichiganSouthFIPS2113());
  }

  /**
   * Test of getNAD1927StatePlaneMinnesotaCentralFIPS2202 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneMinnesotaCentralFIPS2202() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneMinnesotaCentralFIPS2202());
  }

  /**
   * Test of getNAD1927StatePlaneMinnesotaNorthFIPS2201 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneMinnesotaNorthFIPS2201() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneMinnesotaNorthFIPS2201());
  }

  /**
   * Test of getNAD1927StatePlaneMinnesotaSouthFIPS2203 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneMinnesotaSouthFIPS2203() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneMinnesotaSouthFIPS2203());
  }

  /**
   * Test of getNAD1927StatePlaneMississippiEastFIPS2301 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneMississippiEastFIPS2301() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneMississippiEastFIPS2301());
  }

  /**
   * Test of getNAD1927StatePlaneMississippiWestFIPS2302 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneMississippiWestFIPS2302() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneMississippiWestFIPS2302());
  }

  /**
   * Test of getNAD1927StatePlaneMissouriCentralFIPS2402 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneMissouriCentralFIPS2402() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneMissouriCentralFIPS2402());
  }

  /**
   * Test of getNAD1927StatePlaneMissouriEastFIPS2401 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneMissouriEastFIPS2401() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneMissouriEastFIPS2401());
  }

  /**
   * Test of getNAD1927StatePlaneMissouriWestFIPS2403 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneMissouriWestFIPS2403() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneMissouriWestFIPS2403());
  }

  /**
   * Test of getNAD1927StatePlaneMontanaCentralFIPS2502 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneMontanaCentralFIPS2502() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneMontanaCentralFIPS2502());
  }

  /**
   * Test of getNAD1927StatePlaneMontanaNorthFIPS2501 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneMontanaNorthFIPS2501() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneMontanaNorthFIPS2501());
  }

  /**
   * Test of getNAD1927StatePlaneMontanaSouthFIPS2503 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneMontanaSouthFIPS2503() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneMontanaSouthFIPS2503());
  }

  /**
   * Test of getNAD1927StatePlaneNebraskaNorthFIPS2601 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneNebraskaNorthFIPS2601() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneNebraskaNorthFIPS2601());
  }

  /**
   * Test of getNAD1927StatePlaneNebraskaSouthFIPS2602 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneNebraskaSouthFIPS2602() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneNebraskaSouthFIPS2602());
  }

  /**
   * Test of getNAD1927StatePlaneNevadaCentralFIPS2702 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneNevadaCentralFIPS2702() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneNevadaCentralFIPS2702());
  }

  /**
   * Test of getNAD1927StatePlaneNevadaEastFIPS2701 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneNevadaEastFIPS2701() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneNevadaEastFIPS2701());
  }

  /**
   * Test of getNAD1927StatePlaneNevadaWestFIPS2703 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneNevadaWestFIPS2703() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneNevadaWestFIPS2703());
  }

  /**
   * Test of getNAD1927StatePlaneNewHampshireFIPS2800 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneNewHampshireFIPS2800() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneNewHampshireFIPS2800());
  }

  /**
   * Test of getNAD1927StatePlaneNewJerseyFIPS2900 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneNewJerseyFIPS2900() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneNewJerseyFIPS2900());
  }

  /**
   * Test of getNAD1927StatePlaneNewMexicoCentralFIPS3002 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneNewMexicoCentralFIPS3002() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneNewMexicoCentralFIPS3002());
  }

  /**
   * Test of getNAD1927StatePlaneNewMexicoEastFIPS3001 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneNewMexicoEastFIPS3001() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneNewMexicoEastFIPS3001());
  }

  /**
   * Test of getNAD1927StatePlaneNewMexicoWestFIPS3003 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneNewMexicoWestFIPS3003() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneNewMexicoWestFIPS3003());
  }

  /**
   * Test of getNAD1927StatePlaneNewYorkCentralFIPS3102 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneNewYorkCentralFIPS3102() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneNewYorkCentralFIPS3102());
  }

  /**
   * Test of getNAD1927StatePlaneNewYorkEastFIPS3101 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneNewYorkEastFIPS3101() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneNewYorkEastFIPS3101());
  }

  /**
   * Test of getNAD1927StatePlaneNewYorkLongIslandFIPS3104 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneNewYorkLongIslandFIPS3104() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneNewYorkLongIslandFIPS3104());
  }

  /**
   * Test of getNAD1927StatePlaneNewYorkWestFIPS3103 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneNewYorkWestFIPS3103() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneNewYorkWestFIPS3103());
  }

  /**
   * Test of getNAD1927StatePlaneNorthCarolinaFIPS3200 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneNorthCarolinaFIPS3200() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneNorthCarolinaFIPS3200());
  }

  /**
   * Test of getNAD1927StatePlaneNorthDakotaNorthFIPS3301 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneNorthDakotaNorthFIPS3301() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneNorthDakotaNorthFIPS3301());
  }

  /**
   * Test of getNAD1927StatePlaneNorthDakotaSouthFIPS3302 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneNorthDakotaSouthFIPS3302() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneNorthDakotaSouthFIPS3302());
  }

  /**
   * Test of getNAD1927StatePlaneOhioNorthFIPS3401 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneOhioNorthFIPS3401() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneOhioNorthFIPS3401());
  }

  /**
   * Test of getNAD1927StatePlaneOhioSouthFIPS3402 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneOhioSouthFIPS3402() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneOhioSouthFIPS3402());
  }

  /**
   * Test of getNAD1927StatePlaneOklahomaNorthFIPS3501 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneOklahomaNorthFIPS3501() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneOklahomaNorthFIPS3501());
  }

  /**
   * Test of getNAD1927StatePlaneOklahomaSouthFIPS3502 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneOklahomaSouthFIPS3502() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneOklahomaSouthFIPS3502());
  }

  /**
   * Test of getNAD1927StatePlaneOregonNorthFIPS3601 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneOregonNorthFIPS3601() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneOregonNorthFIPS3601());
  }

  /**
   * Test of getNAD1927StatePlaneOregonSouthFIPS3602 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneOregonSouthFIPS3602() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneOregonSouthFIPS3602());
  }

  /**
   * Test of getNAD1927StatePlanePennsylvaniaNorthFIPS3701 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlanePennsylvaniaNorthFIPS3701() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlanePennsylvaniaNorthFIPS3701());
  }

  /**
   * Test of getNAD1927StatePlanePennsylvaniaSouthFIPS3702 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlanePennsylvaniaSouthFIPS3702() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlanePennsylvaniaSouthFIPS3702());
  }

  /**
   * Test of getNAD1927StatePlanePuertoRicoFIPS5201 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlanePuertoRicoFIPS5201() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlanePuertoRicoFIPS5201());
  }

  /**
   * Test of getNAD1927StatePlaneRhodeIslandFIPS3800 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneRhodeIslandFIPS3800() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneRhodeIslandFIPS3800());
  }

  /**
   * Test of getNAD1927StatePlaneSouthCarolinaNorthFIPS3901 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneSouthCarolinaNorthFIPS3901() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneSouthCarolinaNorthFIPS3901());
  }

  /**
   * Test of getNAD1927StatePlaneSouthCarolinaSouthFIPS3902 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneSouthCarolinaSouthFIPS3902() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneSouthCarolinaSouthFIPS3902());
  }

  /**
   * Test of getNAD1927StatePlaneSouthDakotaNorthFIPS4001 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneSouthDakotaNorthFIPS4001() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneSouthDakotaNorthFIPS4001());
  }

  /**
   * Test of getNAD1927StatePlaneSouthDakotaSouthFIPS4002 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneSouthDakotaSouthFIPS4002() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneSouthDakotaSouthFIPS4002());
  }

  /**
   * Test of getNAD1927StatePlaneTennesseeFIPS4100 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneTennesseeFIPS4100() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneTennesseeFIPS4100());
  }

  /**
   * Test of getNAD1927StatePlaneTexasCentralFIPS4203 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneTexasCentralFIPS4203() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneTexasCentralFIPS4203());
  }

  /**
   * Test of getNAD1927StatePlaneTexasNorthCentralFIPS4202 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneTexasNorthCentralFIPS4202() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneTexasNorthCentralFIPS4202());
  }

  /**
   * Test of getNAD1927StatePlaneTexasNorthFIPS4201 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneTexasNorthFIPS4201() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneTexasNorthFIPS4201());
  }

  /**
   * Test of getNAD1927StatePlaneTexasSouthCentralFIPS4204 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneTexasSouthCentralFIPS4204() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneTexasSouthCentralFIPS4204());
  }

  /**
   * Test of getNAD1927StatePlaneTexasSouthFIPS4205 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneTexasSouthFIPS4205() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneTexasSouthFIPS4205());
  }

  /**
   * Test of getNAD1927StatePlaneUtahCentralFIPS4302 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneUtahCentralFIPS4302() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneUtahCentralFIPS4302());
  }

  /**
   * Test of getNAD1927StatePlaneUtahNorthFIPS4301 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneUtahNorthFIPS4301() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneUtahNorthFIPS4301());
  }

  /**
   * Test of getNAD1927StatePlaneUtahSouthFIPS4303 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneUtahSouthFIPS4303() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneUtahSouthFIPS4303());
  }

  /**
   * Test of getNAD1927StatePlaneVermontFIPS3400 method, of class StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneVermontFIPS3400() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneVermontFIPS3400());
  }

  /**
   * Test of getNAD1927StatePlaneVirginiaNorthFIPS4501 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneVirginiaNorthFIPS4501() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneVirginiaNorthFIPS4501());
  }

  /**
   * Test of getNAD1927StatePlaneVirginiaSouthFIPS4502 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneVirginiaSouthFIPS4502() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneVirginiaSouthFIPS4502());
  }

  /**
   * Test of getNAD1927StatePlaneWashingtonNorthFIPS4601 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneWashingtonNorthFIPS4601() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneWashingtonNorthFIPS4601());
  }

  /**
   * Test of getNAD1927StatePlaneWashingtonSouthFIPS4602 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneWashingtonSouthFIPS4602() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneWashingtonSouthFIPS4602());
  }

  /**
   * Test of getNAD1927StatePlaneWestVirginiaNorthFIPS4701 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneWestVirginiaNorthFIPS4701() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneWestVirginiaNorthFIPS4701());
  }

  /**
   * Test of getNAD1927StatePlaneWestVirginiaSouthFIPS4702 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneWestVirginiaSouthFIPS4702() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneWestVirginiaSouthFIPS4702());
  }

  /**
   * Test of getNAD1927StatePlaneWisconsinCentralFIPS4802 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneWisconsinCentralFIPS4802() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneWisconsinCentralFIPS4802());
  }

  /**
   * Test of getNAD1927StatePlaneWisconsinNorthFIPS4801 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneWisconsinNorthFIPS4801() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneWisconsinNorthFIPS4801());
  }

  /**
   * Test of getNAD1927StatePlaneWisconsinSouthFIPS4803 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneWisconsinSouthFIPS4803() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneWisconsinSouthFIPS4803());
  }

  /**
   * Test of getNAD1927StatePlaneWyomingEastCentralFIPS4902 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneWyomingEastCentralFIPS4902() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneWyomingEastCentralFIPS4902());
  }

  /**
   * Test of getNAD1927StatePlaneWyomingEastFIPS4901 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneWyomingEastFIPS4901() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneWyomingEastFIPS4901());
  }

  /**
   * Test of getNAD1927StatePlaneWyomingWestCentralFIPS4903 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneWyomingWestCentralFIPS4903() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneWyomingWestCentralFIPS4903());
  }

  /**
   * Test of getNAD1927StatePlaneWyomingWestFIPS4904 method, of class
   * StatePlaneNad1927.
   */
  @Test
  public void testNAD1927StatePlaneWyomingWestFIPS4904() {
    testToWGS84AndBack(PROJ.getNAD1927StatePlaneWyomingWestFIPS4904());
  }

}
