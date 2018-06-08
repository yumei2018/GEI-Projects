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
public class Orthographic extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">

        private double _cosph0;
        private Modes _mode;
        private double _sinph0;

    //</editor-fold>

    /**
     * Creates a new instance of the Orthographic class.
     */
    public Orthographic(){
              setEsriName("Orthographic");
            setProj4Name("ortho");
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
                double cosphi = Math.cos(lp[phi]);
                double coslam = Math.cos(lp[lam]);
                switch (_mode)
                {
                    case Equatorial:
                        if (cosphi * coslam < -EPS10)
                        {
                            xy[x] = Double.NaN;
                            xy[y] = Double.NaN;
                            continue;
                            //ProjectionException(20);
                        }
                        xy[y] = Math.sin(lp[phi]);
                        break;
                    case Oblique:
                        double sinphi;
                        if (_sinph0 * (sinphi = Math.sin(lp[phi])) + _cosph0 * cosphi * coslam < -EPS10)
                        {
                            xy[x] = Double.NaN;
                            xy[y] = Double.NaN;
                            continue;
                            //ProjectionException(20);
                        }
                        xy[y] = _cosph0 * sinphi - _sinph0 * cosphi * coslam;
                        break;
                    default:
                        if (_mode == Modes.NorthPole) coslam = -coslam;
                        if (Math.abs(lp[phi] - Phi0) - EPS10 > HALF_PI)
                        {
                            xy[x] = Double.NaN;
                            xy[y] = Double.NaN;
                            continue;
                            //ProjectionException(20);
                        }
                        xy[y] = cosphi * coslam;
                        break;
                }
                xy[x] = cosphi * Math.sin(lp[lam]);
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
                double rh, sinc;
                double cx = xy[x];
                double cy = xy[y];
                if ((sinc = (rh = Proj.Hypot(cx, cy))) > 1)
                {
                    if ((sinc - 1) > EPS10)
                    {
                        lp[lam] = Double.NaN;
                        lp[phi] = Double.NaN;
                        continue;
                        //ProjectionException(20);
                    }
                    sinc = 1;
                }
                double cosc = Math.sqrt(1 - sinc * sinc);
                if (Math.abs(rh) <= EPS10)
                {
                    lp[phi] = Phi0;
                    lp[lam] = 0;
                }
                else
                {
                    switch (_mode)
                    {
                        case NorthPole:
                            cy = -cy;
                            lp[phi] = Math.acos(sinc);
                            break;
                        case SouthPole:
                            lp[phi] = -Math.acos(sinc);
                            break;
                        case Equatorial:
                            lp[phi] = cy * sinc / rh;
                            cx *= sinc;
                            cy = cosc * rh;
                            if (Math.abs(lp[phi]) >= 1)
                            {
                                lp[phi] = lp[phi] < 0 ? -HALF_PI : HALF_PI;
                            }
                            else
                            {
                                lp[phi] = Math.asin(lp[phi]);
                            }
                            break;
                        case Oblique:
                            lp[phi] = cosc * _sinph0 + cy * sinc * _cosph0 / rh;
                            cy = (cosc - _sinph0 * lp[phi]) * rh;
                            cx *= sinc * _cosph0;
                            if (Math.abs(lp[phi]) >= 1)
                            {
                                lp[phi] = lp[phi] < 0 ? -HALF_PI : HALF_PI;
                            }
                            else
                            {
                                lp[phi] = Math.asin(lp[phi]);
                            }
                            break;
                    }
                    lp[lam] = (cy == 0 && (_mode == Modes.Oblique || _mode == Modes.Equatorial))
                                  ? (cx == 0 ? 0 : cx < 0 ? -HALF_PI : HALF_PI)
                                  : Math.atan2(cx, cy);
                }
            }
        }

        /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override protected void onInit(ProjectionInfo projInfo)
        {
            if (Math.abs(Math.abs(Phi0) - HALF_PI) <= EPS10)
            {
                _mode = Phi0 < 0 ? Modes.SouthPole : Modes.NorthPole;
            }
            else if (Math.abs(Phi0) > EPS10)
            {
                _mode = Modes.Oblique;
                _sinph0 = Math.sin(Phi0);
                _cosph0 = Math.cos(Phi0);
            }
            else
            {
                _mode = Modes.Equatorial;
            }
        }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
