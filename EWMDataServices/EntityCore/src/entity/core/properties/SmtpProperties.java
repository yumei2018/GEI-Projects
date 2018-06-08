package entity.core.properties;

import entity.core.util.ExceptionUtil;
import entity.core.util.WebUtil;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 *
 * @author clay
 */
public class SmtpProperties extends Properties {
  //<editor-fold defaultstate="collapsed" desc="Constants">
  private final static String APP_CONF_PATH = "WEB-INF/conf/";
  private final static String DEFAULT_APP_FILE = "smtp.properties";
  //</editor-fold>

  //<editor-fold defaultstate="expanded" desc="Private Properties">
  private String mHost;
  private Integer mPort;
  private Boolean mDebug;
  private String mUser;
  private String mPasswd;
  private List<String> mCc;
  private List<String> mBcc;
  private String mFrom;
  private String mFromAlias;
  private final String mAppFile;
  private Boolean mSecure;
  
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mHost = null;
    this.mPort = null;
    this.mDebug = null;
    this.mUser = null;
    this.mPasswd = null;
    this.mCc = null;
    this.mBcc = null;
    this.mFrom = null;
    this.mFromAlias = null;
    this.mSecure = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public SmtpProperties() {
    this(DEFAULT_APP_FILE);
  }
  public SmtpProperties(String appfile) {
    this.mAppFile = appfile;
    this.init();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Initialize the properties">
  private final SmtpProperties init() {
    try {
      String filename = WebUtil.getRequest().getServletContext().getRealPath(APP_CONF_PATH + this.getAppFile());
      File file = new File(filename);
      if (!file.exists()) {
        throw new Exception("The " + this.getAppFile() + " file is missing!");
      }
      try (FileInputStream fis = new FileInputStream(file)) {
        this.load(fis);
      }
      this.initProperties();
    } 
    catch (Exception e) {
      ExceptionUtil.throwIllegalStateException(e);
    }
    
    return this;
  }
  
  private final SmtpProperties initProperties() {

    String key = "smtp.host";
    Object value = this.getProperty(key);
    this.mHost = (String) value;

    key = "smtp.port";
    value = this.getProperty(key);
    if (value != null) {
      this.mPort = Integer.parseInt(value.toString());
    }

    key = "smtp.debug";
    value = this.getProperty(key);
    if (value != null) {
      this.mDebug = Boolean.parseBoolean(value.toString());
    }

    key = "smtp.user";
    value = this.getProperty(key);
    if (value != null){
      this.mUser = value.toString();
    }

    key = "smtp.password";
    value = this.getProperty(key);
    if (value != null){
      this.mPasswd = value.toString();
    }

    key = "smtp.cc";
    value = this.getProperty(key);
    if (value != null) {
      this.mCc = Arrays.asList(value.toString().split(";"));
    }

    key = "smtp.bcc";
    value = this.getProperty(key);
    if (value != null) {
      this.mBcc = Arrays.asList(value.toString().split(";"));
    }

    key = "smtp.from";
    value = this.getProperty(key);
    this.mFrom = value.toString();

    key = "smtp.alias";
    value = this.getProperty(key);
    this.mFromAlias = value.toString();
    
    key = "smtp.secure";
    value = this.getProperty(key);
    if (value != null){
      this.mSecure = Boolean.parseBoolean(value.toString());
      
    }

    return this;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Getters">
  /**
   * @return the mHost
   */
  public String getHost() {
    return this.mHost;
  }

  /**
   * @return the mPort
   */
  public Integer getPort() {
    return this.mPort;
  }

  /**
   * @return the mDebug
   */
  public Boolean getDebug() {
    return this.mDebug;
  }

  /**
   * @return the mUser
   */
  public String getUser() {
    return this.mUser;
  }

  /**
   * @return the mPasswd
   */
  public String getPasswd() {
    return this.mPasswd;
  }

  /**
   * @return the mCc
   */
  public List<String> getCc() {
    return this.mCc;
  }

  /**
   * @return the mBcc
   */
  public List<String> getBcc() {
    return this.mBcc;
  }

  /**
   * @return the mFrom
   */
  public String getFrom() {
    return this.mFrom;
  }

  /**
   * @return the mFromAlias
   */
  public String getFromAlias() {
    return this.mFromAlias;
  }
  //</editor-fold>
    
  //<editor-fold defaultstate="collapsed" desc="Get App File ">
  public final String getAppFile () {
    return this.mAppFile;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Refresh">
  public SmtpProperties refresh() {
    return this.init();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Is Secure">
  public final boolean isSecure() {
    return Objects.equals(this.mSecure, true);
  }
  //</editor-fold>
}