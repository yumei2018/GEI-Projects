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
package gov.ca.water.shapelite.map.layer;

import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.events.MapEventMouse;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.map.LegendSymbol;
import gov.ca.water.shapelite.map.PaintArgs;
import gov.ca.water.shapelite.symbology.Symbolizer;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the base class to many specialized layer types that can be
 * found within the map. Each type is associated with a specific type of
 * symbolizer. Knowing the layer type is usually sufficient to also give you the
 * type for the dataset and the symbolizer.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 * @param <TSym> A Symbolizer
 */
public abstract class Layer<TSym extends Symbolizer> {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The geographic bounds of the data content for this layer. This does not
   * change unless the features drawn by the layer are modified.
   */
  private final Envelope envelope;

  private List<LegendSymbol> legendSymbols;

  /**
   * The largest scale where this layer should be drawn when zooming in and out.
   * The scale of a map is the ratio of a distance on the map to the
   * corresponding distance on the ground.
   */
  private double maxScale;
  /**
   * The smallest scale where this layer should be drawn when zooming in and
   * out. The scale of a map is the ratio of a distance on the map to the
   * corresponding distance on the ground.
   */
  private double minScale;
  /**
   * The string name identifying this layer. This is simply a cached value and
   * is not a unique identifier.
   */
  private String name;
  /**
   * The default symbolizer that provides the information necessary for
   * rendering. If a symbolizer is not assigned on a feature specific level,
   * then this symbolizer is used. This is not rendering engine itself, but
   * simply the set of properties that help the renderer decide how to draw the
   * features.
   */
  private TSym symbolizer;
  /*
   * The Layer's Visible State
   */
  private boolean visible;

  /**
   * Controls whether this layer responds to selection.
   */
  private boolean selectable;

  /**
   * Boolean, that if true indicates this map layer should appear in a legend.
   */
  private boolean visibleInLegend;

  /**
   * A boolean that indicates if this layer is currently selected in the legend.
   */
  private boolean selectedInLegend;

  /**
   * A boolean that indicates if this layer is currently focused in the legend.
   */
  private boolean focusedInLegend;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of the MapLayer class. This method is protected
   * because this class is abstract. It should only be called directly by
   * subclasses.
   */
  protected Layer() {
    minScale = -Double.MAX_VALUE;
    maxScale = Double.MAX_VALUE;
    this.visible = true;
    this.selectable = true;
    this.visibleInLegend = true;
    this.legendSymbols = new ArrayList<>();
    this.envelope = new DefaultEnvelope();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Inverts the selection state for the content found within the specified
   * envelope.
   *
   * @param e the mouse event that trigger the request
   * @param env the envelop of the selection
   * @return
   */
  public boolean invertSelection(MapEventMouse e, Envelope env) {
    return false;
    // This is not abstract because currently image and tile layers do not
    // respond to the selection.  Therefore the default implementation is to
    // do nothing.
  }

  /**
   * This method calls the renderer and instructs the renderer to perform
   * painting. Some layers may load content from disk to do this, but no content
   * is pulled from the Internet.
   *
   * @param args the PaintArgs containing the Graphics and output DataFrame
   * references
   */
  public abstract void paint(PaintArgs args);

  /**
   * This method calls the renderer and instructs the renderer to perform
   * painting. In addition, if any new, slow loading content is required from
   * the Internet, it is instructed to download for display later.
   *
   * @param args the PaintArgs containing the Graphics and output DataFrame
   * references
   */
  public void paintWeb(PaintArgs args) {
    paint(args);
  }

  /**
   * Selects the content on this layer. This is especially designed for vector
   * layers so may do nothing for some layers.
   *
   * @param e the mouse event that trigger the request
   * @param env the envelop of the selection
   * @return
   */
  public boolean select(MapEventMouse e, Envelope env) {
    return false;
    // This is not abstract because currently image and tile layers do not
    // respond to the selection.  Therefore the default implementation is to
    // do nothing.
  }

  /**
   * Gets the string representation for this layer.
   *
   * @return
   */
  @Override
  public String toString() {
    if (this.name == null) {
      return super.toString();
    }
    return this.name;
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the geographic bounds of the data content for this layer. This does
   * not change unless the features drawn by the layer are modified.
   *
   * @return the Envelope.
   */
  public Envelope getEnvelope() {
    return envelope;
  }

  /**
   * Gets the largest scale where this layer should be drawn when zooming in and
   * out. The scale of a map is the ratio of a distance on the map to the
   * corresponding distance on the ground.
   *
   * @return the maxScale
   */
  public double getMaxScale() {
    return maxScale;
  }

  /**
   * Sets the largest scale where this layer should be drawn when zooming in and
   * out. The scale of a map is the ratio of a distance on the map to the
   * corresponding distance on the ground.
   *
   * @param maxScale the maxScale to set
   */
  public void setMaxScale(double maxScale) {
    this.maxScale = maxScale;
  }

  /**
   * Gets the smallest scale where this layer should be drawn when zooming in
   * and out. The scale of a map is the ratio of a distance on the map to the
   * corresponding distance on the ground.
   *
   * @return the minScale
   */
  public double getMinScale() {
    return minScale;
  }

  /**
   * Sets the smallest scale where this layer should be drawn when zooming in
   * and out. The scale of a map is the ratio of a distance on the map to the
   * corresponding distance on the ground.
   *
   * @param minScale the minScale to set
   */
  public void setMinScale(double minScale) {
    this.minScale = minScale;
  }

  /**
   * Gets the string name identifying this layer. This is simply a cached value
   * and is not guaranteed to be a unique identifier.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the string name identifying this layer. This is simply a cached value
   * and is not guaranteed to be a unique identifier.
   *
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the default symbolizer that provides the information necessary for
   * rendering. If a symbolizer is not assigned on a feature specific level,
   * then this symbolizer is used. This is not rendering engine itself, but
   * simply the set of properties that help the renderer decide how to draw the
   * features.
   *
   * @return the symbolizer
   */
  public TSym getDefaultSymbolizer() {
    return symbolizer;

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
  public void setDefaultSymbolizer(TSym symbolizer) {

    this.symbolizer = symbolizer;

  }

  /**
   * Gets a boolean indicating if this layer should actively be drawn on the
   * map.
   *
   * @return the visible
   */
  public boolean isVisible() {
    return visible;
  }

  /**
   * Sets a boolean indicating if this layer should actively be drawn on the
   * map.
   *
   * @param visible the visible to set
   */
  public void setVisible(boolean visible) {
    this.visible = visible;
  }
  //</editor-fold>

  /**
   * @return the selectable
   */
  public boolean isSelectable() {
    return selectable;
  }

  /**
   * @param selectable the selectable to set
   */
  public void setSelectable(boolean selectable) {
    this.selectable = selectable;
  }

  /**
   * Gets a boolean, that if true indicates this map layer should appear in a
   * legend.
   *
   * @return the visibleInLegend
   */
  public final boolean isVisibleInLegend() {
    return visibleInLegend;
  }

  /**
   * Sets a boolean, that if true indicates this map layer should appear in a
   * legend.
   *
   * @param visibleInLegend the visibleInLegend to set
   */
  public final void setVisibleInLegend(boolean visibleInLegend) {
    this.visibleInLegend = visibleInLegend;
  }

  /**
   * @return the legendSymbols
   */
  public List<LegendSymbol> getLegendSymbols() {
    if (legendSymbols == null || legendSymbols.isEmpty()) {
      updateLegendSymbols();
    }
    return legendSymbols;
  }

  /**
   * Gets the list of layers (inclusive) between the start and end, regardless
   * of whether the start or end comes first in the list.
   *
   * @param start The starting legend symbol.
   * @param end The ending legend symbol.
   * @return the List of legend symbols, or null if one of the symbols is not in
   * this list.
   */
  public final List<LegendSymbol> getLegendSymbolsBetween(LegendSymbol start,
      LegendSymbol end) {
    List<LegendSymbol> result = new ArrayList<>();
    List<LegendSymbol> symbols = getLegendSymbols();
    if (!symbols.contains(start) || !symbols.contains(end)) {
      return result;
    }
    boolean include = false;
    for (LegendSymbol symbol : symbols) {
      if (symbol == start || symbol == end) {
        if (include) {
          result.add(symbol);
          break;
        }
        include = true;
      }
      if (include) {
        result.add(symbol);
      }
    }
    return result;
  }

  /**
   * @param legendSymbols the legendSymbols to set
   */
  public final void setLegendSymbols(List<LegendSymbol> legendSymbols) {
    this.legendSymbols = legendSymbols;
  }

  /**
   * This should be overridden in child classes if there is something more
   * complicated than just grabbing the image from the default symbolizer.
   */
  public final void updateLegendSymbols() {
    if (this.legendSymbols == null) {
      this.legendSymbols = new ArrayList<>();
    }
    BufferedImage image = this.symbolizer.getLegendIcon();
    legendSymbols.add(new LegendSymbol(this, image));
  }

  /**
   * Gets a boolean that indicates if this layer is currently selected in the
   * legend.
   *
   * @return the selectedInLegend
   */
  public final boolean isSelectedInLegend() {
    return selectedInLegend;
  }

  /**
   * Sets a boolean that indicates if this layer is currently selected in the
   * legend.
   *
   * @param selectedInLegend the selectedInLegend to set
   */
  public final void setSelectedInLegend(boolean selectedInLegend) {
    this.selectedInLegend = selectedInLegend;
  }

  /**
   * Gets a boolean that indicates if this layer is currently focused in the
   * legend.
   *
   * @return the focusedInLegend
   */
  public final boolean isFocusedInLegend() {
    return focusedInLegend;
  }

  /**
   * Sets a boolean that indicates if this layer is currently focused in the
   * legend.
   *
   * @param focusedInLegend the focusedInLegend to set
   */
  public final void setFocusedInLegend(boolean focusedInLegend) {
    this.focusedInLegend = focusedInLegend;
  }

}
