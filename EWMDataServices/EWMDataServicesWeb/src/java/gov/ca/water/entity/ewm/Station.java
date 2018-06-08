package gov.ca.water.entity.ewm;

import entity.core.delegate.QueryDelegate;
import entity.core.filter.ConditionFilter;
import entity.core.util.EntityUtil;
import gov.ca.water.entity.ewm.base.StationBase;
import gov.ca.water.entity.ewm.collection.ElevationDataReadingCollection;
import gov.ca.water.entity.ewm.collection.StationPerforationCollection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class is auto generated
 */
public class Station extends StationBase<Station> {

  //<editor-fold defaultstate="collapsed" desc="Validate - validate()">
  public static Station validate(String stationId) {
    return Station.validate(Long.parseLong(stationId));
  }
  public static Station validate(Integer stationId) {
    return Station.validate(stationId.longValue());
  }
  public static Station validate(Long stationId) {
    Station result = new Station();
    result.setEwmStationId(ConditionFilter.ConditionOperator.SQL_EQUAL, stationId);
    result.select();
    if (!result.isLoaded()){
      throw new NullPointerException("Station ID does not exist");
    }
    return result;
  }
  //</editor-fold>

  public static boolean softValidateId(String id) {
    return id != null && !id.isEmpty() && id.matches("\\d{5}");
  }

  //<editor-fold defaultstate="expanded" desc="Private Properties">
  private ElevationDataReadingCollection mElevationDataReadings = null;
  private StationPerforationCollection mPerforations = null;
  
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mElevationDataReadings = null;
    this.mPerforations = null;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get ElevationDataReadingCollection - getElevationDataReading()">
  public ElevationDataReadingCollection getElevationDataReading() {
    if (this.isLoaded()){
      if (this.mElevationDataReadings == null){
        this.mElevationDataReadings = new ElevationDataReadingCollection();
        this.mElevationDataReadings.load(this);
      }
    }
    
    return this.mElevationDataReadings;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get HighestPriority ElevationDataReadingCollection - getHighestPriorityElevationDataReading()">
  public ElevationDataReadingCollection getHighestPriorityElevationDataReading() {
    if (this.isLoaded()){
      if (this.mElevationDataReadings == null){
        this.mElevationDataReadings = new ElevationDataReadingCollection();
        this.mElevationDataReadings.loadHighestPriorityRaw(this);
      }
    }
    
    return this.mElevationDataReadings;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Unique ElevationDataReadingCollection - getUniqueElevationDataReading()">
  public ElevationDataReadingCollection getUniqueElevationDataReading() {
    if (this.isLoaded()){
      if (this.mElevationDataReadings == null){
        this.mElevationDataReadings = new ElevationDataReadingCollection();
        this.mElevationDataReadings.loadUnique(this);
      }
    }
    
    return this.mElevationDataReadings;
  }
  //</editor-fold>
  
  public Double getPerforationTop() {
    Double perfTop = null;
    if (this.getPerforationCollection() != null){
      perfTop = this.getPerforationCollection().getTopMin();
    }
    
    return perfTop;
  }

  public Double getPerforationBottom() {
    Double perfBottom = null;
    if (this.getPerforationCollection() != null){
      perfBottom = this.getPerforationCollection().getBottomMax();
    }
    
    return perfBottom;
  }

  private StationPerforationCollection getPerforationCollection() {
    if (this.isLoaded()){
      if (this.mPerforations == null){
        this.mPerforations = new StationPerforationCollection();
        this.mPerforations.load(this);
      }
    }
    
    return this.mPerforations;
  }
}
  