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
package gov.ca.water.shapelite;

import com.vividsolutions.jts.geom.Geometry;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.topology.Adapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * This class represents the combination of a geometric shape with a set of attributes, stored in a HashMap.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Feature implements Serializable/*,Map/**/ {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The geometric shape, like a polygon, multiline, or point, stored as a list of parts, each part containing a list of
   * Coordinates.
   */
  private Shape shape;

  /**
   * The HashMap of field names and keys to store as attributes.
   */
  private final HashMap<String, String> attributes;

  /**
   * The parent featureset containing this feature.
   */
  private FeatureSet mParent;

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mParent = null;
    this.shape = null;
  }

  //</editor-fold>
  /**
   * Creates a new instance of the Feature class.
   */
  public Feature() {
    shape = new Shape();
    attributes = new HashMap<>();
  }

  /**
   * Creates a new instance of a point feature.
   *
   * @param x the x or longitude of the point.
   * @param y the y or latitude of the point.
   */
  public Feature(double x, double y) {
    shape = new Shape(new CoordXY(x, y));
    attributes = new HashMap<>();
  }

  /**
   * Creates a new instance of feature.
   *
   * @param shape The shape to use.
   */
  public Feature(Shape shape) {
    this.shape = shape;
    attributes = new HashMap<>();
  }

  //<editor-fold defaultstate="collapsed" desc="Set Parent">
  /**
   * Sets the feature parent FeatureSet
   *
   * @param parentSet
   * @return
   */
  public final Feature setParent(FeatureSet parentSet) {
    this.mParent = parentSet;

    return this;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Get Parent">
  /**
   * Get the parent container
   *
   * @return FeatureSet
   */
  public FeatureSet getParent() {
    return this.mParent;
  }
  //</editor-fold>

  //<editor-fold  desc="Methods">
  /**
   * Creates a new feature where the shape is the same shape but with a buffer.
   *
   * @param distance The distance measure of the buffer in the units of the coordinate system of the shape.
   * @return The Feature that is identical to this feature but has a buffered shape. In some cases where the shape is
   * smaller than the buffer, this feature will have a Shape of ShapeType.NullShape.
   *
   */
  public final Feature buffer(double distance) {
    Feature result = this.copy();
    Shape originalShape = this.getShape();
    Optional<Geometry> maybeGeom = Adapter.getGeometry(originalShape);
    if (maybeGeom.isPresent()) {
      Geometry geom = maybeGeom.get();
      Geometry buffer = geom.buffer(distance);
      Shape resultShape = Adapter.getShape(buffer, originalShape.hasM(),
        originalShape.hasZ());
      result.setShape(resultShape);
    }
    return result;
  }

  public final void ensureClosedPolygons() {
    List<Part> badParts = new ArrayList<>();
    for (Part part : getShape().getParts()) {
      if (part.getCoordinates().size() < 3) {
        badParts.add(part);
      }
    }
    for (Part part : badParts) {
      this.getShape().getParts().remove(part);
    }
  }

  /**
   * A null save contains key test for the String name.
   *
   * @param name The String name that can be null.
   * @return boolean that is only true if name is not null and is also a key in the attributes map.
   */
  public final boolean containsAttribute(@Nullable String name) {
    if (name != null) {
      if (attributes.containsKey(name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Copies the current feature, but without using any of the same data objects. This is a deep copy for both the shape
   * and attributes.
   *
   * @return A deep copy of this feature.
   */
  public final Feature copy() {
    Feature copy = new Feature();

    copy.copyProperties(this);
    return copy;
  }

  /**
   * Copies the shape and attribute information from the specified source.
   *
   * @param source The Feature source to copy.
   */
  public final void copyProperties(Feature source) {
    this.shape = source.getShape().copy();
    attributes.clear();
    for (Entry<String, String> pair : source.getAttributes().entrySet()) {
      attributes.put(pair.getKey(), pair.getValue());
    }
  }

  /**
   * This inspects the keys of the attributes on the other feature and if there are any attribute keys on other that are
   * not on this feature, it will copy those features over.
   *
   * @param other The other feature to copy missing attributes from.
   */
  public final void copyMissingAttributes(Feature other) {
    for (String key : other.attributes.keySet()) {
      if (key == null) {
        continue;
      }
      if (this.getAttributes().containsKey(key)) {
        continue;
      }
      getAttributes().put(key, other.getAttributes().get(key));
    }
  }

  /**
   * Gets the text value from the specified field name parsed as an integer.
   *
   * @param fieldName The string field name to get the value for.
   * @return The integer version of the field name, or null if parsing fails.
   */
  public final Boolean getBoolValue(String fieldName) {
    Boolean result = null;
    if (attributes.containsKey(fieldName)) {
      try {
        result = Boolean.parseBoolean(attributes.get(fieldName));

      } catch (NumberFormatException nfe) {
      }
    }
    return result;
  }

  /**
   * Gets the bounds for the envelope.
   *
   * @return The Envelope containing the shape of this feature.
   */
  public final Envelope getEnvelope() {
    return getShape().getEnvelope();
  }

  /**
   * Gets the text value from the specified field name parsed as an integer.
   *
   * @param fieldName The string field name to get the value for.
   * @return The integer version of the field name, or null if parsing fails.
   */
  public final Double getDoubleValue(String fieldName) {
    Double result = null;
    if (attributes.containsKey(fieldName)) {
      try {
        result = Double.parseDouble(attributes.get(fieldName));

      } catch (NumberFormatException nfe) {
      }
    }
    return result;
  }

  /**
   * Gets the text value from the specified field name parsed as an integer.
   *
   * @param fieldName The string field name to get the value for.
   * @return The integer version of the field name, or null if parsing fails.
   */
  public final Integer getIntValue(String fieldName) {
    Integer result = null;
    if (attributes.containsKey(fieldName)) {
      try {
        result = Integer.parseInt(attributes.get(fieldName));

      } catch (NumberFormatException nfe) {
      }
    }
    return result;
  }

  /**
   * Shows the attributes map as a string.
   *
   * @return The attributes map representation of this feature.
   */
  @Override
  public final String toString() {
    return this.getAttributes().toString();
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the HashMap of field names and keys to store as attributes.
   *
   * @return the attributes.
   */
  public final HashMap<String, String> getAttributes() {
    return attributes;
  }

  /**
   * Add attributes.
   *
   * @param attrs The attributes to add.
   */
  public final void addAttributes(HashMap<String, String> attrs) {
    this.attributes.putAll(attrs);
  }

  /**
   * Sets the attributes from the specified attributes.
   *
   * @param attributes The attributes to copy.
   */
  public final void setAttributesFrom(HashMap<String, String> attributes) {
    copyAttributes(attributes);
  }

  /**
   * Copies the attributes into this class, clearing any existing attribute information.
   *
   * @param attributes The attributes to copy.
   */
  public final void copyAttributes(HashMap<String, String> attributes) {
    this.attributes.clear();
    for (Entry<String, String> entry : attributes.entrySet()) {
      this.attributes.put(entry.getKey(), entry.getValue());
    }
  }

  /**
   * Gets the copy of the attributes in a new HashMap.
   *
   * @return Gets a deep copy of the hashmap of attributes.
   */
  public final HashMap<String, String> getAttributesCopy() {
    HashMap<String, String> result = new HashMap<>();
    for (Entry<String, String> entry : attributes.entrySet()) {
      result.put(entry.getKey(), entry.getValue());
    }
    return result;
  }

  /**
   * Gets the geometric shape, like a polygon, multiline, or point, stored as a list of parts, each part containing a
   * list of Coordinates.
   *
   * @return the shape
   */
  public final Shape getShape() {
    return shape;
  }

  /**
   * Sets the geometric shape, like a polygon, multiline, or point, stored as a list of parts, each part containing a
   * list of Coordinates.
   *
   * @param shape the shape to set
   */
  public final void setShape(@NonNull Shape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape argument should not be null.");
    }
    this.shape = shape;
  }

  /**
   * Tests if the shapes intersect each other using Java Topology Suite.
   *
   * @param other The other shape to test.
   * @return Boolean that is true if this feature's shape intersects the other features shape.
   */
  public final boolean intersects(Feature other) {
    if (this.shape != null && other.shape != null) {
      return this.shape.intersects(other.shape);
    }
    return false;
  }

  //</editor-fold>
/*
  //<editor-fold defaultstate="collapsed" desc="Map Implementations">
  @Override
  public int size() {
    return this.attributes.size();
  }

  @Override
  public boolean isEmpty() {
    return this.attributes.isEmpty();
  }

  @Override
  public boolean containsKey(Object key) {
    return this.attributes.containsKey(key);
  }

  @Override
  public boolean containsValue(Object value) {
    return this.attributes.containsValue(value);
  }

  @Override
  public Object get(Object key) {
    return this.attributes.get(key);
  }

  @Override
  public Object put(Object key, Object value) {
    String skey = null;
    if (key != null) {
      skey  = key.toString();
    }
    String sval = null;
    if (value != null) {
      sval = value.toString();
    }
    return this.attributes.put(skey,sval);
  }

  @Override
  public Object remove(Object key) {
    return this.attributes.remove(key);
  }

  @Override
  public void putAll(Map m) {
    this.attributes.putAll(m);
  }

  @Override
  public void clear() {
    this.attributes.clear();
  }

  @Override
  public Set keySet() {
    return this.attributes.keySet();
  }

  @Override
  public Collection values() {
    return this.attributes.values();
  }

  @Override
  public Set entrySet() {
    return this.attributes.entrySet();
  }
  </editor-fold>/**/
}
