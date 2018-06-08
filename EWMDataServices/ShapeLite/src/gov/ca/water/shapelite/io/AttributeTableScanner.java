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
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Optional;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class for reading attributes from a dbf file for shapefile reading. This
 * supports forward reading only, so if you query record 10, you cannot then ask
 * for record 9 from the same stream.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class AttributeTableScanner implements AutoCloseable {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The ascii code for an asterix.
   */
  private static final int ASTERIX = 42;

  /**
   * The integer size of the dbf code.
   */
  private static final int CODE_SIZE = 4;

  /**
   * The offset in the header in bytes of the language driver.
   */
  private static final int LANGUAGE_DRIVER_OFFSET = 29;

  /**
   * The offset in the header in bytes where field definitions begin.
   */
  private static final int FIELD_OFFSET = 32;

  /**
   * The size in bytes of each field definition in the header.
   */
  private static final int FIELD_SIZE = 32;

  /**
   * The size in bytes of the 32 byte pre-field content and the header
   * terminating character.
   */
  private static final int NON_FIELD_HEADER_SIZE = 33;

  /**
   * The length in bytes of a field character string.
   */
  private static final int FIELD_NAME_SIZE = 11;

  /**
   * Size of bytes in header definition to skip over.
   */
  private static final int SKIP_SIZE = 4;

  /**
   * Size of bytes in header definition at the end to skip over.
   */
  private static final int TAIL_SIZE = 14;

  /**
   * The maximum character length of a string representation of a number to
   * attempt to convert to an integer representation.
   */
  private static final int MAX_INT_LENGTH = 10;

  /**
   * The maximum character length of a string representation of a number to
   * attempt to turn into a long integer.
   */
  private static final int MAX_LONG_LENGTH = 19;

  /**
   * An end of file marker for DBF tables.
   */
  private static final int END_OF_FILE = 26;

  /**
   * A four byte DBF code representing the kind of file.
   */
  private byte[] code;

  /**
   * The list of Field objects describing the data type and characteristics for
   * each attribute column.
   */
  private final List<Field> fields;

  /**
   * The string filename of the DBF file.
   */
  private String filename;

  /**
   * The short integer size of the header in bytes.
   */
  private short headerSize;

  /**
   * The specification of the language to use for interpreting characters.
   */
  private LanguageDriver languageDriver;

  /**
   * The zero based integer number of fields.
   */
  private int numFields;

  /**
   * The zero based integer number of records.
   */
  private int numRecords;

  /**
   * An internal Scanner object for reading data.
   */
  private Scanner scanner;

  /**
   * The short integer measuring size of a record in bytes.
   */
  private short recordSize;

  /**
   * The long integer offset in bytes of the current row.
   */
  private long currentOffset;

  /**
   * The integer record index of the current record.
   */
  private int currentRecord;

  //</editor-fold>
  /**
   * Creates a new instance of an AttributeTableReader which can be used to read
   * the attributes from the specified dbf stream. The header content is scanned
   * automatically, populating the various properties of this scanner.
   *
   * @param inputStream The input stream to call the attribute of.
   */
  public AttributeTableScanner(InputStream inputStream) {
    fields = new ArrayList<>();
    languageDriver = LanguageDriver.Windows_ANSI;
    doScanHeader(inputStream);
  }

  /**
   * Reads the dbf header content.
   *
   * @param stream The input stream to scan.
   */
  private void doScanHeader(InputStream stream) {
    scanner = new Scanner(stream);
    setCode(scanner.read(CODE_SIZE));
    numRecords = scanner.readInt();
    headerSize = scanner.readShort();
    recordSize = scanner.readShort();
    scanner.advanceTo(LANGUAGE_DRIVER_OFFSET);
    setLanguageDriver(LanguageDriver.find(scanner.read(1)[0]).
        orElse(LanguageDriver.Windows_ANSI));
    scanner.advanceTo(FIELD_OFFSET);
    numFields = (headerSize - NON_FIELD_HEADER_SIZE) / FIELD_SIZE;
    int fieldOffset = 1;
    for (int fld = 0; fld < numFields; fld++) {
      Field field = new Field();
      field.setName(scanner.readString(FIELD_NAME_SIZE));
      field.setType(scanner.readString(1));
      field.setOffset(fieldOffset);
      scanner.advance(SKIP_SIZE);
      field.setLength(scanner.readByte());
      fieldOffset += field.getLength();
      field.setDecimal(scanner.readByte());
      if ("N".equals(field.getType())) {
        if (field.getDecimal() == 0) {
          if (field.getLength() < MAX_INT_LENGTH) {
            field.setDataType(Integer.class);
          } else if (field.getLength() < MAX_LONG_LENGTH) {
            field.setDataType(Long.class);
          }
        } else {
          field.setDataType(Double.class);
        }
      }
      if ("L".equals(field.getType())) {
        field.setDataType(Boolean.class);
      }
      if ("D".equals(field.getType())) {
        field.setDataType(Date.class);
      }
      scanner.advance(TAIL_SIZE);
      getFields().add(field);
    }
    scanner.advanceTo(headerSize);
    currentOffset = headerSize;
    currentRecord = 0;
  }

  /**
   * This closes the scanner and stream if there is one.
   */
  @Override
  public final void close() {
    if (scanner != null) {
      scanner.close();
    }
  }

  /**
   * Gets a list of objects associated with the specified field index.
   *
   * @param fieldIndex The integer field index to return.
   * @return A List of objects. This will never be null.
   * @throws IllegalArgumentException if the field index is out of bounds.
   */
  public final List<Object> getFieldItems(int fieldIndex) {
    List<String> values = getFieldStrings(fieldIndex);
    if (fieldIndex < 0 || fieldIndex >= getFields().size()) {
      throw new IllegalArgumentException("The fieldIndex parameter was out of bounds.");
    }
    List<Object> result = new ArrayList<>();
    for (String value : values) {
      result.add(getFields().get(fieldIndex).parse(value));
    }
    return result;
  }

  /**
   * Gets a list of objects associated with the specified field name.
   *
   * @param fieldName the String name of the field to retrieve values for.
   * @return A List of objects. This will never be null.
   * @throws IllegalArgumentException if the field name is invalid, or is null.
   */
  public final List<Object> getFieldItems(@NonNull String fieldName) {
    if (fieldName == null) {
      throw new IllegalArgumentException("Parameter fieldName cannot be null.");
    }
    int fld = getFieldIndex(fieldName);
    if (fld == -1) {
      throw new IllegalArgumentException("The fieldName " + fieldName
          + " could not be found.");
    }
    return getFieldItems(fld);
  }

  /**
   * Gets a list of Strings associated with the specified field name.
   *
   * @param fieldName the String name of the field to retrieve values for.
   * @return A List of objects. This will never be null.
   * @throws IllegalArgumentException if the field name is invalid.
   */
  public final List<String> getFieldStrings(@NonNull String fieldName) {
    if (fieldName == null) {
      throw new IllegalArgumentException("Parameter fieldName cannot be null.");
    }
    int fld = getFieldIndex(fieldName);
    if (fld == -1) {
      throw new IllegalArgumentException("The fieldName " + fieldName
          + " could not be found.");
    }
    return getFieldStrings(fld);
  }

  /**
   * Gets the Field object associated with the specified field name.
   *
   * @param fieldName The String field name that should not be null.
   * @return An Optional Field. This will be empty if the name does not match
   * any entries.
   * @throws IllegalArgumentException if the field name is null.
   */
  public final Optional<Field> getField(@NonNull String fieldName) {
    if (fieldName == null) {
      throw new IllegalArgumentException("Parameter fieldName cannot be null.");
    }
    for (Field field : getFields()) {
      if (fieldName.equals(field.getName())) {
        return Optional.of(field);
      }
    }
    return Optional.empty();
  }

  /**
   * Gets the integer index of the specified field name or -1 if it is not
   * found.
   *
   * @param fieldName The String field name to get the index for.
   * @return The integer index.
   */
  public final int getFieldIndex(String fieldName) {
    for (int iField = 0; iField < getFields().size(); iField++) {
      if (getFields().get(iField).getName().equals(fieldName)) {
        return iField;
      }
    }
    return -1;
  }

  /**
   * Returns an array of strings where each string represents one record from
   * the dbf file. This will advance through the entire file, while only
   * returning values for one field. The readHeader should be called before
   * this, and this should be the only method called after scanHeader.
   *
   * @param fieldIndex The zero based integer field index.
   * @return An array of strings.
   */
  public final List<String> getFieldStrings(int fieldIndex) {
    List<String> results = new ArrayList<>();
    if (scanner == null) {
      return results;
    }
    int fieldOffset = getFields().get(fieldIndex).getOffset();
    for (int i = 0; i < numRecords; i++) {
      int val = scanner.readByte();
      if (val == ASTERIX) {
        scanner.advance(recordSize);
        currentOffset += recordSize;
        continue;
      }
      scanner.advance(fieldOffset - 1);
      String value = scanner.readString(getFields().get(fieldIndex).getLength());
      results.add(value);
      currentOffset += recordSize;
      scanner.advanceTo(currentOffset);

    }
    return results;
  }

  /**
   * Gets the array of all the String field values for the specified row.
   *
   * @param row The zero based integer row index.
   * @return An Array of String field values.
   */
  public final String[] getRow(int row) {
    String[] result = doGetRow(row);
    return result;
  }

  /**
   * Gets all the rows as a list of row arrays of Strings.
   *
   * @param start The integer start index.
   * @param numRows The integer number of rows.
   * @return The list of strings.
   */
  public final List<String[]> getRows(int start, int numRows) {
    List<String[]> result = new ArrayList<>();
    for (int iRow = start; iRow < start + numRows; iRow++) {
      advanceTo(iRow);
      result.add(getCurrentRow());
    }
    return result;
  }

  /**
   * Gets all the row objects as a list of String arrays, where each array
   * represents all the fields from a single row.
   *
   * @return The List of Strings rows.
   */
  public final List<String[]> getRows() {
    List<String[]> results = new ArrayList<>();
    Optional<String[]> data = getNextRow();
    while (data.isPresent()) {
      results.add(data.get());
      data = getNextRow();
    }
    return results;
  }

  /**
   * Gets the array of String attribute values for the specified row index.
   *
   * @param row The zero based integer row index.
   * @return An Array of strings representing all the field values for the
   * specified row.
   */
  private String[] doGetRow(int row) {
    String[] result = new String[getFields().size()];
    advanceTo(row);
    int iField = 0;
    for (Field field : getFields()) {
      result[iField] = scanner.readString(field.getLength());
      iField++;
    }
    return result;
  }

  /**
   * When iterating through the rows, this will return the strings for the
   * current row index.
   *
   * @return The Array of strings for the current row.
   */
  private String[] getCurrentRow() {
    String[] result = new String[getFields().size()];
    int iField = 0;
    for (Field field : getFields()) {
      result[iField] = scanner.readString(field.getLength());
      iField++;
    }
    return result;
  }

  /**
   * Advances the current row to the next, non-deleted row.
   */
  private void advanceNext() {
    currentOffset += recordSize;
    currentRecord++;
    int deleted = scanner.readByte();
    while (deleted == ASTERIX) {
      scanner.advance(recordSize - 1);
      deleted = scanner.readByte();
      currentOffset += recordSize;
    }
  }

  /**
   * Advances to the specified row index (measured as non-deleted).
   *
   * @param row The next non deleted row to advance to.
   */
  private void advanceTo(int row) {
    if (row >= numRecords) {
      Logger.getLogger(AttributeTableScanner.class.getName()).log(Level.SEVERE,
          "Row index was out of bounds.",
          new Exception("Row was past end of file."));
    }
    for (int i = currentRecord; i < numRecords; i++) {
      int deleted = scanner.readByte();
      if (deleted == ASTERIX) {
        scanner.advance(recordSize - 1);
      } else {
        if (currentRecord == row) {
          return;
        }
        currentRecord++;
        scanner.advance(recordSize - 1);
      }
      currentOffset += recordSize;
    }
  }

  /**
   * Gets the next row, or null if the scanner cannot read.
   *
   * @return The optional Array of Strings representing the row values, or empty
   * if there are no further rows.
   */
  public final Optional<String[]> getNextRow() {
    String[] result = new String[getFields().size()];
    int deleted;
    while ((deleted = scanner.readByte()) == ASTERIX) {
      scanner.advance(recordSize - 1);
      currentOffset += recordSize;
    }
    if (deleted == -1 || deleted == END_OF_FILE) {
      // end of file, return null.
      return Optional.empty();
    }
    int iField = 0;
    for (Field field : getFields()) {
      result[iField] = scanner.readString(field.getLength());
      iField++;
    }
    return Optional.of(result);
  }

  /**
   * Gets the list of Objects representing the row values that have been
   * converted from strings into numeric object representations.
   *
   * @param start The zero based integer start index.
   * @param numRows the integer number of rows to get.
   * @return The list of rows represented as objects arrays.
   */
  public final List<Object[]> getRowItems(int start, int numRows) {
    List<Object[]> result = new ArrayList<>();
    for (int iRow = start; iRow < start + numRows; iRow++) {
      advanceTo(start);
      result.add(toObjects(getCurrentRow()));
    }
    scanner.close();
    return result;
  }

  /**
   * Gets the list of Objects representing all the row values that have been
   * converted from strings into numeric object representations.
   *
   * @return A list of objects for all the rows.
   */
  public final List<Object[]> getRowItems() {
    Optional<String[]> data = getNextRow();
    List<Object[]> result = new ArrayList<>();

    while (data.isPresent()) {
      result.add(toObjects(data.get()));
      data = getNextRow();
    }
    return result;
  }

  /**
   * Converts an Array of String values to an array of objects, based on the
   * best guess parsing of the associated Field objects.
   *
   * @param text The String array with row values to convert.
   * @return The Object array representation of the parsed row.
   */
  private Object[] toObjects(String[] text) {
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
   * Gets the four byte DBF code representing the kind of file.
   *
   * @return the code
   */
  public final byte[] getCode() {
    return code;
  }

  /**
   * Sets the four byte DBF code representing the kind of file.
   *
   * @param code the code to set
   */
  public final void setCode(byte[] code) {
    this.code = code;
  }

  /**
   * Gets the list of Field objects describing the data type and characteristics
   * for each attribute column.
   *
   * @return the values
   */
  public final List<Field> getFields() {
    return fields;
  }

  /**
   * Gets the string filename of the DBF file.
   *
   * @return the filename
   */
  public final String getFilename() {
    return filename;
  }

  /**
   * Sets the string filename of the DBF file.
   *
   * @param filename the filename to set
   */
  public final void setFilename(String filename) {
    this.filename = filename;
  }

  /**
   * Gets The specification of the language to use for interpreting characters.
   *
   * @return the _languageDriver
   */
  public final LanguageDriver getLanguageDriver() {
    return languageDriver;
  }

  /**
   * Sets the specification of the language to use for interpreting characters.
   *
   * @param languageDriver the _languageDriver to set
   */
  public final void setLanguageDriver(LanguageDriver languageDriver) {
    this.languageDriver = languageDriver;
  }

  /**
   * Gets the zero based integer number of records.
   *
   * @return the numRecords
   */
  public final int getNumRecords() {
    return numRecords;
  }

  /**
   * Sets the zero based integer number of records.
   *
   * @param numRecords the numRecords to set
   */
  public final void setNumRecords(int numRecords) {
    this.numRecords = numRecords;
  }

  //</editor-fold>
}
