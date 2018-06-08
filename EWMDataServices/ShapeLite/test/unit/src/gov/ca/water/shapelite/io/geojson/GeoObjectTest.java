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

import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GeoObjectTest {

  public GeoObjectTest() {
  }

  /**
   * Test of getType method, of class GeoObject.
   */
  @Test
  public void testGetType() {
    System.out.println("getType");
    GeoObject instance = new GeoObject();
    String expResult = null;
    String result = instance.getType();
    assertEquals(expResult, result);
  }

  /**
   * Test of setType method, of class GeoObject.
   */
  @Test
  public void testSetType() {
    System.out.println("setType");
    String type = "TestType123";
    GeoObject instance = new GeoObject();
    instance.setType(type);
    assertEquals(type, instance.getType());
  }

  /**
   * Test of getProperties method, of class GeoObject.
   */
  @Test
  public void testGetProperties() {
    System.out.println("getProperties");
    GeoObject instance = new GeoObject();

    String expKey = "Fish";
    Double expValue = 1234.0;
    assertEquals(true, instance.getProperties().isEmpty());
    instance.getProperties().put(expKey, expValue);
    Double result = (Double) instance.getProperties().get(expKey);
    assertEquals(expValue, result);

  }

  /**
   * Test of setProperties method, of class GeoObject.
   */
  @Test
  public void testSetProperties() {
    System.out.println("setProperties");
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("Fish", 1264.0);
    GeoObject instance = new GeoObject();
    instance.setProperties(properties);
    assertEquals(properties, instance.getProperties());

  }

}
