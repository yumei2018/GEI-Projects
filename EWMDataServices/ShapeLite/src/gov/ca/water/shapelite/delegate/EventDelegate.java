package gov.ca.water.shapelite.delegate;

/**
 *
 * @author clay
 */
public abstract class EventDelegate extends Delegate {
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public EventDelegate(Object listener) {
    super(listener);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="On Event Handler">
  public abstract void onEvent(Object...args);
  //</editor-fold>
}
