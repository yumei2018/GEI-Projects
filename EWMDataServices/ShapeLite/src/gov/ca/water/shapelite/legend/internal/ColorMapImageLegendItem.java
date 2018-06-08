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

import gov.ca.water.shapelite.map.layer.Layer;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author rmarquez
 */
public class ColorMapImageLegendItem extends LegendItem {

  public ColorMapImageLegendItem(Layer<?> mapLayer, Image image) {
    super(mapLayer);
    setVerticalAlignment(VAlignment.Top);
    super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    super.setAlignmentX(Component.LEFT_ALIGNMENT);
    super.setMaximumSize(new Dimension(200, 55));
    super.setMinimumSize(new Dimension(200, 55));
    super.setSize(new Dimension(200, 55));

    Rectangle rectangle = new Rectangle(0, 20, super.getWidth(), super.getHeight());
    JPanel colorMapPanel = initImageMapPanel(image, rectangle);
    super.add(Box.createRigidArea(new Dimension(0,20)));
    super.add(colorMapPanel);
  }

  private JPanel initImageMapPanel(Image image, Rectangle rectangle) {
    JPanel colorMapPanel = new JPanel();
    colorMapPanel.setLayout(new BoxLayout(colorMapPanel, BoxLayout.X_AXIS));
    colorMapPanel.add(Box.createRigidArea(new Dimension(20, 0)));
    JLabel jLabel = new JLabel(new ImageIcon(image));
    jLabel.setBounds(rectangle);
    colorMapPanel.add(jLabel);
    return colorMapPanel;
  }

}
