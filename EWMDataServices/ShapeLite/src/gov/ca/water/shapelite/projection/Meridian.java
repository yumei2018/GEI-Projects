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
import gov.ca.water.shapelite.OptionalString;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Meridian {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private int code;
  private double longitude;
  private String name;
  private static final Logger LOGGER = Logger.getLogger(Meridian.class.getName());

  //</editor-fold>
  /**
   * Creates a new instance of the Meridian class.
   */
  public Meridian() {
    name = "Greenwich"; // by default.
  }

  /**
   * Generates a custom meridian given a name and a longitude
   *
   * @param longitude The longitude to use
   * @param name The string name for this meridian
   */
  public Meridian(double longitude, String name) {
    this.longitude = longitude;
    this.name = name;
  }

  /**
   * Creates a new meridian from one of the known, proj4 meridian locations.
   * Presumably the longitudes here correspond to various standard meridians
   * rather than some arbitrary longitudes of capital cities.
   *
   * @param standardMeridian One of the enumerations listed
   */
  public Meridian(Proj4Meridian standardMeridian) {
    doAssignMeridian(standardMeridian);
  }

        /// <summary>
  /// Creates a new meridian from one of the known, proj4 meridian locations.
  /// </summary>
  /// <param name="standardMeridianName">The string name of the meridian to use</param>
  public Meridian(String standardMeridianName) {
    Proj4Meridian meridian = Enum.valueOf(Proj4Meridian.class, standardMeridianName);
    doAssignMeridian(meridian);
  }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>
  /**
   * Reads the integer code (possibly only an internal GDAL code) for the
   * Meridian. The value can be from 8901 (Greenwich) to 8913 (Oslo). This will
   * also alter the name and longitude for this meridian.
   *
   * @param code The integer meridian code.
   */
  public void readCode(int code) {
    switch (code) {
      case 8901:
        assignMeridian(Proj4Meridian.Greenwich);
        break;
      case 8902:
        assignMeridian(Proj4Meridian.Lisbon);
        break;
      case 8903:
        assignMeridian(Proj4Meridian.Paris);
        break;
      case 8904:
        assignMeridian(Proj4Meridian.Bogota);
        break;
      case 8905:
        assignMeridian(Proj4Meridian.Madrid);
        break;
      case 8906:
        assignMeridian(Proj4Meridian.Rome);
        break;
      case 8907:
        assignMeridian(Proj4Meridian.Bern);
        break;
      case 8908:
        assignMeridian(Proj4Meridian.Jakarta);
        break;
      case 8909:
        assignMeridian(Proj4Meridian.Ferro);
        break;
      case 8910:
        assignMeridian(Proj4Meridian.Brussels);
        break;
      case 8911:
        assignMeridian(Proj4Meridian.Stockholm);
        break;
      case 8912:
        assignMeridian(Proj4Meridian.Athens);
        break;
      case 8913:
        assignMeridian(Proj4Meridian.Oslo);
        break;
    }
  }

  /**
   * Changes the longitude to correspond with the specified standard meridian
   *
   * @param standardMeridian
   */
  public void assignMeridian(Proj4Meridian standardMeridian) {
    doAssignMeridian(standardMeridian);
  }

  /**
   * Changes the longitude to correspond with the specified standard meridian
   *
   * @param standardMeridian
   */
  private void doAssignMeridian(Proj4Meridian standardMeridian) {
    if (standardMeridian == null) {
      return;
    }
    setName(standardMeridian.toString());
    switch (standardMeridian) {
      case Greenwich:
        setLongitude(0);
        setCode(8901);
        break;
      case Lisbon:
        setLongitude(-9.131906111);
        setCode(8902);
        break;
      case Paris:
        setLongitude(2.337229167);
        setCode(8903);
        break;
      case Bogota:
        setLongitude(-74.08091667);
        setCode(8904);
        break;
      case Madrid:
        setLongitude(-3.687938889);
        setCode(8905);
        break;
      case Rome:
        setLongitude(12.45233333);
        setCode(8906);
        break;
      case Bern:
        setLongitude(7.439583333);
        setCode(8907);
        break;
      case Jakarta:
        setLongitude(106.8077194);
        setCode(8908);
        break;
      case Ferro:
        setLongitude(-17.66666667);
        setCode(8909);
        break;
      case Brussels:
        setLongitude(4.367975);
        setCode(8910);
        break;
      case Stockholm:
        setLongitude(18.05827778);
        setCode(8911);
        break;
      case Athens:
        setLongitude(23.7163375);
        setCode(8912);
        break;
      case Oslo:
        setLongitude(10.72291667);
        setCode(8913);
        break;
    }
  }

  private void findNameByValue(double pm) {
    if (Math.abs(pm) < .00000001) {
      setName("Greenwich");
      setCode(8901);
    } else if (Math.abs(pm - -9.131906111) < .00000001) {
      setName("Lisbon");
      setCode(8902);
    } else if (Math.abs(pm - 2.337229167) < .00000001) {
      setName("Paris");
      setCode(8903);
    } else if (Math.abs(pm - -74.08091667) < .00000001) {
      setName("Bogota");
      setCode(8904);
    } else if (Math.abs(pm - -3.687938889) < .00000001) {
      setName("Madrid");
      setCode(8905);
    } else if (Math.abs(pm - 12.45233333) < .00000001) {
      setName("Rome");
      setCode(8906);
    } else if (Math.abs(pm - 7.439583333) < .00000001) {
      setName("Bern");
      setCode(8907);
    } else if (Math.abs(pm - 106.8077194) < .0000001) {
      setName("Jakarta");
      setCode(8908);
    } else if (Math.abs(pm - -17.66666667) < .00000001) {
      setName("Ferro");
      setCode(8909);
    } else if (Math.abs(pm - 4.367975) < .000001) {
      setName("Brussels");
      setCode(8910);
    } else if (Math.abs(pm - 18.05827778) < .00000001) {
      setName("Stockholm");
      setCode(8911);
    } else if (Math.abs(pm - 23.7163375) < .00000001) {
      setName("Athens");
      setCode(8912);
    } else if (Math.abs(pm - 10.72291667) < .00000001) {
      setName("Oslo");
      setCode(8913);
    } else {
      setName("Custom");
      setCode(0);
    }
  }

  /**
   * Gets the alternate prime meridian (typically a city name).
   *
   * @return the String meridian.
   */
  public final OptionalString getPM() {
    if (longitude != 0) {
      return OptionalString.of(Double.toString(longitude));
    } else {
      return OptionalString.empty();
    }
  }

  /**
   * Sets the Alternate prime meridian (typically a city name).
   *
   * @param pm The prime meridian city name
   * @throws ProjectionException
   */
  public final void setPM(String pm) throws ProjectionException {
    //maybe we have a numeric value
    double lon;
    try {
      lon = Double.parseDouble(pm);
      setLongitude(lon);
      // Try to find a standard name that has a close longitude.
      findNameByValue(lon);
    } catch (NumberFormatException ex) {
      throw new ProjectionException("Failed to parse Prime Meridian", ex);
    }

    Proj4Meridian meridian = Proj4Meridian.parse(name).orElse(null);
    assignMeridian(meridian);

  }


  /**
   * Writes the longitude and prime meridian content to the esri string
   * @return A string that is formatted for an esri prj file
   */
  public String ToEsriString() {
    return "PRIMEM[\"" + getName() + "\"," + USLocale.format(getLongitude(), 1) + "]";
  }


  /**
   * Reads content from an esri string, learning information about the prime meridian
   * @param esriString
   */
  public void parseEsriString(String esriString) {
    if (esriString == null || esriString.isEmpty()) {
      return;
    }

    int iStart;
    iStart = esriString.indexOf("PRIMEM");
    if (iStart < 0) {
      iStart = esriString.indexOf("primem");
    }
    iStart += 7;
    int iEnd = esriString.indexOf("]", iStart);
    if (iEnd < iStart) {
      return;
    }
    String extracted = esriString.substring(iStart, iEnd);
    String[] terms = extracted.split(",");
    setName(terms[0]);
    setName(getName().substring(1, getName().length() - 1));
    try {
      setLongitude(USLocale.parseDouble(terms[1]));
    } catch (ParseException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }
  }

  /**
   * Returns a representation of this object as a Proj4 string.
   *
   * @return The OptionalString, which can be empty.
   */
  public final OptionalString toProj4String() {
    OptionalString pm = getPM();
    if (pm.isPresent()) {
      return OptionalString.of(String.format(" +pm={0}", pm.get()));
    }
    return OptionalString.empty();
  }

  /**
   * Gets the code. Setting this will not impact the longitude or name. In order
   * to read a standard code (8901-8913) use the readCode method.
   *
   * @return the code
   */
  public final int getCode() {
    return code;
  }

  /**
   * Sets the code. Setting this will not impact the longitude or name. In order
   * to read a standard code (8901-8913) use the readCode method.
   *
   * @param code the code to set
   */
  public final void setCode(int code) {
    this.code = code;
  }

  /**
   * Gets the longitude where the prime meridian for this geographic coordinate
   * occurs.
   *
   * @return the longitude
   */
  public final double getLongitude() {
    return longitude;
  }

  /**
   * Sets the longitude where the prime meridian for this geographic coordinate
   * occurs.
   *
   * @param longitude the longitude to set
   */
  public final void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  /**
   * @return the name
   */
  public final String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public final void setName(String name) {
    this.name = name;
  }

}
