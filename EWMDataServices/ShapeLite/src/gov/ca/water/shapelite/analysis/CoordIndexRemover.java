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
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This works in almost the same way as the enumerator, except that each time it
 * moves to the "next" result, it actually adds the index to a removed set,
 * rather than simply tracking the start index. Has next will return false if
 * the next item has been removed, even if many un-removed coordinates remain
 * after the removed index. A setNextIndex call can be used to continue the
 * process.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CoordIndexRemover implements Iterator<CoordIndex> {

  /**
   * The original part with coordinates to enumerate through.
   */
  private final Part original;

  /**
   * A part made from the remaining coordinates that were not iterated over.
   */
  private final HashSet<Integer> removed;

  /**
   * The current result.
   */
  private CoordIndex current;

  /**
   * The direction, either increasing or decreasing to increment through the
   * original coordinates.
   */
  private IndexDirection direction;

  /**
   * The integer count of how many coordinates were retrieved so far.
   */
  private int count = 0;

  /**
   * This is used only for the first call to the enumeratator, when current is
   * null.
   */
  private int startIndex;

  /**
   * Creates a new instance of the CoordIndexEnumerator. This defaults to moving
   * forward, starting with result 0.
   *
   * @param part The original to enumerate.
   */
  public CoordIndexRemover(@NonNull Part part) {
    this(part, 0, IndexDirection.Increasing);
  }

  /**
   * Creates a new instance of the CoordIndexEnumerator.
   *
   * @param part The original to enumerate.
   * @param startIndex The integer start result of the coordinate to start with.
   */
  public CoordIndexRemover(@NonNull Part part, int startIndex) {
    this(part, startIndex, IndexDirection.Increasing);
  }

  /**
   * Creates a new instance of the CoordIndexEnumerator.
   *
   * @param part The original to enumerate.
   * @param startIndex The integer start result of the coordinate to start with.
   * @param direction if this is increasing, the enumerator will move through
   * the indices in increasing order.
   */
  public CoordIndexRemover(@NonNull Part part, int startIndex,
      IndexDirection direction) {

    if (part == null) {
      throw new IllegalArgumentException("Part cannot be null.");
    }
    if (startIndex < 0 || startIndex > part.getCoordinates().size()) {
      throw new IllegalArgumentException("The specified index is not in the "
          + "range of the part coordinates.");
    }
    this.original = part;
    this.removed = new HashSet<>();
    this.direction = direction;
    this.startIndex = startIndex;
  }

  /**
   * Tests if the original has a coordinate that has not yet been returned.
   *
   * @return Boolean true if there is another coordinate in the sequence.
   */
  @Override
  public final boolean hasNext() {
    if (original.getCoordinates().isEmpty()) {
      return false;
    }
    if (current == null) {
      return true;
    }
    return hasNext(current.getIndex());
  }

  /**
   * Gets the hasNext status for the specified integer next.
   *
   * @param index The current next to test a next value for.
   * @return Booolean, true if the specified next would have a next value.
   */
  final boolean hasNext(int index) {
    if (original.getCoordinates().isEmpty()) {
      return false;
    }
    Optional<Integer> next = nextIndex(index);
    return next.isPresent();
  }

  /**
   * Gets the next CoordIndex in the cycle, moving either towards increasing or
   * decreasing indices. If the original is closed, this will wrap.
   *
   * @return The next CoordIndex.
   */
  @Override
  public final CoordIndex next() {
    current = peekNext();
    removed.add(current.getIndex());
    count++;
    return current;
  }

  /**
   * Gets the next CoordIndex in the cycle, moving either towards increasing or
   * decreasing indices. If the original is closed, this will wrap. This will
   * not increment the enumerator.
   *
   * @return The next CoordIndex.
   */
  public final CoordIndex peekNext() {
    if (!hasNext()) {
      throw new IllegalStateException("Cannot move next because there is no next.");
    }
    if (current == null) {
      current = new CoordIndex(original.getCoordinates().get(startIndex), startIndex);
      return current;
    }
    Optional<Integer> next = nextIndex(current.getIndex());
    CoordIndex result = new CoordIndex(
        original.getCoordinates().get(next.get()), next.get());
    return result;
  }

  /**
   * Gets the integer next Index.
   *
   * @param index the integer current next to get the next from.
   * @return The next next.
   */
  final Optional<Integer> nextIndex(int index) {
    Integer result;
    if (direction == IndexDirection.Increasing) {
      result = index + 1;
      if (result >= original.getCoordinates().size()) {
        if (original.isClosed()) {
          result = 0;
        } else {
          result = null;
        }
      }
    } else {
      result = index - 1;
      if (result < 0) {
        if (original.isClosed()) {
          result = original.getCoordinates().size() - 1;
        } else {
          result = null;
        }
      }
    }
    if (removed.contains(result)) {
      result = null;
    }
    return Optional.ofNullable(result);
  }

  /**
   * Gets the original to enumerate through.
   *
   * @return the original
   */
  public final Part getPart() {
    return original;
  }

  /**
   * Gets the current result.
   *
   * @return the current
   */
  public final CoordIndex getCurrent() {
    return current;
  }

  /**
   * Gets the integer start result.
   *
   * @return the startIndex
   */
  public final int getStartIndex() {
    return startIndex;
  }

  /**
   * Gets the direction, either increasing or decreasing to increment through
   * the original coordinates.
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

  /**
   * This will test each coordinate from the original part against a removed
   * list of indices to determine whether that coordinate should be included in
   * the remainder.
   *
   * @return the remainder Part, or empty if there are no coordinates remaining.
   */
  public final Optional<Part> getRemainder() {
    Part result = null;
    for (CoordIndex item : original) {
      if (!removed.contains(item.getIndex())) {
        if (result == null) {
          result = new Part();
          result.setClosed(original.isClosed());
        }
        result.getCoordinates().add(item.getCoord());
      }
    }
    return Optional.ofNullable(result);
  }

  /**
   * Gets the set of all the indices that have been removed.
   *
   * @return the removed indices.
   */
  public final HashSet<Integer> getRemoved() {
    return removed;
  }

  /**
   * Sets the integer index of the next coordinate. If the series was started,
   * this clears the current index, and sets the start index so that the item
   * returned during the call to next will have the specified index.
   *
   * @param index the integer result to set.
   * @throws IllegalArgumentException if the index is out of bounds, or has
   * already been removed.
   */
  public final void setNextIndex(int index) {
    int max = original.getCoordinates().size() - 1;
    if (index < 0 || index > max) {
      throw new IllegalArgumentException("Index " + index + " was outside the "
          + "valid range from 0 to " + max);
    }
    if (this.removed.contains(index)) {
      throw new IllegalArgumentException("Index " + index + " was already removed.");
    }
    current = null;
    startIndex = index;
  }

  /**
   * Sets the direction that the enumerator shall move.
   *
   * @param direction the direction to set
   */
  public final void setDirection(IndexDirection direction) {
    this.direction = direction;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
