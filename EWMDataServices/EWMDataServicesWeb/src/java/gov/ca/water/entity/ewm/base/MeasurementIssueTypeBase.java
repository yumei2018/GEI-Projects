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
  table = @EntityTable(name = "EWM_MEASUREMENT_ISSUE_TYPE",schema = "EWM_ADM")
  ,datasource = @EntityDataSource(name="EwmAdmDataSource",user = "ewm_adm",password = "Ewm95670!",url = "jdbc:oracle:thin:@localhost:1521:orcl")
)
public class MeasurementIssueTypeBase<E extends MeasurementIssueTypeBase> extends EntityBase<E> {

  //<editor-fold defaultstate="expanded" desc="Private Properties">
  
  /**
   *
   */
  @EntityColumn(name="EWM_MEASUREMENT_ISSUE_TYPE_ID",primary=true)
  private Long ewmMeasurementIssueTypeId;

  /**
   *
   */
  @EntityColumn(name="EWM_MEASURE_ISSUE_TYPE_CODE")
  private String ewmMeasureIssueTypeCode;

  /**
   *
   */
  @EntityColumn(name="EWM_MEASURE_ISSUE_TYPE_DESC")
  private String ewmMeasureIssueTypeDesc;

  /**
   *
   */
  @EntityColumn(name="EWM_MEASURE_ISSUE_TYPE_ACTV")
  private String ewmMeasureIssueTypeActv;

  /**
   *
   */
  @EntityColumn(name="EWM_MEASURE_ISSUE_TYPE_ORDER")
  private Integer ewmMeasureIssueTypeOrder;

  /**
   *
   */
  @EntityColumn(name="EWM_MEASURE_ISSUE_TYPE_CLASS")
  private String ewmMeasureIssueTypeClass;

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
    
    this.ewmMeasurementIssueTypeId = null;
    this.ewmMeasureIssueTypeCode = null;
    this.ewmMeasureIssueTypeDesc = null;
    this.ewmMeasureIssueTypeActv = null;
    this.ewmMeasureIssueTypeOrder = null;
    this.ewmMeasureIssueTypeClass = null;
    this.modifiedDate = null;
    this.modifiedUser = null;
    this.modifiedProc = null;
    this.applId = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters">
  
  /**
   * @param ewmMeasurementIssueTypeId the ewmMeasurementIssueTypeId to set
   */
  public E setEwmMeasurementIssueTypeId(Long ewmMeasurementIssueTypeId) {
    return this.set("ewmMeasurementIssueTypeId",ewmMeasurementIssueTypeId);
  }

  /**
   * @param ewmMeasurementIssueTypeId the ewmMeasurementIssueTypeId to set
   */
  public E setEwmMeasurementIssueTypeId(ConditionOperator op, Long ewmMeasurementIssueTypeId) {
    return this.addFilter(ConditionFilter.createInstance("ewmMeasurementIssueTypeId",op,ewmMeasurementIssueTypeId));
  }

  /**
   * @param ewmMeasureIssueTypeCode the ewmMeasureIssueTypeCode to set
   */
  public E setEwmMeasureIssueTypeCode(String ewmMeasureIssueTypeCode) {
    return this.set("ewmMeasureIssueTypeCode",ewmMeasureIssueTypeCode);
  }

  /**
   * @param ewmMeasureIssueTypeCode the ewmMeasureIssueTypeCode to set
   */
  public E setEwmMeasureIssueTypeCode(ConditionOperator op, String ewmMeasureIssueTypeCode) {
    return this.addFilter(ConditionFilter.createInstance("ewmMeasureIssueTypeCode",op,ewmMeasureIssueTypeCode));
  }

  /**
   * @param ewmMeasureIssueTypeDesc the ewmMeasureIssueTypeDesc to set
   */
  public E setEwmMeasureIssueTypeDesc(String ewmMeasureIssueTypeDesc) {
    return this.set("ewmMeasureIssueTypeDesc",ewmMeasureIssueTypeDesc);
  }

  /**
   * @param ewmMeasureIssueTypeDesc the ewmMeasureIssueTypeDesc to set
   */
  public E setEwmMeasureIssueTypeDesc(ConditionOperator op, String ewmMeasureIssueTypeDesc) {
    return this.addFilter(ConditionFilter.createInstance("ewmMeasureIssueTypeDesc",op,ewmMeasureIssueTypeDesc));
  }

  /**
   * @param ewmMeasureIssueTypeActv the ewmMeasureIssueTypeActv to set
   */
  public E setEwmMeasureIssueTypeActv(String ewmMeasureIssueTypeActv) {
    return this.set("ewmMeasureIssueTypeActv",ewmMeasureIssueTypeActv);
  }

  /**
   * @param ewmMeasureIssueTypeActv the ewmMeasureIssueTypeActv to set
   */
  public E setEwmMeasureIssueTypeActv(ConditionOperator op, String ewmMeasureIssueTypeActv) {
    return this.addFilter(ConditionFilter.createInstance("ewmMeasureIssueTypeActv",op,ewmMeasureIssueTypeActv));
  }

  /**
   * @param ewmMeasureIssueTypeOrder the ewmMeasureIssueTypeOrder to set
   */
  public E setEwmMeasureIssueTypeOrder(Integer ewmMeasureIssueTypeOrder) {
    return this.set("ewmMeasureIssueTypeOrder",ewmMeasureIssueTypeOrder);
  }

  /**
   * @param ewmMeasureIssueTypeOrder the ewmMeasureIssueTypeOrder to set
   */
  public E setEwmMeasureIssueTypeOrder(ConditionOperator op, Integer ewmMeasureIssueTypeOrder) {
    return this.addFilter(ConditionFilter.createInstance("ewmMeasureIssueTypeOrder",op,ewmMeasureIssueTypeOrder));
  }

  /**
   * @param ewmMeasureIssueTypeClass the ewmMeasureIssueTypeClass to set
   */
  public E setEwmMeasureIssueTypeClass(String ewmMeasureIssueTypeClass) {
    return this.set("ewmMeasureIssueTypeClass",ewmMeasureIssueTypeClass);
  }

  /**
   * @param ewmMeasureIssueTypeClass the ewmMeasureIssueTypeClass to set
   */
  public E setEwmMeasureIssueTypeClass(ConditionOperator op, String ewmMeasureIssueTypeClass) {
    return this.addFilter(ConditionFilter.createInstance("ewmMeasureIssueTypeClass",op,ewmMeasureIssueTypeClass));
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
   * @return the ewmMeasurementIssueTypeId
   */
  public Long getEwmMeasurementIssueTypeId() {
    return this.ewmMeasurementIssueTypeId;
  }

  /**
   * @return the ewmMeasureIssueTypeCode
   */
  public String getEwmMeasureIssueTypeCode() {
    return this.ewmMeasureIssueTypeCode;
  }

  /**
   * @return the ewmMeasureIssueTypeDesc
   */
  public String getEwmMeasureIssueTypeDesc() {
    return this.ewmMeasureIssueTypeDesc;
  }

  /**
   * @return the ewmMeasureIssueTypeActv
   */
  public String getEwmMeasureIssueTypeActv() {
    return this.ewmMeasureIssueTypeActv;
  }

  /**
   * @return the ewmMeasureIssueTypeOrder
   */
  public Integer getEwmMeasureIssueTypeOrder() {
    return this.ewmMeasureIssueTypeOrder;
  }

  /**
   * @return the ewmMeasureIssueTypeClass
   */
  public String getEwmMeasureIssueTypeClass() {
    return this.ewmMeasureIssueTypeClass;
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