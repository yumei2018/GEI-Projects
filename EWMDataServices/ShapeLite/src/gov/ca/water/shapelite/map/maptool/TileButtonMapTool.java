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
package gov.ca.water.shapelite.map.maptool;

import gov.ca.water.shapelite.map.layer.TileLayer;
import gov.ca.water.shapelite.events.MapEventComponent;
import gov.ca.water.shapelite.map.Images;
import gov.ca.water.shapelite.map.element.TileButtonMapElement;
import gov.ca.water.shapelite.map.MapTileType;
import java.awt.Rectangle;

/**
 * This MapTool simply is a container for the MapElementPanButtons that do the
 * actual work of panning.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TileButtonMapTool extends MapTool {

  /**
   * Gets the number of pixels from the right side of the map that the right
   * side of the button should be placed.
   */
  private static final int RIGHT_OFFSET = 50;

  /**
   * Gets the original positioning. This is not really used because it will be
   * quickly re-positioned by the componentResized method.
   */
  private static final Rectangle BOUNDS = new Rectangle(1000, 25, 40, 40);

  /**
   * The actual button element that appears on the map.
   */
  private final TileButtonMapElement element;

  /**
   * creates a new instance of the MapToolPanButtons class.
   */
  public TileButtonMapTool() {
    element = new TileButtonMapElement();
    element.setBoundsFrom(BOUNDS);
    element.setStreetMapImage(Images.getInstance().satellite().orElse(null));
    element.setSatelliteImage(Images.getInstance().topomap().orElse(null));
    element.setTopoImage(Images.getInstance().streetmap().orElse(null));
    this.getElements().add(element);
  }

  /**
   * Repositions the button element when the component is resized.
   *
   * @param e The MapEventComponent.
   */
  @Override
  public final void componentResized(MapEventComponent e) {
    element.getBounds().x = e.getMap().getWidth() - RIGHT_OFFSET;
  }

  /**
   * Gets the map layer to make visible in satellite mode.
   *
   * @return The MapLayerTypes object.
   */
  public final TileLayer getSatelliteLayer() {
    return element.getSatelliteLayer();
  }

  /**
   * Sets the map layer to make visible in satellite mode.
   *
   * @param satelliteLayer the satelliteLayer to set
   */
  public final void setSatelliteLayer(TileLayer satelliteLayer) {
    element.setSatelliteLayer(satelliteLayer);
  }

  /**
   * Gets the map layer to make visible in street map mode.
   *
   * @return the streetLayer
   */
  public final TileLayer getStreetLayer() {
    return element.getStreetLayer();
  }

  /**
   * Gets the map layer to make visible in street map mode.
   *
   * @param streetLayer the streetLayer to set
   */
  public final void setStreetLayer(TileLayer streetLayer) {
    element.setStreetLayer(streetLayer);
  }

  /**
   * Gets the map layer to make visible in topo mode.
   *
   * @return the streetLayer
   */
  public final TileLayer getTopoLayer() {
    return element.getTopoLayer();
  }

  /**
   * Gets the map layer to make visible in topo mode.
   *
   * @param streetLayer the streetLayer to set
   */
  public final void setTopoLayer(TileLayer streetLayer) {
    element.setTopoLayer(streetLayer);
  }

  /**
   * Gets the current map type shown on the button.
   *
   * @return The current map type enumeration.
   */
  public final MapTileType getCurrentMapType() {
    return element.getCurrentMapType();
  }

  /**
   * Sets the current map type shown on the button.
   *
   * @param type The MapTileType to set.
   */
  public final void setCurrentMapType(MapTileType type) {
    element.setCurrentMapType(type);
  }

}
