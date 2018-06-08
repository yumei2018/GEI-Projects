/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.core.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author soi
 */
public class DataUtil implements Serializable {
  /**
   * 
   * @param o
   * @return 
   */
  public final static boolean isEmpty(String o){
    return (o == null) || (o.trim().isEmpty());
  }
  
  /**
   * 
   * @param s
   * @param s2
   * @return 
   */
  public final static boolean isEqual(String s, String s2){
    return (s != null) && (s.trim().equals(s2));
  }
  
  /**
   * 
   * @param o
   * @param o2
   * @return 
   */
  public final static boolean isEqual(Object o, Object o2){
    return (o != null) 
            && ((o.getClass().isPrimitive() && (o == o2))
            || (o.equals(o2)));
  }
  
  /**
   * 
   * @param s
   * @param s2
   * @return 
   */
  public final static boolean isEqual(Integer s, Integer s2){
    return (s != null) && (s.equals(s2));
  }
  
  /**
   * 
   * @param strs
   * @return 
   */
  public final static boolean isEmpty(String[] strs){
    return (strs == null) || (strs.length == 0);
  }
  
  /**
   * 
   * @param col
   * @return 
   */
  public final static boolean isEmpty(Collection col){
    return (col == null) || (col.isEmpty());
  }
  
  /**
   * 
   * @param map
   * @return 
   */
  public final static boolean isEmpty(Map map) {
    return (map == null) || (map.isEmpty());
  }
  
  /**
   * 
   * @param objs
   * @return 
   */
  public final static boolean isEmpty(Object[] objs) {
    return (objs == null) || (objs.length == 0);
  }
}
