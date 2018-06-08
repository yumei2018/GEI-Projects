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

import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.ShapeCategory;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GeometryTypeTest {

  /**
   * An array for the geometry types.
   */
  private static final GeometryType[] ALL_TYPES = new GeometryType[]{
    GeometryType.Point,
    GeometryType.MultiPoint,
    GeometryType.LineString,
    GeometryType.MultiLineString,
    GeometryType.Polygon,
    GeometryType.MultiPolygon,
    GeometryType.GeometryCollection};

  public GeometryTypeTest() {
  }

  /**
   * Test of values method, of class GeometryType.
   */
  @Test
  public void testValues() {
    System.out.println("values");
    GeometryType[] expResult = ALL_TYPES;
    GeometryType[] result = GeometryType.values();
    assertArrayEquals(expResult, result);
  }

  /**
   * Test of valueOf method, of class GeometryType.
   */
  @Test
  public void testValueOfPoint() {
    System.out.println("valueOf");
    String name = "Point";
    GeometryType expResult = GeometryType.Point;
    GeometryType result = GeometryType.valueOf(name);
    assertEquals(expResult, result);
  }

  /**
   * Test of valueOf method, of class GeometryType.
   */
  @Test
  public void testValueOfMultiPoint() {
    System.out.println("valueOf");
    String name = "MultiPoint";
    GeometryType expResult = GeometryType.MultiPoint;
    GeometryType result = GeometryType.valueOf(name);
    assertEquals(expResult, result);
  }

  /**
   * Test of valueOf method, of class GeometryType.
   */
  @Test
  public void testValueOfLineString() {
    System.out.println("valueOf");
    String name = "LineString";
    GeometryType expResult = GeometryType.LineString;
    GeometryType result = GeometryType.valueOf(name);
    assertEquals(expResult, result);
  }

  /**
   * Test of valueOf method, of class GeometryType.
   */
  @Test
  public void testValueOfMultiLineString() {
    System.out.println("valueOf");
    String name = "MultiLineString";
    GeometryType expResult = GeometryType.MultiLineString;
    GeometryType result = GeometryType.valueOf(name);
    assertEquals(expResult, result);
  }

  /**
   * Test of valueOf method, of class GeometryType.
   */
  @Test
  public void testValueOfPolygon() {
    System.out.println("valueOf");
    String name = "Polygon";
    GeometryType expResult = GeometryType.Polygon;
    GeometryType result = GeometryType.valueOf(name);
    assertEquals(expResult, result);
  }

  /**
   * Test of valueOf method, of class GeometryType.
   */
  @Test
  public void testValueOfMultiPolygon() {
    System.out.println("valueOf");
    String name = "MultiPolygon";
    GeometryType expResult = GeometryType.MultiPolygon;
    GeometryType result = GeometryType.valueOf(name);
    assertEquals(expResult, result);
  }

  /**
   * Test of valueOf method, of class GeometryType.
   */
  @Test
  public void testValueOfMultiGeometry() {
    System.out.println("valueOf");
    String name = "MultiGeometry";
    GeometryType expResult = GeometryType.GeometryCollection;
    GeometryType result = GeometryType.valueOf(name);
    assertEquals(expResult, result);
  }

  /**
   * Test of from method, of class GeometryType.
   */
  @Test
  public void testFromPoint() {
    System.out.println("from");
    ShapeCategory category = ShapeCategory.Point;
    GeometryType expResult = GeometryType.Point;
    Optional<GeometryType> result = GeometryType.from(category, Dimension.One);
    assertEquals(expResult, result.get());
  }

  /**
   * Test of from method, of class GeometryType.
   */
  @Test
  public void testFromMultiPoint() {
    System.out.println("from");
    ShapeCategory category = ShapeCategory.MultiPoint;
    double[][] coords = new double[][]{new double[]{1, 2}, new double[]{3, 4}};
    GeometryType expResult = GeometryType.MultiPoint;
    Optional<GeometryType> result = GeometryType.from(category, Dimension.Two);
    assertEquals(expResult, result.get());
  }

  /**
   * Test of from method, of class GeometryType.
   */
  @Test
  public void testFromLineString() {
    System.out.println("from");
    ShapeCategory category = ShapeCategory.PolyLine;
    double[][] coords = new double[][]{new double[]{1, 2}, new double[]{3, 4}};
    GeometryType expResult = GeometryType.LineString;
    Optional<GeometryType> result = GeometryType.from(category, Dimension.Two);
    assertEquals(expResult, result.get());
  }

  /**
   * Test of from method, of class GeometryType.
   */
  @Test
  public void testFromMultiLineString() {
    System.out.println("from");
    ShapeCategory category = ShapeCategory.PolyLine;
    GeometryType expResult = GeometryType.MultiLineString;
    Optional<GeometryType> result = GeometryType.from(category, Dimension.Three);
    assertEquals(expResult, result.get());
  }

  /**
   * Test of from method, of class GeometryType.
   */
  @Test
  public void testFromPolygon() {
    System.out.println("from");
    ShapeCategory category = ShapeCategory.Polygon;
    GeometryType expResult = GeometryType.Polygon;
    Optional<GeometryType> result = GeometryType.from(category, Dimension.Three);
    assertEquals(expResult, result.get());
  }

  /**
   * Test of from method, of class GeometryType.
   */
  @Test
  public void testFromMultiPolygon() {
    System.out.println("from");
    ShapeCategory category = ShapeCategory.Polygon;
    GeometryType expResult = GeometryType.MultiPolygon;
    Optional<GeometryType> result = GeometryType.from(category, Dimension.Four);
    assertEquals(expResult, result.get());
  }

}
