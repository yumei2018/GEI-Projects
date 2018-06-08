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

import gov.ca.water.shapelite.map.MapPanel;
import java.util.EventObject;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapEventTrack extends EventObject implements MapEvent {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The MapPanel control that was the source of the Event.
   */
  private MapPanel map;

  /**
   * The integer physical position on the track, where 0 is the bottom and 18 is
   * the top. This also coincides directly to the zoom scale on the map, which
   * corresponds to the standard web tile mapping Mercator tile sets.
   */
  private int position;

  //</editor-fold>

  /**
   * Creates a new instance of the MapEventTrack event object.
   * @param map The MapPanel for this event.
   * @param source The object source of the event.
   * @param position The integer position of the track between 0 and 18.
   */
  public MapEventTrack(MapPanel map, Object source, int position) {
    super(source);
    this.map = map;
    this.position = position;
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the MapPanel control that was the source of the Event.
   *
   * @return
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
   * Gets the integer physical position on the track, where 0 is the bottom and
   * 18 is the top. This also coincides directly to the zoom scale on the map,
   * which corresponds to the standard web tile mapping Mercator tile sets.
   *
   * @return the position
   */
  public final int getPosition() {
    return position;
  }

  /**
   * Sets the integer physical position on the track, where 0 is the bottom and
   * 18 is the top. This also coincides directly to the zoom scale on the map,
   * which corresponds to the standard web tile mapping Mercator tile sets.
   *
   * @param position the position to set
   */
  protected final void setPosition(int position) {
    this.position = position;
  }

  //</editor-fold>
}
