package gov.ca.water.math.interpolate;

import com.vividsolutions.jts.geom.Coordinate;
import gov.ca.water.common.events.EventHandler;
import gov.ca.water.common.events.EventInfo;
import gov.ca.water.contours.GridPoint;
import gov.ca.water.contours.intervals.ContourIntervals;
import gov.ca.water.common.io.DataEntry;
import gov.ca.water.contours.ContourGrid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Base Class for all Contour GridPoint Interpolators.
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public abstract class Interpolator implements Serializable {
  
  //<editor-fold defaultstate="collapsed" desc="Protected Logger">
  /**
   * Protected Static Logger object for logging errors, warnings, and info messages.
   */
  protected static final Logger logger = Logger.getLogger(Interpolator.class.getName());
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * Placeholder for the Observed Data Point to use in the interpolation.
   */
  private List<Coordinate> obsData;
  /**
   * 
   */
  private ContourIntervals contours;
  /**
   * Placeholder for the Interpolated values at the Observed Data Point Locations
   * to use in the interpolation.
   */
  private List<GridPoint> obsGridPoints;
  /**
   * Placeholder for a Error Message to be assigned during processing
   */
  private String errorMsg;
  /**
   * The Counter for maintaining the 
   */
  private int updateCount = 0;
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Eventhandlers">
  /**
   * The EventHandler that fires the ObsData Changed Event.
   */
  public final EventHandler ObsDataChanged;
  
  /**
   * Called to fire the ObsData Changed Event.
   */
  protected final void fireObsDataChanged() {
    if (!this.isUpdating()) {
      this.ObsDataChanged.fireEvent(this, new EventInfo());
      //call something here to create a shapefile using the shapes generated      
    }    
  }
  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor
   */
  protected Interpolator(ContourIntervals contours) {
    super();
    if (contours == null) {
      throw new NullPointerException("The Inetrpolator's ContourIntervals cannot be "
              + "unassigned.");
    }
    this.ObsDataChanged = new EventHandler();            
    this.reset();
    this.contours = contours;
    this.contours.IntervalsChanged.add(this, "onContourIntervalChanged");
  }

  /**
   * {@inheritDoc}
   * <p>OVERRIDE: Call super method before cleaning EventHandler(s) and call reset to
   * clear the local resources.</p>
   */
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); 
    if (this.ObsDataChanged != null) {
      this.ObsDataChanged.clear();
    }
    if (contours != null) {
      this.contours.IntervalsChanged.remove(this);
    }
    this.contours = null;
    this.reset();
  }
  // </editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Managing the isUpdating State">
  /**
   * Get the isUpdating State
   * @return true if this.updateCount > 0
   */
  public boolean isUpdating() {
    return (this.updateCount > 0);
  }
  
  /**
   * Called to begin the Updating process. It will prevent firing of the ObsDataChanged
   * event while this.isUpdating.
   * <p><b>IMPORTANT:</b> This call must be followed with a call to this.endUpdating.</p>
   */
  public void beginUpdating() {
    this.updateCount++;
  }
  
  /**
   * Called to end the Updating process. It will decrement the updateCount and if the 
   * updateCount = 0, it will fire the ObsDataChanged event.
   * <p><b>IMPORTANT:</b> This call must be preceeded with a call to this.beginUpdating.
   * </p>
   */
  public void endUpdating() {
    if (this.updateCount > 0) {
      this.updateCount--;
    }
    if (this.updateCount == 0) {
      this.fireObsDataChanged();
    }
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Error Handling">
  /**
   * Check is a error message is assigned
   * @return (this.errorMsg != null)
   */
  public boolean hasError() {
    return (this.errorMsg != null);
  }
  
  /**
   * Clear previously set errors
   */
  public void clearErrors() {
    this.errorMsg = null;
  }
  
  /**
   * Get the current error message
   * @return the assigned value (can be null)
   */
  public String getErrorMsg() {
    return this.errorMsg;
  }
  
  /**
   * Assigned a new error message. Ignored if ""|null. if a prior message exist, the new
   * message will be appended with a "\n" separator.
   * @param errMsg the new error message
   */
  public void setErrorMsg(String errMsg) {
    errMsg = DataEntry.cleanString(errMsg);
    if (errMsg == null) {
      return;
    }
    if (this.errorMsg == null) {
      this.errorMsg = errMsg;
    } else {
      this.errorMsg += "\n " + errMsg;
    }
  }
  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Private/Protected methods">
  /**
   * Called to reset the Interpolator. It calls this.onReset (between a try-catch to
   * trap and ignore errors, before clearing the StationPoints.
   */
  private void reset() {
    try {
      this.onReset();
    } catch (Exception exp) {
    }
    if (obsData != null) {
      this.obsData.clear();
    }
    this.obsData = null;
    if (obsGridPoints != null) {
      this.obsGridPoints.clear();
    }
    this.obsGridPoints = null;
  }
  
  /**
   * <p>Get the Cached list of interpolated GridPoints for the ObsData locations. It will
   * initiate the list if unassigned and !this.isEmpty be creating a 2D array with one
   * column and a row for each observed value and then call {@linkplain #execute(
   * gov.ca.water.contours.GridPoint[][], int, int) execute(gridPoints, 1,
   * this.obsDataSize).</p>
   * <p><b>NOTE:</b> The list will be reset/recalculated if the Observed data or the
   * Contour inetrvals change.</p>
   * @return the cached list of grid points.
   */
  protected final List<GridPoint> getObsGridPoints() {
    if ((this.obsGridPoints == null) && (!this.isEmpty())) {
      GridPoint[][] gridPoints = new GridPoint[1][this.obsData.size()];
      for (int iCoord = 0; iCoord < this.obsData.size(); iCoord++) {
        GridPoint point = new GridPoint(this.obsData.get(iCoord));
        gridPoints[0][iCoord] = point;
      }
      
      this.execute(gridPoints, 1, this.obsData.size());
      this.obsGridPoints = new ArrayList<>(Arrays.asList(gridPoints[0]));
    }
    return this.obsGridPoints;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Final Methods">
  /**
   * Check if the Observed Data has not been assigned.
   * @return true if (this.obsData=null|isEMpty).
   */
  public final boolean isEmpty() {
    return ((this.obsData == null) || (this.obsData.isEmpty()));
  }
  
  /**
   * Get a reference to the current Contour Intervals
   * @return the assigned instance.
   */
  public ContourIntervals getContourIntervals() {
    return this.contours;
  }
  /**
   * Get the number of known Station Points
   * @return this.obsData.size or 0 if this.obsData=null.
   */
  public final int getObsDataSize() {
    return (this.obsData == null)? 0: this.obsData.size();
  }
  
  /**
   * <p>Called to reset the Interpolator's Observed data - it also clear the
   * ContoruInetrvals</p>
   * <p><b>NOTE:</b> It fires the {@linkplain #ObsDataChanged ObsDataChanged} event.</p>
   */
  public void clearObsData() {
    try {
      this.beginUpdating();
      this.reset();
      if (this.contours != null) {
        this.contours.resetIntervals();
      }
    } finally {
      this.endUpdating();
    }
  }
  
  /**
   * <p>Set the observed data Points. It call {@linkplain #reset() reset} before calling
   * class {@linkplain #onSetObservedData(java.util.List) onSetObservedData} which sis
   * called BEFORE assigning the returned list as the Interpolator's obsData. the list is
   * not assigned if stationPoints=null|isEmpty.</p>
   * <p><b>NOTE:</b> It fires the {@linkplain #ObsDataChanged ObsDataChanged} event.</p>
   * @param obsData the observed data Points to assign for interpolation.
   * @param contours the Contour Interval definition used by the Interpolator.
   * @return true if the process was successful and false if this.hasError().
   */
  public final boolean setObsData(List<Coordinate> obsData) {
    this.reset();    
    try {
      this.beginUpdating();
      if ((obsData == null) || (obsData.isEmpty())) {
        throw new NullPointerException("The Observed Data is unassigned or empty.");
      }
            
      if (this.contours == null) {
        throw new Exception("The Interolator's Contour Intervals is unassigned.");
      }
      
      this.onSetObservedData(obsData);
      if ((obsData != null) && (!obsData.isEmpty())) {
        this.obsData = obsData;
        this.contours.calcIntervals(obsData);
      } else {
        this.contours.resetIntervals();
      }     
    } catch (Exception exp) {
      this.reset(); 
      this.setErrorMsg(exp.getMessage());
      logger.log(Level.WARNING, "{0}.setObsData Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    } finally {
      this.endUpdating();
    }
    return (!this.hasError());
  }
  
  /**
   * Get a list of all the observed data Points. Changing this list will not changes the
   * Interpolator's Observed Data
   * @return a cloned list of observed Data Points
   */
  public final List<Coordinate> getObsData() {
    return (this.isEmpty())? new ArrayList<Coordinate>(): new ArrayList<>(this.obsData);
  }
  
  /**
   * Called to get a list ObsGridPoints (i.e., the GridPoint with interpolated z-Value
   * at the obsData coordinates) that is contained within the GridRect
   * @param gridRec the GridRect to use in the search
   * @return the list of points - an empty list if <tt>gridRec</tt> is empty or 
   * this.isEmpty or no points fall within the GridRect.
   */
  public List<GridPoint> getObsGridPointByRect(ContourGrid.GridRect gridRec) {
    List<GridPoint> result = new ArrayList<>();
    List<GridPoint> obsPoints = null;
    if ((gridRec != null) && ((obsPoints = this.getObsGridPoints()) != null) &&
            (!obsPoints.isEmpty())) {
      for (GridPoint gridPoint : obsPoints) {
        if (gridRec.contains(gridPoint)) {
          result.add(gridPoint);
        }
      }
    }
    return result;
  }
  
  /**
   * <p>Called to interpolate the z-value of the gridPoint based on the assigned know
   * station z-values. It validates that <tt>gridPoints</tt> is not null and not empty 
   * and that the Interpolator Observed data ad Contour Intervals are assigned before 
   * it calls {@linkplain #onExecute(java.util.List,
   * gov.ca.water.contours.intervals.ContourIntervals) onExecute} to custom handle the 
   * request.</p>
   * <p>All Errors are trapped, logged and assigned to this.errorMsg.</p>
   * @param gridPoints the 2D array of gridPoints to process.
   * @param numCol the number of columns in the 2D array of gridPoints.
   * @param numRow the number of rows in the 2D array of gridPoints.
   * @return true if the process was successful and false if this.hasError().
   */
  public final boolean execute(GridPoint[][] gridPoints, int numCol, int numRow) {
    try {
      this.clearErrors();
      if ((gridPoints == null) || (numCol <= 0) || (numRow <= 0)) {
        throw new Exception("The GridPoints is unassigned or empty.");
      }      
      
      if ((this.isEmpty()) || (this.contours == null)) {
        throw new Exception("The Interpolators Observed Data or Contour Intervals is "
                + "not defined.");
      }
      
      GridPoint[] gridCol = null;
      for (int iCol = 0; iCol < numCol; iCol++) {
        gridCol = gridPoints[iCol]; 
        this.onExecute(gridCol);
      }      
    } catch (Exception exp) {
      this.setErrorMsg(exp.getMessage());
      logger.log(Level.WARNING, "{0}.execute Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }
    return (!this.hasError());
  }
  
  /**
   * <p>Called to interpolate the z-value of the gridPoint based on the assigned know
   * station z-values. It validates that <tt>gridPoints</tt> is not null and not empty 
   * and that the Interpolator Observed data ad Contour Intervals are assigned before 
   * it calls {@linkplain #onExecute(gov.ca.water.contours.GridPoint[]) onExecute} to 
   * custom handle the request.</p>
   * <p>All Errors are trapped, logged and assigned to this.errorMsg.</p>
   * @param gridPoints the array of gridPoints to process.
   * @return true if the process was successful and false if this.hasError().
   */
  public final boolean execute(GridPoint...gridPoints) {
    try {
      this.clearErrors();
      if ((gridPoints == null) || (gridPoints.length == 0)) {
        throw new Exception("The GridPoints is unassigned or empty.");
      }      
      
      if ((this.isEmpty()) || (this.contours == null)) {
        throw new Exception("The Interpolators Observed Data or Contour Intervals is "
                + "not defined.");
      }
      
      this.onExecute(gridPoints);
    } catch (Exception exp) {
      this.setErrorMsg(exp.getMessage());
      logger.log(Level.WARNING, "{0}.execute Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }
    return (!this.hasError());
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Protected Can-Override/Abstract Methods">
  /**
   * <p>CAN OVERRIDE: Call to reset any local parameters before initiating the 
   * Interpolator. The base method does nothing.</p>
   * @throws Exception - any exception thrown during this process will be trapped and 
   * ignored.
   */
  protected void onReset() throws Exception {}
  
  /**
   * <p>CAN OVERRIDE: Called by {@linkplain #setObsData(java.util.List)
   * setObsData} BEFORE assigning the specified stationPoints as the interpolator's
   * stationPoints for inheritors to pre-process the stationPoints. If this method clear
   * the list, it will not be assigned as the the interpolator stationPoints.</p>
   * <p>The base method does nothing.</p> 
   * @param stationPoints the not empty list of points.
   */
  protected void onSetObservedData(List<Coordinate> stationPoints) throws Exception {
  }
  
  /**
   * <p>ABSTRACT: Called by {@linkplain #execute(java.util.List,
   * gov.ca.water.contours.intervals.ContourIntervals) execute} -after checking that the
   * gridPoints and contours are assigned, and after resetting the gridPoints- to handle
   * the custom execution of the Interpolation request.</p>
   * <p><b>NOTE:</b> The Interpolator's Contour Interval definition should to pass in 
   * when calling the {@linkplain GridPoint#setZ(double, 
   * gov.ca.water.contours.intervals.ContourIntervals) GridPoint.setZ(zValue, 
   * this.contours)} method to assign each GridPoint's z-Value.</p>
   * @param gridPoints the list of gridPoints to process.
   * @throws Exception on error - errors will be trapped and assigned as this.errorMsg
   */
  protected abstract void onExecute(GridPoint[] gridPoints) 
          throws Exception;  
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Event Methods">
  /**
   * <p>EventHandler: Handles the Interval Changed for Contour</p>
   * <p>Reset the cached obsGridoint force a recalculation of the GridPoints. It also
   * fires Event[ObsDataChanged].</p>
   * @param sender the event sender
   * @param eventInfo the event info
   */
  public final void onContourIntervalChanged(Object sender, EventInfo eventInfo) {
    if ((this.contours != null) && (sender == this.contours)) {
      this.obsGridPoints = null;
      this.fireObsDataChanged();
    }
  }
  //</editor-fold>
}
