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
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * This class can draw an ellipse onto the surface within the specified bounds.
 * This will also create a drop-shadow if usingShadow is true.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShadowEllipse extends ShadowShape {

  //<editor-fold defaultstate="collapsed" desc="Methods">
 
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
      g2.fillOval(getShadowSize().x*2, getShadowSize().y*2, getBounds().width, getBounds().height);
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
    g.fillOval(getBounds().x, getBounds().y, getBounds().width, getBounds().height);

    // Fill
    g.setColor(this.getFillColor());
    g.fillOval(getBounds().x + 1, getBounds().y + 1, getBounds().width - 2, getBounds().height - 2);
  }

  //</editor-fold>
 
}
