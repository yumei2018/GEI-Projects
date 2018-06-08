package gov.ca.water.shapelite.map.layer;

import gov.ca.water.shapelite.data.marker.FeatureMarker;
import gov.ca.water.shapelite.data.marker.PolygonMarker;
import gov.ca.water.shapelite.events.MapLayerHoveredEvent;
import gov.ca.water.shapelite.events.MapLayerMarkerPolygonEventManager;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.maptool.MapTool;
import gov.ca.water.shapelite.map.maptool.ToolTipMapTool;
import gov.ca.water.shapelite.symbology.Symbolizer;
import java.util.List;

/**
 *
 * @author rmarquez
 */
public class PolygonLayerToolTipAdapter implements MapLayerHoveredEvent.Listener<PolygonLayer, PolygonMarker> {

  private final PolygonLayer layer;
  private final MapPanel mapPanel;
  private boolean initialized = false;
  private MapLayerMarkerPolygonEventManager eventManager;
  private MapTool mapToolTip;

  /**
   * Constructor. Use method .setEnabled(true) to initialize.
   *
   * @param panel A MapPanel
   * @param layer A PolygonLayer
   */
  public PolygonLayerToolTipAdapter(MapPanel panel, PolygonLayer layer) {
    this.layer = layer;
    this.mapPanel = panel;
    if (panel != null) {
      List<MapTool> tools = mapPanel.getToolbox().getTools();
      for (MapTool tool : tools) {
        if (tool instanceof ToolTipMapTool) {
          this.mapToolTip = tool;
        }
      }
    }
  }

  public PolygonLayerToolTipAdapter(MapPanel panel, SelectableMarkerPolygonLayer layer, MapLayerMarkerPolygonEventManager evt) {
    this.layer = layer;
    this.mapPanel = panel;
    this.eventManager = evt;
    if (panel != null) {
      List<MapTool> tools = mapPanel.getToolbox().getTools();
      for (MapTool tool : tools) {
        if (tool instanceof ToolTipMapTool) {
          this.mapToolTip = tool;
        }
      }
    }
  }

  /**
   * Enables the tooltip.
   *
   * @param enabled
   */
  public void setEnabled(Boolean enabled) {
    if (!initialized) {
      if (this.eventManager == null) {
        this.eventManager = new MapLayerMarkerPolygonEventManager(this.mapPanel, layer);
      }
      eventManager.addMapLayerHoveredEventListener(this);
      initialized = true;
    }
    eventManager.setEnabled(enabled);
  }

  /**
   * Used by triggers to before hover event
   *
   * @param event
   */
  @Override
  public void before(MapLayerHoveredEvent<PolygonLayer, PolygonMarker> event) {
    FeatureMarker<? extends Symbolizer> marker0 = event.getClosestToMapMouseLocation();
    this.mapPanel.setToolTipText(marker0.getPopupText());
    this.mapToolTip.setActive(true);
  }

  /**
   * Used by triggers to after hover event.
   *
   * @param event
   */
  @Override
  public void after(MapLayerHoveredEvent<PolygonLayer, PolygonMarker> event) {
    this.mapPanel.setToolTipText("");
    this.mapToolTip.setActive(false);
  }

  public void destroy() {
    if (this.eventManager != null) {
      this.eventManager.destroy();
    }
    if (this.mapToolTip != null) {
      this.mapToolTip.setActive(false);
    }
  }

  static class Null extends PolygonLayerToolTipAdapter {
    public Null() {
      super(null, null);
    }

    @Override
    public void after(MapLayerHoveredEvent<PolygonLayer, PolygonMarker> event) {
    }

    @Override
    public void before(MapLayerHoveredEvent<PolygonLayer, PolygonMarker> event) {
    }

    @Override
    public void setEnabled(Boolean enabled) {
    }

  }
}
