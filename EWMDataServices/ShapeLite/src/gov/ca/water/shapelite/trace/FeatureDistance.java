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

import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.Optional;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class FeatureDistance {

  /**
   * The feature.
   */
  private Feature feature;

  /**
   * The distances to each part.
   */
  private final List<PartDistance> partDistances;

  /**
   * Creates a new instance of the FeatureDistance class.
   */
  public FeatureDistance() {
    partDistances = new ArrayList<>();
  }

  /**
   * Creates a new instance of the FeatureDistance class.
   *
   * @param feature The Feature.
   */
  public FeatureDistance(Feature feature) {
    this.feature = feature;
    partDistances = new ArrayList<>();
  }

  /**
   * Gets the closest distance of all the part distances.
   *
   * @return The closest distance.
   */
  public final double getDistance() {
    double min = Double.MAX_VALUE;
    for (PartDistance partDist : partDistances) {
      if (partDist.getDistance() < min) {
        min = partDist.getDistance();
      }
    }
    return min;
  }

  /**
   * Gets the part distance with the minimum distance.
   *
   * @return The closest part distance.
   */
  public final Optional<PartDistance> getClosestPart() {
    PartDistance result = null;
    Double closestDist = Double.MAX_VALUE;
    for (PartDistance pd : partDistances) {
      if (pd.getDistance() < closestDist) {
        result = pd;
        closestDist = pd.getDistance();
      }
    }
    return Optional.ofNullable(result);
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the feature for this FeatureDistance item.
   *
   * @return the feature
   */
  public final Optional<Feature> getFeature() {
    return Optional.ofNullable(feature);
  }

  /**
   * Sets the feature for this feature distance object.
   *
   * @param feature the feature to set
   */
  public final void setFeature(Feature feature) {
    this.feature = feature;
  }

  /**
   * Gets the list of part distances for the parts on this feature.
   *
   * @return the partDistances
   */
  public final List<PartDistance> getPartDistances() {
    return partDistances;
  }

  // </editor-fold>
}
