/*
 * The MIT License
 *
 * Copyright 2016 rmarquez.
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
package gov.ca.water.shapelite.legend.internal;

/**
 * For use with LegendPanel for populating legend items with a custom legend item.
 * For API usage, have the Layer implement this class.
 * @since Shapelite 4.1
 * @author rmarquez
 */
public interface LegendItemSource {


  /**
   * LegendItem getter.
   * <p>
   * Is used by LegendPanel for populating the legendItem list with
   * an extended LegendItem.  Examples of extended legend items includes
   * ColormapLegendItem.
   * </p>
   *
   * <p>
   * If null is returned, then a regular LegendItem instance is created and used.
   * </p>
   * <p>
   * For usage, have a custom layer implement this interface.
   * </p>
   * @see LegendItem
   * @see ColormapLegendItem
   * @see Layer
   * @see LegendPanel
   * @return LegendItem A class implementing or extending LegendItem.
   */
  public LegendItem getLegendItem();


}
