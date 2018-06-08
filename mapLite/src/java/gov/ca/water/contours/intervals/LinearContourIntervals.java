package gov.ca.water.contours.intervals;

import gov.ca.water.common.io.DataEntry;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class LinearContourIntervals extends ContourIntervals {

  //<editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The Min/Max value from which the contours were calculates
   */
  private Double[] valueRange;
  /**
   * A flag controlling whether the range is zero bound (no negative values and the
   * contours must include a zero contour - even though it might not be shown)
   */
  private Boolean zeroBound;
  /**
   * The calculated contour interval.
   */
  private Double contourInc = null;
  /**
   * The Number of contour interval to calculate.
   */
  private Integer numIntervals = null;
  /**
   * A flag controlling whether the contourInc or the NumIntervals is calculated.
   * (default = false|numIntervals must be set)
   */
  private Boolean fixedInterval = null;
  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor
   */
  public LinearContourIntervals() {
    super();   
    this.resetSettings();
    this.resetValueRange();
  }
  
  /**
   * Call to reset the all ContourInetrval settings (not the valeuRange) to their
   * default values.
   */
  private void resetSettings() {
    this.zeroBound = null;
    this.contourInc = null;
    this.numIntervals = null;
    this.fixedInterval = null;
    this.setHideLoContour(false);
  }
  
  /**
   * Call to reset the value range
   */
  private void resetValueRange() {
    this.valueRange = null;
    if (this.isFixedInterval()) {
      this.numIntervals = null;
    } else {
      this.contourInc = null;
    }
  }
  // </editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Get whether this is a zero-bound contour range. If zero-bound then no negative
   * contour intervals are considered and the contour range include a zero contour
   * - even though it might be hidden). Default=false
   * @return the assigned setting
   */
  public Boolean getZeroBound() {
    return ((this.zeroBound != null) && (this.zeroBound));
  }
  
  /**
   * Get the whether fixed contour intervals are used. (Default = false).
   * @return the assigned value.
   */
  public Boolean isFixedInterval() {
    return ((this.fixedInterval != null) && (this.fixedInterval));
  }
  
  /**
   * Get the calculated Contour Increment. Default = null.
   * @return the current calculated value.
   */
  public Double getContourInc() {
    return this.contourInc;
  }
  
  /**
   * Get the Maximum Value on which the contour intervals were calculated
   * @return the assigned value or null if undefined
   */
  public Double getMaxValue() {
    return (this.valueRange == null)? null: this.valueRange[1];
  }
  
  /**
   * Get the Minimum Value on which the contour intervals were calculated.
   * @return the assigned value (which is zero if this.zeroBound=true or null if
   * undefined.
   */
  public Double getMinValue() {
    return (this.valueRange == null)? null: this.valueRange[0];
  }
  
  /**
   * <p>Initiates the ContourInterval with a set number of intervals and a calculated
   * interval increment based on the value range.  Also set the zeroBound constraint and
   * flag controlling whether the lo-contour is rendered.</p>
   * @param numIntervals the number of intervals
   * @param zeroBound true if the value range is lower bound
   * @param hideLoContour true to hide the lo-contour on rendering
   * @throws Exception is the numIntervals is invalid (must by &ge; 2)
   */
  public void initIntervals(int numIntervals, Boolean zeroBound, 
          Boolean hideLoContour) throws Exception {
    this.resetSettings();
    if (numIntervals < 2) {
      throw new Exception("The number of Contour Intervals is invalid. Expected a "
              + "2 or more, got " + numIntervals + ".");
    }
    this.numIntervals = numIntervals;
    this.fixedInterval = null;
    this.contourInc = null;
    this.zeroBound = ((zeroBound == null) || (!zeroBound))? null: zeroBound;
    this.setHideLoContour(hideLoContour);
    if (this.valueRange != null) {
      this.calcIntervals(this.valueRange[0].doubleValue(), 
                        this.valueRange[1].doubleValue());
    }
  }
  
  /**
   * Initiates the ContourInterval with a set interval increment size and a calculated
   * number of increment based on the value range.  Also set the zeroBound constraint and
   * flag controlling whether the lo-contour is rendered.
   * @param contourInc 
   * @param zeroBound true if the value range is lower bound
   * @param hideLoContour true to hide the lo-contour on rendering
   * @throws Exception is the contourInc is invalid (must by != 0 |NaN).
   */
  public void initIntervals(double contourInc, Boolean zeroBound, 
          Boolean hideLoContour)  throws Exception {
    this.resetSettings();
    if ((Double.isNaN(contourInc)) || (contourInc == 0.0d)) {
      throw new Exception("The Contour Interval size is invalid. Expected a "
              + "non-zero and not a NaN, got " + contourInc + ".");
    }
    
    this.fixedInterval = true;
    this.contourInc = contourInc;
    this.zeroBound = ((zeroBound == null) || (!zeroBound))? null: zeroBound;
    this.setHideLoContour(hideLoContour);
    if (this.valueRange != null) {
      this.calcIntervals(this.valueRange[0].doubleValue(), 
                        this.valueRange[1].doubleValue());
    }
  }
    
  /**
   * Called by {@linkplain #calcIntervals(double[]) this.calcIntervals} if an error
   * occur during the calculation of the new intervals (before calling 
   * {@linkplain #setIntervals(java.util.List) this.setIntervals}).
   */
  private void resetOnError() {
    try {
      this.resetValueRange();  
      this.resetIntervals();
      this.fireIntervalsChanged();
    } catch (Exception exp) {
    }
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="ContourIntervals Overrides">
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: Does Nothing</p>
   */
  @Override
  protected void onResetIntervals() throws Exception {
  }
  
  /**
   * <p>OVERRIDE: Called to calculate the Linear Contour Intervals based on the current 
   * settings and the specified value range.  It will calculate an inclusive contour 
   * range and the contour increments based on the specified number of intervals, whether  
   * the range is zero bound, and the specified value range.</p>
   * <p>If (zeroBound = true), the value Range can consist of one or more values. The
   * maximum value in the specified array of values are used to determine the contours
   * range. Else, the value Range must consist of TWO or more values. The
   * maximum and minimum value in the specified array of values are used to determine the 
   * contours range.</p>   
   * <p>Once the contour intervals are calculated, it calls {@linkplain #setIntervals(
   * java.util.List) this.setIntervals} to assign the new contours.</p>
   * @param valRange the input value for calculating the contour range and increment. 
   * See discussion above.
   * @throws if the value range is invalid. It will call {@linkplain #resetOnError() 
   * this.resetOnError} on exception.  It also throws an error if {@linkplain 
   * #setIntervals(java.util.List) this.setIntervals} throws and exception.
   */
  @Override
  public void calcIntervals(double...valRange) throws Exception {    
    Double hiContour = 0.0d;
    Double loContour = 0.0d;    
    try {
      this.resetValueRange();
      if ((valRange == null) || (valRange.length == 0)) {
        throw new Exception("The Value Range is undefined.");
      }
      Double minValue = null;
      Double maxValue = null;
      if (this.getZeroBound()) {
        minValue = 0.0d;
        if (valRange.length == 1) {
          maxValue = valRange[0];
        } else {
          maxValue = DataEntry.Max(valRange);
        }
        
        if (DataEntry.isEq(minValue, maxValue)) {
          throw new Exception("The Maximum value cannot be zero.");
        }
        if (maxValue < 0) {
          minValue = maxValue;
          maxValue = 0.0d;
        }
        this.valueRange = new Double[]{ minValue, maxValue };
        
        if (this.isFixedInterval()) {
          Long numInt = Math.round((Math.abs(maxValue)/this.contourInc)+0.5);
          this.numIntervals = numInt.intValue();
          if (maxValue == 0.0d) {
            loContour = -1*(this.contourInc * this.numIntervals);                        
          } else {
            hiContour = (this.contourInc * this.numIntervals);            
          }
        } else {
          if (maxValue == 0.0d) {
            loContour = DataEntry.roundDnSignificantDigit(minValue);
            this.contourInc = Math.abs(loContour/(1.0d * this.numIntervals));
          } else {
            hiContour = DataEntry.roundUpSignificantDigit(maxValue);
            this.contourInc = hiContour/(1.0d * this.numIntervals);
          }
        }
      } else {
        if (valRange.length == 1) {
          throw new Exception("Invalid Value Range. Expected 2 or more values, got only "
                  + "one value.");
        } else {
          maxValue = DataEntry.Max(valRange);
          minValue = DataEntry.Min(valRange);
        }
        
        if (DataEntry.isEq(minValue, maxValue)) {
          throw new Exception("The Maximum and Minimum value are the same. "
                  + "Need a range of values.");
        }        
        this.valueRange = new Double[]{ minValue, maxValue };
        
        if (this.isFixedInterval()) {
          //this.contourInc = DataEntry.
          if ((minValue >= 0.0d) && (maxValue > 0.0d)) {
            loContour = 0.0d;
            this.numIntervals = 0;
            while ((loContour + this.contourInc) < minValue) {
              loContour += this.contourInc;
              this.numIntervals++;
            }
            hiContour = loContour;
            while (hiContour < maxValue) {
              hiContour += this.contourInc;
              this.numIntervals++;
            }
          } else if ((minValue < 0.0d)  && (maxValue <= 0.0d)) {
            hiContour = 0.0d;
            this.numIntervals = 0;
            this.contourInc = (this.contourInc < 0)? -1*this.contourInc: this.contourInc;
            while ((hiContour - this.contourInc) > maxValue) {
              hiContour -= this.contourInc;
            }
            loContour = hiContour;
            while (loContour > minValue) {
              loContour -= this.contourInc;
              this.numIntervals++;
            }
          } else if ((minValue < 0.0d)  && (maxValue > 0.0d)) {
            loContour = 0.0d;
            hiContour = 0.0d;
            this.numIntervals = 0;
            this.contourInc = (this.contourInc < 0)? -1*this.contourInc: this.contourInc;
            while (loContour > minValue) {
              loContour -= this.contourInc;
              this.numIntervals++;
            }
            while (hiContour < maxValue) {
              hiContour += this.contourInc;
              this.numIntervals++;
            }
          }
        } else {
          Integer maxSigDig = DataEntry.significantDigit(maxValue);
          Integer minSigDig = DataEntry.significantDigit(minValue);
          if ((maxSigDig == null) && (minSigDig == null)) {
            throw new Exception("Invalid valueRange[" + minValue + ".." + maxValue 
                    + "]. Both values' SignificantDigit=NaN." );
          }
          if ((maxSigDig == null) || (minSigDig == null) ||
                  (maxSigDig == minSigDig)) {
            hiContour = DataEntry.roundUpSignificantDigit(maxValue);
            loContour = DataEntry.roundDnSignificantDigit(minValue);
            this.contourInc = (hiContour - loContour)/(1.0d * numIntervals);
          } else if (maxSigDig > minSigDig) {
            hiContour = DataEntry.roundUpSignificantDigit(maxValue);
            if ((maxValue >= 0.0d) && (minValue < 0.0d)) {
              this.contourInc = Math.abs(hiContour)/(1.0d * (numIntervals-1));
              if (minValue < (-1*this.contourInc)) {
                loContour = DataEntry.roundDnSignificantDigit(minValue);
                this.contourInc = (hiContour - loContour)/(1.0d * numIntervals);
              } else {
                loContour = -1*this.contourInc;
              }
            } else {
              this.contourInc = Math.abs(hiContour)/(1.0d * numIntervals);
              loContour = 0.0d;
            }
          } else if (maxSigDig < minSigDig) {
            loContour = DataEntry.roundDnSignificantDigit(minValue);
            if ((maxValue >= 0.0d) && (minValue < 0.0d)) {
              this.contourInc = Math.abs(loContour)/(1.0d * (numIntervals-1));
              if (maxValue > this.contourInc) {
                hiContour = DataEntry.roundUpSignificantDigit(maxValue);
                this.contourInc = (hiContour - loContour)/(1.0d * numIntervals);
              } else {
                hiContour = this.contourInc;
              }
            } else {
              this.contourInc = Math.abs(loContour)/(1.0d * numIntervals);
              hiContour = 0.0d;
            }
          }
        }
      }
    } catch (Exception exp) { 
      this.resetOnError();
      throw new Exception(this.getClass().getSimpleName()
              + ".initIntervals Error:\n " + exp.getMessage());
    } 
    
    if ((this.numIntervals != null) && (this.contourInc != null)) {
      List<Double> intervals = new ArrayList<>();
      for (int i = 0; i <= numIntervals; i++) {
        intervals.add(DataEntry.roundToTolerance((loContour + (this.contourInc*i))));
      }
      this.setIntervals(intervals);
    }
  }  

  /**
   * {@inheritDoc}
   * <p>OVERRIDE: Call the super method before assigning this instance initiation 
   * settings to the target.</p>
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void assignTo(ContourIntervals target) {
    super.assignTo(target); 
    if ((target != null) && (target instanceof LinearContourIntervals)) {
      LinearContourIntervals other = (LinearContourIntervals) target;
      target.resetIntervals();
      other.zeroBound = this.zeroBound;
      other.fixedInterval = this.fixedInterval;
      if (this.isFixedInterval()) {
        other.contourInc = this.contourInc;
      } else {
        other.numIntervals = this.numIntervals;
      }
    }
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Object Overrides">
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: change the return type to LinearContourIntervals, create a new
   * instance (result) to return and call {@linkplain #assignTo(
   * gov.ca.water.contours.intervals.ContourIntervals) this.assignTo(result)} to transfer
   * the settings of this instance to the clone.</p>
   */
  @Override
  public LinearContourIntervals clone() {
    LinearContourIntervals result= new LinearContourIntervals();
    this.assignTo(result);
    return result;
  }
  
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: Get the LinearContourIntervals' settings as a string.</p>
   */
  @Override
  public String toString() {
    String result = this.getClass().getSimpleName() + "[\n";
    if (!this.isEmpty()) {
      result += "\tValue Range = [" + this.getMinValue() + ", " 
                + this.getMaxValue() + "];\n";
      result += "\tZeroBound = " + this.getZeroBound() + ";\n";
      result += "\tFixedInterva = " + this.isFixedInterval()+ ";\n";
      result += "\tContour Increments = " + this.contourInc + ";\n";
      result += super.toString();
    }
    result += "]";
    return result;
  }
  //</editor-fold> 
}
