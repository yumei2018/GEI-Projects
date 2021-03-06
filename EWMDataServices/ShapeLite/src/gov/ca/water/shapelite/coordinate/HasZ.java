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
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface HasZ {
/**
   * @return the m value, or 0 if this is a coordinate type that does not
   * support Z.
   */
  double getZ();

  /**
   * Sets the Z parameter, or does nothing for 2D coordinates.
   *
   * @param z the Z value to set.
   * @throws IllegalArgumentException if Z is NaN and this is a CoordZ or
   * CoordZ.
   * @throws UnsupportedOperationException if Z is a value other than 0 or NaN
   * and the coordinate type is CoordXY.
   */
  void setZ(final double z);
}
