package gov.ca.water.entity.ewm.collection;

import entity.core.EntityCollection;
import gov.ca.water.entity.ewm.County;

/**
 *
 * @author clay
 */
public class CountyCollection extends EntityCollection<County> {
  
  //<editor-fold defaultstate="expanded" desc="Private Properties">

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public CountyCollection() {
    super(County.class);
  }
  //</editor-fold>
}