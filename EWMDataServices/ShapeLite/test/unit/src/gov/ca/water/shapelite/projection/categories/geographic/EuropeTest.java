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
public class EuropeTest {

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
  private static final Europe PROJ = Projections.getGeographic().getEurope();

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
   * Test of getATFParis method, of class Europe.
   */
  @Test
  public void testGetATFParis() {
    testToWGS84AndBack(PROJ.getATFParis());
  }

  /**
   * Test of getAlbanian1987 method, of class Europe.
   */
  @Test
  public void testGetAlbanian1987() {
    testToWGS84AndBack(PROJ.getAlbanian1987());
  }

  /**
   * Test of getBelge1950Brussels method, of class Europe.
   */
  @Test
  public void testGetBelge1950Brussels() {
    testToWGS84AndBack(PROJ.getBelge1950Brussels());
  }

  /**
   * Test of getBelge1972 method, of class Europe.
   */
  @Test
  public void testGetBelge1972() {
    testToWGS84AndBack(PROJ.getBelge1972());
  }

  /**
   * Test of getBern1898 method, of class Europe.
   */
  @Test
  public void testGetBern1898() {
    testToWGS84AndBack(PROJ.getBern1898());
  }

  /**
   * Test of getBern1898Bern method, of class Europe.
   */
  @Test
  public void testGetBern1898Bern() {
    testToWGS84AndBack(PROJ.getBern1898Bern());
  }

  /**
   * Test of getBern1938 method, of class Europe.
   */
  @Test
  public void testGetBern1938() {
    testToWGS84AndBack(PROJ.getBern1938());
  }

  /**
   * Test of getCH1903 method, of class Europe.
   */
  @Test
  public void testGetCH1903() {
    testToWGS84AndBack(PROJ.getCH1903());
  }

  /**
   * Test of getDatum73 method, of class Europe.
   */
  @Test
  public void testGetDatum73() {
    testToWGS84AndBack(PROJ.getDatum73());
  }

  /**
   * Test of getDatumLisboaBessel method, of class Europe.
   */
  @Test
  public void testGetDatumLisboaBessel() {
    testToWGS84AndBack(PROJ.getDatumLisboaBessel());
  }

  /**
   * Test of getDatumLisboaHayford method, of class Europe.
   */
  @Test
  public void testGetDatumLisboaHayford() {
    testToWGS84AndBack(PROJ.getDatumLisboaHayford());
  }

  /**
   * Test of getDealulPiscului1933Romania method, of class Europe.
   */
  @Test
  public void testGetDealulPiscului1933Romania() {
    testToWGS84AndBack(PROJ.getDealulPiscului1933Romania());
  }

  /**
   * Test of getDealulPiscului1970Romania method, of class Europe.
   */
  @Test
  public void testGetDealulPiscului1970Romania() {
    testToWGS84AndBack(PROJ.getDealulPiscului1970Romania());
  }

  /**
   * Test of getDeutscheHauptdreiecksnetz method, of class Europe.
   */
  @Test
  public void testGetDeutscheHauptdreiecksnetz() {
    testToWGS84AndBack(PROJ.getDeutscheHauptdreiecksnetz());
  }

  /**
   * Test of getDutchRD method, of class Europe.
   */
  @Test
  public void testGetDutchRD() {
    testToWGS84AndBack(PROJ.getDutchRD());
  }

  /**
   * Test of getETRF1989 method, of class Europe.
   */
  @Test
  public void testGetETRF1989() {
    testToWGS84AndBack(PROJ.getETRF1989());
  }

  /**
   * Test of getETRS1989 method, of class Europe.
   */
  @Test
  public void testGetETRS1989() {
    testToWGS84AndBack(PROJ.getETRS1989());
  }

  /**
   * Test of getEUREFFIN method, of class Europe.
   */
  @Test
  public void testGetEUREFFIN() {
    testToWGS84AndBack(PROJ.getEUREFFIN());
  }

  /**
   * Test of getEstonia1937 method, of class Europe.
   */
  @Test
  public void testGetEstonia1937() {
    testToWGS84AndBack(PROJ.getEstonia1937());
  }

  /**
   * Test of getEstonia1992 method, of class Europe.
   */
  @Test
  public void testGetEstonia1992() {
    testToWGS84AndBack(PROJ.getEstonia1992());
  }

  /**
   * Test of getEstonia1997 method, of class Europe.
   */
  @Test
  public void testGetEstonia1997() {
    testToWGS84AndBack(PROJ.getEstonia1997());
  }

  /**
   * Test of getEuropean1979 method, of class Europe.
   */
  @Test
  public void testGetEuropean1979() {
    testToWGS84AndBack(PROJ.getEuropean1979());
  }

  /**
   * Test of getEuropeanDatum1950 method, of class Europe.
   */
  @Test
  public void testGetEuropeanDatum1950() {
    testToWGS84AndBack(PROJ.getEuropeanDatum1950());
  }

  /**
   * Test of getEuropeanDatum1987 method, of class Europe.
   */
  @Test
  public void testGetEuropeanDatum1987() {
    testToWGS84AndBack(PROJ.getEuropeanDatum1987());
  }

  /**
   * Test of getGreek method, of class Europe.
   */
  @Test
  public void testGetGreek() {
    testToWGS84AndBack(PROJ.getGreek());
  }

  /**
   * Test of getGreekAthens method, of class Europe.
   */
  @Test
  public void testGetGreekAthens() {
    testToWGS84AndBack(PROJ.getGreekAthens());
  }

  /**
   * Test of getGreekGeodeticRefSystem1987 method, of class Europe.
   */
  @Test
  public void testGetGreekGeodeticRefSystem1987() {
    testToWGS84AndBack(PROJ.getGreekGeodeticRefSystem1987());
  }

  /**
   * Test of getHermannskogel method, of class Europe.
   */
  @Test
  public void testGetHermannskogel() {
    testToWGS84AndBack(PROJ.getHermannskogel());
  }

  /**
   * Test of getHjorsey1955 method, of class Europe.
   */
  @Test
  public void testGetHjorsey1955() {
    testToWGS84AndBack(PROJ.getHjorsey1955());
  }

  /**
   * Test of getHungarianDatum1972 method, of class Europe.
   */
  @Test
  public void testGetHungarianDatum1972() {
    testToWGS84AndBack(PROJ.getHungarianDatum1972());
  }

  /**
   * Test of getIRENET95 method, of class Europe.
   */
  @Test
  public void testGetIRENET95() {
    testToWGS84AndBack(PROJ.getIRENET95());
  }

  /**
   * Test of getISN1993 method, of class Europe.
   */
  @Test
  public void testGetISN1993() {
    testToWGS84AndBack(PROJ.getISN1993());
  }

  /**
   * Test of getKartastokoordinaattijarjestelma method, of class Europe.
   */
  @Test
  public void testGetKartastokoordinaattijarjestelma() {
    testToWGS84AndBack(PROJ.getKartastokoordinaattijarjestelma());
  }

  /**
   * Test of getLKS1992 method, of class Europe.
   */
  @Test
  public void testGetLKS1992() {
    testToWGS84AndBack(PROJ.getLKS1992());
  }

  /**
   * Test of getLKS1994 method, of class Europe.
   */
  @Test
  public void testGetLKS1994() {
    testToWGS84AndBack(PROJ.getLKS1994());
  }

  /**
   * Test of getLisbon method, of class Europe.
   */
  @Test
  public void testGetLisbon() {
    testToWGS84AndBack(PROJ.getLisbon());
  }

  /**
   * Test of getLisbon1890 method, of class Europe.
   */
  @Test
  public void testGetLisbon1890() {
    testToWGS84AndBack(PROJ.getLisbon1890());
  }

  /**
   * Test of getLisbon1890Lisbon method, of class Europe.
   */
  @Test
  public void testGetLisbon1890Lisbon() {
    testToWGS84AndBack(PROJ.getLisbon1890Lisbon());
  }

  /**
   * Test of getLisbonLisbon method, of class Europe.
   */
  @Test
  public void testGetLisbonLisbon() {
    testToWGS84AndBack(PROJ.getLisbonLisbon());
  }

  /**
   * Test of getLuxembourg1930 method, of class Europe.
   */
  @Test
  public void testGetLuxembourg1930() {
    testToWGS84AndBack(PROJ.getLuxembourg1930());
  }

  /**
   * Test of getMGIFerro method, of class Europe.
   */
  @Test
  public void testGetMGIFerro() {
    testToWGS84AndBack(PROJ.getMGIFerro());
  }

  /**
   * Test of getMadrid1870Madrid method, of class Europe.
   */
  @Test
  public void testGetMadrid1870Madrid() {
    testToWGS84AndBack(PROJ.getMadrid1870Madrid());
  }

  /**
   * Test of getMilitarGeographischeInstitut method, of class Europe.
   */
  @Test
  public void testGetMilitarGeographischeInstitut() {
    testToWGS84AndBack(PROJ.getMilitarGeographischeInstitut());
  }

  /**
   * Test of getMonteMario method, of class Europe.
   */
  @Test
  public void testGetMonteMario() {
    testToWGS84AndBack(PROJ.getMonteMario());
  }

  /**
   * Test of getMonteMarioRome method, of class Europe.
   */
  @Test
  public void testGetMonteMarioRome() {
    testToWGS84AndBack(PROJ.getMonteMarioRome());
  }

  /**
   * Test of getNGO1948 method, of class Europe.
   */
  @Test
  public void testGetNGO1948() {
    testToWGS84AndBack(PROJ.getNGO1948());
  }

  /**
   * Test of getNGO1948Oslo method, of class Europe.
   */
  @Test
  public void testGetNGO1948Oslo() {
    testToWGS84AndBack(PROJ.getNGO1948Oslo());
  }

  /**
   * Test of getNTFParis method, of class Europe.
   */
  @Test
  public void testGetNTFParis() {
    testToWGS84AndBack(PROJ.getNTFParis());
  }

  /**
   * Test of getNorddeGuerreParis method, of class Europe.
   */
  @Test
  public void testGetNorddeGuerreParis() {
    testToWGS84AndBack(PROJ.getNorddeGuerreParis());
  }

  /**
   * Test of getNouvelleTriangulationFrancaise method, of class Europe.
   */
  @Test
  public void testGetNouvelleTriangulationFrancaise() {
    testToWGS84AndBack(PROJ.getNouvelleTriangulationFrancaise());
  }

  /**
   * Test of getOSGB1936 method, of class Europe.
   */
  @Test
  public void testGetOSGB1936() {
    testToWGS84AndBack(PROJ.getOSGB1936());
  }

  /**
   * Test of getOSGB1970SN method, of class Europe.
   */
  @Test
  public void testGetOSGB1970SN() {
    testToWGS84AndBack(PROJ.getOSGB1970SN());
  }

  /**
   * Test of getOSNI1952 method, of class Europe.
   */
  @Test
  public void testGetOSNI1952() {
    testToWGS84AndBack(PROJ.getOSNI1952());
  }

  /**
   * Test of getOSSN1980 method, of class Europe.
   */
  @Test
  public void testGetOSSN1980() {
    testToWGS84AndBack(PROJ.getOSSN1980());
  }

  /**
   * Test of getPulkovo1942 method, of class Europe.
   */
  @Test
  public void testGetPulkovo1942() {
    testToWGS84AndBack(PROJ.getPulkovo1942());
  }

  /**
   * Test of getPulkovo1942Adj1958 method, of class Europe.
   */
  @Test
  public void testGetPulkovo1942Adj1958() {
    testToWGS84AndBack(PROJ.getPulkovo1942Adj1958());
  }

  /**
   * Test of getPulkovo1942Adj1983 method, of class Europe.
   */
  @Test
  public void testGetPulkovo1942Adj1983() {
    testToWGS84AndBack(PROJ.getPulkovo1942Adj1983());
  }

  /**
   * Test of getPulkovo1995 method, of class Europe.
   */
  @Test
  public void testGetPulkovo1995() {
    testToWGS84AndBack(PROJ.getPulkovo1995());
  }

  /**
   * Test of getQornoq method, of class Europe.
   */
  @Test
  public void testGetQornoq() {
    testToWGS84AndBack(PROJ.getQornoq());
  }

  /**
   * Test of getRGF1993 method, of class Europe.
   */
  @Test
  public void testGetRGF1993() {
    testToWGS84AndBack(PROJ.getRGF1993());
  }

  /**
   * Test of getRT1990 method, of class Europe.
   */
  @Test
  public void testGetRT1990() {
    testToWGS84AndBack(PROJ.getRT1990());
  }

  /**
   * Test of getRT38 method, of class Europe.
   */
  @Test
  public void testGetRT38() {
    testToWGS84AndBack(PROJ.getRT38());
  }

  /**
   * Test of getRT38Stockholm method, of class Europe.
   */
  @Test
  public void testGetRT38Stockholm() {
    testToWGS84AndBack(PROJ.getRT38Stockholm());
  }

  /**
   * Test of getReseauNationalBelge1950 method, of class Europe.
   */
  @Test
  public void testGetReseauNationalBelge1950() {
    testToWGS84AndBack(PROJ.getReseauNationalBelge1950());
  }

  /**
   * Test of getReseauNationalBelge1972 method, of class Europe.
   */
  @Test
  public void testGetReseauNationalBelge1972() {
    testToWGS84AndBack(PROJ.getReseauNationalBelge1972());
  }

  /**
   * Test of getReykjavik1900 method, of class Europe.
   */
  @Test
  public void testGetReykjavik1900() {
    testToWGS84AndBack(PROJ.getReykjavik1900());
  }

  /**
   * Test of getRoma1940 method, of class Europe.
   */
  @Test
  public void testGetRoma1940() {
    testToWGS84AndBack(PROJ.getRoma1940());
  }

  /**
   * Test of getS42Hungary method, of class Europe.
   */
  @Test
  public void testGetS42Hungary() {
    testToWGS84AndBack(PROJ.getS42Hungary());
  }

  /**
   * Test of getSJTSK method, of class Europe.
   */
  @Test
  public void testGetSJTSK() {
    testToWGS84AndBack(PROJ.getSJTSK());
  }

  /**
   * Test of getSWEREF99 method, of class Europe.
   */
  @Test
  public void testGetSWEREF99() {
    testToWGS84AndBack(PROJ.getSWEREF99());
  }

  /**
   * Test of getSwissTRF1995 method, of class Europe.
   */
  @Test
  public void testGetSwissTRF1995() {
    testToWGS84AndBack(PROJ.getSwissTRF1995());
  }

  /**
   * Test of getTM65 method, of class Europe.
   */
  @Test
  public void testGetTM65() {
    testToWGS84AndBack(PROJ.getTM65());
  }

  /**
   * Test of getTM75 method, of class Europe.
   */
  @Test
  public void testGetTM75() {
    testToWGS84AndBack(PROJ.getTM75());
  }

}
