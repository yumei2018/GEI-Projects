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
public class GeoStationarySatellite extends EllipticalTransform {

    //<editor-fold defaultstate="collapsed" desc="Fields">

        private double _c;
        private double _h;
        private double _radiusG;
        private double _radiusG1;
        private double _radiusP;
        private double _radiusP2;
        private double _radiusPInv2;

    //</editor-fold>

    /**
     * Creates a new instance of the GeoStationarySatellite class.
     */
    public GeoStationarySatellite(){
              setEsriName("Geostationary_Satellite");
            setProj4Name("geos");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
       /**   {@inheritDoc }     */
        @Override protected void sphericalForward(double[] lp, double[] xy, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                /* Calculation of the three components of the vector from satellite to
                    ** position on earth surface (lon, lat).*/
                double tmp = Math.cos(lp[phi]);
                double vx = Math.cos(lp[lam]) * tmp;
                double vy = Math.sin(lp[lam]) * tmp;
                double vz = Math.sin(lp[phi]);

                /* Check visibility.*/
                if (((_radiusG - vx) * vx - vy * vy - vz * vz) < 0)
                {
                    xy[x] = Double.NaN;
                    xy[y] = Double.NaN;
                    continue;
                    //throw new ProjectionException(20);
                }

                /* Calculation based on view angles from satellite.*/
                tmp = _radiusG - vx;
                xy[x] = _radiusG1 * Math.atan(vy / tmp);
                xy[y] = _radiusG1 * Math.atan(vz / Proj.Hypot(vy, tmp));
            }
        }

        /**   {@inheritDoc }     */
        @Override protected void ellipticalForward(double[] lp, double[] xy, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                /* Calculation of geocentric latitude. */
                lp[phi] = Math.atan(_radiusP2 * Math.tan(lp[phi]));

                /* Calculation of the three components of the vector from satellite to
                    ** position on earth surface (lon, lat).*/
                double r = (_radiusP) / Proj.Hypot(_radiusP * Math.cos(lp[phi]), Math.sin(lp[phi]));
                double vx = r * Math.cos(lp[lam]) * Math.cos(lp[phi]);
                double vy = r * Math.sin(lp[lam]) * Math.cos(lp[phi]);
                double vz = r * Math.sin(lp[phi]);

                /* Check visibility. */
                if (((_radiusG - vx) * vx - vy * vy - vz * vz * _radiusPInv2) < 0)
                {
                    xy[x] = Double.NaN;
                    xy[y] = Double.NaN;
                    continue;
                    // throw new ProjectionException(20);
                }

                /* Calculation based on view angles from satellite. */
                double tmp = _radiusG - vx;
                xy[x] = _radiusG1 * Math.atan(vy / tmp);
                xy[y] = _radiusG1 * Math.atan(vz / Proj.Hypot(vy, tmp));
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
                double det;

                /* Setting three components of vector from satellite to position.*/
                double vx = -1.0;
                double vy = Math.tan(xy[x] / (_radiusG - 1.0));
                double vz = Math.tan(xy[y] / (_radiusG - 1.0)) * Math.sqrt(1.0 + vy * vy);

                /* Calculation of terms in cubic equation and determinant.*/
                double a = vy * vy + vz * vz + vx * vx;
                double b = 2 * _radiusG * vx;
                if ((det = (b * b) - 4 * a * _c) < 0)
                {
                    lp[lam] = Double.NaN;
                    lp[phi] = Double.NaN;
                    continue;
                    // throw new ProjectionException(20);
                }

                /* Calculation of three components of vector from satellite to position.*/
                double k = (-b - Math.sqrt(det)) / (2 * a);
                vx = _radiusG + k * vx;
                vy *= k;
                vz *= k;

                /* Calculation of longitude and latitude.*/
                lp[lam] = Math.atan2(vy, vx);
                lp[phi] = Math.atan(vz * Math.cos(lp[lam]) / vx);
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
                double det;

                /* Setting three components of vector from satellite to position.*/
                double vx = -1.0;
                double vy = Math.tan(xy[x] / _radiusG1);
                double vz = Math.tan(xy[y] / _radiusG1) * Proj.Hypot(1.0, vy);

                /* Calculation of terms in cubic equation and determinant.*/
                double a = vz / _radiusP;
                a = vy * vy + a * a + vx * vx;
                double b = 2 * _radiusG * vx;
                if ((det = (b * b) - 4 * a * _c) < 0)
                {
                    lp[lam] = Double.NaN;
                    lp[phi] = Double.NaN;
                    continue;
                    //throw new ProjectionException(20);
                }

                /* Calculation of three components of vector from satellite to position.*/
                double k = (-b - Math.sqrt(det)) / (2 * a);
                vx = _radiusG + k * vx;
                vy *= k;
                vz *= k;

                /* Calculation of longitude and latitude.*/
                lp[lam] = Math.atan2(vy, vx);
                lp[phi] = Math.atan(vz * Math.cos(lp[lam]) / vx);
                lp[phi] = Math.atan(_radiusPInv2 * Math.tan(lp[phi]));
            }
        }

        /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        @Override protected void onInit(ProjectionInfo projInfo) throws ProjectionException
        {
            if ((_h = projInfo.h) <= 0) throw new ProjectionException(-30);
            if (Phi0 == 0) throw new ProjectionException(-46);
            _radiusG = 1 + (_radiusG1 = _h / A);
            _c = _radiusG * _radiusG - 1.0;
            if (IsElliptical)
            {
                _radiusP = Math.sqrt(OneEs);
                _radiusP2 = OneEs;
                _radiusPInv2 = ROneEs;
            }
            else
            {
                _radiusP = _radiusP2 = _radiusPInv2 = 1.0;
            }
        }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
