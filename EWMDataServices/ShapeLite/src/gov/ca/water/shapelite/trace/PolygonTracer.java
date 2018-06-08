/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.trace;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PolygonTracer {

  /**
   * The reference polygons to trace to.
   */
  private final FeatureSet reference;

  /**
   * The reference polygon.
   *
   * @param reference The reference.
   */
  public PolygonTracer(FeatureSet reference) {
    this.reference = reference;
  }

  /**
   *
   *
   * @param shape The shape to represent.
   * @param tolerance The result range to allow for snapping.
   * @return The modified shape.
   */
  public final Shape trace(Shape shape, double tolerance) {
    FeatureDistanceCalculator closest = new FeatureDistanceCalculator();
    FeatureSetDistance previous = null;
    List<Coord> resultCoords = new ArrayList<>();
    boolean tracing = false;
    for (Coord drawnCoord : shape.getCoordinates()) {
      FeatureSetDistance fsd = closest.getFeatureDistances(reference,
          drawnCoord, tolerance);
      if (previous == null) {
        if (fsd.getClosestFeature().isPresent()) {
          if (fsd.getClosestFeature().get().getClosestPart().isPresent()) {
            previous = fsd;
            Coord tracePoint = fsd.getClosestFeature().get()
                .getClosestPart().get().getCoord();
            resultCoords.add(tracePoint);
            continue;
          }
        }
      } else {
        Optional<SharedPartDistance> maybeShared = fsd.getSharedPart(previous);
        if (maybeShared.isPresent()) {

          // same part, so just trace from one index to the other.
          SharedPartDistance pd = maybeShared.get();
          int start = pd.getPrevious().getSegmentIndex();
          int end = pd.getCurrent().getSegmentIndex();
          Part part = pd.getCurrent().getPart().get();

          double forwardLength = length(part, start, end);
          double backwardLength = length(part, end, start);
          if (forwardLength < backwardLength) {
            if (start < end) {
              for (int i = start + 1; i < end; i++) {
                resultCoords.add(part.getCoordinates().get(i));
              }
            } else if (end < start) {
              for (int i = start + 1; i < part.getCoordinates().size(); i++) {
                resultCoords.add(part.getCoordinates().get(i));
              }
              for (int i = 0; i < end; i++) {
                resultCoords.add(part.getCoordinates().get(i));
              }
            }
          } else if (start > end) {
            for (int i = start - 1; i > end; i--) {
              resultCoords.add(part.getCoordinates().get(i));
            }
          } else if (start < end) {
            for (int i = start - 1; i >= 0; i--) {
              resultCoords.add(part.getCoordinates().get(i));
            }
            for (int i = part.getCoordinates().size() - 1; i > end; i--) {
              resultCoords.add(part.getCoordinates().get(i));
            }
          }
          previous = fsd;
          // Add the actual closest point on the line, which may not have been a vertex.
          resultCoords.add(
              fsd.getClosestFeature().get().getClosestPart().get().getCoord());
          continue;
        }
        previous = fsd;
        resultCoords.add(
              fsd.getClosestFeature().get().getClosestPart().get().getCoord());
      }
      // only do this if we failed to match anything.
      resultCoords.add(drawnCoord);
    }
    Shape polygon = new Shape(ShapeType.Polygon);
    Part part = new Part();
    part.setClosed(true);
    part.getCoordinates().addAll(resultCoords);
    polygon.getParts().add(part);
    polygon.calculateBounds();
    return polygon;
  }

  /**
   * Given the start index and the end index, calculates the combined segment
   * result.
   *
   * @param part The part.
   * @param start The start coordinate index.
   * @param end The end coordinate index.
   * @return The length.
   */
  private double length(Part part, int start, int end) {
    double result = 0;
    Coord previous = null;
    if (start > end) {
      for (int i = start; i < part.getCoordinates().size(); i++) {
        Coord coord = part.getCoordinates().get(i);
        if (previous != null) {
          result += coord.distance2D(previous);
        }
        previous = coord;
      }
      for (int i = 0; i < end; i++) {
        Coord coord = part.getCoordinates().get(i);
        if (previous != null) {
          result += coord.distance2D(previous);
        }
        previous = coord;
      }
    } else {
      for (int i = start; i < end; i++) {
        Coord coord = part.getCoordinates().get(i);
        if (previous != null) {
          result += coord.distance2D(previous);
        }
        previous = coord;
      }
    }
    return result;

  }

  /**
   * Gets the FeatureSet for the reference.
   *
   * @return the reference
   */
  public final FeatureSet getReference() {
    return reference;
  }

}
