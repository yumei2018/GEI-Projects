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
  table = @EntityTable(name = "EWM_ELEV_MEASURE_METHOD_TYP",schema = "EWM_ADM")
  ,datasource = @EntityDataSource(name="EwmAdmDataSource",user = "ewm_adm",password = "Ewm95670!",url = "jdbc:oracle:thin:@localhost:1521:orcl")
)
public class ElevMeasureMethodTypBase<E extends ElevMeasureMethodTypBase> extends EntityBase<E> {

  //<editor-fold defaultstate="expanded" desc="Private Properties">
  
  /**
   *
   */
  @EntityColumn(name="EWM_ELEV_MEASURE_METHOD_TYP_ID",primary=true)
  private Long ewmElevMeasureMethodTypId;

  /**
   *
   */
  @EntityColumn(name="EWM_ELEV_MEASURE_METHOD_DESC")
  private String ewmElevMeasureMethodDesc;

  /**
   *
   */
  @EntityColumn(name="EWM_ELEV_MEASURE_METHOD_ACTV")
  private String ewmElevMeasureMethodActv;

  /**
   *
   */
  @EntityColumn(name="EWM_ELEV_MEASURE_METHOD_ORDER")
  private Integer ewmElevMeasureMethodOrder;

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
  @EntityColumn(name="EWM_ELEV_MEASURE_METHOD_CD")
  private String ewmElevMeasureMethodCd;

  /**
   *
   */
  @EntityColumn(name="APPL_ID")
  private Long applId;


  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    
    this.ewmElevMeasureMethodTypId = null;
    this.ewmElevMeasureMethodDesc = null;
    this.ewmElevMeasureMethodActv = null;
    this.ewmElevMeasureMethodOrder = null;
    this.modifiedDate = null;
    this.modifiedUser = null;
    this.modifiedProc = null;
    this.ewmElevMeasureMethodCd = null;
    this.applId = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters">
  
  /**
   * @param ewmElevMeasureMethodTypId the ewmElevMeasureMethodTypId to set
   */
  public E setEwmElevMeasureMethodTypId(Long ewmElevMeasureMethodTypId) {
    return this.set("ewmElevMeasureMethodTypId",ewmElevMeasureMethodTypId);
  }

  /**
   * @param ewmElevMeasureMethodTypId the ewmElevMeasureMethodTypId to set
   */
  public E setEwmElevMeasureMethodTypId(ConditionOperator op, Long ewmElevMeasureMethodTypId) {
    return this.addFilter(ConditionFilter.createInstance("ewmElevMeasureMethodTypId",op,ewmElevMeasureMethodTypId));
  }

  /**
   * @param ewmElevMeasureMethodDesc the ewmElevMeasureMethodDesc to set
   */
  public E setEwmElevMeasureMethodDesc(String ewmElevMeasureMethodDesc) {
    return this.set("ewmElevMeasureMethodDesc",ewmElevMeasureMethodDesc);
  }

  /**
   * @param ewmElevMeasureMethodDesc the ewmElevMeasureMethodDesc to set
   */
  public E setEwmElevMeasureMethodDesc(ConditionOperator op, String ewmElevMeasureMethodDesc) {
    return this.addFilter(ConditionFilter.createInstance("ewmElevMeasureMethodDesc",op,ewmElevMeasureMethodDesc));
  }

  /**
   * @param ewmElevMeasureMethodActv the ewmElevMeasureMethodActv to set
   */
  public E setEwmElevMeasureMethodActv(String ewmElevMeasureMethodActv) {
    return this.set("ewmElevMeasureMethodActv",ewmElevMeasureMethodActv);
  }

  /**
   * @param ewmElevMeasureMethodActv the ewmElevMeasureMethodActv to set
   */
  public E setEwmElevMeasureMethodActv(ConditionOperator op, String ewmElevMeasureMethodActv) {
    return this.addFilter(ConditionFilter.createInstance("ewmElevMeasureMethodActv",op,ewmElevMeasureMethodActv));
  }

  /**
   * @param ewmElevMeasureMethodOrder the ewmElevMeasureMethodOrder to set
   */
  public E setEwmElevMeasureMethodOrder(Integer ewmElevMeasureMethodOrder) {
    return this.set("ewmElevMeasureMethodOrder",ewmElevMeasureMethodOrder);
  }

  /**
   * @param ewmElevMeasureMethodOrder the ewmElevMeasureMethodOrder to set
   */
  public E setEwmElevMeasureMethodOrder(ConditionOperator op, Integer ewmElevMeasureMethodOrder) {
    return this.addFilter(ConditionFilter.createInstance("ewmElevMeasureMethodOrder",op,ewmElevMeasureMethodOrder));
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
   * @param ewmElevMeasureMethodCd the ewmElevMeasureMethodCd to set
   */
  public E setEwmElevMeasureMethodCd(String ewmElevMeasureMethodCd) {
    return this.set("ewmElevMeasureMethodCd",ewmElevMeasureMethodCd);
  }

  /**
   * @param ewmElevMeasureMethodCd the ewmElevMeasureMethodCd to set
   */
  public E setEwmElevMeasureMethodCd(ConditionOperator op, String ewmElevMeasureMethodCd) {
    return this.addFilter(ConditionFilter.createInstance("ewmElevMeasureMethodCd",op,ewmElevMeasureMethodCd));
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
   * @return the ewmElevMeasureMethodTypId
   */
  public Long getEwmElevMeasureMethodTypId() {
    return this.ewmElevMeasureMethodTypId;
  }

  /**
   * @return the ewmElevMeasureMethodDesc
   */
  public String getEwmElevMeasureMethodDesc() {
    return this.ewmElevMeasureMethodDesc;
  }

  /**
   * @return the ewmElevMeasureMethodActv
   */
  public String getEwmElevMeasureMethodActv() {
    return this.ewmElevMeasureMethodActv;
  }

  /**
   * @return the ewmElevMeasureMethodOrder
   */
  public Integer getEwmElevMeasureMethodOrder() {
    return this.ewmElevMeasureMethodOrder;
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
   * @return the ewmElevMeasureMethodCd
   */
  public String getEwmElevMeasureMethodCd() {
    return this.ewmElevMeasureMethodCd;
  }

  /**
   * @return the applId
   */
  public Long getApplId() {
    return this.applId;
  }

  //</editor-fold>
}