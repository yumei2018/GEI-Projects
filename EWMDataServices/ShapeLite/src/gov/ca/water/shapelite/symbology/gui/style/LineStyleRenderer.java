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
package gov.ca.water.shapelite.symbology.gui.style;

import gov.ca.water.shapelite.symbology.LineStyle;
import java.awt.BasicStroke;
import static java.awt.BasicStroke.CAP_SQUARE;
import static java.awt.BasicStroke.JOIN_MITER;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LineStyleRenderer extends DefaultListCellRenderer {

  /**
   * The vertical height of a single item in the combo box.
   */
  public static final int ITEM_HEIGHT = 16;

  /**
   * The spacing in pixels between the drawn line and the edge of the control.
   */
  public static final int PADDING = 10;

  /**
   * The miter limit for rendering the lines in pixels.
   */
  public static final int MITER_LIMIT = 10;

  /**
   * The dash width in pixels for the dashed lines in this control.
   */
  public static final int DASH_LENGTH = 10;

  /**
   * The width of the image created.
   */
  public static final int IMAGE_WIDTH = 100;

  /**
   * Gets the list cell renderer.
   *
   * @param list The JList control (or combo box) that this is for.
   * @param value The value to be rendered, in this case a LineStyle enum.
   * @param index The integer index in the list / combo box.
   * @param isSelected Boolean true if the entry is selected.
   * @param cellHasFocus Boolean true if the entry has focus.
   * @return The Component to be rendered.
   */
  @SuppressWarnings("rawtypes")
  @Override
  public final Component getListCellRendererComponent(JList list, Object value,
      int index, boolean isSelected, boolean cellHasFocus) {
    Component result = super.getListCellRendererComponent(list, value, index,
        isSelected, cellHasFocus);
    if (value instanceof LineStyle && result instanceof JLabel) {
      JLabel label = (JLabel) result;
      label.setText("");
      label.setSize(IMAGE_WIDTH, ITEM_HEIGHT);
      BufferedImage img = new BufferedImage(IMAGE_WIDTH, ITEM_HEIGHT,
          BufferedImage.TYPE_4BYTE_ABGR);
      Graphics g = img.createGraphics();
      Color lineColor = Color.BLACK;
      if (isSelected) {
        lineColor = Color.WHITE;
      }
      g.setColor(lineColor);
      if (g instanceof Graphics2D) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(getStroke((LineStyle) value));
      }
      int y = ITEM_HEIGHT / 2;
      g.drawLine(PADDING, y, IMAGE_WIDTH - PADDING, y);
      g.dispose();
      label.setIcon(new ImageIcon(img));
    }
    return result;
  }

  /**
   * This is just the stroke used in the special case of drawing the combo box
   * entries. The real stroke will be different because it might have a width
   * that is different from the specified width.
   *
   * @param style The LineStyle enum specifying which basic line style to use.
   * @return The BasicStroke.
   */
  /**
   * This is just the stroke used in the special case of drawing the combo box
   * entries. The real stroke will be different because it might have a width
   * that is different from the specified width.
   *
   * @param style The LineStyle enum specifying which basic line style to use.
   * @return The BasicStroke.
   */
  private BasicStroke getStroke(LineStyle style) {

    switch (style) {
      case dashed:
        return new BasicStroke(1, CAP_SQUARE, JOIN_MITER, 1,
            new float[]{DASH_LENGTH}, 0);
      case dotted:
        return new BasicStroke(1, CAP_SQUARE, JOIN_MITER, 1,
            new float[]{1, 2}, 0);
      default:
        return new BasicStroke();

    }
  }

}
