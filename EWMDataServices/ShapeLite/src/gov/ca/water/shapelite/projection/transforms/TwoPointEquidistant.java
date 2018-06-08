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
public class TwoPointEquidistant extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">

        private double _ca;
        private double _ccs;
        private double _cp1;
        private double _cp2;
        private double _cs;
        private double _dlam2;
        private double _hz0;
        private double _lamc;
        private double _lp;
        private double _r2Z0;
        private double _rhshz0;
        private double _sa;
        private double _sc;
        private double _sp1;
        private double _sp2;
        private double _thz0;
        private double _z02;

    //</editor-fold>

    /**
     * Creates a new instance of the TwoPointEquidistant class.
     */
    public TwoPointEquidistant(){
              setProj4Name("tpeqd");
            setEsriName("Two_Point_Equidistant");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
        /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override protected void onInit(ProjectionInfo projInfo) throws ProjectionException
        {
            double pp;

            /* get control point locations */
            double phi1 = projInfo.getPhi1();
            double lam1 = projInfo.getLam1();
            double phi2 = projInfo.getPhi2();
            double lam2 = projInfo.getLam2();
            if (phi1 == phi2 && lam1 == lam2) throw new ProjectionException(-25);
            Lam0 = Proj.Adjlon(0.5 * (lam1 + lam2));
            _dlam2 = Proj.Adjlon(lam2 - lam1);
            _cp1 = Math.cos(phi1);
            _cp2 = Math.cos(phi2);
            _sp1 = Math.sin(phi1);
            _sp2 = Math.sin(phi2);
            _cs = _cp1 * _sp2;
            _sc = _sp1 * _cp2;
            _ccs = _cp1 * _cp2 * Math.sin(_dlam2);
            _z02 = Proj.Aacos(_sp1 * _sp2 + _cp1 * _cp2 * Math.cos(_dlam2));
            _hz0 = .5 * _z02;
            double a12 = Math.atan2(_cp2 * Math.sin(_dlam2),
                                    _cp1 * _sp2 - _sp1 * _cp2 * Math.cos(_dlam2));
            _ca = Math.cos(pp = Proj.Aasin(_cp1 * Math.sin(a12)));
            _sa = Math.sin(pp);
            _lp = Proj.Adjlon(Math.atan2(_cp1 * Math.cos(a12), _sp1) - _hz0);
            _dlam2 *= .5;
            _lamc = HALF_PI - Math.atan2(Math.sin(a12) * _sp1, Math.cos(a12)) - _dlam2;
            _thz0 = Math.tan(_hz0);
            _rhshz0 = .5 / Math.sin(_hz0);
            _r2Z0 = 0.5 / _z02;
            _z02 *= _z02;
        }

        /**   {@inheritDoc }     */
        @Override protected void onForward(double[] lp, double[] xy, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                double t, dl1, dl2;
                double sp = Math.sin(lp[phi]);
                double cp = Math.cos(lp[phi]);
                double z1 = Proj.Aacos(_sp1 * sp + _cp1 * cp * Math.cos(dl1 = lp[lam] + _dlam2));
                double z2 = Proj.Aacos(_sp2 * sp + _cp2 * cp * Math.cos(dl2 = lp[lam] - _dlam2));
                z1 *= z1;
                z2 *= z2;
                xy[x] = _r2Z0 * (t = z1 - z2);
                t = _z02 - t;
                xy[y] = _r2Z0 * Proj.Asqrt(4 * _z02 * z2 - t * t);
                if ((_ccs * sp - cp * (_cs * Math.sin(dl1) - _sc * Math.sin(dl2))) < 0)
                    xy[y] = -xy[y];
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
                double cz1 = Math.cos(Proj.Hypot(xy[y], xy[x] + _hz0));
                double cz2 = Math.cos(Proj.Hypot(xy[y], xy[x] - _hz0));
                double s = cz1 + cz2;
                double d = cz1 - cz2;
                lp[lam] = -Math.atan2(d, (s * _thz0));
                lp[phi] = Proj.Aacos(Proj.Hypot(_thz0 * s, d) * _rhshz0);
                if (xy[y] < 0)
                    lp[phi] = -lp[phi];
                /* lam--phi now in system relative to P1--P2 base equator */
                double sp = Math.sin(lp[phi]);
                double cp = Math.cos(lp[phi]);
                lp[phi] = Proj.Aasin(_sa * sp + _ca * cp * (s = Math.cos(lp[lam] -= _lp)));
                lp[lam] = Math.atan2(cp * Math.sin(lp[lam]), _sa * cp * s - _ca * sp) + _lamc;
            }
        }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
