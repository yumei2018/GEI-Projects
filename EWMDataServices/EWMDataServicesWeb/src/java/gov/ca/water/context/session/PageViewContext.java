package gov.ca.water.context.session;

import entity.core.util.WebUtil;
//import gov.ca.water.entity.PageViewLog;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author clay
 */
public class PageViewContext {
  
//  //<editor-fold defaultstate="collapsed" desc="Private Properties">
//  private String mLastUrl;
//  private String mLastReferer;
//
//  @Override
//  protected void finalize() throws Throwable {
//    super.finalize(); //To change body of generated methods, choose Tools | Templates.
//    this.mLastUrl = null;
//  }
//  //</editor-fold>
//  
//  //<editor-fold defaultstate="collapsed" desc="Set Last Url">
//  public PageViewContext setLastUrl(String url) {
//    this.mLastUrl = url;
//    
//    return this;
//  }
//  //</editor-fold>
//  
//  //<editor-fold defaultstate="collapsed" desc="Get Last Url">
//  public String getLastUrl() {
//    return this.mLastUrl;
//  }
//  //</editor-fold>
//  
//  //<editor-fold defaultstate="collapsed" desc="Set Last Referer">
//  public PageViewContext setLastReferer(String url) {
//    this.mLastReferer = url;
//    
//    return this;
//  }
//  //</editor-fold>
//  
//  //<editor-fold defaultstate="collapsed" desc="Get Last Referer">
//  public String getLastReferer() {
//    return this.mLastReferer;
//  }
//  //</editor-fold>
//  
//  //<editor-fold defaultstate="collapsed" desc="Static Get Instance">
//  public static PageViewContext getInstance(){
//    return WebUtil.getSession(PageViewContext.class);
//  }
//  //</editor-fold>
//  
//  //<editor-fold defaultstate="collapsed" desc="Log Page View">
//  public static PageViewLog logPageView() {
//    PageViewLog log = null;
//    try {
//      HttpServletRequest request = null;
//      if ((request = WebUtil.getRequest()) == null) {
//        throw new NullPointerException("The system cannot retrieve the http servlet request.");
//      }
//      if (Objects.equals(request.getServerName(),"localhost")) {
//        return null;
//      }
//      log = new PageViewLog();
//      log.initProperties(request).validate();
//      
//      if (Objects.equals(PageViewContext.getInstance().getLastUrl(), log.getUrl())
//        && Objects.equals(PageViewContext.getInstance().getLastReferer(), log.getReferer())) {
//        // checking if it's just a refresh page view
//        log = null;
//        return log;
//      }
//      
//      log.insert();
//      PageViewContext.getInstance().setLastUrl(log.getUrl());
//    }
//    catch(Exception ex) {
//      Logger.getLogger(PageViewContext.class.getName()).log(Level.WARNING, ex.getMessage());
//      ex.printStackTrace();
//    }
//    
//    return log;
//  }
//  //</editor-fold>
}
