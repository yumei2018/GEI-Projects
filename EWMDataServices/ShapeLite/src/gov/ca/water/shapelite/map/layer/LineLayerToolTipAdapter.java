package gov.ca.water.shapelite.map.layer;

import gov.ca.water.shapelite.data.marker.FeatureMarker;
import gov.ca.water.shapelite.data.marker.LineMarker;
import gov.ca.water.shapelite.events.MapLayerHoveredEvent;
import gov.ca.water.shapelite.events.MapLayerMarkerPathEventManager;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.maptool.MapTool;
import gov.ca.water.shapelite.map.maptool.ToolTipMapTool;
import gov.ca.water.shapelite.symbology.Symbolizer;
import java.util.List;

/**
 *
 * @author rmarquez
 */
public class LineLayerToolTipAdapter implements MapLayerHoveredEvent.Listener<LineLayer, LineMarker> {

  private final LineLayer layer;
  private final MapPanel mapPanel;
  private boolean initialized = false;
  private MapLayerMarkerPathEventManager eventManager;
  private MapTool mapToolTip;

  /**
   * Constructor. Use method .setEnabled(true) to initialize.
   *
   * @param panel A MapPanel
   * @param layer A LineLayer
   */
  public LineLayerToolTipAdapter(MapPanel panel, LineLayer layer) {
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

  /**
   * Enables the tooltip.
   *
   * @param enabled
   */
  public void setEnabled(Boolean enabled) {
    if (!initialized) {
      this.eventManager = new MapLayerMarkerPathEventManager(this.mapPanel, layer);
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
  public void before(MapLayerHoveredEvent<LineLayer, LineMarker> event) {
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
  public void after(MapLayerHoveredEvent<LineLayer, LineMarker> event) {
    this.mapPanel.setToolTipText("");
    this.mapToolTip.setActive(false);
  }

  public void destroy() {
    if (eventManager != null) {
      this.eventManager.destroy();
    }
    if (mapToolTip != null) {
      this.mapToolTip.setActive(false);
    }
  }

  static class Null extends LineLayerToolTipAdapter {

    public Null() {
      super(null, null);
    }

    @Override
    public void after(MapLayerHoveredEvent<LineLayer, LineMarker> event) {
    }

    @Override
    public void before(MapLayerHoveredEvent<LineLayer, LineMarker> event) {
    }

    @Override
    public void setEnabled(Boolean enabled) {
    }

  }
}
