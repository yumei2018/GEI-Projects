package gov.ca.water.contours;

import com.vividsolutions.jts.geom.Coordinate;
import gov.ca.water.contours.intervals.ContourIntervals;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.utils.MissingVertexException;
import gov.ca.water.common.io.DataEntry;

/**
 * An extention for {@linkplain Coord} to add properties associated with a Contour 
 * GridPoint.
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class GridPoint extends Coordinate {
  
  //<editor-fold defaultstate="collapsed" desc="Public Static Constants">
  /**
   * Public constant for the low undefined contour index [{@value}]
   */
  public static final int LO_UNDEFNED = -9999;
  /**
   * Public constant for the High undefined contour index [{@value}]
   */
  public static final int HI_UNDEFNED = 9999;
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  /**
   * The grid points hiContour index
   */
  private Integer hiIndex;
  /**
   * The grid points loContour index
   */
  private Integer loIndex;
//  /**
//   * A Flag stating whether this point is outside the area-of-interest
//   * (default = null|false)
//   */
//  private Boolean outsideArea;
  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor
   */
  public GridPoint() {
    super();  
//    this.outsideArea = null;
    this.reset();   
  }
    
//  /**
//   * Public Constructor from a Coordinate
//   * @param other a other Coordinate
//   * @param outsideArea true if outside area (can be null|false). 
//   */
//  public GridPoint(Coordinate other, Boolean outsideArea) {
//    super(other); 
//    this.outsideArea = outsideArea;
//    this.reset();
//  }
  
  /**
   * Public Constructor from a Coordinate - it reset the z-value and the contour indices.
   * @param other a other GridPoint
   */
  public GridPoint(Coordinate other) {
    super(other);
//    this.outsideArea = (other == null)? null: other.outsideArea;
    this.reset();
  }
//  
//  /**
//   * Public Constructor with x-, y-, z-coordinates
//   * @param x the x-coordinate
//   * @param y the y-coordinate
//   * @param outsideArea true if outside area (can be null|false). 
//   */
//  public GridPoint(double x, double y, Boolean outsideArea) {
//    super(x, y);  
// //   this.outsideArea = outsideArea;
//    this.reset();
//  }
  // </editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Get this GridPoint cloned as a Coordinate
   * @return new Coordinate(this)
   */
  public Coordinate asCoord() {
    return new Coordinate(this);
  }
  
  /**
   * Call to reset the the z-value (NaN) and the contour indices
   */
  public final void reset() {
    this.setOrdinate(Coordinate.Z, Double.NaN);
    this.hiIndex = null;
    this.loIndex = null;
  }
  
//  /**
//   * Get whether this point is outside the Area-of-Interest
//   * @return true if outside; false inside
//   */
//  public boolean isOutsideArea() {
//    return ((this.outsideArea != null) && (this.outsideArea));
//  }
  
  /**
   * Get the Low Contour Index
   * @return the assigned value; {@linkplain GridPoint.LO_UNDEFNED} if undefined. 
   */
  public int getLoIndex() {
    return (this.loIndex == null)? GridPoint.LO_UNDEFNED: this.loIndex;
  }
  
  /**
   * Get the High Contour Index
   * @return the assigned value; {@linkplain GridPoint.HI_UNDEFNED} if undefined. 
   */
  public int getHiIndex() {
    return (this.hiIndex == null)? GridPoint.HI_UNDEFNED: this.hiIndex;
  }
  
  /**
   * Get whether this point is on the specified contour
   * @param contourIdx the index of the contour
   * @return true if both indices are assigned and equal to the contourIdx
   */
  public boolean isOnContour(int contourIdx) {
    return ((this.hiIndex != null) && (this.hiIndex == contourIdx) &&
            (this.loIndex != null) && (this.loIndex == contourIdx));
  }
  
  /**
   * Get whether this point is below the specified contour
   * @param contourIdx the index of the contour
   * @return true if this.loIndex is defined and (this.loIndex &le; contourIdx)
   */
  public boolean isBelowContour(int contourIdx) {
    return ((this.loIndex != null) && (this.loIndex < contourIdx));
  }
    
  /**
   * Get whether this point is above the specified contour
   * @param contourIdx the index of the contour
   * @return true if this.loIndex is defined and (this.loIndex &gt; contourIdx)
   */
  public boolean isAboveContour(int contourIdx) {
    return ((this.loIndex != null) && (this.loIndex > contourIdx));
  }
   
  /**
   * Get whether this point is above the specified contour
   * @param contourIdx the index of the contour
   * @return true if this.loIndex is defined and (this.loIndex &ge; contourIdx)
   */
  public boolean isOnOrAboveContour(int contourIdx) {
    return ((this.loIndex != null) && (this.loIndex >= contourIdx));
  }
  
  /**
   * Get whether this point is between the two specified contours. 
   * @param loIndex the low index
   * @param hiIndex the high index
   * @return true if this point is above the loIdx and below the low index. It returns
   * false if loIdx > hiIndex
   */
  public boolean inInterval(int loIndex) {
    return ((this.loIndex != null) && (this.loIndex == loIndex));
  }
  
  /**
   * Get whether the GridPoint's X-Y coordinates are not defined.
   * @return true if this.X or this.Y = NaN
   */
  public boolean isEmpty() {
    return ((Double.isNaN(this.getX())) || (Double.isNaN(this.getZ())));
  }
  
  /**
   * Get the Coordinate's X-ordinate (default=NaN)
   * @return return the assigned value
   */
  public double getX() {
    return this.getOrdinate(Coordinate.X);
  }
  
  /**
   * Get the Coordinate's Y-ordinate (default=NaN)
   * @return return the assigned value
   */
  public double getY() {
    return this.getOrdinate(Coordinate.Y);
  }
  
  /**
   * Get whether the GridPoint's Z-Value is set
   * @return true if this.z != NaN.
   */
  public boolean hasZ() {
    return (!Double.isNaN(this.getOrdinate(Coordinate.Z)));
  }
  
  /**
   * Get the Coordinate's Z-ordinate (default=NaN)
   * @return return the assigned value
   */
  public double getZ() {
    return this.getOrdinate(Coordinate.Z);
  }
  
  /**
   * <p>Called to initiate the GripPoint's Z-Value and its Low and High Contour Indices.
   * It resolve the contour indices as follows:</p><ul>
   * <li><b>If (the z-value &lt; contourLow):</b> - 
   *              this.hiIndex=0; this.loIndex=undefined</li>
   * <li><b>If (the z-value &gt; (contourLow + (numIntervals*contourDz)):</b> - 
   *              this.hiIndex=undefined; this.loIndex=numIntervals</li>
   * <li><b>Else:</b> - locate the Index whether (zValue &ge (contourLow + 
   *              (Index*contourDz)) and set this.loIndex=Index. <p>
   *      if (zValue = (contourLow + (Index*contourDz)) set this.hiIndex = Index else 
   *      this.hiIndex = (Index + 1).</p></li>
   * </ul>
   * @param zValue the point's new z-Value
   * @param contours the Contour Interval definition.
   * @throws MissingVertexException if the z-Value is undefined (NaN)
   * @throws IllegalArgumentException is contours is undefined or empty
   */
  public void setZ(double zValue, ContourIntervals contours)
          throws MissingVertexException {
    this.reset();
    
    if ((contours == null) || (contours.isEmpty())) {
      throw new IllegalArgumentException("The Contour Interval Definition is unassigned"
              + " or Empty.");
    }
    
    if (Double.isNaN(zValue)) {
      throw new MissingVertexException("The GridPoint's Z-Value is invalid (NaN).");
    }
    this.setOrdinate(Coordinate.Z, zValue);
    
    /**
     * Set the Default number intervals to 10 is undefined.
     */
    int numContours = contours.getNumContours();
    
    double contourLow = contours.getLoValue();
    if (DataEntry.isLT(zValue, contourLow)) {
      this.hiIndex = 0;
    } else if (DataEntry.isGT(zValue, contours.getHiValue())) {      
      this.loIndex = numContours-1;
    } else {
      for (int iIdx = 0; iIdx < numContours; iIdx++) {
        contourLow = contours.getContour(iIdx);
        if (DataEntry.isGE(zValue, contourLow)) {
          this.loIndex = iIdx;
          if (DataEntry.isEq(zValue, contourLow)) {
            this.hiIndex = iIdx;
          } else if (iIdx < numContours) {
            this.hiIndex = iIdx + 1;
          }
        } else {
          break;
        }
      }
    }
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Object Overrids">
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: Get the super Coordinate String and add "; Indices[hi=??;lo=??](; 
   * Outside)". The "Outside" will only be added if this.isOutsideAreas=true. If the
   * hi- or lo-Indices are not assigned it adds "NA". Undefined indices is set to NaN,
   * </p>
   */
  @Override
  public String toString() {
    String result = super.toString();
    result += "; Indices[hi=" + ((this.hiIndex == null)? "NA": this.hiIndex.toString());    
    result += "; loi=" + ((this.loIndex == null)? "NA": this.loIndex.toString()) + "]";   
//    if (this.isOutsideArea()) {
//      result += "; Outside";
//    }
    return result;
  }
  //</editor-fold>
}
