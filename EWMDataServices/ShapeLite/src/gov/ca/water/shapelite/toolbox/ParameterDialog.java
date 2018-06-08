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
package gov.ca.water.shapelite.toolbox;

import gov.ca.water.shapelite.dialog.ValidationNotifier;
import gov.ca.water.shapelite.events.ValidChangedEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ParameterDialog extends javax.swing.JPanel implements ValidationNotifier {

  /**
   * The maximum height of the help panel in pixels.
   */
  private static final int MAX_HEIGHT = 1000;

  /**
   * The tool.
   */
  private Tool tool;

  /**
   * Help listener.
   */
  private final HelpListener helpListener;

  /**
   * The mouse listener for the help.
   */
  private final MouseListener toolHelpListener;

  /**
   * The status listener.
   */
  private final ParameterStatusListener statusListener;

  /**
   * Creates new form ParameterDialog.
   */
  public ParameterDialog() {
    initComponents();
    helpListener = new HelpListener() {

      @Override
      public void showHelp(HelpEvent e) {
        panelHelp.removeAll();
        if (e.getTitle() != null) {
          JLabel helpTitle = new JLabel();
          helpTitle.setOpaque(true);
          helpTitle.setText("<HTML><B><H1>" + e.getTitle() + "</H1></B></HTML>");
          panelHelp.add(helpTitle);
        }
        int width = 300;
        if (e.getHelpImage() != null) {
          JLabel helpImage = new JLabel();
          helpImage.setIcon(new ImageIcon(e.getHelpImage()));
          width = e.getHelpImage().getWidth();
          panelHelp.setMaximumSize(new Dimension(width, MAX_HEIGHT));
          panelHelp.add(helpImage);
        }
        if (e.getHelpText() != null) {
          JTextArea helpText = new JTextArea();
          helpText.setText(e.getHelpText());
          helpText.setMaximumSize(new Dimension(width, MAX_HEIGHT));
          helpText.setLineWrap(true);
          helpText.setWrapStyleWord(true);
          panelHelp.add(helpText);
        }
        panelHelp.validate();
        panelHelp.repaint();
      }

    };

    toolHelpListener = new MouseAdapter() {

      @Override
      public void mouseClicked(MouseEvent me) {
        showHelp();
      }

      @Override
      public void mousePressed(MouseEvent me) {
        showHelp();
      }
    };
    super.addMouseListener(toolHelpListener);
    panelParameters.addMouseListener(toolHelpListener);
    statusListener = new ParameterStatusListener() {

      @Override
      public void statusChanged(ParameterStatusEvent evt) {
        checkValid();
      }

    };
  }

  //<editor-fold defaultstate="collapsed" desc="ValidChangedEvent">
  /**
   * The list of listeners.
   */
  private final List<ValidChangedEvent.Listener> validChangedListeners = new ArrayList<>();

  /**
   * Adds the specified listener to the list of listeners to be notified during
   * an event. If the item is already in the list, it will not be added a second
   * time.
   *
   * @param listener The ValidChangedEvent.Listener to connect.
   */
  @Override
  public final void addValidChangedListener(ValidChangedEvent.Listener listener) {
    if (!validChangedListeners.contains(listener)) {
      validChangedListeners.add(listener);
    }
  }

  /**
   * Removes the specified listener from the list if it is in the list.
   *
   * @param listener The ValidChangedEvent.Listener to disconnect.
   */
  @Override
  public final void removeValidChangedListener(ValidChangedEvent.Listener listener) {
    validChangedListeners.remove(listener);
  }

  /**
   * Fires the ValidChanged event and notifies each of the listeners.
   *
   * @param e A {EventType}Event with the source object and any properties
   * associated with this event.
   */
  public final void fireValidChanged(ValidChangedEvent e) {
    for (ValidChangedEvent.Listener listener : validChangedListeners) {
      listener.validChanged(e);
    }
  }

  //</editor-fold>
  /**
   * Updates the panel to reflect the current tool.
   */
  private void showHelp() {
    panelHelp.removeAll();
    if (tool != null) {
      int height = 0;
      if (tool.getName() != null) {
        JLabel helpTitle = new JLabel();
        helpTitle.setOpaque(true);
        helpTitle.setText("<HTML><B><H1>" + tool.getName() + "</H1></B></HTML>");
        height += helpTitle.getHeight();
        panelHelp.add(helpTitle);
      }
      int width = 300;
      if (tool.getHelpImage() != null) {
        JLabel helpImage = new JLabel();
        helpImage.setIcon(new ImageIcon(tool.getHelpImage()));
        panelHelp.add(helpImage);
        height += helpImage.getHeight();
        panelHelp.setMaximumSize(new Dimension(width, MAX_HEIGHT));
      }
      if (tool.getHelpText() != null) {
        JTextArea helpText = new JTextArea();
        helpText.setWrapStyleWord(true);
        helpText.setLineWrap(true);
        helpText.setText(tool.getHelpText());
        helpText.setMaximumSize(new Dimension(width, MAX_HEIGHT - height));
        panelHelp.add(helpText);
      }
    }
    panelHelp.validate();
    panelHelp.repaint();

  }

  /**
   * Enables or disables the "OK" button based on the status.
   */
  public final void checkValid() {
    boolean result = true;
    if (tool != null) {
      for (Parameter p : tool.getParameters()) {
        if (!p.isValid()) {
          result = false;
          break;
        }
      }
      fireValidChanged(new ValidChangedEvent(this, result));
    }
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jSplitPane1 = new JSplitPane();
    panelParameters = new JPanel();
    panelHelp = new JPanel();

    jSplitPane1.setDividerLocation(401);
    jSplitPane1.setResizeWeight(0.5);
    jSplitPane1.setToolTipText("");

    panelParameters.setMinimumSize(new Dimension(400, 0));
    panelParameters.setName(""); // NOI18N
    panelParameters.setLayout(new BoxLayout(panelParameters, BoxLayout.Y_AXIS));
    jSplitPane1.setLeftComponent(panelParameters);

    panelHelp.setMaximumSize(new Dimension(500, 1000));
    panelHelp.setLayout(new BoxLayout(panelHelp, BoxLayout.Y_AXIS));
    jSplitPane1.setRightComponent(panelHelp);

    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(jSplitPane1, GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
        .addGap(0, 0, 0))
    );
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(jSplitPane1, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
        .addGap(0, 0, 0))
    );
  }// </editor-fold>//GEN-END:initComponents

  /**
   * This method cycles through each of the parameters on a tool and defines the
   * panels to use.
   */
  public final void refreshPanels() {
    this.removeAll();
    for (Parameter param : tool.getParameters()) {
      param.panelOpened();
      ParameterPanel panel = param.createPanel();
      panelParameters.add(panel);
      panel.addHelpListener(helpListener);
    }
    tool.panelOpened();
  }

  /**
   * @return the tool
   */
  public final Tool getTool() {
    return tool;
  }

  /**
   * @param tool the tool to set
   */
  public final void setTool(Tool tool) {
    this.tool = tool;
    for (Parameter p : tool.getParameters()) {
      p.addStatusListener(statusListener);
    }
    refreshPanels();
    showHelp();
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private JSplitPane jSplitPane1;
  private JPanel panelHelp;
  private JPanel panelParameters;
  // End of variables declaration//GEN-END:variables
}
