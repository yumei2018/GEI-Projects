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
package gov.ca.water.shapelite.map;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public enum MapTileType {

  /**
   * A regular symbolic cartographic map.
   */
  StreetMap(0),
  /**
   * A Satellite image dataset.
   */
  Satellite(1),
  /**
   * Topographic map with elevation tiles.
   */
  Topo(2);

  /**
   * The integer index of this specific enumeration instance.
   */
  private final int index;

  /**
   * Creates a new instance of a MapTileType enumeration.
   *
   * @param index the integer index to create.
   */
  private MapTileType(int index) {
    this.index = index;
  }

  /**
   * This method will cycle through the enumeration values, getting the next
   * value based on the current value.
   *
   * @return The next MapTileType in the list.
   */
  public MapTileType next() {
    switch (this) {
      case StreetMap:
        return Satellite;
      case Satellite:
        return Topo;
      default:
        return StreetMap;
    }
  }

  /**
   * Given a mathematical index, this gets the best MapTileType.
   *
   * @param index The integer index to parse.
   * @return The MapType represented by the index.
   */
  public static MapTileType from(int index) {
    switch (index) {
      case 1:
        return Satellite;
      case 2:
        return Topo;
      default:
        return StreetMap;
    }
  }

  /**
   * Gets the integer index for this specific enumeration.
   *
   * @return The integer index of this enumeration.
   */
  public int getIndex() {
    return this.index;
  }

}
