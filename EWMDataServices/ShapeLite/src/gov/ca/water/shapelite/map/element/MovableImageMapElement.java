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
import gov.ca.water.shapelite.map.ShadowRoundedRectangle;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
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
public class MovableImageMapElement extends MapElement {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private Image image;
  private Point location;
  private boolean mouseOverCancel;
  private boolean dragging;
  private Point dragStart;
  private Point originalPosition;
  private boolean anchorRight = false;
  private Integer right;
  private boolean anchorBottom = false;
  private Integer bottom;

  private Image closeGray = null;

  private Image closeRed = null;

  //</editor-fold>
  /**
   * Creates a new instance of the MapElementImage class.
   */
  public MovableImageMapElement() {
    closeRed = Images.getInstance().get("CloseRed.png").orElse(null);
    closeGray = Images.getInstance().get("CloseGray.png").orElse(null);
  }

  //<editor-fold defaultstate="collapsed" desc="MapElementClosedEvent">
  /**
   * The list of listeners for the MapElementClosed event.
   */
  private final List<MapElementClosedEvent.Listener> mapElementClosedListeners
      = new ArrayList<>();

  /**
   * Adds the specified listener to the list of listeners to be notified during
   * an event. If the item is already in the list, it will not be added a second
   * time.
   *
   * @param listener The MapElementClosedEvent.Listener to connect.
   */
  public final void addMapElementClosedListener(MapElementClosedEvent.Listener listener) {
    if (!mapElementClosedListeners.contains(listener)) {
      mapElementClosedListeners.add(listener);
    }
  }

  /**
   * Removes the specified listener from the list if it is in the list.
   *
   * @param listener The MapElementClosedEvent.Listener to disconnect.
   */
  public final void removeMapElementClosedListener(MapElementClosedEvent.Listener listener) {
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
   * Paints the movable control in the context of the entire map screen.
   *
   * @param args
   */
  @Override
  public void paint(MapPaintArgs args) {
    Graphics2D g = args.getGraphics();
    int x = this.getBounds().x;
    int y = this.getBounds().y;
    if (!dragging) {
      if (anchorRight) {
        int mapWidth = args.getMap().getContent().getDisplay().getWidth();
        if (right == null) {
          right = mapWidth - this.getBounds().x;
        }
        x = mapWidth - right;
        this.getBounds().x = x;
      }
      if (anchorBottom) {
        int mapHeight = args.getMap().getContent().getDisplay().getHeight();
        if (bottom == null) {
          bottom = mapHeight - this.getBounds().y;
        }
        y = mapHeight - bottom;
        this.getBounds().y = y;
      }
    }
    paint(g, x, y);
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
    g.drawImage(image, x + 4, y + 4, w - 8, h - 8, null);

    // draw close icon
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
  }

  /**
   * Mouse moved.
   * @param e
   */
  @Override
  public void mouseMoved(MapEventMouse e) {
    location = e.getPoint();
    int x = this.getBounds().x;
    int y = this.getBounds().y;
    int w = this.getBounds().width;
    int h = this.getBounds().height;
    Rectangle closeRect = new Rectangle(x + w - 17, y + 4, 9, 9);
    if (closeRect.contains(location) && !mouseOverCancel) {
      e.getMap().paintImmediately(closeRect);

    }
    if (!closeRect.contains(location) && mouseOverCancel) {
      e.getMap().paintImmediately(closeRect);
    }

  }

  @Override
  public void mouseDragged(MapEventMouse e) {
    super.mouseDragged(e); //To change body of generated methods, choose Tools | Templates.
    if (dragging) {
      this.getBounds().x = originalPosition.x + (e.getX() - dragStart.x);
      this.getBounds().y = originalPosition.y + (e.getY() - dragStart.y);
      e.getMap().paintImmediately(e.getMap().getBounds());
    }
    if (this.getBounds().contains(e.getPoint())) {
      e.setHandled(true);
    }
  }

  @Override
  public void mousePressed(MapEventMouse e) {
    if (this.getBounds().contains(e.getPoint())) {
      if (SwingUtilities.isLeftMouseButton(e)) {
        dragging = true;
        this.dragStart = e.getPoint();
        this.originalPosition = this.getBounds().getLocation();
      }
      e.setHandled(true);
    }

  }

  @Override
  public void mouseReleased(MapEventMouse e) {
    int x = this.getBounds().x;
    int y = this.getBounds().y;
    int w = this.getBounds().width;
    Rectangle closeRect = new Rectangle(x + w - 17, y + 4, 9, 9);
    if (closeRect.contains(new Point(e.getX(), e.getY()))) {
      this.setVisible(false);
      e.getMap().paintImmediately(e.getMap().getVisibleRect());
      fireMapElementClosed(new MapElementClosedEvent(this, this));
    }
    if (dragging) {
      if (this.getBounds().contains(e.getPoint())) {
        if (SwingUtilities.isLeftMouseButton(e)) {
          int mapWidth = e.getMap().getContent().getDisplay().getWidth();
          int mapHeight = e.getMap().getContent().getDisplay().getHeight();
          dragging = false;
          if (this.getBounds().x > mapWidth / 2) {
            anchorRight = true;
            right = null; // allow recalculate of right during drawing
          } else {
            anchorRight = false;
          }
          if (this.getBounds().y > mapHeight / 2) {
            anchorBottom = true;
            bottom = null;
          } else {
            anchorBottom = false;
          }
        }
      }
    }
    if (this.getBounds().contains(e.getPoint())) {
      e.setHandled(true);
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
   * @return the image
   */
  public Image getImage() {
    return image;
  }

  /**
   * @param image the image to set
   */
  public void setImage(Image image) {
    this.image = image;
  }

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
}
