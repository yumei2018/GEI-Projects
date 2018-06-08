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

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GeoGeometryTest {

  /**
   * A sample json polygon to use for testing.
   */
  private static final String POLYGON_EXAMPLE = "{ \"type\": \"Polygon\",\n"
      + "    \"coordinates\": [\n"
      + "      [ [100.0, 0.0], [101.0, 0.0], [101.0, 1.0], [100.0, 1.0], [100.0, 0.0] ],\n"
      + "      [ [100.2, 0.2], [100.8, 0.2], [100.8, 0.8], [100.2, 0.8], [100.2, 0.2] ]\n"
      + "      ]\n"
      + "   }";

  public GeoGeometryTest() {
  }

  /**
   * Test of getType method, of class GeoGeometry.
   */
  @Test
  public void testGetType() {
    System.out.println("getType");
    GeoGeometry instance = new GeoGeometry();
    GeometryType expResult = null;
    GeometryType result = instance.getType();
    assertEquals(expResult, result);

  }

  /**
   * Test of setType method, of class GeoGeometry.
   */
  @Test
  public void testSetType() {
    System.out.println("setType");
    GeometryType type = GeometryType.Point;
    GeoGeometry instance = new GeoGeometry();
    instance.setType(type);
    assertEquals(type, instance.getType());
  }

  /**
   * Test of getCoordinates method, of class GeoGeometry.
   */
  @Test
  public void testGetCoordinates() {
    System.out.println("getCoordinates");
    GeoGeometry instance = new GeoGeometry();
    Object expResult = null;
    Object result = instance.getCoordinates();
    assertEquals(expResult, result);
  }

  /**
   * Test of setCoordinates method, of class GeoGeometry.
   */
  @Test
  public void testSetCoordinatesObject() {
    System.out.println("setCoordinates");
    ArrayList<Double> coordinates = new ArrayList<>();
    coordinates.add(1.0);
    coordinates.add(2.0);
    GeoGeometry instance = new GeoGeometry();
    instance.setCoordinates(coordinates);
    double[] result = instance.getPointCoordinates();
    double[] expResult = new double[]{1, 2};
    assertArrayEquals(expResult, result, 0);
  }

  /**
   * Test of setCoordinates method, of class GeoGeometry.
   */
  @Test
  public void testSetCoordinatesString() {
    System.out.println("setCoordinates");
    String stringArray = "[[1.0, 2.0],[3.0,4.0]]";
    GeoGeometry instance = new GeoGeometry();
    instance.setCoordinates(stringArray);
    double[][] expResult = new double[][]{new double[]{1, 2}, new double[]{3, 4}};
    double[][] result = instance.getLineStringCoordinates();
    assertArrayEquals(expResult[0], result[0], 0);
    assertArrayEquals(expResult[1], result[1], 0);
  }

  /**
   * Test of setCoordinates method, of class GeoGeometry.
   */
  @Test
  public void testGetMultiPointCoordinates() {
    System.out.println("getMultiPointCoordinates");
    String stringArray = "[[1.0, 2.0],[3.0,4.0]]";
    GeoGeometry instance = new GeoGeometry();
    instance.setCoordinates(stringArray);
    double[][] expResult = new double[][]{new double[]{1, 2}, new double[]{3, 4}};
    double[][] result = instance.getMultiPointCoordinates();
    assertArrayEquals(expResult[0], result[0], 0);
    assertArrayEquals(expResult[1], result[1], 0);
  }

  /**
   * Test of setCoordinates method, of class GeoGeometry.
   */
  @Test
  public void testGetMultiLineStringCoordinates() {
    System.out.println("getMultiLineStringCoordinates");
    String stringArray = "[[[1.0, 2.0],[3.0,4.0]],[[2.0,4.0],[1.0,5.0]]]";
    GeoGeometry instance = new GeoGeometry();
    instance.setCoordinates(stringArray);
    double[][] line1 = new double[][]{new double[]{1, 2}, new double[]{3, 4}};
    double[][] line2 = new double[][]{new double[]{2, 4}, new double[]{1, 5}};
    double[][][] expResult = new double[][][]{line1, line2};

    double[][][] result = instance.getMultiLineStringCoordinates();
    assertArrayEquals(expResult[0][0], result[0][0], 0);
    assertArrayEquals(expResult[0][1], result[0][1], 0);
    assertArrayEquals(expResult[1][0], result[1][0], 0);
    assertArrayEquals(expResult[1][1], result[1][1], 0);
  }

  /**
   * Test of setCoordinates method, of class GeoGeometry.
   */
  @Test
  public void testGetPolygonCoordinates() {
    System.out.println("getPolygonCoordinates");
    String stringArray = "[[[0.0, 0.0],[10.0,0.0],[5,10],[0.0, 0.0]],"
        + "[[1.0,1.0],[5.0,9.0],[9,1],[1,1]]]";
    GeoGeometry instance = new GeoGeometry();
    instance.setCoordinates(stringArray);
    double[][] shell = new double[][]{new double[]{0, 0}, new double[]{10, 0},
    new double[]{5, 10}, new double[]{0, 0}};
    double[][] hole = new double[][]{new double[]{1, 1}, new double[]{5, 9},
    new double[]{9, 1}, new double[]{1, 1}};
    double[][][] expResult = new double[][][]{shell, hole};
    double[][][] result = instance.getPolygonCoordinates();
    assertArrayEquals(expResult[0][0], result[0][0], 0);
    assertArrayEquals(expResult[0][1], result[0][1], 0);
    assertArrayEquals(expResult[0][2], result[0][2], 0);
    assertArrayEquals(expResult[0][3], result[0][3], 0);
    assertArrayEquals(expResult[1][0], result[1][0], 0);
    assertArrayEquals(expResult[1][1], result[1][1], 0);
    assertArrayEquals(expResult[1][2], result[1][2], 0);
    assertArrayEquals(expResult[1][3], result[1][3], 0);
  }

  /**
   * Test of setCoordinates method, of class GeoGeometry.
   */
  @Test
  public void testGetMultiPolygonCoordinates() {
    System.out.println("getMultiPolygonCoordinates");
    String stringArray = "[[[[0.0, 0.0],[10.0,0.0],[5,10],[0.0, 0.0]],"
        + "[[1.0,1.0],[5.0,9.0],[9,1],[1,1]]],[[[5,5],[10,15],[5,15],[5,5]]]]";
    GeoGeometry instance = new GeoGeometry();
    instance.setCoordinates(stringArray);
    double[][] shell1 = new double[][]{new double[]{0, 0}, new double[]{10, 0},
    new double[]{5, 10}, new double[]{0, 0}};
    double[][] hole1 = new double[][]{new double[]{1, 1}, new double[]{5, 9},
    new double[]{9, 1}, new double[]{1, 1}};
    double[][][] poly1 = new double[][][]{shell1, hole1};

    double[][] shell2 = new double[][]{new double[]{5, 5}, new double[]{10, 15},
    new double[]{5, 15}, new double[]{5, 5}};
    double[][][] poly2 = new double[][][]{shell2};

    double[][][][] expResult = new double[][][][]{poly1, poly2};
    double[][][][] result = instance.getMultiPolygonCoordinates();
    assertArrayEquals(expResult[0][0][0], result[0][0][0], 0);
    assertArrayEquals(expResult[0][0][1], result[0][0][1], 0);
    assertArrayEquals(expResult[0][0][2], result[0][0][2], 0);
    assertArrayEquals(expResult[0][0][3], result[0][0][3], 0);
    assertArrayEquals(expResult[0][1][0], result[0][1][0], 0);
    assertArrayEquals(expResult[0][1][1], result[0][1][1], 0);
    assertArrayEquals(expResult[0][1][2], result[0][1][2], 0);
    assertArrayEquals(expResult[0][1][3], result[0][1][3], 0);
    assertArrayEquals(expResult[1][0][0], result[1][0][0], 0);
    assertArrayEquals(expResult[1][0][1], result[1][0][1], 0);
    assertArrayEquals(expResult[1][0][2], result[1][0][2], 0);
    assertArrayEquals(expResult[1][0][3], result[1][0][3], 0);
  }

  /**
   * Test of getGeometries method, of class GeoGeometry.
   */
  @Test
  public void testGetGeometries() {
    System.out.println("getGeometries");
    GeoGeometry instance = new GeoGeometry();
    GeoGeometry expResult = new GeoGeometry();
    instance.getGeometries().add(expResult);
    GeoGeometry result = instance.getGeometries().get(0);
    assertEquals(expResult, result);
  }

  /**
   * Test of setGeometries method, of class GeoGeometry.
   */
  @Test
  public void testSetGeometries() {
    System.out.println("setGeometries");
    List<GeoGeometry> geometries = new ArrayList<>();
    GeoGeometry expResult = new GeoGeometry();
    geometries.add(expResult);
    GeoGeometry instance = new GeoGeometry();
    instance.setGeometries(geometries);

    GeoGeometry result = instance.getGeometries().get(0);
    assertEquals(expResult, result);

  }

  /**
   * Test of copy method, of class GeoGeometry.
   */
  @Test
  public void testCopy() {
    System.out.println("copy");
    GeoGeometry instance = new GeoGeometry();
    GeoGeometry expResult = null;
    GeoGeometry result = instance.copy();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of copyProperties method, of class GeoGeometry.
   */
  @Test
  public void testCopyProperties() {
    System.out.println("copyProperties");
    GeoGeometry other = null;
    GeoGeometry instance = new GeoGeometry();
    instance.copyProperties(other);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getDimension method, of class GeoGeometry.
   */
  @Test
  public void testGetDimension() {
    System.out.println("getDimension");
    GeoGeometry instance = new GeoGeometry();
    Dimension expResult = null;
    Dimension result = instance.getDimension();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setCoordinates method, of class GeoGeometry.
   */
  @Test
  public void testSetCoordinates_ArrayList() {
    System.out.println("setCoordinates");
    ArrayList coordinates = null;
    GeoGeometry instance = new GeoGeometry();
    instance.setCoordinates(coordinates);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setCoordinates method, of class GeoGeometry.
   */
  @Test
  public void testSetCoordinates_String() {
    System.out.println("setCoordinates");
    String stringArray = "";
    GeoGeometry instance = new GeoGeometry();
    instance.setCoordinates(stringArray);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getCoordinateString method, of class GeoGeometry.
   */
  @Test
  public void testGetCoordinateString() {
    System.out.println("getCoordinateString");
    GeoGeometry instance = new GeoGeometry();
    String expResult = "";
    String result = instance.getCoordinateString();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getPointCoordinates method, of class GeoGeometry.
   */
  @Test
  public void testGetPointCoordinates() {
    System.out.println("getPointCoordinates");
    GeoGeometry instance = new GeoGeometry();
    double[] expResult = null;
    double[] result = instance.getPointCoordinates();
    assertArrayEquals(expResult, result, 0);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setPointCoordinates method, of class GeoGeometry.
   */
  @Test
  public void testSetPointCoordinates() {
    System.out.println("setPointCoordinates");
    double[] coord = null;
    GeoGeometry instance = new GeoGeometry();
    instance.setPointCoordinates(coord);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getLineStringCoordinates method, of class GeoGeometry.
   */
  @Test
  public void testGetLineStringCoordinates() {
    System.out.println("getLineStringCoordinates");
    GeoGeometry instance = new GeoGeometry();
    double[][] expResult = null;
    double[][] result = instance.getLineStringCoordinates();
    assertArrayEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setLineStringCoordinates method, of class GeoGeometry.
   */
  @Test
  public void testSetLineStringCoordinates() {
    System.out.println("setLineStringCoordinates");
    double[][] coords = null;
    GeoGeometry instance = new GeoGeometry();
    instance.setLineStringCoordinates(coords);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setMultiPointCoordinates method, of class GeoGeometry.
   */
  @Test
  public void testSetMultiPointCoordinates() {
    System.out.println("setMultiPointCoordinates");
    double[][] coords = null;
    GeoGeometry instance = new GeoGeometry();
    instance.setMultiPointCoordinates(coords);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setMultiLineStringCoordinates method, of class GeoGeometry.
   */
  @Test
  public void testSetMultiLineStringCoordinates() {
    System.out.println("setMultiLineStringCoordinates");
    double[][][] coords = null;
    GeoGeometry instance = new GeoGeometry();
    instance.setMultiLineStringCoordinates(coords);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setPolygonCoordinates method, of class GeoGeometry.
   */
  @Test
  public void testSetPolygonCoordinates() {
    System.out.println("setPolygonCoordinates");
    double[][][] coords = null;
    GeoGeometry instance = new GeoGeometry();
    instance.setPolygonCoordinates(coords);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setMultiPolygonCoordinates method, of class GeoGeometry.
   */
  @Test
  public void testSetMultiPolygonCoordinates() {
    System.out.println("setMultiPolygonCoordinates");
    double[][][][] coords = null;
    GeoGeometry instance = new GeoGeometry();
    instance.setMultiPolygonCoordinates(coords);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toDoubleArrays method, of class GeoGeometry.
   */
  @Test
  public void testToDoubleArrays() {
    System.out.println("toDoubleArrays");
    ArrayList list = null;
    GeoGeometry instance = new GeoGeometry();
    Object expResult = null;
    Object result = instance.toDoubleArrays(list);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toString method, of class GeoGeometry.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    GeoGeometry instance = new GeoGeometry(POLYGON_EXAMPLE);
    String expResult = POLYGON_EXAMPLE;
    String result = instance.toString();
    assertEquals(expResult, result);
  }

}
