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

import gov.ca.water.shapelite.events.MapEventDisplay;
import gov.ca.water.shapelite.events.MapEventTiles;
import java.awt.Rectangle;

/**
 * This actually handles the task of forcing the map to redraw content as new
 * tiles become available.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TileUpdatingMapTool extends MapTool {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * A boolean value that registers tile events as requiring the Map to be
   * updated. The map does not automatically do this, so this functionality can
   * be overridden, or replaced later.
   */
  boolean refreshNeeded;
  //</editor-fold>

  /**
   * Creates a new instance of the MapToolTileUpdate class.
   */
  public TileUpdatingMapTool() {
    super.setName("tileupdate");
  }

  /**
   * Occurs when tiles have been downloaded.
   *
   * @param e A MapEventTiles argument with information about which tiles were
   * downloaded and the Map control.
   */
  @Override
  public void tilesDownloaded(MapEventTiles e) {
    if (e.getMap() != null) {
      e.getMap().getContent().invalidate(new Rectangle(0, 0,
          e.getMap().getWidth(), e.getMap().getHeight()));
      refreshNeeded = true;
    }
  }

  /**
   * Occurs when the content of the map is updated. If a refresh is needed at
   * that time, then this instructs
   *
   * @param e A MapEventDisplay argument that has information about the Map and
   * the graphics drawing surface.
   */
  @Override
  public void renderComplete(MapEventDisplay e) {
    if (refreshNeeded) {
      e.getMap().repaint();
      refreshNeeded = false;
    }
  }

}
