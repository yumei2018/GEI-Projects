package entity.core.delegate;

import java.sql.ResultSet;

/**
 *
 * @author soi
 */
public abstract class QueryDelegate extends DelegateBase {

  /**
   * 
   * @param listener 
   */
  public QueryDelegate(Object listener) {
    super(listener);
  }
  
  /**
   * 
   * @param rs
   * @return 
   */
  public abstract void handle(ResultSet rs) throws Exception;
}
