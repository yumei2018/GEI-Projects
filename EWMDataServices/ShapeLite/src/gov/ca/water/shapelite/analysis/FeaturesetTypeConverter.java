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
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.coordinate.CoordM;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class FeaturesetTypeConverter {

  /**
   * Private constructor for utils class.
   */
  private FeaturesetTypeConverter() {

  }

  /**
   * Given a featureset of one type, this will convert it to a featureset of the
   * specified type. M or Z values may be lost if going to XY types. Converting
   * to Points will drastically increase the number of features.
   *
   * @param featureSet The FeatureSet to convert.
   * @param type The ShapeType to convert.
   * @return The FeatureSet that was created.
   */
  public static FeatureSet convert(@NonNull FeatureSet featureSet, @NonNull ShapeType type) {
    if (featureSet == null) {
      throw new IllegalArgumentException("Parameter featureSet in FeatureTypeCnoverter.convert cannot be null.");
    }
    if (type == null) {
      throw new IllegalArgumentException("Parameter type in FeatureTypeCnoverter.convert cannot be null.");
    }

    FeatureSet result = featureSet.copy();
    result.getFeatures().clear();
    result.setDefaultShapeType(type);
    switch (type.getCategory()) {
      case Point:
        addPoints(featureSet, result);
        break;
      case Null:
        break;
      default:
        addFeatures(featureSet, result);
    }
    return result;
  }

  /**
   * Creates point features based on the features in source.
   *
   * @param source The source features.
   * @param result The result.
   */
  private static void addPoints(FeatureSet source, FeatureSet result) {
    ShapeType type = result.getDefaultShapeType();
    for (Feature feature : source.getFeatures()) {
      for (Coord c : feature.getShape().getCoordinates()) {
        Coord coord = getCoord(c, type);
        Shape s = new Shape(coord);
        Feature f = new Feature(s);
        f.setAttributesFrom(feature.getAttributes());
        result.getFeatures().add(f);
      }
    }
  }

  /**
   * Creates point features based on the features in source.
   *
   * @param source The source features.
   * @param result The result.
   */
  private static void addFeatures(FeatureSet source, FeatureSet result) {
    ShapeType type = result.getDefaultShapeType();
    for (Feature feature : source.getFeatures()) {
      Feature f = new Feature();
      Shape s = new Shape(type);
      for (Part part : feature.getShape().getParts()) {
        Part resultPart = new Part();
        for (Coord c : part.getCoordinates()) {
          resultPart.getCoordinates().add(getCoord(c, type));
        }
        s.getParts().add(resultPart);
      }
      f.setShape(s);
      f.setAttributesFrom(feature.getAttributes());
      result.getFeatures().add(f);
    }
  }

  /**
   * Given a coordinate of one type, this will create the appropriate coordinate
   * of a different type.
   *
   * @param c The Coord.
   * @param type The ShapeType to convert.
   * @return The Coord of the resulting type.
   */
  public static Coord getCoord(@NonNull Coord c, @NonNull ShapeType type) {
    if (c == null) {
      throw new IllegalArgumentException("Coord c cannot be null.");
    }
    if (type == null) {
      throw new IllegalArgumentException("Type cannot be null.");
    }
    Coord result;
    if (type.hasZ()) {
      CoordZ cz = new CoordZ();
      if (c.hasZ()) {
        cz.setZ(c.get(Coord.Index.Z));
      }
      if (c.hasM()) {
        cz.setM(c.get(Coord.Index.M));
      }
      result = cz;
    } else if (type.hasM()) {
      CoordM cm = new CoordM();
      if (c.hasM()) {
        cm.setM(c.get(Coord.Index.M));
      }
      result = cm;
    } else {
      result = new CoordXY();
    }
    result.setX(c.getX());
    result.setY(c.getY());
    return result;
  }
  
  //<editor-fold defaultstate="collapsed" desc="Convert">
  public static Feature convert(Feature feature, ShapeType type) {
    Feature result = null;
    
    if (feature == null) {
      throw new IllegalArgumentException("Parameter feature in FeatureTypeCnoverter.convert cannot be null.");
    }
    if (type == null) {
      throw new IllegalArgumentException("Parameter type in FeatureTypeCnoverter.convert cannot be null.");
    }

    Shape convertedShape = new Shape(type);
    Part convertedPart = null;
    for (Part part : feature.getShape().getParts()) {
       convertedPart = new Part();
      for (Coord c : part.getCoordinates()) {
        convertedPart.getCoordinates().add(getCoord(c, type));
      }
      convertedShape.getParts().add(convertedPart);
    }
    feature.setShape(convertedShape);
    
    return feature;
  }
  //</editor-fold>
}
