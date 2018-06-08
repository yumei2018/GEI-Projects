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
package gov.ca.water.shapelite.data;

import gov.ca.water.shapelite.data.marker.FeatureMarker;
import gov.ca.water.shapelite.symbology.Symbolizer;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.Shape;
import java.util.HashMap;

/**
 * An abstract class for a delegate to update a Marker's Symbolizer based.
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 * @param <TMarker>
 * @param <TSym>
 */
public abstract class FeatureMarkerDelegate<
    TMarker extends FeatureMarker<TSym>, TSym extends Symbolizer> {

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor.
   */
  protected FeatureMarkerDelegate() {
  }
  // </editor-fold>

  /**
   * Call to initiate a new marker representing the specified Feature.
   * It calls null {@linkplain #getMarker(gov.ca.water.shapelite.Shape,
   * java.util.HashMap) this.getMarker(feature.shape, feature.attributes)}
   * and return the result.
   *
   * @param feature the feature to initiate the marker for
   * @return an new marker instance or null if feature and feature.shape !=
   * null.
   */
  public final TMarker getMarker(Feature feature) {
    TMarker result = null;
    if (feature != null) {
      Shape shape = feature.getShape();

      if (shape != null) {
        return this.getMarker(shape, feature.getAttributes());
      }
    }
    return result;
  }

  // <editor-fold defaultstate="collapsed" desc="Public Abstract Methods">
  /**
   * Call to update the symbolizer based on the properties attributes of the
   * specified shape or the shapes attributes.
   *
   * @param shape the Shape to initiate the marker for
   * @param attrs the shape's attributes
   * @return an new marker instance
   */
  public abstract TMarker getMarker(Shape shape, HashMap<String, String> attrs);
  // </editor-fold>
}
