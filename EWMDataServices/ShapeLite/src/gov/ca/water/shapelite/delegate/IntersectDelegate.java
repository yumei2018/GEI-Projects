package gov.ca.water.shapelite.delegate;

import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;

/**
 *
 * @author clay
 */
public abstract class IntersectDelegate extends EventDelegate {

  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  private FeatureSet mThisSet;
  private FeatureSet mOtherSet;

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mThisSet = null;
    this.mOtherSet = null;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public IntersectDelegate() {
    super(null);
  }
  
  public IntersectDelegate(Object listener) {
    super(listener);
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="On Event">
  @Override
  public void onEvent(Object... args){
    try {
      Feature feature = (Feature) args[0];
      Feature otherFeature = (Feature) args[1];
      Feature resultFeature = (Feature) args[2];
      this.onIntersect(feature, otherFeature, resultFeature);
    }
    catch(Exception ex){
      ex.printStackTrace();
    }
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="On Intersect">
  public abstract void onIntersect(Feature feature, Feature otherFeature, Feature resultFeature);
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Set This Set">
  public final <ID extends IntersectDelegate> ID setFeatureSet(FeatureSet fs) {
    this.mThisSet = fs;
    return (ID) this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Set This Set">
  public final <ID extends IntersectDelegate> ID setOtherSet(FeatureSet fs) {
    this.mOtherSet = fs;
    return (ID) this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Feature Set">
  public final FeatureSet getFeatureSet() {
    return this.mThisSet;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Feature Set">
  public final FeatureSet getOtherSet() {
    return this.mOtherSet;
  }
  //</editor-fold>
}
