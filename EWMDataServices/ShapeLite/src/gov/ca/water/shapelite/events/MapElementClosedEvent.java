/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
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

import gov.ca.water.shapelite.map.element.MapElement;
import java.util.EventListener;
import java.util.EventObject;

/**
 * This handles a specific kind of event, providing data access properties.
 *
 * @author hdunsford
 */
public class MapElementClosedEvent extends EventObject {

  /**
   * The MapElement element.
   */
  private MapElement element;

  /**
   * Creates a new instance of <code>RegionChangedEvent</code>.
   *
   * @param source The Object source that initiated the event.
   * @param element*/
  public MapElementClosedEvent(Object source, MapElement element) {
    super(source);
    this.element = element;
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the MapElement element.
   *
   * @return the element.
   */
  public final MapElement getMapElement() {
    return element;
  }

  /**
   * Sets the element.
   *
   * @param element the element to set.
   */
  protected final void setMapElement(MapElement element) {
    this.element = element;
  }

  // </editor-fold>
  /**
   * An event listener interface to handle the MapElementClosedEvent.
   */
  public interface Listener extends EventListener {

    /**
     * Occurs when the MapElementClosedEvent is fired.
     *
     * @param e The MapElementClosedEvent containing information about the event.
     */
    void mapElementClosed(MapElementClosedEvent e);
  }
}
