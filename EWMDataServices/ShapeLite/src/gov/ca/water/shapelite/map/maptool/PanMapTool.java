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

import gov.ca.water.shapelite.events.MapEventMouse;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.data.DataFrame;
import gov.ca.water.shapelite.map.MapPaintArgs;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.map.PaintArgs;
import gov.ca.water.shapelite.map.resources.MapImages;
import java.awt.Graphics2D;
import java.awt.Toolkit;

/**
 * This class is extends the scroll zoom tool to also give panning function in
 * response to mouse movements.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PanMapTool extends ScrollZoomMapTool {

  /**
   * OPEN HAND image for before dragging.
   */
  private static final Cursor OPEN_HAND;

  /**
   * OPEN HAND HOTSPOT.
   */
  private static final Point OPEN_HAND_HOTSPOT = new Point(15, 15);

  /**
   * Closed hand during panning.
   */
  private static final Cursor CLOSED_HAND;

  /**
   * OPEN HAND HOTSPOT.
   */
  private static final Point CLOSED_HAND_HOTSPOT = new Point(16, 15);

  static {

    Optional<BufferedImage> maybeIcon = MapImages.get("HandOpen32.png");
    Cursor openHand;
    if (maybeIcon.isPresent()) {
      openHand = Toolkit.getDefaultToolkit().createCustomCursor(
          maybeIcon.get(), OPEN_HAND_HOTSPOT, "openHand");
    } else {
      openHand = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
    }
    OPEN_HAND = openHand;

    Optional<BufferedImage> maybeClosedIcon = MapImages.get("HandClosed32.png");
    Cursor closedHand;
    if (maybeClosedIcon.isPresent()) {
      closedHand = Toolkit.getDefaultToolkit().createCustomCursor(
          maybeClosedIcon.get(), CLOSED_HAND_HOTSPOT, "closedHand");
    } else {
      closedHand = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
    }
    CLOSED_HAND = closedHand;

  }
  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The current position in client pixel coordinates.
   */
  private Point current;

  /**
   * The buffered image that is stored as a reference during panning and is
   * redrawn slightly shifted as the user pans.
   */
  private BufferedImage image;

  /**
   * A boolean that is true if the map is in the act of panning.
   */
  private boolean panning;

  /**
   * The point that panning began.
   */
  private Point panStart;

  /**
   * The starting coordinate on the map control where the mouse was when panning
   * began.
   */
  private Coord start;

  /**
   * Right button.
   */
  private boolean rightButton;

  /**
   * A boolean that indicates whether this tool will paint a preview that
   * responds to dragging.
   */
  private boolean paintingPanPreview;

  /**
   * The map panel.
   */
  private MapPanel map;


  public static final String PAN_TOOL_NAME = "pan";
  //</editor-fold>
  /**
   * Creates a new instance of the Map control.
   */
  public PanMapTool() {
    super.setName(PAN_TOOL_NAME);
    super.setToolTip("Panning the map.");
    super.setMapIcon(MapImages.get("Pan32.png").orElse(null));
  }


  /**
   * Occurs as the mouse is moved with a button pressed. If we are actively
   * panning, then this will update the position of the map.
   *
   * @param e A MapEventMouse argument with information about the mouse
   * condition and the Map.
   */
  @Override
  public void mouseDragged(MapEventMouse e) {
    this.map = e.getMap();
    if (!panning) {
      return;
    }
    e.getMap().getContent().setVisible(false);
    current = e.getPoint();
    MapPanel map = e.getMap();
    map.paintImmediately(map.getVisibleRect());
    onSetDragCursor(e);

  }

  /**
   * Sets the drag cursor if the cursor is not already locked.
   *
   * @param e
   */
  protected void onSetDragCursor(MapEventMouse e) {
    if (!e.getMap().isCursorLocked()) {
      e.setCursor(CLOSED_HAND);
    }
  }

  /**
   * Handles map cursor.
   *
   * @param e
   */
  @Override
  public void mouseMoved(MapEventMouse e) {
    this.map = e.getMap();
    onSetMoveCursor(e);
  }

  /**
   *
   * @param e
   */
  protected void onSetMoveCursor(MapEventMouse e) {
    if (!e.isHandled()) {
      e.setCursor(OPEN_HAND);
      e.setHandled(true);
    }
  }

  public boolean isPanning() {
    return panning;
  }
  
  /**
   * Occurs when the mouse button is pressed. If the right mouse button is
   * pressed down, we begin panning.
   *
   * @param e The MapEventMouse arguments that have information about the map
   * and the mouse condition when mouse was pressed.
   */
  @Override
  public void mousePressed(MapEventMouse e) {
    if (e.getMap().isCursorLocked()) {
      return;
    }
    if (rightButton && !SwingUtilities.isRightMouseButton(e)) {
      return;
    }
    if (!rightButton && !SwingUtilities.isLeftMouseButton(e)) {
      return;
    }
    image = e.getMap().getContent().getDisplay();
    BufferedImage buffer = new BufferedImage(image.getWidth(),
        image.getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = buffer.createGraphics();
    g.drawImage(image, 0, 0, null);
    DataFrame frame = e.getMap().getContent();
    PaintArgs args = new PaintArgs(this, g, frame);
    e.getMap().getContent().paintDynamicLayers(args);
    g.dispose();
    image = buffer;
    panStart = e.getPoint();
    panning = true;

    e.getMap().setCursor(CLOSED_HAND);
    e.getMap().setCursorLocked(true);
    current = e.getPoint();
    start = Mercator.toMerc(e.getLocation());

    e.getMap().getToolbox().setPaintContentOwner(this);
    e.setHandled(true);
    //this.setVisible(true);
    paintingPanPreview = true;
    e.getMap().repaint();

  }

  /**
   * Occurs when the mouse is released. Stops panning and redraws the layers in
   * the new location on the map.
   *
   * @param e A MapEventMouse argument with information about the Map control
   * and the mouse condition.
   */
  @Override
  public void mouseReleased(MapEventMouse e) {
    if (rightButton && !SwingUtilities.isRightMouseButton(e)) {
      return;
    }
    if (!rightButton && !SwingUtilities.isLeftMouseButton(e)) {
      return;
    }
    if (panning) {
      panning = false;
      e.getMap().getToolbox().setPaintContentOwner(null);
      Coord end = Mercator.toMerc(e.getLocation());
      Envelope env = e.getMap().getContent().getEnvelopeMercator();
      env.getMax().setX(env.getMax().getX() - (end.getX() - start.getX()));
      env.getMin().setX(env.getMin().getX() - (end.getX() - start.getX()));
      env.getMax().setY(env.getMax().getY() - (end.getY() - start.getY()));
      env.getMin().setY(env.getMin().getY() - (end.getY() - start.getY()));
      e.getMap().getContent().setVisible(true);
      e.getMap().getContent().setEnvelope(env);
      e.getMap().getContent().paintImmediately();
      paintingPanPreview = false;
      e.getMap().setCursor(OPEN_HAND);
      e.getMap().setCursorLocked(false);
      e.getMap().repaint();
      e.setHandled(true);
    }

  }

  /**
   * When we are panning, we draw the cached map image in a slightly different
   * position. We don't bother redrawing all the layers until the mouse button
   * is released and panning has ended.
   *
   * @param e A MapPaintArgs argument with information about the drawing canvas
   * and the Map control.
   */
  @Override
  public void paintContent(MapPaintArgs e) {


    if (!this.paintingPanPreview) {
      super.paintContent(e);
    }
    if (panning) {
      AffineTransform oldT = e.getGraphics().getTransform();
      e.getGraphics().translate(current.x - panStart.x, current.y - panStart.y);
      e.getGraphics().drawImage(image, 0, 0, null);
      e.getMap().getContent().paintDynamicLayers(e);

      for (MapTool tool : e.getMap().getToolbox().getTools()) {
        if (tool != this) {
          // only draw paintContent as transformed.
          tool.paintContent(e);
        }
      }
      e.getGraphics().setTransform(oldT);
    } else {
      super.paintContent(e);
    }
  }

  /**
   * @return the rightButton
   */
  public final boolean isRightButton() {
    return rightButton;
  }

  /**
   * @param rightButton the rightButton to set
   */
  public final void setRightButton(boolean rightButton) {
    this.rightButton = rightButton;
  }

  /**
   * Gets a boolean that indicates whether this tool will paint a preview that
   * responds to dragging.
   *
   * @return the paintingPanPreview
   */
  public final boolean isPaintingPanPreview() {
    return paintingPanPreview;
  }

}
