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

import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.map.MapPanel;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Legend extends javax.swing.JPanel {

  /**
   * Creates new form Legend
   */
  public Legend() {
    initComponents();

  }

  /**
   * Ensures the specified listener will receive the help event.
   *
   * @param listener The listener to connect.
   */
  public void addCheckChangedListener(CheckChangedListener listener) {
    if (this.legendPanel1 != null) {
      this.legendPanel1.addCheckChangedListener(listener);
    }

  }

  /**
   * Removes the specified listener form the list if it is in the list.
   *
   * @param listener The listener to disconnect.
   */
  public void removeCheckChangedListener(CheckChangedListener listener) {
    if (this.legendPanel1 != null) {
      this.legendPanel1.removeCheckChangedListener(listener);
    }

  }

  /**
   * This controls for scrolling by considering the view rectangle to retrieve a
   * LegendItem class where the rectangles are in client coordinates.
   *
   * @param point A Point representing the position on this outer control.
   * @return The LegendItem that corresponds to this point.
   */
  public Optional<LegendItem> getLegendItem(Point point) {
    Rectangle rect = this.jScrollPane1.getViewport().getViewRect();
    int y = point.y + rect.y;
    Optional<LegendItem> itemBox = this.legendPanel1.getLegendItem(y);
    if(!itemBox.isPresent()){
      return Optional.empty();
    }
    LegendItem item = itemBox.get().copy();
    item.getBounds().y = item.getBounds().y - rect.y;
    item.getBounds().x = item.getBounds().x - rect.x;
    item.getCheckBox().y = item.getCheckBox().y - rect.y;
    item.getCheckBox().x = item.getCheckBox().x - rect.x;
    item.getExpandBox().y = item.getExpandBox().y - rect.y;
    item.getExpandBox().x = item.getExpandBox().x - rect.x;
    return Optional.of(item);
  }

  /**
   * Gets the optional legend item with the specified name. If the name is not
   * found, the optional return type will be empty.
   *
   * @param name The string name of the legend item to get.
   * @return The Optional legend item which may be empty.
   */
  public final Optional<LegendItem> getLegendItem(String name) {
    for (LegendItem legendItem : this.legendPanel1.getLegendItems()) {
      if (legendItem.getUserObject().toString().equals(name)) {
        return Optional.of(legendItem);
      }
    }
    return Optional.empty();
  }

  /**
   * Gets the cell renderer class that actually draws content for each legend
   * item.
   *
   * @return the cellRenderer
   */
  public LegendCellRenderer getCellRenderer() {
    if (this.legendPanel1 != null) {
      return this.legendPanel1.getCellRenderer();
    }
    return null;
  }

  /**
   * Sets the cell renderer class that actually draws content for each legend
   * item.
   *
   * @param cellRenderer the cellRenderer to set
   */
  public void setCellRenderer(LegendCellRenderer cellRenderer) {
    if (this.legendPanel1 != null) {
      this.legendPanel1.setCellRenderer(cellRenderer);
    }
  }

  /**
   * Gets the Map, who's layers should be drawn to this.
   *
   * @return the map
   */
  public MapPanel getMap() {
    if (this.legendPanel1 != null) {

      return this.legendPanel1.getMap();

    }
    return null;
  }

  /**
   * Sets the map.
   *
   * @param map the map to set
   */
  public void setMap(MapPanel map) {
    if (this.legendPanel1 != null) {
      this.legendPanel1.setMap(map);
    }
  }

  /**
   * Gets the integer row padding between each legend item beyond the preferred
   * size of that legend item.
   *
   * @return the rowPadding
   */
  public int getRowPadding() {
    if (this.legendPanel1 != null) {
      return this.legendPanel1.getRowPadding();
    }
    return 0;
  }

  /**
   * Sets the integer row padding between each legend item beyond the preferred
   * size of that legend item.
   *
   * @param rowPadding the rowPadding to set
   */
  public void setRowPadding(int rowPadding) {
    if (this.legendPanel1 != null) {
      this.legendPanel1.setRowPadding(rowPadding);
    }
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        legendPanel1 = new gov.ca.water.shapelite.legend.LegendPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(204, 255, 255));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 400));

        legendPanel1.setBackground(new java.awt.Color(255, 255, 255));
        legendPanel1.setMinimumSize(new java.awt.Dimension(250, 400));
        legendPanel1.setName(""); // NOI18N
        legendPanel1.setPreferredSize(new java.awt.Dimension(250, 400));

        javax.swing.GroupLayout legendPanel1Layout = new javax.swing.GroupLayout(legendPanel1);
        legendPanel1.setLayout(legendPanel1Layout);
        legendPanel1Layout.setHorizontalGroup(
            legendPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        legendPanel1Layout.setVerticalGroup(
            legendPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(legendPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private gov.ca.water.shapelite.legend.LegendPanel legendPanel1;
    // End of variables declaration//GEN-END:variables
}
