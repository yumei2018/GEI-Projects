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
import gov.ca.water.shapelite.Shape;
import java.util.ArrayList;
import java.util.List;

/**
 * This comparer uses a strict part equality comparison in terms of coordinates,
 * but allows multiple part shapes to be organized in any order.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShapeComparer implements EqualityComparer<Shape> {

  /**
   * The String error message.
   */
  private String error;

  /**
   * Compares all the parts from all three shapes. This ensures that each part
   * has an equivalent part, ignoring winding order.
   *
   * @param a The first shape to compare.
   * @param b The second shape to compare.
   * @return Boolean, true if the parts match between the shapes.
   */
  @Override
  public final boolean equivalent(@Nullable Shape a, @Nullable Shape b) {
    if (a == null && b == null) {
      return true;
    }
    if (a == null || b == null) {
      error = "only one of the shapes was null.";
      return false;
    }
    if (a.isEmpty() && b.isEmpty()) {
      return true;
    }
    if (a.isEmpty() || b.isEmpty()) {
      error = "only one of the shapes was empty.";
      return false;
    }
    if (a.getParts().size() != b.getParts().size()) {
      error = "the number of parts were different.";
      return false;
    }
    PartComparer parts = new PartComparer();
    List<Part> bParts = new ArrayList<>(b.getParts());
    int iPart = 0;
    for (Part part : a.getParts()) {
      Part found = null;
      for (Part other : bParts) {
        if (parts.equivalent(part, other)) {
          found = other;
          break;
        }
      }
      if (found == null) {
        error = "A matching part could not be found for part index " + iPart + ".";
        return false;
      } else {
        bParts.remove(found); // prevent counting a found part more than once.
      }
      iPart++;
    }
    return true;
  }

  /**
   * Gets the String reason for inequality.
   *
   * @return the error String
   */
  @Override
  public final String getError() {
    return error;
  }

}
