package gov.ca.water.contours;

import gov.ca.water.common.events.EventHandler;
import gov.ca.water.common.events.EventInfo;
import gov.ca.water.contours.intervals.ContourIntervals;
import gov.ca.water.math.interpolate.Interpolator;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.util.Exceptions;

/**
 * <p>A ContourBuilder wrap the three components required in the building a set of contour
 * geometry for an Area-of-interest (as defined by the {@linkplain ContourGrid} and based
 * in a set of observed data, a interpolation technique, and defined {@linkplain
 * ContourIntervals} as defined by the {@linkplain Interpolator}. The resulting contours
 * are added to the {@linkplain ContourMap}, which contains the contour geometry for each
 * contour level. </p>
 * <p>The ContourMap can be utilize to post-process the contour data (e.g., generating an
 * ESRI FeatureClass which can be exported to a shape file, or for rendering onto a
 * map.</p>
 * <p>The ContourBuilder is listening to the {@linkplain Interpolator#ObsDataChanged}
 * events, which is fired with changes to the assigned Observed Data or with changes to
 * the ContourInterval settings. The contour's will rebuild every time the event is 
 * fired.</p>
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class ContourBuilder {
  
  //<editor-fold defaultstate="collapsed" desc="Initaite the Static Logged">
  /**
   * Protected Static Logger object for logging errors, warnings, and info messages.
   */
  protected static final Logger logger = Logger.getLogger(ContourBuilder.class.getName());
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Field">
  /**
   * The ContourBuilder's ContourGrid for the specified Area-of-Interest
   */
  public final ContourGrid contourGrid;
  /**
   * The ContourBuilder's Interpolator to use in generating the Contours
   */
  public final Interpolator interpolator;
  /**
   * The Output Contour Map containing the contour geometry
   */
  public final ContourMap contourMap;
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Events">
  /**
   * The EventHandler that fires the Contours Changed Event.
   */
  public final EventHandler ContoursChanged;
  
  /**
   * Called to fire the Contours Changed Event.
   */
  protected final void fireContoursChanged() {
    this.ContoursChanged.fireEvent(this, new EventInfo());
  }
  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor
   */
  public ContourBuilder(ContourGrid contourGrid, Interpolator interpolator) {
    if ((contourGrid == null) || (contourGrid.isEmpty())) {
      throw new NullPointerException("The ContourGrid cannot be unassigned.");
    }
    
    if (interpolator == null) {
      throw new NullPointerException("The Interpolator cannot be unassigned.");
    }
    
    ContourIntervals contours = interpolator.getContourIntervals();
    if (contours == null) {
      throw new NullPointerException("The Interpolator's ContourIntervals is "
              + "unassigend.");
    }
    
    this.ContoursChanged = new EventHandler();
    this.contourGrid = contourGrid;
    this.interpolator = interpolator;
    this.contourMap = new ContourMap(contours);
    this.interpolator.ObsDataChanged.add(this, "onInterpolatorObsDataChanged");
  }

  /**
   * {@inheritDoc}
   * <p>OVERRIDE: Call super method followed by a call to this.resetAll</p>
   */
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); 
    this.ContoursChanged.clear();
    this.resetAll();    
  }
  
  /**
   * Called to reset all local resources on finalization or when re-initiated.
   */
  private void resetAll() {
    this.resetContours();
    if (this.interpolator != null) {
      this.interpolator.ObsDataChanged.remove(this);
    }
  }
  // </editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Check if the ContourBuild isEmpty (i.e., not full initiated).
   * @return true if this.contourGrid=null or this.interpolator=null or
   * this.interpolator.contourInetrvals=null.
   */
  public boolean isEmpty() {
    return ((this.contourGrid == null) || (this.interpolator == null) ||
            (this.interpolator.getContourIntervals() == null));
  }
  
  /**
   * Get a reference to the ContourBuilder's ContourGrid that contains the current
   * ContourGrid geometry.
   * @return a reference to the internal instance.
   */
  public ContourGrid getContourGrid() {
    return this.contourGrid;
  }
  
  /**
   * Get a reference to the ContourBuilder's Interpolator that contains the current
   * Observed Data.
   * @return a reference to the internal instance.
   */
  public Interpolator getInterpolator() {
    return this.interpolator;
  }
  
  /**
   * Get a reference to the ContourBuilder's Interpolator's Contour Intervals that 
   * contains the current contour settings.
   * @return a reference to this.interpolator.contourInetrvals.
   */
  public ContourIntervals getContourIntervals() {
    return (this.interpolator == null)? null: this.interpolator.getContourIntervals();
  }
  
  /**
   * Get a reference to the ContourBuilder's ContourMap that contains the current
   * contour geometry.
   * @return a reference to the internal instance.
   */
  public ContourMap getContourMap() {
    return this.contourMap;
  }
  
  /**
   * Reset the Clear the Interpolator's Data and reset the DataGrid
   */
  public void resetObsData() {
    if (this.interpolator != null) {
      this.interpolator.clearObsData();
    }
    this.resetContours();
  }
  
  /**
   * Get whether the contourBuild has a set of buildContours.
   * @return true if (this.contourMap != null|Empty).
   */
  public boolean hasContours() {
    boolean result = false;
    if (this.contourMap != null) {
      result = (!this.contourMap.isEmpty());
    }
    return result;
  }
  
  /**
   * Reset the ContourGrid and the ContourMap's geometry.
   */
  private void resetContours() {
    if (this.contourMap != null) {
      this.contourMap.clearGeometry();
    }
    
    if (this.contourGrid != null) {
      this.contourGrid.resetContours();
    }
  }
  
  /**
   * <p>Called to build the contours. It calls {@linkplain #resetIntervals()} before
   * calling {@linkplain ContourGrid#buildContours(
   * gov.ca.water.math.interpolate.Interpolator, gov.ca.water.contours.ContourMap) 
   * this.contourGrid.buildContours} passing in this.interpolator and this.contorMap.</p>
   * <p><b>NOTE:</b> There is no need to call this method after assigning new Observed 
   * station data to the Interpolator or when making changed to the ContourIntervals.
   * The ContourBuilder listen to the Interpolator.ObsDataChanged event, which is fired
   * when Interpolator's observed data or the ContourIntervals' setting change, and
   * will call this method automatically. Alway check this.hasError after changing the
   * observed data or any settings to validate the contour rebuild was successful.</p>
   * @return true (!this.hasErrors). if the process was executed with any errors.
   */
  public boolean buildContours() {
    try {
      this.resetContours();
      if (!this.isEmpty()) {
        this.contourGrid.buildContours(this.interpolator, this.contourMap);
      }
    } catch (Exception exp) {
      logger.log(Level.WARNING, "{0}.rebuildContours Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
      this.contourGrid.setErrorMsg(exp.getMessage());
    } finally {        
      this.fireContoursChanged();
    }
    return (!this.contourGrid.hasError());    
  }
  
  /**
   * Check is the Contour Builder has errors in the last update of the contours
   * @return this.contourGrid.hasError
   */
  public boolean hasError() {
    return this.contourGrid.hasError();   
  }
  
  /**
   * Get the error message of the last update of the contours. 
   * @return this.contourGrid.errorMsg - could be null is (!this.hasError).
   */
  public String getErrorMsg() {
    return this.contourGrid.getErrorMsg();   
  }
  //</editor-fold>
    
  //<editor-fold defaultstate="collapsed" desc="Event handling Methods">
  /**
   * <p>EventHandler: Handles the ObsData Changed for Interpolator. If the sender is
   * this.interpolator, call {@linkplain #buildContours()}.</p>
   * @param sender the event sender
   * @param eventInfo the event info
   */
  public final void onInterpolatorObsDataChanged(Object sender, EventInfo eventInfo) {
    if ((sender != null) && (sender == this.interpolator)) {
      this.buildContours();
    }
  }
  //</editor-fold>
}
