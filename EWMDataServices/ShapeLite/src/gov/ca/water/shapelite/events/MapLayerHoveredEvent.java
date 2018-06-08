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
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

/**
 * The MapLayerHovered Event object for those events.
 *
 * @param <TLayer> The layer type for the event.
 * @param <TMarker> The marker type for the event.
 */
public class MapLayerHoveredEvent<TLayer extends Layer<?>, TMarker extends FeatureMarker<? extends Symbolizer>>
        extends EventObject {

  /**
   * The MapLayerMarker being hovered over.
   */
  private final TLayer layer;

  /**
   * The List of markers involved in the hover action.
   */
  private List<TMarker> hoveredMarkers;

  /**
   * The MapEventMouse with mouse events.
   */
  private MapEventMouse e;

  /**
   * Boolean true if the mouse is actively hovering over markers.
   */
  private boolean hovering;

  /**
   * Creates a new instance of the MapLayerHoveredEvent to track hovering.
   *
   * @param source The object source of the event.
   * @param layer The layer object being hovered over.
   */
  public MapLayerHoveredEvent(Object source, TLayer layer) {
    super(source);
    this.source = source;
    this.layer = layer;
  }

  /**
   * Gets the MapLayerMarker layer for hover information.
   *
   * @return The MapLayerMarker involved in this event.
   */
  public final TLayer getLayer() {
    return layer;
  }

  /**
   * Sets the list of hovered markers for this event.
   *
   * @param hoveredMarkers The list of hovered markers.
   * @param e The MapEventMouse object with mouse information.
   */
  public final void setHoveredMarkers(List<TMarker> hoveredMarkers,
          MapEventMouse e) {
    if (hoveredMarkers == null || hoveredMarkers.isEmpty()) {
      this.hovering = false;
      this.hoveredMarkers = new ArrayList<>();
    } else {
      this.hovering = true;
      this.hoveredMarkers = hoveredMarkers;
    }
    this.e = e;
  }

  /**
   * Gets the list of hovered markers.
   *
   * @return The list of markers.
   */
  public final List<TMarker> getHoveredMarkers() {
    return hoveredMarkers;
  }

  /**
   * Gets a boolean that is true if the mouse is actively hovering.
   *
   * @return Boolean, true if the mouse is hovering over markers.
   */
  public final boolean isHovering() {
    return hovering;
  }

  /**
   * Gets the MapMouseEvent for this element.
   *
   * @return The MapEventMouse that contains mouse information.
   */
  public final MapEventMouse getMapMouseEvent() {
    return e;
  }

  public TMarker getClosestToMapMouseLocation() {
    Coord mLoc = this.getMapMouseEvent().getLocation();
    Double minDistance = Double.MAX_VALUE;
    TMarker marker = null;
    for (TMarker hoveredMarker : this.getHoveredMarkers()) {
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
   * The Listener interface for Map Layer Hovered events.
   *
   * @param <TLayer> The layer type
   * @param <TMarker> The marker type.
   */
  public interface Listener<TLayer extends Layer<?>, TMarker extends FeatureMarker<?>>
          extends EventListener {

    /**
     * Occurs before the hovering takes place.
     *
     * @param event The Event object with marker and mouse information.
     */
    void before(MapLayerHoveredEvent<TLayer, TMarker> event);

    /**
     * Occurs after the hovering takes place.
     *
     * @param event The event object with marker and mouse information.
     */
    void after(MapLayerHoveredEvent<TLayer, TMarker> event);
  }

}
