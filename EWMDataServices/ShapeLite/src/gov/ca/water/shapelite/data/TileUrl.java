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
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.analysis.TileLocation;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.map.Mercator;
import java.awt.Point;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TileUrl implements Serializable {

  /**
   * The total span in degrees longitude of the earth, or span of latitude and
   * longitude in Ted Mercator coordinates.
   */
  public static final double LL_SPAN = 360;

  /**
   * The maximum latitude/longitude in Ted Mercator coordinates.
   */
  public static final double LL_MAX = 180;

  /**
   * The tile size in pixels.
   */
  public static final int TILE_SIZE = 256;

  /**
   * The integer horizontal tileIndex of the tile from 0 to 2^level.
   */
  private final int tileX;
  /**
   * The integer vertical tileIndex of the tile from 0 to 2^level.
   */
  private final int tileY;
  /**
   * The integer level from 0 to 18.
   */
  private final int level;

  /**
   * The geographic envelopeMercator in WGS84 coordinates.
   */
  private Envelope envelopeMercator;

  /**
   * The base url, without the level or tilex folders.
   */
  private String baseUrl;

  /**
   * Gets the extension with leading period.
   */
  private final String extension;

  /**
   * This constructor will parse the tileX, tileY, and scale information from
   * the specified filename.
   *
   * @param filename The filename.
   */
  public TileUrl(String filename) {
    File f = new File(filename);
    String file = f.getName();
    int p = file.lastIndexOf(".");
    this.tileY = Integer.parseInt(file.substring(0, p));
    this.extension = file.substring(p);
    this.tileX = Integer.parseInt(f.getParentFile().getName());
    this.level = Integer.parseInt(f.getParentFile().getParentFile().getName());
    this.baseUrl = f.getParentFile().getParentFile().getParent();
    calculateEnvelope();
  }

  /**
   * This builds a tile URL based on the parameter values, rather than based on
   * a file name.
   *
   * @param baseUrl The base folder name without any level, tilex or tiley
   * information.
   * @param level The integer level.
   * @param tileX The integer horizontal offset in tiles.
   * @param tileY The integer vertical offset in tiles.
   * @param extension The string extension.
   */
  public TileUrl(String baseUrl, int level, int tileX, int tileY, String extension) {
    if (!extension.startsWith(".")) {
      extension = "." + extension;
    }
    this.tileY = tileY;
    this.extension = extension;
    this.tileX = tileX;
    this.level = level;
    this.baseUrl = baseUrl;
    calculateEnvelope();
  }

  /**
   * Tests to see if the filename is a path that actually exists.
   *
   * @return Boolean, true if the path exists.
   */
  public final boolean exists() {
    File f = new File(getFilename());
    return f.exists();
  }

  /**
   * Gets the four direct children TileUrl tiles located in the same location
   * but one level higher the current level.
   *
   * @return A List of four TileUrl coordinates.
   */
  public final List<TileUrl> getChildren() {
    List<TileUrl> result = new ArrayList<>();
    TileUrl topLeft = new TileUrl(baseUrl, level + 1, tileX * 2, tileY * 2,
        extension);
    result.add(topLeft);
    TileUrl topRight = new TileUrl(baseUrl, level + 1, tileX * 2 + 1, tileY * 2,
        extension);
    result.add(topRight);
    TileUrl bottomLeft = new TileUrl(baseUrl, level + 1, tileX * 2, tileY * 2 + 1,
        extension);
    result.add(bottomLeft);
    TileUrl bottomRight = new TileUrl(baseUrl, level + 1, tileX * 2 + 1,
        tileY * 2 + 1, extension);
    result.add(bottomRight);
    return result;
  }

  /**
   * Gets the list of tile url values for the specified scale. This list is
   * empty if the scale is the same as the current scale.
   *
   * @param scale the integer scale.
   * @return The list of TileURL values for the specified scale.
   */
  public final List<TileUrl> getChildren(int scale) {
    List<TileUrl> result = new ArrayList<>();
    if (scale <= level) {
      throw new IllegalArgumentException("Parameter scale should have "
          + "been higher than level.");
    }
    result.add(this);
    while (result.get(0).getLevel() < scale) {
      result = getChildren(result);
    }
    return result;
  }

  /**
   * This assumes that the highest source level is 18, but will generate as many
   * generations of children as necessary.
   *
   * @param sourceTiles the list of source tiles.
   * @return The list of child tiles below all the source tiles.
   */
  public final static List<TileUrl> getChildren(List<TileUrl> sourceTiles) {
    List<TileUrl> result = new ArrayList<>();
    for (TileUrl tile : sourceTiles) {
      result.addAll(tile.getChildren());
    }
    return result;
  }

  /**
   * Creates a duplicate using this tileUrl filename.
   *
   * @return A TileUrl for the same location.
   */
  public final TileUrl copy() {
    return new TileUrl(this.getFilename());
  }

  /**
   * Calculates the envelopeMercator based on the tileX, tileY and level.
   */
  private void calculateEnvelope() {
    envelopeMercator = getTileEnvelopeMercator(level, tileX, tileY);
  }

  /**
   * Calculates the envelope bounds of a tile based on its attributes in the
   * Map's TedMercator projection.
   *
   * @param level The integer level.
   * @param tileX The tileX
   * @param tileY The tileY
   * @return The Envelope.
   */
  public static final Envelope getTileEnvelopeMercator(int level, int tileX, int tileY) {
    double mercatorXMin = -LL_MAX + (LL_SPAN / Math.pow(2, level)) * tileX;
    double mercatorXMax = -LL_MAX + (LL_SPAN / Math.pow(2, level)) * (tileX + 1);
    double mercatorYMax = LL_MAX - (LL_SPAN / Math.pow(2, level)) * tileY;
    double mercatorYMin = LL_MAX - (LL_SPAN / Math.pow(2, level)) * (tileY + 1);
    return new DefaultEnvelope(mercatorXMin, mercatorYMin,
        mercatorXMax, mercatorYMax);
  }

  /**
   * Calculates the envelope bounds of a tile based on its attributes in the
   * WGS84 projection.
   *
   * @param level The integer level.
   * @param tileX The tileX
   * @param tileY The tileY
   * @return The Envelope.
   */
  public static final Envelope getTileEnvelope(int level, int tileX, int tileY) {
    double mercatorXMin = -LL_MAX + (LL_SPAN / Math.pow(2, level)) * tileX;
    double mercatorXMax = -LL_MAX + (LL_SPAN / Math.pow(2, level)) * (tileX + 1);
    double mercatorYMax = LL_MAX - (LL_SPAN / Math.pow(2, level)) * tileY;
    double mercatorYMin = LL_MAX - (LL_SPAN / Math.pow(2, level)) * (tileY + 1);
    Envelope merc = new DefaultEnvelope(mercatorXMin, mercatorYMin,
        mercatorXMax, mercatorYMax);
    return Mercator.fromMerc(merc);
  }

  /**
   * Gets the String url for the tile at the specified location.
   *
   * @param base The base folder path.
   * @param level The integer scale.
   * @param tileX horizontal tile offset
   * @param tileY the vertical tile offset
   * @param extension The string extension to use. This can either start with a
   * period or not. If the period is not provided it will be added as the first
   * character.
   * @return The String url for the tile at the specified location.
   */
  public static final String getPath(String base, int level, int tileX,
      int tileY, String extension) {
    String ext = extension;
    if (!ext.startsWith(".")) {
      ext = "." + ext;
    }
    return base + File.separator + level + File.separator + tileX + File.separator
        + tileY + ext;
  }

  /**
   * Gets the String url for the tile at the specified location.
   *
   * @param base The base folder path.
   * @param level The integer scale.
   * @param tileX horizontal tile offset
   * @return The String url for the tile at the specified location.
   */
  public static final String getColumnPath(String base, int level, int tileX) {
    return base + File.separator + level + File.separator + tileX + File.separator;
  }

  /**
   * Gets the String url for the tile at the specified location.
   *
   * @param base The base folder path.
   * @param level The integer scale.
   * @param longitude The longitude in WGS84 coordinates.
   * @param latitude The latitude in WGS84 coordinates.
   * @param extension The string extension to use. This can either start with a
   * period or not. If the period is not provided it will be added as the first
   * character.
   * @return The String url for the tile at the specified location.
   */
  public static final String getPath(String base, int level, double longitude,
      double latitude, String extension) {
    Point tileIndex = getTileIndex(level, new CoordXY(longitude, latitude));
    String ext = extension;
    if (!ext.startsWith(".")) {
      ext = "." + ext;
    }
    return base + level + File.separator + tileIndex.x + File.separator
        + tileIndex.y + ext;
  }

  /**
   * Making the assumption that a standard 256 sized web mercator tiling is
   * used, this defines the complete location, including row and column.
   *
   * @param level The zoom level.
   * @param location the location.
   * @return The TileLocation giving the tileX, tileY, pixel row and column.
   */
  public static final TileLocation getTileLocation(int level, Coord location) {
    Coord mercator = Mercator.toMerc(location);
    return getTileLocationMercator(level, mercator);
  }

  /**
   * Making the assumption that a standard 256 sized web mercator tiling is
   * used, this defines the complete mercatorLocation, including row and column.
   *
   * @param level The zoom level.
   * @param mercatorLocation the mercatorLocation.
   * @return The TileLocation giving the tileX, tileY, pixel row and column.
   */
  public static final TileLocation getTileLocationMercator(int level,
      Coord mercatorLocation) {
    Point tile = getTileIndexMercator(level, mercatorLocation);
    TileLocation result = new TileLocation();
    result.setTileX(tile.x);
    result.setTileY(tile.y);
    double tx = tile.x / Math.pow(2, level) * LL_SPAN - LL_MAX;
    double size = LL_SPAN / Math.pow(2, level);
    result.setCol((int) (TILE_SIZE * (mercatorLocation.getX() - tx) / size));
    double ty = LL_MAX - tile.y / Math.pow(2, level) * LL_SPAN;
    result.setRow((int) (TILE_SIZE * (ty - mercatorLocation.getY()) / size));
    return result;
  }

  /**
   * Gets the top left of the pixel location (not the whole tile).
   *
   * @param level The integer zoom level.
   * @param loc The tile location.
   * @return The Coordinate with the top left location for the pixel in WGS84
   * coordinates.
   */
  public static final Coord getTopLeft(int level, TileLocation loc) {
    return Mercator.fromMerc(getTopLeftMercator(level, loc));
  }

  /**
   * Gets the top left of the pixel location (not the whole tile).
   *
   * @param level The integer zoom level.
   * @param loc The tile location.
   * @return The Coordinate with the top left location for the pixel in WGS84
   * coordinates.
   */
  public static final Coord getTopLeftMercator(int level, TileLocation loc) {
    double tx = loc.getTileX() / Math.pow(2, level) * LL_SPAN - LL_MAX;
    double ty = LL_MAX - loc.getTileY() / Math.pow(2, level) * LL_SPAN;
    double cellSize = LL_SPAN / Math.pow(2, level) / TILE_SIZE;
    Coord merc = new CoordXY(tx + loc.getCol() * cellSize,
        ty - loc.getRow() * cellSize);
    return merc;
  }

  /**
   * Gets the envelope defining the cell location in Mercator coordinates.
   *
   * @param level The integer zoom level from 0 to 20.
   * @param loc The location.
   * @return The Envelope.
   */
  public static final Envelope getCellEnvelopeMercator(int level, TileLocation loc) {
    Coord tl = getTopLeftMercator(level, loc);
    double mercCellSize = getCellSize(level);
    Coord br = new CoordXY(tl.getX() + mercCellSize, tl.getY() - mercCellSize);
    return new DefaultEnvelope(tl.getX(), br.getY(), br.getX(), tl.getY());
  }

  /**
   * Gets the envelope defining the cell location in WGS84 coordinates.
   *
   * @param level The integer zoom level from 0 to 20.
   * @param loc The location.
   * @return The Envelope in WGS84 coordinates (latitude, longitude).
   */
  public static final Envelope getCellEnvelope(int level, TileLocation loc) {
    Coord tl = getTopLeftMercator(level, loc);
    double mercCellSize = getCellSize(level);
    Coord br = new CoordXY(tl.getX() + mercCellSize, tl.getY() - mercCellSize);
    Envelope merc = new DefaultEnvelope(tl.getX(), br.getY(), br.getX(), tl.getY());
    return Mercator.fromMerc(merc);
  }

  /**
   * Gets the cell size in mercator coordinates for the specified cell.
   *
   * @param level The integer zoom level (usually from 0 to 20)
   * @return The double cell size.
   */
  public static final double getCellSize(int level) {
    return LL_SPAN / Math.pow(2, level) / TILE_SIZE;
  }

  /**
   * Gets a point where the integer x, y values corresponds to the specified
   * level and location.
   *
   * @param level The level to get.
   * @param location The geographic location in WGS84 coordinates.
   * @return The Point tile index.
   */
  public static final Point getTileIndex(int level, Coord location) {
    Coord mercator = Mercator.toMerc(location);
    return getTileIndexMercator(level, mercator);
  }

  /**
   * Gets a point where the integer x, y values corresponds to the specified
   * level and location.
   *
   * @param level The level to get.
   * @param mercatorLocation The geographic location in WGS84 coordinates.
   * @return The Point tile index.
   */
  public static final Point getTileIndexMercator(int level, Coord mercatorLocation) {
    double dx = (mercatorLocation.getX() + LL_MAX) / (LL_SPAN);
    double dy = (LL_MAX - mercatorLocation.getY()) / (LL_SPAN);
    int tileX = (int) (dx * Math.pow(2, level));
    int tileY = (int) (dy * Math.pow(2, level));
    return new Point(tileX, tileY);
  }

  /**
   * This uses the tile notation to return the string name of the tile that
   * exists in the shift offset of the specified direction. If the specified
   * path would be outside a viable tile address path, then this returns null.
   *
   * @param shiftX The integer horizontal shift in tiles.
   * @param shiftY The integer vertical shift in tiles.
   * @return The String path or null if the shift is outside the world bounds.
   */
  public final Optional<String> getNeighborPath(int shiftX, int shiftY) {
    int x = this.tileX + shiftX;
    int y = this.tileY + shiftY;
    if (x < 0) {
      return Optional.empty();
    }
    if (y < 0) {
      return Optional.empty();
    }
    int maxIndex = (int) Math.pow(2, this.getLevel()) - 1;
    if (x > maxIndex) {
      return Optional.empty();
    }
    if (y > maxIndex) {
      return Optional.empty();
    }
    String result = baseUrl + File.separator + this.getLevel()
        + File.separator + x + File.separator + y
        + extension;
    return Optional.of(result);
  }

  /**
   * This uses the tile notation to return the TileUrl of the tile that exists
   * in the shift offset of the specified direction. If the specified path would
   * be outside a viable tile address path, then this returns null.
   *
   * @param shiftX The integer horizontal shift in tiles.
   * @param shiftY The integer vertical shift in tiles.
   * @return The optional TileUrl or empty if the shift is outside the world
   * bounds.
   */
  public final Optional<TileUrl> getNeighborUrl(int shiftX, int shiftY) {
    Optional<String> path = getNeighborPath(shiftX, shiftY);
    if (path.isPresent()) {
      return Optional.of(new TileUrl(path.get()));
    }
    return Optional.empty();
  }

  /**
   * Tests this TileURl for equality where object comparison is replaced by a
   * string comparison of the filenames.
   *
   * @param obj the objects to compare.
   * @return Boolean, true if the objects are equal.
   */
  @Override
  public final boolean equals(Object obj) {
    if (obj instanceof TileUrl) {
      TileUrl other = (TileUrl) obj;
      return this.getFilename().equals(other.getFilename());
    }

    return super.equals(obj);
  }

  /**
   * Gets a hash code based on the filename.
   *
   * @return The integer hashcode based on the string filename.
   */
  @Override
  public final int hashCode() {
    return this.getFilename().hashCode();
  }

  /**
   * Gets the integer horizontal tileIndex of the tile from 0 to 2^level.
   *
   * @return the tileX
   */
  public final int getTileX() {
    return tileX;
  }

  /**
   * Gets the integer vertical tileIndex of the tile from 0 to 2^level.
   *
   * @return the tileY
   */
  public final int getTileY() {
    return tileY;
  }

  /**
   * Gets the integer level from 0 to 18.
   *
   * @return the level
   */
  public final int getLevel() {
    return level;
  }

  /**
   * Gets the WGS84 envelopeMercator of this TileURl location.
   *
   * @return the envelopeMercator
   */
  public final Envelope getEnvelopeWGS84() {
    return Mercator.fromMerc(envelopeMercator);
  }

  /**
   * Gets the envelope in mercator coordinates.
   *
   * @return The envelope for this TileURL in Ted Mercator.
   */
  public final Envelope getEnvelopeMercator() {
    return envelopeMercator;
  }

  /**
   * Gets the base url, without the level or tilex folders.
   *
   * @return the baseUrl
   */
  public final String getBaseUrl() {
    return baseUrl;
  }

  /**
   * Sets the base url, without the level or tilex folders.
   *
   * @param baseUrl the baseUrl to set
   */
  public final void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  /**
   * Gets the original filename.
   *
   * @return the String filename.
   */
  public final String getFilename() {
    return baseUrl + File.separator + level + File.separator + tileX
        + File.separator + tileY + extension;
  }

  /**
   * Gets the string extension of the raster, starting with the period.
   *
   * @return the String extension.
   */
  public final String getExtension() {
    return extension;
  }

}
