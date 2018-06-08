/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.core.utils;

import entity.core.util.DataUtil;
import org.junit.Test;

/**
 *
 * @author soi
 */
public class DataUtilTest {
  @Test
  public void testequal(){
    System.out.println(DataUtil.isEqual(new Integer(2), new Double(1.0)));
    System.out.println(DataUtil.isEqual(new Integer(2), new Integer(2)));
    System.out.println(DataUtil.isEqual(new Integer(2), 2));
    System.out.println(DataUtil.isEqual(new Double(2), 2));
  }
}
