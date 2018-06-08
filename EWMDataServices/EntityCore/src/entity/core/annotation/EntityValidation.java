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
@Target({ElementType.FIELD})
public @interface EntityValidation {
  /**
   * The field cannot be empty or null when required is true.
   * @return 
   */
  public boolean required() default false;
  /**
   * Custom error message
   * 
   * @return 
   */
  public String error() default "";
  /**
   * The reader friendly field to display.
   * 
   * @return 
   */
  public String field() default "";
  /**
   * Regex pattern the value must comply with.
   * @return 
   */
  public String pattern() default "";
  /**
   * The readable format to display.
   * 
   * @return 
   */
  public String format() default "";
  /**
   * The maximum value.
   * 
   * For dates, long values of the maximum date is needed.
   * 
   * @return 
   */
  public long max() default Long.MAX_VALUE;
  /**
   * The minimum value.
   * 
   * For dates, long values of the minium date is need.
   * 
   * @return 
   */
  public long min() default 0;
}
