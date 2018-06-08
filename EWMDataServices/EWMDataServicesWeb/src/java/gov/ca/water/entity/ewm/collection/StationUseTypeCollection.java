package gov.ca.water.entity.ewm.collection;

import entity.core.EntityCollection;
import gov.ca.water.entity.ewm.StationUseType;

/**
 *
 * @author clay
 */
public class StationUseTypeCollection extends EntityCollection<StationUseType> {
  
  //<editor-fold defaultstate="expanded" desc="Private Properties">

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public StationUseTypeCollection() {
    super(StationUseType.class);
  }
  //</editor-fold>
}