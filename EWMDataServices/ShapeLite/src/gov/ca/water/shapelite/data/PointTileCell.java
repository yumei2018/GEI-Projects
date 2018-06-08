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
package gov.ca.water.shapelite.data;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.analysis.TileLocation;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.map.Mercator;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PointTileCell implements Serializable {

  /**
   * Full byte.
   */
  private static final int FF = 0xFF;

  /**
   * A multiplier factor for storing doubles as integers.
   */
  private static final double BYTE_SIZE = 256.0;

  /**
   * A multiplier for elevation to get it's precision to .01 ft.
   */
  private static final double EXTRUSION = 100.0;

  /**
   * The number of bytes for a single compressed coordinate.
   */
  private static final int COORD_SIZE = 4;

  // <editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The parent for this PointTileCell.
   */
  private PointTile parent;

  /**
   * The integer vertical y position from the top to bottom of the cell in the
   * tile, from 0 to 256.
   */
  private int row;

  /**
   * The integer horizontal x position from left to right of the cell in the
   * tile, from 0 to 256.
   */
  private int col;
  /**
   * The average elevation of all the points in this cell in 1/100 of a foot, so
   * this number must be divided by 100 to get the feet.
   */
  private int averageElevation;
  /**
   * The number of points in this cell.
   */
  private int numCoords;
  /**
   * The list of actual, fully described CoordZ objects for this cell.
   */
  private final List<CoordZ> coords;

  /**
   * The byte offset in the data array where the coordinates begin.
   */
  private int coordOffset;

  // </editor-fold>
  /**
   * The point tile cell. If the coords is empty but numCoords is greater than
   * 0, then the cell has not been read yet.
   *
   * @param parent The owning parent of this cell.
   */
  public PointTileCell(PointTile parent) {
    coords = new ArrayList<>();
    this.parent = parent;
  }

  /**
   * Instructs this PointTileCell to read the coordinates from the data array in
   * its parent PointTile.
   */
  public final void readCoords() {
    if (!parent.getData().isPresent()) {
      return;
    }
    Coord topLeft = TileUrl.getTopLeftMercator(parent.getZoomLevel(),
        new TileLocation(parent.getTileX(), parent.getTileY(), row, col));
    ByteBuffer stream = ByteBuffer.wrap(parent.getData().get(), coordOffset,
        numCoords * COORD_SIZE);
    double cellSize = TileUrl.getCellSize(parent.getZoomLevel());
    for (int i = 0; i < numCoords; i++) {
      int dx = stream.get() & FF;
      int dy = stream.get() & FF;
      int dz = stream.getShort();
      CoordZ coord = new CoordZ();
      coord.setX(topLeft.getX() + dx * cellSize / BYTE_SIZE);
      coord.setY(topLeft.getY() - dy * cellSize / BYTE_SIZE);
      coord.setZ((double) (dz + averageElevation) / EXTRUSION);
      coords.add(Mercator.fromMerc(coord));
    }
  }

  /**
   * Gets a Coordinate representing the top left of the cell in Mercator
   * coordinates.
   *
   * @return The Coordinate.
   */
  public final Coord getTopLeftMercator() {
    return TileUrl.getTopLeftMercator(parent.getZoomLevel(),
        new TileLocation(parent.getTileX(), parent.getTileY(), row, col));
  }

  /**
   * Gets the cell size in mercator coordinates of this cell.
   *
   * @return The cell size in mercator.
   */
  public final double cellSizeMercator() {
    return TileUrl.getCellSize(parent.getZoomLevel());
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the coords
   */
  public final List<CoordZ> getCoords() {
    return coords;
  }

  /**
   * Gets the integer vertical y position from the top to bottom of the cell in
   * the tile, from 0 to 256.
   *
   * @return the row
   */
  public final int getRow() {
    return row;
  }

  /**
   * Gets the integer vertical y position from the top to bottom of the cell in
   * the tile, from 0 to 256.
   *
   * @param row the row to set
   */
  public final void setRow(int row) {
    this.row = row;
  }

  /**
   * Gets the integer horizontal x position from left to right of the cell in
   * the tile, from 0 to 256.
   *
   * @return the col
   */
  public final int getCol() {
    return col;
  }

  /**
   * Sets the integer horizontal x position from left to right of the cell in
   * the tile, from 0 to 256.
   *
   * @param col the col to set
   */
  public final void setCol(int col) {
    this.col = col;
  }

  /**
   * Gets the average elevation of all the points in this cell in 1/100 of a
   * foot, so this number must be divided by 100 to get the feet.
   *
   * @return the averageElevation
   */
  public final int getAverageElevation() {
    if (coords.isEmpty()) {
      return averageElevation;
    } else {
      double total = 0;
      int count = 0;
      for (CoordZ coord : coords) {
        total += coord.getZ();
        count++;
      }
      return (int) ((EXTRUSION * total) / count);
    }

  }

  /**
   * Sets the average elevation of all the points in this cell in 1/100 of a
   * foot, so this number must be divided by 100 to get the feet.
   *
   * @param averageElevation the averageElevation to set
   */
  public final void setAverageElevation(int averageElevation) {
    this.averageElevation = averageElevation;
  }

  /**
   * Gets the number of points in this cell. If the coords have not been read
   * yet, this returns the count from the header. Otherwise, it returns the size
   * of the array.
   *
   *
   * @return the numCoords
   */
  public final int getNumCoords() {
    if (coords.isEmpty()) {
      return numCoords;
    } else {
      return coords.size();
    }
  }

  /**
   * Sets the number of points in this cell.
   *
   * @param numCoords the numCoords to set
   */
  public final void setNumCoords(int numCoords) {
    this.numCoords = numCoords;
  }

  /**
   * Gets the byte offset in the data array where the coordinates begin.
   *
   * @return the coordOffset
   */
  public final int getCoordOffset() {
    return coordOffset;
  }

  /**
   * Sets the byte offset in the data array where the coordinates begin.
   *
   * @param coordOffset the coordOffset to set
   */
  public final void setCoordOffset(int coordOffset) {
    this.coordOffset = coordOffset;
  }

  /**
   * Gets a diagnostic string representing the cell location and the number of
   * coordinates.
   *
   * @return The string for this cell.
   */
  @Override
  public final String toString() {
    return "Cell(" + col + ", " + row + ", " + getAverageElevation() + ") #"
        + getNumCoords();
  }

  // </editor-fold>
}
