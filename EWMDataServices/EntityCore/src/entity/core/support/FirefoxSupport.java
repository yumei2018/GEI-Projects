package entity.core.support;

import entity.core.util.ExceptionUtil;
import entity.core.util.WebUtil;

/**
 *
 * @author clay
 */
public class FirefoxSupport extends BrowserSupport {
  //<editor-fold defaultstate="expanded" desc="Constants">
  public final static String BROWSER = "Mozilla Firefox";
  public final static Double VERSION = 12.0;
  public final static String SHORTNAME = "firefox";
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  public FirefoxSupport() {
    super(BROWSER, VERSION, SHORTNAME);
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Browser Support Implementations">
  @Override
  public Double parseVersion() {
    Double result = null;
    try {
      // Mozilla/5.0 (Macintosh; Intel Mac OS X 10.7; rv:13.0) Gecko/20100101 Firefox/13.0
      result = Double.parseDouble(WebUtil.getUserAgent().replaceAll("^.*?Firefox/", "").replaceAll("\\.\\d+", ""));
    }
    catch(Exception ex) {
      ExceptionUtil.throwIllegalStateException(ex);
    }
    return result;
  }

  @Override
  public boolean isBrowser() {
    String ua = WebUtil.getUserAgent();
    boolean isFirefox = (ua != null && ua.contains("Firefox/"));
    return isFirefox;
  }
  //</editor-fold>
}
