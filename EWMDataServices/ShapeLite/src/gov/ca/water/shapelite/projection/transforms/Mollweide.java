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
public class Mollweide extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">

        private static final int MAX_ITER = 10;
        private static final double LOOP_TOL = 1E-7;
        protected double Cx;
        protected double Cy;
        protected double Cp;

    //</editor-fold>

    /**
     * Creates a new instance of the Mollweide class.
     */
    public Mollweide(){
              setProj4Name("moll");
            setEsriName("Mollweide");
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
                int j;

                double k = Cp * Math.sin(lp[phi]);
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
                xy[x] = Cx * lp[lam] * Math.cos(lp[phi]);
                xy[y] = Cy * Math.sin(lp[phi]);
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
                lp[phi] = Proj.Aasin(xy[y] / Cy);
                lp[lam] = xy[x] / (Cx * Math.cos(lp[phi]));
                lp[phi] += lp[phi];
                lp[phi] = Proj.Aasin((lp[phi] + Math.sin(lp[phi])) / Cp);
            }
        }

        /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override protected void onInit(ProjectionInfo projInfo)
        {
            Setup(HALF_PI);
        }

        /// <summary>
        /// Finalizes the setup based on the provided P paraemter
        /// </summary>
        /// <param name="p"></param>
        protected void Setup(double p)
        {
            double p2 = p + p;
            Es = 0;
            double sp = Math.sin(p);
            double r = Math.sqrt(TWO_PI * sp / (p2 + Math.sin(p2)));
            Cx = 2 * r / Math.PI;
            Cy = r / sp;
            Cp = p2 + Math.sin(p2);
        }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
