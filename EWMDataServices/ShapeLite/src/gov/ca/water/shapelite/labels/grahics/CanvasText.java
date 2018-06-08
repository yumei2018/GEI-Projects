package gov.ca.water.shapelite.labels.grahics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Wrapper 
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class CanvasText {
  
  //<editor-fold defaultstate="collapsed" desc="Static Logger">
  /**
   * Protected Static Logger object for logging errors, warnings, and info messages.
   */
  protected static final Logger LOGGER
          = Logger.getLogger(CanvasText.class.getName());
  //</editor-fold>        
  
  //<editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The title's text
   */
  private String text;
  /**
   * The Text Font (use default textFont is null)
   */
  private CanvasFont textFont;
  /**
   * The Title's alignment hAlign=LEFT|CENTER|RIGHT; VALIGN=TOP
   */
  private TextStyle textStyle;
  /**
   * The Text's Bounds that are updated when calling the {@linkplain #prepareCanvas(
   * gov.ca.water.chart.ChartCanvas) this.ChartCanvas} during the rendering process.
   */
  private Rectangle bounds;
  //</editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Constructor with all properties set
   * @param text the Text to be rendered (ignored is null | "")
   * @param font the textFont to use for rendering (use the default Helvetica 10pt if
   * unassigned).
   * @param textAlign the text alignment (set as [Left, Bottom, Horizontal] is unassigned.
   */
  public CanvasText(String text, CanvasFont font, TextStyle textAlign) {
    super();
    this.text = (text == null)? null: text.trim();
    this.textFont = (font == null)? new CanvasFont(): font;
    this.textStyle = (textAlign == null)? new TextStyle(): textAlign;
    this.bounds = new Rectangle();
  }
  // </editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Properties">
  /**
   * Get the Text to be rendered
   * @return the assigned text string
   */
  public String getText() {
    return text;
  }
  
  /**
   * Set the Text to be rendered
   * @param text the new text
   */
  public void setText(String text) {
    this.text = (text == null)? null: text.trim();
  }
  
  /**
   * Get the textFont to use for rendering. Call to update the text textFont settings
   * @return this.textFont instance
   */
  public CanvasFont getFont() {
    return this.textFont;
  }

  /**
   * Set the label font
   * @param font the new font (or initiate the {@linkplain CanvasFont#CanvasFont() 
   * default font} if null.
   */
  public void setFont(CanvasFont font) {
    this.textFont = (font == null)? new CanvasFont(): font; 
  }
  
  /**
   * The Text Alignment of the Text. 
   * @return the TextStyle instance.
   */
  public TextStyle getTextStyle() {
    return this.textStyle;
  }
    
  /**
   * Set the Text Alignment of the Text. Call to updated the Text Alignment.
   * @param hAlign the new horizontal alignment (null to keep the existing)
   * @param vAlign the new vertical alignment (null to keep the existing)
   * @param orientation the new text orientation (null to keep the existing)
   */
  public void setTextStyle(TextStyle.Horizontal hAlign, TextStyle.Vertical vAlign, 
                                                     TextStyle.Orientation orientation) {
    TextStyle style = this.textStyle;
    hAlign = (hAlign == null)? style.hAlign: hAlign;
    vAlign = (vAlign == null)? style.vAlign: vAlign;
    orientation = (orientation == null)? style.orientation: orientation;
    this.textStyle = new TextStyle(hAlign, vAlign, orientation);
  }
  
  /**
   * Check if the Text is assigned.
   * @return true if this.text=null|""
   */
  public boolean isEmpty() {
    return (this.text == null);
  }
  
  /**
   * Get the Text's Bounds (i.e., the adjusted box that contains the Text). Only 
   * meaningful after {@linkplain #prepareCanvas(gov.ca.water.chart.ChartCanvas) 
   * this.prepareCanvas} is called during the rendering process.
   * @return the text bounds
   */
  public Rectangle getBounds() {
    return this.bounds;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Object Overrides">
  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: Return this.text</p>
   */
  @Override
  public String toString() {
    return this.text;
  }
//</editor-fold>
  
  /**
   * Called to render the Text relative to the specified insertPoint using the TextStyle
   * and Font settings
   * @param canvas to Canvas to render to
   * @param insertPoint the insert Point - skipped if null.
   */
  public void paint(MapCanvas canvas, Point insertPoint) {
    Graphics2D graphics = null;
    if ((this.text != null) && (insertPoint != null) && (canvas != null) && 
                                      ((graphics = canvas.graphics) != null)) {
      Font curFont = graphics.getFont();
      Color curColor = graphics.getColor();
      AffineTransform curTransForm = graphics.getTransform();
      try {
        TextStyle style = this.getTextStyle();
        FontMetrics fontMetrics = this.textFont.setCanvas(canvas);
        int textHeight = fontMetrics.getHeight();
        int x = insertPoint.x;
        int y = insertPoint.y;
        int textWidth = fontMetrics.stringWidth(this.text);
//        if ((style.orientation == TextStyle.Orientation.VerticalDn) || 
//                (style.orientation == TextStyle.Orientation.VerticalUp)) {
//          if (style.hAlign == TextStyle.Horizontal.Center) {
//            y -= textWidth/2;
//          } else if (style.hAlign == TextStyle.Horizontal.Right) {
//            y -= textWidth;
//          }
//
//          if (style.vAlign == TextStyle.Vertical.Middle) {
//            x += textHeight/2;
//          } else if (style.vAlign == TextStyle.Vertical.Top) {
//            x += textHeight;
//          }
//        } else {
          if (style.hAlign == TextStyle.Horizontal.Center) {
            x -= textWidth/2;
          } else if (style.hAlign == TextStyle.Horizontal.Right) {
            x -= textWidth;
          }

          if (style.vAlign == TextStyle.Vertical.Middle) {
            y += textHeight/2;
          } else if (style.vAlign == TextStyle.Vertical.Top) {
            y += textHeight;
          }
        //}        
        float rotate = style.orientation.rotate;
        if (rotate != 0.0f) {
          AffineTransform transform = new AffineTransform();
          transform.translate(0.0d, 0.0d);
          transform.rotate(rotate, insertPoint.x, insertPoint.y);
          graphics.transform(transform);
        }
        
        graphics.drawString(this.text, x, y - fontMetrics.getDescent());
      } catch (Exception exp) {
        LOGGER.log(Level.WARNING, "{0}.paint Error:\n {1}",
                new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
      } finally {
        if (curFont != null) {
          graphics.setFont(curFont);
        }
        if (curColor != null) {
          graphics.setColor(curColor);
        }
        if (curTransForm != null) {
          graphics.setTransform(curTransForm);
        }
      }
    }
  }
}
