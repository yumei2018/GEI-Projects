package gov.ca.water.shapelite.events;

import gov.ca.water.shapelite.Coord;

import gov.ca.water.shapelite.data.marker.LineMarker;
import gov.ca.water.shapelite.data.Projector;
import gov.ca.water.shapelite.map.layer.LineLayer;
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
public class MapLayerMarkerPathEventManager {

  /**
   * The MapLayerMarker layer that click and hover events are associated with.
   */
  private LineLayer layer;
  /**
   * The list of listeners for hover events.
   */
  private List<MapLayerHoveredEvent.Listener<LineLayer, LineMarker>> hoveredEventlisteners = new ArrayList<>();
  /**
   * The list of listeners for click events.
   */
  private List<MapLayerClickedEvent.Listener<LineLayer, LineMarker>> clickedEventlisteners = new ArrayList<>();
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
  private final List<LineMarker> hoveredMarkers = new ArrayList<>();
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
   * Enabled.
   */
  private boolean enabled;

  /**
   * Mouse adapter implementation
   */
  private final MouseAdapterImpl mouseAdapter;

  /**
   * Gets the MapLayerMarker marker layer for this manager.
   *
   * @return A MapLayerMarker.
   */
  public final LineLayer getLayer() {
    return this.layer;
  }

  /**
   * Creates a new instance of a MapLayerMarkerEventManager object.
   *
   * @param map The MapPanel to use for the events.
   * @param layer The MapLayerMarker layer for the events.
   */
  public MapLayerMarkerPathEventManager(MapPanel map, LineLayer layer) {
    this.layer = layer;
    this.map = map;
    if (this.map != null) {
      this.projector = this.map.getContent().getProjector();
      final MapLayerMarkerPathEventManager self = this;
      mouseAdapter = new MouseAdapterImpl(self);
      this.map.addMouseListener(mouseAdapter);
      this.map.addMouseMotionListener(mouseAdapter);
    } else {
      this.mouseAdapter = null;
      this.projector = null;
    }
  }

  /**
   * Gets enabled.
   *
   * @return Boolean, true if this manager is enabled.
   */
  public final boolean isEnabled() {
    return enabled;
  }

  /**
   * Sets enabled.
   *
   * @param enabled Boolean, true if the manager should become enabled.
   */
  public void setEnabled(boolean enabled) {
    this.map.removeMouseListener(this.mouseAdapter);
    this.map.removeMouseMotionListener(this.mouseAdapter);
    if (enabled) {
      this.map.addMouseListener(this.mouseAdapter);
      this.map.addMouseMotionListener(this.mouseAdapter);
    }
    this.enabled = enabled;
  }

  /**
   * Adds a map layer hovered event listener to this manager.
   *
   * @param listener The listener to add.
   */
  public void addMapLayerHoveredEventListener(
          MapLayerHoveredEvent.Listener<LineLayer, LineMarker> listener) {
    this.hoveredEventlisteners.add(listener);
  }

  public void destroy() {
    if (this.map != null) {
      this.map.removeMouseListener(mouseAdapter);
      this.map.removeMouseMotionListener(mouseAdapter);
    }
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
          List<LineMarker> intersectingMarkers, MapEventMouse e) {
    MapLayerHoveredEvent<LineLayer, LineMarker> hoveredEvent
            = new MapLayerHoveredEvent<>(source, this.layer);
    hoveredEvent.setHoveredMarkers(intersectingMarkers, e);
    for (MapLayerHoveredEvent.Listener<LineLayer, LineMarker> listener
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
          List<LineMarker> intersectingMarkers, MapEventMouse e) {
    MapLayerHoveredEvent<LineLayer, LineMarker> hoveredEvent
            = new MapLayerHoveredEvent<>(source, this.layer);
    hoveredEvent.setHoveredMarkers(intersectingMarkers, e);
    for (MapLayerHoveredEvent.Listener<LineLayer, LineMarker> listener : hoveredEventlisteners) {
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
  public void addMapLayerClickedEventListener(
          MapLayerClickedEvent.Listener<LineLayer, LineMarker> listener) {
    this.clickedEventlisteners.add(listener);
  }

  /**
   * Triggers the click event on the specified layer.
   *
   * @param clickedMarkers The clicked markers to fire.
   * @param layerClick The layer to launch the event.
   * @param e The MapEventMouse args for the click action.
   */
  private void triggerClickedLayer(List<LineMarker> clickedMarkers,
          gov.ca.water.shapelite.events.LayerClickType layerClick,
          MapEventMouse e) {
    MapLayerClickedEvent<LineLayer, LineMarker> clickedEvent
            = new MapLayerClickedEvent<>(this, layer);
    clickedEvent.setLayer(clickedMarkers, layerClick, e);
    for (MapLayerClickedEvent.Listener<LineLayer, LineMarker> listener
            : this.clickedEventlisteners) {
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
   * The method to handle left click actions.
   *
   * @param e The MouseEvent argument with mouse information.
   */
  private void doLeftClickActions(final MouseEvent e) {
    final MapLayerMarkerPathEventManager self = this;
    final MapEventMouse mapEventMouse = new MapEventMouse(self.map, e);
    this.clickCount = e.getClickCount();

    if (this.clickCount
            == gov.ca.water.shapelite.events.LayerClickType.DOUBLE.getClicks()
            && !e.isConsumed()) {
      self.triggerClickedLayer(self.hoveredMarkers,
              gov.ca.water.shapelite.events.LayerClickType.DOUBLE,
              mapEventMouse);
    } else {
      self.triggerClickedLayer(self.hoveredMarkers,
              gov.ca.water.shapelite.events.LayerClickType.SINGLE,
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
    this.triggerClickedLayer(this.hoveredMarkers,
            gov.ca.water.shapelite.events.LayerClickType.CONTEXT,
            new MapEventMouse(this.map, mapEventMouse));
  }

  public static class Null extends MapLayerMarkerPathEventManager {

    public Null() {
      super(null, null);
    }

    @Override
    public void addMapLayerClickedEventListener(MapLayerClickedEvent.Listener<LineLayer, LineMarker> listener) {
    }

    @Override
    public void addMapLayerHoveredEventListener(MapLayerHoveredEvent.Listener<LineLayer, LineMarker> listener) {
    }

    @Override
    public void setEnabled(boolean enabled) {
    }

  }

  @Override
  public String toString() {
    return "MapLayerMarkerPathEventManager{" + "layer=" + layer + ", enabled=" + enabled + '}';
  }

  private class MouseAdapterImpl extends MouseAdapter {

    private final MapLayerMarkerPathEventManager self;
    private boolean mouseDragging = false;

    public MouseAdapterImpl(MapLayerMarkerPathEventManager self) {
      this.self = self;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      if (!e.isConsumed()) {
        mouseDragging = true;
      }
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
    public void mouseReleased(MouseEvent e) {
      if (!enabled) {
        return;
      }

      self.popupTrigger = e.isPopupTrigger();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      if (!self.map.getContent().getAllLayers().contains(layer)) {
        setEnabled(false);
        return;
      }
      if (!enabled) {
        return;
      }

      if (!self.layer.isVisible()) {
        return;
      }

      if (e.getModifiersEx() == MouseEvent.BUTTON1_MASK) {
        System.out.println("mouse dragging");
      }

      //      if point not in layer envelope do nothing.
      MapEventMouse mapEventMouse = new MapEventMouse(self.map, e);
      Coord mousePointLocation = Mercator.toMerc(mapEventMouse.getLocation());
      boolean isEnclosedMapPoint
              = self.layer.getEnvelope().containsPoint2D(mousePointLocation);
      if (!isEnclosedMapPoint) {
        return;
      }

      Point mousePoint = mapEventMouse.getPoint();
      if (!self.isHovered) {
        self.hoveredMarkers.clear();
        self.hoveredMarkers.addAll(self.layer.getIntersectingMarkers(mousePoint,
                self.projector));
        if (hoveredMarkers.size() > 0) {
          self.mapLayerHoveredBefore(self, hoveredMarkers, mapEventMouse);
          self.isHovered = true;
        }
      } else {
        List<LineMarker> newHoveredMarkers = self.layer
                .getIntersectingMarkers(mousePoint, self.projector);
        if (isDifferentMarkers(newHoveredMarkers, hoveredMarkers)) {
          self.mapLayerHoveredAfter(self, hoveredMarkers, mapEventMouse);
          self.isHovered = false;
          self.hoveredMarkers.clear();
        }
      }
    }

    private boolean isDifferentMarkers(List<LineMarker> newHoveredMarkers,
            List<LineMarker> hoveredMarkers) {
      return !hoveredMarkers.equals(newHoveredMarkers);
    }
  }

}
