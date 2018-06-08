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
import gov.ca.water.shapelite.events.MapEventDisplay;
import gov.ca.water.shapelite.events.MapEventFocus;
import gov.ca.water.shapelite.events.MapEventMouse;
import gov.ca.water.shapelite.events.MapEventDisplayListener;
import gov.ca.water.shapelite.events.MapEventLayers;
import gov.ca.water.shapelite.events.MapEventMouseWheel;
import gov.ca.water.shapelite.events.MapEventKey;
import gov.ca.water.shapelite.events.MapEventComponent;
import gov.ca.water.shapelite.events.MapEventTiles;
import gov.ca.water.shapelite.map.MapPaintArgs;
import gov.ca.water.shapelite.map.MapPanel;
import java.awt.Cursor;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class serves both as the container storing the list of tools and as a
 * mechanism that listens to the Map events and forwards those events on to the
 * tools.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapToolbox implements MouseListener, MouseMotionListener, MouseWheelListener,
    ComponentListener, KeyListener, MapEventDisplayListener,
    FocusListener {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The one tool that has captured control of the painting all content. This is
   * used by the pan tool during panning.
   */
  private MapTool paintContentOwner;
  /**
   * The list of all the tools actively used by the map, listed in the order
   * they will be drawn and receive mouse arguments.
   */
  private List<MapTool> tools;

  //</editor-fold>
  /**
   * Creates a new instance of the MapToolbox.
   */
  public MapToolbox() {
    tools = new ArrayList<>();
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Occurs when the map is resized.
   *
   * @param e A ComponentEvent argument with information about the map
   * component.
   */
  @Override
  public final void componentResized(ComponentEvent e) {
    if (e.getSource() instanceof MapPanel) {
      MapEventComponent args = new MapEventComponent((MapPanel) e.getSource(), e);
      for (MapTool tool : getTools()) {
        if (tool.isEnabled()) {
          tool.componentResized(args);
        }
        if (args.isHandled()) {
          return;
        }
      }
    }
  }

  /**
   * Occurs when the map is moved.
   *
   * @param e A ComponentEvent argument with information about the map
   * component.
   */
  @Override
  public final void componentMoved(ComponentEvent e) {
    if (e.getSource() instanceof MapPanel) {
      MapEventComponent args = new MapEventComponent((MapPanel) e.getSource(), e);
      for (MapTool tool : getTools()) {
        if (tool.isEnabled()) {
          tool.componentMoved(args);
        }
        if (args.isHandled()) {
          return;
        }
      }
    }
  }

  /**
   * Occurs when the map is shown.
   *
   * @param e A ComponentEvent argument with information about the map
   * component.
   */
  @Override
  public final void componentShown(ComponentEvent e) {
    if (e.getSource() instanceof MapPanel) {
      MapEventComponent args = new MapEventComponent((MapPanel) e.getSource(), e);
      for (MapTool tool : getTools()) {
        if (tool.isEnabled()) {
          tool.componentShown(args);
        }
        if (args.isHandled()) {
          return;
        }
      }
    }
  }

  /**
   * Occurs when the map is hidden.
   *
   * @param e A ComponentEvent argument with information about the map
   * component.
   */
  @Override
  public final void componentHidden(ComponentEvent e) {
    if (e.getSource() instanceof MapPanel) {
      MapEventComponent args = new MapEventComponent((MapPanel) e.getSource(), e);
      for (MapTool tool : getTools()) {
        if (tool.isEnabled()) {
          tool.componentHidden(args);
        }
        if (args.isHandled()) {
          return;
        }
      }
    }
  }

  /**
   * Occurs when the layers on the map have been changed.
   *
   * @param e A MapEventDisplay argument with information about the map.
   */
  @Override
  public void contentChanged(MapEventDisplay e) {
    for (MapTool tool : getTools()) {
      if (tool.isEnabled()) {
        tool.contentChanged(e);
      }
    }
  }

  /**
   * Searches through the list of map tools and disables any tool that matches
   * the specified name.
   *
   * @param name The name of the tool to disable.
   */
  public void disableMapTool(String name) {
    if (name == null || name.isEmpty()) {
      return;
    }
    for (MapTool tool : tools) {
      if (name.equals(tool.getName())) {
        tool.setEnabled(false);
      }
    }
  }

  /**
   * Searches through the list of map tools and returns the tool with the
   * specified name.
   *
   * @param name The String name of the tool to return.
   * @return The first MapTool with the specified name.
   */
  public final Optional<MapTool> getMapTool(String name) {
    if (name == null || name.isEmpty()) {
      return Optional.empty();
    }
    for (MapTool tool : tools) {
      if (name.equals(tool.getName())) {
        return Optional.of(tool);
      }
    }
    return Optional.empty();
  }

  /**
   * Searches through the list of map tools and disables any tool that matches
   * the specified name.
   *
   * @param name The name of the tool to disable.
   */
  public void enableMapTool(String name) {
    if (name == null || name.isEmpty()) {
      return;
    }
    for (MapTool tool : tools) {
      if (name.equals(tool.getName())) {
        tool.setEnabled(true);
      }
    }
  }

  /**
   * Occurs when the mouse exists the map.
   *
   * @param e A MouseEvent argument with information about the mouse condition.
   */
  @Override
  public final void mouseExited(MouseEvent e) {
    if (e.getSource() instanceof MapPanel) {
      MapEventMouse args = new MapEventMouse((MapPanel) e.getSource(), e);
      for (MapTool tool : getTools()) {
        if (tool.isEnabled()) {
          tool.mouseExited(args);
        }
        if (args.isHandled()) {
          return;
        }
      }
    }
  }

  /**
   * Occurs when the map gains the focus.
   *
   * @param e A FocusEvent argument with information for the focus change.
   */
  @Override
  public void focusGained(FocusEvent e) {
   
    if (e.getSource() instanceof MapPanel) {
      MapEventFocus args = new MapEventFocus((MapPanel) e.getSource(), e.getID(), e.isTemporary(), e.getOppositeComponent());
      for (MapTool tool : getTools()) {
        if (tool.isEnabled()) {
          tool.focusGained(args);
        }
      }
    }
  }

  /**
   * Occurs when the map loses focus.
   *
   * @param e A FocusEvent argument with information for the focus change.
   */
  @Override
  public void focusLost(FocusEvent e) {
    if (e.getSource() instanceof MapPanel) {
      MapEventFocus args = new MapEventFocus((MapPanel) e.getSource(), e.getID(), e.isTemporary(), e.getOppositeComponent());
      for (MapTool tool : getTools()) {
        if (tool.isEnabled()) {
          tool.focusLost(args);
        }
      }
    }
  }

  /**
   * Occurs when a key is pressed while the map has focus.
   *
   * @param e A KeyEvent with information about what key was involved in the
   * event.
   */
  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getSource() instanceof MapPanel) {
      MapEventKey args = new MapEventKey((MapPanel) e.getSource(), e);
      for (MapTool tool : getTools()) {
        if (tool.isEnabled()) {
          tool.keyPressed(args);
        }
        if (args.isHandled()) {
          return;
        }
      }
    }
  }

  /**
   * Occurs when a key is released while the map has focus.
   *
   * @param e A KeyEvent with information about what key was involved in the
   * event.
   */
  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getSource() instanceof MapPanel) {
      MapEventKey args = new MapEventKey((MapPanel) e.getSource(), e);
      for (MapTool tool : getTools()) {
        if (tool.isEnabled()) {
          tool.keyReleased(args);
        }
        if (args.isHandled()) {
          return;
        }
      }
    }
  }

  /**
   * Occurs when a key is typed while the map has focus.
   *
   * @param e A KeyEvent with information about what key was involved in the
   * event.
   */
  @Override
  public void keyTyped(KeyEvent e) {
    if (e.getSource() instanceof MapPanel) {
      MapEventKey args = new MapEventKey((MapPanel) e.getSource(), e);
      for (MapTool tool : getTools()) {
        if (tool.isEnabled()) {
          tool.keyTyped(args);
        }
        if (args.isHandled()) {
          return;
        }
      }
    }
  }

  /**
   * Occurs when the mouse is clicked on the map.
   *
   * @param e A MouseEvent argument with information about the mouse condition.
   */
  @Override
  public final void mouseClicked(MouseEvent e) {
    if (e.getSource() instanceof MapPanel) {
      MapEventMouse args = new MapEventMouse((MapPanel) e.getSource(), e);
      for (MapTool tool : getTools()) {
        if (tool.isEnabled()) {
          tool.mouseClicked(args);
        }
        if (args.isHandled()) {
          return;
        }
      }
    }
  }

  /**
   * Occurs when the mouse is dragged over the map.
   *
   * @param e A MouseEvent argument with information about the mouse condition.
   */
  @Override
  public final void mouseDragged(MouseEvent e) {
    if (e.getSource() instanceof MapPanel) {
      MapEventMouse args = new MapEventMouse((MapPanel) e.getSource(), e);
      for (MapTool tool : getTools()) {
        if (tool.isEnabled()) {
          tool.mouseDragged(args);
        }
        if (args.isHandled()) {
          return;
        }
      }
    }
  }

  /**
   * Occurs when the mouse enters the map.
   *
   * @param e A MouseEvent argument with information about the mouse condition.
   */
  @Override
  public final void mouseEntered(MouseEvent e) {
    if (e.getSource() instanceof MapPanel) {
      
      MapEventMouse args = new MapEventMouse((MapPanel) e.getSource(), e);
      for (MapTool tool : getTools()) {
        if (tool.isEnabled()) {
          tool.mouseEntered(args);
        }
        if (args.isHandled()) {
          return;
        }
      }
    }
  }

  /**
   * Occurs when the mouse is pressed on the map.
   *
   * @param e A MouseEvent argument with information about the mouse condition.
   */
  @Override
  public final void mousePressed(MouseEvent e) {
    if (e.getSource() instanceof MapPanel) {
      MapEventMouse args = new MapEventMouse((MapPanel) e.getSource(), e);
      for (MapTool tool : getTools()) {
        if (tool.isEnabled()) {
          tool.mousePressed(args);
        }
        if (args.isHandled()) {
          return;
        }
      }
    }
  }

  /**
   * Occurs when the mouse moves on the map without being dragged.
   *
   * @param e A MouseEvent argument with information about the mouse condition.
   */
  @Override
  public final void mouseMoved(MouseEvent e) {
    if (e.getSource() instanceof MapPanel) {
      MapPanel map = (MapPanel) e.getSource();
      MapEventMouse args = new MapEventMouse(map, e);
      for (MapTool tool : getTools()) {
        if (tool.isEnabled()) {
          tool.mouseMoved(args);
        }
        if (args.isHandled()) {
          break;
        }
      }
      if (!map.isCursorLocked()) {
        map.setCursor(args.getCursor());
      }
    }
  }

  /**
   * Occurs when the mouse is released on the map.
   *
   * @param e A MouseEvent argument with information about the mouse condition.
   */
  @Override
  public final void mouseReleased(MouseEvent e) {
    if (e.getSource() instanceof MapPanel) {
      MapEventMouse args = new MapEventMouse((MapPanel) e.getSource(), e);
      for (MapTool tool : getTools()) {
        if (tool.isEnabled()) {
          tool.mouseReleased(args);
        }
        if (args.isHandled()) {
          return;
        }
      }
    }
  }

  /**
   * Occurs when the mouse wheel scrolls.
   *
   * @param e A MouseWheelEvent argument with information about the mouse
   * condition.
   */
  @Override
  public final void mouseWheelMoved(MouseWheelEvent e) {
    if (e.getSource() instanceof MapPanel) {
      MapEventMouseWheel args = new MapEventMouseWheel((MapPanel) e.getSource(), e);
      for (MapTool tool : getTools()) {
        if (tool.isEnabled()) {
          tool.mouseWheelMoved(args);
        }
        if (args.isHandled()) {
          return;
        }
      }
    }
  }

  /**
   * This method is called each time the map panel is drawn.
   *
   * @param args A MapPaintArgs with information about the graphics drawing
   * surface and the Map control.
   */
  public final void paintContent(MapPaintArgs args) {
    if (paintContentOwner != null) {
      // During pan, other tools should paint their content according to the pan
      // tools instructions, and not according to their own.  So at that time
      // pan sets itself as the paintContentOwner temporarilly.
      paintContentOwner.paintContent(args);
    } else {
      for (MapTool tool : getTools()) {
        if (tool.isVisible()) {
          tool.paintContent(args);
        }

      }
    }

  }

  /**
   * This method is called each time the map panel is drawn.
   *
   * @param args A MapPaintArgs with information about the graphics drawing
   * surface and the Map control.
   */
  public final void paint(MapPaintArgs args) {
    for (MapTool tool : getTools()) {
      if (tool.isVisible()) {
        tool.paint(args);
      }

    }
  }

  /**
   * Occurs after the MapContent has been redrawn, and the map should be
   * repainted.
   *
   * @param e A MapEventDisplay argument with information about the map.
   */
  @Override
  public void renderComplete(MapEventDisplay e) {
    for (MapTool tool : getTools()) {
      if (tool.isEnabled()) {
        tool.renderComplete(e);
      }

    }
  }

  /**
   * Occurs after the tiles are downloaded.
   *
   * @param e A MapEventTiles argument with information about the Map and the
   * download event.
   */
  @Override
  public void tilesDownloaded(MapEventTiles e) {
    for (MapTool tool : getTools()) {
      if (tool.isEnabled()) {
        tool.tilesDownloaded(e);
      }
    }
  }

  /**
   * Occurs when the map layers have been changed.
   *
   * @param e a MapEventLayers that has information about the layer collection
   * and the Map control.
   */
  @Override
  public void layersChanged(MapEventLayers e) {
    for (MapTool tool : getTools()) {
      if (tool.isEnabled()) {
        tool.layersChanged(e);
      }
    }
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the one tool that has captured control of the painting all content.
   * This is used by the pan tool during panning.
   *
   * @return the paintContentOwner
   */
  public MapTool getPaintContentOwner() {
    return paintContentOwner;
  }

  /**
   * Sets the one tool that has captured control of the painting all content.
   * This is used by the pan tool during panning.
   *
   * @param paintContentOwner the paintContentOwner to set
   */
  public void setPaintContentOwner(MapTool paintContentOwner) {
    this.paintContentOwner = paintContentOwner;
  }

  /**
   * Gets the list of all the tools actively used by the map, listed in the
   * order they will be drawn and receive mouse arguments.
   *
   * @return the tools
   */
  public List<MapTool> getTools() {
    return tools;
  }

  /**
   * Sets the list of all the tools actively used by the map, listed in the
   * order they will be drawn and receive mouse arguments.
   *
   * @param tools the tools to set
   */
  public void setTools(List<MapTool> tools) {
    this.tools = tools;
  }
  //</editor-fold>
}
