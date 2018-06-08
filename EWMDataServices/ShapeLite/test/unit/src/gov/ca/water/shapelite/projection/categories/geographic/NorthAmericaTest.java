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
public class NorthAmericaTest {

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
  private static final NorthAmerica PROJ = Projections.getGeographic().getNorthAmerica();

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
   * Test of getATS1977 method, of class NorthAmerica.
   */
  @Test
  public void testGetATS1977() {
    testToWGS84AndBack(PROJ.getATS1977());
  }

  /**
   * Test of getAlaskanIslands method, of class NorthAmerica.
   */
  @Test
  public void testGetAlaskanIslands() {
    testToWGS84AndBack(PROJ.getAlaskanIslands());
  }

  /**
   * Test of getAmericanSamoa1962 method, of class NorthAmerica.
   */
  @Test
  public void testGetAmericanSamoa1962() {
    testToWGS84AndBack(PROJ.getAmericanSamoa1962());
  }

  /**
   * Test of getAmmassalik1958 method, of class NorthAmerica.
   */
  @Test
  public void testGetAmmassalik1958() {
    testToWGS84AndBack(PROJ.getAmmassalik1958());
  }

  /**
   * Test of getBarbados method, of class NorthAmerica.
   */
  @Test
  public void testGetBarbados() {
    testToWGS84AndBack(PROJ.getBarbados());
  }

  /**
   * Test of getBermuda1957 method, of class NorthAmerica.
   */
  @Test
  public void testGetBermuda1957() {
    testToWGS84AndBack(PROJ.getBermuda1957());
  }

  /**
   * Test of getBermuda2000 method, of class NorthAmerica.
   */
  @Test
  public void testGetBermuda2000() {
    testToWGS84AndBack(PROJ.getBermuda2000());
  }

  /**
   * Test of getCapeCanaveral method, of class NorthAmerica.
   */
  @Test
  public void testGetCapeCanaveral() {
    testToWGS84AndBack(PROJ.getCapeCanaveral());
  }

  /**
   * Test of getGuam1963 method, of class NorthAmerica.
   */
  @Test
  public void testGetGuam1963() {
    testToWGS84AndBack(PROJ.getGuam1963());
  }

  /**
   * Test of getHelle1954 method, of class NorthAmerica.
   */
  @Test
  public void testGetHelle1954() {
    testToWGS84AndBack(PROJ.getHelle1954());
  }

  /**
   * Test of getJamaica1875 method, of class NorthAmerica.
   */
  @Test
  public void testGetJamaica1875() {
    testToWGS84AndBack(PROJ.getJamaica1875());
  }

  /**
   * Test of getJamaica1969 method, of class NorthAmerica.
   */
  @Test
  public void testGetJamaica1969() {
    testToWGS84AndBack(PROJ.getJamaica1969());
  }

  /**
   * Test of getNAD1927CGQ77 method, of class NorthAmerica.
   */
  @Test
  public void testGetNAD1927CGQ77() {
    testToWGS84AndBack(PROJ.getNAD1927CGQ77());
  }

  /**
   * Test of getNAD1927Definition1976 method, of class NorthAmerica.
   */
  @Test
  public void testGetNAD1927Definition1976() {
    testToWGS84AndBack(PROJ.getNAD1927Definition1976());
  }

  /**
   * Test of getNADMichigan method, of class NorthAmerica.
   */
  @Test
  public void testGetNADMichigan() {
    testToWGS84AndBack(PROJ.getNADMichigan());
  }

  /**
   * Test of getNorthAmerican1983CSRS98 method, of class NorthAmerica.
   */
  @Test
  public void testGetNorthAmerican1983CSRS98() {
    testToWGS84AndBack(PROJ.getNorthAmerican1983CSRS98());
  }

  /**
   * Test of getNorthAmerican1983HARN method, of class NorthAmerica.
   */
  @Test
  public void testGetNorthAmerican1983HARN() {
    testToWGS84AndBack(PROJ.getNorthAmerican1983HARN());
  }

  /**
   * Test of getNorthAmericanDatum1927 method, of class NorthAmerica.
   */
  @Test
  public void testGetNorthAmericanDatum1927() {
    testToWGS84AndBack(PROJ.getNorthAmericanDatum1927());
  }

  /**
   * Test of getNorthAmericanDatum1983 method, of class NorthAmerica.
   */
  @Test
  public void testGetNorthAmericanDatum1983() {
    testToWGS84AndBack(PROJ.getNorthAmericanDatum1983());
  }

  /**
   * Test of getOldHawaiian method, of class NorthAmerica.
   */
  @Test
  public void testGetOldHawaiian() {
    testToWGS84AndBack(PROJ.getOldHawaiian());
  }

  /**
   * Test of getPuertoRico method, of class NorthAmerica.
   */
  @Test
  public void testGetPuertoRico() {
    testToWGS84AndBack(PROJ.getPuertoRico());
  }

  /**
   * Test of getQornoq method, of class NorthAmerica.
   */
  @Test
  public void testGetQornoq() {
    testToWGS84AndBack(PROJ.getQornoq());
  }

  /**
   * Test of getQornoq1927 method, of class NorthAmerica.
   */
  @Test
  public void testGetQornoq1927() {
    testToWGS84AndBack(PROJ.getQornoq1927());
  }

  /**
   * Test of getScoresbysund1952 method, of class NorthAmerica.
   */
  @Test
  public void testGetScoresbysund1952() {
    testToWGS84AndBack(PROJ.getScoresbysund1952());
  }

  /**
   * Test of getStGeorgeIsland method, of class NorthAmerica.
   */
  @Test
  public void testGetStGeorgeIsland() {
    testToWGS84AndBack(PROJ.getStGeorgeIsland());
  }

  /**
   * Test of getStLawrenceIsland method, of class NorthAmerica.
   */
  @Test
  public void testGetStLawrenceIsland() {
    testToWGS84AndBack(PROJ.getStLawrenceIsland());
  }

  /**
   * Test of getStPaulIsland method, of class NorthAmerica.
   */
  @Test
  public void testGetStPaulIsland() {
    testToWGS84AndBack(PROJ.getStPaulIsland());
  }

}
