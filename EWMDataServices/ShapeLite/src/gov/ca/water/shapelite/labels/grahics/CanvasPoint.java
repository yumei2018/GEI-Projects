package gov.ca.water.shapelite.labels.grahics;

import java.io.Serializable;
import java.util.Objects;

/**
 * The coordinate of a Point on Chart.
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class CanvasPoint implements Serializable {

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
  public CanvasPoint() {
    this(null,null);  
  }
  
  /**
   * Constructor with an defined  X and Y
   * @param x the X-Coordinate
   * @param y the Y-Coordinate
   */
  public CanvasPoint(Float x, Float y) {
    super();  
    this.x = x;
    this.y = y;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Get the Point's X-Coordinate
   * @return the assigned value (Float.NaN if null|NaN)
   */
  public float getX() {
    return ((this.x == null) || (this.x.isNaN()))? Float.NaN: this.x;
  }

  /**
   * Get the Point's Y-Coordinate
   * @return the assigned value (Float.NaN if null|NaN)
   */
  public float getY() {
    return ((this.y == null) || (this.y.isNaN()))? Float.NaN: this.y;
  }

  /**
   * Set the Point's coordinates
   * @param x the new X-Coordinate
   * @param y the new Y-Coordinate
   */
  public void setPoint(float x, float y) {
    this.x = x;
    this.y = y;
  }
  
  /**
   * Move the point's X- and Y- coordinates by adding set dX and dY values. It 
   * skip the move if this.isEmpty. 
   * @param dX the change in X-coordinate (ignored is null|NaN)
   * @param dY the change in Y-coordinate (ignored is null|NaN)
   */
  public void moveXY(float dX, float dY) {
    if (this.isEmpty()) {
      return;
    }
    if (!Float.isNaN(dX)) {
      this.x += dX;
    }
    if (!Float.isNaN(dY)) {
      this.y += dY;
    }
  }
  
  /**
   * Scale the point's X- and Y- coordinates by multiplying the coordinates the set 
   * xScale and yScale values. It skip the move if this.isEmpty. 
   * @param xScale the x-Scale to apply (ignored is null|NaN| &le; 0
   * @param yScale the y-Scale to apply (ignored is null|NaN| &le; 0)
   */
  public void scale(float xScale, float yScale) {
    if (this.isEmpty()) {
      return;
    }

    if (((!Float.isNaN(xScale)) && (xScale > 0.0))) {
      this.x *= xScale;
    }
    if ((!Float.isNaN(yScale)) && (yScale > 0.0)) {
      this.y *= yScale;
    }
  }
  
  /**
   * Check is the Point's coordinates are undefined
   * @return true if one or both coordinates is null|Nan.
   */
  public final boolean isEmpty() {
    return ((Float.isNaN(this.getX())) || (Float.isNaN(this.getY())));
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
    boolean result = ((obj != null) && (obj instanceof CanvasPoint));
    if (result) {
      CanvasPoint other = (CanvasPoint) obj;
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
   * OVERRIDE: Return a clone with the same x and y values</p>
   */
  @Override
  public CanvasPoint clone() {
    return new CanvasPoint(this.x, this.y);
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
    return "CanvasPoint[x=" + this.getX() + ";  y=" + this.getY() + "].";
  }
  // </editor-fold>
}
