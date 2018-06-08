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
public class ColumnFilter implements Serializable {
  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  private String mFieldName;
  private String mSubquery;
  private List<Object> mValues;

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mFieldName = null;
    this.mSubquery = null;
    this.mValues = null;
  }
  //</editor-fold>
  
  /**
   * 
   * @param fieldname 
   */
  public ColumnFilter(String fieldname) {
    this(fieldname,null);
  }
  
//  /**
//   * 
//   * @param fieldname
//   * @param subquery 
//   */
//  public ColumnFilter(String fieldname,String subquery) {
//    this(fieldname,subquery,null);
//  }
  
  /**
   * 
   * @param fieldname
   * @param subquery
   * @param values 
   */
  public ColumnFilter(String fieldname, String subquery, Object...values) {
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
    this.mSubquery = subquery;
    
    if ((values != null) && (values.length > 0)) {
      this.mValues = new ArrayList<>();
      this.mValues.addAll(Arrays.asList(values));
    }
  }

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
    String colName = null;
    if (((colName = EntityUtil.getColumnName(entityClass, toString()))!=null) && (this.isQuery())) {
      result = String.format("(%s) AS \"%s\"",this.mSubquery,colName);
    }
    else {
      result = "\"" + colName + "\"";
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
  public static ColumnFilter createInstance(String fieldname) {
    return createInstance(fieldname,null);
  }
  
//  /**
//   * 
//   * @param fieldname
//   * @param subquery
//   * @return 
//   */
//  public static ColumnFilter createInstance(String fieldname,String subquery) {
//    return createInstance(fieldname,subquery,null);
//  }
  
  /**
   * 
   * @param fieldname
   * @param subquery
   * @param values
   * @return 
   */
  public static ColumnFilter createInstance(String fieldname, String subquery, Object...values) {
    return new ColumnFilter(fieldname,subquery,values);
  }
  //</editor-fold>
}
