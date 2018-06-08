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

import gov.ca.water.shapelite.map.layer.Layer;
import javax.swing.SwingUtilities;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.data.marker.PointMarker;
import gov.ca.water.shapelite.events.MapEventMouse;
import gov.ca.water.shapelite.events.PointMarkerEvent;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.layer.PointLayer;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

/**
 * This class handles firing an event when point markers are clicked. It does
 * not necessarilly select the marker.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PointClickMapTool extends MapTool {

  /**
   * The integer pixel tolerance for showing the finger cursor, rather
   * than the pan icon, or other default icon.
   */
  private static final int TOLERANCE = 10;

  /**
   * The maximum difference between the start and end point before considering
   * the points no longer in the same place to trigger a click event.
   */
  private static final int PIXEL_DISTANCE = 5;

  /**
   * A boolean that is true if the mouse is currently dragging a new selection
   * window.
   */
  private Point start;

  //</editor-fold>
  /**
   * Creates a new instance of the MapToolSelect class.
   */
  public PointClickMapTool() {
    this.setName("pointClick");
  }

  //<editor-fold defaultstate="collapsed" desc="Events">

  /**
   * Handles the mouse pointer to only show the pointing hand if we are over
   * a clickable point.
   * @param e The MapEventMouse.
   */
  @Override
  public final void mouseMoved(MapEventMouse e) {
    if (intersectsPoint(e.getMap(), e.getPoint())) {
      e.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      e.setHandled(true);
    }

  }

  /**
   * Occurs when the mouse is pressed. If the left button is pressed this
   * enables selection to begin.
   *
   * @param e A MapEventMouse argument with information about the Map and the
   * mouse arguments.
   */
  @Override
  public final void mousePressed(MapEventMouse e) {
    if (SwingUtilities.isLeftMouseButton(e)) {
      start = e.getPoint();
    }
  }

  /**
   * Occurs when the mouse is released. This is responsible for actually
   * performing the selection if we were selecting a box, and the left button
   * was released.
   *
   * @param e A MapEventMouse argument with information about the Map and the
   * mouse condition.
   */
  @Override
  public final void mouseReleased(MapEventMouse e) {
    if (SwingUtilities.isLeftMouseButton(e)) {
      Point end = e.getPoint();
      Coord endCoordinate = e.getLocation();
      if (start == null) {
        return;
      }
      if (Math.abs(end.x - start.x) > PIXEL_DISTANCE
          || Math.abs(end.y - start.y) > PIXEL_DISTANCE) {
        return;
      }

      Rectangle buffer = new Rectangle(e.getPoint().x - TOLERANCE,
          e.getPoint().y - TOLERANCE, TOLERANCE * 2, TOLERANCE * 2);

      boolean changed = false;
      for (Layer<?> mapLayer : e.getMap().getContent().getLayers()) {
        if (!(mapLayer instanceof PointLayer)) {
          continue;
        }
        PointLayer pointLayer = (PointLayer) mapLayer;
        List<PointMarker> potentiallyClicked = pointLayer.getIntersectingMarkers(buffer, e.getProj());
        pointLayer.fireMarkersClicked(
            new PointMarkerEvent(pointLayer, potentiallyClicked));
      }
      e.setHandled(changed);
    }
  }

  public boolean intersectsPoint(MapPanel map, Point location) {
    Rectangle buffer = new Rectangle(location.x - TOLERANCE, location.y - TOLERANCE, TOLERANCE * 2, TOLERANCE * 2);
    for (Layer<?> mapLayer : map.getContent().getLayers()) {
      if (!(mapLayer instanceof PointLayer)) {
        continue;
      }
      PointLayer pointLayer = (PointLayer) mapLayer;
      List<PointMarker> markers = pointLayer.getIntersectingMarkers(
          buffer, map.getContent().getProjector());
      if (!markers.isEmpty()) {
        return true;
      }
    }
    return false;
  }

}
