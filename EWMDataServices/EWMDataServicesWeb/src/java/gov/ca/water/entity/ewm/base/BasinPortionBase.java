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
  table = @EntityTable(name = "EWM_BASIN_PORTION", schema = "EWM_ADM")
  ,datasource = @EntityDataSource(name="EwmAdmDataSource",user = "ewm",password = "Ewm95670!",url = "jdbc:oracle:thin:@localhost:1521:orcl")
)
public class BasinPortionBase<E extends BasinPortionBase> extends EntityBase<E> {

  //<editor-fold defaultstate="expanded" desc="Private Properties">
  
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

  /**
   *
   */
  @EntityColumn(name="EWM_BASIN_PORTION_ID",primary=true)
  private Long ewmBasinPortionId;

  /**
   *
   */
  @EntityColumn(name="EWM_BASIN_ID")
  private Long ewmBasinId;

  /**
   *
   */
  @EntityColumn(name="WHOLE_BASIN_DATA")
  private String wholeBasinData;

  /**
   *
   */
  @EntityColumn(name="PORTION_NAME")
  private String portionName;

  /**
   *
   */
  @EntityColumn(name="HAS_SHAPE_FILE")
  private String hasShapeFile;

  /**
   *
   */
  @EntityColumn(name="NO_SHAPE_FILE_RSN_TXT")
  private String noShapeFileRsnTxt;

  /**
   *
   */
  @EntityColumn(name="MODIFIED_USER")
  private String modifiedUser;


  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    
    this.modifiedDate = null;
    this.modifiedProc = null;
    this.applId = null;
    this.ewmBasinPortionId = null;
    this.ewmBasinId = null;
    this.wholeBasinData = null;
    this.portionName = null;
    this.hasShapeFile = null;
    this.noShapeFileRsnTxt = null;
    this.modifiedUser = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Setters">
  
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
   * @param wholeBasinData the wholeBasinData to set
   */
  public E setWholeBasinData(String wholeBasinData) {
    return this.set("wholeBasinData",wholeBasinData);
  }

  /**
   * @param wholeBasinData the wholeBasinData to set
   */
  public E setWholeBasinData(ConditionOperator op, String wholeBasinData) {
    return this.addFilter(ConditionFilter.createInstance("wholeBasinData",op,wholeBasinData));
  }

  /**
   * @param portionName the portionName to set
   */
  public E setPortionName(String portionName) {
    return this.set("portionName",portionName);
  }

  /**
   * @param portionName the portionName to set
   */
  public E setPortionName(ConditionOperator op, String portionName) {
    return this.addFilter(ConditionFilter.createInstance("portionName",op,portionName));
  }

  /**
   * @param hasShapeFile the hasShapeFile to set
   */
  public E setHasShapeFile(String hasShapeFile) {
    return this.set("hasShapeFile",hasShapeFile);
  }

  /**
   * @param hasShapeFile the hasShapeFile to set
   */
  public E setHasShapeFile(ConditionOperator op, String hasShapeFile) {
    return this.addFilter(ConditionFilter.createInstance("hasShapeFile",op,hasShapeFile));
  }

  /**
   * @param noShapeFileRsnTxt the noShapeFileRsnTxt to set
   */
  public E setNoShapeFileRsnTxt(String noShapeFileRsnTxt) {
    return this.set("noShapeFileRsnTxt",noShapeFileRsnTxt);
  }

  /**
   * @param noShapeFileRsnTxt the noShapeFileRsnTxt to set
   */
  public E setNoShapeFileRsnTxt(ConditionOperator op, String noShapeFileRsnTxt) {
    return this.addFilter(ConditionFilter.createInstance("noShapeFileRsnTxt",op,noShapeFileRsnTxt));
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

  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Getters">
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
   * @return the wholeBasinData
   */
  public String getWholeBasinData() {
    return this.wholeBasinData;
  }

  /**
   * @return the portionName
   */
  public String getPortionName() {
    return this.portionName;
  }

  /**
   * @return the hasShapeFile
   */
  public String getHasShapeFile() {
    return this.hasShapeFile;
  }

  /**
   * @return the noShapeFileRsnTxt
   */
  public String getNoShapeFileRsnTxt() {
    return this.noShapeFileRsnTxt;
  }

  /**
   * @return the modifiedUser
   */
  public String getModifiedUser() {
    return this.modifiedUser;
  }

  //</editor-fold>
}