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
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * The Shadow shapes are used for drawing fancy shadowed controls and buttons on the
 * map.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShadowRoundedRectangle extends ShadowShape {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  
  /**
   * The <code>java.awt.Point></code> that defines the integer pixel X and Y size
   * of the rounding radius for the rounded corners of the shape.
   */
  private Point roundRadius;
  //</editor-fold>
  
  /**
   * Creates a new instance of a ShadowRoundedRectangle.
   */
  public ShadowRoundedRectangle() {
    roundRadius = new Point(5, 5);
  }

  /**
   * Paints this rectangle onto the specified graphics object.
   *
   * @param g
   */
  @Override
  public void paint(Graphics2D g) {

    if (this.isUsingShadow()) {
      // SHADOWING
      int w = getBounds().width + getShadowSize().x * 4;
      int h = getBounds().height + getShadowSize().y * 4;
      BufferedImage shadow = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2 = shadow.createGraphics();
      g2.setColor(new Color(0,0,0,128));
      g2.fillRoundRect(getShadowSize().x*2, getShadowSize().y*2, getBounds().width, getBounds().height, getRoundRadius().x, getRoundRadius().y);
      g2.dispose();
      BufferedImage blurShadow = GraphicsHelper.getInstance().blur(shadow, getShadowSize());
      AffineTransform oldAff = (AffineTransform) g.getTransform().clone();
      g.translate(-getShadowSize().x, -getShadowSize().y);
      g.translate(getShadowOffset().x, getShadowOffset().y);
      g.drawImage(blurShadow, getBounds().x - getShadowSize().x, getBounds().y - getShadowSize().y, w, h, null);
      g.setTransform(oldAff);
    }

    // Border
    g.setColor(this.getBorderColor());
    g.fillRoundRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height, getRoundRadius().x, getRoundRadius().y);

    // Fill
    g.setColor(this.getFillColor());
    g.fillRoundRect(getBounds().x + 1, getBounds().y + 1, getBounds().width - 2, getBounds().height - 2, getRoundRadius().x, getRoundRadius().y);
  }
  
  /**
   * Gets the <code>java.awt.Point></code> that defines the integer pixel X and Y size
   * of the rounding radius for the rounded corners of the shape.
   * @return the roundRadius
   */
  public Point getRoundRadius() {
    return roundRadius;
  }

  /**
   * Sets the <code>java.awt.Point></code> that defines the integer pixel X and Y size
   * of the rounding radius for the rounded corners of the shape.
   * @param roundRadius the roundRadius to set
   */
  public void setRoundRadius(Point roundRadius) {
    this.roundRadius = roundRadius;
  }

 
}
