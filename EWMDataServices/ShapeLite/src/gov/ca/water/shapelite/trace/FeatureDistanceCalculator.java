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
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Segment;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class FeatureDistanceCalculator {

  /**
   * Gets the closest feature.
   *
   * @param featureset The FEatureSet to consider features from.
   * @param point The point to compare the distance to.
   * @param tolerance The tolerance to control if a part can be used.
   * @return A FeatureDistance object.
   */
  public final FeatureSetDistance getFeatureDistances(FeatureSet featureset,
      Coord point, double tolerance) {
    FeatureSetDistance result = new FeatureSetDistance();
    for (Feature feature : featureset.getFeatures()) {
      double dist = feature.getEnvelope().distance(point);
      if (dist > tolerance) {
        continue;
      }
      FeatureDistance fd = getFeatureDistance(feature, point, tolerance);

      if (!fd.getPartDistances().isEmpty()) {
        double distance = fd.getDistance();
        if (distance < tolerance) {
          result.getFeatureDistances().add(fd);
        }
      }
    }
    return result;
  }

  /**
   * Gets the closest part etc.
   *
   * @param feature The feature to compare to the point.
   * @param point The point to compare to.
   * @param tolerance the tolerance.
   * @return The FeatureDistance with the information about the closest
   * position.
   */
  public final FeatureDistance getFeatureDistance(Feature feature, Coord point,
      double tolerance) {
    FeatureDistance result = new FeatureDistance();
    result.setFeature(feature);
    for (Part part : feature.getShape().getParts()) {
      PartDistance partdist = getPartDistance(part, point);
      if (partdist.getDistance() > tolerance) {
        continue;
      }
      result.getPartDistances().add(partdist);
    }
    return result;
  }

  /**
   * Gets the closest point, the segment index and the part to the specified
   * point.
   *
   * @param part the Part to compare to.
   * @param point The Point to compare to.
   * @return FeatureDistance.
   */
  public final PartDistance getPartDistance(Part part, Coord point) {
    PartDistance result = new PartDistance();
    int iSegment = 0;
    int closestIndex = -1;
    double closestDist = Double.MAX_VALUE;
    Coord closest = null;
    if (part.getCoordinates().size() == 1) {
      result.setCoord(part.getCoordinates().get(0));
      result.setSegmentIndex(0);
      result.setPart(part);
      return result;
    }
    for (Segment seg : part.getSegments()) {
      double dist = seg.distanceTo(point);
      if (dist < closestDist) {
        closestDist = dist;
        closestIndex = iSegment;
        closest = seg.closestPointTo(point).getCoord();
      }
      iSegment++;
    }
    result.setCoord(closest);
    result.setDistance(closestDist);
    result.setSegmentIndex(closestIndex);
    result.setPart(part);
    return result;
  }

}
