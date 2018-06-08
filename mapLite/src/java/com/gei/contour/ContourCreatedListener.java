/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.contour;

import java.util.EventListener;

/**
 *
 * @author hdunsford
 */
public interface ContourCreatedListener extends EventListener {
    /**
     * Occurs when a new contour has been created.
     * @param e 
     */
    public void contourCreated(ContourCreatedEvent e);
}
