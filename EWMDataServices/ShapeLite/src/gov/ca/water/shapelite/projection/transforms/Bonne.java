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
public class Bonne extends EllipticalTransform {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private double _am1;
    private double _cphi1;
    private double[] _en;
    private double _m1;
    private double _phi1;

    //</editor-fold>
    /**
     * Creates a new instance of the Bonne class.
     */
    public Bonne() {
        this.setEsriName("Bonne");
        this.setProj4Name("bonne");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
    @Override
    protected void ellipticalForward(double[] lp, double[] xy, int startIndex, int numPoints) {
        for (int i = startIndex; i < startIndex + numPoints; i++) {
            int phi = i * 2 + PHI;
            int lam = i * 2 + LAMBDA;
            int x = i * 2 + X;
            int y = i * 2 + Y;
            double e, c;
            double rh = _am1 + _m1 - Proj.Mlfn(lp[phi], e = Math.sin(lp[phi]), c = Math.cos(lp[phi]), _en);
            e = c * lp[lam] / (rh * Math.sqrt(1 - Es * e * e));
            xy[x] = rh * Math.sin(e);
            xy[y] = _am1 - rh * Math.cos(e);
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
            double rh = _cphi1 + _phi1 - lp[phi];
            if (Math.abs(rh) > EPS10) {
                double e;
                xy[x] = rh * Math.sin(e = lp[lam] * Math.cos(lp[phi]) / rh);
                xy[y] = _cphi1 - rh * Math.cos(e);
            } else {
                xy[x] = xy[y] = 0;
            }
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
            double rh = Proj.Hypot(xy[x], xy[y] = _cphi1 - xy[y]);
            lp[phi] = _cphi1 + _phi1 - rh;
            if (Math.abs(lp[phi]) > HALF_PI) {
                lp[lam] = Double.NaN;
                lp[phi] = Double.NaN;
                continue;
                //throw new ProjectionException(20);
            }
            if (Math.abs(Math.abs(lp[phi]) - HALF_PI) <= EPS10) {
                lp[lam] = 0;
            } else {
                lp[lam] = rh * Math.atan2(xy[x], xy[y]) / Math.cos(lp[phi]);
            }
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
            double s;

            double rh = Proj.Hypot(xy[x], xy[y] = _am1 - xy[y]);
            lp[phi] = Proj.InvMlfn(_am1 + _m1 - rh, Es, _en);
            if ((s = Math.abs(lp[phi])) < HALF_PI) {
                s = Math.sin(lp[phi]);
                lp[lam] = rh * Math.atan2(xy[x], xy[y])
                        * Math.sqrt(1 - Es * s * s) / Math.cos(lp[phi]);
            } else if (Math.abs(s - HALF_PI) <= EPS10) {
                lp[lam] = 0;
            } else {
                lp[lam] = Double.NaN;
                lp[phi] = Double.NaN;
                continue;
                //throw new ProjectionException(20);
            }
        }
    }

        /// <summary>
    /// Initializes the transform using the parameters from the specified coordinate system information
    /// </summary>
    /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
    @Override
    protected void onInit(ProjectionInfo projInfo) throws ProjectionException {
        _phi1 = projInfo.getPhi1();
        if (Math.abs(_phi1) < EPS10) {
            throw new ProjectionException(-23);
        }
        if (Es > 0) {
            _en = Proj.Enfn(Es);
            double c;
            _m1 = Proj.Mlfn(_phi1, _am1 = Math.sin(_phi1),
                    c = Math.cos(_phi1), _en);
            _am1 = c / (Math.sqrt(1 - Es * _am1 * _am1) * _am1);
        } else {
            if (Math.abs(_phi1) + EPS10 >= HALF_PI) {
                _cphi1 = 0;
            } else {
                _cphi1 = 1 / Math.tan(_phi1);
            }
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Properties">
    //</editor-fold>
}
