package gov.ca.water.shapelite.labels;

import gov.ca.water.shapelite.labels.grahics.CanvasFont;
import gov.ca.water.shapelite.labels.grahics.CanvasOffset;
import gov.ca.water.shapelite.labels.grahics.TextStyle;
import java.awt.Color;
import java.awt.Font;

/**
 * The Text Style and Text Border style to draw a text label at an insert point.
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class LabelStyle {

  // <editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The Labeling font (default = CanvasFont(Font.SANS_SERIF, 10, Black));
   */
  private CanvasFont font;
  /**
   * The Label position (default = CENTER)
   */
  private PositionLabel position;
  /**
   * The Label Offset from the insert Point (in points)
   */
  private CanvasOffset labelOffset;  
  /**
   * The Text Offsets from the Label Border (default = null| No back/background)
   */
  private BoxStyleLabel labelBoxStyle;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor  
   */
  public LabelStyle() {
    super();
    this.font = new CanvasFont(Font.SANS_SERIF, 10.0f, Color.BLACK);
    this.position =PositionLabel.CENTER;
    this.labelOffset = new CanvasOffset(0.0f, 0.0f);
    this.labelBoxStyle = null;
  }
  
  /**
   * Public Constructor with the text Styles defined.
   * @param font the text font (ignored is unassigned) (default = Font(Font.SANS_SERIF, 
   * 10, Color.Black)
   * @param position the text insert position relative to an insert point 
   * (ignored is unassigned) (default = CENTER)
   * @param labelOffset the label box's offset from the insert  (ignored is unassigned) 
   * (default = CanvasOffset(0,0))
   */
  public LabelStyle(CanvasFont font, PositionLabel position, CanvasOffset labelOffset) {
    this();
    this.setStyle(font, position, labelOffset);
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Public Methods">  
  /**
   * Set the Label's text styles
   * @param font the text font (ignored is unassigned) (default = 
   * CanvasFont(Font.SANS_SERIF, 10, Black)
   * @param color the text color (ignored is unassigned) (default = black)
   * @param position the text insert position relative to an insert point 
   * (ignored is unassigned) (default = CENTER)
   * @param labelOffset the label box's offset from the insert (ignored is unassigned) 
   * (default = CanvasOffset(0,0))
   */
  public final void setStyle(CanvasFont font, PositionLabel position, 
                                                            CanvasOffset labelOffset) {
    if (font != null) {
      this.font = font;
    }
    if (position != null) {
      this.position = position;
    }
    if (labelOffset != null) {
      this.labelOffset = labelOffset;
    }
  }
  
  /**
   * Get the Label Font
   * @return the assigned font (default= Font(Font.SANS_SERIF, Font.PLAIN, 10)
   */
  public CanvasFont getFont() {
    return this.font;
  }

  /**
   * Get the Label's Position (relative to the insert Point)
   * @return the assigned label position (default = CENTER)
   */
  public PositionLabel getPosition() {
    return this.position;
  }

  /**
   * Get the Label's Style's default textStyle
   * @return this.position.textStyle.clone()
   */
  public TextStyle getTextStyle() {
    return this.position.textStyle.clone();
  }
  
  /**
   * Get the Style's Text Offset
   * @return the assigned offset (default = CanvasOffset(0, 0))
   */
  public CanvasOffset getLabelOffset() {
    return this.labelOffset;
  }
    
  /**
   * Get the Label's LabelBox Style's - initiated on first call
   * <p><b>NOTE:</b> Can be called to initiate the BoxStyleLabel</p>
   * @return this.labelBoxStyle
   */
  public BoxStyleLabel getLabelBoxStyle() {
    if (this.labelBoxStyle == null) {
      this.labelBoxStyle = new BoxStyleLabel();
    }
    return this.labelBoxStyle;
  }
  
  /**
   * Check if the LabelStyle has a defined LabelBox style
   * @return this.labelBoxStyle != null and it has either a FillStyle, a borderStyle, or
   * defined textOffets.
   */
  public boolean hasLabelBoxStyle() {
    return ((this.labelBoxStyle != null) && 
            ((this.labelBoxStyle.hasFill()) || (this.labelBoxStyle.hasBorders()) ||
             (this.labelBoxStyle.hasTextOffset())));
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
