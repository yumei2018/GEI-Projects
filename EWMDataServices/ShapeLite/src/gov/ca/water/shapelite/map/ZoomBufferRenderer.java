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

/**
 * This class is responsible for handling in a background process, the creation of zoom
 * buffers. These buffers can be used by the track slider to show different zoom levels.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ZoomBufferRenderer implements Runnable {

  /**
   * A boolean that can be used to reset the creation of the zoom levels. If this is
   * triggered, all the existing buffers are discarded.
   */
  private boolean reset;
  
  //<editor-fold defaultstate="collapsed" desc="Fields">
  
  /**
   * The ZoomBuffer is a list of cached views, one for each level. Sliding the track
   * slider will show different views.
   */
  private ZoomBuffer buffer;

  //</editor-fold>
  
  /**
   * Creates a new instance of the ZoomBuffer renderer.
   *
   * @param buffer The ZoomBuffer object that this class controls the filling of.
   */
  public ZoomBufferRenderer(ZoomBuffer buffer) {
    this.buffer = buffer;
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
 
  
  /**
   * The runnable process that is run as a background thread.
   */
  @Override
  public void run() {
    do {

      reset = false;
      for (int i = 0; i < 19; i++) {
        /**
         * The actual method to create an image for the buffer.
         */
        buffer.createBuffer(i);
        if (reset) {
          break;
        }
      }
    } while (reset);
  }

   //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Properties">
 
  
  /**
   * Gets a boolean that can be used to reset the creation of the zoom levels. If this is
   * triggered, all the existing buffers are discarded.
   *
   * @return the reset
   */
  public boolean isReset() {
    return reset;
  }

  /**
   * Sets a boolean that can be used to reset the creation of the zoom levels. If this is
   * triggered, all the existing buffers are discarded.
   *
   * @param reset the reset to set
   */
  public void setReset(boolean canceled) {
    this.reset = canceled;
  }
  
   //</editor-fold>
}
