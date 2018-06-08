package entity.jsp.tag;

import entity.jsp.tag.base.BaseTag;
import org.springframework.web.util.HtmlUtils;

/**
 *
 * @author soi
 */
public class TextArea extends BaseTag {

  @Override
  protected String buildTag() {
    String tpl = "<textarea %1$s>%2$s</textarea>";
    String value = this.getValueAttribute() != null ? this.getValueAttribute().getValue() : "";
    String result = String.format(tpl
                                ,this.buildAttributes()
                                ,HtmlUtils.htmlEscape(value));
    
    return result;
  }
  
}
