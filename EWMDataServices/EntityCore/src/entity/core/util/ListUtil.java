package entity.core.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author clay
 */
public class ListUtil implements Serializable {

  //<editor-fold defaultstate="collapsed" desc="Match">

  /**
   *
   * @param <T>
   * @param <L>
   * @param list1
   * @param list2
   * @return
   */
  public static <T,L extends List<T>> Boolean match(L list1, L list2) {
    Boolean result = (list1 != null) && (list2 != null) && (list1.size() == list2.size());
    
    if (result) {
      for (T t : list1) {
        if (!(result = list2.contains(t))) {
          break;
        }
      }
    }
    
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Unique">
  public static <T,L extends List<T>> L unique(L result) {
    if  (result != null && !result.isEmpty()) {
      L l = (L)new ArrayList<>();
      Iterator<T> it = result.iterator();
      T t = null;
      while (it.hasNext()) {
        t = it.next();
        if (l.contains(t)) {
          it.remove();
        }
        else {
          l.add(t);
        }
      }
    }
    
    return result;
  }
  //</editor-fold>
}
