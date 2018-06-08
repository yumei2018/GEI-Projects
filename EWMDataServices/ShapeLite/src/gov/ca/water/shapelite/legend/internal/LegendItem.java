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

import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.events.MapLayerChangedEvent;
import gov.ca.water.shapelite.legend.resources.LegendImages;
import gov.ca.water.shapelite.map.layer.Layer;
import gov.ca.water.shapelite.symbology.ColorMap;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JComponent;
import javax.swing.UIManager;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LegendItem extends JComponent {

  /**
   * The default height of the legend item.
   */
  private static final int ITEM_HEIGHT = 20;

  /**
   * The default width of the legend item.
   */
  private static final int DEFUALT_WIDTH = 200;

  /**
   * The maximum width.
   */
  private static final int MAX_WIDTH = 1000;

  /**
   * The background color to use if something is selected.
   */
  private static final Color SELECT_BACK = UIManager.getColor("Tree.selectionBackground");

  /**
   * The pixel padding between elements.
   */
  private static final int PAD = 3;

  /**
   * The layer being drawn by this legend item.
   */
  final Layer<?> layer;

  /**
   * The rectangle bounds for the icon.
   */
  private Rectangle rectangleIcon;

  /**
   * The rectangle bounds for the checkbox.
   */
  private Rectangle rectangleCheck;

  /**
   * The vertical text alignment for the text on this control.
   */
  private VAlignment verticalAlignment;

  /**
   * Creates a new LegendItem from the layer.
   *
   * @param mapLayer The layer to use for this class. This cannot be null.
   */
  public LegendItem(@NonNull Layer<?> mapLayer) {
    if (mapLayer == null) {
      throw new IllegalArgumentException("The argument layer cannot be null.");
    }
    verticalAlignment = VAlignment.Middle;
    this.layer = mapLayer;
    super.setVisible(true);
    super.setBackground(Color.red);
    super.setSize(DEFUALT_WIDTH, ITEM_HEIGHT);
    super.setMaximumSize(new Dimension(MAX_WIDTH, ITEM_HEIGHT));
    super.setMinimumSize(new Dimension(0, ITEM_HEIGHT));
    super.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent e) {
        if (rectangleCheck != null && rectangleCheck.contains(e.getPoint())) {
          layer.setVisible(!layer.isVisible());
          repaint();
          fireMapLayerChanged(new MapLayerChangedEvent(LegendItem.this, layer));
        }
        super.mouseReleased(e);
      }
    });

  }

  //<editor-fold defaultstate="collapsed" desc="MapLayerChangedEvent">
  /**
   * The list of listeners for the MapLayerChanged event.
   */
  private final List<MapLayerChangedEvent.Listener> mapLayerChangedListeners
          = new ArrayList<>();

  /**
   * Adds the specified listener to the list of listeners to be notified during an event.
   * If the item is already in the list, it will not be added a second time.
   *
   * @param listener The MapLayerChangedEvent.Listener to connect.
   */
  public final void addMapLayerChangedListener(MapLayerChangedEvent.Listener listener) {
    if (!mapLayerChangedListeners.contains(listener)) {
      mapLayerChangedListeners.add(listener);
    }
  }

  /**
   * Removes the specified listener from the list if it is in the list.
   *
   * @param listener The MapLayerChangedEvent.Listener to disconnect.
   */
  public final void removeMapLayerChangedListener(MapLayerChangedEvent.Listener listener) {
    mapLayerChangedListeners.remove(listener);
  }

  /**
   * Fires the MapLayerChanged event and notifies each of the listeners.
   *
   * @param e A {EventType}Event with the source object and any properties associated with
   * this event.
   */
  public final void fireMapLayerChanged(MapLayerChangedEvent e) {
    for (MapLayerChangedEvent.Listener listener : mapLayerChangedListeners) {
      listener.mapLayerChanged(e);
    }
  }

  //</editor-fold>
  /**
   * Paints the content for the layer.
   *
   * @param g The graphics element.
   */
  public void paintComponent(Graphics g) {
    BufferedImage check;
    int height = this.getSize().height;
    g.setClip(0, 0, getWidth(), getHeight());
    if (layer.isVisible()) {
      check = LegendImages.getCheckedImage();

    } else {
      check = LegendImages.getUncheckedImage();
    }

    String text = layer.getName();
    if (text == null) {
      return;
    }
    FontMetrics metrics = g.getFontMetrics();
    int textHeight = metrics.getHeight();
    int textWidth = metrics.stringWidth(text);

    int yTextRect = 0;
    int yText = 0;
    int yCheck = 0;
    switch (verticalAlignment) {
      case Middle:
        yTextRect = height / 2 - (textHeight + 2) / 2;
        if (yTextRect < 0) {
          yTextRect = 0;
        }
        yCheck = height / 2 - check.getHeight() / 2;
        yText = height / 2 + textHeight / 2 - 2;
        break;
      case Bottom:
        yTextRect = height - (textHeight + 2);
        yText = height - 1;
        yCheck = height - check.getHeight();
        if(check.getHeight() < textHeight){
          yCheck = yTextRect + (textHeight - check.getHeight())/2;
        }

        break;
      case Top:
        yTextRect = 0;
        yText = textHeight + 1;
        yCheck = 0;
        if(check.getHeight() < textHeight){
          yCheck = (textHeight - check.getHeight())/2;
        }
    }
    int x = PAD;
    if (check != null) {
      rectangleCheck = new Rectangle(x, yCheck, check.getWidth(), check.getHeight());
      g.drawImage(check, x, yCheck, null);
      x += check.getWidth();
    }
    x += PAD;
    if (layer.getDefaultSymbolizer() != null) {
      BufferedImage icon = layer.getDefaultSymbolizer().getLegendIcon();
      if (icon != null) {
        int y = (int) (height / 2.0 - icon.getHeight() / 2);
        int iconHeight = Math.min(icon.getHeight(), height);
        rectangleIcon = new Rectangle(x, y, icon.getWidth(), icon.getHeight());
        g.drawImage(icon, x, y, icon.getWidth(), iconHeight, null);
        x += icon.getWidth();
        x += PAD;
      }
    }
    if (layer.isSelectedInLegend()) {
      g.setColor(SELECT_BACK);
      g.fillRect(x, yTextRect, textWidth + 10, textHeight + 2);
      g.setColor(Color.WHITE);
    } else {
      g.setColor(Color.BLACK);
    }
    g.drawString(text, x + 5, yText);
  }

  /**
   * Gets the layer being drawn by this legend item.
   *
   * @return the layer
   */
  public final Layer<?> getLayer() {
    return layer;
  }

  @Override
  public String toString() {
    return "LegendItem{" + "layer=" + layer + '}';
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 73 * hash + Objects.hashCode(this.layer);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final LegendItem other = (LegendItem) obj;
    return Objects.equals(this.layer, other.layer);
  }

  /**
   * Gets the vertical text alignment for the text on this control.
   *
   * @return The VAlignment object showing top, middle or bottom text alignment.
   */
  public final VAlignment getVerticalAlignment() {
    return verticalAlignment;
  }

  /**
   * Sets the vertical text alignment for the text on this control.
   *
   * @param verticalAlignment The VAlignment object showing top, middle or bottom text
   * alignment.
   */
  public final void setVerticalAlignment(VAlignment verticalAlignment) {
    this.verticalAlignment = verticalAlignment;
  }

}
