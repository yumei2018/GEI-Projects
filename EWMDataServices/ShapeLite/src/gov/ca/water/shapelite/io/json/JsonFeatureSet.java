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
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 * @param <T> The type of feature set.
 */
public class JsonFeatureSet<T extends JsonGeometry<T>> implements Serializable {

  /**
   * The version ID for this serializable object. Increment this if the fields
   * are modified.
   */
  static final long serialVersionUID = 1L;

  /**
   * The field to use for labeling.
   */
  private String displayFieldName;
  /**
   * The String aliases to use for fields.
   */
  private HashMap<String, String> fieldAliases;
  /**
   * The geometry type of the feature set.
   */
  private EsriGeometryType geometryType;
  /**
   * The list of JsonFields defining the attribute schema.
   */
  private List<JsonField> fields;
  /**
   * The list of features.
   */
  private List<JsonFeature<T>> features;
  /**
   * The spatialReference.
   */
  private SpatialReference spatialReference;
  
  /**
   * Creates a new instance of the JSonFeatureSet.
   */
  public JsonFeatureSet() {
    fieldAliases = new HashMap<>();
    fields = new ArrayList<>();
    features = new ArrayList<>();
  }

  /**
   * Generates a deep copy of this JsonFeatureSet.
   *
   * @return A deep copy of this JsonFeatureSet.
   */
  public final JsonFeatureSet<T> copy() {
    JsonFeatureSet<T> result = new JsonFeatureSet<>();
    result.copyProperties(this);
    return result;
  }

  /**
   * Copies the properties from the other featureset to this object.
   *
   * @param other The other feature set.
   */
  public final void copyProperties(JsonFeatureSet<T> other) {
    this.displayFieldName = other.displayFieldName;
    this.geometryType = other.geometryType;
    fieldAliases.clear();
    for (Entry<String, String> entry : other.getFieldAliases().entrySet()) {
      fieldAliases.put(entry.getKey(), entry.getValue());
    }
    fields.clear();
    for (JsonField otherField : other.getFields()) {
      fields.add(otherField.copy());
    }
    features.clear();
    for (JsonFeature<T> feature : other.getFeatures()) {
      features.add(feature.copy());
    }

  }

  /**
   * Creates a feature set from this serializable object.
   *
   * @param hasM Boolean, true if the features should have M values.
   * @param hasZ Boolean, true if the features should have Z values.
   * @return A FeatureSet.
   */
  public final FeatureSet toFeatureSet(boolean hasM, boolean hasZ) {
    FeatureSet result = new FeatureSet();
    ShapeType shapeType = getFeatureType(hasM, hasZ);
    for (JsonField jsonField : fields) {
      Optional<Field> maybeField = jsonField.toField();
      if (maybeField.isPresent()) {
        result.getFields().add(maybeField.get());
      }
    }
    String wkt = this.getSpatialReference().getWkt();
    Optional<ProjectionInfo> maybeProj = ProjectionInfo.fromEsriStringOpt(wkt);
    if (!maybeProj.isPresent()) {
      int wkid = this.getSpatialReference().getWkid();
      maybeProj = ProjectionInfo.fromEpsgCode(wkid);
      if (!maybeProj.isPresent()) {
        wkid = this.getSpatialReference().getLatestWkid();
        maybeProj = ProjectionInfo.fromEpsgCode(wkid);

      }
    }

    for (JsonFeature<T> jsonFeature : features) {
      Feature feature = jsonFeature.toFeature(shapeType, hasM, hasZ);
      result.getFeatures().add(feature);
      if (maybeProj.isPresent()) {
        result.setProjectionFrom(maybeProj.get());
      }
    }

    return result;
  }

  /**
   * Gets the ShapeType based off of the esriGeometry enumeration. This will be
   * the PointZ form because the geometryType does not specify if z or m values
   * exist.
   *
   * @param hasM Boolean, true if the feature has M values.
   * @param hasZ Boolean, true if the feature has Z values.
   * @return The ShapeType.
   */
  final ShapeType getFeatureType(boolean hasM, boolean hasZ) {
    switch (geometryType) {
      case esriGeometryNull:
        return ShapeType.NullShape;
      case esriGeometryPoint:
        if (hasZ) {
          return ShapeType.PointZ;
        }
        if (hasM) {
          return ShapeType.PointM;
        }
        return ShapeType.Point;
      case esriGeometryMultipoint:
        if (hasZ) {
          return ShapeType.MultiPointZ;
        }
        if (hasM) {
          return ShapeType.MultiPointM;
        }
        return ShapeType.MultiPoint;
      case esriGeometryLine:
        if (hasZ) {
          return ShapeType.PolyLineZ;
        }
        if (hasM) {
          return ShapeType.PolyLineM;
        }
        return ShapeType.PolyLine;
      case esriGeometryPath:
        if (hasZ) {
          return ShapeType.PolyLineZ;
        }
        if (hasM) {
          return ShapeType.PolyLineM;
        }
        return ShapeType.PolyLine;
      case esriGeometryPolyline:
        if (hasZ) {
          return ShapeType.PolyLineZ;
        }
        if (hasM) {
          return ShapeType.PolyLineM;
        }
        return ShapeType.PolyLine;
      case esriGeometryRing:
        if (hasZ) {
          return ShapeType.PolyLineZ;
        }
        if (hasM) {
          return ShapeType.PolyLineM;
        }
        return ShapeType.PolyLine;
      case esriGeometryPolygon:
        if (hasZ) {
          return ShapeType.PolygonZ;
        }
        if (hasM) {
          return ShapeType.PolygonM;
        }
        return ShapeType.Polygon;
      default:
        return ShapeType.NullShape;
    }
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the field to use for labeling.
   *
   * @return the displayFieldName
   */
  public final String getDisplayFieldName() {
    return displayFieldName;
  }

  /**
   * Sets the field to use for labeling.
   *
   * @param displayFieldName the displayFieldName to set
   */
  public final void setDisplayFieldName(String displayFieldName) {
    this.displayFieldName = displayFieldName;
  }

  /**
   * Gets the String aliases to use for fields.
   *
   * @return the fieldAliases
   */
  public final HashMap<String, String> getFieldAliases() {
    return fieldAliases;
  }

  /**
   * Sets the String aliases to use for fields.
   *
   * @param fieldAliases the fieldAliases to set
   */
  public final void setFieldAliases(HashMap<String, String> fieldAliases) {
    this.fieldAliases = fieldAliases;
  }

  /**
   * Gets the geometry type of the feature set.
   *
   * @return the geometryType
   */
  public final EsriGeometryType getGeometryType() {
    return geometryType;
  }

  /**
   * Sets the geometry type of the feature set.
   *
   * @param geometryType the geometryType to set
   */
  public final void setGeometryType(EsriGeometryType geometryType) {
    this.geometryType = geometryType;
  }

  /**
   * Gets the list of all the fields in the feature set.
   *
   * @return the fields
   */
  public final List<JsonField> getFields() {
    return fields;
  }

  /**
   * Sets the list of all the fields in the feature set.
   *
   * @param fields the fields to set
   */
  public final void setFields(List<JsonField> fields) {
    this.fields = fields;
  }

  /**
   * Gets the list of features.
   *
   * @return the features
   */
  public final List<JsonFeature<T>> getFeatures() {
    return features;
  }

  /**
   * Sets the list of features.
   *
   * @param features the features to set
   */
  public final void setFeatures(List<JsonFeature<T>> features) {
    this.features = features;
  }

  public final SpatialReference getSpatialReference() {
    return spatialReference;
  }

  public final JsonFeatureSet setSpatialReference(SpatialReference spatialReference) {
    this.spatialReference = spatialReference;
    return this;
  }
  // </editor-fold>
}
