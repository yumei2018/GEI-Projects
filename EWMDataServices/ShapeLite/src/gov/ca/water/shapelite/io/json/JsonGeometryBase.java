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

/**
 * This class is the base class for all geometries and has common fields.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 * @param <T> The type of the geometry.
 */
public abstract class JsonGeometryBase<T extends JsonGeometry<?>>
    implements JsonGeometry<T> {

  /**
   * The spatial reference object. This can be null.
   */
  private SpatialReference spatialReference;

  /**
   * Gets the spatial reference object. This can be null.
   *
   * @return the spatialReference
   */
  @Override
  public final SpatialReference getSpatialReference() {
    return spatialReference;
  }

  /**
   * SEts the spatial reference object. This can be null.
   *
   * @param spatialReference the spatialReference to set
   */
  @Override
  public final void setSpatialReference(SpatialReference spatialReference) {
    this.spatialReference = spatialReference;
  }

  /**
   * Creates a Json string representation of this object.
   *
   * @return The String.
   */
  @Override
  public final String toString() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }




}
