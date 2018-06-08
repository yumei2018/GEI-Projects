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

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Nullable;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Segment;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.coordinate.CoordFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * This class was created because the JTS buffer operation frequently failed,
 * despite our best efforts.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TriangleMerger {

  /**
   * One Side matches an existing part.
   */
  private static final int ONE_SIDE = 1;

  /**
   * Two sides match existing combined.
   */
  private static final int TWO_SIDES = 2;

  /**
   * Three sides match existing combined.
   */
  private static final int THREE_SIDES = 3;

  /**
   * The hash map has a list of combined, rather than a part, because the hash
   * map might not be perfect at keeping things distinctParts and so two
   * different segments might hash to the same integer value.
   */
  private final HashMap<Segment, List<Part>> parts;

  /**
   * A cycler to use for iterating through coordinate lists and combined.
   */
  private final PartCycler cycler;

  /**
   * Creates a new instance of a triangle merger utility for merging contour
   * triangles.
   */
  public TriangleMerger() {
    parts = new HashMap<>();
    cycler = new PartCycler();
  }

  /**
   * Merges the specified triangles into an optional shape. If the triangles
   * list is empty, this returns empty.
   *
   * @param triangles The array or separately specified triangle objects to join
   * into a multipart polygon.
   * @return An optional polygon that may have multiple combined. If the
   * triangle lists is null, this returns empty.
   */
  public final Optional<Shape> merge(ContourTriangle... triangles) {
    return merge(Arrays.asList(triangles));
  }

  /**
   * Merges the specified triangles into an optional shape. If the triangles
   * list is empty, this returns empty.
   *
   * @param triangles The list of triangles to join into a multipart polygon.
   * @return An optional polygon that may have multiple combined. If the
   * triangle lists is null, this returns empty.
   */
  public final Optional<Shape> merge(@Nullable List<ContourTriangle> triangles) {
    parts.clear();
    if (triangles == null) {
      return Optional.empty();
    }
    for (ContourTriangle triangle : triangles) {
      mergeTriangle(triangle);
    }
    if (parts.isEmpty()) {
      return Optional.empty();
    }

    Shape result = new Shape(ShapeType.PolygonZ);
    HashSet<Part> distinctParts = new HashSet<>();

    // Parts use regular object equals and object hashmap implementations.
    for (List<Part> matches : parts.values()) {
      for (Part part : matches) {
        if (!distinctParts.contains(part)) {
          result.getParts().add(part);
          distinctParts.add(part);
        }
      }
    }
    return Optional.of(result);
  }

  /**
   * Merges the specified triangles into an optional shape. If the triangles
   * list is empty, this returns empty.
   *
   * @param triangle The triangle to join into any existing part.
   * @return An optional polygon that may have multiple combined. If the
   * triangle lists is null, this returns empty.
   */
  final Optional<Shape> append(@Nullable ContourTriangle triangle) {
    if (triangle == null) {
      return Optional.empty();
    }
    mergeTriangle(triangle);
    Coord a = CoordFactory.get(triangle.getA());
    Shape result = new Shape(ShapeType.getPolygon(a.hasM(), a.hasZ()));
    HashSet<Part> distinctParts = new HashSet<>();

    // Parts use regular object equals and object hashmap implementations.
    for (List<Part> matches : parts.values()) {
      for (Part part : matches) {
        if (!distinctParts.contains(part)) {
          result.getParts().add(part);
          distinctParts.add(part);
        }
      }
    }
    return Optional.of(result);
  }

  /**
   * This method will merge an individual triangle to the existing part setup.
   *
   * @param triangle The triangle to merge.
   * @throws UnsupportedOperationException In special cases where the code has
   * not yet been written to handle that case.
   */
  final void mergeTriangle(ContourTriangle triangle)
      throws UnsupportedOperationException {
    List<PartSegmentJoint> foundSegments = new ArrayList<>();

    for (Segment seg : triangle.getSegments()) {
      if (parts.containsKey(seg)) {
        List<Part> possibleMatches = parts.get(seg);
        boolean found = false;
        for (Part part : possibleMatches) {
          int iSeg = 0;
          for (Segment partSeg : part.getReversableSegments()) {
            if (partSeg.equals(seg) || partSeg.equalsReversed(seg)) {
              PartSegmentJoint joint = new PartSegmentJoint(
                  part, partSeg, iSeg);
              foundSegments.add(joint);
              found = true;
              break;
            }
            iSeg++;
          }
          if (found) {
            break;
          }
        }
      }
    }

    // If not found, add the triangle.
    Part trianglePart = triangle.toPart();
    if (foundSegments.isEmpty()) {
      for (Segment seg : triangle.getSegments()) {
        List<Part> possibleMatches = new ArrayList<>();
        possibleMatches.add(trianglePart);
        parts.put(seg, possibleMatches);
      }
    }
    // If 1 segment found, grow the part to show the other two segments.
    if (foundSegments.size() == ONE_SIDE) {
      Part core = triangle.toPart();
      core = replaceSegmentWithPart(foundSegments.get(0), core);
      addPart(core);

    }
    // If 2 segments found, if the midpoint is a single point
    // then simply remove it.  If the midpoint is
    if (foundSegments.size() == TWO_SIDES) {
      twoSegments(foundSegments, triangle);
    }
    if (foundSegments.size() == THREE_SIDES) {
      threeSegments(foundSegments, triangle);
    }
  }

  /**
   * Two segments of triangle were found to match existing combined. Modify
   * either the existing part or combined to include the new triangle.
   *
   * @param foundSegments The list of PartSegmentJoint intersectingVertices that
   * were found.
   * @param triangle The triangle being added.
   */
  final void twoSegments(List<PartSegmentJoint> foundSegments,
      ContourTriangle triangle) {
    if (foundSegments.get(0).getPart() != foundSegments.get(1).getPart()) {
      // all combined are separate, so we just join the individual combined
      // to the current triangle.
      Part core = triangle.toPart();
      core = replaceSegmentWithPart(foundSegments.get(0), core);
      core = replaceSegmentWithPart(foundSegments.get(1), core);
      addPart(core);

    } else {
      Part core = triangle.toPart();
      List<Part> replacements = replaceTwoSegmentsWithPart(
          foundSegments.get(0), foundSegments.get(1), core);
      for (Part part : replacements) {
        addPart(part);
      }
    }
  }

  /**
   * Three segments of triangle were found to match existing combined. Modify
   * either the existing part or combined to include the new triangle.
   *
   * @param foundSegments The list of PartSegmentJoint intersectingVertices that
   * were found.
   * @param triangle The triangle being added.
   */
  final void threeSegments(List<PartSegmentJoint> foundSegments,
      ContourTriangle triangle) {
    Part part1 = foundSegments.get(0).getPart();
    Part part2 = foundSegments.get(1).getPart();
    Part part3 = foundSegments.get(2).getPart();
    if ((part1 != part2)
        && (part2 != part3)
        && (part3 != part1)) {
      // The combined are all separate, so just merge them.
      Part core = triangle.toPart();
      core = replaceSegmentWithPart(foundSegments.get(0), core);
      core = replaceSegmentWithPart(foundSegments.get(1), core);
      core = replaceSegmentWithPart(foundSegments.get(2), core);
      addPart(core);
    } else if (part1 == part2 && part2 == part3) {

      int ia = foundSegments.get(0).getSegmentIndex();
      int ib = foundSegments.get(1).getSegmentIndex();
      int ic = foundSegments.get(2).getSegmentIndex();
      removePart(part1);
      if (areSequential(ia, ib, ic)) {
        // just remove all three vertices from the closed part.
        List<Integer> removeList = toList(ia, ib, ic);
        Part trim = part1.copy();
        trim.removeCoordinatesAt(removeList);
        addPart(trim); // ensure combined array is up to date.
      } else {

        Shape combined = addTriangle(part1, triangle);
        for (Part ring : combined.getParts()) {
          addPart(ring);
        }
        //addPart(part1);// TO Do: addTriangle
      }
    } else {
      // two are the same, one is different;
      PartSegmentJoint same1;
      PartSegmentJoint same2;
      PartSegmentJoint different;
      if (part1 == part2) {
        same1 = foundSegments.get(0);
        same2 = foundSegments.get(1);
        different = foundSegments.get(2);
      } else if (part2 == part3) {
        same1 = foundSegments.get(1);
        same2 = foundSegments.get(2);
        different = foundSegments.get(0);
      } else {
        same1 = foundSegments.get(2);
        same2 = foundSegments.get(0);
        different = foundSegments.get(1);
      }
      Part core = triangle.toPart();
      core = replaceSegmentWithPart(different, core);
      List<Part> items = replaceTwoSegmentsWithPart(same1, same2, core);
      for (Part part : items) {
        addPart(part);
      }
    }
  }

  /**
   * This creates a new part that is the equivalent of the existing part if you
   * added in a triangle. In cases of pure sequential vertices, this is handled
   * by simply removing them.
   * <ul>
   * <li>Remove vertices from remainder if they don't lead anywhere off the
   * triangle.</li>
   * <li>Follow a path away from the vertex.</li>
   * <li>If you arrive back at the same vertex, (but not the same item), then
   * separate this as a result part, removing the entire sequence from the
   * remainder.</li>
   * <li>If you arrive at a different vertex that is part of the triangle,
   * determine if you can bounce, and keep moving in a path that is away from
   * the triangle. If yes, continue on that path. If not, close the part by
   * connecting to the start vertex.
   * </li>
   * <li>Continue in this manner until all the intersecting vertices of the
   * triangle found within the part are considered.
   * </ul>
   *
   * @param part The part to modify.
   * @param triangle The triangle to add to the part.
   * @return A Shape.
   */
  public final Shape addTriangle(Part part, ContourTriangle triangle) {

    // start with one coordinate of the triangle.
    List<Coord> triangleCoords = triangle.toCoords();

    Coord a = CoordFactory.get(triangle.getA());
    Shape result = new Shape(ShapeType.getPolyLine(a.hasM(), a.hasZ()));
    Part remainder = part.copy();
    // find all matches for a in the part.

    List<CoordIndex> unhandledIntersections = new ArrayList<>();
    for (Coord coord : triangleCoords) {
      List<Integer> intersections = remainder.findAllIndices(coord);
      for (Integer intersection : intersections) {
        CoordIndex index = new CoordIndex(coord, intersection);
        unhandledIntersections.add(index);
      }
    }

    CoordIndexRemover ring = new CoordIndexRemover(part);

    while (!unhandledIntersections.isEmpty()) {
      CoordIndex start = unhandledIntersections.get(0); // pick any of the initial paths.
      unhandledIntersections.remove(0);
      if (ring.getRemoved().contains(start.getIndex())) {
        continue; // This was likely the tail end of a path already covered.
      }
      ring.setNextIndex(start.getIndex());
      ring.setDirection(IndexDirection.Increasing);
      if (!ring.hasNext()) {
        ring.setDirection(IndexDirection.Decreasing);
      } else {
        CoordIndex nextItem = ring.peekNext();
        if (triangle.toCoords().contains(nextItem.getCoord())) {
          // don't move along the triangle.
          ring.setDirection(IndexDirection.Decreasing);
        }
      }
      if (!ring.hasNext()) {
        continue;
      }
      CoordIndex nextItem = ring.peekNext();
      if (triangle.toCoords().contains(nextItem.getCoord())) {
        continue;
      }

      Part createdPart = new Part();
      createdPart.setClosed(true);
      createdPart.getCoordinates().add(start.getCoord());

      while (ring.hasNext()) {
        CoordIndex next = ring.next();
        createdPart.getCoordinates().add(next.getCoord());
        if (triangleCoords.contains(next.getCoord())) {
          if (next.getCoord().equals(start.getCoord())) {
            if (createdPart.getCoordinates().get(0).equals(next.getCoord())) {
              createdPart.getCoordinates().remove(
                  createdPart.getCoordinates().size() - 1);
            }
            result.getParts().add(createdPart);
            // close this off as a new part.
            break;
          } else {
            // try to bounce:
            if (ring.hasNext()) {
              // check the next coord without actually moving.
              CoordIndex peek = ring.peekNext();
              if (!triangleCoords.contains(peek.getCoord())) {
                // we go ahead and move right through the vertex and keep on moving.
                createdPart.getCoordinates().add(next.getCoord());
              } else {
                // See if it is possible to jump to another path that is
                // away from the triangle.
                List<CoordIndex> intersectingVertices
                    = intersecting(unhandledIntersections, next);
                if (intersectingVertices.isEmpty()) {
                  if (createdPart.getCoordinates().get(0).equals(next.getCoord())) {
                    createdPart.getCoordinates().remove(
                        createdPart.getCoordinates().size() - 1);
                  }
                  result.getParts().add(createdPart);
                  // close this off as a new part.
                  break; // break while
                } else {
                  CoordIndex chosen = null;
                  for (CoordIndex item : intersectingVertices) {
                    int up = item.getIndex() + 1;
                    if (up < remainder.getCoordinates().size()) {
                      Coord coordUp = remainder.getCoordinates().get(up);
                      if (!triangle.toCoords().contains(coordUp)) {
                        // up not on triangle, continue from up.
                        ring.setNextIndex(up);
                        ring.setDirection(IndexDirection.Increasing);
                        chosen = item;
                        break; // break for
                      } else {
                        int dwn = item.getIndex() - 1;
                        if (dwn >= 0) {
                          Coord coordDown = remainder.getCoordinates().get(dwn);
                          if (!triangle.toCoords().contains(coordDown)) {
                            // down not on triangle continue from down
                            ring.setNextIndex(dwn);
                            ring.setDirection(IndexDirection.Decreasing);
                            chosen = item;
                            break; // break for
                          }
                        }
                      }
                    }
                  }
                  if (chosen != null) {
                    unhandledIntersections.remove(chosen);
                  }
                }
              }
            }
          }
        }
      }
      if (!createdPart.isEmpty()) {
        int last = createdPart.getCoordinates().size() - 1;
        if (createdPart.getCoordinates().get(0).equals(
            createdPart.getCoordinates().get(last))) {
          createdPart.getCoordinates().remove(last);
        }
        if (!result.getParts().contains(createdPart)) {
          result.getParts().add(createdPart);
        }
      }
    }
    // process b
    // process c
    return result;
  }

  /**
   * This contains test only considers the vertex, and ignores the item value.
   *
   * @param list THe list to search.
   * @param testCoord The CoordIndex to compare to.
   * @return Boolean, true if the coordinate was found in the list.
   */
  private List<CoordIndex> intersecting(List<CoordIndex> list, CoordIndex testCoord) {
    List<CoordIndex> result = new ArrayList<>();
    for (CoordIndex item : list) {
      if (item.getCoord().equals(testCoord.getCoord())) {
        result.add(item);
      }
    }
    return result;
  }

//  /**
//   * With this strategy, we look for multiple instances of intersections of
//   * points at at the same triangle vertex. Duplicate indices mean that the path
//   * is not sequential around the triangle.
//   *
//   * @param original The original part to modify.
//   * @param triangle The triangle to add to the part.
//   * @return The list of parts created.
//   */
//  final List<Part> replaceThreeSegmentsWithPart(Part original,
//      ContourTriangle triangle) {
//    Coord a = CoordFactory.get(triangle.getA());
//    boolean didA = false;
//    Coord b = CoordFactory.get(triangle.getB());
//    boolean didB = false;
//    Coord c = CoordFactory.get(triangle.getC());
//    boolean didC = false;
//    List<Integer> aList = original.findAllIndices(a);
//    List<Integer> bList = original.findAllIndices(b);
//    List<Integer> cList = original.findAllIndices(c);
//    int start = aList.get(0);
//    // Coord forward = original.getCoordinates().
//    return null;
//  }
  /**
   * In this case, both segments are connected to a single part. This will
   * replace both of the segments with the necessary part.
   *
   * @param first the first part segment joint.
   * @param second The second part segment joint.
   * @param core - the core part may be a triangle, or an expanded triangle, but
   * is guaranteed to have the two sides in question be adjacent.
   * @return the list of Parts to use to replace the original. This may either
   * have one member or two, but will never be null.
   */
  final List<Part> replaceTwoSegmentsWithPart(PartSegmentJoint first,
      PartSegmentJoint second, Part core) {
    List<Part> result = new ArrayList<>();
    Part part = first.getPart();
    removePart(part);
//    if (areSequentialOrEndpoints(first.getSegmentIndex(), second.getSegmentIndex())) {
//      result.add(union(first.getPart(), first.getSegment(),
//          second.getSegment(), core));
//    } else {

    Optional<Coord> x = first.getSegment().getJoint(second.getSegment());

    if (!x.isPresent()) {
      throw new IllegalStateException("Segments should be adjacent on Core.");
    }
    int start = core.getCoordinates().indexOf(x.get());
    Part a = new Part();
    a.setClosed(true);
    for (int i = start + 1; i < core.getCoordinates().size(); i++) {
      a.getCoordinates().add(core.getCoordinates().get(i));
    }
    for (int i = 0; i < start; i++) {
      a.getCoordinates().add(core.getCoordinates().get(i));
    }
    // get first item of point.
    int partStart = part.getCoordinates().indexOf(a.end().get());
    int partEnd = part.getCoordinates().indexOf(a.start().get());
    Part b = new Part();
    b.setClosed(true);
    // always move away from x for a, towards it for b
    int partStartMinus = partStart - 1;
    if (partStartMinus < 0) {
      partStartMinus = part.getCoordinates().size() - 1;
    }
    if (x.equals(part.getCoordinates().get(partStartMinus))) {
      a.getCoordinates().addAll(cycler.forwardBetween(
          part.getCoordinates(), partStart, partEnd));
      b.getCoordinates().addAll(cycler.forwardBetween(
          part.getCoordinates(), partEnd, partStart));
    } else {
      a.getCoordinates().addAll(cycler.backwardBetween(
          part.getCoordinates(), partStart, partEnd));
      b.getCoordinates().addAll(cycler.backwardBetween(
          part.getCoordinates(), partEnd, partStart));
    }
    if (a.getCoordinates().size() > 2) {
      result.add(a);
    }
    if (b.getCoordinates().size() > 2) {
      result.add(b);
    }
    return result;
  }

  /**
   * This replaces a segment on the current ContourTriangle with a part that is
   * associated with that segment.
   *
   * @param joint ThePartSegment joint that will replace the segment with its
   * part.
   * @param core The new part, starting from the basic triangle to be replaced.
   * @return Part the replacement unioned part created using the joints part and
   * the core part.
   */
  final Part replaceSegmentWithPart(PartSegmentJoint joint, Part core) {
    removePart(joint.getPart());
    return union(joint, core);
  }

  /**
   * Given two separate combined that will share the specified segment, this
   * will combine the two combined.
   *
   * @param joint The joint providing the segment and part to replace it.
   * @param core The part that will exchange the segment for a part.
   * @return A unioned shape.
   */
  final Part union(PartSegmentJoint joint, Part core) {
    // by this point it is assumed a and b both contain both points of the segment.
    Part a = joint.getPart();
    Segment seg = joint.getSegment();
    int p1 = core.getCoordinates().indexOf(seg.getP1());
    int p2 = core.getCoordinates().indexOf(seg.getP2());
    Part result = new Part();
    result.setClosed(true);
    result.getCoordinates().add(seg.getP2());
    int size = joint.getPart().getCoordinates().size();
    int start = (joint.getSegmentIndex() + 1) % size;
    int end = joint.getSegmentIndex();
    result.getCoordinates().addAll(
        cycler.forwardBetween(joint.getPart().getCoordinates(), start, end));
    result.getCoordinates().add(seg.getP1());
    int partStartMinus = p1 - 1;
    if (partStartMinus < 0) {
      partStartMinus = core.getCoordinates().size() - 1;
    }
    if (seg.getP2().equals(core.getCoordinates().get(partStartMinus))) {
      result.getCoordinates().addAll(
          cycler.forwardBetween(core.getCoordinates(), p1, p2));
    } else {
      result.getCoordinates().addAll(
          cycler.backwardBetween(core.getCoordinates(), p1, p2));
    }
    return result;
  }

  /**
   * Sorts the three start values in descending order.
   *
   * @param indices The variable args set of integers.
   * @return A list of the integers sorted in descending order.
   */
  final List<Integer> toList(int... indices) {
    List<Integer> result = new ArrayList<>();
    for (int index : indices) {
      result.add(index);
    }
    return result;
  }

  /**
   * This checks the three start values to determine if all three are
   * sequential. This can be equated to the endpoints touching
   *
   * @param indices either an array of item values, or item values passed in as
   * separate paramters.
   * @return boolean, true if the indices are sequential.
   */
  final boolean areSequential(Integer... indices) {
    List<Integer> values = new ArrayList<>(Arrays.asList(indices));
    Collections.sort(values);
    int prev = values.get(0);
    for (int i = 1; i < values.size(); i++) {
      Integer val = values.get(i);
      if (val - prev != 1) {
        return false;
      }
      prev = val;
    }
    return true;

  }

  /**
   * Removes the specified part, and all the segments pointing to it.
   *
   * @param part The part to remove.
   */
  final void removePart(Part part) {
    for (Segment seg : part.getReversableSegments()) {
      if (parts.containsKey(seg)) {
        parts.get(seg).remove(part);
        if (parts.get(seg).isEmpty()) {
          parts.remove(seg);
        }
      }
    }
  }

  /**
   * Adds the part, ensuring that every segment in the part will point to the
   * specified part. This does not modify the actual part to make a union, but
   * is intended for internal testing.
   *
   * @param part The part to add.
   */
  final void addPart(Part part) {
    for (Segment seg : part.getReversableSegments()) {
      List<Part> matches;
      if (parts.containsKey(seg)) {
        matches = parts.get(seg);
      } else {
        matches = new ArrayList<>();
        parts.put(seg, matches);
      }
      matches.add(part);
    }
  }

  /**
   * Gets the hash map of combined. This is useful for unit testing.
   *
   * @return the combined The combined hash map.
   */
  final HashMap<Segment, List<Part>> getParts() {
    return parts;
  }

}
