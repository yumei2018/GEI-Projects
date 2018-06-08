package gov.ca.water.shapelite.symbology;

import java.awt.Color;
import java.awt.image.IndexColorModel;

/*
 * The MIT License
 *
 * Copyright 2016 rmarquez.
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

/**
 *
 * @author rmarquez
 */
public class ColorMapUtils {


  /**
   * Gets colors at equally spaced intervals according to the specified number of columns.
   * @param map A colormap.
   * @param numColors The number of colors to evaluate the map at.
   *
   * @see ColorMap
   * @return
   */
  public static Color[] getColors(ColorMap map, int numColors) {

    if (numColors < 2) {
      throw new IllegalArgumentException("Requires number of colors to be equal to be >= 2.");
    }
    double maxValue = map.getMax();
    double minValue = map.getMin();
    double numColors_Double = new Integer(numColors).doubleValue();

    Color[] result = new Color[numColors];
    for (int i = 0; i < numColors; i++) {
      double value = minValue + i*(maxValue - minValue)/(numColors_Double - 1.0);
      result[i] = map.getColor(value);
    }
    return result;
  }

}
