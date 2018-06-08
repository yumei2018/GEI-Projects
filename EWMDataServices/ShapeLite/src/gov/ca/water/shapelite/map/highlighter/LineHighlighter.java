package gov.ca.water.shapelite.map.highlighter;

import gov.ca.water.shapelite.data.marker.LineMarker;
import gov.ca.water.shapelite.events.MapLayerHoveredEvent;
import gov.ca.water.shapelite.events.MapLayerMarkerPathEventManager;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.layer.LineLayer;
import gov.ca.water.shapelite.symbology.LineSymbolizer;

/**
 *
 * @author rmarquez
 */
public class LineHighlighter
        implements MapLayerHoveredEvent.Listener<LineLayer, LineMarker> {

  /**
   * The layer
   */
  private final LineLayer layer;

  /**
   * Original layer symbolizer
   */
  private final LineSymbolizer originalSymbolizer;

  /**
   * A MapPanel
   */
  private final MapPanel map;

  /**
   * The current hovered marker
   */
  private LineMarker hoveredMarker = null;

  /**
   * Initialized flag
   */
  private boolean initialized = false;

  /**
   * evtMgr
   */
  private MapLayerMarkerPathEventManager evtMgr;

  /**
   * Constructor.
   *
   * @param map
   * @param layer
   */
  public LineHighlighter(MapPanel map, LineLayer layer) {
    this.layer = layer;
    this.map = map;
    if (layer != null) {
      this.originalSymbolizer = this.layer.getDefaultSymbolizer().copy();
    } else {
      this.originalSymbolizer = null;
    }
  }

  /**
   * Enables highlighter
   *
   * @param enabled
   */
  public void setEnabled(boolean enabled) {
    if (!initialized) {
      this.evtMgr = new MapLayerMarkerPathEventManager(map, layer);
      this.evtMgr.addMapLayerHoveredEventListener(this);
      initialized = true;
    }
    this.evtMgr.setEnabled(enabled);
  }

  /**
   * Used by event trigger on before hover
   *
   * @param event
   */
  @Override
  public void before(MapLayerHoveredEvent<LineLayer, LineMarker> event) {
    for (LineMarker hm : event.getHoveredMarkers()) {
      if (hm.getSymbolizer() == null) {
        hm.setSymbolizer(originalSymbolizer.copy());
      }
    }
    LineMarker marker = event.getClosestToMapMouseLocation();
    this.hoveredMarker = marker;
    float newWidth = Math.max(marker.getSymbolizer().getWidth() + 3, 4);
    marker.getSymbolizer().setWidth(newWidth);
    marker.getSymbolizer();
    this.map.repaint();
    //this.map.getContent().paintImmediately();
  }

  /**
   * Used by event trigger on after hover
   *
   * @param event
   */
  @Override
  public void after(MapLayerHoveredEvent<LineLayer, LineMarker> event) {
    for (LineMarker hoveredMarker : event.getHoveredMarkers()) {
      hoveredMarker.getSymbolizer().setWidth(this.originalSymbolizer.getWidth());
    }
    this.map.repaint(); 
//    this.map.getContent().paintImmediately();
    this.hoveredMarker = null;
  }

  public LineMarker getHoveredMarker() {
    return hoveredMarker;
  }

  public void destroy() {
    this.evtMgr.destroy();
    this.map.getContent().removeLayer(layer);
  }

  public static class Null extends LineHighlighter {

    public Null() {
      super(null, null);
    }

    @Override
    public LineMarker getHoveredMarker() {
      return new LineMarker();
    }

    @Override
    public void after(MapLayerHoveredEvent<LineLayer, LineMarker> event) {
    }

    @Override
    public void before(MapLayerHoveredEvent<LineLayer, LineMarker> event) {
    }

    @Override
    public void setEnabled(boolean enabled) {
    }

    @Override
    public void destroy() {
    }
  }

}
