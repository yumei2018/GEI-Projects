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
public class BipolarObliqueConformalConic extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private static final double EPS = 1e-10;
    private static final double ONEEPS = 1.000000001;
    private static final int NITER = 10;
    private static final double LAM_B = -.34894976726250681539;
    private static final double N = .63055844881274687180;
    private static final double F = 1.89724742567461030582;
    private static final double AZAB = .81650043674686363166;
    private static final double AZBA = 1.82261843856185925133;
    private static final double T = 1.27246578267089012270;
    private static final double RHOC = 1.20709121521568721927;
    private static final double C_AZC = .69691523038678375519;
    private static final double S_AZC = .71715351331143607555;
    private static final double C45 = .70710678118654752469;
    private static final double S45 = .70710678118654752410;
    private static final double C20 = .93969262078590838411;
    private static final double S20 = -.34202014332566873287;
    private static final double R110 = 1.91986217719376253360;
    private static final double R104 = 1.81514242207410275904;

    private boolean _noskew;

    //</editor-fold>
    /**
     * Creates a new instance of the BipolarObliqueConfromalConic class.
     */
    public BipolarObliqueConformalConic() {
        this.setProj4Name("bipc");
        this.setEsriName("Bipolar_Oblique_Conformal_Conic");
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
            double tphi, t, al, az, z, av, sdlam;

            double cphi = Math.cos(lp[phi]);
            double sphi = Math.sin(lp[phi]);
            double cdlam = Math.cos(sdlam = LAM_B - lp[lam]);
            sdlam = Math.sin(sdlam);
            if (Math.abs(Math.abs(lp[phi]) - HALF_PI) < EPS10) {
                az = lp[phi] < 0 ? Math.PI : 0;
                tphi = Double.MAX_VALUE;
            } else {
                tphi = sphi / cphi;
                az = Math.atan2(sdlam, C45 * (tphi - cdlam));
            }
            boolean tag = az > AZBA;
            if (tag) {
                cdlam = Math.cos(sdlam = lp[lam] + R110);
                sdlam = Math.sin(sdlam);
                z = S20 * sphi + C20 * cphi * cdlam;
                if (Math.abs(z) > 1) {
                    if (Math.abs(z) > ONEEPS) {
                        xy[x] = Double.NaN;
                        xy[y] = Double.NaN;
                        continue;
                        //throw new ProjectionException(20);
                    }
                    z = z < 0 ? -1 : 1;
                } else {
                    z = Math.acos(z);
                }
                if (tphi != Double.MAX_VALUE) {
                    az = Math.atan2(sdlam, (C20 * tphi - S20 * cdlam));
                }
                av = AZAB;
                xy[y] = RHOC;
            } else {
                z = S45 * (sphi + cphi * cdlam);
                if (Math.abs(z) > 1) {
                    if (Math.abs(z) > ONEEPS) {
                        xy[x] = Double.NaN;
                        xy[y] = Double.NaN;
                        continue;
                        //throw new ProjectionException(20);
                    }
                    z = z < 0 ? -1 : 1;
                } else {
                    z = Math.acos(z);
                }
                av = AZBA;
                xy[y] = -RHOC;
            }
            if (z < 0) {
                xy[x] = Double.NaN;
                xy[y] = Double.NaN;
                continue;
                //throw new ProjectionException(20);
            }
            double r = F * (t = Math.pow(Math.tan(.5 * z), N));
            if ((al = .5 * (R104 - z)) < 0) {
                xy[x] = Double.NaN;
                xy[y] = Double.NaN;
                continue;
                //throw new ProjectionException(20);
            }
            al = (t + Math.pow(al, N)) / T;
            if (Math.abs(al) > 1) {
                if (Math.abs(al) > ONEEPS) {
                    xy[x] = Double.NaN;
                    xy[y] = Double.NaN;
                    continue;
                    //throw new ProjectionException(20);
                }
                al = al < 0 ? -1 : 1;
            } else {
                al = Math.acos(al);
            }
            if (Math.abs(t = N * (av - az)) < al) {
                r /= Math.cos(al + (tag ? t : -t));
            }
            xy[x] = r * Math.sin(t);
            xy[y] += (tag ? -r : r) * Math.cos(t);
            if (_noskew) {
                t = xy[x];
                xy[x] = -xy[x] * C_AZC - xy[y] * S_AZC;
                xy[y] = -xy[y] * C_AZC + t * S_AZC;
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void onInverse(double[] xy, double[] lp, int startIndex, int numPoints) {
        for (int i = startIndex; i < startIndex + numPoints; i++) {
            int phi = i * 2 + PHI;
            int lam = i * 2 + LAMBDA;
            int x = i * 2 + X;
            int y = i * 2 + Y;
            double r, rp;
            double z = 0, az, s, c, av;
            int j;

            if (_noskew) {
                double t = xy[x];
                xy[x] = -xy[x] * C_AZC + xy[y] * S_AZC;
                xy[y] = -xy[y] * C_AZC - t * S_AZC;
            }
            boolean neg = (xy[x] < 0);
            if (neg) {
                xy[y] = RHOC - xy[y];
                s = S20;
                c = C20;
                av = AZAB;
            } else {
                xy[y] += RHOC;
                s = S45;
                c = C45;
                av = AZBA;
            }
            double rl = rp = r = Proj.Hypot(xy[x], xy[y]);
            double fAz = Math.abs(az = Math.atan2(xy[x], xy[y]));
            for (j = NITER; j > 0; --j) {
                z = 2 * Math.atan(Math.pow(r / F, 1 / N));
                double al = Math.acos((Math.pow(Math.tan(.5 * z), N)
                        + Math.pow(Math.tan(.5 * (R104 - z)), N)) / T);
                if (fAz < al) {
                    r = rp * Math.cos(al + (neg ? az : -az));
                }
                if (Math.abs(rl - r) < EPS) {
                    break;
                }
                rl = r;
            }
            if (j == 0) {
                lp[phi] = Double.NaN;
                lp[lam] = Double.NaN;
                continue;
                //throw new ProjectionException(20);
            }
            az = av - az / N;
            lp[phi] = Math.asin(s * Math.cos(z) + c * Math.sin(z) * Math.cos(az));
            lp[lam] = Math.atan2(Math.sin(az), c / Math.tan(z) - s * Math.cos(az));
            if (neg) {
                lp[lam] -= R110;
            } else {
                lp[lam] = LAM_B - lp[lam];
            }
        }
    }

    /// <summary>
    /// Initializes the transform using the parameters from the specified coordinate system information
    /// </summary>
    /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
    @Override
    protected void onInit(ProjectionInfo projInfo) {
        if (projInfo.bns != null) {
            if (projInfo.bns != 0) {
                _noskew = true;
            }
        }
    }

    //</editor-fold>
}
