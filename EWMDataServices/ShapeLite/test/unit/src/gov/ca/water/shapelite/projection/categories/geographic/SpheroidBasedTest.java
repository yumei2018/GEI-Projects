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
public class SpheroidBasedTest {

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
  private static final SpheroidBased PROJ = Projections.getGeographic().getSpheroidBased();

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
   * Test of getAiry1830 method, of class SpheroidBased.
   */
  @Test
  public void testGetAiry1830() {
    testToWGS84AndBack(PROJ.getAiry1830());
  }

  /**
   * Test of getAirymodified method, of class SpheroidBased.
   */
  @Test
  public void testGetAirymodified() {
    testToWGS84AndBack(PROJ.getAirymodified());
  }

  /**
   * Test of getAustralianNational method, of class SpheroidBased.
   */
  @Test
  public void testGetAustralianNational() {
    testToWGS84AndBack(PROJ.getAustralianNational());
  }

  /**
   * Test of getAuthalicsphere method, of class SpheroidBased.
   */
  @Test
  public void testGetAuthalicsphere() {
    testToWGS84AndBack(PROJ.getAuthalicsphere());
  }

  /**
   * Test of getAuthalicsphereARCINFO method, of class SpheroidBased.
   */
  @Test
  public void testGetAuthalicsphereARCINFO() {
    testToWGS84AndBack(PROJ.getAuthalicsphereARCINFO());
  }

  /**
   * Test of getAverageTerrestrialSystem1977 method, of class SpheroidBased.
   */
  @Test
  public void testGetAverageTerrestrialSystem1977() {
    testToWGS84AndBack(PROJ.getAverageTerrestrialSystem1977());
  }

  /**
   * Test of getBessel1841 method, of class SpheroidBased.
   */
  @Test
  public void testGetBessel1841() {
    testToWGS84AndBack(PROJ.getBessel1841());
  }

  /**
   * Test of getBesselNamibia method, of class SpheroidBased.
   */
  @Test
  public void testGetBesselNamibia() {
    testToWGS84AndBack(PROJ.getBesselNamibia());
  }

  /**
   * Test of getBesselmodified method, of class SpheroidBased.
   */
  @Test
  public void testGetBesselmodified() {
    testToWGS84AndBack(PROJ.getBesselmodified());
  }

  /**
   * Test of getClarke1858 method, of class SpheroidBased.
   */
  @Test
  public void testGetClarke1858() {
    testToWGS84AndBack(PROJ.getClarke1858());
  }

  /**
   * Test of getClarke1866 method, of class SpheroidBased.
   */
  @Test
  public void testGetClarke1866() {
    testToWGS84AndBack(PROJ.getClarke1866());
  }

  /**
   * Test of getClarke1866Michigan method, of class SpheroidBased.
   */
  @Test
  public void testGetClarke1866Michigan() {
    testToWGS84AndBack(PROJ.getClarke1866Michigan());
  }

  /**
   * Test of getClarke1880 method, of class SpheroidBased.
   */
  @Test
  public void testGetClarke1880() {
    testToWGS84AndBack(PROJ.getClarke1880());
  }

  /**
   * Test of getClarke1880Arc method, of class SpheroidBased.
   */
  @Test
  public void testGetClarke1880Arc() {
    testToWGS84AndBack(PROJ.getClarke1880Arc());
  }

  /**
   * Test of getClarke1880Benoit method, of class SpheroidBased.
   */
  @Test
  public void testGetClarke1880Benoit() {
    testToWGS84AndBack(PROJ.getClarke1880Benoit());
  }

  /**
   * Test of getClarke1880IGN method, of class SpheroidBased.
   */
  @Test
  public void testGetClarke1880IGN() {
    testToWGS84AndBack(PROJ.getClarke1880IGN());
  }

  /**
   * Test of getClarke1880RGS method, of class SpheroidBased.
   */
  @Test
  public void testGetClarke1880RGS() {
    testToWGS84AndBack(PROJ.getClarke1880RGS());
  }

  /**
   * Test of getClarke1880SGA method, of class SpheroidBased.
   */
  @Test
  public void testGetClarke1880SGA() {
    testToWGS84AndBack(PROJ.getClarke1880SGA());
  }

  /**
   * Test of getEverest1830 method, of class SpheroidBased.
   */
  @Test
  public void testGetEverest1830() {
    testToWGS84AndBack(PROJ.getEverest1830());
  }

  /**
   * Test of getEverestdefinition1967 method, of class SpheroidBased.
   */
  @Test
  public void testGetEverestdefinition1967() {
    testToWGS84AndBack(PROJ.getEverestdefinition1967());
  }

  /**
   * Test of getEverestdefinition1975 method, of class SpheroidBased.
   */
  @Test
  public void testGetEverestdefinition1975() {
    testToWGS84AndBack(PROJ.getEverestdefinition1975());
  }

  /**
   * Test of getEverestmodified method, of class SpheroidBased.
   */
  @Test
  public void testGetEverestmodified() {
    testToWGS84AndBack(PROJ.getEverestmodified());
  }

  /**
   * Test of getEverestmodified1969 method, of class SpheroidBased.
   */
  @Test
  public void testGetEverestmodified1969() {
    testToWGS84AndBack(PROJ.getEverestmodified1969());
  }

  /**
   * Test of getFischer1960 method, of class SpheroidBased.
   */
  @Test
  public void testGetFischer1960() {
    testToWGS84AndBack(PROJ.getFischer1960());
  }

  /**
   * Test of getFischer1968 method, of class SpheroidBased.
   */
  @Test
  public void testGetFischer1968() {
    testToWGS84AndBack(PROJ.getFischer1968());
  }

  /**
   * Test of getFischermodified method, of class SpheroidBased.
   */
  @Test
  public void testGetFischermodified() {
    testToWGS84AndBack(PROJ.getFischermodified());
  }

  /**
   * Test of getGRS1967 method, of class SpheroidBased.
   */
  @Test
  public void testGetGRS1967() {
    testToWGS84AndBack(PROJ.getGRS1967());
  }

  /**
   * Test of getGRS1980 method, of class SpheroidBased.
   */
  @Test
  public void testGetGRS1980() {
    testToWGS84AndBack(PROJ.getGRS1980());
  }

  /**
   * Test of getHelmert1906 method, of class SpheroidBased.
   */
  @Test
  public void testGetHelmert1906() {
    testToWGS84AndBack(PROJ.getHelmert1906());
  }

  /**
   * Test of getHough1960 method, of class SpheroidBased.
   */
  @Test
  public void testGetHough1960() {
    testToWGS84AndBack(PROJ.getHough1960());
  }

  /**
   * Test of getIndonesianNational method, of class SpheroidBased.
   */
  @Test
  public void testGetIndonesianNational() {
    testToWGS84AndBack(PROJ.getIndonesianNational());
  }

  /**
   * Test of getInternational1924 method, of class SpheroidBased.
   */
  @Test
  public void testGetInternational1924() {
    testToWGS84AndBack(PROJ.getInternational1924());
  }

  /**
   * Test of getInternational1967 method, of class SpheroidBased.
   */
  @Test
  public void testGetInternational1967() {
    testToWGS84AndBack(PROJ.getInternational1967());
  }

  /**
   * Test of getKrasovsky1940 method, of class SpheroidBased.
   */
  @Test
  public void testGetKrasovsky1940() {
    testToWGS84AndBack(PROJ.getKrasovsky1940());
  }

  /**
   * Test of getOSU1986geoidalmodel method, of class SpheroidBased.
   */
  @Test
  public void testGetOSU1986geoidalmodel() {
    testToWGS84AndBack(PROJ.getOSU1986geoidalmodel());
  }

  /**
   * Test of getOSU1991geoidalmodel method, of class SpheroidBased.
   */
  @Test
  public void testGetOSU1991geoidalmodel() {
    testToWGS84AndBack(PROJ.getOSU1991geoidalmodel());
  }

  /**
   * Test of getPlessis1817 method, of class SpheroidBased.
   */
  @Test
  public void testGetPlessis1817() {
    testToWGS84AndBack(PROJ.getPlessis1817());
  }

  /**
   * Test of getSphereEMEP method, of class SpheroidBased.
   */
  @Test
  public void testGetSphereEMEP() {
    testToWGS84AndBack(PROJ.getSphereEMEP());
  }

  /**
   * Test of getStruve1860 method, of class SpheroidBased.
   */
  @Test
  public void testGetStruve1860() {
    testToWGS84AndBack(PROJ.getStruve1860());
  }

  /**
   * Test of getTransitpreciseephemeris method, of class SpheroidBased.
   */
  @Test
  public void testGetTransitpreciseephemeris() {
    testToWGS84AndBack(PROJ.getTransitpreciseephemeris());
  }

  /**
   * Test of getWGS1966 method, of class SpheroidBased.
   */
  @Test
  public void testGetWGS1966() {
    testToWGS84AndBack(PROJ.getWGS1966());
  }

  /**
   * Test of getWalbeck method, of class SpheroidBased.
   */
  @Test
  public void testGetWalbeck() {
    testToWGS84AndBack(PROJ.getWalbeck());
  }

  /**
   * Test of getWarOffice method, of class SpheroidBased.
   */
  @Test
  public void testGetWarOffice() {
    testToWGS84AndBack(PROJ.getWarOffice());
  }

}
