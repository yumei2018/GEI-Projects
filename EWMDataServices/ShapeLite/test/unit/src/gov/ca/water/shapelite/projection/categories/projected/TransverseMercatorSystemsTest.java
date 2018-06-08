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
public class TransverseMercatorSystemsTest {

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
  private static final TransverseMercatorSystems PROJ
      = Projections.getProjected().getTransverseMercatorSystems();

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
   * Test of getWGS1984lo16 method, of class TransverseMercatorSystems.
   */
  @Test
  public void testWGS1984lo16() {
    testToWGS84AndBack(PROJ.getWGS1984lo16());
  }

  /**
   * Test of getWGS1984lo17 method, of class TransverseMercatorSystems.
   */
  @Test
  public void testWGS1984lo17() {
    testToWGS84AndBack(PROJ.getWGS1984lo17());
  }

  /**
   * Test of getWGS1984lo18 method, of class TransverseMercatorSystems.
   */
  @Test
  public void testWGS1984lo18() {
    testToWGS84AndBack(PROJ.getWGS1984lo18());
  }

  /**
   * Test of getWGS1984lo19 method, of class TransverseMercatorSystems.
   */
  @Test
  public void testWGS1984lo19() {
    testToWGS84AndBack(PROJ.getWGS1984lo19());
  }

  /**
   * Test of getWGS1984lo20 method, of class TransverseMercatorSystems.
   */
  @Test
  public void testWGS1984lo20() {
    testToWGS84AndBack(PROJ.getWGS1984lo20());
  }

  /**
   * Test of getWGS1984lo21 method, of class TransverseMercatorSystems.
   */
  @Test
  public void testWGS1984lo21() {
    testToWGS84AndBack(PROJ.getWGS1984lo21());
  }

  /**
   * Test of getWGS1984lo22 method, of class TransverseMercatorSystems.
   */
  @Test
  public void testWGS1984lo22() {
    testToWGS84AndBack(PROJ.getWGS1984lo22());
  }

  /**
   * Test of getWGS1984lo23 method, of class TransverseMercatorSystems.
   */
  @Test
  public void testWGS1984lo23() {
    testToWGS84AndBack(PROJ.getWGS1984lo23());
  }

  /**
   * Test of getWGS1984lo24 method, of class TransverseMercatorSystems.
   */
  @Test
  public void testWGS1984lo24() {
    testToWGS84AndBack(PROJ.getWGS1984lo24());
  }

  /**
   * Test of getWGS1984lo25 method, of class TransverseMercatorSystems.
   */
  @Test
  public void testWGS1984lo25() {
    testToWGS84AndBack(PROJ.getWGS1984lo25());
  }

  /**
   * Test of getWGS1984lo26 method, of class TransverseMercatorSystems.
   */
  @Test
  public void testWGS1984lo26() {
    testToWGS84AndBack(PROJ.getWGS1984lo26());
  }

  /**
   * Test of getWGS1984lo27 method, of class TransverseMercatorSystems.
   */
  @Test
  public void testWGS1984lo27() {
    testToWGS84AndBack(PROJ.getWGS1984lo27());
  }

  /**
   * Test of getWGS1984lo28 method, of class TransverseMercatorSystems.
   */
  @Test
  public void testWGS1984lo28() {
    testToWGS84AndBack(PROJ.getWGS1984lo28());
  }

  /**
   * Test of getWGS1984lo29 method, of class TransverseMercatorSystems.
   */
  @Test
  public void testWGS1984lo29() {
    testToWGS84AndBack(PROJ.getWGS1984lo29());
  }

  /**
   * Test of getWGS1984lo30 method, of class TransverseMercatorSystems.
   */
  @Test
  public void testWGS1984lo30() {
    testToWGS84AndBack(PROJ.getWGS1984lo30());
  }

  /**
   * Test of getWGS1984lo31 method, of class TransverseMercatorSystems.
   */
  @Test
  public void testWGS1984lo31() {
    testToWGS84AndBack(PROJ.getWGS1984lo31());
  }

  /**
   * Test of getWGS1984lo32 method, of class TransverseMercatorSystems.
   */
  @Test
  public void testWGS1984lo32() {
    testToWGS84AndBack(PROJ.getWGS1984lo32());
  }

  /**
   * Test of getWGS1984lo33 method, of class TransverseMercatorSystems.
   */
  @Test
  public void testWGS1984lo33() {
    testToWGS84AndBack(PROJ.getWGS1984lo33());
  }

}
