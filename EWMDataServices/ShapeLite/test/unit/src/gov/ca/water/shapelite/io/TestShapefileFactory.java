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
package gov.ca.water.shapelite.io;

import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.FieldType;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.coordinate.CoordFactory;
import gov.ca.water.shapelite.coordinate.CoordM;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class TestShapefileFactory {

  /**
   * Creates a Feature Set.
   *
   * @param type The type of the feature set to create.
   * @return The newly created random FeatureSet for testing.
   */
  public static FeatureSet createFeatureSet(ShapeType type) {

    switch (type) {
      case Point:
        return createPointXY();
      case PointM:
        return createPointM();
      case PointZ:
        return createPointZ();
      case MultiPoint:
        return createMultipointXY();
      case MultiPointM:
        return createMultipointM();
      case MultiPointZ:
        return createMultipointZ();
      case PolyLine:
        return createPolylineXY();
      case PolyLineM:
        return createPolylineM();
      case PolyLineZ:
        return createPolylineZ();
      case Polygon:
        return createPolygonXY();
      case PolygonM:
        return createPolygonM();
      case PolygonZ:
        return createPolygonZ();
      default:
        return new FeatureSet();
    }
  }

  /**
   * Creates a point feature.
   *
   * @return A FeatureSet.
   */
  public static FeatureSet createPointXY() {
    List<CoordXY> coords = CoordFactory.randomXY(100);
    FeatureSet result = new FeatureSet();
    result.getFields().add(new Field("FID", 3, 0));
    result.calculateFieldOffsets();
    int fid = 0;
    for (CoordXY coord : coords) {
      Shape shape = new Shape(coord);
      Feature f = new Feature(shape);
      f.getAttributes().put("ID", Integer.toString(fid));
      result.getFeatures().add(f);
      fid++;
    }
    return result;
  }

  /**
   * Creates a point feature.
   *
   * @return A FeatureSet.
   */
  public static FeatureSet createPointM() {
    List<CoordM> coords = CoordFactory.randomM(100);
    FeatureSet result = new FeatureSet();
    result.getFields().add(new Field("FID", 3, 0));
    result.calculateFieldOffsets();
    int fid = 0;
    for (CoordM coord : coords) {
      Shape shape = new Shape(coord);
      Feature f = new Feature(shape);
      f.getAttributes().put("ID", Integer.toString(fid));
      result.getFeatures().add(f);
      fid++;
    }
    return result;
  }

  /**
   * Creates a point feature.
   *
   * @return A FeatureSet.
   */
  public static FeatureSet createPointZ() {
    List<CoordZ> coords = CoordFactory.randomZ(100);
    FeatureSet result = new FeatureSet();
    result.getFields().add(new Field("FID", 3, 0));
    result.calculateFieldOffsets();
    int fid = 0;
    for (CoordZ coord : coords) {
      Shape shape = new Shape(coord);
      Feature f = new Feature(shape);
      f.getAttributes().put("ID", Integer.toString(fid));
      result.getFeatures().add(f);
      fid++;
    }
    return result;
  }

  /**
   * Creates a point feature.
   *
   * @return A FeatureSet.
   */
  public static FeatureSet createMultipointXY() {

    FeatureSet result = new FeatureSet();
    result.getFields().add(new Field("FID", 3, 0));
    result.calculateFieldOffsets();
    Random rnd = new Random();
    for (int fid = 0; fid < 100; fid++) {
      int count = rnd.nextInt(100);
      List<CoordXY> coords = CoordFactory.randomXY(count);
      Part part = new Part(coords);
      Shape shape = new Shape(ShapeType.MultiPoint);
      shape.getParts().add(part);
      shape.calculateBounds();
      Feature f = new Feature(shape);
      f.getAttributes().put("ID", Integer.toString(fid));
      result.getFeatures().add(f);

    }
    return result;
  }

  /**
   * Creates a point feature.
   *
   * @return A FeatureSet.
   */
  public static FeatureSet createMultipointM() {

    FeatureSet result = new FeatureSet();
    result.getFields().add(new Field("FID", 3, 0));
    result.calculateFieldOffsets();
    Random rnd = new Random();
    for (int fid = 0; fid < 100; fid++) {
      int count = rnd.nextInt(100);
      List<CoordM> coords = CoordFactory.randomM(count);
      Part part = new Part(coords);
      Shape shape = new Shape(ShapeType.MultiPointM);
      shape.getParts().add(part);
      shape.calculateBounds();
      Feature f = new Feature(shape);
      f.getAttributes().put("ID", Integer.toString(fid));
      result.getFeatures().add(f);
    }
    return result;
  }

  /**
   * Creates a point feature.
   *
   * @return A FeatureSet.
   */
  public static FeatureSet createMultipointZ() {

    FeatureSet result = new FeatureSet();
    result.getFields().add(new Field("FID", 3, 0));
    result.calculateFieldOffsets();
    Random rnd = new Random();
    for (int fid = 0; fid < 100; fid++) {
      int count = rnd.nextInt(100);
      List<CoordZ> coords = CoordFactory.randomZ(count);
      Part part = new Part(coords);
      Shape shape = new Shape(ShapeType.MultiPointZ);
      shape.getParts().add(part);
      shape.calculateBounds();
      Feature f = new Feature(shape);
      f.getAttributes().put("ID", Integer.toString(fid));
      result.getFeatures().add(f);
    }
    return result;
  }

  /**
   * Creates a point feature.
   *
   * @return A FeatureSet.
   */
  public static FeatureSet createPolylineXY() {

    FeatureSet result = new FeatureSet();
    result.getFields().add(new Field("FID", 3, 0));
    result.calculateFieldOffsets();
    Random rnd = new Random();
    for (int fid = 0; fid < 100; fid++) {
      int count = rnd.nextInt(100);
      List<CoordXY> coords = CoordFactory.randomXY(count);
      Part part = new Part(coords);
      Shape shape = new Shape(ShapeType.PolyLine);
      shape.getParts().add(part);
      shape.calculateBounds();
      Feature f = new Feature(shape);
      f.getAttributes().put("ID", Integer.toString(fid));
      result.getFeatures().add(f);
    }
    return result;
  }

  /**
   * Creates a point feature.
   *
   * @return A FeatureSet.
   */
  public static FeatureSet createPolylineM() {

    FeatureSet result = new FeatureSet();
    result.getFields().add(new Field("FID", 3, 0));
    result.calculateFieldOffsets();
    Random rnd = new Random();
    for (int fid = 0; fid < 100; fid++) {
      int count = rnd.nextInt(100);
      List<CoordM> coords = CoordFactory.randomM(count);
      Part part = new Part(coords);
      Shape shape = new Shape(ShapeType.PolyLineM);
      shape.getParts().add(part);
      shape.calculateBounds();
      Feature f = new Feature(shape);
      f.getAttributes().put("ID", Integer.toString(fid));
      result.getFeatures().add(f);
    }
    return result;
  }

  /**
   * Creates a point feature.
   *
   * @return A FeatureSet.
   */
  public static FeatureSet createPolylineZ() {

    FeatureSet result = new FeatureSet();
    result.getFields().add(new Field("FID", 3, 0));
    result.calculateFieldOffsets();
    Random rnd = new Random();
    for (int fid = 0; fid < 100; fid++) {
      int count = rnd.nextInt(100);
      List<CoordZ> coords = CoordFactory.randomZ(count);
      Part part = new Part(coords);
      Shape shape = new Shape(ShapeType.PolyLineZ);
      shape.getParts().add(part);
      shape.calculateBounds();
      Feature f = new Feature(shape);
      f.getAttributes().put("ID", Integer.toString(fid));
      result.getFeatures().add(f);
    }
    return result;
  }

  /**
   * Creates a point feature.
   *
   * @return A FeatureSet.
   */
  public static FeatureSet createPolygonXY() {

    FeatureSet result = new FeatureSet();
    result.getFields().add(new Field("FID", 3, 0));
    result.calculateFieldOffsets();
    Random rnd = new Random();
    for (int fid = 0; fid < 100; fid++) {
      int count = rnd.nextInt(100);
      List<CoordXY> coords = CoordFactory.randomRadiusXY(CoordFactory.randomXY(), count, 20);
      Part part = new Part(coords);
      part.setClosed(true);
      Shape shape = new Shape(ShapeType.Polygon);
      shape.getParts().add(part);
      shape.calculateBounds();
      Feature f = new Feature(shape);
      f.getAttributes().put("ID", Integer.toString(fid));
      result.getFeatures().add(f);
    }
    return result;
  }

  /**
   * Creates a point feature.
   *
   * @return A FeatureSet.
   */
  public static FeatureSet createPolygonM() {

    FeatureSet result = new FeatureSet();
    result.getFields().add(new Field("FID", 3, 0));
    result.calculateFieldOffsets();
    Random rnd = new Random();
    for (int fid = 0; fid < 100; fid++) {
      int count = rnd.nextInt(100);
      List<CoordM> coords = CoordFactory.randomRadiusM(CoordFactory.randomXY(), count, 20);
      Part part = new Part(coords);
      part.setClosed(true);
      Shape shape = new Shape(ShapeType.PolygonM);
      shape.getParts().add(part);
      shape.calculateBounds();
      Feature f = new Feature(shape);
      f.getAttributes().put("ID", Integer.toString(fid));
      result.getFeatures().add(f);
    }
    return result;
  }

  /**
   * Creates a point feature.
   *
   * @return A FeatureSet.
   */
  public static FeatureSet createPolygonZ() {

    FeatureSet result = new FeatureSet();
    result.getFields().add(new Field("FID", 3, 0));
    result.calculateFieldOffsets();
    Random rnd = new Random();
    for (int fid = 0; fid < 100; fid++) {
      int count = rnd.nextInt(100);
      List<CoordZ> coords = CoordFactory.randomRadiusZ(CoordFactory.randomXY(), count, 20);
      Part part = new Part(coords);
      part.setClosed(true);
      Shape shape = new Shape(ShapeType.PolygonZ);
      shape.getParts().add(part);
      shape.calculateBounds();
      Feature f = new Feature(shape);
      f.getAttributes().put("ID", Integer.toString(fid));
      result.getFeatures().add(f);
    }
    return result;
  }
}
