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
  table = @EntityTable(name = "SGMA_BASINMOD_INITREQUEST")
  ,datasource = @EntityDataSource(name="SgmaDataSource",user = "sgma",password = "sgma#95670",url = "jdbc:oracle:thin:@localhost:1521:orcl")
)
public class InitrequestBase<E extends InitrequestBase> extends EntityBase<E> {

  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  
  /**
   *
   */
  @EntityColumn(name="REQUEST_ID")
  private Long requestId;

  /**
   *
   */
  @EntityColumn(name="CONTACT_ID")
  private Long contactId;

  /**
   *
   */
  @EntityColumn(name="AGENCY_KEY")
  private Long agencyKey;

  /**
   *
   */
  @EntityColumn(name="DESCRIPTION")
  private String description;

  /**
   *
   */
  @EntityColumn(name="INITIAL_IMAGES")
  private Long initialImages;

  /**
   *
   */
  @EntityColumn(name="INITIAL_MAPPARAM")
  private String initialMapparam;

  /**
   *
   */
  @EntityColumn(name="MODTYPE_KEY")
  private String modtypeKey;

  /**
   *
   */
  @EntityColumn(name="STATUS")
  private String status;

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

  /**
   *
   */
  @EntityColumn(name="INITIAL_LOCAL_LINKS")
  private String initialLocalLinks;

  /**
   *
   */
  @EntityColumn(name="DOCUMENT_ID")
  private Long documentId;

  /**
   *
   */
  @EntityColumn(name="IS_ACTIVE")
  private Integer isActive;

  /**
   *
   */
  @EntityColumn(name="INITIAL_GRAPHICS")
  private String initialGraphics;

  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters">
  
  /**
   * @param requestId the requestId to set
   */
  public E setRequestId(Long requestId) {
    return this.set("requestId",requestId);
  }

  /**
   * @param requestId the requestId to set
   */
  public E setRequestId(ConditionOperator op, Long requestId) {
    return this.addFilter(ConditionFilter.createInstance("requestId",op,requestId));
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
   * @param description the description to set
   */
  public E setDescription(String description) {
    return this.set("description",description);
  }

  /**
   * @param description the description to set
   */
  public E setDescription(ConditionOperator op, String description) {
    return this.addFilter(ConditionFilter.createInstance("description",op,description));
  }

  /**
   * @param initialImages the initialImages to set
   */
  public E setInitialImages(Long initialImages) {
    return this.set("initialImages",initialImages);
  }

  /**
   * @param initialImages the initialImages to set
   */
  public E setInitialImages(ConditionOperator op, Long initialImages) {
    return this.addFilter(ConditionFilter.createInstance("initialImages",op,initialImages));
  }

  /**
   * @param initialMapparam the initialMapparam to set
   */
  public E setInitialMapparam(String initialMapparam) {
    return this.set("initialMapparam",initialMapparam);
  }

  /**
   * @param initialMapparam the initialMapparam to set
   */
  public E setInitialMapparam(ConditionOperator op, String initialMapparam) {
    return this.addFilter(ConditionFilter.createInstance("initialMapparam",op,initialMapparam));
  }

  /**
   * @param modtypeKey the modtypeKey to set
   */
  public E setModtypeKey(String modtypeKey) {
    return this.set("modtypeKey",modtypeKey);
  }

  /**
   * @param modtypeKey the modtypeKey to set
   */
  public E setModtypeKey(ConditionOperator op, String modtypeKey) {
    return this.addFilter(ConditionFilter.createInstance("modtypeKey",op,modtypeKey));
  }

  /**
   * @param status the status to set
   */
  public E setStatus(String status) {
    return this.set("status",status);
  }

  /**
   * @param status the status to set
   */
  public E setStatus(ConditionOperator op, String status) {
    return this.addFilter(ConditionFilter.createInstance("status",op,status));
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

  /**
   * @param initialLocalLinks the initialLocalLinks to set
   */
  public E setInitialLocalLinks(String initialLocalLinks) {
    return this.set("initialLocalLinks",initialLocalLinks);
  }

  /**
   * @param initialLocalLinks the initialLocalLinks to set
   */
  public E setInitialLocalLinks(ConditionOperator op, String initialLocalLinks) {
    return this.addFilter(ConditionFilter.createInstance("initialLocalLinks",op,initialLocalLinks));
  }

  /**
   * @param documentId the documentId to set
   */
  public E setDocumentId(Long documentId) {
    return this.set("documentId",documentId);
  }

  /**
   * @param documentId the documentId to set
   */
  public E setDocumentId(ConditionOperator op, Long documentId) {
    return this.addFilter(ConditionFilter.createInstance("documentId",op,documentId));
  }

  /**
   * @param isActive the isActive to set
   */
  public E setIsActive(Integer isActive) {
    return this.set("isActive",isActive);
  }

  /**
   * @param isActive the isActive to set
   */
  public E setIsActive(ConditionOperator op, Integer isActive) {
    return this.addFilter(ConditionFilter.createInstance("isActive",op,isActive));
  }

  /**
   * @param initialGraphics the initialGraphics to set
   */
  public E setInitialGraphics(String initialGraphics) {
    return this.set("initialGraphics",initialGraphics);
  }

  /**
   * @param initialGraphics the initialGraphics to set
   */
  public E setInitialGraphics(ConditionOperator op, String initialGraphics) {
    return this.addFilter(ConditionFilter.createInstance("initialGraphics",op,initialGraphics));
  }

  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Getters">
  /**
   * @return the requestId
   */
  public Long getRequestId() {
    return requestId;
  }

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
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @return the initialImages
   */
  public Long getInitialImages() {
    return initialImages;
  }

  /**
   * @return the initialMapparam
   */
  public String getInitialMapparam() {
    return initialMapparam;
  }

  /**
   * @return the modtypeKey
   */
  public String getModtypeKey() {
    return modtypeKey;
  }

  /**
   * @return the status
   */
  public String getStatus() {
    return status;
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

  /**
   * @return the initialLocalLinks
   */
  public String getInitialLocalLinks() {
    return initialLocalLinks;
  }

  /**
   * @return the documentId
   */
  public Long getDocumentId() {
    return documentId;
  }

  /**
   * @return the isActive
   */
  public Integer getIsActive() {
    return isActive;
  }

  /**
   * @return the initialGraphics
   */
  public String getInitialGraphics() {
    return initialGraphics;
  }

  //</editor-fold>
}