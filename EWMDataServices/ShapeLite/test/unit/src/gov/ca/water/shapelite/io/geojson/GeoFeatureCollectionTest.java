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
import java.util.List;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GeoFeatureCollectionTest {

  public GeoFeatureCollectionTest() {
  }

  /**
   * Test of toFeatureSets method, of class GeoFeatureCollection.
   */
  @Test
  public void testToFeatureSets() {
    System.out.println("toFeatureSets");
    GeoFeatureCollection instance = new GeoFeatureCollection();
    List<FeatureSet> expResult = null;
    List<FeatureSet> result = instance.toFeatureSets();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getFeatures method, of class GeoFeatureCollection.
   */
  @Test
  public void testGetFeatures() {
    System.out.println("getFeatures");
    GeoFeatureCollection instance = new GeoFeatureCollection();
    List<GeoFeature> expResult = null;
    List<GeoFeature> result = instance.getFeatures();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setFeatures method, of class GeoFeatureCollection.
   */
  @Test
  public void testSetFeatures() {
    System.out.println("setFeatures");
    List<GeoFeature> features = null;
    GeoFeatureCollection instance = new GeoFeatureCollection();
    instance.setFeatures(features);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getCrs method, of class GeoFeatureCollection.
   */
  @Test
  public void testGetCrs() {
    System.out.println("getCrs");
    GeoFeatureCollection instance = new GeoFeatureCollection();
    GeoObject expResult = null;
    GeoObject result = instance.getCrs();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setCrs method, of class GeoFeatureCollection.
   */
  @Test
  public void testSetCrs() {
    System.out.println("setCrs");
    GeoObject crs = null;
    GeoFeatureCollection instance = new GeoFeatureCollection();
    instance.setCrs(crs);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBbox method, of class GeoFeatureCollection.
   */
  @Test
  public void testGetBbox() {
    System.out.println("getBbox");
    GeoFeatureCollection instance = new GeoFeatureCollection();
    double[] expResult = null;
    double[] result = instance.getBbox();
    assertArrayEquals(expResult, result, 0);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setBbox method, of class GeoFeatureCollection.
   */
  @Test
  public void testSetBbox() {
    System.out.println("setBbox");
    double[] bbox = null;
    GeoFeatureCollection instance = new GeoFeatureCollection();
    instance.setBbox(bbox);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
