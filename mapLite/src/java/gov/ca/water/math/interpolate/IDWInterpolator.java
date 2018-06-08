package gov.ca.water.math.interpolate;

import com.vividsolutions.jts.geom.Coordinate;
import gov.ca.water.common.io.DataEntry;
import gov.ca.water.contours.GridPoint;
import gov.ca.water.contours.intervals.ContourIntervals;
import static gov.ca.water.math.interpolate.Interpolator.logger;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class IDWInterpolator extends Interpolator {
  
  //<editor-fold defaultstate="collapsed" desc="Private NearestPoint Class">
  /**
   * Private Class for storing the NeaestPoint properties - its value and distance
   */
  private class NearestPoint implements Serializable {
    //<editor-fold defaultstate="collapsed" desc="Public Final Fields">
    /**
     * The distance to the point-of-interest
     */
    public final double distance;
    /**
     * The observed DataValue
     */
    public final double value;
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Public Constructor
     */
    public NearestPoint(double value, double distance) {
      this.value = value;
      this.distance = distance;
    }
    // </editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Object Overrides">
    /**
     * {@inheritDoc}
     * <p>OVERRIDE: Return "NearestPoint[" + this.value +", " + this.distance + "]"</p>
     */
    @Override
    public String toString() {
      return "NearestPoint[" + this.value +", " + this.distance + "]";
    }
    //</editor-fold>
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The calculated Maximum distance to to select the nearest Points during interpolation.
   * It is calculated when assigning the Observed Station Data.
   */
  private double maxDistance = 0.0d;
  /**
   * Default Maximum distance to to select the nearest Points during interpolation.
   * This a user assigned value.
   */
  private double defaultMaxDistance = 0.0d;
  /**
   * The IDW Power (default = 2.0)
   */
  private double idwPower = 2.0d;
  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor
   */
  public IDWInterpolator(ContourIntervals contours) {
    super(contours);    
  }
  // </editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Methods">
  /**
   * <p>Computes the interpolated Observed Value at point the specified GridPoint from
   * this point's {@linkplain #getNearestPoints(gov.ca.water.contours.GridPoint)
   * NearestPositions}.</p>
   * <p>If the nearestPoints list has less than 6 points, and error is logged and the
   * return value is NaN.</p>
   * @param gridPoint the {@linkplain GridPoint} for which to compute the interpolated 
   * z-Value.
   * @return the interpolated value or NaN if gridPoint=null or the list NearestPoints 
   * has less than 6 points.
   */
  private double calculateValue(GridPoint gridPoint) {
    List<NearestPoint> nearestPoints = null;
    double result = Double.NaN;
    if ((gridPoint != null) &&
            ((nearestPoints = this.getNearestPoints(gridPoint)) != null)) {
      try {
        if (nearestPoints.size() < 6) {
          throw new Exception("GridPoint" + gridPoint.toString() + " Error.\n"
                  + "Only " + nearestPoints.size() + " observed data points are "
                  + "available. Required six (6) or more.");
        }
        
        double avgValue = this.getAvgNearestValue(nearestPoints);
        double maxWeight = (Double.MAX_VALUE/nearestPoints.size());
        double sumValues = 0.0;
        double sumWeights = 0.0;
        
        for (NearestPoint nearest : nearestPoints) {
          double weight = (nearest.distance < DataEntry.TOLERANCE)? maxWeight :
                  Math.min((1 / Math.pow(nearest.distance, this.idwPower)), maxWeight);
          
          sumValues += ((nearest.value/avgValue) * weight);
          sumWeights += weight;
        }
        
        if (sumWeights > DataEntry.TOLERANCE) {
          result = (avgValue * (sumValues/sumWeights));
        }
      } catch (Exception exp) {
        logger.log(Level.WARNING, "{0}.calculateValue Error:\n {1}",
                new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
      }
    }
    return result;
  }
  
  /**
   * <p>Compute the distance between the gridPoint and all observed data points and if
   * this.maxDistance &le; 0 or (the distance &le; this.maxDistance), initiate a
   * {@linkplain NearestPoint} and add it to the nearest Point List.</p>
   * <p><b>NOTE:</b> this.maxDistance will only be &le; 0 if this.defaultMaxDistacne = 
   * -1.</p>
   * @param gridPoint the grid Point for which the select the nearest Points
   * @return return a list of NearestPoint - empty if none is found.
   */
  private List<NearestPoint> getNearestPoints(GridPoint gridPoint) {
    List<NearestPoint> result = new ArrayList<>();
    if ((gridPoint != null) && (!this.isEmpty())) {
      double maxDist = this.getMaxDistance();
      boolean doNearest = DataEntry.isGT(maxDist, 0.0d);
      for (Coordinate obsPoint : this.getObsData()) {
        double zVal = Double.NaN;
        if ((obsPoint == null) || 
                (Double.isNaN((zVal=obsPoint.getOrdinate(Coordinate.Z))))) {
          continue;
        }
        
        double dist = gridPoint.distance(obsPoint);
        if ((!doNearest) || (dist <= maxDist)) {
          result.add(new NearestPoint(zVal, dist));
        }
      }
    }
    return result;
  }
  
  /**
   * Calculate the Maximum of the NearestPoints' absolute values. It is used to estimate
   * the Maximum Weight that is allowed to prevent a Double overflow.
   * @param nearestPoints the list of NearestPoints
   * @return the sum of the values or 0.0 if nearestPoints = null|empty.
   */
  private double getAvgNearestValue(List<NearestPoint> nearestPoints) {
    double result = 0.0d;
    int count = 0;
    double sum = 0.0d;
    if ((nearestPoints != null) && (!nearestPoints.isEmpty())) {
      for (NearestPoint nearest : nearestPoints) {
        sum += nearest.value;
        count++;
      }
      
      if (count > 0) {
        result = (sum/count);
      }
    }
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Get the Maximum Distance to use in selecting the nearestPoints (i.e, the point are 
   * taken into account during interpolation).
   * @return -1 if Default Maximum Distance = -1 or else 
   * the maximum of the Calculated and the Default Maximum Distance.
   */
  public double getMaxDistance() {
    return (this.defaultMaxDistance < 0)? this.defaultMaxDistance:
                                    Math.max(this.maxDistance, this.defaultMaxDistance);
  }

  /**
   * <p>Set the Default Maximum Distance to selected the nearestPoints (i.e, the point are
   * taken into account during interpolation). The Default Maximum distance applies if it
   * exceed the Calculated Maximum Distance (see {@linkplain #onSetObservedData(
   * java.util.List) onSetObservedData}). (Default = 0.0)</p>
   * <p><b>NOTE:</b> Set the Default Maximum Distance = -1 to force the Interpretor to
   * use all points, not only the neatest points.</p>
   * @param maxDist default maximum distance.
   */
  public void setDefaultMaxDistance(double maxDist) {
    this.defaultMaxDistance = maxDist;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Interpolator Override">
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: Resets the Maximum Distance</p>
   */
  @Override
  protected void onReset() throws Exception {
    this.maxDistance = 0.0d;
  }
  
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: Cycle through the new stationPoints and calculate the new MaxDistance
   * as 2*(the maximum spacing between any two points).</p>
   */
  @Override
  protected void onSetObservedData(List<Coordinate> stationPoints) throws Exception {
    double dist = 0.0d;
    for (int i = 0; i < (stationPoints.size() - 1); i++) {
      Coordinate coord1 = stationPoints.get(i);
      for (int j = i+1; j < stationPoints.size(); j++) {
        Coordinate coord2 = stationPoints.get(j);
        dist = Math.max(dist, coord1.distance(coord2));
      }
    }
    this.maxDistance = (2.0d * dist);
  }
  
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: For each GrdiPoint, call {@linkplain #calculateValue(
   * gov.ca.water.contours.GridPoint) trhis.calculateValue}. If the returned value=NaN,
   * increment the noDataCount, else assign the value to the {@linkplain 
   * GridPoint#setZ(double, gov.ca.water.contours.intervals.ContourIntervals) 
   * GridPoint.Z}.</p>
   * <p>If (noDataCount &gt; 0), a warning will be logged, but no exception will be 
   * thrown.</p>
   */
  @Override
  protected void onExecute(GridPoint[]gridPoints)
          throws Exception {
    ContourIntervals contours = this.getContourIntervals();
    Integer noDataCount = 0;
    if ((gridPoints != null) && (gridPoints.length > 0)) {
      for (int iPoint = 0; iPoint < gridPoints.length; iPoint++) {
        Double zVal = this.calculateValue(gridPoints[iPoint]);
        if (Double.isNaN(zVal)) {
          noDataCount++;
        } else {
          gridPoints[iPoint].setZ(zVal, contours);
        }
      }
    }
    
    if (noDataCount > 0) {
      logger.log(Level.WARNING, "{0} Interpolation Error:\n Interpolation for "
              + noDataCount.toString() +" GridPoints return no or insufficient data "
              + "to complete the interpolation.", this.getClass().getSimpleName());
    }
  }
  //</editor-fold>
}

