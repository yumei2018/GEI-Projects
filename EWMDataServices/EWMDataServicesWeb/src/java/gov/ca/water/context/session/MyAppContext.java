package gov.ca.water.context.session;

import entity.core.util.WebUtil;
import entity.core.properties.SmtpProperties;
import entity.core.properties.AppProperties;
import entity.core.support.BrowserSupport;
import entity.core.support.ChromeSupport;
import entity.core.support.FirefoxSupport;
import entity.core.support.InternetExplorerSupport;
import entity.core.support.SafariSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.util.StringUtils;

public class MyAppContext {
  
  //<editor-fold defaultstate="expanded" desc="Static Properties">
  public static final String BBMRS_CONTEXT_PROP = "BBMRSContext";
  //</editor-fold>
  
  //<editor-fold defaultstate="expanded" desc="Private Properties">
  private final Logger LOGGER = Logger.getLogger(MyAppContext.class.getName());
  private AppProperties mProperties;
  private SmtpProperties mSmtpConfig;
  private List<BrowserSupport> mBrowserSupports;
  
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mProperties = null;
    this.mSmtpConfig = null;
    this.mBrowserSupports = null;
  }
  //</editor-fold>   
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public MyAppContext() {
    super();
    this.initBrowserSupports();
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Init Browser Supports">
  private final MyAppContext initBrowserSupports() {
    if (this.mBrowserSupports == null) {
      this.mBrowserSupports = new ArrayList<>();
      this.mBrowserSupports.add(new FirefoxSupport());
      this.mBrowserSupports.add(new InternetExplorerSupport());
      this.mBrowserSupports.add(new ChromeSupport());
      this.mBrowserSupports.add(new SafariSupport());
    }
  
    return this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Static Get Instance">
  public static MyAppContext getInstance(){
    return WebUtil.getContext(MyAppContext.class);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get App Properties">
  public AppProperties getAppProperties(){
    if (this.mProperties == null) {
      this.mProperties = new AppProperties();
    }
    return this.mProperties;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="App Property Getters">
  public SmtpProperties getSmtpConfig(){
    if (this.mSmtpConfig == null) {
      this.mSmtpConfig = new SmtpProperties();
    }
    
    return this.mSmtpConfig;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="In Production Check">
  public Boolean inProduction(){
    return this.getAppProperties().getAppHosts().contains(WebUtil.getRequest().getServerName());
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Send Email">
  /**
   *
   * @param subject
   * @param body
   * @param tos
   */
  public void sendEmail(String subject, String body, String[] tos){
    this.sendEmail(subject,body,tos,null,null);
  }

  /**
   *
   * @param subject
   * @param body
   * @param tos
   * @param ccs
   * @param bccs
   */
  public void sendEmail(String subject, String body, String[] tos, String[] ccs, String[] bccs){
    try {
      if (StringUtils.isEmpty(subject)
          || StringUtils.isEmpty(body)
          || (tos == null || tos.length == 0)) {
        throw new IllegalArgumentException("The subject, body, and recipents cannot be unassigned!");
      }

      SmtpProperties config = null;
      if ((config = this.getSmtpConfig()) == null){
        throw new Exception("The smtp config cannot be unassigned!");
      }
      
      final HtmlEmail email = new HtmlEmail();
      email.setHostName(config.getHost());
      if (!(StringUtils.isEmpty(config.getUser()) || StringUtils.isEmpty(config.getPasswd()))) {
        email.setAuthentication(config.getUser(), config.getPasswd());
      }
      
      email.setSSLOnConnect(config.isSecure());// can be replaced with email.setSSLOnConnect(config.isSecure());
      if (config.getPort() != null) {
        email.setSmtpPort(config.getPort());
      }
      if (config.getDebug() != null) {
        email.setDebug(config.getDebug());
      }

      if (!StringUtils.isEmpty(config.getFrom())) {
        if (!StringUtils.isEmpty(config.getFromAlias())) {
          email.setFrom(config.getFrom(),config.getFromAlias());
        }
        else {
          email.setFrom(config.getFrom());
        }
      }

      email.setSubject(subject);
      email.setHtmlMsg(body);

      Boolean hasRecipients=false;
      String dummyText = null;
      boolean isProduction;
      try {
        isProduction = this.inProduction();
      } catch (Exception e) {
        isProduction = true;
      }
      if (isProduction) {
        dummyText = "";
      }
      else {
        dummyText = "<geideveloper916@gmail.com>";
      }

      try {
        for (String to : tos) {
          email.addTo(to + dummyText);
          hasRecipients=true;
        }
      }
      catch(Exception ex){
        hasRecipients=false;
        LOGGER
        .log(
          Level.WARNING
          ,String.format("%s.sendEmail(String...) %s:\n%s"
            ,this.getClass().getName()
            ,ex.getClass().getName()
            ,ex.getMessage()
          )
        );
      }

      // App Context CCs
      if ((config.getCc() != null) && (!config.getCc().isEmpty())) {
        try {
          for (String cc : config.getCc()) {
            email.addCc(cc + dummyText);
            hasRecipients=true;
          }
        }
        catch(Exception ex){
          hasRecipients=hasRecipients||false;
          LOGGER
          .log(
            Level.WARNING
            ,String.format("%s.sendEmail(String...) %s:\n%s"
              ,this.getClass().getName()
              ,ex.getClass().getName()
              ,ex.getMessage()
            )
          );
        }
      }

      // Additional CCs
      if ((ccs != null) && (ccs.length > 0)) {
        for (String cc : ccs) {
          try {
            email.addCc(cc + dummyText);
            hasRecipients=true;
          }
          catch(Exception ex){
            hasRecipients=hasRecipients||false;
            
            LOGGER
            .log(
              Level.WARNING
              ,String.format("%s.sendEmail(String...) %s:\n%s"
                ,this.getClass().getName()
                ,ex.getClass().getName()
                ,ex.getMessage()
              )
            );
          }
        }
      }
      
      // App Context BCCs
      if ((config.getBcc() != null) && (!config.getBcc().isEmpty())) {
        try {
          for (String bcc : config.getBcc()) {
            email.addCc(bcc);
            hasRecipients=true;
          }
        }
        catch(Exception ex){
          hasRecipients=hasRecipients||false;
          LOGGER
          .log(
            Level.WARNING
            ,String.format("%s.sendEmail(String...) %s:\n%s"
              ,this.getClass().getName()
              ,ex.getClass().getName()
              ,ex.getMessage()
            )
          );
        }
      }
      
      
      if ((bccs != null) && (bccs.length > 0)) {
        try {
          for (String bcc : bccs){
            email.addBcc(bcc + dummyText);
            hasRecipients=true;
          }
        }
        catch(Exception ex){
          hasRecipients=hasRecipients||false;
          
          LOGGER
          .log(
            Level.WARNING
            ,String.format("%s.sendEmail(String...) %s:\n%s"
              ,this.getClass().getName()
              ,ex.getClass().getName()
              ,ex.getMessage()
            )
          );
        }
      }

      if (hasRecipients) {
        new Thread(new Runnable() {
          @Override
          public void run() {
            try {
              email.send();
            } 
            catch (Exception ex) {
              LOGGER
              .log(
                Level.WARNING
                ,String.format("%s.sendEmail(String...) %s:\n%s"
                  ,MyAppContext.class.getName()
                  ,ex.getClass().getName()
                  ,ex.getMessage()
                )
              );
            }
          }
        }).start();
      }
    }
    catch(Exception ex){
      throw new NullPointerException(ex.getMessage());
    }
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Browser CSS Class">
   /**
   *
   * @return result String - the css class name of the browser
   */
  public String getBrowserClass(){
    String result = null;
    
    for (BrowserSupport bs : this.mBrowserSupports) {
      if (bs.isBrowser() && bs.isSupported()) {
        result = bs.getShortName();
        break;
      }
    }
    
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Supported Browsers">
  public String getSupportedBrowsers(){
    String result = "";
    
    for (BrowserSupport bs : this.mBrowserSupports) {
      result += "<li>" + bs + "</li>";
    }
    
    return result;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Browser Detection Methods">
  /**
   *
   * @return
   */
  public boolean isBrowserSupported(){
    boolean result = false;
    
    for (BrowserSupport bs : this.mBrowserSupports) {
      if ((result = bs.isBrowser() && bs.isSupported())) {
        break;
      }
    }
    
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get IE support">
  public InternetExplorerSupport getIESupport() {
    InternetExplorerSupport result = null;
  
    for (BrowserSupport bs : this.mBrowserSupports) {
      if (bs instanceof InternetExplorerSupport) {
        result = (InternetExplorerSupport) bs;
        break;
      }
    }
    
    return result;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Get Navigation Path">
  public String getNavigationPath() {
    String result = null;

    HttpServletRequest request = null;

    if ((request = WebUtil.getRequest()) != null) {
      result = request.getRequestURI().replace(request.getContextPath(), "");
    }

    return result;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="In Navigation Path">
  public boolean inNavigationPath(String... paths) {
    Boolean result = null;

    if (paths != null) {
      String navPath = this.getNavigationPath();

      for (String p : paths) {
        if ((result = navPath.contains(p))) {
          break;
        }
      }
    }

    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get BBMRS Context">
  public final String getBBMRSContext() {
    String result = null;
    if (this.getAppProperties() != null) {
      result = this.getAppProperties().getProperty(BBMRS_CONTEXT_PROP);
    }
    return result;
  }
  //</editor-fold>
}