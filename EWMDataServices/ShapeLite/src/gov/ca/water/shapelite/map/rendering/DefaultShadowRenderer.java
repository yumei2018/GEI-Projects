/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.map.rendering;

import gov.ca.water.shapelite.map.GraphicsHelper;
import gov.ca.water.shapelite.symbology.ShadowSymbolizer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DefaultShadowRenderer implements ShadowRenderer {

  /**
   * Paints a shadow.
   *
   * @param g The Graphics2D surface for drawing.
   * @param path The GeneralPath to draw the shadow for.
   * @param symbolizer The symbolizer controlling the offset, and other
   * properties.
   * @param clip The clip rectangle to cut off the size of the image.
   */
  @Override
  public final void paint(Graphics2D g, GeneralPath path, ShadowSymbolizer symbolizer,
      Rectangle clip) {
    AffineTransform oldAff = (AffineTransform) g.getTransform().clone();
    int w = clip.width + symbolizer.getShadowSize().x;
    int h = clip.height + symbolizer.getShadowSize().y;
    BufferedImage shadow = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = shadow.createGraphics();
    g2.translate(-clip.x, -clip.y);
    g2.setColor(symbolizer.getShadowColor());
    g2.fill(path);
    g2.dispose();
    BufferedImage blurShadow = GraphicsHelper.getInstance().blur(shadow,
        symbolizer.getShadowSize());
    g.translate(symbolizer.getOffset().x, symbolizer.getOffset().y);
    g.drawImage(blurShadow, 0, 0, w, h, null);
    g.setTransform(oldAff);
  }
}
