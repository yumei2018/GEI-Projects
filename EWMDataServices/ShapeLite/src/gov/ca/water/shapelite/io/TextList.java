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
package gov.ca.water.shapelite.io;

import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.OptionalString;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TextList {

  private List<String> lines;
  private int currentIndex;

  /**
   * Creates a new container to keep track of what line we are reading in the
   * list of strings.
   *
   * @param lines
   */
  public TextList(List<String> lines) {
    this.lines = lines;
    this.currentIndex = -1;
  }

  /**
   * Gets the next line, and increments the current index.
   *
   * @return
   */
  public OptionalString next() {
    currentIndex++;

    return current();
  }

  /**
   * Gets the previous line item, and decrements the index by one line.
   *
   * @return
   */
  public OptionalString previous() {
    currentIndex--;
    return current();
  }

  /**
   * Gets the current index. This is null if the text list has not been used
   * yet.
   *
   * @return The optional String.  This will return empty if the current index
   * is outside the bounds of lines.
   */
  public OptionalString current() {
    if (currentIndex >= lines.size()) {
      return OptionalString.empty();
    }
    if (currentIndex < 0) {
      return OptionalString.empty();
    }
    return OptionalString.of(lines.get(currentIndex));
  }

  /**
   * Gets whether or not this list has another entry.
   *
   * @return
   */
  public boolean hasNext() {
    return currentIndex < lines.size() - 1;
  }

  /**
   * @return the lines
   */
  public List<String> getLines() {
    return lines;
  }

  /**
   * @param lines the lines to set
   */
  public void setLines(List<String> lines) {
    this.lines = lines;
  }

  /**
   * @return the currentIndex
   */
  public int getCurrentIndex() {
    return currentIndex;
  }

  /**
   * @param currentIndex the currentIndex to set
   */
  public void setCurrentIndex(int currentIndex) {
    this.currentIndex = currentIndex;
  }
}
