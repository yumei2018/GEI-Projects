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
package gov.ca.water.shapelite.data.marker;

import gov.ca.water.shapelite.symbology.Symbolizer;
import gov.ca.water.shapelite.Shape;
import java.io.Serializable;

/**
 * The abstract Class of all Marker Classes.
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 * @param <TSym>
 */
public interface Marker<TSym extends Symbolizer>
    extends Comparable<Marker<TSym>>, Serializable {

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * ABSTRACT: Get the Map Marker's Shape.
   *
   * @return the assigned shape
   */
  Shape getShape();

  /**
   * Gets the String ID that uniquely identifies this marked entity.
   *
   * @return the id
   */
  String getId();

  /**
   * Gets the String name of the entity represented by this marker, and the
   * default pop-up text on the map if the MapToolTooltip is enabled.
   *
   * @return the name.
   */
  String getName();

  /**
   * Gets the popup text that should be used in the place of the name in the
   * tool tip label that appears on the map if MapToolToolTip is enabled. This
   * text is optional. If this is null, but popup is enabled, then the name is
   * used.
   *
   * @return the popupText
   */
  String getPopupText();

  /**
   * This symbolizer is completely optional. If it is null, it uses the layer
   * symbolizers.
   *
   * @return the symbolizer
   */
  TSym getSymbolizer();

  /**
   * Gets an optional object that is associated with this specific marker
   * object.
   *
   * @return the tag
   */
  Object getTag();

  /**
   * Sets a boolean that indicates if this marker should be drawn as selected,
   * usually by highlighting the borders.
   *
   * @return the selected state.
   */
  boolean isSelected();

  /**
   * Sets the String ID that uniquely identifies this marked entity.
   *
   * @param id the id to set
   */
  void setId(String id);

  /**
   * Gets a String that defines text for labeling.
   *
   * @return the label
   */
  String getLabel();

  /**
   * Sets a String that defines text for labeling.
   *
   * @param label the label to set
   */
  void setLabel(String label);

  /**
   * Sets the String name of the entity represented by this marker, and the
   * default pop-up text on the map if the MapToolTooltip is enabled.
   *
   * @param name the name to set.
   */
  void setName(String name);

  /**
   * Sets the popup text that should be used in the place of the name in the
   * tool tip label that appears on the map if MapToolToolTip is enabled. This
   * text is optional. If this is null, but popup is enabled, then the name is
   * used.
   *
   * @param popupText the popupText to set
   */
  void setPopupText(String popupText);

  /**
   * Sets a boolean that indicates if this marker should be drawn as selected,
   * usually by highlighting the borders.
   *
   * @param selected the selected state to set.
   */
  void setSelected(boolean selected);

  /**
   * Sets the symbolizer to use for this point. To save memory, it is best if
   * common points point to a shared symbolizer.
   *
   * @param symbol &lt;TSym&gt; extends {@linkplain Symbolizer}
   */
  void setSymbolizer(TSym symbol);

  /**
   * Sets an optional object that is associated with this specific marker
   * object.
   *
   * @param tag the tag to set.
   */
  void setTag(Object tag);
  //</editor-fold>
  
}
