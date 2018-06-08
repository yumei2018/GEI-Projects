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
public class Loximuthal extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private static final double EPS = 1E-8;
    private double _cosphi1;
    private double _phi1;
    private double _tanphi1;

    //</editor-fold>
    /**
     * Creates a new instance of the Loximuthal class.
     */
    public Loximuthal() {
        setProj4Name("loxim");
        setEsriName("Loximuthal");
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
            xy[y] = lp[phi] - _phi1;
            if (Math.abs(xy[y]) < EPS) {
                xy[x] = lp[lam] * _cosphi1;
            } else {
                xy[x] = FORT_PI + 0.5 * lp[phi];
                if (Math.abs(xy[x]) < EPS || Math.abs(Math.abs(xy[x]) - HALF_PI) < EPS) {
                    xy[x] = 0;
                } else {
                    xy[x] = lp[lam] * xy[y] / Math.log(Math.tan(xy[x]) / _tanphi1);
                }
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
            lp[phi] = xy[y] + _phi1;
            if (Math.abs(xy[y]) < EPS) {
                lp[lam] = xy[x] / _cosphi1;
            } else if (Math.abs(lp[lam] = FORT_PI + 0.5 * lp[phi]) < EPS
                    || Math.abs(Math.abs(lp[lam]) - HALF_PI) < EPS) {
                lp[lam] = 0;
            } else {
                lp[lam] = xy[x] * Math.log(Math.tan(lp[lam]) / _tanphi1) / xy[y];
            }
        }
    }

        /// <summary>
    /// Initializes the transform using the parameters from the specified coordinate system information
    /// </summary>
    /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
    @Override
    protected void onInit(ProjectionInfo projInfo) throws ProjectionException {
        if (projInfo.standardParallel1 != null) {
            _phi1 = projInfo.getPhi1();
            _cosphi1 = Math.cos(_phi1);
            if (_cosphi1 < EPS) {
                throw new ProjectionException(22);
            }
        }
        _tanphi1 = Math.tan(FORT_PI + 0.5 * _phi1);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Properties">
    //</editor-fold>
}
