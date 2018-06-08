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

import gov.ca.water.shapelite.events.MapEventTrack;
import java.util.EventListener;

/**
 * This is an event listener interface for listening to the TrackSlider control
 * that can be drawn on the map.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface TrackListener extends EventListener {
  
  /**
   * Occurs when the sliding button begins to be dragged.
   * @param e The MapEventTrack which has information about the Map control and 
   * the track slider control.
   */
  public void handleGrabbed(MapEventTrack e);
  
  /**
   * Occurs when the sliding button that is the handle of the slider is released.
   * @param e The MapEventTrack which has information about the Map control and 
   * the track slider control.
   */
  public void handleReleased(MapEventTrack e);
  
  /**
   * Occurs when the position of the track slider handle is updated.
   * @param e The MapEventTrack which has information about the Map control and 
   * the track slider control.
   */
  public void positionChanged(MapEventTrack e);
}
