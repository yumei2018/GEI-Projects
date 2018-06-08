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

import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.List;
import gov.ca.water.shapelite.envelope.ArrayConvertableEnvelope;
import gov.ca.water.shapelite.envelope.ContainableEnvelope;
import gov.ca.water.shapelite.envelope.CopyableEnvelope;
import gov.ca.water.shapelite.envelope.ExpandableEnvelope;
import gov.ca.water.shapelite.envelope.IntersectableEnvelope;
import gov.ca.water.shapelite.envelope.JTSConvertableEnvelope;
import gov.ca.water.shapelite.envelope.ZoomableEnvelope;
import gov.ca.water.shapelite.envelope.ObservableEnvelope;
import gov.ca.water.shapelite.envelope.MeasurableEnvelope;

/**
 * The Envelope class. This is distinct from JTS envelope because it supports M
 * values for shapefiles.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface Envelope extends ObservableEnvelope, ExpandableEnvelope,
    IntersectableEnvelope, ContainableEnvelope,  ZoomableEnvelope,
    CopyableEnvelope, ArrayConvertableEnvelope, JTSConvertableEnvelope,
    MeasurableEnvelope, Serializable {


  /**
   * Tests for equality with another object.
   *
   * @param env An Envelope to test for equality. If obj is null, then this will
   * return false.
   * @param epsilon A double value expressing the tolerance for intersection equality.
   * @return Boolean, true if the X, Y, Z and M values are equivalent, and the
   * other object is also an Envelope.
   */
  boolean equals(@Nullable Envelope env, double epsilon);


  /**
   * Gets a Rectangle2D version of this envelope in world coordinates (so Y gets
   * larger going up).
   *
   * @return A Rectangle2D (double precision) with the specified coordinate
   * values.
   */
  Rectangle2D toRectangle2D();

  /**
   * Transforms the Envelope into a line shape. This can be useful for
   * representing extent rectangles on the map. This does not include the Z
   * coordinates.
   *
   * @return A 2D Rectangle shape created from this envelope.
   */
  Shape getBorder();

  /**
   * Returns this envelope as set of segments, starting with the top left
   * and moving clockwise.
   * @return The list of Segments.
   */
  List<Segment> getEdges2D();

  /**
   * Gets the shortest link from this envelope to the other coord.  P1 is
   * on this envelope.  If the coord intersects with this envelope, then
   * this returns the degenerate segment (coord,coord) with distance 0.
   * @param coord The coord to get the shortest link to.
   * @return The shortest link segment.
   */
  Segment shortestLink2D(Coord coord);

  /**
   * Gets the shortest link from this envelope to the other segment.  If the
   * segment intersects, this will return a degenerate segment of length
   * zero using a point within this envelope.
   * @param seg The Segment to get the shortest link to.
   * @return The shortest link segment.
   */
  Segment shortestLink2D(Segment seg);

  /**
   * Gets the shortest link from this envelope to the other segment.  If the
   * segment intersects, this will return a degenerate segment of length
   * zero using a point within this envelope.
   * @param env The Envelope to get the shortest link to.
   * @return The shortest link segment.  if the envelopes intersect,
   * this will be a degenerate segment of length 0 at some intersecting
   * location.
   */
  Segment shortestLink2D(Envelope env);

  /**
   * This buffers the envelope in all directions by the specified amount.
   * A negative buffer will contract the envelope.
   * @param distance The distance in projection units.
   * @return The Envelope
   */
  Envelope buffer(double distance);

  /**
   * This assumes an envelope in WGS84 coordinates, and will buffer the
   * envelope in feet.
   * @param distance The distance in feet.
   * @return The Envelope
   */
  Envelope bufferInFeet(double distance);

  /**
   * Splits the envelope at the specified locations, returning four child
   * envelopes.
   * @param x The double x location.
   * @param y The double y location.
   * @return The list of envelopes.
   */
  List<Envelope> partition2D(double x, double y);

  public Envelope multiply(double d);
}
