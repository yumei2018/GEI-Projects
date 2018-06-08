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

import java.util.Arrays;

/**
 * This process uses the idea of a "steepest descent" model to check the local
 * neighbors of the starting parameters.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LeastSumOfSquares {

  /**
   * The smallest allowable step size.
   */
  public static final double STEP_LIMIT = .0000000001;

  /**
   * The factor to change with each step.
   */
  public static final double STEP_FACTOR = 10;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The function that will have the least sum of squares calculated between it
   * and the specified data values.
   */
  private Function function;

  /**
   * The calculated root mean square error for the function.
   */
  private double rmse;

  /**
   * The set of weights to determine how much emphasis to place on individual
   * values for determining how good the fit is.
   */
  private double[] weights;

  /**
   * The independent variables used in both the fit function and in the real
   * data.
   */
  private double[] x;

  /**
   * The dependent variables that are the real data to test the fit against.
   */
  private double[] y;

    //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * This optimization only finds the closest local minimum for Sum of Square
   * differences.
   */
  public final void optimize() {
    double stepsize = 1;

    while (stepsize > STEP_LIMIT) {
      boolean successful = true;
      while (successful) {
        successful = stepDown(stepsize);
      }
      stepsize = stepsize / STEP_FACTOR;
    }

    // the optimized parameters are found in the function.
  }

  /**
   * Assuming the values have been calculated, this calculates the returns the
   * direction with the minimum Sum of Squares value, adjusted by the weights of
   * the function.
   *
   * @param stepSize The double step size to use.
   * @return A boolean, true if the operation was successful.
   */
  public final boolean stepDown(double stepSize) {
    int minimumAddress = 0;
    double minimumSS = Double.MAX_VALUE;
    int numDimensions = function.getParameters().length;
    double[] originalParameters = function.getParameters();
    int dimensions = function.getParameters().length;
    int count = Neighbors.getMaximumAddress(dimensions);
    for (int i = 0; i < count; i++) {
      int[] direction = Neighbors.getDirection(i, dimensions);
      double[] params = Arrays.copyOf(originalParameters,
              originalParameters.length);
      for (int d = 0; d < direction.length; d++) {
        params[d] = params[d] + direction[d] * stepSize;
      }
      function.setParameters(params);
      double ss = SumOfSquares.calculate(function, x, y, weights);
      if (ss < minimumSS) {
        minimumSS = ss;
        minimumAddress = i;
      }
    }

    // If the minimum is 0, then we are currently at the bottom of the hill.
    if (minimumAddress == 0) {
      return false;
    }

        // If the minimum is not 0, then move exactly one stepsize in the
    // specified direction.
    int[] dir = Neighbors.getDirection(minimumAddress, numDimensions);
    double[] params = Arrays.copyOf(originalParameters,
            originalParameters.length);
    for (int d = 0; d < dir.length; d++) {
      params[d] = params[d] + dir[d] * stepSize;
    }

    function.setParameters(params);
    setRmse(Math.sqrt(minimumSS));
    return true;
  }

  //</editor-fold>
  /**
   * Gets the function that will have the least sum of squares calculated
   * between it and the specified data values.
   *
   * @return the function.
   */
  public final Function getFunction() {
    return function;
  }

  /**
   * Sets the function that will have the least sum of squares calculated
   * between it and the specified data values.
   *
   * @param function the function to set.
   */
  public final void setFunction(Function function) {
    this.function = function;
  }

  /**
   * Gets the calculated root mean square error for the function.
   *
   * @return the rmse the root mean square error to fit.
   */
  public final double getRmse() {
    return rmse;
  }

  /**
   * Sets the calculated root mean square error for the function.
   *
   * @param rmse the rmse to set.
   */
  public final void setRmse(double rmse) {
    this.rmse = rmse;
  }

  /**
   * Gets the set of weights to determine how much emphasis to place on
   * individual values for determining how good the fit is.
   *
   * @return the w.
   */
  public final double[] getWeights() {
    return weights;
  }

  /**
   * Sets the set of weights to determine how much emphasis to place on
   * individual values for determining how good the fit is.
   *
   * @param w the w to set.
   */
  public final void setWeights(double[] w) {
    this.weights = w;
  }

  /**
   * Gets the independent variables used in both the fit function and in the
   * real data.
   *
   * @return the x
   */
  public final double[] getX() {
    return x;
  }

  /**
   * Sets the independent variables used in both the fit function and in the
   * real data.
   *
   * @param x the x to set
   */
  public final void setX(double[] x) {
    this.x = x;
  }

  /**
   * Gets the dependent variables that are the real data to test the fit
   * against.
   *
   * @return the y
   */
  public final double[] getY() {
    return y;
  }

  /**
   * Sets the dependent variables that are the real data to test the fit
   * against.
   *
   * @param y the y to set
   */
  public final void setY(double[] y) {
    this.y = y;
  }

}
