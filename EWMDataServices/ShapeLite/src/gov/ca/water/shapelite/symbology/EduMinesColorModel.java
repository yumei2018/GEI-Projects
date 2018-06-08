/*
 * The MIT License
 *
 * Copyright 2016 rmarquez.
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
import java.awt.image.IndexColorModel;

/**
 * Implementation of ColorMap based on EduMines ColorMap. Use Builder to create instance.
 *
 * @see ColorMap
 * @author rmarquez
 */
public class EduMinesColorModel implements ColorMap {

  private final edu.mines.jtk.awt.ColorMap colorMap;
  private Model model;

  private EduMinesColorModel(double min, double max, Model model) {
    this.colorMap = new edu.mines.jtk.awt.ColorMap(min, max, model.getIndexColorModel());
    this.model = model;
  }

  private EduMinesColorModel(double min, double max, Color[] colors) {
    this.colorMap = new edu.mines.jtk.awt.ColorMap(min, max, colors);
  }

  @Override
  public Color getColor(double value) {
    return this.colorMap.getColor(value);
  }

  @Override
  public Double getMin() {
    return this.colorMap.getMinValue();
  }

  @Override
  public Double getMax() {
    return this.colorMap.getMaxValue();
  }

  @Override
  public Color getMinColor() {
    return this.colorMap.getColor(this.colorMap.getMinValue());
  }

  @Override
  public Color getMaxColor() {
    return this.colorMap.getColor(this.colorMap.getMaxValue());
  }

  @Override
  public float getOpacity() {
    return this.colorMap.getColor(0).getAlpha();
  }

  public IndexColorModel getIndexColorModel() {
    return this.colorMap.getColorModel();
  }

  private Model getModel() {
    return this.model;
  }

  public static enum Model {
    GRAY(edu.mines.jtk.awt.ColorMap.getGray()),
    JET(edu.mines.jtk.awt.ColorMap.getJet()),
    HUE(edu.mines.jtk.awt.ColorMap.getHue()),
    PRISM(edu.mines.jtk.awt.ColorMap.getPrism()),
    REDWHITEBLUE(edu.mines.jtk.awt.ColorMap.getRedWhiteBlue()),;

    private IndexColorModel indexColorModel;

    Model(IndexColorModel indexColorModel) {
      this.indexColorModel = indexColorModel;

    }

    public IndexColorModel getIndexColorModel() {
      return indexColorModel;
    }
  }

  /**
   * Builder method for creating instances of EduMinesColorModel. Defaults :
   * <ul>
   * <li> min = 0 </li>
   * <li> max = 100.0 </li>
   * <li> indexModel = JET </li>
   * </ul>
   */
  public static class Builder {

    private double min = 0.0;
    private double max = 100.0;
    private Model model = Model.JET;
    private Color[] colors = null;

    /**
     * Builder method for creating instances of EduMinesColorModel. Defaults :
     * <ul>
     * <li> min = 0 </li>
     * <li> max = 100.0 </li>
     * <li> indexModel = JET </li>
     * </ul>
     */
    public Builder() {
    }

   /**
   * Builder method for creating instances of EduMinesColorModel by copying other.
   * @param other
   */
    public Builder(EduMinesColorModel other) {
      this.min = other.getMin();
      this.max = other.getMax();
      this.model = other.getModel();
    }

    public Builder setMin(double min) {
      this.min = min;
      return this;
    }

    public Builder setMax(double max) {
      this.max = max;
      return this;
    }

    public Builder setModel(Model model) {
      this.model = model;
      return this;
    }

    public void setColors(Color[] colors) {
      this.colors = colors;
      this.model = null;
    }

    public EduMinesColorModel build() {
      if (this.model != null) {
        return new EduMinesColorModel(min, max, model);
      } else {
        return new EduMinesColorModel(min, max, colors);
      }
    }
  }

}
