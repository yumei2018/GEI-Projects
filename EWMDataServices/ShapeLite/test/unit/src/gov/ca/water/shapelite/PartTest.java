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

import gov.ca.water.shapelite.analysis.CoordIndex;
import gov.ca.water.shapelite.compare.PartComparer;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.segment.DefaultSegment;
import gov.ca.water.shapelite.segment.ReversableSegment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PartTest {

  public PartTest() {
  }

  /**
   * Test of distance method, of class Part.
   */
  @Test
  public void testDistance() {

  }

  /**
   * Test of contains method, of class Part.
   */
  @Test
  public void testContains() {
    Part polygon = getTestPolygon();
    Coord inside = new CoordXY(50, 50);
    Assert.assertTrue(polygon.contains(inside));

  }

  @Test
  public void testDoesNotContain() {
    Part polygon = getTestPolygon();
    Coord outside = new CoordXY(250, 250);
    Assert.assertTrue(!polygon.contains(outside));
  }

  /**
   * Test of isHole method, of class Part.
   */
  @Test
  public void testIsHole() {

    // points arranged counter clockwise should be a hole.
    Part polygon = new Part();
    List<Coord> coords = polygon.getCoordinates();
    coords.add(new CoordXY(0, 0));
    coords.add(new CoordXY(100, 0));
    coords.add(new CoordXY(100, 100));
    coords.add(new CoordXY(0, 100));
    polygon.setClosed(true);
    Assert.assertTrue(polygon.isHole());
  }

  @Test
  public void testIsNotHole() {
    // points arranged clockwise should not be a hole.
    Part polygon = getTestPolygon();
    Assert.assertTrue(!polygon.isHole());
  }

  @Test
  public void testArea() {
    // points arranged clockwise should not be a hole.
    Part polygon = getTestPolygon();
    double area = polygon.area();
    Assert.assertTrue(area == 100 * 100);
  }

  private Part getTestPolygon() {
    Part polygon = new Part();
    List<Coord> coords = polygon.getCoordinates();
    coords.add(new CoordXY(0, 0));
    coords.add(new CoordXY(0, 100));
    coords.add(new CoordXY(100, 100));
    coords.add(new CoordXY(100, 0));
    polygon.setClosed(true);
    return polygon;
  }

  /**
   * Test of findAllIndices method, of class Part.
   */
  @Test
  public void testFindAllIndices() {
    System.out.println("findAllIndices");
    Coord coord = new CoordXY(100, 100);
    Part instance = getTestPolygon();
    List<Integer> result = instance.findAllIndices(coord);
    int expResult = 2;
    int resultInt = result.get(0);
    assertEquals(expResult, resultInt);
  }

  /**
   * Test of closestPointTo method, of class Part.
   */
  @Test
  public void testClosestPointTo() {
    System.out.println("closestPointTo");
    Coord point = new CoordXY(200, 200);
    Part instance = getTestPolygon();
    Coord expResult = new CoordXY(100, 100);
    Optional<Coord> result = instance.closestPointTo(point);
    assertEquals(expResult, result.get());
  }

  /**
   * Test of closestSegment method, of class Part.
   */
  @Test
  public void testClosestSegment() {
    System.out.println("closestSegment");
    Coord point = new CoordXY(200, 50);
    Part instance = getTestPolygon();
    Segment expResult = new ReversableSegment(new CoordXY(100, 100),
        new CoordXY(100, 0));
    Optional<Segment> result = instance.closestSegment(point);
    assertEquals(expResult, result.get());
  }

  /**
   * Test of getFirst method, of class Part.
   */
  @Test
  public void testGetFirst() {
    System.out.println("getFirst");
    Part instance = getTestPolygon();
    Coord expResult = new CoordXY(0, 0);
    Optional<Coord> result = instance.getFirst();
    assertEquals(expResult, result.get());
  }

  /**
   * Test of getLast method, of class Part.
   */
  @Test
  public void testGetLast() {
    System.out.println("getLast");
    Part instance = getTestPolygon();
    Coord expResult = new CoordXY(100, 0);
    Optional<Coord> result = instance.getLast();
    assertEquals(expResult, result.get());
  }

  /**
   * Test of shortestLink method, of class Part.
   */
  @Test
  public void testShortestLink() {
    System.out.println("shortestLink");
    Part otherPart = new Part();
    otherPart.getCoordinates().add(new CoordXY(300, 300));
    Part instance = getTestPolygon();
    ReversableSegment expResult = new ReversableSegment(100, 100, 300, 300);
    Optional<Segment> result = instance.shortestLink(otherPart);
    assertEquals(expResult, result.get());
  }

  /**
   * Test of indexOfClosest method, of class Part.
   */
  @Test
  public void testIndexOfClosest() {
    System.out.println("indexOfClosest");
    Coord pt = new CoordXY(300, 300);
    Part instance = getTestPolygon();
    int expResult = 2;
    Optional<Integer> result = instance.indexOfClosest(pt);
    assertEquals(expResult, result.get().intValue());

  }

  /**
   * Test of intersects method, of class Part.
   */
  @Test
  public void testIntersects() {
    System.out.println("intersects");
    Part other = new Part();
    other.getCoordinates().add(new CoordXY(-100, 50));
    other.getCoordinates().add(new CoordXY(300, 50));
    Part instance = getTestPolygon();
    boolean expResult = true;
    boolean result = instance.intersects(other);
    assertEquals(expResult, result);
  }

  /**
   * tests the getEnvelope method, of class Part.
   */
  @Test
  public void getEnvelope() {
    System.out.println("get envelope");
    Part instance = new Part();
    instance.getCoordinates().add(new CoordXY(-1, -2));
    instance.getCoordinates().add(new CoordXY(3, 6));
    Envelope expResult = new DefaultEnvelope(-1, -2, 3, 6);
    assertEquals(expResult, instance.getEnvelope());
  }

  /**
   * Test of distance method, of class Part.
   */
  @Test
  public void testDistanceCoord() {
    System.out.println("distance");
    Coord coordinate = new CoordXY(400, 400);
    Part instance = getTestPolygon();
    double expResult = Math.sqrt(300 * 300 + 300 * 300);
    Optional<Double> result = instance.distance(coordinate);
    assertEquals(expResult, result.get(), 0);
  }

  /**
   * Test of distance2D method, of class Part.
   */
  @Test
  public void testDistance2D() {
    System.out.println("distance2D");
    Coord coordinate = new CoordZ(300, 300, 300, 300);
    Part instance = getTestPolygon();
    double expResult = Math.sqrt(200 * 200 + 200 * 200);
    Optional<Double> result = instance.distance2D(coordinate);
    assertEquals(expResult, result.get(), 0);

  }

  /**
   * Test of distance method, of class Part.
   */
  @Test
  public void testDistanceSegment() {
    System.out.println("distance");
    Segment target = new DefaultSegment(200, 400, 400, 200);
    Part instance = getTestPolygon();
    double expResult = Math.sqrt(200 * 200 + 200 * 200);
    Optional<Double> result = instance.distance(target);
    assertEquals(expResult, result.get(), 0);
  }

  /**
   * Test of distance method, of class Part.
   */
  @Test
  public void testDistancePart() {
    System.out.println("distance");
    Part part = new Part();
    part.getCoordinates().add(new CoordXY(200, 400));
    part.getCoordinates().add(new CoordXY(400, 200));
    Part instance = getTestPolygon();
    double expResult = Math.sqrt(200 * 200 + 200 * 200);
    Optional<Double> result = instance.distance(part);
    assertEquals(expResult, result.get(), 0);
  }

  /**
   * Test of end method, of class Part.
   */
  @Test
  public void testEnd() {
    System.out.println("end");
    Part instance = getTestPolygon();
    Coord expResult = new CoordXY(100, 0);
    Optional<Coord> result = instance.end();
    assertEquals(expResult, result.get());
  }

  /**
   * Test of getSegments method, of class Part.
   */
  @Test
  public void testGetSegments() {
    System.out.println("getSegments");
    Part instance = getTestPolygon();

    List<Segment> result = instance.getSegments();

    Segment expResult = new DefaultSegment(0, 0, 0, 100);
    assertEquals(expResult, result.get(0));
    expResult = new DefaultSegment(0, 100, 100, 100);
    assertEquals(expResult, result.get(1));
    expResult = new DefaultSegment(100, 100, 100, 0);
    assertEquals(expResult, result.get(2));
    expResult = new DefaultSegment(100, 0, 0, 0);
    assertEquals(expResult, result.get(3));

  }

  /**
   * Test of getSegmentCount method, of class Part.
   */
  @Test
  public void testGetSegmentCount() {
    System.out.println("getSegmentCount");
    Part instance = getTestPolygon();
    int expResult = 4;
    int result = instance.getSegmentCount();
    assertEquals(expResult, result);
  }

  /**
   * Test of getReversableSegments method, of class Part.
   */
  @Test
  public void testGetReversableSegments() {
    System.out.println("getReversableSegments");
    Part instance = getTestPolygon();
    List<ReversableSegment> result = instance.getReversableSegments();
    Segment expResult = new DefaultSegment(0, 0, 0, 100);
    assertEquals(expResult, result.get(0));
    expResult = new DefaultSegment(0, 100, 100, 100);
    assertEquals(expResult, result.get(1));
    expResult = new DefaultSegment(100, 100, 100, 0);
    assertEquals(expResult, result.get(2));
    expResult = new DefaultSegment(100, 0, 0, 0);
    assertEquals(expResult, result.get(3));
  }

  /**
   * Test of getSegmentsReversed method, of class Part.
   */
  @Test
  public void testGetSegmentsReversed() {
    System.out.println("getSegmentsReversed");
    Part instance = getTestPolygon();

    List<Segment> result = instance.getSegmentsReversed();
    Segment expResult = new DefaultSegment(0, 0, 100, 0);
    assertEquals(expResult, result.get(0));
    expResult = new DefaultSegment(100, 0, 100, 100);
    assertEquals(expResult, result.get(1));
    expResult = new DefaultSegment(100, 100, 0, 100);
    assertEquals(expResult, result.get(2));
    expResult = new DefaultSegment(0, 100, 0, 0);
    assertEquals(expResult, result.get(3));
  }

  /**
   * Test of removeDuplicates method, of class Part.
   */
  @Test
  public void testRemoveDuplicates() {
    System.out.println("removeDuplicates");
    Part instance = new Part();
    instance.getCoordinates().add(new CoordXY(100, 100));
    instance.getCoordinates().add(new CoordXY(100, 100));
    instance.removeDuplicates();
  }

  /**
   * Test of removeCoordinateAt method, of class Part.
   */
  @Test
  public void testRemoveCoordinateAt() {
    System.out.println("removeCoordinateAt");
    int index = 9;
    PartFactory factory = new PartFactory(124);
    Part instance = factory.randomPart(20);
    Coord c = instance.getCoordinates().get(9);
    instance.removeCoordinateAt(index);
    int expectedCount = 19;
    assertEquals(expectedCount, instance.getCoordinates().size());
    boolean expContains = false;
    assertEquals(expContains, instance.getCoordinates().contains(c));
  }

  /**
   * Test of removeCoordinatesAt method, of class Part.
   */
  @Test
  public void testRemoveCoordinatesAt() {
    System.out.println("removeCoordinatesAt");
    PartFactory factory = new PartFactory(1224);
    Part instance = factory.randomPart(5);
    List<Integer> indices = new ArrayList<>();
    indices.add(1);
    indices.add(4);
    Coord c1 = instance.getCoordinates().get(1);
    Coord c3 = instance.getCoordinates().get(3);
    Coord c4 = instance.getCoordinates().get(4);
    instance.removeCoordinatesAt(indices);
    // The high index is removed before the low index, so this shouldn't break.
    int expCount = 3;
    assertEquals(expCount, instance.getCoordinates().size());
    boolean expContains = false;
    assertEquals(expContains, instance.getCoordinates().contains(c1));
    assertEquals(expContains, instance.getCoordinates().contains(c4));
    expContains = true;
    assertEquals(expContains, instance.getCoordinates().contains(c3));
  }

  /**
   * Test of removeDuplicates method, of class Part.
   */
  @Test
  public void testRemoveDuplicatesDouble() {
    System.out.println("removeDuplicates");
    double epsilon = 0.0;
    PartFactory factory = new PartFactory(1224);
    Part instance = factory.randomPart(5);
    int size = instance.getCoordinates().size();
    int iLast = size - 1;
    Coord c2 = instance.getCoordinates().get(iLast).copy();
    instance.getCoordinates().add(c2);
    int expSize = size + 1;
    assertEquals(instance.getCoordinates().size(), expSize);
    instance.removeDuplicates(epsilon);
    expSize = size;
    assertEquals(instance.getCoordinates().size(), expSize);

  }

  /**
   * Test of reverse method, of class Part.
   */
  @Test
  public void testReverse() {
    System.out.println("reverse");
    PartFactory factory = new PartFactory(1226);
    int size = 5;
    Part instance = factory.randomPart(size);
    Part copy = instance.copy();
    instance.reverse();
    int expSize = size;
    assertEquals(expSize, instance.getCoordinates().size());
    for (int i = 0; i < size; i++) {
      int iReverse = (size - 1) - i;
      assertEquals(instance.getCoordinates().get(i),
          copy.getCoordinates().get(iReverse));
    }
  }

  /**
   * Test of getCoordinateAtDistance method, of class Part.
   */
  @Test
  public void testGetCoordinateAtDistance() {
    System.out.println("getCoordinateAtDistance");
    double distance = 5;
    Part instance = new Part();
    Coord c = new CoordXY(0, 0);
    instance.getCoordinates().add(c);
    Coord c2 = new CoordXY(3, 4);
    instance.getCoordinates().add(c2);
    Coord c3 = new CoordXY(6, 0);
    instance.getCoordinates().add(c3);
    Coord expResult = new CoordXY(3, 4);
    Optional<Coord> result = instance.getCoordinateAtDistance(distance);
    assertEquals(expResult, result.get());
  }

  /**
   * Test of getCoordinateAtDistance method, of class Part.
   */
  @Test
  public void testGetCoordinateAtDistanceNegative() {
    System.out.println("getCoordinateAtDistance Negative");
    double distance = -5;
    Part instance = new Part();
    Coord c = new CoordXY(0, 0);
    instance.getCoordinates().add(c);
    Coord c2 = new CoordXY(3, 4);
    instance.getCoordinates().add(c2);
    Coord c3 = new CoordXY(6, 0);
    instance.getCoordinates().add(c3);
    Coord expResult = new CoordXY(-3, -4);
    Optional<Coord> result = instance.getCoordinateAtDistance(distance);
    assertEquals(expResult, result.get());
  }

  /**
   * Test of getCoordinateAtDistance method, of class Part.
   */
  @Test
  public void testGetCoordinateAtDistanceBeyond() {
    System.out.println("getCoordinateAtDistance Beyond");
    double distance = 15;
    Part instance = new Part();
    Coord c = new CoordXY(0, 0);
    instance.getCoordinates().add(c);
    Coord c2 = new CoordXY(3, 4);
    instance.getCoordinates().add(c2);
    Coord c3 = new CoordXY(6, 0);
    instance.getCoordinates().add(c3);
    Coord expResult = new CoordXY(9, -4);
    Optional<Coord> result = instance.getCoordinateAtDistance(distance);
    assertEquals(expResult, result.get());
  }

  /**
   * Test of getCoordinateAtDistance method, of class Part.
   */
  @Test
  public void testGetCoordAtDistGeneralized() {
    System.out.println("getCoordAtDistGeneralized");
    double distance = 5;
    Part instance = new Part();
    Coord c = new CoordXY(0, 0);
    instance.getCoordinates().add(c);
    Coord c2 = new CoordXY(3, 4);
    instance.getCoordinates().add(c2);
    Coord c3 = new CoordXY(6, 0);
    instance.getCoordinates().add(c3);
    Coord expResult = new CoordXY(3, 4);
    Optional<Coord> result = instance.getCoordAtDistGeneralized(distance);
    assertEquals(expResult, result.get());
  }

  /**
   * Test of getCoordinateAtDistance method, of class Part.
   */
  @Test
  public void testGetCoordAtDistGeneralizedNegative() {
    System.out.println("getCoordAtDistGeneralized Negative");
    double distance = -5;
    Part instance = new Part();
    Coord c = new CoordXY(0, 0);
    instance.getCoordinates().add(c);
    Coord c2 = new CoordXY(3, 4);
    instance.getCoordinates().add(c2);
    Coord c3 = new CoordXY(6, 0);
    instance.getCoordinates().add(c3);
    Coord expResult = new CoordXY(-5, 0);
    Optional<Coord> result = instance.getCoordAtDistGeneralized(distance);
    assertEquals(expResult, result.get());
  }

  /**
   * Test of getCoordinateAtDistance method, of class Part.
   */
  @Test
  public void testGetCoordAtDistGeneralizedBeyond() {
    System.out.println("getCoordAtDistGeneralized Beyond");
    double distance = 15;
    Part instance = new Part();
    Coord c = new CoordXY(0, 0);
    instance.getCoordinates().add(c);
    Coord c2 = new CoordXY(3, 4);
    instance.getCoordinates().add(c2);
    Coord c3 = new CoordXY(6, 0);
    instance.getCoordinates().add(c3);
    Coord expResult = new CoordXY(11, 0);
    Optional<Coord> result = instance.getCoordAtDistGeneralized(distance);
    assertEquals(expResult, result.get());
  }

  /**
   * Test of getAngleAtDistance method, of class Part.
   */
  @Test
  public void testGetAngleAtDistance() {
    System.out.println("getAngleAtDistance");
    double distance = 7;
    Part instance = new Part();
    Coord c = new CoordXY(0, 0);
    instance.getCoordinates().add(c);
    Coord c2 = new CoordXY(4, 4);
    instance.getCoordinates().add(c2);
    Coord c3 = new CoordXY(8, 0);
    instance.getCoordinates().add(c3);
    double expResult = -Math.PI / 4;
    Optional<Double> result = instance.getAngleAtDistance(distance);
    assertEquals(expResult, result.get(), 0);
  }

  /**
   * Test of length method, of class Part.
   */
  @Test
  public void testLength() {
    Part instance = new Part();
    Coord c = new CoordXY(0, 0);
    instance.getCoordinates().add(c);
    Coord c2 = new CoordXY(3, 4);
    instance.getCoordinates().add(c2);
    Coord c3 = new CoordXY(6, 0);
    instance.getCoordinates().add(c3);
    double expResult = 10;
    double result = instance.length();
    assertEquals(expResult, result, 0.0);
  }

  /**
   * Test of split method, of class Part.
   */
  @Test
  public void testSplitSizesCorrect() {
    System.out.println("SplitSizesCorrect");
    Part lineString = new Part();
    for (int i = 0; i < 10; i++) {
      lineString.getCoordinates().add(new CoordXY(i, 2 * i));
    }
    Coord c = lineString.getCoordinates().get(3);
    boolean startWithC = false;
    List<Part> result = lineString.split(c, startWithC);
    assertEquals(2, result.size());
    assertEquals(4, result.get(0).getCoordinates().size());
    assertEquals(7, result.get(1).getCoordinates().size());
  }

  /**
   * Test of split method, of class Part.
   */
  @Test
  public void testSplitCoordInBothParts() {
    Part lineString = new Part();
    for (int i = 0; i < 10; i++) {
      lineString.getCoordinates().add(new CoordXY(i, 2 * i));
    }
    Coord c = lineString.getCoordinates().get(3);
    boolean startWithC = false;
    List<Part> result = lineString.split(c, startWithC);
    assertEquals(2, result.size());
    assertEquals(4, result.get(0).getCoordinates().size());
    assertEquals(7, result.get(1).getCoordinates().size());
    Coord last = result.get(0).getLast().get();
    Coord first = result.get(1).getFirst().get();
    assertEquals(c, last);
    assertEquals(c, first);
  }

  /**
   * Test of split method, of class Part.
   */
  @Test
  public void testSplitStartWithC() {
    Part lineString = new Part();
    for (int i = 0; i < 10; i++) {
      lineString.getCoordinates().add(new CoordXY(i, 2 * i));
    }
    Coord c = lineString.getCoordinates().get(3);
    boolean startWithC = true;
    List<Part> result = lineString.split(c, startWithC);
    assertEquals(2, result.size());
    assertEquals(4, result.get(0).getCoordinates().size());
    assertEquals(7, result.get(1).getCoordinates().size());
    Coord part1 = result.get(0).getFirst().get();
    Coord part2 = result.get(1).getFirst().get();
    assertEquals(c, part1);
    assertEquals(c, part2);
  }

  /**
   * Test of split method, of class Part.
   */
  @Test
  public void testSplitFirst() {
    Part lineString = new Part();
    for (int i = 0; i < 10; i++) {
      lineString.getCoordinates().add(new CoordXY(i, 2 * i));
    }
    Coord c = lineString.getCoordinates().get(0);
    boolean startWithC = true;
    List<Part> result = lineString.split(c, startWithC);
    assertEquals(1, result.size());
    assertEquals(10, result.get(0).getCoordinates().size());
    Coord first = result.get(0).getFirst().get();
    assertEquals(c, first);
  }

  /**
   * Test of split method, of class Part.
   */
  @Test
  public void testSplitOnLast() {
    Part lineString = new Part();
    for (int i = 0; i < 10; i++) {
      lineString.getCoordinates().add(new CoordXY(i, 2 * i));
    }
    Coord c = lineString.getCoordinates().get(lineString.getCoordinates().size() - 1);
    boolean startWithC = false;
    List<Part> result = lineString.split(c, startWithC);
    assertEquals(1, result.size());
    assertEquals(10, result.get(0).getCoordinates().size());
    Coord last = result.get(0).getLast().get();
    assertEquals(c, last);
  }

  /**
   * Test of split method, of class Part.
   */
  @Test
  public void testSplitOnLastStartWithC() {
    Part lineString = new Part();
    for (int i = 0; i < 10; i++) {
      lineString.getCoordinates().add(new CoordXY(i, 2 * i));
    }
    Coord c = lineString.getCoordinates().get(lineString.getCoordinates().size() - 1);
    boolean startWithC = true;
    List<Part> result = lineString.split(c, startWithC);
    assertEquals(1, result.size());
    assertEquals(10, result.get(0).getCoordinates().size());
    Coord first = result.get(0).getFirst().get();
    Coord last = result.get(0).getLast().get();
    assertEquals(c, first);
    assertEquals(lineString.getCoordinates().get(0), last);
  }

  /**
   * Test of split method, of class Part.
   */
  @Test
  public void testSplitBetween() {
    Part lineString = new Part();
    for (int i = 0; i < 10; i++) {
      lineString.getCoordinates().add(new CoordXY(i, 2 * i));
    }
    Coord c = new CoordXY(3.5, 7);
    boolean startWithC = false;
    List<Part> result = lineString.split(c, startWithC);
    assertEquals(2, result.size());
    assertEquals(5, result.get(0).getCoordinates().size());
    assertEquals(7, result.get(1).getCoordinates().size());
    Coord last = result.get(0).getLast().get();
    Coord first = result.get(1).getFirst().get();
    assertEquals(c, first);
    assertEquals(c, last);
  }

  /**
   * Split on a single coordinate should just return the original, unmodified
   * part coordinates.
   */
  @Test
  public void testSplitOneCoord() {
    Part lineString = new Part();
    Coord c = new CoordXY(1, 2);
    lineString.getCoordinates().add(c);
    boolean startWithC = false;
    List<Part> result = lineString.split(c, startWithC);
    assertEquals(1, result.size());
    assertEquals(1, result.get(0).getCoordinates().size());
    Coord first = result.get(0).getLast().get();
    assertEquals(c, first);
  }

  /**
   * Test of copy method, of class Part.
   */
  @Test
  public void testCopy() {
    System.out.println("Copy");
    PartComparer pc = new PartComparer();
    PartFactory factory = new PartFactory(123);
    Part instance = factory.randomPart(10);
    boolean expResult = true;
    Part result = instance.copy();
    assertEquals(expResult, pc.equivalent(instance, result));
  }

  /**
   * Test of start method, of class Part.
   */
  @Test
  public void testStart() {
    System.out.println("start");
    PartFactory factory = new PartFactory(653);
    Part instance = factory.randomPart(10);
    Coord expResult = instance.getCoordinates().get(0);
    Optional<Coord> result = instance.start();
    assertEquals(expResult, result.get());
  }

  /**
   * Test of start method, of class Part.
   */
  @Test
  public void testStartEmpty() {
    System.out.println("start");
    Part instance = new Part();
    boolean expResult = false;
    Optional<Coord> result = instance.start();
    assertEquals(expResult, result.isPresent());
  }

  /**
   * Test of isEmpty method, of class Part.
   */
  @Test
  public void testIsEmptyTrue() {
    System.out.println("isEmpty");
    Part instance = new Part();
    boolean expResult = true;
    boolean result = instance.isEmpty();
    assertEquals(expResult, result);
  }

  /**
   * Test of isEmpty method, of class Part.
   */
  @Test
  public void testIsEmptyFalse() {
    System.out.println("isEmpty");
    PartFactory factory = new PartFactory(9877);
    Part instance = factory.randomPart(10);
    boolean expResult = false;
    boolean result = instance.isEmpty();
    assertEquals(expResult, result);
  }

  /**
   * Test of isClosed method, of class Part.
   */
  @Test
  public void testIsClosedTrue() {
    System.out.println("set/isClosed-True");
    PartFactory factory = new PartFactory(981);
    Part instance = factory.randomPart(7);
    boolean expResult = false;
    boolean result = instance.isClosed();
    assertEquals(expResult, result);
  }

  /**
   * Test of isClosed method, of class Part.
   */
  @Test
  public void testIsClosedFalse() {
    System.out.println("set/isClosed-False");
    Part instance = new Part();
    instance.setClosed(true);
    boolean expResult = true;
    boolean result = instance.isClosed();
    assertEquals(expResult, result);
  }

  /**
   * Test of iterator method, of class Part.
   */
  @Test
  public void testIterator() {
    System.out.println("iterator");
    PartFactory factory = new PartFactory(342);
    Part instance = factory.randomPart(7);
    Iterator<CoordIndex> result = instance.iterator();
    int expectedIndex = 0;
    while (result.hasNext()) {
      CoordIndex ci = result.next();
      assertEquals(expectedIndex, ci.getIndex());
      assertEquals(instance.getCoordinates().get(expectedIndex), ci.getCoord());
      expectedIndex++;
    }
  }

  /**
   * Tests the shortestLink method.
   */
  @Test
  public void testShortestLink_OtherNull() {
    System.out.println("shortestLink other null");
    boolean thrown = false;
    Part instance = new Part();
    try {
      instance.shortestLink(null);
    } catch (IllegalArgumentException ex) {
      thrown = true;
    }
    boolean expResult = true;
    assertEquals(expResult, thrown);
  }

  /**
   * Tests the shortestLink method.
   */
  @Test
  public void testShortestLink_ThisEmpty() {
    System.out.println("shortestLink this empty");
    Part instance = new Part();
    Part other = new Part();
    Optional<Segment> maybeSeg = instance.shortestLink(other);
    boolean expResult = false;
    assertEquals(expResult, maybeSeg.isPresent());
  }

  /**
   * Tests the shortestLink method.
   */
  @Test
  public void testShortestLink_1_1() {
    System.out.println("shortestLink from One");
    Part instance = new Part();
    Coord c = new CoordXY(0, 0);
    instance.getCoordinates().add(c);
    Part other = new Part();
    Coord c2 = new CoordXY(1, 0);
    other.getCoordinates().add(c2);
    Optional<Segment> maybeSeg = instance.shortestLink(other);
    boolean expResult = true;
    assertEquals(expResult, maybeSeg.isPresent());
    assertEquals(c, maybeSeg.get().getP1());
    assertEquals(c2, maybeSeg.get().getP2());
  }

  /**
   * Tests the shortestLink method.
   */
  @Test
  public void testShortestLink_1_2() {
    System.out.println("shortestLink from One");
    Part instance = new Part();
    Coord c = new CoordXY(0, 0);
    instance.getCoordinates().add(c);
    Part other = new Part();
    Coord c2 = new CoordXY(1, 1);
    other.getCoordinates().add(c2);
    Coord c3 = new CoordXY(1, -1);
    other.getCoordinates().add(c3);
    Optional<Segment> maybeSeg = instance.shortestLink(other);
    boolean expResult = true;
    assertEquals(expResult, maybeSeg.isPresent());
    assertEquals(c, maybeSeg.get().getP1());
    Coord expCoord = new CoordXY(1, 0);
    assertEquals(expCoord, maybeSeg.get().getP2());
  }

  /**
   * Tests the shortestLink method.
   */
  @Test
  public void testShortestLink_2_2() {
    System.out.println("shortestLink from One");
    Part instance = new Part();
    Coord ca = new CoordXY(-1, 1);
    instance.getCoordinates().add(ca);
    Coord cb = new CoordXY(0, 0);
    instance.getCoordinates().add(cb);
    Coord cc = new CoordXY(-1, -1);
    instance.getCoordinates().add(cc);
    Part other = new Part();
    Coord c2 = new CoordXY(1, 1);
    other.getCoordinates().add(c2);
    Coord c3 = new CoordXY(1, -1);
    other.getCoordinates().add(c3);
    Optional<Segment> maybeSeg = instance.shortestLink(other);
    boolean expResult = true;
    assertEquals(expResult, maybeSeg.isPresent());
    assertEquals(cb, maybeSeg.get().getP1());
    Coord expCoord = new CoordXY(1, 0);
    assertEquals(expCoord, maybeSeg.get().getP2());
  }

  @Test
  public void testClip() {
    System.out.println("Test Clip");
    PartFactory fact = new PartFactory(123);
    Part instance = fact.randomPart(10);
    Coord startPoint = instance.getCoordinates().get(1);
    Coord endPoint = instance.getCoordinates().get(5);
    Part result = instance.clip(startPoint, endPoint);
    assertEquals(startPoint, result.getFirst().get());
    assertEquals(endPoint, result.getLast().get());
    int expLength = 5;
    assertEquals(expLength, result.getCoordinates().size());
  }

  /**
   * Tests the clip where the second coordinate is found before the first
   * coordinate.
   */
  @Test
  public void testClipFlip() {
    System.out.println("Test Clip Flip");
    Part instance = new Part();
    for (int i = 0; i < 10; i++) {
      CoordXY coord = new CoordXY(i, 2 * i);
      instance.getCoordinates().add(coord);
    }
    Coord startPoint = instance.getCoordinates().get(6);
    Coord endPoint = instance.getCoordinates().get(1);
    Part result = instance.clip(startPoint, endPoint);
    assertEquals(endPoint, result.getFirst().get());
    assertEquals(startPoint, result.getLast().get());
    int expLength = 6;
    assertEquals(expLength, result.getCoordinates().size());
  }

  /**
   * Tests the case where a split instruction is given, but the point is not on
   * the line, and the closest point is not in the middle of the line but rather
   * past the end point. In this case, should just return the original,
   * unchanged part.
   */
  @Test
  public void testSplitOnEndPoint() {
    System.out.println("Test Split on Endpoint");
    Part instance = new Part();
    for (int i = 0; i < 10; i++) {
      CoordXY coord = new CoordXY(i, 2 * i);
      instance.getCoordinates().add(coord);
    }
    Coord point = new CoordXY(-1, -2);
    // Not on the line, and closest point is an endpoint.
    boolean startWithC = false;
    List<Part> splits = instance.split(point, startWithC);
    assertEquals(1, splits.size());
    for (int i = 0; i < 10; i++) {
      assertEquals(instance.getCoordinates().get(i),
          splits.get(0).getCoordinates().get(i));
    }
  }

  /**
   * Tests the case where a split instruction is given, but the point is not on
   * the line, and the closest point is not in the middle of the line but rather
   * past the last point. If startWithC is false, this is just a copy of the
   * original part. Otherwise, it is reversed.
   */
  @Test
  public void testSplitOnEndPointLast() {
    System.out.println("Test Split on End Point Last");
    Part instance = new Part();
    for (int i = 0; i < 10; i++) {
      CoordXY coord = new CoordXY(i, 2 * i);
      instance.getCoordinates().add(coord);
    }
    Coord point = new CoordXY(12, 24);
    // Not on the line, and closest point is an endpoint.
    boolean startWithC = false;
    List<Part> splits = instance.split(point, startWithC);
    assertEquals(1, splits.size());
    for (int i = 0; i < 10; i++) {
      assertEquals(instance.getCoordinates().get(i),
          splits.get(0).getCoordinates().get(i));
    }
  }

  /**
   * Tests the case where a split instruction is given, but the point is not on
   * the line, and the closest point is not in the middle of the line but rather
   * past the last point. If startWithC is false, this is just a copy of the
   * original part. Otherwise, it is reversed.
   */
  @Test
  public void testSplitOnEndPointLastReverse() {
    System.out.println("Test Split on End Point Last Reverse");
    Part instance = new Part();
    for (int i = 0; i < 10; i++) {
      CoordXY coord = new CoordXY(i, 2 * i);
      instance.getCoordinates().add(coord);
    }
    Coord point = new CoordXY(12, 24);
    // Not on the line, and closest point is an endpoint.
    boolean startWithC = true;
    List<Part> splits = instance.split(point, startWithC);
    assertEquals(1, splits.size());
    for (int i = 0; i < 10; i++) {
      assertEquals(instance.getCoordinates().get(i),
          splits.get(0).getCoordinates().get(9 - i));
    }
  }

  /**
   * This tests the clip by distance function.
   */
  @Test
  public void testClipDistance() {
    System.out.println("Test Clip by Distance");
    Part instance = new Part();
    for (int i = 0; i < 10; i++) {
      CoordXY coord = new CoordXY(3 * i, 4 * i); // Each segment is length 5
      instance.getCoordinates().add(coord);
    }
    Part part = instance.clip(10.0, 30.0);
    Coord first = part.getFirst().get();
    Coord last = part.getLast().get();
    assertEquals(instance.getCoordinates().get(2), first);
    assertEquals(instance.getCoordinates().get(6), last);
    assertEquals(5, part.getCoordinates().size());

  }

  /**
   * This tests the clip by distance function.
   */
  @Test
  public void testClipDistanceReverse() {
    System.out.println("Test Clip by Distance Reverse");
    Part instance = new Part();
    for (int i = 0; i < 10; i++) {
      CoordXY coord = new CoordXY(3 * i, 4 * i); // Each segment is length 5
      instance.getCoordinates().add(coord);
    }
    Part part = instance.clip(30.0, 10.0);
    Coord first = part.getFirst().get();
    Coord last = part.getLast().get();
    assertEquals(instance.getCoordinates().get(6), first);
    assertEquals(instance.getCoordinates().get(2), last);
    assertEquals(5, part.getCoordinates().size());

  }

  /**
   * This tests the clip by distance function.
   */
  @Test
  public void testClipDistanceWhole() {
    System.out.println("Test Clip by Distance Reverse");
    Part instance = new Part();
    for (int i = 0; i < 10; i++) {
      CoordXY coord = new CoordXY(3 * i, 4 * i); // Each segment is length 5
      instance.getCoordinates().add(coord);
    }
    Part part = instance.clip(-1.0, 67.0);
    Coord first = part.getFirst().get();
    Coord last = part.getLast().get();
    assertEquals(instance.getCoordinates().get(0), first);
    assertEquals(instance.getCoordinates().get(9), last);
    assertEquals(10, part.getCoordinates().size());
  }

  /**
   * This tests the clip by distance function.
   */
  @Test
  public void testClipDistanceWholeReversed() {
    System.out.println("Test Clip by Distance Reverse");
    Part instance = new Part();
    for (int i = 0; i < 10; i++) {
      CoordXY coord = new CoordXY(3 * i, 4 * i); // Each segment is length 5
      instance.getCoordinates().add(coord);
    }
    Part part = instance.clip(67.0, -1.0);
    Coord first = part.getFirst().get();
    Coord last = part.getLast().get();
    assertEquals(instance.getCoordinates().get(9), first);
    assertEquals(instance.getCoordinates().get(0), last);
    assertEquals(10, part.getCoordinates().size());
  }

  /**
   * This tests the clip by distance function.
   */
  @Test
  public void testBefore() {
    System.out.println("Test before");
    Part instance = new Part();
    for (int i = 0; i < 10; i++) {
      CoordXY coord = new CoordXY(i, 2 * i); // Each segment is length 5
      instance.getCoordinates().add(coord);
    }
    Coord before = instance.before(new CoordXY(5.5, 11));
    assertEquals(new CoordXY(5, 10), before);
  }

  /**
   * This tests the clip by distance function.
   */
  @Test
  public void testBeforeBegin() {
    System.out.println("Test before, before beginning");
    Part instance = new Part();
    for (int i = 0; i < 10; i++) {
      CoordXY coord = new CoordXY(i, 2 * i); // Each segment is length 5
      instance.getCoordinates().add(coord);
    }
    Coord before = instance.before(new CoordXY(-1, -2));
    assertEquals(new CoordXY(0, 0), before);
  }

  /**
   * This tests the clip by distance function.
   */
  @Test
  public void testBeforeEnd() {
    System.out.println("Test before, after end");
    Part instance = new Part();
    for (int i = 0; i < 10; i++) {
      CoordXY coord = new CoordXY(i, 2 * i); // Each segment is length 5
      instance.getCoordinates().add(coord);
    }
    Coord before = instance.before(new CoordXY(12, 24));
    assertEquals(new CoordXY(9, 18), before);
  }

}
