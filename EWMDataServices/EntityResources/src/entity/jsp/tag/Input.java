package entity.jsp.tag;

import entity.jsp.tag.base.Attribute;
import entity.jsp.tag.base.BaseTag;

/**
 *
 * @author soi
 */
public class Input extends BaseTag {
  
  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  private Attribute mType;
  
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mType = null;
  }
  //</editor-fold>
  
  /**
   * 
   */
  public Input(){
    this.setType("text");
  }
  
  /**
   * 
   * @return String
   */
  protected String buildTag(){
    String tpl = "<input %1$s />";
    return String.format(tpl,this.buildAttributes());
  }

  @Override
  protected String buildAttributes() {
    String result = super.buildAttributes();
    result += this.getTypeAttribute();
    result += this.getValue();
    return result;
  }
  
  /**
   * 
   * @param type 
   */
  public void setType(String type) {
    if (this.mType == null) {
      this.mType = new Attribute("type");
    }
    this.mType.setValue(type);
  }
  
  /**
   * 
   * @return Attribute
   */
  public Attribute getTypeAttribute(){
    return this.mType;
  }
}
