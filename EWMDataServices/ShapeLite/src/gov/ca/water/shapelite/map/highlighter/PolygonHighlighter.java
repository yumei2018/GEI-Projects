package gov.ca.water.shapelite.map.highlighter;

import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.data.marker.PolygonMarker;
import gov.ca.water.shapelite.events.MapLayerHoveredEvent;
import gov.ca.water.shapelite.events.MapLayerMarkerPolygonEventManager;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.layer.PolygonLayer;
import gov.ca.water.shapelite.symbology.PolygonSymbolizer;

/**
 *
 * @author rmarquez
 */
public class PolygonHighlighter
        implements MapLayerHoveredEvent.Listener<PolygonLayer, PolygonMarker> {

  /**
   * The layer
   */
  private final PolygonLayer layer;

  /**
   * Original layer symbolizer
   */
  private PolygonSymbolizer originalSymbolizer;

  /**
   * A MapPanel
   */
  private final MapPanel map;

  /**
   * The current hovered marker
   */
  private PolygonMarker hoveredMarker = null;

  /**
   * Initialized flag
   */
  private boolean initialized = false;

  /**
   * evtMgr
   */
  private MapLayerMarkerPolygonEventManager evtMgr = null;

  /**
   * Constructor.
   *
   * @param map
   * @param layer
   */
  public PolygonHighlighter(MapPanel map, PolygonLayer layer) {
    this.layer = layer;
    this.map = map;
    if (layer != null) {
      this.originalSymbolizer = this.layer.getDefaultSymbolizer().copy();
    } else {
      this.originalSymbolizer = null;
    }
  }

  public PolygonHighlighter(MapPanel map, PolygonLayer layer, MapLayerMarkerPolygonEventManager evtMgr) {
    this.layer = layer;
    this.map = map;
    this.evtMgr = evtMgr;
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
      if (this.evtMgr == null) {
        this.evtMgr = new MapLayerMarkerPolygonEventManager(map, layer);
      }
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
  public void before(MapLayerHoveredEvent<PolygonLayer, PolygonMarker> event) {
    for (PolygonMarker hm : event.getHoveredMarkers()) {
      if (hm.getSymbolizer() == null) {
        hm.setSymbolizer(originalSymbolizer.copy());
      }
    }
    PolygonMarker marker = event.getClosestToMapMouseLocation();
    this.hoveredMarker = marker;
    float newWidth = Math.max(marker.getSymbolizer().getBorderWidth() + 3, 4);
    marker.getSymbolizer().setBorderWidth(newWidth);
    marker.getSymbolizer();
    this.map.getContent().paintImmediately();

  }

  /**
   * Used by event trigger on after hover
   *
   * @param event
   */
  @Override
  public void after(MapLayerHoveredEvent<PolygonLayer, PolygonMarker> event) {
    for (PolygonMarker hoveredMarker : event.getHoveredMarkers()) {
      hoveredMarker.getSymbolizer().setBorderWidth(this.originalSymbolizer.getBorderWidth());
    }
    this.map.repaint();
    this.hoveredMarker = null;
  }

  public PolygonMarker getHoveredMarker() {
    return hoveredMarker;
  }

  public void destroy() {
    if (evtMgr != null) {
      this.evtMgr.destroy();
    }
    if (this.map != null) {
      this.map.getContent().removeLayer(layer);
    }
  }

  
  public void setOriginalSymbolizer(PolygonSymbolizer symbolizer) {
    this.originalSymbolizer = symbolizer;
  }

  public static class Null extends PolygonHighlighter {

    public Null() {
      super(null, null);
    }

    @Override
    public PolygonMarker getHoveredMarker() {
      return new PolygonMarker(new Shape());
    }

    @Override
    public void after(MapLayerHoveredEvent<PolygonLayer, PolygonMarker> event) {
    }

    @Override
    public void before(MapLayerHoveredEvent<PolygonLayer, PolygonMarker> event) {
    }

    @Override
    public void setEnabled(boolean enabled) {
    }
  }

}
