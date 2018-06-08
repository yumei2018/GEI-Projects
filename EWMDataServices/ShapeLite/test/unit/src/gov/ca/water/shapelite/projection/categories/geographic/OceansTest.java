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
public class OceansTest {

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
  private static final Oceans PROJ = Projections.getGeographic().getOceans();

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
   * Test of getAlaskanIslands method, of class Oceans.
   */
  @Test
  public void testGetAlaskanIslands() {
    testToWGS84AndBack(PROJ.getAlaskanIslands());
  }

  /**
   * Test of getAmericanSamoa1962 method, of class Oceans.
   */
  @Test
  public void testGetAmericanSamoa1962() {
    testToWGS84AndBack(PROJ.getAmericanSamoa1962());
  }

  /**
   * Test of getAnguilla1957 method, of class Oceans.
   */
  @Test
  public void testGetAnguilla1957() {
    testToWGS84AndBack(PROJ.getAnguilla1957());
  }

  /**
   * Test of getAnna1Astro1965 method, of class Oceans.
   */
  @Test
  public void testGetAnna1Astro1965() {
    testToWGS84AndBack(PROJ.getAnna1Astro1965());
  }

  /**
   * Test of getAntigua1943 method, of class Oceans.
   */
  @Test
  public void testGetAntigua1943() {
    testToWGS84AndBack(PROJ.getAntigua1943());
  }

  /**
   * Test of getAscensionIsland1958 method, of class Oceans.
   */
  @Test
  public void testGetAscensionIsland1958() {
    testToWGS84AndBack(PROJ.getAscensionIsland1958());
  }

  /**
   * Test of getAstroBeaconE1945 method, of class Oceans.
   */
  @Test
  public void testGetAstroBeaconE1945() {
    testToWGS84AndBack(PROJ.getAstroBeaconE1945());
  }

  /**
   * Test of getAstroDOS714 method, of class Oceans.
   */
  @Test
  public void testGetAstroDOS714() {
    testToWGS84AndBack(PROJ.getAstroDOS714());
  }

  /**
   * Test of getAstronomicalStation1952 method, of class Oceans.
   */
  @Test
  public void testGetAstronomicalStation1952() {
    testToWGS84AndBack(PROJ.getAstronomicalStation1952());
  }

  /**
   * Test of getAzoresCentral1948 method, of class Oceans.
   */
  @Test
  public void testGetAzoresCentral1948() {
    testToWGS84AndBack(PROJ.getAzoresCentral1948());
  }

  /**
   * Test of getAzoresCentral1995 method, of class Oceans.
   */
  @Test
  public void testGetAzoresCentral1995() {
    testToWGS84AndBack(PROJ.getAzoresCentral1995());
  }

  /**
   * Test of getAzoresOccidental1939 method, of class Oceans.
   */
  @Test
  public void testGetAzoresOccidental1939() {
    testToWGS84AndBack(PROJ.getAzoresOccidental1939());
  }

  /**
   * Test of getAzoresOriental1940 method, of class Oceans.
   */
  @Test
  public void testGetAzoresOriental1940() {
    testToWGS84AndBack(PROJ.getAzoresOriental1940());
  }

  /**
   * Test of getAzoresOriental1995 method, of class Oceans.
   */
  @Test
  public void testGetAzoresOriental1995() {
    testToWGS84AndBack(PROJ.getAzoresOriental1995());
  }

  /**
   * Test of getBabSouth method, of class Oceans.
   */
  @Test
  public void testGetBabSouth() {
    testToWGS84AndBack(PROJ.getBabSouth());
  }

  /**
   * Test of getBarbados method, of class Oceans.
   */
  @Test
  public void testGetBarbados() {
    testToWGS84AndBack(PROJ.getBarbados());
  }

  /**
   * Test of getBarbados1938 method, of class Oceans.
   */
  @Test
  public void testGetBarbados1938() {
    testToWGS84AndBack(PROJ.getBarbados1938());
  }

  /**
   * Test of getBellevueIGN method, of class Oceans.
   */
  @Test
  public void testGetBellevueIGN() {
    testToWGS84AndBack(PROJ.getBellevueIGN());
  }

  /**
   * Test of getBermuda1957 method, of class Oceans.
   */
  @Test
  public void testGetBermuda1957() {
    testToWGS84AndBack(PROJ.getBermuda1957());
  }

  /**
   * Test of getBermuda2000 method, of class Oceans.
   */
  @Test
  public void testGetBermuda2000() {
    testToWGS84AndBack(PROJ.getBermuda2000());
  }

  /**
   * Test of getCSG1967 method, of class Oceans.
   */
  @Test
  public void testGetCSG1967() {
    testToWGS84AndBack(PROJ.getCSG1967());
  }

  /**
   * Test of getCantonAstro1966 method, of class Oceans.
   */
  @Test
  public void testGetCantonAstro1966() {
    testToWGS84AndBack(PROJ.getCantonAstro1966());
  }

  /**
   * Test of getChathamIslandAstro1971 method, of class Oceans.
   */
  @Test
  public void testGetChathamIslandAstro1971() {
    testToWGS84AndBack(PROJ.getChathamIslandAstro1971());
  }

  /**
   * Test of getCombani1950 method, of class Oceans.
   */
  @Test
  public void testGetCombani1950() {
    testToWGS84AndBack(PROJ.getCombani1950());
  }

  /**
   * Test of getDOS1968 method, of class Oceans.
   */
  @Test
  public void testGetDOS1968() {
    testToWGS84AndBack(PROJ.getDOS1968());
  }

  /**
   * Test of getDominica1945 method, of class Oceans.
   */
  @Test
  public void testGetDominica1945() {
    testToWGS84AndBack(PROJ.getDominica1945());
  }

  /**
   * Test of getEasterIsland1967 method, of class Oceans.
   */
  @Test
  public void testGetEasterIsland1967() {
    testToWGS84AndBack(PROJ.getEasterIsland1967());
  }

  /**
   * Test of getFortDesaix method, of class Oceans.
   */
  @Test
  public void testGetFortDesaix() {
    testToWGS84AndBack(PROJ.getFortDesaix());
  }

  /**
   * Test of getFortMarigot method, of class Oceans.
   */
  @Test
  public void testGetFortMarigot() {
    testToWGS84AndBack(PROJ.getFortMarigot());
  }

  /**
   * Test of getFortThomas1955 method, of class Oceans.
   */
  @Test
  public void testGetFortThomas1955() {
    testToWGS84AndBack(PROJ.getFortThomas1955());
  }

  /**
   * Test of getGUX1Astro method, of class Oceans.
   */
  @Test
  public void testGetGUX1Astro() {
    testToWGS84AndBack(PROJ.getGUX1Astro());
  }

  /**
   * Test of getGan1970 method, of class Oceans.
   */
  @Test
  public void testGetGan1970() {
    testToWGS84AndBack(PROJ.getGan1970());
  }

  /**
   * Test of getGraciosaBaseSW1948 method, of class Oceans.
   */
  @Test
  public void testGetGraciosaBaseSW1948() {
    testToWGS84AndBack(PROJ.getGraciosaBaseSW1948());
  }

  /**
   * Test of getGrandComoros method, of class Oceans.
   */
  @Test
  public void testGetGrandComoros() {
    testToWGS84AndBack(PROJ.getGrandComoros());
  }

  /**
   * Test of getGrenada1953 method, of class Oceans.
   */
  @Test
  public void testGetGrenada1953() {
    testToWGS84AndBack(PROJ.getGrenada1953());
  }

  /**
   * Test of getGuam1963 method, of class Oceans.
   */
  @Test
  public void testGetGuam1963() {
    testToWGS84AndBack(PROJ.getGuam1963());
  }

  /**
   * Test of getHjorsey1955 method, of class Oceans.
   */
  @Test
  public void testGetHjorsey1955() {
    testToWGS84AndBack(PROJ.getHjorsey1955());
  }

  /**
   * Test of getIGN53Mare method, of class Oceans.
   */
  @Test
  public void testGetIGN53Mare() {
    testToWGS84AndBack(PROJ.getIGN53Mare());
  }

  /**
   * Test of getIGN56Lifou method, of class Oceans.
   */
  @Test
  public void testGetIGN56Lifou() {
    testToWGS84AndBack(PROJ.getIGN56Lifou());
  }

  /**
   * Test of getIGN72GrandeTerre method, of class Oceans.
   */
  @Test
  public void testGetIGN72GrandeTerre() {
    testToWGS84AndBack(PROJ.getIGN72GrandeTerre());
  }

  /**
   * Test of getIGN72NukuHiva method, of class Oceans.
   */
  @Test
  public void testGetIGN72NukuHiva() {
    testToWGS84AndBack(PROJ.getIGN72NukuHiva());
  }

  /**
   * Test of getISTS061Astro1968 method, of class Oceans.
   */
  @Test
  public void testGetISTS061Astro1968() {
    testToWGS84AndBack(PROJ.getISTS061Astro1968());
  }

  /**
   * Test of getISTS073Astro1969 method, of class Oceans.
   */
  @Test
  public void testGetISTS073Astro1969() {
    testToWGS84AndBack(PROJ.getISTS073Astro1969());
  }

  /**
   * Test of getJamaica1875 method, of class Oceans.
   */
  @Test
  public void testGetJamaica1875() {
    testToWGS84AndBack(PROJ.getJamaica1875());
  }

  /**
   * Test of getJamaica1969 method, of class Oceans.
   */
  @Test
  public void testGetJamaica1969() {
    testToWGS84AndBack(PROJ.getJamaica1969());
  }

  /**
   * Test of getJohnstonIsland1961 method, of class Oceans.
   */
  @Test
  public void testGetJohnstonIsland1961() {
    testToWGS84AndBack(PROJ.getJohnstonIsland1961());
  }

  /**
   * Test of getK01949 method, of class Oceans.
   */
  @Test
  public void testGetK01949() {
    testToWGS84AndBack(PROJ.getK01949());
  }

  /**
   * Test of getKerguelenIsland1949 method, of class Oceans.
   */
  @Test
  public void testGetKerguelenIsland1949() {
    testToWGS84AndBack(PROJ.getKerguelenIsland1949());
  }

  /**
   * Test of getKusaieAstro1951 method, of class Oceans.
   */
  @Test
  public void testGetKusaieAstro1951() {
    testToWGS84AndBack(PROJ.getKusaieAstro1951());
  }

  /**
   * Test of getLC5Astro1961 method, of class Oceans.
   */
  @Test
  public void testGetLC5Astro1961() {
    testToWGS84AndBack(PROJ.getLC5Astro1961());
  }

  /**
   * Test of getMOP78 method, of class Oceans.
   */
  @Test
  public void testGetMOP78() {
    testToWGS84AndBack(PROJ.getMOP78());
  }

  /**
   * Test of getMadeira1936 method, of class Oceans.
   */
  @Test
  public void testGetMadeira1936() {
    testToWGS84AndBack(PROJ.getMadeira1936());
  }

  /**
   * Test of getMahe1971 method, of class Oceans.
   */
  @Test
  public void testGetMahe1971() {
    testToWGS84AndBack(PROJ.getMahe1971());
  }

  /**
   * Test of getMajuro method, of class Oceans.
   */
  @Test
  public void testGetMajuro() {
    testToWGS84AndBack(PROJ.getMajuro());
  }

  /**
   * Test of getMidwayAstro1961 method, of class Oceans.
   */
  @Test
  public void testGetMidwayAstro1961() {
    testToWGS84AndBack(PROJ.getMidwayAstro1961());
  }

  /**
   * Test of getMontserrat1958 method, of class Oceans.
   */
  @Test
  public void testGetMontserrat1958() {
    testToWGS84AndBack(PROJ.getMontserrat1958());
  }

  /**
   * Test of getNEA74Noumea method, of class Oceans.
   */
  @Test
  public void testGetNEA74Noumea() {
    testToWGS84AndBack(PROJ.getNEA74Noumea());
  }

  /**
   * Test of getObservMeteorologico1939 method, of class Oceans.
   */
  @Test
  public void testGetObservMeteorologico1939() {
    testToWGS84AndBack(PROJ.getObservMeteorologico1939());
  }

  /**
   * Test of getOldHawaiian method, of class Oceans.
   */
  @Test
  public void testGetOldHawaiian() {
    testToWGS84AndBack(PROJ.getOldHawaiian());
  }

  /**
   * Test of getPicodeLasNieves method, of class Oceans.
   */
  @Test
  public void testGetPicodeLasNieves() {
    testToWGS84AndBack(PROJ.getPicodeLasNieves());
  }

  /**
   * Test of getPitcairnAstro1967 method, of class Oceans.
   */
  @Test
  public void testGetPitcairnAstro1967() {
    testToWGS84AndBack(PROJ.getPitcairnAstro1967());
  }

  /**
   * Test of getPitondesNeiges method, of class Oceans.
   */
  @Test
  public void testGetPitondesNeiges() {
    testToWGS84AndBack(PROJ.getPitondesNeiges());
  }

  /**
   * Test of getPohnpei method, of class Oceans.
   */
  @Test
  public void testGetPohnpei() {
    testToWGS84AndBack(PROJ.getPohnpei());
  }

  /**
   * Test of getPortoSanto1936 method, of class Oceans.
   */
  @Test
  public void testGetPortoSanto1936() {
    testToWGS84AndBack(PROJ.getPortoSanto1936());
  }

  /**
   * Test of getPortoSanto1995 method, of class Oceans.
   */
  @Test
  public void testGetPortoSanto1995() {
    testToWGS84AndBack(PROJ.getPortoSanto1995());
  }

  /**
   * Test of getPuertoRico method, of class Oceans.
   */
  @Test
  public void testGetPuertoRico() {
    testToWGS84AndBack(PROJ.getPuertoRico());
  }

  /**
   * Test of getRGFG1995 method, of class Oceans.
   */
  @Test
  public void testGetRGFG1995() {
    testToWGS84AndBack(PROJ.getRGFG1995());
  }

  /**
   * Test of getRGNC1991 method, of class Oceans.
   */
  @Test
  public void testGetRGNC1991() {
    testToWGS84AndBack(PROJ.getRGNC1991());
  }

  /**
   * Test of getRGR1992 method, of class Oceans.
   */
  @Test
  public void testGetRGR1992() {
    testToWGS84AndBack(PROJ.getRGR1992());
  }

  /**
   * Test of getRRAF1991 method, of class Oceans.
   */
  @Test
  public void testGetRRAF1991() {
    testToWGS84AndBack(PROJ.getRRAF1991());
  }

  /**
   * Test of getReunion method, of class Oceans.
   */
  @Test
  public void testGetReunion() {
    testToWGS84AndBack(PROJ.getReunion());
  }

  /**
   * Test of getST71Belep method, of class Oceans.
   */
  @Test
  public void testGetST71Belep() {
    testToWGS84AndBack(PROJ.getST71Belep());
  }

  /**
   * Test of getST84IledesPins method, of class Oceans.
   */
  @Test
  public void testGetST84IledesPins() {
    testToWGS84AndBack(PROJ.getST84IledesPins());
  }

  /**
   * Test of getST87Ouvea method, of class Oceans.
   */
  @Test
  public void testGetST87Ouvea() {
    testToWGS84AndBack(PROJ.getST87Ouvea());
  }

  /**
   * Test of getSaintPierreetMiquelon1950 method, of class Oceans.
   */
  @Test
  public void testGetSaintPierreetMiquelon1950() {
    testToWGS84AndBack(PROJ.getSaintPierreetMiquelon1950());
  }

  /**
   * Test of getSainteAnne method, of class Oceans.
   */
  @Test
  public void testGetSainteAnne() {
    testToWGS84AndBack(PROJ.getSainteAnne());
  }

  /**
   * Test of getSantoDOS1965 method, of class Oceans.
   */
  @Test
  public void testGetSantoDOS1965() {
    testToWGS84AndBack(PROJ.getSantoDOS1965());
  }

  /**
   * Test of getSaoBraz method, of class Oceans.
   */
  @Test
  public void testGetSaoBraz() {
    testToWGS84AndBack(PROJ.getSaoBraz());
  }

  /**
   * Test of getSapperHill1943 method, of class Oceans.
   */
  @Test
  public void testGetSapperHill1943() {
    testToWGS84AndBack(PROJ.getSapperHill1943());
  }

  /**
   * Test of getSelvagemGrande1938 method, of class Oceans.
   */
  @Test
  public void testGetSelvagemGrande1938() {
    testToWGS84AndBack(PROJ.getSelvagemGrande1938());
  }

  /**
   * Test of getStKitts1955 method, of class Oceans.
   */
  @Test
  public void testGetStKitts1955() {
    testToWGS84AndBack(PROJ.getStKitts1955());
  }

  /**
   * Test of getStLucia1955 method, of class Oceans.
   */
  @Test
  public void testGetStLucia1955() {
    testToWGS84AndBack(PROJ.getStLucia1955());
  }

  /**
   * Test of getStVincent1945 method, of class Oceans.
   */
  @Test
  public void testGetStVincent1945() {
    testToWGS84AndBack(PROJ.getStVincent1945());
  }

  /**
   * Test of getTahaa method, of class Oceans.
   */
  @Test
  public void testGetTahaa() {
    testToWGS84AndBack(PROJ.getTahaa());
  }

  /**
   * Test of getTahiti method, of class Oceans.
   */
  @Test
  public void testGetTahiti() {
    testToWGS84AndBack(PROJ.getTahiti());
  }

  /**
   * Test of getTernIslandAstro1961 method, of class Oceans.
   */
  @Test
  public void testGetTernIslandAstro1961() {
    testToWGS84AndBack(PROJ.getTernIslandAstro1961());
  }

  /**
   * Test of getTristanAstro1968 method, of class Oceans.
   */
  @Test
  public void testGetTristanAstro1968() {
    testToWGS84AndBack(PROJ.getTristanAstro1968());
  }

  /**
   * Test of getVitiLevu1916 method, of class Oceans.
   */
  @Test
  public void testGetVitiLevu1916() {
    testToWGS84AndBack(PROJ.getVitiLevu1916());
  }

  /**
   * Test of getWakeEniwetok1960 method, of class Oceans.
   */
  @Test
  public void testGetWakeEniwetok1960() {
    testToWGS84AndBack(PROJ.getWakeEniwetok1960());
  }

  /**
   * Test of getWakeIslandAstro1952 method, of class Oceans.
   */
  @Test
  public void testGetWakeIslandAstro1952() {
    testToWGS84AndBack(PROJ.getWakeIslandAstro1952());
  }

}
