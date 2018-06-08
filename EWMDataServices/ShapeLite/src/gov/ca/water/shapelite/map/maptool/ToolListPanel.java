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
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ToolListPanel extends javax.swing.JPanel {

  /**
   * The width and height of the buttons.
   */
  private static final int BUTTON_SIZE = 40;

  /**
   * The size of all the buttons in the list panel.
   */
  private static final Dimension BUTTON_DIMENSION
      = new Dimension(BUTTON_SIZE, BUTTON_SIZE);

  /**
   * The list of tools that should appear in this panel.
   */
  private final List<MapTool> tools;

  /**
   * Creates new form ToolListPanel.
   */
  public ToolListPanel() {
    initComponents();
    tools = new ArrayList<>();
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
  private void fireMapToolStateChanged(MapToolStateChangedEvent e) {
    for (MapToolStateChangedEvent.Listener listener : mapToolStateChangedListeners) {
      listener.mapToolStateChanged(e);
    }
  }

  //</editor-fold>
  /**
   * After the tools list has been updated, call refresh Buttons to refresh the
   * buttons. The first enabled tool will be the selected tool. Any other added
   * tools will be disabled.
   */
  public final void refeshButtons() {
    super.setBackground(Color.WHITE);
    BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
    super.setLayout(layout);
    boolean selectedSet = false;
    ToolButton firstButton = null;
    for (MapTool tool : tools) {
      ToolButton button = new ToolButton(tool);
      if (firstButton == null) {
        firstButton = button;
      }
      button.setMaximumSize(BUTTON_DIMENSION);
      button.setIcon(new ImageIcon(tool.getMapIcon()));
      button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      groupTools.add(button);
      this.add(button);
      if (tool.isEnabled()) {
        if (!selectedSet) {
          selectedSet = true;
          button.setSelected(true);
        } else {
          tool.setEnabled(false);
        }
      }
      button.addMapToolStateChangedListener(new MapToolStateChangedEvent.Listener() {
        @Override
        public void mapToolStateChanged(MapToolStateChangedEvent e) {
          fireMapToolStateChanged(e);
        }
      });
    }

    if (tools.size() > 0) {
      if (!selectedSet) {
        if (firstButton != null) {
          firstButton.setSelected(true);
        }
      }
    }
    super.setSize(BUTTON_SIZE, tools.size() * BUTTON_SIZE);

  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    groupTools = new ButtonGroup();

    setBackground(new Color(255, 255, 255));

    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGap(0, 40, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGap(0, 135, Short.MAX_VALUE)
    );
  }// </editor-fold>//GEN-END:initComponents


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private ButtonGroup groupTools;
  // End of variables declaration//GEN-END:variables
  /**
   * Gets the list of tools that should appear in this panel.
   *
   * @return the tools
   */
  public final List<MapTool> getTools() {
    return tools;
  }
}
