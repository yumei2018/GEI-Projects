package entity.core.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author clay
 */
public class ExceptionUtil {
  
  public static <E extends RuntimeException> void throwException(Class<E> ExceptionClass, Exception oex) throws RuntimeException {
    ExceptionUtil.throwException(ExceptionClass, oex.getMessage(), oex);
  }
  
  public static <E extends RuntimeException> void throwException(Class<E> ExceptionClass, String error, Exception oex) {
    E result = null;
    try {
      result = ExceptionClass.getDeclaredConstructor(String.class).newInstance(error);
      result.setStackTrace(oex.getStackTrace());
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    finally {
      if (result != null) {
        throw result;
      }
    }
  }
  
  public static void throwNullException(Exception oex) {
    ExceptionUtil.throwException(NullPointerException.class, oex);
  }
  
  public static void throwNullException(String error, Exception oex) {
    ExceptionUtil.throwException(NullPointerException.class, error, oex);
  }
  
  public static void throwIllegalArgumentException(Exception oex) {
    ExceptionUtil.throwException(IllegalArgumentException.class, oex);
  }
  
  public static void throwIllegalArgumentException(String error, Exception oex) {
    ExceptionUtil.throwException(IllegalArgumentException.class, error, oex);
  }
  
  public static void throwIllegalStateException(Exception oex) {
    ExceptionUtil.throwException(IllegalStateException.class, oex);
  }
  
  public static void throwIllegalStateException(String error, Exception oex) {
    ExceptionUtil.throwException(IllegalStateException.class, error, oex);
  }
  
  public static String readStackTrace(Exception ex) {
    String result = null;
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      try (PrintStream ps = new PrintStream(baos)) {
        ex.printStackTrace(ps);
        result = ex.getMessage() + "\n" + new String(baos.toByteArray(), StandardCharsets.UTF_8);
      }
    } 
    catch (IOException ex1) {
      Logger.getLogger(ExceptionUtil.class.getName()).log(Level.SEVERE, null, ex1);
    }
    
    return result;
  }
}
