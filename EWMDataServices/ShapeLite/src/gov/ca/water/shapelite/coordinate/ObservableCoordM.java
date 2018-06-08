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

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Nullable;
import gov.ca.water.shapelite.events.CoordChangedEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ObservableCoordM extends CoordM implements ObservableCoord {

  //<editor-fold defaultstate="collapsed" desc="CoordChangedEvent">

  /**
   * Creates a new instance of a CoordM class where the hasValue member is set
   * to false.
   */
  public ObservableCoordM() {
  }

  /**
   * Creates a new instance of a CoordM given the x, y and m values.
   *
   * @param x The horizontal coordinate or longitude.
   * @param y The vertical coordinate or latitude.
   * @param m A measure value.
   */
  public ObservableCoordM(double x, double y, double m) {
    if (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(m)) {
      throw new IllegalArgumentException("x, y and m cannot be NaN.");
    }
    this.setX(x);
    this.setY(y);
    this.setM(m);
  }

  /**
   * Initializes a new instance of the Coordinate class.
   *
   * @param other the other coordinate to copy values from. If this is null, an
   * Empty coordinate is created.
   */
  public ObservableCoordM(@Nullable Coord other) {
    if (other == null) {
      return; // no need to copy values, and hasValue should remain false.
    }
    copyProperties(other);
  }



  /**
   * The list of listeners.  This should not be serialized.
   */
  private transient final List<CoordChangedEvent.Listener>
      coordChangeListeners = new ArrayList<>();

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


}
