package gov.ca.water.entity.ewm.collection;

import entity.core.EntityCollection;
import gov.ca.water.entity.ewm.ElevationDataReading;
import gov.ca.water.entity.ewm.MeasurementMethodType;
import java.util.Objects;

/**
 *
 * @author clay
 */
public class MeasurementMethodTypeCollection extends EntityCollection<MeasurementMethodType> {
  
  //<editor-fold defaultstate="expanded" desc="Private Properties">

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public MeasurementMethodTypeCollection() {
    super(MeasurementMethodType.class);
  }
  //</editor-fold>

  public MeasurementMethodType getType(ElevationDataReading elevationReading) {
    MeasurementMethodType result = null;
    if (!this.isEmpty()){
      for (MeasurementMethodType type : this){
        if (Objects.equals(type.getEwmMeasurementMethodTypeId(),elevationReading.getEwmElevMeasureMethodTypId())){
          result = type;
          break;
        }
      }
    }
    return result;
  }
}