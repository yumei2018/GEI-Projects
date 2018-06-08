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

import java.util.Date;

/**
 * A class for representing a column of attributes in a shapefile's dbf file.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Field implements Cloneable {

  /**
   * The maximum number of characters allowed in a field name is 10.
   */
  private static final int MAX_NAME_LENGTH = 10;

  /**
   * Substring length of year given YYYYMMDD format.
   */
  private static final int YEAR = 4;
  /**
   * Substring length of date ending in month given YYYYMMDD format.
   */
  private static final int MONTH = 6;
  /**
   * Substring length of date ending in day given YYYYMMDD format.
   */
  private static final int DAY = 8;

  /**
   * Text representations that are shorter than this length can be parsed as an
   * integer.
   */
  private static final int MAX_INT_LENGTH = 10;

  /**
   * Text representations that are shorter than this length can be parsed as a
   * long.
   */
  private static final int MAX_LONG_LENGTH = 19;

  /**
   * The maximum length of a field in bytes.
   */
  private static final int MAX_LENGTH = 255;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The class of the data type. The reason for the question mark is to prevent
   * compile warnings if you just use "Class".
   */
  private Class<?> dataType;

  /**
   * The integer number of digits that are kept past the zero that must be
   * consistent across all numeric values stored in the column represented by
   * this value.
   */
  private int decimal;

  /**
   * The integer length in characters for this field.
   */
  private int length;

  /**
   * The string name identifying this field.
   */
  private String name;

  /**
   * The integer offset in characters of the field in the text representation of
   * a row.
   */
  private int offset;

  /**
   * The character code defining the "type" of the data in the dbf file.
   */
  private String type;

  //</editor-fold>
  /**
   * Creates a new instance of a Field.
   */
  public Field() {
    dataType = String.class;
    length = MAX_LENGTH;
  }

  /**
   * For any type except numeric fields that require a decimal.
   *
   * @param name The string name of the field.
   * @param type The enumerated data type of the field.
   * @param length the integer character length of the field.
   */
  public Field(String name, FieldType type, int length) {
    this.name = name;
    this.type = type.getCode();
    this.length = length;
    this.calculateDataType();
  }

  /**
   * For Numeric types, this creates a new field that supports the value.
   *
   * @param name The string name of the field.
   * @param length the integer character length of the field.
   * @param decimal the integer number of digits after the decimal.
   */
  public Field(String name, int length, int decimal) {
    this.name = name;
    this.type = "N";
    this.length = length;
    this.decimal = decimal;
    this.calculateDataType();

  }

  /**
   * Gets a String representation of this Field.
   *
   * @return The String.
   */
  @Override
  public String toString() {
    return "Field{" + "name=" + name + ", type=" + type + '}';
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Creates a duplicate of this field.
   *
   * @return A Field that is a deep copy of this field.
   */
  public final Field copy() {
    Field result = new Field();
    result.setDataType(dataType);
    result.setDecimal(decimal);
    result.setLength(length);
    result.setName(name);
    result.setOffset(offset);
    result.setType(type);
    return result;
  }

  /**
   * Calculates the java class type from the DBF type and the length.
   */
  public final void calculateDataType() {
    if ("N".equals(getType())) {
      if (getDecimal() == 0) {
        if (getLength() < MAX_INT_LENGTH) {
          setDataType(Integer.class);
        } else if (getLength() < MAX_LONG_LENGTH) {
          setDataType(Long.class);
        }
      } else {
        setDataType(Double.class);
      }
    }
    if ("L".equals(getType())) {
      setDataType(Boolean.class);
    }
    if ("D".equals(getType())) {
      setDataType(Date.class);
    }
  }

  /**
   * Gets the name field, ensuring a maximum of 10 character length.
   *
   * @return Either the name or the substring of the name.
   */
  public final String getShortName() {
    if (this.name.length() <= MAX_NAME_LENGTH) {
      return this.name;
    }
    return this.name.substring(0, MAX_NAME_LENGTH);
  }

  /**
   * Parses the specified text for this field into an object based on the
   * characteristics of this field.
   *
   * @param text The string text to parse.
   * @return An numeric object that can be a date, int, long, double, or string
   * depending on the settings for this field.
   */
  public final Object parse(String text) {
    Object result;
    switch (getType()) {
      case "D":
        String date = text;
        if (date == null || date.isEmpty()) {
          result = null;
        } else {
          int y = Integer.parseInt(date.substring(0, YEAR));
          int m = Integer.parseInt(date.substring(YEAR, MONTH));
          int d = Integer.parseInt(date.substring(MONTH, DAY));
          result = DateSetter.getDate(y, m, d);
        }
        break;
      case "N":
        if (getDecimal() == 0) {
          if (getLength() < MAX_INT_LENGTH) {
            result = Integer.parseInt(text);
          } else if (getLength() < MAX_LONG_LENGTH) {
            result = Long.parseLong(text);
          } else {
            result = text;
          }
        } else {
          result = Double.parseDouble(text);
        }
        break;
      case "L":
        result = "Y".equals(text) || "y".equals(text)
            || "T".equals(text) || "t".equals(text);
        break;
      default:
        result = text;
        break;
    }
    return result;
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the class of the data type. The reason for the question mark is to
   * prevent compile warnings if you just use "Class".
   *
   * @return the dataType.
   */
  public final Class<?> getDataType() {
    return dataType;
  }

  /**
   * Sets the class of the data type. The reason for the question mark is to
   * prevent compile warnings if you just use "Class".
   *
   * @param dataType the dataType to set.
   */
  public final void setDataType(Class<?> dataType) {
    this.dataType = dataType;
  }

  /**
   * Gets the integer number of digits that are kept past the zero that must be
   * consistent across all numeric values stored in the column represented by
   * this value.
   *
   * @return the decimal.
   */
  public final int getDecimal() {
    return decimal;
  }

  /**
   * Sets the integer number of digits that are kept past the zero that must be
   * consistent across all numeric values stored in the column represented by
   * this value.
   *
   * @param decimal the decimal to set.
   */
  public final void setDecimal(int decimal) {
    this.decimal = decimal;
  }

  /**
   * Gets the integer length in characters for this field.
   *
   * @return the length.
   */
  public final int getLength() {
    return length;
  }

  /**
   * Sets the integer length in characters for this field.
   *
   * @param length the length to set.
   */
  public final void setLength(int length) {
    this.length = length;
  }

  /**
   * Gets the string name identifying this field.
   *
   * @return the name.
   */
  public final String getName() {
    return name;
  }

  /**
   * Sets the string name identifying this field.
   *
   * @param name the name to set.
   */
  public final void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the integer offset in characters of the field in the text
   * representation of a row.
   *
   * @return the offset.
   */
  public final int getOffset() {
    return offset;
  }

  /**
   * Sets the Integer offset in characters of the field in the text
   * representation of a row. If this is null, a value of 0 is used.
   *
   * @param offset the offset to set.
   */
  public final void setOffset(Integer offset) {
    if (offset == null) {
      this.offset = 0;
    } else {
      this.offset = offset;
    }
  }

  /**
   * Sets the int offset in characters of the field in the text representation
   * of a row.
   *
   * @param offset the offset to set.
   */
  public final void setOffset(int offset) {
    this.offset = offset;
  }

  /**
   * Gets the character code defining the "type" of the data in the dbf file.
   *
   * @return the type.
   */
  public final String getType() {
    return type;
  }

  /**
   * Sets the character code defining the "type" of the data in the dbf file. D
   * - Date, N - Numeric, L - Logic, C - character
   *
   * @param type the type to set.
   */
  public final void setType(String type) {
    this.type = type;
  }

  //</editor-fold>
}
