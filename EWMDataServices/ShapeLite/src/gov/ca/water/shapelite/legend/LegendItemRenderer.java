/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.legend;

import gov.ca.water.shapelite.map.LegendSymbol;
import gov.ca.water.shapelite.map.layer.Layer;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LegendItemRenderer extends DefaultTreeCellRenderer {

    private final LegendTree checkTree;
    private final LegendItemPanel legendPanel;
    private static Icon layerIcon;
    private final LegendRootPanel legendRoot;

    static {
        BufferedImage img;

        try {
            img = ImageIO.read(LegendItemRenderer.class.getResourceAsStream("resources/Layers.png"));
            layerIcon = new ImageIcon(img);
        } catch (IOException ex) {
            Logger.getLogger(LegendItemRenderer.class.getName()).log(
                  Level.SEVERE, ex.getMessage(), ex);

        }
    }

    public LegendItemRenderer(LegendTree tree) {
        checkTree = tree;
        legendPanel = new LegendItemPanel();
        legendRoot = new LegendRootPanel();
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean isSelected, boolean expanded, boolean leaf, int row,
            boolean hasFocus) {

        // Root node shows layers
        if (value == tree.getModel().getRoot()) {
            legendRoot.setSelected(selected);
            return legendRoot;
        }
        
        // Symbol nodes show a symbol without expand options or check boxes
        if(value instanceof DefaultMutableTreeNode){
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
            Object userObject = node.getUserObject();
            if(userObject instanceof LegendSymbol){
                LegendSymbol symbol = (LegendSymbol)userObject;
                JLabel label = new JLabel();
                label.setText(symbol.getName());
                label.setIcon(new ImageIcon(symbol.getIcon()));
                return label;
            }
        }

        // Layer nodes show expand and checkboxes along with text.
        String stringValue = tree.convertValueToText(value, isSelected,
                expanded, leaf, row, hasFocus);
        legendPanel.setEnabled(tree.isEnabled());
        if (value instanceof TreeNode) {
            TreeNode node = (TreeNode) value;
            legendPanel.setChecked(checkTree.isChecked(node));
        }
        LegendItemLabel label = legendPanel.getLabel();
        label.setFont(tree.getFont());
        label.setText(stringValue);
        label.setSelected(isSelected);
        label.setFocus(hasFocus);
//        if (leaf) {
//            label.setIcon(UIManager.getIcon("Tree.leafIcon"));
//        } else if (expanded) {
//            label.setIcon(UIManager.getIcon("Tree.openIcon"));
//        } else {
//            label.setIcon(UIManager.getIcon("Tree.closedIcon"));
//        }
        if (value instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            Object userObject = node.getUserObject();
            if (userObject instanceof Layer<?>) {
                Layer<?> layer = (Layer<?>) userObject;
                
            }
        }

        return legendPanel;
    }

}
