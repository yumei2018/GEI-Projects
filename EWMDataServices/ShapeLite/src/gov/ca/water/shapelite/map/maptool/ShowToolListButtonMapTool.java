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

import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.events.MapEventComponent;
import gov.ca.water.shapelite.events.MapEventMouse;
import gov.ca.water.shapelite.map.Images;
import gov.ca.water.shapelite.map.MapButtonListener;
import gov.ca.water.shapelite.map.element.ButtonBorderedMapElement;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * This MapTool simply is a container for the MapElementPanButtons that do the
 * actual work of panning.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShowToolListButtonMapTool extends MapTool {

  /**
   * Gets the number of pixels from the right side of the map that the right
   * side of the button should be placed.
   */
  private static final int RIGHT_OFFSET = 50;

  /**
   * Gets the original positioning. This is not really used because it will be
   * quickly re-positioned by the componentResized method.
   */
  private static final Rectangle BOUNDS = new Rectangle(1000, 75, 40, 40);

  /**
   * The actual button element that appears on the map.
   */
  private final ButtonBorderedMapElement element;

  /**
   * creates a new instance of the MapToolPanButtons class.
   */
  public ShowToolListButtonMapTool() {
    element = new ButtonBorderedMapElement();
    element.setBoundsFrom(BOUNDS);
    element.setToolTipText("Hide Tools.");

    Optional<Image> img = Images.getInstance().get("ToolList32.png");
    if (img.isPresent()) {
      element.setImage(img.get());
    }
    element.addMapButtonListener(new MapButtonListener() {
      @Override
      public void buttonClicked(MapEventMouse e) {
        toggleToolsVisibility(e);
      }
    }
    );
    element.setEnabled(true);
    element.setVisible(true);
    super.setVisible(true);
    super.getElements().add(element);
    super.setEnabled(true);

  }

  /**
   * Allows the user to browse for a .shp file and add it.
   *
   * @param e The map event mouse.
   */
  public final void toggleToolsVisibility(MapEventMouse e) {
    Optional<MapTool> maybeTool = e.getMap().getToolbox().getMapTool("buttonList");
    if (maybeTool.isPresent()) {
      MapTool tool = maybeTool.get();
      boolean visible = tool.isVisible();
      if (!visible) {
        if (tool instanceof ToolButtonListMapTool) {
          ToolButtonListMapTool tbl = (ToolButtonListMapTool) tool;
          tbl.getMovableTools().setVisible(true);
          tbl.resetPosition();
        }
        tool.setVisible(true);
        element.setToolTipText("Hide tools.");
        tool.setEnabled(true);
      } else {
        tool.setVisible(false);
        element.setToolTipText("Show tools.");
        tool.setEnabled(false);
        if (tool instanceof ToolButtonListMapTool) {
          ToolButtonListMapTool tbl = (ToolButtonListMapTool) tool;
          tbl.getMovableTools().setVisible(false);
        }
      }
    }
    e.getMap().repaint();
  }

  /**
   * Repositions the button element when the component is resized.
   *
   * @param e The MapEventComponent.
   */
  @Override
  public final void componentResized(MapEventComponent e) {
    element.getBounds().x = e.getMap().getWidth() - RIGHT_OFFSET;
  }

}
