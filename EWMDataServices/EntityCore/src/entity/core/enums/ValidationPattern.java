package entity.core.enums;

import java.util.regex.Pattern;

/**
 *
 * @author soi
 */
public enum ValidationPattern {
  //<editor-fold defaultstate="collapsed" desc="Enum List">
  EMAIL("");
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  private Pattern p;
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Constructor">
  private ValidationPattern(String pattern) {
    this.p = Pattern.compile(pattern);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Test value against the pattern">
  public boolean test(String value) {
    return this.p.matcher(value).matches();
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Getters">
  public String getPatternString(){
    return this.p.pattern();
  }
  
  public Pattern getPattern(){
    return this.p;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="toString Override">
  @Override
  public String toString() {
    return this.getPatternString();
  }
  //</editor-fold>
}
