package gov.ca.water.shapelite.labels.grahics;

import java.io.Serializable;
import java.util.Objects;

/**
 * The coordinate of a Point on Chart.
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class CanvasOffset implements Serializable {

  // <editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The X-coordinate
   */
  private Float x;
  /**
   * The Y-coordinate
   */
  private Float y;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor  ChartPoint(x=0; y=0)
   */
  public CanvasOffset() {
    this(null,null);  
  }
  
  /**
   * Constructor with an defined  X and Y
   * @param x the X-offset in points
   * @param y the X-offset in points
   */
  public CanvasOffset(Float x, Float y) {
    super();  
    this.x = x;
    this.y = y;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Get the Point's X-Coordinate in points
   * @return the offset value (Double.NaN if null|NaN)
   */
  public float getX() {
    return ((this.x != null) && (!this.x.isNaN()))? this.x: Float.NaN;
  }

  /**
   * Get the Point's Y-Coordinate in points
   * @return the offset value (Double.NaN if null|NaN)
   */
  public float getY() { 
    return ((this.y != null) && (!this.y.isNaN()))? this.y: Float.NaN;
  }

  /**
   * Set the offset values in points
   * @param x the new X-offset in points
   * @param y the new Y-offset in points
   */
  public void setPoint(float x, float y) {
    this.x = x;
    this.y = x;
  }
  
  /**
   * Scale the point's X- and Y- coordinates by multiplying the coordinates the set 
   * xScale and yScale values. It skip the move if this.isEmpty. 
   * @param xScale the x-Scale to apply (ignored is null|NaN| &le; 0
   * @param yScale the y-Scale to apply (ignored is null|NaN| &le; 0)
   */
  public CanvasOffset scale(Double xScale, Double yScale) {
    float newX = this.getX();
    float newY = this.getY();
    if ((!Float.isNaN(newX)) && (xScale != null) && (!xScale.isNaN()) &&
            (xScale >= 0.0d)) {
      newX = newX * xScale.floatValue();
    }
    if ((!Float.isNaN(newY)) && (yScale != null) && (!yScale.isNaN()) && 
            (yScale >= 0.0)) {
      newY = newY * yScale.floatValue();
    }
    return new CanvasOffset(newX, newY);
  }
  
  /**
   * Check is the Point's coordinates are undefined
   * @return true if one or both coordinates is null|Nan.
   */
  public final boolean isEmpty() {
    return ((Double.isNaN(this.getX())) || (Double.isNaN(this.getY())));
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object Overrides">
  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: Return true is obj != null and obj instance of CanvasPoint and
   * obj.x and y match this.x and y.</p>
   */
  @Override
  public boolean equals(Object obj) {
    boolean result = ((obj != null) && (obj instanceof CanvasOffset));
    if (result) {
      CanvasOffset other = (CanvasOffset) obj;
      result = ((((this.x == null) && (other.x == null)) || 
                 (this.x != null) && (this.x.equals(other.x))) && 
                (((this.y == null) && (other.y == null)) || 
                 (this.y != null) && (this.y.equals(other.y))));
    }
    return result;
  }

  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: return a HashCode on this.x and y.</p>
   */
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 29 * hash + Objects.hashCode(this.x);
    hash = 29 * hash + Objects.hashCode(this.y);
    return hash;
  }
  
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: </p>
   */
  @Override
  public String toString() {
    return "CanvasOffset[x=" + this.x + ";  y=" + this.y + "].";
  }
  // </editor-fold>
}
