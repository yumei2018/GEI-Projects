/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.ca.water.shapelite.symbology;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface Symbol {

  //<editor-fold defaultstate="collapsed" desc="Fields">

    


  //</editor-fold>

 

  //<editor-fold defaultstate="collapsed" desc="Methods">
  

  public void paint(Graphics2D graphics, Point center);
  

  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Properties">
  


  //</editor-fold>

}