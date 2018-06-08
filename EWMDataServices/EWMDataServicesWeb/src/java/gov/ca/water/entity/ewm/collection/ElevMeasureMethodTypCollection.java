package gov.ca.water.entity.ewm.collection;

import entity.core.EntityCollection;
import gov.ca.water.entity.ewm.ElevMeasureMethodTyp;
import gov.ca.water.entity.ewm.ElevationDataReading;
import java.util.Objects;

/**
 *
 * @author clay
 */
public class ElevMeasureMethodTypCollection extends EntityCollection<ElevMeasureMethodTyp> {
  
  //<editor-fold defaultstate="expanded" desc="Private Properties">

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public ElevMeasureMethodTypCollection() {
    super(ElevMeasureMethodTyp.class);
  }
  //</editor-fold>

  public ElevMeasureMethodTyp getType(ElevationDataReading elevationReading) {
    ElevMeasureMethodTyp result = null;
    if (!this.isEmpty()){
      for (ElevMeasureMethodTyp type : this){
        if (Objects.equals(type.getEwmElevMeasureMethodTypId(),elevationReading.getEwmElevMeasureMethodTypId())){
          result = type;
          break;
        }
      }
    }
    return result;
  }
}