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
package gov.ca.water.shapelite.segment;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.EndPointInteraction;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.NonNan;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Nullable;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Segment;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.Vector;
import gov.ca.water.shapelite.coordinate.ClosestCoord;
import gov.ca.water.shapelite.coordinate.CoordFactory;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.coordinate.HasXY;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public abstract class SegmentBase implements Segment {

  /**
   * A small number constant to use for "effectively equal" tolerance.
   */
  private static final double SMALL_NUM = 0.00000001;

  /**
   * A small value to use for double equality testing.
   */
  private static final double EPSILON = Double.MIN_VALUE;

  /**
   * The integer size if z is present.
   */
  private static final int SIZE_3D = 3;

  /**
   * The integer index to use for X values.
   */
  private static final int X = Coord.Index.X;

  /**
   * The integer index to use for Z values.
   */
  private static final int Y = Coord.Index.Y;

  /**
   * The integer index to use for Z values.
   */
  private static final int Z = Coord.Index.Z;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The start point of the segment. Proper function of this object will assume
   * that this coord can never be null.
   */
  @NonNull
  private final Coord p1;

  /**
   * The end point of the segment. Proper function of this object will assume
   * that this coord can never be null.
   */
  @NonNull
  private final Coord p2;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Initializes a new instance of the Segment class.
   *
   * @param x1 The double x of the start of the segment.
   * @param y1 The double y of the start of the segment.
   * @param x2 The double x of the end of the segment.
   * @param y2 The double y of the end of the segment.
   */
  protected SegmentBase(double x1, double y1, double x2, double y2) {
    p1 = new CoordXY(x1, y1);
    p2 = new CoordXY(x2, y2);
  }

  /**
   * Initializes a new instance of the Segment class.
   *
   * @param p1 The start coordinate for the segment.
   * @param p2 The end coordinate for the segment.
   * @throws IllegalArgumentException if a or b are null.
   */
  protected SegmentBase(@NonNull HasXY p1, @NonNull HasXY p2) {
    if (p1 == null || p2 == null) {
      throw new IllegalArgumentException("A Coordinate cannot be set to null"
          + " in order for the segment to be valueable.");
    }
    this.p1 = new CoordXY(p1);
    this.p2 = new CoordXY(p2);
  }

  /**
   * Initializes a new instance of the Segment class.
   *
   * @param p1 The start coordinate for the segment.
   * @param p2 The end coordinate for the segment.
   * @throws IllegalArgumentException if a or b are null.
   */
  protected SegmentBase(@NonNull Coord p1, @NonNull Coord p2) {
    if (p1 == null || p2 == null) {
      throw new IllegalArgumentException("A Coordinate cannot be set to null"
          + " in order for the segment to be valueable.");
    }
    this.p1 = p1;
    this.p2 = p2;
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
//  /**
//   * Returns the shortest segment between the points. If the lines are parallel
//   * so that all the distances are precisely equal, then this will return a
//   * segment from P1 of this segment to the closest position on the other
//   * segment.
//   *
//   * @param other The other segment to test.
//   * @return A Segment fully describing the shortest connection between these
//   * two segments. This may be degenerate, so that both points on the segment
//   * are the same and the length is 0.
//   * @throws IllegalArgumentException if Segment other is null.
//   */
//  @Override
//  public final Segment shortestLink(@NonNull Segment other) {
//    if (other == null) {
//      throw new IllegalArgumentException("Segment other cannot be null.");
//    }
//    Segment aToCd = other.shortestLink(p1).reversed();
//    Segment bToCd = other.shortestLink(p2).reversed();
//    Segment abToC = this.shortestLink(other.getP1());
//    Segment abToD = this.shortestLink(other.getP2());
//
//    Segment result = aToCd;
//    if (bToCd.length() < result.length()) {
//      result = bToCd;
//    }
//    if (abToC.length() < result.length()) {
//      result = abToC;
//    }
//    if (abToD.length() < result.length()) {
//      result = abToD;
//    }
//    return result;
//  }
  /**
   * Gets the bounding box for this segment.
   *
   * @return This segment's envelope.
   */
  @Override
  public final Envelope getEnvelope() {
    return new DefaultEnvelope(p1, p2);
  }

  /**
   * Tests for equality with another segment, but reversed.
   *
   * @param obj The other object to test. If the object is not a Segment, this
   * will return false.
   * @return True if the other segment has P1 and P2 coordinates with the same
   * values as this segment.
   */
  @Override
  public final boolean equalsReversed(Object obj) {
    if (!(obj instanceof SegmentBase)) {
      return false;
    }
    SegmentBase other = (SegmentBase) obj;
    if (!this.p1.equals(other.p2)) {
      return false;
    }
    return this.p2.equals(other.p1);
  }

  /**
   * Returns a segment directed from the closest point on this segment to the
   * specified point.
   *
   * @param point the point to calculate the link to this segment.
   * @return A Segment directed from the specified point to the position on this
   * segment that is closest to that point.
   * @throws IllegalArgumentException if point is null.
   */
  @Override
  public final Segment shortestLink(@NonNull Coord point) {
    if (point == null) {
      throw new IllegalArgumentException("Shortest link cannot accept a null point");
    }
    Coord c = this.closestPointTo(point).getCoord();
    return new DefaultSegment(c, point);
  }

  /**
   * Returns a segment that has been reversed.
   *
   * @return A Segment that has the same endpoints as this segment, but in the
   * reverse order. Segment will never be null.
   */
  @Override
  public final Segment reversed() {
    return new DefaultSegment(p2, p1);
  }

  /**
   * Gets the Coordinate at the specified distance along the segment.
   *
   * @param distance the distance from the start point along the segment.
   * @return The Coordinate location of the point at the specified distance.
   * @throws IllegalArgumentException if double distance is NaN.
   */
  @Override
  public final Coord coordAt(@NonNan double distance) {
    if (Double.isNaN(distance)) {
      throw new IllegalArgumentException("Distance cannot be NaN.");
    }
    Vector v = toVector();
    v.setLength(distance);
    Vector start = new Vector(p1);
    Vector result = start.add(v);
    return result.toCoord();
  }

  /**
   * Gets the closest point that is on the segment to the specified point, which
   * can be anywhere.
   *
   * @param point The point to get the closest point to. This cannot be null.
   * @return The ClosestCoord with an optional closest coord and an endpoint
   * interaction.
   * @throws IllegalArgumentException if point is null.
   */
  @Override
  public final ClosestCoord closestPointTo(@NonNull Coord point) {
    if (point == null) {
      throw new IllegalArgumentException("closestPointTo cannot accept a "
          + "null point as an argument.");
    }
    return closestPointTo(point, false);
  }

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
  @Override
  public final ClosestCoord closestPointTo(@NonNull Coord point, boolean isInfiniteLine) {
    if (point == null) {
      throw new IllegalArgumentException("The point argument cannot be null.");
    }
    ClosestCoord result = new ClosestCoord();
    // If the points defining this segment are the same, we treat the
    // segment as a point special handling to avoid 0 in denominator later
    if (p2.getX() == p1.getX() && p2.getY() == p1.getY()) {
      result.setInteraction(EndPointInteraction.P1equalsP2);
      result.setCoord(p1.copy());
      return result;
    }

    //http://softsurfer.com/Archive/algorithm_0102/algorithm_0102.htm
    Vector v = toVector(); // vector from a to b in the segment
    v.setZ(0);
    Vector w = new Vector(p1, point); // vector from a to Point
    w.setZ(0);
    // the dot product represents the projection onto the line
    double c1 = w.dot(v);

    if (c1 < 0) {
      result.setInteraction(EndPointInteraction.PastP1);
      if (!isInfiniteLine) {
        // The closest point on the segment to Point is a
        result.setCoord(p1.copy());
        return result;
      }
    }

    double c2 = v.dot(v);

    if (c2 <= c1) {
      result.setInteraction(EndPointInteraction.PastP2);
      if (!isInfiniteLine) {
        // The closest point on the segment to Point is b
        result.setCoord(p2.copy());
        return result;
      }
    }

    // The closest point on the segment is perpendicular to the point,
    // but somewhere on the segment between P1 and P2
    result.setInteraction(EndPointInteraction.OnLine);
    double b = c1 / c2;
    v = v.multiply(b);
    Coord pb = new CoordXY(p1.getX() + v.getX(), p1.getY() + v.getY());
    result.setCoord(pb);
    return result;
  }

  /**
   * Gets the closest point on this segment to the other segment.
   *
   * @param other The other segment.
   * @return The Coord expected.
   */
  public final Coord closestPointTo(Segment other) {
    Segment seg = shortestLink(other);
    return seg.getP1();
  }


  /**
   * Gets the minimum distance to the specified coordinate.
   *
   * @param point The point to get the distance from this segment to.
   * @return The double distance.
   * @throws IllegalArgumentException if point is null.
   */
  @Override
  public final double distanceTo(@NonNull HasXY point) {
    if (point == null) {
      throw new IllegalArgumentException("Coord point cannot be null.");
    }
    Coord coord;
    if(point instanceof Coord){
      coord = (Coord)point;
    }else{
      coord = new CoordXY(point);
    }
    return closestPointTo(coord).getCoord().distance(coord);
  }

  /**
   * Regardless of whether this segment has 3D or 2D content, this will
   * calculate the distance to the point as if everything were projected onto a
   * 2D plane.
   *
   * @param point the point.
   * @return The distance from this segment to the specified point.
   * @throws IllegalArgumentException if point is null.
   */
  @Override
  public final double distanceTo2D(@NonNull Coord point) {
    if (point == null) {
      throw new IllegalArgumentException("Coord point cannot be null.");
    }
    Coord point2D = new CoordXY(point.getX(), point.getY());
    SegmentBase seg2D = new DefaultSegment(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    return seg2D.distanceTo(point2D);
  }

  /**
   * Gets the minimum distance between this segment and the other segment.
   *
   * @param lineSegment The line segment. This cannot be null.
   * @return The double minimum distance between the two segments.
   * @throws IllegalArgumentException if lineSegment is null.
   */
  @Override
  public final double distanceTo(@NonNull Segment lineSegment) {
    return shortestLink(lineSegment).length();
  }

  /**
   * Gets the segment that defines the minimum link between this segment and the
   * other. P1 is on this segment, P2 is on the other.
   *
   * @param lineSegment The line segment. This cannot be null.
   * @return The shortest Segment between the two segments.
   * @throws IllegalArgumentException if lineSegment is null.
   */
  @Override
  public final Segment shortestLink(@NonNull Segment lineSegment) {
    if (lineSegment == null) {
      throw new IllegalArgumentException("Segment lineSegment cannot be null.");
    }
    //http://www.geomalgorithms.com/a07-_distance.html#dist3D_Segment_to_Segment()
    double smallNum = SMALL_NUM;
    Vector u = toVector(); // Segment 1
    Vector v = lineSegment.toVector(); // Segment 2
    Vector w = new Vector(lineSegment.getP1(), this.getP1());
    double a = u.dot(u);  // length of segment 1
    double b = u.dot(v);  // length of segment 2 projected onto line 1
    double c = v.dot(v);  // length of segment 2
    double d = u.dot(w);  //
    double e = v.dot(w);
    double dist = a * c - b * b;
    double sc, sN, sD = dist;
    double tc, tN, tD = dist;
    // compute the line parameters of the two closest points
    if (dist < smallNum) {
      // the lines are almost parallel force using point P0 on segment 1
      // to prevent possible division by 0 later
      sN = 0.0;
      sD = 1.0;
      tN = e;
      tD = c;
    } else {
      // get the closest points on the infinite lines
      sN = (b * e - c * d);
      tN = (a * e - b * d);
      if (sN < 0.0) {
        // sc < 0 => the s=0 edge is visible
        sN = 0.0;
        tN = e;
        tD = c;
      } else if (sN > sD) {
        // sc > 1 => the s=1 edge is visible
        sN = sD;
        tN = e + b;
        tD = c;
      }
    }
    if (tN < 0.0) {
      // tc < 0 => the t=0 edge is visible
      tN = 0.0;
      // recompute sc for this edge
      if (-d < 0.0) {
        sN = 0.0;
      } else if (-d > a) {
        sN = sD;
      } else {
        sN = -d;
        sD = a;
      }
    } else if (tN > tD) {
      tN = tD;
      // recompute sc for this edge
      if ((-d + b) < 0.0) {
        sN = 0;
      } else if ((-d + b) > a) {
        sN = sD;
      } else {
        sN = (-d + b);
        sD = a;
      }
    }
    // finally do the division to get sc and tc
    if (Math.abs(sN) < smallNum) {
      sc = 0.0;
    } else {
      sc = sN / sD;
    }
    if (Math.abs(tN) < smallNum) {
      tc = 0.0;
    } else {
      tc = tN / tD;
    }
    // get the difference of the two closest points
    Vector dU = u.multiply(sc);
    Vector dV = v.multiply(tc);
    Coord start = new Vector(this.getP1()).add(dU);
    Coord end = new Vector(lineSegment.getP1()).add(dV);
    return new DefaultSegment(start, end);
  }

  /**
   * Gets the minimum distance to the specified envelope. This is 0 if the
   * envelope intersects this segment. Only X and Y will be considered.
   *
   * @param env The envelope to get the minimum distance to.
   * @return The double distance.
   * @throws IllegalArgumentException if point is null.
   */
  @Override
  public final double distance2D(@NonNull Envelope env) {
    if (env == null) {
      throw new IllegalArgumentException("Env cannot be null.");
    }
    if (this.intersects2D(env)) {
      return 0;
    }
    List<Segment> segments = env.getEdges2D();
    double closest = Double.MAX_VALUE;
    for (Segment seg : segments) {
      double distance = this.distanceTo(seg);
      if (distance < closest) {
        closest = distance;
      }
    }
    return closest;
  }

  /**
   * Determines if this segment intersects with the specified envelope. This is
   * determined as true if either endpoint is contained, or if the segment
   * intersects with any of the edges. Only X and Y are considered.
   *
   * @param env The envelope to intersect with.
   * @return boolean, true if the segment intersects the envelope. Other can be
   * null, in which case this returns false.
   */
  @Override
  public final boolean intersects2D(@Nullable Envelope env) {
    if (env == null) {
      return false;
    }
    if (env.intersects2D(p1)) {
      return true;
    }
    if (env.intersects2D(p2)) {
      return true;
    }
    List<Segment> segments = env.getEdges2D();
    for (Segment seg : segments) {
      if (this.intersects(seg)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Tests against another segment to measure the number of endpoint
   * intersections.
   *
   * @param other The other segment to consider.
   * @return 0 if no endpoints intersect, 1 if one endpoint intersects, or 2 if
   * 2 endpoints intersect etc. The maximum, in cases of two degenerate segments
   * is 4. If other is null, this will be 0.
   */
  @Override
  public final int getJointCount(@Nullable SegmentValues other) {
    if (other == null) {
      return 0;
    }
    Coord otherP1 = CoordFactory.get(other.getP1());
    Coord otherP2 = CoordFactory.get(other.getP2());
    int count = 0;
    if (p1.intersects(otherP1)) {
      count++;
    }
    if (p1.intersects(otherP2)) {
      count++;
    }
    if (p2.intersects(otherP1)) {
      count++;
    }
    if (p2.intersects(otherP2)) {
      count++;
    }
    return count;
  }

  /**
   * This determines the number of times the other segment intersects this
   * segment.
   *
   * @param other The other Segment to consider intersections with. This can be
   * null, in which case the intersectionCount is 0.
   * @return 0 if they do not intersect, 1 if they intersect, and 2 if they are
   * coincident.
   */
  @Override
  public final int intersectionCount(@Nullable SegmentValues other) {
    if (other == null) {
      return 0;
    }
    Coord otherP1 = CoordFactory.get(other.getP1());
    Coord otherP2 = CoordFactory.get(other.getP2());
    DefaultSegment otherSeg = new DefaultSegment(otherP1, otherP2);
    if (this.getP1().equals(other.getP1())) {
      if (this.intersects(otherP2) || otherSeg.intersects(this.p2)) {
        return 2;
      }
      return 1;
    }
    if (this.getP1().equals(other.getP2())) {
      if (this.intersects(otherP1) || otherSeg.intersects(this.p2)) {
        return 2;
      }
      return 1;
    }
    if (this.getP2().equals(other.getP1())) {
      if (this.intersects(otherP2) || otherSeg.intersects(this.p1)) {
        return 2;
      }
      return 1;
    }
    if (this.getP2().equals(other.getP2())) {
      if (this.intersects(otherP1) || otherSeg.intersects(this.p1)) {
        return 2;
      }
      return 1;
    }
    double x1 = p1.getX();
    double y1 = p1.getY();
    double x2 = p2.getX();
    double y2 = p2.getY();
    double x3 = other.getP1().get(X);
    double y3 = other.getP1().get(Y);
    double x4 = other.getP2().get(X);
    double y4 = other.getP2().get(Y);
    double denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);

    // if denom is 0, then the two lines are parallel
    double na = (x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3);
    double nb = (x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3);
    // if denom is 0 AND na and nb are 0, then the lines are coincident
    // and DO intersect
    if (Math.abs(denom) < EPSILON && Math.abs(na)
        < EPSILON && Math.abs(nb) < EPSILON) {
      return 2;
    }
    // If denom is 0, but na or nb are not 0, then the lines are parallel
    // and not coincident
    if (denom == 0) {
      return 0;
    }
    double ua = na / denom;
    double ub = nb / denom;
    if (ua < 0 || ua > 1) {
      return 0; // not intersecting with segment a
    }
    if (ub < 0 || ub > 1) {
      return 0; // not intersecting with segment b
    }    // If we get here, then one intersection exists and it is
    // found on both line segments
    return 1;
  }

  /**
   * Determines if this segment intersects with the other segment.
   *
   * @param other The other segment.
   * @return boolean, true if the two segments intersect. Other can be null, in
   * which case this returns false.
   */
  @Override
  public final boolean intersects(@Nullable Segment other) {
    if (other == null) {
      return false;
    }
    Line2D a = new Line2D.Double();
    Line2D b = new Line2D.Double();
    a.setLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    b.setLine(other.getP1().getX(), other.getP1().getY(), other.getP2().getX(),
        other.getP2().getY());
    return a.intersectsLine(b);
  }

  /**
   * This will return an optional segment intersection with the specified
   * bounds.
   *
   * @param bounds The bounds to intersect with. A null value would be
   *
   * @return An Optional segment. If there is no intersection with the envelope,
   * this will be empty.
   */
  @Override
  public final Optional<Segment> intersection(@NonNull Envelope bounds) {
    boolean hasZ = this.hasZ() && bounds.hasZ();
    Segment result = null;
    boolean flipped = false;
    if (bounds.intersects(p1) && bounds.intersects(p2)) {
      result = new DefaultSegment(p1.copy(), p2.copy());
    } else {
      Coord inside = null;
      if (bounds.intersects(p2)) {
        inside = p2.copy();
        flipped = true;
      }
      if (bounds.intersects(p1)) {
        inside = p1.copy();
      }
      if (inside != null) {
        // one point is completely inside, one point is outside.
        int dmax = 2;
        if (hasZ) {
          dmax = SIZE_3D;
        }
        for (int dim = 0; dim < dmax; dim++) {
          double min = bounds.getMin().get(dim);
          Optional<Coord> c = intersectionWithPlane(min, dim, hasZ);
          if (c.isPresent()) {
            if (flipped) {
              result = new DefaultSegment(c.get(), inside);
            } else {
              result = new DefaultSegment(inside, c.get());
            }
            break;
          }
          double max = bounds.getMax().get(dim);
          c = intersectionWithPlane(max, dim, hasZ);
          if (c.isPresent()) {
            if (flipped) {
              result = new DefaultSegment(c.get(), inside);
            } else {
              result = new DefaultSegment(inside, c.get());
            }
            break;
          }
        }
      } else {
        // both points are outside, but maybe part of the line is inside.
        // if there are no plane intersections, then the line is completely
        // outside.
        int dmax = 2;
        if (hasZ) {
          dmax = SIZE_3D;
        }
        Coord first = null;
        Coord second = null;
        for (int dim = 0; dim < dmax; dim++) {
          double min = bounds.getMin().get(dim);
          Optional<Coord> c = intersectionWithPlane(min, dim, hasZ);
          if (c.isPresent()) {
            if (first == null) {
              first = c.get();
            } else {
              second = c.get();
              break;
            }
          }

          double max = bounds.getMax().get(dim);
          c = intersectionWithPlane(max, dim, hasZ);
          if (c.isPresent()) {
            if (first == null) {
              first = c.get();
            } else {
              second = c.get();
              break;
            }
          }
        }
        if (first != null && second != null) {
          double d1 = p1.distance(first);
          double d2 = p1.distance(second);
          if (d2 < d1) {
            result = new DefaultSegment(second, first);
          } else {
            result = new DefaultSegment(first, second);
          }
        }
      }

    }
    return Optional.ofNullable(result);
  }

  /**
   * Gets the intersection between this segment and the other segment. I think
   * this is always a 2D intersection, even if there are z values.
   *
   * @param other The other segment.
   * @return An optional shape that is empty if there is no intersection, or a
   * segment if they overlap and are colinear, and a point shape if only one
   * point touches.
   */
  @Override
  public final Optional<Shape> intersection(@NonNull SegmentValues other) {
    if (other == null) {
      throw new IllegalArgumentException("Argument other cannot be null.");
    }
    GeometryFactory gf = new GeometryFactory();
    Coordinate a = getP1().toCoordinate();
    Coordinate b = getP2().toCoordinate();
    Coordinate c = CoordFactory.get(other.getP1()).toCoordinate();
    Coordinate d = CoordFactory.get(other.getP2()).toCoordinate();
    LineString seg1 = gf.createLineString(new Coordinate[]{a, b});
    LineString seg2 = gf.createLineString(new Coordinate[]{c, d});
    Geometry geom = seg1.intersection(seg2);
    if (geom == null || geom.isEmpty()) {
      return Optional.empty();
    }
    if (geom instanceof Point) {
      Point pt = (Point) geom;
      return Optional.of(new Shape(CoordFactory.get(pt.getCoordinate())));
    }
    if (geom instanceof LineString) {
      LineString ls = (LineString) geom;
      Shape result = new Shape(ShapeType.PolyLine);
      Part part = new Part();
      part.getCoordinates().add(CoordFactory.get(ls.getCoordinateN(0)));
      part.getCoordinates().add(CoordFactory.get(ls.getCoordinateN(1)));
      result.getParts().add(part);
      return Optional.of(result);
    }
    return Optional.empty();
  }

  /**
   * If one and only one set of endpoints intersect, this returns the
   * intersecting endpoints. Otherwise this returns empty.
   *
   * @param other The other segment values.
   * @return An optional coordinate if the endpoints don't touch or both
   * endpoints intersect.
   */
  @Override
  public final Optional<Coord> getJoint(SegmentValues other) {
    Coord a = CoordFactory.get(other.getP1());
    Coord b = CoordFactory.get(other.getP2());
    if (this.getP1().intersects(a) && !this.getP2().intersects(b)) {
      return Optional.of(a);
    }
    if (this.getP1().intersects(b) && !this.getP2().intersects(a)) {
      return Optional.of(b);
    }
    if (this.getP2().intersects(a) && !this.getP1().intersects(b)) {
      return Optional.of(a);
    }
    if (this.getP2().intersects(b) && !this.getP1().intersects(a)) {
      return Optional.of(b);
    }
    return Optional.empty();
  }



  /**
   * Optional coordinate. This will either be an CoordXY, or a CoordZ.
   *
   * @param planeValue The double value of the X, Y or Z plane.
   * @param planeDimension 0 - x, 1 - y, 2 - z
   * @param hasZ boolean that should be true if the envelope hasZ. This will be
   * ignored if the segment coordinates do not have a z value.
   * @return The optional coord intersection
   */
  private Optional<Coord> intersectionWithPlane(double planeValue,
      int planeDimension, boolean hasZ) {
    if (planeDimension < 0 || planeDimension > 2) {
      throw new IllegalArgumentException("Parameter planeDimension must "
          + "range from 0 to 2.");
    }
    double v1 = p1.get(planeDimension);
    double v2 = p2.get(planeDimension);
    Coord low = p1;
    Coord high = p2;
    // reverse coordinates.
    if (v2 < v1) {
      double temp = v2;
      Coord tempCoord = high;
      v2 = v1;
      high = low;
      v1 = temp;
      low = tempCoord;
    }

    Coord result = null;

    if (v1 < planeValue && v2 > planeValue) {
      double dv = (planeValue - v1) / (v2 - v1);
      double x = low.getX() + (high.getX() - low.getX()) * dv;
      double y = low.getY() + (high.getY() - low.getY()) * dv;

      if (this.hasZ()) {
        // z values should be interpolated as long as the segment has z,
        // regardless of whether the envelope has z or not.
        double z = low.get(Coord.Index.Z) + (high.get(Coord.Index.Z)
            - low.get(Coord.Index.Z)) * dv;
        result = new CoordZ(x, y, z);
      } else {
        result = new CoordXY(x, y);
      }

    }
    return Optional.ofNullable(result);
  }

  /**
   * Gets a boolean that is true if both coordinates have a z value.
   *
   * @return The boolean that is true if both coordinate have a z.
   */
  @Override
  public final boolean hasZ() {
    return p1.hasZ() && p2.hasZ();
  }

  /**
   * This determines if the point is within EPSILON distance of the line.
   *
   * @param point The coordinate to intersect with. This can be null, in which
   * case intersects returns false.
   * @return The boolean that is true if the point intersects with the output.
   */
  @Override
  public final boolean intersects(@Nullable Coord point) {
    if (point == null) {
      return false;
    }
    return distanceTo(point) <= EPSILON;
  }

  /**
   * This tests to determine if the specified point intersects either of the
   * vertices of this segment.
   *
   * @param point The point to test. This can be null, in which case, this
   * returns false.
   * @return Boolean, true if the point is within Coordinate.EPSILION of any of
   * the points on this segment.
   */
  @Override
  public final boolean intersectsEndpoint(@Nullable Coord point) {
    if (point == null) {
      return false;
    }
    if (point.intersects(p1)) {
      return true;
    }
    if (point.intersects(p2)) {
      return true;
    }
    return false;
  }

  /**
   * The length of this segment.
   *
   * @return the double length.
   */
  @Override
  public final double length() {
    return p1.distance(p2);
  }

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
  @Override
  public final List<Segment> split(@Nullable Coord vertex) {
    List<Segment> result = new ArrayList<>();
    if (vertex == null) {
      result.add(this.copy());
      return result;
    }
    ClosestCoord c = closestPointTo(vertex, false);
    if (c.getInteraction().equals(EndPointInteraction.OnLine)) {
      result.add(new DefaultSegment(p1.copy(), c.getCoord()));
      result.add(new DefaultSegment(c.getCoord(), p2.copy()));
    } else {
      result.add(this.copy());
    }
    return result;
  }

  /**
   * Converts this segment to a vector, starting at P1 and ending at P2.
   *
   * @return A vector defined by placing the tail at the start (P1) and the tip
   * at the end point (P2).
   */
  @Override
  public final Vector toVector() {
    return new Vector(p1, p2);
  }

  /**
   * Get angle (slope) of the segment along the 2D axis. The angle will be range
   * from -pi/2 and pi/2.
   *
   * @return the calculated angle or 0.0 if the segment start or end point is
   * undefined.
   */
  @Override
  public final double getAngle2D() {
    double result = 0.0d;
    Vector vector = this.toVector();
    if ((vector != null) && (!Double.isNaN(vector.getX()))
        && (!Double.isNaN(vector.getY())
        && vector.getY() != 0.0d)) {
      if (vector.getX() == 0.0d) {
        result = ((vector.getY() / Math.abs(vector.getY()))
            * (Math.PI / 2.0d));
      } else {
        result = Math.atan(vector.getY() / vector.getX());
      }
    }
    return result;
  }

  /**
   * @return the a
   */
  @Override
  public final Coord getP1() {
    return p1;
  }

  /**
   * @return the b
   */
  @Override
  public final Coord getP2() {
    return p2;
  }

  /**
   * Gets the string.
   *
   * @return The string.
   */
  @Override
  public final String toString() {
    String z1 = "";
    if (p1.hasZ()) {
      z1 = ", " + p1.get(Coord.Index.Z);
    }
    String z2 = "";
    if (p2.hasZ()) {
      z2 = ", " + p2.get(Coord.Index.Z);
    }
    return "(" + p1.getX() + ", " + p1.getY() + z1 + ")-(" + p2.getX() + ", "
        + p2.getY() + z2 + ")";
  }

  /**
   * Tests the other segment to determine if the end points touch. This is not
   * true unless it is only the actual end vertices that touch. If the interior
   * touches (as is the case with overlapping segments) this is false.
   *
   * @param other The other SegmentValues to test.
   * @return Boolean, true if the endpoints touch, but the interior parts do not
   * overlap.
   */
  @Override
  public final boolean touches(SegmentValues other) {
    int intersectionCount = this.intersectionCount(other);
    if (intersectionCount == 0 || intersectionCount == 2) {
      return false;
    }
    if (getP1().equals(other.getP1())) {
      return true;
    }
    if (getP2().equals(other.getP1())) {
      return true;
    }
    if (getP1().equals(other.getP2())) {
      return true;
    }
    return getP2().equals(other.getP2());
  }

  //</editor-fold>
}
