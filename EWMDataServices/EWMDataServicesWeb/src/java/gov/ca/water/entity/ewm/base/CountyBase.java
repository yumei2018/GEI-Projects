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
  table = @EntityTable(name = "COUNTY",schema = "BUS_ADM")
  ,datasource = @EntityDataSource(name="EwmAdmDataSource",user = "ewm_adm",password = "Ewm95670!",url = "jdbc:oracle:thin:@localhost:1521:orcl")
)
public class CountyBase<E extends CountyBase> extends EntityBase<E> {

  //<editor-fold defaultstate="expanded" desc="Private Properties">
  
  /**
   *
   */
  @EntityColumn(name="COUNTY_ID",primary=true)
  private Long countyId;

  /**
   *
   */
  @EntityColumn(name="COUNTY_NAME")
  private String countyName;

  /**
   *
   */
  @EntityColumn(name="COUNTY_ACTV")
  private String countyActv;

  /**
   *
   */
  @EntityColumn(name="COUNTY_ORDER")
  private Integer countyOrder;

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


  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    
    this.countyId = null;
    this.countyName = null;
    this.countyActv = null;
    this.countyOrder = null;
    this.modifiedDate = null;
    this.modifiedUser = null;
    this.modifiedProc = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters">
  
  /**
   * @param countyId the countyId to set
   */
  public E setCountyId(Long countyId) {
    return this.set("countyId",countyId);
  }

  /**
   * @param countyId the countyId to set
   */
  public E setCountyId(ConditionOperator op, Long countyId) {
    return this.addFilter(ConditionFilter.createInstance("countyId",op,countyId));
  }

  /**
   * @param countyName the countyName to set
   */
  public E setCountyName(String countyName) {
    return this.set("countyName",countyName);
  }

  /**
   * @param countyName the countyName to set
   */
  public E setCountyName(ConditionOperator op, String countyName) {
    return this.addFilter(ConditionFilter.createInstance("countyName",op,countyName));
  }

  /**
   * @param countyActv the countyActv to set
   */
  public E setCountyActv(String countyActv) {
    return this.set("countyActv",countyActv);
  }

  /**
   * @param countyActv the countyActv to set
   */
  public E setCountyActv(ConditionOperator op, String countyActv) {
    return this.addFilter(ConditionFilter.createInstance("countyActv",op,countyActv));
  }

  /**
   * @param countyOrder the countyOrder to set
   */
  public E setCountyOrder(Integer countyOrder) {
    return this.set("countyOrder",countyOrder);
  }

  /**
   * @param countyOrder the countyOrder to set
   */
  public E setCountyOrder(ConditionOperator op, Integer countyOrder) {
    return this.addFilter(ConditionFilter.createInstance("countyOrder",op,countyOrder));
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

  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Getters">
  /**
   * @return the countyId
   */
  public Long getCountyId() {
    return this.countyId;
  }

  /**
   * @return the countyName
   */
  public String getCountyName() {
    return this.countyName;
  }

  /**
   * @return the countyActv
   */
  public String getCountyActv() {
    return this.countyActv;
  }

  /**
   * @return the countyOrder
   */
  public Integer getCountyOrder() {
    return this.countyOrder;
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

  //</editor-fold>
}