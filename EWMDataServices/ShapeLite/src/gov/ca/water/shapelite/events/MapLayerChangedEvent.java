/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.events;

import gov.ca.water.shapelite.map.layer.Layer;
import java.util.EventListener;
import java.util.EventObject;

/**
 * This handles a specific kind of event, providing data access properties.
 *
 * @author hdunsford
 */
public class MapLayerChangedEvent extends EventObject {

  /**
   * The Layer<?> layer.
   */
  private Layer<?> layer;

  /**
   * Creates a new instance of <code>RegionChangedEvent</code>.
   *
   * @param source The Object source that initiated the event.
   * @param layer
   */
  public MapLayerChangedEvent(Object source, Layer<?> layer) {
    super(source);
    this.layer = layer;
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the Layer<?> layer.
   *
   * @return the layer.
   */
  public final Layer<?> getLayer() {
    return layer;
  }

  /**
   * Sets the layer.
   *
   * @param layer the layer to set.
   */
  protected final void setLayer(Layer<?> layer) {
    this.layer = layer;
  }

  // </editor-fold>
  /**
   * An event listener interface to handle the MapLayerChangedEvent.
   */
  public interface Listener extends EventListener {

    /**
     * Occurs when the MapLayerChangedEvent is fired.
     *
     * @param e The MapLayerChangedEvent containing information about the event.
     */
    void mapLayerChanged(MapLayerChangedEvent e);
  }
}
