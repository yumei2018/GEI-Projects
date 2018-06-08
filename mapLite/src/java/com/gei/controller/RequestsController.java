/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.nocrala.tools.gis.data.esri.shapefile.exception.InvalidShapeFileException;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/** 
 *
 * @author ileung
 */
@Controller
@RequestMapping("/upload")
public class RequestsController extends MultiActionController{
    
    MultipartFile shpmpf;
    MultipartFile shxmpf;
    MultipartFile dbfmpf;
    MultipartFile prjmpf;
    private final static ProjectionInfo webmerc = Projections.Projected.getWorld().WebMercator;
    private final static ProjectionInfo wgs84 = Projections.Geographic.getWorld().WGS1984;
    private final static String utmFoot = "PROJCS[\"NAD_1983_UTM_Zone_10N\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",1640416.666666667],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-123.0],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Foot_US\",0.3048006096012192]]";    
    /**
     * This takes in WGS84 Coordinates and returns them as WGS84 JSON for javascript to render as a graphic.
     * @param request
     * @param response
     * @throws IOException
     * @throws IllegalStateException
     * @throws ServletException
     * @throws InvalidShapeFileException 
     */
    @RequestMapping("/uploadFile")    
    public void uploadFile(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException, IllegalStateException, ServletException, InvalidShapeFileException, ProjectionException{
        ProjectionInfo lidar = ProjectionInfo.fromEsriString(utmFoot);
        Iterator<String> itr =  request.getFileNames();
        String filename = null;
        while(itr.hasNext()){
            filename = itr.next();
            setMultipleFiles(request, filename);
        }       
        String projectionType = request.getParameter("projectionType");
        InputStream shp = shpmpf.getInputStream();
        InputStream shx = shxmpf.getInputStream();
        InputStream dbf = dbfmpf.getInputStream();
        InputStream prj = prjmpf.getInputStream();
        ShapefileReader reader = new ShapefileReader();        
        reader.open(shp, shx, dbf, prj);
        FeatureSet featureSet = reader.getFeatures();
        ProjectionInfo sourceInfo = ProjectionInfo.fromEsriString(reader.getProjection());
        //write the shapefile's data to response string
        String allShapes = "";        
        for(Feature f : featureSet.getFeatures()){
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create(); 
            for(gov.ca.water.shapelite.Part part : f.getShape().getParts()){          
                List<Coord> listCoord = new ArrayList<>();  
                if(sourceInfo == null) {
                    switch (projectionType){
                        case "webmerc":
                            for(Coord coord : part.getCoordinates()){                                
                                listCoord = (part.getCoordinates());
                            }                            
                            break;
                        case "UTMFoot":
                            for(Coord coord : part.getCoordinates()){
                                listCoord.add(Reproject.reprojectCoordinate(coord, lidar, webmerc));
                            }
                            break;
                        case "WGS84":
                            for(Coord coord : part.getCoordinates()){
                                listCoord.add(Reproject.reprojectCoordinate(coord, wgs84, webmerc));
                            }
                            break;
                    }
                } else {
                    for(Coord coord : part.getCoordinates()){
                        listCoord.add(Reproject.reprojectCoordinate(coord, sourceInfo, webmerc));
                    }
                }
                
                String test = gson.toJson(listCoord); 
                test = test.replace(",\"Z\":0.0,\"M\":0.0", "").replace("[{","").replace("\"X\":","").replace("\"Y\":","").replace("}]","");
                test = test.replace("},{","],[");
                allShapes = allShapes + test + "--";                                
            }
        }
        
        //writes graphics back to javascript
        response.getWriter().print(allShapes);
    }       
    
    public void setFiles(MultipartHttpServletRequest request, String fileName) {        
        switch (fileName){
            case "shp": 
                shpmpf = request.getFile(fileName);
                break;
            case "shx": 
                shxmpf = request.getFile(fileName);                
                break;
            case "dbf": 
                dbfmpf = request.getFile(fileName);
                break;       
        }
    }
    
    public void setMultipleFiles(MultipartHttpServletRequest request, String fileName){
        MultiValueMap<String, MultipartFile> fileMap = request.getMultiFileMap();
        List<MultipartFile> files = fileMap.get(fileName);
        for(int i = 0; i < files.size(); i++){
            if(files.get(i).getOriginalFilename().contains("shp")){                
                shpmpf = files.get(i);
            } else if(files.get(i).getOriginalFilename().contains("dbf")){                
                dbfmpf = files.get(i);
            } else if(files.get(i).getOriginalFilename().contains("prj")){                
                prjmpf = files.get(i);
            } else if(files.get(i).getOriginalFilename().contains("shx")){                
                shxmpf = files.get(i);
            }
        }
    }
}
