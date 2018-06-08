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

import gov.ca.water.shapelite.projection.ProjectionInfo;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ObliqueCylindricalEqualArea extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">

        private double _cosphi;
        private double _rok;
        private double _rtk;
        private double _singam;
        private double _sinphi;

    //</editor-fold>

    /**
     * Creates a new instance of the ObliqueCylindricalEqualArea class.
     */
    public ObliqueCylindricalEqualArea(){
              setProj4Name("ocea");
            setEsriName("Oblique_Cylindrical_Equal_Area");
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
                xy[y] = Math.sin(lp[lam]);
                double t = Math.cos(lp[lam]);
                xy[x] = Math.atan((Math.tan(lp[phi]) * _cosphi + _sinphi * xy[y]) / t);
                if (t < 0) xy[x] += Math.PI;
                xy[x] *= _rtk;
                xy[y] = _rok * (_sinphi * Math.sin(lp[phi]) - _cosphi * Math.cos(lp[phi]) * xy[y]);
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
                double s;
                xy[y] /= _rok;
                xy[x] /= _rtk;
                double t = Math.sqrt(1 - xy[y] * xy[y]);
                lp[phi] = Math.asin(xy[y] * _sinphi + t * _cosphi * (s = Math.sin(xy[x])));
                lp[lam] = Math.atan2(t * _sinphi * s - xy[y] * _cosphi,
                                     t * Math.cos(xy[x]));
            }
        }
        
               /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override protected void onInit(ProjectionInfo projInfo)
        {
            double phi0 = 0.0;

            _rok = A / K0;
            _rtk = A * K0;
            if (projInfo.alpha != null)
            {
                double alpha = projInfo.alpha * DEG_TO_RAD;
                double lonz = projInfo.lonc * DEG_TO_RAD;
                _singam = Math.atan(-Math.cos(alpha) / (-Math.sin(phi0) * Math.sin(alpha))) + lonz;
                _sinphi = Math.asin(Math.cos(phi0) * Math.sin(alpha));
            }
            else
            {
                double phi1 = projInfo.getPhi1();
                double phi2 = projInfo.getPhi2();
                double lam1 = projInfo.lon_1 * DEG_TO_RAD;
                double lam2 = projInfo.lon_2 * DEG_TO_RAD;
                _singam = Math.atan2(Math.cos(phi1) * Math.sin(phi2) * Math.cos(lam1) -
                                     Math.sin(phi1) * Math.cos(phi2) * Math.cos(lam2),
                                     Math.sin(phi1) * Math.cos(phi2) * Math.sin(lam2) -
                                     Math.cos(phi1) * Math.sin(phi2) * Math.sin(lam1));
                _sinphi = Math.atan(-Math.cos(_singam - lam1) / Math.tan(phi1));
            }
            Lam0 = _singam + HALF_PI;
            _cosphi = Math.cos(_sinphi);
            _sinphi = Math.sin(_sinphi);
            _singam = Math.sin(_singam);
        }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
