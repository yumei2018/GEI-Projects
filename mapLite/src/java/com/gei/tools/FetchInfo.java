/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.tools;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openide.util.Exceptions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author ileung
 */
@Controller
@RequestMapping("/shape")
public class FetchInfo extends MultiActionController {
    
    private final static ProjectionInfo utmFoot = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_UTM_Zone_10N\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",1640416.666666667],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-123.0],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Foot_US\",0.3048006096012192]]");    
    private final static ProjectionInfo webmerc = Projections.Projected.getWorld().WebMercator;
    private final static ProjectionInfo wgs84 = Projections.Geographic.getWorld().WGS1984;
    private final static ProjectionInfo riverProj = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_UTM_Zone_10N\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",500000.0],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-123.0],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Meter\",1.0]");
    private static String shpFile, shxFile, dbfFile, prjFile;
    private static ProjectionInfo sourceInfo, destInfo;
    
    public String fetchInfo(List<Coord> coords, String param){
        setFiles(param);
        InputStream shp = this.getClass().getResourceAsStream(shpFile);     
        InputStream shx = this.getClass().getResourceAsStream(shxFile);
        InputStream dbf = this.getClass().getResourceAsStream(dbfFile);
        InputStream prj = this.getClass().getResourceAsStream(prjFile);
        ShapefileReader reader = new ShapefileReader();
        try{
            reader.open(shp, shx, dbf, prj);
            FeatureSet fs = reader.getFeatures();
            for(Feature f : fs.getFeatures()) {
                String name = f.getAttributes().get("NAME");
                Shape shape = f.getShape();
                for(Part prt : shape.getParts()){
                    for(Coord coord : coords){
                        Coord newCoord = Reproject.reprojectCoordinate(coord, webmerc, destInfo);
                        if(prt.contains(newCoord)){
                            return name;
                        }
                    }                    
                }
            }
        } catch (IOException | ProjectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
    
    public String fetchRiver(List<Coord> coords, String param){
        setFiles(param);
        InputStream shp = this.getClass().getResourceAsStream(shpFile);     
        InputStream shx = this.getClass().getResourceAsStream(shxFile);
        InputStream dbf = this.getClass().getResourceAsStream(dbfFile);
        InputStream prj = this.getClass().getResourceAsStream(prjFile);
        ShapefileReader reader = new ShapefileReader();
        try{
            reader.open(shp, shx, dbf, prj);
            sourceInfo = ProjectionInfo.fromEsriString(reader.getProjection());
            FeatureSet fs = reader.getFeatures();
            for(Feature f : fs.getFeatures()) {
                String name = f.getAttributes().get("Label");
                Shape shape = f.getShape();                                
                List<Line2D.Double> usrLines = new ArrayList<>();
                for(int i = 0; i < coords.size(); i++) {                    
                    if(i==coords.size()-1) {
                        usrLines.add(new Line2D.Double(coords.get(i).X, coords.get(i).Y, coords.get(0).X, coords.get(0).Y));                    
                    } else {
                        usrLines.add(new Line2D.Double(coords.get(i).X, coords.get(i).Y, coords.get(i+1).X, coords.get(i+1).Y));                    
                    }                    
                }
                for(Part prt : shape.getParts()){                    
                    for(int i = 0; i < prt.getCoordinates().size()-1; i++){
                        Coord newCoord = Reproject.reprojectCoordinate(prt.getCoordinates().get(i), sourceInfo, webmerc);
                        Coord newCoord1 = Reproject.reprojectCoordinate(prt.getCoordinates().get(i+1), sourceInfo, webmerc);
                        Line2D.Double line = new Line2D.Double(newCoord.X, newCoord.Y, newCoord1.X, newCoord1.Y);                       
                        for(Line2D.Double usrLine : usrLines) {
                            if(line.intersectsLine(usrLine)){
                                return name;
                            }
                        }                                                                    
                    }
                }
            }
        }catch (IOException | ProjectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
    
    public String fetchLevee(List<Coord> coords, String param){
        setFiles(param);
        InputStream shp = this.getClass().getResourceAsStream(shpFile);     
        InputStream shx = this.getClass().getResourceAsStream(shxFile);
        InputStream dbf = this.getClass().getResourceAsStream(dbfFile);
        InputStream prj = this.getClass().getResourceAsStream(prjFile);
        ShapefileReader reader = new ShapefileReader();
        try{
            reader.open(shp, shx, dbf, prj);
            FeatureSet fs = reader.getFeatures();
            for(Feature f : fs.getFeatures()) {
                String name = f.getAttributes().get("SEG_NAME") + " " + f.getAttributes().get("LIS_CODE");
                Shape shape = f.getShape();                                
                sourceInfo = ProjectionInfo.fromEsriString(reader.getProjection());
                List<Line2D.Double> usrLines = new ArrayList<>();
                for(int i = 0; i < coords.size(); i++) {                    
                    if(i==coords.size()-1) {
                        usrLines.add(new Line2D.Double(coords.get(i).X, coords.get(i).Y, coords.get(0).X, coords.get(0).Y));                    
                    } else {
                        usrLines.add(new Line2D.Double(coords.get(i).X, coords.get(i).Y, coords.get(i+1).X, coords.get(i+1).Y));                    
                    }                    
                }
                for(Part prt : shape.getParts()){                    
                    for(int i = 0; i < prt.getCoordinates().size()-1; i++){
                        Coord newCoord = Reproject.reprojectCoordinate(prt.getCoordinates().get(i), sourceInfo, webmerc);
                        Coord newCoord1 = Reproject.reprojectCoordinate(prt.getCoordinates().get(i+1), sourceInfo, webmerc);
                        Line2D.Double line = new Line2D.Double(newCoord.X, newCoord.Y, newCoord1.X, newCoord1.Y);                       
                        for(Line2D.Double usrLine : usrLines) {
                            if(line.intersectsLine(usrLine)){
                                return name;
                            }
                        }                                                                    
                    }
                } 
            }
        } catch (IOException | ProjectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        return null;
    }
    
    public void setFiles(String param){
        switch(param){
            case "county": 
                shpFile = "resources/CntyBnds_ESRI.shp";
                shxFile = "resources/CntyBnds_ESRI.shx";
                dbfFile = "resources/CntyBnds_ESRI.dbf";
                prjFile = "resources/CntyBnds_ESRI.prj";
                destInfo = wgs84;  
                break;
            case "river":
                shpFile = "resources/Major_Rivers.shp";
                shxFile = "resources/Major_Rivers.shx";
                dbfFile = "resources/Major_Rivers.dbf";
                prjFile = "resources/Major_Rivers.prj";
                break;
            case "levee":
                shpFile = "resources/Levee_Lines_UTM.shp";
                shxFile = "resources/Levee_Lines_UTM.shx";
                dbfFile = "resources/Levee_Lines_UTM.dbf";
                prjFile = "resources/Levee_Lines_UTM.prj";
                break;
            case "lidar":
                shpFile = "resources/Tile_Index_Final_LiDAR_03132014.shp";
                shxFile = "resources/Tile_Index_Final_LiDAR_03132014.shx";
                dbfFile = "resources/Tile_Index_Final_LiDAR_03132014.dbf";
                prjFile = "resources/Tile_Index_Final_LiDAR_03132014.prj";
                break;
        }
    }
}
