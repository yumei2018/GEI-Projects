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

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.data.PointTile;
import gov.ca.water.shapelite.data.ColRow;
import gov.ca.water.shapelite.data.PointTileCell;
import gov.ca.water.shapelite.data.TileUrl;
import gov.ca.water.shapelite.map.Mercator;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * <pre>
 *
 * The pattern for each tile is to represent only points that fall in the
 * same grid pattern that would exist for a mercator web image tile.  Instead
 * of a 256 x 256 pattern of elevation values, we will have a 256 x 256 pattern
 * of multi-points.  Each multi-point is compressed to try to minimize the
 * expense for hosting such repetative points.
 * For each cell we store the following:
 *
 * The file header starts with an integer number of cells.
 * NumCells - 4 - the integer number or cells storing values.
 *
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
public class PointTileWriter {

  /**
   * The integer tile size.
   */
  private static final int TILE_SIZE = 256;

  /**
   * A multiplier factor for storing doubles as integers.
   */
  private static final int BYTE_SIZE = 256;

  /**
   * A multiplier for elevation to get it's precision to .01 ft.
   */
  private static final int EXTRUSION = 100;

  /**
   * Writes the data from the point tile to the specified base bath, which
   * should not include scale, tileX or tileY information.
   *
   * @param tile The PointTile of coordinates to compress and store.
   * @param basePath The base path to store.
   * @throws java.io.FileNotFoundException If permission could not be
   * established to write to the specified file.
   * @throws IOException If there was an error writing the file.
   */
  public final void write(PointTile tile, String basePath)
      throws FileNotFoundException, IOException {
    if (tile.getCells().isEmpty()) {
      return;
    }
    int numCells = getNumCells(tile);

    String filename = basePath + File.separator
        + tile.getZoomLevel() + File.separator
        + tile.getTileX() + File.separator
        + tile.getTileY() + ".pts";
    File f = new File(filename);
    if (f.exists()) {
      f.delete();
    }
    File dir = f.getParentFile();
    if (!dir.exists()) {
      dir.mkdirs();
    }

    try (DataOutputStream out = new DataOutputStream(new FileOutputStream(filename))) {
      out.writeInt(numCells);
      double mercCellSize = TileUrl.getCellSize(tile.getZoomLevel());
      for (PointTileCell cell : tile.getCells().values()) {
        if (cell.getNumCoords() == 0) {
          continue;
        }
        int row = cell.getRow();
        int col = cell.getCol();
        ColRow rc = new ColRow(col, row);
        Coord tl = cell.getTopLeftMercator();
        List<CoordZ> coords = tile.getCoords(rc);
        if (coords.isEmpty()) {
          continue;
        }
        double total = 0;
        int count = 0;
        for (CoordZ coord : coords) {
          total += coord.getZ();
          count++;
        }
        int avg = (int) Math.round((EXTRUSION * total / count));
        out.writeByte((byte) row);
        out.writeByte((byte) col);
        out.writeInt(avg);
        out.writeInt(coords.size());

        for (CoordZ coord : coords) {
          CoordZ merc = Mercator.toMerc(coord);
          int x = (int) (BYTE_SIZE * (merc.getX() - tl.getX()) / mercCellSize);
          out.writeByte(x);
          int y = (int) (BYTE_SIZE * (tl.getY() - merc.getY()) / mercCellSize);
          out.writeByte(y);
          int z = (int) Math.round(EXTRUSION * coord.getZ() - avg);
          if (z > Short.MAX_VALUE) {
            z = Short.MAX_VALUE;
          }
          if (x < Short.MIN_VALUE) {
            z = Short.MIN_VALUE;
          }
          out.writeShort(z);
        }
      }
    }
  }

  /**
   * Gets the number of cells for the tile that are not empty.
   *
   * @param tile The PointTile to accumulate.
   * @return The number of non empty cells.
   */
  final int getNumCells(PointTile tile) {
    int numCells = 0;
    for (int row = 0; row < TILE_SIZE; row++) {
      for (int col = 0; col < TILE_SIZE; col++) {
        ColRow rc = new ColRow(col, row);
        if (tile.getNumCoords(rc) == 0) {
          continue;
        }
        numCells++;
      }
    }
    return numCells;
  }

}
