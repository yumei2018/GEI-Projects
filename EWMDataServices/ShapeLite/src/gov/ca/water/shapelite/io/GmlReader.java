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
package gov.ca.water.shapelite.io;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.ShapeType;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
public final class GmlReader {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The logger for this class.
   */
  private static final Logger LOGGER = Logger.getLogger(GmlReader.class.getName());

  //</editor-fold>
  /**
   * Creates a new instance of the GmlReader class.
   */
  private GmlReader() {

  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Opens from a GML file, and reads the entire contents into a list of feature
   * sets.
   *
   * @param gmlFile
   * @return
   * @throws java.io.FileNotFoundException
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   */
  public static List<FeatureSet> readAll(String gmlFile) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
    FileInputStream stream = new FileInputStream(gmlFile);
    return readAll(stream);
  }

  /**
   * Reads the gml content from a stream. This may be from an embedded file, or
   * from a zip file entry or from a FileInputStream.
   *
   * @param stream The InputStream to read.
   * @return List of FeatureSets, broken down by line, point and polygon types.
   * @throws javax.xml.parsers.ParserConfigurationException If there was an
   * error with parsing.
   * @throws org.xml.sax.SAXException If the xml parser had an exception.
   * @throws java.io.IOException If there was an IO error.
   */
  public static List<FeatureSet> readAll(InputStream stream)
      throws ParserConfigurationException, SAXException, IOException {
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = dbFactory.newDocumentBuilder();
    Document doc = builder.parse(stream);
    doc.normalize();
    Element featureCollection = doc.getDocumentElement();
    List<Element> featureCollectionElements = getChildElements(featureCollection);
    FeatureSet lines = null;
    FeatureSet polygons = null;
    List<FeatureSet> results = new ArrayList<>();
    for (Element child : featureCollectionElements) {
      if ("gml:boundedBy".equals(child.getNodeName())) {
        getBounds(child);
      }
      if ("gml:featureMember".equals(child.getNodeName())) {
        Feature f = getFeature(child);
        if (f.getShape().getShapeType() == ShapeType.PolyLine) {
          if (lines == null) {
            lines = new FeatureSet();
            results.add(lines);
          }
          lines.getFeatures().add(f);
        }
        if (f.getShape().getShapeType() == ShapeType.Polygon) {
          if (polygons == null) {
            polygons = new FeatureSet();
            results.add(polygons);
          }
          polygons.getFeatures().add(f);
        }
      }
    }
    return results;

  }

  /**
   * Given an element that matches the gml:featureMember, this will attempt to
   * parse the information as either attribute fields or else shape information.
   *
   * @param element The feature element to get.
   * @return a Feature
   */
  private static Feature getFeature(Element element) {
    Feature result = new Feature();
    // firstly there is a tag assigning the name of the feature, like XS_Edge_Line
    Optional<Element> nameElement = getChildElement(element);
    // next, within the named element, come attributes, and eventually
    // a gml:curveProperty.
    if (nameElement.isPresent()) {
      List<Element> children = getChildElements(nameElement.get());
      for (Element child : children) {
        String name = child.getNodeName();
        if (name == null) {
          continue;
        }
        if (!name.startsWith("gml")) {
          // treat this as an attribute
          result.getAttributes().put(name, child.getTextContent());
        } else {
          switch (name) {
            case "gml:curveProperty":
              parseCurve(child, result);
              break;
            case "gml:geometryProperty":
              parseGeometry(child, result);
              break;
            default:
              // do nothing
              break;
          }
        }
      }

    }
    return result;
  }

  /**
   *
   * @param curve
   * @param result
   */
  private static void parseCurve(Element curve, Feature result) {

    Optional<Element> lineString = getChildElement(curve, "gml:LineString");
    if (lineString != null) {
      result.getShape().setShapeType(ShapeType.PolyLine);
      Part prt = parseLineStringXY(lineString.get());
      result.getShape().getParts().add(prt);
      result.getShape().calculateBounds();
    }
  }

  /**
   *
   * @param curve
   * @param result
   */
  private static void parseGeometry(Element curve, Feature result) {

    Optional<Element> polygon = getChildElement(curve, "gml:Polygon");
    if (polygon.isPresent()) {
      result.getShape().setShapeType(ShapeType.Polygon);
      List<Part> prt = parsePolygon(polygon.get());
      result.getShape().getParts().addAll(prt);
      result.getShape().calculateBounds();
    }
  }

  /**
   * Parses the polygon.
   *
   * @param polygon The polygon to parse.
   * @return The list of parts that were parsed.
   */
  private static List<Part> parsePolygon(Element polygon) {
    List<Part> results = new ArrayList<>();
    Optional<Element> exterior = getChildElement(polygon, "gml:exterior");
    if (exterior.isPresent()) {
      List<Element> linearRings = getChildElements(exterior.get(), "gml:LinearRing");
      for (Element ring : linearRings) {
        Optional<Element> posList = getChildElement(ring, "gml:posList");
        if (posList != null) {
          String text = posList.get().getTextContent();
          String[] vals = text.split(" ");
          List<Coord> coords = new ArrayList<>();
          for (int i = 0; i < vals.length / 2; i++) {
            try {
              Double x = Double.parseDouble(vals[2 * i]);
              Double y = Double.parseDouble(vals[2 * i + 1]);
              coords.add(new CoordXY(x, y));
            } catch (NumberFormatException ex) {
              LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            }
          }
          Part prt = new Part(coords);
          results.add(prt);
        }
      }
    }
    return results;
  }

  /**
   * Parses the line string.
   *
   * @param lineString
   * @return
   */
  private static Part parseLineStringXY(Element lineString) {
    Part result = new Part();
    Optional<Element> posList = getChildElement(lineString, "gml:posList");
    if (posList.isPresent()) {
      String text = posList.get().getTextContent();
      String[] vals = text.split(" ");
      List<Coord> coords = new ArrayList<>();
      for (int i = 0; i < vals.length / 2; i++) {
        try {
          Double x = Double.parseDouble(vals[2 * i]);
          Double y = Double.parseDouble(vals[2 * i + 1]);
          coords.add(new CoordXY(x, y));
        } catch (NumberFormatException ex) {
          LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
      }
      result.getCoordinates().addAll(coords);
    }
    return result;
  }

  /**
   * Gets the extent, expecting a lowerCorner and upperCorner.
   *
   * @param boundedBy The element boundedby.
   * @return An Envelope defined by boundedBy or an Empty Envelope.
   */
  private static Envelope getBounds(Element boundedBy) {
    Optional<Coord> lowerLeft;
    Optional<Coord> upperRight;
    List<Element> children = getChildElements(boundedBy, "gml:coord");
    if (!children.isEmpty()) {
      Element coords = children.get(0);
      Optional<Element> lowerCorner = getChildElement(coords, "gml:lowerCorner");
      if (lowerCorner.isPresent()) {
        lowerLeft = parseCoordXY(lowerCorner.get().getTextContent());
      } else {
        lowerLeft = Optional.empty();
      }

      Optional<Element> upperCorner = getChildElement(coords, "gml:upperCorner");
      if (upperCorner.isPresent()) {
        upperRight = parseCoordXY(upperCorner.get().getTextContent());
      } else {
        upperRight = Optional.empty();
      }
      if (lowerLeft.isPresent() && upperRight.isPresent()) {
        return new DefaultEnvelope(lowerLeft.get(), upperRight.get());
      }
    }
    return new DefaultEnvelope();
  }

  /**
   * Parses a single X and Y value separated by a single space.
   *
   * @param text The text formatted like 123.123 456.456
   * @return The Coordinate with the specifed X and Y values.
   */
  private static Optional<Coord> parseCoordXY(String text) {
    String[] vals = text.split(" ");
    Coord result = null;
    if (vals.length > 1) {
      Double x = null;
      Double y = null;
      try {
        x = Double.parseDouble(vals[0]);
      } catch (NumberFormatException ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      }
      if (x != null) {
        try {
          y = Double.parseDouble(vals[1]);
        } catch (NumberFormatException ex) {
          LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        if (y != null) {
          result = new CoordXY(x, y);
        }

      }

    }
    return Optional.ofNullable(result);
  }

  /**
   * Assumes there is only one child element of the specified name, this will
   * return either that element or null.
   *
   * @param parent the parent element
   * @return a single child element
   */
  private static Optional<Element> getChildElement(Element parent) {
    NodeList nodes = parent.getChildNodes();
    List<Element> children = new ArrayList<>();
    for (int i = 0; i < nodes.getLength(); i++) {
      Node node = nodes.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element el = (Element) node;
        children.add(el);
      }
    }
    if (children.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(children.get(0));
  }

  /**
   * Assumes there is only one child element of the specified name, this will
   * return either that element or null.
   *
   * @param parent
   * @param tagName
   * @return
   */
  private static Optional<Element> getChildElement(Element parent, String tagName) {
    NodeList nodes = parent.getElementsByTagName(tagName);
    List<Element> children = new ArrayList<>();
    for (int i = 0; i < nodes.getLength(); i++) {
      Node node = nodes.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element el = (Element) node;
        children.add(el);
      }
    }
    if (children.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(children.get(0));
  }

  private static List<Element> getChildElements(Element parent, String tagName) {
    NodeList nodes = parent.getElementsByTagName(tagName);
    List<Element> children = new ArrayList<>();
    for (int i = 0; i < nodes.getLength(); i++) {
      Node node = nodes.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element el = (Element) node;
        children.add(el);
      }
    }
    return children;
  }

  private static List<Element> getChildElements(Element parent) {
    NodeList nodes = parent.getChildNodes();
    List<Element> children = new ArrayList<>();
    for (int i = 0; i < nodes.getLength(); i++) {
      Node node = nodes.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element el = (Element) node;
        children.add(el);
      }
    }
    return children;
  }

    //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>
}
