/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.io.json;

import gov.ca.water.shapelite.utils.ArrayUtils;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class JsonMultiPoint extends JsonGeometryBase<JsonMultiPoint> {

  /**
   * An array of points, each of which can contain several continuous strings of
   * coordinates, each coordinate consisting of two or more doubles.
   */
  private double[][][] points;

  /**
   * Gets the points.
   *
   * @return the points
   */
  public final double[][][] getPoints() {
    return points;
  }

  /**
   * Sets the points.
   *
   * @param paths the points to set
   */
  public final void setPoints(double[][][] paths) {
    this.points = paths;
  }

  /**
   * Returns a copy of the JsonMultiPoint.
   *
   * @return The copy.
   */
  @Override
  public final JsonMultiPoint copy() {
    JsonMultiPoint result = new JsonMultiPoint();
    result.copyProperties(this);
    return result;
  }

  /**
   * Copies all the properties from the other multi-point to this point. If the
   * other geometry is not a Multi-Point object, then only the spatial reference
   * is copied.
   *
   * @param other The other object to copy properties from.
   */
  @Override
  public final void copyProperties(JsonMultiPoint other) {
    super.setSpatialReference(other.getSpatialReference());
    this.points = ArrayUtils.copy3(other.getPoints());
  }
}
