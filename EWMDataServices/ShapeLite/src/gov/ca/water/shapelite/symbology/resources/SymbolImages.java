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
package gov.ca.water.shapelite.symbology.resources;

import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.legend.internal.LegendItem;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class SymbolImages {

  /**
   * A hash map for storing cached IMAGES.
   */
  private static final HashMap<String, BufferedImage> IMAGES = new HashMap<>();

  /**
   * Private constructor for utility class.
   */
  private SymbolImages() {

  }

  /**
   * Gets an optional image based on the image name. If the resource is
   * successfully loaded, this has a BufferedImage value, otherwise, this is
   * empty.
   *
   * @param filename The String name of the image relative to the
   * legend.resources path, including extension.
   * @return An Optional BufferedImage, which can be empty.
   */
  public static Optional<BufferedImage> get(String filename) {
    BufferedImage result = null;
    if (IMAGES.containsKey(filename)) {
      result = IMAGES.get(filename);
    } else {
      try {
        result = ImageIO.read(SymbolImages.class.getResourceAsStream(filename));
        IMAGES.put(filename, result);
      } catch (IOException ex) {
        Logger.getLogger(SymbolImages.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return Optional.of(result);
  }


}
