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
public class WisconsinTest {

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
  private static final Wisconsin PROJ = Projections.getProjected().getWisconsin();

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
   * Test of getNAD1983HARNAdjWIAdamsMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIAdamsMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIAdamsMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIAshlandFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIAshlandFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIAshlandFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIAshlandMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIAshlandMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIAshlandMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIBarronFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIBarronFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIBarronFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIBarronMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIBarronMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIBarronMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIBayfieldFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIBayfieldFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIBayfieldFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIBayfieldMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIBayfieldMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIBayfieldMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIBrownFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIBrownFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIBrownFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIBrownMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIBrownMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIBrownMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIBuffaloFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIBuffaloFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIBuffaloFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIBuffaloMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIBuffaloMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIBuffaloMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIBurnettFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIBurnettFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIBurnettFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIBurnettMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIBurnettMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIBurnettMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWICalumetFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWICalumetFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWICalumetFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWICalumetMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWICalumetMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWICalumetMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIChippewaFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIChippewaFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIChippewaFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIChippewaMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIChippewaMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIChippewaMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIClarkFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIClarkFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIClarkFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIClarkMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIClarkMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIClarkMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIColumbiaFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIColumbiaFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIColumbiaFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIColumbiaMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIColumbiaMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIColumbiaMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWICrawfordFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWICrawfordFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWICrawfordFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWICrawfordMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWICrawfordMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWICrawfordMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIDaneFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIDaneFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIDaneFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIDaneMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIDaneMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIDaneMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIDodgeFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIDodgeFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIDodgeFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIDodgeMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIDodgeMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIDodgeMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIDoorFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIDoorFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIDoorFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIDoorMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIDoorMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIDoorMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIDouglasFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIDouglasFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIDouglasFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIDouglasMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIDouglasMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIDouglasMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIDunnFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIDunnFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIDunnFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIDunnMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIDunnMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIDunnMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIEauClaireFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIEauClaireFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIEauClaireFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIEauClaireMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIEauClaireMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIEauClaireMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIFlorenceFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIFlorenceFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIFlorenceFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIFlorenceMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIFlorenceMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIFlorenceMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIFondduLacFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIFondduLacFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIFondduLacFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIFondduLacMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIFondduLacMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIFondduLacMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIForestFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIForestFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIForestFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIForestMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIForestMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIForestMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIGrantFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIGrantFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIGrantFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIGrantMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIGrantMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIGrantMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIGreenFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIGreenFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIGreenFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIGreenLakeFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIGreenLakeFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIGreenLakeFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIGreenLakeMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIGreenLakeMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIGreenLakeMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIGreenMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIGreenMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIGreenMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIIowaFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIIowaFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIIowaFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIIowaMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIIowaMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIIowaMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIIronFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIIronFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIIronFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIIronMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIIronMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIIronMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIJacksonFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIJacksonFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIJacksonFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIJacksonMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIJacksonMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIJacksonMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIJeffersonFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIJeffersonFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIJeffersonFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIJeffersonMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIJeffersonMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIJeffersonMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIJuneauFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIJuneauFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIJuneauFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIJuneauMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIJuneauMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIJuneauMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIKenoshaFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIKenoshaFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIKenoshaFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIKenoshaMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIKenoshaMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIKenoshaMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIKewauneeFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIKewauneeFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIKewauneeFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIKewauneeMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIKewauneeMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIKewauneeMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWILaCrosseFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWILaCrosseFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWILaCrosseFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWILaCrosseMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWILaCrosseMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWILaCrosseMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWILafayetteFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWILafayetteFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWILafayetteFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWILafayetteMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWILafayetteMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWILafayetteMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWILangladeFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWILangladeFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWILangladeFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWILangladeMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWILangladeMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWILangladeMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWILincolnFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWILincolnFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWILincolnFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWILincolnMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWILincolnMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWILincolnMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIManitowocFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIManitowocFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIManitowocFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIManitowocMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIManitowocMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIManitowocMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIMarathonFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIMarathonFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIMarathonFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIMarathonMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIMarathonMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIMarathonMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIMarinetteFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIMarinetteFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIMarinetteFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIMarinetteMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIMarinetteMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIMarinetteMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIMarquetteFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIMarquetteFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIMarquetteFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIMarquetteMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIMarquetteMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIMarquetteMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIMenomineeFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIMenomineeFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIMenomineeFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIMenomineeMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIMenomineeMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIMenomineeMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIMilwaukeeFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIMilwaukeeFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIMilwaukeeFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIMilwaukeeMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIMilwaukeeMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIMilwaukeeMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIMonroeFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIMonroeFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIMonroeFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIMonroeMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIMonroeMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIMonroeMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIOcontoFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIOcontoFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIOcontoFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIOcontoMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIOcontoMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIOcontoMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIOneidaFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIOneidaFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIOneidaFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIOneidaMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIOneidaMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIOneidaMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIOutagamieFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIOutagamieFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIOutagamieFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIOutagamieMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIOutagamieMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIOutagamieMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIOzaukeeFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIOzaukeeFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIOzaukeeFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIOzaukeeMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIOzaukeeMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIOzaukeeMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIPepinFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIPepinFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIPepinFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIPepinMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIPepinMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIPepinMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIPierceFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIPierceFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIPierceFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIPierceMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIPierceMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIPierceMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIPolkFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIPolkFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIPolkFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIPolkMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIPolkMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIPolkMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIPortageFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIPortageFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIPortageFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIPortageMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIPortageMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIPortageMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIPriceFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIPriceFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIPriceFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIPriceMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIPriceMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIPriceMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIRacineFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIRacineFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIRacineFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIRacineMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIRacineMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIRacineMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIRichlandFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIRichlandFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIRichlandFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIRichlandMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIRichlandMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIRichlandMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIRockFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIRockFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIRockFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIRockMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIRockMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIRockMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIRuskFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIRuskFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIRuskFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIRuskMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIRuskMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIRuskMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWISaukFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWISaukFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWISaukFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWISaukMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWISaukMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWISaukMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWISawyerFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWISawyerFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWISawyerFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWISawyerMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWISawyerMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWISawyerMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIShawanoFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIShawanoFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIShawanoFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIShawanoMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIShawanoMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIShawanoMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWISheboyganFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWISheboyganFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWISheboyganFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWISheboyganMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWISheboyganMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWISheboyganMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIStCroixFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIStCroixFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIStCroixFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIStCroixMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIStCroixMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIStCroixMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWITaylorFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWITaylorFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWITaylorFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWITaylorMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWITaylorMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWITaylorMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWITrempealeauFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWITrempealeauFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWITrempealeauFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWITrempealeauMeters method, of class
   * Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWITrempealeauMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWITrempealeauMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIVernonFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIVernonFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIVernonFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIVernonMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIVernonMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIVernonMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIVilasFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIVilasFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIVilasFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIVilasMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIVilasMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIVilasMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIWalworthFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIWalworthFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWalworthFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIWalworthMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIWalworthMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWalworthMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIWashburnFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIWashburnFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWashburnFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIWashburnMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIWashburnMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWashburnMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIWashingtonFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIWashingtonFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWashingtonFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIWashingtonMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIWashingtonMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWashingtonMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIWaukeshaFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIWaukeshaFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWaukeshaFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIWaukeshaMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIWaukeshaMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWaukeshaMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIWaupacaFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIWaupacaFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWaupacaFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIWaupacaMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIWaupacaMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWaupacaMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIWausharaFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIWausharaFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWausharaFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIWausharaMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIWausharaMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWausharaMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIWinnebagoFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIWinnebagoFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWinnebagoFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIWinnebagoMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIWinnebagoMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWinnebagoMeters());
  }

  /**
   * Test of getNAD1983HARNAdjWIWoodFeet method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIWoodFeet() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWoodFeet());
  }

  /**
   * Test of getNAD1983HARNAdjWIWoodMeters method, of class Wisconsin.
   */
  @Test
  public void testNAD1983HARNAdjWIWoodMeters() {
    testToWGS84AndBack(PROJ.getNAD1983HARNAdjWIWoodMeters());
  }

}
