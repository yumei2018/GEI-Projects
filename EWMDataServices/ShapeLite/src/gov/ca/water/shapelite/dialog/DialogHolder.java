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
package gov.ca.water.shapelite.dialog;

import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.events.ValidChangedEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout.Alignment;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DialogHolder extends javax.swing.JDialog {

  /**
   * The left button.
   */
  private javax.swing.JButton buttonLeft;
  /**
   * The right button.
   */
  private javax.swing.JButton buttonRight;
  /**
   * The content JPanel to show.
   */
  private javax.swing.JPanel panelContent;
  /**
   * The panel holder panel.
   */
  private javax.swing.JPanel panelHolder;
  /**
   * The buttons.
   */
  private final DialogButtonType buttons;
  /**
   * The result from the dialog.
   */
  private DialogResult result;

  /**
   * Controls whether the validation is enabled.
   */
  private ValidationNotifier validation;

  /**
   * Creates new form DialogHolder.
   *
   * @param parent The parent frame.
   * @param modal Boolean modal.
   * @param panel The JPanel to show in the dialog.
   * @param buttons The button state to show.
   * @param notifier The tool to control whether ok is enabled.
   */
  public DialogHolder(java.awt.Frame parent, boolean modal, @NonNull JPanel panel,
      DialogButtonType buttons, ValidationNotifier notifier) {
    super(parent, modal);
    if (panel == null) {
      throw new IllegalArgumentException("Argument panel cannot be null.");
    }
    this.buttons = buttons;
    initComponents();
    this.panelContent.add(panel, BorderLayout.CENTER);
    this.panelHolder.setSize(panel.getWidth(), panel.getHeight() + 50);
    this.result = DialogResult.Cancel;
    this.validation = notifier;
    if (this.validation != null) {
      this.validation.addValidChangedListener(new ValidChangedEvent.Listener() {
        @Override
        public void validChanged(ValidChangedEvent e) {
          buttonRight.setEnabled(e.isValid());
        }
      });
    }

  }

  /**
   * Initializes the components and placement.
   */
  private void initComponents() {

    panelHolder = new JPanel();
    buttonRight = new JButton();
    buttonRight.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        switch (buttons) {
          case YesNo:
            result = DialogResult.Yes;
            break;
          default:
            result = DialogResult.OK;
        }
        DialogHolder.this.setVisible(false);
      }
    });
    buttonLeft = new JButton();
    buttonLeft.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        switch (buttons) {
          case YesNo:
            result = DialogResult.No;
            break;
          default:
            result = DialogResult.Cancel;
            break;

        }
        DialogHolder.this.setVisible(false);
      }
    });
    panelContent = new JPanel();
    panelContent.setBackground(Color.RED);
    panelContent.setLayout(new BorderLayout(20, 20));

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    javax.swing.GroupLayout panelHolderLayout = new javax.swing.GroupLayout(panelHolder);
    panelHolder.setLayout(panelHolderLayout);
    panelHolderLayout.setHorizontalGroup(panelHolderLayout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    panelHolderLayout.setVerticalGroup(panelHolderLayout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    switch (buttons) {
      case Ok:
        buttonLeft.setVisible(false);
        buttonRight.setText("Ok");
        break;
      case YesNo:
        buttonLeft.setVisible(true);
        buttonLeft.setText("No");
        buttonRight.setText("Yes");
        break;
      default:
        buttonLeft.setVisible(true);
        buttonLeft.setText("Cancel");
        buttonRight.setText("Ok");
    }

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(Alignment.LEADING)
        .addComponent(panelHolder, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(Alignment.TRAILING,
            layout.createSequentialGroup()
            .addContainerGap(274, Short.MAX_VALUE)
            .addComponent(buttonLeft)
            .addPreferredGap(ComponentPlacement.RELATED)
            .addComponent(buttonRight)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(panelHolder, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(BASELINE)
                .addComponent(buttonLeft)
                .addComponent(buttonRight))
            .addGap(20)
        )
    );

    pack();
  }

  /**
   * Gets the result of the dialog.
   *
   * @return the result
   */
  public final DialogResult getResult() {
    return result;
  }

}
