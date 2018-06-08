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
public class AfricaTest {

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
  private static final Africa PROJ = Projections.getGeographic().getAfrica();

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
   * Test of getAbidjan1987 method, of class Africa.
   */
  @Test
  public void testGetAbidjan1987() {
    testToWGS84AndBack(PROJ.getAbidjan1987());
  }

  /**
   * Test of getAccra method, of class Africa.
   */
  @Test
  public void testGetAccra() {
    testToWGS84AndBack(PROJ.getAccra());
  }

  /**
   * Test of getAdindan method, of class Africa.
   */
  @Test
  public void testGetAdindan() {
    testToWGS84AndBack(PROJ.getAdindan());
  }

  /**
   * Test of getAfgooye method, of class Africa.
   */
  @Test
  public void testGetAfgooye() {
    testToWGS84AndBack(PROJ.getAfgooye());
  }

  /**
   * Test of getAgadez method, of class Africa.
   */
  @Test
  public void testGetAgadez() {
    testToWGS84AndBack(PROJ.getAgadez());
  }

  /**
   * Test of getAinelAbd1970 method, of class Africa.
   */
  @Test
  public void testGetAinelAbd1970() {
    testToWGS84AndBack(PROJ.getAinelAbd1970());
  }

  /**
   * Test of getArc1950 method, of class Africa.
   */
  @Test
  public void testGetArc1950() {
    testToWGS84AndBack(PROJ.getArc1950());
  }

  /**
   * Test of getArc1960 method, of class Africa.
   */
  @Test
  public void testGetArc1960() {
    testToWGS84AndBack(PROJ.getArc1960());
  }

  /**
   * Test of getAyabelleLighthouse method, of class Africa.
   */
  @Test
  public void testGetAyabelleLighthouse() {
    testToWGS84AndBack(PROJ.getAyabelleLighthouse());
  }

  /**
   * Test of getBeduaram method, of class Africa.
   */
  @Test
  public void testGetBeduaram() {
    testToWGS84AndBack(PROJ.getBeduaram());
  }

  /**
   * Test of getBissau method, of class Africa.
   */
  @Test
  public void testGetBissau() {
    testToWGS84AndBack(PROJ.getBissau());
  }

  /**
   * Test of getCamacupa method, of class Africa.
   */
  @Test
  public void testGetCamacupa() {
    testToWGS84AndBack(PROJ.getCamacupa());
  }

  /**
   * Test of getCape method, of class Africa.
   */
  @Test
  public void testGetCape() {
    testToWGS84AndBack(PROJ.getCape());
  }

  /**
   * Test of getCarthage method, of class Africa.
   */
  @Test
  public void testGetCarthage() {
    testToWGS84AndBack(PROJ.getCarthage());
  }

  /**
   * Test of getCarthageParis method, of class Africa.
   */
  @Test
  public void testGetCarthageParis() {
    testToWGS84AndBack(PROJ.getCarthageParis());
  }

  /**
   * Test of getCarthagedegrees method, of class Africa.
   */
  @Test
  public void testGetCarthagedegrees() {
    testToWGS84AndBack(PROJ.getCarthagedegrees());
  }

  /**
   * Test of getConakry1905 method, of class Africa.
   */
  @Test
  public void testGetConakry1905() {
    testToWGS84AndBack(PROJ.getConakry1905());
  }

  /**
   * Test of getCotedIvoire method, of class Africa.
   */
  @Test
  public void testGetCotedIvoire() {
    testToWGS84AndBack(PROJ.getCotedIvoire());
  }

  /**
   * Test of getDabola method, of class Africa.
   */
  @Test
  public void testGetDabola() {
    testToWGS84AndBack(PROJ.getDabola());
  }

  /**
   * Test of getDouala method, of class Africa.
   */
  @Test
  public void testGetDouala() {
    testToWGS84AndBack(PROJ.getDouala());
  }

  /**
   * Test of getDouala1948 method, of class Africa.
   */
  @Test
  public void testGetDouala1948() {
    testToWGS84AndBack(PROJ.getDouala1948());
  }

  /**
   * Test of getEgypt1907 method, of class Africa.
   */
  @Test
  public void testGetEgypt1907() {
    testToWGS84AndBack(PROJ.getEgypt1907());
  }

  /**
   * Test of getEgypt1930 method, of class Africa.
   */
  @Test
  public void testGetEgypt1930() {
    testToWGS84AndBack(PROJ.getEgypt1930());
  }

  /**
   * Test of getEuropeanDatum1950 method, of class Africa.
   */
  @Test
  public void testGetEuropeanDatum1950() {
    testToWGS84AndBack(PROJ.getEuropeanDatum1950());
  }

  /**
   * Test of getEuropeanLibyanDatum1979 method, of class Africa.
   */
  @Test
  public void testGetEuropeanLibyanDatum1979() {
    testToWGS84AndBack(PROJ.getEuropeanLibyanDatum1979());
  }

  /**
   * Test of getGaroua method, of class Africa.
   */
  @Test
  public void testGetGaroua() {
    testToWGS84AndBack(PROJ.getGaroua());
  }

  /**
   * Test of getHartebeesthoek1994 method, of class Africa.
   */
  @Test
  public void testGetHartebeesthoek1994() {
    testToWGS84AndBack(PROJ.getHartebeesthoek1994());
  }

  /**
   * Test of getKousseri method, of class Africa.
   */
  @Test
  public void testGetKousseri() {
    testToWGS84AndBack(PROJ.getKousseri());
  }

  /**
   * Test of getKuwaitOilCompany method, of class Africa.
   */
  @Test
  public void testGetKuwaitOilCompany() {
    testToWGS84AndBack(PROJ.getKuwaitOilCompany());
  }

  /**
   * Test of getKuwaitUtility method, of class Africa.
   */
  @Test
  public void testGetKuwaitUtility() {
    testToWGS84AndBack(PROJ.getKuwaitUtility());
  }

  /**
   * Test of getLeigon method, of class Africa.
   */
  @Test
  public void testGetLeigon() {
    testToWGS84AndBack(PROJ.getLeigon());
  }

  /**
   * Test of getLiberia1964 method, of class Africa.
   */
  @Test
  public void testGetLiberia1964() {
    testToWGS84AndBack(PROJ.getLiberia1964());
  }

  /**
   * Test of getLocodjo1965 method, of class Africa.
   */
  @Test
  public void testGetLocodjo1965() {
    testToWGS84AndBack(PROJ.getLocodjo1965());
  }

  /**
   * Test of getLome method, of class Africa.
   */
  @Test
  public void testGetLome() {
    testToWGS84AndBack(PROJ.getLome());
  }

  /**
   * Test of getMadzansua method, of class Africa.
   */
  @Test
  public void testGetMadzansua() {
    testToWGS84AndBack(PROJ.getMadzansua());
  }

  /**
   * Test of getMahe1971 method, of class Africa.
   */
  @Test
  public void testGetMahe1971() {
    testToWGS84AndBack(PROJ.getMahe1971());
  }

  /**
   * Test of getMalongo1987 method, of class Africa.
   */
  @Test
  public void testGetMalongo1987() {
    testToWGS84AndBack(PROJ.getMalongo1987());
  }

  /**
   * Test of getManoca method, of class Africa.
   */
  @Test
  public void testGetManoca() {
    testToWGS84AndBack(PROJ.getManoca());
  }

  /**
   * Test of getManoca1962 method, of class Africa.
   */
  @Test
  public void testGetManoca1962() {
    testToWGS84AndBack(PROJ.getManoca1962());
  }

  /**
   * Test of getMassawa method, of class Africa.
   */
  @Test
  public void testGetMassawa() {
    testToWGS84AndBack(PROJ.getMassawa());
  }

  /**
   * Test of getMerchich method, of class Africa.
   */
  @Test
  public void testGetMerchich() {
    testToWGS84AndBack(PROJ.getMerchich());
  }

  /**
   * Test of getMerchichdegrees method, of class Africa.
   */
  @Test
  public void testGetMerchichdegrees() {
    testToWGS84AndBack(PROJ.getMerchichdegrees());
  }

  /**
   * Test of getMhast method, of class Africa.
   */
  @Test
  public void testGetMhast() {
    testToWGS84AndBack(PROJ.getMhast());
  }

  /**
   * Test of getMinna method, of class Africa.
   */
  @Test
  public void testGetMinna() {
    testToWGS84AndBack(PROJ.getMinna());
  }

  /**
   * Test of getMoznet method, of class Africa.
   */
  @Test
  public void testGetMoznet() {
    testToWGS84AndBack(PROJ.getMoznet());
  }

  /**
   * Test of getMporaloko method, of class Africa.
   */
  @Test
  public void testGetMporaloko() {
    testToWGS84AndBack(PROJ.getMporaloko());
  }

  /**
   * Test of getNahrwan1967 method, of class Africa.
   */
  @Test
  public void testGetNahrwan1967() {
    testToWGS84AndBack(PROJ.getNahrwan1967());
  }

  /**
   * Test of getNationalGeodeticNetworkKuwait method, of class Africa.
   */
  @Test
  public void testGetNationalGeodeticNetworkKuwait() {
    testToWGS84AndBack(PROJ.getNationalGeodeticNetworkKuwait());
  }

  /**
   * Test of getNordSahara1959 method, of class Africa.
   */
  @Test
  public void testGetNordSahara1959() {
    testToWGS84AndBack(PROJ.getNordSahara1959());
  }

  /**
   * Test of getNordSahara1959Paris method, of class Africa.
   */
  @Test
  public void testGetNordSahara1959Paris() {
    testToWGS84AndBack(PROJ.getNordSahara1959Paris());
  }

  /**
   * Test of getObservatario method, of class Africa.
   */
  @Test
  public void testGetObservatario() {
    testToWGS84AndBack(PROJ.getObservatario());
  }

  /**
   * Test of getOman method, of class Africa.
   */
  @Test
  public void testGetOman() {
    testToWGS84AndBack(PROJ.getOman());
  }

  /**
   * Test of getPDO1993 method, of class Africa.
   */
  @Test
  public void testGetPDO1993() {
    testToWGS84AndBack(PROJ.getPDO1993());
  }

  /**
   * Test of getPalestine1923 method, of class Africa.
   */
  @Test
  public void testGetPalestine1923() {
    testToWGS84AndBack(PROJ.getPalestine1923());
  }

  /**
   * Test of getPoint58 method, of class Africa.
   */
  @Test
  public void testGetPoint58() {
    testToWGS84AndBack(PROJ.getPoint58());
  }

  /**
   * Test of getPointeNoire method, of class Africa.
   */
  @Test
  public void testGetPointeNoire() {
    testToWGS84AndBack(PROJ.getPointeNoire());
  }

  /**
   * Test of getQatar method, of class Africa.
   */
  @Test
  public void testGetQatar() {
    testToWGS84AndBack(PROJ.getQatar());
  }

  /**
   * Test of getQatar1948 method, of class Africa.
   */
  @Test
  public void testGetQatar1948() {
    testToWGS84AndBack(PROJ.getQatar1948());
  }

  /**
   * Test of getSchwarzeck method, of class Africa.
   */
  @Test
  public void testGetSchwarzeck() {
    testToWGS84AndBack(PROJ.getSchwarzeck());
  }

  /**
   * Test of getSierraLeone1924 method, of class Africa.
   */
  @Test
  public void testGetSierraLeone1924() {
    testToWGS84AndBack(PROJ.getSierraLeone1924());
  }

  /**
   * Test of getSierraLeone1960 method, of class Africa.
   */
  @Test
  public void testGetSierraLeone1960() {
    testToWGS84AndBack(PROJ.getSierraLeone1960());
  }

  /**
   * Test of getSierraLeone1968 method, of class Africa.
   */
  @Test
  public void testGetSierraLeone1968() {
    testToWGS84AndBack(PROJ.getSierraLeone1968());
  }

  /**
   * Test of getSouthYemen method, of class Africa.
   */
  @Test
  public void testGetSouthYemen() {
    testToWGS84AndBack(PROJ.getSouthYemen());
  }

  /**
   * Test of getSudan method, of class Africa.
   */
  @Test
  public void testGetSudan() {
    testToWGS84AndBack(PROJ.getSudan());
  }

  /**
   * Test of getTananarive1925 method, of class Africa.
   */
  @Test
  public void testGetTananarive1925() {
    testToWGS84AndBack(PROJ.getTananarive1925());
  }

  /**
   * Test of getTananarive1925Paris method, of class Africa.
   */
  @Test
  public void testGetTananarive1925Paris() {
    testToWGS84AndBack(PROJ.getTananarive1925Paris());
  }

  /**
   * Test of getTete method, of class Africa.
   */
  @Test
  public void testGetTete() {
    testToWGS84AndBack(PROJ.getTete());
  }

  /**
   * Test of getTrucialCoast1948 method, of class Africa.
   */
  @Test
  public void testGetTrucialCoast1948() {
    testToWGS84AndBack(PROJ.getTrucialCoast1948());
  }

  /**
   * Test of getVoirol1875 method, of class Africa.
   */
  @Test
  public void testGetVoirol1875() {
    testToWGS84AndBack(PROJ.getVoirol1875());
  }

  /**
   * Test of getVoirol1875Paris method, of class Africa.
   */
  @Test
  public void testGetVoirol1875Paris() {
    testToWGS84AndBack(PROJ.getVoirol1875Paris());
  }

  /**
   * Test of getVoirol1875degrees method, of class Africa.
   */
  @Test
  public void testGetVoirol1875degrees() {
    testToWGS84AndBack(PROJ.getVoirol1875degrees());
  }

  /**
   * Test of getVoirolUnifie1960 method, of class Africa.
   */
  @Test
  public void testGetVoirolUnifie1960() {
    testToWGS84AndBack(PROJ.getVoirolUnifie1960());
  }

  /**
   * Test of getVoirolUnifie1960Paris method, of class Africa.
   */
  @Test
  public void testGetVoirolUnifie1960Paris() {
    testToWGS84AndBack(PROJ.getVoirolUnifie1960Paris());
  }

  /**
   * Test of getVoirolUnifie1960degrees method, of class Africa.
   */
  @Test
  public void testGetVoirolUnifie1960degrees() {
    testToWGS84AndBack(PROJ.getVoirolUnifie1960degrees());
  }

  /**
   * Test of getYemenNGN1996 method, of class Africa.
   */
  @Test
  public void testGetYemenNGN1996() {
    testToWGS84AndBack(PROJ.getYemenNGN1996());
  }

  /**
   * Test of getYoff method, of class Africa.
   */
  @Test
  public void testGetYoff() {
    testToWGS84AndBack(PROJ.getYoff());
  }

}
