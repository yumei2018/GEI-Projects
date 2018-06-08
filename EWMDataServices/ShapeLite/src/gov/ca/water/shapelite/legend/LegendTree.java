/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.legend;

import gov.ca.water.shapelite.map.DisplayEvent;
import gov.ca.water.shapelite.map.LegendSymbol;
import gov.ca.water.shapelite.map.MapContentListener;
import gov.ca.water.shapelite.map.layer.Layer;
import gov.ca.water.shapelite.map.MapPanel;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LegendTree extends JTree {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private final HashMap<TreeNode, Boolean> checkMap;
  private MapPanel map;

  //</editor-fold>
  /**
   * Creates a new instance of the CheckedTree class.
   */
  public LegendTree() {
    checkMap = new HashMap<>();
    setCellRenderer(new LegendItemRenderer(this));
    addMouseListener(new NodeSelectionListener(this));
    getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

  }

  /**
   * Sets the map and configures event handling for layers being updated.
   *
   * @param map
   */
  public void setMap(MapPanel map) {
    this.map = map;
    connectMapEvents();
    updateLegend();
  }

  private void connectMapEvents() {
    this.map.getContent().addMapContentListener(new MapContentListener() {

      @Override
      public void renderComplete(DisplayEvent e) {

      }

      @Override
      public void contentChanged(DisplayEvent e) {
        // if the content changes, we need to update the legend as well.
        updateLegend();
      }
    });
  }

  public void updateLegend() {
    DefaultTreeModel model = (DefaultTreeModel) this.getModel();
    DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
    root.removeAllChildren();
    if (this.map != null) {
      for (int i = map.getContent().getLayers().size() - 1; i >= 0; i--) {
        Layer<?> layer = this.map.getContent().getLayers().get(i);
        if (layer.isVisibleInLegend()) {
          DefaultMutableTreeNode layerNode = new DefaultMutableTreeNode(layer);
          this.setChecked(layerNode, layer.isVisible());
          root.add(layerNode);
          if (layer.getLegendSymbols() != null && !layer.getLegendSymbols().isEmpty()) {
            for (LegendSymbol symbol : layer.getLegendSymbols()) {
              DefaultMutableTreeNode symbolNode = new DefaultMutableTreeNode(symbol);
              layerNode.add(symbolNode);
            }

          }

        }
      }
    }
    model.reload();
  }

  private final List<CheckChangedListener> eventListeners = new ArrayList<>();

  /**
   * Ensures the specified listener will receive the help event.
   *
   * @param listener The listener to connect.
   */
  public void addCheckChangedListener(CheckChangedListener listener) {
    eventListeners.add(listener);
  }

  /**
   * Removes the specified listener form the list if it is in the list.
   *
   * @param listener The listener to disconnect.
   */
  public void removecheckChangedListener(CheckChangedListener listener) {
    eventListeners.remove(listener);
  }

  private void updateMapVisibility(TreeNode node) {
    if (node instanceof DefaultMutableTreeNode) {
      DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) node;
      Object userObject = treeNode.getUserObject();
      if (userObject instanceof Layer<?>) {
        Layer<?> layer = (Layer<?>) userObject;
        layer.setVisible(this.isChecked(node));
        if (this.map != null) {
          this.map.getContent().paintImmediately();
          this.map.repaint();
        }
      }
    }
  }

  /**
   * Fires the showHelp event.
   *
   * @param e An EventArgument with the help content to show.
   */
  public void fireCheckChanged(CheckChangedEvent e) {
    updateMapVisibility(e.getNode());
    for (CheckChangedListener listener : eventListeners) {
      listener.checkChanged(e);
    }
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Gets a boolean indicating whether the specified node is checked.
   *
   * @param node THe node to evaluate
   * @return Boolean, true if the node is checked.
   */
  public boolean isChecked(TreeNode node) {
    if (checkMap.containsKey(node)) {
      return checkMap.get(node);
    }
    return false;
  }

  /**
   * Gets a boolean indicating whether the current node is currently checked.
   *
   * @param node
   * @param checked
   */
  public void setChecked(TreeNode node, boolean checked) {
    justSetChecked(node, checked);
    updateChildren(node);
    updateParents(node);

  }

  private void justSetChecked(TreeNode node, boolean checked) {
    boolean prev = isChecked(node);
    checkMap.put(node, checked);
    if (checked != prev) {
      ((DefaultTreeModel) getModel()).nodeChanged(node);
    }
  }

  /**
   * If a parent checkbox is checked, then all the children should be changed to
   * match that setting.
   *
   * @param node
   */
  public void updateChildren(TreeNode node) {
    Enumeration<?> e = node.children();
    boolean checked = isChecked(node);
    while (e.hasMoreElements()) {
      Object childObject = e.nextElement();
      if (childObject instanceof DefaultMutableTreeNode) {
        DefaultMutableTreeNode child = (DefaultMutableTreeNode) childObject;
        justSetChecked(child, checked);
        updateChildren(child);
      }

    }
  }

  /**
   * If any of the children of a parent are checked, then the parent should be
   * checked. If none are checked, then the parent can be unchecked.
   *
   * @param node
   */
  public void updateParents(TreeNode node) {
    TreeNode parent = node.getParent();
    while (parent != null) {
      Enumeration<?> e = parent.children();
      boolean shouldBeChecked = false;
      while (e.hasMoreElements()) {
        Object childObject = e.nextElement();
        if (childObject instanceof TreeNode) {
          TreeNode child = (TreeNode) childObject;
          if (isChecked(child)) {
            shouldBeChecked = true;
            break;
          }
        }

      }
      justSetChecked(parent, shouldBeChecked);
      parent = parent.getParent();
    }
  }

    //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>
}
