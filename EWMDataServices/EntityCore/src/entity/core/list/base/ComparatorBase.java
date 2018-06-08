package entity.core.list.base;

import java.util.Comparator;

/**
 *
 * @author clay
 */
public abstract class ComparatorBase<O extends Object> implements Comparator<O>{

  //<editor-fold defaultstate="collapsed" desc="Private Comparing">
  protected Integer comparing(Object o1, Object o2) {
    Integer result = null;
    
    if (o1 != null) {
      if (o2 == null) {
        result = 1;
      }
    }
    else if (o2 != null) {
      if (o1 == null) {
        result = -1;
      }
    }
    
    return result;
  }
  //</editor-fold>
  
  @Override
  public abstract int compare(O o1, O o2);
}
