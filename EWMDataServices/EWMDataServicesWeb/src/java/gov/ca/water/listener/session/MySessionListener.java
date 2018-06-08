package gov.ca.water.listener.session;

import entity.core.jdbc.ConnectionContext;
import entity.core.util.DateUtil;
import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author clay
 */
public class MySessionListener implements HttpSessionListener {

  public final String RESOURCE_DATA_PATH = "/resources/data";
  public final String WATER_BUDGET_PATH = RESOURCE_DATA_PATH + "/" + "temp";
  
  //<editor-fold defaultstate="collapsed" desc="Session Created">
  @Override
  public void sessionCreated(HttpSessionEvent hse) {
//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Session Destroyed">
  @Override
  public void sessionDestroyed(HttpSessionEvent hse) {
    HttpSession session = hse.getSession();
    if (session != null){
      ServletContext context = session.getServletContext();
      String absPath = context.getRealPath("/WEB-INF" + WATER_BUDGET_PATH);
      
      cleanOldFiles(absPath);
    }
    ConnectionContext.getInstance().closeSessionConnections();
  }
  //</editor-fold>

  /**
   * Deletes files in single level subdirectories of given path
   * Deletes empty single level subdirectories
   * @param path 
   */
  //<editor-fold defaultstate="collapsed" desc="cleanOldFiles">
  private void cleanOldFiles(String path) {
    long MAX_AGE = DateUtil.getDaysAgo(3).getTime();
    File mainDir = new File(path);
    if (mainDir.exists() && mainDir.isDirectory()){
      File[] subdirs = mainDir.listFiles();
      for (File subdir : subdirs){
        if (subdir.isDirectory() && !subdir.getName().equals("..")){
          File[] files = subdir.listFiles();
          for (File file : files){
            if (file.isFile() && file.lastModified() < MAX_AGE){
              file.delete();
            }
          }
          files = subdir.listFiles();
          if (files.length == 0 ){
            subdir.delete();
          }
        }
      }
    }
  }
  //</editor-fold>

}