/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.trace;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Shape;

/**
 *
 * @author hdunsford
 */
public interface LineExtractor {

    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Returns an array of interleaved X, Y values.
     *
     * @param startX The x ordinate of the start point
     * @param startY the y ordinate of the start point
     * @param endX the x ordinate of the end point
     * @param endY the y ordinate of the end point
     * @param tolerance The distance by which to extend the envelope when
     * searching for possible intersecting levee segments.
     * @return A double array of points.
     */
    public double[] extract(double startX, double startY, double endX,
        double endY, double tolerance);

    /**
     * Given a line shapefile, this will attempt to find a path between the
     * start and end points.
     *
     * @param start
     * @param end
     * @param tolerance
     * @return A polyline shape made from as many parts or shapes as necessary
     * to create a path from the start to the end, if possible.
     */
    public Shape extract(Coord start, Coord end, double tolerance);

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Properties">
    //</editor-fold>
}
