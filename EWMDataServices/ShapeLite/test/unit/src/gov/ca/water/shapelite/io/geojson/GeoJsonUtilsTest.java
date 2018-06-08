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
package gov.ca.water.shapelite.io.geojson;

import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.ShapeCategory;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GeoJsonUtilsTest {

  public GeoJsonUtilsTest() {
  }

  /**
   * Test of fromGeoJson method, of class GeoJsonUtils.
   */
  @Test
  public void testFromGeoJson_3args() {
    System.out.println("fromGeoJson");
    ShapeCategory category = null;
    List<String> coordinateArrays = null;
    ProjectionInfo projection = null;
    FeatureSet expResult = null;
    FeatureSet result = GeoJsonUtils.fromGeoJson(category, coordinateArrays, projection);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of fromGeoJson method, of class GeoJsonUtils.
   */
  @Test
  public void testFromGeoJson_String() {
    System.out.println("fromGeoJson");
    String geoJsonFeatureCollection = "";
    List<FeatureSet> expResult = null;
    List<FeatureSet> result = GeoJsonUtils.fromGeoJson(geoJsonFeatureCollection);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of geoJsonToZippedShapes method, of class GeoJsonUtils.
   */
  @Test
  public void testGeoJsonToZippedShapes() {
    System.out.println("geoJsonToZippedShapes");
    InputStream geoJsonFeatureCollection = null;
    OutputStream zippedFiles = null;
    GeoJsonUtils.geoJsonToZippedShapes(geoJsonFeatureCollection, zippedFiles);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
