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
public class Krovak extends Transform {

    //<editor-fold defaultstate="collapsed" desc="Fields">

        private boolean _czech;

    //</editor-fold>

    /**
     * Creates a new instance of the Krovak class.
     */
    public Krovak(){
              setProj4Name("krovak");
            setEsriName("Krovak");
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
                /* calculate xy from lat/lon */

                /* Constants, identical to inverse transform function */
                double s45 = 0.785398163397448;
                double s90 = 2 * s45;
                double fi0 = Phi0;

                /* Ellipsoid is used as Parameter in for.c and inv.c, therefore a must
                  be set to 1 here.
                  Ellipsoid Bessel 1841 a = 6377397.155m 1/f = 299.1528128,
                     e2=0.006674372230614;
                */

                double a = 1;
                /* e2 = Es;*/
                /* 0.006674372230614; */
                double e2 = 0.006674372230614;
                double e = Math.sqrt(e2);

                double alfa = Math.sqrt(1 + (e2 * Math.pow(Math.cos(fi0), 4)) / (1 - e2));

                double uq = 1.04216856380474;
                double u0 = Math.asin(Math.sin(fi0) / alfa);
                double g = Math.pow((1 + e * Math.sin(fi0)) / (1 - e * Math.sin(fi0)), alfa * e / 2);

                double k = Math.tan(u0 / 2 + s45) / Math.pow(Math.tan(fi0 / 2 + s45), alfa) * g;

                double k1 = K0;
                double n0 = a * Math.sqrt(1 - e2) / (1 - e2 * Math.pow(Math.sin(fi0), 2));
                double s0 = 1.37008346281555;
                double n = Math.sin(s0);
                double ro0 = k1 * n0 / Math.tan(s0);
                double ad = s90 - uq;

                /* Transformation */

                double gfi = Math.pow(((1 + e * Math.sin(lp[phi])) /
                                       (1 - e * Math.sin(lp[phi]))), (alfa * e / 2));

                double u = 2 * (Math.atan(k * Math.pow(Math.tan(lp[phi] / 2 + s45), alfa) / gfi) - s45);

                double deltav = -lp[lam] * alfa;

                double s = Math.asin(Math.cos(ad) * Math.sin(u) + Math.sin(ad) * Math.cos(u) * Math.cos(deltav));
                double d = Math.asin(Math.cos(u) * Math.sin(deltav) / Math.cos(s));
                double eps = n * d;
                double ro = ro0 * Math.pow(Math.tan(s0 / 2 + s45), n) / Math.pow(Math.tan(s / 2 + s45), n);

                /* x and y are reverted! */
                if (!_czech)
                {
                    xy[y] = ro * Math.sin(eps) / a;
                    xy[x] = ro * Math.cos(eps) / a;
                }
                else
                {
                    /* in Czech version, Y = -X and X = -Y */
                    xy[x] = -ro * Math.sin(eps) / a;
                    xy[y] = -ro * Math.cos(eps) / a;
                }
            }
        }

        /**   {@inheritDoc }     */
        @Override protected void onInverse(double[] xy, double[] lp, int startIndex, int numPoints)
        {
            /* calculate lat/lon from xy */

            /* Constants, identisch wie in der Umkehrfunktion */
            for (int i = startIndex; i < startIndex + numPoints; i++)
            {
                int phi = i * 2 + PHI;
                int lam = i * 2 + LAMBDA;
                int x = i * 2 + X;
                int y = i * 2 + Y;
                double s45 = 0.785398163397448;
                double s90 = 2 * s45;
                double fi0 = Phi0;

                /* Ellipsoid is used as Parameter in for.c and inv.c, therefore a must
                   be set to 1 here.
                   Ellipsoid Bessel 1841 a = 6377397.155m 1/f = 299.1528128,
                   e2=0.006674372230614;
                */

                double a = 1;
                /* e2 = Es; */
                /* 0.006674372230614; */
                double e2 = 0.006674372230614;
                double e = Math.sqrt(e2);

                double alfa = Math.sqrt(1 + (e2 * Math.pow(Math.cos(fi0), 4)) / (1 - e2));
                double uq = 1.04216856380474;
                double u0 = Math.asin(Math.sin(fi0) / alfa);
                double g = Math.pow((1 + e * Math.sin(fi0)) / (1 - e * Math.sin(fi0)), alfa * e / 2);

                double k = Math.tan(u0 / 2 + s45) / Math.pow(Math.tan(fi0 / 2 + s45), alfa) * g;

                double k1 = K0;
                double n0 = a * Math.sqrt(1 - e2) / (1 - e2 * Math.pow(Math.sin(fi0), 2));
                double s0 = 1.37008346281555;
                double n = Math.sin(s0);
                double ro0 = k1 * n0 / Math.tan(s0);
                double ad = s90 - uq;

                /* Transformation */
                /* revert y, x*/
                double xy0 = xy[x];
                xy[x] = xy[y];
                xy[y] = xy0;

                if (_czech)
                {
                    xy[x] *= -1.0;
                    xy[y] *= -1.0;
                }

                double ro = Math.sqrt(xy[x] * xy[x] + xy[y] * xy[y]);
                double eps = Math.atan2(xy[y], xy[x]);
                double d = eps / Math.sin(s0);
                double s = 2 * (Math.atan(Math.pow(ro0 / ro, 1 / n) * Math.tan(s0 / 2 + s45)) - s45);

                double u = Math.asin(Math.cos(ad) * Math.sin(s) - Math.sin(ad) * Math.cos(s) * Math.cos(d));
                double deltav = Math.asin(Math.cos(s) * Math.sin(d) / Math.cos(u));

                lp[lam] = Lam0 - deltav / alfa;

                /* ITERATION FOR lp[phi] */
                double fi1 = u;

                int ok = 0;
                do
                {
                    lp[phi] = 2 * (Math.atan(Math.pow(k, -1 / alfa) *
                                             Math.pow(Math.tan(u / 2 + s45), 1 / alfa) *
                                             Math.pow((1 + e * Math.sin(fi1)) / (1 - e * Math.sin(fi1)), e / 2)
                                       ) - s45);

                    if (Math.abs(fi1 - lp[phi]) < 0.000000000000001) ok = 1;
                    fi1 = lp[phi];
                } while (ok == 0);

                lp[lam] -= Lam0;
            }
        }

        /// <summary>
        /// Initializes the transform using the parameters from the specified coordinate system information
        /// </summary>
        /// <param name="projInfo">A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform</param>
        /// <remarks>The default value of CZECH is true: The X and Y axis are reversed and multiplied by -1 as typically used in GIS applications of Krovak</remarks>
        @Override protected void onInit(ProjectionInfo projInfo)
        {
            /* read some Parameters,
             * here Latitude Truescale */
            //double ts = 0;
            //if (projInfo.StandardParallel1 != null) ts = projInfo.StandardParallel1*Math.PI/180;
            //_cX = ts;

            /* we want Bessel as fixed ellipsoid */
            A = 6377397.155;
            E = Math.sqrt(Es = 0.006674372230614);

            /* if latitude of projection center is not set, use 49d30'N */
            Phi0 = projInfo.getLatitudeOfOrigin() != null ? projInfo.getPhi0() : 0.863937979737193;

            /* if center long is not set use 42d30'E of Ferro - 17d40' for Ferro */
            /* that will correspond to using longitudes relative to greenwich    */
            /* as input and output, instead of lat/long relative to Ferro */
            Lam0 = projInfo.getCentralMeridian() != null ? projInfo.getLam0() : 0.7417649320975901 - 0.308341501185665;

            /* if scale not set default to 0.9999 */
            K0 = (projInfo.getScaleFactor() != 1) ? projInfo.getScaleFactor() : 0.9999;

            //Previous BAD CODE (commented out!)
            //K0 = projInfo.CentralMeridian != null ? projInfo.getLam0() : 0.9999;

            if (projInfo.czech != null)
            {
                if (projInfo.czech != 0) _czech = true;
            }
            else
            {
                //By default set Czech to TRUE
                _czech = true;
            }
        }


    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
