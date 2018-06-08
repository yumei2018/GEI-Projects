/*
 * The MIT License
 *
 * Copyright 2014 hdunsford.
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
package gov.ca.water.shapelite.projection;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.coordinate.CoordFactory;
import gov.ca.water.shapelite.coordinate.CoordM;
import gov.ca.water.shapelite.coordinate.CoordXY;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class Reproject {



  /**
   * Hide constructor.
   */
  private Reproject() {

  }

  /**
   * Epsilon.
   */
  private static final double EPS = 1E-12;

  /**
   * The tolerance is to allow GRS80 and WGS84 to escape without being
   * transformed at all.
   */
  private static final double TOLERANCE = 0.000000000050;

  /**
   * This was used in the adjust longitude.
   */
  private static final double FUDGE_FACTOR = Math.PI / 72;

  /**
   * X' = AFFINE_X_OFFSET + AFFINE_X_DX * Column + AFFINE_X_DY * Row.
   */
  private static final int AFFINE_X_OFFSET = 0;
  /**
   * X' = AFFINE_X_OFFSET + AFFINE_X_DX * Column + AFFINE_X_DY * Row.
   */
  private static final int AFFINE_X_DX = 1;
  /**
   * X' = AFFINE_X_OFFSET + AFFINE_X_DX * Column + AFFINE_X_DY * Row.
   */
  private static final int AFFINE_X_DY = 2;
  /**
   * Y' = AFFINE_Y_OFFSET + AFFINE_Y_DX * Column + AFFINE_Y_DY * Row.
   */
  private static final int AFFINE_Y_OFFSET = 3;
  /**
   * Y' = AFFINE_Y_OFFSET + AFFINE_Y_DX * Column + AFFINE_Y_DY * Row.
   */
  private static final int AFFINE_Y_DX = 4;
  /**
   * Y' = AFFINE_Y_OFFSET + AFFINE_Y_DX * Column + AFFINE_Y_DY * Row.
   */
  private static final int AFFINE_Y_DY = 5;
  /**
   * The number of values in an affine array.
   */
  private static final int AFFINE_SIZE = 6;

  /**
   * X of top left point.
   */
  private static final int TOP_LEFT_X = 0;
  /**
   * Y of top left point.
   */
  private static final int TOP_LEFT_Y = 1;
  /**
   * X of top right point.
   */
  private static final int TOP_RIGHT_X = 2;
  /**
   * Y of top right point.
   */
  private static final int TOP_RIGHT_Y = 3;
  /**
   * X of bottom left point.
   */
  private static final int BOTTOM_LEFT_X = 4;
  /**
   * Y of bottom left point.
   */
  private static final int BOTTOM_LEFT_Y = 5;
  /**
   * Number of X, Y terms in the list of three coordinates.
   */
  private static final int VERTEX_SIZE = 6;
  /**
   * Total number of vertices used for affine reprojection.
   */
  private static final int VERTEX_COUNT = 3;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  //</editor-fold>
  /**
   * This method reprojects the affine transform coefficients. This is used for
   * projection on the fly, where image transforms can take advantage of an
   * affine projection, but does not have the power of a full projective
   * transform and gets less and less accurate as the image covers larger and
   * larger areas. Since most image layers represent small rectangular areas,
   * this should not be a problem in most cases. the affine array should be
   * ordered as follows: X' = [0] + [1] * Column + [2] * Row Y' = [3] + [4] *
   * Column + [5] * Row
   *
   * @param affine The array of double affine coefficients.
   * @param numRows The number of rows to use for the lower bounds. Value of 0
   * or less will be set to 1.
   * @param numCols The number of columns to use to get the right bounds. Values
   * of 0 or less will be set to 1.
   * @param source The ProjectionInfo describing the source projection.
   * @param dest The ProjectionInfo describing the destination projection.
   * @return The transformed coefficients.
   * @throws ProjectionException If there is an error performing projection.
   */
  public static double[] reprojectAffine(double[] affine, double numRows,
      double numCols, ProjectionInfo source, ProjectionInfo dest)
      throws ProjectionException {
    if (numRows <= 0) {
      numRows = 1;
    }
    if (numCols <= 0) {
      numCols = 1;
    }

    double[] vertices = new double[VERTEX_SIZE];
    // Top left
    vertices[TOP_LEFT_X] = affine[AFFINE_X_OFFSET];
    vertices[TOP_LEFT_Y] = affine[AFFINE_Y_OFFSET];
    // Top right
    vertices[TOP_RIGHT_X] = affine[AFFINE_X_OFFSET]
        + affine[AFFINE_X_DX] * numCols;
    vertices[TOP_RIGHT_Y] = affine[AFFINE_Y_OFFSET]
        + affine[AFFINE_Y_DX] * numCols;
    // Bottom Left
    vertices[BOTTOM_LEFT_X] = affine[AFFINE_Y_OFFSET]
        + affine[AFFINE_X_DY] * numRows;
    vertices[BOTTOM_LEFT_Y] = affine[AFFINE_Y_OFFSET]
        + affine[AFFINE_Y_DY] * numRows;
    double[] z = new double[VERTEX_COUNT];
    reprojectPoints(vertices, z, source, dest, 0, VERTEX_COUNT);
    double[] affineResult = new double[AFFINE_SIZE];

    affineResult[AFFINE_X_OFFSET] = vertices[TOP_LEFT_X];
    affineResult[AFFINE_X_DX] = (vertices[TOP_RIGHT_X]
        - vertices[TOP_LEFT_X]) / numCols;
    affineResult[AFFINE_X_DY] = (vertices[BOTTOM_LEFT_X]
        - vertices[TOP_LEFT_X]) / numRows;

    affineResult[AFFINE_Y_OFFSET] = vertices[TOP_LEFT_Y];
    affineResult[AFFINE_Y_DX] = (vertices[TOP_RIGHT_Y]
        - vertices[TOP_LEFT_Y]) / numCols;
    affineResult[AFFINE_Y_DY] = (vertices[BOTTOM_LEFT_Y]
        - vertices[TOP_LEFT_Y]) / numRows;

    return affineResult;
  }

  /**
   * Reprojects the original shape from the source coordinate system to the dest
   * coordinate system. The original shape is not modified.
   *
   * @param original The Shape to be reprojected.
   * @param source The ProjectionInfo describing the source projection.
   * @param dest The ProjectionInfo describing the destination projection.
   * @return The reprojected shape.
   * @throws ProjectionException if there is an error.
   */
  public static Shape reprojectShape(Shape original, ProjectionInfo source,
      ProjectionInfo dest) throws ProjectionException {
    Shape result = new Shape(original.getShapeType());
    int numPoints = original.getCoordinates().size();
    double[] xy = new double[numPoints * 2];
    double[] z = new double[numPoints];
    for (int i = 0; i < numPoints; i++) {
      Coord c = original.getCoordinates().get(i);
      xy[i * 2] = c.getX();
      xy[i * 2 + 1] = c.getY();
      if (c.hasZ()) {
        z[i] = c.get(Coord.Index.Z);
      }
    }
    reprojectPoints(xy, z, source, dest, 0, numPoints);
    int i = 0;
    for (Part p : original.getParts()) {
      Part resultPart = new Part();
      for (Coord c : p.getCoordinates()) {

        Coord resultCoord;
        if (c.hasZ()) {
          CoordZ coordZ = new CoordZ();
          if (!Double.isNaN(z[i])) {
            coordZ.setZ(z[i]);
          }
          coordZ.setM(c.get(Coord.Index.M));
          resultCoord = coordZ;
        } else if (c.hasM()) {
          CoordM coordM = new CoordM();
          coordM.setM(c.get(Coord.Index.M));
          resultCoord = coordM;
        } else {
          resultCoord = new CoordXY();
        }
        double x = xy[i * 2];
        if (!Double.isNaN(x)) {
          resultCoord.setX(x);
        }
        double y = xy[i * 2 + 1];
        if (!Double.isNaN(y)) {
          resultCoord.setY(y);
        }
        resultPart.getCoordinates().add(resultCoord);
        i++;
      }
      resultPart.setClosed(p.isClosed());
      result.getParts().add(resultPart);
    }
    result.calculateBounds();
    return result;
  }

  /**
   * Reprojects the list of coordinates from the source coordinate system to the
   * destination coordinate system.
   *
   * @param envelope The Envelope to reproject.
   * @param source The ProjectionInfo describing the source projection.
   * @param dest The ProjectionInfo describing the destination projection.
   * @return The Envelope reprojected to the destination projection.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If there was
   * an error during projection.
   */
  public static Envelope reprojectEnvelope(Envelope envelope,
      ProjectionInfo source, ProjectionInfo dest)
      throws ProjectionException {
    Coord min = envelope.getMin();
    Coord max = envelope.getMax();
    min = reprojectCoordinate(min, source, dest);
    max = reprojectCoordinate(max, source, dest);
    return new DefaultEnvelope(min, max);
  }

  /**
   * Reprojects the list of coordinates from the source coordinate system to the
   * destination coordinate system.
   *
   * @param coords The list of coordinates to reproject.
   * @param source The ProjectionInfo describing the source projection.
   * @param dest The ProjectionInfo describing the destination projection.
   * @return a List of coordinates that have been reprojected.
   * @throws gov.ca.water.shapelite.projection.ProjectionException When there is
   * an error with the projection mathematics.
   */
  public static List<Coord> reprojectCoordinates(List<Coord> coords,
      ProjectionInfo source, ProjectionInfo dest)
      throws ProjectionException {
    List<Coord> result = new ArrayList<>();
    double[] xy = new double[coords.size() * 2];
    double[] z = new double[coords.size()];
    for (int i = 0; i < coords.size(); i++) {
      xy[i * 2] = coords.get(i).getX();
      xy[i * 2 + 1] = coords.get(i).getY();
      z[i] = coords.get(i).get(Coord.Index.Z);
    }
    reprojectPoints(xy, z, source, dest, 0, coords.size());
    for (int i = 0; i < coords.size(); i++) {
      Coord c = new CoordZ(xy[i * 2], xy[i * 2 + 1], z[i]);
      result.add(c);
    }
    return result;
  }

  /**
   * Reprojects the single coordinate from the source coordinate system to the
   * destination coordinate system.
   *
   * @param coord The coordinate to reproject.
   * @param source The ProjectionInfo describing the source projection.
   * @param dest The ProjectionInfo describing the destination projection.
   * @return the reprojected Coord object.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If there was
   * an error during projection.
   */
  public static Coord reprojectCoordinate(Coord coord, ProjectionInfo source,
      ProjectionInfo dest) throws ProjectionException {
    Coord result = coord.copy();
    reprojectCoordinate(coord, source, result, dest);
    return result;
  }

  /**
   * Reprojects the single coordinate from the source coordinate system to the
   * destination coordinate system.
   *
   * @param coord The coordinate to reproject.
   * @param source The ProjectionInfo describing the source projection.
   * @param dest The ProjectionInfo describing the destination projection.
   * @return the reprojected Coord object.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If there was
   * an error during projection.
   */
  public static CoordXY reprojectCoordinate(CoordXY coord, ProjectionInfo source,
      ProjectionInfo dest) throws ProjectionException {
    CoordXY result = CoordFactory.copy(coord);
    reprojectCoordinate(coord, source, result, dest);
    return result;
  }

  /**
   * Reprojects the single coordinate from the source coordinate system to the
   * destination coordinate system.
   *
   * @param coord The coordinate to reproject.
   * @param source The ProjectionInfo describing the source projection.
   * @param dest The ProjectionInfo describing the destination projection.
   * @return the reprojected Coord object.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If there was
   * an error during projection.
   */
  public static CoordM reprojectCoordinate(CoordM coord, ProjectionInfo source,
      ProjectionInfo dest) throws ProjectionException {
    CoordM result = CoordFactory.copy(coord);
    reprojectCoordinate(coord, source, result, dest);
    return result;
  }

  /**
   * Reprojects the single coordinate from the source coordinate system to the
   * destination coordinate system.
   *
   * @param coord The coordinate to reproject.
   * @param source The ProjectionInfo describing the source projection.
   * @param dest The ProjectionInfo describing the destination projection.
   * @return the reprojected Coord object.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If there was
   * an error during projection.
   */
  public static CoordZ reprojectCoordinate(CoordZ coord, ProjectionInfo source,
      ProjectionInfo dest) throws ProjectionException {
    CoordZ result = CoordFactory.copy(coord);
    reprojectCoordinate(coord, source, result, dest);
    return result;
  }

  /**
   * Reprojects the single coordinate from the source coordinate system to the
   * destination coordinate system.
   *
   * @param original The coordinate to reproject.
   * @param source The ProjectionInfo describing the source projection.
   * @param result The destination coordinate to store the results.
   * @param dest The ProjectionInfo describing the destination projection.
   *
   * @throws gov.ca.water.shapelite.projection.ProjectionException If there was
   * an error during projection.
   */
  public static void reprojectCoordinate(Coord original, ProjectionInfo source,
      Coord result, ProjectionInfo dest) throws ProjectionException {
    double[] xy = new double[]{original.getX(), original.getY()};
    double[] z = new double[1];
    if (original.hasZ()) {
      z[0] = original.get(Coord.Index.Z);
    }
    reprojectPoints(xy, z, source, dest, 0, 1);
    if (!Double.isNaN(xy[0])) {
      result.setX(xy[0]);
    }
    if (!Double.isNaN(xy[1])) {
      result.setY(xy[1]);
    }
    if (result.hasZ()) {
      result.set(Coord.Index.Z, z[0]);
    }
  }

  /**
   *
   * @param xy The xy array should be in interleaved set of xy coordinates like
   * [x1, y1, x2, y2, ... xn, yn]
   * @param z The z array is the array of all the z values
   * @param source The ProjectionInfo describing the source projection.
   * @param dest The ProjectionInfo describing the destination projection.
   * @param startIndex The integer start index in coordinate pairs.
   * @param numPoints The integer count of coordinate pairs.
   * @throws ProjectionException If there was an error during reprojection.
   */
  public static void reprojectPoints(double[] xy, double[] z,
      ProjectionInfo source, ProjectionInfo dest, int startIndex,
      int numPoints) throws ProjectionException {
    reprojectPoints(xy, z, source, 1.0, dest, 1.0, null, startIndex,
        numPoints);
  }

  /**
   *
   * @param xy The xy array should be in interleaved set of xy coordinates like
   * [x1, y1, x2, y2, ... xn, yn]
   * @param z The z array is the array of all the z values
   * @param source The ProjectionInfo describing the source projection.
   * @param srcZtoMeter The conversion factor for converting z values to meters.
   * @param dest The ProjectionInfo describing the destination projection.
   * @param dstZtoMeter The conversion factor for converting destination z
   * values to meters.
   * @param datumTransform The datum transform.
   * @param startIndex The integer start index.
   * @param numPoints The integer number of points.
   * @throws ProjectionException If there was an error reprojecting.
   */
  public static void reprojectPoints(double[] xy, double[] z,
      ProjectionInfo source, double srcZtoMeter, ProjectionInfo dest,
      double dstZtoMeter, DatumTransformable datumTransform,
      int startIndex, int numPoints) throws ProjectionException {
    double toMeter = source.getUnit().getMeters();

    // Geocentric coordinates are centered at the core of the earth.
    // Z is up toward the north pole.
    // The X axis goes from the center of the earth through Greenwich.
    // The Y axis passes through 90E.
    // This section converts from geocentric coordinates to geodetic
    // ones if necessary.
    if (source.isGeocentric()) {
      if (z == null) {
        throw new ProjectionException(45);
      }
      for (int i = startIndex; i < numPoints; i++) {
        if (toMeter != 1) {
          xy[i * 2] *= toMeter;
          xy[i * 2 + 1] *= toMeter;
        }
      }
      GeocentricGeodetic g = new GeocentricGeodetic(
          source.getGeographicInfo().getDatum().getSpheroid());
      g.GeocentricToGeodetic(xy, z, startIndex, numPoints);
    }

    // Transform source points to lam/phi if they are not already
    convertToLatLon(source, xy, z, srcZtoMeter, startIndex, numPoints);

    double fromGreenwich
        = source.getGeographicInfo().getMeridian().getLongitude()
        * source.getGeographicInfo().getUnit().getRadians();
    if (fromGreenwich != 0) {
      for (int i = startIndex; i < numPoints; i++) {
        if (xy[2 * i] != Double.MAX_VALUE) {
          xy[2 * i] += fromGreenwich;
        }
      }
    }
    // DATUM TRANSFORM IF NEEDED
    if (datumTransform == null) {
      if (!source.getGeographicInfo().getDatum().Matches(
          dest.getGeographicInfo().getDatum())) {
        datumTransform(source, dest, xy, z, startIndex, numPoints);
      }
    } else {
      datumTransform.Transform(source, dest, xy, z,
          startIndex, numPoints);
    }

    // Adjust to new prime meridian if there is one in the destination cs
    fromGreenwich = dest.getGeographicInfo().getMeridian().getLongitude()
        * dest.getGeographicInfo().getUnit().getRadians();
    if (fromGreenwich != 0) {
      for (int i = startIndex; i < numPoints; i++) {
        if (xy[i * 2] != Double.MAX_VALUE) {
          xy[i * 2] -= fromGreenwich;
        }
      }
    }

    if (dest.isGeocentric()) {
      if (z == null) {
        throw new ProjectionException(45);
      }
      GeocentricGeodetic g = new GeocentricGeodetic(
          dest.getGeographicInfo().getDatum().getSpheroid());
      g.GeodeticToGeocentric(xy, z, startIndex, numPoints);
      double frmMeter = 1 / dest.getUnit().getMeters();
      if (frmMeter != 1) {
        for (int i = startIndex; i < numPoints; i++) {
          if (xy[i * 2] != Double.MAX_VALUE) {
            xy[i * 2] *= frmMeter;
            xy[i * 2 + 1] *= frmMeter;
          }
        }
      }
    } else {
      convertToProjected(dest, xy, z, dstZtoMeter, startIndex, numPoints);
    }
  }

  /**
   * Converts the coordinates from geographic to projected coordinates.
   *
   * @param dest The destination projection info.
   * @param xy The xy array should be in interleaved set of xy coordinates like
   * [x1, y1, x2, y2, ... xn, yn]
   * @param z The array of z values.
   * @param dstZtoMeter the conversion factor to obtain z to meter.
   * @param startIndex the integer start index as a coordinate index.
   * @param numPoints the total number of coordinates.
   */
  private static void convertToProjected(ProjectionInfo dest, double[] xy,
      double[] z, double dstZtoMeter, int startIndex, int numPoints) {
    double frmMeter = 1 / dest.getUnit().getMeters();
    double frmZMeter = 1 / dstZtoMeter;
    boolean geoc = dest.isGeoc();
    double lam0 = dest.getLam0();
    double roneEs = 1 / (1 - dest.getGeographicInfo().getDatum().
        getSpheroid().getEccentricitySquared());
    boolean over = dest.isOver();
    double x0 = 0;
    double y0 = 0;
    if (dest.getFalseEasting() != null) {
      x0 = dest.getFalseEasting();
    }
    if (dest.getFalseNorthing() != null) {
      y0 = dest.getFalseNorthing();
    }
    double a = dest.getGeographicInfo().getDatum().getSpheroid().
        getEquatorialRadius();
    for (int i = startIndex; i < numPoints; i++) {
      double lam = xy[2 * i];
      double phi = xy[2 * i + 1];
      double t = Math.abs(phi) - Math.PI / 2;
      if (t > EPS || Math.abs(lam) > 10) {
        xy[2 * i] = Double.MAX_VALUE;
        xy[2 * i + 1] = Double.MAX_VALUE;
        continue;
      }
      if (Math.abs(t) <= EPS) {
        if (phi < 0) {
          xy[2 * i + 1] = -Math.PI / 2;
        } else {
          xy[2 * i + 1] = Math.PI / 2;
        }
      } else if (geoc) {
        xy[2 * i + 1] = Math.atan(roneEs * Math.tan(phi));
      }
      xy[2 * i] -= lam0;
      if (!over) {
        xy[2 * i] = adjlon(xy[2 * i]);
      }
    }

    // break this out because we don't want a chatty call to extension
    // transforms
    dest.getTransform().forward(xy, startIndex, numPoints);

    if (dstZtoMeter == 1.0) {
      for (int i = startIndex; i < numPoints; i++) {
        xy[2 * i] = frmMeter * (a * xy[2 * i] + x0);
        xy[2 * i + 1] = frmMeter * (a * xy[2 * i + 1] + y0);
      }
    } else {
      for (int i = startIndex; i < numPoints; i++) {
        xy[2 * i] = frmMeter * (a * xy[2 * i] + x0);
        xy[2 * i + 1] = frmMeter * (a * xy[2 * i + 1] + y0);
        z[i] *= frmZMeter;
      }
    }
  }

  /**
   * Applies the datum transform, adjusting from other datums to WGS_1984.
   *
   * @param source The ProjectionInfo describing the source projection.
   * @param dest The ProjectionInfo describing the destination projection.
   * @param xy The xy array should be in interleaved set of xy coordinates like
   * [x1, y1, x2, y2, ... xn, yn]
   * @param z The array of z values.
   * @param startIndex The integer start index.
   * @param numPoints The integer number of points.
   * @throws ProjectionException An exception that can occur if there is an
   * invalid datum configuration, or unsupported operation.
   */
  private static void datumTransform(ProjectionInfo source,
      ProjectionInfo dest, double[] xy, double[] z, int startIndex,
      int numPoints) throws ProjectionException {
    Spheroid wgs84 = new Spheroid(Proj4Ellipsoid.WGS_1984);
    Datum sDatum = source.getGeographicInfo().getDatum();
    Datum dDatum = dest.getGeographicInfo().getDatum();

    /* ----------------------------------------------------------------- */
 /*      We cannot do any meaningful datum transformation if either   */
 /*      the source or destination are of an unknown datum type       */
 /*      (ie. only a +ellps declaration, no +datum).  This is new     */
 /*      behavior for PROJ 4.6.0.                                     */
 /* ----------------------------------------------------------------- */
    if (sDatum.getDatumType() == DatumType.Unknown
        || dDatum.getDatumType() == DatumType.Unknown) {
      return;
    }

    /* ----------------------------------------------------------------- */
 /*      Short cut if the datums are identical.                       */
 /* ----------------------------------------------------------------- */
    if (sDatum.Matches(dDatum)) {
      return;
    }

    // proj4 actually allows some tollerance here
    if (sDatum.getDatumType() == dDatum.getDatumType()) {
      if (sDatum.getSpheroid().getEquatorialRadius().
          equals(dDatum.getSpheroid().getEquatorialRadius())) {
        if (Math.abs(sDatum.getSpheroid().getEccentricitySquared()
            - dDatum.getSpheroid().getEccentricitySquared())
            < TOLERANCE) {
          //
          return;
        }
      }
    }

    double srcA = sDatum.getSpheroid().getEquatorialRadius();
    double srcEs = sDatum.getSpheroid().getEccentricitySquared();

    double dstA = dDatum.getSpheroid().getEquatorialRadius();
    double dstEs = dDatum.getSpheroid().getEccentricitySquared();

    /* ----------------------------------------------------------------- */
 /*      Create a temporary Z value if one is not provided.           */
 /* ----------------------------------------------------------------- */
    if (z == null) {
      z = new double[xy.length / 2];
    }

    /* ----------------------------------------------------------------- */
 /*      If this datum requires grid shifts, then apply it to geodetic*/
 /*      coordinates.                                                 */
 /* ------------------------------------------------------------------*/
    if (sDatum.getDatumType() == DatumType.GridShift) {
      GridShift.apply(source.getGeographicInfo().getDatum().getNadGrids(),
          false, xy, startIndex, numPoints);

      srcA = wgs84.getEquatorialRadius();
      srcEs = wgs84.getEccentricitySquared();
    }

    if (dDatum.getDatumType() == DatumType.GridShift) {
      dstA = wgs84.getEquatorialRadius();
      dstEs = wgs84.getEccentricitySquared();
    }

    /* ================================================================ */
 /*      Do we need to go through geocentric coordinates?            */
 /* ================================================================ */
    if (srcEs != dstEs || srcA != dstA
        || sDatum.getDatumType() == DatumType.Param3
        || sDatum.getDatumType() == DatumType.Param7
        || dDatum.getDatumType() == DatumType.Param3
        || dDatum.getDatumType() == DatumType.Param7) {
      /* ------------------------------------------------------------ */
 /*      Convert to geocentric coordinates.                      */
 /* ------------------------------------------------------------ */

      GeocentricGeodetic gc
          = new GeocentricGeodetic(sDatum.getSpheroid());
      gc.GeodeticToGeocentric(xy, z, startIndex, numPoints);

      /* ------------------------------------------------------------- */
 /*      Convert between datums.                                  */
 /* ------------------------------------------------------------- */
      if (sDatum.getDatumType() == DatumType.Param3
          || sDatum.getDatumType() == DatumType.Param7) {
        pjGeocentricToWgs84(source, xy, z, startIndex, numPoints);
      }

      if (dDatum.getDatumType() == DatumType.Param3
          || dDatum.getDatumType() == DatumType.Param7) {
        pjGeocentricFromWgs84(dest, xy, z, startIndex, numPoints);
      }

      /* ------------------------------------------------------------ */
 /*      Convert back to geodetic coordinates.                   */
 /* ------------------------------------------------------------ */
      gc = new GeocentricGeodetic(dDatum.getSpheroid());
      gc.GeocentricToGeodetic(xy, z, startIndex, numPoints);
    }

    /* --------------------------------------------------------------- */
 /*      apply grid shift to destination if required.               */
 /* --------------------------------------------------------------- */
    if (dDatum.getDatumType() == DatumType.GridShift) {
      GridShift.apply(dest.getGeographicInfo().getDatum().getNadGrids(),
          true, xy, startIndex, numPoints);
    }
  }

  /**
   *
   * @param source The ProjectionInfo describing the source projection.
   * @param xy The xy array should be in interleaved set of xy coordinates like
   * [x1, y1, x2, y2, ... xn, yn]
   * @param z The double z value to convert.
   * @param srcZtoMeter The factor to convert z to meters.
   * @param startIndex the Integer start index.
   * @param numPoints The integer number of points.
   */
  private static void convertToLatLon(ProjectionInfo source, double[] xy,
      double[] z, double srcZtoMeter, int startIndex, int numPoints) {
    double toMeter = 1.0;
    if (source.getUnit() != null) {
      toMeter = source.getUnit().getMeters();
    }
    double oneEs = 1 - source.getGeographicInfo().getDatum().
        getSpheroid().getEccentricitySquared();
    Double eRad = source.getGeographicInfo().getDatum().
        getSpheroid().getEquatorialRadius();
    if (eRad == null || eRad == 0) {
      boolean stop = true;
    }
    double ra = 1 / eRad;
    double x0 = 0;
    if (source.getFalseEasting() != null) {
      x0 = source.getFalseEasting();
    }
    double y0 = 0;
    if (source.getFalseNorthing() != null) {
      y0 = source.getFalseNorthing();
    }
    if (srcZtoMeter == 1.0) {
      for (int i = startIndex; i < numPoints; i++) {
        if (xy[i * 2] == Double.MAX_VALUE
            || xy[i * 2 + 1] == Double.MAX_VALUE) {
          continue;
        }
        // descale and de-offset
        xy[i * 2] = (xy[i * 2] * toMeter - x0) * ra;
        xy[i * 2 + 1] = (xy[i * 2 + 1] * toMeter - y0) * ra;
      }
    } else {
      for (int i = startIndex; i < numPoints; i++) {
        if (xy[i * 2] == Double.MAX_VALUE
            || xy[i * 2 + 1] == Double.MAX_VALUE) {
          continue;
        }
        // descale and de-offset
        xy[i * 2] = (xy[i * 2] * toMeter - x0) * ra;
        xy[i * 2 + 1] = (xy[i * 2 + 1] * toMeter - y0) * ra;
        z[i] *= srcZtoMeter;
      }
    }

    if (source.getTransform() == null) {
      boolean stop = true;
    }

    if (source.getTransform() != null) {
      source.getTransform().inverse(xy, startIndex, numPoints);
    }

    for (int i = startIndex; i < numPoints; i++) {
      double lam0 = source.getLam0();
      xy[i * 2] += lam0;
      xy[i * 2] = adjlon(xy[i * 2]);
      if (source.isGeoc()
          && Math.abs(Math.abs(xy[i * 2 + 1]) - Math.PI / 2) > EPS) {
        xy[i * 2 + 1] = Math.atan(oneEs * Math.tan(xy[i * 2 + 1]));
      }
    }
  }

  /**
   * Adjusts the longitude to ensure that it wraps between - 2 PI and 2 PI.
   *
   * @param lon The mathematical longitude in radians.
   * @return The adjusted longitude.
   */
  private static double adjlon(double lon) {
    if (Math.abs(lon) <= Math.PI + FUDGE_FACTOR) {
      return (lon);
    }

    /* adjust to 0..2pi rad */
    lon += Math.PI;

    /* remove integral # of 'revolutions'*/
    lon -= 2 * Math.PI * Math.floor(lon / (2 * Math.PI));

    /* adjust back to -pi..pi rad */
    lon -= Math.PI;

    return (lon);
  }

  /**
   * Converts Geocentric values to WGS_84 representations.
   *
   * @param source The ProjectionInfo describing the source projection.
   * @param xy The xy array should be in interleaved set of xy coordinates like
   * [x1, y1, x2, y2, ... xn, yn]
   * @param zArr The array of z values.
   * @param startIndex the integer start index (counting coordinates, not double
   * values).
   * @param numPoints the integer number of points.
   */
  private static void pjGeocentricToWgs84(ProjectionInfo source, double[] xy,
      double[] zArr, int startIndex, int numPoints) {
    double[] shift = source.getGeographicInfo().getDatum().getToWGS84();

    if (source.getGeographicInfo().getDatum().getDatumType()
        == DatumType.Param3) {
      for (int i = startIndex; i < numPoints; i++) {

        if (Double.isNaN(xy[2 * i]) || Double.isNaN(xy[2 * i + 1])) {
          continue;
        }
        xy[2 * i] = xy[2 * i] + shift[0]; // dx
        xy[2 * i + 1] = xy[2 * i + 1] + shift[1]; // dy
        zArr[i] = zArr[i] + shift[2];
      }
    } else {
      for (int i = startIndex; i < numPoints; i++) {
        if (Double.isNaN(xy[2 * i]) || Double.isNaN(xy[2 * i + 1])) {
          continue;
        }

        double x = xy[2 * i];
        double y = xy[2 * i + 1];
        double z = zArr[i];
        xy[2 * i] = shift[6] * (x - shift[5] * y
            + shift[4] * z) + shift[0];
        xy[2 * i + 1] = shift[6] * (shift[5] * x + y
            - shift[3] * z) + shift[1];
        zArr[i] = shift[6] * (-shift[4] * x
            + shift[3] * y + z) + shift[2];
      }
    }
  }

  /**
   * Converts values to geocentric coordinates from WGS_1984 coordinates.
   *
   * @param dest The ProjectionInfo describing the destination projection.
   * @param xy The xy array should be in interleaved set of xy coordinates like
   * [x1, y1, x2, y2, ... xn, yn]
   * @param zArr The array of z coordinates.
   * @param startIndex The integer start index in points.
   * @param numPoints The count of points.
   */
  private static void pjGeocentricFromWgs84(ProjectionInfo dest, double[] xy,
      double[] zArr, int startIndex, int numPoints) {
    double[] shift = dest.getGeographicInfo().getDatum().getToWGS84();

    if (dest.getGeographicInfo().getDatum().getDatumType()
        == DatumType.Param3) {
      for (int i = startIndex; i < numPoints; i++) {
        if (Double.isNaN(xy[2 * i]) || Double.isNaN(xy[2 * i + 1])) {
          continue;
        }
        xy[2 * i] = xy[2 * i] - shift[0]; // dx
        xy[2 * i + 1] = xy[2 * i + 1] - shift[1]; // dy
        zArr[i] = zArr[i] - shift[2];
      }
    } else {
      for (int i = startIndex; i < numPoints; i++) {
        if (Double.isNaN(xy[2 * i]) || Double.isNaN(xy[2 * i + 1])) {
          continue;
        }

        double x = (xy[2 * i] - shift[0]) / shift[6];
        double y = (xy[2 * i + 1] - shift[1]) / shift[6];
        double z = (zArr[i] - shift[2]) / shift[6];
        xy[2 * i] = x + shift[5] * y - shift[4] * z;
        xy[2 * i + 1] = -shift[5] * x + y + shift[3] * z;
        zArr[i] = shift[4] * x - shift[3] * y + z;
      }
    }
  }

  //</editor-fold>
}
