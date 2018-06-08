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

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class EllipticalTransform extends Transform {

    /**   {@inheritDoc }     */
    @Override
    protected void onForward(double[] lp, double[] xy, int startIndex, int numPoints) {
        if (IsElliptical) {
            ellipticalForward(lp, xy, startIndex, numPoints);
        } else {
            sphericalForward(lp, xy, startIndex, numPoints);
        }
    }

    /**
     * Calculates the forward transformation from geodetic lambda and phi coordinates to
     * linear xy coordinates for projections that have elliptical earth models.
     *
     * @param lp The input interleaved lambda-phi coordinates where lambda is longitude in
     * radians and phi is latitude in radians.
     * @param xy The resulting interleaved x-y coordinates
     * @param startIndex The zero based integer start index in terms of coordinate pairs
     * @param numPoints The integer number of xy pairs to evaluate.
     */
    protected void ellipticalForward(double[] lp, double[] xy, int startIndex, int numPoints) {
    }

    /**
     * Calculates the forward transformation from geodetic lambda and phi coordinates to
     * linear xy coordinates for projections that have spherical earth models.
     *
     * @param lp The input interleaved lambda-phi coordinates where lambda is longitude in
     * radians and phi is latitude in radians.
     * @param xy The resulting interleaved x-y coordinates
     * @param startIndex The zero based integer start index in terms of coordinate pairs
     * @param numPoints The integer number of xy pairs to evaluate.
     */
    protected void sphericalForward(double[] lp, double[] xy, int startIndex, int numPoints) {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void onInverse(double[] xy, double[] lp, int startIndex, int numPoints) {
        if (IsElliptical) {
            ellipticalInverse(xy, lp, startIndex, numPoints);
        } else {
            sphericalInverse(xy, lp, startIndex, numPoints);
        }
    }

    /**
     * Calculates the inverse transformation from linear xy coordinates to geodetic lambda
     * and phi coordinates for projections that have elliptical earth models.
     *
     * @param xy The input interleaved x-y coordinates
     * @param lp The output interleaved lambda-phi coordinates where lambda is longitude
     * in radians and phi is latitude in radians.
     * @param startIndex The zero based integer start index in terms of coordinate pairs
     * @param numPoints The integer number of xy pairs to evaluate.
     */
    protected void ellipticalInverse(double[] xy, double[] lp, int startIndex, int numPoints) {
    }

    /**
     * Calculates the inverse transformation from linear xy coordinates to geodetic lambda
     * and phi coordinates for projections that have spherical earth models.
     *
     * @param xy The input interleaved x-y coordinates
     * @param lp The output interleaved lambda-phi coordinates where lambda is longitude
     * in radians and phi is latitude in radians.
     * @param startIndex The zero based integer start index in terms of coordinate pairs
     * @param numPoints The integer number of xy pairs to evaluate.
     */
    protected void sphericalInverse(double[] xy, double[] lp, int startIndex, int numPoints) {
    }

}
