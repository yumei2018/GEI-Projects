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

import gov.ca.water.shapelite.map.layer.PolygonLayer;
import gov.ca.water.shapelite.events.MapEventMouse;
import java.awt.*;
import java.util.Arrays;
import javax.swing.SwingUtilities;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.data.marker.PolygonMarker;
import javax.swing.event.EventListenerList;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.map.MapPaintArgs;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.Mercator;

/**
 * This class handles selecting markers in response to the user dragging a
 * rectangle with the left mouse button, or clicking on a marker.
 *
 * @author ileung
 */
public class LineMapTool extends MapTool {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The position where selection dragging stopped in pixels in Map client
   * coordinates.
   */
  public Point end;
  /**
   * The geographic position (Mercator) where panning ended.
   */
  public Coord endCoordinate;
  /**
   * The event listeners that listen for the selection events.
   */
  private final EventListenerList eventListeners;
  /**
   * A boolean that is true if the mouse is currently dragging a new selection
   * window.
   */
  public boolean isDragging;
  /**
   * The pixel coordinate in Map client coordinates (0,0 is top left) where the
   * selection started.
   */
  public Point start;
  /**
   * The geographic (Mercator) coordinate where selection started.
   */
  public Coord startCoordinate;

  PolygonLayer mlp;

  /**
   * Creates a new instance of the MapToolSelect class.
   */
  public LineMapTool() {
    this.setName("line");
    this.setEnabled(false);
    eventListeners = new EventListenerList();

  }

  /**
   * Allows a new listener to be added to this object.
   *
   * @param listener
   */
  public void addMapToolLineListener(MapToolLineListener listener) {
    MapToolLineListener[] listeners = eventListeners.getListeners(MapToolLineListener.class);
    if (!Arrays.asList(listeners).contains(listener)) {
      eventListeners.add(MapToolLineListener.class, listener);
    }
  }

  /**
   * Removes an existing MapToolSelect listener
   *
   * @param listener
   */
  public void removeMapToolLineListener(MapToolLineListener listener) {
    eventListeners.remove(MapToolLineListener.class, listener);
  }

  /**
   * Fires the BeforeSelect event, which allows users to do something before
   * selection occurs, like track what the original selected condition was.
   *
   * @param args the MapEventMouse argument that contains information about the
   * Map control and the mouse condition.
   */
  public void fireBeforeSelect(MapEventMouse args) {
    MapToolLineListener[] listeners = eventListeners.getListeners(MapToolLineListener.class);
    removeLineAsMapLayer(args.getMap());
    for (MapToolLineListener listener : listeners) {
      listener.beforeSelect(args);
    }
  }

  /**
   * Fires the AfterSelect event, which allows users to do something after
   * selection occurs.
   *
   * @param args the MapEventMouse argument that contains information about the
   * Map control and the mouse condition.
   */
  public void fireAfterSelect(MapEventMouse args) {
    MapToolLineListener[] listeners = eventListeners.getListeners(MapToolLineListener.class);
    for (MapToolLineListener listener : listeners) {
      listener.afterSelect(args);
    }
  }

  /**
   * Occurs when the mouse is dragged. This will prompt the map to re-draw the
   * dashed selection rectangle, if we are currently dragging one.
   *
   * @param e the MapEventMouse arguments with information about the mouse and
   * the Map control.
   */
  @Override
  public void mouseDragged(MapEventMouse e) {
    if (isDragging) {
      end = e.getPoint();
      endCoordinate = e.getLocation();
      e.getMap().repaint();
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
  public void mousePressed(MapEventMouse e) {
    if (SwingUtilities.isLeftMouseButton(e)) {
      start = e.getPoint();
      startCoordinate = e.getLocation();
      isDragging = true;
      end = null;
      endCoordinate = null;
      this.fireBeforeSelect(e);
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
  public void mouseReleased(MapEventMouse e) {
    if (SwingUtilities.isLeftMouseButton(e)) {
      isDragging = false;
      end = e.getPoint();
      endCoordinate = e.getLocation();
      this.fireAfterSelect(e);

      this.addLineAsMapLayer(e.getMap());
    }
  }

  /**
   * This method will paint the selection box, a black rectangular, dashed box,
   * around the selected region.
   *
   * @param args MapPaintArgs that contain information about the graphics
   * surface and the Map control.
   */
  @Override
  public void paint(MapPaintArgs args) {
    if (isDragging && endCoordinate != null && startCoordinate != null) {
      args.getGraphics().setColor(Color.BLACK);
      Stroke original = args.getGraphics().getStroke();
      BasicStroke strk = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{2.0f, 1.0f}, 0);
      args.getGraphics().setStroke(strk);
      Point strt = args.getFrame().getProjector().getPoint(Mercator.toMerc(startCoordinate));
      Point ed = args.getFrame().getProjector().getPoint(Mercator.toMerc(endCoordinate));
      args.getGraphics().drawLine(strt.x, strt.y, ed.x, ed.y);
      args.getGraphics().setStroke(original);
    }
  }

  private void addLineAsMapLayer(MapPanel map) {
    Part part = new Part(Arrays.asList(startCoordinate, endCoordinate));
    Shape shape = new Shape(ShapeType.Polygon, Arrays.asList(part));
    PolygonMarker markers = new PolygonMarker(shape, "line");
    mlp = new PolygonLayer(Arrays.asList(markers));
    mlp.setName("line");
    map.getContent().getDynamicLayers().add(mlp);
  }

  private void removeLineAsMapLayer(MapPanel map) {
    map.getContent().getDynamicLayers().remove(mlp);
  }
}
