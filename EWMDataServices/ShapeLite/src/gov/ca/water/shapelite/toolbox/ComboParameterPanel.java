/*
 * The MIT License
 *
 * Copyright 2014 hdunsford.
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

import gov.ca.water.shapelite.events.ComboOptionsChangedEvent;
import gov.ca.water.shapelite.events.ParameterChangedEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 * @param <T>
 */
public class ComboParameterPanel extends ParameterPanel {

  private ComboParameter parameter;

  private javax.swing.JComboBox<Object> comboItems;
  private javax.swing.JLabel labelStatus;
  private javax.swing.JPanel panelValue;

  /**
   * Creates new form ComboParameterPanel.
   *
   * @param param The ComboParameter that this panel affects.
   */
  public ComboParameterPanel(ComboParameter param) {
    initComponents();
    addActionListener();
    this.parameter = param;
    updateCombo();
    this.labelStatus.addMouseListener(helpClickListener);
    this.comboItems.addMouseListener(helpClickListener);
    this.panelValue.addMouseListener(helpClickListener);
    if (param != null) {
      param.addParameterChangedListener(new ParameterChangedEvent.Listener() {
        @Override
        public void parameterChanged(ParameterChangedEvent e) {
          comboItems.setSelectedItem(e.getParameterText());
        }
      });
      param.addComboOptionsChangedListener(new ComboOptionsChangedEvent.Listener() {
        @Override
        public void optionsChanged(ComboOptionsChangedEvent e) {
          updateCombo();
        }
      }
      );
    }
  }

  /**
   * Creates new form ComboParameterPanel.
   */
  public ComboParameterPanel() {
    initComponents();
    this.parameter = new ComboParameter();
    addActionListener();
    updateCombo();
  }

  private void addActionListener() {
    comboItems.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent ae) {
        updateParameter();
      }
    });
  }

  private void updateParameter() {
    if (parameter == null) {
      return;
    }
    updateStatus();
    setToolTip(parameter.getValidationMessage());
    labelStatus.setToolTipText(parameter.getStatus().toString());
    Object item = comboItems.getSelectedItem();
    parameter.setSelectedOption(item);

  }

  private void updateStatus() {
    switch (parameter.getStatus()) {
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

  }

  private void setToolTip(String text) {
    this.setToolTipText(text);
    panelValue.setToolTipText(text);
    comboItems.setToolTipText(text);
  }

  private void updateCombo() {
    comboItems.removeAllItems();
    Object value = parameter.getSelectedOption();
    for (Object item : parameter.getOptions()) {
      comboItems.addItem(item);
    }
    comboItems.setSelectedItem(value);
    setTitle(parameter.getParameterName());
    setToolTip(parameter.getDescription());
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
   * Initializes the components.
   */
  private void initComponents() {

    labelStatus = new javax.swing.JLabel();
    panelValue = new javax.swing.JPanel();
    comboItems = new javax.swing.JComboBox<>();

    setMaximumSize(new java.awt.Dimension(32767, 43));

    labelStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gov/ca/water/shapelite/toolbox/resources/StatusGood.png")));
    labelStatus.setText(null);

    panelValue.setBorder(javax.swing.BorderFactory.createTitledBorder(
        "Select:"));

    comboItems.setModel(new javax.swing.DefaultComboBoxModel<>());

    javax.swing.GroupLayout panelValueLayout = new javax.swing.GroupLayout(panelValue);
    panelValue.setLayout(panelValueLayout);
    panelValueLayout.setHorizontalGroup(
        panelValueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(comboItems, 0, 322, Short.MAX_VALUE)
    );
    panelValueLayout.setVerticalGroup(
        panelValueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelValueLayout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(comboItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(panelValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(panelValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(labelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
  }// </editor-fold>//GEN-END:initComponents

  @SuppressWarnings("rawtypes")
  // Variables declaration - do not modify//GEN-BEGIN:variables

  // End of variables declaration//GEN-END:variables
  /**
   * @return the parameter
   */
  @Override
  public ComboParameter getParameter() {
    return parameter;
  }

  /**
   * @param parameter the parameter to set
   */
  public void setParameter(ComboParameter parameter) {
    this.parameter = parameter;
    if (parameter == null) {
      return;
    }
    if (this.panelValue.getBorder() instanceof TitledBorder) {
      TitledBorder brd = (TitledBorder) this.panelValue.getBorder();
      brd.setTitle(parameter.getParameterName());
    }
    this.comboItems.setSelectedItem(parameter.getParameterText());
    this.setToolTipText(parameter.getDescription());
    setToolTip(parameter.getDescription());
  }

  @Override
  void setParameter(Parameter param) {
    if (param instanceof ComboParameter) {
      this.parameter = (ComboParameter) param;
      updateCombo();
    }

  }
}
