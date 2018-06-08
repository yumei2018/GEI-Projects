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

import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Levee {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The geographic extents for this levee structure.
   */
  private Envelope bounds;

  /**
   * The levee inspection segment code.
   */
  private String lisCode;

  /**
   * The list of markers indicating mile positions along the levee or line.
   */
  private List<MileMarker> markers;

  /**
   * The String name identifying this particular levee.
   */
  private String name;

  /**
   * The data structure organizing the parts into a tree for getting levee mile
   * distances.
   */
  private PartTree parts;

  /**
   * The Coordinate starting point for this levee. This controls the side of the
   * levee where distance calculations should begin.
   */
  private Coord start;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the geographic extents for this levee structure.
   *
   * @return the bounds.
   */
  public final Envelope getBounds() {
    return bounds;
  }

  /**
   * Sets the geographic extents for this levee structure.
   *
   * @param bounds the bounds to set.
   */
  public final void setBounds(Envelope bounds) {
    this.bounds = bounds;
  }

  /**
   * Gets the levee inspection segment code.
   *
   * @return the lisCode.
   */
  public final String getLisCode() {
    return lisCode;
  }

  /**
   * Sets the levee inspection segment code.
   *
   * @param lisCode the lisCode to set.
   */
  public final void setLisCode(String lisCode) {
    this.lisCode = lisCode;
  }

  /**
   * Gets the list of markers indicating mile positions along the levee or line.
   *
   * @return the markers.
   */
  public final List<MileMarker> getMarkers() {
    return markers;
  }

  /**
   * Sets the list of markers indicating mile positions along the levee or line.
   *
   * @param markers the markers to set.
   */
  public final void setMarkers(List<MileMarker> markers) {
    this.markers = markers;
  }

  /**
   * Gets the String name identifying this particular levee.
   *
   * @return the name.
   */
  public final String getName() {
    return name;
  }

  /**
   * Sets the String name identifying this particular levee.
   *
   * @param name the name to set.
   */
  public final void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the data structure organizing the parts into a tree for getting levee
   * mile distances.
   *
   * @return the parts.
   */
  public final PartTree getParts() {
    return parts;
  }

  /**
   * Sets the data structure organizing the parts into a tree for getting levee
   * mile distances.
   *
   * @param parts the parts to set.
   */
  public final void setParts(PartTree parts) {
    this.parts = parts;
  }

  /**
   * Gets the Coordinate starting point for this levee. This controls the side
   * of the levee where distance calculations should begin.
   *
   * @return the start.
   */
  public final Coord getStart() {
    return start;
  }

  /**
   * Sets the Coordinate starting point for this levee. This controls the side
   * of the levee where distance calculations should begin.
   *
   * @param start the start to set.
   */
  public final void setStart(Coord start) {
    this.start = start;
  }

  /**
   * Gets the length of the longest path.
   *
   * @return the double length.
   */
  public final double length() {
    return this.parts.length();
  }

  //</editor-fold>
}
