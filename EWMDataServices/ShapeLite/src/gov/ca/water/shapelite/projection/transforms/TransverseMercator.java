/*
 * The MIT License
 *
 * Copyright 2014 hdunsford.
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
package gov.ca.water.shapelite.projection.transforms;

import gov.ca.water.shapelite.projection.MeridionalDistance;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TransverseMercator extends EllipticalTransform {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private static final double FC1 = 1.0;
  private static final double FC2 = .5;
  private static final double FC3 = .16666666666666666666;
  private static final double FC4 = .08333333333333333333;
  private static final double FC5 = .05;
  private static final double FC6 = .03333333333333333333;
  private static final double FC7 = .02380952380952380952;
  private static final double FC8 = .01785714285714285714;
  private double[] _en;

  private double _esp;
  private double _ml0;

  //</editor-fold>
  /**
   * Creates a new instance of the TransverseMercator class.
   */
  public TransverseMercator() {
    this.setEsriName("Transverse_Mercator");
    this.setProj4Name("tmerc");
  }

  @Override
  protected void onInit(ProjectionInfo projInfo) throws ProjectionException {
    if (Es != 0) {
      _en = MeridionalDistance.GetEn(Es);
      if (_en == null) {
        throw new ProjectionException(0);
      }

      _ml0 = MeridionalDistance.MeridionalLength(Phi0, Math.sin(Phi0), Math.cos(Phi0), _en);
      _esp = Es / (1 - Es);
    } else {
      _esp = K0;
      _ml0 = .5 * _esp;
    }
  }

  @Override
  protected void sphericalForward(double[] lp, double[] xy, int startIndex, int numPoints) {
    for (int i = startIndex; i < startIndex + numPoints; i++) {
      int phi = i * 2 + PHI;
      int lam = i * 2 + LAMBDA;
      int x = i * 2 + X;
      int y = i * 2 + Y;
      double cosphi;

      /*
             * Fail if our longitude is more than 90 degrees from the
             * central meridian since the results are essentially garbage.
             * Is error -20 really an appropriate return value?
             *
             *  http://trac.osgeo.org/proj/ticket/5
       */
      if (lp[lam] < -HALF_PI || lp[lam] > HALF_PI) {
        xy[x] = Double.NaN;
        xy[y] = Double.NaN;
        continue;
        //ProjectionException(14);
      }

      double b = (cosphi = Math.cos(lp[phi])) * Math.sin(lp[lam]);
      if (Math.abs(Math.abs(b) - 1) <= EPS10) {
        xy[x] = Double.NaN;
        xy[y] = Double.NaN;
        continue;
        //ProjectionException(20);
      }
      xy[x] = _ml0 * Math.log((1 + b) / (1 - b));
      if ((b = Math.abs(xy[y] = cosphi * Math.cos(lp[lam]) / Math.sqrt(1 - b * b))) >= 1) {
        if ((b - 1) > EPS10) {
          xy[x] = Double.NaN;
          xy[y] = Double.NaN;
          continue;
          //ProjectionException(20);
        }
        xy[y] = 0;
      } else {
        xy[y] = Math.acos(xy[y]);
      }
      if (lp[phi] < 0) {
        xy[y] = -xy[y];
      }
      xy[y] = _esp * (xy[y] - Phi0);
    }
  }

  /**
   * {@inheritDoc }
   */
  @Override
  protected void ellipticalForward(double[] lp, double[] xy, int startIndex, int numPoints) {
    for (int i = startIndex; i < startIndex + numPoints; i++) {
      int phi = i * 2 + PHI;
      int lam = i * 2 + LAMBDA;
      int x = i * 2 + X;
      int y = i * 2 + Y;
      /*
             * Fail if our longitude is more than 90 degrees from the
             * central meridian since the results are essentially garbage.
             * Is error -20 really an appropriate return value?
             *
             *  http://trac.osgeo.org/proj/ticket/5
       */
      if (lp[lam] < -HALF_PI || lp[lam] > HALF_PI) {
        xy[x] = Double.NaN;
        xy[y] = Double.NaN;
        continue;
        //ProjectionException(14);
      }
      double sinphi = Math.sin(lp[phi]);
      double cosphi = Math.cos(lp[phi]);
      double t = Math.abs(cosphi) > 1E-10 ? sinphi / cosphi : 0;
      t *= t;
      double al = cosphi * lp[lam];
      double als = al * al;
      al /= Math.sqrt(1 - Es * sinphi * sinphi);
      double n = _esp * cosphi * cosphi;
      xy[x] = K0 * al * (FC1
          + FC3 * als * (1 - t + n
          + FC5 * als * (5 + t * (t - 18) + n * (14 - 58 * t)
          + FC7 * als * (61 + t * (t * (179 - t) - 479)))));
      xy[y] = K0 * (MeridionalDistance.MeridionalLength(lp[phi], sinphi, cosphi, _en) - _ml0
          + sinphi * al * lp[lam] * FC2 * (1
          + FC4 * als * (5 - t + n * (9 + 4 * n)
          + FC6 * als * (61 + t * (t - 58) + n * (270 - 330 * t)
          + FC8 * als * (1385 + t * (t * (543 - t) - 3111))))));
    }
  }

  /**
   * {@inheritDoc }
   */
  @Override
  protected void sphericalInverse(double[] xy, double[] lp, int startIndex, int numPoints) {
    for (int i = startIndex; i < startIndex + numPoints; i++) {
      int phi = i * 2 + PHI;
      int lam = i * 2 + LAMBDA;
      int x = i * 2 + X;
      int y = i * 2 + Y;
      double h = Math.exp(xy[x] / _esp);
      double g = .5 * (h - 1 / h);
      h = Math.cos(Phi0 + xy[y] / _esp);
      lp[phi] = Math.asin(Math.sqrt((1 - h * h) / (1 + g * g)));
      if (xy[y] < 0) {
        lp[phi] = -lp[phi];
      }
      lp[lam] = ((g != 0 || h != 0) ? Math.atan2(g, h) : 0);
    }
  }

  /**
   * {@inheritDoc }
   */
  @Override
  protected void ellipticalInverse(double[] xy, double[] lp, int startIndex, int numPoints) {
    for (int i = startIndex; i < startIndex + numPoints; i++) {
      int phi = i * 2 + PHI;
      int lam = i * 2 + LAMBDA;
      int x = i * 2 + X;
      int y = i * 2 + Y;
      lp[phi] = MeridionalDistance.AngularDistance(_ml0 + xy[y] / K0, Es, _en);
      if (Math.abs(lp[phi]) >= HALF_PI) {
        lp[phi] = xy[y] < 0 ? -HALF_PI : HALF_PI;
        lp[lam] = 0;
      } else {
        double sinphi = Math.sin(lp[phi]);
        double cosphi = Math.cos(lp[phi]);
        double t = Math.abs(cosphi) > 1e-10 ? sinphi / cosphi : 0;
        double n = _esp * cosphi * cosphi;
        double con;
        double d = xy[x] * Math.sqrt(con = 1 - Es * sinphi * sinphi) / K0;
        con *= t;
        t *= t;
        double ds = d * d;
        lp[phi] -= (con * ds / (1 - Es)) * FC2 * (1
            - ds * FC4 * (5 + t * (3 - 9 * n) + n * (1 - 4 * n)
            - ds * FC6 * (61 + t * (90 - 252 * n
            + 45 * t) + 46 * n
            - ds * FC8 * (1385 + t * (3633 + t * (4095 + 1574 * t))))));
        lp[lam] = d * (FC1
            - ds * FC3 * (1 + 2 * t + n
            - ds * FC5 * (5 + t * (28 + 24 * t + 8 * n) + 6 * n
            - ds * FC7 * (61 + t * (662 + t * (1320 + 720 * t)))))) / cosphi;
      }
    }
  }

}
