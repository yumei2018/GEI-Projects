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
public class SwissObliqueMercator extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">
        private static final double EPS = 1E-10;
        private static final int NITER = 6;
        private double _c;
        private double _cosp0;
        private double _hlfE;
        private double _k;
        private double _kR;
        private double _sinp0;


    //</editor-fold>

    /**
     * Creates a new instance of the SwissObliqueMercator class.
     */
    public SwissObliqueMercator(){
              setProj4Name("somerc");
            setEsriName("Swiss_Oblique_Mercator");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
        /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override protected void onInit(ProjectionInfo projInfo)
        {
            double phip0;

            _hlfE = 0.5 * E;
            double cp = Math.cos(Phi0);
            cp *= cp;
            _c = Math.sqrt(1 + Es * cp * cp * ROneEs);
            double sp = Math.sin(Phi0);
            _cosp0 = Math.cos(phip0 = Proj.Aasin(_sinp0 = sp / _c));
            sp *= E;
            _k = Math.log(Math.tan(FORT_PI + 0.5 * phip0)) - _c * (
                                                                      Math.log(Math.tan(FORT_PI + 0.5 * Phi0)) - _hlfE *
                                                                      Math.log((1 + sp) / (1 - sp)));
            _kR = K0 * Math.sqrt(OneEs) / (1 - sp * sp);
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
                double sp = E * Math.sin(lp[phi]);
                double phip = 2 * Math.atan(Math.exp(_c * (
                                                              Math.log(Math.tan(FORT_PI + 0.5 * lp[phi])) -
                                                              _hlfE * Math.log((1 + sp) / (1 - sp)))
                                                     + _k)) - HALF_PI;
                double lamp = _c * lp[lam];
                double cp = Math.cos(phip);
                double phipp = Proj.Aasin(_cosp0 * Math.sin(phip) - _sinp0 * cp * Math.cos(lamp));
                double lampp = Proj.Aasin(cp * Math.sin(lamp) / Math.cos(phipp));
                xy[x] = _kR * lampp;
                xy[y] = _kR * Math.log(Math.tan(FORT_PI + 0.5 * phipp));
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
                int j;

                double phipp = 2 * (Math.atan(Math.exp(xy[y] / _kR)) - FORT_PI);
                double lampp = xy[x] / _kR;
                double cp = Math.cos(phipp);
                double phip = Proj.Aasin(_cosp0 * Math.sin(phipp) + _sinp0 * cp * Math.cos(lampp));
                double lamp = Proj.Aasin(cp * Math.sin(lampp) / Math.cos(phip));
                double con = (_k - Math.log(Math.tan(FORT_PI + 0.5 * phip))) / _c;
                for (j = NITER; j > 0; --j)
                {
                    double esp = E * Math.sin(phip);
                    double delp = (con + Math.log(Math.tan(FORT_PI + 0.5 * phip)) - _hlfE *
                                   Math.log((1 + esp) / (1 - esp))) *
                                  (1 - esp * esp) * Math.cos(phip) * ROneEs;
                    phip -= delp;
                    if (Math.abs(delp) < EPS)
                        break;
                }
                if (j <= 0)
                {
                    lp[lam] = Double.NaN;
                    lp[phi] = Double.NaN;
                    continue;
                    //ProjectionException(20);
                }
                lp[phi] = phip;
                lp[lam] = lamp / _c;
            }
        }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
