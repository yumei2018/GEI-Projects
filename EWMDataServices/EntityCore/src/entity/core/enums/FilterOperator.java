package entity.core.enums;

/**
 *
 * @author soi
 */
public enum FilterOperator {
  LT(" < ")
  ,LE(" <= ")
  ,GT(" > ")
  ,GE(" >= ")
  ,NOT(" <> ")
  ,EQ(" = ")
  ,LIKE(" LIKE ")
  ,ISNULL(" IS NULL ")
  ,IN(" IN ")
  ,NOTNULL(" IS NOT NULL ")
  ,OUT(" NOT IN ")
  ,BETWEEN(" BETWEEN ");
  
  private final String mOperator;
  
  private FilterOperator(String op){
    mOperator = op;
  }
  
  @Override
  public String toString(){
    return mOperator;
  }
}
