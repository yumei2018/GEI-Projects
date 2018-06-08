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
  table = @EntityTable(name = "EWM_ELEVATION_DATA_READING", schema = "EWM_ADM")
  ,datasource = @EntityDataSource(name="EwmAdmDataSource",user = "ewm",password = "Ewm95670!",url = "jdbc:oracle:thin:@localhost:1521:orcl")
)
public class ElevationDataReadingBase<E extends ElevationDataReadingBase> extends EntityBase<E> {

  //<editor-fold defaultstate="expanded" desc="Private Properties">
  
  /**
   *
   */
  @EntityColumn(name="EWM_ELEVATION_DATA_READING_ID",primary=true)
  private Long ewmElevationDataReadingId;

  /**
   *
   */
  @EntityColumn(name="MEASUREMENT_DT")
  private Date measurementDt;

  /**
   *
   */
  @EntityColumn(name="ORG_ID")
  private Long orgId;

  /**
   *
   */
  @EntityColumn(name="EWM_MEASUREMENT_ISSUE_TYPE_ID")
  private Long ewmMeasurementIssueTypeId;

  /**
   *
   */
  @EntityColumn(name="EWM_STATION_ID")
  private Long ewmStationId;

  /**
   *
   */
  @EntityColumn(name="EWM_ELEV_MEASURE_METHOD_TYP_ID")
  private Long ewmElevMeasureMethodTypId;

  /**
   *
   */
  @EntityColumn(name="EWM_ELEVATION_ACCURACY_TYPE_ID")
  private Long ewmElevationAccuracyTypeId;

  /**
   *
   */
  @EntityColumn(name="REFERENCE_POINT_ELEVATION")
  private Double referencePointElevation;

  /**
   *
   */
  @EntityColumn(name="GROUND_SURFACE_ELEVATION")
  private Double groundSurfaceElevation;

  /**
   *
   */
  @EntityColumn(name="WATER_SURFACE_READING")
  private Double waterSurfaceReading;

  /**
   *
   */
  @EntityColumn(name="REFERENCE_POINT_READING")
  private Double referencePointReading;

  /**
   *
   */
  @EntityColumn(name="MANDATORY_READING")
  private String mandatoryReading;

  /**
   *
   */
  @EntityColumn(name="COMMENTS")
  private String comments;

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
  @EntityColumn(name="COOPERATING_AGENCY_ORG_ID")
  private Long cooperatingAgencyOrgId;

  /**
   *
   */
  @EntityColumn(name="APPL_ID")
  private Long applId;


  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    
    this.ewmElevationDataReadingId = null;
    this.measurementDt = null;
    this.orgId = null;
    this.ewmMeasurementIssueTypeId = null;
    this.ewmStationId = null;
    this.ewmElevMeasureMethodTypId = null;
    this.ewmElevationAccuracyTypeId = null;
    this.referencePointElevation = null;
    this.groundSurfaceElevation = null;
    this.waterSurfaceReading = null;
    this.referencePointReading = null;
    this.mandatoryReading = null;
    this.comments = null;
    this.modifiedDate = null;
    this.modifiedUser = null;
    this.modifiedProc = null;
    this.cooperatingAgencyOrgId = null;
    this.applId = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters">
  
  /**
   * @param ewmElevationDataReadingId the ewmElevationDataReadingId to set
   */
  public E setEwmElevationDataReadingId(Long ewmElevationDataReadingId) {
    return this.set("ewmElevationDataReadingId",ewmElevationDataReadingId);
  }

  /**
   * @param ewmElevationDataReadingId the ewmElevationDataReadingId to set
   */
  public E setEwmElevationDataReadingId(ConditionOperator op, Long ewmElevationDataReadingId) {
    return this.addFilter(ConditionFilter.createInstance("ewmElevationDataReadingId",op,ewmElevationDataReadingId));
  }

  /**
   * @param measurementDt the measurementDt to set
   */
  public E setMeasurementDt(Date measurementDt) {
    return this.set("measurementDt",measurementDt);
  }

  /**
   * @param measurementDt the measurementDt to set
   */
  public E setMeasurementDt(ConditionOperator op, Date measurementDt) {
    return this.addFilter(ConditionFilter.createInstance("measurementDt",op,measurementDt));
  }

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
   * @param ewmStationId the ewmStationId to set
   */
  public E setEwmStationId(Long ewmStationId) {
    return this.set("ewmStationId",ewmStationId);
  }

  /**
   * @param ewmStationId the ewmStationId to set
   */
  public E setEwmStationId(ConditionOperator op, Long ewmStationId) {
    return this.addFilter(ConditionFilter.createInstance("ewmStationId",op,ewmStationId));
  }

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
   * @param ewmElevationAccuracyTypeId the ewmElevationAccuracyTypeId to set
   */
  public E setEwmElevationAccuracyTypeId(Long ewmElevationAccuracyTypeId) {
    return this.set("ewmElevationAccuracyTypeId",ewmElevationAccuracyTypeId);
  }

  /**
   * @param ewmElevationAccuracyTypeId the ewmElevationAccuracyTypeId to set
   */
  public E setEwmElevationAccuracyTypeId(ConditionOperator op, Long ewmElevationAccuracyTypeId) {
    return this.addFilter(ConditionFilter.createInstance("ewmElevationAccuracyTypeId",op,ewmElevationAccuracyTypeId));
  }

  /**
   * @param referencePointElevation the referencePointElevation to set
   */
  public E setReferencePointElevation(Double referencePointElevation) {
    return this.set("referencePointElevation",referencePointElevation);
  }

  /**
   * @param referencePointElevation the referencePointElevation to set
   */
  public E setReferencePointElevation(ConditionOperator op, Double referencePointElevation) {
    return this.addFilter(ConditionFilter.createInstance("referencePointElevation",op,referencePointElevation));
  }

  /**
   * @param groundSurfaceElevation the groundSurfaceElevation to set
   */
  public E setGroundSurfaceElevation(Double groundSurfaceElevation) {
    return this.set("groundSurfaceElevation",groundSurfaceElevation);
  }

  /**
   * @param groundSurfaceElevation the groundSurfaceElevation to set
   */
  public E setGroundSurfaceElevation(ConditionOperator op, Double groundSurfaceElevation) {
    return this.addFilter(ConditionFilter.createInstance("groundSurfaceElevation",op,groundSurfaceElevation));
  }

  /**
   * @param waterSurfaceReading the waterSurfaceReading to set
   */
  public E setWaterSurfaceReading(Double waterSurfaceReading) {
    return this.set("waterSurfaceReading",waterSurfaceReading);
  }

  /**
   * @param waterSurfaceReading the waterSurfaceReading to set
   */
  public E setWaterSurfaceReading(ConditionOperator op, Double waterSurfaceReading) {
    return this.addFilter(ConditionFilter.createInstance("waterSurfaceReading",op,waterSurfaceReading));
  }

  /**
   * @param referencePointReading the referencePointReading to set
   */
  public E setReferencePointReading(Double referencePointReading) {
    return this.set("referencePointReading",referencePointReading);
  }

  /**
   * @param referencePointReading the referencePointReading to set
   */
  public E setReferencePointReading(ConditionOperator op, Double referencePointReading) {
    return this.addFilter(ConditionFilter.createInstance("referencePointReading",op,referencePointReading));
  }

  /**
   * @param mandatoryReading the mandatoryReading to set
   */
  public E setMandatoryReading(String mandatoryReading) {
    return this.set("mandatoryReading",mandatoryReading);
  }

  /**
   * @param mandatoryReading the mandatoryReading to set
   */
  public E setMandatoryReading(ConditionOperator op, String mandatoryReading) {
    return this.addFilter(ConditionFilter.createInstance("mandatoryReading",op,mandatoryReading));
  }

  /**
   * @param comments the comments to set
   */
  public E setComments(String comments) {
    return this.set("comments",comments);
  }

  /**
   * @param comments the comments to set
   */
  public E setComments(ConditionOperator op, String comments) {
    return this.addFilter(ConditionFilter.createInstance("comments",op,comments));
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
   * @param cooperatingAgencyOrgId the cooperatingAgencyOrgId to set
   */
  public E setCooperatingAgencyOrgId(Long cooperatingAgencyOrgId) {
    return this.set("cooperatingAgencyOrgId",cooperatingAgencyOrgId);
  }

  /**
   * @param cooperatingAgencyOrgId the cooperatingAgencyOrgId to set
   */
  public E setCooperatingAgencyOrgId(ConditionOperator op, Long cooperatingAgencyOrgId) {
    return this.addFilter(ConditionFilter.createInstance("cooperatingAgencyOrgId",op,cooperatingAgencyOrgId));
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
   * @return the ewmElevationDataReadingId
   */
  public Long getEwmElevationDataReadingId() {
    return this.ewmElevationDataReadingId;
  }

  /**
   * @return the measurementDt
   */
  public Date getMeasurementDt() {
    return this.measurementDt;
  }

  /**
   * @return the orgId
   */
  public Long getOrgId() {
    return this.orgId;
  }

  /**
   * @return the ewmMeasurementIssueTypeId
   */
  public Long getEwmMeasurementIssueTypeId() {
    return this.ewmMeasurementIssueTypeId;
  }

  /**
   * @return the ewmStationId
   */
  public Long getEwmStationId() {
    return this.ewmStationId;
  }

  /**
   * @return the ewmElevMeasureMethodTypId
   */
  public Long getEwmElevMeasureMethodTypId() {
    return this.ewmElevMeasureMethodTypId;
  }

  /**
   * @return the ewmElevationAccuracyTypeId
   */
  public Long getEwmElevationAccuracyTypeId() {
    return this.ewmElevationAccuracyTypeId;
  }

  /**
   * @return the referencePointElevation
   */
  public Double getReferencePointElevation() {
    return this.referencePointElevation;
  }

  /**
   * @return the groundSurfaceElevation
   */
  public Double getGroundSurfaceElevation() {
    return this.groundSurfaceElevation;
  }

  /**
   * @return the waterSurfaceReading
   */
  public Double getWaterSurfaceReading() {
    return this.waterSurfaceReading;
  }

  /**
   * @return the referencePointReading
   */
  public Double getReferencePointReading() {
    return this.referencePointReading;
  }

  /**
   * @return the mandatoryReading
   */
  public String getMandatoryReading() {
    return this.mandatoryReading;
  }

  /**
   * @return the comments
   */
  public String getComments() {
    return this.comments;
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
   * @return the cooperatingAgencyOrgId
   */
  public Long getCooperatingAgencyOrgId() {
    return this.cooperatingAgencyOrgId;
  }

  /**
   * @return the applId
   */
  public Long getApplId() {
    return this.applId;
  }

  //</editor-fold>
}