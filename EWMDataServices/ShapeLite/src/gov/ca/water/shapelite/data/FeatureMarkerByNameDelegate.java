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

import gov.ca.water.shapelite.data.marker.PointMarker;
import gov.ca.water.shapelite.symbology.PointSymbolizer;
import gov.ca.water.shapelite.Shape;
import java.util.HashMap;

/**
 * A FeatureMarkerDelegate to initiate a PointMarker and (optional) set the PointMarker's
 name property to the value of a 'NameField' of the feature. If the
 * 'NameField' is unassigned the name property will be set to null.
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class FeatureMarkerByNameDelegate extends
        FeatureMarkerDelegate<PointMarker, PointSymbolizer> {

  // <editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * Placeholder for the Shape's 'Name' Field - the field containing the
 PointMarker's Name property.
   */
  private String nameField;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor - with an optional NameField. If the 'NameField' is
   * unassigned the name property will be set to null. Otherwise, the
   * marker.name property will be set as Feature.attributes[nameField].
   *
   * @param nameField The string name field.
   */
  public FeatureMarkerByNameDelegate(String nameField) {
    super();
    this.nameField = null;
    if (nameField != null) {
      String trim = nameField.trim();
      if (!trim.isEmpty()) {
        this.nameField = trim;
      }
    }
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Delegate Overrides">
  /**
   * {@inheritDoc}
   * <p>
 OVERRIDE: Initiates the PointMarker and set PointMarker.name = attrs[this.nameField].
 It sets the marker.symbolizer=null to force the use of the layer's
 defaultSymbolizer.
 </p>
   *
   * @return the PointMarker result.
   */
  @Override
  public final PointMarker getMarker(Shape shape, HashMap<String, String> attrs) {
    PointMarker result = null;
    if (shape != null) {
      String name = null;
      if (this.nameField != null && attrs != null
              && attrs.containsKey(this.nameField)) {
        name = attrs.get(this.nameField);
      }
      result = new PointMarker(shape, name);
      result.setSymbolizer(null);
    }
    return result;
  }
  // </editor-fold>
}
