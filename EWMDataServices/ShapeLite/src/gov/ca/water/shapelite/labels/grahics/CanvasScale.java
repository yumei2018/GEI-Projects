package gov.ca.water.shapelite.labels.grahics;

import java.io.Serializable;

/**
 * A class for defining a canvas's X- and Y-scales, which could be the same or different,
 * but not NaN and &gt; 0. It support a {@linkplain #Void() Void} CanvasScale with both 
 * scales set as NaN.
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class CanvasScale implements Serializable {

  //<editor-fold defaultstate="collapsed" desc="CanvasScale's Void Singleton">
  /**
   * Static class for holding and initiating the CanvasScale singleton in.
   */
  private static class VoidHolder {
    private static final CanvasScale _Void = new CanvasScale();
  }

  /**
   * Static method for accessing the Singleton
   * @return ChartScaleHolder.INSTANCE
   */
  public static CanvasScale Void() {
    return VoidHolder._Void;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The Chart's X-Scale
   */
  private Double xScale;
  /**
   * The Chart's Y-Scale
   */
  private Double yScale;
  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Create a Void scale
   */
  private CanvasScale() {
    this.xScale = Double.NaN;
    this.yScale = Double.NaN;
  }
  
  /**
   * Public Constructor with non-equal x- and y-scale
   * @param xScale the x-scale (cannot be NaN and must be &gt; 0)
   * @param yScale the y-scale (cannot be NaN and must be &gt; 0))
   */
  public CanvasScale(double xScale, double yScale) {    
    if ((Double.isNaN(xScale)) || (xScale <= 0.0d)) {
      throw new IllegalArgumentException("Invalid X-scale. The assigned value is NaN or "
              + "negative.");
    }
    if ((Double.isNaN(yScale)) || (yScale <= 0.0d)) {
      throw new IllegalArgumentException("Invalid Y-scale. The assigned value is NaN or "
              + "negative.");
    }
    this.xScale = xScale;
    this.yScale = yScale;    
  }
  
  /**
   * Constructor with with both scales the same
   * @param scale the x- and y-scale
   */
  public CanvasScale(double scale) {
    this(scale,scale);    
  }
  // </editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public methods">
  /**
   * Get the x-scale
   * @return the assigned value (NaN if Void)
   */
  public double getXScale() {
    return ((this.xScale == null) || (this.xScale.isNaN()))? Double.NaN: this.xScale;
  }
  
  /**
   * Get the x-scale
   * @return the assigned value (NaN if Void)
   */
  public Double getYScale() {
    return ((this.yScale == null) || (this.yScale.isNaN()))? Double.NaN: this.yScale;
  }
  
  /**
   * Get whether the scale assignments are void
   * @return true if the x- or y-scale is NaN
   */
  public boolean isVoid() {
    return ((this.xScale == null) || (this.xScale.isNaN()) ||
            (this.yScale == null) || (this.yScale.isNaN()));
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Object Overrides">
  /**
   * {@inheritDoc}
   * <p>
 OVERRIDE: return "CanvasScale[...]" - with the scale settings as "Void", this.xScale,
 or both scales, depending on this.isVoid or the scales are equal or not.</p>
   */
  @Override
  public String toString() {
    String result = "CanvasScale[";
    if (this.isVoid()) {
      result += "Void]";
    } else if (this.xScale.equals(this.yScale)) {
      result += this.xScale + "]";
    } else  {
      result += "x=" +this.xScale + "; y=" +this.yScale + "]";
    }
    return result;
  }
  //</editor-fold>
}
