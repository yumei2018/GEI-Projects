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
public class Polyconic extends EllipticalTransform {

    //<editor-fold defaultstate="collapsed" desc="Fields">

        private static final double TOL = 1E-10;
        private static final double CONV = 1E-10;
        private static final int N_ITER = 10;
        private static final int ITER = 20;
        private static final double ITOL = 1E-12;
        private double[] _en;
        private double _ml0;

    //</editor-fold>

    /**
     * Creates a new instance of the Polyconic class.
     */
    public Polyconic(){
              setProj4Name("poly");
            setEsriName("Polyconic");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
       /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override protected void onInit(ProjectionInfo projInfo)
        {
            if (IsElliptical)
            {
                _en = Proj.Enfn(Es);
                _ml0 = Proj.Mlfn(Phi0, Math.sin(Phi0), Math.cos(Phi0), _en);
            }
            else
            {
                _ml0 = -Phi0;
            }
        }

        /**   {@inheritDoc }     */
        @Override protected void ellipticalForward(double[] lp, double[] xy, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                if (Math.abs(lp[phi]) <= TOL)
                {
                    xy[x] = lp[lam];
                    xy[y] = -_ml0;
                }
                else
                {
                    double sp = Math.sin(lp[phi]);
                    double cp;
                    double ms = Math.abs(cp = Math.cos(lp[phi])) > TOL ? Proj.Msfn(sp, cp, Es) / sp : 0;
                    xy[x] = ms * Math.sin(lp[lam] *= sp);
                    xy[y] = (Proj.Mlfn(lp[phi], sp, cp, _en) - _ml0) + ms * (1 - Math.cos(lp[lam]));
                }
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
                if (Math.abs(lp[phi]) <= TOL)
                {
                    xy[x] = lp[lam];
                    xy[y] = _ml0;
                }
                else
                {
                    double cot = 1 / Math.tan(lp[phi]);
                    double e;
                    xy[x] = Math.sin(e = lp[lam] * Math.sin(lp[phi])) * cot;
                    xy[y] = lp[phi] - Phi0 + cot * (1 - Math.cos(e));
                }
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
                xy[y] += _ml0;
                if (Math.abs(xy[y]) <= TOL)
                {
                    lp[lam] = xy[x];
                    lp[phi] = 0;
                }
                else
                {
                    double c;
                    int j;
                    double r = xy[y] * xy[y] + xy[x] * xy[x];
                    for (lp[phi] = xy[y], j = ITER; j > 0; --j)
                    {
                        double sp = Math.sin(lp[phi]);
                        double cp;
                        double s2Ph = sp * (cp = Math.cos(lp[phi]));
                        if (Math.abs(cp) < ITOL)
                        {
                            lp[lam] = Double.NaN;
                            lp[phi] = Double.NaN;
                            continue;
                            //ProjectionException(20);
                        }
                        double mlp;
                        c = sp * (mlp = Math.sqrt(1 - Es * sp * sp)) / cp;
                        double ml = Proj.Mlfn(lp[phi], sp, cp, _en);
                        double mlb = ml * ml + r;
                        mlp = OneEs / (mlp * mlp * mlp);
                        double dPhi;
                        lp[phi] += (dPhi =
                                    (ml + ml + c * mlb - 2 * xy[y] * (c * ml + 1)) / (Es * s2Ph * (mlb - 2 * xy[y] * ml) / c +
                                                                                      2 * (xy[y] - ml) * (c * mlp - 1 / s2Ph) - mlp - mlp));
                        if (Math.abs(dPhi) <= ITOL)
                            break;
                    }
                    if (j == 0)
                    {
                        lp[lam] = Double.NaN;
                        lp[phi] = Double.NaN;
                        continue;
                        //ProjectionException(20);
                    }
                    c = Math.sin(lp[phi]);
                    lp[lam] = Math.asin(xy[x] * Math.tan(lp[phi]) * Math.sqrt(1 - Es * c * c)) / Math.sin(lp[phi]);
                }
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
                if (Math.abs(xy[y] = Phi0 + xy[y]) <= TOL)
                {
                    lp[lam] = xy[x];
                    lp[phi] = 0;
                }
                else
                {
                    lp[phi] = xy[y];
                    double b = xy[x] * xy[x] + xy[y] * xy[y];
                    int j = N_ITER;
                    double dphi;
                    do
                    {
                        double tp = Math.tan(lp[phi]);
                        lp[phi] -= (dphi = (xy[y] * (lp[phi] * tp + 1) - lp[phi] -
                                            .5 * (lp[phi] * lp[phi] + b) * tp) /
                                           ((lp[phi] - xy[y]) / tp - 1));
                    } while (Math.abs(dphi) > CONV && --j > 0);
                    if (j == 0)
                    {
                        lp[lam] = Double.NaN;
                        lp[phi] = Double.NaN;
                        continue;
                        //ProjectionException(20);
                    }
                    lp[lam] = Math.asin(xy[x] * Math.tan(lp[phi])) / Math.sin(lp[phi]);
                }
            }
        }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
