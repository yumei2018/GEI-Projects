package gov.ca.water.shapelite.labels.grahics;

/**
 * A Class for defining a text alignment for rendering
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class TextStyle {
  
  //<editor-fold defaultstate="collapsed" desc="Alignment Enum Options">
  /**
   * Vertical Alignment Enum Options
   */
  public static enum Vertical {
    Top(1.0f),
    Middle(0.5f),
    Bottom(0.0f);
    
    // <editor-fold defaultstate="collapsed" desc="Private Vertical Constructor">
    /**
     * The vertical offset fraction of the Text Height from the insert point to
     * the text font's baseline
     */
    public float dY;

    /**
     * Constructor with the vertical offset dY
     * @param dY
     */
    private Vertical(float dY) {
      this.dY = dY;
    }
    // </editor-fold>
  }
  /**
   * Horizonal Alignment Enum Options
   */
  public static enum Horizontal {
    Left(0.0f),
    Center(-0.5f),
    Right(-1.0f);
    
    // <editor-fold defaultstate="collapsed" desc="Private Vertical Constructor">
    /**
     * The horizontal offset fraction of the Text Width from the insert point to
     * the text left inset point
     */
    public float dX;

    /**
     * Constructor with the vertical offset dY
     * @param dX
     */
    private Horizontal(float dX) {
      this.dX = dX;
    }
    // </editor-fold>
  }
  
  /**
   * Text orientation Options
   */
  public static enum Orientation {
    Custom(0.0f),
    HorizontalUp(0.0f),
    HorizontalDn((float) (1.0f * Math.PI)),
    VerticalUp((float) (-0.5f * Math.PI)),
    VerticalDn((float) (+0.5f * Math.PI)),
    SlantedUp((float) (-0.25f * Math.PI)),
    SlantedDn((float) (+0.25f * Math.PI));
    
    //<editor-fold defaultstate="collapsed" desc="Orientation Definition">
    /**
     * The Rotation of the Text
     */
    public final float rotate;
    /**
     * Private Constructor with to the rotation set
     * @param rotate
     */
    private Orientation(float rotate) {
      this.rotate = rotate;
    }
    //</editor-fold>
  }
  
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Final fields">
  /**
   * HorizontalUp Alignment setting
   */
  public final Horizontal hAlign;
  /**
   * Vertical Alignment setting
   */
  public final Vertical vAlign;
  /**
   * Vertical Alignment setting
   */
  public Orientation orientation;
  /**
   * Vertical Alignment setting
   */
  public float rotate;
//</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Initiate the default style (hAlign=Left, vAlign=Bottom, orientation=HorizontalUp)
   */
  public TextStyle() {
    this(null,null,null);
  }
  
  /**
   * Public Constructor
   * @param hAlign the horizontal text alignment (Left if unassigned)
   * @param vAlign the vertical text alignment (Bottom if unassigned)
   * @param orientation the text orientation (HorizontalUp if unassigned)
   */
  public TextStyle(Horizontal hAlign, Vertical vAlign, Orientation orientation) {
    this.hAlign = (hAlign == null)? Horizontal.Left: hAlign;
    this.vAlign = (vAlign == null)? Vertical.Bottom: vAlign;
    this.orientation = (orientation == null)? Orientation.HorizontalUp: orientation;
  }
  
  /**
   * Public Constructor
   * @param hAlign the horizontal text alignment (Left if unassigned)
   * @param vAlign the vertical text alignment (Bottom if unassigned)
   * @param rotate the the custom angle to rotate the text to
   */
  public TextStyle(Horizontal hAlign, Vertical vAlign, float rotate) {
    this.hAlign = (hAlign == null)? Horizontal.Left: hAlign;
    this.vAlign = (vAlign == null)? Vertical.Bottom: vAlign;
    this.orientation = Orientation.Custom;
    this.rotate = (Float.isNaN(rotate))? 0.0f: rotate;
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Get the TextStyle angle of rotation
   * @return (this.orientation = Custom)? this.rotate: this.orientation.rotate
   */
  public float getRotate() {
    if (this.orientation.equals(Orientation.Custom)) {
      return this.rotate;
    } else {
      return this.orientation.rotate;
    }
  }
  
  /**
   * Get the TextStyle angle of rotation
   * @param rotate 
   */
  public void setRotate(float rotate) {
    if ((Float.isNaN(rotate)) || (rotate > (2.0f * Math.PI)) || 
            (rotate < (-2.0f * Math.PI))) {
      rotate = 0.0f;
    } 
    
    if ((Float.isNaN(rotate)) || (rotate == 0.0f)) {
      this.orientation = Orientation.HorizontalUp;
      this.rotate = 0.0f;
    } else {
      this.orientation = Orientation.Custom;
      this.rotate = rotate;
    }
  }

  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Override Object">
  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: return a clone of this instance</p>
   */
  @Override
  public TextStyle clone() {
    TextStyle result = new TextStyle(this.hAlign, this.vAlign, this.orientation);
    result.rotate = this.rotate;
    return result;
  }
  // </editor-fold>
}
