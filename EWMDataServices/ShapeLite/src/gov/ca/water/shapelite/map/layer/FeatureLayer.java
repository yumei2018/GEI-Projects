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
package gov.ca.water.shapelite.map.layer;

import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.data.dataset.FeatureDataset;
import gov.ca.water.shapelite.data.marker.FeatureMarker;
import gov.ca.water.shapelite.symbology.FeatureSymbolizer;
import gov.ca.water.shapelite.symbology.Symbolizer;

/**
 * <p>
 * An abstract base class for supporting all Dataset that extends the
 * DatasetMarkerBase class.</p>
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 * @param <TDataset> the dataset type extending {@linkplain FeatureDataset}
 * <TMarker, TSym>.
 * @param <TMarker> The marker objects on the dataset extending
 * {@linkplain  FeatureMarker}.
 * @param <TSym> The symbolizer for the dataset extending
 * {@linkplain FeatureSymbolizer} .
 */
public abstract class FeatureLayer<TDataset extends FeatureDataset<TMarker, TSym>, TMarker extends FeatureMarker<TSym>, TSym extends FeatureSymbolizer>
    extends DatasetLayer<TDataset, TSym> {

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor.
   */
  public FeatureLayer() {
    super();
  }

  /**
   * Creates a new instance of the MapLayerDataset with a preset dataset.
   *
   * @param dataset the Dataset of type DatasetMarkerBase
   * @param symbolizer the associated default Marker Symbolizer
   */
  public FeatureLayer(TDataset dataset, TSym symbolizer) {
    super(dataset, symbolizer);
  }

  /**
   * Sets the default symbolizer that provides the information necessary for
   * rendering. If a symbolizer is not assigned on a feature specific level,
   * then this symbolizer is used. This is not rendering engine itself, but
   * simply the set of properties that help the renderer decide how to draw the
   * features.
   *
   * @param symbolizer the symbolizer to set
   */
  @Override
  public final void setDefaultSymbolizer(TSym symbolizer) {
    TSym old = this.getDefaultSymbolizer();
    super.setDefaultSymbolizer(symbolizer);
    String field = symbolizer.getLabelField();
    if (old == null || !stringEquals(old.getLabelField(), field)) {
      applyLabels();
    }

  }

  /**
   * Uses the symbolizer's labelField to update the label text on the marker.
   */
  public final void applyLabels() {
    String field = super.getDefaultSymbolizer().getLabelField();
    if (field != null) {
      for (TMarker marker : this.getDataset().getMarkers()) {
        if (marker.getFeature().getAttributes().containsKey(field)) {
          String text = marker.getFeature().getAttributes().get(field);
          marker.setLabel(text);
        } else {
          marker.setLabel(null);
        }
      }
    } else {
      clearLabels();
    }
  }

  /**
   * Sets the label text to null for all markers.
   */
  public final void clearLabels() {
    if (this.getDataset() != null && this.getDataset().getMarkers() != null) {
      for (TMarker marker : this.getDataset().getMarkers()) {
        marker.setLabel(null);
      }
    }
  }

  /**
   * Null safe string equality test.
   *
   * @param a the first string.
   * @param b The second string.
   * @return Boolean if true.
   */
  private boolean stringEquals(String a, String b) {
    if (a != null) {
      return a.equals(b);
    }
    return b == null;
  }

  /**
   * Sets the markers given the specified FeatureSet. This will replace any
   * existing markers.
   *
   * @param featureSet The Featureset to use.
   * @param nameField The attribute field to use for the name of each marker.
   */
  public abstract void setFeaturesFrom(FeatureSet featureSet, String nameField);

  // </editor-fold>
}
