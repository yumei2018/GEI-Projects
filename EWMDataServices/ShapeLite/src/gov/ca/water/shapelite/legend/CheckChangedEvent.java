/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.legend;

import java.util.EventObject;
import javax.swing.tree.TreeNode;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CheckChangedEvent extends EventObject {
    private TreeNode node;
    
    /**
     * 
     * @param sender
     * @param node 
     */
    public CheckChangedEvent(Object sender, TreeNode node){
        super(sender);
        this.node = node;
    }

    /**
     * @return the node
     */
    public TreeNode getNode() {
        return node;
    }

    /**
     * @param node the node to set
     */
    protected void setNode(TreeNode node) {
        this.node = node;
    }
}
