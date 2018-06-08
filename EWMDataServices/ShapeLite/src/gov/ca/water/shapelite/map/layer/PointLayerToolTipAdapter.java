package gov.ca.water.shapelite.map.layer;

import gov.ca.water.shapelite.data.marker.PointMarker;
import gov.ca.water.shapelite.events.MapLayerHoveredEvent;
import gov.ca.water.shapelite.events.MapLayerMarkerEventManager;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.maptool.MapTool;
import gov.ca.water.shapelite.map.maptool.ToolTipMapTool;
import java.util.List;

/**
 *
 * @author rmarquez
 */
public class PointLayerToolTipAdapter 
        implements MapLayerHoveredEvent.Listener<PointLayer, PointMarker> {
  
  private final PointLayer layer;
  private final MapPanel mapPanel;
  private boolean initialized = false;
  private MapLayerMarkerEventManager eventManager;
  private MapTool mapToolTip;

  /**
   * Constructor. Use method .setEnabled(true) to initialize.
   *
   * @param panel A MapPanel
   * @param layer A PointLayer
   */
  public PointLayerToolTipAdapter(MapPanel panel, PointLayer layer) {
    this.layer = layer;
    this.mapPanel = panel;
    List<MapTool> tools = mapPanel.getToolbox().getTools();
    for (MapTool tool : tools) {
      if (tool instanceof ToolTipMapTool) {
        this.mapToolTip = tool;
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
      this.eventManager = new MapLayerMarkerEventManager(this.mapPanel, layer);
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
  public void before(MapLayerHoveredEvent<PointLayer, PointMarker> event) {
    PointMarker marker0 = event.getClosestToMapMouseLocation();
    this.mapPanel.setToolTipText(marker0.getPopupText());
    this.mapToolTip.setActive(true);
  }

  /**
   * Used by triggers to after hover event.
   *
   * @param event
   */
  @Override
  public void after(MapLayerHoveredEvent<PointLayer, PointMarker> event) {
    this.mapPanel.setToolTipText("");
    this.mapToolTip.setActive(false);
  }

  public void destroy() {
    this.eventManager.destroy();
    this.mapToolTip.setActive(false);
  }
}
