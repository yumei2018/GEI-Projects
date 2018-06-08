package entity.core.delegate;

/**
 *
 * @author soi
 */
public abstract class UpdateDelegate extends DelegateBase {

  /**
   * 
   * @param listener 
   */
  public UpdateDelegate(Object listener) {
    super(listener);
  }
  
  /**
   * 
   * @param o
   * @return
   * @throws Exception 
   */
  public abstract void handle(Integer i) throws Exception;
}
