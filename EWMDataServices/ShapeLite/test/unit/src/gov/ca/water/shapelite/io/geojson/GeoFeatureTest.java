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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GeoFeatureTest {

  String polyExample = "{ \"type\": \"Polygon\",\n"
      + "    \"coordinates\": [\n"
      + "      [ [100.0, 0.0], [101.0, 0.0], [101.0, 1.0], [100.0, 1.0], [100.0, 0.0] ],\n"
      + "      [ [100.2, 0.2], [100.8, 0.2], [100.8, 0.8], [100.2, 0.8], [100.2, 0.2] ]\n"
      + "      ]\n"
      + "   }";

  public GeoFeatureTest() {
  }

  /**
   * Test of getGeometry method, of class GeoFeature.
   */
  @Test
  public void testGetGeometry() {
    System.out.println("getGeometry");
    GeoFeature instance = new GeoFeature();
    GeoGeometry expResult = new GeoGeometry(polyExample);
    instance.setGeometry(expResult);

    GeoGeometry result = instance.getGeometry();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setGeometry method, of class GeoFeature.
   */
  @Test
  public void testSetGeometry() {
    System.out.println("setGeometry");
    GeoGeometry geometry = null;
    GeoFeature instance = new GeoFeature();
    instance.setGeometry(geometry);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBbox method, of class GeoFeature.
   */
  @Test
  public void testGetBbox() {
    System.out.println("getBbox");
    GeoFeature instance = new GeoFeature();
    double[] expResult = new double[]{1, 2, 3, 4};
    double[] result = instance.getBbox();
    assertArrayEquals(expResult, result, 0);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setBbox method, of class GeoFeature.
   */
  @Test
  public void testSetBbox() {
    System.out.println("setBbox");
    double[] bbox = null;
    GeoFeature instance = new GeoFeature();
    instance.setBbox(bbox);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
