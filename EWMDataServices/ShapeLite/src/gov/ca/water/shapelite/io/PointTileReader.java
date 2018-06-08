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
package gov.ca.water.shapelite.io;

import gov.ca.water.shapelite.FileHelper;
import gov.ca.water.shapelite.data.PointTile;
import gov.ca.water.shapelite.data.ColRow;
import gov.ca.water.shapelite.data.PointTileCell;
import gov.ca.water.shapelite.data.TileUrl;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * <pre>
 *
 * The pattern for each tile is to represent only points that fall in the
 * same grid pattern that would exist for a mercator web image tile.  Instead
 * of a 256 x 256 pattern of elevation values, we will have a 256 x 256 pattern
 * of multi-points.  Each multi-point is compressed to try to minimize the
 * expense for hosting such repetative points.
 *
 * The file header starts with an integer number of cells.
 * NumCells
 *
 * For each cell we store the following:
 * Name - Byte Count - Meaning
 * Row  - 1 - The pixel offset from the top of the tile.
 * Col  - 1 - The pixel offset from the left of this tile.
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
public class PointTileReader {

  /**
   * Full byte.
   */
  private static final int FF = 0xFF;

  /**
   * The size in bytes of the cell header content.
   */
  private static final int CELL_HEADER_SIZE = 10;

  /**
   * The size in bytes of a single coordinate.
   */
  private static final int POINT_SIZE = 4;
  
  /**
   * The size in bytes of an integer. (Integer.BYTES not supported by Java 7)
   */
  private static final int INTEGER_BYTES = 4;

  /**
   * Opens the string filename of the specified file.
   *
   * @param filename The String filename.
   * @return A newly created PointTile class with the points.
   * @throws java.io.FileNotFoundException if the file was not found.
   * @throws IOException if the file could not be read.
   */
  public final PointTile open(String filename)
      throws FileNotFoundException, IOException {
    PointTile result = new PointTile();
    TileUrl url = new TileUrl(filename);
    result.setTileX(url.getTileX());
    result.setTileY(url.getTileY());
    result.setZoomLevel(url.getLevel());
    byte[] data = FileHelper.readAllBytes(filename);
    result.setData(data);

    int numCells = ByteBuffer.wrap(data, 0, INTEGER_BYTES).getInt();
    int offset = INTEGER_BYTES;
    for (int i = 0; i < numCells; i++) {
      PointTileCell cell = new PointTileCell(result);
      ByteBuffer header = ByteBuffer.wrap(data, offset, CELL_HEADER_SIZE);
      cell.setRow(header.get() & FF);
      cell.setCol(header.get() & FF);
      cell.setAverageElevation(header.getInt());
      cell.setNumCoords(header.getInt());
      offset += CELL_HEADER_SIZE;
      cell.setCoordOffset(offset);
      offset += POINT_SIZE * cell.getNumCoords();
      result.getCells().put(new ColRow(cell.getCol(), cell.getRow()), cell);
    }
    return result;
  }
}
