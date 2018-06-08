/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
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
package gov.ca.water.shapelite.analysis;

import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Part;
import java.util.Iterator;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CoordIndexEnumerator implements Iterator<CoordIndex> {

  /**
   * The part to enumerate through.
   */
  private final Part part;

  /**
   * The current index.
   */
  private CoordIndex current;

  /**
   * The integer start index.
   */
  private final int startIndex;

  /**
   * The direction, either increasing or decreasing to increment through the
   * part coordinates.
   */
  private final IndexDirection direction;

  /**
   * The integer count of how many coordinates were retrieved so far.
   */
  private int count = 0;

  /**
   * Creates a new instance of the CoordIndexEnumerator. This defaults to moving
   * forward, starting with index 0.
   *
   * @param part The part to enumerate.
   */
  public CoordIndexEnumerator(@NonNull Part part) {
    this(part, 0, IndexDirection.Increasing);
  }

  /**
   * Creates a new instance of the CoordIndexEnumerator.
   *
   * @param part The part to enumerate.
   * @param startIndex The integer start index of the coordinate to start with.
   */
  public CoordIndexEnumerator(@NonNull Part part, int startIndex) {
    this(part, startIndex, IndexDirection.Increasing);
  }

  /**
   * Creates a new instance of the CoordIndexEnumerator.
   *
   * @param part The part to enumerate.
   * @param startIndex The integer start index of the coordinate to start with.
   * @param direction if this is increasing, the enumerator will move through
   * the indices in increasing order.
   */
  public CoordIndexEnumerator(@NonNull Part part, int startIndex,
      IndexDirection direction) {

    if (part == null) {
      throw new IllegalArgumentException("Part cannot be null.");
    }
    if (startIndex < 0 || startIndex >= part.getCoordinates().size()) {
      throw new IllegalArgumentException("The specified index is not in the "
          + "range of the part coordinates.");
    }
    this.startIndex = startIndex;
    this.part = part;
    this.direction = direction;
  }

  /**
   * Tests if the part has a coordinate that has not yet been returned.
   *
   * @return Boolean true if there is another coordinate in the sequence.
   */
  @Override
  public final boolean hasNext() {
    if (part.getCoordinates().isEmpty()) {
      return false;
    }
    if (current == null) {
      return true;
    }
    int next = current.getIndex();
    if (direction == IndexDirection.Increasing) {
      next++;
    } else {
      next--;
    }
    if (part.isClosed()) {

      if (next >= part.getCoordinates().size()) {
        next = 0;
      }
      if (next < 0) {
        next = part.getCoordinates().size() - 1;
      }
      if (next == startIndex) {
        return false;
      }
    } else {
      if (next >= part.getCoordinates().size() || next < 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * Gets the next CoordIndex in the cycle, moving either towards increasing or
   * decreasing indices. If the part is closed, this will wrap.
   *
   * @return The next CoordIndex.
   */
  @Override
  public final CoordIndex next() {
    current = peekNext();
    count++;
    return current;
  }

  /**
   * Gets the next CoordIndex in the cycle, moving either towards increasing or
   * decreasing indices. If the part is closed, this will wrap. This will not
   * increment the enumerator.
   *
   * @return The next CoordIndex.
   */
  public final CoordIndex peekNext() {
    if (!hasNext()) {
      throw new IllegalStateException("Cannot move next because there is no next.");
    }
    if (current == null) {
      current = new CoordIndex(part.getCoordinates().get(startIndex), startIndex);
      return current;
    }

    int index;
    if (direction == IndexDirection.Increasing) {
      index = current.getIndex() + 1;
      if (part.isClosed()) {

        if (index == part.getCoordinates().size()) {
          index = 0;
        }

      }
    } else {
      index = current.getIndex() - 1;
      if (part.isClosed()) {

        if (index == -1) {
          index = part.getCoordinates().size() - 1;
        }
      }
    }
    CoordIndex next = new CoordIndex(part.getCoordinates().get(index), index);
    return next;
  }

  /**
   * Gets the part to enumerate through.
   *
   * @return the part
   */
  public final Part getPart() {
    return part;
  }

  /**
   * Gets the current index.
   *
   * @return the current
   */
  public final CoordIndex getCurrent() {
    return current;
  }

  /**
   * Gets the integer start index.
   *
   * @return the startIndex
   */
  public final int getStartIndex() {
    return startIndex;
  }

  /**
   * Gets the direction, either increasing or decreasing to increment through
   * the part coordinates.
   *
   * @return the direction
   */
  public final IndexDirection getDirection() {
    return direction;
  }

  /**
   * Gets the integer count of how many coordinates were retrieved so far.
   *
   * @return the count
   */
  public final int getCount() {
    return count;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
