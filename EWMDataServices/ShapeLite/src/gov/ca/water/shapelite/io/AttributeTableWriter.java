/*
 * The MIT License
 *
 * Copyright 2013 hdunsford.
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

import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.progress.Cancellable;
import gov.ca.water.shapelite.progress.Progress;
import gov.ca.water.shapelite.progress.ProgressCountableCancellable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class AttributeTableWriter {

  /**
   * The binary code identifier for a DBF.
   */
  private static final byte CODE = (byte) 0x03;

  /**
   * The earliest year for a dbf.
   */
  private static final int DBF_START_YEAR = 1900;

  /**
   * The minimum size.
   */
  private static final int MIN_SIZE = 1;
  /**
   * The maximum size.
   */
  private static final int MAX_SIZE = 254;

  /**
   * The size of the leading bytes before the field definitions start.
   */
  private static final int PREFIX_BYTES = 32;

  /**
   * The size of each field definition.
   */
  private static final int FIELD_BYTES = 32;

  /**
   * The number of spaces to write that currently do nothing.
   */
  private static final int PREFIX_RESERVED = 20;

  /**
   * The number of text spaces for a field name.
   */
  private static final int FIELD_NAME_SIZE = 10;

  /**
   * The number of reserved bytes in the field that do nothing.
   */
  private static final int FIELD_RESERVED = 14;

  /**
   * A byte marker that indicates that no more field definitions exist in the
   * header.
   */
  private static final byte END_OF_FIELDS = (byte) 0x0D;

  /**
   * The end of file byte marker.
   */
  public static final int END_OF_FILE = 26;

  /**
   * Progress.
   */
  private ProgressCountableCancellable progress;

  /**
   * Writes the header content of the dbf file.
   *
   * @param writer The Writer object that writes to the file or stream.
   * @param featureSet The featureSet object being written.
   * @throws IOException If there was a problem writing to the writer.
   */
  private void writeHeader(Writer writer,
      FeatureSet featureSet) throws IOException {

    // write the output file type.
    writer.writeByte(CODE);

    Calendar calendar = Calendar.getInstance();

    writer.writeByte((byte) (calendar.get(Calendar.YEAR) - DBF_START_YEAR));
    writer.writeByte((byte) calendar.get(Calendar.MONTH) + 1);
    writer.writeByte((byte) calendar.get(Calendar.DAY_OF_MONTH));

    // write the number of records in the datafile.
    writer.write(featureSet.getFeatures().size());

    // write the length of the header structure.
    writer.writeShort(PREFIX_BYTES + FIELD_BYTES * featureSet.getFields().size() + 1);
    // 32 + 32 * numColumns + 1
    int recordLength = 1; // delete character
    for (Field field : featureSet.getFields()) {
      int len = field.getLength();
      if (len < MIN_SIZE) {
        len = MIN_SIZE;
        field.setLength(len);
      }
      if (len > MAX_SIZE) {
        len = MAX_SIZE;
        field.setLength(len);
      }
      recordLength += len;
    }

    // write the length of a record
    writer.writeShort(recordLength);

    // write the reserved bytes in the header
    writer.writeBlank(PREFIX_RESERVED);

    int offset = 0;
    // write all of the header records
    for (Field currentField : featureSet.getFields()) {
      // write the field name
      writer.writeBlank(currentField.getName(), FIELD_NAME_SIZE);

      // Always terminate the string with a null.  Even though
      // 11 characters are used for the string, the last character must be null.
      writer.writeBlank(1);

      // write the field type
      writer.writeBlank(currentField.getType(), 1);

      // write the field data address, offset from the start of the record.
      writer.write(offset);

      // write the length of the field.
      writer.writeByte(currentField.getLength());

      offset += currentField.getLength();

      // write the decimal count.
      writer.writeByte(currentField.getDecimal());

      // write the reserved bytes.
      writer.writeBlank(FIELD_RESERVED);
    }
    // write the end of the field definitions marker
    writer.writeByte(END_OF_FIELDS);

  }

  /**
   * Writes a featureset to the dbf stream.
   *
   * @param dbfStream The stream of the dbf file to write to.
   * @param featureSet The FeatureSet object to write attributes from.
   * @throws IOException If there was an error writing to the dbfStream.
   */
  public final void write(OutputStream dbfStream, FeatureSet featureSet)
      throws IOException {
    try {
      Writer writer = new Writer(dbfStream);
      this.writeHeader(writer, featureSet);
      if (progress != null) {
        progress.create("Writing DBF for " + featureSet.getName() + ".");
        progress.start(featureSet.getFeatures().size());
      }

      for (Feature feature : featureSet.getFeatures()) {
        HashMap<String, String> attributes = feature.getAttributes();
        writer.writeSpace(" ", 1); // a "*" would signify the row was deleted.
        for (Field field : featureSet.getFields()) {
          int len = field.getLength();
          if (attributes.containsKey(field.getName())) {
            writer.writeSpace(attributes.get(field.getName()), len);
          } else {
            writer.writeSpace(len);
          }
        }
        if (progress != null) {
          if (progress.isCanceled()) {
            progress.finish();
            return;
          }
        }
      }
      writer.writeByte(END_OF_FILE);
      writer.flush();
      if (progress != null) {
        progress.finish();
      }
    } catch (Exception ex) {
      if (progress != null) {
        progress.finish();
      }
      throw ex;
    }

  }

  /**
   * Gets an optional progress indicator.
   *
   * @return the progress
   */
  public final Progress getProgress() {
    return progress;
  }

  /**
   * sets an optional progress indicator.
   *
   * @param progress the progress to set
   */
  public final void setProgress(ProgressCountableCancellable progress) {
    this.progress = progress;
  }

}
