/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.symbology;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShapeSymbol implements Symbol {

  //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private Shape shape;
    private Color color;
    
//</editor-fold>
    /**
     * Creates a new instance of the ShapeSymbol class.
     */
    public ShapeSymbol() {
        color = Color.BLACK;
    }
    
    public ShapeSymbol(Shape shape) {
        this.shape = shape;
        color = Color.BLACK;
    }
    
    
    public ShapeSymbol(SimpleShapes shp, double width, double height, Color color){
        this.color = color;
        switch(shp){
            case STAR: this.shape = star(width, height); break;
            case ELLIPSE: this.shape = ellipse(width, height); break;
            case RECTANGLE: this.shape = rectangle(width, width); break;
            case TRIANGLE: this.shape = triangle(width, width); break;
        }
    }
    

  //<editor-fold defaultstate="collapsed" desc="Methods">
    
    
    
  //</editor-fold>
    
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>

    @Override
    public void paint(Graphics2D graphics, Point center) {
       graphics.setColor(color);
       graphics.translate(center.x, center.y);
       graphics.fill(shape);
       graphics.translate(-center.x, -center.y);
    }

    /**
     * @return the shape
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * @param shape the shape to set
     */
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }
    
    
    public static Shape ellipse(double w, double h){
        GeneralPath path = new GeneralPath();
        path.append(new Ellipse2D.Double(-w/2,-h/2,w,h), false);
        path.closePath();
        return path;
    }
    
    public static Shape rectangle(double x, double y){
        double u = x/10;
        double v = x/10;
        GeneralPath path = new GeneralPath();
        path.moveTo(-5*u, -5*v);
        path.lineTo(5*u, -5*v);
        path.lineTo(-5*u, 5*v);
        path.lineTo(5*u, 5*v);
        path.lineTo(-5*u, -5*v);
        path.closePath();
        return path;
    }
    
    
    public static Shape triangle(double w, double h){
        double u = w/10;
        double v = h/10;
        GeneralPath path = new GeneralPath();
        path.moveTo(-5*u, 5*v);
        path.lineTo(0, -5*v);
        path.lineTo(5*u, 5*v);
        path.lineTo(-5*u, 5*v);
        path.closePath();
        return path;
    }
   
    
    public static Shape star(double w, double h){
         
        
        double u = w/100;
        double v = h/100;
        GeneralPath path = new GeneralPath();
        path.moveTo(-50*u, -12*v);
        path.lineTo(-12*u, -12*v);
        path.lineTo(0, -50*v);
        path.lineTo(12*u, -12*v);
        path.lineTo(50*u, -12*v);
        path.lineTo(19*u, 12*v);
        path.lineTo(30*u, 50*v);
        path.lineTo(0, 26*v);
        path.lineTo(-30*u, 50*v);
        path.lineTo(-19*u, 12*v);
        path.lineTo(-50*u, -12*v);
        path.closePath();
        return path;
    }
}
