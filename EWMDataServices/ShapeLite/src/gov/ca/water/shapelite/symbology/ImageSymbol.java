/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.symbology;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ImageSymbol implements Symbol {

  //<editor-fold defaultstate="collapsed" desc="Fields">
    private BufferedImage image;

  //</editor-fold>
    
    /**
     * Creates a new instance of the ImageSymbol class.
     */
    public ImageSymbol() {

    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public void paint(Graphics2D graphics, Point center) {
        int x = center.x - image.getWidth()/2;
        int y = center.y - image.getHeight()/2;
        graphics.drawImage(image, x, y, null);
        
    }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>
    /**
     * @return the image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
