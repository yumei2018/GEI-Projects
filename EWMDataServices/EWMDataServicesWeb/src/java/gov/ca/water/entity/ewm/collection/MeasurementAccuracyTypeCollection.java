package gov.ca.water.entity.ewm.collection;

import entity.core.EntityCollection;
import gov.ca.water.entity.ewm.ElevationAccuracyType;
import gov.ca.water.entity.ewm.ElevationDataReading;
import gov.ca.water.entity.ewm.MeasurementAccuracyType;
import java.util.Objects;

/**
 *
 * @author clay
 */
public class MeasurementAccuracyTypeCollection extends EntityCollection<MeasurementAccuracyType> {
  
  //<editor-fold defaultstate="expanded" desc="Private Properties">

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public MeasurementAccuracyTypeCollection() {
    super(MeasurementAccuracyType.class);
  }
  //</editor-fold>

  public MeasurementAccuracyType getType(ElevationDataReading elevationReading) {
    MeasurementAccuracyType result = null;
    if (!this.isEmpty()){
      for (MeasurementAccuracyType type : this){
        if (Objects.equals(type.getEwmMeasureAccuracyTypeId(),elevationReading.getEwmElevMeasureMethodTypId())){
          result = type;
          break;
        }
      }
    }
    return result;
  }
}