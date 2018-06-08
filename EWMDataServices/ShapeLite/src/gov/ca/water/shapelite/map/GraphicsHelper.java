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
package gov.ca.water.shapelite.map;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * This class provides some calculation helpers for some graphics effects.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GraphicsHelper {

  /**
   * Creates a new Graphics Helper class.  This currently only has a blur filter,
   * that is used for some shadow effects.
   */
  private GraphicsHelper() {
  }

  /**
   * This method blurs the image by the specified radius in the horizontal and vertical 
   * directions independently by applying the linear filters twice, which is more efficient
   * than applying a square filter once for large blur sizes.
   * @param source The image to blur.
   * @param radius The blur radius.
   * @return 
   */
  public BufferedImage blur(BufferedImage source, Point radius) {
    BufferedImage mid = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
    BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
    ConvolveOp convolve = getGaussianBlurFilter(radius.x, true);
    convolve.filter(source, mid);
    convolve = getGaussianBlurFilter(radius.y, false);
    convolve.filter(mid, result);
    return result;
  }

  /**
   * Convolution simply cycles through each pixel of an output image, and multiplies a 
   * rectangular window of coefficients against the input image.  Generally, the window
   * is centered on the same coordinate location as the output pixel.  For blur calculations
   * it is faster to apply two, linearly independent 1x3 filters than it is to apply a 
   * single pass 3x3 filter.
   * @param radius The integer blur radius in pixels, which must be >= 1.
   * @param horizontal A boolean value indicating whether or not the filter is horizontal
   * or vertical.
   * @return A ConvolveOp which can be used for blur calculations in 2D images.
   */
  private static ConvolveOp getGaussianBlurFilter(int radius,
          boolean horizontal) {
    if (radius < 1) {
      throw new IllegalArgumentException("Radius must be >= 1");
    }
    int size = radius * 2 + 1;
    float[] data = new float[size];
    float sigma = radius / 3.0f;
    float twoSigmaSquare = 2.0f * sigma * sigma;
    float sigmaRoot = sigma * 2.5066f;
    float total = 0.0f;

    for (int i = -radius; i <= radius; i++) {
      float distance = i * i;
      int index = i + radius;
      data[index] = (float) Math.exp(-distance / twoSigmaSquare) / sigmaRoot;
      total += data[index];
    }

    for (int i = 0; i < data.length; i++) {
      data[i] /= total;
    }

    Kernel kernel;
    if (horizontal) {
      kernel = new Kernel(size, 1, data);
    } else {
      kernel = new Kernel(1, size, data);
    }

    return new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);


  }

  /**
   * SingletonHolder is loaded on the first execution of Singleton.getInstance() or the
   * first access to SingletonHolder.INSTANCE, not before.
   */
  private static class SingletonHolder {
    public static final GraphicsHelper INSTANCE = new GraphicsHelper();
  }

  public static GraphicsHelper getInstance() {
    return GraphicsHelper.SingletonHolder.INSTANCE;
  }
}
