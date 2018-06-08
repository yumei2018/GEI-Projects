package gov.ca.water.shapelite.labels.grahics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * A Base Object for wrapping the properties of a line
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class LineStyle implements Serializable {
  
  //<editor-fold defaultstate="collapsed" desc="LineStyle.Style Enum">
  /**
   * Chart Line Style Options
   */
  public enum Style {
    /**
     * Solid Line
     */
    None(new float[]{}),
    /**
     * Solid Line
     */
    Solid(new float[]{}),
    /**
     * Dotted Line; Dash={1.0f}
     */
    Dots(new float[]{1.0f}),
    /**
     * Dotted Line; Dash={3.0f}
     */
    Dash(new float[]{3.0f}),
    /**
     * Dotted Line  Dash={3.0f, 1.0f,1.0f,1.0f}
     */
    DotDash(new float[]{3.0f, 1.0f, 1.0f, 1.0f});
    
    //<editor-fold defaultstate="collapsed" desc="Enum Constructor">
    /**
     * The Line Thickness stoke size in points
     */
    public final float[] dash;
    /**
     * The Thickness Enum constructor
     * @param size 
     */
    private Style(float[] dash) {
      this.dash = dash;
    }
    //</editor-fold>
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="LineStyle.Thickeness Enum">
  /**
   * The LineStyle Thickness Options
   */
  public enum Thickness {
    /**
     * Line draw in thickness of 0.5 points on a 96 ppi canvas
     */
    Thin(0.5f),
    /**
     * Line draw in thickness of 1.0 pixels on a 96 ppi canvas
     */
    Normal(1.0f),
    /**
     * Line draw in thickness of 2.0 pixels on a 96 ppi canvas
     */
    Thick(2.0f),
    /**
     * Line draw in thickness of 3.0 pixels on a 96 ppi canvas
     */
    VeryThick(3.0f);
    
    //<editor-fold defaultstate="collapsed" desc="Enum Constructor">
    /**
     * The Line Thickness stoke size in points
     */
    public final float size;
    /**
     * The Thickness Enum constructor
     * @param size 
     */
    private Thickness(float size) {
      this.size = size;
    }
    //</editor-fold>
  }
  //</editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * Line Thickness (pixels) (default = normal)
   */
  private Thickness thickness;
  /**
   * The line Style (default= solid)
   */
  private Style style;
  /**
   * The line Color (default = black);
   */
  private Color color;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor  - with default settings (thickness=Normal, style=Solid,
   * color=Black)
   */
  public LineStyle() {
    this(null, null, null);  
  }
  
  /**
   * Public Constructor with defined settings
   * @param thickness the line's {@link Thickness} setting (Normal is null)
   * @param style the line's {@linkplain Style} setting (Solid if null)
   * @param color the line's color (Black is null).
   */
  public LineStyle(Thickness thickness, Style style, Color color) {
    super(); 
    this.thickness = thickness;
    this.style = style;
    this.color = color;
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Get the Line Thickness (default = Normal)
   * @return the assigned value
   */
  public final Thickness getThickness() {
    return (this.thickness == null)? Thickness.Normal: this.thickness;
  }

  /**
   * Set the Lien Thickness 
   * @param thickness the new thickness (Normal if null)
   */
  public final void setThickness(Thickness thickness) {
    this.thickness =  thickness;
  }

  /**
   * Get the Line Style (default = Solid)
   * @return the assigned style
   */
  public final Style getStyle() {
    return (this.style == null)? Style.Solid: this.style;
  }

  /**
   * Set the Line Style (default=solid)
   * @param style the line style (Solid if null)
   */
  public final void setStyle(Style style) {
    this.style = style;
  }

  /**
   * Get the Line Color (default = Black)
   * @return the assigned style
   */
  public final Color getColor() {
    return (this.color == null)? Color.BLACK: this.color;
  }

  /**
   * Set the Line Color (default=Black)
   * @param color the line color (Black if null)
   */
  public final void setColor(Color color) {
    this.color = color;
  }
  // </editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Paint methods">
  /**
   * Overload 1: Convert points to list and call {@linkplain #paint(
   * gov.ca.water.graphics.Canvas, java.util.List) this.Overload 2} to paint an array of
   * point as line segments based on the style settings. Return unhandled if canvas is
   * unassigned and the <tt>points</tt> = null | size &lt; 2.
   * @param canvas the canvas to paint on
   * @param points the points to paint
   */
  public void paint(MapCanvas canvas, Point...points) throws Exception {
    if ((points != null) && (points.length > 1)) {
      this.paint(canvas, Arrays.asList(points));
    }
  }
  
  /**
   * Overload 1: Called to paint a list of point as line segments based on the style
   * settings. Return unhandled if canvas is unassigned and the <tt>points</tt> = null |
   * size &lt; 2.
   * <p>
   * <b>NOTE:</b> Null points are skipped with gap in the line</p>
   * @param canvas the canvas to paint on
   * @param points the points to paint
   */
  public void paint(MapCanvas canvas, List<Point> points) throws Exception {
    Graphics2D graphics = null;
    Style drawStyle = this.getStyle();
    float tickness = 0.0f;
    if ((canvas == null) || ((graphics = canvas.graphics) == null) ||
            (points == null) || (points.size() < 2) || (drawStyle == Style.None) ||
            ((tickness = canvas.getStrokeSize(this.getThickness().size)) <= 0.0f)) {
      return;
    }
    
    boolean doDash = (this.getStyle().dash.length > 0);
    
    Color curColor = graphics.getColor();
    Stroke curStroke = graphics.getStroke();
    try {
      graphics.setColor(this.getColor());
      this.setStroke(graphics, tickness);      
      
      for (int iPnt = 1; iPnt < points.size(); iPnt++) {
        Point p1 = points.get(iPnt-1);
        Point p2 = points.get(iPnt);
        if ((p1 == null) || (p2 == null)) {
          if ((p2 == null) && (doDash)) {
            this.setStroke(graphics, tickness);  
          }
          continue;
        }
        
        graphics.drawLine(p1.x, p1.y, p2.x, p2.y);
      }
    } finally {
      if (curColor != null) {
        graphics.setColor(curColor);
      }
      if (curStroke != null) {
        graphics.setStroke(curStroke);
      }
    }
  }
    
  /**
   * Overload 3: Called to paint a polygon's line segments based on the style
   * settings. Return unhandled if canvas is unassigned and the <tt>points</tt> = null |
   * polygon.npoints &lt; 2.
   * @param canvas the canvas to paint on
   * @param bounds the Rectangle to paint
   */
  public void paint(MapCanvas canvas, Rectangle bounds) throws Exception {
    Graphics2D graphics = null;
    Style drawStyle = this.getStyle();
    float tickness = 0.0f;
    if ((canvas == null) || ((graphics = canvas.graphics) == null) ||
            (bounds == null) || (bounds.isEmpty()) || (drawStyle == Style.None) ||
            ((tickness = canvas.getStrokeSize(this.getThickness().size)) <= 0.0f)) {
      return;
    }
    
    Color curColor = graphics.getColor();
    Stroke curStroke = graphics.getStroke();
    try {
      graphics.setColor(this.getColor());
      this.setStroke(graphics, tickness);      
      
      graphics.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
    } finally {
      if (curColor != null) {
        graphics.setColor(curColor);
      }
      if (curStroke != null) {
        graphics.setStroke(curStroke);
      }
    }
  }
  
  /**
   * Overload 3: Called to paint a polygon's line segments based on the style
   * settings. Return unhandled if canvas is unassigned and the <tt>points</tt> = null |
   * polygon.npoints &lt; 2.
   * @param canvas the canvas to paint on
   * @param polygon the polygon to paint
   */
  public void paint(MapCanvas canvas, Polygon polygon) throws Exception {
    Graphics2D graphics = null;
    Style drawStyle = this.getStyle();
    float tickness = 0.0f;
    if ((canvas == null) || ((graphics = canvas.graphics) == null) ||
            (polygon == null) || (polygon.npoints < 2) || (drawStyle == Style.None) ||
            ((tickness = canvas.getStrokeSize(this.getThickness().size)) <= 0.0f)) {
      return;
    }
    
    Color curColor = graphics.getColor();
    Stroke curStroke = graphics.getStroke();
    try {
      graphics.setColor(this.getColor());
      this.setStroke(graphics, tickness);      
      graphics.drawPolygon(polygon);
    } finally {
      if (curColor != null) {
        graphics.setColor(curColor);
      }
      if (curStroke != null) {
        graphics.setStroke(curStroke);
      }
    }
  }
  
  private void  setStroke(Graphics2D graphics, float tickness) {
    /* Get and Scale Line Dash Pattern */
    float mitterLimit = 10.0f;
    float[] scaledDash = null;
    float[] dash = this.getStyle().dash;
    boolean doDash = (dash.length > 0);
    if (doDash) {
      float dashScale = tickness/this.getThickness().size;
      mitterLimit = mitterLimit * dashScale;
      scaledDash = new float[dash.length];
      for (int i = 0; i < dash.length; i++) {
        scaledDash[i] = dash[i] * dashScale;
      }
    }
    
    Stroke newStroke = (doDash)? new BasicStroke(tickness, BasicStroke.CAP_BUTT,
              BasicStroke.JOIN_MITER, mitterLimit, scaledDash, 0.0f):
              new BasicStroke(tickness);
    graphics.setStroke(newStroke);
  }
  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Object Overrides">
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: Get the Line setting as a string</p>
   */
  @Override
  public String toString() {
    return "ChartLine[Style=" + this.getStyle() + "; Thick=" + this.getThickness() + "].";
  }
  // </editor-fold>
}
