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
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.map.Images;
import gov.ca.water.shapelite.map.MapPaintArgs;
import gov.ca.water.shapelite.map.ShadowEllipse;
import java.awt.Rectangle;

/**
 * This map element shows directional buttons that allow the user to pan using a
 * click of the mouse.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PanButtonMapElement extends MapElement {

  /**
   * The default bounds for this element.
   */
  private static final Rectangle DEFAULT_BOUNDS = new Rectangle(25, 25, 50, 50);

  /**
   * The padding in pixels between the outside of the button and the image.
   */
  private static final int PADDING = 4;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The image that is used for all four pan buttons.
   */
  private Image image;

  /**
   * The X coordinate of the center of this element in pixel coordinates of the
   * client rectangle of the map.
   */
  private double centerX;

  /**
   * THe Y coordinate of the center of this element in pixel coordinates of the
   * client rectangle of the map.
   */
  private double centerY;

  /**
   * The horizontal difference between the clicked point and the center X
   * coordinate.
   */
  private double differenceX;

  /**
   * The vertical difference between the clicked point and the center Y
   * coordinate.
   */
  private double differenceY;

  /**
   * The directions image.
   */
  private final Image directions;

  //</editor-fold>
  /**
   * Creates a new instance of the map buttons.
   */
  public PanButtonMapElement() {
    super.setBoundsFrom(DEFAULT_BOUNDS);
    this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    directions = Images.getInstance().directions().orElse(null);

  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Override the default "bound box" test with a test sensitive to the colored
   * circle.
   *
   * @param location The Point being tested for intersectng with this element.
   * @return Boolean, true if this intersects with the current button.
   */
  @Override
  public final boolean intersects(Point location) {
    return Math.sqrt(differenceX * differenceX + differenceY * differenceY)
        < this.getBounds().width / 2;
  }

  /**
   * Tests whether the point is closest to the "UP" portion of the button.
   *
   * @param location The point location to test.
   * @return boolean, true if the point is on the left button.
   */
  public final boolean isUp(Point location) {
    return (location.y < centerY && differenceY > differenceX);
  }

  /**
   * Tests whether the point is closest to the "Down" portion of the button.
   *
   * @param location The point location to test.
   * @return boolean, true if the point is on the left button.
   */
  public final boolean isDown(Point location) {
    return (location.y > centerY && differenceY > differenceX);
  }

  /**
   * Tests whether the point is closest to the left portion of the button.
   *
   * @param location The point location to test.
   * @return boolean, true if the point is on the left button.
   */
  public final boolean isLeft(Point location) {
    return (location.x < centerX && differenceX > differenceY);
  }

  /**
   * Tests whether the point is closest to the right portion of the button.
   *
   * @param location The point location to test.
   * @return boolean, true if the point is on the left button.
   */
  public final boolean isRight(Point location) {
    return (location.x > centerX && differenceX > differenceY);
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
    centerX = this.getBounds().x + this.getBounds().width / 2.0;
    centerY = this.getBounds().y + this.getBounds().height / 2.0;
    differenceX = Math.abs(e.getPoint().x - centerX);
    differenceY = Math.abs(e.getPoint().y - centerY);
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
    }
    super.mousePressed(e);
  }

  /**
   * The mouse is released.
   *
   * @param e The MapEventMouse event arguments contain information about the
   * mouse condition and the map.
   */
  @Override
  public final void mouseReleased(MapEventMouse e) {
    if (intersects(e.getPoint())) {
      e.setHandled(true);
    }
    if (e.getMap().getActiveElement() != this) {
      return;
    }
    if (intersects(e.getPoint())) {
      Envelope env = e.getMap().getContent().getEnvelopeMercator();
      double h = env.getHeight() / 3;
      double w = env.getWidth() / 3;
      double top = env.getMax().getY();
      double bottom = env.getMin().getY();
      double left = env.getMin().getX();
      double right = env.getMax().getX();
      if (isUp(e.getPoint())) {
        env.getMax().setY(top + h);
        env.getMin().setY(bottom + h);
      } else if (isDown(e.getPoint())) {
        env.getMax().setY(top - h);
        env.getMin().setY(bottom - h);
      } else if (isLeft(e.getPoint())) {
        env.getMax().setX(right - w);
        env.getMin().setX(left - w);
      } else if (isRight(e.getPoint())) {
        env.getMax().setX(right + w);
        env.getMin().setX(left + w);

      }

      e.getMap().getContent().setEnvelope(env);
      e.getMap().getContent().paintImmediately();
      e.getMap().repaint();
      e.setHandled(true);
    }
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
    ShadowEllipse ellipse = new ShadowEllipse();
    ellipse.setBounds(this.getBounds());
    ellipse.paint(g);
    int x = this.getBounds().x;
    int y = this.getBounds().y;
    if (directions != null) {
      g.drawImage(directions, x + PADDING, y + PADDING, null);
    }

  }

  @Override
  protected void onBoundsSet(Rectangle bounds) {
    boolean stop = true;
  }



  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the image that is used for all four pan buttons.
   *
   * @return the image
   */
  public final Image getImage() {
    return image;
  }

  /**
   * Sets the image that is used for all four pan buttons.
   *
   * @param image the image to set
   */
  public final void setImage(Image image) {
    this.image = image;
  }

  //</editor-fold>
}
