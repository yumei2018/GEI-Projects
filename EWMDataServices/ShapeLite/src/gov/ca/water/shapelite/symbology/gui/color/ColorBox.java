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
package gov.ca.water.shapelite.symbology.gui.color;

import gov.ca.water.shapelite.NonNull;
import java.awt.Color;
import java.awt.Rectangle;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ColorBox {
  /**
   * The bounds of the rectangle in client pixels.
   */
  private Rectangle bounds;

  /**
   * The color of the rectangle.
   */
  private Color color;

  /**
   * Creates a new instance of a color box.
   * @param bounds The bounds of the box in client pixel coordinates.
   * @param color The color value of the color box.
   */
  public ColorBox(@NonNull Rectangle bounds, @NonNull Color color){
    if (bounds == null) {
      throw new IllegalArgumentException("Parameter bounds cannot be null.");
    }
    this.bounds = bounds;
    this.color = color;
  }

  /**
   * Gets the bounds of the rectangle in client pixels.
   * @return the bounds
   */
  public final Rectangle getBounds() {
    return bounds;
  }

  /**
   * Sets the bounds of the rectangle in client pixels.
   * @param bounds the bounds to set
   */
  public final void setBounds(@NonNull Rectangle bounds) {
    if (bounds == null) {
      throw new IllegalArgumentException("Parameter bounds cannot be null.");
    }
    this.bounds = bounds;
  }

  /**
   * Gets the color of the rectangle.
   * @return the color
   */
  public final Color getColor() {
    return color;
  }

  /**
   * Sets the color of the rectangle.
   * @param color the color to set
   */
  public final void setColor(Color color) {
    if (color == null) {
      throw new IllegalArgumentException("Parameter color cannot be null.");
    }
    this.color = color;
  }
}
