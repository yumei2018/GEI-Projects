package gov.ca.water.shapelite.labels.grahics;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class CanvasPolyline implements Serializable, Iterable<CanvasPoint>{

  // <editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * A List of ChartPoints that defined a line.
   */
  private List<CanvasPoint> points;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor  
   */
  public CanvasPolyline() {
    super();
    this.points = new ArrayList<>();
  }
  
  /**
   * Public Constructor
   * @param points a list of Points
   */
  public CanvasPolyline(List<CanvasPoint> points) {
    this();
    if ((points != null) && (!points.isEmpty())) {
      this.points.addAll(points);
    }
  }
  
  /**
   * Public Constructor
   * @param points an array of Points
   */
  
  public CanvasPolyline(CanvasPoint...points) {
    this();
    if ((points != null) && (points.length > 0)) {
      this.points.addAll(Arrays.asList(points));
    }
  }

  // <editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * The number of points
   * @return this.points.size
   */
  public int size() {
    return this.points.size();
  }

  /**
   * Check if the Line has 2 or more points
   * @return true if this.points.size &le; 1
   */
  public boolean isEmpty() {
    return this.points.size() <= 1;
  }

  /**
   * Get the Point at the specified index
   * @param index the index of the point in the list
   * @return the point or null if the point is out of bounds
   */
  public CanvasPoint get(int index) {
    return ((index < 0) || (index >= this.points.size()))? null: this.points.get(index);
  }

  /**
   * Add the next point to the list of points
   * @param point the next point to add
   * @return 
   */
  public void add(CanvasPoint point) {
    this.points.add(point);
  }

  /**
   * Add a collection of points to the Line
   * @param points a collection of points (ignored is null|empty)
   */
  public void addAll(Collection<? extends CanvasPoint> points) {
    if ((points != null) && (!points.isEmpty())) {
      this.points.addAll(points);
    }
  }

  /**
   * Add an array of points to the Line
   * @param points an array of points (ignored is null|empty)
   */
  public void addAll(CanvasPoint...points) {
    if ((points != null) && (points.length > 0)) {
      this.points.addAll(Arrays.asList(points));
    }
  }

  /**
   * Clear all the points from the list
   */
  public void clear() {
    this.points.clear();
  }
  
  /**
   * Get the Points as an array of Points
   * @return an array of Points
   */
  public CanvasPoint[] toArray() {
    CanvasPoint[] result = new CanvasPoint[]{};
    return this.points.toArray(result);
  }
  
  /**
   * Move all the Point on the line with defined dX and dY
   * @param dX the change in X-coordinate (ignored is null|NaN)
   * @param dY the change in Y-coordinate (ignored is null|NaN)
   */
  public void moveXY(Double dX, Double dY) {
    if (!this.points.isEmpty()) {
      for (CanvasPoint point : this) {
        if (dX != null) {
          point.moveXY(dX.floatValue(), dY.floatValue());
        }
      }
    }
  }
    
  /**
   * Scale all the Point on the line with defined X- and Y-Scale.
   * @param xScale the x-Scale to apply (ignored is null|NaN| &le; 0
   * @param yScale the y-Scale to apply (ignored is null|NaN| &le; 0)
   */
  public void scale(Double xScale, Double yScale) {
    if (!this.points.isEmpty()) {
      for (CanvasPoint point : this) {
        if (xScale != null) {
          point.moveXY(xScale.floatValue(), yScale.floatValue());
        }
      }
    }
  }
  // </editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Implements Iterable">
  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: Return an iterator on this.points</p>
   */
  @Override
  public Iterator<CanvasPoint> iterator() {
    return points.iterator();
  }
  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Object Overrides">
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: The List of points as a string. </p>
   */
  @Override
  public String toString() {
    String result = "CanvasLine[";
    if (this.points.isEmpty()) {
      result += "empty]";
    } else {
      boolean isFirst = true;
      for (CanvasPoint point : this.points) {
        if (isFirst) {
          isFirst = false;
        } else {
          result += ", ";
        }
        result += "(" + point.getX() + "," + point.getY() + ")";
      }
      result += "]";
    }
    return result;
  }
  // </editor-fold>
}
