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

import gov.ca.water.shapelite.Envelope;

/**
 * This class stores information defining a tile being downloaded, including the
 * file path of the cached image, the url to download from, the format of the
 * downloaded image, and the bounds of the image.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TilePath implements Comparable<TilePath> {

  /**
   * Part of the hash code calculation.
   */
  public static final int HASH_OFFSET = 5;

  /**
   * A multiplier for the hash code calculation.
   */
  public static final int HASH_STEP = 13;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The geographic bounds of the tile in Mercator coordinates.
   */
  private Envelope bounds;

  /**
   * The filePath where a copy of the downloaded image will be cached.
   */
  private String filePath;

  /**
   * The format describing how the url is formatted.
   */
  private String format;

  /**
   * A boolean that is true if this tile should be considered low priority.
   */
  private boolean lowPriority;

  /**
   * The original url on the web where the image is found.
   */
  private String url;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * This allows for organizing the tile paths based on level, y and then z
   * order. Therefore lower level tiles come first, then on the same level, top
   * tiles come first, and then on the same row, left tiles come first.
   *
   * @param o The other TilePath to compare to.
   * @return
   * <ul>
   * <li>-1 if this tile comes before the other tile</li>
   * <li>0 if this tile is in the same location as the other tile<li>
   * <li>1 if this tile is above the other tile</li>
   * </ul>
   */
  @Override
  public final int compareTo(TilePath o) {
    if (getBounds().getWidth() != o.getBounds().getWidth()) {
      Double wo = o.getBounds().getWidth();
      Double w = getBounds().getWidth();
      return wo.compareTo(w);
    }
    if (o.getBounds().getMin().getY() != getBounds().getMin().getY()) {
      Double yo = o.getBounds().getMin().getY();
      Double y = getBounds().getMin().getY();
      return yo.compareTo(y);
    }
    Double xo = o.getBounds().getMin().getX();
    Double x = getBounds().getMin().getX();
    return x.compareTo(xo);
  }

  /**
   * Preferentially use the url for testing equality.
   *
   * @param other The other object to test for equality with this object.
   * @return Boolean that is true if the url is the same.
   */
  @Override
  public final boolean equals(Object other) {
    if (other instanceof TilePath) {
      TilePath tp = (TilePath) other;
      if (getUrl() != null) {
        return getUrl().equals(tp.getUrl());
      }
    }
    return super.equals(other);
  }

  /**
   * This is used anytime equals is overridden.
   *
   * @return An integer representing the hash code for this object.
   */
  @Override
  public final int hashCode() {
    int hash = HASH_OFFSET;
    hash += HASH_STEP * hash;
    if (this.getUrl() != null) {
      hash *= this.getUrl().hashCode();
    }
    return hash;
  }

  //</editor-fold>
  /**
   * Gets the geographic bounds of the tile in Mercator coordinates.
   *
   * @return the bounds
   */
  public final Envelope getBounds() {
    return bounds;
  }

  /**
   * Sets the geographic bounds of the tile in Mercator coordinates.
   *
   * @param bounds the bounds to set
   */
  public final void setBounds(Envelope bounds) {
    this.bounds = bounds;
  }

  /**
   * Gets the filePath where a copy of the downloaded image will be cached.
   *
   * @return the filePath
   */
  public final String getFilePath() {
    return filePath;
  }

  /**
   * Sets the filePath where a copy of the downloaded image will be cached.
   *
   * @param filePath the filePath to set
   */
  public final void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Gets the format describing how the url is formatted.
   *
   * @return the format
   */
  public final String getFormat() {
    return format;
  }

  /**
   * Sets the format describing how the url is formatted.
   *
   * @param format the format to set
   */
  public final void setFormat(String format) {
    this.format = format;
  }

  /**
   * Gets a boolean that is true if this tile should be considered low priority.
   *
   * @return the lowPriority
   */
  public final boolean isLowPriority() {
    return lowPriority;
  }

  /**
   * Sets a boolean that is true if this tile should be considered low priority.
   *
   * @param lowPriority the lowPriority to set
   */
  public final void setLowPriority(boolean lowPriority) {
    this.lowPriority = lowPriority;
  }

  /**
   * Gets the original url on the web where the image is found.
   *
   * @return the url.
   */
  public final String getUrl() {
    return url;
  }

  /**
   * Sets the original url on the web where the image is found.
   *
   * @param url the url to set.
   */
  public final void setUrl(String url) {
    this.url = url;
  }

}
