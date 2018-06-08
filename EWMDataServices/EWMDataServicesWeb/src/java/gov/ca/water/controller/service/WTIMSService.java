/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.controller.service;

import gov.ca.water.context.singleton.MultipartResolverContext;
import gov.ca.water.entity.ewm.collection.StationCollection;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author aleshchuk
 */
@Controller
@RequestMapping("/service/wtims")
public class WTIMSService {
  
  // standard response set up for CORS and JSON response type
  private void setUpResponse(HttpServletResponse response) {
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
  }
  
  @RequestMapping("/searchbyid")
  public void getById(@RequestParam("id") String id, HttpServletResponse response) throws IOException {
    setUpResponse(response);
    
    JSONArray result = new JSONArray();
    try {
      StationCollection stations = new StationCollection();
      result = stations.wtimsSearchById(id);
//      result.put("data", data)
//              .put("success", true);
    } catch (Exception e) {
      e.printStackTrace(System.out);
//      result.put("success", false)
//              .put("error", e.getMessage());
    } finally {
      response.getWriter().write(result.toString());
    }
  }
  
  @RequestMapping("/buffer")
  public void getByBuffer(
          @RequestParam("lat") Double lat, 
          @RequestParam("lng") Double lng, 
          @RequestParam("distance") Double distanceFeet, 
          HttpServletResponse response) throws IOException {
    setUpResponse(response);
    
    JSONArray result = new JSONArray();
    try {
      StationCollection stations = new StationCollection();
      result = stations.wtimsSearchByBuffer(lat, lng, distanceFeet);
//      result.put("data", data)
//              .put("success", true);
    } catch (Exception e) {
      e.printStackTrace(System.out);
//      result.put("success", false)
//              .put("error", e.getMessage());
    } finally {
      response.getWriter().write(result.toString());
    }
  }
  
  @RequestMapping("/extent")
  public void getByExtent(
          @RequestParam("json") String jsonStr, HttpServletResponse response) throws IOException {
    setUpResponse(response);
    
    JSONArray result = new JSONArray();
    try {
      JSONObject extent = new JSONObject(jsonStr);
      StationCollection stations = new StationCollection();
      result = stations.wtimsSearchByExtent(extent.getDouble("xmax"),
              extent.getDouble("ymax"),
              extent.getDouble("xmin"),
              extent.getDouble("ymin")
              );
//      result.put("data", data)
//              .put("success", true);
    } catch (Exception e) {
      e.printStackTrace(System.out);
//      result.put("success", false)
//              .put("error", e.getMessage());
    } finally {
      response.getWriter().write(result.toString());
    }
  }
  
  @RequestMapping("/well")
  public void getWellById(
          @RequestParam("wellid") Integer id, 
          HttpServletResponse response) throws IOException {
    setUpResponse(response);
    
    JSONObject result = new JSONObject();
    try {
      StationCollection stations = new StationCollection();
      result = stations.wtimsGetStationById(id);
//      JSONObject data = stations.wtimsGetStationById(id);
//      result.put("data", data)
//              .put("success", true);
    } catch (Exception e) {
      e.printStackTrace(System.out);
      result.put("success", false)
              .put("error", e.getMessage());
    } finally {
      response.getWriter().write(result.toString());
    }
  }
  
  @RequestMapping("/nearestwells")
  public void getNearestWells(
          @RequestParam("wellNumber") Integer id, 
          @RequestParam("lat") Double lat, 
          @RequestParam("lng") Double lng, 
          @RequestParam("distance") Double distanceMile, 
          HttpServletResponse response) throws IOException {
    setUpResponse(response);
    
    JSONArray result = new JSONArray();
    try {
      StationCollection stations = new StationCollection();
      result = stations.wtimsSearchNearestWells(lat, lng, distanceMile*5280);
//      JSONObject data = stations.wtimsGetStationById(id);
//      result.put("data", data)
//              .put("success", true);
    } catch (Exception e) {
      e.printStackTrace(System.out);
//      result.put("success", false)
//              .put("error", e.getMessage());
    } finally {
      response.getWriter().write(result.toString());
    }
    
    // WATERDISTRICT.WATERDISTRICTNAMES ???
  }

  // Option 1: ID Search - Search Button
  @RequestMapping("/contour/getWellsByNames")
  public void getWellsByNames(@RequestParam("wellnumbers") String wells, HttpServletResponse response) throws IOException {
    setUpResponse(response);
    
    JSONArray result = new JSONArray();
    try {
      JSONArray params = new JSONArray(wells);
      List<String> names = new ArrayList<>(params.length());
      for (int i = 0; i < params.length(); i++)
        names.add(params.get(i).toString());
      StationCollection stations = new StationCollection();
      result = stations.wtimsSearchByNames(names);
    } catch (Exception e) {
      e.printStackTrace(System.out);
    } finally {
      response.getWriter().write(result.toString());
    }
  }
  
  // Option 1: ID Search - Text Field Autocomplete
  @RequestMapping("/contour/autoComplete")
  public void autoComplete(@RequestParam("param") String param, HttpServletResponse response) throws IOException {
    setUpResponse(response);
    
    JSONArray result = new JSONArray();
    try {
      StationCollection stations = new StationCollection();
      result = stations.wtimsAutoComplete(param);
    } catch (Exception e) {
      e.printStackTrace(System.out);
    } finally {
      response.getWriter().write(result.toString());
    }
  }
  
  // Option 2: Buffer Search - Search Button
  @RequestMapping("/contour/getBufferedPoints")
  public void getBufferedPoints(
          @RequestParam("lat") double lat,
          @RequestParam("lng") double lng,
          @RequestParam("distance") double distance, // feet
          HttpServletResponse response
  ) throws IOException
  {
    setUpResponse(response);
    
    JSONArray result = new JSONArray();
    try {
      StationCollection stations = new StationCollection();
      result = stations.wtimsBufferedPoints(lat, lng, distance);
    } catch (Exception e) {
      e.printStackTrace(System.out);
    } finally {
      response.getWriter().write(result.toString());
    }
  }

  // Option 3: Draw a Shape - Freehand and Extent Buttons
  @RequestMapping("/contour/wellsInExtentOfBoundary")
  public void wellsInExtentOfBoundary(@RequestParam("json") String jsonStr,
          HttpServletResponse response) throws IOException
  {
    setUpResponse(response);
    JSONObject json = new JSONObject(jsonStr);
    double xmax = json.getDouble("xmax");
    double xmin = json.getDouble("xmin");
    double ymax = json.getDouble("ymax");
    double ymin = json.getDouble("ymin");
    
    JSONArray result = new JSONArray();
    try {
      StationCollection stations = new StationCollection();
      result = stations.wtimsWellsInExtentOfBoundary(xmin, xmax, ymin, ymax);
    } catch (Exception e) {
      e.printStackTrace(System.out);
    } finally {
      response.getWriter().write(result.toString());
    }
  }
  
  // Option 4: Upload a Shape File
  @RequestMapping("/contour/uploadFile")
  public void parseShapefile(HttpServletRequest request, HttpServletResponse response) throws IOException {
    setUpResponse(response);
    
    try {
      String utmFoot = "PROJCS[\"NAD_1983_UTM_Zone_10N\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",1640416.666666667],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-123.0],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Foot_US\",0.3048006096012192]]";
      ProjectionInfo webmerc = ProjectionInfo.fromEpsgCode(102100).get();
      ProjectionInfo wgs84 = Projections.getWGS84();
      ProjectionInfo lidar = ProjectionInfo.fromEsriString(utmFoot);
      String projectionType = request.getParameter("projectionType");

//      EgisImportJsonFromShapefile reader = new EgisImportJsonFromShapefile();
      MultipartResolverContext mrc = MultipartResolverContext.getInstance();
      MultipartFile file = mrc.getMultipartFile("shp");
      
      ShapefileReader reader = new ShapefileReader();
      reader.open(file.getInputStream());
      FeatureSet featureSet = reader.getFeatures();
      ProjectionInfo sourceInfo = ProjectionInfo.fromEsriString(reader.getProjection());
      //write the shapefile's data to response string
      JSONArray json = new JSONArray(), jarray, jcoord;
      for (Feature f : featureSet.getFeatures()) {
        for (gov.ca.water.shapelite.Part part : f.getShape().getParts()) {
          List<Coord> listCoord = new ArrayList<>();
          if (sourceInfo == null) {
            switch (projectionType) {
              case "webmerc":
                for (Coord coord : part.getCoordinates()) {
                  listCoord = (part.getCoordinates());
                }
                break;
              case "UTMFoot":
                for (Coord coord : part.getCoordinates()) {
                  listCoord.add(Reproject.reprojectCoordinate(coord, lidar, webmerc));
                }
                break;
              case "WGS84":
                for (Coord coord : part.getCoordinates()) {
                  listCoord.add(Reproject.reprojectCoordinate(coord, wgs84, webmerc));
                }
                break;
            }
          } else {
            for (Coord coord : part.getCoordinates()) {
              listCoord.add(Reproject.reprojectCoordinate(coord, sourceInfo, webmerc));
            }
          }

          jarray = new JSONArray();
          for (Coord c : listCoord) {
            jcoord = new JSONArray();
            jcoord.put(c.getX());
            jcoord.put(c.getY());
            jarray.put(jcoord);
          }
          json.put(jarray);
        }
      }
      try (PrintWriter pw = response.getWriter()) {
        pw.write(json.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Well Search result point clicked - first call before popup
  @RequestMapping("/contour/nearestWells")
  public void findNearestWells(
          @RequestParam("lat") double lat,
          @RequestParam("lng") double lng,
          @RequestParam("distance") double distance,
          HttpServletResponse response
  ) throws IOException, ProjectionException {
    setUpResponse(response);
    
    double distance_feet = distance * 5280.0;
    
    JSONArray result = new JSONArray();
    try {
      StationCollection stations = new StationCollection();
      result = stations.wtimsBufferedPoints(lat, lng, distance_feet);
    } catch (Exception e) {
      e.printStackTrace(System.out);
    } finally {
      response.getWriter().write(result.toString());
    }
  }
  
  // Well Search result point clicked - first call after popup created
  @RequestMapping("/contour/getWellById")
  public void getWellsById(@RequestParam("wellid") int wellid, HttpServletResponse response) throws IOException {
    setUpResponse(response);
    
    JSONArray result = new JSONArray();
    try {
      StationCollection stations = new StationCollection();
      result.put(stations.wtimsGetStationById(wellid));
    } catch (Exception e) {
      e.printStackTrace(System.out);
    } finally {
      response.getWriter().write(result.toString());
    }
  }
  
  // Well Search result point clicked - second call after popup created
  @RequestMapping("/contour/getTimeSeries")
  public void getTimeSeries(
          @RequestParam("wellNumbers") String wellNumbers,
          @RequestParam("wellNames") String wellNames,
          HttpServletResponse response
  ) throws IOException {
    setUpResponse(response);
    
    JSONArray names = new JSONArray(wellNames);
    JSONArray wells = new JSONArray(wellNumbers);
    
    JSONObject result = new JSONObject();
    try {
      StationCollection stations = new StationCollection();
      result = stations.wtimsGetTimeSeries(wells, names);
    } catch (Exception e) {
      e.printStackTrace(System.out);
    } finally {
      response.getWriter().write(result.toString());
    }
  }

  // Well Search - called during page load
  @RequestMapping("/contour/param")
  public void getParams(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setHeader("Access-Control-Allow-Origin", "*");
    
    List<Integer> result = new ArrayList<>();
    try {
      StationCollection stations = new StationCollection();
      result = stations.wtimsGetParams();
    } catch (Exception e) {
      e.printStackTrace(System.out);
    } finally {
      response.getWriter().write(result.toString());
    }
  }

  // Well Search - Download Station Info
  @RequestMapping("/contour/downloadWellCSV")
  public void downloadWellCSV(@RequestParam("wellids") String wellids, HttpServletResponse response) throws IOException {
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "filename=StationInfo.csv");
    
    JSONArray ids = new JSONArray(wellids);
    try (PrintWriter writer = response.getWriter()) {
      StationCollection stations = new StationCollection();
      stations.wtimsDownloadWellCSV(ids, writer);
    } catch (Exception e) {
      e.printStackTrace(System.out);
    }
  }

  // Well Search - Download Time Series
  @RequestMapping("/contour/downloadTimeSeriesCSV")
  public void downloadTimeSeriesCSV(@RequestParam("wellids") String wellids, HttpServletResponse response) throws IOException {
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "filename=WaterLevels.csv");
    
    JSONArray ids = new JSONArray(wellids);
    try (PrintWriter writer = response.getWriter()) {
      StationCollection stations = new StationCollection();
      stations.wtimsDownloadTimeSeriesCSV(ids, writer);
    } catch (Exception e) {
      e.printStackTrace(System.out);
    }
  }
}
