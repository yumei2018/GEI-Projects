package entity.jsp.tag.base;

import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

/**
 *
 * @author clay
 */
public class SelectOptionAttribute {
  //<editor-fold defaultstate="expanded" desc="Private Properties">
  private String mAttr;
  private Object mValue;
  
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mAttr = null;
    this.mValue = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public SelectOptionAttribute(String attr, Object o) {
    this.mAttr = attr;
    this.mValue = o;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Get Attribute Name">
  /**
   *
   * @return String the attribute name
   */
  public final String getName() {
    return this.mAttr;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Get Value - get the attribute value">
  public final <O extends Object> O getValue(){
    return (O) this.mValue;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Is Valid - isValid()">
  public final boolean isValid(){
    boolean result = false;

    try {
      if ((this.getName() == null) || StringUtils.isEmpty(this.getName())) {
        throw new NullPointerException("The attribute name cannot be unassigned!");
      }
      if (!this.getName().matches("[a-zA-Z0-9\\-]+")) {
        throw new IllegalStateException("The attribute name is invalid. Eg: [a-zA-Z0-9\\-]+");
      }
      if (this.getValue() == null) {
        throw new NullPointerException("The attribute value cannot be unassigned!");
      }
      result = true;
    }
    catch(Exception ex) {
      result = false;
    }


    return result;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="To String Override - toString()">
  @Override
  public final String toString(){
    String result = null;

    if (this.isValid()) {
      result = String.format("%1$s=\"%2$s\""
        ,this.getName().trim()
        ,HtmlUtils.htmlEscape(this.getValue().toString())
      );
    }

    return result;
  }
  //</editor-fold>
  
// TESTS
//  public static void main(String[] args) {
//    SelectOptionAttribute attr = new SelectOptionAttribute("a", new Integer(0));
//    System.out.println(attr.toString());
//
//    JSONObject json = new JSONObject();
//    json.put("test", "again");
//    attr = new SelectOptionAttribute("a", json);
//    System.out.println(attr.toString());
//
//
//    attr = new SelectOptionAttribute("a-as'few-a", new java.util.Date());
//    System.out.println(attr.toString());
//  }
}