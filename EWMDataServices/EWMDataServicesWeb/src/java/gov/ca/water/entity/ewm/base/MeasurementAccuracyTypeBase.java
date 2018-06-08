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
  table = @EntityTable(name = "EWM_MEASUREMENT_ACCURACY_TYPE",schema = "EWM_ADM")
  ,datasource = @EntityDataSource(name="EwmAdmDataSource",user = "ewm_adm",password = "Ewm95670!",url = "jdbc:oracle:thin:@localhost:1521:orcl")
)
public class MeasurementAccuracyTypeBase<E extends MeasurementAccuracyTypeBase> extends EntityBase<E> {

  //<editor-fold defaultstate="expanded" desc="Private Properties">
  
  /**
   *
   */
  @EntityColumn(name="EWM_MEASURE_ACCURACY_TYPE_ID",primary=true)
  private Long ewmMeasureAccuracyTypeId;

  /**
   *
   */
  @EntityColumn(name="EWM_MEASUREMENT_ACCURACY_DESC")
  private String ewmMeasurementAccuracyDesc;

  /**
   *
   */
  @EntityColumn(name="EWM_MEASUREMENT_ACCURACY_ACTV")
  private String ewmMeasurementAccuracyActv;

  /**
   *
   */
  @EntityColumn(name="EWM_MEASUREMENT_ACCURACY_ORDER")
  private Integer ewmMeasurementAccuracyOrder;

  /**
   *
   */
  @EntityColumn(name="EWM_MEASUREMENT_ACCURACY_PLANE")
  private String ewmMeasurementAccuracyPlane;

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
    
    this.ewmMeasureAccuracyTypeId = null;
    this.ewmMeasurementAccuracyDesc = null;
    this.ewmMeasurementAccuracyActv = null;
    this.ewmMeasurementAccuracyOrder = null;
    this.ewmMeasurementAccuracyPlane = null;
    this.modifiedDate = null;
    this.modifiedUser = null;
    this.modifiedProc = null;
    this.applId = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters">
  
  /**
   * @param ewmMeasureAccuracyTypeId the ewmMeasureAccuracyTypeId to set
   */
  public E setEwmMeasureAccuracyTypeId(Long ewmMeasureAccuracyTypeId) {
    return this.set("ewmMeasureAccuracyTypeId",ewmMeasureAccuracyTypeId);
  }

  /**
   * @param ewmMeasureAccuracyTypeId the ewmMeasureAccuracyTypeId to set
   */
  public E setEwmMeasureAccuracyTypeId(ConditionOperator op, Long ewmMeasureAccuracyTypeId) {
    return this.addFilter(ConditionFilter.createInstance("ewmMeasureAccuracyTypeId",op,ewmMeasureAccuracyTypeId));
  }

  /**
   * @param ewmMeasurementAccuracyDesc the ewmMeasurementAccuracyDesc to set
   */
  public E setEwmMeasurementAccuracyDesc(String ewmMeasurementAccuracyDesc) {
    return this.set("ewmMeasurementAccuracyDesc",ewmMeasurementAccuracyDesc);
  }

  /**
   * @param ewmMeasurementAccuracyDesc the ewmMeasurementAccuracyDesc to set
   */
  public E setEwmMeasurementAccuracyDesc(ConditionOperator op, String ewmMeasurementAccuracyDesc) {
    return this.addFilter(ConditionFilter.createInstance("ewmMeasurementAccuracyDesc",op,ewmMeasurementAccuracyDesc));
  }

  /**
   * @param ewmMeasurementAccuracyActv the ewmMeasurementAccuracyActv to set
   */
  public E setEwmMeasurementAccuracyActv(String ewmMeasurementAccuracyActv) {
    return this.set("ewmMeasurementAccuracyActv",ewmMeasurementAccuracyActv);
  }

  /**
   * @param ewmMeasurementAccuracyActv the ewmMeasurementAccuracyActv to set
   */
  public E setEwmMeasurementAccuracyActv(ConditionOperator op, String ewmMeasurementAccuracyActv) {
    return this.addFilter(ConditionFilter.createInstance("ewmMeasurementAccuracyActv",op,ewmMeasurementAccuracyActv));
  }

  /**
   * @param ewmMeasurementAccuracyOrder the ewmMeasurementAccuracyOrder to set
   */
  public E setEwmMeasurementAccuracyOrder(Integer ewmMeasurementAccuracyOrder) {
    return this.set("ewmMeasurementAccuracyOrder",ewmMeasurementAccuracyOrder);
  }

  /**
   * @param ewmMeasurementAccuracyOrder the ewmMeasurementAccuracyOrder to set
   */
  public E setEwmMeasurementAccuracyOrder(ConditionOperator op, Integer ewmMeasurementAccuracyOrder) {
    return this.addFilter(ConditionFilter.createInstance("ewmMeasurementAccuracyOrder",op,ewmMeasurementAccuracyOrder));
  }

  /**
   * @param ewmMeasurementAccuracyPlane the ewmMeasurementAccuracyPlane to set
   */
  public E setEwmMeasurementAccuracyPlane(String ewmMeasurementAccuracyPlane) {
    return this.set("ewmMeasurementAccuracyPlane",ewmMeasurementAccuracyPlane);
  }

  /**
   * @param ewmMeasurementAccuracyPlane the ewmMeasurementAccuracyPlane to set
   */
  public E setEwmMeasurementAccuracyPlane(ConditionOperator op, String ewmMeasurementAccuracyPlane) {
    return this.addFilter(ConditionFilter.createInstance("ewmMeasurementAccuracyPlane",op,ewmMeasurementAccuracyPlane));
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
   * @return the ewmMeasureAccuracyTypeId
   */
  public Long getEwmMeasureAccuracyTypeId() {
    return this.ewmMeasureAccuracyTypeId;
  }

  /**
   * @return the ewmMeasurementAccuracyDesc
   */
  public String getEwmMeasurementAccuracyDesc() {
    return this.ewmMeasurementAccuracyDesc;
  }

  /**
   * @return the ewmMeasurementAccuracyActv
   */
  public String getEwmMeasurementAccuracyActv() {
    return this.ewmMeasurementAccuracyActv;
  }

  /**
   * @return the ewmMeasurementAccuracyOrder
   */
  public Integer getEwmMeasurementAccuracyOrder() {
    return this.ewmMeasurementAccuracyOrder;
  }

  /**
   * @return the ewmMeasurementAccuracyPlane
   */
  public String getEwmMeasurementAccuracyPlane() {
    return this.ewmMeasurementAccuracyPlane;
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