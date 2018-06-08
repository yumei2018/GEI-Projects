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
package gov.ca.water.shapelite.testutils;

import java.awt.image.BufferedImage;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class ImageCompare {

  /**
   * Private constructor.
   */
  private ImageCompare() {

  }

  /**
   * Tests whether the two images are equal.
   *
   * @param imgA the first image to test.
   * @param imgB the image to compare the first image to.
   * @return boolean, true if the pixels in the images are the same and the
   * images are the same size.
   */
  public static boolean areEqual(BufferedImage imgA, BufferedImage imgB) {
    // The images must be the same size.
    if (imgA.getWidth() == imgB.getWidth() && imgA.getHeight() == imgB.getHeight()) {
      int width = imgA.getWidth();
      int height = imgA.getHeight();

      // Loop over every pixel.
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          // Compare the pixels for equality.
          if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
            return false;
          }
        }
      }
    } else {
      return false;
    }

    return true;
  }
}
