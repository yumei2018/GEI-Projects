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
public class NorthAmericaTest {

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
  private static final NorthAmerica PROJ = Projections.getProjected().getNorthAmerica();

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
   * Test of getAlaskaAlbersEqualAreaConic method, of class NorthAmerica.
   */
  @Test
  public void testAlaskaAlbersEqualAreaConic() {
    testToWGS84AndBack(PROJ.getAlaskaAlbersEqualAreaConic());
  }

  /**
   * Test of getCanadaAlbersEqualAreaConic method, of class NorthAmerica.
   */
  @Test
  public void testCanadaAlbersEqualAreaConic() {
    testToWGS84AndBack(PROJ.getCanadaAlbersEqualAreaConic());
  }

  /**
   * Test of getCanadaLambertConformalConic method, of class NorthAmerica.
   */
  @Test
  public void testCanadaLambertConformalConic() {
    testToWGS84AndBack(PROJ.getCanadaLambertConformalConic());
  }

  /**
   * Test of getHawaiiAlbersEqualAreaConic method, of class NorthAmerica.
   */
  @Test
  public void testHawaiiAlbersEqualAreaConic() {
    testToWGS84AndBack(PROJ.getHawaiiAlbersEqualAreaConic());
  }

  /**
   * Test of getNorthAmericaAlbersEqualAreaConic method, of class NorthAmerica.
   */
  @Test
  public void testNorthAmericaAlbersEqualAreaConic() {
    testToWGS84AndBack(PROJ.getNorthAmericaAlbersEqualAreaConic());
  }

  /**
   * Test of getNorthAmericaEquidistantConic method, of class NorthAmerica.
   */
  @Test
  public void testNorthAmericaEquidistantConic() {
    testToWGS84AndBack(PROJ.getNorthAmericaEquidistantConic());
  }

  /**
   * Test of getNorthAmericaLambertConformalConic method, of class
   * NorthAmerica.
   */
  @Test
  public void testNorthAmericaLambertConformalConic() {
    testToWGS84AndBack(PROJ.getNorthAmericaLambertConformalConic());
  }

  /**
   * Test of getUSAContiguousAlbersEqualAreaConic method, of class
   * NorthAmerica.
   */
  @Test
  public void testUSAContiguousAlbersEqualAreaConic() {
    testToWGS84AndBack(PROJ.getUSAContiguousAlbersEqualAreaConic());
  }

  /**
   * Test of getUSAContiguousAlbersEqualAreaConicUSGS method, of class
   * NorthAmerica.
   */
  @Test
  public void testUSAContiguousAlbersEqualAreaConicUSGS() {
    testToWGS84AndBack(PROJ.getUSAContiguousAlbersEqualAreaConicUSGS());
  }

  /**
   * Test of getUSAContiguousEquidistantConic method, of class NorthAmerica.
   */
  @Test
  public void testUSAContiguousEquidistantConic() {
    testToWGS84AndBack(PROJ.getUSAContiguousEquidistantConic());
  }

  /**
   * Test of getUSAContiguousLambertConformalConic method, of class
   * NorthAmerica.
   */
  @Test
  public void testUSAContiguousLambertConformalConic() {
    testToWGS84AndBack(PROJ.getUSAContiguousLambertConformalConic());
  }

}
