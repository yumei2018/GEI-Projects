/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Charlie Lay
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EntityJoin {
  /**
   * (Optional) If the join is in a separate link table, specify the link table here
   * @return 
   */
  EntityTable table() default @EntityTable();
  
  /**
   * <b>Required</b> - The parent's column definition
   * 
   * @return 
   */
  EntityColumn[] column();
  
  /**
   * 
   * @return 
   */
  EntitySort[] sort() default @EntitySort(column = @EntityColumn());
  
  /**
   * Do not load when parent is loading.
   * 
   * @return 
   */
  boolean lazy() default false;
  boolean unique() default false;
}
