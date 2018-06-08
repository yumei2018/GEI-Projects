/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.io.geojson;

import com.google.gson.Gson;
import gov.ca.water.shapelite.utils.ArrayUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GeoGeometry implements Serializable {

  /**
   * The version ID for this serializable object. Increment this if the fields
   * are modified.
   */
  static final long serialVersionUID = 1L;

  /**
   * The geometry type. This can also specify a GeometryCollection, in which
   * case coordinates will not be defined, and geometries should be parsed
   * instead.
   */
  private GeometryType type;

  /**
   * The array of coordinates. The following dimensions are used:
   * <table>
   * <tr><td>ShapeType</td><td>Dim</td><td>Structure</td><td>Note</td></tr>
   * <tr><td>Point</td><td>1</td><td>[x, y]</td><td>Only one position, which is
   * represented as an Array..</td></tr>
   * <tr><td>MultiPoint</td><td>2</td><td>[p1,p2,...,pN]</td><td>Points not
   * connected. Each p is an array like [x,y].</td></tr>
   * <tr><td>LineString</td><td>2</td><td>[p1,p2,...,pN]</td><td>Single
   * connected path. Each p is an array like [x,y].</td></tr>
   * <tr><td>MultiLineString</td><td>3</td>
   * <td>[lineString1,lineString2,...,lineStringN]</td><td>Multiple LineStrings
   * in an array.</td></tr>
   * <tr><td>Polygon</td><td>3</td><td>[shell,hole1,...,holeN]</td><td>Always at
   * least the exterior single shell. Any other rings must be closed (last point
   * = first point) and are holes.</td></tr>
   * <tr><td>MultiPolygon</td><td>4</td><td>[Polygon1,
   * Polygon2]</td><td>Multiple Polygons in an array. See Polygon for it's array
   * structure.</td></tr>
   * </table>
   */
  private ArrayList<?> coordinates;
  /**
   * This is the list of GeoGeometry objects used, but only if the GeometryType
   * is GeometryCollection.
   */
  private List<GeoGeometry> geometries;

  /**
   * GeoGeometry.
   */
  public GeoGeometry() {
    geometries = new ArrayList<>();
  }

  /**
   * Creates a new entry from the specified String format. e.g: {"type":
   * "Polygon", "coordinates" : [[[....]]]}
   *
   * @param geojson The geojson Geometry string.
   */
  public GeoGeometry(String geojson) {
    Gson gson = new Gson();
    GeoGeometry innerGeom = gson.fromJson(geojson, GeoGeometry.class);
    copyProperties(innerGeom);
  }

  /**
   * Gets a copy of this object.
   *
   * @return a deep copy of this object.
   */
  public final GeoGeometry copy() {
    GeoGeometry geom = new GeoGeometry();
    geom.copyProperties(this);
    return geom;
  }

  /**
   * Copies the other geometry and ensures that it is a deep copy so that none
   * of the original pointers are used.
   *
   * @param other The GeoGeometry to copy from.
   */
  public final void copyProperties(GeoGeometry other) {
    this.type = other.type;
    this.coordinates = ArrayUtils.deepCopy(other.coordinates);
    if (other.geometries == null) {
      this.geometries = null;
    } else {
      this.geometries = new ArrayList<>();
      for (GeoGeometry geom : other.getGeometries()) {
        this.geometries.add(geom.copy());
      }
    }

  }

  /**
   * Determines the dimension of the coordinate array list. That is, how many
   * arraylists need to be
   *
   * @return the integer dimension of the coordinate array list.
   */
  public final Dimension getDimension() {
    if (coordinates == null || coordinates.isEmpty()) {
      return Dimension.None;
    }
    if (coordinates.get(0) instanceof Double) {
      return Dimension.One; // Point
    }
    if (coordinates.get(0) instanceof ArrayList<?>) {
      ArrayList<?> l1 = (ArrayList<?>) coordinates.get(0);
      if (l1.get(0) instanceof Double) {
        return Dimension.Two; // LineString, MultiPoint
      }
      if (l1.get(0) instanceof ArrayList<?>) {
        ArrayList<?> l2 = (ArrayList<?>) l1.get(0);
        if (l2.get(0) instanceof Double) {
          return Dimension.Three; // MultiLineString, Polygon
        }
        if (l2.get(0) instanceof ArrayList<?>) {
          ArrayList<?> l3 = (ArrayList<?>) l2.get(0);
          if (l3.get(0) instanceof Double) {
            return Dimension.Four; // MultiPolygon
          }
        }
      }
    }
    return Dimension.None;
  }

  /**
   * Gets the GeometryType associated with this feature.
   *
   * @return the type
   */
  public final GeometryType getType() {
    return type;
  }

  /**
   * Sets the GeometryType associated with this feature.
   *
   * @param type the type to set
   */
  public final void setType(GeometryType type) {
    this.type = type;
  }

  /**
   * Gets an array list that is either an arraylist of doubles, or else an array
   * list that contains array lists of some depth, which terminates in doubles.
   * <ul><li>Point - ArrayList&lt;Double&gt;</li><li>LineString, MultiPoint -
   * ArrayList&lt;ArrayList&lt;Double&gt;&gt;</li><li>MultiLineString, Polygon -
   * ArrayList&lt;ArrayList&lt;ArrayList&lt;Double&gt;&gt;&gt;</li><li>MultiPolygon
   * -
   * ArrayList&lt;ArrayList&lt;ArrayList&lt;ArrayList&lt;Double&gt;&gt;&gt;&gt;</li></ul>
   *
   * @return the coordinates
   */
  public final ArrayList<?> getCoordinates() {
    return coordinates;
  }

  /**
   * Sets an array list that is either an arraylist of doubles, or else an array
   * list that contains array lists of some depth, which terminates in doubles.
   * <ul><li>Point - ArrayList&lt;Double&gt;</li><li>LineString, MultiPoint -
   * ArrayList&lt;ArrayList&lt;Double&gt;&gt;</li><li>MultiLineString, Polygon -
   * ArrayList&lt;ArrayList&lt;ArrayList&lt;Double&gt;&gt;&gt;</li><li>MultiPolygon
   * -
   * ArrayList&lt;ArrayList&lt;ArrayList&lt;ArrayList&lt;Double&gt;&gt;&gt;&gt;</li></ul>
   *
   * @param coordinates the coordinates to set
   */
  public final void setCoordinates(ArrayList<?> coordinates) {
    this.coordinates = coordinates;
  }

  /**
   * Given a string of coordinates this will attempt to parse the object version
   * of the coordinates from the array.
   *
   * @param stringArray The String array.
   */
  public final void setCoordinates(String stringArray) {
    Gson gson = new Gson();
    this.coordinates = gson.fromJson(stringArray, ArrayList.class);
  }

  /**
   * Creates a string array from the arraylist coordinates on this class.
   *
   * @return The coordinate string.
   */
  public final String getCoordinateString() {
    Gson gson = new Gson();
    return gson.toJson(coordinates);
  }

  /**
   * Gets the double array of coordinates in the case of point geometries. If
   * the dimension is not equal to 1, this will throw an error.
   *
   * @return The double array.
   */
  public final double[] getPointCoordinates() {
    if (this.getDimension() != Dimension.One) {
      throw new IllegalStateException("Point coordinates can only requested "
          + "if the underlying arraylist is one dimensional.");
    }
    Object arrays = toDoubleArrays(coordinates);
    if (arrays instanceof double[]) {
      return (double[]) arrays;
    }
    throw new IllegalStateException("An unexpected type was returned for the "
        + "point arrays.");
  }

  /**
   * Sets the double array of coordinates in the case of point geometries.
   *
   * @param coord The X and Y or X,Y and Z values in a double array.
   */
  public final void setPointCoordinates(double[] coord) {
    this.coordinates = ArrayUtils.toList(coord);
  }

  /**
   * Gets the two dimensional double array of coordinates in the case of line
   * geometries or MultiPoint geometries.
   *
   * @return The two dimensional double array.
   */
  public final double[][] getLineStringCoordinates() {
    if (this.getDimension() != Dimension.Two) {
      throw new IllegalStateException("LineString or MultiPoint coordinates "
          + "can only requested if the underlying arraylist is two dimensional.");
    }
    Object arrays = toDoubleArrays(coordinates);
    if (arrays instanceof double[][]) {
      return (double[][]) arrays;
    }
    throw new IllegalStateException("An unexpected type was returned for the "
        + "coordinate arrays.");
  }

  /**
   * Sets the array of coordinates as the points in a line.
   *
   * @param coords The jagged array of double values, with each inner array
   * representing a single point.
   */
  public final void setLineStringCoordinates(double[][] coords) {
    coordinates = ArrayUtils.toList(coords);
  }

  /**
   * Gets the two dimensional double array of coordinates in the case of line
   * geometries or MultiPoint geometries.
   *
   * @return The two dimensional double array.
   */
  public final double[][] getMultiPointCoordinates() {
    return getLineStringCoordinates();
  }

  /**
   * Sets the array of coordinates as the set of disconnected points.
   *
   * @param coords The jagged array of double values, with each inner array
   * representing a single point.
   */
  public final void setMultiPointCoordinates(double[][] coords) {
    coordinates = ArrayUtils.toList(coords);
  }

  /**
   * Gets the three dimensional double array of coordinates in the case of
   * MultiLine geometries or Polygon geometries.
   *
   * @return The three dimensional double array.
   */
  public final double[][][] getMultiLineStringCoordinates() {
    if (this.getDimension() != Dimension.Three) {
      throw new IllegalStateException("MultiLineString or Polygon coordinates "
          + "can only requested if the underlying arraylist is one dimensional.");
    }
    Object arrays = toDoubleArrays(coordinates);
    if (arrays instanceof double[][][]) {
      return (double[][][]) arrays;
    }
    throw new IllegalStateException("An unexpected type was returned for the "
        + "coordinate arrays.");
  }

  /**
   * Sets the array of coordinates, where each array in the outer array is an
   * array of coordinates defining a LineString.
   *
   * @param coords The jagged array of double values, with each inner array
   * representing a single point.
   */
  public final void setMultiLineStringCoordinates(double[][][] coords) {
    coordinates = ArrayUtils.toList(coords);
  }

  /**
   * Gets the three dimensional double array of coordinates in the case of
   * MultiLine geometries or Polygon geometries.
   *
   * @return The three dimensional double array.
   */
  public final double[][][] getPolygonCoordinates() {
    return getMultiLineStringCoordinates();
  }

  /**
   * Sets the array of coordinates, where the first array in the outer array is
   * an array of coordinates defines the polygon shell, and the other arrays
   * define holes within the shell.
   *
   * @param coords The jagged array of double values, with each inner array
   * representing a single point.
   */
  public final void setPolygonCoordinates(double[][][] coords) {
    coordinates = ArrayUtils.toList(coords);
  }

  /**
   * Gets the three dimensional double array of coordinates in the case of
   * MultiLine geometries or Polygon geometries.
   *
   * @return The three dimensional double array.
   */
  public final double[][][][] getMultiPolygonCoordinates() {
    if (this.getDimension() != Dimension.Four) {
      throw new IllegalStateException("MultiPolygon coordinates "
          + "can only requested if the underlying arraylist is four dimensional.");
    }
    Object arrays = toDoubleArrays(coordinates);
    if (arrays instanceof double[][][][]) {
      return (double[][][][]) arrays;
    }
    throw new IllegalStateException("An unexpected type was returned for the "
        + "coordinate arrays.");
  }

  /**
   * Sets the array of coordinates, where the outer array is an array of
   * polygons, and in each polygon, the first array in the outer array is an
   * array of coordinates defines the polygon shell, and the other arrays define
   * holes within the shell.
   *
   * @param coords The jagged array of double values, with each inner array
   * representing a single point.
   */
  public final void setMultiPolygonCoordinates(double[][][][] coords) {
    coordinates = ArrayUtils.toList(coords);
  }

  /**
   * Converts the variable depth ArrayList to a double array.
   *
   * @param list The list of coordinates, which may be a multi-dimensional list.
   * @return an array of various depth. It will be depth 0 if there were no
   * coordinates, or the list was null.
   */
  public final Object toDoubleArrays(ArrayList<?> list) {
    Dimension dimension = this.getDimension();
    switch (dimension) {
      case One:
        return ArrayUtils.toArray(list);
      case Two:
        return ArrayUtils.toArray2(list);
      case Three:
        return ArrayUtils.toArray3(list);
      case Four:
        return ArrayUtils.toArray4(list);
      default:
        return new double[0];
    }
  }

  /**
   * Gets the geometries if this geometry is a multiple geometry.
   *
   * @return the geometries
   */
  public final List<GeoGeometry> getGeometries() {
    return geometries;
  }

  /**
   * Sets the geometries if this geometry is a multiple geometry.
   *
   * @param geometries the geometries to set
   */
  public final void setGeometries(List<GeoGeometry> geometries) {
    this.geometries = geometries;
  }

  /**
   * Gets the geojson string representation of this object.
   */
  @Override
  public final String toString() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}
