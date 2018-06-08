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
package gov.ca.water.shapelite.projection;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MeridionalDistance {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private static final double C00 = 1;
    private static final double C02 = 0.25;
    private static final double C04 = .046875;
    private static final double C06 = .01953125;
    private static final double C08 = .01068115234375;
    private static final double C22 = .75;
    private static final double C44 = .46875;
    private static final double C46 = .01302083333333333333;
    private static final double C48 = .00712076822916666666;
    private static final double C66 = .36458333333333333333;
    private static final double C68 = .00569661458333333333;
    private static final double C88 = .3076171875;
    private static final double EPS = 1e-11;
    private static final int MAX_ITER = 10;
    private static final int EN_SIZE = 5;

    //</editor-fold>
    /**
     * Creates a new instance of the MeridionalDistance class.
     */
    public MeridionalDistance() {

    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Formerly pj_enfn from Proj4
     *
     * @param es
     * @return
     */
    public static double[] GetEn(double es) {
        double t;
        double[] en = new double[EN_SIZE];
        en[0] = C00 - es * (C02 + es * (C04 + es * (C06 + es * C08)));
        en[1] = es * (C22 - es * (C04 + es * (C06 + es * C08)));
        en[2] = (t = es * es) * (C44 - es * (C46 + es * C48));
        en[3] = (t *= es) * (C66 - es * C68);
        en[4] = t * es * C88;
        return en;
    }

    /**
     * Formerly pj_mlfn Given geodetic angular displacement phi, this calculates the
     * equivalent meridional distance
     *
     * @param phi
     * @param sphi
     * @param cphi
     * @param en
     * @return
     */
    public static double MeridionalLength(double phi, double sphi, double cphi, double[] en) {
        cphi *= sphi;
        sphi *= sphi;
        return (en[0] * phi - cphi * (en[1] + sphi * (en[2] + sphi * (en[3] + sphi * en[4]))));
    }

    /**
     * Formerly pj_inv_mlfn /// Given the linear distance, this calculates the equivalent
     * geodetic angular displacement
     *
     * @param arg
     * @param es
     * @param en
     * @return
     */
    public static double AngularDistance(double arg, double es, double[] en) {
        double k = 1 / (1 - es);
        double phi = arg;
        for (int i = MAX_ITER; i > 0; --i) { /* rarely goes over 2 iterations */

            double s = Math.sin(phi);
            double t = 1 - es * s * s;
            phi -= t = (MeridionalLength(phi, s, Math.cos(phi), en) - arg) * (t * Math.sqrt(t)) * k;
            if (Math.abs(t) < EPS) {
                return phi;
            }
        }
        return phi;

    }

         //</editor-fold>
}
