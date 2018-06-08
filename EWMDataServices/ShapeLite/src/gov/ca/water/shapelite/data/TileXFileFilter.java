/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
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

import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Nullable;
import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TileXFileFilter implements FileFilter {

  /**
   * The integer level.
   */
  private final int level;

  /**
   * The Envelope bounds to use.
   */
  private final Envelope envelopeMercator;

  /**
   * Creates a new instance of a file filter that will narrow folders to the
   * values within the specified TedMercator envelope.
   *
   * @param level The integer level from 0 to about 19.
   * @param envelopeMercator The envelope. If this is null or empty, then no
   * filtering takes place. If it is null, the envelope on this object will be
   * an empty envelope instead.
   */
  public TileXFileFilter(int level, @Nullable Envelope envelopeMercator) {
    this.level = level;
    if (envelopeMercator == null) {
      this.envelopeMercator = new DefaultEnvelope();
    } else {
      this.envelopeMercator = envelopeMercator;
    }
  }

  /**
   * Controls whether or not the folder is accepted based on the pre-set scale,
   * and envelope.
   *
   * @param pathname The String path name, which should end in the X folder
   * name.
   * @return boolean, true if the file should be considered.
   */
  @Override
  public final boolean accept(File pathname) {
    if (pathname.isFile()) {
      return false;
    }
    try {
      int tileX = Integer.parseInt(pathname.getName());
      if (envelopeMercator.isEmpty()) {
        return true;
      }
      Envelope bounds = TileUrl.getTileEnvelopeMercator(level, tileX, 0);
      if (bounds.getMin().getX() > envelopeMercator.getMax().getX()) {
        return false;
      }
      return bounds.getMax().getX() >= envelopeMercator.getMin().getX();
    } catch (Exception ex) {
      return false;
    }

  }

  /**
   * Gets the integer zoom level of the tiles, usually from 0 to 20.
   *
   * @return the level
   */
  public final int getLevel() {
    return level;
  }

  /**
   * Gets the Envelope bounds to use.
   *
   * @return the envelopeMercator
   */
  public final Envelope getEnvelopeMercator() {
    return envelopeMercator;
  }

}
