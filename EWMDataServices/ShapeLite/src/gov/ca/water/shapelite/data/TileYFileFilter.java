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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TileYFileFilter implements FileFilter {

  /**
   * The integer level.
   */
  private final int level;

  /**
   * The integer tileX column position.
   */
  private final int tileX;

  /**
   * The Envelope bounds to use.
   */
  private final Envelope envelopeMercator;

  /**
   * The String list of extensions which should be accepted. If none are
   * specified then no extension filtering takes place.
   */
  private final List<String> validExtensions;

  /**
   * Creates a new instance of a file filter that will narrow folders to the
   * values within the specified TedMercator envelope.
   *
   * @param level The integer level from 0 to about 19.
   * @param tileX The integer tile X offset.
   * @param envelopeMercator The envelope. If this is null or empty, then no
   * filtering takes place. If it is null, the envelope on this object will be
   * an empty envelope instead.
   */
  public TileYFileFilter(int level, int tileX, @Nullable Envelope envelopeMercator) {
    this.level = level;
    this.tileX = tileX;
    if (envelopeMercator == null) {
      this.envelopeMercator = new DefaultEnvelope();
    } else {
      this.envelopeMercator = envelopeMercator;
    }
    validExtensions = new ArrayList<>();
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

    try {
      String name = pathname.getName();
      int p = name.lastIndexOf(".");
      if (p > 0) {
        String ext = name.substring(p + 1);
        boolean extOk = false;
        if (validExtensions.isEmpty()) {
          extOk = true;
        } else {
          for (String validExt : this.validExtensions) {
            String vExt = validExt;
            if (vExt.startsWith(".")) {
              vExt = vExt.substring(1);
            }
            if (ext.equals(vExt)) {
              extOk = true;
              break;
            }
          }
        }
        if (!extOk) {
          return false;
        }
        if (envelopeMercator.isEmpty()) {
          return true;
        }
        String withoutExt = name.substring(0, p);
        int tileY = Integer.parseInt(withoutExt);

        Envelope bounds = TileUrl.getTileEnvelopeMercator(level, tileX, tileY);
        return bounds.intersects(envelopeMercator);
      }
      int tileY = Integer.parseInt(name);
      Envelope bounds = TileUrl.getTileEnvelopeMercator(level, tileX, tileY);
      return bounds.intersects(envelopeMercator);

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

  /**
   * The integer tileX column position.
   *
   * @return the tileX
   */
  public final int getTileX() {
    return tileX;
  }

  /**
   * The String list of extensions which should be accepted. If none are
   * specified then no extension filtering takes place.
   *
   * @return the validExtensions
   */
  public final List<String> getValidExtensions() {
    return validExtensions;
  }

}
