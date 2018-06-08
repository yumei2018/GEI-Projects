package com.gei.entities.base;

import entity.core.EntityBase;
import entity.core.annotation.*;
import entity.core.filter.ConditionFilter;
import entity.core.filter.ConditionFilter.ConditionOperator;
import java.sql.Timestamp;


/**
 * This class is auto generated
 */
@EntityDef(
  table = @EntityTable(name = "SGMA_BASINMOD_USER")
  ,datasource = @EntityDataSource(name="SgmaDataSource",user = "sgma",password = "sgma#95670",url = "jdbc:oracle:thin:@localhost:1521:orcl")
)
public class UserBase<E extends UserBase> extends EntityBase<E> {

  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  
  /**
   *
   */
  @EntityColumn(name="USER_ID",primary=true,autoId=true)
  private Long userId;

  /**
   *
   */
  @EntityColumn(name="AGENCY_KEY")
  private Long agencyKey;

  /**
   *
   */
  @EntityColumn(name="CONTACT_ID")
  private Long contactId;

  /**
   *
   */
  @EntityColumn(name="USERNAME")
  private String username;

  /**
   *
   */
  @EntityColumn(name="PASSWORD")
  private String password;

  /**
   *
   */
  @EntityColumn(name="USER_TYPE")
  private String userType;

  /**
   *
   */
  @EntityColumn(name="ACTIVE")
  private Integer active;

  /**
   *
   */
  @EntityColumn(name="LASTLOGIN_DATETIME")
  private Timestamp lastloginDatetime;

  /**
   *
   */
  @EntityColumn(name="LOGIN_COUNT")
  private Long loginCount;

  /**
   *
   */
  @EntityColumn(name="RECOVERY_HASH")
  private String recoveryHash;

  /**
   *
   */
  @EntityColumn(name="RECOVERY_DT")
  private Timestamp recoveryDt;

  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters">
  
  /**
   * @param userId the userId to set
   */
  public E setUserId(Long userId) {
    return this.set("userId",userId);
  }

  /**
   * @param userId the userId to set
   */
  public E setUserId(ConditionOperator op, Long userId) {
    return this.addFilter(ConditionFilter.createInstance("userId",op,userId));
  }

  /**
   * @param agencyKey the agencyKey to set
   */
  public E setAgencyKey(Long agencyKey) {
    return this.set("agencyKey",agencyKey);
  }

  /**
   * @param agencyKey the agencyKey to set
   */
  public E setAgencyKey(ConditionOperator op, Long agencyKey) {
    return this.addFilter(ConditionFilter.createInstance("agencyKey",op,agencyKey));
  }

  /**
   * @param contactId the contactId to set
   */
  public E setContactId(Long contactId) {
    return this.set("contactId",contactId);
  }

  /**
   * @param contactId the contactId to set
   */
  public E setContactId(ConditionOperator op, Long contactId) {
    return this.addFilter(ConditionFilter.createInstance("contactId",op,contactId));
  }

  /**
   * @param username the username to set
   */
  public E setUsername(String username) {
    return this.set("username",username);
  }

  /**
   * @param username the username to set
   */
  public E setUsername(ConditionOperator op, String username) {
    return this.addFilter(ConditionFilter.createInstance("username",op,username));
  }

  /**
   * @param password the password to set
   */
  public E setPassword(String password) {
    return this.set("password",password);
  }

  /**
   * @param password the password to set
   */
  public E setPassword(ConditionOperator op, String password) {
    return this.addFilter(ConditionFilter.createInstance("password",op,password));
  }

  /**
   * @param userType the userType to set
   */
  public E setUserType(String userType) {
    return this.set("userType",userType);
  }

  /**
   * @param userType the userType to set
   */
  public E setUserType(ConditionOperator op, String userType) {
    return this.addFilter(ConditionFilter.createInstance("userType",op,userType));
  }

  /**
   * @param active the active to set
   */
  public E setActive(Integer active) {
    return this.set("active",active);
  }

  /**
   * @param active the active to set
   */
  public E setActive(ConditionOperator op, Integer active) {
    return this.addFilter(ConditionFilter.createInstance("active",op,active));
  }

  /**
   * @param lastloginDatetime the lastloginDatetime to set
   */
  public E setLastloginDatetime(Timestamp lastloginDatetime) {
    return this.set("lastloginDatetime",lastloginDatetime);
  }

  /**
   * @param lastloginDatetime the lastloginDatetime to set
   */
  public E setLastloginDatetime(ConditionOperator op, Timestamp lastloginDatetime) {
    return this.addFilter(ConditionFilter.createInstance("lastloginDatetime",op,lastloginDatetime));
  }

  /**
   * @param loginCount the loginCount to set
   */
  public E setLoginCount(Long loginCount) {
    return this.set("loginCount",loginCount);
  }

  /**
   * @param loginCount the loginCount to set
   */
  public E setLoginCount(ConditionOperator op, Long loginCount) {
    return this.addFilter(ConditionFilter.createInstance("loginCount",op,loginCount));
  }

  /**
   * @param recoveryHash the recoveryHash to set
   */
  public E setRecoveryHash(String recoveryHash) {
    return this.set("recoveryHash",recoveryHash);
  }

  /**
   * @param recoveryHash the recoveryHash to set
   */
  public E setRecoveryHash(ConditionOperator op, String recoveryHash) {
    return this.addFilter(ConditionFilter.createInstance("recoveryHash",op,recoveryHash));
  }

  /**
   * @param recoveryDt the recoveryDt to set
   */
  public E setRecoveryDt(Timestamp recoveryDt) {
    return this.set("recoveryDt",recoveryDt);
  }

  /**
   * @param recoveryDt the recoveryDt to set
   */
  public E setRecoveryDt(ConditionOperator op, Timestamp recoveryDt) {
    return this.addFilter(ConditionFilter.createInstance("recoveryDt",op,recoveryDt));
  }

  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Getters">
  /**
   * @return the userId
   */
  public Long getUserId() {
    return userId;
  }

  /**
   * @return the agencyKey
   */
  public Long getAgencyKey() {
    return agencyKey;
  }

  /**
   * @return the contactId
   */
  public Long getContactId() {
    return contactId;
  }

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @return the userType
   */
  public String getUserType() {
    return userType;
  }

  /**
   * @return the active
   */
  public Integer getActive() {
    return active;
  }

  /**
   * @return the lastloginDatetime
   */
  public Timestamp getLastloginDatetime() {
    return lastloginDatetime;
  }

  /**
   * @return the loginCount
   */
  public Long getLoginCount() {
    return loginCount;
  }

  /**
   * @return the recoveryHash
   */
  public String getRecoveryHash() {
    return recoveryHash;
  }

  /**
   * @return the recoveryDt
   */
  public Timestamp getRecoveryDt() {
    return recoveryDt;
  }

  //</editor-fold>
}