package entity.core.filter;

import entity.core.EntityBase;
import entity.core.annotation.EntityColumn;
import entity.core.enums.FilterOperator;
import entity.core.util.EntityUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author soi
 */
public class GroupByFilter {
  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  private List<String> mFieldNames;
  private FilterOperator mOperator;
  private Object mValue;

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mFieldNames = null;
    this.mOperator = null;
    this.mValue = null;
  }
  //</editor-fold>
  
  public GroupByFilter(String... fnames){
    this(fnames,null,null);
  }
  
  public GroupByFilter(String[] fnames, FilterOperator op, Object value){
    mFieldNames = new ArrayList<>();
    mFieldNames.addAll(Arrays.asList(fnames));
    mOperator = op;
    mValue = value;
  }
  
  public GroupByFilter setValue(Object value){
    mValue = value;
    return this;
  }
  
  public GroupByFilter addFieldName(String fname){
    mFieldNames.add(fname);
    return this;
  }
  
  
  public String toString(Class<? extends EntityBase> entityClass){
    String result = " GROUP BY ";
    EntityColumn annot;
    for (String field : mFieldNames){
      if ((annot = EntityUtil.getEntityColumn(entityClass, field)) != null){
        result += annot.name();
      }
    }
    if (mOperator != null){
      
    }
    
    return result;
  }
}
