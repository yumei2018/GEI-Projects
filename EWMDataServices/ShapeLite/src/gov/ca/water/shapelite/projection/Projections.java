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

import com.opencsv.CSVReader;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.projection.categories.Projected;
import gov.ca.water.shapelite.projection.categories.Geographic;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class to support the existing projections.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class Projections {

  /**
   * Private constructor to prevent alternate instantiations.
   */
  private Projections() {

  }

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * GEOGRAPHIC projections are in terms of angular measurements, like decimal
   * degrees, and describe position on a round globe.
   */
  private static final Geographic GEOGRAPHIC = new Geographic();

  /**
   * PROJECTED coordinates are in terms of linear units, like meters, and are
   * designed to appear on a flat view, like the map screen.
   */
  private static final Projected PROJECTED = new Projected();

  /**
   * Gets a hash map that uses the ESRI Geographic coordinate system name as the
   * key and the EPSG Code as the value.
   */
  private static final HashMap<String, Integer> GEOCS_EPSG = new HashMap<>();

  /**
   * Gets a hash map that uses the ESRI Projection name as the key and returns
   * the EPSG code.
   */
  private static final HashMap<String, Integer> PROJCS_EPSG = new HashMap<>();

  /**
   * A HashMap of EPSG codes and their corresponding ESRI WKT string.
   */
  private static final HashMap<Integer, String> EPSG_ESRI = new HashMap<>();

  /**
   * This is a custom projection used for many HEC-RAS models in Northern
   * California.
   */
  private static final ProjectionInfo NAD83_UTM_ZONE_10_FOOT
      = ProjectionInfo.fromEsriString(
          "PROJCS[\"NAD_1983_UTM_Zone_10N_USFeet\","
          + "GEOGCS[\"GCS_North_American_1983\","
          + "DATUM[\"D_North_American_1983\","
          + "SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],"
          + "PRIMEM[\"Greenwich\",0.0],"
          + "UNIT[\"Degree\",0.0174532925199433]],"
          + "PROJECTION[\"Transverse_Mercator\"],"
          + "PARAMETER[\"False_Easting\",1640416.666666667],"
          + "PARAMETER[\"False_Northing\",0.0],"
          + "PARAMETER[\"Central_Meridian\",-123.0],"
          + "PARAMETER[\"Scale_Factor\",0.9996],"
          + "PARAMETER[\"Latitude_Of_Origin\",0.0],"
          + "UNIT[\"Foot_US\",0.3048006096012192]],"
          + "VERTCS[\"NAVD_1988_USFeet\","
          + "VDATUM[\"North_American_Vertical_Datum_1988\"],"
          + "PARAMETER[\"Vertical_Shift\",0.0],"
          + "PARAMETER[\"Direction\",1.0],"
          + "UNIT[\"Foot_US\",0.3048006096012192]]");

  /**
   * Loads the values.
   */
  private static void load() {
    try {
      InputStream stream = Projections.class.getResourceAsStream("resources/GEOCS.csv");

      CSVReader reader = new CSVReader(new InputStreamReader(stream));
      String[] nextLine;
      reader.readNext(); // skip header
      while ((nextLine = reader.readNext()) != null) {
        try {
          Integer epsg = Integer.parseInt(nextLine[1]);
          GEOCS_EPSG.put(nextLine[0], epsg);
        } catch (NumberFormatException ex) {
          // skip it
        }

      }
    } catch (IOException ex) {
      Logger.getLogger(Projections.class.getName()).log(Level.SEVERE, null, ex);
    }

    try {
      InputStream stream = Projections.class.getResourceAsStream("resources/PROJCS.csv");

      CSVReader reader = new CSVReader(new InputStreamReader(stream));
      String[] nextLine;
      reader.readNext(); // skip header
      while ((nextLine = reader.readNext()) != null) {
        try {
          Integer epsg = Integer.parseInt(nextLine[1]);
          PROJCS_EPSG.put(nextLine[0], epsg);
        } catch (NumberFormatException ex) {
          //skip it
        }

      }
    } catch (IOException ex) {
      Logger.getLogger(Projections.class.getName()).log(Level.SEVERE, null, ex);
    }

    try {
      InputStream stream = Projections.class.getResourceAsStream("resources/EPSG.csv");

      CSVReader reader = new CSVReader(new InputStreamReader(stream));
      String[] nextLine;
      reader.readNext(); // skip header
      while ((nextLine = reader.readNext()) != null) {
        try {
          Integer epsg = Integer.parseInt(nextLine[0]);
          EPSG_ESRI.put(epsg, nextLine[1]);
        } catch (NumberFormatException ex) {
          // skip it
        }

      }
    } catch (IOException ex) {
      Logger.getLogger(Projections.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  /**
   * @return the GEOGRAPHIC
   */
  public static Geographic getGeographic() {
    return GEOGRAPHIC;
  }

  /**
   * @return the PROJECTED
   */
  public static Projected getProjected() {
    return PROJECTED;
  }

  /**
   * Gets the WGS84 geographic coordinate system, which is extremely
   * popular.
   * @return ProjectionInfo containing the coordinate system.
   */
  public static ProjectionInfo getWGS84(){
    return GEOGRAPHIC.getWorld().getWGS1984();
  }

  /**
   * @return the NAD83_UTM_ZONE_10_FOOT
   */
  public static ProjectionInfo getNad83UTMZone10Foot() {
    return NAD83_UTM_ZONE_10_FOOT;
  }

  /**
   * @return the GEOCS_EPSG
   */
  public static HashMap<String, Integer> getGeocsEpsg() {
    if (GEOCS_EPSG.isEmpty()) {
      load();
    }
    return GEOCS_EPSG;
  }

  /**
   *
   * @return the PROJCS_EPSG
   */
  public static HashMap<String, Integer> getProjcsEpsg() {
    if (PROJCS_EPSG.isEmpty()) {
      load();
    }
    return PROJCS_EPSG;
  }

  /**
   * Gets A HashMap of EPSG codes and their corresponding ESRI WKT string.
   *
   * @return the EPSG_ESRI
   */
  public static HashMap<Integer, String> getEpsgEsri() {
    if (EPSG_ESRI.isEmpty()) {
      load();
    }
    return EPSG_ESRI;
  }

  /**
   * Uses the EPSG_ESRI map to convert an integer EPSG code into a
   * ProjectionInfo with a fully formatted ESRI WKT.
   *
   * @param epsgCode The integer code.
   * @return The ProjectionInfo.
   */
  public static Optional<ProjectionInfo> fromEPSG(int epsgCode) {
    ProjectionInfo result = null;
    if (EPSG_ESRI.isEmpty()) {
      load();
    }
    if (EPSG_ESRI.containsKey(epsgCode)) {
      String esri = Projections.getEpsgEsri().get(epsgCode);
      result = ProjectionInfo.fromEsriString(esri);
    }
    return Optional.ofNullable(result);
  }

}
