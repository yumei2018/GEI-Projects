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
public class Winkel2 extends Transform{

    //<editor-fold defaultstate="collapsed" desc="Fields">

        private static final int MAX_ITER = 10;
        private static final double LOOP_TOL = 1e-7;
        private static final double TWO_D_PI = 0.636619772367581343;
        private double _cosphi1;

    //</editor-fold>

    /**
     * Creates a new instance of the Winkel2 class.
     */
    public Winkel2(){
              setProj4Name("wink2");
            setEsriName("Winkel_II");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
       /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override protected void onInit(ProjectionInfo projInfo)
        {
            _cosphi1 = Math.cos(projInfo.getPhi1());
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

                int j;

                xy[y] = lp[phi] * TWO_D_PI;
                double k = Math.PI * Math.sin(lp[phi]);
                lp[phi] *= 1.8;
                for (j = MAX_ITER; j > 0; --j)
                {
                    double v;
                    lp[phi] -= v = (lp[phi] + Math.sin(lp[phi]) - k) /
                                   (1 + Math.cos(lp[phi]));
                    if (Math.abs(v) < LOOP_TOL)
                        break;
                }
                if (j == 0)
                    lp[phi] = (lp[phi] < 0) ? -HALF_PI : HALF_PI;
                else
                    lp[phi] *= 0.5;
                xy[x] = 0.5 * lp[lam] * (Math.cos(lp[phi]) + _cosphi1);
                xy[y] = FORT_PI * (Math.sin(lp[phi]) + xy[y]);
            }
        }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
