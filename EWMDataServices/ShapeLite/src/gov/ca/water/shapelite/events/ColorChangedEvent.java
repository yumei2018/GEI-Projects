/*
 * The MIT License
 *
 * Copyright 2015 Harold A. Dunsford Jr. Ph.D..
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

import java.awt.Color;
import java.util.EventListener;
import java.util.EventObject;

/**
 * This handles a specific kind of event, providing data access properties.
 *
 * @author hdunsford
 */
public class ColorChangedEvent extends EventObject {

  /**
   * The color.
   */
  private Color color;

  /**
   * Creates a new instance of <code>RegionChangedEvent</code>.
   *
   * @param source The Object source that initiated the event.
   * @param color The Color of the event.
   */
  public ColorChangedEvent(Object source, Color color) {
    super(source);
    this.color = color;
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the String color.
   *
   * @return the color.
   */
  public final Color getColor() {
    return color;
  }

  /**
   * Sets the color.
   *
   * @param propertyName the color to set.
   */
  protected final void setColor(Color propertyName) {
    this.color = propertyName;
  }

  // </editor-fold>
  /**
   * An event listener interface to handle the ColorChangedEvent.
   */
  public interface Listener extends EventListener {

    /**
     * Occurs when the ColorChangedEvent is fired.
     *
     * @param e The ColorChangedEvent containing information about the event.
     */
    void colorChanged(ColorChangedEvent e);
  }
}
