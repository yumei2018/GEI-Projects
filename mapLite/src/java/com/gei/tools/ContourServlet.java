/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.tools;

import com.gei.contour.ContourManager;
import com.gei.contour.GWLTest;
import com.gei.entities.ElevationDataReading;
import com.gei.entities.Gwldata;
import com.gei.entities.WellKings;
import com.gei.facades.ElevationDataReadingFacade;
import com.gei.facades.GwlDataFacade;
import com.gei.facades.NcalWellsFacade;
import com.gei.facades.WellKingsFacade;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.FieldType;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.io.ShapefileWriter;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Polygon;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.nocrala.tools.gis.data.esri.shapefile.exception.InvalidShapeFileException;
import org.openide.util.Exceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ileung
 */
@Controller
//@RequestMapping("/contour")
public class ContourServlet extends MultiActionController {

  @Autowired
  ApplicationContext appContext;
  private final static ProjectionInfo webmerc = Projections.Projected.getWorld().WebMercator;
  private final static ProjectionInfo wgs84 = Projections.Geographic.getWorld().WGS1984;
  private final static String utmFoot = "PROJCS[\"NAD_1983_UTM_Zone_10N\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",1640416.666666667],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-123.0],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Foot_US\",0.3048006096012192]]";

//  @RequestMapping("/getPoints")
  public void getPoints(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
    String geometry = request.getParameter("geometry");
    String year = request.getParameter("year");
    String season = request.getParameter("season");
    JSONArray json = new JSONArray(geometry);

    //Construct the polygon for bounds testing while finding min/max X and Y
    Double minY = Double.POSITIVE_INFINITY, maxY = Double.NEGATIVE_INFINITY, minX = Double.POSITIVE_INFINITY, maxX = Double.NEGATIVE_INFINITY;
    Polygon poly = new Polygon();
    for (int i = 0; i < json.length(); i++) {
      JSONArray coord = json.getJSONArray(i);
      if (minY > coord.getDouble(1)) {
        minY = coord.getDouble(1);
      }
      if (maxY < coord.getDouble(1)) {
        maxY = coord.getDouble(1);
      }
      if (minX > coord.getDouble(0)) {
        minX = coord.getDouble(0);
      }
      if (maxX < coord.getDouble(0)) {
        maxX = coord.getDouble(0);
      }

      try {
        Coord coordtemp = new Coord(coord.getDouble(0), coord.getDouble(1));
        Coord newcoord = Reproject.reprojectCoordinate(coordtemp, wgs84, webmerc);
        int x = (int) newcoord.X;
        int y = (int) newcoord.Y;
        poly.addPoint(x, y);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    String monthQuery;
    if (season.equals("Spring")) {
      monthQuery = "AND EXTRACT(month FROM MEASUREMENT_DATE) <= 6";
    } else {
      monthQuery = "AND EXTRACT(month FROM MEASUREMENT_DATE) >= 7";
    }

    String query = "SELECT DISTINCT WELL_NUMBER, LONGITUDE, LATITUDE, WEBMERC_X, WEBMERC_Y FROM "
            + "(SELECT UNIQUE(y.WELL_NUMBER), y.LONGITUDE, y.LATITUDE, y.WEBMERC_X, y.WEBMERC_Y, x.WSE FROM GWLDATA x INNER JOIN WELL_KINGS y ON x.STATE_WELL_NUMBER = y.WELL_NUMBER WHERE EXTRACT(year FROM MEASUREMENT_DATE) = "
            + year + " " + monthQuery + " AND y.LONGITUDE > " + minX
            + " AND y.LONGITUDE < " + maxX + " AND y.LATITUDE > " + minY + " AND y.LATITUDE < " + maxY + ")";
//        String query =  "SELECT y.LONGITUDE, y.LATITUDE, y.WELL_NUMBER, y.WEBMERC_X, y.WEBMERC_Y FROM WELL_KINGS y WHERE y.WEBMERC_X > " + minX.intValue() +
//                        " AND y.WEBMERC_X < " + maxX.intValue() + " AND y.WEBMERC_Y > " + minY.intValue() + " y.AND WEBMERC_Y < " + maxY.intValue();
    WellKingsFacade wkFacade = (WellKingsFacade) appContext.getBean(WellKingsFacade.class.getSimpleName());
    List<Map> maps = wkFacade.select(query);
    JSONArray res = new JSONArray();
    for (Map m : maps) {
      int x;
      int y;
      try {
        Coord coordtemp = new Coord(((BigDecimal) m.get("longitude")).doubleValue(), ((BigDecimal) m.get("latitude")).doubleValue());
        Coord newcoord = Reproject.reprojectCoordinate(coordtemp, wgs84, webmerc);
        x = (int) newcoord.X;
        y = (int) newcoord.Y;
        Point p = new Point(x, y);
        if (poly.contains(p)) {
          JSONObject obj = new JSONObject();
          obj.put("well", m.get("wellNumber"));
          Coord coord = new Coord(((BigDecimal) m.get("longitude")).doubleValue(), ((BigDecimal) m.get("latitude")).doubleValue());
          obj.put("coord", coord);
          res.put(obj);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
    response.getWriter().print(res);
  }

//  @RequestMapping("/getAllPoints")
  public void getAllPoints(HttpServletRequest request, HttpServletResponse response) throws InvalidShapeFileException, FileNotFoundException, IOException {
    String query = "SELECT (abs(CASGEM_STATION.LONGITUDE)*-1) AS longitude, LATITUDE, CASGEM_STATE_WELL_NBR AS WELLNUMBER, CASGEM_STATION_ID AS WELLID FROM CASGEM_STATION";// WHERE WELL_NUMBER = '" + station + "'";
    GwlDataFacade gwlFacade = (GwlDataFacade) appContext.getBean(GwlDataFacade.class.getSimpleName());
    List<Map> maps = gwlFacade.select(query);
    JSONArray stations = new JSONArray();
    for (Map m : maps) {
      try {
        Coord coord = new Coord(((BigDecimal) m.get("longitude")).doubleValue(), ((BigDecimal) m.get("latitude")).doubleValue());
        JSONObject station = new JSONObject();
        station.put("well", m.get("wellnumber"));
        station.put("id", m.get("wellid"));
        station.put("coord", coord);
        stations.put(station);
      } catch (Exception ex) {
        Exceptions.printStackTrace(ex);
      }
    }
    response.getWriter().print(stations);
  }

//  @RequestMapping("/getWellById")
  public void getWellsByid(HttpServletRequest request, HttpServletResponse response) throws InvalidShapeFileException, FileNotFoundException, IOException {
    String wellid = request.getParameter("wellid");
    try {
      String query = "SELECT DISTINCT A.CASGEM_STATION_ID AS ID, "
              + "A.CASGEM_STATE_WELL_NBR AS STATEWELLNUM, "
              + "B.SITE_CODE AS SITECODE, "
              + "A.LOCAL_WELL_DESIGNATION AS LOCALWELLID, "
              + "A.TOTAL_DEPTH_FT AS TOTALDEPTH, "
              + "X.LOW AS LOW, "
              + "Y.HIGH AS HIGH, "
              + "Z.LASTMEASUREDATE AS LASTMEASUREDATE, "
              + "W.FIRSTMEASUREDATE AS FIRSTMEASUREDATE, "
              + "A.WELL_LOCATION_DESC AS WELLLOCATION "
              + "FROM CASGEM_STATION A "
              + " LEFT JOIN MASTER_SITE B ON A.MASTER_SITE_ID = B.MASTER_SITE_ID "
              + " LEFT JOIN (SELECT MAX(PERFORATION_BOTTOM_MSRMNT) AS LOW,CASGEM_STATION_ID FROM CASGEM_STATION_PERFORATION GROUP BY CASGEM_STATION_ID  ) X ON X.CASGEM_STATION_ID = A.CASGEM_STATION_ID "
              + " LEFT JOIN (SELECT MAX(PERFORATION_TOP_MSRMNT) AS HIGH,CASGEM_STATION_ID FROM CASGEM_STATION_PERFORATION GROUP BY CASGEM_STATION_ID  ) Y ON Y.CASGEM_STATION_ID = A.CASGEM_STATION_ID "
              + " LEFT JOIN (SELECT MAX(MEASUREMENT_DT) AS LASTMEASUREDATE,CASGEM_STATION_ID FROM ELEVATION_DATA_READING GROUP BY CASGEM_STATION_ID  ) Z ON Z.CASGEM_STATION_ID = A.CASGEM_STATION_ID"
              + " LEFT JOIN (SELECT MIN(MEASUREMENT_DT) AS FIRSTMEASUREDATE,CASGEM_STATION_ID FROM ELEVATION_DATA_READING GROUP BY CASGEM_STATION_ID  ) W ON W.CASGEM_STATION_ID = A.CASGEM_STATION_ID"
              //                +"LEFT JOIN BASIN D ON A.BASIN_ID = D.BASIN_ID "
              //                +"LEFT JOIN CASGEM_STATION_BAK E ON A.CASGEM_STATION_ID = E.CASGEM_STATION_ID   "
              //                +"LEFT JOIN WELL_COMPLETION_TYPE F ON A.WELL_COMPLETION_ID = F.WELL_COMPLETION_ID "
              //                +"LEFT JOIN CASGEM_STATION_USE_TYPE G ON A.CASGEM_STATION_USE_ID = G.CASGEM_STATION_USE_ID "
              //                +"LEFT JOIN CONFIDENCE_TYPE I ON A.CONFIDENCE_TYPE_ID = I.CONFIDENCE_TYPE_ID "
              //                +"LEFT JOIN COORDINATE_TYPE J ON A.COORDINATE_TYPE_ID = J.COORDINATE_TYPE_ID "
              + " WHERE A.CASGEM_STATION_ID = " + wellid;
      JSONArray results = new JSONArray();
      GwlDataFacade gwlFacade = (GwlDataFacade) appContext.getBean(GwlDataFacade.class.getSimpleName());

      List<Map> maps = gwlFacade.select(query);
      for (Map m : maps) {
        try {
          JSONObject well = new JSONObject();
          Iterator entries = m.entrySet().iterator();
          while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            if (entry.getValue() == null) {
              well.put((String) entry.getKey(), "-");
            } else {
              if ("lastmeasuredate".equals((String) entry.getKey()) || "firstmeasuredate".equals((String) entry.getKey())) {
                well.put((String) entry.getKey(), convertDate(entry.getValue().toString()));
              } else {
                well.put((String) entry.getKey(), entry.getValue());
              }

            }

          }
          results.put(well);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }

      response.getWriter().print(results);

    } catch (Exception ex) {
      Exceptions.printStackTrace(ex);
    }
  }

//  @RequestMapping("/getWellsByNames")
  public void getWellsByNames(HttpServletRequest request, HttpServletResponse response) throws InvalidShapeFileException, FileNotFoundException, IOException {
    String wells = request.getParameter("wellnumbers");
    try {
      JSONArray params = new JSONArray(wells);
      JSONArray stations = new JSONArray();
      GwlDataFacade gwlFacade = (GwlDataFacade) appContext.getBean(GwlDataFacade.class.getSimpleName());
      if (params.length() > 0) {
        String query = "SELECT DISTINCT "
                + " A.CASGEM_STATION_ID AS ID,"
                + " A.CASGEM_STATE_WELL_NBR, "
                + " (abs(A.LONGITUDE)*-1) AS longitude, "
                + " A.LATITUDE, "
                + " B.SITE_CODE, "
                + " A.TOTAL_DEPTH_FT as totaldepth, "
                + " A.LOCAL_WELL_DESIGNATION as localwellid, "
                + "A.TOTAL_DEPTH_FT AS TOTALDEPTH, "
                //                        + "X.LOW AS LOW, "
                //                        + "Y.HIGH AS HIGH, "
                + "Z.LASTMEASUREDATE AS LASTMEASUREDATE "
                //                        + "W.FIRSTMEASUREDATE AS FIRSTMEASUREDATE "

                + " FROM CASGEM_STATION A"
                + " LEFT JOIN MASTER_SITE B ON A.MASTER_SITE_ID = B.MASTER_SITE_ID "
                //                        +" LEFT JOIN (SELECT MAX(PERFORATION_BOTTOM_MSRMNT) AS LOW,CASGEM_STATION_ID FROM CASGEM_STATION_PERFORATION GROUP BY CASGEM_STATION_ID  ) X ON X.CASGEM_STATION_ID = A.CASGEM_STATION_ID "
                //                        +" LEFT JOIN (SELECT MAX(PERFORATION_TOP_MSRMNT) AS HIGH,CASGEM_STATION_ID FROM CASGEM_STATION_PERFORATION GROUP BY CASGEM_STATION_ID  ) Y ON Y.CASGEM_STATION_ID = A.CASGEM_STATION_ID "
                //
                + " LEFT JOIN (SELECT MAX(MEASUREMENT_DT) AS LASTMEASUREDATE,CASGEM_STATION_ID FROM ELEVATION_DATA_READING GROUP BY CASGEM_STATION_ID  ) Z ON Z.CASGEM_STATION_ID = A.CASGEM_STATION_ID"
                //                        +" LEFT JOIN (SELECT MIN(MEASUREMENT_DT) AS FIRSTMEASUREDATE,CASGEM_STATION_ID FROM ELEVATION_DATA_READING GROUP BY CASGEM_STATION_ID  ) W ON W.CASGEM_STATION_ID = A.CASGEM_STATION_ID"

                + " WHERE ";
        for (int i = 0; i < params.length(); i++) {
          query += " (UPPER(LTRIM( A.CASGEM_STATE_WELL_NBR)) = UPPER('" + params.getString(i) + "') "
                  + "OR UPPER(LTRIM( B.SITE_CODE)) LIKE UPPER('" + params.getString(i) + "') "
                  + "OR UPPER(LTRIM( A.CASGEM_STATION_ID)) LIKE UPPER('" + params.getString(i) + "') "
                  + "OR UPPER(LTRIM( A.LOCAL_WELL_DESIGNATION)) LIKE UPPER('" + params.getString(i) + "')) "; //
          if (i != params.length() - 1) {
            query += " OR ";
          }
        }

        query += " ORDER BY Z.LASTMEASUREDATE DESC NULLS LAST";
        List<Map> maps = gwlFacade.select(query);

        for (Map m : maps) {
          try {
            JSONObject station = new JSONObject();

            String lastmeasuredate = "-";
            if (m.get("lastmeasuredate") != null) {
              DateFormat formatter = null;
              Date convertedDate = null;
              String date = m.get("lastmeasuredate").toString();
              formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
              convertedDate = (Date) formatter.parse(date);
              String newdate = new SimpleDateFormat("MM/dd/yyyy").format(convertedDate);
              lastmeasuredate = newdate;
            }

//                        String firstmeasuredate =  "-";
//                        if (m.get("firstmeasuredate")!= null)
//                        {
//                            DateFormat formatter = null;
//                            Date convertedDate = null;
//                            String date = m.get("firstmeasuredate").toString();
//                            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
//                            convertedDate = (Date) formatter.parse(date);
//                            String newdate = new SimpleDateFormat("MM/dd/yyyy").format(convertedDate);
//                            firstmeasuredate = newdate;
//                        }
//                        String high = "-";
//                        if(m.get("high") != null)
//                            high = m.get("high").toString();
//
//                        String low = "-";
//                        if(m.get("low") != null)
//                            low = m.get("low").toString();
            String totaldepth = "-";
            if (m.get("totaldepth") != null) {
              totaldepth = m.get("totaldepth").toString();
            }

            String localwellid = "-";
            if (m.get("localwellid") != null) {
              localwellid = m.get("localwellid").toString();
            }

            station.put("sitecode", m.get("siteCode"));
            station.put("wellnumber", m.get("casgemStateWellNbr"));
            station.put("id", m.get("id"));
            station.put("latitude", ((BigDecimal) m.get("latitude")).doubleValue());
            station.put("longitude", ((BigDecimal) m.get("longitude")).doubleValue());
            station.put("totaldepth", totaldepth);
            station.put("localwellid", localwellid);
//                        station.put("high",high);
//                        station.put("low",low);
//                        station.put("firstmeasuredate",firstmeasuredate);
            station.put("lastmeasuredate", lastmeasuredate);
            station.put("type", "well");

            stations.put(station);
          } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
          }
        }
      }

      if (params.length() > 0) {
        String singleString = "";
        for (int i = 0; i < params.length(); i++) //make single string to test for comma
        {
          singleString += params.getString(i);
          if (i != params.length() - 1) {
            singleString += ", ";
          }
        }

        String query = "SELECT DISTINCT * FROM WATERDISTRICT WHERE ";
        for (int i = 0; i < params.length(); i++) {
          query += " UPPER(WATERDISTRICTNAME) LIKE UPPER('" + params.getString(i) + "') OR ";
          if (i == params.length() - 1) {
            query += " UPPER(WATERDISTRICTNAME) LIKE UPPER('" + singleString + "') ";
          }

        }
        List<Map> maps = gwlFacade.select(query);

        for (Map m : maps) {
          try {
            JSONObject station = new JSONObject();
            oracle.sql.CLOB jsonClob = (oracle.sql.CLOB) m.get("esriJson");
            String esrijson = jsonClob.stringValue();
            station.put("district", m.get("waterdistrictname"));
            station.put("esri_json", esrijson);
            station.put("type", "district");

            stations.put(station);
          } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
          }
        }
      }

      response.getWriter().print(stations);

    } catch (Exception ex) {
      Exceptions.printStackTrace(ex);
      JSONArray stations = new JSONArray();
      response.getWriter().print(stations);
    }
  }

//  @RequestMapping("/getWellsByNames2")
  public void getWellsByNames2(HttpServletRequest request, HttpServletResponse response) throws InvalidShapeFileException, FileNotFoundException, IOException {
    String wells = request.getParameter("q");
    String type = request.getParameter("type");
    try {
      JSONArray params = new JSONArray(wells);
      JSONArray stations = new JSONArray();
      String query = "";
//      switch(type)
//      {
//        case "county":
//          query = "SELECT * FROM NCAL_WELLS WHERE";
//          break;
//        case "basin":
//          query = "SELECT * FROM NCAL_WELLS WHERE";
//          break;
//        case "basinnumber":
//          query = "SELECT * FROM NCAL_WELLS WHERE";
//          break;          
//        case "owner":
//          query = "SELECT * FROM NCAL_WELLS WHERE";
//          break;
//        case "localwell":
//          query = "SELECT * FROM NCAL_WELLS WHERE";
//          break;
//        case "statewell":          
//        default:
      query = "SELECT * FROM NCAL_WELLS WHERE";
//      }
      if (params.length() > 0) {
        for (int i = 0; i < params.length(); i++) {
          if (i != 0) {
            query += " OR";
          }
          switch (type) {
            case "county":
              query += " LTRIM(UPPER(COUNTY)) = UPPER('" + params.getString(i) + "')";
              break;
            case "basin":
              query += " LTRIM(UPPER(BASIN_NAME)) = UPPER('" + params.getString(i) + "')";
              break;
            case "basinnumber":
              query += " LTRIM(UPPER(BASIN_NUMBER)) = UPPER('" + params.getString(i) + "')";
              break;
            case "owner":
              query += " LTRIM(UPPER(MONITORING_ENTITY)) = UPPER('" + params.getString(i) + "')";
              break;
            case "localwell":
              query += " LTRIM(UPPER(LOCAL_WELL_NUMBER)) LIKE UPPER('" + params.getString(i) + "%')";
              break;
            case "statewell":
            default:
              query += " LTRIM(UPPER(STATE_WELL_NUMBER)) LIKE UPPER('" + params.getString(i) + "%')";
          }
        }
//          query += " (UPPER(LTRIM( LOCAL_WELL_NUMBER)) LIKE UPPER('" + params.getString(i) + "%') "
//            + "OR UPPER(LTRIM( STATE_WELL_NUMBER)) LIKE UPPER('" + params.getString(i) + "%') "
//            + "OR UPPER(LTRIM( BASIN_NAME)) LIKE UPPER('" + params.getString(i) + "%') "
//            + "OR UPPER(LTRIM( MONITORING_ENTITY)) LIKE UPPER('" + params.getString(i) + "%')) "; //
//          if (i != params.length() - 1) {
//            query += " OR ";
//          }
      }
      this.selectNcalWellsAndWriteToResponse(query, response);

    } catch (Exception ex) {
      Exceptions.printStackTrace(ex);
      JSONArray stations = new JSONArray();
      response.getWriter().print(stations);
    }
  }

//  private int selectCountNcalWells(String query) {
//    NcalWellsFacade ncalFacade = (NcalWellsFacade) appContext.getBean(NcalWellsFacade.class.getSimpleName());
//    List<Map> wells = ncalFacade.executeQuery(query);
//    return (Integer) wells.get(0).get("count");
//  }
//  @RequestMapping("/getMinExtentWithPoints")
  public void getMinExtentWithPoints(HttpServletRequest request, HttpServletResponse response) throws InvalidShapeFileException, FileNotFoundException, IOException {
    try {
      String data = request.getParameter("json");
      JSONObject json = new JSONObject(data);
      Double xmax = Double.parseDouble(json.getString("xmax"));
      Double xmin = Double.parseDouble(json.getString("xmin"));
      Double ymax = Double.parseDouble(json.getString("ymax"));
      Double ymin = Double.parseDouble(json.getString("ymin"));

      String query = "SELECT count(*) as count FROM NCAL_WELLS"
              + " WHERE ((abs(LONGITUDE)*-1) < " + Double.toString(xmax) + ")"
              + " AND ((abs(LONGITUDE)*-1) > " + Double.toString(xmin) + ")"
              + " AND (LATITUDE < " + Double.toString(ymax) + ")"
              + " AND (LATITUDE > " + Double.toString(ymin) + ")";

      JSONArray wells = new JSONArray();
      wells = mapListToNcalWellJsonArray(selectNcalWells(query), null);

      double miles = 0.5; /* increment extend */

      for (int i = 0; i < 200; i++) {
        if (wells.length() > 1) {
          break;
        }

        xmin -= (miles / 54.6); /* 1 deg long ~ 54.6mi at 38N lat - credit @siying */

        xmax += (miles / 54.6);
        ymin -= (miles / 69.172); /* 1 deg lat ~ 69.172mi at equator */

        ymax += (miles / 69.172);

        query = "SELECT * FROM NCAL_WELLS"
                + " WHERE ((abs(LONGITUDE)*-1) < " + Double.toString(xmax) + ")"
                + " AND ((abs(LONGITUDE)*-1) > " + Double.toString(xmin) + ")"
                + " AND (LATITUDE < " + Double.toString(ymax) + ")"
                + " AND (LATITUDE > " + Double.toString(ymin) + ")";

        wells = mapListToNcalWellJsonArray(selectNcalWells(query), null);
      }

      response.setContentType("application/json");

      JSONObject res = new JSONObject();
      res.put("xmin", xmin - (miles / 54.6));
      res.put("xmax", xmax + (miles / 54.6));
      res.put("ymin", ymin - (miles / 69.172));
      res.put("ymax", ymax + (miles / 69.172));

      try (PrintWriter pw = response.getWriter()) {
        pw.print(res.toString());
      }
    } catch (Exception e) {
      Exceptions.printStackTrace(e);
    }
  }

    //WELL_NUMBER   ->   CASGEM_STATE_WELL_NBR
  //SITECODE      ->   SITE_CODE
//  @RequestMapping("/autoComplete")
  public void autoComplete(HttpServletRequest request, HttpServletResponse response) throws InvalidShapeFileException, FileNotFoundException, IOException {
    try {
      String param = request.getParameter("param");
      JSONArray local = new JSONArray();
      JSONArray wellnums = new JSONArray();
      JSONArray wellids = new JSONArray();
      JSONArray sitecodes = new JSONArray();
      JSONArray stations = new JSONArray();
//            String query = "SELECT DISTINCT CASGEM_STATE_WELL_NBR, SITECODE FROM WELL_KINGS WHERE ";
//            query += " CASGEM_STATE_WELL_NBR LIKE UPPER('"+param +"%') OR LTRIM(SITECODE) LIKE UPPER('"+param +"%') ";

      String query = "SELECT DISTINCT CASGEM_STATION.CASGEM_STATE_WELL_NBR, MASTER_SITE.SITE_CODE, CASGEM_STATION.LOCAL_WELL_DESIGNATION as localwellid, CASGEM_STATION.CASGEM_STATION_ID as wellid FROM CASGEM_STATION "
              + "JOIN MASTER_SITE ON CASGEM_STATION.MASTER_SITE_ID = MASTER_SITE.MASTER_SITE_ID "
              + "WHERE "
              + " (UPPER(CASGEM_STATION.CASGEM_STATE_WELL_NBR) LIKE UPPER('" + param + "%') "
              + "OR UPPER(LTRIM( MASTER_SITE.SITE_CODE)) LIKE UPPER('" + param + "%') "
              + "OR UPPER(LTRIM( CASGEM_STATION.CASGEM_STATION_ID)) LIKE UPPER('" + param + "%') "
              + "OR UPPER(LTRIM( CASGEM_STATION.LOCAL_WELL_DESIGNATION)) LIKE UPPER('" + param + "%')) "; //

      GwlDataFacade gwlFacade = (GwlDataFacade) appContext.getBean(GwlDataFacade.class.getSimpleName());
      List<Map> maps = gwlFacade.select(query);

      for (Map m : maps) {
        try {
          JSONObject station = new JSONObject();
          String wellnum = "";
          if (m.get("casgemStateWellNbr") != null) {
            wellnum = m.get("casgemStateWellNbr").toString();
          }

          String sitecode = "";
          if (m.get("siteCode") != null) {
            sitecode = m.get("siteCode").toString();
          }

          String wellid = "";
          if (m.get("wellid") != null) {
            wellid = m.get("wellid").toString();
          }

          String localwellid = "";
          if (m.get("localwellid") != null) {
            localwellid = m.get("localwellid").toString();
          }

          if (localwellid.toUpperCase().indexOf(param.toUpperCase()) != -1) {
            station.put("label", localwellid);
            station.put("value", localwellid);
            station.put("id", localwellid);
            local.put(station);
          } else if (wellnum.toUpperCase().indexOf(param.toUpperCase()) != -1) {
            station.put("label", wellnum);
            station.put("value", wellnum);
            station.put("id", wellnum);
            wellnums.put(station);
          } else if (wellid.toUpperCase().indexOf(param.toUpperCase()) != -1) {
            station.put("label", wellid);
            station.put("value", wellid);
            station.put("id", wellid);
            wellids.put(station);
          } else if (sitecode.toUpperCase().indexOf(param.toUpperCase()) != -1) {
            station.put("label", sitecode);
            station.put("value", sitecode);
            station.put("id", sitecode);
            sitecodes.put(station);
          } else {
            station.put("label", "");
            station.put("value", "");
            station.put("id", "");
          }
//                     stations.put(station);
        } catch (Exception ex) {
          Exceptions.printStackTrace(ex);
        }
      }

      for (int i = 0; i < local.length(); i++) {
        stations.put(local.getJSONObject(i));
      }
      for (int i = 0; i < wellnums.length(); i++) {
        stations.put(wellnums.getJSONObject(i));
      }
      for (int i = 0; i < wellids.length(); i++) {
        stations.put(wellids.getJSONObject(i));
      }
      for (int i = 0; i < sitecodes.length(); i++) {
        stations.put(sitecodes.getJSONObject(i));
      }

      query = " SELECT DISTINCT WATERDISTRICTNAME FROM WATERDISTRICT WHERE LTRIM(WATERDISTRICTNAME) LIKE UPPER('" + param + "%') ";
      maps = gwlFacade.select(query);
      for (Map m : maps) {
        try {
          JSONObject station = new JSONObject();
          String districtname = (String) m.get("waterdistrictname");

          station.put("label", districtname);
          station.put("value", districtname);
          station.put("id", districtname);

          stations.put(station);
        } catch (Exception ex) {
          Exceptions.printStackTrace(ex);
        }
      }
      response.getWriter().print(stations);

    } catch (Exception ex) {
      Exceptions.printStackTrace(ex);
    }
  }

//  @RequestMapping("/autoComplete2")
  public void autoComplete2(HttpServletRequest request, HttpServletResponse response) throws InvalidShapeFileException, FileNotFoundException, IOException {
    try {
      String param = request.getParameter("param");
      JSONObject station = new JSONObject();
      //Counties
      String query = "SELECT COUNTY AS NAME, COUNT(COUNTY) AS COUNT, 'county' as TYPE FROM NCAL_WELLS"
              + " WHERE UPPER(COUNTY) LIKE UPPER('" + param + "%')"
              + " GROUP BY COUNTY"
              + " UNION ALL"
              + " SELECT BASIN_NAME AS NAME, COUNT(BASIN_NAME) AS COUNT, 'basin' as TYPE FROM NCAL_WELLS"
              + " WHERE UPPER(BASIN_NAME) LIKE UPPER('" + param + "%')"
              + " GROUP BY BASIN_NAME"
              + " UNION ALL"
              + " SELECT BASIN_NUMBER AS NAME, COUNT(BASIN_NUMBER) AS COUNT, 'basinnumber' as TYPE FROM NCAL_WELLS"
              + " WHERE UPPER(BASIN_NUMBER) LIKE UPPER('" + param + "%')"
              + " GROUP BY BASIN_NUMBER"
              + " UNION ALL"
              + " SELECT MONITORING_ENTITY AS NAME, COUNT(MONITORING_ENTITY) AS COUNT, 'owner' as TYPE FROM NCAL_WELLS"
              + " WHERE UPPER(MONITORING_ENTITY) LIKE UPPER('" + param + "%')"
              + " GROUP BY MONITORING_ENTITY"
              + " UNION ALL"
              + " SELECT STATE_WELL_NUMBER AS NAME, 1 AS COUNT, 'statewell' as TYPE FROM NCAL_WELLS"
              + " WHERE UPPER(STATE_WELL_NUMBER) LIKE UPPER('" + param + "%')"
              + " UNION ALL"
              + " SELECT LOCAL_WELL_NUMBER AS NAME, 1 AS COUNT, 'localwell' as TYPE FROM NCAL_WELLS"
              + " WHERE UPPER(LOCAL_WELL_NUMBER) LIKE UPPER('" + param + "%')";
      NcalWellsFacade ncalWellsFacade = (NcalWellsFacade) appContext.getBean(NcalWellsFacade.class.getSimpleName());
      List<Map> maps = ncalWellsFacade.select(query);
      JSONArray counties = new JSONArray();
      for (Map m : maps) {
        try {
          JSONObject county = new JSONObject();
          if (m.get("name") != null) {
            county.put("name", m.get("name"));
            county.put("count", m.get("count"));
            county.put("type", m.get("type"));
            counties.put(county);
          }
        } catch (Exception ex) {
          Exceptions.printStackTrace(ex);
        }
      }
//      //basins
//      query = "SELECT BASIN_NAME, COUNT(BASIN_NAME) AS COUNT FROM NCAL_WELLS"
//        + " WHERE UPPER(BASIN_NAME) LIKE UPPER('"+param+"%')"
//        + " GROUP BY BASIN_NAME";  
////      ncalWellsFacade = (NcalWellsFacade) appContext.getBean(NcalWellsFacade.class.getSimpleName());
//      maps = ncalWellsFacade.select(query);     
//      JSONArray basins = new JSONArray();
//      for (Map m : maps) {
//        try {          
//          JSONObject basin = new JSONObject();
//          if(m.get("basinName") != null)
//          {
////            basin.put(m.get("basinName").toString(), m.get("count"));
//            basin.put("label", m.get("basinName") + " (" + m.get("count") + ")");
//            basin.put("value", m.get("basinName"));                   
//            basin.put("type", "basin");     
//            basins.put(basin);
//          }
//        } catch (Exception ex) {
//          Exceptions.printStackTrace(ex);
//        }
//      } 
//      //owner
//      query = "SELECT MONITORING_ENTITY, COUNT(MONITORING_ENTITY) AS COUNT FROM NCAL_WELLS"
//        + " WHERE UPPER(MONITORING_ENTITY) LIKE UPPER('"+param+"%')"
//        + " GROUP BY MONITORING_ENTITY";  
////      ncalWellsFacade = (NcalWellsFacade) appContext.getBean(NcalWellsFacade.class.getSimpleName());
//      maps = ncalWellsFacade.select(query);     
//      JSONArray owners = new JSONArray();
//      for (Map m : maps) {
//        try {          
//          JSONObject owner = new JSONObject();
//          if(m.get("monitoringEntity") != null)
//          {
////            owner.put(m.get("monitoringEntity").toString(), m.get("count"));
//            owner.put("label", m.get("monitoringEntity") + " (" + m.get("count") + ")");
//            owner.put("value", m.get("monitoringEntity"));       
//            owner.put("type", "owner");            
//            owners.put(owner);
//          }
//        } catch (Exception ex) {
//          Exceptions.printStackTrace(ex);
//        }
//      }      
//            
//      query = "SELECT DISTINCT NCAL_WELLS.STATE_WELL_NUMBER, NCAL_WELLS_ID"
//        + " FROM NCAL_WELLS"
//        + " WHERE "
//        + " UPPER(NCAL_WELLS.STATE_WELL_NUMBER) LIKE UPPER('" + param + "%') ";   
//      
////      NcalWellsFacade ncalWellsFacade = (NcalWellsFacade) appContext.getBean(NcalWellsFacade.class.getSimpleName());
//      maps = ncalWellsFacade.select(query);
//      //return the state well
//      JSONArray statewells = new JSONArray();
//      for (Map m : maps) {
//        try {          
//          JSONObject statewell = new JSONObject();
//          if(m.get("stateWellNumber") != null)
//          {
//            statewell.put("label", m.get("stateWellNumber"));
//            statewell.put("value", m.get("stateWellNumber"));
////            statewell.put("id", m.get("ncalWellsId"));  
//            statewell.put("type", "statewell");
//            statewells.put(statewell);
//          }
//        } catch (Exception ex) {
//          Exceptions.printStackTrace(ex);
//        }
//      }      
//      
//      query = "SELECT DISTINCT NCAL_WELLS.LOCAL_WELL_NUMBER, NCAL_WELLS_ID"
//        + " FROM NCAL_WELLS"
//        + " WHERE "
//        + " UPPER(NCAL_WELLS.LOCAL_WELL_NUMBER) LIKE UPPER('" + param + "%') ";   
//      
////      NcalWellsFacade ncalWellsFacade = (NcalWellsFacade) appContext.getBean(NcalWellsFacade.class.getSimpleName());
//      maps = ncalWellsFacade.select(query);
//      //return the state well
//      JSONArray localwells = new JSONArray();
//      for (Map m : maps) {
//        try {          
//          JSONObject localwell = new JSONObject();
//          if(m.get("localWellNumber") != null)
//          {
//            localwell.put("label", m.get("localWellNumber"));
//            localwell.put("value", m.get("localWellNumber"));
//            localwell.put("type", "localwell");
//            localwells.put(localwell);
//          }
//        } catch (Exception ex) {
//          Exceptions.printStackTrace(ex);
//        }
//      }        
//      station.put("counties", counties);

      response.setContentType("application/json");
      response.getWriter().print(counties);

    } catch (Exception ex) {
      Exceptions.printStackTrace(ex);
    }
  }

//  @RequestMapping("/wellsInExtentOfBoundary")
  public void wellsInExtentOfBoundary(HttpServletRequest request, HttpServletResponse response) throws InvalidShapeFileException, FileNotFoundException, IOException {
    try {
      String data = request.getParameter("json");
      JSONObject json = new JSONObject(data);
      String xmax = json.getString("xmax");
      String xmin = json.getString("xmin");
      String ymax = json.getString("ymax");
      String ymin = json.getString("ymin");

      JSONArray wells = new JSONArray();
      String query = "SELECT DISTINCT "
              + " A.CASGEM_STATION_ID AS ID,"
              + " A.CASGEM_STATE_WELL_NBR, "
              + " (abs(A.LONGITUDE)*-1) AS longitude, "
              + " A.LATITUDE, "
              + " B.SITE_CODE, "
              + " A.TOTAL_DEPTH_FT as totaldepth, "
              + " A.LOCAL_WELL_DESIGNATION as localwellid, "
              //
              + "A.TOTAL_DEPTH_FT AS TOTALDEPTH, "
              //                        + "X.LOW AS LOW, "
              //                        + "Y.HIGH AS HIGH, "
              + "Z.LASTMEASUREDATE AS LASTMEASUREDATE "
              //                        + "W.FIRSTMEASUREDATE AS FIRSTMEASUREDATE "

              + " FROM CASGEM_STATION A"
              + " LEFT JOIN MASTER_SITE B ON A.MASTER_SITE_ID = B.MASTER_SITE_ID "
              //                        +" LEFT JOIN (SELECT MAX(PERFORATION_BOTTOM_MSRMNT) AS LOW,CASGEM_STATION_ID FROM CASGEM_STATION_PERFORATION GROUP BY CASGEM_STATION_ID  ) X ON X.CASGEM_STATION_ID = A.CASGEM_STATION_ID "
              //                        +" LEFT JOIN (SELECT MAX(PERFORATION_TOP_MSRMNT) AS HIGH,CASGEM_STATION_ID FROM CASGEM_STATION_PERFORATION GROUP BY CASGEM_STATION_ID  ) Y ON Y.CASGEM_STATION_ID = A.CASGEM_STATION_ID "

              + " LEFT JOIN (SELECT MAX(MEASUREMENT_DT) AS LASTMEASUREDATE,CASGEM_STATION_ID FROM ELEVATION_DATA_READING GROUP BY CASGEM_STATION_ID  ) Z ON Z.CASGEM_STATION_ID = A.CASGEM_STATION_ID"
              //                        +" LEFT JOIN (SELECT MIN(MEASUREMENT_DT) AS FIRSTMEASUREDATE,CASGEM_STATION_ID FROM ELEVATION_DATA_READING GROUP BY CASGEM_STATION_ID  ) W ON W.CASGEM_STATION_ID = A.CASGEM_STATION_ID"

              + " WHERE ((abs(A.LONGITUDE)*-1) < " + xmax + ")"
              + " AND ((abs(A.LONGITUDE)*-1) > " + xmin + ")"
              + " AND (A.LATITUDE < " + ymax + ")"
              + " AND (A.LATITUDE > " + ymin + ")"
              + " ORDER BY Z.LASTMEASUREDATE DESC NULLS LAST";//

      GwlDataFacade gwlFacade = (GwlDataFacade) appContext.getBean(GwlDataFacade.class.getSimpleName());
      List<Map> query_result = gwlFacade.select(query);

      for (Map res : query_result) {
        try {
          JSONObject well = new JSONObject();
          String well_number = (String) res.get("casgemStateWellNbr");

          String site_code = (String) res.get("siteCode");
          BigDecimal longitude = (BigDecimal) res.get("longitude");
          BigDecimal latitude = (BigDecimal) res.get("latitude");
          String totaldepth = "-";
          if (res.get("totaldepth") != null) {
            totaldepth = res.get("totaldepth").toString();
          }

          String localwellid = "-";
          if (res.get("localwellid") != null) {
            localwellid = res.get("localwellid").toString();
          }

          String lastmeasuredate = "-";
          if (res.get("lastmeasuredate") != null) {
            DateFormat formatter = null;
            Date convertedDate = null;
            String date = res.get("lastmeasuredate").toString();
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
            convertedDate = (Date) formatter.parse(date);
            String newdate = new SimpleDateFormat("MM/dd/yyyy").format(convertedDate);
            lastmeasuredate = newdate;
          }

//                        String firstmeasuredate =  "-";
//                        if (res.get("firstmeasuredate")!= null)
//                        {
//                            DateFormat formatter = null;
//                            Date convertedDate = null;
//                            String date = res.get("firstmeasuredate").toString();
//                            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
//                            convertedDate = (Date) formatter.parse(date);
//                            String newdate = new SimpleDateFormat("MM/dd/yyyy").format(convertedDate);
//                            firstmeasuredate = newdate;
//                        }
//                        String high = "-";
//                        if(res.get("high") != null)
//                            high = res.get("high").toString();
//
//                        String low = "-";
//                        if(res.get("low") != null)
//                            low = res.get("low").toString();
          well.put("well_number", well_number)
                  .put("id", res.get("id"))
                  .put("site_code", site_code)
                  .put("longitude", longitude.doubleValue())
                  .put("latitude", latitude.doubleValue())
                  .put("totaldepth", totaldepth)
                  .put("localwellid", localwellid)
                  //                            .put("high",high)
                  //                            .put("low",low)
                  //                            .put("firstmeasuredate",firstmeasuredate)
                  .put("lastmeasuredate", lastmeasuredate);

          wells.put(well);
        } catch (Exception e) {
          Exceptions.printStackTrace(e);
        }
      }
      response.getWriter().print(wells.toString());
    } catch (Exception e) {
      Exceptions.printStackTrace(e);
    }
  }

//  @RequestMapping("/wellsInExtentOfBoundary2")
  public void wellsInExtentOfBoundary2(HttpServletRequest request, HttpServletResponse response) throws InvalidShapeFileException, FileNotFoundException, IOException {
    try {
      String data = request.getParameter("json");
      JSONObject json = new JSONObject(data);
      String xmax = json.getString("xmax");
      String xmin = json.getString("xmin");
      String ymax = json.getString("ymax");
      String ymin = json.getString("ymin");

      String query = "SELECT * FROM NCAL_WELLS"
              + " WHERE ((abs(LONGITUDE)*-1) < " + xmax + ")"
              + " AND ((abs(LONGITUDE)*-1) > " + xmin + ")"
              + " AND (LATITUDE < " + ymax + ")"
              + " AND (LATITUDE > " + ymin + ")";

      selectNcalWellsAndWriteToResponse(query, response);
    } catch (Exception e) {
      Exceptions.printStackTrace(e);
    }
  }

  private void selectNcalWellsAndWriteToResponse(String query, HttpServletResponse response)
          throws IOException, JSONException {
    writeMapListToResponseAsNcalWellJsonArray(selectNcalWells(query), null, response);
  }

  private void selectNcalWellsAndWriteToResponse(String query, LinkedHashSet<String> keys, HttpServletResponse response)
          throws IOException, JSONException {
    writeMapListToResponseAsNcalWellJsonArray(selectNcalWells(query), keys, response);
  }

  private List<Map> selectNcalWells(String query) {
    NcalWellsFacade ncalFacade = (NcalWellsFacade) appContext.getBean(NcalWellsFacade.class.getSimpleName());
    return ncalFacade.select(query);
  }

  private JSONArray selectNcalWellsAsJsonArray(String query, LinkedHashSet<String> keys) throws JSONException {
    return mapListToNcalWellJsonArray(selectNcalWells(query), keys);
  }

  private void writeMapListToResponseAsNcalWellJsonArray(List<Map> query_result, LinkedHashSet<String> keys, HttpServletResponse response)
          throws IOException, JSONException {
    JSONArray wells = mapListToNcalWellJsonArray(query_result, keys);
    response.setContentType("application/json");
    try (PrintWriter pw = response.getWriter()) {
      pw.print(wells.toString());
    }
  }

  private JSONArray mapListToNcalWellJsonArray(List<Map> query_result, LinkedHashSet<String> keys) throws JSONException {
    JSONArray wells = new JSONArray();
    for (Map m : query_result) {
      try {
        wells.put(mapToNcalWellJsonObject(m, keys));
      } catch (Exception e) {
        Exceptions.printStackTrace(e);
      }
    }
    return wells;
  }

  private LinkedHashSet<String> getNcalKeys(String... excludes) {
    LinkedHashSet<String> set = new LinkedHashSet<>();
    set.add("statewellnumber");
    set.add("localwellnumber");
    set.add("casgemwellid");
    set.add("casgemstationidstr");
    set.add("ncalwellsid");
    set.add("meid");
    set.add("latitude");
    set.add("longitude");
    set.add("monitoringentity");
    set.add("basinname");
    set.add("basinnumber");
    set.add("hydrologicregion");
    set.add("dwrregionoffice");
    set.add("county");
    set.add("welluse");
    set.add("casgemwell");
    set.add("totalwelldepth");
    set.add("coopagency");
    set.add("status");
    set.add("submitdate");
    set.add("status");
    set.add("type");
    for (String exclude : excludes) {
      set.remove(exclude);
    }
    return set;
  }

  private JSONObject mapToNcalWellJsonObject(Map m, LinkedHashSet<String> keys) throws JSONException {
    if (keys == null) {
      return defaultMapToNcalWellJsonObject(m);
    } else {
      return customMapToNcalWellJsonObject(m, keys);
    }
  }

  private JSONObject defaultMapToNcalWellJsonObject(Map m) throws JSONException {
    JSONObject station = new JSONObject();
    station.put("ncalwellsid", m.get("ncalWellsId") != null ? m.get("ncalWellsId") : "");
    station.put("localwellnumber", m.get("localWellNumber") != null ? m.get("localWellNumber") : "");
    station.put("statewellnumber", m.get("stateWellNumber") != null ? m.get("stateWellNumber") : "");
    station.put("latitude", m.get("latitude") != null ? ((BigDecimal) m.get("latitude")).doubleValue() : "");
    station.put("longitude", m.get("longitude") != null ? ((BigDecimal) m.get("longitude")).doubleValue() : "");
    station.put("casgemwellid", m.get("casgemWellId") != null ? m.get("casgemWellId") : "");
    station.put("casgemstationidstr", m.get("casgemStationIdStr") != null ? m.get("casgemStationIdStr") : "");
    station.put("meid", m.get("meId") != null ? m.get("meId") : "");
    station.put("monitoringentity", m.get("monitoringEntity") != null ? m.get("monitoringEntity") : "");
    station.put("basinname", m.get("basinName") != null ? m.get("basinName") : "");
    station.put("basinnumber", m.get("basinNumber") != null ? m.get("basinNumber") : "");
    station.put("hydrologicregion", m.get("hydrologicRegion") != null ? m.get("hydrologicRegion") : "");
    station.put("dwrregionoffice", m.get("dwrRegionOffice") != null ? m.get("dwrRegionOffice") : "");
    station.put("county", m.get("county") != null ? m.get("county") : "");
    station.put("welluse", m.get("wellUse") != null ? m.get("wellUse") : "");
    station.put("casgemwell", m.get("casgemWell") != null ? m.get("casgemWell") : "");
    station.put("totalwelldepth", m.get("totalWellDepth") != null ? m.get("totalWellDepth") : "");
    station.put("wellconstruction", m.get("wellConstruction") != null ? m.get("wellConstruction") : "");
    station.put("coopagency", m.get("coopAgency") != null ? m.get("coopAgency") : "");
    station.put("status", m.get("status") != null ? m.get("status") : "");
    station.put("submitdate", m.get("submitDate") != null ? m.get("submitDate") : "");
    station.put("type", "well");
    return station;
  }

  private JSONObject customMapToNcalWellJsonObject(Map m, LinkedHashSet<String> keys) throws JSONException {
    JSONObject station = new JSONObject();
    if (keys.contains("ncalwellsid")) {
      station.put("ncalwellsid", m.get("ncalWellsId") != null ? m.get("ncalWellsId") : "");
    }
    if (keys.contains("localwellnumber")) {
      station.put("localwellnumber", m.get("localWellNumber") != null ? m.get("localWellNumber") : "");
    }
    if (keys.contains("statewellnumber")) {
      station.put("statewellnumber", m.get("stateWellNumber") != null ? m.get("stateWellNumber") : "");
    }
    if (keys.contains("latitude")) {
      station.put("latitude", m.get("latitude") != null ? ((BigDecimal) m.get("latitude")).doubleValue() : "");
    }
    if (keys.contains("longitude")) {
      station.put("longitude", m.get("longitude") != null ? ((BigDecimal) m.get("longitude")).doubleValue() : "");
    }
    if (keys.contains("casgemwellid")) {
      station.put("casgemwellid", m.get("casgemWellId") != null ? m.get("casgemWellId") : "");
    }
    if (keys.contains("casgemstationidstr")) {
      station.put("casgemstationidstr", m.get("casgemStationIdStr") != null ? m.get("casgemStationIdStr") : "");
    }
    if (keys.contains("meid")) {
      station.put("meid", m.get("meId") != null ? m.get("meId") : "");
    }
    if (keys.contains("monitoringentity")) {
      station.put("monitoringentity", m.get("monitoringEntity") != null ? m.get("monitoringEntity") : "");
    }
    if (keys.contains("basinname")) {
      station.put("basinname", m.get("basinName") != null ? m.get("basinName") : "");
    }
    if (keys.contains("basinnumber")) {
      station.put("basinnumber", m.get("basinNumber") != null ? m.get("basinNumber") : "");
    }
    if (keys.contains("hydrologicregion")) {
      station.put("hydrologicregion", m.get("hydrologicRegion") != null ? m.get("hydrologicRegion") : "");
    }
    if (keys.contains("dwrregionoffice")) {
      station.put("dwrregionoffice", m.get("dwrRegionOffice") != null ? m.get("dwrRegionOffice") : "");
    }
    if (keys.contains("county")) {
      station.put("county", m.get("county") != null ? m.get("county") : "");
    }
    if (keys.contains("welluse")) {
      station.put("welluse", m.get("wellUse") != null ? m.get("wellUse") : "");
    }
    if (keys.contains("casgemwell")) {
      station.put("casgemwell", m.get("casgemWell") != null ? m.get("casgemWell") : "");
    }
    if (keys.contains("totalwelldepth")) {
      station.put("totalwelldepth", m.get("totalWellDepth") != null ? m.get("totalWellDepth") : "");
    }
    if (keys.contains("wellconstruction")) {
      station.put("wellconstruction", m.get("wellConstruction") != null ? m.get("wellConstruction") : "");
    }
    if (keys.contains("coopagency")) {
      station.put("coopagency", m.get("coopAgency") != null ? m.get("coopAgency") : "");
    }
    if (keys.contains("status")) {
      station.put("status", m.get("status") != null ? m.get("status") : "");
    }
    if (keys.contains("submitdate")) {
      station.put("submitdate", m.get("submitDate") != null ? m.get("submitDate") : "");
    }
    if (keys.contains("type")) {
      station.put("type", "well");
    }
    return station;
  }

//  @RequestMapping("/getBufferedPoints")
  public void getBufferedPoints(HttpServletRequest request, HttpServletResponse response) throws InvalidShapeFileException, FileNotFoundException, IOException {
    Double lat = Double.parseDouble(request.getParameter("lat"));
    Double lng = Double.parseDouble(request.getParameter("lng"));
    Double distance_feet = Double.parseDouble(request.getParameter("distance"));
    Double distance = distance_feet / 5280.0;
    WellKingsFacade wkFacade = (WellKingsFacade) appContext.getBean(WellKingsFacade.class.getSimpleName());
    WellKings wk = new WellKings();

    //        ProjectionInfo proj;
    Coord clickLocation = new Coord(lng, lat);
    ProjectionInfo latlong = Projections.Geographic.getWorld().WGS1984;
    ProjectionInfo web = Projections.Projected.getWorld().WebMercator;
    Coord webClick = null;
    try {
      webClick = Reproject.reprojectCoordinate(clickLocation, latlong, web);

      //Make a bounding box around the centerpoint devided
      double minLat = webClick.Y - (distance_feet * Math.cos(Math.toRadians(lat)));  //* 0.3048
      double maxLat = webClick.Y + (distance_feet * Math.cos(Math.toRadians(lat)));
      double minLong = webClick.X - (distance_feet * Math.cos(Math.toRadians(lat)));
      double maxLong = webClick.X + (distance_feet * Math.cos(Math.toRadians(lat)));

      Coord lowerLeft = new Coord(minLong, minLat);
      Coord upperRight = new Coord(maxLong, maxLat);

      lowerLeft = Reproject.reprojectCoordinate(lowerLeft, web, latlong);
      upperRight = Reproject.reprojectCoordinate(upperRight, web, latlong);

      //select
      String query = "SELECT DISTINCT "
              + " A.CASGEM_STATION_ID AS ID,"
              + " A.CASGEM_STATE_WELL_NBR, "
              + " (abs(A.LONGITUDE)*-1) AS longitude, "
              + " A.LATITUDE, "
              + " B.SITE_CODE as sitecode, "
              + " A.TOTAL_DEPTH_FT as totaldepth, "
              + " A.LOCAL_WELL_DESIGNATION as localwellid, "
              + "A.TOTAL_DEPTH_FT AS TOTALDEPTH, "
              //                        + "X.LOW AS LOW, "
              //                        + "Y.HIGH AS HIGH, "
              + "Z.LASTMEASUREDATE AS LASTMEASUREDATE "
              //                        + "W.FIRSTMEASUREDATE AS FIRSTMEASUREDATE "

              + " FROM CASGEM_STATION A"
              + " LEFT JOIN MASTER_SITE B ON A.MASTER_SITE_ID = B.MASTER_SITE_ID "
              //                        +" LEFT JOIN (SELECT MAX(PERFORATION_BOTTOM_MSRMNT) AS LOW,CASGEM_STATION_ID FROM CASGEM_STATION_PERFORATION GROUP BY CASGEM_STATION_ID  ) X ON X.CASGEM_STATION_ID = A.CASGEM_STATION_ID "
              //                        +" LEFT JOIN (SELECT MAX(PERFORATION_TOP_MSRMNT) AS HIGH,CASGEM_STATION_ID FROM CASGEM_STATION_PERFORATION GROUP BY CASGEM_STATION_ID  ) Y ON Y.CASGEM_STATION_ID = A.CASGEM_STATION_ID "

              + " LEFT JOIN (SELECT MAX(MEASUREMENT_DT) AS LASTMEASUREDATE,CASGEM_STATION_ID FROM ELEVATION_DATA_READING GROUP BY CASGEM_STATION_ID  ) Z ON Z.CASGEM_STATION_ID = A.CASGEM_STATION_ID"
              //                        +" LEFT JOIN (SELECT MIN(MEASUREMENT_DT) AS FIRSTMEASUREDATE,CASGEM_STATION_ID FROM ELEVATION_DATA_READING GROUP BY CASGEM_STATION_ID  ) W ON W.CASGEM_STATION_ID = A.CASGEM_STATION_ID"

              + " WHERE (abs(A.LONGITUDE)*-1) BETWEEN " + lowerLeft.X + " AND " + upperRight.X + " AND A.LATITUDE BETWEEN " + lowerLeft.Y + " AND " + upperRight.Y;
      List<Map> maps = wkFacade.select(query);
      JSONArray results = new JSONArray();
      for (Map m : maps) {
        try {
          double resultlat = Double.parseDouble(m.get("latitude").toString());
          double resultlng = Math.abs(Double.parseDouble(m.get("longitude").toString())) * -1;
          Coord wgs84 = new Coord(resultlng, resultlat);

          Coord webCoord = Reproject.reprojectCoordinate(wgs84, latlong, web);
          double distanceFromCenter = Double.MAX_VALUE;
          if (webClick != null) {

//                        distanceFromCenter =getDistance(webClick.X,webCoord.X, webClick.Y, webCoord.Y);
            distanceFromCenter = webClick.distance(webCoord);
          }

                    //double distanceFromCenter = getDistance(lng,resultlng, lat, resultlat);
          double wideDistance = distance_feet / Math.cos(Math.toRadians(lat));
          if (distanceFromCenter <= wideDistance * 0.3048) {
            JSONObject obj = new JSONObject();
            String name = "-";
            if (m.get("casgemStateWellNbr")!= null)
              name = m.get("casgemStateWellNbr").toString();

            String totaldepth = "-";
            if (m.get("totaldepth") != null) {
              totaldepth = m.get("totaldepth").toString();
            }

            String localwellid = "-";
            if (m.get("localwellid") != null) {
              localwellid = m.get("localwellid").toString();
            }

            String sitecode = "-";
            if (m.get("sitecode") != null) {
              sitecode = m.get("sitecode").toString();
            }

            String lastmeasuredate = "-";
            if (m.get("lastmeasuredate") != null) {
              DateFormat formatter = null;
              Date convertedDate = null;
              String date = m.get("lastmeasuredate").toString();
              formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
              convertedDate = (Date) formatter.parse(date);
              String newdate = new SimpleDateFormat("MM/dd/yyyy").format(convertedDate);
              lastmeasuredate = newdate;
            }

//                        String firstmeasuredate =  "-";
//                        if (m.get("firstmeasuredate")!= null)
//                        {
//                            DateFormat formatter = null;
//                            Date convertedDate = null;
//                            String date = m.get("firstmeasuredate").toString();
//                            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
//                            convertedDate = (Date) formatter.parse(date);
//                            String newdate = new SimpleDateFormat("MM/dd/yyyy").format(convertedDate);
//                            firstmeasuredate = newdate;
//                        }
//                        String high = "-";
//                        if(m.get("high") != null)
//                            high = m.get("high").toString();
//
//                        String low = "-";
//                        if(m.get("low") != null)
//                            low = m.get("low").toString();
            double dist = (distanceFromCenter * Math.cos(Math.toRadians(lat)) * 3.28084) / 5280;
            obj.put("id", m.get("id"));
            obj.put("distance", dist);
            obj.put("wellname", m.get("casgemStateWellNbr"));
            obj.put("latitude", resultlat);
            obj.put("longitude", resultlng);
            obj.put("totaldepth", totaldepth);
            obj.put("localwellid", localwellid);
            obj.put("sitecode", sitecode);
//                        obj.put("high",high);
//                        obj.put("low",low);
//                        obj.put("firstmeasuredate",firstmeasuredate);
            obj.put("lastmeasuredate", lastmeasuredate);

            results.put(obj);
          }
        } catch (Exception ex) {
          Exceptions.printStackTrace(ex);
        }
      }

            //Do a merge sort on the results array to order the distances from center point
      JSONArray tempMergeArray = new JSONArray();
      doMergeSort(0, results.length() - 1, results, tempMergeArray);

      response.getWriter().print(results.toString());
    } catch (ProjectionException ex) {
      Exceptions.printStackTrace(ex);
    }
  }

//  @RequestMapping("/getBufferedPoints2")
  public void getBufferedPoints2(HttpServletRequest request, HttpServletResponse response) throws InvalidShapeFileException, FileNotFoundException, IOException {
    Double lat = Double.parseDouble(request.getParameter("lat"));
    Double lng = Double.parseDouble(request.getParameter("lng"));
    Double distance_feet = Double.parseDouble(request.getParameter("distance"));
    Double distance = distance_feet / 5280.0;
    NcalWellsFacade ncalFacade = (NcalWellsFacade) appContext.getBean(NcalWellsFacade.class.getSimpleName());
    WellKings wk = new WellKings();

    //        ProjectionInfo proj;
    Coord clickLocation = new Coord(lng, lat);
    ProjectionInfo latlong = Projections.Geographic.getWorld().WGS1984;
    ProjectionInfo web = Projections.Projected.getWorld().WebMercator;
    Coord webClick = null;
    try {
      webClick = Reproject.reprojectCoordinate(clickLocation, latlong, web);

      //Make a bounding box around the centerpoint devided
      double minLat = webClick.Y - (distance_feet * Math.cos(Math.toRadians(lat)));  //* 0.3048
      double maxLat = webClick.Y + (distance_feet * Math.cos(Math.toRadians(lat)));
      double minLong = webClick.X - (distance_feet * Math.cos(Math.toRadians(lat)));
      double maxLong = webClick.X + (distance_feet * Math.cos(Math.toRadians(lat)));

      Coord lowerLeft = new Coord(minLong, minLat);
      Coord upperRight = new Coord(maxLong, maxLat);

      lowerLeft = Reproject.reprojectCoordinate(lowerLeft, web, latlong);
      upperRight = Reproject.reprojectCoordinate(upperRight, web, latlong);

      //select
      String query = "SELECT * FROM NCAL_WELLS "
              + " WHERE (abs(LONGITUDE)*-1) BETWEEN " + lowerLeft.X + " AND " + upperRight.X + " AND LATITUDE BETWEEN " + lowerLeft.Y + " AND " + upperRight.Y;
      List<Map> maps = ncalFacade.select(query);
      JSONArray results = new JSONArray();
      for (Map m : maps) {
        try {
          double resultlat = Double.parseDouble(m.get("latitude").toString());
          double resultlng = Math.abs(Double.parseDouble(m.get("longitude").toString())) * -1;
          Coord wgs84 = new Coord(resultlng, resultlat);

          Coord webCoord = Reproject.reprojectCoordinate(wgs84, latlong, web);
          double distanceFromCenter = Double.MAX_VALUE;
          if (webClick != null) {
            distanceFromCenter = webClick.distance(webCoord);
          }

          double wideDistance = distance_feet / Math.cos(Math.toRadians(lat));
          if (distanceFromCenter <= wideDistance * 0.3048) {
            double dist = (distanceFromCenter * Math.cos(Math.toRadians(lat)) * 3.28084) / 5280;

            JSONObject station = mapToNcalWellJsonObject(m, getNcalKeys());
            station.put("distance", dist);

            results.put(station);
          }
        } catch (Exception ex) {
          Exceptions.printStackTrace(ex);
        }
      }

      //Do a merge sort on the results array to order the distances from center point
      JSONArray tempMergeArray = new JSONArray();
      doMergeSort(0, results.length() - 1, results, tempMergeArray);
      response.setContentType("application/json");
      response.getWriter().print(results.toString());
    } catch (ProjectionException ex) {
      Exceptions.printStackTrace(ex);
    }
  }

  private void doMergeSort(int lowerIndex, int higherIndex, JSONArray results, JSONArray tempMergeArray) {

    if (lowerIndex < higherIndex) {
      int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
      // Below step sorts the left side of the array
      doMergeSort(lowerIndex, middle, results, tempMergeArray);
      // Below step sorts the right side of the array
      doMergeSort(middle + 1, higherIndex, results, tempMergeArray);
      // Now merge both sides
      mergeParts(lowerIndex, middle, higherIndex, results, tempMergeArray);
    }
  }

  private void mergeParts(int lowerIndex, int middle, int higherIndex, JSONArray results, JSONArray tempMergeArray) {
    try {
      for (int i = lowerIndex; i <= higherIndex; i++) {
        tempMergeArray.put(i, results.getJSONObject(i));
        //            tempMergArr[i] = array[i];
      }
      int i = lowerIndex;
      int j = middle + 1;
      int k = lowerIndex;
      while (i <= middle && j <= higherIndex) {

        JSONObject t1 = tempMergeArray.getJSONObject(i);
        double t1val = t1.getDouble("distance");

        JSONObject t2 = tempMergeArray.getJSONObject(j);
        double t2val = t2.getDouble("distance");

        if (t1val <= t2val) {
          //            if (tempMergArr[i] <= tempMergArr[j]) {
          results.put(k, tempMergeArray.getJSONObject(i));
          //                array[k] = tempMergArr[i];
          i++;
        } else {
          results.put(k, tempMergeArray.getJSONObject(j));
          //                array[k] = tempMergArr[j];
          j++;
        }
        k++;
      }
      while (i <= middle) {
        results.put(k, tempMergeArray.getJSONObject(i));
        //            array[k] = tempMergArr[i];
        k++;
        i++;
      }
    } catch (Exception ex) {
      Exceptions.printStackTrace(ex);
    }

  }

  public double getDistance(double x1, double x2, double y1, double y2) {
    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)); //distance formula
  }

//  @RequestMapping("/getWellsBy")
  public void getWellsBy(@RequestParam(value = "type", required = true) String type, @RequestParam(value = "begin", required = true) String begin, @RequestParam(value = "end", required = true) String end, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
    try {
      String query = "";
      switch (type) {
        case "depth":
          Integer beginDepth = Integer.parseInt(begin);
          Integer endDepth = Integer.parseInt(end);
          query = "SELECT * FROM NCAL_WELLS WHERE TOTAL_WELL_DEPTH BETWEEN " + beginDepth + " AND " + endDepth;
          break;
        case "pump":
          Integer beginPump = Integer.parseInt(begin);
          Integer endPump = Integer.parseInt(end);
          query = "SELECT * FROM NCAL_WELLS WHERE WELL_CAPACITY BETWEEN " + beginPump + " AND " + endPump;
          break;
        case "reportdate":
        default:
          query = "SELECT * FROM NCAL_WELLS WHERE to_date(SUBMIT_DATE, 'MM/DD/YYYY') BETWEEN TO_DATE('" + begin + "', 'MM/DD/YYYY') AND TO_DATE('" + end + "', 'MM/DD/YYYY')";
      }

      this.selectNcalWellsAndWriteToResponse(query, response);
    } catch (Exception ex) {
      Exceptions.printStackTrace(ex);
    }
  }

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONArray readJSON(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONArray json = new JSONArray(jsonText);
      return json;
    } finally {
      is.close();
    }
  }

//  @RequestMapping("/getElevationReading")
  public void getElevationReading(@RequestParam(value = "id", required = true) String stationId,
                                  @RequestParam(value = "well", required = false) String stateWellId,
                                  @RequestParam(value = "markerColors", required = false) String markerColors,
                                  @RequestParam(value = "riverStations", required = false) String riverStations,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws IOException, JSONException {
    try {
      ElevationDataReadingFacade elvationFacade = (ElevationDataReadingFacade) appContext.getBean(ElevationDataReadingFacade.class.getSimpleName());
      JSONObject jsonResponse = new JSONObject();
      // build in string 
      String[] splitIds = stationId.split(",");
      String[] colors = markerColors != null && !markerColors.isEmpty() ? markerColors.split(",") : null;
      String[] rivStgStations = riverStations != null && !riverStations.isEmpty() ? riverStations.split(",") : null;
      String rivStgStationUrl = "";

      String primaryStationId = splitIds[0];

      String query = "SELECT ELEVATION_DATA_READING.*, to_char(MEASUREMENT_DT, 'MM/DD/YYYY') AS MEASUREDT, to_char(MEASUREMENT_DT, 'YYYY-MM-DD') AS FORMAT_Dt FROM ELEVATION_DATA_READING"
              + " WHERE CASGEM_STATION_ID in (" + stationId + ")"
              + " ORDER BY MEASUREMENT_DT ASC";

//      List<Map> maps = elvationFacade.executeQuery(query, params.toArray()); 
      List<Map> maps = elvationFacade.select(query);
      JSONArray results = new JSONArray();
      JSONArray seriesList = new JSONArray();
      JSONArray rpe = new JSONArray();
      JSONArray gse = new JSONArray();
      JSONArray rpws = new JSONArray();
      JSONArray gstows = new JSONArray();
      JSONObject metadata = new JSONObject();
      metadata
              .put("title", "Ground Levels for Well " + stateWellId)
              .put("xlabel", "Date")
              .put("ylabel", "Elevation (ft)")
              .put("y2label", "River Stage (ft)");

      JSONArray series = new JSONArray();
      series.put(new JSONObject()
              .put("axis", "y1")
              .put("color", "#543a1f")
              .put("label", "GSE")
              .put("connectSeparatedPoints", true)
              .put("stroke_pattern", new JSONArray().put(5).put(5)));
      JSONObject wseArrays = new JSONObject();
      int i = 0;
      String color;
      for (String id : splitIds) {
        if (colors != null && i < colors.length) {
          color = colors[i];
        } else {
          if (i == 0) {
            color = "#FF0000";
          } else {
            color = "#" + Integer.toHexString((int) (Math.random() * 160 + 95))
                    + Integer.toHexString((int) (Math.random() * 160 + 95))
                    + Integer.toHexString((int) (Math.random() * 160 + 95));
          }
        }

        series.put(new JSONObject()
                .put("axis", "y1")
                .put("color", color)
                .put("label", "WSE ")
                // WSE + state well number is appended on the front end
                .put("casgemstationid", id)
                .put("draw_points", true)
                .put("connectSeparatedPoints", true)
                .put("point_size", 3)
                .put("stroke_width", 1));
        wseArrays.put(id, new JSONArray());
        i++;
      }

      JSONObject rsArrays = new JSONObject();
      if (rivStgStations != null) {
        String rsColor;
        for (String station : rivStgStations) {
          if (colors != null && i < colors.length) {
            rsColor = colors[i];
          } else {
            rsColor = "#" + Integer.toHexString((int) (Math.random() * 160 + 95))
                    + Integer.toHexString((int) (Math.random() * 160 + 95))
                    + Integer.toHexString((int) (Math.random() * 160 + 95));
          }

          series.put(new JSONObject()
                  .put("axis", "y2")
                  .put("color", rsColor)
                  .put("label", "RS " + station)
                  .put("draw_points", true)
                  .put("connectSeparatedPoints", true)
                  .put("point_size", 3)
                  .put("stroke_width", 1)
                  .put("riverstagestation", station)
          );
          rsArrays.put(station, new JSONArray());
          i++;
        }
      }

      metadata.put("series", series);
      for (Map m : maps) {
        try {
          if (m.get("referencePointReading") == null || m.get("groundSurfaceElevation") == null) {
            continue; // skip record due to dependence on refPoint for other calculations
          }
          JSONObject station = new JSONObject();
          String currentStationId = ((BigDecimal) m.get("casgemStationId")).toPlainString();
          station.put("measuredt", m.get("measuredt") != null ? m.get("measuredt") : "");
          station.put("casgemstationid", m.get("casgemStationId") != null ? m.get("casgemStationId") : "");
          station.put("rpe", m.get("referencePointElevation") != null ? ((BigDecimal) m.get("referencePointElevation")).doubleValue() : "");
          station.put("gse", m.get("groundSurfaceElevation") != null ? ((BigDecimal) m.get("groundSurfaceElevation")).doubleValue() : "");
          station.put("rpws", m.get("referencePointReading") != null ? ((BigDecimal) m.get("referencePointReading")).doubleValue() : "");

          station.put("wse", station.getDouble("rpe") - station.getDouble("rpws"));
          station.put("gstows", station.getDouble("rpws") - (station.getDouble("rpe") - station.getDouble("gse")));
          station.put("coopagencyid", m.get("cooperatingAgencyOrgId"));
          station.put("comments", m.get("comments"));

          if (currentStationId.equalsIgnoreCase(primaryStationId)) {
            results.put(station);
            //rpe data
            JSONArray rpedata = new JSONArray();
            rpedata.put(m.get("formatDt") + "T00:00").put(((BigDecimal) m.get("referencePointElevation")).doubleValue());
            rpe.put(rpedata);
            //gse data
            JSONArray gsedata = new JSONArray();
            gsedata.put(m.get("formatDt") + "T00:00").put(((BigDecimal) m.get("groundSurfaceElevation")).doubleValue());
            gse.put(gsedata);
            //rpws data
            JSONArray rpwsdata = new JSONArray();
            rpwsdata.put(m.get("formatDt") + "T00:00").put(((BigDecimal) m.get("referencePointReading")).doubleValue());
            rpws.put(rpwsdata);          //gstows
            JSONArray gstowsdata = new JSONArray();
            gstowsdata.put(m.get("formatDt") + "T00:00").put(station.getDouble("rpws") - (station.getDouble("rpe") - station.getDouble("gse")));
            gstows.put(gstowsdata);
          }          //wse
          JSONArray wsedata = new JSONArray();
          wsedata.put(m.get("measurementDt")).put(station.getDouble("rpe") - station.getDouble("rpws"));
          wsedata.put(m.get("formatDt") + "T00:00").put(station.getDouble("rpe") - station.getDouble("rpws"));
          wseArrays.getJSONArray(currentStationId).put(wsedata);
        } catch (Exception ex) {
          Exceptions.printStackTrace(ex);
        }
      }
      seriesList.put(gse); //.put(rpe).put(gse).put(rpws).put(wse).put(gstows);
      for (String id : splitIds) {
        seriesList.put(wseArrays.getJSONArray(id));
      }

      if (rivStgStations != null) {
        JSONArray rsData = new JSONArray();
        JSONObject rsRecord = new JSONObject();
        String date = "";

        for (String station : rivStgStations) {
          rivStgStationUrl = "http://cdec.water.ca.gov/preciptemp/req/JsonDataServlet?Stations=" + station;
          rivStgStationUrl += "&SensorNums=1&dur_code=H&Start=2017-05-24&End=2017-08-24";  /* currently set to get 3 months */

          rsData = readJSON(rivStgStationUrl);
          date = "";
          for (int j = 0; j < rsData.length(); j++) {
            JSONArray dateValPair = new JSONArray();
            rsRecord = rsData.getJSONObject(j);
            if (rsRecord.has("date") && rsRecord.has("value")) {
              date = rsRecord.getString("date");
              dateValPair.put(date);
              dateValPair.put(rsRecord.getDouble("value"));
              rsArrays.getJSONArray(station).put(dateValPair);
            }
          }
          seriesList.put(rsArrays.getJSONArray(station));
        }
      }

      jsonResponse.put("metadata", metadata);
      jsonResponse.put("data", seriesList);
      jsonResponse.put("rawdata", results);
      response.setContentType("application/json");
      response.getWriter().print(jsonResponse.toString());
    } catch (Exception ex) {
      Exceptions.printStackTrace(ex);
    }
  }

  private double getYear(Timestamp timestamp) {
    Calendar c = Calendar.getInstance();
    c.setTimeInMillis(timestamp.getTime());
    double v = c.get(Calendar.YEAR);
    v += ((double) c.get(Calendar.DAY_OF_YEAR)) / c.getActualMaximum(Calendar.DAY_OF_YEAR);
    return v;
  }

  private static final Map<String, byte[]> ICON_CACHE = new HashMap<>();

//  @RequestMapping("/getElevationReadingIcon")
  public void getElevationReadingIcon(
          @RequestParam(value = "id", required = true) String stationId,
          @RequestParam(value = "well", required = false) String stateWellId,
          @RequestParam(value = "width", required = true) int width,
          @RequestParam(value = "height", required = true) int height,
          HttpServletRequest request, HttpServletResponse response)
          throws Exception {
    String noDataKey = "NO_DATA_FOUND";
    try {
      String key = stationId + "_" + stateWellId + "_" + width + "_" + height;
      if (!ICON_CACHE.containsKey(key)) {
        ElevationDataReadingFacade elevationFacade = (ElevationDataReadingFacade) appContext.getBean(ElevationDataReadingFacade.class.getSimpleName());
//        JSONObject jsonResponse = new JSONObject();
        String query = "SELECT ELEVATION_DATA_READING.*, to_char(MEASUREMENT_DT, 'MM/DD/YYYY') AS MEASUREDT FROM ELEVATION_DATA_READING"
                + " WHERE CASGEM_STATION_ID = '" + stationId + "'"
                + " ORDER BY MEASUREMENT_DT ASC";
        List<Map> maps = elevationFacade.select(query);

        if (maps != null && maps.size() > 0) {
          XYSeriesCollection dataset = new XYSeriesCollection();
          XYSeries series = new XYSeries("");

          for (Map m : maps) {
            try {
              series.add(getYear((java.sql.Timestamp) m.get("measurementDt")), ((BigDecimal) m.get("referencePointElevation")).doubleValue() - ((BigDecimal) m.get("referencePointReading")).doubleValue());
            } catch (Exception ex) {
//              ex.printStackTrace(System.err);
            }
          }
          dataset.addSeries(series);

          // createXYLineChart or createScatterPlot
          JFreeChart chart = ChartFactory.createScatterPlot("", "", "", dataset, PlotOrientation.VERTICAL, false, false, false);
          chart.setBackgroundPaint(Color.WHITE);
          chart.setBorderVisible(false);
          chart.setPadding(new RectangleInsets(-1, 0, 1, 2));

          XYPlot plot = chart.getXYPlot();
          plot.setBackgroundPaint(Color.WHITE);

          double gridSizeX = 26;
          double gridSizeY = 11;
          double padding = 4;
          width += 15;
          height += 10;
          int fontSizeX = 7;
          int fontSizeY = 7;

//          plot.getDomainAxis().setVisible(false);
          plot.getDomainAxis().setTickLabelFont(new Font("Dialog", Font.PLAIN, fontSizeX));
//          plot.getDomainAxis().setLowerMargin(0);
//          plot.getDomainAxis().setUpperMargin(0);
          plot.setDomainGridlinePaint(Color.GRAY);
          double pixelWidth = (series.getMaxX() - series.getMinX()) / width;
          ((NumberAxis) plot.getDomainAxis()).setTickUnit(new NumberTickUnit(pixelWidth * gridSizeX));
          plot.getDomainAxis().setRange(series.getMinX() - pixelWidth * padding, series.getMaxX() + pixelWidth * padding);
          ((NumberAxis) plot.getDomainAxis()).setNumberFormatOverride(new DecimalFormat("####"));

//          plot.getRangeAxis().setVisible(false);
          plot.getRangeAxis().setTickLabelFont(new Font("Dialog", Font.PLAIN, fontSizeY));
//          plot.getRangeAxis().setLowerMargin(0);
//          plot.getRangeAxis().setUpperMargin(0);
          plot.setRangeGridlinePaint(Color.GRAY);
          double pixelHeight = (series.getMaxY() - series.getMinY()) / height;
          ((NumberAxis) plot.getRangeAxis()).setTickUnit(new NumberTickUnit(pixelHeight * gridSizeY));
          plot.getRangeAxis().setRange(series.getMinY() - pixelHeight * padding, series.getMaxY() + pixelHeight * padding);
          ((NumberAxis) plot.getRangeAxis()).setNumberFormatOverride(new DecimalFormat("###0.#"));

          plot.setInsets(new RectangleInsets(0, 0, 0, 0));
          plot.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
//          plot.setOutlineVisible(false);

          XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
          renderer.setSeriesLinesVisible(0, true);
          renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-1, -1, 3, 3));//new Rectangle(2, 2));
          renderer.setSeriesPaint(0, Color.BLUE);
          renderer.setSeriesItemLabelsVisible(0, false);
          plot.setRenderer(renderer);
//          XYItemRenderer renderer = plot.getRenderer();
//          renderer.setSeriesShape(0, new Rectangle(2, 2));
//          renderer.setSeriesPaint(0, Color.BLUE);
//          renderer.setSeriesItemLabelsVisible(0, false);

          byte[] bytes = ChartUtilities.encodeAsPNG(chart.createBufferedImage(width, height));
          ICON_CACHE.put(key, bytes);
        } // else use no data found png
        else {
          ICON_CACHE.put(key, null); // null indicates no data
          if (!ICON_CACHE.containsKey(noDataKey)) {
            InputStream is = ContourServlet.class.getResourceAsStream("ChartNoDataFound.png");
            byte[] bytes = IOUtils.toByteArray(is);
            ICON_CACHE.put(noDataKey, bytes);
          }
        }
      }

      if (!ICON_CACHE.containsKey(key) || ICON_CACHE.get(key) == null) {
        key = noDataKey;
      }

      response.setContentType("image/png");
      try (OutputStream os = response.getOutputStream()) {
        IOUtils.write(ICON_CACHE.get(key), os);
      }
    } catch (Exception ex) {
      Exceptions.printStackTrace(ex);
      throw ex;
    }
  }

//  @RequestMapping("/getTimeSeries")
  public void getTimeSeries(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String wellNumbers = request.getParameter("wellNumbers");
    String wellNames = request.getParameter("wellNames");
    JSONArray namesarr = new JSONArray(wellNames);
    JSONArray arr = new JSONArray(wellNumbers);
    GwlDataFacade gwlFacade = (GwlDataFacade) appContext.getBean(GwlDataFacade.class.getSimpleName());
    //Hashmap of datestring, wse pairs
    HashMap<String, HashMap<String, Double>> allSeries = new HashMap<>();
    List<String> allDates = new ArrayList<>();
    String returnString = "Date";
    try {
      for (int i = 0; i < arr.length(); i++) {
        String well = arr.getString(i);
        String name = namesarr.getString(i);
        String key = name;
        if (name.isEmpty()) {
          key = well;
        }

        returnString += "," + key;
        HashMap<String, Double> series = new HashMap<>();
        allSeries.put(key, series);
        String query = "SELECT CASGEM_STATION.CASGEM_STATION_ID AS ID,ELEVATION_DATA_READING.MEASUREMENT_DT as measurementDate, ELEVATION_DATA_READING.REFERENCE_POINT_ELEVATION AS REFELEVATION,ELEVATION_DATA_READING.REFERENCE_POINT_READING AS REFREADING FROM ELEVATION_DATA_READING "
                + "JOIN CASGEM_STATION ON ELEVATION_DATA_READING.CASGEM_STATION_ID = CASGEM_STATION.CASGEM_STATION_ID  "
                + "WHERE CASGEM_STATION.CASGEM_STATION_ID = " + well + "";

        List<Map> maps = gwlFacade.select(query);
        for (Map m : maps) {
          if (m.get("measurementdate") == null || m.get("refelevation") == null || m.get("refreading") == null) {
            continue;
          }
          Double elev = ((BigDecimal) m.get("refelevation")).doubleValue();
          Double reading = ((BigDecimal) m.get("refreading")).doubleValue();
          Double graphElevation = elev - reading;
          Calendar cal = Calendar.getInstance();
          cal.setTimeInMillis(((Timestamp) m.get("measurementdate")).getTime());
          String date = sdf.format(cal.getTime());
          series.put(date, graphElevation);
          if (!allDates.contains(date)) {
            allDates.add(date);
          }
        }
      }
//            for(String wellId : wells){
//                String well = wellId.replace("\"","");
//
//            }
    } catch (Exception ex) {
      Exceptions.printStackTrace(ex);
    }
    returnString += "\n";
    Collections.sort(allDates, new Comparator<String>() {

      @Override
      public int compare(String o1, String o2) {
        return o1.compareTo(o2);
      }
    });
    for (String date : allDates) {
      returnString += date;
      for (int i = 0; i < arr.length(); i++) {
        String well = arr.getString(i);
        String name = namesarr.getString(i);
        String wellId = name;
        if (name.isEmpty()) {
          wellId = well;
        }

        returnString += ",";
        if (allSeries.get(wellId) != null && allSeries.get(wellId).get(date) != null) {
          returnString += allSeries.get(wellId).get(date);
        }
      }
      returnString += "\n";
    }
    JSONObject obj = new JSONObject();
    obj.put("data", returnString);
    obj.put("numberOfColumns", arr.length());
    response.getWriter().print(obj);

  }

  private static class ShapeUpload {

    public MultipartFile shp, shx, dbf, prj, zip;

    public ShapeUpload(MultipartHttpServletRequest request) {
      Iterator<String> itr = request.getFileNames();
      while (itr.hasNext()) {
        setMultipleFiles(request, itr.next());
      }
    }

    public void setMultipleFiles(MultipartHttpServletRequest request, String fileName) {
      MultiValueMap<String, MultipartFile> fileMap = request.getMultiFileMap();
      List<MultipartFile> files = fileMap.get(fileName);
      String name;
      for (MultipartFile file : files) {
        name = file.getOriginalFilename();
        if (name.endsWith("zip")) {
          zip = file;
          unzipFolder(request);
        } else if (name.endsWith("shp")) {
          shp = file;
        } else if (name.endsWith("dbf")) {
          dbf = file;
        } else if (name.endsWith("prj")) {
          prj = file;
        } else if (name.endsWith("shx")) {
          shx = file;
        }
      }

    }

    public ShapefileReader openReader() throws IOException {
      ShapefileReader reader = new ShapefileReader();
      reader.open(shp.getInputStream(), shx.getInputStream(), dbf.getInputStream(), prj.getInputStream());
      return reader;
    }

    public void unzipFolder(MultipartHttpServletRequest request) {
      String zipname = zip.getOriginalFilename();
      try {
        //get the zip file content
        ZipInputStream zis = new ZipInputStream(zip.getInputStream());

        //get the zipped file list entry
        ZipEntry ze;

        while ((ze = zis.getNextEntry()) != null) {
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          byte[] buffer = new byte[1024];
          int count;
          while ((count = zis.read(buffer)) != -1) {
            baos.write(buffer, 0, count);
          }
          String filename = ze.getName();
          byte[] bytes = baos.toByteArray();

          MultipartFile multipartFile = new MockMultipartFile("file",
                  filename, "text/plain", bytes);

          if (filename.endsWith("shp")) {
            shp = multipartFile;
          } else if (filename.endsWith("dbf")) {
            dbf = multipartFile;
          } else if (filename.endsWith("prj")) {
            prj = multipartFile;
          } else if (filename.endsWith("shx")) {
            shx = multipartFile;
          }

          // do something with 'filename' and 'bytes'...
          zis.closeEntry();
        }
        zis.close();

      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

//  @RequestMapping("/uploadFile")
  public void uploadFile(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException, IllegalStateException, ServletException, InvalidShapeFileException, ProjectionException, JSONException {
    try {
      ProjectionInfo lidar = ProjectionInfo.fromEsriString(utmFoot);
      String projectionType = request.getParameter("projectionType");

      ShapeUpload upload = new ShapeUpload(request);
      ShapefileReader reader = upload.openReader();
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
            jcoord.put(c.X);
            jcoord.put(c.Y);
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

//    public void getTimeSeries(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException{
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String wellNumber = request.getParameter("wellNumber");
//        String query = "SELECT MEASUREMENT_DATE, WSE FROM GWLDATA WHERE STATE_WELL_NUMBER = '" + wellNumber + "'";
//        GwlDataFacade  gwlFacade= (GwlDataFacade)appContext.getBean(GwlDataFacade.class.getSimpleName());
//        List<Map> maps = gwlFacade.select(query);
//        String returnString = "Date,WSE\n";
//        for(Map m : maps){
//            if(m.get("wse") == null) {
//                continue;
//            }
//            Double wse = ((BigDecimal)m.get("wse")).doubleValue();
//            Calendar cal = Calendar.getInstance();
//            cal.setTimeInMillis(((Timestamp)m.get("measurementDate")).getTime());
//            returnString += sdf.format(cal.getTime()) + ", " + wse + " \n";
//
//        }
//        response.getWriter().print(returnString);
//
//    }
  public String genShape(String serverPath, String geometry) throws ShapefileException {

    geometry = geometry.substring(2, geometry.length() - 2);

    FeatureSet fs = new FeatureSet();
    Feature f = new Feature();
    fs.setProjection(Projections.Geographic.getWorld().WGS1984.toEsriString());
    Shape shape = new Shape(ShapeType.Polygon);

    Part part = new Part();

    String[] coords = geometry.replace("],[", " - ").split(" - ");
    for (int i = 0; i < coords.length; i++) {
      String[] parts = coords[i].split(",");
      Coord coord = new Coord(Double.valueOf(parts[0]), Double.valueOf(parts[1]));
//            try {
//                coord = Reproject.reprojectCoordinate(coord, Projections.Projected.getWorld().WebMercator, Projections.Geographic.getWorld().WGS1984);
//            } catch (ProjectionException ex) {
//                Exceptions.printStackTrace(ex);
//            }
      part.getCoordinates().add(coord);
    }
    shape.getParts().add(part);
    shape.calculateBounds();
    f.setShape(shape);
    fs.getFeatures().add(f);
    ShapefileWriter writer = new ShapefileWriter();
    String shp = serverPath + "bounds.shp";
    String name = serverPath + "bounds";
    writer.write(fs, shp);
    return name;
  }

//    @RequestMapping("/convertCoord")
//    public void reprojectDatabase(HttpServletRequest request, HttpServletResponse response){
//        String query = "SELECT STATE_WELL_NUMBER, MEASUREMENT_DATE FROM GWLDATA";
//        WellKingsFacade  f= (WellKingsFacade)appContext.getBean(WellKingsFacade.class.getSimpleName());
//        List<WellKings> datas = f.findAll();
//        for(WellKings data : datas){
//            try{
//
//                Coord clickLocation = new Coord(data.getX(), data.getY());
//                Coord coordWGS, webmerc;
//                if(clickLocation.X > 400000){
//                    coordWGS = Reproject.reprojectCoordinate(clickLocation, Projections.Projected.getUtmNad1927().NAD1927UTMZone10N, Projections.Geographic.getWorld().WGS1984);
//                    webmerc = Reproject.reprojectCoordinate(clickLocation, Projections.Projected.getUtmNad1927().NAD1927UTMZone10N, Projections.Projected.getWorld().WebMercator);
//                } else {
//                    coordWGS = Reproject.reprojectCoordinate(clickLocation, Projections.Projected.getUtmNad1927().NAD1927UTMZone11N, Projections.Geographic.getWorld().WGS1984);
//                    webmerc = Reproject.reprojectCoordinate(clickLocation, Projections.Projected.getUtmNad1927().NAD1927UTMZone11N, Projections.Projected.getWorld().WebMercator);
//                }
//                data.setLatitude(coordWGS.Y);
//                data.setLongitude(coordWGS.X);
//                data.setWebmercX(webmerc.X);
//                data.setWebmercY(webmerc.Y);
//                f.edit(data);
//            } catch(Exception ex){
//                Exceptions.printStackTrace(ex);
//            }
//        }
//    }
//  @RequestMapping("/gen")
  public void getCountour(HttpServletRequest request, HttpServletResponse response) throws ProjectionException, ShapefileException, JSONException {
    Integer year = Integer.valueOf(request.getParameter("year").trim());
    String season = request.getParameter("season");
    String geometry = request.getParameter("shape");
    String wells = request.getParameter("selectedWells");

    String selectedWells = "' '";
    if (wells != null) {
      JSONArray jsonArray = new JSONArray(wells);
      for (int i = 0; i < jsonArray.length(); i++) {
        selectedWells += ",'" + jsonArray.getString(i) + "'";
      }
      selectedWells += ",' '";
    }
    String serverPath = request.getServletContext().getRealPath("/");
    String name = null;
    if (geometry == null) {
      return;
    }
    if (!geometry.equals("")) {
      name = genShape(serverPath, geometry);
    }

    geometry = geometry.replace("[[", "").replace("]]", "");
    String[] geoParts = geometry.split(Pattern.quote("],["));
    Double minX = Double.POSITIVE_INFINITY, maxX = Double.NEGATIVE_INFINITY, minY = Double.POSITIVE_INFINITY, maxY = Double.NEGATIVE_INFINITY;
    for (int i = 0; i < geoParts.length; i++) {
      String[] parts = geoParts[i].split(",");
      Double x = Double.parseDouble(parts[0]);
      Double y = Double.parseDouble(parts[1]);
      if (minX > x) {
        minX = x;
      }
      if (maxX < x) {
        maxX = x;
      }
      if (minY > y) {
        minY = y;
      }
      if (maxY < y) {
        maxY = y;
      }
    }

    String monthQuery;
    if (season.equals("Spring")) {
      monthQuery = "AND EXTRACT(month FROM B.MEASUREMENT_DT) <= 6";
    } else {
      monthQuery = "AND EXTRACT(month FROM B.MEASUREMENT_DT) >= 7";
    }

    String query = "SELECT (B.REFERENCE_POINT_ELEVATION-B.REFERENCE_POINT_READING) AS WSE, (abs(A.LONGITUDE)*-1) as LONGITUDE, A.LATITUDE FROM CASGEM_STATION A " //
            + "JOIN ELEVATION_DATA_READING B ON A.CASGEM_STATION_ID = B.CASGEM_STATION_ID "
            + "WHERE "
            + "EXTRACT(year FROM B.MEASUREMENT_DT) = " + year + " "
            + monthQuery
            + " AND (abs(A.LONGITUDE)*-1) BETWEEN " + minX + " AND " + maxX
            + " AND A.LATITUDE BETWEEN " + minY + " AND " + maxY;
//        String query = "SELECT x.WSE, y.LONGITUDE, y.LATITUDE FROM GWLDATA x "
//                + "INNER JOIN WELL_KINGS y ON x.STATE_WELL_NUMBER = y.WELL_NUMBER "
//                + "WHERE "
//                + "EXTRACT(year FROM MEASUREMENT_DATE) = " + year + " "
//                + monthQuery
//                + " AND y.WEBMERC_X BETWEEN " + minX + " AND " + maxX
//                + " AND y.WEBMERC_Y BETWEEN " + minY + " AND " + maxY;
    if (!selectedWells.equals("' '")) {
      query += " AND y.WELL_NUMBER IN (" + selectedWells + ")";
    }
    GwlDataFacade gwlFacade = (GwlDataFacade) appContext.getBean(GwlDataFacade.class.getSimpleName());
    List<Map> maps = gwlFacade.select(query);
    List<GWLTest> data = new ArrayList<>();
    Double minWSE = Double.POSITIVE_INFINITY;
    for (Map m : maps) {

      try {
        GWLTest obj = new GWLTest();
        Coord coord = new Coord(((BigDecimal) m.get("longitude")).doubleValue(), ((BigDecimal) m.get("latitude")).doubleValue());
        obj.setX(coord.X);
        obj.setY(coord.Y);
        if (m.get("wse") == null) {
          continue;
        }
        obj.setWse(((BigDecimal) m.get("wse")).doubleValue());
        if (minWSE > obj.getWse()) {
          minWSE = obj.getWse();
        }
        data.add(obj);
      } catch (Exception ex) {
        Exceptions.printStackTrace(ex);
      }
    }
    try {
      if (data.size() >= 6) {
        ContourManager.getInstance().updateContours(response, data, name);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

//  @RequestMapping("/gen2")
  public void getCountour2(HttpServletRequest request, HttpServletResponse response) throws ProjectionException, ShapefileException, JSONException {
    Integer year = Integer.valueOf(request.getParameter("year").trim());
    String season = request.getParameter("season");
    Integer year2 = Integer.valueOf(request.getParameter("year2").trim());
    String season2 = request.getParameter("season2");
    String geometry = request.getParameter("shape");
    String wells = request.getParameter("selectedWells");

    String selectedWells = "' '";
    if (wells != null) {
      JSONArray jsonArray = new JSONArray(wells);
      for (int i = 0; i < jsonArray.length(); i++) {
        selectedWells += ",'" + jsonArray.getString(i) + "'";
      }
      selectedWells += ",' '";
    }
    String serverPath = request.getServletContext().getRealPath("/");
    String name = null;
    if (geometry == null) {
      return;
    }
    if (!geometry.equals("")) {
      name = genShape(serverPath, geometry);
    }

    geometry = geometry.replace("[[", "").replace("]]", "");
    String[] geoParts = geometry.split(Pattern.quote("],["));
    Double minX = Double.POSITIVE_INFINITY, maxX = Double.NEGATIVE_INFINITY, minY = Double.POSITIVE_INFINITY, maxY = Double.NEGATIVE_INFINITY;
    for (int i = 0; i < geoParts.length; i++) {
      String[] parts = geoParts[i].split(",");
      Double x = Double.parseDouble(parts[0]);
      Double y = Double.parseDouble(parts[1]);
      if (minX > x) {
        minX = x;
      }
      if (maxX < x) {
        maxX = x;
      }
      if (minY > y) {
        minY = y;
      }
      if (maxY < y) {
        maxY = y;
      }
    }

//        String monthQuery;
//        String monthQuery2;
//        if (season.equals("Spring")) {
//            monthQuery = "AND EXTRACT(month FROM d.MEASUREMENT_DATE) <= 6";
//            monthQuery2 = "AND EXTRACT(month FROM e.MEASUREMENT_DATE) <= 6";
//        } else {
//            monthQuery = "AND EXTRACT(month FROM d.MEASUREMENT_DATE) >= 7";
//            monthQuery2 = "AND EXTRACT(month FROM e.MEASUREMENT_DATE) >= 7";
//        }
//
//
//        String query = "SELECT c.WSE, b.LONGITUDE, b.LATITUDE FROM "
//                + "(SELECT (d.WSE - e.WSE) as WSE, d.STATE_WELL_NUMBER "
//                + "FROM GWLDATA d, "
//                + "GWLDATA e "
//                        +"WHERE d.STATE_WELL_NUMBER = e.STATE_WELL_NUMBER AND "
//                        +" EXTRACT(year FROM d.MEASUREMENT_DATE) = " + year + " " + monthQuery
//                        +" AND EXTRACT(year FROM e.MEASUREMENT_DATE) = " + year2 + " " + monthQuery2 + ""
//                + ") c" +
//                        " INNER JOIN WELL_KINGS b on b.WELL_NUMBER = c.STATE_WELL_NUMBER WHERE" +
//                        " b.WEBMERC_X BETWEEN " + minX + " AND " + maxX + " AND b.WEBMERC_Y BETWEEN " + minY + " AND " + maxY ;
//        if(!selectedWells.equals("' '")){
//            query += " AND b.WELL_NUMBER IN (" + selectedWells + ")";
//        }
    String date1 = "";
    String date2 = "";

    if (season.equals("Spring")) {

      date1 += "01/01/" + year;

    } else {
      date1 += "07/01/" + year;

    }

    if (season2.equals("Spring")) {
      date2 += "07/01/" + year2;
    } else {
      date2 += "12/31/" + year2;
    }

    String dates = " B.MEASUREMENT_DT BETWEEN TO_DATE('" + date1 + "','MM/DD/YYYY') AND TO_DATE('" + date2 + "','MM/DD/YYYY') ";

    String query = "SELECT (B.REFERENCE_POINT_ELEVATION-B.REFERENCE_POINT_READING) AS WSE, (abs(A.LONGITUDE)*-1) as LONGITUDE, A.LATITUDE FROM CASGEM_STATION A " //
            + "JOIN ELEVATION_DATA_READING B ON A.CASGEM_STATION_ID = B.CASGEM_STATION_ID "
            + "WHERE "
            + dates
            //        + "EXTRACT(year FROM B.MEASUREMENT_DT) BETWEEN " + year + " AND  "+year2
            //        + monthQuery + monthQuery2
            + " AND (abs(A.LONGITUDE)*-1) BETWEEN " + minX + " AND " + maxX
            + " AND A.LATITUDE BETWEEN " + minY + " AND " + maxY;

    GwlDataFacade gwlFacade = (GwlDataFacade) appContext.getBean(GwlDataFacade.class.getSimpleName());
    List<Map> maps = gwlFacade.select(query);
    List<GWLTest> data = new ArrayList<>();
    Double minWSE = Double.POSITIVE_INFINITY;
    for (Map m : maps) {
      try {
        GWLTest obj = new GWLTest();
        Coord coord = new Coord(((BigDecimal) m.get("longitude")).doubleValue(), ((BigDecimal) m.get("latitude")).doubleValue());
        obj.setX(coord.X);
        obj.setY(coord.Y);
        if (m.get("wse") == null) {
          continue;
        }
        obj.setWse(((BigDecimal) m.get("wse")).doubleValue());
        if (minWSE > obj.getWse()) {
          minWSE = obj.getWse();
        }
        data.add(obj);
      } catch (Exception ex) {
        Exceptions.printStackTrace(ex);
      }
    }
//        if(minWSE < 0) {
//            for(GWLTest obj : data){
//                Double newWSE = obj.getWse() + Math.abs(minWSE);
//                obj.setWse(newWSE);
//            }
//        }
    if (data.size() >= 6) {
      ContourManager.getInstance().updateContours(response, data, name);
    }
  }

//  @RequestMapping("/param")
  public void getParams(HttpServletRequest request, HttpServletResponse response) throws IOException {
    GwlDataFacade gwlFacade = (GwlDataFacade) appContext.getBean(GwlDataFacade.class.getSimpleName());

    String query = "SELECT DISTINCT EXTRACT(year FROM MEASUREMENT_DT) AS year FROM ELEVATION_DATA_READING ORDER BY EXTRACT(year FROM MEASUREMENT_DT)";
    java.util.List<Map> results = gwlFacade.select(query);
    java.util.List<Integer> rtnList = new java.util.ArrayList<Integer>();

    for (java.util.Map m : results) {
      rtnList.add(((java.math.BigDecimal) m.get("year")).intValue());
    }

    response.getWriter().print(rtnList);
  }

//  @RequestMapping("/nearestWells")
  public void findNearestWells(HttpServletRequest request, HttpServletResponse response) throws ProjectionException, IOException, JSONException {
    String wellNumber = request.getParameter("wellNumber");
    Double lat = Double.parseDouble(request.getParameter("lat"));
    Double lng = Double.parseDouble(request.getParameter("lng"));
    Double distance = Double.parseDouble(request.getParameter("distance"));
    Double distance_feet = distance * 5280.0;

    WellKingsFacade wkFacade = (WellKingsFacade) appContext.getBean(WellKingsFacade.class.getSimpleName());

    Coord coord = new Coord(lng, lat);

    //        ProjectionInfo proj;
    Coord clickLocation = new Coord(lng, lat);
    ProjectionInfo latlong = Projections.Geographic.getWorld().WGS1984;
    ProjectionInfo web = Projections.Projected.getWorld().WebMercator;
    Coord webClick = null;

    webClick = Reproject.reprojectCoordinate(clickLocation, latlong, web);

    //Make a bounding box around the centerpoint devided
    double minLat = webClick.Y - (distance_feet * Math.cos(Math.toRadians(lat)));
    double maxLat = webClick.Y + (distance_feet * Math.cos(Math.toRadians(lat)));
    double minLong = webClick.X - (distance_feet * Math.cos(Math.toRadians(lat)));
    double maxLong = webClick.X + (distance_feet * Math.cos(Math.toRadians(lat)));

    Coord lowerLeft = new Coord(minLong, minLat);
    Coord upperRight = new Coord(maxLong, maxLat);

    lowerLeft = Reproject.reprojectCoordinate(lowerLeft, web, latlong);
    upperRight = Reproject.reprojectCoordinate(upperRight, web, latlong);

    //select
    String query = "SELECT CASGEM_STATION_ID as id ,CASGEM_STATE_WELL_NBR, (abs(LONGITUDE)*-1) as longitude, LATITUDE FROM CASGEM_STATION WHERE (abs(LONGITUDE)*-1) BETWEEN " + lowerLeft.X + " AND " + upperRight.X + " AND LATITUDE BETWEEN " + lowerLeft.Y + " AND " + upperRight.Y;
    List<Map> maps = wkFacade.select(query);
    JSONArray results = new JSONArray();
    try {
      for (Map m : maps) {
        double resultlat = Double.parseDouble(m.get("latitude").toString());
        double resultlng = Math.abs(Double.parseDouble(m.get("longitude").toString())) * -1;
        Coord wgs84 = new Coord(resultlng, resultlat);

        Coord webCoord = Reproject.reprojectCoordinate(wgs84, latlong, web);
        double distanceFromCenter = Double.MAX_VALUE;
        if (webClick != null) {
          distanceFromCenter = webClick.distance(webCoord);
        }

        double wideDistance = distance_feet / Math.cos(Math.toRadians(lat));
        if (distanceFromCenter <= wideDistance * 0.3048) {
          JSONObject obj = new JSONObject();
          obj.put("wellname", m.get("casgemStateWellNbr"));
          obj.put("id", m.get("id"));
          results.put(obj);
        }

      }
    } catch (Exception ex) {
      Exceptions.printStackTrace(ex);
    }

    response.getWriter().print(results);
  }

//  @RequestMapping("uploadData")
  public void uploadData(MultipartHttpServletRequest request, HttpServletResponse response) {
    Iterator<String> itr = request.getFileNames();
    String dataType = request.getParameter("dataType");
    String dataFormat = request.getParameter("dataFormat");
    String filename = null;
    while (itr.hasNext()) {
      filename = itr.next();
      MultiValueMap<String, MultipartFile> fileMap = request.getMultiFileMap();
      List<MultipartFile> files = fileMap.get(filename);
      for (int i = 0; i < files.size(); i++) {
        MultipartFile filePath = files.get(i);
        insert(filePath);
      }

    }
  }

//  @RequestMapping("downloadWellCSV")
  public void downloadWellCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
      String wellids = request.getParameter("wellids");
      JSONArray ids = new JSONArray(wellids);
      String query = "SELECT DISTINCT * FROM CASGEM_STATION A "
              + " LEFT JOIN MASTER_SITE B ON A.MASTER_SITE_ID = B.MASTER_SITE_ID "
              + " LEFT JOIN (SELECT MAX(PERFORATION_BOTTOM_MSRMNT) AS LOW,CASGEM_STATION_ID FROM CASGEM_STATION_PERFORATION GROUP BY CASGEM_STATION_ID  ) X ON X.CASGEM_STATION_ID = A.CASGEM_STATION_ID "
              + " LEFT JOIN (SELECT MAX(PERFORATION_TOP_MSRMNT) AS HIGH,CASGEM_STATION_ID FROM CASGEM_STATION_PERFORATION GROUP BY CASGEM_STATION_ID  ) Y ON Y.CASGEM_STATION_ID = A.CASGEM_STATION_ID "
              + " LEFT JOIN (SELECT MAX(MEASUREMENT_DT) AS LASTMEASUREDATE,CASGEM_STATION_ID FROM ELEVATION_DATA_READING GROUP BY CASGEM_STATION_ID  ) Z ON Z.CASGEM_STATION_ID = A.CASGEM_STATION_ID"
              + " LEFT JOIN (SELECT MIN(MEASUREMENT_DT) AS FIRSTMEASUREDATE,CASGEM_STATION_ID FROM ELEVATION_DATA_READING GROUP BY CASGEM_STATION_ID  ) W ON W.CASGEM_STATION_ID = A.CASGEM_STATION_ID"
              + " WHERE ";
      for (int i = 0; i < ids.length(); i++) {

        if (i == ids.length() - 1) {
          query += "  A.CASGEM_STATION_ID = " + ids.get(i);
        } else {
          query += "  A.CASGEM_STATION_ID = " + ids.get(i) + " OR";
        }
      }
      query += " ORDER BY A.CASGEM_STATE_WELL_NBR ";
      JSONArray results = new JSONArray();
      GwlDataFacade gwlFacade = (GwlDataFacade) appContext.getBean(GwlDataFacade.class.getSimpleName());
      File file = new File(request.getServletContext().getRealPath("/") + File.separator + "welldata.csv");
      List<Map> maps = gwlFacade.select(query);
      for (Map m : maps) {
        try {
          JSONObject well = new JSONObject();
          Iterator entries = m.entrySet().iterator();
          while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            if (entry.getValue() == null) {
              well.put((String) entry.getKey(), "-");
            } else {
              if ("lastmeasuredate".equals((String) entry.getKey()) || "firstmeasuredate".equals((String) entry.getKey())) {
                well.put((String) entry.getKey(), convertDate(entry.getValue().toString()));
              } else {
                well.put((String) entry.getKey(), entry.getValue());
              }

            }

          }
          results.put(well);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }

      try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
        if (results.length() > 0) {

          JSONObject headers = results.getJSONObject(0);

          Iterator<String> columns = headers.keys();
          while (columns.hasNext()) {
            String key = columns.next();
            if (columns.hasNext()) {
              bw.write(key + ",");
            } else {
              bw.write(key);
            }
          }
          bw.newLine();

          for (int i = 0; i < results.length(); i++) {
            JSONObject row = results.getJSONObject(i);
            Iterator<String> keys = row.keys();
            while (keys.hasNext()) {
              String key = keys.next();
              String value = null;
              try {
                value = row.getString(key);
                if (keys.hasNext()) {
                  bw.write(value + ",");
                } else {
                  bw.write(value);
                }
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
            bw.newLine();
          }
        }
      } catch (Exception ex) {
        Exceptions.printStackTrace(ex);
      }
      response.getWriter().write(file.getAbsolutePath());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

//  @RequestMapping("downloadWellCSV2")
  public void downloadWellCSV2(HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
      String wellids = request.getParameter("wellids");
      String query = "SELECT DISTINCT * FROM NCAL_WELLS A";
      if (wellids != null && wellids.length() > 0 && !"null".equals(wellids)) {
        JSONArray ids = new JSONArray(wellids);
        if (ids.length() > 0) {
          query += " WHERE";
          for (int i = 0; i < ids.length(); i++) {
            if (i == ids.length() - 1) {
              query += " A.CASGEM_WELL_ID=" + ids.get(i);
            } else {
              query += " A.CASGEM_WELL_ID=" + ids.get(i) + " OR";
            }
          }
        }
      }
      query += " ORDER BY A.CASGEM_WELL_ID";
      LinkedHashSet<String> keys = getNcalKeys();
      JSONArray results = selectNcalWellsAsJsonArray(query, keys);

      response.setContentType("text/csv");
      response.setHeader("Content-Disposition", "attachment; filename=welldata.csv");
      try (PrintWriter pw = response.getWriter()) {
        if (results.length() > 0) {
          Iterator<String> columns = keys.iterator();
          while (columns.hasNext()) {
            String key = columns.next();
            if (columns.hasNext()) {
              pw.print(key + ",");
            } else {
              pw.println(key);
            }
          }
          //                pw.newLine();

          for (int i = 0; i < results.length(); i++) {
            JSONObject row = results.getJSONObject(i);
            columns = keys.iterator();
            while (columns.hasNext()) {
              String key = columns.next();
              String value = null;
              try {
                value = row.getString(key);
                if (columns.hasNext()) {
                  pw.print(value + ",");
                } else {
                  pw.println(value);
                }
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
            //                    pw.newLine();
          }
        }
      } catch (Exception ex) {
        Exceptions.printStackTrace(ex);
      }
//        response.getWriter().write(file.getAbsolutePath());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // made for webgis well search
//  @RequestMapping("downloadWellShapefile")
  public void downloadWellShapefile(HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
      String wellids = request.getParameter("wellids");
      String query = "SELECT DISTINCT * FROM NCAL_WELLS A";
      if (wellids != null && wellids.length() > 0 && !"null".equals(wellids)) {
        JSONArray ids = new JSONArray(wellids);
        if (ids.length() > 0) {
          query += " WHERE";
          for (int i = 0; i < ids.length(); i++) {
            if (i == ids.length() - 1) {
              query += " A.CASGEM_WELL_ID=" + ids.get(i);
            } else {
              query += " A.CASGEM_WELL_ID=" + ids.get(i) + " OR";
            }
          }
        }
      }
      query += " ORDER BY A.CASGEM_WELL_ID";
      LinkedHashSet<String> keys = getNcalKeys();
      JSONArray results = selectNcalWellsAsJsonArray(query, keys);

      FeatureSet fs = new FeatureSet();

      fs.setProjection(Projections.Geographic.getWorld().WGS1984.toEsriString());
      Feature feature;
      Shape shape;
      Part part;
      Field field;

      if (results.length() > 0) {
        JSONObject row;
        for (String key : keys) {
          field = new Field(key, FieldType.Character, 50);
          fs.getFields().add(field);
        }
        double longitude, latitude;
        for (int y = 0; y < results.length(); y++) {
          feature = new Feature();
          shape = new Shape(ShapeType.Point);
          part = new Part();
          try {
            row = results.getJSONObject(y);

            longitude = -Math.abs(row.optDouble("longitude", 0));
            latitude = row.optDouble("latitude", 0);
            System.out.println(longitude + ", " + latitude);
            if (longitude == 0 || latitude == 0) {
              continue;
            }
            part.getCoordinates().add(new Coord(longitude, latitude));
            shape.getParts().add(part);
            shape.calculateBounds();
            feature.setShape(shape);

            for (String key : keys) {
              feature.getAttributes().put(key, row.getString(key));
            }
            fs.getFeatures().add(feature);
          } catch (Exception ex) {
            ex.printStackTrace(System.err);
          }
        }
      }

      response.setContentType("application/octet-stream");
      response.setHeader("Content-Disposition", "attachment; filename=welldata.zip");
      try (OutputStream os = response.getOutputStream()) {
        ShapefileWriter writer = new ShapefileWriter();
        writer.write(fs, os);
      } catch (Exception ex) {
        Exceptions.printStackTrace(ex);
      }
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }
  }

  // made for webgis well search
//  @RequestMapping("getWellJson")
  public void getWellJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
      String wellids = request.getParameter("wellids");
      String query = "SELECT DISTINCT * FROM NCAL_WELLS A";
      if (wellids != null && wellids.length() > 0 && !"null".equals(wellids)) {
        JSONArray ids = new JSONArray(wellids);
        if (ids.length() > 0) {
          query += " WHERE";
          for (int i = 0; i < ids.length(); i++) {
            if (i == ids.length() - 1) {
              query += " A.CASGEM_WELL_ID=" + ids.get(i);
            } else {
              query += " A.CASGEM_WELL_ID=" + ids.get(i) + " OR";
            }
          }
        }
      }
      query += " ORDER BY A.CASGEM_WELL_ID";
      LinkedHashSet<String> keys = getNcalKeys("ncalwellsid", "meid");
      JSONArray results = selectNcalWellsAsJsonArray(query, keys);

      JSONObject json = new JSONObject();
      JSONArray headers = new JSONArray(), header = new JSONArray();
      JSONArray data = new JSONArray(), datum;

      if (results.length() > 0) {
        for (String key : keys) {
          header.put(key);
        }
        headers.put(header);

        JSONObject row;
        for (int y = 0; y < results.length(); y++) {
          try {
            row = results.getJSONObject(y);
            datum = new JSONArray();
            for (String key : keys) {
              datum.put(row.getString(key));
            }
            data.put(datum);
          } catch (Exception ex) {
            ex.printStackTrace(System.err);
          }
        }
      }

      json.put("headers", headers);
      json.put("data", data);

      response.setContentType("application/json");
//            response.setHeader("Content-Disposition", "attachment; filename=welldata.zip");
      try (PrintWriter pw = response.getWriter()) {
        pw.write(json.toString());
      } catch (Exception ex) {
        Exceptions.printStackTrace(ex);
      }
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }
  }

//  @RequestMapping("downloadTimeSeriesCSV")
  public void downloadTimeSeriesCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {

    String wellIDs = request.getParameter("wellids");
    wellIDs = wellIDs.replace('[', '(');
    wellIDs = wellIDs.replace(']', ')');
    wellIDs = wellIDs.replace('"', ' ');
    String query = "SELECT A.*,(A.REFERENCE_POINT_ELEVATION-A.REFERENCE_POINT_READING) AS WSE FROM ELEVATION_DATA_READING A WHERE A.CASGEM_STATION_ID IN " + wellIDs + " ORDER BY A.CASGEM_STATION_ID,A.MEASUREMENT_DT";
    JSONArray results = new JSONArray();
    GwlDataFacade gwlFacade = (GwlDataFacade) appContext.getBean(GwlDataFacade.class.getSimpleName());
    List<Map> maps = gwlFacade.select(query);
    File file = new File(request.getServletContext().getRealPath("/") + File.separator + "elevationsdata.csv");

    for (Map m : maps) {
      try {
        JSONObject well = new JSONObject();
        Iterator entries = m.entrySet().iterator();
        while (entries.hasNext()) {
          Map.Entry entry = (Map.Entry) entries.next();
          if (entry.getValue() == null) {
            well.put((String) entry.getKey(), "");
          } else {
            well.put((String) entry.getKey(), entry.getValue());
          }

        }
        results.put(well);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
      if (results.length() > 0) {

        JSONObject headers = results.getJSONObject(0);

        Iterator<String> columns = headers.keys();
        while (columns.hasNext()) {
          String key = columns.next();
          if (columns.hasNext()) {
            bw.write(key + ",");
          } else {
            bw.write(key);
          }
        }
        bw.newLine();

        for (int i = 0; i < results.length(); i++) {
          JSONObject row = results.getJSONObject(i);
          Iterator<String> keys = row.keys();
          while (keys.hasNext()) {
            String key = keys.next();
            String value = null;
            try {
              value = row.getString(key);
              if (keys.hasNext()) {
                bw.write(value + ",");
              } else {
                bw.write(value);
              }
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
          bw.newLine();
        }
      }
    } catch (Exception ex) {
      Exceptions.printStackTrace(ex);
    }
    response.getWriter().write(file.getAbsolutePath());

  }

//  @RequestMapping("downloadTimeSeriesCSV2")
  public void downloadTimeSeriesCSV2(HttpServletRequest request, HttpServletResponse response) throws IOException {

    String wellIDs = request.getParameter("wellids");
    wellIDs = wellIDs.replace('[', '(');
    wellIDs = wellIDs.replace(']', ')');
    wellIDs = wellIDs.replace('"', ' ');
    String query = "SELECT A.*,(A.REFERENCE_POINT_ELEVATION-A.REFERENCE_POINT_READING) AS WSE FROM ELEVATION_DATA_READING A WHERE A.CASGEM_STATION_ID IN " + wellIDs + " ORDER BY A.CASGEM_STATION_ID,A.MEASUREMENT_DT";
    JSONArray results = new JSONArray();
    GwlDataFacade gwlFacade = (GwlDataFacade) appContext.getBean(GwlDataFacade.class.getSimpleName());
    List<Map> maps = gwlFacade.select(query);
//        File file = new File(request.getServletContext().getRealPath("/") + File.separator + "elevationsdata.csv");

    for (Map m : maps) {
      try {
        JSONObject well = new JSONObject();
        Iterator entries = m.entrySet().iterator();
        while (entries.hasNext()) {
          Map.Entry entry = (Map.Entry) entries.next();
          if (entry.getValue() == null) {
            well.put((String) entry.getKey(), "");
          } else {
            well.put((String) entry.getKey(), entry.getValue());
          }

        }
        results.put(well);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }

    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=elevationsdata.csv");
    try (PrintWriter pw = response.getWriter()) {
      if (results.length() > 0) {

        JSONObject headers = results.getJSONObject(0);

        Iterator<String> columns = headers.keys();
        while (columns.hasNext()) {
          String key = columns.next();
          if (columns.hasNext()) {
            pw.print(key + ",");
          } else {
            pw.println(key);
          }
        }
//                pw.newLine();

        for (int i = 0; i < results.length(); i++) {
          JSONObject row = results.getJSONObject(i);
          Iterator<String> keys = row.keys();
          while (keys.hasNext()) {
            String key = keys.next();
            String value = null;
            try {
              value = row.getString(key);
              if (keys.hasNext()) {
                pw.print(value + ",");
              } else {
                pw.println(value);
              }
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
//                    pw.newLine();
        }
      }
    } catch (Exception ex) {
      Exceptions.printStackTrace(ex);
    }
//        response.getWriter().write(file.getAbsolutePath());

  }

//  @RequestMapping("downloadData")
  public void downloadData(HttpServletRequest request, HttpServletResponse response) throws IOException {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    String wellIDs = request.getParameter("names");
    String range = request.getParameter("range");
    String format = request.getParameter("format");
    String startDate = request.getParameter("startDate");
    String endDate = request.getParameter("endDate");
    List<String> wells = Arrays.asList(wellIDs.split(","));
    String query = "";
    for (String well : wells) {
      query += "'" + well + "',";
    }
    query = query.substring(0, query.length() - 1);
    query = "SELECT STATE_WELL_NUMBER, MEASUREMENT_DATE, WSE FROM GWLDATA WHERE STATE_WELL_NUMBER IN (" + query + ")";
    if (!range.equals("all")) {
      query += " AND MEASUREMENT_DATE > TO_DATE('" + startDate + "','YYYY-MM-DD') AND MEASUREMENT_DATE < TO_DATE('" + endDate + "','YYYY-MM-DD')";
    }
    GwlDataFacade gwlFacade = (GwlDataFacade) appContext.getBean(GwlDataFacade.class.getSimpleName());
    List<Map> maps = gwlFacade.select(query);
    File file = new File(request.getServletContext().getRealPath("/") + File.separator + "data.csv");
    List<Gwldata> data = new ArrayList<>();
    for (Map m : maps) {
      try {
        String num = m.get("stateWellNumber").toString();
        Double wse = ((BigDecimal) m.get("wse")).doubleValue();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(((Timestamp) m.get("measurementDate")).getTime());
        Gwldata obj = new Gwldata(num, cal.getTime());
        obj.setWse(wse);
        data.add(obj);
      } catch (Exception ex) {
        Exceptions.printStackTrace(ex);
      }
    }
    Collections.sort(data, new Comparator<Gwldata>() {

      @Override
      public int compare(Gwldata o1, Gwldata o2) {
        int i = o1.getGwldataPK().getStateWellNumber().compareTo(o2.getGwldataPK().getStateWellNumber());
        if (i != 0) {
          return i;
        }
        return o1.getGwldataPK().getMeasurementDate().compareTo(o2.getGwldataPK().getMeasurementDate());
      }
    });
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
      bw.write("State_Well_Number, Measurement_Date, WSE");
      bw.newLine();
      for (Gwldata obj : data) {
        bw.write(obj.getGwldataPK().getStateWellNumber() + "," + sdf.format(obj.getGwldataPK().getMeasurementDate()) + "," + obj.getWse());
        bw.newLine();
      }
    } catch (Exception ex) {
      Exceptions.printStackTrace(ex);
    }
    response.getWriter().write(file.getAbsolutePath());
  }

//  @RequestMapping("/addElevationData")
  public void addElevationData(@RequestParam(value = "id", required = true) Integer stationId, @RequestParam(value = "measuredate", required = true) String measuredate, @RequestParam(value = "depth", required = true) Double depth, @RequestParam(value = "measurecomment", required = false) String measurecomment, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
    JSONObject jsonResponse = new JSONObject();
    try {
      ElevationDataReadingFacade elvationFacade = (ElevationDataReadingFacade) appContext.getBean(ElevationDataReadingFacade.class.getSimpleName());
      ElevationDataReading reading = new ElevationDataReading();
      String query = "select * from (select * from ELEVATION_DATA_READING WHERE CASGEM_STATION_ID = " + stationId + " ORDER BY MEASUREMENT_DT DESC) WHERE ROWNUM < 2";
      List<Map> maps = elvationFacade.select(query);
      for (Map m : maps) {
        try {
          reading.setReferencePointElevation(((BigDecimal) m.get("referencePointElevation")).doubleValue());
          reading.setGroundSurfaceElevation(((BigDecimal) m.get("groundSurfaceElevation")).doubleValue());
          reading.setWaterSurfaceReading(((BigDecimal) m.get("waterSurfaceReading")).doubleValue());
        } catch (Exception ex) {
          Exceptions.printStackTrace(ex);
        }
      }

      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
      reading.setCasgemStationId(stationId);
      reading.setMeasurementDt(sdf.parse(measuredate));
      reading.setReferencePointReading(depth);
      reading.setComments(measurecomment);
      elvationFacade.create(reading);
      jsonResponse.put("success", true);
    } catch (Exception ex) {
      Exceptions.printStackTrace(ex);
      jsonResponse.put("success", false).put("error", ex.getMessage());
    } finally {
      response.setContentType("application/json");
      response.getWriter().print(jsonResponse.toString());
    }
  }

  private void insert(MultipartFile mfile) {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    try {
      List<Gwldata> dataList = new ArrayList<>();
            //read the file
      //skips the header
      BufferedReader br = new BufferedReader(new InputStreamReader(mfile.getInputStream()));
      String line = br.readLine();
      String[] parts = line.split(",");
      HashMap<Integer, String> valueMap = new HashMap<>();
      for (int i = 0; i < parts.length; i++) {
        valueMap.put(i, parts[i].toLowerCase());
      }
      while ((line = br.readLine()) != null) {
        parts = line.split(",");
        if (parts.length < 2) {
          continue;
        }
//                if(parts.length != 11){
//                    continue;
//                }
        Gwldata data = new Gwldata(parts[0], sdf.parse(parts[1]));
        for (int i = 2; i < parts.length; i++) {
          if (valueMap.get(i).contains("wse")) {
            data.setWse(parseDouble(parts[i]));
          } else if (valueMap.get(i).contains("rpelevation")) {
            data.setRpElevation(parseDouble(parts[i]));
          } else if (valueMap.get(i).contains("gselevation")) {
            data.setGsElevation(parseDouble(parts[i]));
          } else if (valueMap.get(i).contains("rpws")) {
            data.setRpws(parseDouble(parts[i]));
          } else if (valueMap.get(i).contains("gsws")) {
            data.setGsws(parseDouble(parts[i]));
          } else if (valueMap.get(i).contains("qmcode")) {
            data.setQmCode(parts[i]);
          } else if (valueMap.get(i).contains("nmcode")) {
            data.setNmCode(parts[i]);
          } else if (valueMap.get(i).contains("agency")) {
            data.setAgency(parseInt(parts[i]));
          } else if (valueMap.get(i).contains("comment")) {
            data.setComments(parts[i].replace("\"", ""));
          }
        }
        dataList.add(data);
      }
      br.close();
      //inserts into oracle db
      doInsertGWL(dataList);

    } catch (IOException | ParseException ex) {
      Exceptions.printStackTrace(ex);
    }
  }

  public void doInsertGWL(List<Gwldata> list) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    GwlDataFacade gwlFacade = (GwlDataFacade) appContext.getBean(GwlDataFacade.class.getSimpleName());
    for (Gwldata data : list) {
      Gwldata obj = gwlFacade.find(data.getGwldataPK());
      if (obj != null) {
        obj.setWse(data.getWse());
        gwlFacade.edit(obj);
      } else {
        gwlFacade.create(data);
      }
    }
  }

  public Integer parseInt(Object obj) {
    Integer num = null;
    if (obj instanceof Long) {
      Long l = (Long) obj;
      num = l.intValue();
    } else if (obj instanceof Double) {
      Double l = (Double) obj;
      num = l.intValue();
    } else if (obj instanceof Float) {
      Float l = (Float) obj;
      num = l.intValue();
    } else if (obj instanceof String) {
      num = Integer.parseInt(obj.toString());
    }
    return num;
  }

  public Double parseDouble(String string) {
    if (string.isEmpty()) {
      return null;
    } else {
      return Double.parseDouble(string);
    }
  }

  public String convertDate(String dateToConvert) throws IOException {
    String newdate = "-";
    try {
      DateFormat formatter = null;
      Date convertedDate = null;
      String date = dateToConvert;
      formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
      convertedDate = (Date) formatter.parse(date);
      newdate = new SimpleDateFormat("MM/dd/yyyy").format(convertedDate);
    } catch (Exception e) {
      Exceptions.printStackTrace(e);
    }

    return newdate;
  }

}
