/*
 * The MIT License
 *
 * Copyright 2015 rmarquez.
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
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.data.FeatureMarkerDelegate;
import gov.ca.water.shapelite.data.marker.LineMarker;
import gov.ca.water.shapelite.data.marker.PathRangeMarker;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rmarquez
 */
public class MarkerPathRangeDataset
        extends FeatureDataset<PathRangeMarker, LineSymbolizer> {

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
   * @param shapes the list of shapes.
   * @param names the array of names.
   * @return a list of MarkerPaths (can be empty).
   */
  public static List<LineMarker> getMarkersFromShapes(List<Shape> shapes,
          String[] names) {
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
   * @throws gov.ca.water.shapelite.projection.ProjectionException If there
   * was an error reprojecting.
   */
  public static List<LineMarker> getMarkersFromFeatureSet(FeatureSet featureSet,
          FeatureMarkerDelegate<LineMarker, LineSymbolizer> delegate)
          throws ProjectionException {
    List<LineMarker> result = new ArrayList<>();
    ProjectionInfo sourceProjection = null;
    if (featureSet.getProjectionESRI() != null) {
      sourceProjection = ProjectionInfo.
              fromEsriString(featureSet.getProjectionESRI());
    }
    ProjectionInfo wgs84 = Projections.getGeographic().getWorld().getWGS1984();
    boolean sameProjection = wgs84.equals(sourceProjection);
    List<Feature> features = featureSet.getFeatures();

    if ((features != null) && (!features.isEmpty())) {
      for (Feature feature : features) {
        LineMarker marker;
        if (delegate == null) {
          Shape shape = feature.getShape();
          if (sourceProjection != null && !sameProjection) {
            shape = Reproject.reprojectShape(shape, sourceProjection, wgs84);
          }
          if (shape == null) {
            marker = null;
          } else {
            marker = new LineMarker(shape, null);
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
  public MarkerPathRangeDataset() {
    super();
  }

  /**
   * Creates a dataset with a preset list of markers.
   *
   * @param markers The list of markers for this markerpath datset.
   */
  public MarkerPathRangeDataset(List<PathRangeMarker> markers) {
    super(markers);
  }
  //</editor-fold>
}
