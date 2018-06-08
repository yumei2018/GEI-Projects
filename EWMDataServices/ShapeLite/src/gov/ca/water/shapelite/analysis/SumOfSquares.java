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
 * Simply calculates the mathematical sum of squares, or a weighted sum of
 * squares, depending on whether an array of weights is specified.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class SumOfSquares {

  /**
   * Hidden constructor.
   */
  private SumOfSquares() {

  }

  /**
   * This overload assumes that all the data are weighted equally.
   *
   * @param f A function, like an exponential curve, to compare against the y
   * values.
   * @param x An array of double precision x coordinates that are the
   * independent variable in both series of raw data values and the calculated
   * output.
   * @param y The y value.
   * @return A double value.
   */
  public static double calculate(Function f, double[] x, double[] y) {
    double[] weights = new double[x.length];
    for (int i = 0; i < x.length; i++) {
      weights[i] = 1.0;
    }
    return calculate(f, x, y, weights);
  }

  /**
   * Calculates the sum of square differences, where the differences are
   * multiplied by the specified weight.
   *
   * @param f A function like an exponential curve, to compare against the y
   * values.
   * @param x An array of x values.
   * @param y An array of y values.
   * @param weights The double weights.
   * @return A double value that is the weighted sum of squares.
   */
  public static double calculate(Function f, double[] x, double[] y,
          double[] weights) {
    double sum = 0;
    for (int i = 0; i < x.length; i++) {
      double dif = (1 - f.execute(x[i]) / y[i]);
      sum += dif * dif;
    }
    return sum;
  }

}
