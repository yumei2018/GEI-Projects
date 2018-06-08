/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
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
import com.vividsolutions.jts.geom.Geometry;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.Nullable;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.topology.Adapter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is designed to rapidly go from a polygon specified by ArcGIS
 * javascript json format to a JTS Polygon to be used for intersections.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class JsonToShape {

  private static final Logger LOGGER
      = Logger.getLogger(JsonToShape.class.getName());

  /**
   * Gets the geometry directly from the specified stream.
   *
   * @param jsonStream The String JSON input stream.
   * @return An optional geometry that is present if the jsonStream could be
   * parsed correctly.
   */
  public static Optional<Geometry> getGeometry(@Nullable InputStream jsonStream) {
    Optional<Geometry> result = Optional.empty();
    if (jsonStream == null) {
      return result;
    }
    java.util.Scanner s = new java.util.Scanner(jsonStream).useDelimiter("\\A");
    if (s.hasNext()) {
      result = getGeometry(s.next());
    }
    return result;
  }

  /**
   * Gets the specified jsonText as a geometry.
   *
   * @param jsonText The String jsonText
   * @return The Geometry to get.
   */
  public static Optional<Geometry> getGeometry(String jsonText) {
    Optional<Geometry> result = Optional.empty();
    Optional<Shape> shape = getShape(jsonText);
    if (shape.isPresent()) {
      return Adapter.getGeometry(shape.get());
    }

    return result;
  }

  /**
   * This method attempts to reformat a polygon shape directly from JSON format
   * to a Shape object. This does not attempt to reproject the coordinates. This
   * only handles the geometry and does not handle any attributes.
   *
   * @param jsonText The string containing the json polygon to get the shape
   * for.
   * @return The Shape object.
   */
  public static Optional<Shape> getShape(String jsonText) {
    Gson gson = new GsonBuilder().create();
    LinkedTreeMap<String, Object> item
        = gson.fromJson(jsonText, new TypeToken<LinkedTreeMap<String, Object>>() {
        }.getType());
    Shape result = null;
    if (item.containsKey("type")) {
      Object typeObject = item.get("type");
      if (typeObject instanceof String) {
        String type = (String) typeObject;
        if (type.toLowerCase().contains("point")) {
          Optional<Shape> shp = getPoint(item);
          result = shp.orElse(null);
        } else if (type.toLowerCase().contains("polyline")) {
          result = getPoly(item, false);
        } else if (type.toLowerCase().contains("polygon")) {
          result = getPoly(item, true);
        }
      }
    }
    return Optional.ofNullable(result);
  }

  /**
   * Formats the specified LinkedTreeMap object as a Shape. This may return null
   * if the shape fails to parse the object.
   *
   * @param item
   * @return
   */
  private static Optional<Shape> getPoint(LinkedTreeMap<String, Object> item) {
    try {

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
            Shape shape = new Shape(merc);
            return Optional.of(shape);
          }
        }
      }
    } catch (Exception ex) {
      Logger.getLogger(JsonToShape.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
    return Optional.empty();
  }

  /**
   * This converts the linked tree map into either a linestring or polygon
   * shape.
   *
   * @param item
   * @param isClosed true for polygons, false for linestrings.
   * @return
   */
  private static Shape getPoly(LinkedTreeMap<String, Object> item, boolean isClosed) {

    Shape polygonShape = new Shape(ShapeType.PolyLine);
    String key = "paths";
    if (isClosed) {
      polygonShape.setShapeType(ShapeType.Polygon);
      key = "rings";
    }
    Object ringsObject = item.get(key);
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
            part.getCoordinates().add(merc);
          }
        }
        polygonShape.getParts().add(part);
      }
    }
    polygonShape.calculateBounds();
    return polygonShape;

  }

}
