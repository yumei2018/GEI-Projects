package com.gei.others;

import entity.core.filter.ConditionGroup;
import entity.core.util.ListUtil;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.junit.Test;

/**
 *
 * @author soi
 */
public class JavaTest {
  @Test
  public void test(){
    ConditionGroup cg = new ConditionGroup();
    System.out.println(cg.get(0));
  }
  
  @Test
  public void t2(){
    Double d = null;
    System.out.println(Objects.equals(d, 2d));
    Boolean b = true;
    System.out.println(Objects.equals(b, true));
  }
  
  @Test
  public void t3(){
    Integer i = 23;
    Double d = 33d;
    Long l = 34l;
    
    System.out.println(d < l);
  }
  
  @Test
  public void t4(){
    String placeholder = " placeholder='Enter name here...'";
    placeholder = placeholder.substring(placeholder.indexOf("'")).replace("'", "");
    System.out.println(placeholder);
  }
  
  
  @Test
  public void t5(){
    List<String> l = Arrays.asList("b", "a","a","b","c");
    System.out.println("Original List");
    for (String s : l) {
      System.out.println(s);
    }
    
    System.out.println("Unique List");
    l = ListUtil.unique(l);
    for (String s : l) {
      System.out.println(s);
    }
  }
  
  @Test
  public void t6(){
    Integer one = new Integer(1);
    System.out.println(one.getClass().isPrimitive());
  }
  
  @Test
  public void t7(){
    Map m = new LinkedHashMap();
    System.out.println(Map.class.isAssignableFrom(m.getClass()));
  }
  
  @Test
  public void t8(){
    System.out.println(Integer.class.isAssignableFrom(int.class));
    System.out.println(int.class.isAssignableFrom(Integer.class));
    System.out.println(Objects.equals(int.class, Integer.class));
  }
}
