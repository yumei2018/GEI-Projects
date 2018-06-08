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
public class SolarSystemTest {

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
  private static final SolarSystem PROJ = Projections.getGeographic().getSolarSystem();

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
   * Test of getAdrastea2000 method, of class SolarSystem.
   */
  @Test
  public void testGetAdrastea2000() {
    testToWGS84AndBack(PROJ.getAdrastea2000());
  }

  /**
   * Test of getAmalthea2000 method, of class SolarSystem.
   */
  @Test
  public void testGetAmalthea2000() {
    testToWGS84AndBack(PROJ.getAmalthea2000());
  }

  /**
   * Test of getAnanke2000 method, of class SolarSystem.
   */
  @Test
  public void testGetAnanke2000() {
    testToWGS84AndBack(PROJ.getAnanke2000());
  }

  /**
   * Test of getAriel2000 method, of class SolarSystem.
   */
  @Test
  public void testGetAriel2000() {
    testToWGS84AndBack(PROJ.getAriel2000());
  }

  /**
   * Test of getAtlas2000 method, of class SolarSystem.
   */
  @Test
  public void testGetAtlas2000() {
    testToWGS84AndBack(PROJ.getAtlas2000());
  }

  /**
   * Test of getBelinda2000 method, of class SolarSystem.
   */
  @Test
  public void testGetBelinda2000() {
    testToWGS84AndBack(PROJ.getBelinda2000());
  }

  /**
   * Test of getBianca2000 method, of class SolarSystem.
   */
  @Test
  public void testGetBianca2000() {
    testToWGS84AndBack(PROJ.getBianca2000());
  }

  /**
   * Test of getCallisto2000 method, of class SolarSystem.
   */
  @Test
  public void testGetCallisto2000() {
    testToWGS84AndBack(PROJ.getCallisto2000());
  }

  /**
   * Test of getCalypso2000 method, of class SolarSystem.
   */
  @Test
  public void testGetCalypso2000() {
    testToWGS84AndBack(PROJ.getCalypso2000());
  }

  /**
   * Test of getCarme2000 method, of class SolarSystem.
   */
  @Test
  public void testGetCarme2000() {
    testToWGS84AndBack(PROJ.getCarme2000());
  }

  /**
   * Test of getCharon2000 method, of class SolarSystem.
   */
  @Test
  public void testGetCharon2000() {
    testToWGS84AndBack(PROJ.getCharon2000());
  }

  /**
   * Test of getCordelia2000 method, of class SolarSystem.
   */
  @Test
  public void testGetCordelia2000() {
    testToWGS84AndBack(PROJ.getCordelia2000());
  }

  /**
   * Test of getCressida2000 method, of class SolarSystem.
   */
  @Test
  public void testGetCressida2000() {
    testToWGS84AndBack(PROJ.getCressida2000());
  }

  /**
   * Test of getDeimos2000 method, of class SolarSystem.
   */
  @Test
  public void testGetDeimos2000() {
    testToWGS84AndBack(PROJ.getDeimos2000());
  }

  /**
   * Test of getDesdemona2000 method, of class SolarSystem.
   */
  @Test
  public void testGetDesdemona2000() {
    testToWGS84AndBack(PROJ.getDesdemona2000());
  }

  /**
   * Test of getDespina2000 method, of class SolarSystem.
   */
  @Test
  public void testGetDespina2000() {
    testToWGS84AndBack(PROJ.getDespina2000());
  }

  /**
   * Test of getDione2000 method, of class SolarSystem.
   */
  @Test
  public void testGetDione2000() {
    testToWGS84AndBack(PROJ.getDione2000());
  }

  /**
   * Test of getElara2000 method, of class SolarSystem.
   */
  @Test
  public void testGetElara2000() {
    testToWGS84AndBack(PROJ.getElara2000());
  }

  /**
   * Test of getEnceladus2000 method, of class SolarSystem.
   */
  @Test
  public void testGetEnceladus2000() {
    testToWGS84AndBack(PROJ.getEnceladus2000());
  }

  /**
   * Test of getEpimetheus2000 method, of class SolarSystem.
   */
  @Test
  public void testGetEpimetheus2000() {
    testToWGS84AndBack(PROJ.getEpimetheus2000());
  }

  /**
   * Test of getEuropa2000 method, of class SolarSystem.
   */
  @Test
  public void testGetEuropa2000() {
    testToWGS84AndBack(PROJ.getEuropa2000());
  }

  /**
   * Test of getGalatea2000 method, of class SolarSystem.
   */
  @Test
  public void testGetGalatea2000() {
    testToWGS84AndBack(PROJ.getGalatea2000());
  }

  /**
   * Test of getGanymede2000 method, of class SolarSystem.
   */
  @Test
  public void testGetGanymede2000() {
    testToWGS84AndBack(PROJ.getGanymede2000());
  }

  /**
   * Test of getHelene2000 method, of class SolarSystem.
   */
  @Test
  public void testGetHelene2000() {
    testToWGS84AndBack(PROJ.getHelene2000());
  }

  /**
   * Test of getHimalia2000 method, of class SolarSystem.
   */
  @Test
  public void testGetHimalia2000() {
    testToWGS84AndBack(PROJ.getHimalia2000());
  }

  /**
   * Test of getHyperion2000 method, of class SolarSystem.
   */
  @Test
  public void testGetHyperion2000() {
    testToWGS84AndBack(PROJ.getHyperion2000());
  }

  /**
   * Test of getIapetus2000 method, of class SolarSystem.
   */
  @Test
  public void testGetIapetus2000() {
    testToWGS84AndBack(PROJ.getIapetus2000());
  }

  /**
   * Test of getIo2000 method, of class SolarSystem.
   */
  @Test
  public void testGetIo2000() {
    testToWGS84AndBack(PROJ.getIo2000());
  }

  /**
   * Test of getJanus2000 method, of class SolarSystem.
   */
  @Test
  public void testGetJanus2000() {
    testToWGS84AndBack(PROJ.getJanus2000());
  }

  /**
   * Test of getJuliet2000 method, of class SolarSystem.
   */
  @Test
  public void testGetJuliet2000() {
    testToWGS84AndBack(PROJ.getJuliet2000());
  }

  /**
   * Test of getJupiter2000 method, of class SolarSystem.
   */
  @Test
  public void testGetJupiter2000() {
    testToWGS84AndBack(PROJ.getJupiter2000());
  }

  /**
   * Test of getLarissa2000 method, of class SolarSystem.
   */
  @Test
  public void testGetLarissa2000() {
    testToWGS84AndBack(PROJ.getLarissa2000());
  }

  /**
   * Test of getLeda2000 method, of class SolarSystem.
   */
  @Test
  public void testGetLeda2000() {
    testToWGS84AndBack(PROJ.getLeda2000());
  }

  /**
   * Test of getLysithea2000 method, of class SolarSystem.
   */
  @Test
  public void testGetLysithea2000() {
    testToWGS84AndBack(PROJ.getLysithea2000());
  }

  /**
   * Test of getMars1979 method, of class SolarSystem.
   */
  @Test
  public void testGetMars1979() {
    testToWGS84AndBack(PROJ.getMars1979());
  }

  /**
   * Test of getMars2000 method, of class SolarSystem.
   */
  @Test
  public void testGetMars2000() {
    testToWGS84AndBack(PROJ.getMars2000());
  }

  /**
   * Test of getMercury2000 method, of class SolarSystem.
   */
  @Test
  public void testGetMercury2000() {
    testToWGS84AndBack(PROJ.getMercury2000());
  }

  /**
   * Test of getMetis2000 method, of class SolarSystem.
   */
  @Test
  public void testGetMetis2000() {
    testToWGS84AndBack(PROJ.getMetis2000());
  }

  /**
   * Test of getMimas2000 method, of class SolarSystem.
   */
  @Test
  public void testGetMimas2000() {
    testToWGS84AndBack(PROJ.getMimas2000());
  }

  /**
   * Test of getMiranda2000 method, of class SolarSystem.
   */
  @Test
  public void testGetMiranda2000() {
    testToWGS84AndBack(PROJ.getMiranda2000());
  }

  /**
   * Test of getMoon2000 method, of class SolarSystem.
   */
  @Test
  public void testGetMoon2000() {
    testToWGS84AndBack(PROJ.getMoon2000());
  }

  /**
   * Test of getNaiad2000 method, of class SolarSystem.
   */
  @Test
  public void testGetNaiad2000() {
    testToWGS84AndBack(PROJ.getNaiad2000());
  }

  /**
   * Test of getNeptune2000 method, of class SolarSystem.
   */
  @Test
  public void testGetNeptune2000() {
    testToWGS84AndBack(PROJ.getNeptune2000());
  }

  /**
   * Test of getNereid2000 method, of class SolarSystem.
   */
  @Test
  public void testGetNereid2000() {
    testToWGS84AndBack(PROJ.getNereid2000());
  }

  /**
   * Test of getOberon2000 method, of class SolarSystem.
   */
  @Test
  public void testGetOberon2000() {
    testToWGS84AndBack(PROJ.getOberon2000());
  }

  /**
   * Test of getOphelia2000 method, of class SolarSystem.
   */
  @Test
  public void testGetOphelia2000() {
    testToWGS84AndBack(PROJ.getOphelia2000());
  }

  /**
   * Test of getPan2000 method, of class SolarSystem.
   */
  @Test
  public void testGetPan2000() {
    testToWGS84AndBack(PROJ.getPan2000());
  }

  /**
   * Test of getPandora2000 method, of class SolarSystem.
   */
  @Test
  public void testGetPandora2000() {
    testToWGS84AndBack(PROJ.getPandora2000());
  }

  /**
   * Test of getPasiphae2000 method, of class SolarSystem.
   */
  @Test
  public void testGetPasiphae2000() {
    testToWGS84AndBack(PROJ.getPasiphae2000());
  }

  /**
   * Test of getPhobos2000 method, of class SolarSystem.
   */
  @Test
  public void testGetPhobos2000() {
    testToWGS84AndBack(PROJ.getPhobos2000());
  }

  /**
   * Test of getPhoebe2000 method, of class SolarSystem.
   */
  @Test
  public void testGetPhoebe2000() {
    testToWGS84AndBack(PROJ.getPhoebe2000());
  }

  /**
   * Test of getPluto2000 method, of class SolarSystem.
   */
  @Test
  public void testGetPluto2000() {
    testToWGS84AndBack(PROJ.getPluto2000());
  }

  /**
   * Test of getPortia2000 method, of class SolarSystem.
   */
  @Test
  public void testGetPortia2000() {
    testToWGS84AndBack(PROJ.getPortia2000());
  }

  /**
   * Test of getPrometheus2000 method, of class SolarSystem.
   */
  @Test
  public void testGetPrometheus2000() {
    testToWGS84AndBack(PROJ.getPrometheus2000());
  }

  /**
   * Test of getProteus2000 method, of class SolarSystem.
   */
  @Test
  public void testGetProteus2000() {
    testToWGS84AndBack(PROJ.getProteus2000());
  }

  /**
   * Test of getPuck2000 method, of class SolarSystem.
   */
  @Test
  public void testGetPuck2000() {
    testToWGS84AndBack(PROJ.getPuck2000());
  }

  /**
   * Test of getRhea2000 method, of class SolarSystem.
   */
  @Test
  public void testGetRhea2000() {
    testToWGS84AndBack(PROJ.getRhea2000());
  }

  /**
   * Test of getRosalind2000 method, of class SolarSystem.
   */
  @Test
  public void testGetRosalind2000() {
    testToWGS84AndBack(PROJ.getRosalind2000());
  }

  /**
   * Test of getSaturn2000 method, of class SolarSystem.
   */
  @Test
  public void testGetSaturn2000() {
    testToWGS84AndBack(PROJ.getSaturn2000());
  }

  /**
   * Test of getSinope2000 method, of class SolarSystem.
   */
  @Test
  public void testGetSinope2000() {
    testToWGS84AndBack(PROJ.getSinope2000());
  }

  /**
   * Test of getTelesto2000 method, of class SolarSystem.
   */
  @Test
  public void testGetTelesto2000() {
    testToWGS84AndBack(PROJ.getTelesto2000());
  }

  /**
   * Test of getTethys2000 method, of class SolarSystem.
   */
  @Test
  public void testGetTethys2000() {
    testToWGS84AndBack(PROJ.getTethys2000());
  }

  /**
   * Test of getThalassa2000 method, of class SolarSystem.
   */
  @Test
  public void testGetThalassa2000() {
    testToWGS84AndBack(PROJ.getThalassa2000());
  }

  /**
   * Test of getThebe2000 method, of class SolarSystem.
   */
  @Test
  public void testGetThebe2000() {
    testToWGS84AndBack(PROJ.getThebe2000());
  }

  /**
   * Test of getTitan2000 method, of class SolarSystem.
   */
  @Test
  public void testGetTitan2000() {
    testToWGS84AndBack(PROJ.getTitan2000());
  }

  /**
   * Test of getTitania2000 method, of class SolarSystem.
   */
  @Test
  public void testGetTitania2000() {
    testToWGS84AndBack(PROJ.getTitania2000());
  }

  /**
   * Test of getTriton2000 method, of class SolarSystem.
   */
  @Test
  public void testGetTriton2000() {
    testToWGS84AndBack(PROJ.getTriton2000());
  }

  /**
   * Test of getUmbriel2000 method, of class SolarSystem.
   */
  @Test
  public void testGetUmbriel2000() {
    testToWGS84AndBack(PROJ.getUmbriel2000());
  }

  /**
   * Test of getUranus2000 method, of class SolarSystem.
   */
  @Test
  public void testGetUranus2000() {
    testToWGS84AndBack(PROJ.getUranus2000());
  }

  /**
   * Test of getVenus1985 method, of class SolarSystem.
   */
  @Test
  public void testGetVenus1985() {
    testToWGS84AndBack(PROJ.getVenus1985());
  }

  /**
   * Test of getVenus2000 method, of class SolarSystem.
   */
  @Test
  public void testGetVenus2000() {
    testToWGS84AndBack(PROJ.getVenus2000());
  }

}
