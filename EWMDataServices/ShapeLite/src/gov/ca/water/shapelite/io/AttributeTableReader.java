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
package gov.ca.water.shapelite.io;

import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.LanguageDriver;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.ReadBytes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A class for reading attributes from a dbf file for shapefile reading.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class AttributeTableReader {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private byte[] code;

  private List<Field> fields;

  private String filename;

  private short headerSize;

  private LanguageDriver languageDriver;

  private int numFields;

  private int numRecords;

  private int numRecordsLive;

  private long[] offsets;

  private byte[] rawData;

  private Reader reader;

  private short recordSize;

  public List<Character> skip;

  //</editor-fold>
  /**
   * Creates a new instance of an AttributeTableReader which can be used to read
   * the attributes from a shapefile.
   */
  public AttributeTableReader() {
    fields = new ArrayList<>();
    skip = new ArrayList<>();
  }

  /**
   * Opens the specified dbf file name for reading attributes. This will attempt
   * to load the entire dbf into memory as the rawData bytes. It will also
   * establish the record offsets to use for individual record lookups.
   *
   * @param filename The string file of the dbf data file part of a shapefile.
   * @throws FileNotFoundException Occurs if the file is missing.
   * @throws IOException Occurs if the file cannot be parsed correctly or is
   * corrupted.
   */
  public void open(String filename) throws FileNotFoundException, IOException {
    this.filename = filename;
    rawData = ReadBytes.readAll(filename);
    readAttributes();
  }

  /**
   * Opens the specified dbf file name for reading attributes. This will attempt
   * to load the entire dbf into memory as the rawData bytes. It will also
   * establish the record offsets to use for individual record lookups.
   *
   * @param stream The input stream. This can be a file or memory stream.
   * @throws FileNotFoundException Occurs if the file is missing.
   * @throws IOException Occurs if the file cannot be parsed correctly or is
   * corrupted.
   */
  public void open(InputStream stream) throws IOException {
    rawData = ReadBytes.readAll(stream);
    readAttributes();
  }

  /**
   * Completes the reading process
   */
  public void readAttributes() {
    reader = new Reader(rawData);
    setCode(reader.read(4));
    numRecords = reader.readInt();
    headerSize = reader.readShort();
    recordSize = reader.readShort();
    reader.advanceTo(29);

    setLanguageDriver(LanguageDriver.find(reader.read(1)[0]).orElse(LanguageDriver.Windows_ANSI));
    reader.advanceTo(32);
    numFields = (headerSize - 33) / 32;
    int fieldOffset = 1;
    for (int fld = 0; fld < numFields; fld++) {
      Field field = new Field();
      field.setName(reader.readString(11));
      field.setType(reader.readString(1));
      field.setOffset(fieldOffset);
      reader.advance(4);
      field.setLength(reader.readByte());
      fieldOffset += field.getLength();
      field.setDecimal(reader.readByte());
      field.calculateDataType();
      reader.advance(14);
      getFields().add(field);
    }
    reader.advanceTo(headerSize);
    offsets = new long[numRecords];
    long offset = headerSize;
    int index = 0;
    numRecordsLive = 0;
    // Scan through to get offsets, skipping deleted records.
    for (int i = 0; i < numRecords; i++) {
      int val = reader.readByte();
      reader.advance(recordSize - 1);
      offset += recordSize;
      skip.add((char) val);
      if (val == 42) {
        continue; // if first character is * then the record is deleted, so don't count it.
      }
      offsets[index] = offset - recordSize;
      index++;
      numRecordsLive++;
    }
    reader.close();
  }

  /**
   * Gets an array of objects, where each object represents a row in the
   * attribute table where the only values returned are from the field specified
   * by its 0 based index.
   *
   * @param fieldIndex The integer 0 based index of the field. The field order
   * can be determined by the getFields method.
   * @return An array of objects that represent the parsed values from the
   * field.
   */
  public Object[] getFieldItems(int fieldIndex) {
    String[] text = getFieldStrings(fieldIndex);
    Object[] result = new Object[text.length];
    for (int row = 0; row < text.length; row++) {
      result[row] = getFields().get(fieldIndex).parse(text[row]);
    }
    return result;
  }

  /**
   * THe optional array of objects based on the fieldName. If the fieldName is
   * not matched, this will return empty.
   *
   * @param fieldName The String field name to match.
   * @return The optional object array or empty.
   */
  public final Optional<Object[]> getFieldItems(String fieldName) {
    int fld = getFieldIndex(fieldName);
    if (fld == -1) {
      return Optional.empty();
    }
    return Optional.of(getFieldItems(fld));
  }

  /**
   * Gets an array of strings corresponding to the text representation of the
   * field values for the field with the specified name. The specified name must
   * be found in the list of Fields for this Attribute table.
   *
   * @param fieldName The string field name.
   * @return The optional array of string values for all the rows in the
   * attribute table. This will be empty if the field name could not be found.
   */
  public final Optional<String[]> getFieldStrings(String fieldName) {
    int fld = getFieldIndex(fieldName);
    if (fld == -1) {
      return Optional.empty();
    }
    return Optional.of(getFieldStrings(fld));
  }

  /**
   * Gets the field with the specified name. If the field does not exist, this
   * is null.
   *
   * @param fieldName The string name of the field to get.
   * @return A Field object corresponding to the first incidence matching the
   * specified name.
   */
  public final Optional<Field> getField(String fieldName) {
    if (fieldName == null) {
      return Optional.empty();
    }
    for (Field field : getFields()) {
      if (fieldName.equals(field.getName())) {
        return Optional.of(field);
      }
    }
    return Optional.empty();
  }

  public int getFieldIndex(String fieldName) {
    for (int iField = 0; iField < getFields().size(); iField++) {
      if (getFields().get(iField).getName().equals(fieldName)) {
        return iField;
      }
    }
    return -1;
  }

  public String[] getFieldStrings(int fieldIndex) {
    reader = new Reader(rawData);
    String[] result = new String[numRecordsLive];
    for (int i = 0; i < numRecordsLive; i++) {
      reader.advanceTo(offsets[i]);
      int fieldOffset = getFields().get(fieldIndex).getOffset();
      reader.advance(fieldOffset);
      result[i] = reader.readString(getFields().get(fieldIndex).getLength());
    }
    return result;
  }

  public String[] getRow(int row) {
    reader = new Reader(rawData);
    String[] result = doGetRow(row);
    reader.close();
    return result;
  }

  public String[][] getRows(int start, int numRows) {
    reader = new Reader(rawData);
    String[][] result = new String[numRows][];
    for (int iRow = start; iRow < start + numRows; iRow++) {
      reader.advanceTo(offsets[iRow]);
      result[iRow - start] = doGetRow(iRow);
    }
    reader.close();
    return result;
  }

  public String[][] getRows() {
    return getRows(0, numRecordsLive);
  }

  private String[] doGetRow(int row) {
    String[] result = new String[getFields().size()];
    reader.advanceTo(offsets[row] + 1);
    int iField = 0;
    for (Field field : getFields()) {
      result[iField] = reader.readString(field.getLength());
      iField++;
    }
    return result;
  }

  public Object[][] getRowItems(int start, int numRows) {
    reader = new Reader(rawData);
    Object[][] result = new Object[numRows][];
    for (int iRow = start; iRow < start + numRows; iRow++) {
      reader.advanceTo(offsets[iRow]);
      result[iRow - start] = doGetRowItems(iRow);
    }
    reader.close();
    return result;
  }

  public Object[][] getRowItems() {
    return getRowItems(0, numRecordsLive);
  }

  private Object[] doGetRowItems(int row) {
    String[] text = doGetRow(row);
    Object[] result = new Object[numFields];
    int iField = 0;
    for (Field field : getFields()) {
      result[iField] = field.parse(text[iField]);
      iField++;
    }
    return result;
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the code
   */
  public byte[] getCode() {
    return code;
  }

  /**
   * @param code the code to set
   */
  public void setCode(byte[] code) {
    this.code = code;
  }

  /**
   * @return the fields
   */
  public List<Field> getFields() {
    return fields;
  }

  /**
   * @param fields the fields to set
   */
  public void setFields(List<Field> fields) {
    this.fields = fields;
  }

  /**
   * @return the filename
   */
  public String getFilename() {
    return filename;
  }

  /**
   * @param filename the filename to set
   */
  public void setFilename(String filename) {
    this.filename = filename;
  }

  /**
   * @return the _languageDriver
   */
  public LanguageDriver getLanguageDriver() {
    return languageDriver;
  }

  /**
   * @param languageDriver the _languageDriver to set
   */
  public void setLanguageDriver(LanguageDriver languageDriver) {
    this.languageDriver = languageDriver;
  }

  /**
   * @return the numRecords
   */
  public int getNumRecords() {
    return numRecords;
  }

  /**
   * @param numRecords the numRecords to set
   */
  public void setNumRecords(int numRecords) {
    this.numRecords = numRecords;
  }

  //</editor-fold>
}
