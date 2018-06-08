package entity.core.util;

import entity.core.EntityBase;
import entity.core.EntityCollection;
import entity.core.annotation.EntityColumn;
import entity.core.annotation.EntityDataSource;
import entity.core.annotation.EntityDef;
import entity.core.annotation.EntityJoin;
import entity.core.annotation.EntityTable;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.Map.Entry;
import org.springframework.util.StringUtils;

/**
 *
 * @author Charlie Lay
 */
public class EntityUtil implements Serializable {
  
  //<editor-fold defaultstate="collapsed" desc="Private Static Properties">
  private static Map<Class<? extends EntityBase>,EntityDef> mEntityDefinitions = new LinkedHashMap<>();
  private static Map<Class<? extends EntityBase>,Map<String,Field>> mEntityFields = new LinkedHashMap<>();
  private static Map<Class<? extends EntityBase>,Map<String,Field>> mEntityColumnFields = new LinkedHashMap<>();
  private static Map<Class<? extends EntityBase>,Map<String,EntityColumn>> mEntityColumns = new LinkedHashMap<>();
  private static Map<Class<? extends EntityBase>,Map<String,EntityJoin>> mEntityJoins = new LinkedHashMap<>();
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Override Methods">
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
//    mEntityDefinitions = null;
//    mEntityFields = null;
//    mEntityColumns = null;
//    mEntityJoins = null;
//    mEntityColumnFields = null;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Entity Util Methods">
  /**
   *
   * @param <E>
   * @param entityClass
   * @return
   */
  public final synchronized static <E extends EntityBase> EntityDef getEntityDefinition(Class<E> entityClass) {
    if (entityClass == null){
      throw new NullPointerException(
              String.format("%s.%s Error(s):\n%s"
                      , EntityUtil.class.getName()
                      , "getEntityDefinition(entityClass)"
                      , "The entity class parameter cannot be unassigned!")
      );
    }
    
    EntityDef result = null;
    
    if ((result = mEntityDefinitions.get(entityClass)) == null) {
      do {
        if ((entityClass.isAnnotationPresent(EntityDef.class))
                && ((result = entityClass.getAnnotation(EntityDef.class)) != null)) {
          break;
        }
      } while (!(entityClass = (Class<E>) entityClass.getSuperclass()).equals(EntityBase.class));
      
      if (result != null) {
        mEntityDefinitions.put(entityClass, result);
      }
    }
    
    return result;
  }
  
  /**
   *
   * @param <E>
   * @param entityClass
   * @return
   */
  public final synchronized static <E extends EntityBase> EntityDataSource getEntityDataSource(Class<E> entityClass) {
    if (entityClass == null) {
      throw new NullPointerException(
              String.format("%s.%s Error(s):\nThe %s parameter cannot be unassigned!"
                      ,EntityUtil.class.getName()
                      ,"getEntityDataSource(entityClass)"
                      ,"entityClass")
      );
    }
    
    EntityDef def = null;
    if ((def = getEntityDefinition(entityClass)) == null) {
      throw new IllegalStateException(
              String.format("%s.%s Error(s):\nThe entity class is not annotated with entity definition!"
                      ,EntityUtil.class.getName()
                      ,"getEntityDataSource(entityClass)")
      );
    }
    
    EntityDataSource result = null;
    if ((result = def.datasource()) == null) {
      throw new IllegalStateException(
              String.format("%s.%s Error(s):\nThe entity definition is missing the data source annotation!"
                      ,EntityUtil.class.getName()
                      ,"getEntityDataSource(entityClass)")
      );
    }
    
    if (StringUtils.isEmpty(result.name())) {
      throw new NullPointerException(
              String.format("%s.%s Error(s):\nThe data source name cannot be unassigned!"
                      ,EntityUtil.class.getName()
                      ,"getEntityDataSource(entityClass)")
      );
    }
    
    return result;
  }
  
  /**
   *
   * @param <E>
   * @param entityClass
   * @return
   */
  public final static <E extends EntityBase> EntityTable getEntityTable(Class<E> entityClass) {
    EntityTable result = null;
    
    if (entityClass == null) {
      throw new NullPointerException(
              String.format("%s.%s Error(s):\nThe %s parameter cannot be unassigned!"
                      ,EntityUtil.class.getName()
                      ,"getEntityTable(entityClass)"
                      ,"entityClass")
      );
    }
    
    EntityDef def = null;
    if ((def = getEntityDefinition(entityClass)) == null) {
      throw new IllegalStateException(
              String.format("%s.%s Error(s):\nThe entity class is not annotated with entity definition!"
                      ,EntityUtil.class.getName()
                      ,"getEntityTable(entityClass)")
      );
    }
    
    if ((result = def.table()) == null) {
      throw new IllegalStateException(
              String.format("%s.%s Error(s):\nThe entity definition is missing the data table annotation!"
                      ,EntityUtil.class.getName()
                      ,"getEntityTable(entityClass)")
      );
    }
    
    return result;
  }
  
  /**
   *
   * @param <E>
   * @param entityClass
   */
  public final synchronized static <E extends EntityBase> void initEntityClass(Class<E> entityClass) {
    if (entityClass == null) {
      throw new NullPointerException(
              String.format("%s.%s Error(s):\nThe %s parameter cannot be unassigned!"
                      ,EntityUtil.class.getName()
                      ,"initEntityClass(entityClass)"
                      ,"entityClass"
              )
      );
    }
    
    try {
      if (!mEntityFields.containsKey(entityClass)) {
        mEntityFields.put(entityClass, new LinkedHashMap<String,Field>());
      }
      if (!mEntityColumnFields.containsKey(entityClass)) {
        mEntityColumnFields.put(entityClass, new LinkedHashMap<String,Field>());
      }
      if (!mEntityColumns.containsKey(entityClass)) {
        mEntityColumns.put(entityClass, new LinkedHashMap<String,EntityColumn>());
      }
      if (!mEntityJoins.containsKey(entityClass)) {
        mEntityJoins.put(entityClass, new LinkedHashMap<String,EntityJoin>());
      }
      
      Class<E> cls = entityClass;
      
      Field[] fields = null;
      EntityColumn col = null;
      while (!cls.equals(EntityBase.class)) {
        fields = cls.getDeclaredFields();
        for (Field f : fields){
          col = null;
          if ((f.isAnnotationPresent(EntityColumn.class))
                  && ((col = f.getAnnotation(EntityColumn.class)) != null)
                  && !DataUtil.isEmpty(col.name())) {
            if (!mEntityFields.get(entityClass).containsKey(f.getName())) {
              mEntityFields.get(entityClass).put(f.getName(),f);
            }
            if (!mEntityColumnFields.get(entityClass).containsKey(f.getName())) {
              mEntityColumnFields.get(entityClass).put(col.name(),f);
            }
            if (!mEntityColumns.get(entityClass).containsKey(f.getName())) {
              mEntityColumns.get(entityClass).put(f.getName(),col);
            }
          }
          if (f.isAnnotationPresent(EntityJoin.class)) {
            mEntityJoins.get(entityClass).put(f.getName(), f.getAnnotation(EntityJoin.class));
          }
        }
        fields = cls.getFields();
        for (Field f : fields){
          col = null;
          if ((f.isAnnotationPresent(EntityColumn.class))
                  && ((col = f.getAnnotation(EntityColumn.class)) != null)
                  && !DataUtil.isEmpty(col.name())) {
            if (!mEntityFields.get(entityClass).containsKey(f.getName())) {
              mEntityFields.get(entityClass).put(f.getName(),f);
            }
            if (!mEntityColumnFields.get(entityClass).containsKey(f.getName())) {
              mEntityColumnFields.get(entityClass).put(col.name(),f);
            }
            if (!mEntityColumns.get(entityClass).containsKey(f.getName())) {
              mEntityColumns.get(entityClass).put(f.getName(),col);
            }
          }
          if (f.isAnnotationPresent(EntityJoin.class)) {
            mEntityJoins.get(entityClass).put(f.getName(), f.getAnnotation(EntityJoin.class));
          }
        }
        if (!(mEntityDefinitions.containsKey(entityClass))) {
          if ((cls.isAnnotationPresent(EntityDef.class))) {
            mEntityDefinitions.put(entityClass, cls.getAnnotation(EntityDef.class));
          }
        }
        cls = (Class<E>) cls.getSuperclass();
      }
    }
    catch (Exception ex) {
      ExceptionUtil
      .throwIllegalStateException(
        String.format("%s.%s Error(s):\n%s"
          ,EntityUtil.class.getName()
          ,"initEntityClass(entityClass)"
          ,ex.getMessage()
        )
        , ex
      );
    }
  }
  
  /**
   *
   * @param <E>
   * @param entityClass
   * @return
   */
  public final synchronized static <E extends EntityBase> Map<String,Field> getEntityFieldMap(Class<E> entityClass) {
    if (entityClass == null) {
      throw new NullPointerException(
              String.format("%s.%s Error(s):\nThe %s parameter cannot be unassigned!"
                      ,EntityUtil.class.getName()
                      ,"getEntityFieldMap(entityClass)"
                      ,"entityClass"
              )
      );
    }
    if (!mEntityFields.containsKey(entityClass)){
      initEntityClass(entityClass);
    }
    return mEntityFields.get(entityClass);
  }
  
  /**
   * 
   * @param <E>
   * @param entityClass
   * @return 
   */
  public final synchronized static <E extends EntityBase> Map<String,Field> getEntityColumnFieldMap(Class<E> entityClass) {
    if (entityClass == null) {
      throw new NullPointerException(
              String.format("%s.%s Error(s):\nThe %s parameter cannot be unassigned!"
                      ,EntityUtil.class.getName()
                      ,"getEntityFieldMap(entityClass)"
                      ,"entityClass"
              )
      );
    }
    if (!mEntityColumnFields.containsKey(entityClass)){
      initEntityClass(entityClass);
    }
    return mEntityColumnFields.get(entityClass);
  }
  
  /**
   *
   * @param <E>
   * @param entityClass
   * @return
   */
  public final static <E extends EntityBase> Collection<Field> getEntityFields(Class<E> entityClass) {
    Collection<Field> result = null;
    Map<String,Field> fieldmap = null;
    if ((fieldmap = getEntityFieldMap(entityClass)) != null) {
      result = fieldmap.values();
    }
    
    return result;
  }
  
  /**
   *
   * @param <E>
   * @param fieldname
   * @param entityClass
   * @return
   */
  public final static <E extends EntityBase> Field getEntityField(Class<E> entityClass, String fieldname) {
    if (StringUtils.isEmpty(fieldname)) {
      throw new NullPointerException(
              String.format("%s.%s Error(s):\nThe %s parameter cannot be unassigned!"
                      ,EntityUtil.class.getName()
                      ,"getEntityColumn(fieldname)"
                      ,"fieldname"
              )
      );
    }
    
    Field result = null;
    Map<String,Field> fieldmap = null;
    if ((fieldmap = getEntityFieldMap(entityClass)) != null) {
      result = fieldmap.get(fieldname);
    }
    
    if ((result == null)
        && ((fieldmap = getEntityColumnFieldMap(entityClass)) != null)) {
      result = fieldmap.get(fieldname);
    }
    
    return result;
  }
  
  /**
   *
   * @param <E>
   * @param entityClass
   * @return
   */
  public final synchronized static <E extends EntityBase> Map<String,EntityColumn> getEntityColumnMap(Class<E> entityClass) {
    if (entityClass == null) {
      throw new NullPointerException(
              String.format("%s.%s Error(s):\nThe %s parameter cannot be unassigned!"
                      ,EntityUtil.class.getName()
                      ,"getEntityColumns(entityClass)"
                      ,"entityClass"
              )
      );
    }
    
    if (!mEntityColumns.containsKey(entityClass)) {
      initEntityClass(entityClass);
    }
    
    return mEntityColumns.get(entityClass);
  }
  
  /**
   *
   * @param <E>
   * @param entityClass
   * @return
   */
  public final static <E extends EntityBase> Collection<EntityColumn> getEntityColumns(Class<E> entityClass) {
    Collection<EntityColumn> result = null;
    Map<String,EntityColumn> columnmap = null;
    if ((columnmap = getEntityColumnMap(entityClass)) !=  null) {
      result = columnmap.values();
    }
    return result;
  }
  
  
  /**
   *
   * @param <E>
   * @param fieldname
   * @param entityClass
   * @return
   */
  public final static <E extends EntityBase> EntityColumn getEntityColumn(Class<E> entityClass,String fieldname) {
    if (StringUtils.isEmpty(fieldname)) {
      throw new NullPointerException(
              String.format("%s.%s Error(s):\nThe %s parameter cannot be unassigned!"
                      ,EntityUtil.class.getName()
                      ,"getEntityColumn(fieldname)"
                      ,"fieldname"
              )
      );
    }
    
    EntityColumn result = null;
    Map<String,EntityColumn> columns = null;
    if ((columns = getEntityColumnMap(entityClass)) != null) {
      result = columns.get(fieldname);
    }
    return result;
  }
  
  /**
   *
   * @param <E>
   * @param fieldname
   * @param entityClass
   * @return
   */
  public final static <E extends EntityBase> String getColumnName(Class<E> entityClass, String fieldname) {
    if (StringUtils.isEmpty(fieldname)) {
      throw new NullPointerException(
              String.format("%s.%s Error(s):\nThe %s parameter cannot be unassigned!"
                      ,EntityUtil.class.getName()
                      ,"getEntityColumn(fieldname)"
                      ,"fieldname"
              )
      );
    }
    
    String result = null;
    EntityColumn col = null;
    if ((col = getEntityColumn(entityClass,fieldname)) != null) {
      result = col.name();
    }
    
    return result;
  }
  
  /**
   *
   * @param <E>
   * @param entityClass
   * @return
   */
  public final static <E extends EntityBase> String getTableName(Class<E> entityClass) {
    String result = null;
    EntityTable tabledef = null;
    
    if ((tabledef = getEntityTable(entityClass)) != null) {
      if (!StringUtils.isEmpty(tabledef.schema())) {
        result = tabledef.schema() + "." + tabledef.name();
      }
      else {
        result = tabledef.name();
      }
    } 
    return result;
  }
  
  /**
   *
   * @param <E>
   * @param entityClass
   * @return
   */
  public final static <E extends EntityBase> List<Field> getIdFields(Class<E> entityClass) {
    List<Field> result = null;
    Map<String,EntityColumn> columnMap = null;
    if ((columnMap = getEntityColumnMap(entityClass)) != null) {
      result = new ArrayList<>();
      Field f = null;
      for (Entry<String,EntityColumn> entry : columnMap.entrySet()) {
        if ((entry.getValue().primary()) && ((f = getEntityField(entityClass, entry.getKey())) != null)) {
          result.add(f);
        }
      }
    }
    return result;
  }
  
  /**
   * 
   * @param <E>
   * @param entity
   * @return 
   */
  public final synchronized static <E extends EntityBase> Map<String,EntityJoin> getEntityJoins(Class<E> entityClass) {
    if (entityClass == null) {
      throw new NullPointerException(
        String.format("%s.%s Error(s):\nThe %s parameter cannot be unassigned!"
          ,EntityUtil.class.getName()
          ,"getEntityJoins(entityClass)"
          ,"entityClass"
        )
      );
    }
    Map<String,EntityJoin> result = null;
    if ((mEntityJoins != null) && (mEntityJoins.containsKey(entityClass))) {
      result = mEntityJoins.get(entityClass);
    }
    
    return result;
  }
  
  public final static <E extends EntityBase> String toQueryColumnSelect(Class<E> entityClass) {
    return EntityUtil.toQueryColumnSelect(entityClass, null, null);
  }
  
  public final static <E extends EntityBase> String toQueryColumnSelect(Class<E> entityClass, String alias) {
    return EntityUtil.toQueryColumnSelect(entityClass, alias, null);
  }
  
  public final static <E extends EntityBase> String toQueryColumnSelect(Class<E> entityClass, String alias, String prefix) {
    String result = null;
    try {
      Collection<EntityColumn> columns = null;
      if ((columns = EntityUtil.getEntityColumns(entityClass)) != null) {
        result = "";
        alias = StringUtils.isEmpty(alias) ? "" : alias;
        String comma = "";
        for (EntityColumn ec : columns) {
          result += comma + alias + ec.name();
          if (!StringUtils.isEmpty(prefix)) {
            result += " AS " + prefix + ec.name();
          }
          comma = "\n,";
        }
      }
    } 
    catch (Exception e) {
      ExceptionUtil.throwIllegalStateException(
        String.format("%s.toQueryColumnSelect(Class,String,String) %s:\n%s"
          ,EntityUtil.class.getName()
          ,e.getClass().getName()
          ,e.getMessage()
        )
        ,e
      );
    }
    
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Static Get Instance">
  public final static synchronized <E extends EntityBase, EC extends EntityCollection<E>> EC getInstance(Class<EC> collectionClass) {
    EC result = null;
    try {
      result = WebUtil.getSession(collectionClass);
      ReflectionUtil.invoke(result, "onGetInstance");
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    return result;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="has Primary Columns - hasPrimaryColumns(Class<E> eclass)">
  public final static synchronized <E extends EntityBase> boolean hasPrimaryColumns(Class<E> eclass){
    Collection<EntityColumn> columns = EntityUtil.getPrimaryColumns(eclass);
    return columns != null && columns.isEmpty();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Get Primary Columns - getPrimaryColumns(Class<E> eclass)">
  public final static synchronized <E extends EntityBase> Collection<EntityColumn> getPrimaryColumns(Class<E> eclass){
    Collection<EntityColumn> result = EntityUtil.getEntityColumns(eclass);

    if (result != null && !result.isEmpty()) {
      Iterator<EntityColumn> it = result.iterator();
      while (it.hasNext()) {
        if (!it.next().primary()) {
          it.remove();
        }
      }
    }

    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Primary Column Fields">
  /**
   *
   * @param <E>
   * @param entityClass
   * @return
   */
  public final static <E extends EntityBase> List<Field> getPrimaryColumnFields(Class<E> entityClass) {
    List<Field> result = null;
    Map<String, EntityColumn> columnMap = null;
    if ((columnMap = getEntityColumnMap(entityClass)) != null) {
      result = new ArrayList<>();
      Field f = null;
      for (Entry<String, EntityColumn> entry : columnMap.entrySet()) {
        if ((entry.getValue().primary()) && ((f = getEntityField(entityClass, entry.getKey())) != null)) {
          result.add(f);
        }
      }
    }
    return result;
  } 
  
  //</editor-fold>
}
