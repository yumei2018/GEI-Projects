package gov.ca.water.context.session;

import entity.core.filter.ConditionFilter;
import entity.core.util.WebUtil;
import gov.ca.water.entity.ewm.MeasurementMethodType;
import gov.ca.water.entity.ewm.collection.ElevMeasureMethodTypCollection;
import gov.ca.water.entity.ewm.collection.ElevationAccuracyTypeCollection;
import gov.ca.water.entity.ewm.collection.MeasurementAccuracyTypeCollection;
import gov.ca.water.entity.ewm.collection.MeasurementMethodTypeCollection;
import gov.ca.water.entity.ewm.collection.StationUseTypeCollection;
//import gov.ca.water.entity.collection.BasinCollection;
//import gov.ca.water.entity.collection.IssueTypeLookupCollection;
//import gov.ca.water.entity.collection.SegmentReferenceCollection;
//import gov.ca.water.entity.collection.StatusLookupCollection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author soi
 */
public class LookupDataContext {
  
  //<editor-fold defaultstate="exapnded" desc="Private Properties">
  private Map mLookupData;
  
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mLookupData = null;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public LookupDataContext() {
    super();
    this.mLookupData = new HashMap<>();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Static Get Instance">
  /**
   * 
   * @return 
   */
  public static LookupDataContext getInstance(){
    return WebUtil.getContext(LookupDataContext.class);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Get Lookup Data">
  private <O extends Object> O getLookupData(String key) {
    O result = null;
    if (this.mLookupData.containsKey(key)) {
      result = (O) this.mLookupData.get(key);
    }
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Measurement Method Type">
  
  public MeasurementMethodTypeCollection getMeasurementMethodType() {
    return this.getMeasurementMethodType(false);
  }
  
  public final MeasurementMethodTypeCollection getMeasurementMethodType(boolean requery) {
    MeasurementMethodTypeCollection result = null;
    String key = MeasurementMethodTypeCollection.class.getName();
    if ((result = this.getLookupData(key)) == null || requery) {
      result = new MeasurementMethodTypeCollection();
      result.findAll();
      if (!result.isEmpty()) {
        this.mLookupData.put(key, result);
      }
    }
    
    return result;
  }
//</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Get Measurement Accuracy Type">
  
  public MeasurementAccuracyTypeCollection getMeasurementAccuracyType() {
    return this.getMeasurementAccuracyType(false);
  }
  
  public final MeasurementAccuracyTypeCollection getMeasurementAccuracyType(boolean requery) {
    MeasurementAccuracyTypeCollection result = null;
    String key = MeasurementAccuracyTypeCollection.class.getName();
    if ((result = this.getLookupData(key)) == null || requery) {
      result = new MeasurementAccuracyTypeCollection();
      result.findAll();
      if (!result.isEmpty()) {
        this.mLookupData.put(key, result);
      }
    }
    
    return result;
  }
//</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Elevation Accuracy Type">
  
  public ElevationAccuracyTypeCollection getElevationAccuracyType() {
    return this.getElevationAccuracyType(false);
  }
  
  public final ElevationAccuracyTypeCollection getElevationAccuracyType(boolean requery) {
    ElevationAccuracyTypeCollection result = null;
    String key = ElevationAccuracyTypeCollection.class.getName();
    if ((result = this.getLookupData(key)) == null || requery) {
      result = new ElevationAccuracyTypeCollection();
      result.findAll();
      if (!result.isEmpty()) {
        this.mLookupData.put(key, result);
      }
    }
    
    return result;
  }
//</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Get Station Use">
  
  public StationUseTypeCollection getStationUse() {
    return this.getStationUse(false);
  }
  
  public final StationUseTypeCollection getStationUse(boolean requery) {
    StationUseTypeCollection result = null;
    String key = StationUseTypeCollection.class.getName();
    if ((result = this.getLookupData(key)) == null || requery) {
      result = new StationUseTypeCollection();
      result.findAll();
      if (!result.isEmpty()) {
        this.mLookupData.put(key, result);
      }
    }
    
    return result;
  }
//</editor-fold>

  public ElevMeasureMethodTypCollection getElevMeasureMethodType() {
    return getElevMeasureMethodType(false);
  }
  
   public final ElevMeasureMethodTypCollection getElevMeasureMethodType(boolean requery) {
    ElevMeasureMethodTypCollection result = null;
    String key = ElevMeasureMethodTypCollection.class.getName();
    if ((result = this.getLookupData(key)) == null || requery) {
      result = new ElevMeasureMethodTypCollection();
      result.findAll();
      if (!result.isEmpty()) {
        this.mLookupData.put(key, result);
      }
    }
    
    return result;
  }
}
