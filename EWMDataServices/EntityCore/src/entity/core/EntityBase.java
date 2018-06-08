package entity.core;

import entity.core.annotation.*;
import entity.core.delegate.JsonDelegate;
import entity.core.delegate.LoadPropertyDelegate;
import entity.core.delegate.QueryDelegate;
import entity.core.delegate.UpdateDelegate;
import entity.core.filter.*;
import entity.core.filter.ConditionFilter.ConditionOperator;
import entity.core.jdbc.ConnectionContext;
import entity.core.util.*;
import java.lang.reflect.*;
import java.sql.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

/**
 *
 * @author Charlie Lay
 * @param <E>
 */
public class EntityBase<E extends EntityBase> implements Cloneable {

  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  private QueryDelegate mSelectQueryDelegate;
  private QueryDelegate mInsertQueryDelegate;
  private QueryDelegate mFindQueryDelegate;
  private QueryDelegate mCountQueryDelegate;
  private UpdateDelegate mUpdateQueryDelegate;
  private List<String> mDirtyFields;
  private List<ConditionGroup> mConditions;
  private List<SortFilter> mSorts;
  private Class<E> mEntityClass;
  private Logger mLogger;
  private Boolean mLoaded;
  private Boolean mQuerySuccess;
  private Boolean mIsUnique;
  
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mSelectQueryDelegate=null;
    this.mInsertQueryDelegate=null;
    this.mUpdateQueryDelegate=null;
    this.mFindQueryDelegate=null;
    this.mCountQueryDelegate=null;
    this.mDirtyFields=null;
    this.mConditions=null;
    this.mSorts=null;
    this.mEntityClass=null;
    this.mLogger=null;
    this.mLoaded=null;
    this.mQuerySuccess=null;
    this.mIsUnique=null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Override Methods">
  @Override
  public String toString() {
    return this.toMap().toString(); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public int hashCode() {
    int result = -1;

    try {
      List<Field> fields = EntityUtil.getPrimaryColumnFields(this.getEntityClass());

      if (fields != null) {
        Object val = null;

        for (Field f : fields) {
          val = f.get(this);
          if (result == -1) {
            result = Objects.hashCode(val);
          }
          else {
            result += 31 * Objects.hashCode(val);
          }
        }
      }

      if (result == -1) {
        result = super.hashCode();
      }
    }
    catch(Exception ex) {
      ExceptionUtil.throwIllegalStateException(ex);
    }

    return result;
  }


  @Override
  public boolean equals(Object obj) {
    E other = (E) obj;
    boolean result = false;
    if ((result = this.getClass().equals(other.getClass()))) {
      Collection<Field> fields = null;
      List<Field> idfields = null;
      if (/*(this.isLoaded()) && (other.isLoaded())
              && /**/((idfields = EntityUtil.getIdFields(this.mEntityClass)) != null)
              && (!idfields.isEmpty())) {
        fields = idfields;
      }
      else {
        fields = EntityUtil.getEntityFields(this.mEntityClass);
      }
      if ((fields == null) || (fields.isEmpty())) {
        throw new IllegalStateException(
          String.format("%s.%s Error(s):\nThe entity has no properties to compare with!"
            ,this.getClass().getName()
            ,"equals(obj)"
          )
        );
      }
      Object thisVal,otherVal;
      for (Field f : fields) {
        f.setAccessible(true);
        try {
          thisVal = f.get(this);
          otherVal = f.get(other);

          if ((thisVal == null) && (otherVal == null)) {
            continue;
          }

          if (((thisVal == null) && (otherVal != null))
              || ((thisVal != null) && (otherVal == null))){
            result = false;
            break;
          }

          if (!(result = thisVal.equals(otherVal))) {
            break;
          }
        } catch (Exception ex) {
          throw new IllegalStateException(
            String.format("%s.%s Error(s):\n%s"
              ,this.getClass().getName()
              ,"equals(obj)"
              ,ex.getMessage()
            )
          );
        }
      }
    }
    return result;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   *
   * @param entityClass
   */
  public EntityBase() {
    this.mEntityClass = (Class<E>) this.getClass();
    this.mDirtyFields = new ArrayList<>();
    this.mConditions = new ArrayList<>();
    this.mSorts = new ArrayList<>();
    this.mLogger = Logger.getLogger(this.mEntityClass.getName());
    this.initDelegates();
    EntityUtil.initEntityClass(this.mEntityClass);
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Private/Protected Methods">
  /**
   *
   * @return
   */
  private final E initDelegates(){
    this.mSelectQueryDelegate = new QueryDelegate(this) {

      @Override
      public void handle(ResultSet rs) throws Exception {
        if (rs.next()) {
          ReflectionUtil.invoke(this.getListener(), "load", rs);
          if (rs.next()) {
            ReflectionUtil.invoke(this.getListener(), "clear");
            throw new IllegalStateException(
              String.format("%s.%s Error(s):\nThe result set returns more than one row!"
                ,this.getListener().getClass().getName()
                ,"select(columns)"
              )
            );
          }
        }
      }
    };
    this.mUpdateQueryDelegate = new UpdateDelegate(this) {

      @Override
      public void handle(Integer i) throws Exception {
        if (i != 1) {
          throw new IllegalStateException("The query affected zero or more than 1 records!");
        }
      }
    };
    this.mInsertQueryDelegate = new QueryDelegate(this) {

      @Override
      public void handle(ResultSet rs) throws Exception {
        if (!rs.next()) {
          throw new Exception("The auto generate key result set is empty!");
        }
        E entity = this.getListener();
        List<Field> fields = null;
        if (((fields = EntityUtil.getIdFields(entity.getClass())) != null)
            && (fields.size() > 0)){
          EntityColumn coldef = null;
          Field f = null;
          for (int i=0;i<fields.size();++i) {
            f = fields.get(i);
            if (((coldef = EntityUtil.getEntityColumn(entity.getClass(), f.getName())) != null)
                && (coldef.autoId())){
              Object o = null;
              if (f.getType().equals(int.class) || f.getType().equals(Integer.class)) {
                o = rs.getInt(i+1);
              }
              else if (f.getType().equals(long.class) || f.getType().equals(Long.class)) {
                o = rs.getLong(i+1);
              }

              if (rs.wasNull()) {
                throw new Exception("The auto id column '" + coldef.name() + "' was null!");
              }

              f.setAccessible(true);
              f.set(entity, o);
            }
          }
        }
      }
    };

    this.mFindQueryDelegate = new QueryDelegate(this) {
      private Collection<E> mEntities;

      @Override
      public void handle(ResultSet rs) throws Exception {
        this.mEntities = new ArrayList<>();
        E entity = this.getListener();
        E newEntity = null;
        while (rs.next()) {
          newEntity = (E) entity.createInstance();
          ReflectionUtil.invoke(newEntity, "load", rs);
          this.mEntities.add(newEntity);
        }
      }

      public Collection<E> getResult() {
        return this.mEntities;
      }
    };

    this.mCountQueryDelegate = new QueryDelegate(this) {
      private Integer mTotalRecords;
      @Override
      public void handle(ResultSet rs) throws Exception {
        if (rs.next()) {
          this.mTotalRecords = rs.getInt("TOTAL_RECORDS");
        }
      }
      public Integer getCount(){
        return this.mTotalRecords;
      }
    };
    return (E)this;
  }

  /**
   *
   * @param rs
   * @return
   * @throws Exception
   */
  protected E load(ResultSet rs) throws Exception {
    return this.load(rs,null);
  }
  
  /**
   *
   * @param rs
   * @param colPrefix
   * @return
   * @throws Exception
   */
  protected E load(ResultSet rs, String colPrefix) throws Exception {
    if (rs == null) {
      throw new NullPointerException(
        String.format("%s.%s Error(s):\nThe %s parameter cannot be unassigned!"
          ,this.getClass().getName()
          ,"load(rs)"
          ,"rs"
        )
      );
    }

    Collection<Field> fields = null;
    if ((fields = EntityUtil.getEntityFields(this.mEntityClass))!=null) {
      EntityColumn annot = null;
      ResultSetMetaData rsmd = rs.getMetaData();
      String columnName = null;
      if (StringUtils.isEmpty(colPrefix)) {
        colPrefix = "";
      }
      for (int i=0;i<rsmd.getColumnCount();++i) {
        columnName = rsmd.getColumnName(i+1);
        for (Field f : fields) {
          if (((annot = EntityUtil.getEntityColumn(this.mEntityClass, f.getName())) != null)
            && (DataUtil.isEqual(colPrefix + annot.name(), columnName))){
            Class<?> ftype = f.getType();
            Object o = null;

            if (((o = rs.getObject(colPrefix + annot.name(),ftype)) != null) && (rs.wasNull())) {
              o = null;
            }

            f.setAccessible(true);
            f.set(this, o);
            break;
          }
        }
      }
      this.mLoaded = true;
    }

    return (E) this;
  }

  /**
   *
   * @return
   */
  protected E clearProperties(){
    Collection<Field> fields = null;
    if ((fields = EntityUtil.getEntityFields(this.getClass())) != null) {
      for (Field f : fields) {
        try {
          f.setAccessible(true);
          f.set(this, null);
        }
        catch(Exception ex){
          this.mLogger.log(Level.WARNING
            ,"{0}.clear Error:\n{1}"
            ,new Object[]{this.getClass().getName(),ex.getMessage()});
        }
      }
    }
    this.mLoaded = false;
    return (E)this;
  }

  /**
   *
   * @return
   */
  protected E clearDirty(){
    this.mDirtyFields.clear();
    return (E)this;
  }

  /**
   *
   * @return
   */
  protected E clearFilters(){
    this.mConditions.clear();
    this.mSorts.clear();
    return (E)this;
  }

  /**
   *
   * @return
   */
  protected E clearFlags(){
//    this.mLoaded=null;
    this.mQuerySuccess=null;
    this.mIsUnique=null;
    return (E)this;
  }

  /**
   *
   * @param fieldname
   * @param value
   * @return
   */
  protected final E set(String fieldname,Object value) {
    try {
      Field f = null;
      if ((f = EntityUtil.getEntityField(this.mEntityClass, fieldname)) != null) {
        f.setAccessible(true);
//        if (!this.isLoaded() || !Objects.equals(f.get(this), value)) {
          f.set(this, value);
          if (!this.mDirtyFields.contains(fieldname)) {
            this.mDirtyFields.add(fieldname);
          }
//        }
      }
    } catch (Exception ex) {
      throw new IllegalStateException(
        String.format("%s.%s Error(s):\n%s"
          ,this.getClass().getName()
          ,"set(fieldname,value)"
          ,ex.getMessage()
        )
      );
    }
    return (E)this;
  }

  /**
   *
   * @param <O>
   * @param fieldname
   * @return
   */
  protected <O extends Object> O get(String fieldname) {
    O result = null;

    Field f = null;
    if ((f = EntityUtil.getEntityField(this.mEntityClass, fieldname)) != null) {
      f.setAccessible(true);
      try {
        result = (O) f.get(this);
      } catch (Exception ex) {
        this.mLogger.log(Level.WARNING
          ,String.format("%s.%s Error(s):\n%s"
            ,this.getClass().getName()
            ,"get(fieldname)"
            ,ex.getMessage()
          )
        );
      }
    }

    return result;
  }

  /**
   *
   * @param columns
   * @param params
   * @return
   */
  protected final String buildSelectColumns(ColumnFilter[] columns,List<Object> params){
    String result = null;

    if ((columns ==  null) || (columns.length == 0)) {
      result = "*";
    }
    else {
      result = "";
      String comma = "";
      for (ColumnFilter cf : columns) {
        result += comma + cf.toString(this.mEntityClass);
        comma = ",";
        if (cf.getValues() != null) {
          params.addAll(cf.getValues());
        }
      }
    }

    return result;
  }

  /**
   *
   * @return
   */
  protected final String buildConditions(List<ConditionGroup> conditions,List<Object> params) {
    String result = null;

    if ((conditions == null) || (conditions.isEmpty())) {
      result = "";
    }
    else {
      String andor = "";
      result = "";
      for (ConditionGroup cfg : conditions) {
        result += andor + cfg.toString(this.mEntityClass);
        andor = cfg.getType().toString();
        if (cfg.getValues() != null) {
          params.addAll(cfg.getValues());
        }
      }
    }

    if (!StringUtils.isEmpty(result)) {
      result = "\nWHERE " + result;
    }

    return result;
  }

  /**
   *
   * @param filters
   * @param params
   * @return
   */
  protected final String buildSorts(List<SortFilter> filters, List<Object> params) {
    String result = null;

    if ((filters == null) || (filters.isEmpty())) {
      result = "";
    }
    else {
      String comma = "";
      result = "";
      String sort = null;
      for (SortFilter sf : filters) {
        if (StringUtils.isEmpty(sort = sf.toString(this.mEntityClass))) {
          continue;
        }
        result += comma + sort;
        comma = ",";
        if (sf.getValues() != null) {
          params.addAll(sf.getValues());
        }
      }
      result = "ORDER BY "+result.replace("ORDER BY", "");
    }

    return result;
  }

  /**
   *
   * @param fieldnames
   * @param params
   * @return
   */
  protected final String buildUpdateSet(List<String> fieldnames, List<Object> params) {
    if ((fieldnames == null) || (fieldnames.isEmpty()) || (params == null)) {
      throw new NullPointerException(
        String.format("%s.%s Error(s):\nThe %s parameters cannot be unassigned!"
          ,this.getClass().getName()
          ,"buildUpdateSet(fieldnames,params)"
          ,"fieldnames and params"
        )
      );
    }

    String result = null;
    String sets = "";
    String comma = "";
    EntityColumn colDef = null;
    Field f = null;
    Object val = null;
    for (String fieldname : fieldnames) {
      try {
        if (((f = EntityUtil.getEntityField(this.mEntityClass, fieldname)) != null)
            && ((colDef = EntityUtil.getEntityColumn(this.mEntityClass, f.getName())) != null)
            && (!StringUtils.isEmpty(colDef.name()))) {
          f.setAccessible(true);
          if ((val = f.get(this))==null) {
            sets += comma + "\"" + colDef.name() + "\" = null";
          }
          else {
            sets += comma + "\"" + colDef.name() + "\" = ?";
            params.add(val);
          }
          comma = ",";
        }
      }
      catch(Exception ex){
        throw new IllegalStateException(
          String.format("%s.%s Error(s):\n%s"
            ,this.getClass().getName()
            ,"buildUpdateSet(fields,params)"
            ,ex.getMessage()
          )
        );
      }
    }

    result = StringUtils.isEmpty(sets) ? result : sets;

    return result;
  }

  /**
   *
   * @param fieldnames
   * @param params
   * @return
   */
  protected final String buildInserts(List<String> fieldnames, List<Object> params) {
    if ((fieldnames == null) || (fieldnames.isEmpty()) || (params == null)) {
      throw new NullPointerException(
        String.format("%s.%s Error(s):\nThe %s parameters cannot be unassigned!"
          ,this.getClass().getName()
          ,"buildUpdateSet(fieldnames,params)"
          ,"fieldnames and params"
        )
      );
    }

    String result = null;
    String columns = "";
    String values = "";
    String comma = "";
    EntityColumn colDef = null;
    Field f = null;
    Object val = null;
    for (String fieldname : fieldnames) {
      try {
        if (((f = EntityUtil.getEntityField(this.mEntityClass, fieldname)) != null)
            && ((colDef = EntityUtil.getEntityColumn(this.mEntityClass, f.getName())) != null)
            && (!StringUtils.isEmpty(colDef.name()))) {
          f.setAccessible(true);
          columns += comma + "\"" + colDef.name() + "\"";
          if ((val = f.get(this))==null) {
            values += comma + "null";
          }
          else {
            values += comma + "?";
            params.add(val);
          }
          comma = ",";
        }
      }
      catch(Exception ex){
        throw new IllegalStateException(
          String.format("%s.%s Error(s):\n%s"
            ,this.getClass().getName()
            ,"buildInserts(fields,params)"
            ,ex.getMessage()
          )
        );
      }
    }

    if (StringUtils.isEmpty(columns)) {
      throw new NullPointerException(
        String.format("%s.%s Error(s):\nThe insert columns cannot be empty!"
          ,this.getClass().getName()
          ,"buildUpdateSet(fieldnames,params)"
        )
      );
    }

    result = String.format("(%s) VALUES (%s)",columns,values);

    return result;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Crud Methods">
  private E findQuery(String query,List<Object> params) throws Exception {
    return this.findQuery(query,params,false);
  }
  /**
   *
   * @param query
   * @param params
   * @return
   * @throws Exception
   */
  private E findQuery(String query,List<Object> params,boolean isCount) throws Exception {
    Connection conn = null;
    if ((conn = ConnectionContext.fetchConnection(this.mEntityClass))==null) {
      throw new NullPointerException("The system cannot retrieve a connection!");
    }
    this.clearFlags();
    if (isCount) {
      EntityFacade.executeQuery(conn, query, params, this.mCountQueryDelegate);
    }
    else {
      EntityFacade.executeQuery(conn, query, params, this.mFindQueryDelegate);
    }
    this.mQuerySuccess = true;
    this.clearDirty();
    this.clearFilters();
    return (E)this;
  }

  /**
   *
   * @param query
   * @param params
   * @return
   * @throws Exception
   */
  private E runQuery(String query,List<Object> params) throws Exception {
    return this.runQuery(query, params, false);
  }

  /**
   *
   * @param query
   * @param params
   * @param isUpdate
   * @return
   * @throws Exception
   */
  private E runQuery(String query,List<Object> params,boolean isUpdate) throws Exception {
    if (StringUtils.isEmpty(query)) {
      throw new NullPointerException(
        String.format("%s.%s Error(s):\nThe %s parameter cannot be unassigned!"
          ,this.getClass().getName()
          ,"runQuery(query,params,isUpdate)"
          ,"query")
      );
    }
    Connection conn = null;
    if ((conn = ConnectionContext.fetchConnection(this.mEntityClass))==null) {
      throw new NullPointerException("The system cannot retrieve a connection!");
    }
    this.clearFlags();
    if (isUpdate){
      List<String> indexes = null;
      if (query.matches("^INSERT.*")) {
        indexes = new ArrayList<>();
        List<Field> fields = null;
        if (((fields = EntityUtil.getIdFields(this.mEntityClass)) != null) && (!fields.isEmpty())) {
          EntityColumn coldef = null;
          for (Field f : fields) {
            if (((coldef = EntityUtil.getEntityColumn(this.mEntityClass, f.getName())) != null)
              && (coldef.autoId())) {
              indexes.add(coldef.name());
            }
          }
        }
      }

      if ((indexes == null) || (indexes.isEmpty())) {
        EntityFacade.executeUpdate(conn, query, params, this.mUpdateQueryDelegate);
      }
      else {
        EntityFacade.executeUpdate(conn, query, params, this.mUpdateQueryDelegate
                , this.mInsertQueryDelegate, indexes.toArray(new String[0]));
      }
    }
    else {
      EntityFacade.executeQuery(conn, query, params, this.mSelectQueryDelegate);
    }
    this.mQuerySuccess = true;
    this.clearDirty();
    this.clearFilters();
    return (E)this;
  }

  /**
   *
   * @param fieldname
   * @param force
   * @return
   */
  protected E loadJoin(String fieldname, boolean force) {
    EntityJoin joinDef = null;
    Map<String,EntityJoin> entries = null;
    if ((fieldname == null)
      || ((entries = EntityUtil.getEntityJoins(this.mEntityClass)) == null)
      || ((joinDef = entries.get(fieldname)) == null)) {
      throw new NullPointerException("The property '" + fieldname + "' is not valid!");
    }
    return this.loadJoin(fieldname, joinDef, force);
  }

  /**
   *
   * @param entry
   * @param force
   * @return
   */
  protected E loadJoin(String fieldname, EntityJoin joinDef, boolean force) {
    try {
      if ((StringUtils.isEmpty(fieldname)) || (joinDef == null)) {
        throw new NullPointerException("The fieldname and joindef parameters cannot be unassigned!");
      }
      Field field = null;
      EntityTable linkTable = null,joinTable=null,entityTable = EntityUtil.getEntityTable(this.mEntityClass);
      EntityColumn[] joinCols;
      Class fieldClass = null,joinEntityClass=null;
      Type type = null;
      boolean isCollectionField = false,isLinkTableJoin=false;
      Field thisEntityField = null;
      String query=null,joins=null,thisColName = null;
      List<String> conditions = null;
      List<String> joinConditions = null;
      List<String> joinConditions2 = null;
      List<Object> params = null;
      List<String> sorts = null;

      if ((field = ReflectionUtil.getField(this.mEntityClass, fieldname)) == null) {
        throw new NullPointerException("The property '" + fieldname + "' is not valid!");
      }
      else {
        field.setAccessible(true);
        if (!force) {
          if ((field.get(this) != null) || (joinDef.lazy())) {
            return (E)this;// skip if it has value(s) or lazy() is true
          }
        }
      }

      if (((joinCols = joinDef.column()) == null) || (joinCols.length == 0)) {
        throw new Exception("The join definition is missing the join columns!");
      }

      fieldClass = field.getType();

      if ((!Collection.class.isAssignableFrom(fieldClass)) && (!EntityBase.class.isAssignableFrom(fieldClass))) {
        throw new Exception(
          String.format("The field type '%s' is not supported!"
            ,fieldClass.getName()
          )
        );
      }

      if (Collection.class.isAssignableFrom(fieldClass)) {
        isCollectionField = true;
        if ((type = field.getGenericType()) == null) {
          throw new Exception("The entity join field must be a collection of entities!");
        }
        else if ((!(type instanceof ParameterizedType))){// && (EntityCollectionBase.class.isAssignableFrom((Class<?>) type))) {
          type = ((Class)type).getGenericSuperclass();
        }

        if (!(type instanceof ParameterizedType)) {
          throw new Exception("The entity join field must be a collection of entities!");
        }

        joinEntityClass = (Class) ((ParameterizedType)type).getActualTypeArguments()[0];
      }
      else if (EntityBase.class.isAssignableFrom(fieldClass)){
        joinEntityClass = fieldClass;
      }
      if (((joinTable = EntityUtil.getEntityTable(joinEntityClass))== null)
          || (StringUtils.isEmpty(joinTable.name()))){
        throw new Exception("The system cannot retrieve the entity table from the joined entity!");
      }
      params = new ArrayList<>();
      conditions=new ArrayList<>();
      joinConditions=new ArrayList<>();
      joinConditions2=new ArrayList<>();
      query = String.format("SELECT %1$s %2$s.* FROM %2$s"
              ,joinDef.unique() ? "DISTINCT" : ""
              ,joinTable.name());
      if (((linkTable = joinDef.table()) != null) && (!StringUtils.isEmpty(linkTable.name()))) {
        joins = String.format("\nJOIN %1$s\nJOIN %2$s",linkTable.name(),entityTable.name());
        isLinkTableJoin = true;
        // join linktable
        //  join entitytable
        //  on linktable.reference = entitytable.reference
        // on jointable.colname = linktable.colname
      }
      else {
        joins = String.format("\nJOIN %s",entityTable.name());
      }
      thisColName = null;
      for (EntityColumn joinCol : joinCols) {
        if (((thisEntityField = EntityUtil.getEntityField(this.mEntityClass, joinCol.name())) == null)
              && ((thisEntityField = EntityUtil.getEntityField(this.mEntityClass, joinCol.reference())) == null)) {
//          throw new Exception("The entity join column definition is not valid!");
          continue;
        }
        else {
          if (EntityUtil.getEntityField(this.mEntityClass, joinCol.name()) != null) {
            thisColName = joinCol.name();
          }
          else if (EntityUtil.getEntityField(this.mEntityClass, joinCol.reference()) != null) {
            thisColName = joinCol.reference();
          }
          if (!StringUtils.isEmpty(thisColName)) {

            conditions.add(String.format("%s.%s = ?"
                                        ,entityTable.name()
                                        ,thisColName));

            thisEntityField.setAccessible(true);
            params.add(thisEntityField.get(this));
          }
        }
        if (isLinkTableJoin) {
          joinConditions.add(String.format("%s.%s = %s.%s"
                                        ,linkTable.name()
                                        ,joinCol.name()
                                        ,joinTable.name()
                                        ,joinCol.name()));
          joinConditions2.add(String.format("%s.%s = %s.%s"
                                        ,linkTable.name()
                                        ,joinCol.name()
                                        ,entityTable.name()
                                        ,thisColName));
        }
        else {
          joinConditions.add(String.format("%s.%s = %s.%s"
                                        ,joinTable.name()
                                        ,joinCol.name()
                                        ,entityTable.name()
                                        ,thisColName));
        }
      }

      query += String.format("%s %s \nON %s \nWHERE %s"
              ,joins
              ,joinConditions2.isEmpty() ? "" : "\nON " + StringUtils.arrayToDelimitedString(joinConditions2.toArray(), " AND ")
              ,StringUtils.arrayToDelimitedString(joinConditions.toArray(), " AND ")
              ,StringUtils.arrayToDelimitedString(conditions.toArray(), " AND "));

      if ((joinDef.sort() != null) && (joinDef.sort().length > 0)) {
        sorts = new ArrayList<>();
        for (EntitySort esort : joinDef.sort()) {
          if (StringUtils.isEmpty(esort.column().name())){
            continue;
          }
          if (!esort.direction().equalsIgnoreCase("ASC") && !esort.direction().equalsIgnoreCase("DESC")) {
            throw new Exception("The sort direction is not supported!");
          }
          sorts.add(joinTable.name() + ".\"" + esort.column().name() + "\" " + esort.direction());
        }
        if ((sorts != null) && (sorts.size() > 0)) {
          query += "\nORDER BY " + StringUtils.arrayToDelimitedString(sorts.toArray(), ",");
        }
      }
      
      if (isCollectionField) {
        E entity = (E) joinEntityClass.newInstance();
        ReflectionUtil.invoke(entity, "findQuery", query,params);
        Collection<? extends EntityBase> entities = null;
        if (((entities = ReflectionUtil.invoke(entity, "getFindQueryResult"))!=null)) {
          if ((field.getType().equals(java.util.List.class))
            || (field.getType().equals(java.util.Collection.class))) {
            field.set(this, entities);
          }
          else {
            Object collection = ((Class)field.getGenericType()).newInstance();
            ReflectionUtil.invoke(collection,"addAll", entities);
            field.set(this, collection);
          }
        }
      }
      else {
        // use select query
        E joinEntity = (E)fieldClass.newInstance();
        joinEntity = ReflectionUtil.invoke(joinEntity, "runQuery", query, params);
        field.set(this, joinEntity);
      }
    }
    catch (Exception ex) {
      String error = null;
      if (ex instanceof InvocationTargetException) {
        error = ex.getCause().getMessage();
      }
      else {
        error = ex.getMessage();
      }
      throw new IllegalStateException(
        String.format("%s.%s Error(s):\n%s"
          ,this.getClass().getName()
          ,"loadJoin(force)"
          ,error
        )
      );
    }
    return (E) this;
  }

  /**
   *
   * @return
   */
  protected E loadJoins(){
    try {
      Map<String,EntityJoin> joinMap = null;
      if (((joinMap = EntityUtil.getEntityJoins(this.mEntityClass)) != null)
          && (!joinMap.isEmpty())){
        Set<Entry<String,EntityJoin>> entries = joinMap.entrySet();
        for (Entry<String,EntityJoin> entry : entries) {
          this.loadJoin(entry.getKey(), entry.getValue(), false);
        }
      }
    }
    catch(Exception ex){
      String error = null;
      if (ex instanceof InvocationTargetException) {
        error = ex.getCause().getMessage();
      }
      else {
        error = ex.getMessage();
      }
      throw new IllegalStateException(
        String.format("%s.%s Error(s):\n%s"
          ,this.getClass().getName()
          ,"loadJoins()"
          ,error
        )
      );
    }

    return (E)this;
  }

  /**
   *
   * @param args
   * @return
   */
  public E select(ColumnFilter...columns){
    try {
      List<Object> params = new ArrayList<>();
      String unique = (this.isUnique()) ? "UNIQUE" : "";
      String columnsSelect = this.buildSelectColumns(columns,params);
      String conditions = this.buildConditions(this.mConditions, params);
      String sorts = this.buildSorts(this.mSorts, params);
      String query = String.format("SELECT %s %s FROM %s %s %s"
        ,StringUtils.isEmpty(unique) ? "" : unique
        ,StringUtils.isEmpty(columnsSelect) ? "" : columnsSelect
        ,EntityUtil.getTableName(this.mEntityClass)
        ,StringUtils.isEmpty(conditions) ? "" : conditions
        ,StringUtils.isEmpty(sorts) ? "" : sorts
      );
      this.runQuery(query,params);
      if (this.getQuerySuccess() && this.recordFound()) {
        this.loadJoins();
      }
    }
    catch(Exception ex){
      String error = null;
      if (ex instanceof InvocationTargetException) {
        error = ex.getCause().getMessage();
      }
      else {
        error = ex.getMessage();
      }
      throw new IllegalStateException(
        String.format("%s.%s Error(s):\n%s"
          ,this.getClass().getName()
          ,"select(columns)"
          ,error
        )
      );
    }

    return (E)this;
  }

  /**
   *
   * @param args
   * @return
   */
  public E delete(Object...args) {
    try {
      if ((this.mLoaded == null) || (!this.mLoaded)) {
        throw new IllegalStateException("You cannot delete unloaded entity!");
      }
      List<Field> idfields = null;
      if (((idfields = EntityUtil.getIdFields(this.mEntityClass)) == null) && ((args==null || args.length == 0))) {
        throw new NullPointerException("You cannot delete the entity without any conditions!");
      }

      if ((idfields != null) && (!idfields.isEmpty())) {
        for (Field f : idfields) {
          try {
            f.setAccessible(true);
            this.addFilter(ConditionFilter.createInstance(f.getName(), ConditionOperator.SQL_EQUAL, f.get(this)));
          }
          catch(Exception ex){
            throw ex;
          }
        }
      }
      else {
        this.addFilter(args);
      }

      List<Object> params = new ArrayList<>();
      String conditions = null;
      if (StringUtils.isEmpty((conditions = this.buildConditions(this.mConditions, params)))) {
        throw new NullPointerException("You cannot delete the entity without any conditions!");
      }

      String query = String.format("DELETE FROM %s %s"
                      ,EntityUtil.getTableName(this.mEntityClass)
                      ,conditions
                     );
      this.runQuery(query,params,true);
    }
    catch(Exception ex){
      String error = null;
      if (ex instanceof InvocationTargetException) {
        error = ex.getCause().getMessage();
      }
      else {
        error = ex.getMessage();
      }
      throw new IllegalStateException(
        String.format("%s.%s Error(s):\n%s"
          ,this.getClass().getName()
          ,"delete(args)"
          ,error
        )
      );
    }

    return (E)this;
  }

  /**
   *
   * @param args
   * @return
   */
  public E update(Object...args) {
    try {
      if ((this.mLoaded == null) || (!this.mLoaded)) {
        throw new IllegalStateException("You cannot update unloaded entity!");
      }
      if (this.mDirtyFields.isEmpty()) {
        throw new Exception("There are no dirty properties to update!");
      }

      List<Field> idfields = null;
      if (((idfields = EntityUtil.getIdFields(this.mEntityClass)) == null) && ((args==null || args.length == 0))) {
        throw new NullPointerException("You cannot delete the entity without any conditions!");
      }

      if ((idfields != null) && (!idfields.isEmpty())) {
        for (Field f : idfields) {
          try {
            f.setAccessible(true);
            this.addFilter(ConditionFilter.createInstance(f.getName(), ConditionOperator.SQL_EQUAL, f.get(this)));
          }
          catch(Exception ex){
            throw ex;
          }
        }
      }
      else {
        this.addFilter(args);
      }

      List<Object> params = new ArrayList<>();
      String updateSets = null;
      if (StringUtils.isEmpty(updateSets = this.buildUpdateSet(this.mDirtyFields, params))) {
        throw new Exception("There are no dirty properties to update!");
      }
      String conditions = null;
      if (StringUtils.isEmpty((conditions = this.buildConditions(this.mConditions, params)))) {
        throw new NullPointerException("You cannot update the entity without any conditions!");
      }

      String query = String.format("UPDATE %s SET %s %s"
                        ,EntityUtil.getTableName(this.mEntityClass)
                        ,updateSets
                        ,conditions
                      );

      this.runQuery(query,params,true);
    }
    catch(Exception ex){
      String error = null;
      if (ex instanceof InvocationTargetException) {
        error = ex.getCause().getMessage();
      }
      else {
        error = ex.getMessage();
      }

      throw new IllegalStateException(
        String.format("%s.%s Error(s):\n%s"
          ,this.getClass().getName()
          ,"update(args)"
          ,error
        )
      );
    }

    return (E)this;
  }

  /**
   *
   * @return
   */
  public E insert(){
    try {
      if ((this.mLoaded != null) && (this.mLoaded)) {
        throw new IllegalStateException("You cannot insert a loaded entity!");
      }
      if (this.mDirtyFields.isEmpty()) {
        throw new Exception("There are no dirty properties to insert!");
      }

      List<Object> params = new ArrayList<>();
      String inserts = null;
      if (StringUtils.isEmpty(inserts = this.buildInserts(this.mDirtyFields, params))) {
        throw new Exception("There are no dirty properties to update!");
      }

      String query = String.format("INSERT INTO %s %s"
                        ,EntityUtil.getTableName(this.mEntityClass)
                        ,inserts
                      );

      this.runQuery(query,params,true);
      this.mLoaded = true;
    }
    catch(Exception ex){
      String error = null;
      if (ex instanceof InvocationTargetException) {
        error = ex.getCause().getMessage();
      }
      else {
        error = ex.getMessage();
      }
      
      IllegalStateException ise = new IllegalStateException(
        String.format("%s.%s Error(s):\n%s"
          ,this.getClass().getName()
          ,"insert(args)"
          ,error
        )
      );
      ise.setStackTrace(ex.getStackTrace());
      throw ise;
    }

    return (E)this;
  }

  /**
   *
   * @return
   */
  private Collection<E> getFindQueryResult() {
    Collection<E> result = null;
    try {
      result = ReflectionUtil.invoke(this.mFindQueryDelegate, "getResult");
    }
    catch(Exception ex){
      String error = null;
      if (ex instanceof InvocationTargetException) {
        error = ex.getCause().getMessage();
      }
      else {
        error = ex.getMessage();
      }

      throw new IllegalStateException(
        String.format("%s.%s Error(s):\n%s"
          ,this.getClass().getName()
          ,"getFindAllResults()"
          ,error
        )
      );
    }
    return result;
  }
  
  protected final String buildQuery(List<Object> params, Object... args){
    params.clear();
    String result = null;
    String unique = null;
    String columnsSelect = null;
    String tableName = null;
    String conditions = null;
    String sorts = null;
    try {
      List<ColumnFilter> columns = null;
      if ((args != null) && (args.length > 0)) {
        this.addFilter(args);
        for (Object o : args) {
          if (o instanceof ColumnFilter) {
            if (columns == null) {
              columns = new ArrayList<>();
            }
            if (!columns.contains(o)) {
              columns.add((ColumnFilter)o);
            }
          }
        }
      }
      
      unique = (this.isUnique()) ? "UNIQUE" : "";
      columnsSelect = this.buildSelectColumns(columns == null?null:columns.toArray(new ColumnFilter[0]),params);
      tableName = EntityUtil.getTableName(this.mEntityClass);
      conditions = this.buildConditions(this.mConditions, params);
      sorts = this.buildSorts(this.mSorts, params);
      
      result = String.format("SELECT %s %s FROM %s %s %s"
        ,StringUtils.isEmpty(unique) ? "" : unique
        ,StringUtils.isEmpty(columnsSelect) ? "" : columnsSelect
        ,StringUtils.isEmpty(tableName) ? "" : tableName
        ,StringUtils.isEmpty(conditions) ? "" : conditions
        ,StringUtils.isEmpty(sorts) ? "" : sorts
      );
    } catch(Exception ex){
      String error = null;
      if (ex instanceof InvocationTargetException) {
        error = ex.getCause().getMessage();
      }
      else {
        error = ex.getMessage();
      }
      ex.printStackTrace();
      System.err.println("query => " + result
        + "\nunique => " + unique
        + "\ncolumnsSelect => " + columnsSelect
        + "\ntableName => " + tableName
        + "\nconditions => " + conditions
        + "\nsorts => " + sorts);
      
      throw new IllegalStateException(
        String.format("%s.%s %s:\n%s"
          ,this.getClass().getName()
          ,"findAll(args)"
          ,ex.getClass().getName()
          ,error
        )
      );
    }
    return result;
  }

  /**
   *
   * @param args
   * @return
   */
  public <C extends Collection<E>> C findAll(Object... args){
    C result = null;
    try {
      List<Object> params = new ArrayList<>();
      String query = this.buildQuery(params, args);
      this.findQuery(query, params);
      result = ReflectionUtil.invoke(this.mFindQueryDelegate, "getResult");
    }
    catch (Exception ex) {
      String error = null;
      if (ex instanceof InvocationTargetException) {
        error = ex.getCause().getMessage();
      }
      else {
        error = ex.getMessage();
      }
      ex.printStackTrace();;
      
      IllegalStateException exception = new IllegalStateException(
        String.format("%s.%s %s:\n%s"
          ,this.getClass().getName()
          ,"findAll(args)"
          ,ex.getClass().getName()
          ,error
        )
      );
      
      throw exception;
    }

    return result;
  }

  /**
   *
   * @param pageNo
   * @param pageSize
   * @param args
   * @return
   */
  public <C extends Collection<E>> C findPage(int pageNo, int pageSize, Object... args) {
    C result = null;
    try {
      if ((pageNo < 1) || (pageSize < 1)) {
        throw new Exception("The page number and page size cannot be smaller than 1!");
      }
      int start = ((pageNo < 0 ? 1 : pageNo) - 1) * pageSize + 1
          ,end = start + pageSize - 1;

      List<Object> params = new ArrayList<>();
      List<ColumnFilter> columns = null;
      if ((args != null) && (args.length > 0)) {
        this.addFilter(args);
        for (Object o : args) {
          if (o instanceof ColumnFilter) {
            if (columns == null) {
              columns = new ArrayList<>();
            }
            if (!columns.contains(o)) {
              columns.add((ColumnFilter)o);
            }
          }
        }
      }

      String selectColumns = this.buildSelectColumns(columns == null?null
                                  : columns.toArray(new ColumnFilter[0]),params)
              ,conditions = this.buildConditions(this.mConditions, params)
              ,sorts = this.buildSorts(this.mSorts, params)
              ,originalQuery = String.format("SELECT %s %s FROM %s %s %s"
                                  ,(this.isUnique()) ? "UNIQUE" : ""
                                  ,selectColumns
                                  ,EntityUtil.getTableName(this.mEntityClass)
                                  ,conditions
                                  ,sorts
                                )
              ,countQuery = String.format("SELECT count(*) AS TOTAL_RECORDS FROM (%s)"
                                    ,originalQuery)
              ,pageQuery = String.format("SELECT * \n" +
                            "  FROM \n" +
                            "( SELECT ROWNUM AS RNUM, A.*\n" +
                            "    from (%s) A\n" +
                            "   WHERE ROWNUM <= %d )\n" +
                            " WHERE RNUM >= %d",originalQuery,end,start);
      this.findQuery(pageQuery, params);
      result = ReflectionUtil.invoke(this.mFindQueryDelegate, "getResult");
      this.findQuery(countQuery, params, true);
    }
    catch (Exception ex) {
      String error = null;
      if (ex instanceof InvocationTargetException) {
        error = ex.getCause().getMessage();
      }
      else {
        error = ex.getMessage();
      }

      throw new IllegalStateException(
        String.format("%s.%s Error(s):\n%s"
          ,this.getClass().getName()
          ,"findAll(args)"
          ,error
        )
      );
    }

    return result;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  //<editor-fold defaultstate="collapsed" desc="Get Entity Class - getEntityClass()">
  public final Class getEntityClass(){
    return this.mEntityClass;
  }
  //</editor-fold>
  /**
   *
   * @param args
   * @return
   */
  public E addFilter(Object...args) {
    if ((args != null) && (args.length > 0)){
      for (Object o : args){
        if (o instanceof ConditionFilter) {
          boolean isfiltered = false;
          for (ConditionGroup cg1 : this.mConditions) {
            if ((isfiltered = cg1.contains((ConditionFilter)o))) {
              break;
            }
          }
          if (!isfiltered) {
            this.mConditions.add(new ConditionGroup((ConditionFilter) o));
          }
        }
        else if (o instanceof ConditionGroup) {
          this.mConditions.add((ConditionGroup) o);
        }
        else if (o instanceof SortFilter) {
          this.mSorts.add((SortFilter)o);
        }
      }
    }
    return (E)this;
  }

  /**
   *
   * @param args
   * @return
   */
  public E removeFilter(Object...args) {
    if ((args != null) && (args.length > 0)){
      for (Object o : args){
        if (o instanceof ConditionFilter) {
          for (ConditionGroup cg : this.mConditions) {
            cg.remove(o);
          }
        }
        else if (o instanceof ConditionGroup) {
          this.mConditions.remove((ConditionGroup) o);
        }
        else if (o instanceof SortFilter) {
          this.mSorts.remove((SortFilter)o);
        }
      }
    }
    return (E)this;
  }

  /**
   *
   * @return
   */
  public E unique(){
    return this.setUnique(true);
  }

  /**
   *
   * @return
   */
  public boolean getQuerySuccess(){
    return (this.mQuerySuccess != null) && (this.mQuerySuccess);
  }

  /**
   *
   * @return
   */
  public boolean recordFound(){
    return (this.mLoaded != null) && (this.mLoaded);
  }

  /**
   *
   * @return
   */
  public boolean isUnique(){
    return (this.mIsUnique != null) && (this.mIsUnique);
  }

  /**
   *
   * @param yesno
   * @return
   */
  public E setUnique(boolean yesno) {
    this.mIsUnique = yesno;
    return (E)this;
  }
  /**
   *
   * @return
   */
  public E beginTransaction(){
    EntityFacade.beginTransaction();
    return (E)this;
  }

  /**
   *
   * @return
   */
  public E commitTransaction(){
    Connection conn = null;
    if ((conn = ConnectionContext.fetchConnection(this.mEntityClass))!= null) {
      try {
        conn.commit();
        EntityFacade.resetAutoCommit();
      }
      catch(Exception ex){
        throw new IllegalStateException(
          String.format("%s.%s Error(s):\n%s"
            ,this.getClass().getName()
            ,"commitTransaction()"
            ,ex.getMessage()
          )
        );
      }
    }
    return (E)this;
  }

  /**
   *
   * @return
   */
  public final E rollbackTransaction() {
    Connection conn = null;
    if ((conn = ConnectionContext.fetchConnection(this.mEntityClass))!= null) {
      try {
        conn.rollback();
      }
      catch(Exception ex){
        ExceptionUtil.throwIllegalStateException(ex);
      }
    }
    return (E)this;
  }
  
  public final E rollbackTransactionQuietly(){
    try {
      this.rollbackTransaction();
    } 
    catch (Exception e) {
      // ignore transaction excpetions because the entity may have no initiated a transaction
    }
    return (E) this;
  }

  /**
   *
   * @return
   */
  public Map<String,Object> toMap(){
    Map<String,Object> map = null;
    Collection<Field> fields = null;
    if ((fields = EntityUtil.getEntityFields(this.mEntityClass)) != null) {
      map = new LinkedHashMap<>();
      for (Field f : fields) {
        try {
          f.setAccessible(true);
          map.put(f.getName(), f.get(this));
        }
        catch(Exception ex){
          this.mLogger
            .log(Level.WARNING
              ,"{0}.toMap() Error:\n{1}"
              ,new Object[]{this.getClass().getName(),ex.getMessage()});
        }
      }
    }
    return map;
  }
  
  public JSONObject toJson(){
    return this.toJson(null);
  }
  
  /**
   *
   * @return
   */
  public JSONObject toJson(JsonDelegate jsondel){
    JSONObject result = null;
    Collection<Field> fields = null;
    if ((fields = EntityUtil.getEntityFields(this.mEntityClass)) != null) {
      result = new JSONObject();
      Object o = null;
      for (Field f : fields) {
        try {
          f.setAccessible(true);
          o = f.get(this);
          if (o == null) {
            o = "";
          }
          result.put(f.getName(), o);
        }
        catch(Exception ex){
          this.mLogger
            .log(Level.WARNING
              ,"{0}.toJson() Error:\n{1}"
              ,new Object[]{this.getClass().getName(),ex.getMessage()});
        }
      }
      if (jsondel != null) {
        try {
          jsondel.setListener(this);
          jsondel.handle(result);
        }
        catch(Exception ex) {
          throw new IllegalStateException(
            String.format("%s.toJson(JsonDelegate) %s:\n%s"
              ,this.getClass().getName()
              ,ex.getClass().getName()
              ,ex.getMessage()
            )
          );
        }
      }
    }
    return result;
  }

  /**
   *
   * @return
   * @throws Exception
   */
  public E createInstance() throws Exception {
    return this.mEntityClass.newInstance();
  }

  /**
   *
   * @return
   */
  public int getTotalRecordCount(){
    int result = 0;
    try {
      result = ReflectionUtil.invoke(this.mCountQueryDelegate, "getCount");
    }
    catch(Exception ex) {
      this.mLogger
            .log(Level.WARNING
              ,"{0}.getTotalRecord() Error:\n{1}"
              ,new Object[]{this.getClass().getName(),ex.getMessage()});
    }
    return result;
  }

  /**
   *
   * @return
   */
  public boolean isLoaded(){
    return Objects.equals(this.mLoaded, true);
  }
  
  /**
   * 
   * @return 
   */
  public boolean isDirty(){
    return this.mDirtyFields != null && !this.mDirtyFields.isEmpty();
  }
  
  //<editor-fold defaultstate="collapsed" desc="Load Properties">
  public final E loadProperties(HttpServletRequest request) {
    return this.loadProperties(request,null);
  }
  
  public final E loadProperties(HttpServletRequest request,LoadPropertyDelegate delegate) {
    Collection<Field> fields = EntityUtil.getEntityFields(this.getClass());
    List<String> keys = new ArrayList<>();
    keys.addAll(request.getParameterMap().keySet());
    String key = null;
    String strVal = null;
    Object val = null;
    
    for (Field f : fields) {
      if (!keys.contains(key = f.getName())) {
        continue;
      }
      if (StringUtils.isEmpty(strVal = request.getParameter(key))) {
        val = null;
      }
      else if (String.class.isAssignableFrom(f.getType())) {
        val = strVal;
      }
      else {
        if (Objects.equals(Byte.class,f.getType())) {
          val = Byte.parseByte(strVal);
        }
        else if (Objects.equals(Short.class,f.getType())) {
          val = Short.parseShort(strVal);
        }
        else if (Objects.equals(Integer.class,f.getType())) {
          val = Integer.parseInt(strVal);
        }
        else if (Objects.equals(Long.class,f.getType())) {
          val = Long.parseLong(strVal);
        }
        else if (Objects.equals(Float.class,f.getType())) {
          val = Float.parseFloat(strVal);
        }
        else if (Objects.equals(Double.class,f.getType())) {
          val = Double.parseDouble(strVal);
        }
        else if (Objects.equals(Boolean.class,f.getType())) {
          val = Boolean.parseBoolean(strVal);
        }
        else if (delegate != null) {
          delegate.setListener(this);
          delegate.parse(f, strVal);
        }
      }
      keys.remove(key);
      this.set(key, val);
    }
    
    if (!keys.isEmpty()) {
      this.onLoadProperties(request, keys);
    }
    
    return (E) this;
  }
  
  protected E onLoadProperties(HttpServletRequest request, List<String> remainingKeys) {
    
    return (E)this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Dirty Field Check">
  public Boolean isDirty(String fieldname) {
    return (this.mDirtyFields != null) && (this.mDirtyFields.contains(fieldname));
  }
  //</editor-fold>
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Validate">
  public boolean onValidate(){
    return true;
  }

  public boolean validate() {
    try {
      if (this.isDirty()) {
        Field field = null;
        EntityValidation annot = null;
        String error = null;
        Class fieldClass = null;
        for (String fieldName : this.mDirtyFields) {
          error = null;
          if ((field = EntityUtil.getEntityField(this.mEntityClass, fieldName)) == null) {
            throw new NullPointerException(
              String.format("%s.validate() Error(s):\nThe entity does not have the field '%s'!"
                ,this.getClass().getName()
                ,fieldName
              )
            );
          }
          if ((annot = field.getAnnotation(EntityValidation.class)) == null) {
            continue;
          }
          field.setAccessible(true);
          fieldClass = field.getType();
          Object fieldValue = field.get(this);
          
          if (fieldClass.isAssignableFrom(String.class)) {
            if ((fieldValue == null) || (fieldValue.toString().isEmpty())) {
              if (annot.required()) {
                error = String.format("The %s field is required!", annot.field().isEmpty() ? fieldName : annot.field());
              }
            }
            else if ((fieldValue.toString().length() > annot.max())) {
              error = String.format("The %s field max character limit (%d) has reached!"
                        ,annot.field().isEmpty() ? fieldName : annot.field()
                        ,annot.max());
            }
            else if ((fieldValue.toString().length() < annot.min())) {
              error = String.format("The %s field min character limit (%d) has not met!"
                        ,annot.field().isEmpty() ? fieldName : annot.field()
                        ,annot.min());
            }
            else if (!annot.pattern().isEmpty()) {
              Pattern p = Pattern.compile(annot.pattern());
              if (!p.matcher(fieldValue.toString()).matches()) {
                error = String.format("The %s field format %s is not valid!"
                        ,annot.field().isEmpty() ? fieldName : annot.field()
                        ,StringUtils.isEmpty(annot.format()) ? annot.format() : "(" + annot.format() + ")");
              }
            }
          }
          else if ((fieldClass.isAssignableFrom(Integer.class))
                || (fieldClass.isAssignableFrom(Long.class))){
            
            if ((fieldClass.isAssignableFrom(Integer.class)) && (fieldValue != null)) {
              fieldValue = ((Integer)fieldValue).longValue();
            }
            
            if ((annot.required()) && (fieldValue == null)) {
              error = String.format("The %s field is required!", annot.field().isEmpty() ? fieldName : annot.field());
            }
            else if (((Long)fieldValue) > annot.max()) {
              error = String.format("The %s field cannot exceed the max value (%d)!"
                        ,annot.field().isEmpty() ? fieldName : annot.field()
                        ,annot.max());
            }
            else if (((Long)fieldValue) < annot.min()) {
              error = String.format("The %s field min value (%d) has not met!"
                        ,annot.field().isEmpty() ? fieldName : annot.field()
                        ,annot.min());
            }
            else if (!annot.pattern().isEmpty()) {
              Pattern p = Pattern.compile(annot.pattern());
              if (!p.matcher(fieldValue.toString()).matches()) {
                error = String.format("The %s field format %s is not valid!"
                        ,annot.field().isEmpty() ? fieldName : annot.field()
                        ,StringUtils.isEmpty(annot.format()) ? annot.format() : "(" + annot.format() + ")");
              }
            }
          }
          else if ((fieldClass.isAssignableFrom(Double.class))
                || (fieldClass.isAssignableFrom(Float.class))){
            if ((fieldClass.isAssignableFrom(Float.class)) && (fieldValue != null)) {
              fieldValue = ((Float)fieldValue).doubleValue();
            }
            if ((annot.required()) && (fieldValue == null)) {
              error = String.format("The %s field is required!", annot.field().isEmpty() ? fieldName : annot.field());
            }
            else if (((Double)fieldValue) > annot.max()) {
              error = String.format("The %s field cannot exceed the max value (%d)!"
                        ,annot.field().isEmpty() ? fieldName : annot.field()
                        ,annot.max());
            }
            else if (((Double)fieldValue) < annot.min()) {
              error = String.format("The %s field min value (%d) has not met!"
                        ,annot.field().isEmpty() ? fieldName : annot.field()
                        ,annot.min());
            }
            else if (!annot.pattern().isEmpty()) {
              Pattern p = Pattern.compile(annot.pattern());
              if (!p.matcher(fieldValue.toString()).matches()) {
                error = String.format("The %s field format %s is not valid!"
                        ,annot.field().isEmpty() ? fieldName : annot.field()
                        ,StringUtils.isEmpty(annot.format()) ? annot.format() : "(" + annot.format() + ")");
              }
            }
          }
          else if ((fieldClass.isAssignableFrom(java.util.Date.class))) {
            if ((annot.required()) && (fieldValue == null)) {
              error = String.format("The %s field is required!", annot.field().isEmpty() ? fieldName : annot.field());
            }
            else if (((java.util.Date)fieldValue).getTime() > annot.max()) {
              error = String.format("The %s field cannot be later than %s!"
                        ,annot.field().isEmpty() ? fieldName : annot.field()
                        ,new java.util.Date(annot.max())
                        ,DateUtil.toDate(annot.max()));
            }
            else if (((java.util.Date)fieldValue).getTime() < annot.min()) {
              error = String.format("The %s field cannot be earlier than %s!"
                        ,annot.field().isEmpty() ? fieldName : annot.field()
                        ,new java.util.Date(annot.min())
                        ,DateUtil.toDate(annot.min()));
            }
          }
          else if ((fieldClass.isAssignableFrom(java.sql.Timestamp.class))) {
            if ((annot.required()) && (fieldValue == null)) {
              error = String.format("The %s field is required!", annot.field().isEmpty() ? fieldName : annot.field());
            }
            else if (((java.sql.Timestamp)fieldValue).getTime() > annot.max()) {
              error = String.format("The %s field cannot be later than %s!"
                        ,annot.field().isEmpty() ? fieldName : annot.field()
                        ,new java.sql.Timestamp(annot.max())
                        ,DateUtil.toDate(annot.max()));
            }
            else if (((java.sql.Timestamp)fieldValue).getTime() < annot.min()) {
              error = String.format("The %s field cannot be earlier than %s!"
                        ,annot.field().isEmpty() ? fieldName : annot.field()
                        ,new java.sql.Timestamp(annot.min())
                        ,DateUtil.toDate(annot.min()));
            }
          }

          if (error != null) {
            if (!StringUtils.isEmpty(annot.error())) {
              error = annot.error();
            }
            throw new IllegalStateException(error);
          }
        }// end for

        this.onValidate();
      }
    }
    catch(Exception ex){
      throw new IllegalStateException(ex.getMessage());
    }
    
    return true;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Save">
  public E save(Object... args) {
    if (this.isDirty()) {
      if (this.isLoaded()) {
        this.update(args);
      }
      else {
        this.insert();
      }
    }
    
    return (E)this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Instance">
  public E getInstance() {
    return (E) WebUtil.getSession(this.getClass());
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Clone">
  /**
   * Copies every entity column properties except the primary properties.
   * 
   * @return 
   */
  @Override
  public E clone() {
    E result = null;
    try {
      result = this.mEntityClass.newInstance();
      Collection<Field> fields = EntityUtil.getEntityFields(this.mEntityClass);
      EntityColumn colDef = null;
      for (Field f : fields) {
        if ((colDef = f.getAnnotation(EntityColumn.class)) == null
          || colDef.primary()){
          continue;// technically, coldef exist, but skip if it is primary key
        }
        f.setAccessible(true);
        f.set(result, f.get(this));
      }
    }
    catch(Exception ex) {
      this.mLogger.log(Level.SEVERE, ExceptionUtil.readStackTrace(ex));
    }
    
    return result;
  }
  //</editor-fold>
}