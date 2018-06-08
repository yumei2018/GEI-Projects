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
import gov.ca.water.shapelite.map.layer.PointLayer;
import gov.ca.water.shapelite.events.MapEventMouse;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.data.marker.PointMarker;
import gov.ca.water.shapelite.map.MapPaintArgs;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.map.TideInterpolationEvent;
import gov.ca.water.shapelite.map.TideInterpolationListener;
import gov.ca.water.shapelite.map.resources.MapImages;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.event.EventListenerList;

/**
 * This tool is for the map, but is very specifically used by the tide project,
 * and not part of the default map toolkit. This tool allows the user to use the
 * left mouse button to draw a river that connects two tide stations. Once
 * drawn, any point along the line can be clicked in order to allow
 * interpolation of the tides between the two stations. This is mostly about
 * estimating the up-stream delay of the arrival of the tides.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TideSketchMapTool extends PanMapTool {

  /**
   * A pencil cursor to use for sketching.
   */
  private static final Cursor PENCIL;

  /**
   * The position in the icon that is where a "click" actually registers.
   */
  private static final Point PENCIL_HOTPOT = new Point(16, 16);

  /**
   * A hand pointing cursor.
   */
  private static final Cursor POINTER;

  /**
   * The pointer hotspot.
   */
  private static final Point POINTER_HOTSPOT = new Point(15, 10);

  /**
   * The integer pixel tolerance for selecting points.
   */
  private static final int TOLERANCE = 10;

  static {
    Optional<BufferedImage> maybeImage = MapImages.get("PencilCursor32.png");
    Cursor pencil;
    if (maybeImage.isPresent()) {
      pencil = Toolkit.getDefaultToolkit().createCustomCursor(maybeImage.get(),
          PENCIL_HOTPOT, "pencil");
    } else {
      pencil = Cursor.getDefaultCursor();
    }
    PENCIL = pencil;

    Optional<BufferedImage> maybeIcon = MapImages.get("Pointing32.png");
    Cursor pointer;
    if (maybeIcon.isPresent()) {
      pointer = Toolkit.getDefaultToolkit().createCustomCursor(
          maybeIcon.get(), POINTER_HOTSPOT, "pointing");
    } else {
      pointer = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
    }
    POINTER = pointer;

  }

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * A geographic coordinate that represents where along the drawn interpolation
   * path interpolation will occur when the user next clicks the left mouse
   * button.
   */
  private Coord circle;

  /**
   * A boolean value that indicates whether or not the user has finished drawing
   * the line. This happens when the user releases the left mouse while
   * connected.
   */
  private boolean completed;

  /**
   * A boolean value that is true if the interpolation path has a valid entry on
   * both ends, so that it can interpolate between two possible values or
   * stations. The user may be connected while still drawing.
   */
  private boolean connected;

  /**
   * Coordinates are stored in lat/long. These are the coordinates for the drawn
   * river line, and not the interpolated tide station.
   */
  private List<Coord> coordinates;

  /**
   * A boolean that is true if the left mouse button has been pressed and is
   * dragging a line. A line can also be drawn by clicking.
   */
  private boolean drawing;

  /**
   * The PointMarker that is connected with the end of the line.
   */
  private PointMarker end;

  /**
   * A boolean that is true if the PointMarker connected at the end represents a
   * station that has harmonic tidal constituents for predicting tides.
   */
  private boolean endIsHarmonic;

  /**
   * A list of listeners for events related to this tool.
   */
  private final EventListenerList eventListeners;

  /**
   * The PointMarker that was used to begin drawing the interpolation path. A
   * path cannot be started anywhere but on a PointMarker.
   */
  private PointMarker start;

  /**
   * A boolean that is true if the initial marker connected to the path has
   * harmonic tidal constituents calculated for it.
   */
  private boolean startIsHarmonic;

  /**
   * Boolean, true if the map is showing a pencil cursor.
   */
  private boolean drawCursor;

  /**
   * Boolean, true if the map is showing a point cursor.
   */
  private boolean pointCursor;

  //</editor-fold>
  /**
   * Creates a new instance of the MapToolTideSketch class.
   */
  public TideSketchMapTool() {
    eventListeners = new EventListenerList();
    super.setName("sketch");
    super.setToolTip("Drawing interpolation paths.");
    super.setMapIcon(MapImages.get("Sketch32.png").orElse(null));
  }

  //<editor-fold defaultstate="collapsed" desc="Events">
  /**
   * Allows a new listener to be added to this object.
   *
   * @param listener The listener to add.
   */
  public final void addTideInterpolationListener(TideInterpolationListener listener) {
    TideInterpolationListener[] listeners = eventListeners.getListeners(
        TideInterpolationListener.class);
    if (!Arrays.asList(listeners).contains(listener)) {
      eventListeners.add(TideInterpolationListener.class, listener);
    }
  }

  /**
   * Removes an existing TideInterpolation listener.
   *
   * @param listener The listener to add.
   */
  public final void removeTideInterpolationListener(TideInterpolationListener listener) {
    eventListeners.remove(TideInterpolationListener.class, listener);
  }

  /**
   * Fires the event.
   *
   * @param args The interpolation arguments with the pathway.
   */
  public final void fireTideInterpolated(TideInterpolationEvent args) {
    TideInterpolationListener[] listeners = eventListeners.getListeners(
        TideInterpolationListener.class);
    for (TideInterpolationListener listener : listeners) {
      listener.tideInterpolated(args);
    }
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   *
   * @param e A MapEventMouse argument with information about the Map and mouse
   * condition.
   * @param isStart Boolean is Start.
   * @return The list of markers. This may be empty, but will never be null.
   */
  private List<PointMarker> getMarkers(MapEventMouse e, boolean isStart) {
    List<PointMarker> result = new ArrayList<>();
    boolean first = true;
    for (Layer<?> layer : e.getMap().getContent().getLayers()) {
      if (layer instanceof PointLayer) {
        PointLayer markerlayer = (PointLayer) layer;
        if (!"subordinate".equals(markerlayer.getName())
            && !"harmonic".equals(markerlayer.getName())) {
          continue;
        }
        Rectangle buffer = new Rectangle(e.getX() - TOLERANCE,
            e.getY() - TOLERANCE, TOLERANCE, TOLERANCE);
        List<PointMarker> markers
            = markerlayer.getIntersectingMarkers(buffer, e.getProj());
        result.addAll(markers);
        if (result.size() > 0 && first) {
          if (isStart) {
            startIsHarmonic = "harmonic".equals(markerlayer.getName());
          } else {
            endIsHarmonic = "harmonic".equals(markerlayer.getName());
          }
          first = false;
        }
      }
    }
    return result;
  }

  /**
   * Occurs when the mouse is dragged. This is important for drawing the lines,
   * and determining whether a line is connected or not. The line will change
   * color if it connects two different markers.
   *
   * @param e A MapEventMouse argument with information about the Map and mouse
   * condition.
   */
  @Override
  public final void mouseDragged(MapEventMouse e) {
    boolean handled = false;
    if (SwingUtilities.isLeftMouseButton(e)) {
      if (drawing) {
        coordinates.add(e.getLocation());
        List<PointMarker> markers = getMarkers(e, false);
        if (markers == null || markers.isEmpty()) {
          connected = false;
          end = null;
        } else {
          end = markers.get(0);
          connected = true;
        }
        e.getMap().repaint();
        handled = true;
      }
    }
    e.setHandled(handled);
    if (!handled) {
      // only pan if we aren't drawing.
      super.mouseDragged(e);
    }

  }

  /**
   * Occurs when the mouse is moved, but without dragging. This is responsible
   * for causing the circle to be drawn in a different location along the path
   * in response to the user moving the mouse.
   *
   * @param e A MapEventMouse argument with information about the Map and mouse
   * condition.
   */
  @Override
  public final void mouseMoved(MapEventMouse e) {
    if (!drawing && !completed) {
      List<PointMarker> markers = getMarkers(e, false);
      if ((markers != null && !markers.isEmpty())) {
        drawCursor = true;
        e.setCursor(PENCIL);
        e.setHandled(true);
      }
      if ((markers == null || markers.isEmpty())) {
        drawCursor = false;
      }
    }
    if (drawing) {
      e.getMap().repaint();
    }
    if (completed) {
      if (drawCursor) {
        drawCursor = false;
        pointCursor = true;
      }
      e.setCursor(POINTER);
      e.setHandled(true);
      Coord latlong = e.getLocation();
      Part prt = new Part(this.coordinates);
      Optional<Coord> closest = prt.closestPointTo(latlong);
      if (closest.isPresent()) {
        circle = closest.get();
        e.getMap().repaint();
      }
    }
    super.mouseMoved(e);
  }

  /**
   * Occurs when the mouse is pressed.
   *
   * @param e A MapEventMouse argument with information about the Map and mouse
   * condition.
   */
  @Override
  public final void mousePressed(MapEventMouse e) {

    boolean handled = false;
    if (SwingUtilities.isLeftMouseButton(e)) {
      // To DO: make context menu to select marker.
      if (!drawing && !completed) {
        List<PointMarker> markers = getMarkers(e, true);
        if (markers.size() > 0) {
          start = markers.get(0);
          coordinates = new ArrayList<>();
          drawing = true;
          handled = true;
        }
      }
      if (drawing && !completed) {
        coordinates.add(e.getLocation());
        List<PointMarker> markers = getMarkers(e, false);
        if (markers == null || markers.isEmpty()) {
          connected = false;
          end = null;
        } else {
          end = markers.get(0);
          connected = true;
        }
        e.getMap().repaint();
        handled = true;
      }
      if (completed) {

        fireTideInterpolated(new TideInterpolationEvent(this, coordinates,
            start, end, startIsHarmonic, endIsHarmonic, e));
        completed = false;
        if (pointCursor) {
          e.getMap().setCursor(Cursor.getDefaultCursor());
          pointCursor = false;
        }
        e.getMap().repaint();
        handled = true;
      }
    }
    e.setHandled(handled);
    if (!handled) {
      super.mousePressed(e);
    }

  }

  /**
   * Occurs when the mouse is released. After drawing a path and using it to
   * connect two stations, this will complete the interpolation path way and
   * allow the circle to slide along the path for selecting the interpolation
   * position.
   *
   * @param e A MapEventMouse argument with information about the Map and mouse
   * condition.
   */
  @Override
  public final void mouseReleased(MapEventMouse e) {
    boolean handled = false;
    if (drawing && connected) {
      drawing = false;
      connected = false;
      completed = true;
      e.getMap().repaint();
      handled = true;
    }
    e.setHandled(handled);
    if (!handled) {
      super.mouseReleased(e);
    }

  }

  /**
   * Paints the interpolation path that is being drawn onto the map. This will
   * also draw the circle indicator where the point is actively being set.
   *
   * @param args MapPaintArgs with information about the drawing canvas and the
   * Map control being rendered.
   */
  @Override
  public final void paintContent(MapPaintArgs args) {
    super.paintContent(args);
    if (super.isPaintingPanPreview()) {
      return;
    }
    if (super.isPaintingZoomPreview()) {
      return;
    }
    if (!isEnabled()) {
      return;
    }

    // handle pan stuff first or else it will be drawn on top of the lines.
    Graphics2D g = args.getGraphics();
    if ((drawing || completed) && coordinates != null && coordinates.size() > 1) {

      if (completed) {
        g.setColor(Color.BLUE);
      } else if (!connected) {
        g.setColor(Color.red);
      } else {
        g.setColor(Color.GREEN);
      }

      GeneralPath gp = new GeneralPath();
      boolean first = true;
      for (Coord c : coordinates) {
        Coord merc = Mercator.toMerc(c);
        Point p = args.getFrame().getProjector().getPoint(merc);
        if (first) {
          gp.moveTo(p.x, p.y);
          first = false;
        } else {
          gp.lineTo(p.x, p.y);
        }
      }
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
          RenderingHints.VALUE_ANTIALIAS_ON);
      g.setStroke(new BasicStroke(2));
      g.draw(gp);
      g.setStroke(new BasicStroke(1));
      if (completed && circle != null) {
        Coord merc = Mercator.toMerc(circle);
        Point p = args.getFrame().getProjector().getPoint(merc);
        g.setColor(Color.BLUE);
        g.fillOval(p.x - 5, p.y - 5, 10, 10);
        g.setColor(new Color(0, 0, 80));
        g.drawOval(p.x - 5, p.y - 5, 10, 10);
      }
    }

  }

  //</editor-fold>
}
