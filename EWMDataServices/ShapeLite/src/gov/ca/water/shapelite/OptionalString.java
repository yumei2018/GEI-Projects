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
package gov.ca.water.shapelite;

/**
 * An OptionalString implementation of the Optional type.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class OptionalString {

  /**
   * The nullable string value.
   */
  private final String value;

  /**
   * Creates a new instance of an OptionalString.
   *
   * @param item The String item that could be null or empty.
   */
  private OptionalString(String item) {
    value = clean(item);
  }

  /**
   * Removes any whitespace, and converts empty strings to null.
   *
   * @param item A nullable item.
   * @return A cleaned string.
   */
  private static String clean(@Nullable String item) {
    if (item == null) {
      return null;
    }
    //item = item.trim();
    if (item.isEmpty()) {
      return null;
    }
    return item;
  }

  /**
   * Gets the string that is present, or else throws an exception if the string
   * is null.
   *
   * @return The String value.
   */
  public String get() {
    if (value == null) {
      throw new IllegalStateException("The value was null.");
    }
    return value;
  }

  /**
   * Gets a boolean that is true if the string is not null.
   *
   * @return A boolean that is true if the string is not null.
   */
  public boolean isPresent() {
    return value != null;
  }

  /**
   * Returns an empty Optional instance.
   *
   * @return The empty optional.
   */
  public static OptionalString empty() {
    return new OptionalString(null);
  }

  /**
   * Creates a new optional from the specified item, where the item must not be
   * null. If it is null, this will throw an IllegalArgumentException.
   *
   * @param item The immutable item for this optional of type T. T cannot be
   * null.
   * @return The Optional&lt;T&gt;
   */
  public static OptionalString of(final String item) {
    if (item == null ) {
      throw new IllegalArgumentException("Use ofNullable if item can be null.");
    }
//    if (item.isEmpty()) {
//      throw new IllegalArgumentException("Use ofNullable if item can be empty.");
//    }
    return new OptionalString(item);
  }

  /**
   * This will either return the value, or else the alternate.
   *
   * @param alternate The alternate string to use if this value is null.
   * @return The string.
   */
  public String orElse(String alternate) {
    if (value == null) {
      return alternate;
    }
    return value;
  }

  /**
   * Returns an Optional describing the specified value, if non-null, otherwise
   * returns an empty Optional.
   *
   * @param item A String item that can be null or empty.
   * @return An Optional of type T, or an Optional where the item is absent.
   */
  public static OptionalString ofNullable(String item) {
    return new OptionalString(item);
  }

}
