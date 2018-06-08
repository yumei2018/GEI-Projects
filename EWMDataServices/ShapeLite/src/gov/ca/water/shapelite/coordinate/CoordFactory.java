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
package gov.ca.water.shapelite.coordinate;

import com.vividsolutions.jts.geom.Coordinate;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.NonNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class CoordFactory {
  /**
   * Creates a list of coordinates from two lists of doubles.
   *
   * The two list arguments must not be nulls and have the same size.
   * @param xPoints   a list of x points
   * @param yPoints   a list of y points
   * @return a list of XY Coords.
   * @throws NullPointerException if xPoints or yPoints is null
   * @throws IllegalArgumentException if two lists are not the same size.
   */
  public static List<CoordXY> fromXYArrays(List<Double> xPoints, List<Double> yPoints) {
    if (xPoints == null) {
      throw new NullPointerException("xPoints");
    }
    if (yPoints == null) {
      throw new NullPointerException("yPoints");
    }
    if (xPoints.size() != yPoints.size()) {
      throw new IllegalArgumentException("size of xPoints != size of yPoints");
    }
    List<CoordXY> coords  = new ArrayList<>();
    for (int i = 0; i < xPoints.size(); i++) {
      CoordXY coordXY = new CoordXY(
              xPoints.get(i),
              yPoints.get(i)
      );
      coords.add(coordXY);
    }
    return coords;
  }


  /**
   * 360 degrees of longitude.
   */
  private static final double LONGITUDE_SPAN = 360;
  /**
   * The maximum longitude value.
   */
  private static final double MAX_LONGITUDE = 180;
  /**
   * 180 degrees of total latitude.
   */
  private static final double LATITUDE_SPAN = 180;
  /**
   * The maximum latitude in degrees.
   */
  private static final double MAX_LATITUDE = 90;

  /**
   * Hide constructor.
   */
  private CoordFactory() {

  }

  /**
   * This array creation is designed only to handle XY or XYZ coordinates. It is
   * not designed to consider M coordinates.
   *
   * @param array The Array of X, Y or X,Y,Z values.
   * @return Either A CoordXY or A CoordZ.
   */
  public static Coord get(double[] array) {

    if (array.length > 2) {
      return new CoordZ(array[0], array[1], array[2]);
    }
    CoordXY result = new CoordXY();
    if (array.length > 0) {
      result.setX(array[0]);
    }
    if (array.length > 1) {
      result.setY(array[1]);
    }
    return result;
  }

  /**
   * Gets a Coord representation from the vertex, based on the hasM or hasZ
   * values.
   *
   * @param vertex The double array of ordinate values defining the coordinate.
   * @param hasM Boolean, true if the array includes an M value.
   * @param hasZ Boolean, true if the array includes a Z value.
   * @return A Coord object, which can be a CoordXY, CoordM or CoordZ depending
   * on the values specified for hasM and hasZ.
   */
  public static Coord getCoord(double[] vertex, boolean hasM, boolean hasZ) {
    double x = 0;
    double y = 0;
    double z = 0;
    double m = 0;
    Coord coord;
    if (vertex.length > 0 && !Double.isNaN(vertex[0])) {
      x = vertex[0];
    }
    if (vertex.length > 1 && !Double.isNaN(vertex[1])) {
      y = vertex[1];
    }
    if (hasM && !hasZ) {
      if (vertex.length > 2 && !Double.isNaN(vertex[2])) {
        m = vertex[2];
      }
      coord = new CoordM(x, y, m);
    } else if (hasZ) {
      if (vertex.length > 2 && !Double.isNaN(vertex[2])) {
        z = vertex[2];
      }
      if (hasM) {
        if (vertex.length > 3 && !Double.isNaN(vertex[3])) {
          m = vertex[3];
        }
      }
      coord = new CoordZ(x, y, z, m);
    } else {
      coord = new CoordXY(x, y);
    }
    return coord;
  }

  /**
   * Gets a coordinate from this array list representation of double values.
   *
   * @param coordValues The list of doubles to convert into a Coordinate.
   * @return A CoordXY or a CoordZ if a third value is present.
   */
  public static Coord get(List<Double> coordValues) {
    if (coordValues.size() > 2) {
      return new CoordZ(coordValues.get(0), coordValues.get(1), coordValues.get(2));
    }
    CoordXY result = new CoordXY();
    if (coordValues.size() > 0) {
      result.setX(coordValues.get(0));
    }
    if (coordValues.size() > 1) {
      result.setY(coordValues.get(1));
    }
    return result;
  }

  /**
   * Given an original coordinate that might have a NaN Z value, this will
   * create either a CoordXY or a CoordZ.
   *
   * @param original The original coordinate.
   * @return A CoordXY or a CoordZ, depending on the value of original.z
   */
  public static Coord get(Coordinate original) {
    if (Double.isNaN(original.z)) {
      return new CoordXY(original.x, original.y);
    } else {
      return new CoordZ(original.x, original.y, original.z);
    }
  }

  /**
   * Gets a fleshed out coordinate from the original. If the original cannot be
   * cast properly, it will be copied instead.
   *
   * @param original The original.
   * @return A Coord that is a CoordXY, CoordM, or CoordZ. If possible, this
   * simply upcasts the original. Otherwise, this will create a copy with the
   * same values.
   */
  public static Coord get(@NonNull CoordValues original) {
    if (original instanceof Coord) {
      return (Coord) original;
    }
    if (original.has(Coord.Index.Z)) {
      return new CoordZ(original);
    }
    if (original.has(Coord.Index.M)) {
      return new CoordM(original);
    }
    return new CoordXY(original);
  }

  /**
   * Copies the original coordinate to create a CoordXY, CoordM or CoordZ.
   *
   * @param original The original coordinate to copy.
   * @return The Coord.
   */
  public static Coord copy(@NonNull Coord original) {
    if (original.hasZ()) {
      return new CoordZ(original);
    }
    if (original.hasM()) {
      return new CoordM(original);
    }
    return new CoordXY(original);
  }

  /**
   * This ensures a CoordZ return type. If the copy method on the original
   * CoordZ is a CoordZ, then you will get the result upcast to a CoordZ.
   * Otherwise (in the case of an improperly implemented Copy method in some
   * kind of unexpected superclass), you will get a new CoordZ created from the
   * original.
   *
   * @param original The original.
   * @return A Copy of the CoordZ.
   */
  public static CoordZ copy(@NonNull CoordZ original) {
    Coord copy = original.copy();
    if (original instanceof CoordZ) {
      return (CoordZ) copy;
    }
    return new CoordZ(copy);
  }

  /**
   * This ensures a CoordM return type. If the copy method on the original
   * CoordM is a CoordM, then you will get the result upcast to a CoordM.
   * Otherwise (in the case of an improperly implemented Copy method in some
   * kind of unexpected superclass), you will get a new CoordM created from the
   * original.
   *
   * @param original The original.
   * @return A Copy of the CoordM.
   */
  public static CoordM copy(@NonNull CoordM original) {
    Coord copy = original.copy();
    if (original instanceof CoordM) {
      return (CoordM) copy;
    }
    return new CoordM(copy);
  }

  /**
   * This ensures a CoordXY return type. If the copy method on the original
   * CoordXY is a CoordXY, then you will get the result upcast to a CoordXY.
   * Otherwise (in the case of an improperly implemented Copy method in some
   * kind of unexpected superclass), you will get a new CoordXY created from the
   * original.
   *
   * @param original The original.
   * @return A Copy of the CoordXY.
   */
  public static CoordXY copy(@NonNull CoordXY original) {
    Coord copy = original.copy();
    if (original instanceof CoordXY) {
      return (CoordXY) copy;
    }
    return new CoordXY(copy);
  }





  /**
   * Vector addition of coord1 and coord2.
   * @param coord1
   * @param coord2
   * @return
   */
  public static CoordXY add2D(@NonNull CoordXY coord1, @NonNull CoordXY coord2) {
    CoordXY result = new CoordXY(coord1.getX() + coord2.getX(), coord1.getY() + coord1.getY());
    return result;
  }



  /**
   * Generates a random CoordXY. Number values will have WGS84 bounds, -180 to
   * 180 for X. -90 to 90 for Y.
   *
   * @return CoordXY with random X and Y set as latitude, longitude.
   */
  public static CoordXY randomXY() {
    Random rnd = new Random();
    return new CoordXY(rnd.nextDouble() * LONGITUDE_SPAN - MAX_LONGITUDE,
        rnd.nextDouble() * LATITUDE_SPAN - MAX_LATITUDE);
  }

  /**
   * Generates a list of random XY values. Number values will have WGS84 bounds,
   * -180 to 180 for X. -90 to 90 for Y.
   *
   * @param count The integer number of random coordinates to create.
   * @return A List of CoordXY objects.
   */
  public static List<CoordXY> randomXY(int count) {
    List<CoordXY> result = new ArrayList<>();
    Random rnd = new Random();
    for (int i = 0; i < count; i++) {
      result.add(new CoordXY(rnd.nextDouble() * LONGITUDE_SPAN - MAX_LONGITUDE,
          rnd.nextDouble() * LATITUDE_SPAN - MAX_LATITUDE));
    }
    return result;
  }

  /**
   * Generates a list of random XY values. Number values will have WGS84 bounds,
   * -180 to 180 for X. -90 to 90 for Y.
   *
   * @param count The integer number of random coordinates to create.
   * @param seed The random seed.
   * @return A List of CoordXY objects.
   */
  public static List<CoordXY> randomXY(int count, long seed) {
    List<CoordXY> result = new ArrayList<>();
    Random rnd = new Random(seed);
    for (int i = 0; i < count; i++) {
      result.add(new CoordXY(rnd.nextDouble() * LONGITUDE_SPAN - MAX_LONGITUDE,
          rnd.nextDouble() * LATITUDE_SPAN - MAX_LATITUDE));
    }
    return result;
  }

  /**
   * Generates a list of random X,Y,M values. Number values will have WGS84
   * bounds, -180 to 180 for X. -90 to 90 for Y.
   *
   * @param count The integer number of random coordinates to create.
   * @return A List of CoordXY objects.
   */
  public static List<CoordM> randomM(int count) {
    List<CoordM> result = new ArrayList<>();
    Random rnd = new Random();
    for (int i = 0; i < count; i++) {
      result.add(new CoordM(rnd.nextDouble() * LONGITUDE_SPAN - MAX_LONGITUDE,
          rnd.nextDouble() * LATITUDE_SPAN - MAX_LATITUDE, rnd.nextDouble()));
    }
    return result;
  }

  /**
   * Generates a list of random X,Y,Z values. Number values will have WGS84
   * bounds, -180 to 180 for X. -90 to 90 for Y.
   *
   * @param count The integer number of random coordinates to create.
   * @return A List of CoordXY objects.
   */
  public static List<CoordZ> randomZ(int count) {
    List<CoordZ> result = new ArrayList<>();
    Random rnd = new Random();
    for (int i = 0; i < count; i++) {
      result.add(new CoordZ(rnd.nextDouble() * LONGITUDE_SPAN - MAX_LONGITUDE,
          rnd.nextDouble() * LATITUDE_SPAN - MAX_LATITUDE, rnd.nextDouble()));
    }
    return result;
  }

  /**
   * This generates points that are effectively a closed form polygon, where the
   * angle about a central point is fixed for each point, but the radius is
   * random.
   *
   * @param center The central point about which the other points are created.
   * @param numPoints the number of points.
   * @param maxRadius the maximum radius.
   * @return the new list of CoordXY shapes.
   */
  public static List<CoordXY> randomRadiusXY(Coord center, int numPoints,
      double maxRadius) {
    double da = (Math.PI * 2) / numPoints;
    double a = 0;
    Random rnd = new Random();
    List<CoordXY> result = new ArrayList<>();
    for (int i = 0; i < numPoints; i++) {
      double r = rnd.nextDouble() * maxRadius;
      a += da;
      double dx = Math.sin(a) * r;
      double dy = Math.cos(a) * r;
      CoordXY coord = new CoordXY(center.getX() + dx, center.getY() + dy);
      result.add(coord);
    }
    return result;
  }

  /**
   * This generates points that are effectively a closed form polygon, where the
   * angle about a central point is fixed for each point, but the radius is
   * random.
   *
   * @param center The central point about which the other points are created.
   * @param numPoints the number of points.
   * @param maxRadius the maximum radius.
   * @return the new list of CoordXY shapes.
   */
  public static List<CoordM> randomRadiusM(Coord center, int numPoints,
      double maxRadius) {
    double da = (Math.PI * 2) / numPoints;
    double a = 0;
    Random rnd = new Random();
    List<CoordM> result = new ArrayList<>();
    for (int i = 0; i < numPoints; i++) {
      double r = rnd.nextDouble() * maxRadius;
      a += da;
      double dx = Math.sin(a) * r;
      double dy = Math.cos(a) * r;
      CoordM coord = new CoordM(center.getX() + dx, center.getY() + dy,
          rnd.nextDouble() * 10);
      result.add(coord);
    }
    return result;
  }

  /**
   * This generates points that are effectively a closed form polygon, where the
   * angle about a central point is fixed for each point, but the radius is
   * random.
   *
   * @param center The central point about which the other points are created.
   * @param numPoints the number of points.
   * @param maxRadius the maximum radius.
   * @return the new list of CoordXY shapes.
   */
  public static List<CoordZ> randomRadiusZ(Coord center, int numPoints, double maxRadius) {
    double da = (Math.PI * 2) / numPoints;
    double a = 0;
    Random rnd = new Random();
    List<CoordZ> result = new ArrayList<>();
    for (int i = 0; i < numPoints; i++) {
      double r = rnd.nextDouble() * maxRadius;
      a += da;
      double dx = Math.sin(a) * r;
      double dy = Math.cos(a) * r;
      CoordZ coord = new CoordZ(center.getX() + dx, center.getY() + dy,
          100 * rnd.nextDouble());
      result.add(coord);
    }
    return result;
  }

}
