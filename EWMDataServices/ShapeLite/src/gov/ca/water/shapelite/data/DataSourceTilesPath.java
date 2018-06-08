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

import gov.ca.water.shapelite.data.marker.PathRangeMarker;
import gov.ca.water.shapelite.data.dataset.MarkerPathRangeDataset;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


/**
 *
 * @author rmarquez
 */
public class DataSourceTilesPath extends DataSource<MarkerPathRangeDataset>
        implements IDataSourceTiles<DataSourceTilesPath,
        MarkerPathRangeDataset> {

  /**
   * The integer maximum level, usually 18, that defines the maximum resolution
   * available for the dataset by default.
   */
  public static final int DEFAULT_MAX_LEVEL = 18;

  /**
   * The complete span in latitude/longitude in Ted Mercator.
   */
  public static final int LL_SPAN = 360;

  /**
   * The maximum value in a single direction of latitude or longitude.
   */
  public static final int LL_MAX = 180;

  /**
   * The string extension (without period) representing the image filename
   * extension for the tiles, like png, jpg or jpg. The default is png.
   */
  private String ext;

  /**
   * The integer level from 0 to 18 that defines the zoom into the Mercator map
   * of the earth. 0 is a single 256x256 tile that covers the entire world.
   * This, along with the x and y tile offsets define which tile to open or
   * download.
   */
  private int level;

  /**
   * The integer maximum level, usually 18, that defines the maximum resolution
   * available for the dataset.
   */
  private int maxLevel;

  /**
   * The string file path where the tiles are stored.
   */
  private String path;

  /**
   * The TileUrlFormat that controls how the tiles are stored on the web.
   */
  private TileUrlFormat urlFormat;

  /**
   * The 0 based integer horizontal x offset in tiles from -180 longitude moving
   * East.
   */
  private int x;

  /**
   * The 0 based integer vertical y offset in tiles from 90 latitude moving
   * South.
   */
  private int y;

  /**
   * True if no URL should be checked, and only the local files should be used.
   */
  private boolean localOnly;

  /**
   * Creates a new instance of the DataSourceTiles class.
   */
  public DataSourceTilesPath() {
    String p = System.getenv("APPDATA") + "\\TileCache\\Open Street Map";
    File dir = new File(p);
    if (!dir.exists()) {
      dir.mkdirs();
    }
    path = p;
    ext = "png";
    maxLevel = DEFAULT_MAX_LEVEL;
    urlFormat = new TileUrlFormatOpenStreetMap();
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Determines whether the current tile, based on the level, x and y properties
   * exists.
   *
   * @return Boolean, true if the cached tile exists.
   */
  @Override
  public final boolean cachedTileExists() {
    String dirPath = path + File.separator + level + File.separator + x;
    String ex = ext;
    if (!ex.startsWith(".")) {
      ex = "." + ext;
    }
    File file = new File(dirPath + File.separator + y + ex);
    return file.exists();
  }

  /**
   * Gets a copy of this data source object, configured the same way.
   *
   * @return A deep copy of htis DataSourceTilesPath object.
   */
  @Override
  public final DataSourceTilesPath copy() {
    DataSourceTilesPath result = new DataSourceTilesPath();
    result.path = path;
    result.setExt(ext);
    result.setX(x);
    result.setY(y);
    result.setLevel(level);
    result.setMaxLevel(maxLevel);
    result.setUrlFormat(urlFormat);
    return result;
  }

  /**
   * Gets an Envelope in the Mercator projection based on the currently
   * specified level and the current x, y offsets that are specified.
   *
   * @return An Envelope in Mercator coordinates.
   */
  @Override
  public final Envelope getCurrentEnvelopeMercator() {
    double n = Math.pow(2, level);
    double dx = LL_SPAN / n;
    double xmin = -LL_MAX + x * dx;
    double xmax = -LL_MAX + (x + 1) * dx;
    double dy = LL_SPAN / n;
    double ymin = LL_MAX - (y + 1) * dy;
    double ymax = LL_MAX - y * dy;
    return new DefaultEnvelope(xmin, ymin, xmax, ymax);
  }

  /**
   * Gets an Envelope in WGS84 projection for the specified level and x and y
   * tile offsets.
   *
   * @return The Envelope bounds for the currently specified tile.
   */
  @Override
  public final Envelope getCurrentEnvelopeWGS84() {
    Envelope env = getCurrentEnvelopeMercator();
    return Mercator.fromMerc(env);
  }

  /**
   * This is the direct method. This will obtain the image either from the file
   * or from the url. This is not recommended during rendering, since the url
   * downloading can be a little slow.
   *
   * @return A BufferedImage with the tile content.
   */
  @Override
  public final Optional<BufferedImage> getImage() {
    TilePath tp = this.getTilePath();
    File file = new File(tp.getFilePath());
    BufferedImage result = null;
    try {
      if (file.exists()) {
        result = ImageIO.read(file);
      } else {
        URL imageUrl = new URL(tp.getUrl());
        result = ImageIO.read(imageUrl);
        ImageIO.write(result, tp.getFormat(), file);
      }
    } catch (Exception exs) {
    }
    return Optional.of(result);
  }

  /**
   * Gets the TilePath object that contains the information about the URL, file
   * path, format and envelope bounds based on the currently specified
   * properties.
   *
   * @return A TilePath object for the current bounds, and filePath.
   */
  public final File getTileFile() {
    String dirPath = path + File.separator + level + File.separator + x;
    File dir = new File(dirPath);
    if (!this.localOnly) {
      if (!dir.exists()) {
        dir.mkdirs();
      }
    }
    String ex = ext;
    if (!ex.startsWith(".")) {
      ex = "." + ext;
    }
    File file = new File(dirPath + File.separator + y + ex);
    return file;
  }

  /**
   * Gets the TilePath object.
   * @return TilePath.
   */
  @Override
  public final TilePath getTilePath() {
    File file = getTileFile();
    TilePath result = new TilePath();
    result.setFilePath(file.getAbsolutePath());
    if (!localOnly) {
      result.setUrl(urlFormat.getUrl(x, y, level));
    } else {
      result.setUrl(null);
    }
    result.setFormat(ext);
    result.setBounds(this.getEnvelopeMercator());
    if (ext.startsWith(".")) {
      result.setFormat(ext.substring(1, ext.length() - 1));
    }
    return result;
  }

  /**
   * Instead of returning the dataset right away, this adds the content to a
   * stack for requested downloads. If the content is already on the stack, it
   * will be ignored.
   */
  @Override
  public final void requestDownload() {
    TilePath tp = this.getTilePath();
    if (tp.getUrl() != null) {
      TileDownloader.getInstance().add(tp);
    }

  }

  /**
   * Given an envelope in mercator coordinates, this will return the integer
   * tile boundaries as a rectangle. This uses the current "level"
   *
   * @param mercator
   * @return
   */
  @Override
  public final Rectangle getTileBounds(Envelope mercator) {
    Envelope cropped = mercator.copy();
    if (cropped.getMin().getX() < -LL_MAX) {
      cropped.getMin().setX(-LL_MAX);
    }
    if (cropped.getMin().getY() < -LL_MAX) {
      cropped.getMin().setY(-LL_MAX);
    }
    if (cropped.getMax().getX() > LL_MAX) {
      cropped.getMax().setX(LL_MAX);
    }
    if (cropped.getMax().getY() > LL_MAX) {
      cropped.getMax().setY(LL_MAX);
    }
    int n = (int) Math.pow(2, level);
    double dx = LL_SPAN / (double) n;
    int x1 = (int) Math.floor((LL_MAX + cropped.getMin().getX()) / dx);
    int y1 = (int) Math.floor((LL_MAX - cropped.getMax().getY()) / dx);
    int x2 = (int) Math.floor((LL_MAX + cropped.getMax().getX()) / dx);
    int y2 = (int) Math.floor((LL_MAX - cropped.getMin().getY()) / dx);
    Rectangle result = new Rectangle(x1, y1, x2 - x1, y2 - y1);
    return result;
  }

    //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the DatasetImage which can be used in a layer object based on the
   * current x, y, and level settings on this object.
   *
   * @return
   */
  @Override
  public final Optional<MarkerPathRangeDataset> getDataset() {
    MarkerPathRangeDataset result = null;
    try {
      //  Setting reader
      File file = getTileFile();
      ShapefileReader reader = new ShapefileReader();
      reader.open(file.getAbsolutePath());

      //  Get featureset from reader.
      FeatureSet features = reader.getFeatures();

      //  set featuresset as list of markerpathrange
      List<PathRangeMarker> list = new ArrayList<>();
      int i = 0;
      for (Feature feature : features.getFeatures()) {
        i++;
        ProjectionInfo source = Projections.getProjected().getWorld().getWebMercator();
        ProjectionInfo dest = Projections.getGeographic().getWorld().getWGS1984();
        try {
          Shape wgs84 = Reproject.reprojectShape(feature.getShape(), source,
                  dest);
          PathRangeMarker mpoly = new PathRangeMarker(wgs84, "Line " + i);
          mpoly.setAttributesFrom(feature.getAttributes());
          list.add(mpoly);
        } catch (ProjectionException ex) {
          Logger.getLogger(DataSourceTilesPath.class.getName()).log(
                  Level.SEVERE, ex.getMessage(), ex);
        }
      }

      //  return resulting dataset.
      result = new MarkerPathRangeDataset(list);
    } catch (IOException ex) {
      System.out.println("Could not open path: " + getTileFile());
    }
    return Optional.ofNullable(result);
  }

  /**
   * Gets the string extension (without period) representing the image filename
   * extension for the tiles, like png, jpg or jpg. The default is png.
   *
   * @return the ext
   */
  @Override
  public final String getExt() {
    return ext;
  }

  /**
   * Sets the string extension (without period) representing the image filename
   * extension for the tiles, like png, jpg or jpg. The default is png.
   *
   * @param ext the ext to set
   */
  @Override
  public final void setExt(String ext) {
    this.ext = ext;
  }

  /**
   * Gets the integer level from 0 to 18 that defines the zoom into the Mercator
   * map of the earth. 0 is a single 256x256 tile that covers the entire world.
   * This, along with the x and y tile offsets define which tile to open or
   * download.
   *
   * @return the level
   */
  @Override
  public final int getLevel() {
    return level;
  }

  /**
   * Sets the integer level from 0 to 18 that defines the zoom into the Mercator
   * map of the earth. 0 is a single 256x256 tile that covers the entire world.
   * This, along with the x and y tile offsets define which tile to open or
   * download.
   *
   * @param scale the level to set.
   */
  @Override
  public final void setLevel(int scale) {
    this.level = scale;
  }

  /**
   * Gets the integer maximum level, usually 18, that defines the maximum
   * resolution available for the dataset.
   *
   * @return the maxScale
   */
  @Override
  public final int getMaxLevel() {
    return maxLevel;
  }

  /**
   * Sets the integer maximum level, usually 18, that defines the maximum
   * resolution available for the dataset.
   *
   * @param maxScale the maxScale to set
   */
  @Override
  public final void setMaxLevel(int maxScale) {
    this.maxLevel = maxScale;
  }

  /**
   * Gets the string file path where the tiles are stored.
   *
   * @return the filePath
   */
  @Override
  public final String getPath() {
    return path;
  }

  /**
   * Sets the string file path where the tiles are stored.
   *
   * @param path the filePath to set. This only uses the final folder name that
   * should appear in the AppData\TileCache folder.
   */
  @Override
  public final void setPath(String path) {
    String p = System.getenv("APPDATA") + "\\TileCache\\" + path;
    File dir = new File(p);
    if (!dir.exists()) {
      dir.mkdirs();
    }
    this.path = p;
  }

  /**
   * Sets the path without using the APPDATA tilecache.
   *
   * @param path
   */
  @Override
  public final void setPathAbsolute(String path) {
    this.path = path;
  }

  /**
   * Gets the TileUrlFormat that controls how the tiles are stored on the web.
   *
   * @return the urlFormat
   */
  @Override
  public final TileUrlFormat getUrlFormat() {
    return urlFormat;
  }

  /**
   * Sets the TileUrlFormat that controls how the tiles are stored on the web.
   *
   * @param urlFormat the urlFormat to set
   */
  @Override
  public final void setUrlFormat(TileUrlFormat urlFormat) {
    this.urlFormat = urlFormat;
  }

  /**
   * Gets the 0 based integer horizontal x offset in tiles from -180 longitude
   * moving East.
   *
   * @return the x
   */
  @Override
  public final int getX() {
    return x;
  }

  /**
   * Sets the 0 based integer horizontal x offset in tiles from -180 longitude
   * moving East.
   *
   * @param x the x to set
   */
  @Override
  public final void setX(int x) {
    this.x = x;
  }

  /**
   * Gets the 0 based integer vertical y offset in tiles from 90 latitude moving
   * South.
   *
   * @return the y
   */
  @Override
  public final int getY() {
    return y;
  }

  /**
   * Sets he 0 based integer vertical y offset in tiles from 90 latitude moving
   * South.
   *
   * @param y the y to set
   */
  @Override
  public final void setY(int y) {
    this.y = y;
  }



  /**
   * Gets a boolean that is true if only local cache data should be used.
   * @return the localOnly
   */
  @Override
  public final boolean isLocalOnly() {
    return localOnly;
  }

  /**
   * Sets a boolean that is true if the local cache should be used.
   * @param localOnly the localOnly to set
   */
  @Override
  public final void setLocalOnly(boolean localOnly) {
    this.localOnly = localOnly;
  }

  /**
   * Not supported for this class.
   * @return
   */
  @Override
  public final String getCacheFilename() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * Not supported for this class.
   * @param urlName
   * @return
   */
  @Override
  public final boolean urlTileExists(String urlName) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

   //</editor-fold>
}
