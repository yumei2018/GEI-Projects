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
package gov.ca.water.shapelite;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class MeridionalDistance {

  /**
   * Hide constructor.
   */
  private MeridionalDistance() {

  }

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The following are all coefficients necessary for the meridional distance
   * calculation.
   */
  /**
   * Coefficient 0,0.
   */
  private static final double C00 = 1;
  /**
   * Coefficient 0,2.
   */
  private static final double C02 = 0.25;
  /**
   * Coefficient 0,4.
   */
  private static final double C04 = .046875;
  /**
   * Coefficient 0,6.
   */
  private static final double C06 = .01953125;
  /**
   * Coefficient 0, 8.
   */
  private static final double C08 = .01068115234375;
  /**
   * Coefficient 2,2.
   */
  private static final double C22 = .75;
  /**
   * Coefficient 4,4.
   */
  private static final double C44 = .46875;
  /**
   * Coefficient 4,6.
   */
  private static final double C46 = .01302083333333333333;
  /**
   * Coefficient 4,8.
   */
  private static final double C48 = .00712076822916666666;
  /**
   * Coefficient 6,6.
   */
  private static final double C66 = .36458333333333333333;
  /**
   * Coefficient 6,8.
   */
  private static final double C68 = .00569661458333333333;
  /**
   * Coefficient 8,8.
   */
  private static final double C88 = .3076171875;

  /**
   * The 0 order EN term.
   */
  private static final int EN0 = 0;
  /**
   * The 1st order EN term.
   */
  private static final int EN1 = 1;
  /**
   * The 2nd order EN term.
   */
  private static final int EN2 = 2;
  /**
   * The 3rd order EN term.
   */
  private static final int EN3 = 3;
  /**
   * The 4th order EN term.
   */
  private static final int EN4 = 4;

  /**
   * Epsilon, the minimum size for determining distinct values.
   */
  private static final double EPS = 1e-11;

  /**
   * Maximum number of iterations.
   */
  private static final int MAX_ITER = 10;

  /**
   * The size of the EN array.
   */
  private static final int EN_SIZE = 5;

  /**
   * Gets an EN structure based on the eccentricity.
   *
   * @param es The es parameter.
   * @return the double array of 4 EN coefficeints.
   */
  public static double[] getEn(double es) {
    double t;
    double[] en = new double[EN_SIZE];
    en[EN0] = C00 - es * (C02 + es * (C04 + es * (C06 + es * C08)));
    en[EN1] = es * (C22 - es * (C04 + es * (C06 + es * C08)));
    t = es * es;
    en[EN2] = t * (C44 - es * (C46 + es * C48));
    t = t * es;
    en[EN3] = t * (C66 - es * C68);
    t = t * es;
    en[EN4] = t * C88;
    return en;
  }

  /**
   * Formerly pj_mlfn Given geodetic angular displacement phi, this calculates
   * the equivalent meridional distance.
   *
   * @param phi The geodetic angular displacement.
   * @param sphi sphi.
   * @param cphi cphi.
   * @param en the double array of EN coefficients.
   * @return the Double meridional length.
   */
  public static double meridionalLength(double phi, double sphi, double cphi,
          double[] en) {
    cphi *= sphi;
    sphi *= sphi;
    return (en[EN0] * phi - cphi * (en[EN1] + sphi * (en[EN2] + sphi
            * (en[EN3] + sphi * en[EN4]))));
  }

  /**
   * Given the linear distance, this calculates the equivalent geodetic angular
   * displacement.
   *
   * @param arg The argument for the angular distance.
   * @param es The es parameter.
   * @param en The double array of EN coefficients.
   * @return The double angular distance.
   */
  public static double angularDistance(double arg, double es, double[] en) {
    double k = 1 / (1 - es);
    double phi = arg;
    for (int i = MAX_ITER; i > 0; --i) { /*
       * rarely goes over 2 iterations
       */

      double s = Math.sin(phi);
      double t = 1 - es * s * s;
      t = (meridionalLength(phi, s, Math.cos(phi), en) - arg)
              * (t * Math.sqrt(t)) * k;
      phi -= t;
      if (Math.abs(t) < EPS) {
        return phi;
      }
    }
    return phi;
    // throw new ProjectionException(17);
  }
}
