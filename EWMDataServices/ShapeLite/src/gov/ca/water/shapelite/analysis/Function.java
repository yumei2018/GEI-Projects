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
package gov.ca.water.shapelite.analysis;

/**
 * This is an effort at creating a generalized, interchangeable function. This
 * allows for processes like LeastSumOfSquares to be applied to a variety of
 * functional fits.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface Function {

  /**
   * Gets the parameter array for this function.
   *
   * @return a double array of parameters.
   */
  double[] getParameters();

  /**
   * Sets the parameter array for this function.
   *
   * @param params the double array of parameters to set.
   */
  void setParameters(double[] params);

  /**
   * Implements the function on the specified x value.
   *
   * @param x the x input for the function.
   * @return The resulting y calculation for the function.
   */
  double execute(double x);
}