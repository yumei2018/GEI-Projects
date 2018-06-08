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
 * Enumerates the possible modes that a conic projection can be in. The North
 * and South poles use a flat disk. The Equatorial uses a cylinder. The Oblique
 * uses a cone.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public enum TransformMode {

  /**
   * North Pole disk.
   */
  NorthPole(0),
  /**
   * South Pole disk.
   */
  SouthPole(1),
  /**
   * Equatorial cylinder.
   */
  Equitorial(2),
  /**
   * Oblique (45 degrees) cone.
   */
  Oblique(3);
  /**
   * The integer representation of the mode.
   */
  private final int value;

  /**
   * Creates a new instance of a TransformMode.
   *
   * @param value the integer representation of the mode (0-3).
   */
  private TransformMode(int value) {
    this.value = value;
  }

  /**
   * Gets the TransformMode corresponding to the specified integer value.
   *
   * @param value The value of the mode.
   * @return The TransformMode.
   */
  public static Optional<TransformMode> find(int value) {
    for (TransformMode mode : TransformMode.values()) {
      if (mode.value == value) {
        return Optional.of(mode);
      }
    }
    return Optional.empty();
  }

}
