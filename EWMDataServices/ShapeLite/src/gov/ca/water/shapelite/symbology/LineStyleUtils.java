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
package gov.ca.water.shapelite.symbology;

import java.awt.BasicStroke;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class LineStyleUtils {

  /**
   * This is the length of a dash, proportional to it's width.
   */
  private static final int DASH_LENGTH = 10;

  /**
   * Private constructor for utilities class.
   */
  private LineStyleUtils() {

  }

  /**
   * Gets the style for the top most stroke in the list of pens for the border.
   *
   * @param symbolizer The symbolizer.
   * @return The LineStyle for the pen that draws last (on top).
   */
  public static LineStyle getBorderStyle(PolygonSymbolizer symbolizer) {
    LineStyle result = LineStyle.solid;
    if (symbolizer != null) {
      List<Pen> pens = symbolizer.getBorderPens();
      if (!pens.isEmpty()) {
        result = getLineStyle(pens.get(pens.size() - 1).getStroke());
      }
    }
    return result;
  }

  /**
   * Gets the line style for the specified stroke.
   *
   * @param stroke The BasicStroke to get the LineStyle for.
   * @return The LineStyle that best matches the stroke.
   */
  public static LineStyle getLineStyle(BasicStroke stroke) {
    LineStyle style = LineStyle.solid;
    if (stroke.getDashArray() != null) {
      if (stroke.getDashArray()[0]
          > stroke.getLineWidth()) {
        style = LineStyle.dashed;
      } else {
        style = LineStyle.solid;
      }
    }
    return style;
  }

  /**
   * Gets a stroke based on the specified style and width.
   *
   * @param style The line style to get the stroke for.
   * @param width The width to use for the stroke.
   * @return a BasicStroke that represents the line style.
   */
  public static BasicStroke getStroke(LineStyle style, float width) {

    float[] dash;
    BasicStroke stroke;
    switch (style) {
      case dashed:
        dash = new float[]{width * DASH_LENGTH};
        stroke = new BasicStroke(width, BasicStroke.CAP_SQUARE,
            BasicStroke.JOIN_MITER, width, dash, 0);
        break;
      case dotted:
        dash = new float[]{width, 2 * width};
        stroke = new BasicStroke(width, BasicStroke.CAP_SQUARE,
            BasicStroke.JOIN_MITER, width, dash, 0);
        break;
      default:
        stroke = new BasicStroke(width);
        break;
    }
    return stroke;
  }
}
