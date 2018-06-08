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
public class StatePlaneOtherTest {

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
  private static final StatePlaneOther PROJ = Projections.getProjected().getStatePlaneOther();

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
   * Test of getAmericanSamoa1962StatePlaneAmericanSamoaFIPS5300 method, of
   * class StatePlaneOther.
   */
  @Test
  public void testAmericanSamoa1962StatePlaneAmericanSamoaFIPS5300() {
    testToWGS84AndBack(PROJ.getAmericanSamoa1962StatePlaneAmericanSamoaFIPS5300());
  }

  /**
   * Test of getNAD1983HARNGuamMapGrid method, of class StatePlaneOther.
   */
  @Test
  public void testNAD1983HARNGuamMapGrid() {
    testToWGS84AndBack(PROJ.getNAD1983HARNGuamMapGrid());
  }

  /**
   * Test of getNAD1983HARNUTMZone2S method, of class StatePlaneOther.
   */
  @Test
  public void testNAD1983HARNUTMZone2S() {
    testToWGS84AndBack(PROJ.getNAD1983HARNUTMZone2S());
  }

  /**
   * Test of getNADMichiganStatePlaneMichiganCentralFIPS2112 method, of class
   * StatePlaneOther.
   */
  @Test
  public void testNADMichiganStatePlaneMichiganCentralFIPS2112() {
    testToWGS84AndBack(PROJ.getNADMichiganStatePlaneMichiganCentralFIPS2112());
  }

  /**
   * Test of getNADMichiganStatePlaneMichiganCentralOldFIPS2102 method, of class
   * StatePlaneOther.
   */
  @Test
  public void testNADMichiganStatePlaneMichiganCentralOldFIPS2102() {
    testToWGS84AndBack(PROJ.getNADMichiganStatePlaneMichiganCentralOldFIPS2102());
  }

  /**
   * Test of getNADMichiganStatePlaneMichiganEastOldFIPS2101 method, of class
   * StatePlaneOther.
   */
  @Test
  public void testNADMichiganStatePlaneMichiganEastOldFIPS2101() {
    testToWGS84AndBack(PROJ.getNADMichiganStatePlaneMichiganEastOldFIPS2101());
  }

  /**
   * Test of getNADMichiganStatePlaneMichiganNorthFIPS2111 method, of class
   * StatePlaneOther.
   */
  @Test
  public void testNADMichiganStatePlaneMichiganNorthFIPS2111() {
    testToWGS84AndBack(PROJ.getNADMichiganStatePlaneMichiganNorthFIPS2111());
  }

  /**
   * Test of getNADMichiganStatePlaneMichiganSouthFIPS2113 method, of class
   * StatePlaneOther.
   */
  @Test
  public void testNADMichiganStatePlaneMichiganSouthFIPS2113() {
    testToWGS84AndBack(PROJ.getNADMichiganStatePlaneMichiganSouthFIPS2113());
  }

  /**
   * Test of getNADMichiganStatePlaneMichiganWestOldFIPS2103 method, of class
   * StatePlaneOther.
   */
  @Test
  public void testNADMichiganStatePlaneMichiganWestOldFIPS2103() {
    testToWGS84AndBack(PROJ.getNADMichiganStatePlaneMichiganWestOldFIPS2103());
  }

  /**
   * Test of getOldHawaiianStatePlaneHawaii1FIPS5101 method, of class
   * StatePlaneOther.
   */
  @Test
  public void testOldHawaiianStatePlaneHawaii1FIPS5101() {
    testToWGS84AndBack(PROJ.getOldHawaiianStatePlaneHawaii1FIPS5101());
  }

  /**
   * Test of getOldHawaiianStatePlaneHawaii2FIPS5102 method, of class
   * StatePlaneOther.
   */
  @Test
  public void testOldHawaiianStatePlaneHawaii2FIPS5102() {
    testToWGS84AndBack(PROJ.getOldHawaiianStatePlaneHawaii2FIPS5102());
  }

  /**
   * Test of getOldHawaiianStatePlaneHawaii3FIPS5103 method, of class
   * StatePlaneOther.
   */
  @Test
  public void testOldHawaiianStatePlaneHawaii3FIPS5103() {
    testToWGS84AndBack(PROJ.getOldHawaiianStatePlaneHawaii3FIPS5103());
  }

  /**
   * Test of getOldHawaiianStatePlaneHawaii4FIPS5104 method, of class
   * StatePlaneOther.
   */
  @Test
  public void testOldHawaiianStatePlaneHawaii4FIPS5104() {
    testToWGS84AndBack(PROJ.getOldHawaiianStatePlaneHawaii4FIPS5104());
  }

  /**
   * Test of getOldHawaiianStatePlaneHawaii5FIPS5105 method, of class
   * StatePlaneOther.
   */
  @Test
  public void testOldHawaiianStatePlaneHawaii5FIPS5105() {
    testToWGS84AndBack(PROJ.getOldHawaiianStatePlaneHawaii5FIPS5105());
  }

  /**
   * Test of getPuertoRicoStatePlanePuertoRicoFIPS5201 method, of class
   * StatePlaneOther.
   */
  @Test
  public void testPuertoRicoStatePlanePuertoRicoFIPS5201() {
    testToWGS84AndBack(PROJ.getPuertoRicoStatePlanePuertoRicoFIPS5201());
  }

  /**
   * Test of getPuertoRicoStatePlaneVirginIslandsStCroixFIPS5202 method, of
   * class StatePlaneOther.
   */
  @Test
  public void testPuertoRicoStatePlaneVirginIslandsStCroixFIPS5202() {
    testToWGS84AndBack(PROJ.getPuertoRicoStatePlaneVirginIslandsStCroixFIPS5202());
  }

}
