/*
 * The MIT License
 *
 * Copyright 2012 hdunsford.
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
package gov.ca.water.shapelite.data.dataset;

import gov.ca.water.shapelite.symbology.PointSymbolizer;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.data.FeatureMarkerDelegate;
import static gov.ca.water.shapelite.data.dataset.LineDataset.WGS84;
import gov.ca.water.shapelite.data.marker.LineMarker;
import gov.ca.water.shapelite.data.marker.PointMarker;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.util.ArrayList;
import java.util.List;

/**
 * This class expands DatasetMarkerBase to hosts a set of PointMarker (points)
 * in memory that represent points on the map that can be labeled.
 *
 * @author Harold A. Dunsford Jr. Ph.D./kprins
 */
public class PointDataset extends FeatureDataset<PointMarker, PointSymbolizer> {

  //<editor-fold defaultstate="collapsed" desc="Static Method">
  /**
   * <p>
   * A Static Method for converting a List of Shapes and its associates array of
   * PointMarker Names in a list of Markers (using the layer's default
   * Symbolizer).</p>
   * <p>
   * <b>NOTE:</b> It returns an empty list if shapes = null|empty and set the
   * PointMarker Names to null if the shape index exceeds the names array
   * length.</p>
   *
   * @param shapes the list of shapes
   * @param names the array of names.
   * @return a list Markers (can be empty)
   */
  public static List<PointMarker> getMarkersFromShapes(List<Shape> shapes,
      String[] names) {
    List<PointMarker> result = new ArrayList<>();
    Shape shape;
    String name;
    if ((shapes != null) && (!shapes.isEmpty())) {
      for (int iShp = 0; iShp < shapes.size(); iShp++) {
        shape = shapes.get(iShp);
        if (shape != null && !shape.isEmpty()) {
          name = null;
          if (names != null && iShp < names.length) {
            name = names[iShp];
          }
          result.add(new PointMarker(shape, name));
        }
      }
    }
    return result;
  }

  /**
   * <p>
   * A Static method for initiating a List of MarkerPaths from the features in
   * the specified featureSet using the defined FeatureMarkerDelegate to
   * initiate the marker based on the Shape properties and feature
   * attributes.</p>
   * <p>
   * <b>NOTE:</b> It returns an empty list if featureSet = null|empty and
   * initiate markers with no properties - other its shape set if the delegate =
   * null.</p>
   *
   * @param featureSet the featureSet containing the feature and attributes
   * @param delegate the FeatureMarkerDelegate to initiate the marker.
   * @return a list of MarkerPaths (can be empty).
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there is a
   * problem projecting the points to WGS84.
   */
  public static List<PointMarker> getMarkersFromFeatureSet(FeatureSet featureSet,
      FeatureMarkerDelegate<PointMarker, PointSymbolizer> delegate) throws ProjectionException {
    List<PointMarker> result = new ArrayList<>();
    List<Feature> features = featureSet.getFeatures();
    ProjectionInfo sourceProjection = featureSet.getProjection();
    ProjectionInfo wgs84 = Projections.getGeographic().getWorld().getWGS1984();
    boolean sameProjection = wgs84.equals(sourceProjection);
    if (features != null) {
      for (Feature feature : features) {
        PointMarker marker;
        if (delegate == null) {
          if (sameProjection) {
            marker = new PointMarker(feature, null);
          } else {
            Shape shape = Reproject.reprojectShape(feature.getShape(),
                sourceProjection, wgs84);
            Feature ft = new Feature(shape);
            ft.copyAttributes(feature.getAttributes());
            marker = new PointMarker(ft, null);
          }
        } else {
          marker = delegate.getMarker(feature);
        }
        if (marker != null) {
          result.add(marker);
        }
      }
    }
    return result;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Creates a new empty set of markers.
   */
  public PointDataset() {
    super();
  }

  /**
   * Creates a new DatasetMarker from the specified list of markers.
   *
   * @param markers The list of markers for this dataset to use.
   */
  public PointDataset(List<PointMarker> markers) {
    super(markers);
  }

  /**
   * Creates a dataset with a preset list of features and a name field.
   *
   * @param featureSet The original featureset from any projection.
   * @param nameField The String name for the entire dataset.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * a problem projecting to WGS84.
   */
  public PointDataset(FeatureSet featureSet, String nameField)
      throws ProjectionException {
    List<PointMarker> paths = new ArrayList<>();
    boolean sameProjection = WGS84.equals(featureSet.getProjection());
    if (!sameProjection) {
      featureSet.reproject(WGS84);
    }
    for (Feature f : featureSet.getFeatures()) {
      PointMarker mp = new PointMarker(f, f.getAttributes().get(nameField));
      paths.add(mp);
    }
    if ((!paths.isEmpty())) {
      this.setMarkersFrom(paths);
      this.calculateEnvelope();
    }
    super.setFieldsFrom(featureSet.getFields());

  }
  //</editor-fold>
}
