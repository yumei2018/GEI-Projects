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
package gov.ca.water.shapelite.compare;

import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class FeatureSetComparer implements EqualityComparer<FeatureSet> {

  /**
   * The error.
   */
  private String error;

  /**
   * This method compares to featuresets to determine if the fields and features
   * from both classes are the same.
   *
   * @param a The first feature to compare.
   * @param b The second feature to compare.
   * @return Boolean true, if the features are equivalent, false otherwise.
   */
  @Override
  public final boolean equivalent(FeatureSet a, FeatureSet b) {
    if (!equals(a.getName(), b.getName())) {
      error = "the names are not equal.";
      return false;
    }
    if (!equals(a.getFeatureCategory(), b.getFeatureCategory())) {
      error = "the categories are not equal.";
      return false;
    }
    if (!equals(a.getFeatureType(), b.getFeatureType())) {
      error = "the featureTypes are not equal.";
      return false;
    }
    if (!equals(a.getFields().size(), b.getFields().size())) {
      error = "the fields list sizes are not equal.";
      return false;
    }
    if (!equals(a.getFeatures().size(), b.getFeatures().size())) {
      error = "the features list sizes are not equal";
      return false;
    }
    if (!equals(a.getProjection(), b.getProjection())) {
      error = "the projections are not equal";
      return false;
    }
    if (!equals(a.getEnvelope(), b.getEnvelope())) {
      error = "the envelopes are not equal";
      return false;
    }
    FieldComparer fieldC = new FieldComparer();
    for (int i = 0; i < a.getFields().size(); i++) {
      Field aField = a.getFields().get(i);
      Field bField = b.getFields().get(i);
      if (!fieldC.equivalent(aField, bField)) {
        error = "the field " + aField.getName() + " is not equal to "
            + bField.getName() + " because " + fieldC.getError();
        return false;
      }
    }
    FeatureComparer featureC = new FeatureComparer();
    for (int i = 0; i < a.getFeatures().size(); i++) {
      Feature aFeature = a.getFeatures().get(i);
      Feature bFeature = b.getFeatures().get(i);
      if (!featureC.equivalent(aFeature, bFeature)) {
        error = "the features at index " + i + " are not equal because "
            + featureC.getError();
        return false;
      }
    }

    return true;
  }

  /**
   * Null safe equals.
   *
   * @param a The first object to compare.
   * @param b The second object to compare.
   * @return Boolean, true if the objects are either both null, or they pass an
   * equals test.
   */
  private boolean equals(Object a, Object b) {
    if (a == null && b == null) {
      return true;
    }
    if (a != null) {
      return a.equals(b);
    }
    return false;
  }

  /**
   * Gets the string error.
   *
   * @return the error text.
   */
  public String getError() {
    return error;
  }

}
