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
public class MinnesotaTest {

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
  private static final Minnesota PROJ = Projections.getProjected().getMinnesota();

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
   * Test of getNAD1983HARNAdjMNAitkinFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNAitkinFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNAitkinFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNAitkinMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNAitkinMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNAitkinMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNAnokaFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNAnokaFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNAnokaFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNAnokaMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNAnokaMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNAnokaMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNBeckerFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNBeckerFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBeckerFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNBeckerMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNBeckerMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBeckerMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNBeltramiNorthFeet method, of class
   * Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNBeltramiNorthFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBeltramiNorthFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNBeltramiNorthMeters method, of class
   * Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNBeltramiNorthMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBeltramiNorthMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNBeltramiSouthFeet method, of class
   * Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNBeltramiSouthFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBeltramiSouthFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNBeltramiSouthMeters method, of class
   * Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNBeltramiSouthMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBeltramiSouthMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNBentonFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNBentonFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBentonFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNBentonMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNBentonMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBentonMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNBigStoneFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNBigStoneFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBigStoneFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNBigStoneMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNBigStoneMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBigStoneMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNBlueEarthFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNBlueEarthFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBlueEarthFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNBlueEarthMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNBlueEarthMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBlueEarthMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNBrownFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNBrownFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBrownFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNBrownMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNBrownMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNBrownMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNCarltonFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNCarltonFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCarltonFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNCarltonMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNCarltonMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCarltonMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNCarverFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNCarverFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCarverFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNCarverMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNCarverMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCarverMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNCassNorthFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNCassNorthFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCassNorthFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNCassNorthMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNCassNorthMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCassNorthMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNCassSouthFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNCassSouthFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCassSouthFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNCassSouthMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNCassSouthMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCassSouthMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNChippewaFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNChippewaFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNChippewaFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNChippewaMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNChippewaMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNChippewaMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNChisagoFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNChisagoFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNChisagoFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNChisagoMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNChisagoMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNChisagoMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNClayFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNClayFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNClayFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNClayMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNClayMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNClayMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNClearwaterFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNClearwaterFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNClearwaterFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNClearwaterMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNClearwaterMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNClearwaterMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNCookNorthFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNCookNorthFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCookNorthFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNCookNorthMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNCookNorthMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCookNorthMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNCookSouthFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNCookSouthFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCookSouthFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNCookSouthMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNCookSouthMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCookSouthMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNCottonwoodFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNCottonwoodFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCottonwoodFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNCottonwoodMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNCottonwoodMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCottonwoodMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNCrowWingFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNCrowWingFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCrowWingFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNCrowWingMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNCrowWingMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNCrowWingMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNDakotaFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNDakotaFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNDakotaFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNDakotaMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNDakotaMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNDakotaMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNDodgeFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNDodgeFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNDodgeFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNDodgeMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNDodgeMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNDodgeMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNDouglasFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNDouglasFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNDouglasFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNDouglasMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNDouglasMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNDouglasMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNFaribaultFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNFaribaultFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNFaribaultFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNFaribaultMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNFaribaultMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNFaribaultMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNFillmoreFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNFillmoreFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNFillmoreFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNFillmoreMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNFillmoreMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNFillmoreMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNFreebornFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNFreebornFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNFreebornFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNFreebornMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNFreebornMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNFreebornMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNGoodhueFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNGoodhueFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNGoodhueFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNGoodhueMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNGoodhueMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNGoodhueMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNGrantFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNGrantFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNGrantFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNGrantMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNGrantMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNGrantMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNHennepinFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNHennepinFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNHennepinFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNHennepinMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNHennepinMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNHennepinMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNHoustonFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNHoustonFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNHoustonFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNHoustonMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNHoustonMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNHoustonMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNHubbardFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNHubbardFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNHubbardFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNHubbardMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNHubbardMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNHubbardMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNIsantiFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNIsantiFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNIsantiFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNIsantiMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNIsantiMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNIsantiMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNItascaNorthFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNItascaNorthFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNItascaNorthFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNItascaNorthMeters method, of class
   * Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNItascaNorthMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNItascaNorthMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNItascaSouthFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNItascaSouthFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNItascaSouthFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNItascaSouthMeters method, of class
   * Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNItascaSouthMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNItascaSouthMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNJacksonFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNJacksonFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNJacksonFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNJacksonMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNJacksonMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNJacksonMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNKanabecFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNKanabecFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNKanabecFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNKanabecMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNKanabecMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNKanabecMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNKandiyohiFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNKandiyohiFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNKandiyohiFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNKandiyohiMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNKandiyohiMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNKandiyohiMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNKittsonFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNKittsonFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNKittsonFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNKittsonMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNKittsonMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNKittsonMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNKoochichingFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNKoochichingFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNKoochichingFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNKoochichingMeters method, of class
   * Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNKoochichingMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNKoochichingMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNLacQuiParleFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNLacQuiParleFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLacQuiParleFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNLacQuiParleMeters method, of class
   * Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNLacQuiParleMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLacQuiParleMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNLakeFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNLakeFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLakeFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNLakeMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNLakeMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLakeMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNLakeoftheWoodsNorthFeet method, of class
   * Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNLakeoftheWoodsNorthFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLakeoftheWoodsNorthFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNLakeoftheWoodsNorthMeters method, of class
   * Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNLakeoftheWoodsNorthMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLakeoftheWoodsNorthMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNLakeoftheWoodsSouthFeet method, of class
   * Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNLakeoftheWoodsSouthFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLakeoftheWoodsSouthFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNLakeoftheWoodsSouthMeters method, of class
   * Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNLakeoftheWoodsSouthMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLakeoftheWoodsSouthMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNLeSueurFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNLeSueurFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLeSueurFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNLeSueurMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNLeSueurMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLeSueurMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNLincolnFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNLincolnFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLincolnFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNLincolnMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNLincolnMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLincolnMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNLyonFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNLyonFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLyonFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNLyonMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNLyonMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNLyonMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNMahnomenFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNMahnomenFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMahnomenFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNMahnomenMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNMahnomenMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMahnomenMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNMarshallFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNMarshallFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMarshallFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNMarshallMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNMarshallMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMarshallMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNMartinFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNMartinFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMartinFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNMartinMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNMartinMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMartinMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNMcLeodFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNMcLeodFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMcLeodFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNMcLeodMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNMcLeodMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMcLeodMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNMeekerFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNMeekerFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMeekerFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNMeekerMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNMeekerMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMeekerMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNMilleLacsFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNMilleLacsFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMilleLacsFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNMilleLacsMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNMilleLacsMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMilleLacsMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNMorrisonFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNMorrisonFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMorrisonFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNMorrisonMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNMorrisonMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMorrisonMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNMowerFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNMowerFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMowerFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNMowerMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNMowerMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMowerMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNMurrayFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNMurrayFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMurrayFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNMurrayMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNMurrayMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNMurrayMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNNicolletFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNNicolletFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNNicolletFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNNicolletMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNNicolletMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNNicolletMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNNoblesFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNNoblesFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNNoblesFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNNoblesMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNNoblesMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNNoblesMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNNormanFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNNormanFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNNormanFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNNormanMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNNormanMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNNormanMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNOlmstedFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNOlmstedFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNOlmstedFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNOlmstedMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNOlmstedMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNOlmstedMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNOttertailFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNOttertailFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNOttertailFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNOttertailMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNOttertailMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNOttertailMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNPenningtonFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNPenningtonFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNPenningtonFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNPenningtonMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNPenningtonMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNPenningtonMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNPineFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNPineFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNPineFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNPineMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNPineMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNPineMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNPipestoneFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNPipestoneFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNPipestoneFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNPipestoneMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNPipestoneMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNPipestoneMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNPolkFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNPolkFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNPolkFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNPolkMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNPolkMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNPolkMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNPopeFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNPopeFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNPopeFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNPopeMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNPopeMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNPopeMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNRamseyFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNRamseyFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRamseyFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNRamseyMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNRamseyMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRamseyMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNRedLakeFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNRedLakeFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRedLakeFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNRedLakeMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNRedLakeMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRedLakeMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNRedwoodFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNRedwoodFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRedwoodFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNRedwoodMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNRedwoodMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRedwoodMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNRenvilleFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNRenvilleFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRenvilleFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNRenvilleMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNRenvilleMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRenvilleMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNRiceFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNRiceFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRiceFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNRiceMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNRiceMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRiceMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNRockFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNRockFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRockFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNRockMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNRockMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRockMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNRoseauFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNRoseauFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRoseauFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNRoseauMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNRoseauMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNRoseauMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNScottFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNScottFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNScottFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNScottMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNScottMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNScottMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNSherburneFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNSherburneFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNSherburneFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNSherburneMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNSherburneMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNSherburneMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNSibleyFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNSibleyFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNSibleyFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNSibleyMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNSibleyMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNSibleyMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNStLouisCentralFeet method, of class
   * Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNStLouisCentralFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNStLouisCentralFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNStLouisCentralMeters method, of class
   * Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNStLouisCentralMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNStLouisCentralMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNStLouisNorthFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNStLouisNorthFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNStLouisNorthFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNStLouisNorthMeters method, of class
   * Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNStLouisNorthMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNStLouisNorthMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNStLouisSouthFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNStLouisSouthFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNStLouisSouthFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNStLouisSouthMeters method, of class
   * Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNStLouisSouthMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNStLouisSouthMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNStearnsFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNStearnsFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNStearnsFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNStearnsMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNStearnsMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNStearnsMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNSteeleFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNSteeleFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNSteeleFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNSteeleMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNSteeleMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNSteeleMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNStevensFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNStevensFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNStevensFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNStevensMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNStevensMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNStevensMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNSwiftFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNSwiftFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNSwiftFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNSwiftMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNSwiftMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNSwiftMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNToddFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNToddFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNToddFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNToddMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNToddMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNToddMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNTraverseFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNTraverseFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNTraverseFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNTraverseMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNTraverseMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNTraverseMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNWabashaFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNWabashaFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWabashaFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNWabashaMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNWabashaMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWabashaMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNWadenaFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNWadenaFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWadenaFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNWadenaMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNWadenaMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWadenaMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNWasecaFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNWasecaFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWasecaFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNWasecaMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNWasecaMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWasecaMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNWashingtonFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNWashingtonFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWashingtonFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNWashingtonMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNWashingtonMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWashingtonMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNWatonwanFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNWatonwanFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWatonwanFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNWatonwanMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNWatonwanMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWatonwanMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNWilkinFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNWilkinFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWilkinFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNWilkinMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNWilkinMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWilkinMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNWinonaFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNWinonaFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWinonaFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNWinonaMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNWinonaMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWinonaMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNWrightFeet method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNWrightFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWrightFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNWrightMeters method, of class Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNWrightMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNWrightMeters());
  }

  /**
   * Test of getNAD1983HARNAdjMNYellowMedicineFeet method, of class
   * Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNYellowMedicineFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNYellowMedicineFeet());
  }

  /**
   * Test of getNAD1983HARNAdjMNYellowMedicineMeters method, of class
   * Minnesota.
   */
  @Test
  public void testNAD1983HARNAdjMNYellowMedicineMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjMNYellowMedicineMeters());
  }

}
