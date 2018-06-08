/*
 * The MIT License
 *
 * Copyright 2015 Harold A. Dunsford Jr. Ph.D..
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
import gov.ca.water.shapelite.data.TileUrl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TileHash<T extends Coord> {

  /**
   * The Defualt level for the tile hash.
   */
  private static final int DEFAULT_LEVEL = 18;

  /**
   * the points list.
   */
  private final HashMap<TileLocation, List<T>> points;

  /**
   * The zoom level on the tiles.
   */
  private final int level;

  /**
   * Creates a new instance of the TileHash.
   */
  public TileHash() {
    points = new HashMap<>();
    level = DEFAULT_LEVEL;
  }

  /**
   * Creates a new instance of the TileHash.
   *
   * @param level the zoom level.
   */
  public TileHash(int level) {
    points = new HashMap<>();
    this.level = level;
  }

  /**
   * Clears the points.
   */
  public final void clear() {
    points.clear();
  }

  /**
   * Adds the list of coordinates to the TileHash.
   *
   * @param coords the list of coords to add.
   */
  public final void add(List<T> coords) {
    for (T coord : coords) {
      TileLocation key = TileUrl.getTileLocation(level, coord);
      if (!points.containsKey(key)) {
        points.put(key, new ArrayList<T>());
      }
      points.get(key).add(coord);
    }
  }

  /**
   * Adds the list of coordinates to the TileHash.
   *
   * @param coord the coord to add.
   */
  public final void add(T coord) {
    TileLocation key = TileUrl.getTileLocation(level, coord);
    if (!points.containsKey(key)) {
      points.put(key, new ArrayList<T>());
    }
    points.get(key).add(coord);
  }

  /**
   * Using tileX, tileY, row and column, this will return the list of points
   * assigned to that location.
   *
   * @param tileX The tileX.
   * @param tileY The tileY.
   * @param row The row.
   * @param col The column.
   * @return The list of coordinates.
   */
  public final List<T> get(int tileX, int tileY, int row, int col) {
    TileLocation loc = new TileLocation(tileX, tileY, row, col);
    return get(loc);
  }

  /**
   * Gets the list of coordinates at the specified location or an empty list.
   *
   * @param loc The location.
   * @return The list of coords. This may be empty but never null.
   */
  public final List<T> get(TileLocation loc) {
    List<T> results = new ArrayList<>();
    if (points.containsKey(loc)) {
      results.addAll(points.get(loc));
    }
    return results;
  }

  /**
   * Gets the points.
   *
   * @return the points
   */
  public final HashMap<TileLocation, List<T>> getPoints() {
    return points;
  }

}
