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
 * An Exception that is thrown in the case where an argument is missing.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MissingArgumentException extends Exception {

  /**
   * Creates a new instance of <code>MissingArgumentException</code> without a
   * detail message.
   */
  public MissingArgumentException() {
  }

  /**
   * Constructs an instance of <code>MissingArgumentException</code> with the
   * specified detail message.
   *
   * @param message the detail message.
   */
  public MissingArgumentException(String message) {
    super(message);
  }

  /**
   * Constructs an instance of <code>MissingArgumentException</code> with the
   * specified <code>Throwable</code> cause.
   *
   * @param cause the original source of the message.
   */
  public MissingArgumentException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs an instance of <code>MissingArgumentException</code> with the
   * specified message and <code>Throwable</code> cause.
   *
   * @param message the detail message.
   * @param cause the original source of the message.
   */
  public MissingArgumentException(String message, Throwable cause) {
    super(cause);
  }

}
