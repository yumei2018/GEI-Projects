/*
 * The MIT License
 *
 * Copyright 2013 hdunsford.
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
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.ObservedList;
import java.util.EventObject;
import java.util.List;

/**
 * The MapEventlayers event contains information about layers that change.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapEventLayers extends EventObject implements MapEvent {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The list of layers involved in the event.
   */
  private List<? extends Layer<?>> layers;

  /**
   * The MapPanel control where this event originated.
   */
  private MapPanel map;

  //</editor-fold>
  /**
   * Creates a new instance of the MapEventLayers class.
   *
   * @param map The MapPanel that controls this object.
   * @param source The object source.
   * @param layers The layers to be drawn.
   */
  public MapEventLayers(MapPanel map, Object source,
          ObservedList<Layer<?>> layers) {
    super(source);
    this.map = map;
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the list of layers involved in the event.
   *
   * @return the layers
   */
  public final List<? extends Layer<?>> getLayers() {
    return layers;
  }

  /**
   * Sets the list of layers involved in the event.
   *
   * @param layers the layers to set
   */
  public final void setLayers(List<? extends Layer<?>> layers) {
    this.layers = layers;
  }

  /**
   * Gets the MapPanel control where this event originated.
   *
   * @return the map
   */
  @Override
  public final MapPanel getMap() {
    return map;
  }

  /**
   * Sets the MapPanel control where this event originated.
   *
   * @param map the map to set
   */
  protected final void setMap(MapPanel map) {
    this.map = map;
  }

  //</editor-fold>
}
