/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.core.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.util.StringUtils;

/**
 *
 * @author soi
 */
public class ReflectionUtil implements Serializable {
  
  /**
   * 
   * @param <TObj>
   * @param o
   * @param methodName
   * @param params
   * @return 
   */
  public static <TObj> TObj invoke(Object o, String methodName, Object... params) throws Exception {
    if (DataUtil.isEmpty(methodName)) {
      throw new IllegalArgumentException("The method name cannot be unassigned!");
    }
    if (o == null) {
      throw new IllegalArgumentException("The object parameter cannot be unassigned!");
    }
    TObj result = null;
    
    List<Method> methods = new ArrayList<>();
    Method[] tmp=null;
    Class cls = o.getClass();
    do {
      if (((tmp = cls.getMethods()) != null)
        && (tmp.length > 0) ) {
        methods.addAll(Arrays.asList(tmp));
      }
      if (((tmp = cls.getDeclaredMethods()) != null)
          && (tmp.length > 0) ) {
        methods.addAll(Arrays.asList(tmp));
      }
    } while (!(cls = cls.getSuperclass()).equals(Object.class));
    boolean parammatch=false;
    Class[] paramTypes = null;
    for (Method m : methods) {
      if (m.getName().toLowerCase().equals(methodName.toLowerCase())) {
        if ((params != null)) {
          if (((paramTypes = m.getParameterTypes()) == null)
            || (!(parammatch = params.length == paramTypes.length))) {
            continue;
          }
          else {
            for (int i=0;i<paramTypes.length;++i) {
              parammatch = false;
              for (int j=0;j<params.length;++j) {
                if ((parammatch = paramTypes[i].isAssignableFrom(params[j].getClass()))) {
                  break;
                }
              }
              if (!(parammatch)) {
                break;
              }
            }
            if (!parammatch){
              continue;
            }
          }
        }
        m.setAccessible(true);
        result = (TObj) m.invoke(o, params);
        break;
      }
    }
    
    return (TObj)result;
  }
  
  /**
   * 
   * @param o
   * @param fieldname
   * @param value 
   */
  public final static void set(Object o, String fieldname, Object value) throws Exception {
    if (DataUtil.isEmpty(fieldname)) {
      throw new IllegalArgumentException("The fieldname parameter cannot be unassigned!");
    }
    if (o == null) {
      throw new IllegalArgumentException("The object parameter cannot be unassigned!");
    }
    
    Field[] fields=null;
    Class cls = o.getClass();
    Boolean isSet = false;
    do {
      if (((fields = cls.getFields()) != null)
        && (fields.length > 0) ) {
        for (Field f : fields) {
          if (f.getName().toLowerCase().equals(fieldname.toLowerCase())) {
            f.setAccessible(true);
            f.set(o, value);
            isSet = true;
            break;
          }
        }
      }
      if (((fields = cls.getDeclaredFields()) != null)
        && (fields.length > 0) ) {
        for (Field f : fields) {
          if (f.getName().toLowerCase().equals(fieldname.toLowerCase())) {
            f.setAccessible(true);
            f.set(o, value);
            isSet = true;
            break;
          }
        }
      }
    } while (!(cls = cls.getSuperclass()).equals(Object.class) && !isSet);
  }
  
  /**
   * 
   * @param o
   * @param fieldname
   * @return 
   */
  public final static Field getField(Class cls, String fieldname) {
    if (StringUtils.isEmpty(fieldname)) {
      throw new IllegalArgumentException("The fieldname parameter cannot be unassigned!");
    }
    if (cls == null) {
      throw new IllegalArgumentException("The cls parameter cannot be unassigned!");
    }
    Field result = null;
    Field[] fields=null;
    Boolean isSet = false;
    do {
      if (((fields = cls.getFields()) != null)
        && (fields.length > 0) ) {
        for (Field f : fields) {
          if (f.getName().toLowerCase().equals(fieldname.toLowerCase())) {
            result = f;
            break;
          }
        }
      }
      if (((fields = cls.getDeclaredFields()) != null)
        && (fields.length > 0) ) {
        for (Field f : fields) {
          if (f.getName().toLowerCase().equals(fieldname.toLowerCase())) {
            result = f;
            break;
          }
        }
      }
    } while (!(cls = cls.getSuperclass()).equals(Object.class) && (result == null));
    
    return result;
  }
  
  /**
   * 
   * @param <TObj>
   * @param o
   * @param fieldname
   * @return 
   */
  public final static <TObj extends Object> TObj get(Object o, String fieldname) {
    TObj result = null;
    
    Field field = null;
    if ((field = getField(o.getClass(),fieldname))!=null) {
      try {
        field.setAccessible(true);
        result = (TObj) field.get(o);
      }
      catch(Exception ex){
        throw new IllegalStateException(
          String.format("%s.%s Error(s):\n%s"
            ,ReflectionUtil.class.getName()
            ,"get(o,fieldname)"
            ,ex.getMessage()
          )
        );
      }
    }
    return result;
  }
}
