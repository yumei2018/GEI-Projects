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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * This class can draw an shape onto the surface within the specified bounds.
 * This will also create a drop-shadow if usingShadow is true.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public abstract class ShadowShape {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  
  /**
   * The color for the border of the shape.  This is part of the main shape, and not
   * part of the shadow.
   */
  private Color borderColor;
  
  /**
   * The rectangle that this shape is inscribed within.
   */
  private Rectangle bounds;
  
  /**
   * The color of the inside of the shape.  This is not affected by the shadow.
   */
  private Color fillColor;
  
  /**
   * The <code>java.awt.Point</code> that has the offset in pixels in the X and 
   * Y directions of the shadow.  The shadow is usually to the bottom and right of the
   * item.
   */
  private Point shadowOffset;
  
  /**
   * The <code>java.awt.Point</code> that has the size in pixels in the X and Y
   * directions.  This affects the blur radius.
   */
  private Point shadowSize;

  /**
   * A boolean that determines whether the shadow should be drawn.
   */
  private boolean usingShadow;
  
  //</editor-fold>

  protected ShadowShape() {
    shadowSize = new Point(6, 6);
    shadowOffset = new Point(1, 2);
    borderColor = new Color(0,0,0,128);
    usingShadow = true;
    fillColor = Color.WHITE;
  }
  
  //<editor-fold defaultstate="collapsed" desc="Methods">
  

  /**
   * Paints this rectangle onto the specified graphics object.
   *
   * @param g
   */
  public abstract void paint(Graphics2D g);

  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Properties">
  
  /**
   * Gets the color for the border of the shape.  This is part of the main shape, and not
   * part of the shadow.
   * @return the borderColor.
   */
  public Color getBorderColor() {
    return borderColor;
  }

  /**
   * Sets the color for the border of the shape.  This is part of the main shape, and not
   * part of the shadow.
   * @param borderColor the borderColor to set.
   */
  public void setBorderColor(Color borderColor) {
    this.borderColor = borderColor;
  }
  
  /**
   * Gets the rectangle that this shape is inscribed within.
   * @return the bounds
   */
  public Rectangle getBounds() {
    return bounds;
  }

  /**
   * Sets the rectangle that this shape is inscribed within.
   * @param bounds the bounds to set
   */
  public void setBounds(Rectangle bounds) {
    this.bounds = bounds;
  }

  /**
   * Gets the color of the inside of the shape.  This is not affected by the shadow.
   * @return the fillColor.
   */
  public Color getFillColor() {
    return fillColor;
  }

  /**
   * Sets the color of the inside of the shape.  This is not affected by the shadow.
   * @param fillColor the fillColor to set.
   */
  public void setFillColor(Color fillColor) {
    this.fillColor = fillColor;
  }

  /**
   * Gets the <code>java.awt.Point</code> that has the offset in pixels in the X and 
   * Y directions of the shadow.  The shadow is usually to the bottom and right of the
   * item.
   * @return the shadowOffset
   */
  public Point getShadowOffset() {
    return shadowOffset;
  }

  /**
   * Sets the <code>java.awt.Point</code> that has the offset in pixels in the X and 
   * Y directions of the shadow.  The shadow is usually to the bottom and right of the
   * item.
   * @param shadowOffset the shadowOffset to set
   */
  public void setShadowOffset(Point shadowOffset) {
    this.shadowOffset = shadowOffset;
  }

  /**
   * Gets the <code>java.awt.Point</code> that has the size in pixels in the X and Y
   * directions.  This affects the blur radius.
   * @return the shadowSize
   */
  public Point getShadowSize() {
    return shadowSize;
  }

  /**
   * Sets the <code>java.awt.Point</code> that has the size in pixels in the X and Y
   * directions.  This affects the blur radius.
   * @param shadowSize the shadowSize to set
   */
  public void setShadowSize(Point shadowSize) {
    this.shadowSize = shadowSize;
  }

  /**
   * Gets a boolean that determines whether the shadow should be drawn.
   * @return the usingShadow
   */
  public boolean isUsingShadow() {
    return usingShadow;
  }

  /**
   * Sets a boolean that determines whether the shadow should be drawn.
   * @param usingShadow the usingShadow to set
   */
  public void setUsingShadow(boolean usingShadow) {
    this.usingShadow = usingShadow;
  }
  
  //</editor-fold>
}
