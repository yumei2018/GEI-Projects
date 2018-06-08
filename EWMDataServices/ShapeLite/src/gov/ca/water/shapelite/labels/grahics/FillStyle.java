package gov.ca.water.shapelite.labels.grahics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.io.Serializable;

/**
 * A Class for capturing the fill styles of a CanvasRectangle
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class FillStyle implements Serializable {
  
  //<editor-fold defaultstate="collapsed" desc="Default Settings">
  /**
   * The Default Stoke to use for Stripes/Dots
   */
  public static LineStyle.Thickness DefaultStoke = LineStyle.Thickness.Thin;
  /**
   * The Default Spacing to use for Stripes/Dots
   */
  public static float DefaultSpacing = 5.0f;
  //</editor-fold>  

  //<editor-fold defaultstate="collapsed" desc="The FillStyle.Style options">
  public enum Style {
    None,
    Solid,
    HorizontalStripes,
    VerticalStripes,
    DiagonalUpStripes,
    DiagonalDownStripes,
    CrisscrossStriples
    /*,
    SquareDots,
    DiagonalDots */
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The FillStyle's fill style (Default = None) 
   */
  public Style style;
  /**
   * The FillStyle's background color (default = null | transparent)
   */
  private Color backgroundColor;
  /**
   * The FillStyle's foreground color (default = Back)
   */
  private Color foregroundColor;
  /**
   * The Draw line/point stroke in points (default = {@linkplain #DefaultStoke})
   */
  private LineStyle.Thickness stroke;
  /**
   * The Draw line/point spacing in points (default = {@linkplain #DefaultSpacing}) 
   */
  private Float spacing;
  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor of a Void fill (style=None)
   */
  public FillStyle() {
    this(null);
  }
          
  /**
   * Public Constructor with a specified Style
   * @param style the specified fill style.
   */
  public FillStyle(Style style) {
    this.style = style;
    this.foregroundColor = null;
    this.backgroundColor = null;
    this.spacing = null;
    this.stroke = null;
  }
  // </editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Set the FillStyle's Fore- and Background Colors
   * @param foreground the foreground color (Black is null)
   * @param background the background color (Transparent if null)
   */
  public void setFillColors(Color foreground, Color background) {
    this.foregroundColor = foreground;
    this.backgroundColor = background;
  }
  
  /**
   * Get the FillStyle's Foreground Color (default = null|Black)
   * @return the assign color or Black is unassigned
   */
  public Color getForegroundColor() {
    return (this.foregroundColor == null)? Color.BLACK: this.foregroundColor;
  }
  
  /**
   * Get the FillStyle's Background Color (default = null|transparent)
   * @return the assign color or null
   */
  public Color getBackgroundColor() {
    return (this.backgroundColor == null)? null: this.backgroundColor;
  }

  /**
   * Get the FillStyle's Style (default = None).
   * @return the assigned style.
   */
  public final Style getStyle() {
    return (this.style == null)? FillStyle.Style.None: this.style;
  }

  /**
   * Set the FillStyle's Style (default = None).
   * @param style the line style (None if null).
   */
  public final void setStyle(Style style) {
    this.style = style;
  }
  
  /**
   * Set the Stripe or Dot spacing (if not using the default values). If the 
   * <tt>stroke</tt> or <tt>spacing</tt> == null | NaN | &le; 0, use the default settings.
   * @param stroke the new stroke thickness.
   * @param spacing the new spacing size in points.
   */
  public void setStripeAndDots(LineStyle.Thickness stroke, Float spacing) {
    this.stroke = stroke;
    this.spacing = 
            ((spacing == null) || (spacing.isNaN()) || (spacing <= 0.0f))? null: spacing;
  }
  
  /**
   * Get the specified Thickness of the Strip/dot stroke.
   * @return the assigned value or {@linkplain #DefaultStoke} is unassigned.
   */
  public LineStyle.Thickness getStroke() {
    return (this.stroke == null)? FillStyle.DefaultStoke: this.stroke;
  }
  
  /**
   * Get the specified spacing of the Stripes or dots.
   * @return the assigned value or {@linkplain #DefaultSpacing} is unassigned.
   */
  public Float getSpacing() {
    return (this.spacing == null)? FillStyle.DefaultSpacing: this.spacing;
  }
  
  /**
   * Call to fill a Polygon with a solid fill using the set Foreground Color.
   * <p>
   * <b>NOTE:</b> At  this point all style != None will be filled with a solid fill.</p>
   * @param canvas the canvas to paint to
   * @param polygon the polygon to fill
   */
  public void paint(MapCanvas canvas, Polygon polygon) {
    Graphics2D graphics = null;
    if ((this.getStyle() == Style.None) ||
            (canvas == null) || ((graphics = canvas.graphics) == null) || 
            (polygon == null) || (polygon.npoints < 2)) {
      return;
    }
    
    Color oldColor = graphics.getColor();
    try {
      Color fgColor = this.getForegroundColor();
      graphics.setColor(fgColor);
      graphics.fillPolygon(polygon);
    } finally {
      if (oldColor != null) {
        graphics.setColor(oldColor);
      }
    }
  }
  
  /**
   * Call to fill a rectangle according
   * @param canvas the canvas to paint to
   * @param bounds the rectangle to fill.
   */
  public void paint(MapCanvas canvas, Rectangle bounds) {
    Graphics2D graphics = null;
    if ((this.getStyle() == Style.None) ||
        (canvas == null) || ((graphics = canvas.graphics) == null) || 
            (bounds == null) || (bounds.isEmpty()) ||
            (bounds.width == 0) || (bounds.height == 0)) {
      return;
    }
    
    Color oldColor = graphics.getColor();
    try {
      Color fgColor = this.getForegroundColor();
      Color bgColor = this.getBackgroundColor();
      if (this.getStyle() == Style.Solid) {
        graphics.setColor(fgColor);
        graphics.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
      } else {
        if (bgColor != null) {
          graphics.setColor(bgColor);
          graphics.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
        
        graphics.setColor(fgColor);
        this.paintStripes(canvas, bounds);
      }
    } finally {
      if (oldColor != null) {
        graphics.setColor(oldColor);
      }
    }
  }
  
  /**
   * Call to paint DiagonapUp-, DiagonalDown-, Vertical-, Horizontal-, and 
   * CrissCrossStripes.
   * @param canvas the canvas to paint in and to scale to
   * @param bounds the bound to fill.
   */
  @SuppressWarnings({"unchecked", "rawtypes", "fallthrough"})
  private void paintStripes(MapCanvas canvas, Rectangle bounds) {
    Graphics2D graphics = null;
    int stripeSpacing = canvas.getFontSize(this.getSpacing());
    if ((stripeSpacing <= 0) || ((graphics = canvas.graphics) == null)) {
      return;
    }
    
    float strokeSize = canvas.getStrokeSize(this.getStroke().size);
    Shape oldClip = null;
    Stroke oldStroke = null;
    try {              
      oldClip = graphics.getClip();
      oldStroke = graphics.getStroke();
      Rectangle clip = new Rectangle(bounds);
      clip.width += 1;
      clip.height += 1;
      graphics.setClip(clip);
      graphics.setStroke(new BasicStroke(strokeSize));
      switch (this.style) {
        case DiagonalUpStripes:
          for (int y = bounds.y + stripeSpacing; 
                    y < bounds.y + bounds.height + bounds.width; y += stripeSpacing) {
            graphics.drawLine(bounds.x, y, bounds.x + bounds.width, y - bounds.width);
          }
          break;
        case DiagonalDownStripes:
          for (int y = bounds.y - bounds.width + stripeSpacing; 
                                  y < bounds.y + bounds.height; y += stripeSpacing) {
            graphics.drawLine(bounds.x, y, bounds.x + bounds.width, y + bounds.width);
          }
          break;
        case HorizontalStripes:
          for (int y = bounds.y + stripeSpacing; y < bounds.y + bounds.height; 
                                                              y += stripeSpacing) {
            graphics.drawLine(bounds.x, y, bounds.x + bounds.width, y);
          }
          break;
        case VerticalStripes:
          for (int x = bounds.x + stripeSpacing; x < bounds.x + bounds.width; 
                                                              x += stripeSpacing) {
            graphics.drawLine(x, bounds.y, x, bounds.y + bounds.height);
          }
        case CrisscrossStriples:
          for (int y = bounds.y + stripeSpacing; 
                    y < bounds.y + bounds.height + bounds.width; y += stripeSpacing) {
            graphics.drawLine(bounds.x, y, bounds.x + bounds.width, y - bounds.width);
          }
          for (int y = bounds.y - bounds.width + stripeSpacing; 
                                  y < bounds.y + bounds.height; y += stripeSpacing) {
            graphics.drawLine(bounds.x, y, bounds.x + bounds.width, y + bounds.width);
          }
          break;
      }      
    } finally {
      if (oldClip != null) {
        graphics.setClip(oldClip);
      }
      if (oldStroke != null) {
        graphics.setStroke(oldStroke);
      }
    }
  } 
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Object Overrides">
  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: Get the FillStyle setting as a string</p>
   */
  @Override
  public String toString() {
    Color bgcolor = this.getBackgroundColor();
    return "FillStyle[style=" + this.style + "; fgcolor=" + this.getForegroundColor() +
            ((bgcolor == null)? "]": "; bgcolor=" + bgcolor + "]");
  }
//</editor-fold>
}
