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

import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.Optional;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class JsonFieldTest {

  /**
   * Test of toField method, of class JsonField.
   */
  @Test
  public void testToFieldString() {
    System.out.println("toField String");
    JsonField instance = new JsonField();
    instance.setName("KML_ID");
    instance.setType(EsriFieldType.STRING);
    instance.setLength(200);
    Optional<Field> maybeResultField = instance.toField();
    Field field = maybeResultField.get();
    assertEquals("KML_ID", field.getName());
    assertEquals(200, field.getLength());
    assertEquals("C", field.getType());
  }

  /**
   * Test of toField method, of class JsonField.
   */
  @Test
  public void testToFieldObjectID() {
    System.out.println("toField Object ID");
    JsonField instance = new JsonField();
    instance.setName("OBJECT_ID");
    instance.setType(EsriFieldType.OBJECT_ID);
    Optional<Field> maybeResultField = instance.toField();
    Field field = maybeResultField.get();
    assertEquals("OBJECT_ID", field.getName());
    assertEquals(32, field.getLength());
    assertEquals("N", field.getType());
  }

  /**
   * Test of toField method, of class JsonField.
   */
  @Test
  public void testToFieldDate() {
    System.out.println("toField Date");
    JsonField instance = new JsonField();
    instance.setName("DATE");
    instance.setType(EsriFieldType.DATE);
    Optional<Field> maybeResultField = instance.toField();
    Field field = maybeResultField.get();
    assertEquals("DATE", field.getName());
    assertEquals(8, field.getLength());
    assertEquals("D", field.getType());
  }

  /**
   * Test of toField method, of class JsonField.
   */
  @Test
  public void testToFieldDouble() {
    System.out.println("toField Double");
    JsonField instance = new JsonField();
    instance.setName("Double");
    instance.setType(EsriFieldType.DOUBLE);
    Optional<Field> maybeResultField = instance.toField();
    Field field = maybeResultField.get();
    assertEquals("Double", field.getName());
    assertEquals(32, field.getLength());
    assertEquals(32, field.getDecimal());
    assertEquals("N", field.getType());
  }

  /**
   * Test of toField method, of class JsonField.
   */
  @Test
  public void testToFieldSingle() {
    System.out.println("toField Single");
    JsonField instance = new JsonField();
    instance.setName("Single");
    instance.setType(EsriFieldType.DOUBLE);
    Optional<Field> maybeResultField = instance.toField();
    Field field = maybeResultField.get();
    assertEquals("Single", field.getName());
    assertEquals(16, field.getLength());
    assertEquals(16, field.getDecimal());
    assertEquals("N", field.getType());
  }

  /**
   * Test of toField method, of class JsonField.
   */
  @Test
  public void testToFieldInteger() {
    System.out.println("toField Integer");
    JsonField instance = new JsonField();
    instance.setName("Integer");
    instance.setType(EsriFieldType.INTEGER);
    Optional<Field> maybeResultField = instance.toField();
    Field field = maybeResultField.get();
    assertEquals("Integer", field.getName());
    assertEquals(32, field.getLength());
    assertEquals(0, field.getDecimal());
    assertEquals("N", field.getType());
  }

  /**
   * Test of toField method, of class JsonField.
   */
  @Test
  public void testToFieldShort() {
    System.out.println("toField Short");
    JsonField instance = new JsonField();
    instance.setName("Short");
    instance.setType(EsriFieldType.SMALL_INTEGER);
    Optional<Field> maybeResultField = instance.toField();
    Field field = maybeResultField.get();
    assertEquals("Short", field.getName());
    assertEquals(16, field.getLength());
    assertEquals(0, field.getDecimal());
    assertEquals("N", field.getType());
  }

  /**
   * Test of toField method, of class JsonField.
   */
  @Test
  public void testToFieldGlobalId() {
    System.out.println("toField Global ID");
    JsonField instance = new JsonField();
    instance.setName("Global_ID");
    instance.setType(EsriFieldType.GLOBAL_ID);
    Optional<Field> maybeResultField = instance.toField();
    Field field = maybeResultField.get();
    assertEquals("Global_ID", field.getName());
    assertEquals(255, field.getLength());
    assertEquals("C", field.getType());
  }

  /**
   * Test of toField method, of class JsonField.
   */
  @Test
  public void testToFieldGId() {
    System.out.println("toField Global ID");
    JsonField instance = new JsonField();
    instance.setName("Global_ID");
    instance.setType(EsriFieldType.GUID);
    Optional<Field> maybeResultField = instance.toField();
    Field field = maybeResultField.get();
    assertEquals("Global_ID", field.getName());
    assertEquals(255, field.getLength());
    assertEquals("C", field.getType());
  }

  /**
   * Test of getName method, of class JsonField.
   */
  @Test
  public void testName() {
    System.out.println("getName");
    JsonField instance = new JsonField();
    instance.setName("Test");
    String expResult = "Test";
    String result = instance.getName();
    assertEquals(expResult, result);
  }

  /**
   * Test of getType method, of class JsonField.
   */
  @Test
  public void testType() {
    System.out.println("getType");
    JsonField instance = new JsonField();
    instance.setType("esriFieldTypeDouble");
    String expResult = "esriFieldTypeDouble";
    String result = instance.getType();
    assertEquals(expResult, result);
  }

  /**
   * Test of getAlias method, of class JsonField.
   */
  @Test
  public void testAlias() {
    System.out.println("getAlias");
    JsonField instance = new JsonField();
    instance.setAlias("Bob");
    String expResult = "Bob";
    String result = instance.getAlias();
    assertEquals(expResult, result);
  }

  /**
   * Test of getLength method, of class JsonField.
   */
  @Test
  public void testGetLength() {
    System.out.println("getLength");
    JsonField instance = new JsonField();
    boolean expResult = false;
    Optional<Integer> maybeResult = instance.getLength();
    assertEquals(expResult, maybeResult.isPresent());
  }

  /**
   * Test of setLength method, of class JsonField.
   */
  @Test
  public void testSetLength() {
    System.out.println("getLength");
    JsonField instance = new JsonField();
    instance.setLength(10);
    Integer expResult = 10;
    Optional<Integer> maybeResult = instance.getLength();
    assertEquals(expResult, maybeResult.get());
  }

}
