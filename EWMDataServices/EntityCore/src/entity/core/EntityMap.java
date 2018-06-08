package entity.core;

import entity.core.delegate.QueryDelegate;
import entity.core.jdbc.ConnectionContext;
import entity.core.util.EntityUtil;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/**
 *
 * @author clay
 * @param <O>
 * @param <E>
 */
public class EntityMap<O extends Object, E extends EntityBase> implements Map<O,E> {

  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  private Map<O,E> mCollection;
  private E mEntity;
  
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mCollection = null;
    this.mEntity = null;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public EntityMap(Class<E> eClass){
    try {
      this.mEntity = eClass.newInstance();
    } catch (Exception ex) {
      throw new IllegalStateException(
              String.format("%s.%s Error(s):\n%s"
                      ,this.getClass().getName()
                      ,"EntityCollectionMap(eClass)"
                      ,ex.getMessage()
              )
      );
    }
    
    this.mCollection = new LinkedHashMap<>();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Map Implementations">
  @Override
  public int size() {
    return this.mCollection.size();
  }

  @Override
  public boolean isEmpty() {
    return this.mCollection.isEmpty();
  }

  @Override
  public boolean containsKey(Object key) {
    return this.mCollection.containsKey(key);
  }

  @Override
  public boolean containsValue(Object value) {
    return this.mCollection.containsValue(value);
  }

  @Override
  public E get(Object key) {
    return this.mCollection.get(key);
  }

  @Override
  public E put(O key, E value) {
    return this.mCollection.put(key, value);
  }

  @Override
  public E remove(Object key) {
    return this.mCollection.remove(key);
  }

  @Override
  public void putAll(Map<? extends O, ? extends E> m) {
    this.mCollection.putAll(m);
  }

  @Override
  public void clear() {
    this.mCollection.clear();
  }

  @Override
  public Set<O> keySet() {
    return this.mCollection.keySet();
  }

  @Override
  public Collection<E> values() {
    return this.mCollection.values();
  }

  @Override
  public Set<Entry<O, E>> entrySet() {
    return this.mCollection.entrySet();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Entity Collection Methods">
  /**
   * 
   * @return 
   */
  public final E getEntity(){
    return this.mEntity;
  }
  
  protected final Class<E> getEntityClass(){
    return (Class<E>) this.mEntity.getClass();
  }
  
  /**
   * 
   * @param <EC>
   * @param args
   * @return 
   */
  public final <EC extends EntityMap> EC findAll(Object...args) {
    Collection<E> results = null;
    if (((results = this.mEntity.clearFilters().findAll(args)) != null) && (results.size() > 0)) {
      try {
        Field idField = this.getIdField();
        Object idVal = null;
        for (E entity : results) {
          idField.setAccessible(true);
          idVal = idField.get(entity);
          this.put((O) idVal, entity);
        }
      } 
      catch (Exception e) {
        throw new IllegalStateException(
          String.format("%s.findPage(pageNo,pageSize,Object[]) %s:\n%s"
            ,this.getClass().getName()
            ,e.getClass().getName()
            ,e.getMessage()
          )
        );
      }
    }
    
    return (EC) this;
  }
  
  protected final Field getIdField(){
    List<Field> idFields = null;
    if (((idFields = EntityUtil.getIdFields(this.getEntityClass())) == null)
      || (idFields.isEmpty())
      || (idFields.size() > 1)){
      throw new IllegalStateException("The entity collection map does not support composite primary key entities!");
    }
    return idFields.get(0);
  }
  
  /**
   * 
   * @param <EC>
   * @param pageNo
   * @param pageSize
   * @param args
   * @return 
   */
  public final <EC extends EntityMap> EC findPage(int pageNo, int pageSize, Object...args) {
    Collection<E> results = null;
    
    if (((results = this.mEntity.clearFilters().findPage(pageNo,pageSize,args)) != null) && (results.size() > 0)) {
      try {
        Field idField = this.getIdField();
        Object idVal = null;
        for (E entity : results) {
          idField.setAccessible(true);
          idVal = idField.get(entity);
          this.put((O) idVal, entity);
        }
      } 
      catch (Exception e) {
        throw new IllegalStateException(
          String.format("%s.findPage(pageNo,pageSize,Object[]) %s:\n%s"
            ,this.getClass().getName()
            ,e.getClass().getName()
            ,e.getMessage()
          )
        );
      }
    }
    return (EC) this;
  }
  
  /**
   * 
   * @return 
   */
  public JSONObject toJson(){
    JSONObject result = new JSONObject();
    
    if (this.size() > 0) {
      for (Entry<O,E> entry : this.entrySet()) {
        result.put(entry.getKey().toString(), entry.getValue().toJson());
      }
    }
    
    return result;
  }
  
  /**
   * 
   * @param <EC>
   * @param fieldname
   * @param sortDir
   * @return 
   */
  public <EC extends EntityMap> EC sort(final String fieldname, final String sortDir) {
    
    if (!this.isEmpty()) {
      List<E> collection = new ArrayList<>();
      collection.addAll(this.values());
      Collections.sort(collection, new Comparator<E>() {

        @Override
        public int compare(E o1, E o2) {
          Integer result = null;
          try {
            Field f1 = null;
            Field f2 = null;
            if ((f1 = EntityUtil.getEntityField(o1.getClass(), fieldname)) == null) {
              throw new NullPointerException(
                String.format(
                  "%s.%s Error(s):%s\n"
                  ,o1.getClass().getName()
                  ,"sort(fieldname,sortDir)"
                  ,"The entity class does not contain property '" + fieldname + "'!"
                )
              );
            }
            if ((f2 = EntityUtil.getEntityField(o2.getClass(), fieldname)) == null) {
              throw new NullPointerException(
                String.format(
                  "%s.%s Error(s):%s\n"
                  ,o2.getClass().getName()
                  ,"sort(fieldname,sortDir)"
                  ,"The entity class does not contain property '" + fieldname + "'!"
                )
              );
            }
            f1.setAccessible(true);
            f2.setAccessible(true);
            Object val1 = f1.get(o1);
            Object val2 = f2.get(o2);
            
            if ((val1 != null) && (val2 == null)) {
              result = 1;
            }
            else if ((val1 == null) && (val2 != null)) {
              result = -1;
            }
            else if ((val1 == null) && (val2 == null)) {
              result = 0;
            }
            else if (f1.getType().equals(Integer.class)) {
              result = ((Integer)f1.get(o1)).compareTo((Integer)f1.get(o2));
              return result;
            }
            else if (f1.getType().equals(Double.class)) {
              result = ((Double)f1.get(o1)).compareTo((Double)f1.get(o2));
            }
            else if (f1.getType().equals(String.class)) {
              result = ((String)f1.get(o1)).compareTo((String)f1.get(o2));
            }
            else if (f1.getType().equals(Float.class)) {
              result = ((Float)f1.get(o1)).compareTo((Float)f1.get(o2));
            }
            else if (f1.getType().equals(Long.class)) {
              result = ((Long)f1.get(o1)).compareTo((Long)f1.get(o2));
            }
            else if (f1.getType().equals(Byte.class)) {
              result = ((Byte)f1.get(o1)).compareTo((Byte)f1.get(o2));
            }
            else if (f1.getType().equals(java.util.Date.class)) {
              result = ((java.util.Date)f1.get(o1)).compareTo((java.util.Date)f1.get(o2));
            }
            else if (f1.getType().equals(java.sql.Date.class)) {
              result = ((java.sql.Date)f1.get(o1)).compareTo((java.sql.Date)f1.get(o2));
            }
            else if (f1.getType().equals(java.sql.Time.class)) {
              result = ((java.sql.Time)f1.get(o1)).compareTo((java.sql.Time)f1.get(o2));
            }
            else if (f1.getType().equals(java.sql.Timestamp.class)) {
              result = ((java.sql.Timestamp)f1.get(o1)).compareTo((java.sql.Timestamp)f1.get(o2));
            }
            else {
              throw new Exception("The compare " + f1.getType().toString() + " type is not supported!");
            }
          }
          catch(Exception ex) {
            throw new IllegalStateException(ex.getMessage());
          }
          
          if (sortDir.equalsIgnoreCase("desc")) {
            result = result * -1;
          }
          
          return result;
        }
      });
    }
    
    return (EC) this;
  }
  
  /**
   * 
   * @return 
   */
  protected final Map<O,E> getCollection(){
    return this.mCollection;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Protected Run Query">
  /**
   * Dummy override-able method
   * @param <NBC>
   * @param node
   * @return 
   */
  protected <NBC extends EntityMap> NBC beforeAdd(E node) {
    return (NBC) this;
  }
  
  protected <NBC extends EntityMap> NBC runQuery(String query){
    return this.runQuery(query, null);
  }
  
  protected <NBC extends EntityMap> NBC runQuery(String query, List params){
    return this.runQuery(query, params, new QueryDelegate(this) {
            @Override
            public void handle(ResultSet rs) throws Exception {
              NBC collection = this.getListener();
              E entity = null;
              Field f = collection.getIdField();
              Object idVal = null;
              while (rs.next()) {
                entity = (E) collection.getEntityClass().newInstance();
                entity.load(rs);
                collection.beforeAdd(entity);
                f.setAccessible(true);
                idVal = f.get(entity);
                collection.put(idVal,entity);
              }
            }
          });
  }
  
  protected <NBC extends EntityMap> NBC runQuery(String query, List params, QueryDelegate delegate){
    try {
      Connection conn = null;
      if ((conn = ConnectionContext.fetchConnection(this.mEntity.getClass())) == null) {
        throw new NullPointerException("Cannot create a data connection object using data source '" + this.mEntity.getClass().getName() + "'!");
      }
              
      try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        if ((params != null) && (!params.isEmpty())) {
          int i=1;
          Integer sqlType = null;
          for (Object o : params){
            if (o == null){
              try {
                sqlType = pstmt.getParameterMetaData().getParameterType(i);
              } catch (Exception e) {
                sqlType = Types.VARCHAR;
              }
              pstmt.setNull(i++, sqlType);
            }
            else {
              pstmt.setObject(i++, o);
            }
          }
        }
//        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Run Query:\n" + query);
        try(ResultSet rs = pstmt.executeQuery()) {
          delegate.handle(rs);
        }
      }
    }
    catch(Exception ex){
      throw new IllegalStateException(
        String.format("%s.%s %s:\n%s"
          ,this.getClass().getName()
          ,"runQuery(String,List)"
          ,ex.getClass().getName()
          ,ex.getMessage()
        )
      );
    }
    return (NBC) this;
  }
  //</editor-fold>
}
