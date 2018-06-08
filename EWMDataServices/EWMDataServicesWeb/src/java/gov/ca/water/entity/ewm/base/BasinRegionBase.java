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
  table = @EntityTable(name = "EWM_BASIN_REGION", schema = "EWM_ADM")
  ,datasource = @EntityDataSource(name="EwmAdmDataSource",user = "ewm",password = "Ewm95670!",url = "jdbc:oracle:thin:@localhost:1521:orcl")
)
public class BasinRegionBase<E extends BasinRegionBase> extends EntityBase<E> {

  //<editor-fold defaultstate="expanded" desc="Private Properties">
  
  /**
   *
   */
  @EntityColumn(name="EWM_BASIN_REGION_ID",primary=true)
  private Long ewmBasinRegionId;

  /**
   *
   */
  @EntityColumn(name="EWM_BASIN_REGION_CD")
  private String ewmBasinRegionCd;

  /**
   *
   */
  @EntityColumn(name="EWM_BASIN_REGION_DESC")
  private String ewmBasinRegionDesc;

  /**
   *
   */
  @EntityColumn(name="EWM_BASIN_REGION_ACTV")
  private String ewmBasinRegionActv;

  /**
   *
   */
  @EntityColumn(name="EWM_BASIN_REGION_ORDER")
  private Integer ewmBasinRegionOrder;

  /**
   *
   */
  @EntityColumn(name="MODIFIED_DATE")
  private Date modifiedDate;

  /**
   *
   */
  @EntityColumn(name="MODIFIED_USER")
  private String modifiedUser;

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
    
    this.ewmBasinRegionId = null;
    this.ewmBasinRegionCd = null;
    this.ewmBasinRegionDesc = null;
    this.ewmBasinRegionActv = null;
    this.ewmBasinRegionOrder = null;
    this.modifiedDate = null;
    this.modifiedUser = null;
    this.modifiedProc = null;
    this.applId = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters">
  
  /**
   * @param ewmBasinRegionId the ewmBasinRegionId to set
   */
  public E setEwmBasinRegionId(Long ewmBasinRegionId) {
    return this.set("ewmBasinRegionId",ewmBasinRegionId);
  }

  /**
   * @param ewmBasinRegionId the ewmBasinRegionId to set
   */
  public E setEwmBasinRegionId(ConditionOperator op, Long ewmBasinRegionId) {
    return this.addFilter(ConditionFilter.createInstance("ewmBasinRegionId",op,ewmBasinRegionId));
  }

  /**
   * @param ewmBasinRegionCd the ewmBasinRegionCd to set
   */
  public E setEwmBasinRegionCd(String ewmBasinRegionCd) {
    return this.set("ewmBasinRegionCd",ewmBasinRegionCd);
  }

  /**
   * @param ewmBasinRegionCd the ewmBasinRegionCd to set
   */
  public E setEwmBasinRegionCd(ConditionOperator op, String ewmBasinRegionCd) {
    return this.addFilter(ConditionFilter.createInstance("ewmBasinRegionCd",op,ewmBasinRegionCd));
  }

  /**
   * @param ewmBasinRegionDesc the ewmBasinRegionDesc to set
   */
  public E setEwmBasinRegionDesc(String ewmBasinRegionDesc) {
    return this.set("ewmBasinRegionDesc",ewmBasinRegionDesc);
  }

  /**
   * @param ewmBasinRegionDesc the ewmBasinRegionDesc to set
   */
  public E setEwmBasinRegionDesc(ConditionOperator op, String ewmBasinRegionDesc) {
    return this.addFilter(ConditionFilter.createInstance("ewmBasinRegionDesc",op,ewmBasinRegionDesc));
  }

  /**
   * @param ewmBasinRegionActv the ewmBasinRegionActv to set
   */
  public E setEwmBasinRegionActv(String ewmBasinRegionActv) {
    return this.set("ewmBasinRegionActv",ewmBasinRegionActv);
  }

  /**
   * @param ewmBasinRegionActv the ewmBasinRegionActv to set
   */
  public E setEwmBasinRegionActv(ConditionOperator op, String ewmBasinRegionActv) {
    return this.addFilter(ConditionFilter.createInstance("ewmBasinRegionActv",op,ewmBasinRegionActv));
  }

  /**
   * @param ewmBasinRegionOrder the ewmBasinRegionOrder to set
   */
  public E setEwmBasinRegionOrder(Integer ewmBasinRegionOrder) {
    return this.set("ewmBasinRegionOrder",ewmBasinRegionOrder);
  }

  /**
   * @param ewmBasinRegionOrder the ewmBasinRegionOrder to set
   */
  public E setEwmBasinRegionOrder(ConditionOperator op, Integer ewmBasinRegionOrder) {
    return this.addFilter(ConditionFilter.createInstance("ewmBasinRegionOrder",op,ewmBasinRegionOrder));
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
   * @return the ewmBasinRegionId
   */
  public Long getEwmBasinRegionId() {
    return this.ewmBasinRegionId;
  }

  /**
   * @return the ewmBasinRegionCd
   */
  public String getEwmBasinRegionCd() {
    return this.ewmBasinRegionCd;
  }

  /**
   * @return the ewmBasinRegionDesc
   */
  public String getEwmBasinRegionDesc() {
    return this.ewmBasinRegionDesc;
  }

  /**
   * @return the ewmBasinRegionActv
   */
  public String getEwmBasinRegionActv() {
    return this.ewmBasinRegionActv;
  }

  /**
   * @return the ewmBasinRegionOrder
   */
  public Integer getEwmBasinRegionOrder() {
    return this.ewmBasinRegionOrder;
  }

  /**
   * @return the modifiedDate
   */
  public Date getModifiedDate() {
    return this.modifiedDate;
  }

  /**
   * @return the modifiedUser
   */
  public String getModifiedUser() {
    return this.modifiedUser;
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