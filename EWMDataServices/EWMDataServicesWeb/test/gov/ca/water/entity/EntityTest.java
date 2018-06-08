package gov.ca.water.entity;

import entity.core.filter.ConditionFilter;
import gov.ca.water.entity.ewm.collection.StationCollection;
import org.junit.Test;

public class EntityTest {

  @Test
  public void entityT1() {
    CalsimTs e = new CalsimTs();
    e.setObjectid(ConditionFilter.ConditionOperator.SQL_EQUAL, 1l).select();
    System.out.println(e.toJson().toString(2));
    
  }
  @Test
  public void entityT12() {
    PrecipEtTsChgFactors e = new PrecipEtTsChgFactors();
    e.setObjectid(ConditionFilter.ConditionOperator.SQL_EQUAL, 1l).select();
    System.out.println(e.toJson().toString(2));
    
  }
  @Test
  public void entityT13() {
    StationCollection e = new StationCollection();
    e.findAll(ConditionFilter.createInstance("ewmStationId", ConditionFilter.ConditionOperator.SQL_EQUAL, 27866));
    System.out.println(e.toJson().toString(2));
    
  }
  @Test
  public void entityT14() {
    StationCollection e = new StationCollection();
    e.getExtent(-121.62222284303787, 38.65990666053913,-121.29263299928833,38.78914306931892);
    System.out.println(e.toJson().toString(2));
    
  }
}
