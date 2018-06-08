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
package gov.ca.water.shapelite.events;

import gov.ca.water.shapelite.data.marker.PolygonMarker;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

/**
 * The MarkerPathEvent is an event object used during the selection of
 * MarkerPaths, but could be used for any event that needs to pass information
 * about a list of MarkerPath objects.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PolygonMarkerEvent extends EventObject {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The list of MarkerPath objects involved in this event.
   */
  private List<PolygonMarker> markers;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of the MarkerPathEvent class.
   *
   * @param source The object source of the event.
   * @param markers The list of markers involved in the event.
   */
  public PolygonMarkerEvent(Object source, List<PolygonMarker> markers) {
    super(source);
    this.markers = markers;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the list of PolygonMarker objects involved in this event.
   *
   * @return the marker Polygons
   */
  public final List<PolygonMarker> getMarkerPaths() {
    return this.markers;
  }

  /**
   * Sets the list of PolygonMarker objects involved in this event.
   *
   * @param markers the markerPolygon to set
   */
  protected final void setMarkerPaths(List<PolygonMarker> markers) {
    this.markers = markers;
  }

  //</editor-fold>

    /**
   * This class is used to define the listener interface for when MarkerPaths are
   * selected.
   *
   * @author Harold A. Dunsford Jr. Ph.D.
   */
  public interface Listener extends EventListener {

    /**
     * Occurs when markers are selected or unselected. The markers listed are
     * changing their state, but could be turning on or off.
     *
     * @param e The PolygonMarkerEvent object with event information.
     */
    void markerPolygonSelectionChanged(PolygonMarkerEvent e);

    /**
     * Occurs when a click occurs on a particular marker.
     *
     * @param e The PolygonMarkerEvent object with event information.
     */
    void markerPolygonClicked(PolygonMarkerEvent e);
  }
}
