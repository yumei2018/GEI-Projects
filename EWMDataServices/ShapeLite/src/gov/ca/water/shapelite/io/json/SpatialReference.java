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

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class SpatialReference {

  /**
   * The well known id for the coordinate system.
   */
  private int wkid;
  /**
   * The well known id for the coordinate system.
   */
  private int latestWkid;

  /**
   * The ESRI well known text. (This is the same as the text string that occurs
   * in the .prj files.)
   */
  private String wkt;
  /**
   * Creates a new SpatialReference with the specified wkid.
   *
   * @param wkid The wkid.
   */
  public SpatialReference(int wkid) {
    this.wkid = wkid;
  }

  /**
   * The well known ID for the projection.
   *
   * @return the wkid
   */
  public final int getWkid() {
    return wkid;
  }

  /**
   * The well known ID for the projection.
   *
   * @param wkid the wkid to set
   */
  public final void setWkid(int wkid) {
    this.wkid = wkid;
  }
  /**
   * Identifies the current wkid value associated with the same spatial
   * reference. For example a WKID of '102100' (Web Mercator) has a latestWKid
   * of '3857'
   *
   * @return the latestWkid
   */
  public int getLatestWkid() {
    return latestWkid;
  }

  /**
   * Identifies the current wkid value associated with the same spatial
   * reference. For example a WKID of '102100' (Web Mercator) has a latestWKid
   * of '3857'
   *
   * @param latestWkid the latestWkid to set
   */
  public void setLatestWkid(int latestWkid) {
    this.latestWkid = latestWkid;
  }

  /**
   * Gets the ESRI well known text. (This is the same as the text string that
   * occurs in the .prj files.)
   *
   * @return the wkt
   */
  public String getWkt() {
    return wkt;
  }

  /**
   * Sets the ESRI well known text. (This is the same as the text string that
   * occurs in the .prj files.)
   *
   * @param wkt the wkt to set
   */
  public void setWkt(String wkt) {
    this.wkt = wkt;
  }
}
