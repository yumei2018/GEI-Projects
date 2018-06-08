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

import gov.ca.water.shapelite.projection.AuxiliarySphereType;
import gov.ca.water.shapelite.projection.Proj4Ellipsoid;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Spheroid;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MercatorAuxiliarySphere extends EllipticalTransform {

    //<editor-fold defaultstate="collapsed" desc="Fields">


    private double _ae;
        private boolean _geodeticToAuthalic;

    //</editor-fold>

    /**
     * Creates a new instance of the MercatorAuxiliarySphere class.
     */
    public MercatorAuxiliarySphere(){
        setProj4Name("merc");
        setEsriName("Mercator_Auxiliary_Sphere");
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
    /**
     * {@inheritDoc }
     */
    @Override
        protected void ellipticalForward(double[] lp, double[] xy, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                if (Math.abs(Math.abs(lp[phi]) - HALF_PI) <= EPS10)
                {
                    xy[x] = Double.NaN;
                    xy[y] = Double.NaN;
                    continue;
                    //throw new ProjectionException(20);
                }
                xy[x] = K0 * lp[lam];
                xy[y] = -K0 * Math.log(Proj.Tsfn(lp[phi], Math.sin(lp[phi]), E));
            }
        }

        /**
     * {@inheritDoc }
     */
        @Override
        protected void sphericalForward(double[] lp, double[] xy, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                if (Math.abs(Math.abs(lp[phi]) - HALF_PI) <= EPS10)
                {
                    xy[x] = Double.NaN;
                    xy[y] = Double.NaN;
                    continue;
                    //throw new ProjectionException(20);
                }
                double phiVal = lp[phi];
                if (_geodeticToAuthalic)
                {
                    phiVal = Authalic(lp[phi]);
                }
                xy[x] = K0 * lp[lam];
                xy[y] = K0 * Math.log(Math.tan(FORT_PI + .5 * phiVal));
            }
        }

       /**
     * {@inheritDoc }
     */
        @Override
        protected void ellipticalInverse(double[] xy, double[] lp, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                if ((lp[phi] = Proj.Phi2(Math.exp(-xy[y] / K0), E)) == Double.MAX_VALUE)
                {
                    lp[lam] = Double.NaN;
                    lp[phi] = Double.NaN;
                    continue;
                    //throw new ProjectionException(20);
                }
                lp[lam] = xy[x] / K0;
            }
        }

       /**
     * {@inheritDoc }
     */
        @Override
        protected void sphericalInverse(double[] xy, double[] lp, int startIndex, int numPoints)
        {
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                lp[phi] = HALF_PI - 2 * Math.atan(Math.exp(-xy[y] / K0));

                if (_geodeticToAuthalic)
                {
                    // TO DO: Convert from Authalic to Geodetic latitude.
                }

                lp[lam] = xy[x] / K0;
            }
        }

        /// <summary>
        /// n' is a calculation based on the eccentricity
        /// </summary>
        /// <param name="phi"></param>
        private double Np(double phi)
        {
            double t = Math.sin(phi) * Math.sin(_ae);
            return 1 / Math.sqrt(1 - (t * t));
        }

        private double Authalic(double phi)
        {
            double n = Np(phi);

            double top = Math.sin(phi) * Math.sin(_ae) * n * n + Math.log(n * (1 + Math.sin(phi) * Math.sin(_ae)));
            double sec2 = 1 / Math.cos(_ae) * Math.cos(_ae);
            double bottom = Math.sin(_ae) * sec2 + Math.log((1 / Math.cos(_ae) * (1 + Math.sin(_ae))));
            return Math.asin(top / bottom);
        }

        /**
     * {@inheritDoc }
     */
        @Override
        protected void onInit(ProjectionInfo projInfo) throws ProjectionException
        {
            if (projInfo.getAuxiliarySphereType() == AuxiliarySphereType.AuthalicWithConvertedLatitudes)
            {
                throw new ProjectionException("The conversion which requries latitude conversion to authalic latitudes is not yet supported");
            }
            double phits = 0.0;
            boolean isPhits = false;
            if (projInfo.standardParallel1 != null)
            {
                isPhits = true;
                phits = projInfo.getPhi1();
                if (phits >= HALF_PI) throw new ProjectionException(23);
            }

            if (IsElliptical)
            { /* ellipsoid */
                if (isPhits) K0 = Proj.Msfn(Math.sin(phits), Math.cos(phits), Es);
            }
            else
            { /* sphere */
                if (isPhits) K0 = Math.cos(phits);
            }

            if (projInfo.getAuxiliarySphereType() == AuxiliarySphereType.AuthalicWithConvertedLatitudes)
            {
                Spheroid sph = new Spheroid(Proj4Ellipsoid.WGS_1984);
                _ae = Math.acos(sph.getPolarRadius() / sph.getEquatorialRadius());
                _geodeticToAuthalic = true;
            }
        }


    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
