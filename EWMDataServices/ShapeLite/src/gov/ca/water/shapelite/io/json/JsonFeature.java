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
package gov.ca.water.shapelite.io.json;

import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * @param <T> A type of JsonGeometry that this feature represents.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class JsonFeature<T extends JsonGeometry<T>> implements Serializable {

  /**
   * The version ID for this serializable object. Increment this if the fields
   * are modified.
   */
  static final long serialVersionUID = 1L;

  /**
   * A hashmap of attribute values.
   */
  private HashMap<String, String> attributes;

  /**
   * The geometry string.
   */
  private T geometry;

  /**
   * The JSonFeature, representing a geometry and a hash map.
   */
  public JsonFeature() {
    attributes = new HashMap<>();
  }

  /**
   * Creates a deep copy of the object by copying its properties to the new
   * object.
   *
   * @return A deep copy of the JsonFeature.
   */
  public final JsonFeature<T> copy() {
    JsonFeature<T> result = new JsonFeature<>();
    result.copyProperties(this);
    return result;
  }

  /**
   * Copies all the properties of this feature.
   *
   * @param other The other feature.
   */
  public final void copyProperties(JsonFeature<T> other) {
    attributes.clear();
    for (Entry<String, String> entry : other.getAttributes().entrySet()) {
      attributes.put(entry.getKey(), entry.getValue());
    }
    geometry = other.getGeometry().copy();
  }

  /**
   * A method that allows custom behavior from subclasses during copying.
   */
  protected void onCopyProperties() {

  }

  /**
   * Gets the HashMap of String field values.
   *
   * @return the attributes
   */
  public final HashMap<String, String> getAttributes() {
    return attributes;
  }

  /**
   * Sets the HashMap of String field values.
   *
   * @param attributes the attributes to set
   */
  public final void setAttributes(HashMap<String, String> attributes) {
    this.attributes = attributes;
  }

  /**
   * Gets the JSON String defining the ESRI Geometry.
   *
   * @return the String geometry
   */
  public final T getGeometry() {
    return geometry;
  }

  /**
   * Sets the JSON String defining the ESRI Geometry.
   *
   * @param geometry the geometry to set
   */
  public final void setGeometry(T geometry) {
    this.geometry = geometry;
  }

  /**
   * Gets a full Feature with both shape and attribute information. It is left
   * up to the developer to ensure that the attributes match the featureset
   * field definitions.
   *
   * @param type The ShapeType to create.
   * @param hasM Boolean, true if the feature should have M values.
   * @param hasZ Boolean, true if the feature should have Z values.
   * @return the newly created Feature.
   *
   */
  public final Feature toFeature(ShapeType type, boolean hasM, boolean hasZ) {
    Feature result = new Feature();
    Shape shape = JsonShapeConverter.getShape(geometry, hasM, hasZ);
    result.setShape(shape);
    result.setAttributesFrom(attributes);
    return result;
  }

}
