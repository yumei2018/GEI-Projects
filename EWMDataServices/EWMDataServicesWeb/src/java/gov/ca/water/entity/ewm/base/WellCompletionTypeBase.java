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
  table = @EntityTable(name = "EWM_WELL_COMPLETION_TYPE", schema = "EWM_ADM")
  ,datasource = @EntityDataSource(name="EwmAdmDataSource",user = "ewm",password = "Ewm95670!",url = "jdbc:oracle:thin:@localhost:1521:orcl")
)
public class WellCompletionTypeBase<E extends WellCompletionTypeBase> extends EntityBase<E> {

  //<editor-fold defaultstate="expanded" desc="Private Properties">
  
  /**
   *
   */
  @EntityColumn(name="EWM_WELL_COMPLETION_ID",primary=true)
  private Long ewmWellCompletionId;

  /**
   *
   */
  @EntityColumn(name="EWM_WELL_COMPLETION_DESC")
  private String ewmWellCompletionDesc;

  /**
   *
   */
  @EntityColumn(name="EWM_WELL_COMPLETION_ACTV")
  private String ewmWellCompletionActv;

  /**
   *
   */
  @EntityColumn(name="EWM_WELL_COMPLETION_ORDER")
  private Integer ewmWellCompletionOrder;

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
    
    this.ewmWellCompletionId = null;
    this.ewmWellCompletionDesc = null;
    this.ewmWellCompletionActv = null;
    this.ewmWellCompletionOrder = null;
    this.modifiedUser = null;
    this.modifiedDate = null;
    this.modifiedProc = null;
    this.applId = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters">
  
  /**
   * @param ewmWellCompletionId the ewmWellCompletionId to set
   */
  public E setEwmWellCompletionId(Long ewmWellCompletionId) {
    return this.set("ewmWellCompletionId",ewmWellCompletionId);
  }

  /**
   * @param ewmWellCompletionId the ewmWellCompletionId to set
   */
  public E setEwmWellCompletionId(ConditionOperator op, Long ewmWellCompletionId) {
    return this.addFilter(ConditionFilter.createInstance("ewmWellCompletionId",op,ewmWellCompletionId));
  }

  /**
   * @param ewmWellCompletionDesc the ewmWellCompletionDesc to set
   */
  public E setEwmWellCompletionDesc(String ewmWellCompletionDesc) {
    return this.set("ewmWellCompletionDesc",ewmWellCompletionDesc);
  }

  /**
   * @param ewmWellCompletionDesc the ewmWellCompletionDesc to set
   */
  public E setEwmWellCompletionDesc(ConditionOperator op, String ewmWellCompletionDesc) {
    return this.addFilter(ConditionFilter.createInstance("ewmWellCompletionDesc",op,ewmWellCompletionDesc));
  }

  /**
   * @param ewmWellCompletionActv the ewmWellCompletionActv to set
   */
  public E setEwmWellCompletionActv(String ewmWellCompletionActv) {
    return this.set("ewmWellCompletionActv",ewmWellCompletionActv);
  }

  /**
   * @param ewmWellCompletionActv the ewmWellCompletionActv to set
   */
  public E setEwmWellCompletionActv(ConditionOperator op, String ewmWellCompletionActv) {
    return this.addFilter(ConditionFilter.createInstance("ewmWellCompletionActv",op,ewmWellCompletionActv));
  }

  /**
   * @param ewmWellCompletionOrder the ewmWellCompletionOrder to set
   */
  public E setEwmWellCompletionOrder(Integer ewmWellCompletionOrder) {
    return this.set("ewmWellCompletionOrder",ewmWellCompletionOrder);
  }

  /**
   * @param ewmWellCompletionOrder the ewmWellCompletionOrder to set
   */
  public E setEwmWellCompletionOrder(ConditionOperator op, Integer ewmWellCompletionOrder) {
    return this.addFilter(ConditionFilter.createInstance("ewmWellCompletionOrder",op,ewmWellCompletionOrder));
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
   * @return the ewmWellCompletionId
   */
  public Long getEwmWellCompletionId() {
    return this.ewmWellCompletionId;
  }

  /**
   * @return the ewmWellCompletionDesc
   */
  public String getEwmWellCompletionDesc() {
    return this.ewmWellCompletionDesc;
  }

  /**
   * @return the ewmWellCompletionActv
   */
  public String getEwmWellCompletionActv() {
    return this.ewmWellCompletionActv;
  }

  /**
   * @return the ewmWellCompletionOrder
   */
  public Integer getEwmWellCompletionOrder() {
    return this.ewmWellCompletionOrder;
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