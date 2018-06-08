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
package gov.ca.water.shapelite.symbology;

import java.awt.image.BufferedImage;
import gov.ca.water.shapelite.symbology.gui.Symbolizeable;
import java.io.Serializable;

/**
 * The Symbolizer is used as the base class for symbolizers, even though there
 * are no shared properties. This allows for generic layers to give some
 * narrowing to what kind of objects can be expected for the symbolizer.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface Symbolizer extends Serializable {

  /**
   * Creates a representation from this symbolizer, suitable for the legend.
   *
   * @return a new BufferedImage.
   */
  BufferedImage getLegendIcon();


}
