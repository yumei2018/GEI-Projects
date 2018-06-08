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
package gov.ca.water.shapelite.map;

import gov.ca.water.shapelite.data.DataFrame;
import java.awt.Graphics2D;

/**
 * The MapPaintArgs class extends the normal PaintArgs that has information about the
 * drawing surface, to also have information about the map control.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapPaintArgs extends PaintArgs{
  
  //<editor-fold defaultstate="collapsed" desc="Fields">
  
  /**
   *  The MapPanel control that is being rendered by the paint event.
   */
  private MapPanel map;
  
  //</editor-fold>
  
  /**
   * Creates a new instance of the MapPaintArgs class with the necessary
   * information about the graphics surface and the data frame.
   * @param source the sender of the event
   * @param map  The MapPanel to draw - also the PaintArg's Source
   * @param g The 2D Drawing surface to draw to.
   * @param f The DataFrame defining the content for the map.
   */
  public MapPaintArgs(MapPanel map, Graphics2D g, DataFrame f){
    super(map, g, f);
    this.map = map;
  }
  
  //<editor-fold defaultstate="collapsed" desc="Properties">
  

  /**
   * Gets the MapPanel control that is being rendered by the paint event.
   * @return the map
   */
  public MapPanel getMap() {
    return map;
  }

  /**
   * Sets the MapPanel control that is being rendered by the paint event.  This
   * is protected so that the variable can be set by overriding subclass controls,
   * but not by classes external to the MapPaintArgs class.
   * @param map the map to set
   */
  protected void setMap(MapPanel map) {
    this.map = map;
  }
  
  //</editor-fold>
}
