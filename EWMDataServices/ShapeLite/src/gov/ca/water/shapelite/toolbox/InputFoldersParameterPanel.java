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

import gov.ca.water.shapelite.events.ParameterChangedEvent;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class InputFoldersParameterPanel extends FilesParameterPanelBase {

  /**
   * The parameter for this panel.
   */
  InputFoldersParameter parameter;

  /**
   * Creates new form InputFileParameterPanel.
   */
  public InputFoldersParameterPanel() {
    parameter = new InputFoldersParameter();
    this.parameter.addParameterChangedListener(new ParameterChangedEvent.Listener() {
      @Override
      public void parameterChanged(ParameterChangedEvent e) {
        updateGui();
      }
    });
    this.fileMode = DIRECTORIES_ONLY;
    this.openMode = OPEN;
    updateGui();
  }

  /**
   * Creates a new instance of an InputFoldersParameterPanel from the specified
   * parameter.
   *
   * @param param The parameter to use.
   */
  public InputFoldersParameterPanel(InputFoldersParameter param) {
    if (this.parameter != null) {
      this.parameter.addParameterChangedListener(new ParameterChangedEvent.Listener() {
        @Override
        public void parameterChanged(ParameterChangedEvent e) {
          updateGui();
        }
      });
    }
    parameter = param;
    this.fileMode = DIRECTORIES_ONLY;
    this.openMode = OPEN;
    updateGui();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 456, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 48, Short.MAX_VALUE)
    );
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  // End of variables declaration//GEN-END:variables
  @Override
  InputFoldersParameter getParameter() {
    return parameter;
  }

  @Override
  void setParameter(Parameter param) {
    if (param instanceof InputFoldersParameter) {
      parameter = (InputFoldersParameter) param;
      updateGui();
    }

  }

  private void updateGui() {
    setTitle(parameter.getParameterName());
    updateTable();
    setToolTip(parameter.getDescription());
    updateStatus();
    repaint();
  }
}