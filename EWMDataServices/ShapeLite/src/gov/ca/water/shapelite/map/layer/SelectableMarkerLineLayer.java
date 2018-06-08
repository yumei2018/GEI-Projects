
package gov.ca.water.shapelite.map.layer;

import gov.ca.water.shapelite.Command;
import gov.ca.water.shapelite.DefaultCommand;
import gov.ca.water.shapelite.data.marker.LineMarker;
import gov.ca.water.shapelite.events.MapLayerClickedEvent;
import gov.ca.water.shapelite.events.MapLayerMarkerPathEventManager;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.highlighter.LineHighlighter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JMenuItem;

/**
 *
 * @author rmarquez
 */
public abstract class SelectableMarkerLineLayer extends LineLayer
        implements MapLayerClickedEvent.Listener<LineLayer, LineMarker> {

  protected final MapPanel map;
  protected LineMarker selected = null;
  private LineHighlighter highlighter = new LineHighlighter.Null();
  protected Command<MapLayerContextMenu> onRequestContext = new DefaultCommand<>();
  protected MarkerSelectedEventManager<LineMarker> eventManager = new MarkerSelectedEventManager<>();
  private boolean isInitialized = false;
  private MapLayerMarkerPathEventManager evt = new MapLayerMarkerPathEventManager.Null();
  private LineLayerToolTipAdapter toolTip = new LineLayerToolTipAdapter.Null();

  public SelectableMarkerLineLayer(MapPanel map) {
    this.map = map;
  }

  public final void setOnRequestContext(Command<MapLayerContextMenu> onRequestContext) {
    this.onRequestContext = onRequestContext;
  }

  @Override
  public void doubleClick(MapLayerClickedEvent<LineLayer, LineMarker> event) {
  }

  @Override
  public void click(MapLayerClickedEvent<LineLayer, LineMarker> e) {
    LineMarker clickedMarker = e.getClosestToMapMouseLocation();
    selected = clickedMarker;
    MarkerSelectedEvent<LineMarker> event
            = new MarkerSelectedEvent<LineMarker>(this,
                    e.getMapMouseEvent()).setSelectedMarker(selected);
    this.eventManager.triggerSelected(event);
  }

  @Override
  public void rightClick(MapLayerClickedEvent<LineLayer, LineMarker> event) {
    List<JMenuItem> ctxMenu = this.getContextMenuItems();
    this.onRequestContext.execute(new MapLayerContextMenu(event.getMapMouseEvent(), ctxMenu));
  }

  @Override
  public void setVisible(boolean visible) {
    super.setVisible(visible);

    this.evt.setEnabled(visible);
    this.highlighter.setEnabled(visible);
    this.toolTip.setEnabled(visible);
    if (this.map == null) {
      return;
    }
    this.map.getContent().paintImmediately();
    this.map.repaint();
  }

  public abstract List<JMenuItem> getContextMenuItems();

  public void enableClickEvents(boolean enable) {
    if (!this.isInitialized && !enable) {
      return;
    }

    if (!this.isInitialized) {
      this.highlighter = new LineHighlighter(map, this);
      this.evt = new MapLayerMarkerPathEventManager(map, this);
      this.evt.addMapLayerClickedEventListener(this);
      this.toolTip = new LineLayerToolTipAdapter(map, this);
      this.isInitialized = true;
    }
    this.evt.setEnabled(enable);
    this.highlighter.setEnabled(enable);
    this.toolTip.setEnabled(enable);
  }



  public final void addListener(MarkerSelectedEvent.Listener<LineMarker> listener) {
    eventManager.addListener(listener);
    if (this.selected != null) {
      listener.onMarkerSelected(
              new MarkerSelectedEvent<LineMarker>(this, null).setSelectedMarker(selected));
    }
  }

  public final void removeListener(MarkerSelectedEvent.Listener<LineMarker> listener) {
    eventManager.removeListener(listener);
  }

  /**
   *
   * @param marker
   */
  public void zoomTo(LineMarker marker) {
    int s = map.getContent().getClosestLevel();
    map.getContent().zoomToEnv(marker.getFeature().getEnvelope());
    map.getContent().setLevel(Math.max(12, s - 1));
    map.repaint();
  }

  @Override
  public void mouseDown(MapLayerClickedEvent<LineLayer, LineMarker> event) {
  }

  /**
   * @return the highlighter
   */
  public LineHighlighter getHighlighter() {
    return highlighter;
  }

  /**
   * Sets selected field of all markers to false.
   */
  public void clearAllBreachFocus() {
    List<LineMarker> _markers = new ArrayList<>(this.getDataset().getMarkers());
    for (LineMarker marker : _markers) {
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
    if (this.selected != null) {
      this.selected = null;
    }
    if (this.evt != null) {
      this.evt.destroy();
    }
  }

}
