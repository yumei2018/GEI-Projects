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
public class LambertAzimuthalEqualArea extends EllipticalTransform {

    //<editor-fold defaultstate="collapsed" desc="Fields">
        private double[] _apa;
        private double _cosb1;
        private double _dd;
        private Modes _mode;
        private double _qp;
        private double _rq;
        private double _sinb1;
        private double _xmf;
        private double _ymf;


    //</editor-fold>

    /**
     * Creates a new instance of the LambertAzimuthalEqualArea class.
     */
    public LambertAzimuthalEqualArea(){
              setEsriName("Lambert_Azimuthal_Equal_Area");
            setProj4Name("laea");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
       /**   {@inheritDoc }     */
        @Override protected void ellipticalForward(double[] lp, double[] xy, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                double sinb = 0, cosb = 0, b = 0;
                double coslam = Math.cos(lp[lam]);
                double sinlam = Math.sin(lp[lam]);
                double sinphi = Math.sin(lp[phi]);
                double q = Proj.Qsfn(sinphi, E, OneEs);
                if (_mode == Modes.Oblique || _mode == Modes.Equatorial)
                {
                    sinb = q / _qp;
                    cosb = Math.sqrt(1 - sinb * sinb);
                }
                switch (_mode)
                {
                    case Oblique:
                        b = 1 + _sinb1 * sinb + _cosb1 * cosb * coslam;
                        break;
                    case Equatorial:
                        b = 1 + cosb * coslam;
                        break;
                    case NorthPole:
                        b = HALF_PI + lp[phi];
                        q = _qp - q;
                        break;
                    case SouthPole:
                        b = lp[phi] - HALF_PI;
                        q = _qp + q;
                        break;
                }
                if (Math.abs(b) < EPS10)
                {
                    xy[x] = Double.NaN;
                    xy[y] = Double.NaN;
                    continue;
                    //throw new ProjectionException(20);
                }
                switch (_mode)
                {
                    case Oblique:
                        xy[y] = _ymf * (b = Math.sqrt(2 / b)) * (_cosb1 * sinb - _sinb1 * cosb * coslam);
                        xy[x] = _xmf * b * cosb * sinlam;
                        break;
                    case Equatorial:
                        xy[y] = (b = Math.sqrt(2 / (1 + cosb * coslam))) * sinb * _ymf;
                        xy[x] = _xmf * b * cosb * sinlam;
                        break;
                    case NorthPole:
                    case SouthPole:
                        if (q >= 0)
                        {
                            xy[x] = (b = Math.sqrt(q)) * sinlam;
                            xy[y] = coslam * (_mode == Modes.SouthPole ? b : -b);
                        }
                        else
                        {
                            xy[x] = xy[y] = 0;
                        }
                        break;
                }
            }
        }

        /**   {@inheritDoc }     */
        @Override protected void sphericalForward(double[] lp, double[] xy, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                double sinphi = Math.sin(lp[phi]);
                double cosphi = Math.cos(lp[phi]);
                double coslam = Math.cos(lp[lam]);
                if (_mode == Modes.Equatorial || _mode == Modes.Oblique)
                {
                    if (_mode == Modes.Equatorial) xy[x] = 1 + cosphi * coslam;
                    if (_mode == Modes.Oblique) xy[y] = 1 + _sinb1 * sinphi + _cosb1 * cosphi * coslam;
                    if (xy[y] <= EPS10)
                    {
                        xy[x] = Double.NaN;
                        xy[y] = Double.NaN;
                        continue;
                        //throw new ProjectionException(20);
                    }
                    xy[x] = (xy[y] = Math.sqrt(2 / xy[y])) * cosphi * Math.sin(lp[lam]);
                    xy[y] *= _mode == Modes.Equatorial ? sinphi : _cosb1 * sinphi - _sinb1 * cosphi * coslam;
                }
                else
                {
                    if (_mode == Modes.NorthPole) coslam = -coslam;
                    if (Math.abs(lp[phi] + Phi0) < EPS10)
                    {
                        xy[x] = Double.NaN;
                        xy[y] = Double.NaN;
                        continue;
                        //throw new ProjectionException(20);
                    }
                    xy[y] = FORT_PI - lp[phi] * .5;
                    xy[y] = 2 * (_mode == Modes.SouthPole ? Math.cos(xy[y]) : Math.sin(xy[y]));
                    xy[x] = xy[y] * Math.sin(lp[lam]);
                    xy[y] *= coslam;
                }
            }
        }

        /**   {@inheritDoc }     */
        @Override protected void ellipticalInverse(double[] xy, double[] lp, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                double ab;
                double cx = xy[x];
                double cy = xy[y];
                if (_mode == Modes.Equatorial || _mode == Modes.Oblique)
                {
                    double rho = Proj.Hypot(cx /= _dd, cy * _dd);
                    if (rho < EPS10)
                    {
                        lp[lam] = 0;
                        lp[phi] = Phi0;
                        return;
                    }
                    double sCe;
                    double cCe = Math.cos(sCe = 2 * Math.asin(.5 * rho / _rq));
                    cx *= (sCe = Math.sin(sCe));
                    if (_mode == Modes.Oblique)
                    {
                        ab = cCe * _sinb1 + y * sCe * _cosb1 / rho;
                        //q = _qp*(ab);
                        cy = rho * _cosb1 * cCe - cy * _sinb1 * sCe;
                    }
                    else
                    {
                        ab = cy * sCe / rho;
                        //q = _qp*(ab);
                        cy = rho * cCe;
                    }
                }
                else
                {
                    if (_mode == Modes.NorthPole) cy = -cy;
                    double q;
                    if ((q = (cx * cx + cy * cy)) == 0)
                    {
                        lp[lam] = 0;
                        lp[phi] = Phi0;
                        return;
                    }
                    ab = 1 - q / _qp;
                    if (_mode == Modes.SouthPole) ab = -ab;
                }
                lp[lam] = Math.atan2(cx, cy);
                lp[phi] = Proj.AuthLat(Math.asin(ab), _apa);
            }
        }

        /**   {@inheritDoc }     */
        @Override protected void sphericalInverse(double[] xy, double[] lp, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                double cosz = 0, sinz = 0;
                double cx = xy[x];
                double cy = xy[y];
                double rh = Proj.Hypot(cx, cy);
                if ((lp[phi] = rh * .5) > 1)
                {
                    lp[lam] = Double.NaN;
                    lp[phi] = Double.NaN;
                    continue;
                    //throw new ProjectionException(20);
                }
                lp[phi] = 2 * Math.asin(lp[phi]);
                if (_mode == Modes.Oblique || _mode == Modes.Equatorial)
                {
                    sinz = Math.sin(lp[phi]);
                    cosz = Math.cos(lp[phi]);
                }
                switch (_mode)
                {
                    case Equatorial:
                        lp[phi] = Math.abs(rh) <= EPS10 ? 0 : Math.asin(cy * sinz / rh);
                        cx *= sinz;
                        cy = cosz * rh;
                        break;
                    case Oblique:
                        lp[phi] = Math.abs(rh) <= EPS10 ? Phi0 : Math.asin(cosz * _sinb1 + cy * sinz * _sinb1 / rh);
                        cx *= sinz * _cosb1;
                        cy = (cosz - Math.sin(lp[phi]) * _sinb1) * rh;
                        break;
                    case NorthPole:
                        cy = -cy;
                        lp[phi] = HALF_PI - lp[phi];
                        break;
                    case SouthPole:
                        lp[phi] -= HALF_PI;
                        break;
                }
                lp[lam] = (cy == 0 && (_mode == Modes.Equatorial || _mode == Modes.Oblique)) ? 0 : Math.atan2(cx, cy);
            }
        }

        /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override protected void onInit(ProjectionInfo projInfo)
        {
            double t = Math.abs(Phi0);
            if (Math.abs(t - HALF_PI) < EPS10)
            {
                _mode = Phi0 < 0 ? Modes.SouthPole : Modes.NorthPole;
            }
            else if (Math.abs(t) < EPS10)
            {
                _mode = Modes.Equatorial;
            }
            else
            {
                _mode = Modes.Oblique;
            }
            if (Es == 0)
            {
                IsElliptical = false;
                _mode = Modes.Oblique;
                _sinb1 = Math.sin(Phi0);
                _cosb1 = Math.cos(Phi0);
                return;
            }
            IsElliptical = true;

            _qp = Proj.Qsfn(1, Es, OneEs);
            // _mmf = .5/(1 - Es);
            _apa = Proj.Authset(Es);
            switch (_mode)
            {
                case NorthPole:
                case SouthPole:
                    _dd = 1;
                    break;
                case Equatorial:
                    _dd = 1 / (_rq = Math.sqrt(.5 * _qp));
                    _xmf = 1;
                    _ymf = .5 * _qp;
                    break;
                case Oblique:
                    _rq = Math.sqrt(.5 * _qp);
                    double sinphi = Math.sin(Phi0);
                    _sinb1 = Proj.Qsfn(sinphi, E, OneEs);
                    _cosb1 = Math.sqrt(1 - _sinb1 * _sinb1);
                    _dd = Math.cos(Phi0) / (Math.sqrt(1 - Es * sinphi * sinphi) * _rq * _cosb1);
                    _ymf = _xmf = _rq / _dd;
                    _xmf *= _dd;
                    break;
            }
        }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
