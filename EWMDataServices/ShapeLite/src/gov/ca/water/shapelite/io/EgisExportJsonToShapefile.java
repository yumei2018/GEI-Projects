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
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.FileHelper;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import gov.ca.water.shapelite.projection.USLocale;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class EgisExportJsonToShapefile {

  /**
   * The number of digits past the decimal for number formatting.
   */
  private static final int DIGITS = 6;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * World Geodetic System 1984 (Latitude, Longitude).
   */
  private static final ProjectionInfo WGS84
      = Projections.getGeographic().getWorld().getWGS1984();

  /**
   * Web Mercator projected coordinate system.
   */
  private static final ProjectionInfo WEB
      = Projections.getProjected().getWorld().getWebMercator();

  //</editor-fold>
  /**
   * Private constructor for utility class.
   */
  private EgisExportJsonToShapefile() {

  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   *
   * @param jsonText The JSON in ESRI FeatureSet format, complete with
   * @param stream The output stream to write the zipped shapefiles to.
   * @throws gov.ca.water.shapelite.ShapefileException If there was an error
   * with the shapefiles.
   * @throws java.io.IOException If there was an error writing to the stream.
   */
  public static void toZippedShapefiles(String jsonText, OutputStream stream)
      throws ShapefileException, IOException {
    Gson gson = new GsonBuilder().create();
    FeatureSet points = new FeatureSet();
    points.setName("Points");
    points.setProjectionESRI(WGS84.toEsriString());
    FeatureSet lines = new FeatureSet();
    lines.setName("Lines");
    lines.setProjectionESRI(WGS84.toEsriString());
    FeatureSet polygons = new FeatureSet();
    polygons.setName("Polygons");
    polygons.setProjectionESRI(WGS84.toEsriString());
    LinkedTreeMap<String, Object>[] data = gson.fromJson(jsonText,
        new TypeToken<LinkedTreeMap<String, Object>[]>() {
    }.getType());

    for (LinkedTreeMap<String, Object> item : data) {
      if (item.containsKey("type")) {
        Object typeObject = item.get("type");
        if (typeObject instanceof String) {
          String type = (String) typeObject;
          if (type.toLowerCase().contains("point")) {
            addPoint(item, points);
          } else if (type.toLowerCase().contains("polyline")) {
            addPoly(item, lines, false);
          } else if (type.toLowerCase().contains("polygon")) {
            addPoly(item, polygons, true);
          }
        }
      }
    }
    try (ZipOutputStream zip = new ZipOutputStream(stream)) {
      ShapefileWriter out = new ShapefileWriter();
      if (points.getFeatures().size() > 0) {
        out.write(points, zip);
        addEGISMetadata(points, zip);
      }
      if (lines.getFeatures().size() > 0) {
        out.write(lines, zip);
        addEGISMetadata(lines, zip);
      }
      if (polygons.getFeatures().size() > 0) {
        out.write(polygons, zip);
        addEGISMetadata(polygons, zip);
      }
    }
  }

  /**
   * This metadata is specific to the USACE EGIS project and should not be used
   * for other projects.
   *
   * @param set The FeatureSet to add metadata to.
   * @param zip The output zipstream to write the xml files to.
   */
  private static void addEGISMetadata(FeatureSet set, ZipOutputStream zip) {
    String templatePath = null;
    if (set.getFeatures().get(0).getShape().getShapeType() == ShapeType.Point) {
      templatePath = "resources/Points.shp.xml";
    }
    if (set.getFeatures().get(0).getShape().getShapeType() == ShapeType.PolyLine) {
      templatePath = "resources/Lines.shp.xml";
    }
    if (set.getFeatures().get(0).getShape().getShapeType() == ShapeType.Polygon) {
      templatePath = "resources/Polygons.shp.xml";
    }
    if (templatePath == null) {
      return;
    }
    InputStream stream = EgisExportJsonToShapefile.class
        .getResourceAsStream(templatePath);
    String metadataText = FileHelper.readAll(stream);
    SimpleDateFormat date = new SimpleDateFormat("MMM dd, yyyy");
    SimpleDateFormat time = new SimpleDateFormat("h:mm a");
    SimpleDateFormat datestamp = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat timestamp = new SimpleDateFormat("HHmmss");
    Date now = new Date();
    metadataText = metadataText.replaceAll(Pattern.quote("[[DATE]]"),
        date.format(now));
    metadataText = metadataText.replaceAll(Pattern.quote("[[TIME]]"),
        time.format(now));
    metadataText = metadataText.replaceAll(Pattern.quote("[[YYYYMMDD]]"),
        datestamp.format(now));
    metadataText = metadataText.replaceAll(Pattern.quote("[[HHMMSS]]"),
        timestamp.format(now));

    Envelope bounds = set.getEnvelope();
    metadataText = metadataText.replaceAll(Pattern.quote("[[WEST]]"),
        USLocale.format(bounds.getMin().getX(), DIGITS));
    metadataText = metadataText.replaceAll(Pattern.quote("[[EAST]]"),
        USLocale.format(bounds.getMax().getX(), DIGITS));
    metadataText = metadataText.replaceAll(Pattern.quote("[[NORTH]]"),
        USLocale.format(bounds.getMax().getY(), DIGITS));
    metadataText = metadataText.replaceAll(Pattern.quote("[[SOUTH]]"),
        USLocale.format(bounds.getMin().getY(), DIGITS));

    metadataText = metadataText.replaceAll(Pattern.quote("[[FEATURECOUNT]]"),
        USLocale.format(set.getFeatures().size()));
    metadataText = metadataText.replaceAll(Pattern.quote("[[METAID]]"),
        UUID.randomUUID().toString());

    String attributes = "";
    for (Field fld : set.getFields()) {
      attributes += getAttributeMetadata(fld);
    }
    metadataText = metadataText.replaceAll(Pattern.quote("[[ATTRIBUTES]]"),
        attributes);
    metadataText = metadataText.replaceAll(Pattern.quote("[[ATTCNT]]"),
        USLocale.format(set.getFields().size()));
    try {
      zip.putNextEntry(new ZipEntry(set.getName() + ".shp.xml"));
      PrintWriter w = new PrintWriter(zip);
      w.write(metadataText);
      w.flush();
      zip.closeEntry();
    } catch (IOException ex) {
      Logger.getLogger(EgisExportJsonToShapefile.class.getName()).log(
          Level.SEVERE, ex.getMessage(), ex);
    }

  }

  /**
   * Get Attribute Metadata from the specified field as a string.
   *
   * @param fld The Field to get data for.
   * @return The String xml representation of the field.
   */
  private static String getAttributeMetadata(Field fld) {
    String typename = "String";
    if ("N".equals(fld.getType())) {
      typename = "Number";
    }
    String result
        = "            <attr>\n"
        + "                <attrlabl Sync=\"TRUE\">"
        + fld.getName() + "</attrlabl>\n"
        + "                <attalias Sync=\"TRUE\">"
        + fld.getName() + "</attalias>\n"
        + "                <attrtype Sync=\"TRUE\">"
        + typename + "</attrtype>\n"
        + "                <attwidth Sync=\"TRUE\">"
        + fld.getLength() + "</attwidth>\n"
        + "            </attr>\n";
    return result;
  }

  /**
   * Adds a point to the featureset based on the linked tree map items.
   *
   * @param item The item to parse.
   * @param points The featureset to add points to.
   * @return The Feature that was created.
   */
  private static Feature addPoint(LinkedTreeMap<String, Object> item,
      FeatureSet points) {
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
            wgs = Reproject.reprojectCoordinate(merc, WEB, WGS84);
            Shape shape = new Shape(wgs);
            point.setShape(shape);
          } catch (ProjectionException ex) {
            Logger.getLogger(EgisExportJsonToShapefile.class.getName()).log(
                Level.SEVERE, ex.getMessage(), ex);
          }

        }
      }
    }

    addAttributes(item, point, points);

    points.getFeatures().add(point);
    return point;

  }

  /**
   * Adds the specified item to the polygon or polyline to the featureset. If
   * isClosed is true, then this method adds a polygon, otherwise, it adds a
   * polyline.
   *
   * @param item The line/polygon to parse from json.
   * @param polygons The featureset of polylines or polygons.
   * @param isClosed Boolean, if true, then polygons are created, if false,
   * polylines are created.
   * @return The Feature that was created.
   */
  private static Feature addPoly(LinkedTreeMap<String, Object> item,
      FeatureSet polygons, boolean isClosed) {
    Feature polygon = new Feature();
    if (item.containsKey("geometry")) {
      Object geometryObject = item.get("geometry");
      if (geometryObject instanceof LinkedTreeMap<?, ?>) {

        LinkedTreeMap<?, ?> geometry = (LinkedTreeMap<?, ?>) geometryObject;
        Shape polygonShape = new Shape(ShapeType.Polygon);
        String key = "paths";
        if (isClosed) {
          key = "rings";
        }
        Object ringsObject = geometry.get(key);
        if (ringsObject instanceof ArrayList<?>) {
          ArrayList<?> rings = (ArrayList<?>) ringsObject;
          for (Object coordsObject : rings) {
            Part part = new Part();
            if (coordsObject instanceof ArrayList<?>) {
              ArrayList<?> coords = (ArrayList<?>) coordsObject;
              Object xObject = coords.get(0);
              Object yObject = coords.get(1);
              if (xObject instanceof Double && yObject instanceof Double) {

                Double xMerc = (Double) xObject;
                Double yMerc = (Double) yObject;
                Coord merc = new CoordXY(xMerc, yMerc);
                Coord wgs;
                try {
                  wgs = Reproject.reprojectCoordinate(merc, WEB, WGS84);
                  part.getCoordinates().add(wgs);
                } catch (ProjectionException ex) {
                  Logger.getLogger(EgisExportJsonToShapefile.class.getName()).log(
                      Level.SEVERE, ex.getMessage(), ex);
                }
              }
              polygonShape.getParts().add(part);
            }

          }
        }

        polygonShape.calculateBounds();
        polygon.setShape(polygonShape);
      }
    }

    addAttributes(item, polygon, polygons);

    polygons.getFeatures().add(polygon);

    return polygon;

  }

  /**
   * This method will add the attributes, parsed from the tree map to the
   * feature specified.
   *
   * @param item The linked tree map of items.
   * @param feature The actual feature to update.
   * @param featureSet The featureset defining the field definitions.
   */
  private static void addAttributes(LinkedTreeMap<String, Object> item,
      Feature feature, FeatureSet featureSet) {
    if (item.containsKey("attributes")) {
      Object attObject = item.get("attributes");
      if (attObject instanceof LinkedTreeMap<?, ?>) {
        LinkedTreeMap<?, ?> atts = (LinkedTreeMap<?, ?>) attObject;
        for (Entry<?, ?> entry : atts.entrySet()) {
          Field field = null;
          for (Field fld : featureSet.getFields()) {
            if (fld.getName().equals(entry.getKey())) {
              field = fld;
            }
          }
          String name = null;
          if (field == null) {
            field = new Field();
            Object nameObject = entry.getKey();
            if (nameObject instanceof String) {
              name = (String) nameObject;
              field.setName(name);
            }
            if (entry.getValue() instanceof Number) {
              field.setType("N");
            } else {
              field.setType("C");
            }
            featureSet.getFields().add(field);
          }
          String val = entry.getValue().toString();
          if (field.getLength() < val.length()) {
            field.setLength(val.length());
          }
          if (name != null) {
            feature.getAttributes().put(name, entry.getValue().toString());
          }

        }
      }

    }

  }

  //</editor-fold>
}
