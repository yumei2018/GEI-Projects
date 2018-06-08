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
package gov.ca.water.shapelite.envelope;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Nullable;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface ExpandableEnvelope {
/**
   * Expands this envelope to include the specified coordinate.
   *
   * @param coordinate The coordinate to include. If this is null or empty then
   * this will do nothing.
   */
  void expandToInclude(@Nullable Coord coordinate);

  /**
   * Expands this envelope to include the specified coordinate.
   *
   * @param coordinate The coordinate to expand to include. This can be null or
   * empty, in which case, this does nothing.
   * @param hasM Boolean true if the bounds considers M values.
   * @param hasZ Boolean true if the bounds considers Z values.
   */
  void expandToInclude(@Nullable Coord coordinate,
      boolean hasM, boolean hasZ);

  /**
   * This will expand the current box in any direction to match the specified
   * box.
   *
   * @param other the other envelope to expand to include. If this is null or
   * empty, then this method does nothing.
   */
  void expandToInclude(@Nullable Envelope other);

  /**
   * This will expand the current box in any direction to match the specified
   * box.
   *
   * @param other The rectangle to expand to include. If other is null or empty
   * then this does nothing.
   */
  void expandToInclude(@Nullable Rectangle2D other);
}
