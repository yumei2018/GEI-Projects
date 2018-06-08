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

import com.vividsolutions.jts.geom.Coordinate;
import gov.ca.water.shapelite.coordinate.CoordGeoMeasurable;
import gov.ca.water.shapelite.coordinate.CoordValues;
import gov.ca.water.shapelite.coordinate.HasM;
import gov.ca.water.shapelite.coordinate.HasXY;
import gov.ca.water.shapelite.coordinate.HasZ;
import java.io.Serializable;
import java.util.Comparator;
import gov.ca.water.shapelite.coordinate.IntersectableCoord;
import gov.ca.water.shapelite.coordinate.MeasureableCoord;

/**
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface Coord extends Serializable, CoordValues, MeasureableCoord,
    CoordGeoMeasurable, IntersectableCoord, HasXY, HasM, HasZ {

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * An index definition that should be followed for all coordinates, even if
   * one or more of these values returns null.
   */
  public static class Index {

    /**
     * The integer index of the X ordinate.
     */
    public static final int X = 0;

    /**
     * The integer index of the Y ordinate.
     */
    public static final int Y = 1;

    /**
     * The integer index of the Z ordinate, which may or may not be supported.
     */
    public static final int Z = 2;

    /**
     * The integer index of the M ordinate, which may or may not be supported.
     */
    public static final int M = 3;
  }

  /**
   * This class supports a set of default comparators that should be useful for
   * all coordinates.
   */
  public static class By {

    /**
     * Sort by the X coordinate.
     */
    public static final Comparator<Coord> X = new Comparator<Coord>() {

      /**
       * Compare the values by the X coordinate.
       *
       * @param o1 The first coordinate.
       * @param o2 The second coordinate.
       * @return An integer based on the X value.
       */
      @Override
      public int compare(final Coord o1, final Coord o2) {
        return Double.compare(o1.getX(), o2.getX());
      }
    };

    /**
     * Sort by the Y coordinate.
     */
    public static final Comparator<Coord> Y = new Comparator<Coord>() {

      @Override
      public int compare(final Coord o1, final Coord o2) {
        return Double.compare(o1.getY(), o2.getY());
      }
    };

    /**
     * Sort by the M ordinate.
     */
    public static final Comparator<Coord> M = new Comparator<Coord>() {

      /**
       * Compare the values by the M ordinate.
       *
       * @param o1 The first coordinate.
       * @param o2 The second coordinate.
       * @return An integer based on the X value.
       */
      @Override
      public int compare(final Coord o1, final Coord o2) {
        if (o1.hasM() && o2.hasM()) {
          return Double.compare(o1.get(Coord.Index.M), o2.get(Coord.Index.M));
        } else {
          return 0;
        }
      }
    };

    /**
     * Compare by row major. First compare by Y, if Y is the same, then compare
     * by X.
     */
    public static final Comparator<Coord> YX = new Comparator<Coord>() {

      @Override
      public int compare(final Coord o1, final Coord o2) {
        int result = Double.compare(o1.getY(), o2.getY());
        if (result == 0) {
          return Double.compare(o1.getX(), o2.getX());
        }
        return result;
      }
    };
  }

  /**
   * If there are values in the envelope, all values will be set to zero, and
   * the hasChanged flag will be set to false so that this will become empty.
   */
  void clear();

  /**
   * Generates a copy of this coordinate.
   *
   * @return A duplicate coordinate that is a different object. This will never
   * be null, but may be flagged as Empty.
   */
  Coord copy();

  /**
   * Copies the X, Y, Z and M values from the other coord object while creating
   * a separate coordinate.
   *
   * @param other The other coordinate to copy from. This can be null, in which
   * case, this coordinate becomes empty.
   */
  void copyProperties(@Nullable CoordValues other);

  /**
   * Gets the 2d coordinate.
   *
   * @return The coordinate.
   */
  Coordinate toCoordinate();

  //</editor-fold>
  /**
   * Gets a boolean that indicates if this Coord has M.
   *
   * @return hasM
   */
  boolean hasM();

  /**
   * Gets a boolean that indicates if this Coord has Z.
   *
   * @return hasZ
   */
  boolean hasZ();



}
