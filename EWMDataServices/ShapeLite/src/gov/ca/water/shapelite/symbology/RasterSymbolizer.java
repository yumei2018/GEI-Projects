/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
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

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class RasterSymbolizer implements Symbolizer, ColorMap {

  /**
   * The pixel width and height of the legend image.
   */
  private static final int LEGEND_SIZE = 16;

  /**
   * The default high value color.
   */
  private static final Color HIGH_COLOR = new Color(127, 51, 0);

  /**
   * The minimum value to assign to the symbology gradient. Values smaller than
   * this value will not be painted and will be transparent. This can be null,
   * in which case the minimum raster value will be used.
   */
  private Double min;
  /**
   * The maximum value to assign to the symbology gradient. Values larger than
   * this value will not be painted and will be transparent. This can be null,
   * in which case the maximum raster value will be used.
   */
  private Double max;
  /**
   * The color defining the gradient extreme associated with the minimum value.
   */
  private Color minColor;
  /**
   * The color defining the gradient extreme associated with the maximum value.
   */
  private Color maxColor;

  /**
   * A floating point value from 0 to 1 that describes how opaque the image
   * should be drawn.
   */
  private float opacity;

  /**
   * The maximum integer byte value.
   */
  private static final int MAX_BYTE = 255;

  /**
   * Creates a default instance of the Symbolizer raster.
   */
  public RasterSymbolizer() {
    minColor = Color.GREEN;
    maxColor = HIGH_COLOR;
    opacity = 1;
    min = 0.0;
    max = 1000.0;
  }

  /**
   * Gets the symbolizer for this raster for the legend.
   *
   * @return A BufferedImage symbolizing this raster.
   */
  @Override
  public final BufferedImage getLegendIcon() {
    BufferedImage result = new BufferedImage(LEGEND_SIZE, LEGEND_SIZE,
            BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = result.createGraphics();
    GradientPaint gp = new GradientPaint(0.0f, 0.0f, minColor, LEGEND_SIZE,
            LEGEND_SIZE, maxColor);
    g.setPaint(gp);
    g.fillRect(0, 0, LEGEND_SIZE, LEGEND_SIZE);
    g.dispose();
    return result;
  }


  /**
   * Gets an interpolated gradient color between the two specified values.
   *
   * @param value The value of the double.
   * @return The Color based on the minimum and maximum gradient definition.
   */
  @Override
  public Color getColor(double value) {
    if (value < getMin()) {
      return getMinColor();
    }
    if (value > getMax()) {
      return getMaxColor();
    }
    double ratio = (value - getMin()) / (getMax() - getMin());
    int r = getMinColor().getRed();
    int dr = getMaxColor().getRed() - r;
    r = r + (int) (ratio * dr);
    r = byteSize(r);
    int g = getMinColor().getGreen();
    int dg = getMaxColor().getGreen() - g;
    g = g + (int) (ratio * dg);
    g = byteSize(g);
    int b = getMinColor().getBlue();
    int db = getMaxColor().getBlue() - b;
    b = b + (int) (ratio * db);
    b = byteSize(b);
    return new Color(r, g, b);
  }

  /**
   * Returns an integer value that is ensured to range from 0 to 255.
   *
   * @param value The integer value to crop.
   * @return The modified integer value.
   */
  private int byteSize(int value) {
    int result = value;
    if (result > MAX_BYTE) {
      result = MAX_BYTE;
    }
    if (result < 0) {
      result = 0;
    }
    return result;
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the minimum value to assign to the symbology gradient. Values smaller
   * than this value will not be painted and will be transparent. This can be
   * null, in which case the minimum raster value will be used.
   *
   * @return the min
   */
  @Override
  public final Double getMin() {
    return min;
  }

  /**
   * Sets the minimum value to assign to the symbology gradient. Values smaller
   * than this value will not be painted and will be transparent. This can be
   * null, in which case the minimum raster value will be used.
   *
   * @param min the min to set
   */
  public final void setMin(Double min) {
    this.min = min;
  }

  /**
   * Gets the maximum value to assign to the symbology gradient. Values larger
   * than this value will not be painted and will be transparent. This can be
   * null, in which case the maximum raster value will be used.
   *
   * @return the max
   */
  @Override
  public final Double getMax() {
    return max;
  }

  /**
   * Sets the maximum value to assign to the symbology gradient. Values larger
   * than this value will not be painted and will be transparent. This can be
   * null, in which case the maximum raster value will be used.
   *
   * @param max the max to set
   */
  public final void setMax(Double max) {
    this.max = max;
  }

  /**
   * Gets the color defining the gradient extreme associated with the minimum
   * value.
   *
   * @return the minColor
   */
  @Override
  public final Color getMinColor() {
    return minColor;
  }

  /**
   * Sets the color defining the gradient extreme associated with the minimum
   * value.
   *
   * @param minColor the minColor to set
   */
  public final void setMinColor(Color minColor) {
    this.minColor = minColor;
  }

  /**
   * Gets the color defining the gradient extreme associated with the maximum
   * value.
   *
   * @return the maxColor
   */
  @Override
  public final Color getMaxColor() {
    return maxColor;
  }

  /**
   * Sets the color defining the gradient extreme associated with the maximum
   * value.
   *
   * @param maxColor the maxColor to set
   */
  public final void setMaxColor(Color maxColor) {
    this.maxColor = maxColor;
  }

  /**
   * Gets a floating point value from 0 to 1 that describes how opaque the image
   * should be drawn.
   *
   * @return the opacity
   */
  @Override
  public final float getOpacity() {
    return opacity;
  }

  /**
   * Sets a floating point value from 0 to 1 that describes how opaque the image
   * should be drawn.
   *
   * @param opacity the opacity to set
   */
  public final void setOpacity(float opacity) {
    this.opacity = opacity;
  }

  // </editor-fold>

}
