package entity.core;

import com.opencsv.CSVWriter;
import entity.core.annotation.EntityColumn;
import entity.core.delegate.JsonDelegate;
import entity.core.delegate.QueryDelegate;
import entity.core.interfaces.EntityPredicate;
import entity.core.jdbc.ConnectionContext;
import entity.core.util.EntityUtil;
import entity.core.util.ExceptionUtil;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class EntityCollection<E extends EntityBase> implements List<E>/**/ {
  
  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  private List<E> mCollection;
  private E mEntity;
  
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    
    this.mCollection = null;
    this.mEntity = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructor">
  public EntityCollection(Class<E> eClass){
    this.mCollection = new ArrayList<E>();
    try {
      this.mEntity = eClass.newInstance();
    } catch (Exception ex) {
      throw new IllegalStateException(
              String.format("%s.%s Error(s):\n%s"
                      ,this.getClass().getName()
                      ,"EntityCollectionBase(eClass)"
                      ,ex.getMessage()
              )
      );
    }
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
  
  protected final String buildQuery(List<Object> params, Object... args){
    return this.mEntity.buildQuery(params, args);
  }
  /**
   * 
   * @param <EC>
   * @param args
   * @return 
   */
  public final <EC extends EntityCollection> EC findAll(Object...args) {
    Collection<E> results = null;
    if (((results = this.mEntity.clearFilters().findAll(args)) != null) && (results.size() > 0)) {
      this.addAll(results);
    }
    
    return (EC) this;
  }
  
  /**
   * 
   * @param <EC>
   * @param pageNo
   * @param pageSize
   * @param args
   * @return 
   */
  public final <EC extends EntityCollection> EC findPage(int pageNo, int pageSize, Object...args) {
    Collection<E> results = null;
    
    if (((results = this.mEntity.clearFilters().findPage(pageNo,pageSize,args)) != null) && (results.size() > 0)) {
      this.addAll(results);
    }
    return (EC) this;
  }
  
  /**
   * 
   * @return 
   */
  public JSONArray toJson(){
    return this.toJson(null);
  }
  
  /**
   * 
   * @param del
   * @return 
   */
  public JSONArray toJson(JsonDelegate del){
    JSONArray result = new JSONArray();
    
    if (this.size() > 0) {
      JSONObject json = null;
      for (E entity : this.getCollection()) {
        json = entity.toJson(del);
        result.put(json);
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
  public <EC extends EntityCollection> EC sort(final String fieldname, final String sortDir) {
    
    if (!this.isEmpty()) {
      Collections.sort(this, new Comparator<E>() {

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
                  ,o1.getClass().getName() + "Collection"
                  ,"sort(fieldname,sortDir)"
                  ,"The entity class does not contain property '" + fieldname + "'!"
                )
              );
            }
            if ((f2 = EntityUtil.getEntityField(o2.getClass(), fieldname)) == null) {
              throw new NullPointerException(
                String.format(
                  "%s.%s Error(s):%s\n"
                  ,o2.getClass().getName() + "Collection"
                  ,"sort(fieldname,sortDir)"
                  ,"The entity class does not contain property '" + fieldname + "'!"
                )
              );
            }
            f1.setAccessible(true);
            f2.setAccessible(true);
            if (f1.getType().equals(Integer.class)) {
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
  protected final List<E> getCollection(){
    return this.mCollection;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="List Implementations">
  @Override
  public int size() {
    return this.mCollection.size();
  }

  @Override
  public boolean isEmpty() {
    return this.mCollection.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return this.mCollection.contains(o);
  }

  @Override
  public Iterator<E> iterator() {
    return this.mCollection.iterator();
  }

  @Override
  public Object[] toArray() {
    return this.mCollection.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return this.mCollection.toArray(a);
  }

  @Override
  public boolean add(E e) {
    return this.mCollection.add(e);
  }

  @Override
  public boolean remove(Object o) {
    return this.mCollection.remove(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return this.mCollection.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    return this.mCollection.addAll(c);
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    return this.mCollection.addAll(index, c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return this.mCollection.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return this.mCollection.retainAll(c);
  }

  @Override
  public void clear() {
    this.mCollection.clear();
  }

  @Override
  public E get(int index) {
    return this.mCollection.get(index);
  }

  @Override
  public E set(int index, E element) {
    return this.mCollection.set(index, element);
  }

  @Override
  public void add(int index, E element) {
    this.mCollection.add(index, element);
  }

  @Override
  public E remove(int index) {
    return this.mCollection.remove(index);
  }

  @Override
  public int indexOf(Object o) {
    return this.mCollection.indexOf(o);
  }

  @Override
  public int lastIndexOf(Object o) {
    return this.mCollection.lastIndexOf(o);
  }

  @Override
  public ListIterator<E> listIterator() {
    return this.mCollection.listIterator();
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    return this.mCollection.listIterator(index);
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    return this.mCollection.subList(fromIndex, toIndex);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Protected Run Query">
  /**
   * Dummy override-able method
   * @param <NBC>
   * @param node
   * @return 
   */
  protected <NBC extends EntityCollection> NBC beforeAdd(E node) {
    return (NBC) this;
  }
  
  protected <NBC extends EntityCollection> NBC runQuery(String query){
    return this.runQuery(query, null);
  }
  
  protected <NBC extends EntityCollection> NBC runQuery(String query, List params){
    return this.runQuery(query, params, new QueryDelegate(this) {
            @Override
            public void handle(ResultSet rs) throws Exception {
              NBC collection = this.getListener();
              E node = null;
              while (rs.next()) {
                node = (E) collection.getEntityClass().newInstance();
                node.load(rs);
                collection.beforeAdd(node);
                collection.add(node);
              }
            }
          });
  }
  
  protected <NBC extends EntityCollection> NBC runQuery(String query, List params, QueryDelegate delegate){
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
        try(ResultSet rs = pstmt.executeQuery()) {
          delegate.handle(rs);
        }
      }
    }
    catch(Exception ex){
      java.util.logging.Logger.getLogger(this.getClass().getName()).log(java.util.logging.Level.INFO, "Run Query:\n" + query);
      ExceptionUtil.throwIllegalStateException(
        String.format("%s.%s %s:\n%s"
          ,this.getClass().getName()
          ,"runQuery(String,List)"
          ,ex.getClass().getName()
          ,ex.getMessage()
        )
        ,ex
      );
    }
    return (NBC) this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Node Class">
  protected Class<E> getEntityClass(){
    return (Class<E>) this.mEntity.getClass();
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Filter Collection">
  public final <EC extends EntityCollection, EP extends EntityPredicate> EC filter(EP predicate) {
    if (!this.isEmpty() && (predicate != null)) {
      Iterator<E> it = this.iterator();
      E entity = null;
      while (it.hasNext()) {
        entity = it.next();
        if (!predicate.evaluate(entity)) {
          it.remove();
        }
      }
    }
    return (EC) this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Overridable On Get Instance">
  protected <EC extends EntityCollection<E>> EC onGetInstance() {
    return (EC) this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Update">
  public <EC extends EntityCollection<E>> EC update(E entity) {
    Integer idx = null;
    if ((idx = this.indexOf(entity)) > -1) {
      this.set(idx, entity);
    }
    return (EC) this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Find">
  public E find(EntityPredicate<E> predicate) {
    E result = null;
    for (E entity : this) {
      if (predicate.evaluate(entity)){
        result = entity;
        break;
      };
    }
  
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Find">
  public Boolean contains(EntityPredicate<E> predicate) {
    Boolean result = false;
    for (E entity : this) {
      if ((result = predicate.evaluate(entity))){
        break;
      };
    }
    
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Write to CSV">
  public <EC extends EntityCollection<E>> EC writeToCsv(OutputStream ostream) {
    try {
      CSVWriter csvwriter = new CSVWriter(new OutputStreamWriter(ostream));
      Collection<EntityColumn> columns = EntityUtil.getEntityColumns(this.mEntity.getClass());
      String[] header = new String[columns.size()];
      String[] row = new String[columns.size()];
      Iterator<EntityColumn> iter = columns.iterator();
      int i = 0;
      while (iter.hasNext()){
        EntityColumn column = iter.next();
        header[i] = column.name();
        i++;
      }
      csvwriter.writeNext(header);
      for (E entity : this){
        for (int r=0; r<row.length; r++){
          row[r] = "";
          if (entity.get(header[r]) != null){
            row[r] = entity.get(header[r]).toString();
          }
        }
        csvwriter.writeNext(row);
      }
      csvwriter.flush();
    } catch (Exception e) {
      e.printStackTrace(System.err);
      IllegalStateException ex = new IllegalStateException(e.getMessage());
      ex.setStackTrace(e.getStackTrace());
      throw ex;
    }
    return (EC) this;
  }
  //</editor-fold>
}