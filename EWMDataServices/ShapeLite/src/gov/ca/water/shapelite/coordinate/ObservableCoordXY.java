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
import gov.ca.water.shapelite.NonNan;
import gov.ca.water.shapelite.Nullable;
import gov.ca.water.shapelite.events.CoordChangedEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ObservableCoordXY extends CoordXY implements ObservableCoord {





  //<editor-fold defaultstate="collapsed" desc="CoordChangedEvent">
  /**
   * Creates a new instance of an empty XY coordinate.
   */
  public ObservableCoordXY() {
  }

  /**
   * Initializes a new instance of the Coordinate class.
   *
   * @param x the horizontal x coordinate.
   * @param y the vertical y coordinate.
   */
  public ObservableCoordXY(@NonNan double x, @NonNan double y) {
    if (Double.isNaN(x) || Double.isNaN(y)) {
      throw new IllegalArgumentException("x and y cannot be NaN.");
    }
    this.setX(x);
    this.setY(y);
  }

  /**
   * Initializes a new instance of the Coordinate class.
   *
   * @param other the other coordinate to copy values from. If this is null, an
   * Empty coordinate is created.
   */
  public ObservableCoordXY(@Nullable Coord other) {
    if (other == null) {
      return; // no need to copy values, and hasValue should remain false.
    }
    this.setX(other.getX());
    this.setY(other.getY());
  }

  /**
   * Creates a new Coord object from a JTS Coordinate.
   *
   * @param c The JTS Coordinate, which cannot support M values. If this is
   * null, then an empty coordinate will be created.
   * @throws IllegalArgumentException if either c.x or c.y is NaN.
   */
  public ObservableCoordXY(Coordinate c) {
    if (c == null) {
      return; // no need to define coordinates.
    }
    if (Double.isNaN(c.x) || Double.isNaN(c.y)) {
      throw new IllegalArgumentException("NaN states are not allowed "
          + "in coord classes.");
    }
    this.setX(c.x);
    this.setY(c.y);
  }

  /**
   * The list of listeners.
   */
  private final transient List<CoordChangedEvent.Listener> coordChangeListeners = new ArrayList<>();

  /**
   * Adds the specified listener to the list of listeners to be notified during
   * an event. If the item is already in the list, it will not be added a second
   * time.
   *
   * @param listener The CoordChangedEvent.Listener to connect.
   */
  @Override
  public final void addCoordChangedListener(CoordChangedEvent.Listener listener) {
    if (!coordChangeListeners.contains(listener)) {
      coordChangeListeners.add(listener);
    }
  }

  /**
   * Removes the specified listener from the list if it is in the list.
   *
   * @param listener The CoordChangedEvent.Listener to disconnect.
   */
  @Override
  public final void removeCoordChangedListener(CoordChangedEvent.Listener listener) {
    coordChangeListeners.remove(listener);
  }

  /**
   * Fires the CoordChanged event and notifies each of the listeners.
   *
   * @param e A {EventType}Event with the source object and any properties
   * associated with this event.
   */
  @Override
  public final void fireCoordChanged(CoordChangedEvent e) {
    for (CoordChangedEvent.Listener listener : coordChangeListeners) {
      listener.coordChanged(e);
    }
  }

  //</editor-fold>
  /**
   * Overrides the default empty case.
   */
  @Override
  protected final void onChanged() {
    fireCoordChanged(new CoordChangedEvent(this, this));
  }

}
