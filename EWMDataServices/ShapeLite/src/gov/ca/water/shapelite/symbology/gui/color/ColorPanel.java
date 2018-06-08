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
package gov.ca.water.shapelite.symbology.gui.color;

import gov.ca.water.shapelite.symbology.gui.color.ColorBox;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.events.ColorChangedEvent;
import gov.ca.water.shapelite.symbology.resources.SymbolImages;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JColorChooser;
import javax.swing.SwingUtilities;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ColorPanel extends JComponent {

  /**
   * The image background that actually draws the permanent boxes.
   */
  private final BufferedImage background;

  /**
   * The grip image to use for the handle.
   */
  private final BufferedImage grip;

  /**
   * The color boxes list for hit testing and color retrieval.
   */
  private final List<ColorBox> colorBoxes;

  /**
   * The y position of the grip.
   */
  private static final int GRIP_Y = 17;

  /**
   * The rectangle for the transparency slider.
   */
  private static final Rectangle RECT_SLIDER = new Rectangle(2, 18, 151, 16);

  /**
   * The rectangle for the "More Colors" option.
   */
  private static final Rectangle MORE_BUTTON = new Rectangle(1, 158, 151, 17);

  /**
   * The color of the selection box around the "current" color.
   */
  private static final Color SELECT_COLOR = new Color(255, 69, 0);

  /**
   * The pixel offset of the top row from the left.
   */
  private static final int LEFT_OFFSET = 3;

  /**
   * The pixel offset of the top row of boxes from the top.
   */
  private static final int TOP_ROW_Y = 50;

  /**
   * The pixel offset of the middle rows of boxes from the top.
   */
  private static final int MIDDLE_Y = 66;

  /**
   * The pixel offset of the bottom row of boxes from the top.
   */
  private static final int BOTTOM_Y = 146;

  /**
   * The width and height of the squares.
   */
  private static final int BOX_SIZE = 13;

  /**
   * The integer padding.
   */
  private static final int PADDING = 2;

  /**
   * There are five rows of colors in the middle with different shades.
   */
  private static final int NUM_MIDDLE_ROWS = 5;

  /**
   * There are 10 columns of boxes.
   */
  private static final int NUM_COLUMNS = 10;

  /**
   * The maximum alpha.
   */
  private static final int MAX_ALPHA = 255;

  /**
   * If this is true the mouse was down on the slider and has not yet been
   * released.
   */
  private boolean sliding;

  /**
   * The point mouse position in client coordinates.
   */
  private Point mousePosition;

  /**
   * The Color value that is ultimately selected.
   */
  private Color value;

  /**
   * The Size of this panel.
   */
  private static final Dimension SIZE = new Dimension(155, 177);

  /**
   * Boolean, true if the dialog is showing.
   */
  private boolean showingDialog;

  /**
   * Creates a new instance of the color panel class.
   *
   *
   */
  public ColorPanel() {
    super.setMaximumSize(SIZE);
    super.setMinimumSize(SIZE);
    super.setPreferredSize(SIZE);
    super.setSize(SIZE);
    grip = SymbolImages.get("Grip.png").orElse(null);
    background = SymbolImages.get("ThemeTransparent.png").orElse(null);
    colorBoxes = new ArrayList<>();
    createBoxes();
    super.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseMoved(MouseEvent e) {
        mousePosition = e.getPoint();
        if (sliding) {
          setSlider(e.getX());
        }
        repaint();
      }

      @Override
      public void mouseDragged(MouseEvent e) {
        mousePosition = e.getPoint();
        if (sliding) {
          setSlider(e.getX());
        }
        repaint();
      }

    });
    super.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
          if (RECT_SLIDER.contains(e.getPoint())) {
            sliding = true;
            setSlider(e.getX());
            repaint();
            if (e.getClickCount() > 1) {
              fireColorChanged(new ColorChangedEvent(ColorPanel.this, value));
              ColorPanel.this.setVisible(false);
            }
          }
        }
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
          mousePosition = e.getPoint();
          if (sliding) {
            sliding = false;
            setSlider(e.getX());
            repaint();
            return;
          }
          if (MORE_BUTTON.contains(mousePosition)) {
            showingDialog = true;
            Color result = JColorChooser.showDialog(ColorPanel.this,
                "Select Color.", value);
            showingDialog = false;

            if (result != null) {
              setBaseColor(result);
            }
            fireColorChanged(new ColorChangedEvent(ColorPanel.this, value));
            ColorPanel.this.setVisible(false);
          }
          for (ColorBox box : colorBoxes) {
            if (box.getBounds().contains(mousePosition)) {
              setBaseColor(box.getColor());
              fireColorChanged(new ColorChangedEvent(ColorPanel.this, value));
              ColorPanel.this.setVisible(false);
            }
          }
        }
      }
    });

  }

  /**
   * Given an RGB color, this keeps the existing alpha, but updates the
   * RGB color to the new color.
   * @param color The color to choose.
   */
  private void setBaseColor(Color color){
    int r = color.getRed();
    int g = color.getGreen();
    int b = color.getBlue();
    int alpha = value.getAlpha();
    value = new Color(r, g, b, alpha);

  }


  //<editor-fold defaultstate="collapsed" desc="ColorChangedEvent">
  /**
   * The listener list.
   */
  private final List<ColorChangedEvent.Listener> colorChangedListeners
      = new ArrayList<>();

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
  public final void fireColorChanged(ColorChangedEvent e) {
    for (ColorChangedEvent.Listener listener : colorChangedListeners) {
      listener.colorChanged(e);
    }
  }

  //</editor-fold>
  /**
   * Sets the slider based on the current x location.
   *
   * @param x The x position.
   */
  private void setSlider(int x) {
    int alpha;
    int gripWidth = 0;
    if (grip != null) {
      gripWidth = grip.getWidth();
    }
    if (x < RECT_SLIDER.x) {
      alpha = 0;
    } else if (x > RECT_SLIDER.x + RECT_SLIDER.width) {
      alpha = MAX_ALPHA;
    } else {
      alpha = ((x - RECT_SLIDER.x) * MAX_ALPHA) / RECT_SLIDER.width;
    }
    int r = value.getRed();
    int g = value.getGreen();
    int b = value.getBlue();
    value = new Color(r, g, b, alpha);
  }

  /**
   * Creates the actual boxes that will control hit testing and colors.
   */
  private void createBoxes() {
    // top row
    for (int col = 0; col < NUM_COLUMNS; col++) {
      int x = LEFT_OFFSET + col * (BOX_SIZE + PADDING);
      Rectangle bounds = new Rectangle(x, TOP_ROW_Y, BOX_SIZE, BOX_SIZE);
      addBox(bounds);
    }

    // middle boxes
    for (int row = 0; row < NUM_MIDDLE_ROWS; row++) {
      for (int col = 0; col < NUM_COLUMNS; col++) {
        int x = LEFT_OFFSET + col * (BOX_SIZE + PADDING);
        int y = MIDDLE_Y + row * (BOX_SIZE-1);
        Rectangle bounds = new Rectangle(x, y, BOX_SIZE, BOX_SIZE);
        addBox(bounds);
      }
    }

    // bottom boxes
    for (int col = 0; col < NUM_COLUMNS; col++) {
      int x = LEFT_OFFSET + col * (BOX_SIZE + PADDING);
      Rectangle bounds = new Rectangle(x, BOTTOM_Y, BOX_SIZE, BOX_SIZE);
      addBox(bounds);
    }
  }

  /**
   * Adds a single box with the specified rectangle, reading the color for the
   * box by looking at the central point in the box and reading the color.
   *
   * @param bounds The rectangle bounds.
   */
  private void addBox(Rectangle bounds) {
    Color color = Color.GRAY;
    if (background != null) {
      Point center = new Point(bounds.x + BOX_SIZE / 2, bounds.y + BOX_SIZE / 2);
      color = new Color(background.getRGB(center.x, center.y));
    }
    ColorBox box = new ColorBox(bounds, color);
    colorBoxes.add(box);
  }

  /**
   * Draws the background colors and any appropriate selection rectangle.
   *
   * @param g The graphics icon.
   */
  @Override
  protected final void paintComponent(Graphics g) {


    if (background != null) {
      g.drawImage(background, 0, 0, null);
    }
    if (g instanceof Graphics2D) {
      Graphics2D g2 = (Graphics2D)g;
      paintTransparencySlider(g2);
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                          RenderingHints.VALUE_ANTIALIAS_ON);
    }

    if (mousePosition != null) {
      for (ColorBox box : colorBoxes) {
        if (box.getBounds().contains(mousePosition)) {
          g.setColor(SELECT_COLOR);
          Rectangle r = box.getBounds();
          int x = r.x - 1;
          int y = r.y - 1;
          int w = r.width + 1;
          int h = r.height + 1;

          g.drawRect(x, y, w, h);
          g.setColor(Color.WHITE);
          g.drawRect(r.x, r.y, r.width-1, r.height-1);
          break;
        }
      }
      if (MORE_BUTTON.contains(mousePosition)) {
        g.setColor(Color.GRAY);
        g.drawRect(MORE_BUTTON.x, MORE_BUTTON.y, MORE_BUTTON.width, MORE_BUTTON.height);
      }
    }

  }

  /**
   * Paints the transparency slider.
   *
   * @param g
   */
  public final void paintTransparencySlider(Graphics2D g2) {
    // draw transparency slider

    int alpha = MAX_ALPHA;
    if (value != null) {
      alpha = value.getAlpha();
    }
    int r = value.getRed();
    int g = value.getGreen();
    int b = value.getBlue();
    Color solid = new Color(r, g, b);
    Color transparent = new Color(r, g, b, 0);
    int top = RECT_SLIDER.y;
    int left = RECT_SLIDER.x;
    int right = RECT_SLIDER.x + RECT_SLIDER.width;
    g2.setPaint(new GradientPaint(left, top, transparent, right, top, solid));
    g2.fillRect(left, top, RECT_SLIDER.width, RECT_SLIDER.height);
    int gripWidth = 0;
    if (grip != null) {
      gripWidth = grip.getWidth();
    }
    int x = RECT_SLIDER.x + (alpha * (RECT_SLIDER.width - gripWidth / 2)) / 255;
    if (grip != null) {
      g2.drawImage(grip, x, GRIP_Y, null);
    }
  }

  /**
   * Gets the Color value that is ultimately selected.
   *
   * @return the value
   */
  public final Color getValue() {
    return value;
  }

  /**
   * Sets the Color value that is ultimately selected.
   *
   * @param value the value to set
   */
  public final void setValue(Color value) {
    this.value = value;
  }

  /**
   * Gets a boolean, true if the dialog is showing.
   *
   * @return the showingDialog
   */
  public final boolean isShowingDialog() {
    return showingDialog;
  }

}
