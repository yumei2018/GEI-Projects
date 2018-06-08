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

import gov.ca.water.shapelite.events.MapEventComponent;
import gov.ca.water.shapelite.map.maptool.MapTool;
import gov.ca.water.shapelite.events.MapEventMouse;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.event.EventListenerList;

/**
 * Elements not only react to mouse actions, but they have a physical presence
 * on the map.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public abstract class MapElement extends MapTool {

  /**
   * A default rectangle for the rectangle if one is not specified.
   */
  private static final Rectangle DEFAULT_BOUNDS = new Rectangle(50, 50, 40, 40);

  /**
   * The default padding to use.
   */
  private static final int DEFAULT_PADDING = 10;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The rectangular bounds in pixels and client coordinates representing the
   * position of the element on the map.
   */
  private final Rectangle bounds;

  /**
   * The cursor displayed on the map control. This cursor is only active so long
   * as the cursor intersects with this element.
   */
  private Cursor cursor;

  /**
   * The list of event listeners in case there is an event thrown by the
   * element.
   */
  private final EventListenerList listenerList;

  /**
   * A boolean that, if true indicates that the item should be on the right.
   */
  private AnchorDirection anchor;

  /**
   * The spacing in pixels between this element and the right edge of the map if
   * the anchor is set to Right.
   */
  private int rightPadding;

  /**
   * The spacing in pixels between this element and the bottom edge of the map
   * if anchor uses Bottom.
   */
  private int bottomPadding;

  //</editor-fold>
  /**
   * Creates a new instance of the map element.
   */
  public MapElement() {
    super();
    listenerList = new EventListenerList();
    bounds = new Rectangle(DEFAULT_BOUNDS);
    rightPadding = DEFAULT_PADDING;
    bottomPadding = DEFAULT_PADDING;
    anchor = AnchorDirection.TopLeft;
  }

  /**
   * Handles the map resize to keep the element anchored right.
   *
   * @param e
   */
  @Override
  public final void componentResized(MapEventComponent e) {
    if (anchor == AnchorDirection.BottomRight || anchor == AnchorDirection.TopRight) {
      getBounds().x = e.getMap().getWidth() - getBounds().width - rightPadding;
    }
    if (anchor == AnchorDirection.BottomLeft || anchor == AnchorDirection.BottomRight) {
      getBounds().y = e.getMap().getHeight() - getBounds().height - bottomPadding;
    }
    onComponentResized(e);
  }

  /**
   * Occurs after the default anchor component resize handling.
   *
   * @param e The MapEventComponent.
   */
  protected void onComponentResized(MapEventComponent e) {

  }

  /**
   * This should only be true if the cursor is over the interactive portion of
   * the element. This checks the bounds rectangle, so it is an approximation.
   *
   * @param pt The Point to test for intersection.
   * @return True if the point intersects with this element.
   */
  abstract boolean intersects(Point pt);

  /**
   * As a general rule, this mouseMove should still be called by super classes
   * to ensure that the cursor behavior is working correctly.
   *
   * @param e
   */
  @Override
  public void mouseMoved(MapEventMouse e) {
    /**
     * The superclass handles elements that may be "contained" by this element.
     * If the event is handled by a child element, then we don't do anything
     * here.
     */
    super.mouseMoved(e);
    if (!e.getMap().isCursorLocked()) {
      if (e.getMap().getActiveElement() != null) {
        if (e.getMap().getActiveElement() != this) {
          if (!e.getMap().getActiveElement().intersects(e.getPoint())) {
            if (intersects(e.getPoint())) {
              e.getMap().setActiveElement(this);
            } else {
              e.getMap().setActiveElement(null);
            }
          } else if (intersects(e.getPoint())) {
            // parent element may also intersect, but since the child element
            // gets it first, it is the responsibility of the child element
            // to "handle" the event.
            e.getMap().setActiveElement(this);
            if (cursor != null) {
              e.setCursor(cursor);
            }
            e.setHandled(true);
          }
        }
      }
      if (intersects(e.getPoint())) {
        e.getMap().setActiveElement(this);
        if (cursor != null) {
          e.setCursor(cursor);
        }
        e.setHandled(true);
      }

    }

  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the rectangular bounds in pixels and client coordinates representing
   * the position of the element on the map.
   *
   * @return the bounds
   */
  public final Rectangle getBounds() {
    return bounds;
  }

  /**
   * Sets the rectangular bounds in pixels and client coordinates representing
   * the position of the element on the map.
   *
   * @param bounds the bounds to set
   */
  public final void setBoundsFrom(Rectangle bounds) {
    this.bounds.x = bounds.x;
    this.bounds.y = bounds.y;
    this.bounds.width = bounds.width;
    this.bounds.height = bounds.height;
    onBoundsSet(this.bounds);
  }

  /**
   * Occurs after the new bounds are set.
   *
   * @param bounds The new bounds being set.
   */
  protected void onBoundsSet(Rectangle bounds) {

  }

  /**
   * Gets the cursor displayed on the map control. This cursor is only active so
   * long as the cursor intersects with this element.
   *
   * @return the cursor
   */
  public final Cursor getCursor() {
    return cursor;
  }

  /**
   * Sets the cursor displayed on the map control. This cursor is only active so
   * long as the cursor intersects with this element.
   *
   * @param cursor the cursor to set
   */
  public final void setCursor(Cursor cursor) {
    this.cursor = cursor;
  }

  /**
   * Gets the listener list for events on the map element.
   *
   * @return the listenerList
   */
  public final EventListenerList getListenerList() {
    return listenerList;
  }

  /**
   * @return the anchor
   */
  public final AnchorDirection getAnchor() {
    return anchor;
  }

  /**
   * @param anchor the anchor to set
   */
  public final void setAnchor(AnchorDirection anchor) {
    this.anchor = anchor;
  }

  //</editor-fold>
  /**
   * Gets the spacing in pixels between this element and the right edge of the
   * map if the anchor is set to Right.
   *
   * @return the rightPadding
   */
  public final int getRightPadding() {
    return rightPadding;
  }

  /**
   * Sets the spacing in pixels between this element and the right edge of the
   * map if the anchor is set to Right.
   *
   * @param rightPadding the rightPadding to set
   */
  public final void setRightPadding(int rightPadding) {
    this.rightPadding = rightPadding;
  }

  /**
   * @return the bottomPadding
   */
  public final int getBottomPadding() {
    return bottomPadding;
  }

  /**
   * @param bottomPadding the bottomPadding to set
   */
  public final void setBottomPadding(int bottomPadding) {
    this.bottomPadding = bottomPadding;
  }
}
