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
package gov.ca.water.shapelite.legend.internal;

import gov.ca.water.shapelite.dialog.DialogButtonType;
import gov.ca.water.shapelite.dialog.DialogResult;
import gov.ca.water.shapelite.events.MapLayerChangedEvent;
import gov.ca.water.shapelite.map.layer.Layer;
import gov.ca.water.shapelite.map.layer.PointLayer;
import gov.ca.water.shapelite.map.layer.LineLayer;
import gov.ca.water.shapelite.map.layer.PolygonLayer;
import gov.ca.water.shapelite.map.MapPanel;
import gov.ca.water.shapelite.map.ObservedListEvent;
import gov.ca.water.shapelite.map.ObservedListListener;
import gov.ca.water.shapelite.symbology.PointSymbolizer;
import gov.ca.water.shapelite.symbology.LineSymbolizer;
import gov.ca.water.shapelite.symbology.PolygonSymbolizer;
import gov.ca.water.shapelite.symbology.gui.SymbolizerPanelMarker;
import gov.ca.water.shapelite.symbology.gui.SymbolizerPanelMarkerPath;
import gov.ca.water.shapelite.symbology.gui.SymbolizerPanelMarkerPolygon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.SwingUtilities;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LegendPanel extends javax.swing.JPanel {

  /**
   * A very light gray, almost white.
   */
  private static final Color OFF_WHITE = new Color(240, 240, 240);

  /**
   * The fixed width of the panel in pixels.
   */
  private static final int DEFAULT_WIDTH = 150;

  /**
   * The fixed final height of the panel.
   */
  private static final int MAX_HEIGHT = 200;

  /**
   * The map that this legend panel represents.
   */
  private MapPanel map;

  /**
   * The list of legend items shown in this panel.
   */
  private final List<LegendItem> legendItems;

  /**
   * The popup menu shown when right clicking on one of the items.
   */
  private final LegendPopup legendPopup;

  /**
   * The current legend item that is being clicked on or interacted with.
   */
  private LegendItem currentItem;

  /**
   * The list changed listener.
   */
  private final ObservedListListener listChangedListener;

  /**
   * The anchor item for shift selection.
   */
  private LegendItem anchor;

  /**
   * Creates new form LegendPanel.
   */
  public LegendPanel() {
    super.setBackground(OFF_WHITE);
    super.setSize(DEFAULT_WIDTH, MAX_HEIGHT);
    super.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
    super.setLayout(layout);

    super.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        redispatchToMap(e);
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        redispatchToMap(e);
      }
    });
    super.addMouseMotionListener(new MouseMotionListener() {
      @Override
      public void mouseDragged(MouseEvent e) {
        redispatchToMap(e);
      }

      @Override
      public void mouseMoved(MouseEvent e) {
        redispatchToMap(e);
      }
    });
    legendItems = new ArrayList<>();
    legendPopup = new LegendPopup();
    legendPopup.getProperties().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (currentItem == null) {
          return;
        }
        if (currentItem.getLayer() instanceof PointLayer) {
          PointLayer layer = (PointLayer) currentItem.getLayer();
          SymbolizerPanelMarker panel = new SymbolizerPanelMarker();
          PointSymbolizer symbolizerMarker = layer.getDefaultSymbolizer();
          panel.setSymbolizer(symbolizerMarker.copy());
          panel.setSize(385, 173);
          Point loc = currentItem.getLocation();
          SwingUtilities.convertPointToScreen(loc, currentItem);
          DialogResult result = map.getDialogHandler().showDialog(panel,
              currentItem.getLayer().getName(), DialogButtonType.OkCancel, null, loc);
          if (result == DialogResult.OK) {
            layer.setDefaultSymbolizer(panel.getSymbolizer());
            map.getContent().paintImmediately();
            map.repaint();
          }

        } else if (currentItem.getLayer() instanceof LineLayer) {
          LineLayer layer = (LineLayer) currentItem.getLayer();
          SymbolizerPanelMarkerPath panel = new SymbolizerPanelMarkerPath();
          LineSymbolizer symbolizerMarkerPath = layer.getDefaultSymbolizer();
          panel.setSymbolizer(symbolizerMarkerPath.copy());
          panel.setSize(309, 130);
          Point loc = currentItem.getLocation();
          SwingUtilities.convertPointToScreen(loc, currentItem);
          DialogResult result = map.getDialogHandler().showDialog(panel,
              currentItem.getLayer().getName(), DialogButtonType.OkCancel, null, loc);
          if (result == DialogResult.OK) {
            layer.setDefaultSymbolizer(panel.getSymbolizer());
            map.getContent().paintImmediately();
            map.repaint();
          }
        } else if (currentItem.getLayer() instanceof PolygonLayer) {
          PolygonLayer layer = (PolygonLayer) currentItem.getLayer();
          SymbolizerPanelMarkerPolygon panel = new SymbolizerPanelMarkerPolygon();
          panel.setFields(layer.getDataset().getFields());
          PolygonSymbolizer symbolizerMarkerPolygon = layer.getDefaultSymbolizer();
          panel.setSymbolizer(symbolizerMarkerPolygon.copy());
          panel.setSize(344, 189);
          Point loc = currentItem.getLocation();
          SwingUtilities.convertPointToScreen(loc, currentItem);
          DialogResult result = map.getDialogHandler().showDialog(panel,
              currentItem.getLayer().getName(), DialogButtonType.OkCancel, null, loc);
          if (result == DialogResult.OK) {
            layer.setDefaultSymbolizer(panel.getSymbolizer());
            map.getContent().paintImmediately();
            map.repaint();
          }
        }

      }
    });
    legendPopup.getRemove().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (currentItem == null) {
          return;
        }
        map.getContent().getLayers().remove(currentItem.getLayer());
      }
    });

    listChangedListener = new ObservedListListener() {
      @Override
      public void listChanged(ObservedListEvent<?> evt) {
        doLayout();
      }
    };
    super.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        int width = getWidth();
        for (LegendItem item : legendItems) {
          item.setSize(width, item.getHeight());
        }
        doLayout();
      }

    });

  }

  /**
   * If a mouse event is not captured by an actual legend item, let it fall to
   * the containing tool.
   *
   * @param e
   */
  public void redispatchToMap(MouseEvent e) {
    Component source = (Component) e.getSource();
    MouseEvent parentEvent = SwingUtilities.convertMouseEvent(source, e, map);
    map.dispatchEvent(parentEvent);
  }

  /**
   * Draws the content for this panel.
   *
   * @param g The graphics object to draw.
   */
  @Override
  protected final void paintComponent(Graphics g) {
    g.setColor(this.getBackground());
    g.fillRect(0, 0, this.getWidth(), this.getHeight());
    boolean stop = true;
  }

  @Override
  public void printAll(Graphics g) {
    super.printAll(g); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void doLayout() {
    updateLegendItems();
    super.doLayout();
  }

  /**
   * Ensure that the legend items are currently consistent with the map.
   */
  private void updateLegendItems() {
    List<Layer<?>> layers = getVisibleLayers();
    if (!layersChanged(layers)) {
      return;
    }
    // remove components from panel.
    for (LegendItem item : this.legendItems) {
      remove(item);
    }
    // clear the list of legend items.
    this.legendItems.clear();
    super.removeAll();
    int height = 0;
    // Add back components that work with the current layers.
    for (Layer<?> layer : layers) {
      LegendItem item = null;
      if (layer instanceof LegendItemSource){
        item = ((LegendItemSource) layer).getLegendItem();
      }
      if (item == null) {
        item = new LegendItem(layer);
      }

      this.legendItems.add(item);
      height += item.getHeight();
      if (height > this.getHeight()) {
        break;
      }

      // To Do: attach listeners (if any)
      item.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) {
          if (!(e.getSource() instanceof LegendItem)) {
            return;
          }
          LegendItem item = (LegendItem) e.getSource();
          currentItem = item;
          if (!e.isShiftDown()) {
            anchor = item;
          }
          if (SwingUtilities.isLeftMouseButton(e)) {
            if (e.isControlDown()) {
              boolean selected = item.getLayer().isSelectedInLegend();
              item.getLayer().setSelectedInLegend(!selected);
            } else {
              if (e.isShiftDown() && anchor != null) {
                int start = legendItems.indexOf(anchor);
                int end = legendItems.indexOf(item);
                boolean selected;
                for (int i = 0; i < legendItems.size(); i++) {
                  selected = (i >= start || i >= end) && !(i > start && i > end);
                  legendItems.get(i).getLayer().setSelectedInLegend(selected);
                }
              } else {
                for (int i = 0; i < legendItems.size(); i++) {
                  legendItems.get(i).getLayer().setSelectedInLegend(false);
                }
              }
              item.getLayer().setSelectedInLegend(true);
            }
          }
          if (SwingUtilities.isRightMouseButton(e)) {
            for (int i = 0; i < legendItems.size(); i++) {
              legendItems.get(i).getLayer().setSelectedInLegend(false);
            }
            item.getLayer().setSelectedInLegend(true);
          }

          if (SwingUtilities.isRightMouseButton(e)) {
            legendPopup.show(item, e.getX(), e.getY());
          }
          repaint();
        }
      });

      item.addMapLayerChangedListener(
          new MapLayerChangedEvent.Listener() {
        @Override
        public void mapLayerChanged(MapLayerChangedEvent e
        ) {
          if (map != null) {
            map.getContent().paintImmediately();
            map.repaint();
            repaint();
          }
        }
      }
      );
      super.add(item);
    }
  }

  /**
   * Check if the layers have been updated.
   *
   * @return Boolean, true if the layers are different.
   */
  private boolean layersChanged(List<Layer<?>> mapLayers) {
    if (mapLayers.size() != legendItems.size()) {
      return true;
    }
    for (int i = 0; i < mapLayers.size(); i++) {
      if (mapLayers.get(i) != legendItems.get(i).getLayer()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Gets the visible layers from the map in the order they would appear.
   *
   * @return The list of layers.
   */
  private List<Layer<?>> getVisibleLayers() {
    List<Layer<?>> result = new ArrayList<>();
    if (this.map == null || this.map.getContent().getLayers().isEmpty()) {
      return result;
    }
    int lastLayer = map.getContent().getLayers().size() - 1;
    for (int i = lastLayer; i >= 0; i--) {
      Layer<?> layer = map.getContent().getLayers().get(i);
      if (!layer.isVisibleInLegend()) {
        continue;
      }
      result.add(layer);
    }
    return result;
  }

  /**
   * Gets the map that this legend panel represents.
   *
   * @return the map
   */
  public final MapPanel getMap() {
    return map;
  }

  /**
   * Sets the map that this legend panel represents.
   *
   * @param map the map to set
   */
  public final void setMap(MapPanel map) {
    if (this.map != null) {
      this.map.getContent().getLayers().removeListener(listChangedListener);
    }
    this.map = map;
    this.map.getContent().getLayers().addListener(listChangedListener);
    doLayout();
  }

  /**
   * @return the legendPopup
   */
  public LegendPopup getLegendPopup() {
    return legendPopup;
  }

}
