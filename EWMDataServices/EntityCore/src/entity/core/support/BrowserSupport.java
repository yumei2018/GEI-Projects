package entity.core.support;

import entity.core.util.ExceptionUtil;

/**
 *
 * @author clay
 */
public abstract class BrowserSupport {
  
  //<editor-fold defaultstate="expanded" desc="Private Properties">
  private final String mBrowser;
  private final Double mVersion;
  private final String mShortName;
  
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public BrowserSupport(String browser, Double version) {
    this(browser,version,null);
  }
  
  public BrowserSupport(String browser, Double version, String shortname) {
    this.mBrowser = browser;
    this.mVersion = version;
    this.mShortName = shortname;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Getters">
  public final String getBrowser(){
    return this.mBrowser;
  }
  
  public final Double getVersion(){
    return this.mVersion;
  }
  
  public final String getShortName(){
    return this.mShortName;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Is Supported">
  public final boolean isSupported() {
    boolean result = false;
    try {
      result = this.parseVersion() >= this.getVersion();
    }
    catch(Exception ex) {
      ExceptionUtil.throwIllegalStateException(ex);
    }
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Abstract Is Supported">
  public abstract Double parseVersion();
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Abstract Is Browser">
  public abstract boolean isBrowser();
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="To String Override">
  @Override
  public String toString() {
    return this.getBrowser() + " " + this.getVersion();
  }
  //</editor-fold>
}
