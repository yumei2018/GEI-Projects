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

import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.FieldType;
import gov.ca.water.shapelite.Optional;
import java.io.Serializable;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class JsonField implements Serializable {

  /**
   * The version ID for this serializable object. Increment this if the fields
   * are modified.
   */
  static final long serialVersionUID = 1L;

  /**
   * The number of characters in the yyyyMMdd date format.
   */
  private static final int DATE_LENGTH = 8;

  /**
   * Length of a short number in characters.
   */
  private static final int SHORT = 16;

  /**
   * Length of a long number in characters.
   */
  private static final int LONG = 32;

  /**
   * The maximum length of a string in a shapefile.
   */
  private static final int MAX_STRING_LENGTH = 255;

  /**
   * The field name.
   */
  private String name;
  /**
   * The field type.
   */
  private String type;
  /**
   * The alias.
   */
  private String alias;

  /**
   * The length in characters of the field. This is not always used, and can
   * therefore sometimes be null.
   */
  private Integer length;

  /**
   * Gets a deep copy of this object.
   * @return A copy with the same properties as this object.
   */
  public JsonField copy() {
    JsonField field = new JsonField();
    field.copyProperties(this);
    return field;
  }

  /**
   * Copies the properties from the other field to this object.
   * @param other The other field to copy properties from.
   */
  public void copyProperties(JsonField other) {
    name = other.getName();
    type = other.getType();
    alias = other.getAlias();
    length = other.length;
  }

  /**
   * Gets the Field object from this string.
   *
   * @return The Field object.
   */
  public final Optional<Field> toField() {
    Field result = null;
    switch (type) {
      case EsriFieldType.SMALL_INTEGER:
        result = new Field(name, SHORT, 0);
        break;
      case EsriFieldType.INTEGER:
        result = new Field(name, getLength().orElse(LONG), 0);
        break;
      case EsriFieldType.SINGLE:
        result = new Field(name, SHORT, SHORT);
        break;
      case EsriFieldType.DOUBLE:
        result = new Field(name, LONG, LONG);
        break;
      case EsriFieldType.STRING:
        int fieldLength = MAX_STRING_LENGTH;
        if (length != null && length < MAX_STRING_LENGTH) {
          fieldLength = length;
        }
        result = new Field(name, FieldType.Character, fieldLength);
        break;
      case EsriFieldType.DATE:
        result = new Field(name, FieldType.Date, DATE_LENGTH);
        break;
      case EsriFieldType.OBJECT_ID:
        result = new Field(name, LONG, 0);
        break;
      case EsriFieldType.GUID:
        result = new Field(name, FieldType.Character, MAX_STRING_LENGTH);
        break;
      case EsriFieldType.GLOBAL_ID:
        result = new Field(name, FieldType.Character, MAX_STRING_LENGTH);
        break;
      default:
      // do nothing, so an empty optional will be returned.
    }
    return Optional.ofNullable(result);
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the field name.
   *
   * @return the name
   */
  public final String getName() {
    return name;
  }

  /**
   * Sets the field name.
   *
   * @param name the name to set
   */
  public final void setName(String name) {
    this.name = name;
  }

  /**
   * @return the type
   */
  public final String getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public final void setType(String type) {
    this.type = type;
  }

  /**
   * @return the alias
   */
  public final String getAlias() {
    return alias;
  }

  /**
   * @param alias the alias to set
   */
  public final void setAlias(String alias) {
    this.alias = alias;
  }

  /**
   * Gets the length in characters of the field. This is not always used, and
   * can therefore sometimes be null.
   *
   * @return the length
   */
  public final Optional<Integer> getLength() {
    return Optional.ofNullable(length);
  }

  /**
   * Sets the length in characters of the field. This is not always used, and
   * can therefore sometimes be null.
   *
   * @param length the length to set
   */
  public final void setLength(Integer length) {
    this.length = length;
  }

  // </editor-fold>
}
