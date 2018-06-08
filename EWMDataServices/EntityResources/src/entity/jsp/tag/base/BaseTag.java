package entity.jsp.tag.base;

import java.io.Serializable;
import java.util.*;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

/**
 *
 * @author soi
 */
public abstract class BaseTag extends SimpleTagSupport implements Serializable, DynamicAttributes {
  
  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  //<editor-fold defaultstate="expanded" desc="Input Properties">
  private Attribute mStyle;
  private Attribute mClass;
  private Attribute mName;
  private Attribute mLength;
  private Attribute mDisabled;
  private Attribute mRequired;
  private Attribute mPlaceholder;
  private Attribute mId;
  private Attribute mCtClass;
  private Attribute mCtStyle;
  private Attribute mValue;
  //</editor-fold>
  
  //<editor-fold defaultstate="expanded" desc="Label Properties">
  private String mLabel;
  private Attribute mLabelStyle;
  private Attribute mLabelClass;
  private Boolean mHideLabel;
  private String mLabelAlign;
  private String mLabelSeparator;
  private Map<String,Attribute> mAdditionalAttributes;
  //</editor-fold>
  
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mStyle=null;
    this.mClass=null;
    this.mName=null;
    this.mLength=null;
    this.mDisabled=null;
    this.mRequired=null;
    this.mPlaceholder=null;
    this.mId=null;
    this.mCtClass=null;
    this.mCtStyle=null;
    this.mValue=null;
    this.mLabel=null;
    this.mLabelStyle=null;
    this.mLabelClass=null;
    this.mHideLabel=null;
    this.mLabelAlign=null;
    this.mLabelSeparator=null;
    
    if (this.mAdditionalAttributes != null) {
      this.mAdditionalAttributes.clear();
      this.mAdditionalAttributes = null;
    }
  }
  //</editor-fold>
  

  /**
   * Called by the container to invoke this tag. The implementation of this method is provided by the tag library
   * developer, and handles all tag processing, body iteration, etc.
   */
  @Override
  public void doTag() throws JspException {
    JspWriter out = getJspContext().getOut();
    
    try {
      // TODO: insert code to write html before writing the body content.
      // e.g.:
      //
      // out.println("<strong>" + attribute_1 + "</strong>");
      // out.println("    <blockquote>");
      out.println("<div " + this.buildCtAttributes() + ">");
      
      if ((this.mLabelAlign == null) 
          || (this.mLabelAlign.equalsIgnoreCase("top")) 
          || (this.mLabelAlign.equalsIgnoreCase("left"))) {
        out.println(this.buildLabelTag());
        out.println(this.buildTag());
      }
      else {
        out.println(this.buildTag());
        out.println(this.buildLabelTag());
      }
      
      JspFragment f = getJspBody();
      if (f != null) {
        f.invoke(out);
      }

      // TODO: insert code to write html after writing the body content.
      // e.g.:
      //
      // out.println("    </blockquote>");
      out.println("</div>");
    } catch (Exception ex) {
      throw new JspException("Error in FormBaseTag tag", ex);
    }
  }
  
  //<editor-fold defaultstate="collapsed" desc="Tag Logic">
  /**
   * 
   * @return boolean
   */
  public boolean hasLabel(){
    return !StringUtils.isEmpty(this.getLabel());
  }
  
  /**
   * 
   * @return boolean
   */
  public boolean hideLabel(){
    return (this.mHideLabel != null) && (this.mHideLabel);
  }
  
  /**
   * 
   * @return String
   */
  protected String buildLabelAttributes(){
    String result = "";
    
    result += "for='" + this.getId() + "'";
    result += this.getLabelClass();
    result += this.getLabelStyle();
    
    return result;
  }
  
  /**
   * 
   * @return String
   */
  protected String buildCtAttributes(){
    String result = "";
    
    if (StringUtils.isEmpty(this.getCtClass())) {
      result += "class='fieldCt'";
    }
    else {
      result += "class='fieldCt " + HtmlUtils.htmlEscape(this.mCtClass.getValue()) + "'";
    }
    
    result += this.getCtStyle();
    
    return result;
  }
  
  /**
   * 
   * @return 
   */
  protected String buildAttributes(){
    String result = "autocomplete='off'";
    
    result += this.getName();
    result += " id='" + HtmlUtils.htmlEscape(this.getId()) + "'";
    result += this.getClazz();
    result += this.getStyle();
    result += this.getLength();
    result += this.getDisabled();
    result += this.getRequired();
    result += this.getPlaceholder();
    
    Collection<Attribute> attributes = null;
    if ((this.mAdditionalAttributes != null)
        && ((attributes = this.mAdditionalAttributes.values()) != null)
        && (!attributes.isEmpty())){
      List<String> baseAttributes = Arrays.asList(new String[]{"id","name","style","length","disabled","required","placeholder"});
      
      for (Attribute attr : attributes) {
        if (baseAttributes.contains(attr.getKey())) {
          continue;
        }
        result += attr.toString();
      }
    }
    
    return result;
  }
  
  /**
   * 
   * @return String
   */
  protected String buildLabelTag(){
    String result = "";
    
    if (this.hasLabel()){
      String tpl = "<label %1$s>%2$s%3$s</label>";
      result = String.format(tpl
                            ,this.buildLabelAttributes()
                            ,this.getLabel()
                            ,this.getLabelSeparator());
    }
    
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="GETTERS">
  /**
   * 
   * @return String
   */
  public Attribute getNameAttribute(){
    if ((this.mName == null) || (StringUtils.isEmpty(this.mName.toString()))) {
      throw new NullPointerException("The name attribute cannot be unassigned!");
    }
    return this.mName;
  }
  
  /**
   * 
   * @return 
   */
  public Attribute getClassAttribute(){
    return this.mClass;
  }
  
  /**
   * 
   * @return 
   */
  public Attribute getStyleAttribute(){
    return this.mStyle;
  }

  /**
   * 
   * @return 
   */
  public Attribute getLengthAttribute(){
    return this.mLength;
  }
  
  /**
   * 
   * @return 
   */
  public Attribute getRequiredAttribute(){
    return this.mRequired;
  }
  
  /**
   * 
   * @return String
   */
  public Attribute getDisabledAttribute(){
    return this.mDisabled;
  }
  
  /**
   * 
   * @return 
   */
  public Attribute getLabelStyleAttribute(){
    return this.mLabelStyle;
  }
  
  /**
   * 
   * @return 
   */
  public String getLabelStyle(){
    String result = "";
      
    if ((this.hasLabel())){
      if ((this.mLabelStyle != null) && (!StringUtils.isEmpty(this.mLabelStyle.getValue()))) {
        result += this.mLabelStyle.getValue() + ";";
      }
      
      if (!StringUtils.isEmpty(this.mLabelAlign)) {
        if ((this.mLabelAlign.equalsIgnoreCase("top"))
            || (this.mLabelAlign.equalsIgnoreCase("bottom"))){
          result += "display:block;";
        }
        else {
          result += "display:inline-block;";
        }
      }
      
      if (!StringUtils.isEmpty(result)) {
        result = "style='" + HtmlUtils.htmlEscape(result) + "'";
      }
    }
    
    return result;
  }
  
  /**
   * 
   * @return 
   */
  public String getLabelClass(){
    String result = "";
    if (!this.hasLabel()) {
      return result;
    }
    Attribute attr = this.getLabelClassAttribute();
    
    if (!StringUtils.isEmpty(this.getRequired())) {
      if (attr == null){
        attr = new Attribute("class", "required");
      }
      else {
        String classes = attr.getValue().replace("required", "") + " required";
        attr.setValue(classes);
      }
    }
    
    if (attr != null) {
      result = attr.toString();
    }
    
    return result;
  }
  
  public Attribute getValueAttribute(){
    return this.mValue;
  }
  
  /**
   * 
   * @return 
   */
  public String getValue(){
    String result = "";
    
    if (this.getValueAttribute() != null){
      result += this.getValueAttribute();
    }
    
    return result;
  }
  
  /**
   * 
   * @return 
   */
  public Attribute getCtClassAttribute(){
    return this.mCtClass;
  }
  
  /**
   * 
   * @return 
   */
  public Attribute getCtStyleAttribute(){
    return this.mCtStyle;
  }
  
  /**
   * 
   * @return 
   */
  public Attribute getLabelClassAttribute(){
    return this.mLabelClass;
  }
  
  /**
   * 
   * @return String
   */
  public String getLabel(){
//    return HtmlUtils.htmlEscape(this.mLabel);
    return this.mLabel;
  }
  
  /**
   * 
   * @return String
   */
  public Attribute getPlaceholderAttribute(){
    return this.mPlaceholder;
  }
  
  /**
   * 
   * @return String
   */
  public String getLabelSeparator(){
    String result = "";
    
    if (!StringUtils.isEmpty(this.mLabelSeparator)) {
      result = this.mLabelSeparator;
    }
    
    return result;
  }
  
  /**
   * 
   * @return 
   */
  public Attribute getIdAttribute() {
    return this.mId;
  }
  
  /**
   * 
   * @return String
   */
  public String getId(){
    String result = "";
    
    if ((this.mId == null) || (StringUtils.isEmpty(this.mId.toString()))) {
      result = this.mName.getValue() + "Field";
    }
    else {
      result = this.mId.getValue();
    }
    
    return result;
  }
  
  /**
   * 
   * @return 
   */
  public String getName(){
    String result = "";
    
    if (this.getNameAttribute() != null) {
      result = this.getNameAttribute().toString();
    }
    
    return result;
  }
  
  /**
   * 
   * @return 
   */
  public String getClazz(){
    String result = "";
    
    if (this.getClassAttribute() != null) {
      result = this.getClassAttribute().toString();
    }
    
    return result;
  }
  
  /**
   * 
   * @return String
   */
  public String getStyle(){
    String result = "";
    
    if (this.getStyleAttribute() != null) {
      result = this.getStyleAttribute().toString();
    }
    
    return result;
  }
  
  /**
   * 
   * @return 
   */
  public String getLength() {
    String result = "";
    
    if (this.getLengthAttribute() != null) {
      result = this.getLengthAttribute().toString();
    }
    
    return result;
  }
  
  /**
   * 
   * @return 
   */
  public String getDisabled(){
    String result = "";
    
    if (this.getDisabledAttribute() != null) {
      result = this.getDisabledAttribute().toString();
    }
    
    return result;
  }
  
  /**
   * 
   * @return 
   */
  public String getRequired(){
    String result = "";
    
    if (this.getRequiredAttribute() != null) {
      result = this.getRequiredAttribute().toString();
    }
    
    return result;
  }
  
  /**
   * 
   * @return 
   */
  public String getPlaceholder(){
    String result = "";
    
    if (this.getPlaceholderAttribute() != null) {
      result = this.getPlaceholderAttribute().toString();
    }
    
    return result;
  }
  
  /**
   * 
   * @return 
   */
  public String getCtClass(){
    String result = "";
    
    if (this.getCtClassAttribute() != null) {
      result = this.getCtClassAttribute().toString();
    }
    
    return result;
  }
  
  /**
   * 
   * @return 
   */
  public String getCtStyle(){
    String result = "";
    
    if (this.getCtStyleAttribute() != null) {
      result = this.getCtStyleAttribute().toString();
    }
    
    return result;
  }
  
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="SETTERS">
  /**
   * 
   * @param style 
   */
  public void setStyle(String style) {
    if (this.mStyle == null) {
      this.mStyle = new Attribute("style");
    }
    this.mStyle.setValue(style);
  }

  /**
   * 
   * @param align 
   */
  public void setLabelAlign(String align) {
    this.mLabelAlign = align;
  }
  
  /**
   * 
   * @param clazz 
   */
  public void setClass(String clazz) {
    if (this.mClass == null) {
      this.mClass = new Attribute("class");
    }
    this.mClass.setValue(clazz);
  }

  /**
   * 
   * @param name 
   */
  public void setName(String name) {
    if (this.mName == null) {
      this.mName = new Attribute("name");
    }
    this.mName.setValue(name);
  }

  /**
   * 
   * @param label 
   */
  public void setLabel(String label) {
    this.mLabel = label;
  }
 
  /**
   * 
   * @param style 
   */
  public void setLabelStyle(String style) {
    if (this.mLabelStyle == null) {
      this.mLabelStyle = new Attribute("style");
    }
    this.mLabelStyle.setValue(style);
  }

  /**
   * 
   * @param clazz 
   */
  public void setLabelClass(String clazz) {
    if (this.mLabelClass == null) {
      this.mLabelClass = new Attribute("class");
    }
    this.mLabelClass.setValue(clazz);
  }
  
  /**
   * 
   * @param length 
   */
  public void setLength(String length) {
    try {
      this.setLength(Integer.parseInt(length));
    }catch(Exception ex){}
  }
  
  /**
   * 
   * @param length 
   */
  public void setLength(Integer length) {
    if (length == null) {
      return;
    }
    if (this.mName == null) {
      this.mName = new Attribute("maxlength");
    }
    this.mLength.setValue(length.toString());
  }

  /**
   * 
   * @param disabled 
   */
  public void setDisabled(String disabled) {
    try{
      this.setDisabled(Boolean.parseBoolean(disabled));
    }catch(Exception ex){}
  }
  
  /**
   * 
   * @param disabled 
   */
  public void setDisabled(Boolean disabled) {
    if (disabled == null) {
      return;
    }
    if (this.mDisabled == null) {
      this.mDisabled = new Attribute("disabled",true);
    }
    if (disabled) {
      this.mDisabled.setValue(disabled.toString());
    }
    else {
      this.mDisabled.setValue(null);
    }
  }

  /**
   * 
   * @param required 
   */
  public void setRequired(String required) {
    try {
      this.setRequired(Boolean.parseBoolean(required));
    }catch(Exception ex){}
  }
  
  /**
   * 
   * @param required 
   */
  public void setRequired(Boolean required) {
    if (required == null) {
      return;
    }
    if (this.mRequired == null) {
      this.mRequired = new Attribute("required",true);
    }
    if (required){
      this.mRequired.setValue(required.toString());
    }
    else {
      this.mRequired.setValue(null);
    }
  }
  
  /**
   * 
   * @param hide 
   */
  public void setHideLabel(String hide) {
    try {
      this.setHideLabel(Boolean.parseBoolean(hide));
    } catch (Exception ex) {} 
  }
  
  /**
   * 
   * @param hide 
   */
  public void setHideLabel(Boolean hide) {
    this.mHideLabel = hide;
  }
  
  /**
   * 
   * @param <O>
   * @param val 
   */
  public void setValue(String val) {
    if (this.mValue == null) {
      this.mValue = new Attribute("value");
    }
    this.mValue.setValue(val);
  }
  
  /**
   * 
   * @param clazz 
   */
  public void setCtClass(String clazz) {
    if (this.mCtClass == null) {
      this.mCtClass = new Attribute("class");
    }
    this.mCtClass.setValue(clazz);
  }
  
  /**
   * 
   * @param style 
   */
  public void setCtStyle(String style){
    if (this.mCtStyle == null) {
      this.mCtStyle = new Attribute("style");
    }
    this.mCtStyle.setValue(style);
  }
  
  /**
   * 
   * @param placeholder 
   */
  public void setPlaceholder(String placeholder) {
    if (this.mPlaceholder == null) {
      this.mPlaceholder = new Attribute("placeholder");
    }
    this.mPlaceholder.setValue(placeholder);
  }
  
  /**
   * 
   * @param sep 
   */
  public void setLabelSeparator(String sep) {
    this.mLabelSeparator = sep;
  }
  
  /**
   * 
   * @param id 
   */
  public void setId(String id) {
    if (this.mId == null) {
      this.mId = new Attribute("id");
    }
    this.mId.setValue(id);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Abstract Methods">
  protected abstract String buildTag();
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Dynamic Attributes Implementation">
  @Override
  public void setDynamicAttribute(String uri, String attr, Object val) throws JspException {
    if (this.mAdditionalAttributes == null) {
      this.mAdditionalAttributes = new LinkedHashMap<>();
    }
    if (val == null) {
      val = "";
    }
    if (attr.equalsIgnoreCase("class")) {
      this.setClass((String) val);
    }
    else {
      if (!attr.equalsIgnoreCase("checked") || (("" + val).equalsIgnoreCase("true"))) {
        this.mAdditionalAttributes.put(attr, new Attribute(attr, (String) val));
      }
    }
  }
  //</editor-fold>
}
