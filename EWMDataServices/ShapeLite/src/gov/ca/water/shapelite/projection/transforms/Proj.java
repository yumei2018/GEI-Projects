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

import gov.ca.water.shapelite.projection.MeridionalDistance;
import java.util.Arrays;

/**
 * Proj  contains frequently used static helper methods for doing projection calculations
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Proj {

        /**
         * Effectively 1 but with a tolerance of 1E-14
         */
        private static final double ONE_TOL = 1.00000000000001;
        /**
         * 1E-50
         */
        private static final double ATOL = 1E-50;

        private static final int R = 0;
        private static final int I = 1;

        /// <summary>
        /// Tolerant Arcsin
        /// </summary>
        /// <param name="v"></param>
        /// <returns></returns>
        public static double Aasin(double v)
        {
            double av;
            if ((av = Math.abs(v)) >= 1)
            {
                if (av < ONE_TOL)
                {
                    return Double.NaN;
                    //ProjectionException(19);
                }
                return v < 0 ? -Math.PI / 2 : Math.PI / 2;
            }
            return Math.asin(v);
        }

        /// <summary>
        /// Tolerant ArcCosine
        /// </summary>
        /// <param name="v"></param>
        /// <returns></returns>
        public static double Aacos(double v)
        {
            double av;
            if ((av = Math.abs(v)) >= 1)
            {
                if (av < ONE_TOL)
                {
                    return Double.NaN;
                    //ProjectionException(19);
                }
                return v < 0 ? Math.PI : 0;
            }
            return Math.acos(v);
        }

        /// <summary>
        /// Tollerant Sqrt
        /// </summary>
        /// <param name="v"></param>
        /// <returns></returns>
        public static double Asqrt(double v)
        {
            return ((v <= 0) ? 0 : Math.sqrt(v));
        }

        /// <summary>
        /// Tollerant Math.Atan method.
        /// </summary>
        /// <param name="n"></param>
        /// <param name="d"></param>
        /// <returns></returns>
        public static double Aatan2(double n, double d)
        {
            return ((Math.abs(n) < ATOL && Math.abs(d) < ATOL) ? 0 : Math.atan2(n, d));
        }

        /// <summary>
        /// Calculates the hypotenuse of a triangle: Sqrt(x*x + y*y);
        /// </summary>
        /// <param name="x">The length of one orthogonal leg of the triangle</param>
        /// <param name="y">The length of the other orthogonal leg of the triangle</param>
        /// <returns>The length of the diagonal.</returns>
        public static double Hypot(double x, double y)
        {
            return Math.sqrt(x * x + y * y);
        }

        /// <summary>
        ///
        /// </summary>
        /// <param name="lon"></param>
        /// <returns></returns>
        public static double Adjlon(double lon)
        {
            if (Math.abs(lon) <= Math.PI) return (lon);
            lon += Math.PI;  /* adjust to 0..2pi rad */
            lon -= 2 * Math.PI * Math.floor(lon / (2 * Math.PI)); /* remove integral # of 'revolutions'*/
            lon -= Math.PI;  /* adjust back to -pi..pi rad */
            return (lon);
        }

        /// <summary>
        /// Determines latitude from authalic latitude
        /// </summary>
        /// <param name="es">Epsilon squared</param>
        /// <returns>The array of double values for the apa parameter</returns>
        public static double[] Authset(double es)
        {
            double p00 = .33333333333333333333;
            double p01 = .17222222222222222222;
            double p02 = .10257936507936507936;
            double p10 = .06388888888888888888;
            double p11 = .06640211640211640211;
            double p20 = .01641501294219154443;
            int apaSize = 3;
            double[] apa = new double[apaSize];
            apa[0] = es * p00;
            double t = es * es;
            apa[0] += t * p01;
            apa[1] = t * p10;
            t *= es;
            apa[0] += t * p02;
            apa[1] += t * p11;
            apa[2] = t * p20;
            return apa;
        }

        /// <summary>
        /// Obtains the authalic latitude
        /// </summary>
        /// <param name="beta"></param>
        /// <param name="apa"></param>
        /// <returns></returns>
        public static double AuthLat(double beta, double[] apa)
        {
            double t = beta + beta;
            return (beta + apa[0] * Math.sin(t) + apa[1] * Math.sin(t + t) + apa[2] * Math.sin(t + t + t));
        }

        /// <summary>
        /// Obtains the EN parameters for the Meridional distance
        /// </summary>
        /// <param name="es"></param>
        /// <returns></returns>
        public static double[] Enfn(double es)
        {
            return MeridionalDistance.GetEn(es);
        }

        /// <summary>
        /// Meridonal length function
        /// </summary>
        /// <param name="phi"></param>
        /// <param name="sphi"></param>
        /// <param name="cphi"></param>
        /// <param name="en"></param>
        /// <returns></returns>
        public static double Mlfn(double phi, double sphi, double cphi, double[] en)
        {
            return MeridionalDistance.MeridionalLength(phi, sphi, cphi, en);
        }

        /// <summary>
        ///
        /// </summary>
        /// <param name="arg"></param>
        /// <param name="es"></param>
        /// <param name="en"></param>
        /// <returns></returns>
        public static double InvMlfn(double arg, double es, double[] en)
        {
            return MeridionalDistance.AngularDistance(arg, es, en);
        }

        /// <summary>
        ///
        /// </summary>
        /// <param name="sinphi"></param>
        /// <param name="e"></param>
        /// <param name="oneEs"></param>
        /// <returns></returns>
        public static double Qsfn(double sinphi, double e, double oneEs)
        {
            if (e >= 1E-7)
            {
                double con = e * sinphi;
                return (oneEs * (sinphi / (1 - con * con) - (.5 / e) * Math.log((1 - con) / (1 + con))));
            }
            return sinphi + sinphi;
        }

        /// <summary>
        ///
        /// </summary>
        /// <param name="phi"></param>
        /// <param name="sinphi"></param>
        /// <param name="e"></param>
        /// <returns></returns>
        public static double Tsfn(double phi, double sinphi, double e)
        {
            sinphi *= e;
            double result = (Math.tan(.5 * (Math.PI / 2 - phi))) / Math.pow((1 - sinphi) / (1 + sinphi), .5 * e);
            return result;
        }

        /// <summary>
        ///
        /// </summary>
        /// <param name="sinphi"></param>
        /// <param name="cosphi"></param>
        /// <param name="es"></param>
        /// <returns></returns>
        public static double Msfn(double sinphi, double cosphi, double es)
        {
            return (cosphi / Math.sqrt(1 - es * sinphi * sinphi));
        }

        /// <summary>
        ///
        /// </summary>
        /// <param name="ts"></param>
        /// <param name="e"></param>
        /// <returns></returns>
        public static double Phi2(double ts, double e)
        {
            double dphi;
            double tol = 1E-10;
            double eccnth = .5 * e;
            double phi = Math.PI / 2 - 2 * Math.atan(ts);
            int i = 15;
            do
            {
                double con = e * Math.sin(phi);
                dphi = Math.PI / 2 - 2 * Math.atan(ts * Math.pow((1 - con) / (1 + con), eccnth)) - phi;
                phi += dphi;
            } while ((Math.abs(dphi) > tol) && (--i > 0));
            if (i <= 0)
            {
                return Double.NaN;
                //ProjectionException(18);
            }
            return phi;
        }

        ///<summary>
        ///</summary>
        ///<param name="z"></param>
        ///<param name="c"></param>
        ///<param name="n"></param>
        ///<returns></returns>
        public static double[] Zpoly1(double[] z, double[][] c, int n)
        {
            double t;

            // make a copy of the array that we are going to modify and return.
            double[] a = Arrays.copyOf(c[n], c[n].length);
            while (n-- > 0)
            {
                a[R] = c[n][R] + z[R] * (t = a[R]) - z[I] * a[I];
                a[I] = c[n][I] + z[R] * a[I] + z[I] * t;
            }
            a[R] = z[R] * (t = a[R]) - z[I] * a[I];
            a[I] = z[R] * a[I] + z[I] * t;
            return a;
        }

        ///<summary>
        ///</summary>
        ///<param name="z"></param>
        ///<param name="c"></param>
        ///<param name="n"></param>
        ///<returns></returns>
        public static Zpolyd1Result Zpolyd1(double[] z, double[][] c, int n)
        {
            double t;
            double[] b = new double[2];
            boolean first = true;
            double[] a = c[n];
            while (n-- > 0)
            {
                if (first)
                {
                    first = false;
                    b = a;
                }
                else
                {
                    b[R] = a[R] + z[R] * (t = b[R]) - z[I] * b[I];
                    b[I] = a[I] + z[R] * b[I] + z[I] * t;
                }
                a[R] = c[n][R] + z[R] * (t = a[R]) - z[I] * a[I];
                a[I] = c[n][I] + z[R] * a[I] + z[I] * t;
            }
            b[R] = a[R] + z[R] * (t = b[R]) - z[I] * b[I];
            b[I] = a[I] + z[R] * b[I] + z[I] * t;
            a[R] = z[R] * (t = a[R]) - z[I] * z[I];
            a[I] = z[R] * a[I] + z[I] * t;
            Zpolyd1Result result = new Zpolyd1Result();
            
            result.der = b;
            result.result = a;
            return result;
        }
        
        public static class Zpolyd1Result{
            public double[] result;
            public double[] der;
        }
    }

    


