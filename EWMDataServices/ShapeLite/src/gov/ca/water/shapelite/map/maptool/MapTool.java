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
import gov.ca.water.shapelite.events.MapEventFocus;
import gov.ca.water.shapelite.events.MapEventMouseWheel;
import gov.ca.water.shapelite.events.MapEventLayers;
import gov.ca.water.shapelite.events.MapEventKey;
import gov.ca.water.shapelite.events.MapEventComponent;
import gov.ca.water.shapelite.events.MapEventTiles;
import gov.ca.water.shapelite.map.element.MapElement;
import gov.ca.water.shapelite.map.MapPaintArgs;
import gov.ca.water.shapelite.map.MapPanel;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the MapTool base class that specific MapTool sub classes extend in
 * order to provide a consistent, modular and easily changeable platform for
 * future maps. Because the toolkit can so easily be added to or removed,
 * functionality can be very easily reworked into the map without requiring a
 * redesign of the entire architecture.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapTool {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * A boolean value that is true if this tool is currently functional.
   */
  private boolean active;

  /**
   * A list of sub-components like buttons that are collectively part of the
   * same basic functionality platform. This allows features like the
   * track-slider to be comprised of several smaller components.
   */
  private List<MapElement> elements;

  /**
   * A boolean value that is true if this tool should respond to mouse events.
   */
  private boolean enabled;

  /**
   * A <code>BufferedImage</code> an icon that can be used to represent this
   * tool, for instance on a tool strip or other component. This is currently
   * not used.
   */
  private BufferedImage icon;

  /**
   * A larger <code>BufferedImage</code> used to select map functionality.
   */
  private BufferedImage mapIcon;

  /**
   * A string name that can be used to identify the tool. This name is
   * changeable, and is not guaranteed to be unique.
   */
  private String name;

  /**
   * A boolean value that is true if this MapTool should be drawn during paint
   * and paint content operations.
   */
  private boolean visible;

  /**
   * A tool tip to display on the map button that enables this tool.
   */
  private String toolTip;

  //</editor-fold>
  /**
   * Creates a new instance of the MapTool class.
   */
  public MapTool() {
    enabled = true;
    visible = true;
    elements = new ArrayList<>();
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Occurs when the MapPanel is hidden.
   *
   * @param e A mapEventComponent object that has information about the Map
   * control.
   */
  public final void componentHidden(MapEventComponent e) {
    for (MapTool element : elements) {
      element.componentHidden(e);
    }
  }

  /**
   * Occurs when the MapPanel is moved.
   *
   * @param e A mapEventComponent object that has information about the Map
   * control.
   */
  public void componentMoved(MapEventComponent e) {
    for (MapTool element : elements) {
      element.componentMoved(e);
    }
  }

  /**
   * Occurs when the MapPanel is resized.
   *
   * @param e A mapEventComponent object that has information about the Map
   * control.
   */
  public void componentResized(MapEventComponent e) {
    for (MapTool element : elements) {
      element.componentResized(e);
    }
  }

  /**
   * Occurs when the MapPanel is shown.
   *
   * @param e A mapEventComponent object that has information about the Map
   * control.
   */
  public void componentShown(MapEventComponent e) {
    for (MapTool element : elements) {
      element.componentShown(e);
    }
  }

  /**
   * Occurs after the map content has been altered, but before the display has
   * been updated. The MapToolResize class is also responsible for updating the
   * new view in the case that the content has been altered.
   *
   * @param e A DisplayEvent that has information about the source of the event.
   */
  public void contentChanged(MapEventDisplay e) {
    for (MapTool element : elements) {
      element.contentChanged(e);
    }
  }

  /**
   * Occurs every time the MapPanel gains the focus.
   *
   * @param e The MapEventFocus event argument that has information about the
   * Map.
   */
  public void focusGained(MapEventFocus e) {
    for (MapTool element : elements) {
      element.focusGained(e);
    }
  }

  /**
   * Occurs when the MapPanel loses focus.
   *
   * @param e The MapEventFocus event argument that has information about the
   * Map.
   */
  public void focusLost(MapEventFocus e) {
    for (MapTool element : elements) {
      element.focusLost(e);
    }
  }

  /**
   * Occurs when a key is pressed while the MapPanel has the focus.
   *
   * @param e A mapEventKey argument that contains information about the keys
   * involved in the event as well as the Map control.
   */
  public void keyPressed(MapEventKey e) {
    for (MapTool element : elements) {
      element.keyPressed(e);
    }
  }

  /**
   * Occurs when a key is released while the MapPanel has the focus.
   *
   * @param e A mapEventKey argument that contains information about the keys
   * involved in the event as well as the Map control.
   */
  public void keyReleased(MapEventKey e) {
    for (MapTool element : elements) {
      element.keyReleased(e);
    }
  }

  /**
   * Occurs when a key is both pressed and released while the MapPanel has
   * focus.
   *
   * @param e A mapEventKey argument that contains information about the keys
   * involved in the event as well as the Map control.
   */
  public void keyTyped(MapEventKey e) {
    for (MapTool element : elements) {
      element.keyTyped(e);
    }
  }

  /**
   * Occurs when the layers are updated.
   *
   * @param e An ObservedListEvent where the type parameter should be MapLayers.
   */
  public void layersChanged(MapEventLayers e) {
    for (MapTool element : elements) {
      element.layersChanged(e);
    }
  }

  /**
   * Occurs when the mouse is clicked on the MapPanel.
   *
   * @param e A MapEventMouse that has information about the mouse state as well
   * as the MapPanel that initiated the event.
   */
  public void mouseClicked(MapEventMouse e) {
    for (MapTool element : elements) {
      element.mouseClicked(e);
    }
  }

  /**
   * Occurs when the mouse is dragged over the MapPanel.
   *
   * @param e A MapEventMouse that has information about the mouse state as well
   * as the MapPanel that initiated the event.
   */
  public void mouseDragged(MapEventMouse e) {
    for (MapTool element : elements) {
      element.mouseDragged(e);
    }
  }

  /**
   * Occurs when the mouse enters the MapPanel.
   *
   * @param e A MapEventMouse that has information about the mouse state as well
   * as the MapPanel that initiated the event.
   */
  public void mouseEntered(MapEventMouse e) {
    for (MapTool element : elements) {
      element.mouseEntered(e);
    }
  }

  /**
   * Occurs when the mouse leaves the MapPanel.
   *
   * @param e A MapEventMouse that has information about the mouse state as well
   * as the MapPanel that initiated the event.
   */
  public void mouseExited(MapEventMouse e) {
    for (MapTool element : elements) {
      element.mouseExited(e);
    }
  }

  /**
   * Occurs when the mouse moves over the MapPanel.
   *
   * @param e A MapEventMouse that has information about the mouse state as well
   * as the MapPanel that initiated the event.
   */
  public void mouseMoved(MapEventMouse e) {
    for (MapTool element : elements) {
      element.mouseMoved(e);
      if (e.isHandled()) {
        return;
      }
    }
  }

  /**
   * Occurs when a mouse button is pressed down while the mouse is on the
   * MapPanel.
   *
   * @param e A MapEventMouse that has information about the mouse state as well
   * as the MapPanel that initiated the event.
   */
  public void mousePressed(MapEventMouse e) {
    for (MapTool element : elements) {
      element.mousePressed(e);
    }
  }

  /**
   * Occurs when a mouse button is released while the mouse is over the
   * MapPanel, or if the original mouse press occurred on the MapPanel.
   *
   * @param e A MapEventMouse that has information about the mouse state as well
   * as the MapPanel that initiated the event.
   */
  public void mouseReleased(MapEventMouse e) {
    for (MapTool element : elements) {
      element.mouseReleased(e);
    }
  }

  /**
   * Occurs when the mouse wheel moves while the mouse is over the MapPanel.
   *
   * @param e A MapEventMouse that has information about the mouse state as well
   * as the MapPanel that initiated the event.
   */
  public void mouseWheelMoved(MapEventMouseWheel e) {
    for (MapTool element : elements) {
      element.mouseWheelMoved(e);
    }
  }

  /**
   * This method is called each time the map panel is drawn.
   *
   * @param args the MapPaintArgs arguments.
   */
  public void paint(MapPaintArgs args) {
    if (elements != null) {
      for (MapTool element : elements) {
        if (element.isVisible()) {
          element.paint(args);
        }
      }
    }
  }

  /**
   * This gives the tool an opportunity to adjust itself each time the layout of
   * the map is updated.
   *
   * @param map The map object.
   */
  public final void doLayout(MapPanel map) {
    onDoLayout(map);
    for (MapElement element : this.getElements()) {
      element.doLayout(map);
    }
  }

  /**
   * This is thrown before the layout of each sub-element is considered.
   *
   * @param map The MapPanel that is part of the layout description.
   */
  protected void onDoLayout(MapPanel map) {

  }

  /**
   * This method is called each time the map panel is drawn, but before the
   * "paint" method, so that all tools have a chance to paint content before any
   * paint themselves. Painting themselves is reserved for drawing buttons and
   * other overlay controls that don't move.
   *
   * @param args
   */
  public void paintContent(MapPaintArgs args) {
    if (elements != null) {
      for (MapTool element : elements) {
        element.paintContent(args);
      }
    }
  }

  /**
   * Occurs after the map content has been refreshed.
   *
   * @param e A DesplayEvent containing information about the event source.
   */
  public void renderComplete(MapEventDisplay e) {
    for (MapTool element : elements) {
      element.renderComplete(e);
    }
  }

  /**
   * Occurs after the tile is downloaded.
   *
   * @param e A TilesEvent that has information about the event source, and the
   * list of Tiles that were downloaded.
   */
  public void tilesDownloaded(MapEventTiles e) {
    for (MapTool element : elements) {
      element.tilesDownloaded(e);
    }
  }

  /**
   * Overrides the toString
   *
   * @return
   */
  @Override
  public String toString() {
    return this.getName();
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets a boolean value that is true if this tool is currently functional.
   *
   * @return the active
   */
  public final boolean isActive() {
    return active;
  }

  /**
   * Sets a boolean value that is true if this tool is currently functional.
   *
   * @param active the active to set
   */
  public final void setActive(boolean active) {
    this.active = active;
  }

  /**
   * Gets a list of sub-components like buttons that are collectively part of
   * the same basic functionality platform. This allows features like the
   * track-slider to be comprised of several smaller components.
   *
   * @return the elements
   */
  public final List<MapElement> getElements() {
    return elements;
  }

  /**
   * Sets a list of sub-components like buttons that are collectively part of
   * the same basic functionality platform. This allows features like the
   * track-slider to be comprised of several smaller components.
   *
   * @param elements the elements to set
   */
  public final void setElements(List<MapElement> elements) {
    this.elements = elements;
  }

  /**
   * Gets a boolean value that is true if this tool should respond to mouse
   * events.
   *
   * @return the enabled
   */
  public final boolean isEnabled() {
    return enabled;
  }

  /**
   * Sets a boolean value that is true if this tool should respond to mouse
   * events.
   *
   * @param enabled the enabled to set
   */
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
    if (this.enabled) {
      onEnabled();
    } else {
      onDisabled();
    }
  }

  /**
   * Occurs when the tool is enabled.
   */
  protected void onEnabled() {

  }

  /**
   * Occurs when the tool is disabled.
   */
  protected void onDisabled() {

  }

  /**
   * Gets a <code>BufferedImage</code> an icon that can be used to represent
   * this tool, for instance on a tool strip or other component. This is
   * currently not used.
   *
   * @return the icon
   */
  public final BufferedImage getIcon() {
    return icon;
  }

  /**
   * Sets a <code>BufferedImage</code> an icon that can be used to represent
   * this tool, for instance on a tool strip or other component. This is
   * currently not used.
   *
   * @param icon the icon to set
   */
  public final void setIcon(BufferedImage icon) {
    this.icon = icon;
  }

  /**
   * Gets a string name that can be used to identify the tool. This name is
   * changeable, and is not guaranteed to be unique.
   *
   * @return the name
   */
  public final String getName() {
    return name;
  }

  /**
   * Sets a string name that can be used to identify the tool. This name is
   * changeable, and is not guaranteed to be unique.
   *
   * @param name the name to set
   */
  public final void setName(String name) {
    this.name = name;
  }

  /**
   * Gets a boolean value that is true if this MapTool should be drawn during
   * paint and paint content operations.
   *
   * @return the visible
   */
  public final boolean isVisible() {
    return visible;
  }

  /**
   * Sets a boolean value that is true if this MapTool should be drawn during
   * paint and paint content operations.
   *
   * @param visible the visible to set
   */
  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  /**
   * Gets a larger <code>BufferedImage</code> used to select map functionality.
   *
   * @return the mapIcon
   */
  public final BufferedImage getMapIcon() {
    return mapIcon;
  }

  /**
   * Sets a larger <code>BufferedImage</code> used to select map functionality.
   *
   * @param mapIcon the mapIcon to set
   */
  public final void setMapIcon(BufferedImage mapIcon) {
    this.mapIcon = mapIcon;
  }

  /**
   * Gets a tool tip to display on the map button that enables this tool.
   *
   * @return the toolTip
   */
  public final String getToolTip() {
    return toolTip;
  }

  /**
   * Sets a tool tip to display on the map button that enables this tool.
   *
   * @param toolTip the toolTip to set
   */
  public final void setToolTip(String toolTip) {
    this.toolTip = toolTip;
  }

  //</editor-fold>
}
