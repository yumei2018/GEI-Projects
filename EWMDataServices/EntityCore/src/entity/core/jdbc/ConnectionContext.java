package entity.core.jdbc;

import entity.core.EntityBase;
import entity.core.annotation.EntityDataSource;
import entity.core.util.*;
import java.io.Serializable;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;
import oracle.ucp.UniversalConnectionPoolException;
import oracle.ucp.admin.UniversalConnectionPoolManager;
import oracle.ucp.admin.UniversalConnectionPoolManagerImpl;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

public class ConnectionContext implements Serializable {

  //<editor-fold defaultstate="collapsed" desc="Private Static Properties">
  private static ConnectionContext mCtx;
  private static Logger mLogger = Logger.getLogger(ConnectionContext.class.getName());
  private static List<String> mPoolNames;
  private final static String LOCAL_SESSION_ID = "999999";
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  private Map<String, Map<String, Connection>> mConnections;
  private Boolean mIsCleaning;
  private Map<String, DataSource> mDataSources;

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mConnections = null;
    this.mIsCleaning = null;
    this.mDataSources = null;
    this.mPoolNames = null;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Constructors
   */
  public ConnectionContext() {
    this.mConnections = new LinkedHashMap<>();
    this.mIsCleaning = false;
    this.mDataSources = new LinkedHashMap<>();
    this.mPoolNames = new ArrayList<>();
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Private Methods">
  /**
   *
   * @param <E>
   * @param entityClass
   * @return
   */
  private synchronized <E extends EntityBase> DataSource buildDataSource(Class<E> entityClass) {
    DataSource result = null;
    EntityDataSource dsdef = EntityUtil.getEntityDataSource(entityClass);

    if (StringUtils.isEmpty(dsdef.user()) || StringUtils.isEmpty(dsdef.password())
            || (StringUtils.isEmpty(dsdef.url())
            && (StringUtils.isEmpty(dsdef.host())
            || StringUtils.isEmpty(dsdef.sid())
            || StringUtils.isEmpty(dsdef.port())))) {
      throw new IllegalStateException(
        String.format("%s.%s Error(s):\nThe data source definition is incomplete!", EntityUtil.class.getName(), "buildDataSource(entityClass)")
      );
    }

    try {
      String url = dsdef.url().trim().isEmpty()
              ? String.format("jdbc:oracle:thin:@%1$s:%2$s:%3$s", dsdef.host(), dsdef.port(), dsdef.sid()) : dsdef.url().trim();

      //Creating a pool-enabled data source
      PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();
      //Setting pool properties
      pds.setURL(url);
      pds.setUser(dsdef.user());
      pds.setPassword(dsdef.password());
      // settings
      //pds.setMinPoolSize(1);
      pds.setConnectionFactoryClassName(OracleDataSource.class.getName());
      pds.setMaxPoolSize(100);
      String poolName = "OraclePoolName_" + DateUtil.getNow().getTime();
      if (!this.mPoolNames.contains(poolName)) {
        this.mPoolNames.add(poolName);
      }
      pds.setConnectionPoolName(poolName);
      pds.setInitialPoolSize(10); // 10s
      pds.setValidateConnectionOnBorrow(true);
      pds.setInactiveConnectionTimeout(10); // 10s
      pds.setAbandonedConnectionTimeout(10); // 10s
      pds.setConnectionWaitTimeout(120);// 2 mins

      result = pds;
    } catch (Exception exp) {
      throw new IllegalStateException(
              String.format("%s.%s Error(s):\n%s", EntityUtil.class.getName(), "buildDataSource(entityClass)", exp.getMessage())
      );
    }

    return result;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   *
   * @param <E>
   * @param entity
   * @return
   * @throws Exception
   */
  public synchronized <E extends EntityBase> DataSource getDataSource(Class<E> entityClass) {
    EntityDataSource dsdef = EntityUtil.getEntityDataSource(entityClass);
    DataSource result = null;

    if ((result = this.mDataSources.get(dsdef.name())) == null) {
      ApplicationContext appCtx = null;
      if (((appCtx = WebUtil.getApplicationContext()) != null)
              && (appCtx.containsBean(dsdef.name()))) {
        result = (DataSource) appCtx.getBean(dsdef.name());
        String poolName = null;
        if ((!StringUtils.isEmpty(poolName = ((PoolDataSource) result).getConnectionPoolName()))
                && (!this.mPoolNames.contains(poolName))) {
          this.mPoolNames.add(poolName);
        } else {
          poolName = "OraclePoolName_" + DateUtil.getNow().getTime();
          this.mPoolNames.add(poolName);
          try {
            ((PoolDataSource) result).setConnectionPoolName(poolName);
          } catch (SQLException ex) {
            Logger.getLogger(ConnectionContext.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      }
      if (result == null) {
        result = this.buildDataSource(entityClass);
      }
      if (result != null) {
        this.mDataSources.put(dsdef.name(), result);
      }
    }

    return result;
  }

  /**
   *
   * @param <E>
   * @param entityClass
   * @return
   * @throws Exception
   */
  public synchronized <E extends EntityBase> Connection getConnection(Class<E> entityClass) throws SQLException {
    this.cleanConnections();
    EntityDataSource dsdef = null;
    HttpSession session = null;
    String sessionId = null;
    if ((dsdef = EntityUtil.getEntityDataSource(entityClass)) == null) {
      throw new NullPointerException("There is no data source annotations for the entity " + entityClass.getName() + "!");
    }
    if ((session = WebUtil.getSession()) != null) {
      sessionId = session.getId();
    } else {
      sessionId = LOCAL_SESSION_ID;
    }
    Connection result = null;
    if ((this.mConnections.get(sessionId) == null)
            || ((result = this.mConnections.get(sessionId).get(dsdef.name())) == null)
            || result.isClosed()) {
      DataSource ds = null;
      if ((ds = this.getDataSource(entityClass)) == null) {
        throw new NullPointerException(
                String.format("%s.%s Error(s):\nCannot retrieve the entity's data source!", this.getClass().getName(), "getConnection(entityClass)")
        );
      }
      try {
        result = ds.getConnection();
        if (!this.mConnections.containsKey(sessionId)) {
          this.mConnections.put(sessionId, new LinkedHashMap());
        }
        this.mConnections.get(sessionId).put(dsdef.name(), result);
      } catch (SQLException ex) {
        ExceptionUtil.throwIllegalStateException(
                String.format("%s.%s Error(s):\n%s", this.getClass().getName(), "getConnection(entityClass)", ex.getMessage()), ex
        );
      }
    }
    return result;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Public Static Methods">
  /**
   *
   * @return mCtx ConnectionContext
   */
  public synchronized static ConnectionContext getInstance() {
    if (mCtx == null) {
      mCtx = new ConnectionContext();
    }
    return mCtx;
  }

  /**
   *
   * @param <E>
   * @param entity
   * @return
   */
  public synchronized static <E extends EntityBase> Connection fetchConnection(Class<E> entityClass) {
    Connection result = null;
    try {
      result = getInstance().getConnection(entityClass);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
           
    return result;
  }
  // </editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Context Destroy Methods">
  /**
   *
   * @return
   */
  @SuppressWarnings("unchecked")
  public synchronized ConnectionContext closeSessionConnections() {
    String sessionId = null;
    if ((WebUtil.getSession() != null)
            && ((sessionId = WebUtil.getSession().getId()) != null)) {
      this.closeSessionConnections(sessionId);
    }
    return this;
  }

  @SuppressWarnings("unchecked")
  public synchronized ConnectionContext closeSessionConnections(String sessionId) {
    Map<String, Connection> conns = null;
    if ((this.mConnections.containsKey(sessionId))
            && ((conns = this.mConnections.get(sessionId)) != null)
            && (conns.size() > 0)) {
      for (Connection conn : conns.values()) {
        try {
          if ((!conn.isClosed()) || (conn.isValid(0))) {
            conn.close();
          }
        } catch (Exception exp) {
          this.mLogger.log(Level.WARNING, "{0}.closeSessionConnections Error:\n {1}", new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
        }
      }
      this.mConnections.get(sessionId).clear();
      this.mConnections.remove(sessionId);
    }

    return this;
  }

  /**
   *
   * @return
   */
  @SuppressWarnings("unchecked")
  private synchronized ConnectionContext cleanConnections() {
    try {
      if ((!Objects.equals(this.mIsCleaning, true))
              && (this.mConnections != null)
              && (this.mConnections.size() > 0)) {
        this.mIsCleaning = true;
        Connection conn = null;
        String[] sessionKeys = this.mConnections.keySet().toArray(new String[0]);
        String[] datasourceKeys = null;
        for (String sessionId : sessionKeys) {
          datasourceKeys = this.mConnections.get(sessionId).keySet().toArray(new String[0]);
          for (String dslookup : datasourceKeys) {
            try {
              if (this.mConnections.get(sessionId) != null) {
                conn = this.mConnections.get(sessionId).get(dslookup);
                if ((conn.isClosed())) {
                  this.mConnections.get(sessionId).remove(dslookup);
                }
              }
            } catch (Exception exp) {
              exp.printStackTrace();
              this.mLogger.log(Level.WARNING, "{0}.cleanConnections Error:\n {1}", new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
            }
          }
          if ((this.mConnections.get(sessionId) != null)
                  && (this.mConnections.get(sessionId).isEmpty())) {
            this.mConnections.remove(sessionId);
          }
        }
        this.mIsCleaning = false;
      }
    } catch (Exception exp) {
      exp.printStackTrace();
      this.mLogger.log(Level.WARNING, "{0}.cleanConnections Error:\n {1}", new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    } finally {
      return this;
    }
  }

  /**
   *
   * @return
   */
  public synchronized ConnectionContext contextDestroy() {
    return this.closeAllConnections().closePoolManager().deregisterJdbcDrivers();
  }

  /**
   * Closes all connections
   *
   * @return
   */
  @SuppressWarnings("unchecked")
  public synchronized ConnectionContext closeAllConnections() {
    this.mLogger.log(Level.INFO, "Closing all connections...");
    if ((this.mConnections != null) && (this.mConnections.size() > 0)) {
      for (Map<String, Connection> conns : this.mConnections.values()) {
        for (Connection conn : conns.values()) {
          try {
            if ((!conn.isClosed()) || (conn.isValid(0))) {
              conn.close();
            }
          } catch (Exception exp) {
            exp.printStackTrace();
            this.mLogger.log(Level.WARNING, "{0}.closeAllConnections Error:\n {1}", new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
          }
        }
      }
      this.mConnections.clear();
    }

    return this;
  }

  /**
   *
   * @return
   */
  @SuppressWarnings("unchecked")
  public synchronized ConnectionContext closePoolManager() {
    this.mLogger.log(Level.INFO, "Closing the pool manager...");
    try {
      UniversalConnectionPoolManager ucpManager = UniversalConnectionPoolManagerImpl
              .getUniversalConnectionPoolManager();
      if (ucpManager != null) {
        if (this.mPoolNames != null) {
          for (String poolName : this.mPoolNames) {
            try {
              this.mLogger.log(Level.INFO, "..." + poolName);
              ucpManager.destroyConnectionPool(poolName);
            } catch (UniversalConnectionPoolException ex) {
              ex.printStackTrace();
              this.mLogger.log(Level.WARNING, "{0}.closePoolManager Error:\n {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
            }
          }
        }
      }
    } catch (Exception exp) {
      exp.printStackTrace();
      this.mLogger.log(Level.WARNING, "{0}.closePoolManager Error:\n {1}", new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }
    return this;
  }

  /**
   *
   * @return
   */
  @SuppressWarnings("unchecked")
  public synchronized ConnectionContext deregisterJdbcDrivers() {
    this.mLogger.log(Level.INFO, "Deregister the JDBC drivers...");
    Enumeration<Driver> drivers = DriverManager.getDrivers();
    while (drivers.hasMoreElements()) {
      Driver driver = drivers.nextElement();
      try {
        DriverManager.deregisterDriver(driver);
      } catch (Exception e) {
        e.printStackTrace();
        this.mLogger.log(Level.WARNING, "{0}.deregisterJdbcDrivers Error:\n {1}", new Object[]{this.getClass().getSimpleName(), e.getMessage()});
      }
    }

    return this;
  }
  //</editor-fold>
}
