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
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ListComparer implements EqualityComparer<List<?>> {

  /**
   * The string error message.
   */
  private String error;

  /**
   * Tests to see if the two lists are equivalent lists of coordinates. This is
   * only true if the coordinates at each index intersect, and the lists are the
   * same size.
   *
   * @param a The first list.
   * @param b The second list.
   * @return Boolean, only true if the coordinate at each index intersects, and
   * the number of indices matches, and the indices are in the same order.
   */
  @Override
  public final boolean equivalent(@Nullable List<?> a,
      @Nullable List<?> b) {
    if (a == null && b == null) {
      return true;
    }
    if (a == null || b == null) {
      error = "there was a null value.";
      return false;
    }
    int size = a.size();
    if (size != b.size()) {
      error = "the list sizes were not the same.";
      return false;
    }
    for (int i = 0; i < size; i++) {
      if (!a.get(i).equals(b.get(i))) {
        error = "the values at index " + i + " were different.";
        return false;
      }
    }
    return true;
  }

  /**
   * Gets the error reason preventing equality.
   *
   * @return the error
   */
  @Override
  public final String getError() {
    return error;
  }

}
