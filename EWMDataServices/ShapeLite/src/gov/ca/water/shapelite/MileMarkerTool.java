/*
 * The MIT License
 *
 * Copyright 2012 hdunsford.
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
package gov.ca.water.shapelite;

import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.Projections;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MileMarkerTool {

  /**
   * Error code.
   */
  private static final int ERROR_CODE = -999;

  /**
   * Gets the projection information for NAD 83, UTM Zone 10 North.
   */
  private static final gov.ca.water.shapelite.projection.ProjectionInfo UTM
      = Projections.getProjected().getUtmNad1983().getNAD1983UTMZone10N();

  /**
   * Gets the projection information for NAD 83, UTM Zone 10 North.
   */
  private static final gov.ca.water.shapelite.projection.ProjectionInfo WGS84
      = Projections.getGeographic().getWorld().getWGS1984();

  // internal variables
  /**
   * The list of river objects used internally.
   */
  private List<River> rivers;
  /**
   * The hash map of river sets.
   */
  private HashMap<Long, River> riverset;
  /**
   * Boolean that is true once the tool is initialized.
   */
  private boolean initialized;

  // Required initial parameters
  /**
   * The string path of the shapefile with mile marker points .shp file.
   */
  private String mileMarkerShapefile;
  /**
   * The string name of the field to use for marker shapeDistance values.
   */
  private String mileMarkerDistanceField;
  /**
   * The string name of the field to use to identify each line.
   */
  private String mileMarkerLineIdField;

  // Optional initial parameters
  /**
   * Boolean that is true if the tool uses line shapes instead of markers.
   */
  private boolean usingLines;
  /**
   * The string line shapefile path of the .shp file.
   */
  private String lineShapefile;
  /**
   * THe string ID field name identifying the line.
   */
  private String lineIdField;
  /**
   * The string field name of the label string for lines.
   */
  private String lineLabelField;

  // variable parameters
  /**
   * The latitude to find the mile for.
   */
  private Double latitude;
  /**
   * The longitude to find the mile shapeDistance for.
   */
  private Double longitude;
  /**
   * The shapefile reader to read marker points.
   */
  private ShapefileReader pointReader;
  /**
   * The shapefile reader to read lines.
   */
  private ShapefileReader lineReader;

  /**
   * The integer id of the previous line.
   */
  private Long lastLineId;

  /**
   * Creates a new instance of the MileMarkerTool.
   */
  public MileMarkerTool() {
    mileMarkerDistanceField = "RIVER_MI_1";
    mileMarkerLineIdField = "RIVER_ID";
    lineIdField = "RIVER_ID";
    lineLabelField = "RIVER_NAME";

  }

  /**
   * This internal method is responsible for reading the vectors and attribute
   * data from the shapefiles and building the internal River data structures.
   *
   * @throws FileNotFoundException If the specified mile marker or line
   * shapefile cannot be found.
   * @throws IOException If the specified mile marker or line shapefile cannot
   * be read.
   * @throws MissingArgumentException If one of the arguments needed is not
   * present.
   */
  public final void initialize() throws FileNotFoundException, IOException,
      MissingArgumentException {
    rivers = new ArrayList<>();
    if (mileMarkerShapefile == null && pointReader == null) {
      throw new MissingArgumentException("The pointShapefile property "
          + "is required.");
    }
    if (mileMarkerDistanceField == null) {
      throw new MissingArgumentException("The mileMarkerDistanceField "
          + "must be set in "
          + "order for distances to be calculated");
    }

    riverset = new HashMap<>();
    if (pointReader == null) {
      pointReader = new ShapefileReader();
      pointReader.open(mileMarkerShapefile);
    }
    List<Shape> rawPoints = pointReader.getShapes();
    Optional<String[]> markerValues = pointReader.getAttributes().
        getFieldStrings(mileMarkerDistanceField);
    Optional<String[]> markerLineIds = pointReader.getAttributes().
        getFieldStrings(mileMarkerLineIdField);
    if (isUsingLines()) {
      if (lineReader == null) {
        lineReader = new ShapefileReader();
        lineReader.open(lineShapefile);
      }
      List<Shape> rawLines = lineReader.getShapes();
      String[] lineLabels = null;
      String[] lineIds = null;
      if (lineLabelField != null) {
        Optional<String[]> lineLabelArray
            = lineReader.getAttributes().getFieldStrings(lineLabelField);
        if (lineLabelArray.isPresent()) {
          lineLabels = lineLabelArray.get();
        }
      }
      if (lineIdField != null) {
        Optional<String[]> lineIdArray
            = lineReader.getAttributes().getFieldStrings(lineIdField);
        if (lineIdArray.isPresent()) {
          lineIds = lineIdArray.get();
        }
      }

      for (int iLine = 0; iLine < rawLines.size(); iLine++) {

        Long id = (long) iLine;
        if (lineIds != null) {
          Double d = Double.parseDouble(lineIds[iLine]);
          id = d.longValue();
        }
        River river = new River(id, rawLines.get(iLine));
        if (lineLabels != null) {
          river.setName(lineLabels[iLine]);
        }
        riverset.put(id, river);
      }
    }

    for (int iPoint = 0; iPoint < rawPoints.size(); iPoint++) {
      MileMarker marker = new MileMarker();
      marker.setLocation(rawPoints.get(iPoint).first());
      if (markerValues.isPresent()) {
        marker.setMile((Double) Double.parseDouble(markerValues.get()[iPoint]));
      }

      Long id = (long) 0;
      if (markerLineIds.isPresent()) {
        Double d = Double.parseDouble(markerLineIds.get()[iPoint]);
        id = d.longValue();
      }
      if (riverset.containsKey(id)) {
        River r = riverset.get(id);
        r.getMarkers().add(marker);
        if (!isUsingLines()) {
          r.getEnvelope().expandToInclude(new DefaultEnvelope(
              marker.getLocation(), marker.getLocation()));
        }
      } else {
        River river = new River(id);
        river.getEnvelope().copyProperties(marker.getLocation());
        river.getMarkers().add(marker);
        riverset.put(id, river);
      }
    }

    Collection<River> col = riverset.values();
    for (River r : col) {
      // Sorting the markers helps
      r.sort();
      if (!usingLines) {
        // Build the line shape from the markers.  This will be used in order to
        // get the closest point on the river to a random point.
        r.markersToLines();
      }
      rivers.add(r);
    }

    initialized = true;

  }

  /**
   * This uses the latitude and longitude properties that have been set in order
   * to calculate the miles upstream based on the river mile marker points. If
   * the lines are also set, then distances will be interpolated between marker
   * points along those lines. Otherwise, straight line distances are assumed
   * between marker points.
   *
   * @return Double miles.
   * @throws gov.ca.water.shapelite.MissingArgumentException if an argument like
   * latitude or longitude is missing.
   * @throws java.io.FileNotFoundException If the marker or line shapefile is
   * not found.
   * @throws java.io.IOException if the marker or line shapefile cannot be read.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If there was
   * an error converting from WGS84 to UTM Zone 10.
   */
  public final double getMiles() throws MissingArgumentException,
      FileNotFoundException, IOException, ProjectionException {
    if (longitude == null) {
      throw new MissingArgumentException("The longitude value "
          + "must be defined to calculate miles.");
    }
    if (latitude == null) {
      throw new MissingArgumentException("The latitude value "
          + "must be defined to calculate miles.");
    }

    if (!initialized) {
      initialize();
    }

    Coord c = new CoordXY(longitude, latitude);
    c = toUtm(c);

    River closest = getClosestRiver(c);
    Optional<Coord> onLine = closest.closestPointTo(c);
    Double dist = null;
    if (onLine.isPresent()) {
      dist = closest.interpolate(onLine.get());
    }
    return ERROR_CODE;
  }

  /**
   * This only requires that the mileMarkerLineId field is correctly set, along
   * with the mileMarker point shapefile.
   *
   * @return The long ID of the closest river.
   * @throws gov.ca.water.shapelite.MissingArgumentException if an argument like
   * latitude or longitude is missing.
   * @throws java.io.FileNotFoundException If the marker or line shapefile is
   * not found.
   * @throws java.io.IOException if the marker or line shapefile cannot be read.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If there was
   * an error converting from WGS84 to UTM Zone 10.
   */
  public final Long getClosestRiverId() throws FileNotFoundException,
      IOException, MissingArgumentException, ProjectionException {
    if (!initialized) {
      initialize();
    }
    Coord c = new CoordXY(longitude, latitude);
    c = toUtm(c);
    return getClosestRiver(c).getId();
  }

  /**
   * This requires that both the line and point shapefile have been set. It also
   * requires that the LineID field is defined for both shapefiles, along with
   * the lineLabelField
   *
   * @return The String name of the closest river.
   * @throws gov.ca.water.shapelite.MissingArgumentException if an argument like
   * latitude or longitude is missing.
   * @throws java.io.FileNotFoundException If the marker or line shapefile is
   * not found.
   * @throws java.io.IOException if the marker or line shapefile cannot be read.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If there was
   * an error converting from WGS84 to UTM Zone 10.
   */
  public final String getClosestRiverName() throws FileNotFoundException,
      IOException, MissingArgumentException, ProjectionException {
    if (!initialized) {
      initialize();
    }
    Coord c = new CoordXY(longitude, latitude);
    c = toUtm(c);
    return getClosestRiver(c).getName();
  }

  /**
   * Gets the River object closest to the specified coordinate.
   *
   * @param c The coordinate to check shapeDistance to.
   * @return the closest River object.
   */
  private River getClosestRiver(Coord c) {
    River closest = null;
    double minDistance = Double.MAX_VALUE;

    for (River r : this.rivers) {
      double envelopeDistance = r.getEnvelope().distance(c);
      if (envelopeDistance < minDistance) {
        Optional<Double> shapeDistance = r.getShape().distance(c);
        if (shapeDistance.isPresent()) {
          if (shapeDistance.get() < minDistance) {
            minDistance = envelopeDistance;
            closest = r;
          }
        }
      }
    }
    if (closest != null) {
      this.lastLineId = closest.getId();
    }
    return closest;
  }

  /**
   *
   * @param lineId The long numeric ID of the line.
   * @return The double shapeDistance in miles along the line.
   * @throws gov.ca.water.shapelite.MissingArgumentException if an argument like
   * latitude or longitude is missing.
   * @throws java.io.FileNotFoundException If the marker or line shapefile is
   * not found.
   * @throws java.io.IOException if the marker or line shapefile cannot be read.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error converting from WGS84 to UTM Zone 10.
   */
  public final double getMiles(Long lineId) throws MissingArgumentException,
      FileNotFoundException, IOException, ProjectionException {
    if (longitude == null) {
      throw new MissingArgumentException("The longitude value must "
          + "be defined to calculate miles.");
    }
    if (latitude == null) {
      throw new MissingArgumentException("The latitude value must "
          + "be defined to calculate miles.");
    }

    this.lastLineId = lineId;

    if (!initialized) {
      initialize();
    }

    Coord c = new CoordXY(longitude, latitude);

    if (riverset.containsKey(lineId)) {
      River r = riverset.get(lineId);
      Optional<Coord> online = r.closestPointTo(c);
      if (online.isPresent()) {
        return r.interpolate(online.get());
      }
    }

    return ERROR_CODE;
  }

  /**
   * Reprojects a coordinate from WGS84 latitude, longitude coordinates to UTM
   * Zone 10 North. This only works near California.
   *
   * @param coord The Coordinate to transfer to UTM coordinates.
   * @return The Coord in UTM Coordinates.
   * @throws ProjectionException If there was an error reprojecting the point.
   */
  private Coord toUtm(Coord coord) throws ProjectionException {
    return gov.ca.water.shapelite.projection.Reproject.reprojectCoordinate(coord,
        WGS84, UTM);
  }

  /**
   * Gets a boolean value that is true if the lineShapefile is specified, the
   * lineIdField is specified and the markerLineIdField is specified.
   *
   * @return Boolean, true if this tool should use line shapes.
   */
  public final boolean isUsingLines() {
    return usingLines;
  }

  /**
   * Gets the  <code>String</code> path to the line shapefile.
   *
   * @return the lineShapefile
   */
  public final String getLineShapefile() {
    return lineShapefile;
  }

  /**
   * (Optional) Sets the  <code>String</code> path to the line shapefile. If
   * lines are not specified, straight line distances will be assumed between
   * marker points. Setting this also sets usingLines to true.
   *
   * @param lineShapefile the lineShapefile to set
   */
  public final void setLineShapefile(String lineShapefile) {
    this.lineShapefile = lineShapefile;
    initialized = false;
    usingLines = true;
  }

  /**
   * Gets the <code>String</code> path to the mile marker shapefile.
   *
   * @return the pointShapefile
   */
  public final String getPointShapefile() {
    return mileMarkerShapefile;
  }

  /**
   * (Required) Sets the <code>String</code> complete or relative path to the
   * point shapefile that is being used to supply distances.
   *
   * @param pointShapefile the pointShapefile to set
   */
  public final void setPointShapefile(String pointShapefile) {
    this.mileMarkerShapefile = pointShapefile;
    initialized = false;
  }

  /**
   * Gets the <code>String</code> column name in the line shapefile that
   * identifies the lines uniquely.
   *
   * @return the lineIdField
   */
  public final String getLineIdField() {
    return lineIdField;
  }

  /**
   * (Optional) Sets the <code>String</code> column name in the line shapefile
   * that identifies the lines uniquely. The values in this column should match
   * the values in the mileMarkerLineIdField. If the line shapefile is used, but
   * this is not specified, then the shape index is used to identify lines
   * instead.
   *
   * @param lineIdField the lineIdField to set
   */
  public final void setLineIdField(String lineIdField) {
    this.lineIdField = lineIdField;
    initialized = false;
  }

  /**
   * Gets the <code>String</code> column name used to classify the marker
   * points. If a line shapefile is used, the values in this field should match
   * the values in the lineIdField.
   *
   * @return the makerLineIdField
   */
  public final String getMileMarkerLineIdField() {
    return mileMarkerLineIdField;
  }

  /**
   * (Recommended) Sets the <code>String</code> column name used to classify the
   * marker points. If lines are being used, then the values in this field
   * should match the values in the lineIdField. If this value is null, or the
   * lineIdField is null and lines are being used then physical shapeDistance
   * will be used to make the match, but may be wrong near junctions. If this is
   * null and lines are not being used, then all the marker lines are assumed to
   * be part of a single continuous set.
   *
   * @param mileMarkerLineIdField The string field that is used to identify the
   * line ID.
   */
  public final void setMileMarkerLineIdField(String mileMarkerLineIdField) {
    this.mileMarkerLineIdField = mileMarkerLineIdField;
    initialized = false;
  }

  /**
   * Gets the <code>String</code> value for the column name that controls the
   * mile marker distances used during shapeDistance calculations.
   *
   * @return the mileMarkerDistanceField
   */
  public final String getMileMarkerDistanceField() {
    return mileMarkerDistanceField;
  }

  /**
   * (Required) Sets the <code>String</code> column name that controls which
   * column will provide the shapeDistance units to use for measurements.
   *
   * @param mileMarkerDistanceField the <code>String</code> column name to set
   */
  public final void setMileMarkerDistanceField(
      String mileMarkerDistanceField) {
    this.mileMarkerDistanceField = mileMarkerDistanceField;
    initialized = false;
  }

  /**
   * Gets the most recently specified latitude. This parameter can change
   * without reinitializing the internal data structures, and is designed to be
   * different each time.
   *
   * @return the <code>Double</code> latitude
   */
  public final Double getLatitude() {
    return latitude;
  }

  /**
   * (Required) Sets the <code>Double</code> geographic latitude (Y) in decimal
   * degrees using WGS84 coordinates.
   *
   * @param latitude the latitude to set in decimal degrees.
   */
  public final void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  /**
   * Gets the most recently assigned geographic longitude.
   *
   * @return the <code>Double</code> longitude
   */
  public final Double getLongitude() {
    return longitude;
  }

  /**
   * (Required) Sets the <code>Double</code> geographic longitude (X) in decimal
   * degrees using WGS84 coordinates. The longitude parameter should be negative
   * for the western hemisphere.
   *
   * @param longitude the longitude to set
   */
  public final void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  /**
   * Gets the most recently assigned field name which should be used in order to
   * label the rivers.
   *
   * @return the lineLabelField
   */
  public final String getLineLabelField() {
    return lineLabelField;
  }

  /**
   * (Optional) Sets the string field name that controls the text returned when
   * determining the closest river. This is only used when finding the name of
   * the closest river.
   *
   * @param lineLabelField the lineLabelField to set
   */
  public final void setLineLabelField(String lineLabelField) {
    this.lineLabelField = lineLabelField;
    initialized = false;
  }

  /**
   * @param usingLines the usingLines to set
   */
  public final void setUsingLines(boolean usingLines) {
    this.usingLines = usingLines;
  }

  /**
   * (FOR UNIT TEST) This gets the point reader to use instead of opening a
   * path.
   *
   * @return the pointReader
   */
  public final ShapefileReader getPointReader() {
    return pointReader;
  }

  /**
   * (FOR UNIT TEST) This allows testing using an embedded shapefile, rather
   * than using the file system, which might not be consistent.
   *
   * @param pointReader the pointReader to set
   */
  public final void setPointReader(ShapefileReader pointReader) {
    this.pointReader = pointReader;
    initialized = false;
  }

  /**
   * (FOR UNIT TEST) This allows testing using an embedded shapefile, rather
   * than using the file system, which might not be consistent.
   *
   * @return the lineReader
   */
  public final ShapefileReader getLineReader() {
    return lineReader;
  }

  /**
   * (FOR UNIT TEST) This allows testing using an embedded shapefile, rather
   * than using the file system, which might not be consistent.
   *
   * @param lineReader the lineReader to set.
   */
  public final void setLineReader(ShapefileReader lineReader) {
    this.lineReader = lineReader;
    usingLines = true;
    initialized = false;
  }

  /**
   * Gets the last line ID used.
   *
   * @return the lastLineId.
   */
  public final Long getLastLineId() {
    return lastLineId;
  }

  /**
   * Sets the last line ID used.
   *
   * @param lastLineId the lastLineId to set.
   */
  public final void setLastLineId(Long lastLineId) {
    this.lastLineId = lastLineId;
  }

}
