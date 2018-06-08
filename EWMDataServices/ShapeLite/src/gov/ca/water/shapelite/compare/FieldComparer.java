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

import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.utils.NullSafe;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class FieldComparer implements EqualityComparer<Field> {

  /**
   * The error.
   */
  private String error;

  /**
   * compares the two fields to see if they are effectively the same based on
   * their properties.
   *
   * @param first The first field to test.
   * @param second The second field to test.
   * @return Boolean, true if the two fields are functionally the same.
   */
  @Override
  public final boolean equivalent(Field first, Field second) {
    if (first == second) {
      // if it is the same instance, we don't need to test everything.
      return true;
    }

    if (!NullSafe.equals(first.getDataType(), second.getDataType())) {
      error = "The data types were not equal.";
      return false;
    }
    if (first.getDecimal() != second.getDecimal()) {
      error = "The decimals were not equal.";
      return false;
    }
    if (first.getLength() != second.getLength()) {
      error = "The lengths were not equal.";
      return false;
    }
    if (!NullSafe.equals(first.getName(), second.getName())) {
      error = "The names were not equal.";
      return false;
    }
    if (first.getOffset() != second.getOffset()) {
      error = "The offsets were not equal.";
      return false;
    }
    if (!NullSafe.equals(first.getShortName(), second.getShortName())) {
      error = "The short names were not equal.";
      return false;
    }
    return true;
  }

  /**
   * Gets the error string.
   *
   * @return the error
   */
  @Override
  public final String getError() {
    return error;
  }

}
