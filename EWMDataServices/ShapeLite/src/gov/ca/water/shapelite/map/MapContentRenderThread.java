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
 *  A background thread that allows the map to redraw it's content.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapContentRenderThread extends Thread {
  

  //<editor-fold defaultstate="collapsed" desc="Fields">
 
  
  /**
   * The actual renderer class that performs the rendering.
   */
  private MapContentRenderer renderer;
  
   //</editor-fold>
  
  /**
   * This thread is designed to interface and control the renderer.
   * @param renderer 
   */
  public MapContentRenderThread(MapContentRenderer renderer)
  {
     super(renderer);
     this.renderer = renderer;
  }
  
  //<editor-fold defaultstate="collapsed" desc="Methods">
  
  
  /**
   * If the rendering process is updated, for instance during a zoom operation,
   * then the old rendering is canceled and the render thread is instructed to
   * start over.
   */
  public void restart()
  {
    renderer.setReset(true);
  }
  
  //</editor-fold>

}
