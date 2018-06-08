/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.symbology;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Envelope;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.EventObject;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PaintEvent extends EventObject {

  //<editor-fold defaultstate="collapsed" desc="Fields">
    private Rectangle rectangle;
    private Envelope envelope;
    private Graphics2D graphics;

  //</editor-fold>
    /**
     * Creates a new instance of the PaintEvent class.
     *
     * @param sender
     * @param rectangle
     * @param envelope
     * @param g
     */
    public PaintEvent(Object sender, Rectangle rectangle, Envelope envelope, Graphics2D g) {
        super(sender);
        this.rectangle = rectangle;
        this.envelope = envelope;
        this.graphics = g;
    }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>
    /**
     * @return the rectangle
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * @param rectangle the rectangle to set
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * @return the envelope
     */
    public Envelope getEnvelope() {
        return envelope;
    }

    /**
     * @param envelope the envelope to set
     */
    public void setEnvelope(Envelope envelope) {
        this.envelope = envelope;
    }

    /**
     * @return the graphics
     */
    public Graphics2D getGraphics() {
        return graphics;
    }

    /**
     * @param graphics the graphics to set
     */
    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    /**
     * Gets the pixel Point location if the coordinate c were projected from the
     * geographic extent to the rectangle.
     * @param c
     * @return Point
     */
    public Point projToPixel(Coord c) {
        Point result = new Point();
        double xmin = envelope.getMin().getX();
        double dx = rectangle.width / envelope.getWidth();
        double ymax = envelope.getMax().getY();
        double dy = rectangle.height / envelope.getHeight();
        result.x = (int) (rectangle.x + (c.getX() - xmin) * dx);
        result.y = (int) (rectangle.y + (ymax - c.getY()) * dy);
        return result;
    }

}
