/*
 * The MIT License
 *
 * Copyright 2015 Harold A. Dunsford Jr. Ph.D..
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

import gov.ca.water.shapelite.events.MapElementClosedEvent;
import gov.ca.water.shapelite.events.MapToolStateChangedEvent;
import gov.ca.water.shapelite.map.element.MovableComponentMapElement;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.element.AnchorDirection;
import java.awt.Rectangle;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ToolButtonListMapTool extends MapTool {

  /**
   * The distance in pixels from the right side of the map, if anchored right.
   */
  private static final int RIGHT_OFFSET = 75;

  /**
   * Movable legend.
   */
  private MovableComponentMapElement movableToolButtons;

  /**
   * The panel with tool buttons.
   */
  private final ToolListPanel toolPanel;

  /**
   * The map that this list belongs to.
   */
  private MapPanel map;

  /**
   * Create a new instance of the Map Tool Legend.
   *
   * @param mapPanel The mapPanel with layers to be reflected in the legend
   * content.
   */
  public ToolButtonListMapTool(MapPanel mapPanel) {
    super.setName("buttonList");
    map = mapPanel;
    movableToolButtons = new MovableComponentMapElement();
    movableToolButtons.setVisible(true);
    movableToolButtons.setAnchorRight(true);
    movableToolButtons.setRightPadding(RIGHT_OFFSET);
    movableToolButtons.setTitle("Tools");
    movableToolButtons.setAllowResize(false);
    toolPanel = new ToolListPanel();
    toolPanel.addMapToolStateChangedListener(new MapToolStateChangedEvent.Listener() {
      @Override
      public void mapToolStateChanged(MapToolStateChangedEvent e) {
        if (map != null) {
          map.repaint();
        }
      }
    });
    movableToolButtons.setMap(mapPanel);
    movableToolButtons.setComponent(toolPanel);
    movableToolButtons.setBoundsFrom(new Rectangle(1000, 25,
        toolPanel.getWidth(), toolPanel.getHeight()));
    movableToolButtons.addMapElementClosedListener(new MapElementClosedEvent.Listener() {
      @Override
      public void mapElementClosed(MapElementClosedEvent e) {
        setVisible(false);
      }
    });
    super.getElements().add(movableToolButtons);

  }

  /**
   * @return the movableToolButtons
   */
  public final MovableComponentMapElement getMovableTools() {
    return movableToolButtons;
  }

  /**
   * @param movableTools the movableToolButtons to set
   */
  public final void setMovableTools(MovableComponentMapElement movableTools) {
    this.movableToolButtons = movableTools;
  }

  /**
   * Gets the panel with tool buttons.
   *
   * @return the toolPanel
   */
  public final ToolListPanel getToolPanel() {
    return toolPanel;
  }

  /**
   * Refreshes the buttons from the tool list.
   */
  public final void refreshButtons() {
    toolPanel.refeshButtons();
    int t = MovableComponentMapElement.TOP_BORDER;
    int b = MovableComponentMapElement.BORDER;
    movableToolButtons.setBoundsFrom(new Rectangle(1000, 25,
        toolPanel.getWidth() + b * 2, toolPanel.getHeight() + t + b));
  }

  /**
   * Changes the position of the tool list button to the original rules.
   */
  public final void resetPosition() {
    movableToolButtons.setRight(null);
    movableToolButtons.setAnchorRight(true);
    movableToolButtons.setAnchor(AnchorDirection.TopRight);
    movableToolButtons.resetBounds();
  }

}
