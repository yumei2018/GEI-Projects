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
public class ObliqueStereographicAlternative extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">

        private double _cosc0;
        private Gauss _gauss;
        private double _phic0;
        private double _r2;
        private double _sinc0;

    //</editor-fold>

    /**
     * Creates a new instance of the ObliqueStereographicAlternative class.
     */
    public ObliqueStereographicAlternative(){
              setProj4Name("sterea");
            setEsriName("Oblique_Stereographic_Alternative");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
       /**   {@inheritDoc }     */
        @Override protected void onForward(double[] lp, double[] xy, int startIndex, int numPoints)
        {
            System.arraycopy(lp, startIndex * 2, xy, startIndex * 2, numPoints * 2);
            _gauss.forward(xy, startIndex, numPoints);
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                // Grab from xy instead of lp because of the Gauss transform
                double phi = xy[i * 2 + PHI];
                double lam = xy[i * 2 + LAMBDA];
                int x = i * 2 + X;
                int y = i * 2 + Y;
                double sinc = Math.sin(phi);
                double cosc = Math.cos(phi);
                double cosl = Math.cos(lam);
                double k = K0 * _r2 / (1 + _sinc0 * sinc + _cosc0 * cosc * cosl);
                xy[x] = k * cosc * Math.sin(lam);
                xy[y] = k * (_cosc0 * sinc - _sinc0 * cosc * cosl);
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
                double rho;

                xy[x] /= K0;
                xy[y] /= K0;
                if ((rho = Proj.Hypot(xy[x], xy[y])) > 0)
                {
                    double c = 2 * Math.atan2(rho, _r2);
                    double sinc = Math.sin(c);
                    double cosc = Math.cos(c);
                    lp[phi] = Math.asin(cosc * _sinc0 + xy[y] * sinc * _cosc0 / rho);
                    lp[lam] = Math.atan2(xy[x] * sinc, rho * _cosc0 * cosc -
                                                       xy[y] * _sinc0 * sinc);
                }
                else
                {
                    lp[phi] = _phic0;
                    lp[lam] = 0;
                }
            }
            _gauss.inverse(lp, startIndex, numPoints);
        }

        /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override protected void onInit(ProjectionInfo projInfo)
        {
            double r = 0;
            _gauss = new Gauss(E, Phi0);
            _phic0 = _gauss.chi;
            r = _gauss.rc;
            _sinc0 = Math.sin(_phic0);
            _cosc0 = Math.cos(_phic0);
            _r2 = 2 * r;
        }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
