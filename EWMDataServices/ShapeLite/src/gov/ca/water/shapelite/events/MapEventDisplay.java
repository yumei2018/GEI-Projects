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
 * The Display event occurs when something is displayed, or rendered.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapEventDisplay extends EventObject implements MapEvent {

  /**
   * The MapPanel that owns this event.
   */
  private MapPanel map;

  /**
   * Creates a new instance of the DisplayEvent.
   *
   * @param map The MapPanel for the event.
   * @param source The Object source of the event.
   */
  public MapEventDisplay(MapPanel map, Object source) {
    super(source);
    this.map = map;
  }

  /**
   * Gets the MapPanel for this event.
   *
   * @return the map.
   */
  @Override
  public final MapPanel getMap() {
    return map;
  }

  /**
   * Sets the MapPanel for this event.
   *
   * @param map the map to set.
   */
  protected final void setMap(MapPanel map) {
    this.map = map;
  }
}
