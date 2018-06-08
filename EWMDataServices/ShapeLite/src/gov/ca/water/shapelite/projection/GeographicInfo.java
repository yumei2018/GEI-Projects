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
public class GeographicInfo extends CopyObject {

  /**
   * The size in characters of the leading GEOCS content.
   */
  private static final int GEOCS_SIZE = 8;

  //<editor-fold defaultstate="collapsed" desc="Fields">

  /**
   * The geographic datum, describing the shape of the ellipsoidal model of earth.
   */
  private Datum datum;
  /**
   * The meridian, describing the central longitude.
   */
  private Meridian meridian;
  /**
   * The string name of this geographic information.
   */
  private String name;
  /**
   * The AngularUnit, such as decimal degrees, defining the geographic grid.
   */
  private AngularUnit unit;

  //</editor-fold>

  /**
   * Creates a new instance of the GeographicInfo class.
   */
  public GeographicInfo() {
    datum = new Datum();
    meridian = new Meridian();
    unit = new AngularUnit();
  }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Generates an esri string from the information in this geographic info
   * class, including the name, datum, meridian, and unit.
   *
   * @return The String representation of this projection in the ESRI style.
   */
  public final String toEsriString() {
    return "GEOGCS[\"" + getName() + "\"," + datum.toEsriString() + ","
            + meridian.ToEsriString() + "," + unit.ToEsriString() + "]";
  }

  /**
   * Reads an esri string in order to parse the datum, meridian and unit as well
   * as the name.
   *
   * @param esriString The string to parse
   * @throws ProjectionException if there was an error parsing the ESRI string.
   */
  public final void parseEsriString(String esriString) throws ProjectionException {
    if (esriString == null || esriString.isEmpty()) {
      return;
    }

    if (!esriString.contains("GEOGCS")) {
      return;
    }
    int iStart = esriString.indexOf("GEOGCS");
    if (iStart < 0) {
      iStart = esriString.indexOf("geogcs");
    }
    iStart += GEOCS_SIZE;
    int iEnd = esriString.indexOf("\"", iStart);
    if (iEnd >= iStart) {
      setName(esriString.substring(iStart, iEnd));
    }
    getDatum().parseEsriString(esriString);
    getMeridian().parseEsriString(esriString);
    getUnit().ParseEsriString(esriString);
  }

  /**
   * Returns a representaion of this object as a Proj4 string.
   *
   * @return The proj4 String.
   */
  public final String toProj4String() {
    return meridian.toProj4String() + datum.toProj4String();
  }

  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the datum eg: D_WGS_1984.
   *
   * @return the datum.
   */
  public final Datum getDatum() {
    return datum;
  }

  /**
   * Sets the datum eg: D_WGS_1984.
   *
   * @param datum the datum to set
   */
  public final void setDatum(Datum datum) {
    this.datum = datum;
  }

  /**
   * Gets the prime meridian longitude of the 0 mark, relative to Greenwich.
   *
   * @return the meridian
   */
  public final Meridian getMeridian() {
    return meridian;
  }

  /**
   * Sets the prime meridian longitude of the 0 mark, relative to Greenwich.
   *
   * @param meridian the meridian to set
   */
  public final void setMeridian(Meridian meridian) {
    this.meridian = meridian;
  }

  /**
   * Gets the geographic coordinate system name.
   *
   * @return the name
   */
  public final String getName() {
    return name;
  }

  /**
   * Sets the geographic coordinate system name.
   *
   * @param name the name to set
   */
  public final void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the AngularUnit, such as decimal degrees, defining the geographic grid.
   * @return the unit
   */
  public final AngularUnit getUnit() {
    return unit;
  }

  /**
   * Sets the AngularUnit, such as decimal degrees, defining the geographic grid.
   * @param unit the unit to set
   */
  public final void setUnit(AngularUnit unit) {
    this.unit = unit;
  }

    //</editor-fold>
}
