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

import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Optional;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public enum AuxiliarySphereType {

    /**
     * Use semimajor axis or radius of the geographic coordinate system.
     */
    SemimajorAxis(0),
    /**
     * Use semiminor axis or radius.
     */
    SemiminorAxis(1),
    /**
     * Calculate and use authalic radius.
     */
    Authalic(2),
    /**
     * Use authalic radius and convert geodetic latitudes to authalic latitudes.
     */
    AuthalicWithConvertedLatitudes(3),
    /**
     * This indicates that this parameter should not appear in the projection string.
     */
    NotSpecified(4);

    /**
     * The integer index of the axis.
     */
    private final int index;

    /**
     * Creates a new instance of the AuxiliarySphereType, defining an index value.
     * <ul>
     * <li>0 - SemimajorAxis</li>
     * <li>1 - SemiminorAxis</li>
     * <li>2 - Authalic</li>
     * <li>3 - Authalic with converted latitudes</li>
     * <li>4 - NotSpecified</li>
     * </ul>
     *
     * @param index The integer index.
     */
    private AuxiliarySphereType(int index) {
        this.index = index;
    }

    /**
     * Attempts to find the matching Auxiliary sphere type based on its numeric index.
     *
     * @param index The integer index to parse.
     * @return The optional AuxiliarySphere type, which can be empty,
     * but will not be null.
     */
    @NonNull
    public static Optional<AuxiliarySphereType> parse(int index) {
        for (AuxiliarySphereType type : AuxiliarySphereType.values()) {
            if (type.getIndex() == index) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }

    /**
     * The integer index of the sphere type.
     * @return the index
     */
    public int getIndex() {
        return index;
    }


}
