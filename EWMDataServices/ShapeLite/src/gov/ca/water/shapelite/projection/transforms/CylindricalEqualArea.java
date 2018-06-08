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
public class CylindricalEqualArea extends EllipticalTransform {

    //<editor-fold defaultstate="collapsed" desc="Fields">

        private static final double EPS = 1e-10;
        private double[] _apa;
        private double _qp;

    //</editor-fold>

    /**
     * Creates a new instance of the CylindricalEqualArea class.
     */
    public CylindricalEqualArea(){
  setEsriName("Cylindrical_Equal_Area");
            setProj4Name("cea");
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
                xy[x] = K0 * lp[lam];
                xy[y] = .5 * Proj.Qsfn(Math.sin(lp[phi]), E, OneEs) / K0;
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
                xy[x] = K0 * lp[lam];
                xy[y] = Math.sin(lp[phi]) / K0;
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
                lp[phi] = Proj.AuthLat(Math.asin(2 * xy[y] * K0 / _qp), _apa);
                lp[lam] = xy[x] / K0;
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
                double t;

                if ((t = Math.abs(xy[y] *= K0)) - EPS <= 1)
                {
                    if (t >= 1)
                        lp[phi] = xy[y] < 0 ? -HALF_PI : HALF_PI;
                    else
                        lp[phi] = Math.asin(xy[y]);
                    lp[lam] = xy[x] / K0;
                }
                else
                {
                    lp[lam] = Double.NaN;
                    lp[phi] = Double.NaN;
                    continue;
                    //throw new ProjectionException(20);
                }
            }
        }

        /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override protected void onInit(ProjectionInfo projInfo) throws ProjectionException
        {
            double t;
            if (projInfo.standardParallel1 != null)
                t = projInfo.standardParallel1 * DEG_TO_RAD;
            else
                t = projInfo.lat_ts * DEG_TO_RAD;
            if ((K0 = Math.cos(t)) < 0) throw new ProjectionException(-24);
            if (!IsElliptical) return;
            t = Math.sin(t);
            K0 /= Math.sqrt(1 - Es * t * t);
            E = Math.sqrt(Es);
            _apa = Proj.Authset(Es);
            _qp = Proj.Qsfn(1, E, OneEs);
        }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
