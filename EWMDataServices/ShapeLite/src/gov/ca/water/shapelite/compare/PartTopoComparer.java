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

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Nullable;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.analysis.PartCycler;
import java.util.Collections;
import java.util.List;

/**
 * Unlike the PartComparer, this version allows for reversal, or re-ordering of
 * closed parts, so long as the coordinate topology is the same. Since this
 * ignores reversal, and won't distinguish holes from parts, it should not be
 * used for general shape equality testing. Further, while it is the most
 * robust, it is also the most computationally expensive.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PartTopoComparer implements EqualityComparer<Part> {

  /**
   * The reason for inequality.
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
      error = "only one of the parts was null.";
      return false;
    }
    if (a.isEmpty() && b.isEmpty()) {
      return true;
    }
    if (a.isEmpty() || b.isEmpty()) {
      error = "only one of the parts was empty.";
      return false;
    }
    if (a.getCoordinates().size() == 1 && b.getCoordinates().size() == 1) {
      error = "the only coordinate for each was different.";
      return a.start().equals(b.start());
    }
    PartCycler cycler = new PartCycler();
    Part aTest = a.copy();
    List<Coord> aCoords = aTest.getCoordinates();
    Part bTest = b.copy();
    List<Coord> bCoords = bTest.getCoordinates();
    if (!aTest.isClosed() && bTest.isClosed()) {
      int end = aTest.getCoordinates().size() - 1;
      if (aCoords.get(end).equals(aCoords.get(0))) {
        // still geometrically closed, so works for equivalence.
        aCoords.remove(aCoords.size() - 1);
        aTest.setClosed(true);
      }
    }
    if (!bTest.isClosed() && aTest.isClosed()) {
      int end = bTest.getCoordinates().size() - 1;
      if (bCoords.get(end).equals(bCoords.get(0))) {
        // still geometrically closed, so works for equivalence.
        bCoords.remove(bCoords.size() - 1);
        bTest.setClosed(true);
        bCoords = bTest.getCoordinates();
      }
    }
    if ((aTest.isClosed() && !bTest.isClosed())
        || (!aTest.isClosed() && bTest.isClosed())) {
      error = "only one of the parts was closed.";
      return false;
    }
    ListComparer lists = new ListComparer();
    if (!aTest.isClosed()) {
      // open parts must either match or match reversed.
      if (lists.equivalent(aCoords, bCoords)) {
        return true;
      }
      Collections.reverse(bCoords);
      return lists.equivalent(a.getCoordinates(), bCoords);
    }
    // they should both be closed here

    // a and b can both cycle cycle.
    List<Integer> possibleStarts = bTest.findAllIndices(aCoords.get(0));
    for (int iStart : possibleStarts) {
      List<Coord> fwd = cycler.forward(bCoords, iStart);
      if (lists.equivalent(aCoords, fwd)) {
        return true;
      }

      List<Coord> back = cycler.backward(bCoords, iStart);
      if (lists.equivalent(aCoords, back)) {
        return true;
      }
    }
    error = "the part coordinates could not be matched.";
    return false;
  }

  /**
   * Gets the error String.
   * @return the error
   */
  @Override
  public final String getError() {
    return error;
  }

}
