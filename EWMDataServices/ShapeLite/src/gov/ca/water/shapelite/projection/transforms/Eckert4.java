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
public class Eckert4 extends Transform{

    //<editor-fold defaultstate="collapsed" desc="Fields">

        private static final double CX = .42223820031577120149;
        private static final double CY = 1.32650042817700232218;
        private static final double CP = 3.57079632679489661922;
        private static final double EPS = 1E-7;
        private static final int NITER = 6;

    //</editor-fold>

    /**
     * Creates a new instance of the Eckert4 class.
     */
    public Eckert4(){
   setProj4Name("eck4");
            setEsriName("Eckert_IV");
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

                double p = CP * Math.sin(lp[phi]);
                double v = lp[phi] * lp[phi];
                lp[phi] *= 0.895168 + v * (0.0218849 + v * 0.00826809);
                for (j = NITER; j > 0; --j)
                {
                    double c = Math.cos(lp[phi]);
                    double s = Math.sin(lp[phi]);
                    lp[phi] -= v = (lp[phi] + s * (c + 2) - p) /
                                   (1 + c * (c + 2) - s * s);
                    if (Math.abs(v) < EPS)
                        break;
                }
                if (j == 0)
                {
                    xy[x] = CX * lp[lam];
                    xy[y] = lp[phi] < 0 ? -CY : CY;
                }
                else
                {
                    xy[x] = CX * lp[lam] * (1 + Math.cos(lp[phi]));
                    xy[y] = CY * Math.sin(lp[phi]);
                }
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
                double c;
                lp[phi] = Proj.Aasin(xy[y] / CY);
                lp[lam] = xy[x] / (CX * (1 + (c = Math.cos(lp[phi]))));
                lp[phi] = Proj.Aasin((lp[phi] + Math.sin(lp[phi]) * (c + 2)) / CP);
            }
        }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
