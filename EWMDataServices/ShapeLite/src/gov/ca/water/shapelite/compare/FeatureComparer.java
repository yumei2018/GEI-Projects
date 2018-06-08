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
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Nullable;
import gov.ca.water.shapelite.utils.NullSafe;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class FeatureComparer implements EqualityComparer<Feature> {

  /**
   * The error string.
   */
  private String error;

  /**
   * Tests the a and b feature against each other to determine if they represent
   * the same feature.
   *
   * @param a The first feature to compare.
   * @param b The second feature to compare.
   * @return Boolean, true if the two features are equivalent, even if they are
   * not equal. That is, the features should have shapes that are equivalent and
   * attribute keys and values that are the same.
   */
  @Override
  public final boolean equivalent(@Nullable Feature a, @Nullable Feature b) {
    if (a == null && b == null) {
      return true;
    }
    if (a == null || b == null) {
      error = "only one feature was null.";
      return false;
    }
    if (a.getAttributes().size() != b.getAttributes().size()) {
      error = "the attribute array sizes were not equal.";
      return false;
    }
    for (String key : a.getAttributes().keySet()) {
      if (!b.getAttributes().containsKey(key)) {
        error = "the key " + key + " was not found in b.";
      }
      if (!NullSafe.equals(a.getAttributes().get(key), b.getAttributes().get(key))) {
        error = "the values for " + key + " were different.";
        return false;
      }
    }
    if (!envelopesEqual(a, b)) {
      error = "the feature envelopes were not equal.";
      return false;
    }
    ShapeComparer shapes = new ShapeComparer();
    boolean result = shapes.equivalent(a.getShape(), b.getShape());
    if(result == false){
      error = "the shapes were not equal because " + shapes.getError();
    }
    return result;

  }

  /**
   * Compares the envelopes from feature a and b in a null safe way.
   *
   * @param a The first feature to compare.
   * @param b The second feature to compare.
   * @return Boolean, true if the envelopes are the same.
   */
  private boolean envelopesEqual(@NonNull Feature a, @NonNull Feature b) {
    if (a.getEnvelope() == null && b.getEnvelope() == null) {
      return true;
    }
    if (a.getEnvelope() == null) {
      return false;
    }
    return a.getEnvelope().equals(b.getEnvelope());
  }

  /**
   * Gets the error string.
   *
   * @return the error string.
   */
  @Override
  public final String getError() {
    return error;
  }

}
