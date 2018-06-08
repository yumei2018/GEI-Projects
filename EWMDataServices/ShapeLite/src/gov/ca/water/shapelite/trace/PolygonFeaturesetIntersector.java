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
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileWriter;
import gov.ca.water.shapelite.projection.ProjectionException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PolygonFeaturesetIntersector {

  /**
   * Performs the intersection calculation, returning the list of strings
   * idField values from the specified test polygons FeatureSet.
   *
   * @param polygons The feature set of polygons to test.
   * @param reference The polygons to intersect with.
   * @param idField The IdField.
   * @return The List of String objects.
   */
  public final List<String> getIntersectionFieldValues(FeatureSet polygons,
      FeatureSet reference, String idField) {
    return getIntersectionFieldValues(polygons, reference, idField, 0);
  }

  /**
   * Performs the intersection calculation, returning the list of strings
   * idField values from the specified test polygons FeatureSet.
   *
   * @param polygons The feature set of polygons to test.
   * @param reference The polygons to intersect with.
   * @param idField The IdField.
   * @param bufferFeet The Buffer distance to apply around the polygons before
   * calculating an intersection. If this distance is negative, then the
   * polygons will be smaller, and if it is positive they will be larger for
   * intersection purposes.
   * @return The List of String objects.
   */
  public final List<String> getIntersectionFieldValues(FeatureSet polygons,
      FeatureSet reference, String idField, double bufferFeet) {
    List<String> result = new ArrayList<>();
    if (!polygons.containsFieldNamed(idField)) {
      return result;
    }
    double bufferDistance = 0;
    if (bufferFeet != 0 && polygons.getProjection() != null) {
      bufferDistance = polygons.getProjection().feetToProj(bufferFeet);
    }
    for (Feature polygon : polygons.getFeatures()) {
      Envelope env = polygon.getEnvelope();
      Shape buffer = null;
      for (Feature ref : reference.getFeatures()) {

        if (!env.intersects(ref.getEnvelope())) {
          continue;
        }
        if (bufferDistance != 0) {
          if (buffer == null) {
            buffer = polygon.getShape().buffer(bufferDistance);
            if (buffer.getShapeType() == ShapeType.NullShape) {
              buffer = new Shape(env.getCenter());
            }
          }
          if (!buffer.intersects(ref.getShape())) {
            continue;
          }
        }
        if (polygon.intersects(ref)) {
          result.add(polygon.getAttributes().get(idField));
          break;
        }
      }
    }
    return result;
  }

  /**
   * Returns the intersection shapes as intersection pieces, so that every
   * possible intersection combination is represented in the output. If base
   * polygon A overlaps with other polygon B and C, this will have two output
   * features: AB, AC.
   *
   * @param basePolygons The FeatureSet with basePolygons to intersect, and that
   * will provide the primary attributes and projection. Attributes from
   * otherPolygons will only be added if they do not conflict with the existing
   * attributes in basePolygons.
   * @param otherPolygons The otherPolygons basePolygons that provide the shapes
   * to intersect with but whose attributes will only be added if they do not
   * conflict with the existing base polygons.
   * @return The FeatureSet.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if the
   * projection for basin data doesn't match and reproject fails.
   */
  public final FeatureSet getSplitIntersections(FeatureSet basePolygons,
      FeatureSet otherPolygons) throws ProjectionException {
    FeatureSet result = new FeatureSet();
    result.copyFields(basePolygons);
    result.addMissingFields(otherPolygons.getFields());
    result.setProjectionFrom(basePolygons.getProjection());
    FeatureSet projectedF = otherPolygons.copy();
    if (!otherPolygons.getProjection().equals(basePolygons.getProjection())) {
      projectedF.reproject(basePolygons.getProjection());
    }

    for (Feature feature : basePolygons.getFeatures()) {

      Shape shape = feature.getShape();
      Shape resultShape = new Shape(shape.getShapeType());
      for (Feature otherFeature : projectedF.getFeatures()) {
        Feature resultFeature = new Feature();
        resultFeature.copyAttributes(feature.getAttributes());
        Shape other = otherFeature.getShape();
        Optional<Shape> maybeIntersect = shape.intersection(other);
        if (maybeIntersect.isPresent() && !maybeIntersect.get().isEmpty()) {
          resultShape.getParts().addAll(maybeIntersect.get().getParts());
          resultShape.calculateBounds();
          resultFeature.setShape(resultShape);
          resultFeature.copyMissingAttributes(otherFeature);
          result.getFeatures().add(resultFeature);
        }

      }
    }

    return result;
  }

  /**
   * Returns the intersection shapes grouped according to the base Polygon
   * shapes. If more than one intersection shape is achieved, that information
   * is ignored.
   *
   * @param basePolygons The FeatureSet with basePolygons to intersect, and that
   * will provide the attributes. Attributes from otherPolygons will be ignored
   * since possibly multiple shapes will be merged into one.
   * @param otherPolygons The otherPolygons basePolygons that provide the shapes
   * to intersect with but whose attributes will be ignored.
   * @return The FeatureSet.
   */
  public final FeatureSet getGroupedIntersections(FeatureSet basePolygons,
      FeatureSet otherPolygons) {
    return getGroupedIntersections(basePolygons, otherPolygons, 0);
  }

  /**
   * Returns the intersection shapes grouped according to the base Polygon
   * shapes. If more than one intersection shape is achieved, that information
   * is ignored.
   *
   * @param basePolygons The FeatureSet with basePolygons to intersect, and that
   * will provide the attributes. Attributes from otherPolygons will be ignored
   * since possibly multiple shapes will be merged into one.
   * @param otherPolygons The otherPolygons basePolygons that provide the shapes
   * to intersect with but whose attributes will be ignored.
   * @param bufferFeet The buffer distance in feet. If the buffered shape is
   * intersects then the unbuffered intersection is returned. This is intended
   * to be used to prevent slivers by using a negative buffer distance. A
   * positive buffer will do nothing here as shapes would still be bounded by
   * the intersection calculation.
   * @return The FeatureSet.
   */
  public final FeatureSet getGroupedIntersections(FeatureSet basePolygons,
      FeatureSet otherPolygons, double bufferFeet) {
    FeatureSet result = new FeatureSet(basePolygons.getDefaultShapeType());
    result.copyFields(basePolygons);
    for (Feature baseFeature : basePolygons.getFeatures()) {
      Feature resultFeature = new Feature();
      Shape baseShape = baseFeature.getShape();
      Shape resultShape = new Shape(baseShape.getShapeType());
      FeatureSet buffered = otherPolygons;
      double dist = 0;
      if (bufferFeet != 0) {
        dist = otherPolygons.getProjection().feetToProj(bufferFeet);
      }
      for (Feature otherFeature : otherPolygons.getFeatures()) {
        Shape bufferShape = otherFeature.getShape();
        Coord center = bufferShape.getEnvelope().getCenter();
        bufferShape = bufferShape.buffer(dist);
        if (bufferShape.getShapeType() == ShapeType.NullShape) {
          bufferShape = new Shape(ShapeType.Point);
          bufferShape.setX(center.getX());
          bufferShape.setY(center.getY());
        }
        if (bufferShape.intersects(baseShape)) {
          Shape otherShape = otherFeature.getShape();
          Optional<Shape> intersect = baseShape.intersection(otherShape);
          if (intersect.isPresent()) {
            resultShape.getParts().addAll(intersect.get().getParts());
            resultShape.calculateBounds();
            resultFeature.setShape(resultShape);
            resultFeature.copyAttributes(baseFeature.getAttributes());
            result.getFeatures().add(resultFeature);
          }
        }

      }
    }
    return result;
  }
}
