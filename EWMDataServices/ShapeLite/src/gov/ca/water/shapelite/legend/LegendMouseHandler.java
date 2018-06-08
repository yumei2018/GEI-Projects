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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LegendMouseHandler extends MouseAdapter implements MouseMotionListener {

  LegendPanel legend;
  LegendItem shiftRangeStart;

  public LegendMouseHandler(LegendPanel legend) {
    this.legend = legend;
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (SwingUtilities.isLeftMouseButton(e)) {
      Optional<LegendItem> itemBox = legend.getLegendItem(e.getPoint());
      if (itemBox.isPresent()) {
        LegendItem item = itemBox.get();
        if (item.getUserObject() instanceof Layer<?>) {
          Layer<?> layer = (Layer<?>) item.getUserObject();
          // toggle expansion
          if (item.getExpandBox().contains(e.getPoint())) {
            boolean expanded = !item.getState().isExpanded();
            item.getState().setExpanded(expanded);
          }

          // toggle visibility
          if (item.getCheckBox().contains(e.getPoint())) {

            boolean visible = !layer.isVisible();
            layer.setVisible(visible);
            legend.getMap().getContent().paintImmediately();
            legend.getMap().repaint();
          }
        }
      }
    }
    // Toggle selection
    Optional<LegendItem> itemBox = legend.getLegendItem(e.getPoint());
    if (!itemBox.isPresent()) {
      // clear selection if we "selected" outside of any points.
      legend.clearSelection();
    } else {
      LegendItem item = itemBox.get();
      if (item.getTextBox().contains(e.getPoint())) {
        if (!e.isShiftDown() && !e.isControlDown()) {
          legend.clearSelection();
          item.getState().setSelected(true);
          item.getState().setHasFocus(true);
          shiftRangeStart = item;
        } else if (e.isControlDown()) {
                // with control click, just toggle selection of this one, ignoring
          // the rest of them
          item.getState().setSelected(!item.getState().isSelected());
          item.getState().setHasFocus(!item.getState().hasFocus());
          shiftRangeStart = item;
        } else if (e.isShiftDown()) {
          legend.clearSelection();
          if (shiftRangeStart.getUserObject() instanceof Layer<?>) {
            Layer<?> startLayer = (Layer<?>) shiftRangeStart.getUserObject();
            if (item.getUserObject() instanceof Layer<?>) {
              Layer<?> endLayer = (Layer<?>) item.getUserObject();
              // multi-select by layers, ignore symbols and content
              List<Layer<?>> selectedLayers = legend.getMap().getContent().getLayersBetween(startLayer, endLayer);
              for (LegendItem testItem : legend.getLegendItems()) {
                if (testItem.getUserObject() instanceof Layer<?>) {
                  if (selectedLayers.contains((Layer<?>) testItem.getUserObject())) {
                    testItem.getState().setSelected(true);
                  }
                }
              }
              item.getState().setHasFocus(true);
            }
          } else if (shiftRangeStart.getUserObject() instanceof LegendSymbol) {
            LegendSymbol startSymbol = (LegendSymbol) shiftRangeStart.getUserObject();
            if (item.getUserObject() instanceof LegendSymbol) {
              LegendSymbol endSymbol = (LegendSymbol) item.getUserObject();
              Layer<?> startLayer = startSymbol.getParent();
              Layer<?> endLayer = endSymbol.getParent();
              if (startLayer == endLayer) {
                // If the symbols are in the same layer, work like shift select, otherwise, like regular select.
                List<LegendSymbol> selectedSymbols = startLayer.getLegendSymbolsBetween(startSymbol, endSymbol);
                for (LegendItem testItem : legend.getLegendItems()) {
                  if (testItem.getUserObject() instanceof LegendSymbol) {
                    if (selectedSymbols.contains((LegendSymbol) testItem.getUserObject())) {
                      testItem.getState().setSelected(true);
                    }
                  }
                }
              }
            }
          } else {
            legend.clearSelection();
            item.getState().setSelected(true);
            shiftRangeStart = item;
          }
          item.getState().setHasFocus(true);
        }
      }

    }
    legend.repaint();

  }
}
