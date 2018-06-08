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
package gov.ca.water.shapelite.analysis;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineSegment;
import com.vividsolutions.jts.geom.Polygon;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Segment;
import gov.ca.water.shapelite.coordinate.CoordFactory;
import gov.ca.water.shapelite.segment.DefaultSegment;
import gov.ca.water.shapelite.segment.ReversableSegment;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to handle contouring. A grid square takes the center point to create
 * four triangles. Each triangle can use this class to calculate contour
 * segments.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ContourTriangle {

  /**
   * The triangle has three vertices.
   */
  public static final int NUM_VERTICES = 3;

  /**
   * The first vertex of the triangle.
   */
  private final Coordinate[] vertices = new Coordinate[NUM_VERTICES];

  /**
   * A default geometry factory.
   */
  private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory();

  /**
   * a.
   */
  public static final int A = 0;
  /**
   * b.
   */
  public static final int B = 1;
  /**
   * c.
   */
  public static final int C = 2;

  /**
   * The value to use to replace missing values by default.
   */
  public static final double DEFAULT_MISSING = -.11;

  /**
   * The z value that represents missing data.
   */
  private double noDataValue;

  /**
   * The double value that should be used if the value is no-data.
   */
  private double missingContourLevel;

  /**
   * The Coord objects should be 3 dimensional, containing X, Y and Z values.
   * This will use 0 as the missing and no data value.
   *
   * @param a The first vertex
   * @param b The second vertex
   * @param c The third vertex
   */
  public ContourTriangle(Coord a, Coord b, Coord c) {
    vertices[A] = a.toCoordinate();
    vertices[B] = b.toCoordinate();
    vertices[C] = c.toCoordinate();
    this.noDataValue = 0;
    this.missingContourLevel = 0;

  }

  /**
   * The Coord objects should be 3 dimensional, containing X, Y and Z values.
   *
   * @param a The first vertex
   * @param b The second vertex
   * @param c The third vertex
   * @param noDataValue The value representing missing data in the source
   * raster.
   * @param missingContourLevel The value to use to represent missing data when
   * building the triangles.
   */
  public ContourTriangle(Coordinate a, Coordinate b, Coordinate c,
      double noDataValue, double missingContourLevel) {
    vertices[A] = a;
    vertices[B] = b;
    vertices[C] = c;
    this.noDataValue = noDataValue;
    this.missingContourLevel = missingContourLevel;

  }

  /**
   * Gets a segment that represents a single segment for this contour.
   *
   * @param level The level to return.
   * @return Either the LineSegment for this triangle or null if the segment
   * should to return a level.
   */
  @NonNull
  public final Optional<LineSegment> getContour(double level) {
    double[] z = new double[NUM_VERTICES];

    Coordinate a = vertices[A];
    Coordinate b = vertices[B];
    Coordinate c = vertices[C];
    z[A] = getZ(a);
    z[B] = getZ(b);
    z[C] = getZ(c);
    int countBelow = 0;
    int countOn = 0;
    int countAbove = 0;

    for (int i = 0; i < NUM_VERTICES; i++) {
      if (z[i] < level) {
        countBelow++;
      }
      if (z[i] == level) {
        countOn++;
      }
      if (z[i] > level) {
        countAbove++;
      }
    }
    // case a - All the vertices lie below the contour level
    if (countAbove == NUM_VERTICES) {
      return Optional.empty();
    }
    // case b - Two vertices lie below and one on the contour level
    if (countBelow == 2 && countOn == 1) {
      return Optional.empty();
    }
    // case c - two vertices lie below and one above the level
    if (countBelow == 2 && countAbove == 1) {
      if (z[A] > level) {
        return Optional.of(twoBelowOneAbove(b, c, a, level));
      }
      if (z[B] > level) {
        return Optional.of(twoBelowOneAbove(a, c, b, level));
      }
      if (z[C] > level) {
        return Optional.of(twoBelowOneAbove(a, b, c, level));
      }
    }
    // case d and h - One vertex lies above or below and two are on the contour
    // level.
    if (countOn == 2) {
      if (z[A] == level && z[B] == level) {
        return Optional.of(new LineSegment(a, b));
      }
      if (z[A] == level && z[C] == level) {
        return Optional.of(new LineSegment(a, c));
      }
      if (z[B] == level && z[C] == level) {
        return Optional.of(new LineSegment(b, c));
      }
    }
    // Case e - One vertex below, one on, and one above.
    if (countBelow == 1 && countOn == 1 && countAbove == 1) {
      Coordinate above = null;
      Coordinate below = null;
      Coordinate on = null;
      for (int i = 0; i < NUM_VERTICES; i++) {
        Coordinate coord = vertices[i];
        double zValue = z[i];
        if (zValue > level) {
          above = coord;
        }
        if (zValue == level) {
          on = coord;
        }
        if (zValue < level) {
          below = coord;
        }
      }
      if (on == null || above == null || below == null) {
        System.out.println("ContourTriangle - countBelow, countOn, and "
            + "countAbove were all 1, but at least one coordinate was null.");
        return Optional.empty();
      }
      return Optional.of(belowOnAbove(below, on, above, level));
    }
    // case f - One vertex below, two above
    if (countBelow == 1 && countAbove == 2) {
      if (z[A] < level) {
        return Optional.of(oneBelowTwoAbove(a, b, c, level));
      }
      if (z[B] < level) {
        return Optional.of(oneBelowTwoAbove(b, a, c, level));
      }
      if (z[C] < level) {
        return Optional.of(oneBelowTwoAbove(c, a, b, level));
      }
    }

    /**
     * case g - all 3 vertices on the level. This is a problematic case that may
     * require extra help merging shapes afterwards. We are currently trying not
     * returning anything, but this may have the effect of creating notable gaps
     * in flat areas. If we return something, we get large grid looking shapes
     * that are not correct either.
     */
    // case h - Two vertices lie on and one above the contour level
    if (countOn == 2 && countAbove == 1) {
      if (z[A] == level && z[B] == level) {
        return Optional.of(new LineSegment(a, b));
      }
      if (z[A] == level && z[C] == level) {
        return Optional.of(new LineSegment(a, c));
      }
      if (z[B] == level && z[C] == level) {
        return Optional.of(new LineSegment(b, c));
      }
    }

    // case i - One vertex lies on tand two abvoe the contour level
    // case j - All the vertices lie above the contour level
    return Optional.empty();
  }

  /**
   * Gets the following segments.
   * <ul>
   * <li>A - B</li>
   * <li>B - C</li>
   * <li>C - A</li>
   * </ul>
   *
   * @return The List of segments for this triangle.
   */
  public final List<ReversableSegment> getSegments() {


    Coord a = CoordFactory.get(vertices[A]);
    Coord b = CoordFactory.get(vertices[B]);
    Coord c = CoordFactory.get(vertices[C]);
    List<ReversableSegment> result = new ArrayList<>();
    result.add(new ReversableSegment(a.copy(), b.copy()));
    result.add(new ReversableSegment(b.copy(), c.copy()));
    result.add(new ReversableSegment(c.copy(), a.copy()));
    return result;
  }



  /**
   * Gets an optional coordinate that should be the offline coordinate given two
   * of the points of the segment.
   *
   * @param seg The segment to get offline points from.
   * @return An optional coordinate. This is empty if the segment match could
   * not be made.
   */
  public final Optional<Coord> getOfflinePoint(Segment seg) {
    Coord a = CoordFactory.get(vertices[A]);
    Coord b = CoordFactory.get(vertices[B]);
    Coord c = CoordFactory.get(vertices[C]);
    Segment ab = new DefaultSegment(a, b);
    if (ab.equals(seg) || ab.equalsReversed(seg)) {
      return Optional.of(c);
    }
    Segment bc = new DefaultSegment(b, c);
    if (bc.equals(seg) || bc.equalsReversed(seg)) {
      return Optional.of(a);
    }
    Segment ca = new DefaultSegment(c, a);
    if (ca.equals(seg) || ca.equalsReversed(seg)) {
      return Optional.of(b);
    }
    return Optional.empty();

  }

  /**
   * Creates a new Part that represents a closed set of line segments in the
   * shape of a triangle.
   *
   * @return Part
   */
  public final Part toPart() {
   Coord a = CoordFactory.get(vertices[A]);
    Coord b = CoordFactory.get(vertices[B]);
    Coord c = CoordFactory.get(vertices[C]);
    Part result = new Part();
    result.getCoordinates().add(a);
    result.getCoordinates().add(b);
    result.getCoordinates().add(c);
    result.setClosed(true);
    return result;
  }

  /**
   * Gets all three coordinates of this triangle in a list.
   * @return The list of Coord objects.
   */
  public final List<Coord> toCoords(){
    List<Coord> result = new ArrayList<>();
    Coord a = CoordFactory.get(vertices[A]);
    Coord b = CoordFactory.get(vertices[B]);
    Coord c = CoordFactory.get(vertices[C]);
    result.add(a);
    result.add(b);
    result.add(c);
    return result;
  }

  /**
   * Handles the case where one point is on the level, and the other two points
   * are above and below the level and therefore cross the level at some point.
   * A line is drawn from the point on the level to the crossing point.
   *
   * @param below The coordinate below the contour level.
   * @param on The coordinate on the contour level.
   * @param above The coordinate above the contour level.
   * @param level The double contour level.
   * @return The LineSegment joining the point on the contour with a crossing.
   */
  private LineSegment belowOnAbove(@NonNull Coordinate below,
      @NonNull Coordinate on, @NonNull Coordinate above, double level) {
    Coordinate p = intersect(above, below, level);
    return new LineSegment(on, p);
  }

  /**
   * Handles case C where two points are below the level and the other point is
   * above the level. The line will require two intersections.
   *
   * @param below1 The first point below the contour layer.
   * @param below2 The second point below the contour layer.
   * @param above The point above the contour layer.
   * @param level The double precision contour layer.
   * @return The LineSegment between the crossings.
   */
  private LineSegment twoBelowOneAbove(@NonNull Coordinate below1,
      @NonNull Coordinate below2, @NonNull Coordinate above, double level) {
    // draw a line between two edges.
    Coordinate p1 = intersect(below1, above, level);
    Coordinate p2 = intersect(below2, above, level);
    return new LineSegment(p1, p2);
  }

  /**
   * Handles case f - one below and two above. There are two crossing points,
   * and they fall along the outside.
   *
   * @param below The Coordinate below the contour level.
   * @param above1 The first Coordinate above the contour level.
   * @param above2 The second Coordinate above the contour level.
   * @param level The double precision contour level.
   * @return A LineSegment joining the crossings.
   */
  private LineSegment oneBelowTwoAbove(@NonNull Coordinate below,
      @NonNull Coordinate above1, @NonNull Coordinate above2, double level) {
    // draw a line between two edges.
    Coordinate p1 = intersect(below, above1, level);
    Coordinate p2 = intersect(below, above2, level);
    return new LineSegment(p1, p2);
  }

  /**
   * Gets the point intersection on the segment between a coordinate above the
   * level and below the level that has a Z value equal to level.
   *
   * @param below A point below the level.
   * @param above A point above the level.
   * @param level The double level z value.
   * @return The Coordinate intersection.
   */
  private Coordinate intersect(@NonNull Coordinate below,
      @NonNull Coordinate above, double level) {

    double dx = above.x - below.x;
    double dy = above.y - below.y;
    double dz = above.z - below.z;

    double ratio = (level - below.z) / dz;
    return new Coordinate(below.x + dx * ratio,
        below.y + dy * ratio,
        level);

  }

  /**
   * Gets a z value, or a substitute elevation in the case of a no-data value.
   *
   * @param c The Coordinate, that may have a no-data value for a z value.
   * @return The double Z value.
   */
  private double getZ(Coordinate c) {
    if (c.z == noDataValue) {
      return missingContourLevel;
    }
    return c.z;
  }

  /**
   * Creates a new JTS polygon from this triangle.
   *
   * @return The Polygon to create.
   */
  public final Polygon toPolygon() {

    Coordinate[] coords = new Coordinate[ContourTriangle.NUM_VERTICES + 1];
    for (int i = 0; i < NUM_VERTICES; i++) {
      Coordinate c = vertices[i];
      coords[i] = new Coordinate(c.x, c.y, c.z);
      if (i == 0) {
        coords[NUM_VERTICES] = new Coordinate(c.x, c.y, c.z);
      }
    }

    return GEOMETRY_FACTORY.createPolygon(coords);

  }

  /**
   * Gets the first vertex of the triangle.
   *
   * @return the a Coordinate
   */
  public final Coordinate getA() {
    return vertices[0];
  }

  /**
   * Sets the first vertex of the triangle.
   *
   * @param a the a Coordinate to set
   */
  public final void setA(Coordinate a) {
    vertices[0] = a;
  }

  /**
   * Gets the second vertex of the triangle.
   *
   * @return the b Coordinate
   */
  public final Coordinate getB() {
    return vertices[1];
  }

  /**
   * Sets the second vertex of the triangle.
   *
   * @param b the b Coordinate to set
   */
  public final void setB(Coordinate b) {
    this.vertices[1] = b;
  }

  /**
   * Gets the third vertex of the triangle.
   *
   * @return the c Coordinate
   */
  public final Coordinate getC() {
    return vertices[2];
  }

  /**
   * Sets the third vertex of the triangle.
   *
   * @param c the c Coordinate to set
   */
  public final void setC(Coordinate c) {
    this.vertices[2] = c;
  }

  /**
   * Gets a boolean that is true if all of the vertices z values are on the
   * level.
   *
   * @param level The double level for the contour.
   * @return the flatOnLevel Boolean true if the shapes are flat on the level.
   */
  public final boolean isFlatOnLevel(double level) {
    return vertices[0] != null && vertices[0].z == level
        && vertices[1] != null && vertices[1].z == level
        && vertices[2] != null && vertices[2].z == level;
  }

  /**
   * Gets the z value that represents missing data.
   *
   * @return the noDataValue
   */
  public final double getNoDataValue() {
    return noDataValue;
  }

  /**
   * Sets the z value that represents missing data.
   *
   * @param noDataValue the noDataValue to set
   */
  public final void setNoDataValue(double noDataValue) {
    this.noDataValue = noDataValue;
  }

  /**
   * Gets the double value that should be used if the value is no-data.
   *
   * @return the missingContourLevel
   */
  public final double getMissingContourLevel() {
    return missingContourLevel;
  }

  /**
   * Sets the double value that should be used if the value is no-data.
   *
   * @param missingContourLevel the missingContourLevel to set
   */
  public final void setMissingContourLevel(double missingContourLevel) {
    this.missingContourLevel = missingContourLevel;
  }

}
