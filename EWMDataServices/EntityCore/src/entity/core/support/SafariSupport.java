package entity.core.support;

import entity.core.util.ExceptionUtil;
import entity.core.util.WebUtil;

/**
 *
 * @author clay
 */
public class SafariSupport extends BrowserSupport {
  //<editor-fold defaultstate="expanded" desc="Constants">
  public final static String BROWSER = "Safari";
  public final static Double VERSION = 51.0;
  public final static String SHORTNAME = "safari";
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  private ChromeSupport mChrome;

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mChrome = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructor">
  public SafariSupport() {
    super(BROWSER, VERSION, SHORTNAME);
    this.mChrome = new ChromeSupport();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Browser Support Implementations">
  @Override
  public Double parseVersion() {
    Double result = null;
    try {
      // Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2
      result = Double.parseDouble(WebUtil.getUserAgent().replaceAll("^.*?Version/(\\d+)\\.(\\d+).*$", "$1$2"));
    }
    catch(Exception ex) {
      ExceptionUtil.throwIllegalStateException(ex);
    }
    return result;
  }

  @Override
  public boolean isBrowser() {
    String ua = WebUtil.getUserAgent();
    boolean isSafari = (!this.mChrome.isBrowser() && (ua != null && ua.contains("Safari/")));
    boolean isSafari5 = (!this.mChrome.isBrowser() && (ua != null && ua.contains("Safari/") && ua.contains("Version/5.")));
    return isSafari || isSafari5;
  }
  //</editor-fold>
}
