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

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class RasterSymbolizerLevel extends RasterSymbolizer {

  /**
   * Creates a default instance of the Symbolizer raster.
   */
  public RasterSymbolizerLevel() {
  }

  /**
   * Gets an interpolated gradient color between the two specified values.
   *
   * @param value The value of the double.
   * @return The Color based on the minimum and maximum gradient definition.
   */
  @Override
  public Color getColor(double value) {
    int val = (int) (value * 100);
    if (val > 256 * 256 * 256) {
      value = 256 * 256 * 256;
    }
    int r = val / 256 / 256;
    if (r > 255) {
      r = 255;
    }
    if (r < 0) {
      r = 0;
    }
    int g = (val / 256) % 256;
    if (g < 0) {
      g = 0;
    }
    if (g > 255) {
      g = 255;
    }
    int b = val % 256;
    if (b > 255) {
      b = 255;
    }
    if (b < 0) {
      b = 0;
    }
    return new Color(r, g, b);
  }

}
