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
  table = @EntityTable(name = "EWM_MASTER_SITE",schema = "EWM_ADM")
  ,datasource = @EntityDataSource(name="EwmAdmDataSource",user = "ewm_adm",password = "Ewm95670!",url = "jdbc:oracle:thin:@localhost:1521:orcl")
)
public class MasterSiteBase<E extends MasterSiteBase> extends EntityBase<E> {

  //<editor-fold defaultstate="expanded" desc="Private Properties">
  
  /**
   *
   */
  @EntityColumn(name="EWM_MASTER_SITE_ID",primary=true)
  private Long ewmMasterSiteId;

  /**
   *
   */
  @EntityColumn(name="LATITUDE")
  private Double latitude;

  /**
   *
   */
  @EntityColumn(name="LONGITUDE")
  private Double longitude;

  /**
   *
   */
  @EntityColumn(name="ELEVATION")
  private Double elevation;

  /**
   *
   */
  @EntityColumn(name="CREATED_DT")
  private Date createdDt;

  /**
   *
   */
  @EntityColumn(name="PERSON_ID")
  private Long personId;

  /**
   *
   */
  @EntityColumn(name="SITE_CODE")
  private String siteCode;

  /**
   *
   */
  @EntityColumn(name="SITE_CODE_ROOT")
  private String siteCodeRoot;

  /**
   *
   */
  @EntityColumn(name="SITE_CODE_SEQ")
  private String siteCodeSeq;

  /**
   *
   */
  @EntityColumn(name="EWM_ACCESS_RESTRICTION_ID")
  private Long ewmAccessRestrictionId;

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
  @EntityColumn(name="HORZ_COORDINATE_TYPE_ID")
  private Long horzCoordinateTypeId;

  /**
   *
   */
  @EntityColumn(name="VERT_COORDINATE_TYPE_ID")
  private Long vertCoordinateTypeId;

  /**
   *
   */
  @EntityColumn(name="HORZ_MEASUREMENT_METHOD_ID")
  private Long horzMeasurementMethodId;

  /**
   *
   */
  @EntityColumn(name="VERT_MEASUREMENT_METHOD_ID")
  private Long vertMeasurementMethodId;

  /**
   *
   */
  @EntityColumn(name="HORZ_MEASUREMENT_ACCURACY_ID")
  private Long horzMeasurementAccuracyId;

  /**
   *
   */
  @EntityColumn(name="VERT_MEASUREMENT_ACCURACY_ID")
  private Long vertMeasurementAccuracyId;

  /**
   *
   */
  @EntityColumn(name="EWM_MASTER_SITE_ACTV")
  private String ewmMasterSiteActv;

  /**
   *
   */
  @EntityColumn(name="APPL_ID")
  private Long applId;


  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    
    this.ewmMasterSiteId = null;
    this.latitude = null;
    this.longitude = null;
    this.elevation = null;
    this.createdDt = null;
    this.personId = null;
    this.siteCode = null;
    this.siteCodeRoot = null;
    this.siteCodeSeq = null;
    this.ewmAccessRestrictionId = null;
    this.modifiedDate = null;
    this.modifiedUser = null;
    this.modifiedProc = null;
    this.horzCoordinateTypeId = null;
    this.vertCoordinateTypeId = null;
    this.horzMeasurementMethodId = null;
    this.vertMeasurementMethodId = null;
    this.horzMeasurementAccuracyId = null;
    this.vertMeasurementAccuracyId = null;
    this.ewmMasterSiteActv = null;
    this.applId = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters">
  
  /**
   * @param ewmMasterSiteId the ewmMasterSiteId to set
   */
  public E setEwmMasterSiteId(Long ewmMasterSiteId) {
    return this.set("ewmMasterSiteId",ewmMasterSiteId);
  }

  /**
   * @param ewmMasterSiteId the ewmMasterSiteId to set
   */
  public E setEwmMasterSiteId(ConditionOperator op, Long ewmMasterSiteId) {
    return this.addFilter(ConditionFilter.createInstance("ewmMasterSiteId",op,ewmMasterSiteId));
  }

  /**
   * @param latitude the latitude to set
   */
  public E setLatitude(Double latitude) {
    return this.set("latitude",latitude);
  }

  /**
   * @param latitude the latitude to set
   */
  public E setLatitude(ConditionOperator op, Double latitude) {
    return this.addFilter(ConditionFilter.createInstance("latitude",op,latitude));
  }

  /**
   * @param longitude the longitude to set
   */
  public E setLongitude(Double longitude) {
    return this.set("longitude",longitude);
  }

  /**
   * @param longitude the longitude to set
   */
  public E setLongitude(ConditionOperator op, Double longitude) {
    return this.addFilter(ConditionFilter.createInstance("longitude",op,longitude));
  }

  /**
   * @param elevation the elevation to set
   */
  public E setElevation(Double elevation) {
    return this.set("elevation",elevation);
  }

  /**
   * @param elevation the elevation to set
   */
  public E setElevation(ConditionOperator op, Double elevation) {
    return this.addFilter(ConditionFilter.createInstance("elevation",op,elevation));
  }

  /**
   * @param createdDt the createdDt to set
   */
  public E setCreatedDt(Date createdDt) {
    return this.set("createdDt",createdDt);
  }

  /**
   * @param createdDt the createdDt to set
   */
  public E setCreatedDt(ConditionOperator op, Date createdDt) {
    return this.addFilter(ConditionFilter.createInstance("createdDt",op,createdDt));
  }

  /**
   * @param personId the personId to set
   */
  public E setPersonId(Long personId) {
    return this.set("personId",personId);
  }

  /**
   * @param personId the personId to set
   */
  public E setPersonId(ConditionOperator op, Long personId) {
    return this.addFilter(ConditionFilter.createInstance("personId",op,personId));
  }

  /**
   * @param siteCode the siteCode to set
   */
  public E setSiteCode(String siteCode) {
    return this.set("siteCode",siteCode);
  }

  /**
   * @param siteCode the siteCode to set
   */
  public E setSiteCode(ConditionOperator op, String siteCode) {
    return this.addFilter(ConditionFilter.createInstance("siteCode",op,siteCode));
  }

  /**
   * @param siteCodeRoot the siteCodeRoot to set
   */
  public E setSiteCodeRoot(String siteCodeRoot) {
    return this.set("siteCodeRoot",siteCodeRoot);
  }

  /**
   * @param siteCodeRoot the siteCodeRoot to set
   */
  public E setSiteCodeRoot(ConditionOperator op, String siteCodeRoot) {
    return this.addFilter(ConditionFilter.createInstance("siteCodeRoot",op,siteCodeRoot));
  }

  /**
   * @param siteCodeSeq the siteCodeSeq to set
   */
  public E setSiteCodeSeq(String siteCodeSeq) {
    return this.set("siteCodeSeq",siteCodeSeq);
  }

  /**
   * @param siteCodeSeq the siteCodeSeq to set
   */
  public E setSiteCodeSeq(ConditionOperator op, String siteCodeSeq) {
    return this.addFilter(ConditionFilter.createInstance("siteCodeSeq",op,siteCodeSeq));
  }

  /**
   * @param ewmAccessRestrictionId the ewmAccessRestrictionId to set
   */
  public E setEwmAccessRestrictionId(Long ewmAccessRestrictionId) {
    return this.set("ewmAccessRestrictionId",ewmAccessRestrictionId);
  }

  /**
   * @param ewmAccessRestrictionId the ewmAccessRestrictionId to set
   */
  public E setEwmAccessRestrictionId(ConditionOperator op, Long ewmAccessRestrictionId) {
    return this.addFilter(ConditionFilter.createInstance("ewmAccessRestrictionId",op,ewmAccessRestrictionId));
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
   * @param horzCoordinateTypeId the horzCoordinateTypeId to set
   */
  public E setHorzCoordinateTypeId(Long horzCoordinateTypeId) {
    return this.set("horzCoordinateTypeId",horzCoordinateTypeId);
  }

  /**
   * @param horzCoordinateTypeId the horzCoordinateTypeId to set
   */
  public E setHorzCoordinateTypeId(ConditionOperator op, Long horzCoordinateTypeId) {
    return this.addFilter(ConditionFilter.createInstance("horzCoordinateTypeId",op,horzCoordinateTypeId));
  }

  /**
   * @param vertCoordinateTypeId the vertCoordinateTypeId to set
   */
  public E setVertCoordinateTypeId(Long vertCoordinateTypeId) {
    return this.set("vertCoordinateTypeId",vertCoordinateTypeId);
  }

  /**
   * @param vertCoordinateTypeId the vertCoordinateTypeId to set
   */
  public E setVertCoordinateTypeId(ConditionOperator op, Long vertCoordinateTypeId) {
    return this.addFilter(ConditionFilter.createInstance("vertCoordinateTypeId",op,vertCoordinateTypeId));
  }

  /**
   * @param horzMeasurementMethodId the horzMeasurementMethodId to set
   */
  public E setHorzMeasurementMethodId(Long horzMeasurementMethodId) {
    return this.set("horzMeasurementMethodId",horzMeasurementMethodId);
  }

  /**
   * @param horzMeasurementMethodId the horzMeasurementMethodId to set
   */
  public E setHorzMeasurementMethodId(ConditionOperator op, Long horzMeasurementMethodId) {
    return this.addFilter(ConditionFilter.createInstance("horzMeasurementMethodId",op,horzMeasurementMethodId));
  }

  /**
   * @param vertMeasurementMethodId the vertMeasurementMethodId to set
   */
  public E setVertMeasurementMethodId(Long vertMeasurementMethodId) {
    return this.set("vertMeasurementMethodId",vertMeasurementMethodId);
  }

  /**
   * @param vertMeasurementMethodId the vertMeasurementMethodId to set
   */
  public E setVertMeasurementMethodId(ConditionOperator op, Long vertMeasurementMethodId) {
    return this.addFilter(ConditionFilter.createInstance("vertMeasurementMethodId",op,vertMeasurementMethodId));
  }

  /**
   * @param horzMeasurementAccuracyId the horzMeasurementAccuracyId to set
   */
  public E setHorzMeasurementAccuracyId(Long horzMeasurementAccuracyId) {
    return this.set("horzMeasurementAccuracyId",horzMeasurementAccuracyId);
  }

  /**
   * @param horzMeasurementAccuracyId the horzMeasurementAccuracyId to set
   */
  public E setHorzMeasurementAccuracyId(ConditionOperator op, Long horzMeasurementAccuracyId) {
    return this.addFilter(ConditionFilter.createInstance("horzMeasurementAccuracyId",op,horzMeasurementAccuracyId));
  }

  /**
   * @param vertMeasurementAccuracyId the vertMeasurementAccuracyId to set
   */
  public E setVertMeasurementAccuracyId(Long vertMeasurementAccuracyId) {
    return this.set("vertMeasurementAccuracyId",vertMeasurementAccuracyId);
  }

  /**
   * @param vertMeasurementAccuracyId the vertMeasurementAccuracyId to set
   */
  public E setVertMeasurementAccuracyId(ConditionOperator op, Long vertMeasurementAccuracyId) {
    return this.addFilter(ConditionFilter.createInstance("vertMeasurementAccuracyId",op,vertMeasurementAccuracyId));
  }

  /**
   * @param ewmMasterSiteActv the ewmMasterSiteActv to set
   */
  public E setEwmMasterSiteActv(String ewmMasterSiteActv) {
    return this.set("ewmMasterSiteActv",ewmMasterSiteActv);
  }

  /**
   * @param ewmMasterSiteActv the ewmMasterSiteActv to set
   */
  public E setEwmMasterSiteActv(ConditionOperator op, String ewmMasterSiteActv) {
    return this.addFilter(ConditionFilter.createInstance("ewmMasterSiteActv",op,ewmMasterSiteActv));
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
   * @return the ewmMasterSiteId
   */
  public Long getEwmMasterSiteId() {
    return this.ewmMasterSiteId;
  }

  /**
   * @return the latitude
   */
  public Double getLatitude() {
    return this.latitude;
  }

  /**
   * @return the longitude
   */
  public Double getLongitude() {
    return this.longitude;
  }

  /**
   * @return the elevation
   */
  public Double getElevation() {
    return this.elevation;
  }

  /**
   * @return the createdDt
   */
  public Date getCreatedDt() {
    return this.createdDt;
  }

  /**
   * @return the personId
   */
  public Long getPersonId() {
    return this.personId;
  }

  /**
   * @return the siteCode
   */
  public String getSiteCode() {
    return this.siteCode;
  }

  /**
   * @return the siteCodeRoot
   */
  public String getSiteCodeRoot() {
    return this.siteCodeRoot;
  }

  /**
   * @return the siteCodeSeq
   */
  public String getSiteCodeSeq() {
    return this.siteCodeSeq;
  }

  /**
   * @return the ewmAccessRestrictionId
   */
  public Long getEwmAccessRestrictionId() {
    return this.ewmAccessRestrictionId;
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
   * @return the horzCoordinateTypeId
   */
  public Long getHorzCoordinateTypeId() {
    return this.horzCoordinateTypeId;
  }

  /**
   * @return the vertCoordinateTypeId
   */
  public Long getVertCoordinateTypeId() {
    return this.vertCoordinateTypeId;
  }

  /**
   * @return the horzMeasurementMethodId
   */
  public Long getHorzMeasurementMethodId() {
    return this.horzMeasurementMethodId;
  }

  /**
   * @return the vertMeasurementMethodId
   */
  public Long getVertMeasurementMethodId() {
    return this.vertMeasurementMethodId;
  }

  /**
   * @return the horzMeasurementAccuracyId
   */
  public Long getHorzMeasurementAccuracyId() {
    return this.horzMeasurementAccuracyId;
  }

  /**
   * @return the vertMeasurementAccuracyId
   */
  public Long getVertMeasurementAccuracyId() {
    return this.vertMeasurementAccuracyId;
  }

  /**
   * @return the ewmMasterSiteActv
   */
  public String getEwmMasterSiteActv() {
    return this.ewmMasterSiteActv;
  }

  /**
   * @return the applId
   */
  public Long getApplId() {
    return this.applId;
  }

  //</editor-fold>
}