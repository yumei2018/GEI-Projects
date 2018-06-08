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
package gov.ca.water.shapelite.projection.categories.geographic;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import gov.ca.water.shapelite.projection.categories.projected.UtmOtherTest;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CountySystemsTest {

  /**
   * Since Geographic coordinates are in decimal degree, this should be a small
   * number. The value 0.00000001 represents about 1 mm.
   */
  private static final double TOLERANCE = 0.00000001;

  /**
   * The WGS84 projection, which is used to test going to geographic coordinates
   * and back.
   */
  private static final ProjectionInfo WGS84
      = Projections.getGeographic().getWorld().getWGS1984();

  /**
   * The UtmOther projection coordinate system list.
   */
  private static final CountySystems PROJ = Projections.getGeographic().getCountySystems();

  /**
   * Tests converting values to WGS84 and back.
   *
   * @param projection The ProjectionInfo to test.
   */
  public void testToWGS84AndBack(ProjectionInfo projection) {

    try {
      Coord original = new CoordXY(10, 10);
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
   * Test of getNAD1983HARNAdjMNAnoka method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNAnoka() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNAnoka());
  }

  /**
   * Test of getNAD1983HARNAdjMNBecker method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNBecker() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBecker());
  }

  /**
   * Test of getNAD1983HARNAdjMNBeltramiNorth method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNBeltramiNorth() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBeltramiNorth());
  }

  /**
   * Test of getNAD1983HARNAdjMNBeltramiSouth method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNBeltramiSouth() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBeltramiSouth());
  }

  /**
   * Test of getNAD1983HARNAdjMNBenton method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNBenton() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBenton());
  }

  /**
   * Test of getNAD1983HARNAdjMNBigStone method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNBigStone() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBigStone());
  }

  /**
   * Test of getNAD1983HARNAdjMNBlueEarth method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNBlueEarth() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBlueEarth());
  }

  /**
   * Test of getNAD1983HARNAdjMNBrown method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNBrown() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBrown());
  }

  /**
   * Test of getNAD1983HARNAdjMNCarlton method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNCarlton() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCarlton());
  }

  /**
   * Test of getNAD1983HARNAdjMNCarver method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNCarver() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCarver());
  }

  /**
   * Test of getNAD1983HARNAdjMNCassNorth method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNCassNorth() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCassNorth());
  }

  /**
   * Test of getNAD1983HARNAdjMNCassSouth method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNCassSouth() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCassSouth());
  }

  /**
   * Test of getNAD1983HARNAdjMNChippewa method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNChippewa() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNChippewa());
  }

  /**
   * Test of getNAD1983HARNAdjMNChisago method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNChisago() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNChisago());
  }

  /**
   * Test of getNAD1983HARNAdjMNCookNorth method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNCookNorth() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCookNorth());
  }

  /**
   * Test of getNAD1983HARNAdjMNCookSouth method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNCookSouth() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCookSouth());
  }

  /**
   * Test of getNAD1983HARNAdjMNCottonwood method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNCottonwood() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCottonwood());
  }

  /**
   * Test of getNAD1983HARNAdjMNCrowWing method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNCrowWing() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCrowWing());
  }

  /**
   * Test of getNAD1983HARNAdjMNDakota method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNDakota() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNDakota());
  }

  /**
   * Test of getNAD1983HARNAdjMNDodge method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNDodge() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNDodge());
  }

  /**
   * Test of getNAD1983HARNAdjMNDouglas method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNDouglas() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNDouglas());
  }

  /**
   * Test of getNAD1983HARNAdjMNFaribault method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNFaribault() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNFaribault());
  }

  /**
   * Test of getNAD1983HARNAdjMNFillmore method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNFillmore() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNFillmore());
  }

  /**
   * Test of getNAD1983HARNAdjMNFreeborn method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNFreeborn() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNFreeborn());
  }

  /**
   * Test of getNAD1983HARNAdjMNGoodhue method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNGoodhue() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNGoodhue());
  }

  /**
   * Test of getNAD1983HARNAdjMNGrant method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNGrant() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNGrant());
  }

  /**
   * Test of getNAD1983HARNAdjMNHennepin method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNHennepin() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNHennepin());
  }

  /**
   * Test of getNAD1983HARNAdjMNHouston method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNHouston() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNHouston());
  }

  /**
   * Test of getNAD1983HARNAdjMNIsanti method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNIsanti() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNIsanti());
  }

  /**
   * Test of getNAD1983HARNAdjMNItascaNorth method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNItascaNorth() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNItascaNorth());
  }

  /**
   * Test of getNAD1983HARNAdjMNItascaSouth method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNItascaSouth() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNItascaSouth());
  }

  /**
   * Test of getNAD1983HARNAdjMNJackson method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNJackson() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNJackson());
  }

  /**
   * Test of getNAD1983HARNAdjMNKanabec method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNKanabec() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNKanabec());
  }

  /**
   * Test of getNAD1983HARNAdjMNKandiyohi method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNKandiyohi() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNKandiyohi());
  }

  /**
   * Test of getNAD1983HARNAdjMNKittson method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNKittson() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNKittson());
  }

  /**
   * Test of getNAD1983HARNAdjMNKoochiching method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNKoochiching() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNKoochiching());
  }

  /**
   * Test of getNAD1983HARNAdjMNLacQuiParle method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNLacQuiParle() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLacQuiParle());
  }

  /**
   * Test of getNAD1983HARNAdjMNLakeoftheWoodsNorth method, of class
   * CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNLakeoftheWoodsNorth() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLakeoftheWoodsNorth());
  }

  /**
   * Test of getNAD1983HARNAdjMNLakeoftheWoodsSouth method, of class
   * CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNLakeoftheWoodsSouth() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLakeoftheWoodsSouth());
  }

  /**
   * Test of getNAD1983HARNAdjMNLeSueur method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNLeSueur() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLeSueur());
  }

  /**
   * Test of getNAD1983HARNAdjMNLincoln method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNLincoln() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLincoln());
  }

  /**
   * Test of getNAD1983HARNAdjMNLyon method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNLyon() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLyon());
  }

  /**
   * Test of getNAD1983HARNAdjMNMahnomen method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNMahnomen() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMahnomen());
  }

  /**
   * Test of getNAD1983HARNAdjMNMarshall method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNMarshall() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMarshall());
  }

  /**
   * Test of getNAD1983HARNAdjMNMartin method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNMartin() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMartin());
  }

  /**
   * Test of getNAD1983HARNAdjMNMcLeod method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNMcLeod() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMcLeod());
  }

  /**
   * Test of getNAD1983HARNAdjMNMeeker method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNMeeker() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMeeker());
  }

  /**
   * Test of getNAD1983HARNAdjMNMorrison method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNMorrison() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMorrison());
  }

  /**
   * Test of getNAD1983HARNAdjMNMower method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNMower() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMower());
  }

  /**
   * Test of getNAD1983HARNAdjMNMurray method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNMurray() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMurray());
  }

  /**
   * Test of getNAD1983HARNAdjMNNicollet method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNNicollet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNNicollet());
  }

  /**
   * Test of getNAD1983HARNAdjMNNobles method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNNobles() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNNobles());
  }

  /**
   * Test of getNAD1983HARNAdjMNNorman method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNNorman() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNNorman());
  }

  /**
   * Test of getNAD1983HARNAdjMNOlmsted method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNOlmsted() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNOlmsted());
  }

  /**
   * Test of getNAD1983HARNAdjMNOttertail method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNOttertail() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNOttertail());
  }

  /**
   * Test of getNAD1983HARNAdjMNPennington method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNPennington() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNPennington());
  }

  /**
   * Test of getNAD1983HARNAdjMNPine method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNPine() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNPine());
  }

  /**
   * Test of getNAD1983HARNAdjMNPipestone method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNPipestone() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNPipestone());
  }

  /**
   * Test of getNAD1983HARNAdjMNPolk method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNPolk() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNPolk());
  }

  /**
   * Test of getNAD1983HARNAdjMNPope method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNPope() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNPope());
  }

  /**
   * Test of getNAD1983HARNAdjMNRamsey method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNRamsey() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRamsey());
  }

  /**
   * Test of getNAD1983HARNAdjMNRedLake method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNRedLake() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRedLake());
  }

  /**
   * Test of getNAD1983HARNAdjMNRedwood method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNRedwood() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRedwood());
  }

  /**
   * Test of getNAD1983HARNAdjMNRenville method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNRenville() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRenville());
  }

  /**
   * Test of getNAD1983HARNAdjMNRice method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNRice() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRice());
  }

  /**
   * Test of getNAD1983HARNAdjMNRock method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNRock() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRock());
  }

  /**
   * Test of getNAD1983HARNAdjMNRoseau method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNRoseau() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRoseau());
  }

  /**
   * Test of getNAD1983HARNAdjMNScott method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNScott() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNScott());
  }

  /**
   * Test of getNAD1983HARNAdjMNSherburne method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNSherburne() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNSherburne());
  }

  /**
   * Test of getNAD1983HARNAdjMNSibley method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNSibley() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNSibley());
  }

  /**
   * Test of getNAD1983HARNAdjMNStLouisCentral method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNStLouisCentral() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNStLouisCentral());
  }

  /**
   * Test of getNAD1983HARNAdjMNStLouisNorth method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNStLouisNorth() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNStLouisNorth());
  }

  /**
   * Test of getNAD1983HARNAdjMNStLouisSouth method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNStLouisSouth() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNStLouisSouth());
  }

  /**
   * Test of getNAD1983HARNAdjMNStearns method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNStearns() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNStearns());
  }

  /**
   * Test of getNAD1983HARNAdjMNSteele method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNSteele() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNSteele());
  }

  /**
   * Test of getNAD1983HARNAdjMNStevens method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNStevens() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNStevens());
  }

  /**
   * Test of getNAD1983HARNAdjMNSwift method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNSwift() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNSwift());
  }

  /**
   * Test of getNAD1983HARNAdjMNTodd method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNTodd() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNTodd());
  }

  /**
   * Test of getNAD1983HARNAdjMNTraverse method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNTraverse() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNTraverse());
  }

  /**
   * Test of getNAD1983HARNAdjMNWabasha method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNWabasha() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWabasha());
  }

  /**
   * Test of getNAD1983HARNAdjMNWadena method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNWadena() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWadena());
  }

  /**
   * Test of getNAD1983HARNAdjMNWaseca method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNWaseca() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWaseca());
  }

  /**
   * Test of getNAD1983HARNAdjMNWatonwan method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNWatonwan() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWatonwan());
  }

  /**
   * Test of getNAD1983HARNAdjMNWinona method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNWinona() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWinona());
  }

  /**
   * Test of getNAD1983HARNAdjMNWright method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNWright() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWright());
  }

  /**
   * Test of getNAD1983HARNAdjMNYellowMedicine method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjMNYellowMedicine() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNYellowMedicine());
  }

  /**
   * Test of getNAD1983HARNAdjWIAdams method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIAdams() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIAdams());
  }

  /**
   * Test of getNAD1983HARNAdjWIAshland method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIAshland() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIAshland());
  }

  /**
   * Test of getNAD1983HARNAdjWIBarron method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIBarron() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIBarron());
  }

  /**
   * Test of getNAD1983HARNAdjWIBayfield method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIBayfield() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIBayfield());
  }

  /**
   * Test of getNAD1983HARNAdjWIBrown method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIBrown() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIBrown());
  }

  /**
   * Test of getNAD1983HARNAdjWIBuffalo method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIBuffalo() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIBuffalo());
  }

  /**
   * Test of getNAD1983HARNAdjWIBurnett method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIBurnett() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIBurnett());
  }

  /**
   * Test of getNAD1983HARNAdjWICalumet method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWICalumet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWICalumet());
  }

  /**
   * Test of getNAD1983HARNAdjWIChippewa method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIChippewa() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIChippewa());
  }

  /**
   * Test of getNAD1983HARNAdjWIClark method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIClark() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIClark());
  }

  /**
   * Test of getNAD1983HARNAdjWIColumbia method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIColumbia() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIColumbia());
  }

  /**
   * Test of getNAD1983HARNAdjWICrawford method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWICrawford() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWICrawford());
  }

  /**
   * Test of getNAD1983HARNAdjWIDane method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIDane() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIDane());
  }

  /**
   * Test of getNAD1983HARNAdjWIDodge method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIDodge() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIDodge());
  }

  /**
   * Test of getNAD1983HARNAdjWIDoor method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIDoor() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIDoor());
  }

  /**
   * Test of getNAD1983HARNAdjWIDouglas method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIDouglas() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIDouglas());
  }

  /**
   * Test of getNAD1983HARNAdjWIDunn method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIDunn() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIDunn());
  }

  /**
   * Test of getNAD1983HARNAdjWIEauClaire method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIEauClaire() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIEauClaire());
  }

  /**
   * Test of getNAD1983HARNAdjWIFlorence method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIFlorence() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIFlorence());
  }

  /**
   * Test of getNAD1983HARNAdjWIFondduLac method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIFondduLac() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIFondduLac());
  }

  /**
   * Test of getNAD1983HARNAdjWIForest method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIForest() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIForest());
  }

  /**
   * Test of getNAD1983HARNAdjWIGrant method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIGrant() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIGrant());
  }

  /**
   * Test of getNAD1983HARNAdjWIGreen method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIGreen() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIGreen());
  }

  /**
   * Test of getNAD1983HARNAdjWIGreenLake method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIGreenLake() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIGreenLake());
  }

  /**
   * Test of getNAD1983HARNAdjWIIowa method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIIowa() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIIowa());
  }

  /**
   * Test of getNAD1983HARNAdjWIIron method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIIron() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIIron());
  }

  /**
   * Test of getNAD1983HARNAdjWIJackson method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIJackson() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIJackson());
  }

  /**
   * Test of getNAD1983HARNAdjWIJefferson method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIJefferson() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIJefferson());
  }

  /**
   * Test of getNAD1983HARNAdjWIJuneau method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIJuneau() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIJuneau());
  }

  /**
   * Test of getNAD1983HARNAdjWIKenosha method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIKenosha() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIKenosha());
  }

  /**
   * Test of getNAD1983HARNAdjWIKewaunee method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIKewaunee() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIKewaunee());
  }

  /**
   * Test of getNAD1983HARNAdjWILaCrosse method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWILaCrosse() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWILaCrosse());
  }

  /**
   * Test of getNAD1983HARNAdjWILafayette method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWILafayette() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWILafayette());
  }

  /**
   * Test of getNAD1983HARNAdjWILanglade method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWILanglade() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWILanglade());
  }

  /**
   * Test of getNAD1983HARNAdjWILincoln method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWILincoln() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWILincoln());
  }

  /**
   * Test of getNAD1983HARNAdjWIManitowoc method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIManitowoc() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIManitowoc());
  }

  /**
   * Test of getNAD1983HARNAdjWIMarathon method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIMarathon() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIMarathon());
  }

  /**
   * Test of getNAD1983HARNAdjWIMarinette method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIMarinette() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIMarinette());
  }

  /**
   * Test of getNAD1983HARNAdjWIMarquette method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIMarquette() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIMarquette());
  }

  /**
   * Test of getNAD1983HARNAdjWIMenominee method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIMenominee() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIMenominee());
  }

  /**
   * Test of getNAD1983HARNAdjWIMilwaukee method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIMilwaukee() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIMilwaukee());
  }

  /**
   * Test of getNAD1983HARNAdjWIMonroe method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIMonroe() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIMonroe());
  }

  /**
   * Test of getNAD1983HARNAdjWIOconto method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIOconto() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIOconto());
  }

  /**
   * Test of getNAD1983HARNAdjWIOneida method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIOneida() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIOneida());
  }

  /**
   * Test of getNAD1983HARNAdjWIOutagamie method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIOutagamie() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIOutagamie());
  }

  /**
   * Test of getNAD1983HARNAdjWIOzaukee method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIOzaukee() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIOzaukee());
  }

  /**
   * Test of getNAD1983HARNAdjWIPepin method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIPepin() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIPepin());
  }

  /**
   * Test of getNAD1983HARNAdjWIPierce method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIPierce() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIPierce());
  }

  /**
   * Test of getNAD1983HARNAdjWIPolk method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIPolk() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIPolk());
  }

  /**
   * Test of getNAD1983HARNAdjWIPortage method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIPortage() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIPortage());
  }

  /**
   * Test of getNAD1983HARNAdjWIPrice method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIPrice() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIPrice());
  }

  /**
   * Test of getNAD1983HARNAdjWIRacine method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIRacine() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIRacine());
  }

  /**
   * Test of getNAD1983HARNAdjWIRichland method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIRichland() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIRichland());
  }

  /**
   * Test of getNAD1983HARNAdjWIRock method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIRock() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIRock());
  }

  /**
   * Test of getNAD1983HARNAdjWIRusk method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIRusk() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIRusk());
  }

  /**
   * Test of getNAD1983HARNAdjWISauk method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWISauk() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWISauk());
  }

  /**
   * Test of getNAD1983HARNAdjWISawyer method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWISawyer() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWISawyer());
  }

  /**
   * Test of getNAD1983HARNAdjWIShawano method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIShawano() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIShawano());
  }

  /**
   * Test of getNAD1983HARNAdjWISheboygan method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWISheboygan() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWISheboygan());
  }

  /**
   * Test of getNAD1983HARNAdjWIStCroix method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIStCroix() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIStCroix());
  }

  /**
   * Test of getNAD1983HARNAdjWITaylor method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWITaylor() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWITaylor());
  }

  /**
   * Test of getNAD1983HARNAdjWITrempealeau method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWITrempealeau() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWITrempealeau());
  }

  /**
   * Test of getNAD1983HARNAdjWIVernon method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIVernon() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIVernon());
  }

  /**
   * Test of getNAD1983HARNAdjWIVilas method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIVilas() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIVilas());
  }

  /**
   * Test of getNAD1983HARNAdjWIWalworth method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIWalworth() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWalworth());
  }

  /**
   * Test of getNAD1983HARNAdjWIWashburn method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIWashburn() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWashburn());
  }

  /**
   * Test of getNAD1983HARNAdjWIWashington method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIWashington() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWashington());
  }

  /**
   * Test of getNAD1983HARNAdjWIWaukesha method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIWaukesha() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWaukesha());
  }

  /**
   * Test of getNAD1983HARNAdjWIWaupaca method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIWaupaca() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWaupaca());
  }

  /**
   * Test of getNAD1983HARNAdjWIWaushara method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIWaushara() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWaushara());
  }

  /**
   * Test of getNAD1983HARNAdjWIWinnebago method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIWinnebago() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWinnebago());
  }

  /**
   * Test of getNAD1983HARNAdjWIWood method, of class CountySystems.
   */
  @Test
  public void testGetNAD1983HARNAdjWIWood() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWood());
  }

}
