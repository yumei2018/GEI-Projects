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
public class JsonLine extends JsonGeometryBase<JsonLine> {

  /**
   * An array of paths, each of which can contain several continuous strings of
   * coordinates, each coordinate consisting of two or more doubles.
   */
  private double[][][] paths;

  /**
   * Gets the paths.
   *
   * @return the paths
   */
  public final double[][][] getPaths() {
    return paths;
  }

  /**
   * Sets the paths.
   *
   * @param paths the paths to set
   */
  public final void setPaths(double[][][] paths) {
    this.paths = paths;
  }

  /**
   * Returns a JsonLine that is the duplicate of the other line.
   *
   * @return
   */
  @Override
  public final JsonLine copy() {
    JsonLine result = new JsonLine();
    result.copyProperties(this);
    return result;
  }

  /**
   * Copies the geometry properties from the other line. If the other geometry
   * is not a line, this only copies the shared properties.
   *
   * @param other The other line.
   */
  @Override
  public final void copyProperties(JsonLine other) {
    super.setSpatialReference(other.getSpatialReference());
    paths = ArrayUtils.copy3(other.getPaths());
  }
}
