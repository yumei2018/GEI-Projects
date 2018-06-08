package gov.ca.water.entity.ewm.collection;

import entity.core.EntityCollection;
import gov.ca.water.entity.ewm.MasterSite;

/**
 *
 * @author clay
 */
public class MasterSiteCollection extends EntityCollection<MasterSite> {
  
  //<editor-fold defaultstate="expanded" desc="Private Properties">

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public MasterSiteCollection() {
    super(MasterSite.class);
  }
  //</editor-fold>
}