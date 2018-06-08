/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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

import gov.ca.water.shapelite.events.MapToolStateChangedEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ToolButton extends JToggleButton {

  /**
   * The tool associated with this button.
   */
  private MapTool tool;

  /**
   * Creates a new JToggleButton that is linked to the enabled state of the
   * specified mapTool.
   *
   * @param mapTool The mapTool to be linked to the button state.
   */
  public ToolButton(MapTool mapTool) {
    super.setSelected(mapTool.isEnabled());
    tool = mapTool;
    super.setToolTipText(tool.getToolTip());
    super.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JToggleButton) {
          JToggleButton button = (JToggleButton) e.getSource();
          if (tool != null) {
            tool.setEnabled(button.isSelected());
            fireMapToolStateChanged(new MapToolStateChangedEvent(ToolButton.this, tool));
          }
        }
      }
    });
  }

  //<editor-fold defaultstate="collapsed" desc="MapToolStateChangedEvent">
  /**
   * The list of listeners.
   */
  private final List<MapToolStateChangedEvent.Listener> mapToolStateChangedListeners
      = new ArrayList<>();

  /**
   * Adds the specified listener to the list of listeners to be notified during
   * an event. If the item is already in the list, it will not be added a second
   * time.
   *
   * @param listener The MapToolStateChangedEvent.Listener to connect.
   */
  public final void addMapToolStateChangedListener(
      MapToolStateChangedEvent.Listener listener) {
    if (!mapToolStateChangedListeners.contains(listener)) {
      mapToolStateChangedListeners.add(listener);
    }
  }

  /**
   * Removes the specified listener from the list if it is in the list.
   *
   * @param listener The MapToolStateChangedEvent.Listener to disconnect.
   */
  public final void removeMapToolStateChangedListener(
      MapToolStateChangedEvent.Listener listener) {
    mapToolStateChangedListeners.remove(listener);
  }

  /**
   * Fires the MapToolStateChanged event and notifies each of the listeners.
   *
   * @param e A {EventType}Event with the source object and any properties
   * associated with this event.
   */
  protected final void fireMapToolStateChanged(MapToolStateChangedEvent e) {
    for (MapToolStateChangedEvent.Listener listener : mapToolStateChangedListeners) {
      listener.mapToolStateChanged(e);
    }
  }

  //</editor-fold>
  /**
   * Gets the tool associated with this button.
   *
   * @return the tool
   */
  public final MapTool getTool() {
    return tool;
  }

  /**
   * Sets the tool associated with this button.
   *
   * @param tool the tool to set
   */
  public final void setTool(MapTool tool) {
    this.tool = tool;
  }

}
