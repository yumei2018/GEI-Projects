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
package gov.ca.water.shapelite.data;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TileUrlFormatGoogleMap extends TileUrlFormat {

  /**
   * Creates a tile url format for google.
   */
  public TileUrlFormatGoogleMap() {
    this.setBaseUrl("http://mt3.google.com");
  }

   /**
    * @{inheritdoc}.
    * @return The String url.
    */
  @Override
  public final String getUrl(int x, int y, int level) {
    //return this.getBaseUrl() + "/vt/v=w2.97&x={" + x + "}&y={" + y + "}&z={0}" +level;
    return "http://mt3.google.com/vt/v=w2p.111&hl=en&x=" + x + "&y=" + y + "&z=" + level;
  }

}
