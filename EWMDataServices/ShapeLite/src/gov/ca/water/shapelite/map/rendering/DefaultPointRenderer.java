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

import gov.ca.water.shapelite.data.marker.PointMarker;
import gov.ca.water.shapelite.symbology.PointSymbolizer;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DefaultPointRenderer implements PointRenderer {

  /**
   * The width to draw selected lines.
   */
  private static final int SELECTION_WIDTH = 3;

  /**
   * The default implementation for rendering markers.
   *
   * @param g The Graphics2D surface to draw on.
   * @param center The Point where the center of the marker should be drawn.
   * @param marker The marker to draw (which currently controls selection).
   * @param symbolizer The symbolic appearance of the marker.
   */
  @Override
  public final void paint(Graphics2D g, Point center, PointMarker marker,
          PointSymbolizer symbolizer) {
    g.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
    // Icon rendering takes priority
    Rectangle box = new Rectangle(center.x - symbolizer.getWidth() / 2, center.y
            - symbolizer.getHeight() / 2, symbolizer.getWidth(), symbolizer.getHeight());
    if (marker.isSelected()) {
      if (symbolizer.getSelectedIcon() != null) {
        g.drawImage(symbolizer.getSelectedIcon(), box.x, box.y, box.width,
                box.height, null);
        return;
      }
      if (symbolizer.getIcon() != null) {
        // If selected, the selected icon is null, but the normal icon is set, then
        // draw a box around the normal icon.
        g.drawImage(symbolizer.getIcon(), box.x, box.y, box.width, box.height,
                null);
        g.setStroke(new BasicStroke(SELECTION_WIDTH));
        g.setColor(symbolizer.getSelectedBorderColor());
        g.drawRect(box.x, box.y, box.width, box.height);
        return;
      }
    }
    if (symbolizer.getIcon() != null && !marker.isSelected()) {
      g.drawImage(symbolizer.getIcon(), box.x, box.y, box.width, box.height,
              null);
      return;

    }
    AffineTransform original = g.getTransform();
    Shape shape;
    if (symbolizer.getShape() != null) {
      shape = symbolizer.getShape();
      g.translate(center.x, center.y);
    } else {
      shape = symbolizer.createShape(box);
    }

    // Antialiasing for smooth curves, not pixelated curves.
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
    // Fill the shape first, so the border is not hidden by the fill.
    g.setColor(symbolizer.getFillColor());
    g.fill(shape);
    // Highlight selected points in a thick cyan border.
    if (marker.isSelected()) {
      g.setStroke(new BasicStroke(SELECTION_WIDTH));
      g.setColor(symbolizer.getSelectedBorderColor());
    } else {
      g.setStroke(new BasicStroke(1));
      g.setColor(symbolizer.getBorderColor());
    }

    g.draw(shape);

    if (symbolizer.getShape() != null) {
      g.setTransform(original);
    }

  }

}
