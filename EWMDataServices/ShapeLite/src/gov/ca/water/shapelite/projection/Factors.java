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

import java.util.EnumSet;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Factors {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    /**
     * max scale error
     */
    public double a;
    /**
     * min scale error
     */
    public double b;
    /**
     * Info as to analytics
     */
    public EnumSet<AnalyticModes> code;

    /**
     * Convergence
     */
    public double conv;

    /**
     * Meridinal scale
     */
    public double h;
    /**
     * parallel scale
     */
    public double k;
    /**
     * Angular distortion
     */
    public double omega;

    /**
     * Areal scale factor
     */
    public double s;

    /**
     * theta prime
     */
    public double thetap;

    /**
     * derivatives of x for lambda
     */
    public double xl;
    /**
     * derivatives of x for phi
     */
    public double xp;
    /**
     * derivatives of y for lambda
     */
    public double yl;
    /**
     * derivatives of y for phi
     */
    public double yp;

    //</editor-fold>
    /**
     * Creates a new instance of the Factors class.
     */
    public Factors() {

    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Properties">
    //</editor-fold>
}
