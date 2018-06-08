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

import com.google.gson.Gson;
import gov.ca.water.shapelite.utils.ArrayUtils;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class JsonPolygon extends JsonGeometryBase<JsonPolygon> {

  /**
   * An array of paths, each of which can contain several continuous strings of
   * coordinates, each coordinate consisting of two or more doubles.
   */
  private double[][][] rings;

  /**
   * Creates a new instance of a JsonPolygon from the specified JSON string.
   *
   * @param jsonString The String representation of the polygon.
   */
  public JsonPolygon(String jsonString) {
    Gson gson = new Gson();
    JsonPolygon innerPolygon = gson.fromJson(jsonString, JsonPolygon.class);
    this.rings = innerPolygon.rings;
    this.setSpatialReference(innerPolygon.getSpatialReference());
  }

  /**
   * Creates a new empty polygon with null for the rings and spatial reference.
   */
  public JsonPolygon() {

  }

  /**
   * Gets the paths.
   *
   * @return the paths
   */
  public final double[][][] getRings() {
    return rings;
  }

  /**
   * Sets the paths.
   *
   * @param paths the paths to set
   */
  public final void setRings(double[][][] paths) {
    this.rings = paths;
  }

  /**
   * Returns a copy of this JsonPolygon.
   *
   * @return JsonPolygon copy.
   */
  @Override
  public final JsonPolygon copy() {
    JsonPolygon result = new JsonPolygon();
    result.copyProperties(this);
    return result;
  }

  /**
   * Copies the properties of the other geometry to this polygon if the other
   * geometry is also a polygon. Otherwise, this only copies the spatial
   * reference information.
   *
   * @param other The other geometry to copy properties from.
   */
  @Override
  public final void copyProperties(JsonPolygon other) {
    super.setSpatialReference(other.getSpatialReference());
    this.rings = ArrayUtils.copy3(other.getRings());

  }
}
