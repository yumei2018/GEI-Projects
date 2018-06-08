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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import de.micromata.opengis.kml.v_2_2_0.Data;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.ExtendedData;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.IconStyle;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LineString;
import de.micromata.opengis.kml.v_2_2_0.LineStyle;
import de.micromata.opengis.kml.v_2_2_0.LinearRing;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.PolyStyle;
import de.micromata.opengis.kml.v_2_2_0.Style;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class EgisExportJsonToKML {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private static final ProjectionInfo wgs84 = Projections.getGeographic().getWorld().getWGS1984();
  private static final ProjectionInfo webmercator = Projections.getProjected().getWorld().getWebMercator();

  //</editor-fold>
  /**
   * Creates a new instance of the JsonToShapefile class.
   */
  private EgisExportJsonToKML() {

  }

  /**
   * This creates a zipped export file.
   *
   * @param jsonText The JSON string in EGIS format.
   * @param stream The OutputStream.
   * @param filename The String filename of the KML file inside the zip file.
   * @throws java.io.IOException if there was an error writing to the stream.
   */
  public static void toKMZ(String jsonText, OutputStream stream, String filename)
      throws IOException {

    try (ZipOutputStream zip = new ZipOutputStream(stream)) {
      zip.putNextEntry(new ZipEntry(filename));
      Kml kml = toKML(jsonText);
      kml.marshal(zip);
      zip.flush();
    }
  }

  /**
   * This creates a new kml file.
   *
   * @param jsonText The JSON string in EGIS format.
   * @param stream The filename for this stream should just be the KML file.
   * @throws gov.ca.water.shapelite.ShapefileException
   * @throws java.io.IOException
   */
  public static void toKML(String jsonText, OutputStream stream)
      throws ShapefileException, IOException {
    Kml kml = toKML(jsonText);
    kml.marshal(stream);
    stream.flush();
  }

  /**
   * Creates the Kml object that can be marshaled.
   *
   * @param jsonText The jsonText to export.
   * @return Kml.
   */
  private static Kml toKML(String jsonText) {
    Gson gson = new GsonBuilder().create();
    LinkedTreeMap<String, Object>[] data = gson.fromJson(jsonText,
        new TypeToken<LinkedTreeMap<String, Object>[]>() {
    }.getType());
    final Kml kml = new Kml();
    Document document = kml.createAndSetDocument().withName("MyMarkers");
    for (LinkedTreeMap<String, Object> item : data) {
      if (item.containsKey("type")) {
        Object typeObject = item.get("type");
        if (typeObject instanceof String) {
          String type = (String) typeObject;
          if (type.toLowerCase().contains("point")) {
            addPoint(item, "point", document);
          } else if (type.toLowerCase().contains("polyline")) {
            addLineString(item, "line", document);
          } else if (type.toLowerCase().contains("polygon")) {
            addPolygon(item, "polygon", document);
          }
        }
      }
    }
    return kml;
  }

  /**
   * Adds a point.
   *
   * @param item
   * @param name
   * @param document
   * @return
   */
  private static Feature addPoint(LinkedTreeMap<String, Object> item,
      String name, Document document) {
    Feature point = new Feature();
    if (item.containsKey("geometry")) {
      Object geometryObject = item.get("geometry");
      if (geometryObject instanceof LinkedTreeMap<?, ?>) {

        LinkedTreeMap<?, ?> geometry = (LinkedTreeMap<?, ?>) geometryObject;
        Object xObject = geometry.get("x");
        Object yObject = geometry.get("y");
        if (xObject instanceof Double && yObject instanceof Double) {
          Double xMerc = (Double) xObject;
          Double yMerc = (Double) yObject;
          Coord merc = new CoordXY(xMerc, yMerc);
          Coord wgs;
          try {
            wgs = Reproject.reprojectCoordinate(merc, webmercator, wgs84);
            Placemark mark = document.createAndAddPlacemark().withName(name).
                withOpen(Boolean.TRUE);
            mark.createAndSetPoint().addToCoordinates(wgs.getX(), wgs.getY());
            addAttributes(item, mark);
            addStyle(item, mark);
          } catch (ProjectionException ex) {
            Logger.getLogger(EgisExportJsonToKML.class.getName()).log(
                Level.SEVERE, ex.getMessage(), ex);
          }

        }

      }
    }
    return point;

  }

  /**
   * Not fully implemented
   *
   * @param item
   * @param mark
   */
  private static void addAttributes(LinkedTreeMap<String, Object> item, Placemark mark) {
    if (item.containsKey("attributes")) {
      ExtendedData extendedData = new ExtendedData();
      List<Data> dataList = new ArrayList<>();
      extendedData.setData(dataList);
      Object attObject = item.get("attributes");
      if (attObject instanceof LinkedTreeMap<?, ?>) {
        LinkedTreeMap<?, ?> atts = (LinkedTreeMap<?, ?>) attObject;
        for (Entry<?, ?> entry : atts.entrySet()) {
          Object key = entry.getKey();
          if (key instanceof String) {
            String fieldName = (String) key;
            if (fieldName.equals("content") || fieldName.equals("title")) {
              Data data = new Data(fieldName);
              if (fieldName.equals("content")) {
                data.setDisplayName("NAME");
                data.setName("NAME");
              } else {
                data.setDisplayName("COMMENTS");
                data.setName("COMMENTS");
              }
              dataList.add(data);
              data.setValue(entry.getValue().toString());
            }
          }

        }
      }

      mark.setExtendedData(extendedData);
    }

  }

  /**
   * Not fully implemented
   *
   * @param item
   * @param mark
   */
  private static void addStyle(LinkedTreeMap<String, Object> item, Placemark mark) {
    if (item.containsKey("symbol")) {
      Object symbolObject = item.get("symbol");
      if (symbolObject instanceof LinkedTreeMap<?, ?>) {
        LinkedTreeMap<?, ?> symbol = (LinkedTreeMap<?, ?>) symbolObject;

        String url = null;
        double width = 0, height = 0;
        Object fillColor = null;
        Object outlineColor = null;

        // POINTS
        if (symbol.containsKey("height")) {
          // line width for lines is stored as an integer value
          Object heightObject = symbol.get("height");
          if (heightObject instanceof Double) {
            height = ((Double) symbol.get("height"));
          }
        }
        if (symbol.containsKey("url")) {
          Object urlObject = symbol.get("url");
          if (urlObject instanceof String) {
            url = (String) symbol.get("url");
          }
        }

        // POINTS &  LINES
        if (symbol.containsKey("width")) {
          Object widthObject = symbol.get("width");
          if (widthObject instanceof Double) {
            // line width for lines is stored as an integer value
            width = ((Double) symbol.get("width"));
          }

        }

        // POLYGONS & LINES
        if (symbol.containsKey("color")) {
          // color value is stored in an array like [255,0,0,255]
          fillColor = symbol.get("color");
        }

        // POLYGONS
        if (symbol.containsKey("outline")) {
          Object outlineObject = symbol.get("outline");
          if (outlineObject instanceof LinkedTreeMap<?, ?>) {
            LinkedTreeMap<?, ?> outline = (LinkedTreeMap<?, ?>) outlineObject;
            if (outline.containsKey("color")) {
              outlineColor = outline.get("color");
            }
            if (outline.containsKey("width")) {
              Object widthObject = outline.get("width");
              if (widthObject instanceof Double) {
                width = ((Double) outline.get("width"));
              }
            }
          }
        }
        //Adds Style elements to the KML file
        Style style = new Style().withId("style");
        if (url != null) {
          style.withIconStyle(new IconStyle().withIcon(
              new Icon().withHref(url)).withScale(1.0));
        }
        //EGIS stores colors as COLOR[R,G,B,A] but KML stores it as hex in the format #abgr
        if (fillColor != null && fillColor instanceof ArrayList<?>) {

          ArrayList<?> colorArray = (ArrayList<?>) fillColor;
          Integer red = convertObjectToInt(colorArray.get(0)),
              blue = convertObjectToInt(colorArray.get(1)),
              green = convertObjectToInt(colorArray.get(2)),
              alpha = convertObjectToInt(colorArray.get(3));
          String hex = String.format("#%02x%02x%02x%02x", alpha, blue,
              green, red);
          style.withLineStyle(new LineStyle().withColor(hex));
          style.getLineStyle().setWidth(width);
        }
        if (outlineColor != null && fillColor instanceof ArrayList<?>) {

          ArrayList<?> colorArray = (ArrayList<?>) outlineColor;
          Integer red = convertObjectToInt(colorArray.get(0)),
              blue = convertObjectToInt(colorArray.get(1)),
              green = convertObjectToInt(colorArray.get(2)),
              alpha = convertObjectToInt(colorArray.get(3));
          String hex = String.format("#%02x%02x%02x%02x", alpha, blue,
              green, red);
          style.withPolyStyle(new PolyStyle().withColor(hex));
        }
        mark.addToStyleSelector(style);
      }

    }

  }

  /**
   * Converts an object to an integer, either by casting it or converting it.
   *
   * @param o The object to convert.
   * @return The Integer value to get.
   */
  private static Integer convertObjectToInt(Object o) {
    Integer intVal = null;
    if (o instanceof Double) {
      Double value = (Double) o;
      intVal = value.intValue();
    } else if (o instanceof Float) {
      Float value = (Float) o;
      intVal = value.intValue();
    } else if (o instanceof Integer) {
      intVal = (Integer) o;
    }
    return intVal;
  }

  /**
   * Adds a polygon to the KML document.
   *
   * @param item The LinkedTreeMap representing the JSON Polygon item.
   * @param name The String name of the item.
   * @param document The Document.
   */
  private static void addPolygon(LinkedTreeMap<String, Object> item, String name,
      Document document) {

    if (item.containsKey("geometry")) {
      Object geometryObject = item.get("geometry");
      if (geometryObject instanceof LinkedTreeMap<?, ?>) {

        LinkedTreeMap<?, ?> geometry = (LinkedTreeMap<?, ?>) geometryObject;
        String key = "rings";
        Object ringsObject = geometry.get(key);
        Placemark mark = document.createAndAddPlacemark().withName(name).
            withOpen(Boolean.TRUE);
        addAttributes(item, mark);
        addStyle(item, mark);
        if (ringsObject instanceof ArrayList<?>) {
          ArrayList<?> rings = (ArrayList<?>) ringsObject;
          for (Object ringObject : rings) {

            LinearRing kmlRing = mark.createAndSetPolygon().
                createAndSetOuterBoundaryIs().createAndSetLinearRing();
            if (ringObject instanceof ArrayList<?>) {
              ArrayList<?> ring = (ArrayList<?>) ringObject;
              for (Object coordsObject : ring) {
                if (coordsObject instanceof ArrayList<?>) {
                  ArrayList<?> coords = (ArrayList<?>) coordsObject;
                  Object xobject = (Object) coords.get(0);
                  Object yobject = (Object) coords.get(1);
                  if (xobject instanceof Double && yobject instanceof Double) {
                    Double xMerc = (Double) xobject;
                    Double yMerc = (Double) yobject;
                    Coord merc = new CoordXY(xMerc, yMerc);
                    Coord wgs;
                    try {
                      wgs = Reproject.reprojectCoordinate(merc, webmercator, wgs84);
                      kmlRing.addToCoordinates(wgs.getX(), wgs.getY());
                    } catch (ProjectionException ex) {
                      System.out.println("Failed to reproject x: " + xMerc
                          + ", y: " + yMerc + " to WGS84.");
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  /**
   * Adds a line to the document.
   *
   * @param item The LinkedTreeMap containing the JSON line item to add.
   * @param name The String name of the placemark.
   * @param document The KML document.
   */
  private static void addLineString(LinkedTreeMap<String, Object> item,
      String name, Document document) {

    if (item.containsKey("geometry")) {
      Object geometryObject = item.get("geometry");
      if (geometryObject instanceof LinkedTreeMap<?, ?>) {

        LinkedTreeMap<?, ?> geometry = (LinkedTreeMap<?, ?>) geometryObject;
        String key = "paths";
        Object ringsObject = geometry.get(key);
        Placemark mark = document.createAndAddPlacemark().
            withName(name).withOpen(Boolean.TRUE);
        addAttributes(item, mark);
        addStyle(item, mark);
        if (ringsObject instanceof ArrayList<?>) {
          ArrayList<?> rings = (ArrayList<?>) ringsObject;
          for (Object coordsObject : rings) {
            LineString ls = mark.createAndSetLineString();
            ArrayList<?> coords = (ArrayList<?>) coordsObject;
            Object xMercObject = coords.get(0);
            Object yMercObject = coords.get(1);
            if (xMercObject instanceof Double && yMercObject instanceof Double) {
              Double xMerc = (Double) xMercObject;
              Double yMerc = (Double) yMercObject;
              Coord merc = new CoordXY(xMerc, yMerc);
              Coord wgs;
              try {
                wgs = Reproject.reprojectCoordinate(merc, webmercator, wgs84);
                ls.addToCoordinates(wgs.getX(), wgs.getY());
              } catch (ProjectionException ex) {
                Logger.getLogger(EgisExportJsonToKML.class.getName()).log(
                    Level.SEVERE, ex.getMessage(), ex);
              }
            }
          }
        }
        addAttributes(item, mark);
      }
    }

  }

}
