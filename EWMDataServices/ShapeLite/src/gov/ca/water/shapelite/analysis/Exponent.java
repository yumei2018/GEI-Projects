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
 * This class stores the power and factor parameters for a fit curve.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Exponent implements Function {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The multiplicative factor for this function.
   */
  public static final int FACTOR = 0;

  /**
   * The double array of parameters for this function.
   */
  private double[] parameters;

  /**
   * The exponential power for this function.
   */
  public static final int POWER = 1;

  //</editor-fold>
  /**
   * This function works according to y = factor*x^power.
   *
   * @param factor The multiplicative factor.
   * @param power The exponential power.
   */
  public Exponent(double factor, double power) {
    parameters = new double[]{factor, power};
  }

  /**
   * Sets the value of the parameter.
   *
   * @param param FACTOR or POWER.
   * @param value The value to set.
   */
  public final void setParameter(int param, double value) {
    parameters[param] = value;
  }

  /**
   * Gets the value of the parameter at the specified value.
   *
   * @param param FACTOR or POWER
   * @return the double value of the specified paramter
   */
  public final double getParameter(int param) {
    return parameters[param];
  }

  @Override
  public final double[] getParameters() {
    return parameters;
  }

  @Override
  public final void setParameters(double[] params) {
    parameters = params;
  }

  /**
   * Actually returns Y.
   *
   * @param x
   * @return
   */
  @Override
  public final double execute(double x) {
    return parameters[FACTOR] * Math.pow(x, parameters[POWER]);
  }

}
