package gov.ca.water.shapelite.events;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.DefaultEnvelope;

import gov.ca.water.shapelite.data.marker.PointMarker;
import gov.ca.water.shapelite.data.Projector;
import gov.ca.water.shapelite.map.layer.PointLayer;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.Mercator;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rmarquez
 */
public class MapLayerMarkerEventManager {

  /**
   * The MapLayerMarker layer that click and hover events are associated with.
   */
  private PointLayer layer;
  /**
   * The list of listeners for hover events.
   */
  private List<MapLayerHoveredEvent.Listener<PointLayer, PointMarker>> hoveredEventlisteners = new ArrayList<>();
  /**
   * The list of listeners for click events.
   */
  private List<MapLayerClickedEvent.Listener<PointLayer, PointMarker>> clickedEventlisteners = new ArrayList<>();
  /**
   * The MapPanel control.
   */
  private final MapPanel map;
  /**
   * The Projector to convert between pixel and Ted Mercator coordinates.
   */
  private final Projector projector;
  /**
   * The list of markers that are currently experiencing hovering.
   */
  private List<PointMarker> hoveredMarkers;
  /**
   * Boolean that is true if the mouse is hovering over markers.
   */
  private boolean isHovered = false;
  /**
   * The integer click count.
   */
  private int clickCount;
  /**
   * Boolean true if the mouse triggers a popup.
   */
  private boolean popupTrigger = false;

  /**
   * Boolean true if the events are enabled
   */
  private boolean enabled;

  /**
   * Mouse adapter
   */
  private final MouseAdapterImpl mouseAdapter;

  /**
   * Gets the MapLayerMarker marker layer for this manager.
   *
   * @return A MapLayerMarker.
   */
  public final PointLayer getLayer() {
    return this.layer;
  }

  /**
   * Creates a new instance of a MapLayerMarkerEventManager object.
   *
   * @param map The MapPanel to use for the events.
   * @param layer The MapLayerMarker layer for the events.
   */
  public MapLayerMarkerEventManager(MapPanel map, PointLayer layer) {
    this.layer = layer;
    this.map = map;
    this.projector = this.map.getContent().getProjector();

    final MapLayerMarkerEventManager self = this;
    mouseAdapter = new MouseAdapterImpl(self);
    this.map.addMouseListener(mouseAdapter);
    this.map.addMouseMotionListener(mouseAdapter);
  }

  private void doMouseMotion(MouseEvent e) {
    MapEventMouse mapEventMouse = new MapEventMouse(this.map, e);
    if (!map.getContent().getAllLayers().contains(layer)) {
      setEnabled(false);
      if (this.isHovered) {
        mapLayerHoveredAfter(e.getSource(), hoveredMarkers, mapEventMouse);
        this.isHovered = false;
      }
      return;
    }
    if (!enabled) {
      if (this.isHovered) {
        mapLayerHoveredAfter(e.getSource(), hoveredMarkers, mapEventMouse);
        this.isHovered = false;
      }
      return;
    }
    if (!this.layer.isVisible()) {
      if (this.isHovered) {
        mapLayerHoveredAfter(e.getSource(), hoveredMarkers, mapEventMouse);
        this.isHovered = false;
      }
      return;
    }

    Coord mousePointLocation = Mercator.toMerc(mapEventMouse.getLocation());
    List<Coord> coords = new ArrayList<>();
    for (PointMarker marker : this.getLayer().getDataset().getMarkers()) {
      coords.add(marker.getCoordinate());
    }
    DefaultEnvelope env = new DefaultEnvelope(coords);
    boolean isEnclosedMapPoint
            = Mercator.toMerc(env).containsPoint2D(mousePointLocation);
    if (!isEnclosedMapPoint) {
      if (this.isHovered) {
        mapLayerHoveredAfter(e.getSource(), hoveredMarkers, mapEventMouse);
        this.isHovered = false;
      }
      return;
    }
    Point mousePoint = mapEventMouse.getPoint();
    if (!this.isHovered) {
      this.hoveredMarkers = this.layer.getIntersectingMarkers(mousePoint,
              this.projector);
      if (hoveredMarkers.size() > 0) {
        this.mapLayerHoveredBefore(this, hoveredMarkers, mapEventMouse);
        this.isHovered = true;
      }
    } else {
      List<PointMarker> newHoveredMarkers = this.layer
              .getIntersectingMarkers(mousePoint, this.projector);
      if (this.isDifferentMarkers(newHoveredMarkers, hoveredMarkers)) {
        this.mapLayerHoveredAfter(this, hoveredMarkers, mapEventMouse);
        this.isHovered = false;
        this.hoveredMarkers = new ArrayList<>();
      }
    }
  }

  private boolean isDifferentMarkers(List<PointMarker> newHoveredMarkers,
          List<PointMarker> hoveredMarkers) {
    return !hoveredMarkers.equals(newHoveredMarkers);
  }

  /**
   * Adds a map layer hovered event listener to this manager.
   *
   * @param listener The listener to add.
   */
  public final void addMapLayerHoveredEventListener(
          MapLayerHoveredEvent.Listener<PointLayer, PointMarker> listener) {
    this.hoveredEventlisteners.add(listener);
  }


  public void destroy() {
    this.map.removeMouseListener(mouseAdapter);
    this.map.removeMouseMotionListener(mouseAdapter);
    this.layer = null;
    this.clickedEventlisteners.clear();
    this.hoveredEventlisteners.clear();
    this.hoveredMarkers.clear();
  }

  /**
   * This event fires before the hovering.
   *
   * @param source The source object for the event.
   * @param intersectingMarkers The list of markers involved in the hover area.
   * @param e The MapEventMouse args for the general mouse action.
   */
  private void mapLayerHoveredBefore(Object source,
          List<PointMarker> intersectingMarkers, MapEventMouse e) {

    MapLayerHoveredEvent<PointLayer, PointMarker> hoveredEvent
            = new MapLayerHoveredEvent<>(source, this.layer);
    hoveredEvent.setHoveredMarkers(intersectingMarkers, e);
    for (MapLayerHoveredEvent.Listener<PointLayer, PointMarker> listener
            : hoveredEventlisteners) {
      listener.before(hoveredEvent);
    }
    this.map.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
    this.map.setCursorLocked(true);
    this.map.repaint();
  }

  /**
   * This event fires after hovering.
   *
   * @param source The object source of the event.
   * @param intersectingMarkers The list of intersecting markers near where the hover is
   * occurring.
   * @param e The MapEventMouse object with mouse information.
   */
  private void mapLayerHoveredAfter(Object source,
          List<PointMarker> intersectingMarkers, MapEventMouse e) {
    MapLayerHoveredEvent<PointLayer, PointMarker> hoveredEvent
            = new MapLayerHoveredEvent<>(source, this.layer);
    hoveredEvent.setHoveredMarkers(intersectingMarkers, e);
    for (MapLayerHoveredEvent.Listener<PointLayer, PointMarker> listener : hoveredEventlisteners) {
      listener.after(hoveredEvent);
    }
    this.map.setCursor(new java.awt.Cursor(Cursor.DEFAULT_CURSOR));
    this.map.setCursorLocked(false);
    this.map.repaint();
  }

  /**
   * Adds a new click listener to this manager.
   *
   * @param listener The listener to add.
   */
  public final void addMapLayerClickedEventListener(
          MapLayerClickedEvent.Listener<PointLayer, PointMarker> listener) {
    this.clickedEventlisteners.add(listener);
  }

  /**
   * Enabled getter
   *
   * @return
   */
  public boolean isEnabled() {
    return enabled;
  }

  /**
   * Enabled setter.
   *
   * @param enabled
   */
  public void setEnabled(boolean enabled) {
    this.map.removeMouseMotionListener(mouseAdapter);
    this.map.removeMouseListener(mouseAdapter);
    if (enabled) {
      this.map.addMouseListener(mouseAdapter);
      this.map.addMouseMotionListener(mouseAdapter);
    }
    this.enabled = enabled;
  }

  /**
   * Triggers the click event on the specified layer.
   *
   * @param clickedMarkers The clicked markers to fire.
   * @param layerClick The layer to launch the event.
   * @param e The MapEventMouse args for the click action.
   */
  private void triggerClickedLayer(List<PointMarker> clickedMarkers,
          LayerClickType layerClick, MapEventMouse e) {
    MapLayerClickedEvent<PointLayer, PointMarker> clickedEvent
            = new MapLayerClickedEvent<>(this, layer);
    clickedEvent.setLayer(clickedMarkers, layerClick, e);
    for (MapLayerClickedEvent.Listener<PointLayer, PointMarker> listener : this.clickedEventlisteners) {
      switch (layerClick) {
        case SINGLE:
          listener.click(clickedEvent);
          break;
        case DOUBLE:
          listener.doubleClick(clickedEvent);
          break;
        case CONTEXT:
          listener.rightClick(clickedEvent);
          break;
        default:
          break;
      }
    }
    this.map.repaint();
  }

  /**
   * Triggers the mouse down event on the specified layer.
   *
   * @param markers The mouse down markers to fire.
   * @param layerClick The layer to launch the event.
   * @param e The MapEventMouse args for the mouse down action.
   */
  private void triggerMouseDownLayer(List<PointMarker> markers,
          LayerClickType layerClick, MapEventMouse e) {
    MapLayerClickedEvent<PointLayer, PointMarker> clickedEvent
            = new MapLayerClickedEvent<>(this, layer);
    clickedEvent.setLayer(markers, layerClick, e);
    for (MapLayerClickedEvent.Listener<PointLayer, PointMarker> listener : this.clickedEventlisteners) {
      listener.mouseDown(clickedEvent);
    }
  }

  /**
   * The method to handle left click actions.
   *
   * @param e The MouseEvent argument with mouse information.
   */
  private void doLeftClickActions(final MouseEvent e) {

    final MapLayerMarkerEventManager self = this;
    final MapEventMouse mapEventMouse = new MapEventMouse(self.map, e);
    this.clickCount = e.getClickCount();

    if (this.clickCount == LayerClickType.DOUBLE.getClicks() && !e.isConsumed()) {
      self.triggerClickedLayer(self.hoveredMarkers, LayerClickType.DOUBLE,
              mapEventMouse);
    } else {
      self.triggerClickedLayer(self.hoveredMarkers, LayerClickType.SINGLE,
              mapEventMouse);
      self.clickCount = 0;
    }

  }

  /**
   * The method to handle right click actions.
   *
   * @param e The MouseEvent with items.
   */
  private void doRightClickActions(MouseEvent e) {
    final MapEventMouse mapEventMouse = new MapEventMouse(this.map, e);
    this.triggerClickedLayer(this.hoveredMarkers, LayerClickType.CONTEXT,
            new MapEventMouse(this.map, mapEventMouse));
  }

  @Override
  public String toString() {
    return "MapLayerMarkerEventManager{" + "layer=" + layer + ", enabled=" + enabled + '}';
  }

  private class MouseAdapterImpl extends MouseAdapter {

    private final MapLayerMarkerEventManager self;

    public MouseAdapterImpl(MapLayerMarkerEventManager self) {
      this.self = self;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      if (!enabled) {
        return;
      }
      if (!self.isHovered) {
        return;
      }
      if (self.popupTrigger) {
        self.doRightClickActions(e);
      } else {
        self.doLeftClickActions(e);
      }
    }

    @Override
    public void mousePressed(MouseEvent e) {
      if (!enabled) {
        return;
      }
      if (hoveredMarkers == null || hoveredMarkers.size() == 0) {
        return;
      }
      if (!getLayer().isVisible()) {
        return;
      }
      final MapEventMouse mapEventMouse = new MapEventMouse(self.map, e);
      self.triggerMouseDownLayer(hoveredMarkers, LayerClickType.CONTEXT,
              new MapEventMouse(map, mapEventMouse));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      if (!enabled) {
        return;
      }
      self.popupTrigger = e.isPopupTrigger();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      doMouseMotion(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      doMouseMotion(e);
    }

    @Override
    public String toString() {
      return self.toString();
    }

  }

}
