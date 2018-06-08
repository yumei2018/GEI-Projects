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
package gov.ca.water.shapelite.map;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.coordinate.CoordFactory;
import gov.ca.water.shapelite.coordinate.CoordM;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import java.util.ArrayList;
import java.util.List;

/**
 * The Mercator transform is extremely important as it is used to translate
 * between the web Mercator projection that all the web tile sets are in, and
 * the more conventional latitude, longitude values that people are used to
 * working in.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class Mercator {

  /**
   * One quarter PI.
   */
  private static final double QUARTER_PI = Math.PI / 4;

  /**
   * The latitude and longitude span in TedMercator.
   */
  public static final int LL_SPAN = 360;

  /**
   * The maximum latitude, longitude in Ted Mercator.
   */
  public static final int LL_MAX = 180;

  /**
   * At the equator, there are 111320 meters in a decimal degree.
   */
  public static final double METERS_PER_DEGREE = 111320;

  /**
   * The number of feet in a meter.
   */
  public static final double FEET_PER_METER = 3.28084;

  /**
   * Creates a blank instance of the projector.
   */
  private Mercator() {
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Changes the specified envelope in Mercator coordinates into an equivalent
   * envelope in WGS84 coordinates. The Mercator coordinates keep longitude for
   * the X ordinate, but change longitude to match the Y positions on the Web
   * Mercator map tiles.
   *
   * @param env The Mercator envelope to translate.
   * @return The Envelope in latitude longitude coordinates.
   */
  public static Envelope fromMerc(Envelope env) {
    if (env == null) {
      throw new IllegalArgumentException("Parameter env cannot be null.");
    }
    Coord min = Mercator.fromMerc(env.getMin());
    Coord max = Mercator.fromMerc(env.getMax());
    return new DefaultEnvelope(min, max);
  }

  /**
   * Changes the specified coordinate in Mercator coordinates into an equivalent
   * envelope in WGS84 coordinates. The Mercator coordinates keep longitude for
   * the X ordinate, but change longitude to match the Y positions on the Web
   * Mercator map tiles.
   *
   * @param c The Mercator coordinate to translate to latitude, longitude.
   * @return The resulting latitude, longitude Coordinate.
   */
  public static Coord fromMerc(@NonNull Coord c) {
    if (c == null) {
      throw new IllegalArgumentException("Parameter c cannot be null.");
    }
    Coord copy = c.copy();
    fromMercator(c, copy);
    return copy;
  }

  /**
   * Changes the specified coordinate in Mercator coordinates into an equivalent
   * envelope in WGS84 coordinates. The Mercator coordinates keep longitude for
   * the X ordinate, but change longitude to match the Y positions on the Web
   * Mercator map tiles.
   *
   * @param c The Mercator coordinate to translate to latitude, longitude.
   * @return The resulting latitude, longitude Coordinate.
   */
  public static CoordZ fromMerc(@NonNull CoordZ c) {
    if (c == null) {
      throw new IllegalArgumentException("Parameter c cannot be null.");
    }
    CoordZ copy = CoordFactory.copy(c);
    fromMercator(c, copy);
    return copy;
  }

  /**
   * Changes the specified coordinate in Mercator coordinates into an equivalent
   * envelope in WGS84 coordinates. The Mercator coordinates keep longitude for
   * the X ordinate, but change longitude to match the Y positions on the Web
   * Mercator map tiles.
   *
   * @param c The Mercator coordinate to translate to latitude, longitude.
   * @return The resulting latitude, longitude Coordinate.
   */
  public static CoordM fromMerc(@NonNull CoordM c) {
    if (c == null) {
      throw new IllegalArgumentException("Parameter c cannot be null.");
    }
    CoordM copy = CoordFactory.copy(c);
    fromMercator(c, copy);
    return copy;
  }

  /**
   * Changes the specified coordinate in Mercator coordinates into an equivalent
   * envelope in WGS84 coordinates. The Mercator coordinates keep longitude for
   * the X ordinate, but change longitude to match the Y positions on the Web
   * Mercator map tiles.
   *
   * @param c The Mercator coordinate to translate to latitude, longitude.
   * @return The resulting latitude, longitude Coordinate.
   */
  public static CoordXY fromMerc(@NonNull CoordXY c) {
    if (c == null) {
      throw new IllegalArgumentException("Parameter c cannot be null.");
    }
    CoordXY copy = CoordFactory.copy(c);
    fromMercator(c, copy);
    return copy;
  }

  /**
   * Changes the specified coordinate in Mercator coordinates into an equivalent
   * envelope in WGS84 coordinates. The Mercator coordinates keep longitude for
   * the X ordinate, but change longitude to match the Y positions on the Web
   * Mercator map tiles.
   *
   * @param inCoord The Mercator coordinate to translate to latitude, longitude.
   * @param outCoord the WGS84 coordinate to host the result Y calculation.
   */
  public static void fromMercator(@NonNull Coord inCoord, @NonNull Coord outCoord) {
    if (inCoord == null) {
      throw new IllegalArgumentException("Parameter inCoord cannot be null.");
    }
    if (outCoord == null) {
      throw new IllegalArgumentException("Parameter outCoord cannot be null.");
    }
    outCoord.setY(mercToLat(inCoord.getY()));
  }

  /**
   * Create new shape instance with same parts and ShapeType of the argument after
   * applying fromMerc to each coordinate of the shape.
   * @param inshape
   * @return
   * @see ShapeType
   * @see Part
   * @see Coord
   */
  public static Shape fromMerc(@NonNull Shape inshape) {
    List<Part> parts = new ArrayList<>();

    for (Part part : inshape.getParts()) {
      List<Coord> coords = new ArrayList<>();
      for (Coord coordinate : part.getCoordinates()) {
        coords.add(Mercator.fromMerc(coordinate));
      }
      parts.add(new Part(coords));
    }
    Shape outshape = new Shape(inshape.getShapeType(), parts);
    return outshape;
  }

  /**
   * Create new shape instance with same parts and ShapeType of the argument after
   * applying fromMerc to each coordinate of the shape.
   * @param inshape
   * @return
   * @see ShapeType
   * @see Part
   * @see Coord
   */
  public static Shape toMerc(@NonNull Shape inshape) {
    List<Part> parts = new ArrayList<>();

    for (Part part : inshape.getParts()) {
      List<Coord> coords = new ArrayList<>();
      for (Coord coordinate : part.getCoordinates()) {
        coords.add(Mercator.toMerc(coordinate));
      }
      parts.add(new Part(coords));
    }
    Shape outshape = new Shape(inshape.getShapeType(), parts);
    return outshape;
  }

  /**
   * Changes the specified latitude into Mercator coordinates. Since the
   * longitude is unchanged, it can be useful to have a method that simply
   * translates the latitude. This is also called by the coordinate and envelope
   * translate methods.
   *
   * @param latitude The double precision latitude to translate.
   * @return The double precision latitude in Mercator coordinates.
   */
  public static double latToMerc(double latitude) {
    return Math.toDegrees(Math.log(Math.tan(QUARTER_PI
        + Math.toRadians(latitude) / 2)));
  }

  /**
   * Changes the specified envelope in Mercator coordinates into an equivalent
   * envelope in WGS84 coordinates. The Mercator coordinates keep longitude for
   * the X ordinate, but change longitude to match the Y positions on the Web
   * Mercator map tiles.
   *
   * @param y The double precision Y in Mercator coordinates to translate to
   * latitude.
   * @return The latitude.
   */
  public static double mercToLat(double y) {
    return Math.toDegrees(2 * Math.atan(Math.exp(Math.toRadians(y)))
        - Math.PI / 2);
  }

  /**
   * Translates the specified envelop from WGS84 coordinates to Mercator
   * coordinates. The Mercator coordinates keep longitude for the X ordinate,
   * but change longitude to match the Y positions on the Web Mercator map
   * tiles.
   *
   * @param env The latitude, longitude Envelope to translate.
   * @return an Envelope in Mercator coordinates.
   */
  public static Envelope toMerc(@NonNull Envelope env) {
    if (env == null) {
      throw new IllegalArgumentException("Parameter env cannot be null.");
    }
    Coord min = toMerc(env.getMin());
    Coord max = toMerc(env.getMax());
    return new DefaultEnvelope(min, max);
  }

  /**
   * This gets the distance in feet as observed at the equator.
   *
   * @param mercDistance in ted-mercator coordinates.
   * @return An approximation of the double distance.
   */
  public static double distanceFeet(double mercDistance) {
    return mercDistance * METERS_PER_DEGREE * FEET_PER_METER;
  }

  /**
   * Translates the specified Coordinate from WGS84 coordinates to Mercator
   * coordinates. The Mercator coordinates keep longitude for the X ordinate,
   * but change longitude to match the Y positions on the Web Mercator map
   * tiles.
   *
   * @param c The latitude, longitude coordinate to translate into Mercator
   * coordinates.
   * @return The resulting Mercator coordinate.
   */
  public static Coord toMerc(@NonNull Coord c) {
    Coord copy = c.copy();
    toMercator(c, copy);
    return copy;
  }

  /**
   * Translates the specified Coordinate from WGS84 coordinates to Mercator
   * coordinates. The Mercator coordinates keep longitude for the X ordinate,
   * but change longitude to match the Y positions on the Web Mercator map
   * tiles.
   *
   * @param c The latitude, longitude coordinate to translate into Mercator
   * coordinates. Since this is a CoordZ, we guarantee that the output is a
   * CoordZ. If the copy method returns a CoordZ, we will use it. Otherwise we
   * will create a new CoordZ.
   * @return The resulting Mercator coordinate.
   */
  public static CoordZ toMerc(@NonNull CoordZ c) {
    CoordZ cz = CoordFactory.copy(c);
    toMercator(c, cz);
    return cz;
  }

  /**
   * Translates the specified Coordinate from WGS84 coordinates to Mercator
   * coordinates. The Mercator coordinates keep longitude for the X ordinate,
   * but change longitude to match the Y positions on the Web Mercator map
   * tiles.
   *
   * @param c The latitude, longitude coordinate to translate into Mercator
   * coordinates.
   * @return The resulting Mercator coordinate.
   */
  public static CoordM toMerc(@NonNull CoordM c) {
    CoordM cm = CoordFactory.copy(c);
    toMercator(c, cm);
    return cm;
  }

  /**
   * Translates the specified Coordinate from WGS84 coordinates to Mercator
   * coordinates. The Mercator coordinates keep longitude for the X ordinate,
   * but change longitude to match the Y positions on the Web Mercator map
   * tiles.
   *
   * @param c The latitude, longitude coordinate to translate into Mercator
   * coordinates.
   * @return The resulting Mercator coordinate.
   */
  public static CoordXY toMerc(@NonNull CoordXY c) {
    CoordXY cxy = CoordFactory.copy(c);
    toMercator(c, cxy);
    return cxy;
  }

  /**
   * Translates the specified Coordinate from WGS84 coordinates to Mercator
   * coordinates. The Mercator coordinates keep longitude for the X ordinate,
   * but change longitude to match the Y positions on the Web Mercator map
   * tiles.
   *
   * @param inCoord The latitude, longitude coordinate to translate into
   * Mercator
   * @param outCoord The latitude, longitude coordinate to translate into
   * Mercator coordinates.
   */
  public static void toMercator(@NonNull Coord inCoord, @NonNull Coord outCoord) {
    if (inCoord == null) {
      throw new IllegalArgumentException("Parameter inCoord cannot be null.");
    }
    if (outCoord == null) {
      throw new IllegalArgumentException("Parameter outCoord cannot be null.");
    }
    double y = latToMerc(inCoord.getY());
    if (!Double.isNaN(y)) {
      outCoord.setY(y);
    } else {
      throw new IllegalArgumentException("The projection returned NaN, which "
          + "may mean the input coordinate was not WGS84.");
    }

  }

  //</editor-fold>
}
