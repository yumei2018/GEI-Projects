/*
 * The MIT License
 *
 * Copyright 2017 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.analysis;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.FieldType;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeCategory;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.coordinate.CoordM;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class FeaturesetTypeConverterTest {

  public FeaturesetTypeConverterTest() {
  }

  /**
   * Test of convert method, of class FeaturesetTypeConverter.
   */
  @Test
  public void testConvertNullFeatureSet() {
    System.out.println("convert Null FeatureSet");
    FeatureSet featureSet = null;
    ShapeType type = ShapeType.Point;
    boolean thrown = false;
    try {
      FeatureSet result = FeaturesetTypeConverter.convert(featureSet, type);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    assertEquals(true, thrown);
  }

  /**
   * Test of convert method, of class FeaturesetTypeConverter.
   */
  @Test
  public void testConvertNullType() {
    System.out.println("convert Null FeatureSet");
    FeatureSet featureSet = getRandom(ShapeType.Point);
    ShapeType type = null;
    boolean thrown = false;
    try {
      FeatureSet result = FeaturesetTypeConverter.convert(featureSet, type);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    assertEquals(true, thrown);
  }

  /**
   * Test of convert method, of class FeaturesetTypeConverter.
   */
  @Test
  public void testConvertPolygonToPolygonZ() {
    testConvert(ShapeType.Polygon, ShapeType.PolygonZ);
  }

  /**
   * Test of convert method, of class FeaturesetTypeConverter.
   */
  @Test
  public void testConvertPolygonToPolygonM() {
    testConvert(ShapeType.Polygon, ShapeType.PolygonM);
  }

  /**
   * Test of convert method, of class FeaturesetTypeConverter.
   */
  @Test
  public void testConvertPolygonMToPolygonZ() {
    testConvert(ShapeType.PolygonM, ShapeType.PolygonZ);
  }

  /**
   * Test of convert method, of class FeaturesetTypeConverter.
   */
  @Test
  public void testConvertPolygonZToPolygonM() {
    testConvert(ShapeType.PolygonZ, ShapeType.PolygonM);
  }

  /**
   * Test of convert method, of class FeaturesetTypeConverter.
   */
  @Test
  public void testConvertPolygonZToPolygon() {
    testConvert(ShapeType.PolygonZ, ShapeType.Polygon);
  }

  /**
   * Test of convert method, of class FeaturesetTypeConverter.
   */
  @Test
  public void testConvertPolygonMToPolygon() {
    testConvert(ShapeType.PolygonM, ShapeType.Polygon);
  }

  private void testConvert(ShapeType originalType, ShapeType resultType) {
    System.out.println("convert " + originalType + " to " + resultType);
    FeatureSet original = getRandom(originalType);
    ShapeType type = resultType;
    FeatureSet result = FeaturesetTypeConverter.convert(original, type);
    checkShapeType(result, type);
    checkCoordTypes(result, type);
    checkAttributes(original, result);
    checkCoords(original, result);
    assertEquals(original.getProjection(), result.getProjection());
    checkEnvelopes(original.getEnvelope(), result.getEnvelope());
  }

  private void checkEnvelopes(Envelope original, Envelope result) {
    assertEqualCoords(original.getMin(), result.getMin());
    assertEqualCoords(original.getMax(), result.getMax());
  }

  /**
   * This tests that the coordinates match to the greatest level supported by
   * both coordinate.
   *
   * @param original
   * @param result
   */
  private void assertEqualCoords(Coord original, Coord result) {
    assertEquals(original.getX(), result.getX(), 0);
    assertEquals(original.getX(), result.getY(), 0);
    if (original.hasZ() && result.hasZ()) {
      assertEquals(original.get(Coord.Index.Z), result.get(Coord.Index.Z), 0);
    }
    if (original.hasM() && result.hasM()) {
      assertEquals(original.get(Coord.Index.M), result.get(Coord.Index.M), 0);
    }
  }

  /**
   *
   * @param original
   * @param result
   */
  private void checkCoords(FeatureSet original, FeatureSet result) {
    if (original.getFeatureCategory() != ShapeCategory.Point && result.getFeatureCategory() == ShapeCategory.Point) {
      // feature numbering won't match exactly, so we need to check point by point.
      int iResultFeature = 0;
      for (int iFeature = 0; iFeature < original.getFeatures().size(); iFeature++) {
        Feature originalF = original.getFeatures().get(iFeature);
        for (Coord oc : originalF.getShape().getCoordinates()) {
          Feature resultF = result.getFeatures().get(iResultFeature);
          Coord rc = resultF.getShape().first();
          assertEquals(oc.getX(), rc.getX(), 0);
          assertEquals(oc.getX(), rc.getY(), 0);
          if (oc.hasZ() && rc.hasZ()) {
            assertEquals(oc.get(Coord.Index.Z), rc.get(Coord.Index.Z), 0);
          }
          if (oc.hasM() && rc.hasM()) {
            assertEquals(oc.get(Coord.Index.M), rc.get(Coord.Index.M), 0);
          }
          iResultFeature++;
        }
        return;
      }
    }

    for (int iFeature = 0; iFeature < original.getFeatures().size(); iFeature++) {
      Feature originalF = original.getFeatures().get(iFeature);
      Feature resultF = result.getFeatures().get(iFeature);
      List<Coord> origCoords = originalF.getShape().getCoordinates();
      List<Coord> resCoords = resultF.getShape().getCoordinates();
      assertEquals(origCoords.size(), resCoords.size());
      for (int iCoord = 0; iCoord < origCoords.size(); iCoord++) {
        Coord oc = origCoords.get(iCoord);
        Coord rc = resCoords.get(iCoord);
        assertEquals(oc.getX(), rc.getX(), 0);
        assertEquals(oc.getY(), rc.getY(), 0);
        if (oc.hasZ() && rc.hasZ()) {
          assertEquals(oc.get(Coord.Index.Z), rc.get(Coord.Index.Z), 0);
        }
        if (oc.hasM() && rc.hasM()) {
          assertEquals(oc.get(Coord.Index.M), rc.get(Coord.Index.M), 0);
        }

      }
    }
  }

  /**
   * Checks the attributes of the shapes to ensure the values were preserved.
   *
   * @param original The original FeatureSet
   * @param result the result FeatureSet
   */
  private void checkAttributes(FeatureSet original, FeatureSet result) {
    if (original.getFeatureCategory() != ShapeCategory.Point && result.getFeatureCategory() == ShapeCategory.Point) {
      // feature numbering won't match exactly, so we need to check point by point.
      int iResultFeature = 0;
      for (int iFeature = 0; iFeature < original.getFeatures().size(); iFeature++) {
        Feature originalF = original.getFeatures().get(iFeature);
        for (Coord c : originalF.getShape().getCoordinates()) {
          Feature resultF = result.getFeatures().get(iResultFeature);
          assertEquals(originalF.getAttributes().size(), resultF.getAttributes().size());
          for (Field f : original.getFields()) {
            assertEquals(originalF.getAttributes().get(f.getName()), resultF.getAttributes().get(f.getName()));
          }
          iResultFeature++;
        }
        return;
      }
    }

    for (int iFeature = 0; iFeature < original.getFeatures().size(); iFeature++) {
      Feature originalF = original.getFeatures().get(iFeature);
      Feature resultF = result.getFeatures().get(iFeature);
      assertEquals(originalF.getAttributes().size(), resultF.getAttributes().size());
      for (Field f : original.getFields()) {
        assertEquals(originalF.getAttributes().get(f.getName()), resultF.getAttributes().get(f.getName()));
      }
    }

  }

  /**
   * Asserts that the coord hasM, and hasZ of all the coordinates match the
   * specified type hasM and hasZ characteristics.
   *
   * @param result The FeatureSet with coordinates to test.
   * @param type The Shape type.
   */
  private void checkCoordTypes(FeatureSet result, ShapeType type) {
    for (Feature f : result.getFeatures()) {
      for (Coord c : f.getShape().getCoordinates()) {
        assertEquals(type.hasZ(), c.hasZ());
        assertEquals(type.hasM(), c.hasM());
      }
    }
  }

  /**
   * Asserts that the Shape type and the ShapeType of all the shapes matches the
   * expected type.
   *
   * @param result The result FeatureSet to test.
   * @param type The ShapeType expected.
   */
  private void checkShapeType(FeatureSet result, ShapeType type) {
    assertEquals(type, result.getDefaultShapeType());
    for (Feature f : result.getFeatures()) {
      assertEquals(type, f.getShape().getShapeType());
    }
  }

  /**
   * Gets a randomly created feature set to use for testing.
   *
   * @param type
   * @return
   */
  private FeatureSet getRandom(ShapeType type) {
    FeatureSet fs = new FeatureSet(type);
    fs.getFields().add(new Field("Name", FieldType.Character, 50));
    Random rnd = new Random(123);
    int numFeatures = rnd.nextInt(100) + 1;
    for (int iFeature = 0; iFeature < numFeatures; iFeature++) {
      int numParts = 1;
      if (type.getCategory() != ShapeCategory.Point) {
        numParts = rnd.nextInt(10) + 1;
      }
      Shape s = new Shape(type);
      Feature f = new Feature(s);
      for (int iPart = 0; iPart < numParts; iPart++) {
        Part part = new Part();
        int numCoords = rnd.nextInt(100) + 3;
        if (type.getCategory() == ShapeCategory.Point) {
          numCoords = 1;
        }
        for (int iCoord = 0; iCoord < numCoords; iCoord++) {
          double x = rnd.nextDouble() * 100;
          double y = rnd.nextDouble() * 100;
          double z = rnd.nextDouble() * 100;
          double m = rnd.nextDouble() * 100;
          CoordZ cz = new CoordZ(x, y, z, m);
          Coord c = FeaturesetTypeConverter.getCoord(cz, type);
          part.getCoordinates().add(c);
        }
        s.getParts().add(part);
      }
      s.calculateBounds();
      f.getAttributes().put("Name", "Shape" + iFeature);
      fs.getFeatures().add(f);
    }
    return fs;
  }

  /**
   * Test of getCoord method, of class FeaturesetTypeConverter.
   */
  @Test
  public void testGetCoordNullCoord() {
    System.out.println("getCoord Null Coord");
    Coord c = null;
    ShapeType type = ShapeType.PointM;
    boolean thrown = false;
    try {
      Coord result = FeaturesetTypeConverter.getCoord(c, type);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    assertEquals(true, thrown);

  }

  /**
   * Test of getCoord method, of class FeaturesetTypeConverter.
   */
  @Test
  public void testGetCoordNullType() {
    System.out.println("getCoord Null Type");
    Coord c = new CoordXY(100, 200);
    ShapeType type = null;
    boolean thrown = false;
    try {
      Coord result = FeaturesetTypeConverter.getCoord(c, type);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    assertEquals(true, thrown);

  }

  /**
   * Test of getCoord method, of class FeaturesetTypeConverter.
   */
  @Test
  public void testGetCoordXYtoM() {
    System.out.println("getCoord XY->M");
    Coord c = new CoordXY(100, 200);
    ShapeType type = ShapeType.PointM;
    Coord expResult = new CoordM(100, 200, 0);
    Coord result = FeaturesetTypeConverter.getCoord(c, type);
    assertEquals(expResult, result);
  }

  /**
   * Test of getCoord method, of class FeaturesetTypeConverter.
   */
  @Test
  public void testGetCoordXYtoZ() {
    System.out.println("getCoord XY->Z");
    Coord c = new CoordXY(100, 200);
    ShapeType type = ShapeType.PointZ;
    Coord expResult = new CoordZ(100, 200, 0, 0);
    Coord result = FeaturesetTypeConverter.getCoord(c, type);
    assertEquals(expResult, result);
  }

  /**
   * Test of getCoord method, of class FeaturesetTypeConverter.
   */
  @Test
  public void testGetCoordMtoZ() {
    System.out.println("getCoord M->Z");
    Coord c = new CoordM(100, 200, 300);
    ShapeType type = ShapeType.PointZ;
    Coord expResult = new CoordZ(100, 200, 0, 300);
    Coord result = FeaturesetTypeConverter.getCoord(c, type);
    assertEquals(expResult, result);
  }

  /**
   * Test of getCoord method, of class FeaturesetTypeConverter.
   */
  @Test
  public void testGetCoordZtoM() {
    System.out.println("getCoord Z->M");
    Coord c = new CoordZ(100, 200, 300, 400);
    ShapeType type = ShapeType.PointM;
    Coord expResult = new CoordM(100, 200, 400);
    Coord result = FeaturesetTypeConverter.getCoord(c, type);
    assertEquals(expResult, result);
  }

  /**
   * Test of getCoord method, of class FeaturesetTypeConverter.
   */
  @Test
  public void testGetCoordZtoXY() {
    System.out.println("getCoord Z->XY");
    Coord c = new CoordZ(100, 200, 300, 400);
    ShapeType type = ShapeType.Point;
    Coord expResult = new CoordXY(100, 200);
    Coord result = FeaturesetTypeConverter.getCoord(c, type);
    assertEquals(expResult, result);
  }

  /**
   * Test of getCoord method, of class FeaturesetTypeConverter.
   */
  @Test
  public void testGetCoordMtoXY() {
    System.out.println("getCoord M->XY");
    Coord c = new CoordM(100, 200, 300);
    ShapeType type = ShapeType.Point;
    Coord expResult = new CoordXY(100, 200);
    Coord result = FeaturesetTypeConverter.getCoord(c, type);
    assertEquals(expResult, result);
  }

}
