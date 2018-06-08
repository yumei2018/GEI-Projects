package gov.ca.water.shapelite.labels;

import gov.ca.water.shapelite.labels.grahics.TextStyle;

/**
 * The position of the label relative to the inert point
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public enum PositionLabel {
  CENTER(new TextStyle(TextStyle.Horizontal.Left, TextStyle.Vertical.Top, 
          TextStyle.Orientation.HorizontalUp), -0.5f, -0.5f, 0.0f, 0.0f),
  TOPLEFT(new TextStyle(TextStyle.Horizontal.Left, TextStyle.Vertical.Top, 
          TextStyle.Orientation.HorizontalUp), -1.0f, -1.0f, -1.0f, -1.0f),
  TOPCENTER(new TextStyle(TextStyle.Horizontal.Left, TextStyle.Vertical.Top, 
          TextStyle.Orientation.HorizontalUp), -0.5f, -1.0f, 0.0f, -1.0f),
  TOPRIGHT(new TextStyle(TextStyle.Horizontal.Left, TextStyle.Vertical.Top, 
          TextStyle.Orientation.HorizontalUp), 0.0f, -1.0f, 1.0f, -1.0f),
  CENTERRIGHT(new TextStyle(TextStyle.Horizontal.Left, TextStyle.Vertical.Top, 
          TextStyle.Orientation.HorizontalUp), 0.0f, -0.5f, 1.0f, 0.0f),
  BOTTOMRIGHT(new TextStyle(TextStyle.Horizontal.Left, TextStyle.Vertical.Top, 
          TextStyle.Orientation.HorizontalUp), 0.0f, 0.0f, 1.0f, 1.0f),
  CENTERBOTTOM(new TextStyle(TextStyle.Horizontal.Left, TextStyle.Vertical.Top, 
          TextStyle.Orientation.HorizontalUp), -0.5f, 0.0f, 0.0f, 1.0f),
  BOTTOMLEFT(new TextStyle(TextStyle.Horizontal.Left, TextStyle.Vertical.Top, 
          TextStyle.Orientation.HorizontalUp), -1.0f, 0.0f, -1.0f, 1.0f),
  CENTERLEFT(new TextStyle(TextStyle.Horizontal.Left, TextStyle.Vertical.Top, 
          TextStyle.Orientation.HorizontalUp), -1.0f, -0.5f, -1.0f, 0.0f);
  
  // <editor-fold defaultstate="collapsed" desc="Private LabelPosision Constructor">
  /**
   * The Text Alignment for the Label Position
   */
  public final TextStyle textStyle;
  public final float dX;
  public final float dY;    
  public final float dXoff;
  public final float dYoff;    

  /**
   * Private LabelPosision Constructor
   * @param textStyle the Anchor position's default textStyle
   */
  private PositionLabel(TextStyle textStyle, float dX, float dY, float dXoff, 
                                                                        float dYoff) {
    this.textStyle = textStyle;
    this.dX = dX;
    this.dY = dY;
    this.dXoff = dXoff;
    this.dYoff = dYoff;
  }
  // </editor-fold>
}
