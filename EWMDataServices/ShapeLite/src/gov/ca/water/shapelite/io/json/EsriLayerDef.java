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

import gov.ca.water.shapelite.Optional;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class EsriLayerDef {

  // <editor-fold desc="Fields">
  /**
   * The name of the layer.
   */
  private String name;
  /**
   * The maximum number of records that can be queried at one time.
   */
  private int maxRecordCount;
  /**
   * A boolean that is true if the layer coordinates have Z values.
   */
  private boolean hasZ;
  /**
   * A boolean that is true if the layer coordinates have M values.
   */
  private boolean hasM;

  /**
   * The list of JsonFields defining the attribute schema.
   */
  private List<JsonField> fields;


  /**
   * The esri geometry type enumeration showing what type of geometry is
   * contained in the layer.
   */
  private EsriGeometryType geometryType;

  // </editor-fold>
  /**
   * Creates a new instance of the EsriLayerDef.
   */
  public EsriLayerDef() {
    fields = new ArrayList<>();
  }

  /**
   * Gets the first instance of a JsonField field with the specified name.
   *
   * @param name The string name.
   * @return The optional JsonField with the specified name, or empty if the
   * field is not found.
   */
  public final Optional<JsonField> getField(String name) {
    for (JsonField field : fields) {
      if (field.getName() != null && field.getName().equals(name)) {
        return Optional.of(field);
      }
    }
    return Optional.empty();
  }

  /**
   * Gets a comma delimited list of the field names.
   *
   * @return The comma delimited list of field names as a string.
   */
  public final String getFieldNames() {
    boolean started = false;
    String result = "";
    for (JsonField field : fields) {
      if (started) {
        result += ",";
      }
      result += field.getName();
      started = true;
    }
    return result;
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the name of the layer.
   *
   * @return the name
   */
  public final String getName() {
    return name;
  }

  /**
   * Sets the name of the layer.
   *
   * @param name the name to set
   */
  public final void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the maximum number of records that can be queried at one time.
   *
   * @return the maxRecordCount
   */
  public final int getMaxRecordCount() {
    return maxRecordCount;
  }

  /**
   * Sets the maximum number of records that can be queried at one time.
   *
   * @param maxRecordCount the maxRecordCount to set
   */
  public final void setMaxRecordCount(int maxRecordCount) {
    this.maxRecordCount = maxRecordCount;
  }

  /**
   * Gets a boolean that is true if the layer coordinates have Z values.
   *
   * @return the hasZ
   */
  public final boolean getHasZ() {
    return hasZ;
  }

  /**
   * Sets a boolean that is true if the layer coordinates have Z values.
   *
   * @param hasZ the hasZ to set
   */
  public final void setHasZ(boolean hasZ) {
    this.hasZ = hasZ;
  }

  /**
   * Gets a boolean that is true if the layer coordinates have M values.
   *
   * @return the hasM
   */
  public final boolean getHasM() {
    return hasM;
  }

  /**
   * Sets a boolean that is true if the layer coordinates have M values.
   *
   * @param hasM the hasM to set
   */
  public final void setHasM(boolean hasM) {
    this.hasM = hasM;
  }

  /**
   * Gets the list of JsonFields defining the attribute schema.
   *
   * @return the fields
   */
  public final List<JsonField> getFields() {
    return fields;
  }

  /**
   * Sets the list of JsonFields defining the attribute schema.
   *
   * @param fields the fields to set
   */
  public final void setFields(List<JsonField> fields) {
    this.fields = fields;
  }

  /**
   * Gets the GeometryType.
   * @return the geometryType
   */
  public final EsriGeometryType getGeometryType() {
    return geometryType;
  }

  /**
   * Sets the GeometryType.
   * @param geometryType the geometryType to set
   */
  public final void setGeometryType(EsriGeometryType geometryType) {
    this.geometryType = geometryType;
  }

  // </editor-fold>
}
