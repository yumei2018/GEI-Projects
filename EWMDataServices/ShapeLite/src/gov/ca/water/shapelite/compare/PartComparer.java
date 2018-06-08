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

import gov.ca.water.shapelite.Nullable;
import gov.ca.water.shapelite.Part;

/**
 * A Strict part comparer requires that the coordinates are in the same order
 * in order to be regarded as a match.  This may be over kill for topological
 * operators.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PartComparer implements EqualityComparer<Part> {

  /**
   * Error.
   */
  private String error;

  /**
   * Compares the two parts. For open parts, the coordinates can only be the
   * same, or reversed. For closed parts, as long as there exists a starting
   * point that can produce the same sequence of coordinates while moving in
   * either direction, they will be regarded as equivalent.
   *
   * @param a The first part to compare.
   * @param b The second part to compare.
   * @return Boolean, true if the coordinates from the two parts all intersect.
   */
  @Override
  public final boolean equivalent(@Nullable Part a, @Nullable Part b) {
    if (a == null && b == null) {
      return true;
    }
    if (a == null || b == null) {
      return false;
    }
    if (a.isEmpty() && b.isEmpty()) {
      return true;
    }
    if (a.isEmpty() || b.isEmpty()) {
      return false;
    }
    if (a.getCoordinates().size() == 1 && b.getCoordinates().size() == 1) {
      return a.start().equals(b.start());
    }
    if (a.isClosed() != b.isClosed()) {
      return false;
    }

    ListComparer lists = new ListComparer();
    return lists.equivalent(a.getCoordinates(), b.getCoordinates());
  }

  /**
   * Gets the error.
   * @return the error
   */
  @Override
  public final String getError() {
    return error;
  }

}
