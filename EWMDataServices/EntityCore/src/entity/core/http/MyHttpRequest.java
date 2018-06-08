package entity.core.http;

import entity.core.io.StringOutputStream;
import entity.core.util.WebUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author soi
 */
public class MyHttpRequest implements Serializable {

  //<editor-fold defaultstate="collapsed" desc="User Agency Enum">
  public enum UserAgent {
    IE("Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko", "11")
    ,Firefox("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0", "49.0b10")
    ,Chrome("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, " 
      + "like Gecko) Chrome/56.0.2924.87 Safari/537.36","56.0.2924.87 (64-bit)");
    
    private String mUserAgentString;
    private String mVersion;
    
    private UserAgent(String uas, String ver) {
      this.mUserAgentString = uas;
      this.mVersion = ver;
    }
    
    @Override
    public String toString() {
      return this.mUserAgentString;
    }
    
    public String getVersion(){
      return this.mVersion;
    }
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  private Map<String,Object> mParams;
  private String mUrl;
  private UserAgent mUserAgent;
  private HttpResponse mResponse;
  private String mResponseString;
  private RequestMethod mMethod;
  private Map<String,String> mCookies;
  private String mCookieDomain;
  private String mCookiePath;
  private Boolean mUrlChanged;
  private final String JSESSIONID_COOKIE = "JSESSIONID";

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mParams = null;
    this.mUrl = null;
    this.mResponse = null;
    this.mUserAgent = null;
    this.mCookieDomain = null;
    this.mCookiePath = null;
    this.mCookies = null;
    this.mUrlChanged = null;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public MyHttpRequest(String url) {
    this(url,null);
  }
  
  public MyHttpRequest(String url, Map params) {
    this.setUrl(url);
    this.setParams(params);
    this.setMethod(RequestMethod.GET);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Properties Setters/Getters">
  /**
   * @return the mParams
   */
  public Map<String,Object> getParams() {
    if (this.mParams == null) {
      this.mParams = new HashMap<>();
    }
    
    return this.mParams;
  }
  
  /**
   * @param mParams the mParams to set
   */
  public MyHttpRequest setParams(Map mParams) {
    this.mParams = mParams;
    return this;
  }
  
  /**
   * @return the mUrl
   */
  public String getUrl() {
    return this.mUrl;
  }
  
  public MyHttpRequest setUrl(String mUrl) {
    this.mUrlChanged = !Objects.equals(this.mUrl, mUrl);
    this.mUrl = mUrl;
    return this;
  }
  
  public MyHttpRequest setUserAgent(UserAgent ua) {
    this.mUserAgent = ua;
    return this;
  }
  
  public UserAgent getUserAgent() {
    if (this.mUserAgent == null) {
      this.mUserAgent = UserAgent.Firefox;
    }
    
    return this.mUserAgent;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Set Param">
  public MyHttpRequest setParam(String key, Object value) {
    this.getParams().put(key, value);
    return this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Param">
  public <T extends Object> T getParam(String key) {
    return (T) this.getParams().get(key);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Build Params">
  private List<NameValuePair> buildParams(){
    List<NameValuePair> result = null;
    try {
      result = new ArrayList<>();
      for (Entry<String,Object> entry : this.getParams().entrySet()) {
        result.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
      }
    }
    catch(Exception ex) {
      throw new IllegalStateException(
        String.format(""
          ,this.getClass().getName()
          ,"buildParams()"
          ,ex.getClass().getName()
          ,ex.getMessage()
        )
      );
    }
    
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Send">
  public MyHttpRequest send() {
    try {
      List<NameValuePair> urlParameters = null;
      HttpRequestBase request = null;
      UrlEncodedFormEntity encodedUrlParams = null;
      HttpClient client = null;
      
      if (this.hasCookies()) {
        client = HttpClientBuilder.create().setDefaultCookieStore(this.buildCookieStore()).build();
      }
      else {
        client = HttpClientBuilder.create().build();
      }
      if ((urlParameters = this.buildParams()) != null) {
        encodedUrlParams = new UrlEncodedFormEntity(urlParameters);
      }
      if (this.isPost()) {
        request = new HttpPost(this.getUrl());
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        if (encodedUrlParams != null) {
          ((HttpPost)request).setEntity(encodedUrlParams);
        }
      }
      else if (this.isGet()) {
        String url = this.getUrl();
        if (encodedUrlParams != null) {
          StringOutputStream sos = new StringOutputStream(true);
          encodedUrlParams.writeTo(sos);
          if (url.contains("?")) {
            url += "&" + sos.toString();
          }
          else {
            url += "?" + sos.toString();
          }
          sos.flush();
        }
        request = new HttpGet(url);
      }
      else {
        throw new NullPointerException("The request method cannot be unassigned!");
      }
      request.setHeader("User-Agent", this.getUserAgent().toString());

      this.mResponse = client.execute(request);
      
      //<editor-fold defaultstate="collapsed" desc="Set last session id">
      Header[] cookies = null;
      for (Header h : this.mResponse.getAllHeaders()) {
        for (HeaderElement  he : h.getElements()) {
          if (he.getName().equals(JSESSIONID_COOKIE)) {
            this.setSessionId(he.getValue());
            break;
          }
        }
      }
      //</editor-fold>

      try (BufferedReader rd 
        = new BufferedReader(new InputStreamReader(this.mResponse.getEntity().getContent()))) {
        StringBuffer result = new StringBuffer();
        String line = null;
        while ((line = rd.readLine()) != null) {
          result.append(line);
        }
        this.mResponseString = result.toString();
      }
    }
    catch(Exception ex) {
      throw new IllegalStateException(
        String.format("%s.%s %s:\n%s"
          ,this.getClass().getName()
          ,"send()"
          ,ex.getClass().getName()
          ,ex.getMessage()
        )
      );
    }
    
    return this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Is Post">
  public Boolean isPost() {
    return Objects.equals(this.mMethod, RequestMethod.POST);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Is Get">
  public Boolean isGet() {
    return Objects.equals(this.mMethod, RequestMethod.GET);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Response String">
  public String getResponse() {
    return this.mResponseString;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Response">
  public HttpResponse getHttpResponse(){
    return this.mResponse;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Response Code">
  public Integer getResponseCode(){
    Integer result= null;
    if (this.getHttpResponse() != null) {
      result= this.getHttpResponse().getStatusLine().getStatusCode();
    }
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Json Response">
  public JSONObject getJsonResponse() {
    JSONObject result = null;
    
    try {
      result = new JSONObject(this.getResponse());
    }
    catch(Exception ex) {
      throw new IllegalStateException(
        String.format(""
          ,this.getClass().getName()
          ,"getJsonResponse()"
          ,ex.getClass().getName()
          ,ex.getMessage()
        )
      );
    }
    
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Set Method">
  public MyHttpRequest setMethod(RequestMethod method) {
    this.mMethod = method;
    
    return this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Set Cookie">
  public MyHttpRequest setCookie(String key, String val) {
    this.getCookies().put(key, val);
    
    return this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Cookie">
  public String getCookie(String key) {
    return this.getCookies().get(key);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Cookies">
  public Map<String,String> getCookies() {
    if (this.mCookies == null) {
      this.mCookies = new HashMap<>();
    }
    return this.mCookies;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Has Cookie">
  public Boolean hasCookies() {
    return this.mCookies != null && !this.mCookies.isEmpty();
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Get Cookie Store">
  public CookieStore buildCookieStore() {
    CookieStore result = null;
    
    if (this.getCookies() != null) {
      String domain = null;
      if (StringUtils.isEmpty(domain = this.getCookieDomain())) {
        throw new NullPointerException("The request url cannot be unassigned!");
      }
      
      result = new BasicCookieStore();
      BasicClientCookie cookie = null;
      if (domain.contains(".")) {
        domain = domain.substring(domain.indexOf("."));// only need the base domain eg: .example.com
      }
      
      for (Entry<String,String> entry : this.getCookies().entrySet()) {
        cookie = new BasicClientCookie(entry.getKey(), entry.getValue());
        cookie.setDomain(domain);
        cookie.setPath(this.getCookiePath());
        result.addCookie(cookie);
      }
    }
  
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Cookie Domain">
  public String getCookieDomain() {
    if ((this.mCookieDomain == null) || (this.urlChanged())) {
      this.mCookieDomain = WebUtil.parseDomain(this.getUrl());
    }
    
    return this.mCookieDomain;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Url Changed">
  public Boolean urlChanged() {
    return Objects.equals(this.mUrlChanged, true);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Cookie Path">
  public String getCookiePath() {
    String result = null;
  
    if (StringUtils.isEmpty(this.mCookiePath)){
      result = "/";
    }
    else {
      result = this.mCookiePath;
    }
    
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Set Session ID">
  public MyHttpRequest setSessionId(String id) {
    this.setCookie(JSESSIONID_COOKIE, id);
    return this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Send Post Request">
  public MyHttpRequest post() {
    return this.setMethod(RequestMethod.POST).send();
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Send Get request">
  public MyHttpRequest get() {
    return this.setMethod(RequestMethod.GET).send();
  }
  //</editor-fold>
}
