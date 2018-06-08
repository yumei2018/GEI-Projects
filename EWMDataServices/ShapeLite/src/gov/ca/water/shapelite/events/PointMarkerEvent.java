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

import gov.ca.water.shapelite.data.marker.PointMarker;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

/**
 * This class is an event object for handling events that act on a list of
 * markers.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PointMarkerEvent extends EventObject {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The list of markers involved in this event.
   */
  private List<PointMarker> markers;

  //</editor-fold>
  /**
   * Creates a new instance of a MarkerEvent.
   *
   * @param source The object that was the source of the event.
   * @param markers The list of markers involved in the event.
   */
  public PointMarkerEvent(Object source, List<PointMarker> markers) {
    super(source);
    this.markers = markers;
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the list of markers involved in this event.
   *
   * @return the markers.
   */
  public final List<PointMarker> getMarkers() {
    return markers;
  }

  /**
   * Sets the list of markers involved in this event.
   *
   * @param markers the markers to set.
   */
  protected final void setMarkers(List<PointMarker> markers) {
    this.markers = markers;
  }

  //</editor-fold>
  /**
   * This class is used to define the interface for when Markers are selected,
   * or the selection is inverted.
   *
   * @author Harold A. Dunsford Jr. Ph.D.
   */
  public interface Listener extends EventListener {

    /**
     * Occurs when markers are selected or unselected. The markers listed are
     * changing their state, but could be turning on or off.
     *
     * @param e The PointMarkerEvent object with event information.
     */
    void markerSelectionChanged(PointMarkerEvent e);

    /**
     * Occurs when a click occurs on a particular marker.
     *
     * @param e The PointMarkerEvent object with event information.
     */
    void markerClicked(PointMarkerEvent e);
  }
}
