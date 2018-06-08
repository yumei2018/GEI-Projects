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
package gov.ca.water.shapelite;

import gov.ca.water.shapelite.coordinate.ClosestCoord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.segment.DefaultSegment;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class SegmentTest {

  @Test
  public final void testEqualsXY() {
    Segment seg = new DefaultSegment(new CoordXY(1, 2), new CoordXY(4, 5));
    Segment test = new DefaultSegment(new CoordXY(1, 2), new CoordXY(4, 5));
    boolean expResult = true;
    boolean result = seg.equals(test);
    assertEquals(expResult, result);
  }

  @Test
  public final void testEqualsZ() {
    Segment seg = new DefaultSegment(new CoordZ(1, 2, 3), new CoordZ(4, 5, 6));
    Segment test = new DefaultSegment(new CoordZ(1, 2, 3), new CoordZ(4, 5, 6));
    boolean expResult = true;
    boolean result = seg.equals(test);
    assertEquals(expResult, result);
  }

  /**
   * Test of intersection method, of class Segment.
   */
  @Test
  public final void testIntersectionZ() {
    System.out.println("intersection");
    Envelope bounds = new DefaultEnvelope(new CoordZ(0, 0, 0), new CoordZ(100, 100, 100));
    Segment instance = new DefaultSegment(new CoordZ(1, 1, 1), new CoordZ(1, 1, 200));
    Segment expResult = new DefaultSegment(new CoordZ(1, 1, 1), new CoordZ(1, 1, 100));
    Optional<Segment> result = instance.intersection(bounds);
    if (result.isPresent()) {
      boolean equals = result.get().equals(expResult);
      if (!equals) {
        fail("Expected output was not cropped in Z correctly.");
      }
    } else {
      fail("Intersection was expected but not found.");
    }

  }

  /**
   * Test of intersection method, of class Segment.
   */
  @Test
  public final void testIntersectionEnvXYSegZCrop() {
    System.out.println("intersection");
    Envelope bounds = new DefaultEnvelope(new CoordXY(0, 0), new CoordXY(100, 100));
    Segment instance = new DefaultSegment(new CoordZ(0, 0, 0), new CoordZ(200, 200, 100));
    Segment expResult = new DefaultSegment(new CoordZ(0, 0, 0), new CoordZ(100, 100, 50));
    Optional<Segment> result = instance.intersection(bounds);
    if (result.isPresent()) {
      boolean equals = result.get().equals(expResult);
      if (!equals) {
        fail("Intersection result was not what was expected.");
      }
    } else {
      fail("Intersection was expected but not found.");
    }

  }

  /**
   * Test of shortestLink method, of class Segment.
   */
  @Test
  public final void testShortestLinkSegmentP1() {
    System.out.println("shortestLink");
    Segment instance = new DefaultSegment(3, 3, 4, 4);
    Segment other = new DefaultSegment(1, 1, 2, 2);
    Segment expSeg = new DefaultSegment(3, 3, 2, 2);
    Segment resultSeg = instance.shortestLink(other);
    boolean expResult = true;
    boolean result = expSeg.equals(resultSeg);
    assertEquals(expResult, result);
  }

  /**
   * Test of shortestLink method, of class Segment.
   */
  @Test
  public final void testShortestLinkSegmentP2() {
    System.out.println("shortestLink");
    Segment instance = new DefaultSegment(1, 1, 2, 2);
    Segment other = new DefaultSegment(3, 3, 4, 4);
    Segment expSeg = new DefaultSegment(2, 2, 3, 3);
    Segment resultSeg = instance.shortestLink(other);
    boolean expResult = true;
    boolean result = expSeg.equals(resultSeg);
    assertEquals(expResult, result);
  }

  /**
   * Test of shortestLink method, of class Segment.
   */
  @Test
  public final void testShortestLinkSegmentCenter() {
    System.out.println("shortestLink");
    Segment instance = new DefaultSegment(1, 1, 3, 1);
    Segment other = new DefaultSegment(2, 2, 2, 4);
    Segment expSeg = new DefaultSegment(2, 1, 2, 2);
    Segment resultSeg = instance.shortestLink(other);
    boolean expResult = true;
    boolean result = expSeg.equals(resultSeg);
    assertEquals(expResult, result);
  }

  /**
   * Test of shortestLink method, of class Segment.
   */
  @Test
  public final void testShortestLinkCoordP1() {
    System.out.println("shortestLink");
    Coord point = new CoordXY(1, 1);
    Segment instance = new DefaultSegment(2, 2, 3, 3);
    Segment expSeg = new DefaultSegment(2, 2, 1, 1);
    Segment resultSeg = instance.shortestLink(point);
    boolean expResult = true;
    boolean result = expSeg.equals(resultSeg);
    assertEquals(expResult, result);
  }

  /**
   * Test of shortestLink method, of class Segment.
   */
  @Test
  public final void testShortestLinkCoordP2() {
    System.out.println("shortestLink");
    Coord point = new CoordXY(4, 4);
    Segment instance = new DefaultSegment(2, 2, 3, 3);
    Segment expSeg = new DefaultSegment(3, 3, 4, 4);
    Segment resultSeg = instance.shortestLink(point);
    boolean expResult = true;
    boolean result = expSeg.equals(resultSeg);
    assertEquals(expResult, result);
  }

  /**
   * Test of shortestLink method, of class Segment.
   */
  @Test
  public final void testShortestLinkCoordCenter() {
    System.out.println("shortestLink");
    Coord point = new CoordXY(3, 3);
    Segment instance = new DefaultSegment(2, 2, 2, 4);
    Segment expSeg = new DefaultSegment(2, 3, 3, 3);
    Segment resultSeg = instance.shortestLink(point);
    boolean expResult = true;
    boolean result = expSeg.equals(resultSeg);
    assertEquals(expResult, result);
  }

  /**
   * Test of reversed method, of class Segment.
   */
  @Test
  public final void testReversed() {
    System.out.println("reversed");
    Segment instance = new DefaultSegment(1, 2, 3, 4);
    Segment expSeg = new DefaultSegment(3, 4, 1, 2);
    Segment resultSeg = instance.reversed();

    boolean expResult = true;
    boolean result = expSeg.equals(resultSeg);
    assertEquals(expResult, result);
  }

  /**
   * Test of coordAt method, of class Segment.
   */
  @Test
  public final void testCoordAt() {
    System.out.println("coordAt");
    double distance = 5;
    Segment instance = new DefaultSegment(0, 0, 6, 8);
    Coord expCoord = new CoordXY(3, 4);
    Coord resultCoord = instance.coordAt(distance);
    boolean expResult = true;
    boolean result = expCoord.equals(resultCoord);
    assertEquals(expResult, result);
  }

  /**
   * Test of coordAt method, of class Segment.
   */
  @Test
  public final void testCoordAtZ() {
    System.out.println("coordAtZ");
    double distance = 5;
    Segment instance = new DefaultSegment(new CoordZ(0, 0, 0), new CoordZ(0, 0, 10));
    Coord expCoord = new CoordZ(0, 0, 5);
    Coord resultCoord = instance.coordAt(distance);
    boolean expResult = true;
    boolean result = expCoord.equals(resultCoord);
    assertEquals(expResult, result);
  }

  /**
   * Test of closestPointTo method, of class Segment.
   */
  @Test
  public final void testClosestPointToCoord() {
    System.out.println("closestPointTo");
    CoordXY point = new CoordXY(1, 1);
    Segment instance = new DefaultSegment(4, 4, 3, 3);
    ClosestCoord expCoord = new ClosestCoord(new CoordXY(3, 3),
        EndPointInteraction.PastP2);
    ClosestCoord resultCoord = instance.closestPointTo(point);
    boolean expResult = true;
    boolean result = resultCoord.equals(expCoord);
    assertEquals(expResult, result);

  }

  /**
   * Test of closestPointTo method, of class Segment.
   */
  @Test
  public final void testClosestPointToCoordBoolean() {
    System.out.println("closestPointTo");
    Coord point = new CoordXY(2, 2);
    boolean isInfiniteLine = true;
    Segment instance = new DefaultSegment(1, 1, 4, 1);
    ClosestCoord expCoord = new ClosestCoord(new CoordXY(2, 1),
        EndPointInteraction.OnLine);
    ClosestCoord resultCoord = instance.closestPointTo(point, isInfiniteLine);
    boolean expResult = true;
    boolean result = expCoord.equals(resultCoord);
    assertEquals(expResult, result);
  }

  /**
   * Test of distanceTo method, of class Segment.
   */
  @Test
  public final void testDistanceToCoord() {
    System.out.println("distanceTo");
    Coord point = new CoordXY(0, 0);
    Segment instance = new DefaultSegment(3, 4, 5, 6);
    double expResult = 5;
    double result = instance.distanceTo(point);
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of distanceTo2D method, of class Segment.
   */
  @Test
  public final void testDistanceTo2D() {
    System.out.println("distanceTo2D");
    Coord point = new CoordXY(3, 4);
    Segment instance = new DefaultSegment(-1, -2, 0, 0);
    double expResult = 5;
    double result = instance.distanceTo2D(point);
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of distanceTo2D method, of class Segment.
   */
  @Test
  public final void testDistanceTo2DEnv() {
    System.out.println("distanceTo2D Envelope");
    Envelope env = new DefaultEnvelope(-2, -2, 4, 4);
    Segment instance = new DefaultSegment(-1, -2, 0, 0);
    double expResult = 0;
    double result = instance.distance2D(env);
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of distanceTo2D method, of class Segment.
   */
  @Test
  public final void testDistanceTo2DEnvTouch() {
    System.out.println("distanceTo2D Envelope");
    Envelope env = new DefaultEnvelope(0, 0, 2, 2);
    Segment instance = new DefaultSegment(-1, -2, 0, 0);
    double expResult = 0;
    double result = instance.distance2D(env);
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of distanceTo2D method, of class Segment.
   */
  @Test
  public final void testDistanceTo2DEnvSeparated() {
    System.out.println("distanceTo2D Envelope");
    Envelope env = new DefaultEnvelope(0, 0, 2, 2);
    Segment instance = new DefaultSegment(3, 3, 4, 4);
    double expResult = Math.sqrt(2);
    double result = instance.distance2D(env);
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Tests the intersection with an envelope.
   */
  @Test
  public final void testIntersects2DNone() {
    System.out.println("testIntersects2D ");
    Envelope env = new DefaultEnvelope(0, 0, 2, 2);
    Segment instance = new DefaultSegment(3, 3, 4, 4);
    boolean result = instance.intersects2D(env);
    boolean expResult = false;
    assertEquals(expResult, result);
  }

  /**
   * Tests the intersection with an envelope.
   */
  @Test
  public final void testIntersects2DP1() {
    System.out.println("testIntersects2D P1");
    Envelope env = new DefaultEnvelope(0, 0, 2, 2);
    Segment instance = new DefaultSegment(1, 1, 4, 4);
    Optional<Segment> resultSeg = instance.intersection(env);
    boolean expResult = true;
    assertEquals(expResult, resultSeg.isPresent());
  }

  /**
   * Tests the intersection with an envelope.
   */
  @Test
  public final void testIntersects2DP2() {
    System.out.println("testIntersects2D P2");
    Envelope env = new DefaultEnvelope(0, 0, 2, 2);
    Segment instance = new DefaultSegment(4, 4, 1, 1);
    Optional<Segment> resultSeg = instance.intersection(env);
    boolean expResult = true;
    assertEquals(expResult, resultSeg.isPresent());
  }

  /**
   * Tests the intersection with an envelope.
   */
  @Test
  public final void testIntersects2DLeft() {
    System.out.println("testIntersects2D P2");
    Envelope env = new DefaultEnvelope(0, 0, 2, 2);
    Segment instance = new DefaultSegment(-1, 1, 3, 1);
    Optional<Segment> resultSeg = instance.intersection(env);
    boolean expResult = true;
    assertEquals(expResult, resultSeg.isPresent());
  }

  /**
   * Test of distanceTo method, of class Segment.
   */
  @Test
  public final void testDistanceToSegment() {
    System.out.println("distanceTo");
    Segment lineSegment = new DefaultSegment(2, 2, 5, 2);
    Segment instance = new DefaultSegment(0, 0, 0, 3);
    double expResult = 2;
    double result = instance.distanceTo(lineSegment);
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of intersectionCount method, of class Segment.
   */
  @Test
  public final void testIntersectionCount0() {
    System.out.println("intersectionCount");
    Segment other = new DefaultSegment(0, 0, 2, 2);
    Segment instance = new DefaultSegment(-1, -1, 4, -3);
    int expResult = 0;
    int result = instance.intersectionCount(other);
    assertEquals(expResult, result);
  }

  @Test
  public final void testEndpointIntersectionCount0() {
    System.out.println("EndpointIntersectionCount");
    Segment other = new DefaultSegment(0, 0, 2, 2);
    Segment instance = new DefaultSegment(-1, -1, 4, -3);
    int expResult = 0;
    int result = instance.getJointCount(other);
    assertEquals(expResult, result);
  }

  @Test
  public final void testEndpointIntersectionCountP1() {
    System.out.println("EndpointIntersectionCount P1");
    Segment other = new DefaultSegment(0, 0, 2, 2);
    Segment instance = new DefaultSegment(2, 2, 3, 3);
    int expResult = 1;
    int result = instance.getJointCount(other);
    assertEquals(expResult, result);
  }

  @Test
  public final void testEndpointIntersectionCountP2() {
    System.out.println("EndpointIntersectionCount P2");
    Segment other = new DefaultSegment(0, 0, 2, 2);
    Segment instance = new DefaultSegment(-1, -1, 0, 0);
    int expResult = 1;
    int result = instance.getJointCount(other);
    assertEquals(expResult, result);
  }

  @Test
  public final void testEndpointIntersectionCountCross() {
    System.out.println("EndpointIntersectionCount Cross");
    Segment other = new DefaultSegment(0, 1, 2, 1);
    Segment instance = new DefaultSegment(1, 0, 1, 2);
    int expResult = 0;
    int result = instance.getJointCount(other);
    assertEquals(expResult, result);
  }

  @Test
  public final void testEndpointIntersectionCountOverlap() {
    System.out.println("EndpointIntersectionCount Overlap");
    Segment other = new DefaultSegment(0, 0, 2, 0);
    Segment instance = new DefaultSegment(1, 0, 3, 0);
    int expResult = 0;
    int result = instance.getJointCount(other);
    assertEquals(expResult, result);
  }

  @Test
  public final void testGetJointP1P2() {
    System.out.println("Get Joint P1P2");
    Segment other = new DefaultSegment(0, 0, 1, 2);
    Segment instance = new DefaultSegment(1, 2, 2, 2);
    Optional<Coord> item = instance.getJoint(other);
    Coord expCoord = new CoordXY(1, 2);
    boolean expResult = true;
    boolean result = item.get().equals(expCoord);
    assertEquals(expResult, result);
  }

  @Test
  public final void testGetJointP1P1() {
    System.out.println("Get Joint P1P1");
    Segment other = new DefaultSegment(1, 2, -1, -1);
    Segment instance = new DefaultSegment(1, 2, 2, 2);
    Optional<Coord> item = instance.getJoint(other);
    Coord expCoord = new CoordXY(1, 2);
    boolean expResult = true;
    boolean result = item.get().equals(expCoord);
    assertEquals(expResult, result);
  }

  @Test
  public final void testGetJointP2P2() {
    System.out.println("Get Joint P2P2");
    Segment other = new DefaultSegment(0, 0, 1, 2);
    Segment instance = new DefaultSegment(2, 2, 1, 2);
    Optional<Coord> item = instance.getJoint(other);
    Coord expCoord = new CoordXY(1, 2);
    boolean expResult = true;
    boolean result = item.get().equals(expCoord);
    assertEquals(expResult, result);
  }

  @Test
  public final void testGetJointP2P1() {
    System.out.println("Get Joint P2P1");
    Segment other = new DefaultSegment(1, 2, 0, 0);
    Segment instance = new DefaultSegment(2, 2, 1, 2);
    Optional<Coord> item = instance.getJoint(other);
    Coord expCoord = new CoordXY(1, 2);
    boolean expResult = true;
    boolean result = item.get().equals(expCoord);
    assertEquals(expResult, result);
  }

  @Test
  public final void testGetJointNone() {
    System.out.println("Get Joint None");
    Segment other = new DefaultSegment(0, 0, 1, 2);
    Segment instance = new DefaultSegment(2, 3, 3, 4);
    Optional<Coord> item = instance.getJoint(other);
    boolean expResult = false;
    boolean result = item.isPresent();
    assertEquals(expResult, result);
  }

  @Test
  public final void testGetJointReversed() {
    System.out.println("Get Joint Reversed");
    Segment other = new DefaultSegment(0, 1, 2, 3);
    Segment instance = new DefaultSegment(2, 3, 0, 1);
    Optional<Coord> item = instance.getJoint(other);
    boolean expResult = false;
    boolean result = item.isPresent();
    assertEquals(expResult, result);
  }

  @Test
  public final void testGetJointReverse() {
    System.out.println("Get Joint Identity");
    Segment other = new DefaultSegment(0, 1, 2, 3);
    Segment instance = new DefaultSegment(0, 1, 2, 3);
    Optional<Coord> item = instance.getJoint(other);
    boolean expResult = false;
    boolean result = item.isPresent();
    assertEquals(expResult, result);
  }

  /**
   * Test of intersectionCount method, of class Segment.
   */
  @Test
  public final void testIntersectionCount1() {
    System.out.println("intersectionCount");
    Segment other = new DefaultSegment(0, 2, 0, -2);
    Segment instance = new DefaultSegment(-2, 0, 2, 0);
    int expResult = 1;
    int result = instance.intersectionCount(other);
    assertEquals(expResult, result);
  }

  /**
   * Test of intersectionCount method, of class Segment.
   */
  @Test
  public final void testIntersectionCount2() {
    System.out.println("intersectionCount");
    Segment other = new DefaultSegment(-2, 0, 2, 0);
    Segment instance = new DefaultSegment(0, 0, 4, 0);
    int expResult = 2;
    int result = instance.intersectionCount(other);
    assertEquals(expResult, result);
  }

  /**
   * Test of intersects method, of class Segment.
   */
  @Test
  public final void testIntersectSegmentYes() {
    System.out.println("intersects");
    Segment other = new DefaultSegment(0, 0, 3, 0);
    Segment instance = new DefaultSegment(-1, -1, 3, 2);
    boolean expResult = true;
    boolean result = instance.intersects(other);
    assertEquals(expResult, result);
  }

  /**
   * Test of intersects method, of class Segment.
   */
  @Test
  public final void testIntersectSegmentNo() {
    System.out.println("intersects");
    Segment other = new DefaultSegment(0, 0, 3, 0);
    Segment instance = new DefaultSegment(-1, -1, 3, -2);
    boolean expResult = false;
    boolean result = instance.intersects(other);
    assertEquals(expResult, result);
  }

  /**
   * Test of hasZ method, of class Segment.
   */
  @Test
  public final void testHasZYes() {
    System.out.println("hasZ");
    Segment instance = new DefaultSegment(new CoordZ(1, 2, 3), new CoordZ(4, 5, 6));
    boolean expResult = true;
    boolean result = instance.hasZ();
    assertEquals(expResult, result);
  }

  /**
   * Test of hasZ method, of class Segment.
   */
  @Test
  public final void testHasZNo() {
    System.out.println("hasZ");
    Segment instance = new DefaultSegment(new CoordXY(1, 2), new CoordXY(4, 5));
    boolean expResult = false;
    boolean result = instance.hasZ();
    assertEquals(expResult, result);
  }

  /**
   * Tests touching where there is no overlap.
   */
  @Test
  public final void testTouchesNoOverlap() {
    System.out.println("touches - No Overlap");
    Segment instance = new DefaultSegment(0, 1, 2, 3);
    Segment other = new DefaultSegment(4, 5, 6, 7);
    boolean expResult = false;
    boolean result = instance.touches(other);
    assertEquals(expResult, result);
  }

  /**
   * Tests touching where there only endpoints touch.
   */
  @Test
  public final void testTouchesEndpointsOnlyP2P1() {
    System.out.println("touches - Endpoints only");
    Segment instance = new DefaultSegment(0, 1, 2, 3);
    Segment other = new DefaultSegment(2, 3, 6, 7);
    boolean expResult = true;
    boolean result = instance.touches(other);
    assertEquals(expResult, result);
  }

  /**
   * Test intersection cross sections.
   */
  @Test
  public final void testIntersectionCross() {
    System.out.println("intersection");
    Segment instance = new DefaultSegment(0, 0, 2, 0);
    Segment other = new DefaultSegment(1, -1, 1, 1);
    Optional<Shape> resultShapeItem = instance.intersection(other);
    Shape resultShape = resultShapeItem.get();
    int expCount = 1;
    assertEquals(expCount, resultShape.getCoordinates().size());
    Coord first = resultShape.first();
    boolean expResult = true;
    Coord expCoord = new CoordXY(1, 0);
    boolean result = expCoord.equals(first);
    assertEquals(expResult, result);

  }

  /**
   * Test intersection cross sections.
   */
  @Test
  public final void testIntersectionOverlap() {
    System.out.println("intersection");
    Segment instance = new DefaultSegment(0, 0, 2, 0);
    Segment other = new DefaultSegment(1, 0, 3, 0);
    Optional<Shape> resultShapeItem = instance.intersection(other);
    Shape resultShape = resultShapeItem.get();
    int expCount = 2;
    assertEquals(expCount, resultShape.getCoordinates().size());
    Coord first = resultShape.first();
    Coord second = resultShape.getCoordinates().get(1);
    boolean expResult = true;
    Coord expCoord = new CoordXY(1, 0);
    Coord expCoord2 = new CoordXY(2, 0);
    boolean result = expCoord.equals(first);
    assertEquals(expResult, result);
    result = expCoord2.equals(second);
    assertEquals(expResult, result);
  }

  /**
   * Test intersection cross sections.
   */
  @Test
  public final void testIntersectionNone() {
    System.out.println("intersection");
    Segment instance = new DefaultSegment(0, 0, 2, 0);
    Segment other = new DefaultSegment(1, -1, 1, -2);
    Optional<Shape> resultShapeItem = instance.intersection(other);
    boolean expResult = false;
    assertEquals(expResult, resultShapeItem.isPresent());
  }

  /**
   * Test intersection cross sections.
   */
  @Test
  public final void testIntersectionNull() {
    System.out.println("intersection");
    Segment instance = new DefaultSegment(0, 0, 2, 0);
    Segment other = null;
    boolean thrown = false;
    try {
      instance.intersection(other);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    boolean expResult = true;
    assertEquals(expResult, thrown);
  }

  /**
   * Tests touching where there only endpoints touch.
   */
  @Test
  public final void testTouchesEndpointsOnlyP2P2() {
    System.out.println("touches - Endpoints only");
    Segment instance = new DefaultSegment(0, 1, 2, 3);
    Segment other = new DefaultSegment(6, 7, 2, 3);
    boolean expResult = true;
    boolean result = instance.touches(other);
    assertEquals(expResult, result);
  }

  /**
   * Tests touching where there only endpoints touch.
   */
  @Test
  public final void testTouchesEndpointsOnlyP1P1() {
    System.out.println("touches - Endpoints only");
    Segment instance = new DefaultSegment(2, 3, 0, 1);
    Segment other = new DefaultSegment(2, 3, 6, 7);
    boolean expResult = true;
    boolean result = instance.touches(other);
    assertEquals(expResult, result);
  }

  /**
   * Tests touching where there only endpoints touch.
   */
  @Test
  public final void testTouchesEndpointsOnlyP1P2() {
    System.out.println("touches - Endpoints only");
    Segment instance = new DefaultSegment(2, 3, 0, 1);
    Segment other = new DefaultSegment(6, 7, 2, 3);
    boolean expResult = true;
    boolean result = instance.touches(other);
    assertEquals(expResult, result);
  }

  /**
   * Tests toString 2D.
   */
  @Test
  public final void testToString2D() {
    System.out.println("toString - 2D");
    Segment instance = new DefaultSegment(0, 1, 2, 3);
    String expResult = "(0.0, 1.0)-(2.0, 3.0)";
    String result = instance.toString();
    assertEquals(expResult, result);
  }

  /**
   * Tests toString 2D.
   */
  @Test
  public final void testToString3D() {
    System.out.println("toString - 3D");
    Segment instance = new DefaultSegment(new CoordZ(1, 2, 3), new CoordZ(4, 5, 6));
    String expResult = "(1.0, 2.0, 3.0)-(4.0, 5.0, 6.0)";
    String result = instance.toString();
    assertEquals(expResult, result);
  }

  /**
   * Tests touching where there is overlap beyond just an endpoint.
   */
  @Test
  public final void testTouchesOverlap() {
    System.out.println("touches - No Overlap");
    Segment instance = new DefaultSegment(0, 0, 2, 4);
    Segment other = new DefaultSegment(1, 2, 3, 6);
    boolean expResult = false;
    boolean result = instance.touches(other);
    assertEquals(expResult, result);
  }

  /**
   * Tests touching where there is overlap beyond just an endpoint.
   */
  @Test
  public final void testTouchesIdentity() {
    System.out.println("touches - No Overlap");
    Segment instance = new DefaultSegment(0, 0, 2, 4);
    Segment other = new DefaultSegment(0, 0, 2, 4);
    boolean expResult = false;
    boolean result = instance.touches(other);
    assertEquals(expResult, result);
  }

  /**
   * Test of hasZ method, of class Segment.
   */
  @Test
  public final void testHasZNoLeft() {
    System.out.println("hasZ");
    Segment instance = new DefaultSegment(new CoordZ(1, 2, 3), new CoordXY(4, 5));
    boolean expResult = false;
    boolean result = instance.hasZ();
    assertEquals(expResult, result);
  }

  /**
   * Test of hasZ method, of class Segment.
   */
  @Test
  public final void testHasZNoRight() {
    System.out.println("hasZ");
    Segment instance = new DefaultSegment(new CoordXY(1, 2), new CoordZ(4, 5, 6));
    boolean expResult = false;
    boolean result = instance.hasZ();
    assertEquals(expResult, result);
  }

  /**
   * Test of intersects method, of class Segment.
   */
  @Test
  public final void testIntersectsCoordYesMiddle() {
    System.out.println("intersects");
    Coord point = new CoordXY(1, 1);
    Segment instance = new DefaultSegment(0, 0, 2, 2);
    boolean expResult = true;
    boolean result = instance.intersects(point);
    assertEquals(expResult, result);
  }

  /**
   * Test of intersects method, of class Segment.
   */
  @Test
  public final void testIntersectsCoordYesP1() {
    System.out.println("intersects");
    Coord point = new CoordXY(0, 0);
    Segment instance = new DefaultSegment(0, 0, 2, 2);
    boolean expResult = true;
    boolean result = instance.intersects(point);
    assertEquals(expResult, result);
  }

  /**
   * Test of intersects method, of class Segment.
   */
  @Test
  public final void testIntersectsCoordYesP2() {
    System.out.println("intersects");
    Coord point = new CoordXY(2, 2);
    Segment instance = new DefaultSegment(0, 0, 2, 2);
    boolean expResult = true;
    boolean result = instance.intersects(point);
    assertEquals(expResult, result);
  }

  /**
   * Test of intersects method, of class Segment.
   */
  @Test
  public final void testIntersectsCoordNull() {
    System.out.println("intersects Null");
    Coord point = null;
    Segment instance = new DefaultSegment(0, 0, 2, 2);
    boolean expResult = false;
    boolean result = instance.intersects(point);
    assertEquals(expResult, result);
  }

  /**
   * Test of intersects method, of class Segment.
   */
  @Test
  public final void testIntersectsEndpointNull() {
    System.out.println("intersects Null");
    Coord point = null;
    Segment instance = new DefaultSegment(0, 0, 2, 2);
    boolean expResult = false;
    boolean result = instance.intersectsEndpoint(point);
    assertEquals(expResult, result);
  }

  /**
   * Test of intersects method, of class Segment.
   */
  @Test
  public final void testIntersectsCoordNo() {
    System.out.println("intersects");
    Coord point = new CoordXY(3, 3);
    Segment instance = new DefaultSegment(0, 0, 2, 2);
    boolean expResult = false;
    boolean result = instance.intersects(point);
    assertEquals(expResult, result);
  }

  /**
   * Test of intersectsEndpoint method, of class Segment.
   */
  @Test
  public final void testIntersectsVertexP1() {
    System.out.println("intersectsVertex");
    Coord point = new CoordXY(0, 0);
    Segment instance = new DefaultSegment(0, 0, 2, 2);
    boolean expResult = true;
    boolean result = instance.intersectsEndpoint(point);
    assertEquals(expResult, result);
  }

  /**
   * Test of intersectsEndpoint method, of class Segment.
   */
  @Test
  public final void testIntersectsVertexP2() {
    System.out.println("intersectsVertex");
    Coord point = new CoordXY(2, 2);
    Segment instance = new DefaultSegment(0, 0, 2, 2);
    boolean expResult = true;
    boolean result = instance.intersectsEndpoint(point);
    assertEquals(expResult, result);
  }

  /**
   * Test of intersectsEndpoint method, of class Segment.
   */
  @Test
  public final void testIntersectsVertexMiddle() {
    System.out.println("intersectsVertex");
    Coord point = new CoordXY(1, 1);
    Segment instance = new DefaultSegment(0, 0, 2, 2);
    boolean expResult = false;
    boolean result = instance.intersectsEndpoint(point);
    assertEquals(expResult, result);
  }

  /**
   * Test of intersectsEndpoint method, of class Segment.
   */
  @Test
  public final void testIntersectsVertexOffLine() {
    System.out.println("intersectsVertex");
    Coord point = new CoordXY(0, 2);
    Segment instance = new DefaultSegment(0, 0, 2, 2);
    boolean expResult = false;
    boolean result = instance.intersectsEndpoint(point);
    assertEquals(expResult, result);
  }

  /**
   * Test of length method, of class Segment.
   */
  @Test
  public final void testLengthVertical() {
    System.out.println("length");
    Segment instance = new DefaultSegment(0, 0, 0, 2);
    double expResult = 2;
    double result = instance.length();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of length method, of class Segment.
   */
  @Test
  public final void testLengthHorizontal() {
    System.out.println("length");
    Segment instance = new DefaultSegment(0, 0, 2, 0);
    double expResult = 2;
    double result = instance.length();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of split method, of class Segment.
   */
  @Test
  public final void testSplitMiddle() {
    System.out.println("split");
    Coord vertex = new CoordXY(2, 2);
    Segment instance = new DefaultSegment(0, 0, 3, 3);
    List<Segment> expList = new ArrayList<>();
    expList.add(new DefaultSegment(0, 0, 2, 2));
    expList.add(new DefaultSegment(2, 2, 3, 3));
    List<Segment> resultList = instance.split(vertex);
    boolean expResult = true;
    int expCount = 2;
    assertEquals(expCount, resultList.size());
    for (int i = 0; i < 2; i++) {
      boolean result = expList.get(i).equals(resultList.get(i));
      assertEquals(expResult, result);
    }
  }

  /**
   * Test of split method, of class Segment.
   */
  @Test
  public final void testSplitEdge() {
    System.out.println("split");
    Coord vertex = new CoordXY(0, 0);
    Segment instance = new DefaultSegment(0, 0, 3, 3);
    List<Segment> expList = new ArrayList<>();
    expList.add(new DefaultSegment(0, 0, 0, 0));
    expList.add(new DefaultSegment(0, 0, 3, 3));
    List<Segment> resultList = instance.split(vertex);
    int expCount = 2;
    assertEquals(expCount, resultList.size());
    boolean expResult = true;
    for (int i = 0; i < 1; i++) {
      boolean result = expList.get(i).equals(resultList.get(i));
      assertEquals(expResult, result);
    }
  }

  /**
   * Test of split method, of class Segment.
   */
  @Test
  public final void testSplitOffline() {
    System.out.println("split");
    Coord vertex = new CoordXY(-1, -1);
    Segment instance = new DefaultSegment(0, 0, 3, 3);
    List<Segment> expList = new ArrayList<>();
    expList.add(new DefaultSegment(0, 0, 3, 3));
    List<Segment> resultList = instance.split(vertex);
    int expCount = 1;
    assertEquals(expCount, resultList.size());
    boolean expResult = true;
    for (int i = 0; i < 1; i++) {
      boolean result = expList.get(i).equals(resultList.get(i));
      assertEquals(expResult, result);
    }
  }

  /**
   * Test of split method, of class Segment.
   */
  @Test
  public final void testSplitNull() {
    System.out.println("split");
    Coord vertex = null;
    Segment instance = new DefaultSegment(0, 0, 3, 3);
    List<Segment> expList = new ArrayList<>();
    expList.add(new DefaultSegment(0, 0, 3, 3));
    List<Segment> resultList = instance.split(vertex);
    int expCount = 1;
    assertEquals(expCount, resultList.size());
    boolean expResult = true;
    for (int i = 0; i < 1; i++) {
      boolean result = expList.get(i).equals(resultList.get(i));
      assertEquals(expResult, result);
    }
  }

  /**
   * Test of copy method, of class Segment.
   */
  @Test
  public final void testCopy2D() {
    System.out.println("copy");
    Segment instance = new DefaultSegment(0, 0, 3, 3);
    Segment expCopy = new DefaultSegment(0, 0, 3, 3);
    Segment resultCopy = instance.copy();
    boolean expResult = true;
    boolean result = expCopy.equals(resultCopy);
    assertEquals(expResult, result);
  }

  /**
   * Test of copy method, of class Segment.
   */
  @Test
  public final void testCopy3D() {
    System.out.println("copy");
    Segment instance = new DefaultSegment(new CoordZ(1, 2, 3), new CoordZ(4, 5, 6));
    Segment expCopy = new DefaultSegment(new CoordZ(1, 2, 3), new CoordZ(4, 5, 6));
    Segment resultCopy = instance.copy();
    boolean expResult = true;
    boolean result = expCopy.equals(resultCopy);
    assertEquals(expResult, result);
  }

  /**
   * Test of toVector method, of class Segment.
   */
  @Test
  public final void testToVector2D() {
    System.out.println("toVector2D");
    Segment instance = new DefaultSegment(0, 0, 1, 2);
    Vector expVector = new Vector(1, 2);
    Vector resultVector = instance.toVector();
    boolean expResult = true;
    boolean result = expVector.equals(resultVector);
    assertEquals(expResult, result);
  }

  /**
   * Test of toVector method, of class Segment.
   */
  @Test
  public final void testToVector3D() {
    System.out.println("toVector");
    Segment instance = new DefaultSegment(new CoordZ(0, 0, 0), new CoordZ(1, 2, 3));
    Vector expVector = new Vector(1, 2, 3);
    Vector resultVector = instance.toVector();
    boolean expResult = true;
    boolean result = expVector.equals(resultVector);
    assertEquals(expResult, result);
  }

  /**
   * Test of getAngle2D method, of class Segment.
   */
  @Test
  public final void testGetAngle2D90() {
    System.out.println("getAngle2D");
    Segment instance = new DefaultSegment(0, 0, 0, 1);
    double expResult = Math.PI / 2;
    double result = instance.getAngle2D();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of getAngle2D method, of class Segment.
   */
  @Test
  public final void testGetAngle2D45() {
    System.out.println("getAngle2D");
    Segment instance = new DefaultSegment(0, 0, 1, 1);
    double expResult = Math.PI / 4;
    double result = instance.getAngle2D();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of getAngle2D method, of class Segment.
   */
  @Test
  public final void testGetAngle2Horizontal() {
    System.out.println("getAngle2D");
    Segment instance = new DefaultSegment(0, 0, 1, 0);
    double expResult = 0;
    double result = instance.getAngle2D();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of getAngle2D method, of class Segment.
   */
  @Test
  public final void testGetAngle2ZeroLength() {
    System.out.println("getAngle2D");
    Segment instance = new DefaultSegment(0, 0, 0, 0);
    double expResult = 0;
    double result = instance.getAngle2D();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of getP1 method, of class Segment.
   */
  @Test
  public final void testGetP1() {
    System.out.println("getP1");
    Segment instance = new DefaultSegment(1, 2, 3, 4);
    Coord expCoord = new CoordXY(1, 2);
    Coord resultCoord = instance.getP1();
    boolean expResult = true;
    boolean result = expCoord.equals(resultCoord);
    assertEquals(expResult, result);
  }

  /**
   * Test of getP1 method, of class Segment.
   */
  @Test
  public final void testGetP1XYZ() {
    System.out.println("getP1");
    Segment instance = new DefaultSegment(new CoordZ(1, 2, 3), new CoordZ(4, 5, 6));
    Coord expCoord = new CoordZ(1, 2, 3);
    Coord resultCoord = instance.getP1();
    boolean expResult = true;
    boolean result = expCoord.equals(resultCoord);
    assertEquals(expResult, result);
  }

  /**
   * Test of getP1 method, of class Segment.
   */
  @Test
  public final void testGetP2() {
    System.out.println("getP2");
    Segment instance = new DefaultSegment(1, 2, 3, 4);
    Coord expCoord = new CoordXY(3, 4);
    Coord resultCoord = instance.getP2();
    boolean expResult = true;
    boolean result = expCoord.equals(resultCoord);
    assertEquals(expResult, result);
  }

  /**
   * Test of getP1 method, of class Segment.
   */
  @Test
  public final void testGetP2Z() {
    System.out.println("getP2Z");
    Segment instance = new DefaultSegment(new CoordZ(1, 2, 3), new CoordZ(4, 5, 6));
    Coord expCoord = new CoordZ(4, 5, 6);
    Coord resultCoord = instance.getP2();
    boolean expResult = true;
    boolean result = expCoord.equals(resultCoord);
    assertEquals(expResult, result);
  }





}
