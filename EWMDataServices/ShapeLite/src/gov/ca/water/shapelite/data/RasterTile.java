/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.data;

import gov.ca.water.shapelite.data.dataset.RasterDataset;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class RasterTile {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * Web Mercator.
   */
  public static final ProjectionInfo WEB
      = Projections.getProjected().getWorld().getWebMercator();
  /**
   * WGS84.
   */
  public static final ProjectionInfo WGS84
      = Projections.getGeographic().getWorld().getWGS1984();

  /**
   * The integer index that represents the size of a tile.
   */
  public static final int TILE_SIZE = 256;

  /**
   * The minimum expected elevation.
   */
  public static final double MIN_ELEVATION = 10;

  /**
   * The maximum expected elevation.
   */
  public static final double MAX_ELEVATION = 100;

  /**
   * The full latitude/longitude span from -180 to 180 in Ted Mercator.
   */
  public static final double LL_SPAN = 360;

  /**
   * The maximum latitude/longitude in Ted Mercator.
   */
  public static final double LL_MAX = 180;

  /**
   * This represents the number of tile at scale 18, or 2^18.
   */
  public static final long MAX_TILES = 262144;

  /**
   * This is an arbitrary value but slighly larger than 18.
   */
  public static final long MAX_SCALE = 20;

  /**
   * The dataset raster for this tile, which stores the actual elevation values.
   */
  private final RasterDataset raster;
  /**
   * The zero based integer index of the tile's horizontal position in the world
   * array for the current scale.
   */
  private int tileX;
  /**
   * The zero based integer index of the tile's vertical position in the world
   * array for the current scale.
   */
  private int tileY;
  /**
   * The integer scale from 0 to 18, where 0 represents a single tile for the
   * whole world and scale N represents 2^N rows and columns.
   */
  private int scale;
  /**
   * The base tile path (not counting scale, and X and y information.
   */
  private String basePath = "";

  //</editor-fold>
  /**
   * Creates a new instance of a raster tile with a default raster element.
   */
  public RasterTile() {
    this.raster = new RasterDataset();
  }

  /**
   * Creates a new instance of a raster tile given the specified raster.
   *
   * @param raster A RasterDataset to define with respect to the tile grid.
   */
  public RasterTile(RasterDataset raster) {
    this.raster = raster;
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  // </editor-fold>
  /**
   * Gets the dataset raster for this tile, which stores the actual elevation
   * values.
   *
   * @return the raster
   */
  public final RasterDataset getRaster() {
    return raster;
  }

  /**
   *
   * @return basePath base directory where tiles are stored. Files are are
   * stored according to {basePath}/{tileX}/{tileY}.{extension}
   */
  public final String getBasePath() {
    return basePath;
  }

  /**
   *
   * @param basePath basePath base directory where tiles are stored. Files are
   * are stored according to {basePath}/{tileX}/{tileY}.{extension}
   */
  public final void setBasePath(String basePath) {
    this.basePath = basePath;
  }

  /**
   * Assigns the Z value to the specified value. If the value exists, this will
   * replace the value.
   *
   * @param point The Coord point to assign to the raster.
   */
  public final void setValue(Coord point) {
    ColRow rc = getIndex(point.getX(), point.getY());
    if (this.getData() != null) {
      this.getData()[rc.getRow()][rc.getColumn()] = point.get(Coord.Index.Z);
    }
  }

  /**
   * This will overwrite nodata values, or average with non-no data values.
   *
   * @param point The Coord point to assign or average together.
   */
  public final void setOrAverage(Coord point) {
    ColRow rc = getIndex(point.getX(), point.getY());
    if (rc.getColumn() < 0 || rc.getColumn() >= TILE_SIZE
        || rc.getRow() < 0 || rc.getRow() >= TILE_SIZE) {
      return;
    }
    if (getData() != null) {
      double z = getData()[rc.getRow()][rc.getColumn()];
      double val = point.get(Coord.Index.Z);
      if (z != raster.getNoDataValue()) {
        val = (z + val) / 2.0; // average
      }
      this.getData()[rc.getRow()][rc.getColumn()] = val;
    }
  }

  /**
   * Gets the top right coordinate of the tile at the specified tile offset and
   * scale.
   *
   * @param x The horizontal tile offset from the left in the world grid.
   * @param y The vertical tile offset from the top in the world grid.
   * @param scale The integer scale from 0 to 18 where the number of tiles wide
   * and tall is 2^scale.
   * @return The Coord object representing the top right corner of the tile.
   * @throws gov.ca.water.shapelite.projection.ProjectionException A projection
   * exception if there was an error during reprojection to web mercator.
   */
  public final static Coord getTopRight(int x, int y, int scale)
      throws ProjectionException {
    double numTiles = Math.pow(2, scale);
    double span = LL_SPAN / numTiles;
    Coord topRight = new CoordXY(-LL_MAX + span * (x + 1), LL_MAX - span * (y));
    Coord topRight84 = Mercator.fromMerc(topRight);

    Coord topRightWeb = Reproject.reprojectCoordinate(topRight84, WGS84, WEB);
    return topRightWeb;
  }

  /**
   * Gets the lower left coordinate of the tile at the specified tile offset and
   * scale.
   *
   * @param x The horizontal tile offset from the left in the world grid.
   * @param y The vertical tile offset from the top in the world grid.
   * @param scale The integer scale from 0 to 18 where the number of tiles wide
   * and tall is 2^scale.
   * @return The Coord object representing the lower left corner of the tile.
   * @throws gov.ca.water.shapelite.projection.ProjectionException A projection
   * exception if there was an error during reprojection to web mercator.
   */
  public final static Coord getLowerLeft(int x, int y, int scale)
      throws ProjectionException {
    double numTiles = Math.pow(2, scale);
    double span = LL_SPAN / numTiles;
    Coord bottomLeft = new CoordXY(-LL_MAX + span * x, LL_MAX - span * (y + 1));
    Coord bottomLeft84 = Mercator.fromMerc(bottomLeft);
    Coord bottomLeftWeb = Reproject.reprojectCoordinate(bottomLeft84,
        WGS84, WEB);
    return bottomLeftWeb;
  }

  /**
   * Saves the raster based on the base path and tileX, TileY, scale.
   *
   * @param basePath The base path, without x, y or scale information.
   *
   * @throws java.io.FileNotFoundException If there was a problem writing to the
   * specified location.
   */
  public final void save(String basePath) throws FileNotFoundException {
    String filename = basePath + File.separator + this.scale + File.separator
        + this.tileX + File.separator + this.tileY + ".asc";
    this.raster.save(filename);
  }

  /**
   * Gets a coordinate with the X, Y and Z value defined for the specified
   * location.
   *
   * @param lon The geographic longitude in WGS84 coordinates.
   * @param lat The geogrpahic longitude in WGS84 coordinates.
   * @return A Coordinate with the same latitude, and longitude, but where the Z
   * value is populated with the value from the raster.
   */
  public final Coord getValue(double lon, double lat) {
    ColRow rc = getIndex(lon, lat);
    Double z = null;
    if (getData() != null) {
      z = getData()[rc.getRow()][rc.getColumn()];
    }
    return new CoordZ(lon, lat, z);
  }

  /**
   * Returns the row/column cell indices corresponding to the specified lat and
   * long Assumes lat & lon are in wgs84 coordinates.
   *
   * @param lon The geographic longitude in WGS84 coordinates.
   * @param lat The geogrpahic longitude in WGS84 coordinates.
   * @return a ColRow structure where the X and Y represent the row and column
 in the raster for the specified latitude and longitude.
   */
  public final ColRow getIndex(double lon, double lat) {
    Coord wgs84 = new CoordXY(lon, lat);
    Coord merc = Mercator.toMerc(wgs84);
    Coord tl = getTopLeft();
    double xDist = merc.getX() - tl.getX();
    double yDist = tl.getY() - merc.getY();
    double cellSize = getCellSize();
    int x = (int) (xDist / cellSize);
    int y = (int) (yDist / cellSize);
    return new ColRow(x, y);
  }

  /**
   * Gets the Ted Mercator top left coordinate for this tile.
   *
   * @return The Coord object where the X and Y values represent the top left
   * corner of the current tile in Ted Mercator.
   */
  public final Coord getTopLeft() {
    double nTiles = Math.pow(2, scale);
    double span = LL_SPAN / nTiles;
    return new CoordXY(-LL_MAX + span * tileX, LL_MAX - span * tileY);
  }

  /**
   * Gets the size of a single pixel at the specified scale in TedMerc
   * coordinates.
   *
   * @return The double size of a single pixel in Ted Mercator coordinates.
   */
  public final double getCellSize() {
    double nTiles = Math.pow(2.0, scale);
    double span = LL_SPAN / nTiles;
    return span / TILE_SIZE;
  }

  /**
   * Gets a value from 0 to 18 where the number of tiles wide and tall is
   * 2^scale.
   *
   * @return the Scale
   */
  public final int getScale() {
    return scale;
  }

  /**
   * Sets a value from 0 to 18 where the number of tiles wide and tall is
   * 2^scale.
   *
   * @param scale the Scale to set.
   */
  public final void setScale(int scale) {
    this.scale = scale;
  }

  /**
   * Gets the raster envelope.
   *
   * @return The Envelope for this raster tile object.
   */
  public final Envelope getEnvelope() {
    return this.raster.getEnvelopeMercator();
  }

  /**
   * Gets a long representing a unique key for this tile.
   *
   * @return The long key representing the tilex, tiley and scale values.
   */
  public final long getKey() {
    return createKey(this.tileX, this.tileY, this.scale);
  }

  /**
   * Creates a long hash code that represents the x, y and scale values.
   *
   * @param x The tile x coordinate position.
   * @param y The tile y coordinate position.
   * @param scale the integer scale from 0 to 18.
   * @return a long value that is unique for standard tile ranges.
   */
  public final static long createKey(int x, int y, int scale) {
    long key = (long) x + MAX_TILES * (long) y * MAX_SCALE + (long) scale;
    return key;
  }

  /**
   * Gets the data from the first band of the raster.
   *
   * @return The jagged array in row major order like [y][x] of doubles.
   */
  public final double[][] getData() {
    return this.raster.getData();
  }

  /**
   * Gets the horizontal offset of this tile in the world grid.
   *
   * @return the tileX
   */
  public final int getTileX() {
    return tileX;
  }

  /**
   * Sets the horizontal offset of this tile in the world grid.
   *
   * @param tileX the tileX to set
   */
  public final void setTileX(int tileX) {
    this.tileX = tileX;
  }

  /**
   * Gets the vertical offset of this tile in the world grid.
   *
   * @return the tileY
   */
  public final int getTileY() {
    return tileY;
  }

  /**
   * Sets the vertical offset of this tile in the world grid.
   *
   * @param tileY the tileY to set
   */
  public final void setTileY(int tileY) {
    this.tileY = tileY;
  }

  /**
   * Opens the tile given the base path and the tileX, tileY and scale.
   *
   * @param basePath The string base path to open.
   * @throws IOException An IO Exception if there is a failure to open the file.
   */
  public final void open(String basePath) throws IOException {
    String fileName = basePath + File.separator + this.scale + File.separator
        + this.tileX + File.separator + this.tileY + ".asc";
    this.raster.open(fileName);
    if (this.raster.getWidth() == 0) {
      boolean stop = true;
    }
  }

  /**
   * Gets the full file name, including the base path, scale, tileX, and tileY.
   *
   * @return The String file name.
   */
  public final String getFullFileName() {
    String fullFileName = basePath + File.separator + this.scale + File.separator
        + this.tileX + File.separator + this.tileY + ".asc";
    return fullFileName;
  }

  /**
   * Gets a boolean that is true if the file exists for this base path, scale,
   * tileX and TileY.
   *
   * @return Boolean, true if the file exists.
   */
  public final boolean fileExists() {
    File file = new File(this.getFullFileName());
    return file.exists() && !file.isDirectory();
  }
}
