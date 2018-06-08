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
package gov.ca.water.shapelite.analysis;

import com.vividsolutions.jts.geom.Coordinate;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Segment;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.compare.PartTopoComparer;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.segment.ReversableSegment;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TriangleMergerTest {

  /**
   * A comparer to use to compare the output part lists.
   */
  private final PartTopoComparer comparer;

  /**
   * Creates a new instance of the TriangleMergerTest class.
   */
  public TriangleMergerTest() {
    comparer = new PartTopoComparer();
  }

  /**
   * Test of mergeTriangles method, of class ContourCreator.
   */
  @Test
  public final void testMergeOneSide() {
    Coordinate a = new Coordinate(0, 0, 0);
    Coordinate b = new Coordinate(10, 10, 0);
    Coordinate c = new Coordinate(10, 0, 0);
    Coordinate d = new Coordinate(20, 10, 0);

    ContourTriangle abc = new ContourTriangle(a, b, c, -99, 0);
    ContourTriangle bdc = new ContourTriangle(d, b, c, -99, 0);

    List<ContourTriangle> onLevel = new ArrayList<>();
    onLevel.add(abc);
    onLevel.add(bdc);
    TriangleMerger merger = new TriangleMerger();
    Optional<Shape> optionalShape = merger.merge(onLevel);
    if (!optionalShape.isPresent()) {
      fail("Was expecting a shape, but got empty.");
    }
    boolean expResult = true;
    boolean result = new CoordZ(c).equals(
        optionalShape.get().getCoordinates().get(0));
    assertEquals(expResult, result);
    result = new CoordZ(a).equals(optionalShape.get().getCoordinates().get(1));
    assertEquals(expResult, result);
    result = new CoordZ(b).equals(optionalShape.get().getCoordinates().get(2));
    assertEquals(expResult, result);
    result = new CoordZ(d).equals(optionalShape.get().getCoordinates().get(3));
    assertEquals(expResult, result);
  }

  /**
   * Test of mergeTriangles method, of class ContourCreator.
   */
  @Test
  public final void testMergeArrayOneSide() {
    Coordinate a = new Coordinate(0, 0, 0);
    Coordinate b = new Coordinate(10, 10, 0);
    Coordinate c = new Coordinate(10, 0, 0);
    Coordinate d = new Coordinate(20, 10, 0);

    ContourTriangle abc = new ContourTriangle(a, b, c, -99, 0);
    ContourTriangle bdc = new ContourTriangle(d, b, c, -99, 0);
    TriangleMerger merger = new TriangleMerger();
    Optional<Shape> optionalShape = merger.merge(
        new ContourTriangle[]{abc, bdc});
    if (!optionalShape.isPresent()) {
      fail("Was expecting a shape, but got empty.");
    }
    boolean expResult = true;
    boolean result = new CoordZ(c).equals(
        optionalShape.get().getCoordinates().get(0));
    assertEquals(expResult, result);
    result = new CoordZ(a).equals(optionalShape.get().getCoordinates().get(1));
    assertEquals(expResult, result);
    result = new CoordZ(b).equals(optionalShape.get().getCoordinates().get(2));
    assertEquals(expResult, result);
    result = new CoordZ(d).equals(optionalShape.get().getCoordinates().get(3));
    assertEquals(expResult, result);
  }

  /**
   * Test of mergeTriangles method, of class ContourCreator.
   */
  @Test
  public final void testMergeEmpty() {
    TriangleMerger merger = new TriangleMerger();
    Optional<Shape> optionalShape = merger.merge(new ArrayList<ContourTriangle>());
    if (optionalShape.isPresent()) {
      fail("Was expecting a shape, but got empty.");
    }
  }

  /**
   * Test of mergeTriangles method, of class ContourCreator.
   */
  @Test
  public final void testAppendNull() {
    TriangleMerger merger = new TriangleMerger();
    Optional<Shape> optionalShape = merger.append(null);
    if (optionalShape.isPresent()) {
      fail("Was expecting a shape, but got empty.");
    }
  }

  /**
   * Tests the situation where a triangle will match two segments, but the two
   * segments belong to separate parts.
   */
  @Test
  public final void testTwoNo() {
    Coord topLeft = new CoordZ(0, 10, 0);
    Coord midLeft = new CoordZ(0, 5, 0);
    Coord bottomLeft = new CoordZ(0, 0, 0);
    Coord topRight = new CoordZ(10, 10, 0);
    Coord bottomRight = new CoordZ(10, 0, 0);
    ContourTriangle triangle1 = new ContourTriangle(topLeft, midLeft, topRight);
    ContourTriangle triangle2 = new ContourTriangle(midLeft, bottomLeft, bottomRight);
    ContourTriangle triangle3 = new ContourTriangle(topRight, midLeft, bottomRight);
    List<ContourTriangle> onLevel = new ArrayList<>();
    onLevel.add(triangle1);
    onLevel.add(triangle2);
    onLevel.add(triangle3);
    TriangleMerger merger = new TriangleMerger();
    Optional<Shape> optionalShape = merger.merge(onLevel);
    if (!optionalShape.isPresent()) {
      fail("Was expecting a shape, but got empty.");
    }
    boolean expResult = true;
    boolean result = midLeft.equals(optionalShape.get().getCoordinates().get(0));
    assertEquals(expResult, result);
    result = bottomLeft.equals(optionalShape.get().getCoordinates().get(1));
    assertEquals(expResult, result);
    result = bottomRight.equals(optionalShape.get().getCoordinates().get(2));
    assertEquals(expResult, result);
    result = topRight.equals(optionalShape.get().getCoordinates().get(3));
    assertEquals(expResult, result);
    result = topLeft.equals(optionalShape.get().getCoordinates().get(4));
    assertEquals(expResult, result);
  }

  /**
   * Tests the situation where a triangle will match two segments, but the two
   * segments belong to separate parts.
   */
  @Test
  public final void testTwoSeparateTriangles() {
    Coord a = new CoordZ(0, 10, 0);
    Coord b = new CoordZ(0, 5, 0);
    Coord c = new CoordZ(0, 0, 0);
    Coord d = new CoordZ(10, 10, 0);
    Coord e = new CoordZ(10, 0, 0);
    Coord f = new CoordZ(20, 7, 0);
    ContourTriangle triangle1 = new ContourTriangle(a, b, c);
    ContourTriangle triangle2 = new ContourTriangle(d, e, f);
    TriangleMerger merger = new TriangleMerger();
    merger.append(triangle1);
    Optional<Shape> result = merger.append(triangle2);
    int expSize = 2;
    assertEquals(expSize, result.get().getParts().size());
    Part triPart1 = triangle1.toPart();
    Part triPart2 = triangle2.toPart();
    PartTopoComparer comp = new PartTopoComparer();
    boolean expResult = true;
    for (ReversableSegment seg : triangle1.getSegments()) {
      Part part = merger.getParts().get(seg).get(0);
      assertEquals(expResult, comp.equivalent(part, triPart1));
    }
    for (ReversableSegment seg : triangle2.getSegments()) {
      Part part = merger.getParts().get(seg).get(0);
      assertEquals(expResult, comp.equivalent(part, triPart2));
    }
    boolean stop = true;

  }

  /**
   * Tests the situation where a triangle will match two segments in a single
   * existing first.
   */
  @Test
  public final void testTwoSidesSamePartEndpoint() {
    Coord wayLeft = new CoordZ(-10, 5, 0);
    Coord topLeft = new CoordZ(0, 10, 0);
    Coord midLeft = new CoordZ(0, 5, 0);
    Coord bottomLeft = new CoordZ(0, 0, 0);
    Coord topRight = new CoordZ(10, 10, 0);
    Coord bottomRight = new CoordZ(10, 0, 0);
    ContourTriangle triangle1 = new ContourTriangle(wayLeft, midLeft, topLeft);
    ContourTriangle triangle2 = new ContourTriangle(wayLeft, midLeft, bottomLeft);
    ContourTriangle triangle3 = new ContourTriangle(topLeft, topRight, midLeft);
    ContourTriangle triangle4 = new ContourTriangle(midLeft, bottomRight, bottomLeft);
    ContourTriangle triangle5 = new ContourTriangle(topRight, midLeft, bottomRight);

    List<ContourTriangle> onLevel = new ArrayList<>();
    onLevel.add(triangle1);
    onLevel.add(triangle2);
    onLevel.add(triangle3);
    onLevel.add(triangle4);
    onLevel.add(triangle5);
    TriangleMerger merger = new TriangleMerger();
    Optional<Shape> optionalShape = merger.merge(onLevel);
    if (!optionalShape.isPresent()) {
      fail("Was expecting a shape, but got empty.");
    }
    int expPartSize = 1;
    assertEquals(expPartSize, optionalShape.get().getParts().size());

    Part expPart = new Part();
    expPart.setClosed(true);
    expPart.getCoordinates().add(topRight);
    expPart.getCoordinates().add(topLeft);
    expPart.getCoordinates().add(wayLeft);
    expPart.getCoordinates().add(bottomLeft);
    expPart.getCoordinates().add(bottomRight);
    boolean expResult = true;
    PartTopoComparer comp = new PartTopoComparer();
    boolean result = comp.equivalent(expPart, optionalShape.get().getParts().get(0));
    assertEquals(expResult, result);
  }

  /**
   * Tests the situation where a triangle will match two segments in a single
   * existing first.
   */
  @Test
  public final void testTwoSidesSamePartMidpoint1() {
    Coord wayLeft = new CoordZ(-10, 5, 0);
    Coord topLeft = new CoordZ(0, 10, 0);
    Coord midLeft = new CoordZ(0, 5, 0);
    Coord bottomLeft = new CoordZ(0, 0, 0);
    Coord topRight = new CoordZ(10, 10, 0);
    Coord bottomRight = new CoordZ(10, 0, 0);
    ContourTriangle triangle3 = new ContourTriangle(topLeft, topRight, midLeft);
    ContourTriangle triangle1 = new ContourTriangle(wayLeft, midLeft, topLeft);
    ContourTriangle triangle2 = new ContourTriangle(wayLeft, midLeft, bottomLeft);
    ContourTriangle triangle4 = new ContourTriangle(midLeft, bottomRight, bottomLeft);
    ContourTriangle triangle5 = new ContourTriangle(topRight, midLeft, bottomRight);

    List<ContourTriangle> onLevel = new ArrayList<>();
    onLevel.add(triangle1);
    onLevel.add(triangle2);
    onLevel.add(triangle3);
    onLevel.add(triangle4);
    onLevel.add(triangle5);
    TriangleMerger merger = new TriangleMerger();
    Optional<Shape> optionalShape = merger.merge(onLevel);
    if (!optionalShape.isPresent()) {
      fail("Was expecting a shape, but got empty.");
    }
    boolean expResult = true;
    boolean result = bottomRight.equals(optionalShape.get().getCoordinates().get(0));
    assertEquals(expResult, result);
    result = topRight.equals(optionalShape.get().getCoordinates().get(1));
    assertEquals(expResult, result);
    result = topLeft.equals(optionalShape.get().getCoordinates().get(2));
    assertEquals(expResult, result);
    result = wayLeft.equals(optionalShape.get().getCoordinates().get(3));
    assertEquals(expResult, result);
    result = bottomLeft.equals(optionalShape.get().getCoordinates().get(4));
    assertEquals(expResult, result);

  }

  /**
   * Tests the situation where a triangle will match two segments in the
   * existing first.
   */
  @Test
  public final void mergeeThreeSidesSeparate() {
    Coord topLeft = new CoordZ(0, 10, 0);
    Coord midLeft = new CoordZ(0, 5, 0);
    Coord bottomLeft = new CoordZ(0, 0, 0);
    Coord topCenter = new CoordZ(10, 10, 0);
    Coord bottomCenter = new CoordZ(10, 0, 0);
    Coord midRight = new CoordZ(20, 5, 0);
    ContourTriangle triangle1 = new ContourTriangle(topLeft, midLeft, topCenter);
    ContourTriangle triangle2 = new ContourTriangle(midLeft, bottomLeft, bottomCenter);
    ContourTriangle triangle3 = new ContourTriangle(topCenter, midRight, bottomCenter);
    ContourTriangle triangle4 = new ContourTriangle(topCenter, midLeft, bottomCenter);
    List<ContourTriangle> onLevel = new ArrayList<>();
    onLevel.add(triangle1);
    onLevel.add(triangle2);
    onLevel.add(triangle3);
    onLevel.add(triangle4);
    TriangleMerger merger = new TriangleMerger();
    Optional<Shape> optionalShape = merger.merge(onLevel);
    if (!optionalShape.isPresent()) {
      fail("Was expecting a shape, but got empty.");
    }
    boolean expResult = true;
    boolean result = topCenter.equals(optionalShape.get().getCoordinates().get(0));
    assertEquals(expResult, result);
    result = midRight.equals(optionalShape.get().getCoordinates().get(1));
    assertEquals(expResult, result);
    result = bottomCenter.equals(optionalShape.get().getCoordinates().get(2));
    assertEquals(expResult, result);
    result = bottomLeft.equals(optionalShape.get().getCoordinates().get(3));
    assertEquals(expResult, result);
    result = midLeft.equals(optionalShape.get().getCoordinates().get(4));
    assertEquals(expResult, result);
    result = topLeft.equals(optionalShape.get().getCoordinates().get(5));
    assertEquals(expResult, result);
  }

  /**
   * The strategy used by the merger depends on the hash code matching reversed
   * segments as well as normal segments.
   */
  @Test
  public final void testReversableHashcode() {
    CoordZ a = new CoordZ(0, 0, 0);
    CoordZ b = new CoordZ(10, 10, 0);
    Segment forward = new ReversableSegment(a, b);
    Segment copy = forward.copy();
    Segment reverse = new ReversableSegment(a.copy(), b.copy());
    int expResult = forward.hashCode();
    int result = reverse.hashCode();
    assertEquals(expResult, result);
    result = copy.hashCode();
    assertEquals(expResult, result);
  }

  /**
   * The strategy used by the merger depends on the hash code matching reversed
   * segments as well as normal segments.
   */
  @Test
  public final void testReversableEquals() {
    CoordZ a = new CoordZ(0, 0, 0);
    CoordZ b = new CoordZ(10, 10, 0);
    Segment forward = new ReversableSegment(a, b);
    Segment copy = forward.copy();
    Segment reverse = new ReversableSegment(a.copy(), b.copy());
    boolean expResult = true;
    boolean result = reverse.equals(forward);
    assertEquals(expResult, result);
    result = copy.equals(reverse);
    assertEquals(expResult, result);
  }

  /**
   * Test of mergeTriangles method, of class ContourCreator.
   */
  @Test
  public final void testMergeNull() {
    List<ContourTriangle> onLevel = null;
    TriangleMerger merger = new TriangleMerger();
    Optional<Shape> optionalShape = merger.merge(onLevel);
    if (optionalShape.isPresent()) {
      fail("Was expecting empty, but got something.");
    }

  }

  /**
   * Tests that the first is added successfully to the parts list.
   */
  @Test
  public final void testAddPart() {
    Part part = new Part();
    part.setClosed(true);
    part.getCoordinates().add(new CoordZ(0, 0, 0));
    part.getCoordinates().add(new CoordZ(0, 10, 0));
    part.getCoordinates().add(new CoordZ(0, 0, 10));
    TriangleMerger instance = new TriangleMerger();
    instance.addPart(part);
    int expResult = 3;
    int result = instance.getParts().size();
    assertEquals(expResult, result);
    for (ReversableSegment seg : part.getReversableSegments()) {
      if (!instance.getParts().containsKey(seg)) {
        fail("Expected to parts to contain " + seg + " key but it was missing.");
      }
    }
  }

  /**
   * Tests that the first is added successfully to the parts list.
   */
  @Test
  public final void testAddPartTwice() {
    Part part = new Part();
    part.setClosed(true);
    part.getCoordinates().add(new CoordZ(0, 0, 0));
    part.getCoordinates().add(new CoordZ(0, 10, 0));
    part.getCoordinates().add(new CoordZ(0, 0, 10));
    TriangleMerger instance = new TriangleMerger();
    instance.addPart(part);
    Part part2 = part.copy();
    instance.addPart(part2);
    int expResult = 3;
    int result = instance.getParts().size();
    assertEquals(expResult, result);
    for (ReversableSegment seg : part.getReversableSegments()) {
      if (!instance.getParts().containsKey(seg)) {
        fail("Expected to parts to contain " + seg + " key but it was missing.");
      }
      List<Part> parts = instance.getParts().get(seg);
      assertEquals(2, parts.size());
      boolean expContains = true;
      assertEquals(expContains, parts.contains(part));
      assertEquals(expContains, parts.contains(part2));
    }
  }

  /**
   * Tests that the first is successfully removed from the parts list.
   */
  @Test
  public final void testAddRemovePart() {
    Part part = new Part();
    part.setClosed(true);
    part.getCoordinates().add(new CoordZ(0, 0, 0));
    part.getCoordinates().add(new CoordZ(0, 10, 0));
    part.getCoordinates().add(new CoordZ(0, 0, 10));
    TriangleMerger instance = new TriangleMerger();
    instance.addPart(part);
    int expResult = 3;
    int result = instance.getParts().size();
    assertEquals(expResult, result);
    for (ReversableSegment seg : part.getReversableSegments()) {
      if (!instance.getParts().containsKey(seg)) {
        fail("Expected to parts to contain " + seg + " key but it was missing.");
      }
    }
    instance.removePart(part);
    expResult = 0;
    result = instance.getParts().size();
    assertEquals(expResult, result);
  }

  /**
   * Tests that the first is successfully removed from the parts list.
   */
  @Test
  public final void testAddRemoveDifferentPart() {
    Part part = new Part();
    part.setClosed(true);
    part.getCoordinates().add(new CoordZ(0, 0, 0));
    part.getCoordinates().add(new CoordZ(0, 10, 0));
    part.getCoordinates().add(new CoordZ(0, 0, 10));

    Part part2 = new Part();
    part2.setClosed(true);
    part2.getCoordinates().add(new CoordZ(5, 5, 0));
    part2.getCoordinates().add(new CoordZ(8, 10, 0));
    part2.getCoordinates().add(new CoordZ(4, 1, 10));
    TriangleMerger instance = new TriangleMerger();
    instance.addPart(part);
    int expResult = 3;
    int result = instance.getParts().size();
    assertEquals(expResult, result);
    for (ReversableSegment seg : part.getReversableSegments()) {
      if (!instance.getParts().containsKey(seg)) {
        fail("Expected to parts to contain " + seg + " key but it was missing.");
      }
      assertEquals(instance.getParts().get(seg).get(0), part);
    }
    instance.removePart(part2);
    expResult = 3;
    result = instance.getParts().size();
    assertEquals(expResult, result);
    for (ReversableSegment seg : part.getReversableSegments()) {
      if (!instance.getParts().containsKey(seg)) {
        fail("Expected to parts to contain " + seg + " key but it was missing.");
      }
      assertEquals(instance.getParts().get(seg).get(0), part);
    }
  }

  /**
   * Tests that the first is successfully removed from the parts list.
   */
  @Test
  public final void testAddTwiceRemovePart() {
    Part part = new Part();
    part.setClosed(true);
    part.getCoordinates().add(new CoordZ(0, 0, 0));
    part.getCoordinates().add(new CoordZ(0, 10, 0));
    part.getCoordinates().add(new CoordZ(0, 0, 10));
    TriangleMerger instance = new TriangleMerger();
    instance.addPart(part);
    Part part2 = part.copy();
    instance.addPart(part2);
    int expResult = 3;
    int result = instance.getParts().size();
    assertEquals(expResult, result);
    for (ReversableSegment seg : part.getReversableSegments()) {
      if (!instance.getParts().containsKey(seg)) {
        fail("Expected to parts to contain " + seg + " key but it was missing.");
      }
      boolean expContains = true;
      boolean contains = instance.getParts().get(seg).contains(part);
      assertEquals(expContains, contains);
    }
    instance.removePart(part);
    expResult = 3;
    result = instance.getParts().size();
    assertEquals(expResult, result);
    for (ReversableSegment seg : part.getReversableSegments()) {
      if (!instance.getParts().containsKey(seg)) {
        fail("Expected to parts to contain " + seg + " key but it was missing.");
      }
      boolean expContains = true;
      boolean contains = instance.getParts().get(seg).contains(part2);
      assertEquals(expContains, contains);
    }

  }

  /**
   * Tests that the first is successfully removed from the parts list.
   */
  @Test
  public final void testRemoveUncontainedPart() {
    Part part = new Part();
    part.setClosed(true);
    part.getCoordinates().add(new CoordZ(0, 0, 0));
    part.getCoordinates().add(new CoordZ(0, 10, 0));
    part.getCoordinates().add(new CoordZ(0, 0, 10));
    TriangleMerger instance = new TriangleMerger();
    instance.addPart(part);
    Part part2 = part.copy();
    int expResult = 3;
    int result = instance.getParts().size();
    assertEquals(expResult, result);
    for (ReversableSegment seg : part.getReversableSegments()) {
      if (!instance.getParts().containsKey(seg)) {
        fail("Expected to parts to contain " + seg + " key but it was missing.");
      }
    }
    instance.removePart(part2);
    assertEquals(expResult, result);
    for (ReversableSegment seg : part.getReversableSegments()) {
      if (!instance.getParts().containsKey(seg)) {
        fail("Expected to parts to contain " + seg + " key but it was missing.");
      }
      Part test = instance.getParts().get(seg).get(0);
      assertEquals(test, part);
    }
  }

  /**
   * Test of areSequential method, of class TriangleMerger.
   */
  @Test
  public final void testAreSequential() {
    System.out.println("areSequential");

    // not even different numbers
    int ia = 0;
    int ib = 0;
    int ic = 0;
    TriangleMerger instance = new TriangleMerger();
    boolean expResult = false;
    boolean result = instance.areSequential(ia, ib, ic);
    assertEquals(expResult, result);

    // in order
    ia = 1;
    ib = 2;
    ic = 3;
    expResult = true;
    result = instance.areSequential(ia, ib, ic);
    assertEquals(expResult, result);

    // out of order
    ia = 1;
    ib = 3;
    ic = 2;
    expResult = true;
    result = instance.areSequential(ia, ib, ic);
    assertEquals(expResult, result);
  }

  /**
   * Test of toList method, of class TriangleMerger.
   */
  @Test
  public final void testToList() {
    System.out.println("toList");
    TriangleMerger instance = new TriangleMerger();
    List<Integer> expResult = new ArrayList<>();
    expResult.add(0);
    expResult.add(1);
    expResult.add(2);
    // parameter call
    List<Integer> result = instance.toList(0, 1, 2);
    assertEquals(expResult, result);

    // Array call
    result = instance.toList(new int[]{0, 1, 2});
    assertEquals(expResult, result);

  }

  /**
   * This is a support method for testing functions that contain a
   * PartSegmentJoint. This returns the middle segment so that the joint can be
   * useful for the most tests.
   *
   * @return PartSegmentJoint.
   */
  final PartSegmentJoint getTestJoint() {
    PartSegmentJoint result;
    Part part = new Part();
    part.setClosed(true);
    CoordZ a = new CoordZ(0, 0, 0);
    CoordZ b = new CoordZ(10, 0, 0);
    CoordZ c = new CoordZ(0, 10, 0);
    CoordZ d = new CoordZ(-10, 0, 0);
    part.getCoordinates().add(a);
    part.getCoordinates().add(b);
    part.getCoordinates().add(c);
    part.getCoordinates().add(d);
    ReversableSegment seg = new ReversableSegment(b, c);
    result = new PartSegmentJoint(part, seg, 1);
    return result;
  }

  /**
   * Test of union method, of class TriangleMerger.
   */
  @Test
  public final void testUnion() {
    CoordZ a = new CoordZ(0, 0, 0);
    CoordZ b = new CoordZ(10, 0, 0);
    CoordZ c = new CoordZ(0, 10, 0);
    CoordZ d = new CoordZ(-10, 0, 0);

    Part core = new Part();
    core.setClosed(true);
    CoordZ e = new CoordZ(0, 10, 0);
    CoordZ f = new CoordZ(10, 0, 0);
    CoordZ g = new CoordZ(10, 10, 0);
    core.getCoordinates().add(e);
    core.getCoordinates().add(f);
    core.getCoordinates().add(g);

    PartSegmentJoint joint = getTestJoint();
    TriangleMerger instance = new TriangleMerger();
    Part union = instance.union(joint, core);
    int expSize = 5;
    assertEquals(expSize, union.getCoordinates().size());
    boolean expResult = true;
    boolean result = union.getCoordinates().get(0).equals(c);
    assertEquals(expResult, result);
    result = union.getCoordinates().get(1).equals(d);
    assertEquals(expResult, result);
    result = union.getCoordinates().get(2).equals(a);
    assertEquals(expResult, result);
    result = union.getCoordinates().get(3).equals(b);
    assertEquals(expResult, result);
    result = union.getCoordinates().get(4).equals(g);
    assertEquals(expResult, result);
  }

  /**
   * Test of replaceSegmentWithPart method, of class TriangleMerger.
   */
  @Test
  public final void testReplaceSegmentWithPart() {
    CoordZ a = new CoordZ(0, 0, 0);
    CoordZ b = new CoordZ(10, 0, 0);
    CoordZ c = new CoordZ(0, 10, 0);
    CoordZ d = new CoordZ(-10, 0, 0);

    Part core = new Part();
    core.setClosed(true);
    CoordZ e = new CoordZ(0, 10, 0);
    CoordZ f = new CoordZ(10, 0, 0);
    CoordZ g = new CoordZ(10, 10, 0);
    core.getCoordinates().add(e);
    core.getCoordinates().add(f);
    core.getCoordinates().add(g);

    PartSegmentJoint joint = getTestJoint();
    TriangleMerger instance = new TriangleMerger();
    instance.addPart(joint.getPart());
    Part union = instance.replaceSegmentWithPart(joint, core);
    int expSize = 5;
    assertEquals(expSize, union.getCoordinates().size());
    boolean expResult = true;
    boolean result = union.getCoordinates().get(0).equals(c);
    assertEquals(expResult, result);
    result = union.getCoordinates().get(1).equals(d);
    assertEquals(expResult, result);
    result = union.getCoordinates().get(2).equals(a);
    assertEquals(expResult, result);
    result = union.getCoordinates().get(3).equals(b);
    assertEquals(expResult, result);
    result = union.getCoordinates().get(4).equals(g);
    assertEquals(expResult, result);

    // the main difference between this test and union is that
    // this method should also remove the original first.
    expSize = 0;
    assertEquals(expSize, instance.getParts().size());

  }

  /**
   * This tests the case where a single first actually touches all three sides
   * of a triangle.
   */
  @Test
  public final void testThreeSidesAllSamePartSequential() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    Coord f = new CoordXY(30, 50);
    Coord g = new CoordXY(20, 50);
    Coord h = new CoordXY(50, 40);
    Coord i = new CoordXY(20, 20);
    Part part = new Part();
    part.setClosed(true);
    part.getCoordinates().add(a);
    part.getCoordinates().add(b);
    part.getCoordinates().add(c);
    part.getCoordinates().add(d);
    part.getCoordinates().add(e);
    part.getCoordinates().add(f);
    part.getCoordinates().add(g);
    part.getCoordinates().add(h);
    part.getCoordinates().add(i);
    part.getCoordinates().add(c);
    part.getCoordinates().add(h);

    TriangleMerger instance = new TriangleMerger();
    instance.addPart(part);
    ContourTriangle test = new ContourTriangle(c, i, h);

    Optional<Shape> output = instance.append(test);
    if (!output.isPresent()) {
      fail("Expected output.");
    }
    Coord[] expCoord = new Coord[]{a, b, c, d, e, f, g, h};
    List<Coord> coords = output.get().getParts().get(0).getCoordinates();
    int expSize = 8;
    assertEquals(expSize, coords.size());
    boolean expResult = true;
    for (int index = 0; index < coords.size(); index++) {
      boolean result = expCoord[index].equals(coords.get(index));
      assertEquals(expResult, result);
    }

  }

  /**
   * This tests the case where a single first actually touches all three sides
   * of a triangle.
   */
  @Test
  public final void testThreeSidesAllSamePart() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    Coord f = new CoordXY(30, 50);
    Coord g = new CoordXY(20, 50);
    Coord h = new CoordXY(50, 40);
    Coord i = new CoordXY(20, 20);
    Part part = new Part();
    part.setClosed(true);
    part.getCoordinates().add(a);
    part.getCoordinates().add(b);
    part.getCoordinates().add(c);
    part.getCoordinates().add(d);
    part.getCoordinates().add(e);
    part.getCoordinates().add(f);
    part.getCoordinates().add(g);
    part.getCoordinates().add(h);
    part.getCoordinates().add(i);
    part.getCoordinates().add(c);
    part.getCoordinates().add(h);

    TriangleMerger instance = new TriangleMerger();
    instance.addPart(part);
    ContourTriangle test = new ContourTriangle(c, i, h);

    Optional<Shape> output = instance.append(test);
    if (!output.isPresent()) {
      fail("Expected output.");
    }
    Coord[] expCoord = new Coord[]{a, b, c, d, e, f, g, h};
    List<Coord> coords = output.get().getParts().get(0).getCoordinates();
    int expSize = 8;
    assertEquals(expSize, coords.size());
    boolean expResult = true;
    for (int index = 0; index < coords.size(); index++) {
      boolean result = expCoord[index].equals(coords.get(index));
      assertEquals(expResult, result);
    }

  }

  /**
   * This tests the case where three sides are found, and two sides are the same
   * first, but the third first is separate.
   */
  @Test
  public final void testThreeSidesTwoPartsCIH() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    Coord f = new CoordXY(30, 50);
    Coord g = new CoordXY(20, 50);
    Coord h = new CoordXY(20, 40);
    Coord i = new CoordXY(20, 20);
    Part first = new Part();
    first.setClosed(true);
    first.getCoordinates().add(a);
    first.getCoordinates().add(b);
    first.getCoordinates().add(c);
    first.getCoordinates().add(h);

    Part second = new Part();
    second.setClosed(true);
    second.getCoordinates().add(c);
    second.getCoordinates().add(d);
    second.getCoordinates().add(e);
    second.getCoordinates().add(f);
    second.getCoordinates().add(g);
    second.getCoordinates().add(h);
    second.getCoordinates().add(i);

    TriangleMerger instance = new TriangleMerger();
    instance.addPart(first);
    instance.addPart(second);
    ContourTriangle test = new ContourTriangle(c, i, h);

    Optional<Shape> output = instance.append(test);
    if (!output.isPresent()) {
      fail("Expected output.");
    }
    Coord[] expCoord = new Coord[]{h, a, b, c, d, e, f, g};
    List<Coord> coords = output.get().getParts().get(0).getCoordinates();
    int expSize = 8;
    assertEquals(expSize, coords.size());
    boolean expResult = true;
    for (int index = 0; index < coords.size(); index++) {
      boolean result = expCoord[index].equals(coords.get(index));
      assertEquals(expResult, result);
    }

  }

  /**
   * This tests the case where three sides are found, and two sides are the same
   * first, but the third first is separate.
   */
  @Test
  public final void testThreeSidesTwoPartsIHC() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    Coord f = new CoordXY(30, 50);
    Coord g = new CoordXY(20, 50);
    Coord h = new CoordXY(20, 40);
    Coord i = new CoordXY(20, 20);
    Part first = new Part();
    first.setClosed(true);
    first.getCoordinates().add(a);
    first.getCoordinates().add(b);
    first.getCoordinates().add(c);
    first.getCoordinates().add(h);

    Part second = new Part();
    second.setClosed(true);
    second.getCoordinates().add(c);
    second.getCoordinates().add(d);
    second.getCoordinates().add(e);
    second.getCoordinates().add(f);
    second.getCoordinates().add(g);
    second.getCoordinates().add(h);
    second.getCoordinates().add(i);

    TriangleMerger instance = new TriangleMerger();
    instance.addPart(first);
    instance.addPart(second);
    ContourTriangle test = new ContourTriangle(i, h, c);

    Optional<Shape> output = instance.append(test);
    if (!output.isPresent()) {
      fail("Expected output.");
    }
    Coord[] expCoord = new Coord[]{h, a, b, c, d, e, f, g};
    List<Coord> coords = output.get().getParts().get(0).getCoordinates();
    int expSize = 8;
    assertEquals(expSize, coords.size());
    boolean expResult = true;
    for (int index = 0; index < coords.size(); index++) {
      boolean result = expCoord[index].equals(coords.get(index));
      assertEquals(expResult, result);
    }

  }

  /**
   * This tests the case where three sides are found, and two sides are the same
   * first, but the third first is separate.
   */
  @Test
  public final void testThreeSidesTwoPartsHCI() {
    Coord a = new CoordXY(0, 50);
    Coord b = new CoordXY(0, 20);
    Coord c = new CoordXY(10, 20);
    Coord d = new CoordXY(10, 10);
    Coord e = new CoordXY(30, 10);
    Coord f = new CoordXY(30, 50);
    Coord g = new CoordXY(20, 50);
    Coord h = new CoordXY(20, 40);
    Coord i = new CoordXY(20, 20);
    Part first = new Part();
    first.setClosed(true);
    first.getCoordinates().add(a);
    first.getCoordinates().add(b);
    first.getCoordinates().add(c);
    first.getCoordinates().add(h);

    Part second = new Part();
    second.setClosed(true);
    second.getCoordinates().add(c);
    second.getCoordinates().add(d);
    second.getCoordinates().add(e);
    second.getCoordinates().add(f);
    second.getCoordinates().add(g);
    second.getCoordinates().add(h);
    second.getCoordinates().add(i);

    TriangleMerger instance = new TriangleMerger();
    instance.addPart(first);
    instance.addPart(second);
    ContourTriangle test = new ContourTriangle(h, c, i);

    Optional<Shape> output = instance.append(test);
    if (!output.isPresent()) {
      fail("Expected output.");
    }
    Coord[] expCoord = new Coord[]{h, a, b, c, d, e, f, g};
    List<Coord> coords = output.get().getParts().get(0).getCoordinates();
    int expSize = 8;
    assertEquals(expSize, coords.size());
    boolean expResult = true;
    for (int index = 0; index < coords.size(); index++) {
      boolean result = expCoord[index].equals(coords.get(index));
      assertEquals(expResult, result);
    }

  }

  /**
   * Tests one case for having a triangle where all three sides are in the
   * existing part.
   * <pre>
   * 30 l------------m
   * -- |            |
   * 25 |    i--h    |
   * -- |   /    \   |
   * 20 k--j      g  |
   * --           | \|
   * 15           |  n
   * --           | /|
   * 10 b--c      f  |
   * -- |   \    /   |
   * 5  |    d--e    |
   * -- |            |
   * 0  a------------o
   * -- 0   10    20
   * --   5   15    25
   * </pre>
   */
  @Test
  public final void testThreeSidesOnePartNonSequential1() {
    Coord a = new CoordXY(0, 0);
    Coord b = new CoordXY(0, 10);
    Coord c = new CoordXY(5, 10);
    Coord d = new CoordXY(10, 5);
    Coord e = new CoordXY(15, 5);
    Coord f = new CoordXY(20, 10);
    Coord g = new CoordXY(20, 20);
    Coord h = new CoordXY(15, 25);
    Coord i = new CoordXY(10, 25);
    Coord j = new CoordXY(5, 20);
    Coord k = new CoordXY(0, 20);
    Coord l = new CoordXY(0, 30);
    Coord m = new CoordXY(25, 30);
    Coord n = new CoordXY(25, 15);
    Coord o = new CoordXY(25, 0);
    Coord[] coords = new Coord[]{
      a, b, c, d, e, f, g, h, i, j, k, l, m, n, g, f, n, o
    };
    Part threeSides = new Part(coords);
    threeSides.setClosed(true);
    TriangleMerger instance = new TriangleMerger();
    instance.addPart(threeSides);
    ContourTriangle test = new ContourTriangle(g, f, n);

    Optional<Shape> output = instance.append(test);

    Coord[] expCoords = new Coord[]{
      a, b, c, d, e, f, g, h, i, j, k, l, m, n, o
    };
    Part expPart = new Part(expCoords);
    expPart.setClosed(true);

    PartTopoComparer comp = new PartTopoComparer();
    boolean expResult = true;
    boolean result = comp.equivalent(expPart, output.get().getParts().get(0));
    assertEquals(expResult, result);

  }

  /**
   * Tests one case for having a triangle where all three sides are in the
   * existing part.
   * <pre>
   * 30 l------------m
   * -- |            |
   * 25 |    i--h    |
   * -- |   /    \   |
   * 20 k--j      g  |
   * --           | \|
   * 15           |  n
   * --           | /|
   * 10 b--c      f  |
   * -- |   \    /   |
   * 5  |    d--e    |
   * -- |            |
   * 0  a------------o
   * -- 0   10    20
   * --   5   15    25
   * </pre>
   */
  @Test
  public final void testThreeSidesOnePartNonSequentialReversed() {
    Coord a = new CoordXY(0, 0);
    Coord b = new CoordXY(0, 10);
    Coord c = new CoordXY(5, 10);
    Coord d = new CoordXY(10, 5);
    Coord e = new CoordXY(15, 5);
    Coord f = new CoordXY(20, 10);
    Coord g = new CoordXY(20, 20);
    Coord h = new CoordXY(15, 25);
    Coord i = new CoordXY(10, 25);
    Coord j = new CoordXY(5, 20);
    Coord k = new CoordXY(0, 20);
    Coord l = new CoordXY(0, 30);
    Coord m = new CoordXY(25, 30);
    Coord n = new CoordXY(25, 15);
    Coord o = new CoordXY(25, 0);
    Coord[] coords = new Coord[]{
      a, o, n, f, g, n, m, l, k, j, i, h, g, f, e, d, c, b
    };
    Part threeSides = new Part(coords);
    threeSides.setClosed(true);
    TriangleMerger instance = new TriangleMerger();
    instance.addPart(threeSides);
    ContourTriangle test = new ContourTriangle(g, f, n);

    Optional<Shape> output = instance.append(test);

    Coord[] expCoords = new Coord[]{
      a, b, c, d, e, f, g, h, i, j, k, l, m, n, o
    };
    Part expPart = new Part(expCoords);
    expPart.setClosed(true);

    PartTopoComparer comp = new PartTopoComparer();
    boolean expResult = true;
    boolean result = comp.equivalent(expPart, output.get().getParts().get(0));
    assertEquals(expResult, result);

  }

  /**
   * Tests one case for having a triangle where all three sides are in the
   * existing part.
   * <pre>
   * 30 m-------------l
   * -- |             |
   * 25 |             |
   * -- |             |
   * 20 |       i  j  |
   * -- |     / | /|  |
   * 15 |    h--g  |  |
   * -- |         \|  |
   * 10 |          d--k
   * -- |         /|\
   * 5  |       f--e  c
   * -- |             |
   * 0  a-------------b
   * -- 0   10    20
   * --   5    15    25
   * </pre>
   */
  @Test
  public final void testThreeSidesOnePartNonSequential2() {
    Coord a = new CoordXY(0, 0);
    Coord b = new CoordXY(25, 0);
    Coord c = new CoordXY(25, 5);
    Coord d = new CoordXY(20, 10);
    Coord e = new CoordXY(20, 5);
    Coord f = new CoordXY(15, 5);
    Coord g = new CoordXY(15, 15);
    Coord h = new CoordXY(10, 25);
    Coord i = new CoordXY(15, 20);
    Coord j = new CoordXY(20, 20);
    Coord k = new CoordXY(25, 10);
    Coord l = new CoordXY(25, 30);
    Coord m = new CoordXY(0, 30);
    Coord[] coords = new Coord[]{
      a, b, c, d, e, f, d, g, h, i, g, j, d, k, l, m
    };
    Part threeSides = new Part(coords);
    threeSides.setClosed(true);
    TriangleMerger instance = new TriangleMerger();
    instance.addPart(threeSides);
    ContourTriangle test = new ContourTriangle(g, j, d);

    Optional<Shape> output = instance.append(test);

    Shape expShape = new Shape(ShapeType.Polygon);
    Coord[] expCoords = new Coord[]{
      d, k, l, m, a, b, c
    };
    Part expPart = new Part(expCoords);
    expPart.setClosed(true);
    expShape.getParts().add(expPart);

    Coord[] expCoords2 = new Coord[]{
      d, e, f
    };
    Part expPart2 = new Part(expCoords2);
    expShape.getParts().add(expPart2);

    Coord[] expCoords3 = new Coord[]{
      h, g, i
    };
    Part expPart3 = new Part(expCoords3);
    expShape.getParts().add(expPart3);

    PartTopoComparer comp = new PartTopoComparer();
    boolean expResult = true;
    boolean result = comp.equivalent(expPart, output.get().getParts().get(0));
    assertEquals(expResult, result);

  }

  /**
   * Tests one case for having a triangle where all three sides are in the
   * existing part.
   * <pre>
   * 30 m-------------l
   * -- |             |
   * 25 |             |
   * -- |             |
   * 20 |       i  j  |
   * -- |     / | /|  |
   * 15 |    h--g  |  |
   * -- |         \|  |
   * 10 |          d--k
   * -- |         /|\
   * 5  |       f--e  c
   * -- |             |
   * 0  a-------------b
   * -- 0   10    20
   * --   5    15    25
   * </pre>
   */
  @Test
  public final void testThreeSidesOnePartNonSequential2Reversed() {
    Coord a = new CoordXY(0, 0);
    Coord b = new CoordXY(25, 0);
    Coord c = new CoordXY(25, 5);
    Coord d = new CoordXY(20, 10);
    Coord e = new CoordXY(20, 5);
    Coord f = new CoordXY(15, 5);
    Coord g = new CoordXY(15, 15);
    Coord h = new CoordXY(10, 25);
    Coord i = new CoordXY(15, 20);
    Coord j = new CoordXY(20, 20);
    Coord k = new CoordXY(25, 10);
    Coord l = new CoordXY(25, 30);
    Coord m = new CoordXY(0, 30);
    Coord[] coords = new Coord[]{
      a, m, l, k, d, j, g, i, h, g, d, f, e, d, c, b
    };
    Part threeSides = new Part(coords);
    threeSides.setClosed(true);
    TriangleMerger instance = new TriangleMerger();
    instance.addPart(threeSides);
    ContourTriangle test = new ContourTriangle(g, j, d);

    Optional<Shape> output = instance.append(test);

    Shape expShape = new Shape(ShapeType.Polygon);
    Coord[] expCoords = new Coord[]{
      d, k, l, m, a, b, c
    };
    Part expPart = new Part(expCoords);
    expPart.setClosed(true);
    expShape.getParts().add(expPart);

    Coord[] expCoords2 = new Coord[]{
      d, e, f
    };
    Part expPart2 = new Part(expCoords2);
    expShape.getParts().add(expPart2);

    Coord[] expCoords3 = new Coord[]{
      h, g, i
    };
    Part expPart3 = new Part(expCoords3);
    expShape.getParts().add(expPart3);

    PartTopoComparer comp = new PartTopoComparer();
    boolean expResult = true;
    boolean result = comp.equivalent(expPart, output.get().getParts().get(0));
    assertEquals(expResult, result);

  }

}
