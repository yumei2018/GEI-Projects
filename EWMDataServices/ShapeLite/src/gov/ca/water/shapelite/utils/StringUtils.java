/*
 * The MIT License
 *
 * Copyright 2015 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.utils;

import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class StringUtils {

  /**
   * Private constructor for utilities class.
   */
  private StringUtils() {

  }

  /**
   * Provides a null-safe string comparison.
   *
   * @param first The first string to compare.
   * @param second The second string to compare.
   * @return Boolean, true if the strings are the same or are both null.
   */
  public static boolean equals(String first, String second) {
    if (first == null) {
      return second == null;
    }
    return first.equals(second);
  }

  /**
   * Joins the following strings using the specified delimiter.
   *
   * @param delimiter The delimeter to use to separate the items..
   * @param strings The list of Strings to join.
   * @return The joined string.
   */
  public static String join(String delimiter, List<String> strings) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < strings.size(); i++) {
      if (i > 0) {
        sb.append(delimiter);
      }
      sb.append(strings.get(i));
    }
    return sb.toString();
  }

  /**
   * Joins the following strings using the specified delimiter.
   *
   * @param delimiter The delimeter to use to separate the items.
   * @param strings The Strings to join, either as an array or as separate
   * arguments.
   * @return The joined string.
   */
  public static String join(String delimiter, String... strings) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < strings.length; i++) {
      if (i > 0) {
        sb.append(delimiter);
      }
      sb.append(strings[i]);
    }
    return sb.toString();
  }
}
