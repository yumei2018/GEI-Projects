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

import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.projection.transforms.LongLat;
import gov.ca.water.shapelite.projection.transforms.TransformManager;
import gov.ca.water.shapelite.projection.transforms.Transformable;
import gov.ca.water.shapelite.projection.transforms.UniversalTransverseMercator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ProjectionInfo extends CopyObject {

  /**
   * If no projection is specified, we will assume that the projection is WGS84.
   */
  private static final ProjectionInfo DEFAULT
      = Projections.getGeographic().getWorld().getWGS1984();

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * Logger.
   */
  private static final Logger LOGGER = Logger.getLogger(ProjectionInfo.class.getName());

  /**
   * @return the DEFAULT
   */
  public static ProjectionInfo getDefault() {
    return DEFAULT.copy();
  }

  public static Optional<ProjectionInfo> fromEsriStringOpt(String esriString) {
    Optional<ProjectionInfo> result = Optional.empty();
    if (esriString == null || esriString.isEmpty()) {
      // Return a default 'empty' projection
      return result;
    }
    //special case for Krovak Projection
    //use a lookup table instead of hard coding the projection here
    if (esriString.contains("Krovak")) {
      result = Optional.of(Projections.getProjected().getNationalGrids().getSJTSKKrovakEastNorth());
      return result;
    }

    ProjectionInfo info = new ProjectionInfo();

    info.noDefs = true;
    if (!info.tryParseEsriString(esriString)) {
      return result;
    }
    if (info.isLatLon()) {
      String name = info.getGeographicInfo().getName();
      if (Projections.getGeocsEpsg().containsKey(name)) {
        info.epsgCode = Projections.getGeocsEpsg().get(name);
      }
    } else {
      String name = info.getName();
      if (Projections.getProjcsEpsg().containsKey(name)) {
        info.epsgCode = Projections.getProjcsEpsg().get(info.getName());
      }

    }
    result = Optional.of(info);
    return result;
  }

  private GeographicInfo geographicInfo;

  private Double longitudeOf1st;

  /**
   * Longitude of first digits preserved past the decimal.
   */
  private int longitudeOf1stDigits;

  private Double longitudeOf2nd;

  /**
   * Longitude of second digits preserved past the decimal.
   */
  private int longitudeOf2ndDigits;

  private Double scaleFactor;

  /**
   * The integer number of digits past the decimal to preserve.
   */
  private int scaleFactorDigits = -1;

  private String longitudeOfCenterAlias;

  /**
   * stores the value of the actual parameter name that was used in the original
   * (when the string came from WKT/Esri)
   */
  private String latitudeOfOriginAlias;

  private String falseEastingAlias;

  private String falseNorthingAlias;

  private LinearUnit unit;

  /**
   * The auxiliary sphere type.
   */
  private AuxiliarySphereType auxiliarySphereType;

  /**
   * The athority, for example EPSG.
   */
  private String authority;

  /**
   * The horizontal 0 point in geographic terms.
   */
  private Double centralMeridian;

  /**
   * The digits for the central meridian.
   */
  private int centralMeridianDigits = -1;

  /**
   * The Reference Code.
   */
  private int epsgCode;

  /**
   * The false easting for this coordinate system.
   */
  private Double falseEasting;

  /**
   * The digits to preserve for false easting. If this is less than 0, then no
   * formatting is done.
   */
  private int falseEastingDigits = -1;

  /**
   * The false northing for this coordinate system.
   */
  private Double falseNorthing;

  /**
   * The digits to preserve for false northing. If this is less to 0 then no
   * formatting is done.
   */
  private int falseNorthingDigits = -1;

  /**
   * a boolean indicating a geocentric latitude parameter.
   */
  private boolean geoc;

  /**
   * a boolean that indicates whether or not this is geocentric.
   */
  private boolean geocentric;

  /**
   * True if this coordinate system is expressed only using geographic
   * coordinates.
   */
  private boolean latLon;

  /**
   * Gets or sets a boolean indicating whether this projection applies to the
   * southern coordinate system or not.
   */
  private boolean south;

  /**
   * True if the transform is defined. That doesn't really mean it accurately
   * represents the named projection, but rather it won't throw a null exception
   * during transformation for the lack of a transform definition.
   *
   * @return Boolean, true if the transform is not null.
   */
  public final boolean isValid() {

    return (this.transform != null);

  }

  /**
   * The zero point in geographic terms.
   */
  private Double latitudeOfOrigin;

  /**
   * The integer digits past the decimal to preserve.
   */
  private int latitudeOfOriginDigits = -1;

  /**
   * The longitude of center for this coordinate system.
   */
  public Double longitudeOfCenter;

  /**
   * The integer number of digits to preserve for longitude of center.
   */
  private int longitudeOfCenterDigits = -1;

  /**
   * The M.
   */
  private Double m;

  /**
   * The name of this projection information.
   */
  private String name;

  /**
   * A boolean that indicates whether to use the /usr/share/proj/proj_def.dat
   * defaults file (proj4 parameter "no_defs").
   */
  public boolean noDefs;

  /**
   * a boolean for the over-ranging flag
   */
  public boolean over;

  /**
   * Gets the scale factor for this coordinate system.
   *
   * @return
   */
  public double getScaleFactor() {
    if (scaleFactor != null) {
      return scaleFactor;
    }
    return 1;
  }

  /**
   * Sets the scale factor for this coordinate system.
   *
   * @param value
   */
  public void setScaleFactor(double value) {
    scaleFactor = value;
  }

  /**
   * The line of latitude where the scale information is preserved.
   */
  public Double standardParallel1;

  /**
   * The integer digits past the decimal to preserve.
   */
  private int standardParallel1Digits = -1;

  /**
   * The standard parallel 2.
   */
  public Double standardParallel2;

  /**
   * The integer digits past the decimal to preserve.
   */
  private int standardParallel2Digits = -1;

  /**
   * The transform that converts between geodetic coordinates and projected
   * coordinates.
   */
  public Transformable transform;

  /**
   * Gets or sets the w.
   */
  public Double w;

  /**
   * The integer zone parameter if it is specified.
   */
  public Integer Zone;

  /**
   * The alpha/ azimuth. Used with Oblique Mercator and possibly a few others.
   * For our purposes this is exactly the same as azimuth
   */
  public Double alpha;

  /**
   * The number of digits past the decimal to preserve.
   */
  private int alphaDigits;

  /**
   * The BNS.
   */
  public Integer bns;

  /**
   * Gets or sets the czech index.
   */
  public Integer czech;

  /**
   * Gets or sets the guam flag.
   */
  public Boolean guam;

  /**
   * Gets or sets the h.
   */
  public Double h;

  /**
   * Gets or sets the lat_ts.
   */
  public Double lat_ts;

  /**
   * Gets or sets the lon_1.
   */
  public Double lon_1;

  /**
   * Gets or sets the lon_2.
   */
  public Double lon_2;

  /**
   * Gets or sets the lonc.
   */
  public Double lonc;

  /**
   * Gets or sets the m. Named mGeneral to prevent CLS conflicts.
   */
  public Double mGeneral;

  /**
   * Gets or sets the n.
   */
  public Double n;

  /**
   * Gets or sets the no_rot.
   */
  public Integer no_rot;

  /**
   * Gets or sets the no_uoff.
   */
  public Integer no_uoff;

  /**
   * Gets or sets the rot_conv.
   */
  public Integer rot_conv;

  /**
   * Gets or sets the to_meter.
   */
  public Double to_meter;

  //</editor-fold>
  /**
   * Creates a new instance of the ProjectionInfo class.
   */
  public ProjectionInfo() {
    geographicInfo = new GeographicInfo();
    unit = new LinearUnit();
    scaleFactor = 1.0; // if not specified, default to 1
    auxiliarySphereType = AuxiliarySphereType.NotSpecified;
    noDefs = true;
  }

  /**
   * Duplicates the settings from this ProjectionInfo object to another
   * projection info class.
   *
   * @return The ProjectionInfo copy.
   */
  @Override
  public final ProjectionInfo copy() {
    return (ProjectionInfo) super.copy();
  }

  /**
   * Converts a distance unit of the specified amount to an approximate distance
   * in the projected units. For lat/long this is a simplification that
   * represents the north/south distance or distance at the equator.
   *
   * @param distance The distance in US_Foot measurements.
   * @return The double distance in projected units of the specified distance in
   * feet.
   */
  public final double feetToProj(double distance) {
    return DistanceUtils.feetToProjectedDistanceY(distance, this);
  }

  /**
   * Converts a distance unit from this projections units to an equivalent
   * distance in US Feet.
   *
   * @param distance The distance in projected units.
   * @return The double distance.
   */
  public final double projToFeet(double distance) {
    return DistanceUtils.projectedDistanceToFeetY(distance, this);
  }

  /**
   * Gets the lon_1 parameter in radians.
   *
   * @return the lon_1 parameter
   */
  public final double getLam1() {
    if (standardParallel1 != null) {
      return standardParallel1 * geographicInfo.getUnit().getRadians();
    }
    return 0;

  }

  /**
   * Gets the lon_2 parameter in radians.
   *
   * @return the lon_2 parameter
   */
  public final double getLam2() {
    if (standardParallel2 != null) {
      return standardParallel2 * geographicInfo.getUnit().getRadians();
    }

    return 0;
  }

  /**
   * Gets the lat_1 parameter multiplied by radians.
   *
   * @return
   */
  public final double getPhi1() {
    if (standardParallel1 != null) {
      return standardParallel1 * geographicInfo.getUnit().getRadians();
    }

    return 0;
  }

  /**
   * Gets the lat_2 parameter multiplied by radians.
   *
   * @return
   */
  public final double getPhi2() {
    if (standardParallel2 != null) {
      return standardParallel2 * geographicInfo.getUnit().getRadians();
    }

    return 0;
  }

  /**
   * Gets the lambda 0, or central meridian in radial coordinates.
   */
  public final double getLam0() {
    if (getCentralMeridian() != null) {
      return getCentralMeridian() * geographicInfo.getUnit().getRadians();
    }

    return 0;

  }

  /**
   * Sets the lambda 0, or central meridian in radial coordinates.
   *
   * @param value
   */
  public final void setLam0(double value) {
    setCentralMeridian((Double) value / geographicInfo.getUnit().getRadians());
  }

  /**
   * Gets the phi 0 or latitude of origin in radial coordinates.
   *
   * @return
   */
  public final double getPhi0() {
    if (latitudeOfOrigin != null) {
      return latitudeOfOrigin * geographicInfo.getUnit().getRadians();
    }

    return 0;
  }

  /**
   * Sets the phi 0 or latitude of origin in radial coordinates.
   *
   * @param value
   */
  public final void setPhi0(double value) {
    latitudeOfOrigin = value / geographicInfo.getUnit().getRadians();
  }

  /**
   * Gets a string representation of the parameter.
   *
   * @param parameter The parameter value.
   * @param digits The digits past the decimal to round to.
   * @return The string representation of the digits.
   */
  private String getString(Double parameter, int digits) {
    if (digits < 0) {
      return USLocale.format(parameter);
    }
    return USLocale.format(parameter, digits);
  }

  /**
   * Expresses the entire projection as the Esri well known text format that can
   * be found in .prj files
   *
   * @return The generated string
   */
  public final String toEsriString() {
    Spheroid tempSpheroid = new Spheroid(Proj4Ellipsoid.WGS_1984);

    // changed by JK to fix the web mercator auxiliary sphere Esri string
    if (name != null && name.equals("WGS_1984_Web_Mercator_Auxiliary_Sphere")) {
      tempSpheroid = geographicInfo.getDatum().getSpheroid();
      geographicInfo.getDatum().setSpheroid(new Spheroid(Proj4Ellipsoid.WGS_1984));
    }

    String result = "";
    if (!latLon) {
      result += "PROJCS[\"" + name + "\",";
    }

    result += geographicInfo.toEsriString();
    if (latLon) {
      return result;
    }

    result += ",";
    if (transform != null) {
      // Since we can have semi-colon delimited names for aliases, we have
      // to output just one in the WKT. Issue #297
      String transformName = transform.getEsriName();
      result += "PROJECTION[\"" + transformName + "\"],";
    }

    if (falseEasting != null) {
      String alias = "False_Easting";
      if (falseEastingAlias != null) {
        alias = falseEastingAlias;
      }
      result += "PARAMETER[\"" + alias + "\","
          + getString(falseEasting / unit.getMeters(), falseEastingDigits) + "],";
    }

    if (falseNorthing != null) {
      String alias = "False_Northing";
      if (falseNorthingAlias != null) {
        alias = falseNorthingAlias;
      }
      result += "PARAMETER[\"" + alias + "\","
          + getString(falseNorthing / unit.getMeters(), falseNorthingDigits) + "],";
    }

    if (centralMeridian != null && isCentralMeridianValid()) {
      result += "PARAMETER[\"Central_Meridian\","
          + getString(centralMeridian, centralMeridianDigits) + "],";
    }

    if (standardParallel1 != null) {
      result += "PARAMETER[\"Standard_Parallel_1\","
          + getString(standardParallel1, standardParallel1Digits) + "],";
    }

    if (standardParallel2 != null) {
      result += "PARAMETER[\"Standard_Parallel_2\","
          + getString(standardParallel2, standardParallel2Digits) + "],";
    }

    if (scaleFactor != null) {
      result += "PARAMETER[\"Scale_Factor\"," + USLocale.format(scaleFactor) + "],";
    }

    if (alpha != null) {
      result += "PARAMETER[\"Azimuth\"," + USLocale.format(alpha) + "],";
    }

    if (longitudeOfCenter != null) {
      String alias = "Longitude_Of_Center";
      if (longitudeOfCenterAlias != null) {
        alias = longitudeOfCenterAlias;
      }
      result += "PARAMETER[\"" + alias + "\","
          + getString(longitudeOfCenter, longitudeOfCenterDigits) + "],";
    }

    if (longitudeOf1st != null) {
      result += "PARAMETER[\"Longitude_Of_1st\","
          + getString(longitudeOf1st, longitudeOf1stDigits) + "],";
    }

    if (longitudeOf2nd != null) {
      result += "PARAMETER[\"Longitude_Of_2nd\","
          + getString(longitudeOf2nd, longitudeOf2ndDigits) + "],";
    }

    if (latitudeOfOrigin != null) {
      String alias = "Latitude_Of_Origin";
      if (latitudeOfOriginAlias != null) {
        alias = latitudeOfOriginAlias;
      }

      result += "PARAMETER[\"" + alias + "\","
          + getString(latitudeOfOrigin, latitudeOfOriginDigits) + "],";
    }

    // changed by JK to fix the web mercator auxiliary sphere Esri string
    if ("WGS_1984_Web_Mercator_Auxiliary_Sphere".equals(name)) {
      result += "PARAMETER[\"Auxiliary_Sphere_Type\","
          + USLocale.format(getAuxiliarySphereType().getIndex()) + ".0],";
    }

    result += unit.ToEsriString() + "]";
    // changed by JK to fix the web mercator auxiliary sphere Esri string
    if ("WGS_1984_Web_Mercator_Auxiliary_Sphere".equals(name)) {
      geographicInfo.getDatum().setSpheroid(new Spheroid(Proj4Ellipsoid.WGS_1984));
      geographicInfo.getDatum().setSpheroid(tempSpheroid);
    }

    return result;
  }

  /**
   * Using the specified code, this will attempt to look up the related
   * reference information from the appropriate pcs code. This will not throw an
   * exception, but will be empty if there were any problems.
   *
   * @param epsgCode The epsg Code.
   * @return ProjectionInfo matching the code.
   */
  public static Optional<ProjectionInfo> fromEpsgCode(int epsgCode) {

    if (Projections.getEpsgEsri().containsKey(epsgCode)) {
      return Optional.of(fromEsriString(Projections.getEpsgEsri().get(epsgCode),
          epsgCode));
    }

    try {
      return readAuthorityCode("EPSG", epsgCode);

    } catch (ProjectionException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);

    }
    return Optional.empty();
  }

  /**
   * Parses the entire projection from an Esri string. In some cases, this will
   * have default projection information since only geographic information is
   * obtained.
   *
   * @param esriString The Esri string to parse
   * @return ProjectionInfo projection.
   */
  public static ProjectionInfo fromEsriString(String esriString) {
    return fromEsriString(esriString, null);
  }

  /**
   * Parses the entire projection from an Esri string. In some cases, this will
   * have default projection information since only geographic information is
   * obtained.
   *
   * @param esriString The Esri string to parse
   * @param epsgCode The EPSG code if already known.
   * @return ProjectionInfo projection.
   */
  public static ProjectionInfo fromEsriString(String esriString, Integer epsgCode) {
    if (esriString == null || esriString.isEmpty()) {
      // Return a default 'empty' projection
      return ProjectionInfo.DEFAULT;
    }

    //special case for Krovak Projection
    //use a lookup table instead of hard coding the projection here
    if (esriString.contains("Krovak")) {
      return Projections.getProjected().getNationalGrids().getSJTSKKrovakEastNorth();
    }

    ProjectionInfo info = new ProjectionInfo();
    if (epsgCode != null) {
      info.epsgCode = epsgCode;
    }
    info.noDefs = true;
    if (!info.tryParseEsriString(esriString)) {
      LOGGER.log(Level.SEVERE, "Failed to parse: {0}", esriString);
    }
    if (epsgCode == null) {
      if (info.isLatLon()) {
        String name = info.getGeographicInfo().getName();
        if (Projections.getGeocsEpsg().containsKey(name)) {
          info.epsgCode = Projections.getGeocsEpsg().get(name);
        }

      } else {
        String name = info.getName();
        if (Projections.getProjcsEpsg().containsKey(name)) {
          info.epsgCode = Projections.getProjcsEpsg().get(info.getName());
        }

      }
    }

    return info;
  }

  /**
   * Creates a new projection and automatically reads in the proj4 string.
   *
   * @param proj4String The proj4String to read in while defining the projection
   * @return A ProjectionInfo representing the projection. an error parsing the
   * projection.
   */
  public static Optional<ProjectionInfo> fromProj4String(String proj4String) {
    try {
      ProjectionInfo info = new ProjectionInfo();
      info.parseProj4String(proj4String);
      return Optional.of(info);
    } catch (ProjectionException ex) {
      Logger.getLogger(ProjectionInfo.class.getName()).log(
          Level.SEVERE, ex.getMessage(), ex);
    }
    return Optional.empty();
  }

  /**
   * Open a given prj fileName.
   *
   * @param prjFilename The String path to the .prj file.
   * @return A ProjectionInfo read from the file.
   * @throws ProjectionException If the Projection has an error in parsing.
   * @throws java.io.FileNotFoundException If the File was not found.
   */
  public static ProjectionInfo open(String prjFilename) throws ProjectionException,
      FileNotFoundException {
    String output = new Scanner(new File(prjFilename)).useDelimiter("\\Z").next();
    return fromEsriString(output);
  }

  /**
   * Opens an ESRI PRJ File.
   *
   * @param prjFilename The String for the ESRI PRJ File to open.
   * @throws java.io.FileNotFoundException If there was an error finding the
   * file.
   */
  public final void openESRI(String prjFilename) throws FileNotFoundException {
    Scanner scan = new Scanner(new File(prjFilename)).useDelimiter("\\Z");
    if (scan.hasNext()) {
      String proj = scan.next();
      ProjectionInfo info = fromEsriString(proj);
      this.copyProperties(info);
    }
  }

  /**
   * Uses the ESRI Projection name to discover the EPSG code.
   */
  private void findEpsg() {
    if (this.isLatLon()) {
      String name = this.getGeographicInfo().getName();
      if (Projections.getGeocsEpsg().containsKey(name)) {
        this.epsgCode = Projections.getGeocsEpsg().get(name);
      }
    } else if (Projections.getProjcsEpsg().containsKey(this.getName())) {
      this.epsgCode = Projections.getProjcsEpsg().get(this.getName());
    }
  }

  /**
   * Gets a boolean that is true if the Esri WKT string created by the
   * projections matches. There are multiple ways to write the same projection,
   * but the output Esri WKT string should be a good indicator of whether or not
   * they are the same.
   *
   * @param other The other projection to compare with. Other can be null, but
   * equals will always return false in that case.
   * @return Boolean, true if the projections are the same.
   */
  @Override
  public final boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (other instanceof ProjectionInfo) {
      ProjectionInfo proj = (ProjectionInfo) other;
      String first = toEsriString();
      String second = proj.toEsriString();
      return first.equals(second) || toProj4String().equals(proj.toProj4String());
    }
    return false;
  }

  /**
   * Gets a hash code representation of the ESRI string of this object.
   *
   * @return The hash code.
   */
  @Override
  public final int hashCode() {
    return toEsriString().hashCode();
  }

  /**
   * If this is a geographic coordinate system, this will show decimal degrees.
   * Otherwise, this will show the linear unit units.
   *
   * @param quantity The quantity.
   * @return The get unit text.
   */
  public final String getUnitText(double quantity) {
    if (geoc || latLon) {
      if (Math.abs(quantity) < 1) {
        return "of a decimal degree";
      }

      return quantity == 1 ? "decimal degree" : "decimal degrees";
    }

    if (Math.abs(quantity) < 1) {
      return "of a " + unit.getName();
    }

    if (Math.abs(quantity) == 1) {
      return unit.getName();
    }

    if (Math.abs(quantity) > 1) {
      // The following are frequently followed by specifications, so adding s doesn't work
      if (unit.getName().contains("Foot") || unit.getName().contains("foot")) {
        String plural = unit.getName().replace("Foot", "Feet");
        plural = plural.replace("foot", "Feet");
        return plural;
      }

      if (unit.getName().contains("Yard") || unit.getName().contains("yard")) {
        String plural = unit.getName().replace("Yard", "Yards");
        plural = plural.replace("yard", "Yards");
        return plural;
      }

      if (unit.getName().contains("Chain") || unit.getName().contains("chain")) {
        String plural = unit.getName().replace("Chain", "Chains");
        plural = plural.replace("chain", "Chains");
        return plural;
      }

      if (unit.getName().contains("Link") || unit.getName().contains("link")) {
        String plural = unit.getName().replace("Link", "Links");
        plural = plural.replace("link", "Links");
        return plural;
      }

      return unit.getName() + "s";
    }

    return unit.getName();
  }

  /**
   * Exports this projection info by saving it to a *.prj file.
   *
   * @param prjFilename The String filename to save this projection to.
   * @throws java.io.FileNotFoundException If the folder was missing or the file
   * permission to write could not be established.
   */
  public final void saveAs(String prjFilename) throws FileNotFoundException {
    File f = new File(prjFilename);

    if (f.exists()) {
      f.delete();
    }
    try (PrintWriter out = new PrintWriter(f)) {
      out.println(this.toEsriString());
      out.flush();
    }

  }

  /**
   * Returns a representaion of this object as a Proj4 string.
   *
   * @return The to proj 4 string.
   */
  public final String toProj4String() {
    //enforce invariant culture
    StringBuilder result = new StringBuilder();

    append(result, "x_0", falseEasting);
    append(result, "y_0", falseNorthing);
    if (scaleFactor != null && scaleFactor != 1) {
      append(result, "k_0", scaleFactor);
    }

    append(result, "lat_0", latitudeOfOrigin);
    append(result, "lon_0", centralMeridian);
    append(result, "lat_1", standardParallel1);
    append(result, "lat_2", standardParallel2);
    if (over) {
      append(result, "over", 1);
    }

    if (geoc) {
      append(result, "geoc", 1);
    }

    append(result, "alpha", alpha);
    append(result, "lonc", longitudeOfCenter);
    append(result, "zone", Zone);

    if (latLon) {
      append(result, "proj", "longlat");
    } else {
      if (transform != null) {
        append(result, "proj", transform.getProj4Name());
      }

      // skips over to_meter if this is geographic or defaults to 1
      if (unit.getMeters() != 1) {
        // we don't create +units=m or +units=f instead we use.
        append(result, "to_meter", unit.getMeters());
      }
    }

    result.append(geographicInfo.toProj4String());

    if (south) {
      result.append(" +south");
    }

    if (noDefs) {
      result.append(" +no_defs");
    }

    return result.toString();
  }

  /**
   * Gets a boolean that is true if the central meridian passes validation.
   *
   * @return Boolean, true if the meridean is valid.
   */
  private boolean isCentralMeridianValid() {
    // CentralMeridian (lam0) is calculated for these coordinate system,
    // but IS NOT part of their Esri WKTs
    return (transform == null || transform.getEsriName() == null
        || !"hotine_oblique_mercator_azimuth_natural_origin".
        equals(transform.getEsriName().toLowerCase())
        && !"hotine_oblique_mercator_azimuth_center".equals(
            transform.getEsriName().toLowerCase()));
  }

  /**
   * Appends the formatted content to the specified string builder.
   *
   * @param result The string builder to append to.
   * @param name The string name of the attribute to append.
   * @param value The value of the attribute to append.
   */
  private static void append(StringBuilder result, String name, Object value) {
    if (value == null) {
      return;
    }

    result.append(String.format(" +{0}={1}", name, value));
  }

  /**
   * Gets the parameter based on the string line.
   *
   * @param name The name of the parameter to read.
   * @param esriString The entire esri string to parse.
   * @return The Double valued parameter to read.
   */
  private static Optional<DigitValue> getParameter(String name, String esriString) {
    if (name == null || name.isEmpty()
        || esriString == null || esriString.isEmpty()) {
      return Optional.empty();
    }

    DigitValue result = null;
    int iStart = esriString.indexOf("PARAMETER[\"" + name + "\"");
    if (iStart < 0) {
      iStart = esriString.indexOf("PARAMETER[\"" + name.toLowerCase() + "\"");
    }
    if (iStart >= 0) {
      iStart += 13 + name.length();
      int iEnd = esriString.indexOf(']', iStart);
      String tst = esriString.substring(iStart, iEnd);
      try {
        result = new DigitValue();
        result.setValue(USLocale.parseDouble(tst));
        if (!tst.contains(".")) {
          result.setDigits(0);
        } else {
          int index = tst.indexOf(".");
          result.setDigits(tst.length() - (index + 1));
        }
      } catch (ParseException ex) {
        Logger.getLogger(ProjectionInfo.class.getName()).log(
            Level.SEVERE, ex.getMessage(), ex);
      }
    }

    return Optional.ofNullable(result);
  }

  /**
   * Gets the parameter with the possible list of names.
   *
   * @param parameterNames The list of possible parameter names.
   * @param esriString The String to parse.
   * @return An optional AliasedValue. This lists both the value, and the actual
   * version of the acceptable alias list that was found.
   */
  private static Optional<AliasedValue> getParameter(List<String> parameterNames,
      String esriString) {
    if (parameterNames == null || esriString == null || esriString.isEmpty()) {
      return Optional.empty();
    }

    // Return the first result that returns a value
    for (String parameterName : parameterNames) {

      Optional<DigitValue> val = getParameter(parameterName, esriString);
      if (val.isPresent()) {
        DigitValue dv = val.get();
        AliasedValue result = new AliasedValue(parameterName, dv);
        return Optional.of(result);
      }
    }
    return Optional.empty();
  }

  /// <summary>
  /// Reads the authority code.
  /// </summary>
  /// <param name="authority">
  /// The authority.
  /// </param>
  /// <param name="epsgCode">
  /// The code.
  /// </param>
  private static Optional<ProjectionInfo> readAuthorityCode(String authority,
      int epsgCode) throws ProjectionException {
    String key = String.format("{0}:{1}", authority, epsgCode);

    return AuthorityCodeHandler.getInstance().getProjectionInfo(key);

  }

  /// <summary>
  /// Attempts to parse known parameters from the set of proj4 parameters
  /// </summary>
  /// <param name="proj4String">
  /// </param>
  private void parseProj4String(String proj4String) throws ProjectionException {
    try {
      if (proj4String == null || proj4String.isEmpty()) {
        return;
      }

      // If it has a zone, and the projection is tmerc, it actually needs the utm initialization
      boolean tmercIsUtm = proj4String.contains("zone=");

      String[] sections = proj4String.split("\\+");
      for (String str : sections) {
        String s = str.trim();
        if (s == null || s.isEmpty()) {
          continue;
        }
        // single token commands
        switch (s) {
          case "no_defs":
            noDefs = true;
            continue;
          case "south":
            south = true;
            continue;
          case "R_A":
            //+R_A tells PROJ.4 to use a spherical radius that
            //gives a sphere with the same surface area as the original ellipsoid.  I
            //imagine I added this while trying to get the results to match PCI's GCTP
            //based implementation though the history isn't clear on the details.
            //the R_A parameter indicates that an authalic auxiliary sphere should be used.
            //from http://pdl.perl.org/?docs=Transform/Proj4&title=PDL::Transform::Proj4#r_a
            setAuxiliarySphereType(AuxiliarySphereType.Authalic);
            break;
          case "to":
            // some "+to" parameters exist... e.g., DutchRD. but I'm not sure what to do with them.
            // they seem to specify a second projection
            LOGGER.log(Level.FINE, String.format("ProjectionInfo.ParseProj4String:"
                + " command 'to' not supported and the portion of the string"
                + " after 'to' will not be processed in '{0}'", proj4String));
            break;
        }

        // parameters
        String[] set = s.split("=");
        if (set.length != 2) {
          LOGGER.log(Level.FINE, String.format("ProjectionInfo.ParseProj4String:"
              + " command '{0}' not understood in '{1}'", s, proj4String));
          continue;
        }

        String keyname = set[0].trim();
        String value = set[1].trim();

        switch (keyname) {
          case "lonc":
            longitudeOfCenter = USLocale.parseDouble(value);
            break;

          case "alpha":
            alpha = USLocale.parseDouble(value);
            break;

          case "x_0":
            falseEasting = USLocale.parseDouble(value);
            break;

          case "y_0":
            falseNorthing = USLocale.parseDouble(value);
            break;

          case "k":
          case "k_0":
            scaleFactor = USLocale.parseDouble(value);
            break;

          case "lat_0":
            latitudeOfOrigin = USLocale.parseDouble(value);
            break;

          case "lat_1":
            standardParallel1 = USLocale.parseDouble(value);
            break;

          case "lat_2":
            standardParallel2 = USLocale.parseDouble(value);
            break;

          case "lon_0":
            centralMeridian = USLocale.parseDouble(value);
            break;

          case "lat_ts":
            standardParallel1 = USLocale.parseDouble(value);
            break;

          case "zone":
            Zone = USLocale.parseInteger(value);
            break;

          case "geoc":
            geoc = Boolean.parseBoolean(value) && (geographicInfo.getDatum()
                .getSpheroid().getEccentricitySquared() != 0);
            break;

          case "over":
            over = Boolean.parseBoolean(value);
            break;

          case "proj":

            if ("longlat".equals(value)) {
              setLatLon(true);
            }

            if (tmercIsUtm) {
              transform = new UniversalTransverseMercator();
            } else {
              transform = TransformManager.getInstance().getProj4(value);
            }

            break;

          case "to_meter":

            unit.setMeters(USLocale.parseInteger(value));
            if (unit.getMeters() == .3048) {
              unit.setName("Foot"); // International Foot
            } else if (unit.getMeters() > .3048 && unit.getMeters() < .305) {
              unit.setName("Foot_US");
            }

            break;

          case "units":
            switch (value) {
              case "m":
                break;
              case "ft":
              case "f":
                unit.setName("Foot");
                unit.setMeters(.3048);
                break;
              case "us-ft":
                unit.setName("Foot_US");
                unit.setMeters(.304800609601219);
                break;
            }

            break;

          case "pm":
            geographicInfo.getMeridian().setPM(value);
            //// added by Jiri Kadlec - pm should also specify the CentralMeridian
            //if (value != null)
            //{
            //    CentralMeridian = GeographicInfo.Meridian.Longitude;
            //}
            break;

          case "datum":

            // Even though th ellipsoid is set by its known definition,
            // we permit overriding it with a specifically defined ellps parameter
            geographicInfo.setDatum(new Datum(value));
            break;

          case "nadgrids":
            geographicInfo.getDatum().setNadGrids(value.split(","));

            if ("@null".equals(value)) {
              geographicInfo.getDatum().setDatumType(DatumType.GridShift);
            }

            break;

          case "towgs84":
            geographicInfo.getDatum().initializeToWgs84(value.split(","));
            break;

          case "ellps":

            // Even though th ellipsoid is set by its known definition, we permit
            // overriding it with a specifically defined ellps parameter
            // generally ellps will not be used in the same string as a,b,rf,R.
            geographicInfo.getDatum().setSpheroid(new Spheroid(value));
            break;

          case "a":
          case "R":
            geographicInfo.getDatum().getSpheroid().setEquatorialRadius(
                USLocale.parseDouble(value));
            break;

          case "b":
            geographicInfo.getDatum().getSpheroid().setPolarRadius(
                USLocale.parseDouble(value));
            break;

          case "rf":
            if (geographicInfo.getDatum().getSpheroid().getEquatorialRadius() <= 0) {
              throw new ProjectionException("a must appear before rf");
            }
            geographicInfo.getDatum().getSpheroid().setInverseFlattening(
                USLocale.parseDouble(value));
            break;

          default:
            LOGGER.log(Level.FINE, String.format(
                "Unrecognized parameter skipped {0}.", name));
            break;
        }
      }

      if (transform != null) {
        transform.init(this);
      }
    } catch (ParseException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      throw new ProjectionException("Error reading Proj4 string", ex);
    } catch (ProjectionException ex) {
      throw ex;
    }
  }

  /// <summary>
  /// This overrides ToString to get the Esri name of the projection.
  /// </summary>
  /// <returns>
  /// The to string.
  /// </returns>
  @Override
  public String toString() {
    if (name != null && !name.isEmpty()) {
      return name;
    }

    if (transform != null && transform.getEsriName() != null
        && !transform.getEsriName().isEmpty()) {
      return transform.getEsriName();
    }

    if (latLon) {
      if (geographicInfo == null || geographicInfo.getName() == null
          || geographicInfo.getName().isEmpty()) {
        return "LatLon";
      }

      return geographicInfo.getName();
    }

    return toProj4String();
  }

  public boolean tryParseEsriString(String esriString) {
    if (esriString == null) {
      return false;
    }

    if (!esriString.contains("GEOGCS")) {
      return false;
    }

    if (esriString.contains("PROJCS") == false) {
      try {
        getGeographicInfo().parseEsriString(esriString);
      } catch (ProjectionException ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      }
      setLatLon(true);
      transform = new LongLat();
      try {
        transform.init(this);
      } catch (ProjectionException ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      }
      return true;
    }

    int iStart = esriString.indexOf("PROJCS[\"") + 8;
    int iEnd = esriString.indexOf("\"", iStart);
    setName(esriString.substring(iStart, iEnd));

    try {
      getGeographicInfo().parseEsriString(esriString);
    } catch (ProjectionException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }
    Optional<AliasedValue> easting = getParameter(Arrays.asList(
        new String[]{"False_Easting", "Easting_At_False_Origin"}), esriString);
    if (easting.isPresent()) {
      falseEastingAlias = easting.get().getAlias();
      falseEastingDigits = easting.get().getDigits();
      falseEasting = easting.get().getValue();
    }
    Optional<AliasedValue> northing = getParameter(Arrays.asList(
        new String[]{"False_Northing", "Northing_At_False_Origin"}), esriString);
    if (northing.isPresent()) {
      falseNorthingAlias = northing.get().getAlias();
      falseNorthingDigits = northing.get().getDigits();
      falseNorthing = northing.get().getValue();
    }

    Optional<DigitValue> maybeCm = getParameter("Central_Meridian", esriString);
    if (maybeCm.isPresent()) {
      centralMeridian = maybeCm.get().getValue();
      centralMeridianDigits = maybeCm.get().getDigits();
    }

    // Esri seems to indicate that these should be treated the same, but they
    // aren't here...
    // http://support.esri.com/en/knowledgebase/techarticles/detail/39992
    // CentralMeridian = GetParameter(new string[] { "Longitude_Of_Center",
    // "Central_Meridian", "Longitude_Of_Origin" }, ref longitudeOfCenterAlias,
    // esriString);
    Optional<DigitValue> maybeDm = getParameter("Longitude_Of_Center", esriString);
    if (maybeDm.isPresent()) {
      longitudeOfCenter = maybeDm.get().getValue();
      longitudeOfCenterDigits = maybeDm.get().getDigits();
    }

    Optional<DigitValue> maybeSp1 = getParameter("Standard_Parallel_1", esriString);
    if (maybeSp1.isPresent()) {
      standardParallel1 = maybeSp1.get().getValue();
      standardParallel1Digits = maybeSp1.get().getDigits();
    }

    Optional<DigitValue> maybeSp2 = getParameter("Standard_Parallel_2", esriString);
    if (maybeSp2.isPresent()) {
      standardParallel2 = maybeSp2.get().getValue();
      standardParallel2Digits = maybeSp2.get().getDigits();
    }

    Optional<DigitValue> maybeSf = getParameter("Scale_Factor", esriString);
    if (maybeSf.isPresent()) {
      scaleFactor = maybeSf.get().getValue();
      scaleFactorDigits = maybeSf.get().getDigits();
    }

    Optional<DigitValue> maybeAlpha = getParameter("Azimuth", esriString);
    if (maybeAlpha.isPresent()) {
      alpha = maybeAlpha.get().getValue();
      alphaDigits = maybeAlpha.get().getDigits();
    }

    Optional<DigitValue> maybeLof1 = getParameter("Longitude_Of_1st", esriString);
    if (maybeLof1.isPresent()) {
      longitudeOf1st = maybeLof1.get().getValue();
      longitudeOf1stDigits = maybeLof1.get().getDigits();
    }

    Optional<DigitValue> maybeLof2 = getParameter("Longitude_Of_2nd", esriString);
    if (maybeLof2.isPresent()) {
      longitudeOf2nd = maybeLof2.get().getValue();
      longitudeOf2ndDigits = maybeLof2.get().getDigits();
    }

    Optional<AliasedValue> latOf = getParameter(Arrays.asList(new String[]{
      "Latitude_Of_Origin", "Latitude_Of_Center", "Central_Parallel"}), esriString);
    if (latOf.isPresent()) {
      latitudeOfOriginAlias = latOf.get().getAlias();
      setLatitudeOfOrigin(latOf.get().getValue());
      latitudeOfOriginDigits = latOf.get().getDigits();
    } else {
      setLatitudeOfOrigin(null);
    }

    iStart = esriString.lastIndexOf("UNIT");
    String unitText = esriString.substring(iStart);
    try {
      getUnit().ParseEsriString(unitText);
    } catch (ProjectionException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }

    if (esriString.contains("PROJECTION")) {
      iStart = esriString.indexOf("PROJECTION") + 12;
      iEnd = esriString.indexOf("]", iStart) - 1;
      String projection = esriString.substring(iStart, iEnd);

      transform = TransformManager.getInstance().getESRI(projection);
      try {
        transform.init(this);
      } catch (ProjectionException ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      }
    }
    Optional<DigitValue> auxD = getParameter("Auxiliary_Sphere_Type", esriString);
    Integer auxType = null;
    if (auxD.isPresent()) {
      auxType = auxD.get().getValue().intValue();
    }

    if (auxType != null) {
      // While the Esri implementation sort of tip-toes around the datum transform,
      // we simply ensure that the spheroid becomes properly spherical based on the
      // parameters we have here.  (The sphereoid will be read as WGS84).
      Optional<AuxiliarySphereType> type = AuxiliarySphereType.parse(auxType);
      setAuxiliarySphereType(type.orElse(null));
      if (AuxiliarySphereType.SemimajorAxis == this.getAuxiliarySphereType()) {
        // added by Jiri to properly re-initialize the 'web mercator auxiliary
        // sphere' transform
        transform = Projections.getProjected().getWorld().getWebMercator().getTransform();
      } else if (this.getAuxiliarySphereType() == AuxiliarySphereType.SemiminorAxis) {
        double r = getGeographicInfo().getDatum().getSpheroid().getPolarRadius();
        geographicInfo.getDatum().setSpheroid(new Spheroid(r));
      } else if (this.getAuxiliarySphereType() == AuxiliarySphereType.Authalic
          || this.getAuxiliarySphereType()
          == AuxiliarySphereType.AuthalicWithConvertedLatitudes) {
        double a = geographicInfo.getDatum().getSpheroid().getEquatorialRadius();
        double b = geographicInfo.getDatum().getSpheroid().getPolarRadius();
        double r
            = Math.sqrt(
                (a * a
                + a * b * b
                / (Math.sqrt(a * a - b * b) * Math.log(
                (a + Math.sqrt(a * a - b * b)) / b)))
                / 2);
        geographicInfo.getDatum().setSpheroid(new Spheroid(r));
      }
    }

    if (falseEasting != null) {
      falseEasting = (getFalseEasting() * getUnit().getMeters());
    }

    if (falseNorthing != null) {
      falseNorthing = getFalseNorthing() * getUnit().getMeters();
    }

    return true;
  }

  /**
   * Gets a boolean that is true if this projection is the WGS1984 geographic
   * coordinate system.
   *
   * @return Boolean, true if this projection is WGS84.
   */
  public boolean isWGS84() {
    if (this.getEpsgCode() == 4326) {
      return true;
    }
    return this.equals(Projections.getGeographic().getWorld().getWGS1984());
  }

  //</editor-fold>
  /**
   * @return the geographicInfo
   */
  public final GeographicInfo getGeographicInfo() {
    return geographicInfo;
  }

  /**
   * @param geographicInfo the geographicInfo to set
   */
  public final void setGeographicInfo(GeographicInfo geographicInfo) {
    this.geographicInfo = geographicInfo;
  }

  /**
   * @return the latitudeOfOriginAlias
   */
  public final String getLatitudeOfOriginAlias() {
    return latitudeOfOriginAlias;
  }

  /**
   * @param latitudeOfOriginAlias the latitudeOfOriginAlias to set
   */
  public final void setLatitudeOfOriginAlias(String latitudeOfOriginAlias) {
    this.latitudeOfOriginAlias = latitudeOfOriginAlias;
  }

  /**
   * @return the latitudeOfOrigin
   */
  public final Double getLatitudeOfOrigin() {
    return latitudeOfOrigin;
  }

  /**
   * @param latitudeOfOrigin the latitudeOfOrigin to set
   */
  public final void setLatitudeOfOrigin(Double latitudeOfOrigin) {
    this.latitudeOfOrigin = latitudeOfOrigin;
  }

  //</editor-fold>
  /**
   * @return the longitudeOf1st
   */
  public final Double getLongitudeOf1st() {
    return longitudeOf1st;
  }

  /**
   * @param longitudeOf1st the longitudeOf1st to set
   */
  public final void setLongitudeOf1st(Double longitudeOf1st) {
    this.longitudeOf1st = longitudeOf1st;
  }

  /**
   * @return the longitudeOf2nd
   */
  public final Double getLongitudeOf2nd() {
    return longitudeOf2nd;
  }

  /**
   * @param longitudeOf2nd the longitudeOf2nd to set
   */
  public final void setLongitudeOf2nd(Double longitudeOf2nd) {
    this.longitudeOf2nd = longitudeOf2nd;
  }

  /**
   * @return the unit
   */
  public final LinearUnit getUnit() {
    return unit;
  }

  /**
   * @param unit the unit to set
   */
  public final void setUnit(LinearUnit unit) {
    this.unit = unit;
  }

  /**
   * @return the authority
   */
  public final String getAuthority() {
    return authority;
  }

  /**
   * @param authority the authority to set
   */
  public final void setAuthority(String authority) {
    this.authority = authority;
  }

  /**
   * @return the centralMeridian
   */
  public final Double getCentralMeridian() {
    return centralMeridian;
  }

  /**
   * @param centralMeridian the centralMeridian to set
   */
  public final void setCentralMeridian(Double centralMeridian) {
    this.centralMeridian = centralMeridian;
  }

  /**
   * @return the falseEasting
   */
  public final Double getFalseEasting() {
    return falseEasting;
  }

  /**
   * @param falseEasting the falseEasting to set
   */
  public final void setFalseEasting(Double falseEasting) {
    this.falseEasting = falseEasting;
  }

  /**
   * @return the falseNorthing
   */
  public final Double getFalseNorthing() {
    return falseNorthing;
  }

  /**
   * @param falseNorthing the falseNorthing to set
   */
  public final void setFalseNorthing(Double falseNorthing) {
    this.falseNorthing = falseNorthing;
  }

  /**
   * @return the geoc
   */
  public final boolean isGeoc() {
    return geoc;
  }

  /**
   * @param geoc the geoc to set
   */
  public final void setGeoc(boolean geoc) {
    this.geoc = geoc;
  }

  /**
   * @return the geocentric
   */
  public final boolean isGeocentric() {
    return geocentric;
  }

  /**
   * @param geocentric the geocentric to set
   */
  public final void setGeocentric(boolean geocentric) {
    this.geocentric = geocentric;
  }

  /**
   * @return the latLon
   */
  public final boolean isLatLon() {
    return latLon;
  }

  /**
   * @param latLon the latLon to set
   */
  public final void setLatLon(boolean latLon) {
    this.latLon = latLon;
  }

  /**
   * @return the south
   */
  public final boolean isSouth() {
    return south;
  }

  /**
   * @param south the south to set
   */
  public final void setSouth(boolean south) {
    this.south = south;
  }

  /**
   * @return the m
   */
  public final Double getM() {
    return m;
  }

  /**
   * @param m the m to set
   */
  public final void setM(Double m) {
    this.m = m;
  }

  /**
   * Gets the String name of this projection.
   *
   * @return the name
   */
  public final String getName() {
    return name;
  }

  /**
   * Sets the String name of this projection.
   *
   * @param name the name to set
   */
  public final void setName(String name) {
    this.name = name;
    if (this.epsgCode == 0) {
      findEpsg();
    }

  }

  /**
   * Gets a boolean that is true if this is over.
   *
   * @return Boolean
   */
  public final boolean isOver() {
    return this.over;
  }

  /**
   * Sets a boolean that is true if this is over.
   *
   * @param value The boolean value to set.
   */
  public final void setOver(boolean value) {
    this.over = value;
  }

  /**
   * Gets the transform for this projection info.
   *
   * @return
   */
  public final Transformable getTransform() {
    return this.transform;
  }

  /**
   * Sets the transform for this projection. The projection is useless without
   * this.
   *
   * @param value
   */
  public final void setTransform(Transformable value) {
    this.transform = value;
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the auxiliarySphereType
   */
  public final AuxiliarySphereType getAuxiliarySphereType() {
    return auxiliarySphereType;
  }

  /**
   * @param auxiliarySphereType the auxiliarySphereType to set
   */
  public final void setAuxiliarySphereType(AuxiliarySphereType auxiliarySphereType) {
    this.auxiliarySphereType = auxiliarySphereType;
  }

  /**
   * Gets the EPSG Code for this coordinate system.
   *
   * @return the epsgCode
   */
  public final int getEpsgCode() {
    return epsgCode;
  }

  /**
   * Sets the EPSG Code for this coordinate system.
   *
   * @param epsgCode the epsgCode to set
   */
  public final void setEpsgCode(int epsgCode) {
    this.epsgCode = epsgCode;
  }
}
