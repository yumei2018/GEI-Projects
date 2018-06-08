package gov.ca.water.shapelite.labels.grahics;

import java.io.Serializable;

/**
 * A 
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class CanvasRectangle implements Serializable {
  
  //<editor-fold defaultstate="collapsed" desc="CanvasRectangle.Anchor">
  /**
   * The CanvasRectangle Anchor Options
   */
  public enum Anchor {
    TopLeft(0.0f,0.0f),
    TopCenter(-0.5f,0.0f),
    TopRight(-1.0f,0.0f),
    CenterLeft(0.0f,0.5f),
    CenterCenter(-0.5f,0.5f),
    CenterRight(-1.0f,0.5f),
    BottomLeft(0.0f,1.0f),
    BottomCenter(-0.5f,1.0f),
    BottomRight(-1.0f,1.0f);
    
    //<editor-fold defaultstate="collapsed" desc="Enum Constructor/Properties">
    public final float dX;
    public final float dY;
    private Anchor(float dX, float dY) {
      this.dX = dX;
      this.dY = dY;
    }
    //</editor-fold>
  }
  //</editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * Placeholder for the CanvasSize's Width in Pixels
   */
  private Float width;
  /**
   * Placeholder for the CanvasSize's Height in Pixels
   */
  private Float height;
  /**
   * The Location of the Bounds anchor Point (in pixels) relative to the Top/Left of
   * the Chart's Canvas
   */
  private CanvasPoint location; 
  /**
   * The bounds' anchor location (Default - TopLeft
   */
  private Anchor anchor;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor of a Void instance assuming Pixels-per-Inch of 72.
   */
  public CanvasRectangle() {
    this.width = null;
    this.height = null;
    this.location = new CanvasPoint();
    this.anchor = Anchor.TopLeft;
  }
  
  /**
   * Public constructor with a defined Width and Height in inches assuming 
   * Pixels-per-Inch of {@linkplain ChartCanvas#DefaultPPI} and location (0,0).
   * @param width the canvas with in Inches (must be &gt; 0)
   * @param height the canvas height in Inches (must be &gt; 0)
   */
  public CanvasRectangle(float width, float height) {
    this(0.0f, 0.0f, width, height);
  }
  
  /**
   * Public constructor with a defined Width and Height in inches assuming 
   * Pixels-per-Inch of {@linkplain ChartCanvas#DefaultPPI}.
   * @param x the canvas with in Inches (must be &ge; 0)
   * @param y the canvas height in Inches (must be &ge; 0)
   * @param width the canvas with in Inches (must be &gt; 0)
   * @param height the canvas height in Inches (must be &gt; 0)
   */
  public CanvasRectangle(float x, float y, float width, float height) {
    if ((Float.isNaN(x)) || (x < 0.0d)) {
      throw new IllegalArgumentException("Invalid X-coordinate. The assigned value is "
              + " NaN or negative.");
    }
    if ((Float.isNaN(y)) || (y < 0.0d)) {
      throw new IllegalArgumentException("Invalid X-coordinate. The assigned value is "
              + "NaN or negative.");
    }
    if ((Float.isNaN(height)) || (height <= 0.0d)) {
      throw new IllegalArgumentException("Invalid Width. The assigned value is NaN, zero"
              + " or negative.");
    }
    if ((Float.isNaN(height)) || (height <= 0.0d)) {
      throw new IllegalArgumentException("Invalid Height. The assigned value is NaN, zero"
              + " or negative.");
    }
    this.location = new CanvasPoint(x, y);
    this.width = width;
    this.height = height;
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Get the Top-Left corner of the area.
   * @return 
   */
  public final CanvasPoint getLocation() {
    if (this.location.isEmpty()) {
      float x = (Float.isNaN(this.location.getX()))? 0.0f: this.location.getX();
      float y = (Float.isNaN(this.location.getY()))? 0.0f: this.location.getY();
      this.location.setPoint(x, y);
    }
    return this.location;
  }
  
  /**
   * Setting the size in inches.
   * @param width the canvas with in Inches (must be &gt; 0)
   * @param height the canvas height in Inches (must be &gt; 0)
   */
  public final void setSize(float width, float height) {
    if ((Float.isNaN(width)) || (width <= 0.0f)) {
      throw new IllegalArgumentException("Invalid Width. The assigned value is NaN, "
              + "zero or negative.");
    }
    if ((Float.isNaN(height)) || (height <= 0.0f)) {
      throw new IllegalArgumentException("Invalid Height. The assigned value is NaN, "
              + "zero or negative.");
    }
    this.width = width;
    this.height = height;
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Public Properties">
  /**
   * Get the Rectangle's Width in inches
   * @return the assigned value or NaN is this.isEmpty
   */
  public float getWidth() {
    return (this.width == null)? Float.NaN: this.width;
  }

  /**
   * Get the Rectangle's Height in inches
   * @return the assigned value or NaN is this.isEmpty
   */
  public float getHeight() {
    return (this.height == null)? Float.NaN: this.height;
  }
    
  /**
   * Get whether the CanvasSize assignments are void
   * @return true if the width or height is NaN
   */
  public boolean isEmpty() {
    return ((Float.isNaN(this.getWidth())) || (Float.isNaN(this.getHeight())));
  }
  
  /**
   * Move the Rectangle with defined dX and dY - it move it location without changing
   * the width and height.
   * @param dX the change in X-coordinate (ignored is null|NaN)
   * @param dY the change in Y-coordinate (ignored is null|NaN)
   */
  public void moveXY(Float dX, Float dY) {
    CanvasPoint local = this.getLocation();
    local.moveXY(dX, dY);
  }
  
  /**
   * Grow the Rectangle size with defined dX and dY - it add dX and dY to the width and
   * height respectively, but does no change the Rectangle's location.
   * @param dX the change in X-coordinate (ignored is null|NaN)
   * @param dY the change in Y-coordinate (ignored is null|NaN)
   */
  public void grow(Float dX, Float dY) {
    if (this.isEmpty()) {
      return;
    }
    if ((dX != null) && (!dX.isNaN())) {
      this.width += dX;
    }
    if ((dY != null) && (!dY.isNaN())) {
      this.height += dY;
    }
  }
  
  /**
   * Scale the rectangle's location as well as its width and height by the specified set 
   * xScale and yScale values. It skip the width/height the scaling if this.isEmpty and
   * the location scaling if the location is undefined.
   * @param xScale the x-Scale to apply (ignored is null|NaN| &le; 0
   * @param yScale the y-Scale to apply (ignored is null|NaN| &le; 0)
   */
  public void scale(Float xScale, Float yScale) {
    CanvasPoint local = this.getLocation();
    local.scale(xScale, yScale);
    
    if (!this.isEmpty()) {
      if (((xScale != null) && (!xScale.isNaN()) && (xScale > 0.0))) {
        this.width *= xScale;
      }
      if ((yScale != null) && (!yScale.isNaN()) && (yScale > 0.0)) {
        this.height *= yScale;
      }
    }
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object Overrides">
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: "CanvasRectangle[Void; ppi=...]" or 
   * "CanvasSize[width=..; height..; ppi=...]"</p>
   */
  @Override
  public String toString() {
    String result = "CanvasRectangle[";
    if (this.isEmpty()) {
      result += "Void]";
    } else  {
      CanvasPoint loc = this.getLocation();
      result += "width=" +this.width + "; height=" +this.height + "; location=(" +
              loc.getX() + ", " + loc.getY() + ")]";
    }
    return result;
  }
  // </editor-fold>
}
