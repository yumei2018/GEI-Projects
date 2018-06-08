/*
 * The MIT License
 *
 * Copyright 2012 hdunsford.
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
 * An Exception that occurs while reading or writing shapefiles.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShapefileException extends Exception {

  /**
   * Creates a new instance of the ShapefileException class.
   *
   * @param message The string message.
   */
  public ShapefileException(String message) {
    super(message);
  }

  /**
   * Creates a new instance of the ShapefileException class.
   *
   * @param cause the original exception that this exception wraps.
   */
  public ShapefileException(Throwable cause) {
    super(cause);
  }

  /**
   * Creates a new instance of the ShapefileException class.
   *
   * @param message The string message for this exception.
   * @param cause The original cause.
   */
  public ShapefileException(String message, Throwable cause) {
    super(message, cause);
  }
}