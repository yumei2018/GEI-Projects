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
public class Mercator extends EllipticalTransform {

    
    /**
     * Creates a new instance of the Mercator class.
     */
    public Mercator() {
        setProj4Name("merc");
        setEsriName("Mercator");
        setEsriAliases(new String[]{"Mercator_1SP", "Mercator_2SP"});
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * {@inheritDoc }
     */
    @Override
    protected void ellipticalForward(double[] lp, double[] xy, int startIndex, int numPoints) {
        for (int i = startIndex; i < startIndex + numPoints; i++) {
            int phi = i * 2 + PHI;
            int lam = i * 2 + LAMBDA;
            int x = i * 2 + X;
            int y = i * 2 + Y;
            if (Math.abs(Math.abs(lp[phi]) - HALF_PI) <= EPS10) {
                xy[x] = Double.NaN;
                xy[y] = Double.NaN;
                continue;
                //throw new ProjectionException(20);
            }
            xy[x] = K0 * lp[lam];
            xy[y] = -K0 * Math.log(Proj.Tsfn(lp[phi], Math.sin(lp[phi]), E));
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void sphericalForward(double[] lp, double[] xy, int startIndex, int numPoints) {
        for (int i = startIndex; i < startIndex + numPoints; i++) {
            int phi = i * 2 + PHI;
            int lam = i * 2 + LAMBDA;
            int x = i * 2 + X;
            int y = i * 2 + Y;
            if (Math.abs(Math.abs(lp[phi]) - HALF_PI) <= EPS10) {
                xy[x] = Double.NaN;
                xy[y] = Double.NaN;
                continue;
                //throw new ProjectionException(20);
            }
            xy[x] = K0 * lp[lam];
            xy[y] = K0 * Math.log(Math.tan(FORT_PI + .5 * lp[phi]));
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void ellipticalInverse(double[] xy, double[] lp, int startIndex, int numPoints) {
        for (int i = startIndex; i < startIndex + numPoints; i++) {
            int phi = i * 2 + PHI;
            int lam = i * 2 + LAMBDA;
            int x = i * 2 + X;
            int y = i * 2 + Y;
            if ((lp[phi] = Proj.Phi2(Math.exp(-xy[y] / K0), E)) == Double.MAX_VALUE) {
                lp[lam] = Double.NaN;
                lp[phi] = Double.NaN;
                continue;
                //throw new ProjectionException(20);
            }
            lp[lam] = xy[x] / K0;
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void sphericalInverse(double[] xy, double[] lp, int startIndex, int numPoints) {
        for (int i = startIndex; i < startIndex + numPoints; i++) {
            int phi = i * 2 + PHI;
            int lam = i * 2 + LAMBDA;
            int x = i * 2 + X;
            int y = i * 2 + Y;
            lp[phi] = HALF_PI - 2 * Math.atan(Math.exp(-xy[y] / K0));
            lp[lam] = xy[x] / K0;
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void onInit(ProjectionInfo projInfo) throws ProjectionException {
        double phits = 0.0;
        boolean isPhits = false;
        if (projInfo.standardParallel1 != null) {
            isPhits = true;
            phits = projInfo.getPhi1();
            if (phits >= HALF_PI) {
                throw new ProjectionException(23);
            }
        }

        if (IsElliptical) { /* ellipsoid */

            if (isPhits) {
                K0 = Proj.Msfn(Math.sin(phits), Math.cos(phits), Es);
            }
        } else { /* sphere */

            if (isPhits) {
                K0 = Math.cos(phits);
            }
        }
    }

    //</editor-fold>

}
