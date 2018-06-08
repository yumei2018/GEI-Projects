package entity.core.list.base;

import entity.core.context.DataSourceContext;
import entity.core.delegate.JsonDelegate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import oracle.sql.BINARY_DOUBLE;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

/**
 *
 * @author clay
 */
public class GenericCollection extends BaseList<Map> {

  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  private String mDataSourceId;
  private String mQuery;

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mDataSourceId = null;
    this.mQuery = null;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public GenericCollection(String dsId) {
    super(Map.class);
    this.mDataSourceId = dsId;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters/Getters">
  /**
   * @return the mDataSourceId
   */
  public String getDataSourceId() {
    return this.mDataSourceId;
  }

  /**
   * @param mDataSourceId the mDataSourceId to set
   */
  public GenericCollection setDataSourceId(String mDataSourceId) {
    this.mDataSourceId = mDataSourceId;
    return this;
  }

  /**
   * @return the mQuery
   */
  public String getQuery() {
    return this.mQuery;
  }

  /**
   * @param mQuery the mQuery to set
   */
  public GenericCollection setQuery(String mQuery) {
    if (StringUtils.isEmpty(mQuery)) {
      throw new NullPointerException("The parameter mQuery cannot be unassigned!");
    }
    
    if (!Objects.equals(this.mQuery, mQuery)) {
      this.mQuery = mQuery.trim();
      this.validateQuery();
    }
    
    return this;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Private Validations">
  private boolean validateDataSource(){
    if (StringUtils.isEmpty(this.getDataSourceId())) {
      throw new NullPointerException("The data source id cannot be unassigned!");
    }
    return true;
  }
  
  private boolean validateQuery(){
    
    if (StringUtils.isEmpty(this.getQuery())) {
      throw new NullPointerException("The query cannot be unassigned!");
    }
    
    if (!this.getQuery().toUpperCase().matches("^SELECT.*")) {
      throw new IllegalStateException("The query must be a \"SELECT\" query!");
    }
    
    return true;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Validate">
  public boolean validate() {
    return this.validateDataSource() && this.validateQuery();
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Query Methods">
  public GenericCollection findAll(){
    oracle.ucp.jdbc.PoolDataSourceImpl ds = null;
    if ((ds = DataSourceContext.getInstance().getDataSource(this.getDataSourceId())) != null) {
      try {
        this.validate();
        
        try (java.sql.Connection conn = ds.getConnection()) {
          try (PreparedStatement pstmt = conn.prepareStatement(this.getQuery())) {
            try(ResultSet rs = pstmt.executeQuery()) {
              ResultSetMetaData rsmd = rs.getMetaData();
              Map recMap = null;
              String column = null;
              Class valueClass = null;
              while (rs.next()) {
                recMap = new LinkedHashMap();
                for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
                  column = rsmd.getColumnName(i);
                  valueClass = Class.forName(rsmd.getColumnClassName(i));
                  if (valueClass.equals(BINARY_DOUBLE.class)) {
                    valueClass = Double.class;
                  }
                  recMap.put(column, rs.getObject(column, valueClass));
                }
                this.add(recMap);
              }
            }
          }
        }
      } 
      catch (Exception ex) {
        throw new IllegalStateException(
          String.format("%s.%s %s:\n%s"
            ,this.getClass().getName()
            ,"getDataSourceIds()"
            ,ex.getClass().getName()
            ,ex.getMessage()
          )
        );
      }
    }
    return this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="To JSON">
  public JSONArray toJson(){
    return this.toJson(null);
  }
  
  public JSONArray toJson(JsonDelegate delegate){
    JSONArray result = new JSONArray();
    
    if (!this.isEmpty()) {
      Iterator keyIt = null;
      Object key = null;
      JSONObject json = null;
      for (Map m : this) {
        keyIt = m.keySet().iterator();
        while (keyIt.hasNext()) {
          key = keyIt.next();
          if (m.get(key) == null) {
            // set null to empty string
            m.put(key, "");
          }
          else {
            m.put(key, m.get(key).toString());
          }
        }
        json = new JSONObject(m);
        if (delegate != null) {
          delegate.setListener(m);
        }
      }
    }
    
    return result;
  }
  //</editor-fold>
}
