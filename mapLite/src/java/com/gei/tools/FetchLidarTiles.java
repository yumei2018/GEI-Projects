/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template points, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.tools;


import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialClob;
import org.openide.util.Exceptions;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author ileung
 */
//@Controller
//@RequestMapping("/fetchTiles")
public class FetchLidarTiles {
    
    private final static ProjectionInfo webmerc = Projections.Projected.getWorld().WebMercator;
    private final static String utmFoot = "PROJCS[\"NAD_1983_UTM_Zone_10N\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",1640416.666666667],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-123.0],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Foot_US\",0.3048006096012192]]";
    private final static ProjectionInfo wgs84 = Projections.Geographic.getWorld().WGS1984;                
    
    /**
     * this method takes in a list of coords in webmerc
     * @param coords
     * @return 
     */                
    public List<String> getLidarTiles(List<Coord> coords, String lidarShape){
        List<String> filesToRead = new ArrayList<>();
        List<Line2D.Double> lines = new ArrayList<>();
        ProjectionInfo info = ProjectionInfo.fromEsriString(utmFoot);
        Polygon usrPolygon = new Polygon();
        for(int i = 0; i < coords.size(); i++) {             
            Coord convertCoord;
            Coord convertCoord2;
            try {                
                convertCoord = Reproject.reprojectCoordinate(coords.get(i), webmerc, info);
                usrPolygon.addPoint(((Double)convertCoord.X).intValue(), ((Double)convertCoord.Y).intValue());
                if( i < coords.size() -1){                                       
                    convertCoord2 = Reproject.reprojectCoordinate(coords.get(i+1), webmerc, info); 
                    lines.add(new Line2D.Double(convertCoord.X, convertCoord.Y, convertCoord2.X, convertCoord2.Y));                
                }
                if( i == coords.size()-1){
                    convertCoord2 = Reproject.reprojectCoordinate(coords.get(0), webmerc, info); 
                    lines.add(new Line2D.Double(convertCoord.X, convertCoord.Y, convertCoord2.X, convertCoord2.Y));                
                }
            } catch (ProjectionException ex) {
                Exceptions.printStackTrace(ex);
            }            
        }  
        return testIntersectionsShape(filesToRead, info, lines, usrPolygon, lidarShape);
    }
    
    public String getSingleTileExtent(Coord coord){
        String points = "";
        InputStream stream = this.getClass().getResourceAsStream("resources/bounds_fileName.txt");  
        ProjectionInfo info = ProjectionInfo.fromEsriString(utmFoot);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(stream))){
            String line;
            while( (line = br.readLine()) != null) {
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
                
                Double leftX = Double.parseDouble(e)/100.0;
                Double lowerY = Double.parseDouble(f)/100.0;
                Double rightX = Double.parseDouble(g)/100.0;
                Double upperY = Double.parseDouble(h)/100.0;
                
                Polygon polygon = new Polygon();
                polygon.addPoint(leftX.intValue(), lowerY.intValue());
                polygon.addPoint(leftX.intValue(), upperY.intValue());
                polygon.addPoint(rightX.intValue(), upperY.intValue());
                polygon.addPoint(rightX.intValue(), lowerY.intValue());
                
                if(polygon.contains(coord.X, coord.Y)) {
                    for(int j = 0;  j < polygon.npoints; j++){
                        Coord coordPoly = new Coord(polygon.xpoints[j], polygon.ypoints[j]);
                        Coord coordPolyRep = Reproject.reprojectCoordinate(coordPoly, info, wgs84);
                        points = points + coordPolyRep.X + "," + coordPolyRep.Y + "--";
                    }
                    break;
                }
            }                    
        } catch (IOException | ProjectionException ex) {
            Exceptions.printStackTrace(ex);                
        }
        return points;
    }
    
    public Polygon getExtentsTile(String fileName){
        InputStream stream = this.getClass().getResourceAsStream("resources/bounds_fileName.txt"); 
        ProjectionInfo info = ProjectionInfo.fromEsriString(utmFoot);
        Polygon polygon = new Polygon();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(stream))){
            String line;
            while( (line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if(fileName.equals(parts[4])){
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

                    Double leftX = Double.parseDouble(e)/100.0;
                    Double lowerY = Double.parseDouble(f)/100.0;
                    Double rightX = Double.parseDouble(g)/100.0;
                    Double upperY = Double.parseDouble(h)/100.0;
                    
                    polygon.addPoint(leftX.intValue(), lowerY.intValue());
                    polygon.addPoint(leftX.intValue(), upperY.intValue());
                    polygon.addPoint(rightX.intValue(), upperY.intValue());
                    polygon.addPoint(rightX.intValue(), lowerY.intValue());
                    break;
                }
            }
        } catch (IOException | ProjectionException ex) {
            Exceptions.printStackTrace(ex);                
        }        
        return polygon;
    }
    
    public String getSingleTile(Coord coord){
        String file = "";
        InputStream stream = this.getClass().getResourceAsStream("resources/bounds_fileName.txt");  
        ProjectionInfo info = ProjectionInfo.fromEsriString(utmFoot);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(stream))){
            String line;
            while( (line = br.readLine()) != null) {
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
                
                Double leftX = Double.parseDouble(e)/100.0;
                Double lowerY = Double.parseDouble(f)/100.0;
                Double rightX = Double.parseDouble(g)/100.0;
                Double upperY = Double.parseDouble(h)/100.0;
                
                Polygon polygon = new Polygon();
                polygon.addPoint(leftX.intValue(), lowerY.intValue());
                polygon.addPoint(leftX.intValue(), upperY.intValue());
                polygon.addPoint(rightX.intValue(), upperY.intValue());
                polygon.addPoint(rightX.intValue(), lowerY.intValue());
                
                if(polygon.contains(coord.X, coord.Y)) {
                    file = parts[4];
                    break;
                }
            }                    
        } catch (IOException | ProjectionException ex) {
            Exceptions.printStackTrace(ex);                
        }
        return file;
    }
        
    /**
     * This method tests which lidar tiles a polygon intersects using bounds txt file
     * @param filesToRead
     * @param info
     * @param lines
     * @param usrPolygon
     * @return 
     */
    public List<String> testIntersections(List<String> filesToRead, ProjectionInfo info, List<Line2D.Double> lines, Polygon usrPolygon){
        InputStream stream = this.getClass().getResourceAsStream("resources/bounds_fileName.txt");                
        try(BufferedReader br = new BufferedReader(new InputStreamReader(stream))){
            String line;
            while( (line = br.readLine()) != null) {
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
                
                Double leftX = Double.parseDouble(e)/100.0;
                Double lowerY = Double.parseDouble(f)/100.0;
                Double rightX = Double.parseDouble(g)/100.0;
                Double upperY = Double.parseDouble(h)/100.0;
                
                Line2D.Double left = new Line2D.Double(leftX, lowerY, leftX, upperY );
                Line2D.Double top = new Line2D.Double(leftX, upperY , rightX, upperY );
                Line2D.Double right = new Line2D.Double(rightX, upperY , rightX, lowerY);
                Line2D.Double bot = new Line2D.Double(rightX, lowerY, leftX, lowerY);
                
                Polygon polygon = new Polygon();
                polygon.addPoint(leftX.intValue(), lowerY.intValue());
                polygon.addPoint(leftX.intValue(), upperY.intValue());
                polygon.addPoint(rightX.intValue(), upperY.intValue());
                polygon.addPoint(rightX.intValue(), lowerY.intValue());
                
                for (Line2D.Double usrLine : lines) {
                    if(usrPolygon.contains(leftX.intValue(), lowerY.intValue()) || usrPolygon.contains(leftX.intValue(), upperY.intValue()) || 
                       usrPolygon.contains(rightX.intValue(), upperY.intValue()) || usrPolygon.contains(rightX.intValue(), lowerY.intValue())){
                        filesToRead.add(parts[4]);
                        break;
                    }
                    else if(usrLine.intersectsLine(left) || usrLine.intersectsLine(top) || usrLine.intersectsLine(right) || usrLine.intersectsLine(bot)){
                        filesToRead.add(parts[4]);
                        break;
                    } else if(polygon.contains(usrLine.x1, usrLine.y1) || polygon.contains(usrLine.x2, usrLine.y2)) {
                        filesToRead.add(parts[4]);
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            
        }
        return filesToRead;
    }
    
    /**
     * This method will use the shapeFile and return lidar index 
     * @param filesToRead
     * @param info
     * @param lines
     * @param usrPolygon
     * @param lidarShape
     * @return 
     */
    public List<String> testIntersectionsShape(List<String> filesToRead, ProjectionInfo info, List<Line2D.Double> lines, Polygon usrPolygon, String lidarShape) {
        int count = 0;
        InputStream shp = null, shx = null, dbf = null, prj = null;
        switch(lidarShape){
            case "lidarTest":
                shp = this.getClass().getResourceAsStream("resources/Lidar.shp");     
                shx = this.getClass().getResourceAsStream("resources/Lidar.shx");
                dbf = this.getClass().getResourceAsStream("resources/Lidar.dbf");
                prj = this.getClass().getResourceAsStream("resources/Tile_Index_Final_LiDAR_01312014.prj");
                break;
            case "lidarDwrFinal":
                shp = this.getClass().getResourceAsStream("resources/Tile_Index_Final_LiDAR_01312014.shp");     
                shx = this.getClass().getResourceAsStream("resources/Tile_Index_Final_LiDAR_01312014.shx");
                dbf = this.getClass().getResourceAsStream("resources/Tile_Index_Final_LiDAR_01312014.dbf");
                prj = this.getClass().getResourceAsStream("resources/Tile_Index_Final_LiDAR_01312014.prj");
                break;
            case "lidarDwrInitial":
                shp = this.getClass().getResourceAsStream("resources/Tile_Index_Initial_LiDAR_01312014.shp");     
                shx = this.getClass().getResourceAsStream("resources/Tile_Index_Initial_LiDAR_01312014.shx");
                dbf = this.getClass().getResourceAsStream("resources/Tile_Index_Initial_LiDAR_01312014.dbf");
                prj = this.getClass().getResourceAsStream("resources/Tile_Index_Initial_LiDAR_01312014.prj");
                break;
            case "lidarDwrOrtho":
                shp = this.getClass().getResourceAsStream("resources/Tile_Index_Ortho_10182010.shp");     
                shx = this.getClass().getResourceAsStream("resources/Tile_Index_Ortho_10182010.shx");
                dbf = this.getClass().getResourceAsStream("resources/Tile_Index_Ortho_10182010.dbf");
                prj = this.getClass().getResourceAsStream("resources/Tile_Index_Ortho_10182010.prj");
                break;                
        }        
        ShapefileReader reader = new ShapefileReader(); 
        String allShapes = "";                
        try {
            reader.open(shp, shx, dbf, prj); 
            ProjectionInfo sourceInfo;
            if(reader.getProjection() != null) {
                sourceInfo = ProjectionInfo.fromEsriString(reader.getProjection());
            } else {
                sourceInfo = ProjectionInfo.fromEsriString(utmFoot);
            }
            FeatureSet fs = reader.getFeatures();
            for(Feature f : fs.getFeatures()) {      
                count++;
                String name = f.getAttributes().get("Tile");
                if(name == null) {
                    name = f.getAttributes().get("TILE");
                }
                if(name == null) {
                    name = f.getAttributes().get("Name");
                }
                String test = f.getAttributes().get("FID");              
                if(name.equals("0942n0337e")){
                    boolean stop = true;
                }
                Polygon poly = new Polygon();  
                List<Line2D.Double> lidarLines = new ArrayList<>();
                List<Point> shapePointList = new ArrayList<>(); 
                for(Part part : f.getShape().getParts()){                                                            
                    for(int i = 0; i < part.getCoordinates().size()-1; i++){                        
                        Coord transform = Reproject.reprojectCoordinate(part.getCoordinates().get(i), sourceInfo, info);                       
                        Coord transform2 = Reproject.reprojectCoordinate(part.getCoordinates().get(i+1), sourceInfo, info);                       
                        Point p = new Point((int)transform.X, (int)transform.Y);
                        poly.addPoint((int)transform.X, (int)transform.Y);                        
                        shapePointList.add(p);
                        lidarLines.add(new Line2D.Double(transform.X, transform.Y, transform2.X, transform2.Y));                                                
                    }  
                }                
                for (Line2D.Double usrLine : lines) {
                    //tests if the features contains any of the points that the user draws
                    if(poly.contains(usrLine.x1, usrLine.y1) || poly.contains(usrLine.x2, usrLine.y2)) {
                        if(!filesToRead.contains(name)) {
                            filesToRead.add(name);
                        }
                        break;
                    }
                    //tests if the features intersects any of the lines the user draws
                    for(Line2D.Double shapeLine : lidarLines){
                        if(usrLine.intersectsLine(shapeLine)){
                            if(!filesToRead.contains(name)) {
                               filesToRead.add(name);
                            }
                            break;
                        }
                    }
                }                
                //tests if the user polygon contains any points belonging to the features
                for(Point testPoint : shapePointList){
                    if(usrPolygon.contains(testPoint)) {
                        if(!filesToRead.contains(name)) {
                            filesToRead.add(name);
                        }
                        break;
                    }
                }   
            }                                                                                              
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return filesToRead;
    }
}
