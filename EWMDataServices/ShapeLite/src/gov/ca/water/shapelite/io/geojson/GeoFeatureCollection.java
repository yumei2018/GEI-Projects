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

import com.google.gson.Gson;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.ShapeCategory;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.projection.Projections;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GeoFeatureCollection extends GeoObject {

  /**
   * The version ID for this serializable object. Increment this if the fields
   * are modified.
   */
  static final long serialVersionUID = 1L;

  /**
   * The list of features in the collection.
   */
  private List<GeoFeature> features;

  /**
   * A coordinate reference system. The property "name" stores the urn code like
   *
   */
  private GeoObject crs;

  /**
   * A bounding box that is optionally present in JSON format. All the lower
   * bounds for all dimensions are listed, followed by upper bounds, like
   * [xmin,ymin,xmax,ymax].
   */
  private double[] bbox;

  /**
   * Creates a new instance of a feature collection.
   */
  public GeoFeatureCollection() {
    features = new ArrayList<>();
  }

  /**
   * Creates a new instance of a GeoFeatureCollection for representing GeoJson
   * objects.
   *
   * @param featureSet The FeatureSet.
   */
  public GeoFeatureCollection(FeatureSet featureSet) {

  }

  /**
   * Creates a new instance of a GeoFeatureCollection based on the specified
   * Json string.
   *
   * @param geoJsonFeatureCollection The feature collection string.
   */
  public GeoFeatureCollection(String geoJsonFeatureCollection) {
    Gson gson = new Gson();
    GeoFeatureCollection internal = gson.fromJson(geoJsonFeatureCollection,
        GeoFeatureCollection.class);
    this.features = internal.getFeatures();
    this.crs = internal.getCrs();
  }

  /**
   * Creates a new GeoFeature collection, where all the geometries are the same
   * type, and can be parsed from the string form of their coordinate arrays.
   *
   * @param type The geometry type for all the features.
   * @param coordinateArrays The list of string arrays.
   * @param projection The projection for all the features.
   */
  public GeoFeatureCollection(ShapeCategory type,
      List<String> coordinateArrays, ProjectionInfo projection) {

    crs = new GeoObject();
    String proj = "urn:ogc:def:crs:EPSG::" + projection.getEpsgCode();
    crs.getProperties().put("name", proj);
    for (String array : coordinateArrays) {
      GeoFeature feature = new GeoFeature();
      GeoGeometry geom = new GeoGeometry();
      geom.setCoordinates(array);
      Optional<GeometryType> maybeGeoType = GeometryType.from(type,
          geom.getDimension());
      if (maybeGeoType.isPresent()) {
        geom.setType(maybeGeoType.get());
      }
      feature.setGeometry(geom);
      this.getFeatures().add(feature);
    }
  }

  /**
   * This creates a feature collection with no crs using only the specified
   * geometry type and the list of coordinate arrays.
   *
   * @param type The GeometryType.
   * @param coordinateArrays The coordinate arrays.
   */
  public GeoFeatureCollection(GeometryType type,
      List<String> coordinateArrays) {
    for (String array : coordinateArrays) {
      GeoFeature feature = new GeoFeature();
      GeoGeometry geom = new GeoGeometry();
      geom.setType(type);
      geom.setCoordinates(array);
      feature.setGeometry(geom);
      this.getFeatures().add(feature);
    }
  }

  /**
   * Creates up to three FeatureSets, one for Points, one for Lines and one for
   * Polygons.
   *
   * @return The list of FeatureSet objects.
   */
  public final List<FeatureSet> toFeatureSets() {
    List<FeatureSet> result = new ArrayList<>();
    FeatureSet point = new FeatureSet();
    FeatureSet multiPoint = new FeatureSet();
    FeatureSet line = new FeatureSet();
    FeatureSet polygon = new FeatureSet();
    GeoJsonShapeConverter converter = new GeoJsonShapeConverter();
    for (GeoFeature feature : features) {
      List<Shape> shapes = converter.getShapes(feature.getGeometry());
      for (Shape shape : shapes) {
        Feature f = new Feature();
        f.setShape(shape);
        switch (shape.getShapeType().getCategory()) {
          case Point:
            point.getFeatures().add(f);
            break;
          case MultiPoint:
            multiPoint.getFeatures().add(f);
            break;
          case PolyLine:
            line.getFeatures().add(f);
            break;
          case Polygon:
            polygon.getFeatures().add(f);
            break;
          default:
          // do nothing;
        }
      }
    }
    if (this.getCrs() != null && this.getCrs().getProperties().containsKey("name")) {
      Object crsObj = this.getCrs().getProperties().get("name");
      if (crsObj instanceof String) {
        String urnEPSG = (String) crsObj;
        String[] parts = urnEPSG.split(":");
        if (parts.length > 0) {
          String epsg = parts[parts.length - 1];
          int code = Integer.parseInt(epsg);
          if (Projections.getEpsgEsri().containsKey(code)) {
            String proj = Projections.getEpsgEsri().get(code);
            multiPoint.setProjectionESRI(proj);
            point.setProjectionESRI(proj);
            line.setProjectionESRI(proj);
            polygon.setProjectionESRI(proj);
          }
        }

      }

    }

    return result;
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the list of features.
   *
   * @return the features
   */
  public final List<GeoFeature> getFeatures() {
    return features;
  }

  /**
   * Sets the list of features.
   *
   * @param features the features to set
   */
  public final void setFeatures(List<GeoFeature> features) {
    this.features = features;
  }

  /**
   * Gets the String form of the coordinate reference system.
   *
   * @return the crs
   */
  public final GeoObject getCrs() {
    return crs;
  }

  /**
   * Sets the String form of the coordinate reference system.
   *
   * @param crs the crs to set
   */
  public final void setCrs(GeoObject crs) {
    this.crs = crs;
  }

  // </editor-fold>
  /**
   * Gets A bounding box that is optionally present in JSON format. All the
   * lower bounds for all dimensions are listed, followed by upper bounds, like
   * [xmin,ymin,xmax,ymax].
   *
   * @return the bbox
   */
  public final double[] getBbox() {
    return bbox;
  }

  /**
   * Sets A bounding box that is optionally present in JSON format. All the
   * lower bounds for all dimensions are listed, followed by upper bounds, like
   * [xmin,ymin,xmax,ymax].
   *
   * @param bbox the bbox to set
   */
  public final void setBbox(double[] bbox) {
    this.bbox = bbox;
  }
}
