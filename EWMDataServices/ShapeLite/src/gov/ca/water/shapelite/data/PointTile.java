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
package gov.ca.water.shapelite.data;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Nullable;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.analysis.TileLocation;
import gov.ca.water.shapelite.coordinate.CoordZ;
import static gov.ca.water.shapelite.data.TileUrl.LL_MAX;
import static gov.ca.water.shapelite.data.TileUrl.LL_SPAN;
import static gov.ca.water.shapelite.data.TileUrl.TILE_SIZE;
import gov.ca.water.shapelite.map.Mercator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *  * The file header starts with an integer number of cells.
 * <pre>
 * NumCells
 *
 * For each cell we store the following:
 * Name - Byte Count - Meaning
 * Row  - 1 - The pixel offset from the left of the tile.
 * Col  - 1 - The pixel offset from the top of this tile.
 * Avg  - 4 - The integer average Z value for this cell.
 * Count- 4 - The number of multi-points.
 * -- the following are repeated [Count] times.
 * Xi   - 1 - The x position with a precision of the cell size / 256.
 * Yi   - 1 - The y posisiton with a precision of the cell size / 256.
 * Zi   - 2 - The short difference between
 *
 * </pre>
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PointTile {

  /**
   * The integer zoomLevel.
   */
  private int zoomLevel;

  /**
   * The integer tileX.
   */
  private int tileX;

  /**
   * The integer tileY.
   */
  private int tileY;

  /**
   * The list of coordinates, organized according to grid offset.
   */
  private final HashMap<ColRow, PointTileCell> cells;

  /**
   * A boolean that tracks whether the data has been changed since it was read.
   */
  private boolean changed;

  /**
   * The byte array representing the original compressed bytes from the file.
   */
  private byte[] data;

  /**
   * Creates a new instance of the PointTile class.
   *
   * @param url The TileUrl scale, tileX and TileY to be represented.
   */
  public PointTile(TileUrl url) {
    cells = new HashMap<>();
    this.zoomLevel = url.getLevel();
    this.tileX = url.getTileX();
    this.tileY = url.getTileY();
  }

  /**
   * Creates a new instance of the PointTile class.
   */
  public PointTile() {
    cells = new HashMap<>();
  }

  /**
   * Gets the WGS84 envelope for this tile.
   *
   * @return The Envelope in WGS84 coordinates.
   */
  public final Envelope getEnvelope() {
    return Mercator.fromMerc(TileUrl.getTileEnvelopeMercator(zoomLevel, tileX, tileY));
  }

  /**
   * Gets the WGS84 envelope for this tile.
   *
   * @return The Envelope in WGS84 coordinates.
   */
  public final Envelope getEnvelopeMercator() {
    return TileUrl.getTileEnvelopeMercator(zoomLevel, tileX, tileY);
  }

  /**
   * @return the zoomLevel
   */
  public final int getZoomLevel() {
    return zoomLevel;
  }

  /**
   * @param scale the zoomLevel to set
   */
  public final void setZoomLevel(int scale) {
    this.zoomLevel = scale;
  }

  /**
   * Gets the cells.
   *
   * @return the cells
   */
  public final HashMap<ColRow, PointTileCell> getCells() {
    return cells;
  }

  /**
   * Gets the coordinates for the specified row and column position. If there
   * are no points at that location, this returns an empty list.
   *
   * @param rc The row column index.
   * @return A list of CoordZ objects that might be empty, but won't be null.
   * This is not the same list object that is in the hashmap.
   */
  public final List<CoordZ> getCoords(ColRow rc) {
    List<CoordZ> results = new ArrayList<>();
    if (cells.containsKey(rc)) {
      PointTileCell cell = cells.get(rc);
      if (cell.getNumCoords() > 0 && cell.getCoords().isEmpty()) {
        cell.readCoords();
      }
      results.addAll(cell.getCoords());
    }
    return results;
  }

  /**
   * This will automatically create a cell if one doesn't exist and will add the
   * coordinates to the cell. This will not remove coordinates if the cell
   * already has coordinates.
   *
   * @param index the Row and Column index.
   * @param coords The Coordinates to add.
   */
  public final void addCoords(ColRow index, List<CoordZ> coords) {
    PointTileCell cell;
    if (cells.containsKey(index)) {
      cell = cells.get(index);
    } else {
      cell = new PointTileCell(this);
      cell.setRow(index.getRow());
      cell.setCol(index.getColumn());
      cells.put(index, cell);
    }
    cell.getCoords().addAll(coords);
  }

  /**
   * This will automatically create a cell if one doesn't exist and will add the
   * coordinates to the cell. This will not remove coordinates if the cell
   * already has coordinates.
   *
   * @param index the Row and Column index.
   * @param coords The Coordinates to add.
   */
  public final void addCoords(ColRow index, CoordZ... coords) {
    PointTileCell cell;
    if (cells.containsKey(index)) {
      cell = cells.get(index);
    } else {
      cell = new PointTileCell(this);
      cell.setRow(index.getRow());
      cell.setCol(index.getColumn());
    }
    cell.getCoords().addAll(Arrays.asList(coords));
  }

  /**
   * Adds the coordinates in coords. This will consider each coordinate. If the
   * coordinate is not in the tile, it will be skipped. It will automatically
   * determine the row and column index for the tile based on the zoomScale of
   * the tile, so be sure that the zoomScale, tileX and tileY are correctly
   * configured before calling this method.
   *
   * @param coords The array, or coordinate parameters to add in WGS84
   * coordinates.
   */
  public final void addCoords(CoordZ... coords) {
    addCoords(Arrays.asList(coords));
  }

  /**
   * Adds the coordinates in the list. This will consider each coordinate. If
   * the coordinate is not in the tile, it will be skipped. It will
   * automatically determine the row and column index for the tile based on the
   * zoomScale of the tile, so be sure that the zoomScale, tileX and tileY are
   * correctly configured before calling this method.
   *
   * @param coords The list of coordinates to add in WGS84 coordinates.
   */
  public final void addCoords(List<CoordZ> coords) {
    for (CoordZ coord : coords) {
      TileLocation loc = TileUrl.getTileLocation(zoomLevel, coord);
      if (loc.getTileX() != tileX || loc.getTileY() != tileY) {
        continue;
      }
      ColRow rc = new ColRow(loc.getCol(), loc.getRow());
      addCoords(rc, coords);
    }
  }

  /**
   * Adds the coordinates in coords. This will consider each coordinate. If the
   * coordinate is not in the tile, it will be skipped. It will automatically
   * determine the row and column index for the tile based on the zoomScale of
   * the tile, so be sure that the zoomScale, tileX and tileY are correctly
   * configured before calling this method.
   *
   * @param coords The array, or coordinate parameters to add in Mercator
   * coordinates.
   */
  public final void addCoordsMercator(CoordZ... coords) {
    addCoords(Arrays.asList(coords));
  }

  /**
   * Adds the coordinates in the list. This will consider each coordinate. If
   * the coordinate is not in the tile, it will be skipped. It will
   * automatically determine the row and column index for the tile based on the
   * zoomScale of the tile, so be sure that the zoomScale, tileX and tileY are
   * correctly configured before calling this method.
   *
   * @param coords The list of coordinates to add in Mercator coordinates.
   */
  public final void addCoordsMercator(List<CoordZ> coords) {
    for (CoordZ coord : coords) {
      TileLocation loc = TileUrl.getTileLocationMercator(zoomLevel, coord);
      if (loc.getTileX() != tileX || loc.getTileY() != tileY) {
        continue;
      }
      ColRow rc = new ColRow(loc.getCol(), loc.getRow());
      addCoords(rc, coords);
    }
  }

  /**
   * This removes the cell from the specified row and column index.
   *
   * @param index The index to remove the cell from.
   */
  public final void removeCell(ColRow index) {
    if (cells.containsKey(index)) {
      cells.remove(index);
    }
  }

  /**
   * Gets the integer count of the cells that are not currently filled.
   *
   * @return The integer number of cells.
   */
  public final int getNumCells() {
    int count = 0;
    for (PointTileCell cell : cells.values()) {
      if (cell.getNumCoords() > 0) {
        count++;
      }
    }
    return count;
  }

  /**
   * Rather than forcing the byte parsing, this returns 0 if the cell is
   * missing, otherwise, it returns the getNumCoords, as long as the coords
   * array is empty. Otherwise it returns the size of the coords array.
   *
   * @param index The Column and Row index of hte cell to check.
   * @return The integer number of coords.
   */
  public final int getNumCoords(ColRow index) {
    if (!cells.containsKey(index)) {
      return 0;
    }
    PointTileCell cell = cells.get(index);
    return cell.getNumCoords();
  }

  /**
   * Gets the row and column for the point, guaranteeing that it will be a cell
   * in this tile. If it is beyond an edge in any direction, then the edge cell
   * will be returned.
   *
   * @param latLong A Coordinate in the WGS84 coordinate system.
   * @return The ColRow indices.
   */
  public final ColRow getColRow(Coord latLong) {
    Coord merc = Mercator.toMerc(latLong);
    return getColRowMercator(merc);
  }

  /**
   * Gets the row and column for the point, guaranteeing that it will be a cell
   * in this tile. If it is beyond an edge in any direction, then the edge cell
   * will be returned.
   *
   * @param mercatorCoord A Coordinate in the Map Mercator coordinate system.
   * @return The ColRow indices.
   */
  public final ColRow getColRowMercator(Coord mercatorCoord) {
    // this figures out the row and column offset.
    double tx = tileX / Math.pow(2, zoomLevel) * LL_SPAN - LL_MAX;
    double size = LL_SPAN / Math.pow(2, zoomLevel);
    int col = (int) (TILE_SIZE * (mercatorCoord.getX() - tx) / size);
    double ty = LL_MAX - tileY / Math.pow(2, zoomLevel) * LL_SPAN;
    int row = (int) (TILE_SIZE * (ty - mercatorCoord.getY()) / size);
    if (row < 0) {
      row = 0;
    }
    if (row >= TILE_SIZE) {
      row = TILE_SIZE - 1;
    }
    if (col < 0) {
      col = 0;
    }
    if (col > TILE_SIZE) {
      col = TILE_SIZE - 1;
    }
    ColRow result = new ColRow(col, row);
    return result;
  }

  /**
   * This method checks the counts of all row/columns to determine if it has
   * cell values that are not empty.
   *
   * @return Boolean, only true if no cell has points.
   */
  public final boolean isEmpty() {
    for (int row = 0; row < TILE_SIZE; row++) {
      for (int col = 0; col < TILE_SIZE; col++) {
        ColRow index = new ColRow(col, row);
        if (cells.containsKey(index)) {
          PointTileCell cell = cells.get(index);
          if (cell.getNumCoords() > 0) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * @return the tileX
   */
  public final int getTileX() {
    return tileX;
  }

  /**
   * @param tileX the tileX to set
   */
  public final void setTileX(int tileX) {
    this.tileX = tileX;
  }

  /**
   * @return the tileY
   */
  public final int getTileY() {
    return tileY;
  }

  /**
   * @param tileY the tileY to set
   */
  public final void setTileY(int tileY) {
    this.tileY = tileY;
  }

  /**
   * Gets the byte array representing the original compressed bytes from the
   * file.
   *
   * @return the data, can be empty.
   */
  public final Optional<byte[]> getData() {
    return Optional.ofNullable(data);
  }

  /**
   * Sets the byte array representing the original compressed bytes from the
   * file.
   *
   * @param data the data to set, can be null.
   */
  public final void setData(@Nullable byte[] data) {
    this.data = data;
  }

  /**
   * Gets a boolean that tracks whether the data has been changed since it was
   * read.
   *
   * @return the changed
   */
  public final boolean isChanged() {
    return changed;
  }

}
