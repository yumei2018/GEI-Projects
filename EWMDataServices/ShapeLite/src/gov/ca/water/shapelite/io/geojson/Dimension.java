/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.io.geojson;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public enum Dimension {

  /**
   * No coordinates.
   */
  None(0),
  /**
   * A single coordinate, like a point.
   */
  One(1),
  /**
   * Multiple coordinates like a LineString or MultiPoint.
   */
  Two(2),
  /**
   * Multiple lines, like a MultiLineString or Polygon (with shell and holes).
   */
  Three(3),
  /**
   * Multiple Polygons.
   */
  Four(4);

  /**
   * One dimensional array list.
   */
  public static final int DIM_1 = 1;
  /**
   * Two dimensional array list.
   */
  public static final int DIM_2 = 2;
  /**
   * Three dimensional array list.
   */
  public static final int DIM_3 = 3;
  /**
   * Four dimensional array list.
   */
  public static final int DIM_4 = 4;

  /**
   * The integer representation of the dimension.
   */
  private final int dim;

  /**
   * Private constructor.
   *
   * @param dim the integer dimension.
   */
  private Dimension(int dim) {
    this.dim = dim;
  }

  /**
   * Creates a Dimension enumeration from an integer value.
   *
   * @param value The value to create.
   * @return A Dimension enumeration.
   */
  public static Dimension fromInt(int value) {
    switch (value) {
      case DIM_1:
        return Dimension.One;
      case DIM_2:
        return Dimension.Two;
      case DIM_3:
        return Dimension.Three;
      case DIM_4:
        return Dimension.Four;
      default:
        return Dimension.None;
    }
  }

  /**
   * Gets the integer representation of this enumeration.
   *
   * @return The integer.
   */
  public int toInt() {
    return this.dim;
  }

}
