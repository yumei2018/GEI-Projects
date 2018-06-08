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

import java.util.EventListener;
import java.util.EventObject;

/**
 * This handles a specific kind of event, providing data access properties.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ValidChangedEvent extends EventObject {

  /**
   * A valid value.
   */
  private final boolean valid;

  /**
   * Creates a new instance of <code>RegionChangedEvent</code>.
   *
   * @param source The Object source that initiated the event.
   * @param valid The @{propertyType} valid to set.
   */
  public ValidChangedEvent(Object source, boolean valid) {
    super(source);
    this.valid = valid;
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the boolean valid.
   *
   * @return the valid.
   */
  public final boolean isValid() {
    return valid;
  }

  
  // </editor-fold>
  /**
   * An event listener interface to handle the ValidChangedEvent.
   */
  public interface Listener extends EventListener {

    /**
     * Occurs when the ValidChangedEvent is fired.
     *
     * @param e The ValidChangedEvent containing information about the event.
     */
    void validChanged(ValidChangedEvent e);
  }
}
