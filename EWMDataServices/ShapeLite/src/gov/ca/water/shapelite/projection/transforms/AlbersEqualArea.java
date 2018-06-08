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
public class AlbersEqualArea extends Transform{

    //<editor-fold defaultstate="collapsed" desc="Fields">


        private static final int N_ITER = 15;
        private static final double EPSILON = 1.0E-7;
        private static final double TOL = 1E-10;
        private static final double TOL7 = 1E-7;

        private double _c;
        private double _dd;
        private double _ec;
        private double _n;
        private double _n2;
        protected double Phi1;
        protected double Phi2;
        private double _rho;
        private double _rho0;

    //</editor-fold>

    /**
     * Creates a new instance of the AlbersEqualArea class.
     */
    public AlbersEqualArea(){
       setEsriName("Albers");
            setProj4Name("aea");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
       /**   {@inheritDoc }     */
    @Override
        protected void onForward(double[] lp, double[] xy, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;

                if ((_rho = _c - (IsElliptical
                                      ? _n * Proj.Qsfn(Math.sin(lp[phi]),
                                                       E, OneEs)
                                      : _n2 * Math.sin(lp[phi]))) < 0)
                {
                    xy[x] = Double.NaN;
                    xy[y] = Double.NaN;
                    continue;
                    //throw new ProjectionException(20);
                }
                _rho = _dd * Math.sqrt(_rho);
                xy[x] = _rho * Math.sin(lp[lam] *= _n);
                xy[y] = _rho0 - _rho * Math.cos(lp[lam]);
            }
        }

        private static double PhiFn(double qs, double te, double toneEs)
        {
            double dphi;
            double myPhi = Math.asin(.5 * qs);
            if (te < EPSILON) return (myPhi);
            int i = N_ITER;
            do
            {
                double sinpi = Math.sin(myPhi);
                double cospi = Math.cos(myPhi);
                double con = te * sinpi;
                double com = 1 - con * con;
                dphi = .5 * com * com / cospi * (qs / toneEs -
                                                 sinpi / com + .5 / te * Math.log((1 - con) / (1 + con)));
                myPhi += dphi;
            }
            while (Math.abs(dphi) > TOL && --i > 0);
            return (i != 0 ? myPhi : Double.MAX_VALUE);
        }

        /**   {@inheritDoc }     */
        @Override
        protected void onInverse(double[] xy, double[] lp, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;

                if ((_rho = Proj.Hypot(xy[x], xy[y] = _rho0 - xy[y])) != 0.0)
                {
                    if (_n < 0)
                    {
                        _rho = -_rho;
                        xy[x] = -xy[x];
                        xy[y] = -xy[y];
                    }
                    lp[phi] = _rho / _dd;
                    if (IsElliptical)
                    {
                        lp[phi] = (_c - lp[phi] * lp[phi]) / _n;
                        if (Math.abs(_ec - Math.abs(lp[phi])) > TOL7)
                        {
                            if ((lp[phi] = PhiFn(lp[phi], E, OneEs)) == Double.MAX_VALUE)
                            {
                                lp[phi] = Double.NaN;
                                lp[lam] = Double.NaN;
                                continue;
                                //throw new ProjectionException(20);
                            }
                        }
                        else
                        {
                            lp[phi] = lp[phi] < 0 ? -HALF_PI : HALF_PI;
                        }
                    }
                    else if (Math.abs(lp[phi] = (_c - lp[phi] * lp[phi]) / _n2) <= 1)
                    {
                        lp[phi] = Math.asin(lp[phi]);
                    }
                    else
                    {
                        lp[phi] = lp[phi] < 0 ? -HALF_PI : HALF_PI;
                    }
                    lp[lam] = Math.atan2(xy[x], xy[y]) / _n;
                }
                else
                {
                    lp[lam] = 0;
                    lp[phi] = _n > 0 ? HALF_PI : -HALF_PI;
                }
            }
        }

        /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override
        protected void onInit(ProjectionInfo projInfo) throws ProjectionException
        {
            Phi1 = projInfo.getPhi1();
            Phi2 = projInfo.getPhi2();
            setup();
        }

        /// <summary>
        /// Internal code handling the setup operations for the transform
        /// </summary>
        protected void setup() throws ProjectionException
        {
            double sinphi;
            if (Math.abs(Phi1 + Phi2) < EPS10)
            {
                throw new ProjectionException(-21);
            }
            _n = sinphi = Math.sin(Phi1);
            double cosphi = Math.cos(Phi1);
            boolean secant = Math.abs(Phi1 - Phi2) >= EPS10;
            if (IsElliptical)
            {
                double m1 = Proj.Msfn(sinphi, cosphi, Es);
                double ml1 = Proj.Qsfn(sinphi, E, OneEs);
                if (secant)
                { /* secant cone */
                    sinphi = Math.sin(Phi2);
                    cosphi = Math.cos(Phi2);
                    double m2 = Proj.Msfn(sinphi, cosphi, Es);
                    double ml2 = Proj.Qsfn(sinphi, E, OneEs);
                    _n = (m1 * m1 - m2 * m2) / (ml2 - ml1);
                }
                _ec = 1 - .5 * OneEs * Math.log((1 - E) / (1 + E)) / E;
                _c = m1 * m1 + _n * ml1;
                _dd = 1 / _n;
                _rho0 = _dd * Math.sqrt(_c - _n * Proj.Qsfn(Math.sin(Phi0), E, OneEs));
            }
            else
            {
                if (secant) _n = .5 * (_n + Math.sin(Phi2));
                _n2 = _n + _n;
                _c = cosphi * cosphi + _n2 * sinphi;
                _dd = 1 / _n;
                _rho0 = _dd * Math.sqrt(_c - _n2 * Math.sin(Phi0));
            }
        }

    //</editor-fold>



}
