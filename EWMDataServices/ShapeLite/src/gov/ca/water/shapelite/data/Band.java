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
package gov.ca.water.shapelite.data;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Band {

  /**
   * The jagged array of double values ordered [y][x] that store values.
   */
  private final double[][] data;
  /**
   * The number of data values wide. (x count).
   */
  private int width;
  /**
   * The number of data values tall. (y count).
   */
  private int height;

  /**
   * The minimum value found in the data for this band, not counting the no-data
   * value.
   */
  private double min;
  /**
   * The maximum value found in the data for this band, not counting the no-data
   * value.
   */
  private double max;

  /**
   * Creates a new band for storing data values of the specified width and
   * height.
   *
   * @param width The width of the band in data values.
   * @param height The height of the band in data values.
   * @param noDataValue The no data value to use as the initial value.
   */
  public Band(int width, int height, double noDataValue) {
    data = new double[height][width];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        data[row][col] = noDataValue;
      }
    }
  }

  /**
   * @return the data
   */
  public final double[][] getData() {
    return data;
  }

  /**
   * @return the width
   */
  public final int getWidth() {
    return width;
  }

  /**
   * @return the height
   */
  public final int getHeight() {
    return height;
  }

  /**
   * @return the min
   */
  public final double getMin() {
    return min;
  }

  /**
   * @param min the min to set
   */
  public final void setMin(double min) {
    this.min = min;
  }

  /**
   * @return the max
   */
  public final double getMax() {
    return max;
  }

  /**
   * @param max the max to set
   */
  public final void setMax(double max) {
    this.max = max;
  }

}
