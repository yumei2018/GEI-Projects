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
  table = @EntityTable(name = "EWM_WELL_ORGANIZATION",schema = "EWM_ADM")
  ,datasource = @EntityDataSource(name="EwmAdmDataSource",user = "ewm_adm",password = "Ewm95670!",url = "jdbc:oracle:thin:@localhost:1521:orcl")
)
public class OrganizationBase<E extends OrganizationBase> extends EntityBase<E> {

  //<editor-fold defaultstate="expanded" desc="Private Properties">
  
  /**
   *
   */
  @EntityColumn(name="ORG_ID",primary=true)
  private Long orgId;

  /**
   *
   */
  @EntityColumn(name="ORG_NAME")
  private String orgName;

  /**
   *
   */
  @EntityColumn(name="ORG_ABBR")
  private String orgAbbr;

  /**
   *
   */
  @EntityColumn(name="ORG_TYPE_ID")
  private Long orgTypeId;

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
  @EntityColumn(name="ORG_TIN")
  private Integer orgTin;


  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    
    this.orgId = null;
    this.orgName = null;
    this.orgAbbr = null;
    this.orgTypeId = null;
    this.modifiedDate = null;
    this.modifiedUser = null;
    this.modifiedProc = null;
    this.orgTin = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters">
  
  /**
   * @param orgId the orgId to set
   */
  public E setOrgId(Long orgId) {
    return this.set("orgId",orgId);
  }

  /**
   * @param orgId the orgId to set
   */
  public E setOrgId(ConditionOperator op, Long orgId) {
    return this.addFilter(ConditionFilter.createInstance("orgId",op,orgId));
  }

  /**
   * @param orgName the orgName to set
   */
  public E setOrgName(String orgName) {
    return this.set("orgName",orgName);
  }

  /**
   * @param orgName the orgName to set
   */
  public E setOrgName(ConditionOperator op, String orgName) {
    return this.addFilter(ConditionFilter.createInstance("orgName",op,orgName));
  }

  /**
   * @param orgAbbr the orgAbbr to set
   */
  public E setOrgAbbr(String orgAbbr) {
    return this.set("orgAbbr",orgAbbr);
  }

  /**
   * @param orgAbbr the orgAbbr to set
   */
  public E setOrgAbbr(ConditionOperator op, String orgAbbr) {
    return this.addFilter(ConditionFilter.createInstance("orgAbbr",op,orgAbbr));
  }

  /**
   * @param orgTypeId the orgTypeId to set
   */
  public E setOrgTypeId(Long orgTypeId) {
    return this.set("orgTypeId",orgTypeId);
  }

  /**
   * @param orgTypeId the orgTypeId to set
   */
  public E setOrgTypeId(ConditionOperator op, Long orgTypeId) {
    return this.addFilter(ConditionFilter.createInstance("orgTypeId",op,orgTypeId));
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
   * @param orgTin the orgTin to set
   */
  public E setOrgTin(Integer orgTin) {
    return this.set("orgTin",orgTin);
  }

  /**
   * @param orgTin the orgTin to set
   */
  public E setOrgTin(ConditionOperator op, Integer orgTin) {
    return this.addFilter(ConditionFilter.createInstance("orgTin",op,orgTin));
  }

  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Getters">
  /**
   * @return the orgId
   */
  public Long getOrgId() {
    return this.orgId;
  }

  /**
   * @return the orgName
   */
  public String getOrgName() {
    return this.orgName;
  }

  /**
   * @return the orgAbbr
   */
  public String getOrgAbbr() {
    return this.orgAbbr;
  }

  /**
   * @return the orgTypeId
   */
  public Long getOrgTypeId() {
    return this.orgTypeId;
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
   * @return the orgTin
   */
  public Integer getOrgTin() {
    return this.orgTin;
  }

  //</editor-fold>
}