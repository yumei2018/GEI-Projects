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
  table = @EntityTable(name = "EWM_BASIN", schema = "EWM_ADM")
  ,datasource = @EntityDataSource(name="EwmAdmDataSource",user = "ewm",password = "Ewm95670!",url = "jdbc:oracle:thin:@localhost:1521:orcl")
)
public class BasinBase<E extends BasinBase> extends EntityBase<E> {

  //<editor-fold defaultstate="expanded" desc="Private Properties">
  
  /**
   *
   */
  @EntityColumn(name="EWM_BASIN_ID",primary=true)
  private Long ewmBasinId;

  /**
   *
   */
  @EntityColumn(name="EWM_BASIN_CD")
  private String ewmBasinCd;

  /**
   *
   */
  @EntityColumn(name="EWM_BASIN_DESC")
  private String ewmBasinDesc;

  /**
   *
   */
  @EntityColumn(name="EWM_BASIN_ACTV")
  private String ewmBasinActv;

  /**
   *
   */
  @EntityColumn(name="EWM_BASIN_ORDER")
  private Integer ewmBasinOrder;

  /**
   *
   */
  @EntityColumn(name="EWM_BASIN_REGION_ID")
  private Long ewmBasinRegionId;

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
  @EntityColumn(name="REG_OFFICE_ID")
  private Long regOfficeId;

  /**
   *
   */
  @EntityColumn(name="BASIN_SHAPE")
  private Object basinShape;

  /**
   *
   */
  @EntityColumn(name="SYS_NC00012$")
  private Integer sysNc00012$;

  /**
   *
   */
  @EntityColumn(name="SYS_NC00013$")
  private Integer sysNc00013$;

  /**
   *
   */
  @EntityColumn(name="SYS_NC00014$")
  private Integer sysNc00014$;

  /**
   *
   */
  @EntityColumn(name="SYS_NC00015$")
  private Integer sysNc00015$;

  /**
   *
   */
  @EntityColumn(name="SYS_NC00016$")
  private Integer sysNc00016$;

  /**
   *
   */
  @EntityColumn(name="SYS_NC00017$")
  private Object sysNc00017$;

  /**
   *
   */
  @EntityColumn(name="SYS_NC00018$")
  private Object sysNc00018$;

  /**
   *
   */
  @EntityColumn(name="APPL_ID")
  private Long applId;


  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    
    this.ewmBasinId = null;
    this.ewmBasinCd = null;
    this.ewmBasinDesc = null;
    this.ewmBasinActv = null;
    this.ewmBasinOrder = null;
    this.ewmBasinRegionId = null;
    this.modifiedDate = null;
    this.modifiedUser = null;
    this.modifiedProc = null;
    this.regOfficeId = null;
    this.basinShape = null;
    this.sysNc00012$ = null;
    this.sysNc00013$ = null;
    this.sysNc00014$ = null;
    this.sysNc00015$ = null;
    this.sysNc00016$ = null;
    this.sysNc00017$ = null;
    this.sysNc00018$ = null;
    this.applId = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters">
  
  /**
   * @param ewmBasinId the ewmBasinId to set
   */
  public E setEwmBasinId(Long ewmBasinId) {
    return this.set("ewmBasinId",ewmBasinId);
  }

  /**
   * @param ewmBasinId the ewmBasinId to set
   */
  public E setEwmBasinId(ConditionOperator op, Long ewmBasinId) {
    return this.addFilter(ConditionFilter.createInstance("ewmBasinId",op,ewmBasinId));
  }

  /**
   * @param ewmBasinCd the ewmBasinCd to set
   */
  public E setEwmBasinCd(String ewmBasinCd) {
    return this.set("ewmBasinCd",ewmBasinCd);
  }

  /**
   * @param ewmBasinCd the ewmBasinCd to set
   */
  public E setEwmBasinCd(ConditionOperator op, String ewmBasinCd) {
    return this.addFilter(ConditionFilter.createInstance("ewmBasinCd",op,ewmBasinCd));
  }

  /**
   * @param ewmBasinDesc the ewmBasinDesc to set
   */
  public E setEwmBasinDesc(String ewmBasinDesc) {
    return this.set("ewmBasinDesc",ewmBasinDesc);
  }

  /**
   * @param ewmBasinDesc the ewmBasinDesc to set
   */
  public E setEwmBasinDesc(ConditionOperator op, String ewmBasinDesc) {
    return this.addFilter(ConditionFilter.createInstance("ewmBasinDesc",op,ewmBasinDesc));
  }

  /**
   * @param ewmBasinActv the ewmBasinActv to set
   */
  public E setEwmBasinActv(String ewmBasinActv) {
    return this.set("ewmBasinActv",ewmBasinActv);
  }

  /**
   * @param ewmBasinActv the ewmBasinActv to set
   */
  public E setEwmBasinActv(ConditionOperator op, String ewmBasinActv) {
    return this.addFilter(ConditionFilter.createInstance("ewmBasinActv",op,ewmBasinActv));
  }

  /**
   * @param ewmBasinOrder the ewmBasinOrder to set
   */
  public E setEwmBasinOrder(Integer ewmBasinOrder) {
    return this.set("ewmBasinOrder",ewmBasinOrder);
  }

  /**
   * @param ewmBasinOrder the ewmBasinOrder to set
   */
  public E setEwmBasinOrder(ConditionOperator op, Integer ewmBasinOrder) {
    return this.addFilter(ConditionFilter.createInstance("ewmBasinOrder",op,ewmBasinOrder));
  }

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
   * @param regOfficeId the regOfficeId to set
   */
  public E setRegOfficeId(Long regOfficeId) {
    return this.set("regOfficeId",regOfficeId);
  }

  /**
   * @param regOfficeId the regOfficeId to set
   */
  public E setRegOfficeId(ConditionOperator op, Long regOfficeId) {
    return this.addFilter(ConditionFilter.createInstance("regOfficeId",op,regOfficeId));
  }

  /**
   * @param basinShape the basinShape to set
   */
  public E setBasinShape(Object basinShape) {
    return this.set("basinShape",basinShape);
  }

  /**
   * @param basinShape the basinShape to set
   */
  public E setBasinShape(ConditionOperator op, Object basinShape) {
    return this.addFilter(ConditionFilter.createInstance("basinShape",op,basinShape));
  }

  /**
   * @param sysNc00012$ the sysNc00012$ to set
   */
  public E setSysNc00012$(Integer sysNc00012$) {
    return this.set("sysNc00012$",sysNc00012$);
  }

  /**
   * @param sysNc00012$ the sysNc00012$ to set
   */
  public E setSysNc00012$(ConditionOperator op, Integer sysNc00012$) {
    return this.addFilter(ConditionFilter.createInstance("sysNc00012$",op,sysNc00012$));
  }

  /**
   * @param sysNc00013$ the sysNc00013$ to set
   */
  public E setSysNc00013$(Integer sysNc00013$) {
    return this.set("sysNc00013$",sysNc00013$);
  }

  /**
   * @param sysNc00013$ the sysNc00013$ to set
   */
  public E setSysNc00013$(ConditionOperator op, Integer sysNc00013$) {
    return this.addFilter(ConditionFilter.createInstance("sysNc00013$",op,sysNc00013$));
  }

  /**
   * @param sysNc00014$ the sysNc00014$ to set
   */
  public E setSysNc00014$(Integer sysNc00014$) {
    return this.set("sysNc00014$",sysNc00014$);
  }

  /**
   * @param sysNc00014$ the sysNc00014$ to set
   */
  public E setSysNc00014$(ConditionOperator op, Integer sysNc00014$) {
    return this.addFilter(ConditionFilter.createInstance("sysNc00014$",op,sysNc00014$));
  }

  /**
   * @param sysNc00015$ the sysNc00015$ to set
   */
  public E setSysNc00015$(Integer sysNc00015$) {
    return this.set("sysNc00015$",sysNc00015$);
  }

  /**
   * @param sysNc00015$ the sysNc00015$ to set
   */
  public E setSysNc00015$(ConditionOperator op, Integer sysNc00015$) {
    return this.addFilter(ConditionFilter.createInstance("sysNc00015$",op,sysNc00015$));
  }

  /**
   * @param sysNc00016$ the sysNc00016$ to set
   */
  public E setSysNc00016$(Integer sysNc00016$) {
    return this.set("sysNc00016$",sysNc00016$);
  }

  /**
   * @param sysNc00016$ the sysNc00016$ to set
   */
  public E setSysNc00016$(ConditionOperator op, Integer sysNc00016$) {
    return this.addFilter(ConditionFilter.createInstance("sysNc00016$",op,sysNc00016$));
  }

  /**
   * @param sysNc00017$ the sysNc00017$ to set
   */
  public E setSysNc00017$(Object sysNc00017$) {
    return this.set("sysNc00017$",sysNc00017$);
  }

  /**
   * @param sysNc00017$ the sysNc00017$ to set
   */
  public E setSysNc00017$(ConditionOperator op, Object sysNc00017$) {
    return this.addFilter(ConditionFilter.createInstance("sysNc00017$",op,sysNc00017$));
  }

  /**
   * @param sysNc00018$ the sysNc00018$ to set
   */
  public E setSysNc00018$(Object sysNc00018$) {
    return this.set("sysNc00018$",sysNc00018$);
  }

  /**
   * @param sysNc00018$ the sysNc00018$ to set
   */
  public E setSysNc00018$(ConditionOperator op, Object sysNc00018$) {
    return this.addFilter(ConditionFilter.createInstance("sysNc00018$",op,sysNc00018$));
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
   * @return the ewmBasinId
   */
  public Long getEwmBasinId() {
    return this.ewmBasinId;
  }

  /**
   * @return the ewmBasinCd
   */
  public String getEwmBasinCd() {
    return this.ewmBasinCd;
  }

  /**
   * @return the ewmBasinDesc
   */
  public String getEwmBasinDesc() {
    return this.ewmBasinDesc;
  }

  /**
   * @return the ewmBasinActv
   */
  public String getEwmBasinActv() {
    return this.ewmBasinActv;
  }

  /**
   * @return the ewmBasinOrder
   */
  public Integer getEwmBasinOrder() {
    return this.ewmBasinOrder;
  }

  /**
   * @return the ewmBasinRegionId
   */
  public Long getEwmBasinRegionId() {
    return this.ewmBasinRegionId;
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
   * @return the regOfficeId
   */
  public Long getRegOfficeId() {
    return this.regOfficeId;
  }

  /**
   * @return the basinShape
   */
  public Object getBasinShape() {
    return this.basinShape;
  }

  /**
   * @return the sysNc00012$
   */
  public Integer getSysNc00012$() {
    return this.sysNc00012$;
  }

  /**
   * @return the sysNc00013$
   */
  public Integer getSysNc00013$() {
    return this.sysNc00013$;
  }

  /**
   * @return the sysNc00014$
   */
  public Integer getSysNc00014$() {
    return this.sysNc00014$;
  }

  /**
   * @return the sysNc00015$
   */
  public Integer getSysNc00015$() {
    return this.sysNc00015$;
  }

  /**
   * @return the sysNc00016$
   */
  public Integer getSysNc00016$() {
    return this.sysNc00016$;
  }

  /**
   * @return the sysNc00017$
   */
  public Object getSysNc00017$() {
    return this.sysNc00017$;
  }

  /**
   * @return the sysNc00018$
   */
  public Object getSysNc00018$() {
    return this.sysNc00018$;
  }

  /**
   * @return the applId
   */
  public Long getApplId() {
    return this.applId;
  }

  //</editor-fold>
}