package entity.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author soi
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EntityColumn {
  public String name() default "";
  
  public String reference() default "";

  public boolean nullable() default true;

  public boolean insertable() default true;

  public boolean updatable() default true;
  
  public boolean primary() default false;
  
  public boolean autoId() default false;

  public int length() default -1;

  public int precision() default -1;

  public int scale() default -1;
  
  public boolean unique() default false;
}
