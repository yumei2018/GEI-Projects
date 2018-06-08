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
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import gov.ca.water.shapelite.coordinate.CoordM;
import gov.ca.water.shapelite.coordinate.ObservableCoordM;
import gov.ca.water.shapelite.coordinate.ObservableCoordXY;
import gov.ca.water.shapelite.coordinate.ObservableCoordZ;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.envelope.EnvelopeValues;
import gov.ca.water.shapelite.events.CoordChangedEvent;
import gov.ca.water.shapelite.events.EnvelopeChangedEvent;
import gov.ca.water.shapelite.segment.DefaultSegment;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import gov.ca.water.shapelite.coordinate.ObservableCoord;
import java.util.Arrays;

/**
 * The Envelope class. This is distinct from JTS envelope because it supports M
 * values for shapefiles.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DefaultEnvelope implements Envelope {

  //<editor-fold defaultstate="collapsed" desc="Constants">
  /**
   * The M Coordinate index.
   */
  private static final int M = Coord.Index.M;

  /**
   * The Z Coordinate index.
   */
  private static final int Z = Coord.Index.Z;

  /**
   * The number of doubles in a 2D envelope.
   */
  public static final int SIZE_2D = 4;

  /**
   * The number of doubles in a 3D envelope with measure values M.
   */
  public static final int SIZE_3D = 8;

  /**
   * The array minimum X index in a shapefile envelope.
   */
  public static final int X1 = 0;

  /**
   * The array minimum Y index in a shapefile envelope.
   */
  public static final int Y1 = 1;

  /**
   * The array maximum X index in a shapefile envelope.
   */
  public static final int X2 = 2;

  /**
   * The array maximum Y index in a shapefile envelope.
   */
  public static final int Y2 = 3;

  /**
   * The array minimum X index in a shapefile envelope.
   */
  public static final int MIN_Z = 4;

  /**
   * The array minimum X index in a shapefile envelope.
   */
  public static final int MAX_Z = 5;

  /**
   * The array minimum X index in a shapefile envelope.
   */
  public static final int MIN_M = 6;

  /**
   * The array minimum X index in a shapefile envelope.
   */
  public static final int MAX_M = 7;

  /**
   * A hash offset to use in the hash set calculation.
   */
  public static final int HASH_OFFSET = 3;

  /**
   * A multiplier to use in a hash map calculation.
   */
  public static final int HASH_MULTIPLIER = 41;

  /**
   * The bit shift to use for the HASH Calculation.
   */
  public static final int HASH_SHIFT = 32;

  /**
   * The polygon index for the lower left point.
   */
  public static final int LOWER_LEFT = 0;

  /**
   * The polygon index for the upper left point.
   */
  public static final int UPPER_LEFT = 1;

  /**
   * The polygon index for the upper right point.
   */
  public static final int UPPER_RIGHT = 2;

  /**
   * The polygon index for the lower right point.
   */
  public static final int LOWER_RIGHT = 3;

  /**
   * The polygon index for the lower left point to close the polygon.
   */
  public static final int CLOSE = 4;

  /**
   * The size of a polygon created from the four points of the envelope, and
   * then repeating the first point to close the shape.
   */
  public static final int POLYGON_SIZE = 5;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The coordinate that defines the minimum bounds in X, Y and Z. This is
   * final, and so will never be null.
   */
  private final ObservableCoord min;

  /**
   * The coordinate that defines the maximum bounds in X and Y and Z. This is
   * final, and so will never be null.
   */
  private final ObservableCoord max;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Initializes a new instance of the Envelope class that is empty. This will
   * be a 2D envelope, so use the Coord versions for 3D cases.
   */
  public DefaultEnvelope() {
    min = new ObservableCoordXY(1, 1);
    max = new ObservableCoordXY(-1, -1);
    addChangeListeners();
  }

  /**
   * Creates an empty envelope that can optionally be made from M or Z
   * parameters. Once the coordinate type is defined, it is final.
   *
   * @param hasM Boolean, if true, but hasZ is false, then the coordinates will
   * be CoordM type coordinates.
   * @param hasZ Boolean, if true, then the coordinates will be CoordZ type
   * coordinates.
   */
  public DefaultEnvelope(boolean hasM, boolean hasZ) {
    if (hasZ) {
      min = new ObservableCoordZ(1, 1, 1);
      max = new ObservableCoordZ(-1, -1, -1);
    } else if (hasM) {
      min = new ObservableCoordM(1, 1, 0);
      max = new ObservableCoordM(-1, -1, 0);
    } else {
      min = new ObservableCoordXY(1, 1);
      max = new ObservableCoordXY(-1, -1);
    }
    addChangeListeners();

  }

  /**
   * Initializes a new instance of the Envelope from the array of values. The
   * envelope is designed to either have 4 values, in which case the coordinates
   * are CoordXY objects, or 8 values, in which case the coordinates will be
   * CoordZ objects.
   *
   * @param values minX, minY, maxX, maxY optionally: minZ, maxZ, minM, maxM.
   *
   */
  public DefaultEnvelope(double[] values) {
    if (values.length >= SIZE_3D) {
      ObservableCoordZ minZ = new ObservableCoordZ();
      ObservableCoordZ maxZ = new ObservableCoordZ();
      minZ.set(Z, values[MIN_Z]);
      maxZ.set(Z, values[MAX_Z]);
      minZ.set(M, values[MIN_M]);
      maxZ.set(M, values[MAX_M]);
      this.min = minZ;
      this.max = maxZ;
    } else {
      min = new ObservableCoordXY();
      max = new ObservableCoordXY();
    }
    if (values.length >= SIZE_2D) {
      if (!Double.isNaN(values[X1])) {
        min.setX(values[X1]);
      }
      if (!Double.isNaN(values[Y1])) {
        min.setY(values[Y1]);
      }
      if (!Double.isNaN(values[X2])) {
        max.setX(values[X2]);
      }
      if (!Double.isNaN(values[Y2])) {
        max.setY(values[Y2]);
      }
    }
    addChangeListeners();
  }

  /**
   * Initializes a new instance of the box class from the minZ and maxZ values.
   *
   * @param xyOnly minX, minY, maxX, maxY, without any M or Z values.
   * @param hasM Boolean, if true, and hasZ is false, then the coordinates will
   * be CoordM type coordinates.
   * @param hasZ Boolean, if true, then the min and max coords will be CoordZ
   * type coordinates.
   */
  public DefaultEnvelope(@NonNull double[] xyOnly, boolean hasM, boolean hasZ) {
    if (xyOnly == null) {
      throw new IllegalArgumentException("Parameter xyOnly cannot be null.");
    }
    if (hasZ) {
      this.min = new ObservableCoordZ();
      this.max = new ObservableCoordZ();
    } else if (hasM) {
      min = new ObservableCoordM();
      max = new ObservableCoordM();
    } else {
      min = new ObservableCoordXY();
      max = new ObservableCoordXY();
    }
    this.min.setX(xyOnly[X1]);
    this.min.setY(xyOnly[Y1]);
    this.max.setX(xyOnly[X2]);
    this.max.setY(xyOnly[Y2]);
    addChangeListeners();
  }

  /**
   * Initializes an Envelope using the specified coordinate, and expanding that
   * coordinate by Coordinate.Epsilon.
   *
   * @param coord The center of the envelope.
   */
  public DefaultEnvelope(Coord coord) {
    if (coord.hasZ()) {
      CoordZ coordZ = (CoordZ) coord;
      ObservableCoordZ minZ = new ObservableCoordZ();
      ObservableCoordZ maxZ = new ObservableCoordZ();
      minZ.set(Z, coordZ.get(Z) - CoordZ.getEpsilon());
      maxZ.set(Z, coordZ.get(Z) + CoordZ.getEpsilon());
      this.min = minZ;
      this.max = maxZ;
    } else if (coord.hasM()) {
      this.min = new ObservableCoordM();
      this.max = new ObservableCoordM();
    } else {
      min = new ObservableCoordXY(coord);
      max = new ObservableCoordXY(coord);
    }
    if (hasM()) {
      CoordM coordM = (CoordM) coord;
      CoordM minM = (CoordM) min;
      CoordM maxM = (CoordM) max;
      minM.set(M, coordM.get(M));
      maxM.set(M, coordM.get(M));
    }
    addChangeListeners();
  }

  /**
   * Create envelope based on the extent in the list of coordinates by iterating
   * through the list and applying expansions to include each coordinate. If
   * List of coordinates is empty or null, then the result will be the same as
   * empty constructor. If List contains only 1 coordinate, then the result will
   * be the same as single coordinate constructor.
   *
   * @param cellCoords
   */
  public DefaultEnvelope(List<Coord> cellCoords) {
    DefaultEnvelope _env = null;
    if (cellCoords != null) {
      for (Coord cellCoord : cellCoords) {
        if (_env == null) {
          _env = new DefaultEnvelope(cellCoord);
        } else {
          _env.expandToInclude(cellCoord, cellCoord.hasM(), cellCoord.hasZ());
        }
      }
      if (_env == null) {
        _env = new DefaultEnvelope();
      }
    }
    this.max = _env.getMax();
    this.min = _env.getMin();
  }

  /**
   * Initializes a new instance of the box class using minimum and maximum
   * values.
   *
   * @param coordA One of the two coordinates defining the envelope.
   * @param coordB The other coordinate defining the envelope.
   */
  public DefaultEnvelope(Coord coordA, Coord coordB) {
    double minX = Math.min(coordA.getX(), coordB.getX());
    double maxX = Math.max(coordA.getX(), coordB.getX());
    double minY = Math.min(coordA.getY(), coordB.getY());
    double maxY = Math.max(coordA.getY(), coordB.getY());
    double minM = 0;
    double maxM = 0;

    if (coordA.hasM() && coordB.hasM()) {
      minM = Math.min(coordA.get(M), coordB.get(M));
      maxM = Math.max(coordA.get(M), coordB.get(M));
    }
    if (coordA.hasZ() && coordB.hasZ()) {
      double minZ = Math.min(coordA.get(Z), coordB.get(Z));
      double maxZ = Math.max(coordA.get(Z), coordB.get(Z));
      this.min = new ObservableCoordZ(minX, minY, minZ, minM);
      this.max = new ObservableCoordZ(maxX, maxY, maxZ, maxM);
    } else if (coordA.hasM() && coordB.hasM()) {
      this.min = new ObservableCoordM(minX, minY, minM);
      this.max = new ObservableCoordM(maxX, maxY, maxM);
    } else {
      this.min = new ObservableCoordXY(minX, minY);
      this.max = new ObservableCoordXY(maxX, maxY);
    }
    addChangeListeners();
  }

  /**
   * Initializes a new instance of the Envelope class with CoordXY coordinates
   * using the specified bounds. This will find the minimum x for defining the
   * minimum and the maximum x for defining a maximum.
   *
   * @param x1 the first x coordinate.
   * @param y1 the first y coordinate.
   * @param x2 the second x coordinate.
   * @param y2 the second y coordinate.
   * @throws IllegalArgumentException if any of the values is NaN.
   */
  public DefaultEnvelope(@NonNan double x1, @NonNan double y1, @NonNan double x2,
      @NonNan double y2) {
    if (Double.isNaN(x1) || Double.isNaN(x2) || Double.isNaN(y1) || Double.isNaN(y2)) {
      throw new IllegalArgumentException("Envelope values cannot be NaN.");
    }
    min = new ObservableCoordXY(Math.min(x1, x2), Math.min(y1, y2));
    max = new ObservableCoordXY(Math.max(x1, x2), Math.max(y1, y2));
    addChangeListeners();
  }

  /**
   * Initializes a new instance of the Envelope class using the specified box.
   *
   * @param source The Envelope to use as a source envelope. This new envelope
   * will be entirely separate, but have values that match the coordinate
   * bounds.
   */
  public DefaultEnvelope(EnvelopeValues source) {
    if (source.getMin().hasZ() && source.getMax().hasZ()) {
      min = new ObservableCoordZ(source.getMin());
      max = new ObservableCoordZ(source.getMax());
    } else if (source.getMin().hasM() && source.getMax().hasM()) {
      min = new ObservableCoordM(source.getMin());
      max = new ObservableCoordM(source.getMax());
    } else {
      min = new ObservableCoordXY(source.getMin());
      max = new ObservableCoordXY(source.getMax());
    }
    addChangeListeners();

  }

  /**
   * Creates a new 2D envelope from a JTS envelope.
   *
   * @param jtsEnvelope A JTS Envelope to use to define the X and Y for this
   * envelope.
   * @throws IllegalArgumentException if any of the values is NaN.
   */
  public DefaultEnvelope(com.vividsolutions.jts.geom.Envelope jtsEnvelope) {
    double x1 = jtsEnvelope.getMinX();
    double x2 = jtsEnvelope.getMaxX();
    double y1 = jtsEnvelope.getMinY();
    double y2 = jtsEnvelope.getMaxY();
    if (Double.isNaN(x1) || Double.isNaN(x2) || Double.isNaN(y1) || Double.isNaN(y2)) {
      throw new IllegalArgumentException("Envelope values cannot be NaN.");
    }
    min = new ObservableCoordXY(x1, y1);
    max = new ObservableCoordXY(x2, y2);
    addChangeListeners();
  }

  /**
   * Creates envelope with a center, width, and height.
   *
   * @param center
   * @param width
   * @param height
   */
  public DefaultEnvelope(Coord center, double width, double height) {
    this.min = new ObservableCoordXY(CoordMath.add2D(center, new CoordXY(-0.5 * width, -0.5 * height)));
    this.max = new ObservableCoordXY(CoordMath.add2D(center, new CoordXY(0.5 * width, 0.5 * height)));
  }

  /**
   * Adds change listeners to the coordinates so that if the values are
   * modified, the change can be observed in the envelope.
   */
  private void addChangeListeners() {
    CoordChangedEvent.Listener changeHandler = new CoordChangedEvent.Listener() {

      @Override
      public void coordChanged(CoordChangedEvent e) {
        fireEnvelopeChanged(new EnvelopeChangedEvent(DefaultEnvelope.this,
            DefaultEnvelope.this));
      }
    };
    min.addCoordChangedListener(changeHandler);
    max.addCoordChangedListener(changeHandler);
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="EnvelopeChangedEvent">
  /**
   * A set of listeners for any changes in this envelope.
   */
  private final List<EnvelopeChangedEvent.Listener> envelopeChangedListeners
      = new ArrayList<>();

  /**
   * Adds the specified listener to the list of listeners to be notified during
   * an event. If the item is already in the list, it will not be added a second
   * time.
   *
   * @param listener The EnvelopeChangedEvent.Listener to connect.
   */
  @Override
  public final void addEnvelopeChangedListener(
      EnvelopeChangedEvent.Listener listener) {
    if (!envelopeChangedListeners.contains(listener)) {
      envelopeChangedListeners.add(listener);
    }
  }

  /**
   * Removes the specified listener from the list if it is in the list.
   *
   * @param listener The EnvelopeChangedEvent.Listener to disconnect.
   */
  @Override
  public final void removeEnvelopeChangedListener(
      EnvelopeChangedEvent.Listener listener) {
    envelopeChangedListeners.remove(listener);
  }

  /**
   * Fires the EnvelopeChanged event and notifies each of the listeners.
   *
   * @param e A {EventType}Event with the source object and any properties
   * associated with this event.
   */
  public final void fireEnvelopeChanged(EnvelopeChangedEvent e) {
    for (EnvelopeChangedEvent.Listener listener : envelopeChangedListeners) {
      listener.envelopeChanged(e);
    }
  }

  //</editor-fold>
  /**
   * Given an array with the first four values, this will create an envelope
   * from the array where the coordinates are CoordM values. The min, max will
   * be calculated for the values.
   *
   * @param values Assumes the values are in the following order: x1 - either x
   * value y1 - either y value x2 - the other x value. y2 - the other y value.
   * values can be null, in which case this returns an empty Envelop with CoordM
   * coordinates.
   * @return An Envelope with CoordM coordinates.
   */
  public static DefaultEnvelope getXY(@Nullable double[] values) {
    if (values == null) {
      return new DefaultEnvelope(new CoordM(), new CoordM());
    }
    if (values.length != SIZE_2D) {
      throw new IllegalArgumentException("This method was not intended for"
          + " use with array sizes other than 4.");
    }
    double minX = Math.min(values[X1], values[X2]);
    double minY = Math.min(values[Y1], values[Y2]);
    double maxX = Math.max(values[X1], values[X2]);
    double maxY = Math.max(values[Y1], values[Y2]);
    return new DefaultEnvelope(new CoordXY(minX, minY), new CoordXY(maxX, maxY));
  }

  /**
   * Given an array with the first four values, this will create an envelope
   * from the array where the coordinates are CoordM values. The min, max will
   * be calculated for the values.
   *
   * @param values Assumes the values are in the following order: x1 - either x
   * value y1 - either y value x2 - the other x value. y2 - the other y value.
   * values can be null, in which case this returns an empty Envelop with CoordM
   * coordinates.
   * @return An Envelope with CoordM coordinates.
   */
  public static DefaultEnvelope getM(@Nullable double[] values) {
    if (values == null) {
      return new DefaultEnvelope(new CoordM(), new CoordM());
    }
    if (values.length != SIZE_2D) {
      throw new IllegalArgumentException("This method was not intended for"
          + " use with array sizes other than 4.");
    }
    double minX = Math.min(values[X1], values[X2]);
    double minY = Math.min(values[Y1], values[Y2]);
    double maxX = Math.max(values[X1], values[X2]);
    double maxY = Math.max(values[Y1], values[Y2]);
    return new DefaultEnvelope(new CoordM(minX, minY, 0), new CoordM(maxX, maxY, 0));
  }

  /**
   * Given an array with the first four values, this will create an envelope
   * from the array where the coordinates are CoordM values. The min, max will
   * be calculated for the values.
   *
   * @param values Assumes the values are in the following order: x1 - either x
   * value y1 - either y value x2 - the other x value. y2 - the other y value.
   * values can be null, in which case this returns an empty Envelop with CoordM
   * coordinates.
   * @return An Envelope with CoordM coordinates.
   */
  public static DefaultEnvelope getZ(@Nullable double[] values) {
    if (values == null) {
      return new DefaultEnvelope(new CoordM(), new CoordM());
    }
    if (values.length != SIZE_2D) {
      throw new IllegalArgumentException("This method was not intended for"
          + " use with array sizes other than 4.");
    }
    double minX = Math.min(values[X1], values[X2]);
    double minY = Math.min(values[Y1], values[Y2]);
    double maxX = Math.max(values[X1], values[X2]);
    double maxY = Math.max(values[Y1], values[Y2]);
    return new DefaultEnvelope(new CoordZ(minX, minY, 0, 0),
        new CoordZ(maxX, maxY, 0, 0));
  }

  /**
   * Generates a duplicate of this box.
   *
   * @return A deep copy of this envelope.
   */
  @Override
  public final DefaultEnvelope copy() {
    DefaultEnvelope result = new DefaultEnvelope(hasM(), hasZ());
    result.copyProperties(this);
    return result;
  }

  /**
   * Copies the coordinate properties to this envelope, but use a separate set
   * of coordinate objects, making it a deep copy.
   *
   * @param other The Other envelope to copy. This cannot be null.
   * @throws IllegalArgumentException if other is null.
   */
  @Override
  public final void copyProperties(@NonNull Envelope other) {
    if (other == null) {
      throw new IllegalArgumentException("Envelope other cannot be null.");
    }
    this.min.copyProperties(other.getMin());
    this.max.copyProperties(other.getMax());

  }

  /**
   * Sets both the minimum and maximum.
   *
   * @param other Coord.
   * @throws IllegalArgumentException if other is null.
   */
  @Override
  public final void copyProperties(@NonNull Coord other) {
    if (other == null) {
      throw new IllegalArgumentException("Envelope other cannot be null.");
    }
    this.min.copyProperties(other);
    this.max.copyProperties(other);
  }

  /**
   * Calculates the smallest distance from this box to the specified coordinate.
   *
   * @param position The coordinate to test.
   * @return The double distance to the position.
   */
  @Override
  public final double distance(Coord position) {
    double dx = 0;
    double dy = 0;
    if (this.intersects(position)) {
      return 0;
    }
    if (position.getX() < min.getX()) {
      dx = min.getX() - position.getX();
    } else if (position.getX() > max.getX()) {
      dx = position.getX() - max.getX();
    }
    if (position.getY() < min.getY()) {
      dy = min.getY() - position.getY();
    } else if (position.getY() > max.getY()) {
      dy = position.getY() - max.getY();
    }
    return Math.sqrt(dx * dx + dy * dy);
  }

  /**
   * Tests for equality with another object.
   *
   * @param obj An Object to test for equality. If obj is null, then this will
   * return false.
   * @return Boolean, true if the X, Y, Z and M values are equivalent, and the
   * other object is also an Envelope.
   */
  @Override
  public final boolean equals(@Nullable Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj instanceof Envelope) {
      final Envelope other = (DefaultEnvelope) obj;
      if (!this.min.equals(other.getMin())) {
        return false;
      }
      return this.max.equals(other.getMax());
    }
    return false;
  }

  /**
   * Tests for equality with another object.
   *
   * @param env An Envelope to test for equality. If obj is null, then this will
   * return false.
   * @param epsilon A double value expressing the tolerance for intersection
   * equality.
   * @return Boolean, true if the X, Y, Z and M values are equivalent, and the
   * other object is also an Envelope.
   */
  @Override
  public final boolean equals(@Nullable Envelope env, double epsilon) {
    if (env == null) {
      return false;
    }
    final Envelope other = (DefaultEnvelope) env;
    if (!this.min.intersects(other.getMin(), epsilon)) {
      return false;
    }
    return this.max.intersects(other.getMax(), epsilon);
  }

  /**
   * Expands this envelope to include the specified coordinate.
   *
   * @param coordinate The coordinate to include. If this is null or empty then
   * this will do nothing.
   */
  @Override
  public final void expandToInclude(@Nullable Coord coordinate) {
    if (coordinate == null) {
      return;
    }
    if (this.isEmpty()) {
      this.min.setX(coordinate.getX());
      this.min.setY(coordinate.getY());
      this.max.setX(coordinate.getX());
      this.max.setY(coordinate.getY());
      if (this.hasM() && coordinate.hasM()) {
        this.min.set(M, coordinate.get(M));
        this.max.set(M, coordinate.get(M));
      }
      if (this.hasZ() && coordinate.hasZ()) {
        this.min.set(Z, coordinate.get(Z));
        this.max.set(Z, coordinate.get(Z));
      }
      return;
    }
    if (max.getX() < coordinate.getX()) {
      max.setX(coordinate.getX());
    }
    if (max.getY() < coordinate.getY()) {
      max.setY(coordinate.getY());
    }
    if (min.getY() > coordinate.getY()) {
      min.setY(coordinate.getY());
    }
    if (min.getX() > coordinate.getX()) {
      min.setX(coordinate.getX());
    }
    if (coordinate.hasZ() && min.hasZ() && max.hasZ()) {
      min.set(Z, coordinate.get(Z));
      max.set(Z, coordinate.get(Z));
    }
  }

  /**
   * Expands this envelope to include the specified coordinate.
   *
   * @param coordinate The coordinate to expand to include. This can be null or
   * empty, in which case, this does nothing.
   * @param hasM Boolean true if the bounds considers M values.
   * @param hasZ Boolean true if the bounds considers Z values.
   */
  @Override
  public final void expandToInclude(@Nullable Coord coordinate,
      boolean hasM, boolean hasZ) {
    if (coordinate == null) {
      return;
    }
    if (this.isEmpty()) {
      min.setX(coordinate.getX());
      max.setX(coordinate.getX());
      min.setY(coordinate.getY());
      max.setY(coordinate.getY());
      if (this.hasZ() && coordinate.hasZ()) {
        min.set(Z, coordinate.get(Z));
        max.set(Z, coordinate.get(Z));
      }
      if (this.hasM() && coordinate.hasM()) {
        min.set(M, coordinate.get(M));
        max.set(M, coordinate.get(M));
      }
      return;
    }
    if (max.getX() < coordinate.getX()) {
      max.setX(coordinate.getX());
    }
    if (max.getY() < coordinate.getY()) {
      max.setY(coordinate.getY());
    }
    if (min.getY() > coordinate.getY()) {
      min.setY(coordinate.getY());
    }
    if (min.getX() > coordinate.getX()) {
      min.setX(coordinate.getX());
    }
    if (hasM && coordinate.hasM()) {
      if (min.get(M) > coordinate.get(M)) {
        min.set(M, coordinate.get(M));
      }
      if (max.get(M) < coordinate.get(M)) {
        max.set(M, coordinate.get(M));
      }
    }
    if (hasZ && coordinate.hasZ()) {
      if (min.get(Z) > coordinate.get(Z)) {
        min.set(Z, coordinate.get(Z));
      }
      if (max.get(Z) < coordinate.get(Z)) {
        max.set(Z, coordinate.get(Z));
      }
    }
  }

  /**
   * This will expand the current box in any direction to match the specified
   * box.
   *
   * @param other the other envelope to expand to include. If this is null or
   * empty, then this method does nothing.
   */
  @Override
  public final void expandToInclude(@Nullable Envelope other) {
    if (other == null || other.isEmpty()) {
      return;
    }
    if (this.isEmpty()) {
      this.copyProperties(other);
      return;
    }
    if (other.getMax().getX() > max.getX()) {
      max.setX(other.getMax().getX());
    }
    if (other.getMax().getY() > max.getY()) {
      max.setY(other.getMax().getY());
    }
    if (this.hasZ() && other.hasZ()) {
      if (other.getMax().get(Z) > max.get(Z)) {
        max.set(Z, other.getMax().get(Z));
      }
    }
    if (other.getMin().getX() < min.getX()) {
      min.setX(other.getMin().getX());
    }
    if (other.getMin().getY() < min.getY()) {
      min.setY(other.getMin().getY());
    }
    if (this.hasZ() && other.hasZ()) {
      if (other.getMin().get(Z) < min.get(Z)) {
        min.set(Z, other.getMin().get(Z));
      }
    }

  }

  /**
   * This will expand the current box in any direction to match the specified
   * box.
   *
   * @param other The rectangle to expand to include. If other is null or empty
   * then this does nothing.
   */
  @Override
  public final void expandToInclude(@Nullable Rectangle2D other) {
    if (other == null || other.isEmpty()) {
      return;
    }
    if (this.isEmpty()) {
      min.setX(other.getMinX());
      max.setX(other.getMaxX());
      min.setY(other.getMinY());
      max.setY(other.getMaxY());
      return;
    }
    if (other.getMaxX() > max.getX()) {
      max.setX(other.getMaxX());
    }
    if (other.getMaxY() > max.getY()) {
      max.setY(other.getMaxY());
    }
    if (other.getMinX() < min.getX()) {
      min.setX(other.getMinX());
    }
    if (other.getMinY() < min.getY()) {
      min.setY(other.getMinY());
    }
  }

  /**
   * Gets the coordinate at the geographic center of this envelope.
   *
   * @return A newly created Coordinate calculated to be the center position.
   */
  @Override
  public final CoordXY getCenter() {
    double x = this.min.getX();
    if (Double.isNaN(x)) {
      x = 0;
    }
    double y = this.min.getY();
    if (Double.isNaN(y)) {
      y = 0;
    }
    return new CoordXY(x + this.getWidth() / 2.0,
        y + this.getHeight() / 2.0);
  }

  /**
   * Gets a hashcode that attempts to be distinct based on the values, or at
   * least spread different envelopes out across the integer number line.
   *
   * @return
   */
  @Override
  public final int hashCode() {
    int hash = HASH_OFFSET;
    hash = HASH_MULTIPLIER * hash + min.hashCode();
    hash = HASH_MULTIPLIER * hash + max.hashCode();
    return hash;
  }

  /**
   * Gets the double distance between minimum and maximum Y.
   *
   * @return the height.
   */
  @Override
  public final double getHeight() {
    return max.getY() - min.getY();
  }

  /**
   * Gets the double distance between minimum and maximum X.
   *
   * @return the width.
   */
  @Override
  public final double getWidth() {
    return max.getX() - min.getX();
  }

  /**
   * Gets the envelope that is the overlapping region between the two envelopes.
   *
   * @param other the envelope to intersect with. If other is null or empty,
   * then this method will return a new empty envelope.
   * @return The Envelope of intersection, or an Empty envelope in the case of
   * no intersection. This will never be null.
   * @NonNull
   */
  @Override
  public final DefaultEnvelope intersect(@Nullable Envelope other) {
    if (other == null || other.isEmpty() || !intersects(other)) {
      return new DefaultEnvelope();
    }
    if (this.isEmpty()) {
      return new DefaultEnvelope();
    }
    double minX, maxX, minY, maxY, minM = 0, maxM = 0;
    minX = Math.max(min.getX(), other.getMin().getX());
    maxX = Math.min(max.getX(), other.getMax().getX());
    minY = Math.max(min.getY(), other.getMin().getY());
    maxY = Math.min(max.getY(), other.getMax().getY());

    Coord resultMin;
    Coord resultMax;
    if (this.hasM() && other.hasM()) {
      minM = Math.max(min.get(M), other.getMin().get(M));
      maxM = Math.min(max.get(M), other.getMax().get(M));
    }
    if (this.hasZ() && other.hasZ()) {
      double maxZ = Math.min(max.get(Z), other.getMax().get(Z));
      double minZ = Math.max(min.get(Z), other.getMin().get(Z));
      resultMin = new CoordZ(minX, minY, minZ, minM);
      resultMax = new CoordZ(maxX, maxY, maxZ, maxM);
    } else if (this.hasM() && other.hasM()) {
      resultMin = new CoordM(minX, minY, minM);
      resultMax = new CoordM(maxX, maxY, maxM);
    } else {
      resultMin = new CoordXY(minX, minY);
      resultMax = new CoordXY(maxX, maxY);
    }

    return new DefaultEnvelope(resultMin, resultMax);
  }

  /**
   * This will test for intersections using the X and Y ordinate. If the Z
   * ordinate is not NaN in any of the values, it will test for intersection in
   * the Z dimension.
   *
   * @param seg The Segment to test for intersection.
   * @return True if the rectangular regions overlap or touch the segment.
   */
  @Override
  public final boolean intersects2D(@Nullable Segment seg) {
    return intersection2D(seg).isPresent();
  }

  /**
   * Gets an optional segment representing the intersection of that segment with
   * this envelope in 2D.
   *
   * @param seg The segment to intersect.
   * @return The Optional segment, which is empty if there is no intersection.
   */
  @Override
  public final Optional<Segment> intersection2D(Segment seg) {
    Segment result;
    boolean insideP1 = this.intersects2D(seg.getP1());
    boolean insideP2 = this.intersects2D(seg.getP2());
    Coord p1 = new CoordXY(seg.getP1());
    Coord p2 = new CoordXY(seg.getP2());
    Segment seg2D = new DefaultSegment(p1, p2);
    if (insideP1 && insideP2) {
      // if both endpoints are within the box, the whole segment is.
      return Optional.of(seg2D);
    }
    List<Coord> edgePoints = edgeIntersections(seg2D);
    if (edgePoints.size() > 1) {
      // the segment lies on an edge, creating an overlapping segment.
      result = new DefaultSegment(edgePoints.get(0), edgePoints.get(1));
      return Optional.of(result);
    }
    if (edgePoints.size() > 0) {
      if (insideP1) {
        result = new DefaultSegment(p1, edgePoints.get(0));
        return Optional.of(result);
      }
      if (insideP2) {
        result = new DefaultSegment(edgePoints.get(0), p2);
        return Optional.of(result);
      }
      result = new DefaultSegment(edgePoints.get(0), edgePoints.get(0));
      return Optional.of(result);
    }

    return Optional.empty();

  }

  /**
   * Finds the list of edge intersections. Since a segment could lie on the
   * edge, this may return the overlapping region as defined by two coordinates.
   *
   * @param seg The segment to test.
   * @return The list of coordinate intersections with edges. This may be empty
   * but will never be null.
   */
  private List<Coord> edgeIntersections(Segment seg) {
    List<Coord> result = new ArrayList<>();
    List<Segment> edges = this.getEdges2D();
    for (Segment edge : edges) {
      Optional<Shape> sect = edge.intersection(seg);
      if (sect.isPresent()) {
        for (Coord coord : sect.get().getCoordinates()) {
          result.add(coord);
        }
      }
    }
    return result;
  }

  /**
   * This will test for intersection of the specified coordinate within this
   * box. If the Z is NaN in either of the bounds of this box or in the
   * coordinate, then the Z dimension will not be tested.
   *
   * @param coordinate The Coordinate to test for intersection with this
   * envelope. If coordinate is null, or either this or coordinate is empty then
   * this will return false.
   * @return A boolean that is true if the coordinate intersects with this
   * Envelope.
   */
  @Override
  public final boolean intersects(@Nullable Coord coordinate) {
    if (coordinate == null) {
      return false;
    }
    if (this.isEmpty()) {
      return false;
    }
    if (coordinate.getX() < min.getX() || coordinate.getX() > max.getX()) {
      return false;
    }
    if (coordinate.getY() < min.getY() || coordinate.getY() > max.getY()) {
      return false;
    }
    if (this.hasZ() && coordinate.hasZ()) {
      if (coordinate.get(Z) < min.get(Z)
          || coordinate.get(Z) > max.get(Z)) {
        return false;
      }
    }
    return true;
  }

  /**
   * This will test for intersections using the X and Y ordinate. If the Z
   * ordinate is not NaN in any of the values, it will test for intersection in
   * the Z dimension.
   *
   * @param other The Envelope to test for intersection.
   * @return True if the rectangular regions overlap or touch.
   */
  @Override
  public final boolean intersects(@Nullable Envelope other) {
    if (other == null || other.isEmpty() || this.isEmpty()) {
      return false;
    }
    if (other.getMax().getX() < min.getX()) {
      return false;
    }
    if (other.getMax().getY() < min.getY()) {
      return false;
    }
    if (other.getMin().getX() > max.getX()) {
      return false;
    }
    if (other.getMin().getY() > max.getY()) {
      return false;
    }
    if (this.hasZ() && other.hasZ()) {
      if (other.getMax().get(Z) < min.get(Z)) {
        return false;
      }
      return other.getMin().get(Z) <= max.get(Z);
    }
    return true;
  }

  /**
   * This will test for intersection of the specified coordinate within this
   * box, but only in X and Y coordinates.
   *
   * @param coord The Coordinate to test for intersection with this envelope. If
   * coordinate is null, or either this or coordinate is empty then this will
   * return false.
   * @return A boolean that is true if the coordinate intersects with this
   * Envelope.
   */
  @Override
  public final boolean intersects2D(@Nullable Coord coord) {
    if (coord == null) {
      return false;
    }
    return (min.getX() <= coord.getX() && coord.getX() <= max.getX())
        && (min.getY() <= coord.getY() && coord.getY() <= max.getY());
  }

  /**
   * Gets an optional segment representing the intersection of that segment with
   * this envelope in 2D.
   *
   * @param coord The coordinate to intersect.
   * @return The Optional segment, which is empty if there is no intersection.
   */
  @Override
  public final Optional<Coord> intersection2D(Coord coord) {
    Coord c = new CoordXY(coord);
    if (intersects2D(c)) {
      return Optional.of(c);
    }
    return Optional.empty();
  }

  /**
   * This will test for intersections using the X and Y ordinate, and ignore Z
   * or M values, even if one or the other envelope has them.
   *
   * @param other The Envelope to test for intersection. Other can be null. If
   * other is null, or either envelope is empty, this returns false.
   * @return True if the rectangular regions overlap or touch.
   */
  @Override
  public final boolean intersects2D(@Nullable Envelope other) {
    if (other == null || this.isEmpty() || other.isEmpty()) {
      return false;
    }
    if (other.getMax().getX() < min.getX()) {
      return false;
    }
    if (other.getMax().getY() < min.getY()) {
      return false;
    }
    if (other.getMin().getX() > max.getX()) {
      return false;
    }
    return other.getMin().getY() <= max.getY();
  }

  /**
   * If the minimum is greater than the maximum, this is a special envelope
   * state considered to be "empty" or not yet defined.
   *
   * @return A boolean that is true if this Envelope is empty.
   */
  @Override
  public final boolean isEmpty() {
    return min.getX() > max.getX() || min.getY() > max.getY();
  }

  /**
   * This tests to see if the Z axis is either NAN or else equal, so that the
   * box is two dimensional.
   *
   * @return A boolean that is true if the Z dimension is NaN for either
   * coordinate, or else if the minimum and maximum Z are equal.
   */
  @Override
  public final boolean isFlat() {
    return !hasZ();
  }

  /**
   * If the point is inside the extents, this returns the largest distance to
   * the bounds. if the point is outside, then rather than finding the smallest
   * distance in the box to the point, this finds the largest distance to the
   * point.
   *
   * @param position The coordinate to test. This cannot be null.
   * @return The double maximum distance.
   * @throws IllegalArgumentException if position is null or empty.
   */
  @Override
  public final double maxDistance(@NonNull Coord position) {
    if (position == null) {
      throw new IllegalArgumentException("Parameter position cannot be null.");
    }
    double dx = 0;
    double dy = 0;
    if (this.intersects(position)) {
      double cx = (min.getX() + max.getX()) / 2;
      double cy = (min.getY() + max.getY()) / 2;

      if (position.getX() < cx) {
        dx = max.getX() - position.getX();
      } else {
        dx = position.getX() - min.getX();
      }

      if (position.getY() < cy) {
        dy = max.getY() - position.getY();
      } else {
        dy = position.getY() - min.getY();
      }
    } else {
      if (position.getX() < min.getX()) {
        dx = max.getX() - position.getX();
      } else if (position.getX() > max.getX()) {
        dx = position.getX() - min.getX();
      }
      if (position.getY() < min.getY()) {
        dy = max.getY() - position.getY();
      } else if (position.getY() > max.getY()) {
        dy = position.getY() - min.getY();
      }
    }

    return Math.sqrt(dx * dx + dy * dy);
  }

  /**
   * Returns the entire contents of this box in the array form xmin, ymin, xmax,
   * ymax, zmin, zmax, mmin, mmax.
   *
   * @return double[] the array of double values.
   */
  @Override
  @NonNull
  public final double[] toArray() {
    int size = SIZE_2D;
    if (hasZ() || hasM()) {
      size = SIZE_3D;
    }
    double[] result = new double[size];
    result[X1] = min.getX();
    result[Y1] = min.getY();
    result[X2] = max.getX();
    result[Y2] = max.getY();
    if (hasZ()) {
      result[MIN_Z] = min.get(Z);
      result[MAX_Z] = max.get(Z);
    } else if (hasM()) {
      result[MIN_M] = min.get(M);
      result[MAX_M] = max.get(M);
    }
    return result;
  }

  /**
   * Generates a two dimensional array with 4 values. xmin, ymin, xmax, ymax.
   *
   * @return double[]
   */
  @Override
  @NonNull
  public final double[] toArray2D() {
    double[] result = new double[SIZE_2D];
    result[X1] = min.getX();
    result[Y1] = min.getY();
    result[X2] = max.getX();
    result[Y2] = max.getY();
    return result;
  }

  /**
   * Gets a Rectangle2D version of this envelope in world coordinates (so Y gets
   * larger going up).
   *
   * @return A Rectangle2D (double precision) with the specified coordinate
   * values.
   */
  @Override
  @NonNull
  public final Rectangle2D toRectangle2D() {
    Rectangle2D result = new Rectangle2D.Double(
        this.min.getX(), this.min.getY(),
        this.getWidth(), this.getHeight());
    return result;
  }

  /**
   * Transforms the Envelope into a line shape. This can be useful for
   * representing extent rectangles on the map. This does not include the Z
   * coordinates.
   *
   * @return A 2D Rectangle shape created from this envelope.
   */
  @Override
  @NonNull
  public final Shape getBorder() {
    Shape result = new Shape(ShapeType.PolyLine);
    List<Coord> coords = new ArrayList<>();
    coords.add(new CoordXY(min.getX(), max.getY()));
    coords.add(new CoordXY(max.getX(), max.getY()));
    coords.add(new CoordXY(max.getX(), min.getY()));
    coords.add(new CoordXY(min.getX(), min.getY()));
    coords.add(new CoordXY(min.getX(), max.getY()));
    Part prt = new Part(coords);
    result.getParts().add(prt);
    result.calculateBounds();
    return result;
  }

  /**
   * Adjusts the size of this envelope by a multiplicative factor scale, keeping
   * the anchor point in the same relative location in both views.
   *
   * @param scale The double multiplicative scale factor.
   * @return The resulting Envelope.
   */
  @Override
  public final DefaultEnvelope zoom(double scale) {
    return this.zoom(scale, this.getCenter());
  }

  /**
   * Adjusts the size of this envelope by a multiplicative factor scale, keeping
   * the anchor point in the same relative location in both views.
   *
   * @param scale The multiplier scale for zooming.
   * @param anchor The position that should be in the same location after
   * zooming.
   * @return the Envelope that is the result of the zoom operation.
   */
  @Override
  public final DefaultEnvelope zoom(double scale, @NonNull Coord anchor) {
    if (anchor == null) {
      throw new IllegalArgumentException("Parameter anchor cannot be null.");
    }
    double lowx = (anchor.getX() - min.getX());
    double lowy = (anchor.getY() - min.getY());
    double highx = (max.getX() - anchor.getX());
    double highy = (max.getY() - anchor.getY());
    lowx = lowx * scale;
    lowy = lowy * scale;
    highx = highx * scale;
    highy = highy * scale;
    DefaultEnvelope result = new DefaultEnvelope(
        anchor.getX() - lowx,
        anchor.getY() - lowy,
        anchor.getX() + highx,
        anchor.getY() + highy);
    return result;
  }

  /**
   * Gets a boolean that is true if this envelope is entirely contained by the
   * other envelope.
   *
   * @param other The other envelope to compare to.
   * @return Boolean, true if the other envelope is contained by this one.
   */
  @Override
  public final boolean within2D(Envelope other) {
    if (other.getMax().getX() < max.getX()) {
      return false;
    }
    if (other.getMax().getY() < max.getY()) {
      return false;
    }
    if (other.getMin().getX() > min.getX()) {
      return false;
    }
    return other.getMin().getY() <= min.getY();
  }

  /**
   * This tests if the X and Y dimensions of the specified other envelope are
   * completely contained in this envelope.
   *
   * @param other The other envelope to compare to.
   * @return boolean, true if the other envelope is contained in this one.
   */
  @Override
  public final boolean contains2D(Envelope other) {
    if (other.getMax().getX() > max.getX()) {
      return false;
    }
    if (other.getMax().getY() > max.getY()) {
      return false;
    }
    if (other.getMin().getX() < min.getX()) {
      return false;
    }
    return other.getMin().getY() >= min.getY();
  }

  /**
   * Checks whether a point is contained in the envelope.
   *
   * @see containsPoint for 3D version
   * @param point -- a query point
   * @return -- returns true if points is contained in envelope, false otherwise
   */
  @Override
  public final boolean containsPoint2D(Coord point) {
    return (min.getX() < point.getX() && point.getX() < max.getX())
        && (min.getY() < point.getY() && point.getY() < max.getY());
  }

  /**
   * Checks whether a point is contained in the envelope. This officially will
   * test a 3D envelope, but if either the coordinate or the envelope returns
   * false for hasZ, then the Z values are not considered, and the 2D contains
   * test is used instead.
   *
   * @see containsPoint2D for 2D version
   * @param point -- a query point. The point can be null or empty, in which
   * case, contains returns false.
   * @return -- returns true if points is contained in envelope, false otherwise
   */
  @Override
  public final boolean containsPoint(@Nullable Coord point) {
    if (point == null) {
      return false;
    }
    boolean result = containsPoint2D(point);
    if (result && this.hasZ() && point.hasZ()) {
      return (min.get(Z) < point.get(Z) && point.get(Z) < max.get(Z));
    }
    return result;
  }

  /**
   * Corrects the envelope if isEmpty, by interchanging the maximum and minimum
   * coordinates if necessary.
   *
   * @see isEmpty
   */
  @Override
  public final void correct() {
    Coord maxCopy = max.copy();
    Coord minCopy = min.copy();
    if (min.getX() > max.getX()) {
      min.setX(maxCopy.getX());
      max.setX(minCopy.getX());
    }
    if (min.getY() > max.getY()) {
      min.setY(maxCopy.getY());
      max.setY(minCopy.getY());
    }
    if (this.hasZ()) {
      int z = Coord.Index.Z;
      min.set(z, maxCopy.get(z));
      max.set(z, minCopy.get(z));
    }

  }

  /**
   * @return the minZ
   */
  @Override
  public final ObservableCoord getMin() {
    return min;
  }

  /**
   * @return the maxZ
   */
  @Override
  public final ObservableCoord getMax() {
    return max;
  }

  /**
   * Gets a boolean that indicates if this Envelope has a Z value. Both the Min
   * and Max coordinates must have a z value for this to be true.
   *
   * @return boolean, true if the envelope has Z values.
   */
  @Override
  public final boolean hasZ() {
    return min.hasZ() && max.hasZ();
  }

  /**
   * Gets a boolean that indicates if this Envelope has an M value. Both the Min
   * and Max coordinates must have an M value for this to be true.
   *
   * @return boolean, true if the envelope has M values.
   */
  @Override
  public final boolean hasM() {
    return min.hasM() && max.hasM();
  }

  /**
   * Creates a String representation of this envelope.
   *
   * @return A String with the x and y content.
   */
  @Override
  public final String toString() {
    return "[" + min.getX() + ", " + min.getY() + "]-[" + max.getX() + ", "
        + max.getY() + "]";
  }

  /**
   * Gets a JTS envelope from this envelope. The JTS envelope is intrinsic to
   * topology, but has no M information.
   *
   * @return The JTS Envelope.
   */
  @Override
  public final com.vividsolutions.jts.geom.Envelope toJtsEnvelope() {
    return new com.vividsolutions.jts.geom.Envelope(this.min.toCoordinate(),
        this.max.toCoordinate());
  }

  /**
   * Creates a new polygon based on this envelope.
   *
   * @return A JTS Polygon based on the outer bounds of this envelope.
   */
  @Override
  public final Polygon toJtsPolygon() {
    double xmin = this.min.getX();
    double ymin = this.min.getY();
    double xmax = this.max.getX();
    double ymax = this.max.getY();
    Coordinate[] points = new Coordinate[POLYGON_SIZE];
    points[LOWER_LEFT] = new Coordinate(xmin, ymin);
    points[UPPER_LEFT] = new Coordinate(xmin, ymax);
    points[UPPER_RIGHT] = new Coordinate(xmax, ymax);
    points[LOWER_RIGHT] = new Coordinate(xmax, ymin);
    points[CLOSE] = new Coordinate(xmin, ymin);
    GeometryFactory gf = new GeometryFactory();
    return gf.createPolygon(points);
  }

  /**
   * Returns this envelope as set of segments, starting with the top left and
   * moving clockwise.
   *
   * @return The list of Segments.
   */
  @Override
  public final List<Segment> getEdges2D() {
    double top = max.getY();
    double left = min.getX();
    double right = max.getX();
    double bottom = min.getY();
    List<Segment> results = new ArrayList<>();
    results.add(new DefaultSegment(left, top, right, top));
    results.add(new DefaultSegment(right, top, right, bottom));
    results.add(new DefaultSegment(right, bottom, left, bottom));
    results.add(new DefaultSegment(left, bottom, left, top));
    return results;
  }

  /**
   * Gets the shortest link from this envelope to the other coord. P1 is on this
   * envelope. If the coord intersects with this envelope, then this returns the
   * degenerate segment (coord,coord) with distance 0.
   *
   * @param coord The coord to get the shortest link to.
   * @return The shortest link segment.
   */
  @Override
  public final Segment shortestLink2D(Coord coord) {
    if (this.intersects2D(coord)) {
      return new DefaultSegment(new CoordXY(coord), new CoordXY(coord));
    }
    List<Segment> segs = getEdges2D();
    Segment shortest = null;
    for (Segment seg : segs) {
      Segment link = seg.shortestLink(coord);
      if (shortest == null || link.length() < shortest.length()) {
        shortest = link;
      }
    }
    return shortest;
  }

  /**
   * Gets the shortest link from this envelope to the other segment. If the
   * segment intersects, this will return a degenerate segment of length zero
   * using a point within this envelope. If the segment does not intersect, then
   * this will return the shortest segment to that segment, where P1 is a point
   * on this envelope.
   *
   * @param seg The Segment to get the shortest link to.
   * @return The shortest link segment.
   */
  @Override
  public final Segment shortestLink2D(Segment seg) {
    CoordXY p1 = new CoordXY(seg.getP1());
    CoordXY p2 = new CoordXY(seg.getP2());
    Segment seg2D = new DefaultSegment(p1, p2);
    if (this.intersects2D(p1)) {
      return new DefaultSegment(p1, p1);
    }
    if (this.intersects2D(p2)) {
      return new DefaultSegment(p2, p2);
    }
    Optional<Segment> overlap = this.intersection2D(seg2D);
    if (overlap.isPresent()) {
      return new DefaultSegment(overlap.get().getP1(), overlap.get().getP1());
    }
    // there is no overlap, so now we get the min distance to the edges.
    List<Segment> edges = this.getEdges2D();
    Segment shortest = null;
    for (Segment edge : edges) {
      Segment link = edge.shortestLink(seg2D);
      if (shortest == null || link.length() < shortest.length()) {
        shortest = link;
      }
    }
    return shortest;
  }

  /**
   * Gets the shortest link from this envelope to the other segment. If the
   * segment intersects, this will return a degenerate segment of length zero
   * using a point within this envelope.
   *
   * @param env The Envelope to get the shortest link to.
   * @return The shortest link segment. if the envelopes intersect, this will be
   * a degenerate segment of length 0 at some intersecting location.
   */
  @Override
  public final Segment shortestLink2D(Envelope env) {
    Optional<Envelope> overlap = intersection2D(env);
    if (overlap.isPresent()) {
      return new DefaultSegment(overlap.get().getMin(), overlap.get().getMin());
    }
    // if we don't overlap, then return the shortest distance to an edge.
    List<Segment> edges = this.getEdges2D();
    List<Segment> otherEdges = env.getEdges2D();
    Segment shortest = null;
    for (Segment edge : edges) {
      for (Segment otherEdge : otherEdges) {
        Segment link = edge.shortestLink(otherEdge);
        if (shortest == null || link.length() < shortest.length()) {
          shortest = link;
        }
      }
    }
    return shortest;
  }

  /**
   * This returns an optional envelope that is either empty or the intersection
   * envelope.
   *
   * @param env The envelope to intersect with.
   * @return The result.
   */
  @Override
  public final Optional<Envelope> intersection2D(Envelope env) {
    if (!intersects2D(env)) {
      return Optional.empty();
    }
    double minX = Math.max(min.getX(), env.getMin().getX());
    double minY = Math.max(min.getY(), env.getMin().getY());
    double maxX = Math.min(max.getX(), env.getMax().getX());
    double maxY = Math.min(max.getY(), env.getMax().getY());
    Envelope result = new DefaultEnvelope(minX, minY, maxX, maxY);
    return Optional.of(result);
  }

  /**
   * This buffers the envelope in all directions by the specified amount. A
   * negative buffer will contract the envelope.
   *
   * @param distance The distance in projection units.
   * @return The Envelope
   */
  @Override
  public final Envelope buffer(double distance) {
    Envelope result = new DefaultEnvelope();
    result.getMin().setX(min.getX() - distance);
    result.getMax().setX(max.getX() + distance);
    result.getMin().setY(min.getY() - distance);
    result.getMax().setY(max.getY() + distance);
    return result;
  }

  /**
   * This assumes an envelope in WGS84 coordinates, and will buffer the envelope
   * in feet.
   *
   * @param distance The distance in feet.
   * @return The Envelope
   */
  @Override
  public final Envelope bufferInFeet(double distance) {
    Envelope result = new DefaultEnvelope();
    Envelope minEnv = min.bufferInFeet(distance);
    Envelope maxEnv = max.bufferInFeet(distance);
    result.expandToInclude(minEnv);
    result.expandToInclude(maxEnv);
    return result;
  }

  /**
   * Creates child envelopes.
   *
   * @param x The double geographic x location where the envelope will be
   * divided.
   * @param y The double geographic y location where the envelope will be
   * divided.
   * @return A List of four envelopes, or an empty list if this envelope is
   * empty.
   */
  @Override
  public final List<Envelope> partition2D(double x, double y) {

    List<Envelope> result = new ArrayList<>();
    if (this.isEmpty()) {
      return result;
    }
    double x1 = this.getMin().getX();
    double x2 = this.getMax().getX();
    double y1 = this.getMin().getY();
    double y2 = this.getMax().getY();
    result.add(new DefaultEnvelope(x1, y1, x, y));
    result.add(new DefaultEnvelope(x, y1, x2, y));
    result.add(new DefaultEnvelope(x1, y, x, y2));
    result.add(new DefaultEnvelope(x, y, x2, y2));
    return result;
  }

  /**
   * Multiplies the width and height by a factor d and returns a new Envelope.
   * This method does not alter this instance state.
   *
   * @param d
   * @return
   */
  public Envelope multiply(double d) {
    double newWidth = this.getWidth() * d;
    double newHeight = this.getHeight() * d;
    CoordXY center = this.getCenter();
    return new DefaultEnvelope(center, newWidth, newHeight);
  }

  @Override
  public final double distance(Envelope envelope) {
    return this.shortestLink2D(envelope).length();
  }

  public final List<Envelope> multiPartition2D(int numX, int numY) {
    List<Envelope> partitions = new ArrayList<>();
    double dX = this.getWidth() / new Integer(numX).doubleValue();
    double dY = this.getHeight() / new Integer(numY).doubleValue();
    for (int i = 1; i <= numX; i++) {
      for (int j = 1; j <= numY; j++) {
        CoordXY current = CoordMath.add2D(min, new CoordXY(i * dX, j * dY));
        CoordXY previous = CoordMath.add2D(min, new CoordXY((i - 1) * dX, (j - 1) * dY));
        partitions.add(new DefaultEnvelope(previous, current));
      }
    }
    return partitions;
  }

  public static List<Envelope> multiPartition2D(DefaultEnvelope e, int numX, int numY) {
    return e.multiPartition2D(numX, numY);
  }

  public List<CoordXY> asCoordsXY() {
    return Arrays.asList(
        new CoordXY(min),
        new CoordXY(max.getX(), min.getY()),
        new CoordXY(max),
        new CoordXY(min.getX(), max.getY())
    );
  }
}
