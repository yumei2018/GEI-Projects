package gov.ca.water.context.session;

import static entity.core.filter.ConditionFilter.ConditionOperator.SQL_EQUAL;
import entity.core.util.DateUtil;
import entity.core.util.WebUtil;
import javax.servlet.http.HttpSession;
import org.springframework.util.StringUtils;

/**
 *
 * @author soi
 */
public class UserContext {
  //<editor-fold defaultstate="expanded" desc="Static Properties">
  private static final Integer MAX_ATTEMPT = 5;
  public static final String SALT = "G3!";
  //</editor-fold>
  
  // <editor-fold defaultstate="expanded" desc="Private Properties">
 
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   *
   */
  public UserContext(){}
  // </editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Static Get Instance">
  public static UserContext getInstance(){
    return WebUtil.getContext(UserContext.class);
  }
//</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Is Logged In">
  public boolean isLoggedIn() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Authenticating">
  public final boolean authenticating(String username, String password) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Authenticate">
  public static final boolean authenticate(String username, String password) {
    UserContext.getInstance().authenticating(username,password);
    return true;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="authenticateHash">
  public static final boolean authenticateHash(String usernameHash) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="logout">
  public static UserContext logout(){
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  //</editor-fold>
  
  
  //<editor-fold defaultstate="collapsed" desc="Static Logged In Check">
  public final static boolean loggedInCheck() {
    if (!UserContext.getInstance().isLoggedIn()) {
      throw new IllegalStateException("Please sign in first!");
    }
  
    return true;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Static Validate Password Requirements">
  public static boolean validatePasswordRequirements(String password) {
    return (password != null) && (password.length() >= 8)
            && (password.matches(".*[\\d].*")) && (password.matches(".*[a-z].*"))
            && (password.matches(".*[A-Z].*")) && (password.matches(".*[^\\w].*"));
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Static Generate Hash">
  public static String generateHash(){
    String result = null;
    
    result = DateUtil.format(new java.util.Date(), "yyyy'G'MM'E'dd'I'kk:mm:ss");
    
    return WebUtil.md5(result);
  }
  //</editor-fold>

}
