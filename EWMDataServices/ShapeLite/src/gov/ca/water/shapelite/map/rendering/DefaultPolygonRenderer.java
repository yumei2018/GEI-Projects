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

import gov.ca.water.shapelite.data.marker.PolygonMarker;
import gov.ca.water.shapelite.symbology.Pen;
import gov.ca.water.shapelite.symbology.PolygonSymbolizer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DefaultPolygonRenderer implements PolygonRenderer {

  /**
   * The renderer that controls drawing the shadow.
   */
  private ShadowRenderer shadowRenderer;

  /**
   * The width (in pixels) of the border line when the shape is selected.
   */
  private static final int SELECTED_BORDER_WIDTH = 3;

  /**
   * Creates a new instance of the DefaultMarkerPolygonRnderer.
   */
  public DefaultPolygonRenderer() {
    shadowRenderer = new DefaultShadowRenderer();
  }

  /**
   * Paints the polygon.
   *
   * @param g The graphics object to draw on.
   * @param polygon The GeneralPath to draw.
   * @param marker The marker that describes selection state.
   * @param symbolizer The symbolizer describing what the polygon looks like.
   * @param clip A clip rectangle for cropping the shadow image.
   */
  @Override
  public final void paint(Graphics2D g, GeneralPath polygon, PolygonMarker marker,
      PolygonSymbolizer symbolizer, Rectangle clip) {

    if (polygon == null) {
      return;
    }
    g.setRenderingHint(
        RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    g.setRenderingHint(
        RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    /* Call close path to ensure the shape is closed */
    polygon.closePath();

    if (symbolizer.getDropShadow() != null && shadowRenderer != null) {
      shadowRenderer.paint(g, polygon, symbolizer.getDropShadow(), clip);
    }

    g.setColor(symbolizer.getFillColor());
    g.fill(polygon);
    if (marker.isSelected()) {
      g.setColor(symbolizer.getSelectedColor());
      g.setStroke(new BasicStroke(SELECTED_BORDER_WIDTH));
      g.draw(polygon);
    } else {
      for (Pen pen : symbolizer.getBorderPens()) {
        g.setStroke(pen.getStroke());
        g.setColor(pen.getColor());
        g.draw(polygon);
      }
    }

    if (marker.getLabel() != null) {
      int x = polygon.getBounds().x + polygon.getBounds().width / 2;

      int y = polygon.getBounds().y + polygon.getBounds().height / 2;
      Color c = symbolizer.getFillColor();
      float[] hsbvals = new float[3];
      Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsbvals);
      if (hsbvals[2] > .5) {
        g.setColor(Color.BLACK);
      } else {
        g.setColor(Color.WHITE);
      }
      Rectangle2D rect = g.getFontMetrics().getStringBounds(marker.getLabel(), g);
      x = x - (int) rect.getWidth() / 2;
      g.drawString(marker.getLabel(), x, y);
    }

  }

  /**
   * Gets the renderer for controlling the shadow drawing.
   *
   * @return the shadowRenderer
   */
  public final ShadowRenderer getShadowRenderer() {
    return shadowRenderer;
  }

  /**
   * Sets the renderer for drawing the shadows.
   *
   * @param shadowRenderer the shadowRenderer to set
   */
  public final void setShadowRenderer(ShadowRenderer shadowRenderer) {
    this.shadowRenderer = shadowRenderer;
  }

}
