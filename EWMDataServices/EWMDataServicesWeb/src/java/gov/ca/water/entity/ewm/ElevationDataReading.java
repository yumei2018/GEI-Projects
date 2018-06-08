package gov.ca.water.entity.ewm;

import gov.ca.water.context.session.LookupDataContext;
import gov.ca.water.entity.ewm.base.ElevationDataReadingBase;

/**
 * This class is auto generated
 */
public class ElevationDataReading extends ElevationDataReadingBase<ElevationDataReading> {

  //<editor-fold defaultstate="expanded" desc="Private Properties">

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
  }
  //</editor-fold>
  
  public ElevMeasureMethodTyp getElevMeasureMethodType(){
    return LookupDataContext.getInstance().getElevMeasureMethodType().getType(this);
  }
  public ElevationAccuracyType getElevationAccuracyType(){
    return LookupDataContext.getInstance().getElevationAccuracyType().getType(this);
  }
}
  