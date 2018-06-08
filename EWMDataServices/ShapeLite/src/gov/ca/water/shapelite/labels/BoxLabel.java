package gov.ca.water.shapelite.labels;

import gov.ca.water.shapelite.labels.grahics.*;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 * A 
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class BoxLabel implements Serializable {

  // <editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * Placeholder for the CanvasSize's Width in Pixels
   */
  public final float width;
  /**
   * Placeholder for the CanvasSize's Height in Pixels
   */
  public final float height;
  /**
   * The Location of the Bounds anchor Point (in pixels) relative to the Top/Left of
   * the Chart's Canvas
   */
  public final CanvasPoint topleft; 
  /**
   * The Location of the Bounds anchor Point (in pixels) relative to the Top/Left of
   * the Chart's Canvas
   */
  public final CanvasPoint textInsert; 
  /**
   * The bounds' anchor location (Default - CENTER)
   */
  public final TextStyle textStyle;
  /**
   * Get the BoxLabel bounds in Pixels
   */
  public final Rectangle bounds;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public constructor with a defined Width and Height in inches assuming 
   * Pixels-per-Inch of {@linkplain ChartCanvas#DefaultPPI}.
   * @param insertPoint the label box insert point in canvas coordinate
   * @param textWidth the text width in pixels (must be &gt; 0)
   * @param textHeight the text width in pixels (must be &gt; 0)
   * @param canvas the current canvas
   * @param labelStyle the label's style setting (can be null)
   */
  public BoxLabel(CanvasPoint insertPoint, float textWidth, float textHeight, 
                                            MapCanvas canvas, LabelStyle labelStyle) {
    if ((insertPoint == null) || (insertPoint.isEmpty())) {
      throw new IllegalArgumentException("Invalid Insert Point. The assigned or empty "
              + "Canvas Point.");
    }
    
    if ((Float.isNaN(textWidth)) || (textWidth <= 0.0d)) {
      throw new IllegalArgumentException("Invalid Width. The assigned value is NaN, zero"
              + " or negative.");
    }
    if ((Float.isNaN(textHeight)) || (textHeight <= 0.0d)) {
      throw new IllegalArgumentException("Invalid Height. The assigned value is NaN, zero"
              + " or negative.");
    }
    
    if (canvas == null) {
      throw new IllegalArgumentException("The MapCanvas reference cannot be unassigned");
    }
    
    
    if (labelStyle == null) {
      this.width = textWidth;
      this.height = textHeight;
      this.topleft = insertPoint.clone();
      this.textInsert = insertPoint.clone();
      this.textStyle = new TextStyle(TextStyle.Horizontal.Left, TextStyle.Vertical.Top, 
                                      TextStyle.Orientation.HorizontalUp);
    } else {
      float boxWidth = 0.0f;
      float boxheight = 0.0f;
      BoxStyleLabel boxStyle = labelStyle.getLabelBoxStyle();
      PositionLabel pos = labelStyle.getPosition();
      CanvasOffset labOffset = labelStyle.getLabelOffset();
      
      this.topleft = insertPoint.clone();
      boxWidth = textWidth;
      boxheight = textHeight;
      TextOffsets offsets = null;
      if (boxStyle.hasTextOffset()) {
        offsets = boxStyle.getTextOffsets();
        boxWidth +=  canvas.getStrokeSize(offsets.getLeft() + offsets.getRight());
        boxheight += canvas.getStrokeSize(offsets.getTop()+ offsets.getBottom());
      }
      
      this.topleft.moveXY((pos.dX * boxWidth), (pos.dY * boxheight));
      this.topleft.moveXY((pos.dXoff * canvas.getStrokeSize(labOffset.getX())),
                          (pos.dYoff * canvas.getStrokeSize(labOffset.getY())));
      
      this.textStyle = new TextStyle(TextStyle.Horizontal.Left, TextStyle.Vertical.Top, 
                                      TextStyle.Orientation.HorizontalUp);
      this.width = boxWidth;
      this.height = boxheight;      
      this.textInsert = this.topleft.clone();
      if (offsets != null) {
        this.textInsert.moveXY(canvas.getStrokeSize(offsets.getLeft()), 
                                              canvas.getStrokeSize(offsets.getTop()));
      }
    }
    
    this.bounds = new Rectangle((int) this.topleft.getX(),(int) this.topleft.getY(), 
                                (int) this.width, (int) this.height);
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Public Properties">  
  /**
   * Get whether the CanvasSize assignments are void
   * @return true if the width or height is NaN
   */
  public boolean isEmpty() {
    return ((Float.isNaN(this.width) || (Float.isNaN(this.height))));
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
    String result = "LabelBox[";
    if (this.isEmpty()) {
      result += "Void]";
    } else  {
//      CanvasPoint loc = this.getLocation();
//      result += "width=" +this.textWidth + "; height=" +this.height + "; location=(" +
//              loc.getX() + ", " + loc.getY() + ")]";
    }
    return result;
  }
  // </editor-fold>
}
