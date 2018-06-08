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
package gov.ca.water.shapelite.io.json;

import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class JsonFeatureTest {

  public JsonFeatureTest() {
  }

  /**
   * Test of getAttributes method, of class JsonFeature.
   */
  @Test
  public void testGetAttributes() {
    System.out.println("getAttributes");
    JsonFeature<JsonPoint> instance = new JsonFeature<>();
    instance.getAttributes().put("Test1", "TestValue");
    String expResult = "TestValue";
    String result = instance.getAttributes().get("Test1");
    assertEquals(expResult, result);
  }

  /**
   * Test of setAttributes method, of class JsonFeature.
   */
  @Test
  public void testSetAttributes() {
    System.out.println("setAttributes");
    JsonFeature<JsonPoint> instance = new JsonFeature<>();
    HashMap<String, String> attributes = new HashMap<>();
    attributes.put("Test1", "TestValue");
    instance.setAttributes(attributes);
    String expResult = "TestValue";
    String result = instance.getAttributes().get("Test1");
    assertEquals(expResult, result);
  }

  /**
   * Test of getGeometry method, of class JsonFeature.
   */
  @Test
  public void testGeometry() {
    System.out.println("getGeometry");
    JsonFeature<JsonPoint> instance = new JsonFeature<>();
    JsonPoint expResult = new JsonPoint(-13573884.660153722, 4826263.4669894828);
    instance.setGeometry(expResult);
    JsonPoint result = instance.getGeometry();
    assertEquals(expResult, result);
  }

  /**
   * Test of toShape method, of class JsonFeature.
   */
  @Test
  public void testToFeatureShapePointXY() {
    System.out.println("toFeature");
    JsonFeature<JsonPoint> instance = new JsonFeature<>();
    JsonPoint point = new JsonPoint(-13573884.660153722, 4826263.4669894828);
    instance.setGeometry(point);
    Feature feature = instance.toFeature(ShapeType.Point, false, false);
    Shape result = feature.getShape();
    assertEquals(-13573884.660153722, result.first().getX(), 0);
    assertEquals(4826263.4669894828, result.first().getY(), 0);
  }

  /**
   * Test of toFeature method, of class JsonFeature.
   */
  @Test
  public void testToFeature() {
    System.out.println("toFeature");
    JsonFeature<JsonPoint> instance = new JsonFeature<>();
    instance.getAttributes().put("Test1", "TestValue");
    JsonPoint point = new JsonPoint(-13573884.660153722, 4826263.4669894828);
    instance.setGeometry(point);
    String expResult = "TestValue";
    Feature result = instance.toFeature(ShapeType.Point, false, false);
    assertEquals(expResult, result.getAttributes().get("Test1"));
  }

}
