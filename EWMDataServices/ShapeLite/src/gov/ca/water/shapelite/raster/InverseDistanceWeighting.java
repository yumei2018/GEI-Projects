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
package gov.ca.water.shapelite.raster;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.index.kdtree.KdNode;
import com.vividsolutions.jts.index.kdtree.KdTree;
import gov.ca.water.shapelite.data.dataset.RasterDataset;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class InverseDistanceWeighting {
    /**
     * 
     * @param sampled A Raster that has some values, and some missing values for no-data.
     * @param searchRadius in raster cells.  Distance coordinates are considered using cell size.
     * The search window will extend from x-searchRadius to x+searchRadius.
     * @return 
     */
    public static void interpolate(RasterDataset sampled, int searchRadius){
        KdTree tree = new KdTree();
        int height = sampled.getHeight();
        int width = sampled.getWidth();
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double value = sampled.getData()[y][x];
                if(value != sampled.getNoDataValue()){
                    Coordinate c = new Coordinate(x, y);
                    tree.insert(c, value);
                }
            }
        }
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double value = sampled.getData()[y][x];
                if(value == sampled.getNoDataValue()){
                    int xmin = Math.max(x-searchRadius, 0);
                    int ymin = Math.max(y-searchRadius, 0);
                    int xmax = Math.min(x+searchRadius, width-1);
                    int ymax = Math.min(y+searchRadius, height-1);
                    Envelope env = new Envelope(xmin, xmax, ymin, ymax);
                    List<KdNode> nodes = new ArrayList<>();
                    tree.query(env, nodes);
                    double result = interpolate(nodes, new Coordinate(x, y), sampled.getNoDataValue());
                    sampled.getData()[y][x] = result;
                }
            }
        }
    }
    
    private static double interpolate(List<KdNode> nodes, Coordinate cell, double noData){
        double totalWeights = 0.0;
        double totalFactor = 0.0;
        for(KdNode node : nodes){
            double dist = node.getCoordinate().distance(cell);
            double w = 1/dist;
            totalWeights += w;
            totalFactor += w * (Double)node.getData();
        }
        if(totalWeights > 0){
            return totalFactor/totalWeights;
        }
        return noData;
    }
    
    /*
    public Envelope getRadiusEnvelope(int x, int y, int searchRadius){
        int xmin = Math.max(x-searchRadius, 0);
        int ymin = Math.max(y-searchRadius, 0);
        int xmax = Math.min(x+searchRadius, width-1);
        int ymax = Math.min(y+searchRadius, height-1);
        Envelope env = new Envelope(xmin, xmax, ymin, ymax);
        return env; 
    }
    */
    
    
}
