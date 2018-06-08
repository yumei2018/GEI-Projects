/*
 * The MIT License
 *
 * Copyright 2015 rmarquez.
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
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rmarquez
 */
public class LineSymbolizer extends FeatureSymbolizer {

  /**
   * A default color to use if no pens are in this symbolizer.
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

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The color to color the line if the marker is selected. This replaces the
   * foreground fill color.
   */
  private Color selectedColor;


  /**
   * The width of the line if the marker is selected.  Default is the width value of
   * the default symbolizer.
   */
  private float selectedWidth;


  /**
   * The list of pens used to draw the line.
   */
  private final List<Pen> pens;


  //</editor-fold>
  /**
   * Creates a new instance of the SymbolizerMarkerPathRange.
   */
  public LineSymbolizer() {
    Random rnd = new Random();
    Color fillColor = Color.getHSBColor(rnd.nextFloat(), PASTEL, PASTEL);


    pens = new ArrayList<>();
    pens.add(new Pen(fillColor));
    selectedColor = new Color(0, FF, FF);
    selectedWidth = this.getWidth();
  }

  /**
   * Defines a new symbolizer marker path of the specified color.
   *
   * @param color The color to use as the fill color for the marker path.
   */
  public LineSymbolizer(Color color) {
    pens = new ArrayList<>();
    pens.add(new Pen(color));
    selectedColor = new Color(0, FF, FF);
    selectedWidth = this.getWidth();
  }


  /**
   * Creates a new SymbolizerMarkerPath with the same properties as this item.
   *
   * @return The SymbolizerMarkerPath.
   */
  public final LineSymbolizer copy() {
    LineSymbolizer copy = new LineSymbolizer();
    copy.copyProperties(this);
    return copy;
  }

  /**
   * Copies the characteristics of the other path to this one. This is a deep
   * copy, so things like strokes will be different objects.
   *
   * @param other The Symbolizer marker Path to copy.
   */
  public final void copyProperties(LineSymbolizer other) {
    // Basic Stroke and Color are immutable, so deep copy is easy.
    this.selectedColor = other.selectedColor;
    this.selectedWidth = other.selectedWidth;
    pens.clear();

    for (Pen pen : other.pens) {
      new Pen(new BasicStroke(
              pen.getStroke().getLineWidth(),
              pen.getStroke().getEndCap(),
              pen.getStroke().getLineJoin(),
              pen.getStroke().getMiterLimit(),
              pen.getStroke().getDashArray(),
              pen.getStroke().getDashPhase()
      ),
              new Color(pen.getColor().getRGB(), pen.getColor().getAlpha() > 0)
      );
      pens.add(pen); // pen is immutable, so just using the same object is ok.
    }
    super.setLabelField(other.getLabelField());
  }

  /**
   * Gets the width of the wider of the two strokes.
   *
   * @return The floating point width.
   */
  public final float getWidth() {
    float width = 0;
    for (Pen pen : pens) {
      float strokeWidth = pen.getStroke().getLineWidth();
      if (strokeWidth > width) {
        width = strokeWidth;
      }
    }
    return width;
  }

  /**
   * This sets the width of the top pen by replacing it with a similar pen but
   * with a different width. If no pens exist, this will add a pen.
   *
   * @param width The floating point width to set.
   */
  public final void setWidth(float width) {
    if (!pens.isEmpty()) {
      Pen top = pens.get(pens.size() - 1);
      Pen replacement = top.changeWidth(width);
      pens.remove(top);
      pens.add(replacement);
    } else {
      pens.add(new Pen(width, Color.BLACK));
    }
  }

  /**
   * Gets the line style of the pen that is drawn last, on top of other content.
   *
   * @return The LineStyle.
   */
  public final LineStyle getStyle() {
    if (pens.isEmpty()) {
      return LineStyle.solid;
    }
    return pens.get(pens.size() - 1).getStyle();
  }

  /**
   * This will replace the top pen with the specified line style.
   *
   * @param style the line style to use.
   */
  public final void setStyle(LineStyle style) {
    if (!pens.isEmpty()) {
      Pen top = pens.get(pens.size() - 1);
      Pen replacement = top.changeStyle(style);
      pens.remove(top);
      pens.add(replacement);
    } else {
      pens.add(new Pen(1, Color.BLACK, style));
    }
  }

  /**
   * This will replace the top pen with the specified color.
   *
   * @param color The color for the pen.
   */
  public final void setColor(Color color) {
    if (!pens.isEmpty()) {
      Pen top = pens.get(pens.size() - 1);
      Pen replacement = top.changeColor(color);
      pens.remove(top);
      pens.add(replacement);
    } else {
      pens.add(new Pen(1, color));
    }
  }

  /**
   * Gets the color of the top pen.
   *
   * @return The Color.
   */
  public final Color getColor() {
    if (pens.isEmpty()) {
      return EMPTY_COLOR;
    }
    return pens.get(pens.size() - 1).getColor();
  }

  /**
   * Gets the legend icon for this path.
   *
   * @return A BufferedImage with the icon.
   */
  @Override
  public final BufferedImage getLegendIcon() {
    int size = LEGEND_ICON_SIZE;
    if (this.getWidth() > size) {
      size = Math.round(this.getWidth());
    }
    BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = image.createGraphics();
    for (Pen pen : pens) {
      g.setStroke(pen.getStroke());
      g.setColor(pen.getColor());
      g.drawLine(0, size / 2, size - 1, size / 2);
    }
    return image;
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the color for the foreground stroke.
   *
   * @return the fillColor
   */
  public final Color getFillColor() {
    if (pens.isEmpty()) {
      return EMPTY_COLOR;
    }
    return pens.get(pens.size() - 1).getColor();
  }

  /**
   * Sets the color for the foreground stroke.
   *
   * @param fillColor the fillColor to set
   */
  public final void setFillColor(Color fillColor) {
    float width = 1;
    if (!pens.isEmpty()) {
      width = pens.get(pens.size() - 1).getStroke().getLineWidth();
    }
    pens.clear();
    pens.add(new Pen(width, fillColor));
  }

  /**
   * Gets the color to color the line if the marker is selected. This replaces
   * the foreground fill color.
   *
   * @return the selectedColor
   */
  public final Color getSelectedColor() {
    return selectedColor;
  }


  /**
   * Sets the color to color the line if the marker is selected. This replaces
   * the foreground fill color.
   *
   * @param selectedColor the selectedColor to set
   */
  public final void setSelectedColor(Color selectedColor) {
    this.selectedColor = selectedColor;
  }


  /**
   * Sets the width of the line if the marker is selected.
   *
   * @param width the width to set
   */
  public void setSelectedWidth(float width) {
    this.selectedWidth = width;
  }
  
  /**
   * Gets the selected width of the line.
   * @return
   */
  public float getSelectedWidth() {
    return selectedWidth;
  }





  //</editor-fold>
  /**
   * Gets the list of pens used to draw the line.
   *
   * @return the pens
   */
  public final List<Pen> getPens() {
    return pens;
  }


}
