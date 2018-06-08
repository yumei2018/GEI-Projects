/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.tools;

import com.gei.las.GraphAllPoints;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author ileung
 */
@Controller
@RequestMapping("/crossSection")
public class CrossSectionServlet extends MultiActionController{
    
    private final static ProjectionInfo webmerc = Projections.Projected.getWorld().WebMercator;
    private final static ProjectionInfo wgs84 = Projections.Geographic.getWorld().WGS1984;
    private final static String utmFoot = "PROJCS[\"NAD_1983_UTM_Zone_10N\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",1640416.666666667],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-123.0],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Foot_US\",0.3048006096012192]]";
    
    @RequestMapping("/parseData")
    public void parseData(HttpServletRequest request, HttpServletResponse response) throws ProjectionException, IOException{
        //Grab the start and end coordinates in WEBMERC
        Double xstart = Double.parseDouble(request.getParameter("xstart"));
        Double ystart = Double.parseDouble(request.getParameter("ystart"));
        Double xend = Double.parseDouble(request.getParameter("xend"));
        Double yend = Double.parseDouble(request.getParameter("yend"));      
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create(); 
        String result = GraphAllPoints.getPoints(xstart, ystart, xend, yend);  
//        String gsonString = gson.toJson(result);
//        gsonString = gsonString.replace("\\\"", "\"");
//        gsonString = gsonString.substring(0, gsonString.length()-2);
//        response.getWriter().print(gsonString);
        result = result.substring(0, result.length()-1);
        response.getWriter().print(result);
    }
}
