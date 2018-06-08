package entity.core.exception;

/**
 *
 * @author soi
 */
public class SessionTimeoutException extends IllegalStateException {

  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  private String mCallback;
  
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mCallback = null;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public SessionTimeoutException(String error, String callback) {
    super(error);
    this.setCallback(callback);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Callback">
  /**
   * @return the mCallback
   */
  public String getCallback() {
    return mCallback;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Set Callback">
  /**
   * @param mCallback the mCallback to set
   */
  public void setCallback(String mCallback) {
    this.mCallback = mCallback;
  }
  //</editor-fold>
}
