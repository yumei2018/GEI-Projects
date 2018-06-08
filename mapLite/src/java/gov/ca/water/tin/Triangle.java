/*
 * The MIT License
 *
 * Copyright 2013 hdunsford.
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
package gov.ca.water.tin;

import java.util.ArrayList;
import java.util.List;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Segment;
import gov.ca.water.utils.MissingVertexException;
import java.io.Serializable;

/**
 *
 * @author hdunsford
 */
public class Triangle implements Serializable {

  //<editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The Triangle's three coordinates
   */
  public Coord a;
  public Coord b;
  public Coord c;
  /**
   * The indices of the end point in the source list of coordinates (default=0)
   */
  public int iA = 0;
  public int iB = 1;
  public int iC = 2;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Defines a new triangle exclusively by the indices. This does not yet establish the
   * vertices.
   * @param iA the index of the A vertex in the TriangleGroup's vertices list.
   * @param iB the index of the B vertex in the TriangleGroup's vertices list.
   * @param iC the index of the C vertex in the TriangleGroup's vertices list.
   * @param vertices the vertices list to build the coordinates from.
   * @throws IndexOutOfBoundsException if the vertices are out of bounds.
   */
  public Triangle(int iA, int iB, int iC, List<Coord> vertices)
          throws IndexOutOfBoundsException {
    this.iA = iA;
    this.iB = iB;
    this.iC = iC;
    this.a = vertices.get(iA);
    this.b = vertices.get(iB);
    this.c = vertices.get(iC);
  }
  
  /**
   * Defines a new triangle exclusively by three coordinates. Indices are set to 0, 1, & 
   * 2, respectively.
   * @param a the coordinate for point A
   * @param b the coordinate for point B
   * @param c the coordinate for point C
   */
  public Triangle(Coord a, Coord b, Coord c) {
    this.a = a;
    this.b = b;
    this.c = c;
    this.iA = 0;
    this.iB = 0;
    this.iC = 0;    
  }
  
  /**
   * Assign the TriangleGroup's vertices index for the three coordinates
   * @param iA the index of the A vertex in the TriangleGroup's vertices list.
   * @param iB the index of the B vertex in the TriangleGroup's vertices list.
   * @param iC the index of the C vertex in the TriangleGroup's vertices list.
   */
  public void setIndices(int iA, int iB, int iC) {
    this.iA = iA;
    this.iB = iB;
    this.iC = iC;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Tests to see if a flat plane of the specified elevation falls within the triangle.
   * @param elevation the elevation to evaluate
   * @return true at least one point is above or equal to the elevation and one point
   * below or equal.
   */
  public boolean intersectsElevation(double elevation) throws MissingVertexException {
    boolean result = false;
    if (a == null || b == null || c == null) {
      throw new MissingVertexException("One of the three vertices (a, b, or c) "
              + "was null.");
    }
    if (!((a.Z > elevation) && (b.Z > elevation) && (c.Z > elevation))) {
      // at least one point is below or equal to, so if any points are above
      // or equal to the elevation, the triangle intersects.
      result = ((a.Z >= elevation) || (b.Z >= elevation) || (c.Z >= elevation));
    }
    return result;
  }
  
  /**
   * Find the list of intersecting segments. The list can be empty if the triangle does
   * not intersect. If all the points are within Coordinate.EPSILON of the specified
   * elevation, then the whole original triangle is returned. All Z values in the result
   * are set to elevation.
   *
   * @param elevation The elevation of the desired cross section to find.
   * @return the list of segment - can be an empty list if there is no intersect
   * @throws MissingVertexException
   */
  public List<Segment> intersect(double elevation) throws MissingVertexException {
    List<Segment> result = new ArrayList<>();
    if (!this.intersectsElevation(elevation)) {
      return result;
    }
    
    // Check if all 3 segments lie on the plane.
    if ((this.onPlane(a, elevation)) && (this.onPlane(b, elevation))
            && (this.onPlane(c, elevation))) {
      result.add(segmentOnPlane(a, b, elevation));
      result.add(segmentOnPlane(b, c, elevation));
      result.add(segmentOnPlane(c, a, elevation));
      return result;
    }
    
    // Sometimes only one segment will lie in the plane.  But since
    // we are here, the whole triangle does not lie in the plane,
    // so if any segment does lie in the plane, it is the only segment
    // that can do so, and the only set of crossings we need to return.
    if ((this.onPlane(a, elevation)) && (this.onPlane(b, elevation))) {
      // This can be the only segment lying in the plane.
      result.add(this.segmentOnPlane(a, b, elevation));
      return result;
    }
    if ((this.onPlane(b, elevation)) && (this.onPlane(c, elevation))) {
      result.add(this.segmentOnPlane(b, c, elevation));
      return result;
    }
    if ((this.onPlane(c, elevation)) && (this.onPlane(a, elevation))) {
      result.add(this.segmentOnPlane(c, a, elevation));
      return result;
    }
    
    // If we got here, then the only possible intersection
    // comes without an edge lying on the plane.
    List<Coord> crossings = new ArrayList<>();
    Coord crossing = null; 
    if ((crossing = this.crossing(a, b, elevation)) != null) {
      crossings.add(crossing);
    }
    if ((crossing = this.crossing(b, c, elevation)) != null) {
      crossings.add(crossing);
    }
    if ((crossing = this.crossing(c, a, elevation)) != null) {
      crossings.add(crossing);
    }
    if (crossings.size() >= 2) {
      Segment cross = new Segment(crossings.get(0), crossings.get(1));
      result.add(cross);
    }
    return result;
  }
  
  /**
   * Check if the the coordinate's Z-Value matches the plane's z-value
   *
   * @param a the coordinate
   * @param z the plane's z-value
   * @return true if the absolute difference is less than {@linkplain Coord#EPSILON}
   */
  private boolean onPlane(Coord a, double z) {
    return Math.abs(a.Z - z) < Coord.EPSILON;
  }
  
  /**
   * This method assumes that the "on-plane" case has been handled, and the only
   * possibilities left are 1 point or 0 point of intersection with the z plane.
   *
   * @param a The coordinate staring the segment
   * @param b The coordinate ending the triangle segment
   * @param z The double valued elevation intersection point.
   * @return A Coordinate on the segment with elevation at z or else a null value if the
   * segment does not intersect with the specified elevation.
   */
  private Coord crossing(Coord a, Coord b, double z) {
    Coord result = null;
    if (Math.abs(a.Z - z) < Coord.EPSILON) {
      result = new Coord(a.X, a.Y, z);
    } else if (Math.abs(b.Z - z) < Coord.EPSILON) {
      result = new Coord(b.X, b.Y, z);
    } else {
      double dz = z - a.Z;
      double s = dz / (b.Z - a.Z);
      if ((s >= 0) && (s <= 1)) {
        // there is an intersect.
        double dx = (b.X - a.X);
        double dy = (b.Y - a.Y);
        result = new Coord(a.X + dx * s, a.Y + dy * s, z);
      }
    }
    return result;
  }
  
  /**
   * Given a segment defined by two coordinates, this returns a segment with the same two
   * coordinates projected onto the specified z elevation.
   * @param a coordinate 1
   * @param b coordinate 1
   * @param z the plane's z-value
   * @return a segment
   */
  private Segment segmentOnPlane(Coord a, Coord b, double z) {
    Coord resultA = new Coord(a.X, a.Y, z);
    Coord resultB = new Coord(b.X, b.Y, z);
    return new Segment(resultA, resultB);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Object Overrides">
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: Return "{iA} {iB} {iC}"</p>
   */
  @Override
  public String toString() {
    return Integer.toString(iA) + " " + Integer.toString(iB) + " "
            + Integer.toString(iC);
  }
  //</editor-fold>

  
}
