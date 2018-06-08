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

/**
 * An interface defining a consistent contract for comparing items.
 * @param <T> The type of the items to be compared.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface EqualityComparer<T> {


  /**
   * Given two instances of T, this interface provides a boolean value that is
   * true if the two instances can be considered equivalent, even if they are
   * not the same object, and even if they do not explicitly override the equals
   * functionality. This is useful for unit testing.
   *
   * @param a The first item to compare.
   * @param b The second item to compare.
   * @return A boolean that is true if the two are equivalent.
   */
  boolean equivalent(T a, T b);

  /**
   * Gets a String describing the last error that occurred.
   * @return The error String.
   */
  String getError();

}
