package entity.core.utils;

import entity.core.util.ExceptionUtil;
import org.junit.Test;

/**
 *
 * @author soi
 */
public class ExceptionUtilTest {
  
  @Test
  public void t1(){
    try {
      try {
        throw new Exception("First Level!");
      }
      catch(Exception e1) {
        try {
          throw e1;
        }
        catch(Exception e2) {
          try {
            throw e2;
          }
          catch(Exception e3) {
            throw e3;
          }
        }
      }
    }
    catch(Exception ex) {
      System.out.println(ExceptionUtil.readStackTrace(ex));
    }
  }
}
