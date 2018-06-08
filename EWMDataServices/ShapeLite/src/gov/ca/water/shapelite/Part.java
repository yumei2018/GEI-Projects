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

import gov.ca.water.shapelite.analysis.CoordIndex;
import gov.ca.water.shapelite.analysis.CoordIndexEnumerator;
import gov.ca.water.shapelite.coordinate.ClosestCoord;
import gov.ca.water.shapelite.coordinate.CoordM;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.segment.DefaultSegment;
import gov.ca.water.shapelite.segment.ReversableSegment;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * This represents a single part of a shapefile or part tree. This has a list of
 * coordinates that make up this part. The order of the coordinates is
 * important.
 *
 * @author Harold A. Dunsford Jr. Ph.D.; modified by kprins;
 */
public class Part implements Serializable, Iterable<CoordIndex> {

  /**
   * Added by Ricardo to be used as a place holder for empty rather than
   * optional.
   */
  private static Part nullValue;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * A boolean value that is true if this part should be regarded as a closed
   * loop.
   */
  private boolean closed;

  /**
   * The list of coordinates that make up this part. This will never be null.
   * This is final to prevent possible null setting.
   */
  private final List<Coord> coordinates;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Initializes a new instance of the Part class.
   */
  public Part() {
    coordinates = new ArrayList<>();
  }

  /**
   * Initializes a new instance of the Part class with only the specified
   * coordinate.
   *
   * @param coord The Coord to use to define the part. This can be null, in
   * which case it creates an empty part.
   */
  public Part(@Nullable Coord coord) {
    coordinates = new ArrayList<>();
    if (coord != null) {
      coordinates.add(coord);
    }
  }

  /**
   * Initializes a new part from a list of coordinates. The coordinates from the
   * incoming list will be added to this part.
   *
   * @param coords The list of coords to use in this part. This can be null, in
   * which case, it returns an empty part.
   */
  public Part(@Nullable List<? extends Coord> coords) {
    coordinates = new ArrayList<>();
    if (coords != null) {
      coordinates.addAll(coords);
    }

  }

  /**
   * Initializes a new part.
   *
   * @param coords The coordinate parameters or an array of coordinates.
   */
  public Part(@Nullable Coord... coords) {
    coordinates = new ArrayList<>();
    if (coords != null) {
      coordinates.addAll(Arrays.asList(coords));
    }

  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Calculates the area for this feature.
   *
   * @return The double valued area. Return 0 if (!this.isClosed())
   */
  public final double area() {
    double result = 0.0d;
//    if (this.closed) {
      List<Segment> segs = getSegments();
      double total = 0;
      for (Segment seg : segs) {
        total += ((seg.getP1().getX() * seg.getP2().getY())
            - (seg.getP2().getX() * seg.getP1().getY()));
      }
      result = -total / 2;
//    }
    return result;
  }

  /**
   * A list of coordinates defines coordinate equality as intersection, not
   * object equality. It is very possible to have multiple occurrences of the
   * same coordinate in this part. This returns the indices of all such
   * occurrences.
   *
   * @param coord The coordinate to search for.
   * @return The array of indices matching the coordinate.
   */
  public final List<Integer> findAllIndices(Coord coord) {
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < this.getCoordinates().size(); i++) {
      if (this.getCoordinates().get(i).equals(coord)) {
        result.add(i);
      }
    }
    return result;
  }

  /**
   * This cycles through the segments on this part, finds the closest segment,
   * and then gets the distance of the closest point on that segment to the
   * specified point.
   *
   * @param point The point to test. This cannot be null.
   * @return An optional representing the closest point to the specified point.
   * The optional is empty if this part has no coordinates.
   * @throws IllegalArgumentException if Coord point is null.
   */
  public final Optional<Coord> closestPointTo(@NonNull Coord point) {
    if (point == null) {
      throw new IllegalArgumentException("Coord point cannot be null.");
    }
    if (this.coordinates.isEmpty()) {
      return Optional.empty();
    }
    if (this.coordinates.size() == 1) {
      return this.start();
    }
    Optional<Segment> seg = closestSegment(point);

    if (seg.isPresent()) {
      return Optional.of(seg.get().closestPointTo(point).getCoord());
    }
    return Optional.empty();
  }

  /**
   * This cycles through the segments on this part, finds the closest segment,
   * and then returns that segment.
   *
   * @param point The point to test. This cannot be null.
   * @return An Optional&lt;Segment&gt; that has the closest segment to the test
   * point or is empty. This can be empty if this part has insufficient
   * coordinates to define a segment.
   */
  public final Optional<Segment> closestSegment(@NonNull Coord point) {
    if (point == null) {
      throw new IllegalArgumentException("Argument 'point' in method closestSegment"
          + " cannot be null.");
    }
    List<Segment> segs = this.getSegments();
    double minDist = Double.MAX_VALUE;
    Segment closestSegment = null;
    for (Segment seg : segs) {
      double dist = seg.distanceTo(point);
      if (dist < minDist) {
        closestSegment = seg;
        minDist = dist;
      }
    }
    return Optional.ofNullable(closestSegment);
  }

  /**
   * Gets the first coordinate, assuming there are any coordinates.
   *
   * @return The coordinate, or empty if no coordinate is found.
   */
  public final Optional<Coord> getFirst() {
    Coord result = null;
    if (!coordinates.isEmpty()) {
      result = coordinates.get(0);
    }
    return Optional.ofNullable(result);
  }

  /**
   * Calculates the bounding envelope. This is calculated on the fly and is not
   * a cached property of the Part class.
   *
   * @return The Envelope.
   */
  public final Envelope getEnvelope() {
    Envelope result = new DefaultEnvelope();
    for (Coord c : coordinates) {
      result.expandToInclude(c);
    }
    return result;
  }

  /**
   * Gets the last coordinate, assuming there are any coordinates.
   *
   * @return The last coordinate, or empty if no coordinates exist.
   */
  public final Optional<Coord> getLast() {
    Coord result = null;
    if (!coordinates.isEmpty()) {
      result = coordinates.get(coordinates.size() - 1);
    }
    return Optional.ofNullable(result);
  }

  /**
   * A segment with P1 and P2 defining the closest physical connection between
   * the two parts. The link is always directed from this part to the other
   * part.
   *
   * @param otherPart The other part to consider a connection between.
   * @return The shortest segment or null if either part has no coordinates.
   * @throws IllegalArgumentException if otherPart is null.
   */
  public final Optional<Segment> shortestLink(@NonNull Part otherPart) {
    if (otherPart == null) {
      throw new IllegalArgumentException("Part otherPart cannot be null.");
    }
    if (this.getCoordinates().isEmpty()
        || otherPart.getCoordinates().isEmpty()) {
      return Optional.empty();
    }
    if (this.getCoordinates().size() == 1) {
      Optional<Coord> myStart = this.start();
      if (myStart.isPresent()) {
        Coord start = myStart.get();
        if (otherPart.getCoordinates().size() == 1) {
          Optional<Coord> otherStart = otherPart.start();
          if (myStart.isPresent() && otherStart.isPresent()) {
            Segment result = new DefaultSegment(start, otherStart.get());
            return Optional.of(result);
          } else {
            throw new IllegalStateException("Start should always be present "
                + "if the coordinates list size is 1.");
          }
        } else {
          Segment result = null;
          Optional<Coord> endItem = otherPart.closestPointTo(start);
          if (endItem.isPresent()) {
            Coord end = endItem.get();
            result = new DefaultSegment(start, end);
          }
          return Optional.ofNullable(result);
        }
      } else {
        throw new IllegalStateException("Start should always be present "
            + "if the coordinates list size is 1.");
      }
    }
    Segment shortest = null;

    for (Segment seg : this.getSegments()) {
      if (otherPart.getCoordinates().size() == 1) {
        Segment link = seg.shortestLink(otherPart.getCoordinates().get(0));
        if (shortest == null || link.length() < shortest.length()) {
          shortest = link;
        }
      } else {
        for (Segment other : otherPart.getSegments()) {
          Segment link = seg.shortestLink(other);
          if (shortest == null || link.length() < shortest.length()) {
            shortest = link;
          }
        }
      }
    }

    return Optional.ofNullable(shortest);
  }

  /**
   * Finds the integer index of the coordinate that has the best X, Y
   * correlation with the specified point. This can be null if there are no
   * coordinates.
   *
   * @param pt The Coord of point.
   * @return The Integer index of the closest point.
   * @throws IllegalArgumentException if Coord pt is null.
   */
  public final Optional<Integer> indexOfClosest(Coord pt) {
    if (pt == null) {
      throw new IllegalArgumentException("Coord pt cannot be null.");
    }
    double closest = Double.MAX_VALUE;
    Integer closestIndex = null;
    for (int i = 0; i < this.getCoordinates().size(); i++) {
      Coord c = this.getCoordinates().get(i);
      double dist = c.distance(pt);
      if (closestIndex == null || dist < closest) {
        closestIndex = i;
        closest = dist;
      }
    }
    return Optional.ofNullable(closestIndex);
  }

  /**
   * Performs point in polygon testing with a horizontal ray. If a ray in any
   * direction crosses the polygon an odd number of times, then the ray is
   * inside the polygon. If it crosses it an even number of times, then the ray
   * is outside the polygon.
   *
   * @param coordinate the coordinate to test for. This can be null, in which
   * case this returns false.
   * @return true if the coordinate is inside the polygon. Return false if
   * (!this.isClosed())
   */
  public final boolean contains(@Nullable Coord coordinate) {
    boolean result = false;
    if (coordinate == null) {
      return false;
    }
    if (this.closed) {
      List<Segment> segs = getSegments();
      int intersectionCount = 0;
      Segment ray
          = new DefaultSegment(coordinate.getX(), coordinate.getY(),
              Double.MAX_VALUE, coordinate.getY());
      for (Segment seg : segs) {
        if (seg.intersects(ray)) {
          if (!ray.intersects(seg.getP2())) {
            intersectionCount++;
          }
        }
      }
      result = (intersectionCount % 2 == 1);
    }
    return result;
  }

  /**
   * Check whether this part intersects with the other part. Even if only one
   * point of the other Part falls on a line segment of this or, if
   * (this.isClosed) one point of the other Part is contained inside this part.
   *
   * @param other The other part to test against this part. This can be null, in
   * which case this returns false.
   * @return true if the other intersects with this part.
   */
  public final boolean intersects(@Nullable Part other) {
    boolean result = false;
    if (other == null) {
      return false;
    }
    Envelope myEnv = this.getEnvelope();
    Envelope otherEnv = other.getEnvelope();
    if (!myEnv.intersects(otherEnv)) {
      return false;
    }
    List<Coord> otherCoords;

    otherCoords = other.coordinates;
    if (otherCoords != null && (!otherCoords.isEmpty())) {
      if (this.closed) {
        for (Coord coord : otherCoords) {
          if (this.contains(coord)) {
            result = true;
            break;
          }
        }
        List<Segment> otherSegs = other.getSegments();
        List<Segment> segs = this.getSegments();
        for (Segment otherSeg : otherSegs) {
          for (Segment seg : segs) {
            if (seg.intersects(otherSeg)) {
              return true;
            }
          }
        }
        return false;
      } else if ((this.coordinates != null)
          && (!this.coordinates.isEmpty())) {
        List<Segment> thisSegs;
        List<Segment> otherSegs = null;
        if (this.coordinates.size() == 1) {
          result = other.contains(this.coordinates.get(0));
        } else if (otherCoords.size() == 1) {
          result = this.contains(otherCoords.get(0));
        } else {
          thisSegs = this.getSegments();
          if (thisSegs != null && (!thisSegs.isEmpty())) {
            for (Segment thisSeg : thisSegs) {
              for (Segment otherSeg : otherSegs) {
                if (thisSeg.intersects(otherSeg)) {
                  result = true;
                  break;
                }
              }
              if (result) {
                break;
              }
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * Gets the minimum distance to an edge of the part. This does not consider
   * whether the point is inside the part or not.
   *
   * @param coordinate the coordinate to test for.
   * @return the minimum vertical distance between the coordinate and the any of
   * the part's segments.
   * @throws IllegalArgumentException if Coord coordinate is null.
   */
  public final Optional<Double> distance(@NonNull Coord coordinate) {
    if (coordinate == null) {
      throw new IllegalArgumentException("Coord coordinate cannot be null.");
    }
    if (this.getCoordinates() == null || this.getCoordinates().isEmpty()) {
      return Optional.empty();
    }
    Optional<Coord> start = this.start();
    if (this.getCoordinates().size() == 1) {
      return Optional.of(coordinate.distance(start.get()));
    }
    List<Segment> segs = this.getSegments();
    Double minDist = null;
    double minDistVal = Double.MAX_VALUE;
    for (Segment seg : segs) {
      double dist = seg.distanceTo(coordinate);
      if (dist < minDistVal) {
        minDistVal = dist;
        minDist = minDistVal;
      }
    }
    return Optional.of(minDist);
  }

  /**
   * Gets the minimum distance to an edge of the part. This does not consider
   * whether the point is inside the part or not. This treats all shapes as if
   * they were projected onto the Z = 0 plane.
   *
   * @param coordinate the coordinate to test for.
   * @return the minimum vertical distance between the coordinate and the any of
   * the part's segments.
   * @throws IllegalArgumentException if Coord coordinate is null.
   */
  public final Optional<Double> distance2D(@NonNull Coord coordinate) {
    if (coordinate == null) {
      throw new IllegalArgumentException("Coord coordinate cannot be null.");
    }
    if (this.getCoordinates() == null || this.getCoordinates().isEmpty()) {
      return Optional.empty();
    }
    if (this.getCoordinates().size() == 1) {
      Optional<Coord> start = this.start();
      if (start.isPresent()) {
        double dist = coordinate.distance2D(this.start().get());
        return Optional.of(dist);
      }
    }
    List<Segment> segs = this.getSegments();
    double minDist = Double.MAX_VALUE;
    Double result = null;
    for (Segment seg : segs) {
      double dist = seg.distanceTo2D(coordinate);
      if (dist < minDist) {
        minDist = dist;
        result = minDist;
      }
    }
    return Optional.ofNullable(result);
  }

  /**
   * Gets the minimum distance from any of the segments on this part to the
   * specified target segment.
   *
   * @param target The segment to get the distance to.
   * @return the minimum distance between the segment and the any of the part's
   * segments.
   * @throws IllegalArgumentException if Segment target is null.
   */
  public final Optional<Double> distance(@NonNull Segment target) {
    if (target == null) {
      throw new IllegalArgumentException("Segment target cannot be null.");
    }
    List<Segment> segs = this.getSegments();
    double minDist = Double.MAX_VALUE;
    Double result = null;
    for (Segment seg : segs) {
      double dist = seg.distanceTo(target);
      if (dist < minDist) {
        minDist = dist;
        result = dist;
      }
    }
    return Optional.ofNullable(result);
  }

  /**
   * Gets the minimum distance to the specified part.
   *
   * @param part the target part
   * @return the minimum distance between the part and the any of this part.
   * @throws IllegalArgumentException if Part part is null.
   */
  public final Optional<Double> distance(@NonNull Part part) {
    if (part == null) {
      throw new IllegalArgumentException("Part part cannot be null.");
    }
    double minDist = Double.MAX_VALUE;
    Double result = null;
    for (Segment seg : part.getSegments()) {
      Optional<Double> dist = distance(seg);
      if (dist.isPresent()) {
        if (dist.get() < minDist) {
          minDist = dist.get();
          result = minDist;
        }
      }
    }
    return Optional.ofNullable(result);
  }

  /**
   * Gets the last (size()-1) coordinate for this shape. This might be empty if
   * there are no coordinates.
   *
   * @return An optional Coord, which can be empty if there are no coordinates
   * in this shape.
   */
  public final Optional<Coord> end() {
    if (coordinates.isEmpty()) {
      return Optional.empty();
    }
    Coord result = coordinates.get(coordinates.size() - 1);
    return Optional.of(result);
  }

  /**
   * Generates a set of segments that represent this part.
   *
   * @return the list of Segments that can be created from this part. This will
   * never be null, even if there are no results, but may be empty.
   */
  public final List<Segment> getSegments() {
    List<Segment> result = new ArrayList<>();
    if (getCoordinates().size() < 2) {
      return result;
    }
    for (int i = 0; i < getCoordinates().size() - 1; i++) {
      result.add(new DefaultSegment(getCoordinates().get(i),
          getCoordinates().get(i + 1)));
    }
    if (closed) {
      // In JTS closed shapes will already have this added coordinate,
      // but in shapefile shapes, we need to imagine an extra segment
      // connecting the last point to the first point.
      Coord startCoord = start().get();
      Coord endCoord = end().get();
      result.add(new DefaultSegment(endCoord, startCoord));
    }
    return result;
  }

  /**
   * Gets the number of segments on this part.
   *
   * @return The integer number of segments, which will depend on whether the
   * part is open or closed.
   */
  public final int getSegmentCount() {
    if (this.isClosed()) {
      return getCoordinates().size();
    }
    return getCoordinates().size() - 1;
  }

  /**
   * Generates a set of segments that represent this part.
   *
   * @return the list of Segments that can be created from this part. This will
   * never be null, even if there are no results, but may be empty.
   */
  public final List<ReversableSegment> getReversableSegments() {
    List<ReversableSegment> result = new ArrayList<>();
    if (getCoordinates().size() < 2) {
      return result;
    }
    for (int i = 0; i < getCoordinates().size() - 1; i++) {
      result.add(new ReversableSegment(getCoordinates().get(i),
          getCoordinates().get(i + 1)));
    }
    if (closed) {
      // In JTS closed shapes will already have this added coordinate,
      // but in shapefile shapes, we need to imagine an extra segment
      // connecting the last point to the first point.
      Coord startCoord = start().get();
      Coord endCoord = end().get();
      result.add(new ReversableSegment(endCoord, startCoord));
    }
    return result;
  }

  /**
   * This is more than simply reversing the order of the forward designed
   * segments. The Start and End point of the segments will also be flipped, so
   * this starts with the last point and works towards the first.
   *
   * @return List of Segment
   */
  public final List<Segment> getSegmentsReversed() {
    List<Segment> result = new ArrayList<>();
    if (getCoordinates().size() < 2) {
      return result;
    }
    if (closed) {
      result.add(new DefaultSegment(getCoordinates().get(0),
          getCoordinates().get(getCoordinates().size() - 1)));
    }
    for (int i = getCoordinates().size() - 2; i > -1; i--) {
      result.add(new DefaultSegment(getCoordinates().get(i + 1),
          getCoordinates().get(i)));
    }
    return result;
  }

  /**
   * Removes any coordinates that are the same as the previous value.
   */
  public final void removeDuplicates() {
    removeDuplicates(Double.MIN_NORMAL);
  }

  /**
   * Removes the coordinate from the specified index of this part.
   *
   * @param index The integer index of the integer part to remove.
   */
  public final void removeCoordinateAt(int index) {
    this.coordinates.remove(index);
  }

  /**
   * Removes the coordinates from the specified list of indices.
   *
   * @param indices the indices to remove.
   */
  public final void removeCoordinatesAt(List<Integer> indices) {
    List<Integer> localList = new ArrayList<>(indices);
    Collections.sort(localList);
    Collections.reverse(localList);
    for (Integer index : localList) {
      removeCoordinateAt(index);
    }
  }

  /**
   * Removes any coordinates that are the same as the previous value.
   *
   * @param epsilon A double tolerance for determining equality.
   */
  public final void removeDuplicates(double epsilon) {
    List<Coord> result = new ArrayList<>();
    Coord previous = null;
    for (Coord c : coordinates) {
      if (previous != null) {
        if (!previous.intersects(c, epsilon)) {
          result.add(c);
        }
      } else {
        result.add(c);
      }
      previous = c;
    }
    coordinates.clear();
    coordinates.addAll(result);
  }

  /**
   * Reverses the direction of the coordinates.
   */
  public final void reverse() {
    Collections.reverse(coordinates);
  }

  /**
   * Gets the coordinate along the part at the specified distance. If the
   * distance is negative, or at a greater distance than the part length, then
   * the end segment direction is used to extend the line, and a coordinate is
   * chosen along the length of that extended line that is the remainder
   * distance or the negative distance.
   *
   * @param distance The double distance.
   * @return The Coordinate at a specified distance along the part.
   * @throws IllegalArgumentException if distance is less than 0.
   */
  public final Optional<Coord> getCoordinateAtDistance(double distance) {

    if (distance < 0) {
      // in the case of negative distance, extend first segment.
      Coord a = this.coordinates.get(0);
      Coord b = this.coordinates.get(1);
      Vector dir = new Vector(a, b);
      Coord result = along(a, dir, distance);
      return Optional.of(result);
    }
    List<Segment> segments = getSegments();
    double remainder = distance;
    for (Segment seg : segments) {
      if (seg.length() > remainder) {
        return Optional.of(seg.coordAt(remainder));
      } else {
        remainder = remainder - seg.length();
      }
    }
    int iLast = this.coordinates.size() - 1;
    int iBefore = this.coordinates.size() - 2;
    Coord a = this.coordinates.get(iBefore);
    Coord b = this.coordinates.get(iLast);
    Vector dir = new Vector(a, b);
    Coord result = along(b, dir, remainder);
    return Optional.ofNullable(result);
  }

  /**
   * This uses a start coordinate and a second coordinate that defines the
   * direction to move. The returned value will be found distance along the
   * line, starting from start.
   *
   * @param start The start point of the line to find the coordinate.
   * @param dir The directional vector to move in.
   * @param distance The distance along the line.
   * @return The coordinate.
   */
  public final Coord along(Coord start, Vector dir, Double distance) {
    double dx = dir.getX();
    double dy = dir.getY();
    double ds = dir.length();
    double x = start.getX() + distance * dx / ds;
    double y = start.getY() + distance * dy / ds;
    Coord result = new CoordXY(x, y);
    return result;
  }

  /**
   * Gets the coordinate along the part at the specified distance. If the
   * distance is negative, or at a greater distance than the length of the part,
   * rather than using the ending segment to extrapolate a direction, this
   * method will use the start and end points of the part to determine the
   * direction to extend.
   *
   * @param distance The double distance.
   * @return The Coordinate at a specified distance along the part.
   * @throws IllegalArgumentException if distance is less than 0.
   */
  public final Optional<Coord> getCoordAtDistGeneralized(double distance) {
    if (this.coordinates.size() < 2) {
      return Optional.empty();
    }
    int iLast = this.coordinates.size() - 1;
    Coord first = this.coordinates.get(0);
    Coord last = this.coordinates.get(iLast);
    Vector generalDirection = new Vector(first, last);
    if (distance < 0) {
      // in the case of negative distance, extend first segment.
      Coord a = this.coordinates.get(0);
      Coord result = along(a, generalDirection, distance);
      return Optional.of(result);
    }
    List<Segment> segments = getSegments();
    double remainder = distance;
    for (Segment seg : segments) {
      if (seg.length() > remainder) {
        return Optional.of(seg.coordAt(remainder));
      } else {
        remainder = remainder - seg.length();
      }
    }
    Coord result = along(last, generalDirection, remainder);
    Optional.of(result);
    return Optional.ofNullable(result);
  }

  /**
   * Gets the distance to the specified coordinate.
   *
   * @param coord The Coordinate to find the distance to.
   * @return The Coordinate at a specified distance along the part.
   */
  public final Optional<Double> distanceTo(Coord coord) {
    List<Segment> allSegments = this.getSegments();
    Optional<Segment> closestSegment = this.closestSegment(coord);
    double distance = 0.0;
    for (Segment segment : allSegments) {
      if (!(segment.equals(closestSegment.get()))) {
        distance += segment.length();
      } else {
        Coord start = closestSegment.getItem().getP1();
        distance += start.distance(coord);
        return Optional.ofNullable(distance);
      }
    }
    return Optional.ofNullable(null);

  }

  /**
   * Gets the coordinate along the part at the specified distance.
   *
   * @param distance The double distance.
   * @return The Optional Double angle at a specified distance along the part.
   * If the distance is outside the bounds of this part, this returns empty.
   * @throws IllegalArgumentException if distance is less than 0, or greater
   * than the length of the part.
   */
  public final Optional<Double> getAngleAtDistance(double distance) {
    if (distance < 0) {
      throw new IllegalArgumentException("The distance should be greater than 0.");
    }
    if (distance > length()) {
      throw new IllegalArgumentException("The distance should be less than "
          + "the length of the part.");
    }
    List<Segment> segments = getSegments();
    double remainder = distance;
    for (Segment seg : segments) {
      if (seg.length() > remainder) {
        return Optional.of(seg.getAngle2D());
      } else {
        remainder = remainder - seg.length();
      }
    }
    return Optional.empty();
  }

  /**
   * Vertices of rings defining holes in polygons are in a counterclockwise
   * direction. Vertices for a single, ringed polygon are, therefore, always in
   * clockwise order
   *
   * @return A boolean that is true if the shape is a hole.
   */
  public final boolean isHole() {
    return this.area() < 0;
  }

  /**
   * Gets the entire length of this part along the segments of the part.
   *
   * @return the double length. This will be 0 if there are no coordinates.
   */
  public final double length() {
    double length = 0;
    List<Segment> segs = getSegments();
    for (Segment seg : segs) {
      length += seg.length();
    }
    return length;
  }

  /**
   * This is intended for linestrings. This will split the linestring on c.
   *
   * @param c The coordinate to split on. This new coordinate will be added to
   * both parts. This cannot be null.
   * @param startWithC true if both segments should be ordered to start with c.
   * Otherwise, the split portions are returned in the same order.
   * @return two parts if c is within a segment somewhere. If c is an endpoint,
   * or there are fewer than two points in the line, the line will not be split
   * and a copy of this part is returned in the list. If startWithC is true,
   * however, it may be reversed. throws IllegalArgumentException if Coord c is
   * null.
   */
  public final List<Part> split(@NonNull Coord c, boolean startWithC) {
    if (c == null) {
      throw new IllegalArgumentException("Coord c cannot be null.");
    }
    List<Part> results = new ArrayList<>();
    if (this.coordinates.size() < 2) {
      results.add(this.copy());
      return results;
    }
    Optional<Coord> maybeC = closestPointTo(c);
    if (!maybeC.isPresent()) {
      return results;
    }
    Coord endTest = maybeC.get();
    if (endTest.equals(this.coordinates.get(0))) {
      results.add(new Part(this.getCoordinates()));
      return results;
    }
    if (endTest.equals(this.coordinates.get(this.coordinates.size() - 1))) {
      Part result = new Part(coordinates);
      results.add(result);
      if (startWithC) {
        Collections.reverse(result.getCoordinates());
      }
      return results;
    }
    Optional<Segment> item = closestSegment(c);
    if (!item.isPresent()) {
      results.add(this.copy());
      return results;
    }
    Segment seg = item.get();
    Optional<Integer> itemA = indexOfClosest(seg.getP1());
    Optional<Integer> itemB = indexOfClosest(seg.getP2());
    if (!itemA.isPresent() || !itemB.isPresent()) {
      results.add(this.copy());
      return results;
    }
    int ia = itemA.get();
    int ib = itemB.get();
    if (ia == 0 && c.equals(seg.getP1())) {
      List<Coord> coords = new ArrayList<>(coordinates);
      Part prt = new Part(coords);
      results.add(prt);
      return results;
    }
    int imax = coordinates.size() - 1;
    if (ib == imax && c.equals(seg.getP2())) {
      List<Coord> coords = new ArrayList<>(coordinates);
      if (startWithC) {
        Collections.reverse(coords);
      }
      Part prt = new Part(coords);
      results.add(prt);
      return results;
    }
    List<Coord> aCoords = new ArrayList<>(coordinates.subList(0, ia + 1));
    List<Coord> bCoords = new ArrayList<>(coordinates.subList(ib, imax + 1));
    if (!c.equals(aCoords.get(aCoords.size() - 1))) {
      aCoords.add(c);
    }
    if (!c.equals(bCoords.get(0))) {
      bCoords.add(0, c);
    }
    if (startWithC) {
      Collections.reverse(aCoords);
    }
    results.add(new Part(aCoords));
    results.add(new Part(bCoords));
    return results;
  }

  /**
   * Returns a new Part that has duplicate coordinates to this part.
   *
   * @return A deep copy of this part.
   */
  public final Part copy() {
    Part result = new Part();
    result.copyProperties(this);
    return result;
  }

  /**
   * Copies the coordinate values from the other part, but does not use any of
   * the same memory slots.
   *
   * @param other A part to create a deep copy from.
   */
  public final void copyProperties(Part other) {
    coordinates.clear();
    for (Coord coord : other.coordinates) {
      coordinates.add(coord.copy());
    }
    closed = other.closed;
  }

  /**
   * Gets the first (0 index) coordinate for this shape.
   *
   * @return The first Coordinate.
   */
  public final Optional<Coord> start() {
    Coord result = null;
    if (!coordinates.isEmpty()) {
      result = coordinates.get(0);
    }
    return Optional.ofNullable(result);

  }

  /**
   * Tests the coordinates array to determine if it is empty.
   *
   * @return
   */
  public final boolean isEmpty() {
    return coordinates.isEmpty();
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets a boolean value that is true if this part should be regarded as a
   * closed loop.
   *
   * @return the closed
   */
  public final boolean isClosed() {
    return closed;
  }

  /**
   * Sets a boolean value that is true if this part should be regarded as a
   * closed loop.
   *
   * @param closed the closed to set
   */
  public final void setClosed(boolean closed) {
    this.closed = closed;
  }

  /**
   * Gets the list of coordinates that make up this part. This will never be
   * null.
   *
   * @return the coordinates. This is a list of coordinates. It can be empty,
   * but will never be null.
   */
  public final List<Coord> getCoordinates() {
    return coordinates;
  }

  /**
   * Gets a copy of the list of coordinates that make up this part converted to
   * CoordZ points. These points are always copies, even if the original
   * coordinates are coordZ values.
   *
   * @return A copy of the list of coordinates as CoordZ coordinate types. This
   * is a list of coordinates. It can be empty, but will never be null.
   */
  public final List<CoordZ> getCoordinatesZ() {
    List<CoordZ> result = new ArrayList<>();
    for (Coord c : coordinates) {
      CoordZ cz = new CoordZ();
      cz.setX(c.getX());
      cz.setY(c.getY());
      if (c.hasM()) {
        cz.setM(c.get(Coord.Index.M));
      }
      if (c.hasZ()) {
        cz.setZ(c.get(Coord.Index.Z));
      }
      result.add(cz);
    }
    return result;
  }

  //</editor-fold>
  /**
   * By default, this gets an iterator that starts at zero and works through all
   * the coordinates in the part in ascending order.
   *
   * @return
   */
  @Override
  public Iterator<CoordIndex> iterator() {
    return new CoordIndexEnumerator(this);
  }

  /**
   * Clips part according to startPoint and endPoint coordinates. If the part
   * actually contains the points, then it uses the first instance of the start
   * point to start, and the first instance of the endPoint after that to end
   * while moving through the coordinates sequentially. If the actual
   * coordinates are not contained, but intersect, then the intersection
   * locations are used in the same way. If there is no intersection, then the
   * closest point on the part to the specified endpoints is used instead.
   *
   * @param startPoint The Coordinate to use as the start point for the clip
   * operation.
   * @param endPoint The Coordinate to use as the end point.
   * @return The resulting Part may have no coordinates.
   */
  public final Part clip(Coord startPoint, Coord endPoint) {
    Part result = new Part();
    List<Part> startSplit = this.split(startPoint, false);
    Part remainder;
    if (startSplit.size() > 1) {
      remainder = startSplit.get(1);
    } else if (startSplit.size() > 0) {
      remainder = startSplit.get(0);
    } else {
      return result; // this should not happen.
    }
    int remainderLen = remainder.getCoordinates().size();
    List<Part> parts = remainder.split(endPoint, false);
    if (parts.size() > 1) {
      Part trim = parts.get(0);
      int trimLen = trim.getCoordinates().size();
      if (trimLen > 1 && trimLen < remainderLen) {
        // successful trim
        return trim;
      }
    }
    // the tail of the first part might not be good, try going backwards.
    Part lead = startSplit.get(0);
    int leadLen = lead.getCoordinates().size();
    List<Part> lastParts = lead.split(endPoint, false);
    if (lastParts.size() > 1) {
      Part tail = lastParts.get(1);
      int tailLen = tail.getCoordinates().size();
      if (tailLen > 1 && tailLen < leadLen) {
        return tail;
      }
    }
    return result;
  }

  /**
   * This method finds the index of the specified startPoint in the list of
   * coordinates preferentially, and gets the sublist inclusive of the point to
   * the end of this part. If the startPoint is not an actual point, then the
   * closest point in the line to the specified point is used instead.
   *
   * @param startPoint The startPoint to define where to start keeping
   * coordinates.
   * @return A part containing the remaining points. This will always be a Part,
   * though the coordinate list could be empty if this list is empty.
   */
  public final Part remainder(@NonNull Coord startPoint) {
    if (startPoint == null) {
      throw new IllegalArgumentException("startPoint cannot be null");
    }
    int iStart = this.coordinates.indexOf(startPoint);
    List<Coord> coords = new ArrayList<>();
    if (iStart >= 0) {
      coords = this.coordinates.subList(iStart, this.coordinates.size());
    } else {
      Optional<Coord> maybeCoord = closestPointTo(startPoint);
      if (maybeCoord.isPresent()) {
        Coord start = maybeCoord.get();
        boolean started = false;
        for (Segment seg : this.getSegments()) {
          if (!started && seg.intersects(start)) {
            started = true;
            coords.add(start);
          }
          if (started) {
            coords.add(seg.getP2());
          }
        }
      }
    }
    Part result = new Part();
    result.setClosed(false);
    result.getCoordinates().addAll(coords);
    return result;
  }

  /**
   * Clips part according to distances as measured from the first coordinate in
   * this part, and assuming that all the coordinates form a continuous
   * connected line string. If the start distance is greater than the end
   * distance, the returned piece will be reversed compared to the original
   * segment, but still represent distances as measured from the 0 index point.
   *
   * @param startDistance The double distance start distance for the segment to
   * split on, inclusive.
   * @param endDistance The double distance end distance for the segment to
   * split on, inclusive.
   * @return The coordinates from the startDistance coord to the endDistance
   * coord. If the startDistance is greater than the endDistance, the returned
   * coordinates will be reversed compared to this part.
   */
  public final Part clip(Double startDistance, Double endDistance) {
    double start = startDistance;
    double end = endDistance;
    if (startDistance > endDistance) {
      end = startDistance;
      start = endDistance;
    }
    List<Coord> clipCoords = new ArrayList<>();
    if (start > 0) {
      // Don't add a "start point" if that point is off the line.
      Coord startPoint = this.getCoordinateAtDistance(start).orElse(new CoordXY());
      clipCoords.add(startPoint);
    }
    Coord endPoint = this.getCoordinateAtDistance(end).orElse(new CoordXY());
    List<Segment> segs = this.getSegments();

    double totalDistance = 0;

    for (Segment seg : segs) {
      if (totalDistance + seg.length() < start) {
        totalDistance += seg.length();
        continue;
      }
      if (totalDistance > end) {
        ClosestCoord onLine = seg.closestPointTo(endPoint);
        clipCoords.add(onLine.getCoord());
        break;
      }
      if (clipCoords.isEmpty()) {
        clipCoords.add(seg.getP1());
      }
      if (!clipCoords.contains(seg.getP2())) {
        clipCoords.add(seg.getP2());
      }
      totalDistance += seg.length();
      if (totalDistance == end) {
        break;
      }
    }
    Part result = new Part(clipCoords);
    if (endDistance < startDistance) {
      result.reverse();
    }
    return result;
  }

  /**
   * This method returns a duplicate of this part except that it uses
   * coordinates that have the specified M and Z properties. If Z is present the
   * CoordZ type will be used. If only M is present, the coordinates will be
   * CoordM type. If both are false, the Coordinates will be CoordXY.
   *
   * @param hasM boolean, true if the output coordinates should have M values.
   * @param hasZ boolean, true if output coordinates should have Z values.
   * @return The Part.
   */
  public Part changeCoordinateType(boolean hasM, boolean hasZ) {
    Part result = new Part();
    for (Coord coord : coordinates) {
      if (hasZ) {
        CoordZ z = new CoordZ(coord);
        result.getCoordinates().add(z);
      } else if (hasM) {
        CoordM m = new CoordM(coord);
        result.getCoordinates().add(m);
      } else {
        CoordXY x = new CoordXY(coord);
        result.getCoordinates().add(x);
      }
    }
    return result;
  }

  /**
   * *
   * Gets the coordinate at the center of the linear feature by incrementing
   * along the line to half the total distance of the part.
   *
   * @return The coordinate at the linear center of this part.
   */
  public final Optional<Coord> getCenter() {
    return this.getCoordinateAtDistance(this.length() / 2);
  }

  /**
   * Moving through this shape in order of the coordinates, this will return the
   * vertex point that exists immediately before the specified point. If a point
   * is specified that does not intersect, this will return the point before the
   * closest point on this segment to the specified point. If these points are
   * empty, this will return an empty CoordXY with 0,0 values.
   *
   * @param point The point to use to define the intersection or closest point
   * on the line in order to find the vertex before it.
   * @return A Coord that is in the list of coordinates and positioned before
   * the closest point on this line to the specified point, or an empty CoordXY.
   */
  public final Coord before(@NonNull Coord point) {
    if (point == null) {
      throw new IllegalArgumentException("point cannot be null.");
    }
    if (this.coordinates.isEmpty()) {
      throw new IllegalStateException("Cannot call Part.before() on an empty part.");
    }
    List<Segment> segs = this.getSegments();
    Double pointDistance = this.distanceTo(point).orElse(0.0);
    double totalDistance = 0;
    for (Segment seg : segs) {
      if (totalDistance + seg.length() < pointDistance) {
        totalDistance += seg.length();
        continue;
      }
      return seg.getP1();
    }
    return this.getLast().get();
  }

  /**
   * Moving through this shape in order of the coordinates, this will return the
   * vertex point that exists immediately after the specified point. If a point
   * is specified that does not intersect, this will return the point before the
   * closest point on this segment to the specified point. If these points are
   * empty, this will return an empty CoordXY with 0,0 values.
   *
   * @param point The point to use to define the intersection or closest point
   * on the line in order to find the vertex before it.
   * @return A Coord that is in the list of coordinates and positioned before
   * the closest point on this line to the specified point, or an empty CoordXY.
   */
  public final Coord after(@NonNull Coord point) {
    if (point == null) {
      throw new IllegalArgumentException("point cannot be null.");
    }
    if (this.coordinates.isEmpty()) {
      throw new IllegalStateException("Cannot call Part.after() on an empty part.");
    }
    List<Segment> segs = this.getSegments();
    Double pointDistance = this.distanceTo(point).orElse(Double.NaN);
    double totalDistance = 0;
    for (Segment seg : segs) {
      if (totalDistance + seg.length() < pointDistance) {
        totalDistance += seg.length();
        continue;
      }
      return seg.getP2();
    }
    return new CoordXY();
  }

  /**
   * Gets a part with a single 0,0 coordinate.
   *
   * @return An empty part.
   */
  public static Part NullValue() {
    if (nullValue == null) {
      nullValue = new NullPart();
    }
    return nullValue;
  }

  /**
   * Null Part.
   */
  private static class NullPart extends Part {

    /**
     * Creates a new instance of a Null Part.
     */
    private NullPart() {
      super(new CoordXY(0.0, 0.0));
    }
  }

}
