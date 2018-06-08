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

import gov.ca.water.shapelite.dialog.DialogButtonType;
import gov.ca.water.shapelite.dialog.DialogHandler;
import gov.ca.water.shapelite.dialog.DefaultDialogHandler;
import gov.ca.water.shapelite.dialog.DialogResult;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.SwingUtilities;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ToolboxPopup {

  ToolboxPanel panel;
  boolean isAlreadyOneClick;
  JTree tree;

  /**
   * A dialog handler for showing the popup content.
   */
  private DialogHandler dialogHandler;

  /**
   * Creates a new instance of the Toolbox Popup handler for a toolbox.
   *
   * @param panel The ToolboxPanel that holds this popup.
   */
  public ToolboxPopup(ToolboxPanel panel) {
    this.panel = panel;
    tree = panel.getTree();
    if (dialogHandler == null) {
      Window window = SwingUtilities.windowForComponent(panel);
      JFrame frame = null;
      if (window instanceof JFrame) {
        frame = (JFrame) window;
      }
      dialogHandler = new DefaultDialogHandler(frame);
    }

    panel.getTree().addMouseListener(new MouseAdapter() {

      @Override
      public void mouseClicked(MouseEvent me) {
        if (SwingUtilities.isLeftMouseButton(me)) {
          if (isAlreadyOneClick) {
            TreePath path = tree.getPathForLocation(me.getX(), me.getY());
            launchDialog(path);
          } else {
            isAlreadyOneClick = true;
            Timer t = new Timer("doubleclickTimer", false);
            t.schedule(new TimerTask() {

              @Override
              public void run() {
                isAlreadyOneClick = false;
              }

            }, 500);
          }
        }
      }
    });
  }

  private void launchDialog(TreePath path) {
    if (path == null) {
      return;
    }
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
    if (node == null) {
      return;
    }
    Object obj = node.getUserObject();
    if (obj instanceof Tool) {
      Tool tool = (Tool) obj;
      tool.panelOpened();
      ParameterDialog dlg = new ParameterDialog();
      dlg.setTool(tool);
      Dimension sz = dlg.getPreferredSize();
      sz.height = tool.getParameters().size() * 50 + 80;
      dlg.setPreferredSize(sz);
      DialogResult result = getDialogHandler().showDialog(dlg, tool.getName(),
          DialogButtonType.OkCancel, dlg);
      if (result == DialogResult.OK) {
        // Launch the tool on a background thread for progress messages etc.
        new Thread(tool).start();
      }
    }
  }

  /**
   * @return the dialogHandler
   */
  public DialogHandler getDialogHandler() {
    return dialogHandler;
  }

  /**
   * @param dialogHandler the dialogHandler to set
   */
  public void setDialogHandler(DialogHandler dialogHandler) {
    this.dialogHandler = dialogHandler;
  }

}
