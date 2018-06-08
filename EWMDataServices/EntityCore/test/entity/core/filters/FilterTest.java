/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.core.filters;

import entity.core.filter.ConditionGroup;
import entity.core.filter.ConditionFilter;
import com.gei.entities.User;
import entity.core.filter.ConditionFilter.ConditionOperator;
import entity.core.filter.ConditionFilter.ConditionType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author Charlie Lay
 */
public class FilterTest {
  
  @Test
  public void ConditionFilterTests(){
    User user = new User();
    System.out.println("EQ: " + ConditionFilter.createInstance("username", ConditionOperator.SQL_EQUAL, "Charlie").toString(User.class));
    System.out.println("LT: " + ConditionFilter.createInstance("userId", ConditionOperator.SQL_LT, 5).toString(User.class));
    System.out.println("LE: " + ConditionFilter.createInstance("userId", ConditionOperator.SQL_LE, 5).toString(User.class));
    System.out.println("GT: " + ConditionFilter.createInstance("userId", ConditionOperator.SQL_GT, 5).toString(User.class));
    System.out.println("GE: " + ConditionFilter.createInstance("userId", ConditionOperator.SQL_LE, 5).toString(User.class));
    System.out.println("NE: " + ConditionFilter.createInstance("userId", ConditionOperator.SQL_NOT, 5).toString(User.class));
    System.out.println("BETWEEN: " + ConditionFilter.createInstance("userId", ConditionOperator.SQL_BETWEEN, 5, 7).toString(User.class));
    System.out.println("IN: " + ConditionFilter.createInstance("userId", ConditionOperator.SQL_IN, 5,2,3,4,7,8).toString(User.class));
    System.out.println("OUT: " + ConditionFilter.createInstance("userId", ConditionOperator.SQL_OUT, 5,2,3,4,7,8).toString(User.class));
    System.out.println("IN: " + ConditionFilter.createInstance("userId", ConditionOperator.SQL_ISNULL).toString(User.class));
    System.out.println("IN: " + ConditionFilter.createInstance("userId", ConditionOperator.SQL_NOTNULL).toString(User.class));
    System.out.println("LIKE: " + ConditionFilter.createInstance("userId", ConditionOperator.SQL_LE, "%lay%").toString(User.class));
  }
  
  @Test
  public void ConditionFilterFailureTests(){
    this.ColumnNotFoundFailTest();
    this.MistmatchValueFilterFailureTest();
  }
  
  private void ColumnNotFoundFailTest(){
    try {
      User user = new User();
      System.out.println("EQ: " + ConditionFilter.createInstance("nae", ConditionOperator.SQL_EQUAL, "Charlie").toString(User.class));
    } catch (Exception exp) {
      Logger
      .getLogger(this.getClass().getName())
      .log(Level.INFO, "{0}.ColumnNotFoundFailTest Error:\n {1}", new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }   
  }
  
  private void MistmatchValueFilterFailureTest(){
    try {
      User user = new User();
      System.out.println("ISNULL: " + ConditionFilter.createInstance("name", ConditionOperator.SQL_ISNULL, "Charlie").toString(User.class));
    } catch (Exception exp) {
      Logger
      .getLogger(this.getClass().getName())
      .log(Level.INFO, "{0}.ColumnNotFoundFailTest ISNULL Error:\n {1}", new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }   
    
    try {
      User user = new User();
      System.out.println("BETWEEN: " + ConditionFilter.createInstance("name", ConditionOperator.SQL_BETWEEN, "Charlie").toString(User.class));
    } catch (Exception exp) {
      Logger
      .getLogger(this.getClass().getName())
      .log(Level.INFO, "{0}.ColumnNotFoundFailTest BETWEEN Error:\n {1}", new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }
    
    try {
      User user = new User();
      System.out.println("BETWEEN: " + ConditionFilter.createInstance("name", ConditionOperator.SQL_BETWEEN, "Charlie",1,3,4).toString(User.class));
    } catch (Exception exp) {
      Logger
      .getLogger(this.getClass().getName())
      .log(Level.INFO, "{0}.ColumnNotFoundFailTest BETWEEN Error:\n {1}", new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }
    
    try {
      User user = new User();
      System.out.println("IN: " + ConditionFilter.createInstance("name", ConditionOperator.SQL_IN).toString(User.class));
    } catch (Exception exp) {
      Logger
      .getLogger(this.getClass().getName())
      .log(Level.INFO, "{0}.ColumnNotFoundFailTest IN Error:\n {1}", new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }
    
    try {
      User user = new User();
      System.out.println("OUT: " + ConditionFilter.createInstance("name", ConditionOperator.SQL_OUT).toString(User.class));
    } catch (Exception exp) {
      Logger
      .getLogger(this.getClass().getName())
      .log(Level.INFO, "{0}.ColumnNotFoundFailTest OUT Error:\n {1}", new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }
  }
  
  @Test
  public void GroupFilterTest(){
    ConditionGroup gf = new ConditionGroup(ConditionFilter.createInstance(ConditionType.OR,"username", ConditionOperator.SQL_EQUAL, "Charlie"));
    gf.and("userId",ConditionOperator.SQL_LT, 5)
      .or("createDate",ConditionOperator.SQL_BETWEEN,new Date(), new Date());
    System.out.println(gf.toString(User.class));
  }
  
  @Test
  public void arraytest(){
    Integer[] ints = new Integer[]{1,2,3};
    List a1 = new ArrayList();
    System.out.println(a1.size());
    a1.addAll(Arrays.asList(1,2,3));
    System.out.println(a1.size());
    a1.addAll(Arrays.asList(ints));
    System.out.println(a1.size());
    Object[] objs = {"a","b","c"};
    a1.addAll(Arrays.asList(objs));
    System.out.println(a1.size());
  }
}
