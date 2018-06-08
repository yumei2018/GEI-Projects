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
  table = @EntityTable(name = "EWM_MEASUREMENT_METHOD_TYPE", schema = "EWM_ADM")
  ,datasource = @EntityDataSource(name="EwmAdmDataSource",user = "ewm",password = "Ewm95670!",url = "jdbc:oracle:thin:@localhost:1521:orcl")
)
public class MeasurementMethodTypeBase<E extends MeasurementMethodTypeBase> extends EntityBase<E> {

  //<editor-fold defaultstate="expanded" desc="Private Properties">
  
  /**
   *
   */
  @EntityColumn(name="EWM_MEASUREMENT_METHOD_TYPE_ID",primary=true)
  private Long ewmMeasurementMethodTypeId;

  /**
   *
   */
  @EntityColumn(name="EWM_MEASUREMENT_METHOD_DESC")
  private String ewmMeasurementMethodDesc;

  /**
   *
   */
  @EntityColumn(name="EWM_MEASUREMENT_METHOD_ACTV")
  private String ewmMeasurementMethodActv;

  /**
   *
   */
  @EntityColumn(name="EWM_MEASUREMENT_METHOD_ORDER")
  private Integer ewmMeasurementMethodOrder;

  /**
   *
   */
  @EntityColumn(name="EWM_MEASUREMENT_METHOD_PLANE")
  private String ewmMeasurementMethodPlane;

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
    
    this.ewmMeasurementMethodTypeId = null;
    this.ewmMeasurementMethodDesc = null;
    this.ewmMeasurementMethodActv = null;
    this.ewmMeasurementMethodOrder = null;
    this.ewmMeasurementMethodPlane = null;
    this.modifiedDate = null;
    this.modifiedUser = null;
    this.modifiedProc = null;
    this.applId = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters">
  
  /**
   * @param ewmMeasurementMethodTypeId the ewmMeasurementMethodTypeId to set
   */
  public E setEwmMeasurementMethodTypeId(Long ewmMeasurementMethodTypeId) {
    return this.set("ewmMeasurementMethodTypeId",ewmMeasurementMethodTypeId);
  }

  /**
   * @param ewmMeasurementMethodTypeId the ewmMeasurementMethodTypeId to set
   */
  public E setEwmMeasurementMethodTypeId(ConditionOperator op, Long ewmMeasurementMethodTypeId) {
    return this.addFilter(ConditionFilter.createInstance("ewmMeasurementMethodTypeId",op,ewmMeasurementMethodTypeId));
  }

  /**
   * @param ewmMeasurementMethodDesc the ewmMeasurementMethodDesc to set
   */
  public E setEwmMeasurementMethodDesc(String ewmMeasurementMethodDesc) {
    return this.set("ewmMeasurementMethodDesc",ewmMeasurementMethodDesc);
  }

  /**
   * @param ewmMeasurementMethodDesc the ewmMeasurementMethodDesc to set
   */
  public E setEwmMeasurementMethodDesc(ConditionOperator op, String ewmMeasurementMethodDesc) {
    return this.addFilter(ConditionFilter.createInstance("ewmMeasurementMethodDesc",op,ewmMeasurementMethodDesc));
  }

  /**
   * @param ewmMeasurementMethodActv the ewmMeasurementMethodActv to set
   */
  public E setEwmMeasurementMethodActv(String ewmMeasurementMethodActv) {
    return this.set("ewmMeasurementMethodActv",ewmMeasurementMethodActv);
  }

  /**
   * @param ewmMeasurementMethodActv the ewmMeasurementMethodActv to set
   */
  public E setEwmMeasurementMethodActv(ConditionOperator op, String ewmMeasurementMethodActv) {
    return this.addFilter(ConditionFilter.createInstance("ewmMeasurementMethodActv",op,ewmMeasurementMethodActv));
  }

  /**
   * @param ewmMeasurementMethodOrder the ewmMeasurementMethodOrder to set
   */
  public E setEwmMeasurementMethodOrder(Integer ewmMeasurementMethodOrder) {
    return this.set("ewmMeasurementMethodOrder",ewmMeasurementMethodOrder);
  }

  /**
   * @param ewmMeasurementMethodOrder the ewmMeasurementMethodOrder to set
   */
  public E setEwmMeasurementMethodOrder(ConditionOperator op, Integer ewmMeasurementMethodOrder) {
    return this.addFilter(ConditionFilter.createInstance("ewmMeasurementMethodOrder",op,ewmMeasurementMethodOrder));
  }

  /**
   * @param ewmMeasurementMethodPlane the ewmMeasurementMethodPlane to set
   */
  public E setEwmMeasurementMethodPlane(String ewmMeasurementMethodPlane) {
    return this.set("ewmMeasurementMethodPlane",ewmMeasurementMethodPlane);
  }

  /**
   * @param ewmMeasurementMethodPlane the ewmMeasurementMethodPlane to set
   */
  public E setEwmMeasurementMethodPlane(ConditionOperator op, String ewmMeasurementMethodPlane) {
    return this.addFilter(ConditionFilter.createInstance("ewmMeasurementMethodPlane",op,ewmMeasurementMethodPlane));
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
   * @return the ewmMeasurementMethodTypeId
   */
  public Long getEwmMeasurementMethodTypeId() {
    return this.ewmMeasurementMethodTypeId;
  }

  /**
   * @return the ewmMeasurementMethodDesc
   */
  public String getEwmMeasurementMethodDesc() {
    return this.ewmMeasurementMethodDesc;
  }

  /**
   * @return the ewmMeasurementMethodActv
   */
  public String getEwmMeasurementMethodActv() {
    return this.ewmMeasurementMethodActv;
  }

  /**
   * @return the ewmMeasurementMethodOrder
   */
  public Integer getEwmMeasurementMethodOrder() {
    return this.ewmMeasurementMethodOrder;
  }

  /**
   * @return the ewmMeasurementMethodPlane
   */
  public String getEwmMeasurementMethodPlane() {
    return this.ewmMeasurementMethodPlane;
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