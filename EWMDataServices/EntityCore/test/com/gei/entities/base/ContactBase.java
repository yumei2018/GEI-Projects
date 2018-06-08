package com.gei.entities.base;

import entity.core.EntityBase;
import entity.core.annotation.*;
import entity.core.filter.ConditionFilter;
import entity.core.filter.ConditionFilter.ConditionOperator;
import java.sql.Date;


/**
 * This class is auto generated
 */
@EntityDef(
  table = @EntityTable(name = "SGMA_BASINMOD_CONTACT")
  ,datasource = @EntityDataSource(name="SgmaDataSource",user = "sgma",password = "sgma#95670",url = "jdbc:oracle:thin:@localhost:1521:orcl")
)
public class ContactBase<E extends ContactBase> extends EntityBase<E> {

  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  
  /**
   *
   */
  @EntityColumn(name="CONTACT_ID",primary=true,autoId=true)
  private Long contactId;

  /**
   *
   */
  @EntityColumn(name="AGENCY_KEY")
  private Long agencyKey;

  /**
   *
   */
  @EntityColumn(name="NAME")
  private String name;

  /**
   *
   */
  @EntityColumn(name="ADDRESS")
  private String address;

  /**
   *
   */
  @EntityColumn(name="CITY")
  private String city;

  /**
   *
   */
  @EntityColumn(name="STATE")
  private String state;

  /**
   *
   */
  @EntityColumn(name="ZIP")
  private String zip;

  /**
   *
   */
  @EntityColumn(name="PHONE_WORK")
  private String phoneWork;

  /**
   *
   */
  @EntityColumn(name="PHONE_CELL")
  private String phoneCell;

  /**
   *
   */
  @EntityColumn(name="EMAIL")
  private String email;

  /**
   *
   */
  @EntityColumn(name="FAX")
  private String fax;

  /**
   *
   */
  @EntityColumn(name="CREATEDBY_ID")
  private Long createdbyId;

  /**
   *
   */
  @EntityColumn(name="CREATED_DATETIME")
  private Date createdDatetime;

  /**
   *
   */
  @EntityColumn(name="LASTUPDATEDBY_ID")
  private Long lastupdatedbyId;

  /**
   *
   */
  @EntityColumn(name="LASTUPDATED_DATETIME")
  private Date lastupdatedDatetime;

  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters">
  
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
   * @param name the name to set
   */
  public E setName(String name) {
    return this.set("name",name);
  }

  /**
   * @param name the name to set
   */
  public E setName(ConditionOperator op, String name) {
    return this.addFilter(ConditionFilter.createInstance("name",op,name));
  }

  /**
   * @param address the address to set
   */
  public E setAddress(String address) {
    return this.set("address",address);
  }

  /**
   * @param address the address to set
   */
  public E setAddress(ConditionOperator op, String address) {
    return this.addFilter(ConditionFilter.createInstance("address",op,address));
  }

  /**
   * @param city the city to set
   */
  public E setCity(String city) {
    return this.set("city",city);
  }

  /**
   * @param city the city to set
   */
  public E setCity(ConditionOperator op, String city) {
    return this.addFilter(ConditionFilter.createInstance("city",op,city));
  }

  /**
   * @param state the state to set
   */
  public E setState(String state) {
    return this.set("state",state);
  }

  /**
   * @param state the state to set
   */
  public E setState(ConditionOperator op, String state) {
    return this.addFilter(ConditionFilter.createInstance("state",op,state));
  }

  /**
   * @param zip the zip to set
   */
  public E setZip(String zip) {
    return this.set("zip",zip);
  }

  /**
   * @param zip the zip to set
   */
  public E setZip(ConditionOperator op, String zip) {
    return this.addFilter(ConditionFilter.createInstance("zip",op,zip));
  }

  /**
   * @param phoneWork the phoneWork to set
   */
  public E setPhoneWork(String phoneWork) {
    return this.set("phoneWork",phoneWork);
  }

  /**
   * @param phoneWork the phoneWork to set
   */
  public E setPhoneWork(ConditionOperator op, String phoneWork) {
    return this.addFilter(ConditionFilter.createInstance("phoneWork",op,phoneWork));
  }

  /**
   * @param phoneCell the phoneCell to set
   */
  public E setPhoneCell(String phoneCell) {
    return this.set("phoneCell",phoneCell);
  }

  /**
   * @param phoneCell the phoneCell to set
   */
  public E setPhoneCell(ConditionOperator op, String phoneCell) {
    return this.addFilter(ConditionFilter.createInstance("phoneCell",op,phoneCell));
  }

  /**
   * @param email the email to set
   */
  public E setEmail(String email) {
    return this.set("email",email);
  }

  /**
   * @param email the email to set
   */
  public E setEmail(ConditionOperator op, String email) {
    return this.addFilter(ConditionFilter.createInstance("email",op,email));
  }

  /**
   * @param fax the fax to set
   */
  public E setFax(String fax) {
    return this.set("fax",fax);
  }

  /**
   * @param fax the fax to set
   */
  public E setFax(ConditionOperator op, String fax) {
    return this.addFilter(ConditionFilter.createInstance("fax",op,fax));
  }

  /**
   * @param createdbyId the createdbyId to set
   */
  public E setCreatedbyId(Long createdbyId) {
    return this.set("createdbyId",createdbyId);
  }

  /**
   * @param createdbyId the createdbyId to set
   */
  public E setCreatedbyId(ConditionOperator op, Long createdbyId) {
    return this.addFilter(ConditionFilter.createInstance("createdbyId",op,createdbyId));
  }

  /**
   * @param createdDatetime the createdDatetime to set
   */
  public E setCreatedDatetime(Date createdDatetime) {
    return this.set("createdDatetime",createdDatetime);
  }

  /**
   * @param createdDatetime the createdDatetime to set
   */
  public E setCreatedDatetime(ConditionOperator op, Date createdDatetime) {
    return this.addFilter(ConditionFilter.createInstance("createdDatetime",op,createdDatetime));
  }

  /**
   * @param lastupdatedbyId the lastupdatedbyId to set
   */
  public E setLastupdatedbyId(Long lastupdatedbyId) {
    return this.set("lastupdatedbyId",lastupdatedbyId);
  }

  /**
   * @param lastupdatedbyId the lastupdatedbyId to set
   */
  public E setLastupdatedbyId(ConditionOperator op, Long lastupdatedbyId) {
    return this.addFilter(ConditionFilter.createInstance("lastupdatedbyId",op,lastupdatedbyId));
  }

  /**
   * @param lastupdatedDatetime the lastupdatedDatetime to set
   */
  public E setLastupdatedDatetime(Date lastupdatedDatetime) {
    return this.set("lastupdatedDatetime",lastupdatedDatetime);
  }

  /**
   * @param lastupdatedDatetime the lastupdatedDatetime to set
   */
  public E setLastupdatedDatetime(ConditionOperator op, Date lastupdatedDatetime) {
    return this.addFilter(ConditionFilter.createInstance("lastupdatedDatetime",op,lastupdatedDatetime));
  }

  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Getters">
  /**
   * @return the contactId
   */
  public Long getContactId() {
    return contactId;
  }

  /**
   * @return the agencyKey
   */
  public Long getAgencyKey() {
    return agencyKey;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * @return the state
   */
  public String getState() {
    return state;
  }

  /**
   * @return the zip
   */
  public String getZip() {
    return zip;
  }

  /**
   * @return the phoneWork
   */
  public String getPhoneWork() {
    return phoneWork;
  }

  /**
   * @return the phoneCell
   */
  public String getPhoneCell() {
    return phoneCell;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @return the fax
   */
  public String getFax() {
    return fax;
  }

  /**
   * @return the createdbyId
   */
  public Long getCreatedbyId() {
    return createdbyId;
  }

  /**
   * @return the createdDatetime
   */
  public Date getCreatedDatetime() {
    return createdDatetime;
  }

  /**
   * @return the lastupdatedbyId
   */
  public Long getLastupdatedbyId() {
    return lastupdatedbyId;
  }

  /**
   * @return the lastupdatedDatetime
   */
  public Date getLastupdatedDatetime() {
    return lastupdatedDatetime;
  }

  //</editor-fold>
}