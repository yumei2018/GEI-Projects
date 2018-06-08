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
package gov.ca.water.shapelite.map;

import gov.ca.water.shapelite.events.MapEventMouse;
import java.util.List;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.data.marker.PointMarker;

/**
 * This event extends the basic MapEventMouse to also include information about the
 * Tide interpolation.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TideInterpolationEvent extends MapEventMouse {
  
  //<editor-fold defaultstate="collapsed" desc="Fields">
  
  /**
   * The PointMarker that is at the end of the interpolation path.
   */
  private PointMarker end;
  
  /**
   * A boolean that indicates if the start PointMarker is a tide station with harmonic
 constituents calculated for it.
   */
  private boolean endHarmonic;
  
  /**
   * The set of coordinates that define the interpolation path along which the 
   * point is supposed to be interpolated.
   */
  private List<Coord> path;
  
  /**
   * The PointMarker that is at the start of the interpolation path.
   */
  private PointMarker start;

  /**
   * A boolean that indicates if the start PointMarker is a tide station with harmonic
 constituents calculated for it.
   */
  private boolean startHarmonic;
  
  //</editor-fold>
  
  /**
   * Creates a new instance of the joining path event
   * @param source The object that initiated the event.  This should be the Map.
   * @param path The list of lat,long coordinates that make up the drawn path.
   * @param start The PointMarker at the start of the path.
   * @param end The PointMarker at the end of the path.
   */
  public TideInterpolationEvent(Object source, List<Coord> path, PointMarker start,
          PointMarker end, boolean startHarmonic, boolean endHarmonic, MapEventMouse e){
    super(e.getMap(), e);
    this.end = end;
    this.endHarmonic = endHarmonic;
    this.start = start;
    this.startHarmonic = startHarmonic;
    this.path = path;
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
 
  /**
   * Gets the PointMarker that is at the end of the interpolation path.
   * @return the end
   */
  public PointMarker getEnd() {
    return end;
  }

  /**
   * Sets the PointMarker that is at the end of the interpolation path.
   * @param end the end to set
   */
  protected void setEnd(PointMarker end) {
    this.end = end;
  }
  
  /**
   * Gets a boolean that indicates if the start PointMarker is a tide station with harmonic
 constituents calculated for it.
   * @return the endHarmonic
   */
  public boolean isEndHarmonic() {
    return endHarmonic;
  }

  /**
   * Sets a boolean that indicates if the start PointMarker is a tide station with harmonic
 constituents calculated for it.
   * @param endHarmonic the endHarmonic to set
   */
  protected void setEndHarmonic(boolean endHarmonic) {
    this.endHarmonic = endHarmonic;
  }
  
  /**
   * Gets the set of coordinates that define the interpolation path along which the 
   * point is supposed to be interpolated.
   * @return the path
   */
  public List<Coord> getPath() {
    return path;
  }

  /**
   * Sets the set of coordinates that define the interpolation path along which the 
   * point is supposed to be interpolated.
   * @param path the path to set
   */
  protected void setPath(List<Coord> path) {
    this.path = path;
  }

  /**
   * Gets the PointMarker that is at the start of the interpolation path.
   * @return the start
   */
  public PointMarker getStart() {
    return start;
  }

  /**
   * Sets the PointMarker that is at the start of the interpolation path.
   * @param start the start to set
   */
  protected void setStart(PointMarker start) {
    this.start = start;
  }

  /**
   * Gets a boolean that indicates if the start PointMarker is a tide station with harmonic
 constituents calculated for it.
   * @return the startHarmonic
   */
  public boolean isStartHarmonic() {
    return startHarmonic;
  }

  /**
   * Sets a boolean that indicates if the start PointMarker is a tide station with harmonic
 constituents calculated for it.
   * @param startHarmonic the startHarmonic to set
   */
  protected void setStartHarmonic(boolean startHarmonic) {
    this.startHarmonic = startHarmonic;
  }
  
  //</editor-fold>

  
}
