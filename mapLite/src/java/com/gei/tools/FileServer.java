/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.tools;

import com.gei.controller.UserController;
import com.gei.las.GraphAllPoints;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileWriter;
import static gov.ca.water.shapelite.io.ShapefileWriter.setExtension;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.awt.Polygon;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialClob;
import org.openide.util.Exceptions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController; 

/**
 *
 * @author ileung
 */
@Controller
@RequestMapping("/files") 
public class FileServer extends MultiActionController{
    
    private final static ProjectionInfo webmerc = Projections.Projected.getWorld().WebMercator;
    private final static ProjectionInfo wgs84 = Projections.Geographic.getWorld().WGS1984;
    private final static String utmFoot = "PROJCS[\"NAD_1983_UTM_Zone_10N\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",1640416.666666667],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-123.0],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Foot_US\",0.3048006096012192]]";
    
    // <editor-fold defaultstate="collapsed" desc="Load ShapeFile Functions">
    /**
     * Loads the levee shapefile
     * @param request
     * @param response
     * @throws IOException 
     */
    @RequestMapping("/levee")
    public void getLevee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStream shp = this.getClass().getResourceAsStream("resources/SimpleLevees.shp");     
        InputStream shx = this.getClass().getResourceAsStream("resources/SimpleLevees.shx");
        InputStream dbf = this.getClass().getResourceAsStream("resources/SimpleLevees.dbf");
        InputStream prj = this.getClass().getResourceAsStream("resources/SimpleLevees.prj");
        response.getWriter().print(formatShapeFileReproject(shp, shx, dbf, prj));
    }
    
    /**
     * Loads the river shapefile
     * @param request
     * @param response
     * @throws IOException 
     */
    @RequestMapping("/river")
    public void getRiver(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStream shp = this.getClass().getResourceAsStream("resources/SimpleRivers.shp");     
        InputStream shx = this.getClass().getResourceAsStream("resources/SimpleRivers.shx");
        InputStream dbf = this.getClass().getResourceAsStream("resources/SimpleRivers.dbf");
        InputStream prj = this.getClass().getResourceAsStream("resources/SimpleRivers.prj");
        response.getWriter().print(formatShapeFileReproject(shp, shx, dbf, prj));
    }
     
    /**
     *Loads the county shapefile
     * @param request
     * @param response
     * @throws IOException 
     */
    @RequestMapping("/county")
    public void getCounty(HttpServletRequest request, HttpServletResponse response) throws IOException{                     
        InputStream shp = this.getClass().getResourceAsStream("resources/SimpleCounties.shp");     
        InputStream shx = this.getClass().getResourceAsStream("resources/SimpleCounties.shx");
        InputStream dbf = this.getClass().getResourceAsStream("resources/SimpleCounties.dbf");
        InputStream prj = this.getClass().getResourceAsStream("resources/SimpleCounties.prj");
        response.getWriter().print(formatShapeFile(shp, shx, dbf, prj));
    }
    
    /**
     * Loads the lidar shapefile
     * @param request
     * @param response
     * @throws IOException 
     */
    @RequestMapping("/lidar")
    public void getLidar(HttpServletRequest request, HttpServletResponse response) throws IOException{                     
        InputStream shp = this.getClass().getResourceAsStream("resources/Lidar.shp");     
        InputStream shx = this.getClass().getResourceAsStream("resources/Lidar.shx");
        InputStream dbf = this.getClass().getResourceAsStream("resources/Lidar.dbf");        
        response.getWriter().print(formatShapeFile(shp, shx, dbf, null));
    }
    
    /**
     * This method is used to figure out which shapefile to "display" not query
     * @param request
     * @param response
     * @throws IOException 
     */
    @RequestMapping("/dwrlidar")
    public void getDwrLidar(HttpServletRequest request, HttpServletResponse response) throws IOException{        
        InputStream shp = null, shx = null, dbf = null, prj = null;
        switch(request.getParameter("type")) {
            case "test":
                shp = this.getClass().getResourceAsStream("resources/Lidar.shp");     
                shx = this.getClass().getResourceAsStream("resources/Lidar.shx");
                dbf = this.getClass().getResourceAsStream("resources/Lidar.dbf");
                prj = null;
                break;
            case "final":
                shp = this.getClass().getResourceAsStream("resources/Tile_Index_Final_LiDAR_Simple.shp");     
                shx = this.getClass().getResourceAsStream("resources/Tile_Index_Final_LiDAR_Simple.shx");
                dbf = this.getClass().getResourceAsStream("resources/Tile_Index_Final_LiDAR_Simple.dbf");
                prj = this.getClass().getResourceAsStream("resources/Tile_Index_Final_LiDAR_Simple.prj");
                break;
            case "initial":
                shp = this.getClass().getResourceAsStream("resources/Tile_Index_Initial_LiDAR_Simple.shp");     
                shx = this.getClass().getResourceAsStream("resources/Tile_Index_Initial_LiDAR_Simple.shx");
                dbf = this.getClass().getResourceAsStream("resources/Tile_Index_Initial_LiDAR_Simple.dbf");
                prj = this.getClass().getResourceAsStream("resources/Tile_Index_Initial_LiDAR_Simple.prj");
                break;
            case "ortho":
                shp = this.getClass().getResourceAsStream("resources/Tile_Index_Ortho_Simple.shp");     
                shx = this.getClass().getResourceAsStream("resources/Tile_Index_Ortho_Simple.shx");
                dbf = this.getClass().getResourceAsStream("resources/Tile_Index_Ortho_Simple.dbf");
                prj = this.getClass().getResourceAsStream("resources/Tile_Index_Ortho_Simple.prj");
                break;
        }
        if(shp != null && shx != null && dbf != null && prj != null) {
            response.getWriter().print(formatShapeFileReproject(shp, shx, dbf, prj));
        } 
        //used for testing
        else if(prj == null){
            response.getWriter().print(formatShapeFile(shp, shx, dbf, prj));
        }
            
    }
    // </editor-fold>
    
    @RequestMapping("/saveshape")
    public void saveShape(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ProjectionException, ShapefileException{
        ProjectionInfo nad = Projections.Nad83_UTM_Zone10_Foot;
        UserController uc = new UserController();
        String geoType = request.getParameter("geoType");
        SerialClob geo = new SerialClob(request.getParameter("geometry").toCharArray());
        List<Coord> coords = uc.parseGeometry(geo, geoType);
        FeatureSet features = new FeatureSet();
        features.setProjection(nad.toEsriString());
        List<Coord> prjCoords = Reproject.reprojectCoordinates(coords, webmerc, nad);
        Feature f = new Feature();
        Shape shape = new Shape(ShapeType.Polygon);
        Part part = new Part();
        for(Coord coord : prjCoords){
            part.getCoordinates().add(coord);
        }
        shape.getParts().add(part);
        shape.calculateBounds();
        f.setShape(shape);
        features.getFeatures().add(f);
        ShapefileWriter writer = new ShapefileWriter();
        String shp = "C:\\Temp\\drawn_polygon.shp";                
        writer.write(features, shp);        
        String shpString = setExtension(shp, ".shp");
        String shxString = setExtension(shp, ".shx");
        String dbfString = setExtension(shp, ".dbf");
        String prjString = setExtension(shp, ".prj");
        String files = shpString + "  " + shxString + "  " + dbfString + "  " + prjString;        
        response.getWriter().print(files); 
        
    }
    
    /**
     * This method paints the lidar tile selected.  The coordinate passed in is webmerc and returns the extent of the lidar tile
     * @param request
     * @param response
     * @throws IOException
     * @throws ProjectionException 
     */
    @RequestMapping("/drawSelection")
    public void getExtents(HttpServletRequest request, HttpServletResponse response) throws IOException, ProjectionException{
        double xCoord = Double.parseDouble(request.getParameter("x"));
        double yCoord = Double.parseDouble(request.getParameter("y"));
        Coord webMerc = new Coord(xCoord, yCoord);
        ProjectionInfo lidar = ProjectionInfo.fromEsriString(utmFoot);
        Coord newCoord = Reproject.reprojectCoordinate(webMerc, webmerc, lidar);
        FetchLidarTiles fl = new FetchLidarTiles();      
        response.getWriter().print(fl.getSingleTileExtent(newCoord));
    }        
    
    /**
     * This method downloads the lidar tiled specified from an X, Y point with webMerc projection
     * @param request
     * @param response
     * @throws ProjectionException
     * @throws IOException 
     */
    @RequestMapping("/lidarDownload")
    public void download(HttpServletRequest request, HttpServletResponse response) throws ProjectionException, IOException {
        double xCoord = Double.parseDouble(request.getParameter("x"));
        double yCoord = Double.parseDouble(request.getParameter("y"));
        Coord webMerc = new Coord(xCoord, yCoord);
        ProjectionInfo lidar = ProjectionInfo.fromEsriString(utmFoot);
        Coord newCoord = Reproject.reprojectCoordinate(webMerc, webmerc, lidar);
        FetchLidarTiles fl = new FetchLidarTiles();      
        response.getWriter().print(fl.getSingleTile(newCoord));                    
    }
    
    @RequestMapping("/shapefileDownload")
    public void downloadShapefile(HttpServletRequest request, HttpServletResponse response) throws ProjectionException, IOException, ShapefileException{
        double xCoord = Double.parseDouble(request.getParameter("x"));
        double yCoord = Double.parseDouble(request.getParameter("y"));
        Coord webMerc = new Coord(xCoord, yCoord);
        ProjectionInfo lidar = ProjectionInfo.fromEsriString(utmFoot);
        Coord newCoord = Reproject.reprojectCoordinate(webMerc, webmerc, lidar);
        FetchLidarTiles fl = new FetchLidarTiles();      
        List<String> filesToRead = new ArrayList<>();
        String filename = fl.getSingleTile(newCoord);
        filesToRead.add(filename);
        Polygon poly = fl.getExtentsTile(filename);
        GraphAllPoints g = new GraphAllPoints();
        String shapefileName = g.generateShapeFileFromLidar(filesToRead, poly);
        String shp = setExtension(shapefileName, ".shp");
        String shx = setExtension(shapefileName, ".shx");
        String dbf = setExtension(shapefileName, ".dbf");
        String files = shp + "  " + shx + "  " + dbf;        
        response.getWriter().print(files);  
    }
    
    /**
     *This will create a shapefile from the shape the user uploaded.  The shapefile will 
     * contain lidar points within the boundaries of the shape.     
     * The data is passed in as wgs84 coordinates
     * @param request
     * @param response 
     */
    @RequestMapping("/userShape")
    public void downloadUserShape(HttpServletRequest request, HttpServletResponse response) throws ProjectionException, ShapefileException, IOException {
        String coordReq = request.getParameter("coord");       
        coordReq = coordReq.substring(0, coordReq.length()-1);
        String[] coords = coordReq.split(",");
        List<Coord> coordExtent = new ArrayList<>();
        Polygon poly = new Polygon();
        ProjectionInfo lidar = ProjectionInfo.fromEsriString(utmFoot);
        for(int i = 0; i < coords.length/2; i++){
            Double lat = Double.parseDouble(coords[(i * 2)]);
            Double lng = Double.parseDouble(coords[(i * 2) + 1]);
            Coord coord = new Coord(lat, lng);
            coord = Reproject.reprojectCoordinate(coord, wgs84, webmerc);
            coordExtent.add(coord);
            coord = Reproject.reprojectCoordinate(coord, webmerc, lidar);
            poly.addPoint(intval(coord.X), intval(coord.Y));
        }
        //get the list of tile names the polygon intersects
        FetchLidarTiles flt = new FetchLidarTiles();   
        List<String> fileNames = flt.getLidarTiles(coordExtent, request.getParameter("activeLidarShape"));
        GraphAllPoints g = new GraphAllPoints();
        String shapefileName = g.generateShapeFileFromLidar(fileNames, poly);
        String shp = setExtension(shapefileName, ".shp");
        String shx = setExtension(shapefileName, ".shx");
        String dbf = setExtension(shapefileName, ".dbf");
        String files = shp + "  " + shx + "  " + dbf;        
        response.getWriter().print(files);   
        boolean stop = true;
    }
    
    @RequestMapping("/shapefileExtent") 
    public void downloadShapeFileExtent(HttpServletRequest request, HttpServletResponse response) throws ProjectionException, ShapefileException, IOException {
        double llx = Double.parseDouble(request.getParameter("xmin"));
        double urx = Double.parseDouble(request.getParameter("xmax"));
        double lly = Double.parseDouble(request.getParameter("ymin"));
        double ury = Double.parseDouble(request.getParameter("ymax"));
        List<Coord> coordExtent = new ArrayList<>();
        Coord coordll = new Coord(llx, lly);
        Coord coordur = new Coord(urx, ury);
        Coord coordul = new Coord(llx, ury);
        Coord coordlr = new Coord(urx, lly);
        //coordExtent is in webmerc
        coordExtent.add(coordll);
        coordExtent.add(coordul);
        coordExtent.add(coordur);
        coordExtent.add(coordlr);
        
        //comparing polygons using utmfoot projection
        Polygon poly = new Polygon();
        ProjectionInfo lidar = ProjectionInfo.fromEsriString(utmFoot);
        Coord newll = Reproject.reprojectCoordinate(coordll, webmerc, lidar);
        Coord newur = Reproject.reprojectCoordinate(coordur, webmerc, lidar);
        Coord newul = Reproject.reprojectCoordinate(coordul, webmerc, lidar);
        Coord newlr = Reproject.reprojectCoordinate(coordlr, webmerc, lidar);
        poly.addPoint(intval(newll.X), intval(newll.Y));
        poly.addPoint(intval(newul.X), intval(newul.Y));
        poly.addPoint(intval(newur.X), intval(newur.Y));
        poly.addPoint(intval(newlr.X), intval(newlr.Y));        
        FetchLidarTiles flt = new FetchLidarTiles();   
        List<String> fileNames = flt.getLidarTiles(coordExtent, request.getParameter("activeLidarShape"));
        GraphAllPoints g = new GraphAllPoints();
        String shapefileName = g.generateShapeFileFromLidar(fileNames, poly);
        String shp = setExtension(shapefileName, ".shp");
        String shx = setExtension(shapefileName, ".shx");
        String dbf = setExtension(shapefileName, ".dbf");
        String files = shp + "  " + shx + "  " + dbf;        
        response.getWriter().print(files);  
        
    }
    
    /**
     * Takes coordinates in webMerc
     * @param request
     * @param response
     * @throws IOException 
     */
    @RequestMapping("/lidarExtent")
    public void downloadExtent(HttpServletRequest request, HttpServletResponse response) throws IOException, ProjectionException{
        double llx = Double.parseDouble(request.getParameter("xmin"));
        double urx = Double.parseDouble(request.getParameter("xmax"));
        double lly = Double.parseDouble(request.getParameter("ymin"));
        double ury = Double.parseDouble(request.getParameter("ymax"));
        String projection = request.getParameter("proj");
        List<Coord> coordExtent = new ArrayList<>();
        Coord coordll = new Coord(llx, lly);
        Coord coordur = new Coord(urx, ury);
        Coord coordul = new Coord(llx, ury);
        Coord coordlr = new Coord(urx, lly);
        if(projection.equals("wgs84")){ 
            coordll = Reproject.reprojectCoordinate(coordll, wgs84, webmerc);
            coordur = Reproject.reprojectCoordinate(coordur, wgs84, webmerc);
            coordul = Reproject.reprojectCoordinate(coordul, wgs84, webmerc);
            coordlr = Reproject.reprojectCoordinate(coordlr, wgs84, webmerc);
        }
        coordExtent.add(coordll);
        coordExtent.add(coordul);
        coordExtent.add(coordur);
        coordExtent.add(coordlr);        
        FetchLidarTiles flt = new FetchLidarTiles();       
        for(String fileName : flt.getLidarTiles(coordExtent, request.getParameter("activeLidarShape"))){
            response.getWriter().print(fileName + "  ");
        }
    }
    
    /**
     * This downloads lidar tiles
     * @param request
     * @param response
     * @throws FileNotFoundException
     * @throws IOException 
     */
    @RequestMapping("/downloadFile") 
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException{
        String filenamesList = request.getParameter("fileName");
//        String outName = request.getParameter("outName");
        String[] filenames = filenamesList.split("  ");        
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"LiDAR_Data.zip\"");
        response.setHeader(headerKey, headerValue);  
        response.setContentType("application/zip");
        ZipOutputStream outZip = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream(), 4096));
        for(String filename :filenames){
            File file = new File(filename);        
            try (FileInputStream inStream = new FileInputStream(file)) {                
//                response.setContentLength((int) file.length());                                                         
                outZip.putNextEntry(new ZipEntry(file.getName()));
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outZip.write(buffer, 0, bytesRead);
                }
                outZip.closeEntry();
            }              
        }   
        
        outZip.flush();
        outZip.close();
        
    }        
    
    @RequestMapping("/downloadData") 
    public void downloadAgreement(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException{
        String fileString = request.getParameter("fileName");
//        String contextPath = getServletContext().getRealPath(File.separator);
        File file = new File(fileString);

        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=data.csv");
        response.setContentLength((int) file.length());

        FileInputStream fileInputStream = new FileInputStream(file);
        try (OutputStream responseOutputStream = response.getOutputStream()) {
            int bytes;
            while ((bytes = fileInputStream.read()) != -1) {
                responseOutputStream.write(bytes);
            }
            responseOutputStream.flush();
        }
    }
    
    public String formatShapeFile(InputStream shp, InputStream shx, InputStream dbf, InputStream prj){
        ShapefileReader reader = new ShapefileReader(); 
        String allShapes = "";
        try {
            if(prj == null) {
                reader.open(shp, shx, dbf);
            } else {
                reader.open(shp, shx, dbf, prj);             
            }
            FeatureSet fs = reader.getFeatures();
            for(Feature f : fs.getFeatures()) {                 
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create(); 
                for(Part part : f.getShape().getParts()){                                                                
                    String test = gson.toJson(part.getCoordinates()); 
                    test = test.replace(",\"Z\":0.0,\"M\":0.0", "").replace("[{","").replace("\"X\":","").replace("\"Y\":","").replace("}]","");
                    test = test.replace("},{","],[");
                    allShapes = allShapes + test + "--";                                
                }
            }            
        } catch(Exception ex){
            Exceptions.printStackTrace(ex);
        }
        return allShapes;
    }
        
    public String formatShapeFileReproject(InputStream shp, InputStream shx, InputStream dbf, InputStream prj){
        ShapefileReader reader = new ShapefileReader(); 
        String allShapes = "";
        try {
            reader.open(shp, shx, dbf, prj); 
            ProjectionInfo sourceInfo = ProjectionInfo.fromEsriString(reader.getProjection());
            FeatureSet fs = reader.getFeatures();
            for(Feature f : fs.getFeatures()) {                                 
                for(Part part : f.getShape().getParts()){
                    String test = "";
                    for(Coord coord : part.getCoordinates()){
                        Coord transform = Reproject.reprojectCoordinate(coord, sourceInfo, wgs84);
                        test = test  + transform.X + "," + transform.Y + "},{";
                    }
                    test = test.substring(0, test.length()-3);
//                    String test = gson.toJson(part.getCoordinates()); 
                    test = test.replace(",\"Z\":0.0,\"M\":0.0", "").replace("[{","").replace("\"X\":","").replace("\"Y\":","").replace("}]","");
                    test = test.replace("},{","],[");
                    allShapes = allShapes + test + "--";                                
                }
            }            
//        response.getWriter().print(allShapes);
        } catch(IOException | ProjectionException ex){
            Exceptions.printStackTrace(ex);
        }
        return allShapes;
    }
    
    public int intval(double doub){
        Double d = (Double)doub;
        return d.intValue();
    }
}
