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
import gov.ca.water.shapelite.map.LegendSymbol;
import gov.ca.water.shapelite.map.layer.Layer;
import gov.ca.water.shapelite.map.MapPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * Getting the JTree to support the re-ordering feature I wanted was very
 * complicated and didn't work correctly, so this is a very simplified approach
 * that caters directly to the map.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LegendPanel extends JPanel {

  private LegendCellRenderer cellRenderer;
  private MapPanel map;
  private int rowPadding;
  private int indent;
  private final List<LegendItem> legendItems;
  private final HashMap<Object, LegendState> legendStates;
  private int insertY;

  private static BufferedImage plus;
  private static BufferedImage minus;

  static {

    try {
      plus = ImageIO.read(LegendItemRenderer.class.getResourceAsStream(
              "resources/Plus.png"));

    } catch (IOException ex) {
      Logger.getLogger(LegendPanel.class.getName()).log(
              Level.SEVERE, ex.getMessage(), ex);

    }
    try {
      minus = ImageIO.read(LegendItemRenderer.class.getResourceAsStream(
              "resources/Minus.png"));
    } catch (IOException ex) {
      Logger.getLogger(LegendPanel.class.getName()).log(
              Level.SEVERE, ex.getMessage(), ex);

    }

  }

  public LegendPanel() {
    cellRenderer = new DefaultLegendCellRenderer();
    rowPadding = 4;
    indent = 20;
    legendItems = new ArrayList<>();
    legendStates = new HashMap<>();
    map = new MapPanel();
    LegendMouseHandler handler = new LegendMouseHandler(this);
    this.addMouseListener(handler);
    this.addMouseMotionListener(handler);
    insertY = -1;
  }

  private final List<CheckChangedListener> checkChangedEventListeners = new ArrayList<>();

  /**
   * Ensures the specified listener will receive the help event.
   *
   * @param listener The listener to connect.
   */
  public void addCheckChangedListener(CheckChangedListener listener) {
    checkChangedEventListeners.add(listener);
  }

  /**
   * Removes the specified listener form the list if it is in the list.
   *
   * @param listener The listener to disconnect.
   */
  public void removeCheckChangedListener(CheckChangedListener listener) {
    checkChangedEventListeners.remove(listener);
  }

  /**
   * Fires the showHelp event.
   *
   * @param e An EventArgument with the help content to show.
   */
  public void fireCheckChanged(CheckChangedEvent e) {
    for (CheckChangedListener listener : checkChangedEventListeners) {
      listener.checkChanged(e);
    }
  }

  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);
    g.setColor(this.getBackground());
    g.fillRect(0, 0, this.getWidth(), this.getHeight());
    if (map == null) {
      return;
    }

    paintLegendItems(g);

    if (insertY > -1) {
      Graphics2D g2 = (Graphics2D) g.create(); // duplicate will not affect original
      g2.setStroke(new BasicStroke(2));
      g2.setColor(Color.BLACK);
      g2.drawLine(this.indent, this.insertY, this.getWidth(), this.insertY);
    }

  }

  /**
   * This cycles through all the legend items, getting a rendering component for
   * each item, and then painting that component.
   *
   * @param g
   */
  protected void paintLegendItems(Graphics g) {

    LegendState state = getState(map.getContent());
    int row = 0;
    int y = 0;
    Component component = cellRenderer.getRootRendererComponent(this, map.getContent(), row, state);
    Dimension size = component.getPreferredSize();
    component.setSize(size);
    component.setLocation(0, 0);
    component.printAll(g);
    legendItems.clear();
    addItem(map.getContent(), size, state, y);
    int dy = size.height + rowPadding;
    y += dy;
    g.translate(0, dy);
    row++;
    if (state.isExpanded()) {
      g.translate(indent, 0);
      for (Layer<?> layer : map.getContent().getAllLayers()) {
        if (!layer.isVisibleInLegend()) {
          continue;
        }
        LegendState layerState = getState(layer);

        component = cellRenderer.getLayerRendererComponent(this, layer, row, layerState);
        size = component.getPreferredSize();
        if (y + size.height > 1000) {
          return;
        }
        int expX = -minus.getWidth();
        int expY = (size.height - minus.getHeight()) / 2;
        if (layerState.isExpanded() && minus != null) {
          g.drawImage(minus, expX, expY, null);
        } else if (!layerState.isExpanded() && plus != null) {
          g.drawImage(plus, expX, expY, null);
        }
        component.setSize(size);
        component.printAll(g);

        addItem(layer, size, layerState, y);
        dy = size.height + rowPadding;
        y += dy;
        g.translate(0, dy);
        row++;
        if (layerState.isExpanded()) {
          g.translate(indent, 0);
          for (LegendSymbol symbol : layer.getLegendSymbols()) {
            LegendState symbolState = getState(symbol);
            component = cellRenderer.getSymbolRendererComponent(this, symbol, row, symbolState);
            if (y + component.getPreferredSize().height > 1000) {
              return;
            }

            size = component.getPreferredSize();
            component.setSize(size);
            component.printAll(g);
            addItem(symbol, size, symbolState, y);
            dy = size.height + rowPadding;
            y += dy;
            g.translate(0, dy);
            row++;
          }
          g.translate(-indent, 0);
        }
      }
    }
  }

  /**
   * Gets the legend item for the associated y index, or null.
   *
   * @param y the integer position from the top of the entire panel, which may
   * or may not currently be visible.
   * @return The LegendItem for the vertical y position.
   */
  public final Optional<LegendItem> getLegendItem(int y) {
    for (LegendItem item : legendItems) {
      if (item.getBounds().contains(10, y)) {
        return Optional.of(item);
      }
    }
    return Optional.empty();
  }

  /**
   * Gets the optional LegendItem for the specified point.  This will
   * return an empty optional if there is no item at that point.
   * @param point The point in legend client pixel coordinates.
   * @return The Legend Item.
   */
  public Optional<LegendItem> getLegendItem(Point point) {
    return getLegendItem(point.y);
  }

  private void addItem(Object item, Dimension size, LegendState state, int y) {
    LegendItem legendItem = new LegendItem();
    legendItem.setState(state);
    legendItem.setUserObject(item);
    legendItem.setBounds(new Rectangle(0, y, size.width, size.height));
    legendItem.setExpandBox(new Rectangle(5, y, 10, 16));
    legendItem.setCheckBox(new Rectangle(20, y, 16, 16));
    legendItem.setTextBox(new Rectangle(36, y, this.getWidth(), size.height));
    legendItems.add(legendItem);
  }

  /**
   * Clears the selection by changing the selected state of all the current
   * legend items.
   */
  public void clearSelection() {
    for (LegendItem item : legendItems) {
      item.getState().setSelected(false);
      item.getState().setHasFocus(false);
    }
    this.repaint();
  }

  public List<LegendItem> getLegendItems(LegendItem start, LegendItem end) {
    int startIndex = legendItems.indexOf(start);
    int endIndex = legendItems.indexOf(end);
    if (startIndex > endIndex) {
      int temp = endIndex;
      endIndex = startIndex;
      startIndex = temp;
    }
    List<LegendItem> result = new ArrayList<>();
    for (int i = startIndex; i <= endIndex; i++) {
      result.add(legendItems.get(i));
    }
    return result;
  }

  /**
   * Gets or creates a LegendState for storing an objects state.
   *
   * @param object
   * @return
   */
  private LegendState getState(Object object) {
    LegendState result;
    if (legendStates.containsKey(object)) {
      result = legendStates.get(object);
    } else {
      result = new LegendState();
      if (object instanceof Layer) {
        result.setLeaf(false);  // MapLayers have symbols
      }
      legendStates.put(object, result);

    }
    return result;
  }

  /**
   * Gets the cell renderer class that actually draws content for each legend
   * item.
   *
   * @return the cellRenderer
   */
  public LegendCellRenderer getCellRenderer() {
    return cellRenderer;
  }

  /**
   * Sets the cell renderer class that actually draws content for each legend
   * item.
   *
   * @param cellRenderer the cellRenderer to set
   */
  public void setCellRenderer(LegendCellRenderer cellRenderer) {
    this.cellRenderer = cellRenderer;
  }

  /**
   * Gets the Map, who's layers should be drawn to this.
   *
   * @return the map
   */
  public MapPanel getMap() {
    return map;
  }

  /**
   * Sets the map.
   *
   * @param map the map to set
   */
  public void setMap(MapPanel map) {
    this.map = map;
  }

  /**
   * Gets the integer row padding between each legend item beyond the preferred
   * size of that legend item.
   *
   * @return the rowPadding
   */
  public int getRowPadding() {
    return rowPadding;
  }

  /**
   * Sets the integer row padding between each legend item beyond the preferred
   * size of that legend item.
   *
   * @param rowPadding the rowPadding to set
   */
  public void setRowPadding(int rowPadding) {
    this.rowPadding = rowPadding;
  }

  /**
   * Gets the number of pixels of indentation
   *
   * @return the indent
   */
  public int getIndent() {
    return indent;
  }

  /**
   * Sets the number of pixels to indent between layers.
   *
   * @param indent the indent to set
   */
  public void setIndent(int indent) {
    this.indent = indent;
  }

  /**
   * @return the legendItems
   */
  public List<LegendItem> getLegendItems() {
    return legendItems;
  }

  /**
   * @return the insertY
   */
  public int getInsertY() {
    return insertY;
  }

  /**
   * @param insertY the insertY to set
   */
  public void setInsertY(int insertY) {
    this.insertY = insertY;
  }

}
