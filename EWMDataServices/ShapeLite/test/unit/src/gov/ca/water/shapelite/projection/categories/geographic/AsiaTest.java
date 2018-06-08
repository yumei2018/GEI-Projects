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
public class AsiaTest {

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
  private static final Asia PROJ = Projections.getGeographic().getAsia();

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
   * Test of getAinelAbd1970 method, of class Asia.
   */
  @Test
  public void testGetAinelAbd1970() {
    testToWGS84AndBack(PROJ.getAinelAbd1970());
  }

  /**
   * Test of getBatavia method, of class Asia.
   */
  @Test
  public void testGetBatavia() {
    testToWGS84AndBack(PROJ.getBatavia());
  }

  /**
   * Test of getBataviaJakarta method, of class Asia.
   */
  @Test
  public void testGetBataviaJakarta() {
    testToWGS84AndBack(PROJ.getBataviaJakarta());
  }

  /**
   * Test of getBeijing1954 method, of class Asia.
   */
  @Test
  public void testGetBeijing1954() {
    testToWGS84AndBack(PROJ.getBeijing1954());
  }

  /**
   * Test of getBukitRimpah method, of class Asia.
   */
  @Test
  public void testGetBukitRimpah() {
    testToWGS84AndBack(PROJ.getBukitRimpah());
  }

  /**
   * Test of getDeirezZor method, of class Asia.
   */
  @Test
  public void testGetDeirezZor() {
    testToWGS84AndBack(PROJ.getDeirezZor());
  }

  /**
   * Test of getEuropean1950ED77 method, of class Asia.
   */
  @Test
  public void testGetEuropean1950ED77() {
    testToWGS84AndBack(PROJ.getEuropean1950ED77());
  }

  /**
   * Test of getEuropeanDatum1950 method, of class Asia.
   */
  @Test
  public void testGetEuropeanDatum1950() {
    testToWGS84AndBack(PROJ.getEuropeanDatum1950());
  }

  /**
   * Test of getEverest1830 method, of class Asia.
   */
  @Test
  public void testGetEverest1830() {
    testToWGS84AndBack(PROJ.getEverest1830());
  }

  /**
   * Test of getEverestBangladesh method, of class Asia.
   */
  @Test
  public void testGetEverestBangladesh() {
    testToWGS84AndBack(PROJ.getEverestBangladesh());
  }

  /**
   * Test of getEverestIndiaandNepal method, of class Asia.
   */
  @Test
  public void testGetEverestIndiaandNepal() {
    testToWGS84AndBack(PROJ.getEverestIndiaandNepal());
  }

  /**
   * Test of getEverestModified method, of class Asia.
   */
  @Test
  public void testGetEverestModified() {
    testToWGS84AndBack(PROJ.getEverestModified());
  }

  /**
   * Test of getEverestdef1962 method, of class Asia.
   */
  @Test
  public void testGetEverestdef1962() {
    testToWGS84AndBack(PROJ.getEverestdef1962());
  }

  /**
   * Test of getEverestdef1967 method, of class Asia.
   */
  @Test
  public void testGetEverestdef1967() {
    testToWGS84AndBack(PROJ.getEverestdef1967());
  }

  /**
   * Test of getEverestdef1975 method, of class Asia.
   */
  @Test
  public void testGetEverestdef1975() {
    testToWGS84AndBack(PROJ.getEverestdef1975());
  }

  /**
   * Test of getFD1958 method, of class Asia.
   */
  @Test
  public void testGetFD1958() {
    testToWGS84AndBack(PROJ.getFD1958());
  }

  /**
   * Test of getFahud method, of class Asia.
   */
  @Test
  public void testGetFahud() {
    testToWGS84AndBack(PROJ.getFahud());
  }

  /**
   * Test of getGandajika1970 method, of class Asia.
   */
  @Test
  public void testGetGandajika1970() {
    testToWGS84AndBack(PROJ.getGandajika1970());
  }

  /**
   * Test of getGunungSegara method, of class Asia.
   */
  @Test
  public void testGetGunungSegara() {
    testToWGS84AndBack(PROJ.getGunungSegara());
  }

  /**
   * Test of getGunungSegaraJakarta method, of class Asia.
   */
  @Test
  public void testGetGunungSegaraJakarta() {
    testToWGS84AndBack(PROJ.getGunungSegaraJakarta());
  }

  /**
   * Test of getHanoi1972 method, of class Asia.
   */
  @Test
  public void testGetHanoi1972() {
    testToWGS84AndBack(PROJ.getHanoi1972());
  }

  /**
   * Test of getHeratNorth method, of class Asia.
   */
  @Test
  public void testGetHeratNorth() {
    testToWGS84AndBack(PROJ.getHeratNorth());
  }

  /**
   * Test of getHongKong1963 method, of class Asia.
   */
  @Test
  public void testGetHongKong1963() {
    testToWGS84AndBack(PROJ.getHongKong1963());
  }

  /**
   * Test of getHongKong1980 method, of class Asia.
   */
  @Test
  public void testGetHongKong1980() {
    testToWGS84AndBack(PROJ.getHongKong1980());
  }

  /**
   * Test of getHuTzuShan method, of class Asia.
   */
  @Test
  public void testGetHuTzuShan() {
    testToWGS84AndBack(PROJ.getHuTzuShan());
  }

  /**
   * Test of getIGM1995 method, of class Asia.
   */
  @Test
  public void testGetIGM1995() {
    testToWGS84AndBack(PROJ.getIGM1995());
  }

  /**
   * Test of getIKBD1992 method, of class Asia.
   */
  @Test
  public void testGetIKBD1992() {
    testToWGS84AndBack(PROJ.getIKBD1992());
  }

  /**
   * Test of getIndian1954 method, of class Asia.
   */
  @Test
  public void testGetIndian1954() {
    testToWGS84AndBack(PROJ.getIndian1954());
  }

  /**
   * Test of getIndian1960 method, of class Asia.
   */
  @Test
  public void testGetIndian1960() {
    testToWGS84AndBack(PROJ.getIndian1960());
  }

  /**
   * Test of getIndian1975 method, of class Asia.
   */
  @Test
  public void testGetIndian1975() {
    testToWGS84AndBack(PROJ.getIndian1975());
  }

  /**
   * Test of getIndonesianDatum1974 method, of class Asia.
   */
  @Test
  public void testGetIndonesianDatum1974() {
    testToWGS84AndBack(PROJ.getIndonesianDatum1974());
  }

  /**
   * Test of getIsrael method, of class Asia.
   */
  @Test
  public void testGetIsrael() {
    testToWGS84AndBack(PROJ.getIsrael());
  }

  /**
   * Test of getJGD2000 method, of class Asia.
   */
  @Test
  public void testGetJGD2000() {
    testToWGS84AndBack(PROJ.getJGD2000());
  }

  /**
   * Test of getJordan method, of class Asia.
   */
  @Test
  public void testGetJordan() {
    testToWGS84AndBack(PROJ.getJordan());
  }

  /**
   * Test of getKalianpur1880 method, of class Asia.
   */
  @Test
  public void testGetKalianpur1880() {
    testToWGS84AndBack(PROJ.getKalianpur1880());
  }

  /**
   * Test of getKalianpur1937 method, of class Asia.
   */
  @Test
  public void testGetKalianpur1937() {
    testToWGS84AndBack(PROJ.getKalianpur1937());
  }

  /**
   * Test of getKalianpur1962 method, of class Asia.
   */
  @Test
  public void testGetKalianpur1962() {
    testToWGS84AndBack(PROJ.getKalianpur1962());
  }

  /**
   * Test of getKalianpur1975 method, of class Asia.
   */
  @Test
  public void testGetKalianpur1975() {
    testToWGS84AndBack(PROJ.getKalianpur1975());
  }

  /**
   * Test of getKandawala method, of class Asia.
   */
  @Test
  public void testGetKandawala() {
    testToWGS84AndBack(PROJ.getKandawala());
  }

  /**
   * Test of getKertau method, of class Asia.
   */
  @Test
  public void testGetKertau() {
    testToWGS84AndBack(PROJ.getKertau());
  }

  /**
   * Test of getKoreanDatum1985 method, of class Asia.
   */
  @Test
  public void testGetKoreanDatum1985() {
    testToWGS84AndBack(PROJ.getKoreanDatum1985());
  }

  /**
   * Test of getKoreanDatum1995 method, of class Asia.
   */
  @Test
  public void testGetKoreanDatum1995() {
    testToWGS84AndBack(PROJ.getKoreanDatum1995());
  }

  /**
   * Test of getKuwaitOilCompany method, of class Asia.
   */
  @Test
  public void testGetKuwaitOilCompany() {
    testToWGS84AndBack(PROJ.getKuwaitOilCompany());
  }

  /**
   * Test of getKuwaitUtility method, of class Asia.
   */
  @Test
  public void testGetKuwaitUtility() {
    testToWGS84AndBack(PROJ.getKuwaitUtility());
  }

  /**
   * Test of getLuzon1911 method, of class Asia.
   */
  @Test
  public void testGetLuzon1911() {
    testToWGS84AndBack(PROJ.getLuzon1911());
  }

  /**
   * Test of getMakassar method, of class Asia.
   */
  @Test
  public void testGetMakassar() {
    testToWGS84AndBack(PROJ.getMakassar());
  }

  /**
   * Test of getMakassarJakarta method, of class Asia.
   */
  @Test
  public void testGetMakassarJakarta() {
    testToWGS84AndBack(PROJ.getMakassarJakarta());
  }

  /**
   * Test of getNahrwan1967 method, of class Asia.
   */
  @Test
  public void testGetNahrwan1967() {
    testToWGS84AndBack(PROJ.getNahrwan1967());
  }

  /**
   * Test of getNationalGeodeticNetworkKuwait method, of class Asia.
   */
  @Test
  public void testGetNationalGeodeticNetworkKuwait() {
    testToWGS84AndBack(PROJ.getNationalGeodeticNetworkKuwait());
  }

  /**
   * Test of getObservatorioMeteorologico1965 method, of class Asia.
   */
  @Test
  public void testGetObservatorioMeteorologico1965() {
    testToWGS84AndBack(PROJ.getObservatorioMeteorologico1965());
  }

  /**
   * Test of getOman method, of class Asia.
   */
  @Test
  public void testGetOman() {
    testToWGS84AndBack(PROJ.getOman());
  }

  /**
   * Test of getPadang1884 method, of class Asia.
   */
  @Test
  public void testGetPadang1884() {
    testToWGS84AndBack(PROJ.getPadang1884());
  }

  /**
   * Test of getPadang1884Jakarta method, of class Asia.
   */
  @Test
  public void testGetPadang1884Jakarta() {
    testToWGS84AndBack(PROJ.getPadang1884Jakarta());
  }

  /**
   * Test of getPalestine1923 method, of class Asia.
   */
  @Test
  public void testGetPalestine1923() {
    testToWGS84AndBack(PROJ.getPalestine1923());
  }

  /**
   * Test of getPulkovo1942 method, of class Asia.
   */
  @Test
  public void testGetPulkovo1942() {
    testToWGS84AndBack(PROJ.getPulkovo1942());
  }

  /**
   * Test of getPulkovo1995 method, of class Asia.
   */
  @Test
  public void testGetPulkovo1995() {
    testToWGS84AndBack(PROJ.getPulkovo1995());
  }

  /**
   * Test of getQND1995 method, of class Asia.
   */
  @Test
  public void testGetQND1995() {
    testToWGS84AndBack(PROJ.getQND1995());
  }

  /**
   * Test of getQatar method, of class Asia.
   */
  @Test
  public void testGetQatar() {
    testToWGS84AndBack(PROJ.getQatar());
  }

  /**
   * Test of getQatar1948 method, of class Asia.
   */
  @Test
  public void testGetQatar1948() {
    testToWGS84AndBack(PROJ.getQatar1948());
  }

  /**
   * Test of getRassadiran method, of class Asia.
   */
  @Test
  public void testGetRassadiran() {
    testToWGS84AndBack(PROJ.getRassadiran());
  }

  /**
   * Test of getSamboja method, of class Asia.
   */
  @Test
  public void testGetSamboja() {
    testToWGS84AndBack(PROJ.getSamboja());
  }

  /**
   * Test of getSegora method, of class Asia.
   */
  @Test
  public void testGetSegora() {
    testToWGS84AndBack(PROJ.getSegora());
  }

  /**
   * Test of getSerindung method, of class Asia.
   */
  @Test
  public void testGetSerindung() {
    testToWGS84AndBack(PROJ.getSerindung());
  }

  /**
   * Test of getSouthAsiaSingapore method, of class Asia.
   */
  @Test
  public void testGetSouthAsiaSingapore() {
    testToWGS84AndBack(PROJ.getSouthAsiaSingapore());
  }

  /**
   * Test of getTimbalai1948 method, of class Asia.
   */
  @Test
  public void testGetTimbalai1948() {
    testToWGS84AndBack(PROJ.getTimbalai1948());
  }

  /**
   * Test of getTokyo method, of class Asia.
   */
  @Test
  public void testGetTokyo() {
    testToWGS84AndBack(PROJ.getTokyo());
  }

  /**
   * Test of getTrucialCoast1948 method, of class Asia.
   */
  @Test
  public void testGetTrucialCoast1948() {
    testToWGS84AndBack(PROJ.getTrucialCoast1948());
  }

  /**
   * Test of getXian1980 method, of class Asia.
   */
  @Test
  public void testGetXian1980() {
    testToWGS84AndBack(PROJ.getXian1980());
  }

}
