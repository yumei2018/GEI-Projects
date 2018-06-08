/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.ca.water.shapelite.trace;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hdunsford
 */
public class Path {

    //<editor-fold defaultstate="collapsed" desc="Fields">

    private List<PartNode> nodes;


    //</editor-fold>

    /**
     * Creates a new instance of the Path class.
     */
    public Path(){
        nodes = new ArrayList<>();
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
    


    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

    /**
     * @return the nodes
     */
    public List<PartNode> getNodes() {
        return nodes;
    }

    /**
     * @param nodes the nodes to set
     */
    public void setNodes(List<PartNode> nodes) {
        this.nodes = nodes;
    }
    
   

}
