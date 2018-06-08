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
package gov.ca.water.shapelite.services;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.analysis.PointThinner;
import gov.ca.water.shapelite.analysis.TileLocation;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.coordinate.HasXY;
import gov.ca.water.shapelite.data.ColRow;
import gov.ca.water.shapelite.data.dataset.RasterDataset;
import gov.ca.water.shapelite.data.TileUrl;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ElevationServiceRaster implements ElevationService {

  /**
   * The default zoom level.
   */
  private static final int DEFAULT_ZOOM = 16;

  /**
   * WGS84.
   */
  private static final ProjectionInfo WGS84
      = Projections.getGeographic().getWorld().getWGS1984();
  /**
   * Web Mercator.
   */
  private static final ProjectionInfo WEB
      = Projections.getProjected().getWorld().getWebMercator();

  /**
   * The base URL without any scale, level, tilex or tiley information.
   */
  private String baseUrl;

  /**
   * Gets the zoom level which ranges from 0 to about 20.
   */
  private int zoomLevel;

  /**
   * Creates a new PointTile elevation service.
   */
  public ElevationServiceRaster() {
    zoomLevel = DEFAULT_ZOOM;
  }

  /**
   *
   * @param start The starting coordinate in WGS84 coordinates.
   * @param end The ending coordinate in WGS84 coordinates.
   * @return The List of coordinates, with Z values, forming a line between the
   * points. The Z is in feet, and the M is the mercator distance along the
   * line.
   */
  @Override
  public final List<CoordZ> getElevations(HasXY start, HasXY end) {
    List<CoordZ> results = new ArrayList<>();
    CoordXY startWGS84 = new CoordXY(start);
    CoordXY startMerc = Mercator.toMerc(startWGS84);
    CoordXY endWGS84 = new CoordXY(end);
    Coord endMerc = Mercator.toMerc(endWGS84);
    double xSpan = endMerc.getX() - startMerc.getX();
    double ySpan = endMerc.getY() - startMerc.getY();
    double length = Math.sqrt(xSpan * xSpan + ySpan * ySpan);
    double ds = TileUrl.getCellSize(zoomLevel);
    double dx = ds * xSpan / length;
    double dy = ds * ySpan / length;
    int count = (int) (Math.ceil(length / ds));
    RasterDataset previous = null;
    int previousTileX = -1;
    int previousTileY = -1;
    int previousRow = -1;
    int previousCol = -1;

    RasterDataset raster = new RasterDataset();
    for (int i = 0; i < count; i++) {
      CoordXY c = new CoordXY(startMerc.getX() + dx * i, startMerc.getY() + dy * i);
      TileLocation loc = TileUrl.getTileLocationMercator(zoomLevel, c);
      TileUrl url = new TileUrl(baseUrl, zoomLevel, loc.getTileX(),
          loc.getTileY(), ".asc");
      if (!url.exists()) {
        continue;
      }
      if (previous == null || previousTileX != loc.getTileX()
          || previousTileY != loc.getTileY()) {
        previousRow = -1;
        previousCol = -1;
        try {
          // load a new tile.
          raster.open(url.getFilename());
          previous = raster;
        } catch (IOException ex) {
          Logger.getLogger(ElevationServiceRaster.class.getName()).log(Level.SEVERE,
              null, ex);
          continue;
        }
      }

      if (loc.getRow() == previousRow && loc.getCol() == previousCol) {
        continue;
      }
      // we have a new group on the same tile.
      ColRow rc = new ColRow(loc.getCol(), loc.getRow());
      double val = raster.getData()[loc.getRow()][loc.getCol()];
      if (val != raster.getNoDataValue()) {
        Coord center = raster.getCellBounds(loc.getRow(), loc.getCol()).getCenter();
        try {
          Coord wgs84 = Reproject.reprojectCoordinate(center, WEB, WGS84);
          CoordZ coord = new CoordZ(wgs84.getX(), wgs84.getY(), val);
          double distance = coord.distanceInFeet(startWGS84);
          coord.setM(distance);
          results.add(coord);
        } catch (ProjectionException ex) {
          Logger.getLogger(ElevationServiceRaster.class.getName()).log(Level.SEVERE, null, ex);
        }

      }
      previousRow = loc.getRow();
      previousCol = loc.getCol();
      previousTileX = loc.getTileX();
      previousTileY = loc.getTileY();
    }

    return results;
  }

  /**
   * Gets the base URL without any scale, level, tilex or tiley information.
   *
   * @return the baseUrl
   */
  public final String getBaseUrl() {
    return baseUrl;
  }

  /**
   * Sets the base URL without any scale, level, tilex or tiley information.
   *
   * @param baseUrl the baseUrl to set
   */
  public final void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  /**
   * Gets the zoom level which ranges from 0 to about 20. By default this is 16.
   *
   * @return the zoomLevel
   */
  public final int getZoomLevel() {
    return zoomLevel;
  }

  /**
   * Gets the zoom level which ranges from 0 to about 20. By default this is 16.
   *
   * @param zoomLevel the zoomLevel to set
   */
  public final void setZoomLevel(int zoomLevel) {
    this.zoomLevel = zoomLevel;
  }


  @Override
  public final List<CoordZ> getElevations(HasXY start, HasXY end,
      double buffer, int count) throws ElevationServiceException {
    List<CoordZ> coords =  getElevations(start, end);
    PointThinner thinner = new PointThinner();
    return thinner.thin(coords, start, end, count);
  }
}
