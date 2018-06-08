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
public final class Proj {

  /**
   * Hide constructor.
   */
  private Proj() {

  }

  /**
   * P00.
   */
  private static final double P00 = .33333333333333333333;
  /**
   * P01.
   */
  private static final double P01 = .17222222222222222222;
  /**
   * P02.
   */
  private static final double P02 = .10257936507936507936;
  /**
   * P10.
   */
  private static final double P10 = .06388888888888888888;
  /**
   * P11.
   */
  private static final double P11 = .06640211640211640211;
  /**
   * P20.
   */
  private static final double P20 = .01641501294219154443;

  /**
   * The size of the array of authalic parameters.
   */
  private static final int APA_SIZE = 3;

  /**
   * Effectively 1 but with a tolerance of 1E-14.
   */
  private static final double ONE_TOL = 1.00000000000001;
  /**
   * 1E-50.
   */
  private static final double ATOL = 1E-50;

  /**
   * Real index.
   */
  private static final int R = 0;
  /**
   * Imaginary index.
   */
  private static final int I = 1;

  /**
   * Tolerant Arcsin.
   *
   * @param v the value to take the arcsin of.
   * @return The arcsin of the value.
   */
  public static double aasin(double v) {
    double av = Math.abs(v);

    if (av >= 1) {
      if (av < ONE_TOL) {
        return Double.NaN;
        //ProjectionException(19);
      }
      if (v < 0) {
        return -Math.PI / 2;
      }
      return Math.PI / 2;
    }
    return Math.asin(v);
  }

  /**
   * Tolerant ArcCosine.
   *
   * @param v The value to take the arccos of.
   * @return The arccos of the value.
   */
  public static double aacos(double v) {
    double av;
    av = Math.abs(v);
    if (av >= 1) {
      if (av < ONE_TOL) {
        return Double.NaN;
      }
      if (v < 0) {
        return Math.PI;
      }
      return 0;
    }
    return Math.acos(v);
  }

  /**
   * Tolerant Sqrt.
   *
   * @param v The value to take the square root of.
   * @return The square root of the value.
   */
  public static double asqrt(double v) {
    if (v <= 0) {
      return 0;
    }
    return Math.sqrt(v);
  }

  /**
   * Tolerant Math.atan method.
   *
   * @param n The double number (x)
   * @param d the second value in the atan expression (y).
   * @return the ArcTan2 from the n and d values.
   */
  public static double aatan2(double n, double d) {
    if ((Math.abs(n) < ATOL && Math.abs(d) < ATOL)) {
      return 0;
    }
    return Math.atan2(n, d);
  }

  /**
   * Calculates the hypotenuse of a triangle: Sqrt(x*x + y*y).
   *
   * @param x The length of one orthogonal leg of the triangle
   * @param y The length of the other orthogonal leg of the triangle
   * @return The length of the diagonal.
   */
  public static double hypot(double x, double y) {
    return Math.sqrt(x * x + y * y);
  }

  /**
   * Adjusts longitude to a degree range from 0 to 2 pi.
   *
   * @param lon The double longitude in radians.
   * @return The adjusted longitude from - PI to PI.
   */
  public static double adjlon(double lon) {
    if (Math.abs(lon) <= Math.PI) {
      return (lon);
    }
    lon += Math.PI;  /* adjust to 0..2pi rad */

    /* remove integral # of 'revolutions'*/
    lon -= 2 * Math.PI * Math.floor(lon / (2 * Math.PI));

    lon -= Math.PI;  /* adjust back to -pi..pi rad */

    return (lon);
  }

  /**
   * Determines latitude from authalic latitude.
   *
   * @param es Epsilon squared
   * @return The array of double values for the apa parameter
   */
  public static double[] authset(double es) {

    double[] apa = new double[APA_SIZE];
    apa[0] = es * P00;
    double t = es * es;
    apa[0] += t * P01;
    apa[1] = t * P10;
    t *= es;
    apa[0] += t * P02;
    apa[1] += t * P11;
    apa[2] = t * P20;
    return apa;
  }

  /**
   * Obtains the authalic latitude.
   *
   * @param beta The beta parameter.
   * @param apa The apa array of coefficients.
   * @return the double authalic latitude.
   */
  public static double authLat(double beta, double[] apa) {
    double t = beta + beta;
    return (beta + apa[0] * Math.sin(t) + apa[1] * Math.sin(t + t)
            + apa[2] * Math.sin(t + t + t));
  }

  /**
   * Obtains the EN parameters for the Meridional distance.
   *
   * @param es The es parameter.
   * @return the Meridional Distance EN parameters array.
   */
  public static double[] enfn(double es) {
    return MeridionalDistance.getEn(es);
  }

  /**
   * Meridional length function.
   *
   * @param phi The angular distance phi;
   * @param sphi Sphi.
   * @param cphi Cphi.
   * @param en En.
   * @return The Meridional Length.
   */
  public static double meridionalLength(double phi, double sphi, double cphi,
          double[] en) {
    return MeridionalDistance.meridionalLength(phi, sphi, cphi, en);
  }

  /**
   * Inverse Meridional length.
   *
   * @param arg The double argument.
   * @param es The double eccentricity parameter.
   * @param en The EN parameters.
   * @return The double angular distance.
   */
  public static double invMeridionalLength(double arg, double es,
          double[] en) {
    return MeridionalDistance.angularDistance(arg, es, en);
  }

  /**
   * The qs projection function.
   *
   * @param sinphi The sin of phi.
   * @param e
   * @param oneEs
   * @return
   */
  public static double qsfn(double sinphi, double e, double oneEs) {
    if (e >= 1E-7) {
      double con = e * sinphi;
      return (oneEs * (sinphi / (1 - con * con)
              - (.5 / e) * Math.log((1 - con) / (1 + con))));
    }
    return sinphi + sinphi;
  }

  /**
   * The ts projection function.
   *
   * @param phi
   * @param sinphi
   * @param e
   * @return
   */
  public static double tsfn(double phi, double sinphi, double e) {
    sinphi *= e;
    double result = (Math.tan(.5 * (Math.PI / 2 - phi)))
            / Math.pow((1 - sinphi) / (1 + sinphi), .5 * e);
    return result;
  }

  /**
   * The ms projection function.
   *
   * @param sinphi
   * @param cosphi
   * @param es
   * @return
   */
  public static double msfn(double sinphi, double cosphi, double es) {
    return (cosphi / Math.sqrt(1 - es * sinphi * sinphi));
  }

  /**
   * The phi2 projection function.
   *
   * @param ts
   * @param e
   * @return
   */
  public static double phi2(double ts, double e) {
    double dphi;
    double tol = 1E-10;
    double eccnth = .5 * e;
    double phi = Math.PI / 2 - 2 * Math.atan(ts);
    int i = 15;
    do {
      double con = e * Math.sin(phi);
      dphi = Math.PI / 2 - 2 * Math.atan(ts * Math.pow((1 - con)
              / (1 + con), eccnth)) - phi;
      phi += dphi;
    } while ((Math.abs(dphi) > tol) && (--i > 0));
    if (i <= 0) {
      return Double.NaN;
      //ProjectionException(18);
    }
    return phi;
  }

  /**
   * The zpoly1 projection function.
   *
   * @param z
   * @param c
   * @param n
   * @return
   */
  public static double[] zpoly1(double[] z, double[][] c, int n) {
    double t;
    double[] a = c[n];
    while (n-- > 0) {
      a[R] = c[n][R] + z[R] * (t = a[R]) - z[I] * a[I];
      a[I] = c[n][I] + z[R] * a[I] + z[I] * t;
    }
    a[R] = z[R] * (t = a[R]) - z[I] * a[I];
    a[I] = z[R] * a[I] + z[I] * t;
    return a;
  }

  /**
   *
   * @param z
   * @param c
   * @param n
   * @param der
   * @return
   */
  public static double[] zpolyd1(double[] z, double[][] c, int n,
          double[] der) {
    double t;
    double[] b = new double[2];
    boolean first = true;
    double[] a = c[n];
    while (n-- > 0) {
      if (first) {
        first = false;
        b = a;
      } else {
        t = b[R];
        b[R] = a[R] + z[R] * t - z[I] * b[I];
        b[I] = a[I] + z[R] * b[I] + z[I] * t;
      }
      t = a[R];
      a[R] = c[n][R] + z[R] * t - z[I] * a[I];
      a[I] = c[n][I] + z[R] * a[I] + z[I] * t;
    }
    t = b[R];
    b[R] = a[R] + z[R] * t - z[I] * b[I];
    b[I] = a[I] + z[R] * b[I] + z[I] * t;
    t = a[R];
    a[R] = z[R] * t - z[I] * z[I];
    a[I] = z[R] * a[I] + z[I] * t;
    der = b;
    return a;
  }
}
