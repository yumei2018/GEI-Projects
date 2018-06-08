package entity.core.support;

import entity.core.util.ExceptionUtil;
import entity.core.util.WebUtil;

/**
 *
 * @author clay
 */
public class ChromeSupport extends BrowserSupport {
  //<editor-fold defaultstate="expanded" desc="Constants">
  public final static String BROWSER = "Google Chrome";
  public final static Double VERSION = 18.0;
  public final static String SHORTNAME = "chrome";
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructor">
  public ChromeSupport() {
    super(BROWSER, VERSION, SHORTNAME);
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Browser Support Implementations">
  @Override
  public Double parseVersion() {
    Double result = null;
    try {
      // Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.52 Safari/536.5
      result = Double.parseDouble(WebUtil.getUserAgent().replaceAll("^.*?Chrome/(\\d+)\\..*$", "$1"));
    }
    catch(Exception ex) {
      ExceptionUtil.throwIllegalStateException(ex);
    }
    return result;
  }

  @Override
  public boolean isBrowser() {
    String ua = WebUtil.getUserAgent();
    boolean isChrome = (ua != null && ua.contains("Chrome/"));
    return isChrome;
  }
  //</editor-fold>
}
