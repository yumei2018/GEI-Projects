/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
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

import gov.ca.water.shapelite.dialog.DialogButtonType;
import gov.ca.water.shapelite.dialog.DialogHandler;
import gov.ca.water.shapelite.dialog.DefaultDialogHandler;
import gov.ca.water.shapelite.dialog.DialogResult;
import gov.ca.water.shapelite.toolbox.resources.ToolboxImages;
import java.awt.Window;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ConnectionParameterPanel extends ParameterPanel {

  private ConnectionParameter parameter;
  private ConnectionDialog detailPanel;
  private DefaultComboBoxModel<ConnectionParameter> comboModel;
  private DialogHandler dialogHandler;

  /**
   * Creates new form ConnectionParameterPanel
   */
  public ConnectionParameterPanel() {
    initComponents();

  }

  /**
   * Creates new form ConnectionParameterPanel
   *
   * @param parameter The connection parameter containing information about the
   * connection.
   */
  public ConnectionParameterPanel(ConnectionParameter parameter) {
    initComponents();
    if (!ConnectionParameterManager.getInstance().getConnections().contains(
        parameter)) {
      ConnectionParameterManager.getInstance().getConnections().add(parameter);
    }
    this.parameter = parameter;
    this.detailPanel = new ConnectionDialog();
    updateGui();
  }

  private void updateGui() {
    comboModel = new DefaultComboBoxModel<>();
    for (ConnectionParameter param
        : ConnectionParameterManager.getInstance().getConnections()) {
      comboModel.addElement(param);
    }
    this.comboConnections.setModel(comboModel);

    this.comboConnections.setSelectedItem(this.parameter);
    updateStatus();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    labelStatus = new javax.swing.JLabel();
    panelConnection = new javax.swing.JPanel();
    buttonBrowse = new javax.swing.JButton();
    comboConnections = new javax.swing.JComboBox<>();

    setMaximumSize(new java.awt.Dimension(32767, 43));
    setPreferredSize(new java.awt.Dimension(448, 43));
    setRequestFocusEnabled(false);

    labelStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gov/ca/water/shapelite/toolbox/resources/StatusGood.png")));

    panelConnection.setBorder(javax.swing.BorderFactory.createTitledBorder(
        "Database Connection"));
    buttonBrowse.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gov/ca/water/shapelite/toolbox/resources/Plus14.png")));

    buttonBrowse.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonBrowseActionPerformed(evt);
      }
    });

    comboConnections.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboConnectionsActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout panelConnectionLayout
        = new javax.swing.GroupLayout(panelConnection);
    panelConnection.setLayout(panelConnectionLayout);
    panelConnectionLayout.setHorizontalGroup(
        panelConnectionLayout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelConnectionLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(comboConnections, 0, 313, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonBrowse, javax.swing.GroupLayout.PREFERRED_SIZE,
                37, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(20, 20, 20))
    );
    panelConnectionLayout.setVerticalGroup(
        panelConnectionLayout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
            panelConnectionLayout.createSequentialGroup()
            .addGroup(panelConnectionLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(comboConnections)
                .addComponent(buttonBrowse, javax.swing.GroupLayout.PREFERRED_SIZE,
                    0, Short.MAX_VALUE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE,
                32, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(panelConnection, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(labelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 43,
            Short.MAX_VALUE)
        .addComponent(panelConnection, javax.swing.GroupLayout.PREFERRED_SIZE,
            43, Short.MAX_VALUE)
    );
  }

  private void buttonBrowseActionPerformed(java.awt.event.ActionEvent evt) {
    this.detailPanel.setParameter(parameter);
    DialogResult result = getDialogHandler().showDialog(detailPanel,
        "Specify Connection Properties",
        DialogButtonType.OkCancel, null, buttonBrowse.getLocation());
    if (result == DialogResult.OK) {
      ConnectionParameterManager.getInstance().addConnection(detailPanel.getParameter());
      comboModel.setSelectedItem(detailPanel.getParameter());
    }

  }

  private void comboConnectionsActionPerformed(java.awt.event.ActionEvent evt) {

    this.parameter = (ConnectionParameter) comboModel.getSelectedItem();
    updateStatus();

  }

  private void updateStatus() {
    if (this.parameter.isValid()) {
      labelStatus.setIcon(new ImageIcon(ToolboxImages.getInstance().get(
          "StatusGood.png")));
      labelStatus.setToolTipText("Good Connection");
    } else {
      if (this.parameter.isRequired()) {
        labelStatus.setIcon(new ImageIcon(ToolboxImages.getInstance().get(
            "StatusError.png")));

      } else {
        labelStatus.setIcon(new ImageIcon(ToolboxImages.getInstance().get(
            "StatusWarning.png")));
      }
      labelStatus.setToolTipText(this.parameter.getValidationMessage());
    }
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonBrowse;
  private javax.swing.JComboBox<ConnectionParameter> comboConnections;
  private javax.swing.JLabel labelStatus;
  private javax.swing.JPanel panelConnection;
  // End of variables declaration//GEN-END:variables

  @Override
  Parameter getParameter() {
    return this.parameter;
  }

  ConnectionParameter getConnectionParameter() {
    return this.parameter;
  }

  @Override
  void setParameter(Parameter param) {
    if (param instanceof ConnectionParameter) {
      this.parameter = (ConnectionParameter) param;
      updateGui();
    }

  }

  /**
   * @return the dialogHandler
   */
  public DialogHandler getDialogHandler() {
    if (dialogHandler == null) {
      Window window = SwingUtilities.windowForComponent(this);
      JFrame frame = null;
      if (window instanceof JFrame) {
        frame = (JFrame) window;
      }
      dialogHandler = new DefaultDialogHandler(frame);
    }
    return dialogHandler;
  }

  /**
   * @param dialogHandler the dialogHandler to set
   */
  public void setDialogHandler(DialogHandler dialogHandler) {
    this.dialogHandler = dialogHandler;
  }
}
