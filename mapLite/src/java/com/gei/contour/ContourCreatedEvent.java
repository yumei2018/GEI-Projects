/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.contour;

import gov.ca.water.shapelite.FeatureSet;
import java.util.EventObject;

/**
 *
 * @author hdunsford
 */
public class ContourCreatedEvent extends EventObject{
    
    private FeatureSet featureSet;
    
    public ContourCreatedEvent(Object source, FeatureSet featureSet){
      super(source);    
    }

    /**
     * @return the featureSet
     */
    public FeatureSet getFeatureSet() {
        return featureSet;
    }

    /**
     * @param featureSet the featureSet to set
     */
    protected void setFeatureSet(FeatureSet featureSet) {
        this.featureSet = featureSet;
    }
}
