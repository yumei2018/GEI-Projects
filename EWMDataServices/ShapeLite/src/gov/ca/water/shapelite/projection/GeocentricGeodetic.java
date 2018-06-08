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

/*
 * Reference...
 * ============
 * Wenzel, H.-G.(1985): Hochauflösende Kugelfunktionsmodelle für
 * das Gravitationspotential der Erde. Wiss. Arb. Univ. Hannover
 * Nr. 137, p. 130-131.

 * Programmed by GGA- Leibniz-Institue of Applied Geophysics
 *               Stilleweg 2
 *               D-30655 Hannover
 *               Federal Republic of Germany
 *               Internet: www.gga-hannover.de
 *
 *               Hannover, March 1999, April 2004.
 *               see also: comments in statements
 * remarks:
 * Mathematically exact and because of symmetry of rotation-ellipsoid,
 * each point (X, Y, Z) has at least two solutions (Latitude1, Longitude1, Height1) and
 * (Latitude2, Longitude2, Height2). Is point=(0., 0., Z) (P=0.), so you get even
 * four solutions, 	every two symmetrical to the semi-minor axis.
 * Here Height1 and Height2 have at least a difference in order of
 * radius of curvature (e.g. (0, 0, b)=> (90., 0., 0.) or (-90., 0., -2b);
 * (a+100.)*(sqrt(2.)/2., sqrt(2.)/2., 0.) => (0., 45., 100.) or
 * (0., 225., -(2a+100.))).
 * The algorithm always computes (Latitude, Longitude) with smallest |Height|.
 * For normal computations, that means |Height|<10000.m, algorithm normally
 * converges after to 2-3 steps!!!
 * But if |Height| has the amount of length of ellipsoid's axis
 * (e.g. -6300000.m), 	algorithm needs about 15 steps.
 */
package gov.ca.water.shapelite.projection;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordZ;

/**
 * Wenzel, H.-G.(1985): Hochauflösende Kugelfunktionsmodelle für das
 * Gravitationspotential der Erde. Wiss. Arb. Univ. Hannover Nr. 137, p.
 * 130-131.
 */
public class GeocentricGeodetic {

    //<editor-fold defaultstate="collapsed" desc="Fields">
     /* local defintions and variables */
  /* end-criterium of loop, accuracy of sin(Latitude) */
  private static final double GENAU = 1E-12;
  private static final double GENAU2 = GENAU * GENAU;
  private static final int MAXITER = 30;
        // private const double COS_67P5 = 0.38268343236508977;  /* cosine of 67.5 degrees */
  // private const double AD_C = 1.0026000;            /* Toms region 1 constant */
  private static final double PI = 3.14159265358979323e0;
  private static final double PI_OVER_2 = (PI / 2.0e0);
  private final double _a;
  private final double _a2;
  private final double _b;
  private final double _b2;
  private final double _e2;

    //</editor-fold>
  /// <summary>
  /// Creates a new instance of GeocentricGeodetic
  /// </summary>
  public GeocentricGeodetic(Spheroid gi) {
    _a = gi.getEquatorialRadius();
    _b = gi.getPolarRadius();
    _a2 = _a * _a;
    _b2 = _b * _b;
    _e2 = (_a2 - _b2) / _a2;
    //_ep2 = (_a2 - _b2)/_b2;
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Converts lon, lat, height to x, y, z where lon and lat are in radians and
   * everything else is meters
   *
   * @param xy
   * @param z
   * @param startIndex
   * @param numPoints
   */
  public void GeodeticToGeocentric(double[] xy, double[] z, int startIndex, int numPoints) {
    for (int i = startIndex; i < numPoints; i++) {
      CoordZ c = new CoordZ();
      double x = xy[i * 2];
      double y = xy[i * 2 + 1];
      double zz = z[i];
      if (!Double.isNaN(x)) {
        c.setX(x);
      }
      if (!Double.isNaN(y)) {
        c.setY(y);
      }
      if (!Double.isNaN(zz)) {
        c.setZ(zz);
      }
      GeodeticToGeocentric(c);
      xy[i * 2] = c.getX();
      xy[i * 2 + 1] = c.getY();
      z[i] = c.getZ();
    }
  }

  private void GeodeticToGeocentric(Coord coord) {
    double lon = coord.getX();
    double lat = coord.getY();
    double height = coord.get(Coord.Index.Z);

    /*
     * The function Convert_Geodetic_To_Geocentric converts geodetic coordinates
     * (latitude, longitude, and height) to geocentric coordinates (X, Y, Z),
     * according to the current ellipsoid parameters.
     *
     *    Latitude  : Geodetic latitude in radians                     (input)
     *    Longitude : Geodetic longitude in radians                    (input)
     *    Height    : Geodetic height, in meters                       (input)
     *    X         : Calculated Geocentric X coordinate, in meters    (output)
     *    Y         : Calculated Geocentric Y coordinate, in meters    (output)
     *    Z         : Calculated Geocentric Z coordinate, in meters    (output)
     *
     */

    /*
     ** Don't blow up if Latitude is just a little out of the value
     ** range as it may just be a rounding issue.  Also removed longitude
     ** test, it should be wrapped by cos() and sin().  NFW for PROJ.4, Sep/2001.
     */
    if (lat < -PI_OVER_2 && lat > -1.001 * PI_OVER_2) {
      lat = -PI_OVER_2;
    } else if (lat > PI_OVER_2 && lat < 1.001 * PI_OVER_2) {
      lat = PI_OVER_2;
    } else if ((lat < -PI_OVER_2) || (lat > PI_OVER_2)) { /* lat out of range */

      coord.clear();
      return;
      //throw new ProjectionException(11);
    }

    if (lon > PI) {
      lon -= (2 * PI);
    }
    double sinLat = Math.sin(lat);
    double cosLat = Math.cos(lat);
    double sin2Lat = sinLat * sinLat;                     /*  Square of sin(Latitude)  */

    double rn = _a / (Math.sqrt(1.0e0 - _e2 * sin2Lat)); /*  Earth radius at location  */

    coord.setX((rn + height) * cosLat * Math.cos(lon));
    coord.setY((rn + height) * cosLat * Math.sin(lon));
    coord.set(Coord.Index.Z, ((rn * (1 - _e2)) + height) * sinLat);
  }

        /// <summary>
  /// Converts x, y, z to lon, lat, height
  /// </summary>
  /// <param name="xy"></param>
  /// <param name="z"></param>
  /// <param name="startIndex"></param>
  /// <param name="numPoints"></param>
  public void GeocentricToGeodetic(double[] xy, double[] z, int startIndex, int numPoints) {
    for (int i = startIndex; i < numPoints; i++) {
      Coord c = new CoordZ(xy[i * 2], xy[i * 2 + 1], z[i]);
      GeocentricToGeodetic(c);
      xy[i * 2] = c.getX();
      xy[i * 2 + 1] = c.getY();
      z[i] = c.get(Coord.Index.Z);
    }
  }

  private void IterativeMethod(Coord coord) {
    double lon;
    double height;

    double cosPhi;   /* cos of searched geodetic latitude */

    double sinPhi;     /* sin of searched geodetic latitude */

    double sinDiffPhi;    /* end-criterium: addition-theorem of sin(Latitude(iter)-Latitude(iter-1)) */

    //bool At_Pole = false;     /* indicates location is in polar region */
    double x = coord.getX();
    double y = coord.getY();
    double z = coord.get(Coord.Index.Z);
    double p = Math.sqrt(coord.getX() * coord.getX() + coord.getY() * coord.getY());

    double rr = Math.sqrt(x * x + y * y + z * z); /* distance between center and location */

    /*	special cases for latitude and longitude */
    if (p / _a < GENAU) {
      /*  special case, if P=0. (X=0., Y=0.) */
      //At_Pole = true;
      lon = 0.0;

      /*  if (X, Y, Z)=(0., 0., 0.) then Height becomes semi-minor axis
       *  of ellipsoid (=center of mass), Latitude becomes PI/2 */
      if (rr / _a < GENAU) {
                    //lat = PI_OVER_2;
        //height   = -_b;
        return;
      }
    } else {
      /*  ellipsoidal (geodetic) longitude
       *  interval: -PI < Longitude <= +PI */
      lon = Math.atan2(y, x);
    }

    /* --------------------------------------------------------------
     * Following iterative algorithm was developped by
     * "Institut für Erdmessung", University of Hannover, July 1988.
     * Internet: www.ife.uni-hannover.de
     * Iterative computation of CPHI, SPHI and Height.
     * Iteration of CPHI and SPHI to 10**-12 radian resp.
     * 2*10**-7 arcsec.
     * --------------------------------------------------------------
     */
    double ct = z / rr; // sin of geocentric latitude // looks like these two should be flipped (TD).
    double st = p / rr; // cos of geocentric latitude
    double rx = 1.0 / Math.sqrt(1.0 - _e2 * (2.0 - _e2) * st * st);
    double cosPhi0 = st * (1.0 - _e2) * rx; /* cos of start or old geodetic latitude in iterations */

    double sinPhi0 = ct * rx; /* sin of start or old geodetic latitude in iterations */

    int iter = 0; /* # of continous iteration, max. 30 is always enough (s.a.) */

    /* loop to find sin(Latitude) resp. Latitude
     * until |sin(Latitude(iter)-Latitude(iter-1))| < genau */
    do {
      iter++;
      double earthRadius = _a / Math.sqrt(1.0 - _e2 * sinPhi0 * sinPhi0);

      /*  ellipsoidal (geodetic) height */
      height = p * cosPhi0 + z * sinPhi0 - earthRadius * (1.0 - _e2 * sinPhi0 * sinPhi0);

      double rk = _e2 * earthRadius / (earthRadius + height);
      rx = 1.0 / Math.sqrt(1.0 - rk * (2.0 - rk) * st * st);
      cosPhi = st * (1.0 - rk) * rx;
      sinPhi = ct * rx;
      sinDiffPhi = sinPhi * cosPhi0 - cosPhi * sinPhi0;
      cosPhi0 = cosPhi;
      sinPhi0 = sinPhi;
    } while (sinDiffPhi * sinDiffPhi > GENAU2 && iter < MAXITER);

    /*	ellipsoidal (geodetic) latitude */
    double lat = Math.atan(sinPhi / Math.abs(cosPhi));

    coord.setX(lon);
    coord.setY(lat);
    coord.set(Coord.Index.Z, height);
  }

        /// <summary>
  /// Converts geocentric x, y, z coords to geodetic lon, lat, h
  /// </summary>
  private void GeocentricToGeodetic(Coord c) {
    IterativeMethod(c);
  }

        ///// <summary>
  ///// Converts geocentric x, y, z coords to geodetic lon, lat, h
  ///// </summary>
  //private void GeocentricToGeodeticOld(ref double x, ref double y, ref double z)
  //{
  //    double lat;
  //    double lon;
  //    double height;
  //    double cphi;     /* cos of searched geodetic latitude */
  //    double sphi;     /* sin of searched geodetic latitude */
  //    double sdphi;    /* end-criterium: addition-theorem of sin(Latitude(iter)-Latitude(iter-1)) */
  //    double p = Math.Sqrt(x*x+y*y);
  //    double rr = Math.Sqrt(x*x+y*y+z*z);
  //    /*	special cases for latitude and longitude */
  //    if (p/_a < GENAU)
  //    {
  //       /*  special case, if P=0. (X=0., Y=0.) */
  //        lon = 0;
  //        /*  if (X, Y, Z)=(0., 0., 0.) then Height becomes semi-minor axis
  //         *  of ellipsoid (=center of mass), Latitude becomes PI/2 */
  //        if (rr/_a < GENAU)
  //        {
  //            lat = PI/2;
  //            height = -_b;
  //            x = lon;
  //            y = lat;
  //            z = height;
  //            return;
  //        }
  //    }
  //    else
  //    {
  //        /*  ellipsoidal (geodetic) longitude
  //         *  interval: -PI < Longitude <= +PI */
  //        lon = Math.Atan2(y, x);
  //    }
  //    /* --------------------------------------------------------------
  //     * Following iterative algorithm was developped by
  //     * "Institut für Erdmessung", University of Hannover, July 1988.
  //     * Internet: www.ife.uni-hannover.de
  //     * Iterative computation of CPHI, SPHI and Height.
  //     * Iteration of CPHI and SPHI to 10**-12 radian resp.
  //     * 2*10**-7 arcsec.
  //     * --------------------------------------------------------------
  //     */
  //    double ct = z/rr;
  //    double st = p/rr;
  //    double rx = 1.0/Math.Sqrt(1.0-_e2*(2.0-_e2)*st*st);
  //    double cphi0 = st*(1.0-_e2)*rx;
  //    double sphi0 = ct * rx; /* sin of start or old geodetic latitude in iterations */
  //    int iter = 0;
  //    /* loop to find sin(Latitude) resp. Latitude
  //     * until |sin(Latitude(iter)-Latitude(iter-1))| < genau */
  //    do
  //    {
  //        iter++;
  //        double rn = _a/Math.Sqrt(1.0-_e2*sphi0*sphi0);       /* Earth radius at location */
  //        /*  ellipsoidal (geodetic) height */
  //        height = p*cphi0+z*sphi0-rn*(1.0-_e2*sphi0*sphi0);
  //        double rk = _e2*rn/(rn+height);
  //        rx = 1.0/Math.Sqrt(1.0-rk*(2.0-rk)*st*st);
  //        cphi = st*(1.0-rk)*rx;
  //        sphi = ct*rx;
  //        sdphi = sphi*cphi0-cphi*sphi0;
  //        cphi0 = cphi;
  //        sphi0 = sphi;
  //    }
  //    while (sdphi*sdphi > GENAU2 && iter < MAXITER);
  //    /*	ellipsoidal (geodetic) latitude */
  //    lat=Math.Atan(sphi/Math.Abs(cphi));
  //    x = lon;
  //    y = lat;
  //    z = height;
  //    return;
  //}
  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>
}
