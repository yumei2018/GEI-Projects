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
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DistanceTool {

  /**
   * Meter to mile conversion constant.
   */
  private static final double METERS_TO_MILES = 0.000621371192;

  /**
   * The join tolerance for the tool.
   */
  private static final double JOIN_TOLERANCE = 10;

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

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The field name storing start points.
   */
  private String startpointIdField;
  /**
   * The list of string start point ID values.
   */
  private List<String> startpointIdValues;
  /**
   * The shapefile that can store start and end points.
   */
  private String startpointShapefile;
  /**
   * The list of shapes that store start/end points.
   */
  private List<Shape> startpointShapes;
  /**
   * The field name that stores the line ID field.
   */
  private String lineIdField;
  /**
   * The list of string values for all the line ids.
   */
  private List<String> lineIdValues;
  /**
   * The string filename of the shapefile storing lines to get distance for.
   */
  private String lineShapefile;
  /**
   * This list of actual line shapes for getting distances.
   */
  private List<Shape> lineShapes;
  /**
   * The field containing start values.
   */
  private String startField;
  /**
   * The list of Strings containing valid start values.
   */
  private List<String> validStartValues;
  /**
   * The list of string containing start values.
   */
  private List<String> startValues;
  /**
   * The double latitude for finding the distance to.
   */
  private double latitude;
  /**
   * The double longitude for finding distance too.
   */
  private double longitude;
  /**
   * Boolean true if start/end points are used to control directions.
   */
  private boolean usingStartpoints;
  /**
   * The list of levees structures.
   */
  private List<Levee> levees;
  /**
   * The envelope containing the start point.
   */
  private Envelope startpointBox;
  /**
   * The string name of the attribute field storing line names.
   */
  private String lineNameField;
  /**
   * The list of actual line name values read from the attribute field.
   */
  private List<String> lineNameValues;
  /**
   * The Levee object closest to the target point.
   */
  private Levee closestLevee;
  /**
   * The double distance of the closest distance to the target point.
   */
  private double closestDistance;
  /**
   * The coordinate location to find the distance too.
   */
  private Coord location;

  //</editor-fold>
  /**
   * This uses embedded shapefiles to handle the levees.
   */
  public DistanceTool() {
    lineIdField = "LIS_CODE";
    startpointIdField = "LIS_CODE";
    startField = "START";
    lineNameField = "SEG_NAME";
    validStartValues = new ArrayList<>();
    validStartValues.add("Start");
    validStartValues.add("START_SEG");

  }

  /**
   * Gets the miles along the levee given a latitude and longitude.
   *
   * @param latitude the double latitude to get the distance to.
   * @param longitude the double longitude to get the distance to.
   * @return the double distance in miles.
   * @throws ShapefileException thrown if the shapefile can't be read.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error converting from WGS84 to UTM Zone 10.
   */
  public final double getMiles(final double latitude,
      final double longitude) throws ShapefileException, ProjectionException {
    this.setLatitude(latitude);
    this.setLongitude(longitude);
    return getMiles();
  }

  /**
   * Gets the Levee distance in miles using the latitude and longitude set in
   * properties.
   *
   * @return The double distance in miles.
   * @throws gov.ca.water.shapelite.ShapefileException If there was an error
   * reading the shapefile.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error converting from WGS84 to UTM Zone 10.
   */
  public final double getMiles() throws ShapefileException, ProjectionException {
    return getClosestLevee().getParts().distanceAlong(location)
        * METERS_TO_MILES;
  }

  /**
   * Gets the Levee distance in miles using the latitude and longitude set in
   * properties.
   *
   * @param maxSeg The integer maximum segment to be considered.
   * @return the double distance in miles.
   * @throws gov.ca.water.shapelite.ShapefileException If there was an error
   * reading the shapefile.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error converting from WGS84 to UTM Zone 10.
   */
  public final double getMiles(int maxSeg) throws ShapefileException,
      ProjectionException {
    return getClosestLevee().getParts().distanceAlong(location, maxSeg)
        * METERS_TO_MILES;
  }

  /**
   * This is just like getMiles except that by default, if no start point is
   * available, the last point in the shape will be used as the starting point.
   *
   * @param latitude The geographic latitude in WGS84 coordinates.
   * @param longitude The geographic longitude in WGS84 coordinates.
   * @return The double distance for the specified coordinate.
   * @throws ShapefileException If there was an error reading the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error converting from WGS84 to UTM Zone 10.
   */
  public final double getMilesFromEnd(double latitude, double longitude)
      throws ShapefileException, ProjectionException {
    this.setLatitude(latitude);
    this.setLongitude(longitude);
    return getMilesFromEnd();
  }

  /**
   * This is just like getMiles except that by default, if no start point is
   * available, the last point in the shape will be used as the starting point.
   *
   * @return The double distance of miles from the end.
   * @throws ShapefileException If there was an error reading the shapefile.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error converting from WGS84 to UTM Zone 10.
   */
  public final double getMilesFromEnd() throws ShapefileException, ProjectionException {
    return getClosestLevee().getParts().distanceAlong(location)
        * METERS_TO_MILES;
  }

  /**
   * Gets the closest levee to the most recently specified coordinates. Setting
   * new coordinates will cause this to be re-calculated.
   *
   * @return The closest Levee object.
   * @throws ShapefileException If there was an error reading the shapefile.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error converting from WGS84 to UTM Zone 10.
   */
  public final Levee getClosestLevee() throws ShapefileException,
      ProjectionException {
    long start = System.currentTimeMillis();
    if (closestLevee == null) {
      location = new CoordXY(longitude, latitude);
      location = toUtm(location);
      double minDist = Double.MAX_VALUE;
      if (getLevees() == null) {
        buildLevees(true);
      }
      for (Levee levee : getLevees()) {
        double dist = levee.getBounds().distance(location);
        if (dist < minDist) {
          // Part distance is slower than bounds distance, so only do it
          // when useful.
          dist = levee.getParts().distanceTo(location);
          if (dist < minDist) {
            closestLevee = levee;
            minDist = dist;
          }
        }
      }
      closestDistance = minDist * METERS_TO_MILES;
    }
    long end = System.currentTimeMillis();
    //System.out.println("Time: " + Long.toString(end-start) + " ms.");
    return closestLevee;
  }

  /**
   * Reprojects a coordinate from WGS84 latitude, longitude coordinates to UTM
   * Zone 10 North. This only works near California.
   *
   * @param coord The Coordinate to transfer to UTM coordinates.
   * @return The Coord in UTM Coordinates.
   * @throws ProjectionException If there was an error reprojecting the point.
   */
  private Coord toUtm(Coord coord) throws
      gov.ca.water.shapelite.projection.ProjectionException {
    return gov.ca.water.shapelite.projection.Reproject.reprojectCoordinate(coord,
        WGS84, UTM);
  }

  /**
   * Reprojects a coordinate from WGS84 latitude, longitude coordinates to UTM
   * Zone 10 North. This only works near California.
   *
   * @param coord The Coordinate to transfer to UTM coordinates.
   * @return The Coord in UTM Coordinates.
   * @throws ProjectionException If there was an error reprojecting the point.
   */
  private Coord fromUtm(Coord coord) throws
      gov.ca.water.shapelite.projection.ProjectionException {
    return gov.ca.water.shapelite.projection.Reproject.reprojectCoordinate(coord,
        UTM, WGS84);
  }

  /**
   * Gets the linear distance in miles to the closest lineShape from the
   * specified latitude and longitude.
   *
   * @param latitude The geographic latitude in WGS84 coordinates.
   * @param longitude The geographic longitude in WGS84 coordinates.
   * @return The double distance.
   * @throws ShapefileException If there was an error reading the line
   * shapefile.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error converting from WGS84 to UTM Zone 10.
   */
  public final double getDistanceToClosestLineShape(double latitude,
      double longitude) throws ShapefileException, ProjectionException {
    this.setLongitude(longitude);
    this.setLatitude(latitude);
    return getDistanceToClosestLineShape();
  }

  /**
   * Gets the linear distance in miles to the closest line shape from the
   * latitude and longitude specified in the latitude and longitude properties.
   *
   * @return The double distance to the closest line shape.
   * @throws gov.ca.water.shapelite.ShapefileException If there was an error
   * reading the shapefile.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error converting from WGS84 to UTM Zone 10.
   */
  public final double getDistanceToClosestLineShape()
      throws ShapefileException, ProjectionException {
    getClosestLevee(); // Call this to ensure that the calculations are done.
    return closestDistance;
  }

  /**
   *
   * @param idValue The string id in the lineIdField to inspect.
   * @param distance The double distance in miles
   * @return The list of coordinates that match the corresponding ID value.
   * @throws gov.ca.water.shapelite.ShapefileException If there was an error
   * reading the shapefile.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error converting from WGS84 to UTM Zone 10.
   */
  public final List<Coord> getCoordinatesForDistance(String idValue,
      double distance) throws ShapefileException, ProjectionException {
    distance = distance / METERS_TO_MILES;
    List<Coord> result = new ArrayList<>();
    Optional<Levee> levee = getLevee(idValue);
    if (levee.isPresent()) {
      List<PartTreeNode> nodes = levee.get().getParts().getConnectedNodes();
      for (PartTreeNode node : nodes) {
        double start = node.startDistance();
        double end = node.endDistance();
        if (start <= distance && end >= distance) {
          Optional<Coord> coordC = node.getPart().getCoordinateAtDistance(distance
              - node.startDistance());
          if (coordC.isPresent()) {
            Coord d = fromUtm(coordC.get());
            result.add(d);
          }
        } else if (node.getParent() != null) {
          double st = node.startDistance();
          double pe = node.getParent().endDistance();
          if (distance < st && distance > pe) {
            Optional<Coord> a = node.getParent().end();
            Optional<Coord> b = node.start();
            if (a.isPresent() && b.isPresent()) {
              double d = distance - node.getParent().endDistance();
              double rat = d / node.getGap();
              double x = b.get().getX() * rat + a.get().getX() * (1 - rat);
              double y = b.get().getY() * rat + a.get().getY() * (1 - rat);
              Coord c = new CoordXY(x, y);
              Coord e = fromUtm(c);
              result.add(e);
            }

          }
        }
      }
    }
    return result;
  }

  /**
   * Gets the levee object associated with the specified ID.
   *
   * @param idValue The integer levee ID value.
   * @return The Levee object.
   * @throws ShapefileException If there was an error reading the shapefile.
   */
  private Optional<Levee> getLevee(String idValue) throws ShapefileException {
    if (getLevees() == null) {
      buildLevees(false);
    }
    for (Levee levee : getLevees()) {
      String id = levee.getLisCode();
      if (id != null && id.equals(idValue)) {
        return Optional.of(levee);
      }
    }
    return Optional.empty();
  }

  /**
   * Gets the string ID associated with the lineIdField for the line shape that
   * is closest to the specified longitude and latitude.
   *
   * @param latitude The geographic latitude in WGS84 coordinates.
   * @param longitude the geographic longitude in WGS84 coordinates.
   * @return the String name of the closest line shape.
   * @throws ShapefileException If there was an error reading the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error converting from WGS84 to UTM Zone 10.
   */
  public final Optional<String> getClosestLineName(double latitude, double longitude)
      throws ShapefileException, ProjectionException {
    this.setLatitude(latitude);
    this.setLongitude(longitude);
    return getClosestLineName();
  }

  /**
   * Gets the string name of the closest line to the coordinates used for this
   * distance tool.
   *
   * @return String name of the closest line.
   * @throws ShapefileException If there was an error reading the shapefile.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error converting from WGS84 to UTM Zone 10.
   */
  public final Optional<String> getClosestLineName() throws ShapefileException,
      ProjectionException {
    Levee result = getClosestLevee();
    if (result != null) {
      return Optional.of(getClosestLevee().getName());
    }
    return Optional.empty();
  }

  /**
   * Gets the string ID associated with the lineIdField for the line shape that
   * is closest to the specified longitude and latitude.
   *
   * @param latitude The geographic latitude in WGS84 coordinates.
   * @param longitude The geographic longitude in WGS84 coordinates.
   * @return the String name of the line shape closest to the specified
   * coordinates.
   * @throws ShapefileException If there was an error reading the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error converting from WGS84 to UTM Zone 10.
   */
  public final String getClosestLineShape(double latitude, double longitude)
      throws ShapefileException, ProjectionException {
    this.setLongitude(longitude);
    this.setLatitude(latitude);
    return getClosestLineShape();
  }

  /**
   * Gets the string ID associated with the lineIdField for the line shape that
   * is closest to the specified longitude and latitude in the properties.
   *
   * @return The string LIS Code for the closest line shape.
   * @throws ShapefileException If there was an error reading the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error converting from WGS84 to UTM Zone 10.
   */
  public final String getClosestLineShape() throws ShapefileException,
      ProjectionException {
    return getClosestLevee().getLisCode();
  }

  /**
   *
   * @param latitude The geographic latitude in WGS84 coordinates.
   * @param longitude The geographic longitude in WGS84 coordinates.
   * @param lineIdValue the string identifier in the lineIdField column that
   * identifies the line on which to do calculations. This will occur on the
   * first instance of the specified Id value.
   * @return The double distance in miles.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error converting from WGS84 to UTM Zone 10.
   */
  public final double getMiles(double latitude, double longitude,
      String lineIdValue) throws ProjectionException {
    double result = 0;
    Coord spot = new CoordXY(longitude, latitude);
    spot = toUtm(spot);

    for (Levee levee : getLevees()) {
      if (levee.getLisCode().equals(lineIdValue)) {
        double dist = levee.getParts().distanceAlong(spot);
        return dist * METERS_TO_MILES;
      }
    }
    return result;
  }

  /**
   * This method clears the internal data members. This will allow the program
   * to reload the data members differently.
   */
  public final void invalidate() {
    setLevees(null);
    lineShapes = null;
    startpointShapes = null;
    closestLevee = null;
    closestDistance = -1;
    location = null;
  }

  /**
   * Lazy construction of lines. If the lines have already been built, we assume
   * that we don't need to rebuild them. If the lineShapes are null, however,
   * then we must rebuild the lines.
   *
   * @throws ShapefileException If there was a problem opening the line
   * shapefile.
   */
  private void getLineShapes() throws ShapefileException {
    if (lineShapefile == null) {
      throw new ShapefileException("The line shapefile is not optional and must"
          + " be specified.");
    }
    ShapefileReader lines = new ShapefileReader();
    try {
      lines.open(lineShapefile);
      lineShapes = lines.getShapes();
      if (this.usingStartpoints) {
        if (this.lineIdField == null) {
          throw new ShapefileException("If usingEndpoints is true, an "
              + "lineIdField must be specified for the lines to match their"
              + " respective endpoints.");
        }
      }
      if (lineIdField != null && lines.getAttributes().getField(lineIdField)
          != null) {
        // A null value here is only a problem if we intend to do a lookup
        // by id.
        Optional<String[]> items = lines.getAttributes().
            getFieldStrings(lineIdField);
        if(items.isPresent()){
          lineIdValues = Arrays.asList(items.get());
        }

      }
      if (lineNameField != null && lines.getAttributes().
          getField(lineNameField) != null) {
        Optional<String[]> items = lines.getAttributes().
            getFieldStrings(getLineNameField());
        if(items.isPresent()){
           lineNameValues = Arrays.asList(items.get());
        }


      }
    } catch (FileNotFoundException ex) {
      throw new ShapefileException("The line shapefile: " + lineShapefile
          + " could not be found.", ex);
    } catch (IOException ex) {
      throw new ShapefileException("There was a problem reading the line"
          + " shapefile: " + lineShapefile, ex);
    }
  }

  /**
   * Opens the start point shapefile in order to determine which direction lines
   * begin.
   *
   * @throws ShapefileException If there was an error opening the start point
   * shapefile.
   */
  private void getStartpointShapes() throws ShapefileException {

    if (startpointShapefile == null) {
      throw new ShapefileException("If usingEndpoints is true, an "
          + "EndpointShapefile is"
          + " not optional and must be specified.");
    }
    ShapefileReader points = new ShapefileReader();
    try {
      points.open(startpointShapefile);
      startpointShapes = points.getShapes();
      startpointBox = points.header.getBounds();
      if (startpointIdField == null) {
        throw new ShapefileException("If usingEndpoints is true, an "
            + "endpointIdField "
            + "must be specified for the endpoints to match the line "
            + "shapes.");
      }
      if (startpointIdField != null) {
        // A null value here is only a problem if we intend to do a lookup by
        // id.
        Optional<String[]> items = points.getAttributes().
            getFieldStrings(startpointIdField);
        if(items.isPresent()){
          startpointIdValues = Arrays.asList(items.get());
        }

      }
      if (startField != null) {
        // startField can be null.  In that case, all points are assumed to be
        // start points.
        Optional<String[]> items = points.getAttributes().
            getFieldStrings(startField);
        startValues = Arrays.asList(items.get());
      }
    } catch (FileNotFoundException ex) {
      throw new ShapefileException("The line shapefile: " + lineShapefile
          + " could not be found.", ex);
    } catch (IOException ex) {
      throw new ShapefileException("There was a problem reading the line"
          + " shapefile: " + lineShapefile, ex);
    }

  }

  /**
   * While it is planned for this tool to be used for shapes other than levees,
   * this method simply constructs in memory objects.
   *
   * @param useEnd Boolean, true if the endpoints should be used to control the
   * direction of the lines.
   * @throws ShapefileException If there was an error reading the shapefile.
   */
  private void buildLevees(boolean useEnd) throws ShapefileException {
    Long startTime = System.currentTimeMillis();
    if (lineShapes == null) {
      getLineShapes();
    }
    if (usingStartpoints) {
      getStartpointShapes();
    }

    setLevees(new ArrayList<Levee>());
    for (int iLine = 0; iLine < lineShapes.size(); iLine++) {
      Levee levee = new Levee();
      Shape shape = lineShapes.get(iLine);
      levee.setLisCode(lineIdValues.get(iLine));
      levee.setName(lineNameValues.get(iLine));
      levee.setBounds(shape.getEnvelope());
      PartTree tree = new PartTree(JOIN_TOLERANCE);

      Coord start = shape.first();
      if (useEnd) {
        start = shape.last();
      }

      // The Id should match
      levee.setStart(getStart(levee.getLisCode(), start));

      tree.add(shape, levee.getStart());
      levee.setParts(tree);
      getLevees().add(levee);
    }
    long endTime = System.currentTimeMillis();
    System.out.println("+Create Levee Time: " + Long.toString(endTime
        - startTime) + " ms.");
    closestLevee = null;

  }

  /**
   * Gets the start coordinate for the specified endpointId and coordinate.
   *
   * @param endpointId The endpointId.
   * @param def The coordinate definition.
   * @return The start Coordinate.
   */
  private Coord getStart(String endpointId, Coord def) {
    Coord result = def;
    if (startpointIdValues == null
        || startpointIdValues.isEmpty() || !this.usingStartpoints) {
      return def;
    }
    for (int i = 0; i < startpointIdValues.size(); i++) {
      if (endpointId.equals(startpointIdValues.get(i))) {
        if (validStartValues.contains(startValues.get(i))) {
          result = startpointShapes.get(i).first();
          break;
        }
      }
    }
    return result;
  }

  /**
   * Gets the geographic latitude in WGS84 coordinates.
   *
   * @return the latitude
   */
  public final double getLatitude() {
    return latitude;
  }

  /**
   * Sets the geographic latitude in WGS84 coordinates.
   *
   * @param latitude the latitude to set
   */
  public final void setLatitude(double latitude) {
    this.latitude = latitude;
    closestLevee = null;
  }

  /**
   * Gets the geographic longitude in WGS84 coordinates.
   *
   * @return the longitude
   */
  public final double getLongitude() {
    return longitude;
  }

  /**
   * Sets the geographic longitude in WGS84 coordinates.
   *
   * @param longitude the longitude to set
   */
  public final void setLongitude(double longitude) {
    this.longitude = longitude;
    closestLevee = null;
  }

  /**
   * @return the String field name of the field that contains a "Start" marker
   * for the point that is the start point, in order to distinguish between
   * potentially several points with the same ID.
   */
  public final String getStartField() {
    return startField;
  }

  /**
   * @param startField the String field name of the field that contains a
   * "Start" marker for the point that is the start point, in order to
   * distinguish between potentially several points with the same ID.
   */
  public final void setStartField(String startField) {
    this.startField = startField;
  }

  /**
   * @return the optional lineShapefile name.
   */
  public final String getLineShapefile() {
    return lineShapefile;
  }

  /**
   * @param lineShapefile the lineShapefile can be specified. If no line
   * shapefile is specified, then the embedded line shapefile will be used.
   */
  public final void setLineShapefile(String lineShapefile) {
    this.lineShapefile = lineShapefile;
  }

  /**
   * @return the endpointShapefile
   */
  public final String getStartpointShapefile() {
    return startpointShapefile;
  }

  /**
   * Sets the string field name of the attribute column on the line shapefile
   * that joins with the matching shape on the point shapefile.
   *
   * @param endpointShapefile the endpointShapefile to set
   */
  public final void setStartpointShapefile(String endpointShapefile) {
    this.startpointShapefile = endpointShapefile;
    this.usingStartpoints = endpointShapefile != null;

  }

  /**
   * Gets the string field name of the attribute column on the line shapefile
   * that joins with the matching shape on the point shapefile.
   *
   * @return the lineIdField
   */
  public final String getLineIdField() {
    return lineIdField;
  }

  /**
   * @param lineIdField the lineIdField to set
   */
  public final void setLineIdField(String lineIdField) {
    this.lineIdField = lineIdField;
  }

  /**
   * Gets the string field name of the attribute column on the point shapefile
   * that joins with the matching shape on the line shapefile.
   *
   * @return the endpointIdField
   */
  public final String getStartpointIdField() {
    return startpointIdField;
  }

  /**
   * Sets the string field name of the attribute column on the point shapefile
   * that joins with the matching shape on the line shapefile.
   *
   * @param endpointIdField the endpointIdField to set
   */
  public final void setStartpointIdField(String endpointIdField) {
    this.startpointIdField = endpointIdField;
  }

  /**
   * @return the boolean that controls whether the endpoints are used in the
   * calculation. By default this is true, but this can be disabled.
   */
  public final boolean isUsingStartpoints() {
    return usingStartpoints;
  }

  /**
   * Sets a boolean indicating whether or not the distance tool should use
   * endpoints to order line elements. If not, then the first coordinate in
   *
   * @param usingEndpoints the usingEndpoints to set
   */
  public final void setUsingStartpoints(boolean usingEndpoints) {
    this.usingStartpoints = usingEndpoints;
  }

  /**
   * @return the startValue
   */
  public final List<String> getValidStartValues() {
    return validStartValues;
  }

  /**
   * @param startValue the startValue to set
   */
  public final void setValidStartValues(List<String> startValue) {
    this.validStartValues = startValue;
  }

  /**
   * Gets the list of valid string identifiers that can appear in the startField
   * to indicate that the point is a valid start point. If startField is null,
   * this is not used. However, if startField is set, this cannot be null.
   *
   * @return the startValues
   */
  public final List<String> getStartValues() {
    return startValues;
  }

  /**
   * Sets the list of valid string identifiers that can appear in the startField
   * to indicate that the point is a valid start point. If startField is null,
   * this is not used. However, if startField is set, this cannot be null.
   *
   * @param startValues the startValues to set
   */
  public final void setStartValues(List<String> startValues) {
    this.startValues = startValues;
  }

  /**
   * @return the lineNameField
   */
  public final String getLineNameField() {
    return lineNameField;
  }

  /**
   * @param lineNameField the lineNameField to set
   */
  public final void setLineNameField(String lineNameField) {
    this.lineNameField = lineNameField;
  }

  /**
   * @return the levees
   */
  public final List<Levee> getLevees() {
    return levees;
  }

  /**
   * @param levees the levees to set
   */
  public final void setLevees(List<Levee> levees) {
    this.levees = levees;
  }
}
