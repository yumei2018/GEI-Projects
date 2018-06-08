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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class River {

  /**
   * The Shape of the river lines.
   */
  private Shape shape;
  /**
   * The long integer FID of the river shape.
   */
  private long id;
  /**
   * The list of MileMarker objects for this river.
   */
  private List<MileMarker> markers;
  /**
   * The string name of this river.
   */
  private String name;
  /**
   * The Envelope envelope that contains this river.
   */
  private Envelope envelope;
  /**
   * The meters to miles conversion factor.
   */
  private static final double METERS_TO_MILES = 0.000621371192;

  /**
   * The code to use for doubles if there was an error.
   */
  private static final double ERROR_CODE = -999;

  /**
   * Creates a new instance of a river.
   * @param id The long ID that is the FID for this river shape.
   * @param riverShape The final shape for this river object.  This cannot be
   * null, but can be a shape with shapetype NullShape.
   */
  public River(long id, @NonNull Shape riverShape) {
    if (riverShape == null) {
      throw new IllegalArgumentException("Parameter riverShape cannot be null.");
    }
    this.id = id;
    this.shape = riverShape;
    this.envelope = shape.getEnvelope();
    this.markers = new ArrayList<>();
  }

  /**
   * Creates a new instance of a River, without the benefit of shape lines,
   * so that distances will be calculated from marker points instead.  Markers
   * should be added to the set of markers.
   * @param id The integer FID for this river.
   */
  public River(long id){
    this.id = id;
    this.shape = new Shape();
  }

  /**
   * Sorts the markers.
   */
  public final void sort() {
    Collections.sort(markers);
  }

  /**
   * Rather than attempting to involve the lines at all, this simply
   * interpolates the distance using the points. This won't be as precise around
   * bends, but is simpler and faster to calculate.
   *
   * @param c The coordinate to interpolate to.
   * @return The double valued interpolation.
   */
  public final double interpolate(Coord c) {
    if (markers == null || markers.isEmpty()) {
      return 0;
    }
    if (markers.size() == 1) {
            // For now, assume geographic distances are in UTM coordinates
      // of meters and the desired distance is in miles.
      return c.distance(markers.get(0).getLocation()) * METERS_TO_MILES;
    }
    if (markers.size() == 2) {
      return interpolateDistance(c, markers.get(0), markers.get(1));
    }

    MileMarker closest = null;
    int closestIndex = 0;
    double mindist = Double.MAX_VALUE;
    for (int iMarker = 0; iMarker < markers.size(); iMarker++) {
      MileMarker marker = markers.get(iMarker);
      double dist = marker.getLocation().distance(c);
      if (dist < mindist) {
        closest = marker;
        closestIndex = iMarker;
        mindist = dist;
      }
    }
    MileMarker upstream = null;
    MileMarker downstream = null;
    if (closestIndex < markers.size() - 1) {
      upstream = markers.get(closestIndex + 1);
    }
    if (closestIndex > 0) {
      downstream = markers.get(closestIndex - 1);
    }

    if (upstream != null) {
      if (downstream == null || upstream.getLocation().distance(c)
              < downstream.getLocation().distance(c)) {
        return interpolateDistance(c, closest, upstream);
      }
    }

    if (downstream != null) {
      return interpolateDistance(c, closest, downstream);
    }

    if (closest != null) {
      return c.distance(closest.getLocation()) * METERS_TO_MILES;
    }
    return ERROR_CODE;

  }

  /**
   * Gets the double distance to the closest marker to the coordinate.
   *
   * @param c the coordinate to test.
   * @return the double distance.
   */
  public final double distanceToClosestMarker(Coord c) {
    double minDist = Double.MAX_VALUE;
    for (MileMarker m : this.getMarkers()) {
      double dist = m.getLocation().distance(c);
      if (dist < minDist) {
        minDist = dist;
      }
    }
    return minDist;
  }

  /**
   * Gets the closest point on the shape to the coordinate.
   *
   * @param point the point to test.  This cannot be null.
   * @return the optional Coord that is the closest.  This can be empty
   * if the river shape has no coordinates.
   * @throws IllegalArgumentException if the point parameter is null.
   */
  public final Optional<Coord> closestPointTo(@NonNull Coord point) {
    if (point == null) {
      throw new IllegalArgumentException("Parameter point cannot be null.");
    }
    if (shape != null) {
      return shape.closestPointTo(point);
    }
    return Optional.empty();
  }

  /**
   * Converts markers to lines.
   */
  public final void markersToLines() {
    List<Coord> coords = new ArrayList<>();
    for (MileMarker m : this.getMarkers()) {
      coords.add(m.getLocation());
    }
    Shape shp = new Shape(ShapeType.PolyLine);
    Part pt = new Part(coords);
    pt.setClosed(false);
    shp.getParts().add(pt);
    this.shape = shp;
  }

  /**
   * This method ignores the linear portions entirely and simply interpolates a
   * distance using purely the mile marker points. This distance is easier to
   * calculate and because it is entirely dependant on the
   *
   * @param c The coordinate to interpolate.
   * @param a The first mile marker.
   * @param b The second mile marker.
   * @return The double valued interpolation.
   */
  private double interpolateDistance(Coord c, MileMarker a,
          MileMarker b) {
    double separation = a.getLocation().distance(b.getLocation());
    double ca = c.distance(a.getLocation());
    double cb = c.distance(b.getLocation());
    if (ca < separation && cb < separation) {
      // Coordinate c falls between a and b.
      return cb / separation * a.getMile() + ca / separation
              * b.getMile();
    }
    double ab = b.getMile() - a.getMile();
    if (ca < cb) {
      // The point is on the opposite side of a from b.
      return a.getMile() - ab / separation * ca;
    } else {
      // The point is on the opposite side of b from a.
      return b.getMile() + ab / separation * cb;
    }
  }

  /**
   * Gets the Shape of the river lines.
   * @return the shape The shape for this river object.
   */
  public final Shape getShape() {
    return shape;
  }


  /**
   * @return the id
   */
  public final Long getId() {
    return id;
  }

  /**
   * @return the markers
   */
  public final List<MileMarker> getMarkers() {
    return markers;
  }

  /**
   * @param markers the markers to set
   */
  public final void setMarkers(List<MileMarker> markers) {
    this.markers = markers;
  }

  /**
   * @return the name
   */
  public final String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public final void setName(String name) {
    this.name = name;
  }

  /**
   * Gets
   * @return the envelope
   */
  public Envelope getEnvelope() {
    return envelope;
  }



}
