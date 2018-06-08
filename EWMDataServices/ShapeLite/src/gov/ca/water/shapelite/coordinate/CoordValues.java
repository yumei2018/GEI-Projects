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
package gov.ca.water.shapelite.coordinate;

/**
 * This interface provides the simplest, most generic coordinate interface
 * for testing values, with a minimum of convenience methods.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface CoordValues {


  /**
   * Gets the i'th ordinate from the coordinate. This supports the following:
   * <ul>
   * <li>X - 0</li>
   * <li>Y - 1</li>
   * <li>Z - 2> (Only supported by CoordZ and Vector types.) </li>
   * <li>M - 3> (Only supported by CoordM and CoordZ types.)</li>
   * </ul>
   *
   * @param index The zero based integer index.
   * @return A double value represented by the specified index.
   */
  double get(int index);

  /**
   * Sets the i'th ordinate from the coordinate. This supports the following:
   * <ul>
   * <li>X - 0</li>
   * <li>Y - 1</li>
   * <li>Z - 2> (Only supported by CoordZ and Vector types.) </li>
   * <li>M - 3> (Only supported by CoordM and CoordZ types.)</li>
   * </ul>
   *
   * @param index The zero based integer index of the ordinate to set.
   * @param value A double value to set.
   */
   void set(int index, double value);

  /**
   * Gets the integer maximum index for the get operation. This does not
   * guarantee that all index constants are supported. CoordM in particular does
   * not support 2 - the index for Z.
   *
   * @return An integer representing one higher than the largest ordinate index.
   * So for instance CoordM, which supports M, but not Z values will still have
   * a size of 4.
   */
  int getSize();

  /**
   * Gets a boolean that indicates whether this coordinate supports a coordinate
   * for the specified index.  This is the generic form so that index values
   * higher than the original 4 may be supported by this interface.
   *
   * @param index The integer index being supported.
   * @return Boolean, true if the coordinate has a value for that index.
   */
  boolean has(int index);

}
