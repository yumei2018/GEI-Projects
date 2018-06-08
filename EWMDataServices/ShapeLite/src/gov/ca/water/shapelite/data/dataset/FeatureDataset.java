/*
 * The MIT License
 *
 * Copyright 2014 J.G. "Koos" Prins, D.Eng. PE..
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

import gov.ca.water.shapelite.symbology.Symbolizer;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.data.marker.FeatureMarker;
import gov.ca.water.shapelite.map.Mercator;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract generic base class for managing any Marker Type.
 *
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 * @param <TMarker> The marker type.
 * @param <TSym> The symbol type.
 */
public abstract class FeatureDataset<TMarker extends FeatureMarker<TSym>, TSym extends Symbolizer> extends Dataset {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The list of markers stored in memory that make up this Dataset.
   */
  private final List<TMarker> markers;

  /**
   * The list of fields for the dataset.
   */
  private final List<Field> fields;

  /**
   * The shape type for the features in this dataset.
   */
  private ShapeType shapeType;

  //</editor-fold>
  /**
   * Parameterless constructor.
   */
  protected FeatureDataset() {
    super();
    this.markers = new ArrayList<>();
    this.fields = new ArrayList<>();
    this.shapeType = ShapeType.NullShape;
  }

  /**
   * Creates a new DatasetMarker from the specified list of markers.
   *
   * @param markers The list of markers to use to build this dataset.
   */
  protected FeatureDataset(List<TMarker> markers) {
    super();
    if ((markers != null) && (!markers.isEmpty())) {
      this.markers = markers;
      TMarker marker = markers.get(0);
      this.shapeType = marker.getFeature().getShape().getShapeType();
      this.doCalculateEnvelope();
    } else {
      this.markers = new ArrayList<>();
    }
    this.fields = new ArrayList<>();

  }

  /**
   * Adds all the fields if they are not already in this dataset.
   *
   * @param fs The featureSet with fields to add to this layer.
   */
  public final void addMissingFields(FeatureSet fs) {
    for (Field field : fs.getFields()) {
      if (containsFieldNamed(field.getName())) {
        continue;
      }
      getFields().add(field.copy());
    }
  }

  /**
   * Gets a boolean that is true if this FeatureDataset contains a field with a
   * name matching the specified name. This is case sensitive.
   *
   * @param name The String name of the field.
   * @return Boolean, true if the field with the specified name exists.
   */
  public final boolean containsFieldNamed(String name) {
    for (Field field : getFields()) {
      if (field.getName() != null && field.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Actual implementation of the calculate envelope method. It initiates a new
   * envelope and expands it to include the shape of each marker. If the dataset
   * contains no markers, it initiates an all inclusive envelope (the hole
   * world).
   */
  private void doCalculateEnvelope() {
    Envelope result = null;
    if ((this.markers != null) && (!this.markers.isEmpty())) {
      for (TMarker marker : this.markers) {
        Shape shape = marker.getShape();

        Envelope shpEnv = null;
        if (shape != null) {
          shpEnv = shape.getEnvelope();
        }
        if (shpEnv != null && !shpEnv.isEmpty()) {
          if (result == null) {
            result = Mercator.toMerc(shpEnv);
          } else {
            result.expandToInclude(Mercator.toMerc(shpEnv));
          }
        }
      }
    }

    if (result == null) {
      result = new DefaultEnvelope(-MAX_LON, -MAX_LAT, MAX_LON, MAX_LAT);
    }
    super.getEnvelopeMercator().copyProperties(result);

  }

  /**
   * Cycles through all of the markers and defines the envelope for those
   * markers. it Calls
   * {@linkplain #doCalculateEnvelope() this.doCalculateEnvelope} to calculate
   * the new envelope
   */
  public final void calculateEnvelope() {
    this.doCalculateEnvelope();
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the list of markers stored in memory that make up this Dataset.
   *
   * @return the markers
   */
  public final List<TMarker> getMarkers() {
    return this.markers;
  }

  /**
   * Gets the marker with the specified name.
   *
   * @param name The String name of the marker to get.
   * @return The optional TMarker marker. The optional will never be null, but
   * may be empty.
   */
  @NonNull
  public final Optional<TMarker> getMarker(String name) {
    for (TMarker marker : this.markers) {
      if (marker.getName().equals(name)) {
        return Optional.of(marker);
      }
    }
    return Optional.empty();
  }

  /**
   * Copies the markers from the specified list into the list on this dataset.
   *
   * @param markers the markers to set.
   */
  public final void setMarkersFrom(List<TMarker> markers) {
    this.markers.clear();
    if ((markers != null) && (!markers.isEmpty())) {
      this.markers.addAll(markers);
      this.calculateEnvelope();
    } else {
      Envelope env = new DefaultEnvelope(-MAX_LON, -MAX_LAT, MAX_LON, MAX_LAT);
      this.getEnvelopeMercator().copyProperties(env);
    }
  }

  // </editor-fold>
  /**
   * Gets the list of fields for the dataset.
   *
   * @return the fields
   */
  public final List<Field> getFields() {
    return fields;
  }

  /**
   * Clears the current fields and copies all the specified fields to this
   * dataset.
   *
   * @param fields The fields to copy.
   */
  public final void setFieldsFrom(List<Field> fields) {
    this.fields.clear();
    this.fields.addAll(fields);
  }

  /**
   * Gets a FeatureSet from this PolygonDataset.
   *
   * @return A FeatureSet, which only has the information that is typically
   * stored in a shapefile, and loses information about popup text and labeling.
   */
  public final FeatureSet toFeatureSet() {
    FeatureSet result = new FeatureSet();
    result.appendCopiedFields(fields);
    for (TMarker marker : markers) {
      result.getFeatures().add(marker.getFeature());
    }
    return result;
  }

  /**
   * Gets the shape type for the features in this dataset.
   *
   * @return the shapeType
   */
  public final ShapeType getShapeType() {
    return shapeType;
  }

  /**
   * Sets the shape type for the features in this dataset.
   *
   * @param shapeType the shapeType to set
   */
  public final void setShapeType(ShapeType shapeType) {
    this.shapeType = shapeType;
  }
}
