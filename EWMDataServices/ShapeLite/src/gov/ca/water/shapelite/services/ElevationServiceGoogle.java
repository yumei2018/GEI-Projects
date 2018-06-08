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
package gov.ca.water.shapelite.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.coordinate.HasXY;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ElevationServiceGoogle implements ElevationService {

  /**
   * Only 500 points can be requested in one go.
   */
  private static final int MAX_COUNT = 499;

  /**
   * The maximum latitude.
   */
  private static final double MAX_LAT = 90.0;

  /**
   * The maximum longitude.
   */
  private static final double MAX_LON = 180.0;

  /**
   * Given a list of X,Y coordinates where the X and Y are latitude and
   * longitude (WGS84), this will return the same list of locations in Z
   * coordinates. The maximum number of locations is 500. If this list contains
   * more than 500, the first 500 will be chosen from this list.
   *
   * @param startLatLon The start coordinate of the path. This must be in
   * latitude and longitude.
   * @param endLatLon The end coordinate of the path. This must be in latitude
   * and longitude.
   * @return the List of X,Y,Z coordinates from google.
   * @throws gov.ca.water.shapelite.services.ElevationServiceException If the
   * latitude or longitude are outside the acceptable bounds.
   */
  @Override
  public final List<CoordZ> getElevations(HasXY startLatLon, HasXY endLatLon)
      throws ElevationServiceException {
    return getElevations(startLatLon, endLatLon, MAX_COUNT);
  }

  /**
   * Given a list of X,Y coordinates where the X and Y are latitude and
   * longitude (WGS84), this will return the same list of locations in Z
   * coordinates. The maximum number of locations is 500. If this list contains
   * more than 500, the first 500 will be chosen from this list.
   *
   * @param startLatLon The start coordinate of the path. This must be in
   * latitude and longitude.
   * @param endLatLon The end coordinate of the path. This must be in latitude
   * and longitude.
   * @param numPoints The number of points, (maximum 500).
   * @return the List of X,Y,Z coordinates from google.
   * @throws gov.ca.water.shapelite.services.ElevationServiceException If the
   * latitude or longitude are outside the acceptable bounds.
   */
  public final List<CoordZ> getElevations(HasXY startLatLon, HasXY endLatLon,
      int numPoints) throws ElevationServiceException {
    try {
      String paramText = "";
      int count = numPoints;
      if (count > MAX_COUNT) {
        count = MAX_COUNT;
      }
      if (count < 0) {
        count = 0;
      }
      CoordXY startXY = new CoordXY(startLatLon);
      if (startLatLon.getX() < -MAX_LON || startLatLon.getX() > MAX_LON
          || startLatLon.getY() < -MAX_LAT || startLatLon.getX() > MAX_LAT
          || endLatLon.getX() < -MAX_LON || endLatLon.getX() > MAX_LON
          || endLatLon.getY() < -MAX_LAT || endLatLon.getY() > MAX_LAT) {
        throw new ElevationServiceException(
            "The latitude and longitude was not valid.");
      }

      URL url = new URL("https://maps.googleapis.com/maps/api/elevation/json?path="
          + startLatLon.getY() + "," + startLatLon.getX() + "|" + endLatLon.getY()
          + "," + endLatLon.getX() + "&samples=" + count
          + "&key=AIzaSyDiNj0nDVScpXLwJRzw40akekGZ9a0K75g" + paramText);
      String json = getString(url.openStream());
      Gson gson = new GsonBuilder().create();
      ElevationResult googleResult = gson.fromJson(json, ElevationResult.class);

      if (googleResult.getResults() != null && googleResult.getResults().size() > 0) {
        List<CoordZ> result = new ArrayList<>();
        for (ElevationResultPoint point : googleResult.getResults()) {
          ElevationResultCoord c = point.getLocation();
          CoordZ ele = new CoordZ(c.getLng(), c.getLat(), point.getElevation());
          ele.setM(ele.distanceInFeet(startXY));
          result.add(ele);
        }
        return result;
      }
      return null;
    } catch (IOException ex) {
      throw new ElevationServiceException(ex);
    }
  }

  /**
   * Given a list of X,Y coordinates where the X and Y are latitude and
   * longitude (WGS84), this will return the same list of locations in Z
   * coordinates. The maximum number of locations is 500. If this list contains
   * more than 500, the first 500 will be chosen from this list.
   *
   * @param locations THe list of locations to get elevations for.
   * @return the List of X,Y,Z coordinates from google.
   * @throws IOException If there was an error reading the returned text.
   */
  public final List<CoordZ> getElevations(List<Coord> locations) throws IOException {
    String paramText = "";
    int count = 0;
    for (Coord coord : locations) {
      if (count == 0) {
        paramText += "|";
      }
      if (count == MAX_COUNT) {
        break;
      }
      paramText += coord.getX() + "," + coord.getY();
      count++;
    }

    URL url
        = new URL("https://maps.googleapis.com/maps/api/elevation/json?locations="
            + paramText);
    String json = getString(url.openStream());
    Gson gson = new GsonBuilder().create();
    ElevationResult googleResult = gson.fromJson(json, ElevationResult.class);

    if (googleResult.getResults() != null && googleResult.getResults().size() > 0) {
      List<CoordZ> result = new ArrayList<>();
      for (ElevationResultPoint point : googleResult.getResults()) {
        ElevationResultCoord c = point.getLocation();
        CoordZ ele = new CoordZ(c.getLng(), c.getLng(), point.getElevation());
        result.add(ele);
      }
      return result;
    }
    return null;
  }

  /**
   * Given a single location with X,Y coordinates in latitude/longitude, this
   * will return a single location with Z elevation.
   *
   * @param location the location to find the elevation of.
   * @return A Coord object with X, Y and a Z value representing the elevation.
   * @throws IOException If there was an error reading the returned stream.
   */
  public final Coord getElevation(Coord location) throws IOException {
    URL url = new URL("https://maps.googleapis.com/maps/api/elevation/json?locations="
        + location.getY() + "," + location.getX());
    String json = getString(url.openStream());
    Gson gson = new GsonBuilder().create();
    ElevationResult googleResult = gson.fromJson(json, ElevationResult.class);

    if (googleResult.getResults() != null && googleResult.getResults().size() > 0) {
      ElevationResultPoint point = googleResult.getResults().get(0);
      ElevationResultCoord c = point.getLocation();
      Coord result = new CoordZ(c.getLng(), c.getLng(), point.getElevation());
      return result;
    }
    return null;
  }

  /**
   * Gets the entire stream as a string by using the scanner.
   *
   * @param stream The stream to read.
   * @return The String result.
   */
  private String getString(InputStream stream) {
    Scanner scanner = new Scanner(stream).useDelimiter("\\A");
    if (scanner.hasNext()) {
      return scanner.next();
    }
    return null;
  }

  @Override
  public final List<CoordZ> getElevations(HasXY start, HasXY end, double buffer,
      int count)
      throws ElevationServiceException {
    return getElevations(start, end, count);
  }
}
