package entity.core.support;

import entity.core.util.ExceptionUtil;
import entity.core.util.WebUtil;

/**
 *
 * @author clay
 */
public class InternetExplorerSupport extends BrowserSupport {
  //<editor-fold defaultstate="expanded" desc="Constants">
  public final static String BROWSER = "IE";
  public final static Double VERSION = 10.0;
  public final static String SHORTNAME = "ie";
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  public InternetExplorerSupport() {
    super(BROWSER, VERSION, SHORTNAME);
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Browser Support Implementations">
  @Override
  public Double parseVersion() {
    Double result = null;
    try {
      String ua = WebUtil.getUserAgent();
      if (ua.contains("Trident/7.0")) {
        // Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko
        result = 11.0; //Double.parseDouble(ua.replaceAll("^.*?Trident.+rv\\:(\\d+(\\.\\d+){0,1}).*$", "$1")) >= IE_MINIMUM;
      }
      else {
        // Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C; .NET4.0E)
        result = Double.parseDouble(ua.replaceAll("^.*?MSIE\\s+(\\d+).*$", "$1"));
      }
    }
    catch(Exception ex) {
      ExceptionUtil.throwIllegalStateException(ex);
    }
    return result;
  }

  @Override
  public boolean isBrowser() {
    String ua = WebUtil.getUserAgent();

    boolean isMSIE = (ua != null && ua.contains("MSIE"));
    boolean isMSIE10 = (ua != null && ua.contains("MSIE 10"));
    boolean isMSIE9 = (ua != null && ua.contains("MSIE 9"));
    boolean isMSIE8 = (ua != null && ua.contains("MSIE 8"));
    boolean isMSIE11 = (ua != null && ua.contains("Trident"));

    return isMSIE || isMSIE10 || isMSIE9 || isMSIE8 || isMSIE11;
  }
  //</editor-fold>
}
