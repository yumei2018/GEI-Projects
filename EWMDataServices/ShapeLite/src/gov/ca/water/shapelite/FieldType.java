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
package gov.ca.water.shapelite;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public enum FieldType {

  /**
   * Character, for String data types.
   */
  Character("C"),
  /**
   * Number, for text representations of numeric values.
   */
  Numeric("N"),
  /**
   * Logical boolean (True/False).
   */
  Logical("L"),
  /**
   * YYYYMMDD formated date. This cannot hold time or timestamp information.
   */
  Date("D");

  /**
   * The string code.
   */
  private final String code;

  /**
   * Creates a new instance of a field type.
   *
   * @param code The String code C, N, L or D.
   */
  private FieldType(String code) {
    this.code = code;
  }

  /**
   * Gets the character code of this Field type.
   *
   * @return the String code C, N, L or D.
   */
  public String getCode() {
    return code;
  }
}
