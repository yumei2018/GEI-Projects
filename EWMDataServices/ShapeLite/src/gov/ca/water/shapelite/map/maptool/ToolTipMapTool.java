/*
 * The MIT License
 *
 * Copyright 2012 hdunsford.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gov.ca.water.shapelite.map.maptool;

import gov.ca.water.shapelite.map.layer.LineLayer;
import gov.ca.water.shapelite.map.layer.Layer;
import gov.ca.water.shapelite.map.layer.PointLayer;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.events.MapEventMouse;
import java.util.List;
import javax.swing.ToolTipManager;
import gov.ca.water.shapelite.data.marker.PointMarker;
import gov.ca.water.shapelite.data.marker.LineMarker;
import gov.ca.water.shapelite.data.marker.PolygonMarker;
import gov.ca.water.shapelite.map.layer.PolygonLayer;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This tool controls showing a tool tip relevant to a marker as the mouse moves
 * over the marker. Each marker has a tooltipText attribute that controls the
 * text value displayed.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ToolTipMapTool extends MapTool {

  public ToolTipMapTool() {
    super();
    super.setName("tooltip");
  }

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * A boolean indicating whether or not the tool tip is actively showing.
   */
  private boolean showing;

  /**
   * The layer that the current tool tip was set for.
   */
  private Layer<?> layerShowing;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Occurs when the mouse exist the control so that the dismiss delay can be
   * set to 3000. Otherwise the tool tips on other controls will not work.
   *
   * @param e A MapEventMouse with information about the Map and the mouse
   * condition.
   */
  @Override
  public void mouseExited(MapEventMouse e) {
    ToolTipManager.sharedInstance().setDismissDelay(3000);
  }

  /**
   * Occurs when the mouse is moved without being dragged. This tests if the
   * mouse is currently over a marker, and if it is, shows the tool tip.
   *
   * @param e A MapEventMouse argument with information about the Map and mouse
   * condition.
   */
  @Override
  public void mouseMoved(MapEventMouse e) {
    boolean found = false;
    Layer<?> foundLayer = null;
    List<DistanceLabel> labels = new ArrayList<>();
    Coord location = e.getProj().getCoordinate(e.getPoint());
    for (Layer<?> layer : e.getMap().getContent().getLayers()) {
      if (!layer.isVisible()) {
        continue;
      }
      if (layer instanceof PointLayer) {
        PointLayer mlayer = (PointLayer) layer;
        List<PointMarker> mlist = mlayer.getIntersectingMarkers(e.getPoint(),
            e.getProj());
        for (PointMarker marker : mlist) {
          double dist = location.distance(marker.getCoordinate());
          String text = marker.getPopupText();
          if (text == null) {
            text = marker.getName();
          }
          DistanceLabel label = new DistanceLabel(dist, text);
          labels.add(label);
          foundLayer = layer;
        }
      }
      if (layer instanceof LineLayer) {
        if (layerShowing != null && layerShowing instanceof PointLayer) {
          continue;
        }
        LineLayer mlayer = (LineLayer) layer;
        List<LineMarker> mlist = mlayer.getIntersectingMarkers(e.getPoint(),
            e.getProj());
        for (LineMarker marker : mlist) {
          Optional<Double> distance = marker.getShape().distance(location);
          if (distance.isPresent()) {
            String text = marker.getPopupText();
            if (text == null) {
              text = marker.getName();
            }
            DistanceLabel label = new DistanceLabel(distance.get(), text);
            labels.add(label);
            foundLayer = layer;
          }

        }
      }
      if (layer instanceof PolygonLayer) {
        if (layerShowing != null && !(layerShowing instanceof PolygonLayer)) {
          continue;
        }
        PolygonLayer pLayer = (PolygonLayer) layer;
        List<PolygonMarker> plist = pLayer.getIntersectingMarkers(e.getPoint(),
            e.getProj());
        for (PolygonMarker marker : plist) {
          if (marker.getShape().intersects(location)) {
            DistanceLabel label = new DistanceLabel(0, marker.getPopupText());
            labels.add(label);
            foundLayer = layer;
          }

        }
      }
    }

    if (labels.size() > 0) {
      Collections.sort(labels);
      String text = labels.get(0).label;
      if (!showing) {
        ToolTipManager.sharedInstance().registerComponent(e.getMap());
        // Normally tool tips show slowly.  For the map, use no delay.
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(3000);

      }
      e.getMap().setToolTipText(text);
      showing = true;
      layerShowing = foundLayer;
      found = true;
    }

    if (!found) {
      if (showing) {
        // In order to hide a showing tool tip right away, we have to alter the
        // dismiss delay time.
        ToolTipManager.sharedInstance().setDismissDelay(0);
        e.getMap().setToolTipText(null);
        ToolTipManager.sharedInstance().setInitialDelay(200);
        ToolTipManager.sharedInstance().unregisterComponent(e.getMap());
        showing = false;
        layerShowing = null;
      } else {
        ToolTipManager.sharedInstance().setDismissDelay(3000);
      }

    }
  }

  private class DistanceLabel implements Comparable<DistanceLabel> {

    public double distance;
    public String label;

    /**
     * Creates a new instance of the DistanceLabel class.
     *
     * @param distance the distance
     * @param label the
     */
    public DistanceLabel(double distance, String label) {
      this.distance = distance;
      this.label = label;
    }

    @Override
    public int compareTo(DistanceLabel o) {
      return Double.compare(this.distance, o.distance);
    }
  }

  //</editor-fold>
}
