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
public class StateSystemsTest {

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
  private static final StateSystems PROJ = Projections.getProjected().getStateSystems();

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
   * Test of getNAD_1927_Alaska_Albers_Feet method, of class StateSystems.
   */
  @Test
  public void testNAD_1927_Alaska_Albers_Feet() {
    testToWGS84AndBack(PROJ.getNAD_1927_Alaska_Albers_Feet());
  }

  /**
   * Test of getNAD_1927_Alaska_Albers_Meters method, of class StateSystems.
   */
  @Test
  public void testNAD_1927_Alaska_Albers_Meters() {
    testToWGS84AndBack(PROJ.getNAD_1927_Alaska_Albers_Meters());
  }

  /**
   * Test of getNAD_1927_California_Teale_Albers method, of class StateSystems.
   */
  @Test
  public void testNAD_1927_California_Teale_Albers() {
    testToWGS84AndBack(PROJ.getNAD_1927_California_Teale_Albers());
  }

  /**
   * Test of getNAD_1927_Georgia_Statewide_Albers method, of class
   * StateSystems.
   */
  @Test
  public void testNAD_1927_Georgia_Statewide_Albers() {
    testToWGS84AndBack(PROJ.getNAD_1927_Georgia_Statewide_Albers());
  }

  /**
   * Test of getNAD_1927_Michigan_GeoRef_Meters method, of class StateSystems.
   */
  @Test
  public void testNAD_1927_Michigan_GeoRef_Meters() {
    testToWGS84AndBack(PROJ.getNAD_1927_Michigan_GeoRef_Meters());
  }

  /**
   * Test of getNAD_1927_Michigan_GeoRef_US_feet method, of class StateSystems.
   */
  @Test
  public void testNAD_1927_Michigan_GeoRef_US_feet() {
    testToWGS84AndBack(PROJ.getNAD_1927_Michigan_GeoRef_US_feet());
  }

  /**
   * Test of getNAD_1927_Texas_Statewide_Mapping_System method, of class
   * StateSystems.
   */
  @Test
  public void testNAD_1927_Texas_Statewide_Mapping_System() {
    testToWGS84AndBack(PROJ.getNAD_1927_Texas_Statewide_Mapping_System());
  }

  /**
   * Test of getNAD_1927_Wisconsin_TM method, of class StateSystems.
   */
  @Test
  public void testNAD_1927_Wisconsin_TM() {
    testToWGS84AndBack(PROJ.getNAD_1927_Wisconsin_TM());
  }

  /**
   * Test of getNAD_1983_California_Teale_Albers method, of class StateSystems.
   */
  @Test
  public void testNAD_1983_California_Teale_Albers() {
    testToWGS84AndBack(PROJ.getNAD_1983_California_Teale_Albers());
  }

  /**
   * Test of getNAD_1983_Florida_GDL_Albers method, of class StateSystems.
   */
  @Test
  public void testNAD_1983_Florida_GDL_Albers() {
    testToWGS84AndBack(PROJ.getNAD_1983_Florida_GDL_Albers());
  }

  /**
   * Test of getNAD_1983_Georgia_Statewide_Lambert method, of class
   * StateSystems.
   */
  @Test
  public void testNAD_1983_Georgia_Statewide_Lambert() {
    testToWGS84AndBack(PROJ.getNAD_1983_Georgia_Statewide_Lambert());
  }

  /**
   * Test of getNAD_1983_HARN_California_Teale_Albers method, of class
   * StateSystems.
   */
  @Test
  public void testNAD_1983_HARN_California_Teale_Albers() {
    testToWGS84AndBack(PROJ.getNAD_1983_HARN_California_Teale_Albers());
  }

  /**
   * Test of getNAD_1983_HARN_Florida_GDL_Albers method, of class StateSystems.
   */
  @Test
  public void testNAD_1983_HARN_Florida_GDL_Albers() {
    testToWGS84AndBack(PROJ.getNAD_1983_HARN_Florida_GDL_Albers());
  }

  /**
   * Test of getNAD_1983_HARN_Michigan_GeoRef_Meters method, of class
   * StateSystems.
   */
  @Test
  public void testNAD_1983_HARN_Michigan_GeoRef_Meters() {
    testToWGS84AndBack(PROJ.getNAD_1983_HARN_Michigan_GeoRef_Meters());
  }

  /**
   * Test of getNAD_1983_HARN_Michigan_GeoRef method, of class StateSystems.
   */
  @Test
  public void testNAD_1983_HARN_Michigan_GeoRef() {
    testToWGS84AndBack(PROJ.getNAD_1983_HARN_Michigan_GeoRef());
  }

  /**
   * Test of getNAD_1983_HARN_Mississippi_TM method, of class StateSystems.
   */
  @Test
  public void testNAD_1983_HARN_Mississippi_TM() {
    testToWGS84AndBack(PROJ.getNAD_1983_HARN_Mississippi_TM());
  }

  /**
   * Test of getNAD_1983_HARN_Oregon_Statewide_Lambert_Feet_Intl method, of
   * class StateSystems.
   */
  @Test
  public void testNAD_1983_HARN_Oregon_Statewide_Lambert_Feet_Intl() {
    testToWGS84AndBack(PROJ.getNAD_1983_HARN_Oregon_Statewide_Lambert_Feet_Intl());
  }

  /**
   * Test of getNAD_1983_HARN_Oregon_Statewide_Lambert method, of class
   * StateSystems.
   */
  @Test
  public void testNAD_1983_HARN_Oregon_Statewide_Lambert() {
    testToWGS84AndBack(PROJ.getNAD_1983_HARN_Oregon_Statewide_Lambert());
  }

  /**
   * Test of getNAD_1983_HARN_Wisconsin_TM_US_Feet method, of class
   * StateSystems.
   */
  @Test
  public void testNAD_1983_HARN_Wisconsin_TM_US_Feet() {
    testToWGS84AndBack(PROJ.getNAD_1983_HARN_Wisconsin_TM_US_Feet());
  }

  /**
   * Test of getNAD_1983_HARN_Wisconsin_TM method, of class StateSystems.
   */
  @Test
  public void testNAD_1983_HARN_Wisconsin_TM() {
    testToWGS84AndBack(PROJ.getNAD_1983_HARN_Wisconsin_TM());
  }

  /**
   * Test of getNAD_1983_Idaho_TM method, of class StateSystems.
   */
  @Test
  public void testNAD_1983_Idaho_TM() {
    testToWGS84AndBack(PROJ.getNAD_1983_Idaho_TM());
  }

  /**
   * Test of getNAD_1983_Michigan_GeoRef_Meters method, of class StateSystems.
   */
  @Test
  public void testNAD_1983_Michigan_GeoRef_Meters() {
    testToWGS84AndBack(PROJ.getNAD_1983_Michigan_GeoRef_Meters());
  }

  /**
   * Test of getNAD_1983_Michigan_GeoRef_US_feet method, of class StateSystems.
   */
  @Test
  public void testNAD_1983_Michigan_GeoRef_US_feet() {
    testToWGS84AndBack(PROJ.getNAD_1983_Michigan_GeoRef_US_feet());
  }

  /**
   * Test of getNAD_1983_Mississippi_TM method, of class StateSystems.
   */
  @Test
  public void testNAD_1983_Mississippi_TM() {
    testToWGS84AndBack(PROJ.getNAD_1983_Mississippi_TM());
  }

  /**
   * Test of getNAD_1983_Oregon_Statewide_Lambert_Feet_Intl method, of class
   * StateSystems.
   */
  @Test
  public void testNAD_1983_Oregon_Statewide_Lambert_Feet_Intl() {
    testToWGS84AndBack(PROJ.getNAD_1983_Oregon_Statewide_Lambert_Feet_Intl());
  }

  /**
   * Test of getNAD_1983_Oregon_Statewide_Lambert method, of class
   * StateSystems.
   */
  @Test
  public void testNAD_1983_Oregon_Statewide_Lambert() {
    testToWGS84AndBack(PROJ.getNAD_1983_Oregon_Statewide_Lambert());
  }

  /**
   * Test of getNAD_1983_Texas_Centric_Mapping_System_Albers method, of class
   * StateSystems.
   */
  @Test
  public void testNAD_1983_Texas_Centric_Mapping_System_Albers() {
    testToWGS84AndBack(PROJ.getNAD_1983_Texas_Centric_Mapping_System_Albers());
  }

  /**
   * Test of getNAD_1983_Texas_Centric_Mapping_System_Lambert method, of class
   * StateSystems.
   */
  @Test
  public void testNAD_1983_Texas_Centric_Mapping_System_Lambert() {
    testToWGS84AndBack(PROJ.getNAD_1983_Texas_Centric_Mapping_System_Lambert());
  }

  /**
   * Test of getNAD_1983_Texas_Statewide_Mapping_System method, of class
   * StateSystems.
   */
  @Test
  public void testNAD_1983_Texas_Statewide_Mapping_System() {
    testToWGS84AndBack(PROJ.getNAD_1983_Texas_Statewide_Mapping_System());
  }

  /**
   * Test of getNAD_1983_USFS_R6_Albers method, of class StateSystems.
   */
  @Test
  public void testNAD_1983_USFS_R6_Albers() {
    testToWGS84AndBack(PROJ.getNAD_1983_USFS_R6_Albers());
  }

  /**
   * Test of getNAD_1983_Wisconsin_TM_US_Feet method, of class StateSystems.
   */
  @Test
  public void testNAD_1983_Wisconsin_TM_US_Feet() {
    testToWGS84AndBack(PROJ.getNAD_1983_Wisconsin_TM_US_Feet());
  }

  /**
   * Test of getNAD_1983_Wisconsin_TM method, of class StateSystems.
   */
  @Test
  public void testNAD_1983_Wisconsin_TM() {
    testToWGS84AndBack(PROJ.getNAD_1983_Wisconsin_TM());
  }

}
