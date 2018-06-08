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
public class Aitoff extends Transform {

    /**
     * Creates a new instance of the Aitoff class.
     */
    public Aitoff() {
        this.setProj4Name("aitoff");
        this.setEsriName("Aitoff");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * {@inheritDoc }
     */
    @Override
    protected void onForward(double[] lp, double[] xy, int startIndex, int numPoints) {
        for (int i = startIndex; i < startIndex + numPoints; i++) {
            int phi = i * 2 + PHI;
            int lam = i * 2 + LAMBDA;
            int x = i * 2 + X;
            int y = i * 2 + Y;
            double c;
            double d;
            if ((d = Math.acos(Math.cos(lp[phi]) * Math.cos(c = 0.5 * lp[lam]))) != 0) {
                xy[x] = 2 * d * Math.cos(lp[phi]) * Math.sin(c) * (xy[y] = 1 / Math.sin(d));
                xy[y] *= d * Math.sin(lp[phi]);
            } else {
                xy[x] = xy[y] = 0;
            }
        }
    }

    //</editor-fold>
}
