package entity.core.delegate;

import java.lang.reflect.Field;

/**
 *
 * @author soi
 */
public abstract class LoadPropertyDelegate extends DelegateBase {
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public LoadPropertyDelegate(Object listener) {
    super(listener);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Abstract Parser">
  public abstract void parse(Field f,String strVal);
  //</editor-fold>
}
