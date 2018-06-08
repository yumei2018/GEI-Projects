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

import com.vividsolutions.jts.geom.Geometry;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.topology.Adapter;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PolygonIntersector {

  /**
   * The reference polygons to trace to.
   */
  private final FeatureSet reference;

  /**
   * The reference polygon.
   *
   * @param reference The reference.
   */
  public PolygonIntersector(FeatureSet reference) {
    this.reference = reference;
  }

  /**
   *
   *
   * @param drawnShape The shape to represent.
   * @return The modified shape.
   */
  public final Shape intersect(Shape drawnShape) {
    Shape polygon = new Shape(reference.getFeatureType());
    for (Part drawnPart : drawnShape.getParts()) {
      drawnPart.setClosed(true);
      if (drawnPart.isHole()) {
        drawnPart.reverse();
      }
    }
    Optional<Geometry> maybeGeometry = Adapter.getGeometry(drawnShape);
    if (!maybeGeometry.isPresent()) {
      return Shape.getNullShape();
    }
    Geometry drawnGeom = maybeGeometry.get();
    if (!drawnGeom.isValid()) {
      boolean stop = true; // drawn incorrectly or bug?
    }
    for (Feature feature : reference.getFeatures()) {
      Shape refShape = feature.getShape();
      if (drawnShape.getEnvelope().intersects2D(refShape.getEnvelope())) {
        Optional<Geometry> maybeGeom = Adapter.getGeometry(refShape);
        if (maybeGeom.isPresent()) {
          Geometry refGeom = maybeGeom.get();
          if (!refGeom.isValid()) {
            boolean stop = true;
          }
          if (refGeom.intersects(drawnGeom)) {
            Geometry clip = refGeom.intersection(drawnGeom);
            Shape clippedShape = Adapter.getShape(clip, false, false);
            for (Part part : clippedShape.getParts()) {
              polygon.getParts().add(part);
            }
          }
        }
      }
    }
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
