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
import gov.ca.water.shapelite.projection.transforms.Proj.Zpolyd1Result;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class NewZealandMapGrid extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">
        private static final double SEC5_TO_RAD = 0.4848136811095359935899141023;
        private static final double RAD_TO_SEC5 = 2.062648062470963551564733573;
        private static final int NBF = 5;
        private static final int NTPSI = 9;
        private static final int NTPHI = 8;

        private final double[][] _bf;
        private final double[] _tphi;
        private final double[] _tpsi;


    //</editor-fold>

    /**
     * Creates a new instance of the NewZealandMapGrid class.
     */
    public NewZealandMapGrid(){
              setProj4Name("nzmg");
            setEsriName("New_Zealand_Map_Grid");
            _bf = new double[6][];
            _bf[0] = new double[] { .7557853228, 0.0 };
            _bf[1] = new double[] { .249204646, .003371507 };
            _bf[2] = new double[] { -.001541739, .041058560 };
            _bf[3] = new double[] { -.10162907, .01727609 };
            _bf[4] = new double[] { -.26623489, -.36249218 };
            _bf[5] = new double[] { -.6870983, -1.1651967 };

            _tphi = new double[] { 1.5627014243, .5185406398, -.03333098, -.1052906, -.0368594, .007317, .01220, .00394, -.0013 };
            _tpsi = new double[]
                        {
                            .6399175073, -.1358797613, .063294409, -.02526853, .0117879, -.0055161, .0026906, -.001333,
                            .00067, -.00034
                        };
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
        /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override protected void onInit(ProjectionInfo projInfo)
        {
            Ra = 1 / (A = 6378388.0);
            Lam0 = DEG_TO_RAD * 173;
            Phi0 = DEG_TO_RAD * -41;
            X0 = 2510000;
            Y0 = 6023150;
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

                double[] p = new double[2];

                lp[phi] = (lp[phi] - Phi0) * RAD_TO_SEC5;
                p[R] = _tpsi[NTPSI];
                for (int j = NTPSI - 1; j >= 0; j--)
                {
                    p[R] = _tpsi[j] + lp[phi] * p[R];
                }
                p[R] *= lp[phi];
                p[I] = lp[lam];
                p = Proj.Zpoly1(p, _bf, NBF);
                xy[x] = p[I];
                xy[y] = p[R];
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
                int nn;
                double[] p = new double[2];
                double[] dp = new double[2];
                p[R] = xy[y];
                p[I] = xy[x];
                for (nn = 20; nn > 0; --nn)
                {
                    double[] fp;
                    Zpolyd1Result res = Proj.Zpolyd1(p, _bf, NBF);
                    double[] f= res.result;
                    fp = res.der;
                    f[R] -= xy[y];
                    f[I] -= xy[x];
                    double den = fp[R] * fp[R] + fp[I] * fp[I];
                    p[R] += dp[R] = -(f[R] * fp[R] + f[I] * fp[I]) / den;
                    p[I] += dp[I] = -(f[I] * fp[R] - f[R] * fp[I]) / den;
                    if ((Math.abs(dp[R]) + Math.abs(dp[I])) <= EPS10)
                        break;
                }
                if (nn > 0)
                {
                    lp[lam] = p[I];
                    lp[phi] = _tphi[NTPHI];
                    for (int j = NTPHI - 1; j > 0; j++)
                    {
                        lp[phi] = _tphi[j] + p[R] * lp[phi];
                    }
                    lp[phi] = Phi0 + p[R] * lp[phi] * SEC5_TO_RAD;
                }
                else
                {
                    lp[lam] = lp[phi] = Double.MAX_VALUE;
                }
            }
        }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
