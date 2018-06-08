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
public class PolarTest {

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
  private static final Polar PROJ = Projections.getProjected().getPolar();

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
   * Test of getNorthPoleAzimuthalEquidistant method, of class Polar.
   */
  @Test
  public void testNorthPoleAzimuthalEquidistant() {
    testToWGS84AndBack(PROJ.getNorthPoleAzimuthalEquidistant());
  }

  /**
   * Test of getNorthPoleGnomonic method, of class Polar.
   */
  @Test
  public void testNorthPoleGnomonic() {
    testToWGS84AndBack(PROJ.getNorthPoleGnomonic());
  }

  /**
   * Test of getNorthPoleLambertAzimuthalEqualArea method, of class
   * Polar.
   */
  @Test
  public void testNorthPoleLambertAzimuthalEqualArea() {
    testToWGS84AndBack(PROJ.getNorthPoleLambertAzimuthalEqualArea());
  }

  /**
   * Test of getNorthPoleOrthographic method, of class Polar.
   */
  @Test
  public void testNorthPoleOrthographic() {
    testToWGS84AndBack(PROJ.getNorthPoleOrthographic());
  }

  /**
   * Test of getNorthPoleStereographic method, of class Polar.
   */
  @Test
  public void testNorthPoleStereographic() {
    testToWGS84AndBack(PROJ.getNorthPoleStereographic());
  }

  /**
   * Test of getPerroud1950TerreAdeliePolarStereographic method, of class
   * Polar.
   */
  @Test
  public void testPerroud1950TerreAdeliePolarStereographic() {
    testToWGS84AndBack(PROJ.getPerroud1950TerreAdeliePolarStereographic());
  }

  /**
   * Test of getPetrels1972TerreAdeliePolarStereographic method, of class
   * Polar.
   */
  @Test
  public void testPetrels1972TerreAdeliePolarStereographic() {
    testToWGS84AndBack(PROJ.getPetrels1972TerreAdeliePolarStereographic());
  }

  /**
   * Test of getSouthPoleAzimuthalEquidistant method, of class Polar.
   */
  @Test
  public void testSouthPoleAzimuthalEquidistant() {
    testToWGS84AndBack(PROJ.getSouthPoleAzimuthalEquidistant());
  }

  /**
   * Test of getSouthPoleGnomonic method, of class Polar.
   */
  @Test
  public void testSouthPoleGnomonic() {
    testToWGS84AndBack(PROJ.getSouthPoleGnomonic());
  }

  /**
   * Test of getSouthPoleLambertAzimuthalEqualArea method, of class
   * Polar.
   */
  @Test
  public void testSouthPoleLambertAzimuthalEqualArea() {
    testToWGS84AndBack(PROJ.getSouthPoleLambertAzimuthalEqualArea());
  }

  /**
   * Test of getSouthPoleOrthographic method, of class Polar.
   */
  @Test
  public void testSouthPoleOrthographic() {
    testToWGS84AndBack(PROJ.getSouthPoleOrthographic());
  }

  /**
   * Test of getSouthPoleStereographic method, of class Polar.
   */
  @Test
  public void testSouthPoleStereographic() {
    testToWGS84AndBack(PROJ.getSouthPoleStereographic());
  }

  /**
   * Test of getUPSNorth method, of class Polar.
   */
  @Test
  public void testUPSNorth() {
    testToWGS84AndBack(PROJ.getUPSNorth());
  }

  /**
   * Test of getUPSSouth method, of class Polar.
   */
  @Test
  public void testUPSSouth() {
    testToWGS84AndBack(PROJ.getUPSSouth());
  }

  /**
   * Test of getWGS1984AntarcticPolarStereographic method, of class
   * Polar.
   */
  @Test
  public void testWGS1984AntarcticPolarStereographic() {
    testToWGS84AndBack(PROJ.getWGS1984AntarcticPolarStereographic());
  }

  /**
   * Test of getWGS1984AustralianAntarcticLambert method, of class
   * Polar.
   */
  @Test
  public void testWGS1984AustralianAntarcticLambert() {
    testToWGS84AndBack(PROJ.getWGS1984AustralianAntarcticLambert());
  }

  /**
   * Test of getWGS1984AustralianAntarcticPolarStereographic method, of class
   * Polar.
   */
  @Test
  public void testWGS1984AustralianAntarcticPolarStereographic() {
    testToWGS84AndBack(PROJ.getWGS1984AustralianAntarcticPolarStereographic());
  }

}
