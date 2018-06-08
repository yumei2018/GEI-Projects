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
package gov.ca.water.shapelite.topology;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.Nullable;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.utils.JTSUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * The function of the Adapter is to allow a smooth transition between Shapelite shapes and JTS topologies.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class Adapter {

  /**
   * Hidden constructor.
   */
  private Adapter() {

  }

  /**
   * If there is more than one shape in the featureset, this will create a multi-geometry that contains all the
   * features.
   *
   * @param featureSet The feature set. If the feature set is empty, then an empty GeometryCollection is returned.
   * @return A Geometry or Multi-Geometry with all the combined features from the feature set.
   */
  public static Geometry getGeometry(FeatureSet featureSet) {
    Collection<Geometry> geometries = new ArrayList<>();
    GeometryFactory gf = new GeometryFactory();
    for (Feature feature : featureSet.getFeatures()) {
      Optional<Geometry> maybeGeom = getGeometry(feature.getShape());
      if (maybeGeom.isPresent()) {
        Geometry shapeGeom = maybeGeom.get();
        geometries.add(shapeGeom);
      }
    }
    return gf.buildGeometry(geometries);
  }

  /**
   * Creates a Geometry from the Specified shape.
   *
   * @param shape The shape to turn into a JTS Geometry.
   * @return A GTS Geometry whose type is determined by the shape type.
   */
  public static Optional<Geometry> getGeometry(Shape shape) {
    switch (shape.getShapeType()) {
      case Polygon:
      case PolygonM:
      case PolygonZ:
        return getPolygonGeometry(shape);
      case PolyLine:
      case PolyLineM:
      case PolyLineZ:
        return getLineGeometry(shape);
      case MultiPoint:
      case MultiPointM:
      case MultiPointZ:
        return getMultiPointGeometry(shape);
      case Point:
      case PointM:
      case PointZ:
        return getPointGeometry(shape);
      default:
        return Optional.empty();
    }
  }

  /**
   * Gets a shape from the geometry. This may be a ShapeType.NullShape if the geometry was not correctly recognized.
   *
   * @param geom the JTS Geometry to convert into a Shape object.
   * @param hasM True if the output shapes should be shape type M.
   * @param hasZ True if the output shapes should be shape type Z.
   * @return A new Shape object representing the same input geometry.
   */
  public static Shape getShape(Geometry geom, boolean hasM, boolean hasZ) {
    if (geom instanceof GeometryCollection) {
      boolean hasPolygon = false;
      boolean hasLine = false;
      for (int i = 0; i < geom.getNumGeometries(); i++) {
        Geometry g = geom.getGeometryN(i);
        if (g instanceof Polygon) {
          hasPolygon = true;
          break;
        } else if (g instanceof LineString) {
          hasLine = true;
        }
      }
      if (hasPolygon) {
        return getPolygonShape(geom, hasM, hasZ);
      } else if (hasLine) {
        return getMultiLineShape(hasZ, hasM, geom);
      } else {
        return getMultiPointShape(hasZ, hasM, geom);
      }
    }
    if (geom instanceof Polygon || geom instanceof MultiPolygon) {
      return getPolygonShape(geom, hasM, hasZ);
    }
    if (geom instanceof LineString) {
      Shape resultShape;
      if (hasZ) {
        resultShape = new Shape(ShapeType.PolyLineZ);
      } else if (hasM) {
        resultShape = new Shape(ShapeType.PolyLineM);
      } else {
        resultShape = new Shape(ShapeType.PolyLine);
      }

      Part part = new Part(getCoords(geom.getCoordinates(), hasZ));
      resultShape.getParts().add(part);
      resultShape.calculateBounds();
      return resultShape;
    }
    if (geom instanceof MultiLineString) {
      return getMultiLineShape(hasZ, hasM, geom);
    }
    if (geom instanceof Point) {
      Shape resultShape;
      if (hasZ) {
        resultShape = new Shape(ShapeType.PointZ);
      } else if (hasM) {
        resultShape = new Shape(ShapeType.PointM);
      } else {
        resultShape = new Shape(ShapeType.Point);
      }

      resultShape.first().setX(geom.getCoordinate().x);
      resultShape.first().setY(geom.getCoordinate().y);
      if (hasZ) {
        resultShape.first().set(Coord.Index.Z, geom.getCoordinate().z);
      }
      resultShape.calculateBounds();
      return resultShape;
    }
    if (geom instanceof MultiPoint) {
      Shape resultShape = getMultiPointShape(hasZ, hasM, geom);
      return resultShape;
    }
    return new Shape(ShapeType.NullShape);
  }

  private static Shape getMultiPointShape(boolean hasZ, boolean hasM, Geometry geom) {
    Shape resultShape;
    if (hasZ) {
      resultShape = new Shape(ShapeType.MultiPointZ);
    } else if (hasM) {
      resultShape = new Shape(ShapeType.MultiPointM);
    } else {
      resultShape = new Shape(ShapeType.MultiPoint);
    }
    List<Coord> coords = getCoords(geom.getCoordinates(), hasZ);
    Part part = new Part(coords);
    resultShape.getParts().add(part);
    resultShape.calculateBounds();
    return resultShape;
  }

  private static Shape getMultiLineShape(boolean hasZ, boolean hasM, Geometry geom) {
    Shape resultShape;
    if (hasZ) {
      resultShape = new Shape(ShapeType.PolyLineZ);
    } else if (hasM) {
      resultShape = new Shape(ShapeType.PolyLineM);
    } else {
      resultShape = new Shape(ShapeType.PolyLine);
    }
    for (int i = 0; i < geom.getNumGeometries(); i++) {
      if (geom instanceof LineString) {
        LineString ls = (LineString) geom.getGeometryN(i);
        Part part = new Part(getCoords(ls.getCoordinates(), hasZ));
        resultShape.getParts().add(part);
      }
    }
    resultShape.calculateBounds();
    return resultShape;
  }

  /**
   * Gets an Array of coordinates from all of the coordinates for a shape.
   *
   * @param shape The Shape to get a coordinate array for.
   * @return An array of JTS Coordinate objects, representing all the coordinates from the shape.
   */
  public static Coordinate[] getCoordinates(Shape shape) {
    return getCoordinates(shape.getCoordinates());
  }

  /**
   * Given a list of ShapeLite.Coord objects, this will return an array of Coordinate objects.
   *
   * @param coords The list to return.
   * @return An Array of JTS Coordinate objects.
   */
  public static Coordinate[] getCoordinates(List<Coord> coords) {
    Coordinate[] coordinates = new Coordinate[coords.size()];
    for (int i = 0; i < coordinates.length; i++) {
      coordinates[i] = getCoordinate(coords.get(i));
    }
    return coordinates;
  }

  /**
   * Given a ShapeLite Coord class, this creates a single JTS Coordinate.
   *
   * @param coord The Coord object representing x and y values.
   * @return A JTS Coordinate matching the specified Coord.
   */
  public static Coordinate getCoordinate(Coord coord) {
    Coordinate point = new Coordinate();
    point.x = coord.getX();
    point.y = coord.getY();
    if (coord.hasZ()) {
      point.z = coord.get(Coord.Index.Z);
    }

    return point;
  }

  /**
   * Given a part, this will return an array of JTS Coordinate objects representing the coordinates in the part. M and Z
   * values may be l
   *
   * @param part A ShapeLite Part object to get JTS Coordinates for.
   * @return An Array of JTS Coordinates.
   */
  public static Coordinate[] getCoordinates(Part part) {
    List<Coord> coords = new ArrayList<>(part.getCoordinates());
    if (part.isClosed()) {
      coords.add(part.getCoordinates().get(0));
    }
    return getCoordinates(coords);
  }

  /**
   * Converts the array of JTS Coordinate objects into a List of ShapeLite Coord objects, conditionally using z if the
   * usingZ property is set to true.
   *
   * @param coordinates The array of Coordinate objects to convert.
   * @param hasZ A boolean that is true if the created shape should have Z values.
   * @return A List of ShapeLite Coord objects representing the coordinates.
   */
  public static List<Coord> getCoords(Coordinate[] coordinates, boolean hasZ) {
    List<Coord> resultCoords = new ArrayList<>();
    for (Coordinate c : coordinates) {
      if (hasZ) {
        resultCoords.add(new CoordZ(c.x, c.y, c.z));
      } else {
        resultCoords.add(new CoordXY(c.x, c.y));
      }
    }

    return resultCoords;
  }

  /**
   * Converts the array of JTS Coordinate objects into a List of ShapeLite Coord objects, conditionally using z if the
   * usingZ property is set to true.
   *
   * @param c Coordinate objects to convert
   * @param hasZ A boolean that is true if the created shape should have Z values.
   * @return A List of ShapeLite Coord objects representing the coordinates.
   */
  public static Coord getCoord(Coordinate c, boolean hasZ) {
    Coord resultCoords;
    if (hasZ) {
      resultCoords = new CoordZ(c.x, c.y, c.z);
    } else {
      resultCoords = new CoordXY(c.x, c.y);
    }
    return resultCoords;
  }

  /**
   * Gets a Polygon or MultiPolygon Geometry from a polygon shape.
   *
   * @param shape The shape to convert to a polygon or multi-polygon.
   * @return A Geometry that can be a Polygon or MultiPolygon.
   */
  private static Optional<Geometry> getPolygonGeometry(@Nullable Shape shape) {
    Optional<Geometry> result = Optional.empty();
    if (shape == null || shape.isEmpty()) {
      return result;
    }
    List<Poly> polys = new ArrayList<>();
    List<Hole> unfoundHoles = new ArrayList<>();
    GeometryFactory factory = new GeometryFactory();
    for (Part part : shape.getParts()) {
      Coordinate[] coordinates = getCoordinates(part);
      if (!(coordinates == null || coordinates.length == 0 || coordinates.length > 1)) {
        continue;
      }
      LinearRing ring = factory.createLinearRing(coordinates);
      if (part.isHole()) {
        Hole h = new Hole(part, ring);
        unfoundHoles.add(h);
      } else {
        Poly p = new Poly(part, ring);
        polys.add(p);
      }
    }
    for (Hole hole : unfoundHoles) {
      Polygon pHole = factory.createPolygon(hole.getRing());
      boolean found = false;
      for (Poly poly : polys) {
        Polygon pShell = factory.createPolygon(poly.getRing());
        if (pShell.contains(pHole)) {
          poly.getHoles().add(hole);
          found = true;
          break;
        }
      }
      if (!found) {
        // Any hole that is not contained has no choice but to become a shell.
        Poly p = new Poly(hole.getPart(), hole.getRing());
        polys.add(p);
      }
    }
    Geometry polygon = null;
    if (polys.size() == 1) {
      Poly p = polys.get(0);
      Optional<LinearRing[]> holeSet = p.getHoleRings();
      if (holeSet.isPresent()) {
        polygon = factory.createPolygon(p.getRing(), holeSet.get());
      } else {
        polygon = factory.createPolygon(p.getRing());
      }
    } else {
      Polygon[] polygons = new Polygon[polys.size()];
      for (int i = 0; i < polys.size(); i++) {
        Poly p = polys.get(i);
        Optional<LinearRing[]> holeSet = p.getHoleRings();
        if (holeSet.isPresent()) {
          polygons[i] = factory.createPolygon(p.getRing(), holeSet.get());
        } else {
          polygons[i] = factory.createPolygon(p.getRing());
        }
      }
      polygon = factory.createMultiPolygon(polygons);
    }
    //polygon = JTSUtils.fix(polygon);
    return Optional.ofNullable(polygon);

  }

  /**
   * Gets a LineString or MultiLineString geometry from a shape.
   *
   * @param shape the ShapeLight shape
   * @return A JTS Geometry that can be a LineString or a multi-geom string.
   */
  private static Optional<Geometry> getLineGeometry(@Nullable Shape shape) {
    if (shape == null || shape.isEmpty()) {
      return Optional.empty();
    }
    GeometryFactory gf = new GeometryFactory();
    Geometry geom;
    if (shape.getParts().size() == 1) {
      LineString ls = gf.createLineString(getCoordinates(shape.getParts().get(0)));
      geom = ls;
    } else {
      LineString[] lines = new LineString[shape.getParts().size()];
      for (int i = 0; i < shape.getParts().size(); i++) {
        LineString line = gf.createLineString(getCoordinates(shape.getParts().get(i)));
        lines[i] = line;
      }
      geom = gf.createMultiLineString(lines);
    }
    return Optional.ofNullable(geom);
  }

  /**
   * Gets a LineString or MultiLineString geometry from a shape.
   *
   * @param shape the ShapeLight shape to convert.
   * @return A JTS Geometry that should be a MultiPoint geometry.
   */
  private static Optional<Geometry> getMultiPointGeometry(@Nullable Shape shape) {
    if (shape == null || shape.isEmpty()) {
      return Optional.empty();
    }
    GeometryFactory gf = new GeometryFactory();
    Geometry geom = gf.createMultiPoint(getCoordinates(shape.getCoordinates()));
    return Optional.ofNullable(geom);

  }

  /**
   * Gets a Point geometry from a Shape.
   *
   * @param shape the ShapeLite shape to convert.
   * @return The JTS Geometry.
   */
  private static Optional<Geometry> getPointGeometry(@Nullable Shape shape) {
    if (shape == null || shape.isEmpty()) {
      return Optional.empty();
    }
    GeometryFactory gf = new GeometryFactory();
    Geometry geom = gf.createPoint(getCoordinate(shape.getCoordinates().get(0)));
    return Optional.ofNullable(geom);
  }

  /**
   * Gets a Shape from a Geometry.
   *
   * @param geom A JTS Polygon or MultiPolygon geometry.
   * @return A ShapeLite Shape object representing the polygon or multi-polygon. This is guaranteed to not return null.
   * If geom is null, it may return a null shape.
   * @param hasM Boolean true if the created shape should have M values.
   * @param hasZ Boolean true if the created shape should have Z values.
   */
  private static Shape getPolygonShape(@Nullable Geometry geom, boolean hasM, boolean hasZ) {

    Shape resultShape;
    if (hasZ) {
      resultShape = new Shape(ShapeType.PolygonZ);
    } else if (hasM) {
      resultShape = new Shape(ShapeType.PolygonM);
    } else {
      resultShape = new Shape(ShapeType.Polygon);
    }
    if (geom instanceof Polygon) {
      Polygon polygon = (Polygon) geom;
      appendPolygon(polygon, resultShape);
    } else if (geom instanceof MultiPolygon || geom instanceof GeometryCollection) {
      for (int i = 0; i < geom.getNumGeometries(); i++) {
        if(geom.getGeometryN(i) instanceof Polygon){
          Polygon polygon = (Polygon) geom.getGeometryN(i);
          appendPolygon(polygon, resultShape);
        }
      }
    }
    resultShape.calculateBounds();
    if (resultShape.getCoordinates().isEmpty()) {
      return Shape.getNullShape();
    }
    return resultShape;
  }

  /**
   * Appends the specified polygon to the specified result shape.
   *
   * @param polygon The Polygon to append.
   * @param resultShape The result shape to modify.
   * @throws IllegalArgumentException if resultShape is null;
   */
  private static void appendPolygon(@Nullable Polygon polygon, Shape resultShape) {
    if (polygon == null) {
      return; // do nothing.
    }
    if (resultShape == null) {
      throw new IllegalArgumentException("Result Shape cannot be null.");
    }
    List<Coord> resultCoords = getCoords(polygon.getExteriorRing().getCoordinates(),
            resultShape.hasZ());
    Part resultPart = new Part(resultCoords);
    resultPart.setClosed(true);
    if (resultPart.isHole()) {
      Collections.reverse(resultPart.getCoordinates());
    }
    resultShape.getParts().add(resultPart);
    for (int j = 0; j < polygon.getNumInteriorRing(); j++) {
      List<Coord> holeCoords = getCoords(polygon.getInteriorRingN(j).getCoordinates(),
              resultShape.hasZ());
      Part holePart = new Part(holeCoords);
      holePart.setClosed(true);
      if (!holePart.isHole()) {
        Collections.reverse(holePart.getCoordinates());
      }
      resultShape.getParts().add(holePart);
    }
  }

  //<editor-fold defaultstate="collapsed" desc="Inner Classes">
  /**
   * A poly pairs together the shell and its part, and lists the holes.
   */
  public static class Poly {

    /**
     * A LinearRing ring around the polygon.
     */
    private LinearRing ring;

    /**
     * A list of Hole objects representing holes.
     */
    private List<Hole> holes;

    /**
     * The part for this polygon.
     */
    private Part part;

    /**
     * Creates a new instance of the Poly class.
     */
    public Poly() {
      holes = new ArrayList<>();
    }

    /**
     * Creates a new instance of the Poly class.
     *
     * @param part The ShapeLite part
     * @param shell the LinearRing
     */
    public Poly(Part part, LinearRing shell) {
      this.part = part;
      this.ring = shell;
      holes = new ArrayList<>();
    }

    /**
     * Gets the holes as an array of linear holeSet.
     *
     * @return An optional array of JTS LinearRing objects.
     */
    public final Optional<LinearRing[]> getHoleRings() {
      if (holes.isEmpty()) {
        return Optional.empty();
      }
      LinearRing[] result = new LinearRing[holes.size()];
      for (int i = 0; i < holes.size(); i++) {
        result[i] = holes.get(i).getRing();
      }
      return Optional.of(result);
    }

    // <editor-fold defaultstate="collapsed" desc="Properties">
    /**
     * Gets the LinearRing ring around the polygon.
     *
     * @return the ring
     */
    public final LinearRing getRing() {
      return ring;
    }

    /**
     * Sets the LinearRing ring around the polygon.
     *
     * @param ring the ring to set
     */
    public final void setRing(LinearRing ring) {
      this.ring = ring;
    }

    /**
     * @return the part
     */
    public final Part getPart() {
      return part;
    }

    /**
     * @param part the part to set
     */
    public final void setPart(Part part) {
      this.part = part;
    }

    /**
     * @return the holes
     */
    public final List<Hole> getHoles() {
      return holes;
    }

    /**
     * @param holes the holes to set
     */
    public final void setHoles(List<Hole> holes) {
      this.holes = holes;
    }

    // </editor-fold>
  }

  /**
   * A Hole pairs together the part with the linear ring.
   */
  public static class Hole {

    /**
     * The LinearRing that forms this hole.
     */
    private LinearRing ring;

    /**
     * The part that is associated with this hole.
     */
    private Part part;

    /**
     * Creates a new instance of the Hole class.
     */
    public Hole() {

    }

    /**
     * Creates a new instance of the Hole class.
     *
     * @param part the ShapeLite part
     * @param ring the JTS linear ring
     */
    public Hole(Part part, LinearRing ring) {
      this.part = part;
      this.ring = ring;
    }

    /**
     * @return the ring
     */
    public final LinearRing getRing() {
      return ring;
    }

    /**
     * @param ring the ring to set
     */
    public final void setRing(LinearRing ring) {
      this.ring = ring;
    }

    /**
     * @return the part
     */
    public final Part getPart() {
      return part;
    }

    /**
     * @param part the part to set
     */
    public final void setPart(Part part) {
      this.part = part;
    }

  }

  //</editor-fold>
}
