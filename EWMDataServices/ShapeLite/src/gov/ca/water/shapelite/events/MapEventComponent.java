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
import gov.ca.water.shapelite.map.MapPanel;
import java.awt.event.ComponentEvent;

/**
 * Extends the ComponentEvent for things like the component being shown or
 * resized, but adds information about the map and creates a projector for the
 * map content.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapEventComponent extends ComponentEvent implements MapEvent {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The boolean that indicates whether or not the event has been handled by a
   * map tool so that tools with higher index values should ignore the event.
   */
  private boolean handled;

  /**
   * The MapPanel that is the map control that the original source event belongs
   * to.
   */
  private MapPanel map;

  /**
   * A Projector object that has been configured to work with the Mercator
   * projected object in the background to make coordinate transformation
   * easier.
   */
  private Projector proj;

  //</editor-fold>

  /**
   * MapPanel panel.
   * @param map The map.
   * @param parent The parent ComponentEvent.
   */
  public MapEventComponent(MapPanel map, ComponentEvent parent) {
    super(parent.getComponent(), parent.getID());
    this.map = map;
    proj = new Projector(map.getContent());
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the boolean that indicates whether or not the event has been handled
   * by a map tool so that tools with higher index values should ignore the
   * event.
   *
   * @return the handled boolean value.
   */
  public final boolean isHandled() {
    return handled;
  }

  /**
   * Sets the boolean that indicates whether or not the event has been handled
   * by a map tool so that tools with higher index values should ignore the
   * event.
   *
   * @param handled the handled to set.
   */
  public final void setHandled(boolean handled) {
    this.handled = handled;
  }

  /**
   * Gets the MapPanel that is the map control that the original source event
   * belongs to.
   *
   * @return the map
   */
  @Override
  public final MapPanel getMap() {
    return map;
  }

  /**
   * Sets the MapPanel control that was the source of the Event. This is
   * protected so that it can be edited by subclasses, but not by external
   * entities.
   *
   * @param map the map to set.
   */
  protected final void setMap(MapPanel map) {
    this.map = map;
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

  //</editor-fold>
}
