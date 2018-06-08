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
package gov.ca.water.shapelite.analysis;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.NonNull;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CoordIndex {

  /**
   * The Coord coordinate.
   */
  private Coord coord;

  /**
   * The integer index in the part for this Coord.
   */
  private int index;

  /**
   * Creates a new CoordIndex class.
   *
   * @param coord The coordinate to use.
   * @param index The integer index in the part.
   */
  public CoordIndex(@NonNull Coord coord, int index) {
    if (coord == null) {
      throw new IllegalArgumentException("Argument coord cannot be null.");
    }
    this.coord = coord;
    this.index = index;
  }

  /**
   * Gets the Coord coordinate.
   *
   * @return the coord
   */
  public final Coord getCoord() {
    return coord;
  }

  /**
   * Sets the Coord coordinate.
   *
   * @param coord the coord to set
   */
  public final void setCoord(@NonNull Coord coord) {
    if (coord == null) {
      throw new IllegalArgumentException("Argument coord cannot be null.");
    }
    this.coord = coord;
  }

  /**
   * Gets the Coord coordinate.
   *
   * @return the index
   */
  public final int getIndex() {
    return index;
  }

  /**
   * Sets the Coord coordinate.
   *
   * @param index the index to set
   */
  public final void setIndex(int index) {
    this.index = index;
  }
}
