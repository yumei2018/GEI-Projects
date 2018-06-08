/*
 * The MIT License
 *
 * Copyright 2015 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.symbology.gui;

import gov.ca.water.shapelite.symbology.gui.color.ColorPanelDialog;
import gov.ca.water.shapelite.symbology.gui.color.ColorPanel;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.events.ColorChangedEvent;
import gov.ca.water.shapelite.symbology.resources.SymbolImages;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ColorDropDown extends JComponent {

  /**
   * The left border outline when the mouse is not on the control.
   */
  private static final Color LEFT_NORM = new Color(226, 227, 234);

  /**
   * The right border outline when the mouse is not on the control.
   */
  private static final Color RIGHT_NORM = new Color(219, 223, 230);

  /**
   * The top border outline when the mouse is not on the control.
   */
  private static final Color TOP_NORM = new Color(171, 173, 179);

  /**
   * The bottom border outline when the mouse is not on the control.
   */
  private static final Color BOTTOM_NORM = new Color(227, 233, 239);

  /**
   * A light gray color for the default color region border.
   */
  private static final Color LIGHT_GRAY = new Color(211, 211, 211);

  /**
   * A dark gray color that is used for the dotted focus rectangle.
   */
  private static final Color DARK_GRAY = new Color(128, 128, 128);

  /**
   * The integer horizontal and vertical offset of the color rectangle in
   * pixels.
   */
  private static final int BOX_OFFSET = 4;

  /**
   * The height of the color rectangle.
   */
  private static final int BOX_HEIGHT = 13;

  /**
   * The width of the color rectangle.
   */
  private static final int BUTTON_WIDTH = 17;

  /**
   * The integer padding around the color box in pixels.
   */
  private static final int HORIZONTAL_PADDING = 3;

  /**
   * The height of the control.
   */
  private static final int FIXED_HEIGHT = 21;

  /**
   * Boolean that is true when the mouse is over this control.
   */
  private boolean hasMouse;

  /**
   * The color value for this control.
   */
  private Color value;

  /**
   * The color panel frame.
   */
  private ColorPanelDialog frame;

  /**
   * Creates a new instance of the color dropdown.
   */
  public ColorDropDown() {
    super.setPreferredSize(new Dimension(74, FIXED_HEIGHT));
    super.setMaximumSize(new Dimension(1000, FIXED_HEIGHT));
    super.setFocusable(true);
    value = Color.GREEN;
    super.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        ColorDropDown.this.repaint();
      }

      @Override
      public void focusLost(FocusEvent e) {
        ColorDropDown.this.repaint();
      }
    });
    super.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        hasMouse = true;
        ColorDropDown.this.repaint();
      }

      @Override
      public void mouseExited(MouseEvent e) {
        hasMouse = false;
        ColorDropDown.this.repaint();
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        int x = 0;
        int y = ColorDropDown.this.getHeight();
        if (frame != null) {
          frame.setVisible(false);
          frame.dispose();
        }

        frame = new ColorPanelDialog(
            SwingUtilities.getWindowAncestor(ColorDropDown.this),
            ColorDropDown.this, x, y);

        ColorPanel panel = frame.getColorPanel();
        panel.setValue(value);
        panel.addColorChangedListener(new ColorChangedEvent.Listener() {
          @Override
          public void colorChanged(ColorChangedEvent e) {
            value = e.getColor();
            repaint();
            fireColorChanged(e);
          }
        });
        frame.setVisible(true);

      }

    });
  }

  //<editor-fold defaultstate="collapsed" desc="ColorChangedEvent">
  /**
   * The listeners for the color changed event.
   */
  private final List<ColorChangedEvent.Listener> colorChangedListeners = new ArrayList<>();

  /**
   * Adds the specified listener to the list of listeners to be notified during
   * an event. If the item is already in the list, it will not be added a second
   * time.
   *
   * @param listener The ColorChangedEvent.Listener to connect.
   */
  public final void addColorChangedListener(ColorChangedEvent.Listener listener) {
    if (!colorChangedListeners.contains(listener)) {
      colorChangedListeners.add(listener);
    }
  }

  /**
   * Removes the specified listener from the list if it is in the list.
   *
   * @param listener The ColorChangedEvent.Listener to disconnect.
   */
  public final void removeColorChangedListener(ColorChangedEvent.Listener listener) {
    colorChangedListeners.remove(listener);
  }

  /**
   * Fires the ColorChanged event and notifies each of the listeners.
   *
   * @param e A {EventType}Event with the source object and any properties
   * associated with this event.
   */
  private void fireColorChanged(ColorChangedEvent e) {
    for (ColorChangedEvent.Listener listener : colorChangedListeners) {
      listener.colorChanged(e);
    }
  }

  //</editor-fold>
  /**
   * Paints this component.
   *
   * @param g The graphics surface to draw on.
   */
  @Override
  protected final void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    if (!hasMouse) {
      drawLitBounds(g);
    } else {
      drawNormalBounds(g);
    }
    drawColorBox(g2);
    drawButton(g2);

  }

  /**
   * Gets the parent JPanel.
   *
   * @param component
   * @return
   */
  private Optional<JPanel> getParentPanel() {
    Container parent = this.getParent();

    while (parent != null && !(parent instanceof JPanel)) {
      parent = parent.getParent();
    }
    if (parent instanceof JPanel) {
      return Optional.ofNullable((JPanel) parent);
    }
    return Optional.empty();

  }

  /**
   * Draws the color rectangle showing the current color.
   *
   * @param g The graphics object.
   */
  final void drawColorBox(Graphics2D g) {
    int boxWidth = this.getWidth() - BUTTON_WIDTH - HORIZONTAL_PADDING * 2;
    g.setColor(this.value);
    g.fillRect(BOX_OFFSET, BOX_OFFSET, boxWidth, BOX_HEIGHT);
    g.setColor(LIGHT_GRAY);
    g.drawRect(BOX_OFFSET, BOX_OFFSET, boxWidth, BOX_HEIGHT);
    if (this.hasFocus()) {
      g.setColor(DARK_GRAY);
      g.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT,
          BasicStroke.JOIN_BEVEL, 0, new float[]{1}, 0));
      g.drawRect(BOX_OFFSET, BOX_OFFSET, boxWidth, BOX_HEIGHT);
    }
  }

  /**
   * Draws the button image to the specified graphics object.
   *
   * @param g The Graphics surface to draw on.
   */
  final void drawButton(Graphics g) {
    BufferedImage button;
    if (hasMouse) {
      button = SymbolImages.get("ColorLitButton.png").orElse(null);
    } else {
      button = SymbolImages.get("ColorNormalButton.png").orElse(null);
    }
    if (button != null) {
      g.drawImage(button, this.getWidth() - button.getWidth(), 0, null);
    }
  }

  /**
   * Draws the lit border.
   *
   * @param g the Graphics surface to draw on.
   */
  final void drawLitBounds(Graphics g) {
    int b = this.getHeight() - 1;
    int r = this.getWidth() - 1;
    g.setColor(TOP_NORM);
    g.drawLine(0, 0, r, 0);
    g.setColor(LEFT_NORM);
    g.drawLine(0, 0, 0, b);
    g.setColor(RIGHT_NORM);
    g.drawLine(r, 0, r, b);
    g.setColor(BOTTOM_NORM);
    g.drawLine(0, b, r, b);
  }

  /**
   * Draws the normal border.
   *
   * @param g The graphics surface to draw on.
   */
  final void drawNormalBounds(Graphics g) {
    int b = this.getHeight() - 1;
    int r = this.getWidth() - 1;
    g.setColor(TOP_NORM);
    g.drawLine(0, 0, r, 0);
    g.setColor(LEFT_NORM);
    g.drawLine(0, 0, 0, b);
    g.setColor(RIGHT_NORM);
    g.drawLine(r, 0, r, b);
    g.setColor(BOTTOM_NORM);
    g.drawLine(0, b, r, b);
  }

  /**
   * Gets the selected color value for this control.
   *
   * @return the value
   */
  public final Color getValue() {
    return value;
  }

  /**
   * sets the selected color value for this control.
   *
   * @param selectedColor the value to set
   */
  public final void setSelectedColor(Color selectedColor) {
    this.value = selectedColor;
  }
}
