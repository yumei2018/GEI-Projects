/*
 * The MIT License
 *
 * Copyright 2017 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.utils;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.precision.GeometryPrecisionReducer;
import gov.ca.water.shapelite.NonNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A Utils class to help with some JTS related methods.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class JTSUtils {

  /**
   * The Private constructor for a utilities class.
   */
  private JTSUtils() {

  }

  /**
   * Repairs potential issues in geometries from invalid lines.
   *
   * @param potentiallyInvalid The potentially invalid.
   * @return The Original geometry or else a buffered Polygon if the original
   * was a Polygon or multipolygon potentially in error.
   */
  public static Geometry fix(Geometry potentiallyInvalid) {

    if (potentiallyInvalid == null) {
      return null;
    }
    if (potentiallyInvalid.isValid()) {
      return potentiallyInvalid;
    }
    if (potentiallyInvalid instanceof Polygon) {
      Polygon p = (Polygon) potentiallyInvalid;
      return fixPolygon(p);
    }
    if (potentiallyInvalid instanceof MultiPolygon) {
      List<Polygon> multiPolygons = new ArrayList<>();
      for (int i = 0; i < potentiallyInvalid.getNumGeometries(); i++) {
        Geometry geom = potentiallyInvalid.getGeometryN(i);
        if (geom instanceof Polygon) {
          Polygon p = (Polygon) geom;
          Polygon fixed = fixPolygon(p);
          multiPolygons.add(fixed);
        }
      }
      GeometryFactory factory = new GeometryFactory();
      MultiPolygon pg = factory.createMultiPolygon(multiPolygons.toArray(new Polygon[0]));
      return pg;
    }
    return potentiallyInvalid;

  }

  /**
   * Fixes the polygon in cases where too much precision has been given and some
   * line crossing occurs.
   *
   * @param original The original.
   * @return The Polygon that should not throw exceptions.
   */
  public static Polygon fixPolygon(Polygon original) {
    if (original.isValid()) {
      return original;
    }
    GeometryFactory factory = new GeometryFactory();
    Geometry bounds = original.buffer(0);
    List<LinearRing> linearRings = new ArrayList<>();
    for (int iHole = 0; iHole < original.getNumInteriorRing(); iHole++) {
      Coordinate[] coords = original.getInteriorRingN(iHole).getCoordinates();
      coords = close(coords);
      Polygon hole = factory.createPolygon(coords);
      if (!hole.isValid()) {
        Geometry bufferedHole = hole.buffer(0);
        Coordinate[] bufCoords = bufferedHole.getCoordinates();
        bufCoords = close(bufCoords);
        LinearRing ring = factory.createLinearRing(bufCoords);
        linearRings.add(ring);
      }
    }
    Coordinate[] boundCoords = bounds.getCoordinates();
    boundCoords = close(boundCoords);
    LinearRing shell = factory.createLinearRing(boundCoords);
    LinearRing[] holes = linearRings.toArray(new LinearRing[0]);
    Polygon fixed = factory.createPolygon(shell, holes);
    return fixed;
  }

  /**
   * In a case where the last coordinate should be the same as the first, this
   * simply ensures closure by increasing the return array size by one and
   * appending the first coordinate to the end. If the array is already closed
   * this simply returns the original array.
   *
   * @param coordinates The coordinate array to close.
   * @return The closed coordinate array. If the original array was closed, then
   * the original array is returned.
   */
  public static Coordinate[] close(@NonNull Coordinate[] coordinates) {
    if (coordinates == null) {
      throw new IllegalArgumentException("Parameter coordinates cannot be null.");
    }
    if (coordinates.length > 0) {
      Coordinate first = coordinates[0];
      int iLast = coordinates.length - 1;
      Coordinate last = coordinates[iLast];
      int size = coordinates.length;
      if (first.x == last.x && first.y == last.y) {
        return coordinates; // already closed.
      }
      Coordinate[] copy = Arrays.copyOf(coordinates, size);
      copy[iLast] = first;
      return copy;
    }
    return coordinates;
  }

  /**
   * Sets the precision, usually reducing unnecessary precision.
   *
   * @param geometry The geometry to reduce.
   * @param scale scale - amount by which to multiply a coordinate after
   * subtracting the offset, to obtain a precise coordinate
   * @return the Geometry after it has been rounded to the specified precision.
   */
  public static Geometry setPrecision(Geometry geometry, double scale) {
    PrecisionModel pmFixed3 = new PrecisionModel(scale);
    return GeometryPrecisionReducer.reduce(geometry, pmFixed3);
  }
}
