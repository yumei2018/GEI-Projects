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

import gov.ca.water.shapelite.data.marker.LineMarker;
import gov.ca.water.shapelite.symbology.Pen;
import gov.ca.water.shapelite.symbology.LineSymbolizer;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DefaultLineRenderer implements TextRenderer {

  /**
   * Paints the general path.
   *
   * @param g The graphics object to paint to.
   * @param line The line to draw.
   * @param marker The marker that controls selected status.
   * @param symbolizer The symbolizer that controls what the line looks like.
   */
  @Override
  public final void paint(Graphics2D g, GeneralPath line, LineMarker marker,
      LineSymbolizer symbolizer) {
    g.setRenderingHint(
        RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    g.setRenderingHint(
        RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    if (!marker.isSelected()) {
      for (Pen pen : symbolizer.getPens()) {
        g.setStroke(pen.getStroke());
        g.setColor(pen.getColor());
        g.draw(line);
      }
    } else {
      // Selected uses the width of the wider of the two strokes.
      g.setStroke(new BasicStroke(symbolizer.getSelectedWidth()));
      g.setColor(symbolizer.getSelectedColor());
      g.draw(line);
    }

  }

}
