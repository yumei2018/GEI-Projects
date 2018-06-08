/*
 * The MIT License
 *
 * Copyright 2014 hdunsford.
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
package gov.ca.water.shapelite.symbology;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShadowSymbolizer {

  //<editor-fold defaultstate="collapsed" desc="Static Constructor">
  /**
   * Static Constructor of a Centered Drop Shadow.
   *
   * @param size the Drop Shadow size (in pixels)
   * @param shadowColor the Drop Shadow color
   * @return the new Symbolizer
   */
  public static final ShadowSymbolizer centered(int size,
      Color shadowColor) {
    ShadowSymbolizer result
        = new ShadowSymbolizer(new Point(size, size),
            new Point(0, 0), shadowColor);
    return result;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The Drop Shadow size.
   */
  private Point shadowSize;
  /**
   * The Drop Shadow from the shape's Center.
   */
  private Point offset;
  /**
   * The Drop Shadow's fill color.
   */
  private Color shadowColor;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Creates a new instance of the DropShadow class.
   */
  public ShadowSymbolizer() {
    this.shadowSize = new Point();
    this.offset = new Point();
    this.shadowColor = Color.BLACK;
  }

  /**
   * Create the new drop shadow.
   *
   * @param size The Point size representing the X and Y dimensions of the
   * shadowing
   * @param offset The Point offset representing the X and Y offsets in pixels.
   * @param shadowColor The shadowColor to use.
   */
  public ShadowSymbolizer(Point size, Point offset, Color shadowColor) {
    if (size == null) {
      this.shadowSize = new Point();
    } else {
      this.shadowSize = size;
    }
    if (offset == null) {
      this.offset = new Point();
    } else {
      this.offset = offset;
    }
    if (shadowColor == null) {
      this.shadowColor = Color.BLACK;
    } else {
      this.shadowColor = shadowColor;
    }
  }
  //</editor-fold>

  /**
   * Gets a deep copy of this symbolizer.
   *
   * @return The new ShadowSymbolizer.
   */
  public final ShadowSymbolizer copy() {
    ShadowSymbolizer result = new ShadowSymbolizer();
    result.copyProperties(this);
    return result;
  }

  /**
   * Copies the properties of the other symbolizer to this one.
   *
   * @param other The other ShadowSymbolizer to copy values from.
   */
  public final void copyProperties(ShadowSymbolizer other) {
    shadowSize = new Point(other.shadowSize.x, other.shadowSize.y);
    offset = new Point(other.offset.x, other.offset.y);
    shadowColor = other.shadowColor;
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the offset
   */
  public final Point getOffset() {
    return this.offset;
  }

  /**
   * @param offset the offset to set
   */
  public final void setOffset(Point offset) {
    if (offset != null) {
      this.offset = offset;
    } else {
      this.offset = new Point();
    }
  }

  /**
   * @return the shadowSize
   */
  public final Point getShadowSize() {
    return this.shadowSize;
  }

  /**
   * @param shadowSize the shadowSize to set
   */
  public final void setShadowSize(Point shadowSize) {
    if (shadowSize == null) {
      this.shadowSize = new Point();
    } else {
      this.shadowSize = shadowSize;
    }
  }

  /**
   * Gets the color of the shadow.
   *
   * @return the shadow color.
   */
  public final Color getShadowColor() {
    return this.shadowColor;
  }

  /**
   * Sets the color of the shadow.
   *
   * @param shadowColor the color of the shadow to set.
   */
  public final void setShadowColor(Color shadowColor) {
    if (shadowColor != null) {
      this.shadowColor = shadowColor;
    } else {
      this.shadowColor = Color.BLACK;
    }
  }
  //</editor-fold>

}
