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
public class SouthAmericaTest {

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
  private static final SouthAmerica PROJ = Projections.getGeographic().getSouthAmerica();

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
   * Test of getAratu method, of class SouthAmerica.
   */
  @Test
  public void testGetAratu() {
    testToWGS84AndBack(PROJ.getAratu());
  }

  /**
   * Test of getBogota method, of class SouthAmerica.
   */
  @Test
  public void testGetBogota() {
    testToWGS84AndBack(PROJ.getBogota());
  }

  /**
   * Test of getBogotaBogota method, of class SouthAmerica.
   */
  @Test
  public void testGetBogotaBogota() {
    testToWGS84AndBack(PROJ.getBogotaBogota());
  }

  /**
   * Test of getCampoInchauspe method, of class SouthAmerica.
   */
  @Test
  public void testGetCampoInchauspe() {
    testToWGS84AndBack(PROJ.getCampoInchauspe());
  }

  /**
   * Test of getChosMalal1914 method, of class SouthAmerica.
   */
  @Test
  public void testGetChosMalal1914() {
    testToWGS84AndBack(PROJ.getChosMalal1914());
  }

  /**
   * Test of getChua method, of class SouthAmerica.
   */
  @Test
  public void testGetChua() {
    testToWGS84AndBack(PROJ.getChua());
  }

  /**
   * Test of getCorregoAlegre method, of class SouthAmerica.
   */
  @Test
  public void testGetCorregoAlegre() {
    testToWGS84AndBack(PROJ.getCorregoAlegre());
  }

  /**
   * Test of getGuyaneFrancaise method, of class SouthAmerica.
   */
  @Test
  public void testGetGuyaneFrancaise() {
    testToWGS84AndBack(PROJ.getGuyaneFrancaise());
  }

  /**
   * Test of getHitoXVIII1963 method, of class SouthAmerica.
   */
  @Test
  public void testGetHitoXVIII1963() {
    testToWGS84AndBack(PROJ.getHitoXVIII1963());
  }

  /**
   * Test of getLaCanoa method, of class SouthAmerica.
   */
  @Test
  public void testGetLaCanoa() {
    testToWGS84AndBack(PROJ.getLaCanoa());
  }

  /**
   * Test of getLake method, of class SouthAmerica.
   */
  @Test
  public void testGetLake() {
    testToWGS84AndBack(PROJ.getLake());
  }

  /**
   * Test of getLomaQuintana method, of class SouthAmerica.
   */
  @Test
  public void testGetLomaQuintana() {
    testToWGS84AndBack(PROJ.getLomaQuintana());
  }

  /**
   * Test of getMountDillon method, of class SouthAmerica.
   */
  @Test
  public void testGetMountDillon() {
    testToWGS84AndBack(PROJ.getMountDillon());
  }

  /**
   * Test of getNaparima1955 method, of class SouthAmerica.
   */
  @Test
  public void testGetNaparima1955() {
    testToWGS84AndBack(PROJ.getNaparima1955());
  }

  /**
   * Test of getNaparima1972 method, of class SouthAmerica.
   */
  @Test
  public void testGetNaparima1972() {
    testToWGS84AndBack(PROJ.getNaparima1972());
  }

  /**
   * Test of getPOSGAR method, of class SouthAmerica.
   */
  @Test
  public void testGetPOSGAR() {
    testToWGS84AndBack(PROJ.getPOSGAR());
  }

  /**
   * Test of getPOSGAR1998 method, of class SouthAmerica.
   */
  @Test
  public void testGetPOSGAR1998() {
    testToWGS84AndBack(PROJ.getPOSGAR1998());
  }

  /**
   * Test of getPampadelCastillo method, of class SouthAmerica.
   */
  @Test
  public void testGetPampadelCastillo() {
    testToWGS84AndBack(PROJ.getPampadelCastillo());
  }

  /**
   * Test of getProvisionalSouthAmer method, of class SouthAmerica.
   */
  @Test
  public void testGetProvisionalSouthAmer() {
    testToWGS84AndBack(PROJ.getProvisionalSouthAmer());
  }

  /**
   * Test of getREGVEN method, of class SouthAmerica.
   */
  @Test
  public void testGetREGVEN() {
    testToWGS84AndBack(PROJ.getREGVEN());
  }

  /**
   * Test of getSIRGAS method, of class SouthAmerica.
   */
  @Test
  public void testGetSIRGAS() {
    testToWGS84AndBack(PROJ.getSIRGAS());
  }

  /**
   * Test of getSapperHill1943 method, of class SouthAmerica.
   */
  @Test
  public void testGetSapperHill1943() {
    testToWGS84AndBack(PROJ.getSapperHill1943());
  }

  /**
   * Test of getSouthAmericanDatum1969 method, of class SouthAmerica.
   */
  @Test
  public void testGetSouthAmericanDatum1969() {
    testToWGS84AndBack(PROJ.getSouthAmericanDatum1969());
  }

  /**
   * Test of getTrinidad1903 method, of class SouthAmerica.
   */
  @Test
  public void testGetTrinidad1903() {
    testToWGS84AndBack(PROJ.getTrinidad1903());
  }

  /**
   * Test of getYacare method, of class SouthAmerica.
   */
  @Test
  public void testGetYacare() {
    testToWGS84AndBack(PROJ.getYacare());
  }

  /**
   * Test of getZanderij method, of class SouthAmerica.
   */
  @Test
  public void testGetZanderij() {
    testToWGS84AndBack(PROJ.getZanderij());
  }

}
