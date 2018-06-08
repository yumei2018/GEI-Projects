package gov.ca.water.entity.ewm.collection;

import com.opencsv.CSVWriter;
import entity.core.EntityCollection;
import entity.core.delegate.QueryDelegate;
import entity.core.filter.ConditionFilter;
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
import static gov.ca.water.entity.ewm.collection.StationCollection.getFieldsMap;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author clay
 */
public class StationPerforationCollection extends EntityCollection<StationPerforation> {
  
  //<editor-fold defaultstate="expanded" desc="Private Properties">

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public StationPerforationCollection() {
    super(StationPerforation.class);
  }
  //</editor-fold>

  public StationPerforationCollection load(Station station) {
    if (station != null && station.isLoaded()){
      this.findAll(
          ConditionFilter.createInstance("ewmStationId", ConditionFilter.ConditionOperator.SQL_EQUAL, station.getEwmStationId())
      );
    }
    return this;
  }

  public Double getBottomMax() {
    Double bottomMin = null;
    if (!this.isEmpty()){
      bottomMin = this.get(0).getPerforationBottomMsrmnt();
      for (StationPerforation perf : this){
        if (bottomMin < perf.getPerforationBottomMsrmnt()){
          bottomMin = perf.getPerforationBottomMsrmnt();
        }
      }
    }
    return bottomMin;
  }

  public Double getTopMin() {
    Double topMax = null;
    if (!this.isEmpty()){
      topMax = this.get(0).getPerforationTopMsrmnt();
      for (StationPerforation perf : this){
        if (topMax > perf.getPerforationTopMsrmnt()){
          topMax = perf.getPerforationTopMsrmnt();
        }
      }
    }
    return topMax;
  }
  
  public StationPerforationCollection writePerforationExport(final ZipOutputStream zos, String queryData){
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
    
    //<editor-fold defaultstate="collapsed" desc="query">
    String query = "SELECT S.EWM_STATION_ID as STN_ID, "
      + "S.EWM_STATE_WELL_NBR as SWN, "
      + "M.SITE_CODE as SITE_CD, "
      + "P.PERFORATION_TOP_MSRMNT as TOP_PRF, "
      + "P.PERFORATION_BOTTOM_MSRMNT as BOT_PRF "
      + "FROM " + EntityUtil.getTableName(StationPerforation.class) + " P "
      + "LEFT JOIN " + EntityUtil.getTableName(Station.class) + " S "
      + "ON P.EWM_STATION_ID = S.EWM_STATION_ID "
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
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="where">    
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
    
    query += "ORDER BY P.EWM_STATION_ID ";
//    final String[] header="TOP_PRF, BOT_PRF, STN_ID, SWN, SITE_CD".split(",");
    CSVWriter writer = new CSVWriter(new OutputStreamWriter(zos));
    
    this.runQuery(query, params, new QueryDelegate(writer) {
        @Override
        public void handle(ResultSet rs) throws Exception {
          CSVWriter writer = getListener();
          writer.writeAll(rs, true);
          writer.flush();
        }
      });
    
    return this;
  }
  
  @Deprecated
  //<editor-fold defaultstate="collapsed" desc="writePerforationExport(final ZipOutputStream zos, List<Integer> stationIds)">
  public StationPerforationCollection writePerforationExport(final ZipOutputStream zos, List<Integer> stationIds) {
    String query = buildPerforationExportQuery(stationIds);
    final String[] header="TOP_PRF, BOT_PRF, STN_ID, SWN, SITE_CD".split(",");
    this.runQuery(query, stationIds, new QueryDelegate(zos) {
        @Override
        public void handle(ResultSet rs) throws Exception {
          CSVWriter writer = new CSVWriter(new OutputStreamWriter(zos));
          Integer lastStationId = null;
          while (rs.next()){
            Integer currentId = rs.getInt("STN_ID");
            if(currentId != null && !Objects.equals(currentId, 0)){
              String s1 = rs.getString("TOP_PRF");
              String s2 = rs.getString("BOT_PRF");
              String s3 = rs.getString("STN_ID");
              String s4 = rs.getString("SWN");
              String s5 = rs.getString("SITE_CD");
              String [] record = new String[] {s1, s2, s3, s4, s5};
              if(lastStationId == null || !Objects.equals(currentId, lastStationId)){
                if(lastStationId != null){
                  zos.closeEntry();
                }
                zos.putNextEntry(new ZipEntry("Station_" + currentId + "_Perforation.csv"));
                writer.writeNext(header);
              }
              writer.writeNext(record);
              writer.flush();
              lastStationId = currentId;
            }
          }
        }
      }
    );
    return this;
  }
  //</editor-fold>
  
  public StationPerforationCollection writeAll(final ZipOutputStream zos){
    String query = "SELECT P.PERFORATION_TOP_MSRMNT as TOP_PRF, "
      + "P.PERFORATION_BOTTOM_MSRMNT as BOT_PRF, "
      + "S.EWM_STATION_ID as STN_ID, "
      + "S.EWM_STATE_WELL_NBR as SWN, "
      + "M.SITE_CODE as SITE_CD "
      + "FROM " + EntityUtil.getTableName(StationPerforation.class) + " P "
      + "LEFT JOIN " + EntityUtil.getTableName(Station.class) + " S ON P.EWM_STATION_ID = S.EWM_STATION_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(MasterSite.class) + " M ON S.EWM_MASTER_SITE_ID = M.EWM_MASTER_SITE_ID "
      + "ORDER BY P.EWM_STATION_ID"; 
    final String[] header="TOP_PRF, BOT_PRF, STN_ID, SWN, SITE_CD".split(",");
    this.runQuery(query, null, new QueryDelegate(zos){
      @Override
      public void handle(ResultSet rs) throws Exception{
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(zos));
        Integer lastStationId = null;
        while(rs.next()){
          Integer currentId = rs.getInt("STN_ID");
          if(currentId != null && !Objects.equals(currentId, 0)){
            String s1 = rs.getString("TOP_PRF");
            String s2 = rs.getString("BOT_PRF");
            String s3 = rs.getString("STN_ID");
            String s4 = rs.getString("SWN");
            String s5 = rs.getString("SITE_CD");
            String [] record = new String[] {s1, s2, s3, s4, s5};
            if(lastStationId == null || !Objects.equals(currentId, lastStationId)){
              if(lastStationId != null){
                zos.closeEntry();
              }
              zos.putNextEntry(new ZipEntry("Station_" + currentId + "_Perforation.csv"));
              writer.writeNext(header);
            }
            writer.writeNext(record);
            writer.flush();
            lastStationId = currentId;
          }
        }
        zos.closeEntry();
      }
    });
    return this;
  
  }

  
  private String buildPerforationExportQuery(List<Integer> stationIds) {
    Integer N = stationIds.size() / 1000;
    Integer m = stationIds.size() % 1000;
    
   String query = "SELECT P.PERFORATION_TOP_MSRMNT as TOP_PRF, "
      + "P.PERFORATION_BOTTOM_MSRMNT as BOT_PRF, "
      + "S.EWM_STATION_ID as STN_ID, "
      + "S.EWM_STATE_WELL_NBR as SWN, "
      + "M.SITE_CODE as SITE_CD "
      + "FROM " + EntityUtil.getTableName(StationPerforation.class) + " P "
      + "LEFT JOIN " + EntityUtil.getTableName(Station.class) + " S ON P.EWM_STATION_ID = S.EWM_STATION_ID "
      + "LEFT JOIN " + EntityUtil.getTableName(MasterSite.class) + " M ON S.EWM_MASTER_SITE_ID = M.EWM_MASTER_SITE_ID "
      + "WHERE ";
      
    String s = "?,";
    for(int n=0; n<N; n++){ //n: index of sublist 
      String in = String.format("%0" + 1000 + "d", 0).replace("0",s);
      in = in.substring(0, in.length()-1);
      query += "P.EWM_STATION_ID IN (%s)"; 
      query = String.format(query, in);
      query += " OR ";
    }
    
    if(m != 0){
      query += "P.EWM_STATION_ID IN (%s)"; 
      String in = String.format("%0" + m + "d", 0).replace("0",s);
      in = in.substring(0, in.length()-1);
      query = String.format(query, in);
    }
    
    query += "ORDER BY P.EWM_STATION_ID";
    return query;
  }
  
  public List<String> stationPerforationHeaders() {
    List<String> headerNames = new ArrayList<String>();
    headerNames.add("TOP_PRF");
    headerNames.add("BOT_PRF");
    headerNames.add("STN_ID");
    headerNames.add("SWN");
    headerNames.add("SITE_CD");
    return headerNames;
  }
  
  public HashMap<String, String[]> stationPerforationDescriptionMap() {
    HashMap<String, String[]> result = new HashMap<>();
    result.put("TOP_PRF", new String[]{"Perforation top measuremnet", "Number"});
    result.put("BOT_PRF", new String[]{"Perforatiion bottom measurement", "Number"});
    result.put("STN_ID", new String[]{"Unique station identifier", "Number"});
    result.put("SWN", new String[]{"State well number", "Char"});
    result.put("SITE_CD", new String[]{"Site code", "Char"});
    return result;
  }
}