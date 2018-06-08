package entity.core.enums;

/**
 *
 * @author soi
 */
public enum FilterGroupOperator {
  AND(" AND ")
  ,OR(" OR ");
  
  private final String mOperator;
  private FilterGroupOperator(String op){
    mOperator = op;
  }
  
  @Override
  public String toString(){
    return mOperator;
  }
}
