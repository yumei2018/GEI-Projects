package entity.core.logger;

import entity.core.util.DateUtil;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.util.StringUtils;

/**
 *
 * @author clay
 */
public class TimeLogger {
  
  //<editor-fold defaultstate="collapsed" desc="Enum Display Unit">
  public static enum DisplayUnit {
    MINUTE
    ,SECOND
    ,HOUR
    ,DAY
    ,MONTH
    ,WEEK
    ,YEAR
    ,MILLISECOND;

    public String toString(Date start, Date end) {
      String result = null;
      
      if (Objects.equals(this, MINUTE)) {
        result = DateUtil.dateDiffInMinutes(mStart, end) + " minutes.";
      }
      else if (Objects.equals(this, SECOND)) {
        result = DateUtil.dateDiffInSeconds(mStart, end) + " seconds.";
      }
      else if (Objects.equals(this,HOUR)) {
        result = DateUtil.dateDiffInHours(mStart, end) + " hours.";
      }
      else if (Objects.equals(this,DAY)) {
        result = DateUtil.dateDiff(mStart, end) + " days.";
      }
      else if (Objects.equals(this,MONTH)) {
        // not supported yet
      }
      else if (Objects.equals(this,WEEK)) {
        // not supported yet
      }
      else if (Objects.equals(this,YEAR)) {
        // not supported yet
      }
      else if (Objects.equals(this,MILLISECOND)) {
        // not supported yet
      }
      
      return result;
    }
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="expanded" desc="Private Static Properties">
  private static Date mStart;
  private static Class mClass;
  private static String mPrefix;
  private static Logger mLogger;
  private static DisplayUnit mUnit;
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Start Timer">
  public synchronized static void Start() { 
    TimeLogger.Start(null, "The process takes about ");
  }
  
  public synchronized static void Start(Class cls, String prefix) {
    TimeLogger.Start(cls, prefix, DisplayUnit.SECOND);
  }
  
  public synchronized static void Start(Class cls, String prefix, DisplayUnit unit) {
    if (TimeLogger.mStart != null) {
      TimeLogger.Reset();
      return;
//      throw new IllegalStateException(
//        String.format("%s.%s: The timer has started already from another process!"
//          ,TimeLogger.class.getName()
//          ,"StartTimer"
//        )
//      );
    }
    TimeLogger.setClass(cls);
    TimeLogger.setPrefix(prefix);
    TimeLogger.mStart = DateUtil.getNow();
    TimeLogger.setDisplayUnit(unit);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Stop">
  public synchronized static void Stop() {
    if (TimeLogger.mStart == null) {
      return;
    }
    
    Date end = DateUtil.getNow();
    String suffix = null;
    
    String log = String.format("%s %s"
      ,TimeLogger.getPrefix()
      ,TimeLogger.getUnit().toString(TimeLogger.mStart, end)
    );
    
    TimeLogger.getLogger().log(Level.INFO,log); 
    
    TimeLogger.Reset();
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Set Class">
  private synchronized static void setClass(Class cls) {
    TimeLogger.mClass = cls;
    TimeLogger.mLogger = Logger.getLogger(TimeLogger.mClass == null ? "" : TimeLogger.mClass.getName());
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Reset">
  private synchronized static void Reset() {
    TimeLogger.mStart = null;
    TimeLogger.mClass = null;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Logger">
  private synchronized static Logger getLogger() {
    return TimeLogger.mLogger;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Set Prefix">
  private synchronized static void setPrefix(String prefix) {
    TimeLogger.mPrefix = prefix;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Set Display Unit">
  private synchronized static void setDisplayUnit(DisplayUnit unit) {
    TimeLogger.mUnit = unit;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Prefix">
  public synchronized static String getPrefix() {
    return StringUtils.isEmpty(TimeLogger.mPrefix) ? "The process takes about " : TimeLogger.mPrefix;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Unit">
  private static DisplayUnit getUnit() {
    return TimeLogger.mUnit == null ? DisplayUnit.SECOND : TimeLogger.mUnit;
  }
  //</editor-fold>
}