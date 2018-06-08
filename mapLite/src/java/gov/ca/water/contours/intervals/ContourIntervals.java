package gov.ca.water.contours.intervals;

import com.vividsolutions.jts.geom.Coordinate;
import gov.ca.water.common.events.EventHandler;
import gov.ca.water.common.events.EventInfo;
import gov.ca.water.common.io.DataEntry;
import gov.ca.water.math.interpolate.Interpolator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Abstract Method for setting the Contour Intervals for the Contour Building process.
 * Once initiated it is assigned to the Observed Data {@linkplain Interpolator}.
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public abstract class ContourIntervals {
  
  //<editor-fold defaultstate="collapsed" desc="Static Logger">
  /**
   * Protected Static Logger object for logging errors, warnings, and info messages.
   */
  protected static final Logger logger =
          Logger.getLogger(ContourIntervals.class.getName());
  //</editor-fold>
       
  //<editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The ContourIntervals
   */
  private List<Double> contours;
  /**
   * A flag for whether the low contour should be hidden (default = null|false)
   */
  private Boolean hideLoContour;
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="EventHandler">
  /**
   * The EventHandler that fires the Intervals Changed Event.
   */
  public final EventHandler IntervalsChanged;
  
  /**
   * Called to fire the Intervals Changed Event.
   */
  protected final void fireIntervalsChanged() {
    this.IntervalsChanged.fireEvent(this, new EventInfo());
  }
  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor
   */
  public ContourIntervals() {
    super();    
    this.IntervalsChanged = new EventHandler();
    this.contours = null;
    this.hideLoContour = null;
  }

  /**
   * {@inheritDoc}
   * <p>OVERRIDE: Call the super method before clearing this.IntervalsChanged 
   * EventHandler./</p>
   */
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); 
    if (this.IntervalsChanged != null) {
      this.IntervalsChanged.clear();
    }
    this.resetIntervals();
  }
  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Private/Protected methods">  
  /**
   * <p>ABSTRACT: Called {@linkplain #resetIntervals() this.resetIntervals()} to reset any
   * resources related to the contour intervals - NOT the ContoruInetrval settings (e.g.,
   * this.hideLoContour).</p>
   * @throws Exception any error occurred during this process.
   */
  protected abstract void onResetIntervals() throws Exception ;
  
  /**
   * Get whether the low contour should be hidden (default = false)
   * @return the assigned value
   */
  protected final void setHideLoContour(Boolean hideLoContour) {
    this.hideLoContour = 
                    ((hideLoContour == null) || (!hideLoContour))? null: hideLoContour;
  }
  
  /**
   * <p>Called by inheritors to assign the contours and assign the new Contour Intervals.
   * It calls {@linkplain #resetIntervals() this.resetIntervals}, validates the new
   * contours, before assigning and sorting the contours intervals.</p>
   * <p><b>NOTE:</b> The method always fire the IntervalsChanged event. even if the 
   * contours are reset and failed to set.</p>
   * @param contours the list of contour values (the list will be sorted from low to high.
   * @throws It traps, log, and throws any exception that may occur. It also reset the 
   * contours on any exception.
   */
  protected final void setIntervals(List<Double> contours) throws Exception {
    try {
      this.resetIntervals();
      this.validateContours(contours);      
      this.contours = new ArrayList<>(contours);
      Collections.sort(this.contours);
    } catch (Exception exp) {
      this.resetIntervals();
      logger.log(Level.WARNING, "{0}.initIntervals Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
      throw exp;
    } finally {
      this.fireIntervalsChanged();
    }
  }
  
  /**
   * Called to validate the Contour Intervals
   * @throws Exception 
   */
  private void validateContours(List<Double> contours) throws Exception {
    if ((contours == null) || (contours.size() < 2)) {
      throw new Exception("The Contour Intervals cannot be undefined and "
              + "requires at lease two contours.");
    }
    
    for (int i = 0; i < contours.size()-1; i++) {
      for (int j = i+1; j < contours.size(); j++) {
        if (DataEntry.isEq(contours.get(i), contours.get(j))) {
          throw new Exception("The Contour Intervals contains duplicate values.");
        }
      }
    }
  }
  // </editor-fold>  
  
  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Get whether the low contour should be hidden (default = false)
   * @return the assigned value
   */
  public final Boolean getHideLoContour() {
    return ((this.hideLoContour != null) && (this.hideLoContour));
  }
  
  /**
   * Check if the ContourIntervals is empty/undefined
   * @return true if (this.increment or this.loValue) = NaN, or this.numIntervals &lt; 2.
   */
  public final boolean isEmpty() {
    return ((this.contours == null) || (this.contours.size() < 2));
  }
  
  /**
   * Get the lowest value in the Contour ContourIntervals
   * @return this.this.increments.get(0) or null if this.isEmpty
   */
  public final double getLoValue() {
    return (this.isEmpty())? Double.NaN: this.contours.get(0);
  }
  
  /**
   * Get the Highest value in the Contour ContourIntervals
   * @return this.increments.get(this.increments.size()-1) or NaN is this.isEmpty
   */
  public final double getHiValue() {
    int index = this.contours.size()-1;
    return (this.isEmpty())? Double.NaN: this.contours.get(index);
  }
  
  
  /**
   * The number of contours
   * @return the size of this.increments or 0 if this.isEmpty
   */
  public final int getNumContours() {
    return (this.isEmpty())? 0: this.contours.size();
  }
  
  /**
   * Get the Lo-Value of Interval[index]
   * @param index the interval index in range [0..(this.getNumContours()-1)]
   * @return the lower interval value or NaN is this.isEmpty
   */
  public final double getContour(int index) {
    double result = Double.NaN;
    if (!this.isEmpty()) {
      if ((index < 0) || (index >= this.getNumContours())) {
        throw new ArrayIndexOutOfBoundsException("Contour Index out of Bound[0.." +
                Integer.toString((this.getNumContours()-1)) + "].");
      }
      result = this.contours.get(index);
    }
    return result;
  }  
  
  /**
   * Get the Contour Index for the specified value
   * @param value the value to search for
   * @return a value [0..this.numCountour-1] if found or -1 if not found.
   */
  public final int getContourIndex(double value) {
    int result = -1;
    if ((!Double.isNaN(value)) && (!this.isEmpty())) {
      for (int iIdx = 0; iIdx < contours.size(); iIdx++) {
        if (DataEntry.isLT(value, contours.get(iIdx))) {
          result = (iIdx-1);
          break;
        }
      }
    }
    return result;
  }
  
  /**
   * <p>Called to reset the previously set Contours. This method clear and reset 
   * this.contours, before calling {@linkplain #onResetIntervals() this.onResetIntervals(),
   * whihc should be implemented by inheritors to handle teh reset of contours on a local 
   * level. All execptions are trapped and logged.</p> 
   * <p><b>NOTE:</b> This method should only be called before recalculating a new set
   * of contours (e.g., when the value range ot settings have changed). <u>It does not 
   * fire the IntervalsChanged event.</u></p>
   */
  public final void resetIntervals() {
    if (this.contours != null) {
      this.contours.clear();
    }
    this.contours = null;    
    try {
      this.onResetIntervals();
    } catch (Exception exp) {
      logger.log(Level.WARNING, "{0}.resetContours Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }
  }
  
  /**
   * Called by the Interpolator when the new Observed Data is assigned to
   * update the Contour Intervals. If extract an array of Coordinate z-Values and call
   * the custom {@linkplain #calcIntervals(double[]) this.calcIntervals(zValue Array)} 
   * to update the Contour Inetrvals.
   * @param obsData the new observed data points
   * @throws Exception if the process failed.
   */
  public final void calcIntervals(List<Coordinate> obsData) throws Exception {
    try {
      this.resetIntervals();
      if ((obsData == null) || (obsData.isEmpty())) {
        return;
      }
      
      double[] zValues = new double[obsData.size()];
      for (int i = 0; i < obsData.size(); i++) {
        Coordinate coordinate = obsData.get(i);
        zValues[i] = coordinate.getOrdinate(Coordinate.Z);
      }
      //Double minValue = DataEntry.Min(zValues);
      //Double maxValue = DataEntry.Max(zValues);
      this.calcIntervals(zValues);
    } catch (Exception exp) {
      this.resetIntervals();
      logger.log(Level.WARNING, "{0}.calcIntervals Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
      throw exp;
    }
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Abstract Method">
  /**
   * <p>ABSTRACT: Called to update the Contour Intervals based on the specified range (or
   * array) of values. This method is called from {@linkplain #calcIntervals(
   * java.util.List) this.calcIntervals(obsData)} or can be called directly.</p> 
   * <p><b>NOTE:</b> This method should call {@linkplain #setIntervals(java.util.List) 
   * this.setIntervals} to assign the calculated intervals, which will fire the 
   * {@linkplain #IntervalsChanged IntervalsChanged} event.</p>
   * @param obsData the new observed data points
   * @throws Exception if the process failed.
   */
  public abstract void calcIntervals(double...valRange) throws Exception;
  
  //<editor-fold defaultstate="collapsed" desc="Object Overrride">
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: Get the ContourInetrvals as a string: "ContourIntervals[hideLoContour =
   * ??; Intervals[??,??,..]."</p>
   */
  //</editor-fold>
  
  /**
   * <p>CAN OVERRIDE: Inheritors can override this method to assign additional 
   * parameters to target, but must call the base method. The base method assign 
   * this.hideLoContour to the target. The process is skipped if target=null.</p>
   * @param target the ContourIntervals to update. 
   */
  protected void assignTo(ContourIntervals target) {
    if (target != null) {
      target.hideLoContour = this.hideLoContour;
    }
  }
  
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: make the method publicly visible and throw a CloneNotSupportedException
   * to force inheritors to override the method</p>
   */
  @Override
  public ContourIntervals clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException("Cloning not implements by " +
            this.getClass().getSimpleName()); 
  }
  
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: Get the ContourInetrvals as a string: "ContourIntervals[hideLoContour =
   * ??; Intervals[??,??,..]."</p>
   */
  @Override
  public String toString() {
    String result = null;
    if (this.isEmpty()) {
      result = "Empty";
    } else {
      result = "\thideLoContour = " + this.getHideLoContour() + ";\n";
      result = "\tInterval Count = " + Integer.toString(this.getNumContours() - 1) 
              + ";\n";
      result += "\tIntervals[";
      boolean isFirst = true;
      for (Double val : this.contours) {
        if (isFirst) {
          result += val;
          isFirst = false;
        } else {
          result += " ," + val;
        }
      }
      result += "]";
    }
    return result;
  }
  //</editor-fold>
}
