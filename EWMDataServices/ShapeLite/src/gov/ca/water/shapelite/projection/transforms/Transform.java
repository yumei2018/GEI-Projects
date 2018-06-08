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

import gov.ca.water.shapelite.projection.CopyObject;
import gov.ca.water.shapelite.projection.Factors;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Transform extends CopyObject implements Transformable{

    //<editor-fold defaultstate="collapsed" desc="Constants">
    /**
     * Pi/2
     */
    protected static final double HALF_PI = 1.5707963267948966; //= Math.PI/2;
    /**
     * Math.Pi / 4
     */
    protected static final double FORT_PI = 0.78539816339744833; //= Math.PI/4;
    /**
     * 2 * Math.Pi
     */
    protected static final double TWO_PI = Math.PI * 2;
    /**
     * 1E-10
     */
    protected static final double EPS10 = 1E-10;
    /**
     * Analytic Hk
     */
    protected static final int IS_ANAL_HK = 4;
    /**
     * Analytic Conv
     */
    protected static final int IS_ANAL_CONV = 10;
    /**
     * Analytic Xl Yl
     */
    protected static final int IS_ANAL_XL_YL = 1;
    /**
     * Analytic Xp Yp
     */
    protected static final int IS_ANAL_XP_YP = 2;
    /**
     * Radians to Degrees
     */
    protected static final double RAD_TO_DEG = 57.29577951308232;
    /**
     * Degrees to Radians
     */
    protected static final double DEG_TO_RAD = .0174532925199432958;

    /**
     * The integer index representing lambda values in arrays representing geodetic
     * coordinates
     */
    protected static final int LAMBDA = 0;
    /**
     * The integer index representing phi values in arrays representing geodetic
     * coordinates
     */
    protected static final int PHI = 1;
    /**
     * The integer index representing X values in arrays representing linear coordinates
     */
    protected static final int X = 0;
    /**
     * The integer index representing Y values in arrays representing linear coordinates
     */
    protected static final int Y = 1;

    /**
     * The integer index representing real values in arrays representing complex numbers
     */
    protected static final int R = 0;
    /**
     * The integer index representing immaginary values in arrays representing complex
     * numbers
     */
    protected static final int I = 1;

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private String esriName;
    private String[] esriAliases;
    private String[] proj4Aliases;
    private String proj4Name;

    /**
     * The major axis
     */
    protected double A;

    /**
     * 1/a
     */
    protected double Ra;

    /**
     * 1 - e^2;
     */
    protected double OneEs;

    /**
     * 1/OneEs
     */
    protected double ROneEs;

    /**
     * Eccentricity
     */
    protected double E;

    /**
     * True if the spheroid is flattened
     */
    protected boolean IsElliptical;

    /**
     * Eccentricity Squared
     */
    protected double Es;

    /**
     * Central Latitude
     */
    protected double Phi0; // central latitude

    /**
     * Central Longitude
     */
    protected double Lam0; // central longitude

    /**
     * False Easting
     */
    protected double X0; // easting

    /**
     * False Northing
     */
    protected double Y0; // northing

    /**
     * Scaling Factor
     */
    protected double K0; // scaling factor

    /**
     * Scaling to meter
     */
    protected double ToMeter; // cartesian scaling

    /**
     * Scaling from meter
     */
    protected double FromMeter; // cartesian scaling from meter

    /**
     * The B parameter, which should be consisten with the semi-minor axis
     */
    protected double B;

    //</editor-fold>
    /**
     * Creates a new instance of the Transform class.
     */
    public Transform() {

    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Properties">
    /**
     * @return the esriName
     */
    @Override
    public String getEsriName() {
        return esriName;
    }

    /**
     * @param esriName the esriName to set
     */
    @Override
    public void setEsriName(String esriName) {
        this.esriName = esriName;
    }

    /**
     * @return the _proj4Aliases
     */
    @Override
    public String[] getProj4Aliases() {
        return proj4Aliases;
    }

    /**
     * @param _proj4Aliases the _proj4Aliases to set
     */
    public void setProj4Aliases(String[] proj4Aliases) {
        this.proj4Aliases = proj4Aliases;
    }

    /**
     * @return the _proj4Name
     */
    @Override
    public String getProj4Name() {
        return proj4Name;
    }

    /**
     * @param _proj4Name the _proj4Name to set
     */
    @Override
    public void setProj4Name(String proj4Name) {
        this.proj4Name = proj4Name;
    }

    //</editor-fold>
    /**
     * @return the esriAliases
     */
    @Override
    public String[] getEsriAliases() {
        return esriAliases;
    }

    /**
     * @param esriAliases the esriAliases to set
     */
    public void setEsriAliases(String[] esriAliases) {
        this.esriAliases = esriAliases;
    }


    @Override
    public Transform copy(){
        return (Transform)super.copy();
    }

    /**
     * For transforms that distinguish between polar, oblique and equatorial modes
     */
    protected enum Modes {

        /**
         * North Pole
         */
        NorthPole,
        /**
         * South Pole
         */
        SouthPole,
        /**
         * Equatorial
         */
        Equatorial,
        /**
         * Oblique
         */
        Oblique;
    }

    /**
     * Initializes the parameters from the projection info
     *
     * @param projectionInfo The projection information used to control this transform
     * @throws ProjectionException
     */
    @Override
    public void init(ProjectionInfo projectionInfo) throws ProjectionException {
        // Setup protected values common to all the projections that inherit from this projection
        Es = projectionInfo.getGeographicInfo().getDatum().getSpheroid().getEccentricitySquared();
        if (projectionInfo.getLatitudeOfOrigin() != null) {
            Phi0 = projectionInfo.getPhi0();
        }
        if (projectionInfo.getCentralMeridian() != null) {
            Lam0 = projectionInfo.getLam0();
        }
        if (projectionInfo.getFalseEasting() != null) {
            X0 = projectionInfo.getFalseEasting();
        }
        if (projectionInfo.getFalseNorthing() != null) {
            Y0 = projectionInfo.getFalseNorthing();
        }
        K0 = projectionInfo.getScaleFactor();
        A = projectionInfo.getGeographicInfo().getDatum().getSpheroid().getEquatorialRadius();
        B = projectionInfo.getGeographicInfo().getDatum().getSpheroid().getPolarRadius();
        E = projectionInfo.getGeographicInfo().getDatum().getSpheroid().getEccentricity();
        Ra = 1 / A;
        OneEs = 1 - Es;
        ROneEs = 1 / OneEs;

        //_datumParams = proj.GeographicInfo.Datum.ToWGS84;
        if (projectionInfo.getUnit() != null) {
            ToMeter = projectionInfo.getUnit().getMeters();
            FromMeter = 1 / projectionInfo.getUnit().getMeters();
        } else {
            ToMeter = 1;
            FromMeter = 1;
        }

        if (Es != 0) {
            IsElliptical = true;
        }
        onInit(projectionInfo);
    }

    /**
     * Transforms all the coordinates by cycling through them in a loop, transforming each
     * one. Only the 0 and 1 values of each coordinate will be transformed, higher
     * dimensional values will be copied.
     *
     * @param lp
     * @param startIndex
     * @param numPoints
     */
    @Override
    public void forward(double[] lp, int startIndex, int numPoints) {
        double[] xy = new double[lp.length];
        onForward(lp, xy, startIndex, numPoints);

        System.arraycopy(xy, startIndex * 2, lp, startIndex * 2, numPoints * 2);
    }

    /**
     * Transforms all the coordinates by cycling through them in a loop, transforming each
     * one. Only the 0 and 1 values of each coordinate will be transformed, higher
     * dimensional values will be copied.
     *
     * @param xy
     * @param startIndex
     * @param numPoints
     */
    @Override
    public void inverse(double[] xy, int startIndex, int numPoints) {
        double[] lp = new double[xy.length];
        onInverse(xy, lp, startIndex, numPoints);
        System.arraycopy(lp, startIndex * 2, xy, startIndex * 2, numPoints * 2);
    }

    /**
     * special factor calculations for a factors calculation
     *
     * @param lp
     * @param p
     * @param fac
     */
    @Override
    public void special(double[] lp, ProjectionInfo p, Factors fac) {
        onSpecial(lp, p, fac);
    }

    /**
     * Uses an enumeration to generate a new instance of a known transform class
     *
     * @param value
     * @return
     */
    public static Transformable fromKnownTransform(KnownTransform value) {
        String name = value.toString();
        for (Transformable transform : TransformManager.getInstance().getTransforms()) {
            if (transform.getEsriName().equals(name)) {
                return transform.copy();
            }
        }
        return null;
    }

    /**
     * Allows for some custom code during a process method
     *
     * @param lp lambda-phi
     * @param p The projection coordinate system information
     * @param fac The Factors
     */
    protected void onSpecial(double[] lp, ProjectionInfo p, Factors fac) {
            // some projections support this as part of a process routine,
        // this will not affect forward or inverse transforms
    }

   
    /**
     * Initializes the transform using the parameters from the specified coordinate system
     * information
     * @param projInfo A ProjectionInfo class contains all the standard and custom parameters needed to initialize this transform
     * @exception ProjectionException if there is a problem initializing the projection.
     */
    protected void onInit(ProjectionInfo projInfo) throws ProjectionException {
    }

    
    /**
     * Transforms cartesian x, y to geodetic lambda, phi
     * @param lp in -> the lambda, phi coordinates
     * @param xy out -> the cartesian x, y
     * @param startIndex The zero based starting point index.  If this is 1, for instance, reading will skip the x and y of the first point and start at the second point.
     * @param numPoints The integer count of the pairs of xy values to consider.
     */
    protected void onForward(double[] lp, double[] xy, int startIndex, int numPoints) {
    }

    
    /**
     * The inverse transform from linear units to geodetic units
     * @param xy The double values for the input x and y values stored in an array
     * @param lp The double values for the output lambda and phi values stored in an array
     * @param startIndex The zero based starting point index.  If this is 1, for instance,
     * reading will skip the x and y of the first point and start at the second point.
     * @param numPoints The integer count of the pairs of xy values to consider
     */
    protected void onInverse(double[] xy, double[] lp, int startIndex, int numPoints) {
    }

}
