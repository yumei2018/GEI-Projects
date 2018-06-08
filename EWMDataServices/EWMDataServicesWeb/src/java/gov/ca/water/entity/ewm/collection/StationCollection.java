package gov.ca.water.entity.ewm.collection;

import com.opencsv.CSVWriter;
import entity.core.EntityCollection;
import entity.core.delegate.QueryDelegate;
import entity.core.filter.ConditionFilter;
import entity.core.filter.ConditionGroup;
import entity.core.util.EntityUtil;
import entity.core.util.ExceptionUtil;
import gov.ca.water.entity.Wrapper;
import gov.ca.water.entity.ewm.Basin;
import gov.ca.water.entity.ewm.BasinRegion;
import gov.ca.water.entity.ewm.County;
import gov.ca.water.entity.ewm.ElevationDataReading;
import gov.ca.water.entity.ewm.MasterSite;
import gov.ca.water.entity.ewm.MeasurementAccuracyType;
import gov.ca.water.entity.ewm.MeasurementMethodType;
import gov.ca.water.entity.ewm.Organization;
import gov.ca.water.entity.ewm.Station;
import gov.ca.water.entity.ewm.StationPerforation;
import gov.ca.water.entity.ewm.StationUseType;
import gov.ca.water.entity.ewm.WellCompletionType;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.FieldType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.delegate.IntersectDelegate;
import gov.ca.water.shapelite.io.ShapefileWriter;
import gov.ca.water.shapelite.io.json.EsriJsonUtils;
import gov.ca.water.shapelite.projection.ProjectionException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static gov.ca.water.entity.ewm.collection.StationCollection.stationDownloadFields;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.projection.Projections;
import java.util.HashSet;
import java.util.zip.ZipEntry;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Segment;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Reproject;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;


/**
 *
 * @author clay
 */
public class StationCollection extends EntityCollection<Station> {
  
  //<editor-fold defaultstate="collapsed" desc="getFiledsMap">
  public static Map<String, Wrapper> getFieldsMap(){
    Map<String, Wrapper> map = new HashMap<>();
    map.put("SITE_CODE", new Wrapper(EntityUtil.getTableName(MasterSite.class), "SITE_CODE"));
    map.put("WELL_NAME", new Wrapper(EntityUtil.getTableName(Station.class), "LOCAL_WELL_DESIGNATION"));
    map.put("SWN", new Wrapper(EntityUtil.getTableName(Station.class), "EWM_STATE_WELL_NBR"));
    map.put("STN_ID", new Wrapper(EntityUtil.getTableName(Station.class), "EWM_STATION_ID"));
    map.put("WCR_NO", new Wrapper(EntityUtil.getTableName(Station.class), "COMPLETION_RPT_NBR"));
//    map.put("LATITUDE", new Wrapper(EntityUtil.getTableName(Station.class), "LATITUDE"));
//    map.put("LONGITUDE", new Wrapper(EntityUtil.getTableName(Station.class), "LONGITUDE"));
//    map.put("STN_ORG_ID", new Wrapper(EntityUtil.getTableName(Station.class), "PRIMARY_WELL_ORG_ID"));
    map.put("STN_ORG_NAME", new Wrapper(EntityUtil.getTableName(Organization.class), "ORG_NAME"));
//    map.put("LOC_DESC", new Wrapper(EntityUtil.getTableName(Station.class), "WELL_LOCATION_DESC"));
//    map.put("WELL_USE", new Wrapper(EntityUtil.getTableName(StationUseType.class), "EWM_STATION_USE_DESC"));
//    map.put("WELL_TYPE", new Wrapper(EntityUtil.getTableName(WellCompletionType.class), "EWM_WELL_COMPLETION_DESC"));
//    map.put("WELL_DEPTH", new Wrapper(EntityUtil.getTableName(Station.class), "TOTAL_DEPTH_FT"));
//    map.put("TOP_PRF", new Wrapper(EntityUtil.getTableName(StationPerforation.class), "PERFORATION_TOP_MSRMNT"));
//    map.put("BOT_PRF", new Wrapper(EntityUtil.getTableName(StationPerforation.class), "PERFORATION_BOTTOM_MSRMNT"));
//    map.put("LAST_GSE", new Wrapper(EntityUtil.getTableName(Station.class), "LATEST_GS_ELEVATION"));
//    map.put("LAST_RPE", new Wrapper(EntityUtil.getTableName(Station.class), "LATEST_RP_ELEVATION"));
//    map.put("RP_DESC", new Wrapper(EntityUtil.getTableName(Station.class), "RP_DESC"));
//    map.put("STN_CMT", new Wrapper(EntityUtil.getTableName(Station.class), "ADDL_COMMENTS"));
//    map.put("STN_MOD_DATE", new Wrapper(EntityUtil.getTableName(Station.class), "MODIFIED_DATE"));
//    map.put("STN_MOD_USER", new Wrapper(EntityUtil.getTableName(Station.class), "MODIFIED_USER"));
//    map.put("MSMT_DATE", new Wrapper(EntityUtil.getTableName(ElevationDataReading.class), "MEASUREMENT_DT"));
//    map.put("WSE", new Wrapper(EntityUtil.getTableName(.class), ""));
//    map.put("RPE_WSE", new Wrapper(EntityUtil.getTableName(.class), ""));
//    map.put("GSE_WSE", new Wrapper(EntityUtil.getTableName(.class), ""));
//    map.put("WLM_RPE", new Wrapper(EntityUtil.getTableName(ElevationDataReading.class), "REFERENCE_POINT_ELEVATION"));
//    map.put("WLM_GSE ", new Wrapper(EntityUtil.getTableName(ElevationDataReading.class), "GROUND_SURFACE_ELEVATION"));
//    map.put("RDNG_RP", new Wrapper(EntityUtil.getTableName(ElevationDataReading.class), "REFERENCE_POINT_READING"));
//    map.put("RDNG_WS", new Wrapper(EntityUtil.getTableName(ElevationDataReading.class), "WATER_SURFACE_READING"));
//    map.put("WLM_ORG_ID", new Wrapper(EntityUtil.getTableName(ElevationDataReading.class), "ORG_ID"));
//    map.put("WLM_ORG_NAME", new Wrapper(EntityUtil.getTableName(Organization.class), "ORG_NAME"));
//    map.put("WLM_DESC", new Wrapper(EntityUtil.getTableName(ElevMeasureMethodTyp.class), "EWM_ELEV_MEASURE_METHOD_DESC"));
//    map.put("WLM_ACC_CODE", new Wrapper(EntityUtil.getTableName(ElevationAccuracyType.class), "EWM_ELEVATION_ACCURACY_CD"));
//    map.put("WLM_ACC_DESC", new Wrapper(EntityUtil.getTableName(ElevationAccuracyType.class), "EWM_ELEVATION_ACCURACY_DESC"));
//    map.put("WLM_QA_CODE", new Wrapper(EntityUtil.getTableName(MeasurementIssueType.class), "EWM_MEASURE_ISSUE_TYPE_CODE"));
//    map.put("WLM_QA_DESC", new Wrapper(EntityUtil.getTableName(MeasurementIssueType.class), "EWM_MEASURE_ISSUE_TYPE_DESC"));
//    map.put("WLM_METHOD", new Wrapper(EntityUtil.getTableName(MeasurementMethodType.class), "EWM_MEASUREMENT_METHOD_DESC"));
//    map.put("WLM_ACC", new Wrapper(EntityUtil.getTableName(MeasurementAccuracyType.class), "EWM_MEASUREMENT_ACCURACY_DESC"));
//    map.put("MSMT_CMT", new Wrapper(EntityUtil.getTableName(ElevationDataReading.class), "COMMENTS"));
//    map.put("WLM_MOD_DATE", new Wrapper(EntityUtil.getTableName(ElevationDataReading.class), "MODIFIED_DATE"));
//    map.put("WLM_MOD_USER", new Wrapper(EntityUtil.getTableName(ElevationDataReading.class), "MODIFIED_USER"));
//    map.put("COUNTY", new Wrapper(EntityUtil.getTableName(.class), ""));
//    map.put("HR", new Wrapper(EntityUtil.getTableName(.class), ""));
//    map.put("BASIN_CODE", new Wrapper(EntityUtil.getTableName(Basin.class), "EWM_BASIN_CD"));
//    map.put("BASIN_NAME", new Wrapper(EntityUtil.getTableName(Basin.class), "EWM_BASIN_DESC"));
//    map.put("BASIN_RO", new Wrapper(EntityUtil.getTableName(Basin.class), "REG_OFFICE_ID"));

  
//    "SITE_CODE",
//    "WELL_NAME",
//    "SWN",
//    "STN_ID",
//    "WCR_NO",
//    "STN_ORG_ID",
//    "STN_ORG_NAME",
//    "LOC_DESC",
//    "WELL_USE",
//    "WELL_TYPE",
//    "STN_MOD_USER",
//    "WLM_ORG_NAME",
//    "WLM_DESC",
//    "WLM_ACC_DESC",
//    "WLM_QA_DESC",
//    "WLM_METHOD",
//    "WLM_ACC",
//    "WLM_MOD_USER"
    return map;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="searchableFields">
  public final List<String> searchableFields = Arrays.asList(
    "SITE_CODE",
    "WELL_NAME",
    "SWN",
    "STN_ID",
    "WCR_NO",
    "STN_ORG_ID",
    "STN_ORG_NAME",
    "LOC_DESC",
    "WELL_USE",
    "WELL_TYPE",
    "STN_MOD_USER",
    "WLM_ORG_NAME",
    "WLM_DESC",
    "WLM_ACC_DESC",
    "WLM_QA_DESC",
    "WLM_METHOD",
    "WLM_ACC",
    "WLM_MOD_USER"
  );
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="getMinDepth">
  public static Integer getMinDepth() {
    Integer result = null;
    Integer[] container = new Integer[1];
    StationCollection stations = new StationCollection();
    stations.runQuery("select min(total_depth_ft) depth from " + EntityUtil.getTableName(Station.class), null, new QueryDelegate(container) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        Integer[] container = this.getListener();
        rs.next();
        container[0] = rs.getInt("depth");
      }
    });
    result = container[0];
    return result;
  }
//</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="getMaxDepth">
  public static Integer getMaxDepth() {
    Integer result = null;
    Integer[] container = new Integer[1];
    StationCollection stations = new StationCollection();
    stations.runQuery("select max(total_depth_ft) depth from " + EntityUtil.getTableName(Station.class), null, new QueryDelegate(container) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        Integer[] container = this.getListener();
        rs.next();
        container[0] = rs.getInt("depth");
      }
    });
    result = container[0];
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="expanded" desc="Private Properties">
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public StationCollection() {
    super(Station.class);
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Get Extent, Raw Query - getExtentRaw()">
  public JSONArray getExtentRaw(Double xmin, Double ymin, Double xmax, Double ymax) {
    JSONArray result = new JSONArray(); 
    String query = "SELECT S.LATITUDE, S.LONGITUDE, "
        + "S.EWM_STATION_ID, "
        + "MS.SITE_CODE, "
        + "EWM_STATE_WELL_NBR, "
        + "TOTAL_DEPTH_FT, "
        + "COMPLETION_RPT_NBR, "
        + "S.LOCAL_WELL_DESIGNATION, "
        + "LATEST_RP_ELEVATION, LATEST_GS_ELEVATION, "
        + "S.EWM_BASIN_ID, EWM_BASIN_CD, EWM_BASIN_DESC, "
        + "EWM_STATION_USE_DESC "
        + "FROM " + EntityUtil.getTableName(Station.class) + " S "
        + "LEFT JOIN " + EntityUtil.getTableName(StationUseType.class) + " SUT "
        + "ON SUT.EWM_STATION_USE_ID = S.EWM_STATION_USE_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(Basin.class) + " B "
        + "ON B.EWM_BASIN_ID = S.EWM_BASIN_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(MasterSite.class) + " MS "
        + "ON S.EWM_MASTER_SITE_ID = MS.EWM_MASTER_SITE_ID "
        + "WHERE S.LONGITUDE >= ? "
        + "AND S.LONGITUDE <= ? "
        + "AND S.LATITUDE >= ? "
        + "AND S.LONGITUDE <= ? ";

    List<Object> params = new ArrayList<>();
    params.add(xmin);
    params.add(xmax);
    params.add(ymin);
    params.add(ymax);

    this.runQuery(query, params, new QueryDelegate(result) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        JSONArray result = (JSONArray)this.getListener();
        while (rs.next()){
          result.put(parsePointRS(rs));
        }
      }
    });
    return result;
  }
  
  public JSONArray getExtentWithFilteredResult(Double xmin, Double ymin, Double xmax, Double ymax, String stationIds) {
    JSONArray result = new JSONArray(); 
    
    String query = "SELECT S.LATITUDE, S.LONGITUDE, "
        + "S.EWM_STATION_ID, "
        + "MS.SITE_CODE, "
        + "EWM_STATE_WELL_NBR, "
        + "TOTAL_DEPTH_FT, "
        + "COMPLETION_RPT_NBR, "
        + "S.LOCAL_WELL_DESIGNATION, "
        + "LATEST_RP_ELEVATION, LATEST_GS_ELEVATION, "
        + "S.EWM_BASIN_ID, EWM_BASIN_CD, EWM_BASIN_DESC, "
        + "EWM_STATION_USE_DESC "
        + "FROM " + EntityUtil.getTableName(Station.class) + " S "
        + "LEFT JOIN " + EntityUtil.getTableName(StationUseType.class) + " SUT "
        + "ON SUT.EWM_STATION_USE_ID = S.EWM_STATION_USE_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(Basin.class) + " B "
        + "ON B.EWM_BASIN_ID = S.EWM_BASIN_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(MasterSite.class) + " MS "
        + "ON S.EWM_MASTER_SITE_ID = MS.EWM_MASTER_SITE_ID "
        + "WHERE S.LONGITUDE >= ? "
        + "AND S.LONGITUDE <= ? "
        + "AND S.LATITUDE >= ? "
        + "AND S.LONGITUDE <= ? ";
    
    List<Object> params = new ArrayList<>();
    params.add(xmin);
    params.add(xmax);
    params.add(ymin);
    params.add(ymax);
    if(stationIds != null && !stationIds.isEmpty()){
      String stationsIdsSubq = "";
      String stationsIn = "";
      String or = "";
      String[] splitIds = stationIds.split(",");
      for (int k=0; k<=Math.floor(splitIds.length/1000);k++){
        stationsIn = "";
        int start = k*1000;
        int end = Math.min((k+1)*1000, splitIds.length);
        for (int i=start;i<end;i++){
          stationsIn += "?,";
          params.add(splitIds[i]);
        }
        stationsIn = stationsIn.substring(0, stationsIn.length()-1);
        stationsIdsSubq += or + "(S.EWM_STATION_ID IN (" + stationsIn + "))";
        or = " or ";
      }
      query += "AND (" + stationsIdsSubq + ")";
      
    }
    this.runQuery(query, params, new QueryDelegate(result) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        JSONArray result = (JSONArray)this.getListener();
        while (rs.next()){
          result.put(parsePointRS(rs));
        }
      }
    });
    return result;
  }
  
  private JSONObject parsePointRS(ResultSet rs) {
    JSONObject result = new JSONObject();
    try {
      result.put("LATITUDE", rs.getDouble("LATITUDE"));
      result.put("LONGITUDE", rs.getDouble("LONGITUDE"));
      result.put("SITE_CODE", rs.getString("SITE_CODE"));
      result.put("STATION_ID", rs.getInt("EWM_STATION_ID"));
      result.put("STATE_WELL_NBR", rs.getString("EWM_STATE_WELL_NBR"));
      String localDesignation = rs.getString("LOCAL_WELL_DESIGNATION");
      if(localDesignation == null)
        localDesignation = "";
      result.put("LOCAL_WELL_DESIGNATION", localDesignation);
      result.put("TOTAL_DEPTH_FT", rs.getDouble("TOTAL_DEPTH_FT"));
      result.put("LATEST_GS_ELEVATION", rs.getDouble("LATEST_GS_ELEVATION"));
      result.put("LATEST_RP_ELEVATION", rs.getDouble("LATEST_RP_ELEVATION"));
      result.put("BASIN_ID", rs.getString("EWM_BASIN_ID"));
      result.put("BASIN_CD", rs.getString("EWM_BASIN_CD"));
      result.put("BASIN_DESC", rs.getString("EWM_BASIN_DESC"));
      result.put("STATION_USE_DESC", rs.getString("EWM_STATION_USE_DESC"));
      result.put("COMPLETION_RPT_NBR", rs.getString("COMPLETION_RPT_NBR"));
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Extent - getExtent()">
  public StationCollection getExtent(Double xmin, Double ymin, Double xmax, Double ymax) {
    this.findAll(
        new ConditionGroup(
          ConditionFilter.createInstance("longitude", ConditionFilter.ConditionOperator.SQL_GE, xmin),
          ConditionFilter.createInstance("longitude", ConditionFilter.ConditionOperator.SQL_LE, xmax),
          ConditionFilter.createInstance("latitude", ConditionFilter.ConditionOperator.SQL_GE, ymin),
          ConditionFilter.createInstance("latitude", ConditionFilter.ConditionOperator.SQL_LE, ymax)
        )
    );
    return this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="loadByIds">
  public StationCollection loadByIds(String[] splitIds) {
    return this.findAll(
        ConditionFilter.createInstance("ewmStationId", ConditionFilter.ConditionOperator.SQL_IN, new ArrayList<Object>(Arrays.asList(splitIds)))
    );
  }
  
  public StationCollection loadByIds(List<Integer> stationIds) {
    int start, end;
    for (int i = 0; i<stationIds.size(); i += 1000){
      start = 0;
      end = Math.min(start + 1000, stationIds.size());
      this.findAll(
          ConditionFilter.createInstance("ewmStationId", ConditionFilter.ConditionOperator.SQL_IN, stationIds.subList(start, end))
      );
    }
    return this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="loadRelatedWells">
  public StationCollection loadRelatedWells(Long stationId){
    String query = "SELECT EWM_STATION_ID FROM " + EntityUtil.getTableName(Station.class) +
      " WHERE COMPLETION_RPT_NBR = (SELECT COMPLETION_RPT_NBR FROM " + EntityUtil.getTableName(Station.class) +
      " WHERE EWM_STATION_ID=? AND EWM_WELL_COMPLETION_ID=2)";
    return this.runQuery(query, Arrays.asList(stationId));    
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="queryAutofill - new version (with more fields added)">
  public JSONArray queryAutofill(String keyword){
    JSONArray queryRes = new JSONArray();
    final JSONArray autofillRes = new JSONArray();
    String query = "";
    Map<String, Wrapper> map = getFieldsMap();
    List<Object> params = new ArrayList<>();
    int index = 0;
    for (Map.Entry<String,Wrapper> entry : map.entrySet()){
      index ++;
      String displayName = entry.getKey();
      String tableName = entry.getValue().getTableName();
      String columnName = entry.getValue().getColumnName();
      query += "SELECT * FROM (" + 
        "SELECT COUNT(" + columnName + ") CNT, CAST(" + columnName + " as VARCHAR(50))as VAL, '" + displayName + "' as TYPE " +
        "FROM " + tableName + " " + 
        "WHERE (LTRIM (UPPER(" + columnName + ")) LIKE ?) " +
        "GROUP BY " + columnName +
        ") WHERE ROWNUM <= 5 ";
       if(index != map.entrySet().size()){
         query +=  "UNION ALL ";
       }
       params.add("%" + keyword.toUpperCase() + "%");
    }
    this.runQuery(query, params, new QueryDelegate(queryRes) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        while (rs.next()){          
          JSONObject localwell = new JSONObject();
          localwell.put("count", rs.getInt("CNT")); 
          localwell.put("name", rs.getString("VAL"));
          localwell.put("type", rs.getString("TYPE"));
          autofillRes.put(localwell);
        }
      }
    });
    return autofillRes;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="queryAutofill - original">
//  public JSONArray queryAutofill(String keyword) {
//    JSONArray queryRes = new JSONArray();
//    final JSONArray autofillRes = new JSONArray();
//      
//    String query = "SELECT * FROM ( " +
//      "SELECT COUNT(EWM_STATE_WELL_NBR) CNT, EWM_STATE_WELL_NBR as VAL, 'state well no' as TYPE" + " " +
//      "FROM " +
//      "" + EntityUtil.getTableName(Station.class) + " s " +
//      "WHERE (LTRIM(UPPER(EWM_STATE_WELL_NBR)) LIKE ?) " +
//      "GROUP BY EWM_STATE_WELL_NBR " +
////        " HAVING COUNT(EWM_STATE_WELL_NBR) > 1"  +
//      ") WHERE ROWNUM <= 5 " +
//      // search by LOCAL_WELL_DESIGNATION
//        "UNION ALL " +
//        "SELECT * FROM ( " +
//        "SELECT COUNT(LOCAL_WELL_DESIGNATION) CNT, LOCAL_WELL_DESIGNATION as VAL, 'local well id' as TYPE " +
//        "FROM " + EntityUtil.getTableName(Station.class) + " S " +
//        "WHERE (LTRIM(UPPER(LOCAL_WELL_DESIGNATION)) LIKE ?) " + 
//        "GROUP BY LOCAL_WELL_DESIGNATION" +
////        " HAVING COUNT(LOCAL_WELL_DESIGNATION) > 1"  +
//        ") WHERE ROWNUM <= 5 ";
////        // search by EWM_BASIN_DESC
////        "UNION ALL " +
////        "SELECT * FROM ( " +
////        "SELECT COUNT(EWM_BASIN_DESC) CNT, EWM_BASIN_DESC as VAL, 'basin' as TYPE " +
////        "FROM " + EntityUtil.getTableName(Station.class) + " S " +
////        "LEFT JOIN " + EntityUtil.getTableName(Basin.class) + " B " +
////        "ON s.ewm_basin_id = b.ewm_basin_id " +
////        "WHERE (LTRIM(UPPER(EWM_BASIN_DESC)) LIKE ?) " + 
////        "GROUP BY EWM_BASIN_DESC" +
//////        " HAVING COUNT(EWM_BASIN_DESC) > 1"  +
////        ") WHERE ROWNUM <= 5 " +
////        "UNION ALL " +
////        "SELECT * FROM ( " +
////        "SELECT COUNT(EWM_BASIN_CD) CNT, EWM_BASIN_CD as VAL, 'basin no' as TYPE " +
////        "FROM " + EntityUtil.getTableName(Station.class) + " S " +
////        "LEFT JOIN " + EntityUtil.getTableName(Basin.class) + " B " +
////        "ON s.ewm_basin_id = b.ewm_basin_id " +
////        "WHERE (LTRIM(UPPER(EWM_BASIN_CD)) LIKE ?) " + 
////        "GROUP BY EWM_BASIN_CD" +
//////        " HAVING COUNT(EWM_BASIN_cd) > 1"  +
////        ") WHERE ROWNUM <= 5 " +
////        "UNION ALL " +
////        "SELECT * FROM ( " +
////        "SELECT COUNT(EWM_BASIN_REGION_DESC) CNT, EWM_BASIN_REGION_DESC as VAL, 'basin region' as TYPE " +
////        "FROM " + EntityUtil.getTableName(Station.class) + " S " +
////        "LEFT JOIN " + EntityUtil.getTableName(Basin.class) + " B " +
////        "ON s.ewm_basin_id = b.ewm_basin_id " +
////        "LEFT JOIN " + EntityUtil.getTableName(BasinRegion.class) + " BR " +
////        "ON br.ewm_basin_region_id = b.ewm_basin_region_id " +
////        "WHERE (LTRIM(UPPER(EWM_BASIN_REGION_DESC)) LIKE ?) " + 
////        "GROUP BY EWM_BASIN_REGION_DESC" +
////        " HAVING COUNT(EWM_BASIN_REGION_DESC) > 1"  +
////        ") WHERE ROWNUM <= 5 "+
////        "UNION ALL " +
////        "SELECT * FROM ( " +
////        "SELECT COUNT(EWM_STATION_USE_DESC) CNT, EWM_STATION_USE_DESC as VAL, 'Well Use' as TYPE " +
////        "FROM " + EntityUtil.getTableName(Station.class) + " S " +
////        "LEFT JOIN " + EntityUtil.getTableName(StationUseType.class) + " su " +
////        "ON s.ewm_station_use_id = su.ewm_station_use_id " +
////        "WHERE (LTRIM(UPPER(EWM_STATION_USE_DESC)) LIKE ?) " + 
////        "GROUP BY EWM_STATION_USE_DESC" +
////        " HAVING COUNT(EWM_STATION_USE_DESC) > 1"  +
////        ") WHERE ROWNUM <= 5 "
//        ; 
//    List<Object> params = new ArrayList<>();
//    params.add("%" + keyword.toUpperCase() + "%");
//    params.add("%" + keyword.toUpperCase() + "%");
////    params.add("%" + keyword.toUpperCase() + "%");
////    params.add("%" + keyword.toUpperCase() + "%");
////    params.add("%" + keyword.toUpperCase() + "%");
//    
//    this.runQuery(query, params, new QueryDelegate(queryRes) {
//      @Override
//      public void handle(ResultSet rs) throws Exception {
//        while (rs.next()){          
//          JSONObject localwell = new JSONObject();
//          localwell.put("count", rs.getInt("CNT")); // todo: fix this
//          localwell.put("name", rs.getString("VAL"));
//          localwell.put("type", rs.getString("TYPE"));
//          autofillRes.put(localwell);
//        }
//      }
//    });
//    
//    return autofillRes;
//  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="getQueryCount">
  public Integer getQueryCount(
            String keyword,
            String startDate, 
            String endDate,
            String minWellDepth, 
            String maxWellDepth, 
            Boolean acceptNullDepth,
            JSONArray wellUseType,
            String extent)
  {
    Integer result = null;
    Integer[] count = new Integer[1];
    
    //<editor-fold defaultstate="collapsed" desc="query">
    String query = "SELECT COUNT(*) as COUNT "
      + "FROM " + EntityUtil.getTableName(Station.class) + " S " 
      + "LEFT JOIN " + EntityUtil.getTableName(MasterSite.class) + " M "
      + "ON M.EWM_MASTER_SITE_ID = S.EWM_MASTER_SITE_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(Organization.class) + " O "
      + "ON O.ORG_ID = S.PRIMARY_WELL_ORG_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(StationUseType.class) + " SUT "
      + "ON SUT.EWM_STATION_USE_ID = S.EWM_STATION_USE_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(WellCompletionType.class) + " WCT " 
      + "ON WCT.EWM_WELL_COMPLETION_ID = S.EWM_WELL_COMPLETION_ID "
      + "LEFT JOIN ("
      + "SELECT EWM_STATION_ID, MIN(PERFORATION_TOP_MSRMNT) PERFORATION_TOP_MSRMNT, MAX(PERFORATION_BOTTOM_MSRMNT) PERFORATION_BOTTOM_MSRMNT "
      + "FROM (" 
      + "SELECT S1.EWM_STATION_ID, SP.PERFORATION_TOP_MSRMNT, SP.PERFORATION_BOTTOM_MSRMNT "
      + "FROM " + EntityUtil.getTableName(Station.class) + " S1 "
      + "LEFT JOIN " + EntityUtil.getTableName(StationPerforation.class) + " SP " 
      + "ON SP.EWM_STATION_ID = S1.EWM_STATION_ID )"
      + "GROUP BY EWM_STATION_ID ) perforation "
      + "ON perforation.EWM_STATION_ID = S.EWM_STATION_ID "
      + "WHERE ";
    //</editor-fold>
    
    List<Object> params = new ArrayList<>();
    
    //<editor-fold defaultstate="collapsed" desc="where">
    if(acceptNullDepth == true){
      if(minWellDepth.isEmpty() && maxWellDepth.isEmpty()){
        query += "";
      }
      else{
        query += "((S.TOTAL_DEPTH_FT IS NULL) OR (S.TOTAL_DEPTH_FT BETWEEN ? AND ?)) AND";
        params.add(minWellDepth);
        params.add(maxWellDepth);
      }
    }
    else{
      if(!minWellDepth.isEmpty() && !maxWellDepth.isEmpty()){
        query += "(S.TOTAL_DEPTH_FT BETWEEN ? AND ?) AND";
        params.add(minWellDepth);
        params.add(maxWellDepth);
      }
    }
    
    if(!startDate.isEmpty() && !endDate.isEmpty()){
      query += "( S.EWM_STATION_ID IN " + 
        "(SELECT DISTINCT EWM_STATION_ID FROM " + EntityUtil.getTableName(ElevationDataReading.class)+ " WHERE MEASUREMENT_DT BETWEEN " +
        "TO_DATE(?, 'MM/DD/YY') " +
        "AND TO_DATE(?, 'MM/DD/YY')) ) AND";
      params.add(startDate);
      params.add(endDate);
    }
    else if(!startDate.isEmpty()){
      query += "( S.EWM_STATION_ID IN " + 
        "(SELECT DISTINCT EWM_STATION_ID FROM " + EntityUtil.getTableName(ElevationDataReading.class)+ " WHERE MEASUREMENT_DT > " +
        "TO_DATE(?, 'MM/DD/YY')) ) AND";
      params.add(startDate);
    }
    else if(!endDate.isEmpty()){
      query += "AND ( S.EWM_STATION_ID IN " + 
        "(SELECT DISTINCT EWM_STATION_ID FROM " + EntityUtil.getTableName(ElevationDataReading.class)+ " WHERE MEASUREMENT_DT < " +
        "TO_DATE(?, 'MM/DD/YY')) ) AND";
      params.add(endDate);
    }
    
    if(wellUseType.length() > 0){
      query +=  "( sut.EWM_STATION_USE_DESC IN (";
      String comma = "";
      for (int i=0; i<wellUseType.length(); i++){
        query += comma + "?";
        comma = ",";
        params.add(wellUseType.getString(i));
      }
      query += ")) AND";
    }
    
    if(!keyword.isEmpty()){
      String keywordValue = null;
      String keywordType = null;
      try{
        JSONObject keywordJson = new JSONObject(keyword);
        keywordValue = keywordJson.getString("value");
        keywordType = keywordJson.getString("type");
      } catch(JSONException ex){
        ExceptionUtil.throwIllegalArgumentException(ex);
      }
      
      if(keywordType != null && !keywordType.isEmpty()){
        Map<String, Wrapper> map = getFieldsMap();
        if(!keywordType.equals("all")){
          Wrapper w = map.get(keywordType);
          String columnName = w.getColumnName();
//          String tableName = w.getTableName();
          String tableAbbr = "";
          switch (columnName) {
            case "EWM_STATION_ID":
            case "EWM_STATE_WELL_NBR":
            case "LOCAL_WELL_DESIGNATION":
            case "COMPLETION_RPT_NBR":
              tableAbbr = "S";
              break;
            case "SITE_CODE":
              tableAbbr = "M";
              break;
            case "ORG_NAME":
              tableAbbr = "O";
              break;
          }
          query += "( LOWER(" + tableAbbr + "." + columnName + ") = ? ) AND";
          params.add(keywordValue.toLowerCase());
        } 
        else {
          for(Map.Entry<String, Wrapper> entry : map.entrySet()){
            
          }
          query += "("
            + "(LOWER(S.EWM_STATION_ID) LIKE ?) OR"
            + "(LOWER(S.EWM_STATE_WELL_NBR) LIKE ?) OR"
            + "(LOWER(S.LOCAL_WELL_DESIGNATION) LIKE ?) OR"
            + "(LOWER(S.COMPLETION_RPT_NBR) LIKE ?) OR"
            + "(LOWER(M.SITE_CODE) LIKE ?) OR"
            + "(LOWER(O.ORG_NAME) LIKE ?) "
            + " ) AND";
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
        }
      }
    }
    
    if(extent != null && !extent.isEmpty()){
      try {
        JSONArray extentArray = new JSONArray(extent);
        JSONArray min = (JSONArray) extentArray.get(0);
        JSONArray max = (JSONArray) extentArray.get(1);
        query += "( S.LATITUDE BETWEEN ? AND ? ) AND " + 
                 "( S.LONGITUDE BETWEEN ? AND ? )";
        params.add(min.getDouble(1));
        params.add(max.getDouble(1));
        params.add(min.getDouble(0));
        params.add(max.getDouble(0));
      } catch (Exception e) {
        ExceptionUtil.throwIllegalStateException(e);
      } 
    }
    
    if(query.endsWith("AND")){
      query = query.substring(0, query.length()-3);
    }
    //</editor-fold>
    
    this.runQuery(query, params, new QueryDelegate(count) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        Integer count[] = this.getListener();
        rs.next();
        count[0] = rs.getInt("COUNT");
      }      
    });
    result = count[0];
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Query">
  public JSONArray query( 
            String keyword,
            String startDate, 
            String endDate,
            String minWellDepth, 
            String maxWellDepth, 
            Boolean acceptNullDepth,
            JSONArray wellUseType,
            String extent) 
  {
    JSONArray queryRes = new JSONArray();
    final JSONArray finalRes = new JSONArray();
    
    String query = buildSearchQuery();
    List<Object> params = new ArrayList<>();
    
    if(acceptNullDepth == true){
      if(minWellDepth.isEmpty() && maxWellDepth.isEmpty()){
        query += "";
      }
      else{
        query += "((S.TOTAL_DEPTH_FT IS NULL) OR (S.TOTAL_DEPTH_FT BETWEEN ? AND ?)) AND";
        params.add(minWellDepth);
        params.add(maxWellDepth);
      }
    }
    else{
      if(!minWellDepth.isEmpty() && !maxWellDepth.isEmpty()){
        query += "(S.TOTAL_DEPTH_FT BETWEEN ? AND ?) AND";
        params.add(minWellDepth);
        params.add(maxWellDepth);
      }
    }
    
    if(!startDate.isEmpty() && !endDate.isEmpty()){
      query += "( S.EWM_STATION_ID IN " + 
        "(SELECT DISTINCT EWM_STATION_ID FROM " + EntityUtil.getTableName(ElevationDataReading.class)+ " WHERE MEASUREMENT_DT BETWEEN " +
        "TO_DATE(?, 'MM/DD/YY') " +
        "AND TO_DATE(?, 'MM/DD/YY')) ) AND";
      params.add(startDate);
      params.add(endDate);
    }
    else if(!startDate.isEmpty()){
      query += "( S.EWM_STATION_ID IN " + 
        "(SELECT DISTINCT EWM_STATION_ID FROM " + EntityUtil.getTableName(ElevationDataReading.class)+ " WHERE MEASUREMENT_DT > " +
        "TO_DATE(?, 'MM/DD/YY')) ) AND";
      params.add(startDate);
    }
    else if(!endDate.isEmpty()){
      query += "AND ( S.EWM_STATION_ID IN " + 
        "(SELECT DISTINCT EWM_STATION_ID FROM " + EntityUtil.getTableName(ElevationDataReading.class)+ " WHERE MEASUREMENT_DT < " +
        "TO_DATE(?, 'MM/DD/YY')) ) AND";
      params.add(endDate);
    }
    
    if(wellUseType.length() > 0){
      query +=  "( sut.EWM_STATION_USE_DESC IN (";
//      List<String> types = Arrays.asList(wellUseType.split(","));
      String comma = "";
      for (int i=0; i<wellUseType.length(); i++){
        query += comma + "?";
        comma = ",";
        params.add(wellUseType.getString(i));
      }
      query += ")) AND";
    }
    
    if(!keyword.isEmpty()){
      String keywordValue = null;
      String keywordType = null;
      try{
        JSONObject keywordJson = new JSONObject(keyword);
        keywordValue = keywordJson.getString("value");
        keywordType = keywordJson.getString("type");
      } catch(JSONException ex){
        ExceptionUtil.throwIllegalArgumentException(ex);
      }
      
      if(keywordType != null && !keywordType.isEmpty()){
        Map<String, Wrapper> map = getFieldsMap();
        if(!keywordType.equals("all")){
          Wrapper w = map.get(keywordType);
          String columnName = w.getColumnName();
//          String tableName = w.getTableName();
          String tableAbbr = "";
          switch (columnName) {
            case "EWM_STATION_ID":
            case "EWM_STATE_WELL_NBR":
            case "LOCAL_WELL_DESIGNATION":
            case "COMPLETION_RPT_NBR":
              tableAbbr = "S";
              break;
            case "SITE_CODE":
              tableAbbr = "M";
              break;
            case "ORG_NAME":
              tableAbbr = "O";
              break;
          }
          query += "( LOWER(" + tableAbbr + "." + columnName + ") = ? ) AND";
          params.add(keywordValue.toLowerCase());
        } 
        else {
          for(Map.Entry<String, Wrapper> entry : map.entrySet()){
            
          }
          query += "("
            + "(LOWER(S.EWM_STATION_ID) LIKE ?) OR"
            + "(LOWER(S.EWM_STATE_WELL_NBR) LIKE ?) OR"
            + "(LOWER(S.LOCAL_WELL_DESIGNATION) LIKE ?) OR"
            + "(LOWER(S.COMPLETION_RPT_NBR) LIKE ?) OR"
            + "(LOWER(M.SITE_CODE) LIKE ?) OR"
            + "(LOWER(O.ORG_NAME) LIKE ?) "
            + " ) AND";
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
        }
      }
    }
    
    if(extent != null && !extent.isEmpty()){
      try {
        JSONArray extentArray = new JSONArray(extent);
        JSONArray min = (JSONArray) extentArray.get(0);
        JSONArray max = (JSONArray) extentArray.get(1);
        query += "( S.LATITUDE BETWEEN ? AND ? ) AND " + 
                 "( S.LONGITUDE BETWEEN ? AND ? )";
        params.add(min.getDouble(1));
        params.add(max.getDouble(1));
        params.add(min.getDouble(0));
        params.add(max.getDouble(0));
      } catch (Exception e) {
        ExceptionUtil.throwIllegalStateException(e);
      } 
    }
    
    if(query.endsWith("AND")){
      query = query.substring(0, query.length()-3);
    }
    
    this.runQuery(query, params, new QueryDelegate(queryRes) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        while (rs.next()){
          JSONObject localwell = new JSONObject();
          localwell.put("SITE_CODE", rs.getString("SITE_CODE")!=null?rs.getString("SITE_CODE"):"");
          localwell.put("WELL_NAME", rs.getString("LOCAL_WELL_DESIGNATION")!=null?rs.getString("LOCAL_WELL_DESIGNATION"):""); 
          localwell.put("SWN", rs.getString("EWM_STATE_WELL_NBR")!=null?rs.getString("EWM_STATE_WELL_NBR"):"");
          localwell.put("STN_ID", rs.getString("EWM_STATION_ID")!=null?rs.getString("EWM_STATION_ID"):"");
          localwell.put("WCR_NO", rs.getString("COMPLETION_RPT_NBR")!=null?rs.getString("COMPLETION_RPT_NBR"):"");
          localwell.put("LATITUDE", rs.getString("LATITUDE")!=null?rs.getString("LATITUDE"):"");
          localwell.put("LONGITUDE", rs.getString("LONGITUDE")!=null?rs.getString("LONGITUDE"):"");
          localwell.put("STN_ORG_ID", rs.getString("PRIMARY_WELL_ORG_ID")!=null?rs.getString("PRIMARY_WELL_ORG_ID"):"");
          localwell.put("STN_ORG_NAME", rs.getString("ORG_NAME")!=null?rs.getString("ORG_NAME"):"");
          localwell.put("LOC_DESC", rs.getString("WELL_LOCATION_DESC")!=null?rs.getString("WELL_LOCATION_DESC"):"");
          localwell.put("WELL_USE", rs.getString("EWM_STATION_USE_DESC")!=null?rs.getString("EWM_STATION_USE_DESC"):"");
          localwell.put("WELL_TYPE", rs.getString("EWM_WELL_COMPLETION_DESC")!=null?rs.getString("EWM_WELL_COMPLETION_DESC"):"");
          localwell.put("WELL_DEPTH", rs.getString("TOTAL_DEPTH_FT")!=null?rs.getString("TOTAL_DEPTH_FT"):"");
          localwell.put("TOP_PRF", rs.getString("PERFORATION_TOP_MSRMNT")!=null?rs.getString("PERFORATION_TOP_MSRMNT"):"");
          localwell.put("BOT_PRF", rs.getString("PERFORATION_BOTTOM_MSRMNT")!=null?rs.getString("PERFORATION_BOTTOM_MSRMNT"):"");
          localwell.put("GSE", rs.getString("LATEST_GS_ELEVATION")!=null?rs.getString("LATEST_GS_ELEVATION"):"");
          localwell.put("RPE", rs.getString("LATEST_RP_ELEVATION")!=null?rs.getString("LATEST_RP_ELEVATION"):"");
          localwell.put("RP_DESC", rs.getString("RP_DESC")!=null?rs.getString("RP_DESC"):"");
          localwell.put("STN_CMT", rs.getString("ADDL_COMMENTS")!=null?rs.getString("ADDL_COMMENTS"):"");    
          finalRes.put(localwell);
        }
      }
    });
    return finalRes;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Query - by keywords">
  public JSONArray queryAllFieldsByKeyword(String keyword){
    JSONArray queryRes = new JSONArray();
    final JSONArray finalRes = new JSONArray();
    
    List<Object> params = new ArrayList<>();
    
    String query = buildSearchQuery();
    for(String f : searchableFields){
      query += "(LTRIM(UPPER( " + f + ")) LIKE ?) ";
      params.add(keyword);
    }
        
    this.runQuery(query, params, new QueryDelegate(queryRes) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        while (rs.next()){
          JSONObject localwell = new JSONObject();
          localwell.put("FID", "");  // For the openInfoWindowBtn icon
          localwell.put("SITE_CODE", rs.getString("SITE_CODE")!=null?rs.getString("SITE_CODE"):"");
          localwell.put("WELL_NAME", rs.getString("LOCAL_WELL_DESIGNATION")!=null?rs.getString("LOCAL_WELL_DESIGNATION"):""); 
          localwell.put("SWN", rs.getString("EWM_STATE_WELL_NBR")!=null?rs.getString("EWM_STATE_WELL_NBR"):"");
          localwell.put("STN_ID", rs.getString("EWM_STATION_ID")!=null?rs.getString("EWM_STATION_ID"):"");
          localwell.put("WCR_NO", rs.getString("COMPLETION_RPT_NBR")!=null?rs.getString("COMPLETION_RPT_NBR"):"");
          localwell.put("LATITUDE", rs.getString("LATITUDE")!=null?rs.getString("LATITUDE"):"");
          localwell.put("LONGITUDE", rs.getString("LONGITUDE")!=null?rs.getString("LONGITUDE"):"");
          localwell.put("STN_ORG_ID", rs.getString("PRIMARY_WELL_ORG_ID")!=null?rs.getString("PRIMARY_WELL_ORG_ID"):"");
          localwell.put("STN_ORG_NAME", rs.getString("ORG_NAME")!=null?rs.getString("ORG_NAME"):"");
          localwell.put("LOC_DESC", rs.getString("WELL_LOCATION_DESC")!=null?rs.getString("WELL_LOCATION_DESC"):"");
          localwell.put("WELL_USE", rs.getString("EWM_STATION_USE_DESC")!=null?rs.getString("EWM_STATION_USE_DESC"):"");
          localwell.put("WELL_TYPE", rs.getString("EWM_WELL_COMPLETION_DESC")!=null?rs.getString("EWM_WELL_COMPLETION_DESC"):"");
          localwell.put("WELL_DEPTH", rs.getString("TOTAL_DEPTH_FT")!=null?rs.getString("TOTAL_DEPTH_FT"):"");
          localwell.put("TOP_PRF", rs.getString("PERFORATION_TOP_MSRMNT")!=null?rs.getString("PERFORATION_TOP_MSRMNT"):"");
          localwell.put("BOT_PRF", rs.getString("PERFORATION_BOTTOM_MSRMNT")!=null?rs.getString("PERFORATION_BOTTOM_MSRMNT"):"");
          localwell.put("LAST_GSE", rs.getString("LATEST_GS_ELEVATION")!=null?rs.getString("LATEST_GS_ELEVATION"):"");
          localwell.put("LAST_RPE", rs.getString("LATEST_RP_ELEVATION")!=null?rs.getString("LATEST_RP_ELEVATION"):"");
          localwell.put("RP_DESC", rs.getString("RP_DESC")!=null?rs.getString("RP_DESC"):"");
          localwell.put("STN_CMT", rs.getString("ADDL_COMMENTS")!=null?rs.getString("ADDL_COMMENTS"):"");    
          finalRes.put(localwell);
        }
      }
    });
    return finalRes;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Query - by date range">
  public JSONArray queryByDateRange(String startDate, String endDate) throws ParseException{
    JSONArray queryRes = new JSONArray();
    final JSONArray finalRes = new JSONArray();
    String query = "SELECT * FROM " + EntityUtil.getTableName(Station.class) +
      " WHERE EWM_STATION_ID IN " +
      "(SELECT DISTINCT EWM_STATION_ID FROM " + EntityUtil.getTableName(ElevationDataReading.class)+
      " WHERE MEASUREMENT_DT BETWEEN " +
      "TO_DATE(?, 'MM/DD/YY') " +
      "AND TO_DATE(?, 'MM/DD/YY'))";
    
//    String query2 = "SELECT * FROM " + EntityUtil.getTableName(Station.class)+ " S " +
//      "WHERE MODIFIED_DATE BETWEEN " +
//      "TO_DATE(?, 'YYYY-MM-DD') " +
//      "AND TO_DATE(?,'YYYY-MM-DD')";
    
    List<Object> params = new ArrayList<>();
    params.add(startDate);
    params.add(endDate);
    
    this.runQuery(query, params, new QueryDelegate(queryRes) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        while (rs.next()){          
          JSONObject localwell = new JSONObject();
          localwell.put("stationId", rs.getInt("EWM_STATION_ID")); 
          localwell.put("stationWellNo", rs.getString("EWM_STATE_WELL_NBR"));
          localwell.put("localWellDesig", rs.getString("LOCAL_WELL_DESIGNATION"));
          finalRes.put(localwell);
        }
      }
    });
    return finalRes;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Query - by stateWellNo">
  public JSONArray query(String stateWellNo){
    JSONArray queryRes = new JSONArray();
    final JSONArray finalRes = new JSONArray();
    String query = "SELECT * FROM " + EntityUtil.getTableName(Station.class) + " WHERE EWM_STATE_WELL_NBR = ? ";
    List<Object> params = new ArrayList<>();
    params.add(stateWellNo);
    
    this.runQuery(query, params, new QueryDelegate(queryRes) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        while (rs.next()){
          JSONObject localwell = new JSONObject();
          localwell.put("stationId", rs.getInt("EWM_STATION_ID")); 
          localwell.put("latitude", rs.getString("LATITUDE"));
          localwell.put("longitude", rs.getString("LONGITUDE"));
          finalRes.put(localwell);
        }
      }
    });
    return finalRes;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Query - for download">
  public JSONArray queryForDownload(String queryData) throws ShapefileException, ProjectionException, IOException{
    JSONArray queryRes = new JSONArray();
    final JSONArray stationArray = new JSONArray();
    //<editor-fold defaultstate="collapsed" desc="Get the query parameters">
    JSONObject queryJson = new JSONObject(queryData);
    String keyword = queryJson.has("keyword")? queryJson.optString("keyword"): "";
    String startDate = queryJson.has("startDate")? queryJson.optString("startDate"): "";
    String endDate = queryJson.has("endDate")? queryJson.optString("endDate"): "";
    String minWellDepth = queryJson.has("minDepth")? queryJson.optString("minDepth"): "";
    String maxWellDepth = queryJson.has("maxDepth")? queryJson.optString("maxDepth"): "";
    Boolean acceptNullDepth = queryJson.optBoolean("nullDepth");
    String wellUseType = queryJson.has("wellUseType")? queryJson.optString("wellUseType"): "";
    String extent = queryJson.has("extent")? queryJson.optString("extent"): "";
    JSONObject shape = queryJson.has("shapeDrawn")? queryJson.optJSONObject("shapeDrawn"): null;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Build the query + where statement">    
    String query = buildExportQuery();
    List<Object> params = new ArrayList<>();
    if(acceptNullDepth == true){
      if(minWellDepth.isEmpty() && maxWellDepth.isEmpty()){
        query += "";
      }
      else{
        query += "((S.TOTAL_DEPTH_FT IS NULL) OR (S.TOTAL_DEPTH_FT BETWEEN ? AND ?)) AND";
        params.add(minWellDepth);
        params.add(maxWellDepth);
      }
    }
    else{
      if(!minWellDepth.isEmpty() && !maxWellDepth.isEmpty()){
        query += "(S.TOTAL_DEPTH_FT BETWEEN ? AND ?) AND";
        params.add(minWellDepth);
        params.add(maxWellDepth);
      }
    }
    
    if(!startDate.isEmpty() && !endDate.isEmpty()){
      query += "( S.EWM_STATION_ID IN " + 
        "(SELECT DISTINCT EWM_STATION_ID FROM " + EntityUtil.getTableName(ElevationDataReading.class)+ " WHERE MEASUREMENT_DT BETWEEN " +
        "TO_DATE(?, 'MM/DD/YY') " +
        "AND TO_DATE(?, 'MM/DD/YY')) ) AND";
      params.add(startDate);
      params.add(endDate);
    }
    else if(!startDate.isEmpty()){
      query += "( S.EWM_STATION_ID IN " + 
        "(SELECT DISTINCT EWM_STATION_ID FROM " + EntityUtil.getTableName(ElevationDataReading.class)+ " WHERE MEASUREMENT_DT > " +
        "TO_DATE(?, 'MM/DD/YY')) ) AND";
      params.add(startDate);
    }
    else if(!endDate.isEmpty()){
      query += "AND ( S.EWM_STATION_ID IN " + 
        "(SELECT DISTINCT EWM_STATION_ID FROM " + EntityUtil.getTableName(ElevationDataReading.class)+ " WHERE MEASUREMENT_DT < " +
        "TO_DATE(?, 'MM/DD/YY')) ) AND";
      params.add(endDate);
    }
    
    if(!wellUseType.isEmpty()){
      query +=  "( sut.EWM_STATION_USE_DESC IN (";
      List<String> types = Arrays.asList(wellUseType.split(","));
      String comma = "";
      for (int i=0; i<types.size(); i++){
        query += comma + "?";
        comma = ",";
        params.add(types.get(i));
      }
      query += ")) AND";
    }
    
    if(!keyword.isEmpty()){
      String keywordValue = null;
      String keywordType = null;
      try{
        JSONObject keywordJson = new JSONObject(keyword);
        keywordValue = keywordJson.getString("value");
        keywordType = keywordJson.getString("type");
      } catch(JSONException ex){
        ExceptionUtil.throwIllegalArgumentException(ex);
      }
      
      if(keywordType != null && !keywordType.isEmpty()){
        Map<String, Wrapper> map = getFieldsMap();
        if(!keywordType.equals("all")){
          Wrapper w = map.get(keywordType);
          String columnName = w.getColumnName();
//          String tableName = w.getTableName();
          String tableAbbr = "";
          switch (columnName) {
            case "EWM_STATION_ID":
            case "EWM_STATE_WELL_NBR":
            case "LOCAL_WELL_DESIGNATION":
            case "COMPLETION_RPT_NBR":
              tableAbbr = "S";
              break;
            case "SITE_CODE":
              tableAbbr = "M";
              break;
            case "ORG_NAME":
              tableAbbr = "O";
              break;
          }
          query += "( LOWER(" + tableAbbr + "." + columnName + ") = ? ) AND";
          params.add(keywordValue.toLowerCase());
        } 
        else {
          for(Map.Entry<String, Wrapper> entry : map.entrySet()){
            
          }
          query += "("
            + "(LOWER(S.EWM_STATION_ID) LIKE ?) OR"
            + "(LOWER(S.EWM_STATE_WELL_NBR) LIKE ?) OR"
            + "(LOWER(S.LOCAL_WELL_DESIGNATION) LIKE ?) OR"
            + "(LOWER(S.COMPLETION_RPT_NBR) LIKE ?) OR"
            + "(LOWER(M.SITE_CODE) LIKE ?) OR"
            + "(LOWER(O.ORG_NAME) LIKE ?) "
            + " ) AND";
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
        }
      }
    }
    
    if(extent != null && !extent.isEmpty()){
      try {
        JSONArray extentArray = new JSONArray(extent);
        JSONArray min = (JSONArray) extentArray.get(0);
        JSONArray max = (JSONArray) extentArray.get(1);
        query += "( S.LATITUDE BETWEEN ? AND ? ) AND " + 
                 "( S.LONGITUDE BETWEEN ? AND ? )";
        params.add(min.getDouble(1));
        params.add(max.getDouble(1));
        params.add(min.getDouble(0));
        params.add(max.getDouble(0));
      } catch (Exception e) {
        ExceptionUtil.throwIllegalStateException(e);
      } 
    }
    
    if(query.endsWith("AND")){
      query = query.substring(0, query.length()-3);
    }
    //</editor-fold>
    
    final List<String> headers = stationDownloadFields();
    this.runQuery(query, params, new QueryDelegate(queryRes) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        while (rs.next()){
          JSONObject localwell = new JSONObject();
          for(String h : headers){
            localwell.put(h, rs.getString(h)!=null?rs.getString(h):"");
          }
          stationArray.put(localwell);
        }
      }
    });
    JSONArray finalRes = shape != null? stationArray: filterByShape(stationArray, shape);
    return finalRes;
  }
//</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Download - csv, shapefile & return a hashset (STN_ID)">
  public HashSet export( final ZipOutputStream zos, String queryData ) throws ShapefileException, ProjectionException, IOException{
    final HashSet stationIds = new HashSet();
    //<editor-fold defaultstate="collapsed" desc="Get the query parameters">
    JSONObject queryJson = new JSONObject(queryData);
    String keyword = queryJson.has("keyword")? queryJson.optString("keyword"): "";
    String startDate = queryJson.has("startDate")? queryJson.optString("startDate"): "";
    String endDate = queryJson.has("endDate")? queryJson.optString("endDate"): "";
    String minWellDepth = queryJson.has("minDepth")? queryJson.optString("minDepth"): "";
    String maxWellDepth = queryJson.has("maxDepth")? queryJson.optString("maxDepth"): "";
    Boolean acceptNullDepth = queryJson.optBoolean("nullDepth");
    String wellUseType = queryJson.has("wellUseType")? queryJson.optString("wellUseType"): "";
    String extent = queryJson.has("extent")? queryJson.optString("extent"): "";
    JSONObject shape = queryJson.has("shapeDrawn")? queryJson.optJSONObject("shapeDrawn"): null;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Build the query + where statement">    
    String query = buildExportQuery();
    List<Object> params = new ArrayList<>();
    if(acceptNullDepth == true){
      if(minWellDepth.isEmpty() && maxWellDepth.isEmpty()){
        query += "";
      }
      else{
        query += "((S.TOTAL_DEPTH_FT IS NULL) OR (S.TOTAL_DEPTH_FT BETWEEN ? AND ?)) AND";
        params.add(minWellDepth);
        params.add(maxWellDepth);
      }
    }
    else{
      if(!minWellDepth.isEmpty() && !maxWellDepth.isEmpty()){
        query += "(S.TOTAL_DEPTH_FT BETWEEN ? AND ?) AND";
        params.add(minWellDepth);
        params.add(maxWellDepth);
      }
    }
    
    if(!startDate.isEmpty() && !endDate.isEmpty()){
      query += "( S.EWM_STATION_ID IN " + 
        "(SELECT DISTINCT EWM_STATION_ID FROM " + EntityUtil.getTableName(ElevationDataReading.class)+ " WHERE MEASUREMENT_DT BETWEEN " +
        "TO_DATE(?, 'MM/DD/YY') " +
        "AND TO_DATE(?, 'MM/DD/YY')) ) AND";
      params.add(startDate);
      params.add(endDate);
    }
    else if(!startDate.isEmpty()){
      query += "( S.EWM_STATION_ID IN " + 
        "(SELECT DISTINCT EWM_STATION_ID FROM " + EntityUtil.getTableName(ElevationDataReading.class)+ " WHERE MEASUREMENT_DT > " +
        "TO_DATE(?, 'MM/DD/YY')) ) AND";
      params.add(startDate);
    }
    else if(!endDate.isEmpty()){
      query += "AND ( S.EWM_STATION_ID IN " + 
        "(SELECT DISTINCT EWM_STATION_ID FROM " + EntityUtil.getTableName(ElevationDataReading.class)+ " WHERE MEASUREMENT_DT < " +
        "TO_DATE(?, 'MM/DD/YY')) ) AND";
      params.add(endDate);
    }
    
    if(!wellUseType.isEmpty()){
      JSONArray wellUseTypeJson = new JSONArray(wellUseType);
      query +=  "( sut.EWM_STATION_USE_DESC IN (";
      String comma = "";
      for (int i=0; i<wellUseTypeJson.length(); i++){
        query += comma + "?";
        comma = ",";
        params.add(wellUseTypeJson.getString(i));
      }
      query += ")) AND";
    }
    
    if(!keyword.isEmpty()){
      String keywordValue = null;
      String keywordType = null;
      try{
        JSONObject keywordJson = new JSONObject(keyword);
        keywordValue = keywordJson.getString("value");
        keywordType = keywordJson.getString("type");
      } catch(JSONException ex){
        ExceptionUtil.throwIllegalArgumentException(ex);
      }
      
      if(keywordType != null && !keywordType.isEmpty()){
        Map<String, Wrapper> map = getFieldsMap();
        if(!keywordType.equals("all")){
          Wrapper w = map.get(keywordType);
          String columnName = w.getColumnName();
//          String tableName = w.getTableName();
          String tableAbbr = "";
          switch (columnName) {
            case "EWM_STATION_ID":
            case "EWM_STATE_WELL_NBR":
            case "LOCAL_WELL_DESIGNATION":
            case "COMPLETION_RPT_NBR":
              tableAbbr = "S";
              break;
            case "SITE_CODE":
              tableAbbr = "M";
              break;
            case "ORG_NAME":
              tableAbbr = "O";
              break;
          }
          query += "( LOWER(" + tableAbbr + "." + columnName + ") = ? ) AND";
          params.add(keywordValue.toLowerCase());
        } 
        else {
          for(Map.Entry<String, Wrapper> entry : map.entrySet()){
            
          }
          query += "("
            + "(LOWER(S.EWM_STATION_ID) LIKE ?) OR"
            + "(LOWER(S.EWM_STATE_WELL_NBR) LIKE ?) OR"
            + "(LOWER(S.LOCAL_WELL_DESIGNATION) LIKE ?) OR"
            + "(LOWER(S.COMPLETION_RPT_NBR) LIKE ?) OR"
            + "(LOWER(M.SITE_CODE) LIKE ?) OR"
            + "(LOWER(O.ORG_NAME) LIKE ?) "
            + " ) AND";
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
          params.add("%" + keywordValue.toLowerCase()+ "%");
        }
      }
    }
    
    if(extent != null && !extent.isEmpty()){
      try {
        JSONArray extentArray = new JSONArray(extent);
        JSONArray min = (JSONArray) extentArray.get(0);
        JSONArray max = (JSONArray) extentArray.get(1);
        query += "( S.LATITUDE BETWEEN ? AND ? ) AND " + 
                 "( S.LONGITUDE BETWEEN ? AND ? )";
        params.add(min.getDouble(1));
        params.add(max.getDouble(1));
        params.add(min.getDouble(0));
        params.add(max.getDouble(0));
      } catch (Exception e) {
        ExceptionUtil.throwIllegalStateException(e);
      } 
    }
    
    if(query.endsWith("AND")){
      query = query.substring(0, query.length()-3);
    }
    //</editor-fold>
    
    zos.putNextEntry(new ZipEntry("Station.csv"));
    CSVWriter writer = new CSVWriter(new OutputStreamWriter(zos));
    
    Shape filteringS = null;
    if(shape!=null){
      Feature feature = EsriJsonUtils.esriGeometryToFeature(shape);
      FeatureSet FS = new FeatureSet(feature);
      FS.reproject(Projections.getWGS84());
      filteringS = FS.getFeatures().get(0).getShape();
    }
    final Shape filteringShape = filteringS;

    final List<String> fields = stationDownloadFields();
    
    this.runQuery(query, params, new QueryDelegate(writer) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        CSVWriter writer = getListener();
        String [] header = fields.toArray(new String[fields.size()]);
        writer.writeNext(header);
        String[] csvRow = new String[fields.size()];
        
        ShapefileWriter shapewriter = new ShapefileWriter();
        FeatureSet resFS = new FeatureSet();
        resFS.setName("PeriodicGWShape");
        List<Field> shapefields = new ArrayList<>();
        shapefields.add(new Field("SITE_CODE", FieldType.Character, 20));
        shapefields.add(new Field("WELL_NAME", FieldType.Character, 20));
        shapefields.add(new Field("SWN", FieldType.Character, 20));
        shapefields.add(new Field("STN_ID", FieldType.Character, 20));
        shapefields.add(new Field("WCR_NO", FieldType.Character, 20));
        shapefields.add(new Field("LATITUDE", FieldType.Character, 20));
        shapefields.add(new Field("LONGITUDE", FieldType.Character, 20));
        shapefields.add(new Field("STN_ORG_ID", FieldType.Character, 20));
        shapefields.add(new Field("STN_ORG_NAME", FieldType.Character, 50));
        shapefields.add(new Field("LOC_DESC", FieldType.Character, 20));
        shapefields.add(new Field("WELL_USE", FieldType.Character, 20));
        shapefields.add(new Field("WELL_TYPE", FieldType.Character, 20));
        shapefields.add(new Field("WELL_DEPTH", FieldType.Character, 20));
        shapefields.add(new Field("TOP_PRF", FieldType.Character, 20));
        shapefields.add(new Field("BOT_PRF", FieldType.Character, 20));
        shapefields.add(new Field("GSE", FieldType.Character, 20));
        shapefields.add(new Field("RPE", FieldType.Character, 20));
        shapefields.add(new Field("RP_DESC", FieldType.Character, 20));
        shapefields.add(new Field("STN_CMT", FieldType.Character, 100));
        shapefields.add(new Field("STN_MOD_DATE", FieldType.Character, 20));
        shapefields.add(new Field("STN_MOD_USER", FieldType.Character, 20));
        shapefields.add(new Field("WLM_METHOD", FieldType.Character, 20));
        shapefields.add(new Field("WLM_ACC", FieldType.Character, 20));
        shapefields.add(new Field("COUNTY", FieldType.Character, 20));
        shapefields.add(new Field("HR", FieldType.Character, 20));
        shapefields.add(new Field("BASIN_CODE", FieldType.Character, 20));
        shapefields.add(new Field("BASIN_NAME", FieldType.Character, 20));
        shapefields.add(new Field("BASIN_RO", FieldType.Character, 20));
        resFS.setFields(shapefields);

        while (rs.next()){
          if( filteringShape!= null){
            Shape s = new Shape(new CoordXY(rs.getDouble("LONGITUDE"), rs.getDouble("LATITUDE")));
            if(filteringShape.intersection(s).isPresent()){
              if(rs.getString("STN_ID") != null){
                Feature f = new Feature(rs.getDouble("LONGITUDE"), rs.getDouble("LATITUDE"));
                //<editor-fold defaultstate="collapsed" desc="Shapefile Attributes">
                HashMap<String, String> attrs = new HashMap<>();
                attrs.put("SITE_CODE", rs.getString("SITE_CODE"));
                attrs.put("WELL_NAME", rs.getString("WELL_NAME"));
                attrs.put("SWN", rs.getString("SWN"));
                attrs.put("STN_ID", rs.getString("STN_ID"));
                attrs.put("WCR_NO", rs.getString("WCR_NO"));
                attrs.put("LATITUDE", rs.getString("LATITUDE"));
                attrs.put("LONGITUDE", rs.getString("LONGITUDE"));
                attrs.put("STN_ORG_ID", rs.getString("STN_ORG_ID"));
                attrs.put("STN_ORG_NAME", rs.getString("STN_ORG_NAME"));
                attrs.put("LOC_DESC", rs.getString("LOC_DESC"));
                attrs.put("WELL_USE", rs.getString("WELL_USE"));
                attrs.put("WELL_TYPE", rs.getString("WELL_TYPE"));
                attrs.put("WELL_DEPTH", rs.getString("WELL_DEPTH"));
                attrs.put("TOP_PRF", rs.getString("TOP_PRF"));
                attrs.put("BOT_PRF", rs.getString("BOT_PRF"));
                attrs.put("GSE", rs.getString("GSE"));
                attrs.put("RPE", rs.getString("RPE"));
                attrs.put("RP_DESC", rs.getString("RP_DESC"));
                attrs.put("STN_CMT", rs.getString("STN_CMT"));
                attrs.put("STN_MOD_DATE", rs.getString("STN_MOD_DATE"));
                attrs.put("STN_MOD_USER", rs.getString("STN_MOD_USER"));
                attrs.put("WLM_METHOD", rs.getString("WLM_METHOD"));
                attrs.put("WLM_ACC", rs.getString("WLM_ACC"));
                attrs.put("COUNTY", rs.getString("COUNTY"));
                attrs.put("HR", rs.getString("HR"));
                attrs.put("BASIN_CODE", rs.getString("BASIN_CODE"));
                attrs.put("BASIN_NAME", rs.getString("BASIN_NAME"));
                attrs.put("BASIN_RO", rs.getString("BASIN_RO"));
                //</editor-fold>
                f.addAttributes(attrs);
                resFS.getFeatures().add(f);
                stationIds.add(rs.getString("STN_ID"));
              }
              //write CSV
              for(int j=0; j<fields.size(); j++){
                String field = fields.get(j);
                csvRow[j] = rs.getString(field) != null ? rs.getString(field): "";
              }
              writer.writeNext(csvRow);
            }
          }
          else{
            if(rs.getString("STN_ID") != null){
                Feature f = new Feature(rs.getDouble("LONGITUDE"), rs.getDouble("LATITUDE"));
                //<editor-fold defaultstate="collapsed" desc="Shapefile Attributes">
                HashMap<String, String> attrs = new HashMap<>();
                attrs.put("SITE_CODE", rs.getString("SITE_CODE"));
                attrs.put("WELL_NAME", rs.getString("WELL_NAME"));
                attrs.put("SWN", rs.getString("SWN"));
                attrs.put("STN_ID", rs.getString("STN_ID"));
                attrs.put("WCR_NO", rs.getString("WCR_NO"));
                attrs.put("LATITUDE", rs.getString("LATITUDE"));
                attrs.put("LONGITUDE", rs.getString("LONGITUDE"));
                attrs.put("STN_ORG_ID", rs.getString("STN_ORG_ID"));
                attrs.put("STN_ORG_NAME", rs.getString("STN_ORG_NAME"));
                attrs.put("LOC_DESC", rs.getString("LOC_DESC"));
                attrs.put("WELL_USE", rs.getString("WELL_USE"));
                attrs.put("WELL_TYPE", rs.getString("WELL_TYPE"));
                attrs.put("WELL_DEPTH", rs.getString("WELL_DEPTH"));
                attrs.put("TOP_PRF", rs.getString("TOP_PRF"));
                attrs.put("BOT_PRF", rs.getString("BOT_PRF"));
                attrs.put("GSE", rs.getString("GSE"));
                attrs.put("RPE", rs.getString("RPE"));
                attrs.put("RP_DESC", rs.getString("RP_DESC"));
                attrs.put("STN_CMT", rs.getString("STN_CMT"));
                attrs.put("STN_MOD_DATE", rs.getString("STN_MOD_DATE"));
                attrs.put("STN_MOD_USER", rs.getString("STN_MOD_USER"));
                attrs.put("WLM_METHOD", rs.getString("WLM_METHOD"));
                attrs.put("WLM_ACC", rs.getString("WLM_ACC"));
                attrs.put("COUNTY", rs.getString("COUNTY"));
                attrs.put("HR", rs.getString("HR"));
                attrs.put("BASIN_CODE", rs.getString("BASIN_CODE"));
                attrs.put("BASIN_NAME", rs.getString("BASIN_NAME"));
                attrs.put("BASIN_RO", rs.getString("BASIN_RO"));
                //</editor-fold>
                f.addAttributes(attrs);
                resFS.getFeatures().add(f);
                stationIds.add(rs.getString("STN_ID"));
            }
            for(int j=0; j<fields.size(); j++){
                String field = fields.get(j);
                csvRow[j] = rs.getString(field) != null ? rs.getString(field): "";
              }
            writer.writeNext(csvRow);
          }
        }
        writer.flush();
        zos.closeEntry();
        //write shapefile
        shapewriter.write(resFS, zos);
      }
    });
    return stationIds;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="filterByShape">
  public static JSONArray filterByShape(JSONArray queryResult, JSONObject geometry) throws ShapefileException, ProjectionException, IOException{
    JSONArray result = new JSONArray();
    FeatureSet filteringFS = null;
    final HashMap<String, JSONObject> allFeaturesMap = new HashMap<>();
    try {
      Feature feature = EsriJsonUtils.esriGeometryToFeature(geometry);
      filteringFS = new FeatureSet(feature);
      FeatureSet queryResults = new FeatureSet();
      for (int i=0;i<queryResult.length();i++){
        JSONObject r = queryResult.getJSONObject(i);
        String stationId = Integer.toString(r.getInt("STN_ID"));
        Feature f = new Feature(r.getDouble("LONGITUDE"), r.getDouble("LATITUDE"));
        f.getAttributes().put("STN_ID", stationId);
        queryResults.getFeatures().add(f);
        allFeaturesMap.put(stationId, r);
      }
      filteringFS.intersection(queryResults, new IntersectDelegate(result) {
        @Override
        public void onIntersect(Feature feature, Feature otherFeature, Feature resultFeature) {
          JSONArray res = this.getListener();
          String stationId = otherFeature.getAttributes().get("STN_ID");
          res.put(allFeaturesMap.get(stationId));
        }
      });
    } catch (Exception e) {
    }
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Build Seach Query">
  private String buildSearchQuery(){
    String query = "SELECT COUNT(*) OVER() as COUNT, M.SITE_CODE, "
      + "O.ORG_NAME, "
      + "SUT.EWM_STATION_USE_DESC, "
      + "WCT.EWM_WELL_COMPLETION_DESC, "
      + "perforation.PERFORATION_TOP_MSRMNT, perforation.PERFORATION_BOTTOM_MSRMNT, "
      + "S.* " 
      + "FROM " + EntityUtil.getTableName(Station.class) + " S " 
      + "LEFT JOIN " + EntityUtil.getTableName(MasterSite.class) + " M "
      + "ON M.EWM_MASTER_SITE_ID = S.EWM_MASTER_SITE_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(Organization.class) + " O "
      + "ON O.ORG_ID = S.PRIMARY_WELL_ORG_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(StationUseType.class) + " SUT "
      + "ON SUT.EWM_STATION_USE_ID = S.EWM_STATION_USE_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(WellCompletionType.class) + " WCT " 
      + "ON WCT.EWM_WELL_COMPLETION_ID = S.EWM_WELL_COMPLETION_ID "
      
      //Station Id, min(Top Perforation), max(Bottom Perforation)
      + "LEFT JOIN ("
      + "SELECT EWM_STATION_ID, MIN(PERFORATION_TOP_MSRMNT) PERFORATION_TOP_MSRMNT, MAX(PERFORATION_BOTTOM_MSRMNT) PERFORATION_BOTTOM_MSRMNT "
      + "FROM (" 
      + "SELECT S1.EWM_STATION_ID, SP.PERFORATION_TOP_MSRMNT, SP.PERFORATION_BOTTOM_MSRMNT "
      + "FROM " + EntityUtil.getTableName(Station.class) + " S1 "
      + "LEFT JOIN " + EntityUtil.getTableName(StationPerforation.class) + " SP " 
      + "ON SP.EWM_STATION_ID = S1.EWM_STATION_ID )"
      + "GROUP BY EWM_STATION_ID ) perforation "
      + "ON perforation.EWM_STATION_ID = S.EWM_STATION_ID "
      + "WHERE ";
    return query;
  }
  //</editor-fold>  
  
  //<editor-fold defaultstate="collapsed" desc="Get Single Well Station Details (Group B)">
  public JSONArray getSingleWellStationData(String stationId) 
  {
    JSONArray queryRes = new JSONArray();
    final JSONArray finalRes = new JSONArray();
    String query = "SELECT M.SITE_CODE, "
      + "O.ORG_NAME, "
      + "SUT.EWM_STATION_USE_DESC, "
      + "WCT.EWM_WELL_COMPLETION_DESC, "
      + "perforation.PERFORATION_TOP_MSRMNT, perforation.PERFORATION_BOTTOM_MSRMNT, "
      + "S.* " 
      + "FROM " + EntityUtil.getTableName(Station.class) + " S " 
      + "LEFT JOIN " + EntityUtil.getTableName(MasterSite.class) + " M "
      + "ON M.EWM_MASTER_SITE_ID = S.EWM_MASTER_SITE_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(Organization.class) + " O "
      + "ON O.ORG_ID = S.PRIMARY_WELL_ORG_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(StationUseType.class) + " SUT "
      + "ON SUT.EWM_STATION_USE_ID = S.EWM_STATION_USE_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(WellCompletionType.class) + " WCT " 
      + "ON WCT.EWM_WELL_COMPLETION_ID = S.EWM_WELL_COMPLETION_ID "
      
      + "LEFT JOIN ("
      + "SELECT EWM_STATION_ID, MIN(PERFORATION_TOP_MSRMNT) PERFORATION_TOP_MSRMNT, MAX(PERFORATION_BOTTOM_MSRMNT) PERFORATION_BOTTOM_MSRMNT "
      + "FROM (" 
      + "SELECT S1.EWM_STATION_ID, SP.PERFORATION_TOP_MSRMNT, SP.PERFORATION_BOTTOM_MSRMNT "
      + "FROM " + EntityUtil.getTableName(Station.class) + " S1 "
      + "LEFT JOIN " + EntityUtil.getTableName(StationPerforation.class) + " SP " 
      + "ON SP.EWM_STATION_ID = S1.EWM_STATION_ID )"
      + "GROUP BY EWM_STATION_ID ) perforation "
      + "ON perforation.EWM_STATION_ID = S.EWM_STATION_ID "
      + "WHERE S.EWM_STATION_ID = ? ";
    
    List<Object> params = new ArrayList<>();
    params.add(stationId);

    this.runQuery(query, params, new QueryDelegate(queryRes) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        while (rs.next()){
          JSONObject localwell = new JSONObject();
          localwell.put("SITE_CODE", rs.getString("SITE_CODE")!=null?rs.getString("SITE_CODE"):"");
          localwell.put("WELL_NAME", rs.getString("LOCAL_WELL_DESIGNATION")!=null?rs.getString("LOCAL_WELL_DESIGNATION"):""); 
          localwell.put("SWN", rs.getString("EWM_STATE_WELL_NBR")!=null?rs.getString("EWM_STATE_WELL_NBR"):"");
          localwell.put("STN_ID", rs.getString("EWM_STATION_ID")!=null?rs.getString("EWM_STATION_ID"):"");
          localwell.put("WCR_NO", rs.getString("COMPLETION_RPT_NBR")!=null?rs.getString("COMPLETION_RPT_NBR"):"");
          localwell.put("LATITUDE", rs.getString("LATITUDE")!=null?rs.getString("LATITUDE"):"");
          localwell.put("LONGITUDE", rs.getString("LONGITUDE")!=null?rs.getString("LONGITUDE"):"");
          localwell.put("STN_ORG_ID", rs.getString("PRIMARY_WELL_ORG_ID")!=null?rs.getString("PRIMARY_WELL_ORG_ID"):"");
          localwell.put("STN_ORG_NAME", rs.getString("ORG_NAME")!=null?rs.getString("ORG_NAME"):"");
          localwell.put("LOC_DESC", rs.getString("WELL_LOCATION_DESC")!=null?rs.getString("WELL_LOCATION_DESC"):"");
          localwell.put("WELL_USE", rs.getString("EWM_STATION_USE_DESC")!=null?rs.getString("EWM_STATION_USE_DESC"):"");
          localwell.put("WELL_TYPE", rs.getString("EWM_WELL_COMPLETION_DESC")!=null?rs.getString("EWM_WELL_COMPLETION_DESC"):"");
          localwell.put("WELL_DEPTH", rs.getString("TOTAL_DEPTH_FT"));
          localwell.put("TOP_PRF", rs.getString("PERFORATION_TOP_MSRMNT")!=null?rs.getString("PERFORATION_TOP_MSRMNT"):"");
          localwell.put("BOT_PRF", rs.getString("PERFORATION_BOTTOM_MSRMNT")!=null?rs.getString("PERFORATION_BOTTOM_MSRMNT"):"");
          localwell.put("GSE", rs.getString("LATEST_GS_ELEVATION")!=null?rs.getString("LATEST_GS_ELEVATION"):"");
          localwell.put("RPE", rs.getString("LATEST_RP_ELEVATION")!=null?rs.getString("LATEST_RP_ELEVATION"):"");
          localwell.put("RP_DESC", rs.getString("RP_DESC")!=null?rs.getString("RP_DESC"):"");
          localwell.put("STN_CMT", rs.getString("ADDL_COMMENTS")!=null?rs.getString("ADDL_COMMENTS"):"");     
          finalRes.put(localwell);
        }
      }
    });
    return finalRes;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="writeToShapefile">
  public StationCollection writeToShapefile(ZipOutputStream zstream) throws Exception {
    try {
      FeatureSet fs = new FeatureSet();
      fs.setName("PeriodicGWShape");
      List<Field> fields = new ArrayList<>();
      fields.add(new Field("ewmStationId", FieldType.Character, 6));
      fs.setFields(fields);
      for (Station station : this){
        Feature feature = new Feature(station.getLongitude(), station.getLatitude());
        fs.getFeatures().add(feature);
        HashMap<String, String> attrs = new HashMap<>();
        attrs.put("STN_ID", station.getEwmStationId().toString());
        feature.addAttributes(attrs);
        // todo: add attributes
      }
      ShapefileWriter sw = new ShapefileWriter();
      sw.write(fs, zstream);
    } catch (Exception e) {
      e.printStackTrace(System.err);
      throw e;
    }
    return this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="writeExportToCsv">
  public StationCollection writeExportToCsv(OutputStream ostream, List<Integer> stationIds) {
    CSVWriter writer = new CSVWriter(new OutputStreamWriter(ostream));
    String query = buildExportQuery();
    this.runQuery(query, stationIds, new QueryDelegate(writer) {
        @Override
        public void handle(ResultSet rs) throws Exception {
          CSVWriter writer = getListener();
          writer.writeAll(rs, true);
          writer.flush();
        }
    });
    return this;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Build Export Query">
  private String buildExportQuery() {
    
    String query = "SELECT M.SITE_CODE as SITE_CODE, "
      + "S.LOCAL_WELL_DESIGNATION as WELL_NAME, "
      + "S.EWM_STATE_WELL_NBR as SWN, "
      + "S.EWM_STATION_ID as STN_ID, "
      + "S.COMPLETION_RPT_NBR as WCR_NO, " 
      + "S.LATITUDE, "
      + "S.LONGITUDE, "
      + "S.PRIMARY_WELL_ORG_ID as STN_ORG_ID, "
      + "O.ORG_NAME as STN_ORG_NAME, "
      + "S.WELL_LOCATION_DESC as LOC_DESC, "
      + "SUT.EWM_STATION_USE_DESC as WELL_USE, "
      + "WCT.EWM_WELL_COMPLETION_DESC as WELL_TYPE, "
      + "S.TOTAL_DEPTH_FT as WELL_DEPTH, "
      + "perforation.PERFORATION_TOP_MSRMNT as TOP_PRF, "
      + "perforation.PERFORATION_BOTTOM_MSRMNT as BOT_PRF, "
      + "S.LATEST_GS_ELEVATION as GSE, "
      + "S.LATEST_RP_ELEVATION as RPE, "
      + "S.RP_DESC as RP_DESC, "
      + "S.ADDL_COMMENTS as STN_CMT, "
      + "S.MODIFIED_DATE as STN_MOD_DATE, "
      + "S.MODIFIED_USER as STN_MOD_USER, "
      + "MMT.EWM_MEASUREMENT_METHOD_DESC as WLM_METHOD, "
      + "MAT.EWM_MEASUREMENT_ACCURACY_DESC as WLM_ACC, "
      + "C.COUNTY_NAME as COUNTY, "
      + "BR.EWM_BASIN_REGION_DESC as HR, "
      + "B.EWM_BASIN_CD as BASIN_CODE, "
      + "B.EWM_BASIN_DESC as BASIN_NAME, "
      + "B.REG_OFFICE_ID as BASIN_RO "
      + "FROM " + EntityUtil.getTableName(Station.class) + " S " 
      + "LEFT JOIN " + EntityUtil.getTableName(MasterSite.class) + " M "
      + "ON M.EWM_MASTER_SITE_ID = S.EWM_MASTER_SITE_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(Organization.class) + " O "
      + "ON O.ORG_ID = S.PRIMARY_WELL_ORG_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(StationUseType.class) + " SUT "
      + "ON SUT.EWM_STATION_USE_ID = S.EWM_STATION_USE_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(WellCompletionType.class) + " WCT " 
      + "ON WCT.EWM_WELL_COMPLETION_ID = S.EWM_WELL_COMPLETION_ID "
      + "LEFT JOIN ("
      + "SELECT EWM_STATION_ID, MIN(PERFORATION_TOP_MSRMNT) PERFORATION_TOP_MSRMNT, MAX(PERFORATION_BOTTOM_MSRMNT) PERFORATION_BOTTOM_MSRMNT "
      + "FROM (" 
      + "SELECT S1.EWM_STATION_ID, SP.PERFORATION_TOP_MSRMNT, SP.PERFORATION_BOTTOM_MSRMNT "
      + "FROM " + EntityUtil.getTableName(Station.class) + " S1 "
      + "LEFT JOIN " + EntityUtil.getTableName(StationPerforation.class) + " SP " 
      + "ON SP.EWM_STATION_ID = S1.EWM_STATION_ID )"
      + "GROUP BY EWM_STATION_ID ) perforation "
      + "ON perforation.EWM_STATION_ID = S.EWM_STATION_ID "
//      + "LEFT JOIN " + EntityUtil.getTableName(StationPerforation.class) + " SP "
//      + "ON SP.EWM_STATION_ID = S.EWM_STATION_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(MeasurementMethodType.class) + " MMT "
      + "ON MMT.EWM_MEASUREMENT_METHOD_TYPE_ID = S.VERTICAL_MEASURE_METHOD_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(MeasurementAccuracyType.class) + " MAT "
      + "ON MAT.EWM_MEASURE_ACCURACY_TYPE_ID = S.VERTICAL_MEASURE_ACCURACY_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(County.class) + " C "
      + "ON C.COUNTY_ID = S.COUNTY_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(Basin.class) + " B "
      + "ON B.EWM_BASIN_ID = S.EWM_BASIN_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(BasinRegion.class) + " BR "
      + "ON BR.EWM_BASIN_REGION_ID = B.EWM_BASIN_REGION_ID "
      + "WHERE ";
    
    //<editor-fold defaultstate="collapsed" desc="original where">
//    String s = "?,";
//    for(int n=0; n<N; n++){ //n: index of sublist 
//      String in = String.format("%0" + 1000 + "d", 0).replace("0",s);
//      in = in.substring(0, in.length()-1);
//      query += "S.EWM_STATION_ID in (%s)"; 
//      query = String.format(query, in);
//      query += " OR ";
//    }
//    
//    if(m != 0){
//      query += "S.EWM_STATION_ID in (%s)"; 
//      String in = String.format("%0" + m + "d", 0).replace("0",s);
//      in = in.substring(0, in.length()-1);
//      query = String.format(query, in);
//    }
//</editor-fold>
//    query += "ORDER BY S.EWM_STATION_ID";
    return query;
  }  
//</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="buildExportAllQuery">
  private String buildExportAllQuery(){
    String query = "SELECT M.SITE_CODE as SITE_CODE, "
      + "S.LOCAL_WELL_DESIGNATION as WELL_NAME, "
      + "S.EWM_STATE_WELL_NBR as SWN, "
      + "S.EWM_STATION_ID as STN_ID, "
      + "S.COMPLETION_RPT_NBR as WCR_NO, " 
      + "S.LATITUDE, "
      + "S.LONGITUDE, "
      + "S.PRIMARY_WELL_ORG_ID as STN_ORG_ID, "
      + "O.ORG_NAME as STN_ORG_NAME, "
      + "S.WELL_LOCATION_DESC as LOC_DESC, "
      + "SUT.EWM_STATION_USE_DESC as WELL_USE, "
      + "WCT.EWM_WELL_COMPLETION_DESC as WELL_TYPE, "
      + "S.TOTAL_DEPTH_FT as WELL_DEPTH, "
      + "perforation.PERFORATION_TOP_MSRMNT as TOP_PRF, "
      + "perforation.PERFORATION_BOTTOM_MSRMNT as BOT_PRF, "
      + "S.LATEST_GS_ELEVATION as LAST_GSE, "
      + "S.LATEST_RP_ELEVATION as LAST_RPE, "
      + "S.RP_DESC as RP_DESC, "
      + "S.ADDL_COMMENTS as STN_CMT, "
      + "S.MODIFIED_DATE as STN_MOD_DATE, "
      + "S.MODIFIED_USER as STN_MOD_USER, "
      + "MMT.EWM_MEASUREMENT_METHOD_DESC as WLM_METHOD, "
      + "MAT.EWM_MEASUREMENT_ACCURACY_DESC as WLM_ACC, "
      + "C.COUNTY_NAME as COUNTY, "
      + "BR.EWM_BASIN_REGION_DESC as HR, "
      + "B.EWM_BASIN_CD as BASIN_CODE, "
      + "B.EWM_BASIN_DESC as BASIN_NAME, "
      + "B.REG_OFFICE_ID as BASIN_RO "
      + "FROM " + EntityUtil.getTableName(Station.class) + " S " 
      + "LEFT JOIN " + EntityUtil.getTableName(MasterSite.class) + " M "
      + "ON M.EWM_MASTER_SITE_ID = S.EWM_MASTER_SITE_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(Organization.class) + " O "
      + "ON O.ORG_ID = S.PRIMARY_WELL_ORG_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(StationUseType.class) + " SUT "
      + "ON SUT.EWM_STATION_USE_ID = S.EWM_STATION_USE_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(WellCompletionType.class) + " WCT " 
      + "ON WCT.EWM_WELL_COMPLETION_ID = S.EWM_WELL_COMPLETION_ID "
      + "LEFT JOIN ("
      + "SELECT EWM_STATION_ID, MIN(PERFORATION_TOP_MSRMNT) PERFORATION_TOP_MSRMNT, MAX(PERFORATION_BOTTOM_MSRMNT) PERFORATION_BOTTOM_MSRMNT "
      + "FROM (" 
      + "SELECT S1.EWM_STATION_ID, SP.PERFORATION_TOP_MSRMNT, SP.PERFORATION_BOTTOM_MSRMNT "
      + "FROM " + EntityUtil.getTableName(Station.class) + " S1 "
      + "LEFT JOIN " + EntityUtil.getTableName(StationPerforation.class) + " SP " 
      + "ON SP.EWM_STATION_ID = S1.EWM_STATION_ID )"
      + "GROUP BY EWM_STATION_ID ) perforation "
      + "ON perforation.EWM_STATION_ID = S.EWM_STATION_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(MeasurementMethodType.class) + " MMT "
      + "ON MMT.EWM_MEASUREMENT_METHOD_TYPE_ID = S.VERTICAL_MEASURE_METHOD_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(MeasurementAccuracyType.class) + " MAT "
      + "ON MAT.EWM_MEASURE_ACCURACY_TYPE_ID = S.VERTICAL_MEASURE_ACCURACY_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(County.class) + " C "
      + "ON C.COUNTY_ID = S.COUNTY_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(Basin.class) + " B "
      + "ON B.EWM_BASIN_ID = S.EWM_BASIN_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(BasinRegion.class) + " BR "
      + "ON BR.EWM_BASIN_REGION_ID = B.EWM_BASIN_REGION_ID "
      + "ORDER BY S.EWM_STATION_ID";
    return query;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="loadTest">
  public StationCollection loadTEST(String whereStatement) {
    String query = "SELECT M.SITE_CODE, "
        + "S.* "
        + "FROM " + EntityUtil.getTableName(Station.class) + " S "
        + "LEFT JOIN " + EntityUtil.getTableName(MasterSite.class) + " M "
        + "ON M.EWM_MASTER_SITE_ID = S.EWM_MASTER_SITE_ID "
        + whereStatement + " "
        + "ORDER BY S.EWM_STATION_ID";
    this.runQuery(query);
    return this;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="writeExportToCsvTEST">
  public StationCollection writeExportToCsvTEST(OutputStream ostream, String whereStatement) {
    CSVWriter writer = new CSVWriter(new OutputStreamWriter(ostream));
    String query = "SELECT M.SITE_CODE, "
        + "S.* "
        + "FROM " + EntityUtil.getTableName(Station.class) + " S "
        + "LEFT JOIN " + EntityUtil.getTableName(MasterSite.class) + " M "
        + "ON M.EWM_MASTER_SITE_ID = S.EWM_MASTER_SITE_ID "
        + whereStatement + " "
        + "ORDER BY S.EWM_STATION_ID";
    this.runQuery(query, null, new QueryDelegate(writer) {
        @Override
        public void handle(ResultSet rs) throws Exception {
          CSVWriter writer = getListener();
          writer.writeAll(rs, true);
          writer.flush();
        }
      }
    );
    return this;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="writeAllStationsToCsv">
  public StationCollection writeAllStationsToCsv(OutputStream os) {
    String query = buildExportAllQuery();
    CSVWriter writer = new CSVWriter(new OutputStreamWriter(os));
    this.runQuery(query, null, new QueryDelegate(writer) {
        @Override
        public void handle(ResultSet rs) throws Exception {       
          CSVWriter writer = getListener();
          writer.writeAll(rs, true);
          writer.flush();
        }
      }
    );
    return this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Extent Query">
  public JSONArray getExtentWithFilteredResult(Double xmin, Double ymin, Double xmax, Double ymax, JSONObject queryData) throws ShapefileException, IOException, ProjectionException {
    String keyword = "";
    String startDate = "";
    String endDate = "";
    String minWellDepth = "";
    String maxWellDepth = "";
    Boolean acceptNullDepth = false;
    JSONArray wellUseType = new JSONArray();
    if(queryData != null){
      keyword = queryData.has("keyword")?queryData.getJSONObject("keyword").toString():"";
      startDate = queryData.has("startDate")?queryData.getString("startDate"):"";
      endDate = queryData.has("endDate")?queryData.getString("endDate"):"";
      minWellDepth = queryData.has("minDepth")?queryData.getString("minDepth"):""; 
      maxWellDepth = queryData.has("maxDepth")?queryData.getString("maxDepth"):"";
      acceptNullDepth = queryData.has("nullDepth")?queryData.getBoolean("nullDepth"):false;
      wellUseType = queryData.has("wellUseType")?queryData.getJSONArray("wellUseType") : wellUseType;
    }
    String extent = "[ ["+xmin+","+ymin+"]," + "["+xmax+","+ymax+"] ]" ;
    StationCollection stations = new StationCollection();
    JSONArray result = stations.query(keyword, startDate, endDate, minWellDepth, maxWellDepth, acceptNullDepth, wellUseType, extent);
    
    JSONArray filteredResult = doSpatialFilter(result, queryData);
    return filteredResult;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Extent Query (Alex's version) -- not used anymore">
//  public JSONArray getExtentWithFilteredResult(Double xmin, Double ymin, Double xmax, Double ymax, JSONObject queryData) throws ShapefileException, IOException, ProjectionException {
//    JSONArray result = new JSONArray(); 
//    String whereStatement = "";
//      
//    String query = "SELECT S.LATITUDE, S.LONGITUDE, "
//        + "S.EWM_STATION_ID, "
//        + "MS.SITE_CODE, "
//        + "EWM_STATE_WELL_NBR, "
//        + "TOTAL_DEPTH_FT, "
//        + "COMPLETION_RPT_NBR, "
//        + "S.LOCAL_WELL_DESIGNATION, "
//        + "LATEST_RP_ELEVATION, LATEST_GS_ELEVATION, "
//        + "S.EWM_BASIN_ID, EWM_BASIN_CD, EWM_BASIN_DESC, "
//        + "EWM_STATION_USE_DESC "
//        + "FROM " + EntityUtil.getTableName(Station.class) + " S "
//        + "LEFT JOIN " + EntityUtil.getTableName(StationUseType.class) + " SUT "
//        + "ON SUT.EWM_STATION_USE_ID = S.EWM_STATION_USE_ID "
//        + "LEFT JOIN " + EntityUtil.getTableName(Basin.class) + " B "
//        + "ON B.EWM_BASIN_ID = S.EWM_BASIN_ID "
//        + "LEFT JOIN " + EntityUtil.getTableName(MasterSite.class) + " MS "
//        + "ON S.EWM_MASTER_SITE_ID = MS.EWM_MASTER_SITE_ID ";
//    
//    List<Object> params = new ArrayList<>();
//    
//    if (queryData != null){
//      // date range
//      // well depth range
//      // accept well depth
//      // well use list
//      // keyword
//      String startDate = "";
//      String endDate = "";
//      String minDepth = "";
//      String maxDepth = "";
//      String nullDepth = "";
//      String wellUse = "";
//      String keyword = "";
//      if (queryData.has("startDate")){
//        startDate = "MEASUREMENT_DT >= TO_DATE(?, 'MM/DD/YYYY')";
//        params.add(queryData.getString("startDate"));
//      }
//      if (queryData.has("endDate")){
//        endDate = "MEASUREMENT_DT <= TO_DATE(?, 'MM/DD/YYYY')";
//        params.add(queryData.getString("endDate"));
//      }
//      if (queryData.has("minDepth")){
//        minDepth = "TOTAL_DEPTH_FT >= ?";
//        params.add(queryData.getInt("minDepth"));
//      }
//      if (queryData.has("maxDepth")){
//        maxDepth = "TOTAL_DEPTH_FT <= ?";
//        params.add(queryData.getInt("maxDepth"));
//      }
//      
//      nullDepth = "TOTAL_DEPTH_FT is not NULL";
//      String depthANDorOR = " AND ";
//      if (queryData.has("nullDepth")){
//        if (queryData.getBoolean("nullDepth")){
//          nullDepth = "TOTAL_DEPTH_FT is NULL";
//          depthANDorOR = " OR ";
//        }
//      }
//      if (queryData.has("wellUseType")){
//        JSONArray wellUses = queryData.getJSONArray("wellUseType");
//        String or = "";
//        for (int i = 0; i<wellUses.length(); i++){
//          wellUse += or;
//          or = " OR ";
//          wellUse += "SUT.EWM_STATION_USE_DESC = ?";
//          params.add(wellUses.getString(i));
//        }
//      }
//      if (queryData.has("keyword")){
//        JSONObject keywordObj = queryData.getJSONObject("keyword");
//        boolean isAll = false;
//        String or = "";
//        switch (keywordObj.getString("type")){
//          case "all":
//            isAll = true;
//          case "state well no":
//            keyword += or;
//            keyword += "EWM_STATE_WELL_NBR = ?";
//            params.add(keywordObj.getString("value"));
//            or = " OR ";
//            if (!isAll)
//              break;
//          case "local well id":
//            keyword += or;
//            keyword += "LOCAL_WELL_DESIGNATION = ?";
//            params.add(keywordObj.getString("value"));
//            or = " OR ";
//            if (!isAll)
//              break;
//        }
//      }
//      if (!startDate.isEmpty() || !startDate.isEmpty()){
//        query += "inner join "
//            + "(select ewm_station_id from "
//            + EntityUtil.getTableName(ElevationDataReading.class) 
//            + " where (" + startDate + (!endDate.isEmpty() && !startDate.isEmpty() ? " AND " : "") + endDate + "))"
//            + " d on d.ewm_station_id = s.ewm_station_id ";
//       
//      }
//      if (!minDepth.isEmpty() || !maxDepth.isEmpty()){
//        whereStatement += " (" + minDepth + (!maxDepth.isEmpty() && !minDepth.isEmpty() ? " AND " : "") + maxDepth;
//        if (!nullDepth.isEmpty()){
//          whereStatement += depthANDorOR + nullDepth;
//        }
//        whereStatement += ") AND ";
//      }
//      if (!wellUse.isEmpty()){
//        whereStatement += " (" + wellUse + ") AND ";
//      }
//      if (!keyword.isEmpty()){
//        whereStatement += " (" + keyword + ") AND ";
//      }
//    }
//    
//    query += " WHERE " + whereStatement +
//        " S.LONGITUDE >= ? "
//        + "AND S.LONGITUDE <= ? "
//        + "AND S.LATITUDE >= ? "
//        + "AND S.LATITUDE <= ? ";
//    
//    params.add(xmin);
//    params.add(xmax);
//    params.add(ymin);
//    params.add(ymax);
//    
//    this.runQuery(query, params, new QueryDelegate(result) {
//      @Override
//      public void handle(ResultSet rs) throws Exception {
//        JSONArray result = (JSONArray)this.getListener();
//        while (rs.next()){
//          result.put(parsePointRS(rs));
//        }
//      }
//    });
//    
//    JSONArray filteredResult = doSpatialFilter(result, queryData);
//    
//    return filteredResult;
//  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="doSpatialFilter">
  private static JSONArray doSpatialFilter(JSONArray queryResult, JSONObject queryData) throws ShapefileException, ProjectionException, IOException{
    JSONArray result = new JSONArray();
    FeatureSet filteringFS = null;
    final HashMap<String, JSONObject> allFeaturesMap = new HashMap<>();
    if (queryData != null && queryData.has("shapeDrawn")){
      Feature feature = EsriJsonUtils.esriGeometryToFeature(queryData.getJSONObject("shapeDrawn"));
      filteringFS = new FeatureSet(feature);
      FeatureSet queryResults = new FeatureSet();
      for (int i=0;i<queryResult.length();i++){
        JSONObject r = queryResult.getJSONObject(i);
        String stationId = Integer.toString(r.getInt("STN_ID"));
        Feature f = new Feature(r.getDouble("LONGITUDE"), r.getDouble("LATITUDE"));
        f.getAttributes().put("STN_ID", stationId);
        queryResults.getFeatures().add(f);
        allFeaturesMap.put(stationId, r);
      }
      filteringFS.intersection(queryResults, new IntersectDelegate(result) {
        @Override
        public void onIntersect(Feature feature, Feature otherFeature, Feature resultFeature) {
          JSONArray res = this.getListener();
          String stationId = otherFeature.getAttributes().get("STN_ID");
          res.put(allFeaturesMap.get(stationId));
        }
      });
    } else {
      result = queryResult;
    }
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="stationExportFields">
  public static List<String> stationDownloadFields() {
    List<String> headerNames = new ArrayList<String>();
    headerNames.add("SITE_CODE");
    headerNames.add("WELL_NAME");    
    headerNames.add("SWN");
    headerNames.add("STN_ID");
    headerNames.add("WCR_NO");
    headerNames.add("LATITUDE");
    headerNames.add("LONGITUDE");
    headerNames.add("STN_ORG_ID");
    headerNames.add("STN_ORG_NAME");
    headerNames.add("LOC_DESC");
    headerNames.add("WELL_USE");
    headerNames.add("WELL_TYPE");
    headerNames.add("WELL_DEPTH");
    headerNames.add("TOP_PRF");
    headerNames.add("BOT_PRF");
    headerNames.add("GSE");
    headerNames.add("RPE");
    headerNames.add("RP_DESC");
    headerNames.add("STN_CMT");
    headerNames.add("STN_MOD_DATE");
    headerNames.add("STN_MOD_USER");
    headerNames.add("WLM_METHOD");
    headerNames.add("WLM_ACC");
    headerNames.add("COUNTY");
    headerNames.add("HR");
    headerNames.add("BASIN_CODE");
    headerNames.add("BASIN_NAME");
    headerNames.add("BASIN_RO");    
    return headerNames;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="exportDescriptionMap">
  public static HashMap<String, String[]> exportDescriptionMap() {
    HashMap<String, String[]> result = new HashMap<>();
    result.put("SITE_CODE", new String[]{"Site Code", "Char"});
    result.put("WELL_NAME", new String[]{"Local Well Name", "Char"});
    result.put("SWN", new String[]{"State Well Number", "Char"});
    result.put("STN_ID", new String[]{"Station ID", "Number"});
    result.put("WCR_NO", new String[]{"WCR Number", "Number"});
    result.put("LATITUDE", new String[]{"Latitude", "Number"});
    result.put("LONGITUDE", new String[]{"Longitude", "Number"});    
    result.put("STN_ORG_ID", new String[]{"Station Organization ID", "Number"});
    result.put("STN_ORG_NAME", new String[]{"Station Organization Name", "Char"});
    result.put("LOC_DESC", new String[]{"Well Location Description", "Char"});    
    result.put("WELL_USE", new String[]{"Well Use Type", "Char"});        
    result.put("WELL_TYPE", new String[]{"Well Completion Type", "Char"});
    result.put("WELL_DEPTH", new String[]{"Well Depth (feet bgs)", "Number"});    
    result.put("TOP_PRF", new String[]{"Top Perforation (feet bgs)", "Number"});
    result.put("BOT_PRF", new String[]{"Bottom Perforation (feet bgs)", "Number"});
    result.put("GSE", new String[]{"Ground Surface Elevation", "Number"});
    result.put("RPE", new String[]{"Reference Point Elevation", "Number"});
    result.put("RP_DESC", new String[]{"Reference Point Description", "Char"});
    result.put("STN_CMT", new String[]{"Station Comments", "Char"});
    result.put("STN_MOD_DATE", new String[]{"Station Modified Date", "Date (MM/DD/YY HH:MI)"});
    result.put("STN_MOD_USER", new String[]{"Station Modified User", "Char"});
    result.put("WLM_METHOD", new String[]{"Water Level Measurement Method", "Char"});
    result.put("WLM_ACC", new String[]{"Water Level Measurement Accuracy", "Char"});
    result.put("COUNTY", new String[]{"County", "Char"});
    result.put("HR", new String[]{"Hydrologic Region", "Char"});
    result.put("BASIN_CODE", new String[]{"Basin Subbasin Number", "Number"});
    result.put("BASIN_NAME", new String[]{"Basin Subbasin Name", "Char"});
    result.put("BASIN_RO", new String[]{"DWR Region Office", "Number"});
    return result;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="WTIMS Queries">
  
  //<editor-fold defaultstate="collapsed" desc="WTIMS_WELL_SEARCH_QUERY">
  private static final String WTIMS_WELL_SEARCH_QUERY = "SELECT DISTINCT\n" +
                  "    a.ewm_station_id AS id,\n" +
                  "    a.ewm_state_well_nbr,\n" +
                  "    ( abs(a.longitude) *-1 ) AS longitude,\n" +
                  "    a.latitude,\n" +
                  "    b.site_code,\n" +
                  "    a.total_depth_ft AS totaldepth,\n" +
                  "    a.local_well_designation AS localwellid,\n" +
                  "    to_char(z.lastmeasuredate, 'MM/DD/YYYY') AS lastmeasuredate,\n" +
                  "    z.lastmeasuredate as lmd " +
                  "FROM\n" +
                  "    ewm_station a\n" +
                  "    LEFT JOIN ewm_master_site b ON a.ewm_master_site_id = b.ewm_master_site_id\n" +
                  "    LEFT JOIN (\n" +
                  "        SELECT\n" +
                  "            MAX(measurement_dt) AS lastmeasuredate,\n" +
                  "            ewm_station_id\n" +
                  "        FROM\n" +
                  "            ewm_elevation_data_reading\n" +
                  "        GROUP BY\n" +
                  "            ewm_station_id\n" +
                  "    ) z ON z.ewm_station_id = a.ewm_station_id";
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="WtimsQueryDelegate">
  private static class WtimsQueryDelegate extends QueryDelegate {

    public WtimsQueryDelegate(Object listener) {
      super(listener);
    }

    @Override
    public void handle(ResultSet rs) throws Exception {
      JSONArray results = this.getListener();
      while (rs.next()){
        JSONObject row = new JSONObject();
        row.put("id", rs.getInt("id"))
                .put("well_number", rs.getObject("ewm_state_well_nbr") != null ? rs.getString("ewm_state_well_nbr") : "-")
                .put("longitude", rs.getObject("longitude") != null ? rs.getDouble("longitude") : "-")
                .put("latitude", rs.getObject("latitude") != null ? rs.getDouble("latitude") : "-")
                .put("site_code", rs.getObject("site_code") != null ? rs.getString("site_code") : "-")
                .put("totaldepth", rs.getObject("totaldepth") != null ? rs.getDouble("totaldepth") : "-")
                .put("localwellid", rs.getObject("localwellid") != null ? rs.getString("localwellid") : "-")
                .put("totaldepth", rs.getObject("totaldepth") != null ? rs.getDouble("totaldepth") : "-")
                .put("lastmeasuredate", rs.getObject("lastmeasuredate") != null ? rs.getString("lastmeasuredate") : "-");
        
        results.put(row);
      }
    }
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="WtimsWellSearchQueryDelegate">
  private static class WtimsWellSearchQueryDelegate extends QueryDelegate {

    public WtimsWellSearchQueryDelegate(Object listener) {
      super(listener);
    }

    @Override
    public void handle(ResultSet rs) throws Exception {
      JSONArray results = this.getListener();
      while (rs.next()){
        JSONObject row = new JSONObject();
        row.put("id", rs.getInt("id"))
                .put("wellnumber", rs.getObject("ewm_state_well_nbr") != null ? rs.getString("ewm_state_well_nbr") : "-")
                .put("longitude", rs.getObject("longitude") != null ? rs.getDouble("longitude") : "-")
                .put("latitude", rs.getObject("latitude") != null ? rs.getDouble("latitude") : "-")
                .put("sitecode", rs.getObject("site_code") != null ? rs.getString("site_code") : "-")
                .put("totaldepth", rs.getObject("totaldepth") != null ? rs.getDouble("totaldepth") : "-")
                .put("localwellid", rs.getObject("localwellid") != null ? rs.getString("localwellid") : "-")
                .put("totaldepth", rs.getObject("totaldepth") != null ? rs.getDouble("totaldepth") : "-")
                .put("lastmeasuredate", rs.getObject("lastmeasuredate") != null ? rs.getString("lastmeasuredate") : "-")
                .put("type", "well");
        
        results.put(row);
      }
    }
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="WtimsAutoCompleteQueryDelegate">
  private static class WtimsAutoCompleteQueryDelegate extends QueryDelegate {
    
    private final String param;

    public WtimsAutoCompleteQueryDelegate(Object listener, String param) {
      super(listener);
      this.param = param;
    }

    @Override
    public void handle(ResultSet rs) throws Exception {
      JSONArray results = this.getListener();
      String upperParam = this.param.toUpperCase();
      Set<String> idSet = new HashSet<>();
      
      while (rs.next()) {
        String localwellid = rs.getObject("localwellid") != null ? rs.getString("localwellid") : "";
        String wellnum = rs.getObject("ewm_state_well_nbr") != null ? rs.getString("ewm_state_well_nbr") : "";
        String wellid = Integer.toString(rs.getInt("id"));
        String sitecode = rs.getObject("site_code") != null ? rs.getString("site_code") : "";
        
        if (localwellid.toUpperCase().contains(upperParam)) {
          idSet.add(localwellid);
        } else if (wellnum.toUpperCase().contains(upperParam)) {
          idSet.add(wellnum);
        } else if (wellid.toUpperCase().contains(upperParam)) {
          idSet.add(wellid);
        } else if (sitecode.toUpperCase().contains(upperParam)) {
          idSet.add(sitecode);
        }
      }
      
      List<String> ids = new ArrayList<>(idSet);
      Collections.sort(ids);
      
      for (String id : ids) {
        JSONObject row = new JSONObject();
        row.put("label", id);
        row.put("value", id);
        row.put("id", id);
        results.put(row);
      }
    }
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="WtimsBufferedPointsQueryDelegate">
  private static class WtimsBufferedPointsQueryDelegate extends QueryDelegate {
    
    private final ProjectionInfo latlong, web;
    private final Coord webClick;
    private final double distance_feet, lat;

    public WtimsBufferedPointsQueryDelegate(Object listener, ProjectionInfo latlong,
            ProjectionInfo web, Coord webClick, double distance_feet, double lat) {
      super(listener);
      this.latlong = latlong;
      this.web = web;
      this.webClick = webClick;
      this.distance_feet = distance_feet;
      this.lat = lat;
    }

    @Override
    public void handle(ResultSet rs) throws Exception {
      JSONArray results = this.getListener();
      List<JSONObject> objects = new ArrayList<>();

      double wideDistance = distance_feet / Math.cos(Math.toRadians(lat));
      
      while (rs.next()) {
        double resultlat = rs.getDouble("latitude");
        double resultlng = Math.abs(rs.getDouble("longitude")) * -1;
        Coord wgs84 = new CoordXY(resultlng, resultlat);

        Coord webCoord = Reproject.reprojectCoordinate(wgs84, latlong, web);
        double distanceFromCenter = Double.MAX_VALUE;
        if (webClick != null) {
          distanceFromCenter = webClick.distance(webCoord);
        }

        if (distanceFromCenter <= wideDistance * 0.3048) {
          double dist = (distanceFromCenter * Math.cos(Math.toRadians(lat)) * 3.28084) / 5280;
          JSONObject obj = new JSONObject();
          obj.put("id", rs.getInt("id"))
              .put("distance", dist)
              .put("wellname", rs.getObject("ewm_state_well_nbr") != null ? rs.getString("ewm_state_well_nbr") : "-")
              .put("longitude", rs.getObject("longitude") != null ? rs.getDouble("longitude") : "-")
              .put("latitude", rs.getObject("latitude") != null ? rs.getDouble("latitude") : "-")
              .put("sitecode", rs.getObject("site_code") != null ? rs.getString("site_code") : "-")
              .put("totaldepth", rs.getObject("totaldepth") != null ? rs.getDouble("totaldepth") : "-")
              .put("localwellid", rs.getObject("localwellid") != null ? rs.getString("localwellid") : "-")
              .put("totaldepth", rs.getObject("totaldepth") != null ? rs.getDouble("totaldepth") : "-")
              .put("lastmeasuredate", rs.getObject("lastmeasuredate") != null ? rs.getString("lastmeasuredate") : "-")
              .put("type", "well");

          objects.add(obj);
        }
      }
      
      Collections.sort(objects, new Comparator<JSONObject>() {
        @Override
        public int compare(JSONObject o1, JSONObject o2) {
          return Double.compare(o1.getDouble("distance"), o2.getDouble("distance"));
        }
      });
      
      for (JSONObject object : objects) {
        results.put(object);
      }
    }
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="WtimsBufferedPointsQueryDelegate">
  
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Search By ID">
  public JSONArray wtimsSearchById(String id) {
    JSONArray result = new JSONArray();
    String where = "WHERE\n" +
        "    (\n" +
        "            upper(ltrim(a.ewm_state_well_nbr) ) = upper(?)\n" +
        "        OR\n" +
        "            upper(ltrim(b.site_code) ) LIKE upper(?)\n" +
        "        OR\n" +
        "            upper(ltrim(a.ewm_station_id) ) LIKE upper(?)\n" +
        "        OR\n" +
        "            upper(ltrim(a.local_well_designation) ) LIKE upper(?)\n" +
        "    )\n" +
        "ORDER BY lmd DESC NULLS LAST";
    
    String query = String.format("%s %s", WTIMS_WELL_SEARCH_QUERY, where);
    ArrayList<String> params = new ArrayList<>();
    params.add(id);
    params.add(id);
    params.add(id);
    params.add(id);
    this.runQuery(query, params, new WtimsQueryDelegate(result));
    return result;
  }
//</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="search By Buffer">
  public JSONArray wtimsSearchByBuffer(Double lat, Double lng, Double distanceFeet) {
    Shape shape = new Shape(new CoordXY(lng, lat));
    Envelope extent = shape.getEnvelope().bufferInFeet(distanceFeet);
    double minLat = extent.getMin().getY();
    double maxLat = extent.getMax().getY();
    double minLong = extent.getMin().getX();
    double maxLong = extent.getMax().getX();
    return wtimsSearchByExtent(minLat, maxLat, minLong, maxLong);
  }
//</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="search By Buffer">
  public JSONArray wtimsSearchNearestWells(Double lat, Double lng, Double distanceFeet) {
    JSONArray result = new JSONArray();
    Shape shape = new Shape(new CoordXY(lng, lat));
    Envelope extent = shape.getEnvelope().bufferInFeet(distanceFeet);
    double minLat = extent.getMin().getY();
    double maxLat = extent.getMax().getY();
    double minLong = extent.getMin().getX();
    double maxLong = extent.getMax().getX();
    
    String query = "SELECT\n" +
            "    ewm_station_id AS id,\n" +
            "    ewm_state_well_nbr as wellname,\n" +
            "    ( abs(longitude) *-1 ) AS longitude,\n" +
            "    latitude\n" +
            "FROM\n" +
            "    ewm_station\n" +
            "WHERE\n" +
            "        ( abs(longitude) *-1 ) BETWEEN ? AND ? \n" +
            "    AND\n" +
            "        latitude BETWEEN ? AND ?";
    ArrayList<Object> params = new ArrayList<>();
    params.add(minLong);
    params.add(maxLong);
    params.add(minLat);
    params.add(maxLat);
    this.runQuery(query, params, new QueryDelegate(result) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        JSONArray result = this.getListener();
        while (rs.next()){
          JSONObject row = new JSONObject();
          row.put("id", rs.getInt("id"))
                  .put("wellname", rs.getString("wellname"));
          result.put(row);
        }
      }
    });
    return result;
  }
//</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Search by extent">
  public JSONArray wtimsSearchByExtent(double minLat, double maxLat, double minLong, double maxLong) {
    JSONArray result = new JSONArray();
    String where = " where    ( abs(a.longitude) *-1 ) BETWEEN ? AND ? \n" +
                    "    AND\n" +
                    "        a.latitude BETWEEN ? AND ? " +
                    " ORDER BY lmd DESC NULLS LAST";

    String query = String.format("%s %s", WTIMS_WELL_SEARCH_QUERY, where);
    ArrayList<Object> params = new ArrayList<>();
    params.add(minLong);
    params.add(maxLong);
    params.add(minLat);
    params.add(maxLat);
    this.runQuery(query, params, new WtimsQueryDelegate(result));
    return result;
  }
//</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get by ID">
  public JSONObject wtimsGetStationById(Integer id) {
    JSONObject result = new JSONObject();
    String query = "SELECT\n" +
                    "    a.ewm_station_id as id, " +
                    "    ewm_state_well_nbr as statewellnum, " +
                    "    site_code as sitecode,\n" +
                    "    a.local_well_designation AS localwellid,\n" +
                    "    a.total_depth_ft AS totaldepth,\n" +
                    "    x.low AS low,\n" +
                    "    y.high AS high,\n" +
                    "    to_char(z.lastmeasuredate, 'MM/DD/YYYY') AS lastmeasuredate,\n" +
                    "    to_char(w.firstmeasuredate, 'MM/DD/YYYY') AS firstmeasuredate,\n" +
                    "    a.well_location_desc AS welllocation\n" +
                    "FROM\n" +
                    "    ewm_station a\n" +
                    "    LEFT JOIN ewm_master_site b ON a.ewm_master_site_id = b.ewm_master_site_id\n" +
                    "    LEFT JOIN (\n" +
                    "        SELECT\n" +
                    "            MAX(perforation_bottom_msrmnt) AS low,\n" +
                    "            ewm_station_id\n" +
                    "        FROM\n" +
                    "            ewm_station_perforation\n" +
                    "        GROUP BY\n" +
                    "            ewm_station_id\n" +
                    "    ) x ON x.ewm_station_id = a.ewm_station_id\n" +
                    "    LEFT JOIN (\n" +
                    "        SELECT\n" +
                    "            MAX(perforation_top_msrmnt) AS high,\n" +
                    "            ewm_station_id\n" +
                    "        FROM\n" +
                    "            ewm_station_perforation\n" +
                    "        GROUP BY\n" +
                    "            ewm_station_id\n" +
                    "    ) y ON y.ewm_station_id = a.ewm_station_id\n" +
                    "    LEFT JOIN (\n" +
                    "        SELECT\n" +
                    "            MAX(measurement_dt) AS lastmeasuredate,\n" +
                    "            ewm_station_id\n" +
                    "        FROM\n" +
                    "            ewm_elevation_data_reading\n" +
                    "        GROUP BY\n" +
                    "            ewm_station_id\n" +
                    "    ) z ON z.ewm_station_id = a.ewm_station_id\n" +
                    "    LEFT JOIN (\n" +
                    "        SELECT\n" +
                    "            MIN(measurement_dt) AS firstmeasuredate,\n" +
                    "            ewm_station_id\n" +
                    "        FROM\n" +
                    "            ewm_elevation_data_reading\n" +
                    "        GROUP BY\n" +
                    "            ewm_station_id\n" +
                    "    ) w ON w.ewm_station_id = a.ewm_station_id\n" +
                    "WHERE\n" +
                    "    a.ewm_station_id = ?";

    ArrayList<Object> params = new ArrayList<>();
    params.add(id);
    this.runQuery(query, params, new QueryDelegate(result) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        JSONObject results = this.getListener();
        rs.next();
        results
                .put("id", rs.getInt("id"))
                .put("sitecode", rs.getObject("sitecode") != null ? rs.getString("sitecode") : "-")
                .put("statewellnum", rs.getObject("statewellnum") != null ? rs.getString("statewellnum") : "-")
                .put("localwellid", rs.getObject("localwellid") != null ? rs.getString("localwellid") : "-")
                .put("totaldepth", rs.getObject("totaldepth") != null ? rs.getDouble("totaldepth") : "-")
                .put("low", rs.getObject("low") != null ? rs.getDouble("low") : "-")
                .put("high", rs.getObject("high") != null ? rs.getDouble("high") : "-")
                .put("lastmeasuredate", rs.getObject("lastmeasuredate") != null ? rs.getString("lastmeasuredate") : "-")
                .put("firstmeasuredate", rs.getObject("firstmeasuredate") != null ? rs.getString("firstmeasuredate") : "-")
                .put("welllocation", rs.getObject("welllocation") != null ? rs.getString("welllocation") : "-");
      }
    });
    return result;
  }
//</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Search By Names">
  public JSONArray wtimsSearchByNames(List<String> names) {
    JSONArray result = new JSONArray();
    String where = "";
    ArrayList<String> params = new ArrayList<>();
    
    for (int i = 0; i < names.size(); i++) {
      if (i == 0)
        where += "WHERE\n";
      
      where += " (UPPER(LTRIM( A.EWM_STATE_WELL_NBR)) = UPPER(?)\n"
              + "OR UPPER(LTRIM( B.SITE_CODE)) LIKE UPPER(?)\n"
              + "OR UPPER(LTRIM( A.EWM_STATION_ID)) LIKE UPPER(?)\n"
              + "OR UPPER(LTRIM( A.LOCAL_WELL_DESIGNATION)) LIKE UPPER(?))\n";

      if (i != names.size() - 1)
        where += " OR ";
      
      for (int j = 0; j < 4; j++)
        params.add(names.get(i));
    }
    where += " ORDER BY Z.LASTMEASUREDATE DESC NULLS LAST";
    
    String query = String.format("%s %s", WTIMS_WELL_SEARCH_QUERY, where);
    this.runQuery(query, params, new WtimsWellSearchQueryDelegate(result));
    return result;
  }
//</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="AutoComplete">
  public JSONArray wtimsAutoComplete(String param) {
    JSONArray result = new JSONArray();
    ArrayList<String> params = new ArrayList<>();
    
    String where = "WHERE (UPPER(LTRIM( A.EWM_STATE_WELL_NBR)) = UPPER(? || '%')\n"
            + "OR UPPER(LTRIM( B.SITE_CODE)) LIKE UPPER(? || '%')\n"
            + "OR UPPER(LTRIM( A.EWM_STATION_ID)) LIKE UPPER(? || '%')\n"
            + "OR UPPER(LTRIM( A.LOCAL_WELL_DESIGNATION)) LIKE UPPER(? || '%'))\n";

    for (int j = 0; j < 4; j++)
      params.add(param);
    
    where += " ORDER BY Z.LASTMEASUREDATE DESC NULLS LAST";
    
    String query = String.format("%s %s", WTIMS_WELL_SEARCH_QUERY, where);
    this.runQuery(query, params, new WtimsAutoCompleteQueryDelegate(result, param));
    return result;
  }
//</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Buffered Points">
  public JSONArray wtimsBufferedPoints(double lat, double lng, double distance_feet) throws ProjectionException {
    JSONArray result = new JSONArray();
    ArrayList<Double> params = new ArrayList<>();
    
    Coord clickLocation = new CoordXY(lng, lat);
    ProjectionInfo latlong = Projections.getGeographic().getWorld().getWGS1984();
    ProjectionInfo web = Projections.getProjected().getWorld().getWebMercator();
    Coord webClick = Reproject.reprojectCoordinate(clickLocation, latlong, web);

    //Make a bounding box around the centerpoint devided
    double minLat = webClick.getY() - (distance_feet * Math.cos(Math.toRadians(lat)));  //* 0.3048
    double maxLat = webClick.getY() + (distance_feet * Math.cos(Math.toRadians(lat)));
    double minLong = webClick.getX() - (distance_feet * Math.cos(Math.toRadians(lat)));
    double maxLong = webClick.getX() + (distance_feet * Math.cos(Math.toRadians(lat)));

    Coord lowerLeft = new CoordXY(minLong, minLat);
    Coord upperRight = new CoordXY(maxLong, maxLat);

    lowerLeft = Reproject.reprojectCoordinate(lowerLeft, web, latlong);
    upperRight = Reproject.reprojectCoordinate(upperRight, web, latlong);

    //select
    String where = "WHERE (abs(A.LONGITUDE)*-1) BETWEEN ? AND ? AND A.LATITUDE BETWEEN ? AND ?\n";

    params.add(lowerLeft.getX());
    params.add(upperRight.getX());
    params.add(lowerLeft.getY());
    params.add(upperRight.getY());
    
    String query = String.format("%s %s", WTIMS_WELL_SEARCH_QUERY, where);
    this.runQuery(query, params, new WtimsBufferedPointsQueryDelegate(result, latlong,
            web, webClick, distance_feet, lat));
    return result;
  }
//</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="In Extend of Boundary">
  public JSONArray wtimsWellsInExtentOfBoundary(double xmin, double xmax, double ymin, double ymax) {
    JSONArray result = new JSONArray();
    ArrayList<Double> params = new ArrayList<>();
    
    String where = "WHERE ((abs(A.LONGITUDE)*-1) < ?)"
              + " AND ((abs(A.LONGITUDE)*-1) > ?)\n"
              + " AND (A.LATITUDE < ?)"
              + " AND (A.LATITUDE > ?)\n";
    where += " ORDER BY Z.LASTMEASUREDATE DESC NULLS LAST";
    
    params.add(xmax);
    params.add(xmin);
    params.add(ymax);
    params.add(ymin);
    
    String query = String.format("%s %s", WTIMS_WELL_SEARCH_QUERY, where);
    this.runQuery(query, params, new WtimsQueryDelegate(result));
    return result;
  }
//</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Time Series">
  public JSONObject wtimsGetTimeSeries(JSONArray wells, JSONArray names) {
    
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    final String query = "SELECT\n" +
"  s.EWM_STATION_ID AS ID,\n" +
"  edr.MEASUREMENT_DT as measurementDate,\n" +
"  edr.REFERENCE_POINT_ELEVATION AS REFELEVATION,\n" +
"  edr.REFERENCE_POINT_READING AS REFREADING\n" +
"FROM\n" +
"  EWM_ELEVATION_DATA_READING edr\n" +
"  JOIN EWM_STATION s ON edr.EWM_STATION_ID = s.EWM_STATION_ID\n" +
"WHERE\n" +
"  s.EWM_STATION_ID=? and edr.measurement_dt is not null\n" +
"  and edr.reference_point_elevation is not null and edr.reference_point_reading is not null";
//    this.runQuery(query, params, new WtimsQueryDelegate(result));
//    return result;
    
    HashMap<String, HashMap<String, Double>> allSeries = new HashMap<>();
    final List<String> allDates = new ArrayList<>();
    String returnString = "Date";
    try {
      for (int i = 0; i < wells.length(); i++) {
        String well = wells.get(i) == null ? "" : Integer.toString(wells.getInt(i));
        String name = names.getString(i);
        String key = name;
        if (name.isEmpty() || name.equals("-")) {
          key = well;
        }

        returnString += "," + key;
        HashMap<String, Double> series = new HashMap<>();
        allSeries.put(key, series);

        List<String> params = new ArrayList<>();
        params.add(well);
        this.runQuery(query, params, new QueryDelegate(series) {
          @Override
          public void handle(ResultSet rs) throws Exception {
            HashMap<String, Double> series = this.getListener();
            
            while(rs.next()) {
              double elev = rs.getDouble("refelevation");
              double reading = rs.getDouble("refreading");
              double graphElevation = elev - reading;
              Calendar cal = Calendar.getInstance();
              cal.setTimeInMillis(rs.getTimestamp("measurementdate").getTime());
              String date = sdf.format(cal.getTime());
              series.put(date, graphElevation);
              if (!allDates.contains(date)) {
                allDates.add(date);
              }
            }
          }
        });
      }
    } catch (Exception ex) {
      ex.printStackTrace(System.err);
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
      for (int i = 0; i < wells.length(); i++) {
        String well = wells.get(i) == null ? "" : Integer.toString(wells.getInt(i));
        String name = names.getString(i);
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
    JSONObject result = new JSONObject();
    result.put("data", returnString);
    result.put("numberOfColumns", wells.length());
    return result;
  }
//</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Download Well CSV">
  public void wtimsDownloadWellCSV(JSONArray ids, PrintWriter writer) {
    String query = "SELECT\n" +
                    "    a.ewm_station_id as id, " +
                    "    ewm_state_well_nbr as statewellnum, " +
                    "    site_code as sitecode,\n" +
                    "    a.local_well_designation AS localwellid,\n" +
                    "    a.total_depth_ft AS totaldepth,\n" +
                    "    x.low AS low,\n" +
                    "    y.high AS high,\n" +
                    "    to_char(z.lastmeasuredate, 'MM/DD/YYYY') AS lastmeasuredate,\n" +
                    "    to_char(w.firstmeasuredate, 'MM/DD/YYYY') AS firstmeasuredate,\n" +
                    "    a.well_location_desc AS welllocation\n" +
                    "FROM\n" +
                    "    ewm_station a\n" +
                    "    LEFT JOIN ewm_master_site b ON a.ewm_master_site_id = b.ewm_master_site_id\n" +
                    "    LEFT JOIN (\n" +
                    "        SELECT\n" +
                    "            MAX(perforation_bottom_msrmnt) AS low,\n" +
                    "            ewm_station_id\n" +
                    "        FROM\n" +
                    "            ewm_station_perforation\n" +
                    "        GROUP BY\n" +
                    "            ewm_station_id\n" +
                    "    ) x ON x.ewm_station_id = a.ewm_station_id\n" +
                    "    LEFT JOIN (\n" +
                    "        SELECT\n" +
                    "            MAX(perforation_top_msrmnt) AS high,\n" +
                    "            ewm_station_id\n" +
                    "        FROM\n" +
                    "            ewm_station_perforation\n" +
                    "        GROUP BY\n" +
                    "            ewm_station_id\n" +
                    "    ) y ON y.ewm_station_id = a.ewm_station_id\n" +
                    "    LEFT JOIN (\n" +
                    "        SELECT\n" +
                    "            MAX(measurement_dt) AS lastmeasuredate,\n" +
                    "            ewm_station_id\n" +
                    "        FROM\n" +
                    "            ewm_elevation_data_reading\n" +
                    "        GROUP BY\n" +
                    "            ewm_station_id\n" +
                    "    ) z ON z.ewm_station_id = a.ewm_station_id\n" +
                    "    LEFT JOIN (\n" +
                    "        SELECT\n" +
                    "            MIN(measurement_dt) AS firstmeasuredate,\n" +
                    "            ewm_station_id\n" +
                    "        FROM\n" +
                    "            ewm_elevation_data_reading\n" +
                    "        GROUP BY\n" +
                    "            ewm_station_id\n" +
                    "    ) w ON w.ewm_station_id = a.ewm_station_id\n" +
                    "WHERE\n";
//                    "    a.ewm_station_id = ?";

    ArrayList<Object> params = new ArrayList<>();
    for (int i = 0; i < ids.length(); i++) {
      if (i == ids.length() - 1) {
        query += "A.EWM_STATION_ID=?\n";
      } else {
        query += "A.EWM_STATION_ID=? OR\n";
      }
      params.add(ids.get(i));
    }
    query += "ORDER BY A.EWM_STATE_WELL_NBR\n";
    
    writer.println("Station ID,Site Code,State Well Number,Local Well ID,Total Depth,Bottom Perforation,Top Perforation,Last Measure Date,First Measure Date,Well Location Description");
    this.runQuery(query, params, new QueryDelegate(writer) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        PrintWriter writer = this.getListener();
        while (rs.next()) {
          writer.print(rs.getInt("id"));
          writer.print(",");
          writer.print(rs.getObject("sitecode") != null ? rs.getString("sitecode") : "");
          writer.print(",");
          writer.print(rs.getObject("statewellnum") != null ? rs.getString("statewellnum") : "");
          writer.print(",");
          writer.print(rs.getObject("localwellid") != null ? rs.getString("localwellid") : "");
          writer.print(",");
          writer.print(rs.getObject("totaldepth") != null ? rs.getDouble("totaldepth") : "");
          writer.print(",");
          writer.print(rs.getObject("low") != null ? rs.getDouble("low") : "-");
          writer.print(",");
          writer.print(rs.getObject("high") != null ? rs.getDouble("high") : "-");
          writer.print(",");
          writer.print(rs.getObject("lastmeasuredate") != null ? rs.getString("lastmeasuredate") : "");
          writer.print(",");
          writer.print(rs.getObject("firstmeasuredate") != null ? rs.getString("firstmeasuredate") : "");
          writer.print(",");
          writer.print(rs.getObject("welllocation") != null ? rs.getString("welllocation") : "");
          writer.println();
        }
      }
    });
  }
//</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Download Time Series CSV">
  public void wtimsDownloadTimeSeriesCSV(JSONArray ids, PrintWriter writer) {
    String query = "SELECT A.*,(A.REFERENCE_POINT_ELEVATION-A.REFERENCE_POINT_READING) AS WSE\n" +
"FROM EWM_ELEVATION_DATA_READING A\n" +
"WHERE\n";

    ArrayList<Object> params = new ArrayList<>();
    for (int i = 0; i < ids.length(); i++) {
      if (i == ids.length() - 1) {
        query += "A.EWM_STATION_ID=?\n";
      } else {
        query += "A.EWM_STATION_ID=? OR\n";
      }
      params.add(ids.get(i));
    }
    query += "ORDER BY A.EWM_STATION_ID,A.MEASUREMENT_DT\n";
    
    writer.println("id,measurement date,modified date,org id,ewm measurement issue type id,ewm station id,ewm elev measure method type id,"
    + "elev accuracy type id,ref point elev,ground surface elev,water surface reading,reference point reading,mandatory reading,wse");
    this.runQuery(query, params, new QueryDelegate(writer) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        PrintWriter writer = this.getListener();
        while (rs.next()) {
          writer.print(rs.getInt("ewm_station_id"));
          writer.print(",");
          writer.print(rs.getObject("measurement_dt") != null ? rs.getString("measurement_dt") : "");
          writer.print(",");
          writer.print(rs.getObject("modified_date") != null ? rs.getString("modified_date") : "");
          writer.print(",");
          writer.print(rs.getObject("org_id") != null ? rs.getInt("org_id") : "");
          writer.print(",");
          writer.print(rs.getObject("ewm_measurement_issue_type_id") != null ? rs.getInt("ewm_measurement_issue_type_id") : "");
          writer.print(",");
          writer.print(rs.getObject("ewm_station_id") != null ? rs.getInt("ewm_station_id") : "");
          writer.print(",");
          writer.print(rs.getObject("ewm_elev_measure_method_typ_id") != null ? rs.getInt("ewm_elev_measure_method_typ_id") : "");
          writer.print(",");
          writer.print(rs.getObject("ewm_elevation_accuracy_type_id") != null ? rs.getInt("ewm_elevation_accuracy_type_id") : "");
          writer.print(",");
          writer.print(rs.getObject("reference_point_elevation") != null ? rs.getDouble("reference_point_elevation") : "");
          writer.print(",");
          writer.print(rs.getObject("ground_surface_elevation") != null ? rs.getDouble("ground_surface_elevation") : "");
          writer.print(",");
          writer.print(rs.getObject("water_surface_reading") != null ? rs.getDouble("water_surface_reading") : "");
          writer.print(",");
          writer.print(rs.getObject("reference_point_reading") != null ? rs.getDouble("reference_point_reading") : "");
          writer.print(",");
          writer.print(rs.getObject("mandatory_reading") != null ? rs.getString("mandatory_reading") : "");
          writer.print(",");
          writer.print(rs.getObject("wse") != null ? rs.getDouble("wse") : "");
          writer.println();
        }
      }
    });
  }
//</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Params">
  public List<Integer> wtimsGetParams() {
    List<Object> params = new ArrayList<>();
    List<Integer> result = new ArrayList<>();
    
    String query = "SELECT DISTINCT EXTRACT(year FROM MEASUREMENT_DT) AS year\n"
            + "FROM EWM_ELEVATION_DATA_READING where MEASUREMENT_DT is not null\n"
            + "ORDER BY EXTRACT(year FROM MEASUREMENT_DT)";

    this.runQuery(query, params, new QueryDelegate(result) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        List<Integer> results = this.getListener();
        
        while (rs.next()) {
          results.add(rs.getInt("year"));
        }
      }
    });
    return result;
  }
//</editor-fold>
  
//</editor-fold>

}