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
import gov.ca.water.shapelite.dialog.DialogResult;
import gov.ca.water.shapelite.events.ParameterChangedEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ProjectionParameterPanel extends ParameterPanel {

  private ProjectionParameter parameter;
  private DialogHandler dialogHandler;

  /**
   * Creates new form ProjectionParameterPanel
   */
  public ProjectionParameterPanel() {
    initComponents();
    this.labelStatus.addMouseListener(helpClickListener);
    this.textValue.addMouseListener(helpClickListener);
    this.panelValue.addMouseListener(helpClickListener);
    addDocumentListener();
    this.parameter = new ProjectionParameter();

    this.parameter.addParameterChangedListener(new ParameterChangedEvent.Listener() {
      @Override
      public void parameterChanged(ParameterChangedEvent e) {
        updateGui();
      }
    });

    updateGui();
  }

  private void updateGui() {
    this.textValue.setText(this.parameter.getProjection().getName());
  }

  /**
   * Creates a new instance of a ProjectionParameterPanel using hte specified
   * ProjectionParameter as the parameter to modify.
   *
   * @param parameter
   */
  public ProjectionParameterPanel(ProjectionParameter parameter) {
    initComponents();
    this.labelStatus.addMouseListener(helpClickListener);
    this.textValue.addMouseListener(helpClickListener);
    this.panelValue.addMouseListener(helpClickListener);
    addDocumentListener();
    this.parameter = parameter;
    if (this.parameter != null) {
      this.parameter.addParameterChangedListener(new ParameterChangedEvent.Listener() {
        @Override
        public void parameterChanged(ParameterChangedEvent e) {
          updateGui();
        }
      });
    }
    updateGui();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    labelStatus = new javax.swing.JLabel();
    panelValue = new javax.swing.JPanel();
    textValue = new javax.swing.JTextField();
    buttonOpenDialog = new javax.swing.JButton();

    setMaximumSize(new java.awt.Dimension(32767, 43));

    labelStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gov/ca/water/shapelite/toolbox/resources/StatusGood.png")));

    panelValue.setBorder(javax.swing.BorderFactory.createTitledBorder(
        "Projection Name:"));
    textValue.setToolTipText("Projection Name");
    textValue.setEnabled(false);

    buttonOpenDialog.setText("...");
    buttonOpenDialog.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonOpenDialogActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout panelValueLayout = new javax.swing.GroupLayout(panelValue);
    panelValue.setLayout(panelValueLayout);
    panelValueLayout.setHorizontalGroup(
        panelValueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelValueLayout.createSequentialGroup()
            .addComponent(textValue, javax.swing.GroupLayout.DEFAULT_SIZE,
                328, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonOpenDialog)
            .addContainerGap())
    );
    panelValueLayout.setVerticalGroup(
        panelValueLayout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelValueLayout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(textValue, javax.swing.GroupLayout.PREFERRED_SIZE,
                20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(buttonOpenDialog))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE,
                32, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(panelValue, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(labelStatus, javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(panelValue, javax.swing.GroupLayout.PREFERRED_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.PREFERRED_SIZE)
    );
  }

  private void buttonOpenDialogActionPerformed(java.awt.event.ActionEvent evt) {
    ProjectionParameterSelectionPanel panel
        = new ProjectionParameterSelectionPanel();
    DialogResult result = this.getDialogHandler().showDialog(panelValue,
        "Select a projection:", DialogButtonType.OkCancel, null);
    if (result == DialogResult.OK) {
      this.textValue.setText(panel.getProjection().getName());
    }

  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonOpenDialog;
  private javax.swing.JLabel labelStatus;
  private javax.swing.JPanel panelValue;
  private javax.swing.JTextField textValue;
  // End of variables declaration//GEN-END:variables

  private void addDocumentListener() {
    textValue.getDocument().addDocumentListener(new DocumentListener() {

      @Override
      public void insertUpdate(DocumentEvent e) {
        updateParameter();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        updateParameter();
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        updateParameter();
      }
    });
  }

  protected void updateParameter() {
    if (getParameter() == null) {
      return;
    }
    getParameter().setParameterText(getValue());
    updateStatus();
    setToolTip(getParameter().getValidationMessage());
    labelStatus.setToolTipText(getParameter().getStatus().toString());
  }

  protected void updateStatus() {
    switch (getParameter().getStatus()) {
      case Good:
        labelStatus.setIcon(StatusIcon.getGood());

        break;
      case Warning:
        labelStatus.setIcon(StatusIcon.getWarning());
        break;
      case Error:
        labelStatus.setIcon(StatusIcon.getError());
        break;
    }
    labelStatus.repaint();
  }

  protected void setToolTip(String text) {
    this.setToolTipText(text);
    panelValue.setToolTipText(text);
    textValue.setToolTipText(text);
  }

  protected JLabel getLabelStatus() {
    return labelStatus;
  }

  protected JPanel getPanel() {
    return panelValue;
  }

  protected JTextField getTextField() {
    return textValue;
  }

  /**
   * Gets the title on the border around the text value.
   *
   * @return the String title
   */
  public String getTitle() {
    if (panelValue.getBorder() instanceof TitledBorder) {
      TitledBorder border = (TitledBorder) panelValue.getBorder();
      return border.getTitle();
    }
    return null;
  }

  /**
   * Sets the title on the border around the text value.
   *
   * @param title The String title to set.
   */
  public void setTitle(String title) {
    panelValue.setBorder(javax.swing.BorderFactory.createTitledBorder(title));
  }

  /**
   * Gets the String value for this parameter panel.
   *
   * @return String value.
   */
  public String getValue() {
    return textValue.getText();
  }

  /**
   * The actual text value of this parameter.
   *
   * @param value the string value.
   */
  public void setValue(String value) {
    textValue.setText(value);
  }

  @Override
  Parameter getParameter() {
    return parameter;
  }

  @Override
  void setParameter(Parameter param) {
    if (param instanceof ProjectionParameter) {
      parameter = (ProjectionParameter) param;
    }
  }

  /**
   * @return the dialogHandler
   */
  public DialogHandler getDialogHandler() {
    return dialogHandler;
  }

  /**
   * @param dialogHandler the dialogHandler to set
   */
  public void setDialogHandler(DialogHandler dialogHandler) {
    this.dialogHandler = dialogHandler;
  }

}
