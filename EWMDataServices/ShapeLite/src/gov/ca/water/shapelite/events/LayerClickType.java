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
package gov.ca.water.shapelite.events;

/**
 * A class for clarifying the clicks.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public enum LayerClickType {

  /**
   * No clicks.
   */
  CONTEXT(0),
  /**
   * A single click.
   */
  SINGLE(1),
  /**
   * A double click.
   */
  DOUBLE(2);
  /**
   * The integer number of clicks.
   */
  private final int clicks;

  /**
   * Creates a new instance of the LayerClickType enumeration.
   *
   * @param clicks The integer number of clicks.
   */
  LayerClickType(int clicks) {
    this.clicks = clicks;
  }

  /**
   * Gets the clicks from the click type.
   *
   * @return the integer number of clicks.
   */
  public int getClicks() {
    return clicks;
  }

  /**
   * Gets a LayerCLickType from an integer number of clicks.
   *
   * @param clicks The integer number of clicks.
   * @return the LayerClickType.
   */
  public static LayerClickType fromInteger(int clicks) {
    switch (clicks) {
      case 0:
        return CONTEXT;
      case 1:
        return SINGLE;
      default:
        return DOUBLE;
    }
  }
}
