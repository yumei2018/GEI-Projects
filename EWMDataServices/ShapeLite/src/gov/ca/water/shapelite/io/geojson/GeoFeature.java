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
package gov.ca.water.shapelite.io.geojson;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GeoFeature extends GeoObject {

  /**
   * The version ID for this serializable object. Increment this if the fields
   * are modified.
   */
  static final long serialVersionUID = 1L;


  /**
   * A bounding box object that is optionally available.
   */
  private double[] bbox;


  /**
   * The geometry object, containing geometry type information and the
   * coordinates.
   */
  private GeoGeometry geometry;


  /**
   * Gets the GeoGeometry for the coordinates..
   * @return the geometry
   */
  public final GeoGeometry getGeometry() {
    return geometry;
  }

  /**
   * Sets the GeoGeometry for the coordinates.
   * @param geometry the geometry to set
   */
  public final void setGeometry(GeoGeometry geometry) {
    this.geometry = geometry;
  }

  /**
   * Gets A bounding box object that is optionally available.
   * @return the bbox
   */
  public final double[] getBbox() {
    return bbox;
  }

  /**
   * Sets A bounding box object that is optionally available.
   * @param bbox the bbox to set
   */
  public final void setBbox(double[] bbox) {
    this.bbox = bbox;
  }
}
