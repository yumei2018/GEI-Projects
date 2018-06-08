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
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class OptionalDouble extends Optional<Double> {

  /**
   * Creates a new instance of an OptionalDouble that works like an
   * OptionalDouble but extends the "IsPresent" criteria to include NaN.
   *
   * @param value The value to test.
   */
  private OptionalDouble(@Nullable Double value) {
    super(value);
  }

  /**
   * Tests if the value is both non-null and not NaN.
   *
   * @return A boolean that is true if this Double value is both non null and
   * not NaN.
   */
  @Override
  public boolean isPresent() {
    return !(super.getItem() == null || super.getItem().isNaN());
  }

  /**
   * Creates a new optional from the specified item, where the item must not be
   * null. If it is null, this will throw an IllegalArgumentException.
   *
   * @param item The immutable item for this optional of type T. T cannot be
   * null.
   * @return The Optional&lt;T&gt;
   */
  public static Optional<Double> of(final Double item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    return new Optional<>(item);
  }

  /**
   * Returns an Optional describing the specified value, if non-null, otherwise
   * returns an empty Optional.
   *
   * @param <T> The type of the item in the nullable optional to be returned.
   * @param item An item of type T that can be null.
   * @return An Optional of type T, or an Optional where the item is absent.
   */
  public static <T> Optional<T> ofNullable(T item) {
    return new Optional<>(item);
  }
}
