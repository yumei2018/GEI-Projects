/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.legend;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class NodeSelectionListener extends MouseAdapter {

    LegendTree tree;

    /**
     * Creates a new instance of the NodeSelectionListener, passing the tree.
     *
     * @param tree A JTree control
     */
    NodeSelectionListener(LegendTree tree) {
        this.tree = tree;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int row = tree.getRowForLocation(x, y);
        TreePath path = tree.getPathForRow(row);
        if (SwingUtilities.isRightMouseButton(e)) {
            // To Do - launch Context Menu
        }
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (path != null) {
                Object nodeObj = path.getLastPathComponent();
                if (nodeObj instanceof TreeNode) {
                    TreeNode node = (TreeNode) nodeObj;
                    if (node != tree.getModel().getRoot()) {
                        if(x >= 23 && x <= 35){
                            boolean isSelected = !(tree.isChecked(node));
                            tree.setChecked(node, isSelected);
                            tree.fireCheckChanged(new CheckChangedEvent(this, node));
                        }
                    }
                }
                if (row == 0) {
                    tree.revalidate();
                    tree.repaint();
                }

            }
        }

    }

    private void toggleExpand(TreeNode node) {
        TreePath path = new TreePath(node);
        if(tree.isExpanded(path)){
            tree.collapsePath(path);
        }else{
            tree.expandPath(path);
        }
        
    }

}
