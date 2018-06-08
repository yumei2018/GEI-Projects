/*
 * The MIT License
 *
 * Copyright 2016 rmarquez.
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

import gov.ca.water.shapelite.events.MapLayerChangedEvent;
import gov.ca.water.shapelite.map.layer.Layer;
import gov.ca.water.shapelite.symbology.ColorMap;
import gov.ca.water.shapelite.symbology.ColorMapUtils;
import gov.ca.water.shapelite.symbology.EduMinesColorModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author rmarquez
 */
public class ColorMapLegendItem extends LegendItem {

  private final edu.mines.jtk.awt.ColorMap cmap;
  private final ColorValueBar colorValueBar;
  private final JPanel colorMapPanel;

  /**
   *
   * Constructor requiring mapLayer and a colorMap.
   * <p>
   * This is an example of creating a colormap:    <code>
   * ColorMap cmap = new ColorMap(0.0, 100.0, ColorMap.getJet());
   * </code>
   * </p>
   *
   * @param mapLayer
   * @param colorMap
   */
  public ColorMapLegendItem(Layer<?> mapLayer, ColorMap colorMap) {
    super(mapLayer);

    //    Initializing general layout
    super.setVerticalAlignment(VAlignment.Top);
    super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    super.setAlignmentX(Component.LEFT_ALIGNMENT);
    super.setMaximumSize(new Dimension(200, 55));
    super.setMinimumSize(new Dimension(200, 55));
    super.setSize(new Dimension(200, 55));

    //    Adding color value bar.
    if (colorMap instanceof EduMinesColorModel) {
      EduMinesColorModel eduMinesColorMap = (EduMinesColorModel) colorMap;
      this.cmap = new edu.mines.jtk.awt.ColorMap(colorMap.getMin(),
              colorMap.getMax(), eduMinesColorMap.getIndexColorModel());
    } else {
      this.cmap = new edu.mines.jtk.awt.ColorMap(colorMap.getMin(),
              colorMap.getMax(), ColorMapUtils.getColors(colorMap, 10));
    }

    this.colorValueBar = new ColorValueBar(cmap);
    Rectangle rectangle = new Rectangle(0, 20, super.getWidth(), super.getHeight());
    this.colorMapPanel = initColorMapPanel(colorValueBar, rectangle);
    super.add(Box.createRigidArea(new Dimension(0, 20)));
    super.add(colorMapPanel);

    super.addMapLayerChangedListener(new MapLayerChangedEvent.Listener() {
      @Override
      public void mapLayerChanged(MapLayerChangedEvent e) {
        colorMapPanel.setVisible(layer.isVisible());
      }
    });
  }

  private synchronized static JPanel initColorMapPanel(ColorValueBar colorValueBar,
          Rectangle bounds) {
    colorValueBar.setAlignmentX(Component.LEFT_ALIGNMENT);
    colorValueBar.setSize(new Dimension(200, 45));
    colorValueBar.setMinimumSize(new Dimension(200, 45));
    colorValueBar.setBounds(bounds);
    JPanel colorMapPanel = new JPanel();
    colorMapPanel.setLayout(new BoxLayout(colorMapPanel, BoxLayout.X_AXIS));
    colorMapPanel.add(Box.createRigidArea(new Dimension(20, 0)));
    colorMapPanel.add(colorValueBar);
    return colorMapPanel;
  }


  /**
   *
   * @param colorMap
   * @return
   */
  public synchronized static JPanel initColorMapPanel(ColorMap colorMap) {
    edu.mines.jtk.awt.ColorMap cmap;
    if (colorMap instanceof EduMinesColorModel) {
      EduMinesColorModel eduMinesColorMap = (EduMinesColorModel) colorMap;
      cmap = new edu.mines.jtk.awt.ColorMap(colorMap.getMin(),
              colorMap.getMax(), eduMinesColorMap.getIndexColorModel());
    } else {
      cmap = new edu.mines.jtk.awt.ColorMap(colorMap.getMin(),
              colorMap.getMax(), ColorMapUtils.getColors(colorMap, 10));
    }
    Rectangle rectangle = new Rectangle(0, 20, 200, 55);
    ColorValueBar colorValueBar = new ColorValueBar(cmap);
    colorValueBar.setAlignmentX(Component.LEFT_ALIGNMENT);
    colorValueBar.setSize(new Dimension(200, 45));
    colorValueBar.setMinimumSize(new Dimension(200, 45));
    colorValueBar.setBounds(rectangle);
    colorValueBar.setBackground(new Color(0, 0, 0, 0));
    JPanel colorMapPanel = new JPanel();
    colorMapPanel.setLayout(new BoxLayout(colorMapPanel, BoxLayout.X_AXIS));
    colorMapPanel.add(Box.createRigidArea(new Dimension(20, 0)));
    colorMapPanel.add(colorValueBar);
    return colorMapPanel;
  }
  
  public double getMinValue() {
    return cmap.getMinValue();
  }

  public double getMaxValue() {
    return cmap.getMaxValue();
  }

  public Color getColor(double v) {
    return cmap.getColor(v);
  }

  @Override
  public String toString() {
    return "ColorMapLegendItem{" + "cmap=" + cmap + '}';
  }
}
