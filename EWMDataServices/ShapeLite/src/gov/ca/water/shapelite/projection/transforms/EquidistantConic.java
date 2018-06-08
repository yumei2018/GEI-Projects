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
public class EquidistantConic extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">
        private double _c;
        private double[] _en;
        private double _n;
        private double _phi1;
        private double _phi2;
        private double _rho;
        private double _rho0;


    //</editor-fold>

    /**
     * Creates a new instance of the EquidistantConic class.
     */
    public EquidistantConic(){
              setProj4Name("eqdc");
            setEsriName("Equidistant_Conic");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
        /**   {@inheritDoc }     */
        @Override protected void onForward(double[] lp, double[] xy, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                _rho = _c - (IsElliptical
                                 ? Proj.Mlfn(lp[phi], Math.sin(lp[phi]),
                                             Math.cos(lp[phi]), _en)
                                 : lp[phi]);
                xy[x] = _rho * Math.sin(lp[lam] *= _n);
                xy[y] = _rho0 - _rho * Math.cos(lp[lam]);
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
                if ((_rho = Proj.Hypot(xy[x], xy[y] = _rho0 - xy[y])) != 0.0)
                {
                    if (_n < 0)
                    {
                        _rho = -_rho;
                        xy[x] = -xy[x];
                        xy[y] = -xy[y];
                    }
                    lp[phi] = _c - _rho;
                    if (IsElliptical)
                        lp[phi] = Proj.InvMlfn(lp[phi], Es, _en);
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
        /// This exists in the case that we ever develop code to perform the special proj4 calculations
        /// </summary>
        /// <param name="lp"></param>
        /// <param name="p"></param>
        /// <param name="fac"></param>
        @Override protected void onSpecial(double[] lp, ProjectionInfo p, Factors fac)
        {
            double sinphi = Math.sin(lp[PHI]);
            double cosphi = Math.cos(lp[PHI]);
            fac.code.add(AnalyticModes.AnalHk);
            fac.h = 1;
            fac.k = _n * (_c - (IsElliptical
                                    ? Proj.Mlfn(lp[PHI], sinphi,
                                                cosphi, _en)
                                    : lp[PHI])) / Proj.Msfn(sinphi, cosphi, Es);
        }

        /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override protected void onInit(ProjectionInfo projInfo) throws ProjectionException
        {
            double sinphi;

            if (projInfo.standardParallel1 != null) _phi1 = projInfo.standardParallel1 * Math.PI / 180;
            if (projInfo.standardParallel2 != null) _phi2 = projInfo.standardParallel2 * Math.PI / 180;

            if (Math.abs(_phi1 + _phi2) < EPS10) throw new ProjectionException(-21);
            _en = Proj.Enfn(Es);
            _n = sinphi = Math.sin(_phi1);
            double cosphi = Math.cos(_phi1);
            boolean secant = Math.abs(_phi1 - _phi2) >= EPS10;
            if (IsElliptical)
            {
                double m1 = Proj.Msfn(sinphi, cosphi, Es);
                double ml1 = Proj.Mlfn(_phi1, sinphi, cosphi, _en);
                if (secant)
                {
                    /* secant cone */
                    sinphi = Math.sin(_phi2);
                    cosphi = Math.cos(_phi2);
                    _n = (m1 - Proj.Msfn(sinphi, cosphi, Es)) /
                         (Proj.Mlfn(_phi2, sinphi, cosphi, _en) - ml1);
                }
                _c = ml1 + m1 / _n;
                _rho0 = _c - Proj.Mlfn(Phi0, Math.sin(Phi0),
                                       Math.cos(Phi0), _en);
            }
            else
            {
                if (secant)
                    _n = (cosphi - Math.cos(_phi2)) / (_phi2 - _phi1);
                _c = _phi1 + Math.cos(_phi1) / _n;
                _rho0 = _c - Phi0;
            }
        }


    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
