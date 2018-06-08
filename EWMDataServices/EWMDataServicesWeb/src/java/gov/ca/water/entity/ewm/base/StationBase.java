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
  table = @EntityTable(name = "EWM_STATION", schema = "EWM_ADM")
  ,datasource = @EntityDataSource(name="EwmAdmDataSource",user = "EWM_ADM",password = "Ewm95670!",url = "jdbc:oracle:thin:@sac1v-vtieudb.geiconsultants.com:1521:orcl")
)
public class StationBase<E extends StationBase> extends EntityBase<E> {

  //<editor-fold defaultstate="expanded" desc="Private Properties">
  
  /**
   *
   */
  @EntityColumn(name="EWM_STATION_ID",primary=true)
  private Long ewmStationId;

  /**
   *
   */
  @EntityColumn(name="EWM_BASIN_PORTION_ID")
  private Long ewmBasinPortionId;

  /**
   *
   */
  @EntityColumn(name="EWM_BASIN_ID")
  private Long ewmBasinId;

  /**
   *
   */
  @EntityColumn(name="VERTICAL_MEASURE_METHOD_ID")
  private Long verticalMeasureMethodId;

  /**
   *
   */
  @EntityColumn(name="VERTICAL_MEASURE_ACCURACY_ID")
  private Long verticalMeasureAccuracyId;

  /**
   *
   */
  @EntityColumn(name="EWM_WELL_COMPLETION_ID")
  private Long ewmWellCompletionId;

  /**
   *
   */
  @EntityColumn(name="COMPLETION_RPT_NBR_AVAIL")
  private String completionRptNbrAvail;

  /**
   *
   */
  @EntityColumn(name="LATEST_RP_ELEVATION")
  private Double latestRpElevation;

  /**
   *
   */
  @EntityColumn(name="LATEST_GS_ELEVATION")
  private Double latestGsElevation;

  /**
   *
   */
  @EntityColumn(name="COMPLETION_RPT_NBR")
  private String completionRptNbr;

  /**
   *
   */
  @EntityColumn(name="WELL_LOCATION_DESC")
  private String wellLocationDesc;

  /**
   *
   */
  @EntityColumn(name="SAME_AS_STATE_WELL_NBR")
  private String sameAsStateWellNbr;

  /**
   *
   */
  @EntityColumn(name="EWM_STATION_USE_ID")
  private Long ewmStationUseId;

  /**
   *
   */
  @EntityColumn(name="EWM_STATION_ACTV")
  private String ewmStationActv;

  /**
   *
   */
  @EntityColumn(name="TOTAL_DEPTH_FT")
  private Long totalDepthFt;

  /**
   *
   */
  @EntityColumn(name="EWM_STATE_WELL_NBR")
  private String ewmStateWellNbr;

  /**
   *
   */
  @EntityColumn(name="EWM_OCCURRENCE_TYPE_ID")
  private Long ewmOccurrenceTypeId;

  /**
   *
   */
  @EntityColumn(name="EWM_CONFIDENCE_TYPE_ID")
  private Long ewmConfidenceTypeId;

  /**
   *
   */
  @EntityColumn(name="WELL_DEPTH_UNKNOWN")
  private String wellDepthUnknown;

  /**
   *
   */
  @EntityColumn(name="RP_DESC")
  private String rpDesc;

  /**
   *
   */
  @EntityColumn(name="HAS_WELL_CONSTRUCTION_DATA")
  private String hasWellConstructionData;

  /**
   *
   */
  @EntityColumn(name="ADDL_COMMENTS")
  private String addlComments;

  /**
   *
   */
  @EntityColumn(name="LOCAL_WELL_DESIGNATION")
  private String localWellDesignation;

  /**
   *
   */
  @EntityColumn(name="MODIFIED_USER")
  private String modifiedUser;

  /**
   *
   */
  @EntityColumn(name="MODIFIED_DATE")
  private Date modifiedDate;

  /**
   *
   */
  @EntityColumn(name="MODIFIED_PROC")
  private String modifiedProc;

  /**
   *
   */
  @EntityColumn(name="IS_VOLUNTARY_REPORTING")
  private String isVoluntaryReporting;

  /**
   *
   */
  @EntityColumn(name="HORIZONTAL_MEASURE_METHOD_ID")
  private Long horizontalMeasureMethodId;

  /**
   *
   */
  @EntityColumn(name="HORIZONTAL_MEASURE_ACCURACY_ID")
  private Long horizontalMeasureAccuracyId;

  /**
   *
   */
  @EntityColumn(name="COUNTY_ID")
  private Long countyId;

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
  @EntityColumn(name="EWM_COORDINATE_TYPE_ID")
  private Long ewmCoordinateTypeId;

  /**
   *
   */
  @EntityColumn(name="EWM_MASTER_SITE_ID")
  private Long ewmMasterSiteId;

  /**
   *
   */
  @EntityColumn(name="PRIMARY_WELL_ORG_ID")
  private Long primaryWellOrgId;

  /**
   *
   */
  @EntityColumn(name="APPL_ID")
  private Long applId;


  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    
    this.ewmStationId = null;
    this.ewmBasinPortionId = null;
    this.ewmBasinId = null;
    this.verticalMeasureMethodId = null;
    this.verticalMeasureAccuracyId = null;
    this.ewmWellCompletionId = null;
    this.completionRptNbrAvail = null;
    this.latestRpElevation = null;
    this.latestGsElevation = null;
    this.completionRptNbr = null;
    this.wellLocationDesc = null;
    this.sameAsStateWellNbr = null;
    this.ewmStationUseId = null;
    this.ewmStationActv = null;
    this.totalDepthFt = null;
    this.ewmStateWellNbr = null;
    this.ewmOccurrenceTypeId = null;
    this.ewmConfidenceTypeId = null;
    this.wellDepthUnknown = null;
    this.rpDesc = null;
    this.hasWellConstructionData = null;
    this.addlComments = null;
    this.localWellDesignation = null;
    this.modifiedUser = null;
    this.modifiedDate = null;
    this.modifiedProc = null;
    this.isVoluntaryReporting = null;
    this.horizontalMeasureMethodId = null;
    this.horizontalMeasureAccuracyId = null;
    this.countyId = null;
    this.latitude = null;
    this.longitude = null;
    this.ewmCoordinateTypeId = null;
    this.ewmMasterSiteId = null;
    this.primaryWellOrgId = null;
    this.applId = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters">
  
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
   * @param ewmBasinPortionId the ewmBasinPortionId to set
   */
  public E setEwmBasinPortionId(Long ewmBasinPortionId) {
    return this.set("ewmBasinPortionId",ewmBasinPortionId);
  }

  /**
   * @param ewmBasinPortionId the ewmBasinPortionId to set
   */
  public E setEwmBasinPortionId(ConditionOperator op, Long ewmBasinPortionId) {
    return this.addFilter(ConditionFilter.createInstance("ewmBasinPortionId",op,ewmBasinPortionId));
  }

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
   * @param verticalMeasureMethodId the verticalMeasureMethodId to set
   */
  public E setVerticalMeasureMethodId(Long verticalMeasureMethodId) {
    return this.set("verticalMeasureMethodId",verticalMeasureMethodId);
  }

  /**
   * @param verticalMeasureMethodId the verticalMeasureMethodId to set
   */
  public E setVerticalMeasureMethodId(ConditionOperator op, Long verticalMeasureMethodId) {
    return this.addFilter(ConditionFilter.createInstance("verticalMeasureMethodId",op,verticalMeasureMethodId));
  }

  /**
   * @param verticalMeasureAccuracyId the verticalMeasureAccuracyId to set
   */
  public E setVerticalMeasureAccuracyId(Long verticalMeasureAccuracyId) {
    return this.set("verticalMeasureAccuracyId",verticalMeasureAccuracyId);
  }

  /**
   * @param verticalMeasureAccuracyId the verticalMeasureAccuracyId to set
   */
  public E setVerticalMeasureAccuracyId(ConditionOperator op, Long verticalMeasureAccuracyId) {
    return this.addFilter(ConditionFilter.createInstance("verticalMeasureAccuracyId",op,verticalMeasureAccuracyId));
  }

  /**
   * @param ewmWellCompletionId the ewmWellCompletionId to set
   */
  public E setEwmWellCompletionId(Long ewmWellCompletionId) {
    return this.set("ewmWellCompletionId",ewmWellCompletionId);
  }

  /**
   * @param ewmWellCompletionId the ewmWellCompletionId to set
   */
  public E setEwmWellCompletionId(ConditionOperator op, Long ewmWellCompletionId) {
    return this.addFilter(ConditionFilter.createInstance("ewmWellCompletionId",op,ewmWellCompletionId));
  }

  /**
   * @param completionRptNbrAvail the completionRptNbrAvail to set
   */
  public E setCompletionRptNbrAvail(String completionRptNbrAvail) {
    return this.set("completionRptNbrAvail",completionRptNbrAvail);
  }

  /**
   * @param completionRptNbrAvail the completionRptNbrAvail to set
   */
  public E setCompletionRptNbrAvail(ConditionOperator op, String completionRptNbrAvail) {
    return this.addFilter(ConditionFilter.createInstance("completionRptNbrAvail",op,completionRptNbrAvail));
  }

  /**
   * @param latestRpElevation the latestRpElevation to set
   */
  public E setLatestRpElevation(Double latestRpElevation) {
    return this.set("latestRpElevation",latestRpElevation);
  }

  /**
   * @param latestRpElevation the latestRpElevation to set
   */
  public E setLatestRpElevation(ConditionOperator op, Double latestRpElevation) {
    return this.addFilter(ConditionFilter.createInstance("latestRpElevation",op,latestRpElevation));
  }

  /**
   * @param latestGsElevation the latestGsElevation to set
   */
  public E setLatestGsElevation(Double latestGsElevation) {
    return this.set("latestGsElevation",latestGsElevation);
  }

  /**
   * @param latestGsElevation the latestGsElevation to set
   */
  public E setLatestGsElevation(ConditionOperator op, Double latestGsElevation) {
    return this.addFilter(ConditionFilter.createInstance("latestGsElevation",op,latestGsElevation));
  }

  /**
   * @param completionRptNbr the completionRptNbr to set
   */
  public E setCompletionRptNbr(String completionRptNbr) {
    return this.set("completionRptNbr",completionRptNbr);
  }

  /**
   * @param completionRptNbr the completionRptNbr to set
   */
  public E setCompletionRptNbr(ConditionOperator op, String completionRptNbr) {
    return this.addFilter(ConditionFilter.createInstance("completionRptNbr",op,completionRptNbr));
  }

  /**
   * @param wellLocationDesc the wellLocationDesc to set
   */
  public E setWellLocationDesc(String wellLocationDesc) {
    return this.set("wellLocationDesc",wellLocationDesc);
  }

  /**
   * @param wellLocationDesc the wellLocationDesc to set
   */
  public E setWellLocationDesc(ConditionOperator op, String wellLocationDesc) {
    return this.addFilter(ConditionFilter.createInstance("wellLocationDesc",op,wellLocationDesc));
  }

  /**
   * @param sameAsStateWellNbr the sameAsStateWellNbr to set
   */
  public E setSameAsStateWellNbr(String sameAsStateWellNbr) {
    return this.set("sameAsStateWellNbr",sameAsStateWellNbr);
  }

  /**
   * @param sameAsStateWellNbr the sameAsStateWellNbr to set
   */
  public E setSameAsStateWellNbr(ConditionOperator op, String sameAsStateWellNbr) {
    return this.addFilter(ConditionFilter.createInstance("sameAsStateWellNbr",op,sameAsStateWellNbr));
  }

  /**
   * @param ewmStationUseId the ewmStationUseId to set
   */
  public E setEwmStationUseId(Long ewmStationUseId) {
    return this.set("ewmStationUseId",ewmStationUseId);
  }

  /**
   * @param ewmStationUseId the ewmStationUseId to set
   */
  public E setEwmStationUseId(ConditionOperator op, Long ewmStationUseId) {
    return this.addFilter(ConditionFilter.createInstance("ewmStationUseId",op,ewmStationUseId));
  }

  /**
   * @param ewmStationActv the ewmStationActv to set
   */
  public E setEwmStationActv(String ewmStationActv) {
    return this.set("ewmStationActv",ewmStationActv);
  }

  /**
   * @param ewmStationActv the ewmStationActv to set
   */
  public E setEwmStationActv(ConditionOperator op, String ewmStationActv) {
    return this.addFilter(ConditionFilter.createInstance("ewmStationActv",op,ewmStationActv));
  }

  /**
   * @param totalDepthFt the totalDepthFt to set
   */
  public E setTotalDepthFt(Long totalDepthFt) {
    return this.set("totalDepthFt",totalDepthFt);
  }

  /**
   * @param totalDepthFt the totalDepthFt to set
   */
  public E setTotalDepthFt(ConditionOperator op, Long totalDepthFt) {
    return this.addFilter(ConditionFilter.createInstance("totalDepthFt",op,totalDepthFt));
  }

  /**
   * @param ewmStateWellNbr the ewmStateWellNbr to set
   */
  public E setEwmStateWellNbr(String ewmStateWellNbr) {
    return this.set("ewmStateWellNbr",ewmStateWellNbr);
  }

  /**
   * @param ewmStateWellNbr the ewmStateWellNbr to set
   */
  public E setEwmStateWellNbr(ConditionOperator op, String ewmStateWellNbr) {
    return this.addFilter(ConditionFilter.createInstance("ewmStateWellNbr",op,ewmStateWellNbr));
  }

  /**
   * @param ewmOccurrenceTypeId the ewmOccurrenceTypeId to set
   */
  public E setEwmOccurrenceTypeId(Long ewmOccurrenceTypeId) {
    return this.set("ewmOccurrenceTypeId",ewmOccurrenceTypeId);
  }

  /**
   * @param ewmOccurrenceTypeId the ewmOccurrenceTypeId to set
   */
  public E setEwmOccurrenceTypeId(ConditionOperator op, Long ewmOccurrenceTypeId) {
    return this.addFilter(ConditionFilter.createInstance("ewmOccurrenceTypeId",op,ewmOccurrenceTypeId));
  }

  /**
   * @param ewmConfidenceTypeId the ewmConfidenceTypeId to set
   */
  public E setEwmConfidenceTypeId(Long ewmConfidenceTypeId) {
    return this.set("ewmConfidenceTypeId",ewmConfidenceTypeId);
  }

  /**
   * @param ewmConfidenceTypeId the ewmConfidenceTypeId to set
   */
  public E setEwmConfidenceTypeId(ConditionOperator op, Long ewmConfidenceTypeId) {
    return this.addFilter(ConditionFilter.createInstance("ewmConfidenceTypeId",op,ewmConfidenceTypeId));
  }

  /**
   * @param wellDepthUnknown the wellDepthUnknown to set
   */
  public E setWellDepthUnknown(String wellDepthUnknown) {
    return this.set("wellDepthUnknown",wellDepthUnknown);
  }

  /**
   * @param wellDepthUnknown the wellDepthUnknown to set
   */
  public E setWellDepthUnknown(ConditionOperator op, String wellDepthUnknown) {
    return this.addFilter(ConditionFilter.createInstance("wellDepthUnknown",op,wellDepthUnknown));
  }

  /**
   * @param rpDesc the rpDesc to set
   */
  public E setRpDesc(String rpDesc) {
    return this.set("rpDesc",rpDesc);
  }

  /**
   * @param rpDesc the rpDesc to set
   */
  public E setRpDesc(ConditionOperator op, String rpDesc) {
    return this.addFilter(ConditionFilter.createInstance("rpDesc",op,rpDesc));
  }

  /**
   * @param hasWellConstructionData the hasWellConstructionData to set
   */
  public E setHasWellConstructionData(String hasWellConstructionData) {
    return this.set("hasWellConstructionData",hasWellConstructionData);
  }

  /**
   * @param hasWellConstructionData the hasWellConstructionData to set
   */
  public E setHasWellConstructionData(ConditionOperator op, String hasWellConstructionData) {
    return this.addFilter(ConditionFilter.createInstance("hasWellConstructionData",op,hasWellConstructionData));
  }

  /**
   * @param addlComments the addlComments to set
   */
  public E setAddlComments(String addlComments) {
    return this.set("addlComments",addlComments);
  }

  /**
   * @param addlComments the addlComments to set
   */
  public E setAddlComments(ConditionOperator op, String addlComments) {
    return this.addFilter(ConditionFilter.createInstance("addlComments",op,addlComments));
  }

  /**
   * @param localWellDesignation the localWellDesignation to set
   */
  public E setLocalWellDesignation(String localWellDesignation) {
    return this.set("localWellDesignation",localWellDesignation);
  }

  /**
   * @param localWellDesignation the localWellDesignation to set
   */
  public E setLocalWellDesignation(ConditionOperator op, String localWellDesignation) {
    return this.addFilter(ConditionFilter.createInstance("localWellDesignation",op,localWellDesignation));
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
   * @param isVoluntaryReporting the isVoluntaryReporting to set
   */
  public E setIsVoluntaryReporting(String isVoluntaryReporting) {
    return this.set("isVoluntaryReporting",isVoluntaryReporting);
  }

  /**
   * @param isVoluntaryReporting the isVoluntaryReporting to set
   */
  public E setIsVoluntaryReporting(ConditionOperator op, String isVoluntaryReporting) {
    return this.addFilter(ConditionFilter.createInstance("isVoluntaryReporting",op,isVoluntaryReporting));
  }

  /**
   * @param horizontalMeasureMethodId the horizontalMeasureMethodId to set
   */
  public E setHorizontalMeasureMethodId(Long horizontalMeasureMethodId) {
    return this.set("horizontalMeasureMethodId",horizontalMeasureMethodId);
  }

  /**
   * @param horizontalMeasureMethodId the horizontalMeasureMethodId to set
   */
  public E setHorizontalMeasureMethodId(ConditionOperator op, Long horizontalMeasureMethodId) {
    return this.addFilter(ConditionFilter.createInstance("horizontalMeasureMethodId",op,horizontalMeasureMethodId));
  }

  /**
   * @param horizontalMeasureAccuracyId the horizontalMeasureAccuracyId to set
   */
  public E setHorizontalMeasureAccuracyId(Long horizontalMeasureAccuracyId) {
    return this.set("horizontalMeasureAccuracyId",horizontalMeasureAccuracyId);
  }

  /**
   * @param horizontalMeasureAccuracyId the horizontalMeasureAccuracyId to set
   */
  public E setHorizontalMeasureAccuracyId(ConditionOperator op, Long horizontalMeasureAccuracyId) {
    return this.addFilter(ConditionFilter.createInstance("horizontalMeasureAccuracyId",op,horizontalMeasureAccuracyId));
  }

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
   * @param ewmCoordinateTypeId the ewmCoordinateTypeId to set
   */
  public E setEwmCoordinateTypeId(Long ewmCoordinateTypeId) {
    return this.set("ewmCoordinateTypeId",ewmCoordinateTypeId);
  }

  /**
   * @param ewmCoordinateTypeId the ewmCoordinateTypeId to set
   */
  public E setEwmCoordinateTypeId(ConditionOperator op, Long ewmCoordinateTypeId) {
    return this.addFilter(ConditionFilter.createInstance("ewmCoordinateTypeId",op,ewmCoordinateTypeId));
  }

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
   * @param primaryWellOrgId the primaryWellOrgId to set
   */
  public E setPrimaryWellOrgId(Long primaryWellOrgId) {
    return this.set("primaryWellOrgId",primaryWellOrgId);
  }

  /**
   * @param primaryWellOrgId the primaryWellOrgId to set
   */
  public E setPrimaryWellOrgId(ConditionOperator op, Long primaryWellOrgId) {
    return this.addFilter(ConditionFilter.createInstance("primaryWellOrgId",op,primaryWellOrgId));
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
   * @return the ewmStationId
   */
  public Long getEwmStationId() {
    return this.ewmStationId;
  }

  /**
   * @return the ewmBasinPortionId
   */
  public Long getEwmBasinPortionId() {
    return this.ewmBasinPortionId;
  }

  /**
   * @return the ewmBasinId
   */
  public Long getEwmBasinId() {
    return this.ewmBasinId;
  }

  /**
   * @return the verticalMeasureMethodId
   */
  public Long getVerticalMeasureMethodId() {
    return this.verticalMeasureMethodId;
  }

  /**
   * @return the verticalMeasureAccuracyId
   */
  public Long getVerticalMeasureAccuracyId() {
    return this.verticalMeasureAccuracyId;
  }

  /**
   * @return the ewmWellCompletionId
   */
  public Long getEwmWellCompletionId() {
    return this.ewmWellCompletionId;
  }

  /**
   * @return the completionRptNbrAvail
   */
  public String getCompletionRptNbrAvail() {
    return this.completionRptNbrAvail;
  }

  /**
   * @return the latestRpElevation
   */
  public Double getLatestRpElevation() {
    return this.latestRpElevation;
  }

  /**
   * @return the latestGsElevation
   */
  public Double getLatestGsElevation() {
    return this.latestGsElevation;
  }

  /**
   * @return the completionRptNbr
   */
  public String getCompletionRptNbr() {
    return this.completionRptNbr;
  }

  /**
   * @return the wellLocationDesc
   */
  public String getWellLocationDesc() {
    return this.wellLocationDesc;
  }

  /**
   * @return the sameAsStateWellNbr
   */
  public String getSameAsStateWellNbr() {
    return this.sameAsStateWellNbr;
  }

  /**
   * @return the ewmStationUseId
   */
  public Long getEwmStationUseId() {
    return this.ewmStationUseId;
  }

  /**
   * @return the ewmStationActv
   */
  public String getEwmStationActv() {
    return this.ewmStationActv;
  }

  /**
   * @return the totalDepthFt
   */
  public Long getTotalDepthFt() {
    return this.totalDepthFt;
  }

  /**
   * @return the ewmStateWellNbr
   */
  public String getEwmStateWellNbr() {
    return this.ewmStateWellNbr;
  }

  /**
   * @return the ewmOccurrenceTypeId
   */
  public Long getEwmOccurrenceTypeId() {
    return this.ewmOccurrenceTypeId;
  }

  /**
   * @return the ewmConfidenceTypeId
   */
  public Long getEwmConfidenceTypeId() {
    return this.ewmConfidenceTypeId;
  }

  /**
   * @return the wellDepthUnknown
   */
  public String getWellDepthUnknown() {
    return this.wellDepthUnknown;
  }

  /**
   * @return the rpDesc
   */
  public String getRpDesc() {
    return this.rpDesc;
  }

  /**
   * @return the hasWellConstructionData
   */
  public String getHasWellConstructionData() {
    return this.hasWellConstructionData;
  }

  /**
   * @return the addlComments
   */
  public String getAddlComments() {
    return this.addlComments;
  }

  /**
   * @return the localWellDesignation
   */
  public String getLocalWellDesignation() {
    return this.localWellDesignation;
  }

  /**
   * @return the modifiedUser
   */
  public String getModifiedUser() {
    return this.modifiedUser;
  }

  /**
   * @return the modifiedDate
   */
  public Date getModifiedDate() {
    return this.modifiedDate;
  }

  /**
   * @return the modifiedProc
   */
  public String getModifiedProc() {
    return this.modifiedProc;
  }

  /**
   * @return the isVoluntaryReporting
   */
  public String getIsVoluntaryReporting() {
    return this.isVoluntaryReporting;
  }

  /**
   * @return the horizontalMeasureMethodId
   */
  public Long getHorizontalMeasureMethodId() {
    return this.horizontalMeasureMethodId;
  }

  /**
   * @return the horizontalMeasureAccuracyId
   */
  public Long getHorizontalMeasureAccuracyId() {
    return this.horizontalMeasureAccuracyId;
  }

  /**
   * @return the countyId
   */
  public Long getCountyId() {
    return this.countyId;
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
   * @return the ewmCoordinateTypeId
   */
  public Long getEwmCoordinateTypeId() {
    return this.ewmCoordinateTypeId;
  }

  /**
   * @return the ewmMasterSiteId
   */
  public Long getEwmMasterSiteId() {
    return this.ewmMasterSiteId;
  }

  /**
   * @return the primaryWellOrgId
   */
  public Long getPrimaryWellOrgId() {
    return this.primaryWellOrgId;
  }

  /**
   * @return the applId
   */
  public Long getApplId() {
    return this.applId;
  }

  //</editor-fold>
}