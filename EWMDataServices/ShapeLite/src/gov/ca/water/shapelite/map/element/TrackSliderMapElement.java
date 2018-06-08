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
import gov.ca.water.shapelite.events.MapEventTrack;
import gov.ca.water.shapelite.map.Cursors;
import gov.ca.water.shapelite.map.MapPaintArgs;
import gov.ca.water.shapelite.map.ShadowRectangle;
import gov.ca.water.shapelite.map.TrackListener;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Arrays;
import javax.swing.SwingUtilities;

/**
 * This element shows a slider which allows the user to zoom, as well as a
 * button on top and bottom of the slider for controlling zooming.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TrackSliderMapElement extends MapElement {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The boolean that is true if the track slider button is being dragged up or
   * down.
   */
  private boolean dragging;

  /**
   * The ButtonMapElement that acts as the handle that is being grabbed.
   */
  private ButtonMapElement handle;

  /**
   * The integer height of the handle button.
   */
  private int handleHeight;

  /**
   * The integer maximum zoom scale for the handle. This should be 18.
   */
  private int max;

  /**
   * The integer minimum zoom scale for the handle. This should be 0.
   */
  private int min;

  /**
   * The integer position, which corresponds to the current zoom scale.
   */
  private int position;

  //</editor-fold>
  /**
   * Creates a new instance of the TrackSliderMapElement.
   */
  public TrackSliderMapElement() {
    handleHeight = 10;
    handle = new ButtonMapElement();
    handle.setBoundsFrom(new Rectangle(0, 0, 20, handleHeight));
    handle.setCursor(Cursors.getInstance().handCursor().orElse(null));

    this.getElements().add(handle);
    min = 0;
    max = 18;
  }

  //<editor-fold defaultstate="collapsed" desc="Events">
  /**
   * Adds the TrackListener to the list of listeners for this MapElement that
   * will be notified when the track is adjusted. Duplicates will not be added.
   *
   * @param listener The TrackListener interface implementation to add.
   */
  public void addPositionChangedListener(TrackListener listener) {
    if (!Arrays.asList(getListenerList().getListeners(TrackListener.class)).contains(listener)) {
      getListenerList().add(TrackListener.class, listener);
    }
  }

  /**
   * Removes the specified TrackListener listener from this object.
   *
   * @param listener The TrackListener interface implementation to remove.
   */
  public void removePositionChangedListener(TrackListener listener) {
    getListenerList().remove(TrackListener.class, listener);
  }

  void firePositionChanged(MapEventTrack evt) {
    TrackListener[] listeners = getListenerList().getListeners(TrackListener.class);
    for (TrackListener listener : listeners) {
      listener.positionChanged(evt);
    }
  }

  void fireHandleGrabbed(MapEventTrack evt) {
    TrackListener[] listeners = getListenerList().getListeners(TrackListener.class);
    for (TrackListener listener : listeners) {
      listener.handleGrabbed(evt);
    }
  }

  void fireHandleReleased(MapEventTrack evt) {
    TrackListener[] listeners = getListenerList().getListeners(TrackListener.class);
    for (TrackListener listener : listeners) {
      listener.handleReleased(evt);
    }
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * This method paints content.
   *
   * @param args This is the paint argument parameter that also has access to
   * the map.
   */
  @Override
  public void paint(MapPaintArgs args) {
    int cx = getBounds().x + getBounds().width / 2;
    Graphics2D g = args.getGraphics();
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    Rectangle r = new Rectangle(cx - 2, getBounds().y, 4, getBounds().height + 4);
    ShadowRectangle track = new ShadowRectangle(r);
    track.paint(g);
    Rectangle b = this.getBounds();
    double dy = (b.height) / (max - min);
    getHandle().setBoundsFrom(new Rectangle(b.x, (int) (b.y + getPosition() * dy),
        getHandle().getBounds().width, getHandle().getBounds().height));
    super.paint(args);
  }

  @Override
  public void mouseReleased(MapEventMouse e) {
    if (this.getBounds().contains(e.getPoint())) {
      e.setHandled(true); // dragging or not, we are over the slider, so ignore selection.
    }
    if (!isDragging()) {
      return;
    }
    if (SwingUtilities.isLeftMouseButton(e)) {
      e.getMap().setCursorLocked(false);
      super.mousePressed(e);
      setDragging(false);
      this.fireHandleReleased(new MapEventTrack(e.getMap(), this, getPosition()));
    }

  }

  @Override
  public void mouseDragged(MapEventMouse e) {
    if (this.getBounds().contains(e.getPoint())) {
      e.setHandled(true); // dragging or not, we are over the slider, so ignore selection.
    }
    if (!isDragging()) {
      return;
    }
    if (SwingUtilities.isLeftMouseButton(e)) {
      Rectangle b = this.getBounds();
      double dy = (b.height) / (max - min);
      this.setPosition((int) Math.round((e.getY() - this.getBounds().y) / dy));
      this.firePositionChanged(new MapEventTrack(e.getMap(), this, getPosition()));
      super.mouseDragged(e);

      e.getMap().repaint();
    }
  }

  @Override
  public void mousePressed(MapEventMouse e) {
    if (this.getBounds().contains(e.getPoint())) {
      if (SwingUtilities.isLeftMouseButton(e)) {
        e.getMap().setCursorLocked(true);
        e.getMap().setCursor(Cursors.getInstance().handClosedCursor().orElse(null));
        e.setHandled(true);
        super.mousePressed(e);
        setDragging(true);
        this.fireHandleGrabbed(new MapEventTrack(e.getMap(), this, getPosition()));
      }
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
   * Adds the extra behavior to update the handle bounds whenever the bounds are
   * set.
   *
   * @param bounds The new bounds rectangle.
   */
  @Override
  protected final void onBoundsSet(Rectangle bounds) {
    getHandle().getBounds().width = bounds.width;
  }

  /**
   * Gets the boolean that is true if the track slider button is being dragged
   * up or down.
   *
   * @return the dragging
   */
  public boolean isDragging() {
    return dragging;
  }

  /**
   * Sets the boolean that is true if the track slider button is being dragged
   * up or down.
   *
   * @param dragging the dragging to set
   */
  public void setDragging(boolean dragging) {
    this.dragging = dragging;
  }

  /**
   * Gets the ButtonMapElement that acts as the handle that is being grabbed.
   *
   * @return the handle
   */
  public ButtonMapElement getHandle() {
    return handle;
  }

  /**
   * Sets the ButtonMapElement that acts as the handle that is being grabbed.
   *
   * @param handle the handle to set
   */
  public void setHandle(ButtonMapElement handle) {
    this.handle = handle;
  }

  /**
   * Gets the integer height of the handle button in pixels.
   *
   * @return the handleHeight
   */
  public int getHandleHeight() {
    return handleHeight;
  }

  /**
   * Sets the integer height of the handle button in pixels.
   *
   * @param handleHeight the handleHeight to set
   */
  public void setHandleHeight(int handleHeight) {
    this.handleHeight = handleHeight;
  }

  /**
   * Gets the integer maximum zoom scale for the handle. This should be 18.
   *
   * @return the max
   */
  public int getMax() {
    return max;
  }

  /**
   * Sets the integer maximum zoom scale for the handle. This should be 18.
   *
   * @param max the max to set
   */
  public void setMax(int max) {
    this.max = max;
  }

  /**
   * Gets the integer minimum zoom scale for the handle. This should be 0.
   *
   * @return the min
   */
  public int getMin() {
    return min;
  }

  /**
   * Sets the integer minimum zoom scale for the handle. This should be 0.
   *
   * @param min the min to set
   */
  public void setMin(int min) {
    this.min = min;
  }

  /**
   * Gets the integer position, which corresponds to the current zoom scale.
   *
   * @return the position
   */
  public int getPosition() {
    return position;
  }

  /**
   * Sets the integer track zoom level between min and max, which default to 0
   * and 18.
   *
   * @param position The integer track position corresponding to the current
   * zoom level.
   */
  public void setPosition(int position) {

    if (position > max + 1) {
      this.position = max + 1;
    } else if (position < min) {
      this.position = min;
    } else {
      this.position = position;
    }
  }

  //</editor-fold>
}
