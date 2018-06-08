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
package gov.ca.water.shapelite.events;

import gov.ca.water.shapelite.data.Projector;
import java.awt.Component;
import java.awt.event.MouseEvent;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.Mercator;
import java.awt.Cursor;
import java.awt.Point;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapEventMouse extends MouseEvent implements MapEvent {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The boolean that indicates whether or not the event has been handled, and
   * should therefore not propagate to tools with higher index values.
   */
  private boolean handled;

  /**
   * The Cursor to be drawn on the map. Actually setting the cursor on the panel
   * will draw that cursor. So to prevent flicker, we will only draw the final
   * cursor state.
   */
  private Cursor cursor;

  /**
   * Gets the geographic location of the mouse event in Mercator coordinates. To
   * get the lat, long, use Mercator.fromMerc().
   */
  private final Coord location;

  /**
   * The MapPanel control that was the source of the Event.
   */
  private final MapPanel map;

  /**
   * A class that allows for quickly translating points and rectangles from
   * client pixel coordinates to geographic coordinates and back.
   */
  private Projector proj;

  //</editor-fold>
  /**
   * Creates a new instance of the map mouse event.
   *
   * @param map The mapPanel for this event.
   * @param parent The MouseEvent parent that was the original event object.
   */
  public MapEventMouse(MapPanel map, MouseEvent parent) {
    super((Component) parent.getSource(), parent.getID(), parent.getWhen(),
        parent.getModifiers(), parent.getX(), parent.getY(),
        parent.getXOnScreen(), parent.getYOnScreen(),
        parent.getClickCount(), parent.isPopupTrigger(),
        parent.getButton());
    this.map = map;
    proj = new Projector(map.getContent());
    location = Mercator.fromMerc(proj.getCoordinate(parent.getPoint()));
    cursor = Cursor.getDefaultCursor();
  }

  /**
   * Creates a new instance of the map mouse event.
   *
   * @param parent The MapMouseEvent parent that was the original event object.
   * @param offset the offset of the child component from the parent frame.
   */
  public MapEventMouse(MapEventMouse parent, Point offset) {
    super((Component) parent.getSource(), parent.getID(), parent.getWhen(),
        parent.getModifiers(), parent.getX() - offset.x, parent.getY() - offset.y,
        parent.getXOnScreen(), parent.getYOnScreen(),
        parent.getClickCount(), parent.isPopupTrigger(),
        parent.getButton());
    this.map = parent.getMap();
    proj = new Projector(map.getContent());
    location = Mercator.fromMerc(proj.getCoordinate(parent.getPoint()));
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the boolean that indicates whether or not the event has been handled,
   * and should therefore not propagate to tools with higher index values.
   *
   * @return the handled boolean.
   */
  public final boolean isHandled() {
    return handled;
  }

  /**
   * Sets the boolean that indicates whether or not the event has been handled,
   * and should therefore not propagate to tools with higher index values.
   *
   * @param handled the handled boolean to set.
   */
  public final void setHandled(boolean handled) {
    this.handled = handled;
  }

  /**
   * Gets the geographic location of the mouse event in WGS84 coordinates.
   *
   * @return the location
   */
  public final Coord getLocation() {
    return location;
  }

  /**
   * Gets the MapPanel control that was the source of the Event.
   *
   * @return the map.
   */
  @Override
  public final MapPanel getMap() {
    return map;
  }

  /**
   * Gets the class that allows for quickly translating points and rectangles
   * from client pixel coordinates to geographic coordinates and back.
   *
   * @return the Projector object for this map content.
   */
  public final Projector getProj() {
    return proj;
  }

  /**
   * Sets the class allows for quickly translating points and rectangles from
   * client pixel coordinates to geographic coordinates and back. This is
   * accessible to subclasses of the event arguments, but not to external
   * entities.
   *
   * @param proj The Projector to set.
   */
  protected final void setProj(Projector proj) {
    this.proj = proj;
  }

  /**
   * Gets the Cursor to be drawn on the map. Actually setting the cursor on the
   * panel will draw that cursor. So to prevent flicker, we will only draw the
   * final cursor state.
   *
   * @return the cursor
   */
  public final Cursor getCursor() {
    return cursor;
  }

  /**
   * Sets the Cursor to be drawn on the map. Actually setting the cursor on the
   * panel will draw that cursor. So to prevent flicker, we will only draw the
   * final cursor state.
   *
   * @param cursor the cursor to set
   */
  public final void setCursor(Cursor cursor) {
    this.cursor = cursor;
  }

  //</editor-fold>
}
