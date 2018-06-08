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
package gov.ca.water.shapelite.toolbox;

import java.awt.Component;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ToolboxTreeRenderer extends DefaultTreeCellRenderer {

  private static Icon toolboxIcon;
  private static Icon hammerIcon;

  static {
    try {
      toolboxIcon = new ImageIcon(ImageIO.read(ToolboxTreeRenderer.class.getResourceAsStream("resources/Toolbox.png")));
      hammerIcon = new ImageIcon(ImageIO.read(ToolboxTreeRenderer.class.getResourceAsStream("resources/Hammer.png")));
    } catch (IOException ex) {
      Logger.getLogger(ToolboxTreeRenderer.class.getName()).log(
              Level.SEVERE, ex.getMessage(), ex);
    }
  }

  @Override
  public Component getTreeCellRendererComponent(JTree jtree, Object o, boolean bln, boolean bln1, boolean bln2, int i, boolean bln3) {

    Component result = super.getTreeCellRendererComponent(jtree, o, bln, bln1, bln2, i, bln3);

    if (o instanceof DefaultMutableTreeNode) {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode) o;
      Object userItem = node.getUserObject();
      if (userItem instanceof Tool) {
        Tool tool = (Tool) userItem;
        if (result instanceof JLabel) {
          JLabel label = (JLabel) result;
          if (tool.getIcon() == null) {
            label.setIcon(hammerIcon);
          } else {
            label.setIcon(new ImageIcon(tool.getIcon()));
          }
          label.setText(tool.getName());
          label.setToolTipText(tool.getDescription());
          return result;
        }
      }
    }
    if (result instanceof JLabel) {
      JLabel label = (JLabel) result;
      label.setIcon(toolboxIcon);
    }

    return result;
  }

}
