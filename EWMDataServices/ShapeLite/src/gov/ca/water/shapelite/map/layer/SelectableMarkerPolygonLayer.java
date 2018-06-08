
package gov.ca.water.shapelite.map.layer;


import gov.ca.water.shapelite.Command;
import gov.ca.water.shapelite.DefaultCommand;
import gov.ca.water.shapelite.data.marker.PolygonMarker;
import gov.ca.water.shapelite.events.MapLayerClickedEvent;
import gov.ca.water.shapelite.events.MapLayerMarkerPolygonEventManager;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.highlighter.PolygonHighlighter;
import gov.ca.water.shapelite.symbology.PolygonSymbolizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JMenuItem;

/**
 *
 * @author rmarquez
 */
public abstract class SelectableMarkerPolygonLayer extends PolygonLayer
    implements MapLayerClickedEvent.Listener<PolygonLayer, PolygonMarker> {

  protected final MapPanel map;
  protected PolygonMarker selectedXS = null;
  private PolygonHighlighter highlighter = new PolygonHighlighter.Null();
  protected Command<MapLayerContextMenu> onRequestContext = new DefaultCommand<>();
  protected MarkerSelectedEventManager<PolygonMarker> eventManager = new MarkerSelectedEventManager<>();
  private boolean isInitialized = false;
  private MapLayerMarkerPolygonEventManager evt = new MapLayerMarkerPolygonEventManager.Null();
  private PolygonLayerToolTipAdapter toolTip = new PolygonLayerToolTipAdapter.Null();

  public SelectableMarkerPolygonLayer(MapPanel map) {
    this.map = map;

  }

  public final void setOnRequestContext(Command<MapLayerContextMenu> onRequestContext) {
    this.onRequestContext = onRequestContext;
  }

  @Override
  public void doubleClick(MapLayerClickedEvent<PolygonLayer, PolygonMarker> event) {
  }

  @Override
  public void click(MapLayerClickedEvent<PolygonLayer, PolygonMarker> e) {
    PolygonMarker clickedMarker = e.getClosestToMapMouseLocation();
    selectedXS = clickedMarker;
    MarkerSelectedEvent<PolygonMarker> event = new MarkerSelectedEvent<PolygonMarker>(this, e.getMapMouseEvent()).setSelectedMarker(selectedXS);
    this.eventManager.triggerSelected(event);
  }

  @Override
  public void rightClick(MapLayerClickedEvent<PolygonLayer, PolygonMarker> event) {
    List<JMenuItem> ctxMenu = this.getContextMenuItems();
    this.onRequestContext.execute(new MapLayerContextMenu(event.getMapMouseEvent(), ctxMenu));
  }

  @Override
  public void setVisible(boolean visible) {
    super.setVisible(visible);
    this.evt.setEnabled(visible);
    this.highlighter.setEnabled(visible);
    this.toolTip.setEnabled(visible);
    if (this.map == null){
      return;
    }
    this.map.getContent().paintImmediately();
    this.map.repaint();
  }

  public abstract List<JMenuItem> getContextMenuItems();

  public void enableClickEvents(boolean enable) {
    if (!this.isInitialized) {

      this.evt = new MapLayerMarkerPolygonEventManager(map, this);
      this.evt.addMapLayerClickedEventListener(this);
      this.highlighter = new PolygonHighlighter(map, this, this.evt);
      this.toolTip = new PolygonLayerToolTipAdapter(map, this, this.evt);
      this.isInitialized = true;
    }
    this.evt.setEnabled(enable);
    this.highlighter.setEnabled(enable);
    this.toolTip.setEnabled(enable);
  }

  public final void addListener(MarkerSelectedEvent.Listener<PolygonMarker> listener) {
    eventManager.addListener(listener);
    if (this.selectedXS != null) {
      listener.onMarkerSelected(new MarkerSelectedEvent<PolygonMarker>(this, null).setSelectedMarker(selectedXS));
    }
  }

  public boolean contains(MarkerSelectedEvent.Listener<PolygonMarker> listener) {
    return eventManager.contains(listener);
  }


  public final void removeListener(MarkerSelectedEvent.Listener<PolygonMarker> listener) {
    eventManager.removeListener(listener);
  }

  public void zoomTo(PolygonMarker marker) {
    PolygonLayer layer = new PolygonLayer(Arrays.asList(marker));
    map.getContent().zoomToLayer(layer);
    map.getContent().setLevel(14);
    map.repaint();
  }

  @Override
  public void mouseDown(MapLayerClickedEvent<PolygonLayer, PolygonMarker> event){
  };

  /**
   * @return the highlighter
   */
  public PolygonHighlighter getHighlighter() {
    return highlighter;
  }

  /**
   * Sets selected field of all markers to false.
   */
  public void clearAllBreachFocus() {
    List<PolygonMarker> _markers = new ArrayList<>(this.getDataset().getMarkers());
    for (PolygonMarker marker : _markers) {
      marker.setSelected(false);
    }
  }

  public final void destroy() {
    this.eventManager.removeListeners();
    if (this.highlighter != null) {
      this.highlighter.destroy();
    }
    if (this.toolTip != null) {
      this.toolTip.destroy();
    }
    if (this.selectedXS != null) {
      this.selectedXS = null;
    }
    if (this.evt != null) {
      this.evt.destroy();
    }
  }

  public void setOriginalSymbolizer(PolygonSymbolizer symbolizer) {
    highlighter.setOriginalSymbolizer(symbolizer);
  }


}
