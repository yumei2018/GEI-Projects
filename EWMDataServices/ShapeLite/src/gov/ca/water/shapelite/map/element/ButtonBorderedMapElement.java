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
package gov.ca.water.shapelite.map.element;

import gov.ca.water.shapelite.events.MapEventMouse;
import gov.ca.water.shapelite.map.MapButtonListener;
import gov.ca.water.shapelite.map.MapPaintArgs;
import gov.ca.water.shapelite.map.ShadowRoundedRectangle;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Arrays;
import javax.swing.ToolTipManager;

/**
 * This creates a button with an image that can be clicked and is shown over the
 * map. It does not use the standard Control rendering system, however, so it is
 * possible for the button to be any shape, or partly transparent or even
 * translucent.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ButtonBorderedMapElement extends MapElement {

  /**
   * The default border width around the image.
   */
  private static final int DEFAULT_BORDER_WIDTH = 4;

  /**
   * The delay in milliseconds between a mouse enter and showing a tool tip.
   */
  private static final int TOOL_TIP_SHOW_DELAY = 200;

  /**
   * Show tool tips for 3 seconds over button.
   */
  private static final int TOOL_TIP_DISMISS_DELAY = 3000;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The image that is normally drawn on the button.
   */
  private Image image;

  /**
   * A boolean that is true if the cursor is over the button.
   */
  private boolean lit;

  /**
   * The image that is shown if the mouse is over the button.
   */
  private Image litImage;

  /**
   * A boolean that is true if the button is being pressed, or has been toggled.
   */
  private boolean pressed;

  /*
   * The image that is shown when the mouse is pressing the button with a left click.
   */
  private Image pressedImage;

  /**
   * A boolean that is true if the this button should stay pressed once clicked.
   */
  private boolean supportsToggle;

  /**
   * A boolean that is true if the button supports toggling and has been
   * pressed.
   */
  private boolean toggledOn;

  /**
   * A boolean that is true if the tool tip is showing.
   */
  private boolean toolTipShowing;

  /**
   * The string text to display as a tool tip as the mouse hovers over the
   * button.
   */
  private String toolTipText;

  /**
   * Gets the integer width of the border.
   */
  private int borderWidth;

  //</editor-fold>
  /**
   * Creates a new instance of the MapElementButton class.
   */
  public ButtonBorderedMapElement() {
    super.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    borderWidth = DEFAULT_BORDER_WIDTH;
  }

  //<editor-fold defaultstate="collapsed" desc="Events">
  /**
   * Adds a listener for the button clicked event. Duplicates will not be added.
   *
   * @param listener The MapButtonListener implementation to add.
   */
  public final void addMapButtonListener(MapButtonListener listener) {
    if (!Arrays.asList(getListenerList().getListeners(MapButtonListener.class))
        .contains(listener)) {
      getListenerList().add(MapButtonListener.class, listener);
    }
  }

  /**
   * Removes the specified listener from the list of listeners.
   *
   * @param listener The listener to remove.
   */
  public final void removeMapButtonListener(MapButtonListener listener) {
    getListenerList().remove(MapButtonListener.class, listener);
  }

  /**
   * Fires the MapButtonClicked event.
   *
   * @param e A MapEventMouse args which has information about the mouse and the
   * Map control containing the button.
   */
  public final void fireMapButtonClicked(MapEventMouse e) {
    MapButtonListener[] listenerSet = getListenerList().getListeners(MapButtonListener.class);
    for (MapButtonListener listener : listenerSet) {
      listener.buttonClicked(e);
    }
  }

  //</editor-fold>
  /**
   * Occurs when the button is clicked. If toggle is supported, it will reverse
   * the toggledOn status.
   *
   * @param e The MapEventMouse argument for the click event.
   */
  public final void buttonClicked(MapEventMouse e) {
    fireMapButtonClicked(e);
  }

  /**
   * Occurs when the mouse moves. This controls the tool-tip handling and
   * toggling from lit to un-lit. This calls the superclass mouseMoved to handle
   * the mouse cursor changing if necessary.
   *
   * @param e The MapEventMouse arguments containing information about the mouse
   * states and the map that this tool belongs to.
   */
  @Override
  public final void mouseMoved(MapEventMouse e) {
    if (this.getBounds().contains(e.getPoint())) {
      if (!lit) {
        lit = true;
        e.getMap().repaint(this.getBounds());
      }
      if (!toolTipShowing) {
        ToolTipManager.sharedInstance().registerComponent(e.getMap());
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(TOOL_TIP_DISMISS_DELAY);
      }
      e.getMap().setToolTipText(toolTipText);
      toolTipShowing = true;
    } else {
      if (lit) {
        lit = false;
        e.getMap().repaint(this.getBounds());
      }
      if (toolTipShowing) {
        ToolTipManager.sharedInstance().setDismissDelay(0);
        e.getMap().setToolTipText(null);
        ToolTipManager.sharedInstance().setInitialDelay(TOOL_TIP_SHOW_DELAY);
        ToolTipManager.sharedInstance().unregisterComponent(e.getMap());
        toolTipShowing = false;
      } else {
        ToolTipManager.sharedInstance().setDismissDelay(TOOL_TIP_DISMISS_DELAY);
      }
    }
    super.mouseMoved(e);
  }

  /**
   * This always simply toggles the pressed state to be true so that the pressed
   * icon will be displayed.
   *
   * @param e The MapEventMouse arguments containing information about the mouse
   * states and the map that this tool belongs to.
   */
  @Override
  public final void mousePressed(MapEventMouse e) {
    if (this.intersects(e.getPoint()) && e.getMap().getActiveElement() == this) {
      this.pressed = true;
      e.getMap().repaint(getBounds());
    }
  }

  /**
   * This handles the mouse release differently depending on whether
   * supportsToggle is true. If it supports toggling, then it inverts the toggle
   * state. Otherwise it simply alters the pressed condition to false.
   *
   * @param e The MapEventMouse arguments containing information about the mouse
   * states and the map that this tool belongs to.
   */
  @Override
  public final void mouseReleased(MapEventMouse e) {
    if (this.intersects(e.getPoint())
        && e.getMap().getActiveElement() == this && pressed) {
      this.pressed = false;
      if (this.supportsToggle) {
        toggledOn = !toggledOn;
      }
      buttonClicked(e);
      e.getMap().repaint(getBounds());
    }
  }

  /**
   * This is where the button renders the graphics for itself. The paint method
   * is called after "paintContent" has been called for all tools.
   *
   * @param args the MapPaintArgs event object that contains information about
   * the drawing canvas as well as the map that is being rendered.
   */
  @Override
  public final void paint(MapPaintArgs args) {
    // all buttons surround their icon with a bit of a rounded rectangle.
    Graphics2D g = args.getGraphics();
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    Rectangle r = this.getBounds();
    ShadowRoundedRectangle rr = new ShadowRoundedRectangle();
    rr.setBounds(r);
    rr.paint(g);
    // Pressed is only true as the button is held down, even if it is a toggle button.
    if (pressed && pressedImage != null) {
      g.drawImage(pressedImage, r.x + borderWidth, r.y + borderWidth, null);
    } else if (lit && litImage != null) {
      // lighting out ranks toggling, but not pressing.
      g.drawImage(litImage, r.x + borderWidth, r.y + borderWidth, null);
    } else if (toggledOn && pressedImage != null) {
      // the pressed image is shown during toggling.
      g.drawImage(pressedImage, r.x + borderWidth, r.y + borderWidth, null);
    } else {
      g.drawImage(image, r.x + borderWidth, r.y + borderWidth, null);
    }
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
   * Gets the image that is normally drawn on the button.
   *
   * @return the image
   */
  public final Image getImage() {
    return image;
  }

  /**
   * Sets the image that is normally drawn on the button.
   *
   * @param image the image to set
   */
  public final void setImage(Image image) {
    this.image = image;
  }

  /**
   * Gets a boolean that is true if the cursor is over the button.
   *
   * @return the lit
   */
  public final boolean isLit() {
    return lit;
  }

  /**
   * Sets a boolean that is true if the cursor is over the button.
   *
   * @param lit the lit to set
   */
  public final void setLit(boolean lit) {
    this.lit = lit;
  }

  /**
   * Gets the image that is shown if the mouse is over the button.
   *
   * @return the litImage
   */
  public final Image getLitImage() {
    return litImage;
  }

  /**
   * Sets the image that is shown if the mouse is over the button.
   *
   * @param litImage the litImage to set
   */
  public final void setLitImage(Image litImage) {
    this.litImage = litImage;
  }

  /**
   * Gets a boolean that is true if the button is being pressed, or has been
   * toggled.
   *
   * @return the pressed
   */
  public final boolean isPressed() {
    return pressed;
  }

  /**
   * Sets a boolean that is true if the button is being pressed, or has been
   * toggled.
   *
   * @param pressed the pressed to set
   */
  public final void setPressed(boolean pressed) {
    this.pressed = pressed;
  }

  /**
   * Gets the image that is shown when the mouse is pressing the button with a
   * left click.
   *
   * @return the pressedImage
   */
  public final Image getPressedImage() {
    return pressedImage;
  }

  /**
   * Sets the image that is shown when the mouse is pressing the button with a
   * left click.
   *
   * @param pressedImage the pressedImage to set
   */
  public final void setPressedImage(Image pressedImage) {
    this.pressedImage = pressedImage;
  }

  /**
   * Gets a boolean that is true if the this button should stay pressed once
   * clicked.
   *
   * @return the supportsToggle
   */
  public final boolean getSupportsToggle() {
    return supportsToggle;
  }

  /**
   * Sets a boolean that is true if the this button should stay pressed once
   * clicked.
   *
   * @param toggle the boolean that is true if this supports toggle.
   */
  public final void setSupportsToggle(boolean toggle) {
    this.supportsToggle = toggle;
  }

  /**
   * Gets a boolean that is true if the button supports toggling and has been
   * pressed.
   *
   * @return the toggledOn
   */
  public final boolean isToggledOn() {
    return toggledOn;
  }

  /**
   * Sets a boolean that is true if the button supports toggling and has been
   * pressed.
   *
   * @param toggledOn the toggledOn to set
   */
  public final void setToggledOn(boolean toggledOn) {
    this.toggledOn = toggledOn;
  }

  /**
   * Gets the string text to display as a tool tip as the mouse hovers over the
   * button.
   *
   * @return the toolTipText
   */
  public final String getToolTipText() {
    return toolTipText;
  }

  /**
   * Sets the string text to display as a tool tip as the mouse hovers over the
   * button.
   *
   * @param toolTipText the toolTipText to set
   */
  public final void setToolTipText(String toolTipText) {
    this.toolTipText = toolTipText;
  }

  /**
   * @return the borderWidth
   */
  public final int getBorderWidth() {
    return borderWidth;
  }

  /**
   * @param borderWidth the borderWidth to set
   */
  public final void setBorderWidth(int borderWidth) {
    this.borderWidth = borderWidth;
  }

  //</editor-fold>
}
