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

import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeCategory;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.compare.ShapeComparer;
import gov.ca.water.shapelite.coordinate.CoordXY;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GeoJsonShapeConverterTest {

  private final String nullPoint = "{\n"
      + "        \"type\": \"Point\""
      + "      }";

  private final String nullMultiPoint = "{\n"
      + "        \"type\": \"MultiPoint\""
      + "      }";

  private final String nullLine = "{\n"
      + "        \"type\": \"LineString\""
      + "      }";

  private final String nullPolygon = "{\n"
      + "        \"type\": \"Polygon\""
      + "      }";

  private final String multiLineString = "{ \"type\": \"MultiLineString\",\n"
      + "    \"coordinates\": [\n"
      + "        [ [100.0, 0.0], [101.0, 1.0] ],\n"
      + "        [ [102.0, 2.0], [103.0, 3.0] ]\n"
      + "      ]\n"
      + "    }";

  private final String pointJson = "{\n"
      + "        \"type\": \"Point\",\n"
      + "        \"coordinates\": [\n"
      + "          -121.43085479736327,\n"
      + "          38.593528858323666\n"
      + "        ]\n"
      + "      }";

  private final String polygonJson = "{ \"type\": \"Polygon\",\n"
      + "    \"coordinates\": [\n"
      + "      [ [100.0, 0.0], [101.0, 0.0], [101.0, 1.0], [100.0, 1.0],"
      + " [100.0, 0.0] ],\n"
      + "      [ [100.2, 0.2], [100.8, 0.2], [100.8, 0.8], [100.2, 0.8], "
      + "[100.2, 0.2] ]\n"
      + "      ]\n"
      + "   }";

  private final String multiPointJson = "{ \"type\": \"MultiPoint\",\n"
      + "    \"coordinates\": [ [100.0, 0.0], [101.0, 1.0] ]\n"
      + "    }";

  private final String multiPolygonJson = "{ \"type\": \"MultiPolygon\",\n"
      + "    \"coordinates\": [\n"
      + "      [[[102.0, 2.0], [103.0, 2.0], [103.0, 3.0], [102.0, 3.0], [102.0, 2.0]]],\n"
      + "      [[[100.0, 0.0], [101.0, 0.0], [101.0, 1.0], [100.0, 1.0], [100.0, 0.0]],\n"
      + "       [[100.2, 0.2], [100.8, 0.2], [100.8, 0.8], [100.2, 0.8], [100.2, 0.2]]]\n"
      + "      ]\n"
      + "    }";

  private final String lineJson = "{ \"type\": \"LineString\",\n"
      + "    \"coordinates\": [ [100.0, 0.0], [101.0, 1.0] ]\n"
      + "    }";

  private final String geometryCollection = "{ \"type\": \"GeometryCollection\",\n"
      + "    \"geometries\": [\n"
      + "      { \"type\": \"Point\",\n"
      + "        \"coordinates\": [100.0, 0.0]\n"
      + "        },\n"
      + "      { \"type\": \"LineString\",\n"
      + "        \"coordinates\": [ [101.0, 0.0], [102.0, 1.0] ]\n"
      + "        }\n"
      + "    ]\n"
      + "  }";

  /**
   * A solid polygon, created in a counter clockwise direction so that it can be
   * flipped to clockwise for testing.
   */
  private final String counterClockwisePolygon = "{\n"
      + "        \"type\": \"Polygon\",\n"
      + "        \"coordinates\": [\n"
      + "          [\n"
      + "            [\n"
      + "              1,\n"
      + "              10\n"
      + "            ],\n"
      + "            [\n"
      + "              1,\n"
      + "              5\n"
      + "            ],\n"
      + "            [\n"
      + "              2,\n"
      + "              5\n"
      + "            ],\n"
      + "            [\n"
      + "              2,\n"
      + "              10\n"
      + "            ],\n"
      + "            [\n"
      + "              1,\n"
      + "              10\n"
      + "            ]\n"
      + "          ]\n"
      + "        ]\n"
      + "      }";

  public GeoJsonShapeConverterTest() {
  }

  /**
   * Test of getShapes method, of class GeoJsonShapeConverter.
   */
  @Test
  public void testPointAndlineGeometryCollection() {
    System.out.println("getShapes - Geometry Collection");
    GeoGeometry geometry = new GeoGeometry(geometryCollection);
    GeoJsonShapeConverter instance = new GeoJsonShapeConverter();
    Shape expPoint = new Shape(new CoordXY(100, 0));
    Shape expLine = new Shape(ShapeType.PolyLine);
    Part part = new Part();
    part.getCoordinates().add(new CoordXY(101, 0));
    part.getCoordinates().add(new CoordXY(102, 1));
    expLine.getParts().add(part);
    expLine.calculateBounds();
    List<Shape> result = instance.getShapes(geometry);
    int expSize = 2;
    assertEquals(expSize, result.size());
    boolean expResult = true;
    ShapeComparer shapes = new ShapeComparer();
    assertEquals(expResult, shapes.equivalent(expPoint, result.get(0)));
    assertEquals(expResult, shapes.equivalent(expLine, result.get(1)));
  }

  /**
   * Test of getShapes method, of class GeoJsonShapeConverter.
   */
  @Test
  public void testgetShapesPoint() {
    System.out.println("getShapes - Point");
    GeoGeometry geometry = new GeoGeometry(pointJson);
    GeoJsonShapeConverter instance = new GeoJsonShapeConverter();
    Shape expPoint = new Shape(new CoordXY(-121.43085479736327, 38.593528858323666));
    List<Shape> result = instance.getShapes(geometry);
    int expSize = 1;
    assertEquals(expSize, result.size());
    boolean expResult = true;
    ShapeComparer shapes = new ShapeComparer();
    assertEquals(expResult, shapes.equivalent(expPoint, result.get(0)));
  }

  /**
   * Test of getShapes method, of class GeoJsonShapeConverter.
   */
  @Test
  public void testgetShapesMultiPoint() {
    System.out.println("getShapes - MultiPoint");
    GeoGeometry geometry = new GeoGeometry(multiPointJson);
    GeoJsonShapeConverter instance = new GeoJsonShapeConverter();
    Shape expPoint = new Shape(new CoordXY(100, 0));
    Shape expLine = new Shape(ShapeType.PolyLine);
    Part part = new Part();
    part.getCoordinates().add(new CoordXY(101, 0));
    part.getCoordinates().add(new CoordXY(102, 1));
    expLine.getParts().add(part);
    expLine.calculateBounds();
    List<Shape> result = instance.getShapes(geometry);
    int expSize = 2;
    assertEquals(expSize, result.size());
    boolean expResult = true;
    ShapeComparer shapes = new ShapeComparer();
    assertEquals(expResult, shapes.equivalent(expPoint, result.get(0)));
    assertEquals(expResult, shapes.equivalent(expLine, result.get(1)));
  }

  /**
   * Test of getShapes method, of class GeoJsonShapeConverter.
   */
  @Test
  public void testgetShapesLineString() {
    System.out.println("getShapes - LineString");
    GeoGeometry geometry = new GeoGeometry(lineJson);
    GeoJsonShapeConverter instance = new GeoJsonShapeConverter();
    Shape expShape = new Shape(ShapeType.PolyLine);
    Part part = new Part();
    part.getCoordinates().add(new CoordXY(100, 0));
    part.getCoordinates().add(new CoordXY(101, 1));
    expShape.getParts().add(part);
    expShape.calculateBounds();
    List<Shape> result = instance.getShapes(geometry);
    int expSize = 1;
    assertEquals(expSize, result.size());
    boolean expResult = true;
    ShapeComparer shapes = new ShapeComparer();
    assertEquals(expResult, shapes.equivalent(expShape, result.get(0)));
  }

  /**
   * Test of getShapes method, of class GeoJsonShapeConverter.
   */
  @Test
  public void testgetShapesMultiLineString() {
    System.out.println("getShapes - MultiLineString");
    GeoGeometry geometry = new GeoGeometry(multiLineString);
    GeoJsonShapeConverter instance = new GeoJsonShapeConverter();
    Shape expShape = new Shape(ShapeType.PolyLine);
    Part part = new Part();
    part.getCoordinates().add(new CoordXY(100, 0));
    part.getCoordinates().add(new CoordXY(101, 1));
    expShape.getParts().add(part);
    Part part2 = new Part();
    part2.getCoordinates().add(new CoordXY(102.0, 2.0));
    part2.getCoordinates().add(new CoordXY(103.0, 3.0));
    expShape.getParts().add(part2);
    expShape.calculateBounds();
    List<Shape> result = instance.getShapes(geometry);
    int expSize = 1;
    assertEquals(expSize, result.size());
    boolean expResult = true;
    ShapeComparer shapes = new ShapeComparer();
    assertEquals(expResult, shapes.equivalent(expShape, result.get(0)));
  }

  /**
   * Test of getShapes method, of class GeoJsonShapeConverter.
   */
  @Test
  public void testgetShapesPolygon() {
    System.out.println("getShapes - Polygon");
    GeoGeometry geometry = new GeoGeometry(polygonJson);
    GeoJsonShapeConverter instance = new GeoJsonShapeConverter();
    Shape expShape = new Shape(ShapeType.Polygon);
    Part shell = new Part();
    shell.setClosed(true);
    shell.getCoordinates().add(new CoordXY(100, 1));
    shell.getCoordinates().add(new CoordXY(101, 1));
    shell.getCoordinates().add(new CoordXY(101, 0));
    shell.getCoordinates().add(new CoordXY(100, 0));
    expShape.getParts().add(shell);
    Part hole = new Part();
    hole.setClosed(true);
    hole.getCoordinates().add(new CoordXY(100.2, 0.2));
    hole.getCoordinates().add(new CoordXY(100.8, 0.2));
    hole.getCoordinates().add(new CoordXY(100.8, 0.8));
    hole.getCoordinates().add(new CoordXY(100.2, 0.8));
    expShape.getParts().add(hole);
    expShape.calculateBounds();
    List<Shape> result = instance.getShapes(geometry);
    int expSize = 1;
    assertEquals(expSize, result.size());
    boolean expResult = true;
    ShapeComparer shapes = new ShapeComparer();
    assertEquals(expResult, shapes.equivalent(expShape, result.get(0)));
  }

  /**
   * Test of getShapes method, of class GeoJsonShapeConverter.
   */
  @Test
  public void testgetShapesMultiPolygon() {
    System.out.println("getShapes - MultiPolygon");
    GeoGeometry geometry = new GeoGeometry(multiPolygonJson);
    GeoJsonShapeConverter instance = new GeoJsonShapeConverter();
    Shape expShape = new Shape(ShapeType.Polygon);
    Part part = new Part();
    part.setClosed(true);
    part.getCoordinates().add(new CoordXY(102, 3));
    part.getCoordinates().add(new CoordXY(103, 3));
    part.getCoordinates().add(new CoordXY(103, 2));
    part.getCoordinates().add(new CoordXY(102, 2));
    expShape.getParts().add(part);

    Part shell = new Part();
    shell.setClosed(true);
    shell.getCoordinates().add(new CoordXY(100, 1));
    shell.getCoordinates().add(new CoordXY(101, 1));
    shell.getCoordinates().add(new CoordXY(101, 0));
    shell.getCoordinates().add(new CoordXY(100, 0));
    expShape.getParts().add(shell);
    Part hole = new Part();
    hole.setClosed(true);
    hole.getCoordinates().add(new CoordXY(100.2, 0.2));
    hole.getCoordinates().add(new CoordXY(100.8, 0.2));
    hole.getCoordinates().add(new CoordXY(100.8, 0.8));
    hole.getCoordinates().add(new CoordXY(100.2, 0.8));
    expShape.getParts().add(hole);
    expShape.calculateBounds();
    List<Shape> result = instance.getShapes(geometry);
    int expSize = 1;
    assertEquals(expSize, result.size());
    boolean expResult = true;
    ShapeComparer shapes = new ShapeComparer();
    assertEquals(expResult, shapes.equivalent(expShape, result.get(0)));
  }

  /**
   * Test of getShapes method, of class GeoJsonShapeConverter.
   */
  @Test
  public void testGetPolygonWithHole() {
    System.out.println("getShapes");
    GeoGeometry geometry = new GeoGeometry(polygonJson);
    GeoJsonShapeConverter instance = new GeoJsonShapeConverter();
    Shape expPolygon = new Shape(ShapeType.Polygon);

    // What should happen for shell is that the last point should be removed
    // and then the ring should be reversed to get a valid clockwise shell.
    Part shell = new Part();
    shell.setClosed(true);
    shell.getCoordinates().add(new CoordXY(100, 1));
    shell.getCoordinates().add(new CoordXY(101, 1));
    shell.getCoordinates().add(new CoordXY(101, 0));
    shell.getCoordinates().add(new CoordXY(100, 0));
    expPolygon.getParts().add(shell);
    Part hole = new Part();
    hole.setClosed(true);
    hole.getCoordinates().add(new CoordXY(100.2, 0.2));
    hole.getCoordinates().add(new CoordXY(100.8, 0.2));
    hole.getCoordinates().add(new CoordXY(100.8, 0.8));
    hole.getCoordinates().add(new CoordXY(100.2, 0.8));
    expPolygon.getParts().add(hole);
    List<Shape> result = instance.getShapes(geometry);
    int expSize = 1;
    assertEquals(expSize, result.size());
    Shape first = result.get(0);
    boolean expResult = true;
    ShapeComparer shapes = new ShapeComparer();

    assertEquals(expResult, shapes.equivalent(expPolygon, first));

  }

  /**
   * Test of getShape method, of class GeoJsonShapeConverter.
   */
  @Test
  public void testGetShapeLine() {
    System.out.println("getShape");
    ShapeCategory category = ShapeCategory.PolyLine;
    GeoGeometry geometry = new GeoGeometry(lineJson);
    GeoJsonShapeConverter instance = new GeoJsonShapeConverter();
    Shape expShape = new Shape(ShapeType.PolyLine);
    Part part = new Part();
    part.getCoordinates().add(new CoordXY(100, 0));
    part.getCoordinates().add(new CoordXY(101, 1));
    expShape.getParts().add(part);
    Shape result = instance.getShape(category, geometry);
    ShapeComparer shapes = new ShapeComparer();
    boolean expResult = true;
    assertEquals(expResult, shapes.equivalent(expShape, result));
  }

  /**
   * Test of getShape method, of class GeoJsonShapeConverter.
   */
  @Test
  public void testGetShapeMultiPoint() {
    System.out.println("getShape");
    ShapeCategory category = ShapeCategory.MultiPoint;
    GeoGeometry geometry = new GeoGeometry(multiPointJson);
    GeoJsonShapeConverter instance = new GeoJsonShapeConverter();
    Shape expShape = new Shape(ShapeType.MultiPoint);
    Part part = new Part();
    part.getCoordinates().add(new CoordXY(100, 0));
    part.getCoordinates().add(new CoordXY(101, 1));
    expShape.getParts().add(part);
    Shape result = instance.getShape(category, geometry);
    ShapeComparer shapes = new ShapeComparer();
    boolean expResult = true;
    assertEquals(expResult, shapes.equivalent(expShape, result));
  }

  /**
   * Test of getShape method, of class GeoJsonShapeConverter.
   */
  @Test
  public void testGetShapePolygon() {
    System.out.println("getShape");
    ShapeCategory category = ShapeCategory.Polygon;
    GeoGeometry geometry = new GeoGeometry(counterClockwisePolygon);
    GeoJsonShapeConverter instance = new GeoJsonShapeConverter();
    Shape expShape = new Shape(ShapeType.Polygon);
    Part part = new Part();
    part.setClosed(true);
    part.getCoordinates().add(new CoordXY(2, 10));
    part.getCoordinates().add(new CoordXY(2, 5));
    part.getCoordinates().add(new CoordXY(1, 5));
    part.getCoordinates().add(new CoordXY(1, 10));
    expShape.getParts().add(part);
    Shape result = instance.getShape(category, geometry);
    ShapeComparer shapes = new ShapeComparer();
    boolean expResult = true;
    assertEquals(expResult, shapes.equivalent(expShape, result));
  }

  @Test
  public void testGetShapePoint() {
    System.out.println("getShape Point");
    ShapeCategory category = ShapeCategory.Point;
    GeoGeometry geometry = new GeoGeometry(pointJson);
    GeoJsonShapeConverter instance = new GeoJsonShapeConverter();
    Shape expShape = new Shape(new CoordXY(-121.43085479736327, 38.593528858323666));
    Shape result = instance.getShape(category, geometry);
    ShapeComparer shapes = new ShapeComparer();
    boolean expResult = true;
    assertEquals(expResult, shapes.equivalent(expShape, result));
  }

  @Test
  public void testGetShapePointNull() {
    System.out.println("getShape Point Null (no coordinates)");
    ShapeCategory category = ShapeCategory.Point;
    GeoGeometry geometry = new GeoGeometry(nullPoint);
    GeoJsonShapeConverter instance = new GeoJsonShapeConverter();
    Shape expShape = new Shape();
    Shape result = instance.getShape(category, geometry);
    ShapeComparer shapes = new ShapeComparer();
    boolean expResult = true;
    assertEquals(expResult, shapes.equivalent(expShape, result));
  }

  @Test
  public void testGetShapeMultiPointNull() {
    System.out.println("getShape MultiPoint Null (no coordinates)");
    ShapeCategory category = ShapeCategory.MultiPoint;
    GeoGeometry geometry = new GeoGeometry(nullMultiPoint);
    GeoJsonShapeConverter instance = new GeoJsonShapeConverter();
    Shape expShape = new Shape();
    Shape result = instance.getShape(category, geometry);
    ShapeComparer shapes = new ShapeComparer();
    boolean expResult = true;
    assertEquals(expResult, shapes.equivalent(expShape, result));
  }

  @Test
  public void testGetShapeLineStringNull() {
    System.out.println("getShape Point Null (no coordinates)");
    ShapeCategory category = ShapeCategory.PolyLine;
    GeoGeometry geometry = new GeoGeometry(nullLine);
    GeoJsonShapeConverter instance = new GeoJsonShapeConverter();
    Shape expShape = new Shape();
    Shape result = instance.getShape(category, geometry);
    ShapeComparer shapes = new ShapeComparer();
    boolean expResult = true;
    assertEquals(expResult, shapes.equivalent(expShape, result));
  }

  @Test
  public void testGetShapePolygonNull() {
    System.out.println("getShape Point Null (no coordinates)");
    ShapeCategory category = ShapeCategory.Polygon;
    GeoGeometry geometry = new GeoGeometry(nullPolygon);
    GeoJsonShapeConverter instance = new GeoJsonShapeConverter();
    Shape expShape = new Shape();
    Shape result = instance.getShape(category, geometry);
    ShapeComparer shapes = new ShapeComparer();
    boolean expResult = true;
    assertEquals(expResult, shapes.equivalent(expShape, result));
  }

}
