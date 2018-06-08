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

import gov.ca.water.shapelite.coordinate.ClosestCoord;
import gov.ca.water.shapelite.coordinate.HasXY;
import gov.ca.water.shapelite.segment.SegmentValues;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface Segment extends Serializable, SegmentValues {

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Creates a deep copy of this object, where the coordinate objects are
   * different objects.
   *
   * @return A Copy of this object.
   */
  Segment copy();

  /**
   * Tests against another segment to measure the number of endpoint
   * intersections.
   *
   * @param other The other segment to consider.
   * @return 0 if no endpoints intersect, 1 if one endpoint intersects, or 2 if
   * 2 endpoints intersect etc. The maximum, in cases of two degenerate segments
   * is 4. If other is null, this will be 0.
   */
   int getJointCount(@Nullable SegmentValues other);

  /**
   * Tests for equality with another segment, but reversed.
   *
   * @param obj The other object to test. If the object is not a Segment, this
   * will return false.
   * @return True if the other segment has P1 and P2 coordinates with the same
   * values as this segment.
   */
  boolean equalsReversed(Object obj);

  /**
   * Returns a segment that has been reversed.
   *
   * @return A Segment that has the same endpoints as this segment, but in the
   * reverse order. Segment will never be null.
   */
  Segment reversed();

  /**
   * Gets the Coordinate at the specified distance along the segment.
   *
   * @param distance the distance from the start point along the segment.
   * @return The Coordinate location of the point at the specified distance.
   * @throws IllegalArgumentException if double distance is NaN.
   */
  Coord coordAt(@NonNan double distance);

  /**
   * Gets the closest point that is on the segment to the specified point, which
   * can be anywhere.
   *
   * @param point The point to get the closest point to. This cannot be null.
   * @return The ClosestCoord with an optional closest coord and an endpoint
   * interaction.
   * @throws IllegalArgumentException if point is null.
   */
  ClosestCoord closestPointTo(@NonNull Coord point);

  /**
   * Gets the closest point the the specified point, given information about
   * whether the line is allowed to be infinite or not.
   *
   * @param point The point to find the closest point to. This cannot be null.
   * @param isInfiniteLine boolean. If this is true, the segment is treated as
   * if it defines an infinite line.
   * @return A ClosestCoord object with an optional coordinate that has x and y
   * values that represent the closest point on the line to the specified point,
   * but will not be the same coordinate instance as a coordinate on the point.
   * @throws IllegalArgumentException if point is null.
   */
  ClosestCoord closestPointTo(@NonNull Coord point, boolean isInfiniteLine);

  /**
   * Gets the minimum distance to the specified coordinate.
   *
   * @param point The point to get the distance from this segment to.
   * @return The double distance.
   * @throws IllegalArgumentException if point is null.
   */
  double distanceTo(@NonNull HasXY point);


  /**
   * Gets the minimum distance to the specified envelope. This is 0 if the
   * envelope intersects this segment.
   *
   * @param env The envelope to get the minimum distance to.
   * @return The double distance.
   * @throws IllegalArgumentException if point is null.
   */
  double distance2D(@NonNull Envelope env);

  /**
   * If one and only one set of endpoints intersect, this returns the
   * intersecting endpoints. Otherwise this returns empty.
   *
   * @param other The other segment values.
   * @return An optional coordinate if the endpoints don't touch or both
   * endpoints intersect.
   */
  Optional<Coord> getJoint(SegmentValues other);

  /**
   * Regardless of whether this segment has 3D or 2D content, this will
   * calculate the distance to the point as if everything were projected onto a
   * 2D plane.
   *
   * @param point the point.
   * @return The distance from this segment to the specified point.
   * @throws IllegalArgumentException if point is null.
   */
  double distanceTo2D(@NonNull Coord point);

  /**
   * Gets the minimum distance between this segment and the other segment.
   *
   * @param lineSegment The line segment. This cannot be null.
   * @return The double minimum distance between the two segments.
   * @throws IllegalArgumentException if lineSegment is null.
   */
  double distanceTo(@NonNull Segment lineSegment);

  /**
   * This determines the number of times the other segment intersects this
   * segment.
   *
   * @param other The other Segment to consider intersections with. This can be
   * null, in which case the intersectionCount is 0.
   * @return 0 if they do not intersect, 1 if they intersect, and 2 if they are
   * coincident.
   */
  int intersectionCount(@Nullable SegmentValues other);

  /**
   * Determines if this segment intersects with the other segment.
   *
   * @param other The other segment.
   * @return boolean, true if the two segments intersect. Other can be null, in
   * which case this returns false.
   */
  boolean intersects(@Nullable Segment other);

  /**
   * Determines if this segment intersects with the specified envelope. This is
   * determined as true if either endpoint is contained, or if the segment
   * intersects with any of the edges.  Only X and Y are considered.
   *
   * @param env The envelope to intersect with.
   * @return boolean, true if the segment intersects the envelope. Other can be
   * null, in which case this returns false.
   */
  boolean intersects2D(@Nullable Envelope env);


  /**
   * This will return an optional segment intersection with the specified
   * bounds.
   *
   * @param bounds The bounds to intersect with. A null value would be
   *
   * @return An Optional segment. If there is no intersection with the envelope,
   * this will be empty.
   */
  Optional<Segment> intersection(@NonNull Envelope bounds);

  /**
   * Gets the intersection between this segment and the other segment. I think
   * this is always a 2D intersection, even if there are z values.
   *
   * @param other The other segment.
   * @return An optional shape that is empty if there is no intersection, or a
   * segment if they overlap and are colinear, and a point shape if only one
   * point touches.
   */
  Optional<Shape> intersection(SegmentValues other);

  /**
   * Gets a boolean that is true if both coordinates have a z value.
   *
   * @return The boolean that is true if both coordinate have a z.
   */
  boolean hasZ();

  /**
   * This determines if the point is within EPSILON distance of the line.
   *
   * @param point The coordinate to intersect with. This can be null, in which
   * case intersects returns false.
   * @return The boolean that is true if the point intersects with the output.
   */
  boolean intersects(@Nullable Coord point);

  /**
   * This tests to determine if the specified point is anywhere on this segment.
   *
   * @param point The point to test. This can be null, in which case, this
   * returns false.
   * @return Boolean, true if the point is within Coordinate.EPSILION of this
   * segment.
   */
  boolean intersectsEndpoint(@Nullable Coord point);

  /**
   * The length of this segment.
   *
   * @return the double length.
   */
  double length();

  /**
   * Returns the shortest segment between the points. If the lines are parallel
   * so that all the distances are precisely equal, then this will return a
   * segment from P1 of this segment to the closest position on the other
   * segment.
   *
   * @param other The other segment to test.
   * @return A Segment fully describing the shortest connection between these
   * two segments. This may be degenerate, so that both points on the segment
   * are the same and the length is 0.
   * @throws IllegalArgumentException if Segment other is null.
   */
  Segment shortestLink(@NonNull Segment other);

  /**
   * Returns a segment directed from the closest point on this segment to the
   * specified point.
   *
   * @param point the point to calculate the link to this segment.
   * @return A Segment directed from the specified point to the position on this
   * segment that is closest to that point.
   * @throws IllegalArgumentException if point is null.
   */
  Segment shortestLink(@NonNull Coord point);


  /**
   * Finds the nearest point on this segment to the specified vertex. If the
   * vertex is not an endpoint, this will split this segment. Otherwise it will
   * return this segment.
   *
   * @param vertex The Coordinate vertex location to split this segment. This
   * can be null, in which case, split will return a copy of this segment.
   * @return The two segments created by splitting the current segment at the
   * specified location.
   */
  List<Segment> split(@Nullable Coord vertex);

  /**
   * Tests the other segment to determine if the end points touch. This is not
   * true unless it is only the actual end vertices that touch. If the interior
   * touches (as is the case with overlapping segments) this is false.
   *
   * @param other The other SegmentValues to test.
   * @return Boolean, true if the endpoints touch, but the interior parts do not
   * overlap.
   */
  boolean touches(SegmentValues other);


  /**
   * Converts this segment to a vector, starting at P1 and ending at P2.
   *
   * @return A vector defined by placing the tail at the start (P1) and the tip
   * at the end point (P2).
   */
  Vector toVector();

  /**
   * Get angle (slope) of the segment along the 2D axis. The angle will be range
   * from -pi/2 and pi/2.
   *
   * @return the calculated angle or 0.0 if the segment start or end point is
   * undefined.
   */
  double getAngle2D();

  /**
   * @return the p1
   */
  @Override
  Coord getP1();

  /**
   * @return the p2
   */
  @Override
  Coord getP2();

  /**
   * Gets the bounding box for this segment.
   * @return This segment's envelope.
   */
  Envelope getEnvelope();

    //</editor-fold>
}
