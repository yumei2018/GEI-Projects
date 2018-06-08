/*
 * The MIT License
 *
 * Copyright 2013 hdunsford.
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

import gov.ca.water.shapelite.data.TilesEvent;
import gov.ca.water.shapelite.map.MapPanel;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapEventTiles extends TilesEvent {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The MapPanel control that this event was involved with the tile event.
   */
  private MapPanel map;

  //</editor-fold>
  /**
   * Creates a new instance of the MapEventTile class using an original
   * TileEvent.
   * @param map The MapPanel for this event.
   * @param evt The TilesEvent event object from the original event.
   */
  public MapEventTiles(MapPanel map, TilesEvent evt) {
    super(evt.getSource(), evt.getTiles());
    this.map = map;
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the MapPanel control that this event was involved with the tile event.
   *
   * @return the map
   */
  public final MapPanel getMap() {
    return map;
  }

  /**
   * Sets the MapPanel control that this event was involved with the tile event.
   *
   * @param map the map to set
   */
  public final void setMap(MapPanel map) {
    this.map = map;
  }

  //</editor-fold>
}
