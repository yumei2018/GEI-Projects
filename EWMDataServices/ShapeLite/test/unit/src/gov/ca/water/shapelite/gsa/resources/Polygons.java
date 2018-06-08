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
package gov.ca.water.shapelite.gsa.resources;

import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.io.ShapefileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class Polygons {

  /**
   * A hash map for storing cached POLYGONS.
   */
  private static final HashMap<String, FeatureSet> POLYGONS = new HashMap<>();

  /**
   * Private constructor for utility class.
   */
  private Polygons() {

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
  public static Optional<FeatureSet> get(String filename) {
    FeatureSet result = null;
    if (POLYGONS.containsKey(filename)) {
      result = POLYGONS.get(filename);
    } else {
      try {
        ShapefileReader reader = new ShapefileReader();
        reader.open(Polygons.class.getResourceAsStream(filename));
        result = reader.getFeatures();
        POLYGONS.put(filename, result);
      } catch (IOException ex) {
        Logger.getLogger(Polygons.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return Optional.of(result);
  }




}
