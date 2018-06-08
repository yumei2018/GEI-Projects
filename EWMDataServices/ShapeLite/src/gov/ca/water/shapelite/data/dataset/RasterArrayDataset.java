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
package gov.ca.water.shapelite.data.dataset;

import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.data.TileUrl;
import java.io.File;
import java.io.IOException;

/**
 * This class supports a 3x3 set of dataset raster tiles, positioned around the
 * raster, if they exist in the tile folder path.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class RasterArrayDataset {

  /**
   * By default, packs are 3x3 arrays, with the current tile in the center.
   */
  private static final int DEFAULT_PACK_SIZE = 3;

  /**
   * The integer width and height of the tiles.
   */
  private static final int TILE_SIZE = 256;

  /**
   * The integer size of this raster pack.
   */
  private final int arraySize;

  /**
   * The jagged array of rasters making up this pack in Y, X order.
   */
  private RasterDataset[][] rasters;

  /**
   * The Url describing the location of the currently loaded tile.
   */
  private TileUrl loadedUrl;

  /**
   * Creates a new instance of a DatasetRasterPack of default size 3x3.
   */
  public RasterArrayDataset() {
    arraySize = DEFAULT_PACK_SIZE;
    rasters = new RasterDataset[arraySize][arraySize];
  }

  /**
   * Creates a new instance of a DatasetRasterPack of the specified size.
   *
   * @param size The integer number of raster tiles wide and tall.
   */
  public RasterArrayDataset(int size) {
    arraySize = size;
    rasters = new RasterDataset[arraySize][arraySize];
  }

  /**
   * Opens the specified tiled raster, plus also will attempt to open any
   * neighboring tiles.
   *
   * @param filename The Sting filename to open.
   * @throws java.io.IOException If there was an error reading a file.
   */
  public final void open(String filename) throws IOException {
    TileUrl url = new TileUrl(filename);
    load(url);
  }

  /**
   * Gets the double precision value at the specified x and y relative to the
   * top, left of the center tile. If the value is a no-data value on that
   * raster, this returns null.
   *
   * @param x The integer x pixel offset from the left of the middle tile.
   * @param y THe integer y pixel offset from the top of the middle tile.
   * @return The Double value, or null if it was a no-data value.
   */
  public final Optional<Double> getValue(int x, int y) {
    int tileOffsetX = x / TILE_SIZE;
    int tileOffsetY = y / TILE_SIZE;

    if (x > TILE_SIZE) {
      boolean stop = true;
    }

    // even one pixel negative requires a whole tile offset.
    if (x < 0) {
      tileOffsetX -= 1;
    }
    if (y < 0) {
      tileOffsetY -= 1;
    }
    // center the offsets relative to the center tile in the pack.
    tileOffsetX += arraySize / 2;
    tileOffsetY += arraySize / 2;

    int pixelX = x % TILE_SIZE;
    int pixelY = y % TILE_SIZE;
    // For negative values, since we used an extra tile shift, adjust
    // the pixel offset to be positive again.
    if (pixelX < 0) {
      pixelX += TILE_SIZE;
    }
    if (pixelY < 0) {
      pixelY += TILE_SIZE;
    }
    RasterDataset raster = rasters[tileOffsetY][tileOffsetX];
    if (raster == null) {
      return Optional.empty();
    }
    double val = raster.getData()[pixelY][pixelX];
    if (val == raster.getNoDataValue()) {
      return Optional.empty();
    }
    return Optional.of(val);
  }

  /**
   * Given a window size, this will calculate the average value about the x and
   * y center.
   *
   * @param x The integer x offset of the center pixel relative to the left of
   * the middle tile.
   * @param y The integer y offset of the center pixel relative to the top of
   * the middle tile.
   * @param windowSize for example for a 5x5 average, this should be 5.
   * @return A Double representing the average, or null if none of the cells had
   * any values.
   */
  public final Optional<Double> getAverage(int x, int y, int windowSize) {
    int half = windowSize / 2;
    double total = 0;
    int count = 0;
    for (int row = 0; row < windowSize; row++) {
      for (int col = 0; col < windowSize; col++) {
        int pixelX = x + col - half;
        int pixelY = y + row - half;
        Optional<Double> value = getValue(pixelX, pixelY);
        if (value.isPresent()) {
          total += value.get();
          count++;
        }
      }
    }
    if (count == 0) {
      return Optional.empty();
    }
    double average = total / count;
    return Optional.ofNullable(average);
  }

  /**
   * Given the new coordinates, checks against the loadedUrl tile coordinates to
   * see if we can keep some of the grids in memory and simply shift the grids
   * over.
   *
   * @param url The TileUrl that has tileX, tileY and level information.
   * @throws IOException if there was a problem opening a file.
   */
  private void load(TileUrl url) throws IOException {
    RasterDataset[][] shifted = new RasterDataset[arraySize][arraySize];
    int dx = 0;
    int dy = 0;
    if (loadedUrl != null) {
      dx = url.getTileX() - loadedUrl.getTileX();
      dy = url.getTileY() - loadedUrl.getTileY();
    }
    for (int row = 0; row < arraySize; row++) {
      for (int col = 0; col < arraySize; col++) {
        int sourceRow = row + dy;
        int sourceCol = col + dx;

        if (loadedUrl == null || loadedUrl.getLevel() != url.getLevel()
            || sourceRow < 0 || sourceRow >= arraySize
            || sourceCol < 0 || sourceCol >= arraySize) {
          Optional<String> path;
          path = url.getNeighborPath(col - arraySize / 2, row - arraySize / 2);
          if (!path.isPresent()) {
            continue;
          }
          File f = new File(path.get());
          if (f.exists()) {
            RasterDataset r = new RasterDataset();
            r.open(path.get());
            shifted[row][col] = r;
          }
        } else {
          shifted[row][col] = rasters[sourceRow][sourceCol];
        }
      }
    }
    rasters = shifted;
    loadedUrl = url;
  }

  /**
   * Gets a shallow copy of this raster array.
   *
   * @return A shallow copy of this array, so that the array is different, but
   * the actual raster objects are the same raster objects.
   */
  public final RasterArrayDataset shallowCopy() {
    RasterArrayDataset result = new RasterArrayDataset(arraySize);
    for (int row = 0; row < arraySize; row++) {
      System.arraycopy(rasters[row], 0, result.rasters[row], 0, arraySize);
    }
    result.loadedUrl = loadedUrl;
    return result;
  }

  /**
   * Gets the dataset raster array organized in Y, X order, top to bottom, left
   * to right.
   *
   * @return the jagged array of RasterDataset objects.
   */
  public final RasterDataset[][] getRasters() {
    return rasters;
  }

  /**
   * Gets the center dataset raster cell.
   *
   * @return Gets the RasterDataset at the center.
   */
  public final RasterDataset getCenter() {
    int halfPack = arraySize / 2;
    return rasters[halfPack][halfPack];
  }

  /**
   * Gets the integer array size, the number of tiles wide and tall.
   *
   * @return the arraySize The integer pack size.
   */
  public final int getArraySize() {
    return arraySize;
  }

  /**
   * Gets the Url describing the location of the currently loaded tile.
   *
   * @return the loadedUrl
   */
  public final TileUrl getLoadedUrl() {
    return loadedUrl;
  }

}
