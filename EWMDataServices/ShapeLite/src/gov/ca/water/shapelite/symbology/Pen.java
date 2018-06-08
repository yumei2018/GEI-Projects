/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.symbology;

import gov.ca.water.shapelite.Nullable;
import java.awt.BasicStroke;
import java.awt.Color;

/**
 * This class is made immutable, to follow the pattern used by the stroke and
 * color itself.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Pen {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The stroke to be used by the pen.
   */
  private final BasicStroke stroke;

  /**
   * The color of the stroke.
   */
  private final Color color;

  //</editor-fold>
  /**
   * Creates a new instance of the Pen class.
   */
  public Pen() {
    color = Color.BLACK;
    stroke = new BasicStroke();
  }

  /**
   * Creates a pen with the specified color and thickness.
   *
   * @param color The color to use for the pen. A null value will simply result
   * in the default color of a black pen being used.
   */
  public Pen(@Nullable Color color) {
    Color c = Color.BLACK;
    if (color != null) {
      c = color;
    }
    this.color = c;
    this.stroke = new BasicStroke();
  }

  /**
   * Creates a pen with the specified color and thickness.
   *
   * @param thickness The thickness of the stroke.
   * @param color The color to use for the pen. A null value will simply result
   * in the default color of a black pen being used.
   */
  public Pen(float thickness, @Nullable Color color) {
    Color c = Color.BLACK;
    if (color != null) {
      c = color;
    }
    this.color = c;
    this.stroke = new BasicStroke(thickness);
  }

  /**
   * Creates a pen with the specified color and thickness.
   *
   * @param thickness The thickness of the stroke.
   * @param color The color to use for the pen. A null value will simply result
   * @param style The style to use for the new pen.
   * in the default color of a black pen being used.
   */
  public Pen(float thickness, @Nullable Color color, LineStyle style) {
    Color c = Color.BLACK;
    if (color != null) {
      c = color;
    }
    this.color = c;
    this.stroke = LineStyleUtils.getStroke(style, thickness);
  }

  /**
   * Creates a pen with the specified color and thickness.
   *
   * @param stroke The stroke to use for the pen. If this is null, then a
   * default solid, 1 thick wide stroke is used.
   * @param color The color to use for the pen. If this is null, then a default
   * black color is used.
   */
  public Pen(@Nullable BasicStroke stroke, @Nullable Color color) {
    Color c = Color.BLACK;
    if (color != null) {
      c = color;
    }
    this.color = c;
    BasicStroke s = new BasicStroke();
    if (stroke != null) {
      s = stroke;
    }
    this.stroke = s;
  }

  /**
   * This gets an pen that has the same color and style, but a different width.
   *
   * @param width The width of the new pen.
   * @return The new Pen.
   */
  public final Pen changeWidth(float width) {
    Color resultColor = this.color;
    LineStyle style = LineStyleUtils.getLineStyle(stroke);
    BasicStroke resultStroke = LineStyleUtils.getStroke(style, width);
    return new Pen(resultStroke, resultColor);
  }

  /**
   * This gets the a new pen by keeping the stroke but changing only the color.
   *
   * @param lineColor The new line color.
   * @return A Pen.
   */
  public final Pen changeColor(Color lineColor) {
    return new Pen(stroke, lineColor);
  }

  /**
   * This causes the pen to keep the color and width the same, but updates the
   * style based on the selection.
   *
   * @param style The new style.
   * @return A newly created pen that has the new style as part of its stroke.
   */
  public final Pen changeStyle(LineStyle style) {
    Color resultColor = this.color;
    BasicStroke resultStroke = LineStyleUtils.getStroke(style, stroke.getLineWidth());
    return new Pen(resultStroke, resultColor);
  }

  /**
   * Gets the closest matching line style based on the stroke.
   * @return The LineStyle enumeration.
   */
  public final LineStyle getStyle(){
    return LineStyleUtils.getLineStyle(stroke);
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the stroke.
   *
   * @return the stroke
   */
  public final BasicStroke getStroke() {
    return stroke;
  }

  /**
   * Gets the color for this pen.
   *
   * @return the color
   */
  public final Color getColor() {
    return color;
  }



  //</editor-fold>
}
