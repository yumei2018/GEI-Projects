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
public abstract class TileUrlFormat {

  /**
   * The string base url with no scale, tileX or tileY component.
   */
  private String baseUrl;

  /**
   * The string extension of the image files, like ".png".
   */
  private String extension;

  /**
   * Gets the url.
   * @param x The integer X from 0 to level^2.
   * @param y The integer Y from 0 to level^2.
   * @param level The integer scale from 0 to 18.
   * @return The url combining the base path with the x, y and level.
   */
  public abstract String getUrl(int x, int y, int level);

  /**
   * Gets the string base url with no scale, tileX or tileY component.
   * @return the baseUrl
   */
  public final String getBaseUrl() {
    return baseUrl;
  }

  /**
   * Sets the string base url with no scale, tileX or tileY component.
   * @param baseUrl the baseUrl to set
   */
  public final void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  /**
   * Gets the string extension of the image files, like ".png".
   * @return the extension
   */
  public final String getExtension() {
    return extension;
  }

  /**
   * Sets the string extension of the image files, like ".png".
   * @param extension the extension to set
   */
  public final void setExtension(String extension) {
    this.extension = extension;
  }
}
