/*
 * The MIT License
 *
 * Copyright 2015 rmarquez.
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

import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Optional;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author rmarquez
 * @param <Self> used in copy() method
 * @param <DataSet> used in getDataSet() method.
 */
public interface IDataSourceTiles<Self, DataSet> {

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Determines whether the current tile, based on the level, x and y properties
   * exists.
   * @return Boolean, true if the cached tiles exist.
   */
  boolean cachedTileExists();

  /**
   * Gets a copy of this data source object, configured the same way.
   *
   * @return A deep copy of the DataSourceTiles object.
   */
  Self copy();

  /**
   * Gets the full path to the specific file.
   *
   * @return A String full path to the current cached tile..
   */
  String getCacheFilename();

    //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the DatasetImage which can be used in a layer object based on the
   * current x, y, and level settings on this object.
   *
   * @return A DataSet object for this data source.
   */
  @NonNull
  Optional<DataSet> getDataset();

  /**
   * The geographic envelope where data is supported.
   * @return The envelope in projectected coordinates.
   */
  Envelope getEnvelopeMercator();

  /**
   * Gets an Envelope in the Mercator projection based on the currently
   * specified level and the current x, y offsets that are specified.
   *
   * @return An Envelope in Mercator coordinates.
   */
  Envelope getCurrentEnvelopeMercator();

  /**
   * Gets an Envelope in WGS84 projection for the specified level and x and y
   * tile offsets.
   *
   * @return The Envelope bounds for the currently specified tile.
   */
  Envelope getCurrentEnvelopeWGS84();

  /**
   * Gets the string extension (without period) representing the image filename
   * extension for the tiles, like png, jpg or jpg. The default is png.
   *
   * @return the ext
   */
  String getExt();

  /**
   * This is the direct method. This will obtain the image either from the file
   * or from the url. This is not recommended during rendering, since the url
   * downloading can be a little slow.
   *
   * @return A BufferedImage with the tile content.
   */
  Optional<BufferedImage> getImage();

  /**
   * Gets the integer level from 0 to 18 that defines the zoom into the Mercator
   * map of the earth. 0 is a single 256x256 tile that covers the entire world.
   * This, along with the x and y tile offsets define which tile to open or
   * download.
   *
   * @return the level
   */
  int getLevel();

  /**
   * Gets the integer maximum level, usually 18, that defines the maximum
   * resolution available for the dataset.
   *
   * @return the maxScale
   */
  int getMaxLevel();

  /**
   * Gets the string file path where the tiles are stored.
   *
   * @return the filePath
   */
  String getPath();

  /**
   * Given an envelope in mercator coordinates, this will return the integer
   * tile boundaries as a rectangle. This uses the current "level"
   *
   * @param mercator The Mercator coordinate envelope.
   * @return A Rectangle representing the tile bounds for the envelope.
   */
  Rectangle getTileBounds(Envelope mercator);

  /**
   * Gets the TilePath object that contains the information about the URL, file
   * path, format and envelope bounds based on the currently specified
   * properties.
   *
   * @return A TilePath object for the current bounds, and filePath.
   */
  TilePath getTilePath();

  /**
   * Gets the TileUrlFormat that controls how the tiles are stored on the web.
   *
   * @return the urlFormat
   */
  TileUrlFormat getUrlFormat();

  /**
   * Gets the 0 based integer horizontal x offset in tiles from -180 longitude
   * moving East.
   *
   * @return the x
   */
  int getX();

  /**
   * Gets the 0 based integer vertical y offset in tiles from 90 latitude moving
   * South.
   *
   * @return the y
   */
  int getY();

  //</editor-fold>
  /**
   * @return the localOnly
   */
  boolean isLocalOnly();

  /**
   * Instead of returning the dataset right away, this adds the content to a
   * stack for requested downloads. If the content is already on the stack, it
   * will be ignored.
   */
  void requestDownload();

  /**
   * Sets the string extension (without period) representing the image filename
   * extension for the tiles, like png, jpg or jpg. The default is png.
   *
   * @param ext the ext to set
   */
  void setExt(String ext);

  /**
   * Sets the integer level from 0 to 18 that defines the zoom into the Mercator
   * map of the earth. 0 is a single 256x256 tile that covers the entire world.
   * This, along with the x and y tile offsets define which tile to open or
   * download.
   *
   * @param scale The integer scale from 0 to 18.
   */
  void setLevel(int scale);

  /**
   * @param localOnly the localOnly to set
   */
  void setLocalOnly(boolean localOnly);

  /**
   * Sets the integer maximum level, usually 18, that defines the maximum
   * resolution available for the dataset.
   *
   * @param maxScale the maxScale to set
   */
  void setMaxLevel(int maxScale);

  /**
   * Sets the string file path where the tiles are stored.
   *
   * @param path the filePath to set. This only uses the final folder name that
   * should appear in the AppData\TileCache folder.
   */
  void setPath(String path);

  /**
   * Sets the path without using the APPDATA tilecache.
   *
   * @param path The String path.
   */
  void setPathAbsolute(String path);

  /**
   * Sets the TileUrlFormat that controls how the tiles are stored on the web.
   *
   * @param urlFormat the urlFormat to set
   */
  void setUrlFormat(TileUrlFormat urlFormat);

  /**
   * Sets the 0 based integer horizontal x offset in tiles from -180 longitude
   * moving East.
   *
   * @param x the x to set
   */
  void setX(int x);

  /**
   * Sets he 0 based integer vertical y offset in tiles from 90 latitude moving
   * South.
   *
   * @param y the y to set.
   */
  void setY(int y);

  /**
   * Gets a boolean that is true if the specified url name exists.
   * @param urlName The URL of the resource.
   * @return Boolean, true if the resource exists.
   */
  boolean urlTileExists(String urlName);

}
