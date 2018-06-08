/*
 * The MIT License
 *
 * Copyright 2012 hdunsford.
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
package gov.ca.water.shapelite;

import com.vividsolutions.jts.geom.Geometry;
import gov.ca.water.shapelite.analysis.PartIntersector;
import gov.ca.water.shapelite.analysis.PartIntersectorLine;
import gov.ca.water.shapelite.analysis.PartIntersectorPoint;
import gov.ca.water.shapelite.analysis.PartIntersectorPolygon;
import gov.ca.water.shapelite.coordinate.CoordM;
import gov.ca.water.shapelite.coordinate.CoordType;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.topology.Adapter;
import gov.ca.water.shapelite.utils.JTSUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class stores the basic geometries that make up points, multipoints,
 * polylines, and polygons.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Shape implements Serializable {

  /**
   * The integer value to use to break down the shape type values.
   */
  private static final int SHAPE_TYPE_SPLIT = 10;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The cached geographic extents of this shape. This will never be null
   * because it is final, and all constructors ensure an envelope.
   */
  @NonNull
  private final Envelope envelope;

  /**
   * A length in 4 byte words describing the size of this shape. This is used by
   * the index file.
   */
  private int contentLength;

  /**
   * An integer feature index representing the sequence of this shape in the
   * shapefile.
   */
  private int fid;

  /**
   * The list of parts that make up the shape. Parts are usually disconnected,
   * but can touch. For polygons, parts can also represent holes. This is final
   * to prevent any cases of null part arrays.
   */
  private final List<Part> parts;

  /**
   * The enumeration showing whether this is a point, multi-point, line or
   * polygon. This should never be null, and instead ShapeType.None should be
   * used.
   */
  @NonNull
  private ShapeType shapeType;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Creates a new empty instance of a Shape object with no parts. This by
   * default will create a shape that has no M or Z values.
   */
  public Shape() {
    this.envelope = new DefaultEnvelope();
    this.parts = new ArrayList<>();
    this.shapeType = ShapeType.NullShape;
  }

  /**
   * Creates a new empty instance of a Shape object with no parts. If hasZ is
   * true, the envelope will support Z coordinates. Otherwise If hasM is true,
   * the coordinates will be type CoordM.
   *
   * @param hasM If this is true, the envelope type will be CoordM.
   * @param hasZ If this is true, the envelope type will be CoordZ.
   */
  public Shape(boolean hasM, boolean hasZ) {
    this.envelope = new DefaultEnvelope(hasM, hasZ);
    this.parts = new ArrayList<>();
    this.shapeType = ShapeType.NullShape;
  }

  /**
   * Instantiates a new point shape from the specified coordinate.
   *
   * @param coord The coordinate at the center of the point. This can be null,
   * in which case this has the same behavior as the constructor called without
   * any parameters, defining the shape type as a NullShape. If the coordinates
   * are updated later, the shape type should be updated.
   */
  public Shape(@Nullable Coord coord) {
    this.parts = new ArrayList<>();
    if (coord == null) {
      this.shapeType = ShapeType.NullShape;
      this.envelope = new DefaultEnvelope();
      return;
    } else {
      this.envelope = new DefaultEnvelope(coord.hasM(), coord.hasZ());
      if (coord.hasZ()) {
        this.shapeType = ShapeType.PointZ;
      } else if (coord.hasM()) {
        this.shapeType = ShapeType.PointM;
      } else {
        this.shapeType = ShapeType.Point;
      }
    }
    Part p = new Part(coord);
    this.parts.add(p);
    this.envelope.copyProperties(new DefaultEnvelope(coord, coord));
  }

  /**
   * Creates a new, empty instance of a shape of the specified type.
   *
   * @param type The type of the shape to create.
   */
  public Shape(ShapeType type) {
    this.parts = new ArrayList<>();

    this.envelope = new DefaultEnvelope(type.hasM(), type.hasZ());
    this.shapeType = type;
  }

  /**
   * Creates a new, empty instance of a shape of the specified type and
   * envelope.
   *
   * @param type The shape type, which can be null. If it is null, it will be
   * replaced by ShapeType.NullShape.
   * @param bounds The extents. This can be null, in which case the envelope
   * will become an empty envelope, but will not be null.
   */
  public Shape(@Nullable ShapeType type, @Nullable Envelope bounds) {
    if (type != null) {
      this.shapeType = type;
    } else {
      this.shapeType = ShapeType.NullShape;
    }
    if (bounds != null) {
      this.envelope = bounds;
    } else {
      this.envelope = new DefaultEnvelope(this.shapeType.hasM(),
          this.shapeType.hasZ());
    }
    this.parts = new ArrayList<>();
  }

  /**
   * Creates a new shape, using the specified part list, and the specified shape
   * type.
   *
   * @param type The type that the shape should become. If this is null, then
   * the shapetype will be set to NullShape.
   * @param parts The list of parts to add. This will not re-use the passed in
   * list, but rather will add the parts to this shape. If this is null, no
   * parts get added. The envelope will be calculated from these parts.
   */
  public Shape(@Nullable ShapeType type, @Nullable List<Part> parts) {
    if (type != null) {
      this.shapeType = type;
    } else {
      this.shapeType = ShapeType.NullShape;
    }
    this.parts = new ArrayList<>();
    if (parts != null) {
      this.parts.addAll(parts);
    }
    this.envelope = new DefaultEnvelope(this.shapeType.hasM(), this.shapeType.hasZ());
    calculateBounds();
  }

  //</editor-fold>
  /**
   * Creates a new feature where the shape is the same shape but with a buffer.
   *
   * @param distance The distance measure of the buffer in the units of the
   * coordinate system of the shape.
   * @return The Feature that is identical to this feature but has a buffered
   * shape. In some cases where the shape is smaller than the buffer, this
   * feature will have a Shape of ShapeType.NullShape.
   *
   */
  public final Shape buffer(double distance) {
    Shape result = this.copy();
    if (distance == 0) {
      return result;
    }
    Optional<Geometry> maybeGeom = Adapter.getGeometry(this);
    if (maybeGeom.isPresent()) {
      Geometry geom = maybeGeom.get();
      Geometry buffer = geom.buffer(distance);
      Shape resultShape = Adapter.getShape(buffer, hasM(), hasZ());
      return resultShape;
    }
    return result;
  }

  /**
   * This uses the basic JTS Adapter strategy to use JTS to test if this shape
   * completely contains the other shape. This will be false if the borders
   * touch.
   *
   * @param other The other Shape to test.
   * @return Boolean, true if the specified other shape is within this shape.
   */
  public final boolean contains(Shape other) {
    if (this.getEnvelope() == null || other.getEnvelope() == null) {
      return false;
    }
    if (!this.getEnvelope().intersects(other.getEnvelope())) {
      return false;
    }
    Optional<Geometry> maybeThis = Adapter.getGeometry(this);
    Optional<Geometry> maybeOther = Adapter.getGeometry(other);
    if (maybeThis.isPresent() && maybeOther.isPresent()) {
      return maybeThis.get().contains(maybeOther.get());
    }
    return false;
  }

  /**
   * Creates a deep copy of this shape. None of the actual objects are used, but
   * rather a duplicate of this shape will be created.
   *
   * @return A deep copy of this shape.
   */
  public Shape copy() {
    Shape copy = new Shape(this.hasM(), this.hasZ());
    copy.copyProperties(this);
    return copy;
  }

  /**
   * Copies the shape but changes the coordinate type.
   *
   * @param coordType The coordinate type to change to.
   * @return The new Shape.
   */
  public final Shape copy(CoordType coordType) {
    return ShapeUtils.convertCoordinates(this, coordType);
  }

  /**
   * Copies all the aspects of the specified shape to this one, but does not use
   * any of the same memory locations.
   *
   * @param other A Shape source object to copy from.
   */
  public final void copyProperties(Shape other) {
    envelope.copyProperties(other.envelope);
    contentLength = other.contentLength;
    fid = other.fid;
    parts.clear();
    for (Part part : other.parts) {
      parts.add(part.copy());
    }
    shapeType = other.shapeType;
  }

  /**
   * Creates a new null shape.
   *
   * @return A Null shape, with an empty envelope and no coordinates.
   */
  public static final Shape getNullShape() {
    return new Shape();
  }

  /**
   * Gets the double distance to the specified coordinate.
   *
   * @param point The point to calculate the distance to.
   * @return The double distance.
   * @throws IllegalArgumentException if point is null.
   */
  public final Optional<Double> distance(@NonNull Coord point) {
    if (point == null) {
      throw new IllegalArgumentException("Coord point cannot be null.");
    }
    double minDist = Double.MAX_VALUE;
    Double result = null;
    for (Part part : this.parts) {
      Optional<Double> distance = part.distance(point);
      if (distance.isPresent() && distance.get() < minDist) {
        minDist = distance.get();
        result = minDist;
      }
    }
    return Optional.of(result);
  }

  /**
   * Gets the double distance to the specified coordinate.
   *
   * @param point The point to calculate the distance to. This cannot be null.
   * @return The double distance.
   * @throws IllegalArgumentException if point is null.
   */
  public final Optional<Double> distance2D(@NonNull Coord point) {
    if (point == null) {
      throw new IllegalArgumentException("Coord point cannot be null.");
    }
    double minDist = Double.MAX_VALUE;
    Double result = null;
    for (Part part : this.parts) {
      Optional<Double> dist = part.distance2D(point);
      if (dist.isPresent() && dist.get() < minDist) {
        minDist = dist.get();
        result = minDist;
      }
    }
    return Optional.ofNullable(result);
  }

  /**
   * This guarantees the existence of at least a single coordinate and returns
   * that coordinate.
   *
   * @return The first existing or newly created coordinate. This will never be
   * null.
   */
  public final Coord first() {
    if (this.parts.isEmpty()) {
      this.parts.add(new Part());
    }
    Part p = this.parts.get(0);
    if (p.getCoordinates().isEmpty()) {
      if (this.getShapeType().hasZ()) {
        p.getCoordinates().add(new CoordZ());
      } else if (this.getShapeType().hasM()) {
        p.getCoordinates().add(new CoordM());
      } else {
        p.getCoordinates().add(new CoordXY());
      }
    }
    if (p.start().isPresent()) {
      return p.start().get();
    }
    throw new IllegalStateException("First attempted to create a point, but"
        + "it was still incorrectly absent.");
  }

  /**
   * Gets the array of M values for the shape. Each value represents one
   * coordinate.
   *
   * @return the array of M values.
   */
  public final double[] getArrayM() {
    List<Coord> cds = getCoordinates();
    double[] result = new double[cds.size()];
    for (int i = 0; i < cds.size(); i++) {
      result[i] = cds.get(i).get(Coord.Index.M);
    }
    return result;
  }

  /**
   * Gets the array of interleaved x y coordinates for the array.
   *
   * @return the array of interleaved x y coordinates for the array.
   */
  public final double[] getArrayXY() {
    List<Coord> cds = this.getCoordinates();
    double[] result = new double[cds.size() * 2];
    for (int i = 0; i < cds.size(); i++) {
      result[i * 2] = cds.get(i).getX();
      result[i * 2 + 1] = cds.get(i).getY();
    }
    return result;
  }

  /**
   * Gets an array of doubles storing the Z values from the coordinates.
   *
   * @return An array of doubles. This will never be null, but might have 0
   * members.
   */
  public final double[] getArrayZ() {
    List<Coord> cds = getCoordinates();
    double[] result = new double[cds.size()];
    for (int i = 0; i < cds.size(); i++) {
      result[i] = cds.get(i).get(Coord.Index.Z);
    }
    return result;
  }

  /**
   * This method forces the envelope to be re-calculated based on the current
   * coordinates in the X, Y, and if applicable M and Z extents. This should be
   * called after the shape is edited.
   */
  public final void calculateBounds() {
    Envelope updatedBounds = new DefaultEnvelope(this.hasM(), this.hasZ());
    List<Coord> coordList = this.getCoordinates();
    if ((coordList != null) && (!coordList.isEmpty())) {
      for (Coord c : coordList) {
        updatedBounds.expandToInclude(c, this.hasM(), this.hasZ());
      }
    }
    this.envelope.copyProperties(updatedBounds);
  }

  /**
   * This cycles through the parts on this shape, finds the closestPart part,
   * and then gets the distance of the closestPart point on that part to the
   * specified point.
   *
   * @param point The point to test.
   * @return the Coordinate that is closestPart to the point while still being
   * on the shape.
   * @throws IllegalArgumentException if point is null.
   */
  public final Optional<Coord> closestPointTo(@NonNull Coord point) {
    if (point == null) {
      throw new IllegalArgumentException("Coord point cannot be null.");
    }
    double minDist = Double.MAX_VALUE;
    Part closestPart = null;
    for (Part p : this.parts) {
      Optional<Double> itemDist = p.distance(point);
      if (itemDist.isPresent()) {
        double dist = itemDist.get();
        if (dist < minDist) {
          closestPart = p;
          minDist = dist;
        }
      }
    }
    if (closestPart != null) {
      return closestPart.closestPointTo(point);
    }
    return Optional.empty();
  }

  /**
   * Gets a list of coordinates.
   *
   * @return The List of coordinates to get. This will never be null.
   */
  public final List<Coord> getCoordinates() {
    List<Coord> result = new ArrayList<>();
    for (Part part : this.parts) {
      result.addAll(part.getCoordinates());
    }
    return result;
  }

  /**
   * Gets a list of coordinates as CoordZ. If Z or M values to not exist, these
   * will have the default value of 0.
   *
   * @return The List of coordinates to get. This will never be null.
   */
  public final List<CoordZ> getCoordinatesZ() {
    List<CoordZ> result = new ArrayList<>();
    for (Part part : this.parts) {
      result.addAll(part.getCoordinatesZ());
    }
    return result;
  }

  /**
   * Gets the list of segments.
   *
   * @return Segment list. This will never be null, but may be empty.
   */
  public final List<Segment> getSegments() {
    List<Segment> result = new ArrayList<>();
    for (Part part : this.parts) {
      result.addAll(part.getSegments());
    }
    return result;
  }

  /**
   * Gets a boolean that is true if the shape type has an M value.
   *
   * @return a boolean that is true if the shape type has an M value.
   */
  public final boolean hasM() {
    return (this.shapeType.getValue() > SHAPE_TYPE_SPLIT);
  }

  /**
   * Gets a boolean that is true if the shape type has a Z value.
   *
   * @return a boolean that is true if the shape type has a Z value.
   */
  public final boolean hasZ() {
    int t = this.shapeType.getValue() / SHAPE_TYPE_SPLIT;
    return t == 1;
  }

  /**
   * Instead of checking the shape type, like hasZ, this actually checks the
   * first coordinate of the first part.
   *
   * @return Boolean, true if the first coordinate of the first part exists and
   * has Z.
   */
  public final boolean firstHasZ() {
    if (this.getParts().isEmpty()) {
      return false;
    }
    Optional<Coord> coord = this.getParts().get(0).getFirst();
    if (coord.isPresent()) {
      return coord.get().hasZ();
    }
    return false;
  }

  /**
   * This guarantees the existence of at least a single coordinate and returns
   * the last coordinate of the last part.
   *
   * @return The last existing coordinate or a newly created coordinate. It will
   * never be null.
   */
  public final Coord last() {
    if (this.parts.isEmpty()) {
      this.parts.add(new Part());
    }
    Part p = this.parts.get(this.parts.size() - 1);
    if (p.getCoordinates().isEmpty()) {
      if (this.getShapeType().hasZ()) {
        p.getCoordinates().add(new CoordZ());
      } else if (this.getShapeType().hasM()) {
        p.getCoordinates().add(new CoordM());
      } else {
        p.getCoordinates().add(new CoordXY());
      }
    }
    if (p.end().isPresent()) {
      return p.end().get();
    }
    throw new IllegalStateException("End point was incorrectly absent, even"
        + "when we tried to add a new one to create it.");
  }

  /**
   * Removes any coordinates that are identical to the previous coordinate.
   */
  public final void removeDuplicates() {
    for (Part p : this.parts) {
      p.removeDuplicates();
    }
  }

  /**
   * Removes any coordinates that are identical to the previous coordinate.
   *
   * @param epsilon The double tolerance for determining equality.
   */
  public final void removeDuplicates(double epsilon) {
    for (Part p : this.parts) {
      p.removeDuplicates(epsilon);
    }
  }

  /**
   * Assigns the array of M values to the coordinate. If this array of values is
   * longer than the number of coordinates, extra values are ignored.
   *
   * @param values The array of M values to assign to the coordinates.
   * @throw IllegalArgumentException if the coordinate type does not support M.
   */
  public final void setArrayM(double[] values) {
    List<Coord> cds = getCoordinates();
    for (int i = 0; i < cds.size(); i++) {
      if (i < values.length) {
        Coord c = cds.get(i);
        if (c.hasM()) {
          cds.get(i).set(Coord.Index.M, values[i]);
        } else {
          throw new IllegalArgumentException("The Coordinate type for this shape"
              + " does not support M values.");
        }

      }
    }
  }

  /**
   * Assigns the array of X Y values to the coordinate. If this array of values
   * is longer than the number of coordinates, extra values are ignored.
   *
   * @param values The array of interleaved x, y values to assign to the
   * coordinates.
   */
  public final void setArrayXY(double[] values) {
    List<Coord> cds = this.getCoordinates();
    for (int i = 0; i < cds.size(); i++) {
      if (i * 2 + 1 < values.length) {
        cds.get(i).setX(values[i * 2]);
        cds.get(i).setY(values[i * 2 + 1]);
      }
    }
  }

  /**
   * Assigns the array of Z values to the coordinate. If this array of values is
   * longer than the number of coordinates, extra values are ignored.
   *
   * @param values The array of interleaved z values to assign to the
   * coordinates.
   */
  public final void setArrayZ(double[] values) {
    List<Coord> cds = getCoordinates();
    for (int i = 0; i < cds.size(); i++) {
      if (i < values.length) {
        Coord c = cds.get(i);
        if (!c.hasZ()) {
          throw new IllegalArgumentException("The coordinate type for this "
              + "shape does not support Z values.");
        } else {
          c.set(Coord.Index.Z, values[i]);
        }
      }
    }
  }

  /**
   * A geographic coordinate to test for intersection, assuming a zero
   * tolerance. This is useful for polygon testing where tolerance is not
   * desired.
   *
   * @param coord The coordinate to test. intersection testing that might work
   * well using an exact test.
   * @return Boolean, true if the shape intersects within the tolerance.
   */
  public final boolean intersects(Coord coord) {
    if (this.getEnvelope().intersects(coord)) {
      for (Part part : this.getParts()) {
        if (part.contains(coord)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * A geographic coordinate to test for intersection.
   *
   * @param coord The coordinate to test.
   * @param tolerance The tolerance to use. This is useful for point or line
   * intersection testing that might work well using an exact test.
   * @return Boolean, true if the shape intersects within the tolerance.
   */
  public final boolean intersects(Coord coord, double tolerance) {
    Envelope env = new DefaultEnvelope(coord.getX() - tolerance, coord.getY() - tolerance,
        coord.getX() + tolerance, coord.getY() + tolerance);
    return intersects(env);
  }

  /**
   * Test for an intersection with the specified envelope and returns true if
   * this shape intersects with it, false otherwise.
   *
   * @param envelope The envelope to test.
   * @return Boolean, true if the envelope intersects this shape.
   */
  public final boolean intersects(Envelope envelope) {
    Optional<Shape> intersection = intersection(envelope);
    return intersection.isPresent();
  }

  /**
   * This uses the basic JTS Adapter strategy to use JTS to test for
   * intersection.
   *
   * @param other The other Shape to test.
   * @return Boolean, true if the specified other shape intersects this shape.
   */
  public final boolean intersects(Shape other) {
    if (this.getEnvelope() == null || other.getEnvelope() == null) {
      return false;
    }
    if (!this.getEnvelope().intersects(other.getEnvelope())) {
      return false;
    }
    if (this.getShapeType() == ShapeType.NullShape
        || other.getShapeType() == ShapeType.NullShape) {
      return false;
    }
    Optional<Geometry> maybeThis = Adapter.getGeometry(this);
    Optional<Geometry> maybeOther = Adapter.getGeometry(other);
    Geometry thisGeom = null;
    Geometry otherGeom = null;
    if (maybeThis.isPresent() && maybeOther.isPresent()) {
      if (!(thisGeom = maybeThis.get()).isValid()) {
        thisGeom = JTSUtils.fix(thisGeom);
      }
      
      if (!(otherGeom = maybeOther.get()).isValid()) {
        otherGeom = JTSUtils.fix(otherGeom);
      }
      Geometry thisUnion = thisGeom;//.union();
      Geometry otherUnion = otherGeom;//.union();
      return thisUnion.intersects(otherUnion);
    }
    return false;
  }

  /**
   * Gets the intersection using the java topology suite.
   *
   * @param other The Other shape to intersect with.
   * @return The optional shape.
   */
  public final Optional<Shape> intersection(Shape other) {
    if (this.getEnvelope() == null || other.getEnvelope() == null) {
      return Optional.empty();
    }
    if (!this.getEnvelope().intersects(other.getEnvelope())) {
      return Optional.empty();
    }
    Optional<Geometry> maybeThis = Adapter.getGeometry(this);
    Optional<Geometry> maybeOther = Adapter.getGeometry(other);
    Geometry thisGeom = null;
    Geometry otherGeom = null;
    if (maybeThis.isPresent() && maybeOther.isPresent()) {
      if (!(thisGeom = maybeThis.get()).isValid()) {
        thisGeom = JTSUtils.fix(thisGeom);
      }
      if (!(otherGeom = maybeOther.get()).isValid()) {
        otherGeom = JTSUtils.fix(otherGeom);
      }
      Geometry union = thisGeom;//.union();
      Geometry otherUnion = otherGeom;//.union();
      Geometry geom = union.intersection(otherUnion);
      Shape shape = null;
      if (!geom.isEmpty()){
        shape = Adapter.getShape(geom, this.hasM(), this.hasZ());
      }
      if (shape != null && shape.getShapeType() != ShapeType.NullShape) {
        return Optional.of(shape);
      }
    }
    return Optional.empty();
  }

  /**
   * Given a shape, this will use the JTS library to find the differences that
   * are not contained by the other shape. This may result in a multi-part shape
   * or a shape with ShapeType.NullShape, but won't return a null value.
   *
   * @param other The other shape to compare against.
   * @return A Shape that is only the shape outside of the specified shape.
   */
  public final Shape difference(Shape other) {
    Optional<Geometry> maybeThis = Adapter.getGeometry(this);
    Optional<Geometry> maybeOther = Adapter.getGeometry(other);
    if (maybeThis.isPresent() && maybeOther.isPresent()) {
      Geometry geom = maybeThis.get().difference(maybeOther.get());
      Shape shape = Adapter.getShape(geom, this.hasM(), this.hasZ());
      return shape;
    }
    return Shape.getNullShape();
  }

  /**
   * This will crop the coordinates. If both the shape and envelope have Z
   * values then the Z envelope values will be considered for cropping. If the
   * envelope does not have z, but this shape does have z, then the z values
   * will be preserved, but only x and y are considered for cropping.
   *
   * @param envelope The envelope to use for the intersection calculation.
   * @return The Shape intersection.
   */
  public final Optional<Shape> intersection(Envelope envelope) {
    Shape result = new Shape(getShapeType());
    PartIntersector intersector = null;
    if (shapeType.isPolyLine()) {
      intersector = new PartIntersectorLine();
    }
    if (shapeType.isPoint() || shapeType.isMultiPoint()) {
      intersector = new PartIntersectorPoint();
    }
    if (shapeType.isPolygon()) {
      intersector = new PartIntersectorPolygon();
    }
    if (intersector != null) {
      for (Part part : parts) {
        List<Part> items = intersector.intersection(part, envelope);
        for (Part item : items) {
          result.getParts().add(item);
        }
      }
      if (result.parts.isEmpty()) {
        return Optional.empty();
      }
      return Optional.of(result);
    } else {
      throw new UnsupportedOperationException("Intersection for " + shapeType
          + " is not yet supported.");
    }

  }

  /**
   * The number of digits to preserve after the decimal.
   *
   * @param scale scale - amount by which to multiply a coordinate after
   * subtracting the offset, to obtain a precise coordinate
   * @return This Shape modified to fit the defined precision.
   */
  public final Shape round(double multiplier) {
//    Optional<Geometry> maybeGeom = Adapter.getGeometry(this);
//    Shape result = new Shape(this.hasM(), this.hasZ());
//    if (maybeGeom.isPresent()) {
//      Geometry g = JTSUtils.setPrecision(maybeGeom.get(), scale);
//      return Adapter.getShape(g, this.hasM(), this.hasZ());
//    }
//    return result;
    Optional<Geometry> maybeGeom = Adapter.getGeometry(this);
    Shape result = this.copy();
    if (maybeGeom.isPresent()) {
      Geometry g = JTSUtils.setPrecision(maybeGeom.get(), multiplier);
      result = Adapter.getShape(g, this.hasM(), this.hasZ());
      if (this.hasM()) {
        int partSize = Math.min(this.getParts().size(), result.getParts().size());
        for (int iPart = 0; iPart < partSize; iPart++) {
          Part part = this.getParts().get(iPart);
          Part resultPart = result.getParts().get(iPart);
          int size = Math.min(part.getCoordinates().size(), resultPart.getCoordinates().size());
          for (int i = 0; i < size; i++) {
            double m = part.getCoordinates().get(i).getM();
            m = m * multiplier;
            m = Math.round(m);
            m = m / multiplier;
            resultPart.getCoordinates().get(i).setM(m);
          }
        }
        result.calculateBounds();
      }
      result.setShapeType(this.getShapeType());
    }
    return result;
  }

  /**
   * The shapes to attempt to union.
   *
   * @param otherShape The other shape to union with this shape.
   * @return A single shape, potentially multi-part, which contains the unioned
   * areas.
   */
  public final Shape union(Shape otherShape) {
    if (otherShape == null || otherShape.getShapeType() == ShapeType.NullShape
        || otherShape.isEmpty()) {
      this.copy();
    }
    ShapeCategory thisCat = this.getShapeType().getCategory();
    ShapeCategory otherCat = otherShape.getShapeType().getCategory();
    if (thisCat != otherCat) {
      throw new IllegalArgumentException("A shape of type " + otherCat
          + " cannot be unioned with a shape of type " + thisCat);

    }
    Optional<Geometry> maybeGeom = Adapter.getGeometry(this);
    Optional<Geometry> maybeOther = Adapter.getGeometry(otherShape);
    if (maybeGeom.isPresent() && maybeOther.isPresent()) {
      Geometry geom = maybeGeom.get();
      Geometry other = maybeOther.get();
      Geometry result = geom.union(other);
      return Adapter.getShape(result, hasM(), hasZ());
    }
    return this.copy();
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the cached geographic extents of this shape.
   *
   * @return the envelope
   */
  public final Envelope getEnvelope() {
    if (((this.envelope.isEmpty())
        && (!this.isEmpty()))) {
      this.calculateBounds();
    }
    return this.envelope;
  }

  /**
   * Gets a length in 4 byte words describing the size of this shape. This is
   * used by the index file.
   *
   * @return the contentLength
   */
  public final int getContentLength() {
    return contentLength;
  }

  /**
   * Sets a length in 4 byte words describing the size of this shape. This is
   * used by the index file.
   *
   * @param contentLength the contentLength to set
   */
  public final void setContentLength(int contentLength) {
    this.contentLength = contentLength;
  }

  /**
   * Gets an integer feature index representing the sequence of this shape in
   * the shapefile.
   *
   * @return the fid
   */
  public final int getFid() {
    return fid;
  }

  /**
   * Sets an integer feature index representing the sequence of this shape in
   * the shapefile.
   *
   * @param fid the fid to set
   */
  public final void setFid(int fid) {
    this.fid = fid;
  }

  /**
   * Gets the M value for the first coordinate of this shape. This will return 0
   * if M is not supported.
   *
   * @return The M value.
   */
  public final double getM() {
    Coord first = first();
    if (first.hasM()) {
      return first.get(Coord.Index.M);
    }
    return 0;
  }

  /**
   * Sets the M value for the first coordinate of this shape. Mostly useful for
   * Points. If the coordinate type does not support M, this will do nothing.
   *
   * @param value the M value to set.
   */
  public final void setM(double value) {
    Coord first = first();
    if (first.hasM()) {
      first().set(Coord.Index.M, value);
    }

  }

  /**
   * Gets the X ordinate for the first coordinate of the shape. This is mostly
   * useful for points.
   *
   * @return the double X value.
   */
  public final double getX() {
    return first().getX();
  }

  /**
   * Sets the X value for the first coordinate of the shape.
   *
   * @param value this.first().X
   */
  public final void setX(double value) {
    first().setX(value);
  }

  /**
   * Gets the Y value for the first coordinate of the shape. This is mostly
   * useful for points.
   *
   * @return the double Y value.
   */
  public final double getY() {
    return first().getY();
  }

  /**
   * Sets the Y value for the first coordinate of the shape.
   *
   * @param value this.first().Y
   */
  public final void setY(double value) {
    first().setY(value);
  }

  /**
   * Gets the Z value for the first coordinate of the shape. If z values are not
   * supported, this will return 0.
   *
   * @return this.first().Z
   */
  public final double getZ() {
    Coord first = first();
    if (first.hasZ()) {
      return first.get(Coord.Index.Z);
    }
    return 0;
  }

  /**
   * Sets the Z value for the first coordinate of the shape. If the shape does
   * not support Z, this will do nothing.
   *
   * @param value this.first().Z
   */
  public final void setZ(double value) {
    Coord first = first();
    if (first.hasZ()) {
      first.set(Coord.Index.Z, value);
    }
  }

  /**
   * Check if the shape is empty - has no coordinates. this will also be true if
   * the shapetype is a nullshape.
   *
   * @return true of this.coordinates.isEmpty.
   */
  public final boolean isEmpty() {
    if (this.shapeType == ShapeType.NullShape) {
      return true;
    }
    List<Coord> coordList = this.getCoordinates();
    return ((coordList == null) || (coordList.isEmpty()));
  }

  /**
   * Gets the list of parts that make up the shape. Parts are usually
   * disconnected, but can touch. For polygons, parts can also represent holes.
   *
   * @return the parts.
   */
  public final List<Part> getParts() {
    return this.parts;
  }

  /**
   * Gets the ShapeType for this shape.
   *
   * @return the shapeType
   */
  public final ShapeType getShapeType() {
    return this.shapeType;
  }

  /**
   * Sets the ShaepType for this shape, clarifying whether the shape has z or m,
   * and clarifying whether it is a point, line or polygon.
   *
   * @param shapeType the shapeType to set. This can be null, in which case the
   * shapeType will be assigned to NullShape.
   */
  public final void setShapeType(@Nullable ShapeType shapeType) {
    if (shapeType == null) {
      this.shapeType = ShapeType.NullShape;
    } else {
      this.shapeType = shapeType;
    }

  }

  //</editor-fold>
}
