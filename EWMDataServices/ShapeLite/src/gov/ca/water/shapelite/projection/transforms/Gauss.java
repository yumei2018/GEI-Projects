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

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Gauss extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">

            private static final int MAX_ITER = 20;
        private static final double DEL_TOL = 1E-14;
        private final double _c;
        private final double _e;
        private final double _k;
        private final double _ratexp;

        public double chi;
        public double rc;

    //</editor-fold>

    /// <summary>
        /// Creates a new instance of Gauss
        /// </summary>
        public Gauss(double e, double phi0)
        {
            double es = e * e;
            _e = e;
            double sphi = Math.sin(phi0);
            double cphi = Math.cos(phi0);
            cphi *= cphi;
            rc = Math.sqrt(1 - es) / (1 - es * sphi * sphi);
            _c = Math.sqrt(1 + es * cphi * cphi / (1 - es));
            chi = Math.asin(sphi / _c);
            _ratexp = .5 * _c * e;
            _k = Math.tan(.5 * chi + Math.PI / 4) / (Math.pow(Math.tan(.5 * phi0 + Math.PI / 4), _c) * Srat(e * sphi, _ratexp));
        }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
                /**   {@inheritDoc }     */
        @Override protected void onForward(double[] elp, double[] result, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                result[phi] = 2 * Math.atan(_k *
                                            Math.pow(Math.tan(.5 * elp[phi] + Math.PI / 4), _c) *
                                            Srat(_e * Math.sin(elp[phi]), _ratexp)) - Math.PI / 2;
                result[lam] = _c * elp[lam];
            }
        }

        /**   {@inheritDoc }     */
        @Override protected void onInverse(double[] slp, double[] result, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int j;
                result[lam] = slp[lam] / _c;
                double alpha = Math.tan(.5 * slp[phi] + Math.PI / 4) / _k;
                double num = Math.pow(alpha, 1 / _c);
                for (j = MAX_ITER; j > 0; --j)
                {
                    double temp = Srat(_e * Math.sin(slp[phi]), -.5 * _e);
                    result[phi] = 2 * Math.atan(num * temp) - Math.PI / 2;
                    if (Math.abs(result[phi] - slp[phi]) < DEL_TOL) break;
                    slp[phi] = result[phi];
                }
                if (j != 0) continue;
                result[lam] = Double.NaN;
                result[phi] = Double.NaN;
            }
        }

        private static double Srat(double esinp, double exp)
        {
            return Math.pow((1 - esinp) / (1 + esinp), exp);
        }


    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
