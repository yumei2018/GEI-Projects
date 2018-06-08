/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.core.annotation;

import java.lang.annotation.*;

/**
 *
 * @author Charlie Lay
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpParameter {
  String name() default "";
  
  String pattern() default "";
  
  String format() default "";
}
