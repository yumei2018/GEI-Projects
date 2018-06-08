/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.core.utils;

import entity.core.util.DateUtil;
import org.junit.Test;

/**
 *
 * @author Charlie Lay
 */
public class DateUtilTests {
  @Test
  public void todaytest(){ 
    System.out.println(DateUtil.getToday().getClass().getName());
    java.util.Date d = DateUtil.getToday();
    System.out.println(d.getClass().getName());
  }
  
  @Test
  public void dates(){
    java.util.Date d1 = DateUtil.getToday();
    System.out.println(d1);
    java.sql.Date d2 = DateUtil.getTodayAsSqlDate();
    System.out.println(d2);
    java.util.Date d3 = DateUtil.getNow();
    System.out.println(d3);
    java.sql.Timestamp d4 = DateUtil.getNowAsTimestamp();
    System.out.println(d4);
  }
}
