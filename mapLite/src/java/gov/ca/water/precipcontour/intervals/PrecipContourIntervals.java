package gov.ca.water.precipcontour.intervals;

import gov.ca.water.contours.intervals.LinearContourIntervals;

/**
 * <p>A LinearContourIntervals specific to Contouring precip value. It initiates the
 * ContoruInterval with 6 contours that is zero-bound and the LoContour (0.0) will not
 * be rendered on the plot.</p>
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class PrecipContourIntervals extends LinearContourIntervals {
  
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor
   */
  public PrecipContourIntervals() {
    super(); 
    try {
      this.initIntervals(20, false, true);
    } catch (Exception exp) {
      throw new IllegalArgumentException(this.getClass().getSimpleName()
              + ".new Error:\n " + exp.getMessage());
    }
  }
  // </editor-fold>
}
