package gov.ca.water.entity.ewm.collection;

import entity.core.EntityCollection;
import entity.core.util.EntityUtil;
import gov.ca.water.entity.ewm.MeasurementIssueType;
import java.util.Arrays;

/**
 *
 * @author clay
 */
public class MeasurementIssueTypeCollection extends EntityCollection<MeasurementIssueType> {
  
  //<editor-fold defaultstate="expanded" desc="Private Properties">

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public MeasurementIssueTypeCollection() {
    super(MeasurementIssueType.class);
  }
  //</editor-fold>
  
  public MeasurementIssueTypeCollection loadById(Long issueTypeId){
    String query = "SELECT * FROM " + EntityUtil.getTableName(MeasurementIssueType.class) +
      " WHERE EWM_MEASUREMENT_ISSUE_TYPE_ID=? ";
    return this.runQuery(query, Arrays.asList(issueTypeId));
  }
}