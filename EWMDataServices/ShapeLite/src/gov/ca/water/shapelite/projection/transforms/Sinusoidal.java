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
public class Sinusoidal extends EllipticalTransform {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private double _cX;
    private double _cY;
    private double[] _en;

    protected double M;
    protected double N;

    //</editor-fold>
    /**
     * Creates a new instance of the Sinusoidal class.
     */
    public Sinusoidal() {
        setProj4Name("sinu");
        setEsriName("Sinusoidal");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**   {@inheritDoc }     */
    @Override
    protected void sphericalForward(double[] lp, double[] xy, int startIndex, int numPoints) {
        for (int i = startIndex; i < startIndex + numPoints; i++) {
            int phi = i * 2 + PHI;
            int lam = i * 2 + LAMBDA;
            int x = i * 2 + X;
            int y = i * 2 + Y;
            double s, c;
            xy[x] = Proj.Mlfn(lp[phi], s = Math.sin(lp[phi]), c = Math.cos(lp[phi]), _en);
            xy[y] = lp[lam] * c / Math.sqrt(1 - Es * s * s);
        }
    }

    /**   {@inheritDoc }     */
    @Override
    protected void sphericalInverse(double[] xy, double[] lp, int startIndex, int numPoints) {
        for (int i = startIndex; i < startIndex + numPoints; i++) {
            int phi = i * 2 + PHI;
            int lam = i * 2 + LAMBDA;
            int x = i * 2 + X;
            int y = i * 2 + Y;
            xy[y] /= _cY;
            lp[phi] = M > 0
                    ? Proj.Aasin((M * xy[y] + Math.sin(xy[y])) / N)
                    : (N != 1 ? Proj.Aasin(Math.sin(xy[y]) / N) : xy[y]);
            lp[lam] = xy[x] / (_cX * (M + Math.cos(xy[y])));
        }
    }

    /**   {@inheritDoc }     */
    @Override
    protected void ellipticalForward(double[] lp, double[] xy, int startIndex, int numPoints) {
        for (int i = startIndex; i < startIndex + numPoints; i++) {
            int phi = i * 2 + PHI;
            int lam = i * 2 + LAMBDA;
            int x = i * 2 + X;
            int y = i * 2 + Y;
            double s, c;
            xy[y] = Proj.Mlfn(lp[phi], s = Math.sin(lp[phi]), c = Math.cos(lp[phi]), _en);
            xy[x] = lp[lam] * c / Math.sqrt(1 - Es * s * s);
        }
    }

    /**   {@inheritDoc }     */
    @Override
    protected void ellipticalInverse(double[] xy, double[] lp, int startIndex, int numPoints) {
        for (int i = startIndex; i < startIndex + numPoints; i++) {
            int phi = i * 2 + PHI;
            int lam = i * 2 + LAMBDA;
            int x = i * 2 + X;
            int y = i * 2 + Y;
            double s;

            if ((s = Math.abs(lp[phi] = Proj.InvMlfn(xy[y], Es, _en))) < HALF_PI) {
                s = Math.sin(lp[phi]);
                lp[lam] = xy[x] * Math.sqrt(1 - Es * s * s) / Math.cos(lp[phi]);
            } else if ((s - EPS10) < HALF_PI) {
                lp[lam] = 0;
            } else {
                lp[lam] = Double.NaN;
                lp[phi] = Double.NaN;
                continue;
                //ProjectionException(20);
            }
        }
    }

        /// <summary>
    /// Handles the original configuration of sinusoidal transforms
    /// </summary>
    protected void Setup() {
        _cX = (_cY = Math.sqrt((M + 1) / N)) / (M + 1);
    }

        /// <summary>
    /// Initializes the transform using the parameters from the specified coordinate system information
    /// </summary>
    /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
    @Override
    protected void onInit(ProjectionInfo projInfo) throws ProjectionException {
        _en = Proj.Enfn(Es);
        if (IsElliptical == false) {
            N = 1;
            M = 0;
            Setup();
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Properties">
    //</editor-fold>
}
