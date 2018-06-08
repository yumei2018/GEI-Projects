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
 * This class shows the possible end-point cases for finding a vertex on a
 * segment.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public enum EndPointInteraction {
  /**
   * No interaction was established.
   */
  None,
  /**
   * The vertex found is on the line segment and between P1 and P2.
   */
  OnLine,
  /**
   * The vertex found is beyond the end of P1.
   */
  PastP1,
  /**
   * The vertex found is beyond the end of P2.
   */
  PastP2,
  /**
   * P1 equals P2 so the segment cannot be extended into an infinite line the
   * closest vertex is P1/P2.
   */
  P1equalsP2
}
