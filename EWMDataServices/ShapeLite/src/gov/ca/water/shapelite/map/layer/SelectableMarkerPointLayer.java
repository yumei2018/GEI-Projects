package gov.ca.water.shapelite.map.layer;

import gov.ca.water.shapelite.Command;
import gov.ca.water.shapelite.DefaultCommand;
import gov.ca.water.shapelite.data.marker.PointMarker;
import gov.ca.water.shapelite.events.MapLayerClickedEvent;
import gov.ca.water.shapelite.events.MapLayerHoveredEvent;
import gov.ca.water.shapelite.events.MapLayerMarkerEventManager;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.highlighter.PointHighlighter;
import gov.ca.water.shapelite.symbology.PointSymbolizer;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

/**
 *
 * @author rmarquez
 */
public abstract class SelectableMarkerPointLayer extends PointLayer
        implements MapLayerClickedEvent.Listener<PointLayer, PointMarker>,
        MapLayerHoveredEvent.Listener<PointLayer, PointMarker> {

  protected final MapPanel map;
  protected PointMarker selected = null;
  protected PointHighlighter highlighter;
  protected Command<MapLayerContextMenu> onRequestContext = new DefaultCommand<>();
  protected final MarkerSelectedEventManager<PointMarker> eventManager
          = new MarkerSelectedEventManager<>();
  private boolean isInitialized = false;
  private PointLayerToolTipAdapter toolTip;
  private MapLayerMarkerEventManager evt;
  private List<MarkerSelectedEvent.Listener<PointMarker>> listeners = new ArrayList<>();

  public SelectableMarkerPointLayer(MapPanel map) {
    this.map = map;
  }

  public final void setOnRequestContext(Command<MapLayerContextMenu> onRequestContext) {
    this.onRequestContext = onRequestContext;
  }

  @Override
  public void doubleClick(MapLayerClickedEvent<PointLayer, PointMarker> event) {
  }

  @Override
  public void click(MapLayerClickedEvent<PointLayer, PointMarker> e) {
    PointMarker clickedMarker = e.getClosestToMapMouseLocation();
    selected = clickedMarker;
    MarkerSelectedEvent<PointMarker> event
            = new MarkerSelectedEvent<PointMarker>(this, e.getMapMouseEvent()).setSelectedMarker(selected);
    this.eventManager.triggerSelected(event);
  }

  @Override
  public void rightClick(MapLayerClickedEvent<PointLayer, PointMarker> event) {
    List<JMenuItem> ctxMenu = this.getContextMenuItems();
    this.onRequestContext.execute(new MapLayerContextMenu(event.getMapMouseEvent(), ctxMenu));
  }

  @Override
  public void setVisible(boolean visible) {
    super.setVisible(visible);
    this.evt.setEnabled(visible);
    this.highlighter.setEnabled(visible);
    this.toolTip.setEnabled(visible);
    this.setSelectable(visible);
    this.map.getContent().paintImmediately();
    this.map.repaint();
  }

  public abstract List<JMenuItem> getContextMenuItems();

  public void enableClickEvents(boolean enable) {
    initialize();
    this.evt.setEnabled(enable);
    this.highlighter.setEnabled(enable);
    this.toolTip.setEnabled(enable);
    this.setSelectable(enable);
  }

  public void initialize() {
    if (this.isInitialized) {
      return;
    }
    this.evt = new MapLayerMarkerEventManager(map, this);
    this.evt.addMapLayerClickedEventListener(this);
    this.evt.addMapLayerHoveredEventListener(this);
    this.highlighter = new PointHighlighter(map, this);
    this.toolTip = new PointLayerToolTipAdapter(map, this);
    this.isInitialized = true;
  }

  public final void addListener(MarkerSelectedEvent.Listener<PointMarker> listener) {
    this.listeners.add(listener);
    eventManager.addListener(listener);
    if (this.selected != null) {
      MarkerSelectedEvent<PointMarker> e = new MarkerSelectedEvent<>(this, null);
      e.setSelectedMarker(selected);
      listener.onMarkerSelected(e);
    }
  }

  public final void removeListener(MarkerSelectedEvent.Listener<PointMarker> listener) {
    this.listeners.remove(listener);
    eventManager.removeListener(listener);
  }

  public void zoomTo(PointMarker marker) {
    int s = map.getContent().getClosestLevel();
    map.getContent().zoomToCoordinate(marker.getCoordinate());
    map.getContent().setLevel(Math.max(12, s - 1));
    map.repaint();
  }

  public void setOnHoverSize(Dimension dimension) {
    this.highlighter.setOnHoverDimension(dimension);
  }

  public void setOriginalSymbolizer(PointSymbolizer symbolizer) {
    highlighter.setOriginalSymbolizer(symbolizer);
  }


  @Override
  public void mouseDown(MapLayerClickedEvent<PointLayer, PointMarker> event) {
  }

  @Override
  public void before(MapLayerHoveredEvent<PointLayer, PointMarker> event) {
    final MarkerSelectedEvent<PointMarker> e
            = new MarkerSelectedEvent<>(event.getSource(), event.getMapMouseEvent());
    e.setSelectedMarker(event.getClosestToMapMouseLocation());
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        for (final MarkerSelectedEvent.Listener<PointMarker> listener : listeners) {
          listener.onMarkerHoveredBefore(e);
        }
      }
    });
  }

  @Override
  public void after(MapLayerHoveredEvent<PointLayer, PointMarker> event) {
    final MarkerSelectedEvent<PointMarker> e
            = new MarkerSelectedEvent<>(event.getSource(), event.getMapMouseEvent());
    e.setSelectedMarker(event.getClosestToMapMouseLocation());
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        for (final MarkerSelectedEvent.Listener<PointMarker> listener : listeners) {
          listener.onMarkerHoveredAfter(e);
        }
      }
    });
  }

  public final void destroy() {
    this.eventManager.removeListeners();
    if (this.highlighter != null) {
      this.highlighter.destroy();
    }
    if (this.toolTip != null) {
      this.toolTip.destroy();
    }
    if (this.selected != null) {
      this.selected = null;
    }
    if (this.evt != null) {
      this.evt.destroy();
    }
  }

}
