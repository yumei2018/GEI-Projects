package entity.core.interfaces;

import entity.core.EntityBase;
import entity.core.delegate.DelegateBase;

/**
 *
 * @author clay
 */
public abstract class EntityPredicate<E extends EntityBase> extends DelegateBase {

  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public EntityPredicate() {
    super(null);
  }
  
  public EntityPredicate(Object listener) {
    super(listener);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Evaluate">
  public abstract boolean evaluate(E entity);
  //</editor-fold>
}
