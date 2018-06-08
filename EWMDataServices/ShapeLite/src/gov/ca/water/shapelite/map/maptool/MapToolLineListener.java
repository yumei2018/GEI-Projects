/*
 * The MIT License
 *
 * Copyright 2014 ileung.
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

package gov.ca.water.shapelite.map.maptool;

import gov.ca.water.shapelite.events.MapEventMouse;
import java.util.EventListener;

/**
 *
 * @author ileung
 */
public interface MapToolLineListener extends EventListener {
      /**
   * This event is fired immediately before the selection tool selects content.
   * @param e 
   */
  void beforeSelect(MapEventMouse e);
  
  /**
   * This event is fired immediately after the selection tool selects content.
   * @param e 
   */
  void afterSelect(MapEventMouse e);

}