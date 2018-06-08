package gov.ca.water.shapelite.labels.grahics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class SymbolStyle {
  
  //<editor-fold defaultstate="collapsed" desc="Static Logger">
  /**
   * Protected Static Logger object for logging errors, warnings, and info messages.
   */
  protected static final Logger LOGGER
          = Logger.getLogger(SymbolStyle.class.getName());
  //</editor-fold>        

  //<editor-fold defaultstate="collapsed" desc="The SybmolStye.Style Options">
  public enum Style {
    None,
    Circle,
    Triangle,
    Square,
    Diamond
  }
  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The Public Final style of the SybmolStyle
   */
  public Style style;
  /**
   * The Symbol Size (in Points - always be &ge; 1, default = 2pt)
   */
  public Float size;
  /**
   * The Symbol Size (in Points - always be &ge; 0, default = 0.75pt)
   */
  public Float lineThickness;
  /**
   * The Symbol's outline Color (Default = null|black)
   */
  public Color lineColor;
  /**
   * The Symbol's fill color (Default = null|No Fill)
   */
  public Color fillColor;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * A Void Symbol - Style=None.
   */
  public SymbolStyle() {
    this(null);
  }
  
  /**
   * Public Constructor  with a defined Style.
   */
  public SymbolStyle(Style style) {
    this.style = style;  
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Private/Protected Methods">
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Get the FillStyle's Style (default = None)
   * @return the assigned style
   */
  public final Style getStyle() {
    return (this.style == null)? Style.None: this.style;
  }

  /**
   * Set the FillStyle's Style (default=None)
   * @param style the line style (None if null)
   */
  public final void setStyle(Style style) {
    this.style = style;
  }
  /**
   * get the Symbol Size (in points). Always &ge; 2.0
   * @return the assigned value or 2.0
   */
  public float getSize() {
    return ((this.size == null) || (this.size < 2.0f))? 2.0f: this.size;
  }
  
  /**
   * Set the Symbol Size 
   * @param size the new size in points (2.0 &le; 2.0)
   */
  public void setSize(float size) {
    this.size = size;
  }
  
  /**
   * get the Symbol's Line Thickness (in points). Always &ge; 0.75pt
   * @return the assigned value or 0.75
   */
  public float getLineThickness() {
    return ((this.lineThickness == null) || (this.size < 0.75f))? 0.75f: 
              this.lineThickness;
  }
  
  /**
   * Set the Symbol's Line Thickness
   * @param size the new size in points (0.75 &le; 0.75)
   */
  public void setLineThickness(float size) {
    this.size = size;
  }
  
  /**
   * Set the symbol's outline and fill Colors
   * @param lineColor the symbol's outline color color (Black is null)
   * @param fillColor the symbol's fill color (Transparent if null)
   */
  public void setFillColors(Color lineColor, Color fillColor) {
    this.lineColor = lineColor;
    this.fillColor = fillColor;
  }
  
  /**
   * Get the symbol's outline Color (default = null|Black)
   * @return the assign color or Black is unassigned
   */
  public Color getLineColor() {
    return (this.lineColor == null)? Color.BLACK: this.lineColor;
  }
  
  /**
   * Get the symbol's fill color (default = null|transparent)
   * @return the assign color or null
   */
  public Color getFillColor() {
    return this.fillColor;
  }
  
  public void paint(MapCanvas canvas, List<Point> linePoints) {
    Graphics2D graphics = null;
    float symbSize = 0.0f;
    float thickness = 0.0f;
    if ((canvas == null) || (linePoints == null) || (linePoints.isEmpty()) ||
            (this.getStyle() == Style.None) ||
            ((graphics = canvas.graphics) == null) ||
            ((symbSize = canvas.getStrokeSize(this.getSize())) <= 0.0f) ||
            ((thickness = canvas.getStrokeSize(this.getLineThickness())) <= 0.0f)) {
      return;
    }
    
    Color curColor = graphics.getColor();
    Stroke curStroke = graphics.getStroke();
    try {
      graphics.setStroke(new BasicStroke(thickness));
      switch (this.getStyle()) {
        case Circle:
          this.paintCicles(graphics, symbSize, linePoints);
          break;
        case Triangle:
          this.paintTriangles(graphics, symbSize, linePoints);
          break;
        case Square:
          this.paintSquares(graphics, symbSize, linePoints);
          break;
        case Diamond:
          this.paintDiamonds(graphics, symbSize, linePoints);
          break;
      }
    } catch (Exception exp) {
      LOGGER.log(Level.WARNING, "{0}.paint Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    } finally {
      if (curColor != null) {
        graphics.setColor(curColor);
      }
      
      if (curStroke != null){
        graphics.setStroke(curStroke);
      }
    }
  }
  
  /**
   * Draw a Circle Symbol of the specified size for each point in the list
   * @param grahics the graphic component to draw on
   * @param size the rectangle's size
   * @param linePoints the list of points (can include null points)
   */
  private void paintCicles(Graphics2D grahics, float size, List<Point> linePoints) {
    for (Point point : linePoints) {
      if (point != null) {
        if (this.fillColor != null) {
          grahics.setColor(this.fillColor);
          grahics.fillOval(point.x, point.y, (int) size, (int) size);
        }
        if (this.lineColor != null) {
          grahics.setColor(this.lineColor);
          grahics.drawOval(point.x, point.y, (int) size, (int) size);
        }
      }
    }
  }
  
  /**
   * Draw a Square Symbol of the specified size for each point in the list
   * @param grahics the graphic component to draw on
   * @param size the rectangle's size
   * @param linePoints the list of points (can include null points)
   */
  private void paintSquares(Graphics2D grahics, float size, List<Point> linePoints) {
    for (Point point : linePoints) {
      if (point != null) {
        if (this.fillColor != null) {
          grahics.setColor(this.fillColor);
          grahics.fillRect((int) (point.x - size/2), (int) (point.y - size/2),
                                  (int) size, (int) size);
        }
        if (this.lineColor != null) {
          grahics.setColor(this.lineColor);
          grahics.drawRect((int) (point.x - size/2), (int) (point.y - size/2),
                                  (int) size, (int) size);
        }
      }
    }
  }
    
  /**
   * Draw a triangle Symbol of the specified size for each point in the list
   * @param grahics the graphic component to draw on
   * @param size the rectangle's size
   * @param linePoints the list of points (can include null points)
   */
  private void paintTriangles(Graphics2D grahics, float size, List<Point> linePoints) {
    int[] xValues = new int[4];
    int[] yValues = new int[4];
    for (Point point : linePoints) {
      if (point != null) {
        xValues[0] = point.x;
        xValues[1] = (int) (point.x + size/2);
        xValues[2] = (int) (point.x - size/2);
        xValues[3] = xValues[0];
        yValues[0] = (int) (point.y - size/2);
        yValues[1] = (int) (point.y + size/2);
        yValues[2] = (int) (point.y + size/2);
        yValues[3] = yValues[0];
        if (this.fillColor != null) {
          grahics.setColor(this.fillColor);
          grahics.fillPolygon(xValues, yValues, 4);
        }
        if (this.lineColor != null) {
          grahics.setColor(this.lineColor);
          grahics.drawPolygon(xValues, yValues, 4);
        }
      }
    }
  }
  
    
  /**
   * Draw a triangle Symbol of the specified size for each point in the list
   * @param grahics the graphic component to draw on
   * @param size the rectangle's size
   * @param linePoints the list of points (can include null points)
   */
  private void paintDiamonds(Graphics2D grahics, float size, List<Point> linePoints) {
    int[] xValues = new int[5];
    int[] yValues = new int[5];
    for (Point point : linePoints) {
      if (point != null) {
        xValues[0] = point.x;
        xValues[1] = (int) (point.x + size/2);
        xValues[2] = point.x;
        xValues[3] = (int) (point.x - size/2);
        xValues[4] = xValues[0];
        yValues[0] = (int) (point.y - size/2);
        yValues[1] = point.y;
        yValues[2] = (int) (point.y + size/2);
        yValues[3] = point.y;
        yValues[4] = yValues[0];
        if (this.fillColor != null) {
          grahics.setColor(this.fillColor);
          grahics.fillPolygon(xValues, yValues, 5);
        }
        if (this.lineColor != null) {
          grahics.setColor(this.lineColor);
          grahics.drawPolygon(xValues, yValues, 5);
        }
      }
    }
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
