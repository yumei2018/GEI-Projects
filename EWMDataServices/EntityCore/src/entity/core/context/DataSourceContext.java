package entity.core.context;

import entity.core.util.WebUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

/**
 *
 * @author clay
 */
public class DataSourceContext {
  
  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  private Map<String,oracle.ucp.jdbc.PoolDataSourceImpl> mDataSourceMap;

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mDataSourceMap = null;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Static Get Instance">
  public static DataSourceContext getInstance() {
    return WebUtil.getContext(DataSourceContext.class);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Data Source Map">
  public Map<String,oracle.ucp.jdbc.PoolDataSourceImpl> getDataSourceMap() {
    if (this.mDataSourceMap == null) {
      try {
        ApplicationContext appCtx = null;
        if ((appCtx = WebUtil.getApplicationContext()) == null) {
          throw new NullPointerException("The system was unable to retrieve the web application context!");
        }
        
        this.mDataSourceMap = appCtx.getBeansOfType(oracle.ucp.jdbc.PoolDataSourceImpl.class);
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
  
    return this.mDataSourceMap;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Data Source IDs">
  public List<String> getDataSourceIds(){
    List<String> result = null;
    if (this.getDataSourceMap() != null) {
      String[] dses = null;
      if ((dses = this.getDataSourceMap().keySet().toArray(new String[0])) != null) {
        result = Arrays.asList(dses);
      }
    }
    
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Data Source">
  public <DS extends DataSource> DS getDataSource(String id) {
    DS result = null;
    
    try {
      if (StringUtils.isEmpty(id)) {
        throw new NullPointerException("The data source id cannot be unassigned!");
      }
      
      if (this.getDataSourceMap() == null) {
        throw new NullPointerException("The system does have any data source beans!");
      }
      
      if (!this.getDataSourceMap().containsKey(id)) {
        throw new NullPointerException("The data source id is invalid!");
      }
      
      result = (DS) this.getDataSourceMap().get(id);
    } 
    catch (Exception ex)  {
      throw new IllegalStateException(
        String.format("%s.%s %s:\n%s"
          ,this.getClass().getName()
          ,"getDataSourceIds()"
          ,ex.getClass().getName()
          ,ex.getMessage()
        )
      );
    }
  
    return result;
  }
  //</editor-fold>
}