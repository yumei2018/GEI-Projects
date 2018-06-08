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

import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.symbology.Symbolizer;
import gov.ca.water.shapelite.Shape;
import java.util.Objects;

/**
 * The abstract Class of all Marker Classes.
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 * @param <TSym>
 */
public abstract class FeatureMarker<TSym extends Symbolizer>
    implements Marker<TSym> {

  /**
   * A base value to use for the hash calculation.
   */
  private static final int HASH_BASE = 7;

  /**
   * A multiplier for the hash calculation.
   */
  private static final int HASH_FACTOR = 79;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The String ID that uniquely identifies this marked entity.
   */
  private String id;
  /**
   * The String name of the entity represented by this marker, and the default
   * pop-up text on the map if the MapToolTooltip is enabled.
   */
  private String name;
  /**
   * The popup text that should be used in the place of the name in the tool tip
   * label that appears on the map if MapToolToolTip is enabled. This text is
   * optional. If this is null, but popup is enabled, then the name is used.
   */
  private String popupText;
  /**
   * A boolean that indicates if this marker should be drawn as selected,
   * usually by highlighting the borders.
   */
  private Boolean selected;
  /**
   * An optional specific symbolizer if this symbolizer should not use the layer
   * symbols.
   */
  private TSym symbolizer;
  /**
   * An optional object that is associated with this specific marker object.
   */
  private Object tag;

  /**
   * A String that defines text for labeling.
   */
  private String label;

  /**
   * The feature that defines the shape for the path in geographic latitude
   * longitude coordinates (wgs84).
   */
  private final Feature feature;
  //</editor-fold>

  /**
   * Creates a new instance of the MarkerBase object, which is only called from
   * it's subclasses.
   *
   * @param feature The feature to use for this marker. The default layer level
   * symbolizer will be used, not the feature specific symbolizer.
   */
  protected FeatureMarker(@NonNull Feature feature) {
    if (feature == null) {
      throw new IllegalArgumentException("Feature should not be null");
    }
    this.id = null;
    this.name = null;
    this.popupText = null;
    this.selected = null;
    this.tag = null;
    this.feature = feature;
  }

  /**
   * Creates a new instance of the MarkerBase object, which is only called from
   * it's subclasses.
   *
   * @param feature The feature to use for this marker.
   * @param symbolizer The symbolizer to use for this marker.
   */
  protected FeatureMarker(@NonNull Feature feature, TSym symbolizer) {
    if (feature == null) {
      throw new IllegalArgumentException("Feature should not be null");
    }
    if (symbolizer == null) {
      throw new IllegalArgumentException("Symbolizer should not be null");
    }
    this.id = null;
    this.name = null;
    this.popupText = null;
    this.selected = null;
    this.tag = null;
    this.symbolizer = symbolizer;
    this.feature = feature;
  }

  /**
   * This is used for sorting the markers by name.
   *
   * @param o The other marker to compare to.
   * @return <ul><li>1 if this markers name is larger</li>
   * <li>0 if this marker's name is the same as the other marker's</li>
   * <li>-1 if this marker's name comes before the other marker's</li>
   * </ul>
   */
  @Override
  public final int compareTo(Marker<TSym> o) {
    return this.getName().compareTo(o.getName());
  }

  /**
   * Gets the String name of this marker as the "text" representation of the
   * marker.
   *
   * @return this.name
   */
  @Override
  public final String toString() {
    return this.name;
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * ABSTRACT: Get the Map Marker's Shape.
   *
   * @return the assigned shape
   */
  @Override
  public final Shape getShape() {
    if (this.feature != null) {
      return this.feature.getShape();
    }
    return null;
  }

  /**
   * @param shape the shape to set
   */
  public final void setShape(Shape shape) {
    this.feature.setShape(shape);
  }

  /**
   * Gets the String ID that uniquely identifies this marked entity.
   *
   * @return the id
   */
  @Override
  public final String getId() {
    return this.id;
  }

  /**
   * Gets the String name of the entity represented by this marker, and the
   * default pop-up text on the map if the MapToolTooltip is enabled.
   *
   * @return the name.
   */
  @Override
  public final String getName() {
    return this.name;
  }

  /**
   * Gets the popup text that should be used in the place of the name in the
   * tool tip label that appears on the map if MapToolToolTip is enabled. This
   * text is optional. If this is null, but popup is enabled, then the name is
   * used.
   *
   * @return the popupText
   */
  @Override
  public final String getPopupText() {
    return this.popupText;
  }

  /**
   * This symbolizer is completely optional. If it is null, it uses the layer
   * symbolizers.
   *
   * @return the symbolizer
   */
  @Override
  public final TSym getSymbolizer() {
    return this.symbolizer;
  }

  /**
   * Gets an optional object that is associated with this specific marker
   * object.
   *
   * @return the tag
   */
  @Override
  public final Object getTag() {
    return this.tag;
  }

  /**
   * Sets a boolean that indicates if this marker should be drawn as selected,
   * usually by highlighting the borders.
   *
   * @return the selected state.
   */
  @Override
  public final boolean isSelected() {
    return ((this.selected != null) && (this.selected));
  }

  /**
   * Sets the String ID that uniquely identifies this marked entity.
   *
   * @param id the id to set
   */
  @Override
  public final void setId(String id) {
    this.id = id;
  }

  /**
   * Gets a String that defines text for labeling.
   *
   * @return the label
   */
  @Override
  public final String getLabel() {
    return label;
  }

  /**
   * Sets a String that defines text for labeling.
   *
   * @param label the label to set
   */
  @Override
  public final void setLabel(String label) {
    this.label = label;
  }

  /**
   * Sets the String name of the entity represented by this marker, and the
   * default pop-up text on the map if the MapToolTooltip is enabled.
   *
   * @param name the name to set.
   */
  @Override
  public final void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the popup text that should be used in the place of the name in the
   * tool tip label that appears on the map if MapToolToolTip is enabled. This
   * text is optional. If this is null, but popup is enabled, then the name is
   * used.
   *
   * @param popupText the popupText to set
   */
  @Override
  public final void setPopupText(String popupText) {
    this.popupText = popupText;
  }

  /**
   * Sets a boolean that indicates if this marker should be drawn as selected,
   * usually by highlighting the borders.
   *
   * @param selected the selected state to set.
   */
  @Override
  public final void setSelected(boolean selected) {
    if (!selected) {
      this.selected = null;
    } else {
      this.selected = selected;
    }
  }

  /**
   * Sets the symbolizer to use for this point. To save memory, it is best if
   * common points point to a shared symbolizer.
   *
   * @param symbol &lt;TSym&gt; extends {@linkplain Symbolizer}
   */
  @Override
  public final void setSymbolizer(TSym symbol) {
    this.symbolizer = symbol;
  }

  /**
   * Sets an optional object that is associated with this specific marker
   * object.
   *
   * @param tag the tag to set.
   */
  @Override
  public final void setTag(Object tag) {
    this.tag = tag;
  }
  //</editor-fold>

  /**
   * The feature that defines the shape for the path in geographic latitude
   * longitude coordinates (wgs84).
   *
   * @return the feature
   */
  public final Feature getFeature() {
    return feature;
  }

  /**
   * Gets the hash code integer based on the id.
   *
   * @return Gets an integer based on the hash code of this object.
   */
  @Override
  public int hashCode() {
    int hash = HASH_BASE;
    hash = HASH_FACTOR * hash + Objects.hashCode(this.id);
    return hash;
  }

  /**
   * Tests equality for this object and the specified object.
   *
   * @param obj The object to test equality with.
   * @return boolean, true if the objects are equal or if the characteristics
   * are equivalent.
   */
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final FeatureMarker<?> other = (FeatureMarker<?>) obj;
    return Objects.equals(this.id, other.id);
  }

}
