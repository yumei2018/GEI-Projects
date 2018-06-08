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
public class Cassini extends EllipticalTransform {

    //<editor-fold defaultstate="collapsed" desc="Fields">
        private static final double C1 = .16666666666666666666;
        private static final double C2 = .00833333333333333333;
        private static final double C3 = .04166666666666666666;
        private static final double C4 = .33333333333333333333;
        private static final double C5 = .06666666666666666666;
        private double _a1;
        private double _a2;
        private double _c;
        private double _d2;
        private double _dd;
        private double[] _en;
        private double _m0;
        private double _n;
        private double _r;
        private double _t;
        private double _tn;


    //</editor-fold>

    /**
     * Creates a new instance of the Cassini class.
     */
    public Cassini(){
        setProj4Name("cass");
        setEsriName("Cassini");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
    @Override
    protected void sphericalForward(double[] lp, double[] xy, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                xy[x] = Math.asin(Math.cos(lp[phi]) * Math.sin(lp[lam]));
                xy[y] = Math.atan2(Math.tan(lp[phi]), Math.cos(lp[lam])) - Phi0;
            }
        }

        /**   {@inheritDoc }     */
    @Override
        protected void ellipticalForward(double[] lp, double[] xy, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                xy[y] = Proj.Mlfn(lp[phi], _n = Math.sin(lp[phi]), _c = Math.cos(lp[phi]), _en);
                _n = 1 / Math.sqrt(1 - Es * _n * _n);
                _tn = Math.tan(lp[phi]);
                _t = _tn * _tn;
                _a1 = lp[lam] * _c;
                _c *= Es * _c / (1 - Es);
                _a2 = _a1 * _a1;
                xy[x] = _n * _a1 * (1 - _a2 * _t *
                                    (C1 - (8 - _t + 8 * _c) * _a2 * C2));
                xy[y] -= _m0 - _n * _tn * _a2 *
                         (.5 + (5 - _t + 6 * _c) * _a2 * C3);
            }
        }

        /**   {@inheritDoc }     */
        @Override
        protected void ellipticalInverse(double[] xy, double[] lp, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                double ph1 = Proj.InvMlfn(_m0 + xy[y], Es, _en);
                _tn = Math.tan(ph1);
                _t = _tn * _tn;
                _n = Math.sin(ph1);
                _r = 1 / (1 - Es * _n * _n);
                _n = Math.sqrt(_r);
                _r *= (1 - Es) * _n;
                _dd = xy[x] / _n;
                _d2 = _dd * _dd;
                lp[phi] = ph1 - (_n * _tn / _r) * _d2 *
                          (.5 - (1 + 3 * _t) * _d2 * C3);
                lp[lam] = _dd * (1 + _t * _d2 *
                                 (-C4 + (1 + 3 * _t) * _d2 * C5)) / Math.cos(ph1);
            }
        }

        /**   {@inheritDoc }     */
        @Override
        protected void sphericalInverse(double[] xy, double[] lp, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                lp[phi] = Math.asin(Math.sin(_dd = xy[y] + Phi0) * Math.cos(xy[x]));
                lp[lam] = Math.atan2(Math.tan(xy[x]), Math.cos(_dd));
            }
        }

        /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override
        protected void onInit(ProjectionInfo projInfo)
        {
            if (!IsElliptical) return;
            _en = Proj.Enfn(Es);
            _m0 = Proj.Mlfn(Phi0, Math.sin(Phi0), Math.cos(Phi0), _en);
        }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
