/*
 * The MIT License
 *
 * Copyright 2015 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.map;

import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.data.TileUrl;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PointTileFile {

  /**
   * The full path to the tile.
   */
  private String path;
  /**
   * Boolean true if the file exists, false otherwise.
   */
  private boolean present;
  /**
   * The mercator envelope of the tile.
   */
  private Envelope envelopeMercator;

  /**
   * Creates a new PointTileFile.
   *
   * @param url The url.
   */
  public PointTileFile(TileUrl url) {
    path = url.getFilename();
    present = url.exists();
    envelopeMercator = url.getEnvelopeMercator();
  }

  /**
   * Gets the full path to the tile.
   *
   * @return the path
   */
  public final String getPath() {
    return path;
  }

  /**
   * Sets the full path to the tile.
   *
   * @param path the path to set
   */
  public final void setPath(String path) {
    this.path = path;
  }

  /**
   * Gets a boolean that is true if the file exists, false otherwise.
   *
   * @return the present
   */
  public final boolean isPresent() {
    return present;
  }

  /**
   * Sets a boolean that is true if the file exists, false otherwise.
   *
   * @param present the present to set
   */
  public final void setPresent(boolean present) {
    this.present = present;
  }

  /**
   * Gets the mercator envelope of the tile.
   *
   * @return the envelopeMercator
   */
  public final Envelope getEnvelopeMercator() {
    return envelopeMercator;
  }

  /**
   * Sets the mercator envelope of the tile.
   *
   * @param envelopeMercator the envelopeMercator to set
   */
  public final void setEnvelopeMercator(Envelope envelopeMercator) {
    this.envelopeMercator = envelopeMercator;
  }
}
