package gov.ca.water.shapelite.map.highlighter;

import gov.ca.water.shapelite.data.marker.PointMarker;
import gov.ca.water.shapelite.events.MapLayerHoveredEvent;
import gov.ca.water.shapelite.events.MapLayerMarkerEventManager;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.layer.PointLayer;
import gov.ca.water.shapelite.symbology.PointSymbolizer;
import java.awt.Dimension;

/**
 *
 * @author rmarquez
 */
public class PointHighlighter
        implements MapLayerHoveredEvent.Listener<PointLayer, PointMarker> {

  /**
   * The layer
   */
  private final PointLayer layer;

  /**
   * Original layer symbolizer
   */
  private final PointSymbolizer originalSymbolizer;

  /**
   * A MapPanel
   */
  private final MapPanel map;

  /**
   * The current hovered marker
   */
  private PointMarker hoveredMarker = null;


  /**
   * Initialized flag
   */
  private boolean initialized = false;

  /**
   * evtMgr
   */
  private MapLayerMarkerEventManager evtMgr;
  private Dimension onHoverDimension = new Dimension(10, 10);

  /**
   * Constructor.
   * @param map
   * @param layer
   */
  public PointHighlighter(MapPanel map, PointLayer layer) {
    this.layer = layer;
    this.map = map;
    this.originalSymbolizer = this.layer.getDefaultSymbolizer().copy();
    int newWidth = Math.max(this.originalSymbolizer.getWidth() + 16, 4);
    int newHeight = Math.max(this.originalSymbolizer.getHeight()+ 16, 4);
    onHoverDimension.setSize(newWidth, newHeight);
  }

  /**
   * Enables highlighter
   * @param enabled
   */
  public void setEnabled(boolean enabled) {
    if (!initialized) {
      this.evtMgr = new MapLayerMarkerEventManager(map, layer);
      this.evtMgr.addMapLayerHoveredEventListener(this);
      initialized = true;
    }
    this.evtMgr.setEnabled(enabled);
  }



  /**
   * Used by event trigger on before hover
   * @param event
   */
  @Override
  public void before(MapLayerHoveredEvent<PointLayer, PointMarker> event) {
    for (PointMarker hoveredMarker : event.getHoveredMarkers()) {
      if (hoveredMarker.getSymbolizer() == null) {
        hoveredMarker.setSymbolizer(originalSymbolizer.copy());
      }
    }
    PointMarker marker = event.getClosestToMapMouseLocation();
    this.hoveredMarker = marker;
    int newWidth = onHoverDimension.width;
    int newHeight = onHoverDimension.height;
    marker.getSymbolizer().setWidth(newWidth);
    marker.getSymbolizer().setHeight(newHeight);
    marker.getSymbolizer();

    this.map.getContent().paintImmediately();
    this.map.repaint();
  }


  /**
   * Used by event trigger on after hover
   * @param event
   */
  @Override
  public void after(MapLayerHoveredEvent<PointLayer, PointMarker> event) {
    for (PointMarker hoveredMarker : event.getHoveredMarkers()) {

      hoveredMarker.getSymbolizer().setWidth(this.originalSymbolizer.getWidth());
      hoveredMarker.getSymbolizer().setHeight(this.originalSymbolizer.getHeight());
    }
    this.map.getContent().paintImmediately();
    this.map.repaint();
    this.hoveredMarker = null;
  }

  public PointMarker getHoveredMarker() {
    return hoveredMarker;
  }
  public void setOriginalSymbolizer(PointSymbolizer symbolizer) {
    this.originalSymbolizer.copyProperties(symbolizer);
  }

  public void setOnHoverDimension(Dimension dimension) {
    this.onHoverDimension = dimension;
  }

  public void destroy() {
    this.evtMgr.destroy();
    this.map.getContent().removeLayer(layer);
  }

}
