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
public class Gnomonic extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">

        private double _cosph0;
        private Modes _mode;
        private double _sinph0;

    //</editor-fold>

    /**
     * Creates a new instance of the Gnomonic class.
     */
    public Gnomonic(){
              setProj4Name("gnom");
            setEsriName("Gnomonic");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
        /**   {@inheritDoc }     */
        @Override protected void onForward(double[] lp, double[] xy, int startIndex, int numPoints)
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
                switch (_mode)
                {
                    case Equatorial:
                        xy[y] = cosphi * coslam;
                        break;
                    case Oblique:
                        xy[y] = _sinph0 * sinphi + _cosph0 * cosphi * coslam;
                        break;
                    case SouthPole:
                        xy[y] = -sinphi;
                        break;
                    case NorthPole:
                        xy[y] = sinphi;
                        break;
                }
                if (xy[y] <= EPS10)
                {
                    xy[x] = Double.NaN;
                    xy[y] = Double.NaN;
                    continue;
                    //throw new ProjectionException(20);
                }
                xy[x] = (xy[y] = 1 / xy[y]) * cosphi * Math.sin(lp[lam]);
                switch (_mode)
                {
                    case Equatorial:
                        xy[y] *= sinphi;
                        break;
                    case Oblique:
                        xy[y] *= _cosph0 * sinphi - _sinph0 * cosphi * coslam;
                        break;
                    case NorthPole:
                    case SouthPole:
                        if (_mode == Modes.NorthPole) coslam = -coslam;
                        xy[y] *= cosphi * coslam;
                        break;
                }
            }
        }

        /**   {@inheritDoc }     */
        @Override protected void onInverse(double[] xy, double[] lp, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                double rh = Proj.Hypot(xy[x], xy[y]);
                double sinz = Math.sin(lp[phi] = Math.atan(rh));
                double cosz = Math.sqrt(1 - sinz * sinz);
                if (Math.abs(rh) <= EPS10)
                {
                    lp[phi] = Phi0;
                    lp[lam] = 0;
                }
                else
                {
                    switch (_mode)
                    {
                        case Oblique:
                            lp[phi] = cosz * _sinph0 + xy[y] * sinz * _cosph0 / rh;
                            if (Math.abs(lp[phi]) >= 1)
                                lp[phi] = lp[phi] > 0 ? HALF_PI : -HALF_PI;
                            else
                                lp[phi] = Math.asin(lp[phi]);
                            xy[y] = (cosz - _sinph0 * Math.sin(lp[phi])) * rh;
                            xy[x] *= sinz * _cosph0;
                            break;
                        case Equatorial:
                            lp[phi] = xy[y] * sinz / rh;
                            if (Math.abs(lp[phi]) >= 1)
                                lp[phi] = lp[phi] > 0 ? HALF_PI : -HALF_PI;
                            else
                                lp[phi] = Math.asin(lp[phi]);
                            xy[y] = cosz * rh;
                            xy[x] *= sinz;
                            break;
                        case SouthPole:
                            lp[phi] -= HALF_PI;
                            break;
                        case NorthPole:
                            lp[phi] = HALF_PI - lp[phi];
                            xy[y] = -xy[y];
                            break;
                    }
                    lp[lam] = Math.atan2(xy[x], xy[y]);
                }
            }
        }

        /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override protected void onInit(ProjectionInfo projInfo)
        {
            if (Math.abs(Math.abs(Phi0) - HALF_PI) < EPS10)
                _mode = Phi0 < 0 ? Modes.SouthPole : Modes.NorthPole;
            else if (Math.abs(Phi0) < EPS10)
                _mode = Modes.Equatorial;
            else
            {
                _mode = Modes.Oblique;
                _sinph0 = Math.sin(Phi0);
                _cosph0 = Math.cos(Phi0);
            }
        }

    //</editor-fold>



}
