/*
 * The MIT License
 *
 * Copyright 2013 hdunsford.
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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ByteSizes {

  /**
   * An array list to store byte sizes.
   */
  private final List<Integer> sizes;

  /**
   * Creates a new instance of the ByteSizes array.
   */
  public ByteSizes() {
    sizes = new ArrayList<>();
  }

  /**
   *
   * @param index
   * @return
   */
  public int getOffset(int index) {
    int result = 100;
    for (int i = 0; i < index; i++) {
      result += getSizes().get(i);
    }
    return result;
  }

  /**
   *
   * @return
   */
  public int getTotalLength() {
    int result = 100;
    for (Integer size : getSizes()) {
      result += size;
    }
    return result;
  }

  /**
   * @return the sizes
   */
  public List<Integer> getSizes() {
    return sizes;
  }

  /**
   * Gets the last size in the list, or null if the list is undefined
   *
   * @return
   */
  public Integer getLast() {
    return this.sizes.get(sizes.size() - 1);
  }
}
