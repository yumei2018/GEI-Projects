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

import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeCategory;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class GeoJsonUtils {

  /**
   * Private constructor for utilities class.
   */
  private GeoJsonUtils() {

  }

  /**
   * Given a consistent geometry category (points/multipoints/lines/polygons),
   * this will allow the creation of a single FeatureSet from a list of
   * coordinates. This will not define any of the field objects, which must
   * later be configured.
   *
   * @param category The category, like Point, MultiPoint, Line or Polygon.
   * @param coordinateArrays The list of string representations of the
   * coordinate arrays.
   * @param projection The ProjectionInfo representing the coordinate system for
   * the data.
   * @return A FeatureSet that matches the category and has shape
   * representations for each of the listed coordinate arrays.
   */
  public static FeatureSet fromGeoJson(ShapeCategory category,
      List<String> coordinateArrays, ProjectionInfo projection) {
    GeoFeatureCollection collection = new GeoFeatureCollection(category,
        coordinateArrays, projection);
    FeatureSet result = new FeatureSet();
    for (GeoFeature geoFeature : collection.getFeatures()) {
      GeoGeometry geom = geoFeature.getGeometry();
      GeoJsonShapeConverter converter = new GeoJsonShapeConverter();
      Shape shape = converter.getShape(category, geom);
      Feature f = new Feature();
      f.setShape(shape);
      result.getFeatures().add(f);
    }
    return result;
  }

  /**
   * Given a properly formatted GeoJson input string, this will return the list
   * of FeatureSet objects, divided by shape type.
   *
   * @param geoJsonFeatureCollection The GeoJson string representation of the
   * FeatureCollection.
   * @return A list of all the created FeatureSet objects.
   */
  public static List<FeatureSet> fromGeoJson(String geoJsonFeatureCollection) {
    GeoFeatureCollection collection = new GeoFeatureCollection(geoJsonFeatureCollection);
    return collection.toFeatureSets();
  }

  /**
   * Given an input stream containing the string JSON, this will write the zipped shapefile content to the
   * output stream.
   * @param geoJsonFeatureCollection
   */
  public static void geoJsonToZippedShapes(InputStream geoJsonFeatureCollection,
      OutputStream zippedFiles) {

  }

}
