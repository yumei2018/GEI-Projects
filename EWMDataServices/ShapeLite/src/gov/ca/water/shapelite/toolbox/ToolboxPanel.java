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

import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.dialog.DialogHandler;
import gov.ca.water.shapelite.dialog.DefaultDialogHandler;
import gov.ca.water.shapelite.progress.ProgressCountableCancellable;
import gov.ca.water.shapelite.progress.ProgressCountableCancellableConsole;
import gov.ca.water.shapelite.toolbox.tools.AverageElevation;
import gov.ca.water.shapelite.toolbox.tools.ClassifyLines;
import gov.ca.water.shapelite.toolbox.tools.CreateHillshade;
import gov.ca.water.shapelite.toolbox.tools.CreateSectionQuarters;
import gov.ca.water.shapelite.toolbox.tools.CropAnimationWithPolygon;
import gov.ca.water.shapelite.toolbox.tools.CsvToShapefile;
import gov.ca.water.shapelite.toolbox.tools.ExportBankCrossings;
import gov.ca.water.shapelite.toolbox.tools.GeocodePoints;
import gov.ca.water.shapelite.toolbox.tools.GmzToShapefiles;
import gov.ca.water.shapelite.toolbox.tools.ShapefileToCsv;
import gov.ca.water.shapelite.toolbox.tools.SimplifyLines;
import java.awt.Window;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ToolboxPanel extends javax.swing.JPanel {

  /**
   * The list of tools.
   */
  private List<Tool> tools;

  /**
   * The root node.
   */
  private DefaultMutableTreeNode root;

  /**
   * The toolbox popup that shows when right clicking a tool.
   */
  private final ToolboxPopup popup;

  /**
   * The tree model for this control.
   */
  private DefaultTreeModel treeModel;

  /**
   * Specifies the progress handler for tasks in the toolbox panel.
   */
  private ProgressCountableCancellable progressHandler;

  /**
   * Creates new form Toolbox.
   */
  public ToolboxPanel() {
    initComponents();
    root = new DefaultMutableTreeNode("Tools");
    treeModel = new DefaultTreeModel(root);
    treeTools.setModel(treeModel);
    treeTools.setCellRenderer(new ToolboxTreeRenderer());
    tools = new ArrayList<>();
    popup = new ToolboxPopup(this);
    DialogHandler dialogHandler = new DefaultDialogHandler(null);
    popup.setDialogHandler(dialogHandler);
    progressHandler = new ProgressCountableCancellableConsole();
  }

  /**
   * Updates the dialog handler so that it has an updated parent.
   */
  public final void updateDialogHandler() {
    Window window = SwingUtilities.getWindowAncestor(this);
    JFrame frame = null;
    if (window instanceof JFrame) {
      frame = (JFrame) window;
    }
    DialogHandler dialogHandler = new DefaultDialogHandler(frame);
    popup.setDialogHandler(dialogHandler);
  }

  /**
   * Adds the default tools to the toolbox panel.
   */
  public final void addDefaultTools() {
    addTool(new GeocodePoints());
    addTool(new ClassifyLines());
    addTool(new SimplifyLines());
    addTool(new CreateHillshade());
    addTool(new GmzToShapefiles());
    addTool(new ExportBankCrossings());
    addTool(new AverageElevation());
    addTool(new ShapefileToCsv());
    addTool(new CsvToShapefile());
    addTool(new CropAnimationWithPolygon());
    addTool(new CreateSectionQuarters());
    refreshTree();
  }

  /**
   * Adds all the tools and refreshes the tree afterwards.
   *
   * @param tools The list of Tool objects to add.
   */
  public final void addTools(List<Tool> tools) {
    for (Tool tool : tools) {
      doAddTool(tool);
    }
    refreshTree();
  }

  /**
   * Adds the specified tool, ensuring the images are loaded. This refreshes the
   * tree afterwards, so if several tools are added, use the addTools method
   * instead for faster loading.
   *
   * @param tool The tool to add.
   */
  public final void addTool(Tool tool) {
    doAddTool(tool);
    refreshTree();
  }

  /**
   * Adds the tools internally, but does not update the tree.
   *
   * @param tool The new tool to add.
   */
  private void doAddTool(Tool tool) {
    if (this.progressHandler != null) {
      tool.setProgress(this.progressHandler);
    }
    tool.loadImages();
    tools.add(tool);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    treeTools = new javax.swing.JTree();

    jScrollPane1.setViewportView(treeTools);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
    );
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  /**
   * Scroll panel.
   */
  private javax.swing.JScrollPane jScrollPane1;
  /**
   * Tree tools.
   */
  private javax.swing.JTree treeTools;
  // End of variables declaration//GEN-END:variables

  /**
   * Rebuilds the tree based on the model data.
   */
  public void refreshTree() {
    HashMap<String, DefaultMutableTreeNode> categories = new HashMap<>();
    root = new DefaultMutableTreeNode("Tools");
    for (Tool tool : tools) {
      DefaultMutableTreeNode category;
      if (tool.getCategory() == null) {
        category = root;
      } else if (categories.containsKey(tool.getCategory())) {
        category = categories.get(tool.getCategory());
      } else {
        category = new DefaultMutableTreeNode(tool.getCategory());
        categories.put(tool.getCategory(), category);
        root.add(category);
      }
      DefaultMutableTreeNode toolNode = new DefaultMutableTreeNode(tool);
      category.add(toolNode);
    }
    treeModel = new DefaultTreeModel(root);
    treeTools.setModel(treeModel);
    //treeModel.reload(root);

  }

  /**
   * Gets the tree control for this panel.
   *
   * @return The JTree control.
   */
  public final JTree getTree() {
    return treeTools;
  }

  /**
   * Gets the list of tools.
   *
   * @return the tools
   */
  protected final List<Tool> getTools() {
    return tools;
  }

  /**
   * Gets the tool with the specified name.
   *
   * @param name The name of the tool to get.
   * @return An optional tool.
   */
  public Optional<Tool> getTool(String name) {
    for (Tool tool : tools) {
      if (tool.getName().equals(name)) {
        return Optional.of(tool);
      }
    }
    return Optional.empty();
  }

  /**
   * @param tools the tools to set
   */
  protected final void setTools(List<Tool> tools) {
    this.tools = tools;
  }

  /**
   * Gets the DialogHandler for the popup dialogs.
   *
   * @return the dialogHandler
   */
  public final DialogHandler getDialogHandler() {
    return popup.getDialogHandler();
  }

  /**
   * Sets the DialogHandler for the popup dialogs.
   *
   * @param dialogHandler the dialogHandler to set
   */
  public final void setDialogHandler(DialogHandler dialogHandler) {
    this.popup.setDialogHandler(dialogHandler);
  }

  /**
   * Gets the progress handler for tasks in the toolbox panel.
   *
   * @return the progressHandler
   */
  public final ProgressCountableCancellable getProgressHandler() {
    return progressHandler;
  }

  /**
   * Sets the progress handler for tasks in the toolbox panel.
   *
   * @param progressHandler the progressHandler to set
   */
  public final void setProgressHandler(ProgressCountableCancellable progressHandler) {
    this.progressHandler = progressHandler;
  }

}
