/*
 * The MIT License
 *
 * Copyright 2015 rmarquez.
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
package gov.ca.water.shapelite.data.marker;

import gov.ca.water.shapelite.Shape;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author rmarquez
 */
public class PathRangeMarker extends LineMarker {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The HashMap of attributes.
   */
  private final HashMap<String, String> attributes;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Creates a new instance of a MarkerPath based on a shape and a name.
   *
   * @param shape The Line Shape object to use for the MarkerPath.
   * @param name The String name.
   */
  public PathRangeMarker(Shape shape, String name) {
    super(shape, name);
    attributes = new HashMap<>();
  }

  /**
   * Creates a new instance of a MarkerPath based on a shape and a name.
   *
   * @param shape The Line Shape object to use for the MarkerPath.
   * @param name The String name.
   * @param attributes The attributes to use to define the attributes for this
   * marker path range.
   */
  public PathRangeMarker(Shape shape, String name,
      HashMap<String, String> attributes) {
    super(shape, name);
    this.attributes = attributes;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Methods">
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the HashMap of attributes.
   *
   * @return A HashMap of field name and values.
   */
  public final HashMap<String, String> getAttributes() {
    return attributes;
  }

  /**
   * Clears the current attributes and copies the specified HashMap of keys and
   * values to this hash map.
   *
   * @param attributes A HashMap of field name and values.
   */
  public final void setAttributesFrom(HashMap<String, String> attributes) {
    this.attributes.clear();
    for (Entry<String, String> attribute : attributes.entrySet()) {
      this.attributes.put(attribute.getKey(), attribute.getValue());
    }
  }

  //</editor-fold>
}
