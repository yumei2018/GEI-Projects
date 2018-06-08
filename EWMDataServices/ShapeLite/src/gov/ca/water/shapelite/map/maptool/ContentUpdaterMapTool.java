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
import gov.ca.water.shapelite.events.MapEventLayers;

/**
 * This tool ensures that the map redraws the cached image to the screen
 * when the layers have been modified.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ContentUpdaterMapTool extends MapTool{

  /**
   * Occurs when the content has changed.  This can occur after moving, resizing,
   * or anything that modifies the content that is stored on the cached image for
   * for the map to display.
   * @param e A DisplayEvent containing information about the source object.
   */
  @Override
  public void contentChanged(MapEventDisplay e) {
    e.getMap().repaint();
  }

  /**
   * Occurs when the list of layers has been updated or modified.
   * @param e A MapEventLayers that shows the current map's layers.
   */
  @Override
  public void layersChanged(MapEventLayers e) {
    if(e.getMap() != null){
      e.getMap().getContent().paintImmediately();
      e.getMap().repaint();
    }
  }
  
 
}
