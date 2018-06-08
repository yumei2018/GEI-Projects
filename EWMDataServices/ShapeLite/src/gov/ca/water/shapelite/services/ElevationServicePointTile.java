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
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Segment;
import gov.ca.water.shapelite.analysis.PointThinner;
import gov.ca.water.shapelite.analysis.SegmentExtender;
import gov.ca.water.shapelite.analysis.TileLocation;
import gov.ca.water.shapelite.coordinate.ClosestCoord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.coordinate.HasXY;
import gov.ca.water.shapelite.data.PointTile;
import gov.ca.water.shapelite.data.ColRow;
import gov.ca.water.shapelite.data.TileUrl;
import gov.ca.water.shapelite.io.PointTileReader;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.segment.DefaultSegment;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ElevationServicePointTile implements ElevationService {

  /**
   * The default zoom level.
   */
  private static final int DEFAULT_ZOOM = 16;

  /**
   * The X ordinate index.
   */
  private static final int X = Coord.Index.X;

  /**
   * The Y ordinate index.
   */
  private static final int Y = Coord.Index.Y;

  /**
   * The size of size of the tile in pixels.
   */
  private static final int TILE_SIZE = 256;

  /**
   * The buffer distance in feet.
   */
  private static final double BUFFER = 5;

  /**
   * The buffer to use if a single point is clicked.
   */
  private static final double POINT_BUFFER = 20;

  /**
   * The step size to increase the search radius if no points are found during a
   * point query.
   */
  private static final double STEP_SIZE = 20;

  /**
   * The distance in feet to step in a direction perpendicular to the line
   * looking for blocks. For scale 16, this number should be less than 7.
   */
  private static final double CROSS_STEP = 5;

  /**
   * The conversion factor from US Foot to decimal degrees (vertically or at
   * equator).
   */
  private static final double FEET_TO_DD = 0.00000387343;

  /**
   * The base URL without any scale, level, tilex or tiley information.
   */
  private String baseUrl;

  /**
   * Gets the zoom level which ranges from 0 to about 20.
   */
  private int zoomLevel;

  /**
   * The buffer to use.
   */
  private double buffer;

  /**
   * Creates a new PointTile elevation service.
   */
  public ElevationServicePointTile() {
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
  public final List<CoordZ> getElevations(HasXY start, HasXY end,
      double buffer, int count) {
    this.buffer = buffer;
    List<CoordZ> coords = getSampledElevations(start, end, count);
    PointThinner thinner = new PointThinner();
    return thinner.thin(coords, start, end, count);
  }

  /**
   * Gets the elevations using a default buffer. The specified buffer is larger
   * if start and end are the same value.
   *
   * @param start the start coordinate.
   * @param end The end coordinate.
   * @return The List of all the points within the buffer distance.
   */
  @Override
  public final List<CoordZ> getElevations(HasXY start, HasXY end) {
    if (this.buffer < 0) {
      this.buffer = BUFFER;
      if (new CoordXY(start).distance2D(new CoordXY(end)) < BUFFER) {
        this.buffer = POINT_BUFFER;
      }
    }
    List<CoordZ> results = getBufferedElevations(start, end);
    if (start.equals(end)) {
      while (results.isEmpty()) {
        buffer = buffer + STEP_SIZE;
        results = getBufferedElevations(start, end);
      }
    }
    return results;
  }

  /**
   *
   * @param start The starting coordinate in WGS84 coordinates.
   * @param end The ending coordinate in WGS84 coordinates.
   * @return The List of coordinates, with Z values, forming a line between the
   * points. The Z is in feet, and the M is the mercator distance along the
   * line.
   */
  private List<CoordZ> getBufferedElevations(HasXY start, HasXY end) {
    Segment seg = new DefaultSegment(start, end);
    Envelope bufferEnvelope = seg.getEnvelope().bufferInFeet(buffer);
    Envelope bufferMerc = Mercator.toMerc(bufferEnvelope);
    List<CoordZ> results = new ArrayList<>();
    PointTileReader reader = new PointTileReader();
    TileLocation minLoc = TileUrl.getTileLocationMercator(zoomLevel, bufferMerc.getMin());
    TileLocation maxLoc = TileUrl.getTileLocationMercator(zoomLevel, bufferMerc.getMax());
    int x1 = minLoc.getTileX();
    int x2 = maxLoc.getTileX();
    int y1 = maxLoc.getTileY(); // lower geographic y is higher tile y index.
    int y2 = minLoc.getTileY();
    boolean allEmpty = true;

    for (int tileY = y1; tileY <= y2; tileY++) {
      for (int tileX = x1; tileX <= x2; tileX++) {
        Envelope tileEnv = TileUrl.getTileEnvelope(zoomLevel, tileX, tileY);
        Segment link = tileEnv.shortestLink2D(seg);
        if (link.getP1().distanceInFeet(link.getP2()) > buffer) {
          // For diagonal lines, we may be able to skip entire tiles.
          continue;
        }
        TileUrl url = new TileUrl(baseUrl, zoomLevel, tileX, tileY, ".pts");
        if (!url.exists()) {
          continue;
        }
        try {
          // load a new tile.
          PointTile tile = reader.open(url.getFilename());
          results.addAll(getTilePoints(tile, seg));
        } catch (IOException ex) {
          Logger.getLogger(ElevationServicePointTile.class.getName()).log(Level.SEVERE,
              null, ex);
        }
      }
    }

    Collections.sort(results, Coord.By.M);
    return results;
  }

  /**
   * Samples in evenly spaced locations, looking for the closest point in each
   * case.
   *
   * @param start The starting coordinate in WGS84 coordinates.
   * @param end The ending coordinate in WGS84 coordinates.
   * @param count The approximate number of desired returned points. Point cells
   * will be sub-sampled along the line.
   * @return The List of coordinates, with Z values, forming a line between the
   * points. The Z is in feet, and the M is the mercator distance along the
   * line.
   */
  private List<CoordZ> getSampledElevations(HasXY start, HasXY end, int count) {
    List<CoordZ> results = new ArrayList<>();
    if (count == 0) {
      return results;
    }
    CoordXY startXY = new CoordXY(start);
    Segment seg = new DefaultSegment(start, end);
    SegmentExtender ext = new SegmentExtender();
    Segment extended = ext.extend2D(seg, buffer * FEET_TO_DD);
    Coord startMerc = Mercator.toMerc(extended.getP1());
    Coord endMerc = Mercator.toMerc(extended.getP2());
    double dx = (endMerc.getX() - startMerc.getX()) / count;
    double dy = (endMerc.getY() - startMerc.getY()) / count;
    double stepLength = Math.sqrt(dx * dx + dy * dy);
    PointTileReader reader = new PointTileReader();
    TileLocation previousTileLoc = null;
    PointTile tile = null;
    // u is directed along the line.
    // v is directed orthoganl to the line.
    // a block size is 7.8 feet.  Stepping by 5 feet should get all the blocks.
    int bufferBlocks = (int) Math.ceil(buffer / CROSS_STEP);
    double vx = CROSS_STEP * -dy / stepLength * FEET_TO_DD;
    double vy = CROSS_STEP * dx / stepLength * FEET_TO_DD;
    HashSet<TileLocation> counted = new HashSet<>();
    for (int u = 0; u < count; u++) {
      for (int v = -bufferBlocks; v <= bufferBlocks; v++) {

        double x = startMerc.getX() + dx * u + vx * v;
        double y = startMerc.getY() + dy * u + vy * v;
        Coord locMerc = new CoordXY(x, y);
        TileLocation tileLoc = TileUrl.getTileLocationMercator(zoomLevel, locMerc);
        if (counted.contains(tileLoc)) {
          continue;
        }

        if (previousTileLoc == null || tile == null
            || previousTileLoc.getTileX() != tileLoc.getTileX()
            || previousTileLoc.getTileY() != tileLoc.getTileY()) {
          TileUrl url = new TileUrl(baseUrl, zoomLevel, tileLoc.getTileX(),
              tileLoc.getTileY(), ".pts");
          if (!url.exists()) {
            continue;
          }
          try {
            tile = reader.open(url.getFilename());
          } catch (IOException ex) {
            Logger.getLogger(ElevationServicePointTile.class.getName()).
                log(Level.SEVERE, null, ex);
          }
        }
        if (tile != null) {
          List<CoordZ> coords = tile.getCoords(new ColRow(tileLoc.getCol(),
              tileLoc.getRow()));
          for (CoordZ coord : coords) {
            if (seg.closestPointTo(coord).getCoord().distanceInFeet(coord) <= buffer) {
              coord.setM(startXY.distanceInFeet(coord));
              results.add(coord);
            }
          }
        }
        previousTileLoc = tileLoc;
        counted.add(tileLoc);
      }

    }
    Collections.sort(results, Coord.By.M);
    return results;
  }

  /**
   * Considers an entire tile, by considering all the row and column indices.
   *
   * @param tile The PointTile URL.
   * @param seg The Segment seg.
   * @return the list of CoordZ objects that are within range of this segment.
   */
  private List<CoordZ> getTilePoints(PointTile tile, Segment seg) {

    List<CoordZ> results = new ArrayList<>();
    Envelope segBuffer = seg.getEnvelope().bufferInFeet(buffer);
    Optional<Envelope> intersect = tile.getEnvelope().intersection2D(segBuffer);
    if (!intersect.isPresent()) {
      return results;
    }
    Coord min = intersect.get().getMin();
    Coord max = intersect.get().getMax();
    ColRow minLoc = tile.getColRow(min);
    ColRow maxLoc = tile.getColRow(max);
    for (int row = maxLoc.getRow(); row <= minLoc.getRow(); row++) {
      for (int col = minLoc.getColumn(); col <= maxLoc.getColumn(); col++) {
        TileLocation loc = new TileLocation(
            tile.getTileX(), tile.getTileY(), row, col);
        results.addAll(getCellPoints(tile, loc, seg));
      }
    }
    return results;
  }

  /**
   * Gets the list of coordinates from the specified PointTile that are in the
   * cell indexed by TileLocation, that are within BUFFER distance of segment in
   * feet.
   *
   * @param tile The PointTile to get values from.
   * @param loc The TileLocation that stores row and column cell information.
   * @param seg the Segment that is the query criteria.
   * @return The list of CoordZ objects.
   */
  private List<CoordZ> getCellPoints(PointTile tile, TileLocation loc, Segment seg) {
    List<CoordZ> results = new ArrayList<>();
    List<CoordZ> cellCoords = tile.getCoords(new ColRow(loc.getCol(), loc.getRow()));
    for (CoordZ coord : cellCoords) {
      ClosestCoord cc = seg.closestPointTo(coord);
      double segDist = coord.distanceInFeet(cc.getCoord());
      if (segDist < buffer) {
        double startDist = coord.distanceInFeet(seg.getP1());
        coord.setM(startDist);
        results.add(coord);
      }
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

}
