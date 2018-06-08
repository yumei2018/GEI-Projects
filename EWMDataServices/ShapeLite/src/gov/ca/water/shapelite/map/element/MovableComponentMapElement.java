/*
 * The MIT License
 *
 * Copyright 2014 hdunsford.
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

import gov.ca.water.shapelite.events.MapElementClosedEvent;
import gov.ca.water.shapelite.events.MapEventMouse;
import gov.ca.water.shapelite.map.Images;
import gov.ca.water.shapelite.map.MapPaintArgs;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.ShadowRoundedRectangle;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 * This hosts an experience within the map that acts more like a movable window.
 * When a mouse down event is registered, this element will adjust it's
 * position. Also, this element features a close icon that if clicked should
 * make the icon vanish.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MovableComponentMapElement extends MapElement {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * When drawn as a draggable element in the map, this is the size of the
   * border surrounding the actual component.
   */
  public static final int BORDER = 4;

  /**
   * The height of the top part of the border, which is larger in order to
   * support the close button.
   */
  public static final int TOP_BORDER = 16;

  /**
   * The distance in pixels that the mouse can be from an edge and still trigger
   * resize behavior.
   */
  private static final int RESIZE_MARGIN = 10;

  /**
   * The default bounds that should probably not be used, but is used if not
   * other bounds are specified.
   */
  private static final Rectangle DEFAULT_BOUNDS = new Rectangle(1000, 250, 40, 40);

  private Component component;
  private Point location;
  private boolean mouseOverCancel;
  private boolean mouseOverRightEdge;
  private boolean mouseOverBottomEdge;
  private boolean mouseOverBRCorner;
  private boolean resizingRight;
  private boolean resizingBottom;
  private boolean resizingDiagonal;
  private boolean dragging;
  private Point dragStart;
  private Point originalPosition;
  private boolean anchorRight = false;

  /**
   * The Integer pixel location of the left of this component from the right
   * side of the map if the user has moved the component.
   */
  private Integer right;
  private boolean anchorBottom = false;
  private Integer bottom;

  private Image closeGray = null;

  private Image closeRed = null;

  /**
   * The map this tool is drawn on.
   */
  private MapPanel map;

  /**
   * A boolean that if true will allow the control to be resized.
   */
  private boolean allowResize;
  /**
   * A String title that will appear at the top of the component in the title
   * bar.
   */
  private String title;

  /**
   * The bounds to use when reseting the position.
   */
  private final Rectangle resetBounds;

  //</editor-fold>
  /**
   * Creates a new instance of the MapElementImage class.
   */
  public MovableComponentMapElement() {
    closeRed = Images.getInstance().get("CloseRed.png").orElse(null);
    closeGray = Images.getInstance().get("CloseGray.png").orElse(null);
    resetBounds = new Rectangle(DEFAULT_BOUNDS);
    allowResize = true;
  }

  //<editor-fold defaultstate="collapsed" desc="MapElementClosedEvent">
  /**
   * The listener list.
   */
  private final List<MapElementClosedEvent.Listener> mapElementClosedListeners = new ArrayList<>();

  /**
   * Adds the specified listener to the list of listeners to be notified during
   * an event. If the item is already in the list, it will not be added a second
   * time.
   *
   * @param listener The MapElementClosedEvent.Listener to connect.
   */
  public final void addMapElementClosedListener(
      MapElementClosedEvent.Listener listener) {
    if (!mapElementClosedListeners.contains(listener)) {
      mapElementClosedListeners.add(listener);
    }
  }

  /**
   * Removes the specified listener from the list if it is in the list.
   *
   * @param listener The MapElementClosedEvent.Listener to disconnect.
   */
  public final void removeMapElementClosedListener(
      MapElementClosedEvent.Listener listener) {
    mapElementClosedListeners.remove(listener);
  }

  /**
   * Fires the MapElementClosed event and notifies each of the listeners.
   *
   * @param e A {EventType}Event with the source object and any properties
   * associated with this event.
   */
  public final void fireMapElementClosed(MapElementClosedEvent e) {
    for (MapElementClosedEvent.Listener listener : mapElementClosedListeners) {
      listener.mapElementClosed(e);
    }
  }

  //</editor-fold>
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
   * Paints the movable control in the context of the entire map screen.
   *
   * @param args The MapPaintArguments.
   */
  @Override
  public final void paint(MapPaintArgs args) {
    Graphics2D g = args.getGraphics();
    int x = this.getBounds().x;
    int y = this.getBounds().y;
    paint(g, x, y);
  }

  /**
   * Occurs during a layout action from the map. This happens before any
   * painting occurs and gives elements the chance to position themselves
   * correctly, which is important if they use java controls positioned on top
   * of the map.
   *
   * @param map The map panel to update.
   */
  @Override
  protected final void onDoLayout(MapPanel map) {
    updatePosition(map);
  }

  /**
   * Based on the size of the map panel, this will update the position of
   * various components before any resize takes place.
   *
   * @param map The amp to update.
   */
  private void updatePosition(MapPanel map) {

    if (map.getWidth() > 0 && map.getHeight() > 0) {
      if (anchorRight) {
        int mapWidth = map.getWidth();
        int x;
        if (right != null) {
          x = mapWidth - right;
        } else {
          x = mapWidth - getBounds().width - super.getRightPadding();
        }
        this.getBounds().x = x;
      }
      if (anchorBottom) {
        int mapHeight = map.getHeight();
        int y;
        if (bottom != null) {
          y = mapHeight - bottom;
        } else {
          y = mapHeight - getBounds().height - super.getBottomPadding();
        }
        this.getBounds().y = y;
      }
      this.getComponent().setLocation(this.getBounds().x + BORDER,
          this.getBounds().y + TOP_BORDER);
    }
  }

  /**
   * This will paint the image, but instead of using the normal positioning,
   * this will ensure that the element is drawn with the top left corner at the
   * x and y pixel locations.
   *
   * @param g The graphics object for drawing.
   * @param x The left pixel location
   * @param y the top pixel location
   */
  public void paint(Graphics2D g, int x, int y) {
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    int w = this.getBounds().width;
    int h = this.getBounds().height;
    ShadowRoundedRectangle rect = new ShadowRoundedRectangle();
    rect.setBounds(new Rectangle(x, y, w, h));
    rect.paint(g);

    Rectangle closeRect = new Rectangle(x + w - 17, y + 4, 9, 9);
    if (location != null) {
      if (closeRect.contains(location)) {
        if (closeRed != null) {
          g.drawImage(closeRed, x + w - 17, y + 4, 9, 9, null);
        }
      } else if (closeGray != null) {
        g.drawImage(closeGray, x + w - 17, y + 4, 9, 9, null);
      }
    }
    if (title != null) {
      g.setColor(Color.BLACK);
      g.setFont(new Font("Arial", Font.BOLD, 9));
      g.drawString(title, x + 4, y + 12);
    }
  }



  /**
   * Handles mouse moved.
   *
   * @param e The MapEventMouse event arguments, which extend mouse events with
   * map information.
   */
  @Override
  public void mouseMoved(MapEventMouse e) {
    location = e.getPoint();
    int x = this.getBounds().x;
    int y = this.getBounds().y;
    int w = this.getBounds().width;
    int h = this.getBounds().height;
    int r = x + w;
    int b = y + h;
    Rectangle closeRect = new Rectangle(x + w - 17, y + 4, 9, 9);
    if (closeRect.contains(location) && !mouseOverCancel) {
      e.getMap().paintImmediately(closeRect);
    }
    if (!closeRect.contains(location) && mouseOverCancel) {
      e.getMap().paintImmediately(closeRect);
    }

    if (allowResize) {
      if (Math.abs(location.x - r) < RESIZE_MARGIN
          && Math.abs(location.y - b) < RESIZE_MARGIN
          && !mouseOverBRCorner) {
        e.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        mouseOverBRCorner = true;
      } else if (!mouseOverBRCorner && Math.abs(location.x - r)
          < RESIZE_MARGIN && !mouseOverRightEdge) {
        e.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        mouseOverRightEdge = true;
      } else if (!mouseOverBRCorner && !mouseOverRightEdge
          && Math.abs(location.y - b) < RESIZE_MARGIN && !mouseOverBottomEdge) {
        e.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
        mouseOverBottomEdge = true;
      }
      if (mouseOverBRCorner
          && (Math.abs(location.x - r) > RESIZE_MARGIN
          || Math.abs(location.y - b) > RESIZE_MARGIN)) {
        e.setCursor(Cursor.getDefaultCursor());
        mouseOverBRCorner = false;
      }
      if (mouseOverRightEdge && Math.abs(location.x - r) > RESIZE_MARGIN) {
        e.setCursor(Cursor.getDefaultCursor());
        mouseOverRightEdge = false;
      }
      if (mouseOverBottomEdge && Math.abs(location.y - b) > RESIZE_MARGIN) {
        e.setCursor(Cursor.getDefaultCursor());
        mouseOverBottomEdge = false;
      }
    }
    // handle cursor.
    super.mouseMoved(e);

  }

  /**
   * Forward mouseClicked to child controls.
   *
   * @param e The event arguments.
   */
  @Override
  public final void mouseClicked(MapEventMouse e) {
    location = e.getPoint();

  }

  /**
   * Handles mouse dragged.
   *
   * @param e The MapEventMouse event arguments, which extend mouse events with
   * map information.
   */
  @Override
  public final void mouseDragged(MapEventMouse e) {
    super.mouseDragged(e);
    int minHeight = BORDER + TOP_BORDER;
    int minWidth = BORDER + BORDER;
    if (allowResize) {
      if (resizingDiagonal) {
        int r = e.getPoint().x;
        int b = e.getPoint().y;
        int w = r - this.getBounds().x;
        int h = b - this.getBounds().y;
        if (h < minHeight) {
          h = minHeight;
        }
        if (w < minWidth) {
          w = minWidth;
        }
        this.getBounds().width = w;
        this.getBounds().height = h;
        int componentW = w - BORDER * 2;
        int componentH = h - BORDER * 2;
        component.setSize(componentW, componentH);
        component.repaint();
        e.getMap().paintImmediately(e.getMap().getBounds());
        e.setHandled(true);
      }
      if (resizingRight) {
        int r = e.getPoint().x;
        int w = r - this.getBounds().x;
        if (w < minWidth) {
          w = minWidth;
        }
        this.getBounds().width = w;
        int componentW = w - BORDER * 2;
        component.setSize(componentW, component.getHeight());
        component.repaint();
        e.getMap().paintImmediately(e.getMap().getBounds());
        e.setHandled(true);
      }
      if (resizingBottom) {
        int b = e.getPoint().y;
        int h = b - this.getBounds().y;
        if (h < minHeight) {
          h = minHeight;
        }
        this.getBounds().height = h;
        int componentH = h - BORDER * 2;
        component.setSize(component.getWidth(), componentH);
        component.repaint();
        e.getMap().paintImmediately(e.getMap().getBounds());
        e.setHandled(true);
      }
    }

    if (dragging) {
      this.getBounds().x = originalPosition.x + (e.getX() - dragStart.x);
      this.getBounds().y = originalPosition.y + (e.getY() - dragStart.y);
      component.setLocation(this.getBounds().x + BORDER, this.getBounds().y + TOP_BORDER);
      e.getMap().repaint();
    }

    if (this.getBounds().contains(e.getPoint())) {
      e.setHandled(true);
    }

  }

  /**
   * Resets the bounds to the original location.
   */
  public void resetBounds() {
    this.setBoundsFrom(resetBounds);
  }

  /**
   * Resets the component bounds whenever the tool bounds are adjusted.
   *
   * @param bounds
   */
  @Override
  protected void onBoundsSet(Rectangle bounds) {
    if (this.getComponent() != null) {
      this.getComponent().setLocation(bounds.x + BORDER, bounds.y + TOP_BORDER);
      this.getComponent().setSize(bounds.width - 2 * BORDER,
          bounds.height - TOP_BORDER - BORDER);
    }
  }

  /**
   * Gets the margin bounds.
   *
   * @return
   */
  public Rectangle getMarginBounds() {
    Rectangle result = new Rectangle(this.getBounds().x,
        this.getBounds().y,
        this.getBounds().width + RESIZE_MARGIN,
        this.getBounds().height + RESIZE_MARGIN);
    return result;
  }

  /**
   * Handles mouse pressed.
   *
   * @param e The MapEventMouse event arguments, which extend mouse events with
   * map information.
   */
  @Override
  public final void mousePressed(MapEventMouse e) {
    Rectangle bounds = getMarginBounds();
    if (bounds.contains(e.getPoint())) {
      if (SwingUtilities.isLeftMouseButton(e)) {
        this.dragStart = e.getPoint();
        this.originalPosition = this.getBounds().getLocation();
        if (allowResize) {
          if (mouseOverBRCorner) {
            resizingDiagonal = true;
            e.setHandled(true);
          } else if (mouseOverRightEdge) {
            resizingRight = true;
            e.setHandled(true);
          } else if (mouseOverBottomEdge) {
            resizingBottom = true;
            e.setHandled(true);
          }
        }
        if (!e.isHandled()) {
          dragging = true;
          e.setHandled(true);
        }

      }
      e.setHandled(true);
    }

  }

  /**
   * Handles mouse released.
   *
   * @param e The MapEventMouse event arguments, which extend mouse events with
   * map information.
   */
  @Override
  public final void mouseReleased(MapEventMouse e) {
    int x = this.getBounds().x;
    int y = this.getBounds().y;
    int w = this.getBounds().width;
    Rectangle closeRect = new Rectangle(x + w - 17, y + 4, 9, 9);
    if (closeRect.contains(new Point(e.getX(), e.getY()))) {
      this.setVisible(false);
      e.getMap().paintImmediately(e.getMap().getVisibleRect());
      fireMapElementClosed(new MapElementClosedEvent(this, this));
    }
    if (resizingDiagonal) {
      resizingDiagonal = false;
      e.setHandled(true);
    }
    if (resizingRight) {
      resizingRight = false;
      e.setHandled(true);
    }
    if (resizingBottom) {
      resizingBottom = false;
      e.setHandled(true);
    }

    if (dragging) {
      if (this.getBounds().contains(e.getPoint())) {
        if (SwingUtilities.isLeftMouseButton(e)) {
          int mapWidth = e.getMap().getContent().getDisplay().getWidth();
          int mapHeight = e.getMap().getContent().getDisplay().getHeight();
          dragging = false;
          if (this.getBounds().x > mapWidth / 2) {
            anchorRight = true;
            right = mapWidth - x;
          } else {
            anchorRight = false;
            right = null;
          }
          if (this.getBounds().y > mapHeight / 2) {
            anchorBottom = true;
            bottom = mapHeight - y;
          } else {
            anchorBottom = false;
            bottom = null;
          }
        }
      }
    }

    if (this.getBounds().contains(e.getPoint())) {
      e.setHandled(true);
    }

  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>
  /**
   * @return the anchorRight
   */
  public final boolean isAnchorRight() {
    return anchorRight;
  }

  /**
   * @param anchorRight the anchorRight to set
   */
  public final void setAnchorRight(boolean anchorRight) {
    this.anchorRight = anchorRight;

  }

  /**
   * Sets the component to be drawn.
   *
   * @return the component
   */
  public final Component getComponent() {
    return component;
  }

  /**
   * @param component the component to set
   */
  public final void setComponent(Component component) {
    this.component = component;
    map.add(component);
    updateSize();
    this.getBounds().width = component.getWidth() + 2 * BORDER;
    this.getBounds().height = component.getHeight() + BORDER + TOP_BORDER;
    component.setLocation(this.getBounds().x + BORDER, this.getBounds().y + TOP_BORDER);
    this.setResetBoundsFrom(this.getBounds());
    component.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        updateSize();
      }
    });
  }

  /**
   * Updates the size properties of this element to reflect the component that
   * it contains.
   */
  private void updateSize() {
    Rectangle b = this.getBounds();
    if (component != null) {
      int w = component.getWidth();
      int h = component.getHeight();
      this.setBoundsFrom(new Rectangle(b.x, b.y, w + BORDER * 2,
          h + BORDER + TOP_BORDER));
    } else {
      this.setBoundsFrom(new Rectangle(b.x, b.y, BORDER * 2, BORDER + TOP_BORDER));
    }
    component.doLayout();
    if (map != null) {
      map.doLayout();
      map.repaint();
    }

  }

  /**
   * @return the map
   */
  public final MapPanel getMap() {
    return map;
  }

  /**
   * @param map the map to set
   */
  public final void setMap(MapPanel map) {
    this.map = map;
  }

  @Override
  public void setVisible(boolean visible) {
    super.setVisible(visible); //To change body of generated methods, choose Tools | Templates.
    if (this.getComponent() != null) {
      this.component.setVisible(visible);
    }
  }



  /**
   * Gets a String title that will appear at the top of the component in the
   * title bar.
   *
   * @return the title
   */
  public final String getTitle() {
    return title;
  }

  /**
   * Sets a String title that will appear at the top of the component in the
   * title bar.
   *
   * @param title the title to set
   */
  public final void setTitle(String title) {
    this.title = title;
  }

  /**
   * Gets a boolean that if true will allow the control to be resized.
   *
   * @return the allowResize
   */
  public final boolean isAllowResize() {
    return allowResize;
  }

  /**
   * Sets a boolean that if true will allow the control to be resized.
   *
   * @param allowResize the allowResize to set
   */
  public final void setAllowResize(boolean allowResize) {
    this.allowResize = allowResize;
  }

  /**
   * Gets the Integer pixel location of the left of this component from the
   * right side of the map if the user has moved the component.
   *
   * @return the right
   */
  public final Integer getRight() {
    return right;
  }

  /**
   * Sets the Integer pixel location of the left of this component from the
   * right side of the map if the user has moved the component.
   *
   * @param right the right to set
   */
  public final void setRight(Integer right) {
    this.right = right;
  }

  /**
   * Gets the Rectangular bounds where the element will be positioned when
   * resetting to it's original location.
   *
   * @return the resetBounds
   */
  public final Rectangle getResetBounds() {
    return resetBounds;
  }

  /**
   * Sets the reset bounds by copying the X, Y, width and height from the
   * specified rectangle to the bounds.
   *
   * @param rect The rectangle to copy from.
   */
  public final void setResetBoundsFrom(Rectangle rect) {
    resetBounds.x = rect.x;
    resetBounds.y = rect.y;
    resetBounds.width = rect.width;
    resetBounds.height = rect.height;
  }

}
