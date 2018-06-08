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

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Spheroid extends CopyObject {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private String name; // term 0
    private Double equatorialRadius; // term 1
    private String code;
    private Double polarRadius;
    private Proj4Ellipsoid knownEllipsoid;
    private static final HashMap<Proj4Ellipsoid, String> proj4Names;

    private static final Logger LOGGER = Logger.getLogger(Spheroid.class.getName());
    
    static {
        proj4Names = new HashMap<>();
        proj4Names.put(Proj4Ellipsoid.Airy_1830, "airy");
        proj4Names.put(Proj4Ellipsoid.AiryModified, "mod_airy");
        proj4Names.put(Proj4Ellipsoid.Andrae_1876, "andrae");
        proj4Names.put(Proj4Ellipsoid.AppPhysics_1965, "APL4.9");
        proj4Names.put(Proj4Ellipsoid.Austrailia_SouthAmerica, "aust_SA");
        proj4Names.put(Proj4Ellipsoid.Bessel_1841, "bessel");
        proj4Names.put(Proj4Ellipsoid.BesselNamibia, "bess_nam");
        proj4Names.put(Proj4Ellipsoid.Clarke_1866, "clrk66");
        proj4Names.put(Proj4Ellipsoid.ClarkeModified_1880, "clrk80");
        proj4Names.put(Proj4Ellipsoid.CPM_1799, "CPM");
        proj4Names.put(Proj4Ellipsoid.Custom, "");
        proj4Names.put(Proj4Ellipsoid.Delambre_1810, "delmbr");
        proj4Names.put(Proj4Ellipsoid.Engelis_1985, "engelis");
        proj4Names.put(Proj4Ellipsoid.Everest_1830, "evrst30");
        proj4Names.put(Proj4Ellipsoid.Everest_1948, "evrst48");
        proj4Names.put(Proj4Ellipsoid.Everest_1956, "evrst56");
        proj4Names.put(Proj4Ellipsoid.Everest_1969, "evrst69");
        proj4Names.put(Proj4Ellipsoid.Everest_SS, "evrstSS");
        proj4Names.put(Proj4Ellipsoid.Fischer_1960, "fschr60");
        proj4Names.put(Proj4Ellipsoid.FischerModified_1960, "fschr60m");
        proj4Names.put(Proj4Ellipsoid.Fischer_1968, "fschr68");
        proj4Names.put(Proj4Ellipsoid.GRS_1967, "GRS67");
        proj4Names.put(Proj4Ellipsoid.GRS_1980, "GRS80");
        proj4Names.put(Proj4Ellipsoid.Helmert_1906, "helmert");
        proj4Names.put(Proj4Ellipsoid.Hough, "hough");
        proj4Names.put(Proj4Ellipsoid.IAU_1976, "IAU76");
        proj4Names.put(Proj4Ellipsoid.International_1909, "intl");
        proj4Names.put(Proj4Ellipsoid.InternationalNew_1967, "new_intl");
        proj4Names.put(Proj4Ellipsoid.Krassovsky_1942, "krass");
        proj4Names.put(Proj4Ellipsoid.Lerch_1979, "lerch");
        proj4Names.put(Proj4Ellipsoid.Maupertius_1738, "mprts");
        proj4Names.put(Proj4Ellipsoid.Merit_1983, "MERIT");
        proj4Names.put(Proj4Ellipsoid.NavalWeaponsLab_1965, "NWL9D");
        proj4Names.put(Proj4Ellipsoid.Plessis_1817, "plessis");
        proj4Names.put(Proj4Ellipsoid.SoutheastAsia, "SEasia");
        proj4Names.put(Proj4Ellipsoid.SovietGeodeticSystem_1985, "SGS85");
        proj4Names.put(Proj4Ellipsoid.Sphere, "sphere");
        proj4Names.put(Proj4Ellipsoid.Walbeck, "walbeck");
        proj4Names.put(Proj4Ellipsoid.WGS_1960, "WGS60");
        proj4Names.put(Proj4Ellipsoid.WGS_1966, "WGS66");
        proj4Names.put(Proj4Ellipsoid.WGS_1972, "WGS72");
        proj4Names.put(Proj4Ellipsoid.WGS_1984, "WGS84");

    }

    @Override
    public Spheroid copy() {
        return (Spheroid)super.copy();
    }
  
    
  

    /// <summary>
    /// Given the enumeration of known ellipsoids, this will redefine this spheroid
    /// so that it matches the A and B coefficients for the known ellipsoids.
    /// </summary>
    /// <param name="knownEllipse">The known Ellipse</param>
    public final void assignKnownEllipsoid(Proj4Ellipsoid knownEllipse) {
        name = knownEllipse.toString();
        knownEllipsoid = knownEllipse;
        switch (knownEllipse) {
            case Airy_1830:
                equatorialRadius = 6377563.396;
                setPolarRadius((Double) 6356256.910);
                code = "AA";
                break;
            case AiryModified:
                equatorialRadius = 6377340.189;
                setPolarRadius((Double) 6356034.446);
                code = "AM";
                break;
            case Andrae_1876:
                equatorialRadius = 6377104.43;
                setInverseFlattening(300.0);
                break;
            case AppPhysics_1965:
                equatorialRadius = 6378137.0;
                setInverseFlattening(298.25);
                break;
            case Austrailia_SouthAmerica:
                equatorialRadius = 6378160.0;
                setInverseFlattening(298.25);
                code = "AN";
                break;
            case Bessel_1841:
                equatorialRadius = 6377397.155;
                setInverseFlattening(299.1528128);
                code = "BR";
                break;
            case BesselNamibia:
                equatorialRadius = 6377483.865;
                setInverseFlattening(299.1528128);
                code = "BN";
                break;
            case Clarke_1866:
                equatorialRadius = 6378206.4;
                setPolarRadius((Double) 6356583.8);
                code = "CC";
                break;
            case ClarkeModified_1880:
                equatorialRadius = 6378249.145;
                setInverseFlattening(293.4663);
                code = "CD";
                break;
            case CPM_1799:
                equatorialRadius = 6375738.7;
                setInverseFlattening(334.29);
                break;
            case Custom: // Default to WGS84
                equatorialRadius = 6378137.0;
                setInverseFlattening(298.257223563);
                break;
            case Delambre_1810:
                equatorialRadius = 6376428.0;
                setInverseFlattening(311.5);
                break;
            case Engelis_1985:
                equatorialRadius = 6378136.05;
                setInverseFlattening(298.2566);
                break;
            case Everest_1830:
                equatorialRadius = 6377276.345;
                setInverseFlattening(300.8017);
                code = "EA";
                break;
            case Everest_1948:
                equatorialRadius = 6377304.063;
                setInverseFlattening(300.8017);
                code = "EE";
                break;
            case Everest_1956:
                equatorialRadius = 6377301.243;
                setInverseFlattening(300.8017);
                code = "EC";
                break;
            case Everest_1969:
                equatorialRadius = 6377295.664;
                setInverseFlattening(300.8017);
                code = "ED";
                break;
            case Everest_Pakistan:
                equatorialRadius = 6377309.613;
                setInverseFlattening(300.8017);
                code = "EF";
                break;
            case Everest_SS:
                equatorialRadius = 6377298.556;
                setInverseFlattening(300.8017);
                code = "EB";
                break;
            case Fischer_1960:
                equatorialRadius = 6378166.0;
                setInverseFlattening(298.3);
                break;
            case Fischer_1968:
                equatorialRadius = 6378150.0;
                setInverseFlattening(298.3);
                break;
            case FischerModified_1960:
                equatorialRadius = 6378155.0;
                setInverseFlattening(298.3);
                code = "FA";
                break;
            case GRS_1967:
                equatorialRadius = 6378160.0;
                setInverseFlattening(298.2471674270);
                break;
            case GRS_1980:
                equatorialRadius = 6378137.0;
                setInverseFlattening(298.257222101);
                code = "RF";
                break;
            case Helmert_1906:
                equatorialRadius = 6378200.0;
                setInverseFlattening(298.3);
                code = "HE";
                break;
            case Hough:
                equatorialRadius = 6378270.0;
                setInverseFlattening(297.0);
                code = "HO";
                break;
            case IAU_1976:
                equatorialRadius = 6378140.0;
                setInverseFlattening(298.257);
                break;
            case Indonesian_1974:
                equatorialRadius = 6378160.0;
                setInverseFlattening(298.247);
                code = "ID";
                break;
            case International_1909:
                equatorialRadius = 6378388.0;
                setInverseFlattening(297.0);
                code = "IN";
                break;
            case InternationalNew_1967:
                equatorialRadius = 6378157.5;
                setPolarRadius((Double) 6356772.2);
                break;
            case Krassovsky_1942:
                equatorialRadius = 6378245.0;
                setInverseFlattening(298.3);
                code = "KA";
                break;
            case Kaula_1961:
                equatorialRadius = 6378163.0;
                setInverseFlattening(298.24);
                break;
            case Lerch_1979:
                equatorialRadius = 6378139.0;
                setInverseFlattening(298.257);
                break;
            case Maupertius_1738:
                equatorialRadius = 6397300.0;
                setInverseFlattening(191.0);
                break;
            case Merit_1983:
                equatorialRadius = 6378137.0;
                setInverseFlattening(298.257);
                break;
            case NavalWeaponsLab_1965:
                equatorialRadius = 6378145.0;
                setInverseFlattening(298.25);
                break;
            case Plessis_1817:
                equatorialRadius = 6376523.0;
                setPolarRadius((Double) 6355863.0);
                break;
            case SoutheastAsia:
                equatorialRadius = 6378155.0;
                setPolarRadius((Double) 6356773.3205);
                break;
            case SovietGeodeticSystem_1985:
                equatorialRadius = 6378136.0;
                setInverseFlattening(298.257);
                break;
            case Sphere:
                equatorialRadius = 6370997.0;
                setPolarRadius((Double) 6370997.0);
                break;
            case Walbeck:
                equatorialRadius = 6376896.0;
                setPolarRadius((Double) 6355834.8467);
                break;
            case WGS_1960:
                name = "WGS_1960";
                equatorialRadius = 6378165.0;
                setInverseFlattening(298.3);
                break;
            case WGS_1966:
                name = "WGS_1966";
                equatorialRadius = 6378145.0;
                setInverseFlattening(298.25);
                break;
            case WGS_1972:
                name = "WGS_1972";
                code = "WD";
                equatorialRadius = 6378135.0;
                setInverseFlattening(298.26);
                break;
            case WGS_1984:
                name = "WGS_1984";
                code = "WE";
                equatorialRadius = 6378137.0;
                setInverseFlattening(298.257223563);
                break;
        }
    }

    //</editor-fold>
    /**
     * Creates a new instance of the Spheroid class.
     */
    public Spheroid() {
        assignKnownEllipsoid(Proj4Ellipsoid.WGS_1984);
    }

    /**
     * Creates a new spheroid using an the equatorial radius in meters and a flattening
     * coefficient that is the inverse flattening factor. eg. for WGS84 (6378137.0,
     * 298.257223563)
     *
     * @param equatorialRadius
     * @param inverseFlattening
     */
    public Spheroid(double equatorialRadius, double inverseFlattening) {
        this.equatorialRadius = equatorialRadius;
        this.doSetInverseFlattening(inverseFlattening);
        assignKnownEllipsoid(Proj4Ellipsoid.WGS_1984);
    }

    /**
     * For perfect spheres, you just need to specify one radius, which will be applied to
     * both radii. You can then directly change the polar or equatorial radius if
     * necessary using the properties.
     *
     * @param radius
     */
    public Spheroid(double radius) {
        polarRadius = radius;
        equatorialRadius = radius;
    }

    /**
     * The ellps parameter in a proj4 string will only work with certain pre-defined
     * spheroids, enumerated in the Proj4Ellipsoids enumeration. Custom spheroids can be
     * specified but will use the a and b parameters when creating a proj4 parameter
     * instead of using the ellps parameter.
     *
     * @param knownEllipse
     */
    public Spheroid(Proj4Ellipsoid knownEllipse) {
        assignKnownEllipsoid(knownEllipse);
    }

    /**
     * Given the proj4 code, this will set the radii correctly.
     *
     * @param proj4Ellips
     */
    public Spheroid(String proj4Ellips) {
        for (Entry<Proj4Ellipsoid, String> pair : proj4Names.entrySet()) {
            if (pair.getValue().equals(proj4Ellips)) {
                assignKnownEllipsoid(pair.getKey());
                return;
            }
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    

    /**
     * Calculates the flattening factor, (a - b) / a.
     *
     * @return
     */
    public double getFlatteningFactor() {
       if(equatorialRadius == null || polarRadius == null){
           return 0;
       }
       return (equatorialRadius - polarRadius) / equatorialRadius;
    }

    /**
     * Calculates the eccentrity according to e = sqrt(2f - f^2) where f is the flattening
     * factor.
     *
     * @return
     */
    public double getEccentricity() {
        double f = getFlatteningFactor();
        return Math.sqrt(2 * f - f * f);
    }

    /**
     * Calculates the square of eccentricity according to es = (2f - f^2) where f is the
     * flattening factor.
     *
     * @return
     */
    public double getEccentricitySquared() {
        double f = getFlatteningFactor();
        return 2 * f - f * f;
    }

    /**
     * @return the inverseFlattening
     */
    public Double getInverseFlattening() {
        if (getPolarRadius().equals(equatorialRadius)) {
            return 0.0;
        }
        return equatorialRadius / (equatorialRadius - getPolarRadius());
    }

    /**
     * @param inverseFlattening the inverseFlattening to set
     */
    public void setInverseFlattening(Double inverseFlattening) {
        doSetInverseFlattening(inverseFlattening);
    }

    private void doSetInverseFlattening(Double inverseFlattening) {
        if (inverseFlattening == 0 || inverseFlattening == null) {
            setPolarRadius(equatorialRadius);
        } else {
            setPolarRadius(equatorialRadius - equatorialRadius / inverseFlattening);
        }
    }

    /**
     * Gets a boolean that is true if the spheroid has been flattened.
     *
     * @return
     */
    public boolean isOblate() {
        return (getPolarRadius() < equatorialRadius);
    }

    /**
     * Reads the Esri string to define the spheroid, which controls how flattened the
     * earth's radius is
     *
     * @param esriString
     */
    public void parseEsriString(String esriString) throws ProjectionException {
        if (esriString.contains("SPHEROID") == false) {
            return;
        }
        int iStart = esriString.indexOf("SPHEROID") + 9;
        int iEnd = esriString.indexOf("]", iStart);
        if (iEnd < iStart) {
            return;
        }
        String extracted = esriString.substring(iStart, iEnd);
        String[] terms = extracted.split(",");
        name = terms[0];
        name = name.substring(1, name.length() - 1);
        if (terms.length > 1) {
            try {
                equatorialRadius = USLocale.parseDouble(terms[1]);
            } catch (ParseException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
                throw new ProjectionException("Spheroid radius parse exception.", ex);
            }
        }
        if (terms.length > 2) {
            try {
                setInverseFlattening(USLocale.parseDouble(terms[2]));
            } catch (ParseException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
                throw new ProjectionException("Spheroid length parse exception.", ex);
            }
        }

    }

    /**
     * Gets the string expression that represents this spheroid.
     *
     * @return
     */
    public String toEsriString() {
        return "SPHEROID[\"" + name + "\"," + USLocale.format(equatorialRadius, 1)
                + "," + USLocale.format(getInverseFlattening(), 9) + "]";
    }

    /// <summary>
    /// Uses the current known ellipsoid to return a code name for the proj4 string if possible.
    /// Otherwise, this returns the radial parameters a and b.
    /// </summary>
    /// <returns></returns>
    public String toProj4String() {
        if (knownEllipsoid == Proj4Ellipsoid.Custom) {
            return " +a=" + USLocale.format(equatorialRadius) + " +b=" + USLocale.format(getPolarRadius());
        } else {
            return " +ellps=" + proj4Names.get(knownEllipsoid);
        }

    }

   

    
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Properties">
    
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the equatorialRadius
     */
    public Double getEquatorialRadius() {
        return equatorialRadius;
    }

    /**
     * @param equatorialRadius the equatorialRadius to set
     */
    public void setEquatorialRadius(Double equatorialRadius) {
        this.equatorialRadius = equatorialRadius;
    }
    
     /**
     * @return the polarRadius
     */
    public Double getPolarRadius() {
        return polarRadius;
    }

    /**
     * @param polarRadius the polarRadius to set
     */
    public void setPolarRadius(Double polarRadius) {
        this.polarRadius = polarRadius;
    }
    
    //</editor-fold>
}
