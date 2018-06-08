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
import gov.ca.water.shapelite.map.MapPaintArgs;
import gov.ca.water.shapelite.map.ShadowRoundedRectangle;
import gov.ca.water.shapelite.map.maptool.MapTool;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.ToolTipManager;

/**
 * This map element shows directional buttons that allow the user to pan using a
 * click of the mouse.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class FunctionButtonMapElement extends MapElement {

  /**
   * The duration of the tool tip in milliseconds.
   */
  private static final int TOOL_TIP_SUSTAIN = 3000;

  /**
   * The top.
   */
  private static final int TOP = 100;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The pixels of space between the edge of the button and the tool image.
   */
  private static final int PADDING = 4;

  /**
   * True if the button is actively depressed.
   */
  private boolean pressed;

  /**
   * The integer index of the currently selected map tool.
   */
  private int currentIndex;

  /**
   * Gets the list of map tools.
   */
  private final List<MapTool> tools;

  private boolean showing;

  /**
   * The logger utility for this class.
   */
  private static final Logger LOGGER = Logger.getLogger(
      FunctionButtonMapElement.class.getName());

  //</editor-fold>
  /**
   * Creates a new instance of the map buttons.
   */
  public FunctionButtonMapElement() {
    super.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    tools = new ArrayList<>();
    super.setAnchor(AnchorDirection.TopRight);
    super.getBounds().y = TOP;
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
    if (tools.size() > currentIndex) {
      boolean intersects = this.getBounds().contains(e.getPoint());
      if (intersects && !showing) {
        MapTool tool = this.tools.get(currentIndex);
        e.getMap().setToolTipText(tool.getToolTip());
        ToolTipManager.sharedInstance().registerComponent(e.getMap());
        // Normally tool tips show slowly.  For the map, use no delay.
        ToolTipManager.sharedInstance().setDismissDelay(TOOL_TIP_SUSTAIN);
        showing = true;
      }
      if (showing && !intersects) {
        ToolTipManager.sharedInstance().setDismissDelay(0);
        e.getMap().setToolTipText(null);
        ToolTipManager.sharedInstance().unregisterComponent(e.getMap());
        showing = false;
      }
    }

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
      pressed = true;
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
    boolean ok = true;
    if (!intersects(e.getPoint())) {
      ok = false;
    }
    if (e.getMap().getActiveElement() != this) {
      ok = false;
    }
    if (!pressed) {
      ok = false;
    }
    if (!intersects(e.getPoint())) {
      ok = false;
    }
    if (tools.size() <= 0) {
      ok = false;
    }

    if (ok) {
      e.setHandled(true);
      pressed = false;
      currentIndex = (currentIndex + 1) % tools.size();
      for (MapTool tool : tools) {
        tool.setEnabled(false);
      }
      tools.get(currentIndex).setEnabled(true);
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

    if (tools.size() > currentIndex) {
      BufferedImage image = tools.get(currentIndex).getMapIcon();
      g.drawImage(image, x + PADDING, y + PADDING, null);
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
   * @return the tools
   */
  public final List<MapTool> getTools() {
    return tools;
  }

  /**
   * @return the currentIndex
   */
  public final int getCurrentIndex() {
    return currentIndex;
  }

  /**
   * @param currentIndex the currentIndex to set
   */
  public final void setCurrentIndex(int currentIndex) {
    this.currentIndex = currentIndex;
  }

  //</editor-fold>
}
