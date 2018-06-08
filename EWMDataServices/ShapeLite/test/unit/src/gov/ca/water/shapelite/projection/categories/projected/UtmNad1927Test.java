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
public class UtmNad1927Test {

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
  private static final UtmNad1927 PROJ = Projections.getProjected().getUtmNad1927();

  /**
   * Tests converting values to WGS84 and back.
   *
   * @param projection The ProjectionInfo to test.
   */
  public void testToWGS84AndBack(ProjectionInfo projection) {

    try {
      Coord original = new CoordXY(500000, 5000000);
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
   * Test of getNAD1927UTMZone10N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone10N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone10N());
  }

  /**
   * Test of getNAD1927UTMZone11N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone11N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone11N());
  }

  /**
   * Test of getNAD1927UTMZone12N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone12N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone12N());
  }

  /**
   * Test of getNAD1927UTMZone13N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone13N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone13N());
  }

  /**
   * Test of getNAD1927UTMZone14N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone14N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone14N());
  }

  /**
   * Test of getNAD1927UTMZone15N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone15N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone15N());
  }

  /**
   * Test of getNAD1927UTMZone16N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone16N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone16N());
  }

  /**
   * Test of getNAD1927UTMZone17N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone17N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone17N());
  }

  /**
   * Test of getNAD1927UTMZone18N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone18N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone18N());
  }

  /**
   * Test of getNAD1927UTMZone19N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone19N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone19N());
  }

  /**
   * Test of getNAD1927UTMZone1N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone1N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone1N());
  }

  /**
   * Test of getNAD1927UTMZone20N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone20N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone20N());
  }

  /**
   * Test of getNAD1927UTMZone21N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone21N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone21N());
  }

  /**
   * Test of getNAD1927UTMZone22N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone22N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone22N());
  }

  /**
   * Test of getNAD1927UTMZone2N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone2N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone2N());
  }

  /**
   * Test of getNAD1927UTMZone3N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone3N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone3N());
  }

  /**
   * Test of getNAD1927UTMZone4N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone4N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone4N());
  }

  /**
   * Test of getNAD1927UTMZone59N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone59N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone59N());
  }

  /**
   * Test of getNAD1927UTMZone5N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone5N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone5N());
  }

  /**
   * Test of getNAD1927UTMZone60N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone60N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone60N());
  }

  /**
   * Test of getNAD1927UTMZone6N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone6N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone6N());
  }

  /**
   * Test of getNAD1927UTMZone7N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone7N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone7N());
  }

  /**
   * Test of getNAD1927UTMZone8N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone8N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone8N());
  }

  /**
   * Test of getNAD1927UTMZone9N method, of class UtmNad1927.
   */
  @Test
  public void testNAD1927UTMZone9N() {
    testToWGS84AndBack(PROJ.getNAD1927UTMZone9N());
  }

}
