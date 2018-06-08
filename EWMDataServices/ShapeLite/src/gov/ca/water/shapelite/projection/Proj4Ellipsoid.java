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
public enum Proj4Ellipsoid {

    /**
     * Custom will use the a parameter for the major axis and the rf parameter for the
     * flattening divisor
     */
    Custom,
    /**
     * MERIT 1983
     */
    Merit_1983,
    /**
     * Soviet Geodetic System 85
     */
    SovietGeodeticSystem_1985,
    /**
     * Geodetic Reference System 1980(IUGG, 1980)
     */
    GRS_1980,
    /**
     * International Astronomical Union 1976
     */
    IAU_1976,
    /**
     * Sir George Biddell Airy 1830 (Britain)
     */
    Airy_1830,
    /**
     * App. Physics. 1965
     */
    AppPhysics_1965,
    /**
     * Naval Weapons Lab., 1965
     */
    NavalWeaponsLab_1965,
    /**
     * Modified Airy
     */
    AiryModified,
    /**
     * Andrae 1876 (Den., Iclnd.)
     */
    Andrae_1876,
    /**
     * Australian National and South American 1969
     */
    Austrailia_SouthAmerica,
    /**
     * Geodetic Reference System 67 (IUGG 1967)
     */
    GRS_1967,
    /**
     * Bessel 1841
     */
    Bessel_1841,
    /**
     * Bessel 1841 (Namibia)
     */
    BesselNamibia,
    /**
     * Clarke 1866
     */
    Clarke_1866,
    /**
     * Clarke 1880 Modified
     */
    ClarkeModified_1880,
    /**
     * Comm. des Poids et Mesures 1799
     */
    CPM_1799,
    /**
     * Delambre 1810 (Belgium)
     */
    Delambre_1810,
    /**
     * Engelis 1985
     */
    Engelis_1985,
    /**
     * Everest 1830
     */
    Everest_1830,
    /**
     * Everest 1948
     */
    Everest_1948,
    /**
     * Everest 1956
     */
    Everest_1956,
    /**
     * Everest 1969
     */
    Everest_1969,
    /**
     * Everest (Sabah and Sarawak)
     */
    Everest_SS,
    /**
     * Everest (Pakistan)
     */
    Everest_Pakistan,
    /**
     * Fischer (Mercury Datum) 1960
     */
    Fischer_1960,
    /**
     * Modified Fischer 1960
     */
    FischerModified_1960,
    /**
     * Fischer 1968
     */
    Fischer_1968,
    /**
     * Helmert 1906
     */
    Helmert_1906,
    /**
     * Hough
     */
    Hough,
    /**
     * Indonesian 1974
     */
    Indonesian_1974,
    /**
     * International 1909
     */
    International_1909,
    /**
     * Krassovsky 1942
     */
    Krassovsky_1942,
    /**
     * Kaula 1961
     */
    Kaula_1961,
    /**
     * Lerch 1979
     */
    Lerch_1979,
    /**
     * Maupertius 1738
     */
    Maupertius_1738,
    /**
     * New International 1967
     */
    InternationalNew_1967,
    /**
     * Plessis 1817 (France)
     */
    Plessis_1817,
    /**
     * Southeast Asia
     */
    SoutheastAsia,
    /**
     * Walbekc (Germany)
     */
    Walbeck,
    /**
     * World Geodetic System 1960
     */
    WGS_1960,
    /**
     * World Geodetic System 1966
     */
    WGS_1966,
    /**
     * World Geodetic System 1972
     */
    WGS_1972,
    /**
     * World Geodetic System 1984
     */
    WGS_1984,
    /**
     * Normal Sphere
     */
    Sphere

}
