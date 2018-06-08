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

import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Stereographic extends EllipticalTransform {

    //<editor-fold defaultstate="collapsed" desc="Fields">

        private static final double TOL = 1E-8;
        private static final int NITER = 8;
        private static final double CONV = 1E-10;
        private double _akm1;
        private double _cosX1;
        private Modes _mode;
        private double _phits;
        private double _sinX1;

    //</editor-fold>

    /**
     * Creates a new instance of the Stereographic class.
     */
    public Stereographic(){
              setEsriName("Stereographic");
              
            setProj4Name("stere");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
       /**   {@inheritDoc }     */
        @Override protected void ellipticalForward(double[] lp, double[] xy, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                double sinX = 0.0, cosX = 0.0;

                double coslam = Math.cos(lp[lam]);
                double sinlam = Math.sin(lp[lam]);
                double sinphi = Math.sin(lp[phi]);
                if (_mode == Modes.Oblique || _mode == Modes.Equatorial)
                {
                    double cx;
                    sinX = Math.sin(cx = 2 * Math.atan(Ssfn(lp[phi], sinphi, E)) - HALF_PI);
                    cosX = Math.cos(cx);
                }
                if (_mode == Modes.Oblique || _mode == Modes.Equatorial)
                {
                    double a;
                    if (_mode == Modes.Oblique)
                    {
                        a = _akm1 / (_cosX1 * (1 + _sinX1 * sinX +
                                               _cosX1 * cosX * coslam));
                        xy[y] = a * (_cosX1 * sinX - _sinX1 * cosX * coslam);
                    }
                    else
                    {
                        a = 2 * _akm1 / (1 + cosX * coslam);
                        xy[y] = a * sinX;
                    }
                    xy[x] = a * cosX;
                }
                else
                {
                    if (_mode == Modes.SouthPole)
                    {
                        lp[phi] = -lp[phi];
                        coslam = -coslam;
                        sinphi = -sinphi;
                    }
                    xy[x] = _akm1 * Proj.Tsfn(lp[phi], sinphi, E);
                    xy[y] = -xy[x] * coslam;
                }
                xy[x] = xy[x] * sinlam;
            }
        }

        /**   {@inheritDoc }     */
        @Override protected void sphericalForward(double[] lp, double[] xy, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                double sinphi = Math.sin(lp[phi]);
                double cosphi = Math.cos(lp[phi]);
                double coslam = Math.cos(lp[lam]);
                double sinlam = Math.sin(lp[lam]);
                if (_mode == Modes.Equatorial || _mode == Modes.Oblique)
                {
                    if (_mode == Modes.Equatorial)
                    {
                        xy[y] = 1 + cosphi * coslam;
                    }
                    else
                    {
                        xy[y] = 1 + _sinX1 * sinphi + _cosX1 * cosphi * coslam;
                    }
                    if (xy[y] <= EPS10)
                    {
                        xy[x] = Double.NaN;
                        xy[y] = Double.NaN;
                        continue;
                        //ProjectionException(20);
                    }
                    xy[x] = (xy[y] = _akm1 / xy[y]) * cosphi * sinlam;
                    xy[y] *= (_mode == Modes.Equatorial)
                                 ? sinphi
                                 :
                                     _cosX1 * sinphi - _sinX1 * cosphi * coslam;
                }
                else
                {
                    if (_mode == Modes.NorthPole)
                    {
                        coslam = -coslam;
                        lp[phi] = -lp[phi];
                    }
                    if (Math.abs(lp[phi] - HALF_PI) < TOL)
                    {
                        xy[x] = Double.NaN;
                        xy[y] = Double.NaN;
                        continue;
                        //ProjectionException(20);
                    }
                    xy[x] = sinlam * (xy[y] = _akm1 * Math.tan(FORT_PI + .5 * lp[phi]));
                    xy[y] *= coslam;
                }
            }
        }

        /**   {@inheritDoc }     */
        @Override protected void ellipticalInverse(double[] xy, double[] lp, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;

                double sinphi, tp = 0.0, phiL = 0.0, halfe = 0.0, halfpi = 0.0;
                int j;

                double rho = Proj.Hypot(xy[x], xy[y]);
                switch (_mode)
                {
                    case Oblique:
                    case Equatorial:
                        double cosphi = Math.cos(tp = 2 * Math.atan2(rho * _cosX1, _akm1));
                        sinphi = Math.sin(tp);
                        if (rho == 0.0)
                            phiL = Math.asin(cosphi * _sinX1);
                        else
                            phiL = Math.asin(cosphi * _sinX1 + (xy[y] * sinphi * _cosX1 / rho));
                        tp = Math.tan(.5 * (HALF_PI + phiL));
                        xy[x] *= sinphi;
                        xy[y] = rho * _cosX1 * cosphi - xy[y] * _sinX1 * sinphi;
                        halfpi = HALF_PI;
                        halfe = .5 * E;
                        break;
                    case NorthPole:
                    case SouthPole:
                        if (_mode == Modes.NorthPole) xy[y] = -xy[y];
                        phiL = HALF_PI - 2 * Math.atan(tp = -rho / _akm1);
                        halfpi = -HALF_PI;
                        halfe = -.5 * E;
                        break;
                }
                for (j = NITER; j-- > 0; phiL = lp[phi])
                {
                    sinphi = E * Math.sin(phiL);
                    lp[phi] = 2 * Math.atan(tp * Math.pow((1 + sinphi) / (1 - sinphi), halfe)) - halfpi;
                    if (Math.abs(phiL - lp[phi]) < CONV)
                    {
                        if (_mode == Modes.SouthPole) lp[phi] = -lp[phi];
                        lp[lam] = (xy[x] == 0 && xy[y] == 0) ? 0 : Math.atan2(xy[x], xy[y]);
                        return;
                    }
                }
                lp[lam] = Double.NaN;
                lp[phi] = Double.NaN;
                continue;
                //ProjectionException(20);
            }
        }

        /**   {@inheritDoc }     */
        @Override protected void sphericalInverse(double[] xy, double[] lp, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                double c, rh;

                double sinc = Math.sin(c = 2 * Math.atan((rh = Proj.Hypot(xy[x], xy[y])) / _akm1));
                double cosc = Math.cos(c);
                lp[lam] = 0;
                switch (_mode)
                {
                    case Equatorial:
                        if (Math.abs(rh) <= EPS10) lp[phi] = 0;
                        else lp[phi] = Math.asin(xy[y] * sinc / rh);
                        if (cosc != 0 || xy[x] != 0) lp[lam] = Math.atan2(xy[x] * sinc, cosc * rh);
                        break;
                    case Oblique:
                        if (Math.abs(rh) <= EPS10) lp[phi] = Phi0;
                        else lp[phi] = Math.asin(cosc * _sinX1 + xy[y] * sinc * _cosX1 / rh);
                        if ((c = cosc - _sinX1 * Math.sin(lp[phi])) != 0 || xy[x] != 0)
                            lp[lam] = Math.atan2(xy[x] * sinc * _cosX1, c * rh);
                        break;
                    case NorthPole:
                    case SouthPole:
                        if (_mode == Modes.NorthPole) xy[y] = -xy[y];
                        if (Math.abs(rh) <= EPS10)
                        {
                            lp[phi] = Phi0;
                        }
                        else
                        {
                            lp[phi] = Math.asin(_mode == Modes.SouthPole ? -cosc : cosc);
                        }
                        lp[lam] = (xy[x] == 0 && xy[y] == 0) ? 0 : Math.atan2(xy[x], xy[y]);
                        break;
                }
            }
        }

        /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override protected void onInit(ProjectionInfo projInfo) throws ProjectionException
        {
            if (projInfo.standardParallel1 != null)
            {
                _phits = projInfo.getPhi1();
            }
            else
            {
                _phits = HALF_PI;
            }
            double t;
            if (Math.abs((t = Math.abs(Phi0)) - HALF_PI) < EPS10)
            {
                _mode = Phi0 < 0 ? Modes.SouthPole : Modes.NorthPole;
            }
            else
            {
                _mode = t > EPS10 ? Modes.Oblique : Modes.Equatorial;
            }
            _phits = Math.abs(_phits);
            if (Es != 0)
            {
                switch (_mode)
                {
                    case NorthPole:
                    case SouthPole:
                        if (Math.abs(_phits - HALF_PI) < EPS10)
                        {
                            _akm1 = 2 * K0 / Math.sqrt(Math.pow(1 + E, 1 + E) * Math.pow(1 - E, 1 - E));
                        }
                        else
                        {
                            _akm1 = Math.cos(_phits) / Proj.Tsfn(_phits, t = Math.sin(_phits), E);
                            t *= E;
                            _akm1 /= Math.sqrt(1 - t * t);
                        }
                        break;
                    case Equatorial:
                        _akm1 = 2 * K0;
                        break;
                    case Oblique:
                        t = Math.sin(Phi0);
                        double x = 2 * Math.atan(Ssfn(Phi0, t, E)) - HALF_PI;
                        t *= E;
                        _akm1 = 2 * K0 * Math.cos(Phi0) / Math.sqrt(1 - t * t);
                        _sinX1 = Math.sin(x);
                        _cosX1 = Math.cos(x);
                        break;
                }
            }
            else
            {
                if (_mode == Modes.Oblique || _mode == Modes.Equatorial)
                {
                    if (_mode == Modes.Oblique)
                    {
                        _sinX1 = Math.sin(Phi0);
                        _cosX1 = Math.cos(Phi0);
                    }
                    _akm1 = 2 * K0;
                }
                else
                {
                    _akm1 = Math.abs(_phits - HALF_PI) >= EPS10 ?
                                                                    Math.cos(_phits) / Math.tan(FORT_PI - .5 * _phits) :
                                                                                                                           2 * K0;
                }
            }
        }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  
        private static double Ssfn(double phit, double sinphi, double eccen)
        {
            sinphi *= eccen;
            return (Math.tan(.5 * (HALF_PI + phit)) *
                Math.pow((1 - sinphi) / (1 + sinphi), .5 * eccen));
        }

    //</editor-fold>

}
