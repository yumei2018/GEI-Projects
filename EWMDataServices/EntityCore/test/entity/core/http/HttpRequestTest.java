package entity.core.http;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import entity.core.io.StringOutputStream;
import entity.core.util.ReflectionUtil;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author soi
 */
public class HttpRequestTest {
  
  @Test
  public void t1() throws Exception {
    List<NameValuePair> urlParameters = new ArrayList<>();
    String[] values= {"a","b","c"};
    for (String key : values) {
      urlParameters.add(new BasicNameValuePair(key, key));
    }
    
    UrlEncodedFormEntity params = new UrlEncodedFormEntity(urlParameters);
    StringOutputStream sos = new StringOutputStream(true);
    params.writeTo(sos);
    System.out.println(sos.toString());
    sos.flush();
  }
  
  @Test
  public void t2() {
    MyHttpRequest req = new MyHttpRequest("http://www.google.com");
    req.send();
    System.out.println(req.getResponse());
  }
  
  @Test
  public void t3(){
    MyHttpRequest req = new MyHttpRequest("http://localhost:8080/SgmaApplicationWeb/service/gsa/review/getgsareviews");
    req.setSessionId("039C94B4A229DA25AE16F5D047153F69");
    req.post();
    System.out.println(req.getJsonResponse().toString(2));
  }
  
  @Test
  public void t4() throws Exception {
    String urlStr = "http://localhost:8080;JESSIONID=31432149032840";
    URL url = new URL(urlStr);
    
    String[] methods = {"getHost", "getAuthority", "getFile", "getPath", "getPort", "getProtocol", "getQuery", "getRef", "getUserInfo"};
    for (String m : methods) {
      System.out.println("url." + m + "() => " + ReflectionUtil.invoke(url, m));
    }
  }
  
  @Test
  public void t5(){
    MyHttpRequest req = new MyHttpRequest("http://localhost:8080/SgmaApplicationWeb/service/gsa/getgsacontacts");
    req.setSessionId("039C94B4A229DA25AE16F5D047153F69");
    req.setParam("gsaAppId", 15);
    req.get();
    System.out.println(req.getJsonResponse().toString(2));
  }
  
  @Test
  public void t7(){
    MyHttpRequest req = new MyHttpRequest("http://localhost:8080/SgmaApplicationWeb/service/gsadocument/exclusivegsa");
    req.get();
    System.out.println(req.getResponse());
  }
  
  public static class MyRunnable implements Runnable {
    Logger mLogger = null;
    String mName = null;
    Thread mThread = null;
    
    public MyRunnable(Logger l,String name) {
      this.mLogger = l;
      this.mName = name;
    }
    
    @Override
    public void run() {
      this.mLogger.log(Level.INFO, "Starting... " + this.mName);
      try {
        MyHttpRequest req = new MyHttpRequest("http://localhost:8080/SgmaApplicationWeb/service/gsadocument/exclusivegsa");
        req.get();
        this.mLogger.log(Level.INFO, this.mName + " response code: " + req.getResponseCode());
      }
      catch(Exception ex) {
        this.mLogger.log(Level.INFO, ex.getMessage());
      }
      this.mLogger.log(Level.INFO, "Thread " + this.mName + " exiting...");
    }
    
    public void start(){
      if (this.mThread == null) {
        this.mThread = new Thread(this, this.mName);
        this.mThread.start();
      }
    }
  }
  
  public static void main(String[] args){
    Logger logger = Logger.getLogger(HttpRequestTest.class.getName());
    logger.log(Level.INFO, "Starting test...");
    for (int i=0;i < 5;++i) {
      new MyRunnable(logger
        ,"Exclusive GSA " + i
      ).start();
    }
    logger.log(Level.INFO, "...done!");
  }
}
