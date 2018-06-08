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
package gov.ca.water.shapelite.legend;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.UIManager;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LegendItemPanel extends javax.swing.JPanel {

  /**
   * A check box that determines if the layer should be visible.
   */
  private javax.swing.JCheckBox checkVisible;
  /**
   * A label that shows the text of this legend item.
   */
  private gov.ca.water.shapelite.legend.LegendItemLabel labelText;
  
  
  /**
   * Creates new form LegendItem.
   */
  public LegendItemPanel() {
    initComponents();
    //checkVisible.setBackground(UIManager.getColor("Tree.textBackground"));
    labelText.setForeground(UIManager.getColor("Tree.textForeground"));
  }

  public void setChecked(boolean checked) {
    checkVisible.setSelected(checked);
  }

  public boolean isChecked() {
    return checkVisible.isSelected();
  }

  public LegendItemLabel getLabel() {
    return this.labelText;
  }

  @Override
  public Dimension getPreferredSize() {
    Dimension dimensionCheck = checkVisible.getPreferredSize();
    Dimension dimensionText = labelText.getPreferredSize();
    int width = dimensionCheck.width + dimensionText.width;
    int height = (Math.max(dimensionCheck.height, dimensionText.height));
    return new Dimension(width, height);
  }

  @Override
  public void doLayout() {
    Dimension d_checkVisible = checkVisible.getPreferredSize();
    Dimension d_labelText = labelText.getPreferredSize();
    int y_checkVisible = 0;
    int y_labelText = 0;
    if (d_checkVisible.height < d_labelText.height) {
      y_checkVisible = (d_labelText.height - d_checkVisible.height) / 2;
    } else {
      y_labelText = (d_checkVisible.height - d_labelText.height) / 2;
    }

    checkVisible.setLocation(0, y_checkVisible);
    checkVisible.setBounds(0, y_checkVisible, d_checkVisible.width, d_checkVisible.height - 5);
    labelText.setLocation(d_checkVisible.width, y_labelText);
    labelText.setBounds(d_checkVisible.width, y_labelText, d_labelText.width, d_labelText.height);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    checkVisible = new javax.swing.JCheckBox();
    labelText = new gov.ca.water.shapelite.legend.LegendItemLabel();

    setBackground(Color.WHITE);

    checkVisible.setBackground(Color.WHITE);
    checkVisible.setText(null);
    checkVisible.setToolTipText(null);

    labelText.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 5, 2, 2));
    labelText.setText("legendItemLabel1");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(checkVisible)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(labelText, javax.swing.GroupLayout.DEFAULT_SIZE,
                            112, Short.MAX_VALUE)
                    .addGap(18, 18, 18))
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(checkVisible, javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelText, javax.swing.GroupLayout.Alignment.LEADING,
                    javax.swing.GroupLayout.PREFERRED_SIZE, 21, 
                    javax.swing.GroupLayout.PREFERRED_SIZE)
    );
  }


}
