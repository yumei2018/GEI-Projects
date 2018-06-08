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
package gov.ca.water.shapelite.analysis;

/**
 * Based on
 * http://www.cemyuksel.com/research/catmullrom_param/catmullrom_slides.pdf.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public enum CatmullRomType {

  /**
   * Times used for the algorithm (t1, t2, t3) etc. are uniformly sampled so
   * that t2 = t1 + 1. This can result in loops.
   */
  Uniform,
  /**
   * Times used for the algorithm are adjusted based on the difference of the
   * magnitude between the point values. t2 = t1 + |P2-P1|. This results in very
   * "stiff" arcs that are very unlikely to form loops, but are extremely
   * sensitive to sharp changes, so that a highly local direction shift will
   * affect the whole arc.
   */
  Chordal,
  /**
   * Times used for the algorithm are adjusted based on the square root of the
   * magnitude between the point values t2 = t1 + |P2-P1|^2. This creates a
   * balanced approach that should be less sensitive to looping than the uniform
   * distribution, but also less sensitive to sharp direction changes than
   * Chordal.
   */
  Centripetal
}
