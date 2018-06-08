package entity.core.properties;

import entity.core.util.ExceptionUtil;
import entity.core.util.WebUtil;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.springframework.util.StringUtils;

/**
 *
 * @author clay
 */
public class AppProperties extends Properties {
  //<editor-fold defaultstate="expanded" desc="Private Properties">
  private final static String APP_CONF_PATH = "WEB-INF/conf/";
  private final static String DEFAULT_APP_FILE = "app.properties";
  private final String mAppFile;
  private String mAppName;
  private String mAppAdminName;
  private List<String> mSysAdminEmails;
  private List<String> mAppAdminEmails;
  private List<String> mAppHosts;

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mAppName = null;
    this.mSysAdminEmails = null;
    this.mAppAdminEmails = null;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public AppProperties() {
    this(DEFAULT_APP_FILE);
  }
  public AppProperties(String appfile) {
    this.mAppFile = appfile;
    this.init();
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Initialize the properties">
  private final AppProperties init() {
    try {
      String filename = WebUtil.getRequest().getServletContext().getRealPath(APP_CONF_PATH + this.getAppFile());
      File file = new File(filename);
      if (!file.exists()) {
        throw new Exception("The " + this.getAppFile() + " file is missing!");
      }
      try (FileInputStream fis = new FileInputStream(file)) {
        this.load(fis);
      }
      this.initAppName().initSysAdminEmails().initAppAdminEmails().initAppHosts().initAppAdminName();
    } 
    catch (Exception e) {
      ExceptionUtil.throwIllegalStateException(e);
    }
    
    return this;
  }
  
  private final AppProperties initAppName(){
    this.mAppName = this.getProperty("AppName");
    return this;
  }
  
  private final AppProperties initSysAdminEmails(){
    try {
      String emails = null;
      if (!StringUtils.isEmpty(emails = this.getProperty("SysAdminEmail"))){
        this.mSysAdminEmails = Arrays.asList(emails.split(";"));
      }
    }
    catch(Exception ex){
      ExceptionUtil.throwIllegalStateException(ex);
    }
    return this;
  }
  
  private final AppProperties initAppAdminEmails(){
    try {
      String emails = null;
      if (!StringUtils.isEmpty(emails = this.getProperty("AppAdminEmail"))){
        this.mAppAdminEmails = Arrays.asList(emails.split(";"));
      }
    }
    catch(Exception ex){
      ExceptionUtil.throwIllegalStateException(ex);
    }
    return this;
  }
  
  private final AppProperties initAppHosts(){
    try {
      String appHosts = null;
      if (!StringUtils.isEmpty(appHosts = this.getProperty("AppHosts"))){
        this.mAppHosts = Arrays.asList(appHosts.split(";"));
      }
    }
    catch(Exception ex){
      ExceptionUtil.throwIllegalStateException(ex);
    }
    return this;
  }
  
  private final AppProperties initAppAdminName(){
    this.mAppAdminName = this.getProperty("AppAdminName");
    return this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get App File ">
  public final String getAppFile () {
    return this.mAppFile;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get App Admin Name">
  public final String getAppAdminName() {
    return this.mAppAdminName;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get App Name">
  public final String getAppName() {
    return this.mAppName;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Sys Admin Emails">
  public final List<String> getSysAdminEmails() {
    return this.mSysAdminEmails;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get App Admin Emails">
  public final List<String> getAppAdminEmails() {
    return this.mAppAdminEmails;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get App Production Hosts">
  public final List<String> getAppHosts() {
    return this.mAppHosts;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Refresh">
  /**
   * Reinitiate the app.properties file
   * 
   * @return this
   */
  public final AppProperties refresh() {
    return this.init();
  }
  //</editor-fold>
}
