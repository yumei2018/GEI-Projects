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
public class GaussKrugerOtherTest {

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
  private static final GaussKrugerOther PROJ = Projections.getProjected().getGaussKrugerOther();

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
   * Test of getAlbanian1987GKZone4 method, of class GaussKrugerOther.
   */
  @Test
  public void testAlbanian1987GKZone4() {
    testToWGS84AndBack(PROJ.getAlbanian1987GKZone4());
  }

  /**
   * Test of getED19503DegreeGKZone10 method, of class GaussKrugerOther.
   */
  @Test
  public void testED19503DegreeGKZone10() {
    testToWGS84AndBack(PROJ.getED19503DegreeGKZone10());
  }

  /**
   * Test of getED19503DegreeGKZone11 method, of class GaussKrugerOther.
   */
  @Test
  public void testED19503DegreeGKZone11() {
    testToWGS84AndBack(PROJ.getED19503DegreeGKZone11());
  }

  /**
   * Test of getED19503DegreeGKZone12 method, of class GaussKrugerOther.
   */
  @Test
  public void testED19503DegreeGKZone12() {
    testToWGS84AndBack(PROJ.getED19503DegreeGKZone12());
  }

  /**
   * Test of getED19503DegreeGKZone13 method, of class GaussKrugerOther.
   */
  @Test
  public void testED19503DegreeGKZone13() {
    testToWGS84AndBack(PROJ.getED19503DegreeGKZone13());
  }

  /**
   * Test of getED19503DegreeGKZone14 method, of class GaussKrugerOther.
   */
  @Test
  public void testED19503DegreeGKZone14() {
    testToWGS84AndBack(PROJ.getED19503DegreeGKZone14());
  }

  /**
   * Test of getED19503DegreeGKZone15 method, of class GaussKrugerOther.
   */
  @Test
  public void testED19503DegreeGKZone15() {
    testToWGS84AndBack(PROJ.getED19503DegreeGKZone15());
  }

  /**
   * Test of getED19503DegreeGKZone9 method, of class GaussKrugerOther.
   */
  @Test
  public void testED19503DegreeGKZone9() {
    testToWGS84AndBack(PROJ.getED19503DegreeGKZone9());
  }

  /**
   * Test of getHanoi1972GKZone18 method, of class GaussKrugerOther.
   */
  @Test
  public void testHanoi1972GKZone18() {
    testToWGS84AndBack(PROJ.getHanoi1972GKZone18());
  }

  /**
   * Test of getHanoi1972GKZone19 method, of class GaussKrugerOther.
   */
  @Test
  public void testHanoi1972GKZone19() {
    testToWGS84AndBack(PROJ.getHanoi1972GKZone19());
  }

  /**
   * Test of getPulkovo1942Adj19833DegreeGKZone3 method, of class GaussKrugerOther.
   */
  @Test
  public void testPulkovo1942Adj19833DegreeGKZone3() {
    testToWGS84AndBack(PROJ.getPulkovo1942Adj19833DegreeGKZone3());
  }

  /**
   * Test of getPulkovo1942Adj19833DegreeGKZone4 method, of class GaussKrugerOther.
   */
  @Test
  public void testPulkovo1942Adj19833DegreeGKZone4() {
    testToWGS84AndBack(PROJ.getPulkovo1942Adj19833DegreeGKZone4());
  }

  /**
   * Test of getPulkovo1942Adj19833DegreeGKZone5 method, of class GaussKrugerOther.
   */
  @Test
  public void testPulkovo1942Adj19833DegreeGKZone5() {
    testToWGS84AndBack(PROJ.getPulkovo1942Adj19833DegreeGKZone5());
  }

  /**
   * Test of getSouthYemenGKZone8 method, of class GaussKrugerOther.
   */
  @Test
  public void testSouthYemenGKZone8() {
    testToWGS84AndBack(PROJ.getSouthYemenGKZone8());
  }

  /**
   * Test of getSouthYemenGKZone9 method, of class GaussKrugerOther.
   */
  @Test
  public void testSouthYemenGKZone9() {
    testToWGS84AndBack(PROJ.getSouthYemenGKZone9());
  }

}
