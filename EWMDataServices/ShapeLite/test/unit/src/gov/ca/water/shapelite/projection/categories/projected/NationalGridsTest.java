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
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class NationalGridsTest {

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
  private static final NationalGrids GRID = Projections.getProjected().getNationalGrids();

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
   * Test of getAbidjan1987TM5NW method, of class NationalGrids.
   */
  @Test
  public void testAbidjan1987TM5NW() {
    testToWGS84AndBack(GRID.getAbidjan1987TM5NW());
  }

  /**
   * Test of getAccraGhanaGrid method, of class NationalGrids.
   */
  @Test
  public void testAccraGhanaGrid() {
    testToWGS84AndBack(GRID.getAccraGhanaGrid());
  }

  /**
   * Test of getAccraTM1NW method, of class NationalGrids.
   */
  @Test
  public void testAccraTM1NW() {
    testToWGS84AndBack(GRID.getAccraTM1NW());
  }

  /**
   * Test of getAinelAbdAramcoLambert method, of class NationalGrids.
   */
  @Test
  public void testAinelAbdAramcoLambert() {
    testToWGS84AndBack(GRID.getAinelAbdAramcoLambert());
  }

  /**
   * Test of getAmericanSamoa1962SamoaLambert method, of class NationalGrids.
   */
  @Test
  public void testAmericanSamoa1962SamoaLambert() {
    testToWGS84AndBack(GRID.getAmericanSamoa1962SamoaLambert());
  }

  /**
   * Test of getAnguilla1957BritishWestIndiesGrid method, of class
   * NationalGrids.
   */
  @Test
  public void testAnguilla1957BritishWestIndiesGrid() {
    testToWGS84AndBack(GRID.getAnguilla1957BritishWestIndiesGrid());
  }

  /**
   * Test of getAntigua1943BritishWestIndiesGrid method, of class NationalGrids.
   */
  @Test
  public void testAntigua1943BritishWestIndiesGrid() {
    testToWGS84AndBack(GRID.getAntigua1943BritishWestIndiesGrid());
  }

  /**
   * Test of getArgentinaZone1 method, of class NationalGrids.
   */
  @Test
  public void testArgentinaZone1() {
    testToWGS84AndBack(GRID.getArgentinaZone1());
  }

  /**
   * Test of getArgentinaZone2 method, of class NationalGrids.
   */
  @Test
  public void testArgentinaZone2() {
    testToWGS84AndBack(GRID.getArgentinaZone2());
  }

  /**
   * Test of getArgentinaZone3 method, of class NationalGrids.
   */
  @Test
  public void testArgentinaZone3() {
    testToWGS84AndBack(GRID.getArgentinaZone3());
  }

  /**
   * Test of getArgentinaZone4 method, of class NationalGrids.
   */
  @Test
  public void testArgentinaZone4() {
    testToWGS84AndBack(GRID.getArgentinaZone4());
  }

  /**
   * Test of getArgentinaZone5 method, of class NationalGrids.
   */
  @Test
  public void testArgentinaZone5() {
    testToWGS84AndBack(GRID.getArgentinaZone5());
  }

  /**
   * Test of getArgentinaZone6 method, of class NationalGrids.
   */
  @Test
  public void testArgentinaZone6() {
    testToWGS84AndBack(GRID.getArgentinaZone6());
  }

  /**
   * Test of getArgentinaZone7 method, of class NationalGrids.
   */
  @Test
  public void testArgentinaZone7() {
    testToWGS84AndBack(GRID.getArgentinaZone7());
  }

  /**
   * Test of getAustriaFerroCentralZone method, of class NationalGrids.
   */
  @Test
  public void testAustriaFerroCentralZone() {
    testToWGS84AndBack(GRID.getAustriaFerroCentralZone());
  }

  /**
   * Test of getAustriaFerroEastZone method, of class NationalGrids.
   */
  @Test
  public void testAustriaFerroEastZone() {
    testToWGS84AndBack(GRID.getAustriaFerroEastZone());
  }

  /**
   * Test of getAustriaFerroWestZone method, of class NationalGrids.
   */
  @Test
  public void testAustriaFerroWestZone() {
    testToWGS84AndBack(GRID.getAustriaFerroWestZone());
  }

  /**
   * Test of getBahrainStateGrid method, of class NationalGrids.
   */
  @Test
  public void testBahrainStateGrid() {
    testToWGS84AndBack(GRID.getBahrainStateGrid());
  }

  /**
   * Test of getBarbados1938BarbadosGrid method, of class NationalGrids.
   */
  @Test
  public void testBarbados1938BarbadosGrid() {
    testToWGS84AndBack(GRID.getBarbados1938BarbadosGrid());
  }

  /**
   * Test of getBarbados1938BritishWestIndiesGrid method, of class
   * NationalGrids.
   */
  @Test
  public void testBarbados1938BritishWestIndiesGrid() {
    testToWGS84AndBack(GRID.getBarbados1938BritishWestIndiesGrid());
  }

  /**
   * Test of getBataviaNEIEZ method, of class NationalGrids.
   */
  @Test
  public void testBataviaNEIEZ() {
    testToWGS84AndBack(GRID.getBataviaNEIEZ());
  }

  /**
   * Test of getBataviaTM109SE method, of class NationalGrids.
   */
  @Test
  public void testBataviaTM109SE() {
    testToWGS84AndBack(GRID.getBataviaTM109SE());
  }

  /**
   * Test of getBelgeLambert1950 method, of class NationalGrids.
   */
  @Test
  public void testBelgeLambert1950() {
    testToWGS84AndBack(GRID.getBelgeLambert1950());
  }

  /**
   * Test of getBelgeLambert1972 method, of class NationalGrids.
   */
  @Test
  public void testBelgeLambert1972() {
    testToWGS84AndBack(GRID.getBelgeLambert1972());
  }

  /**
   * Test of getBermuda2000NationalGrid method, of class NationalGrids.
   */
  @Test
  public void testBermuda2000NationalGrid() {
    testToWGS84AndBack(GRID.getBermuda2000NationalGrid());
  }

  /**
   * Test of getBern1898BernLV03C method, of class NationalGrids.
   */
  @Test
  public void testBern1898BernLV03C() {
    testToWGS84AndBack(GRID.getBern1898BernLV03C());
  }

  /**
   * Test of getBritishNationalGridOSGB36 method, of class NationalGrids.
   */
  @Test
  public void testBritishNationalGridOSGB36() {
    testToWGS84AndBack(GRID.getBritishNationalGridOSGB36());
  }

  /**
   * Test of getCH1903LV03 method, of class NationalGrids.
   */
  @Test
  public void testCH1903LV03() {
    testToWGS84AndBack(GRID.getCH1903LV03());
  }

  /**
   * Test of getCH1903LV95 method, of class NationalGrids.
   */
  @Test
  public void testCH1903LV95() {
    testToWGS84AndBack(GRID.getCH1903LV95());
  }

  /**
   * Test of getCamacupaTM1130SE method, of class NationalGrids.
   */
  @Test
  public void testCamacupaTM1130SE() {
    testToWGS84AndBack(GRID.getCamacupaTM1130SE());
  }

  /**
   * Test of getCamacupaTM12SE method, of class NationalGrids.
   */
  @Test
  public void testCamacupaTM12SE() {
    testToWGS84AndBack(GRID.getCamacupaTM12SE());
  }

  /**
   * Test of getCarthageTM11NE method, of class NationalGrids.
   */
  @Test
  public void testCarthageTM11NE() {
    testToWGS84AndBack(GRID.getCarthageTM11NE());
  }

  /**
   * Test of getCentreFrance method, of class NationalGrids.
   */
  @Test
  public void testCentreFrance() {
    testToWGS84AndBack(GRID.getCentreFrance());
  }

  /**
   * Test of getChosMalal1914Argentina2 method, of class NationalGrids.
   */
  @Test
  public void testChosMalal1914Argentina2() {
    testToWGS84AndBack(GRID.getChosMalal1914Argentina2());
  }

  /**
   * Test of getColombiaBogotaZone method, of class NationalGrids.
   */
  @Test
  public void testColombiaBogotaZone() {
    testToWGS84AndBack(GRID.getColombiaBogotaZone());
  }

  /**
   * Test of getColombiaECentralZone method, of class NationalGrids.
   */
  @Test
  public void testColombiaECentralZone() {
    testToWGS84AndBack(GRID.getColombiaECentralZone());
  }

  /**
   * Test of getColombiaEastZone method, of class NationalGrids.
   */
  @Test
  public void testColombiaEastZone() {
    testToWGS84AndBack(GRID.getColombiaEastZone());
  }

  /**
   * Test of getColombiaWestZone method, of class NationalGrids.
   */
  @Test
  public void testColombiaWestZone() {
    testToWGS84AndBack(GRID.getColombiaWestZone());
  }

  /**
   * Test of getCorse method, of class NationalGrids.
   */
  @Test
  public void testCorse() {
    testToWGS84AndBack(GRID.getCorse());
  }

  /**
   * Test of getDHDN3DegreeGaussZone1 method, of class NationalGrids.
   */
  @Test
  public void testDHDN3DegreeGaussZone1() {
    testToWGS84AndBack(GRID.getDHDN3DegreeGaussZone1());
  }

  /**
   * Test of getDHDN3DegreeGaussZone2 method, of class NationalGrids.
   */
  @Test
  public void testDHDN3DegreeGaussZone2() {
    testToWGS84AndBack(GRID.getDHDN3DegreeGaussZone2());
  }

  /**
   * Test of getDHDN3DegreeGaussZone3 method, of class NationalGrids.
   */
  @Test
  public void testDHDN3DegreeGaussZone3() {
    testToWGS84AndBack(GRID.getDHDN3DegreeGaussZone3());
  }

  /**
   * Test of getDHDN3DegreeGaussZone4 method, of class NationalGrids.
   */
  @Test
  public void testDHDN3DegreeGaussZone4() {
    testToWGS84AndBack(GRID.getDHDN3DegreeGaussZone4());
  }

  /**
   * Test of getDHDN3DegreeGaussZone5 method, of class NationalGrids.
   */
  @Test
  public void testDHDN3DegreeGaussZone5() {
    testToWGS84AndBack(GRID.getDHDN3DegreeGaussZone5());
  }

  /**
   * Test of getDatum73HayfordGaussIGeoE method, of class NationalGrids.
   */
  @Test
  public void testDatum73HayfordGaussIGeoE() {
    testToWGS84AndBack(GRID.getDatum73HayfordGaussIGeoE());
  }

  /**
   * Test of getDatum73HayfordGaussIPCC method, of class NationalGrids.
   */
  @Test
  public void testDatum73HayfordGaussIPCC() {
    testToWGS84AndBack(GRID.getDatum73HayfordGaussIPCC());
  }

  /**
   * Test of getDeirezZorLevantStereographic method, of class NationalGrids.
   */
  @Test
  public void testDeirezZorLevantStereographic() {
    testToWGS84AndBack(GRID.getDeirezZorLevantStereographic());
  }

  /**
   * Test of getDeirezZorLevantZone method, of class NationalGrids.
   */
  @Test
  public void testDeirezZorLevantZone() {
    testToWGS84AndBack(GRID.getDeirezZorLevantZone());
  }

  /**
   * Test of getDeirezZorSyriaLambert method, of class NationalGrids.
   */
  @Test
  public void testDeirezZorSyriaLambert() {
    testToWGS84AndBack(GRID.getDeirezZorSyriaLambert());
  }

  /**
   * Test of getDominica1945BritishWestIndiesGrid method, of class
   * NationalGrids.
   */
  @Test
  public void testDominica1945BritishWestIndiesGrid() {
    testToWGS84AndBack(GRID.getDominica1945BritishWestIndiesGrid());
  }

  /**
   * Test of getDouala1948AOFWest method, of class NationalGrids.
   */
  @Test
  public void testDouala1948AOFWest() {
    testToWGS84AndBack(GRID.getDouala1948AOFWest());
  }



  /**
   * Test of getED1950FranceEuroLambert method, of class NationalGrids.
   */
  @Test
  public void testED1950FranceEuroLambert() {
    testToWGS84AndBack(GRID.getED1950FranceEuroLambert());
  }

  /**
   * Test of getED1950TM0N method, of class NationalGrids.
   */
  @Test
  public void testED1950TM0N() {
    testToWGS84AndBack(GRID.getED1950TM0N());
  }

  /**
   * Test of getED1950TM27 method, of class NationalGrids.
   */
  @Test
  public void testED1950TM27() {
    testToWGS84AndBack(GRID.getED1950TM27());
  }

  /**
   * Test of getED1950TM30 method, of class NationalGrids.
   */
  @Test
  public void testED1950TM30() {
    testToWGS84AndBack(GRID.getED1950TM30());
  }

  /**
   * Test of getED1950TM33 method, of class NationalGrids.
   */
  @Test
  public void testED1950TM33() {
    testToWGS84AndBack(GRID.getED1950TM33());
  }

  /**
   * Test of getED1950TM36 method, of class NationalGrids.
   */
  @Test
  public void testED1950TM36() {
    testToWGS84AndBack(GRID.getED1950TM36());
  }

  /**
   * Test of getED1950TM39 method, of class NationalGrids.
   */
  @Test
  public void testED1950TM39() {
    testToWGS84AndBack(GRID.getED1950TM39());
  }

  /**
   * Test of getED1950TM42 method, of class NationalGrids.
   */
  @Test
  public void testED1950TM42() {
    testToWGS84AndBack(GRID.getED1950TM42());
  }

  /**
   * Test of getED1950TM45 method, of class NationalGrids.
   */
  @Test
  public void testED1950TM45() {
    testToWGS84AndBack(GRID.getED1950TM45());
  }

  /**
   * Test of getED1950TM5NE method, of class NationalGrids.
   */
  @Test
  public void testED1950TM5NE() {
    testToWGS84AndBack(GRID.getED1950TM5NE());
  }

  /**
   * Test of getED1950Turkey10 method, of class NationalGrids.
   */
  @Test
  public void testED1950Turkey10() {
    testToWGS84AndBack(GRID.getED1950Turkey10());
  }

  /**
   * Test of getED1950Turkey11 method, of class NationalGrids.
   */
  @Test
  public void testED1950Turkey11() {
    testToWGS84AndBack(GRID.getED1950Turkey11());
  }

  /**
   * Test of getED1950Turkey12 method, of class NationalGrids.
   */
  @Test
  public void testED1950Turkey12() {
    testToWGS84AndBack(GRID.getED1950Turkey12());
  }

  /**
   * Test of getED1950Turkey13 method, of class NationalGrids.
   */
  @Test
  public void testED1950Turkey13() {
    testToWGS84AndBack(GRID.getED1950Turkey13());
  }

  /**
   * Test of getED1950Turkey14 method, of class NationalGrids.
   */
  @Test
  public void testED1950Turkey14() {
    testToWGS84AndBack(GRID.getED1950Turkey14());
  }

  /**
   * Test of getED1950Turkey15 method, of class NationalGrids.
   */
  @Test
  public void testED1950Turkey15() {
    testToWGS84AndBack(GRID.getED1950Turkey15());
  }

  /**
   * Test of getED1950Turkey9 method, of class NationalGrids.
   */
  @Test
  public void testED1950Turkey9() {
    testToWGS84AndBack(GRID.getED1950Turkey9());
  }

  /**
   * Test of getELD1979Libya10 method, of class NationalGrids.
   */
  @Test
  public void testELD1979Libya10() {
    testToWGS84AndBack(GRID.getELD1979Libya10());
  }

  /**
   * Test of getELD1979Libya11 method, of class NationalGrids.
   */
  @Test
  public void testELD1979Libya11() {
    testToWGS84AndBack(GRID.getELD1979Libya11());
  }

  /**
   * Test of getELD1979Libya12 method, of class NationalGrids.
   */
  @Test
  public void testELD1979Libya12() {
    testToWGS84AndBack(GRID.getELD1979Libya12());
  }

  /**
   * Test of getELD1979Libya13 method, of class NationalGrids.
   */
  @Test
  public void testELD1979Libya13() {
    testToWGS84AndBack(GRID.getELD1979Libya13());
  }

  /**
   * Test of getELD1979Libya5 method, of class NationalGrids.
   */
  @Test
  public void testELD1979Libya5() {
    testToWGS84AndBack(GRID.getELD1979Libya5());
  }

  /**
   * Test of getELD1979Libya6 method, of class NationalGrids.
   */
  @Test
  public void testELD1979Libya6() {
    testToWGS84AndBack(GRID.getELD1979Libya6());
  }

  /**
   * Test of getELD1979Libya7 method, of class NationalGrids.
   */
  @Test
  public void testELD1979Libya7() {
    testToWGS84AndBack(GRID.getELD1979Libya7());
  }

  /**
   * Test of getELD1979Libya8 method, of class NationalGrids.
   */
  @Test
  public void testELD1979Libya8() {
    testToWGS84AndBack(GRID.getELD1979Libya8());
  }

  /**
   * Test of getELD1979Libya9 method, of class NationalGrids.
   */
  @Test
  public void testELD1979Libya9() {
    testToWGS84AndBack(GRID.getELD1979Libya9());
  }

  /**
   * Test of getELD1979TM12NE method, of class NationalGrids.
   */
  @Test
  public void testELD1979TM12NE() {
    testToWGS84AndBack(GRID.getELD1979TM12NE());
  }

  /**
   * Test of getETRF1989TMBaltic1993 method, of class NationalGrids.
   */
  @Test
  public void testETRF1989TMBaltic1993() {
    testToWGS84AndBack(GRID.getETRF1989TMBaltic1993());
  }

  /**
   * Test of getETRS1989Kp2000Bornholm method, of class NationalGrids.
   */
  @Test
  public void testETRS1989Kp2000Bornholm() {
    testToWGS84AndBack(GRID.getETRS1989Kp2000Bornholm());
  }

  /**
   * Test of getETRS1989Kp2000Jutland method, of class NationalGrids.
   */
  @Test
  public void testETRS1989Kp2000Jutland() {
    testToWGS84AndBack(GRID.getETRS1989Kp2000Jutland());
  }

  /**
   * Test of getETRS1989Kp2000Zealand method, of class NationalGrids.
   */
  @Test
  public void testETRS1989Kp2000Zealand() {
    testToWGS84AndBack(GRID.getETRS1989Kp2000Zealand());
  }

  /**
   * Test of getETRS1989PolandCS2000Zone5 method, of class NationalGrids.
   */
  @Test
  public void testETRS1989PolandCS2000Zone5() {
    testToWGS84AndBack(GRID.getETRS1989PolandCS2000Zone5());
  }

  /**
   * Test of getETRS1989PolandCS2000Zone6 method, of class NationalGrids.
   */
  @Test
  public void testETRS1989PolandCS2000Zone6() {
    testToWGS84AndBack(GRID.getETRS1989PolandCS2000Zone6());
  }

  /**
   * Test of getETRS1989PolandCS2000Zone7 method, of class NationalGrids.
   */
  @Test
  public void testETRS1989PolandCS2000Zone7() {
    testToWGS84AndBack(GRID.getETRS1989PolandCS2000Zone7());
  }

  /**
   * Test of getETRS1989PolandCS2000Zone8 method, of class NationalGrids.
   */
  @Test
  public void testETRS1989PolandCS2000Zone8() {
    testToWGS84AndBack(GRID.getETRS1989PolandCS2000Zone8());
  }

  /**
   * Test of getETRS1989PolandCS92 method, of class NationalGrids.
   */
  @Test
  public void testETRS1989PolandCS92() {
    testToWGS84AndBack(GRID.getETRS1989PolandCS92());
  }

  /**
   * Test of getETRS1989TM30NE method, of class NationalGrids.
   */
  @Test
  public void testETRS1989TM30NE() {
    testToWGS84AndBack(GRID.getETRS1989TM30NE());
  }

  /**
   * Test of getETRS1989TMBaltic1993 method, of class NationalGrids.
   */
  @Test
  public void testETRS1989TMBaltic1993() {
    testToWGS84AndBack(GRID.getETRS1989TMBaltic1993());
  }

  /**
   * Test of getETRS1989UWPP1992 method, of class NationalGrids.
   */
  @Test
  public void testETRS1989UWPP1992() {
    testToWGS84AndBack(GRID.getETRS1989UWPP1992());
  }

  /**
   * Test of getETRS1989UWPP2000PAS5 method, of class NationalGrids.
   */
  @Test
  public void testETRS1989UWPP2000PAS5() {
    testToWGS84AndBack(GRID.getETRS1989UWPP2000PAS5());
  }

  /**
   * Test of getETRS1989UWPP2000PAS6 method, of class NationalGrids.
   */
  @Test
  public void testETRS1989UWPP2000PAS6() {
    testToWGS84AndBack(GRID.getETRS1989UWPP2000PAS6());
  }

  /**
   * Test of getETRS1989UWPP2000PAS7 method, of class NationalGrids.
   */
  @Test
  public void testETRS1989UWPP2000PAS7() {
    testToWGS84AndBack(GRID.getETRS1989UWPP2000PAS7());
  }

  /**
   * Test of getETRS1989UWPP2000PAS8 method, of class NationalGrids.
   */
  @Test
  public void testETRS1989UWPP2000PAS8() {
    testToWGS84AndBack(GRID.getETRS1989UWPP2000PAS8());
  }

  /**
   * Test of getEUREFFINTM35FIN method, of class NationalGrids.
   */
  @Test
  public void testEUREFFINTM35FIN() {
    testToWGS84AndBack(GRID.getEUREFFINTM35FIN());
  }

  /**
   * Test of getEgyptBlueBelt method, of class NationalGrids.
   */
  @Test
  public void testEgyptBlueBelt() {
    testToWGS84AndBack(GRID.getEgyptBlueBelt());
  }

  /**
   * Test of getEgyptExtendedPurpleBelt method, of class NationalGrids.
   */
  @Test
  public void testEgyptExtendedPurpleBelt() {
    testToWGS84AndBack(GRID.getEgyptExtendedPurpleBelt());
  }

  /**
   * Test of getEgyptPurpleBelt method, of class NationalGrids.
   */
  @Test
  public void testEgyptPurpleBelt() {
    testToWGS84AndBack(GRID.getEgyptPurpleBelt());
  }

  /**
   * Test of getEgyptRedBelt method, of class NationalGrids.
   */
  @Test
  public void testEgyptRedBelt() {
    testToWGS84AndBack(GRID.getEgyptRedBelt());
  }

  /**
   * Test of getEstonia1997EstoniaNationalGrid method, of class NationalGrids.
   */
  @Test
  public void testEstonia1997EstoniaNationalGrid() {
    testToWGS84AndBack(GRID.getEstonia1997EstoniaNationalGrid());
  }

  /**
   * Test of getEstonianCoordinateSystemof1992 method, of class NationalGrids.
   */
  @Test
  public void testEstonianCoordinateSystemof1992() {
    testToWGS84AndBack(GRID.getEstonianCoordinateSystemof1992());
  }


  /**
   * Test of getFD1958Iraq method, of class NationalGrids.
   */
  @Test
  public void testFD1958Iraq() {
    testToWGS84AndBack(GRID.getFD1958Iraq());
  }

  /**
   * Test of getFinlandZone1 method, of class NationalGrids.
   */
  @Test
  public void testFinlandZone1() {
    testToWGS84AndBack(GRID.getFinlandZone1());
  }

  /**
   * Test of getFinlandZone2 method, of class NationalGrids.
   */
  @Test
  public void testFinlandZone2() {
    testToWGS84AndBack(GRID.getFinlandZone2());
  }

  /**
   * Test of getFinlandZone3 method, of class NationalGrids.
   */
  @Test
  public void testFinlandZone3() {
    testToWGS84AndBack(GRID.getFinlandZone3());
  }

  /**
   * Test of getFinlandZone4 method, of class NationalGrids.
   */
  @Test
  public void testFinlandZone4() {
    testToWGS84AndBack(GRID.getFinlandZone4());
  }

  /**
   * Test of getFranceI method, of class NationalGrids.
   */
  @Test
  public void testFranceI() {
    testToWGS84AndBack(GRID.getFranceI());
  }

  /**
   * Test of getFranceII method, of class NationalGrids.
   */
  @Test
  public void testFranceII() {
    testToWGS84AndBack(GRID.getFranceII());
  }

  /**
   * Test of getFranceIII method, of class NationalGrids.
   */
  @Test
  public void testFranceIII() {
    testToWGS84AndBack(GRID.getFranceIII());
  }

  /**
   * Test of getFranceIV method, of class NationalGrids.
   */
  @Test
  public void testFranceIV() {
    testToWGS84AndBack(GRID.getFranceIV());
  }

  /**
   * Test of getGermanyZone1 method, of class NationalGrids.
   */
  @Test
  public void testGermanyZone1() {
    testToWGS84AndBack(GRID.getGermanyZone1());
  }

  /**
   * Test of getGermanyZone2 method, of class NationalGrids.
   */
  @Test
  public void testGermanyZone2() {
    testToWGS84AndBack(GRID.getGermanyZone2());
  }

  /**
   * Test of getGermanyZone3 method, of class NationalGrids.
   */
  @Test
  public void testGermanyZone3() {
    testToWGS84AndBack(GRID.getGermanyZone3());
  }

  /**
   * Test of getGermanyZone4 method, of class NationalGrids.
   */
  @Test
  public void testGermanyZone4() {
    testToWGS84AndBack(GRID.getGermanyZone4());
  }

  /**
   * Test of getGermanyZone5 method, of class NationalGrids.
   */
  @Test
  public void testGermanyZone5() {
    testToWGS84AndBack(GRID.getGermanyZone5());
  }

  /**
   * Test of getGhanaMetreGrid method, of class NationalGrids.
   */
  @Test
  public void testGhanaMetreGrid() {
    testToWGS84AndBack(GRID.getGhanaMetreGrid());
  }

  /**
   * Test of getGreekGrid method, of class NationalGrids.
   */
  @Test
  public void testGreekGrid() {
    testToWGS84AndBack(GRID.getGreekGrid());
  }

  /**
   * Test of getGrenada1953BritishWestIndiesGrid method, of class NationalGrids.
   */
  @Test
  public void testGrenada1953BritishWestIndiesGrid() {
    testToWGS84AndBack(GRID.getGrenada1953BritishWestIndiesGrid());
  }

  /**
   * Test of getGuernseyGrid method, of class NationalGrids.
   */
  @Test
  public void testGuernseyGrid() {
    testToWGS84AndBack(GRID.getGuernseyGrid());
  }

  /**
   * Test of getGunungSegaraNEIEZ method, of class NationalGrids.
   */
  @Test
  public void testGunungSegaraNEIEZ() {
    testToWGS84AndBack(GRID.getGunungSegaraNEIEZ());
  }

  /**
   * Test of getHD1972EgysegesOrszagosVetuleti method, of class NationalGrids.
   */
  @Test
  public void testHD1972EgysegesOrszagosVetuleti() {
    testToWGS84AndBack(GRID.getHD1972EgysegesOrszagosVetuleti());
  }

  /**
   * Test of getHanoi1972GK106NE method, of class NationalGrids.
   */
  @Test
  public void testHanoi1972GK106NE() {
    testToWGS84AndBack(GRID.getHanoi1972GK106NE());
  }

  /**
   * Test of getHelle1954JanMayenGrid method, of class NationalGrids.
   */
  @Test
  public void testHelle1954JanMayenGrid() {
    testToWGS84AndBack(GRID.getHelle1954JanMayenGrid());
  }

  /**
   * Test of getHitoXVIII1963Argentina2 method, of class NationalGrids.
   */
  @Test
  public void testHitoXVIII1963Argentina2() {
    testToWGS84AndBack(GRID.getHitoXVIII1963Argentina2());
  }

  /**
   * Test of getHongKong1980Grid method, of class NationalGrids.
   */
  @Test
  public void testHongKong1980Grid() {
    testToWGS84AndBack(GRID.getHongKong1980Grid());
  }

  /**
   * Test of getIRENET95IrishTranverseMercator method, of class NationalGrids.
   */
  @Test
  public void testIRENET95IrishTranverseMercator() {
    testToWGS84AndBack(GRID.getIRENET95IrishTranverseMercator());
  }

  /**
   * Test of getISN1993Lambert1993 method, of class NationalGrids.
   */
  @Test
  public void testISN1993Lambert1993() {
    testToWGS84AndBack(GRID.getISN1993Lambert1993());
  }

  /**
   * Test of getIndian1960TM106NE method, of class NationalGrids.
   */
  @Test
  public void testIndian1960TM106NE() {
    testToWGS84AndBack(GRID.getIndian1960TM106NE());
  }

  /**
   * Test of getIrishNationalGrid method, of class NationalGrids.
   */
  @Test
  public void testIrishNationalGrid() {
    testToWGS84AndBack(GRID.getIrishNationalGrid());
  }

  /**
   * Test of getIsraelTMGrid method, of class NationalGrids.
   */
  @Test
  public void testIsraelTMGrid() {
    testToWGS84AndBack(GRID.getIsraelTMGrid());
  }

  /**
   * Test of getJamaica1875OldGrid method, of class NationalGrids.
   */
  @Test
  public void testJamaica1875OldGrid() {
    testToWGS84AndBack(GRID.getJamaica1875OldGrid());
  }

  /**
   * Test of getJamaicaGrid method, of class NationalGrids.
   */
  @Test
  public void testJamaicaGrid() {
    testToWGS84AndBack(GRID.getJamaicaGrid());
  }

  /**
   * Test of getJordanJTM method, of class NationalGrids.
   */
  @Test
  public void testJordanJTM() {
    testToWGS84AndBack(GRID.getJordanJTM());
  }

  /**
   * Test of getKOCLambert method, of class NationalGrids.
   */
  @Test
  public void testKOCLambert() {
    testToWGS84AndBack(GRID.getKOCLambert());
  }

  /**
   * Test of getKUDAMSKTM method, of class NationalGrids.
   */
  @Test
  public void testKUDAMSKTM() {
    testToWGS84AndBack(GRID.getKUDAMSKTM());
  }

  /**
   * Test of getKandawalaCeylonBeltIndianYards1937 method, of class
   * NationalGrids.
   */
  @Test
  public void testKandawalaCeylonBeltIndianYards1937() {
    testToWGS84AndBack(GRID.getKandawalaCeylonBeltIndianYards1937());
  }

  /**
   * Test of getKandawalaCeylonBeltMeters method, of class NationalGrids.
   */
  @Test
  public void testKandawalaCeylonBeltMeters() {
    testToWGS84AndBack(GRID.getKandawalaCeylonBeltMeters());
  }

  /**
   * Test of getKertauRSOMalayaChains method, of class NationalGrids.
   */
  @Test
  public void testKertauRSOMalayaChains() {
    testToWGS84AndBack(GRID.getKertauRSOMalayaChains());
  }

  /**
   * Test of getKertauRSOMalayaMeters method, of class NationalGrids.
   */
  @Test
  public void testKertauRSOMalayaMeters() {
    testToWGS84AndBack(GRID.getKertauRSOMalayaMeters());
  }

  /**
   * Test of getKertauSingaporeGrid method, of class NationalGrids.
   */
  @Test
  public void testKertauSingaporeGrid() {
    testToWGS84AndBack(GRID.getKertauSingaporeGrid());
  }

  /**
   * Test of getKorean1985KoreaCentralBelt method, of class NationalGrids.
   */
  @Test
  public void testKorean1985KoreaCentralBelt() {
    testToWGS84AndBack(GRID.getKorean1985KoreaCentralBelt());
  }

  /**
   * Test of getKorean1985KoreaEastBelt method, of class NationalGrids.
   */
  @Test
  public void testKorean1985KoreaEastBelt() {
    testToWGS84AndBack(GRID.getKorean1985KoreaEastBelt());
  }

  /**
   * Test of getKorean1985KoreaWestBelt method, of class NationalGrids.
   */
  @Test
  public void testKorean1985KoreaWestBelt() {
    testToWGS84AndBack(GRID.getKorean1985KoreaWestBelt());
  }

  /**
   * Test of getKuwaitOilCoLambert method, of class NationalGrids.
   */
  @Test
  public void testKuwaitOilCoLambert() {
    testToWGS84AndBack(GRID.getKuwaitOilCoLambert());
  }

  /**
   * Test of getKuwaitUtilityKTM method, of class NationalGrids.
   */
  @Test
  public void testKuwaitUtilityKTM() {
    testToWGS84AndBack(GRID.getKuwaitUtilityKTM());
  }

  /**
   * Test of getLakeMaracaiboGrid method, of class NationalGrids.
   */
  @Test
  public void testLakeMaracaiboGrid() {
    testToWGS84AndBack(GRID.getLakeMaracaiboGrid());
  }

  /**
   * Test of getLakeMaracaiboGridM1 method, of class NationalGrids.
   */
  @Test
  public void testLakeMaracaiboGridM1() {
    testToWGS84AndBack(GRID.getLakeMaracaiboGridM1());
  }

  /**
   * Test of getLakeMaracaiboGridM3 method, of class NationalGrids.
   */
  @Test
  public void testLakeMaracaiboGridM3() {
    testToWGS84AndBack(GRID.getLakeMaracaiboGridM3());
  }

  /**
   * Test of getLakeMaracaiboLaRosaGrid method, of class NationalGrids.
   */
  @Test
  public void testLakeMaracaiboLaRosaGrid() {
    testToWGS84AndBack(GRID.getLakeMaracaiboLaRosaGrid());
  }

  /**
   * Test of getLietuvosKoordinaciuSistema method, of class NationalGrids.
   */
  @Test
  public void testLietuvosKoordinaciuSistema() {
    testToWGS84AndBack(GRID.getLietuvosKoordinaciuSistema());
  }

  /**
   * Test of getLisboaBesselBonne method, of class NationalGrids.
   */
  @Test
  public void testLisboaBesselBonne() {
    testToWGS84AndBack(GRID.getLisboaBesselBonne());
  }

  /**
   * Test of getLisboaHayfordGaussIGeoE method, of class NationalGrids.
   */
  @Test
  public void testLisboaHayfordGaussIGeoE() {
    testToWGS84AndBack(GRID.getLisboaHayfordGaussIGeoE());
  }

  /**
   * Test of getLisboaHayfordGaussIPCC method, of class NationalGrids.
   */
  @Test
  public void testLisboaHayfordGaussIPCC() {
    testToWGS84AndBack(GRID.getLisboaHayfordGaussIPCC());
  }

  /**
   * Test of getLocodjo1965TM5NW method, of class NationalGrids.
   */
  @Test
  public void testLocodjo1965TM5NW() {
    testToWGS84AndBack(GRID.getLocodjo1965TM5NW());
  }

  /**
   * Test of getLuxembourg1930Gauss method, of class NationalGrids.
   */
  @Test
  public void testLuxembourg1930Gauss() {
    testToWGS84AndBack(GRID.getLuxembourg1930Gauss());
  }

  /**
   * Test of getMGI3DegreeGaussZone5 method, of class NationalGrids.
   */
  @Test
  public void testMGI3DegreeGaussZone5() {
    testToWGS84AndBack(GRID.getMGI3DegreeGaussZone5());
  }

  /**
   * Test of getMGI3DegreeGaussZone6 method, of class NationalGrids.
   */
  @Test
  public void testMGI3DegreeGaussZone6() {
    testToWGS84AndBack(GRID.getMGI3DegreeGaussZone6());
  }

  /**
   * Test of getMGI3DegreeGaussZone7 method, of class NationalGrids.
   */
  @Test
  public void testMGI3DegreeGaussZone7() {
    testToWGS84AndBack(GRID.getMGI3DegreeGaussZone7());
  }

  /**
   * Test of getMGI3DegreeGaussZone8 method, of class NationalGrids.
   */
  @Test
  public void testMGI3DegreeGaussZone8() {
    testToWGS84AndBack(GRID.getMGI3DegreeGaussZone8());
  }

  /**
   * Test of getMGIAustriaLambert method, of class NationalGrids.
   */
  @Test
  public void testMGIAustriaLambert() {
    testToWGS84AndBack(GRID.getMGIAustriaLambert());
  }

  /**
   * Test of getMGIBalkans5 method, of class NationalGrids.
   */
  @Test
  public void testMGIBalkans5() {
    testToWGS84AndBack(GRID.getMGIBalkans5());
  }

  /**
   * Test of getMGIBalkans6 method, of class NationalGrids.
   */
  @Test
  public void testMGIBalkans6() {
    testToWGS84AndBack(GRID.getMGIBalkans6());
  }

  /**
   * Test of getMGIBalkans7 method, of class NationalGrids.
   */
  @Test
  public void testMGIBalkans7() {
    testToWGS84AndBack(GRID.getMGIBalkans7());
  }

  /**
   * Test of getMGIBalkans8 method, of class NationalGrids.
   */
  @Test
  public void testMGIBalkans8() {
    testToWGS84AndBack(GRID.getMGIBalkans8());
  }

  /**
   * Test of getMGIM28 method, of class NationalGrids.
   */
  @Test
  public void testMGIM28() {
    testToWGS84AndBack(GRID.getMGIM28());
  }

  /**
   * Test of getMGIM31 method, of class NationalGrids.
   */
  @Test
  public void testMGIM31() {
    testToWGS84AndBack(GRID.getMGIM31());
  }

  /**
   * Test of getMGIM34 method, of class NationalGrids.
   */
  @Test
  public void testMGIM34() {
    testToWGS84AndBack(GRID.getMGIM34());
  }

  /**
   * Test of getMGISloveniaGrid method, of class NationalGrids.
   */
  @Test
  public void testMGISloveniaGrid() {
    testToWGS84AndBack(GRID.getMGISloveniaGrid());
  }

  /**
   * Test of getMadrid1870MadridSpain method, of class NationalGrids.
   */
  @Test
  public void testMadrid1870MadridSpain() {
    testToWGS84AndBack(GRID.getMadrid1870MadridSpain());
  }

  /**
   * Test of getMakassarNEIEZ method, of class NationalGrids.
   */
  @Test
  public void testMakassarNEIEZ() {
    testToWGS84AndBack(GRID.getMakassarNEIEZ());
  }

  /**
   * Test of getMonteMarioItaly1 method, of class NationalGrids.
   */
  @Test
  public void testMonteMarioItaly1() {
    testToWGS84AndBack(GRID.getMonteMarioItaly1());
  }

  /**
   * Test of getMonteMarioItaly2 method, of class NationalGrids.
   */
  @Test
  public void testMonteMarioItaly2() {
    testToWGS84AndBack(GRID.getMonteMarioItaly2());
  }

  /**
   * Test of getMonteMarioRomeItaly1 method, of class NationalGrids.
   */
  @Test
  public void testMonteMarioRomeItaly1() {
    testToWGS84AndBack(GRID.getMonteMarioRomeItaly1());
  }

  /**
   * Test of getMonteMarioRomeItaly2 method, of class NationalGrids.
   */
  @Test
  public void testMonteMarioRomeItaly2() {
    testToWGS84AndBack(GRID.getMonteMarioRomeItaly2());
  }

  /**
   * Test of getMontserrat1958BritishWestIndiesGrid method, of class
   * NationalGrids.
   */
  @Test
  public void testMontserrat1958BritishWestIndiesGrid() {
    testToWGS84AndBack(GRID.getMontserrat1958BritishWestIndiesGrid());
  }

  /**
   * Test of getMountDillonTobagoGrid method, of class NationalGrids.
   */
  @Test
  public void testMountDillonTobagoGrid() {
    testToWGS84AndBack(GRID.getMountDillonTobagoGrid());
  }

  /**
   * Test of getNAD1927CubaNorte method, of class NationalGrids.
   */
  @Test
  public void testNAD1927CubaNorte() {
    testToWGS84AndBack(GRID.getNAD1927CubaNorte());
  }

  /**
   * Test of getNAD1927CubaSur method, of class NationalGrids.
   */
  @Test
  public void testNAD1927CubaSur() {
    testToWGS84AndBack(GRID.getNAD1927CubaSur());
  }

  /**
   * Test of getNAD1927GuatemalaNorte method, of class NationalGrids.
   */
  @Test
  public void testNAD1927GuatemalaNorte() {
    testToWGS84AndBack(GRID.getNAD1927GuatemalaNorte());
  }

  /**
   * Test of getNAD1927GuatemalaSur method, of class NationalGrids.
   */
  @Test
  public void testNAD1927GuatemalaSur() {
    testToWGS84AndBack(GRID.getNAD1927GuatemalaSur());
  }

  /**
   * Test of getNAD1927MichiganGeoRefMeters method, of class NationalGrids.
   */
  @Test
  public void testNAD1927MichiganGeoRefMeters() {
    testToWGS84AndBack(GRID.getNAD1927MichiganGeoRefMeters());
  }

  /**
   * Test of getNAD1927MichiganGeoRefUSfeet method, of class NationalGrids.
   */
  @Test
  public void testNAD1927MichiganGeoRefUSfeet() {
    testToWGS84AndBack(GRID.getNAD1927MichiganGeoRefUSfeet());
  }

  /**
   * Test of getNAD1983HARNGuamMapGrid method, of class NationalGrids.
   */
  @Test
  public void testNAD1983HARNGuamMapGrid() {
    testToWGS84AndBack(GRID.getNAD1983HARNGuamMapGrid());
  }

  /**
   * Test of getNAD1983MichiganGeoRefMeters method, of class NationalGrids.
   */
  @Test
  public void testNAD1983MichiganGeoRefMeters() {
    testToWGS84AndBack(GRID.getNAD1983MichiganGeoRefMeters());
  }

  /**
   * Test of getNAD1983MichiganGeoRefUSfeet method, of class NationalGrids.
   */
  @Test
  public void testNAD1983MichiganGeoRefUSfeet() {
    testToWGS84AndBack(GRID.getNAD1983MichiganGeoRefUSfeet());
  }

  /**
   * Test of getNAD1983MichiganGeoReferencedMeters method, of class
   * NationalGrids.
   */
  @Test
  public void testNAD1983MichiganGeoReferencedMeters() {
    testToWGS84AndBack(GRID.getNAD1983MichiganGeoReferencedMeters());
  }

  /**
   * Test of getNTFFranceIIIdegrees method, of class NationalGrids.
   */
  @Test
  public void testNTFFranceIIIdegrees() {
    testToWGS84AndBack(GRID.getNTFFranceIIIdegrees());
  }

  /**
   * Test of getNTFFranceIIdegrees method, of class NationalGrids.
   */
  @Test
  public void testNTFFranceIIdegrees() {
    testToWGS84AndBack(GRID.getNTFFranceIIdegrees());
  }

  /**
   * Test of getNTFFranceIVdegrees method, of class NationalGrids.
   */
  @Test
  public void testNTFFranceIVdegrees() {
    testToWGS84AndBack(GRID.getNTFFranceIVdegrees());
  }

  /**
   * Test of getNTFFranceIdegrees method, of class NationalGrids.
   */
  @Test
  public void testNTFFranceIdegrees() {
    testToWGS84AndBack(GRID.getNTFFranceIdegrees());
  }

  /**
   * Test of getNewZealandMapGrid method, of class NationalGrids.
   */
  @Test
  public void testNewZealandMapGrid() {
    testToWGS84AndBack(GRID.getNewZealandMapGrid());
  }

  /**
   * Test of getNewZealandNorthIsland method, of class NationalGrids.
   */
  @Test
  public void testNewZealandNorthIsland() {
    testToWGS84AndBack(GRID.getNewZealandNorthIsland());
  }

  /**
   * Test of getNewZealandSouthIsland method, of class NationalGrids.
   */
  @Test
  public void testNewZealandSouthIsland() {
    testToWGS84AndBack(GRID.getNewZealandSouthIsland());
  }

  /**
   * Test of getNigeriaEastBelt method, of class NationalGrids.
   */
  @Test
  public void testNigeriaEastBelt() {
    testToWGS84AndBack(GRID.getNigeriaEastBelt());
  }

  /**
   * Test of getNigeriaMidBelt method, of class NationalGrids.
   */
  @Test
  public void testNigeriaMidBelt() {
    testToWGS84AndBack(GRID.getNigeriaMidBelt());
  }

  /**
   * Test of getNigeriaWestBelt method, of class NationalGrids.
   */
  @Test
  public void testNigeriaWestBelt() {
    testToWGS84AndBack(GRID.getNigeriaWestBelt());
  }

  /**
   * Test of getNordAlgerie method, of class NationalGrids.
   */
  @Test
  public void testNordAlgerie() {
    testToWGS84AndBack(GRID.getNordAlgerie());
  }

  /**
   * Test of getNordAlgerieAnciennedegrees method, of class NationalGrids.
   */
  @Test
  public void testNordAlgerieAnciennedegrees() {
    testToWGS84AndBack(GRID.getNordAlgerieAnciennedegrees());
  }

  /**
   * Test of getNordAlgerieancienne method, of class NationalGrids.
   */
  @Test
  public void testNordAlgerieancienne() {
    testToWGS84AndBack(GRID.getNordAlgerieancienne());
  }

  /**
   * Test of getNordAlgeriedegrees method, of class NationalGrids.
   */
  @Test
  public void testNordAlgeriedegrees() {
    testToWGS84AndBack(GRID.getNordAlgeriedegrees());
  }

  /**
   * Test of getNordFrance method, of class NationalGrids.
   */
  @Test
  public void testNordFrance() {
    testToWGS84AndBack(GRID.getNordFrance());
  }

  /**
   * Test of getNordMaroc method, of class NationalGrids.
   */
  @Test
  public void testNordMaroc() {
    testToWGS84AndBack(GRID.getNordMaroc());
  }

  /**
   * Test of getNordMarocdegrees method, of class NationalGrids.
   */
  @Test
  public void testNordMarocdegrees() {
    testToWGS84AndBack(GRID.getNordMarocdegrees());
  }

  /**
   * Test of getNordTunisie method, of class NationalGrids.
   */
  @Test
  public void testNordTunisie() {
    testToWGS84AndBack(GRID.getNordTunisie());
  }

  /**
   * Test of getNorddeGuerre method, of class NationalGrids.
   */
  @Test
  public void testNorddeGuerre() {
    testToWGS84AndBack(GRID.getNorddeGuerre());
  }

  /**
   * Test of getOSNI1952IrishNationalGrid method, of class NationalGrids.
   */
  @Test
  public void testOSNI1952IrishNationalGrid() {
    testToWGS84AndBack(GRID.getOSNI1952IrishNationalGrid());
  }

  /**
   * Test of getObservatorioMeteorologico1965MacauGrid method, of class
   * NationalGrids.
   */
  @Test
  public void testObservatorioMeteorologico1965MacauGrid() {
    testToWGS84AndBack(GRID.getObservatorioMeteorologico1965MacauGrid());
  }

  /**
   * Test of getPSAD1956ICNRegional method, of class NationalGrids.
   */
  @Test
  public void testPSAD1956ICNRegional() {
    testToWGS84AndBack(GRID.getPSAD1956ICNRegional());
  }

  /**
   * Test of getPalestine1923IsraelCSGrid method, of class NationalGrids.
   */
  @Test
  public void testPalestine1923IsraelCSGrid() {
    testToWGS84AndBack(GRID.getPalestine1923IsraelCSGrid());
  }

  /**
   * Test of getPalestine1923PalestineBelt method, of class NationalGrids.
   */
  @Test
  public void testPalestine1923PalestineBelt() {
    testToWGS84AndBack(GRID.getPalestine1923PalestineBelt());
  }

  /**
   * Test of getPalestine1923PalestineGrid method, of class NationalGrids.
   */
  @Test
  public void testPalestine1923PalestineGrid() {
    testToWGS84AndBack(GRID.getPalestine1923PalestineGrid());
  }

  /**
   * Test of getPampadelCastilloArgentina2 method, of class NationalGrids.
   */
  @Test
  public void testPampadelCastilloArgentina2() {
    testToWGS84AndBack(GRID.getPampadelCastilloArgentina2());
  }

  /**
   * Test of getPeruCentralZone method, of class NationalGrids.
   */
  @Test
  public void testPeruCentralZone() {
    testToWGS84AndBack(GRID.getPeruCentralZone());
  }

  /**
   * Test of getPeruEastZone method, of class NationalGrids.
   */
  @Test
  public void testPeruEastZone() {
    testToWGS84AndBack(GRID.getPeruEastZone());
  }

  /**
   * Test of getPeruWestZone method, of class NationalGrids.
   */
  @Test
  public void testPeruWestZone() {
    testToWGS84AndBack(GRID.getPeruWestZone());
  }

  /**
   * Test of getPhilippinesZoneI method, of class NationalGrids.
   */
  @Test
  public void testPhilippinesZoneI() {
    testToWGS84AndBack(GRID.getPhilippinesZoneI());
  }

  /**
   * Test of getPhilippinesZoneII method, of class NationalGrids.
   */
  @Test
  public void testPhilippinesZoneII() {
    testToWGS84AndBack(GRID.getPhilippinesZoneII());
  }

  /**
   * Test of getPhilippinesZoneIII method, of class NationalGrids.
   */
  @Test
  public void testPhilippinesZoneIII() {
    testToWGS84AndBack(GRID.getPhilippinesZoneIII());
  }

  /**
   * Test of getPhilippinesZoneIV method, of class NationalGrids.
   */
  @Test
  public void testPhilippinesZoneIV() {
    testToWGS84AndBack(GRID.getPhilippinesZoneIV());
  }

  /**
   * Test of getPhilippinesZoneV method, of class NationalGrids.
   */
  @Test
  public void testPhilippinesZoneV() {
    testToWGS84AndBack(GRID.getPhilippinesZoneV());
  }

  /**
   * Test of getPitondesNeigesTMReunion method, of class NationalGrids.
   */
  @Test
  public void testPitondesNeigesTMReunion() {
    testToWGS84AndBack(GRID.getPitondesNeigesTMReunion());
  }

  /**
   * Test of getPortugueseNationalGrid method, of class NationalGrids.
   */
  @Test
  public void testPortugueseNationalGrid() {
    testToWGS84AndBack(GRID.getPortugueseNationalGrid());
  }

  /**
   * Test of getQatar1948QatarGrid method, of class NationalGrids.
   */
  @Test
  public void testQatar1948QatarGrid() {
    testToWGS84AndBack(GRID.getQatar1948QatarGrid());
  }

  /**
   * Test of getQatarNationalGrid method, of class NationalGrids.
   */
  @Test
  public void testQatarNationalGrid() {
    testToWGS84AndBack(GRID.getQatarNationalGrid());
  }

  /**
   * Test of getRGF1993Lambert93 method, of class NationalGrids.
   */
  @Test
  public void testRGF1993Lambert93() {
    testToWGS84AndBack(GRID.getRGF1993Lambert93());
  }

  /**
   * Test of getRGNC1991LambertNewCaledonia method, of class NationalGrids.
   */
  @Test
  public void testRGNC1991LambertNewCaledonia() {
    testToWGS84AndBack(GRID.getRGNC1991LambertNewCaledonia());
  }

  /**
   * Test of getRT9025gonWest method, of class NationalGrids.
   */
  @Test
  public void testRT9025gonWest() {
    testToWGS84AndBack(GRID.getRT9025gonWest());
  }

  /**
   * Test of getRassadiranNakhleTaqi method, of class NationalGrids.
   */
  @Test
  public void testRassadiranNakhleTaqi() {
    testToWGS84AndBack(GRID.getRassadiranNakhleTaqi());
  }



  /**
   * Test of getRoma1940GaussBoagaEst method, of class NationalGrids.
   */
  @Test
  public void testRoma1940GaussBoagaEst() {
    testToWGS84AndBack(GRID.getRoma1940GaussBoagaEst());
  }

  /**
   * Test of getRoma1940GaussBoagaOvest method, of class NationalGrids.
   */
  @Test
  public void testRoma1940GaussBoagaOvest() {
    testToWGS84AndBack(GRID.getRoma1940GaussBoagaOvest());
  }

  /**
   * Test of getSAD1969BrazilPolyconic method, of class NationalGrids.
   */
  @Test
  public void testSAD1969BrazilPolyconic() {
    testToWGS84AndBack(GRID.getSAD1969BrazilPolyconic());
  }

  /**
   * Test of getSJTSKFerroKrovak method, of class NationalGrids.
   */
  @Test
  public void testSJTSKFerroKrovak() {
    testToWGS84AndBack(GRID.getSJTSKFerroKrovak());
  }

  /**
   * Test of getSJTSKFerroKrovakEastNorth method, of class NationalGrids.
   */
  @Test
  public void testSJTSKFerroKrovakEastNorth() {
    testToWGS84AndBack(GRID.getSJTSKFerroKrovakEastNorth());
  }

  /**
   * Test of getSJTSKKrovak method, of class NationalGrids.
   */
  @Test
  public void testSJTSKKrovak() {
    testToWGS84AndBack(GRID.getSJTSKKrovak());
  }

  /**
   * Test of getSJTSKKrovakEastNorth method, of class NationalGrids.
   */
  @Test
  public void testSJTSKKrovakEastNorth() {
    testToWGS84AndBack(GRID.getSJTSKKrovakEastNorth());
  }

  /**
   * Test of getSahara method, of class NationalGrids.
   */
  @Test
  public void testSahara() {
    testToWGS84AndBack(GRID.getSahara());
  }

  /**
   * Test of getSaharadegrees method, of class NationalGrids.
   */
  @Test
  public void testSaharadegrees() {
    testToWGS84AndBack(GRID.getSaharadegrees());
  }

  /**
   * Test of getSierraLeone1924NewColonyGrid method, of class NationalGrids.
   */
  @Test
  public void testSierraLeone1924NewColonyGrid() {
    testToWGS84AndBack(GRID.getSierraLeone1924NewColonyGrid());
  }

  /**
   * Test of getSierraLeone1924NewWarOfficeGrid method, of class NationalGrids.
   */
  @Test
  public void testSierraLeone1924NewWarOfficeGrid() {
    testToWGS84AndBack(GRID.getSierraLeone1924NewWarOfficeGrid());
  }

  /**
   * Test of getStKitts1955BritishWestIndiesGrid method, of class NationalGrids.
   */
  @Test
  public void testStKitts1955BritishWestIndiesGrid() {
    testToWGS84AndBack(GRID.getStKitts1955BritishWestIndiesGrid());
  }

  /**
   * Test of getStLucia1955BritishWestIndiesGrid method, of class NationalGrids.
   */
  @Test
  public void testStLucia1955BritishWestIndiesGrid() {
    testToWGS84AndBack(GRID.getStLucia1955BritishWestIndiesGrid());
  }

  /**
   * Test of getStVincent1945BritishWestIndiesGrid method, of class
   * NationalGrids.
   */
  @Test
  public void testStVincent1945BritishWestIndiesGrid() {
    testToWGS84AndBack(GRID.getStVincent1945BritishWestIndiesGrid());
  }

 
  /**
   * Test of getSudAlgerie method, of class NationalGrids.
   */
  @Test
  public void testSudAlgerie() {
    testToWGS84AndBack(GRID.getSudAlgerie());
  }

  /**
   * Test of getSudAlgerieAncienneDegree method, of class NationalGrids.
   */
  @Test
  public void testSudAlgerieAncienneDegree() {
    testToWGS84AndBack(GRID.getSudAlgerieAncienneDegree());
  }

  /**
   * Test of getSudAlgeriedegrees method, of class NationalGrids.
   */
  @Test
  public void testSudAlgeriedegrees() {
    testToWGS84AndBack(GRID.getSudAlgeriedegrees());
  }

  /**
   * Test of getSudFrance method, of class NationalGrids.
   */
  @Test
  public void testSudFrance() {
    testToWGS84AndBack(GRID.getSudFrance());
  }

  /**
   * Test of getSudMaroc method, of class NationalGrids.
   */
  @Test
  public void testSudMaroc() {
    testToWGS84AndBack(GRID.getSudMaroc());
  }

  /**
   * Test of getSudMarocdegrees method, of class NationalGrids.
   */
  @Test
  public void testSudMarocdegrees() {
    testToWGS84AndBack(GRID.getSudMarocdegrees());
  }

  /**
   * Test of getSudTunisie method, of class NationalGrids.
   */
  @Test
  public void testSudTunisie() {
    testToWGS84AndBack(GRID.getSudTunisie());
  }

  /**
   * Test of getSwedishNationalGrid method, of class NationalGrids.
   */
  @Test
  public void testSwedishNationalGrid() {
    testToWGS84AndBack(GRID.getSwedishNationalGrid());
  }

  /**
   * Test of getTM75IrishGrid method, of class NationalGrids.
   */
  @Test
  public void testTM75IrishGrid() {
    testToWGS84AndBack(GRID.getTM75IrishGrid());
  }

  /**
   * Test of getTimbalai1948RSOBorneoChains method, of class NationalGrids.
   */
  @Test
  public void testTimbalai1948RSOBorneoChains() {
    testToWGS84AndBack(GRID.getTimbalai1948RSOBorneoChains());
  }

  /**
   * Test of getTimbalai1948RSOBorneoFeet method, of class NationalGrids.
   */
  @Test
  public void testTimbalai1948RSOBorneoFeet() {
    testToWGS84AndBack(GRID.getTimbalai1948RSOBorneoFeet());
  }

  /**
   * Test of getTimbalai1948RSOBorneoMeters method, of class NationalGrids.
   */
  @Test
  public void testTimbalai1948RSOBorneoMeters() {
    testToWGS84AndBack(GRID.getTimbalai1948RSOBorneoMeters());
  }

  /**
   * Test of getTrinidad1903TrinidadGrid method, of class NationalGrids.
   */
  @Test
  public void testTrinidad1903TrinidadGrid() {
    testToWGS84AndBack(GRID.getTrinidad1903TrinidadGrid());
  }

  /**
   * Test of getTrinidad1903TrinidadGridFeetClarke method, of class
   * NationalGrids.
   */
  @Test
  public void testTrinidad1903TrinidadGridFeetClarke() {
    testToWGS84AndBack(GRID.getTrinidad1903TrinidadGridFeetClarke());
  }

  /**
   * Test of getUWPP1992 method, of class NationalGrids.
   */
  @Test
  public void testUWPP1992() {
    testToWGS84AndBack(GRID.getUWPP1992());
  }

  /**
   * Test of getUWPP2000pas5 method, of class NationalGrids.
   */
  @Test
  public void testUWPP2000pas5() {
    testToWGS84AndBack(GRID.getUWPP2000pas5());
  }

  /**
   * Test of getUWPP2000pas6 method, of class NationalGrids.
   */
  @Test
  public void testUWPP2000pas6() {
    testToWGS84AndBack(GRID.getUWPP2000pas6());
  }

  /**
   * Test of getUWPP2000pas7 method, of class NationalGrids.
   */
  @Test
  public void testUWPP2000pas7() {
    testToWGS84AndBack(GRID.getUWPP2000pas7());
  }

  /**
   * Test of getUWPP2000pas8 method, of class NationalGrids.
   */
  @Test
  public void testUWPP2000pas8() {
    testToWGS84AndBack(GRID.getUWPP2000pas8());
  }

  /**
   * Test of getWGS1972TM106NE method, of class NationalGrids.
   */
  @Test
  public void testWGS1972TM106NE() {
    testToWGS84AndBack(GRID.getWGS1972TM106NE());
  }

  /**
   * Test of getWGS1984TM116SE method, of class NationalGrids.
   */
  @Test
  public void testWGS1984TM116SE() {
    testToWGS84AndBack(GRID.getWGS1984TM116SE());
  }

  /**
   * Test of getWGS1984TM132SE method, of class NationalGrids.
   */
  @Test
  public void testWGS1984TM132SE() {
    testToWGS84AndBack(GRID.getWGS1984TM132SE());
  }

  /**
   * Test of getWGS1984TM36SE method, of class NationalGrids.
   */
  @Test
  public void testWGS1984TM36SE() {
    testToWGS84AndBack(GRID.getWGS1984TM36SE());
  }

  /**
   * Test of getWGS1984TM6NE method, of class NationalGrids.
   */
  @Test
  public void testWGS1984TM6NE() {
    testToWGS84AndBack(GRID.getWGS1984TM6NE());
  }

  /**
   * Test of getZanderijSurinameOldTM method, of class NationalGrids.
   */
  @Test
  public void testZanderijSurinameOldTM() {
    testToWGS84AndBack(GRID.getZanderijSurinameOldTM());
  }

  /**
   * Test of getZanderijSurinameTM method, of class NationalGrids.
   */
  @Test
  public void testZanderijSurinameTM() {
    testToWGS84AndBack(GRID.getZanderijSurinameTM());
  }

  /**
   * Test of getZanderijTM54NW method, of class NationalGrids.
   */
  @Test
  public void testZanderijTM54NW() {
    testToWGS84AndBack(GRID.getZanderijTM54NW());
  }

}
