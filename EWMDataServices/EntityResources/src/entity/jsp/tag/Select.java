package entity.jsp.tag;

import entity.jsp.tag.base.BaseTag;
import entity.jsp.tag.base.SelectOptionAttribute;
import entity.jsp.tag.base.iSelectOption;
import java.util.*;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

/**
 *
 * @author soi
 */
public class Select extends BaseTag {

  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  private Collection <iSelectOption> mOptions;
  private List mPlainOptions;
  private List<SelectOptionAttribute> mOptionAttributes;
  
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mOptions = null;
    this.mPlainOptions = null;
    this.mOptionAttributes = null;
  }
  //</editor-fold>
  
  @Override
  protected String buildTag() {
    String tpl = "<select %1$s>%2$s</select>";
    String result = String.format(tpl
                                ,this.buildAttributes()
                                ,this.buildOptions());
    
    return result;
  }
  
  /**
   * 
   * @param <EC>
   * @param options 
   */
  public <I extends iSelectOption,EC extends Collection<I>> void setOptions(EC options) {
    this.mOptions = new ArrayList<>();
    if (options != null) {
      this.mOptions.addAll(options);
    }
  }
  
  /**
   * 
   * @param <EC>
   * @param options 
   */
  public void setPlainOptions(List options) {
    this.mPlainOptions = new ArrayList<>();
    if (options != null) {
      this.mPlainOptions.addAll(options);
    }
  }

  //<editor-fold defaultstate="collapsed" desc="Set Option Attributes - setOptionAttributes(List<SelectOptionAttribute> attrs)">
  public final void setOptionAttributes(List<SelectOptionAttribute> attrs){
    this.mOptionAttributes = attrs;
  }
  //</editor-fold>
  
  /**
   * 
   * @return 
   */
  public String buildOptions(){
    String placeholder = null;
    if (StringUtils.isEmpty(placeholder = this.getPlaceholder())) {
      placeholder = "Select an option...";
    }
    else {
      placeholder = placeholder.substring(placeholder.indexOf("'")).replace("'", "");
    }
    String result = "<option value=''>" + placeholder + "</option>";
    if (!StringUtils.isEmpty(this.mOptions)) {
      String value=this.getValueAttribute() != null ? this.getValueAttribute().getValue() : "";
      String optLabel = null;
      Object optValue = null;
      for (iSelectOption option : this.mOptions) {
        optValue = option.getOptionValue();
        optValue = StringUtils.isEmpty(optValue) ? "" : optValue.toString();
        optLabel = option.getOptionLabel();
        optLabel = StringUtils.isEmpty(optLabel) ? " " : optLabel;

        result += String.format("<option value='%1$s' %2$s %3$s>%4$s</option>"
          ,HtmlUtils.htmlEscape((String) optValue)
          ,StringUtils.isEmpty(value) || (!Objects.equals(optValue, value.toString())) ?  "" : "selected"
          ,this.buildOptionAttributes()
          ,HtmlUtils.htmlEscape(optLabel));
      }
    }
    
    if (!StringUtils.isEmpty(this.mPlainOptions)) {
      String value=this.getValueAttribute() != null ? this.getValueAttribute().getValue() : "";
      for (Object option : this.mPlainOptions) {
        result += String.format("<option value='%1$s' %2$s>%3$s</option>"
                      ,HtmlUtils.htmlEscape(option.toString())
                      ,StringUtils.isEmpty(value) || (!Objects.equals(option.toString(), value)) ?  "" : "selected"
                      ,HtmlUtils.htmlEscape(option.toString()));
      }
    }
    
    return result;
  }

  //<editor-fold defaultstate="collapsed" desc="Build Option Attributes - buildOptionAttributes()">
  public final String buildOptionAttributes(){
    String result = null;

    if (this.mOptionAttributes == null) {
      result = "";
    }
    else {
      String space = "";
      result = "";
      for (SelectOptionAttribute soa : this.mOptionAttributes) {
        if (!soa.isValid()) {
          continue;// can throw exception here need be
        }
        result += space + soa.toString();
        space = " ";
      }
    }

    return result;
  }
  //</editor-fold>
}
