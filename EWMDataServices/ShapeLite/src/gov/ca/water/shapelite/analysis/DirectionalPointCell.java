/*
 * The MIT License
 *
 * Copyright 2014 hdunsford.
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
package gov.ca.water.shapelite.analysis;

import gov.ca.water.shapelite.Coord;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DirectionalPointCell {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The coord location.
   */
  private Coord location;

  /**
   * The list of directional points for this cell.
   */
  private List<DirectionalPoint> points;

  //</editor-fold>
  /**
   * Creates a new instance of a DirectionalPointCell.
   *
   * @param location The location of the cell.
   */
  public DirectionalPointCell(Coord location) {
    this.location = location;
    points = new ArrayList<>();
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the Coord location of the cell.
   *
   * @return the location
   */
  public final Coord getLocation() {
    return location;
  }

  /**
   * Sets the Coord location of the cell.
   *
   * @param location the location to set
   */
  public final void setLocation(Coord location) {
    this.location = location;
  }

  //</editor-fold>
  /**
   * Gets the list of DirectionalPoint objects for this cell.
   *
   * @return the points
   */
  public final List<DirectionalPoint> getPoints() {
    return points;
  }

  /**
   * Sets the list of DirectionalPoint objects for this cell.
   *
   * @param points the points to set
   */
  public final void setPoints(List<DirectionalPoint> points) {
    this.points = points;
  }

}
