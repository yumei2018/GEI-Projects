/*
 * The MIT License
 *
 * Copyright 2012 hdunsford.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gov.ca.water.shapelite.symbology;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates an instance of the SymbolizerMarkerPolygon for rendering
 * MakerPolygon.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PolygonSymbolizer extends FeatureSymbolizer {

  /**
   * An empty, transparent color.
   */
  public static final Color EMPTY_COLOR = new Color(0, 0, 0, 0);

  /**
   * The size of the default icon on the map.
   */
  public static final int ICON_SIZE = 10;

  /**
   * The size of the default icon in the legend.
   */
  public static final int LEGEND_ICON_SIZE = 16;

  /**
   * The HSB ratio used for pastel coloring.
   */
  public static final float PASTEL = .8f;

  /**
   * The HSB ratio used for darker pastel coloring.
   */
  public static final float DARK_SAT = .7f;

  /**
   * The HSB ratio used for darker pastel coloring.
   */
  public static final float DARK_BRIGHTNESS = .3f;

  /**
   * The ratio of rounding to use for rounded rectangles.
   */
  public static final double ROUNDING = .25;

  /**
   * Fully on color.
   */
  public static final int FF = 255;

  /**
   * The number of floating point values in an HSB array.
   */
  public static final int HSB_SIZE = 3;

  /**
   * Called to initiates a simpler polygon Symbolizer based in the a set fill
   * color and a border size (for using with a {@linkplain BasicStroke}). The
   * Border color is set as HSBcolor(fillColor.hue, fillColor.saturation,
   * fillColor.brightness/2)
   *
   * @param fillColor the polygon fill color
   * @param borderSize the border width
   * @return the new Symbolizer instance.
   */
  public static PolygonSymbolizer simpleSymbolizer(Color fillColor,
      float borderSize) {
    PolygonSymbolizer result = new PolygonSymbolizer();
    result.setFillColor(fillColor);
    float[] hsbVals = new float[HSB_SIZE];
    Color.RGBtoHSB(fillColor.getRed(), fillColor.getGreen(),
        fillColor.getBlue(), hsbVals);
    Color borderColor = Color.getHSBColor(hsbVals[0], hsbVals[1],
        hsbVals[2] / 2.0f);
    BasicStroke borderStroke = new BasicStroke(borderSize);
    result.getBorderPens().add(new Pen(borderStroke, borderColor));
    return result;
  }

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The stroke that should be used for the background. A background stroke is
   * useful for drawing borders with a wider stroke or else the alternate color
   * for a dashed stroke.
   */
  private final List<Pen> borderPens;
  /**
   * The color for the foreground stroke.
   */
  private Color fillColor;

  /**
   * The color to color the line if the marker is selected. This replaces the
   * foreground fill color.
   */
  private Color selectedColor;

  /**
   * An Optional Drop Shadow to render is set.
   */
  private ShadowSymbolizer dropShadow;



  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of the SymbolizerMarkerPath with random colors.
   */
  public PolygonSymbolizer() {
    Random rnd = new Random();
    this.fillColor = Color.getHSBColor(rnd.nextFloat(), PASTEL, PASTEL);
    float[] hsbVals = new float[HSB_SIZE];
    Color.RGBtoHSB(this.fillColor.getRed(), this.fillColor.getGreen(),
        this.fillColor.getBlue(), hsbVals);

    Color borderColor = Color.getHSBColor(hsbVals[0], DARK_SAT, DARK_BRIGHTNESS);
    Pen pen = new Pen(1, borderColor);
    borderPens = new ArrayList<>();
    borderPens.add(pen);
    this.selectedColor = Color.CYAN;
  }
  //</editor-fold>

  /**
   * Returns the copy.
   *
   * @return The SymbolizerMarkerPolygon duplicate of this symbolizer.
   */
  public final PolygonSymbolizer copy() {
    PolygonSymbolizer copy = new PolygonSymbolizer();
    copy.copyProperties(this);
    return copy;
  }

  /**
   * Copies the properties from the other symbolizer to this object.
   *
   * @param other The other symbolizer to copy.
   */
  public final void copyProperties(PolygonSymbolizer other) {
    borderPens.clear();
    for (Pen pen : other.borderPens) {
      borderPens.add(pen);
    }
    fillColor = other.fillColor;
    selectedColor = other.selectedColor;
    if (other.dropShadow == null) {
      dropShadow = null;
    } else {
      dropShadow = other.dropShadow.copy();
    }
    super.setLabelField(other.getLabelField());
  }

  /**
   * Gets the width of the wider of the two strokes.
   *
   * @return the maximum width of the border strokes.
   */
  public final float getBorderWidth() {
    float width = 0.0f;
    for (Pen pen : borderPens) {
      if (pen.getStroke().getLineWidth() > width) {
        width = pen.getStroke().getLineWidth();
      }
    }
    return width;
  }

  /**
   * Set the border width by updating the top stroke, or adding a stroke if the
   * border strokes list is empty.
   *
   * @param width The floating point width to define.
   */
  public final void setBorderWidth(float width) {
    if (!borderPens.isEmpty()) {
      Pen top = borderPens.get(borderPens.size() - 1);
      Pen replacement = top.changeWidth(width);
      borderPens.remove(top);
      borderPens.add(replacement);
    } else {
      borderPens.add(new Pen(width, Color.BLACK));
    }
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the color for the foreground stroke.
   *
   * @return the fillColor (or null if unassigned - no fill)
   */
  public final Color getFillColor() {
    return fillColor;
  }

  /**
   * Sets the color for the foreground stroke.
   *
   * @param fillColor the fillColor to set
   */
  public final void setFillColor(Color fillColor) {
    this.fillColor = fillColor;
  }

  /**
   * Gets the color for the foreground stroke.
   *
   * @return the color of the stroke that will be drawn last.
   */
  public final Color getBorderColor() {
    if (borderPens.isEmpty()) {
      return EMPTY_COLOR;
    }
    return borderPens.get(borderPens.size() - 1).getColor();
  }

  /**
   * This will clear the border strokes, and set the border stroke to be a solid
   * line of width 1 with the specified color.
   *
   * @param borderColor the border color to set to the newly created border pen
   * that replaces all other border pens.
   */
  public final void setBorderColor(Color borderColor) {
    if (!borderPens.isEmpty()) {
      Pen top = borderPens.get(borderPens.size() - 1);
      Pen replacement = top.changeColor(borderColor);
      borderPens.remove(top);
      borderPens.add(replacement);
    } else {
      borderPens.add(new Pen(1, borderColor));
    }
  }

  /**
   * Gets the line style of the pen that is drawn last, on top of other content.
   *
   * @return The LineStyle.
   */
  public final LineStyle getBorderStyle() {
    if (borderPens.isEmpty()) {
      return LineStyle.solid;
    }
    return borderPens.get(borderPens.size() - 1).getStyle();
  }

  /**
   * This will replace the top pen with the specified line style.
   *
   * @param style the line style to use.
   */
  public final void setBorderStyle(LineStyle style) {
    if (!borderPens.isEmpty()) {
      Pen top = borderPens.get(borderPens.size() - 1);
      Pen replacement = top.changeStyle(style);
      borderPens.remove(top);
      borderPens.add(replacement);
    } else {
      borderPens.add(new Pen(1, Color.BLACK, style));
    }
  }

  /**
   * Gets the color to color the line if the marker is selected. This replaces
   * the foreground fill color.
   *
   * @return this.selectedColor or Color.CYAN if unassigned.
   */
  public final Color getSelectedColor() {
    return this.selectedColor;

  }

  /**
   * Sets the the Border color if the marker is selected. This replaces the
   * Border color.
   *
   * @param selectedColor the selectedColor to set
   */
  public final void setSelectedColor(Color selectedColor) {
    this.selectedColor = selectedColor;
  }

  /**
   * Gets the shape's Drop Shadow.
   *
   * @return the assigned Drop Shadow (can be null)
   */
  public final ShadowSymbolizer getDropShadow() {
    return dropShadow;
  }

  /**
   * Sets the shape's Drop Shadow.
   *
   * @param dropShadow the DropShadow to set (can be null)
   */
  public final void getDropShadow(ShadowSymbolizer dropShadow) {
    this.dropShadow = dropShadow;
  }
  //</editor-fold>

  /**
   * Gets the legend icon for polygons.
   * @return
   */
  @Override
  public final BufferedImage getLegendIcon() {
    int size = LEGEND_ICON_SIZE;
    BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = image.createGraphics();
    GeneralPath p = new GeneralPath();
    p.moveTo(0, 0);
    p.lineTo(size - 1, 0);
    p.lineTo(size - 1, size - 1);
    p.lineTo(0, size - 1);
    p.lineTo(0, 0);
    p.closePath();
    g.setColor(this.getFillColor());
    g.fill(p);
    for (Pen pen : borderPens) {
      g.setColor(pen.getColor());
      g.setStroke(pen.getStroke());
      g.draw(p);
    }
    return image;

  }

  /**
   * Gets the list of pens (colors and strokes) that can be used to draw the
   * border.
   *
   * @return the borderPens
   */
  public final List<Pen> getBorderPens() {
    return borderPens;
  }


}
