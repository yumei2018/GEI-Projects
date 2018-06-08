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

import gov.ca.water.shapelite.map.layer.Layer;
import java.awt.Image;

/**
 * This class stores information that is useful for a legend control, like an
 * icon representing the layer, and the string name of the layer.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class LegendSymbol {

  /**
   * The String name of the layer.
   */
  private String name;

  /**
   * The Image to show in the legend representing this layer.
   */
  private Image icon;

  private Layer<?> parent;

  /**
   * Creates a new instance of LegendSymbol
   *
   * @param parent
   */
  public LegendSymbol(Layer<?> parent) {
    this.parent = parent;
  }

  /**
   * Creates a new Legend Symbol with only an icon
   *
   * @param parent
   * @param icon
   */
  public LegendSymbol(Layer<?> parent, Image icon) {
    this.parent = parent;
    this.icon = icon;
  }

  /**
   * Creates a new LegendSymbol with a name and icon
   *
   * @param parent
   * @param icon
   * @param name
   */
  public LegendSymbol(Layer<?> parent, Image icon, String name) {
    this.parent = parent;
    this.icon = icon;
    this.name = name;

  }

  /**
   * Gets the String Name for the layer.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the String name of the layer.
   *
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the Image to show in the legend representing this layer
   *
   * @return the icon
   */
  public Image getIcon() {
    return icon;
  }

  /**
   * Sets the Image to show in the legend representing this layer
   *
   * @param icon the icon to set
   */
  public void setIcon(Image icon) {
    this.icon = icon;
  }

  /**
   * @return the parent
   */
  public Layer<?> getParent() {
    return parent;
  }

  /**
   * @param parent the parent to set
   */
  public void setParent(Layer<?> parent) {
    this.parent = parent;
  }
}
