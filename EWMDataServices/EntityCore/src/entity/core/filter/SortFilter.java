package entity.core.filter;

import entity.core.EntityBase;
import entity.core.util.EntityUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.util.StringUtils;

/**
 *
 * @author soi
 */
public class SortFilter implements Serializable {
  
  //<editor-fold defaultstate="collapsed" desc="Direction Enum">
  public enum SortDirection {
    DESC("DESC")
    ,ASC("ASC");
    
    private String mDir;
    
    private SortDirection(String dir){
      this.mDir = dir;
    }
    
    @Override
    public String toString() {
      return this.mDir;
    }
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  private SortDirection mDirection;
  private String mFieldName;
  private String mSubquery;
  private List<Object> mValues;

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mValues = null;
    this.mDirection = null;
    this.mFieldName = null;
    this.mSubquery = null;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructors">
  public SortFilter(String fieldname) {
    this(fieldname,SortDirection.ASC);
  }
  /**
   *
   * @param fieldname
   */
  public SortFilter(String fieldname,SortDirection dir) {
    this(fieldname,dir,null);
  }
  
  /**
   * 
   * @param fieldname
   * @param subquery 
   */
  public SortFilter(String fieldname,SortDirection dir,String subquery) {
    this(fieldname,dir,subquery,null);
  }
  
  /**
   * 
   * @param fieldname
   * @param subquery
   * @param values 
   */
  public SortFilter(String fieldname,SortDirection dir, String subquery, Object[] values) {
    if (fieldname == null) {
      throw new NullPointerException(
        String.format("%s.%s Error(s):\nThe %s parameter cannot be unassigned!"
          ,this.getClass().getName()
          ,"ColumnFilter(fieldname,subquery,values)"
          ,"fieldname"
        )
      );
    }
    this.mFieldName = fieldname;
    this.mDirection = dir;
    this.mSubquery = subquery;
    
    if ((values != null) && (values.length > 0)) {
      this.mValues = new ArrayList<>();
      this.mValues.addAll(Arrays.asList(values));
    }
  }
  //</editor-fold>
  
  @Override
  public String toString() {
    return this.mFieldName;
  }
  
  /**
   * 
   * @param entityClass
   * @return 
   */
  public String toString(Class<? extends EntityBase> entityClass){
    String result = null;
    if (this.isQuery()) {
      result = String.format("(%s)",this.mSubquery);
    }
    else {
      String colName = null;
      if (StringUtils.isEmpty(colName = EntityUtil.getColumnName(entityClass, this.toString()))) {
        throw new IllegalStateException("Cannot find sort column name!");
      }
      result = String.format("\"%s\"",colName);
    }
    
    if (!StringUtils.isEmpty(result)) {
      result = String.format("ORDER BY %s %s",result,this.mDirection);
    }
    
    return result;
  }
  
  /**
   * 
   * @return 
   */
  public List<Object> getValues(){
    return this.mValues;
  }
  
  /**
   * 
   * @return 
   */
  private boolean isQuery(){
    return !StringUtils.isEmpty(this.mSubquery);
  }
  
  //<editor-fold defaultstate="collapsed" desc="Public Static Methods">
  /**
   *
   * @param fieldname
   * @return
   */
  public static SortFilter createInstance(String fieldname) {
    return createInstance(fieldname,SortDirection.ASC);
  }
  
  /**
   *
   * @param fieldname
   */
  public static SortFilter createInstance(String fieldname,SortDirection dir) {
    return createInstance(fieldname,dir,null);
  }
  
//  /**
//   *
//   * @param fieldname
//   * @param subquery
//   */
//  public static SortFilter createInstance(String fieldname,SortDirection dir,String subquery) {
//    return createInstance(fieldname,dir,subquery,null);
//  }
  
  /**
   *
   * @param fieldname
   * @param subquery
   * @param values
   */
  public static SortFilter createInstance(String fieldname,SortDirection dir, String subquery, Object...values) {
    return new SortFilter(fieldname,dir,subquery,values);
  }
//</editor-fold>
}
