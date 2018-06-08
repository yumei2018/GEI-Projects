/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.events.MapEventMouse;
import gov.ca.water.shapelite.events.PolygonFinishedEvent;
import gov.ca.water.shapelite.map.MapPaintArgs;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.map.resources.MapImages;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DrawPolygonMapTool extends MapTool {

  /**
   * The logger utility for this class.
   */
  private static final Logger LOGGER = Logger.getLogger(
      DrawPolygonMapTool.class.getName());

  /**
   * Red.
   */
  private static final Color UNFINISHED_LINE = new Color(255, 0, 0);

  /**
   * Translucent Pink.
   */
  private static final Color UNFINISHED_FILL = new Color(255, 128, 128, 128);

  /**
   * Cyan.
   */
  private static final Color FINISHED_LINE = new Color(0, 255, 255);

  /**
   * Translucent Cyan.
   */
  private static final Color FINSIHED_FILL = new Color(0, 255, 255, 128);

  /**
   * The size in pixels of the points.
   */
  private static final int POINT_SIZE = 10;

  /**
   * The coordinates of the drawn polygon.
   */
  private final List<Coord> coordinates;

  /**
   * The location of the mouse.
   */
  private Point mousePosition;

  /**
   * Finished.
   */
  private boolean finished;

  /**
   * Creates a new instance of this tool.
   */
  public DrawPolygonMapTool() {
    coordinates = new ArrayList<>();
    super.setName("polygon");
    super.setToolTip("Draw polygons.");
    super.setMapIcon(MapImages.get("Polygon32.png").orElse(null));
  }

  //<editor-fold defaultstate="collapsed" desc="PolygonFinishedEvent">
  /**
   * The list of listeners.
   */
  private final List<PolygonFinishedEvent.Listener> polygonFinishedListeners
      = new ArrayList<>();

  /**
   * Adds the specified listener to the list of listeners to be notified during
   * an event. If the item is already in the list, it will not be added a second
   * time.
   *
   * @param listener The PolygonFinishedEvent.Listener to connect.
   */
  public final void addPolygonFinishedListener(
      PolygonFinishedEvent.Listener listener) {
    if (!polygonFinishedListeners.contains(listener)) {
      polygonFinishedListeners.add(listener);
    }
  }

  /**
   * Removes the specified listener from the list if it is in the list.
   *
   * @param listener The PolygonFinishedEvent.Listener to disconnect.
   */
  public final void removePolygonFinishedListener(
      PolygonFinishedEvent.Listener listener) {
    polygonFinishedListeners.remove(listener);
  }

  /**
   * Fires the PolygonFinished event and notifies each of the listeners.
   *
   * @param e A {EventType}Event with the source object and any properties
   * associated with this event.
   */
  public final void firePolygonFinished(PolygonFinishedEvent e) {
    for (PolygonFinishedEvent.Listener listener : polygonFinishedListeners) {
      try {
        listener.polygonFinished(e);
      } catch (Exception ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      }

    }
  }

  //</editor-fold>
  /**
   * draws the polygon being actively drawn on the map.
   *
   * @param args The paint args.
   */
  @Override
  public final void paint(MapPaintArgs args) {
    if (coordinates != null) {

      int[] x = new int[coordinates.size()];
      int[] y = new int[coordinates.size()];
      for (int i = 0; i < coordinates.size(); i++) {
        Coord merc = Mercator.toMerc(coordinates.get(i));
        Point pt = args.getFrame().getProjector().getPoint(merc);
        x[i] = pt.x;
        y[i] = pt.y;
      }
      Color fill = UNFINISHED_FILL;
      Color line = UNFINISHED_LINE;
      if (coordinates.size() > 2 && mousePosition != null
          && Math.abs(mousePosition.x - x[0]) < POINT_SIZE
          && Math.abs(mousePosition.y - y[0]) < POINT_SIZE) {
        fill = FINSIHED_FILL;
        line = FINISHED_LINE;
      }

      if (x.length > 2) {
        args.getGraphics().setColor(fill);
        args.getGraphics().fillPolygon(x, y, x.length);
        args.getGraphics().setColor(line);
        args.getGraphics().drawPolygon(x, y, x.length);
      } else if (x.length > 1) {
        args.getGraphics().setColor(line);
        args.getGraphics().drawPolyline(x, y, x.length);
      }
      for (int i = 0; i < x.length; i++) {
        args.getGraphics().drawRect(x[i] - POINT_SIZE / 2, y[i] - POINT_SIZE / 2,
            POINT_SIZE, POINT_SIZE);
      }

    }

  }

  @Override
  public final void mousePressed(MapEventMouse e) {
    if (finished) {
      coordinates.clear();
      finished = false;
      e.getMap().repaint();
      return;
    }
    mousePosition = e.getPoint();
    Coord c = e.getLocation();

    if (coordinates.size() > 2) {
      Coord merc = Mercator.toMerc(coordinates.get(0));
      Point start = e.getProj().getPoint(merc);
      if (mousePosition != null && Math.abs(mousePosition.x - start.x) < POINT_SIZE
          && Math.abs(mousePosition.y - start.y) < POINT_SIZE) {
        Shape shape = new Shape(ShapeType.Polygon);
        Part part = new Part(coordinates);
        part.setClosed(true);
        shape.getParts().add(part);
        finished = true;

        firePolygonFinished(new PolygonFinishedEvent(e.getMap(), shape));
        e.getMap().repaint();
        return;
      }
    }

    this.coordinates.add(c);
    e.getMap().repaint();
  }

  @Override
  public final void mouseMoved(MapEventMouse e) {
    mousePosition = e.getPoint();
    e.getMap().repaint();
  }

  @Override
  protected void onDisabled() {
    finished = false;
    coordinates.clear();
  }



}
