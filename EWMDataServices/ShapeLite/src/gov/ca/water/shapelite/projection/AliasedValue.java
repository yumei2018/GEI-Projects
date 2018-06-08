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
package gov.ca.water.shapelite.projection;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class AliasedValue {

  /**
   * The actual value of the parameter.
   */
  private final double value;
  /**
   * The specific string parameter name actually found.
   */
  private final String alias;

  /**
   * The digits of precision past the decimal.
   */
  private final int digits;

  /**
   * Creates a new instance of an aliased value return object.
   *
   * @param alias The alias string found.
   * @param value The actual value used.
   */
  public AliasedValue(String alias, double value) {
    this.value = value;
    this.alias = alias;
    this.digits = -1;
  }

  /**
   * Creates a new instance of an aliased value return object.
   *
   * @param alias The alias string found.
   * @param digValue The actual value used and digits past the decimal to keep.
   */
  public AliasedValue(String alias, DigitValue digValue) {
    this.value = digValue.getValue();
    this.digits = digValue.getDigits();
    this.alias = alias;
  }

  /**
   * Creates a new instance of an aliased value return object.
   *
   * @param alias The alias string found.
   * @param value The actual value used.
   * @param digits The number of digits to preserve past the decimal.
   */
  public AliasedValue(String alias, double value, int digits) {
    this.value = value;
    this.alias = alias;
    this.digits = digits;
  }

  /**
   * Sets the actual value of the parameter.
   *
   * @return the value
   */
  public final double getValue() {
    return value;
  }

  /**
   * Gets the specific string parameter name actually found.
   *
   * @return the alias
   */
  public final String getAlias() {
    return alias;
  }

  /**
   * Gets The digits of precision past the decimal.
   * @return the digits
   */
  public final int getDigits() {
    return digits;
  }

}
