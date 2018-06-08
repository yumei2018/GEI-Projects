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
import gov.ca.water.shapelite.utils.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Datum extends CopyObject {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private static double SEC_TO_RAD = 4.84813681109535993589914102357e-6;
  private static Logger LOGGER = Logger.getLogger(Datum.class.getName());
  private DatumType datumType;
  private String description;
  private String[] nadGrids;
  private String name;
  private Spheroid spheroid;
  private double[] toWGS84;
  private static HashMap<String, Datum> knownDatums = new HashMap<>();
  private static boolean datumsInitialized = false;

  //</editor-fold>
  /**
   * Creates a new instance of the Datum class.
   */
  /// <summary>
  /// Creates a new instance of Datum
  /// </summary>
  public Datum() {
    spheroid = new Spheroid();
    datumType = DatumType.Unknown;

  }

  /// <summary>
  /// uses a string datumName of a standard datum to create a new instance of the Datum class
  /// </summary>
  /// <param datumName="standardDatum">The string datumName of the datum to use</param>
  public Datum(String standardDatum) {
    this();
    doSetProj4DatumName(standardDatum);
  }

  /// <summary>
  /// Uses a Proj4Datums enumeration in order to specify a known datum to
  /// define the spheroid and to WGS calculation method and parameters
  /// </summary>
  /// <param datumName="standardDatum">The Proj4Datums enumeration specifying the known datum</param>
  public Datum(Proj4Datum standardDatum) {
    this(standardDatum.toString());
  }

  @Override
  public Datum copy() {
    return (Datum) super.copy();
  }

  @Override
  protected void onCopyFields(CopyObject original) {
    super.onCopyFields(original);
    Datum originalDatum = (Datum) original;
    if (originalDatum.getToWGS84() != null) {
      this.setToWGS84(Arrays.copyOf(originalDatum.getToWGS84(), originalDatum.getToWGS84().length));
    }

  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Returns a representaion of this object as a Proj4 string.
   *
   * @return
   */
  public String toProj4String() {
    // if you have a datum datumName you don't need to say anything about the Spheroid
    Optional<String> strOp = getProj4DatumName();
    if (strOp.isPresent()) {
      String str = strOp.get();
      switch (getDatumType()) {
        case Unknown:
        case WGS84:
          break;

        case Param3:
          str = " +towgs84=" + getToWGS84()[0] + ","
              + getToWGS84()[1] + "," + getToWGS84()[2];
          break;

        case Param7:
          if (getToWGS84().length == 7) {

            str = " +towgs84="
                + getToWGS84()[0] + ","
                + getToWGS84()[1] + ","
                + getToWGS84()[2] + ","
                + getToWGS84()[3] / SEC_TO_RAD + ","
                + getToWGS84()[4] / SEC_TO_RAD + ","
                + getToWGS84()[5] / SEC_TO_RAD + ","
                + (getToWGS84()[6] - 1) * 1000000.0;
          }
          break;

        case GridShift:
          str = " +nadgrids=" + StringUtils.join(",", getNadGrids());
          break;
      }
      return str + spheroid.toProj4String();
    } else {
      return String.format(" +datum={0}", getProj4DatumName());
    }
  }

  /**
   * Compares two datums to see if they are actually describing the same thing
   * and therefore don't need to be transformed.
   *
   * @param otherDatum the other datum to compare against
   * @return The boolean result that is true if the attributes are the same
   */
  public boolean Matches(Datum otherDatum) {
    if (getDatumType() != otherDatum.getDatumType()) {
      return false;
    }
    if (getDatumType() == DatumType.WGS84) {
      return true;
    }
    if (!Objects.equals(spheroid.getEquatorialRadius(), otherDatum.spheroid.getEquatorialRadius())) {
      return false;
    }
    if (!Objects.equals(spheroid.getPolarRadius(), otherDatum.spheroid.getPolarRadius())) {
      return false;
    }
    if (getDatumType() == DatumType.Param3) {
      if (getToWGS84()[0] != otherDatum.getToWGS84()[0]) {
        return false;
      }
      if (getToWGS84()[1] != otherDatum.getToWGS84()[1]) {
        return false;
      }
      if (getToWGS84()[2] != otherDatum.getToWGS84()[2]) {
        return false;
      }
      return true;
    }
    if (getDatumType() == DatumType.Param7) {
      if (getToWGS84()[0] != otherDatum.getToWGS84()[0]) {
        return false;
      }
      if (getToWGS84()[1] != otherDatum.getToWGS84()[1]) {
        return false;
      }
      if (getToWGS84()[2] != otherDatum.getToWGS84()[2]) {
        return false;
      }
      if (getToWGS84()[3] != otherDatum.getToWGS84()[3]) {
        return false;
      }
      if (getToWGS84()[4] != otherDatum.getToWGS84()[4]) {
        return false;
      }
      if (getToWGS84()[5] != otherDatum.getToWGS84()[5]) {
        return false;
      }
      if (getToWGS84()[6] != otherDatum.getToWGS84()[6]) {
        return false;
      }
      return true;
    }
    if (getDatumType() == DatumType.GridShift) {
      if (getNadGrids().length != otherDatum.getNadGrids().length) {
        return false;
      }
      for (int i = 0; i < getNadGrids().length; i++) {
        if (!nadGrids[i].equals(otherDatum.nadGrids[i])) {
          return false;
        }
      }
    }
    return false;
  }

  /// <summary>
  /// Lookups the proj4 datum based on the stored datumName.
  /// </summary>
  public Optional<String> getProj4DatumName() {
    String datumName = getName();
    if (datumName == null) {
      return Optional.empty();
    }
    String result;
    switch (datumName) {
      case "D_WGS_1984":
        result = "WGS84";
        break;
      case "D_Greek":
        result = "GGRS87";
        break;
      case "D_North_American_1983":
        result = "NAD83";
        break;
      case "D_North_American_1927":
        result = "NAD27";
        break;
      default:
        result = null;
    }
    return Optional.ofNullable(result);
  }

  /**
   *
   * @param value
   */
  public void setProj4DatumName(String value) {
    doSetProj4DatumName(value);
  }

  /**
   *
   * @param value
   */
  private void doSetProj4DatumName(String value) {
    String id = value.toLowerCase();
    switch (id) {
      case "wgs84":
        setToWGS84(new double[]{0, 0, 0});
        spheroid = new Spheroid(Proj4Ellipsoid.WGS_1984);
        description = "WGS 1984";
        setName("D_WGS_1984");
        setDatumType(DatumType.WGS84);
        break;
      case "ggrs87":
        setToWGS84(new double[]{-199.87, 74.79, 246.62});
        spheroid = new Spheroid(Proj4Ellipsoid.GRS_1980);
        description = "Greek Geodetic Reference System 1987";
        setDatumType(DatumType.Param3);
        setName("D_Greek");
        break;
      case "nad83":
        setToWGS84(new double[]{0, 0, 0});
        spheroid = new Spheroid(Proj4Ellipsoid.GRS_1980);
        description = "North American Datum 1983";
        setDatumType(DatumType.WGS84);
        setName("D_North_American_1983");
        break;
      case "nad27":
        setNadGrids(new String[]{"@conus", "@ntv1_can", "@ntv2_0", "@alaska"});
        spheroid = new Spheroid(Proj4Ellipsoid.Clarke_1866);
        description = "North American Datum 1927";
        setName("D_North_American_1927");
        setDatumType(DatumType.GridShift);
        break;
      case "potsdam":
        setToWGS84(new double[]{606.0, 23.0, 413.0});
        spheroid = new Spheroid(Proj4Ellipsoid.Bessel_1841);
        description = "Potsdam Rauenberg 1950 DHDN";
        setDatumType(DatumType.Param3);
        break;
      case "carthage":
        setToWGS84(new double[]{-263.0, 6, 413});
        spheroid = new Spheroid(Proj4Ellipsoid.ClarkeModified_1880);
        description = "Carthage 1934 Tunisia";
        setDatumType(DatumType.Param3);
        break;
      case "hermannskogel":
        setToWGS84(new double[]{653.0, -212.0, 449});
        spheroid = new Spheroid(Proj4Ellipsoid.Bessel_1841);
        description = "Hermannskogel";
        setDatumType(DatumType.Param3);
        break;
      case "ire65":
        setToWGS84(new double[]{482.530, -130.569, 564.557, -1.042, -.214, -.631, 8.15});
        spheroid = new Spheroid(Proj4Ellipsoid.AiryModified);
        description = "Ireland 1965";
        setDatumType(DatumType.Param7);
        break;
      case "nzgd49":
        setToWGS84(new double[]{59.47, -5.04, 187.44, 0.47, -0.1, 1.024, -4.5993});
        spheroid = new Spheroid(Proj4Ellipsoid.International_1909);
        description = "New Zealand";
        setDatumType(DatumType.Param7);
        break;
      case "osgb36":
        setToWGS84(new double[]{446.448, -125.157, 542.060, 0.1502, 0.2470, 0.8421, -20.4894});
        spheroid = new Spheroid(Proj4Ellipsoid.Airy_1830);
        description = "Airy 1830";
        setDatumType(DatumType.Param7);
        break;
    }
  }

  /// <summary>
  /// Creates an esri well known text string for the datum part of the string
  /// </summary>
  /// <returns>The datum portion of the esri well known text</returns>
  public String toEsriString() {
    return "DATUM[\"" + getName() + "\"," + spheroid.toEsriString() + "]";
  }

  /**
   * this method will either return the known datums, or generate the datum
   * lookup if it doesn't exist from the xml.
   *
   * @return The known datums hash.
   */
  private static HashMap<String, Datum> getKnownDatums() {
    if (datumsInitialized) {
      return knownDatums;
    }

    datumsInitialized = true;
    InputStream datumStream = Datum.class.getResourceAsStream("resources/datums.xml");

    if (datumStream != null) {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder;
      try {
        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(datumStream);
        doc.getDocumentElement().normalize();
        NodeList list = doc.getChildNodes(); // first get [Datums] root node
        list = list.item(0).getChildNodes();// get the list of individual datums
        //list = list.item(0).getChildNodes();
        for (int iNode = 0; iNode < list.getLength(); iNode++) {
          Node node = list.item(iNode);
          if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element el = (Element) node;
            Datum result = new Datum();
            String name = el.getAttribute("Name");
            result.setName(name);
            String typename = el.getAttribute("Type");
            DatumType type = Enum.valueOf(DatumType.class, typename);
            result.setDatumType(type);
            switch (type) {
              case Param3: {
                double[] transform = new double[3];
                for (int i = 1; i < 4; i++) {
                  String num = el.getAttribute("P" + i);
                  double val = 0;
                  try {
                    val = USLocale.parseDoubleVal(num);
                  } catch (ParseException ex) {
                    Logger.getLogger(Datum.class.getName()).log(
                        Level.SEVERE, ex.getMessage(), ex);
                  }
                  transform[i - 1] = val;
                }
                result.setToWGS84(transform);
              }
              break;
              case Param7: {
                double[] transform = new double[7];
                for (int i = 1; i < 8; i++) {
                  String num = el.getAttribute("P" + i);
                  double val = 0;
                  try {
                    val = USLocale.parseDoubleVal(num);
                  } catch (ParseException ex) {
                    Logger.getLogger(Datum.class.getName()).log(
                        Level.SEVERE, ex.getMessage(), ex);
                  }
                  transform[i - 1] = val;
                }
                result.setToWGS84(transform);
                break;
              }
              case GridShift:
                String names = el.getAttribute("Shift");
                result.setNadGrids(names.split(";"));
                break;
            }
            knownDatums.put(name.toLowerCase(), result);
          }
        }
      } catch (ParserConfigurationException | SAXException | IOException | NumberFormatException ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      }

    }

    return knownDatums;
  }

  /// <summary>
  /// parses the datum from the esri string
  /// </summary>
  /// <param datumName="esriString">The string to parse values from</param>
  public void parseEsriString(String esriString) throws ProjectionException {
    if (esriString == null || esriString.isEmpty()) {
      return;
    }

    if (esriString.contains("DATUM") == false) {
      return;
    }
    int iStart = esriString.indexOf("DATUM") + 7;
    int iEnd = esriString.indexOf("\"", iStart);
    if (iEnd < iStart) {
      return;
    }
    this.name = esriString.substring(iStart, iEnd);
    String namelc = this.name.toLowerCase();

    HashMap<String, Datum> datums = getKnownDatums();
    if (datums.containsKey(namelc)) {
      Datum d = datums.get(namelc);
      this.copyProperties(d);
    }

    spheroid.parseEsriString(esriString);
  }

  /**
   * Initializes to WGS84.
   *
   * @param values
   * @throws ProjectionException
   */
  public void initializeToWgs84(String[] values) throws ProjectionException {
    setToWGS84(new double[values.length]);
    for (int i = 0; i < values.length; i++) {
      getToWGS84()[i] = Double.parseDouble(values[i]);
    }

    if (getToWGS84().length != 3 && getToWGS84().length != 7) {
      throw new ProjectionException("Unrecognized ToWgs84 array length. The number of elements in the array should be 3 or 7");
    }

    if (getToWGS84().length == 3) {
      setDatumType(DatumType.Param3);
    } else // checking to see if several blank values were included.
    {
      if (getToWGS84()[3] == 0.0 && getToWGS84()[4] == 0.0 && getToWGS84()[5] == 0.0 && getToWGS84()[6] == 0.0) {
        setDatumType(DatumType.Param3);
      } else {
        setDatumType(DatumType.Param7);
        // Transform from arc seconds to radians
        getToWGS84()[3] *= SEC_TO_RAD;
        getToWGS84()[4] *= SEC_TO_RAD;
        getToWGS84()[5] *= SEC_TO_RAD;
        // transform from parts per millon to scaling factor
        getToWGS84()[6] = (getToWGS84()[6] / 1000000.0) + 1;
      }
    }
  }

  //</editor-fold>
  //editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the spheroid
   */
  public Spheroid getSpheroid() {
    return spheroid;
  }

  /**
   * @param spheroid the spheroid to set
   */
  public void setSpheroid(Spheroid spheroid) {
    this.spheroid = spheroid;
  }

  //</editor-fold>
  /**
   * @return the datumName
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the datumName to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the nadGrids
   */
  public String[] getNadGrids() {
    return nadGrids;
  }

  /**
   * @param nadGrids the nadGrids to set
   */
  public void setNadGrids(String[] nadGrids) {
    this.nadGrids = nadGrids;
  }

  /**
   * @return the datumType
   */
  public DatumType getDatumType() {
    return datumType;
  }

  /**
   * @param datumType the datumType to set
   */
  public void setDatumType(DatumType datumType) {
    this.datumType = datumType;
  }

  /**
   * @return the toWGS84
   */
  public double[] getToWGS84() {
    return toWGS84;
  }

  /**
   * @param toWGS84 the toWGS84 to set
   */
  public void setToWGS84(double[] toWGS84) {
    this.toWGS84 = toWGS84;
  }
}
