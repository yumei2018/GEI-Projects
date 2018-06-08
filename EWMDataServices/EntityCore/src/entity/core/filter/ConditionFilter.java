package entity.core.filter;

import entity.core.EntityBase;
import entity.core.util.DataUtil;
import entity.core.util.EntityUtil;
import java.io.Serializable;
import java.util.*;
import org.springframework.util.StringUtils;

/**
 *
 * @author Charlie Lay
 */
public class ConditionFilter implements Serializable {
  
  //<editor-fold defaultstate="collapsed" desc="Class Properties">
  /**
   * 
   */
  private String mFieldName;
  /**
   * 
   */
  private List<Object> mValues;
  /**
   * 
   */
  private ConditionOperator mOperator;
  /**
   * 
   */
  private ConditionType mType;
  /**
   * 
   */
  private Boolean mIsFirst;
  
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mFieldName = null;
    this.mOperator = null;
    this.mType = null;
    this.mValues = null;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * 
   * @param name
   * @param op
   * @param values 
   */
  public ConditionFilter(String name, ConditionOperator op, Object... values){
    this(ConditionType.AND,name,op,values);
  }
  
  /**
   * 
   * @param name
   * @param opType
   * @param values 
   */
  public ConditionFilter(ConditionType type, String name, ConditionOperator op, Object... values){
    if (DataUtil.isEmpty(name)) {
      throw new IllegalArgumentException("The name parameter cannot be unassigned!");
    }
    if (op == null){
      throw new IllegalArgumentException("The filter operator parameter cannot be unassigned!");
    }
    this.mFieldName = name;
    this.mOperator = op;
    this.mType = type;
    this.mValues = new ArrayList<>();
    if ((!op.equals(ConditionOperator.SQL_ISNULL)) 
        && (!op.equals(ConditionOperator.SQL_NOTNULL)) 
        && (values != null) && (values.length > 0)) {
      if ((values.length == 1) && (values[0] instanceof Collection)) {
        this.mValues.addAll((Collection)values[0]);
      }
      else {
        this.mValues.addAll(Arrays.asList(values));
      }
    }
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Static Methods">
  /**
   * 
   * @param name
   * @param op
   * @param values
   * @return 
   */
  public static ConditionFilter createInstance(String name, ConditionOperator op, Object... values){
    return new ConditionFilter(name,op,values);
  }
  
  /**
   * 
   * @param type
   * @param name
   * @param op
   * @param values
   * @return 
   */
  public static ConditionFilter createInstance(ConditionType type, String name, ConditionOperator op, Object... values){
    return new ConditionFilter(type, name,op,values);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Condition Enums">
  /**
   * 
   */
  public enum ConditionOperator {
    SQL_LT(" < ")
    ,SQL_LE(" <= ")
    ,SQL_GT(" > ")
    ,SQL_GE(" >= ")
    ,SQL_NOT(" <> ")
    ,SQL_EQUAL(" = ")
    ,SQL_LIKE(" LIKE ")
    ,SQL_ISNULL(" IS NULL ")
    ,SQL_IN(" IN ")
    ,SQL_NOTNULL(" IS NOT NULL ")
    ,SQL_OUT(" NOT IN ")
    ,SQL_BETWEEN(" BETWEEN ");

    /**
     * 
     */
    private final String mOperator;
    
    /**
     * 
     * @param op 
     */
    private ConditionOperator(String op){
      this.mOperator = op;
    }

    @Override
    public String toString(){
      return this.mOperator;
    }
  }
  
  public enum ConditionType {
    OR(" OR ")
    ,AND(" AND ");
    
    /**
     * 
     */
    private String mOperator;
    
    /**
     * 
     * @param op 
     */
    private ConditionType(String op){
      this.mOperator = op;
    }

    @Override
    public String toString() {
      return this.mOperator;
    }
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Overrides">
  @Override
  public boolean equals(Object obj) {
    ConditionFilter otherFilter = (ConditionFilter) obj;
    return (DataUtil.isEqual(this.mFieldName,otherFilter.mFieldName))
            && (DataUtil.isEqual(this.mOperator, otherFilter.mOperator))
            && (this.mValues.equals(otherFilter.mValues));
  }

  @Override
  public int hashCode() {
    return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * 
   * @param <E>
   * @param entity
   * @return 
   */
  public <E extends EntityBase> String toString(Class<E> entityClass) {
    String columnName = null;
    if (StringUtils.isEmpty(columnName = EntityUtil.getColumnName(entityClass,this.mFieldName))) {
      if (this.mFieldName.contains("(")) {
        columnName = this.mFieldName;
      }
      else {
        throw new NullPointerException("The entity cannot find the column "
                                              + "name for the field " + this.mFieldName);
      }
    }
    else {
      columnName = "\"" + columnName + "\"";
    }
    
    if (this.mValues == null) {
      throw new NullPointerException("The filter values cannot be unassigned!");
    }
    
    String result = null;
    
    if (this.mOperator.equals(ConditionOperator.SQL_LIKE)) {
      result = String.format("UPPER(%s) %s", columnName,this.mOperator);
    }
    else {
      result = String.format("%s %s", columnName,this.mOperator);
    }
    
    if ((!this.mOperator.equals(ConditionOperator.SQL_ISNULL))
        && (!this.mOperator.equals(ConditionOperator.SQL_NOTNULL))) {
      if (this.mOperator.equals(ConditionOperator.SQL_BETWEEN)) {
        if (this.mValues.size() != 2) {
          throw new IllegalArgumentException("The between filter has mismatch values (found " + this.mValues.size() + ", requires 2)!");
        }
        result += " ? AND ? ";
      }
      else {
        if (this.mValues.size() == 0) {
          throw new IllegalArgumentException("The filter has mismatch values (found 0, requires 1+)!");
        }
        
        if ((this.mOperator.equals(ConditionOperator.SQL_IN))
              || (this.mOperator.equals(ConditionOperator.SQL_OUT))) {
          String tmp="",comma="";
          for (int i=0;i<this.mValues.size();++i) {
            tmp += comma + "?";
            comma = ",";
          }
          result += "(" + tmp + ")";
        }
        else if (this.mOperator.equals(ConditionOperator.SQL_LIKE)) {
          result += " UPPER(?) ";
        }
        else {
          result += " ? ";
        }
      }
    }
    else if ((this.mValues != null) && (this.mValues.size() > 0)) {
      throw new IllegalArgumentException("The null filter has mismatch values (found " + this.mValues.size() + ", requires 0)");
    }
    
    return "(" + result + ")";
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
  public ConditionType getType(){
    return this.mType;
  }
  
  /**
   * 
   * @return 
   */
  public String getFieldName(){
    return this.mFieldName;
  }
  //</editor-fold>
}
