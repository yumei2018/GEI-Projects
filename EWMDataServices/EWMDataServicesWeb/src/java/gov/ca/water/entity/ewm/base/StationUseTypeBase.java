package gov.ca.water.entity.ewm.base;

import entity.core.EntityBase;
import entity.core.annotation.*;
import entity.core.filter.ConditionFilter;
import entity.core.filter.ConditionFilter.ConditionOperator;
import java.sql.Date;


/**
 * This class is auto generated
 */
@EntityDef(
  table = @EntityTable(name = "EWM_STATION_USE_TYPE", schema = "EWM_ADM")
  ,datasource = @EntityDataSource(name="EwmAdmDataSource",user = "ewm",password = "Ewm95670!",url = "jdbc:oracle:thin:@localhost:1521:orcl")
)
public class StationUseTypeBase<E extends StationUseTypeBase> extends EntityBase<E> {

  //<editor-fold defaultstate="expanded" desc="Private Properties">
  
  /**
   *
   */
  @EntityColumn(name="EWM_STATION_USE_ID",primary=true)
  private Long ewmStationUseId;

  /**
   *
   */
  @EntityColumn(name="EWM_STATION_USE_DESC")
  private String ewmStationUseDesc;

  /**
   *
   */
  @EntityColumn(name="EWM_STATION_USE_ACTV")
  private String ewmStationUseActv;

  /**
   *
   */
  @EntityColumn(name="EWM_STATION_USE_ORDER")
  private Integer ewmStationUseOrder;

  /**
   *
   */
  @EntityColumn(name="MODIFIED_USER")
  private String modifiedUser;

  /**
   *
   */
  @EntityColumn(name="MODIFIED_DATE")
  private Date modifiedDate;

  /**
   *
   */
  @EntityColumn(name="MODIFIED_PROC")
  private String modifiedProc;

  /**
   *
   */
  @EntityColumn(name="APPL_ID")
  private Long applId;


  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    
    this.ewmStationUseId = null;
    this.ewmStationUseDesc = null;
    this.ewmStationUseActv = null;
    this.ewmStationUseOrder = null;
    this.modifiedUser = null;
    this.modifiedDate = null;
    this.modifiedProc = null;
    this.applId = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters">
  
  /**
   * @param ewmStationUseId the ewmStationUseId to set
   */
  public E setEwmStationUseId(Long ewmStationUseId) {
    return this.set("ewmStationUseId",ewmStationUseId);
  }

  /**
   * @param ewmStationUseId the ewmStationUseId to set
   */
  public E setEwmStationUseId(ConditionOperator op, Long ewmStationUseId) {
    return this.addFilter(ConditionFilter.createInstance("ewmStationUseId",op,ewmStationUseId));
  }

  /**
   * @param ewmStationUseDesc the ewmStationUseDesc to set
   */
  public E setEwmStationUseDesc(String ewmStationUseDesc) {
    return this.set("ewmStationUseDesc",ewmStationUseDesc);
  }

  /**
   * @param ewmStationUseDesc the ewmStationUseDesc to set
   */
  public E setEwmStationUseDesc(ConditionOperator op, String ewmStationUseDesc) {
    return this.addFilter(ConditionFilter.createInstance("ewmStationUseDesc",op,ewmStationUseDesc));
  }

  /**
   * @param ewmStationUseActv the ewmStationUseActv to set
   */
  public E setEwmStationUseActv(String ewmStationUseActv) {
    return this.set("ewmStationUseActv",ewmStationUseActv);
  }

  /**
   * @param ewmStationUseActv the ewmStationUseActv to set
   */
  public E setEwmStationUseActv(ConditionOperator op, String ewmStationUseActv) {
    return this.addFilter(ConditionFilter.createInstance("ewmStationUseActv",op,ewmStationUseActv));
  }

  /**
   * @param ewmStationUseOrder the ewmStationUseOrder to set
   */
  public E setEwmStationUseOrder(Integer ewmStationUseOrder) {
    return this.set("ewmStationUseOrder",ewmStationUseOrder);
  }

  /**
   * @param ewmStationUseOrder the ewmStationUseOrder to set
   */
  public E setEwmStationUseOrder(ConditionOperator op, Integer ewmStationUseOrder) {
    return this.addFilter(ConditionFilter.createInstance("ewmStationUseOrder",op,ewmStationUseOrder));
  }

  /**
   * @param modifiedUser the modifiedUser to set
   */
  public E setModifiedUser(String modifiedUser) {
    return this.set("modifiedUser",modifiedUser);
  }

  /**
   * @param modifiedUser the modifiedUser to set
   */
  public E setModifiedUser(ConditionOperator op, String modifiedUser) {
    return this.addFilter(ConditionFilter.createInstance("modifiedUser",op,modifiedUser));
  }

  /**
   * @param modifiedDate the modifiedDate to set
   */
  public E setModifiedDate(Date modifiedDate) {
    return this.set("modifiedDate",modifiedDate);
  }

  /**
   * @param modifiedDate the modifiedDate to set
   */
  public E setModifiedDate(ConditionOperator op, Date modifiedDate) {
    return this.addFilter(ConditionFilter.createInstance("modifiedDate",op,modifiedDate));
  }

  /**
   * @param modifiedProc the modifiedProc to set
   */
  public E setModifiedProc(String modifiedProc) {
    return this.set("modifiedProc",modifiedProc);
  }

  /**
   * @param modifiedProc the modifiedProc to set
   */
  public E setModifiedProc(ConditionOperator op, String modifiedProc) {
    return this.addFilter(ConditionFilter.createInstance("modifiedProc",op,modifiedProc));
  }

  /**
   * @param applId the applId to set
   */
  public E setApplId(Long applId) {
    return this.set("applId",applId);
  }

  /**
   * @param applId the applId to set
   */
  public E setApplId(ConditionOperator op, Long applId) {
    return this.addFilter(ConditionFilter.createInstance("applId",op,applId));
  }

  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Getters">
  /**
   * @return the ewmStationUseId
   */
  public Long getEwmStationUseId() {
    return this.ewmStationUseId;
  }

  /**
   * @return the ewmStationUseDesc
   */
  public String getEwmStationUseDesc() {
    return this.ewmStationUseDesc;
  }

  /**
   * @return the ewmStationUseActv
   */
  public String getEwmStationUseActv() {
    return this.ewmStationUseActv;
  }

  /**
   * @return the ewmStationUseOrder
   */
  public Integer getEwmStationUseOrder() {
    return this.ewmStationUseOrder;
  }

  /**
   * @return the modifiedUser
   */
  public String getModifiedUser() {
    return this.modifiedUser;
  }

  /**
   * @return the modifiedDate
   */
  public Date getModifiedDate() {
    return this.modifiedDate;
  }

  /**
   * @return the modifiedProc
   */
  public String getModifiedProc() {
    return this.modifiedProc;
  }

  /**
   * @return the applId
   */
  public Long getApplId() {
    return this.applId;
  }

  //</editor-fold>
}