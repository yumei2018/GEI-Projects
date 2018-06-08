package gov.ca.water.shapelite.labels.grahics;

/**
 * A class to capture the Canvas' width, height and ppi and provide the
 * functionality to convert between font size in points and pixels and size in
 * inches.
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class CanvasSize {

  // <editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * Placeholder for the CanvasSize's Width in Pixels.
   */
  private Integer width;
  /**
   * Placeholder for the CanvasSize's Height in Pixels.
   */
  private Integer height;
  /**
   * Placeholder for the CanvasSize's Pixels per Inch (default = 72).
   */
  private Integer ppi;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor of a Void instance assuming Pixels-per-Inch of 72.
   */
  public CanvasSize() {
    this.width = null;
    this.height = null;
    this.ppi = MapCanvas.DefaultPPI;
  }

  /**
   * Public constructor of a Void instance with a defined Pixels-per-Inch Factor
   *
   * @param ppi the canvas' Pixels per Inch (must be &gt; 0)
   */
  public CanvasSize(int ppi) {
    if (ppi <= 0) {
      throw new IllegalArgumentException("Invalid Pixels-per-Inch Factor. The assigned "
          + "value is zero or negative.");
    }
    this.width = null;
    this.height = null;
    this.ppi = ppi;
  }

  /**
   * Public constructor with a defined Width and Height in inches assuming
   * Pixels-per-Inch of 72.
   *
   * @param width the canvas width in Inches (must be &gt; 0)
   * @param height the canvas height in Inches (must be &gt; 0)
   */
  public CanvasSize(double width, double height) {
    if ((Double.isNaN(width)) || (width <= 0.0d)) {
      throw new IllegalArgumentException("Invalid Width. The assigned value is NaN or "
          + "negative.");
    }
    if ((Double.isNaN(height)) || (height <= 0.0d)) {
      throw new IllegalArgumentException("Invalid Height. The assigned value is NaN or "
          + "negative.");
    }
    this.width = (int) (width * MapCanvas.DefaultPPI);
    this.height = (int) (height * MapCanvas.DefaultPPI);
    this.ppi = MapCanvas.DefaultPPI;
  }

  /**
   * Protected constructor with a defined Width and Height in inches with a set
   * Pixels-per-Inch Factor
   *
   * @param width the canvas with in Inches (must be &gt; 0)
   * @param height the canvas height in Inches (must be &gt; 0)
   * @param ppi the canvas' Pixels per Inch (must be &gt; 0)
   */
  public CanvasSize(double width, double height, int ppi) {
    if ((Double.isNaN(width)) || (width <= 0.0d)) {
      throw new IllegalArgumentException("Invalid Width. The assigned value is NaN, "
          + "zero or negative.");
    }
    if ((Double.isNaN(height)) || (height <= 0.0d)) {
      throw new IllegalArgumentException("Invalid Height. The assigned value is NaN, "
          + "zero or negative.");
    }
    if (ppi <= 0) {
      throw new IllegalArgumentException("Invalid Pixels-per-Inch Factor. The assigned "
          + "value is zero or negative.");
    }
    this.width = (int) (width * ppi);
    this.height = (int) (height * ppi);
    this.ppi = ppi;
  }

  /**
   * Protected constructor with a defined Width and Height in inches assuming
   * Pixels-per-Inch of 72.
   *
   * @param width the canvas with in Inches (must be &gt; 0)
   * @param height the canvas height in Inches (must be &gt; 0)
   */
  public CanvasSize(int width, int height) {
    if (width <= 0.0d) {
      throw new IllegalArgumentException("Invalid Width. The assigned value is 0 or "
          + "negative.");
    }
    if (height <= 0.0d) {
      throw new IllegalArgumentException("Invalid Height. The assigned value is 0 or "
          + "negative.");
    }
    this.width = width;
    this.height = height;
    this.ppi = MapCanvas.DefaultPPI;
  }

  /**
   * Protected constructor with a defined Width and Height in inches with a set
   * Pixels-per-Inch Factor
   *
   * @param width the canvas with in Pixels (must be &gt; 0)
   * @param height the canvas height in Pixels (must be &gt; 0)
   * @param ppi the canvas' Pixels per Inch (must be &gt; 0)
   */
  public CanvasSize(int width, int height, int ppi) {
    if (width <= 0.0d) {
      throw new IllegalArgumentException("Invalid Width. The assigned value is 0 or "
          + "negative.");
    }
    if (height <= 0.0d) {
      throw new IllegalArgumentException("Invalid Height. The assigned value is 0 or "
          + "negative.");
    }
    if (ppi <= 0) {
      throw new IllegalArgumentException("Invalid Pixels-per-Inch Factor. The assigned "
          + "value is zero or negative.");
    }
    this.width = width;
    this.height = height;
    this.ppi = ppi;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Setting the size in inches.
   *
   * @param width the canvas with in Inches (must be &gt; 0)
   * @param height the canvas height in Inches (must be &gt; 0)
   */
  public final void setSize(double width, double height) {
    if ((Double.isNaN(width)) || (width <= 0.0d)) {
      throw new IllegalArgumentException("Invalid Width. The assigned value is NaN, "
          + "zero or negative.");
    }
    if ((Double.isNaN(height)) || (height <= 0.0d)) {
      throw new IllegalArgumentException("Invalid Height. The assigned value is NaN, "
          + "zero or negative.");
    }
    this.width = (int) (width * this.ppi);
    this.height = (int) (height * this.ppi);
  }

  /**
   * Setting the size in inches.
   *
   * @param width the canvas with in Pixels (must be &gt; 0)
   * @param height the canvas height in Pixels (must be &gt; 0)
   */
  public final void setSize(int width, int height) {
    if (width <= 0.0d) {
      throw new IllegalArgumentException("Invalid Width. The assigned value is 0 or "
          + "negative.");
    }
    if (height <= 0.0d) {
      throw new IllegalArgumentException("Invalid Height. The assigned value is 0 or "
          + "negative.");
    }
    this.width = width;
    this.height = height;
  }

  /**
   * Convert the specified font size in Points (assuming 72 points per inch) to
   * pixels.
   *
   * @param points the font size in Points
   * @return the font size in pixels or 0 if <tt>points</tt> &le; 0 | NaN
   */
  public final int getFontSize(float points) {
    int result = 0;
    if ((points != Float.NaN) && (points >= 1.0f)) {
      float asInches = (points / 72.0f);
      result = (int) (asInches * this.ppi);
    }
    return result;
  }

  /**
   * Convert the specified stroke size in Points (assuming 72 points per inch)
   * to pixels.
   *
   * @param points the font size in Points.
   * @return the font size in pixels or 0 if <tt>points</tt> &le; 0 | NaN
   */
  public final float getStrokeSize(float points) {
    float result = 0.0f;
    if ((points != Float.NaN) && (points > 0.0f)) {
      float asInches = (points / 72.0f);
      result = (asInches * this.ppi);
    }
    return result;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Public Properties">
  /**
   * Get the size's Width in inches
   *
   * @return the assigned value or NaN is this.isVoid
   */
  public double getWidth() {
    return (this.width == null) ? Double.NaN : ((1.0 * this.width) / this.ppi);
  }

  /**
   * Get the size's Height in inches
   *
   * @return the assigned value or NaN is this.isVoid
   */
  public double getHeight() {
    return (this.height == null) ? Double.NaN : ((1.0 * this.height) / this.ppi);
  }

  /**
   * Get the size's Width in pixels
   *
   * @return the converted value or 0 is this.isVoid
   */
  public int getPixelWidth() {
    return (this.width == null) ? 0 : this.width;
  }

  /**
   * Get the size's Height in pixels
   *
   * @return the converted value or 0 is this.isVoid
   */
  public int getPixelHeight() {
    return (this.height == null) ? 0 : this.height;
  }

  /**
   * Get whether the CanvasSize assignments are void
   *
   * @return true if the width or height is NaN
   */
  public boolean isVoid() {
    return ((this.width == null) || (this.height == null));
  }

  /**
   * Get the canvas' Pixels-per-Inch Factor (default = {@linkplain #DefaultPPI})
   *
   * @return the assigned value
   */
  public int getPixelsPerInch() {
    return this.ppi;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object Overrides">
  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: "CanvasSize[Void; ppi=...]" or "CanvasSize[width=..; height..;
   * ppi=...]"</p>
   */
  @Override
  public String toString() {
    String result = "CanvasSize[";
    if (this.isVoid()) {
      result += "Void; ppi=" + this.getPixelsPerInch() + "]";
    } else {
      result += "width=" + this.width + "; height=" + this.height + "; ppi="
          + this.getPixelsPerInch() + "]";
    }
    return result;
  }
  // </editor-fold>
}
