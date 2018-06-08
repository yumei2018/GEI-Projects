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
package gov.ca.water.shapelite.projection;

import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ProjectionSelectionTree extends JTree {
    public ProjectionSelectionTree(){
        loadModel();
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }
    
    private void loadModel(){
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Projections");
        DefaultTreeModel model = new DefaultTreeModel(root);
        DefaultMutableTreeNode geographicNode = new DefaultMutableTreeNode("Geographic");
        root.add(geographicNode);
        DefaultMutableTreeNode projectedNode = new DefaultMutableTreeNode("Projected");
        root.add(projectedNode);
        List<CoordinateSystemCategory> geographicCategories = Projections.getGeographic().getAll();
        for(CoordinateSystemCategory category: geographicCategories){
            DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode(category.getCategoryName());
            geographicNode.add(categoryNode);
            for(ProjectionInfo proj : category.getAll()){
                DefaultMutableTreeNode projNode = new DefaultMutableTreeNode(proj);
                categoryNode.add(projNode);
            }
        }
        List<CoordinateSystemCategory> projectedCategories = Projections.getProjected().getAll();
        for(CoordinateSystemCategory category: projectedCategories){
            DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode(category.getCategoryName());
            projectedNode.add(categoryNode);
            for(ProjectionInfo proj : category.getAll()){
                DefaultMutableTreeNode projNode = new DefaultMutableTreeNode(proj);
                categoryNode.add(projNode);
            }
        }
        this.setModel(model);
    }
    
    /**
     * Return ProjectionInfo
     * @return 
     */
    public ProjectionInfo getSelectedProjection(){
        if(this.getSelectionPath() == null){
            return null;
        }
        Object node = this.getSelectionPath().getLastPathComponent();
        if(node instanceof DefaultMutableTreeNode){
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)node;
            Object item = selectedNode.getUserObject();
            if(item instanceof ProjectionInfo){
                return (ProjectionInfo)item;
            }
        }
        return null;
    }   
}
