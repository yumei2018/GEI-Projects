package gov.ca.water.shapelite.labels;

import gov.ca.water.shapelite.labels.grahics.FillStyle;
import gov.ca.water.shapelite.labels.grahics.LineStyle;

/**
 * The Text Style and Text Border style to draw a text label at an insert point.
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class BoxStyleLabel {

  // <editor-fold defaultstate="collapsed" desc="Private Fields">  
  /**
   * The color to fill the background (default = null|No Fill)
   */
  private FillStyle fillStyle;
  /**
   * The label box border style (default = null|No Border)
   */
  private LineStyle borderStyle;
  /**
   * The offset between the text and the LableBox border (default = null|No offsets)
   */
  private TextOffsets textOffsets;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor  
   */
  public BoxStyleLabel() {
    super();
    this.fillStyle = null;
    this.borderStyle = null;
    this.textOffsets = null;
  }
  
  /**
   * Public Constructor with the text Styles defined.
   * @param fillStyle the background fill style (can be null - no fill)
   * @param borderStyle the border style (can be null - no border)
   * @param textOffsets the offsets between the text and the box's borders (can be null -
   * no offsets).
   */
  public BoxStyleLabel(FillStyle fillStyle, LineStyle borderStyle, 
                                                  TextOffsets textOffsets) {
    this();
    this.setStyle(fillStyle, borderStyle, textOffsets);
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Public Methods">  
  /**
   * Set the Label's TextBox properties (i.e., the box that forms the Label's background)
   * @param fillStyle the background fill style (can be null - no fill)
   * @param borderStyle the border style (can be null - no border)
   * @param textOffsets the offsets between the text and the box's borders (can be null -
   * no offsets).
   */
  public final void setStyle(FillStyle fillStyle, LineStyle borderStyle, 
                                                  TextOffsets textOffsets) {
    this.fillStyle = fillStyle;
    this.borderStyle = borderStyle;
    this.textOffsets = textOffsets;
  }

  /**
   * Get the Label's background Fill Style - initiate the style if currently unassigned.
   * <p>
   * <b>NOTE:</b> Can be called to initiate the LabelBaoxStyle's fillStyle</p>
   * @return this.fillStyle
   */
  public FillStyle getFillStyle() {
    if (this.fillStyle == null) {
      this.fillStyle = new FillStyle();
    }
    return this.fillStyle;
  }

  /**
   * Get the Label Style's Border Style - initiate the style if currently unassigned.
   * <p>
   * <b>NOTE:</b> Can be called to initiate the BoxStyleLabel's borderStyle</p>
   * @return this.borderStyle
   */
  public LineStyle getBorderStyle() {
    if (this.borderStyle == null) {
      this.borderStyle = new LineStyle();
    }
    return this.borderStyle;
  }

  /**
   * Get the Label Style's Border Style - initiate the style if currently unassigned.
   * <p>
   * <b>NOTE:</b> Can be called to initiate the BoxStyleLabel's borderStyle</p>
   * @return this.borderStyle
   */
  public TextOffsets getTextOffsets() {
    if (this.textOffsets == null) {
      this.textOffsets = new TextOffsets();
    }
    return this.textOffsets;
  }
  
  /**
   * Get whether the label has a filled background
   * @return (this.fillColor != null) and
   * (!this.fillStyle.style.equals(FillStyle.Style.None)))
   */
  public boolean hasFill() {
    return ((this.fillStyle != null) && 
            (!this.fillStyle.style.equals(FillStyle.Style.None)));
  }
  
  /**
   * Get whether the label has a border
   * @return (this.borderPen != null) and
            (!this.borderStyle.getStyle().equals(LineStyle.Style.None))
   */
  public boolean hasBorders() {
    return ((this.borderStyle != null) &&
            (!this.borderStyle.getStyle().equals(LineStyle.Style.None)));
  } 
  
  /**
   * Get whether the label has a Text Offsets
   * @return (this.textOffsets != null) and (!this.textOffsets.isEmpty())
   */
  public boolean hasTextOffset() {
    return ((this.textOffsets != null) &&
            (!this.textOffsets.isEmpty()));
  } 
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object Overrides">
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: </p>
   */
  @Override
  public String toString() {
    return super.toString();
  }
  // </editor-fold>
}
