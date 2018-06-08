package entity.core.filter;

import entity.core.EntityBase;
import entity.core.filter.ConditionFilter.ConditionOperator;
import entity.core.filter.ConditionFilter.ConditionType;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Charlie Lay
 */
public class ConditionGroup implements Collection<ConditionFilter>, Serializable {
  
  //<editor-fold defaultstate="collapsed" desc="Group Operator Enum">
  //</editor-fold>
  
  /**
   * 
   */
  private ConditionType mType;
  
  /**
   * 
   */
  private List<ConditionFilter> mFilters;
  
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mFilters = null;
    this.mType = null;
  }
  
  /**
   * 
   * @param filters 
   */
  public ConditionGroup(ConditionFilter... filters) {
    this(ConditionType.AND,filters);
  }
  
  /**
   * 
   * @param op
   * @param filters 
   */
  public ConditionGroup(ConditionType op, ConditionFilter... filters) {
    if (op == null){
      throw new IllegalArgumentException("The group operator parameter cannot be unassigned!");
    }
    this.mType = op;
    this.mFilters = new ArrayList<>();
    if ((filters != null) && (filters.length > 0)) {
      this.addAll(Arrays.asList(filters));
    }
  }
  
  /**
   * 
   * @return 
   */
  public List<Object> getValues(){
    List<Object> result = null;
    
    if ((this.mFilters != null) && (this.size() > 0)){
      result = new ArrayList<>();
      for (ConditionFilter f : this){
        if ((f.getValues() != null) && (f.getValues().size() > 0)){
          result.addAll(f.getValues());
        }
      }
    }
    
    return result;
  }
  
  /**
   * 
   * @param name
   * @param op
   * @param values
   * @return 
   */
  public ConditionGroup and(String name, ConditionOperator op, Object... values) {
    return this.add(ConditionType.AND, name, op, values);
  }
  
  /**
   * 
   * @param name
   * @param op
   * @param values
   * @return 
   */
  public ConditionGroup or(String name, ConditionOperator op, Object... values) {
    return this.add(ConditionType.OR, name, op, values);
  }
  
  /**
   * 
   * @param name
   * @param op
   * @param values
   * @return 
   */
  public ConditionGroup add(String name, ConditionOperator op, Object... values) {
    return this.and(name,op,values);
  }
  
  /**
   * 
   * @param name
   * @param op
   * @param values
   * @return 
   */
  public ConditionGroup add(ConditionType type, String name, ConditionOperator op, Object... values) {
    ConditionFilter filter = null;
    
    if ((filter = ConditionFilter.createInstance(type, name, op, values)) != null){
      this.add(filter);
    }
    
    return this;
  }
  
  public ConditionType getType(){
    return this.mType;
  }
  
  /**
   * 
   * @param <E>
   * @param entity
   * @return 
   */
  public <E extends EntityBase> String toString(Class<E> entityClass) {
    String result = null;
    
    if ((this.size() > 0)) {
      String tmp = "",op="";
      int i=0;
      for (ConditionFilter f : this){
        if (i++ > 0) {
          op = f.getType().toString();
        }
        tmp += op + f.toString(entityClass);
      }
      result = "(" + tmp + ")";
    }
    return result;
  }

  /**
   * 
   * @param i
   * @return 
   */
  public ConditionFilter get(int i) {
    ConditionFilter result = null;
    try {
      result = this.mFilters.get(i);
    } catch (Exception e) {
      
    }
    finally {
      return result;
    }
  }
  
  //<editor-fold defaultstate="collapsed" desc="Collection Implementations">
  @Override
  public int size() {
    return this.mFilters.size();
  }

  @Override
  public boolean isEmpty() {
    return this.mFilters.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return this.mFilters.contains(o);
  }

  @Override
  public Iterator<ConditionFilter> iterator() {
    return this.mFilters.iterator();
  }

  @Override
  public Object[] toArray() {
    return this.mFilters.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return this.mFilters.toArray(a);
  }

  @Override
  public boolean add(ConditionFilter e) {
    return this.mFilters.add(e);
  }

  @Override
  public boolean remove(Object o) {
    return this.mFilters.remove(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return this.mFilters.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends ConditionFilter> c) {
    return this.mFilters.addAll(c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return this.mFilters.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return this.mFilters.retainAll(c);
  }

  @Override
  public void clear() {
    this.mFilters.clear();
  }
  //</editor-fold>
}
