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
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Segment;
import gov.ca.water.shapelite.analysis.PointThinner;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.coordinate.HasXY;
import gov.ca.water.shapelite.data.PointRecord;
import gov.ca.water.shapelite.io.LasReadException;
import gov.ca.water.shapelite.io.LasReaderVM;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import gov.ca.water.shapelite.segment.DefaultSegment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ElevationServiceLas implements ElevationService {

  /**
   * The logger utility for this class.
   */
  private static final Logger LOGGER = Logger.getLogger(
      ElevationServiceLas.class.getName());

  /**
   * WGS84 Projection.
   */
  private static final ProjectionInfo WGS84
      = Projections.getGeographic().getWorld().getWGS1984();

  /**
   * UTM projection used by LAS files.
   */
  private static final ProjectionInfo UTM = Projections.getNad83UTMZone10Foot();

  /**
   * The default buffer distance in feet.
   */
  private static final double DEFAULT_BUFFER = 5;

  /**
   * A polygon FeatureSet with attributes "FILENAME" which show the file
   * associated with each WGS84 polygon.
   */
  private FeatureSet lasExtents;

  /**
   * The buffer distance in feet, which determines the maximum distance a point
   * can be from the specified line segment and still be counted as part of the
   * return.
   */
  private double buffer;

  /**
   * The default buffer distance is about 5 meters.
   */
  public ElevationServiceLas() {
    buffer = DEFAULT_BUFFER;
  }

  /**
   * Gets the elevations by reading the las points, and returning the points
   * ordered by their distance from the start point.
   *
   * @param start The start location in WGS84 coordinates.
   * @param end The end location in WGS84 coordinates.
   * @return The list of CoordZ values.
   * @throws ElevationServiceException An exception if there was an error
   * projecting the start or end points, or else if a file could not be read.
   */
  @Override
  public final List<CoordZ> getElevations(HasXY start, HasXY end)
      throws ElevationServiceException {
    Segment seg = new DefaultSegment(start, end);
    Coord startUtm = null;
    Coord endUtm = null;
    CoordXY startXY = new CoordXY(start);
    try {
      startUtm = Reproject.reprojectCoordinate(startXY, WGS84, UTM);
      endUtm = Reproject.reprojectCoordinate(new CoordXY(end), WGS84, UTM);
    } catch (ProjectionException ex) {
      throw new ElevationServiceException(ex);
    }
    Segment segUtm = new DefaultSegment(startUtm, endUtm);
    List<CoordZ> results = new ArrayList<>();
    for (Feature tile : lasExtents.getFeatures()) {
      if (seg.intersection(tile.getEnvelope()).isPresent()) {
        try {
          String filename = tile.getAttributes().get("FILENAME");
          LasReaderVM reader = new LasReaderVM();
          reader.open(filename);
          List<PointRecord> records = reader.getPointsOnly();
          for (PointRecord record : records) {
            CoordZ utm = record.toCoord();
            double distUtm = segUtm.distanceTo(utm);
            if (distUtm > buffer + 2) {
              continue;
            }
            try {
              // For consistency with the other methods, use the same
              // distance calculation with the start point, even though
              // distUtm may actually be more precise.
              CoordZ wgs84 = Reproject.reprojectCoordinate(utm, UTM, WGS84);
              Segment link = seg.shortestLink(wgs84);
              double dist = link.getP1().distanceInFeet(link.getP2());
              if (dist > buffer) {
                continue;
              }
              wgs84.setM(wgs84.distanceInFeet(startXY));
              results.add(wgs84);
            } catch (ProjectionException ex) {
              LOGGER.log(Level.INFO, ex.getMessage(), ex);
            }
          }
        } catch (LasReadException ex) {
          throw new ElevationServiceException(ex);
        }

      }
    }
    Collections.sort(results, Coord.By.M);
    return results;
  }

  /**
   * Gets a polygon FeatureSet with attributes "FILENAME" which show the file
   * associated with each WGS84 polygon.
   *
   * @return the lasExtents
   */
  public final FeatureSet getLasExtents() {
    return lasExtents;
  }

  /**
   * Sets a polygon FeatureSet with attributes "FILENAME" which show the file
   * associated with each WGS84 polygon.
   *
   * @param lasExtents the lasExtents to set
   */
  public final void setLasExtents(FeatureSet lasExtents) {
    this.lasExtents = lasExtents;
  }

  /**
   * Gets the buffer distance in decimal degrees, which determines the maximum
   * distance a point can be from the specified line segment and still be
   * counted as part of the return.
   *
   * @return the buffer
   */
  public final double getBuffer() {
    return buffer;
  }

  /**
   * Sets the buffer distance in decimal degrees, which determines the maximum
   * distance a point can be from the specified line segment and still be
   * counted as part of the return.
   *
   * @param buffer the buffer to set
   */
  public final void setBuffer(double buffer) {
    this.buffer = buffer;
  }


  @Override
  public final List<CoordZ> getElevations(HasXY start, HasXY end,
      double buffer, int count) throws ElevationServiceException {
    this.buffer = buffer;
    List<CoordZ> coords = getElevations(start, end);
    PointThinner thinner = new PointThinner();
    return thinner.thin(coords, start, end, count);
  }

}
