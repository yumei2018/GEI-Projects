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
public class VanderGrinten1 extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">

        private static final double TOL = 1E-10;
        private static final double THIRD = .33333333333333333333;
        private static final double C227 = .07407407407407407407;
        private static final double PI43 = 4.18879020478639098458;
        private static final double PISQ = 9.86960440108935861869;
        private static final double TPISQ = 19.73920880217871723738;
        private static final double HPISQ = 4.93480220054467930934;

    //</editor-fold>

    /**
     * Creates a new instance of the VanderGrinten1 class.
     */
    public VanderGrinten1(){
              setProj4Name("vandg");
            setEsriName("Van_der_Grinten_I");
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
                double p2 = Math.abs(lp[phi] / HALF_PI);
                if ((p2 - TOL) > 1)
                {
                    xy[x] = Double.NaN;
                    xy[y] = Double.NaN;
                    continue;
                    //ProjectionException(20);
                }
                if (p2 > 1)
                    p2 = 1;
                if (Math.abs(lp[phi]) <= TOL)
                {
                    xy[x] = lp[lam];
                    xy[y] = 0;
                }
                else if (Math.abs(lp[lam]) <= TOL || Math.abs(p2 - 1) < TOL)
                {
                    xy[x] = 0;
                    xy[y] = Math.PI * Math.tan(.5 * Math.asin(p2));
                    if (lp[phi] < 0) xy[y] = -xy[y];
                }
                else
                {
                    double al = .5 * Math.abs(Math.PI / lp[lam] - lp[lam] / Math.PI);
                    double al2 = al * al;
                    double g = Math.sqrt(1 - p2 * p2);
                    g = g / (p2 + g - 1);
                    double g2 = g * g;
                    p2 = g * (2 / p2 - 1);
                    p2 = p2 * p2;
                    xy[x] = g - p2;
                    g = p2 + al2;
                    xy[x] = Math.PI * (al * xy[x] + Math.sqrt(al2 * xy[x] * xy[x] - g * (g2 - p2))) / g;
                    if (lp[lam] < 0) xy[x] = -xy[x];
                    xy[y] = Math.abs(xy[x] / Math.PI);
                    xy[y] = 1 - xy[y] * (xy[y] + 2 * al);
                    if (xy[y] < -TOL)
                    {
                        xy[x] = Double.NaN;
                        xy[y] = Double.NaN;
                        continue;
                        //ProjectionException(20);
                    }
                    if (xy[y] < 0) xy[y] = 0;
                    else xy[y] = Math.sqrt(xy[y]) * (lp[phi] < 0 ? -Math.PI : Math.PI);
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
                double t, ay;

                double x2 = xy[x] * xy[x];
                if ((ay = Math.abs(xy[y])) < TOL)
                {
                    lp[phi] = 0;
                    t = x2 * x2 + TPISQ * (x2 + HPISQ);
                    lp[lam] = Math.abs(xy[x]) <= TOL ? 0 : .5 * (x2 - PISQ + Math.sqrt(t)) / xy[x];
                    return;
                }
                double y2 = xy[y] * xy[y];
                double r = x2 + y2;
                double r2 = r * r;
                double c1 = -Math.PI * ay * (r + PISQ);
                double c3 = r2 + TWO_PI * (ay * r + Math.PI * (y2 + Math.PI * (ay + HALF_PI)));
                double c2 = c1 + PISQ * (r - 3 * y2);
                double c0 = Math.PI * ay;
                c2 /= c3;
                double al = c1 / c3 - THIRD * c2 * c2;
                double m = 2 * Math.sqrt(-THIRD * al);
                double d = C227 * c2 * c2 * c2 + (c0 * c0 - THIRD * c2 * c1) / c3;
                if (((t = Math.abs(d = 3 * d / (al * m))) - TOL) > 1)
                {
                    xy[x] = Double.NaN;
                    xy[y] = Double.NaN;
                    continue;
                    //ProjectionException(20);
                }
                d = t > 1 ? (d > 0 ? 0 : Math.PI) : Math.acos(d);
                lp[phi] = Math.PI * (m * Math.cos(d * THIRD + PI43) - THIRD * c2);
                if (xy[y] < 0) lp[phi] = -lp[phi];
                t = r2 + PISQ * (x2 - y2 + HPISQ);
                lp[lam] = Math.abs(xy[x]) <= TOL ? 0 : .5 * (r - PISQ + (t <= 0 ? 0 : Math.sqrt(t))) / xy[x];
            }
        }


    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
