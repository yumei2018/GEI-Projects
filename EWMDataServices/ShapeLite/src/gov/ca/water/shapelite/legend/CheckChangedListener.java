/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.legend;

import java.util.EventListener;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface CheckChangedListener extends EventListener{
    public void checkChanged(CheckChangedEvent e);
}
