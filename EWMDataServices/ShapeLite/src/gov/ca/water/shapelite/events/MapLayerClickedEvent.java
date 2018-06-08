/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
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

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.data.marker.FeatureMarker;
import gov.ca.water.shapelite.map.layer.Layer;
import gov.ca.water.shapelite.symbology.Symbolizer;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

/**
 * The event object for layer click events.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 * @param <TLayer> The layer type, like MapLayerMarker.
 * @param <TMarker> The marker type.
 */
public class MapLayerClickedEvent<TLayer extends Layer<?>, TMarker extends FeatureMarker<? extends Symbolizer>>
        extends EventObject {

  /**
   * The MapLayerMarker layer involved in the clicking event.
   */
  private final TLayer layer;
  /**
   * The list of Markers that were clicked.
   */
  private List<TMarker> clickedMarkers;
  /**
   * The click type defining whether it was 0, 1, or 2 clicks.
   */
  private LayerClickType layerClickType;
  /**
   * The MapEventMouse with mouse information for this event.
   */
  private MapEventMouse mapMouseEvent;

  /**
   * Creates a new instance of the MapLayerClicked event.
   *
   * @param source The object source of the event.
   * @param layer The MapLayerMarker for the layer being clicked.
   */
  public MapLayerClickedEvent(Object source, TLayer layer) {
    super(source);
    this.layer = layer;
  }

  /**
   * Gets the MapLayerMarker for the layer.
   *
   * @return The layer object.
   */
  public final TLayer getLayer() {
    return layer;
  }

  /**
   * Sets the layer that is involved in the event.
   *
   * @param hoveredMarkers The list of markers.
   * @param layerClick The layer click type, showing the number of clicks.
   * @param e the MapEventMouse with mouse events.
   */
  public final void setLayer(List<TMarker> hoveredMarkers,
          LayerClickType layerClick, MapEventMouse e) {
    this.clickedMarkers = hoveredMarkers;
    this.layerClickType = layerClick;
    this.mapMouseEvent = e;
  }
  public TMarker getClosestToMapMouseLocation() {
    Coord mLoc = this.getMapMouseEvent().getLocation();
    Double minDistance = Double.MAX_VALUE;
    TMarker marker = null;
    for (TMarker hoveredMarker : this.getClickedMarkers()) {
      for (Part p : hoveredMarker.getFeature().getShape().getParts()) {
        Double distance = p.distance(mLoc).get();
        if (distance < minDistance) {
          minDistance = distance;
          marker = hoveredMarker;
        }
      }
    }
    return marker;
  }
  /**
   * Gets the list of clicked markers.
   *
   * @return The list of clicked markers.
   */
  public final List<TMarker> getClickedMarkers() {
    return clickedMarkers;
  }

  /**
   * The layer click type, meaning one click or two clicks or context.
   *
   * @return The LayerClickType with the number of clicks.
   */
  public final LayerClickType getLayerClickType() {
    return layerClickType;
  }

  /**
   * Gets the MapEventMouse object with mouse information.
   *
   * @return The MapEventMouse object.
   */
  public final MapEventMouse getMapMouseEvent() {
    return mapMouseEvent;
  }

  /**
   * The listener interface for the clicked event.
   *
   * @param <TLayer> The layer type
   * @param <TMarker> The marker type
   */
  public static interface Listener<TLayer extends Layer<?>, TMarker extends FeatureMarker<? extends Symbolizer>>
          extends EventListener {

    /**
     * Occurs during a double click.
     *
     * @param event The MapLayerClickedEvent object.
     */
    void doubleClick(MapLayerClickedEvent<TLayer , TMarker> event);

    /**
     * Occurs during a single click.
     *
     * @param event The MapLayerClickedEvent object.
     */
    void click(MapLayerClickedEvent<TLayer, TMarker> event);

    /**
     * Occurs during a right click.
     *
     * @param event The MapLayerClickedEvent object.
     */
    void rightClick(MapLayerClickedEvent<TLayer, TMarker> event);

    /**
     * Occurs during a mouse down.
     *
     * @param event The MapLayerClickedEvent object.
     */
    void mouseDown(MapLayerClickedEvent<TLayer, TMarker> event);

  }
  /**
   * The listener interface for the clicked event.
   *
   * @param <TLayer> The layer type
   * @param <TMarker> The marker type
   */
  public static class DefaultListener<TLayer extends Layer<?>, TMarker extends FeatureMarker<? extends Symbolizer>>
          implements Listener<TLayer, TMarker> {

    /**
     * Occurs during a double click.
     *
     * @param event The MapLayerClickedEvent object.
     */
    public void doubleClick(MapLayerClickedEvent<TLayer , TMarker> event){};

    /**
     * Occurs during a single click.
     *
     * @param event The MapLayerClickedEvent object.
     */
    public void click(MapLayerClickedEvent<TLayer, TMarker> event){};

    /**
     * Occurs during a right click.
     *
     * @param event The MapLayerClickedEvent object.
     */
    public void rightClick(MapLayerClickedEvent<TLayer, TMarker> event){
    };

    @Override
    public void mouseDown(MapLayerClickedEvent<TLayer, TMarker> event) {
    }
  }

}
