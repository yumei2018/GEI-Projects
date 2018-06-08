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
  table = @EntityTable(name = "EWM_STATION_PERFORATION",schema = "EWM_ADM")
  ,datasource = @EntityDataSource(name="EwmAdmDataSource",user = "ewm_adm",password = "Ewm95670!",url = "jdbc:oracle:thin:@localhost:1521:orcl")
)
public class StationPerforationBase<E extends StationPerforationBase> extends EntityBase<E> {

  //<editor-fold defaultstate="expanded" desc="Private Properties">
  
  /**
   *
   */
  @EntityColumn(name="EWM_STATION_PERFORATION_ID",primary=true)
  private Long ewmStationPerforationId;

  /**
   *
   */
  @EntityColumn(name="PERFORATION_TOP_MSRMNT")
  private Double perforationTopMsrmnt;

  /**
   *
   */
  @EntityColumn(name="PERFORATION_BOTTOM_MSRMNT")
  private Double perforationBottomMsrmnt;

  /**
   *
   */
  @EntityColumn(name="EWM_STATION_ID")
  private Long ewmStationId;

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
  @EntityColumn(name="APPL_ID")
  private Long applId;


  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    
    this.ewmStationPerforationId = null;
    this.perforationTopMsrmnt = null;
    this.perforationBottomMsrmnt = null;
    this.ewmStationId = null;
    this.modifiedUser = null;
    this.modifiedDate = null;
    this.modifiedProc = null;
    this.applId = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters">
  
  /**
   * @param ewmStationPerforationId the ewmStationPerforationId to set
   */
  public E setEwmStationPerforationId(Long ewmStationPerforationId) {
    return this.set("ewmStationPerforationId",ewmStationPerforationId);
  }

  /**
   * @param ewmStationPerforationId the ewmStationPerforationId to set
   */
  public E setEwmStationPerforationId(ConditionOperator op, Long ewmStationPerforationId) {
    return this.addFilter(ConditionFilter.createInstance("ewmStationPerforationId",op,ewmStationPerforationId));
  }

  /**
   * @param perforationTopMsrmnt the perforationTopMsrmnt to set
   */
  public E setPerforationTopMsrmnt(Double perforationTopMsrmnt) {
    return this.set("perforationTopMsrmnt",perforationTopMsrmnt);
  }

  /**
   * @param perforationTopMsrmnt the perforationTopMsrmnt to set
   */
  public E setPerforationTopMsrmnt(ConditionOperator op, Double perforationTopMsrmnt) {
    return this.addFilter(ConditionFilter.createInstance("perforationTopMsrmnt",op,perforationTopMsrmnt));
  }

  /**
   * @param perforationBottomMsrmnt the perforationBottomMsrmnt to set
   */
  public E setPerforationBottomMsrmnt(Double perforationBottomMsrmnt) {
    return this.set("perforationBottomMsrmnt",perforationBottomMsrmnt);
  }

  /**
   * @param perforationBottomMsrmnt the perforationBottomMsrmnt to set
   */
  public E setPerforationBottomMsrmnt(ConditionOperator op, Double perforationBottomMsrmnt) {
    return this.addFilter(ConditionFilter.createInstance("perforationBottomMsrmnt",op,perforationBottomMsrmnt));
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
   * @return the ewmStationPerforationId
   */
  public Long getEwmStationPerforationId() {
    return this.ewmStationPerforationId;
  }

  /**
   * @return the perforationTopMsrmnt
   */
  public Double getPerforationTopMsrmnt() {
    return this.perforationTopMsrmnt;
  }

  /**
   * @return the perforationBottomMsrmnt
   */
  public Double getPerforationBottomMsrmnt() {
    return this.perforationBottomMsrmnt;
  }

  /**
   * @return the ewmStationId
   */
  public Long getEwmStationId() {
    return this.ewmStationId;
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
   * @return the applId
   */
  public Long getApplId() {
    return this.applId;
  }

  //</editor-fold>
}