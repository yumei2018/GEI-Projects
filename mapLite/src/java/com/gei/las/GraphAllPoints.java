/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gei.las;


import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileWriter;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.openide.util.Exceptions;

/**
 *
 * @author ileung
 */
public class GraphAllPoints {
    private final static ProjectionInfo webmerc = Projections.Projected.getWorld().WebMercator;
    private final static ProjectionInfo wgs84 = Projections.Geographic.getWorld().WGS1984;
    private final static String utmFoot = "PROJCS[\"NAD_1983_UTM_Zone_10N\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",1640416.666666667],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-123.0],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Foot_US\",0.3048006096012192]]";
    private final static DecimalFormat twodigit = new DecimalFormat("#.00");

    /**
     * This method generates a ShapeFile from x and y coordinates specified in webmerc projection and extracts all LiDAR information
     * the extent contains
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     * @throws ProjectionException
     */
    public void getShapeFileFromExtent(double x1, double y1, double x2, double y2) throws ProjectionException, ShapefileException {
        Coord coord1 = new Coord(x1, y1);
        Coord coord2 = new Coord(x2, y2);
        ProjectionInfo info = ProjectionInfo.fromEsriString(utmFoot);
        Coord newCoord1 = Reproject.reprojectCoordinate(coord1, webmerc, info);
        Coord newCoord2 = Reproject.reprojectCoordinate(coord2, webmerc, info);
        String a = newCoord1.X*100 + "", b = newCoord1.Y*100 + "", c = newCoord2.X*100 + "", d = newCoord2.Y*100 + "";
        a = a.substring(0, 10).replace(".", "");
        b = b.substring(0, 11).replace(".", "");
        c = c.substring(0, 10).replace(".", "");
        d = d.substring(0, 11).replace(".", "");
        Double xStart = Double.parseDouble(a);
        Double yStart = Double.parseDouble(b);
        Double xEnd = Double.parseDouble(c);
        Double yEnd = Double.parseDouble(d);        
        Polygon poly = new Polygon();        
        poly.addPoint(xStart.intValue(), yStart.intValue());
        poly.addPoint(xEnd.intValue(), yStart.intValue());
        poly.addPoint(xEnd.intValue(), yEnd.intValue());
        poly.addPoint(xStart.intValue(), yEnd.intValue());
        
        
        Line2D.Double usrLeft = new Line2D.Double(xStart, yStart, xStart, yEnd);
        Line2D.Double usrTop = new Line2D.Double(xStart, yStart, xEnd, yStart);
        Line2D.Double usrRight = new Line2D.Double(xEnd, yStart, xEnd, yEnd);
        Line2D.Double usrBot = new Line2D.Double(xStart, yEnd, xEnd, yEnd);
        
        
        List<String> filesToRead = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\ileung\\Documents\\Test2\\bounds_fileName.txt")))) {
            String line = "";
            while((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Double llx = Double.parseDouble(parts[0]);
                Double lly = Double.parseDouble(parts[1]);
                Double urx = Double.parseDouble(parts[2]);
                Double ury = Double.parseDouble(parts[3]);
                
                Coord coordll = new Coord(llx, lly);
                Coord coordur = new Coord(urx, ury);
                Coord newCoordll = Reproject.reprojectCoordinate(coordll, wgs84, info);
                Coord newCoordur = Reproject.reprojectCoordinate(coordur, wgs84, info);
                
                String e = newCoordll.X*100 + "", f = newCoordll.Y*100 + "", g = newCoordur.X*100 + "", h = newCoordur.Y*100 + "";
                e = e.substring(0, 10).replace(".", "");
                f = f.substring(0, 11).replace(".", "");
                g = g.substring(0, 10).replace(".", "");
                h = h.substring(0, 11).replace(".", "");
                
                Double leftX = Double.parseDouble(e);
                Double lowerY = Double.parseDouble(f);
                Double rightX = Double.parseDouble(g);
                Double upperY = Double.parseDouble(h);
                
                Line2D.Double left = new Line2D.Double(leftX, lowerY, leftX, upperY );
                Line2D.Double top = new Line2D.Double(leftX, upperY , rightX, upperY );
                Line2D.Double right = new Line2D.Double(rightX, upperY , rightX, lowerY);
                Line2D.Double bot = new Line2D.Double(rightX, lowerY, leftX, lowerY);
                
                Polygon polygon = new Polygon();
                polygon.addPoint(leftX.intValue(), lowerY.intValue());
                polygon.addPoint(leftX.intValue(), upperY.intValue());
                polygon.addPoint(rightX.intValue(), upperY.intValue());
                polygon.addPoint(rightX.intValue(), lowerY.intValue());
                
                if(usrLeft.intersectsLine(left) || usrLeft.intersectsLine(top) || usrLeft.intersectsLine(right) || usrLeft.intersectsLine(bot) ||
                   usrRight.intersectsLine(left) || usrRight.intersectsLine(top) || usrRight.intersectsLine(right) || usrRight.intersectsLine(bot) ||
                   usrTop.intersectsLine(left) || usrTop.intersectsLine(top) || usrTop.intersectsLine(right) || usrTop.intersectsLine(bot) ||
                   usrBot.intersectsLine(left) || usrBot.intersectsLine(top) || usrBot.intersectsLine(right) || usrBot.intersectsLine(bot)) {
                    filesToRead.add(parts[4]);
                } else if(polygon.contains(xStart, yStart) || polygon.contains(xEnd, yEnd)) {
                    filesToRead.add(parts[4]);
                }
                
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }   
        generateShapeFileFromLidar(filesToRead, poly);
    }
        
    /**
     * This method generates a shapeFile from a user defined polygon and a list of LiDAR file names
     * @param filesToRead
     * @param poly
     * @return
     * @throws ShapefileException 
     */
    public String generateShapeFileFromLidar(List<String> filesToRead, Polygon poly) throws ShapefileException{
        ProjectionInfo info = ProjectionInfo.fromEsriString(utmFoot);
        FeatureSet points = new FeatureSet();        
        points.setProjection(wgs84.toProj4String());
        LasReader reader = new LasReader();
        for(String fileName : filesToRead) {
            reader.open(fileName);
            try {
            List<PointRecord> results = reader.getPointsOnly();
                Double minZ = null;
                Double maxZ = null;             
                for(PointRecord rec : results) { 
                    if(poly.contains(rec.getX()/100.0, rec.getY()/100.0)) {     
                        Feature f = new Feature();                                
                        Shape shape = new Shape(ShapeType.PointZ);                                                                                        
                        Coord coord = convertToLatLong(info, wgs84, new Coord(rec.getX(), rec.getY()));
                        shape.setX(coord.X);
                        shape.setY(coord.Y);
                        shape.setZ(rec.getZ()/100.0);
                        shape.calculateBounds();
                        f.setShape(shape);                              
                        points.getFeatures().add(f);                        
                    }
                }            

            } catch (LasReadException | ProjectionException ex) {
                Exceptions.printStackTrace(ex);
            }
        }                        
        ShapefileWriter writer = new ShapefileWriter();
        String shp = "C:\\Temp\\samplePoints.shp";
        String shx = "C:\\Temp\\samplePoints.shx";
        String dbf = "C:\\Temp\\samplePoints.dbf";
        File fileShp = new File(shp);
        File fileShx = new File(shx);
        File fileDbf = new File(dbf);
        if(fileShp.exists()) {
            fileShp.delete();
        }
        if(fileShx.exists()) {
            fileShx.delete();
        }
        if(fileDbf.exists()) {
            fileDbf.delete();
        }
        writer.write(points, shp);
        return shp;
    }
    
    /**
     * This method converts coordinates.
     * @param source
     * @param dest
     * @param coord
     * @return
     * @throws ProjectionException 
     */
    public static Coord convertToLatLong(ProjectionInfo source, ProjectionInfo dest, Coord coord) throws ProjectionException {
        Double x1 = coord.X/100;
        Double y1 = coord.Y/100;        
        
        Coord coord1 = new Coord(x1, y1);                       
        Coord newCoord = Reproject.reprojectCoordinate(coord1, source, dest);                        
        
        Coord result = new Coord(newCoord.X, newCoord.Y);
        return result;
    }
    
    /**
     * This method will return all points that lay within an error box of the line specified
     * It will return a list of points (x,y) where x is the distance from the starting point and y is the elevation
     * This specific method return a csvString to the response writer for dygraphs to process and graph.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     * @throws ProjectionException 
     */
    public static String getPoints(double x1, double y1, double x2, double y2) throws ProjectionException{        
        Coord coord1 = new Coord(x1, y1);
        Coord coord2 = new Coord(x2, y2);
        ProjectionInfo info = ProjectionInfo.fromEsriString(utmFoot);
        Coord newCoord1 = Reproject.reprojectCoordinate(coord1, webmerc, info);
        Coord newCoord2 = Reproject.reprojectCoordinate(coord2, webmerc, info);
        String a = newCoord1.X*100 + "", b = newCoord1.Y*100 + "", c = newCoord2.X*100 + "", d = newCoord2.Y*100 + "";
        a = a.substring(0, 10).replace(".", "");
        b = b.substring(0, 11).replace(".", "");
        c = c.substring(0, 10).replace(".", "");
        d = d.substring(0, 11).replace(".", "");
        Double xStart = Double.parseDouble(a);
        Double yStart = Double.parseDouble(b);
        Double xEnd = Double.parseDouble(c);
        Double yEnd = Double.parseDouble(d);
        Double long1Max = xStart + 300;
        Double long1Min = xStart - 300;
        Double lat1Max = yStart + 300;
        Double lat1Min = yStart - 300;
        Double long2Max = xEnd + 300;
        Double long2Min = xEnd - 300;
        Double lat2Max = yEnd + 300;
        Double lat2Min = yEnd - 300;
        Polygon poly = new Polygon();
        if(Math.abs((yEnd-yStart)/(xEnd-xStart)) > .5) {
            poly.addPoint(long1Min.intValue(), yStart.intValue());
            poly.addPoint(long1Max.intValue(), yStart.intValue());
            poly.addPoint(long2Max.intValue(), yEnd.intValue());
            poly.addPoint(long2Min.intValue(), yEnd.intValue());
        } else {
            poly.addPoint(xStart.intValue(), lat1Min.intValue());
            poly.addPoint(xStart.intValue(), lat1Max.intValue());
            poly.addPoint(xEnd.intValue(), lat2Max.intValue());
            poly.addPoint(xEnd.intValue(), lat2Min.intValue());
        }
        
        Line2D.Double usrLine = new Line2D.Double(xStart, yStart, xEnd, yEnd);
        
        
        List<String> filesToRead = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\ileung\\Documents\\Test2\\bounds_fileName.txt")))) {
            String line = "";
            while((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Double llx = Double.parseDouble(parts[0]);
                Double lly = Double.parseDouble(parts[1]);
                Double urx = Double.parseDouble(parts[2]);
                Double ury = Double.parseDouble(parts[3]);
                
                Coord coordll = new Coord(llx, lly);
                Coord coordur = new Coord(urx, ury);
                Coord newCoordll = Reproject.reprojectCoordinate(coordll, wgs84, info);
                Coord newCoordur = Reproject.reprojectCoordinate(coordur, wgs84, info);
                
                String e = newCoordll.X*100 + "", f = newCoordll.Y*100 + "", g = newCoordur.X*100 + "", h = newCoordur.Y*100 + "";
                e = e.substring(0, 10).replace(".", "");
                f = f.substring(0, 11).replace(".", "");
                g = g.substring(0, 10).replace(".", "");
                h = h.substring(0, 11).replace(".", "");
                
                Double leftX = Double.parseDouble(e);
                Double lowerY = Double.parseDouble(f);
                Double rightX = Double.parseDouble(g);
                Double upperY = Double.parseDouble(h);
                
                Line2D.Double left = new Line2D.Double(leftX, lowerY, leftX, upperY );
                Line2D.Double top = new Line2D.Double(leftX, upperY , rightX, upperY );
                Line2D.Double right = new Line2D.Double(rightX, upperY , rightX, lowerY);
                Line2D.Double bot = new Line2D.Double(rightX, lowerY, leftX, lowerY);
                
                Polygon polygon = new Polygon();
                polygon.addPoint(leftX.intValue(), lowerY.intValue());
                polygon.addPoint(leftX.intValue(), upperY.intValue());
                polygon.addPoint(rightX.intValue(), upperY.intValue());
                polygon.addPoint(rightX.intValue(), lowerY.intValue());
                
                if(usrLine.intersectsLine(left) || usrLine.intersectsLine(top) || usrLine.intersectsLine(right) || usrLine.intersectsLine(bot)) {
                    filesToRead.add(parts[4]);
                } else if(polygon.contains(xStart, yStart) || polygon.contains(xEnd, yEnd)) {
                    filesToRead.add(parts[4]);
                }
                
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

                
        
        List<Coord> points = new ArrayList<>();
//        List<Coord> result = new ArrayList<>();        
        LasReader reader = new LasReader();
        for(String fileName : filesToRead) {
            reader.open(fileName);
            try {
            List<PointRecord> results = reader.getPointsOnly();
                for(PointRecord rec : results) {
                    if(poly.contains(rec.getX(), rec.getY())) {     
                        Double vertDist = Line2D.ptLineDistSq(xStart, yStart, xEnd, yEnd, rec.getX(), rec.getY());                    
                        Double horDist = distance(rec.getX(), Integer.parseInt(a), rec.getY(), Integer.parseInt(b));
                        Double distance = Math.sqrt(vertDist + horDist);
                        points.add(new Coord(distance, ((double)rec.getZ())/100));
                    }
                }            

            } catch (LasReadException ex) {
                Exceptions.printStackTrace(ex);
            }
        }                        
        Collections.sort(points, Coord.byX);        
//        String csvString = "Distance, Elevation(ft)\n\" + ";
        String csvString = "Distance, Elevation(ft)\n"; 
        for(int i = 0; i < points.size(); i++){
//            csvString = csvString + "\"" + (points.get(i).X - points.get(0).X) + "," + points.get(i).Y + "\n\"+";
            Double kilometers = (points.get(i).X - points.get(0).X)/100*.0003048;
            BigDecimal test = new BigDecimal(kilometers);
            test = test.setScale(3, RoundingMode.HALF_UP);
            csvString = csvString + test.toString() + "," + points.get(i).Y + "\n";
        }
        return csvString;
    }
    
    /**
     * Calculates distance from one point to another
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @return 
     */
    public static Double distance(int x1, int x2, int y1, int y2){        
        Double dist = (((double)x2-(double)x1)*((double)x2-(double)x1)) + (((double)y2-(double)y1)*((double)y2-(double)y1));
        return dist;
    }   
}
