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
package gov.ca.water.shapelite.symbology;

import java.awt.Color;


/**
 *
 * @author rmarquez
 */
public interface ColorMap {
  
  /**
   * Gets an interpolated gradient color between the two specified values.
   *
   * @param value The value of the double.
   * @return The Color based on the minimum and maximum gradient definition.
   */
  Color getColor(double value);

  /**
   * Gets the minimum value to assign to the symbology gradient. Values smaller
   * than this value will not be painted and will be transparent. This can be
   * null, in which case the minimum raster value will be used.
   *
   * @return the min
   */
  Double getMin();


  /**
   * Gets the maximum value to assign to the symbology gradient. Values larger
   * than this value will not be painted and will be transparent. This can be
   * null, in which case the maximum raster value will be used.
   *
   * @return the max
   */
  Double getMax();


  /**
   * Gets the color defining the gradient extreme associated with the minimum
   * value.
   *
   * @return the minColor
   */
  Color getMinColor();



  /**
   * Gets the color defining the gradient extreme associated with the maximum
   * value.
   *
   * @return the maxColor
   */
  Color getMaxColor();



  /**
   * Gets a floating point value from 0 to 1 that describes how opaque the image
   * should be drawn.
   *
   * @return the opacity
   */
  float getOpacity();



}
