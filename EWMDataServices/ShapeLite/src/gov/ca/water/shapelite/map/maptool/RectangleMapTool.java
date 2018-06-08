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

import gov.ca.water.shapelite.events.MapToolRectangleEvent;
import gov.ca.water.shapelite.map.layer.PolygonLayer;
import gov.ca.water.shapelite.events.MapEventMouse;
import gov.ca.water.shapelite.events.MapEventKey;
import javax.swing.SwingUtilities;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.data.marker.PolygonMarker;
import gov.ca.water.shapelite.map.MapPaintArgs;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.symbology.Pen;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class handles selecting markers in response to the user dragging a
 * rectangle with the left mouse button, or clicking on a marker.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class RectangleMapTool extends MapTool {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The position where selection dragging stopped in pixels in Map client
   * coordinates.
   */
  public Point end;
  /**
   * The geographic position (Mercator) where panning ended.
   */
  private Coord endCoordinate;
  /**
   * The event listeners that listen for the selection events.
   */
  private final List<MapToolRectangleListener> eventListeners;
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

  /**
   * Blue rectangle
   */
  public Rectangle redRect;
  private PolygonLayer mlp;

  //</editor-fold>
  /**
   * Creates a new instance of the MapToolSelect class.
   */
  public RectangleMapTool() {
    this.setName("rectangle");
    this.setEnabled(false);
    eventListeners = new ArrayList<>();
  }

  //<editor-fold defaultstate="collapsed" desc="Events">
  /**
   * Allows a new listener to be added to this object.
   *
   * @param listener
   */
  public void addMapToolRectangleListener(MapToolRectangleListener listener) {

    if (!eventListeners.contains(listener)) {
      eventListeners.add(listener);
    }
  }

  /**
   * Removes an existing MapToolSelect listener
   *
   * @param listener
   */
  public void removeMapToolSelectListener(MapToolRectangleListener listener) {
    eventListeners.remove(listener);
  }

  /**
   * Fires the BeforeSelect event, which allows users to do something before
   * selection occurs, like track what the original selected condition was.
   *
   * @param e the MapToolRectangleEvent argument that contains information about
 the Envelope and the layer hosting the rectangle.
   */
  public void fireRectangleDrawn(MapToolRectangleEvent e) {
    for (MapToolRectangleListener listener : eventListeners) {
      listener.rectangleDrawn(e);
    }
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
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
      removeRectangleAsMapLayer(e.getMap());
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
    if (isDragging) {
      if (SwingUtilities.isLeftMouseButton(e)) {
        isDragging = false;
        end = e.getPoint();
        endCoordinate = e.getLocation();
        Envelope box = new DefaultEnvelope();
        if (startCoordinate != null) {
          box.expandToInclude(startCoordinate);
          box.expandToInclude(endCoordinate);
        }
        box = Mercator.toMerc(box);
        int x = Math.min(start.x, end.x);
        int y = Math.min(start.y, end.y);
        int w = Math.max(start.x, end.x) - x;
        int h = Math.max(start.y, end.y) - y;
        redRect = new Rectangle(x, y, w, h);
        e.getMap().paintImmediately(e.getMap().getContent().getView());
        this.fireRectangleDrawn(new MapToolRectangleEvent(this, box, redRect));
        addRectangleAsMapLayer(e.getMap());
        e.setHandled(true);
      }
    }

  }

  @Override
  public void keyPressed(MapEventKey e) {

    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      if (redRect != null) {
        Envelope box = e.getMap().getContent().getProjector().getEnvelope(redRect);
        this.fireRectangleDrawn(new MapToolRectangleEvent(this, box, redRect));
        e.setHandled(true);
      } else {
        Envelope box = e.getMap().getContent().getDisplayEnvelope();
        redRect = e.getMap().getContent().getView();
        this.fireRectangleDrawn(new MapToolRectangleEvent(this, box, redRect));
      }

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
      int xmin = Math.min(end.x, start.x);
      int xmax = Math.max(end.x, start.x);
      int ymin = Math.min(end.y, start.y);
      int ymax = Math.max(end.y, start.y);
      Rectangle rect = new Rectangle(xmin, ymin, xmax - xmin, ymax - ymin);
      args.getGraphics().setColor(Color.RED);
      args.getGraphics().drawRect(rect.x, rect.y, rect.width, rect.height);
    }
    /*
     if(!isDragging && redRect != null){
     args.getGraphics().setColor(Color.red);
     args.getGraphics().drawRect(redRect.x, redRect.y, redRect.width, redRect.height);
     }
     */

  }

  private void addRectangleAsMapLayer(MapPanel map) {
    Part part = new Part(Arrays.asList(
        startCoordinate,
        new CoordXY(startCoordinate.getX(), endCoordinate.getY()),
        endCoordinate,
        new CoordXY(endCoordinate.getX(), startCoordinate.getY())
    ));
    Shape shape = new Shape(ShapeType.Polygon, Arrays.asList(part));
    PolygonMarker markers = new PolygonMarker(shape, "rectangle");
    mlp = new PolygonLayer(Arrays.asList(markers));
    mlp.setName("rectangle");
    mlp.getDefaultSymbolizer().setFillColor(new Color(0, 0, 0, 0));
    mlp.getDefaultSymbolizer().setBorderColor(Color.RED);
    map.getContent().getDynamicLayers().add(mlp);
    map.repaint();
    startCoordinate = null;
    endCoordinate = null;

  }

  private void removeRectangleAsMapLayer(MapPanel map) {
    map.getContent().getDynamicLayers().remove(mlp);
  }
}
