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
public class AzimuthalEquidistant extends EllipticalTransform {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private static final double TOL = 1E-14;
    private double _cosph0;
    private double[] _en;
    private double _g;
    private double _he;
    private boolean _isGuam;
    private double _m1;
    private Modes _mode;
    private double _mp;
    private double _n1;
    private double _sinph0;

    //</editor-fold>
    /**
     * Creates a new instance of the AzimuthalEquidistant class.
     */
    public AzimuthalEquidistant() {
        this.setProj4Name("aeqd");
        this.setEsriName("Azimuthal_Equidistant");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void onInit(ProjectionInfo projInfo) {
        Phi0 = projInfo.getPhi0();
        if (Math.abs(Math.abs(Phi0) - HALF_PI) < EPS10) {
            _mode = Phi0 < 0 ? Modes.SouthPole : Modes.NorthPole;
            _sinph0 = Phi0 < 0 ? -1 : 1;
            _cosph0 = 0;
        } else if (Math.abs(Phi0) < EPS10) {
            _mode = Modes.Equatorial;
            _sinph0 = 0;
            _cosph0 = 1;
        } else {
            _mode = Modes.Oblique;
            _sinph0 = Math.sin(Phi0);
            _cosph0 = Math.cos(Phi0);
        }
        if (Es == 0) {
            return;
        }
        _en = Proj.Enfn(Es);
        if (projInfo.guam != null && projInfo.guam) {
            _m1 = Proj.Mlfn(Phi0, _sinph0, _cosph0, _en);
            _isGuam = true;
        } else {
            switch (_mode) {
                case NorthPole:
                    _mp = Proj.Mlfn(HALF_PI, 1, 0, _en);
                    break;
                case SouthPole:
                    _mp = Proj.Mlfn(-HALF_PI, -1, 0, _en);
                    break;
                case Equatorial:
                case Oblique:
                    _n1 = 1 / Math.sqrt(1 - Es * _sinph0 * _sinph0);
                    _g = _sinph0 * (_he = E / Math.sqrt(OneEs));
                    _he *= _cosph0;
                    break;
            }
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
            double sinphi = Math.sin(lp[phi]);
            double cosphi = Math.cos(lp[phi]);
            double coslam = Math.cos(lp[lam]);
            switch (_mode) {
                case Equatorial:
                case Oblique:
                    if (_mode == Modes.Equatorial) {
                        xy[y] = cosphi * coslam;
                    } else {
                        xy[y] = _sinph0 * sinphi + _cosph0 * cosphi * coslam;
                    }
                    if (Math.abs(Math.abs(xy[y]) - 1) < TOL) {
                        if (xy[y] < 0) {
                            xy[x] = Double.NaN;
                            xy[y] = Double.NaN;
                            continue;
                            //throw new ProjectionException(20);
                        }
                        xy[x] = xy[y] = 0;
                    } else {
                        xy[y] = Math.acos(xy[y]);
                        xy[y] /= Math.sin(xy[y]);
                        xy[x] = xy[y] * cosphi * Math.sin(lp[lam]);
                        xy[y] *= (_mode == Modes.Equatorial)
                                ? sinphi
                                : _cosph0 * sinphi - _sinph0 * cosphi * coslam;
                    }
                    break;
                case NorthPole:
                case SouthPole:
                    if (_mode == Modes.NorthPole) {
                        lp[phi] = -lp[phi];
                        coslam = -coslam;
                    }
                    if (Math.abs(lp[phi] - HALF_PI) < EPS10) {
                        xy[x] = Double.NaN;
                        xy[y] = Double.NaN;
                        continue;
                        //throw new ProjectionException(20);
                    }
                    xy[x] = (xy[y] = (HALF_PI + lp[phi])) * Math.sin(lp[lam]);
                    xy[y] *= coslam;
                    break;
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void ellipticalForward(double[] lp, double[] xy, int startIndex, int numPoints) {
        if (_isGuam) {
            GuamForward(lp, xy, startIndex, numPoints);
            return;
        }
        for (int i = startIndex; i < startIndex + numPoints; i++) {
            int phi = i * 2 + PHI;
            int lam = i * 2 + LAMBDA;
            int x = i * 2 + X;
            int y = i * 2 + Y;
            double coslam = Math.cos(lp[lam]);
            double cosphi = Math.cos(lp[phi]);
            double sinphi = Math.sin(lp[phi]);
            switch (_mode) {
                case NorthPole:
                case SouthPole:
                    if (_mode == Modes.NorthPole) {
                        coslam = -coslam;
                    }
                    double rho;
                    xy[x] = (rho = Math.abs(_mp - Proj.Mlfn(lp[phi], sinphi, cosphi, _en))) * Math.sin(lp[lam]);
                    xy[y] = rho * coslam;
                    break;
                case Equatorial:
                case Oblique:
                    if (Math.abs(lp[lam]) < EPS10 && Math.abs(lp[phi] - Phi0) < EPS10) {
                        xy[x] = xy[y] = 0;
                        break;
                    }
                    double t = Math.atan2(OneEs * sinphi + Es * _n1 * _sinph0
                            * Math.sqrt(1 - Es * sinphi * sinphi), cosphi);
                    double ct = Math.cos(t);
                    double st = Math.sin(t);
                    double az = Math.atan2(Math.sin(lp[lam]) * ct, _cosph0 * st - _sinph0 * coslam * ct);
                    double cA = Math.cos(az);
                    double sA = Math.sin(az);
                    double s = Math.asin(Math.abs(sA) < TOL
                            ? (_cosph0 * st - _sinph0 * coslam * ct) / cA
                            : Math.sin(lp[lam]) * ct / sA);
                    double h = _he * cA;
                    double h2 = h * h;
                    double c = _n1 * s * (1 + s * s * (-h2 * (1 - h2) / 6
                            + s * (_g * h * (1 - 2 * h2 * h2) / 8
                            + s * ((h2 * (4 - 7 * h2) - 3 * _g * _g * (1 - 7 * h2))
                            / 120 - s * _g * h / 48))));
                    xy[x] = c * sA;
                    xy[y] = c * cA;
                    break;
            }
        }
    }

    private void GuamForward(double[] lp, double[] xy, int startIndex, int numPoints) {
        for (int i = startIndex; i < startIndex + numPoints; i++) {
            int phi = i * 2 + PHI;
            int lam = i * 2 + LAMBDA;
            int x = i * 2 + X;
            int y = i * 2 + Y;
            double cosphi = Math.cos(lp[phi]);
            double sinphi = Math.sin(lp[phi]);
            double t = 1 / Math.sqrt(1 - Es * sinphi * sinphi);
            xy[x] = lp[lam] * cosphi * t;
            xy[y] = Proj.Mlfn(lp[phi], sinphi, cosphi, _en) - _m1
                    + .5 * lp[lam] * lp[lam] * cosphi * sinphi * t;
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
            double cRh;

            if ((cRh = Proj.Hypot(xy[x], xy[y])) > Math.PI) {
                if (cRh - EPS10 > Math.PI) {
                    lp[phi] = Double.NaN;
                    lp[lam] = Double.NaN;
                    continue;
                    //throw new ProjectionException(20);
                }
                cRh = Math.PI;
            } else if (cRh < EPS10) {
                lp[phi] = Phi0;
                lp[lam] = 0;
                return;
            }
            if (_mode == Modes.Oblique || _mode == Modes.Equatorial) {
                double sinc = Math.sin(cRh);
                double cosc = Math.cos(cRh);
                if (_mode == Modes.Equatorial) {
                    lp[phi] = Proj.Aasin(xy[y] * sinc / cRh);
                    xy[x] *= sinc;
                    xy[y] = cosc * cRh;
                } else {
                    lp[phi] = Proj.Aasin(cosc * _sinph0 + xy[y] * sinc * _cosph0
                            / cRh);
                    xy[y] = (cosc - _sinph0 * Math.sin(lp[phi])) * cRh;
                    xy[x] *= sinc * _cosph0;
                }
                lp[lam] = xy[y] == 0 ? 0 : Math.atan2(xy[x], xy[y]);
            } else if (_mode == Modes.NorthPole) {
                lp[phi] = HALF_PI - cRh;
                lp[lam] = Math.atan2(xy[x], -xy[y]);
            } else {
                lp[phi] = cRh - HALF_PI;
                lp[lam] = Math.atan2(xy[x], xy[y]);
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void ellipticalInverse(double[] xy, double[] lp, int startIndex, int numPoints) {
        if (_isGuam) {
            GuamInverse(xy, lp, startIndex, numPoints);
        }

        for (int i = startIndex; i < startIndex + numPoints; i++) {
            int phi = i * 2 + PHI;
            int lam = i * 2 + LAMBDA;
            int x = i * 2 + X;
            int y = i * 2 + Y;
            double c;

            if ((c = Proj.Hypot(xy[x], xy[y])) < EPS10) {
                lp[phi] = Phi0;
                lp[lam] = 0;
                return;
            }
            if (_mode == Modes.Oblique || _mode == Modes.Equatorial) {
                double az;
                double cosAz = Math.cos(az = Math.atan2(xy[x], xy[y]));
                double t = _cosph0 * cosAz;
                double b = Es * t / OneEs;
                double a = -b * t;
                b *= 3 * (1 - a) * _sinph0;
                double d = c / _n1;
                double e = d * (1 - d * d * (a * (1 + a) / 6 + b * (1 + 3 * a) * d / 24));
                double f = 1 - e * e * (a / 2 + b * e / 6);
                double psi = Proj.Aasin(_sinph0 * Math.cos(e) + t * Math.sin(e));
                lp[lam] = Proj.Aasin(Math.sin(az) * Math.sin(e) / Math.cos(psi));
                if ((t = Math.abs(psi)) < EPS10) {
                    lp[phi] = 0;
                } else if (Math.abs(t - HALF_PI) < 0) {
                    lp[phi] = HALF_PI;
                } else {
                    lp[phi] = Math.atan((1 - Es * f * _sinph0 / Math.sin(psi)) * Math.tan(psi)
                            / OneEs);
                }
            } else {
                /* Polar */
                lp[phi] = Proj.InvMlfn(_mode == Modes.NorthPole ? _mp - c : _mp + c,
                        Es, _en);
                lp[lam] = Math.atan2(xy[x], _mode == Modes.NorthPole ? -xy[y] : xy[y]);
            }
        }
    }

    private void GuamInverse(double[] xy, double[] lp, int startIndex, int numPoints) {
        for (int i = startIndex; i < startIndex + numPoints; i++) {
            int phi = i * 2 + PHI;
            int lam = i * 2 + LAMBDA;
            int x = i * 2 + X;
            int y = i * 2 + Y;
            double t = 0;
            int j;

            double x2 = 0.5 * xy[x] * xy[x];
            lp[phi] = Phi0;
            for (j = 0; j < 3; ++j) {
                t = E * Math.sin(lp[phi]);
                lp[phi] = Proj.InvMlfn(_m1 + xy[y]
                        - x2 * Math.tan(lp[phi]) * (t = Math.sqrt(1 - t * t)), Es, _en);
            }
            lp[lam] = xy[x] * t / Math.cos(lp[phi]);
        }
    }

    //</editor-fold>

}
