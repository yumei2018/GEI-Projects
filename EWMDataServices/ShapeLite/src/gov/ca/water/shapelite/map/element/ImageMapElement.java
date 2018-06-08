/*
 * The MIT License
 *
 * Copyright 2014 hdunsford.
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
package gov.ca.water.shapelite.map.element;

import gov.ca.water.shapelite.map.MapPaintArgs;
import gov.ca.water.shapelite.map.ShadowRoundedRectangle;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ImageMapElement extends MapElement {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private Image image;

  //</editor-fold>
  /**
   * Creates a new instance of the MapElementImage class.
   */
  public ImageMapElement() {

  }

  /**
   * Paints the button along with shadowing.
   *
   * @param args The MapPaintArgs containing information about the map and the
   * drawing.
   */
  @Override
  public void paint(MapPaintArgs args) {
    Graphics2D g = args.getGraphics();
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    ShadowRoundedRectangle rect = new ShadowRoundedRectangle();
    rect.setBounds(this.getBounds());
    rect.paint(g);
    int x = this.getBounds().x;
    int y = this.getBounds().y;
    int w = this.getBounds().width;
    int h = this.getBounds().height;
    g.drawImage(image, x + 4, y + 4, w - 8, h - 8, null);
  }

  /**
   * Tests the mouse click at the specified point for intersection with this
   * element.
   *
   * @param pt The Point.
   * @return Boolean, if the point intersected with this element.
   */
  @Override
  public final boolean intersects(Point pt) {
    if (super.getBounds() != null) {
      return super.getBounds().contains(pt);
    }
    return false;
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the image
   */
  public Image getImage() {
    return image;
  }

  /**
   * @param image the image to set
   */
  public void setImage(Image image) {
    this.image = image;
  }

  //</editor-fold>
}
