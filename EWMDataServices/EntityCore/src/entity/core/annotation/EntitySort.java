package entity.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EntitySort {
  /**
   * 
   * @return 
   */
  String direction() default "ASC";
  
  /**
   * 
   * @return 
   */
  EntityColumn column();
}
