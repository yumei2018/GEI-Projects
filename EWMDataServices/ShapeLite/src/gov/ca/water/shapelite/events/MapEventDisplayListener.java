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

import java.util.EventListener;

/**
 * This class provides a mechanism to listen to layer and content events while
 * still having information about the MapPanel.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface MapEventDisplayListener extends EventListener {

  /**
   * Occurs any time the information in the viewable region of the map is
   * updated.
   *
   * @param e MapEventDisplay argument with information about the Map control.
   */
  void contentChanged(MapEventDisplay e);

  /**
   * Occurs after the content image has been re-rendered.
   *
   * @param e MapEventDisplay argument with information about the Map control.
   */
  void renderComplete(MapEventDisplay e);

  /**
   * Occurs when the map's layers are adjusted.
   *
   * @param e A MapEventLayers event argument with information about the map and
   * layers.
   */
  void layersChanged(MapEventLayers e);

  /**
   * Occurs when a tile has been downloaded.
   *
   * @param e The MapEventTile argument which has information about the tile and
   * the map.
   */
  void tilesDownloaded(MapEventTiles e);



  /**
   *
   */
  public static class Default implements MapEventDisplayListener{

    @Override
    public void contentChanged(MapEventDisplay e) {
    }

    @Override
    public void renderComplete(MapEventDisplay e) {
    }

    @Override
    public void layersChanged(MapEventLayers e) {
    }

    @Override
    public void tilesDownloaded(MapEventTiles e) {
    }

  }
}
