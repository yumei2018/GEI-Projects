package gov.ca.water.entity.ewm.collection;

import entity.core.EntityCollection;
import gov.ca.water.entity.ewm.ElevationAccuracyType;
import gov.ca.water.entity.ewm.ElevationDataReading;
import java.util.Objects;

/**
 *
 * @author clay
 */
public class ElevationAccuracyTypeCollection extends EntityCollection<ElevationAccuracyType> {
  
  //<editor-fold defaultstate="expanded" desc="Private Properties">

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public ElevationAccuracyTypeCollection() {
    super(ElevationAccuracyType.class);
  }
  //</editor-fold>

  public ElevationAccuracyType getType(ElevationDataReading elevationReading) {
    ElevationAccuracyType result = null;
    if (!this.isEmpty()){
      for (ElevationAccuracyType type : this){
        if (Objects.equals(type.getEwmElevationAccuracyTypeId(),elevationReading.getEwmElevationAccuracyTypeId())){
          result = type;
          break;
        }
      }
    }
    return result;
  }
}