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
public class HotineObliqueMercatorAzimuthNaturalOrigin extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private static final double TOLERANCE = 1E-7;
    private double _al;    // this is the documented 'A' divided by 'a' (i.e. 'a' is not included since DS de-scales by a)
    private double _alpha; // azimuth of initial line - AKA alpha-sub-c (an input)
    private double _bl;    // the docuemented 'B'
    private double _cosgam;
    private double _cosrot;
    private double _el;    // from EPSG doc: 'H', from Snyder: 'E'
    private boolean _ellips; // false if sphere is being used instead of an ellipsoid
    private double _gamma; // AKA gamma-sub-zero; calculated internally
    private double _lamc;  // longitude of projection center - AKA lambda-sub-c (an input)
    private boolean _rot;    // true if alpha=90 deg; Swiss & Hung
    private double _singam;
    private double _sinrot;

    //</editor-fold>
    /**
     * Creates a new instance of the HotineObliqueMercatorAzimuthNaturalOrigin class.
     */
    public HotineObliqueMercatorAzimuthNaturalOrigin() {
        setProj4Name("omerc");
        setEsriName("Hotine_Oblique_Mercator_Azimuth_Natural_Origin");  // EPSG 9812 (Hotine Oblique Mercator Variant A)
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
            double ul, us;
            double vl = Math.sin(_bl * lp[lam]);
            if (Math.abs(Math.abs(lp[phi]) - Math.PI / 2) <= EPS10) {
                ul = lp[phi] < 0 ? -_singam : _singam;
                us = _al * lp[phi] / _bl;
            } else {
                double q = _el / (_ellips ? Math.pow(Proj.Tsfn(lp[phi], Math.sin(lp[phi]), E), _bl) : Tsfn0(lp[phi]));
                double s = .5 * (q - 1 / q);
                ul = 2.0 * (s * _singam - vl * _cosgam) / (q + 1.0 / q);
                double con = Math.cos(_bl * lp[lam]);
                if (Math.abs(con) >= TOLERANCE) {
                    us = _al * Math.atan((s * _cosgam + vl * _singam) / con) / _bl;
                    if (con < 0) {
                        us += Math.PI * _al / _bl;
                    }
                } else {
                    us = _al * _bl * lp[lam];
                }
            }
            if (Math.abs(Math.abs(ul) - 1.0) <= EPS10) {
                xy[x] = Double.NaN;
                xy[y] = Double.NaN;
                continue;
                //ProjectionException(20);
            }
            double vs = .5 * _al * Math.log((1 - ul) / (1 + ul)) / _bl;
            if (!_rot) {
                xy[x] = us;
                xy[y] = vs;
            } else {
                xy[x] = vs * _cosrot + us * _sinrot;
                xy[y] = us * _cosrot - vs * _sinrot;
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
            double us, vs;
            if (!_rot) {
                us = xy[x];
                vs = xy[y];
            } else {
                vs = xy[x] * _cosrot - xy[y] * _sinrot;
                us = xy[y] * _cosrot + xy[x] * _sinrot;
            }
            double q = Math.exp(-_bl * vs / _al);
            double s = .5 * (q - 1 / q);
            double vl = Math.sin(_bl * us / _al);
            double ul = 2 * (vl * _cosgam + s * _singam) / (q + 1 / q);
            if (Math.abs(Math.abs(ul) - 1) < EPS10) {
                lp[lam] = 0;
                lp[phi] = ul < 0 ? -HALF_PI : HALF_PI;
            } else {
                lp[phi] = _el / Math.sqrt((1 + ul) / (1 - ul));
                if (_ellips) {
                    if ((lp[phi] = Proj.Phi2(Math.pow(lp[phi], 1 / _bl), E)) == Double.MAX_VALUE) {
                        lp[lam] = Double.NaN;
                        lp[phi] = Double.NaN;
                        continue;
                        //ProjectionException(20);
                    }
                } else {
                    lp[phi] = HALF_PI - 2 * Math.atan(lp[phi]);
                }
                lp[lam] = -Math.atan2((s * _cosgam - vl * _singam), Math.cos(_bl * us / _al)) / _bl;
            }
        }
    }

        /// <summary>
    /// Initializes the transform using the parameters from the specified coordinate system information
    /// </summary>
    /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
    @Override
    protected void onInit(ProjectionInfo projInfo) throws ProjectionException {
        double con;
        double f;
        double d;
        double toRadians = projInfo.getGeographicInfo().getUnit().getRadians();
        _rot = projInfo.no_rot == null || projInfo.no_rot == 0;

        _lamc = projInfo.longitudeOfCenter * toRadians;

        if (projInfo.alpha != null) {
            _alpha = projInfo.alpha * toRadians;
        }
        if (Math.abs(_alpha) < TOLERANCE
                || Math.abs(Math.abs(Phi0) - HALF_PI) <= TOLERANCE
                || Math.abs(Math.abs(_alpha) - HALF_PI) <= TOLERANCE) {
            throw new ProjectionException(32);
        }

        _ellips = Es > 0;
        double com = _ellips ? Math.sqrt(OneEs) : 1;
        if (Math.abs(Phi0) > EPS10) {
            double sinph0 = Math.sin(Phi0);
            double cosph0 = Math.cos(Phi0);
            if (_ellips) {
                con = 1 - Es * sinph0 * sinph0;
                _bl = cosph0 * cosph0;
                _bl = Math.sqrt(1 + Es * _bl * _bl / OneEs);
                _al = _bl * K0 * com / con;
                d = _bl * com / (cosph0 * Math.sqrt(con));
            } else {
                _bl = 1;
                _al = K0;
                d = 1 / cosph0;
            }

            if ((f = d * d - 1) <= 0) {
                f = 0;
            } else {
                f = Math.sqrt(f);
                if (Phi0 < 0) {
                    f = -f;
                }
            }
            _el = f += d;
            if (_ellips) {
                _el *= Math.pow(Proj.Tsfn(Phi0, sinph0, E), _bl);
            } else {
                _el *= Tsfn0(Phi0);
            }
        } else {
            _bl = 1 / com;
            _al = K0;
            _el = d = f = 1;
        }

        _gamma = Math.asin(Math.sin(_alpha) / d);
        Lam0 = _lamc - Math.asin((.5 * (f - 1 / f)) * Math.tan(_gamma)) / _bl;
        projInfo.setLam0(Lam0);

        _singam = Math.sin(_gamma);
        _cosgam = Math.cos(_gamma);

        _sinrot = Math.sin(_alpha);
        _cosrot = Math.cos(_alpha);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Properties">
    private static double Tsfn0(double x) {
        return Math.tan(.5 * (HALF_PI - x));
    }

    //</editor-fold>
}
