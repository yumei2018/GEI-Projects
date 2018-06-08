package entity.core.delegate;

import java.io.Serializable;

/**
 *
 * @author soi
 */
public class DelegateBase implements Serializable {
  /**
   * 
   */
  private Object mListener;
  
  /**
   * 
   * @param listener 
   */
  public DelegateBase(Object listener) {
    if (listener != null) {
      this.setListener(listener);
    }
  }
  
  /**
   * 
   * @param <TObj>
   * @return 
   */
  public <TObj> TObj getListener(){
    return (TObj)this.mListener;
  }
  
  //<editor-fold defaultstate="collapsed" desc="Set Listener">
  public <D extends DelegateBase> D setListener(Object listener) {
    if (listener == null) {
      throw new NullPointerException(
        String.format("%s.%s Error(s):\n%s"
          ,this.getClass().getName()
          ,"setListener(listener)"
          ,"The listener parameter cannot be unassigned!"
        )
      );
    }
    this.mListener = listener;
    
    return (D) this;
  }
  //</editor-fold>

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    if (this.mListener != null) {
      this.mListener = null;
    }
  }
}