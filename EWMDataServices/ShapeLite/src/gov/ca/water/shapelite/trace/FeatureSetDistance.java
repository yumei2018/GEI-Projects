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

import gov.ca.water.shapelite.Optional;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class FeatureSetDistance {

  /**
   * The distance to individual features.
   */
  private final List<FeatureDistance> featureDistances;

  /**
   * Creates a new instance of a FeatureSetDistance class.
   */
  public FeatureSetDistance() {
    featureDistances = new ArrayList<>();
  }

  /**
   * Gets the FeatureDistance with the closest part. In most cases, there are
   * likely to me more than one that is equally close, in which case this
   * selection is essentially random.
   *
   * @return optional.
   */
  public final Optional<FeatureDistance> getClosestFeature() {
    double closestDistance = Double.MAX_VALUE;
    FeatureDistance result = null;
    for (FeatureDistance fd : featureDistances) {
      if (fd.getDistance() < closestDistance) {
        result = fd;
      }
    }
    return Optional.ofNullable(result);
  }

  /**
   * Gets the feature distance.
   *
   * @return the featureDistances
   */
  public final List<FeatureDistance> getFeatureDistances() {
    return featureDistances;
  }

  /**
   * This cycles through the listed parts, and finds the first part that
   * is common between the two part distances.
   *
   * @param other The other distance.
   * @return The Optional shared part distance.
   */
  public final Optional<SharedPartDistance> getSharedPart(FeatureSetDistance other) {

    for (FeatureDistance selfFeature : featureDistances) {
      for (PartDistance selfPart : selfFeature.getPartDistances()) {
        for (FeatureDistance otherFeature : other.getFeatureDistances()) {
          for (PartDistance otherPart : otherFeature.getPartDistances()) {
            if (selfPart.getPart().equals(otherPart.getPart())) {
              SharedPartDistance result = new SharedPartDistance();
              result.setCurrent(selfPart);
              result.setPrevious(otherPart);
              return Optional.ofNullable(result);
            }
          }
        }
      }
    }
    return Optional.empty();
  }

}
