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
package gov.ca.water.shapelite.map.maptool;

import gov.ca.water.shapelite.events.MapEventDisplay;
import gov.ca.water.shapelite.events.MapEventMouse;
import gov.ca.water.shapelite.events.MapEventTrack;
import gov.ca.water.shapelite.map.Images;
import gov.ca.water.shapelite.map.element.ButtonMapElement;
import gov.ca.water.shapelite.map.element.TrackSliderMapElement;
import gov.ca.water.shapelite.map.MapPaintArgs;
import gov.ca.water.shapelite.map.TrackListener;
import gov.ca.water.shapelite.map.ZoomBuffer;
import java.awt.Rectangle;
import javax.swing.SwingUtilities;

/**
 * This class supports the track slider that can be used to zoom in and out of
 * the map.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TrackZoomMapTool extends MapTool {

  /**
   * The TOP button rectangle.
   */
  private static final Rectangle TOP = new Rectangle(42, 85, 16, 16);

  /**
   * The BOTTOM rectangle around the BOTTOM button.
   */
  private static final Rectangle BOTTOM = new Rectangle(42, 205, 16, 16);

  /**
   * The maximum level for the zoom in the track slider.
   */
  private static final int MAX_LEVEL = 19;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The BOTTOM button, which zooms out.
   */
  private final ButtonMapElement bottomButton;

  /**
   * A buffer for caching image content when zooming to different levels.
   */
  private ZoomBuffer buffer;

  /**
   * A slider that allows the user to both see what level they are on depicted
   * graphically, and to zoom into or out of the map.
   */
  private final TrackSliderMapElement slider;

  /**
   * A MapElement button that, when pressed, zooms in.
   */
  private final ButtonMapElement topButton;

  //</editor-fold>
  /**
   * Creates a new instance of the MapToolTrackZoom tool for handling a zoom
   * slider.
   */
  public TrackZoomMapTool() {
    this.setName("trackZoom");

    /**
     * Top, Slider, Bottom to prevent shadow on button itself. The track element
     * is drawing first during paintContent, so it will always be underneath
     * everything.
     */
    slider = new TrackSliderMapElement();
    slider.setBoundsFrom(new Rectangle(TOP.x, TOP.y + TOP.height,
        TOP.width, BOTTOM.y - (TOP.y + TOP.height)));

    /**
     * Listen to the track slider being moved..
     */
    slider.addPositionChangedListener(new TrackListener() {
      /**
       * Occurs when the handle is grabbed and hides the normal map content.
       */
      @Override
      public void handleGrabbed(MapEventTrack e) {
        e.getMap().getContent().setVisible(false);
      }

      /**
       * Occurs when the handle is released, and shows the map content, and
       * modifies the level.
       */
      @Override
      public void handleReleased(MapEventTrack e) {
        e.getMap().getContent().setVisible(true);
        e.getMap().getContent().setLevel(MAX_LEVEL - e.getPosition());
      }

      /**
       * Occurs when the position is changed. This is not used.
       */
      @Override
      public void positionChanged(MapEventTrack e) {
      }
    });

    this.getElements().add(slider);
    topButton = new ButtonMapElement();
    topButton.setImage(Images.getInstance().plus().orElse(null));
    topButton.setBoundsFrom(TOP);
    this.getElements().add(topButton);

    // To better control the drawing order in this case, move the handle to act as a
    // direct member of this tool.  Ordinarily the handle's parent is the track.
    slider.getElements().remove(slider.getHandle());
    this.getElements().add(slider.getHandle());
    bottomButton = new ButtonMapElement();
    bottomButton.setImage(Images.getInstance().minus().orElse(null));
    bottomButton.setBoundsFrom(BOTTOM);
    this.getElements().add(bottomButton);
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * As the position is changed, this tool takes over and draws content for the
   * specified zoom level in the buffer. This will either create the image on
   * the fly, if it has not been created yet, or else it will store the image so
   * that future zooming is more responsive.
   *
   * @param args A MapPaintArgs argument with information about the drawing
   * canvas and the Map.
   */
  @Override
  public final void paintContent(MapPaintArgs args) {
    if (slider.isDragging()) {
      if (buffer != null) {
        buffer.setLevel(MAX_LEVEL - slider.getPosition());
        buffer.paint(args);
      }
    }
  }

  /**
   * Occurs when the mouse is clicked. This handles the zoom in and zoom out in
   * response to a click on the TOP or BOTTOM buttons. A button event handler is
   * not strictly needed since the tool already has information about the mouse
   * click directly.
   *
   * @param e A MapEventMouse argument with information about the Map and mouse
   * condition.
   */
  @Override
  public final void mouseClicked(MapEventMouse e) {
    if (TOP.contains(e.getPoint()) && SwingUtilities.isLeftMouseButton(e)) {
      int level = e.getMap().getContent().getClosestLevel();
      level += 1;
      e.getMap().getContent().setLevel(level);
      e.setHandled(true);
      e.getMap().repaint();
    }

    if (BOTTOM.contains(e.getPoint()) && SwingUtilities.isLeftMouseButton(e)) {
      int level = e.getMap().getContent().getClosestLevel();
      level -= 1;
      e.getMap().getContent().setLevel(level);
      e.setHandled(true);
      e.getMap().repaint();
    }
  }

  /**
   * Occurs when the mouse is pressed.
   *
   * @param e A MapEventMouse argument with information about the Map and mouse
   * condition.
   */
  @Override
  public final void mousePressed(MapEventMouse e) {
    if (TOP.contains(e.getPoint()) || BOTTOM.contains(e.getPoint())) {
      e.setHandled(true); // prevent other tools from interacting if we are on a button.
    }
    super.mousePressed(e);
  }

  /**
   * Occurs when the mouse is released.
   *
   * @param e A MapEventMouse argument with information about the Map and mouse
   * condition.
   */
  @Override
  public final void mouseReleased(MapEventMouse e) {
    if (TOP.contains(e.getPoint()) || BOTTOM.contains(e.getPoint())) {
      e.setHandled(true); // prevent other tools from interacting if we are on a button.
    }
    super.mouseReleased(e);
  }

  /**
   * Occurs after the map content has been painted. This updates the slider
   * position based on zoom changes on the map.
   *
   * @param args A MapPaintArgs argument that has information about the Map and
   * the graphics surface for drawing.
   */
  @Override
  public final void paint(MapPaintArgs args) {
    if (!slider.isDragging()) {
      //
      slider.setPosition(MAX_LEVEL - args.getMap().getContent().getClosestLevel());
    }
    // paint the child elements
    super.paint(args);
  }

  /**
   * Occurs after the map content is rendered.
   *
   * @param e A MapEventDisplay argument that has information about the Map
   * control.
   */
  @Override
  public final void renderComplete(MapEventDisplay e) {
    // once the basic rendering of the content is complete, fill the zoom buffer.
    buffer = new ZoomBuffer();
  }

  //</editor-fold>
}
