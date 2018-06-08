/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.core.util;

import java.util.List;

/**
 *
 * @author soi
 */
public class StringUtil {
  
  /**
   * 
   * @param s
   * @return 
   */
  public static Boolean isEmpty(String s){
    return s == null || trim(s).isEmpty();
  }
  
  /**
   * 
   * @param s1
   * @param s2
   * @return 
   */
  public static Boolean equals(String s1, String s2){
    return (isEmpty(s1) && isEmpty(s2))
            || (isEmpty(s1) && isEmpty(s1) && s1.equals(s2));
  }
  
  /**
   * 
   * @param s1
   * @param s2
   * @return 
   */
  public static Boolean equalsIgnoreCase(String s1, String s2){
    return (isEmpty(s1) && isEmpty(s2))
            || (isEmpty(s1) && isEmpty(s1) && s1.equalsIgnoreCase(s2));
  }
  
  /**
   * 
   * @param s
   * @return 
   */
  public static String trim(String s){
    return trim(s,"\\s");
  }
  
  /**
   * 
   * @param s
   * @return 
   */
  public static String ltrim(String s){
    return ltrim(s,"\\s");
  }
  
  /**
   * 
   * @param s
   * @return 
   */
  public static String rtrim(String s){
    return rtrim(s,"\\s");
  }
  
  /**
   * 
   * @param s
   * @param c
   * @return 
   */
  public static String trim(String s, String c){
    String result = s;
    if (result != null && c != null){
      result = s.replaceAll(String.format("^%1$s*|%1$s*$",c), "");
    }
    return result;
  }
  
  /**
   * 
   * @param s
   * @param c
   * @return 
   */
  public static String ltrim(String s,String c){
    String result = s;
    if (!isEmpty(result) && !isEmpty(c)){
      result = result.replaceAll(String.format("^%s*",c), "");
    }
    return result;
  }
  
  /**
   * 
   * @param s
   * @param c
   * @return 
   */
  public static String rtrim(String s,String c){
    String result = s;
    if (!isEmpty(result) && !isEmpty(c)){
      result = result.replaceAll(String.format("%s*$",c), "");
    }
    return result;
  }
  
  /**
   * 
   * @param words
   * @return 
   */
  public static String join(String[] words){
    return join(words,",");
  }
  
  /**
   * 
   * @param words
   * @param del
   * @return 
   */
  public static String join(String[] words,String del){
    String result="";
    if (words != null && !isEmpty(del)){
      for (String word : words){
        if (!isEmpty(trim(word))) {
          result += del + trim(word);
        }
      }
      result = result.trim().replaceAll("^" + del + "|" + del + "$","");
    }
    return result;
  }
  
  /**
   * 
   * @param words
   * @param del
   * @return 
   */
  public static String join(List<String> words,String del){
    return join(words.toArray(new String[0]),del);
  }
  
  /**
   * 
   * @param words
   * @return 
   */
  public static String join(List<String> words){
    return join(words.toArray(new String[0]));
  }
}
