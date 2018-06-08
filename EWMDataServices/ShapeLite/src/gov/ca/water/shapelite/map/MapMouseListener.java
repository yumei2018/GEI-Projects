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

import gov.ca.water.shapelite.events.MapEventMouse;
import java.util.EventListener;

/**
 * This class provides a mouse listener where each event also includes the 
 * information about the map control.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface MapMouseListener extends EventListener {
  
  /**
   * Occurs when the a mouse button is pressed and released.
   * @param e The MapEventMouse argument with information about the mouse condition
   * and the Map control that was the source of the event.
   */
  void mouseClicked(MapEventMouse e);
  
  /**
   * Occurs when the mouse button is pressed.
   * @param e The MapEventMouse argument with information about the mouse condition
   * and the Map control that was the source of the event.
   */
  void mousePressed(MapEventMouse e);
  
  /**
   * Occurs when the mouse button is released.
   * @param e The MapEventMouse argument with information about the mouse condition
   * and the Map control that was the source of the event.
   */
  void mouseReleased(MapEventMouse e);
  
  /**
   * Occurs when the mouse enters the map.
   * @param e The MapEventMouse argument with information about the mouse condition
   * and the Map control that was the source of the event.
   */
  void mouseEntered(MapEventMouse e);
  
  /**
   * Occurs when the mouse leaves the map.
   * @param e The MapEventMouse argument with information about the mouse condition
   * and the Map control that was the source of the event.
   */
  void mouseExited(MapEventMouse e);
  
  /**
   * Occurs when the mouse is dragged on the map.
   * @param e The MapEventMouse argument with information about the mouse condition
   * and the Map control that was the source of the event.
   */
  void mouseDragged(MapEventMouse e);
  
  /**
   * Occurs when the mouse is moved on the map.
   * @param e The MapEventMouse argument with information about the mouse condition
   * and the Map control that was the source of the event.
   */
  void mouseMoved(MapEventMouse e);
  
}
