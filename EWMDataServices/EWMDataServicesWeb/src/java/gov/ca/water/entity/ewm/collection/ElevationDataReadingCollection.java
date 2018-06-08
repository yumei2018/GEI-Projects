package gov.ca.water.entity.ewm.collection;

import com.opencsv.CSVWriter;
import entity.core.EntityCollection;
import entity.core.delegate.QueryDelegate;
import entity.core.filter.ConditionFilter;
import entity.core.util.EntityUtil;
import entity.core.util.ExceptionUtil;
import gov.ca.water.entity.Wrapper;
import gov.ca.water.entity.ewm.ElevMeasureMethodTyp;
import gov.ca.water.entity.ewm.ElevationAccuracyType;
import gov.ca.water.entity.ewm.ElevationDataReading;
import gov.ca.water.entity.ewm.MasterSite;
import gov.ca.water.entity.ewm.MeasurementIssueType;
import gov.ca.water.entity.ewm.MeasurementMethodType;
import gov.ca.water.entity.ewm.Organization;
import gov.ca.water.entity.ewm.Station;
import gov.ca.water.entity.ewm.StationUseType;
import static gov.ca.water.entity.ewm.collection.StationCollection.getFieldsMap;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashSet;
/**
 *
 * @author clay
 */
public class ElevationDataReadingCollection extends EntityCollection<ElevationDataReading> {

  //<editor-fold defaultstate="collapsed" desc="getMinDate">
  public static Date getMinDate() {
    Date result = null;
    Date[] container = new Date[1];
    ElevationDataReadingCollection data = new ElevationDataReadingCollection();
    data.runQuery("select min(MEASUREMENT_DT) min_date from " + EntityUtil.getTableName(ElevationDataReading.class), null, new QueryDelegate(container) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        Date[] container = this.getListener();
        rs.next();
        container[0] = rs.getDate("min_date");
      }
    });
    result = container[0];
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="getMaxDate">
  public static Date getMaxDate() {
    Date result = null;
    Date[] container = new Date[1];
    ElevationDataReadingCollection data = new ElevationDataReadingCollection();
    data.runQuery("select max(MEASUREMENT_DT) max_date from " + EntityUtil.getTableName(ElevationDataReading.class), null, new QueryDelegate(container) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        Date[] container = this.getListener();
        rs.next();
        container[0] = rs.getDate("max_date");
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
  public ElevationDataReadingCollection() {
    super(ElevationDataReading.class);
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Load... ">
  public ElevationDataReadingCollection loadByIds(String[] ids) {
    int start, end;
    List<String> stationIds = Arrays.asList(ids);
    for (int i = 0; i<ids.length; i += 1000){
      start = 0;
      end = Math.min(start + 1000, ids.length);
      this.findAll(
        ConditionFilter.createInstance("ewmStationId", ConditionFilter.ConditionOperator.SQL_IN, stationIds.subList(start, end))
      );
    }
    return this;
  }
  
  public ElevationDataReadingCollection loadByIds(ArrayList<Integer> ids) {
    int start, end;
    for (int i = 0; i<ids.size(); i += 1000){
      start = 0;
      end = Math.min(start + 1000, ids.size());
      this.findAll(
        ConditionFilter.createInstance("ewmStationId", ConditionFilter.ConditionOperator.SQL_IN, ids.subList(start, end))
      );
    }
    this.sort("measurementDt", "desc");
    return this;
  }

  public ElevationDataReadingCollection load(Station station) {
    if (station != null && station.isLoaded()){
      this.findAll(
          ConditionFilter.createInstance("ewmStationId", ConditionFilter.ConditionOperator.SQL_EQUAL, station.getEwmStationId())
      );
    }
    this.sort("measurementDt", "asc");
    
    return this;
  }
  
  public ElevationDataReadingCollection loadHighestPriorityRaw(Station station){
    if (station != null && station.isLoaded()){
      String query = "SELECT edr.* " +
            "FROM " + EntityUtil.getTableName(ElevationDataReading.class) + " edr " +
            "LEFT JOIN " + EntityUtil.getTableName(MeasurementMethodType.class) + " mmt ON mmt.ewm_measurement_method_type_id = edr.ewm_elev_measure_method_typ_id " +
            "WHERE " +
              "ewm_station_id = ? " +
              "AND " +
              "mmt.ewm_measurement_method_order = ( " +
                "SELECT " +
                "MIN(ewm_measurement_method_order) " +
                "FROM " +
                "" + EntityUtil.getTableName(ElevationDataReading.class) + " edr " +
                "LEFT JOIN " + EntityUtil.getTableName(MeasurementMethodType.class) + " mmt ON mmt.ewm_measurement_method_type_id = edr.ewm_elev_measure_method_typ_id " +
                "WHERE " +
                "ewm_station_id = ?)";
      List<Object> params = new ArrayList<>();
      params.add(station.getEwmStationId());
      params.add(station.getEwmStationId());
      this.runQuery(query, params);
    }
    return this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="function loadUnique(Station station)">
  public ElevationDataReadingCollection loadUnique(Station station) {
    if (station != null && station.isLoaded()){
      this.load(station);
      Date lastReadingDate = null;
      Iterator<ElevationDataReading> iter = this.iterator();
      while (iter.hasNext()){
        ElevationDataReading reading = iter.next();
        if (Objects.equals(reading.getMeasurementDt(),lastReadingDate)){
          iter.remove();
        }
        lastReadingDate = reading.getMeasurementDt();
      }
    }
    return this;
  }
  //</editor-fold>
  
  public void writeCSV(OutputStream os, String queryData, final HashSet stationIds) throws IOException{
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
    String query = buildWaterlevelDownloadQuery();    
    //<editor-fold defaultstate="collapsed" desc="Build the Where Statement">    
    List<Object> params = new ArrayList<>();
    if(acceptNullDepth = true){
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
    JSONArray queryRes = new JSONArray();
    final List<String> fields = waterlevelDownloadFields();
    String[] header = new String[fields.size()];
    header = fields.toArray(header);
    final CSVWriter writer = new CSVWriter(new OutputStreamWriter(os));
    writer.writeNext(header);
    final String[] csvRow = new String[fields.size()];
    this.runQuery(query, params, new QueryDelegate(queryRes) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        while (rs.next()){
          if(rs.getString("STN_ID")!=null && stationIds.contains(rs.getString("STN_ID"))){
            for(int j=0; j<fields.size(); j++){
              String field = fields.get(j);
              csvRow[j] = rs.getString(field) != null? rs.getString(field): "";
            }
            writer.writeNext(csvRow);
          }
        }
      }
    });
    writer.flush();
  }
  
  //<editor-fold defaultstate="collapsed" desc="Export Elevation Data as CSV (filtered results) - writeElevationDataExport">
  public ElevationDataReadingCollection writeElevationDataExport(OutputStream ostream, List<Integer> stationIds, String metadata) {
    CSVWriter writer = new CSVWriter(new OutputStreamWriter(ostream));
    List params = new ArrayList();
    params.addAll(stationIds);
    String startDate = null;
    String endDate = null;
    try {
      JSONObject metadataJson = new JSONObject(metadata);
      if(metadataJson.has("startDate")){
        startDate = metadataJson.optString("startDate");
        params.add(startDate);
      }
      if(metadataJson.has("endDate")){
        endDate = metadataJson.optString("endDate");
        params.add(endDate);
      }
      String query = buildElevationDataQuery(stationIds, startDate, endDate);
      this.runQuery(query, params, new QueryDelegate(writer) {
        @Override
        public void handle(ResultSet rs) throws Exception {
          CSVWriter writer = getListener();
          writer.writeAll(rs, true);
          writer.flush();
        }
      });
    } catch (Exception e) {
    }
    return this;
  }
  
  public ElevationDataReadingCollection writeElevationDataExport(OutputStream ostream, List<Integer> stationIds) {
    CSVWriter writer = new CSVWriter(new OutputStreamWriter(ostream));
    List params = new ArrayList();
    params.addAll(stationIds);
    
    try {
      String query = buildElevationDataQuery(stationIds, null, null);
      this.runQuery(query, params, new QueryDelegate(writer) {
        @Override
        public void handle(ResultSet rs) throws Exception {
          CSVWriter writer = getListener();
          writer.writeAll(rs, true);
          writer.flush();
        }
      });
    } catch (Exception e) {
    }
    return this;
    
  }
  
  public ElevationDataReadingCollection writeElevationDataExport(OutputStream ostream, List<Integer> stationIds, String startDate, String endDate) {
    CSVWriter writer = new CSVWriter(new OutputStreamWriter(ostream));
    List params = new ArrayList();
    params.addAll(stationIds);
    if(startDate != null){
      params.add(startDate);
    }
    if(endDate != null){
      params.add(endDate);
    }
    try {
      String query = buildElevationDataQuery(stationIds, startDate, endDate);
      this.runQuery(query, params, new QueryDelegate(writer) {
        @Override
        public void handle(ResultSet rs) throws Exception {
          CSVWriter writer = getListener();
          writer.writeAll(rs, true);
          writer.flush();
        }
      });
    } catch (Exception e) {
    }
    return this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Export Elevation Data as CSV (full dataset) - writeFullElevationData ">
  public ElevationDataReadingCollection writeFullElevationData(OutputStream os) {
    String query = buildAllElevationDataQuery();
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

  //<editor-fold defaultstate="collapsed" desc="Get Single Well Elevation Data">
  public JSONArray getSingleWellElevationData(String stationId) 
  {
    JSONArray queryRes = new JSONArray();
    final JSONArray finalRes = new JSONArray();
    String query = buildSingleWellElevationDataQuery();
    List<Object> params = new ArrayList<>();
    params.add(stationId);
    this.runQuery(query, params, new QueryDelegate(queryRes) {
      @Override
      public void handle(ResultSet rs) throws Exception {
        while (rs.next()){
          JSONObject localwell = new JSONObject();
          localwell.put("SITE_CD", rs.getString("SITE_CODE")!=null?rs.getString("SITE_CODE"):"");
          localwell.put("WELL_ID", rs.getString("LOCAL_WELL_DESIGNATION")!=null?rs.getString("LOCAL_WELL_DESIGNATION"):"");    
          finalRes.put(localwell);
        }
      }
    });
    return finalRes;
  }
  //</editor-fold>

  private String buildWaterlevelDownloadQuery(){
    String query = "SELECT M.SITE_CODE as SITE_CODE, "
        + "S.LOCAL_WELL_DESIGNATION as WELL_NAME, "
        + "S.EWM_STATE_WELL_NBR as SWN, "
        + "S.EWM_STATION_ID as STN_ID, "
        + "EDR.MEASUREMENT_DT as MSMT_DATE, "
        + "S.LATITUDE as LATITUDE, "
        + "S.LONGITUDE as LONGITUDE, "
      //WSE:
        + "case when EDR.REFERENCE_POINT_READING is not null and "
        + "EDR.REFERENCE_POINT_ELEVATION is not null and "
        + "EDR.WATER_SURFACE_READING is not null "
        + "THEN "
        + "EDR.REFERENCE_POINT_ELEVATION - (EDR.REFERENCE_POINT_READING - EDR.WATER_SURFACE_READING)"
        + "else NULL "
        + "end "
        + "as WSE, "
      //RPE_WSE:
        + "case when EDR.REFERENCE_POINT_READING is not null and "
        + "EDR.WATER_SURFACE_READING is not null "
        + "THEN EDR.REFERENCE_POINT_READING - EDR.WATER_SURFACE_READING "
        + "else NULL "
        + "end "
        + "as RPE_WSE, "
      //GSE_WSE: GSE - RPE + RDNG_RP - RDNG_WS
        + "case when EDR.GROUND_SURFACE_ELEVATION is not null and "
        + "EDR.REFERENCE_POINT_ELEVATION is not null and "
        + "EDR.REFERENCE_POINT_READING is not null and "
        + "EDR.WATER_SURFACE_READING is not null "
        + "THEN "
        + "EDR.GROUND_SURFACE_ELEVATION - EDR.REFERENCE_POINT_ELEVATION + REFERENCE_POINT_READING - EDR.WATER_SURFACE_READING "
        + "else NULL "
        + "end "
        + "as GSE_WSE, "
      //PRE:
        + "EDR.REFERENCE_POINT_ELEVATION as WLM_RPE, "
      //GSE:
        + "EDR.GROUND_SURFACE_ELEVATION as WLM_GSE, "
      //RDNG_RP:
        + "EDR.REFERENCE_POINT_READING as RDNG_RP, "
      //RDNG_WS:
        + "EDR.WATER_SURFACE_READING as RDNG_WS, "
      
        + "EDR.ORG_ID as WLM_ORG_ID, "
        + "O.ORG_NAME as WLM_ORG_NAME, "
        + "EMMT.EWM_ELEV_MEASURE_METHOD_DESC as WLM_DESC, "
        + "EAT.EWM_ELEVATION_ACCURACY_CD as WLM_ACC_CODE, "
        + "EAT.EWM_ELEVATION_ACCURACY_DESC as WLM_ACC_DESC, "
        + "MIT.EWM_MEASURE_ISSUE_TYPE_CODE as WLM_QA_CODE, "
        + "MIT.EWM_MEASURE_ISSUE_TYPE_DESC as WLM_QA_DESC, "

        + "EDR.COMMENTS as MSMT_CMT, "
        + "EDR.MODIFIED_DATE as WLM_MOD_DATE, "
        + "EDR.MODIFIED_USER as WLM_MOD_USER "
        + "FROM " + EntityUtil.getTableName(ElevationDataReading.class) + " EDR "
        + "LEFT JOIN " + EntityUtil.getTableName(Station.class) + " S "
        + "ON EDR.EWM_STATION_ID = S.EWM_STATION_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(MasterSite.class) + " M "
        + "ON M.EWM_MASTER_SITE_ID = S.EWM_MASTER_SITE_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(Organization.class) + " O "
        + "ON O.ORG_ID = EDR.ORG_ID " 
        + "LEFT JOIN " + EntityUtil.getTableName(ElevMeasureMethodTyp.class) + " EMMT "
        + "ON EMMT.EWM_ELEV_MEASURE_METHOD_TYP_ID = EDR.EWM_ELEV_MEASURE_METHOD_TYP_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(ElevationAccuracyType.class) + " EAT "
        + "ON EDR.EWM_ELEVATION_ACCURACY_TYPE_ID = EAT.EWM_ELEVATION_ACCURACY_TYPE_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(MeasurementIssueType.class) + " MIT "
        + "ON MIT.EWM_MEASUREMENT_ISSUE_TYPE_ID = EDR.EWM_MEASUREMENT_ISSUE_TYPE_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(StationUseType.class) + " SUT "
        + "ON S.EWM_STATION_USE_ID = SUT.EWM_STATION_USE_ID "
//        + "LEFT JOIN " + EntityUtil.getTableName(MeasurementMethodType.class) + " MMT "
//        + "ON MMT.EWM_MEASUREMENT_METHOD_TYPE_ID = EDR.EWM_MEASUREMENT_METHOD_TYPE_ID "
//        + "LEFT JOIN " + EntityUtil.getTableName(MeasurementAccuracyType.class) + " MAT "
//        + "ON MAT.EWM_MEASURE_ACCURACY_TYPE_ID = EDR."
        + "WHERE ";
    return query;
  }
  
  //<editor-fold defaultstate="collapsed" desc="buildTimeseriesExportQuery">
  private String buildTimeseriesExportQuery(List<Integer> params) {
    String query = "SELECT M.SITE_CODE, "
        + "S.EWM_STATION_ID, "
        + "S.EWM_STATE_WELL_NBR, "
        + "S.LOCAL_WELL_DESIGNATION, "
        + "EDR.MEASUREMENT_DT, "
        + "EDR.REFERENCE_POINT_ELEVATION, "
        + "EDR.GROUND_SURFACE_ELEVATION, "
        + "EDR.REFERENCE_POINT_READING, "
        + "case when EDR.REFERENCE_POINT_READING is not null and "
        + "EDR.REFERENCE_POINT_ELEVATION is not null and "
        + "EDR.GROUND_SURFACE_ELEVATION is not null "
        + "THEN "
        + "EDR.REFERENCE_POINT_ELEVATION - EDR.REFERENCE_POINT_READING "
        + "else NULL "
        + "end "
        + "as WSE, "
        + "case when EDR.REFERENCE_POINT_READING is not null and "
        + "EDR.REFERENCE_POINT_ELEVATION is not null and "
        + "EDR.GROUND_SURFACE_ELEVATION is not null "
        + "THEN "
        + "EDR.REFERENCE_POINT_READING - (EDR.REFERENCE_POINT_ELEVATION - EDR.GROUND_SURFACE_ELEVATION) "
        + "else NULL "
        + "end "
        + "as GS_TO_WS, "
        + "EMMT.EWM_ELEV_MEASURE_METHOD_DESC, "
        + "EAT.EWM_ELEVATION_ACCURACY_CD "
        + "FROM " + EntityUtil.getTableName(ElevationDataReading.class) + " EDR "
        + "LEFT JOIN " + EntityUtil.getTableName(Station.class) + " S "
        + "ON EDR.EWM_STATION_ID = S.EWM_STATION_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(MasterSite.class) + " M "
        + "ON M.EWM_MASTER_SITE_ID = S.EWM_MASTER_SITE_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(ElevMeasureMethodTyp.class) + " EMMT "
        + "ON EMMT.EWM_ELEV_MEASURE_METHOD_TYP_ID = EDR.EWM_ELEV_MEASURE_METHOD_TYP_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(ElevationAccuracyType.class) + " EAT "
        + "ON EDR.EWM_ELEVATION_ACCURACY_TYPE_ID = EAT.EWM_ELEVATION_ACCURACY_TYPE_ID "
        + "WHERE EDR.EWM_STATION_ID in (%s) "
        + "ORDER BY EDR.EWM_STATION_ID, EDR.MEASUREMENT_DT";
    String in = "";
    for (int i=0; i<params.size(); i++){
      in += "?,";
    }
    in = in.substring(0, in.length()-1);
    return String.format(query, in);
  }
//</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="buildSingleWellElevationDataQuery">
  private String buildSingleWellElevationDataQuery(){
    String query = "SELECT EDR.MEASUREMENT_DT as MSMT_DATE, "
      //WSE:
        + "case when EDR.REFERENCE_POINT_READING is not null and "
        + "EDR.REFERENCE_POINT_ELEVATION is not null and "
        + "EDR.WATER_SURFACE_READING is not null "
        + "THEN "
        + "EDR.REFERENCE_POINT_ELEVATION - (EDR.REFERENCE_POINT_READING - EDR.WATER_SURFACE_READING)"
        + "else NULL "
        + "end "
        + "as WSE, "
      //RPE_WSE:
        + "case when EDR.REFERENCE_POINT_READING is not null and "
        + "EDR.WATER_SURFACE_READING is not null "
        + "THEN EDR.REFERENCE_POINT_READING - EDR.WATER_SURFACE_READING "
        + "else NULL "
        + "end "
        + "as RPE_WSE, "
      //GSE_WSE: GSE - RPE + RDNG_RP - RDNG_WS
        + "case when EDR.GROUND_SURFACE_ELEVATION is not null and "
        + "EDR.REFERENCE_POINT_ELEVATION is not null and "
        + "EDR.REFERENCE_POINT_READING is not null and "
        + "EDR.WATER_SURFACE_READING is not null "
        + "THEN "
        + "EDR.GROUND_SURFACE_ELEVATION - EDR.REFERENCE_POINT_ELEVATION + REFERENCE_POINT_READING - EDR.WATER_SURFACE_READING "
        + "else NULL "
        + "end "
        + "as GSE_WSE, "
      //PRE:
        + "EDR.REFERENCE_POINT_ELEVATION as WLM_RPE, "
      //GSE:
        + "EDR.GROUND_SURFACE_ELEVATION as WLM_GSE, "
      //RDNG_RP:
        + "EDR.REFERENCE_POINT_READING as RDNG_RP, "
      //RDNG_WS:
        + "EDR.WATER_SURFACE_READING as RDNG_WS, "
      
        + "EDR.ORG_ID as WLM_ORG_ID, "
        + "O.ORG_NAME as WLM_ORG_NAME, "
        + "MIT.EWM_MEASURE_ISSUE_TYPE_DESC as WLM_QA_DESC, "
      
        + "EDR.COMMENTS as MSMT_CMT, "
        + "EDR.MODIFIED_DATE as WLM_MOD_DATE, "
        + "EDR.MODIFIED_USER as WLM_MOD_USER "
        + "FROM " + EntityUtil.getTableName(ElevationDataReading.class) + " EDR "
        + "LEFT JOIN " + EntityUtil.getTableName(Station.class) + " S "
        + "ON EDR.EWM_STATION_ID = S.EWM_STATION_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(MasterSite.class) + " M "
        + "ON M.EWM_MASTER_SITE_ID = S.EWM_MASTER_SITE_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(Organization.class) + " O "
        + "ON O.ORG_ID = EDR.ORG_ID " 
        + "LEFT JOIN " + EntityUtil.getTableName(ElevMeasureMethodTyp.class) + " EMMT "
        + "ON EMMT.EWM_ELEV_MEASURE_METHOD_TYP_ID = EDR.EWM_ELEV_MEASURE_METHOD_TYP_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(ElevationAccuracyType.class) + " EAT "
        + "ON EDR.EWM_ELEVATION_ACCURACY_TYPE_ID = EAT.EWM_ELEVATION_ACCURACY_TYPE_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(MeasurementIssueType.class) + " MIT "
        + "ON MIT.EWM_MEASUREMENT_ISSUE_TYPE_ID = EDR.EWM_MEASUREMENT_ISSUE_TYPE_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(MeasurementMethodType.class) + " MMT "
        + "ON MMT.EWM_MEASUREMENT_METHOD_TYPE_ID = S.VERTICAL_MEASURE_METHOD_ID "
        + "WHERE S.EWM_STATIOIN_ID = ?";
    return query;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="buildElevationDataQuery">
  private String buildElevationDataQuery(List<Integer> stationIds, String startDate, String endDate) {
    Integer N = stationIds.size() / 1000;
    Integer m = stationIds.size() % 1000;
    
    String query = "SELECT M.SITE_CODE as SITE_CODE, "
        + "S.LOCAL_WELL_DESIGNATION as WELL_NAME, "
        + "S.EWM_STATE_WELL_NBR as SWN, "
        + "S.EWM_STATION_ID as STN_ID, "
        + "EDR.MEASUREMENT_DT as MSMT_DATE, "
      //WSE:
        + "case when EDR.REFERENCE_POINT_READING is not null and "
        + "EDR.REFERENCE_POINT_ELEVATION is not null and "
        + "EDR.WATER_SURFACE_READING is not null "
        + "THEN "
        + "EDR.REFERENCE_POINT_ELEVATION - (EDR.REFERENCE_POINT_READING - EDR.WATER_SURFACE_READING)"
        + "else NULL "
        + "end "
        + "as WSE, "
      //RPE_WSE:
        + "case when EDR.REFERENCE_POINT_READING is not null and "
        + "EDR.WATER_SURFACE_READING is not null "
        + "THEN EDR.REFERENCE_POINT_READING - EDR.WATER_SURFACE_READING "
        + "else NULL "
        + "end "
        + "as RPE_WSE, "
      //GSE_WSE: GSE - RPE + RDNG_RP - RDNG_WS
        + "case when EDR.GROUND_SURFACE_ELEVATION is not null and "
        + "EDR.REFERENCE_POINT_ELEVATION is not null and "
        + "EDR.REFERENCE_POINT_READING is not null and "
        + "EDR.WATER_SURFACE_READING is not null "
        + "THEN "
        + "EDR.GROUND_SURFACE_ELEVATION - EDR.REFERENCE_POINT_ELEVATION + REFERENCE_POINT_READING - EDR.WATER_SURFACE_READING "
        + "else NULL "
        + "end "
        + "as GSE_WSE, "
      //PRE:
        + "EDR.REFERENCE_POINT_ELEVATION as WLM_RPE, "
      //GSE:
        + "EDR.GROUND_SURFACE_ELEVATION as WLM_GSE, "
      //RDNG_RP:
        + "EDR.REFERENCE_POINT_READING as RDNG_RP, "
      //RDNG_WS:
        + "EDR.WATER_SURFACE_READING as RDNG_WS, "
      
        + "EDR.ORG_ID as WLM_ORG_ID, "
        + "O.ORG_NAME as WLM_ORG_NAME, "
        + "EMMT.EWM_ELEV_MEASURE_METHOD_DESC as WLM_DESC, "
        + "EAT.EWM_ELEVATION_ACCURACY_CD as WLM_ACC_CODE, "
        + "EAT.EWM_ELEVATION_ACCURACY_DESC as WLM_ACC_DESC, "
        + "MIT.EWM_MEASURE_ISSUE_TYPE_CODE as WLM_QA_CODE, "
        + "MIT.EWM_MEASURE_ISSUE_TYPE_DESC as WLM_QA_DESC, "

        + "EDR.COMMENTS as MSMT_CMT, "
        + "EDR.MODIFIED_DATE as WLM_MOD_DATE, "
        + "EDR.MODIFIED_USER as WLM_MOD_USER "
        + "FROM " + EntityUtil.getTableName(ElevationDataReading.class) + " EDR "
        + "LEFT JOIN " + EntityUtil.getTableName(Station.class) + " S "
        + "ON EDR.EWM_STATION_ID = S.EWM_STATION_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(MasterSite.class) + " M "
        + "ON M.EWM_MASTER_SITE_ID = S.EWM_MASTER_SITE_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(Organization.class) + " O "
        + "ON O.ORG_ID = EDR.ORG_ID " 
        + "LEFT JOIN " + EntityUtil.getTableName(ElevMeasureMethodTyp.class) + " EMMT "
        + "ON EMMT.EWM_ELEV_MEASURE_METHOD_TYP_ID = EDR.EWM_ELEV_MEASURE_METHOD_TYP_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(ElevationAccuracyType.class) + " EAT "
        + "ON EDR.EWM_ELEVATION_ACCURACY_TYPE_ID = EAT.EWM_ELEVATION_ACCURACY_TYPE_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(MeasurementIssueType.class) + " MIT "
        + "ON MIT.EWM_MEASUREMENT_ISSUE_TYPE_ID = EDR.EWM_MEASUREMENT_ISSUE_TYPE_ID "
//        + "LEFT JOIN " + EntityUtil.getTableName(MeasurementMethodType.class) + " MMT "
//        + "ON MMT.EWM_MEASUREMENT_METHOD_TYPE_ID = EDR.EWM_MEASUREMENT_METHOD_TYPE_ID "
//        + "LEFT JOIN " + EntityUtil.getTableName(MeasurementAccuracyType.class) + " MAT "
//        + "ON MAT.EWM_MEASURE_ACCURACY_TYPE_ID = EDR."
        + "WHERE (";
    //<editor-fold defaultstate="collapsed" desc="Original Query">
//    String query = "SELECT M.SITE_CODE, "
//        + "S.EWM_STATION_ID, "
//        + "S.EWM_STATE_WELL_NBR, "
//        + "S.LOCAL_WELL_DESIGNATION, "
//        + "EDR.MEASUREMENT_DT, "
//        + "EDR.REFERENCE_POINT_ELEVATION, "
//        + "EDR.GROUND_SURFACE_ELEVATION, "
//        + "EDR.REFERENCE_POINT_READING, "
//        + "case when EDR.REFERENCE_POINT_READING is not null and "
//        + "EDR.REFERENCE_POINT_ELEVATION is not null and "
//        + "EDR.GROUND_SURFACE_ELEVATION is not null "
//        + "THEN "
//        + "EDR.REFERENCE_POINT_ELEVATION - EDR.REFERENCE_POINT_READING "
//        + "else NULL "
//        + "end "
//        + "as WSE, "
//        + "case when EDR.REFERENCE_POINT_READING is not null and "
//        + "EDR.REFERENCE_POINT_ELEVATION is not null and "
//        + "EDR.GROUND_SURFACE_ELEVATION is not null "
//        + "THEN "
//        + "EDR.REFERENCE_POINT_READING - (EDR.REFERENCE_POINT_ELEVATION - EDR.GROUND_SURFACE_ELEVATION) "
//        + "else NULL "
//        + "end "
//        + "as GS_TO_WS, "
//        + "EMMT.EWM_ELEV_MEASURE_METHOD_DESC, "
//        + "EAT.EWM_ELEVATION_ACCURACY_CD "
//        + "FROM " + EntityUtil.getTableName(ElevationDataReading.class) + " EDR "
//        + "LEFT JOIN " + EntityUtil.getTableName(Station.class) + " S "
//        + "ON EDR.EWM_STATION_ID = S.EWM_STATION_ID "
//        + "LEFT JOIN " + EntityUtil.getTableName(MasterSite.class) + " M "
//        + "ON M.EWM_MASTER_SITE_ID = S.EWM_MASTER_SITE_ID "
//        + "LEFT JOIN " + EntityUtil.getTableName(ElevMeasureMethodTyp.class) + " EMMT "
//        + "ON EMMT.EWM_ELEV_MEASURE_METHOD_TYP_ID = EDR.EWM_ELEV_MEASURE_METHOD_TYP_ID "
//        + "LEFT JOIN " + EntityUtil.getTableName(ElevationAccuracyType.class) + " EAT "
//        + "ON EDR.EWM_ELEVATION_ACCURACY_TYPE_ID = EAT.EWM_ELEVATION_ACCURACY_TYPE_ID "
//        + "WHERE (";
//</editor-fold>
    String s = "?,";
    for(int n=0; n<N; n++){ //n: index of sublist 
      String in = String.format("%0" + 1000 + "d", 0).replace("0",s);
      in = in.substring(0, in.length()-1);
      query += "EDR.EWM_STATION_ID in (%s) "; 
      query = String.format(query, in);
      query += " OR ";
    }
    if(m != 0){
      query += "EDR.EWM_STATION_ID in (%s) "; 
      String in = String.format("%0" + m + "d", 0).replace("0",s);
      in = in.substring(0, in.length()-1);
      query = String.format(query, in);
    }
    query += ")";
    if(startDate != null){
      query += "AND ( EDR.MEASUREMENT_DT > TO_DATE(?, 'MM/DD/YYYY') )";
    }
    if(endDate != null){
      query += "AND ( EDR.MEASUREMENT_DT < TO_DATE(?, 'MM/DD/YYYY') )";
    }
    query += "ORDER BY EDR.EWM_STATION_ID, EDR.MEASUREMENT_DT";
    return query;
  }
//</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="buildAllElevationDataQuery">
  private String buildAllElevationDataQuery() {
    String query = "SELECT M.SITE_CODE as SITE_CD, "
        + "S.LOCAL_WELL_DESIGNATION as WELL_ID, "
        + "S.EWM_STATE_WELL_NBR as SWN, "
        + "S.EWM_STATION_ID as STN_ID, "
        + "EDR.MEASUREMENT_DT as MSMT_DATE, "
      //WSE:
        + "case when EDR.REFERENCE_POINT_READING is not null and "
        + "EDR.REFERENCE_POINT_ELEVATION is not null and "
        + "EDR.WATER_SURFACE_READING is not null "
        + "THEN "
        + "EDR.REFERENCE_POINT_ELEVATION - (EDR.REFERENCE_POINT_READING - EDR.WATER_SURFACE_READING)"
        + "else NULL "
        + "end "
        + "as WSE, "
      //RPE_WSE:
        + "case when EDR.REFERENCE_POINT_READING is not null and "
        + "EDR.WATER_SURFACE_READING is not null "
        + "THEN EDR.REFERENCE_POINT_READING - EDR.WATER_SURFACE_READING "
        + "else NULL "
        + "end "
        + "as RPE_WSE, "
      //GSE_WSE: GSE - RPE + RDNG_RP - RDNG_WS
        + "case when EDR.GROUND_SURFACE_ELEVATION is not null and "
        + "EDR.REFERENCE_POINT_ELEVATION is not null and "
        + "EDR.REFERENCE_POINT_READING is not null and "
        + "EDR.WATER_SURFACE_READING is not null "
        + "THEN "
        + "EDR.GROUND_SURFACE_ELEVATION - EDR.REFERENCE_POINT_ELEVATION + REFERENCE_POINT_READING - EDR.WATER_SURFACE_READING "
        + "else NULL "
        + "end "
        + "as GSE_WSE, "
      //PRE:
        + "EDR.REFERENCE_POINT_ELEVATION as WLM_RPE, "
      //GSE:
        + "EDR.GROUND_SURFACE_ELEVATION as WLM_GSE, "
      //RDNG_RP:
        + "EDR.REFERENCE_POINT_READING as RDNG_RP, "
      //RDNG_WS:
        + "EDR.WATER_SURFACE_READING as RDNG_WS, "
      
        + "EDR.ORG_ID as WLM_ORG_ID, "
        + "O.ORG_NAME as WLM_ORG_NAME, "
        + "EMMT.EWM_ELEV_MEASURE_METHOD_DESC as EL_MSMT_DSC, "
        + "EAT.EWM_ELEVATION_ACCURACY_CD as EL_ACC_CD, "
        + "EAT.EWM_ELEVATION_ACCURACY_DESC as WLM_ACC_DSC, "
        + "MIT.EWM_MEASURE_ISSUE_TYPE_CODE as WLM_Q_CD, "
        + "MIT.EWM_MEASURE_ISSUE_TYPE_DESC as WLM_Q_DSC, "
        + "EDR.COMMENTS as MSMT_CMT, "
        + "EDR.MODIFIED_DATE as WLM_MOD_DATE, "
        + "EDR.MODIFIED_USER as WLM_MOD_USER "
        + "FROM " + EntityUtil.getTableName(ElevationDataReading.class) + " EDR "
        + "LEFT JOIN " + EntityUtil.getTableName(Station.class) + " S "
        + "ON EDR.EWM_STATION_ID = S.EWM_STATION_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(MasterSite.class) + " M "
        + "ON M.EWM_MASTER_SITE_ID = S.EWM_MASTER_SITE_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(Organization.class) + " O "
        + "ON O.ORG_ID = EDR.ORG_ID " 
        + "LEFT JOIN " + EntityUtil.getTableName(ElevMeasureMethodTyp.class) + " EMMT "
        + "ON EMMT.EWM_ELEV_MEASURE_METHOD_TYP_ID = EDR.EWM_ELEV_MEASURE_METHOD_TYP_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(ElevationAccuracyType.class) + " EAT "
        + "ON EDR.EWM_ELEVATION_ACCURACY_TYPE_ID = EAT.EWM_ELEVATION_ACCURACY_TYPE_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(MeasurementIssueType.class) + " MIT "
        + "ON MIT.EWM_MEASUREMENT_ISSUE_TYPE_ID = EDR.EWM_MEASUREMENT_ISSUE_TYPE_ID "
        + "ORDER BY EDR.EWM_STATION_ID, EDR.MEASUREMENT_DT";
    return query;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Headers & Maps - For README">
  public List<String> periodicDownloadHeaders() {
    List<String> headerNames = new ArrayList<String>();
    headerNames.add("STN_ID");
    headerNames.add("MSMT_DATE");
    headerNames.add("LAST_RPE");
    headerNames.add("LAST_GSE");
    headerNames.add("RPE_WSE");
    headerNames.add("WSE");
    headerNames.add("GSE_WSE");
    headerNames.add("WLM_METHOD");
    headerNames.add("WLM_ACC");
    headerNames.add("WLM_ORG_ID");
    headerNames.add("MSMT_CMT");
    return headerNames;
  }
  
  public HashMap<String, String[]> periodicDownloadDescriptionMap() {
    HashMap<String, String[]> result = new HashMap<>();
    result.put("STN_ID", new String[]{"Station ID", "Number"});
    result.put("MSMT_DATE", new String[]{"Water Level Measurement Date (PST)", "Date (MM/DD/YY)"});
    result.put("LAST_RPE", new String[]{"Reference Point Elevation", "Number"});
    result.put("LAST_GSE", new String[]{"Ground Surface Elevation", "Number"});
    result.put("RPE_WSE", new String[]{"RPE to WSE", "Number"});
    result.put("WSE", new String[]{"WS Elevation", "Number"});
    result.put("GSE_WSE", new String[]{"GSE to WSE", "Number"});
    result.put("WLM_METHOD", new String[]{"Water Level Measurement Method", "Char"});
    result.put("WLM_ACC", new String[]{"Water Level Measurement Accuracy", "Char"});
    result.put("WLM_ORG_ID", new String[]{"Water Level Measurement Organization ID", "Number"});
    result.put("MSMT_CMT", new String[]{"Water Level Measurement Comments", "Char"});
    return result;
  }
   
  public static List<String> waterlevelDownloadFields(){
    int size = waterlevelDownloadQueryFields().size();
    return waterlevelDownloadQueryFields().subList(2, size);
  }
  
  public static List<String> waterlevelDownloadQueryFields() {
    List<String> headerNames = new ArrayList<String>();
    headerNames.add("LATITUDE"); //Only for spatial filter; Excluded from download
    headerNames.add("LONGITUDE"); //Only for spatial filter; Excluded from download
    headerNames.add("SITE_CODE");
    headerNames.add("WELL_NAME");
    headerNames.add("SWN");
    headerNames.add("STN_ID");
    headerNames.add("MSMT_DATE");
    headerNames.add("WSE");
    headerNames.add("RPE_WSE");
    headerNames.add("GSE_WSE");
    headerNames.add("WLM_RPE");
    headerNames.add("WLM_GSE");
    headerNames.add("RDNG_RP");
    headerNames.add("RDNG_WS");
    headerNames.add("WLM_ORG_ID");
    headerNames.add("WLM_ORG_NAME");
    headerNames.add("WLM_DESC");
    headerNames.add("WLM_ACC_CODE");
    headerNames.add("WLM_ACC_DESC");
    headerNames.add("WLM_QA_CODE");
    headerNames.add("WLM_QA_DESC");
    headerNames.add("MSMT_CMT");
    headerNames.add("WLM_MOD_DATE");
    headerNames.add("WLM_MOD_USER");
    return headerNames;
  }
  
  // <header, {description, data type}
  public static HashMap<String, String[]> timeseriesExportDescriptionMap() {
    HashMap<String, String[]> result = new HashMap<>();
    result.put("SITE_CODE", new String[]{"Site Code", "Char"});
    result.put("WELL_NAME", new String[]{"Local Well Name", "Char"});
    result.put("SWN", new String[]{"State Well Number", "Char"});
    result.put("STN_ID", new String[]{"Station ID", "Number"});
    result.put("MSMT_DATE", new String[]{"Water Level Measurement Date (PST)", "Date (MM/DD/YY HH:MI)"});
    result.put("WSE", new String[]{"WS Elevation", "Number"});
    result.put("RPE_WSE", new String[]{"RPE to WSE", "Number"});
    result.put("GSE_WSE", new String[]{"GSE to WSE", "Number"});
    result.put("WLM_RPE", new String[]{"RPE for a specific water level measurement record", "Number"});
    result.put("WLM_GSE", new String[]{"GSE for a specific water level measurement record", "Number"});
    result.put("RDNG_RP", new String[]{"Reading at RP", "Number"});
    result.put("RDNG_WS", new String[]{"Reading at WS", "Number"});
    result.put("WLM_ORG_ID", new String[]{"Water Level Measurement Organization ID", "Number"});
    result.put("WLM_ORG_NAME", new String[]{"Water Level Measurement Organization/Agency Name", "Char"});
    result.put("WLM_DESC", new String[]{"Water Level Measurement Method Description", "Char"});
    result.put("WLM_ACC_CODE", new String[]{"Water Level Measurement Accuracy Code", "Char"});
    result.put("WLM_ACC_DESC", new String[]{"Water Level Measurement Accuracy Description", "Char"});
    result.put("WLM_QA_CODE", new String[]{"Water Level Measurement Quality Code", "Number"});
    result.put("WLM_QA_DESC", new String[]{"Water Level Measurement Quality Description", "Char"});
    result.put("MSMT_CMT", new String[]{"Water Level Measurement  Comments", "Char"});
    result.put("WLM_MOD_DATE", new String[]{"Last Modified Date", "Date (MM/DD/YY HH:MI)"});
    result.put("WLM_MOD_USER", new String[]{"Last Modified By", "Char"});
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="writeTimeseriesExportTEST">
  public ElevationDataReadingCollection writeTimeseriesExportTEST(OutputStream ostream, String whereStatement) {
    // used for multiple station ids
    CSVWriter writer = new CSVWriter(new OutputStreamWriter(ostream));
    String query = buildTimeseriesExportQueryTEST(whereStatement);
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
  
  private String buildTimeseriesExportQueryTEST(String whereStatement) {
    String query = "SELECT M.SITE_CODE, "
        + "S.EWM_STATION_ID, "
        + "S.EWM_STATE_WELL_NBR, "
        + "S.LOCAL_WELL_DESIGNATION, "
        + "EDR.MEASUREMENT_DT, "
        + "EDR.REFERENCE_POINT_ELEVATION, "
        + "EDR.GROUND_SURFACE_ELEVATION, "
        + "EDR.REFERENCE_POINT_READING, "
        + "case when EDR.REFERENCE_POINT_READING is not null and "
        + "EDR.REFERENCE_POINT_ELEVATION is not null and "
        + "EDR.GROUND_SURFACE_ELEVATION is not null "
        + "THEN "
        + "EDR.REFERENCE_POINT_ELEVATION - EDR.REFERENCE_POINT_READING "
        + "else NULL "
        + "end "
        + "as WSE, "
        + "case when EDR.REFERENCE_POINT_READING is not null and "
        + "EDR.REFERENCE_POINT_ELEVATION is not null and "
        + "EDR.GROUND_SURFACE_ELEVATION is not null "
        + "THEN "
        + "EDR.REFERENCE_POINT_READING - (EDR.REFERENCE_POINT_ELEVATION - EDR.GROUND_SURFACE_ELEVATION) "
        + "else NULL "
        + "end "
        + "as GS_TO_WS, "
        + "EMMT.EWM_ELEV_MEASURE_METHOD_DESC, "
        + "EAT.EWM_ELEVATION_ACCURACY_CD "
        + "FROM " + EntityUtil.getTableName(ElevationDataReading.class) + " EDR "
        + "LEFT JOIN " + EntityUtil.getTableName(Station.class) + " S "
        + "ON EDR.EWM_STATION_ID = S.EWM_STATION_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(MasterSite.class) + " M "
        + "ON M.EWM_MASTER_SITE_ID = S.EWM_MASTER_SITE_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(ElevMeasureMethodTyp.class) + " EMMT "
        + "ON EMMT.EWM_ELEV_MEASURE_METHOD_TYP_ID = EDR.EWM_ELEV_MEASURE_METHOD_TYP_ID "
        + "LEFT JOIN " + EntityUtil.getTableName(ElevationAccuracyType.class) + " EAT "
        + "ON EDR.EWM_ELEVATION_ACCURACY_TYPE_ID = EAT.EWM_ELEVATION_ACCURACY_TYPE_ID "
        + whereStatement + " "
        + "ORDER BY EDR.EWM_STATION_ID, EDR.MEASUREMENT_DT";
    
    return query;
  }
  //</editor-fold>

}