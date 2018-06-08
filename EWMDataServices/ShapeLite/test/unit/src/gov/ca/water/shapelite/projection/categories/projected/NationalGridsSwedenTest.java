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
public class NationalGridsSwedenTest {

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
  private static final NationalGridsSweden PROJ = Projections.getProjected().getNationalGridsSweden();

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
   * Test of getRT380gon method, of class NationalGridsSweden.
   */
  @Test
  public void testRT380gon() {
    testToWGS84AndBack(PROJ.getRT380gon());
  }

  /**
   * Test of getRT3825gonO method, of class NationalGridsSweden.
   */
  @Test
  public void testRT3825gonO() {
    testToWGS84AndBack(PROJ.getRT3825gonO());
  }

  /**
   * Test of getRT3825gonV method, of class NationalGridsSweden.
   */
  @Test
  public void testRT3825gonV() {
    testToWGS84AndBack(PROJ.getRT3825gonV());
  }

  /**
   * Test of getRT385gonO method, of class NationalGridsSweden.
   */
  @Test
  public void testRT385gonO() {
    testToWGS84AndBack(PROJ.getRT385gonO());
  }

  /**
   * Test of getRT385gonV method, of class NationalGridsSweden.
   */
  @Test
  public void testRT385gonV() {
    testToWGS84AndBack(PROJ.getRT385gonV());
  }

  /**
   * Test of getRT3875gonV method, of class NationalGridsSweden.
   */
  @Test
  public void testRT3875gonV() {
    testToWGS84AndBack(PROJ.getRT3875gonV());
  }

  /**
   * Test of getRT900gon method, of class NationalGridsSweden.
   */
  @Test
  public void testRT900gon() {
    testToWGS84AndBack(PROJ.getRT900gon());
  }

  /**
   * Test of getRT9025gonO method, of class NationalGridsSweden.
   */
  @Test
  public void testRT9025gonO() {
    testToWGS84AndBack(PROJ.getRT9025gonO());
  }

  /**
   * Test of getRT9025gonV method, of class NationalGridsSweden.
   */
  @Test
  public void testRT9025gonV() {
    testToWGS84AndBack(PROJ.getRT9025gonV());
  }

  /**
   * Test of getRT905gonO method, of class NationalGridsSweden.
   */
  @Test
  public void testRT905gonO() {
    testToWGS84AndBack(PROJ.getRT905gonO());
  }

  /**
   * Test of getRT905gonV method, of class NationalGridsSweden.
   */
  @Test
  public void testRT905gonV() {
    testToWGS84AndBack(PROJ.getRT905gonV());
  }

  /**
   * Test of getRT9075gonV method, of class NationalGridsSweden.
   */
  @Test
  public void testRT9075gonV() {
    testToWGS84AndBack(PROJ.getRT9075gonV());
  }

  /**
   * Test of getSWEREF991200 method, of class NationalGridsSweden.
   */
  @Test
  public void testSWEREF991200() {
    testToWGS84AndBack(PROJ.getSWEREF991200());
  }

  /**
   * Test of getSWEREF991330 method, of class NationalGridsSweden.
   */
  @Test
  public void testSWEREF991330() {
    testToWGS84AndBack(PROJ.getSWEREF991330());
  }

  /**
   * Test of getSWEREF991415 method, of class NationalGridsSweden.
   */
  @Test
  public void testSWEREF991415() {
    testToWGS84AndBack(PROJ.getSWEREF991415());
  }

  /**
   * Test of getSWEREF991500 method, of class NationalGridsSweden.
   */
  @Test
  public void testSWEREF991500() {
    testToWGS84AndBack(PROJ.getSWEREF991500());
  }

  /**
   * Test of getSWEREF991545 method, of class NationalGridsSweden.
   */
  @Test
  public void testSWEREF991545() {
    testToWGS84AndBack(PROJ.getSWEREF991545());
  }

  /**
   * Test of getSWEREF991630 method, of class NationalGridsSweden.
   */
  @Test
  public void testSWEREF991630() {
    testToWGS84AndBack(PROJ.getSWEREF991630());
  }

  /**
   * Test of getSWEREF991715 method, of class NationalGridsSweden.
   */
  @Test
  public void testSWEREF991715() {
    testToWGS84AndBack(PROJ.getSWEREF991715());
  }

  /**
   * Test of getSWEREF991800 method, of class NationalGridsSweden.
   */
  @Test
  public void testSWEREF991800() {
    testToWGS84AndBack(PROJ.getSWEREF991800());
  }

  /**
   * Test of getSWEREF991845 method, of class NationalGridsSweden.
   */
  @Test
  public void testSWEREF991845() {
    testToWGS84AndBack(PROJ.getSWEREF991845());
  }

  /**
   * Test of getSWEREF992015 method, of class NationalGridsSweden.
   */
  @Test
  public void testSWEREF992015() {
    testToWGS84AndBack(PROJ.getSWEREF992015());
  }

  /**
   * Test of getSWEREF992145 method, of class NationalGridsSweden.
   */
  @Test
  public void testSWEREF992145() {
    testToWGS84AndBack(PROJ.getSWEREF992145());
  }

  /**
   * Test of getSWEREF992315 method, of class NationalGridsSweden.
   */
  @Test
  public void testSWEREF992315() {
    testToWGS84AndBack(PROJ.getSWEREF992315());
  }

  /**
   * Test of getSWEREF99TM method, of class NationalGridsSweden.
   */
  @Test
  public void testSWEREF99TM() {
    testToWGS84AndBack(PROJ.getSWEREF99TM());
  }

}
