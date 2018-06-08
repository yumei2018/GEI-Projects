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

import gov.ca.water.shapelite.projection.AnalyticModes;
import gov.ca.water.shapelite.projection.Factors;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LambertConformalConic extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">

        private double _c;
        private boolean _ellipse;
        private double _n;
        private double _phi1;
        private double _phi2;
        private double _rho;
        private double _rho0;

    //</editor-fold>

    /**
     * Creates a new instance of the LambertConformalConic class.
     */
    public LambertConformalConic(){
            setEsriName("Lambert_Conformal_Conic");
            this.setEsriAliases(new String[]{"Lambert_Conformal_Conic_2SP"});
            setProj4Name("lcc");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
       @Override protected void onForward(double[] lp, double[] xy, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                if (Math.abs(Math.abs(lp[phi]) - HALF_PI) < EPS10)
                {
                    if (lp[phi] * _n <= 0)
                    {
                        xy[x] = Double.NaN;
                        xy[y] = Double.NaN;
                        continue;
                        //throw new ProjectionException(20);
                    }
                    _rho = 0;
                }
                else
                {
                    double cx;
                    if (_ellipse)
                    {
                        cx = Math.pow(Proj.Tsfn(lp[phi], Math.sin(lp[phi]), E), _n);
                    }
                    else
                    {
                        cx = Math.pow(Math.tan(Math.PI / 4 + .5 * lp[phi]), -_n);
                    }
                    _rho = _c * cx;
                }
                double nLam = lp[lam] * _n;
                xy[x] = K0 * (_rho * Math.sin(nLam));
                xy[y] = K0 * (_rho0 - _rho * Math.cos(nLam));
            }
        }

        /**   {@inheritDoc }     */
        @Override protected void onInverse(double[] xy, double[] lp, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                double cx = xy[x] / K0;
                double cy = _rho0 - xy[y] / K0;

                _rho = Math.sqrt(cx * cx + cy * cy);

                if (_rho != 0.0)
                {
                    if (_n < 0)
                    {
                        _rho = -_rho;
                        cx = -cx;
                        cy = -cy;
                    }
                    if (_ellipse)
                    {
                        double temp = Math.pow(_rho / _c, 1 / _n);
                        lp[phi] = Proj.Phi2(temp, E);
                        if (lp[phi] == Double.MAX_VALUE)
                        {
                            lp[lam] = Double.NaN;
                            lp[phi] = Double.NaN;
                            continue;
                            //throw new ProjectionException(20);
                        }
                    }
                    else
                    {
                        lp[phi] = 2 * Math.atan(Math.pow(_c / _rho, 1 / _n)) - HALF_PI;
                    }
                    lp[lam] = Math.atan2(cx, cy) / _n;
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
        @Override protected void onInit(ProjectionInfo projInfo) throws ProjectionException
        {
            double sinphi;
            _phi1 = projInfo.getPhi1();
            if (projInfo.standardParallel2 != null)
            {
                _phi2 = projInfo.getPhi2();
            }
            else
            {
                _phi2 = _phi1;
                _phi1 = projInfo.getPhi0();
            }
            if (Math.abs(_phi1 + _phi2) < EPS10)
            {
                throw new ProjectionException(21);
            }
            _n = sinphi = Math.sin(_phi1);
            double cosphi = Math.cos(_phi1);
            boolean secant = Math.abs(_phi1 - _phi2) >= EPS10;
            _ellipse = projInfo.getGeographicInfo().getDatum().getSpheroid().isOblate();
            if (_ellipse)
            {
                double m1 = Proj.Msfn(sinphi, cosphi, Es);
                double ml1 = Proj.Tsfn(_phi1, sinphi, E);
                if (secant)
                {
                    sinphi = Math.sin(_phi2);
                    _n = Math.log(m1 / Proj.Msfn(sinphi, Math.cos(_phi2), Es));
                    _n = _n / Math.log(ml1 / Proj.Tsfn(_phi2, sinphi, E));
                }
                _rho0 = m1 * Math.pow(ml1, -_n) / _n;
                _c = _rho0;
                if (Math.abs(Math.abs(Phi0) - HALF_PI) < EPS10)
                {
                    _rho0 = 0;
                }
                else
                {
                    _rho0 *= Math.pow(Proj.Tsfn(Phi0, Math.sin(Phi0), E), _n);
                }
            }
            else
            {
                if (secant)
                {
                    _n = Math.log(cosphi / Math.cos(_phi2)) /
                         Math.log(Math.tan(Math.PI / 4 + .5 * _phi2) /
                                  Math.tan(Math.PI / 4 + .5 * _phi1));
                    _c = cosphi * Math.pow(Math.tan(Math.PI / 4 + .5 * _phi1), _n) / _n;
                }
                if (Math.abs(Math.abs(Phi0) - HALF_PI) < EPS10)
                {
                    _rho0 = 0;
                }
                else
                {
                    _rho0 = _c * Math.pow(Math.tan(Math.PI / 4 + .5 * Phi0), -_n);
                }
            }
        }

        /// <summary>
        /// Special factor calculations for a factors calculation
        /// </summary>
        /// <param name="lp">lambda-phi</param>
        /// <param name="p">The projection</param>
        /// <param name="fac">The Factors</param>
        @Override protected void onSpecial(double[] lp, ProjectionInfo p, Factors fac)
        {
            if (Math.abs(Math.abs(lp[PHI]) - HALF_PI) < EPS10)
            {
                if ((lp[PHI] * _n) <= 0) return;
                _rho = 0;
            }
            else
            {
                _rho = _c * (_ellipse ? Math.pow(Proj.Tsfn(lp[PHI], Math.sin(lp[PHI]),
                                                           p.getGeographicInfo().getDatum().getSpheroid().getEccentricity()), _n)
                                 : Math.pow(Math.tan(Math.PI / 4 + .5 * lp[PHI]), -_n));
            }
            fac.code.add(AnalyticModes.AnalHk);
            fac.code.add(AnalyticModes.AnalConv);
            fac.k = fac.h = p.getScaleFactor() * _n * _rho / Proj.Msfn(Math.sin(lp[PHI]), Math.cos(lp[PHI]), 
                    p.getGeographicInfo().getDatum().getSpheroid().getEccentricitySquared());
            fac.conv = -_n * lp[LAMBDA];
        }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
