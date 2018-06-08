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
package gov.ca.water.shapelite.envelope;

import gov.ca.water.shapelite.events.EnvelopeChangedEvent;
import gov.ca.water.shapelite.coordinate.ObservableCoord;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface ObservableEnvelope extends BasicEnvelope {

  /**
   * @return the minZ
   */
  @Override
  ObservableCoord getMin();

  /**
   * @return the maxZ
   */
  @Override
  ObservableCoord getMax();

  /**
   * Adds the specified listener to the list of listeners to be notified during
   * an event. If the item is already in the list, it will not be added a second
   * time.
   *
   * @param listener The EnvelopeChangedEvent.Listener to connect.
   */
  void addEnvelopeChangedListener(
      EnvelopeChangedEvent.Listener listener);

  /**
   * Removes the specified listener from the list if it is in the list.
   *
   * @param listener The EnvelopeChangedEvent.Listener to disconnect.
   */
  void removeEnvelopeChangedListener(
      EnvelopeChangedEvent.Listener listener);
}
