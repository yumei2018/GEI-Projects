/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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

import gov.ca.water.shapelite.Shape;
import java.util.EventListener;
import java.util.EventObject;

/**
 * This handles a specific kind of event, providing data access properties.
 *
 * @author hdunsford
 */
public class PolygonFinishedEvent extends EventObject {

  /**
   * The property of the event object.
   */
  private Shape polygon;

  /**
   * Creates a new instance of <code>RegionChangedEvent</code>.
   *
   * @param source The Object source that initiated the event.
   * @param polygon The polygon.
   */
  public PolygonFinishedEvent(Object source, Shape polygon) {
    super(source);
    this.polygon = polygon;
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the Shape polygon.
   *
   * @return the polygon.
   */
  public final Shape getShape() {
    return polygon;
  }

  /**
   * Sets the polygon.
   *
   * @param polygon the polygon to set.
   */
  protected final void setShape(Shape polygon) {
    this.polygon = polygon;
  }

  // </editor-fold>
  /**
   * An event listener interface to handle the PolygonFinishedEvent.
   */
  public interface Listener extends EventListener {

    /**
     * Occurs when the PolygonFinishedEvent is fired.
     *
     * @param e The PolygonFinishedEvent containing information about the event.
     */
    void polygonFinished(PolygonFinishedEvent e);
  }
}
