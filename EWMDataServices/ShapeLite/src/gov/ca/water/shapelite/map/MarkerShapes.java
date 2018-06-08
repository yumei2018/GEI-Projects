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

import java.awt.Shape;
import java.awt.geom.GeneralPath;

/**
 * The MarkerShapes class holds a singleton with some pre-defined shapes.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MarkerShapes {
  
  /**
   * Private singleton constructor, only accessible through requesting Instance.
   */
  private MarkerShapes() {
  }
  /**
   * Gets the single static, shared instance of this MarkerShapes class.
   * @return The shared MarkerShapes object.
   */
  public static MarkerShapes getInstance() {
    return MarkerShapesHolder.INSTANCE;
  }
  /**
   * 
   */
  private static class MarkerShapesHolder {
    private static final MarkerShapes INSTANCE = new MarkerShapes();
  }
  
  /**
   * Gets a <code>java.awt.Shape</code> that is in the shape of a cone with the 
   * top of the cone pointing downward.
   * @param w The integer width of the cone.
   * @param h The integer height of the cone.
   * @return The <code>java.awt.Shape</code> shaped like a cone.
   */
  public Shape getCone(int w, int h){
    double y2 = 13*h/32;
    double y3 = h/8;
    double x = w/2.0 - 1;
    double y = h/2.0;
    GeneralPath gp = new GeneralPath();
    // triangle
    //gp.moveTo(-x, -y2);
    gp.moveTo(x, -y2);
    gp.lineTo(0, h/2);
    gp.lineTo(-x, -y2);
    gp.curveTo(-x, -y, x, -y, x, -y2);
    gp.curveTo(x, -y3, -x, -y3, -x, -y2);
    return gp;
  }
  
  /**
   * Gets a <code>java.awt.Shape</code> that is a rectangle with the specified with and height
   * centered on 0, 0.
   * @param w The integer width.
   * @param h The integer height.
   * @return A <code>java.awt.Shape</code> that is a rectangle.
   */
  public Shape getRectangle(int w, int h){
    GeneralPath gp = new GeneralPath();
    int x = w/2;
    int y = h/2;
    gp.moveTo(-x, -y);
    gp.lineTo(x, -y);
    gp.lineTo(x, y);
    gp.lineTo(-x, y);
    gp.lineTo(-x, -y);
    return gp;
  }
  
  /**
   * Gets a <code>java.awt.Shape</code> that is a rounded triangle pointing down.
   * @param w The width of the top of the triangle.
   * @param h The height of the symbol.
   * @return A <code>java.awt.Shape</code> representing a downward pointing triangle.
   */
  public Shape getRoundTriangleDown(int w, int h){
    double xout = w/2.0 - 1;
    double y2 = h/2.0 - 1;
    double y3 = h/3.0;
    GeneralPath gp = new GeneralPath();
    gp.moveTo(-xout, 0);
    gp.curveTo(-xout, -y2, xout, -y2, xout, 0);
    gp.lineTo(0, y2);
    gp.lineTo(-xout, 0);
    return gp;
  }
  
  
  
  
  
}
