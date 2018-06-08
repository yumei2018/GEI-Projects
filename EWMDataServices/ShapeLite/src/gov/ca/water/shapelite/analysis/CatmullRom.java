/*
 * The MIT License
 *
 * Copyright 2013 hdunsford.
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

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import java.util.ArrayList;
import java.util.List;

/**
 * Catmull-Rom spline interpolation does not use external control points, but
 * rather derives all of its control points from the points in the actual
 * linestring. However, it needs four points in order to interpolate the region
 * between the middle two points, which means you have to add two more points to
 * each end. For a closed shape, the two extra points are just points 2 and
 * points n-1, but added to the opposite ends. So p2 gets added to the end of
 * the list, while point n-1 gets added to the beginning of the list.
 * http://www.mvps.org/directx/articles/catmull/
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class CatmullRom {

  /**
   * When doing a spline, each link uses 4 points.
   */
  private static final int NUM_POINTS = 4;

  /**
   * Minimum number of points to allow before attempting to interpolate.
   */
  private static final int MIN_SIZE = 3;

  /**
   * The power coefficient for centripital curves.
   */
  private static final double CENTRIPITAL_POWER = .25;

  /**
   * The power coefficient for chordal curves.
   */
  private static final double CHORDAL_POWER = .5;

  /**
   * The integer index for the Z coordinate.
   */
  private static final int Z = Coord.Index.Z;


  /**
   * Hidden constructor.
   */
  private CatmullRom() {

  }

  /**
   * This calculates the CatmullRom interpolated shape that is derived from the
   * straight line shape specified as linearShape.
   *
   * @param linearShape the polyline or polygon to throw
   * @param pointsPerSegment The number of interpolated points for each segment.
   * This number must be greater than 2.
   * @param curveType Chordal (stiff), Uniform(floppy), or Centripetal(medium)
   * @return The Shape containing the curve coordinates for the specified point.
   * @throws CatmullRomException if the pointsPerSegment is not correct, or if
   * the shapetype is incorrect.
   */
  public static Shape interpolate(Shape linearShape, int pointsPerSegment,
          CatmullRomType curveType) throws CatmullRomException {
    // Test the shape to ensure that it is a polyline or polygon.
    // Z or M values are ok.
    if (linearShape.getShapeType() != ShapeType.PolyLine
            && linearShape.getShapeType() != ShapeType.PolyLineM
            && linearShape.getShapeType() != ShapeType.PolyLineZ
            && linearShape.getShapeType() != ShapeType.Polygon
            && linearShape.getShapeType() != ShapeType.PolygonM
            && linearShape.getShapeType() != ShapeType.PolygonZ) {
      throw new CatmullRomException(
              "This can only be run on PolyLine or Polygon shapes");
    }

    // Dimension the output shape that will hold the curve.
    Shape resultShape = new Shape(linearShape.getShapeType());

    // Shapes can have multiple parts.  Each part should be treated as
    // an independant calculation.
    for (Part part : linearShape.getParts()) {
      // Each part has a list of coordinates, which define the raw
      // points to work with.
      List<Coord> coords = part.getCoordinates();
      // Create a corresponding output part based on the Catmull-Rom
      // interpolated points.
      Part resultPart = new Part(interpolate(coords, pointsPerSegment,
              curveType));
      // Add the result part to the output shape.
      resultShape.getParts().add(resultPart);
    }
    // Shapes have cached envelopes, so update the envelope based on the
    // current points.
    resultShape.calculateBounds();

    return resultShape;

  }

  /**
   * This method will calculate the Catmull-Rom interpolation curve, returning
   * it as a list of Coord coordinate objects.
   *
   * @param coordinates The list of original straight line points to calculate
   * an interpolation from.
   * @param pointsPerSegment The integer number of equally spaced points to
   * inter interpolated along each curve. The actual distance between each point
   * will depend on the spacing between the control points.
   * @return The list of interpolated coordinates.
   * @param curveType Chordal (stiff), Uniform(floppy), or Centripetal(medium)
   * @throws gov.ca.water.shapelite.analysis.CatmullRomException if
   * pointsPerSegment is less than 2.
   */
  public static List<Coord> interpolate(List<Coord> coordinates,
          int pointsPerSegment, CatmullRomType curveType)
          throws CatmullRomException {
    List<Coord> vertices = new ArrayList<>();
    for (Coord c : coordinates) {
      vertices.add(c.copy());
    }
    if (pointsPerSegment < 2) {
      throw new CatmullRomException("The pointsPerSegment parameter must"
              + " be greater than 2, since 2 points is just the"
              + " linear segment.");
    }

    // Cannot interpolate curves given only two points.  Two points
    // is best represented as a simple line segment.
    if (vertices.size() < MIN_SIZE) {
      return vertices;
    }

    // Test whether the shape is open or closed by checking to see if
    // the first point intersects with the last point.  M and Z are ignored.
    boolean isClosed = vertices.get(0).intersects2D(
            vertices.get(vertices.size() - 1));
    if (isClosed) {
      // Use the second and second from last points as control points.
      // get the second point.
      Coord p2 = vertices.get(1).copy();
      // get the point before the last point
      Coord pn1 = vertices.get(vertices.size() - 2).copy();

      // insert the second from the last point as the first point in the
      // list because when the shape is closed it keeps wrapping around to
      // the second point.
      vertices.add(0, pn1);
      // add the second point to the end.
      vertices.add(p2);
    } else {
            // The shape is open, so use control points that simply extend
      // the first and last segments

      // Get the change in x and y between the first and second
      // coordinates.
      double dx = vertices.get(1).getX() - vertices.get(0).getX();
      double dy = vertices.get(1).getY() - vertices.get(0).getY();

      // Then using the change, extrapolate backwards to find a control
      // point.
      double x1 = vertices.get(0).getX() - dx;
      double y1 = vertices.get(0).getY() - dy;

      // Actaully create the start point from the extrapolated values.
      Coord start = new CoordZ(x1, y1, vertices.get(0).get(Z));

      // Repeat for the end control point.
      int n = vertices.size() - 1;
      dx = vertices.get(n).getX() - vertices.get(n - 1).getX();
      dy = vertices.get(n).getY() - vertices.get(n - 1).getY();
      double xn = vertices.get(n).getX() + dx;
      double yn = vertices.get(n).getY() + dy;
      Coord end = new CoordXY(xn, yn);

      // insert the start control point at the start of the vertices list.
      vertices.add(0, start);

      // append the end control ponit to the end of the vertices list.
      vertices.add(end);
    }

    // Dimension a result list of coordinates.
    List<Coord> result = new ArrayList<>();
    // When looping, remember that each cycle requires 4 points, starting
    // with i and ending with i+3.  So we don't loop through all the points.
    for (int i = 0; i < vertices.size() - 3; i++) {

      // Actually calculate the Catmull-Rom curve for one segment.
      List<Coord> points = interpolate(vertices, i, pointsPerSegment,
              curveType);
      // Since the middle points are added twice, once for each bordering
      // segment, we only add the 0 index result point for the first
      // segment.  Otherwise we will have duplicate points.
      if (result.size() > 0) {
        points.remove(0);
      }

      // Add the coordinates for the segment to the result list.
      result.addAll(points);
    }
    return result;

  }

  /**
   * Given a list of points, this will create a uniform series of points spaced
   * dt apart on the curve where dt ranges from 0 to 1.
   *
   * @param points The list of control points.
   * @param index The index of control point p0, where p0, p1, p2, and p3 are
   * used in order to create a curve between p1 and p2.
   * @param pointsPerSegment The total number of uniformly spaced interpolated
   * points to calculate for each segment. The larger this number, the smoother
   * the resulting curve.
   * @param curveType Clarifies whether the curve should use uniform, chordal or
   * centripetal curve types. Uniform can produce loops, chordal can produce
   * large distortions from the original lines, and centripetal is an optimal
   * balance without spaces.
   * @return the List of Coordinates.
   */
  public static List<Coord> interpolate(List<Coord> points, int index,
          int pointsPerSegment, CatmullRomType curveType) {
    List<Coord> result = new ArrayList<>();
    double[] x = new double[NUM_POINTS];
    double[] y = new double[NUM_POINTS];
    double[] time = new double[NUM_POINTS];
    for (int i = 0; i < NUM_POINTS; i++) {
      x[i] = points.get(index + i).getX();
      y[i] = points.get(index + i).getY();
      time[i] = i;
    }

    double tstart = 1;
    double tend = 2;
    if (!curveType.equals(CatmullRomType.Uniform)) {
      double total = 0;
      for (int i = 1; i < NUM_POINTS; i++) {
        double dx = x[i] - x[i - 1];
        double dy = y[i] - y[i - 1];
        if (curveType.equals(CatmullRomType.Centripetal)) {
          total += Math.pow(dx * dx + dy * dy, CENTRIPITAL_POWER);
        } else {
          total += Math.pow(dx * dx + dy * dy, CHORDAL_POWER);
        }
        time[i] = total;
      }
      tstart = time[1];
      tend = time[2];
    }
    double z1 = 0.0;
    double z2 = 0.0;
    if (!Double.isNaN(points.get(index + 1).get(Z))) {
      z1 = points.get(index + 1).get(Z);
    }
    if (!Double.isNaN(points.get(index + 2).get(Z))) {
      z2 = points.get(index + 2).get(Z);
    }
    double dz = z2 - z1;
    int segments = pointsPerSegment - 1;
    result.add(points.get(index + 1));
    for (int i = 1; i < segments; i++) {
      double xi = interpolate(x, time, tstart + (i * (tend - tstart))
              / segments);
      double yi = interpolate(y, time, tstart + (i * (tend - tstart))
              / segments);
      double zi = z1 + (dz * i) / segments;
      result.add(new CoordZ(xi, yi, zi));
    }
    result.add(points.get(index + 2));
    return result;
  }

  /**
   * Unlike the other implementation here, which uses the default "uniform"
   * treatment of t, this computation is used to calculate the same values but
   * introduces the ability to "parameterize" the t values used in the
   * calculation. This is based on Figure 3 from
   * http://www.cemyuksel.com/research/catmullrom_param/catmullrom.pdf
   *
   * @param p An array of double values of length 4, where interpolation occurs
   * from p1 to p2.
   * @param time An array of time measures of length 4, corresponding to each p
   * value.
   * @param t the actual interpolation ratio from 0 to 1 representing the
   * position between p1 and p2 to interpolate the value.
   * @return The double interpolated scalar value.
   */
  public static double interpolate(double[] p, double[] time, double t) {

    double l01 = p[0] * (time[1] - t) / (time[1] - time[0]) + p[1]
            * (t - time[0]) / (time[1] - time[0]);
    double l12 = p[1] * (time[2] - t) / (time[2] - time[1]) + p[2]
            * (t - time[1]) / (time[2] - time[1]);
    double l23 = p[2] * (time[3] - t) / (time[3] - time[2]) + p[3]
            * (t - time[2]) / (time[3] - time[2]);
    double l012 = l01 * (time[2] - t) / (time[2] - time[0]) + l12
            * (t - time[0]) / (time[2] - time[0]);
    double l123 = l12 * (time[3] - t) / (time[3] - time[1]) + l23
            * (t - time[1]) / (time[3] - time[1]);
    double c12 = l012 * (time[2] - t) / (time[2] - time[1]) + l123
            * (t - time[1]) / (time[2] - time[1]);
    return c12;
  }

}
