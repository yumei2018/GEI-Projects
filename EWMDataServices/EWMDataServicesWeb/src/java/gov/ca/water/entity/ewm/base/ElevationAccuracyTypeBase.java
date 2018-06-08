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
  table = @EntityTable(name = "EWM_ELEVATION_ACCURACY_TYPE",schema = "EWM_ADM")
  ,datasource = @EntityDataSource(name="EwmAdmDataSource",user = "ewm_adm",password = "Ewm95670!",url = "jdbc:oracle:thin:@localhost:1521:orcl")
)
public class ElevationAccuracyTypeBase<E extends ElevationAccuracyTypeBase> extends EntityBase<E> {

  //<editor-fold defaultstate="expanded" desc="Private Properties">
  
  /**
   *
   */
  @EntityColumn(name="EWM_ELEVATION_ACCURACY_TYPE_ID",primary=true)
  private Long ewmElevationAccuracyTypeId;

  /**
   *
   */
  @EntityColumn(name="EWM_ELEVATION_ACCURACY_DESC")
  private String ewmElevationAccuracyDesc;

  /**
   *
   */
  @EntityColumn(name="EWM_ELEVATION_ACCURACY_ACTV")
  private String ewmElevationAccuracyActv;

  /**
   *
   */
  @EntityColumn(name="EWM_ELEVATION_ACCURACY_ORDER")
  private Integer ewmElevationAccuracyOrder;

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
  @EntityColumn(name="EWM_ELEVATION_ACCURACY_CD")
  private String ewmElevationAccuracyCd;

  /**
   *
   */
  @EntityColumn(name="APPL_ID")
  private Long applId;


  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    
    this.ewmElevationAccuracyTypeId = null;
    this.ewmElevationAccuracyDesc = null;
    this.ewmElevationAccuracyActv = null;
    this.ewmElevationAccuracyOrder = null;
    this.modifiedDate = null;
    this.modifiedUser = null;
    this.modifiedProc = null;
    this.ewmElevationAccuracyCd = null;
    this.applId = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters">
  
  /**
   * @param ewmElevationAccuracyTypeId the ewmElevationAccuracyTypeId to set
   */
  public E setEwmElevationAccuracyTypeId(Long ewmElevationAccuracyTypeId) {
    return this.set("ewmElevationAccuracyTypeId",ewmElevationAccuracyTypeId);
  }

  /**
   * @param ewmElevationAccuracyTypeId the ewmElevationAccuracyTypeId to set
   */
  public E setEwmElevationAccuracyTypeId(ConditionOperator op, Long ewmElevationAccuracyTypeId) {
    return this.addFilter(ConditionFilter.createInstance("ewmElevationAccuracyTypeId",op,ewmElevationAccuracyTypeId));
  }

  /**
   * @param ewmElevationAccuracyDesc the ewmElevationAccuracyDesc to set
   */
  public E setEwmElevationAccuracyDesc(String ewmElevationAccuracyDesc) {
    return this.set("ewmElevationAccuracyDesc",ewmElevationAccuracyDesc);
  }

  /**
   * @param ewmElevationAccuracyDesc the ewmElevationAccuracyDesc to set
   */
  public E setEwmElevationAccuracyDesc(ConditionOperator op, String ewmElevationAccuracyDesc) {
    return this.addFilter(ConditionFilter.createInstance("ewmElevationAccuracyDesc",op,ewmElevationAccuracyDesc));
  }

  /**
   * @param ewmElevationAccuracyActv the ewmElevationAccuracyActv to set
   */
  public E setEwmElevationAccuracyActv(String ewmElevationAccuracyActv) {
    return this.set("ewmElevationAccuracyActv",ewmElevationAccuracyActv);
  }

  /**
   * @param ewmElevationAccuracyActv the ewmElevationAccuracyActv to set
   */
  public E setEwmElevationAccuracyActv(ConditionOperator op, String ewmElevationAccuracyActv) {
    return this.addFilter(ConditionFilter.createInstance("ewmElevationAccuracyActv",op,ewmElevationAccuracyActv));
  }

  /**
   * @param ewmElevationAccuracyOrder the ewmElevationAccuracyOrder to set
   */
  public E setEwmElevationAccuracyOrder(Integer ewmElevationAccuracyOrder) {
    return this.set("ewmElevationAccuracyOrder",ewmElevationAccuracyOrder);
  }

  /**
   * @param ewmElevationAccuracyOrder the ewmElevationAccuracyOrder to set
   */
  public E setEwmElevationAccuracyOrder(ConditionOperator op, Integer ewmElevationAccuracyOrder) {
    return this.addFilter(ConditionFilter.createInstance("ewmElevationAccuracyOrder",op,ewmElevationAccuracyOrder));
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
   * @param ewmElevationAccuracyCd the ewmElevationAccuracyCd to set
   */
  public E setEwmElevationAccuracyCd(String ewmElevationAccuracyCd) {
    return this.set("ewmElevationAccuracyCd",ewmElevationAccuracyCd);
  }

  /**
   * @param ewmElevationAccuracyCd the ewmElevationAccuracyCd to set
   */
  public E setEwmElevationAccuracyCd(ConditionOperator op, String ewmElevationAccuracyCd) {
    return this.addFilter(ConditionFilter.createInstance("ewmElevationAccuracyCd",op,ewmElevationAccuracyCd));
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
   * @return the ewmElevationAccuracyTypeId
   */
  public Long getEwmElevationAccuracyTypeId() {
    return this.ewmElevationAccuracyTypeId;
  }

  /**
   * @return the ewmElevationAccuracyDesc
   */
  public String getEwmElevationAccuracyDesc() {
    return this.ewmElevationAccuracyDesc;
  }

  /**
   * @return the ewmElevationAccuracyActv
   */
  public String getEwmElevationAccuracyActv() {
    return this.ewmElevationAccuracyActv;
  }

  /**
   * @return the ewmElevationAccuracyOrder
   */
  public Integer getEwmElevationAccuracyOrder() {
    return this.ewmElevationAccuracyOrder;
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
   * @return the ewmElevationAccuracyCd
   */
  public String getEwmElevationAccuracyCd() {
    return this.ewmElevationAccuracyCd;
  }

  /**
   * @return the applId
   */
  public Long getApplId() {
    return this.applId;
  }

  //</editor-fold>
}