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
package gov.ca.water.shapelite.projection;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DigitValue {

  /**
   * The digits to preserve.
   */
  private int digits;
  /**
   * The value.
   */
  private Double value;

  /**
   * Creates a new instance of a DigitValue class.
   */
  public DigitValue() {
    digits = -1;
  }

  /**
   * Creates a new instance of a DigitValue class.
   *
   * @param value The double precision value for this class.
   */
  public DigitValue(Double value) {
    digits = -1;
    this.value = value;
  }

  /**
   * Creates a new instance of a DigitValue class.
   *
   * @param value The double precision value for this class.
   * @param digits The digits past the decimal to preserve.
   */
  public DigitValue(Double value, int digits) {
    this.digits = digits;
    this.value = value;
  }

  /**
   * Gets the digits to preserve.
   *
   * @return the digits
   */
  public final int getDigits() {
    return digits;
  }

  /**
   * Sets the digits to preserve.
   *
   * @param digits the digits to set
   */
  public final void setDigits(int digits) {
    this.digits = digits;
  }

  /**
   * Gets the Double value for this object.
   *
   * @return the value
   */
  public final Double getValue() {
    return value;
  }

  /**
   * Sets the Double value for this object.
   *
   * @param value the value to set
   */
  public final void setValue(Double value) {
    this.value = value;
  }

}
