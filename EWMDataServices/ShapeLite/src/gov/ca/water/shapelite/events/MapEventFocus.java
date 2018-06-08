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
import java.awt.Component;
import java.awt.event.FocusEvent;

/**
 * This class extends the FocusEvent to include information about the MapPanel
 * control.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapEventFocus extends FocusEvent implements MapEvent {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The MapPanel control that was the source of the focus event.
   */
  private MapPanel map;

  //</editor-fold>
  /**
   * Creates a new instance of a MapEventFocus.
   *
   * @param map The MapPanel for the event.
   * @param id The integer Id.
   * @param temporary Boolean if the focus is temporary.
   * @param opposite The other component that was losing/gaining focus.
   */
  public MapEventFocus(MapPanel map, int id, boolean temporary,
          Component opposite) {
    super(map, id, temporary, opposite);
    this.map = map;
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the MapPanel control that was the source of the focus event.
   *
   * @return the MapPanel map.
   */
  @Override
  public final MapPanel getMap() {
    return map;
  }

  /**
   * Sets the MapPanel control that was the source of the focus event.
   *
   * @param map the MapPanel map to set.
   */
  protected final void setMap(MapPanel map) {
    this.map = map;
  }

  //</editor-fold>
}
