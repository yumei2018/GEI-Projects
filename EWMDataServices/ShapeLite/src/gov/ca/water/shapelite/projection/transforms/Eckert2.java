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
public class Eckert2 extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private static final double FXC = 0.46065886596178063902;
    private static final double FYC = 1.44720250911653531871;
    private static final double C13 = 0.33333333333333333333;
    private static final double ONEEPS = 1.0000001;

    //</editor-fold>
    /**
     * Creates a new instance of the Eckert2 class.
     */
    public Eckert2() {
        setProj4Name("eck2");
        setEsriName("Eckert_II");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**   {@inheritDoc }     */
    @Override
    protected void onForward(double[] lp, double[] xy, int startIndex, int numPoints) {
        for (int i = startIndex; i < startIndex + numPoints; i++) {
            int phi = i * 2 + PHI;
            int lam = i * 2 + LAMBDA;
            int x = i * 2 + X;
            int y = i * 2 + Y;
            xy[x] = FXC * lp[lam] * (xy[y] = Math.sqrt(4 - 3 * Math.sin(Math.abs(lp[phi]))));
            xy[y] = FYC * (2 - xy[y]);
            if (lp[phi] < 0) {
                xy[y] = -xy[y];
            }
        }
    }

    /**   {@inheritDoc }     */
    @Override
    protected void onInverse(double[] xy, double[] lp, int startIndex, int numPoints) {
        for (int i = startIndex; i < startIndex + numPoints; i++) {
            int phi = i * 2 + PHI;
            int lam = i * 2 + LAMBDA;
            int x = i * 2 + X;
            int y = i * 2 + Y;
            lp[lam] = xy[x] / (FXC * (lp[phi] = 2 - Math.abs(xy[y]) / FYC));
            lp[phi] = (4 - lp[phi] * lp[phi]) * C13;
            if (Math.abs(lp[phi]) >= 1) {
                if (Math.abs(lp[phi]) > ONEEPS) {
                    lp[lam] = Double.NaN;
                    lp[phi] = Double.NaN;
                    continue;
                    //throw new ProjectionException(20);
                }
                lp[phi] = lp[phi] < 0 ? -HALF_PI : HALF_PI;
            } else {
                lp[phi] = Math.asin(lp[phi]);
            }
            if (xy[y] < 0) {
                lp[phi] = -lp[phi];
            }
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Properties">
    //</editor-fold>
}
