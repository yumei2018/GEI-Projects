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
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GmlParser extends DefaultHandler {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private Feature currentFeature;
  private Part currentPart;
  private FeatureHandler handler;
  private boolean suspended;
  private StringBuilder curCharValue;
  private String numDimensions;
  private boolean endOfDocument;
  private static final Logger LOGGER = Logger.getLogger(GmlParser.class.getName());
  private int depth;

  //</editor-fold>
  /**
   * Creates a new instance of the GmlParser class.
   *
   * @param handler
   */
  public GmlParser(FeatureHandler handler) {
    this.handler = handler;
    this.depth = 0;
  }

    //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    depth++;

    if ("gml:featureMember".equals(qName)) {
      currentFeature = new Feature();
      currentFeature.setShape(new Shape(ShapeType.PolyLine));
    }
    if ("gml:Polygon".equals(qName)) {
      currentFeature.setShape(new Shape(ShapeType.Polygon));
    }
    if ("gml:LinearRing".equals(qName) || "gml:LineString".equals(qName)) {
      currentPart = new Part();
      currentFeature.getShape().getParts().add(currentPart);
    }
    if ("gml:Point".equals(qName)) {
      currentPart = new Part();
      currentFeature.setShape(new Shape(ShapeType.Point));
      currentFeature.getShape().getParts().add(currentPart);
    }
    if ("gml:posList".equals(qName) || "gml:pos".equals(qName)) {
      numDimensions = attributes.getValue("srsDimension");
      ShapeType type = currentFeature.getShape().getShapeType();
      if ("3".equals(numDimensions)) {
        if (type == ShapeType.Polygon) {
          currentFeature.getShape().setShapeType(ShapeType.PolygonZ);
        }
        if (type == ShapeType.PolyLine) {
          currentFeature.getShape().setShapeType(ShapeType.PolyLineZ);
        }
        if (type == ShapeType.Point) {
          currentFeature.getShape().setShapeType(ShapeType.PointZ);
        }
      }
      curCharValue = new StringBuilder();
    }
    if (depth == 4 && qName != null && !qName.startsWith("gml:")) {
      curCharValue = new StringBuilder();
    }

  }

  @Override
  public void endDocument() throws SAXException {
    super.endDocument();

  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    if (curCharValue != null) {
      if (ch != null) {
        curCharValue.append(ch, start, length);
      }
    }

  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    depth--;
    if ("gml:featureMember".equals(qName)) {
      currentFeature.getShape().calculateBounds();
      handler.featureCreated(currentFeature);
      try {
        synchronized (this) {
          while (suspended) {
            this.wait();
          }
        }
      } catch (InterruptedException ex) {

      }
    }
    if ("gml:posList".equals(qName) || "gml:pos".equals(qName)) {
      String coordText = curCharValue.toString();
      if ("2".equals(numDimensions)) {
        parseCoordinatesXY(coordText);
      }
      if ("3".equals(numDimensions)) {
        parseCoordinatesXYZ(coordText);
      }
    }
    if (currentFeature != null) {
      if (depth == 3 && qName != null && !qName.startsWith("gml:")) {
        String name = qName;
        if (qName.startsWith("hec:")) {
          name = qName.substring(4);
        }
        currentFeature.getAttributes().put(name, curCharValue.toString());
      }
    }

  }

  private void parseCoordinatesXY(String coordText) {
    String[] vals = coordText.split(" ");
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
    currentPart.getCoordinates().addAll(coords);
    currentFeature.getShape().calculateBounds();
  }

  private void parseCoordinatesXYZ(String coordText) {
    String[] vals = coordText.split(" ");
    List<Coord> coords = new ArrayList<>();
    for (int i = 0; i < vals.length / 3; i++) {
      try {
        Double x = Double.parseDouble(vals[3 * i]);
        Double y = Double.parseDouble(vals[3 * i + 1]);
        Double z = Double.parseDouble(vals[3 * i + 2]);
        coords.add(new CoordXY(x, y));
      } catch (NumberFormatException ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      }
    }
    currentPart.getCoordinates().addAll(coords);
    currentFeature.getShape().calculateBounds();
  }

  /**
   * @return the suspended
   */
  public boolean isSuspended() {
    return suspended;
  }

  /**
   * @param suspended the suspended to set
   */
  public void setSuspended(boolean suspended) {
    this.suspended = suspended;
  }

  /**
   * @return the endOfDocument
   */
  public boolean isEndOfDocument() {
    return endOfDocument;
  }

  /**
   * @param endOfDocument the endOfDocument to set
   */
  public void setEndOfDocument(boolean endOfDocument) {
    this.endOfDocument = endOfDocument;
  }

}
