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

import gov.ca.water.shapelite.symbology.LineSymbolizer;
import java.util.ArrayList;
import java.util.List;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Nullable;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.data.FeatureMarkerDelegate;
import gov.ca.water.shapelite.data.marker.LineMarker;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;

/**
 * This class expands DatasetMarkerBase to hosts a set of MarkerPath (polyline
 * shapes) that each also have a name and can serve as markers on the map.
 *
 * @author Harold A. Dunsford Jr. Ph.D./kprins
 */
public class LineDataset
    extends FeatureDataset<LineMarker, LineSymbolizer> {

  /**
   * WGS84 projection.
   */
  public static final ProjectionInfo WGS84
      = Projections.getGeographic().getWorld().getWGS1984();

  //<editor-fold defaultstate="collapsed" desc="Static Method">
  /**
   * <p>
   * A Static Method for converting a List of Shapes and its associates array of
   * Marker Names in a list of Markers (using the layer's default
   * Symbolizer).</p>
   * <p>
   * <b>NOTE:</b> It returns an empty list if shapes = null|empty and set the
   * Marker Names to null if the shape index exceeds the names array length.</p>
   *
   * @param shapes the list of shapes
   * @param names the array of names.
   * @return a list of MarkerPaths (can be empty).
   */
  public static List<LineMarker> getMarkersFromShapes(List<Shape> shapes,
      @Nullable String[] names) {
    List<LineMarker> result = new ArrayList<>();
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
          result.add(new LineMarker(shape, name));
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
   * @throws gov.ca.water.shapelite.projection.ProjectionException If there was
   * a problem reprojecting.
   */
  public static List<LineMarker> getMarkersFromFeatureSet(FeatureSet featureSet,
      FeatureMarkerDelegate<LineMarker, LineSymbolizer> delegate)
      throws ProjectionException {
    List<LineMarker> result = new ArrayList<>();
    ProjectionInfo sourceProjection = featureSet.getProjection();
    ProjectionInfo wgs84 = Projections.getGeographic().getWorld().getWGS1984();
    boolean sameProjection = wgs84.equals(sourceProjection);
    List<Feature> features = featureSet.getFeatures();
    if (features != null) {
      for (Feature feature : features) {
        LineMarker marker;
        if (delegate == null) {
          Shape shape = feature.getShape();
          if (!sameProjection) {
            shape = Reproject.reprojectShape(shape, sourceProjection, wgs84);
          }
          Feature ft = new Feature(shape);
          ft.copyAttributes(feature.getAttributes());
          marker = new LineMarker(ft);
        } else {
          marker = delegate.getMarker(feature);
          if (!sameProjection) {
            Shape shape = Reproject.reprojectShape(marker.getShape(),
                sourceProjection, wgs84);
            marker.getShape().getParts().clear();
            marker.getShape().getParts().addAll(shape.getParts());
            marker.getShape().calculateBounds();
          }
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
  public LineDataset() {
    super();
  }

  /**
   * Creates a dataset with a preset list of markers.
   *
   * @param markers The list of marker path objects for this dataset.
   */
  public LineDataset(List<LineMarker> markers) {
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
  public LineDataset(FeatureSet featureSet, String nameField)
      throws ProjectionException {
    List<LineMarker> paths = new ArrayList<>();
    boolean sameProjection = WGS84.equals(featureSet.getProjection());
    if (!sameProjection) {
      featureSet.reproject(WGS84);
    }
    for (Feature f : featureSet.getFeatures()) {
      LineMarker mp = new LineMarker(f, f.getAttributes().get(nameField));
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
