/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.jsp.tag.base;

import java.io.Serializable;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

/**
 *
 * @author soi
 */
public class Attribute implements Serializable {
  /**
   * 
   */
  private String mKey;
  /**
   * 
   */
  private String mValue;
  /**
   * 
   */
  private Boolean mKeyOnly;
  
  /**
   * 
   * @param k 
   */
  public Attribute(String k) {
    this(k,null,false);
  }
  
  /**
   * 
   * @param k 
   */
  public Attribute(String k,String v) {
    this(k,v,false);
  }
  
  /**
   * 
   * @param k 
   */
  public Attribute(String k,Boolean keyonly) {
    this(k,null,keyonly);
  }
  
  /**
   * 
   * @param k
   * @param v 
   */
  public Attribute(String k, String v, Boolean keyonly) {
    this.mKey = k;
    this.mValue = v;
    this.mKeyOnly = keyonly;
  }
  
  /**
   * 
   * @param value 
   */
  public void setValue(String value) {
    this.mValue = value;
  }

  /**
   * 
   * @return 
   */
  public String getValue() {
    return this.mValue;
  }
  
  @Override
  public String toString() {
    String result = "";
    
    if ((!StringUtils.isEmpty(this.mKey)) && (!StringUtils.isEmpty(this.mValue))) {
      result = " " + HtmlUtils.htmlEscape(this.mKey);
      if ((this.mKeyOnly == null) || (!this.mKeyOnly)) {
         result += "='" + HtmlUtils.htmlEscape(this.mValue) + "'";
      }
    }
    
    return result;
  }
  
  public String getKey(){
    return this.mKey;
  }
}
