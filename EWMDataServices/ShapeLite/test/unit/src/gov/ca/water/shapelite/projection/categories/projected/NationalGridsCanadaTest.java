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
public class NationalGridsCanadaTest {

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
  private static final NationalGridsCanada PROJ
      = Projections.getProjected().getNationalGridsCanada();

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
   * Test of getATS1977MTM4NovaScotia method, of class NationalGridsCanada.
   */
  @Test
  public void testATS1977MTM4NovaScotia() {
    testToWGS84AndBack(PROJ.getATS1977MTM4NovaScotia());
  }

  /**
   * Test of getATS1977MTM5NovaScotia method, of class NationalGridsCanada.
   */
  @Test
  public void testATS1977MTM5NovaScotia() {
    testToWGS84AndBack(PROJ.getATS1977MTM5NovaScotia());
  }

  /**
   * Test of getATS1977NewBrunswickStereographic method, of class NationalGridsCanada.
   */
  @Test
  public void testATS1977NewBrunswickStereographic() {
    testToWGS84AndBack(PROJ.getATS1977NewBrunswickStereographic());
  }

  /**
   * Test of getNAD192710TMAEPForest method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD192710TMAEPForest() {
    testToWGS84AndBack(PROJ.getNAD192710TMAEPForest());
  }

  /**
   * Test of getNAD192710TMAEPResource method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD192710TMAEPResource() {
    testToWGS84AndBack(PROJ.getNAD192710TMAEPResource());
  }

  /**
   * Test of getNAD19273TM111 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD19273TM111() {
    testToWGS84AndBack(PROJ.getNAD19273TM111());
  }

  /**
   * Test of getNAD19273TM114 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD19273TM114() {
    testToWGS84AndBack(PROJ.getNAD19273TM114());
  }

  /**
   * Test of getNAD19273TM117 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD19273TM117() {
    testToWGS84AndBack(PROJ.getNAD19273TM117());
  }

  /**
   * Test of getNAD19273TM120 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD19273TM120() {
    testToWGS84AndBack(PROJ.getNAD19273TM120());
  }

  /**
   * Test of getNAD1927CGQ77MTM10SCoPQ method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927CGQ77MTM10SCoPQ() {
    testToWGS84AndBack(PROJ.getNAD1927CGQ77MTM10SCoPQ());
  }

  /**
   * Test of getNAD1927CGQ77MTM2SCoPQ method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927CGQ77MTM2SCoPQ() {
    testToWGS84AndBack(PROJ.getNAD1927CGQ77MTM2SCoPQ());
  }

  /**
   * Test of getNAD1927CGQ77MTM3SCoPQ method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927CGQ77MTM3SCoPQ() {
    testToWGS84AndBack(PROJ.getNAD1927CGQ77MTM3SCoPQ());
  }

  /**
   * Test of getNAD1927CGQ77MTM4SCoPQ method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927CGQ77MTM4SCoPQ() {
    testToWGS84AndBack(PROJ.getNAD1927CGQ77MTM4SCoPQ());
  }

  /**
   * Test of getNAD1927CGQ77MTM5SCoPQ method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927CGQ77MTM5SCoPQ() {
    testToWGS84AndBack(PROJ.getNAD1927CGQ77MTM5SCoPQ());
  }

  /**
   * Test of getNAD1927CGQ77MTM6SCoPQ method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927CGQ77MTM6SCoPQ() {
    testToWGS84AndBack(PROJ.getNAD1927CGQ77MTM6SCoPQ());
  }

  /**
   * Test of getNAD1927CGQ77MTM7SCoPQ method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927CGQ77MTM7SCoPQ() {
    testToWGS84AndBack(PROJ.getNAD1927CGQ77MTM7SCoPQ());
  }

  /**
   * Test of getNAD1927CGQ77MTM8SCoPQ method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927CGQ77MTM8SCoPQ() {
    testToWGS84AndBack(PROJ.getNAD1927CGQ77MTM8SCoPQ());
  }

  /**
   * Test of getNAD1927CGQ77MTM9SCoPQ method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927CGQ77MTM9SCoPQ() {
    testToWGS84AndBack(PROJ.getNAD1927CGQ77MTM9SCoPQ());
  }

  /**
   * Test of getNAD1927CGQ77QuebecLambert method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927CGQ77QuebecLambert() {
    testToWGS84AndBack(PROJ.getNAD1927CGQ77QuebecLambert());
  }

  /**
   * Test of getNAD1927CGQ77UTMZone17N method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927CGQ77UTMZone17N() {
    testToWGS84AndBack(PROJ.getNAD1927CGQ77UTMZone17N());
  }

  /**
   * Test of getNAD1927CGQ77UTMZone18N method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927CGQ77UTMZone18N() {
    testToWGS84AndBack(PROJ.getNAD1927CGQ77UTMZone18N());
  }

  /**
   * Test of getNAD1927CGQ77UTMZone19N method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927CGQ77UTMZone19N() {
    testToWGS84AndBack(PROJ.getNAD1927CGQ77UTMZone19N());
  }

  /**
   * Test of getNAD1927CGQ77UTMZone20N method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927CGQ77UTMZone20N() {
    testToWGS84AndBack(PROJ.getNAD1927CGQ77UTMZone20N());
  }

  /**
   * Test of getNAD1927CGQ77UTMZone21N method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927CGQ77UTMZone21N() {
    testToWGS84AndBack(PROJ.getNAD1927CGQ77UTMZone21N());
  }

  /**
   * Test of getNAD1927DEF1976MTM10 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927DEF1976MTM10() {
    testToWGS84AndBack(PROJ.getNAD1927DEF1976MTM10());
  }

  /**
   * Test of getNAD1927DEF1976MTM11 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927DEF1976MTM11() {
    testToWGS84AndBack(PROJ.getNAD1927DEF1976MTM11());
  }

  /**
   * Test of getNAD1927DEF1976MTM12 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927DEF1976MTM12() {
    testToWGS84AndBack(PROJ.getNAD1927DEF1976MTM12());
  }

  /**
   * Test of getNAD1927DEF1976MTM13 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927DEF1976MTM13() {
    testToWGS84AndBack(PROJ.getNAD1927DEF1976MTM13());
  }

  /**
   * Test of getNAD1927DEF1976MTM14 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927DEF1976MTM14() {
    testToWGS84AndBack(PROJ.getNAD1927DEF1976MTM14());
  }

  /**
   * Test of getNAD1927DEF1976MTM15 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927DEF1976MTM15() {
    testToWGS84AndBack(PROJ.getNAD1927DEF1976MTM15());
  }

  /**
   * Test of getNAD1927DEF1976MTM16 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927DEF1976MTM16() {
    testToWGS84AndBack(PROJ.getNAD1927DEF1976MTM16());
  }

  /**
   * Test of getNAD1927DEF1976MTM17 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927DEF1976MTM17() {
    testToWGS84AndBack(PROJ.getNAD1927DEF1976MTM17());
  }

  /**
   * Test of getNAD1927DEF1976MTM8 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927DEF1976MTM8() {
    testToWGS84AndBack(PROJ.getNAD1927DEF1976MTM8());
  }

  /**
   * Test of getNAD1927DEF1976MTM9 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927DEF1976MTM9() {
    testToWGS84AndBack(PROJ.getNAD1927DEF1976MTM9());
  }

  /**
   * Test of getNAD1927DEF1976UTMZone15N method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927DEF1976UTMZone15N() {
    testToWGS84AndBack(PROJ.getNAD1927DEF1976UTMZone15N());
  }

  /**
   * Test of getNAD1927DEF1976UTMZone16N method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927DEF1976UTMZone16N() {
    testToWGS84AndBack(PROJ.getNAD1927DEF1976UTMZone16N());
  }

  /**
   * Test of getNAD1927DEF1976UTMZone17N method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927DEF1976UTMZone17N() {
    testToWGS84AndBack(PROJ.getNAD1927DEF1976UTMZone17N());
  }

  /**
   * Test of getNAD1927DEF1976UTMZone18N method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927DEF1976UTMZone18N() {
    testToWGS84AndBack(PROJ.getNAD1927DEF1976UTMZone18N());
  }

  /**
   * Test of getNAD1927MTM1 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927MTM1() {
    testToWGS84AndBack(PROJ.getNAD1927MTM1());
  }

  /**
   * Test of getNAD1927MTM2 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927MTM2() {
    testToWGS84AndBack(PROJ.getNAD1927MTM2());
  }

  /**
   * Test of getNAD1927MTM3 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927MTM3() {
    testToWGS84AndBack(PROJ.getNAD1927MTM3());
  }

  /**
   * Test of getNAD1927MTM4 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927MTM4() {
    testToWGS84AndBack(PROJ.getNAD1927MTM4());
  }

  /**
   * Test of getNAD1927MTM5 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927MTM5() {
    testToWGS84AndBack(PROJ.getNAD1927MTM5());
  }

  /**
   * Test of getNAD1927MTM6 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927MTM6() {
    testToWGS84AndBack(PROJ.getNAD1927MTM6());
  }

  /**
   * Test of getNAD1927QuebecLambert method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1927QuebecLambert() {
    testToWGS84AndBack(PROJ.getNAD1927QuebecLambert());
  }

  /**
   * Test of getNAD198310TMAEPForest method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD198310TMAEPForest() {
    testToWGS84AndBack(PROJ.getNAD198310TMAEPForest());
  }

  /**
   * Test of getNAD198310TMAEPResource method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD198310TMAEPResource() {
    testToWGS84AndBack(PROJ.getNAD198310TMAEPResource());
  }

  /**
   * Test of getNAD19833TM111 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD19833TM111() {
    testToWGS84AndBack(PROJ.getNAD19833TM111());
  }

  /**
   * Test of getNAD19833TM114 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD19833TM114() {
    testToWGS84AndBack(PROJ.getNAD19833TM114());
  }

  /**
   * Test of getNAD19833TM117 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD19833TM117() {
    testToWGS84AndBack(PROJ.getNAD19833TM117());
  }

  /**
   * Test of getNAD19833TM120 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD19833TM120() {
    testToWGS84AndBack(PROJ.getNAD19833TM120());
  }

  /**
   * Test of getNAD1983BCEnvironmentAlbers method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983BCEnvironmentAlbers() {
    testToWGS84AndBack(PROJ.getNAD1983BCEnvironmentAlbers());
  }

  /**
   * Test of getNAD1983CSRS98MTM10 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983CSRS98MTM10() {
    testToWGS84AndBack(PROJ.getNAD1983CSRS98MTM10());
  }

  /**
   * Test of getNAD1983CSRS98MTM2SCoPQ method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983CSRS98MTM2SCoPQ() {
    testToWGS84AndBack(PROJ.getNAD1983CSRS98MTM2SCoPQ());
  }

  /**
   * Test of getNAD1983CSRS98MTM3 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983CSRS98MTM3() {
    testToWGS84AndBack(PROJ.getNAD1983CSRS98MTM3());
  }

  /**
   * Test of getNAD1983CSRS98MTM4 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983CSRS98MTM4() {
    testToWGS84AndBack(PROJ.getNAD1983CSRS98MTM4());
  }

  /**
   * Test of getNAD1983CSRS98MTM5 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983CSRS98MTM5() {
    testToWGS84AndBack(PROJ.getNAD1983CSRS98MTM5());
  }

  /**
   * Test of getNAD1983CSRS98MTM6 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983CSRS98MTM6() {
    testToWGS84AndBack(PROJ.getNAD1983CSRS98MTM6());
  }

  /**
   * Test of getNAD1983CSRS98MTM7 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983CSRS98MTM7() {
    testToWGS84AndBack(PROJ.getNAD1983CSRS98MTM7());
  }

  /**
   * Test of getNAD1983CSRS98MTM8 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983CSRS98MTM8() {
    testToWGS84AndBack(PROJ.getNAD1983CSRS98MTM8());
  }

  /**
   * Test of getNAD1983CSRS98MTM9 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983CSRS98MTM9() {
    testToWGS84AndBack(PROJ.getNAD1983CSRS98MTM9());
  }

  /**
   * Test of getNAD1983CSRS98NewBrunswickStereographic method, of class
   * NationalGridsCanada.
   */
  @Test
  public void testNAD1983CSRS98NewBrunswickStereographic() {
    testToWGS84AndBack(PROJ.getNAD1983CSRS98NewBrunswickStereographic());
  }

  /**
   * Test of getNAD1983CSRS98PrinceEdwardIsland method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983CSRS98PrinceEdwardIsland() {
    testToWGS84AndBack(PROJ.getNAD1983CSRS98PrinceEdwardIsland());
  }

  /**
   * Test of getNAD1983CSRS98UTMZone11N method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983CSRS98UTMZone11N() {
    testToWGS84AndBack(PROJ.getNAD1983CSRS98UTMZone11N());
  }

  /**
   * Test of getNAD1983CSRS98UTMZone12N method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983CSRS98UTMZone12N() {
    testToWGS84AndBack(PROJ.getNAD1983CSRS98UTMZone12N());
  }

  /**
   * Test of getNAD1983CSRS98UTMZone13N method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983CSRS98UTMZone13N() {
    testToWGS84AndBack(PROJ.getNAD1983CSRS98UTMZone13N());
  }

  /**
   * Test of getNAD1983CSRS98UTMZone17N method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983CSRS98UTMZone17N() {
    testToWGS84AndBack(PROJ.getNAD1983CSRS98UTMZone17N());
  }

  /**
   * Test of getNAD1983CSRS98UTMZone18N method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983CSRS98UTMZone18N() {
    testToWGS84AndBack(PROJ.getNAD1983CSRS98UTMZone18N());
  }

  /**
   * Test of getNAD1983CSRS98UTMZone19N method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983CSRS98UTMZone19N() {
    testToWGS84AndBack(PROJ.getNAD1983CSRS98UTMZone19N());
  }

  /**
   * Test of getNAD1983CSRS98UTMZone20N method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983CSRS98UTMZone20N() {
    testToWGS84AndBack(PROJ.getNAD1983CSRS98UTMZone20N());
  }

  /**
   * Test of getNAD1983CSRS98UTMZone21N method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983CSRS98UTMZone21N() {
    testToWGS84AndBack(PROJ.getNAD1983CSRS98UTMZone21N());
  }

  /**
   * Test of getNAD1983MTM1 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983MTM1() {
    testToWGS84AndBack(PROJ.getNAD1983MTM1());
  }

  /**
   * Test of getNAD1983MTM10 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983MTM10() {
    testToWGS84AndBack(PROJ.getNAD1983MTM10());
  }

  /**
   * Test of getNAD1983MTM11 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983MTM11() {
    testToWGS84AndBack(PROJ.getNAD1983MTM11());
  }

  /**
   * Test of getNAD1983MTM12 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983MTM12() {
    testToWGS84AndBack(PROJ.getNAD1983MTM12());
  }

  /**
   * Test of getNAD1983MTM13 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983MTM13() {
    testToWGS84AndBack(PROJ.getNAD1983MTM13());
  }

  /**
   * Test of getNAD1983MTM14 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983MTM14() {
    testToWGS84AndBack(PROJ.getNAD1983MTM14());
  }

  /**
   * Test of getNAD1983MTM15 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983MTM15() {
    testToWGS84AndBack(PROJ.getNAD1983MTM15());
  }

  /**
   * Test of getNAD1983MTM16 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983MTM16() {
    testToWGS84AndBack(PROJ.getNAD1983MTM16());
  }

  /**
   * Test of getNAD1983MTM17 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983MTM17() {
    testToWGS84AndBack(PROJ.getNAD1983MTM17());
  }

  /**
   * Test of getNAD1983MTM2 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983MTM2() {
    testToWGS84AndBack(PROJ.getNAD1983MTM2());
  }

  /**
   * Test of getNAD1983MTM2SCoPQ method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983MTM2SCoPQ() {
    testToWGS84AndBack(PROJ.getNAD1983MTM2SCoPQ());
  }

  /**
   * Test of getNAD1983MTM3 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983MTM3() {
    testToWGS84AndBack(PROJ.getNAD1983MTM3());
  }

  /**
   * Test of getNAD1983MTM4 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983MTM4() {
    testToWGS84AndBack(PROJ.getNAD1983MTM4());
  }

  /**
   * Test of getNAD1983MTM5 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983MTM5() {
    testToWGS84AndBack(PROJ.getNAD1983MTM5());
  }

  /**
   * Test of getNAD1983MTM6 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983MTM6() {
    testToWGS84AndBack(PROJ.getNAD1983MTM6());
  }

  /**
   * Test of getNAD1983MTM7 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983MTM7() {
    testToWGS84AndBack(PROJ.getNAD1983MTM7());
  }

  /**
   * Test of getNAD1983MTM8 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983MTM8() {
    testToWGS84AndBack(PROJ.getNAD1983MTM8());
  }

  /**
   * Test of getNAD1983MTM9 method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983MTM9() {
    testToWGS84AndBack(PROJ.getNAD1983MTM9());
  }

  /**
   * Test of getNAD1983QuebecLambert method, of class NationalGridsCanada.
   */
  @Test
  public void testNAD1983QuebecLambert() {
    testToWGS84AndBack(PROJ.getNAD1983QuebecLambert());
  }

  /**
   * Test of getPrinceEdwardIslandStereographic method, of class NationalGridsCanada.
   */
  @Test
  public void testPrinceEdwardIslandStereographic() {
    testToWGS84AndBack(PROJ.getPrinceEdwardIslandStereographic());
  }

}
