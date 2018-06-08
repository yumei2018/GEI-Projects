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

import java.util.EventListener;
import java.util.EventObject;

/**
 * This handles a specific kind of event, providing data access properties.
 *
 * @author hdunsford
 */
public class ProgressChangedEvent extends EventObject {

  /**
   * The property of the event object.
   */
  private String message;

  /**
   * The integer percent.
   */
  private int percent;

  /**
   * Creates a new instance of <code>RegionChangedEvent</code>.
   *
   * @param source The Object source that initiated the event.
   */
  public ProgressChangedEvent(Object source) {
    super(source);
  }

  /**
   * Creates a new instance of <code>RegionChangedEvent</code>.
   *
   * @param source The Object source that initiated the event.
   * @param message The String message.
   */
  public ProgressChangedEvent(Object source, String message) {
    super(source);
    this.message = message;
  }

  /**
   * Creates a new instance of <code>RegionChangedEvent</code>.
   *
   * @param source The Object source that initiated the event.
   * @param message The String message.
   * @param percent The integer percent.
   */
  public ProgressChangedEvent(Object source, String message, int percent) {
    super(source);
    this.message = message;
    this.percent = percent;
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the String message.
   *
   * @return the message.
   */
  public final String getMessage() {
    return message;
  }

  /**
   * Sets the message.
   *
   * @param message the message to set.
   */
  protected final void setMessage(String message) {
    this.message = message;
  }

  /**
   * Gets the integer percent.
   *
   * @return the percent
   */
  public final int getPercent() {
    return percent;
  }

  /**
   * Sets the integer percent.
   *
   * @param percent the percent to set
   */
  public final void setPercent(int percent) {
    this.percent = percent;
  }

  // </editor-fold>
  /**
   * An event listener interface to handle the ProgressChangedEvent.
   */
  public interface Listener extends EventListener {

    /**
     * Occurs when the ProgressChangedEvent is fired.
     *
     * @param e The ProgressChangedEvent containing information about the event.
     */
    void progressChanged(ProgressChangedEvent e);
  }
}
