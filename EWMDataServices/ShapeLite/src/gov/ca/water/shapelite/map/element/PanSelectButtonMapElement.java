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

import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.events.MapEventMouse;
import gov.ca.water.shapelite.map.Images;
import gov.ca.water.shapelite.map.MapPaintArgs;
import gov.ca.water.shapelite.map.ShadowRoundedRectangle;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.logging.Logger;

/**
 * This map element shows directional buttons that allow the user to pan using a
 * click of the mouse.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PanSelectButtonMapElement extends MapElement {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The satelliteImage that is used for all four pan buttons.
   */
  private Image panImage;
  /**
   * The select image.
   */
  private Image selectImage;
  /**
   * Boolean that is true if the button is pressed.
   */
  private boolean isPressed;
  /**
   * Boolean that is true if the button is in select mode.
   */
  private boolean isSelectMode;

  /**
   * The logger utility for this class.
   */
  private static final Logger LOGGER
      = Logger.getLogger(PanSelectButtonMapElement.class.getName());

  //</editor-fold>
  /**
   * Creates a new instance of the map buttons.
   */
  public PanSelectButtonMapElement() {
    this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    Optional<Image> testPan = Images.getInstance().get("Pan32.png");
    Optional<Image> testSelect = Images.getInstance().get("Select32.png");
    // these are just the default images, so don't crash if they are not found.
    this.panImage = testPan.orElse(null);
    this.selectImage = testSelect.orElse(null);

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

  /**
   * Handles the mouseDragged event on this button.
   *
   * @param e A MapEventMouse argument which includes information about the
   * mouse as well as the Map control itself.
   */
  @Override
  public final void mouseDragged(MapEventMouse e) {
    if (this.getBounds().contains(e.getPoint())) {
      e.setHandled(true);
      return;
    }
    super.mouseDragged(e);
  }

  /**
   * Handles the mouseMoved event on this button. This is mostly used to update
   * the centerX, centerY, differenceX and differenceY
   *
   * @param e A MapEventMouse argument which includes information about the
   * mouse as well as the Map control itself.
   */
  @Override
  public final void mouseMoved(MapEventMouse e) {
    super.mouseMoved(e);
  }

  /**
   * Handles the mousePressed event to determine if the button intersects with
   * the point being pressed. If true, then the event is considered handled.
   *
   * @param e The MapEventMouse event arguments contain information about the
   * mouse condition and the map.
   */
  @Override
  public final void mousePressed(MapEventMouse e) {
    if (intersects(e.getPoint())) {
      e.setHandled(true);
      isPressed = true;
    }
    super.mousePressed(e); //To change body of generated methods, choose Tools | Templates.
  }

  /**
   * The mouse is released.
   *
   * @param e The MapEventMouse event arguments contain information about the
   * mouse condition and the map.
   */
  @Override
  public final void mouseReleased(MapEventMouse e) {
    boolean ok = true;
    if (!intersects(e.getPoint())) {
      ok = false;
    }
    if (e.getMap().getActiveElement() != this) {
      ok = false;
    }
    if (!isPressed) {
      ok = false;
    }
    if (!intersects(e.getPoint())) {
      ok = false;
    }

    if (ok) {
      e.setHandled(true); // whether this is the active element or not, we are over this tool, so don't do selection.
      isPressed = false;
      this.isSelectMode = !this.isSelectMode;

      if (isSelectMode) {
        e.getMap().getToolbox().enableMapTool("select");
        e.getMap().getToolbox().disableMapTool("pan");
      } else {
        e.getMap().getToolbox().enableMapTool("pan");
        e.getMap().getToolbox().disableMapTool("select");
      }
      e.getMap().getContent().paintImmediately();
      e.getMap().repaint();
    }
    super.mouseReleased(e);

  }

  /**
   * Paints the button along with shadowing.
   *
   * @param args The MapPaintArgs containing information about the map and the
   * drawing.
   */
  @Override
  public final void paint(MapPaintArgs args) {
    Graphics2D g = args.getGraphics();
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
    ShadowRoundedRectangle rect = new ShadowRoundedRectangle();
    rect.setBounds(this.getBounds());
    rect.paint(g);
    int x = this.getBounds().x;
    int y = this.getBounds().y;
    // the image is reversed from the state on the map
    Image image = this.selectImage;
    if (isSelectMode) {
      image = panImage;
    }
    g.drawImage(image, x + 4, y + 4, null);
  }


  //<editor-fold defaultstate="collapsed" desc="Properties">

  /**
   * @return the panImage
   */
  public final Image getPanImage() {
    return panImage;
  }

  /**
   * @param panImage the panImage to set
   */
  public final void setPanImage(Image panImage) {
    this.panImage = panImage;
  }

  /**
   * @return the selectImage
   */
  public final Image getSelectImage() {
    return selectImage;
  }

  /**
   * @param selectImage the selectImage to set
   */
  public final void setSelectImage(Image selectImage) {
    this.selectImage = selectImage;
  }

  /**
   * @return the isPressed
   */
  public final boolean isIsPressed() {
    return isPressed;
  }

  /**
   * @param isPressed the isPressed to set
   */
  public final void setIsPressed(boolean isPressed) {
    this.isPressed = isPressed;
  }

  /**
   * @return the isSelectMode
   */
  public final boolean isIsSelectMode() {
    return isSelectMode;
  }

  /**
   * @param isSelectMode the isSelectMode to set
   */
  public final void setSelectMode(boolean isSelectMode) {
    this.isSelectMode = isSelectMode;
  }

  //</editor-fold>

}
